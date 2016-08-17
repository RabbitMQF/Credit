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
            case 0://工商信息
                if (DataManager.allcountsList.get(0).BaseInfoCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).BaseInfoCount) + "");
                break;
            case 1://行政审批
                if (DataManager.allcountsList.get(0).ApprovalCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).ApprovalCount) + "");
                break;
            case 2://荣誉
                if (DataManager.allcountsList.get(0).HonorCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).HonorCount) + "");
                break;
            case 3://扶持
                if (DataManager.allcountsList.get(0).SupportCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).SupportCount) + "");
                break;
            case 4://抵押
                if (DataManager.allcountsList.get(0).MortgagorCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).MortgagorCount) + "");
                break;
            case 5://出质
                if (DataManager.allcountsList.get(0).PledgeCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).PledgeCount) + "");
                break;
            case 6://司法信息
                if (DataManager.allcountsList.get(0).JudiciaryCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).JudiciaryCount) + "");
                break;
            case 7://预警信息
                if (DataManager.allcountsList.get(0).WarningCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).WarningCount) + "");
                break;
            case 8://行政处罚
                if (DataManager.allcountsList.get(0).PunishCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).PunishCount) + "");
                break;
            case 9://经营异常
                if (DataManager.allcountsList.get(0).AbnormityCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).AbnormityCount) + "");
                break;
            case 10://专利信息
                if (DataManager.allcountsList.get(0).PatentCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).PatentCount) + "");
                break;
            case 11://商标信息
                if (DataManager.allcountsList.get(0).TrademarkCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).TrademarkCount) + "");
                break;
            case 12://著作权
                if (DataManager.allcountsList.get(0).CopyrightCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).CopyrightCount) + "");
                break;
            case 13://广告资质
                if (DataManager.allcountsList.get(0).AdvertisementCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).AdvertisementCount) + "");
                break;
            case 14://守合同重信用
                if (DataManager.allcountsList.get(0).CreditCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).CreditCount) + "");
                break;
            case 15://自主公示
                if (DataManager.allcountsList.get(0).AnnualCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).AnnualCount) + "");
                break;
            //===========================================================后续功能
            case 16://全景视图
//                if(DataManager.allcountsList.get(0).AnnualCount.equals("0")){
//                tv.setTextColor(Color.parseColor("#B8B8B8"));
//            }
//                tv.setText((DataManager.allcountsList.get(0).AnnualCount)+"");

//                tv.setTextColor(Color.parseColor("#B8B8B8"));
                tv.setText("1");
                break;
            case 17://投资连图
//                if(DataManager.allcountsList.get(0).AnnualCount.equals("0")){
//                    tv.setTextColor(Color.parseColor("#B8B8B8"));
//                }
//                tv.setText((DataManager.allcountsList.get(0).AnnualCount)+"");

//                tv.setTextColor(Color.parseColor("#B8B8B8"));
                tv.setText("1");
                break;
            case 18://发展历程
//                if(DataManager.allcountsList.get(0).AnnualCount.equals("0")){
//                    tv.setTextColor(Color.parseColor("#B8B8B8"));
//                }
//                tv.setText((DataManager.allcountsList.get(0).AnnualCount)+"");

//                tv.setTextColor(Color.parseColor("#B8B8B8"));
                tv.setText("1");
                break;
            case 19://招投标
                if (DataManager.allcountsList.get(0).BiddingCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).BiddingCount) + "");
                break;
            case 20://企业新闻
                if (DataManager.allcountsList.get(0).EntNewCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).EntNewCount) + "");
                break;
            case 21://企业招聘
                if (DataManager.allcountsList.get(0).JobCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText((DataManager.allcountsList.get(0).JobCount) + "");
                break;
            case 22://企业展示
//                if(DataManager.allcountsList.get(0).AnnualCount.equals("0")){
//                    tv.setTextColor(Color.parseColor("#B8B8B8"));
//                }
//                tv.setText((DataManager.allcountsList.get(0).AnnualCount)+"");
                if (DataManager.allcountsList.get(0).EntShowCount.equals("0")) {
                    tv.setTextColor(Color.parseColor("#B8B8B8"));
                }
                tv.setText(DataManager.allcountsList.get(0).EntShowCount+"");
                break;
            case 23://信用报告
//                if(DataManager.allcountsList.get(0).AnnualCount.equals("0")){
//                    tv.setTextColor(Color.parseColor("#B8B8B8"));
//                }
//                tv.setText((DataManager.allcountsList.get(0).AnnualCount)+"");
//                tv.setTextColor(Color.parseColor("#B8B8B8"));
                tv.setText("1");
                break;
            default:
                break;

        }
        return convertView;
    }

}
