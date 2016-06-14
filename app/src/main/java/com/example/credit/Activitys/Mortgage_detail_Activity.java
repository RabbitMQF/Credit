package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.Fragments.Mortgage_detail_Fragment;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.ViewPagerIndicator;
import com.yolanda.nohttp.RequestMethod;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 抵押信息
 * Created by alucard on 2016-05-24.
 */
public class Mortgage_detail_Activity extends FragmentActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private ViewPagerIndicator mIndicator;
    private List<String> mTitles = Arrays.asList("动产抵押信息", "不动产抵押信息");
    private List<Mortgage_detail_Fragment> fragmentList = new ArrayList<Mortgage_detail_Fragment>();
    private ImageView b_return;
    TextView topBarTV;
    String Tname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_detail);
        //getSupportActionBar().setDisplayShowHomeEnabled(false); //ActionBar不显示应用Icon
        Intent i=getIntent();
         Tname=i.getStringExtra("Tname");
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
        b_return = (ImageView) findViewById(R.id.b_return);
        b_return.setOnClickListener(this);
        topBarTV= (TextView) findViewById(R.id.b_topname);
        topBarTV.setText(Tname);
    }

    private void initDatas() {
        if (DataManager.mortgageMP_List.size() == 0 || DataManager.mortgageRE_List.size() == 0) {
            DataManager.mortgageMP mp = new DataManager.mortgageMP();
            mp.MORREG_ID = "415641631654165";
            mp.MORREGCNO = "456415646516546545646";
            mp.REGIDATE = "2015年1月30日";
            mp.PUBLICDATE = "2016年1月-2017年1月";
            mp.REGORG_CN = "南昌市东湖区人民法院";
            mp.PRICLASECAM = "1000万";
            //mp.detail="地球上的最后一滴水，就是人类的眼泪--这句辛酸的话，既道出了人类对于水资源的渴望，又隐含了对于水资源不合理利用与开发的事实。在水资源日益紧张的今天，如何高效地获取淡水，则成为了一项重要的课题。而空气中的水分，看似虚无缥缈，实则储量可观。最近…";
            DataManager.mortgageMP_List.add(mp);
            DataManager.mortgageMP_List.add(mp);
            DataManager.mortgageMP_List.add(mp);
            DataManager.mortgageRE re = new DataManager.mortgageRE();
            re.C_INFOID = "154654644564";
            re.C_DYDJZH = "56416316546";
            re.D_DJRQ = "2015年1月30日";
            re.D_SQRQ = "2016年1月-2017年1月";
            re.C_DJJG = "南昌市西湖区人民法院";
            re.C_DBFW = "地球上的最后一滴水，就是人类的眼泪--这句辛酸的话，既道出了人类对于水资源的渴望，又隐含了对于水资源不合理利用与开发的事实。在水资源日益紧张的今天，如何高效地获取淡水，则成为了一项重要的课题。而空气中的水分，看似虚无缥缈，实则储量可观。最近…";
            //re.debts="4700万";
            //re.collateral="阿斯顿马丁 ONE-77";
            //re.valuation="4700万";
            DataManager.mortgageRE_List.add(re);
            DataManager.mortgageRE_List.add(re);
            DataManager.mortgageRE_List.add(re);
        }

        Mortgage_detail_Fragment fragment = Mortgage_detail_Fragment.newInstance();
        Mortgage_detail_Fragment fragment1 = Mortgage_detail_Fragment.newInstance();
        fragment.setListData(DataManager.mortgageMP_List, null);
        fragment1.setListData(null, DataManager.mortgageRE_List);
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
            case R.id.b_return:
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
                break;
            default:
                break;
        }

    }
}
