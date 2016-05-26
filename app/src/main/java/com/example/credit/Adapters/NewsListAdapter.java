package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;

import java.util.List;

/**
 * Created by alucard on 2016-05-20.
 */
public class NewsListAdapter extends BaseAdapter{
    Context context;
    List<DataManager.News> newsList;

    public NewsListAdapter(Context context, List<DataManager.News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override

    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.news_list_item, null);
            vh=new ViewHolder();
            vh.img= (ImageView) convertView.findViewById(R.id.news_img);
            vh.title= (TextView) convertView.findViewById(R.id.news_title);
            vh.congtent= (TextView) convertView.findViewById(R.id.news_content);
            vh.time= (TextView) convertView.findViewById(R.id.news_time);
            convertView.setTag(vh);
        }else {
            vh=(ViewHolder) convertView.getTag();
        }
        DataManager.News tempNews=newsList.get(position);
        vh.title.setText(tempNews.title);
        vh.congtent.setText(tempNews.content);
        vh.time.setText(tempNews.time);
        return convertView;
    }

    public class ViewHolder{
        ImageView img;
        TextView title;
        TextView congtent;
        TextView time;
    }
}
