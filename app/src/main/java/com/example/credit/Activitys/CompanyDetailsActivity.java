package com.example.credit.Activitys;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.credit.Adapters.MyGridAdapter2;
import com.example.credit.Adapters.MyGridAdapters;
import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.ActionItem;
import com.example.credit.Views.MyGridView;
import com.example.credit.Views.TitlePopup;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司信息界面
 */
public class CompanyDetailsActivity extends BaseActivity {
    public static WaitDialog waitDialog;
//    @ViewInject(R.id.sc)
//    ScrollView mScrollView;

    @ViewInject(R.id.d_return)
    LinearLayout d_return;

    @ViewInject(R.id.details_logo)
    ImageView details_logo;
    @ViewInject(R.id.cp_name)
    TextView cp_name;
    @ViewInject(R.id.details_tit1)//浏览量
            TextView details_tit1;

    @ViewInject(R.id.saveG)
    LinearLayout saveG;
    @ViewInject(R.id.details_tit3)
    TextView details_tit3;

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
    @ViewInject(R.id.gai1)
    TextView gai1;//名称
    @ViewInject(R.id.name)
    TextView name;//法人
    @ViewInject(R.id.regcap)
    TextView regcap;//注册资金
    @ViewInject(R.id.enttype)
    TextView enttype;//市场主体类型
    @ViewInject(R.id.estdate)
    TextView estdate;//成立日期

    int a1 = 0, a2 = 0, a3 = 0, a4 = 0, a5 = 0, a6 = 0, a7 = 0, a8 = 0, a9 = 0, a10 = 0, a11 = 0, a12 = 0, a13 = 0, a14 = 0, a15 = 0, a16 = 0;
    MyGridAdapter2 adapter2;
    private final int MSG = 0x015;

    //    List<DataManager.search> detailsList = new ArrayList<DataManager.search>();
    public String[] arrays3 = {"注册资本", "法定代表人", "发照日期", "成立日期",
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


    public int[] imgs1 = {R.mipmap.icon1, R.mipmap.icon2,
            R.mipmap.icon3, R.mipmap.icon4,
            R.mipmap.icon5, R.mipmap.icon6,
            R.mipmap.icon7, R.mipmap.icon8,
            R.mipmap.icon9, R.mipmap.icon10,
            R.mipmap.icon11, R.mipmap.icon12,
            R.mipmap.icon13, R.mipmap.icon14,
            R.mipmap.icon15, R.mipmap.icon16,
            R.mipmap.icon17, R.mipmap.icon18,
            R.mipmap.icon19, R.mipmap.icon20,
            R.mipmap.icon21, R.mipmap.icon22,
            R.mipmap.icon23, R.mipmap.icon24,};

    public static Handler handler;
    String model, KeyNo, token, regnore, enterId;

    @ViewInject(R.id.pb_0)
    LinearLayout pb_0;//二维码
    @ViewInject(R.id.pb_1)
    LinearLayout pb_1;//首页
    @ViewInject(R.id.pb_2)
    LinearLayout pb_2;//评论
    @ViewInject(R.id.pb_3)
    LinearLayout pb_3;//投诉
    @ViewInject(R.id.pb_4)
    LinearLayout pb_4;//关注
    @ViewInject(R.id.pb_4_img)
    ImageView pb_4_img;//关注图标
    @ViewInject(R.id.pb_4_txt)
    TextView pb_4_txt;//关注文字
    @ViewInject(R.id.panoramic)
    ImageView panoramic;//全景视图

    TitlePopup titlePopup;
    public static ProgressDialog pd;
    String KeyNos, tokens;
    CreditSharePreferences csp;
    int type, posit;

    AlertDialog.Builder builder;
    public static AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);
        ViewUtils.inject(this);
        csp = CreditSharePreferences.getLifeSharedPreferences();
        waitDialog = new WaitDialog(this);
//        mScrollView.smoothScrollTo(0, 20);
        Intent i = getIntent();
        posit = i.getIntExtra("posit", 0);
        type = i.getIntExtra("type", 0);
//        detailsList = DataManager.searchList;
        Build bd = new Build();
        model = bd.MODEL;//设备ID

        if (DataManager.BaseinfoList.size() > 0) {
            KeyNo = DataManager.BaseinfoList.get(0).PRIPID;//市场主体身份代码
            enterId = DataManager.allcountsList.get(0).EnterAddtionID;//企业附加表ID
            token = SearchFirmActivty.MD5s(KeyNo + model);//token 由 设备ID+市场主体身份代码 MD5生成
            regnore = DataManager.BaseinfoList.get(0).REGNO;//注册号
            KeyNos = DataManager.allcountsList.get(0).EnterAddtionID;//企业附加信息主键ID
        } else {
            Toast.show("暂无数据...");
        }

        tokens = SearchFirmActivty.MD5s(KeyNos + model);
        init();//初始化16宫格等

