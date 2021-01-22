package sergway.mycompany.numbershapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    class Number {
        int number;

        public boolean isTriangular() {
            int x = 1;

            int triangularNumber = 1;

            while (triangularNumber < number) {
                x++;
                triangularNumber += x;
            }

            if (triangularNumber == number)
                return true;
            else
                return false;
        }

        public boolean isSquare() {
            double squareRoot = Math.sqrt(this.number);
            return squareRoot == Math.floor(squareRoot);
        }
    }

    public void publishResult(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public void onClick(View view){
        EditText editText = (EditText) findViewById(R.id.editTextNumber);

        if(editText.getText().toString().isEmpty()) {
            publishResult("values is required");
            return;
        }
        Number number = new Number();
        number.number = Integer.parseInt(editText.getText().toString());

        if(number.isSquare() && number.isTriangular())
            publishResult("Both");

        else if(number.isSquare())
            publishResult("number is square");

        else if(number.isTriangular())
            publishResult("number is triangular");
        else
            publishResult("number is not triangular or square");


    }
}