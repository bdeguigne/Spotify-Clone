package com.example.spotifyclone.ui.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.spotifyclone.R;
import com.example.spotifyclone.SingletonDataViewModelFactory;
import com.example.spotifyclone.DataViewModel;

public class CreatePlaylistActivity extends AppCompatActivity {

    private DataViewModel dataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_playlist);

        SingletonDataViewModelFactory singletonDataViewModelFactory = new SingletonDataViewModelFactory(DataViewModel.getInstance(this));
        dataViewModel = new ViewModelProvider(this, singletonDataViewModelFactory).get(DataViewModel.class);

        Button cancelButton = findViewById(R.id.activity_createPlaylist_cancel);

        final Button createButton = findViewById(R.id.activity_createPlaylist_create);
        createButton.setEnabled(false);
        createButton.setTextColor(ContextCompat.getColor(this, R.color.inactive_tab_color));

        final EditText createPlaylistInput = findViewById(R.id.activity_createPlaylist_input);
        createPlaylistInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    createButton.setEnabled(true);
                    createButton.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                } else {
                    createButton.setEnabled(false);
                    createButton.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.inactive_tab_color));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataViewModel.addPlaylist(createPlaylistInput.getText().toString());
                finish();
            }
        });
    }
}