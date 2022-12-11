package com.example.assignmenttwostudentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assignmenttwostudentapp.model.Model;
import com.example.assignmenttwostudentapp.model.Student;

import java.util.List;

public class AddStudentActivity extends AppCompatActivity {

    List<Student> data;
    EditText name, id, phone, address;
    CheckBox cb;
    Student st;
    Button save, cancel;
//  TextView messageTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        data = Model.instance().getAllStudents();

        name = findViewById(R.id.addstudent_name_et);
        id = findViewById(R.id.addstudent_id_et);
        phone = findViewById(R.id.addstudent_phone_et);
        address = findViewById(R.id.addstudent_address_et);
        cb = findViewById(R.id.addstudent_cb);

        save = findViewById(R.id.addstudent_save_btn);

        save.setOnClickListener( view -> {
            st = new Student(name.getText().toString(), id.getText().toString(), phone.getText().toString(), address.getText().toString(), "", cb.isChecked());
            data.add(st);
            finish();
        });

        cancel = findViewById(R.id.addstudent_cancel_btn);

        cancel.setOnClickListener(view -> finish());

//      messageTv = findViewById(R.id.addstudent_message);

    }
}