package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.Fragments.Mortgage_detail_Fragment;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.ViewPagerIndicator;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
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
    private LinearLayout b_return;
    TextView topBarTV;
    String Tname;
    @ViewInject(R.id.dy_size1)
    TextView dy_size1;
    @ViewInject(R.id.dy_size2)
    TextView dy_size2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_detail);
        ViewUtils.inject(this);
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
        b_return = (LinearLayout) findViewById(R.id.b_return);
        b_return.setOnClickListener(this);
        topBarTV= (TextView) findViewById(R.id.b_topname);
        topBarTV.setText(Tname);
        dy_size1.setText(DataManager.MychattelS.data.chattel.size()+"");
        dy_size2.setText(DataManager.MyrealEstateS.data.realEstate.size()+"");
    }

    private void initDatas() {
        Mortgage_detail_Fragment fragment = Mortgage_detail_Fragment.newInstance();
        Mortgage_detail_Fragment fragment1 = Mortgage_detail_Fragment.newInstance();
        fragment.setListData(DataManager.MychattelS.data.chattel, null);
        fragment1.setListData(null, DataManager.MyrealEstateS.data.realEstate);
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
