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
                if(DataManager.allcountsList.get(0).BaseInfoCount.equals("0")){
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).BaseInfoCount)+"");
                break;
            case 1:
                if(DataManager.allcountsList.get(0).ApprovalCount.equals("0")){
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).ApprovalCount)+"");
                break;
            case 2:
                if(DataManager.allcountsList.get(0).HonorCount.equals("0")){
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).HonorCount)+"");
                break;
            case 3:
                if(DataManager.allcountsList.get(0).SupportCount.equals("0")){
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).SupportCount)+"");
                break;
            case 4:
                if(DataManager.allcountsList.get(0).PledgeCount.equals("0")){
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).PledgeCount)+"");
                break;
            case 5:
                if(DataManager.allcountsList.get(0).MortgagorCount.equals("0")){
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).MortgagorCount)+"");
                break;
            case 6:
                if(DataManager.allcountsList.get(0).JudiciaryCount.equals("0")){
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).JudiciaryCount)+"");
                break;
            case 7:
                if(DataManager.allcountsList.get(0).WarningCount.equals("0")){
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).WarningCount)+"");
                break;
            case 8:
                if(DataManager.allcountsList.get(0).PunishCount.equals("0")){
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).PunishCount)+"");
                break;
            case 9:
                if(DataManager.allcountsList.get(0).AbnormityCount.equals("0")){
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).AbnormityCount)+"");
                break;
            case 10:
                if(DataManager.allcountsList.get(0).PatentCount.equals("0")){
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).PatentCount)+"");
                break;
            case 11:
                if(DataManager.allcountsList.get(0).TrademarkCount.equals("0")){
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).TrademarkCount)+"");
                break;
            case 12:
                if(DataManager.allcountsList.get(0).CopyrightCount.equals("0")){
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).CopyrightCount)+"");
                break;
            case 13:
                if(DataManager.allcountsList.get(0).AdvertisementCount.equals("0")){
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).AdvertisementCount)+"");
                break;
            case 14:
                if(DataManager.allcountsList.get(0).CreditCount.equals("0")){
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).CreditCount)+"");
                break;
            case 15:
                if(DataManager.allcountsList.get(0).AnnualCount.equals("0")){
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).AnnualCount)+"");
                break;
            default:
                break;

        }
        return convertView;
    }

}
