package com.example.assignmenttwostudentapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.assignmenttwostudentapp.model.Model;
import com.example.assignmenttwostudentapp.model.Student;

import java.util.List;

public class StudentDetailsActivity extends AppCompatActivity {

    List<Student> data;
    Intent intent1, intent2;
    TextView name, id, phone, address;
    CheckBox cb;
    int pos;
    Student st;
    Button edit, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        data = Model.instance().getAllStudents();
        intent1 = getIntent();
        pos = (int) intent1.getSerializableExtra("pos");
        st = data.get(pos);

        name = findViewById(R.id.studentdetails_name_tv);
        id = findViewById(R.id.studentdetails_id_tv);
        phone = findViewById(R.id.studentdetails_phone_tv);
        address = findViewById(R.id.studentdetails_address_tv);
        cb = findViewById(R.id.studentdetails_cb);

        edit = findViewById(R.id.studentdetails_edit_btn);

        this.bind(st, pos);

        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String result1 = data.getStringExtra("result");
                        if(result1.equals("delete"))
                            finish();
                    }
                });

        edit.setOnClickListener(view -> {
            intent2 = new Intent(this, EditStudentActivity.class);
            intent2.putExtra("pos", pos);
            someActivityResultLauncher.launch(intent2);
        });
        cancel = findViewById(R.id.studentdetails_cancel_btn);
        cancel.setOnClickListener(view -> finish());
    }

    public void bind(Student st, int pos) {
        name.setText(st.name);
        id.setText(st.id);
        phone.setText(st.phone);
        address.setText(st.address);
        cb.setChecked(st.cb);
    }

    @Override
    public void onStart() {
        super.onStart();
        bind(st, pos);
    }

}