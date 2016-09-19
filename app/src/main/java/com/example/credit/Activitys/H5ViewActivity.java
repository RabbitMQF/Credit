package com.example.credit.Activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class H5ViewActivity extends Activity {
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;

    @ViewInject(R.id.Hwebv)
    WebView Hwebv;

    String KeyNo, pripid, URL, priptype, regno, entname, estdate,msg,Tname,courtcaseid;
    WaitDialog wd;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5_view);
        ViewUtils.inject(this);
        wd = new WaitDialog(this);
        Intent i = getIntent();
        KeyNo = i.getStringExtra("KeyNo");
        pripid = i.getStringExtra("pripid");
        URL = i.getStringExtra("URL");
        courtcaseid=i.getStringExtra("courtcaseid");
        regno = i.getStringExtra("regno");
        priptype = i.getStringExtra("priptype");
        entname = i.getStringExtra("entname");
        estdate = i.getStringExtra("estdate");
        msg=i.getStringExtra("msg");
        Tname = i.getStringExtra("Tname");
        b_topname.setText(Tname);
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Hwebv.canGoBack()) {
                    Hwebv.goBack();// 返回前一个页面
                } else {
                    finish();
                }

            }
        });
        webinit();
    }

    public void webinit() {
        wd.show();
        switch (msg){
            case "1"://投资连图
                str = URL + "?KeyNo=" + KeyNo + "&regno=" + regno + "&entname=" + entname + "&devicetype=1";
                break;
            case "2"://发展历程
                str=URL+"?KeyNo=" + KeyNo+ "&regno=" + regno+"&devicetype=1"+"&priptype="+priptype;
                break;
            case "3"://招投标
                str = URL + "?KeyNo=" + KeyNo + "&pripid=" + pripid + "&regno=" + regno + "&priptype=" + priptype + "&entname=" + entname + "&devicetype=1";
                break;
            case "4"://企业新闻
                str = URL + "?KeyNo=" + KeyNo + "&pripid=" + pripid + "&regno=" + regno + "&priptype=" + priptype + "&entname=" + entname + "&devicetype=1";
                break;
            case "5"://企业招聘
                str = URL + "?KeyNo=" + KeyNo + "&pripid=" + pripid + "&regno=" + regno + "&priptype=" + priptype + "&entname=" + entname + "&devicetype=1";
                break;
            case "6"://企业展示
                b_topname.setText("企业展示");
                str = URL + "?KeyNo=" + KeyNo +  "&regno=" + regno + "&entname=" + entname + "&devicetype=1";
                break;
            case "7"://首页招投标
                b_topname.setText("招投标");
                str=URL+"?token="+"202cb962ac59075b964b07152d234b70"+"&KeyNo="+"&deviceId=123"+"&pageSize=20&pageIndex=0"+"&biddingTitle="+KeyNo+"&devicetype=1";
                break;
            case "8"://首页商标详情
                b_topname.setText("商标详情");
                str=URL+"?brandId="+KeyNo+"&devicetype=1";
                break;
            case"9"://首页专利详情
                b_topname.setText("专利详情");
                str=URL+"?patentId="+KeyNo+"&devicetype=1";
                break;
            case"10"://失信详情
                b_topname.setText("失信详情");
                str = URL + "?KeyNo=" + KeyNo + "&courtcaseId=" + courtcaseid+"&devicetype=1" ;
                break;

            default:
                break;
        }



//        if (pripid != null) {
//            str = URL + "?KeyNo=" + KeyNo + "&pripid=" + pripid + "&regno=" + regno + "&priptype=" + priptype + "&entname=" + entname + "&devicetype=1";
//        }
//        if (estdate != null) {//发展历程
//            b_topname.setText(entname);
//            str=URL+"?KeyNo=" + KeyNo+ "&regno=" + regno+"&entname="+entname+"&estdate="+estdate+"&devicetype=1";
//        } else {//投资连图
//            b_topname.setText(entname);
//            str = URL + "?KeyNo=" + KeyNo + "&regno=" + regno + "&entname=" + entname + "&devicetype=1";
//        }
        WebSettings ws = Hwebv.getSettings();//网页设置
        //设置 缓存模式
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        ws.setDomStorageEnabled(true);
        ws.setJavaScriptEnabled(true);
        ws.setRenderPriority(WebSettings.RenderPriority.HIGH);//提高渲染优先级
        Hwebv.loadUrl(str);

        Hwebv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    wd.dismiss();
                } else {
                    // 加载中
                }
            }
        });

        Hwebv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        Hwebv.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//网页缓存
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && Hwebv.canGoBack()) {
            Hwebv.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
