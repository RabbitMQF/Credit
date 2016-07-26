package com.WSZW.Activity;

import java.util.List;

import com.WSZW.adapter.ZMHD_ZX_listview_adapter;
import com.WSZW.data.Constants;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.ZMHD_zx_bean;
import com.WSZW.service.getQueryLetterManagerService;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class ZMHD_fragment_jy extends Fragment{
	
	private View view;
	private List<ZMHD_zx_bean> list;
	private ZMHD_ZX_listview_adapter adapter;
	private ListView lv;
	private boolean isMore = true;
	TextView tvMore;
	ProgressBar pbMore;
	private int Count = 0;// 已加载的项数
	private int indexPager = 1;// 当前页数
	boolean haveAddFoot = false;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		view = inflater.inflate(R.layout.zmhd_framgemnt_zx, container, false);
		initView();
		loadDatas(indexPager);
		return view;
	}

	public static ZMHD_fragment_jy newInstance(String string) {
		// TODO 自动生成的方法存根
		ZMHD_fragment_jy fragment = new ZMHD_fragment_jy();
		return fragment;
	}
	
	private void initView(){
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		LinearLayout llMore = (LinearLayout) inflater.inflate(
				R.layout.more_data_bar, null);
		pbMore = (ProgressBar) llMore.findViewById(R.id.pbMore);
		tvMore = (TextView) llMore.findViewById(R.id.tvMore);
		lv = (ListView) view.findViewById(R.id.lv_base);
		lv.setOnScrollListener(new ScrollListener());
	}
	private void addFoot() {
		haveAddFoot = true;
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		LinearLayout llMore = (LinearLayout) inflater.inflate(
				R.layout.more_data_bar, null);
		pbMore = (ProgressBar) llMore.findViewById(R.id.pbMore);
		tvMore = (TextView) llMore.findViewById(R.id.tvMore);
		lv.addFooterView(llMore);
	}
	private String pageIndex;
	private void loadDatas(int nextpage) {
		// TODO 自动生成的方法存根
		pageIndex=nextpage+"";
		getQueryLetter.getQueryLetter(getActivity(), 1, "1219","3",pageIndex, "10");
	}
	getQueryLetterManagerService getQueryLetter = new getQueryLetterManagerService() {

		private String TAG;

		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
			// TODO 自动生成的方法存根
			loadfinish = true;
			if(handleResult.getiSuccess() != null && handleResult.getiSuccess().equals("success")){
				Count += Constants.COUNT_OF_LIST_ZMHD;
				if(lv.getAdapter() == null){
					list = Constants.zx_item;
			        Log.i(TAG,"ssssssssss======="+list.size());
			        adapter = new ZMHD_ZX_listview_adapter(getActivity(), list);
					addFoot();
					lv.setAdapter(adapter);
				}else{
					list.addAll( Constants.zx_item);
					adapter.notifyDataSetChanged();
				}
			}
				tishiInfo(Constants.COUNT_OF_LIST_ZMHD,Count);
		
	}
};
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
						/ number
						: loadtotal / number + 1;
				int nextpage = currentpage + 1; // 下一页
				if (loadfinish && isMore) {
					loadfinish = false;
					loadDatas(nextpage);
				}
			}
		}
	}
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO 自动生成的方法存根
		
	}
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
}
