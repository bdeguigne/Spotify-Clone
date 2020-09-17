package com.example.spotifyclone.ui.library;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.spotifyclone.Artist;
import com.example.spotifyclone.ArtistAdapter;
import com.example.spotifyclone.PlaylistAdapter;
import com.example.spotifyclone.Playlist;
import com.example.spotifyclone.R;
import com.example.spotifyclone.SingletonDataViewModelFactory;
import com.example.spotifyclone.DataViewModel;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private DataViewModel dataViewModel;
    //private LibraryViewModel libraryViewModel;

    private List<Playlist> playlists;
    private List<Artist> artists;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SingletonDataViewModelFactory singletonDataViewModelFactory = new SingletonDataViewModelFactory(DataViewModel.getInstance(getActivity()));
        dataViewModel = new ViewModelProvider(this, singletonDataViewModelFactory).get(DataViewModel.class);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);

        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.section_library, container, false);
        final ListView listview = root.findViewById(R.id.library_listview);

        playlists = dataViewModel.getPlaylistList().getValue();
        artists = dataViewModel.getArtistsList();

        dataViewModel.getPlaylistList().observe(getViewLifecycleOwner(), new Observer<List<Playlist>>() {
            @Override
            public void onChanged(List<Playlist> playlists) {
                if (pageViewModel.getIndex().getValue() != null && pageViewModel.getIndex().getValue() == 1) {
                    PlaylistAdapter listAdapter = new PlaylistAdapter(getContext(), playlists);
                    listview.setAdapter(listAdapter);
                }
            }
        });

        pageViewModel.getIndex().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer i) {
                Log.d("INDEX", String.valueOf(i));
                if (i == 1) {
                    PlaylistAdapter listAdapter = new PlaylistAdapter(getContext(), playlists);
                    listview.setAdapter(listAdapter);
                } else {
                    ArtistAdapter artistAdapter = new ArtistAdapter(getContext(), artists);
                    listview.setAdapter(artistAdapter);
                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                if (pageViewModel.getIndex().getValue() != null && pageViewModel.getIndex().getValue() == 1) {
                    Playlist playlist = playlists.get(position);
                    if (playlist.getIsCreatePlaylist()) {
                        Intent createPlaylistActivity = new Intent(getContext(), CreatePlaylistActivity.class);
                        startActivity(createPlaylistActivity);
                    } else {
                        Intent viewPlaylistActivity = new Intent(getContext(), ViewPlaylistActivity.class);
                        viewPlaylistActivity.putExtra("coverURL", playlist.getImageURL());
                        viewPlaylistActivity.putExtra("author", playlist.getAuthor());
                        viewPlaylistActivity.putExtra("name", playlist.getName());

                        startActivity(viewPlaylistActivity);
                    }
                }
            }
        });

        return root;
    }
}