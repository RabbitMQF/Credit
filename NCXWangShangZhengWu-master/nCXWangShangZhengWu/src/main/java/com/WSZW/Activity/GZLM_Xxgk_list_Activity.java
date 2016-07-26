package com.WSZW.Activity;

import java.util.List;
import com.WSZW.adapter.GzLm_xxgk_listveiw_adapter;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GzLm_secondColumn_bean;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.ChannelsByParentIdManagerService;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 信息公开
 * @author Administrator
 *
 */
public class GZLM_Xxgk_list_Activity extends BaseActivity implements OnClickListener{
	private View view;

	private ListView lv;
	private InfoFile_ infofile;

	private long arg1;
//	private List<GWLM_ZwDt_title> title_list;
//	private String chanIds,pageSize, pageIndex, statusIds;
	private List<GzLm_secondColumn_bean> list;
	private GzLm_xxgk_listveiw_adapter adapter;

	private String TAG="NOT";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gzlm_fragment_bmdt);
		initView();
		String endPoint="http://117.40.244.143:8089/ecm/ws/ChannelService?wsdl";
		arg1=Long.parseLong(infofile.chanIds().get());
		Log.i(TAG, "GZLM_Xxgk_list_Activity======="+arg1);
		getchannels.getChannels(this, 1, "getChannelsByParentId", endPoint, 3, arg1 , 1);
		
	};
	@SuppressLint("WrongViewCast")
	private void initView() {
		infofile=new InfoFile_(context);
		iv_back = (TextView) findViewById(R.id.iv_back);;
		findViewById(R.id.rl_title).setVisibility(View.VISIBLE);
		iv_back.setOnClickListener(this);
		lv = (ListView)findViewById(R.id.gzlm_lv);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK){
			finish();
		}
		return true;
	}
	ChannelsByParentIdManagerService getchannels =new ChannelsByParentIdManagerService() {
		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
			list = Constants.gzlm_second_bean;
			if(list.size()==0){
				Intent intent=new Intent();
				infofile.chanDocId().put(arg1+"");
				infofile.gzlm_title().put(infofile.gzlm_title().get());
				intent.setClass(context, GZLM_Content_mainActivity.class);
				context.startActivity(intent);
			}
			adapter = new GzLm_xxgk_listveiw_adapter(context, list);
			lv.setAdapter(adapter);
		}
	};
	
	private TextView iv_back;
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.iv_back:
			this.finish();
			break;
		}
	}
}
