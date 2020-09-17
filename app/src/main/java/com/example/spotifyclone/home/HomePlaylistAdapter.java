package com.example.spotifyclone.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spotifyclone.Playlist;
import com.example.spotifyclone.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomePlaylistAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Playlist> playlists;

    // 1
    public HomePlaylistAdapter(Context context, List<Playlist> playlists) {
        this.mContext = context;
        this.playlists = playlists;
    }

    // 2
    @Override
    public int getCount() {
        return playlists.size();
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Playlist playlist = playlists.get(position);
        final ViewHolder viewHolder;
        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.home_playlist_adapter, null);

            TextView playlistName = convertView.findViewById(R.id.home_playlist_text);
            playlistName.setText(playlist.getName());

            ImageView playlistImage = convertView.findViewById(R.id.home_playlist_image);
            if (playlist.getImageURL() != null) {
                Picasso.get().load(playlist.getImageURL()).into(playlistImage);
            } else {
                playlistImage.setImageResource(R.drawable.playlist_image);
            }

            viewHolder = new ViewHolder(playlistName, playlistImage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            if (playlist.getImageURL() != null) {
                Picasso.get().load(playlist.getImageURL()).into(viewHolder.playlistImage);
            } else {
                viewHolder.playlistImage.setImageResource(R.drawable.playlist_image);
            }
            viewHolder.playlistName.setText(playlist.getName());
        }
        return convertView;
    }

    // Your "view holder" that holds references to each subview
    private static class ViewHolder {
        private final TextView playlistName;
        private final ImageView playlistImage;

        public ViewHolder(TextView playlistName, ImageView playlistImage) {
            this.playlistName = playlistName;
            this.playlistImage = playlistImage;
        }
    }
}

