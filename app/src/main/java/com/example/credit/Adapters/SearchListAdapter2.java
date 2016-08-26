package com.example.credit.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.credit.Activitys.SearchFirmActivty;
import com.example.credit.Entitys.DataManager.search;
import com.example.credit.R;

import java.util.ArrayList;
import java.util.List;

public class SearchListAdapter2 extends BaseAdapter {
	private Context context;
	private  List<search> list;
	public SearchListAdapter2(Context context, List<search> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return  0;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder vh=null;
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.search_result_list_item, null);
			vh=new ViewHolder();
			vh.firm_name=(TextView) view.findViewById(R.id.firm_name);
			vh.corporate=(TextView) view.findViewById(R.id.corporate);
			vh.funds=(TextView) view.findViewById(R.id.funds);
			vh.status=(TextView) view.findViewById(R.id.status);
			vh.time=(TextView) view.findViewById(R.id.date);
			view.setTag(vh);
		}else{
			vh=(ViewHolder) view.getTag();
		}
		search c=list.get(position);
		String str=SearchFirmActivty.searchEt.getText().toString();
		String[] strR=str.split("");
		SpannableStringBuilder style = new SpannableStringBuilder(c.ENTNAME);
		for(int i=0;i<strR.length;i++){
			if(!strR[i].equals("")){
				int start=c.ENTNAME.indexOf(strR[i]);
				int end=start+1;
//				style.setSpan(new BackgroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的背景颜色
				style.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的颜色
			}
		}
		vh.firm_name.setText(style);
		vh.corporate.setText(c.NAME);
		if(vh.corporate.getText().toString().equals("")){
			vh.corporate.setText("无");
		}
		if(c.REGCAP.equals("")){
			vh.funds.setText("0万元");
		}else{
			vh.funds.setText(c.REGCAP+"万元");
		}

		vh.status.setText(c.REGSTATE_CN);
		vh.time.setText(c.OPFROM);
		return view;
	}
	public class ViewHolder{
		TextView firm_name;
		TextView corporate;
		TextView funds;
		TextView status;
		TextView time;
	}
}
