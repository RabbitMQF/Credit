package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CC_List_itemAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private String str;
    List<String> imgUrl;
    public CC_List_itemAdapter(Context context, List<String> list, String str,List<String> imgUrl) {
        this.context = context;
        this.list = list;
        this.str = str;
        this.imgUrl=imgUrl;
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder vh = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_c_list_item, null);
            vh = new ViewHolder();
            vh.punlic_1 = (RelativeLayout) view.findViewById(R.id.punlic_1);
            vh.im = (ImageView) view.findViewById(R.id.im);
            vh.cl_tv1 = (TextView) view.findViewById(R.id.cl_tv1);
            vh.cl_tv2 = (TextView) view.findViewById(R.id.cl_tv2);

            vh.punlic_2 = (FrameLayout) view.findViewById(R.id.punlic_2);
            vh.cl2_tv1 = (TextView) view.findViewById(R.id.cl2_tv1);
            vh.cl2_tv2 = (TextView) view.findViewById(R.id.cl2_tv2);
            vh.cl2_tv3 = (TextView) view.findViewById(R.id.cl2_tv3);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        if (str.equals("punish")) {//行政处罚
            vh.cl_tv1.setVisibility(View.VISIBLE);
            vh.cl_tv2.setVisibility(View.VISIBLE);
            vh.cl_tv1.setText("行政处罚文书");
            vh.cl_tv2.setText(list.get(position));

        } else if (str.equals("trademark")) {//商标信息
            vh.im.setVisibility(View.VISIBLE);
            vh.cl_tv1.setVisibility(View.VISIBLE);
            vh.cl_tv1.setText(list.get(position));
            if(imgUrl !=null && imgUrl.size()>0){
                Picasso.with(context).load(imgUrl.get(position)).into(vh.im);
            }
        } else if (str.equals("judicial")) {//司法信息
//            vh.im.setVisibility(View.VISIBLE);
            vh.cl_tv1.setVisibility(View.VISIBLE);
            vh.cl_tv1.setText(list.get(position));

        } else if (str.equals("copyright")) {//著作权
            vh.punlic_1.setVisibility(View.GONE);
            vh.punlic_2.setVisibility(View.VISIBLE);
            vh.cl2_tv1.setText(list.get(position));
            vh.cl2_tv2.setText(imgUrl.get(position));
            vh.cl2_tv3.setText(DataManager.copyrightInfoeList.get(position).WORKCLASS);

        }else if (str.equals("patent")) {//专利信息
            vh.cl_tv1.setVisibility(View.VISIBLE);
            vh.cl_tv2.setVisibility(View.VISIBLE);
            vh.cl_tv1.setText("专利名称");
            vh.cl_tv2.setText(list.get(position));

        }else if (str.equals("pledge")) {//出质信息
            vh.cl_tv1.setVisibility(View.VISIBLE);
            vh.cl_tv2.setVisibility(View.VISIBLE);
            vh.cl_tv1.setText("登记编号");
            vh.cl_tv2.setText(list.get(position));

        }
        return view;
    }

    public class ViewHolder {
        RelativeLayout punlic_1;
        FrameLayout punlic_2;
        ImageView im;
        TextView cl_tv1;
        TextView cl_tv2;

        TextView cl2_tv1;
        TextView cl2_tv2;
        TextView cl2_tv3;
    }
}
