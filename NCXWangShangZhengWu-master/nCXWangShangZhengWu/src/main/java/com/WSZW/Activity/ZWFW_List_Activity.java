package com.WSZW.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.WSZW.adapter.Zwfw_ShiXiang_List_adapter;
import com.WSZW.adapter.Zwfw_main_listview_adapter;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.Zwfw_Shixiang_List_Item;
import com.WSZW.service.ZWFW_Shixiang_List_ManagerService;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class ZWFW_List_Activity extends Activity {
	InfoFile_ infofile;
	ListView lv_base;
	TextView title;
	Button btn_back;
	List<Zwfw_Shixiang_List_Item> list = new ArrayList<Zwfw_Shixiang_List_Item>();

	Zwfw_ShiXiang_List_adapter adapter;

	ProgressBar pbMore;
	TextView tvMore;
	private int indexPager = 1;// 当前页数
	private boolean isMore = true;
	private int lastItem;// 最后一项的下标
	private int Count = 0;// 已加载的项数

	int type = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zwfw_shixiang_list);
		infofile = new InfoFile_(this);
		type = getIntent().getFlags();
		lv_base = (ListView) findViewById(R.id.lv_base);
		title = (TextView) findViewById(R.id.tv_title);
		title.setText(infofile.zhutiName().get());
		btn_back = (Button) findViewById(R.id.ib_back);
		
		loadDatas(indexPager);
		
		lv_base.setOnScrollListener(new ScrollListener());
		
		btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ZWFW_List_Activity.this.finish();
			}
		});
	}

	private void loadDatas(int page) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		String id = infofile.zhutiID().get();
		map.put("catalogId", "");
		map.put("adminOrgId", "");
		map.put("xzcfAdminOrgId", "");
		map.put("serviceSubjectName", "");
		map.put("pageSize", String.valueOf(page));
		map.put("pageCount", "10");
		map.put("xzzqCode", "");
		switch (type) {
		case 0:								//个人企业
			map.put("catalogId", id);
			Constants.TYPE_IF_XZCF = 0;
			break;
		case 1:								//行政职权
			map.put("xzzqCode", infofile.xzzq_num().get() != null ? infofile.xzzq_num().get():"");
			Constants.TYPE_IF_XZCF = 0;
			break;
		case 2:								//部门服务
			map.put("adminOrgId", id);
			Constants.TYPE_IF_XZCF = 0;
			break;
		case 3:								//行政处罚
			map.put("xzcfAdminOrgId", id);
			Constants.TYPE_IF_XZCF = 1;
			break;
		default:
			break;
		}
		list.add(map);
		SX_managerservice.get_ZWFW_Shixiang_list(this, 1, list);		
	}

	private void addFoot() {
		LayoutInflater inflater = LayoutInflater.from(this);
		LinearLayout llMore = (LinearLayout) inflater.inflate(
				R.layout.more_data_bar, null);
		pbMore = (ProgressBar) llMore.findViewById(R.id.pbMore);
		tvMore = (TextView) llMore.findViewById(R.id.tvMore);
		lv_base.addFooterView(llMore);
	}

	private boolean loadfinish = true; // 指示数据是否加载完成

	private final class ScrollListener implements OnScrollListener {
		private int number = 10; // 每次获取多少条数据

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			final int loadtotal = totalItemCount;
			int lastItemid = lv_base.getLastVisiblePosition(); // 获取当前屏幕最后Item的ID
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
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	private ZWFW_Shixiang_List_ManagerService SX_managerservice = new ZWFW_Shixiang_List_ManagerService() {

		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
			loadfinish = true;
			if (paramHandleResult.getiSuccess().equals("success")) {
				Count += Constants.COUNT_OF_LIST_ZWFW_SHIXIANG;
				if (lv_base.getAdapter() == null) {
					list = Constants.list_zwfw_shixiang;
					adapter = new Zwfw_ShiXiang_List_adapter(
							ZWFW_List_Activity.this, list);
					addFoot();
					lv_base.setAdapter(adapter);
				}else{
					list.addAll(Constants.list_zwfw_shixiang);
					adapter.notifyDataSetChanged();
					
				}
				tishiInfo(Constants.COUNT_OF_LIST_ZWFW_SHIXIANG,Count);
			}
		}
	};

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
