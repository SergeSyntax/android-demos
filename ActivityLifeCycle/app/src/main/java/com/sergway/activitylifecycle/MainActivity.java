package com.sergway.activitylifecycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 2;
    private Button showGuess;
    private EditText enterGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showGuess = findViewById(R.id.buttonGuess);
        enterGuess = findViewById(R.id.guessField);
        showGuess.setOnClickListener(v -> {
            String guess = enterGuess.getText().toString().trim();
            if (!guess.isEmpty()) {
                Intent intent = new Intent(this, ShowGuess.class);
                intent.putExtra("guess", guess);
                intent.putExtra("name", "bond");
                intent.putExtra("age", 34);
//                startActivity(intent);

                startActivityForResult(intent, REQUEST_CODE);
            } else
                Toast.makeText(this, "Enter guess", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            String message = data.getStringExtra("message_back");
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        alertProcess("onStart");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        alertProcess("onResume");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        alertProcess("onPause");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        alertProcess("onStop");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        alertProcess("onDestroy");
//    }

//    private void alertProcess(String processName) {
//        Toast.makeText(MainActivity.this, processName + " called", Toast.LENGTH_SHORT).show();
//        Log.d("Cycle", processName);
//    }
}