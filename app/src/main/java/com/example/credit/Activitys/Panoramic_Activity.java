package com.example.credit.Activitys;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.R;
import com.example.credit.Utils.MD5;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by alucard on 2016-07-15.
 * 全景视图
 */

public class Panoramic_Activity extends BaseActivity {
    //@ViewInject(R.id.fullview)
    WebView fullview;
    //@ViewInject(R.id.b_return)
    LinearLayout b_return;//退出
    // @ViewInject(R.id.b_topname)
    TextView b_topname;//顶栏
    String url = "http://101.201.211.27:8282/zhirong.credith5/overallView/overallView.do?";
    String KeyNo, deviceId, entname, priptype, regno,devicetype,token;
   LinearLayout web_error;
    WaitDialog wd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panoramic);
        wd=new WaitDialog(this);
        Intent in = getIntent();
        KeyNo = "KeyNo="+in.getStringExtra("KeyNo")+"&";
        deviceId = "deviceId="+in.getStringExtra("deviceId")+"&";
        priptype ="priptype=" +in.getStringExtra("priptype")+"&";
        regno = "regno="+in.getStringExtra("regnore")+"&";
        entname = "entname="+in.getStringExtra("entname")+"&";
        token="token="+ MD5.MD5s(in.getStringExtra("KeyNo")+in.getStringExtra("deviceId"))+"&";
        devicetype="devicetype=1";

        init();
    }

    private void init() {
        wd.show();
        fullview = (WebView) findViewById(R.id.fullview);
        b_return = (LinearLayout) findViewById(R.id.b_return);
        b_topname = (TextView) findViewById(R.id.b_topname);
        web_error= (LinearLayout) findViewById(R.id.web_error);
        b_topname.setText("全景视图");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        WebSettings ws = fullview.getSettings();//网页设置
        //设置 缓存模式
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        ws.setDomStorageEnabled(true);
        ws.setJavaScriptEnabled(true);
        ws.setRenderPriority(WebSettings.RenderPriority.HIGH);//提高渲染优先级
        //ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        ws.setUseWideViewPort(true);//自适应
        ws.setLoadWithOverviewMode(true);//自适应
        fullview.loadUrl(url+KeyNo+deviceId+priptype+regno+entname+token+devicetype);
        fullview.setWebViewClient( new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                wd.dismiss();
            }


            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                 fullview.setVisibility(View.GONE);
                 web_error.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);

            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                fullview.setVisibility(View.GONE);
                web_error.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                fullview.setVisibility(View.GONE);
                web_error.setVisibility(View.VISIBLE);
            }
        });
    }
}
