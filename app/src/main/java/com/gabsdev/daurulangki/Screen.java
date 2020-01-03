package com.gabsdev.daurulangki;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ScrollView;

public class Screen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Screen.this, MainActivity.class));
                finish();
            }
        },2000);
    }
}
