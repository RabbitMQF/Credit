package com.WSZW.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ZMHD_Fragment_xw extends Fragment{

	public static ZMHD_Fragment_xw newInstance(String string) {
		// TODO 自动生成的方法存根
		ZMHD_Fragment_xw newfragment = new ZMHD_Fragment_xw();
		return newfragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		View view = inflater.inflate(R.layout.zmhd_framgemnt_xw, container, false);
		return view;
	}
}
