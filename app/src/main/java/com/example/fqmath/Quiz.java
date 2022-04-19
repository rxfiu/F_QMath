package com.example.fqmath;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.*;
import android.os.Handler;
import android.os.Bundle;

public class Quiz extends AppCompatActivity {

    Button button_answer0, button_answer1, button_answer2, button_answer3;
    TextView txt_score, txt_questions, txt_timer, txt_msg;
    ProgressBar progressBar_timer;
    Game game;
    int secondsRemaning = 60;
    int tmp_seconds = secondsRemaning;
    int counter = 0;

    /* Quando si carica l'activity */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        /* Find ID */
        button_answer0 = findViewById(R.id.button_answer0);
        button_answer1 = findViewById(R.id.button_answer1);
        button_answer2 = findViewById(R.id.button_answer2);
        button_answer3 = findViewById(R.id.button_answer3);
        txt_score = findViewById(R.id.txt_score);
        txt_questions = findViewById(R.id.txt_questions);
        txt_timer = findViewById(R.id.txt_timer);
        txt_msg = findViewById(R.id.txt_msg);
        progressBar_timer =  findViewById(R.id.progressBar_timer);
        txt_score.setText("0");

        game = new Game();
        nextTurn(); //game.nextTurn()?
        timer.start();

        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;
                String answerSelected = buttonClicked.getText().toString();
                game.checkAnswer((answerSelected));
                txt_score.setText(Integer.toString(game.getScore()));
                nextTurn();
            }
        };

        button_answer0.setOnClickListener(answerButtonClickListener);
        button_answer1.setOnClickListener(answerButtonClickListener);
        button_answer2.setOnClickListener(answerButtonClickListener);
        button_answer3.setOnClickListener(answerButtonClickListener);
    }

    /* Timer */
    CountDownTimer timer = new CountDownTimer(secondsRemaning*1000, 1000) { //per esempio 30000 sono 30 secondi disponibili (tempo totale) e 1000 è un 1 secondo (ogni tick)
        @Override
        public void onTick(long millisUntilFinished) {
            secondsRemaning--;
            txt_timer.setText(Integer.toString(secondsRemaning) + " sec");
            progressBar_timer.setProgress(tmp_seconds - secondsRemaning);
        }

        @Override
        public void onFinish() {
            SetButtons(false);
            txt_msg.setText("Time is up! " + game.getNumberCorrect() + "/" + (game.getTotalQuestions() - 1));
            OpenActivity();
        }
    };

    private void nextTurn()
    {
        int temp = 0;
        if(counter < 2)
        {
            temp = 0;
        }
        else if(counter < 4)
        {
            temp = 1;
        }
        else if(counter < 6)
        {
            temp = 2;
        }
        else if(counter < 8)
        {
            temp = 3;
        }
        else
        {
            SetButtons(false);
            txt_msg.setText("Fine! " + game.getNumberCorrect() + "/" + game.getTotalQuestions());
            timer.cancel();
            OpenActivity();
            return;
        }

        game.makeNewQuestion(temp);

        counter++;

        String [] answer = game.getCurrentQuestion().getAnswerArray();

        button_answer0.setText(answer[0]);
        button_answer1.setText(answer[1]);
        button_answer2.setText(answer[2]);
        button_answer3.setText(answer[3]);

        SetButtons(true);
        txt_questions.setText(game.getCurrentQuestion().getQuestion());
        txt_msg.setText(game.getNumberCorrect() + "/" + (game.getTotalQuestions() - 1));
    }

    /* Disattiva o riattiva i bottoni */
    public void SetButtons(boolean bool)
    {
        button_answer0.setEnabled(bool);
        button_answer1.setEnabled(bool);
        button_answer2.setEnabled(bool);
        button_answer3.setEnabled(bool);
    }

    public void OpenActivity(){
        //Aspetta 2 secondi prima di mostrare il riepilogo
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(Quiz.this, Summary.class);
                intent.putExtra("numberCorrect", game.getNumberCorrect());
                intent.putExtra("numberIncorrect", game.getNumberIncorrect());
                intent.putExtra("score", game.getScore());
                startActivity(intent);
            }
        }, 2000);
    }
}