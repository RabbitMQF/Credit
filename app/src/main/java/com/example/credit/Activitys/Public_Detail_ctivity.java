package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.credit.Adapters.AllDetailsAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 【专利】，【处罚】，【著作】，【商标】，【出质】详情界面
 */
public class Public_Detail_ctivity extends BaseActivity {
    @ViewInject(R.id.cd_return)
    ImageView cd_return;
    @ViewInject(R.id.cd_topname)
    TextView cd_topname;

    @ViewInject(R.id.cd_cs)
    ScrollView cd_cs;
    @ViewInject(R.id.cdListView1)
    MyListView cdListView1;


    int position;
    String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_details);
        ViewUtils.inject(this);
        cd_cs.smoothScrollTo(0,20);
        Intent i = getIntent();
        position = i.getIntExtra("postion", 0);
        state = i.getStringExtra("state");
        cd_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        init();
    }

    public void init() {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        if (state.equals("patent")) {//专利
            list1.add(DataManager.patentInfoList.get(position).PATENTNAME);
            list1.add(DataManager.patentInfoList.get(position).RCODE);
            list1.add(DataManager.patentInfoList.get(position).RDATE);
            list1.add(DataManager.patentInfoList.get(position).ACODE);
            list1.add(DataManager.patentInfoList.get(position).ADATE);
            list1.add(DataManager.patentInfoList.get(position).INVENTOR);
            list1.add(DataManager.patentInfoList.get(position).PATENTTYPE);
            list1.add(DataManager.patentInfoList.get(position).AGENCY);
            list1.add(DataManager.patentInfoList.get(position).LEGALSTATUS);
            list1.add(DataManager.patentInfoList.get(position).DETAILINFO);
            list2 = Arrays.asList(getResources().getStringArray(R.array.patent_c));

        } else if (state.equals("punish")) {//处罚
            list1.add(DataManager.punishInfoList.get(position).ILLEGACTTYPE);
            list1.add(DataManager.punishInfoList.get(position).PENCONTENT);
            list1.add(DataManager.punishInfoList.get(position).PENDECNO);
            list1.add(DataManager.punishInfoList.get(position).JUDAUTH);
            list1.add(DataManager.punishInfoList.get(position).PENDECISSDATE);
            list1.add(DataManager.punishInfoList.get(position).Remark);
            list2 = Arrays.asList(getResources().getStringArray(R.array.punish_c));

        } else if (state.equals("copyright")) {//著作
            list1.add(DataManager.copyrightInfoList.get(position).WORKNAME);
            list1.add(DataManager.copyrightInfoList.get(position).REGISTERDATA);
            list1.add(DataManager.copyrightInfoList.get(position).REGISTERID);
            list1.add(DataManager.copyrightInfoList.get(position).WORKCLASS);
            list1.add(DataManager.copyrightInfoList.get(position).FINISHDATE);
            list1.add(DataManager.copyrightInfoList.get(position).FIRSTDATE);
            list2 = Arrays.asList(getResources().getStringArray(R.array.copyright_c));

        } else if (state.equals("trademark")) {//商标
            list1.add(DataManager.trademarkInfoList.get(position).REGCORE);
            list1.add(DataManager.trademarkInfoList.get(position).BRANDNAME);
            list1.add(DataManager.trademarkInfoList.get(position).APPLICATIONDATE);
            list1.add(DataManager.trademarkInfoList.get(position).AGENCY);
            list1.add(DataManager.trademarkInfoList.get(position).ENTNAME);
            list1.add(DataManager.trademarkInfoList.get(position).APPLICANT);
            list1.add(DataManager.trademarkInfoList.get(position).LIFESPAN);
            list2 = Arrays.asList(getResources().getStringArray(R.array.trademark_c));

        } else if (state.equals("pledge")) {//出质
            list1.add(DataManager.pledgeInfoList.get(position).REGNO);//股权所在公司注册号
            list1.add(DataManager.pledgeInfoList.get(position).EQUITYNO);//股权登记编号
            list1.add(DataManager.pledgeInfoList.get(position).PLEDGOR);//出质人
            list1.add(DataManager.pledgeInfoList.get(position).PLEDBLICNO);//出质人证照号
            list1.add(DataManager.pledgeInfoList.get(position).PLEDBLICTYPE_CN);//出质人证件类型
            list1.add(DataManager.pledgeInfoList.get(position).IMPAM);//出质股权数额
            list1.add(DataManager.pledgeInfoList.get(position).IMPORG);//质权人
            list1.add(DataManager.pledgeInfoList.get(position).IMPORGBLICNO);//质权人证照号
            list1.add(DataManager.pledgeInfoList.get(position).ENTNAME);//股权所在公司名称
            list1.add(DataManager.pledgeInfoList.get(position).EQUPLEDATE);//股权出质登记日期
            list1.add(DataManager.pledgeInfoList.get(position).PUBLICDATE);//公示日期
            list2 = Arrays.asList(getResources().getStringArray(R.array.pledge_c));

        } else if (state.equals("333")) {

        }
        AllDetailsAdapter adapter = new AllDetailsAdapter(Public_Detail_ctivity.this, list1, list2);
        cdListView1.setAdapter(adapter);
    }
}
