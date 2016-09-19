package com.example.credit.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.credit.Adapters.Admin_Adapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;

import java.util.List;

/**
 * Created by alucard on 2016-05-25.
 */
public class Admin_Fragment extends Fragment {
    ListView listView;
    Admin_Adapter admin_adapter;
    List<DataManager.administraton> amdinList;
    List<DataManager.admin_other> otherList;//空其它信息数据

    public static Admin_Fragment newInstance(){
        Admin_Fragment fragment=new Admin_Fragment();
        return fragment;
    }
    public void setListData(List<DataManager.administraton> amdinList,List<DataManager.admin_other> otherList){
        this.amdinList=amdinList;
        this.otherList=otherList;//空其它信息数据
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mortgage,container,false);
        listView= (ListView) v.findViewById(R.id.mortgage_listview);
        admin_adapter=new Admin_Adapter(getActivity(),amdinList,otherList);
        listView.setAdapter(admin_adapter);

        return v;
    }
}
