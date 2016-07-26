package com.WSZW.Activity;

import java.util.List;

import com.WSZW.adapter.GzLm_title_listveiw_adapter;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.getDocumentsManagerService;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class GZLM_Documents_mainActivity extends BaseActivity implements
		OnClickListener {
	private ListView lv;
	private String chanIds, pageSize, pageIndex = "1", statusIds;
	private GzLm_title_listveiw_adapter adapter;
	ProgressBar pbMore;
	TextView tvMore;
	private int indexPager = 1;// 当前页数
	private int Count = 0;// 已加载的项数
	private boolean isMore = true;
	boolean haveAddFoot = false;
	private List<GWLM_ZwDt_title> title_list;
	private ImageView iv_back;
	private View ll_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gzlm_xqgk_chanle_list);
		infoFile_ = new InfoFile_(context);
		initView();
		loadDatas(indexPager);
	}

	private void initView() {
		LayoutInflater inflater = LayoutInflater.from(this);
		LinearLayout llMore = (LinearLayout) inflater.inflate(
				R.layout.more_data_bar, null);
		pbMore = (ProgressBar) llMore.findViewById(R.id.pbMore);
		tvMore = (TextView) llMore.findViewById(R.id.tvMore);
		lv = (ListView) findViewById(R.id.gzlm_lv);
		lv.setOnScrollListener(new ScrollListener());
		iv_back = (ImageView) findViewById(R.id.iv_back);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_back.setOnClickListener(this);
	}

	private void addFoot() {
		haveAddFoot = true;
		LayoutInflater inflater = LayoutInflater.from(this);
		LinearLayout llMore = (LinearLayout) inflater.inflate(
				R.layout.more_data_bar, null);
		pbMore = (ProgressBar) llMore.findViewById(R.id.pbMore);
		tvMore = (TextView) llMore.findViewById(R.id.tvMore);
		lv.addFooterView(llMore);
	}

	private boolean loadfinish = true; // 指示数据是否加载完成

	private final class ScrollListener implements OnScrollListener {
		private int number = 10; // 每次获取多少条数据

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			final int loadtotal = totalItemCount;
			int lastItemid = lv.getLastVisiblePosition(); // 获取当前屏幕最后Item的ID
			if ((lastItemid + 1) == loadtotal) { // 达到数据的最后一条记录
				if (loadtotal > 0) {
					// 当前页
					int currentpage = loadtotal % number == 0 ? loadtotal
							/ number : loadtotal / number + 1;
					int nextpage = currentpage + 1; // 下一页
					if (loadfinish && isMore) {
						loadfinish = false;
						loadDatas(nextpage);
						System.out.println("执行了--下一页=" + nextpage);
					}
				}
			}
		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
		}
	}

	private void loadDatas(int nextpage) {
		chanIds = infoFile_.chanIds().get().toString();
		pageSize = "10";
		statusIds = "4";
		pageIndex = nextpage + "";
		System.out.println("indexpager---=" + indexPager);
		getdocuments.getDocuments(this, 1, chanIds, pageSize, pageIndex,
				statusIds);
	}

	getDocumentsManagerService getdocuments = new getDocumentsManagerService() {

		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
			loadfinish = true;
			if (handleResult.getiSuccess().equals("success")) {
				Count += Constants.COUNT_OF_LIST_ZWFW_SHIXIANG;
				if (lv.getAdapter() == null) {
					title_list = Constants.gzlm_item;
					adapter = new GzLm_title_listveiw_adapter(context,
							title_list);
					addFoot();
					lv.setAdapter(adapter);
				} else {
					title_list.addAll(Constants.gzlm_item);
					adapter.notifyDataSetChanged();
				}
			}
			tishiInfo(Constants.COUNT_OF_LIST_ZWFW_SHIXIANG, Count);
		}
	};
	private InfoFile_ infoFile_;

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
			finish();
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
