package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
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
 * 主界面的【专利】，【商标】详情界面
 */
public class Main_Public_Detail_ctivity extends BaseActivity {
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;

    @ViewInject(R.id.cd_cs)
    ScrollView cd_cs;
    @ViewInject(R.id.cdListView1)
    MyListView cdListView1;


    int position;
    String state,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_details);
        ViewUtils.inject(this);
        cd_cs.smoothScrollTo(0,20);
        Intent i = getIntent();
        position = i.getIntExtra("position", 0);
        state = i.getStringExtra("state");
        type = i.getStringExtra("type");
        b_return.setOnClickListener(new View.OnClickListener() {
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
            b_topname.setText("专利信息");
            list1.add(DataManager.zl_searchS.data.patentInfo.get(position).PATENTNAME);
            list1.add(DataManager.zl_searchS.data.patentInfo.get(position).RCODE);
            list1.add(DataManager.zl_searchS.data.patentInfo.get(position).RDATE);
            list1.add(DataManager.zl_searchS.data.patentInfo.get(position).ACODE);
            list1.add(DataManager.zl_searchS.data.patentInfo.get(position).ADATE);
            list1.add(DataManager.zl_searchS.data.patentInfo.get(position).INVENTOR);
            list1.add(DataManager.zl_searchS.data.patentInfo.get(position).PATENTTYPE);
            list1.add(DataManager.zl_searchS.data.patentInfo.get(position).AGENCY);
            list1.add(DataManager.zl_searchS.data.patentInfo.get(position).LEGALSTATUS);
            list1.add(DataManager.zl_searchS.data.patentInfo.get(position).DETAILINFO);
            list2 = Arrays.asList(getResources().getStringArray(R.array.patent_c));

        } else if (state.equals("punish")) {//处罚
            b_topname.setText("行政处罚");
            list1.add(DataManager.punishInfoList.get(position).ILLEGACTTYPE);
            list1.add(DataManager.punishInfoList.get(position).PENCONTENT);
            list1.add(DataManager.punishInfoList.get(position).PENDECNO);
            list1.add(DataManager.punishInfoList.get(position).JUDAUTH);
            list1.add(DataManager.punishInfoList.get(position).PENDECISSDATE);
            list1.add(DataManager.punishInfoList.get(position).Remark);
            list2 = Arrays.asList(getResources().getStringArray(R.array.punish_c));

        } else if (state.equals("copyright")) {//著作
            b_topname.setText("著作权");
//            String t=type.substring(1,3);
            if(!type.equals("软件")){
                list2 = Arrays.asList(getResources().getStringArray(R.array.copyright_c));
                list1.add(DataManager.copyrightInfoeList.get(position).WORKNAME);
                list1.add(DataManager.copyrightInfoeList.get(position).REGISTERDATA);
                list1.add(DataManager.copyrightInfoeList.get(position).REGISTERID);
                list1.add(DataManager.copyrightInfoeList.get(position).WORKCLASS);
                list1.add(DataManager.copyrightInfoeList.get(position).FINISHDATE);
                list1.add(DataManager.copyrightInfoeList.get(position).FIRSTDATE);

            }else{
                list2 = Arrays.asList(getResources().getStringArray(R.array.copyright_c1));
                list1.add(DataManager.copyrightInfoeList.get(position).SOFTWARENAME);
                list1.add(DataManager.copyrightInfoeList.get(position).REGISTERDATA);
                list1.add(DataManager.copyrightInfoeList.get(position).REGISTERID);
                list1.add(DataManager.copyrightInfoeList.get(position).SOFTWARESHORT);
                list1.add(DataManager.copyrightInfoeList.get(position).STARTINGDATE);
            }



        } else if (state.equals("trademark")) {//商标
            b_topname.setText("商标信息");
            list1.add(DataManager.sb_searchS.data.trademark.get(position).BRANDNAME);
            list1.add(DataManager.sb_searchS.data.trademark.get(position).APPLICATIONDATE);
            list1.add(DataManager.sb_searchS.data.trademark.get(position).APPLICANT);
            list1.add(DataManager.sb_searchS.data.trademark.get(position).BRANDSTAUTS);
            list1.add(DataManager.sb_searchS.data.trademark.get(position).REGCORE);
            list1.add(DataManager.sb_searchS.data.trademark.get(position).CLASSIFYID);
            list1.add(DataManager.sb_searchS.data.trademark.get(position).AGENCY);
            list1.add(DataManager.sb_searchS.data.trademark.get(position).LIFESPAN);
            list2 = Arrays.asList(getResources().getStringArray(R.array.trademark_c));

        } else if (state.equals("pledge")) {//出质
            b_topname.setText("出质信息");
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
        AllDetailsAdapter adapter = new AllDetailsAdapter(Main_Public_Detail_ctivity.this, list1, list2);
        cdListView1.setAdapter(adapter);
    }
}
