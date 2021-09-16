package com.example.touristagency.entity.authority;

import com.example.touristagency.entity.MyEntity;
import com.example.touristagency.entity.ReservationEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class UserEntity implements UserDetails, MyEntity {


    /** User id **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** User username **/
    @Column(nullable = false)
    private String username;

    /** User password **/
    @Column(nullable = false)
    private String password;

    /** User firstname **/
    @Column(name = "first_name",nullable = false)
    private String firstName;

    /** User lastname **/
    @Column(name = "last_name",nullable = false)
    private String lastName;

    /** User authorities/roles **/
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> authorities = new HashSet<>();

    /** Flag whether user is enabled **/
    private boolean enabled = true;

    @ManyToMany(mappedBy = "usersReservations")
    @JsonBackReference
    private Set<ReservationEntity> reservations = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAuthorities(Set<RoleEntity> authorities) {
        this.authorities = authorities;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationEntity> reservations) {
        this.reservations = reservations;
    }

    /** Methods from the implemented interface **/

    /**
     * Checks for user roles/groups
     * @return User roles/gorups
     */

    @Override
    public Collection<RoleEntity> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Getter for username
     * @return username
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Checks if the user account is still active
     * @return true if it is active, false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return this.enabled;
    }

    /**
     * Checks if the user account is non locked
     * @return true if its non locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    /**
     * Checks if user credentials are valid
     * @return true if they are valid, false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.enabled;
    }

    /**
     * Checks if the user account is active, enabled
     * @return true if it is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
