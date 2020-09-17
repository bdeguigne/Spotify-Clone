//package com.example.spotifyclone;
//
//import android.content.Context;
//import android.content.res.ColorStateList;
//import android.graphics.Color;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.core.content.ContextCompat;
//
//import com.google.android.material.card.MaterialCardView;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class SearchGridAdapter extends BaseAdapter {
//
//    private final Context mContext;
//    private final String[] cards;
//    private final List<Integer> colors = new ArrayList<>();
//
//    // 1
//    public SearchGridAdapter(Context context, String[] cards) {
//        this.mContext = context;
//        this.cards = cards;
//        String[] colorsTxt = context.getResources().getStringArray(R.array.colors);
//
//        for (String s : colorsTxt) {
//            int newColor = Color.parseColor(s);
//            colors.add(newColor);
//        }
//    }
//
//    // 2
//    @Override
//    public int getCount() {
//        return cards.length;
//    }
//
//    // 3
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    // 4
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    // 5
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        final String card = cards[position];
//        final ViewHolder viewHolder;
//        int rand = new Random().nextInt(colors.size());
//        final Integer color = colors.get(rand);
//
//        if (convertView == null) {
//            final TextView nameTextView;
//            final MaterialCardView cardView;
//
//            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
//            convertView = layoutInflater.inflate(R.layout.search_grid_adapter, null);
//            nameTextView = convertView.findViewById(R.id.search_grid_text);
//            cardView = convertView.findViewById(R.id.search_card);
//            nameTextView.setText(card);
//            cardView.setBackgroundTintList(ColorStateList.valueOf(color));
//
//            viewHolder = new ViewHolder(nameTextView, cardView);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//            viewHolder.nameTextView.setText(card);
//            viewHolder.cardView.setBackgroundTintList(ColorStateList.valueOf(color));
//        }
//        return convertView;
//    }
//
//    // Your "view holder" that holds references to each subview
//    private static class ViewHolder {
//        private final TextView nameTextView;
//        private final MaterialCardView cardView;
//
//        public ViewHolder(TextView nameTextView, MaterialCardView cardView) {
//            this.nameTextView = nameTextView;
//            this.cardView = cardView;
//        }
//    }
//
//}
//
//
