package com.example.credit.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Adapters.MyGridAdapter4;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.MyGridView;

import java.util.ArrayList;

/**
 * Created by alucard on 2016-05-26.
 * 自主公示
 */
public class AutonomyActivity extends BaseActivity {
    TextView  topBar;
    MyGridView auto_mgv;
    MyGridAdapter4 autoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        initview();
    }

    private void initview() {


        auto_mgv = (MyGridView) findViewById(R.id.myGridView4);
        topBar = (TextView) findViewById(R.id.b_topname);
//        if(getIntent().getStringExtra("tag")=="list"||getIntent().getStringExtra("tag").equals("list")){
//          autoAdapter=new MyGridAdapter4(Authis,WebAutonomy.class)
//        }
        autoAdapter = new MyGridAdapter4(this, getResources().getStringArray(R.array.autonomy));
        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParam.setMargins(10, 10, 0, 10);
        auto_mgv.setLayoutParams(layoutParam);
        autoAdapter.setmargin(layoutParam);
        auto_mgv.setAdapter(autoAdapter);
        auto_mgv.setSelector(new ColorDrawable(Color.TRANSPARENT));
        auto_mgv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tbv = (TextView) view.findViewById(R.id.tbv);
                if (tbv.getText().toString().substring(0,4).equals("企业年报")) {
                   startActivity(new Intent(AutonomyActivity.this,YearReportList.class));
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                } else {
                    Intent in = new Intent(AutonomyActivity.this, Autonomy_Detail_Activity.class);
                    in.putExtra("key", tbv.getText());
                    startActivity(in);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                }
            }
        });
        findViewById(R.id.b_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        topBar.setText("企业自主公示");

    }


}
