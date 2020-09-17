package com.example.spotifyclone.search;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifyclone.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder> {

    private String[] mCards;
    private LayoutInflater mInflater;
    private final List<Integer> colors = new ArrayList<>();

    public SearchRecyclerViewAdapter(Context context, String[] cards) {
        this.mInflater = LayoutInflater.from(context);
        this.mCards = cards;
        String[] colorsTxt = context.getResources().getStringArray(R.array.colors);
        for (String s : colorsTxt) {
            int newColor = Color.parseColor(s);
            colors.add(newColor);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.search_grid_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int rand = new Random().nextInt(colors.size());
        Integer color = colors.get(rand);

        holder.cardTextView.setText(mCards[position]);
        holder.cardView.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    @Override
    public int getItemCount() {
        return mCards.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cardTextView;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            cardTextView = itemView.findViewById(R.id.search_grid_text);
            cardView = itemView.findViewById(R.id.search_card);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
