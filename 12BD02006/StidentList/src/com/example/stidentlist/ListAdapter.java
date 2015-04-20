package com.example.stidentlist;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter{
	 Context context;
	    ArrayList<Student> students;
	    public ListAdapter(Context context, int resource, ArrayList<Student> students) {
	        super(context, resource);
	        this.context = context;
	        this.students = students;
	    }

	    @Override
	    public int getCount() {
	        return students.size();
	    }

	    @SuppressLint("ViewHolder") @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View row = convertView;

	        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
	        row = inflater.inflate(R.layout.lisview_row, parent, false);

	        TextView txt = (TextView) row.findViewById(R.id.student_name);
	        txt.setText(students.get(position).name);

	        txt = (TextView) row.findViewById(R.id.student_surname);
	        txt.setText(students.get(position).surname);

	        txt = (TextView) row.findViewById(R.id.student_id);
	        txt.setText(students.get(position).studentId);

	        ImageView imageView = (ImageView) row.findViewById(R.id.delete_btn);
	        imageView.setTag(position);
	        imageView.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                Integer i = (Integer) view.getTag();
	                ((MainActivity)context).deleteStudent(i);
	            }
	        });

	        ImageView imageView1 = (ImageView) row.findViewById(R.id.edit_btn);
	        imageView1.setTag(position);
	        imageView1.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                Integer i = (Integer) view.getTag();
	                ((MainActivity)context).showEditDialog(i);
	            }
	        });
	        return row;
	    }

}
