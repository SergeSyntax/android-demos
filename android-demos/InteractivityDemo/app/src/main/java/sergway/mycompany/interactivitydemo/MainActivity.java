package sergway.mycompany.interactivitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickFunction(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        Log.i("info","it worked!");
        Log.i("values",nameEditText.getText().toString());
    }
}