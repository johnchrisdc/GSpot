package com.potato.gspot;

import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListViewAdapter extends ArrayAdapter<RowItem>{
	Context context;
	
	private Random mRandomizer = new Random();
	
	public CustomListViewAdapter(Context context, int resourceId, List<RowItem> items){
		super(context, resourceId, items);
		this.context = context;
	}
	
	private class ViewHolder{
		ImageView imageView;
		TextView txtTitle;
		TextView txtDesc;
	}
	
	
	//TLDR - Too Long Don't Read
	
	public View getView (int position, View convertView, ViewGroup parent){
		ViewHolder holder = null;
		RowItem rowItem = getItem(position);
		LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.txtDesc = (TextView)convertView.findViewById(R.id.desc);
			holder.txtTitle = (TextView)convertView.findViewById(R.id.title);
			holder.imageView = (ImageView)convertView.findViewById(R.id.icon);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		holder.txtDesc.setText(rowItem.getDesc());
		holder.txtTitle.setText(rowItem.getTitle());
		holder.imageView.setImageResource(rowItem.getImageID());
		
		int color = Color.argb(255, mRandomizer.nextInt(256), mRandomizer.nextInt(256), mRandomizer.nextInt(256));
        
		return convertView; //BABAlik ka rin
		
	}
	
}
