package com.example.assignmenttwostudentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.assignmenttwostudentapp.model.Model;
import com.example.assignmenttwostudentapp.model.Student;

import java.util.List;

public class StudentRecyclerList extends AppCompatActivity {
    List<Student> data;
    RecyclerView list;
    StudentRecyclerAdapter adapter;
    Button plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_recycler_list);

        data = Model.instance().getAllStudents();
        list = findViewById(R.id.studentrecycler_list);
        list.setHasFixedSize(true);

        plus = findViewById(R.id.studentrecyclerlist_plus_btn);

        plus.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddStudentActivity.class);
            startActivity(intent);
        });

        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudentRecyclerAdapter();
        list.setAdapter(adapter);

        adapter.setOnItemClickListener((int pos) -> {
                    Log.d("TAG", "Row was clicked " + pos);
                    Intent intent = new Intent(this, StudentDetailsActivity.class);
                    intent.putExtra("pos", pos);
                    startActivity(intent);
                }
        );
    }

        @Override
        protected void onStart() {
            super.onStart();
            adapter.notifyDataSetChanged();
        }

    class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        TextView idTv;
        CheckBox cb;
        public StudentViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.studentlistrow_name_tv);
            idTv = itemView.findViewById(R.id.studentlistrow_id_tv);
            cb = itemView.findViewById(R.id.studentlistrow_cb);
            cb.setOnClickListener(view -> {
                int pos = (int)cb.getTag();
                Student st = data.get(pos);
                st.cb = cb.isChecked();
            });
            itemView.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                listener.onItemClick(pos);
            });
        }

        public void bind(Student st, int pos) {
            nameTv.setText(st.name);
            idTv.setText(st.id);
            cb.setChecked(st.cb);
            cb.setTag(pos);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }

    class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder> {
        OnItemClickListener listener;
        void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row, parent, false);
            return new StudentViewHolder(view, listener);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student st = data.get(position);
            holder.bind(st, position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}