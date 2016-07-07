package com.example.credit.Activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.credit.Adapters.MyGridAdapterClaim;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.MyGridView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 提交投诉界面
 */
public class ToComplaintActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;//顶栏
    @ViewInject(R.id.b_return)
    LinearLayout b_return;//退出
    @ViewInject(R.id.com_et_title)
    EditText com_et_title;//投诉主题
    @ViewInject(R.id.com_et_conten)
    EditText com_et_conten;//投诉内容
    @ViewInject(R.id.com_et_num)
    TextView com_et_num;//内容字数
    @ViewInject(R.id.com_photo)
    ImageView com_photo;//投诉图片
    @ViewInject(R.id.com_submit)
    TextView com_submit;//提交按钮
    @ViewInject(R.id.myGridViewtc)
    MyGridView myGridViewtc;//照片控件
    CreditSharePreferences csp;
    private static final String IMAGE_FILE_NAME = "avatarImage.jpg";// 图片文件名称
    private static String urlpath;			// 图片本地路径
    private static final int REQUESTCODE_PICK = 0;		// 相册选图标记
    private static final int REQUESTCODE_TAKE = 1;		// 相机拍照标记
    private static final int REQUESTCODE_CUTTING = 2;	// 图片裁切标记
    AlertDialog.Builder builder;
    public Drawable[] imgs1; //九张图片数组
    int i=0;
    List<String> listStirng=new ArrayList<>();
    public static Handler handler;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_complaint);
        csp = CreditSharePreferences.getLifeSharedPreferences();
        ViewUtils.inject(this);
        init();
        initHandler();

    }

    private void initHandler() {
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 0://提交图片成功成功
                        pd.dismiss();
                        finish();
                        Toast.show("提交投诉成功");
                        MycomplaintsListActivity.handler.sendEmptyMessage(4);//通知投诉listview更新数据源重新适配UI
                        break;
                    case 1://提交文字成功
                        if(imgs1!=null){
                            String attchmentDescS="";
                            String attchmentSteamS="";
                            for(int j=0;j<i;j++){
                                attchmentDescS=attchmentDescS+"pic@";
                            }
                            for(int c=0;c<listStirng.size();c++){
                                attchmentSteamS=attchmentSteamS+listStirng.get(c)+"@";
                            }

                            GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.ENCLOSUREURL, RequestMethod.POST);
                            request14.add("deviceId",(new Build()).MODEL);
                            request14.add("token",MD5.MD5s(DataManager.toComplain.data.commentList.get(0).COMPLAINTID+ (new Build()).MODEL));
                            request14.add("KeyNo",DataManager.toComplain.data.commentList.get(0).COMPLAINTID);
                            request14.add("memberId",csp.getID());
                            request14.add("Type","投诉");
                            request14.add("attchmentDesc",attchmentDescS);//图片描述内容，多文件以@符号分割
                            request14.add("attchmentSteam",attchmentSteamS);//base64码内容，多文件以@符号分割
                            CallServer.getInstance().add(ToComplaintActivity.this, request14, MyhttpCallBack.getInstance(), 0x992, true, false, true);
                        }else{
                            pd.dismiss();
                            Toast.show("提交投诉成功");
                            finish();
                        }

                        break;
                    default:break;
                }
                super.handleMessage(msg);
            }
        };
    }

    public void init() {
        pd=new ProgressDialog(ToComplaintActivity.this);
        pd.setMessage("提交数据中，请稍后...");
        pd.setCancelable(false);
        b_topname.setText("我要投诉");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        builder= new AlertDialog.Builder(this);
        builder.setTitle("请选择上传方式：");
        final String[] items = new String[]{"本地", "拍照"};
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                imgs1=new Drawable[9];
                switch (items[which]) {
                    case "本地":
                        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                        // 如果朋友们要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(pickIntent, REQUESTCODE_PICK);
                        break;
                    case "拍照":
                        Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        //下面这句指定调用相机拍照后的照片存储的路径
                        takeIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
                        startActivityForResult(takeIntent, REQUESTCODE_TAKE);
                        break;

                    default:
                        break;
                }
                //关闭对话框
                dialog.dismiss();
            }
        });


        com_et_conten.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    com_et_num.setText("您还可以输入300个字");
                } else {
                    int num = 300 - s.length();
                    com_et_num.setText("您还可以输入" + num + "个字");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 300) {
                    Toast.show("您输入的字数已经达到300了");
                }
            }
        });
        com_submit.setOnClickListener(listener);
        com_photo.setOnClickListener(listener);

    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.com_submit:
                    if (com_et_title.getText().toString().equals("")) {
                        Toast.show("投诉主题不能为空!");
                    } else if (com_et_conten.getText().toString().equals("")) {
                        Toast.show("投诉内容不能为空!");
                    } else if (com_et_conten.getText().toString().length() < 15) {
                        Toast.show("投诉内容不能少于15字!");
                    } else if (com_et_title.getText().toString().length() < 3) {
                        Toast.show("投诉主题不能少于3个字!");
                    } else {
                        pd.show();
                        GsonUtil ComRequerst = new GsonUtil(URLconstant.URLINSER + URLconstant.SEVECOM, RequestMethod.GET);
                        ComRequerst.add("token", MD5.MD5s(DataManager.BaseinfoList.get(0).EnterAddtionID + new Build().MODEL));
                        ComRequerst.add("KeyNo", DataManager.BaseinfoList.get(0).EnterAddtionID);
                        ComRequerst.add("deviceId", new Build().MODEL);
                        ComRequerst.add("memberId", csp.getID());//csp.getID()
                        ComRequerst.add("complaintTitle", com_et_title.getText().toString());
                        ComRequerst.add("complainComment", com_et_conten.getText().toString());
                        ComRequerst.add("complainTypeId","11");
                        // ComRequerst.add("complainTypeId","");//投诉类型
                        CallServer.getInstance().add(ToComplaintActivity.this, ComRequerst, MyhttpCallBack.getInstance(), 0x993, true, false, true);
                    }
                    break;
                case R.id.com_photo:
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    break;
                default:
                    break;
            }

        }
    };


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
            case REQUESTCODE_TAKE:// 调用相机拍照
                File temp = new File(Environment.getExternalStorageDirectory() + "/" + IMAGE_FILE_NAME);
                startPhotoZoom(Uri.fromFile(temp));
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

            imgs1[i]=drawable;
            MyGridAdapterClaim adapters = new MyGridAdapterClaim(ToComplaintActivity.this, imgs1);
            myGridViewtc.setAdapter(adapters);
            i++;
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //将bitmap一字节流输出 Bitmap.CompressFormat.PNG 压缩格式，100：压缩率，baos：字节流
                photo.compress(Bitmap.CompressFormat.PNG, 100, baos);
                baos.close();
                byte[] buffer = baos.toByteArray();
                //将图片的字节流数据加密成base64字符输出
                String pic= Base64.encodeToString(buffer, 0, buffer.length,Base64.DEFAULT);
                listStirng.add(pic);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
