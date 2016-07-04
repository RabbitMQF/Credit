package com.example.credit.Activitys;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
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

import com.example.credit.Adapters.ComplainListAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.util.List;

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
    ComplainListAdapter Cadapter;
    public static Handler handler;
    public static ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycomplaints_list);
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
                    case 2://数据源更新后更新UI
                        Cadapter.setDataList(DataManager.myComplaint.data.commentList);
                        Cadapter.notifyDataSetChanged();
                        //complain_lv.setAdapter(Cadapter);
                        pd.dismiss();
                        Toast.show("取消成功");
                        break;
                    case 3://点击item获取详情数据结束后跳转详情页
                        pd.dismiss();
                    startActivity(new Intent(MycomplaintsListActivity.this, ComplaintDetailsActivity.class));
                    default:
                        break;
                }
            }
        };

    }

    public void init() {
        Cadapter = new ComplainListAdapter(this);
        Cadapter.setDataList(DataManager.myComplaint.data.commentList);
        complain_lv.setAdapter(Cadapter);
        Cadapter.notifyDataSetChanged();
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


    }
}
