package com.sport.wintersports;

public class Sport {
    private String name;
    private String imageUrl;

    public Sport(String name, String imageUrl) {
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
