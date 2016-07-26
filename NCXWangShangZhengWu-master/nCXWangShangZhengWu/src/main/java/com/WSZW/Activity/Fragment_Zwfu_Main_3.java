package com.WSZW.Activity;

import com.WSZW.data.InfoFile;
import com.WSZW.data.InfoFile_;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragment_Zwfu_Main_3 extends Fragment implements OnClickListener{
	private static final String TAG = "Fragment_Zwfu_Main_3";
	InfoFile_ infofile;
	View view;
	ImageView iv_1,iv_2,iv_3,iv_4,iv_5,iv_6,iv_7;

	static Fragment_Zwfu_Main_3 newInstance(String s) {
		Fragment_Zwfu_Main_3 newFragment = new Fragment_Zwfu_Main_3();
		return newFragment;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 view = inflater.inflate(R.layout.fragment_zwfw_main_3, container, false);
		 infofile = new InfoFile_(this.getActivity());
		initView();
		return view;
	}

	private void initView() {
		iv_1 = (ImageView) view.findViewById(R.id.iv_1);
		iv_2 = (ImageView) view.findViewById(R.id.iv_2);
		iv_3 = (ImageView) view.findViewById(R.id.iv_3);
		iv_4 = (ImageView) view.findViewById(R.id.iv_4);
		iv_5 = (ImageView) view.findViewById(R.id.iv_5);
		iv_6 = (ImageView) view.findViewById(R.id.iv_6);
		iv_7 = (ImageView) view.findViewById(R.id.iv_7);
		iv_1.setOnClickListener(this);
		iv_2.setOnClickListener(this);
		iv_3.setOnClickListener(this);
		iv_4.setOnClickListener(this);
		iv_5.setOnClickListener(this);
		iv_6.setOnClickListener(this);
		iv_7.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_1:
			infofile.edit().xzzq_num().put("1").apply();
			infofile.edit().zhutiName().put("行政许可").apply();
			loadDatas();
			break;
		case R.id.iv_2:
			infofile.edit().xzzq_num().put("2").apply();
			infofile.edit().zhutiName().put("非行政许可").apply();
			loadDatas();
			break;		
		case R.id.iv_3:
			infofile.edit().xzzq_num().put("4").apply();
			infofile.edit().zhutiName().put("行政征收").apply();
			loadDatas();
			break;
		case R.id.iv_4:
			infofile.edit().xzzq_num().put("6").apply();
			infofile.edit().zhutiName().put("行政确认").apply();
			loadDatas();
			break;
		case R.id.iv_5:
			infofile.edit().xzzq_num().put("7").apply();
			infofile.edit().zhutiName().put("代理事项").apply();
			loadDatas();
			break;
		case R.id.iv_6:
			infofile.edit().xzzq_num().put("8").apply();
			infofile.edit().zhutiName().put("便民服务").apply();
			loadDatas();
			break;
		case R.id.iv_7:
			infofile.edit().xzzq_num().put("9").apply();
			infofile.edit().zhutiName().put("其他事项").apply();
			loadDatas();
			break;
		default:
			break;
		}
		
	}

	private void loadDatas() {
		this.getActivity().startActivity(new Intent(Fragment_Zwfu_Main_3.this.getActivity(),ZWFW_List_Activity.class).addFlags(1));
	}
}
