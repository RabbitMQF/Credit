package com.WSZW.Activity;

import java.util.ArrayList;
import java.util.List;

import com.WSZW.adapter.ZwFw_bmfw_listveiw_adapter;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.Zwfw_bmfw_listView_item;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class Fragment_Zwfu_Main_6 extends Fragment {
	 private static final String TAG = "Fragment_Zwfu_Main_6";  
	  private ListView lv;
	  InfoFile_ infofile;
	  private ZwFw_bmfw_listveiw_adapter adapter;
	  private List<Zwfw_bmfw_listView_item > datas= new ArrayList<Zwfw_bmfw_listView_item >();
	    @Override  
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {  
	        View view = inflater.inflate(R.layout.fragment_zwfw_main_4, container, false); 
	        lv=(ListView) view.findViewById(R.id.listView1);
	        datas=Constants.Zwfw_xzcf_listview_item;
	        infofile = new InfoFile_(getActivity());
	        adapter=new ZwFw_bmfw_listveiw_adapter(this.getActivity(),datas);
	        lv.setAdapter(adapter);
	        
	        lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					infofile.edit().zhutiID().put(datas.get(arg2).getId() != null 
							? datas.get(arg2).getId() : "").apply();;
					infofile.edit().zhutiName().put(datas.get(arg2).getName() != null 
							? datas.get(arg2).getName() : "").apply();	
					Intent intent=new Intent(Fragment_Zwfu_Main_6.this.getActivity(),ZWFW_List_Activity.class);
					intent.addFlags(3);
					startActivity(intent);
					
				}
			});
			
	        return view;  
	    }
		public static Fragment_Zwfu_Main_6 newInstance(String string) {
			// TODO 自动生成的方法存根
			Fragment_Zwfu_Main_6 newFragment =new Fragment_Zwfu_Main_6();
			return newFragment;
		}
}
