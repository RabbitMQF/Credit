package com.example.credit.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.example.credit.Adapters.SearchAutoAdapter;
import com.example.credit.Adapters.SearchAutoData;
import com.example.credit.Adapters.SearchListAdapter2;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.CustomPopupwindow;
import com.yolanda.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Created by alucard on 2016-05-12.
 */
public class SearchFirmActivty extends BaseActivity implements View.OnClickListener {
    private SearchAutoAdapter mSearchAutoAdapter;
    public static final String SEARCH_HISTORY = "search_history";
    public static final int NOHTTP_SEARCH = 0x02;
    private ListView mAutoListView;
    public static EditText searchEt;
    public static TextView downButton, city, capital, time, industry, selectCity, tab_frim, tab_illegal, tab_shareholder;
    TextView temp;
    public static ImageView city_arraow, capital_arraow, time_arraow, industry_arraow, arrowBack;
    CustomPopupwindow popupwindow;
    ImageView search_bt;
    SharedPreferences sp;
    public static boolean city_check = false, capital_check = false, time_check = false, industry_check = false,tab_frim_check=false,tab_illegal_check=false,tab_shareholder_check=false;
    LinearLayout history, select;
    ListView menu_one;
    ListView menu_two;
    ViewGroup.MarginLayoutParams oneLayoutParams;
    ViewGroup.MarginLayoutParams twoLayoutParams;
    int screenWidth;
    private ArrayList<String> onelist, twolist, popDataList;
    ArrayAdapter<String> adapter2;
    List<DataManager.citys> citysList;
    ListView search_list;
    public static Handler handler;
    public static List<DataManager.search> listsea = new ArrayList<DataManager.search>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_firm_activity);
        search_list = (ListView) findViewById(R.id.search_list);
        initView();
//        initData();
        init();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    listsea = DataManager.searchList;
                    SearchListAdapter2 adapter2 = new SearchListAdapter2(SearchFirmActivty.this, listsea);
                    search_list.setAdapter(adapter2);
                    adapter2.notifyDataSetChanged();
                    search_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i=new Intent(SearchFirmActivty.this, CompanyDetailsActivity.class);
                            i.putExtra("s",0);
                            i.putExtra("position",position);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            overridePendingTransition(R.anim.start_tran_one,R.anim.start_tran_two);
                        }
                    });
                }
            }
        };

    }

    private void initData() {
        citysList = DataManager.citysList;
        List<String> provincelist = new ArrayList<String>();
        provincelist.add("全国");
        List<String> citylist = new ArrayList<>();

        if (citysList.size() > 0 && citylist != null) {
            for (int i = 0; i < citysList.size(); i++) {
                if (i > 0) {
                    if (!citysList.get(i).Province.equals(citysList.get(i - 1).Province)) {
                        provincelist.add(citysList.get(i).Province);
                    }
                } else {
                    provincelist.add(citysList.get(i).Province);
                }
            }
        }


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.search_select_onelistitem, provincelist);
        menu_one.setAdapter(adapter);
        menu_one.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                temp = (TextView) view.findViewById(R.id.item_tv);
                if (twolist == null) {
                    twolist = new ArrayList<String>();
                    twolist.add("全省");
                } else {
                    twolist.clear();
                    twolist = new ArrayList<String>();
                    twolist.add("全省");
                }

                for (DataManager.citys city : citysList) {
                    if (city.Province.equals(temp.getText())) {
                        twolist.add(city.City);
                    }
                }

                adapter2 = new ArrayAdapter<String>(SearchFirmActivty.this, R.layout.search_select_twolistitem, twolist);
                menu_two.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
                menu_two.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        temp= (TextView) view.findViewById(R.id.menu_two_tv);
                        select.setVisibility(View.GONE);
                        history.setVisibility(View.VISIBLE);
                        selectCity.setText(temp.getText());
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
        selectCity = (TextView) findViewById(R.id.selectCity);
        selectCity.setOnClickListener(this);
        arrowBack = (ImageView) findViewById(R.id.arrow_back);
        arrowBack.setOnClickListener(this);
        tab_frim = (TextView) findViewById(R.id.tab_frim);
        tab_illegal = (TextView) findViewById(R.id.tab_illegal);
        tab_shareholder = (TextView) findViewById(R.id.tab_shareholder);
        tab_frim.setOnClickListener(this);
        tab_illegal.setOnClickListener(this);
        tab_shareholder.setOnClickListener(this);
