package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.Adapters.CC_List_itemAdapter;
import com.example.credit.Adapters.Main_CC_List_itemAdapter;
import com.example.credit.Adapters.SearchListAdapter2;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.PullToRefreshView;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面的【专利】，【商标】搜索结果显示界面
 */
public class Main_Search_ListActivity extends BaseActivity implements PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener{
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;

    @ViewInject(R.id.search_list23)
    ListView tListView1;

    @ViewInject(R.id.pull_refresh_view23)
    PullToRefreshView mPullToRefreshView;

    Main_CC_List_itemAdapter adapter;
    String type="";
    String Tname,ETname;
    int TotalPage,CurrentPage;
    public static Handler handler;
    List<String> list = new ArrayList<>();
    public static List<DataManager.sb_search.DataBean.TrademarkBean> listsb = new ArrayList<>();//商标

    public static List<DataManager.zl_search.DataBean.PatentInfoBean> listzl = new ArrayList<>();//专利

    public static List<DataManager.MyDishonesty.DataBean.CourtcaseinfoBean> listsx = new ArrayList<>();//专利

    String syatc="0";
    int por;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__search_list);
        ViewUtils.inject(this);
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        Intent i=getIntent();
        Tname=i.getStringExtra("Tname");
        ETname=i.getStringExtra("ETname");
        TotalPage=i.getIntExtra("TotalPage",0);
        CurrentPage=i.getIntExtra("CurrentPage",0);
        b_topname.setText(Tname+"搜索结果");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                List<String> list = new ArrayList<>();
                List<String> listurl = new ArrayList<>();//商标URL
                switch (msg.what) {
                    case 0://商标
                        if(!syatc.equals("1")){
                            for(DataManager.sb_search.DataBean.TrademarkBean p:listsb){
                                list.add(p.BRANDNAME);
                                if(!p.BRANDIMG.equals("") && p.BRANDIMG!=null){
                                    listurl.add(p.BRANDIMG);
                                }
                            }
                        }else{
                            por=listsb.size()-1;
                            listsb.addAll(DataManager.sb_searchS.data.trademark);
                            for(DataManager.sb_search.DataBean.TrademarkBean p:listsb){
                                list.add(p.BRANDNAME);
                                if(!p.BRANDIMG.equals("") && p.BRANDIMG!=null){
                                    listurl.add(p.BRANDIMG);
                                }
                            }
                            CurrentPage++;
                        }
                        adapter = new Main_CC_List_itemAdapter(Main_Search_ListActivity.this, list,"trademark",listurl);
                        type="trademark";
                        tListView1.setAdapter(adapter);
                        tListView1.setSelection(por-2);
                        break;
                    case 1://专利
                        if(!syatc.equals("1")) {
                            for (DataManager.zl_search.DataBean.PatentInfoBean p : listzl) {
                                list.add(p.PATENTNAME);
                                listurl.add(p.ABSTRACTGRAPH);
                            }
                        }else{
                            por=listzl.size()-1;
                            listzl.addAll(DataManager.zl_searchS.data.patentInfo);
                            for (DataManager.zl_search.DataBean.PatentInfoBean p : listzl) {
                                list.add(p.PATENTNAME);
                                listurl.add(p.ABSTRACTGRAPH);
                            }
                            CurrentPage++;
                        }
                        adapter= new Main_CC_List_itemAdapter(Main_Search_ListActivity.this, list, "patent",listurl);
                        type="patent";
                        tListView1.setAdapter(adapter);
                        tListView1.setSelection(por-2);
                        break;
                    case 2://失信
                        if(!syatc.equals("1")) {
                            for (DataManager.MyDishonesty.DataBean.CourtcaseinfoBean p : listsx) {
                                list.add(p.INAME);
                            }
                        }else{
                            por=listzl.size()-1;
                            listsx.addAll(DataManager.MyDishonestyS.data.Courtcaseinfo);
                            for (DataManager.MyDishonesty.DataBean.CourtcaseinfoBean p : listsx) {
                                list.add(p.INAME);
                            }
                            CurrentPage++;
                        }
                        adapter= new Main_CC_List_itemAdapter(Main_Search_ListActivity.this, list, "Dishonesty",null);
                        type="Dishonesty";
                        tListView1.setAdapter(adapter);
                        tListView1.setSelection(por-2);
                        break;
                }
            }
        };
        switch (Tname){
            case "商标":
                Main_Search_ListActivity.handler.sendEmptyMessage(0);
                break;
            case "专利":
                Main_Search_ListActivity.handler.sendEmptyMessage(1);
                break;
            case "失信":
                Main_Search_ListActivity.handler.sendEmptyMessage(2);
                break;
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
                mPullToRefreshView.onFooterRefreshComplete();
                if(TotalPage>CurrentPage){
                    syatc="1";
                    String urls = "";
                    String nam = "";
                    int num = 0;
                    switch (Tname) {
                        case "商标":
                            urls = URLconstant.URLINSER + URLconstant.SBSREACH;
                            nam = "brandName";
                            num = 0x1003;
                            break;
                        case "专利":
                            urls = URLconstant.URLINSER + URLconstant.ZLSREACH;
                            nam = "patentName";
                            num = 0x1004;
                            break;
                        case "失信":
                            urls = URLconstant.URLINSER + URLconstant.SXDETAILS;
                            num = 0x1006;
                            break;
                    }
                    if (Tname != "招投标") {
                        GsonUtil ComplaintsRuerst = new GsonUtil(urls, RequestMethod.GET);
                        ComplaintsRuerst.add("token", MD5.MD5s("" + new Build().MODEL));
                        ComplaintsRuerst.add("KeyNo", "");
                        ComplaintsRuerst.add("deviceId", new Build().MODEL);
                        if (Tname != "失信"&&!Tname.equals("失信")) {
                            ComplaintsRuerst.add(nam, ETname);
                        }
                        ComplaintsRuerst.add("pageIndex", CurrentPage+1);
                        CallServer.getInstance().add(Main_Search_ListActivity.this, ComplaintsRuerst, MyhttpCallBack.getInstance(), num, true, false, true);
                    }
                }else {
                    Toast.show("没有数据了!");
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
            }
        }, 1000);
    }



}
