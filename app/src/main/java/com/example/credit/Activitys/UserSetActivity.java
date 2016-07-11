package com.example.credit.Activitys;


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
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.FileUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * 用户设置界面
 */
public class UserSetActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.us1)
    RelativeLayout us1;
    @ViewInject(R.id.us_headimg)
    ImageView us_headimg;
    private static final int REQUESTCODE_PICK = 0;        // 相册选图标记
    private static final int REQUESTCODE_CUTTING = 2;    // 图片裁切标记
    String pic;

    @ViewInject(R.id.us2)
    RelativeLayout us2;
    @ViewInject(R.id.us_name)
    TextView us_name;

    @ViewInject(R.id.us3)
    RelativeLayout us3;
    @ViewInject(R.id.us_sex)
    TextView us_sex;
    String sexs="";

    @ViewInject(R.id.us4)
    RelativeLayout us4;
    @ViewInject(R.id.us_emils)
    TextView us_emils;

    @ViewInject(R.id.us5)
    RelativeLayout us5;
    @ViewInject(R.id.us_hangye)
    TextView us_hangye;

    @ViewInject(R.id.us6)
    RelativeLayout us6;
    @ViewInject(R.id.us_xueli)
    TextView us_xueli;

    @ViewInject(R.id.us7)
    RelativeLayout us7;
    @ViewInject(R.id.us_phone)
    TextView us_phone;
    CreditSharePreferences csf;
    public static Handler handler;
    WaitDialog wd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_set);
        ViewUtils.inject(this);
        wd=new WaitDialog(this);
        csf=CreditSharePreferences.getLifeSharedPreferences();
        init();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                wd.dismiss();
               switch (msg.what){
                   case 1:
                       Toast.show("修改信息成功！");
                       csf.putICONSTEAM(pic);//保存图片
                       finish();
                       break;
                   case 2:
                       Toast.show("修改信息失败！");
                       break;
               }
            }
        };
    }

    public void init(){
        b_topname.setText("用户设置");
        b_return.setOnClickListener(listener);
        /**
         * 获取用户头像
         */
        File file = new File(Environment.getExternalStorageDirectory() + "/Credit/loginImg.jpg");
        if (file.exists()) {//获取本地图片路径是否存在
            Bitmap bm = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/Credit/loginImg.jpg");
            us_headimg.setImageBitmap(bm);
        }
        us1.setOnClickListener(listener);
        /**
         * 获取用户昵称
         */
        us_name.setText(csf.getALIASNAME());
        /**
         * 获取用户性别
         */
        if(csf.getSEX().equals("0")){
            us_sex.setText("女");
            sexs="0";
        }else{
            us_sex.setText("男");
            sexs="1";
        }

        /**
         * 获取用户邮箱
         */
        us_emils.setText(csf.getEMAIL());
        /**
         * 获取用户行业
         */
        us_hangye.setText(csf.getINDUSTRY());
        /**
         * 获取用户学历
         */
        us_xueli.setText(csf.getEDUCATION());
        /**
         * 获取用户手机
         */
        us_phone.setText(csf.getMOBILE());
    }
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.b_return:
                    wd.show();
                    GsonUtil MyClaimRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.REVISEUSER, RequestMethod.POST);
                    MyClaimRuerst.add("deviceId", (new Build()).MODEL);
                    MyClaimRuerst.add("token", SearchFirmActivty.MD5s(csf.getID() + (new Build()).MODEL));
                    MyClaimRuerst.add("KeyNo", csf.getID());
                    if(!us_emils.getText().toString().equals(csf.getEMAIL())){
                        MyClaimRuerst.add("Email", us_emils.getText().toString());//邮箱
                    }
                    if(!us_name.getText().toString().equals(csf.getALIASNAME())) {
                        MyClaimRuerst.add("aliasName", us_name.getText().toString());//别名
                    }
                    if(!sexs.equals(csf.getSEX())) {
                        MyClaimRuerst.add("sex", sexs);//性别
                    }
                    if(!us_hangye.getText().toString().equals(csf.getINDUSTRY())) {
                        MyClaimRuerst.add("industryId", us_hangye.getText().toString());//行业
                    }
                    if(!us_xueli.getText().toString().equals(csf.getEDUCATION())) {
                        MyClaimRuerst.add("educationId", us_xueli.getText().toString());//教育
                    }
                    if(!pic.equals(csf.getICONSTEAM())) {
                        MyClaimRuerst.add("iconSteam", pic);//头像base64位图
                    }
                    if(!us_phone.getText().toString().equals(csf.getMOBILE())) {
                        MyClaimRuerst.add("mobile",us_phone.getText().toString());//移动电话
                    }

                    MyClaimRuerst.add("openType", "1");//0：注册  1：修改(必须)

                    //                    MyClaimRuerst.add("password", "86D9D7F53FCA45DD93E2D83DFCA0CB42");//用户密码
                    CallServer.getInstance().add(UserSetActivity.this, MyClaimRuerst, MyhttpCallBack.getInstance(), 0x401, true, false, true);
                    break;
                case R.id.us1:
                    Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                    // 如果朋友们要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                    pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(pickIntent, REQUESTCODE_PICK);
                    break;
                case R.id.us2:
                    break;
                case R.id.us3:
                    break;
                case R.id.us4:
                    break;
                case R.id.us5:
                    break;
                case R.id.us6:
                    break;
                case R.id.us7:
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
//            Drawable drawable = new BitmapDrawable(null, photo);
            us_headimg.setImageBitmap(photo);
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //将bitmap一字节流输出 Bitmap.CompressFormat.PNG 压缩格式，100：压缩率，baos：字节流
                photo.compress(Bitmap.CompressFormat.PNG, 100, baos);
                baos.close();
                byte[] buffer = baos.toByteArray();
                //将图片的字节流数据加密成base64字符输出
                pic= Base64.encodeToString(buffer, 0, buffer.length,Base64.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
