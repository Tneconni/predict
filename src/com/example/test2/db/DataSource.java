package com.example.test2.db;

public class DataSource {
//	public static String[] tabs = new String[]{
//		"Create table midnight(id INTEGER PRIMARY KEY AUTOINCREMENT,name text,budget DOUBLE);",
//		"CREATE TABLE articles(id INTEGER PRIMARY KEY AUTOINCREMENT, author text DEFAULT 'guest'," +
//		" title text DEFAULT NULL, content text , publishTime datetime DEFAULT NULL, typeId text DEFAULT NULL , status INTEGER DEFAULT '0' );"
//	};
	
	public static String[][] tabs = new String[][]{
		{"note", "id INTEGER PRIMARY KEY AUTOINCREMENT", "recorder int ", "title text", "content text","persons text"},
		{"body","id INTEGER PRIMARY KEY AUTOINCREMENT","name text","gender text","age INTEGER","body_group text"},
		{"body_group","id INTEGER PRIMARY KEY AUTOINCREMENT","group_name text","group_type int","status int" },
		{"body_group_type","id INTEGER PRIMARY KEY AUTOINCREMENT","name text"},
		{"event_type","id INTEGER PRIMARY KEY AUTOINCREMENT","name text"}		
	};
	

}
