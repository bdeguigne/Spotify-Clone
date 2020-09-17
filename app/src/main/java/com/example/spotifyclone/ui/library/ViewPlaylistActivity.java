package com.example.spotifyclone.ui.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spotifyclone.R;
import com.squareup.picasso.Picasso;

public class ViewPlaylistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_playlist);

        String coverURL = getIntent().getExtras().getString("coverURL");
        String name = getIntent().getExtras().getString("name");
        String author = getIntent().getExtras().getString("author");

        Button quitButton = findViewById(R.id.view_playlist_quit);
        TextView textViewName = findViewById(R.id.view_playlist_playlist_name);
        TextView textViewAuthor = findViewById(R.id.view_playlist_author_name);
        ImageView imageView = findViewById(R.id.view_playlist_cover);

        if (coverURL == null) {
            imageView.setImageResource(R.drawable.playlist_image);
        } else {
            Picasso.get().load(coverURL).into(imageView);
        }
        textViewName.setText(name);
        textViewAuthor.setText(getString(R.string.author_name, author));

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}