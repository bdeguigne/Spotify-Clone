package com.example.spotifyclone.home;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifyclone.Playlist;
import com.example.spotifyclone.R;
import com.example.spotifyclone.ui.library.ViewPlaylistActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomePlaylistRecyclerViewAdapter extends RecyclerView.Adapter<HomePlaylistRecyclerViewAdapter.ViewHolder> {

    private List<Playlist> mPlaylists;
    private LayoutInflater mInflater;
    private Context mContext;


    public HomePlaylistRecyclerViewAdapter(Context context, List<Playlist> playlists) {
        this.mInflater = LayoutInflater.from(context);
        this.mPlaylists = playlists;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.home_playlist_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = mPlaylists.get(position);

        holder.cardTextView.setText(playlist.getName());
        if (playlist.getImageURL() != null) {
            Picasso.get().load(playlist.getImageURL()).into(holder.playlistImageView);
        } else {
            holder.playlistImageView.setImageResource(R.drawable.playlist_image);
        }


    }

    @Override
    public int getItemCount() {
        return mPlaylists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cardTextView;
        ImageView playlistImageView;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            cardTextView = itemView.findViewById(R.id.home_playlist_text);
            playlistImageView = itemView.findViewById(R.id.home_playlist_image);
            cardView = itemView.findViewById(R.id.home_playlist_card);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClick(view, getAdapterPosition());
        }
    }

    Playlist getItem(int id) {
        return mPlaylists.get(id);
    }

    // allows clicks events to be caught
    public void onItemClick(View view, int position) {
        Log.i("TAG", "You clicked number " + getItem(position).toString() + ", which is at cell position " + position);

        Playlist playlist = mPlaylists.get(position);
        Intent viewPlaylistActivity = new Intent(mContext, ViewPlaylistActivity.class);
        viewPlaylistActivity.putExtra("coverURL", playlist.getImageURL());
        viewPlaylistActivity.putExtra("author", playlist.getAuthor());
        viewPlaylistActivity.putExtra("name", playlist.getName());
        mContext.startActivity(viewPlaylistActivity);
    }
}
