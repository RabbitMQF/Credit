package com.example.credit.Activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

    String KeyNo,pripid,URL,priptype,regno;
WaitDialog wd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5_view);
        ViewUtils.inject(this);
        wd=new WaitDialog(this);
        Intent i=getIntent();
        KeyNo=i.getStringExtra("KeyNo");
        pripid=i.getStringExtra("pripid");
        URL=i.getStringExtra("URL");
        regno=i.getStringExtra("regno");
        priptype=i.getStringExtra("priptype");
        b_topname.setText(KeyNo);
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webinit();
    }
    public  void webinit(){
        wd.show();
        String str=URL+"?KeyNo="+KeyNo+"&pripid="+pripid+"&regno="+regno+"&priptype="+priptype;
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
        Hwebv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        Hwebv.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//网页缓存
    }
}
