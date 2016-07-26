package com.WSZW.Activity;

import java.util.Arrays;
import java.util.List;

import com.WSZW.adapter.GzLm_xqgk_listveiw_adapter;
import com.WSZW.data.Constants;
import com.WSZW.entity.GzLm_secondColumn_bean;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.ChannelsByParentIdManagerService;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * 县情概括
 * @author Administrator
 *
 */
public class GZLM_Fragment_xqgk extends Fragment {
	private View view;
	private List<GzLm_secondColumn_bean> list;

	private ListView lv;




	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.gzlm_fragment_bmdt, null);
		String endPoint="http://117.40.244.143:8089/ecm/ws/ChannelService?wsdl";
		getchannels.getChannels(getActivity(), 1, "getChannelsByParentId", endPoint, 3,648, 1);
		initView();
		return view;
	}

	private void initView() {
		lv = (ListView) view.findViewById(R.id.gzlm_lv);
	}

	ChannelsByParentIdManagerService getchannels =new ChannelsByParentIdManagerService() {
		

		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
			list = Constants.gzlm_second_bean;
			adapter = new GzLm_xqgk_listveiw_adapter(getActivity(), list);
			lv.setAdapter(adapter);
		}
	};
	private GzLm_xqgk_listveiw_adapter adapter;

}
