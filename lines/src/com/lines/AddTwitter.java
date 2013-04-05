package com.lines;

import java.io.UnsupportedEncodingException;

import com.tencentApi.ApiFamily;

import com.tencent.tauth.Tencent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

public class AddTwitter extends Activity {

	Button sendMsg;
	AlertDialog.Builder msgTips;
	String twitterContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_twitter);
		initView();
		
	}
	
	
	private void initView(){
		sendMsg = (Button)findViewById(R.id.add_single_twitter);
		sendMsg.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createDialog();  // I am warry about the code
				msgTips.create().show(); 
				
			}
			
		});
		
	}
	
	
	
	
	private void createDialog(){
		msgTips = new Builder(AddTwitter.this);
		LayoutInflater factory = LayoutInflater.from(this);  
	    final View textEntryView = factory.inflate(R.layout.edit_twitter, null);  
//	    builder.setIcon(R.drawable.icon);  
	    msgTips.setTitle("please write your twitter");  
	    msgTips.setView(textEntryView);  
	    msgTips.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
	        public void onClick(DialogInterface dialog, int whichButton) {
	  
	            EditText inputNumber = (EditText) textEntryView.findViewById(R.id.edit_single_twitter);  
	            twitterContent = inputNumber.getText().toString();  
	  
	            // send twitter to tencent
	            try {			
	            	
	            	String singleTwitter = ApiFamily.add_t(twitterContent);	            		
	            	
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	        }
	    });  
	  
	    msgTips.setNegativeButton("取消", new DialogInterface.OnClickListener() {  
	        public void onClick(DialogInterface dialog, int whichButton) {  
	        	
	        }  
	    });
		
			
	}
	

}
