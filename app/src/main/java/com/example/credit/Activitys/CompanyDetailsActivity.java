package com.example.credit.Activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
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
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;
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

    @ViewInject(R.id.saveG)//关注layout
            LinearLayout saveG;
    @ViewInject(R.id.details_tit3)//关注text
            TextView details_tit3;

    PopupMenu popupMenu;
    Menu menu;

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

//    List<DataManager.search> detailsList = new ArrayList<DataManager.search>();
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

    public static Handler handler;
    String model,KeyNo,token,regnore;

    @ViewInject(R.id.pb_1)
    TextView pb_1;//首页
    @ViewInject(R.id.pb_2)
    TextView pb_2;//评论
    @ViewInject(R.id.pb_3)
    TextView pb_3;//投诉
    @ViewInject(R.id.pb_4)
    TextView pb_4;//我

    ProgressDialog pd;
    String KeyNos,tokens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);
        ViewUtils.inject(this);
        waitDialog=new WaitDialog(this);
        mScrollView.smoothScrollTo(0, 20);
        Intent i = getIntent();
        int s = i.getIntExtra("s", 0);
//        detailsList = DataManager.searchList;
        Build bd = new Build();
        model = bd.MODEL;//设备ID
        KeyNo = DataManager.BaseinfoList.get(0).PRIPID;//市场主体身份代码
        token = SearchFirmActivty.MD5s(KeyNo + model);//token 由 设备ID+市场主体身份代码 MD5生成
        regnore=DataManager.BaseinfoList.get(0).REGNO;//注册号

        KeyNos=DataManager.BaseinfoList.get(0).EnterAddtionID;//企业附加信息主键ID
        tokens= SearchFirmActivty.MD5s(KeyNos + model);
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
                    case 8://行政处罚
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
                    case 10://专利
                        waitDialog.dismiss();
                        Intent i10 = new Intent(CompanyDetailsActivity.this, PatentActivity.class);
                        i10.putExtra("Tname",arrays1[msg.what]);
                        startActivity(i10);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 11://商标
                        waitDialog.dismiss();
                        Intent i11 = new Intent(CompanyDetailsActivity.this, TrademarkActivity.class);
                        i11.putExtra("Tname",arrays1[msg.what]);
                        startActivity(i11);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 12://著作权
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
                        break;
                    case 21://评论
