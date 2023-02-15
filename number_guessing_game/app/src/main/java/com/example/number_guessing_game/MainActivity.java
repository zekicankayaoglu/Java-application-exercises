package com.example.number_guessing_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView remaining,noti;
    private EditText guess;
    private String number;
    private Button button;
    private int remain = 3,randomNumber;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        remaining = (TextView)findViewById(R.id.txt_remaining);
        noti = (TextView)findViewById(R.id.txt_noti);
        guess = (EditText)findViewById(R.id.txt_guess);
        button = (Button)findViewById(R.id.guess_button);
        random = new Random();
        randomNumber = random.nextInt(11);
        noti.setText("-Welcome to number guessing game-");
    }

    public void guess_click(View v){
        number = guess.getText().toString();
        if(!TextUtils.isEmpty(number)) {
            if (remain > 0) {
                if (number.equals(String.valueOf(randomNumber))) {
                    noti.setText("Congratulations you found the number!!!");
                    guess.setEnabled(false);
                    button.setText("PLAY AGAIN");
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            restartGame();
                        }
                    });
                } else {
                    noti.setText("Please try again :(");
                    remain--;
                }
                remaining.setText("Remain: " + remain);
            }
            if (remain == 0) {
                noti.setText("You lost :( Number was: " + randomNumber);
                guess.setEnabled(false);
                button.setText("TRY AGAIN");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        restartGame();
                    }
                });
            }

            if(Integer.valueOf(number) < 0 || Integer.valueOf(number) > 10){
                noti.setText("Your guess can be between 0 - 10 !!");
            }
        }

        else{
            noti.setText("Please enter number!");
        }
        guess.setText("");
    }

    public void restartGame(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}