package com.WSZW.Activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.WSZW.adapter.GzLm_title_listveiw_adapter;
import com.WSZW.adapter.MyPagerAdapter;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.getDocumentsManagerService;
import com.WSZW.util.CommUtil;
import com.WSZW.view.PriViewPager;
import com.WSZW.view.PriViewPager.OnSingleTouchListener;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Toast;

public class GZLM_Fragment_zwdt extends Fragment implements OnClickListener {
	private View view, v1, v2, v3, v4, v5;
	private SearchView sv;
	private PriViewPager vp;
	private TextView tv;
	private ListView lv;
	ProgressBar pbMore;
	TextView tvMore;
	private int indexPager = 1;// 当前页数
	private int Count = 0;// 已加载的项数
	private boolean isMore = true;
	boolean haveAddFoot = false;
	private String[] titlesRes;
	private LinearLayout llPoints;
	private int prePosition = 0;// 记录前一个小圆点的位置
	private List<View> list;
	private List<GWLM_ZwDt_title> title_list;
	private MyPagerAdapter adapter;
	private GzLm_title_listveiw_adapter adapter1;
	private boolean isLoop = true;
	private String chanIds, pageSize, pageIndex, statusIds;
	private ImageView iv1, iv2, iv3, iv4, iv5;
	private int position1;
	private InfoFile_ infofile;
	List<Map<String, Object>> list_flash_contents_datas = null;
	Bitmap b1 = null, b2 = null, b3 = null, b4 = null, b5 = null;
	WebView mWebView;
	LinearLayout ll_pg;

	List<GWLM_ZwDt_title> list_zwdt;// 政务动态前五条数据

	List<GWLM_ZwDt_title> list_mtjj;// 媒体聚焦前五条数据

	/**
	 * 图片对应的文字
	 * 
	 * @return
	 */

	private String[] getTitlesRes() {
		String[] titlesRes = new String[] { "县十五届人大五次会议隆重开幕",
				"我县召开创建全国县级文明城市动员大会", "县十五届人大五次会议胜利闭幕", "县十五届人大五次会议举行第二次全体会议",
				"县十五届人大五次会议隆重开幕" };
		return titlesRes;
	}

