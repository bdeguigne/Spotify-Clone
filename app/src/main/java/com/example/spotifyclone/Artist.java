package com.example.spotifyclone;

public class Artist {
    private final String name;
    private final String imageURL;

    public Artist(String name, String imageURL) {
        this.name = name;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }
}

