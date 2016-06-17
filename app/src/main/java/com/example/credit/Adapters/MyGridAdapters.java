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
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Views.BaseViewHolder;

/**
 * @author http://blog.csdn.net/finddreams
 * @Description:gridview的Adapter
 */
public class MyGridAdapters extends BaseAdapter {
    private Context mContext;
    private int[] imgs;

    public MyGridAdapters(Context context, int[] imgs1) {
        super();
        this.mContext = context;
        imgs = imgs1;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (imgs.length % 4 == 0) {
            return imgs.length;
        } else {
            return (4 - imgs.length % 4) + imgs.length;
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
                    R.layout.grid_items, parent, false);

        }
        if (imgs.length % 4 != 0) {
            if ((imgs.length - 1) < position) {
                return convertView;
            }
        }
        ImageView iv = BaseViewHolder.get(convertView, R.id.iv_items);
        iv.setBackgroundResource(imgs[position]);
        TextView tv = BaseViewHolder.get(convertView, R.id.iv_num);
        switch (position) {
            case 0:
                tv.setText(DataManager.allcounts.BaseInfoCount);
                break;
            case 1:
                tv.setText(DataManager.allcounts.ApprovalCount);
                break;
            case 2:
                tv.setText(DataManager.allcounts.HonorCount);
                break;
            case 3:
                tv.setText(DataManager.allcounts.SupportCount);
                break;
            case 4:
                tv.setText(DataManager.allcounts.PledgeCount);
                break;
            case 5:
                tv.setText(DataManager.allcounts.MortgagorCount);
                break;
            case 6:
                tv.setText(DataManager.allcounts.JudiciaryCount);
                break;
            case 7:
                tv.setText(DataManager.allcounts.WarningCount);
                break;
            case 8:
                tv.setText(DataManager.allcounts.PunishCount);
                break;
            case 9:
                tv.setText(DataManager.allcounts.AbnormityCount);
                break;
            case 10:
                tv.setText(DataManager.allcounts.PatentCount);
                break;
            case 11:
                tv.setText(DataManager.allcounts.TrademarkCount);
                break;
            case 12:
                tv.setText(DataManager.allcounts.CopyrightCount);
                break;
            case 13:
                tv.setText(DataManager.allcounts.AdvertisementCount);
                break;
            case 14:
                tv.setText(DataManager.allcounts.CreditCount);
                break;
            case 15:
                tv.setText(DataManager.allcounts.AnnualCount);
                break;
            default:
                break;

        }
        return convertView;
    }

}
