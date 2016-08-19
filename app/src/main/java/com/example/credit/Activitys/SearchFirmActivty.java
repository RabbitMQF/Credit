package com.example.credit.Activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.credit.Adapters.Citys_Adapter;
import com.example.credit.Adapters.SearchAutoAdapter;
import com.example.credit.Adapters.SearchAutoData;
import com.example.credit.Adapters.SearchListAdapter2;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.ContainsEmojiEditText;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.PullToRefreshView;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.CustomPopupwindow;
import com.yolanda.nohttp.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

/**
 * Created by alucard on 2016-05-12.
 */
public class SearchFirmActivty extends BaseActivity implements PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener,GestureDetector.OnGestureListener {
    public static final String ENCODEING_TRYPE = "UTF-8";
    public static final String SEARCH_HISTORY = "search_history";
    public static final int NOHTTP_SEARCH = 0x022;
    public static ContainsEmojiEditText searchEt;
    public static ImageView search_et_cc;
    public static TextView downButton, city, capital, time, industry, selectCity, tab_frim, tab_illegal, tab_shareholder;
    TextView temp, tempprovince;
    public static ImageView city_arraow, capital_arraow, time_arraow, industry_arraow;
    CustomPopupwindow popupwindow;
    ImageView search_bt;
    public static boolean city_check = false, capital_check = false, time_check = false, industry_check = false, tab_frim_check = false, tab_illegal_check = false, tab_shareholder_check = false;
    FrameLayout his_sra;
    LinearLayout select,arrowBack,searchContent;
    ListView menu_one;
    public static ListView menu_two;
    ViewGroup.MarginLayoutParams oneLayoutParams;
    ViewGroup.MarginLayoutParams twoLayoutParams;
    int screenWidth;
    private ArrayList<String> onelist, twolist, popDataList;
    ArrayAdapter<String> adapter2;
    List<DataManager.citys> citysList;
    ListView search_list;//搜索结果
    public static Handler handler;
    public static List<DataManager.search> listsea = new ArrayList<DataManager.search>();
    public static String industryindex = null, provinceindex = null, cityindex = null, startDateindex = null, endDateindex = null, registCapiStartIndex = null, registCapiEndIndex = null;
    CreditSharePreferences csp;
    ImageView his_nullbt;
    TextView history_list_null;
    //=================
    private TextView mTextView1, mTextView2, mTextView3, mTextView4,
            mTextView5, mTextView6, mTextView7, mTextView8, mTextView9,
            mTextView10;
    private Animation animation1, animation2, animation3, animation4,
            animation6, animation7, animation8, animation9, animation11,
            animation12, animation13, animation2_1, animation2_2, animation2_3,
            animation2_4, animation2_5, animation2_6, animation2_8,
            animation2_9, animation2_10, animation2_11, animation2_12,
            animation2_13;
    private GestureDetector gDetector;
    public static ProgressDialog pd;
    int falg = 0;
    Build bd = new Build();
    String model = bd.MODEL;//设备ID
    int po;
    int type;
    AlertDialog.Builder builder;
    public static AlertDialog dialog;
    int t=2;
    int sum=1;
    int por;
    Intent i;
    String Setname;
    String hit;
    String Urls;//地址集合
    PullToRefreshView mPullToRefreshView;
    LinearLayout typeSD,search_list_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_firm_activity);
        i = getIntent();
        type = i.getIntExtra("type", 0);
        initView();
        initCityData();
        search_bt = (ImageView) findViewById(R.id.search_bt);
        search_bt.setOnClickListener(onClickListener);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        search_list_layout.setVisibility(View.VISIBLE);
                        falg = 2;//设置搜索结果时的默认值
                        pd.dismiss();
                        if(DataManager.searchListMore!=null && DataManager.searchListMore.size()>0){
                            mPullToRefreshView.onFooterRefreshComplete();
                            por=listsea.size()-1;
                            t++;
                            for(int i=0;i<DataManager.searchListMore.size();i++){
                                listsea.add(DataManager.searchListMore.get(i));
                            }
//                            por=listsea.size()-1;
                        }else{
                            listsea = DataManager.searchList;
                            android.widget.Toast.makeText(SearchFirmActivty.this, "已搜索到" + MyhttpCallBack.baging.TotalResult+ "条数据", android.widget.Toast.LENGTH_SHORT).show();
                        }
                        his_sra.setVisibility(View.GONE);
                        search_list.setVisibility(View.VISIBLE);
                        mPullToRefreshView.setVisibility(View.VISIBLE);
                        SearchListAdapter2 adapter2 = new SearchListAdapter2(SearchFirmActivty.this, listsea);
                        search_list.setAdapter(adapter2);
                        adapter2.notifyDataSetChanged();
                        search_list.setSelection(por-2);
                        search_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                po = position;
                                pd.show();
                                String KeyNo = DataManager.searchList.get(position).PRIPID;//市场主体身份代码
                                String token = MD5s(KeyNo + model);
                                GsonUtil requst = new GsonUtil(URLconstant.URLINSER + URLconstant.GETITEMNUM, RequestMethod.GET);
                                requst.setReadTimeout(30000);
                                requst.setConnectTimeout(30000);
                                requst.add("KeyNo", KeyNo);
                                requst.add("token", token);
                                requst.add("deviceId", model);
                                requst.add("memberId", csp.getID());//86D9D7F53FCA45DD93E2D83DFCA0CB42
                                requst.add("regnore", DataManager.searchList.get(position).REGNO);
                                requst.add("priptype", DataManager.searchList.get(position).ENTTYPE);
                                if(!DataManager.StringZero.equals(DataManager.searchList.get(position).ENTNAME)) {
                                    GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                                    request121.add("token", token);
                                    request121.add("deviceId", model);
                                    request121.add("KeyNo", KeyNo);
                                    request121.add("memberId", csp.getID() );
                                    request121.add("regnore", DataManager.searchList.get(position).REGNO );
                                    request121.add("entname", DataManager.searchList.get(position).ENTNAME );
                                    request121.add("enttype", DataManager.searchList.get(position).ENTTYPE );
                                    CallServer.getInstance().add(SearchFirmActivty.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                                }
                                CallServer.getInstance().add(SearchFirmActivty.this, requst, MyhttpCallBack.getInstance(), 0x024, true, false, true);
                            }
                        });
                        break;
                    case 5:
                        pd.dismiss();
                        DataManager.StringZero=DataManager.BaseinfoList.get(0).ENTNAME;
                        Intent i = new Intent(SearchFirmActivty.this, CompanyDetailsActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        cityindex=null;
                        provinceindex=null;
                        industryindex=null;
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 110:
                        GETsearch();
                        break;
                    case 500:
                        pd.dismiss();
                        android.widget.Toast.makeText(SearchFirmActivty.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }

            }
        };
        initAnimations();
        initAnimations2();
        initViews();
        randomTanslate();
        gDetector = new GestureDetector(this);

    }

    /**
     * 初始化城市数据
     */
    private void initCityData() {
        citysList = DataManager.citysList;
        List<String> provincelist = new ArrayList<String>();
        final List<String> provinceCode = new ArrayList<>();
        final List<String> cityCoed = new ArrayList<>();
        if (provincelist.size() == 0) {
            provincelist.add("不限城市");
            provinceCode.add(null);
            for (DataManager.citys temp : DataManager.citysList) {
                provincelist.add(temp.c_name);
                provinceCode.add(temp.c_code);
            }
        } else {
            provincelist.clear();
            provinceCode.clear();
            provincelist.add("不限城市");
            provinceCode.add(null);
            for (DataManager.citys temp : DataManager.citysList) {
                provincelist.add(temp.c_name);
                provinceCode.add(temp.c_code);

            }
        }

        //List<String> citylist = new ArrayList<>();
//        if (citysList.size() > 0 && citylist != null) {    //企查查城市解析
//            for (int i = 0; i < citysList.size(); i++) {
//                if (i > 0) {
//                    if (!citysList.get(i).Province.equals(citysList.get(i - 1).Province)) {
//                        provincelist.add(citysList.get(i).Province);
//                    }
//                } else {
//                    provincelist.add(citysList.get(i).Province);
//                }
//            }
//        }
        //Citys_Adapter adapter= new Citys_Adapter(this,citysList);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.search_select_onelistitem, provincelist);
        menu_one.setAdapter(adapter);
        menu_one.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                temp = (TextView) view.findViewById(R.id.item_tv);
                tempprovince = (TextView) view.findViewById(R.id.item_tv);
                provinceindex = provinceCode.get(position);
                int pid = position;
                if (twolist == null) {
                    twolist = new ArrayList<String>();
                    //twolist.add("全省");
                } else {
                    twolist.clear();
                    cityCoed.clear();
                    twolist = new ArrayList<String>();
                    //twolist.add("全省");
                }
//企查查城市解析     for (DataManager.citys city : citysList) {
//                    if (city.Province.equals(temp.getText())) {
//                        twolist.add(city.City);
//                    }
//                }
                try {
                    for (DataManager.citycode citycode : citysList.get(pid - 1).citycode) {
                        twolist.add(citycode.c_name);
                        cityCoed.add(citycode.c_code);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {//捕获数组越界异常用于处理城市选择器的不限
                    select.setVisibility(View.GONE);
                    GETsearch();
                    if (his_sra.getVisibility() == View.GONE) {//当历史界面隐藏
                        if (falg == 2) {//并且当前处于已经搜索结果时
                            search_list.setVisibility(View.VISIBLE);//则显示搜索结果list
                            mPullToRefreshView.setVisibility(View.VISIBLE);
                            search_list_layout.setVisibility(View.VISIBLE);
                        } else {
                            his_sra.setVisibility(View.VISIBLE);//反之则显示历史UI
                        }
                    }
                    city.setText(temp.getText());//四合一城市选择器
                    city.setTextColor(getResources().getColor(R.color.text_nocheck));
                    city_arraow.setImageResource(R.mipmap.senior_arraow_down);
                    city_check = false;
                    cityindex = null;
                    searchEt.setFocusable(true);//重新获取焦点
                    searchEt.setFocusableInTouchMode(true);//重新获取焦点
                    searchEt.requestFocus();//重新获取焦点
                    e.printStackTrace();
                }

                 /*for(int i=0;i<citysList.get(pid+1).citycode.size();i++)*/
                adapter2 = new ArrayAdapter<String>(SearchFirmActivty.this, R.layout.search_select_twolistitem, twolist);
                menu_two.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
                menu_two.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        cityindex = cityCoed.get(position);
                        temp = (TextView) view.findViewById(R.id.menu_two_tv);
                        select.setVisibility(View.GONE);
                        //falg=2;
                        GETsearch();//调用搜索请求方法
                        if (his_sra.getVisibility() == View.GONE) {//当历史界面隐藏
                            if (falg == 2) {//并且当前处于已经搜索结果时
                                search_list.setVisibility(View.VISIBLE);//则显示搜索结果list
                                mPullToRefreshView.setVisibility(View.VISIBLE);
                                search_list_layout.setVisibility(View.VISIBLE);
                            } else {
                                his_sra.setVisibility(View.VISIBLE);//反之则显示历史UI
                            }
                        }

                        //selectCity.setText(temp.getText());//旧版城市选择器
                        if (temp.getText().equals("全省") || temp.getText() == "全省") {
                            city.setText(tempprovince.getText());
                        } else {//如果二级列表选择的是全省取上级省的text
                            city.setText(temp.getText());//四合一城市选择器
                        }
                        city.setTextColor(getResources().getColor(R.color.text_nocheck));
                        city_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        city_check = false;
                        searchEt.setFocusable(true);//重新获取焦点
                        searchEt.setFocusableInTouchMode(true);//重新获取焦点
                        searchEt.requestFocus();//重新获取焦点

                    }
                });

                oneLayoutParams.width = screenWidth / 2;
                twoLayoutParams.width = screenWidth / 2;
                menu_one.setLayoutParams(oneLayoutParams);
                menu_two.setLayoutParams(twoLayoutParams);
                menu_two.setVisibility(View.VISIBLE);

            }

        });


    }

    /**
     * 初始化UI组建
     */
    private void initView() {
        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_refresh_view);
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);

        search_list_layout= (LinearLayout) findViewById(R.id.search_list_layout);

        typeSD= (LinearLayout) findViewById(R.id.typeSD);

        builder = new AlertDialog.Builder(this);
        builder.setTitle("是否登录");
        builder.setMessage("浏览企业详情，请先登录账号。");
        builder.setPositiveButton("去登陆", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(SearchFirmActivty.this,LoginActivity.class));
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

        searchEt = (ContainsEmojiEditText) findViewById(R.id.search_et);
        search_et_cc = (ImageView) findViewById(R.id.search_et_cc);//叉叉
        //selectCity = (TextView) findViewById(R.id.selectCity);//旧版搜索城市
        //selectCity.setOnClickListener(this);////旧版搜索城市
        arrowBack = (LinearLayout) findViewById(R.id.arrow_back);
        arrowBack.setOnClickListener(onClickListener);
        tab_frim = (TextView) findViewById(R.id.tab_frim);
        tab_illegal = (TextView) findViewById(R.id.tab_illegal);
        tab_shareholder = (TextView) findViewById(R.id.tab_shareholder);
        tab_frim.setOnClickListener(onClickListener);
        tab_illegal.setOnClickListener(onClickListener);
        tab_shareholder.setOnClickListener(onClickListener);
