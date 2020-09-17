package com.example.spotifyclone;

public class Playlist {
    private final String name;
    private final String author;
    private final String imageURL;
    private final boolean isCreatePlaylist;

    public Playlist(String name, String author, String imageURL) {
        this.name = name;
        this.author = author;
        this.imageURL = imageURL;
        this.isCreatePlaylist = false;
    }

    public Playlist() {
        this.name = null;
        this.author = null;
        this.imageURL = null;
        this.isCreatePlaylist = true;
    }

    public Playlist(String name) {
        this.name = name;
        this.author = "User";
        this.imageURL = null;
        this.isCreatePlaylist = false;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageURL() {
        return imageURL;
    }

    public boolean getIsCreatePlaylist() { return isCreatePlaylist; }
}