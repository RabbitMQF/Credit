package com.example.credit.Activitys;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Adapters.MyGridAdapterClaim;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Utils.base64Util;
import com.example.credit.Views.MyGridView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import Decoder.BASE64Decoder;

/**
 * 认领详情界面
 */
public class ClaimDetailsActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.cm_name)
    TextView cm_name;//企业名称
    @ViewInject(R.id.cm_time)
    TextView cm_time;//认领时间
    @ViewInject(R.id.cm_state)
    TextView cm_state;//认领状态
    @ViewInject(R.id.cm_emall)
    TextView cm_emall;//邮箱地址
    @ViewInject(R.id.cm_phone)
    TextView cm_phone;//电话号码
    @ViewInject(R.id.cm_details)
    TextView cm_details;//描述详情
    @ViewInject(R.id.myGridViewtcDD)
    MyGridView myGridViewtcDD;//描述详情

    int position;
    String [] imgS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_details);
        ViewUtils.inject(this);
        Intent i=getIntent();
        position=i.getIntExtra("position",0);
        init();
    }
    public void init(){
        b_topname.setText("认领详情");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cm_name.setText(DataManager.MyClaimUtilsModel.data.Claimlist.get(position).ENTERNAME);

        cm_time.setText(DataManager.MyClaimUtilsModel.data.Claimlist.get(position).CLAIMTIME);

        cm_state.setText(DataManager.MyClaimUtilsModel.data.Claimlist.get(position).STATUSNAME);

        cm_emall.setText(DataManager.MyClaimUtilsModel.data.Claimlist.get(position).EMAIL);

        cm_phone.setText(DataManager.MyClaimUtilsModel.data.Claimlist.get(position).TELPHONE);

        cm_details.setText(DataManager.MyClaimUtilsModel.data.Claimlist.get(position).DESCRIPTION);

        imgS=new String[DataManager.MyClaimUtilsModel.data.Claimlist.get(position).AttachmentList.size()];
        for(int i=0;i<DataManager.MyClaimUtilsModel.data.Claimlist.get(position).AttachmentList.size();i++){
            String base64String=DataManager.MyClaimUtilsModel.data.Claimlist.get(position).AttachmentList.get(i).ATTACHMENTPATH;
//            byte[] bydata=Base64.decode(base64String,Base64.DEFAULT);
//            Bitmap bit= BitmapFactory.decodeByteArray(bydata,0,bydata.length);
//            Drawable drawable = new BitmapDrawable(null, bit);
            //=================
            try {
                BASE64Decoder decode = new BASE64Decoder();
                byte[] b = decode.decodeBuffer(base64String);
//                System.out.println(new String(b));
//                StringBuffer str = new StringBuffer();//不建议用String
//                for (byte bs : b) {
//                    str.append(Integer.toBinaryString(bs));//转换为二进制
//                }

                String imgpath =Environment.getExternalStorageDirectory() + "/Credit/cache" + "/pag"+i+".jpg";

                //把字节数组的图片写到另一个地方
                File apple = new File(imgpath);
                FileOutputStream fos = new FileOutputStream(apple);

                fos.write(b);
                fos.flush();
                fos.close();
                //==============
                File file = new File(imgpath);
                if (file.exists()) {//获取本地图片路径是否存在
//                    Bitmap bm = BitmapFactory.decodeFile(imgpath);
//                    Drawable drawable = new BitmapDrawable(null, bm);
                    imgS[i]=imgpath;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        MyGridAdapterClaim adapters = new MyGridAdapterClaim(ClaimDetailsActivity.this, imgS);
        myGridViewtcDD.setAdapter(adapters);
        MyClaimActivity.wd.dismiss();
        myGridViewtcDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }


}
