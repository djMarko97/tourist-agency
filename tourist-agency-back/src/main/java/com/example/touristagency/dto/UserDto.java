package com.example.touristagency.dto;

import com.example.touristagency.entity.ReservationEntity;
import com.example.touristagency.entity.authority.RoleEntity;

import java.util.HashSet;
import java.util.Set;

public class UserDto implements MyDto {

    /** User id **/
    private Long id;

    /** Username **/
    private String username;

    /** Firstname **/
    private String firstName;

    /** Lastname **/
    private String lastName;

    /** User roles/groups **/
    private Set<RoleEntity> authorities;

    private Set<ReservationEntity> reservations = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<RoleEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<RoleEntity> authorities) {
        this.authorities = authorities;
    }

    public Set<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationEntity> reservations) {
        this.reservations = reservations;
    }
}
