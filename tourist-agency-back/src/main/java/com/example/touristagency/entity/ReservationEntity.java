package com.example.touristagency.entity;

import javax.persistence.*;

import com.example.touristagency.entity.authority.UserEntity;
import com.example.touristagency.enumeration.*;
import com.fasterxml.jackson.annotation.JsonBackReference;


import java.util.*;

@Entity
@Table(name="reservation")
public class ReservationEntity implements MyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /*
    @ManyToOne
    @JoinColumn(name="admin")
    private UserEntity user;
    */
    @Column(name="date_from")
    private Date dateFrom;

    @Column(name="dateTo")
    private Date dateTo;

    @Column(name="num_of_nights")
    private int numberOfNights;

    @OneToMany(mappedBy = "reservation")
    private List<RoomEntity> rooms = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Meals meals;

    @ManyToOne
    @JoinColumn(name="hotel_id")
    private HotelEntity hotel;

    @ManyToOne
    @JoinColumn(name="transportation_id")
    private TransportationEntity transportation;

    @Column(name="total_price")
    private double totalPrice;

    private int people;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private DestinationEntity destination;

    @Column(name="number_left")
    private int numberOfArrangementsLeft ;

    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_reservation",
            joinColumns = { @JoinColumn(name = "reservation_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )

    @JsonBackReference
    private Set<UserEntity> usersReservations = new HashSet<>();

    public ReservationEntity() {
        super();
        this.numberOfArrangementsLeft = 1;
    }

    public ReservationEntity(Long id,/* UserEntity user,*/ Date dateFrom, Date dateTo, int numberOfNights,
                             List<RoomEntity> rooms, int people, Meals meals, TransportationEntity transportation, int numLeft,
                             DestinationEntity destination) {
        super();
        this.id = id;
        //this.user = user;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.numberOfNights = numberOfNights;
        this.rooms = rooms;
        this.meals = meals;
        this.transportation = transportation;
        this.totalPrice = getTotalPrice();
        this.people = people;
        this.destination = destination;
        this.numberOfArrangementsLeft = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public Set<UserEntity> getUsers() {
        return usersReservations;
    }

    public void setUsers(Set<UserEntity> users) {
        this.usersReservations = users;
    }

    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

    public Meals getMeals() {
        return meals;
    }

    public void setMeals(Meals meals) {
        this.meals = meals;
    }

    public TransportationEntity getTransportation() {
        return transportation;
    }

    public void setTransportation(TransportationEntity transportation) {
        this.transportation = transportation;
    }

    public double getTotalPrice() {
        int roomPrice = 0;

        for (RoomEntity roomEntity : rooms) {
            roomPrice += roomEntity.getPricePerNight().intValue();
        }

        int accommodation = numberOfNights * roomPrice;
        int meal = priceOfMeals(meals);
        int meals = numberOfNights * people * meal;
        double transport = transportation.getPrice() * people;
        return (accommodation + meals + transport)/people;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    private int priceOfMeals(Meals meal) {
        int mealPrice;
        switch (meal) {
            case BB :
                mealPrice = 10;
                break;

            case PP:
                mealPrice = 20;
                break;

            case FB:
                mealPrice = 35;
                break;

            case ALL:
                mealPrice = 50;
                break;
            default:
                mealPrice = 0;
                break;
        }
        return mealPrice;
    }

    public DestinationEntity getDestination() {
        return destination;
    }

    public void setDestination(DestinationEntity destination) {
        this.destination = destination;
    }

    public int getNumberOfArrangementsLeft() {
        return numberOfArrangementsLeft;
    }

    public void setNumberOfArrangementsLeft(int numberOfArrangementsLeft) {
        this.numberOfArrangementsLeft = numberOfArrangementsLeft;
    }
}
