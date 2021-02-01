package sergway.mycompany.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ImageView> images = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        images.add(findViewById(R.id.bartImageView));
        images.add(findViewById(R.id.homerImageView));
    }

    public void toggleImageAlpha() {
        for (ImageView image : images) {
            float alpha = image.getAlpha();
            if(!(alpha == 0) && !(alpha == 1)) return;
            image.animate().alpha(image.getAlpha() == 1 ? 0 : 1).setDuration(2000);
        }
    }

    public void fade(View view) {
        toggleImageAlpha();
    }
}