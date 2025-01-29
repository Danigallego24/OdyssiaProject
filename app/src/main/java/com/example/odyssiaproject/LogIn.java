package com.example.odyssiaproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText userField = findViewById(R.id.userField);
        EditText passField = findViewById(R.id.passField);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        Button buttonRecover = findViewById(R.id.buttonRecover);
        ImageButton buttonNext = findViewById(R.id.buttonNext);

        buttonNext.setOnClickListener(v -> {

            Intent intent = new Intent(LogIn.this,MainActivity.class);
            startActivity(intent);
            finish();
        });



    }
}