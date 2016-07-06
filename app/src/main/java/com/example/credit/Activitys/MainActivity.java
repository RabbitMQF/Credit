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
import android.view.KeyEvent;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.credit.Adapters.NewsListAdapter;
import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.FileUtil;
import com.example.credit.Views.RoundImageView;
import com.example.credit.Views.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.io.File;

public class MainActivity extends Activity implements View.OnClickListener {
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
    ListView NewsListview;
    public static Handler handler;


    @ViewInject(R.id.headimg)
    RoundImageView headimg;//我的头像
    private static final String IMAGE_FILE_NAME = "avatarImage.jpg";// 头像文件名称
    private static String urlpath;            // 图片本地路径
    private static final int REQUESTCODE_PICK = 0;        // 相册选图标记
    private static final int REQUESTCODE_CUTTING = 2;    // 图片裁切标记
    private static String newName = "UserImg.jpg";
    public static String photo;

    @ViewInject(R.id.UserSz)
    TextView UserSz;//用户

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
    static CreditSharePreferences csp;
    Boolean LoginStatus;
    public static ProgressDialog pd;
    WaitDialog ad;
//    @ViewInject(R.id.pb_1)
//    TextView pb_1;//首页
//    @ViewInject(R.id.pb_2)
//    TextView pb_2;
//    @ViewInject(R.id.pb_3)
//    TextView pb_3;
//    @ViewInject(R.id.pb_4)
//    TextView pb_4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        csp = CreditSharePreferences.getLifeSharedPreferences();
        LoginStatus = csp.getLoginStatus();
        ViewUtils.inject(this);
        ad=new WaitDialog(this);
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
        GsonUtil request = new GsonUtil(URLconstant.URLINSER + URLconstant.GETCITY, RequestMethod.GET);
        CallServer.getInstance().add(this, request, MyhttpCallBack.getInstance(), NOHTTP_CITY, true, false, true);//获取城市
        CallServer.getInstance().add(this, new GsonUtil(URLconstant.URLINSER + URLconstant.GETINDUSTRY, RequestMethod.GET), MyhttpCallBack.getInstance(), NOHTTP_INDUSTRY, true, false, true);//获取行业
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 0:
                        NewsListAdapter adapter = new NewsListAdapter(MainActivity.this, DataManager.NewssList);
                        NewsListview.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1://我的评价
                        ad.dismiss();
                        Intent i1 = new Intent(MainActivity.this, MyCommentlistActivity.class);
                        startActivity(i1);
                        break;
                    case 2://跳我的投诉
                        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
                        if(!cn.getClassName().equals(MycomplaintsListActivity.class.getName())) {
                            pd.dismiss();
                            startActivity(new Intent(MainActivity.this, MycomplaintsListActivity.class));
                        }else {MycomplaintsListActivity.handler.sendEmptyMessage(2);}
                            break;
                    case 5://跳我的关注
                        ad.dismiss();
                        Intent i3 = new Intent(MainActivity.this, MyconcernActivity.class);
                        startActivity(i3);
                        break;
                    case 6://跳我的认领
                        ad.dismiss();
                        Intent i6= new Intent(MainActivity.this, MyClaimActivity.class);
                        startActivity(i6);
                        break;
                    default:
                        break;
                }

            }
        };
        initData();
    }

    private void initData() {
       /* GsonUtil NewsRequest=new GsonUtil(URLconstant.NEWSURL,RequestMethod.GET);
        NewsRequest.setConnectTimeout(20000);
        CallServer.getInstance().add(this,NewsRequest,MyhttpCallBack.getInstance(),0x111,true,false,true);*/
        if (DataManager.NewssList != null && DataManager.NewssList.size() > 0) {
            handler.sendEmptyMessage(0);
        }
    }

    private void initView() {
        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
        topSearch = (RelativeLayout) findViewById(R.id.top_search);
        topSearch.setOnClickListener(this);
        NewsListview = (ListView) findViewById(R.id.news_list);
        pd=new ProgressDialog(MainActivity.this);
        pd.setMessage("请稍后...");
        pd.setCancelable(false);
        NewsListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, NewsContentActivity.class);
                i.putExtra("position", position);
                startActivity(i);
//                Toast.makeText(MainActivity.this, "此模块，正在赶点加工中...", Toast.LENGTH_SHORT).show();
            }
        });

        //获取本地图片路径