//                        waitDialog.dismiss();
                        pd.dismiss();
                        CommentListActivity.RUserreviewList= DataManager.UserreviewList;
                        Intent i21=new Intent(CompanyDetailsActivity.this,CommentListActivity.class);
                        startActivity(i21);
                        break;
                    case 22://关注
                        if(DataManager.FavotiteS.data.result.equals("success")){
                            saveG.setBackgroundResource(R.drawable.details_toplogo_s);
                            details_tit3.setText("已关注");
                            android.widget.Toast.makeText(CompanyDetailsActivity.this, "关注成功！", android.widget.Toast.LENGTH_SHORT).show();
                        }else{
                            android.widget.Toast.makeText(CompanyDetailsActivity.this, "关注失败！", android.widget.Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 23://取消关注
                        if(DataManager.FavotiteS.data.result.equals("success")){
                            saveG.setBackgroundResource(R.drawable.details_toplogo);
                            details_tit3.setText("关注");
                            android.widget.Toast.makeText(CompanyDetailsActivity.this, "取消关注成功！", android.widget.Toast.LENGTH_SHORT).show();
                        }else{
                            android.widget.Toast.makeText(CompanyDetailsActivity.this, "取消关注失败！", android.widget.Toast.LENGTH_SHORT).show();

                        }
                        break;
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
                        request0.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        request0.add("token", token);
                        request0.add("deviceId", model);
                        request0.add("KeyNo", KeyNo);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request0, MyhttpCallBack.getInstance(), 0x000, true, false, true);
                    }
                    break;
                case 1://行政审批
                    if(a2==0) {
                        waitDialog.show();
                        GsonUtil request1 = new GsonUtil(URLconstant.URLINSER + URLconstant.ADMINURL, RequestMethod.GET);
                        request1.add("token", token);
                        request1.add("deviceId", model);
                        request1.add("KeyNo", KeyNo);
                        request1.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request1, MyhttpCallBack.getInstance(), 0x001, true, false, true);
                    }
                    break;
                case 2://荣誉信息
                    if(a3==0) {
                        waitDialog.show();
                        String KeyNoR = DataManager.BaseinfoList.get(0).REGNO;//注册号
                        String tokenr = SearchFirmActivty.MD5s(KeyNoR + model);//token 由 设备ID+/注册号 MD5生成
                        GsonUtil request2 = new GsonUtil(URLconstant.URLINSER + URLconstant.HONORURL, RequestMethod.GET);
                        request2.add("token", tokenr);
                        request2.add("deviceId", model);
                        request2.add("KeyNo", KeyNoR);
                        request2.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request2, MyhttpCallBack.getInstance(), 0x002, true, false, true);
                    }
                    break;
                case 3://扶持信息
                    if(a4==0) {
                        waitDialog.show();
                        GsonUtil request3 = new GsonUtil(URLconstant.URLINSER + URLconstant.SUPPORTURL, RequestMethod.GET);
                        request3.add("token", token);
                        request3.add("deviceId", model);
                        request3.add("KeyNo", KeyNo);
                        request3.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request3, MyhttpCallBack.getInstance(), 0x003, true, false, true);
                    }
                    break;
                case 4://抵押信息
                    if(a5==0) {
                        waitDialog.show();
                        GsonUtil mortinfoRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.MORTINFO, RequestMethod.GET);//动产request
                        GsonUtil mortinfoBdcRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.MORTINFOBDC, RequestMethod.GET);//不动产request
                        mortinfoRequest.add("token", token);
                        mortinfoRequest.add("deviceId", model);
                        mortinfoRequest.add("KeyNo", KeyNo);
                        mortinfoRequest.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        mortinfoBdcRequest.add("token", token);
                        mortinfoBdcRequest.add("deviceId", model);
                        mortinfoBdcRequest.add("KeyNo", KeyNo);
                        mortinfoBdcRequest.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, mortinfoRequest, MyhttpCallBack.getInstance(), 0x004, true, false, true);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, mortinfoBdcRequest, MyhttpCallBack.getInstance(), 0x0041, true, false, true);
                    }
                    break;
                case 5://出质信息
                    if(a6==0) {
                        waitDialog.show();
                        GsonUtil request5 = new GsonUtil(URLconstant.URLINSER + URLconstant.PLEDGEURL, RequestMethod.GET);
                        request5.add("token", token);
                        request5.add("deviceId", model);
                        request5.add("KeyNo", KeyNo);
                        request5.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request5, MyhttpCallBack.getInstance(), 0x005, true, false, true);
                    }
                    break;
                case 6://司法信息
                    if(a7==0) {
                        waitDialog.show();
                        GsonUtil request6 = new GsonUtil(URLconstant.URLINSER + URLconstant.JUDICIALURL, RequestMethod.GET);
                        request6.add("token", token);
                        request6.add("deviceId", model);
                        request6.add("KeyNo", KeyNo);
                        request6.add("regnore", regnore);//注册号
                        request6.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request6, MyhttpCallBack.getInstance(), 0X006, true, false, true);
                    }
                    break;
                case 7://预警信息
                    if(a8==0) {
                        waitDialog.show();
                        GsonUtil request7 = new GsonUtil(URLconstant.URLINSER + URLconstant.GETALERT, RequestMethod.GET);
                        request7.add("token", token);
                        request7.add("deviceId", model);
                        request7.add("KeyNo", KeyNo);
                        request7.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request7, MyhttpCallBack.getInstance(), 0x007, true, false, true);
                    }
                    break;
                case 8://行政处罚
                    if(a9==0) {
                        waitDialog.show();
                        GsonUtil request = new GsonUtil(URLconstant.URLINSER + URLconstant.PUNISHURL, RequestMethod.GET);
                        request.add("token", token);
                        request.add("deviceId", model);
                        request.add("KeyNo", KeyNo);
                        request.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request, MyhttpCallBack.getInstance(), 0X008, true, false, true);
                    }
                    break;
                case 9://经营异常
                    if(a10==0) {
                        waitDialog.show();
                        GsonUtil request9 = new GsonUtil(URLconstant.URLINSER + URLconstant.ABNORMALURL, RequestMethod.GET);
                        request9.add("token", token);
                        request9.add("deviceId", model);
                        request9.add("KeyNo", KeyNo);
                        request9.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request9, MyhttpCallBack.getInstance(), 0X009, true, false, true);
                    }
                    break;
                case 10://专利信息
                    if(a11==0) {
                        waitDialog.show();
                        GsonUtil request10 = new GsonUtil(URLconstant.URLINSER + URLconstant.PATENTURL, RequestMethod.GET);
                        request10.add("token", token);
                        request10.add("deviceId", model);
                        request10.add("KeyNo", KeyNo);
                        request10.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
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
                        request11.add("deviceId", model);
                        request11.add("KeyNo", KeyNo);
                        request11.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request11, MyhttpCallBack.getInstance(), 0x011, true, false, true);
                    }
                    break;
                case 12://著作权
                    if(a13==0) {
                        waitDialog.show();
                        GsonUtil request12 = new GsonUtil(URLconstant.URLINSER + URLconstant.COPYRIGHTURL, RequestMethod.GET);
                        request12.add("token", token);
                        request12.add("deviceId", model);
                        request12.add("KeyNo", KeyNo);
                        request12.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request12, MyhttpCallBack.getInstance(), 0x012, true, false, true);
                    }
                    break;
                case 13://广告资质
                    if(a14==0) {
                        waitDialog.show();
                        GsonUtil request13 = new GsonUtil(URLconstant.URLINSER + URLconstant.ADVERTISEMENTURL, RequestMethod.GET);
                        request13.add("token", token);
                        request13.add("deviceId", model);
                        request13.add("KeyNo", KeyNo);
                        request13.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request13, MyhttpCallBack.getInstance(), 0x013, true, false, true);
                    }
                    break;
                case 14://守合同重信用
                    if(a15==0) {
                        waitDialog.show();
                        GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.OBEYEDURL, RequestMethod.GET);
                        request14.add("token", token);
                        request14.add("deviceId", model);
                        request14.add("KeyNo", KeyNo);
                        request14.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request14, MyhttpCallBack.getInstance(), 0x014, true, false, true);
                    }
                    break;
                case 15://自主公示
                    if(a16==0) {
                        waitDialog.show();
                        GsonUtil request15 = new GsonUtil(URLconstant.URLINSER + URLconstant.GETAUTO, RequestMethod.GET);
                        request15.add("token", token);
                        request15.add("deviceId", model);
                        request15.add("KeyNo", KeyNo);
                        request15.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
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
        if (DataManager.BaseinfoList != null && DataManager.BaseinfoList.size() > 0) {
            String stra = (DataManager.BaseinfoList.get(0).REGSTATE_CN).substring(0, 2);
            details_tit.setText(stra);//状态
            details_tit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopupWindow(v);
                }
            });
            cp_name.setText(DataManager.BaseinfoList.get(0).ENTNAME);
            List<String> lt = new ArrayList<String>();
            lt.add(DataManager.BaseinfoList.get(0).REGCAP + "万元");
            lt.add(DataManager.BaseinfoList.get(0).NAME);
            lt.add(DataManager.BaseinfoList.get(0).ESTDATE);
            lt.add(DataManager.BaseinfoList.get(0).ESTDATE);
            lt.add(DataManager.BaseinfoList.get(0).REGNO);
