package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Utils.Toast;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import Decoder.BASE64Decoder;

import static com.example.credit.Views.FileUtil.saveBitmap;

/**
 * 二维码详情页
 */
public class TwoDimActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.towd)
    ImageView towd;
    @ViewInject(R.id.xiazai)
    Button xiazai;
    @ViewInject(R.id.fenxiang)
    Button fenxiang;
    File file1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_dim);
        ViewUtils.inject(this);
        init();
    }
    public void init() {
        file1 = new File(Environment.getExternalStorageDirectory() + "/Credit/cache/"+DataManager.BaseinfoList.get(0).REGNO+"_TwoDimImg.jpg");
        if (file1.exists()) {//获取本地图片路径是否存在
            Picasso.with(TwoDimActivity.this).load(file1).into(towd);
        }else{
            try {
                BASE64Decoder decode = new BASE64Decoder();
                byte[] b = decode.decodeBuffer(DataManager.TwoDimSli.data.result);
//                System.out.println(new String(b));
//                StringBuffer str = new StringBuffer();//不建议用String
//                for (byte bs : b) {
//                    str.append(Integer.toBinaryString(bs));//转换为二进制
//                }
                //把字节数组的图片写到另一个地方
                File apple = new File(Environment.getExternalStorageDirectory() + "/Credit/cache/"+DataManager.BaseinfoList.get(0).REGNO+"_TwoDimImg.jpg");
                FileOutputStream fos = new FileOutputStream(apple);
                fos.write(b);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Picasso.with(TwoDimActivity.this).load(file1).into(towd);
        }

        b_topname.setText("我的名片");

        b_return.setOnClickListener(listener);
        xiazai.setOnClickListener(listener);
        fenxiang.setOnClickListener(listener);
    }
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.xiazai:
                    String strD = Environment.getExternalStorageDirectory() + "/Credit/TwoDimImg/" + DataManager.BaseinfoList.get(0).ENTNAME + "_TwoDimImg.jpg";
                    File file = new File(strD);
                    if (file.exists()) {//获取本地图片路径是否存在
                        Toast.show("图片已保存到到:" + strD);
                    }else {
                        Bitmap image = ((BitmapDrawable)towd.getDrawable()).getBitmap();
                        saveBitmap(image,strD);
                        Toast.show("图片已保存到到:" + strD);
                    }

                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri uri1 = Uri.fromFile(file);
                    intent.setData(uri1);
                    TwoDimActivity.this.sendBroadcast(intent);//发送广播更新图库
                    break;
                case R.id.fenxiang:
                    //Toast.show("功能开发中...");
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("image/*");
                    Uri uri = Uri.fromFile(file1);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                    startActivity(Intent.createChooser(shareIntent, "选择分享方式"));
                    break;
                case R.id.b_return:
                    finish();
                    break;
            }
        }
    };
}
