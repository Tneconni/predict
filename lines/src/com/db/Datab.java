package com.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;


public class Datab {
	private SQLiteDatabase sqldb;
	
	private static int version = 1;//数据库版本号	  
	private static String myDBName = "touch";
	private static mydbHelper mHelper;
	private SQLiteDatabase mDb;
	private final Context mctx;
	private static int tableCreated = 0;
	
	
	public void onCreate(Bundle savedInstanceState){
		
		//db = SQLiteDatabase.openOrCreateDatabase( factory, errorHandler);
		
	}
	
	public Datab(Context ctx){
		this.mctx = ctx;
		
	}
	
	public static Datab getInstance(Context c){
		
		return new Datab(c);
	}
	
	public static class mydbHelper extends SQLiteOpenHelper{

		public mydbHelper(Context context) {
			super(context, myDBName, null, version);
			
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) { System.out.println("db onCreate");
			// TODO Auto-generated method stub
			
			int l = DataSource.tabs.length;	

			String sql = "";
	
			for(int i = 0; i < l; i ++){
				sql += "Create table " + DataSource.tabs[i][0] + "(";
				int tabLen = DataSource.tabs[i].length;
				for(int j = 1; j < tabLen; j ++){
					sql += DataSource.tabs[i][j];
					if(j < tabLen - 1){
						sql += ",";
					}
				}
				sql += ");";
			
			}
			db.execSQL(sql);System.out.println("db onCreate end sql:" + sql);
			tableCreated = 1;
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			String sql = "";
			int l = DataSource.tabs.length;
			for(int i = 0; i < l; i ++){
				
				sql += "DROP TABLE IF EXISTS " + DataSource.tabs[i][0]+";";
				
			}
			db.execSQL(sql);
			onCreate(db);
		}
		
	}
	
	public void open(){System.out.println("open function");

		mHelper = new mydbHelper(mctx);
		sqldb = mHelper.getWritableDatabase();
		
	}

	public void close(){
		sqldb.close();

	}
	
	public void insert(String table, String[] fields, String[] vals){
		ContentValues v = new ContentValues();
		int l = fields.length;
		for(int i = 0; i < l; i ++){
			v.put(fields[i], vals[i]);
		}
		sqldb.insert(table, null, v);
		
	}
	
	/**查询数据*/
	public Cursor select(String table, String[] columns)
	{
		Cursor cursor = sqldb.query
		(
				table, columns, null, null, null, null, null
		);
		return cursor;
	}
	
}
