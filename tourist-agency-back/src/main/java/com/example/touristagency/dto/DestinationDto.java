package com.example.touristagency.dto;

public class DestinationDto implements MyDto{

    private Long id;

    private String name;

    private int postalCode;

    private StateDto state;

    public DestinationDto(){
        super();
    }

    public DestinationDto(Long id, String name, int postalCode, StateDto state) {
        super();
        this.id = id;
        this.name = name;
        this.postalCode = postalCode;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public StateDto getState() {
        return state;
    }

    public void setState(StateDto state) {
        this.state = state;
    }
}
