package com.example.credit.Activitys;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;

import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.credit.Adapters.NewClaimListAdapter;
import com.example.credit.Adapters.NewsListAdapter;
import com.example.credit.Adapters.SearchListAdapter2;
import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.PullToRefreshView;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.FileUtil;
import com.example.credit.Views.RoundImageView;
import com.example.credit.Views.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.picasso.Picasso;
import com.yolanda.nohttp.RequestMethod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Decoder.BASE64Decoder;

import static com.example.credit.Views.FileUtil.decodeBitmap;
import static com.example.credit.Views.FileUtil.deleteDir;
//SwipeRefreshLayout.OnRefreshListener
public class MainActivity extends Activity implements View.OnClickListener {
//    ,PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener
    private long exitTime = 0;
    private SlidingMenu mLeftMenu;
    private final int NOHTTP_CITY = 0x021;//获取城市
    private final int NOHTTP_INDUSTRY = 0x023;//获取行业
    @ViewInject(R.id.tab1)
    LinearLayout tab1;//企业查询
    @ViewInject(R.id.tab2)
    LinearLayout tab2;//著作权查询
    @ViewInject(R.id.tab3)
    LinearLayout tab3;//违法查询
    @ViewInject(R.id.tab4)
    LinearLayout tab4;//股东查询
    RelativeLayout topSearch;
    public static Handler handler;

    @ViewInject(R.id.headimg)
    RoundImageView headimg;//我的头像
    @ViewInject(R.id.UserSz)
    public static TextView UserSz;//用户名

    @ViewInject(R.id.set)
    ImageView set;//设置


    @ViewInject(R.id.Smenu_1)
    RelativeLayout Smenu_1;//我的评价
    @ViewInject(R.id.Smenu_2)
    RelativeLayout Smenu_2;//我的投诉
    @ViewInject(R.id.Smenu_3)
    RelativeLayout Smenu_3;//我的关注
    @ViewInject(R.id.Smenu_4)
    RelativeLayout Smenu_4;//我的认领
    @ViewInject(R.id.Smenu_5)
    RelativeLayout Smenu_5;//服务协议
    @ViewInject(R.id.Smenu_6)
    RelativeLayout Smenu_6;//关于我们
    @ViewInject(R.id.login)
    Button login;//登录

    @ViewInject(R.id.btmore)
    Button btmore;//加载更多

    //    @ViewInject(R.id.homeprogress)
//    LinearLayout homeprogress;//数据加载框
    @ViewInject(R.id.news_list)
    ListView NewsListview;

    public static List<DataManager.MyNews.DataBean.NewslistBean> MyNewsList = new ArrayList<DataManager.MyNews.DataBean.NewslistBean>();//初始新闻集合
    static CreditSharePreferences csp;
    Boolean LoginStatus;
    public static ProgressDialog pd;
    public static WaitDialog ad;

