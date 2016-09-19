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
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by alucard on 2016-05-20.
 */
public class NewsListAdapter extends BaseAdapter{
    Context context;
    List<DataManager.MyNews.DataBean.NewslistBean> newsList;
    public NewsListAdapter(Context context, List<DataManager.MyNews.DataBean.NewslistBean> newsList) {
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
        DataManager.MyNews.DataBean.NewslistBean tempNews=newsList.get(position);
        //Bitmap bitmap=BitmapFactory.decodeByteArray(Base64.decode(tempNews.img,Base64.DEFAULT),0,Base64.decode(tempNews.img,Base64.DEFAULT).length);
        if(tempNews.TITLE_IMG!=""){
            Picasso.with(context).load("http://101.201.211.27:8282"+tempNews.TITLE_IMG).into(vh.img);
        }else {
            Picasso.with(context).load(R.mipmap.nopicture).into(vh.img);
        }

        vh.title.setText(tempNews.TITLE);
        vh.congtent.setText(tempNews.DESCRIPTION);
        vh.time.setText(tempNews.RELEASE_DATE);
        return convertView;
    }

    public class ViewHolder{
        ImageView img;
        TextView title;
        TextView congtent;
        TextView time;
    }
}
