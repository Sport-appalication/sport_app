package com.example.sport.bodytype;

public class BodyType {
    private String name;
    private String imageUrl;

    public BodyType(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
