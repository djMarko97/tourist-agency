package com.example.touristagency.dto;

import com.example.touristagency.entity.RoomIdentity;
import com.example.touristagency.enumeration.RoomType;

import java.math.BigDecimal;

public class RoomDto implements MyDto{

    private RoomIdentity id;
    private String description;
    private BigDecimal pricePerNight;
    private RoomType roomType;
    private boolean available;
    private HotelDto hotel;

    public RoomDto() {


    }

    public RoomDto(RoomIdentity id, String description, BigDecimal pricePerNight, RoomType roomType, boolean available,
                   HotelDto hotel) {
        super();
        this.id = id;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.roomType = roomType;
        this.available = available;
        this.hotel = hotel;
    }

    public RoomIdentity getId() {
        return id;
    }

    public void setId(RoomIdentity id) {
        this.id = id;
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

    public HotelDto getHotel() {
        return hotel;
    }

    public void setHotel(HotelDto hotel) {
        this.hotel = hotel;
    }
}