	// private String[] getTitlesRes() {
	// if (list_flash_contents_datas != null
	// && list_flash_contents_datas.size() == 5) {
	//
	// return new String[] {
	// (String) list_flash_contents_datas.get(0).get("title"),
	// (String) list_flash_contents_datas.get(1).get("title"),
	// (String) list_flash_contents_datas.get(2).get("title"),
	// (String) list_flash_contents_datas.get(3).get("title"),
	// (String) list_flash_contents_datas.get(4).get("title") };
	// }
	// return new String[] { "数据加载中···", "数据加载中···", "数据加载中···", "数据加载中···",
	// "数据加载中···" };
	// }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.gzlm_fragment_1, null);
		v1 = inflater.inflate(R.layout.viewpager_view1, null);
		v2 = inflater.inflate(R.layout.viewpager_view2, null);
		v3 = inflater.inflate(R.layout.viewpager_view3, null);
		v4 = inflater.inflate(R.layout.viewpager_view4, null);
		v5 = inflater.inflate(R.layout.viewpager_view5, null);
		infofile = new InfoFile_(getActivity());
		initView();
		loadDatas(indexPager);
		initDatas();
		return view;
	}

	@SuppressWarnings("deprecation")
	private void initView() {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		LinearLayout llMore = (LinearLayout) inflater.inflate(
				R.layout.more_data_bar, null);

		pbMore = (ProgressBar) llMore.findViewById(R.id.pbMore);
		tvMore = (TextView) llMore.findViewById(R.id.tvMore);
		vp = (PriViewPager) view.findViewById(R.id.gzlm_vp);
		ll_pg = (LinearLayout) view.findViewById(R.id.pg);
		tv = (TextView) view.findViewById(R.id.gzlm_vp_tv);
		tv.setSingleLine();
		tv.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
		lv = (ListView) view.findViewById(R.id.gzlm_lv);
		llPoints = (LinearLayout) view.findViewById(R.id.ll_points);
		lv.setOnScrollListener(new ScrollListener());
		iv1 = (ImageView) v1.findViewById(R.id.iv1);
		iv2 = (ImageView) v2.findViewById(R.id.iv2);
		iv3 = (ImageView) v3.findViewById(R.id.iv3);
		iv4 = (ImageView) v4.findViewById(R.id.iv4);
		iv5 = (ImageView) v5.findViewById(R.id.iv5);
		iv1.setOnClickListener(this);
		iv2.setOnClickListener(this);
		iv3.setOnClickListener(this);
		iv4.setOnClickListener(this);
		iv5.setOnClickListener(this);

	}

	private Handler callbackHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1314:
				/*
				 * iv1.setBackgroundResource(R.drawable.nan1);
				 * 
				 * iv2.setBackgroundResource(R.drawable.nan2);
				 * iv3.setBackgroundResource(R.drawable.nan3);
				 * iv4.setBackgroundResource(R.drawable.nan4);
				 * iv5.setBackgroundResource(R.drawable.nan5);
				 */
				iv1.setImageBitmap(b1);
				iv2.setImageBitmap(b2);
				iv3.setImageBitmap(b3);
				iv4.setImageBitmap(b4);
				iv5.setImageBitmap(b5);
				break;
			}
		};
	};

	private void addFoot() {
		haveAddFoot = true;
		LayoutInflater inflater = LayoutInflater.from(getActivity());
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
			// TODO 自动生成的方法存根
		}
	}

	private void loadDatas(int nextpage) {
		chanIds = "738";
		pageSize = "10";
		statusIds = "4";
		pageIndex = nextpage + "";
		System.out.println("indexpager---=" + indexPager);
		getdocuments.getDocuments(getActivity(), 1, chanIds, pageSize,
				pageIndex, statusIds);
	}

	// 添加
	getDocumentsManagerService getdocuments = new getDocumentsManagerService() {
		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
			switch (paramInt) {
			case 1:
				loadfinish = true;
				if (handleResult.getiSuccess().equals("success")) {
					Count += Constants.COUNT_OF_LIST_ZWFW_SHIXIANG;

					if (lv.getAdapter() == null) {
						title_list = Constants.gzlm_item;
						adapter1 = new GzLm_title_listveiw_adapter(
								getActivity(), title_list);
						addFoot();
						lv.setAdapter(adapter1);
					} else {
						title_list.addAll(Constants.gzlm_item);
						adapter1.notifyDataSetChanged();
					}
				}
				tishiInfo(Constants.COUNT_OF_LIST_ZWFW_SHIXIANG, Count);
				if (pageIndex.equals("1")) {
					getFlashDatas();
				}
				break;
			case 2:
				list_zwdt = Constants.gzlm_item;
				getdocuments.getDocumentsForFlash(getActivity(), 3, "742", "5",
						"1", "4");

				break;
			case 3:
				list_mtjj = Constants.gzlm_item;
				list_mtjj.addAll(list_zwdt);
				for (int i = 0; i < list_mtjj.size(); i++) {
					String str = list_mtjj.get(i).getWriteTime();
					str = str.replace("T", " ").replace("+08:00", "");
					list_mtjj.get(i).setWriteTime(str);
				}
				Collections.sort(list_mtjj, new Comparator<GWLM_ZwDt_title>() {
					/**
					 * 
					 * @param lhs
					 * @param rhs
					 * @return an integer < 0 if lhs is less than rhs, 0 if they
					 *         are equal, and > 0 if lhs is greater than
					 *         rhs,比较数据大小时,这里比的是时间
					 */
					@Override
					public int compare(GWLM_ZwDt_title lhs, GWLM_ZwDt_title rhs) {
						Date date1 = CommUtil.stringToDate(lhs.getWriteTime());
						Date date2 = CommUtil.stringToDate(rhs.getWriteTime());
						// 对日期字段进行升序，如果欲降序可采用after方法
						if (date1.before(date2)) {
							return 1;
						}
						return -1;
					}
				});
				getPics();

				break;
			default:
				break;
			}

		}

	};

	private void getPics() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (list_mtjj != null && list_mtjj.size() > 5) {
					String urlHead = "http://www.ncx.gov.cn";
					b1 = CommUtil.getHttpBitmap(urlHead
							+ list_mtjj.get(0).getPicUrl());
					b2 = CommUtil.getHttpBitmap(urlHead
							+ list_mtjj.get(1).getPicUrl());
					b3 = CommUtil.getHttpBitmap(urlHead
							+ list_mtjj.get(2).getPicUrl());
					b4 = CommUtil.getHttpBitmap(urlHead
							+ list_mtjj.get(3).getPicUrl());
					b5 = CommUtil.getHttpBitmap(urlHead
							+ list_mtjj.get(4).getPicUrl());
					callbackHandler.sendEmptyMessage(1314);
					handler.sendEmptyMessage(1);
				} else {
					Toast.makeText(getActivity(), "获取图片失败！", Toast.LENGTH_SHORT)
							.show();
				}

			}
		}).start();

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

	// 调用

	// 设置Viewpager图片的监听

	private void initDatas() {
		// // 得到图片数组
		// int[] imagesRes = getImagesRes();
		// 得到标题数组
		titlesRes = getTitlesRes();
		list = new ArrayList<View>();
		list.add(v1);
		list.add(v2);
		list.add(v3);
		list.add(v4);
		list.add(v5);
		for (int i = 0; i < list.size(); i++) {
			// 创建小圆点
			View view = new View(getActivity());
			// 设置小圆点的宽高
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					10, 10);
			// 设置小圆点距左边多打距离
			params.leftMargin = 5;
			// 设置小圆点的布局参数
			view.setLayoutParams(params);
			view.setBackgroundResource(R.drawable.dot_normal);
			llPoints.addView(view);
		}
		adapter = new MyPagerAdapter(getActivity(), list);
		vp.setAdapter(adapter);

		// 设置标题的默认值
		// llPoints.getChildAt(0).setBackgroundResource(R.drawable.dot_enable);
		vp.setOnPageChangeListener(new OnPageChangeListener() {

			// 当一个页面被选择调用
			@Override
			public void onPageSelected(int position) {
				position1 = position;
				tv.setText(titlesRes[position]);
				// 设置前一个点的背景图
				llPoints.getChildAt(prePosition).setBackgroundResource(
						R.drawable.dot_normal);
				llPoints.getChildAt(position % list.size())
						.setBackgroundResource(R.drawable.dot_enable);
				prePosition = position % list.size();
			}

			// 当前页面滚动的时候调用
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
			}

			// 当滚动状态改变了会调用
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
		});
		vp.setOnSingleTouchListener(new OnSingleTouchListener() {

			@Override
			public void onSingleTouch() {
				Intent intent = new Intent();
				if (position1 == 0) {
					intent.setClass(getActivity(),
							GzLm_pictureContent_Activity1.class);
					startActivity(intent);
				} else if (position1 == 1) {
					intent.setClass(getActivity(),
							GzLm_pictureContent_Activity2.class);
					startActivity(intent);
				} else if (position1 == 2) {
					intent.setClass(getActivity(),
							GzLm_pictureContent_Activity3.class);
					startActivity(intent);
				} else if (position1 == 3) {
					intent.setClass(getActivity(),
							GzLm_pictureContent_Activity4.class);
					startActivity(intent);
				} else if (position1 == 4) {
					intent.setClass(getActivity(),
							GzLm_pictureContent_Activity5.class);
					startActivity(intent);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(getActivity(),
				GzLm_pictureContent_mainActivity.class);
		switch (v.getId()) {
		case R.id.iv1:
			System.out.println("这里执行了");
			startActivity(intent);
			break;
		case R.id.iv2:
			System.out.println("这里执行了");
			startActivity(intent);
			break;
		case R.id.iv3:
			System.out.println("这里执行了");
			startActivity(intent);
			break;
		case R.id.iv4:
			System.out.println("这里执行了");
			startActivity(intent);
			break;
		case R.id.iv5:
			System.out.println("这里执行了");
			startActivity(intent);
			break;

		default:
			break;
		}

	}

	private void getFlashDatas() {
		getdocuments.getDocumentsForFlash(getActivity(), 2, "738", "5", "1",
				"4");
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				// 从当前页面切换到下一个页面
				vp.setCurrentItem(vp.getCurrentItem() + 1);

				break;
			case 1:
				vp.setVisibility(View.VISIBLE);
				ll_pg.setVisibility(View.GONE);
				titlesRes = new String[] { list_mtjj.get(0).getTitle(),
						list_mtjj.get(1).getTitle(),
						list_mtjj.get(2).getTitle(),
						list_mtjj.get(3).getTitle(),
						list_mtjj.get(4).getTitle() };
				list = new ArrayList<View>();
				list.add(v1);
				list.add(v2);
				list.add(v3);
				list.add(v4);
				list.add(v5);
				adapter = new MyPagerAdapter(getActivity(), list);
				vp.setAdapter(adapter);
				vp.setOnSingleTouchListener(new OnSingleTouchListener() {
					//
					@Override
					public void onSingleTouch() {
						System.out.println("这里执行了1322");
						Intent intent = new Intent();
						if (position1 == 0) {
							infofile.gzlm_title().put(
									list_mtjj.get(0).getTitle());
							infofile.chanDocId().put(
									list_mtjj.get(0).getId());
							infofile.writeTime().put(
									list_mtjj.get(0).getWriteTime());
						}
						if (position1 == 1) {
							infofile.gzlm_title().put(
									list_mtjj.get(1).getTitle());
							infofile.chanDocId().put(
									list_mtjj.get(1).getId());
							infofile.writeTime().put(
									list_mtjj.get(1).getWriteTime());
						}
						if (position1 == 2) {
							infofile.gzlm_title().put(
									list_mtjj.get(2).getTitle());
							infofile.chanDocId().put(
									list_mtjj.get(2).getId());
							infofile.writeTime().put(
									list_mtjj.get(2).getWriteTime());
						}
						if (position1 == 3) {
							infofile.gzlm_title().put(
									list_mtjj.get(3).getTitle());
							infofile.chanDocId().put(
									list_mtjj.get(3).getId());
							infofile.writeTime().put(
									list_mtjj.get(3).getWriteTime());
						}
						if (position1 == 4) {
							infofile.gzlm_title().put(
									list_mtjj.get(4).getTitle());
							infofile.chanDocId().put(
									list_mtjj.get(4).getId());
							infofile.writeTime().put(
									list_mtjj.get(4).getWriteTime());
						}
						intent.setClass(getActivity(),
								GZLM_Content_mainActivity.class);
						startActivity(intent);
						// // if (list_flash_contents_datas != null) {
						// // Intent intent = new Intent(getActivity(),
						// // GzLm_pictureContent_mainActivity.class)
						// // .putExtra("url",
						// // (String) list_flash_contents_datas
						// // .get(position1).get("url")).putExtra("title",
						// (String) list_flash_contents_datas
						// // .get(position1).get("title"));
						// // startActivity(intent);
						// // }
						//
						// // Toast.makeText(getActivity(),
						// //
						// position1+(String)list_flash_contents_datas.get(position1).get("url"),
						// // Toast.LENGTH_SHORT).show();
					}
				});
				vp.setOnPageChangeListener(new OnPageChangeListener() {

					// 当一个页面被选择调用
					@Override
					public void onPageSelected(int position) {
						position1 = position;
						tv.setText(titlesRes[position]);
						// 设置前一个点的背景图
						llPoints.getChildAt(prePosition).setBackgroundResource(
								R.drawable.dot_normal);
						llPoints.getChildAt(position % list.size())
								.setBackgroundResource(R.drawable.dot_enable);
						prePosition = position % list.size();
					}

					// 当前页面滚动的时候调用
					@Override
					public void onPageScrolled(int position,
							float positionOffset, int positionOffsetPixels) {
					}

					// 当滚动状态改变了会调用
					@Override
					public void onPageScrollStateChanged(int arg0) {
						// TODO Auto-generated method stub
					}
				});
				// new Thread() {
				// public void run() {
				// while (isLoop) {
				// try {
				// Thread.sleep(3000);
				// } catch (InterruptedException e) {
				// e.printStackTrace();
				// }
				// // 这个睡眠方法,不会被中断
				// SystemClock.sleep(3000);
				// handler.sendEmptyMessage(0);
				// }
				// }
				// }.start();
				break;
			default:
				break;
			}
		}
	};
}
