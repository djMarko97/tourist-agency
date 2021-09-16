package com.example.touristagency.entity;


import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "hotel")
public class HotelEntity implements MyEntity {

    /* Hotel Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Name */
    private String name;

    /* Address */
    @NotNull
    private String address;

    /* Rating */
    private int rating;

    /* Destination */
    @ManyToOne
    @JoinColumn(name = "destination_id")
    private DestinationEntity destination;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_file")
    private String imageType;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    public HotelEntity() {

    }

    public HotelEntity(Long id, String name, String address, int rating, DestinationEntity destination,
                       String imageName, String imageType, byte[] image/*, String extension*/) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.destination = destination;
        this.imageName = imageName;
        this.imageType = imageType;
        this.image = image;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public DestinationEntity getDestination() {
        return destination;
    }

    public void setDestination(DestinationEntity destination) {
        this.destination = destination;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
