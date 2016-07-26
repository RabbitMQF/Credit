package com.WSZW.Activity;

import java.util.ArrayList;
import java.util.List;

import com.WSZW.adapter.ZwFw_bmfw_listveiw_adapter;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.Zwfw_bmfw_listView_item;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_Zwfu_Main_4 extends Fragment{
	 private String TAG = "Fragment_Zwfu_Main_4";  
	 InfoFile_ infofile;
	 private Context context;
	  private ListView lv;
	  private TextView tv;
	  private ZwFw_bmfw_listveiw_adapter adapter;
	  private List<Zwfw_bmfw_listView_item > datas= new ArrayList<Zwfw_bmfw_listView_item >();
	  
	  public static Fragment_Zwfu_Main_4 newInstance(String string) {
		  Fragment_Zwfu_Main_4 newFragment =new Fragment_Zwfu_Main_4();
		  return newFragment;
	  }
	  
	    @Override  
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {  
	        View view = inflater.inflate(R.layout.fragment_zwfw_main_4, container, false); 
	        lv=(ListView) view.findViewById(R.id.listView1);
	        datas=Constants.Zwfw_bmfw_listview_item;
	        adapter=new ZwFw_bmfw_listveiw_adapter(this.getActivity(),datas);
	        infofile = new InfoFile_(getActivity());
	        lv.setAdapter(adapter);
	        context = this.getActivity();
	        lv.setOnItemClickListener(new MyListener());
	        return view;  
	    }
	    class MyListener implements OnItemClickListener{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
				infofile.edit().zhutiID().put(datas.get(position).getId() != null 
						? datas.get(position).getId() : "").apply();;
				infofile.edit().zhutiName().put(datas.get(position).getName() != null 
						? datas.get(position).getName() : "").apply();	
				Intent intent=new Intent(Fragment_Zwfu_Main_4.this.getActivity(),ZWFW_List_Activity.class);
				intent.addFlags(2);
				startActivity(intent);
			}
	    }
}
