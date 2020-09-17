package com.example.spotifyclone.home;

public class HomeAlbum {
    private final String name;
    private final String imageURL;
    private final boolean isArtist;

    public HomeAlbum(String name, String imageURL, boolean isArtist) {
        this.name = name;
        this.imageURL = imageURL;
        this.isArtist = isArtist;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public boolean getIsArtist() { return isArtist; }
}
