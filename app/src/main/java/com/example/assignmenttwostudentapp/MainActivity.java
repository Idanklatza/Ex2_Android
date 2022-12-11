package com.example.assignmenttwostudentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.main_add_student_btn);

        btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddStudentActivity.class);
            startActivity(intent);
        });
    }
}