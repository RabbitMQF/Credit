package com.example.credit.Activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.Fragments.Admin_Fragment;
import com.example.credit.R;
import com.example.credit.Views.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alucard on 2016-05-25.
 */
public class AdminActivity extends FragmentActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private ViewPagerIndicator mIndicator;
    private List<String> mTitles = Arrays.asList("行政许可信息", "其他信息");
    private List<Admin_Fragment> fragmentList = new ArrayList<>();
    private ImageView c_return;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
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
        DataManager.administraton an = new DataManager.administraton();
        an.LICNAME = "企业投资技术改造项目变更建设年限批复";
        an.LICNO = "赣环评函[2015]182号";
        an.VALFROM = "2015年1月30日";
        an.LICANTH = "南昌市经开区管委会";
        an.VALTO = "2017年1月30日";
        an.PRIPID = "地球上的最后一滴水，就是人类的眼泪--这句辛酸的话，既道出了人类对于水资源的渴望，又隐含了对于水资源不合理利用与开发的事实。在水资源日益紧张的今天，如何高效地获取淡水，则成为了一项重要的课题。而空气中的水分，看似虚无缥缈，实则储量可观。最近…";
        //DataManager.ad_List.add(an);
        //DataManager.ad_List.add(an);
        //DataManager.ad_List.add(an);
        Admin_Fragment fragment=Admin_Fragment.newInstance();
        fragment.setListData(DataManager.ad_List);
        Admin_Fragment fragment1=Admin_Fragment.newInstance();
        fragment1.setListData(DataManager.ad_List);
        fragmentList.add(fragment);
        fragmentList.add(fragment1);
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
        c_return = (ImageView) findViewById(R.id.c_return);
        c_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.c_return:
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
                break;
            default:
                break;
        }
    }
}
