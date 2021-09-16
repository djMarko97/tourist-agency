package com.example.touristagency.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.sun.istack.NotNull;

@Entity
@Table(name="destination")
public class DestinationEntity implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Column(name = "postal_code", unique = true)
    @NotNull
    private int postalCode;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private StateEntity state;

    public DestinationEntity(){
        super();
        // TODO Auto-generated constructor stub
    }

    public DestinationEntity(Long id, String name, int postalCode, StateEntity state){
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

    public StateEntity getState() {
        return state;
    }

    public void setState(StateEntity state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "DestinationEntity [id=" + id + ", name=" + name + ", postalCode=" + postalCode + ", state=" + state
                + "]";
    }
}