//        if(esp.getLogin()==true){
//            String imgpath =Environment.getExternalStorageDirectory() + "/Credit" + "/"+esp.getPhone()+"UserImg.jpg";
        String imgpath = Environment.getExternalStorageDirectory() + "/Credit" + "/UserImg.jpg";
        File file = new File(imgpath);
        if (file.exists()) {//获取本地图片路径是否存在
            Bitmap bm = BitmapFactory.decodeFile(imgpath);
            //将图片显示到ImageView中
            headimg.setImageBitmap(bm);
//            }
//            else{
//                options=new DisplayImageOptions.Builder()
//                        .showImageForEmptyUri(R.drawable.plugin_imagebrowser_friends_sends_pictures_no)
//                        // 默认图片
//                        .showImageOnLoading(R.drawable.plugin_imagebrowser_friends_sends_pictures_no)
//                        // 加载中的图片
//                        .showImageOnFail(R.drawable.plugin_imagebrowser_friends_sends_pictures_no)
//                        .cacheInMemory(true)// 是否缓存在内中
//                        .cacheOnDisk(true)// 是否缓存在磁盘中
//                        .build();
//                ImageLoader.getInstance().displayImage(URLProtocol.USERIMGURL+esp.getImage(), iamge, options);
//            }
        }

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
                    Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                    // 如果朋友们要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                    pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(pickIntent, REQUESTCODE_PICK);
                    break;
                case R.id.Smenu_1://我的评价
                    ad.show();
                    GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.MMOMM, RequestMethod.GET);
                    request14.add("deviceId",(new Build()).MODEL);
                    request14.add("token",SearchFirmActivty.MD5s("86D9D7F53FCA45DD93E2D83DFCA0CB42" + (new Build()).MODEL));
                    request14.add("KeyNo","86D9D7F53FCA45DD93E2D83DFCA0CB42");
                    CallServer.getInstance().add(MainActivity.this, request14, MyhttpCallBack.getInstance(), 0x206, true, false, true);


//                    Toast.makeText(MainActivity.this, "此模块，正在赶点加工中...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Smenu_2://我的投诉
                    pd.show();
                    getComplaint(MainActivity.this);

//                    Toast.makeText(MainActivity.this, "此模块，正在赶点加工中...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Smenu_3://我的关注
                    ad.show();
                    GsonUtil  MyconcernRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.MYFAVORITE, RequestMethod.GET);
                    MyconcernRuerst.add("deviceId",(new Build()).MODEL);
                    MyconcernRuerst.add("token",SearchFirmActivty.MD5s("86D9D7F53FCA45DD93E2D83DFCA0CB42" + (new Build()).MODEL));
                    MyconcernRuerst.add("KeyNo","86D9D7F53FCA45DD93E2D83DFCA0CB42");
                    CallServer.getInstance().add(MainActivity.this,MyconcernRuerst,MyhttpCallBack.getInstance(),0x103,true,false,true);

//                    Toast.makeText(MainActivity.this, "此模块，正在赶点加工中...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Smenu_4://我的认领
                    ad.show();
                    GsonUtil  MyClaimRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.MYCLAIMURL, RequestMethod.GET);
                    MyClaimRuerst.add("deviceId",(new Build()).MODEL);
                    MyClaimRuerst.add("token",SearchFirmActivty.MD5s("86D9D7F53FCA45DD93E2D83DFCA0CB42" + (new Build()).MODEL));
                    MyClaimRuerst.add("KeyNo","86D9D7F53FCA45DD93E2D83DFCA0CB42");
                    CallServer.getInstance().add(MainActivity.this,MyClaimRuerst,MyhttpCallBack.getInstance(),0x303,true,false,true);
//                    Toast.makeText(MainActivity.this, "此模块，正在赶点加工中...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Smenu_5://服务协议
                    Toast.makeText(MainActivity.this, "此模块，正在赶点加工中...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Smenu_6://关于我们
                    Toast.makeText(MainActivity.this, "此模块，正在赶点加工中...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.login://登录
                    if (!LoginStatus) {//如果当前状态未登录  点登录的跳转
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    } else {//如果当前状态已登录  点击退出登录的操作
                        csp.putUser(null);
                        com.example.credit.Utils.Toast.show("退出登录");
                        csp.putLoginStatus(false);
                        isLogin();

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

                case R.id.set:
                    Intent is=new Intent(MainActivity.this,UserSetActivity.class);
                    startActivity(is);
                    break;


            }
        }
    };

    /**
     * 获取我的投诉列表方法
     */
    public static void getComplaint(Activity activity) {
        GsonUtil ComplaintsRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.GETCOMPLAIN, RequestMethod.GET);
        ComplaintsRuerst.add("token", MD5.MD5s(csp.getID()+ new Build().MODEL));//csp.getID()
        ComplaintsRuerst.add("KeyNo",csp.getID());// b2d794b453664657af61b373c1d00e7c
        ComplaintsRuerst.add("deviceId", new Build().MODEL);
        CallServer.getInstance().add(activity,ComplaintsRuerst,MyhttpCallBack.getInstance(),0x997,true,false,true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUESTCODE_PICK:// 直接从相册获取
                try {
                    startPhotoZoom(data.getData());
                } catch (NullPointerException e) {
                    e.printStackTrace();// 用户点击取消操作
                }
                break;
            case REQUESTCODE_CUTTING:// 取得裁剪后的图片
                if (data != null) {
                    setPicToView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            // 取得SDCard图片路径做显示
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(null, photo);
            urlpath = FileUtil.saveFile(MainActivity.this, newName, photo);
            headimg.setImageDrawable(drawable);
        }
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
            login.setText("退出登录");
        } else {//若当前状态未未登录
            login.setText("登录");
        }
    }

    @Override
    protected void onRestart() {
        isLogin();
        super.onRestart();
    }

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
