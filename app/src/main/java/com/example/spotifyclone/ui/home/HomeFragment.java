package com.example.spotifyclone.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifyclone.DataViewModel;
import com.example.spotifyclone.SpacesItemDecoration;
import com.example.spotifyclone.home.HomeContent;
//import com.example.spotifyclone.home.HomeContentAdapter;
import com.example.spotifyclone.home.HomeContentRecyclerViewAdapter;
import com.example.spotifyclone.home.HomePlaylistAdapter;
import com.example.spotifyclone.Playlist;
import com.example.spotifyclone.R;
import com.example.spotifyclone.SingletonDataViewModelFactory;
import com.example.spotifyclone.home.HomePlaylistRecyclerViewAdapter;
import com.example.spotifyclone.search.SearchRecyclerViewAdapter;
import com.example.spotifyclone.ui.library.ViewPlaylistActivity;
import com.google.android.material.transition.MaterialSharedAxis;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<Playlist> getNPlaylists(List<Playlist> playlists, int n) {
        List<Playlist> firstPlaylists = new ArrayList<>();
        int nb = n + 1;
        if (playlists.size() > nb) {
            for (int i = 1; i < nb; i++) {
                firstPlaylists.add(playlists.get(i));
            }
        }
        return firstPlaylists;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        SingletonDataViewModelFactory singletonDataViewModelFactory = new SingletonDataViewModelFactory(DataViewModel.getInstance(getActivity()));
        DataViewModel dataViewModel = new ViewModelProvider(this, singletonDataViewModelFactory).get(DataViewModel.class);

        final List<Playlist> fullPlaylists = dataViewModel.getPlaylistList().getValue();
        final List<HomeContent> homeContents = dataViewModel.getHomeContentList().getValue();

        // YOUR PLAYLISTS GRID //

        if (fullPlaylists != null) {
            final List<Playlist> playlists = getNPlaylists(fullPlaylists, 6);

            RecyclerView recyclerView = root.findViewById(R.id.home_playlist_grid);
            int numberOfColumns = 2;
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
            HomePlaylistRecyclerViewAdapter homePlaylistRecyclerViewAdapter = new HomePlaylistRecyclerViewAdapter(getActivity(), playlists);
            SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(20, 10);
            recyclerView.addItemDecoration(spacesItemDecoration);
            recyclerView.setAdapter(homePlaylistRecyclerViewAdapter);
        }

        // CONTENT LIST //

        if (homeContents.size() != 0) {
            RecyclerView recyclerView = root.findViewById(R.id.home_content_list);

            int numberOfColumns = 1;
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
            HomeContentRecyclerViewAdapter homeContentRecyclerViewAdapter = new HomeContentRecyclerViewAdapter(getActivity(), homeContents);
            SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(30, 0);
            recyclerView.addItemDecoration(spacesItemDecoration);
            recyclerView.setAdapter(homeContentRecyclerViewAdapter);

//            HomeContentAdapter homeContentAdapter = new HomeContentAdapter(getActivity(), homeContents);
//            gridContentView.setAdapter(homeContentAdapter);
        }

        return root;
    }
}


//            final List<Playlist> playlists = getNPlaylists(fullPlaylists, 6);
//
//            HomePlaylistAdapter homePlaylistAdapter = new HomePlaylistAdapter(getActivity(), playlists);
//            playlistGridView.setAdapter(homePlaylistAdapter);
//            playlistGridView.setDrawSelectorOnTop(true);
//
//            playlistGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Playlist playlist = playlists.get(position);
//                    Intent viewPlaylistActivity = new Intent(getContext(), ViewPlaylistActivity.class);
//                    viewPlaylistActivity.putExtra("coverURL", playlist.getImageURL());
//                    viewPlaylistActivity.putExtra("author", playlist.getAuthor());
//                    viewPlaylistActivity.putExtra("name", playlist.getName());
//                    startActivity(viewPlaylistActivity);
//                }
//            });