package com.example.spotifyclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spotifyclone.home.HomeAlbum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HorizontalGridAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<HomeAlbum> albums;

    // 1
    public HorizontalGridAdapter(Context context,List<HomeAlbum> albums) {
        this.mContext = context;
        this.albums = albums;
    }

    // 2
    @Override
    public int getCount() {
        return albums.size();
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
        final HomeAlbum album = albums.get(position);

        if (convertView == null) {
            final ImageView imageViewAlbumCover;
            final TextView nameTextView;
            final ViewHolder viewHolder;
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            if (!album.getIsArtist()) {
                convertView = layoutInflater.inflate(R.layout.linearlayout_album, null);
                imageViewAlbumCover = (ImageView) convertView.findViewById(R.id.imageview_albumCover);
                nameTextView = (TextView) convertView.findViewById(R.id.textview_albumName);
            } else {
                convertView = layoutInflater.inflate(R.layout.linearlayout_artist, null);
                imageViewAlbumCover = (ImageView) convertView.findViewById(R.id.imageview_artistImage);
                nameTextView = (TextView) convertView.findViewById(R.id.textview_artistName);
            }
            viewHolder = new ViewHolder(nameTextView, imageViewAlbumCover);
            convertView.setTag(viewHolder);
        }
        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        Picasso.get().load(album.getImageURL()).into(viewHolder.imageViewAlbumCover);
        viewHolder.nameTextView.setText(album.getName());
        return convertView;
    }

    // Your "view holder" that holds references to each subview
    private class ViewHolder {
        private final TextView nameTextView;
        private final ImageView imageViewAlbumCover;

        public ViewHolder(TextView nameTextView, ImageView imageViewAlbumCover) {
            this.nameTextView = nameTextView;
            this.imageViewAlbumCover = imageViewAlbumCover;
        }
    }

}

