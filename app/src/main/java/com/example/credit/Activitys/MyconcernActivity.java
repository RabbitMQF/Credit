package com.example.credit.Activitys;

import android.app.Activity;
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

import com.example.credit.Adapters.MyCommment_listAdapter;
import com.example.credit.Adapters.Myconcer_listAdapter;
import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

/**
 * 我的关注界面
 */
public class MyconcernActivity extends BaseActivity {
    WaitDialog wd;
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.mycconn)
    ListView mycconn;//评论列表
    @ViewInject(R.id.Null)
    LinearLayout Null;
    Myconcer_listAdapter adapter;

    public static Handler handler;

    CreditSharePreferences csp;
    int positions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myconcern);
        ViewUtils.inject(this);
        csp = CreditSharePreferences.getLifeSharedPreferences();
        wd = new WaitDialog(this);
        init();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        wd.dismiss();
                        Intent i = new Intent(MyconcernActivity.this, CompanyDetailsActivity.class);
                        i.putExtra("type", 5);
                        i.putExtra("posit", positions);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 500:
                        wd.dismiss();
                        android.widget.Toast.makeText(MyconcernActivity.this, "暂无数据！", android.widget.Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }

    public void init() {
        b_topname.setText("我的关注");
        b_return.setOnClickListener(listener);
        adapter = new Myconcer_listAdapter(MyconcernActivity.this, DataManager.FavotiteListS.data.AttentionList);
        if ( DataManager.FavotiteListS.data!=null&&!DataManager.FavotiteListS.data.equals(null)&& DataManager.FavotiteListS.data.AttentionList.size()>0) {
            mycconn.setAdapter(adapter);
        } else {
            mycconn.setVisibility(View.GONE);
            Null.setVisibility(View.VISIBLE);
        }
        MainActivity.ad.dismiss();
        mycconn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                wd.show();
                positions = position;
                String KeyNo = DataManager.FavotiteListS.data.AttentionList.get(position).PRIPID;//市场主体身份代码
                String token = SearchFirmActivty.MD5s(KeyNo + (new Build()).MODEL);
                GsonUtil requst = new GsonUtil(URLconstant.URLINSER + URLconstant.GETITEMNUM, RequestMethod.GET);
                requst.add("KeyNo", KeyNo);
                requst.add("token", token);
                requst.add("deviceId", (new Build()).MODEL);
                requst.add("memberId", csp.getID());
                requst.add("regnore", DataManager.FavotiteListS.data.AttentionList.get(position).REGNORE);
                requst.add("priptype", DataManager.FavotiteListS.data.AttentionList.get(position).ENTTYPE);
                CallServer.getInstance().add(MyconcernActivity.this, requst, MyhttpCallBack.getInstance(), 0x025, true, false, true);
            }
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.b_return://返回键
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onRestart() {
        adapter.notifyDataSetChanged();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        adapter.notifyDataSetChanged();
        super.onResume();
    }
}