//            lt.add(detailsList.get(position).INDUSTRYPHY);
            lt.add("暂无信息");
            int size = lt.size();
            arrays4 = (String[]) lt.toArray(new String[size]);
        }
        if(DataManager.allcountsList.get(0).BaseInfoCount=="0"||DataManager.allcountsList.get(0).BaseInfoCount.equals("0")){
            a1=1;
            imgs1[0]=R.mipmap.icon1_1;
        }
        if(DataManager.allcountsList.get(0).ApprovalCount=="0"||DataManager.allcountsList.get(0).ApprovalCount.equals("0")){
            a2=1;
            imgs1[1]=R.mipmap.icon2_1;
        }
        if(DataManager.allcountsList.get(0).HonorCount=="0"||DataManager.allcountsList.get(0).HonorCount.equals("0")){
            a3=1;
            imgs1[2]=R.mipmap.icon3_1;
        }
        if(DataManager.allcountsList.get(0).SupportCount=="0"||DataManager.allcountsList.get(0).SupportCount.equals("0")){
            a4=1;
            imgs1[3]=R.mipmap.icon4_1;
        }
        if(DataManager.allcountsList.get(0).PledgeCount=="0"||DataManager.allcountsList.get(0).PledgeCount.equals("0")){
            a5=1;
            imgs1[4]=R.mipmap.icon5_1;
        }
        if(DataManager.allcountsList.get(0).MortgagorCount=="0"||DataManager.allcountsList.get(0).MortgagorCount.equals("0")){
            a6=1;
            imgs1[5]=R.mipmap.icon6_1;
        }
        if(DataManager.allcountsList.get(0).JudiciaryCount=="0"||DataManager.allcountsList.get(0).JudiciaryCount.equals("0")){
            a7=1;
            imgs1[6]=R.mipmap.icon7_1;
        }
        if(DataManager.allcountsList.get(0).WarningCount=="0"||DataManager.allcountsList.get(0).WarningCount.equals("0")){
            a8=1;
            imgs1[7]=R.mipmap.icon8_1;
        }
        if(DataManager.allcountsList.get(0).PunishCount=="0"||DataManager.allcountsList.get(0).PunishCount.equals("0")){
            a9=1;
            imgs1[8]=R.mipmap.icon9_1;
        }
        if(DataManager.allcountsList.get(0).AbnormityCount=="0"||DataManager.allcountsList.get(0).AbnormityCount.equals("0")){
            a10=1;
            imgs1[9]=R.mipmap.icon10_1;
        }
        if(DataManager.allcountsList.get(0).PatentCount=="0"||DataManager.allcountsList.get(0).PatentCount.equals("0")){
            a11=1;
            imgs1[10]=R.mipmap.icon11_1;
        }
        if(DataManager.allcountsList.get(0).TrademarkCount=="0"||DataManager.allcountsList.get(0).TrademarkCount.equals("0")){
            a12=1;
            imgs1[11]=R.mipmap.icon12_1;
        }
        if(DataManager.allcountsList.get(0).CopyrightCount=="0"||DataManager.allcountsList.get(0).CopyrightCount.equals("0")){
            a13=1;
            imgs1[12]=R.mipmap.icon13_1;
        }
        if(DataManager.allcountsList.get(0).AdvertisementCount=="0"||DataManager.allcountsList.get(0).AdvertisementCount.equals("0")){
            a14=1;
            imgs1[13]=R.mipmap.icon14_1;
        }
        if(DataManager.allcountsList.get(0).CreditCount=="0"||DataManager.allcountsList.get(0).CreditCount.equals("0")){
            a15=1;
            imgs1[14]=R.mipmap.icon15_1;
        }
        if(DataManager.allcountsList.get(0).AnnualCount=="0"||DataManager.allcountsList.get(0).AnnualCount.equals("0")){
            a16=1;
            imgs1[15]=R.mipmap.icon16_1;
        }

        if(DataManager.allcountsList.get(0).IsFavorite.equals("false")) {//当前状态为未关注，所以点击是关注
            saveG.setBackgroundResource(R.drawable.details_toplogo);
            details_tit3.setText("关注");
        }else{
            saveG.setBackgroundResource(R.drawable.details_toplogo_s);
            details_tit3.setText("已关注");
        }

        pb_1.setOnClickListener(onClickListener);
        pb_2.setOnClickListener(onClickListener);
        pb_3.setOnClickListener(onClickListener);
        pb_4.setOnClickListener(onClickListener);
        saveG.setOnClickListener(onClickListener);

        popupMenu = new PopupMenu(this, findViewById(R.id.d_more));
        menu = popupMenu.getMenu();
        // 通过代码添加菜单项
