package com.example.credit.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Activitys.ToCommentActivity;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.MyListView;
import com.example.credit.Views.RoundImageView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import Decoder.BASE64Decoder;

import static com.example.credit.Utils.base64Util.stringtoBitmap;
import static com.example.credit.Views.FileUtil.decodeBitmap;

public class CommmentAdapter extends BaseAdapter {
    private Context context;
    private List<DataManager.MyCommentlistr.DataBean.UserreviewBean> list;
    ViewHolder vh = null;
    int sum=0;
    public CommmentAdapter(Context context, List<DataManager.MyCommentlistr.DataBean.UserreviewBean> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
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
            view = LayoutInflater.from(context).inflate(R.layout.activity_comment_list_item, null);
            vh = new ViewHolder();
            vh.comm_img=(RoundImageView) view.findViewById(R.id.comm_img);
            vh.comm_name=(TextView) view.findViewById(R.id.comm_name);
            vh.comm_time=(TextView) view.findViewById(R.id.comm_time);
            vh.comm_cont=(TextView) view.findViewById(R.id.comm_cont);
            vh.pl_alreadgood=(ImageView) view.findViewById(R.id.pl_alreadgood);
            vh.pl_good=(ImageView) view.findViewById(R.id.pl_good);
            vh.pl_good_num=(TextView) view.findViewById(R.id.pl_good_num);
            vh.nogood=(ImageView) view.findViewById(R.id.nogood);
            vh.alreadynogood=(ImageView) view.findViewById(R.id.alreadynogood);
            vh.nogood_num=(TextView) view.findViewById(R.id.nogood_num);
            vh.liuyan=(ImageView) view.findViewById(R.id.liuyan);
            vh.liuyan_num=(TextView) view.findViewById(R.id.liuyan_num);
            vh.huifu=(LinearLayout) view.findViewById(R.id.huifu);
            vh.huifu_con=(EditText) view.findViewById(R.id.huifu_con);
            vh.commpl_list=(MyListView) view.findViewById(R.id.commpl_list);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
//@mipmap/alreadygood  好评图片   红色
//@mipmap/good          好评图片 灰色
//@mipmap/alreadynogood   差评 黑色
//@mipmap/nogood  差评 灰色
        String str="";
        if(!list.get(position).ICONPATH.equals("")){
            str=((list.get(position).ICONPATH).substring(list.get(position).ICONPATH.length() - 20, list.get(position).ICONPATH.length()-5)).replaceAll("\\/", "_");

        }
        if(!list.get(position).ICONPATH.equals("")){
            vh.comm_img.setImageBitmap(decodeBitmap(Environment.getExternalStorageDirectory() + "/Credit/cache/"+str+".jpg",35,35));
        }else{
            vh.comm_img.setImageResource(R.mipmap.me_icon02);
        }

//        vh.comm_img.setImageBitmap(stringtoBitmap(list.get(position).ICONPATH));
        vh.comm_name.setText(list.get(position).MEMBERNAME);
        vh.comm_time.setText(list.get(position).CREATETIME);
        vh.comm_cont.setText(list.get(position).CONTENT);
        vh.nogood_num.setText(list.get(position).FAILEDQTY);
        vh.pl_good_num.setText(list.get(position).SUCCESSQTY);

        String s=list.get(position).ISSUCCESS;
        String s2=list.get(position).ISFAILED;
/**
 * /初始化点赞和差评 状态和数量
 */
        if(list.get(position).ISSUCCESS.equals("0")){//是否点赞
            vh.pl_good.setVisibility(View.VISIBLE);
            vh.pl_alreadgood.setVisibility(View.GONE);
        }else{
            vh.pl_good.setVisibility(View.GONE);
            vh.pl_alreadgood.setVisibility(View.VISIBLE);
        }
        if(list.get(position).ISFAILED.equals("0")){//是否吐槽
            vh.nogood.setVisibility(View.VISIBLE);
            vh.alreadynogood.setVisibility(View.GONE);
        }else{
            vh.nogood.setVisibility(View.GONE);
            vh.alreadynogood.setVisibility(View.VISIBLE);
        }
//   /**
//    * /对点赞和差评 状态和数量 进行改变请求
//   */
//
//        /**
//         * 好评icon点击事件
//         */
//        if(vh.pl_alreadgood.getVisibility()==View.VISIBLE){
//            vh.pl_alreadgood.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    vh.pl_alreadgood.setVisibility(View.GONE);
//                    vh.pl_good.setVisibility(View.VISIBLE);
//                    vh.pl_good_num.setText(Integer.parseInt(list.get(position).SUCCESSQTY.trim())-1+"");
//                }
//            });
//            vh.pl_good.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    vh.pl_good.setVisibility(View.GONE);
//                    vh.pl_alreadgood.setVisibility(View.VISIBLE);
//                    vh.pl_good_num.setText(Integer.parseInt(list.get(position).SUCCESSQTY.trim())+"");
//                }
//            });
//        }else{
//            vh.pl_alreadgood.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    vh.pl_alreadgood.setVisibility(View.GONE);
//                    vh.pl_good.setVisibility(View.VISIBLE);
//                    vh.pl_good_num.setText(Integer.parseInt(list.get(position).SUCCESSQTY.trim())+"");
//                }
//            });
//            vh.pl_good.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    vh.pl_good.setVisibility(View.GONE);
//                    vh.pl_alreadgood.setVisibility(View.VISIBLE);
//                    vh.pl_good_num.setText(Integer.parseInt(list.get(position).SUCCESSQTY.trim())+1+"");
//                }
//            });
//        }
//
//        /**
//         * 差评icon点击事件
//         */
//        if(vh.alreadynogood.getVisibility()==View.VISIBLE){
//            vh.alreadynogood.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    vh.alreadynogood.setVisibility(View.GONE);
//                    vh.nogood.setVisibility(View.VISIBLE);
//                    vh.nogood_num.setText(Integer.parseInt(list.get(position).FAILEDQTY.trim())-1+"");
//                }
//            });
//            vh.nogood.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    vh.nogood.setVisibility(View.GONE);
//                    vh.alreadynogood.setVisibility(View.VISIBLE);
//                    vh.nogood_num.setText(Integer.parseInt(list.get(position).FAILEDQTY.trim())+"");
//                }
//            });
//        }else{
//            vh.alreadynogood.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    vh.alreadynogood.setVisibility(View.GONE);
//                    vh.nogood.setVisibility(View.VISIBLE);
//                    vh.nogood_num.setText(Integer.parseInt(list.get(position).FAILEDQTY.trim())+"");
//                }
//            });
//            vh.nogood.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    vh.nogood.setVisibility(View.GONE);
//                    vh.alreadynogood.setVisibility(View.VISIBLE);
//                    vh.nogood_num.setText(Integer.parseInt(list.get(position).FAILEDQTY.trim())+1+"");
//                }
//            });
//        }
        /**
         * 绑定评论的回复列表
         */
        if(DataManager.replay2reviewList!=null){
            vh.liuyan_num.setText(list.get(position).replay2review.size()+"");
            Commment_ItemlistAdapter adapter=new Commment_ItemlistAdapter(context,list.get(position).replay2review);
            vh.commpl_list.setAdapter(adapter);
        }
        return view;
    }



    public class ViewHolder {
        RoundImageView comm_img;//用户头像
        TextView comm_name;//用户姓名
        TextView comm_time;//评论时间
        TextView comm_cont;//评论内容
        ImageView pl_alreadgood;//已赞icon
        ImageView pl_good;//未赞icon
        TextView pl_good_num;//已赞size
        ImageView alreadynogood;//差评黑
        ImageView nogood;//差评灰
        TextView nogood_num;//差评size
        ImageView liuyan;//留言
        TextView liuyan_num;//留言size
        LinearLayout huifu;//留言输入框
        EditText huifu_con;//留言内容
        MyListView commpl_list;
    }
}
