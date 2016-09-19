package com.example.credit.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.BaseViewHolder;

/**
 * @author http://blog.csdn.net/finddreams
 * @Description:gridview的Adapter
 */
public class MyGridAdaptermMain extends BaseAdapter {
    private Context mContext;
    private int[] imgs;
    private String[] txt;

    public MyGridAdaptermMain(Context context, int[] imgs1,String[] txt1) {
        super();
        this.mContext = context;
        imgs = imgs1;
        txt = txt1;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (imgs.length % 2 == 0) {
            return imgs.length;
        } else {
            return (2 - imgs.length % 2) + imgs.length;
        }
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.mains_grid_item1, parent, false);

        }
        if (imgs.length % 2 != 0) {
            if ((imgs.length - 1) < position) {
                return convertView;
            }
        }
        ImageView iv = BaseViewHolder.get(convertView, R.id.iv_img);
        TextView tv = BaseViewHolder.get(convertView, R.id.iv_txt);
        iv.setBackgroundResource(imgs[position]);
        tv.setText(txt[position]);

        return convertView;
    }

}
