package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.credit.Adapters.CC_List_itemAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.PullToRefreshView;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;


/**
 * 专利信息主界面
 */
public class PatentActivity extends BaseActivity implements  PullToRefreshView.OnHeaderRefreshListener,PullToRefreshView.OnFooterRefreshListener{
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;

    @ViewInject(R.id.pa_sc)
    ScrollView pa_sc;
    @ViewInject(R.id.paListView1)
    MyListView paListView1;
    @ViewInject(R.id.pull_refresh_view250)
    PullToRefreshView mPullToRefreshView;
    private int size;
    private boolean falg=false;
    private int por;
    public static Handler handler;
    public static List<DataManager.PatentInfo.DataBean.PatentInfoBean> listPt = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patent);
        ViewUtils.inject(this);
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        size = DataManager.PatentInfoS.data.Paging.CurrentPage;
        pa_sc.smoothScrollTo(0,20);
        Intent i=getIntent();
        String Tname=i.getStringExtra("Tname");
        b_topname.setText(Tname);
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                List<String> list = new ArrayList<>();
                List<String> listurl = new ArrayList<>();
                if(falg==true){
                    por=listPt.size()-1;
                    listPt.addAll(DataManager.PatentInfoS.data.patentInfo);
                    size++;
                }else{
                    listPt=DataManager.PatentInfoS.data.patentInfo;
                }
                for(DataManager.PatentInfo.DataBean.PatentInfoBean p:listPt){
                    list.add(p.PATENTNAME);
                    listurl.add(p.ABSTRACTGRAPH);
                }
                CC_List_itemAdapter adapter = new CC_List_itemAdapter(PatentActivity.this, list, "patent",listurl);
                paListView1.setAdapter(adapter);
                paListView1.setSelection(por-2);
                mPullToRefreshView.onFooterRefreshComplete();
            }
        };
        //初始化
        PatentActivity.handler.sendEmptyMessage(0);
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

                if (DataManager.PatentInfoS.data.Paging.TotalPage>size){
                    falg = true;
                    GsonUtil request10 = new GsonUtil(URLconstant.URLINSER + URLconstant.PATENTURL, RequestMethod.GET);
                    request10.add("token", SearchFirmActivty.MD5s(DataManager.BaseinfoList.get(0).PRIPID +  new Build().MODEL));
                    request10.add("deviceId", new Build().MODEL);
                    request10.add("KeyNo", DataManager.BaseinfoList.get(0).PRIPID);
                    request10.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                    request10.add("pageIndex",size+1 );
                    CallServer.getInstance().add(PatentActivity.this, request10, MyhttpCallBack.getInstance(), 0x01012, true, false, true);
                }else {
                    mPullToRefreshView.onFooterRefreshComplete();
                    Toast.show("没有数据了!");
                }
            }
        },1000);

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