        MyGridAdapters adapters = new MyGridAdapters(CompanyDetailsActivity.this, imgs1);
        myGridView1.setAdapter(adapters);
        myGridView1.setSelector(new ColorDrawable(Color.TRANSPARENT));
        myGridView1.setOnItemClickListener(listener);
        panoramic.setOnClickListener(onClickListener);//全景视图

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
                        DataManager.String0 = DataManager.BaseinfoList.get(0).ENTNAME;
                        Intent i0 = new Intent(CompanyDetailsActivity.this, DetailsContentActivity.class);
                        i0.putExtra("Tname", arrays1[msg.what]);
                        startActivity(i0);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 1://行政审批
                        waitDialog.dismiss();
                        DataManager.String1 = DataManager.BaseinfoList.get(0).ENTNAME;
                        Intent i1 = new Intent(CompanyDetailsActivity.this, AdminActivity.class);
                        i1.putExtra("Tname", arrays1[msg.what]);
                        startActivity(i1);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 2://荣誉信息
                        waitDialog.dismiss();
                        DataManager.String2 = DataManager.BaseinfoList.get(0).ENTNAME;
                        Intent i2 = new Intent(CompanyDetailsActivity.this, Honor_Support_Activity.class);
                        i2.putExtra("Tname", arrays1[msg.what]);
                        i2.putExtra("st", 1);
                        startActivity(i2);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 3://扶持信息
                        waitDialog.dismiss();
                        DataManager.String3 = DataManager.BaseinfoList.get(0).ENTNAME;
                        Intent i3 = new Intent(CompanyDetailsActivity.this, Honor_Support_Activity.class);
                        i3.putExtra("st", 2);
                        i3.putExtra("Tname", arrays1[msg.what]);
                        startActivity(i3);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 4://抵押信息
                        waitDialog.dismiss();
                        if (DataManager.MyrealEstateS.data.realEstate != null || DataManager.MychattelS.data.chattel != null) {
                            DataManager.String4 = DataManager.BaseinfoList.get(0).ENTNAME;
                            Intent i4 = new Intent(CompanyDetailsActivity.this, Mortgage_detail_Activity.class);
                            i4.putExtra("Tname", arrays1[msg.what]);
                            startActivity(i4);
                            overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        } else {
                            CompanyDetailsActivity.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 5://出质信息
                        waitDialog.dismiss();
                        DataManager.String5 = DataManager.BaseinfoList.get(0).ENTNAME;
                        Intent i5 = new Intent(CompanyDetailsActivity.this, PledgeActivity.class);
                        i5.putExtra("Tname", arrays1[msg.what]);
                        startActivity(i5);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 6://司法信息
                        waitDialog.dismiss();
                        DataManager.String6 = DataManager.BaseinfoList.get(0).ENTNAME;
                        Intent i6 = new Intent(CompanyDetailsActivity.this, JudicialActivity.class);
                        i6.putExtra("Tname", arrays1[msg.what]);
                        startActivity(i6);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 7://预警信息
                        waitDialog.dismiss();
                        DataManager.String7 = DataManager.BaseinfoList.get(0).ENTNAME;
                        Intent i7 = new Intent(CompanyDetailsActivity.this, AlertActivity.class);
                        i7.putExtra("Tname", arrays1[msg.what]);
                        startActivity(i7);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 8://行政处罚
                        waitDialog.dismiss();
                        DataManager.String8 = DataManager.BaseinfoList.get(0).ENTNAME;
                        Intent i8 = new Intent(CompanyDetailsActivity.this, PunishActivity.class);
                        i8.putExtra("Tname", arrays1[msg.what]);
                        startActivity(i8);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 9://经营异常
                        waitDialog.dismiss();
                        DataManager.String9 = DataManager.BaseinfoList.get(0).ENTNAME;
                        Intent i9 = new Intent(CompanyDetailsActivity.this, AbnormalActivity.class);
                        i9.putExtra("Tname", arrays1[msg.what]);
                        startActivity(i9);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 10://专利
                        waitDialog.dismiss();
                        DataManager.String10 = DataManager.BaseinfoList.get(0).ENTNAME;
                        Intent i10 = new Intent(CompanyDetailsActivity.this, PatentActivity.class);
                        i10.putExtra("Tname", arrays1[msg.what]);
                        startActivity(i10);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 11://商标
                        waitDialog.dismiss();
                        DataManager.String11 = DataManager.BaseinfoList.get(0).ENTNAME;
                        Intent i11 = new Intent(CompanyDetailsActivity.this, TrademarkActivity.class);
                        i11.putExtra("Tname", arrays1[msg.what]);
                        startActivity(i11);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 12://著作权
                        waitDialog.dismiss();
                        DataManager.String12 = DataManager.BaseinfoList.get(0).ENTNAME;
                        Intent i12 = new Intent(CompanyDetailsActivity.this, CopyrightActivity.class);
                        i12.putExtra("Tname", arrays1[msg.what]);
                        startActivity(i12);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 13://广告资质
                        waitDialog.dismiss();
                        DataManager.String13 = DataManager.BaseinfoList.get(0).ENTNAME;
                        Intent i13 = new Intent(CompanyDetailsActivity.this, AdvertisementActivity.class);
                        i13.putExtra("Tname", arrays1[msg.what]);
                        startActivity(i13);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 14://守合同重信用
                        waitDialog.dismiss();
                        DataManager.String14 = DataManager.BaseinfoList.get(0).ENTNAME;
                        Intent i14 = new Intent(CompanyDetailsActivity.this, ObeyedActivity.class);
                        i14.putExtra("Tname", arrays1[msg.what]);
                        startActivity(i14);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 15://自主公示
                        waitDialog.dismiss();
                        DataManager.String15 = DataManager.BaseinfoList.get(0).ENTNAME;
                        startActivity(new Intent(CompanyDetailsActivity.this, AutonomyActivity.class));
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 21://评论
//                      waitDialog.dismiss();
                        Intent i21 = new Intent(CompanyDetailsActivity.this, CommentListActivity.class);
                        i21.putExtra("type", 0);
                        startActivity(i21);
                        break;
                    case 22://关注
                        waitDialog.dismiss();
                        if (DataManager.FavotiteS.data.result.equals("success")) {
                            pb_4_img.setBackgroundResource(R.mipmap.btm_4_s);
                            pb_4_txt.setText("已关注");
                            android.widget.Toast.makeText(CompanyDetailsActivity.this, "关注成功！", android.widget.Toast.LENGTH_SHORT).show();
                        } else {
                            android.widget.Toast.makeText(CompanyDetailsActivity.this, "关注失败！", android.widget.Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 23://取消关注
                        waitDialog.dismiss();
                        if (DataManager.FavotiteS.data.result.equals("success")) {
                            pb_4_img.setBackgroundResource(R.mipmap.btm_4_n);
                            pb_4_txt.setText("关注");
                            android.widget.Toast.makeText(CompanyDetailsActivity.this, "取消关注成功！", android.widget.Toast.LENGTH_SHORT).show();
                        } else {
                            android.widget.Toast.makeText(CompanyDetailsActivity.this, "取消关注失败！", android.widget.Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 24://投诉跳转列表
                        if (waitDialog != null) {
                           waitDialog.dismiss();
                        }
                        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
                        if (!cn.getClassName().equals(MycomplaintsListActivity.class.getName())) {

                            startActivity(new Intent(CompanyDetailsActivity.this, MycomplaintsListActivity.class).putExtra("key", 1));
                        } else {//用于判定是否更新数据，是否跳转
                            MycomplaintsListActivity.handler.sendEmptyMessage(5);//通知企业投诉ListView更新数据源刷新UI
                        }
                        break;

                    case 25://我的二维码名片
                        startActivity(new Intent(CompanyDetailsActivity.this, TwoDimActivity.class));
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
                    if (!DataManager.allcountsList.get(0).BaseInfoCount.equals("0")) {
                        waitDialog.show();
                        GsonUtil request0 = new GsonUtil(URLconstant.URLINSER + URLconstant.DETAILSCINFOURL, RequestMethod.GET);
                        request0.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        request0.add("token", token);
                        request0.add("deviceId", model);
                        request0.add("KeyNo", KeyNo);
                        if (!DataManager.String0.equals(DataManager.BaseinfoList.get(0).ENTNAME)) {
                            GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                            request121.add("token", token);
                            request121.add("deviceId", model);
                            request121.add("KeyNo", KeyNo);
                            request121.add("regnore", DataManager.BaseinfoList.get(0).REGNO);
                            request121.add("entname", DataManager.BaseinfoList.get(0).ENTNAME);
                            request121.add("memberId", csp.getID());
                            request121.add("enttype", DataManager.BaseinfoList.get(0).ENTTYPE);
                            request121.add("modulename", "工商信息");
                            CallServer.getInstance().add(CompanyDetailsActivity.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                        }
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request0, MyhttpCallBack.getInstance(), 0x000, true, false, true);
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 1://行政审批
                    if (!DataManager.allcountsList.get(0).ApprovalCount.equals("0")) {
                        waitDialog.show();
                        GsonUtil request1 = new GsonUtil(URLconstant.URLINSER + URLconstant.ADMINURL, RequestMethod.GET);
                        request1.add("token", token);
                        request1.add("deviceId", model);
                        request1.add("KeyNo", KeyNo);
                        request1.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        if (!DataManager.String1.equals(DataManager.BaseinfoList.get(0).ENTNAME)) {
                            GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                            request121.add("token", token);
                            request121.add("deviceId", model);
                            request121.add("KeyNo", KeyNo);
                            request121.add("regnore", DataManager.BaseinfoList.get(0).REGNO);
                            request121.add("entname", DataManager.BaseinfoList.get(0).ENTNAME);
                            request121.add("memberId", csp.getID());
                            request121.add("enttype", DataManager.BaseinfoList.get(0).ENTTYPE);
                            request121.add("modulename", "行政审批");
                            CallServer.getInstance().add(CompanyDetailsActivity.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                        }
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request1, MyhttpCallBack.getInstance(), 0x001, true, false, true);
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2://荣誉信息
                    if (!DataManager.allcountsList.get(0).HonorCount.equals("0")) {
                        waitDialog.show();
                        String KeyNoR = DataManager.BaseinfoList.get(0).REGNO;//注册号
                        String tokenr = SearchFirmActivty.MD5s(KeyNoR + model);//token 由 设备ID+/注册号 MD5生成
                        GsonUtil request2 = new GsonUtil(URLconstant.URLINSER + URLconstant.HONORURL, RequestMethod.GET);
                        request2.add("token", tokenr);
                        request2.add("deviceId", model);
                        request2.add("KeyNo", KeyNoR);
                        request2.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        if (!DataManager.String2.equals(DataManager.BaseinfoList.get(0).ENTNAME)) {
                            GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                            request121.add("token", token);
                            request121.add("deviceId", model);
                            request121.add("KeyNo", KeyNo);
                            request121.add("regnore", DataManager.BaseinfoList.get(0).REGNO);
                            request121.add("entname", DataManager.BaseinfoList.get(0).ENTNAME);
                            request121.add("memberId", csp.getID());
                            request121.add("enttype", DataManager.BaseinfoList.get(0).ENTTYPE);
                            request121.add("modulename", "荣誉信息");
                            CallServer.getInstance().add(CompanyDetailsActivity.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                        }
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request2, MyhttpCallBack.getInstance(), 0x002, true, false, true);
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 3://扶持信息
                    if (!DataManager.allcountsList.get(0).SupportCount.equals("0")) {
                        waitDialog.show();
                        GsonUtil request3 = new GsonUtil(URLconstant.URLINSER + URLconstant.SUPPORTURL, RequestMethod.GET);
                        request3.add("token", token);
                        request3.add("deviceId", model);
                        request3.add("KeyNo", KeyNo);
                        request3.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        if (!DataManager.String3.equals(DataManager.BaseinfoList.get(0).ENTNAME)) {
                            GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                            request121.add("token", token);
                            request121.add("deviceId", model);
                            request121.add("KeyNo", KeyNo);
                            request121.add("regnore", DataManager.BaseinfoList.get(0).REGNO);
                            request121.add("entname", DataManager.BaseinfoList.get(0).ENTNAME);
                            request121.add("memberId", csp.getID());
                            request121.add("enttype", DataManager.BaseinfoList.get(0).ENTTYPE);
                            request121.add("modulename", "扶持信息");
                            CallServer.getInstance().add(CompanyDetailsActivity.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                        }
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request3, MyhttpCallBack.getInstance(), 0x003, true, false, true);
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 4://抵押信息
                    if (!DataManager.allcountsList.get(0).MortgagorCount.equals("0")) {
                        waitDialog.show();
                        GsonUtil mortinfoRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.MORTINFO, RequestMethod.GET);//动产request
                        mortinfoRequest.add("token", SearchFirmActivty.MD5s(DataManager.BaseinfoList.get(0).PRIPID + model));
                        mortinfoRequest.add("deviceId", model);
                        mortinfoRequest.add("KeyNo", DataManager.BaseinfoList.get(0).PRIPID);
                        mortinfoRequest.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        if (!DataManager.String4.equals(DataManager.BaseinfoList.get(0).ENTNAME)) {
                            GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                            request121.add("token", token);
                            request121.add("deviceId", model);
                            request121.add("KeyNo", KeyNo);
                            request121.add("regnore", DataManager.BaseinfoList.get(0).REGNO);
                            request121.add("entname", DataManager.BaseinfoList.get(0).ENTNAME);
                            request121.add("memberId", csp.getID());
                            request121.add("enttype", DataManager.BaseinfoList.get(0).ENTTYPE);
                            request121.add("modulename", "抵押信息");
                            CallServer.getInstance().add(CompanyDetailsActivity.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                        }
                        GsonUtil mortinfoBdcRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.MORTINFOBDC, RequestMethod.GET);//不动产request
                        mortinfoBdcRequest.add("token", SearchFirmActivty.MD5s(DataManager.BaseinfoList.get(0).REGNO + model));
                        mortinfoBdcRequest.add("deviceId", model);
                        mortinfoBdcRequest.add("KeyNo", DataManager.BaseinfoList.get(0).REGNO);
                        mortinfoBdcRequest.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, mortinfoRequest, MyhttpCallBack.getInstance(), 0x004, true, false, true);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, mortinfoBdcRequest, MyhttpCallBack.getInstance(), 0x0041, true, false, true);
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 5://出质信息
                    if (!DataManager.allcountsList.get(0).PledgeCount.equals("0")) {
                        waitDialog.show();
                        GsonUtil request5 = new GsonUtil(URLconstant.URLINSER + URLconstant.PLEDGEURL, RequestMethod.GET);
                        request5.add("token", token);
                        request5.add("deviceId", model);
                        request5.add("KeyNo", KeyNo);
                        request5.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        if (!DataManager.String5.equals(DataManager.BaseinfoList.get(0).ENTNAME)) {
                            GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                            request121.add("token", token);
                            request121.add("deviceId", model);
                            request121.add("KeyNo", KeyNo);
                            request121.add("regnore", DataManager.BaseinfoList.get(0).REGNO);
                            request121.add("entname", DataManager.BaseinfoList.get(0).ENTNAME);
                            request121.add("memberId", csp.getID());
                            request121.add("enttype", DataManager.BaseinfoList.get(0).ENTTYPE);
                            request121.add("modulename", "出质信息");
                            CallServer.getInstance().add(CompanyDetailsActivity.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                        }
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request5, MyhttpCallBack.getInstance(), 0x005, true, false, true);
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 6://司法信息
                    if (!DataManager.allcountsList.get(0).JudiciaryCount.equals("0")) {
                        waitDialog.show();
                        GsonUtil request6 = new GsonUtil(URLconstant.URLINSER + URLconstant.JUDICIALURL, RequestMethod.GET);
                        request6.add("token", token);
                        request6.add("deviceId", model);
                        request6.add("KeyNo", KeyNo);
                        request6.add("regnore", regnore);//注册号
                        request6.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        if (!DataManager.String6.equals(DataManager.BaseinfoList.get(0).ENTNAME)) {
                            GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                            request121.add("token", token);
                            request121.add("deviceId", model);
                            request121.add("KeyNo", KeyNo);
                            request121.add("regnore", DataManager.BaseinfoList.get(0).REGNO);
                            request121.add("entname", DataManager.BaseinfoList.get(0).ENTNAME);
                            request121.add("memberId", csp.getID());
                            request121.add("enttype", DataManager.BaseinfoList.get(0).ENTTYPE);
                            request121.add("modulename", "司法信息");
                            CallServer.getInstance().add(CompanyDetailsActivity.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                        }
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request6, MyhttpCallBack.getInstance(), 0X006, true, false, true);
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 7://预警信息
                    if (!DataManager.allcountsList.get(0).WarningCount.equals("0")) {
                        waitDialog.show();
                        GsonUtil request7 = new GsonUtil(URLconstant.URLINSER + URLconstant.GETALERT, RequestMethod.GET);
                        request7.add("token", token);
                        request7.add("deviceId", model);
                        request7.add("KeyNo", KeyNo);
                        request7.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        if (!DataManager.String7.equals(DataManager.BaseinfoList.get(0).ENTNAME)) {
                            GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                            request121.add("token", token);
                            request121.add("deviceId", model);
                            request121.add("KeyNo", KeyNo);
                            request121.add("regnore", DataManager.BaseinfoList.get(0).REGNO);
                            request121.add("entname", DataManager.BaseinfoList.get(0).ENTNAME);
                            request121.add("memberId", csp.getID());
                            request121.add("enttype", DataManager.BaseinfoList.get(0).ENTTYPE);
                            request121.add("modulename", "预警信息");
                            CallServer.getInstance().add(CompanyDetailsActivity.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                        }
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request7, MyhttpCallBack.getInstance(), 0x007, true, false, true);
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 8://行政处罚
                    if (!DataManager.allcountsList.get(0).PunishCount.equals("0")) {
                        waitDialog.show();
                        GsonUtil request = new GsonUtil(URLconstant.URLINSER + URLconstant.PUNISHURL, RequestMethod.GET);
                        request.add("token", token);
                        request.add("deviceId", model);
                        request.add("KeyNo", KeyNo);
                        request.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        if (!DataManager.String8.equals(DataManager.BaseinfoList.get(0).ENTNAME)) {
                            GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                            request121.add("token", token);
                            request121.add("deviceId", model);
                            request121.add("KeyNo", KeyNo);
                            request121.add("regnore", DataManager.BaseinfoList.get(0).REGNO);
                            request121.add("entname", DataManager.BaseinfoList.get(0).ENTNAME);
                            request121.add("memberId", csp.getID());
                            request121.add("enttype", DataManager.BaseinfoList.get(0).ENTTYPE);
                            request121.add("modulename", "行政处罚");
                            CallServer.getInstance().add(CompanyDetailsActivity.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                        }
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request, MyhttpCallBack.getInstance(), 0X008, true, false, true);
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 9://经营异常
                    if (!DataManager.allcountsList.get(0).AbnormityCount.equals("0")) {
                        waitDialog.show();
                        GsonUtil request9 = new GsonUtil(URLconstant.URLINSER + URLconstant.ABNORMALURL, RequestMethod.GET);
                        request9.add("token", token);
                        request9.add("deviceId", model);
                        request9.add("KeyNo", KeyNo);
                        request9.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        if (!DataManager.String9.equals(DataManager.BaseinfoList.get(0).ENTNAME)) {
                            GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                            request121.add("token", token);
                            request121.add("deviceId", model);
                            request121.add("KeyNo", KeyNo);
                            request121.add("regnore", DataManager.BaseinfoList.get(0).REGNO);
                            request121.add("entname", DataManager.BaseinfoList.get(0).ENTNAME);
                            request121.add("memberId", csp.getID());
                            request121.add("enttype", DataManager.BaseinfoList.get(0).ENTTYPE);
                            request121.add("modulename", "经营异常");
                            CallServer.getInstance().add(CompanyDetailsActivity.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                        }
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request9, MyhttpCallBack.getInstance(), 0X009, true, false, true);
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 10://专利信息
                    if (!DataManager.allcountsList.get(0).PatentCount.equals("0")) {
                        waitDialog.show();
                        GsonUtil request10 = new GsonUtil(URLconstant.URLINSER + URLconstant.PATENTURL, RequestMethod.GET);
                        request10.add("token", token);
                        request10.add("deviceId", model);
                        request10.add("KeyNo", KeyNo);
                        request10.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        if (!DataManager.String10.equals(DataManager.BaseinfoList.get(0).ENTNAME)) {
                            GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                            request121.add("token", token);
                            request121.add("deviceId", model);
                            request121.add("KeyNo", KeyNo);
                            request121.add("regnore", DataManager.BaseinfoList.get(0).REGNO);
                            request121.add("entname", DataManager.BaseinfoList.get(0).ENTNAME);
                            request121.add("memberId", csp.getID());
                            request121.add("enttype", DataManager.BaseinfoList.get(0).ENTTYPE);
                            request121.add("modulename", "专利信息");
                            CallServer.getInstance().add(CompanyDetailsActivity.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                        }
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request10, MyhttpCallBack.getInstance(), 0X010, true, false, true);
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 11://商标信息
                    if (!DataManager.allcountsList.get(0).TrademarkCount.equals("0")) {
                        waitDialog.show();
                        GsonUtil request11 = new GsonUtil(URLconstant.URLINSER + URLconstant.TRADEMARKURL, RequestMethod.GET);
                        request11.add("token", token);
                        request11.add("deviceId", model);
                        request11.add("KeyNo", KeyNo);
                        request11.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        if (!DataManager.String11.equals(DataManager.BaseinfoList.get(0).ENTNAME)) {
                            GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                            request121.add("token", token);
                            request121.add("deviceId", model);
                            request121.add("KeyNo", KeyNo);
                            request121.add("regnore", DataManager.BaseinfoList.get(0).REGNO);
                            request121.add("entname", DataManager.BaseinfoList.get(0).ENTNAME);
                            request121.add("memberId", csp.getID());
                            request121.add("enttype", DataManager.BaseinfoList.get(0).ENTTYPE);
                            request121.add("modulename", "商标信息");
                            CallServer.getInstance().add(CompanyDetailsActivity.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                        }
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request11, MyhttpCallBack.getInstance(), 0x011, true, false, true);
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 12://著作权
                    if (!DataManager.allcountsList.get(0).CopyrightCount.equals("0")) {
                        waitDialog.show();
                        GsonUtil request12 = new GsonUtil(URLconstant.URLINSER + URLconstant.COPYRIGHTURL, RequestMethod.GET);
                        request12.add("token", token);
                        request12.add("deviceId", model);
                        request12.add("KeyNo", KeyNo);
                        request12.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        if (!DataManager.String12.equals(DataManager.BaseinfoList.get(0).ENTNAME)) {
                            GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                            request121.add("token", token);
                            request121.add("deviceId", model);
                            request121.add("KeyNo", KeyNo);
                            request121.add("regnore", DataManager.BaseinfoList.get(0).REGNO);
                            request121.add("entname", DataManager.BaseinfoList.get(0).ENTNAME);
                            request121.add("memberId", csp.getID());
                            request121.add("enttype", DataManager.BaseinfoList.get(0).ENTTYPE);
                            request121.add("modulename", "著作权");
                            CallServer.getInstance().add(CompanyDetailsActivity.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                        }
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request12, MyhttpCallBack.getInstance(), 0x012, true, false, true);
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 13://广告资质
                    if (!DataManager.allcountsList.get(0).AdvertisementCount.equals("0")) {
                        waitDialog.show();
                        GsonUtil request13 = new GsonUtil(URLconstant.URLINSER + URLconstant.ADVERTISEMENTURL, RequestMethod.GET);
                        request13.add("token", token);
                        request13.add("deviceId", model);
                        request13.add("KeyNo", KeyNo);
                        request13.add("memberId", "");
                        request13.add("content", "");
                        if (!DataManager.String13.equals(DataManager.BaseinfoList.get(0).ENTNAME)) {
                            GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                            request121.add("token", token);
                            request121.add("deviceId", model);
                            request121.add("KeyNo", KeyNo);
                            request121.add("regnore", DataManager.BaseinfoList.get(0).REGNO);
                            request121.add("entname", DataManager.BaseinfoList.get(0).ENTNAME);
                            request121.add("memberId", csp.getID());
                            request121.add("enttype", DataManager.BaseinfoList.get(0).ENTTYPE);
                            request121.add("modulename", "广告资质");
                            CallServer.getInstance().add(CompanyDetailsActivity.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                        }
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request13, MyhttpCallBack.getInstance(), 0x013, true, false, true);
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 14://守合同重信用
                    if (!DataManager.allcountsList.get(0).CreditCount.equals("0")) {
                        waitDialog.show();
                        GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.OBEYEDURL, RequestMethod.GET);
                        request14.add("token", token);
                        request14.add("deviceId", model);
                        request14.add("KeyNo", KeyNo);
                        request14.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        if (!DataManager.String14.equals(DataManager.BaseinfoList.get(0).ENTNAME)) {
                            GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                            request121.add("token", token);
                            request121.add("deviceId", model);
                            request121.add("KeyNo", KeyNo);
                            request121.add("regnore", DataManager.BaseinfoList.get(0).REGNO);
                            request121.add("entname", DataManager.BaseinfoList.get(0).ENTNAME);
                            request121.add("memberId", csp.getID());
                            request121.add("enttype", DataManager.BaseinfoList.get(0).ENTTYPE);
                            request121.add("modulename", "守合同重信用");
                            CallServer.getInstance().add(CompanyDetailsActivity.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                        }
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request14, MyhttpCallBack.getInstance(), 0x014, true, false, true);
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 15://自主公示
                    if (!DataManager.allcountsList.get(0).AnnualCount.equals("0")) {
                        waitDialog.show();
                        GsonUtil request15 = new GsonUtil(URLconstant.URLINSER + URLconstant.GETAUTO, RequestMethod.GET);
                        request15.add("token", token);
                        request15.add("deviceId", model);
                        request15.add("KeyNo", KeyNo);
                        request15.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        if (!DataManager.String15.equals(DataManager.BaseinfoList.get(0).ENTNAME)) {
                            GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                            request121.add("token", token);
                            request121.add("deviceId", model);
                            request121.add("KeyNo", KeyNo);
                            request121.add("regnore", DataManager.BaseinfoList.get(0).REGNO);
                            request121.add("entname", DataManager.BaseinfoList.get(0).ENTNAME);
                            request121.add("memberId", csp.getID());
                            request121.add("enttype", DataManager.BaseinfoList.get(0).ENTTYPE);
                            request121.add("modulename", "自主公示");
                            CallServer.getInstance().add(CompanyDetailsActivity.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                        }
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request15, MyhttpCallBack.getInstance(), MSG, true, false, true);
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 16://全景视图
                    startActivity(new Intent(CompanyDetailsActivity.this, Panoramic_Activity.class).putExtra("KeyNo", KeyNo).putExtra("deviceId", model).putExtra("priptype", DataManager.BaseinfoList.get(0).ENTTYPE).putExtra("regnore", regnore).putExtra("entname", DataManager.BaseinfoList.get(0).ENTNAME));
                    break;
                case 17://投资连图
                    startActivity(new Intent(CompanyDetailsActivity.this, H5ViewActivity.class).putExtra("KeyNo", DataManager.BaseinfoList.get(0).PRIPID).putExtra("URL", URLconstant.TOUZILIAN).putExtra("regno", DataManager.BaseinfoList.get(0).REGNO).putExtra("entname", DataManager.BaseinfoList.get(0).ENTNAME).putExtra("msg", "1").putExtra("Tname", "投资链图"));
                    //Toast.show( "此模块，正在开发中...");
                    break;
                case 18://发展历程
                    startActivity(new Intent(CompanyDetailsActivity.this, H5ViewActivity.class).putExtra("KeyNo", DataManager.BaseinfoList.get(0).PRIPID).putExtra("URL", URLconstant.FAZHAN).putExtra("regno", DataManager.BaseinfoList.get(0).REGNO).putExtra("entname", DataManager.BaseinfoList.get(0).ENTNAME).putExtra("estdate", DataManager.BaseinfoList.get(0).ESTDATE).putExtra("msg", "2").putExtra("Tname", "发展历程").putExtra("priptype", DataManager.BaseinfoList.get(0).ENTTYPE));
                    //Toast.show("此模块，正在开发中...");
                    break;
                case 19://招投标
                    if (!DataManager.allcountsList.get(0).BiddingCount.equals("0")) {
                        Intent i19 = new Intent(CompanyDetailsActivity.this, H5ViewActivity.class);
                        i19.putExtra("KeyNo", DataManager.BaseinfoList.get(0).ENTNAME);
                        i19.putExtra("pripid", DataManager.BaseinfoList.get(0).PRIPID);
                        i19.putExtra("URL", URLconstant.TENDER);
                        i19.putExtra("Tname", "招投标");
                        i19.putExtra("regno", DataManager.BaseinfoList.get(0).REGNO);
                        i19.putExtra("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        i19.putExtra("msg", "3");
                        startActivity(i19);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
//                    Toast.show( "此模块，正在开发中...");
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 20://企业新闻
                    if (!DataManager.allcountsList.get(0).EntNewCount.equals("0")) {
                        Intent i20 = new Intent(CompanyDetailsActivity.this, H5ViewActivity.class);
                        i20.putExtra("KeyNo", DataManager.BaseinfoList.get(0).ENTNAME);
                        i20.putExtra("pripid", DataManager.BaseinfoList.get(0).PRIPID);
                        i20.putExtra("URL", URLconstant.COMPANYNEWS);
                        i20.putExtra("Tname", "企业新闻");
                        i20.putExtra("regno", DataManager.BaseinfoList.get(0).REGNO);
                        i20.putExtra("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        i20.putExtra("msg", "4");
                        startActivity(i20);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        //                    Toast.show( "此模块，正在开发中...");
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 21://企业招聘
                    if (!DataManager.allcountsList.get(0).JobCount.equals("0")) {
                        Intent i21 = new Intent(CompanyDetailsActivity.this, H5ViewActivity.class);
                        i21.putExtra("KeyNo", DataManager.BaseinfoList.get(0).ENTNAME);
                        i21.putExtra("pripid", DataManager.BaseinfoList.get(0).PRIPID);
                        i21.putExtra("URL", URLconstant.RECRUIT);
                        i21.putExtra("Tname", "企业招聘");
                        i21.putExtra("regno", DataManager.BaseinfoList.get(0).REGNO);
                        i21.putExtra("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                        i21.putExtra("msg", "5");
                        startActivity(i21);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        //                    Toast.show( "此模块，正在开发中...");
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 22://企业展示
                    if (!DataManager.allcountsList.get(0).EntShowCount.equals("0")) {
                        //Toast.show("此模块，正在开发中...");
                        startActivity(new Intent(CompanyDetailsActivity.this, H5ViewActivity.class).putExtra("KeyNo", DataManager.BaseinfoList.get(0).PRIPID).putExtra("URL", URLconstant.SHOW).putExtra("regno", DataManager.BaseinfoList.get(0).REGNO).putExtra("priptype", DataManager.BaseinfoList.get(0).ENTTYPE).putExtra("entname", DataManager.BaseinfoList.get(0).ENTNAME).putExtra("msg", "6"));
                    } else {
                        android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 23://信用报告
                    Intent i23 = new Intent(CompanyDetailsActivity.this, ReportActivity.class);
                    startActivity(i23);
                    break;

                case 500://没有数据时返回信息
                    android.widget.Toast.makeText(CompanyDetailsActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public void init() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("是否登录");
        builder.setMessage("请先登录账号!");
        builder.setPositiveButton("去登陆", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(CompanyDetailsActivity.this, LoginActivity.class));
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                finish();
//                startActivity(new Intent(SearchFirmActivty.this, MainActivity.class));
            }
        });
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失


        if (DataManager.allcountsList.size() > 0) {
            details_tit1.setText(DataManager.allcountsList.get(0).PageView);
        }

//        if(DataManager.allcountsList.get(0).IsClaim=="1"||DataManager.allcountsList.get(0).IsClaim.equals("1")){
//            details_tit3.setText("已认领");
//        }if(DataManager.allcountsList.get(0).IsClaim=="2"||DataManager.allcountsList.get(0).IsClaim.equals("2")) {
//            details_tit3.setText("认领中");
//        }else {
//            details_tit3.setText("未认领");
//        }
        if (DataManager.BaseinfoList.size()==0||DataManager.BaseinfoList.get(0).NAME.equals("")) {
            name.setText("无");
        } else {
            name.setText(DataManager.BaseinfoList.get(0).NAME);
        }


        if (DataManager.BaseinfoList.size()!=0&&(DataManager.BaseinfoList.get(0).ENTNAME).indexOf("分公司") != -1) {
            gai1.setText("负责人");
            regcap.setText("无");
        } else {
            if (DataManager.BaseinfoList.size()>0&&DataManager.BaseinfoList.get(0).REGCAP.equals("")) {
                regcap.setText("0万元");
            } else {
                regcap.setText(DataManager.BaseinfoList.get(0).REGCAP.substring(0, DataManager.BaseinfoList.get(0).REGCAP.indexOf(".")) + "万元");
            }

        }
        estdate.setText(DataManager.BaseinfoList.get(0).ESTDATE);
        enttype.setText(DataManager.BaseinfoList.get(0).ENTTYPE_CN);


        pd = new ProgressDialog(CompanyDetailsActivity.this);
        pd.setMessage("正在加载中...");
        pd.setCancelable(false);
        d_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);

            }
        });


        if (DataManager.BaseinfoList != null && DataManager.BaseinfoList.size() > 0) {
//            String stra = (DataManager.BaseinfoList.get(0).REGSTATE_CN).substring(0, 2);
//            details_tit3.setText(stra);//状态
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

        if (DataManager.allcountsList.get(0).IsFavorite.equals("false")) {//当前状态为未关注，所以点击是关注
            pb_4_img.setBackgroundResource(R.mipmap.btm_4_n);
            pb_4_txt.setText("关注");
        } else {
            pb_4_img.setBackgroundResource(R.mipmap.btm_4_s);
            pb_4_txt.setText("已关注");
        }

        pb_0.setOnClickListener(onClickListener);
        pb_1.setOnClickListener(onClickListener);
        pb_2.setOnClickListener(onClickListener);
        pb_3.setOnClickListener(onClickListener);
        pb_4.setOnClickListener(onClickListener);
        saveG.setOnClickListener(onClickListener);

        // 实例化标题栏弹窗
        titlePopup = new TitlePopup(this, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        titlePopup.setItemOnClickListener(onitemClick);
        // 给标题栏弹窗添加子类
        titlePopup.addAction(new ActionItem(this, "认领企业",
                R.mipmap.icon_menu_group));
    }

    public void popupmenu(View v) {
        titlePopup.show(findViewById(R.id.d_more));
    }

    private TitlePopup.OnItemOnClickListener onitemClick = new TitlePopup.OnItemOnClickListener() {

        @Override
        public void onItemClick(ActionItem item, int position) {
            switch (position) {
                case 0://认领企业
                    if (!csp.getLoginStatus()) {//判定是否登录
                        //Toast.show("请先登录账号");
                        dialog.show();
                    } else {
                        if (DataManager.allcountsList.get(0).IsClaim == "1" || DataManager.allcountsList.get(0).IsClaim.equals("1")) {
                            Toast.show("该企业已认领");
                        } else {
                            Intent i = new Intent(CompanyDetailsActivity.this, ToClaimActivity.class);
                            startActivity(i);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pb_1://首页
                    Intent i1 = new Intent(CompanyDetailsActivity.this, MainActivity.class);
                    startActivity(i1);
                    finish();
                    break;
                case R.id.pb_2://评论
                    Intent i21 = new Intent(CompanyDetailsActivity.this, CommentListActivity.class);
                    i21.putExtra("type", 0);
                    startActivity(i21);
                    break;
                case R.id.pb_0://二维码名片
                    File file = new File(Environment.getExternalStorageDirectory() + "/Credit/cache/" + DataManager.BaseinfoList.get(0).REGNO + "_TwoDimImg.jpg");
                    if (file.exists()) {//获取本地图片路径是否存在
                        CompanyDetailsActivity.handler.sendEmptyMessage(25);
                    } else {
                        waitDialog.show();
                        GsonUtil request100 = new GsonUtil(URLconstant.URLINSER + URLconstant.TWODIM, RequestMethod.GET);
                        request100.add("deviceId", model);
                        request100.add("token", token);
                        request100.add("KeyNo", KeyNo);
                        request100.add("regnore", regnore);
                        request100.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE_CN);
                        request100.add("entname", DataManager.BaseinfoList.get(0).ENTNAME);
                        CallServer.getInstance().add(CompanyDetailsActivity.this, request100, MyhttpCallBack.getInstance(), 0x601, true, false, true);
                    }
                    break;
                case R.id.pb_3://企业投诉
                    waitDialog.show();
                    GsonUtil ComplaintsRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.GETCOMPLAIN, RequestMethod.GET);
                    ComplaintsRuerst.add("token", MD5.MD5s("" + new Build().MODEL));//csp.getID()
                    ComplaintsRuerst.add("KeyNo", "");//csp.getID()
                    ComplaintsRuerst.add("deviceId", new Build().MODEL);
                    ComplaintsRuerst.add("enterId", enterId);
                    CallServer.getInstance().add(CompanyDetailsActivity.this, ComplaintsRuerst, MyhttpCallBack.getInstance(), 0x994, true, false, true);

                    break;
                case R.id.pb_4://关注
                    if (!csp.getLoginStatus()) {//判定是否登录
                        //Toast.show("请先登录账号");
                        dialog.show();
                    } else {
                        waitDialog.show();
                        if (pb_4_txt.getText().toString().equals("关注")) {//当前状态为未关注，所以点击是关注
                            GsonUtil requestG = new GsonUtil(URLconstant.URLINSER + URLconstant.YESFAVORITE, RequestMethod.GET);
                            requestG.add("deviceId", model);
                            requestG.add("token", tokens);
                            requestG.add("KeyNo", KeyNos);
                            requestG.add("memberId", csp.getID());
                            requestG.add("attentionTypeId", "11");
                            CallServer.getInstance().add(CompanyDetailsActivity.this, requestG, MyhttpCallBack.getInstance(), 0x101, true, false, true);
                        } else {//当前状态为已关注，所以点击是取消关注
                            if (type == 5) {
                                DataManager.FavotiteListS.data.AttentionList.remove(posit);
                            }
                            GsonUtil requestN = new GsonUtil(URLconstant.URLINSER + URLconstant.NOFAVORITE, RequestMethod.GET);
                            requestN.add("deviceId", model);
                            requestN.add("token", tokens);
                            requestN.add("KeyNo", KeyNos);
                            requestN.add("memberId", csp.getID());
                            CallServer.getInstance().add(CompanyDetailsActivity.this, requestN, MyhttpCallBack.getInstance(), 0x102, true, false, true);
                        }
                    }
                    break;
                case R.id.panoramic://全景
                    startActivity(new Intent(CompanyDetailsActivity.this, Panoramic_Activity.class).putExtra("KeyNo", KeyNo).putExtra("deviceId", model).putExtra("priptype", DataManager.BaseinfoList.get(0).ENTTYPE).putExtra("regnore", regnore).putExtra("entname", DataManager.BaseinfoList.get(0).ENTNAME));
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (waitDialog!=null){
            pd.dismiss();
        }
        if(pd!=null){
            pd.dismiss();
        }

    }
}
