package com.example.credit.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;


/**
 * 我的投诉
 */
public class MycomplaintsListActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.complain_lv)
    ListView complain_lv;
    @ViewInject(R.id.b_topY)
    TextView b_topY;
    ComplainListAdapter Cadapter;
    public static Handler handler;
    public static ProgressDialog pd;
    CreditSharePreferences csp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycomplaints_list);
        csp = CreditSharePreferences.getLifeSharedPreferences();
        ViewUtils.inject(this);
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
                        Cadapter.setDataList(DataManager.myComplaint.data.commentList);
                        Cadapter.notifyDataSetChanged();
                        pd.dismiss();
                        Toast.show("取消成功");
                        break;
                    case 3://点击item获取详情数据结束后跳转详情页
                        pd.dismiss();
                        startActivity(new Intent(MycomplaintsListActivity.this, ComplaintDetailsActivity.class));
                        break;
                    case 4://提交投诉后重新请求获取企业投诉数据源
                       pd.show();
                        GsonUtil ComplaintsRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.GETCOMPLAIN, RequestMethod.GET);
                        ComplaintsRuerst.add("token", MD5.MD5s("" + new Build().MODEL));//csp.getID()
                        ComplaintsRuerst.add("KeyNo","");//csp.getID()
                        ComplaintsRuerst.add("deviceId", new Build().MODEL);
                        ComplaintsRuerst.add("enterId",DataManager.BaseinfoList.get(0).EnterAddtionID);
                        CallServer.getInstance().add(MycomplaintsListActivity.this,ComplaintsRuerst, MyhttpCallBack.getInstance(),0x991,true,false,true);
                        break;
                    case 5://提交投诉后数据源更新后刷新UI
                        Cadapter.setDataList(DataManager.myComplaint.data.commentList);
                        Cadapter.notifyDataSetChanged();
                        pd.dismiss();
                        break;
                    default:
                        break;
                }
            }
        };

    }

    public void init() {
        if (DataManager.myComplaint.data != null && !DataManager.myComplaint.data.equals(null)) {
            Cadapter = new ComplainListAdapter(this);
            Cadapter.setDataList(DataManager.myComplaint.data.commentList);

        Intent i = getIntent();
        if (i.getIntExtra("key", 0) == 1) {
            b_topY.setText("提交投诉");
            b_topY.setVisibility(View.VISIBLE);
            Cadapter.setTag();

        }
        complain_lv.setAdapter(Cadapter);
        Cadapter.notifyDataSetChanged();
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
                startActivity(new Intent(MycomplaintsListActivity.this, ToComplaintActivity.class));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Cadapter.setDataList(DataManager.myComplaint.data.commentList);
        Cadapter.notifyDataSetChanged();
        pd.dismiss();

    }
}
