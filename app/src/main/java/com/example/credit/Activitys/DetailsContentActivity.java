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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import android.view.ViewGroup.LayoutParams;

import com.example.credit.Adapters.Details_content_mylistAdapter;
import com.example.credit.Adapters.FeiZhiAdapter;
import com.example.credit.Adapters.MyGridAdapter3;
import com.example.credit.Adapters.MyGridZYAdapter;
import com.example.credit.Adapters.Support_CAdapter;
import com.example.credit.Entitys.DataManager;
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
//    @ViewInject(R.id.about_top)
//    LinearLayout about_top;//返回

    @ViewInject(R.id.b_return)
    LinearLayout b_return;//返回

    @ViewInject(R.id.b_topname)
    TextView b_topname;//企业名称

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
    GridView myGridView3;//基本信息

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

    @ViewInject(R.id.myGridViewp)
    MyGridView myGridViewp;//投资列表

    @ViewInject(R.id.c_people)
    LinearLayout c_people;//投资列表null

    @ViewInject(R.id.myGridViewZY)
    MyGridView myGridViewZY;//主要人员

    @ViewInject(R.id.c_Cpeople)
    LinearLayout c_Cpeople;//主要人员null

    @ViewInject(R.id.GSmylist)
    MyListView GSmylist;//工商变更

    @ViewInject(R.id.c_GS)
    LinearLayout c_GS;//工商变更null

    @ViewInject(R.id.myGridViewZYfz)
    MyListView myGridViewZYfz;//分支机构

    @ViewInject(R.id.c_fzjg)
    LinearLayout c_impPeoc_fzjgple;//分支机构null

    public String[] arrays3 = {"注册资本", "法定代表人","成立日期","核准日期",
            "工商注册号", "组织机构代码"};
    public String[] arrays4;
    MyGridAdapter3 adapter2;
    MyGridZYAdapter adapterzy;
    String Tname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_content);
        ViewUtils.inject(this);
        Intent i = getIntent();
        Tname = i.getStringExtra("Tname");
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
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        /**
         * 基本信息
         */

        List<String> lt = new ArrayList<String>();
        lt.add(DataManager.gsxx.data.BaseInfo.REGCAP+"");
        if(DataManager.gsxx.data.BaseInfo.NAME!=null&&!DataManager.gsxx.data.BaseInfo.NAME.equals("")&&!DataManager.gsxx.data.BaseInfo.NAME.equals(null)){
            lt.add(DataManager.gsxx.data.BaseInfo.NAME);
        }else{
            lt.add("暂无信息");
        }
        if(DataManager.gsxx.data.BaseInfo.ESTDATE!=null&&!DataManager.gsxx.data.BaseInfo.ESTDATE.equals(null)&&!DataManager.gsxx.data.BaseInfo.ESTDATE.equals("")){
            lt.add(DataManager.gsxx.data.BaseInfo.ESTDATE);
        }else{
            lt.add("暂无信息");
        }
        if(DataManager.gsxx.data.BaseInfo.APPRDATE!=null&&!DataManager.gsxx.data.BaseInfo.APPRDATE.equals(null)&&!DataManager.gsxx.data.BaseInfo.APPRDATE.equals("")){
            lt.add(DataManager.gsxx.data.BaseInfo.APPRDATE);
        }else{
            lt.add("暂无信息");
        }
        if(DataManager.gsxx.data.BaseInfo.UNISCID!=null&&!DataManager.gsxx.data.BaseInfo.UNISCID.equals(null)&&!DataManager.gsxx.data.BaseInfo.UNISCID.equals("")){
            arrays3[4]="统一社会信用代码";
            lt.add(DataManager.gsxx.data.BaseInfo.UNISCID);
        }else{
            if(DataManager.gsxx.data.BaseInfo.REGNO!=null&&!DataManager.gsxx.data.BaseInfo.REGNO.equals(null)&&!DataManager.gsxx.data.BaseInfo.REGNO.equals("")){
                lt.add(DataManager.gsxx.data.BaseInfo.REGNO);
            }else{
                lt.add("暂无信息");
            }
        }

        if(DataManager.gsxx.data.BaseInfo.PRIPID!=null&&!DataManager.gsxx.data.BaseInfo.PRIPID.equals(null)&&!DataManager.gsxx.data.BaseInfo.PRIPID.equals("")){
            lt.add(DataManager.gsxx.data.BaseInfo.PRIPID);
        }else{
            lt.add("暂无信息");
        }

        if(lt.get(0).equals("") || lt.get(0).equals("null") || lt.get(0)==null){
            lt.set(0,"0万元");
        }else{
            lt.set(0,DataManager.gsxx.data.BaseInfo.REGCAP.substring(0,DataManager.gsxx.data.BaseInfo.REGCAP.indexOf(".")) + "万元");
        }

        int size = lt.size();
        arrays4 = (String[]) lt.toArray(new String[size]);
        adapter2 = new MyGridAdapter3(DetailsContentActivity.this, arrays3, arrays4);
        myGridView3.setAdapter(adapter2);
        myGridView3.setSelector(new ColorDrawable(Color.TRANSPARENT));

        b_topname.setText(Tname);
        /**
         * 判断企业类型是否为空
         */
        try{
            if(DataManager.gsxx.data.BaseInfo.ENTTYPE.equals("9999")){
                c_type.setText("个体工商户");//个体工商户
            }else{
                if(DataManager.gsxx.data.BaseInfo.ENTTYPE_CN!=null&&!DataManager.gsxx.data.BaseInfo.ENTTYPE_CN.equals(null)&&!(DataManager.gsxx.data.BaseInfo.ENTTYPE_CN).equals("")){
                    c_type.setText(DataManager.gsxx.data.BaseInfo.ENTTYPE_CN);//企业类型
                }else{
                    c_type.setText("暂无信息");
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        /**
         * 判断经营状态是否为空
         */
        try{
            if(DataManager.gsxx.data.BaseInfo.REGSTATE_CN!=null&&!DataManager.gsxx.data.BaseInfo.REGSTATE_CN.equals(null)&&!(DataManager.gsxx.data.BaseInfo.REGSTATE_CN).equals("")){
                c_state.setText(DataManager.gsxx.data.BaseInfo.REGSTATE_CN);//经营状态/
            }else{
                c_state.setText("暂无信息");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        /**
         * 判断经营范围是否为空
         */
        try{
            if(DataManager.gsxx.data.BaseInfo.OPSCOPE!=null&&!DataManager.gsxx.data.BaseInfo.OPSCOPE.equals(null)&&!(DataManager.gsxx.data.BaseInfo.OPSCOPE).equals("")){
                c_fangwei.setText(DataManager.gsxx.data.BaseInfo.OPSCOPE);//经营范围
            }else{
                c_fangwei.setText("暂无信息");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        /**
         * 判断企业地址是否为空
         */
        try{
            if(DataManager.gsxx.data.BaseInfo.DOM!=null&&!DataManager.gsxx.data.BaseInfo.DOM.equals(null)&&!(DataManager.gsxx.data.BaseInfo.DOM).equals("")){
                c_address.setText(DataManager.gsxx.data.BaseInfo.DOM);//企业地址
            }else{
                c_address.setText("暂无信息");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        /**
         * 判断经营期限是否为空
         */
        try{
        if(DataManager.gsxx.data.BaseInfo.OPFROM==null||DataManager.gsxx.data.BaseInfo.OPFROM.equals(null)||DataManager.gsxx.data.BaseInfo.OPFROM.equals("")){
            c_datetiem.setText("**** 至" + DataManager.gsxx.data.BaseInfo.OPTO);
        }else if(DataManager.gsxx.data.BaseInfo.OPTO==null||DataManager.gsxx.data.BaseInfo.OPTO.equals(null)||DataManager.gsxx.data.BaseInfo.OPTO.equals("")){
            c_datetiem.setText(DataManager.gsxx.data.BaseInfo.OPFROM + "至 ****" );
        }else if((DataManager.gsxx.data.BaseInfo.OPFROM!=null&&!DataManager.gsxx.data.BaseInfo.OPFROM.equals(null)&&!(DataManager.gsxx.data.BaseInfo.OPFROM).equals(""))&&DataManager.gsxx.data.BaseInfo.OPTO!=null&&!DataManager.gsxx.data.BaseInfo.OPTO.equals(null)&&!(DataManager.gsxx.data.BaseInfo.OPTO).equals("")){
            c_datetiem.setText(DataManager.gsxx.data.BaseInfo.OPFROM + "至" + DataManager.gsxx.data.BaseInfo.OPTO);//经营期限
        }else if(DataManager.gsxx.data.BaseInfo.OPFROM==null&&DataManager.gsxx.data.BaseInfo.OPTO==null){
            c_datetiem.setText("暂无信息");
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        /**
         * 判断登记机关是否为空
         */
        try{
        if(DataManager.gsxx.data.BaseInfo.REGORG_CN==null||DataManager.gsxx.data.BaseInfo.REGORG_CN.equals(null)||(DataManager.gsxx.data.BaseInfo.REGORG_CN).equals("")){
            c_djjg.setText("暂无信息");
        }else{
            c_djjg.setText(DataManager.gsxx.data.BaseInfo.REGORG_CN);//登记机关
        }
        }catch(Exception e){
            e.printStackTrace();
        }

        /**
         * 投资人员
         */
        List<String> p1 = new ArrayList<String>();//股东类型list
        List<String> p2 = new ArrayList<String>();//人员list
        if((DataManager.gsxx.data.Partners)!=null && (DataManager.gsxx.data.Partners).size()>0){
            for (DataManager.GSXX.DataBean.PartnersBean p: DataManager.gsxx.data.Partners) {
                p1.add(p.INVTYPE_CN);
                p2.add(p.INV);
            }
            int size11 = p1.size();
            int size21 = p2.size();
            String[] arrayszy11 = (String[]) p1.toArray(new String[size11]);
            String[] arrayszy21 = (String[]) p2.toArray(new String[size21]);
            adapterzy = new MyGridZYAdapter(DetailsContentActivity.this, arrayszy11, arrayszy21);
            myGridViewp.setAdapter(adapterzy);
            myGridViewp.setSelector(new ColorDrawable(Color.TRANSPARENT));
        }else{
            c_people.setVisibility(View.VISIBLE);
            myGridViewp.setVisibility(View.GONE);
        }

        /**
         * 主要人员
         */
        List<String> zy1 = new ArrayList<String>();//职位list
        List<String> zy2 = new ArrayList<String>();//人员list
        if((DataManager.gsxx.data.Employees)!=null && (DataManager.gsxx.data.Employees).size()>0){
            for (DataManager.GSXX.DataBean.EmployeesBean e : DataManager.gsxx.data.Employees) {
                zy1.add(e.POSITION_CN);
                zy2.add(e.NAME);
            }
            int size1 = zy1.size();
            int size2 = zy2.size();
            String[] arrayszy1 = (String[]) zy1.toArray(new String[size1]);
            String[] arrayszy2 = (String[]) zy2.toArray(new String[size2]);
            adapterzy = new MyGridZYAdapter(DetailsContentActivity.this, arrayszy1, arrayszy2);
            myGridViewZY.setAdapter(adapterzy);
            myGridViewZY.setSelector(new ColorDrawable(Color.TRANSPARENT));
        }else{
            c_Cpeople.setVisibility(View.VISIBLE);
            myGridViewZY.setVisibility(View.GONE);
        }
        /**
         * 分支机构
         */
        if((DataManager.gsxx.data.AnnualReports)!=null && (DataManager.gsxx.data.AnnualReports).size()>0){
            FeiZhiAdapter hcadapter2=new FeiZhiAdapter(DetailsContentActivity.this, DataManager.gsxx.data.AnnualReports);
            myGridViewZYfz.setAdapter(hcadapter2);
        }else{
            c_impPeoc_fzjgple.setVisibility(View.VISIBLE);
            myGridViewZYfz.setVisibility(View.GONE);
        }

    }

    /**
     * 工商变更数据
     */
    public void GSchages() {
        List<DataManager.ChangeTime> clisttimeD = new ArrayList<>();//变更集合
        List<DataManager.ChangeData> clistd1 = new ArrayList<>();//变更信息临时仓库

        List<DataManager.GSXX.DataBean.ChangeRecordsBean> clistc = DataManager.gsxx.data.ChangeRecords;//工商变更信息
        if(( DataManager.gsxx.data.ChangeRecords)!=null && ( DataManager.gsxx.data.ChangeRecords).size()>0){//判断数据是否为空
            for (int i = 0; i <clistc.size(); i++) {//数据打乱并按时间重新分组
                if (i > 0) {
                    if (clistc.get(i).ALTDATE.equals(clistc.get(i - 1).ALTDATE)) {//当下标为i的日期与下标为i-1的日期 相等 时
                        DataManager.ChangeData cc2 = new DataManager.ChangeData();
                        cc2.ALTDATE = clistc.get(i).ALTDATE;
                        cc2.ALTITEM_CN = clistc.get(i).ALTITEM_CN;
                        cc2.ALTBE = clistc.get(i).ALTBE;
                        cc2.ALTAF = clistc.get(i).ALTAF;
                        clistd1.add(cc2);//把数据追加入临时仓库

                        if(i==clistc.size()-1){//当i==数据最大size时(数据的最后结算位置，直接加入变更集合)
                            DataManager.ChangeTime ccr = new DataManager.ChangeTime();
                            ccr.ALTDATE = clistd1.get(0).ALTDATE;
                            ccr.changedata = clistd1;
                            clisttimeD.add(ccr);//则把最后变更信息临时仓库中的全部数据加入变更集合中
                        }
                    } else {//当下标为i的日期与下标为i-1的日期 不相等 时（因为这里是结算的位置，所以要使用中转仓库，不然直接使用临时仓库会使变更集合中的数据错乱）
                        DataManager.ChangeTime ccr = new DataManager.ChangeTime();//变更中转仓库1
                        ccr.ALTDATE = clistd1.get(0).ALTDATE;
                        ccr.changedata = clistd1;
                        clisttimeD.add(ccr);//先把临时仓库中的数据加入变更集合中

                        List<DataManager.ChangeData> clistdtest = new ArrayList<>();//变更中转仓库2
                        DataManager.ChangeData cc3 = new DataManager.ChangeData();
                        cc3.ALTDATE = clistc.get(i).ALTDATE;
                        cc3.ALTITEM_CN = clistc.get(i).ALTITEM_CN;
                        cc3.ALTBE = clistc.get(i).ALTBE;
                        cc3.ALTAF = clistc.get(i).ALTAF;
                        clistdtest.add(cc3);//把数据加入临时仓库
                        clistd1 = clistdtest;
                    }
                } else {//下标为0的数据直接加入临时仓库
                    DataManager.ChangeData cc1 = new DataManager.ChangeData();
                    cc1.ALTDATE = clistc.get(i).ALTDATE;
                    cc1.ALTITEM_CN = clistc.get(i).ALTITEM_CN;
                    cc1.ALTBE = clistc.get(i).ALTBE;
                    cc1.ALTAF = clistc.get(i).ALTAF;
                    clistd1.add(cc1);
                }
            }
            Details_content_mylistAdapter adapter1 = new Details_content_mylistAdapter(DetailsContentActivity.this, clisttimeD);
            GSmylist.setAdapter(adapter1);
        }else{
            GSmylist.setVisibility(View.GONE);
            c_GS.setVisibility(View.VISIBLE);
        }

    }

}
