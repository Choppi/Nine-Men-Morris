package com.example.alexis.ninemensmorris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button reset;
    private CustomView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_turn = (TextView) findViewById(R.id.tv_turn);
        TextView tv_score = (TextView) findViewById(R.id.tv_score);
        cv = (CustomView) findViewById(R.id.cv);
        reset = (Button) findViewById(R.id.reset);

        cv.setTv(tv_turn, tv_score);
        cv.UpdateScore();
        cv.ChangeText();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv.reset();
            }
        });
    }

}
