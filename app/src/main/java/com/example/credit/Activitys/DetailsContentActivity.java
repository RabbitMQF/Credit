package com.example.credit.Activitys;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import android.view.ViewGroup.LayoutParams;

import com.example.credit.Adapters.Details_content_mylistAdapter;
import com.example.credit.Adapters.MyGridAdapter3;
import com.example.credit.Adapters.MyGridZYAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.Entitys.changeContent;
import com.example.credit.Entitys.changeResult;
import com.example.credit.R;
import com.example.credit.Views.MyGridView;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 工商信息主界面
 */
public class DetailsContentActivity extends BaseActivity {
    @ViewInject(R.id.c_return)
    ImageView c_return;//返回

    @ViewInject(R.id.topname)
    TextView topname;//企业名称

    @ViewInject(R.id.c_tab1)
    Button c_tab1;
    @ViewInject(R.id.c_tab2)
    Button c_tab2;
    @ViewInject(R.id.c_tab3)
    Button c_tab3;
    @ViewInject(R.id.c_tab4)
    Button c_tab4;
    @ViewInject(R.id.c_tab5)
    Button c_tab5;

    @ViewInject(R.id.c_scr)
    ScrollView c_scr;

    @ViewInject(R.id.c_tv1)
    TextView c_tv1;
    @ViewInject(R.id.c_tv2)
    TextView c_tv2;
    @ViewInject(R.id.c_tv3)
    TextView c_tv3;
    @ViewInject(R.id.c_tv4)
    TextView c_tv4;
    @ViewInject(R.id.c_tv5)
    TextView c_tv5;

    private boolean hasMeasure = false;
    private int pointY[];

    @ViewInject(R.id.myGridView3)
    GridView myGridView3;//企业类型

    @ViewInject(R.id.c_type)
    TextView c_type;//企业类型

    @ViewInject(R.id.c_state)
    TextView c_state;//经营状态

    @ViewInject(R.id.c_fangwei)
    TextView c_fangwei;//经营范围

    @ViewInject(R.id.c_address)
    TextView c_address;//企业地址

    @ViewInject(R.id.c_datetiem)
    TextView c_datetiem;//经营期限

    @ViewInject(R.id.c_djjg)
    TextView c_djjg;//登记机关

    @ViewInject(R.id.c_people)
    TextView c_people;//投资人

    @ViewInject(R.id.myGridViewZY)
    MyGridView myGridViewZY;//主要人员

    @ViewInject(R.id.GSmylist)
    MyListView GSmylist;//工商变更

    @ViewInject(R.id.c_fzjg)
    TextView c_impPeoc_fzjgple;//分支机构

