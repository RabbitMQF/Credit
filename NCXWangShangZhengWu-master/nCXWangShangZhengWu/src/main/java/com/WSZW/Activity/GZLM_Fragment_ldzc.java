package com.WSZW.Activity;

import java.util.ArrayList;
import java.util.List;

import com.WSZW.adapter.GzLm_ldzc_listveiw_adapter;
import com.WSZW.adapter.GzLm_ldzc_listveiw_item_adapter;
import com.WSZW.adapter.Zwfw_main_listview_adapter;
import com.WSZW.data.Constants;
import com.WSZW.entity.GWLM_DpwD_bean;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.GzLm_secondColumn_bean;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.Zwfw_main_listview_item;
import com.WSZW.service.ChannelsByParentIdManagerService;
import com.WSZW.service.getDocumentManagerService;
import com.WSZW.service.getDocumentsManagerService;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 领导之窗
 * @author Administrator
 *
 */
public class GZLM_Fragment_ldzc extends Fragment {
	private View view;
//	private List<GzLm_secondColumn_bean> list;
//
//	private ListView lv;
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		view = inflater.inflate(R.layout.gzlm_fragment_bmdt, null);
//		String endPoint="http://117.40.244.143:8089/ecm/ws/ChannelService?wsdl";
//		getchannels.getChannels(getActivity(), 1, "getChannelsByParentId", endPoint, 3,753 , 1);
//		initView();
//		return view;
//	}
//	private void initView() {
//		lv = (ListView) view.findViewById(R.id.gzlm_lv);
//	}
//	/**
//	 * 获取子栏目
//	 */
//	ChannelsByParentIdManagerService getchannels =new ChannelsByParentIdManagerService() {
//		
//
//		@Override
//		protected void handlerLoginInfo(Context paramActivity,
//				HandleResult handleResult, int paramInt) {
//			list = Constants.gzlm_second_bean;
//			adapter = new GzLm_ldzc_listveiw_adapter(getActivity(), list);
//			lv.setAdapter(adapter);
//		}
//	};
//	private GzLm_ldzc_listveiw_adapter adapter;
	
	
//------------------------------------------------------
	TextView tv_1, tv_2, tv_3, tv_4;
	private ListView lv;
	private GWLM_DpwD_bean dpwd_bean;
	private List<GWLM_ZwDt_title> title_list;
	private List<GWLM_DpwD_bean> list_dpwd= new ArrayList<GWLM_DpwD_bean>();;

