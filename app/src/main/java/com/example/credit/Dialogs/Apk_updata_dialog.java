package com.example.credit.Dialogs;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.credit.R;


public class Apk_updata_dialog extends PopupWindow {
	private Button quxiao_dialog_pays;
	public static Button del_dialog_pays;
	private View v;
	public Apk_updata_dialog(Context context, OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = inflater.inflate(R.layout.pay_dialog1, null);
		quxiao_dialog_pays = (Button) v.findViewById(R.id.quxiao_dialog_pay);
		del_dialog_pays= (Button) v.findViewById(R.id.del_dialog_pay);
		TextView dialog_ts= (TextView) v.findViewById(R.id.dialog_ts);
		dialog_ts.setText("是否更新？");
		quxiao_dialog_pays.setText("暂不更新");
		del_dialog_pays.setText("立即更新");
		del_dialog_pays.setOnClickListener(itemsOnClick);
		quxiao_dialog_pays.setOnClickListener(quxiaos);
		this.setContentView(v);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.MATCH_PARENT);
		this.setFocusable(true);
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		this.setBackgroundDrawable(dw);
		Animation anim= AnimationUtils.loadAnimation(context, R.anim.delete_update);
		v.setAnimation(anim);
	}
	OnClickListener quxiaos=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			dismiss();
		}
	};
}
