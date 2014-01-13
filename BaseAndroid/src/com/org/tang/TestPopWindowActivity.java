package com.org.tang;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class TestPopWindowActivity extends Activity {

    private List<StoreyImageInfo> imageList;
    private ImageButton imageButton;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.layout_pop);
	imageButton = (ImageButton) findViewById(R.id.sel_storey);
	listView = (ListView) findViewById(R.id.listView);
	imageList = new ArrayList<StoreyImageInfo>();

	for (int i = 0; i < 5; i++) {
	    StoreyImageInfo info = new StoreyImageInfo();
	    info.setImageId("");
	    info.setImagePath("");
	    info.setName("test" + i);
	    imageList.add(info);
	}

	imageButton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		if (listView != null && listView.getVisibility() == View.VISIBLE) {
		    listView.setVisibility(View.GONE);
		} else {
		    listView.setVisibility(View.VISIBLE);
		    showListView();
		}

	    }
	});
    }

    /**
     * ����¥��
     */
    private void showListView() {
	final String[] names = new String[imageList.size()];
	for (int i = 0; i < imageList.size(); i++) {
	    names[i] = imageList.get(i).getName();
	}
	System.out.println("&&&&&&&&&&&&&");
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_sel_shop_img, names);
	listView.setAdapter(adapter);
	listView.setOnItemClickListener(new OnItemClickListener() {

	    @Override
	    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Toast.makeText(TestPopWindowActivity.this, names[arg2], Toast.LENGTH_SHORT).show();
	    }
	});
    }
}