//        downButton = (TextView) findViewById(R.id.down_button);
//        downButton.setOnClickListener(this);
//        city = (TextView) findViewById(R.id.city);
//        capital = (TextView) findViewById(R.id.capital);
//        time = (TextView) findViewById(R.id.time);
//        industry = (TextView) findViewById(R.id.industry);
//        city_arraow = (ImageView) findViewById(R.id.city_arraow);
//        capital_arraow = (ImageView) findViewById(R.id.capital_arraow);
//        time_arraow = (ImageView) findViewById(R.id.time_arraow);
//        industry_arraow = (ImageView) findViewById(R.id.industry_arraow);
//        city.setOnClickListener(this);
//        capital.setOnClickListener(this);
//        time.setOnClickListener(this);
//        industry.setOnClickListener(this);
        menu_one = (ListView) findViewById(R.id.menu_one);
        menu_two = (ListView) findViewById(R.id.menu_two);
        history = (LinearLayout) findViewById(R.id.history);
        select = (LinearLayout) findViewById(R.id.select);
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        oneLayoutParams = (ViewGroup.MarginLayoutParams) menu_one.getLayoutParams();
        twoLayoutParams = (ViewGroup.MarginLayoutParams) menu_two.getLayoutParams();

    }


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
//            case R.id.city:
//                if (city_check) {
//                    city.setTextColor(getResources().getColor(R.color.text_nocheck));
//                    city_arraow.setImageResource(R.mipmap.senior_arraow_down);
//                    city_check = false;
//                    history.setVisibility(View.VISIBLE);
//                    select.setVisibility(View.GONE);
//                    if (adapter2 != null) {
//                        adapter2.clear();
//                    }
//                } else {
//                    city.setTextColor(getResources().getColor(R.color.text_check));
//                    city_arraow.setImageResource(R.mipmap.senior_arraow_up);
//                    capital.setTextColor(getResources().getColor(R.color.text_nocheck));
//                    capital_arraow.setImageResource(R.mipmap.senior_arraow_down);
//                    time.setTextColor(getResources().getColor(R.color.text_nocheck));
//                    time_arraow.setImageResource(R.mipmap.senior_arraow_down);
//                    industry.setTextColor(getResources().getColor(R.color.text_nocheck));
//                    industry_arraow.setImageResource(R.mipmap.senior_arraow_down);
//                    city_check = true;
//                    history.setVisibility(View.GONE);
//                    select.setVisibility(View.VISIBLE);
//                }
//                break;
//            case R.id.capital:
//                if (capital_check) {
//                    capital.setTextColor(getResources().getColor(R.color.text_nocheck));
//                    capital_arraow.setImageResource(R.mipmap.senior_arraow_down);
//                    capital_check = false;
//                } else {
//                    city.setTextColor(getResources().getColor(R.color.text_nocheck));
//                    city_arraow.setImageResource(R.mipmap.senior_arraow_down);
//                    capital.setTextColor(getResources().getColor(R.color.text_check));
//                    capital_arraow.setImageResource(R.mipmap.senior_arraow_up);
//                    time.setTextColor(getResources().getColor(R.color.text_nocheck));
//                    time_arraow.setImageResource(R.mipmap.senior_arraow_down);
//                    industry.setTextColor(getResources().getColor(R.color.text_nocheck));
//                    industry_arraow.setImageResource(R.mipmap.senior_arraow_down);
//                    select.setVisibility(View.GONE);
//                    history.setVisibility(View.VISIBLE);
//                    popDataList = new ArrayList<>();
//                    popDataList.add("不限注册");
//                    popDataList.add("100万以内");
//                    popDataList.add("100万到200万");
//                    popDataList.add("200万到500万");
//                    popDataList.add("500万到1000万");
//                    popDataList.add("1000万以上");
//                    popupwindow = new CustomPopupwindow(this, popDataList);
//                    popupwindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//                    popupwindow.showAtDropDownLeft(v);
//                    city_check = false;
//                    industry_check = false;
//                    capital_check = true;
//                    time_check = false;
//                }
//                break;
//            case R.id.time:
//                if (time_check) {
//                    time.setTextColor(getResources().getColor(R.color.text_nocheck));
//                    time_arraow.setImageResource(R.mipmap.senior_arraow_down);
//                    time_check = false;
//                } else {
//                    city.setTextColor(getResources().getColor(R.color.text_nocheck));
//                    city_arraow.setImageResource(R.mipmap.senior_arraow_down);
//                    capital.setTextColor(getResources().getColor(R.color.text_nocheck));
//                    capital_arraow.setImageResource(R.mipmap.senior_arraow_down);
//                    time.setTextColor(getResources().getColor(R.color.text_check));
//                    time_arraow.setImageResource(R.mipmap.senior_arraow_up);
//                    industry.setTextColor(getResources().getColor(R.color.text_nocheck));
//                    industry_arraow.setImageResource(R.mipmap.senior_arraow_down);
//                    select.setVisibility(View.GONE);
//                    history.setVisibility(View.VISIBLE);
//                    popDataList = new ArrayList<>();
//                    popDataList.add("不限年限");
//                    popDataList.add("1年内");
//                    popDataList.add("1-2年");
//                    popDataList.add("2-3年");
//                    popDataList.add("3-5年");
//                    popDataList.add("5-10年");
//                    popDataList.add("10年以上");
//                    popupwindow = new CustomPopupwindow(this, popDataList);
//                    popupwindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//                    popupwindow.showAtDropDownLeft(v);
//                    time_check = true;
//                    city_check = false;
//                    industry_check = false;
//                    capital_check = false;
//                }
//                break;
//            case R.id.industry:
//                if (industry_check) {
//                    industry.setTextColor(getResources().getColor(R.color.text_nocheck));
//                    industry_arraow.setImageResource(R.mipmap.senior_arraow_down);
//                    industry_check = false;
//                } else {
//                    city.setTextColor(getResources().getColor(R.color.text_nocheck));
//                    city_arraow.setImageResource(R.mipmap.senior_arraow_down);
//                    capital.setTextColor(getResources().getColor(R.color.text_nocheck));
//                    capital_arraow.setImageResource(R.mipmap.senior_arraow_down);
//                    time.setTextColor(getResources().getColor(R.color.text_nocheck));
//                    time_arraow.setImageResource(R.mipmap.senior_arraow_down);
//                    industry.setTextColor(getResources().getColor(R.color.text_check));
//                    industry_arraow.setImageResource(R.mipmap.senior_arraow_up);
//                    industry_check = true;
//                }
//                break;

            case R.id.search_bt://搜索按钮
                saveSearchHistory();
                mSearchAutoAdapter.initSearchHistory();
                // http://app.qichacha.com/enterprises/new/advancedSearch
                // ?industryCode=
                // &startDateBegin=
                // &registCapiBegin=
                // &pageIndex=1
                // &registCapiEnd=
                // &province=
                // &searchIndex=default
                // &deviceType=iOS
                // &cityCode=
                // &sortField=Default
                // &isSortAsc=false
                // &token=836bdb71843f45ec29e8c1f9e6165305
                // &deviceId=8F39D131-A0D5-457F-AAC1-062765847E73
                // &searchKey=智容
                // &subIndustryCode=
                // &user=
                // &startDateEnd=
                // &pageSize=40
                GsonUtil request = new GsonUtil(URLconstant.SEARCHURL, RequestMethod.GET);
                request.add("searchKey", searchEt.getText().toString());
                request.add("industryCode", "");
                request.add("startDateBegin", "");
                request.add("registCapiBegin", "");
                request.add("pageIndex", 1);
                request.add("registCapiEnd", "");
                request.add("province", "");
                request.add("searchIndex", "default");
                request.add("deviceType", "IOS");
                request.add("cityCode", "");
                request.add("sortField", "Default");
                request.add("isSortAsc", "false");
                request.add("token", "836bdb71843f45ec29e8c1f9e6165305");
                request.add("deviceId", "8F39D131-A0D5-457F-AAC1-062765847E73");
                request.add("subIndustryCode", "");
                request.add("user", "");
                request.add("startDateEnd", "");
                request.add("pageSize", 40);
                CallServer.getInstance().add(this, request, MyhttpCallBack.getInstance(), NOHTTP_SEARCH, true, false, true);
                break;
            case R.id.auto_add:
                SearchAutoData data = (SearchAutoData) v.getTag();
                searchEt.setText(data.getContent());
                break;
            case R.id.selectCity:
                if (select.getVisibility() != View.VISIBLE) {
                    select.setVisibility(View.VISIBLE);
                    history.setVisibility(View.GONE);
                } else {
                    select.setVisibility(View.GONE);
                    history.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.arrow_back:
                finish();
                overridePendingTransition(R.anim.finish_tran_one,R.anim.finish_tran_two);
                break;
            case R.id.tab_frim:
               if(!tab_frim_check) {
                   tab_frim.setTextColor(getResources().getColor(R.color.black));
                   tab_illegal.setTextColor(getResources().getColor(R.color.white));
                   tab_shareholder.setTextColor(getResources().getColor(R.color.white));
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                       tab_frim.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_selected));
                       tab_illegal.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_unselected));
                       tab_shareholder.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_unselected));
                   }
                   tab_frim_check=true;
                   tab_illegal_check=false;
                   tab_shareholder_check=false;
               }
                break;
            case R.id.tab_illegal:
                if(!tab_illegal_check) {
                    tab_frim.setTextColor(getResources().getColor(R.color.white));
                    tab_illegal.setTextColor(getResources().getColor(R.color.black));
                    tab_shareholder.setTextColor(getResources().getColor(R.color.white));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        tab_frim.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_unselected));
                        tab_illegal.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_selected));
                        tab_shareholder.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_unselected));
                    }
                    tab_frim_check=false;
                    tab_illegal_check=true;
                    tab_shareholder_check=false;
                }
                break;
            case R.id.tab_shareholder:
                if(!tab_shareholder_check) {
                    tab_frim.setTextColor(getResources().getColor(R.color.white));
                    tab_illegal.setTextColor(getResources().getColor(R.color.white));
                    tab_shareholder.setTextColor(getResources().getColor(R.color.black));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        tab_frim.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_unselected));
                        tab_illegal.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_unselected));
                        tab_shareholder.setBackground(getResources().getDrawable(R.drawable.tebhost_bg_selected));
                    }
                    tab_frim_check=false;
                    tab_illegal_check=false;
                    tab_shareholder_check=true;
                }
                break;

            default:
                break;
        }
    }


    private void init() {
        mSearchAutoAdapter = new SearchAutoAdapter(this, -1, this);
        mAutoListView = (ListView) findViewById(R.id.auto_listview);
        mAutoListView.setAdapter(mSearchAutoAdapter);
        mAutoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long arg3) {
                SearchAutoData data = (SearchAutoData) mSearchAutoAdapter.getItem(position);
                searchEt.setText(data.getContent());
                search_bt.performClick();
            }
        });

        search_bt = (ImageView) findViewById(R.id.search_bt);
        search_bt.setOnClickListener(this);
        searchEt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                mSearchAutoAdapter.performFiltering(s);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {//获得焦点
                    mAutoListView.setVisibility(View.VISIBLE);
                } else {//失去焦点
                    mAutoListView.setVisibility(View.GONE);
                }
            }
        });

        history.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                history.setFocusable(true);
                history.setFocusableInTouchMode(true);
                history.requestFocus();

                return false;
            }
        });
    }


    /**
     * 保存搜索记录
     */
    private void saveSearchHistory() {
        String text = searchEt.getText().toString().trim();
        if (text.length() < 1) {
            return;
        }
        sp = getSharedPreferences(SEARCH_HISTORY, 0);
        String longhistory = sp.getString(SEARCH_HISTORY, "");
        String[] tmpHistory = longhistory.split(",");
        ArrayList<String> history = new ArrayList<String>(
                Arrays.asList(tmpHistory));
        if (history.size() > 0) {
            int i;
            for (i = 0; i < history.size(); i++) {
                if (text.equals(history.get(i))) {
                    history.remove(i);
                    break;
                }
            }
            history.add(0, text);
        }
        if (history.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < history.size(); i++) {
                sb.append(history.get(i) + ",");
            }
            sp.edit().putString(SEARCH_HISTORY, sb.toString()).commit();
        } else {
            sp.edit().putString(SEARCH_HISTORY, text + ",").commit();
        }
    }



}
