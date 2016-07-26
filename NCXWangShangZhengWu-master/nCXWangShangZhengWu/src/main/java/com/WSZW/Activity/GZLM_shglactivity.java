package com.WSZW.Activity;

import java.util.List;

import com.WSZW.adapter.GzLm_xqgk_cnnj_listveiw_adapter;
import com.WSZW.adapter.Gzlm_shgl_listview_adapter;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GzLm_secondColumn_bean;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.ChannelsByParentIdManagerService;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class GZLM_shglactivity extends BaseActivity implements OnClickListener{
	private ListView lv;
	private InfoFile_ infofile;
	private ImageView iv_back;
	private LinearLayout ll_back;
	private List<GzLm_secondColumn_bean> list;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO 自动生成的方法存根
		setContentView(R.layout.gzlm_xqgk_chanle_list);
		initView();
		String endPoint="http://117.40.244.143:8089/ecm/ws/ChannelService?wsdl";
//		System.out.println("chanIds"+"===="+infofile.chanIds().toString());
//		arg = Long.parseLong(infofile.chanIds().toString());
		initView();
		getchannels.getChannels(context, 1, "getChannelsByParentId", endPoint, 3,793, 1);
	}

	private void initView() {
		iv_back = (ImageView) findViewById(R.id.iv_back);
    	ll_back = (LinearLayout) findViewById(R.id.ll_back);
		lv = (ListView) findViewById(R.id.gzlm_lv);
		ll_back.setOnClickListener(this);
	}

	ChannelsByParentIdManagerService getchannels =new ChannelsByParentIdManagerService() {
		


		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
			list = Constants.gzlm_second_bean;
			adapter = new GzLm_xqgk_cnnj_listveiw_adapter(context, list);
			lv.setAdapter(adapter);
		}
	};
	private GzLm_xqgk_cnnj_listveiw_adapter adapter;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK){
			finish();
		}
		return true;
	}
	

	private long arg;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_back:
			this.finish();
			break;

		default:
			break;
		}
		
	}

}
