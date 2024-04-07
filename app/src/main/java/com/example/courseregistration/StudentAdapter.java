package com.example.courseregistration;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Context context;
    private Cursor cursor;

    public StudentAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        if (!cursor.moveToPosition(position)) {
            return;
        }

        String name = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.NAME_COL));
        String priority = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.PRIORITY_COL));
        String course = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.COURSE_COL));

        holder.textViewStudentName.setText(name);
        holder.textViewPriority.setText(priority);
        holder.textViewCourse.setText(course);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewStudentName;
        public TextView textViewPriority;
        public TextView textViewCourse;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewStudentName = itemView.findViewById(R.id.textViewStudentName);
            textViewPriority = itemView.findViewById(R.id.textViewPriority);
            textViewCourse = itemView.findViewById(R.id.textViewCourse);
        }
    }
}
