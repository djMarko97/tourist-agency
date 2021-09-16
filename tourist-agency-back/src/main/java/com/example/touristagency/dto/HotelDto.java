package com.example.touristagency.dto;

public class HotelDto implements MyDto {
    private Long id;
    private String name;
    private String address;
    private int rating;
    private DestinationDto destination;
    private String imageName;
    private String imageType;
    private byte[] image;


    public HotelDto() {


    }


    public HotelDto(Long id, String name, String address, int rating, DestinationDto destination, String imageName,
                    String imageType, byte[] image) {
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

    public DestinationDto getDestination() {
        return destination;
    }

    public void setDestination(DestinationDto destination) {
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
