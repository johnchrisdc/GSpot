package com.potato.gspot;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CreatePost extends Activity {

	AccountsDatabaseAdapter mh;
	
	ImageView photo;
	
	EditText title;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.post_photo);
	    
	    //Hmmm basta sa DB
	    mh =  new AccountsDatabaseAdapter(this);
	    
	    String imagePath = getIntent().getStringExtra(MainActivity.IMAGE_PATH);
	    
	    photo = (ImageView) findViewById(R.id.imageViewImage);
	    photo.setImageBitmap(BitmapFactory.decodeFile(imagePath));
	    
	    title = (EditText)findViewById(R.id.editTextTitle);	    
	    
	    // Set up the white button on the lower right corner
        // more or less with default parameter
        final ImageView fabIconNew = new ImageView(this);
        fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_okay));
        final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(this)
                .setContentView(fabIconNew)
                .build();
        
        fabIconNew.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
		//		Toast.makeText(CreatePost.this, "Gallery",	Toast.LENGTH_SHORT).show();
		//		openGallery();
				
				upload();
			}

		});
	}
	
	byte[] imgByte;
	
	private void upload(){	
		//Kunin ung drawable sa imageView mehehe
		
		BitmapDrawable getImage = (BitmapDrawable)photo.getDrawable();
		Bitmap bm = getImage.getBitmap();

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG,100,stream);

		//Si imgByte ung iInsert sa DB (Sabi ni ser)
		imgByte = stream.toByteArray();
		
		String title_post = title.getText().toString();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		String date = sdf.format(new Date()); 
		
		mh.helper.insertCoins(imgByte, title_post, date);
	}

}
