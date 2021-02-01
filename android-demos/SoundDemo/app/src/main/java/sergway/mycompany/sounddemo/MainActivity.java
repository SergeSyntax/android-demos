package sergway.mycompany.sounddemo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.marbles);
    }

    public void onPlay(View view) {
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
    }

    public void onPause(View view) {
        mediaPlayer.pause();
        Log.i("MediaPlayer", "" + mediaPlayer.isPlaying());
    }
}