package com.org.tang;

import com.org.tang.learn.CircularImage;

import android.app.Activity;
import android.os.Bundle;

public class DemoSimpleActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo_simple);
		
//		CircularImage circular_image = (CircularImage)findViewById(R.id.circular_image);
//		circular_image.setImageDrawable(getResources().getDrawable(R.drawable.earth));
	}

}
