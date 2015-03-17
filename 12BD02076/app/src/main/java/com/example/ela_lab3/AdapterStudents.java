package com.example.ela_lab3;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdapterStudents extends BaseAdapter{
	 Context ctx;
	 LayoutInflater inflater;
	 private List<Student> objects;
	 private AlertDialog alert;
	 private AlertDialog.Builder builder;
	 private DBHelper dbHelper;
	 AdapterStudents(Context context){
		 ctx = context;
		 inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 dbHelper = new DBHelper(ctx);
	 }
	 AdapterStudents(Context context, List<Student> students) {
		 this(context);
		 this.objects = students;
	 }
	 public int getCount(){
		 return objects.size();
	 }
	 public static final class ViewHolder {
		 TextView tvName,tvFaculty;
		 Button btnEdit,btnDelete;
	 }
	 public void clearData(){
		 objects.clear();
	 }
	 public View getView(final int position,View convertView, ViewGroup parent){
		 ViewHolder holder;
		 if (convertView==null){
			 convertView = inflater.inflate(R.layout.student,parent,false);
			 holder = new ViewHolder();
	         holder.tvName = (TextView)convertView.findViewById(R.id.textViewName);
	         holder.tvFaculty = (TextView)convertView.findViewById(R.id.textViewFaculty);
	         holder.btnDelete = (Button)convertView.findViewById(R.id.btnDelete);
	         holder.btnEdit = (Button)convertView.findViewById(R.id.btnEdit);
	         convertView.setTag(holder);
		 }
	     else
	    	 holder = (ViewHolder) convertView.getTag();
		 final Student temp = objects.get(position);
	     holder.tvName.setText(temp.getsFirstName()+" "+temp.getsLastName());
	     holder.tvFaculty.setText(temp.getsFaculty());
	     holder.btnDelete.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				builder = new AlertDialog.Builder(ctx);
				LayoutInflater inflater = LayoutInflater.from(ctx);
				View inflatedLayout= inflater.inflate(R.layout.dialog_delete, null, false);
				builder.setView(inflatedLayout);
				builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface v, int arg1) {
						// TODO Auto-generated method stub
						dbHelper.deleteStudent(temp);
						objects.remove(temp);
						AdapterStudents.this.notifyDataSetChanged();
						alert.dismiss();
					}				
				});
				builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface v, int arg1) {
						// TODO Auto-generated method stub
						alert.dismiss();
					}				
				});

				alert = builder.create();
				alert.show();
			}	    	 
	     });
	     holder.btnEdit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				builder = new AlertDialog.Builder(ctx);
				LayoutInflater inflater = LayoutInflater.from(ctx);
				final View inflatedLayout= inflater.inflate(R.layout.dialog_student, null, false);
				builder.setView(inflatedLayout);
				
				((EditText)inflatedLayout.findViewById(R.id.etFirstName)).setText(temp.getsFirstName());
				((EditText)inflatedLayout.findViewById(R.id.etLastName)).setText(temp.getsLastName());
				((EditText)inflatedLayout.findViewById(R.id.etFaculty)).setText(temp.getsFaculty());
				builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface v, int arg1) {
						// TODO Auto-generated method stub
						temp.setsFirstName(((EditText)inflatedLayout.findViewById(R.id.etFirstName)).getText().toString());
						temp.setsLastName(((EditText)inflatedLayout.findViewById(R.id.etLastName)).getText().toString());
						temp.setsFaculty(((EditText)inflatedLayout.findViewById(R.id.etFaculty)).getText().toString());
						dbHelper.updateStudent(temp);
						AdapterStudents.this.notifyDataSetChanged();
						alert.dismiss();
					}				
				});
				builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface v, int arg1) {
						// TODO Auto-generated method stub
						alert.dismiss();
					}				
				});
				
				alert = builder.create();
				alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
				alert.show();
			}
	    	 
	     });
	     return convertView;
	 }
	 @Override
	 public Object getItem(int position) {
		 return objects.get(position);
	 }
	 @Override
	 public long getItemId(int i) {
		 return i;
	 }
}
