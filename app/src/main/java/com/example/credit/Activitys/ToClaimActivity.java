package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Adapters.MyGridAdapterClaim;
import com.example.credit.Adapters.MyGridAdapters;
import com.example.credit.Dialogs.enclosure_dialog;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Utils.base64Util;
import com.example.credit.Views.FileUtil;
import com.example.credit.Views.MyGridView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 企业认领界面
 */
public class ToClaimActivity extends Activity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.claim_emils)
    EditText claim_emils;//邮箱
    @ViewInject(R.id.claim_phone)
    EditText claim_phone;//手机号
    @ViewInject(R.id.claim_details)
    EditText claim_details;//描述详情
    @ViewInject(R.id.claim_details_num)
    TextView claim_details_num;//描述详情字数
    @ViewInject(R.id.claim_fj)
    ImageView claim_fj;//附件
    @ViewInject(R.id.claim_btn)
    LinearLayout claim_btn;//提交
    @ViewInject(R.id.myGridViewtc)
    MyGridView myGridViewtc;//图片gridview

    public static Handler handler;

    /**
     * 附件
     * @param savedInstanceState
     */
    private static String newName ="UserImg.jpg";
    private String resultStr = "";	// 服务端返回结果集
    private static final String IMAGE_FILE_NAME = "avatarImage.jpg";// 头像文件名称
    private static String urlpath;			// 图片本地路径
    private static final int REQUESTCODE_PICK = 0;		// 相册选图标记
    private static final int REQUESTCODE_TAKE = 1;		// 相机拍照标记
    private static final int REQUESTCODE_CUTTING = 2;	// 图片裁切标记

    enclosure_dialog ed;
    public Drawable[] imgs1 =new Drawable[9];
    List<String> listStirng=new ArrayList<>();
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_claim);
        ViewUtils.inject(this);
        init();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        if(imgs1.length>0){
                            String attchmentDescS="";
                            String attchmentSteamS="";
                            for(int j=0;j<i;j++){
                                attchmentDescS=attchmentDescS+"pic@";
                            }
                            for(int c=0;c<listStirng.size();c++){
                                attchmentSteamS=attchmentSteamS+listStirng.get(c)+"@";
                            }
                            String a=(new Build()).MODEL;
                            String b=SearchFirmActivty.MD5s(DataManager.ClaimUtilsModel.data.CLAIMID + (new Build()).MODEL);
                            String d=DataManager.ClaimUtilsModel.data.CLAIMID;
                            GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.ENCLOSUREURL, RequestMethod.POST);
                            request14.add("deviceId",(new Build()).MODEL);
                            request14.add("token",SearchFirmActivty.MD5s(DataManager.ClaimUtilsModel.data.CLAIMID + (new Build()).MODEL));
                            request14.add("KeyNo",DataManager.ClaimUtilsModel.data.CLAIMID);
                            request14.add("memberId","86D9D7F53FCA45DD93E2D83DFCA0CB42");
                            request14.add("Type","认领企业");
                            request14.add("attchmentDesc",attchmentDescS);//图片描述内容，多文件以@符号分割
                            request14.add("attchmentSteam",attchmentSteamS);//base64码内容，多文件以@符号分割
                            CallServer.getInstance().add(ToClaimActivity.this, request14, MyhttpCallBack.getInstance(), 0x302, true, false, true);
                        }else{
                            Toast.show("附件不能为空!!");
                        }
                        break;
                    case 2:
                        finish();
                        Toast.show("企业认领提交成功，请等待验证!");
                        break;
                    case 500:
                        Toast.show("数据提交失败!");
                        break;
                }
            }
        };

    }
    public void init(){
        b_topname.setText("企业认领");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one,R.anim.finish_tran_two);
            }
        });

        claim_details.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    claim_details_num.setText("您还可以输入300个字");
                }else{
                    int num=300-s.length();
                    claim_details_num.setText("您还可以输入"+num+"个字");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==300){
                    android.widget.Toast.makeText(ToClaimActivity.this, "您输入的字数已经达到300了", android.widget.Toast.LENGTH_SHORT).show();
                }
            }
        });
        claim_btn.setOnClickListener(listener);
        claim_fj.setOnClickListener(listener);
    }
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.claim_btn:
                    if(claim_emils.getText().toString().equals("")){
                        Toast.show("邮箱地址不能为空!");
                    }else if(claim_phone.getText().toString().equals("")){
                        Toast.show("手机号码不能为空!");
                    }else if(claim_details.getText().toString().equals("")){
                        Toast.show("描述详情不能为空!");
                    }else if((claim_phone.getText().toString()).length()>13){
                        Toast.show("手机号码格式错误!");
                    }else{
                        GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.CLAIMURL, RequestMethod.GET);
                        request14.add("deviceId",(new Build()).MODEL);
                        request14.add("token",SearchFirmActivty.MD5s(DataManager.BaseinfoList.get(0).EnterAddtionID + (new Build()).MODEL));
                        request14.add("KeyNo",DataManager.BaseinfoList.get(0).EnterAddtionID);
                        request14.add("memberId","86D9D7F53FCA45DD93E2D83DFCA0CB42");
                        request14.add("email",claim_emils.getText().toString());
                        request14.add("description",claim_details.getText().toString());
                        request14.add("telphone",claim_phone.getText().toString());
                        request14.add("openType","0");//0为添加，1为修改
                        CallServer.getInstance().add(ToClaimActivity.this, request14, MyhttpCallBack.getInstance(), 0x301, true, false, true);
                    }
                    break;
                case R.id.claim_fj:
                    ed = new enclosure_dialog(ToClaimActivity.this, new View.OnClickListener() {
                        @Override
                        public void onClick(View arg0) {
                            ed.dismiss();
                            switch (arg0.getId()){
                                case R.id.dialog_bd://本地选取附件
                                    Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                                    // 如果朋友们要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                                    pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                    startActivityForResult(pickIntent, REQUESTCODE_PICK);
                                    Toast.show("本地附件");
                                    break;
                                case R.id.dialog_zx://照相选取附件
                                    Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    //下面这句指定调用相机拍照后的照片存储的路径
                                    takeIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                            Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
                                    startActivityForResult(takeIntent, REQUESTCODE_TAKE);
                                    Toast.show("实时附件");
                                    break;
                            }
                        }
                    });
                    ed.showAtLocation(ToClaimActivity.this.findViewById(R.id.cl), Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
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
            MyGridAdapterClaim adapters = new MyGridAdapterClaim(ToClaimActivity.this, imgs1);
            myGridViewtc.setAdapter(adapters);
            i++;
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //将bitmap一字节流输出 Bitmap.CompressFormat.PNG 压缩格式，100：压缩率，baos：字节流
                photo.compress(Bitmap.CompressFormat.PNG, 100, baos);
                baos.close();
                byte[] buffer = baos.toByteArray();
                //将图片的字节流数据加密成base64字符输出
                String pic=Base64.encodeToString(buffer, 0, buffer.length,Base64.DEFAULT);
                listStirng.add(pic);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //判断email格式是否正确
    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }
}
