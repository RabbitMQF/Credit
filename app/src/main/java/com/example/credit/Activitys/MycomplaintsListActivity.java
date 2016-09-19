package com.example.credit.Activitys;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.Adapters.ComplainListAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
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
 * 我的投诉
 */
public class MycomplaintsListActivity extends BaseActivity implements  PullToRefreshView.OnHeaderRefreshListener,PullToRefreshView.OnFooterRefreshListener {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.complain_lv)
    ListView complain_lv;
    @ViewInject(R.id.b_topY)
    TextView b_topY;
    @ViewInject(R.id.Null)
    LinearLayout Null;//空
    public static List<DataManager.MyComplaint.DataBean.CommentListBean> listmycp = new ArrayList<>();
    int falgg = 0;
    @ViewInject(R.id.pull_refresh_view450)
    PullToRefreshView mPullToRefreshView;

    ComplainListAdapter Cadapter;
    public static Handler handler;
    public static ProgressDialog pd;
    CreditSharePreferences csp;
    AlertDialog.Builder builder;
    public static AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycomplaints_list);
        ViewUtils.inject(this);
        Intent intent = getIntent();
        falgg = intent.getIntExtra("falgg",0);
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        csp = CreditSharePreferences.getLifeSharedPreferences();
        init();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1://调用主页面重新请求更新数据源
                        MainActivity.getComplaint(MycomplaintsListActivity.this);
                        break;
                    case 2://取消投诉后数据源更新后更新UI
                        listmycp.addAll(DataManager.myComplaint.data.commentList);
                        Cadapter.setDataList(listmycp);
                        Cadapter.notifyDataSetChanged();
                        pd.dismiss();
                        Toast.show("取消成功");
                        break;
                    case 21://个人投诉加载数据源更新后更新UI
                        listmycp.addAll(DataManager.myComplaint.data.commentList);
                        Cadapter.setDataList(listmycp);
                        Cadapter.notifyDataSetChanged();
                        pd.dismiss();
                        mPullToRefreshView.onFooterRefreshComplete();
                        break;
                    case 3://点击item获取详情数据结束后跳转详情页
                        startActivity(new Intent(MycomplaintsListActivity.this, ComplaintDetailsActivity.class));
                    break;
                    case 4://提交投诉后重新请求获取企业投诉数据源
                        pd.show();
                        GsonUtil ComplaintsRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.GETCOMPLAIN, RequestMethod.GET);
                        ComplaintsRuerst.add("token", MD5.MD5s("" + new Build().MODEL));//csp.getID()
                        ComplaintsRuerst.add("KeyNo", "");//csp.getID()
                        ComplaintsRuerst.add("deviceId", new Build().MODEL);
                        ComplaintsRuerst.add("enterId", DataManager.allcountsList.get(0).EnterAddtionID);
                        CallServer.getInstance().add(MycomplaintsListActivity.this, ComplaintsRuerst, MyhttpCallBack.getInstance(), 0x991, true, false, true);
                        break;
                    case 5://提交投诉后数据源更新后刷新UI
                        Cadapter.setDataList(listmycp);
                        Cadapter.notifyDataSetChanged();
                        pd.dismiss();
                        break;
                    case 6://提交投诉后数据源加载后刷新UI
                        listmycp.addAll(DataManager.myComplaint.data.commentList);
                        Cadapter.setDataList(listmycp);
                        Cadapter.notifyDataSetChanged();
                        pd.dismiss();
                        mPullToRefreshView.onFooterRefreshComplete();
                        break;
                    default:
                        break;
                }
            }
        };

    }

    public void init() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("是否登录");
        builder.setMessage("请先登录账号!");
        builder.setPositiveButton("去登陆", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(MycomplaintsListActivity.this,LoginActivity.class));
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
        listmycp = DataManager.myComplaint.data.commentList;
        Cadapter = new ComplainListAdapter(MycomplaintsListActivity.this,listmycp);
        Intent i = getIntent();
        if (i.getIntExtra("key", 0) == 1) {
            b_topY.setText("我要投诉");
            b_topY.setTextSize(16);
            b_topY.setVisibility(View.VISIBLE);
            Cadapter.setTag();

        }
        if (DataManager.myComplaint.data != null && !DataManager.myComplaint.data.equals(null) && DataManager.myComplaint.data.commentList.size() != 0) {

            Cadapter.setDataList(listmycp);


            complain_lv.setAdapter(Cadapter);
            Cadapter.notifyDataSetChanged();
        } else {
            mPullToRefreshView.setVisibility(View.GONE);
            complain_lv.setVisibility(View.GONE);
            Null.setVisibility(View.VISIBLE);
        }
        b_topname.setText("我的投诉");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pd = new ProgressDialog(this);
        pd.setMessage("请稍后...");
        pd.setCancelable(false);


        b_topY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!csp.getLoginStatus()) {//判定是否登录
                    //Toast.show("请先登录账号");
                    dialog.show();
                } else {
                    startActivity(new Intent(MycomplaintsListActivity.this, ToComplaintActivity.class));
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dialog!=null){
            dialog.dismiss();

        }
        if(pd!=null){
            pd.dismiss();
        }

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
                listmycp.addAll(DataManager.myComplaint.data.commentList);
                Cadapter.setDataList(listmycp);
                Cadapter.notifyDataSetChanged();
            }
        },1000);
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
                if(DataManager.myComplaint.data.Paging.TotalPage>DataManager.myComplaint.data.Paging.CurrentPage){
                    GsonUtil ComplaintsRuerst ;
                        if (falgg==1){//我的投诉
                            ComplaintsRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.GETCOMPLAIN, RequestMethod.GET);
                            ComplaintsRuerst.add("token", MD5.MD5s(csp.getID() + new Build().MODEL));//csp.getID()
                            ComplaintsRuerst.add("KeyNo", csp.getID());//csp.getID()
                            ComplaintsRuerst.add("deviceId", new Build().MODEL);
                            ComplaintsRuerst.add("pageIndex", DataManager.myComplaint.data.Paging.CurrentPage+1);
                            CallServer.getInstance().add(MycomplaintsListActivity.this, ComplaintsRuerst, MyhttpCallBack.getInstance(), 0x9971, true, false, true);
                        }
                         else {//企业投诉
                            ComplaintsRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.GETCOMPLAIN, RequestMethod.GET);
                            ComplaintsRuerst.add("token", MD5.MD5s("" + new Build().MODEL));//csp.getID()
                            ComplaintsRuerst.add("KeyNo", "");//csp.getID()
                            ComplaintsRuerst.add("enterId",     DataManager.allcountsList.get(0).EnterAddtionID);
                            ComplaintsRuerst.add("deviceId", new Build().MODEL);
                            ComplaintsRuerst.add("pageIndex", DataManager.myComplaint.data.Paging.CurrentPage+1);
                            CallServer.getInstance().add(MycomplaintsListActivity.this, ComplaintsRuerst, MyhttpCallBack.getInstance(), 0x9941, true, false, true);
                             }


                }else {
                    mPullToRefreshView.onFooterRefreshComplete();
                    Toast.show("没有数据了!");
                }
            }

        },1000);
    }


    /*@Override
    protected void onRestart() {
        super.onRestart();

            Cadapter.setDataList(DataManager.myComplaint.data.commentList);
            Cadapter.notifyDataSetChanged();
            init();
            pd.dismiss();

    }*/
}
