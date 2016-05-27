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
 * 著作权详情界面
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
            list1.add(DataManager.patentInfoList.get(position).patentName);
            list1.add(DataManager.patentInfoList.get(position).applyNum);
            list1.add(DataManager.patentInfoList.get(position).applyDate);
            list1.add(DataManager.patentInfoList.get(position).applyPublishNum);
            list1.add(DataManager.patentInfoList.get(position).applyPublishDate);
            list1.add(DataManager.patentInfoList.get(position).inventor);
            list1.add(DataManager.patentInfoList.get(position).type);
            list1.add(DataManager.patentInfoList.get(position).patentAgency);
            list1.add(DataManager.patentInfoList.get(position).LegalStatus);
            list1.add(DataManager.patentInfoList.get(position).abstracts);
            list2 = Arrays.asList(getResources().getStringArray(R.array.patent_c));

        } else if (state.equals("punish")) {//处罚
            list1.add(DataManager.punishInfoList.get(position).type);
            list1.add(DataManager.punishInfoList.get(position).centent);
            list1.add(DataManager.punishInfoList.get(position).documentNumber);
            list1.add(DataManager.punishInfoList.get(position).organs);
            list1.add(DataManager.punishInfoList.get(position).time);
            list1.add(DataManager.punishInfoList.get(position).detail);
            list2 = Arrays.asList(getResources().getStringArray(R.array.punish_c));

        } else if (state.equals("copyright")) {//著作
            list1.add(DataManager.copyrightInfoList.get(position).literatureName);
            list1.add(DataManager.copyrightInfoList.get(position).registerDate);
            list1.add(DataManager.copyrightInfoList.get(position).registerNum);
            list1.add(DataManager.copyrightInfoList.get(position).category);
            list1.add(DataManager.copyrightInfoList.get(position).literatureCompleteData);
            list1.add(DataManager.copyrightInfoList.get(position).firstPublishDate);
            list2 = Arrays.asList(getResources().getStringArray(R.array.copyright_c));

        } else if (state.equals("trademark")) {//商标
            list1.add(DataManager.trademarkInfoList.get(position).registeredName);
            list1.add(DataManager.trademarkInfoList.get(position).applyNum);
            list1.add(DataManager.trademarkInfoList.get(position).applyDate);
            list1.add(DataManager.trademarkInfoList.get(position).RecognizedAuthority);
            list1.add(DataManager.trademarkInfoList.get(position).applyPublishDate);
            list1.add(DataManager.trademarkInfoList.get(position).identifiedDate);
            list2 = Arrays.asList(getResources().getStringArray(R.array.trademark_c));

        } else if (state.equals("pledge")) {//出质
            list1.add(DataManager.pledgeInfoList.get(position).registerNum);
            list1.add(DataManager.pledgeInfoList.get(position).pledgor);
            list1.add(DataManager.pledgeInfoList.get(position).pledgorCertificateNum);
            list1.add(DataManager.pledgeInfoList.get(position).pledgedEquityNum);
            list1.add(DataManager.pledgeInfoList.get(position).pledgee);
            list1.add(DataManager.pledgeInfoList.get(position).pledgeeCertificateNum);
            list1.add(DataManager.pledgeInfoList.get(position).equityPledge);
            list1.add(DataManager.pledgeInfoList.get(position).setRegisterDate);
            list1.add(DataManager.pledgeInfoList.get(position).status);
            list1.add(DataManager.pledgeInfoList.get(position).registerAndChangeTime);
            list1.add(DataManager.pledgeInfoList.get(position).registerAndChangeContent);
            list2 = Arrays.asList(getResources().getStringArray(R.array.pledge_c));

        } else if (state.equals("333")) {//333

        }
        AllDetailsAdapter adapter = new AllDetailsAdapter(Public_Detail_ctivity.this, list1, list2);
        cdListView1.setAdapter(adapter);
    }
}
