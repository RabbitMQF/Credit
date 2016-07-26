package com.WSZW.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.WSZW.Activity.R;
import com.WSZW.Activity.ZWGK_Content_mainActivity;
import com.WSZW.Activity.ZWGK_FirstColumn_mainActivity;
import com.WSZW.data.InfoFile_;

public class ZwGk_GridView_adapter extends BaseAdapter {
	
	private Context context;
	private LayoutInflater inflater;
	private InfoFile_ infofile_;
	private List list;
	
	public ZwGk_GridView_adapter(Context context,List list) {
		this.context = context;
		this.list=list;
		infofile_=new InfoFile_(context);
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewholder;
		if(convertView==null){
			viewholder=new ViewHolder();
			convertView=inflater.inflate(R.layout.zwgk_bmxxgk_list_item, null);
			
			viewholder.ll=(LinearLayout) convertView.findViewById(R.id.ll);
			
			viewholder.tv=(TextView) convertView.findViewById(R.id.textview);
			convertView.setTag(viewholder);
		}else {
			viewholder=(ViewHolder) convertView.getTag();
		}
		if(list!= null){
			
			viewholder.tv.setText(list.get(position).toString());  
			
		}else{
			viewholder.tv.setText("县政府办");
		}
		
		viewholder.ll.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(context, ZWGK_FirstColumn_mainActivity.class);
					switch (position) {
					case 0:
						infofile_.prefixURL().put("http://rsj.ncx.gov.cn");//人社局
					    infofile_.organId().put("4595");//县人社局
					    context.startActivity(intent);
						
						/*infofile_.prefixURL().put("http://xgsj.ncx.gov.cn");//南昌县政府办"http://zfb.ncx.gov.cn"4521
						infofile_.organId().put("4566");//县政府办
						context.startActivity(intent);*/
						break;
					case 1:
						infofile_.prefixURL().put("http://wgj.ncx.gov.cn");//
						infofile_.organId().put("4597");//县文广局
						context.startActivity(intent);
						break;
					case 2:
						infofile_.prefixURL().put("http://kjj.ncx.gov.cn");//
						infofile_.organId().put("4599");//县科技局
						context.startActivity(intent);
						break;
					case 3:
						infofile_.prefixURL().put("http://daj.ncx.gov.cn");//
						infofile_.organId().put("4601");//县文档局
						context.startActivity(intent);
						break;
					case 4:
						infofile_.prefixURL().put("http://gxs.ncx.gov.cn");//
						infofile_.organId().put("4603");//县供销社
						context.startActivity(intent);
						break;
					case 5:
						infofile_.prefixURL().put("http://ncxga.ncx.gov.cn");//
						infofile_.organId().put("4605");//县公安局
						context.startActivity(intent);
						break;
					case 6:
						infofile_.prefixURL().put("http://gtj.ncx.gov.cn");//
						infofile_.organId().put("4607");//县国土局
						context.startActivity(intent);
						break;
					case 7:
						infofile_.prefixURL().put("http://wsj.ncx.gov.cn");//
						infofile_.organId().put("4609");//县卫生局
						context.startActivity(intent);
						break;
					case 8:
						infofile_.prefixURL().put("http://qxj.ncx.gov.cn");//
						infofile_.organId().put("4611");//县气象局
						context.startActivity(intent);
						break;
					case 9:
						infofile_.prefixURL().put("http://sfj.ncx.gov.cn");//
						infofile_.organId().put("4615");//县司法局
						context.startActivity(intent);
						break;
					/*case 10:
						infofile_.prefixURL().put("http://xmscj.ncx.gov.cn");//
						infofile_.organId().put("4613");//县畜牧水产局
						context.startActivity(intent);
						break;*/
					case 10:
						infofile_.prefixURL().put("http://cjj.ncx.gov.cn");//
						infofile_.organId().put("4617");//县城建局
						context.startActivity(intent);
						break;
					case 11:
						infofile_.prefixURL().put("http://swj.ncx.gov.cn");//
						infofile_.organId().put("4619");//县商务局
						context.startActivity(intent);
						break;
					case 12:
						infofile_.prefixURL().put("http://fgj.ncx.gov.cn");//
						infofile_.organId().put("4621");//县房管局
						context.startActivity(intent);
						break;
					case 13:
						infofile_.prefixURL().put("http://jyj.ncx.gov.cn");//
						infofile_.organId().put("4593");//县教体局
						context.startActivity(intent);
						break;
					/*case 15-1:
						infofile_.prefixURL().put("http://gsj.ncx.gov.cn");//
						infofile_.organId().put("4623");//县国税局
						context.startActivity(intent);
						break;*/
					case 14:
						infofile_.prefixURL().put("http://clh.ncx.gov.cn");//
						infofile_.organId().put("4591");//县残联
						context.startActivity(intent);
						break;
					case 15:
						infofile_.prefixURL().put("http://cgj.ncx.gov.cn");//
						infofile_.organId().put("4587");//县城管局
						context.startActivity(intent);
						break;
					case 16:
						infofile_.prefixURL().put("http://nyj.ncx.gov.cn");//
						infofile_.organId().put("4585");//县农业局
						context.startActivity(intent);
						/*infofile_.prefixURL().put("http://dsj.ncx.gov.cn");//
						infofile_.organId().put("4589");//县地税局
						context.startActivity(intent);*/
						break;
					case 17:
						infofile_.prefixURL().put("http://tj.ncx.gov.cn");//
						infofile_.organId().put("4583");//县统计局
						context.startActivity(intent);
						break;
					case 18:
						infofile_.prefixURL().put("http://lyj.ncx.gov.cn");//
						infofile_.organId().put("4578");//县林业局
						context.startActivity(intent);
						break;
					case 19:
						infofile_.prefixURL().put("http://xswj.ncx.gov.cn");//
						infofile_.organId().put("4574");//县水务局
						context.startActivity(intent);
						/*infofile_.prefixURL().put("http://nkb.ncx.gov.cn");//
						infofile_.organId().put("6373");//县农开办
						context.startActivity(intent);*/
						break;
					case 20:
						infofile_.prefixURL().put("http://sjj.ncx.gov.cn");//
						infofile_.organId().put("4572");//县审计局
						context.startActivity(intent);
						break;
					case 21:
						infofile_.prefixURL().put("http://gxw.ncx.gov.cn");//
						infofile_.organId().put("4570");//县工信委
						context.startActivity(intent);
					/*	infofile_.prefixURL().put("http://fzb.ncx.gov.cn");//
						infofile_.organId().put("6372");//县法制办
						context.startActivity(intent);*/
						break;
				
					/*case 24-2:
						infofile_.prefixURL().put("http://jsw.ncx.gov.cn");//
						infofile_.organId().put("4581");//县计生委
						context.startActivity(intent);
						break;*/
					case 22:
						infofile_.prefixURL().put("http://njj.ncx.gov.cn");//
						infofile_.organId().put("4626");//县农机局
						context.startActivity(intent);
						/*infofile_.prefixURL().put("http://zjj.ncx.gov.cn");//
						infofile_.organId().put("4579");//县质监局
						context.startActivity(intent);*/
						break;
					case 23:
						infofile_.prefixURL().put("http://xgsj.ncx.gov.cn");//
						infofile_.organId().put("4566");//县市监局
						context.startActivity(intent);
						break;
					case 24:
						infofile_.prefixURL().put("http://jtj.ncx.gov.cn");//
						infofile_.organId().put("4550");//县交通局
						context.startActivity(intent);
						/*infofile_.prefixURL().put("http://yjj.ncx.gov.cn");//
						infofile_.organId().put("4576");//县药监局
						context.startActivity(intent);*/
						break;
					case 25:
						infofile_.prefixURL().put("http://jtj.ncx.gov.cn");//
						infofile_.organId().put("4550");//县交通局
						context.startActivity(intent);
						break;
					case 26:
						infofile_.prefixURL().put("http://czj.ncx.gov.cn");//
						infofile_.organId().put("4547");//县财政局
						context.startActivity(intent);
						break;
					case 27:
						infofile_.prefixURL().put("http://fgw.ncx.gov.cn");//
						infofile_.organId().put("4544");//县发改委
						context.startActivity(intent);
						break;
					case 28:
						infofile_.prefixURL().put("http://ycj.ncx.gov.cn");//
						infofile_.organId().put("4542");//县烟草局
						context.startActivity(intent);
						break;
					case 29:
						infofile_.prefixURL().put("http://lsj.ncx.gov.cn");//
						infofile_.organId().put("4534");//县粮食局
						context.startActivity(intent);
						/*infofile_.prefixURL().put("http://xzfw.ncx.gov.cn");//
						infofile_.organId().put("5842");//县行政服务中心
						context.startActivity(intent);*/
						break;
					case 30:
						infofile_.prefixURL().put("http://ajj.ncx.gov.cn");//
						infofile_.organId().put("4526");//县安监局
						context.startActivity(intent);
						/*infofile_.prefixURL().put("http://xfj.ncx.gov.cn");//
						infofile_.organId().put("6374");//县信访局
						context.startActivity(intent);*/
						break;
					case 31:
						infofile_.prefixURL().put("http://hbj.ncx.gov.cn");//
						infofile_.organId().put("4524");//县环保局
						context.startActivity(intent);
						/*infofile_.prefixURL().put("http://rmyh.ncx.gov.cn");//
						infofile_.organId().put("5841");//人民银行南昌县分行
						context.startActivity(intent);*/
						break;
					case 32:
						infofile_.prefixURL().put("http://mzj.ncx.gov.cn");//
						infofile_.organId().put("4523");//县民政局
						context.startActivity(intent);
						/*infofile_.prefixURL().put("http://glfj.ncx.gov.cn");//
						infofile_.organId().put("5840");//县公路分局
						context.startActivity(intent);*/
						break;
					/*case 36-3:
						infofile_.prefixURL().put("http://hgj.ncx.gov.cn");//
						infofile_.organId().put("5839");//县湖管局
						context.startActivity(intent);
						break;
					case 37-3:
						infofile_.prefixURL().put("http://gdgs.ncx.gov.cn");//
						infofile_.organId().put("5838");//供电公司
						context.startActivity(intent);
						break;
					case 38-3:
						infofile_.prefixURL().put("http://yzj.ncx.gov.cn");//
						infofile_.organId().put("5837");//县邮政局
						context.startActivity(intent);
						break;
					case 39-3:
						infofile_.prefixURL().put("http://jcj.ncx.gov.cn");//
						infofile_.organId().put("5836");//县监察局
						context.startActivity(intent);
						break;
					case 40-3:
						infofile_.prefixURL().put("http://xgsj.ncx.gov.cn");//
						infofile_.organId().put("4566");//县工商局
						context.startActivity(intent);
						break;
					case 41-3:
						infofile_.prefixURL().put("http://mzzjj.ncx.gov.cn");//
						infofile_.organId().put("4560");//县民宗局
						context.startActivity(intent);
						break;
					case 42-3:
						
						break;
					case 43-3:
						
						break;
					case 44-3:
						
						break;
					case 45-3:
						
						break;
					case 46-3:
					
						break;
					case 47-3:
						
						break;
					case 48-3:
						
						break;
					case 49-3:
						
						break;				*/
					default:
						break;
					}
			}
		});
			return convertView;
	}
	class ViewHolder{
		public LinearLayout ll;
		TextView tv ,time;
		ImageView iv;
	}	
}
