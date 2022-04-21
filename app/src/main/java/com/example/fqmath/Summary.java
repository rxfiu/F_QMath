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


public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        /* Array con i dati dell'activity Quiz */
        Bundle data = getIntent().getExtras();

        /* Dati dentro l'array data */
        int numberCorrect = data.getInt("numberCorrect");
        int numberIncorrect = data.getInt("numberIncorrect");
        int score = data.getInt("score");

        /* TextView */
        TextView txt_correct = (TextView) findViewById(R.id.txt_correct);
        TextView txt_incorrect = (TextView) findViewById(R.id.txt_incorrect);
        TextView txt_points = (TextView) findViewById(R.id.txt_points);
        txt_correct.setText(Integer.toString(numberCorrect));
        txt_incorrect.setText(Integer.toString(numberIncorrect));
        txt_points.setText(Integer.toString(score));

        /* Bottone torna al menù principale */
        Button menù;

        menù = findViewById(R.id.menù);
        menù.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(Summary.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}