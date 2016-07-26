package com.WSZW.view;

import com.WSZW.Activity.R;
import com.WSZW.util.StringUtil;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 自定义加载对话框
 * 
 * @author yuhuihui
 * @date 2014-6-3
 */
public class LoadingDialog extends Dialog {

	TextView tvTip;
	ImageView ivLoading;
	ObjectAnimator animator;

	/**
	 * 创建加载对话框，并立刻显示
	 * 
	 * @param msg
	 *            显示的消息，为空时，显示默认的消息
	 * @param cancelable
	 *            按返回键能否取消
	 * @author yuhuihui
	 * @date 2014-6-3
	 */
	public LoadingDialog(Context context, String msg, boolean cancelable) {
		super(context, R.style.dialog_loading);
		LayoutInflater inflater = LayoutInflater.from(context);
		View vContent = inflater.inflate(R.layout.dialog_loading, null);
		setContentView(vContent);

		ivLoading = (ImageView) vContent.findViewById(R.id.ivLoading);
		animator = ObjectAnimator.ofFloat(ivLoading, "rotation", 0, 360)
				.setDuration(800);
		animator.setInterpolator(new LinearInterpolator());
		animator.setRepeatCount(50);
		animator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				dismiss();
			}
		});

		tvTip = (TextView) vContent.findViewById(R.id.tvTip);// 提示文字
		if (StringUtil.isNotTrimBlank(msg)) {
			tvTip.setText(msg);// 设置加载信息
		}
		setCancelable(cancelable);
		show();
	}

	public LoadingDialog setMessage(String msg) {
		if (StringUtil.isNotTrimBlank(msg)) {
			tvTip.setText(msg);
		} else {
			tvTip.setText("数据加载中..");
		}
		return this;
	}

	@Override
	public void show() {
		animator.start();
		super.show();
	}

	@Override
	public void dismiss() {
		try {
			super.dismiss();
			animator.cancel();
			ivLoading.clearAnimation();
		} catch (Exception e) {
		}
	}
}
