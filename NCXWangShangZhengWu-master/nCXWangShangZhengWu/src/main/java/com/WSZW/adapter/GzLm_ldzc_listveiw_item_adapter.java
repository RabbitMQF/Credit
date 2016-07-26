package com.WSZW.adapter;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.WSZW.Activity.R;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.Bitmap_bean;
import com.WSZW.entity.GWLM_DpwD_bean;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.getDocumentManagerService;

public class GzLm_ldzc_listveiw_item_adapter extends BaseAdapter {

	DisplayMetrics mDisplayMetrics = new DisplayMetrics();
	
	
	private Context context;
	private List<GWLM_DpwD_bean> list;
	private LayoutInflater inflater;
	private Activity activity;

	public GzLm_ldzc_listveiw_item_adapter(Context context,
			List<GWLM_DpwD_bean> list) {
		this.context = context;
		activity=(Activity) context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return list.size();
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewholder;
		if (convertView == null) {
			viewholder = new ViewHolder();
			convertView = inflater.inflate(R.layout.gzlm_ldzc_list_item, null);

			viewholder.ll_content = (LinearLayout) convertView
					.findViewById(R.id.ll_content);
			viewholder.tv = (TextView) convertView.findViewById(R.id.tv_title);
			viewholder.tv2 = (TextView) convertView
					.findViewById(R.id.tv_title2);
			viewholder.iv_picture = (WebView) convertView
					.findViewById(R.id.iv_picture);
			convertView.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) convertView.getTag();
		}
		if(list!=null){
					url = "http://www.ncx.gov.cn" + list.get(position).getPicUrl();
					viewholder.iv_picture.loadUrl(url);
					WebSettings settings = viewholder.iv_picture.getSettings();  //
					System.out.println("========================="+list.size());
					System.out.println("========================="+list.get(position).getTitle());
					viewholder.tv.setText(list.get(position).getTitle());
					viewholder.tv2.setText(list.get(position).getSummary());
					settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);  // 设置WebView 文本居中
					settings.setSupportZoom(true); // 支持缩放
					
					//判定屏幕尺寸
					activity.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
					int W = mDisplayMetrics.widthPixels;
					//int H = mDisplayMetrics.heightPixels;
					if(W>=720){
					if(W!=720&&W!=1080){settings.setUseWideViewPort(true);}//扩大比例的缩放
					if(W==720){settings.setUseWideViewPort(true);}
										}
					//自适应屏幕
					settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);  
					settings.setLoadWithOverviewMode(true);
					
		}

		viewholder.ll_content.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		return convertView;
	}

	class ViewHolder {
		LinearLayout ll_content;
		TextView tv, tv2;
		WebView iv_picture;
	}
//	private Bitmap bitmap;
	private String url;
}
