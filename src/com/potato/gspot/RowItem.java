package com.potato.gspot;

public class RowItem {
	
	private int imageID;
	private String title;
	private String desc;
	
	public RowItem(int imageID, String title, String desc){
		this.imageID = imageID;
		this.title = title;
		this.desc = desc;
	}
	
	public int getImageID(){
		return imageID;
	}
	
	public void setImageID(int imageID){
		this.imageID = imageID;
	}
	
	public String getDesc(){
		return desc;
	}
	
	public void setDesc(String desc){
		this.desc = desc;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String toString(){
		return title + "\n" + desc;
	}
}