    TextView main1, main2;//新闻和最新认领按钮
    //    PullToRefreshView mPullToRefreshView;
    boolean falg = true;
    int t = 2;
    int str = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        csp = CreditSharePreferences.getLifeSharedPreferences();
        LoginStatus = csp.getLoginStatus();
        ViewUtils.inject(this);
        ad = new WaitDialog(this);
        initView();
        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        if (DataManager.citysList.size() == 0 || DataManager.citysList == null) {
            GsonUtil request = new GsonUtil(URLconstant.URLINSER + URLconstant.GETCITY, RequestMethod.GET);
            CallServer.getInstance().add(this, request, MyhttpCallBack.getInstance(), NOHTTP_CITY, true, false, true);//获取城市
        }
        CallServer.getInstance().add(this, new GsonUtil(URLconstant.URLINSER + URLconstant.GETINDUSTRY, RequestMethod.GET), MyhttpCallBack.getInstance(), NOHTTP_INDUSTRY, true, false, true);//获取行业
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        btmore.setVisibility(View.VISIBLE);
                        int por = MyNewsList.size() - 1;
                        NewsListAdapter adapter = new NewsListAdapter(MainActivity.this, MyNewsList);
                        NewsListview.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        if (str == 2) {
                            NewsListview.setSelection(por - 1);
                        }
                        NewsListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent i = new Intent(MainActivity.this, NewsContentActivity.class);
                                i.putExtra("id", MyNewsList.get(position).ID);
                                startActivity(i);
                            }
                        });
                        break;
                    case 1://我的评价
                        Intent i1 = new Intent(MainActivity.this, MyCommentlistActivity.class);
                        startActivity(i1);
                        break;
                    case 2://跳我的投诉
                        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
                        if (!cn.getClassName().equals(MycomplaintsListActivity.class.getName())) {
                            pd.dismiss();
                            startActivity(new Intent(MainActivity.this, MycomplaintsListActivity.class));
                        } else {
                            MycomplaintsListActivity.handler.sendEmptyMessage(2);
                        }
                        break;
                    case 5://跳我的关注
                        Intent i3 = new Intent(MainActivity.this, MyconcernActivity.class);
                        startActivity(i3);
                        break;
                    case 6://跳我的认领
                        Intent i6 = new Intent(MainActivity.this, MyClaimActivity.class);
                        startActivity(i6);
                        break;
                    case 7:
                        NewClaimListAdapter adapter1 = new NewClaimListAdapter(MainActivity.this, DataManager.MyClaimUtilsModel.data.Claimlist);
                        NewsListview.setAdapter(adapter1);
                        adapter1.notifyDataSetChanged();
                        NewsListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                if (csp.getLoginStatus()) {
                                    ad.show();
                                    String KeyNo = DataManager.MyClaimUtilsModel.data.Claimlist.get(position).PRIPID;//市场主体身份代码
                                    String token = SearchFirmActivty.MD5s(KeyNo + (new Build()).MODEL);
                                    GsonUtil requst = new GsonUtil(URLconstant.URLINSER + URLconstant.GETITEMNUM, RequestMethod.GET);
                                    requst.add("KeyNo", KeyNo);
                                    requst.add("token", token);
                                    requst.add("deviceId", (new Build()).MODEL);
                                    requst.add("memberId", csp.getID());
                                    requst.add("regnore", DataManager.MyClaimUtilsModel.data.Claimlist.get(position).REGNORE);
                                    requst.add("priptype", DataManager.MyClaimUtilsModel.data.Claimlist.get(position).ENTTYPE);
                                    CallServer.getInstance().add(MainActivity.this, requst, MyhttpCallBack.getInstance(), 0x026, true, false, true);
                                } else {
                                    Toast.makeText(MainActivity.this, "请先登录!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                    case 8://跳公司详情
                        ad.dismiss();
                        Intent i = new Intent(MainActivity.this, CompanyDetailsActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 9:
                        Intent i2 = new Intent(MainActivity.this, NewsContentActivity.class);
                        startActivity(i2);
                        break;
                    case 101:
                        com.example.credit.Utils.Toast.show("没有数据了!");
                        break;

                    default:
                        break;
                }

            }
        };
        initData();
        NewsListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 当不滚动时
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    // 判断是否滚动到底部
                    btmore.setVisibility(View.VISIBLE);
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        //加载更多功能的代码
                        if (t <= DataManager.MyNewsS.data.Paging.TotalPage) {
                            GsonUtil NewsRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.NEWSURL, RequestMethod.GET);//新闻数据
                            NewsRequest.setConnectTimeout(20000);
                            NewsRequest.setReadTimeout(20000);
                            NewsRequest.add("token", MD5.MD5s("" + new Build().MODEL));
                            NewsRequest.add("KeyNo", "");
                            NewsRequest.add("deviceId", (new Build()).MODEL);

                            NewsRequest.add("pageIndex", t);
                            NewsRequest.add("pageSize", 5);
                            CallServer.getInstance().add(MainActivity.this, NewsRequest, MyhttpCallBack.getInstance(), 0x1111, true, false, true);
                            t++;
                            str = 2;
                        } else {
                            btmore.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });


    }

    private void initData() {
//        try{
//            if (!DataManager.MyNewsS.data.Newslist.equals(null) && DataManager.MyNewsS.data.Newslist != null && DataManager.MyNewsS.data.Newslist.size() > 0 ) {
//                handler.sendEmptyMessage(0);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            com.example.credit.Utils.Toast.show("新闻正在赶来的路上...");
//            mPullToRefreshView.setVisibility(View.GONE);
//        }
        if (MyNewsList != null && MyNewsList.size() > 0) {
            handler.sendEmptyMessage(0);
        } else {//没有数据
//            mPullToRefreshView.setVisibility(View.GONE);
            btmore.setVisibility(View.GONE);
        }

        main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                falg = true;
                main1.setTextColor(getResources().getColor(R.color.white));
                main1.setBackgroundDrawable(getResources().getDrawable(R.drawable.details_gg_bgtit));
                main2.setTextColor(getResources().getColor(R.color.black));
                main2.setBackgroundDrawable(getResources().getDrawable(R.drawable.details_con_tabbg2));
                try {
                    if (MyNewsList != null && MyNewsList.size() > 0) {
                        handler.sendEmptyMessage(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    com.example.credit.Utils.Toast.show("新闻正在赶来的路上...");
//                    mPullToRefreshView.setVisibility(View.GONE);
                }
            }
        });
        main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                falg = false;
                btmore.setVisibility(View.GONE);
                main1.setTextColor(getResources().getColor(R.color.black));
                main1.setBackgroundDrawable(getResources().getDrawable(R.drawable.details_con_tabbg2));
                main2.setTextColor(getResources().getColor(R.color.white));
                main2.setBackgroundDrawable(getResources().getDrawable(R.drawable.details_gg_bgtit));
                try {
                    if (DataManager.MyClaimUtilsModel.data.Claimlist != null && DataManager.MyClaimUtilsModel.data.Claimlist.size() > 0) {
                        handler.sendEmptyMessage(7);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    com.example.credit.Utils.Toast.show("数据正在赶来的路上...");
//                    mPullToRefreshView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initView() {
//        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_refresh_view);
//        mPullToRefreshView.setOnHeaderRefreshListener(this);
//        mPullToRefreshView.setOnFooterRefreshListener(this);
        main1 = (TextView) findViewById(R.id.main1);
        main2 = (TextView) findViewById(R.id.main2);

        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
        topSearch = (RelativeLayout) findViewById(R.id.top_search);
        topSearch.setOnClickListener(this);
        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("请稍后...");
        pd.setCancelable(false);
//        NewsListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                GsonUtil NewsRequest=new GsonUtil(URLconstant.NEWSURL, RequestMethod.POST);//新闻数据
//                NewsRequest.add("KeyNo",DataManager.NewClaimS.data.Newslist.get(position).ID);
//                CallServer.getInstance().add(MainActivity.this,NewsRequest, MyhttpCallBack.getInstance(),0x111,true,false,true);
//            }
//        });
        headimg.setOnClickListener(listener);
        UserSz.setOnClickListener(listener);
        Smenu_1.setOnClickListener(listener);
        Smenu_2.setOnClickListener(listener);
        Smenu_3.setOnClickListener(listener);
        Smenu_4.setOnClickListener(listener);
        Smenu_5.setOnClickListener(listener);
        Smenu_6.setOnClickListener(listener);
        isLogin();
        login.setOnClickListener(listener);
        tab1.setOnClickListener(listener);
        tab2.setOnClickListener(listener);
        tab3.setOnClickListener(listener);
        tab4.setOnClickListener(listener);
        btmore.setOnClickListener(listener);
        set.setOnClickListener(listener);

//        pb_4.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.UserSz://用户
                    // Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    //startActivity(i);
//                    Toa2st.makeText(MainActivity.this, "此模块，正在赶点加工中...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.headimg://我的头像

                    break;
                case R.id.Smenu_1://我的评价
                    if (!csp.getLoginStatus()) {//判定是否登录
                        com.example.credit.Utils.Toast.show("请先登录账号");
                    } else {
                        ad.show();
                        GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.MMOMM, RequestMethod.GET);
                        request14.add("deviceId", (new Build()).MODEL);
                        request14.add("token", SearchFirmActivty.MD5s(csp.getID() + (new Build()).MODEL));
                        request14.add("KeyNo", csp.getID());
                        CallServer.getInstance().add(MainActivity.this, request14, MyhttpCallBack.getInstance(), 0x206, true, false, true);
                    }

//                    Toast.makeText(MainActivity.this, "此模块，正在赶点加工中...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Smenu_2://我的投诉
                    if (!csp.getLoginStatus()) {//判定是否登录
                        com.example.credit.Utils.Toast.show("请先登录账号");
                    } else {
                        pd.show();
                        getComplaint(MainActivity.this);
                    }
//                    Toast.makeText(MainActivity.this, "此模块，正在赶点加工中...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Smenu_3://我的关注
                    if (!csp.getLoginStatus()) {//判定是否登录
                        com.example.credit.Utils.Toast.show("请先登录账号");
                    } else {
                        ad.show();
                        GsonUtil MyconcernRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.MYFAVORITE, RequestMethod.GET);
                        MyconcernRuerst.add("deviceId", (new Build()).MODEL);
                        MyconcernRuerst.add("token", SearchFirmActivty.MD5s(csp.getID() + (new Build()).MODEL));
                        MyconcernRuerst.add("KeyNo", csp.getID());
                        CallServer.getInstance().add(MainActivity.this, MyconcernRuerst, MyhttpCallBack.getInstance(), 0x103, true, false, true);
                    }
