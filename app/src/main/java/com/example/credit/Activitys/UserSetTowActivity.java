package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.credit.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class UserSetTowActivity extends Activity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topY)
    TextView b_topY;

    @ViewInject(R.id.ll1)
    LinearLayout ll1;
    @ViewInject(R.id.ll2)
    LinearLayout ll2;
    @ViewInject(R.id.ll3)
    LinearLayout ll3;
    @ViewInject(R.id.spinner2)
    Spinner spinner2;

    @ViewInject(R.id.rg)
    RadioGroup rg;
    @ViewInject(R.id.rb1)
    RadioButton rb1;
    @ViewInject(R.id.rb2)
    RadioButton rb2;

    @ViewInject(R.id.ustC_et)
    EditText ustC_et;
    @ViewInject(R.id.ust1_cc)
    ImageView ust1_cc;
    int type;
    String txt;
    String text;
    Intent i;
    String content;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_set_tow);
        ViewUtils.inject(this);
        Intent i=getIntent();
        type=i.getIntExtra("type",0);
        txt=i.getStringExtra("txt");
        init();
    }

    public void init(){
        ustC_et.setText(txt);
        if(txt.equals("男")){
            rb1.setChecked(true);
        }else{
            rb2.setChecked(true);
        }
        b_topY.setVisibility(View.VISIBLE);//显示右上角控件“发表”
        b_topY.setText("保存");
        b_topY.setOnClickListener(listener);
        b_return.setOnClickListener(listener);
        switch (type){
            case 2:
                b_topname.setText("昵称修改");
                break;
            case 3:
                ll1.setVisibility(View.GONE);
                ll2.setVisibility(View.VISIBLE);
                b_topname.setText("性别修改");
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId){
                            case R.id.rb1:
                                ustC_et.setText("男");
                                break;
                            case R.id.rb2:
                                ustC_et.setText("女");
                                break;
                        }
                    }
                });
                break;
            case 4:
                b_topname.setText("邮箱修改");
                break;
            case 5:
                ll1.setVisibility(View.GONE);
                ll3.setVisibility(View.VISIBLE);
                b_topname.setText("行业修改");
                data_list = new ArrayList<String>();
                data_list.add("金融");
                data_list.add("通信");
                data_list.add("运输");
                arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, data_list);
                arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(arr_adapter);
                break;
            case 6:
                ll1.setVisibility(View.GONE);
                spinner2.setVisibility(View.VISIBLE);
                b_topname.setText("学历修改");
                data_list = new ArrayList<String>();
                data_list.add("本科");
                data_list.add("专科");
                data_list.add("硕士");
                data_list.add("博士");
                data_list.add("高中");
                arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
                arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(arr_adapter);
                break;
            case 7:
                b_topname.setText("手机号码修改");
                break;
        }
    }
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.b_return:
                    i=new Intent();
                    i.putExtra("text",ustC_et.getText().toString() );
                    setResult(3, i);
                    finish();
                    break;
                case R.id.b_topY:
                    i=new Intent();
                    i.putExtra("text",ustC_et.getText().toString());
                    setResult(3, i);
                    finish();
                    break;
            }
        }
    };
}
