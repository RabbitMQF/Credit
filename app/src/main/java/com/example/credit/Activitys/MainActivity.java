package com.example.credit.Activitys;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.credit.Adapters.MyGridAdaptermMain;
import com.example.credit.Adapters.NewClaimListAdapter;
import com.example.credit.Adapters.NewsListAdapter;
import com.example.credit.Contacts.ContactsActivity;
import com.example.credit.Dialogs.Apk_updata_dialog;
import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.NetUtils;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.FileUtil;
import com.example.credit.Views.ImageCycleView;
import com.example.credit.Views.MyGridView;
import com.example.credit.Views.MyListView;
import com.example.credit.Views.MyScrollView;
import com.example.credit.Views.RoundImageView;
import com.example.credit.Views.SlidingMenu;
import com.igexin.sdk.PushManager;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import com.yolanda.nohttp.RequestMethod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Decoder.BASE64Decoder;

import static com.example.credit.R.id.top_search;
import static com.example.credit.Views.FileUtil.decodeBitmap;
import static com.example.credit.Views.FileUtil.deleteDir;

//SwipeRefreshLayout.OnRefreshListener
public class MainActivity extends Activity implements View.OnClickListener {
    //    ,PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener
    private long exitTime = 0;
    private SlidingMenu mLeftMenu;
    private final int NOHTTP_CITY = 0x021;//获取城市
    private final int NOHTTP_INDUSTRY = 0x023;//获取行业

    private int searchLayoutTop;//顶部位置
    @ViewInject(R.id.search_top1)
    RelativeLayout search_top1;//搜索
    @ViewInject(R.id.search_top2)
    LinearLayout search_top2;//顶部悬浮
    MyScrollView myScrollView;//自定义ScrollView

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

    @ViewInject(R.id.togg)
    ImageView togg;//左上角侧滑按钮
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

    @ViewInject(R.id.hot_1)
    TextView hot_1;//热点1
    @ViewInject(R.id.hot_2)
    TextView hot_2;//热点2
    @ViewInject(R.id.hot_huan)
    TextView hot_huan;//热点换


    @ViewInject(R.id.scmain)
    ScrollView scmain;
    @ViewInject(R.id.news_list)
    MyListView NewsListview;
    @ViewInject(R.id.NewClaimListview)
    MyListView NewClaimListview;

    @ViewInject(R.id.NewClaimTxT)
    TextView NewClaimTxT;//最新认领暂无数据
    @ViewInject(R.id.News_list_null)
    TextView News_list_null;//最新热点暂无数据

    @ViewInject(R.id.myGridViewMain)
    MyGridView myGridViewMain;

    @ViewInject(R.id.cliam_more)
    TextView cliam_more;//认领-查看更多
    @ViewInject(R.id.news_more)
    TextView news_more;//新闻-查看更多

    NewsListAdapter adapter;
    public static List<DataManager.MyHot.DataBean.HotspotAnalysisBean> MyHotsList = new ArrayList<DataManager.MyHot.DataBean.HotspotAnalysisBean>();//热搜

    public static List<DataManager.MyNews.DataBean.NewslistBean> MyNewsList = new ArrayList<DataManager.MyNews.DataBean.NewslistBean>();//初始新闻集合

    public static List<DataManager.MyClaimUtils.DataBean.ClaimlistBean> MyCliamList = new ArrayList<DataManager.MyClaimUtils.DataBean.ClaimlistBean>();//初始最新认领集合

    static CreditSharePreferences csp;
    Boolean LoginStatus;
    public static ProgressDialog pd;
    public static WaitDialog ad;
    private ImageCycleView mImageCycleView;
    Apk_updata_dialog apppd;
    int t = 2;
    int str = 1;
    AlertDialog.Builder builder;
    public static AlertDialog dialog;
    public int[] imgs1 = {R.mipmap.maincon_1, R.mipmap.maincon_2,
            R.mipmap.maincon_3, R.mipmap.maincon_4};
    public String[] txt = {"商标查询", "专利查询",
            "招投标", "失信查询"};
    public static NewClaimListAdapter adapter1;