//        downButton = (TextView) findViewById(R.id.down_button);
//        downButton.setOnClickListener(this);
        city = (TextView) findViewById(R.id.city);//四合一选择器
        capital = (TextView) findViewById(R.id.capital);//四合一选择器
        time = (TextView) findViewById(R.id.time);//四合一选择器
        industry = (TextView) findViewById(R.id.industry);//四合一选择器
        city_arraow = (ImageView) findViewById(R.id.city_arraow);//四合一选择器
        capital_arraow = (ImageView) findViewById(R.id.capital_arraow);//四合一选择器
        time_arraow = (ImageView) findViewById(R.id.time_arraow);//四合一选择器
        industry_arraow = (ImageView) findViewById(R.id.industry_arraow);//四合一选择器
        city.setOnClickListener(onClickListener);//四合一选择器
        capital.setOnClickListener(onClickListener);//四合一选择器
        time.setOnClickListener(onClickListener);//四合一选择器
        industry.setOnClickListener(onClickListener);//四合一选择器
        menu_one = (ListView) findViewById(R.id.menu_one);
        menu_two = (ListView) findViewById(R.id.menu_two);
//        history = (LinearLayout) findViewById(R.id.history);
        select = (LinearLayout) findViewById(R.id.select);//城市list
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        oneLayoutParams = (ViewGroup.MarginLayoutParams) menu_one.getLayoutParams();
        twoLayoutParams = (ViewGroup.MarginLayoutParams) menu_two.getLayoutParams();
        search_list = (ListView) findViewById(R.id.search_list);
        his_sra = (FrameLayout) findViewById(R.id.his_sra);//历史记录view
        searchContent = (LinearLayout) findViewById(R.id.searchContent);
        history_list_null = (TextView) findViewById(R.id.history_list_null);
        csp = CreditSharePreferences.getLifeSharedPreferences();
        if (csp.getHistory() == null || csp.getHistory().equals("")) {//给历史记录赋初始值
            String Tnameh = "余江县龙溪养蜂专业合作社,江西圆融医疗器械有限公司,景德镇市第一炉面包房,江西梦娜袜业有限公司,江西工商联合投资有限公司,江西智容科技有限公司,南昌和平大厦实业发展公司,贵溪市幸福树电器有限公司,德兴市华清汽车销售服务有限公司,江西新星建筑装饰工程有限公司,";//历史字备用
            csp.putHistory(Tnameh);
        }
        /**
         * 监听软键盘回车
         */
        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    GETsearch();
                    return true;
                }
                return false;
            }
        });

        searchEt.addTextChangedListener(new TextWatcher() {//动态判断输入框中的字数并显示隐藏图标
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 0) {
                    search_et_cc.setVisibility(View.VISIBLE);
                } else {
                    search_et_cc.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable t) {
                if (t.length() > 0) {
                    search_et_cc.setVisibility(View.VISIBLE);
                } else {
                    search_et_cc.setVisibility(View.GONE);
                }
            }
        });
        search_et_cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEt.setText("");
            }
        });

        //根据type判断查询类型
        switch (type) {
            case 0:
                Setname=i.getStringExtra("Setname");
                if(!Setname.equals("")){
                    searchEt.setText(Setname);
                }
                searchEt.setHint("请输入企业名称");
                Urls=URLconstant.URLINSER + URLconstant.SEARCHURL;
                break;
            case 1:
                searchEt.setHint("请输入法人名称");
                Urls=URLconstant.URLINSER + URLconstant.SEARCHURL;
                break;
            case 2:
                searchEt.setHint("请输入品牌名称");
                Urls=URLconstant.URLINSER + URLconstant.SEARCHURL;
                break;
            case 3:
                searchEt.setHint("请输入失信名称");
                Urls=URLconstant.URLINSER + URLconstant.SEARCHURL;
                break;
        }

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
//            case R.id.down_button:
//                popDataList = new ArrayList<>();
//                popDataList.add("查全部");
//                popDataList.add("按名称查询");
//                popDataList.add("按地址查询");
//                popDataList.add("按经营范围查询");
//                popDataList.add("按品牌/产品查询");
//                popDataList.add("按法定代表人查询");
//                popupwindow = new CustomPopupwindow(this, popDataList);
//                popupwindow.showAtDropDownLeft(v);
//                break;
                case R.id.city://城市选择按钮
                    if (city_check) {
                        city.setTextColor(getResources().getColor(R.color.text_nocheck));
                        city_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        city_check = false;
                        if (his_sra.getVisibility() == View.GONE) {//当历史界面隐藏
                            if (falg == 2) {//并且当前处于已经搜索结果时
                                search_list.setVisibility(View.VISIBLE);//则显示搜索结果list
                                mPullToRefreshView.setVisibility(View.VISIBLE);
                                search_list_layout.setVisibility(View.VISIBLE);
                            } else {
                                his_sra.setVisibility(View.VISIBLE);//反之则显示历史UI
                            }
                        }
                        select.setVisibility(View.GONE);
                        searchEt.setFocusable(true);//重新获取焦点
                        searchEt.setFocusableInTouchMode(true);//重新获取焦点
                        searchEt.requestFocus();//重新获取焦点
                        /*if (adapter2 != null) {
                            adapter2.clear();
                        }*/
                    } else {
                        if (city.getText() == "不限城市") {//判定如果选中不限回复menu_one的全屏宽
                            oneLayoutParams.width = screenWidth;
                            menu_one.setLayoutParams(oneLayoutParams);
                        }
                        city.setTextColor(getResources().getColor(R.color.text_check));
                        city_arraow.setImageResource(R.mipmap.senior_arraow_up);
                        capital.setTextColor(getResources().getColor(R.color.text_nocheck));
                        capital_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        time.setTextColor(getResources().getColor(R.color.text_nocheck));
                        time_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        industry.setTextColor(getResources().getColor(R.color.text_nocheck));
                        industry_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        city_check = true;
                        if (his_sra.getVisibility() == View.GONE) {//当历史界面隐藏
                            search_list.setVisibility(View.GONE);//则隐藏搜索结果list
                            mPullToRefreshView.setVisibility(View.GONE);
                            search_list_layout.setVisibility(View.GONE);
                        } else {
                            his_sra.setVisibility(View.GONE);//反之则隐藏历史UI
                        }
                        select.setVisibility(View.VISIBLE);
                        searchEt.clearFocus();//edit失焦点
                        searchEt.setFocusable(false);//edit失焦点
                    }
                    break;
                case R.id.capital://注册资金按钮
                    if (capital_check) {
                        capital.setTextColor(getResources().getColor(R.color.text_nocheck));
                        capital_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        capital_check = false;
                    } else {
                        city.setTextColor(getResources().getColor(R.color.text_nocheck));
                        city_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        capital.setTextColor(getResources().getColor(R.color.text_check));
                        capital_arraow.setImageResource(R.mipmap.senior_arraow_up);
                        time.setTextColor(getResources().getColor(R.color.text_nocheck));
                        time_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        industry.setTextColor(getResources().getColor(R.color.text_nocheck));
                        industry_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        select.setVisibility(View.GONE);
                        if (city_check) {
                            city.setTextColor(getResources().getColor(R.color.text_nocheck));
                            city_arraow.setImageResource(R.mipmap.senior_arraow_down);
                            city_check = false;
                            if (his_sra.getVisibility() == View.GONE) {//当历史界面隐藏
                                if (falg == 2) {//并且当前处于已经搜索结果时
                                    search_list.setVisibility(View.VISIBLE);//则显示搜索结果list
                                    mPullToRefreshView.setVisibility(View.VISIBLE);
                                    search_list_layout.setVisibility(View.VISIBLE);
                                } else {
                                    his_sra.setVisibility(View.VISIBLE);//反之则显示历史UI
                                }
                            }
                            select.setVisibility(View.GONE);
                        }
                        popDataList = new ArrayList<>();
                        popDataList.add("不限注册");
                        popDataList.add("100万以内");
                        popDataList.add("100万到200万");
                        popDataList.add("200万到500万");
                        popDataList.add("500万到1000万");
                        popDataList.add("1000万以上");
                        popupwindow = new CustomPopupwindow(SearchFirmActivty.this, popDataList, null);
                        popupwindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                        popupwindow.showAtDropDownLeft(v);
                        city_check = false;
                        industry_check = false;
                        capital_check = true;
                        time_check = false;
                    }
                    break;
                case R.id.time://经营年限按钮
                    if (time_check) {
                        time.setTextColor(getResources().getColor(R.color.text_nocheck));
                        time_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        time_check = false;
                    } else {
                        city.setTextColor(getResources().getColor(R.color.text_nocheck));
                        city_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        capital.setTextColor(getResources().getColor(R.color.text_nocheck));
                        capital_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        time.setTextColor(getResources().getColor(R.color.text_check));
                        time_arraow.setImageResource(R.mipmap.senior_arraow_up);
                        industry.setTextColor(getResources().getColor(R.color.text_nocheck));
                        industry_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        select.setVisibility(View.GONE);
                        if (city_check) {
                            city.setTextColor(getResources().getColor(R.color.text_nocheck));
                            city_arraow.setImageResource(R.mipmap.senior_arraow_down);
                            city_check = false;
                            if (his_sra.getVisibility() == View.GONE) {//当历史界面隐藏
                                if (falg == 2) {//并且当前处于已经搜索结果时
                                    search_list.setVisibility(View.VISIBLE);//则显示搜索结果list
                                    mPullToRefreshView.setVisibility(View.VISIBLE);
                                    search_list_layout.setVisibility(View.VISIBLE);
                                } else {
                                    his_sra.setVisibility(View.VISIBLE);//反之则显示历史UI
                                }
                            }
                            select.setVisibility(View.GONE);

                        }
                        popDataList = new ArrayList<>();
                        popDataList.add("不限年限");
                        popDataList.add("1年内");
                        popDataList.add("1-2年");
                        popDataList.add("2-3年");
                        popDataList.add("3-5年");
                        popDataList.add("5-10年");
                        popDataList.add("10年以上");
                        popupwindow = new CustomPopupwindow(SearchFirmActivty.this, popDataList, null);
                        popupwindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                        popupwindow.showAtDropDownLeft(v);
                        time_check = true;
                        city_check = false;
                        industry_check = false;
                        capital_check = false;
                    }
                    break;
                case R.id.industry://行业选择按钮
                    if (industry_check) {
                        industry.setTextColor(getResources().getColor(R.color.text_nocheck));
                        industry_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        industry_check = false;
                    } else {
                        city.setTextColor(getResources().getColor(R.color.text_nocheck));
                        city_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        capital.setTextColor(getResources().getColor(R.color.text_nocheck));
                        capital_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        time.setTextColor(getResources().getColor(R.color.text_nocheck));
                        time_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        industry.setTextColor(getResources().getColor(R.color.text_check));
                        industry_arraow.setImageResource(R.mipmap.senior_arraow_up);
                        if (city_check) {
                            city.setTextColor(getResources().getColor(R.color.text_nocheck));
                            city_arraow.setImageResource(R.mipmap.senior_arraow_down);
                            city_check = false;
                            if (his_sra.getVisibility() == View.GONE) {//当历史界面隐藏
                                if (falg == 2) {//并且当前处于已经搜索结果时
                                    search_list.setVisibility(View.VISIBLE);//则显示搜索结果list
                                    mPullToRefreshView.setVisibility(View.VISIBLE);
                                    search_list_layout.setVisibility(View.VISIBLE);
                                } else {
                                    his_sra.setVisibility(View.VISIBLE);//反之则显示历史UI
                                }
                            }
                            select.setVisibility(View.GONE);

                        }
                        popupwindow = new CustomPopupwindow(SearchFirmActivty.this, null, DataManager.industryDataList);
                        popupwindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                        popupwindow.setHeight(getWindowManager().getDefaultDisplay().getHeight()/2);
                        popupwindow.showAtDropDownLeft(v);
                        time_check = false;
                        city_check = false;
                        industry_check = true;
                        capital_check = false;
                    }
                    break;

                case R.id.search_bt://搜索按钮
                    GETsearch();
                    break;
                case R.id.auto_add:
                    SearchAutoData data = (SearchAutoData) v.getTag();
                    searchEt.setText(data.getContent());
                    break;
           /* case R.id.selectCity://旧版搜索城市
                if (select.getVisibility() != View.VISIBLE) {
                    select.setVisibility(View.VISIBLE);
                    history.setVisibility(View.GONE);
                } else {
                    select.setVisibility(View.GONE);
                    history.setVisibility(View.VISIBLE);
                }
                break;*/
                case R.id.arrow_back:
                    finish();
                    overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
                    cityindex=null;
                    provinceindex=null;
                    industryindex=null;
                    break;
                case R.id.tab_frim:
                    if (!tab_frim_check) {
                        tab_frim.setTextColor(getResources().getColor(R.color.black));
                        tab_illegal.setTextColor(getResources().getColor(R.color.white));
                        tab_shareholder.setTextColor(getResources().getColor(R.color.white));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            tab_frim.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_selected));
                            tab_illegal.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_unselected));
                            tab_shareholder.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_unselected));
                        }
                        tab_frim_check = true;
                        tab_illegal_check = false;
                        tab_shareholder_check = false;
                    }
                    break;
                case R.id.tab_illegal:
                    if (!tab_illegal_check) {
                        tab_frim.setTextColor(getResources().getColor(R.color.white));
                        tab_illegal.setTextColor(getResources().getColor(R.color.black));
                        tab_shareholder.setTextColor(getResources().getColor(R.color.white));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            tab_frim.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_unselected));
                            tab_illegal.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_selected));
                            tab_shareholder.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_unselected));
                        }
                        tab_frim_check = false;
                        tab_illegal_check = true;
                        tab_shareholder_check = false;
                    }
                    break;
                case R.id.tab_shareholder:
                    if (!tab_shareholder_check) {
                        tab_frim.setTextColor(getResources().getColor(R.color.white));
                        tab_illegal.setTextColor(getResources().getColor(R.color.white));
                        tab_shareholder.setTextColor(getResources().getColor(R.color.black));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            tab_frim.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_unselected));
                            tab_illegal.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_unselected));
                            tab_shareholder.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_selected));
                        }
                        tab_frim_check = false;
                        tab_illegal_check = false;
                        tab_shareholder_check = true;
                    }
                    break;

                default:
                    break;
            }
        }
    };


    /**
     * MD5加密
     *
     * @param s
     * @return
     */
    public final static String MD5s(String s) {
        try {

            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            byte[] btInput = s.getBytes(ENCODEING_TRYPE);
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int val = ((int) md[i]) & 0xff;
                if (val < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(val));

            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 历史记录悬浮动画效果监听手势滑动如果滑动就随机乱序
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(listsea.size()<=0){
            return gDetector.onTouchEvent(event);
        }else{
            return super.onTouchEvent(event);
        }
    }

    private void initViews() {
        mTextView1 = (TextView) findViewById(R.id.txt1);
        mTextView2 = (TextView) findViewById(R.id.txt2);
        mTextView3 = (TextView) findViewById(R.id.txt3);
        mTextView4 = (TextView) findViewById(R.id.txt4);
        mTextView5 = (TextView) findViewById(R.id.txt5);
        mTextView6 = (TextView) findViewById(R.id.txt6);
        mTextView7 = (TextView) findViewById(R.id.txt7);
        mTextView8 = (TextView) findViewById(R.id.txt8);
        mTextView9 = (TextView) findViewById(R.id.txt9);
        mTextView10 = (TextView) findViewById(R.id.txt10);
        mTextView1.setOnClickListener(textbt);
        mTextView2.setOnClickListener(textbt);
        mTextView3.setOnClickListener(textbt);
        mTextView4.setOnClickListener(textbt);
        mTextView5.setOnClickListener(textbt);
        mTextView6.setOnClickListener(textbt);
        mTextView7.setOnClickListener(textbt);
        mTextView8.setOnClickListener(textbt);
        mTextView9.setOnClickListener(textbt);
        mTextView10.setOnClickListener(textbt);
        his_nullbt = (ImageView) findViewById(R.id.his_nullbt);
        his_nullbt.setOnClickListener(textbt);
    }

    /**
     * 将历史记录赋给浮动控件
     */
    private void randomText() {
        ListView history_list = (ListView) findViewById(R.id.history_list);
        TextView history_list_null = (TextView) findViewById(R.id.history_list_null);
        if (csp.getHistory() != null && !(csp.getHistory()).equals("")) {
            String str = csp.getHistory();
            String[] strh = str.split(",");
            List<String> listh = new ArrayList<String>(Arrays.asList(strh));
            if (listh != null && listh.size() > 0) {
                his_nullbt.setVisibility(View.VISIBLE);
                for (int i = R.id.txt1; i <= R.id.txt10; i++) {
                    ((TextView) findViewById(i)).setText(getKeyword(listh));
                }
            }

        } else {
            his_nullbt.setVisibility(View.GONE);
            searchContent.setVisibility(View.GONE);
            history_list_null.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 随机乱序方法
     *
     * @param list 数据源
     * @return
     */
    private String getKeyword(List<String> list) {
        if (list != null && list.size() > 0) {
            int num = random.nextInt(list.size());
            String keyword = list.get(num);
            list.remove(num);
            return keyword;
        } else {
            return "";
        }
    }

    private void initAnimations() {
        animation1 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim1);
        animation2 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim2);
        animation3 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim3);
        animation4 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim4);
        animation6 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim6);
        animation7 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim7);
        animation8 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim8);
        animation9 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim9);
        animation11 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim11);
        animation12 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim12);
        animation13 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim13);
    }

    private void initAnimations2() {
        animation2_1 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim2_1);
        animation2_2 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim2_2);
        animation2_3 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim2_3);
        animation2_4 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim2_4);
        animation2_5 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim2_5);
        animation2_6 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim2_6);
        animation2_8 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim2_8);
        animation2_9 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim2_9);
        animation2_10 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim2_10);
        animation2_11 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim2_11);
        animation2_12 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim2_12);
        animation2_13 = AnimationUtils.loadAnimation(SearchFirmActivty.this, R.anim.anim2_13);
    }

    private void startAnimations1() {
        mTextView1.startAnimation(animation1);
        mTextView2.startAnimation(animation2);
        mTextView3.startAnimation(animation3);
        mTextView4.startAnimation(animation4);
        mTextView5.startAnimation(animation3);
        mTextView6.startAnimation(animation7);
        mTextView7.startAnimation(animation8);
        mTextView8.startAnimation(animation9);
        mTextView9.startAnimation(animation11);
        mTextView10.startAnimation(animation12);
    }

    private void startAnimations2() {
        mTextView1.startAnimation(animation2_1);
        mTextView2.startAnimation(animation2_2);
        mTextView3.startAnimation(animation2_3);
        mTextView4.startAnimation(animation2_4);
        mTextView5.startAnimation(animation2_5);
        mTextView6.startAnimation(animation2_6);

        mTextView7.startAnimation(animation2_6);

        mTextView8.startAnimation(animation2_8);
        mTextView9.startAnimation(animation2_9);
        mTextView10.startAnimation(animation2_10);
    }

    public boolean onDown(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    private void randomTanslate() {
        int num = random.nextInt(2);
        randomText();
        switch (num) {
            case 0:
                startAnimations1();
                break;
            case 1:
                startAnimations2();
                break;
        }
    }

    private Random random = new Random();//随机数

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        float minMove = 120;         //最小滑动距离
        float minVelocity = 0;      //最小滑动速度
        float beginX = e1.getX();
        float endX = e2.getX();
        float beginY = e1.getY();
        float endY = e2.getY();
        if (beginX - endX > minMove && Math.abs(velocityX) > minVelocity) {   //左滑
            startAnimations1();
            randomText();
        } else if (endX - beginX > minMove && Math.abs(velocityX) > minVelocity) {   //右滑
            startAnimations1();
            randomText();
        } else
        if (beginY - endY > minMove && Math.abs(velocityY) > minVelocity) {   //上滑
            startAnimations2();
            randomText();
        } else if (endY - beginY > minMove && Math.abs(velocityY) > minVelocity) {   //下滑
            startAnimations2();
            randomText();
        }
//        randomTanslate();
        return false;
    }

    public void onLongPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // System.out.println("onScroll");
        return false;
    }

    public void onShowPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    public boolean onSingleTapUp(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * 点击浮动字体给搜索框赋值
     */
    View.OnClickListener textbt = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.txt1:
                    searchEt.setText(mTextView1.getText().toString());
                    break;
                case R.id.txt2:
                    searchEt.setText(mTextView2.getText().toString());
                    break;
                case R.id.txt3:
                    searchEt.setText(mTextView3.getText().toString());
                    break;
                case R.id.txt4:
                    searchEt.setText(mTextView4.getText().toString());
                    break;
                case R.id.txt5:
                    searchEt.setText(mTextView5.getText().toString());
                    break;
                case R.id.txt6:
                    searchEt.setText(mTextView6.getText().toString());
                    break;
                case R.id.txt7:
                    searchEt.setText(mTextView7.getText().toString());
                    break;
                case R.id.txt8:
                    searchEt.setText(mTextView8.getText().toString());
                    break;
                case R.id.txt9:
                    searchEt.setText(mTextView9.getText().toString());
                    break;
                case R.id.txt10:
                    searchEt.setText(mTextView10.getText().toString());
                    break;
                case R.id.his_nullbt:
                    csp.putHistory("");
                    history_list_null.setVisibility(View.VISIBLE);
                    his_nullbt.setVisibility(View.GONE);
                    searchContent.setVisibility(View.GONE);
                    break;
            }
        }
    };

    /**
     * MD5加密+搜索历史+进度条+搜索请求方法
     */
    public void GETsearch() {

        //model = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
        String Tname = searchEt.getText().toString();
        String Tks = MD5s(Tname + model);


        //历史记录保存本地SP
        String Tnameh = Tname + ",";//历史字备用
        if (!Tname.equals("")) {
            if (csp.getHistory() != null && !(csp.getHistory()).equals("")) {
                String str1 = csp.getHistory();
                String[] strh = str1.split(",");
                List<String> listh = new ArrayList<String>(Arrays.asList(strh));
                if (listh != null && listh.size() < 10) {
                    String temp = "";
                    for (int i = 0; i < listh.size(); i++) {
                        if (Tname.equals(listh.get(i))) {
                            temp = listh.get(i);
                        }
                    }
                    if (temp.equals("")) {
                        csp.putHistory(str1 + Tnameh);
                    }
                } else {
                    String temp = "";
                    for (int i = 0; i < listh.size(); i++) {
                        if (Tname.equals(listh.get(i))) {
                            temp = listh.get(i);
                        }
                    }
                    if (temp.equals("")) {
                        listh.remove(0);
                        String strlists = "";
                        for (int i = 0; i < listh.size(); i++) {
                            strlists = strlists + listh.get(i) + ",";
                        }
                        csp.putHistory(strlists + Tnameh);
                    }
                }
            } else {
                csp.putHistory(Tnameh);
            }

            pd = new ProgressDialog(SearchFirmActivty.this);
            pd.setMessage("正在加载中...");

            pd.setCancelable(false);
            pd.show();
            GsonUtil request = new GsonUtil(Urls, RequestMethod.GET);
            request.setReadTimeout(50000);
            request.add("deviceId", model);//设备ID
            request.add("token", Tks);//加密结果
            request.add("searchKey", Tname);//string搜索关键字
            if (!csp.getLoginStatus()){
                request.add("memberId", "");
            }else{
                request.add("memberId", csp.getID());//记录用户ID
            }
            //根据type判断查询类型
            switch (type) {
                case 0:
                    request.add("searchType", 0);//int 搜索类型 （企业、法人、失信、违法）默认为0企业,1是该法人名下企业,2失信企业,3违法企业
                    break;
                case 1:
                    request.add("searchType", 1);//int 搜索类型 （企业、法人、失信、违法）默认为0企业,1是该法人名下企业,2失信企业,3违法企业
                    break;
                case 2:
                    request.add("searchType", 2);//int 搜索类型 （企业、法人、失信、违法）默认为0企业,1是该法人名下企业,2失信企业,3违法企业
                    break;
                case 3:
                    request.add("searchType", 3);//int 搜索类型 （企业、法人、失信、违法）默认为0企业,1是该法人名下企业,2失信企业,3违法企业
                    break;
            }
            request.add("pageIndex", 1);//int 搜索请求页数
            request.add("pageSize", 10);//int 搜索请求条数
            if (industryindex != null && industryindex != "") {//int/string(建议传string) 行业代码为空不做限制
                request.add("industryCode", industryindex);
            }
            if (startDateindex != null) {//int 企业经营时间起 3（3至startDateEnd）
                request.add("startDateBegin", startDateindex);
            }
            if (endDateindex != null) {//int 企业经营时间止 5（startDateBegin至5） 为空不作限制，为空必须与startDateBegin一起为空
                request.add("startDateEnd", endDateindex);
            }
            if (registCapiStartIndex != null) {//int 注册资金起 为空不做限制
                request.add("registCapiBegin", registCapiStartIndex);
            }
            if (registCapiEndIndex != null) {//int 注册资金止 为空必须和registCapiEnd一起为空
                request.add("registCapiEnd", registCapiEndIndex);
            }
            if (provinceindex != null) {//int/string 省代码 为空不做限制 为空citycode必须为空
                request.add("province", provinceindex);
            }
            if (cityindex != null && provinceindex != null && cityindex != "") {//int 城市代码  为空为当前省所有城市
                request.add("cityCode", cityindex);

            }

            CallServer.getInstance().add(SearchFirmActivty.this, request, MyhttpCallBack.getInstance(), NOHTTP_SEARCH, true, false, true);


        } else {
            android.widget.Toast.makeText(SearchFirmActivty.this, "搜索关键词不能为空!", android.widget.Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 上拉加载
     * @param view
     */
    @Override
    public void onFooterRefresh(PullToRefreshView view) {

        mPullToRefreshView.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (t <=  MyhttpCallBack.baging.TotalPage) {
                    GsonUtil request = new GsonUtil(URLconstant.URLINSER + URLconstant.SEARCHURL, RequestMethod.GET);
                    request.setReadTimeout(50000);
                    request.add("token",  MD5s(searchEt.getText().toString() + model));//加密结果
                    request.add("searchKey", searchEt.getText().toString());//string搜索关键字
                    request.add("deviceId", model);//设备ID
                    request.add("memberId", csp.getID());//86D9D7F53FCA45DD93E2D83DFCA0CB42  记录用户搜索关键字
                    //根据type判断查询类型
                    switch (type) {
                        case 0:
                            request.add("searchType", 0);//int 搜索类型 （企业、法人、失信、违法）默认为0企业,1是该法人名下企业,2失信企业,3违法企业
                            break;
                        case 1:
                            request.add("searchType", 1);//int 搜索类型 （企业、法人、失信、违法）默认为0企业,1是该法人名下企业,2失信企业,3违法企业
                            break;
                        case 2:
                            request.add("searchType", 2);//int 搜索类型 （企业、法人、失信、违法）默认为0企业,1是该法人名下企业,2失信企业,3违法企业
                            break;
                        case 3:
                            request.add("searchType", 3);//int 搜索类型 （企业、法人、失信、违法）默认为0企业,1是该法人名下企业,2失信企业,3违法企业
                            break;
                    }
                    request.add("pageIndex", t);//int 搜索请求页数
                    request.add("pageSize", 10);//int 搜索请求条数
                    if (industryindex != null && industryindex != "") {//int/string(建议传string) 行业代码为空不做限制
                        request.add("industryCode", industryindex);
                    }
                    if (startDateindex != null) {//int 企业经营时间起 3（3至startDateEnd）
                        request.add("startDateBegin", startDateindex);
                    }
                    if (endDateindex != null) {//int 企业经营时间止 5（startDateBegin至5） 为空不作限制，为空必须与startDateBegin一起为空
                        request.add("startDateEnd", endDateindex);
                    }
                    if (registCapiStartIndex != null) {//int 注册资金起 为空不做限制
                        request.add("registCapiBegin", registCapiStartIndex);
                    }
                    if (registCapiEndIndex != null) {//int 注册资金止 为空必须和registCapiEnd一起为空
                        request.add("registCapiEnd", registCapiEndIndex);
                    }
                    if (provinceindex != null) {//int/string 省代码 为空不做限制 为空citycode必须为空
                        request.add("province", provinceindex);
                    }
                    if (cityindex != null && provinceindex != null && cityindex != "") {//int 城市代码  为空为当前省所有城市
                        request.add("cityCode", cityindex);
                    }
                    CallServer.getInstance().add(SearchFirmActivty.this, request, MyhttpCallBack.getInstance(), 0x0221, true, false, true);
                    t++;
                    sum++;
                }else{
                    com.example.credit.Utils.Toast.show("没有更多数据了！");
                    mPullToRefreshView.onFooterRefreshComplete();
                }
            }
        }, 1000);

    }

    /**
     * 下拉刷新
     * @param view
     */
    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {

            @Override
            public void run() {
                mPullToRefreshView.onHeaderRefreshComplete();
                SearchListAdapter2 adapter2 = new SearchListAdapter2(SearchFirmActivty.this, listsea);
                search_list.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
            }
        }, 1000);
    }
}
