package com.example.test2;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;

public class DataAdapter extends BaseAdapter {

	private JSONArray json;
	private LayoutInflater inflater;
	
	private JSONArray fansArray;
	
	private ArrayList<Object> list;
	public DataAdapter(FansList activity) {
		// TODO Auto-generated constructor stub
		fansArray = activity.fansJson;
		
		inflater = LayoutInflater.from(activity);
		
	}

	public void DataAdapter(JSONObject json){
		
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fansArray.length();
	}

	@Override
	public Object getItem(int no) {
		// TODO Auto-generated method stub
//		try {
//			return fansArray.getJSONObject(no);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int i, View dataView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		
		dataView = inflater.inflate(R.layout.fans_content, null);
		TextView name = (TextView) dataView.findViewById(R.id.wbName);
		TextView content = (TextView) dataView.findViewById(R.id.wbContent);
		JSONObject singleFans;
		try {
			singleFans = fansArray.getJSONObject(i); System.out.println("dataAdapter singleFans :"); System.out.println(singleFans);
			String nickName = singleFans.getString("nick");
			String text = singleFans.getJSONArray("tweet").getJSONObject(0).getString("text");
			name.setText(nickName);
			content.setText(text);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return dataView;
		
	}

}