	private GzLm_ldzc_listveiw_item_adapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.gzlm_ldzc, null);
		tv_1 = (TextView) view.findViewById(R.id.tv_1);
		tv_2 = (TextView) view.findViewById(R.id.tv_2);
		tv_3 = (TextView) view.findViewById(R.id.tv_3);
		tv_4 = (TextView) view.findViewById(R.id.tv_4);
		lv = (ListView) view.findViewById(R.id.lv_base);
		list_dpwd= new ArrayList<GWLM_DpwD_bean>();
		getdocuments.getDocuments(getActivity(), 1, 754+"" , 100+"", 1+"", 4+"");
		adapter= new GzLm_ldzc_listveiw_item_adapter(getActivity(), list_dpwd);
		lv.setAdapter(adapter);
		tv_1.setOnClickListener(onClickListener);
		tv_2.setOnClickListener(onClickListener);
		tv_3.setOnClickListener(onClickListener);
		tv_4.setOnClickListener(onClickListener);
		return view;
	}
	
	/**
	 * 获取单篇文档
	 */
	getDocumentManagerService getdocument =new getDocumentManagerService() {

		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
				dpwd_bean = Constants.gzlm_dpwd_bean;	
				list_dpwd.add(dpwd_bean);
				adapter.notifyDataSetChanged();
		}
		
	};
	/**
	 * 获取文档列表
	 */
	getDocumentsManagerService getdocuments=new getDocumentsManagerService() {
		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
			if (handleResult.getiSuccess().equals("success")) {
				title_list=Constants.gzlm_item;
				for (int i = 0; i < title_list.size(); i++) {
					getdocument.getDocuments(getActivity(), 1, title_list.get(i).getId());
//					System.out.println("++++多少人++++"+i);
				}
				
			}
		}
	};
	
	
	
	private View.OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tv_1:
				tv_1.setTextColor(GZLM_Fragment_ldzc.this.getActivity()
						.getResources().getColor(R.color.yellow));
				tv_2.setTextColor(GZLM_Fragment_ldzc.this.getActivity()
						.getResources().getColor(R.color.white_light));
				tv_3.setTextColor(GZLM_Fragment_ldzc.this.getActivity()
						.getResources().getColor(R.color.white_light));
				tv_4.setTextColor(GZLM_Fragment_ldzc.this.getActivity()
						.getResources().getColor(R.color.white_light));
				list_dpwd= new ArrayList<GWLM_DpwD_bean>();
				getdocuments.getDocuments(getActivity(), 1, 754+"" , 100+"", 1+"", 4+"");
				adapter= new GzLm_ldzc_listveiw_item_adapter(getActivity(), list_dpwd);
				lv.setAdapter(adapter);
				break;
			case R.id.tv_2:
				tv_1.setTextColor(GZLM_Fragment_ldzc.this.getActivity()
						.getResources().getColor(R.color.white_light));
				tv_2.setTextColor(GZLM_Fragment_ldzc.this.getActivity()
						.getResources().getColor(R.color.yellow));
				tv_3.setTextColor(GZLM_Fragment_ldzc.this.getActivity()
						.getResources().getColor(R.color.white_light));
				tv_4.setTextColor(GZLM_Fragment_ldzc.this.getActivity()
						.getResources().getColor(R.color.white_light));
				list_dpwd= new ArrayList<GWLM_DpwD_bean>();
				getdocuments.getDocuments(getActivity(), 1, 759+"" , 100+"", 1+"", 4+"");
				adapter= new GzLm_ldzc_listveiw_item_adapter(getActivity(), list_dpwd);
				lv.setAdapter(adapter);
				break;
			case R.id.tv_3:
				tv_1.setTextColor(GZLM_Fragment_ldzc.this.getActivity()
						.getResources().getColor(R.color.white_light));
				tv_2.setTextColor(GZLM_Fragment_ldzc.this.getActivity()
						.getResources().getColor(R.color.white_light));
				tv_3.setTextColor(GZLM_Fragment_ldzc.this.getActivity()
						.getResources().getColor(R.color.yellow));
				tv_4.setTextColor(GZLM_Fragment_ldzc.this.getActivity()
						.getResources().getColor(R.color.white_light));
				list_dpwd= new ArrayList<GWLM_DpwD_bean>();
				getdocuments.getDocuments(getActivity(), 1, 764+"" , 100+"", 1+"", 4+"");
				adapter= new GzLm_ldzc_listveiw_item_adapter(getActivity(), list_dpwd);
				lv.setAdapter(adapter);
				break;
			case R.id.tv_4:
				tv_1.setTextColor(GZLM_Fragment_ldzc.this.getActivity()
						.getResources().getColor(R.color.white_light));
				tv_2.setTextColor(GZLM_Fragment_ldzc.this.getActivity()
						.getResources().getColor(R.color.white_light));
				tv_3.setTextColor(GZLM_Fragment_ldzc.this.getActivity()
						.getResources().getColor(R.color.white_light));
				tv_4.setTextColor(GZLM_Fragment_ldzc.this.getActivity()
						.getResources().getColor(R.color.yellow));
				list_dpwd= new ArrayList<GWLM_DpwD_bean>();
				getdocuments.getDocuments(getActivity(), 1, 769+"" , 100+"", 1+"", 4+"");
				adapter= new GzLm_ldzc_listveiw_item_adapter(getActivity(), list_dpwd);
				lv.setAdapter(adapter);
				break;
			default:
				break;
			}

		}
	};
}
