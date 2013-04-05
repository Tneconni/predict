package com.example.test2;

import java.io.UnsupportedEncodingException;

import tencentApi.ApiFamily;

import com.example.test2.db.Datab;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;


import android.app.AlertDialog;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.app.Dialog;
import android.view.LayoutInflater;

public class WriteNewRecord extends Activity {
	private Datab db;
	private EditText recordTitle;
	private EditText recordContent;
	private Button recordSave;
	private Button recordCancel;

	private Button addNote;
	private Button addPerson;
	private Button addGroup;
	
	private AlertDialog.Builder builder;
	private AlertDialog dialog;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_record);
		initView();
		initDB();
	}
	
	private void initView(){
		initBuilder();
		recordTitle = (EditText)findViewById(R.id.recordTitle);
		recordContent = (EditText)findViewById(R.id.recordContent);
		recordSave = (Button)findViewById(R.id.recordSave);
		recordCancel = (Button)findViewById(R.id.recordCancel);
		
		addNote = (Button)findViewById(R.id.addNote);
		addPerson = (Button)findViewById(R.id.addPerson);
		addGroup = (Button)findViewById(R.id.addGroup);
		
		recordSave.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				db.open();
				// TODO Auto-generated method stub
				String titleValue = recordTitle.getText().toString();
				String contentValue = recordContent.getText().toString();
				db.insert("note", new String[]{"title","content"}, new String[]{titleValue,contentValue});

				db.close();
			}
			
		});
		
		addNote.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.show();
			}
			
		});
		addPerson.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
		addGroup.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private void initDB(){
		
		db = Datab.getInstance(this.getApplicationContext());

	}
	
	private void initBuilder(){
		builder = new Builder(WriteNewRecord.this);

		LayoutInflater factory = LayoutInflater.from(this);  
	    final View textEntryView = factory.inflate(R.layout.edit_twitter, null);  
//	    builder.setIcon(R.drawable.icon);  
	    builder.setTitle("please write your twitter");  
	    builder.setView(textEntryView);  
	    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
	    	

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
	    });  
	  
	    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}  
	    });
	    
	    dialog = builder.create();
	}

}
