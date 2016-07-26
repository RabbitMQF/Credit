package com.WSZW.Activity;

import java.util.ArrayList;
import java.util.List;
import com.WSZW.adapter.GzLm_ldzc_listveiw_item_adapter;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GWLM_DpwD_bean;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.getDocumentManagerService;
import com.WSZW.service.getDocumentsManagerService;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 领导之窗列表
 * @author Administrator
 *
 */
public class GZLM_LdZc_list_Activity extends BaseActivity implements OnClickListener{
	private ListView lv;
	private InfoFile_ infofile;
	private GWLM_DpwD_bean dpwd_bean;
	private List<GWLM_ZwDt_title> title_list;
	private List<GWLM_DpwD_bean> list_dpwd;
	private GzLm_ldzc_listveiw_item_adapter adapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gzlm_fragment_bmdt);
		infofile=new InfoFile_(context);
		String chanIds =infofile.chanIds().get();
		initView();
		list_dpwd = new ArrayList<GWLM_DpwD_bean>();
		getdocuments.getDocuments(context, 1, chanIds , 100+"", 1+"", 4+"");
		adapter= new GzLm_ldzc_listveiw_item_adapter(context, list_dpwd);
		lv.setAdapter(adapter);
	};
	@SuppressLint("WrongViewCast")
	private void initView() {
		findViewById(R.id.rl_title).setVisibility(View.VISIBLE);
		iv_back = (TextView) findViewById(R.id.iv_back);
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
					getdocument.getDocuments(context, 1, title_list.get(i).getId());
//					System.out.println("++++多少人++++"+i);
				}
				
			}
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
