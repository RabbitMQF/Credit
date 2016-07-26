package com.WSZW.Activity;

import java.util.List;
import com.WSZW.adapter.GzLm_title_listitem_adapter;
import com.WSZW.adapter.GzLm_title_listveiw_adapter;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.getDocumentsManagerService;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 公众栏目列表
 * @author Administrator
 *
 */
public class GZLM_list_mainActivity extends BaseActivity implements OnClickListener{
	private View view;

	private ListView lv;
	private InfoFile_ infofile;
	private List<GWLM_ZwDt_title> title_list;
	private String chanIds,pageSize, pageIndex, statusIds;
	
	@SuppressLint("WrongViewCast")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gzlm_fragment_bmdt);
		infofile=new InfoFile_(context);
		lv = (ListView)findViewById(R.id.gzlm_lv);
		findViewById(R.id.rl_title).setVisibility(View.VISIBLE);
		iv_back = (TextView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(this);
		initDatas();
		
	};
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK){
			finish();
		}
		return true;
	}
	private void initDatas() {
		chanIds=infofile.chanIds().get();
		pageSize="10";
		pageIndex="1";
		statusIds="4";
		System.out.println("=============="+chanIds);
		getdocuments.getDocuments(this, 1, chanIds,pageSize, pageIndex, statusIds);
		
		
	}
	getDocumentsManagerService getdocuments=new getDocumentsManagerService() {
		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
				title_list=Constants.gzlm_item;
				GzLm_title_listitem_adapter adapter=new GzLm_title_listitem_adapter(context, title_list);
				lv.setAdapter(adapter);
		}
	};

	private TextView iv_back;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back:
				this.finish();
			break;

		default:
			break;
		}
		
	}

}
