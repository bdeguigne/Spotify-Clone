package com.example.spotifyclone.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifyclone.HorizontalGridAdapter;
import com.example.spotifyclone.Playlist;
import com.example.spotifyclone.R;
import com.example.spotifyclone.SpacesItemDecoration;
import com.example.spotifyclone.ui.library.ViewPlaylistActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeContentRecyclerViewAdapter extends RecyclerView.Adapter<HomeContentRecyclerViewAdapter.ViewHolder> {

    private List<HomeContent> contents;
    private LayoutInflater mInflater;
    private Context mContext;


    public HomeContentRecyclerViewAdapter(Context context, List<HomeContent> contents) {
        this.mInflater = LayoutInflater.from(context);
        this.contents = contents;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.home_content_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeContent content = contents.get(position);

        List<HomeAlbum> homeAlbums = content.getContent();

        holder.title.setText(content.getTitle());

        int numberOfColumns = homeAlbums.size();
        holder.recyclerView.setLayoutManager(new GridLayoutManager(mContext, numberOfColumns));
        holder.linearLayout.getLayoutParams().width =  ((int) mContext.getResources().getDimension(R.dimen.imageview_width)) * homeAlbums.size();
        HomeHorizontalGridRecyclerViewAdapter homeHorizontalGridRecyclerViewAdapter = new HomeHorizontalGridRecyclerViewAdapter(mContext, homeAlbums);
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(0, 20);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.addItemDecoration(spacesItemDecoration);
        holder.recyclerView.setAdapter(homeHorizontalGridRecyclerViewAdapter);
//        HorizontalGridAdapter horizontalGridAdapter = new HorizontalGridAdapter(mContext, homeAlbums);
//        holder.gridView.setNumColumns(homeAlbums.size());
//        holder.gridView.setAdapter(horizontalGridAdapter);
//        holder.gridView.setDrawSelectorOnTop(true);
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        LinearLayout linearLayout;
        RecyclerView recyclerView;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.home_content_text);
            linearLayout = itemView.findViewById(R.id.home_content_layout);
            recyclerView = itemView.findViewById(R.id.home_content_grid);
        }

    }

}
