package com.potato.gspot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccountsDatabaseAdapter{
	
	static MyHelper helper;
	
	public AccountsDatabaseAdapter (Context context){
		helper=new MyHelper(context);
		
	}
	
	static class MyHelper extends SQLiteOpenHelper{
		private static final String DATABASE_NAME="MyDB";
		private static final String TABLE_NAME="accounts";
		private static final int DATABASE_VESION=1;
		
		private static final String UID="_id";
		private static final String QUESTION="question";
		private static final String ANSWER="answer";
		private static final String PASSWORD="password";
		private static final String CREATE_TABLE=" CREATE TABLE " + TABLE_NAME
				+ "(" + UID + " integer primary key autoincrement, " + QUESTION
				+ " varchar(50), "+ ANSWER + " varchar(50), "
				+PASSWORD +" varchar(50))";
		
		private Context context;
		
		public MyHelper (Context context){
			super(context,DATABASE_NAME,null,DATABASE_VESION);
			this.context=context;
			Message.message(context, "Constructor called. Database Created.");
			
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(CREATE_TABLE);
				Message.message(context,  "table_create");
			} catch (SQLException e) {
				Message.message(context, "" + e);
			}
			
			
		}
		
		public int updateName(String oldName,String newName){
			SQLiteDatabase db =  helper.getWritableDatabase();
			ContentValues con =  new ContentValues();
			con.put(MyHelper.QUESTION, newName);
			String [] whereArgs  = {oldName};
			//first parameter table, connection,field na iaupdate
			int count = db.update(MyHelper.TABLE_NAME, con, MyHelper.QUESTION + "=?",whereArgs);
			return count;
		}
		
		public int deleteRow(String name) {
			SQLiteDatabase db = helper.getWritableDatabase();
			String [] whereArgs  = {name};
			int count = db.delete(MyHelper.TABLE_NAME, MyHelper.QUESTION + "=?",whereArgs);
			return count;
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}
		
		public long insertData(String question ,String answer , String password){
			SQLiteDatabase db = helper.getWritableDatabase();
			ContentValues con = new ContentValues();
			con.put(MyHelper.QUESTION, question);
			con.put(MyHelper.ANSWER, answer);
			con.put(MyHelper.PASSWORD, password);
			long id = db.insert(MyHelper.TABLE_NAME, null, con);//methods that returns long variable
			db.close();
			return id;			
		}
		
		public String getAllData(String namee,String pass){
			SQLiteDatabase db = helper.getWritableDatabase();
			ContentValues con = new ContentValues();
			String [] columns = {MyHelper.QUESTION, MyHelper.PASSWORD};
			Cursor c= db.query(MyHelper.TABLE_NAME, columns, null, null, null, null, null);
			StringBuffer buffer = new StringBuffer();
			String count ="";
			while(c.moveToNext()){
				String name=c.getString(0);
				String password=c.getString(1);	
				
				if(name.equals(namee) && password.equals(pass))
				{
					count = "aaa";
				}
				
			}
			return count;
		}
		
		public boolean isPINOkay(String pass){
			SQLiteDatabase db = helper.getWritableDatabase();
			ContentValues con = new ContentValues();
			String [] columns = {MyHelper.PASSWORD};
			Cursor c= db.query(MyHelper.TABLE_NAME, columns, null, null, null, null, null);
			StringBuffer buffer = new StringBuffer();
			String count ="";
			while(c.moveToNext()){
				String password=c.getString(0);	
				
				if(password.equals(pass))
				{
					return true;
				}
				
			}
			return false;
		}
		
		public boolean isThereAnAccount(){
			SQLiteDatabase db = helper.getWritableDatabase();
			ContentValues con = new ContentValues();
			String [] columns = {MyHelper.PASSWORD};
			Cursor c= db.query(MyHelper.TABLE_NAME, columns, null, null, null, null, null);
			StringBuffer buffer = new StringBuffer();
			String count ="";
			while(c.moveToNext()){
				
				return true;
				
			}
			return false;
		}

	}
}
