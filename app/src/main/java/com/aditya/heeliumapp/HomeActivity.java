package com.aditya.heeliumapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        ImageView takeAPhoto=(ImageView)findViewById(R.id.takeaphoto);
        takeAPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDocument(1);
            }
        });

        ImageView chooseAPhoto = findViewById(R.id.find);
        chooseAPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDocument(2);
            }
        });

    }

    public void addDocument(int i) {
        if(i==1){
            startActivity(new Intent(getApplicationContext(), DocumentAdder.class));
        }
        else{
            startActivity(new Intent(getApplicationContext(), DocumentAdder2.class));
        }

    }
}