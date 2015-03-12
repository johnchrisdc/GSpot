package com.potato.gspot;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class CreatePost extends Activity {

	ImageView photo;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.post_photo);
	    
	    String imagePath = getIntent().getStringExtra(MainActivity.IMAGE_PATH);
	    
	    photo = (ImageView) findViewById(R.id.imageViewImage);
	    photo.setImageBitmap(BitmapFactory.decodeFile(imagePath));
	}

}
