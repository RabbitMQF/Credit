package com.example.credit.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.credit.Activitys.ClaimDetailsActivity;
import com.example.credit.Activitys.MyClaimActivity;
import com.example.credit.Activitys.SearchFirmActivty;
import com.example.credit.Activitys.ToClaimActivity;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.yolanda.nohttp.RequestMethod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import Decoder.BASE64Decoder;

public class MyClaim_listAdapter extends BaseAdapter {
    private Context context;
    private List<DataManager.MyClaimUtils.DataBean.ClaimlistBean> list;
    ViewHolder vh = null;
    String ImgString="";
    int a=0;
    int b=0;
    CreditSharePreferences csp=CreditSharePreferences.getLifeSharedPreferences();
    public MyClaim_listAdapter(Context context) {
        this.context = context;
    }
    public void setDataList(List<DataManager.MyClaimUtils.DataBean.ClaimlistBean> list){
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_my_claim_item, null);
            vh = new ViewHolder();
            vh.myclaim_name=(TextView) view.findViewById(R.id.myclaim_name);
            vh.myclaim_state=(TextView) view.findViewById(R.id.myclaim_state);
            vh.myclaim_base=(TextView) view.findViewById(R.id.myclaim_base);
            vh.myclaim_text=(TextView) view.findViewById(R.id.myclaim_text);
            vh.myclaim_dimss=(TextView) view.findViewById(R.id.myclaim_dimss);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        vh.myclaim_name.setText(list.get(position).ENTERNAME);
        vh.myclaim_state.setText(list.get(position).STATUSNAME);
        vh.myclaim_base.setText(list.get(position).REFUSEREASON);

//        if(list.get(position).STATUS.equals("1")){
//            vh.myclaim_text.setVisibility(View.GONE);
//            vh.myclaim_dimss.setVisibility(View.GONE);
//        }
        /**
         * 附件图片数组
         */
        final Drawable[] imgS=new Drawable[DataManager.MyClaimUtilsModel.data.Claimlist.get(position).AttachmentList.size()];
        for(int i=0;i<DataManager.MyClaimUtilsModel.data.Claimlist.get(position).AttachmentList.size();i++){
            String base64String=DataManager.MyClaimUtilsModel.data.Claimlist.get(position).AttachmentList.get(i).ATTACHMENTPATH;
//            byte[] bydata=Base64.decode(base64String,Base64.DEFAULT);
//            Bitmap bit= BitmapFactory.decodeByteArray(bydata,0,bydata.length);
//            Drawable drawable = new BitmapDrawable(null, bit);
            //=================
            try {
                BASE64Decoder decode = new BASE64Decoder();
                byte[] b = decode.decodeBuffer(base64String);
                System.out.println(new String(b));
                StringBuilder str = new StringBuilder();//不建议用String
                for (byte bs : b) {
                    str.append(Integer.toBinaryString(bs));//转换为二进制
                }
                String imgpath = Environment.getExternalStorageDirectory() + "/ELife" + "/pag"+i+".jpg";
                //把字节数组的图片写到另一个地方
                File apple = new File(imgpath);
                FileOutputStream fos = new FileOutputStream(apple);

                fos.write(b);
                fos.flush();
                fos.close();
                //==============
                File file = new File(imgpath);
                if (file.exists()) {//获取本地图片路径是否存在
                    Bitmap bm = BitmapFactory.decodeFile(imgpath);
                    Drawable drawable = new BitmapDrawable(null, bm);
                    imgS[i]=drawable;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for(int i=0;i<imgS.length;i++){
            ImgString=ImgString+imgS[i]+"&";
        }
        /**
         * 认领详情
         */
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a<b){
                    GsonUtil MyClaimRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.MYCLAIMURL, RequestMethod.GET);
                    MyClaimRuerst.add("deviceId",(new Build()).MODEL);
                    MyClaimRuerst.add("token", SearchFirmActivty.MD5s(csp.getID() + (new Build()).MODEL));
                    MyClaimRuerst.add("KeyNo",csp.getID());
                    MyClaimRuerst.add("claimId",DataManager.MyClaimUtilsModel.data.Claimlist.get(position).CLAIMID);
                    CallServer.getInstance().add(context,MyClaimRuerst, MyhttpCallBack.getInstance(),0x304,true,false,true);
                    Intent i=new Intent(context, ClaimDetailsActivity.class);
                    i.putExtra("position",position);
                    context.startActivity(i);
                }else{
                    Intent i=new Intent(context, ClaimDetailsActivity.class);
                    i.putExtra("position",position);
                    context.startActivity(i);
                }
                a++;
            }
        });

        /**
         *认领取消
         */
        vh.myclaim_dimss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyClaimActivity.wd.show();
                GsonUtil MyClaimRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.DISSCLAIMURL, RequestMethod.GET);
                MyClaimRuerst.add("deviceId",(new Build()).MODEL);
                MyClaimRuerst.add("token", SearchFirmActivty.MD5s(csp.getID() + (new Build()).MODEL));
                MyClaimRuerst.add("KeyNo",csp.getID());
                MyClaimRuerst.add("enterId",list.get(position).ENTERID);
                CallServer.getInstance().add(context,MyClaimRuerst, MyhttpCallBack.getInstance(),0x305,true,false,true);
                b++;
            }
        });
        /**
         *编辑修改
         */
        vh.myclaim_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyClaimActivity.wd.show();
                Intent i=new Intent(context, ToClaimActivity.class);
                i.putExtra("position",position);
                i.putExtra("type",1);
                context.startActivity(i);
            }
        });
        return view;
    }

    public class ViewHolder {
        TextView myclaim_name;//公司名称
        TextView myclaim_state;//法人代表
        TextView myclaim_base;//注册资金
        TextView myclaim_text;//编辑
        TextView myclaim_dimss;//取消
    }
}
