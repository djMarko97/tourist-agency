package com.example.touristagency.entity;

import com.example.touristagency.enumeration.Season;
import com.example.touristagency.enumeration.TransportationType;

import javax.persistence.*;

@Entity
@Table(name = "transportation")
public class TransportationEntity implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransportationType type;

    private double price;

    @Enumerated(EnumType.STRING)
    private Season season;

    @ManyToOne
    @JoinColumn(name = "start")
    private DestinationEntity start;

    @ManyToOne
    @JoinColumn(name = "end")
    private DestinationEntity end;

    public TransportationEntity(){
        super();
        // TODO Auto-generated constructor stub
    }

    public TransportationEntity(Long id, TransportationType type, double price, Season season,
                                DestinationEntity startDestination, DestinationEntity endDestination) {
        super();
        this.id = id;
        this.type = type;
        this.price = price;
        this.season = season;
        this.start = startDestination;
        this.end = endDestination;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public DestinationEntity getStart() {
        return start;
    }

    public void setStart(DestinationEntity start) {
        this.start = start;
    }

    public DestinationEntity getEnd() {
        return end;
    }

    public void setEnd(DestinationEntity end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "TransportationEntity{" +
                "id=" + id +
                ", type=" + type +
                ", price=" + price +
                ", season=" + season +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
