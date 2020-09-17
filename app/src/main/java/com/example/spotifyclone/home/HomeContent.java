package com.example.spotifyclone.home;

import java.util.List;

public class HomeContent {
    private final String title;
    private final List<HomeAlbum> homeAlbums;

    public HomeContent(String title, List<HomeAlbum> homeAlbums) {
        this.title = title;
        this.homeAlbums = homeAlbums;
    }

    public String getTitle() {
        return title;
    }

    public List<HomeAlbum> getContent() {
        return homeAlbums;
    }

}
