package com.example.spotifyclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtistAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Artist> artists;

    // 1
    public ArtistAdapter(Context context, List<Artist> artists) {
        this.mContext = context;
        this.artists = artists;
    }

    // 2
    @Override
    public int getCount() {
        return artists.size();
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
        final Artist artist = artists.get(position);
        final ViewHolder viewHolder;
        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.library_artist_view, null);

            TextView artistNameTextView = convertView.findViewById(R.id.libraryArtistName);
            artistNameTextView.setText(artist.getName());

            ImageView artistCoverImageView = convertView.findViewById(R.id.libraryArtistCover);
            Picasso.get().load(artist.getImageURL()).into(artistCoverImageView);

            viewHolder = new ViewHolder(artistNameTextView, artistCoverImageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            Picasso.get().load(artist.getImageURL()).into(viewHolder.artistCover);
            viewHolder.artistName.setText(artist.getName());
        }
        return convertView;
    }

    // Your "view holder" that holds references to each subview
    private static class ViewHolder {
        private final TextView artistName;
        private final ImageView artistCover;

        public ViewHolder(TextView artistName, ImageView artistCover) {
            this.artistName = artistName;
            this.artistCover = artistCover;
        }
    }
}

