package com.example.credit.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by alucard on 2016-05-20.
 */
public class NewsListAdapter extends BaseAdapter{
    Context context;
    List<DataManager.Newss> newsList;
    public NewsListAdapter(Context context, List<DataManager.Newss> newsList) {
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
        DataManager.Newss tempNews=newsList.get(position);
        //Bitmap bitmap=BitmapFactory.decodeByteArray(Base64.decode(tempNews.img,Base64.DEFAULT),0,Base64.decode(tempNews.img,Base64.DEFAULT).length);
        /*if(tempNews.img!=""){
            Picasso.with(context).load(tempNews.img).into(vh.img);
        }else {*/
            Picasso.with(context).load("http://zkres.myzaker.com/201606/574e83227f52e98f2700000c_320.jpg").into(vh.img);
        /*}*/

        vh.title.setText(tempNews.title);
        vh.congtent.setText(tempNews.content);
        vh.time.setText(tempNews.pdate_src);
        return convertView;
    }

    public class ViewHolder{
        ImageView img;
        TextView title;
        TextView congtent;
        TextView time;
    }
}
