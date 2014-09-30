package com.org.tang.learn;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundImage extends ImageView {
	private static final Xfermode MASK_XFERMODE;
	private Bitmap mask;
	private Paint paint;

	static {
		PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
		MASK_XFERMODE = new PorterDuffXfermode(localMode);
	}

	public RoundImage(Context paramContext) {
		super(paramContext);
		init();
	}

	public RoundImage(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		init();
	}

	public RoundImage(Context paramContext, AttributeSet paramAttributeSet,
			int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		init();
	}

	private void init() {
		paint = new Paint();
		paint.setFilterBitmap(false);
		Xfermode localXfermode1 = MASK_XFERMODE;
		paint.setXfermode(localXfermode1);
	}

	@Override
	protected void onDraw(Canvas paramCanvas) {
		Drawable localDrawable = getDrawable();

		int width = getWidth();
		int height = getHeight();
		int i = paramCanvas.saveLayer(0.0F, 0.0F, width, height, null,Canvas.ALL_SAVE_FLAG);
////		paramCanvas.save();
		localDrawable.setBounds(0, 0, width, height);
		localDrawable.draw(paramCanvas);
		mask = createMask();
		paramCanvas.drawBitmap(mask, 0.0F, 0.0F, paint);
		paramCanvas.restoreToCount(i);
//		paramCanvas.restore();
		return;
	}

	public Bitmap createMask() {
		int i = getWidth();
		int j = getHeight();
		Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;
		Bitmap localBitmap = Bitmap.createBitmap(i, j, localConfig);
		Canvas localCanvas = new Canvas(localBitmap);
		Paint localPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//		localPaint.setColor(-16777216);
		localPaint.setColor(0x88888888);
//		localPaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));
		float f1 = getWidth();
		float f2 = getHeight();
		RectF localRectF = new RectF(0.0F, 0.0F, f1, f2);
		localCanvas.drawOval(localRectF, localPaint);
		return localBitmap;
	}
}
