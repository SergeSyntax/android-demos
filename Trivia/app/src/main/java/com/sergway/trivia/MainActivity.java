package com.sergway.trivia;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.sergway.trivia.data.Repository;
import com.sergway.trivia.databinding.ActivityMainBinding;
import com.sergway.trivia.model.Question;
import com.sergway.trivia.model.Score;
import com.sergway.trivia.util.Prefs;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Question> questionList;
    int scoreCounter = 0;
    private Prefs prefs;
    private int currentQuestionIndex;
    private ActivityMainBinding binding;
    private Score score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new Prefs(this);
        currentQuestionIndex = prefs.getState();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        score = new Score();
        binding.scoreText.setText(MessageFormat.format("Current Score: {0}", String.valueOf(score.getScore())));
        binding.highestScore.setText(MessageFormat.format("Highest: {0}", String.valueOf(prefs.getHighestScore())));

        questionList = new Repository().getQuestions(
                questionArrayList -> {
                    binding.questionTextview.setText(questionArrayList.get(currentQuestionIndex).getAnswer());
                    updateCounter(questionArrayList);
                }
        );

        binding.shareButton.setOnClickListener(v -> {
            String message = "My current score is " + score.getScore() + " My highest score is " + " and " + "My highest score is " + prefs.getHighestScore();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "I am playing Trivia");
            intent.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(intent);
        });

        binding.buttonNext.setOnClickListener(v -> {
            getNextQuestion();
            updateQuestion();
        });

        binding.buttonTrue.setOnClickListener(v -> {
            checkAnswer(true);
        });
        binding.buttonFalse.setOnClickListener(v -> {
            checkAnswer(false);
        });

    }

    private void getNextQuestion() {
        currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
    }

    private void checkAnswer(boolean userChoseCorrect) {
        boolean answer = questionList.get(currentQuestionIndex).isAnswerTrue();
        int snackMessageId = 0;
        if (userChoseCorrect == answer) {
            snackMessageId = R.string.correct_answer;
            fadeAnimation();
            addPoints();
        } else {
            snackMessageId = R.string.incorrect_answer;
            shakeAnimation();
            deductPoints();
        }

        Snackbar.make(binding.cardView, snackMessageId, Snackbar.LENGTH_SHORT).show();

    }

    private void fadeAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        binding.cardView.setAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionTextview.setTextColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionTextview.setTextColor(Color.WHITE);
                getNextQuestion();
                updateQuestion();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    private void updateQuestion() {
        String question = questionList.get(currentQuestionIndex).getAnswer();
        binding.questionTextview.setText(question);
        updateCounter((ArrayList<Question>) questionList);
    }

    private void updateCounter(ArrayList<Question> questionArrayList) {
        binding.textViewOutOf.setText(String.format(getString(R.string.text_formatted), currentQuestionIndex, questionArrayList.size()));
    }

    public void shakeAnimation() {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake_animation);
        binding.cardView.setAnimation(shake);

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionTextview.setTextColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionTextview.setTextColor(Color.WHITE);
                getNextQuestion();
                updateQuestion();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void deductPoints() {
        scoreCounter -= 100;

        if (scoreCounter < 0)
            scoreCounter = 0;

        score.setScore(scoreCounter);
        binding.scoreText.setText(MessageFormat.format("Current Score: {0}", score.getScore()));

    }

    private void addPoints() {
        scoreCounter += 100;
        score.setScore(scoreCounter);
        binding.scoreText.setText(String.valueOf(score.getScore()));
        binding.scoreText.setText(MessageFormat.format("Current Score: {0}", String.valueOf(score.getScore())));

    }

    @Override
    protected void onPause() {
        prefs.saveHighestScore(score.getScore());
        prefs.setState(currentQuestionIndex);
        super.onPause();
    }
}