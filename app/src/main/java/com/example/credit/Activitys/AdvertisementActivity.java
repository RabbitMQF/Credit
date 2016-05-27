package com.example.credit.Activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.credit.Adapters.Advertisement_Adapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;

/**
 * Created by alucard on 2016-05-25.
 * 广告资质
 */
public class AdvertisementActivity extends BaseActivity {
    ListView listView;
    Advertisement_Adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        listView = (ListView) findViewById(R.id.advertisement_listview);
        initData();
        adapter = new Advertisement_Adapter(this, DataManager.advertisementList);
        listView.setAdapter(adapter);
        findViewById(R.id.c_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
    }

    private void initData() {
        DataManager.advertisement adv = new DataManager.advertisement();
        adv.level = "A4";
        adv.type = "设计策划";
        adv.dates = "2016-12-12";
        adv.times = "2016-1-1";
        adv.office = "南昌市东湖区工商局";
        DataManager.advertisementList.add(adv);
        DataManager.advertisementList.add(adv);
        DataManager.advertisementList.add(adv);

    }
}
