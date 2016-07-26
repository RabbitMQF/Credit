package com.WSZW.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.WSZW.adapter.Zwfw_main_listview_adapter;
import com.WSZW.data.Constants;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.Zwfw_main_listview_item;
import com.WSZW.service.ZWFW_Zhuti_ManagerService;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_Zwfu_Main_1 extends Fragment {
	private ListView lv;
	Zwfw_main_listview_adapter adapter;
	private List<Zwfw_main_listview_item> datas1 = new ArrayList<Zwfw_main_listview_item>();
	private List<Zwfw_main_listview_item> datas2 = new ArrayList<Zwfw_main_listview_item>();
	private List<Zwfw_main_listview_item> datas3 = new ArrayList<Zwfw_main_listview_item>();
	TextView tv_1, tv_2, tv_3;

	static Fragment_Zwfu_Main_1 newInstance(String s) {
		Fragment_Zwfu_Main_1 newFragment = new Fragment_Zwfu_Main_1();
		return newFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_zwfu_main_1, container,
				false);
		lv = (ListView) view.findViewById(R.id.lv_base);
		datas1 = Constants.list_zwfw_zhuti_gr_ztfl;
		datas2 = Constants.list_zwfw_zhuti_gr_rssj;
		datas3 = Constants.list_zwfw_zhuti_gr_tdqt;
		adapter = new Zwfw_main_listview_adapter(this.getActivity(), datas1);
		lv.setAdapter(adapter);
		tv_1 = (TextView) view.findViewById(R.id.tv_1);
		tv_2 = (TextView) view.findViewById(R.id.tv_2);
		tv_3 = (TextView) view.findViewById(R.id.tv_3);

		tv_1.setOnClickListener(onClickListener);
		tv_2.setOnClickListener(onClickListener);
		tv_3.setOnClickListener(onClickListener);
		return view;
	}

	private View.OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tv_1:
				tv_1.setTextColor(Fragment_Zwfu_Main_1.this.getActivity()
						.getResources().getColor(R.color.yellow));
				tv_2.setTextColor(Fragment_Zwfu_Main_1.this.getActivity()
						.getResources().getColor(R.color.white_light));
				tv_3.setTextColor(Fragment_Zwfu_Main_1.this.getActivity()
						.getResources().getColor(R.color.white_light));
				adapter = new Zwfw_main_listview_adapter(
						Fragment_Zwfu_Main_1.this.getActivity(), datas1);
				lv.setAdapter(adapter);
				break;
			case R.id.tv_2:
				tv_1.setTextColor(Fragment_Zwfu_Main_1.this.getActivity()
						.getResources().getColor(R.color.white_light));
				tv_2.setTextColor(Fragment_Zwfu_Main_1.this.getActivity()
						.getResources().getColor(R.color.yellow));
				tv_3.setTextColor(Fragment_Zwfu_Main_1.this.getActivity()
						.getResources().getColor(R.color.white_light));
				adapter = new Zwfw_main_listview_adapter(
						Fragment_Zwfu_Main_1.this.getActivity(), datas2);
				lv.setAdapter(adapter);
				break;
			case R.id.tv_3:
				tv_1.setTextColor(Fragment_Zwfu_Main_1.this.getActivity()
						.getResources().getColor(R.color.white_light));
				tv_2.setTextColor(Fragment_Zwfu_Main_1.this.getActivity()
						.getResources().getColor(R.color.white_light));
				tv_3.setTextColor(Fragment_Zwfu_Main_1.this.getActivity()
						.getResources().getColor(R.color.yellow));
				adapter = new Zwfw_main_listview_adapter(
						Fragment_Zwfu_Main_1.this.getActivity(), datas3);
				lv.setAdapter(adapter);
				break;
			default:
				break;
			}

		}
	};
}