//        menu.add(Menu.NONE, Menu.FIRST + 0, 0, "复制");
//        menu.add(Menu.NONE, Menu.FIRST + 1, 1, "粘贴");

        // 通过XML文件添加菜单项
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_list, menu);

        // 监听事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.claim:
                        Toast.show("我的认领");
                        Intent i=new Intent(CompanyDetailsActivity.this,ToClaimActivity.class);
                        startActivity(i);
                        break;
                }
                return false;
            }
        });
    }
    public void popupmenu(View v) {
        popupMenu.show();
    }
    public void showPopupWindow(View view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(CompanyDetailsActivity.this).inflate(
                R.layout.popupwindow_state, null);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        TextView tv = (TextView) contentView.findViewById(R.id.tv_state);
        tv.setText(DataManager.BaseinfoList.get(0).REGSTATE_CN);
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
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.pb_1://首页
                    Intent i1=new Intent(CompanyDetailsActivity.this,MainActivity.class);
                    startActivity(i1);
                    finish();
                    break;
                case R.id.pb_2://评论
//                    android.widget.Toast.makeText(CompanyDetailsActivity.this, "此模块，正在抢修中。。。！", android.widget.Toast.LENGTH_SHORT).show();
//                    waitDialog.show();
                    pd=new ProgressDialog(CompanyDetailsActivity.this);
                    pd.setMessage("正在加载中...");
                    pd.setCancelable(false);
                    pd.show();
                    GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.COMM, RequestMethod.GET);
                    request14.add("deviceId",model);
                    request14.add("token",tokens);
                    request14.add("KeyNo",KeyNos);
                    request14.add("memberId","86D9D7F53FCA45DD93E2D83DFCA0CB42");
                    CallServer.getInstance().add(CompanyDetailsActivity.this, request14, MyhttpCallBack.getInstance(), 0x201, true, false, true);
                    break;
                case R.id.pb_3://投诉
                    Intent i3=new Intent(CompanyDetailsActivity.this,ToComplaintActivity.class);
                    startActivity(i3);
                    break;
                case R.id.pb_4://我
