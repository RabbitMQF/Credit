package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.Adapters.CC_List_itemAdapter;
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
import java.util.Arrays;
import java.util.List;

/**
 * 商标信息主界面
 */
public class TrademarkActivity extends BaseActivity implements PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener{
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;

    @ViewInject(R.id.tListView1)
    ListView tListView1;
    int size;
    @ViewInject(R.id.pull_refresh_view223)
    PullToRefreshView mPullToRefreshView;
    public static Handler handler;
    boolean falg=false;
    int por;
    public static List<DataManager.trademarkModel.DataBean.TrademarkBean> listTr = new ArrayList<>();//商标
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trademark);
        ViewUtils.inject(this);
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        Intent i=getIntent();
        String Tname=i.getStringExtra("Tname");
        size=DataManager.trademarkModelS.data.Paging.CurrentPage;
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
                List<String> listurl = new ArrayList<>();//商标URL
                if(falg==true){
                    por=listTr.size()-1;
                    listTr.addAll(DataManager.trademarkModelS.data.trademark);
                    size++;
                }else{
                    listTr=DataManager.trademarkModelS.data.trademark;
                }
                for(DataManager.trademarkModel.DataBean.TrademarkBean p:listTr){
                    list.add(p.BRANDNAME);
                    if(!p.BRANDIMG.equals("") && p.BRANDIMG!=null){
                        listurl.add(p.BRANDIMG);
                    }
                }
                CC_List_itemAdapter adapter = new CC_List_itemAdapter(TrademarkActivity.this, list,"trademark",listurl);
                tListView1.setAdapter(adapter);
                tListView1.setSelection(por-2);
                /**
                 * 详情页改H5点击事件写adapter
                 */
//                tListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Intent i = new Intent(TrademarkActivity.this, Public_Detail_ctivity.class);
//                        i.putExtra("position", position);
//                        i.putExtra("state", "trademark");
//                        startActivity(i);
//                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
//                    }
//                });
            }
        };

        //初始化
        TrademarkActivity.handler.sendEmptyMessage(0);
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
                if(DataManager.trademarkModelS.data.Paging.TotalPage>size){
                    falg=true;
                    GsonUtil request11 = new GsonUtil(URLconstant.URLINSER + URLconstant.TRADEMARKURL, RequestMethod.GET);
                    request11.add("token", SearchFirmActivty.MD5s(DataManager.BaseinfoList.get(0).PRIPID +  new Build().MODEL));
                    request11.add("deviceId", new Build().MODEL);
                    request11.add("KeyNo", DataManager.BaseinfoList.get(0).PRIPID);
                    request11.add("priptype", DataManager.BaseinfoList.get(0).ENTTYPE);
                    request11.add("pageIndex",size+1 );
                    CallServer.getInstance().add(TrademarkActivity.this, request11, MyhttpCallBack.getInstance(), 0x01112, true, false, true);
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
