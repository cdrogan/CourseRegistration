package com.example.courseregistration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    public static final String DB_NAME = "waitinglistdb";

    // below int is our database version
    public static final int DB_VERSION = 1;

    // below variable is for our table name.
    public static final String TABLE_NAME = "waitinglist";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our student name column
    public static final String NAME_COL = "name";

    // below variable id for our priority column.
    public static final String PRIORITY_COL = "priority";

    // below variable for our course description column.
    public static final String COURSE_COL = "course";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating 
        // an sqlite query and we are 
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + PRIORITY_COL + " TEXT,"
                + COURSE_COL + " TEXT)";

        // at last we are calling a exec sql 
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewStudent(String student, String priority, String course) {

        // on below line we are creating a variable for 
        // our sqlite database and calling writable method 
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a 
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values 
        // along with its key and value pair.
        values.put(NAME_COL, student);
        values.put(PRIORITY_COL, priority);
        values.put(COURSE_COL, course);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void deleteStudent(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, NAME_COL + "=?", new String[]{name});
        db.close();
    }

    public void editStudentInfo(String name, String priority, String course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRIORITY_COL, priority);
        values.put(COURSE_COL, course);
        db.update(TABLE_NAME, values, NAME_COL + "=?", new String[]{name});
        db.close();

    }

    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {ID_COL, NAME_COL, PRIORITY_COL, COURSE_COL};
        return db.query(TABLE_NAME, projection, null, null, null, null, null);
    }
}