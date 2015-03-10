package com.potato.gspot;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		Toast t = Toast.makeText(getApplicationContext(),"Item " + (position +1) + ": " 
				+ rowItems.get(position) , Toast.LENGTH_SHORT);
		t.show();
	}
	
}
