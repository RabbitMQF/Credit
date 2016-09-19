package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Adapters.MyGridAdapter4;
import com.example.credit.Adapters.MyGridAdapter41;
import com.example.credit.R;
import com.example.credit.Views.MyGridView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 预警信息主界面
 */
public class AlertActivity extends BaseActivity {
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;


    @ViewInject(R.id.myGridView4)
    MyGridView myGridView4;
    String Tname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        ViewUtils.inject(this);
        Intent i=getIntent();
         Tname=i.getStringExtra("Tname");
        init();
    }

    public void init() {
        b_topname.setText(Tname);
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
//
        MyGridAdapter41 adapter4 = new MyGridAdapter41(AlertActivity.this, getResources().getStringArray(R.array.alert));
        myGridView4.setAdapter(adapter4);
        myGridView4.setSelector(new ColorDrawable(Color.TRANSPARENT));
        myGridView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                      TextView temp= (TextView) view.findViewById(R.id.tbv);
                        Intent i1=new Intent(AlertActivity.this,Alert_CActivity.class);
                        i1.putExtra("position",index);
                        i1.putExtra("key",temp.getText().toString().substring(0,4));
                        startActivity(i1);
                        overridePendingTransition(R.anim.start_tran_one,R.anim.start_tran_two);
            }
        });
    }

}
