package com.example.credit.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.credit.Adapters.MyGridAdapters;
import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Adapters.MyGridAdapter1;
import com.example.credit.Adapters.MyGridAdapter2;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.MyGridView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 公司信息界面
 */
public class CompanyDetailsActivity extends BaseActivity {
    WaitDialog waitDialog;
    @ViewInject(R.id.sc)
    ScrollView mScrollView;

    @ViewInject(R.id.d_return)
    ImageView d_return;

    @ViewInject(R.id.details_logo)
    ImageView details_logo;
    @ViewInject(R.id.details_tit)
    TextView details_tit;
    @ViewInject(R.id.cp_name)
    TextView cp_name;
    @ViewInject(R.id.details_tit1)//浏览量
            TextView details_tit1;

    @ViewInject(R.id.vg)
    RelativeLayout vg;
    @ViewInject(R.id.state_tv)
    TextView state_tv;
    @ViewInject(R.id.state_iv)
    ImageView state_iv;
    @ViewInject(R.id.myGridView1)
    MyGridView myGridView1;
    @ViewInject(R.id.myGridView2)
    MyGridView myGridView2;

    MyGridAdapter1 adapter1;
    MyGridAdapter2 adapter2;
    private final int MSG = 0x015;
    int a1=0,a2=0,a3=0,a4=0,a5=0,a6=0,a7=0,a8=0,a9=0,a10=0,a11=0,a12=0,a13=0,a14=0,a15=0,a16=0;

    List<DataManager.search> detailsList = new ArrayList<DataManager.search>();
    public String[] arrays3 = {"注册资本", "法定代表", "发照日期", "成立日期",
            "工商注册号", "组织机构代码"};

    public String[] arrays4;


    public String[] arrays1 = {"工商信息", "行政审批", "荣誉信息", "扶持信息",
            "抵押信息", "出质信息", "司法信息", "预警信息",
            "行政处罚", "经营异常", "专利信息", "商标信息",
            "著作权", "广告资质", "守合同重信用", "企业自主公示"};
    public String[] arrays2 = {"+1", "+1", "+1", "+1",
            "+1", "+1", "+1", "+1",
            "+1", "+1", "+1", "+1",
            "+1", "+1", "+1", "+1"};

    public int[] imgs = {R.mipmap.infodetial_fun1_abled, R.mipmap.infodetial_fun2_abled,
            R.mipmap.infodetial_fun3_abled, R.mipmap.infodetial_fun4_abled,
            R.mipmap.infodetial_fun5_abled, R.mipmap.infodetial_fun6_abled,
            R.mipmap.infodetial_fun7_abled, R.mipmap.infodetial_fun8_abled,
            R.mipmap.infodetial_fun9_abled, R.mipmap.infodetial_fun10_abled,
            R.mipmap.infodetial_fun11_abled, R.mipmap.infodetial_fun12_abled,
            R.mipmap.infodetial_fun13_abled, R.mipmap.infodetial_fun14_abled,
            R.mipmap.infodetial_fun15_abled, R.mipmap.infodetial_fun16_abled};

    public int[] imgs1 = {R.mipmap.icon1, R.mipmap.icon2,
            R.mipmap.icon3, R.mipmap.icon4,
            R.mipmap.icon5, R.mipmap.icon6,
            R.mipmap.icon7, R.mipmap.icon8,
            R.mipmap.icon9, R.mipmap.icon10,
            R.mipmap.icon11, R.mipmap.icon12,
            R.mipmap.icon13, R.mipmap.icon14,
            R.mipmap.icon15, R.mipmap.icon16};

