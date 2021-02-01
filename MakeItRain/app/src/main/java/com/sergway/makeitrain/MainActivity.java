package com.sergway.makeitrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    //    private Button makeItRain;
    private TextView moneyValue;
    private TextView showInfo;
    private int moneyCounter = 0;
    private TextView textViewCongrats;
    private Button newButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_layout);
//        makeItRain = findViewById(R.id.buttonMakeItRain);
        moneyValue = findViewById(R.id.moneyValue);
        textViewCongrats = findViewById(R.id.textViewCongrats);
        newButton = findViewById(R.id.button);


        newButton.setOnClickListener(V -> Log.d("New button", "onClick: Hola"));
//        makeItRain.setOnClickListener(v -> Log.d("MainActivity", "onClick: make it rain"));
//        moneyValue.setText(R.string.test);
    }

    public void showMoney(View view) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

        moneyCounter += 1000;

        switch (moneyCounter) {
            case 20000:
                moneyValue.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.purple_700));
                break;

            case 30000:
                moneyValue.setTextColor(Color.MAGENTA);
                break;

            case 40000:
                moneyValue.setTextColor(Color.LTGRAY);
                textViewCongrats.setVisibility(View.VISIBLE);
                break;

            default:
                moneyValue.setTextColor(Color.RED);
        }
        moneyValue.setText(String.valueOf(numberFormat.format(moneyCounter)));
//        Log.d("MIR", "onClick: " + moneyCounter);
    }

    public void showInfo(View view) {
//        Toast.makeText(MainActivity.this, R.string.app_info, Toast.LENGTH_SHORT).show();

        Snackbar.make(moneyValue, R.string.app_info, Snackbar.LENGTH_LONG).setAction("More", v ->
                Log.d("Snack", "showInfo: Snackbar More")).show();
    }
}


