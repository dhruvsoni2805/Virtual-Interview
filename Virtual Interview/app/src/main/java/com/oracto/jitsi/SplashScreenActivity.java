package com.oracto.jitsi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    ProgressBar loading;
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        prog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),SigninActivity.class);
                startActivity(i);
                finish();
            }
        },1500);
    }
    public void prog()
    {
        loading = (ProgressBar) findViewById(R.id.loading);
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter++;
                loading.setProgress(counter);
                if (counter == 100);
            }
        };
        t.schedule(tt,0,20);
    }
}