    List<DataManager.search> dcList = new ArrayList<DataManager.search>();
    public String[] arrays3 = {"注册资本", "法定代表", "发照日期", "成立日期",
            "工商注册号", "组织机构代码"};
    public String[] arrays4;
    MyGridAdapter3 adapter2;
    MyGridZYAdapter adapterzy;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_content);
        ViewUtils.inject(this);
        Intent i = getIntent();
        position = i.getIntExtra("position", 0);
        init();
        ScrIndex();
        GSchages();
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void ScrIndex() {
        pointY = new int[5];
        ViewTreeObserver observer = c_tv1.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (hasMeasure) {

                } else {
                    int height = c_tv1.getMeasuredHeight();
                    LayoutParams params = c_tv1.getLayoutParams();
                    pointY[0] = c_tv1.getTop();
                    pointY[1] = c_tv2.getTop();
                    pointY[2] = c_tv3.getTop();
                    pointY[3] = c_tv4.getTop();
                    pointY[4] = c_tv5.getTop();
                    hasMeasure = true;
                }
                return true;
            }

        });

        c_scr.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int s = c_scr.getScrollY();
                int x = pointY[2];
                if (c_scr.getScrollY() < pointY[0] || (c_scr.getScrollY() >= pointY[0] && c_scr.getScrollY() < pointY[1])) {
                    c_tab1.setBackgroundResource(R.drawable.details_con_tabbg1);
                    c_tab1.setTextColor(getResources().getColor(R.color.white));

                    c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab2.setTextColor(getResources().getColor(R.color.black));
                    c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab3.setTextColor(getResources().getColor(R.color.black));
                    c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab4.setTextColor(getResources().getColor(R.color.black));
                    c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab5.setTextColor(getResources().getColor(R.color.black));

                } else if (c_scr.getScrollY() >= pointY[1] && c_scr.getScrollY() < pointY[2]) {
                    c_tab2.setBackgroundResource(R.drawable.details_con_tabbg1);
                    c_tab2.setTextColor(getResources().getColor(R.color.white));

                    c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab1.setTextColor(getResources().getColor(R.color.black));
                    c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab3.setTextColor(getResources().getColor(R.color.black));
                    c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab4.setTextColor(getResources().getColor(R.color.black));
                    c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab5.setTextColor(getResources().getColor(R.color.black));

                } else if (c_scr.getScrollY() >= pointY[2] && c_scr.getScrollY() < pointY[3]) {
                    c_tab3.setBackgroundResource(R.drawable.details_con_tabbg1);
                    c_tab3.setTextColor(getResources().getColor(R.color.white));

                    c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab1.setTextColor(getResources().getColor(R.color.black));
                    c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab2.setTextColor(getResources().getColor(R.color.black));
                    c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab4.setTextColor(getResources().getColor(R.color.black));
                    c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab5.setTextColor(getResources().getColor(R.color.black));
                } else if (c_scr.getScrollY() >= pointY[3] && c_scr.getScrollY() < pointY[4]) {
                    c_tab4.setBackgroundResource(R.drawable.details_con_tabbg1);
                    c_tab4.setTextColor(getResources().getColor(R.color.white));

                    c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab1.setTextColor(getResources().getColor(R.color.black));
                    c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab2.setTextColor(getResources().getColor(R.color.black));
                    c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab3.setTextColor(getResources().getColor(R.color.black));
                    c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab5.setTextColor(getResources().getColor(R.color.black));
                } else if (c_scr.getScrollY() >= pointY[4]) {
                    c_tab5.setBackgroundResource(R.drawable.details_con_tabbg1);
                    c_tab5.setTextColor(getResources().getColor(R.color.white));

                    c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab1.setTextColor(getResources().getColor(R.color.black));
                    c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab2.setTextColor(getResources().getColor(R.color.black));
                    c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab3.setTextColor(getResources().getColor(R.color.black));
                    c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab4.setTextColor(getResources().getColor(R.color.black));
                }
                return false;
            }
        });

    }

    public void tabshow(View v) {
        switch (v.getId()) {
            case R.id.c_tab1:
                c_scr.smoothScrollTo(0, pointY[0]);
                c_tab1.setBackgroundResource(R.drawable.details_con_tabbg1);
                c_tab1.setTextColor(getResources().getColor(R.color.white));

                c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab2.setTextColor(getResources().getColor(R.color.black));
                c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab3.setTextColor(getResources().getColor(R.color.black));
                c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab4.setTextColor(getResources().getColor(R.color.black));
                c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab5.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.c_tab2:
                c_scr.smoothScrollTo(0, pointY[1]);
                c_tab2.setBackgroundResource(R.drawable.details_con_tabbg1);
                c_tab2.setTextColor(getResources().getColor(R.color.white));

                c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab1.setTextColor(getResources().getColor(R.color.black));
                c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab3.setTextColor(getResources().getColor(R.color.black));
                c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab4.setTextColor(getResources().getColor(R.color.black));
                c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab5.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.c_tab3:
                c_scr.smoothScrollTo(0, pointY[2]);
                c_tab3.setBackgroundResource(R.drawable.details_con_tabbg1);
                c_tab3.setTextColor(getResources().getColor(R.color.white));

                c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab1.setTextColor(getResources().getColor(R.color.black));
                c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab2.setTextColor(getResources().getColor(R.color.black));
                c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab4.setTextColor(getResources().getColor(R.color.black));
                c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab5.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.c_tab4:
                c_scr.smoothScrollTo(0, pointY[3]);
                c_tab4.setBackgroundResource(R.drawable.details_con_tabbg1);
                c_tab4.setTextColor(getResources().getColor(R.color.white));

                c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab1.setTextColor(getResources().getColor(R.color.black));
                c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab2.setTextColor(getResources().getColor(R.color.black));
                c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab3.setTextColor(getResources().getColor(R.color.black));
                c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab5.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.c_tab5:
                c_scr.smoothScrollTo(0, pointY[4]);
                c_tab5.setBackgroundResource(R.drawable.details_con_tabbg1);
                c_tab5.setTextColor(getResources().getColor(R.color.white));

                c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab1.setTextColor(getResources().getColor(R.color.black));
                c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab2.setTextColor(getResources().getColor(R.color.black));
                c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab3.setTextColor(getResources().getColor(R.color.black));
                c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab4.setTextColor(getResources().getColor(R.color.black));
                break;
        }
    }

    public void init() {
        c_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        dcList = DataManager.searchList;
        topname.setText(dcList.get(position).entname);//企业名称
        c_type.setText(dcList.get(position).ENTTYPE_CN);//企业类型
        c_state.setText(dcList.get(position).REGSTATE_CN);//经营状态/
        c_fangwei.setText(dcList.get(position).OPSCOPE);//经营范围
        c_address.setText(dcList.get(position).DOM);//企业地址
        c_datetiem.setText(dcList.get(position).OPFROM + "至" + dcList.get(position).OPTO);//经营期限
        c_djjg.setText(dcList.get(position).OPTO);//登记机关
        c_people.setText(dcList.get(position).NAME);//投资人


        List<String> lt = new ArrayList<String>();
        lt.add(dcList.get(position).REGCAP + "万元");
        lt.add(dcList.get(position).NAME);
        lt.add(dcList.get(position).OPFROM);
        lt.add(dcList.get(position).OPFROM);
        lt.add(dcList.get(position).REGNO);
        lt.add(dcList.get(position).INDUSTRYPHY);
        int size = lt.size();
        arrays4 = (String[]) lt.toArray(new String[size]);
        adapter2 = new MyGridAdapter3(DetailsContentActivity.this, arrays3, arrays4);
        myGridView3.setAdapter(adapter2);
        myGridView3.setSelector(new ColorDrawable(Color.TRANSPARENT));

//        List<String> zy1 = new ArrayList<String>();//职位list
//
//        List<String> zy2 = new ArrayList<String>();//人员list
//
//        int size1 = zy1.size();
//        int size2 = zy2.size();
//        String[] arrayszy1 = (String[]) zy1.toArray(new String[size1]);
//        String[] arrayszy2 = (String[]) zy2.toArray(new String[size2]);
//        adapterzy = new MyGridZYAdapter(DetailsContentActivity.this, arrayszy1, arrayszy2);
//        myGridView3.setAdapter(adapter2);
//        myGridView3.setSelector(new ColorDrawable(Color.TRANSPARENT));
    }

    /**
     * 工商变更数据
     */
    public void GSchages() {
        List<changeResult> clisttimeD = new ArrayList<>();//变更集合
        List<changeContent> clistd = new ArrayList<>();//变更信息临时仓库

        List<DataManager.ChangeRecords> clistc = DataManager.Data0List.get(0).ChangeRecords;//工商变更信息

        for (int i = 0; i < clistc.size(); i++) {//重新分组
            if (i > 0) {
                if (clistc.get(i).ALTDATE.equals(clistc.get(i - 1).ALTDATE)) {//当下标为i的日期与下标为i-1的日期 相等 时
                    changeContent cc2=new changeContent();
                    cc2.setALTDATE(clistc.get(i).ALTDATE);
                    cc2.setALTITEM_CN(clistc.get(i).ALTITEM_CN);
                    cc2.setALTBE(clistc.get(i).ALTBE);
                    cc2.setALTAF(clistc.get(i).ALTAF);
                    clistd.add(cc2);//把数据追加入临时仓库
                } else {//当下标为i的日期与下标为i-1的日期 不相等 时
                    changeResult ccr=new changeResult();
                    ccr.setALTDATE(clistd.get(0).ALTDATE);
                    ccr.setChangedata(clistd);
                    clisttimeD.add(ccr);//先把临时仓库中的数据加入变更集合中
                    clistd.clear();//然后清空临时仓库

                    changeContent cc3=new changeContent();
                    cc3.setALTDATE(clistc.get(i).ALTDATE);
                    cc3.setALTITEM_CN(clistc.get(i).ALTITEM_CN);
                    cc3.setALTBE(clistc.get(i).ALTBE);
                    cc3.setALTAF(clistc.get(i).ALTAF);
                    clistd.add(cc3);//把数据加入临时仓库
                }
            } else {//下标为0的数据直接加入临时仓库
                changeContent cc1=new changeContent();
                cc1.setALTDATE(clistc.get(i).ALTDATE);
                cc1.setALTITEM_CN(clistc.get(i).ALTITEM_CN);
                cc1.setALTBE(clistc.get(i).ALTBE);
                cc1.setALTAF(clistc.get(i).ALTAF);
                clistd.add(cc1);
            }
        }
        Details_content_mylistAdapter adapter1 = new Details_content_mylistAdapter(DetailsContentActivity.this, clisttimeD);
        GSmylist.setAdapter(adapter1);
    }


}
