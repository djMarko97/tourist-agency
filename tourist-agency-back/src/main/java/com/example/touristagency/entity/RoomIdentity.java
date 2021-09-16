package com.example.touristagency.entity;

public class RoomIdentity implements MyEntity{
    private Long hotel_id;
    private String room_number;

    public RoomIdentity() {

    }

    public RoomIdentity(Long hotel_id, String room_number) {
        super();
        this.hotel_id = hotel_id;
        this.room_number = room_number;
    }

    public Long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }
}
