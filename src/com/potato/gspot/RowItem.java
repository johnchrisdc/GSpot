package com.potato.gspot;

import android.graphics.Bitmap;

public class RowItem {
	
	private Bitmap image;
	private String title;
	private String date;
	
	public RowItem(Bitmap imageID, String title, String desc){
		this.image = imageID;
		this.title = title;
		this.date = desc;
	}
	
	public Bitmap getImage(){
		return image;
	}
	
	public void setImageID(Bitmap image){
		this.image = image;
	}
	
	public String getDate(){
		return date;
	}
	
	public void setDate(String desc){
		this.date = desc;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String toString(){
		return title + "\n" + date;
	}
}