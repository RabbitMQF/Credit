package com.example.credit.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Adapters.MyGridAdapter1;
import com.example.credit.Adapters.MyGridAdapter2;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.MyGridView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司信息界面
 */
public class CompanyDetailsActivity extends BaseActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);
        ViewUtils.inject(this);
        mScrollView.smoothScrollTo(0, 20);
        Intent i = getIntent();
        int s = i.getIntExtra("s", 0);
        position = i.getIntExtra("position", 0);
        detailsList = DataManager.searchList;

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
                    case 2://荣誉信息
                        Intent i2 = new Intent(CompanyDetailsActivity.this, Honor_Support_Activity.class);
                        i2.putExtra("st", 1);
                        startActivity(i2);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 3://扶持信息
                        Intent i3 = new Intent(CompanyDetailsActivity.this, Honor_Support_Activity.class);
                        i3.putExtra("st", 2);
                        startActivity(i3);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 5:
                        Intent i5 = new Intent(CompanyDetailsActivity.this, PledgeActivity.class);
                        startActivity(i5);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 6://司法信息
                        Intent i6 = new Intent(CompanyDetailsActivity.this, JudicialActivity.class);
                        startActivity(i6);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 8:
                        Intent i8 = new Intent(CompanyDetailsActivity.this, PunishActivity.class);
                        startActivity(i8);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 9://经营异常
                        Intent i9 = new Intent(CompanyDetailsActivity.this, AbnormalActivity.class);
                        startActivity(i9);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 10:
                        Intent i10 = new Intent(CompanyDetailsActivity.this, PatentActivity.class);
                        startActivity(i10);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 11:
                        Intent i11 = new Intent(CompanyDetailsActivity.this, TrademarkActivity.class);
                        startActivity(i11);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 12:
                        Intent i12 = new Intent(CompanyDetailsActivity.this, CopyrightActivity.class);
                        startActivity(i12);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 13://广告资质
                        Intent i13 = new Intent(CompanyDetailsActivity.this, AdvertisementActivity.class);
                        startActivity(i13);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 14://守合同重信用
                        Intent i14 = new Intent(CompanyDetailsActivity.this, ObeyedActivity.class);
                        startActivity(i14);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
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
                    Intent i0 = new Intent(CompanyDetailsActivity.this, DetailsContentActivity.class);
                    i0.putExtra("position", position);
                    startActivity(i0);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;
                case 1://行政审批
                    startActivity(new Intent(CompanyDetailsActivity.this, AdminActivity.class));
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;
                case 2://荣誉信息
                    GsonUtil request2 = new GsonUtil(URLconstant.HONORURL, RequestMethod.GET);
                    CallServer.getInstance().add(CompanyDetailsActivity.this, request2, MyhttpCallBack.getInstance(), 0x002, true, false, true);

                    break;
                case 3://扶持信息
                    GsonUtil request3 = new GsonUtil(URLconstant.SUPPORTURL, RequestMethod.GET);
                    CallServer.getInstance().add(CompanyDetailsActivity.this, request3, MyhttpCallBack.getInstance(), 0x003, true, false, true);

                    break;
                case 4://抵押信息
                    startActivity(new Intent(CompanyDetailsActivity.this, Mortgage_detail_Activity.class));
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;
                case 5://出质信息
                    GsonUtil request5 = new GsonUtil(URLconstant.PLEDGEURL, RequestMethod.GET);
                    CallServer.getInstance().add(CompanyDetailsActivity.this, request5, MyhttpCallBack.getInstance(), 0x005, true, false, true);

                    break;
                case 6://司法信息
                    GsonUtil request8 = new GsonUtil(URLconstant.JUDICIALURL, RequestMethod.GET);
                    CallServer.getInstance().add(CompanyDetailsActivity.this, request8, MyhttpCallBack.getInstance(), 0X006, true, false, true);

                    break;
                case 7://预警信息
                    CallServer.getInstance().add(CompanyDetailsActivity.this,new GsonUtil(URLconstant.GETALERT,RequestMethod.GET),MyhttpCallBack.getInstance(),0x007,true,false,true);
                    Intent i7 = new Intent(CompanyDetailsActivity.this, AlertActivity.class);
                    startActivity(i7);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;
                case 8://行政处罚
                    GsonUtil request = new GsonUtil(URLconstant.PUNISHURL, RequestMethod.GET);
                    CallServer.getInstance().add(CompanyDetailsActivity.this, request, MyhttpCallBack.getInstance(), 0X008, true, false, true);

                    break;
                case 9://经营异常
                    GsonUtil request9 = new GsonUtil(URLconstant.ABNORMALURL, RequestMethod.GET);
                    CallServer.getInstance().add(CompanyDetailsActivity.this, request9, MyhttpCallBack.getInstance(), 0X009, true, false, true);

                    break;
                case 10://专利信息
                    GsonUtil request10 = new GsonUtil(URLconstant.PATENTURL, RequestMethod.GET);
                    CallServer.getInstance().add(CompanyDetailsActivity.this, request10, MyhttpCallBack.getInstance(), 0X010, true, false, true);

                    break;
                case 11://商标信息
                    GsonUtil request11 = new GsonUtil(URLconstant.TRADEMARKURL, RequestMethod.GET);
                    CallServer.getInstance().add(CompanyDetailsActivity.this, request11, MyhttpCallBack.getInstance(), 0x011, true, false, true);
                    break;
                case 12://著作权
                    GsonUtil request12 = new GsonUtil(URLconstant.COPYRIGHTURL, RequestMethod.GET);
                    CallServer.getInstance().add(CompanyDetailsActivity.this, request12, MyhttpCallBack.getInstance(), 0x012, true, false, true);
                    break;
                case 13://广告资质
                    GsonUtil request13 = new GsonUtil(URLconstant.ADVERTISEMENTURL, RequestMethod.GET);
                    CallServer.getInstance().add(CompanyDetailsActivity.this, request13, MyhttpCallBack.getInstance(), 0x013, true, false, true);

                    break;
                case 14://守合同重信用
                    GsonUtil request14= new GsonUtil(URLconstant.OBEYEDURL, RequestMethod.GET);
                    CallServer.getInstance().add(CompanyDetailsActivity.this, request14, MyhttpCallBack.getInstance(), 0x014, true, false, true);

                    break;
                case 15://自主公示
                    CallServer.getInstance().add(CompanyDetailsActivity.this, new GsonUtil(URLconstant.GETAUTONOMY, RequestMethod.GET), MyhttpCallBack.getInstance(), MSG, true, false, true);
                    startActivity(new Intent(CompanyDetailsActivity.this, AutonomyActivity.class));
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;
                default:
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
            String stra=(detailsList.get(position).REGSTATE_CN).substring(0,2);
            details_tit.setText(stra);//状态
            details_tit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopupWindow(v);
                }
            });
            cp_name.setText(detailsList.get(position).entname);
            List<String> lt = new ArrayList<String>();
            lt.add(detailsList.get(position).REGCAP+"万元");
            lt.add(detailsList.get(position).NAME);
            lt.add(detailsList.get(position).OPFROM);
            lt.add(detailsList.get(position).OPFROM);
            lt.add(detailsList.get(position).REGNO);
            lt.add(detailsList.get(position).INDUSTRYPHY);
            int size = lt.size();
            arrays4 = (String[]) lt.toArray(new String[size]);
        }
    }
    public void  showPopupWindow(View view){
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(CompanyDetailsActivity.this).inflate(
                R.layout.popupwindow_state, null);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        TextView tv= (TextView) contentView.findViewById(R.id.tv_state);
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
