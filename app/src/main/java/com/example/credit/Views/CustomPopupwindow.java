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
    ArrayAdapter adapter;
    TextView temp;

    public CustomPopupwindow(Activity activity, List<String> listdata) {
        super(activity);
        this.activity = activity;
        this.listdata = listdata;
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
        adapter = new ArrayAdapter(activity, R.layout.search_select_twolistitem, listdata);
        popuplist.setAdapter(adapter);
        popuplist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                temp = (TextView) view.findViewById(R.id.menu_two_tv);//textview
                switch (temp.getText().toString()) {
                    case "查全部":
                        SearchFirmActivty.downButton.setText("按全部");
                        SearchFirmActivty.searchEt.setHint("请输入公司名/地址/经营/商标");
                        break;
                    case "按名称查询":
                        SearchFirmActivty.downButton.setText("按名称");
                        SearchFirmActivty.searchEt.setHint("请输入公司名称");
                        break;
                    case "按地址查询":
                        SearchFirmActivty.downButton.setText("按地址");
                        SearchFirmActivty.searchEt.setHint("请输入公司地址");
                        break;
                    case "按经营范围查询":
                        SearchFirmActivty.downButton.setText("按经营");
                        SearchFirmActivty.searchEt.setHint("请输入经营项目");
                        break;
                    case "按品牌/产品查询":
                        SearchFirmActivty.downButton.setText("按产品");
                        SearchFirmActivty.searchEt.setHint("请输入品牌/产品名称");
                        break;
                    case "按法定代表人查询":
                        SearchFirmActivty.downButton.setText("按法人");
                        SearchFirmActivty.searchEt.setHint("请输入公司法定代表人名称");
                        break;
                    case "不限注册":
                        SearchFirmActivty.capital.setText("不限注册");
                        break;
                    case "100万以内":
                        SearchFirmActivty.capital.setText("100万以内");
                        break;
                    case "100万到200万":
                        SearchFirmActivty.capital.setText("100万到200万");
                        break;
                    case "200万到500万":
                        SearchFirmActivty.capital.setText("200万到500万");
                        break;
                    case "500万到1000万":
                        SearchFirmActivty.capital.setText("500万到1000万");
                        break;
                    case "1000万以上":
                        SearchFirmActivty.capital.setText("1000万以上");
                        break;
                    case "不限年限":
                        SearchFirmActivty.time.setText("不限年限");
                        break;
                    case "1年内":
                        SearchFirmActivty.time.setText("1年内");
                        break;
                    case "1-2年":
                        SearchFirmActivty.time.setText("1-2年");
                        break;
                    case "2-3年":
                        SearchFirmActivty.time.setText("2-3年");
                        break;
                    case "3-5年":
                        SearchFirmActivty.time.setText("3-5年");
                        break;
                    case "5-10年":
                        SearchFirmActivty.time.setText("5-10年");
                        break;
                    case "10年以上":
                        SearchFirmActivty.time.setText("10年以上");
                        break;
                    case "1":
                        break;
                    case "0":
                        break;

                    default:
                        break;

                }
                dismiss();
                /*SearchFirmActivty.capital.setTextColor(activity.getResources().getColor(R.color.text_nocheck));
                SearchFirmActivty.capital_arraow.setImageResource(R.mipmap.senior_arraow_down);
                SearchFirmActivty.time.setTextColor(activity.getResources().getColor(R.color.text_nocheck));
                SearchFirmActivty.time_arraow.setImageResource(R.mipmap.senior_arraow_down);
                SearchFirmActivty.capital_check=false;
                SearchFirmActivty.time_check=false;*/
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
        SearchFirmActivty.capital_check=false;
        SearchFirmActivty.time_check=false;
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
