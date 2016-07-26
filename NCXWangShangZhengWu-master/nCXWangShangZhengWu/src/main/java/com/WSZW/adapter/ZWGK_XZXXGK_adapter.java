package com.WSZW.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
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
import com.WSZW.adapter.ZwGk_GridView_adapter.ViewHolder;
import com.WSZW.data.InfoFile_;

public class ZWGK_XZXXGK_adapter extends BaseAdapter{
	private Context context;
	private LayoutInflater inflater;
	private InfoFile_ infofile_;
	private List list;
	
	public ZWGK_XZXXGK_adapter(Context context,List list) {
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
				// TODO 自动生成的方法存根
				Intent intent=new Intent();
				intent.setClass(context, ZWGK_FirstColumn_mainActivity.class);
				switch (position) {
				case 0:
					infofile_.prefixURL().put("http://ltz.ncx.gov.cn");//
					infofile_.organId().put("4264");//县莲塘镇
					break;
				case 1:
//					infofile_.prefixURL().put("http://wgj.ncx.gov.cn");//
					infofile_.organId().put("5847");//小兰经开区
					break;
				case 2:
					infofile_.prefixURL().put("http://gsz.ncx.gov.cn");//
					infofile_.organId().put("4554");//岗上镇
					break;
				case 3:
					infofile_.prefixURL().put("http://hmx.ncx.gov.cn");//
					infofile_.organId().put("4562");//黄马乡
					break;
				case 4:
					infofile_.prefixURL().put("http://xtz.ncx.gov.cn");//
					infofile_.organId().put("4546");//向塘镇
					break;
				case 5:
					infofile_.prefixURL().put("http://gfz.ncx.gov.cn");//
					infofile_.organId().put("4555");//广福镇
					break;
				case 6:
					infofile_.prefixURL().put("http://tcx.ncx.gov.cn");//
					infofile_.organId().put("4564");//塔城乡
					break;
				case 7:
					infofile_.prefixURL().put("http://wgj.ncx.gov.cn");//
					infofile_.organId().put("4548");//蒋巷镇
					break;
				case 8:
					infofile_.prefixURL().put("http://sjz.ncx.gov.cn");//
					infofile_.organId().put("4557");//三江镇
					break;
				case 9:
					infofile_.prefixURL().put("http://fsx.ncx.gov.cn");//
					infofile_.organId().put("4565");//富山乡
					break;
				case 10:
					infofile_.prefixURL().put("http://ylz.ncx.gov.cn");//
					infofile_.organId().put("4551");//幽兰镇
					break;
				case 11:
					infofile_.prefixURL().put("http://jkx.ncx.gov.cn");//
					infofile_.organId().put("4558");//泾口乡
					break;
				case 12:
					infofile_.prefixURL().put("http://dxx.ncx.gov.cn");//
					infofile_.organId().put("4567");//东新乡
					break;
				case 13:
					infofile_.prefixURL().put("http://tnz.ncx.gov.cn");//
					infofile_.organId().put("4552");//塘南镇
					break;
				case 14:
					infofile_.prefixURL().put("http://nxx.ncx.gov.cn");//
					infofile_.organId().put("4559");//南新乡
					break;
				case 15:
					infofile_.prefixURL().put("http://byx.ncx.gov.cn");//
					infofile_.organId().put("4561");//八一乡
					break;
				case 16:
					infofile_.prefixURL().put("http://ysjgwh.ncx.gov.cn");//
					infofile_.organId().put("4568");//银三角管委会
					break;
				case 17:
					infofile_.prefixURL().put("http://wyz.ncx.gov.cn");//
					infofile_.organId().put("4553");//武阳镇
					break;
				default:
					break;
				}
				context.startActivity(intent);
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
