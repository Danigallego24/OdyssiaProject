package com.example.odyssiaproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoadingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);

       findViewById(android.R.id.content).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingPage.this, LogIn.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}