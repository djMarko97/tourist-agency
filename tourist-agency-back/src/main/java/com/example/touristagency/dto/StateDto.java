package com.example.touristagency.dto;

public class StateDto implements MyDto{

    private Long id;
    private String name;

    public StateDto(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public StateDto() {
        super();
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

    @Override
    public String toString() {
        return "DestinationDto [id=" + id + ", name=" + name + "]";
    }
}
