package com.example.credit.Views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.credit.Activitys.SearchFirmActivty;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Utils.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by alucard on 2016-05-13.
 */
public class CustomPopupwindow extends PopupWindow implements View.OnClickListener {
    private Activity activity;
    private View contentView;
    // 用于保存PopupWindow的宽度
    private int width;
    // 用于保存PopupWindow的高度
    private int height;
    TextView search_all, search_add, search_name, search_scope, search_product, search_corporate;
    ListView popuplist;
    List<String> listdata;
    List<DataManager.industryData> industryDataList;
    List<String> industryName = new ArrayList<>();
    List<String> industryCode = new ArrayList<>();
    ArrayAdapter adapter;
    TextView temp;

    public CustomPopupwindow(Activity activity, List<String> listdata, List<DataManager.industryData> industryDataList) {
        super(activity);
        this.activity = activity;
        this.listdata = listdata;

        if (industryDataList != null) {
            this.industryDataList = industryDataList;
            for (DataManager.industryData temp : industryDataList) {
                industryName.add(temp.EC_NAME);
                industryCode.add(temp.EC_VALUE);
            }
        }
        this.initPopupWindow();

    }


    public CustomPopupwindow(Activity activity) {
        super(activity);
        this.activity = activity;
        this.initPopupWindow();

    }

