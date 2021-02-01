package sergway.mycompany.animationvariety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView barImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barImageView = findViewById(R.id.bartImageView);
        barImageView.setX(-1500);
        barImageView.animate().translationXBy(1500).rotation(3600).setDuration(2000);
    }

    public void fade(View view) {
        ImageView barImageView = findViewById(R.id.bartImageView);
        ImageView homImageView = findViewById(R.id.homerImageView);
//        barImageView.animate().rotation(1800).alpha(0).setDuration(1000);
        barImageView.animate().scaleX(0).scaleY((0)).setDuration(2000);
    }
}