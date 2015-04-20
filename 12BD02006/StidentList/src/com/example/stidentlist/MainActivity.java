package com.example.stidentlist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

	 ListView listView;
	    ArrayList<Student> students;
	    ListAdapter listAdapter;
	    Dialog dialog;
	    View dialogView;
	    View add_btn;
	    EditText nameEdit, surnameEdit, idEdit;
	    Button confirm;
	    int ind,pos;
	    StudentDAO dao;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);

	        
	        init();
	        fillList();

	        add_btn.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                ind=1;
	                nameEdit.setText("");
	    	        surnameEdit.setText("");
	    	        idEdit.setText("");
	                dialog.show();
	            }
	        });

	        confirm.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {

	                String str1 = nameEdit.getText().toString();
	                String str2 = surnameEdit.getText().toString();
	                String str3 = idEdit.getText().toString();

	                if (!str1.equals("") && !str2.equals("") && !str2.equals("")) {

	                    if (ind==1)
	                        createStudent(str1, str2, str3);
	                    else if (ind==2)
	                        try {
	                            updateStudent(pos,str1,str2,str3);
	                        } catch (SQLException e) {
	                            e.printStackTrace();
	                        }

	                }else  showToast();


	            }
	        });

	    }


	    public void init(){
	    	
	        dao = new StudentDAO(this);
	        dao.open();
	    	ind=1;
	        listView = (ListView) findViewById(R.id.list);


	        dialog = new Dialog(this);
	        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
	        LayoutInflater inflater = LayoutInflater.from(this);
	        dialogView = inflater.inflate(R.layout.dialog_style, null);
	        dialog.setContentView(dialogView);

	        add_btn = findViewById(R.id.add_btn);

	        nameEdit = (EditText) dialogView.findViewById(R.id.name_edit);
	        surnameEdit = (EditText) dialogView.findViewById(R.id.surname_edit);
	        idEdit = (EditText) dialogView.findViewById(R.id.id_edit);

	        confirm = (Button) dialogView.findViewById(R.id.confirm_add);


	    }
	    public void fillList(){
	        //students = studentMapper.select();
	    	students = dao.select();
	        listAdapter = new ListAdapter(this,0,students);
	        listView.setAdapter(listAdapter);
	    }

	    public void createStudent(String name, String surname, String student_id){
	        Student student = new Student(name, surname, student_id);
	        //studentMapper.insert(student);
	        dao.insert(student);
	        students.add(student);
	        listAdapter.notifyDataSetChanged();
	        dialog.dismiss();

	    }

	    public void deleteStudent(int position){
	        //studentMapper.delete(students.get(position));
	    	dao.delete(students.get(position));
	        students.remove(position);

	        listAdapter.notifyDataSetChanged();
	    }

	    public void updateStudent(int position,String name, String surname, String student_id) throws SQLException {

	        Student student = students.get(pos);
	        student.name = name;
	        student.surname = surname;
	        student.studentId = student_id;


	        //studentMapper.update(student);
	        dao.update(student);
	        students.set(position,student);
	        listAdapter.notifyDataSetChanged();

	        dialog.dismiss();
	    }

	    public void showEditDialog(int position){
	        nameEdit.setText(students.get(position).name);
	        surnameEdit.setText(students.get(position).surname);
	        idEdit.setText(students.get(position).studentId);
	        ind=2;
	        pos = position;
	        dialog.show();

	    }

	    public void showToast(){
	        Toast toast = Toast.makeText(this, "Fill all fields", 100);
	        toast.setGravity(Gravity.CENTER, 0, 0);
	        toast.show();
	    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	
}
