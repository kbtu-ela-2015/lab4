package com.example.stidentlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

  public static final String TABLE_STUDENTS = "students";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_NAME = "name";
  public static final String COLUMN_SURNAME = "surname";
  public static final String COLUMN_STUDENT_ID = "studentId";


  private static final String DATABASE_NAME = "students.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_STUDENTS + "(" + COLUMN_ID
      + " integer primary key autoincrement, " + COLUMN_NAME
      + " text not null, " + COLUMN_SURNAME+" text not null, "+ COLUMN_STUDENT_ID+" text not null);";

  public DataBaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(DataBaseHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
    onCreate(db);
  }

} 
