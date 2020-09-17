package com.example.spotifyclone;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Playlist> playlists;

    // 1
    public PlaylistAdapter(Context context, List<Playlist> playlists) {
        this.mContext = context;
        this.playlists = playlists;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
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
        final String authorName = "par " + playlist.getAuthor();
        final int type = getItemViewType(position);
        final ViewHolder viewHolder;
        final ViewHolderCreate viewHolderCreate;
        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        if (type == 0) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.library_createplaylist_view, null);
                TextView createPlaylistNameTextView = convertView.findViewById(R.id.createPlaylistName);
                ImageView createPlaylistCoverImageView = convertView.findViewById(R.id.createPlaylistCover);
                viewHolderCreate = new ViewHolderCreate(createPlaylistNameTextView, createPlaylistCoverImageView);
                convertView.setTag(viewHolderCreate);
            } else {
                viewHolderCreate = (ViewHolderCreate) convertView.getTag();
            }
        } else {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.library_listview, null);

                TextView playlistNameTextView = convertView.findViewById(R.id.playlistName);
                playlistNameTextView.setText(playlist.getName());

                TextView authorNameTextView = convertView.findViewById(R.id.playlistAuthor);
                authorNameTextView.setText(authorName);

                ImageView playlistCoverImageView = convertView.findViewById(R.id.playlistCover);
                if (playlist.getImageURL() != null) {
                    Picasso.get().load(playlist.getImageURL()).into(playlistCoverImageView);
                }
                else {
                    playlistCoverImageView.setImageResource(R.drawable.playlist_image);
                }

                viewHolder = new ViewHolder(playlistNameTextView, authorNameTextView, playlistCoverImageView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                if (playlist.getImageURL() != null) {
                    Picasso.get().load(playlist.getImageURL()).into(viewHolder.playlistCover);
                }
                else {
                    viewHolder.playlistCover.setImageResource(R.drawable.playlist_image);
                }
                viewHolder.playlistName.setText(playlist.getName());
                viewHolder.playlistAuthor.setText(authorName);
            }

        }
        return convertView;
    }

    // Your "view holder" that holds references to each subview
    private static class ViewHolder {
        private final TextView playlistName;
        private final ImageView playlistCover;
        private final TextView playlistAuthor;

        public ViewHolder(TextView playlistName, TextView playlistAuthor, ImageView playlistCover) {
            this.playlistName = playlistName;
            this.playlistCover = playlistCover;
            this.playlistAuthor = playlistAuthor;
        }
    }

    protected static  class ViewHolderCreate {
        private final TextView playlistName;
        private final ImageView playlistCover;

        public ViewHolderCreate(TextView playlistName, ImageView playlistCover) {
            this.playlistName = playlistName;
            this.playlistCover = playlistCover;
        }
    }

}