    int num1, num2;//热点随机数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FileUtil.filenewsexists();
        csp = CreditSharePreferences.getLifeSharedPreferences();
        LoginStatus = csp.getLoginStatus();
        ViewUtils.inject(this);

        ad = new WaitDialog(this);
        boolean falg = NetUtils.isConnectingToInternet(this);
        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
        mLeftMenu.setOnScrollStopListner(onScrollStop);
        PushManager.getInstance().initialize(this.getApplicationContext());
        PushManager.getInstance().getClientid(MainActivity.this);

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
                        adapter = new NewsListAdapter(MainActivity.this, MyNewsList);
                        if (MyNewsList.size() == 0) {
                            News_list_null.setVisibility(View.VISIBLE);
                            NewsListview.setVisibility(View.GONE);
                        } else {
                            NewsListview.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
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
                            startActivity(new Intent(MainActivity.this, MycomplaintsListActivity.class).putExtra("falgg",1));
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
                        adapter1 = new NewClaimListAdapter(MainActivity.this, MyCliamList, 0);
                        NewClaimListview.setAdapter(adapter1);
                        adapter1.notifyDataSetChanged();
                        NewClaimListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ad.show();
                                String KeyNo = MyCliamList.get(position).PRIPID;//市场主体身份代码
                                String token = MD5.MD5s(KeyNo + (new Build()).MODEL);
                                String b=MD5.MD5s("14591365178594053553" + (new Build()).MODEL);
                                GsonUtil requst = new GsonUtil(URLconstant.URLINSER + URLconstant.GETITEMNUM, RequestMethod.GET);
                                requst.add("KeyNo", KeyNo);
                                requst.add("token", token);
                                requst.add("deviceId", (new Build()).MODEL);
                                if (csp.getLoginStatus()) {
                                    requst.add("memberId", csp.getID());
                                }
                                requst.add("memberId", "");
                                requst.add("regnore", MyCliamList.get(position).REGNORE);
                                requst.add("priptype", MyCliamList.get(position).ENTTYPE);
                                CallServer.getInstance().add(MainActivity.this, requst, MyhttpCallBack.getInstance(), 0x026, true, false, true);
                            }
                        });
                        break;
                    case 8://跳公司详情
                        ad.dismiss();
                        if (Main_NewCliam_MoreListActivity.falg == true) {
                            Main_NewCliam_MoreListActivity.ad.dismiss();
                            Main_NewCliam_MoreListActivity.falg = false;
                        }
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
                    case 10://刷新新闻
                        adapter.notifyDataSetChanged();
                        break;
                    case 11:
                        Intent i1w1 = new Intent(MainActivity.this, Main_NewCliam_MoreListActivity.class);
                        i1w1.putExtra("Tname", "最新认领");
                        startActivity(i1w1);
                        break;
                    default:
                        com.example.credit.Utils.Toast.show("数据正在赶来的路上...");
                        break;
                }

            }
        };
        if (falg == true) {
            initView();
            initData();
        } else {
            this.finish();
            System.exit(0);
        }
        FileUtil.imgscache();//缓存轮播图base64

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
        if (DataManager.MyNewAppS.message != null && DataManager.MyNewAppS.message.equals("success")) {
            if (DataManager.MyNewAppS.data != null & DataManager.MyNewAppS.data.size() > 0) {
                if (DataManager.MyNewAppS.data.get(0).VERSION != null & DataManager.MyNewAppS.data.get(0).PATH != null) {
                    double in = Double.parseDouble(DataManager.MyNewAppS.data.get(0).VERSION);//最新版本号
                    double isn = Double.parseDouble( FileUtil.getVersionName(MainActivity.this));//当前版本号
                    if (isn < in) {
                        dialog.show();
                    }
                }
            }
        }

        /**
         * 轮播
         */
        mImageCycleView = (ImageCycleView) findViewById(R.id.icv_topView);
        List<ImageCycleView.ImageInfo> list = new ArrayList<ImageCycleView.ImageInfo>();

        if (DataManager.LBimgS.data.Photolist != null && DataManager.LBimgS.data.Photolist.size()>0) {
            //SD卡图片资源
            for (int i = 0; i < DataManager.LBimgS.data.Photolist.size(); i++) {
                list.add(new ImageCycleView.ImageInfo(new File(Environment.getExternalStorageDirectory(),"/Credit/cache/CarouselImg" + i + ".jpg"),"",""));
            }
        }else{
            //res图片资源
            list.add(new ImageCycleView.ImageInfo(R.drawable.banner1, "", ""));
            list.add(new ImageCycleView.ImageInfo(R.drawable.banner2, "", ""));
        }
        mImageCycleView.loadData(list, new ImageCycleView.LoadImageCallBack() {
            @Override
            public ImageView loadAndDisplay(ImageCycleView.ImageInfo imageInfo) {

//                //本地图片
//                ImageView imageView=new ImageView(MainActivity.this);
//                imageView.setImageResource(Integer.parseInt(imageInfo.image.toString()));
//                return imageView;
//                //使用SD卡图片
//                SmartImageView smartImageView=new SmartImageView(MainActivity.this);
//                smartImageView.setImageURI(Uri.fromFile((File)imageInfo.image));
//                return smartImageView;
//				//使用SmartImageView，既可以使用网络图片也可以使用本地资源
//				SmartImageView smartImageView=new SmartImageView(MainActivity.this);
//				smartImageView.setImageResource(Integer.parseInt(imageInfo.image.toString()));
//				return smartImageView;
//                使用BitmapUtils,只能使用网络图片
				BitmapUtils bitmapUtils = new BitmapUtils(MainActivity.this);
				ImageView imageView = new ImageView(MainActivity.this);
				bitmapUtils.display(imageView, imageInfo.image.toString());
				return imageView;
            }
        });
    }

    private void initData() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("最新版本");
        builder.setMessage("是否更新最新版本?!");
        builder.setPositiveButton("现在更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse(DataManager.MyNewAppS.data.get(0).PATH);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失

        MyGridAdaptermMain adapters = new MyGridAdaptermMain(MainActivity.this, imgs1, txt);
        myGridViewMain.setAdapter(adapters);
        myGridViewMain.setSelector(new ColorDrawable(Color.TRANSPARENT));
        myGridViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                switch (position) {
                    case 0://商标查询
                        i = new Intent(MainActivity.this, Main_SearchActivity.class);
                        i.putExtra("hit", "商标");
                        startActivity(i);
                        break;
                    case 1://专利查询
                        i = new Intent(MainActivity.this, Main_SearchActivity.class);
                        i.putExtra("hit", "专利");
                        startActivity(i);
                        break;
                    case 2://招投标
                        startActivity(new Intent(MainActivity.this, Main_SearchActivity.class).putExtra("hit", "招投标"));
                        break;
                    case 3://失信
                        i = new Intent(MainActivity.this, Main_SearchActivity.class);
                        i.putExtra("hit", "失信");
                        startActivity(i);
                        break;
                }
            }
        });

        if (MyNewsList != null && MyNewsList.size() > 0) {
            handler.sendEmptyMessage(0);
        } else {//没有数据
            news_more.setVisibility(View.GONE);
            btmore.setVisibility(View.GONE);
            News_list_null.setVisibility(View.VISIBLE);
        }
        if (MyCliamList != null && MyCliamList.size() > 0) {
            handler.sendEmptyMessage(7);
        } else {//没有数据
            cliam_more.setVisibility(View.GONE);
            btmore.setVisibility(View.GONE);
            NewClaimTxT.setVisibility(View.VISIBLE);
        }
        if (MyHotsList.size() > 1 && !MyHotsList.get(0).KEYWORDS.equals("") && !MyHotsList.get(1).KEYWORDS.equals("")) {
            hot_1.setText(MyHotsList.get(0).KEYWORDS);
            hot_2.setText(MyHotsList.get(1).KEYWORDS);
        }
    }

    private void initView() {
        topSearch = (RelativeLayout) findViewById(top_search);
        topSearch.setOnClickListener(this);
        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("请稍后...");
        pd.setCancelable(false);
        headimg.setOnClickListener(listener);
        UserSz.setOnClickListener(listener);
        Smenu_1.setOnClickListener(listener);
        Smenu_2.setOnClickListener(listener);
        Smenu_3.setOnClickListener(listener);
        Smenu_4.setOnClickListener(listener);
        Smenu_5.setOnClickListener(listener);
        Smenu_6.setOnClickListener(listener);
        isLogin();
        hot_1.setOnClickListener(listener);
        hot_2.setOnClickListener(listener);
        hot_huan.setOnClickListener(listener);
        login.setOnClickListener(listener);
        tab1.setOnClickListener(listener);
        tab2.setOnClickListener(listener);
        tab3.setOnClickListener(listener);
        tab4.setOnClickListener(listener);
        btmore.setOnClickListener(listener);
        set.setOnClickListener(listener);
        cliam_more.setOnClickListener(listener);
        myScrollView = (MyScrollView) findViewById(R.id.myScrollView1);
        myScrollView.setOnScrollListener(new MyScrollView.OnScrollListener(){
            /**
             * 监听滚动Y值变化，通过addView和removeView来实现悬停效果
             * @param scrollY
             */
            @Override
            public void onScroll(int scrollY) {
                if(scrollY >= searchLayoutTop){
                    if (topSearch.getParent()!=search_top2) {
                        search_top1.removeView(topSearch);
                        search_top2.setVisibility(View.VISIBLE);
                        search_top2.addView(topSearch);
                    }
                }else{
                    if (topSearch.getParent()!=search_top1) {
                        search_top2.removeView(topSearch);
                        search_top2.setVisibility(View.GONE);
                        search_top1.addView(topSearch);
                    }
                }
            }
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Smenu_1://我的评价
                    if (!csp.getLoginStatus()) {//判定是否登录
                        com.example.credit.Utils.Toast.show("请先登录账号");
                    } else {
                        ad.show();
                        GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.MMOMM, RequestMethod.GET);
                        request14.add("deviceId", (new Build()).MODEL);
                        request14.add("token", MD5.MD5s(csp.getID() + (new Build()).MODEL));
                        request14.add("KeyNo", csp.getID());
                        CallServer.getInstance().add(MainActivity.this, request14, MyhttpCallBack.getInstance(), 0x206, true, false, true);
                    }
                    break;
                case R.id.Smenu_2://我的投诉
                    if (!csp.getLoginStatus()) {//判定是否登录
                        com.example.credit.Utils.Toast.show("请先登录账号");
                    } else {
                        pd.show();
                        getComplaint(MainActivity.this);
                    }
                    break;
                case R.id.Smenu_3://我的关注
                    if (!csp.getLoginStatus()) {//判定是否登录
                        com.example.credit.Utils.Toast.show("请先登录账号");
                    } else {
                        ad.show();
                        GsonUtil MyconcernRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.MYFAVORITE, RequestMethod.GET);
                        MyconcernRuerst.add("deviceId", (new Build()).MODEL);
                        MyconcernRuerst.add("token", MD5.MD5s(csp.getID() + (new Build()).MODEL));
                        MyconcernRuerst.add("KeyNo", csp.getID());
                        CallServer.getInstance().add(MainActivity.this, MyconcernRuerst, MyhttpCallBack.getInstance(), 0x103, true, false, true);
                    }
                    break;
                case R.id.Smenu_4://我的认领
                    if (!csp.getLoginStatus()) {//判定是否登录
                        com.example.credit.Utils.Toast.show("请先登录账号");
                    } else {
                        ad.show();
                        GsonUtil MyClaimRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.MYCLAIMURL, RequestMethod.GET);
                        MyClaimRuerst.add("deviceId", (new Build()).MODEL);
                        MyClaimRuerst.add("token", MD5.MD5s(csp.getID() + (new Build()).MODEL));
                        MyClaimRuerst.add("KeyNo", csp.getID());
                        CallServer.getInstance().add(MainActivity.this, MyClaimRuerst, MyhttpCallBack.getInstance(), 0x303, true, false, true);
                    }
                    break;
                case R.id.Smenu_5://服务协议
                    Intent in12 = new Intent(MainActivity.this, AgreementActivity.class);
                    startActivity(in12);
                    break;
                case R.id.Smenu_6://关于我们
                    Intent in13 = new Intent(MainActivity.this, AboutActivity.class);
                    startActivity(in13);
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
                    in1.putExtra("Setname", "");
                    startActivity(in1);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;
                case R.id.tab2://法人查询
                    Intent in2 = new Intent(MainActivity.this, SearchFirmActivty.class);
                    in2.putExtra("type", 1);
                    startActivity(in2);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;
                case R.id.tab3://品牌查询
                    Intent in3 = new Intent(MainActivity.this, SearchFirmActivty.class);
                    in3.putExtra("type", 2);
                    startActivity(in3);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;
                case R.id.tab4://失信信息
                    Intent in4 = new Intent(MainActivity.this, SearchFirmActivty.class);
                    in4.putExtra("type", 3);
                    startActivity(in4);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;

                case R.id.set://个人信息设置
                    if (!csp.getLoginStatus()) {//判定是否登录
                        com.example.credit.Utils.Toast.show("请先登录账号");
                    } else {
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
                        str = 2;
                    } else {
                        com.example.credit.Utils.Toast.show("没有更多数据了！");
                        btmore.setVisibility(View.GONE);
                    }
                    break;
                case R.id.cliam_more:
                    GsonUtil NewClaimRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.NEWCLAIM, RequestMethod.GET);//最新认领
                    CallServer.getInstance().add(MainActivity.this, NewClaimRequest, MyhttpCallBack.getInstance(), 0x1131, true, false, true);
                    break;
                case R.id.hot_huan:
                    if (MyHotsList.size() > 0) {
                        Random random = new Random();//随机数
                        while (true) {
                            num1 = random.nextInt(MyHotsList.size());
                            num2 = random.nextInt(MyHotsList.size());
                            if (num1 != num2) {
                                break;
                            }
                        }
                        hot_1.setText(MyHotsList.get(num1).KEYWORDS);
                        hot_2.setText(MyHotsList.get(num2).KEYWORDS);
                    } else {
                        com.example.credit.Utils.Toast.show("获取数据失败");
                    }
                    break;
                case R.id.hot_1:
                    Intent hot_1s = new Intent(MainActivity.this, SearchFirmActivty.class);
                    hot_1s.putExtra("Setname", hot_1.getText().toString());
                    hot_1s.putExtra("type", 0);
                    startActivity(hot_1s);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;
                case R.id.hot_2:
                    Intent hot_2s = new Intent(MainActivity.this, SearchFirmActivty.class);
                    hot_2s.putExtra("Setname", hot_2.getText().toString());
                    hot_2s.putExtra("type", 0);
                    startActivity(hot_2s);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
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
        settogg();
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void settogg() {
        if (SlidingMenu.isOpen) {
            togg.setBackground(getResources().getDrawable(R.drawable.gang32));
        } else {
            togg.setBackground(getResources().getDrawable(R.drawable.gang3));
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case top_search:
                Intent in = new Intent(this, SearchFirmActivty.class);
                in.putExtra("Setname", "");
                startActivity(in);
                overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                break;
            default:
                break;
        }
    }
    private void isLogin() {
        LoginStatus = csp.getLoginStatus();
        if (LoginStatus) {//若当前状态为登录
            UserSz.setText(csp.getALIASNAME());
            login.setText("退出登录");
            if (!csp.getICONSTEAM().equals("")) {
                File file = new File(Environment.getExternalStorageDirectory() + "/Credit/loginImg.jpg");
                if (file.exists()) {//获取本地图片路径是否存在
                    headimg.setImageBitmap(decodeBitmap(Environment.getExternalStorageDirectory() + "/Credit/loginImg.jpg", 80, 80));
                    // Picasso.with(MainActivity.this).load(decodeBitmap(Environment.getExternalStorageDirectory() + "/Credit/loginImg.jpg", 80, 80)).into(headimg);

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
        initData();
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
    /**
     * 横滑置顶监听器
     */
    SlidingMenu.OnScrollStopListner onScrollStop = new SlidingMenu.OnScrollStopListner() {
        public void onScrollToRightEdge() {
            settogg();
        }

        public void onScrollToMiddle() {
        }

        public void onScrollToLeftEdge() {
            settogg();
        }

        public void onScrollStoped() {
        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            searchLayoutTop = togg.getBottom();//获取ImageView的顶部位置
        }
    }

}
