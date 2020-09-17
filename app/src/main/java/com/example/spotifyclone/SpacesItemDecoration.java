package com.example.spotifyclone;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int verticalSpace;
    private int horizontalSpace;

    public SpacesItemDecoration(int verticalSpace, int horizontalSpace) {
        this.verticalSpace = verticalSpace;
        this.horizontalSpace = horizontalSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = horizontalSpace;
        outRect.right = horizontalSpace;
        outRect.bottom = verticalSpace;

        // Add top margin only for the first item to avoid double space between items
//        if(parent.getChildAdapterPosition(view) == 0) {
//            outRect.top = space;
//        }
    }
}