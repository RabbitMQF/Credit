package com.example.credit.Activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.Fragments.Mortgage_detail_Fragment;
import com.example.credit.R;
import com.example.credit.Views.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by alucard on 2016-05-24.
 */
public class Mortgage_detail_Activity extends FragmentActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private ViewPagerIndicator mIndicator;
    private List<String> mTitles = Arrays.asList("动产抵押信息", "不动产抵押信息");
    private List<Mortgage_detail_Fragment> fragmentList = new ArrayList<Mortgage_detail_Fragment>();
    private ImageView c_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_detail);
        //getSupportActionBar().setDisplayShowHomeEnabled(false); //ActionBar不显示应用Icon

        initViews();
        initDatas();
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) { //简单创建一个FragmentPagerAdapter
           /* @Override
            public CharSequence getPageTitle(int position) { //必须复写这个方法，开源控件PagerSlidingTabStrip需要通过它获取标签标题
                return "Title " + (position + 1);
            }*/

            @Override
            public Fragment getItem(int position) {
                // return Mortgage_detail_Fragment.newInstance("Content " + (i + 1)); //返回刚刚我们创建的那个Fragment，显示内容为Content X
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        mIndicator.setViewPager(mViewPager, 0);

    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
        c_return = (ImageView) findViewById(R.id.c_return);
        c_return.setOnClickListener(this);
    }

    private void initDatas() {
        DataManager.mortgageMP mp=new DataManager.mortgageMP();
        mp.no="415641631654165";
        mp.register_date="2015年1月30日";
        mp.public_date="2016年1月-2017年1月";
        mp.office="南昌市东湖区人民法院";
        mp.debts="1000万";
        mp.detail="地球上的最后一滴水，就是人类的眼泪--这句辛酸的话，既道出了人类对于水资源的渴望，又隐含了对于水资源不合理利用与开发的事实。在水资源日益紧张的今天，如何高效地获取淡水，则成为了一项重要的课题。而空气中的水分，看似虚无缥缈，实则储量可观。最近…";
        DataManager.mortgageMP_List.add(mp);
        DataManager.mortgageMP_List.add(mp);
        DataManager.mortgageMP_List.add(mp);
        DataManager.mortgageRE re=new DataManager.mortgageRE();
        re.no="154654644564";
        re.register_date="2015年1月30日";
        re.public_date="2016年1月-2017年1月";
        re.office="南昌市西湖区人民法院";
        re.debts="4700万";
        re.collateral="阿斯顿马丁 ONE-77";
        re.valuation="4700万";
        DataManager.mortgageRE_List.add(re);
        DataManager.mortgageRE_List.add(re);
        DataManager.mortgageRE_List.add(re);

        Mortgage_detail_Fragment fragment = Mortgage_detail_Fragment.newInstance();
        Mortgage_detail_Fragment fragment1=Mortgage_detail_Fragment.newInstance();
        fragment.setListData(DataManager.mortgageMP_List,null);
        fragment1.setListData(null,DataManager.mortgageRE_List);
        fragmentList.add(fragment);
        fragmentList.add(fragment1);





       /* for (String title : mTitles) {
            Mortgage_detail_Fragment fragment = Mortgage_detail_Fragment.newInstance(title);
            fragmentList.add(fragment);
        }*/
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
