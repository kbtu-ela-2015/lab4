package com.example.ela_lab3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper implements  StudentDAO{

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String S_COLUMN_NAME = "name";
    public static final String S_COLUMN_LAST_NAME = "last_name";
    public static final String S_FACULTY = "faculty";


    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table students " +
                        "(id integer primary key, name text,last_name text,faculty text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS students");
        onCreate(db);
    }
    @Override
    public List<Student> findAll() {
        List array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from students", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            Student temp = new Student();
            temp.setsID(res.getInt(0));
            temp.setsFirstName(res.getString(1));
            temp.setsLastName(res.getString(2));
            temp.setsFaculty(res.getString(3));
            array_list.add(temp);
            res.moveToNext();
        }
        return array_list;
    }

    @Override
    public List<Student> findById(int id) {
        List array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from students where id ="+id, null );

        do{
            Student temp = new Student();
            temp.setsID(res.getInt(0));
            temp.setsFirstName(res.getString(1));
            temp.setsLastName(res.getString(2));
            temp.setsFaculty(res.getString(3));
            array_list.add(temp);
            res.moveToNext();
        }
        while(res.moveToFirst());
        return array_list;
    }

    @Override
    public List<Student> findByName(String name) {
        List array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from students where name = "+name, null );

        res.moveToFirst();
        while(res.isAfterLast() == false){
            Student temp = new Student();
            temp.setsID(res.getInt(0));
            temp.setsFirstName(res.getString(1));
            temp.setsLastName(res.getString(2));
            temp.setsFaculty(res.getString(3));
            array_list.add(temp);
            res.moveToNext();
        }
        return array_list;
    }

    @Override
    public boolean insertStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(S_COLUMN_NAME, student.getsFirstName());
        contentValues.put(S_COLUMN_LAST_NAME, student.getsLastName());
        contentValues.put(S_FACULTY, student.getsFaculty());

        db.insert("students", null, contentValues);
        return true;
    }

    @Override
    public int deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("students",
                "id = ? ",
                new String[] { Integer.toString(student.getsID())});
    }

    @Override
    public boolean updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(S_COLUMN_NAME, student.getsFirstName());
        contentValues.put(S_COLUMN_LAST_NAME, student.getsLastName());
        contentValues.put(S_FACULTY, student.getsFaculty());
        db.update("students", contentValues, "id = ? ", new String[]{Integer.toString(student.getsID())});
        return true;
    }
}