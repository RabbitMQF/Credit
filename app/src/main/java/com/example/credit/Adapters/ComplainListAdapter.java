package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by alucard on 2016-07-01.
 */
public class ComplainListAdapter extends BaseAdapter {
    private Context context;
    private List<DataManager.MyComplaint.DataBean.CommentListBean> ComplainList;

    public ComplainListAdapter(Context context, List<DataManager.MyComplaint.DataBean.CommentListBean> ComplainList) {
        this.context = context;
        this.ComplainList = ComplainList;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
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
        vh.complain_status.setText(ComplainList.get(position).COMPLAINSTATUS);
        vh.complain_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
