package com.example.credit.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.CustomPopupwindow;
import com.yolanda.nohttp.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by alucard on 2016-05-12.
 */
public class SearchFirmActivty extends BaseActivity implements GestureDetector.OnGestureListener {
    public static final String ENCODEING_TRYPE = "UTF-8";
    public static final String SEARCH_HISTORY = "search_history";
    public static final int NOHTTP_SEARCH = 0x022;
    public static EditText searchEt;
    public static TextView downButton, city, capital, time, industry, selectCity, tab_frim, tab_illegal, tab_shareholder;
    TextView temp;
    public static ImageView city_arraow, capital_arraow, time_arraow, industry_arraow, arrowBack;
    CustomPopupwindow popupwindow;
    ImageView search_bt;
    public static boolean city_check = false, capital_check = false, time_check = false, industry_check = false, tab_frim_check = false, tab_illegal_check = false, tab_shareholder_check = false;
    LinearLayout his_sra, select;
    RelativeLayout searchContent;
    ListView menu_one;
    public static ListView menu_two;
    ViewGroup.MarginLayoutParams oneLayoutParams;
    ViewGroup.MarginLayoutParams twoLayoutParams;
    int screenWidth;
    private ArrayList<String> onelist, twolist, popDataList;
    ArrayAdapter<String> adapter2;
    List<DataManager.citys> citysList;
    ListView search_list;
    public static Handler handler;
    public static List<DataManager.search> listsea = new ArrayList<DataManager.search>();

