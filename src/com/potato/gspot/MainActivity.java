package com.potato.gspot;

import java.util.ArrayList;
import java.util.List;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnItemClickListener{

	public static final String[] titles = new String[]{"Dead By Sunrise","Alapaap","Iron Man","Pwede boat?","Banana","Orange","Mixed"};
	
	public static final String[] descriptions = new String[]{"Hmmmm...","Gusto mo bang sumama?","Anemic","Only one","flowering plant","citrus fruit","mixed fruits"};
	
	public static Integer[] images = {R.drawable.dummy_post_1, R.drawable.dummy_post_2, R.drawable.dummy_post_3, R.drawable.dummy_post_4, R.drawable.banana, R.drawable.orange, R.drawable.mixed};
	
	ListView listView;
	List<RowItem> rowItems;
	
	ActionBar actionBar;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        actionBar = getActionBar();
        actionBar.setTitle(null);
        
        setContentView(R.layout.activity_main);
        
        rowItems = new ArrayList<RowItem>();
        
        for(int i=0; i<titles.length; i++){
        	RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
        	rowItems.add(item);
        }
        
        listView =  (ListView) findViewById(R.id.listView);
        
        CustomListViewAdapter adapter = new CustomListViewAdapter(this, R.layout.list_item, rowItems);
        listView.setAdapter(adapter);
        
        listView.setOnItemClickListener(this);
        
        listView.setOnScrollListener(new OnScrollListener() {
            int mLastFirstVisibleItem = 0;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {   }           

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {    
                if (view.getId() == listView.getId()) {
                    final int currentFirstVisibleItem = listView.getFirstVisiblePosition();

                    if (currentFirstVisibleItem > mLastFirstVisibleItem) {
                        // getSherlockActivity().getSupportActionBar().hide();
                    	actionBar.hide();
                    } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
                        // getSherlockActivity().getSupportActionBar().show();
                    	actionBar.show();
                    }

                    mLastFirstVisibleItem = currentFirstVisibleItem;
                }               
            }
        });
        
        
     // Set up the white button on the lower right corner
        // more or less with default parameter
        final ImageView fabIconNew = new ImageView(this);
        fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_new));
        final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(this)
                .setContentView(fabIconNew)
                .build();

        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
        ImageView rlIcon1 = new ImageView(this);
        ImageView rlIcon2 = new ImageView(this);

        rlIcon1.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_camera));
        rlIcon2.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_gallery));


        
     // Build the menu with default options: light theme, 90 degrees, 72dp radius.
        // Set 4 default SubActionButtons
        final FloatingActionMenu rightLowerMenu = new FloatingActionMenu.Builder(this)
                                                .addSubActionView(rLSubBuilder.setContentView(rlIcon1).build())
                                                .addSubActionView(rLSubBuilder.setContentView(rlIcon2).build())
                                                .attachTo(rightLowerButton)
                                                .setStartAngle(180)
                                                .setEndAngle(220)
                                                .setRadius(60)
                                                .build();

        // Listen menu open and close events to animate the button content view
        rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees clockwise
                fabIconNew.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees counter-clockwise
                fabIconNew.setRotation(45);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }
        });
        
        rlIcon1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "Camera",	Toast.LENGTH_SHORT).show();
			}

		});
        
        rlIcon2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "Gallery",	Toast.LENGTH_SHORT).show();
			}

		});

    }

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		Toast t = Toast.makeText(getApplicationContext(),"Item " + (position +1) + ": " 
				+ rowItems.get(position) , Toast.LENGTH_SHORT);
		t.show();
	}
	
}
