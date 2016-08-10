package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 主界面的【专利】，【商标】搜索界面
 */
public class Main_SearchActivity extends Activity {
    @ViewInject(R.id.search_et)
    EditText search_et;

    @ViewInject(R.id.arrow_backm)
    LinearLayout arrow_backm;
    String hit;
    public static Handler handler;
    public static WaitDialog wd;
    @ViewInject(R.id.search_bt)
    ImageView search_bt;

    CreditSharePreferences csp=CreditSharePreferences.getLifeSharedPreferences();

    @ViewInject(R.id.his_yout)
    LinearLayout his_yout;
    @ViewInject(R.id.main_s1)
    TextView main_s1;
    @ViewInject(R.id.main_s2)
    TextView main_s2;
    @ViewInject(R.id.main_s3)
    TextView main_s3;
    @ViewInject(R.id.main_s4)
    TextView main_s4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__search2);
        ViewUtils.inject(this);
        wd = new WaitDialog(this);
        Intent i = getIntent();
        hit = i.getStringExtra("hit");
        search_et.setHint("请输入" + hit + "信息");
        arrow_backm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) search_et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(Main_SearchActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //执行方法
                    if (!search_et.getText().toString().equals("")) {
                        search();
                    } else {
                        Toast.show("搜索关键字不能为空!");
                    }

                    return true;
                }
                return false;
            }
        });
        search_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!search_et.getText().toString().equals("")) {
                    search();
                } else {
                    Toast.show("搜索关键字不能为空!");
                }
            }
        });
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Intent i;
                switch (msg.what) {
                    case 0://商标
                        wd.dismiss();
                        Main_Search_ListActivity.listsb=DataManager.sb_searchS.data.trademark;
                        i = new Intent(Main_SearchActivity.this, Main_Search_ListActivity.class);
                        i.putExtra("Tname", "商标");
                        i.putExtra("ETname", search_et.getText().toString());
                        i.putExtra("CurrentPage",DataManager.sb_searchS.data.Paging.CurrentPage);
                        i.putExtra("TotalPage", DataManager.sb_searchS.data.Paging.TotalPage);
                        startActivity(i);
                        break;
                    case 1://专利
                        wd.dismiss();
                        Main_Search_ListActivity.listzl=DataManager.zl_searchS.data.patentInfo;
                        i = new Intent(Main_SearchActivity.this, Main_Search_ListActivity.class);
                        i.putExtra("Tname", "专利");
                        i.putExtra("ETname", search_et.getText().toString());
                        i.putExtra("CurrentPage", DataManager.zl_searchS.data.Paging.CurrentPage);
                        i.putExtra("TotalPage", DataManager.zl_searchS.data.Paging.TotalPage);
                        startActivity(i);
                        break;
                    case 500:
                        wd.dismiss();
                        Toast.show("暂无数据!");
                        break;
                }
            }
        };

        if (csp.getmainHistory() != null && !(csp.getmainHistory()).equals("")) {
            String str = csp.getmainHistory();
            String[] strh = str.split(",");
            List<String> listh = new ArrayList<String>(Arrays.asList(strh));
            if(listh.size()>0 && listh.size()<2){
                main_s1.setText(listh.get(0));
                main_s2.setVisibility(View.GONE);
                main_s3.setVisibility(View.GONE);
                main_s4.setVisibility(View.GONE);
            }else if(listh.size()<3){
                main_s1.setText(listh.get(0));
                main_s2.setText(listh.get(1));
                main_s3.setVisibility(View.GONE);
                main_s4.setVisibility(View.GONE);
            }else if(listh.size()<4){
                main_s1.setText(listh.get(0));
                main_s2.setText(listh.get(1));
                main_s3.setText(listh.get(2));
                main_s4.setVisibility(View.GONE);
            }else if(listh.size()>3){
                main_s1.setText(listh.get(0));
                main_s2.setText(listh.get(1));
                main_s3.setText(listh.get(2));
                main_s4.setText(listh.get(3));
            } else{
                main_s1.setVisibility(View.GONE);
                main_s2.setVisibility(View.GONE);
                main_s3.setVisibility(View.GONE);
                main_s4.setVisibility(View.GONE);
            }

        } else {
            his_yout.setVisibility(View.GONE);
        }
        main_s1.setOnClickListener(listener);
        main_s2.setOnClickListener(listener);
        main_s3.setOnClickListener(listener);
        main_s4.setOnClickListener(listener);

    }
    public void search() {
        hisinit();
        wd.show();
        String urls = "";
        String nam = "";
        int num = 0;
        switch (hit) {
            case "商标":
                urls = URLconstant.URLINSER + URLconstant.SBSREACH;
                nam = "brandName";
                num = 0x1001;
                break;
            case "专利":
                urls = URLconstant.URLINSER + URLconstant.ZLSREACH;
                nam = "patentName";
                num = 0x1002;
                break;
            case "招投标":
//                urls = URLconstant.URLINSER + URLconstant.TBSREACH;
//                nam = "patentName";
                search_et.setHint("请输入招标名称");
                startActivity(new Intent(Main_SearchActivity.this,H5ViewActivity.class).putExtra("msg","7").putExtra("URL",URLconstant.TENDER).putExtra("KeyNo",search_et.getText().toString()));
                overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                wd.dismiss();
                break;
            case "失信":
                urls = URLconstant.URLINSER + URLconstant.JYSREACH;
                nam = "patentName";
                break;
        }
        if (hit != "招投标"&&!hit.equals("招投标")) {
            GsonUtil ComplaintsRuerst = new GsonUtil(urls, RequestMethod.GET);
            ComplaintsRuerst.add("token", MD5.MD5s("" + new Build().MODEL));
            ComplaintsRuerst.add("KeyNo", "");
            ComplaintsRuerst.add("deviceId", new Build().MODEL);
            ComplaintsRuerst.add(nam, search_et.getText().toString());
            CallServer.getInstance().add(Main_SearchActivity.this, ComplaintsRuerst, MyhttpCallBack.getInstance(), num, true, false, true);
        }
    }

    public void hisinit(){
        //历史记录保存本地SP
        String Tnameh = search_et.getText().toString() + ",";//历史字备用
        if (csp.getmainHistory() != null && !(csp.getmainHistory()).equals("")) {
            String str1 = csp.getmainHistory();
            String[] strh = str1.split(",");
            List<String> listh = new ArrayList<String>(Arrays.asList(strh));
            if (listh != null && listh.size() < 4) {
                String temp = "";
                for (int i = 0; i < listh.size(); i++) {
                    if (search_et.getText().toString().equals(listh.get(i))) {
                        temp = listh.get(i);
                    }
                }
                if (temp.equals("")) {
                    csp.putmainHistory(str1 + Tnameh);
                }
            } else {
                String temp = "";
                for (int i = 0; i < listh.size(); i++) {
                    if (search_et.getText().toString().equals(listh.get(i))) {
                        temp = listh.get(i);
                    }
                }
                if (temp.equals("")) {
                    listh.remove(0);
                    String strlists = "";
                    for (int i = 0; i < listh.size(); i++) {
                        strlists = strlists + listh.get(i) + ",";
                    }
                    csp.putmainHistory(strlists + Tnameh);
                }
            }
        } else {
            csp.putmainHistory(Tnameh);
        }
    }
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.main_s1:
                    search_et.setText(main_s1.getText().toString());
                    break;
                case R.id.main_s2:
                    search_et.setText(main_s2.getText().toString());
                    break;
                case R.id.main_s3:
                    search_et.setText(main_s3.getText().toString());
                    break;
                case R.id.main_s4:
                    search_et.setText(main_s4.getText().toString());
                    break;
            }
        }
    };
}