    CreditSharePreferences csp;
    ImageView his_nullbt;
    TextView history_list_null;
    //=================
    private TextView mTextView1, mTextView2, mTextView3, mTextView4,
            mTextView5, mTextView6, mTextView7, mTextView8, mTextView9,
            mTextView10, mTextView11, mTextView12, mTextView13;
    private Animation animation1, animation2, animation3, animation4,
            animation6, animation7, animation8, animation9, animation11,
            animation12, animation13, animation2_1, animation2_2, animation2_3,
            animation2_4, animation2_5, animation2_6, animation2_8,
            animation2_9, animation2_10, animation2_11, animation2_12,
            animation2_13;
    private GestureDetector gDetector;
    private String[] keywords = {"安卓优化大师", "飞信", "赛车", "360", "短信", "文件管理",
            "拨号", "短信", "捕鱼达人", "日历", "hd", "视频播放器", "3D动态壁纸"};
    ProgressDialog pd;
    int falg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_firm_activity);
        search_list = (ListView) findViewById(R.id.search_list);
        his_sra = (LinearLayout) findViewById(R.id.his_sra);//历史记录view
        searchContent = (RelativeLayout) findViewById(R.id.searchContent);
        history_list_null = (TextView) findViewById(R.id.history_list_null);
        csp = CreditSharePreferences.getLifeSharedPreferences();
        initView();
        initData();
        search_bt = (ImageView) findViewById(R.id.search_bt);
        search_bt.setOnClickListener(onClickListener);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    falg = 2;//设置搜索结果时的默认值
                    pd.dismiss();
                    listsea = DataManager.searchList;
                    his_sra.setVisibility(View.GONE);
                    search_list.setVisibility(View.VISIBLE);
                    SearchListAdapter2 adapter2 = new SearchListAdapter2(SearchFirmActivty.this, listsea);
                    search_list.setAdapter(adapter2);
                    adapter2.notifyDataSetChanged();
                    search_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(SearchFirmActivty.this, CompanyDetailsActivity.class);
                            i.putExtra("position", position);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        }
                    });
                } else if (msg.what == 1) {
                    adapter2 = new ArrayAdapter<String>(SearchFirmActivty.this, R.layout.search_select_twolistitem, DataManager.city);
                    menu_two.setAdapter(adapter2);
                    /*menu_two.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            temp = (TextView) view.findViewById(R.id.menu_two_tv);
                            select.setVisibility(View.GONE);
                            search_list.setVisibility(View.VISIBLE);
                            //selectCity.setText(temp.getText());//旧版城市选择器
                            city.setText(temp.getText());//四合一城市选择器
                            city.setTextColor(getResources().getColor(R.color.text_nocheck));
                            city_arraow.setImageResource(R.mipmap.senior_arraow_down);
                            city_check = false;

                        }
                    });*/

                    oneLayoutParams.width = screenWidth / 2;
                    twoLayoutParams.width = screenWidth / 2;
                    menu_one.setLayoutParams(oneLayoutParams);
                    menu_two.setLayoutParams(twoLayoutParams);
                    menu_two.setVisibility(View.VISIBLE);

                }
            }
        };
        initAnimations();
        initAnimations2();
        initViews();
        randomTanslate();
        gDetector = new GestureDetector(this);
    }

    private void initData() {
        citysList = DataManager.citysList;
        List<String> provincelist = new ArrayList<String>();
        provincelist.add("不限");
        final List<DataManager.citycode> alist = null;
        for (DataManager.citys temp : DataManager.citysList) {
            provincelist.add(temp.c_name);
            // alist.add((DataManager.citycode) temp.citycode);
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
                int pid = position;
                if (twolist == null) {
                    twolist = new ArrayList<String>();
                    twolist.add("全省");
                } else {
                    twolist.clear();
                    twolist = new ArrayList<String>();
                    twolist.add("全省");
                }

//企查查城市解析     for (DataManager.citys city : citysList) {
//                    if (city.Province.equals(temp.getText())) {
//                        twolist.add(city.City);
//                    }
//                }
                DataManager.citycode a = alist.get(pid + 1);
                //a.c_name
                adapter2 = new ArrayAdapter<String>(SearchFirmActivty.this, R.layout.search_select_twolistitem, twolist);
                //menu_two.setAdapter(adapter2);
                //adapter2.notifyDataSetChanged();
                menu_two.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        temp = (TextView) view.findViewById(R.id.menu_two_tv);
                        select.setVisibility(View.GONE);
                        if (his_sra.getVisibility() == View.GONE) {//当历史界面隐藏
                            if (falg == 2) {//并且当前处于已经搜索结果时
                                search_list.setVisibility(View.VISIBLE);//则显示搜索结果list
                            } else {
                                his_sra.setVisibility(View.VISIBLE);//反之则显示历史UI
                            }
                        }

                        //selectCity.setText(temp.getText());//旧版城市选择器
                        city.setText(temp.getText());//四合一城市选择器
                        city.setTextColor(getResources().getColor(R.color.text_nocheck));
                        city_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        city_check = false;

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

    private void initView() {

        searchEt = (EditText) findViewById(R.id.search_et);
        //selectCity = (TextView) findViewById(R.id.selectCity);//旧版搜索城市
        //selectCity.setOnClickListener(this);////旧版搜索城市
        arrowBack = (ImageView) findViewById(R.id.arrow_back);
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
        //city.setOnClickListener(onClickListener);//四合一选择器
        capital.setOnClickListener(onClickListener);//四合一选择器
        time.setOnClickListener(onClickListener);//四合一选择器
        industry.setOnClickListener(onClickListener);//四合一选择器
        menu_one = (ListView) findViewById(R.id.menu_one);
        menu_two = (ListView) findViewById(R.id.menu_two);
//        history = (LinearLayout) findViewById(R.id.history);
        select = (LinearLayout) findViewById(R.id.select);
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        oneLayoutParams = (ViewGroup.MarginLayoutParams) menu_one.getLayoutParams();
        twoLayoutParams = (ViewGroup.MarginLayoutParams) menu_two.getLayoutParams();

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
                case R.id.city:
                    if (city_check) {
                        city.setTextColor(getResources().getColor(R.color.text_nocheck));
                        city_arraow.setImageResource(R.mipmap.senior_arraow_down);
                        city_check = false;
                        if (his_sra.getVisibility() == View.GONE) {//当历史界面隐藏
                            if (falg == 2) {//并且当前处于已经搜索结果时
                                search_list.setVisibility(View.VISIBLE);//则显示搜索结果list
                            } else {
                                his_sra.setVisibility(View.VISIBLE);//反之则显示历史UI
                            }
                        }
                        select.setVisibility(View.GONE);
                        if (adapter2 != null) {
                            adapter2.clear();
                        }
                    } else {
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
                        } else {
                            his_sra.setVisibility(View.GONE);//反之则隐藏历史UI
                        }
                        select.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.capital:
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
                        if (his_sra.getVisibility() == View.GONE) {//当历史界面隐藏
                            search_list.setVisibility(View.VISIBLE);//则显示搜索结果list
                        } else {
                            his_sra.setVisibility(View.VISIBLE);//反之则显示历史UI
                        }
                        popDataList = new ArrayList<>();
                        popDataList.add("不限注册");
                        popDataList.add("100万以内");
                        popDataList.add("100万到200万");
                        popDataList.add("200万到500万");
                        popDataList.add("500万到1000万");
                        popDataList.add("1000万以上");
                        popupwindow = new CustomPopupwindow(SearchFirmActivty.this, popDataList);
                        popupwindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                        popupwindow.showAtDropDownLeft(v);
                        city_check = false;
                        industry_check = false;
                        capital_check = true;
                        time_check = false;
                    }
                    break;
                case R.id.time:
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
                        if (his_sra.getVisibility() == View.GONE) {//当历史界面隐藏
                            search_list.setVisibility(View.VISIBLE);//则显示搜索结果list
                        } else {
                            his_sra.setVisibility(View.VISIBLE);//反之则显示历史UI
                        }
                        popDataList = new ArrayList<>();
                        popDataList.add("不限年限");
                        popDataList.add("1年内");
                        popDataList.add("1-2年");
                        popDataList.add("2-3年");
                        popDataList.add("3-5年");
                        popDataList.add("5-10年");
                        popDataList.add("10年以上");
                        popupwindow = new CustomPopupwindow(SearchFirmActivty.this, popDataList);
                        popupwindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                        popupwindow.showAtDropDownLeft(v);
                        time_check = true;
                        city_check = false;
                        industry_check = false;
                        capital_check = false;
                    }
                    break;
                case R.id.industry:
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
                        industry_check = true;
                    }
                    break;

                case R.id.search_bt://搜索按钮


                    Build bd = new Build();
                    String model = bd.MODEL;//设备ID
                    //model = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
                    String Tname = searchEt.getText().toString();
                    String Tks = MD5s(Tname + model);
                    String str = "";
                    try {
                        str = URLEncoder.encode("智容", "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    //历史记录保存本地SP
                    String Tnameh = Tname + ",";//历史字备用
                    if (!Tname.equals("")) {
                        if (csp.getHistory() != null && !(csp.getHistory()).equals("")) {
                            String str1 = csp.getHistory();
                            String[] strh = str1.split(",");
                            List<String> listh = new ArrayList<String>(Arrays.asList(strh));

                            if (listh != null && listh.size() < 13) {
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
                    } else {
                        android.widget.Toast.makeText(SearchFirmActivty.this, "搜索关键词不能为空!", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    pd = new ProgressDialog(SearchFirmActivty.this);
                    pd.setMessage("正在加载中...");
                    pd.setCancelable(false);
                    pd.show();
                    GsonUtil request = new GsonUtil(URLconstant.URLINSER + URLconstant.SEARCHURL, RequestMethod.GET);
                    //request.setConnectTimeout(50000);
                    request.setReadTimeout(50000);
                    request.add("token", Tks);//加密结果
                    request.add("searchKey", Tname);//string搜索关键字
                    request.add("deviceld", model);//设备ID
                    request.add("searchType", 0);//int 搜索类型 （企业、法人、失信、违法）默认为0企业,1是该法人名下企业,2失信企业,3违法企业
                    request.add("pageIndex", 0);//int 搜索请求页数
                    request.add("pageSize", 40);//int 搜索请求条数
                    //request.add("industryCode", "I");//int/string(建议传string) 行业代码为空不做限制
                    //request.add("startDateBegin", 0);//int 企业经营时间起 3（3至startDateEnd）
                    //request.add("startDateEnd", 30);//int 企业经营时间止 5（startDateBegin至5） 为空不作限制，为空必须与startDateBegin一起为空
                    //request.add("registCapiBegin", 20);//int 注册资金起 为空不做限制
                    //request.add("registCapiEnd", 5555);//int 注册资金止 为空必须和registCapiEnd一起为空
                    //request.add("province", "36");//int/string 省代码 为空不做限制 为空citicode必须为空
                    //request.add("cityCode", 3601);//int 城市代码  为空为当前省所有城市
                    CallServer.getInstance().add(SearchFirmActivty.this, request, MyhttpCallBack.getInstance(), NOHTTP_SEARCH, true, false, true);
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


    // MD5加密，32位
    public static String MD5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public final String get32MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = s.getBytes();
//使用MD5创建MessageDigest对象
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
//System.out.println((int)b);
//将没个数(int)b进行双字节加密
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

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


    //===========历史记录悬浮动画效果监听手势滑动如果滑动就随机乱序
    public boolean onTouchEvent(MotionEvent event) {
        return gDetector.onTouchEvent(event);
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
        mTextView11 = (TextView) findViewById(R.id.txt11);
        mTextView12 = (TextView) findViewById(R.id.txt12);
        mTextView13 = (TextView) findViewById(R.id.txt13);

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
        mTextView11.setOnClickListener(textbt);
        mTextView12.setOnClickListener(textbt);
        mTextView13.setOnClickListener(textbt);
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
                for (int i = R.id.txt1; i <= R.id.txt13; i++) {
                    ((TextView) findViewById(i)).setText(getKeyword(listh));
                }
            }

        } else {
            his_nullbt.setVisibility(View.GONE);
            searchContent.setVisibility(View.GONE);
            history_list_null.setVisibility(View.VISIBLE);
        }

    }

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
        mTextView5.startAnimation(animation4);
        mTextView6.startAnimation(animation6);
        mTextView7.startAnimation(animation7);
        mTextView8.startAnimation(animation8);
        mTextView9.startAnimation(animation9);
        mTextView10.startAnimation(animation9);
        mTextView11.startAnimation(animation11);
        mTextView12.startAnimation(animation12);
        mTextView13.startAnimation(animation13);
    }

    private void startAnimations2() {
        mTextView1.startAnimation(animation2_1);
        mTextView2.startAnimation(animation2_2);
        mTextView3.startAnimation(animation2_3);
        mTextView4.startAnimation(animation2_4);
        mTextView5.startAnimation(animation2_5);
        mTextView6.startAnimation(animation2_6);
        mTextView8.startAnimation(animation2_8);
        mTextView9.startAnimation(animation2_9);
        mTextView10.startAnimation(animation2_10);
        mTextView11.startAnimation(animation2_11);
        mTextView12.startAnimation(animation2_12);
        mTextView13.startAnimation(animation2_13);
    }

    private void startAnimations3() {

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

        randomTanslate();
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
                case R.id.txt11:
                    searchEt.setText(mTextView11.getText().toString());
                    break;
                case R.id.txt12:
                    searchEt.setText(mTextView12.getText().toString());
                    break;
                case R.id.txt13:
                    searchEt.setText(mTextView13.getText().toString());
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
}
