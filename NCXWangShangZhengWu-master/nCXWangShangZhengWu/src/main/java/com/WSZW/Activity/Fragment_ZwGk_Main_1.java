package com.WSZW.Activity;


import com.WSZW.entity.HandleResult;
import com.WSZW.service.getQueryLetterManagerService;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_ZwGk_Main_1 extends Fragment {


	private View view;
	private TextView tv_content;

	static Fragment_ZwGk_Main_1 newInstance(String s) {
		Fragment_ZwGk_Main_1 newFragment = new Fragment_ZwGk_Main_1();
		return newFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_zwgk_main_1, container,
				false);
		initView();
		return view;
	}
	private void initView() {
		tv_content = (TextView) view.findViewById(R.id.tv_content);
	}
}
