package com.example.spotifyclone.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifyclone.R;
import com.example.spotifyclone.search.SearchRecyclerViewAdapter;
import com.example.spotifyclone.SpacesItemDecoration;

public class SearchFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        String[] cards = {"Podcast", "Conçu spécialement pour vous", "Classements", "Sorties", "Radio", "Découvrir", "Concerts", "Ambiance", "Electro", "Rock", "Latino", "Été", "Chill", "RnB", "Soirée", "Sport", "Décennies", "Dormir", "Étudiants", "Afro", "À la maison", "Reggae/Dancehall", "Classique", "Jazz", "Soul", "Metal", "K-pop", "Romance", "League of Legends", "Focus", "Tendances", "Bien-être", "Funk", "Gaming", "Folk et acoustique", "Country", "influenceurs", "Arabe", "Blues", "Voyage", "Musique brésilienne", "Jeunesse", "Punk", "Musique Indienne", "Pride", "Higher Ground"};

        RecyclerView recyclerView = root.findViewById(R.id.search_recyclerView);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        SearchRecyclerViewAdapter searchRecyclerViewAdapter = new SearchRecyclerViewAdapter(getActivity(), cards);
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(45, 25);
        recyclerView.addItemDecoration(spacesItemDecoration);
        recyclerView.setAdapter(searchRecyclerViewAdapter);
//
//        final GridView searchGridView = root.findViewById(R.id.search_grid);
//        SearchGridAdapter searchGridAdapter = new SearchGridAdapter(getActivity(), cards);
//        searchGridView.setDrawSelectorOnTop(true);
//        searchGridView.setAdapter(searchGridAdapter);
        return root;
    }
}