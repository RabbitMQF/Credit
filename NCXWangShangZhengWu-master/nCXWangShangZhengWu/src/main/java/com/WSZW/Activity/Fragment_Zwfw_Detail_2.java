package com.WSZW.Activity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;

import com.WSZW.util.StringUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragment_Zwfw_Detail_2 extends Fragment {
	TextView tv;
	private String hello;// = "hello android";
	private String defaultHello = "default value";
	ImageView iv;

	static Fragment_Zwfw_Detail_2 newInstance(String s) {
		Fragment_Zwfw_Detail_2 newFragment = new Fragment_Zwfw_Detail_2();
		Bundle bundle = new Bundle();
		bundle.putString("hello", s);
		newFragment.setArguments(bundle);
		return newFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Bundle args = getArguments();
		hello = args != null ? args.getString("hello") : defaultHello;
		View view = inflater.inflate(R.layout.fragment_zwfw_detail_2,
				container, false);
		tv = (TextView) view.findViewById(R.id.tv);
		iv = (ImageView) view.findViewById(R.id.iv);
		if (hello.contains("http")) {
			hello = hello.replace(")", "");
			List<String> list = StringUtil.stringToNormalListBy1(hello);
			if (list.size() == 2) {
				tv.setText(list.get(0).equals("null") ? "" : list.get(0));
				final String url = list.get(1);
				new Thread(new Runnable() {

					@Override
					public void run() {
						bt = getHttpBitmap(url);
						myHandler.sendEmptyMessage(123456);
					}
				}).start();

			}

		} else
			tv.setText(hello);
		return view;
	}

	Bitmap bt = null;
	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 123456:
				if (bt != null) {

					iv.setImageBitmap(bt);
				}
				break;
			}
			super.handleMessage(msg);
		}
	};

	public Bitmap getHttpBitmap(String url) {
		URL myFileURL;
		Bitmap bitmap = null;
		try {
			myFileURL = new URL(url);
			// 获得连接
			HttpURLConnection conn = (HttpURLConnection) myFileURL
					.openConnection();
			// 设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
			conn.setConnectTimeout(6000);
			// 连接设置获得数据流
			conn.setDoInput(true);
			// 不使用缓存
			conn.setUseCaches(false);
			// 这句可有可无，没有影响
			// conn.connect();
			// 得到数据流
			InputStream is = conn.getInputStream();
			// 解析得到图片
			bitmap = BitmapFactory.decodeStream(is);
			// 关闭数据流
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bitmap;

	}
}
