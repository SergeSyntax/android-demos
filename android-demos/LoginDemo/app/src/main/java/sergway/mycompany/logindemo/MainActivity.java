package sergway.mycompany.logindemo;

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

    public void onClick(View view) {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);

        Log.i("Credentials", "username: "
                + username.getText().toString() + "\n"
                + "password: " + password.getText().toString()
        );

        Toast.makeText(this, "Hi there", Toast.LENGTH_SHORT).show();

    }
}