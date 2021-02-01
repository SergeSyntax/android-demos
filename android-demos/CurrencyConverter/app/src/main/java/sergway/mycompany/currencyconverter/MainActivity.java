package sergway.mycompany.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        EditText editText = (EditText) findViewById(R.id.amount);
        String amountInPounds = editText.getText().toString();
        double amountInDollars = Double.parseDouble(amountInPounds) * 1.3;
        String amountInDollarsText = String.format("%.2f", amountInDollars);

        Toast.makeText(this, "In dollars: " + amountInDollarsText, Toast.LENGTH_LONG).show();
    }
}