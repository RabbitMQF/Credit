package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.BaseViewHolder;

/**
 * @Description:gridview的Adapter
 * @author http://blog.csdn.net/finddreams
 */
public class MyGridAdapter41 extends BaseAdapter {
	private Context mContext;
	private String[] arrays1;
	LinearLayout.LayoutParams layoutParam;

	public MyGridAdapter41(Context context, String[] array1 ) {
		super();
		this.mContext = context;
		arrays1=array1;
	}
	public void setmargin(LinearLayout.LayoutParams layoutParam){
		this.layoutParam=layoutParam;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(arrays1.length%2==0){
			return arrays1.length;
		}else{
			return (2-arrays1.length%2)+arrays1.length;
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.grid_item4, parent, false);

		}
		if(arrays1.length%2!=0){
			if((arrays1.length-1)<position){
				return convertView;
			}
		}
		if(layoutParam!=null){
			TextView ttemp=BaseViewHolder.get(convertView,R.id.tbv);
			ttemp.setTextSize(13);
		}

		TextView tv1 = BaseViewHolder.get(convertView, R.id.tbv);
		switch (arrays1[position]){
			case "证照到期":
				for(int i=0;i<DataManager.AlertInfoS.data.size();i++){
					if(DataManager.AlertInfoS.data.get(i).type.equals("zzdq")){
						tv1.setText(arrays1[position]+"("+ DataManager.AlertInfoS.data.get(i).data.size()+")");
					}
				}
				break;
			case "证照过期":
				for(int i=0;i<DataManager.AlertInfoS.data.size();i++){
					if(DataManager.AlertInfoS.data.get(i).type.equals("zzgq")){
						tv1.setText(arrays1[position]+"("+ DataManager.AlertInfoS.data.get(i).data.size()+")");
					}
				}
				break;
			case "责令改正":
				for(int i=0;i<DataManager.AlertInfoS.data.size();i++){
					if(DataManager.AlertInfoS.data.get(i).type.equals("zlgz")){
						tv1.setText(arrays1[position]+"("+ DataManager.AlertInfoS.data.get(i).data.size()+")");
					}
				}
				break;
			case "欠贷信息":
				for(int i=0;i<DataManager.AlertInfoS.data.size();i++){
					if(DataManager.AlertInfoS.data.get(i).type.equals("qdxx")){
						tv1.setText(arrays1[position]+"("+ DataManager.AlertInfoS.data.get(i).data.size()+")");
					}
				}
				break;
			case "欠税信息":
				for(int i=0;i<DataManager.AlertInfoS.data.size();i++){
					if(DataManager.AlertInfoS.data.get(i).type.equals("qsxx")){
						tv1.setText(arrays1[position]+"("+ DataManager.AlertInfoS.data.get(i).data.size()+")");
					}
				}
				break;
			case "欠薪信息":
				for(int i=0;i<DataManager.AlertInfoS.data.size();i++){
					if(DataManager.AlertInfoS.data.get(i).type.equals("qxxx")){
						tv1.setText(arrays1[position]+"("+ DataManager.AlertInfoS.data.get(i).data.size()+")");
					}
				}
				break;
		}
		return convertView;
	}

}
