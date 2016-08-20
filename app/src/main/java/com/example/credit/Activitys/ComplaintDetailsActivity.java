package com.example.credit.Activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
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
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import Decoder.BASE64Decoder;

import static com.example.credit.Views.FileUtil.decodeBitmap;

/**
 * 投诉详情界面
 */
public class ComplaintDetailsActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.com_title)
    TextView com_title;//投诉主题
//    @ViewInject(R.id.complain_er)
//    TextView complain_er;//投诉人
    @ViewInject(R.id.complain_qiye)
    TextView complain_qiye;//投诉企业
    @ViewInject(R.id.complain_time)
    TextView complain_time;////投诉时间
    @ViewInject(R.id.complain_detail)
    TextView complain_detail;
    @ViewInject(R.id.myGridViewtcDD2)
    MyGridView myGridViewtcDD2;
    String [] imgS;//图片数组
    // int position;//下标
//    @ViewInject(R.id.mlgb)
//    ImageView mlgb;
    //Bitmap b;
    //String temps="data:image/png;base64,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";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_details);
        ViewUtils.inject(this);
        init();
//        Drawable d=getResources().getDrawable(R.mipmap.main_cfr);
//        BitmapDrawable bd= (BitmapDrawable) d;
//        Bitmap b=bd.getBitmap();
//
//        mlgb.setImageBitmap(b);
        //String[] t= temps.split("=");
////        byte[] imageByte=Base64.decode(temps,Base64.DEFAULT);
////        Bitmap b=BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
        //    BitmapDrawable bd= new BitmapDrawable(b);
       /* Uri u=Uri.parse(temps);
        Picasso.with(this).load(u).into(mlgb);*/
        // mlgb.setImageBitmap(b);
    }

    public void init() {
        hasSdcard();
        b_topname.setText("投诉详情");
        com_title.setTextColor(getResources().getColor(R.color.black));
        com_title.setText(DataManager.complaintDetail.data.UserComplain.COMPLAINTTITLE);

//        complain_er.setTextColor(getResources().getColor(R.color.black));
//        complain_er.setText(DataManager.complaintDetail.data.UserComplain.MEMBERNAME);

        complain_qiye.setTextColor(getResources().getColor(R.color.black));
        complain_qiye.setText(DataManager.complaintDetail.data.UserComplain.ENTERNAME);

        complain_time.setTextColor(getResources().getColor(R.color.black));
        complain_time.setText(DataManager.complaintDetail.data.UserComplain.COMPLAINTIME);

        complain_detail.setTextColor(getResources().getColor(R.color.black));
        complain_detail.setText(DataManager.complaintDetail.data.UserComplain.COMPLAINCOMMENT);

        imgS = new String[DataManager.complaintDetail.data.AttachmentList.size()];
        /**
         * 循环加载9图片
         */
        for (int i = 0; i < DataManager.complaintDetail.data.AttachmentList.size(); i++) {
            String base64 = DataManager.complaintDetail.data.AttachmentList.get(i).ATTACHMENTPATH;
            if(!base64.equals("")){
                try {
                    BASE64Decoder decode = new BASE64Decoder();
                    byte[] b = decode.decodeBuffer(base64);
                    //System.out.println(new String(b));

                   /* byte [] byt =new byte[1024];
                    for (input.read(byt)!=-1){
                        output.write(byt);
                    }*/


//                    StringBuffer str = new StringBuffer();//不建议用String
//                    for (byte bs : b) {
//                        str.append(Integer.toBinaryString(bs));//转换为二进制
//                    }
                    String path=Environment.getExternalStorageDirectory() + "/Credit/cache/";
                    String imgpath = Environment.getExternalStorageDirectory() + "/Credit/cache" + "/pag" + i + ".jpg";
                    //把字节数组的图片写到另一个地方
                    File apple2=new File(path);
                    if (!apple2.exists()) {
                        apple2.mkdir();
                    }
                    File apple = new File(imgpath);
                    FileOutputStream fos = new FileOutputStream(apple);

                    fos.write(b);
                    fos.flush();
                    fos.close();
                    //==============
                    File file = new File(imgpath);
                    if (file.exists()) {//获取本地图片路径是否存在
//                        Bitmap bm = BitmapFactory.decodeFile(imgpath);
//                        Drawable drawable = new BitmapDrawable(null, bm);
                        imgS[i] = imgpath;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        MyGridAdapterClaim adapters = new MyGridAdapterClaim(ComplaintDetailsActivity.this, imgS);
        myGridViewtcDD2.setAdapter(adapters);
        MycomplaintsListActivity.pd.dismiss();
        myGridViewtcDD2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //StringBuffer head = new StringBuffer("data:img/jpg;base64,");
        //StringBuffer body = head.append(DataManager.complaintDetail.data.AttachmentList.get(0).ATTACHMENTPATH);

        /*Bitmap bitmap = null;
        byte[] bitmapArray;
        bitmapArray = Base64.decode(temps, Base64.DEFAULT);
        bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);*/
        //com_img_1.setImageBitmap(base64Util.stringtoBitmap(temps));
        //com_img_1.setImageURI(a);


    }

    /**
     * 1、判断SD卡是否存在
     * 2
     * 3
     */
    public static boolean hasSdcard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