    int position;
    public static Handler handler;
    String model;
    String KeyNo;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);
        ViewUtils.inject(this);
        waitDialog=new WaitDialog(this);
        mScrollView.smoothScrollTo(0, 20);
        Intent i = getIntent();
        int s = i.getIntExtra("s", 0);
        position = i.getIntExtra("position", 0);
        detailsList = DataManager.searchList;
        Build bd = new Build();
        model = bd.MODEL;//设备ID
        KeyNo = DataManager.searchList.get(position).PRIPID;//市场主体身份代码
        token = SearchFirmActivty.MD5s(KeyNo + model);//token 由 设备ID+市场主体身份代码 MD5生成
        init();
        adapter1 = new MyGridAdapter1(CompanyDetailsActivity.this, arrays1, arrays2, imgs);
        MyGridAdapters adapters = new MyGridAdapters(CompanyDetailsActivity.this, imgs1);
        myGridView1.setAdapter(adapters);
        myGridView1.setSelector(new ColorDrawable(Color.TRANSPARENT));
        myGridView1.setOnItemClickListener(listener);

        vg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myGridView2.getVisibility() == View.GONE) {
                    myGridView2.setVisibility(View.VISIBLE);
                    state_tv.setText("收起");
                    state_iv.setBackgroundResource(R.mipmap.details_up);
                } else {
                    myGridView2.setVisibility(View.GONE);
                    state_tv.setText("展开");
                    state_iv.setBackgroundResource(R.mipmap.details_down);
                }
            }
        });
        adapter2 = new MyGridAdapter2(CompanyDetailsActivity.this, arrays3, arrays4);
        myGridView2.setAdapter(adapter2);
        myGridView2.setSelector(new ColorDrawable(Color.TRANSPARENT));
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0://工商信息
                        waitDialog.dismiss();
                        Intent i0 = new Intent(CompanyDetailsActivity.this, DetailsContentActivity.class);
                        i0.putExtra("position", position);
                        i0.putExtra("Tname",arrays1[msg.what]);
                        startActivity(i0);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 1://行政审批
                        waitDialog.dismiss();
                        Intent i1 = new Intent(CompanyDetailsActivity.this, AdminActivity.class);
                        i1.putExtra("Tname",arrays1[msg.what]);
                        startActivity(i1);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 2://荣誉信息
                        waitDialog.dismiss();
                        Intent i2 = new Intent(CompanyDetailsActivity.this, Honor_Support_Activity.class);
                        i2.putExtra("Tname",arrays1[msg.what]);
                        i2.putExtra("st", 1);
                        startActivity(i2);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 3://扶持信息
                        waitDialog.dismiss();
                        Intent i3 = new Intent(CompanyDetailsActivity.this, Honor_Support_Activity.class);
                        i3.putExtra("st", 2);
                        i3.putExtra("Tname",arrays1[msg.what]);
                        startActivity(i3);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 4://抵押信息
                        waitDialog.dismiss();
                        Intent i4 =new Intent(CompanyDetailsActivity.this,Mortgage_detail_Activity.class);
                        i4.putExtra("Tname",arrays1[msg.what]);
                        startActivity(i4);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 5://出质信息
                        waitDialog.dismiss();
                        Intent i5 = new Intent(CompanyDetailsActivity.this, PledgeActivity.class);
                        i5.putExtra("Tname",arrays1[msg.what]);
                        startActivity(i5);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 6://司法信息
                        waitDialog.dismiss();
                        Intent i6 = new Intent(CompanyDetailsActivity.this, JudicialActivity.class);
                        i6.putExtra("Tname",arrays1[msg.what]);
                        startActivity(i6);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 7://预警信息
                        waitDialog.dismiss();
                        Intent i7 = new Intent(CompanyDetailsActivity.this, AlertActivity.class);
                        i7.putExtra("Tname",arrays1[msg.what]);
                        startActivity(i7);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 8:
                        waitDialog.dismiss();
                        Intent i8 = new Intent(CompanyDetailsActivity.this, PunishActivity.class);
                        i8.putExtra("Tname",arrays1[msg.what]);
                        startActivity(i8);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 9://经营异常
                        waitDialog.dismiss();
                        Intent i9 = new Intent(CompanyDetailsActivity.this, AbnormalActivity.class);
                        i9.putExtra("Tname",arrays1[msg.what]);
                        startActivity(i9);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 10:
                        waitDialog.dismiss();
                        Intent i10 = new Intent(CompanyDetailsActivity.this, PatentActivity.class);
                        i10.putExtra("Tname",arrays1[msg.what]);
                        startActivity(i10);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 11:
                        waitDialog.dismiss();
                        Intent i11 = new Intent(CompanyDetailsActivity.this, TrademarkActivity.class);
                        i11.putExtra("Tname",arrays1[msg.what]);
                        startActivity(i11);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 12:
                        waitDialog.dismiss();
                        Intent i12 = new Intent(CompanyDetailsActivity.this, CopyrightActivity.class);
                        i12.putExtra("Tname",arrays1[msg.what]);
                        startActivity(i12);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 13://广告资质
                        waitDialog.dismiss();
                        Intent i13 = new Intent(CompanyDetailsActivity.this, AdvertisementActivity.class);
                        i13.putExtra("Tname",arrays1[msg.what]);
                        startActivity(i13);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 14://守合同重信用
                        waitDialog.dismiss();
                        Intent i14 = new Intent(CompanyDetailsActivity.this, ObeyedActivity.class);
                        i14.putExtra("Tname",arrays1[msg.what]);
                        startActivity(i14);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 15://自主公示
                        waitDialog.dismiss();
                        startActivity(new Intent(CompanyDetailsActivity.this, AutonomyActivity.class));
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    case 500:
                        waitDialog.dismiss();
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
            switch (index) {
                case 0://工商信息
                    if(a1==0){
                        waitDialog.show();
                        GsonUtil request0 = new GsonUtil(URLconstant.URLINSER + URLconstant.DETAILSCINFOURL, RequestMethod.GET);
                        request0.add("token", token);
                        request0.add("deviceld", model);
                        request0.add("KeyNo", KeyNo);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request0, MyhttpCallBack.getInstance(), 0x000, true, false, true);
                    }
                    break;
                case 1://行政审批
                    if(a2==0) {
                        waitDialog.show();
                        GsonUtil request1 = new GsonUtil(URLconstant.URLINSER + URLconstant.ADMINURL, RequestMethod.GET);
                        request1.add("token", token);
                        request1.add("deviceld", model);
                        request1.add("KeyNo", KeyNo);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request1, MyhttpCallBack.getInstance(), 0x001, true, false, true);
                    }
                    break;
                case 2://荣誉信息
                    if(a3==0) {
                        waitDialog.show();
                        String KeyNoR = DataManager.searchList.get(position).REGNO;//注册号
                        String tokenr = SearchFirmActivty.MD5s(KeyNoR + model);//token 由 设备ID+/注册号 MD5生成
                        GsonUtil request2 = new GsonUtil(URLconstant.URLINSER + URLconstant.HONORURL, RequestMethod.GET);
                        request2.add("token", tokenr);
                        request2.add("deviceld", model);
                        request2.add("KeyNo", KeyNoR);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request2, MyhttpCallBack.getInstance(), 0x002, true, false, true);
                    }
                    break;
                case 3://扶持信息
                    if(a4==0) {
                        waitDialog.show();
                        GsonUtil request3 = new GsonUtil(URLconstant.URLINSER + URLconstant.SUPPORTURL, RequestMethod.GET);
                        request3.add("token", token);
                        request3.add("deviceld", model);
                        request3.add("KeyNo", KeyNo);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request3, MyhttpCallBack.getInstance(), 0x003, true, false, true);
                    }
                    break;
                case 4://抵押信息
                    if(a5==0) {
                        waitDialog.show();
                        GsonUtil mortinfoRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.MORTINFO, RequestMethod.GET);//动产request
                        GsonUtil mortinfoBdcRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.MORTINFOBDC, RequestMethod.GET);//不动产request
                        mortinfoRequest.add("token", token);
                        mortinfoRequest.add("deviceld", model);
                        mortinfoRequest.add("KeyNo", KeyNo);
                        mortinfoBdcRequest.add("token", token);
                        mortinfoBdcRequest.add("deviceld", model);
                        mortinfoBdcRequest.add("KeyNo", KeyNo);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, mortinfoRequest, MyhttpCallBack.getInstance(), 0x004, true, false, true);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, mortinfoBdcRequest, MyhttpCallBack.getInstance(), 0x0041, true, false, true);
                    }
                    break;
                case 5://出质信息
                    if(a6==0) {
                        waitDialog.show();
                        GsonUtil request5 = new GsonUtil(URLconstant.URLINSER + URLconstant.PLEDGEURL, RequestMethod.GET);
                        request5.add("token", token);
                        request5.add("deviceld", model);
                        request5.add("KeyNo", KeyNo);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request5, MyhttpCallBack.getInstance(), 0x005, true, false, true);
                    }
                    break;
                case 6://司法信息
                    if(a7==0) {
                        waitDialog.show();
                        GsonUtil request6 = new GsonUtil(URLconstant.URLINSER + URLconstant.JUDICIALURL, RequestMethod.GET);
                        request6.add("token", token);
                        request6.add("deviceld", model);
                        request6.add("KeyNo", KeyNo);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request6, MyhttpCallBack.getInstance(), 0X006, true, false, true);
                    }
                    break;
                case 7://预警信息
                    if(a8==0) {
                        waitDialog.show();
                        GsonUtil request7 = new GsonUtil(URLconstant.URLINSER + URLconstant.GETALERT, RequestMethod.GET);
                        request7.add("token", token);
                        request7.add("deviceld", model);
                        request7.add("KeyNo", KeyNo);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request7, MyhttpCallBack.getInstance(), 0x007, true, false, true);
                    }
                    break;
                case 8://行政处罚
                    if(a9==0) {
                        waitDialog.show();
                        GsonUtil request = new GsonUtil(URLconstant.URLINSER + URLconstant.PUNISHURL, RequestMethod.GET);
                        request.add("token", token);
                        request.add("deviceld", model);
                        request.add("KeyNo", KeyNo);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request, MyhttpCallBack.getInstance(), 0X008, true, false, true);
                    }
                    break;
                case 9://经营异常
                    if(a10==0) {
                        waitDialog.show();
                        GsonUtil request9 = new GsonUtil(URLconstant.URLINSER + URLconstant.ABNORMALURL, RequestMethod.GET);
                        request9.add("token", token);
                        request9.add("deviceld", model);
                        request9.add("KeyNo", KeyNo);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request9, MyhttpCallBack.getInstance(), 0X009, true, false, true);
                    }
                    break;
                case 10://专利信息
                    if(a11==0) {
                        waitDialog.show();
                        GsonUtil request10 = new GsonUtil(URLconstant.URLINSER + URLconstant.PATENTURL, RequestMethod.GET);
                        request10.add("token", token);
                        request10.add("deviceld", model);
                        request10.add("KeyNo", KeyNo);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request10, MyhttpCallBack.getInstance(), 0X010, true, false, true);
