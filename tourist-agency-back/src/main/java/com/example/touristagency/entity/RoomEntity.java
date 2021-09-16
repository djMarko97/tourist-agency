package com.example.touristagency.entity;

import com.example.touristagency.enumeration.RoomType;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class RoomEntity implements MyEntity{

    /*Room ID*/
    @EmbeddedId
    private RoomIdentity id;

    /* Hotel */
    @MapsId("hotel_id")
    @ManyToOne
    private HotelEntity hotel;

    /* Description */
    private String description;

    /* Price per night */
    @Column(name = "price_per_night")
    private BigDecimal pricePerNight;

    /* Room type */
    @Column(name = "room_type")
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    /* Is it available? */
    private boolean available;

    /* Reservation */
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="reservation_id")
    private ReservationEntity reservation;

    public RoomEntity(){

    }

    public RoomEntity(RoomIdentity id, HotelEntity hotel, String description, BigDecimal pricePerNight,
                      RoomType roomType, boolean available, ReservationEntity reservation){
        super();
        this.id = id;
        this.hotel = hotel;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.roomType = roomType;
        this.available = available;
        this.reservation = reservation;
    }

    public RoomIdentity getId() {
        return id;
    }

    public void setId(RoomIdentity id) {
        this.id = id;
    }

    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public ReservationEntity getReservation() {
        return reservation;
    }

    public void setReservation(ReservationEntity reservation) {
        this.reservation = reservation;
    }
}
