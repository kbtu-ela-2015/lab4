package com.example.stidentlist;

import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

public class StudentDAO {

	private SQLiteDatabase database;
	private DataBaseHelper dbHelper;
	private String[] allColumns = { DataBaseHelper.COLUMN_ID,
			DataBaseHelper.COLUMN_NAME, DataBaseHelper.COLUMN_SURNAME, DataBaseHelper.COLUMN_STUDENT_ID };
	
	public StudentDAO(Context context){
		dbHelper = new DataBaseHelper(context);
	}
	
	public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
	    dbHelper.close();
	}
	
	public void insert(Student student){
		String name = student.name.replaceAll("'","\'");
		String surname = student.surname.replaceAll("'","\'");
		String studentId = student.studentId.replaceAll("'","\'");

        database.execSQL("insert into students (name,surname,studentId) values(?,?,?);", new String[]{ name,surname,studentId});

	}
	
	public void delete(Student student){
		Integer i = student.id;

        database.execSQL("delete from students where _id="+"'"+i.toString()+"'");

	}
	
	public ArrayList<Student> select(){
		ArrayList<Student> students = new ArrayList<Student>();
		
        String  q = "select * from students";
        Cursor cursor = database.rawQuery(q, null);
        cursor.moveToFirst();

        if(!cursor.isAfterLast()) {

            do {
            	Student student = new Student();
                String str = cursor.getString(0);
                Integer i = Integer.parseInt(str);
                student.id = i;
                
                str = cursor.getString(1);
                student.name=str;
                
                str = cursor.getString(2);
                student.surname = str;
                
                str = cursor.getString(3);
                student.studentId = str;
                students.add(student);
            } while (cursor.moveToNext());
        }
            cursor.close();
		return students;
	}
	
	public void update(Student student){
		ContentValues values = new ContentValues();
		values.put("name", student.name); 
		values.put("surname", student.surname); 
		values.put("studentId", student.studentId); 
		Integer i = student.id;

		database.update("students", values, "_id=?", new String[]{i.toString()});

	}
	
}
