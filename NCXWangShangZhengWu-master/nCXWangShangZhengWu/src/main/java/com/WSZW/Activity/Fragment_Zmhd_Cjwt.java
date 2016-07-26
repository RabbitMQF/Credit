package com.WSZW.Activity;

import java.util.List;

import com.WSZW.adapter.Gzlm_rsxx_listview_adapter;
import com.WSZW.adapter.Zmhd_cjwt_listview_adapter;
import com.WSZW.data.Constants;
import com.WSZW.entity.GzLm_secondColumn_bean;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.ChannelsByParentIdManagerService;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Fragment_Zmhd_Cjwt extends Fragment {
	View view;
	ListView lv;
	private List<GzLm_secondColumn_bean> list;
	Zmhd_cjwt_listview_adapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 view = inflater.inflate(R.layout.fragment_zmhd_cjwt, container,
				false);
		 String endPoint = "http://117.40.244.143:8089/ecm/ws/ChannelService?wsdl";
			getchannels.getChannels(getActivity(), 1, "getChannelsByParentId", endPoint, 3,1186, 1);
			initView();
		 return view;
	}
	
	private void initView() {
		lv = (ListView) view.findViewById(R.id.lv_cjwt);
	}
	
	ChannelsByParentIdManagerService getchannels = new ChannelsByParentIdManagerService() {
		
		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
			// TODO 自动生成的方法存根
			list = Constants.gzlm_second_bean;
			adapter = new Zmhd_cjwt_listview_adapter(getActivity(), list);
			lv.setAdapter(adapter);
		}
	};
}
