package com.example.credit.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.Adapters.Mortgage_Adapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;

import java.util.List;

/**
 * Created by alucard on 2016-05-24.
 */

/**
 * Simple Fragment which only has one TextView
 */
public class Mortgage_detail_Fragment extends Fragment {
    private String content; //Fragment中显示的内容
    public static final String BUNDLE_TITLE = "titile";
    ListView listView;
    Mortgage_Adapter adapter;
    List<DataManager.Mychattel.DataBean.ChattelBean> listMP;
    List<DataManager.MyrealEstate.DataBean.realEstateBean> listRE;

    public static Mortgage_detail_Fragment newInstance() { //对外提供创建实例的方法，你给我需要显示的内容，我给你Fragment实例
        //this.listMP=listMP;
        //this.listRE=listRE;
        //Bundle bundle = new Bundle();
        //bundle.putString(BUNDLE_TITLE, content);
        Mortgage_detail_Fragment fragment = new Mortgage_detail_Fragment();
        //fragment.setArguments(bundle);
        return fragment;
    }
    public void setListData(List<DataManager.Mychattel.DataBean.ChattelBean> listMP, List<DataManager.MyrealEstate.DataBean.realEstateBean> listRE){
        this.listMP=listMP;
        this.listRE=listRE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //initdatas();
        View v = inflater.inflate(R.layout.fragment_mortgage, container, false);
        listView = (ListView) v.findViewById(R.id.mortgage_listview);
        adapter = new Mortgage_Adapter(getActivity(), listRE, listMP);
        listView.setAdapter(adapter);
        Bundle bundle = getArguments();
        if (bundle != null) {
            content = bundle.getString(BUNDLE_TITLE);
        }



        return v;
    }

    private void initdatas() {
        listRE=DataManager.MyrealEstateS.data.realEstate;
        listMP=DataManager.MychattelS.data.chattel;
    }


}
