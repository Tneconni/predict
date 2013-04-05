package com.example.test2;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FansList extends Activity {
	
	public  ListView lv;
	private DataAdapter data;
	public JSONArray fansJson = new JSONArray();
	private String fansString = new String(); 

	public ArrayList list;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fans_list);
		try {
			getIntentData();
			initView();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	private void getIntentData() throws JSONException{
		
		Intent fansData = getIntent();
		Bundle bdl = fansData.getExtras();
		fansString = bdl.getString("fansList"); 
		
		 JSONObject json = new JSONObject(fansString);
		 fansJson = json.getJSONObject("data").getJSONArray("info");

	}
	
	private void initView() throws JSONException{
		lv = (ListView) findViewById(R.id.fansList);
		
		List<String> list = new ArrayList<String>();
		
		int l = fansJson.length();
		for(int i = 0; i < l; i ++){
			JSONObject singleFans = fansJson.getJSONObject(i); 
			String nickName = singleFans.getString("nick");
			String text = singleFans.getJSONArray("tweet").getJSONObject(0).getString("text");
			list.add(nickName + "--" + text);

		}
		DataAdapter fans = new DataAdapter( this );
		lv.setAdapter(fans);
		
		
	}

}
