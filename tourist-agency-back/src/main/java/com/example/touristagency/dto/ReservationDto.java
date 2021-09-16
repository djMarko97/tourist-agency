package com.example.touristagency.dto;

import com.example.touristagency.entity.HotelEntity;
import com.example.touristagency.entity.RoomEntity;
import com.example.touristagency.entity.TransportationEntity;
import com.example.touristagency.enumeration.Meals;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReservationDto implements MyDto {

    private Long id;

    private Date dateFrom;

    private Date dateTo;

    private int numberOfNights;

    private List<RoomEntity> rooms;

    private Meals meals;

    private HotelEntity hotel;

    private TransportationEntity transportation;

    private double totalPrice;

    private int people;

    private DestinationDto destination;

    private int numberOfArrangementsLeft;

    private Set<UserDto> usersReservations = new HashSet<>();

    public ReservationDto() {
        super();
    }

    public ReservationDto(Long id, Date dateFrom, Date dateTo, int numberOfNights,
                          List<RoomEntity> rooms, Meals meals, HotelEntity hotel, TransportationEntity transportation,
                          double totalPrice, int people, DestinationDto destination, int numLeft) {
        super();
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.numberOfNights = numberOfNights;
        this.rooms = rooms;
        this.meals = meals;
        this.hotel = hotel;
        this.transportation = transportation;
        this.totalPrice = totalPrice;
        this.people = people;
        this.destination = destination;
        this.numberOfArrangementsLeft = numLeft;
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

    public Meals getMeals() {
        return meals;
    }

    public void setMeals(Meals meals) {
        this.meals = meals;
    }

    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

    public TransportationEntity getTransportation() {
        return transportation;
    }

    public void setTransportation(TransportationEntity transportation) {
        this.transportation = transportation;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public DestinationDto getDestination() {
        return destination;
    }

    public void setDestination(DestinationDto destination) {
        this.destination = destination;
    }

    public int getNumberOfArrangementsLeft() {
        return numberOfArrangementsLeft;
    }

    public void setNumberOfArrangementsLeft(int numberOfArrangementsLeft) {
        this.numberOfArrangementsLeft = numberOfArrangementsLeft;
    }

    public Set<UserDto> getUsersReservations() {
        return usersReservations;
    }

    public void setUsersReservations(Set<UserDto> usersReservations) {
        this.usersReservations = usersReservations;
    }
}
