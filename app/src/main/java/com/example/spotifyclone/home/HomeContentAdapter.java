package com.example.spotifyclone.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.spotifyclone.HorizontalGridAdapter;
import com.example.spotifyclone.R;

import java.util.List;

public class HomeContentAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<HomeContent> contents;

    // 1
    public HomeContentAdapter(Context context, List<HomeContent> contents) {
        this.mContext = context;
        this.contents = contents;
    }

    // 2
    @Override
    public int getCount() {
        return contents.size();
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
        final HomeContent content = contents.get(position);

        final ViewHolder viewHolder;
        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        List<HomeAlbum> homeAlbums = content.getContent();

        if (convertView == null) {
            Log.d("TEST", String.valueOf(position));

            convertView = layoutInflater.inflate(R.layout.home_content_adapter, null);

            TextView contentTitle = convertView.findViewById(R.id.home_content_text);
            contentTitle.setText(content.getTitle());

            // Horizontal Grid View //

            LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.home_content_layout);
            GridView gridView = (GridView) convertView.findViewById(R.id.home_content_grid);

            linearLayout.getLayoutParams().width = ((int) mContext.getResources().getDimension(R.dimen.imageview_width)) * homeAlbums.size();
            HorizontalGridAdapter horizontalGridAdapter = new HorizontalGridAdapter(mContext, homeAlbums);
            gridView.setNumColumns(homeAlbums.size());
            gridView.setAdapter(horizontalGridAdapter);
            gridView.setDrawSelectorOnTop(true);

            viewHolder = new ViewHolder(contentTitle, linearLayout, gridView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            Log.d("TEST", content.getTitle());
            viewHolder.title.setText(content.getTitle());
            viewHolder.linearLayout.getLayoutParams().width = ((int) mContext.getResources().getDimension(R.dimen.imageview_width)) * homeAlbums.size();
            HorizontalGridAdapter horizontalGridAdapter = new HorizontalGridAdapter(mContext, homeAlbums);
            viewHolder.gridView.setNumColumns(homeAlbums.size());
            viewHolder.gridView.setAdapter(horizontalGridAdapter);
            viewHolder.gridView.setDrawSelectorOnTop(true);
        }
        return convertView;
    }

    // Your "view holder" that holds references to each subview
    private static class ViewHolder {
        private final TextView title;
        private final LinearLayout linearLayout;
        private final GridView gridView;

        public ViewHolder(TextView title, LinearLayout linearLayout, GridView gridView) {
            this.title = title;
            this.linearLayout = linearLayout;
            this.gridView = gridView;
        }
    }
}

