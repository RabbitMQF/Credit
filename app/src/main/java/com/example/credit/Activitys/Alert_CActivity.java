package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.credit.Adapters.Alert_Adapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 预警信息子项公用界面
 */
public class Alert_CActivity extends BaseActivity {
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;

    @ViewInject(R.id.alert_title)
    TextView alert_title;

    @ViewInject(R.id.alert_lv)
    ListView alert_lv;
    int position;
    String key;
    Alert_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert__content);
        ViewUtils.inject(this);
        b_topname.setText("预警信息");
        Intent i = getIntent();
        position = i.getIntExtra("position", 0);
        key= i.getStringExtra("key");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        init();
    }

    public void init() {
        String[] str = getResources().getStringArray(R.array.alert);
        alert_title.setText(str[position]);
        adapter=new Alert_Adapter(this);
        switch (key){
            case "证照到期":
                for(int i=0;i<DataManager.AlertInfoS.data.size();i++){
                    if(DataManager.AlertInfoS.data.get(i).type.equals("zzdq")){
                        adapter.setData(DataManager.AlertInfoS.data.get(i).data,"证照到期");
                    }
                }
                alert_lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                break;
            case "证照过期":
                for(int i=0;i<DataManager.AlertInfoS.data.size();i++){
                    if(DataManager.AlertInfoS.data.get(i).type.equals("zzgq")){
                        adapter.setData(DataManager.AlertInfoS.data.get(i).data,"证照过期");
                    }
                }
                alert_lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                break;
            case "责令改正":
                for(int i=0;i<DataManager.AlertInfoS.data.size();i++){
                    if(DataManager.AlertInfoS.data.get(i).type.equals("zlgz")){
                        adapter.setData(DataManager.AlertInfoS.data.get(i).data,"责令改正");
                    }
                }
                alert_lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                break;
            case "欠贷信息":
                for(int i=0;i<DataManager.AlertInfoS.data.size();i++){
                    if(DataManager.AlertInfoS.data.get(i).type.equals("qdxx")){
                        adapter.setData(DataManager.AlertInfoS.data.get(i).data,"欠贷信息");
                    }
                }
                alert_lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                break;
            case "欠税信息":
                for(int i=0;i<DataManager.AlertInfoS.data.size();i++){
                    if(DataManager.AlertInfoS.data.get(i).type.equals("qsxx")){
                        adapter.setData(DataManager.AlertInfoS.data.get(i).data,"欠税信息");
                    }
                }
                alert_lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                break;
            case "欠薪信息":
                for(int i=0;i<DataManager.AlertInfoS.data.size();i++){
                    if(DataManager.AlertInfoS.data.get(i).type.equals("qxxx")){
                        adapter.setData(DataManager.AlertInfoS.data.get(i).data,"欠薪信息");
                    }
                }
                alert_lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                break;
        }

    }


}
