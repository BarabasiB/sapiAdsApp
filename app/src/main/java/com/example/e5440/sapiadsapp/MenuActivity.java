package com.example.e5440.sapiadsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    public void randomHero(View view){
        Intent intent = new Intent(getApplicationContext(),RandomHero_Activity.class);
        startActivity(intent);
    }

    public void listOfHeroes(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
}
