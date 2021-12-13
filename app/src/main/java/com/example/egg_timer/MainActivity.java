package com.example.egg_timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
/*Seek bar -> timer
go button to start timer
go button -> find seek bar to get time ->start timer
 */
    TextView timeTextView;
    SeekBar timeSeekBar;
    Button buttonText;
    boolean active = false;
    CountDownTimer countDownTimer;
    public void resetTimer(){
        timeTextView.setText("0:30");
        timeSeekBar.setProgress(30);
        timeSeekBar.setEnabled(true);
        countDownTimer.cancel();
        buttonText.setText("Go!");
        active = false;
    }
    public void startTimerButton(View view){
        if(active){
            resetTimer();
        }else {
            active = true;
            timeSeekBar.setEnabled(false);
            buttonText.setText("Stop!");

            countDownTimer = new CountDownTimer((int)timeSeekBar.getProgress() * 1000, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();
                    resetTimer();
                }
            }.start();
        }
    }
    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);
        String secondString = Integer.toString(seconds);
        if(seconds <= 9){
            secondString = "0" + secondString;
        }
        timeTextView.setText(Integer.toString(minutes)+":"+ secondString);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeSeekBar = findViewById(R.id.seekBar);
        timeTextView = findViewById(R.id.textView);
        buttonText = findViewById(R.id.button);

        int max = (600);
        int startingPosition = 0;
        timeSeekBar.setMax(max);
        timeSeekBar.setProgress(30);

        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}