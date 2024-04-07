package com.example.courseregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

        public class MainActivity extends AppCompatActivity {

            // creating variables for our edittext, button and dbhandler
            private EditText StudentName, Priority, Course;
            private Button BtnAddStudent, BtnEditStudent, BtnDeleteStudent, BtnViewList;
            private DBHandler dbHandler;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                // initializing all our variables.
                StudentName = findViewById(R.id.StudentName);
                Priority = findViewById(R.id.Priority);
                Course = findViewById(R.id.Course);
                BtnAddStudent = findViewById(R.id.BtnAddStudent);
                BtnEditStudent = findViewById(R.id.BtnEditStudent);
                BtnDeleteStudent = findViewById(R.id.BtnDeleteStudent);
                BtnViewList = findViewById(R.id.BtnViewList);

                // creating a new dbhandler class
                // and passing our context to it.
                dbHandler = new DBHandler(com.example.courseregistration.MainActivity.this);



                // on click listener for our add Student button.
                BtnAddStudent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // below line is to get data from all edit text fields.
                        String name = StudentName.getText().toString();
                        String priority = Priority.getText().toString();
                        String courseName = Course.getText().toString();

                        // validating if the text fields are empty or not.
                        if (name.isEmpty() && priority.isEmpty() && courseName.isEmpty()) {
                            Toast.makeText(com.example.courseregistration.MainActivity.this, "Please enter all the data.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // on below line we are calling a method to add new
                        // course to sqlite data and pass all our values to it.
                        dbHandler.addNewStudent(name, priority, courseName);

                        // after adding the data we are displaying a toast message.
                        Toast.makeText(com.example.courseregistration.MainActivity.this, "Student has been added.", Toast.LENGTH_SHORT).show();
                        StudentName.setText("");
                        Priority.setText("");
                        Course.setText("");
                    }
                });

                // on click listener for Delete student button
                BtnDeleteStudent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Get student name from EditText
                        String name = StudentName.getText().toString().trim();

                        // Call method to remove student
                        dbHandler.deleteStudent(name);

                        // Clear input fields
                        StudentName.setText("");
                        Priority.setText("");
                        Course.setText("");

                        // Notify user
                        Toast.makeText(com.example.courseregistration.MainActivity.this, "Student removed from the waiting list.", Toast.LENGTH_SHORT).show();
                    }
                });

                // on click listener for edit student button
                BtnEditStudent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Get input from EditText fields
                        String name = StudentName.getText().toString().trim();
                        String priority = Priority.getText().toString().trim();
                        String course = Course.getText().toString().trim();

                        // Call method to update student info
                        dbHandler.editStudentInfo(name, priority, course);

                        // Clear input fields
                        StudentName.setText("");
                        Priority.setText("");
                        Course.setText("");

                        // Notify user
                        Toast.makeText(com.example.courseregistration.MainActivity.this, "Student information updated.", Toast.LENGTH_SHORT).show();
                    }
                });

                //on click listener to view list
                BtnViewList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Start the ListActivity when the button is clicked
                        startActivity(new Intent(MainActivity.this, LoadTable.class));
                    }
                });

            }
        }