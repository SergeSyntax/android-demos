package sergway.mycompany.higherorlower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRandomNumber();
        setContentView(R.layout.activity_main);
    }

    private void publishResult(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void setRandomNumber() {
        Random random = new Random();
        this.number = random.nextInt(20) + 1;
    }

    public void onClick(View view) {
        EditText editText = (EditText) findViewById(R.id.number);
        int guessedNumber = Integer.parseInt(editText.getText().toString());
        Log.i("Random number", Integer.toString(number));

        if (this.number == guessedNumber) {
            this.setRandomNumber();
            this.publishResult("You got it! Try again!");
        } else if (this.number > guessedNumber) {
            this.publishResult("higher!");
        } else
            this.publishResult("lower!");
    }
}