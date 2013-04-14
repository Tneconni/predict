package com.db;

public class DataSource {
	
	public static String[][] tabs = new String[][]{
		{"body_group","id INTEGER PRIMARY KEY AUTOINCREMENT","group_name text","group_type int","status int" },
		{"body_group_type","id INTEGER PRIMARY KEY AUTOINCREMENT","name text"},
		{"event_type","id INTEGER PRIMARY KEY AUTOINCREMENT","name text"},
		{"body","id INTEGER PRIMARY KEY AUTOINCREMENT","name text","gender text","age INTEGER","body_group text"},
		{"note", "id INTEGER PRIMARY KEY AUTOINCREMENT", "recorder int ", "title text", "content text","persons text"}
	};
	

}
