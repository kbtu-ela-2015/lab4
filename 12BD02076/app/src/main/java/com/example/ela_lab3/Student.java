package com.example.ela_lab3;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "Students")
public class Student {
	public static final String S_ID = "student_id";
	public static final String S_FIRST_NAME = "student_first_name";
	public static final String S_LAST_NAME = "student_last_name";
	public static final String S_FACULTY = "student_faculty";
	@DatabaseField(generatedId = true, canBeNull = false, columnName = S_ID)
	private int sID;
	@DatabaseField(columnName = S_FIRST_NAME)
	private String sFirstName;
	@DatabaseField(columnName = S_LAST_NAME)
	private String sLastName;
	@DatabaseField(columnName = S_FACULTY)
	private String sFaculty;
	
	public Student(){}
	public int getsID() {
		return sID;
	}
	public void setsID(int sID) {
		this.sID = sID;
	}
	public String getsFirstName() {
		return sFirstName;
	}
	public void setsFirstName(String sFirstName) {
		this.sFirstName = sFirstName;
	}
	public String getsLastName() {
		return sLastName;
	}
	public void setsLastName(String sLastName) {
		this.sLastName = sLastName;
	}
	public String getsFaculty() {
		return sFaculty;
	}
	public void setsFaculty(String sFaculty) {
		this.sFaculty = sFaculty;
	}
	
	
	
}
