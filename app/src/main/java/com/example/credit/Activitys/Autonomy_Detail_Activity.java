package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.credit.Adapters.Autonomy_Adapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.MyListView;

/**
 * Created by alucard on 2016-05-26.
 * 自主公示二级界面（详情页）
 */
public class Autonomy_Detail_Activity extends BaseActivity{
   LinearLayout report,funded,stock,permit,lore,punish;
    MyListView report_lv,funded_lv,stock_lv,permit_lv,lore_lv,punish_lv;
    TextView report_title,topBarTV;
    String value;
    Intent in;
    Autonomy_Adapter autoAdapter=new Autonomy_Adapter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autonomy_detai);
        ScrollView sv= (ScrollView) findViewById(R.id.au_scr);
        sv.smoothScrollTo(0,20);
        in=getIntent();
        initview();
        initdata();
    }



    private void initdata() {
    }

    private void initview() {
        topBarTV= (TextView) findViewById(R.id.b_topname);
        report_title= (TextView) findViewById(R.id.report_title);
        report= (LinearLayout) findViewById(R.id.report_content);
        funded= (LinearLayout) findViewById(R.id.funded_content);
        stock= (LinearLayout) findViewById(R.id.stock_content);
        permit= (LinearLayout) findViewById(R.id.permit_content);
        lore= (LinearLayout) findViewById(R.id.lore_content);
        punish= (LinearLayout) findViewById(R.id.punish_content);
        report_lv= (MyListView) findViewById(R.id.report_listview);
        funded_lv= (MyListView) findViewById(R.id.funded_listview);
        stock_lv= (MyListView) findViewById(R.id.stock_listview);
        permit_lv= (MyListView) findViewById(R.id.permit_listview);
        lore_lv= (MyListView) findViewById(R.id.lore_listview);
        punish_lv= (MyListView) findViewById(R.id.punish_listview);
        topBarTV.setText("企业自主公示");
        value=in.getStringExtra("key");
        
        switch (value){
            case "企业年报":
                report_title.setText("企业年报");
                report.setVisibility(View.VISIBLE);
                funded.setVisibility(View.GONE);
                stock.setVisibility(View.GONE);
                permit.setVisibility(View.GONE);
                lore.setVisibility(View.GONE);
                punish.setVisibility(View.GONE);
                autoAdapter.setData(DataManager.reportList,null,null,null,null,null);
                report_lv.setAdapter(autoAdapter);
                autoAdapter.notifyDataSetChanged();
                report_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        startActivity(new Intent(Autonomy_Detail_Activity.this,WebAutonomy.class).putExtra("KeyNo", DataManager.reportList.get(position).ANCHEID));
                    }
                });
                break;
            case "股东及出资信息":
                report_title.setText("股东及出资信息");
                report.setVisibility(View.GONE);
                funded.setVisibility(View.VISIBLE);
                stock.setVisibility(View.GONE);
                permit.setVisibility(View.GONE);
                lore.setVisibility(View.GONE);
                punish.setVisibility(View.GONE);
                autoAdapter.setData(null,DataManager.fundedList,null,null,null,null);
                funded_lv.setAdapter(autoAdapter);
                autoAdapter.notifyDataSetChanged();

                break;
            case "股权变更信息":
                report_title.setText("股权变更信息");
                report.setVisibility(View.GONE);
                funded.setVisibility(View.GONE);
                stock.setVisibility(View.VISIBLE);
                permit.setVisibility(View.GONE);
                lore.setVisibility(View.GONE);
                punish.setVisibility(View.GONE);
                autoAdapter.setData(null,null,DataManager.stockList,null,null,null);
                stock_lv.setAdapter(autoAdapter);
                autoAdapter.notifyDataSetChanged();

                break;
            case "行政许可信息":
                report_title.setText("行政许可信息");
                report.setVisibility(View.GONE);
                funded.setVisibility(View.GONE);
                stock.setVisibility(View.GONE);
                permit.setVisibility(View.VISIBLE);
                lore.setVisibility(View.GONE);
                punish.setVisibility(View.GONE);
                autoAdapter.setData(null,null,null,DataManager.permitList,null,null);
                permit_lv.setAdapter(autoAdapter);
                autoAdapter.notifyDataSetChanged();

                break;
            case"知识产权登记信息":
                report_title.setText("知识产权登记信息");
                report.setVisibility(View.GONE);
                funded.setVisibility(View.GONE);
                stock.setVisibility(View.GONE);
                permit.setVisibility(View.GONE);
                lore.setVisibility(View.VISIBLE);
                punish.setVisibility(View.GONE);
                autoAdapter.setData(null,null,null,null,DataManager.loreList,null);
                lore_lv.setAdapter(autoAdapter);
                autoAdapter.notifyDataSetChanged();
                break;
            case"行政处罚信息":
                report_title.setText("行政处罚信息");
                report.setVisibility(View.GONE);
                funded.setVisibility(View.GONE);
                stock.setVisibility(View.GONE);
                permit.setVisibility(View.GONE);
                lore.setVisibility(View.GONE);
                punish.setVisibility(View.VISIBLE);
                autoAdapter.setData(null,null,null,null,null,DataManager.punishList);
                punish_lv.setAdapter(autoAdapter);
                autoAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
          findViewById(R.id.b_return).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  finish();
                  overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
              }
          });

    }



}
