package com.WSZW.Activity;


import java.util.Arrays;
import java.util.List;

import com.WSZW.adapter.ZwGk_GridView_adapter;
import com.WSZW.entity.HandleResult;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class Fragment_ZwGk_Main_2 extends Fragment {


	private View view;
	private GridView gv;
	private List list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_zwgk_main_2, container,
				false);
		initView();
		String [] str={
		/*"县政府办",*/ "县人社局", "县文广局", "县科技局", "县档案局", 
		"县供销社", "县公安局", "县国土局", "县卫生局", "县气象局", 
		/*"县畜牧水产局",*/"县司法局", "县城建局", "县商务局", "县房管局", 
		/*"县国税局",*/"县教体局", "县残联 ",/*"县地税局",*/ "县城管局", 
		"县农业局", /*"县农开办",*/ "县统计局", /*"县法制办",*/ /*"县计生委",*/ 
		/*"县质监局",*/ "县林业局", /*"县药监局", */"县水务局", "县审计局", 
		"县工信委", "县农机局", /*"县行政服务中心",*/ /*"县信访局", *//*"人民银行南昌县分行", */
		/*"县公路分局","县湖管局","供电公司", "县邮政局","县监察局", 
		"县工商局","县民宗局",*/ "县市监局","县交通局 ","县财政局", "县发改委", 
		"县烟草局", "县粮食局", "县安监局","县环保局", "县民政局" 
		};

//		莲塘镇 小蓝经开区 冈上镇 黄马乡 向塘镇 
//		广福镇 塔城乡 蒋巷镇 三江镇 富山乡 
//		幽兰镇 泾口乡 东新乡 塘南镇 南新乡 
//		八一乡 银三角管委会 武阳镇 

	
//		7-31日前版本
//		String [] str={
//		"县政府办", "县人社局", "县文广局", "县科技局", "县档案局", 
//		"县供销社", "县公安局", "县国土局", "县卫生局", "县气象局", 
//		/*"县畜牧水产局",*/"县司法局", "县城建局", "县商务局", "县房管局", 
//		/*"县国税局",*/"县教体局", "县残联 ","县地税局","县城管局", 
//		"县农业局", "县农开办","县统计局", "县法制办",/*"县计生委",*/
//		"县质监局", "县林业局", "县药监局", "县水务局", "县审计局", 
//		"县工信委", "县农机局", "县行政服务中心","县信访局", "人民银行南昌县分行", 
//		"县公路分局","县湖管局","供电公司", "县邮政局","县监察局", 
//		"县工商局","县民宗局","县交通局 ","县财政局", "县发改委", 
//		"县烟草局", "县粮食局", "县安监局","县环保局", "县民政局" 
//		};

		
//		String [] str={
//				"县政府办"," 县发改委","县工信委","县计生委","县教体局","县公安局","县司法局","县民政局","县财政局",
//				"县审计局","县统计局","县人社局","县国土局","县城建局","县环保局","县交通局","县水务局","县农业局",
//				"县文广局","县卫生局","县商务局","县安监局","县民宗局","县药监局","县城管局","县科技局",
//				"县气象局 ","县房管局","县粮食局","县工商局","县林业局","县地税局","县档案局","县畜牧水产局",
//				"县国税局 ","县烟草局","县农机局","县质监局","县残联","县供销社","县委党校","县金融办" 
//		};
		list = Arrays.asList(str);
		ZwGk_GridView_adapter adapter=new ZwGk_GridView_adapter(getActivity(), list);
		gv.setAdapter(adapter);
		return view;
	}

	
	private void initView() {
		gv = (GridView) view.findViewById(R.id.gridView1);
		gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
	}	
}
