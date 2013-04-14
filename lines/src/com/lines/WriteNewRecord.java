package com.lines;

import java.io.UnsupportedEncodingException;

import com.tencentApi.ApiFamily;

import com.db.Datab;

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
	private int clickSign = 0;
	
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
				clickSign = 1;
				dialog.setTitle("asd");
				dialog.show();
			}
			
		});
		addPerson.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.setTitle("qwe");
				clickSign = 2;
				dialog.show();
			}
			
		});
		addGroup.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.setTitle("zxc");
				clickSign = 3;
				dialog.show();
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
	    builder.setTitle("please add new person");  
	    builder.setView(textEntryView);  
	    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
	    	
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String table = "note";
				String[] insertKey;
				String[] insertValue;
				EditText edit = (EditText)textEntryView.findViewById(R.id.edit_single_twitter);
				String value = edit.getText().toString();

				if(clickSign == 1){

					insertKey = new String[]{"content","title"};
					insertValue = new String[]{value,"default"};
				}else if(clickSign == 2){
					table = "body";

					insertKey = new String[]{"name","age"};
					insertValue = new String[]{value,"24"};
				}else{
					table = "body_group";

					insertKey = new String[]{"group_name","group_type"};
					insertValue = new String[]{value,"default"};
				}
				db.open();
				db.insert(table, insertKey, insertValue);
				db.close();

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
