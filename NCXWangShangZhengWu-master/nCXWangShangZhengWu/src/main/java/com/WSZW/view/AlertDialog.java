package com.WSZW.view;

import com.WSZW.Activity.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.TextView;

/**
 * <pre>
 * 1.自定义提醒对话框，默认隐藏两个按钮，但是标题和提示信息必显示
 * 2.当调用setEnsureText方法时显示”确认“按钮，当调用setCancelText方法时显示“取消“按钮
 * 3.提供两种监听按钮点击事件的方式，�?��是调用�?实现OnClickListener接口，另�?��是调用�?继承OnClickAdapter抽象�?
 * </pre>
 * 
 * @author yuhuihui
 * @date 2014-6-3
 */
public class AlertDialog extends Dialog {

	TextView tvTitle, tvInfo;
	Button btnEnsure, btnCancle;
	View vContent;
	OnClickListener clickListener;
	int height;
	boolean isCanceled;

	public AlertDialog(Context context) {
		this(context, R.layout.dialog_alert, R.style.dialog_alert);
	}

	private AlertDialog(Context context, int layout, int style) {
		super(context, style);
		LayoutInflater inflater = LayoutInflater.from(context);
		vContent = inflater.inflate(layout, null);
		setContentView(vContent);
		tvTitle = (TextView) vContent.findViewById(R.id.tvTitle);
		tvInfo = (TextView) vContent.findViewById(R.id.tvInfo);
		btnEnsure = (Button) vContent.findViewById(R.id.btnEnsure);
		btnCancle = (Button) vContent.findViewById(R.id.btnCancle);
		btnEnsure.setOnClickListener(listener);
		btnCancle.setOnClickListener(listener);
		btnEnsure.setVisibility(View.GONE);
		btnCancle.setVisibility(View.GONE);
	}

	@Override
	public void show() {
		WindowManager wManager = getWindow().getWindowManager();
		height = wManager.getDefaultDisplay().getHeight();
		ObjectAnimator animator = ObjectAnimator.ofFloat(vContent, "y",
				-height, 0).setDuration(1000);
		animator.setInterpolator(new BounceInterpolator());
		animator.start();
		super.show();
	}

	@Override
	public void dismiss() {
		isCanceled = true;
		ObjectAnimator animator = ObjectAnimator.ofFloat(vContent, "y", 0,
				height).setDuration(500);
		animator.addListener(animatorListener);
		animator.start();
	}

	private void trueDismiss() {
		try {
			super.dismiss();
		} catch (Exception e) {
		}
	}

	Animator.AnimatorListener animatorListener = new AnimatorListenerAdapter() {
		@Override
		public void onAnimationEnd(Animator animation) {
			super.onAnimationEnd(animation);
			if (isCanceled) {
				trueDismiss();
			}
		}
	};

	public AlertDialog setOnClickListener(OnClickListener clickListener) {
		this.clickListener = clickListener;
		return this;
	}

	/**
	 * 设置标题
	 * 
	 * @author yuhuihui
	 * @date 2014-6-3
	 */
	public AlertDialog setTitle(String title) {
		tvTitle.setText(title);
		return this;
	}

	/**
	 * 设置提醒信息
	 * 
	 * @author yuhuihui
	 * @date 2014-6-3
	 */
	public AlertDialog setMessage(String msg) {
		tvInfo.setText(msg);
		return this;
	}

	/**
	 * 设置确认按钮文本
	 * 
	 * @author yuhuihui
	 * @date 2014-6-3
	 */
	public AlertDialog setEnsureText(String text) {
		btnEnsure.setVisibility(View.VISIBLE);
		btnEnsure.setText(text);
		return this;
	}

	/**
	 * 设置取消按钮文本
	 * 
	 * @author yuhuihui
	 * @date 2014-6-3
	 */
	public AlertDialog setCancelText(String text) {
		btnCancle.setVisibility(View.VISIBLE);
		btnCancle.setText(text);
		return this;
	}

	/**
	 * 按下返回键是否取消
	 * 
	 * @author yuhuihui
	 * @date 2014-6-3
	 */
	public AlertDialog cancelable(boolean cancelable) {
		this.setCancelable(cancelable);
		return this;
	}

	View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			dismiss();
			if (clickListener != null) {
				switch (v.getId()) {
				case R.id.btnEnsure:
					clickListener.onEnsureClick(AlertDialog.this);
					break;
				case R.id.btnCancle:
					clickListener.onCancelClick(AlertDialog.this);
					break;
				}
			}
		}
	};

	public interface OnClickListener {
		void onEnsureClick(AlertDialog dialog);

		void onCancelClick(AlertDialog dialog);
	}

	public static abstract class OnClickAdapter implements OnClickListener {
		@Override
		public void onEnsureClick(AlertDialog dialog) {
		}

		@Override
		public void onCancelClick(AlertDialog dialog) {
		}
	}

}
