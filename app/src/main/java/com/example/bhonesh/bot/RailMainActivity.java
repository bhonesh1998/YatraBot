package com.example.bhonesh.bot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class RailMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rail_main);
        Thread background = new Thread() {
            public void run() {

                try {

                    sleep(2000);


                    Intent intent = new Intent(RailMainActivity.this, RailDisplay.class);
                    startActivity(intent);

                    finish();

                } catch (Exception e) {

                }
            }
        };


        background.start();
    }
}
