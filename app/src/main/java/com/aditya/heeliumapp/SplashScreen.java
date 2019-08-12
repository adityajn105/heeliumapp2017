package com.aditya.heeliumapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity {
    @BindView(R.id.splash)
    ImageView splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        Runnable runnable = new Runnable(){
            public void run() {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        };
        new Handler().postDelayed(runnable, 1500);
    }
}