package sergway.mycompany.connect3game;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    Color[] gameState = new Color[9];
    Color activePlayer = Color.YELLOW;

    boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Arrays.fill(gameState, Color.EMPTY);
    }

    public void playAgain(View view) {

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            child.setImageDrawable(null);
            gameState[i] = Color.EMPTY;
        }

        gameActive = true;
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int numPosition = Integer.parseInt(counter.getTag().toString());

        if ((gameState[numPosition] == Color.EMPTY) && gameActive) {
            counter.setTranslationY(-1500);

            if (activePlayer == Color.YELLOW) {
                counter.setImageResource(R.drawable.red);
                gameState[numPosition] = Color.RED;
                activePlayer = Color.RED;
            } else {
                counter.setImageResource(R.drawable.yellow);
                gameState[numPosition] = Color.YELLOW;
                activePlayer = Color.YELLOW;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[2]] != Color.EMPTY) {
                    gameActive = false;
                    Color winner = gameState[winningPosition[1]];
                    Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner.toString().toLowerCase() + " has won!");

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }


    }

    enum Color {YELLOW, RED, EMPTY}
}