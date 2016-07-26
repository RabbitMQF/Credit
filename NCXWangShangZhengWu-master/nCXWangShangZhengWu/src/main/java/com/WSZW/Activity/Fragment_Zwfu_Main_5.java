package com.WSZW.Activity;

import java.util.ArrayList;
import java.util.List;

import com.WSZW.Activity.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragment_Zwfu_Main_5 extends Fragment implements OnClickListener{
	private static final String TAG = "Fragment_Zwfu_Main_5";
	View view ;
	TextView tv_1,tv_2,tv_3,tv_4;
	LinearLayout ll_1,ll_2,ll_3,ll_4;
	int item = 0;
	boolean temp=false;
	 
	static Fragment_Zwfu_Main_5 newInstance(String s) {
		Fragment_Zwfu_Main_5 newFragment = new Fragment_Zwfu_Main_5();
		return newFragment;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 view = inflater.inflate(R.layout.fragment_zwfu_main_5, container, false);
		 item = 0;
		initView();
		return view;
	}

	private void initView() {
		tv_1 = (TextView)view.findViewById(R.id.tv_1);
		tv_2 = (TextView)view.findViewById(R.id.tv_2);
		tv_3 = (TextView)view.findViewById(R.id.tv_3);
		tv_4 = (TextView)view.findViewById(R.id.tv_4);
		
		ll_1 = (LinearLayout)view.findViewById(R.id.ll_1);
		ll_2 = (LinearLayout)view.findViewById(R.id.ll_2);
		ll_3 = (LinearLayout)view.findViewById(R.id.ll_3);
		ll_4 = (LinearLayout)view.findViewById(R.id.ll_4);
		
		tv_1.setOnClickListener(this);
		tv_2.setOnClickListener(this);
		tv_3.setOnClickListener(this);
		tv_4.setOnClickListener(this);
		
		ll_1.setOnClickListener(this);
		ll_2.setOnClickListener(this);
		ll_3.setOnClickListener(this);
		ll_4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.tv_1:
			item = 0;
			setTextColorWhite();
			tv_1.setTextColor(Fragment_Zwfu_Main_5.this.getActivity()
					.getResources().getColor(R.color.yellow));
			temp=false;
			break;
		case R.id.tv_2:
			item = 1;
			setTextColorWhite();
			tv_2.setTextColor(Fragment_Zwfu_Main_5.this.getActivity()
					.getResources().getColor(R.color.yellow));
			temp=false;
			break;
		case R.id.tv_3:
			item = 2;
			setTextColorWhite();
			tv_3.setTextColor(Fragment_Zwfu_Main_5.this.getActivity()
					.getResources().getColor(R.color.yellow));
			temp=false;
			break;
		case R.id.tv_4:
			item = 3;
			setTextColorWhite();
			tv_4.setTextColor(Fragment_Zwfu_Main_5.this.getActivity()
					.getResources().getColor(R.color.yellow));
			temp=true;
			break;
		case R.id.ll_1:
			List<Integer> list_1 = new ArrayList<Integer>();
			list_1.add(R.drawable.bmfw_1_1);/*"http://117.40.244.134:8081/portal/onlineService/ggfw/gsfwSfbz.seam"*/
			list_1.add(R.drawable.bmfw_2_1);/*"http://117.40.244.134:8081/portal/onlineService/ggfw/gdfwSfbz.seam"*/
			list_1.add(R.drawable.bmfw_3_1);/*"http://117.40.244.134:8081/portal/onlineService/ggfw/rqfwSfbz.seam"*/
			list_1.add(8888);/*"http://117.40.244.134:8081/portal/onlineService/ggfw/txfwSfbz.seam"*/
			goToShowImageView(list_1.get(item));
			/*if(temp){
				goToShowWebView("http://117.40.244.134:8081/portal/onlineService/ggfw/txfwSfbz.seam");
			}else  {
				goToShowImageView(list_1.get(item));
			}*/
			
			break;
		case R.id.ll_2:
			List<Integer> list_2 = new ArrayList<Integer>();
			list_2.add(R.drawable.bmfw_1_2);/*"http://117.40.244.134:8081/portal/onlineService/ggfw/gsfwSfdd.seam"*/
			list_2.add(R.drawable.bmfw_2_2);/*"http://117.40.244.134:8081/portal/onlineService/ggfw/gdfwSfdd.seam"*/
			list_2.add(R.drawable.bmfw_3_2);/*"http://117.40.244.134:8081/portal/onlineService/ggfw/rqfwSfdd.seam"*/
			list_2.add(R.drawable.bmfw_4_2);/*"http://117.40.244.134:8081/portal/onlineService/ggfw/gsfwSfdd.seam"*/
			goToShowImageView(list_2.get(item));
			break;
		case R.id.ll_3:
			List<Integer> list_3 = new ArrayList<Integer>();
			list_3.add(R.drawable.bmfw_1_3);//"http://117.40.244.134:8081/portal/onlineService/ggfw/gsfwFwdh.seam"
			list_3.add(R.drawable.bmfw_2_3);//"http://117.40.244.134:8081/portal/onlineService/ggfw/gdfwFwdh.seam"
			list_3.add(R.drawable.bmfw_3_3);//"http://117.40.244.134:8081/portal/onlineService/ggfw/rqfwFwdh.seam"
			list_3.add(R.drawable.bmfw_4_3);//"http://117.40.244.134:8081/portal/onlineService/ggfw/txfwFwdh.seam"
			goToShowImageView(list_3.get(item));
			break;
		case R.id.ll_4:
			List<Integer> list_4 = new ArrayList<Integer>();
			list_4.add(R.drawable.bmfw_1_4);//"http://117.40.244.134:8081/portal/onlineService/ggfw/gsfwXgzn.seam"
			list_4.add(R.drawable.bmfw_2_4);//"http://117.40.244.134:8081/portal/onlineService/ggfw/ggfwDefault.seam"
			list_4.add(R.drawable.bmfw_3_4);//"http://117.40.244.134:8081/portal/onlineService/ggfw/rqfwXgzn.seam"
			list_4.add(0000);//"http://117.40.244.134:8081/portal/onlineService/ggfw/txfwXgzn.seam"
			goToShowImageView(list_4.get(item));
			/*if(temp){
				goToShowWebView("http://117.40.244.134:8081/portal/onlineService/ggfw/txfwXgzn.seam");
			}else {
				goToShowImageView(list_4.get(item));
			}*/
			
			break;
		default:
			break;
		}
	}
	
	private void setTextColorWhite(){
		tv_1.setTextColor(Fragment_Zwfu_Main_5.this.getActivity()
				.getResources().getColor(R.color.white_light));
		tv_2.setTextColor(Fragment_Zwfu_Main_5.this.getActivity()
				.getResources().getColor(R.color.white_light));
		tv_3.setTextColor(Fragment_Zwfu_Main_5.this.getActivity()
				.getResources().getColor(R.color.white_light));
		tv_4.setTextColor(Fragment_Zwfu_Main_5.this.getActivity()
				.getResources().getColor(R.color.white_light));
		
	}

	
     private void goToShowWebView(String url){
		startActivity(new Intent(Fragment_Zwfu_Main_5.this.getActivity(),ZWFW_Show_WebView_Activity.class).putExtra("url", url));
}
	
     /*直跳系统浏览器
      * private void goToSystemWeb(String url){
    	 startActivity(new Intent("Fragment_Zwfu_Main_5.this.getActivity()",Uri.parse(url) ));
     }*/
	private void goToShowImageView(int x){ 
		startActivity(new Intent(Fragment_Zwfu_Main_5.this.getActivity(),ZWFW_Activity_Bmfw.class).putExtra("id", x));
	}
	
	
}
