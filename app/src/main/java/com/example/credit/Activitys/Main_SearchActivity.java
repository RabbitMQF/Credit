package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.credit.Dialogs.WaitDialog;
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

public class Main_SearchActivity extends Activity {
    @ViewInject(R.id.search_et)
    EditText search_et;

    @ViewInject(R.id.arrow_backm)
    LinearLayout arrow_backm;
    String hit;
    public static Handler handler;
    WaitDialog wd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__search);
        ViewUtils.inject(this);
        wd=new WaitDialog(this);
        Intent i=getIntent();
        hit=i.getStringExtra("hit");
        search_et.setHint("请输入"+hit+"信息");
        arrow_backm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        search_et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(Main_SearchActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //进行搜索操作的方法，在该方法中可以加入mEditSearchUser的非空判断
                    search();
                }
                return false;
            }
        });
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        wd.dismiss();
                        Toast.show("后续模块正在赶工中...!");
                        break;
                    case 500:
                        wd.dismiss();
                        Toast.show("暂无数据!");
                        break;
                }
            }
        };
    }
    public void search(){
        wd.show();
        String urls="";
        String nam="";
        int num=0;
        switch (hit){
            case "商标":
                urls=URLconstant.URLINSER + URLconstant.SBSREACH;
                nam="brandName";
                num= 0x1001;
                break;
            case "专利":
                urls=URLconstant.URLINSER + URLconstant.ZLSREACH;
                nam="patentName";
                num= 0x1002;
                break;
            case "招投标":
                urls=URLconstant.URLINSER + URLconstant.TBSREACH;
                nam="patentName";
                break;
            case "失信":
                urls=URLconstant.URLINSER + URLconstant.JYSREACH;
                nam="patentName";
                break;
        }
        GsonUtil ComplaintsRuerst = new GsonUtil(urls, RequestMethod.GET);
        ComplaintsRuerst.add("token", MD5.MD5s("" + new Build().MODEL));
        ComplaintsRuerst.add("KeyNo", "");
        ComplaintsRuerst.add("deviceId", new Build().MODEL);
        ComplaintsRuerst.add(nam, search_et.getText().toString());
        CallServer.getInstance().add(Main_SearchActivity.this, ComplaintsRuerst, MyhttpCallBack.getInstance(),num, true, false, true);
    }
}