    private void initPopupWindow() {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.contentView = inflater.inflate(R.layout.popupwindow_custom, null);
        this.setContentView(contentView);
        popuplist = (ListView) contentView.findViewById(R.id.popuplist);

        if (listdata != null) {
            adapter = new ArrayAdapter(activity, R.layout.search_select_twolistitem, listdata);
        } else {
            adapter = new ArrayAdapter(activity, R.layout.search_select_twolistitem, industryName);
        }


        popuplist.setAdapter(adapter);
        popuplist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (SearchFirmActivty.capital_check) {
                    switch (position) {
                        case 0:
                            SearchFirmActivty.registCapiStartIndex = null;
                            SearchFirmActivty.registCapiEndIndex = null;
                            break;
                        case 1:
                            SearchFirmActivty.registCapiStartIndex = "0";
                            SearchFirmActivty.registCapiEndIndex = "100";
                            break;
                        case 2:
                            SearchFirmActivty.registCapiStartIndex = "100";
                            SearchFirmActivty.registCapiEndIndex = "200";
                            break;
                        case 3:
                            SearchFirmActivty.registCapiStartIndex = "200";
                            SearchFirmActivty.registCapiEndIndex = "500";
                            break;
                        case 4:
                            SearchFirmActivty.registCapiStartIndex = "500";
                            SearchFirmActivty.registCapiEndIndex = "1000";
                            break;
                        case 5:
                            SearchFirmActivty.registCapiStartIndex = "1000";
                            SearchFirmActivty.registCapiEndIndex = null;
                            break;
                    }
                    SearchFirmActivty.capital.setText(listdata.get(position));
                    SearchFirmActivty.handler.sendEmptyMessage(110);
                }
                if (SearchFirmActivty.time_check) {
                    switch (position) {
                        case 0:
                            SearchFirmActivty.startDateindex = null;
                            SearchFirmActivty.endDateindex = null;
                            break;
                        case 1:
                            SearchFirmActivty.startDateindex = "0";
                            SearchFirmActivty.endDateindex = "1";
                            break;
                        case 2:
                            SearchFirmActivty.startDateindex = "1";
                            SearchFirmActivty.endDateindex = "2";
                            break;
                        case 3:
                            SearchFirmActivty.startDateindex = "2";
                            SearchFirmActivty.endDateindex = "3";
                            break;
                        case 4:
                            SearchFirmActivty.startDateindex = "3";
                            SearchFirmActivty.endDateindex = "5";
                            break;
                        case 5:
                            SearchFirmActivty.startDateindex = "5";
                            SearchFirmActivty.endDateindex = "10";
                            break;
                        case 6:
                            SearchFirmActivty.startDateindex = "10";
                            SearchFirmActivty.endDateindex = null;
                            break;

                    }
                    SearchFirmActivty.time.setText(listdata.get(position));
                    SearchFirmActivty.handler.sendEmptyMessage(110);
                }
                if (SearchFirmActivty.industry_check) {
                    SearchFirmActivty.industry.setText(industryName.get(position));
                    SearchFirmActivty.industryindex = industryCode.get(position);
                    SearchFirmActivty.handler.sendEmptyMessage(110);

                }
//
                dismiss();

            }

        });

        /*search_all = (TextView) contentView.findViewById(R.id.search_all);
        search_add = (TextView) contentView.findViewById(R.id.search_add);
        search_name = (TextView) contentView.findViewById(R.id.search_name);
        search_scope = (TextView) contentView.findViewById(R.id.search_scope);
        search_product = (TextView) contentView.findViewById(R.id.search_product);
        search_corporate = (TextView) contentView.findViewById(R.id.search_corporate);
        search_all.setOnClickListener(this);
        search_add.setOnClickListener(this);
        search_name.setOnClickListener(this);
        search_scope.setOnClickListener(this);
        search_product.setOnClickListener(this);
        search_corporate.setOnClickListener(this);*/

        // 设置弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置弹出窗体可点击
        this.setTouchable(true);
        this.setFocusable(true);
        // 设置点击是否消失
        this.setOutsideTouchable(true);
        //设置弹出窗体动画效果
        this.setAnimationStyle(R.style.PopupAnimation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable background = new ColorDrawable(0x4f000000);
        //设置弹出窗体的背景
        this.setBackgroundDrawable(background);
        // 绘制
        this.mandatoryDraw();


    }

    @Override
    public void dismiss() {
        SearchFirmActivty.capital.setTextColor(activity.getResources().getColor(R.color.text_nocheck));
        SearchFirmActivty.capital_arraow.setImageResource(R.mipmap.senior_arraow_down);
        SearchFirmActivty.time.setTextColor(activity.getResources().getColor(R.color.text_nocheck));
        SearchFirmActivty.time_arraow.setImageResource(R.mipmap.senior_arraow_down);
        SearchFirmActivty.industry.setTextColor(activity.getResources().getColor(R.color.text_nocheck));
        SearchFirmActivty.industry_arraow.setImageResource(R.mipmap.senior_arraow_down);
        SearchFirmActivty.capital_check = false;
        SearchFirmActivty.time_check = false;
        SearchFirmActivty.industry_check = false;
        super.dismiss();
    }

    /**
     * 强制绘制popupWindowView，并且初始化popupWindowView的尺寸
     */
    private void mandatoryDraw() {
        this.contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        /**
         * 强制刷新后拿到PopupWindow的宽高
         */
        this.width = this.contentView.getMeasuredWidth();
        this.height = this.contentView.getMeasuredHeight();
    }

    /**
     * 显示在控件的下中方
     *
     * @param parent parent
     */
    public void showAtDropDownCenter(View parent) {
        if (parent.getVisibility() == View.GONE) {
            this.showAtLocation(parent, 0, 0, 0);
        } else {
            // x y
            int[] location = new int[2];
            //获取在整个屏幕内的绝对坐标
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, 0, location[0] / 2 + parent.getWidth() / 2 - this.width / 6, location[1] + parent.getHeight());
        }
    }

    /**
     * 显示在控件的下右方
     *
     * @param parent parent
     */
    public void showAtDropDownRight(View parent) {
        if (parent.getVisibility() == View.GONE) {
            this.showAtLocation(parent, 0, 0, 0);
        } else {
            // x y
            int[] location = new int[2];
            //获取在整个屏幕内的绝对坐标
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, 0, location[0] + parent.getWidth() - this.width, location[1] + parent.getHeight());
        }
    }

    /**
     * 显示在控件的下左方
     *
     * @param parent parent
     */
    public void showAtDropDownLeft(View parent) {
        if (parent.getVisibility() == View.GONE) {
            this.showAtLocation(parent, 0, 0, 0);
        } else {
            // x y
            int[] location = new int[2];
            //获取在整个屏幕内的绝对坐标
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, 0, location[0], location[1] + parent.getHeight());
        }
    }

    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
            case R.id.search_all:
                SearchFirmActivty.downButton.setText("按全部");
                SearchFirmActivty.searchEt.setHint("请输入公司名/地址/经营/商标");
                dismiss();
                break;
            case R.id.search_add:
                SearchFirmActivty.downButton.setText("按地址");
                SearchFirmActivty.searchEt.setHint("请输入公司地址");
                dismiss();
                break;
            case R.id.search_name:
                SearchFirmActivty.downButton.setText("按名称");
                SearchFirmActivty.searchEt.setHint("请输入公司名称");
                dismiss();
                break;
            case R.id.search_scope:
                SearchFirmActivty.downButton.setText("按经营");
                SearchFirmActivty.searchEt.setHint("请输入经营项目");
                dismiss();
                break;
            case R.id.search_product:
                SearchFirmActivty.downButton.setText("按产品");
                SearchFirmActivty.searchEt.setHint("请输入品牌/产品名称");
                dismiss();
                break;
            case R.id.search_corporate:
                SearchFirmActivty.downButton.setText("按法人");
                SearchFirmActivty.searchEt.setHint("请输入公司法定代表人名称");
                dismiss();
                break;
            default:
                break;
        }*/

    }

    public static class PopupWindowBuilder {
        private static String activityHashCode;
        private static CustomPopupwindow popupWindow;
        public static PopupWindowBuilder ourInstance;

        public static PopupWindowBuilder getInstance(Activity activity) {
            if (ourInstance == null) ourInstance = new PopupWindowBuilder();
            String hashCode = String.valueOf(activity.hashCode());
            /**
             * 不同一个Activity
             */
            if (!hashCode.equals(String.valueOf(activityHashCode))) {
                activityHashCode = hashCode;
                popupWindow = new CustomPopupwindow(activity);
            }
            return ourInstance;
        }

        public PopupWindowBuilder setTouchable(boolean touchable) {
            popupWindow.setTouchable(touchable);
            return this;
        }

        public PopupWindowBuilder setAnimationStyle(int animationStyle) {
            popupWindow.setAnimationStyle(animationStyle);
            return this;
        }

        public PopupWindowBuilder setBackgroundDrawable(Drawable background) {
            popupWindow.setBackgroundDrawable(background);
            return this;
        }

        public CustomPopupwindow getPopupWindow() {
            popupWindow.update();
            return popupWindow;
        }

    }

}
