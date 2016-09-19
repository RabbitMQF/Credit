package com.example.credit.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.credit.Activitys.MycomplaintsListActivity;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.yolanda.nohttp.RequestMethod;

import java.util.List;

/**
 * Created by alucard on 2016-07-01.
 * 我的投诉item适配器
 */
public class ComplainListAdapter extends BaseAdapter {
    private Context context;
    ViewHolder vh = null;
    boolean Tag=false;

    private List<DataManager.MyComplaint.DataBean.CommentListBean> ComplainList;

    public ComplainListAdapter(Context context) {
        this.context = context;
    }

    public ComplainListAdapter(Context context, List<DataManager.MyComplaint.DataBean.CommentListBean> ComplainList) {
        this.context = context;
        this.ComplainList = ComplainList;
    }

   public void setTag(){
       this.Tag=true;
   }
    public void setDataList(List<DataManager.MyComplaint.DataBean.CommentListBean> ComplainList){
        this.ComplainList=ComplainList;
    }

    @Override
    public int getCount() {
        return ComplainList.size();
    }

    @Override
    public Object getItem(int position) {
        return ComplainList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_mycomplaints_list_item, null);
            vh = new ViewHolder();
            vh.complain_img= (ImageView) convertView.findViewById(R.id.complain_img);
            vh.complain_firm= (TextView) convertView.findViewById(R.id.complain_firm);
            vh.complain_title= (TextView) convertView.findViewById(R.id.complain_title);
            vh.complain_time= (TextView) convertView.findViewById(R.id.complain_time);
            vh.complain_status= (TextView) convertView.findViewById(R.id.complain_status);
            vh.complain_cancel= (Button) convertView.findViewById(R.id.complain_cancel);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
        //Picasso.with(context).load(ComplainList.get(position).).into(vh.complain_img);
        vh.complain_firm.setText(ComplainList.get(position).ENTERNAME);
        vh.complain_title.setText(ComplainList.get(position).COMPLAINTITLE);
        vh.complain_time.setText(ComplainList.get(position).COMPLAINTIME);
        switch (ComplainList.get(position).COMPLAINSTATUS){
            case "0"://未处理

                vh.complain_status.setTextColor(context.getResources().getColor(R.color.orange));
                vh.complain_status.setText("未处理");
                break;
            case "1"://已处理

                vh.complain_status.setTextColor(context.getResources().getColor(R.color.green));
                vh.complain_status.setText("已审核");
                //vh.complain_cancel.setVisibility(View.GONE);
                break;
            case "2"://已拒绝

                vh.complain_status.setTextColor(context.getResources().getColor(R.color.red));
                vh.complain_status.setText("拒绝审核");
                break;
            default:break;
        }
        //vh.complain_status.setText(ComplainList.get(position).COMPLAINSTATUS);
        if(Tag){
            vh.complain_cancel.setVisibility(View.GONE);
        }
        /**
         * 取消投诉
         */
        vh.complain_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GsonUtil cancelRuerst =new GsonUtil(URLconstant.URLINSER+URLconstant.CANCELCOM, RequestMethod.GET);
                cancelRuerst.add("token", MD5.MD5s(ComplainList.get(position).COMPLAINTID+new Build().MODEL));
                cancelRuerst.add("KeyNo",ComplainList.get(position).COMPLAINTID);
                cancelRuerst.add("deviceId",new Build().MODEL);
                CallServer.getInstance().add(context,cancelRuerst, MyhttpCallBack.getInstance(),0x996,true,false,true);
                MycomplaintsListActivity.pd.show();
            }
        });
        /**
         * Item点击事件
         */
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MycomplaintsListActivity.pd.show();
                GsonUtil DetailQuerst=new GsonUtil(URLconstant.URLINSER+URLconstant.GETCOMDETAIL, RequestMethod.GET);
                DetailQuerst.add("token", MD5.MD5s(ComplainList.get(position).COMPLAINTID+new Build().MODEL));
                DetailQuerst.add("KeyNo",ComplainList.get(position).COMPLAINTID);
                DetailQuerst.add("deviceId",new Build().MODEL);
                CallServer.getInstance().add(context,DetailQuerst, MyhttpCallBack.getInstance(),0x995,true,false,true);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView complain_img;
        TextView complain_firm;
        TextView complain_title;
        TextView complain_time;
        TextView complain_status;
        Button complain_cancel;

    }
}
