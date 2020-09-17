package com.example.spotifyclone;

import android.content.Context;

import com.example.spotifyclone.home.HomeAlbum;
import com.example.spotifyclone.home.HomeContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JSONReader {

    private static List<HomeContent> homeContents = new ArrayList<>();
    private static List<Playlist> playlists = new ArrayList<>();
    private static List<Artist> artists = new ArrayList<>();

    public static void getDataFromJSON(Context context) {
        try {
            JSONObject obj = new JSONObject(Objects.requireNonNull(loadJSONFromAsset("data.json", context)));

            // HOME //

            JSONArray homeArray = obj.getJSONArray("home");
            for (int i = 0; i < homeArray.length(); i++) {
                List<HomeAlbum> homeAlbums = new ArrayList<>();
                JSONObject jo_inside = homeArray.getJSONObject(i);

                String name_value = jo_inside.getString("title");
                final boolean isArtist = jo_inside.getBoolean("artistGrid");

                JSONArray contentArray = jo_inside.getJSONArray("content");
                for (int j = 0; j < contentArray.length(); j++) {
                    JSONObject elem = contentArray.getJSONObject(j);

                    String name = elem.getString("name");
                    String imageURL = elem.getString("imageURL");

                    HomeAlbum content = new HomeAlbum(name, imageURL, isArtist);
                    homeAlbums.add(content);
                }

                //Add values in Playlist Class
                HomeContent homeContent = new HomeContent(name_value, homeAlbums);

                homeContents.add(homeContent);
            }

            // PLAYLISTS //

            JSONArray playlistsArray = obj.getJSONArray("playlists");
            for (int i = 0; i < playlistsArray.length(); i++) {
                // add the "Create Playlist button at the start//
                if (i == 0) {
                    Playlist playlist = new Playlist();
                    playlists.add(playlist);
                }
                JSONObject jo_inside = playlistsArray.getJSONObject(i);
                String name_value = jo_inside.getString("name");
                String author_value = jo_inside.getString("author");
                String image_value = jo_inside.getString("imageURL");

                Playlist playlist = new Playlist(name_value, author_value, image_value);

                playlists.add(playlist);
            }

            // LIBRARY ARTISTS //

            JSONArray libraryArtistsArray = obj.getJSONArray("library_artists");
            for (int i = 0; i < libraryArtistsArray.length(); i++) {
                JSONObject jo_inside = libraryArtistsArray.getJSONObject(i);
                String name_value = jo_inside.getString("name");
                String image_value = jo_inside.getString("imageURL");

                Artist artist = new Artist(name_value, image_value);

                artists.add(artist);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static List<HomeContent> getHomeContents() {
        return homeContents;
    }

    public static List<Playlist> getPlaylists() {
        return playlists;
    }

    public static List<Artist> getArtists() {
        return artists;
    }

    public static String loadJSONFromAsset(String filename, Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
