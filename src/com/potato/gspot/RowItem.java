package com.potato.gspot;

import android.graphics.Bitmap;

public class RowItem {
	
	private Bitmap image;
	private String title;
	private String date;
	private String description;
	
	public RowItem(Bitmap imageID, String title, String desc, String date){
		this.image = imageID;
		this.title = title;
		this.date = date;
		this.description = desc;
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
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String dessc){
		this.description = dessc;
	}
	
	public String toString(){
		return title + "\n" + date;
	}
}