//                    Gson gson=new Gson();
//                    String jstring10 = getResources().getString(R.string.test1);
//                    Map<String, Object> map = gson.fromJson(jstring10, new TypeToken<Map<String, Object>>() {
//                    }.getType());
//                    List<DataManager.patentInfo> list10 = gson.fromJson(((Map<String, Object>) map.get("data")).get("patentInfo").toString(), new TypeToken<List<DataManager.patentInfo>>() {
//                    }.getType());
//                    DataManager.patentInfoList = list10;
//                    if(DataManager.patentInfoList.size()>0 && DataManager.patentInfoList!=null){
//                        CompanyDetailsActivity.handler.sendEmptyMessage(10);
//                    }else{
//                        CompanyDetailsActivity.handler.sendEmptyMessage(500);
//                    }
                    }
                    break;
                case 11://商标信息
                    if(a12==0) {
                        waitDialog.show();
                        GsonUtil request11 = new GsonUtil(URLconstant.URLINSER + URLconstant.TRADEMARKURL, RequestMethod.GET);
                        request11.add("token", token);
                        request11.add("deviceld", model);
                        request11.add("KeyNo", KeyNo);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request11, MyhttpCallBack.getInstance(), 0x011, true, false, true);
                    }
                    break;
                case 12://著作权
                    if(a13==0) {
                        waitDialog.show();
                        GsonUtil request12 = new GsonUtil(URLconstant.URLINSER + URLconstant.COPYRIGHTURL, RequestMethod.GET);
                        request12.add("token", token);
                        request12.add("deviceld", model);
                        request12.add("KeyNo", KeyNo);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request12, MyhttpCallBack.getInstance(), 0x012, true, false, true);
                    }
                    break;
                case 13://广告资质
                    if(a14==0) {
                        waitDialog.show();
                        GsonUtil request13 = new GsonUtil(URLconstant.URLINSER + URLconstant.ADVERTISEMENTURL, RequestMethod.GET);
                        request13.add("token", token);
                        request13.add("deviceld", model);
                        request13.add("KeyNo", KeyNo);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request13, MyhttpCallBack.getInstance(), 0x013, true, false, true);
                    }
                    break;
                case 14://守合同重信用
                    if(a15==0) {
                        waitDialog.show();
                        GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.OBEYEDURL, RequestMethod.GET);
                        request14.add("token", token);
                        request14.add("deviceld", model);
                        request14.add("KeyNo", KeyNo);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request14, MyhttpCallBack.getInstance(), 0x014, true, false, true);
                    }
                    break;
                case 15://自主公示
                    if(a16==0) {
                        waitDialog.show();
                        GsonUtil request15 = new GsonUtil(URLconstant.URLINSER + URLconstant.GETAUTO, RequestMethod.GET);
                        request15.add("token", token);
                        request15.add("deviceld", model);
                        request15.add("KeyNo", KeyNo);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request15, MyhttpCallBack.getInstance(), MSG, true, false, true);
                    }
                    break;
                case 500://没有数据时返回信息
                    android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public void init() {
        d_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);

            }
        });
        if (detailsList != null && detailsList.size() > 0) {
            String stra = (detailsList.get(position).REGSTATE_CN).substring(0, 2);
            details_tit.setText(stra);//状态
            details_tit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopupWindow(v);
                }
            });
            cp_name.setText(detailsList.get(position).entname);
            List<String> lt = new ArrayList<String>();
            lt.add(detailsList.get(position).REGCAP + "万元");
            lt.add(detailsList.get(position).NAME);
            lt.add(detailsList.get(position).OPFROM);
            lt.add(detailsList.get(position).OPFROM);
            lt.add(detailsList.get(position).REGNO);
