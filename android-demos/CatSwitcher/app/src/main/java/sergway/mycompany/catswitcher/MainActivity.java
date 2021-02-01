package sergway.mycompany.catswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        ImageView image = (ImageView) findViewById(R.id.image);

        if( image.getTag() == null) {
            image.setTag(R.drawable.cat1);
        }


        if ((int) image.getTag() == R.drawable.cat1) {
            image.setImageResource(R.drawable.cat2);
            image.setTag(R.drawable.cat2);
        } else {
            image.setImageResource(R.drawable.cat1);
            image.setTag(R.drawable.cat1);
        }
//        if(image.getId())
//        image.setImageResource(R.drawable.cat2);
    }
}