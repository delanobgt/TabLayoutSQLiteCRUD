package com.example.tablayoutsqlitecrud.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class StudentTable extends SQLiteOpenHelper {

    private static final String TAG = "StudentTable";

    private static final String TABLE_NAME = "students";
    private static final String TABLE_CREATE_SQL =
            String.format(
                "CREATE TABLE %s (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "fullname TEXT NOT NULL," +
                    "birthdate TEXT NOT NULL," +
                    "gender TEXT NOT NULL CHECK(gender IN ('male', 'female'))," +
                    "telephone TEXT NOT NULL CHECK(CAST(telephone as INTEGER) IS telephone)," +
                    "country TEXT NOT NULL," +
                    "city TEXT NOT NULL" +
                ");", TABLE_NAME);
    private static final String KEY_ID = "id";
    private static final String KEY_FULLNAME = "fullname";
    private static final String KEY_BIRTHDATE = "birthdate";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_TELEPHONE = "telephone";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_CITY = "city";
    private static final String[] KEYS = {KEY_ID, KEY_FULLNAME, KEY_BIRTHDATE, KEY_GENDER, KEY_TELEPHONE, KEY_COUNTRY, KEY_CITY};

    private SQLiteDatabase db;

    public StudentTable(Context context){
        super(context, Config.DB_NAME, null, Config.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(TABLE_CREATE_SQL);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
        onCreate(db);
    }

    public StudentTable open() throws SQLException {
        db = getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }

    public long createStudent(Student student) {
        ContentValues values = new ContentValues();
        values.put(KEY_FULLNAME, student.fullname);
        values.put(KEY_BIRTHDATE, student.birthdate);
        values.put(KEY_GENDER, student.gender);
        values.put(KEY_TELEPHONE, student.telephone);
        values.put(KEY_COUNTRY, student.country);
        values.put(KEY_CITY, student.city);
        return db.insert(TABLE_NAME, null, values);
    }

    public List<Student> getStudents(){
        List<Student> studentList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, KEYS, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                studentList.add(new Student(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                ));
            } while (cursor.moveToNext());
        }
        return studentList;
    }

    public Student getStudentById(Long id) throws SQLException {
        Cursor cursor = db.query(TABLE_NAME, KEYS, "id=?", new String[] { id.toString() }, null, null, null);
        if (cursor.moveToFirst()) {
            return new Student(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
            );
        }
        return null;
    }

    public boolean updateContactById(Long id, Student student) {
        ContentValues values = new ContentValues();
        values.put(KEY_FULLNAME, student.fullname);
        values.put(KEY_BIRTHDATE, student.birthdate);
        values.put(KEY_GENDER, student.gender);
        values.put(KEY_TELEPHONE, student.telephone);
        values.put(KEY_COUNTRY, student.country);
        values.put(KEY_CITY, student.city);
        return db.update(TABLE_NAME, values, "id=?", new String[] { id.toString() }) > 0;
    }

    public boolean deleteContactById(Long id) {
        return db.delete(TABLE_NAME, "id=?", new String[] { id.toString() }) > 0;
    }
}