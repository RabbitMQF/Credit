package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.credit.Adapters.Main_CC_List_itemAdapter;
import com.example.credit.Adapters.NewClaimListAdapter;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面最新认领的  more 界面
 */
public class Main_NewCliam_MoreListActivity extends BaseActivity {
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;

    public static List<DataManager.MyClaimUtils.DataBean.ClaimlistBean> MyCliamListMore = new ArrayList<DataManager.MyClaimUtils.DataBean.ClaimlistBean>();//初始最新认领集合

    @ViewInject(R.id.tListViewNC)
    ListView tListViewNC;
    NewClaimListAdapter adapter;
    CreditSharePreferences csp;
    public static boolean falg=false;
   public static WaitDialog ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcliam_more);
        ViewUtils.inject(this);
        csp=CreditSharePreferences.getLifeSharedPreferences();
        ad=new WaitDialog(this);
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
        adapter = new NewClaimListAdapter(Main_NewCliam_MoreListActivity.this, MyCliamListMore,1);
        tListViewNC.setAdapter(adapter);
        tListViewNC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (csp.getLoginStatus()) {
                    ad.show();
                    falg=true;
                    String KeyNo = DataManager.MyClaimUtilsModel.data.Claimlist.get(position).PRIPID;//市场主体身份代码
                    String token = SearchFirmActivty.MD5s(KeyNo + (new Build()).MODEL);
                    GsonUtil requst = new GsonUtil(URLconstant.URLINSER + URLconstant.GETITEMNUM, RequestMethod.GET);
                    requst.add("KeyNo", KeyNo);
                    requst.add("token", token);
                    requst.add("deviceId", (new Build()).MODEL);
                    requst.add("memberId", csp.getID());
                    requst.add("regnore",MyCliamListMore.get(position).REGNORE);
                    requst.add("priptype", MyCliamListMore.get(position).ENTTYPE);
                    CallServer.getInstance().add(Main_NewCliam_MoreListActivity.this, requst, MyhttpCallBack.getInstance(), 0x026, true, false, true);
                } else {
                    Toast.makeText(Main_NewCliam_MoreListActivity.this, "请先登录!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
