package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.Fragments.Admin_Fragment;
import com.example.credit.R;
import com.example.credit.Views.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 行政审批
 * Created by alucard on 2016-05-25.
 */
public class AdminActivity extends FragmentActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private ViewPagerIndicator mIndicator;
    private List<String> mTitles = Arrays.asList("行政许可信息", "其他信息");
    private List<Admin_Fragment> fragmentList = new ArrayList<>();
    private LinearLayout b_return;
    TextView topBarTV;
    String Tname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Intent i=getIntent();
        Tname=i.getStringExtra("Tname");
        initViews();
        initDatas();
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        mIndicator.setViewPager(mViewPager, 0);
    }

    private void initDatas() {
        Admin_Fragment fragment=Admin_Fragment.newInstance();
        fragment.setListData(DataManager.ad_List,null);
        Admin_Fragment fragment1=Admin_Fragment.newInstance();
        fragment1.setListData(null,DataManager.admin_other_List);//其它信息数据
        fragmentList.add(fragment);
        fragmentList.add(fragment1);
    }

    private void initViews() {
        topBarTV= (TextView) findViewById(R.id.b_topname);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
        b_return = (LinearLayout) findViewById(R.id.b_return);
        b_return.setOnClickListener(this);
        topBarTV.setText(Tname);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_return:
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
                break;
            default:
                break;
        }
    }
}
