package com.WSZW.Activity;


import java.util.Arrays;
import java.util.List;

import com.WSZW.adapter.ZWGK_XZXXGK_adapter;
import com.WSZW.adapter.ZwGk_GridView_adapter;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class Fragment_ZwGk_Main_3 extends Fragment {


	private View view;
	private GridView gv;
	private List list;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_zwgk_main_3, container,
				false);
	initView();
		String [] str={
				"莲塘镇","小蓝经开区","冈上镇","黄马乡","向塘镇" 
				,"广福镇","塔城乡","蒋巷镇","三江镇","富山乡", 
				"幽兰镇", "泾口乡","东新乡","塘南镇","南新乡", 
				"八一乡", "银三角管委会","武阳镇" 
		};
		list = Arrays.asList(str);
		ZWGK_XZXXGK_adapter adapter=new ZWGK_XZXXGK_adapter(getActivity(), list);
		gv.setAdapter(adapter);
		return view;
	}

	private void initView() {
		gv = (GridView) view.findViewById(R.id.gridView1);
		gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
	}	
}