//                    Toast.makeText(MainActivity.this, "此模块，正在赶点加工中...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Smenu_4://我的认领
                    if (!csp.getLoginStatus()) {//判定是否登录
                        com.example.credit.Utils.Toast.show("请先登录账号");
                    } else {
                        ad.show();
                        GsonUtil MyClaimRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.MYCLAIMURL, RequestMethod.GET);
                        MyClaimRuerst.add("deviceId", (new Build()).MODEL);
                        MyClaimRuerst.add("token", SearchFirmActivty.MD5s(csp.getID() + (new Build()).MODEL));
                        MyClaimRuerst.add("KeyNo", csp.getID());
                        CallServer.getInstance().add(MainActivity.this, MyClaimRuerst, MyhttpCallBack.getInstance(), 0x303, true, false, true);
                    }
//                    Toast.makeText(MainActivity.this, "此模块，正在赶点加工中...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Smenu_5://服务协议
                    Intent in12 = new Intent(MainActivity.this, AgreementActivity.class);
                    startActivity(in12);
//                    Toast.makeText(MainActivity.this, "此模块，正在赶点加工中...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Smenu_6://关于我们
                    Intent in13 = new Intent(MainActivity.this, AboutActivity.class);
                    startActivity(in13);
//                    Toast.makeText(MainActivity.this, "此模块，正在赶点加工中...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.login://登录
                    if (!LoginStatus) {//如果当前状态未登录  点登录的跳转
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    } else {//如果当前状态已登录  点击退出登录的操作
                        csp.putUser(null);
                        com.example.credit.Utils.Toast.show("退出登录");
                        csp.putLoginStatus(false);
                        isLogin();
                        UserSz.setText("游客");//用户名
                    }
                    break;

                case R.id.tab1://企业查询
                    Intent in1 = new Intent(MainActivity.this, SearchFirmActivty.class);
                    in1.putExtra("type", 0);
                    startActivity(in1);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;
                case R.id.tab2://法人查询
                    Intent in2 = new Intent(MainActivity.this, SearchFirmActivty.class);
                    in2.putExtra("type", 1);
                    startActivity(in2);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;
                case R.id.tab3://失信查询
                    Intent in3 = new Intent(MainActivity.this, SearchFirmActivty.class);
                    in3.putExtra("type", 2);
                    startActivity(in3);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;
                case R.id.tab4://违法信息
                    Intent in4 = new Intent(MainActivity.this, SearchFirmActivty.class);
                    in4.putExtra("type", 3);
                    startActivity(in4);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;

                case R.id.set://个人信息设置
                    if (!csp.getLoginStatus()) {//判定是否登录
                        com.example.credit.Utils.Toast.show("请先登录账号");
                    } else {
//                    com.example.credit.Utils.Toast.show("此模块，正在赶点加工中...");
                        Intent is = new Intent(MainActivity.this, UserSetActivity.class);
                        startActivity(is);
                    }
                    break;
                case R.id.btmore://新闻加载更多
                    if (t <= DataManager.MyNewsS.data.Paging.TotalPage) {
                        GsonUtil NewsRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.NEWSURL, RequestMethod.GET);//新闻数据
                        NewsRequest.setConnectTimeout(20000);
                        NewsRequest.setReadTimeout(20000);
                        NewsRequest.add("token", MD5.MD5s("" + new Build().MODEL));
                        NewsRequest.add("KeyNo", "");
                        NewsRequest.add("deviceId", (new Build()).MODEL);

                        NewsRequest.add("pageIndex", t);
                        NewsRequest.add("pageSize", 5);
                        CallServer.getInstance().add(MainActivity.this, NewsRequest, MyhttpCallBack.getInstance(), 0x1111, true, false, true);
                        t++;
                        str=2;
                    }else{
                        com.example.credit.Utils.Toast.show("没有更多数据了！");
                        btmore.setVisibility(View.GONE);
                    }
                    break;
                default:
                    break;

            }
        }
    };

    /**
     * 获取我的投诉列表方法
     */
    public static void getComplaint(Activity activity) {
        GsonUtil ComplaintsRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.GETCOMPLAIN, RequestMethod.GET);
        ComplaintsRuerst.add("token", MD5.MD5s(csp.getID() + new Build().MODEL));//csp.getID()
        ComplaintsRuerst.add("KeyNo", csp.getID());// b2d794b453664657af61b373c1d00e7c
        ComplaintsRuerst.add("deviceId", new Build().MODEL);
        CallServer.getInstance().add(activity, ComplaintsRuerst, MyhttpCallBack.getInstance(), 0x997, true, false, true);
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

    private void isLogin() {
        LoginStatus = csp.getLoginStatus();
        if (LoginStatus) {//若当前状态为登录
            UserSz.setText(csp.getALIASNAME());
            login.setText("退出登录");
            if (!csp.getICONSTEAM().equals("")) {
                File file = new File(Environment.getExternalStorageDirectory() + "/Credit/loginImg.jpg");
                if (file.exists()) {//获取本地图片路径是否存在
                    headimg.setImageBitmap(decodeBitmap(Environment.getExternalStorageDirectory() + "/Credit/loginImg.jpg", 80, 80));
//                Picasso.with(MainActivity.this).load(file).into(headimg);
                }
            }
        } else {//若当前状态未未登录
            login.setText("登录");
            UserSz.setText("游客");
            headimg.setImageResource(R.mipmap.me_icon02);
        }
    }

    public static void loginImg(String base64) {
        if (base64 != null) {
            try {
                BASE64Decoder decode = new BASE64Decoder();
                byte[] b = decode.decodeBuffer(base64);
                System.out.println(new String(b));
                StringBuilder str = new StringBuilder();//不建议用String
                for (byte bs : b) {
                    str.append(Integer.toBinaryString(bs));//转换为二进制
                }
                //把字节数组的图片写到另一个地方
                File apple = new File(Environment.getExternalStorageDirectory() + "/Credit/loginImg.jpg");
                FileOutputStream fos = new FileOutputStream(apple);
                fos.write(b);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onRestart() {
        isLogin();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isLogin();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                deleteDir(Environment.getExternalStorageDirectory() + "/Credit/cache");//正常退出时，清除缓存
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

//    /**
//     * 上啦加载
//     * @param view
//     */
//    @Override
//    public void onFooterRefresh(PullToRefreshView view) {
//        mPullToRefreshView.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                mPullToRefreshView.onFooterRefreshComplete();
//                if(falg){
//                    if(t<=DataManager.MyNewsS.data.Paging.TotalPage){
//                        GsonUtil NewsRequest=new GsonUtil(URLconstant.URLINSER+URLconstant.NEWSURL, RequestMethod.GET);//新闻数据
//                        NewsRequest.setConnectTimeout(20000);
//                        NewsRequest.setReadTimeout(20000);
//                        NewsRequest.add("token",MD5.MD5s("" + new Build().MODEL));
//                        NewsRequest.add("KeyNo","");
//                        NewsRequest.add("deviceId",(new Build()).MODEL);
//
//                        NewsRequest.add("pageIndex",t);
//                        NewsRequest.add("pageSize",5);
//                        CallServer.getInstance().add(MainActivity.this,NewsRequest, MyhttpCallBack.getInstance(),0x1111,true,false,true);
//                        t++;
//                        str=2;
//                    }else{
//                        com.example.credit.Utils.Toast.show("没有数据了!");
//                    }
//                }else{
//                    NewClaimListAdapter adapter1 = new NewClaimListAdapter(MainActivity.this, DataManager.MyClaimUtilsModel.data.Claimlist);
//                    NewsListview.setAdapter(adapter1);
//                    adapter1.notifyDataSetChanged();
//                }
//            }
//        }, 1000);
//    }
//    /**
//     * 下拉刷新
//     * @param view
//     */
//    @Override
//    public void onHeaderRefresh(PullToRefreshView view) {
//        mPullToRefreshView.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                mPullToRefreshView.onHeaderRefreshComplete();
//                if(falg){
//                    NewsListAdapter adapter = new NewsListAdapter(MainActivity.this, MyNewsList);
//                    NewsListview.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
//                }else{
//                    NewClaimListAdapter adapter1 = new NewClaimListAdapter(MainActivity.this, DataManager.MyClaimUtilsModel.data.Claimlist);
//                    NewsListview.setAdapter(adapter1);
//                    adapter1.notifyDataSetChanged();
//                }
//            }
//        }, 1000);
//    }
}
