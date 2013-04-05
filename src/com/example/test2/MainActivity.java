package com.example.test2;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import tencentApi.ApiFamily;

import com.example.test2.db.Datab;



import android.R.string;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.tencent.open.HttpStatusException;
import com.tencent.open.NetworkUnavailableException;
import com.tencent.open.OpenConfig;

import com.tencent.tauth.Constants;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;


public class MainActivity extends Activity {
	
	private Datab db;
	public ListView lv;
	DataAdapter data;
	private Button writeRecordBtn;
//	Tencent mTencent;
	Button mBaseMessageText;
	Button mMessageText;
	Button addT;
	Button fans;
	String SCOPE = "all";
	String openId = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView)findViewById(R.id.lv);
		writeRecordBtn = (Button) findViewById(R.id.writeRecord);
		
		testdb();
		initView();
		ApiFamily.mTencent = Tencent.createInstance("100402110", this.getApplicationContext());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	private void initView(){
		mMessageText = (Button) findViewById(R.id.mMessage);
		mBaseMessageText = (Button) findViewById(R.id.mBaseMessage);
		fans = (Button) findViewById(R.id.fans);
		addT = (Button) findViewById(R.id.add_t);
		writeRecordBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent itt = new Intent(MainActivity.this, WriteNewRecord.class);
				startActivity(itt);
			}
			
		});
		mMessageText.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BaseUiListener uiListener = new BaseUiListener();
				ApiFamily.mTencent.login(MainActivity.this, SCOPE, uiListener);
				
			}
			
		});
		
		mBaseMessageText.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				ApiFamily.mTencent.logout(MainActivity.this);
			}
			
		});
		
		fans.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				String fansList = ApiFamily.getFansList(ApiFamily.mTencent);
				if(fansList != ""){

					Intent fansHref = new Intent(MainActivity.this,FansList.class);
					fansHref.putExtra("fansList", fansList);
					startActivity(fansHref);
					
				}
				
			}
			
		});
		
		addT.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				if(ApiFamily.mTencent != null){
					Intent addTwitterHref = new Intent(MainActivity.this,AddTwitter.class);
					startActivity(addTwitterHref);	
				}	
			}
			
		});
				
	}
	
	private void testdb(){
		db = Datab.getInstance(this.getApplicationContext());
		db.open();
		
		Cursor c = db.select("note", new String[]{"title", "content"});
		
		List<String> list = new ArrayList<String>();
		while(c.moveToNext()){
			list.add(c.getString(0) +"::"+ c.getString(1));
			
		}
		
		
		ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,R.layout.data,list);
		
		lv.setAdapter(adapter);
		c.close();
		db.close();
	}
	
	@Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		ApiFamily.mTencent.onActivityResult(requestCode, resultCode, data) ;

	 }
	
	private class BaseUiListener implements IUiListener {
		@Override
		public void onComplete(JSONObject response) {
			
			try {
				if(ApiFamily.checkLogin(response)){
					mBaseMessageText.setText("welcome");
				}else{
					mBaseMessageText.setText("failure");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			doComplete(response);
		}
		
		protected void doComplete(JSONObject values) {
			
		}
		
		@Override
		public void onError(UiError e) {
			showResult("onError:", "code:" + e.errorCode + ", msg:"
					+ e.errorMessage + ", detail:" + e.errorDetail);
		}
		
		private void showResult(String string, String string2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onCancel() {
			showResult("onCancel", "");
			
		}
		
	}

}
