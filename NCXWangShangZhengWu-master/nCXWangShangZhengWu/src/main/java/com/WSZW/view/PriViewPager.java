package com.WSZW.view;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 自定义viewpager
 * 
 * @author yuhuihui
 * @data 2014-6-27
 * 
 */
public class PriViewPager extends ViewPager {

	private PointF downf = new PointF();

	private PointF curF = new PointF();

	private OnSingleTouchListener onSingleTouchListener;

	public PriViewPager(Context context) {
		super(context);
	}

	public PriViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		curF.x = arg0.getX();
		curF.y = arg0.getY();

		if (arg0.getAction() == MotionEvent.ACTION_DOWN) {
			downf.x = arg0.getX();
			downf.y = arg0.getY();

			getParent().requestDisallowInterceptTouchEvent(true);
		}

		if (arg0.getAction() == MotionEvent.ACTION_MOVE) {
			getParent().requestDisallowInterceptTouchEvent(true);
		}

		if (arg0.getAction() == MotionEvent.ACTION_UP) {
			if (downf.x == curF.x && downf.y == curF.y) {
				OnSingleTouch();
				return true;
			}
		}
		return super.onTouchEvent(arg0);
	}

	public void OnSingleTouch() {
		if (onSingleTouchListener != null) {
			onSingleTouchListener.onSingleTouch();
		}
	}

	public interface OnSingleTouchListener {
		public void onSingleTouch();
	}

	public void setOnSingleTouchListener(
			OnSingleTouchListener onSingleTouchListener) {
		this.onSingleTouchListener = onSingleTouchListener;
	}

}
