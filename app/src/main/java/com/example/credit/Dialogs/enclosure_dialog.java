package com.example.credit.Dialogs;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.credit.R;

/**
 * 选取附件弹窗PopupWindow;
 */
public class enclosure_dialog extends PopupWindow {
	private View v;
	public enclosure_dialog(Context context, OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = inflater.inflate(R.layout.pay_dialog, null);
		TextView dialog_bd= (TextView) v.findViewById(R.id.dialog_bd);//本地选取图片
		TextView dialog_zx= (TextView) v.findViewById(R.id.dialog_zx);//照相选取图片
		dialog_bd.setOnClickListener(itemsOnClick);
		dialog_zx.setOnClickListener(itemsOnClick);
		this.setContentView(v);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.MATCH_PARENT);
		this.setFocusable(true);
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		this.setBackgroundDrawable(dw);
		Animation anim= AnimationUtils.loadAnimation(context, R.anim.enclosure);
		v.setAnimation(anim);
	}

}
