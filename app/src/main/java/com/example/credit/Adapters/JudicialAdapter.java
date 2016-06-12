package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager.JudicialDocuments;
import com.example.credit.Entitys.DataManager.CrackCredit;
import com.example.credit.Entitys.DataManager.ShareholderInformationChange;
import com.example.credit.Entitys.DataManager.FrozenInformation;
import com.example.credit.R;
import com.example.credit.Views.MyListView;

import java.util.ArrayList;
import java.util.List;

public class JudicialAdapter extends BaseAdapter {
    private Context context;
    private List<JudicialDocuments> list1;
    private List<CrackCredit> list2;
    private List<ShareholderInformationChange> list3;
    private List<FrozenInformation> list4;
    private List<String> lists;

    public JudicialAdapter(Context context, List<JudicialDocuments> list1, List<CrackCredit> list2,List<ShareholderInformationChange> list3,List<FrozenInformation> list4,List<String> lists) {
        this.context = context;
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        if(list1!=null && list1.size()>0){
            return list1.size();
        }
        if(list2!=null && list2.size()>0){
            return list2.size();
        }
        if(list3!=null && list3.size()>0){
            return list3.size();
        }
        if(list4!=null && list4.size()>0){
            return list4.size();
        }else{
            return 0;
        }

    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder vh = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_judicial__c_item, null);
            vh = new ViewHolder();
            vh.jListView_item = (MyListView) view.findViewById(R.id.jListView_item);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        List<String> listst=new ArrayList<>();
        if(list1!=null && list1.size()>0){
            listst.add(list1.get(position).centent);
            listst.add(list1.get(position).number);
            listst.add(list1.get(position).unit);
            listst.add(list1.get(position).decisiontime);
            listst.add(list1.get(position).detail);
        }
        if(list2!=null && list2.size()>0){
            listst.add(list2.get(position).court);
            listst.add(list2.get(position).jurisdictionTime);
            listst.add(list2.get(position).caseNumber);
            listst.add(list2.get(position).referenceNumber);
            listst.add(list2.get(position).performCondition);
            listst.add(list2.get(position).peopleCondition);
            listst.add(list2.get(position).detail);
        }
        if(list3!=null && list3.size()>0){
            listst.add(list3.get(position).executedPerson);
            listst.add(list3.get(position).equityAmount);
            listst.add(list3.get(position).acceptPerson);
            listst.add(list3.get(position).courtOfExecution);
            listst.add(list3.get(position).detail);
        }
        if(list4!=null && list4.size()>0){
            listst.add(list4.get(position).FROFROM);
            listst.add(list4.get(position).FROZDEADLINE);
            listst.add(list4.get(position).FROAUTH);
            listst.add(list4.get(position).INVTYPE_CN);
            listst.add(list4.get(position).FROAM);
        }
        Judicial_cItemAdapter adapter=new Judicial_cItemAdapter(context,listst,lists);
        vh.jListView_item.setAdapter(adapter);
        return view;
    }

    public class ViewHolder {
        MyListView jListView_item;
    }
}
