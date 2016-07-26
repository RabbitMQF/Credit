package com.WSZW.view;

import java.util.ArrayList;
import java.util.List;

import com.WSZW.Activity.Fragment_Zmhd_Cjwt;
import com.WSZW.Activity.Fragment_Zmhd_Myzj;
import com.WSZW.Activity.Fragment_Zmhd_Myzjtjfx;
import com.WSZW.Activity.Fragment_Zmhd_Wsdc;
import com.WSZW.Activity.Fragment_Zmhd_Xjwjbxx;
import com.WSZW.Activity.Fragment_Zmhd_Xzxx;
import com.WSZW.Activity.Fragment_Zmhd_Zxft;
import com.WSZW.Activity.Fragment_Zmhd_lxxd;
import com.WSZW.Activity.R;
import com.ZWPT.sliding.SlidingFragmentActivity;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author yangyu 功能描述：列表Fragment，用来显示滑动菜单打开后的内容
 */

public class SampleListFragment extends ListFragment {
	SlidingFragmentActivity context;
	List<String> list_menu = new ArrayList<String>();
	private FragmentTransaction oFragmentTransaction;// 碎片管理者
	Fragment_Zmhd_Xzxx xzxx_fragment;
	Fragment_Zmhd_Xjwjbxx xjw_fragment;
	Fragment_Zmhd_lxxd lxxd_fragment;
	Fragment_Zmhd_Cjwt cjwt_fragment;
	Fragment_Zmhd_Zxft zxft_fragment;
	Fragment_Zmhd_Wsdc wsdc_fragment;
	Fragment_Zmhd_Myzj myzj_fragment;
	Fragment_Zmhd_Myzjtjfx myzjtjfx_fragment;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.sliding_menu_list, null);
	}

	public SampleListFragment(SlidingFragmentActivity context){
		this.context = context;
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//context = this.getActivity();
		oFragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager()
				.beginTransaction();// 碎片管理者开始处理
		SampleAdapter adapter = new SampleAdapter(this.getActivity());

		list_menu.add("县长信箱");
		//list_menu.add("县纪委举报信箱");
		list_menu.add("来信选登");
		list_menu.add("常见问题");
		list_menu.add("在线访谈");
		list_menu.add("网上调查");
		list_menu.add("民意征集");
		list_menu.add("民意征集统计分析");
		for (String str : list_menu) {
			adapter.add(new SampleItem(str, android.R.drawable.ic_menu_search,
					false));
		}
		setListAdapter(adapter);
		
		if (lxxd_fragment == null) {
			lxxd_fragment = new Fragment_Zmhd_lxxd();
//			Bundle bundle = new Bundle();
//			bundle.putSerializable("Detail_Sjdj_bean",
//					detail_Sjdj_bean_ysl);
//			xzxx_fragment.setArguments(bundle);
			oFragmentTransaction.add(R.id.frame,
					lxxd_fragment);
		}
//		 oFragmentTransaction.replace(R.id.frame,
//				 lxxd_fragment);
//		 oFragmentTransaction.commit();
		oFragmentTransaction.show(lxxd_fragment);
		oFragmentTransaction.commit();
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {
		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {

				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.sliding_menu_row, null);
				// }
				TextView title = (TextView) convertView
						.findViewById(R.id.row_title);
				title.setText(getItem(position).tag);
			} else {

			}

			return convertView;
		}
	}

	private class SampleItem {
		public String tag;
		public boolean clickable;

		public SampleItem(String tag, int iconRes, boolean click) {
			this.tag = tag;
			this.clickable = click;
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		SampleItem sam = (SampleItem) l.getAdapter().getItem(position);
		startListActivity(sam);
	}
	private void hideFragment(FragmentTransaction fragmentTransaction) {
		if (xzxx_fragment != null) {
			fragmentTransaction.hide(xzxx_fragment);
		}
		if (xjw_fragment != null) {
			fragmentTransaction.hide(xjw_fragment);
		}
		if (lxxd_fragment != null) {
			fragmentTransaction.hide(lxxd_fragment);
		}
		if (cjwt_fragment != null) {
			fragmentTransaction.hide(cjwt_fragment);
		}
		if (zxft_fragment != null) {
			fragmentTransaction.hide(zxft_fragment);
		}
		if (wsdc_fragment != null) {
			fragmentTransaction.hide(wsdc_fragment);
		}
		if (myzj_fragment != null) {
			fragmentTransaction.hide(myzj_fragment);
		}
		if(myzjtjfx_fragment != null){
			fragmentTransaction.hide(myzjtjfx_fragment);
		}
	}
	public void startListActivity(SampleItem samp) {
		oFragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager()
				.beginTransaction();// 碎片管理者开始处理
		hideFragment(oFragmentTransaction);
		if (samp.tag.equals("县长信箱")) {
			if (xzxx_fragment == null) {
				xzxx_fragment = new Fragment_Zmhd_Xzxx();
//				Bundle bundle = new Bundle();
//				bundle.putSerializable("Detail_Sjdj_bean",
//						detail_Sjdj_bean_ysl);
//				xzxx_fragment.setArguments(bundle);
				oFragmentTransaction.add(R.id.frame,
						xzxx_fragment);
			}
//			 oFragmentTransaction.replace(R.id.frame,
//			 xzxx_fragment);
//			 oFragmentTransaction.commit();
			oFragmentTransaction.show(xzxx_fragment);
		} else if (samp.tag.equals("县纪委举报信箱")) {
			if (xjw_fragment == null) {
				xjw_fragment = new Fragment_Zmhd_Xjwjbxx();
//				Bundle bundle = new Bundle();
//				bundle.putSerializable("Detail_Sjdj_bean",
//						detail_Sjdj_bean_ysl);
//				xzxx_fragment.setArguments(bundle);
				oFragmentTransaction.add(R.id.frame,
						xjw_fragment);
			}
//			 oFragmentTransaction.replace(R.id.frame,
//					 xjw_fragment);
//			 oFragmentTransaction.commit();
			oFragmentTransaction.show(xjw_fragment);
		} else if (samp.tag.equals("来信选登")) {
			if (lxxd_fragment == null) {
				lxxd_fragment = new Fragment_Zmhd_lxxd();
//				Bundle bundle = new Bundle();
//				bundle.putSerializable("Detail_Sjdj_bean",
//						detail_Sjdj_bean_ysl);
//				xzxx_fragment.setArguments(bundle);
				oFragmentTransaction.add(R.id.frame,
						lxxd_fragment);
			}
//			 oFragmentTransaction.replace(R.id.frame,
//					 lxxd_fragment);
//			 oFragmentTransaction.commit();
			oFragmentTransaction.show(lxxd_fragment);

		} else if (samp.tag.equals("常见问题")) {
			if (cjwt_fragment == null) {
				cjwt_fragment = new Fragment_Zmhd_Cjwt();
//				Bundle bundle = new Bundle();
//				bundle.putSerializable("Detail_Sjdj_bean",
//						detail_Sjdj_bean_ysl);
//				xzxx_fragment.setArguments(bundle);
				oFragmentTransaction.add(R.id.frame,
						cjwt_fragment);
			}
//			 oFragmentTransaction.replace(R.id.frame,
//					 cjwt_fragment);
//			 oFragmentTransaction.commit();
			oFragmentTransaction.show(cjwt_fragment);
		} else if (samp.tag.equals("在线访谈")) {
			if (zxft_fragment == null) {
				zxft_fragment = new Fragment_Zmhd_Zxft();
//				Bundle bundle = new Bundle();
//				bundle.putSerializable("Detail_Sjdj_bean",
//						detail_Sjdj_bean_ysl);
//				xzxx_fragment.setArguments(bundle);
				oFragmentTransaction.add(R.id.frame,
						zxft_fragment);
			}
//			 oFragmentTransaction.replace(R.id.frame,
//					 zxft_fragment);
//			 oFragmentTransaction.commit();
			oFragmentTransaction.show(zxft_fragment);
		} else if (samp.tag.equals("网上调查")) {
			if (wsdc_fragment == null) {
				wsdc_fragment = new Fragment_Zmhd_Wsdc();
//				Bundle bundle = new Bundle();
//				bundle.putSerializable("Detail_Sjdj_bean",
//						detail_Sjdj_bean_ysl);
//				xzxx_fragment.setArguments(bundle);
				oFragmentTransaction.add(R.id.frame,
						wsdc_fragment);
			}
//			 oFragmentTransaction.replace(R.id.frame,
//					 wsdc_fragment);
//			 oFragmentTransaction.commit();
			oFragmentTransaction.show(wsdc_fragment);
		} else if (samp.tag.equals("民意征集")) {
			if (myzj_fragment == null) {
				myzj_fragment = new Fragment_Zmhd_Myzj();
//				Bundle bundle = new Bundle();
//				bundle.putSerializable("Detail_Sjdj_bean",
//						detail_Sjdj_bean_ysl);
//				xzxx_fragment.setArguments(bundle);
				oFragmentTransaction.add(R.id.frame,
						myzj_fragment);
			}
//			 oFragmentTransaction.replace(R.id.frame,
//					 mytd_fragment);
//			 oFragmentTransaction.commit();
			oFragmentTransaction.show(myzj_fragment);
		} else if (samp.tag.equals("民意征集统计分析")){
			if (myzjtjfx_fragment == null) {
				myzjtjfx_fragment = new Fragment_Zmhd_Myzjtjfx();
//				Bundle bundle = new Bundle();
//				bundle.putSerializable("Detail_Sjdj_bean",
//						detail_Sjdj_bean_ysl);
//				xzxx_fragment.setArguments(bundle);
				oFragmentTransaction.add(R.id.frame,
						myzjtjfx_fragment);
			}
//			 oFragmentTransaction.replace(R.id.frame,
//					 mytd_fragment);
//			 oFragmentTransaction.commit();
			oFragmentTransaction.show(myzjtjfx_fragment);
		}
		oFragmentTransaction.commitAllowingStateLoss();
		context.getSlidingMenu().toggle();
	}
}
