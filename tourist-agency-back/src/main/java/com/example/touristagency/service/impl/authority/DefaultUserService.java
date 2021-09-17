package com.example.touristagency.service.impl.authority;

import com.example.touristagency.dto.ReservationDto;
import com.example.touristagency.dto.UserDto;
import com.example.touristagency.entity.ReservationEntity;
import com.example.touristagency.entity.authority.UserEntity;
import com.example.touristagency.mapper.ReservationMapper;
import com.example.touristagency.mapper.authority.UserCreateMapper;
import com.example.touristagency.mapper.authority.UserViewMapper;
import com.example.touristagency.repository.ReservationRepository;
import com.example.touristagency.repository.authority.UserRepository;
import com.example.touristagency.requests.authority.CreateUserRequest;
import com.example.touristagency.service.RoleService;
import com.example.touristagency.service.UserService;
import com.example.touristagency.service.impl.MailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.Objects;
import java.util.Set;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DefaultUserService implements UserService {

    /**
     * User Repository
     **/
    private final UserRepository userRepository;

    /**
     * Password encoder
     **/
    private final PasswordEncoder passwordEncoder;

    /**
     * User View Mapper
     **/
    private final UserViewMapper userViewMapper;

    /**
     * User Create Mapper
     **/
    private final UserCreateMapper userCreateMapper;

    /**
     * Reservation Mapper
     */
    private final ReservationMapper reservationMapper;

    /**
     * Role Service
     **/
    private final RoleService roleService;

    /**
     * Reservation Repository
     **/
    private final ReservationRepository reservationRepository;

    /**
     * Authorization Service
     */
    private final DefaultAuthorizationService defaultAuthorizationService;

    private final MailService mailService;

    public DefaultUserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserViewMapper userViewMapper, UserCreateMapper userCreateMapper, ReservationMapper reservationMapper, RoleService roleService, ReservationRepository reservationRepository, DefaultAuthorizationService defaultAuthorizationService, MailService mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userViewMapper = userViewMapper;
        this.userCreateMapper = userCreateMapper;
        this.reservationMapper = reservationMapper;
        this.roleService = roleService;
        this.reservationRepository = reservationRepository;
        this.defaultAuthorizationService = defaultAuthorizationService;
        this.mailService = mailService;
    }

    /**
     * Creates user
     *
     * @param createUserRequest userRequest to save
     * @return User DTO
     */

    @Override
    public UserDto createUser(CreateUserRequest createUserRequest) {
        Objects.requireNonNull(createUserRequest);

        if(this.userRepository.findByUsername(createUserRequest.getUsername()).isPresent()){
            throw new ValidationException("Username exists!");
        }
        if (!StringUtils.equals(createUserRequest.getPassword(), createUserRequest.getRePassword())) {
            throw new javax.validation.ValidationException("Passwords don't match");
        }

        UserEntity userEntity = this.userCreateMapper.toEntity(createUserRequest);
        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));

        userEntity = this.userRepository.save(userEntity);

        userEntity = this.roleService.addUserToRole(userEntity.getId(), RoleService.ROLE_USER);

        return this.userViewMapper.toDto(userEntity);
    }

    /**
     * Update User
     *
     * @param userEntity userEntity to update
     * @return User Entity
     */

    @Override
    public UserEntity save(UserEntity userEntity) {
        Objects.requireNonNull(userEntity);
        Objects.requireNonNull(userEntity.getId());

        this.userRepository.findById(userEntity.getId()).orElseThrow(()->
                new EntityNotFoundException("User with that id does not exists")
        );

        return this.userRepository.save(userEntity);

    }
    /**
     * Search users by id
     *
     * @param id id of the user for search
     * @return User Entity
     */
    @Override
    public UserEntity findById(Long id) {
        Objects.requireNonNull(id);

        return this.userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User with that id does not exists"));
    }

    @Override
    public Set<ReservationDto> getReservationsByUserId(Long userId) {
        UserEntity user = findById(userId);
        Set<ReservationEntity> reservations = user.getReservations();
        return reservations.stream().map(reservationMapper::toDto).collect(Collectors.toSet());

    }

    private UserEntity validateUser(Long idUser) {
        Objects.requireNonNull(idUser);

        return this.userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    public ReservationEntity validateReservation(Long idReservation) {
        Objects.requireNonNull(idReservation);

        return  this.reservationRepository.findById(idReservation)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Override
    public Set<ReservationDto> removeReservationForUser(Long idUser, Long idRes) {
        UserEntity userEntity = this.validateUser(idUser);
        ReservationEntity reservationEntity = this.validateReservation(idRes);

        if(!reservationEntity.getUsers().contains(userEntity)){
            throw new ValidationException("User does not have that reservation");
        }

        reservationEntity.getUsers().remove(userEntity);
        reservationEntity.setNumberOfArrangementsLeft(reservationEntity.getNumberOfArrangementsLeft() + 1);
        this.reservationRepository.save(reservationEntity);

        // Da izbegnemo da opet loadujemo usera posto nisu updatovane rezervacije, rucno cemo dodati na njega ovu sto smo sacuvali
        userEntity.getReservations().remove(reservationEntity);
        try {
            this.mailService.sendEmail(userEntity.getUsername(), "Cancelation of reservation", "You canceled your reservation. Please let us know why.");
        } catch (Exception e) {
            //#TODO
        }
        return userEntity.getReservations().stream().map(this.reservationMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public Set<ReservationDto> addReservationToUser(Long idUser, Long idRes) {

        UserEntity userEntity = this.validateUser(idUser);
        ReservationEntity reservationEntity = this.validateReservation(idRes);

        if(reservationEntity.getUsers().contains(userEntity)){
            throw new ValidationException("User already has that reservation");
        }

        if(reservationEntity.getNumberOfArrangementsLeft() <= 0){
            throw new ValidationException("Cannot add user to reservation, no arrangements left");
        }

        reservationEntity.getUsers().add(userEntity);
        reservationEntity.setNumberOfArrangementsLeft(reservationEntity.getNumberOfArrangementsLeft() - 1);
        this.reservationRepository.save(reservationEntity);

        // Da izbegnemo da opet loadujemo usera posto nisu updatovane rezervacije, rucno cemo dodati na njega ovu sto smo sacuvali
        userEntity.getReservations().add(reservationEntity);

        try {
            this.mailService.sendEmailWithAttachment(userEntity.getUsername());
        } catch (Exception e) {
            //#TODO
        }

        return userEntity.getReservations().stream().map(this.reservationMapper::toDto).collect(Collectors.toSet());
    }
}
