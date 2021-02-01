package com.sergway.sprefs;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sergway.sprefs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String MESSAGE_ID = "messages_prefs";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = BindingUtill
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        binding.button.setOnClickListener(v -> {
            String message = binding.editTextName.getText().toString().trim();

            SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("message", message);

            editor.apply(); // saving to disk!
        });


        SharedPreferences getSharedData = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
        String value = getSharedData.getString("message", "Nothing yet");

        binding.showMessageTextview.setText(value);
    }

}