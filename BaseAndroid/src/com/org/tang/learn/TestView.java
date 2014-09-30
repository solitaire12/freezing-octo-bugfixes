package com.org.tang.learn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class TestView extends View {

	private Paint paint = new Paint();
	private Paint backGroundPaint = new Paint();

	public TestView(Context context) {
		this(context, null);
	}

	public TestView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	
	private void init(){
		backGroundPaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));
		paint.setColor(getResources().getColor(android.R.color.holo_red_dark));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		int px = getMeasuredWidth();
		int py = getMeasuredHeight();

		canvas.drawRect(0, 0, px, py, backGroundPaint);
//		canvas.save();
		canvas.rotate(90, px/2, py/2);
		canvas.drawLine(px/2, 0, 0, py/2, paint);
		canvas.drawLine(px/2, 0, px, py/2, paint);
		canvas.drawLine(px/2, 0, px/2, py, paint);
//		canvas.restore();
		canvas.drawCircle(px-10, py-10, 10, paint);
	}
}
