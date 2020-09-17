package com.example.spotifyclone.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifyclone.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeHorizontalGridRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder > {

    private static int TYPE_ALBUM = 1;
    private static int TYPE_ARTIST = 2;

    private List<HomeAlbum> albums;
    private LayoutInflater mInflater;
    private Context mContext;

    public HomeHorizontalGridRecyclerViewAdapter(Context context, List<HomeAlbum> albums) {
        this.mInflater = LayoutInflater.from(context);
        this.albums = albums;
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (albums.get(position).getIsArtist()) {
            return TYPE_ARTIST;
        } else {
            return TYPE_ALBUM;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_ARTIST) {
            view = mInflater.inflate(R.layout.linearlayout_artist, parent, false);
            return new ArtistViewHolder(view);
        } else {
            view = mInflater.inflate(R.layout.linearlayout_album, parent, false);
            return new AlbumViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder  holder, int position) {
        HomeAlbum album = albums.get(position);

        if (getItemViewType(position) == TYPE_ARTIST) {
            ((ArtistViewHolder) holder).nameTextView.setText(album.getName());
            Picasso.get().load(album.getImageURL()).into(((ArtistViewHolder) holder).albumCover);
        } else {
            ((AlbumViewHolder) holder).nameTextView.setText(album.getName());
            Picasso.get().load(album.getImageURL()).into(((AlbumViewHolder) holder).albumCover);
        }
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView albumCover;

        AlbumViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textview_albumName);
            albumCover = itemView.findViewById(R.id.imageview_albumCover);
        }

    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView albumCover;

        ArtistViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textview_artistName);
            albumCover = itemView.findViewById(R.id.imageview_artistImage );
        }
    }
}