//                    Intent i4=new Intent(CompanyDetailsActivity.this,MainActivity.class);
//                    startActivity(i4);
                    android.widget.Toast.makeText(CompanyDetailsActivity.this, "此模块，正在抢修中。。。！", android.widget.Toast.LENGTH_SHORT).show();

                    break;

                case R.id.saveG://关注
                    if(details_tit3.getText().toString().equals("关注")){//当前状态为未关注，所以点击是关注
                        GsonUtil requestG = new GsonUtil(URLconstant.URLINSER + URLconstant.YESFAVORITE, RequestMethod.GET);
                        requestG.add("deviceId",model);
                        requestG.add("token",tokens);
                        requestG.add("KeyNo",KeyNos);
                        requestG.add("memberId","86D9D7F53FCA45DD93E2D83DFCA0CB42");
                        requestG.add("attentionTypeId","11");
                        CallServer.getInstance().add(CompanyDetailsActivity.this, requestG, MyhttpCallBack.getInstance(), 0x101, true, false, true);
                    }else{//当前状态为已关注，所以点击是取消关注
                        GsonUtil requestN = new GsonUtil(URLconstant.URLINSER + URLconstant.NOFAVORITE, RequestMethod.GET);
                        requestN.add("deviceId",model);
                        requestN.add("token",tokens);
                        requestN.add("KeyNo",KeyNos);
                        requestN.add("memberId","86D9D7F53FCA45DD93E2D83DFCA0CB42");
                        CallServer.getInstance().add(CompanyDetailsActivity.this, requestN, MyhttpCallBack.getInstance(), 0x102, true, false, true);
                    }
//                    android.widget.Toast.makeText(CompanyDetailsActivity.this, "此模块，正在抢修中。。。！", android.widget.Toast.LENGTH_SHORT).show();

                    break;
            }
        }
    };

}