//            lt.add(detailsList.get(position).INDUSTRYPHY);
            lt.add("暂无信息");
            int size = lt.size();
            arrays4 = (String[]) lt.toArray(new String[size]);
        }
        if(DataManager.allcounts.BaseInfoCount=="0"||DataManager.allcounts.BaseInfoCount.equals("0")){
            a1=1;
            imgs1[0]=R.mipmap.icon1_1;
        }
        if(DataManager.allcounts.ApprovalCount=="0"||DataManager.allcounts.ApprovalCount.equals("0")){
            a2=1;
            imgs1[1]=R.mipmap.icon2_1;
        }
        if(DataManager.allcounts.HonorCount=="0"||DataManager.allcounts.HonorCount.equals("0")){
            a3=1;
            imgs1[2]=R.mipmap.icon3_1;
        }
        if(DataManager.allcounts.SupportCount=="0"||DataManager.allcounts.SupportCount.equals("0")){
            a4=1;
            imgs1[3]=R.mipmap.icon4_1;
        }
        if(DataManager.allcounts.PledgeCount=="0"||DataManager.allcounts.PledgeCount.equals("0")){
            a5=1;
            imgs1[4]=R.mipmap.icon5_1;
        }
        if(DataManager.allcounts.MortgagorCount=="0"||DataManager.allcounts.MortgagorCount.equals("0")){
            a6=1;
            imgs1[5]=R.mipmap.icon6_1;
        }
        if(DataManager.allcounts.JudiciaryCount=="0"||DataManager.allcounts.JudiciaryCount.equals("0")){
            a7=1;
            imgs1[6]=R.mipmap.icon7_1;
        }
        if(DataManager.allcounts.WarningCount=="0"||DataManager.allcounts.WarningCount.equals("0")){
            a8=1;
            imgs1[7]=R.mipmap.icon8_1;
        }
        if(DataManager.allcounts.PunishCount=="0"||DataManager.allcounts.PunishCount.equals("0")){
            a9=1;
            imgs1[8]=R.mipmap.icon9_1;
        }
        if(DataManager.allcounts.AbnormityCount=="0"||DataManager.allcounts.AbnormityCount.equals("0")){
            a10=1;
            imgs1[9]=R.mipmap.icon10_1;
        }
        if(DataManager.allcounts.PatentCount=="0"||DataManager.allcounts.PatentCount.equals("0")){
            a11=1;
            imgs1[10]=R.mipmap.icon11_1;
        }
        if(DataManager.allcounts.TrademarkCount=="0"||DataManager.allcounts.TrademarkCount.equals("0")){
            a12=1;
            imgs1[11]=R.mipmap.icon12_1;
        }
        if(DataManager.allcounts.CopyrightCount=="0"||DataManager.allcounts.CopyrightCount.equals("0")){
            a13=1;
            imgs1[12]=R.mipmap.icon13_1;
        }
        if(DataManager.allcounts.AdvertisementCount=="0"||DataManager.allcounts.AdvertisementCount.equals("0")){
            a14=1;
            imgs1[13]=R.mipmap.icon14_1;
        }
        if(DataManager.allcounts.CreditCount=="0"||DataManager.allcounts.CreditCount.equals("0")){
            a15=1;
            imgs1[14]=R.mipmap.icon15_1;
        }
        if(DataManager.allcounts.AnnualCount=="0"||DataManager.allcounts.AnnualCount.equals("0")){
            a16=1;
            imgs1[15]=R.mipmap.icon16_1;
        }

    }

    public void showPopupWindow(View view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(CompanyDetailsActivity.this).inflate(
                R.layout.popupwindow_state, null);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        TextView tv = (TextView) contentView.findViewById(R.id.tv_state);
        tv.setText(detailsList.get(position).REGSTATE_CN);
        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.menu_bg));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

    }

}
