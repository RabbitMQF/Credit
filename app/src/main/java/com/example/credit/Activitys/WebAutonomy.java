package com.example.credit.Activitys;

import android.content.Intent;
import android.net.http.SslError;
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
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by alucard on 2016-07-12.
 * 企业年报WEB详情页
 */
public class WebAutonomy extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.web_conten)
    WebView web_conten;
    String KeyNo,devicetype,infoname,infotype;
    String Url废弃="http://101.201.211.27:8282/zhirong.credith5/enterpriseNoticeController/getquerymyselferType.do";
    String Url="http://101.201.211.27:8282/zhirong.credith5/enterpriseNoticeController/getAnnualReportInfo.do";
    WaitDialog wd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_auto);
        ViewUtils.inject(this);
        init();
    }

    private void init() {
        wd=new WaitDialog(this);
        wd.show();
        Intent in=getIntent();
        KeyNo="?KeyNo="+in.getStringExtra("KeyNo");
        infoname="&infoname=企业年报";
        infotype="&infotype="+in.getStringExtra("infotype");
        devicetype="&devicetype=1";
        b_topname.setText("企业年报");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        WebSettings ws = web_conten.getSettings();//网页设置
        //设置 缓存模式
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        ws.setDomStorageEnabled(true);
        ws.setJavaScriptEnabled(true);
        ws.setRenderPriority(WebSettings.RenderPriority.HIGH);//提高渲染优先级
        //ws.setSupportZoom(true);
        //ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        ws.setUseWideViewPort(true);//自适应
        ws.setLoadWithOverviewMode(true);//自适应

        web_conten.loadUrl(Url+KeyNo+devicetype);

        web_conten.setWebViewClient( new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                wd.dismiss();
            }


            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);

            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);

            }
        });

    }
}
