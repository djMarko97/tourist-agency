package com.example.touristagency.dto;

import com.example.touristagency.enumeration.Season;
import com.example.touristagency.enumeration.TransportationType;

import java.math.BigDecimal;

public class TransportationDto implements MyDto{

    private Long id;

    private TransportationType type;

    private BigDecimal price;

    private Season season;

    private DestinationDto start;

    private DestinationDto end;

    public TransportationDto() {
        super();

    }

    public TransportationDto(Long id, TransportationType type, BigDecimal price, Season season, DestinationDto start,
                             DestinationDto end) {
        super();
        this.id = id;
        this.type = type;
        this.price = price;
        this.season = season;
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransportationType getType() {
        return type;
    }

    public void setType(TransportationType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public DestinationDto getStart() {
        return start;
    }

    public void setStart(DestinationDto start) {
        this.start = start;
    }

    public DestinationDto getEnd() {
        return end;
    }

    public void setEnd(DestinationDto end) {
        this.end = end;
    }
}
