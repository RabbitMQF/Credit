package com.WSZW.Activity;

import java.util.List;

import com.WSZW.adapter.GzLm_title_listveiw_adapter;
import com.WSZW.adapter.ZWGK_Formcontent_listveiw_adapter;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.ZWGK_formcontent_bean;
import com.WSZW.service.getGovInfoDocumentsManagerService;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

/**
 *信息内容表单
 */
public class ZWGK_formContent_mainActivity extends BaseActivity implements OnClickListener{
	private LinearLayout ll_back;
//	private ExpandableListView elv;
	private ZWGK_Formcontent_listveiw_adapter adapter;
	private List<ZWGK_formcontent_bean> list;
	private String id;
	InfoFile_ inFile_;
	private String pageIndex="1";
	private String TAG="NNN";

	
	ProgressBar pbMore;
	TextView tvMore;
	private int indexPager = 1;// 当前页数
	private int Count = 0;// 已加载的项数
	private boolean isMore = true;
	boolean haveAddFoot = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zwgk_formcontent_main);
		inFile_=new InfoFile_(context);
		Log.i(TAG, "信息内容表单organId+++"+inFile_.organId().get().toString());
		Log.i(TAG, "信息内容表单id+++"+inFile_.id().get().toString());
//		getgo.getGovInfoDocuments(this, 1,inFile_.organId().get().toString(),inFile_.id().get().toString(), 1+"", 10+"");
//		getgo.getGovInfoDocuments(this, 1, 4521+"", 247+"", 1+"", 10+"");
		initView();
		loadDatas(indexPager);
	}
	
getGovInfoDocumentsManagerService getgo=new getGovInfoDocumentsManagerService() {


		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
			loadfinish = true;
			if (handleResult.getiSuccess() != null && handleResult.getiSuccess().equals("success")) {
				Count += Constants.COUNT_OF_LIST_ZWFW_SHIXIANG;
				if (lv.getAdapter() == null) {
					list=Constants.list_formcontent_bean;
			        Log.i(TAG,"ssssssssss======="+list.size());
					adapter = new ZWGK_Formcontent_listveiw_adapter(context,list);
					addFoot();
					lv.setAdapter(adapter);
				}else{
					list.addAll( Constants.list_formcontent_bean);
					adapter.notifyDataSetChanged();
				}
			}
			tishiInfo(Constants.COUNT_OF_LIST_ZWFW_SHIXIANG,Count);
		}
	};
	private ListView lv;
		private void initView() {
			
			LayoutInflater inflater = LayoutInflater.from(context);
			LinearLayout llMore = (LinearLayout) inflater.inflate(
					R.layout.more_data_bar, null);
			pbMore = (ProgressBar) llMore.findViewById(R.id.pbMore);
			tvMore = (TextView) llMore.findViewById(R.id.tvMore);
			
			lv=(ListView) findViewById(R.id.listView1);
//			tv.setVisibility(View.VISIBLE);
//			tv.setVisibility(View.GONE);
			lv.setOnScrollListener(new ScrollListener());
	    	ll_back = (LinearLayout) findViewById(R.id.ll_back);
	    	ll_back.setOnClickListener(this);
	    }
		private void addFoot() {
			haveAddFoot = true;
			LayoutInflater inflater = LayoutInflater.from(context);
			LinearLayout llMore = (LinearLayout) inflater.inflate(
					R.layout.more_data_bar, null);
			pbMore = (ProgressBar) llMore.findViewById(R.id.pbMore);
			tvMore = (TextView) llMore.findViewById(R.id.tvMore);
			lv.addFooterView(llMore);
		}
		
		private boolean loadfinish = true; // 指示数据是否加载完成
	
		private final class ScrollListener implements OnScrollListener {
			private int number = 11; // 每次获取多少条数据

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				final int loadtotal = totalItemCount ;
				int lastItemid = lv.getLastVisiblePosition(); // 获取当前屏幕最后Item的ID
				if ((lastItemid + 1) == loadtotal) { // 达到数据的最后一条记录
					if (loadtotal > 0) {
						// 当前页
						int currentpage = loadtotal % number == 0 ? loadtotal
								/ number
								: loadtotal / number + 1;
						int nextpage = currentpage + 1; // 下一页
						if (loadfinish && isMore) {
							loadfinish = false;
							loadDatas(nextpage);
							System.out.println("执行了--下一页="+nextpage);
						}
					}
				}
			}
		
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO 自动生成的方法存根
				
			}
		}
		private void loadDatas(int nextpage) {
			pageIndex=nextpage+"";
			System.out.println("indexpager---="+indexPager);
			getgo.getGovInfoDocuments(this, 1,inFile_.organId().get().toString(),inFile_.id().get().toString(), pageIndex, 10+"");
		}
		public void tishiInfo(int size1, int count1) {
			if (size1 < Constants.PAGE_SIZE_LARGE) {// 当后台加载的数据小于十条的时候
				isMore = false;// 表示没满
			} else {
				isMore = true;// 否则表示满了
			}
			tvMore.setText(size1 < Constants.PAGE_SIZE_LARGE ? "共 " + count1
					+ " 条记录" : "更多记录...");
			pbMore.setVisibility(View.GONE);// 隐藏圆形进度条
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
