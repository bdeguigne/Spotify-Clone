package com.example.spotifyclone;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spotifyclone.home.HomeContent;

import java.util.ArrayList;
import java.util.List;

public class DataViewModel extends ViewModel {
    private static DataViewModel dataViewModel;
    private MutableLiveData<List<Playlist>> livePlaylists;
    private MutableLiveData<List<HomeContent>> homeContents;
    private List<Artist> artistsList = null;

    private DataViewModel(Context context) {
        JSONReader.getDataFromJSON(context);
        fetchHomeContentList();
        fetchPlaylistList();
        fetchArtistsList();
    }

    public static synchronized DataViewModel getInstance(Context context) {
        if (dataViewModel == null) {
            dataViewModel = new DataViewModel(context);
            return dataViewModel;
        }
        return dataViewModel;
    }

    // HOME PAGE //

    public LiveData<List<HomeContent>> getHomeContentList() {
        if (homeContents == null) {
            homeContents = new MutableLiveData<>();
        }
        return homeContents;
    }

    private void fetchHomeContentList() {
        if (homeContents == null) {
            homeContents = new MutableLiveData<>();
            homeContents.setValue(JSONReader.getHomeContents());
        }
    }

    // Playlists //

    public LiveData<List<Playlist>> getPlaylistList() {
        if (livePlaylists == null) {
            livePlaylists = new MutableLiveData<>();
        }
        return livePlaylists;
    }

    private void fetchPlaylistList() {
        if (livePlaylists == null) {
            livePlaylists = new MutableLiveData<>();
            livePlaylists.setValue(JSONReader.getPlaylists());
        }
    }

    public void addPlaylist(String playlistName) {
        Playlist playlist = new Playlist(playlistName);
        List<Playlist> playlistsTmp = livePlaylists.getValue();
        playlistsTmp.add(1, playlist);
        livePlaylists.setValue(playlistsTmp);
    }

    // Artists //

    public List<Artist> getArtistsList() {
        if (artistsList == null) {
            artistsList = new ArrayList<>();
        }
        return artistsList;
    }

    private void fetchArtistsList() {
        if (artistsList == null) {
            artistsList = new ArrayList<>();
            artistsList = JSONReader.getArtists();
        }
    }
}
