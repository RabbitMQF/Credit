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
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
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

    @ViewInject(R.id.Myclaim_list)
    ListView Myclaim_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_claim);
        ViewUtils.inject(this);
        init();
    }
    public void init(){
        b_topname.setText("我的认领");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        MyClaim_listAdapter adapter=new MyClaim_listAdapter(MyClaimActivity.this, DataManager.MyClaimUtilsModel.data.Claimlist);
        Myclaim_list.setAdapter(adapter);
//        Myclaim_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                GsonUtil MyClaimRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.MYCLAIMURL, RequestMethod.GET);
//                MyClaimRuerst.add("deviceId",(new Build()).MODEL);
//                MyClaimRuerst.add("token",SearchFirmActivty.MD5s("86D9D7F53FCA45DD93E2D83DFCA0CB42" + (new Build()).MODEL));
//                MyClaimRuerst.add("KeyNo","86D9D7F53FCA45DD93E2D83DFCA0CB42");
//                MyClaimRuerst.add("claimId",DataManager.MyClaimUtilsModel.data.Claimlist.get(position).CLAIMID);
//                CallServer.getInstance().add(MyClaimActivity.this,MyClaimRuerst, MyhttpCallBack.getInstance(),0x304,true,false,true);
//            }
//        });
    }
}
