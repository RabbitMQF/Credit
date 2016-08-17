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

import com.example.credit.Adapters.MyClaim_listAdapter;
import com.example.credit.Dialogs.WaitDialog;
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
 * 我的认领界面
 */
public class MyClaimActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.Null)
    LinearLayout Null;
    @ViewInject(R.id.Myclaim_list)
    ListView Myclaim_list;
    public static Handler handler;
    MyClaim_listAdapter adapter;
    public static WaitDialog wd;
    CreditSharePreferences csp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_claim);
        ViewUtils.inject(this);
        csp = CreditSharePreferences.getLifeSharedPreferences();
        wd = new WaitDialog(this);
        init();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1://重新请求获取企业认领数据源
                        UInit();
                        break;
                    case 2://
                        wd.dismiss();
                        adapter.setDataList(DataManager.MyClaimUtilsModel.data.Claimlist);
                        adapter.notifyDataSetChanged();
//                        Toast.show("数据更新成功~！");
                        break;
                    case 6://
                        break;
                    case 500://
                        wd.dismiss();
                        Toast.show("认领取消失败~！");
                        break;
                    default:
                        break;
                }
            }
        };
    }


    public void init() {
        b_topname.setText("我的认领");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GsonUtil NewClaimRequest=new GsonUtil(URLconstant.URLINSER + URLconstant.NEWCLAIM, RequestMethod.GET);//最新认领
                CallServer.getInstance().add(MyClaimActivity.this,NewClaimRequest, MyhttpCallBack.getInstance(),0x113,true,false,true);
                finish();
            }
        });
        if (DataManager.MyClaimUtilsModel.data != null && !DataManager.MyClaimUtilsModel.data.equals(null) && DataManager.MyClaimUtilsModel.data.Claimlist.size() > 0) {
            adapter = new MyClaim_listAdapter(MyClaimActivity.this);
            adapter.setDataList(DataManager.MyClaimUtilsModel.data.Claimlist);
            Myclaim_list.setAdapter(adapter);
        } else {
            Myclaim_list.setVisibility(View.GONE);
            Null.setVisibility(View.VISIBLE);
        }
        MainActivity.ad.dismiss();

//        /**
//         * 认领详情
//         */
//        Myclaim_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                GsonUtil MyClaimRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.MYCLAIMURL, RequestMethod.GET);
//                MyClaimRuerst.add("deviceId",(new Build()).MODEL);
//                MyClaimRuerst.add("token", SearchFirmActivty.MD5s(csp.getID() + (new Build()).MODEL));
//                MyClaimRuerst.add("KeyNo",csp.getID());
//                MyClaimRuerst.add("claimId",DataManager.MyClaimUtilsModel.data.Claimlist.get(position).CLAIMID);
//                CallServer.getInstance().add(MyClaimActivity.this,MyClaimRuerst, MyhttpCallBack.getInstance(),0x304,true,false,true);
//                Intent i=new Intent(MyClaimActivity.this, ClaimDetailsActivity.class);
//                i.putExtra("position",position);
//                startActivity(i);
//            }
//        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        UInit();
    }

    public void UInit() {
        GsonUtil MyClaimRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.MYCLAIMURL, RequestMethod.GET);
        MyClaimRuerst.add("deviceId", (new Build()).MODEL);
        MyClaimRuerst.add("token", SearchFirmActivty.MD5s(csp.getID() + (new Build()).MODEL));
        MyClaimRuerst.add("KeyNo", csp.getID());
        CallServer.getInstance().add(MyClaimActivity.this, MyClaimRuerst, MyhttpCallBack.getInstance(), 0x3031, true, false, true);

    }
}