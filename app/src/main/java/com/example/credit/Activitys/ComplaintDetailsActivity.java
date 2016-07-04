package com.example.credit.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 投诉详情界面
 */
public class ComplaintDetailsActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.com_title)
    TextView com_title;
    @ViewInject(R.id.complain_er)
    TextView complain_er;
    @ViewInject(R.id.complain_detail)
    TextView complain_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_details);
        ViewUtils.inject(this);
        init();
    }
    public void init(){
        b_topname.setText("投诉详情");
        com_title.setTextColor(getResources().getColor(R.color.black));
        com_title.setText(DataManager.complaintDetail.data.UserComplain.COMPLAINTTITLE);
        complain_er.setTextColor(getResources().getColor(R.color.black));
        complain_er.setText(DataManager.complaintDetail.data.UserComplain.MEMBERNAME);
        complain_detail.setTextColor(getResources().getColor(R.color.black));
        complain_detail.setText(DataManager.complaintDetail.data.UserComplain.COMPLAINCOMMENT);
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
