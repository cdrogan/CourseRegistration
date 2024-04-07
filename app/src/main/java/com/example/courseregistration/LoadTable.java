package com.example.courseregistration;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LoadTable extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DBHandler dbHandler;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_table);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dbHandler = new DBHandler(this);

        displayTableData();
    }

    private void displayTableData() {
        Cursor cursor = dbHandler.getAllStudents();
        if (cursor == null || cursor.getCount() == 0) {
            Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
            return;
        }

        adapter = new StudentAdapter(this, cursor);
        recyclerView.setAdapter(adapter);
    }
}