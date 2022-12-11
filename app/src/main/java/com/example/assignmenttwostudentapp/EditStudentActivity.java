package com.example.assignmenttwostudentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignmenttwostudentapp.model.Model;
import com.example.assignmenttwostudentapp.model.Student;

import java.util.List;

public class EditStudentActivity extends AppCompatActivity {

    List<Student> data;
    EditText name, id, phone, address;
    CheckBox cb;
    Button save, cancel, delete ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        data = Model.instance().getAllStudents();
        Intent intent = getIntent();
        int pos = (int) intent.getSerializableExtra("pos");
        Student st = data.get(pos);

        name = findViewById(R.id.editstudent_name_et);
        id = findViewById(R.id.editstudent_id_et);
        phone = findViewById(R.id.editstudent_phone_et);
        address = findViewById(R.id.editstudent_address_et);
        cb = findViewById(R.id.editstudent_cb);

        this.bind(st,pos);

        save = findViewById(R.id.editstudent_save_btn);

        save.setOnClickListener(view -> {
            this.bindBack(pos);
            Intent result = new Intent();
            result.putExtra("result", "save");
            setResult(RESULT_OK, result);
            finish();
        });

        cancel = findViewById(R.id.editstudent_cancel_btn);

        cancel.setOnClickListener(view ->
        {
            Intent result = new Intent();
            result.putExtra("result", "cancel");
            setResult(RESULT_OK, result);
            finish();
        });

        delete = findViewById(R.id.editstudent_delete_btn);

        delete.setOnClickListener(view->{
            Model.instance().deleteStudent(pos);
            Intent result = new Intent();
            result.putExtra("result", "delete");
            setResult(RESULT_OK, result);
            finish();
        });

    }

    public void bind(Student st, int pos) {
        name.setText(st.name);
        id.setText(st.id);
        phone.setText(st.phone);
        address.setText(st.address);
        cb.setChecked(st.cb);
    }

    public void bindBack(int pos) {
        data.get(pos).name = name.getText().toString();
        data.get(pos).id = id.getText().toString();
        data.get(pos).phone = phone.getText().toString();
        data.get(pos).address = address.getText().toString();
        data.get(pos).cb = cb.isChecked();
    }

}