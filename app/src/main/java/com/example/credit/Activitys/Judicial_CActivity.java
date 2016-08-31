package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.credit.Adapters.JudicialAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.Arrays;
import java.util.List;

/**
 * 司法信息详情页
 */
public class Judicial_CActivity extends BaseActivity {
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;

    @ViewInject(R.id.juh_tit)
    TextView juh_tit;
    @ViewInject(R.id.jst_sc)
    ScrollView js_sc;
    @ViewInject(R.id.jcListView1)
    MyListView jcListView1;

    int position;
    String state;
    JudicialAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judicial__c);
        ViewUtils.inject(this);
        b_topname.setText("司法信息");
        js_sc.smoothScrollTo(0,20);
        Intent i = getIntent();
        position = i.getIntExtra("postion", 0);
        state = i.getStringExtra("state");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        juh_tit.setText(state);
        init();
    }
    public void init(){
        List<String> list1 = Arrays.asList(getResources().getStringArray(R.array.JudicialDocuments));//司法文书信息
        List<String> list2 = Arrays.asList(getResources().getStringArray(R.array.CrackCredit));//失信被执行人信息
        List<String> list3 = Arrays.asList(getResources().getStringArray(R.array.ShareholderInformationChange));//股东变更信息
        List<String> list4 = Arrays.asList(getResources().getStringArray(R.array.FrozenInformation));//股权冻结信息

        if (state.equals(list1.get(0))){
            adapter=new JudicialAdapter(Judicial_CActivity.this, DataManager.JudicialDocumentsList,null,null,null,list1);
        }else if (state.equals(list2.get(0))){
            adapter=new JudicialAdapter(Judicial_CActivity.this, null,DataManager.CrackCreditList,null,null,list2);
        }else if (state.equals(list3.get(0))){
            adapter=new JudicialAdapter(Judicial_CActivity.this, null,null,DataManager.ShareholderInformationChangeList,null,list3);
        }else if (state.equals(list4.get(0))){
            adapter=new JudicialAdapter(Judicial_CActivity.this,null,null,null,DataManager.FrozenInformationList,list4);
        }
        jcListView1.setAdapter(adapter);
    }
}
