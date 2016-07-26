package com.WSZW.Activity;


import java.util.ArrayList;
import java.util.List;

import com.WSZW.adapter.ZWGK_Twocolumn_listveiw_adapter;
import com.WSZW.adapter.ZWGK_column_listveiw_adapter;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.ZWGK_FirstColumn_bean;
import com.WSZW.service.getGovInfoTypesByParentManagerService;
import com.WSZW.service.getGovInfoTypesByParentTwoManagerService;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 *表单内容
 */
public class ZWGK_TwoColumn_mainActivity extends BaseActivity implements OnClickListener{
	private LinearLayout ll_back;
	private ExpandableListView elv;
	private List<ZWGK_FirstColumn_bean> list_first;
	private List<ZWGK_FirstColumn_bean> list_two;
	private String id;
	InfoFile_ inFile_;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zwgk_content_main);
//		getin.getGovInfoTypesByParent(this,1,4521+"");
		inFile_=new InfoFile_(context);
		System.out.println("=====子菜单Activity=="+inFile_.id().get().toString());
		getint.getGovInfoTypesByParent(this,1,4521+"",inFile_.id().get().toString());
		initView();
	}

	getGovInfoTypesByParentManagerService getin =new getGovInfoTypesByParentManagerService() {
		
		
		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
			list_first=Constants.list_firstcolumn_bean;
			
		}
	};
	getGovInfoTypesByParentTwoManagerService getint =new getGovInfoTypesByParentTwoManagerService() {
		
		

		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
			list_two=Constants.list_twocolumn_bean;
			lv.setAdapter(new ZWGK_Twocolumn_listveiw_adapter(context,list_two));
			
		}
	};
	private ListView lv;
		private void initView() {
			
	    	lv = (ListView) findViewById(R.id.lv);
	    
	    	ll_back = (LinearLayout) findViewById(R.id.ll_back);
	    	ll_back.setOnClickListener(this);
	    }
	
	    @Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				this.finish();
			}
			return true;
		}


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
