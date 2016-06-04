package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.credit.Adapters.NewsListAdapter;
import com.example.credit.Contacts.ContactsActivity;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.client.util.URLEncodedUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    private long exitTime = 0;
    private SlidingMenu mLeftMenu;
    private final int NOHTTP_CITY = 0x021;//获取城市
    private final int NOHTTP_INDUSTRY = 0x023;//获取行业
    @ViewInject(R.id.tab1)
    LinearLayout tab1;
    @ViewInject(R.id.tab2)
    LinearLayout tab2;
    RelativeLayout topSearch;
    ListView NewsListview;
    public static Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        initView();
        initData();
        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(MainActivity.this, ContactsActivity.class);
                //startActivity(i);
            }
        });
        GsonUtil request = new GsonUtil(URLconstant.URLINSER+URLconstant.GETCITY, RequestMethod.GET);
        CallServer.getInstance().add(this, request, MyhttpCallBack.getInstance(), NOHTTP_CITY, true, false, true);//获取城市
        CallServer.getInstance().add(this, new GsonUtil(URLconstant.URLINSER+URLconstant.GETINDUSTRY, RequestMethod.GET), MyhttpCallBack.getInstance(), NOHTTP_INDUSTRY, true, false, true);//获取行业
         handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                NewsListAdapter adapter= new NewsListAdapter(MainActivity.this,DataManager.NewssList);
                NewsListview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };
    }

    private void initData() {
        GsonUtil NewsRequest=new GsonUtil(URLconstant.NEWSURL,RequestMethod.GET);
        NewsRequest.setConnectTimeout(20000);
        CallServer.getInstance().add(this,NewsRequest,MyhttpCallBack.getInstance(),0x111,true,false,true);
        /*List<DataManager.News> listData = new ArrayList<>();
        DataManager.News news1= new DataManager.News("","新闻标题1","内容内容内容内容内容内容内容内容内容内容内容内容内容","2016-9-19");
        DataManager.News news2= new DataManager.News("","新闻标题2","内容内容内容内容内容内容内容内容内容内容内容内容内容","2016-9-19");
        DataManager.News news3= new DataManager.News("","新闻标题3","内容内容内容内容内容内容内容内容内容内容内容内容内容","2016-9-19");
        DataManager.News news4= new DataManager.News("","新闻标题4","内容内容内容内容内容内容内容内容内容内容内容内容内容","2016-9-19");
        DataManager.News news5= new DataManager.News("","新闻标题5","内容内容内容内容内容内容内容内容内容内容内容内容内容","2016-9-19");
        DataManager.News news6= new DataManager.News("","新闻标题6","内容内容内容内容内容内容内容内容内容内容内容内容内容","2016-9-19");
        listData.add(news1);
        listData.add(news2);
        listData.add(news3);
        listData.add(news4);
        listData.add(news5);
        listData.add(news6);*/



    }

    private void initView() {
        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
        topSearch = (RelativeLayout) findViewById(R.id.top_search);
        topSearch.setOnClickListener(this);
        NewsListview = (ListView) findViewById(R.id.news_list);
    }

    public void toggleMenu(View view) {
        mLeftMenu.toggle();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_search:
                Intent in = new Intent(this, SearchFirmActivty.class);
                startActivity(in);
                overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                break;
            default:
                break;
        }
    }


    /*@Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
