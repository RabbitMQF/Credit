package com.example.credit.Activitys;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.credit.Adapters.CommmentAdapter;
import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.PullToRefreshView;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Decoder.BASE64Decoder;

/**
 * 评论列表界面
 */
public class CommentListActivity extends BaseActivity implements PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener{
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.b_topLLt)
    LinearLayout b_topLLt;
    @ViewInject(R.id.b_topYicon)
    ImageView b_topYicon;
    @ViewInject(R.id.b_topY)
    TextView b_topY;
    @ViewInject(R.id.Ccomm_list)
    MyListView Ccomm_list;//评论列表
    @ViewInject(R.id.scrollV)
    ScrollView scrollV;//列表
    @ViewInject(R.id.commentNull)
    LinearLayout commentNull;//空

    @ViewInject(R.id.pull_refresh_view233)
    PullToRefreshView mPullToRefreshView;
    CommmentAdapter adapter;
    public static Handler handler;
    CreditSharePreferences csp;
    int type;
    public static WaitDialog wd;
    AlertDialog.Builder builder;
    public static AlertDialog dialog;

    public static List<DataManager.MyCommentlistr.DataBean.UserreviewBean> listpl = new ArrayList<>();
    boolean falg=false;
    int por;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        ViewUtils.inject(this);
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        wd = new WaitDialog(this);
        Intent i = getIntent();
        type = i.getIntExtra("type", 0);
        csp = CreditSharePreferences.getLifeSharedPreferences();
        init();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        if(falg==false){
                            listpl=DataManager.MyCommentlistrS.data.userreview;
                        }else{
                            por=listpl.size()-1;
                            listpl.addAll(DataManager.MyCommentlistrS.data.userreview);
                        }
                        if(listpl.size()>0){
                            mPullToRefreshView.setVisibility(View.VISIBLE);
                            commentNull.setVisibility(View.GONE);
                        }
                        Rit();
                        if(falg==false) {
                            wd.dismiss();
                        }
                        break;
                    case 500:
                        if(falg==false) {
                            wd.dismiss();
                        }
                        mPullToRefreshView.setVisibility(View.GONE);
                        commentNull.setVisibility(View.VISIBLE);
                        break;
                }
            }
        };
    }

    public void init() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("是否登录");
        builder.setMessage("请先登录账号!");
        builder.setPositiveButton("去登陆", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(CommentListActivity.this,LoginActivity.class));
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                finish();
//                startActivity(new Intent(SearchFirmActivty.this, MainActivity.class));
            }
        });
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失


        b_topYicon.setVisibility(View.VISIBLE);
        b_topY.setVisibility(View.VISIBLE);//显示右上角控件“发表”
        b_topname.setText("评论");
        b_return.setOnClickListener(listener);
        b_topLLt.setOnClickListener(listener);
        intiow();
        Ccomm_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!csp.getLoginStatus()) {//判定是否登录
                    //Toast.show("请先登录账号");
                    dialog.show();
                } else {
                    falg=false;
                    Intent i = new Intent(CommentListActivity.this, CommentListDetailsActivity.class);
                    i.putExtra("uid", listpl.get(position).MEMBERID);
                    i.putExtra("pid", listpl.get(position).COMMENTID);
                    i.putExtra("position", position);
                    startActivityForResult(i, 22);
                }
            }
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.b_return://返回键
                    finish();
                    break;
                case R.id.b_topLLt://跳转发表评论界面
                    if (!csp.getLoginStatus()) {//判定是否登录
                        //Toast.show("请先登录账号");
                        dialog.show();
                    } else {
                        falg=false;
                        Intent i = new Intent(CommentListActivity.this, ToCommentActivity.class);
                        startActivityForResult(i, 11);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 11:
                intiow();
                break;
            case 22:
                adapter.notifyDataSetChanged();
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void Rit() {
        for (int i = 0; i < listpl.size(); i++) {
            if (!listpl.get(i).ICONPATH.equals("")) {
                String strr = ((listpl.get(i).ICONPATH).substring(listpl.get(i).ICONPATH.length() - 20, listpl.get(i).ICONPATH.length() - 5)).replaceAll("\\/", "_");
                File file = new File(Environment.getExternalStorageDirectory() + "/Credit/cache/" + strr + ".jpg");
                if (!file.exists()) {//获取本地图片路径是否存在
                    try {
                        BASE64Decoder decode = new BASE64Decoder();
                        byte[] b = decode.decodeBuffer(listpl.get(i).ICONPATH);
//                        System.out.println(new String(b));
//                        StringBuffer str = new StringBuffer();//不建议用String
//                        for (byte bs : b) {
//                            str.append(Integer.toBinaryString(bs));//转换为二进制
//                        }
                        //把字节数组的图片写到另一个地方
                        File apple = new File(Environment.getExternalStorageDirectory() + "/Credit/cache/" + strr + ".jpg");
                        FileOutputStream fos = new FileOutputStream(apple);
                        fos.write(b);
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        adapter = new CommmentAdapter(CommentListActivity.this, listpl);
        Ccomm_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Ccomm_list.setSelection(por-2);
        mPullToRefreshView.onFooterRefreshComplete();
    }

    public void intiow() {
        /**
         * 查询评论
         */
        GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.COMM, RequestMethod.GET);
        request14.add("deviceId", (new Build()).MODEL);
        request14.add("token", SearchFirmActivty.MD5s(DataManager.allcountsList.get(0).EnterAddtionID + (new Build()).MODEL));
        request14.add("KeyNo", DataManager.allcountsList.get(0).EnterAddtionID);
        if (!csp.getLoginStatus()) {//判定是否登录
            request14.add("memberId", "");
        }else{
            request14.add("memberId", csp.getID());
        }
        if(falg==true){
            request14.add("pageIndex", DataManager.MyCommentlistrS.data.Paging.CurrentPage+1);
        }else{
            wd.show();
        }
        CallServer.getInstance().add(CommentListActivity.this, request14, MyhttpCallBack.getInstance(), 0x201, true, false, true);
    }

    /**
     * 上拉加载
     * @param view
     */
    @Override
    public void onFooterRefresh(PullToRefreshView view) {

        mPullToRefreshView.postDelayed(new Runnable() {

            @Override
            public void run() {
                if(DataManager.MyCommentlistrS.data.Paging.TotalPage>DataManager.MyCommentlistrS.data.Paging.CurrentPage){
                    falg=true;
                    intiow();
                }else {
                    mPullToRefreshView.onFooterRefreshComplete();
                    Toast.show("没有数据了!");
                }
            }
        }, 1000);

    }

    /**
     * 下拉刷新
     * @param view
     */
    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {

            @Override
            public void run() {
                mPullToRefreshView.onHeaderRefreshComplete();
                adapter = new CommmentAdapter(CommentListActivity.this, listpl);
                adapter.notifyDataSetChanged();
                Ccomm_list.setAdapter(adapter);
            }
        }, 1000);
    }

}
