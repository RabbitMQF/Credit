package com.example.credit.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.credit.Views.FileUtil.decodeBitmap;

/**
 * @author http://blog.csdn.net/finddreams
 * @Description:gridview的Adapter
 */
public class MyGridAdapterClaim extends BaseAdapter {
    private Context mContext;
    private String [] imgs;
    public MyGridAdapterClaim(Context context, String[] imgs1) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.grid_items_claim, parent, false);
        }
        if (imgs.length % 4 != 0) {
            if ((imgs.length - 1) < position) {
                return convertView;
            }
        }
        final ImageView bg= (ImageView) convertView.findViewById(R.id.ivc_items);
        final ImageView iv = BaseViewHolder.get(convertView, R.id.ivc_items);
//        Picasso.with(mContext).load(imgs[position]).into(bg);
        iv.setImageBitmap(decodeBitmap(imgs[position], 200,200));
        if (bg.getDrawable() != null){
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onThumbnailClick(bg);
                }
            });
        }
        return convertView;
    }
    //---------------显示大图
    public void onThumbnailClick(ImageView img) {
        final Dialog dialog = new Dialog(mContext, android.R.style.Theme_Black_NoTitleBar);
        ImageView imgView = getView(img);
        dialog.setContentView(imgView);
        dialog.show();
        // 点击图片消失
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
    }

    private ImageView getView(ImageView img) {
        ImageView imgView = new ImageView(mContext);
        imgView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imgView.setImageDrawable(img.getDrawable());
        return imgView;
    }
}
