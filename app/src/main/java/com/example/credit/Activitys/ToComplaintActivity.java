package com.example.credit.Activitys;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

/**
 * 提交投诉界面
 */
public class ToComplaintActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;//顶栏
    @ViewInject(R.id.b_return)
    LinearLayout b_return;//退出
    @ViewInject(R.id.com_et_title)
    EditText com_et_title;//投诉主题
    @ViewInject(R.id.com_et_conten)
    EditText com_et_conten;//投诉内容
    @ViewInject(R.id.com_et_num)
    TextView com_et_num;//内容字数
    @ViewInject(R.id.com_photo)
    ImageView com_photo;//投诉图片
    @ViewInject(R.id.com_submit)
    TextView com_submit;//提交按钮
    CreditSharePreferences csp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_complaint);
        csp=CreditSharePreferences.getLifeSharedPreferences();
        ViewUtils.inject(this);
        init();
    }

    public void init() {
        b_topname.setText("我要投诉");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });


        com_et_conten.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    com_et_num.setText("您还可以输入300个字");
                } else {
                    int num = 300 - s.length();
                    com_et_num.setText("您还可以输入" + num + "个字");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 300) {
                    Toast.show("您输入的字数已经达到300了");
                }
            }
        });


    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.com_submit:
                    if(com_et_title.getText().toString().equals("")){
                        Toast.show("投诉主题不能为空!");
                    }else if(com_et_conten.getText().toString().equals("")){
                        Toast.show("投诉内容不能为空!");
                    }else if(com_et_conten.getText().toString().length()<15){
                        Toast.show("投诉内容不能少于15字!");
                    }else if(com_et_title.getText().toString().length()<3){
                        Toast.show("投诉主题不能少于3个字!");
                    }else{
                        GsonUtil ComRequerst =new GsonUtil(URLconstant.URLINSER+URLconstant.SEVECOM, RequestMethod.GET);
                        ComRequerst.add("token", MD5.MD5s(DataManager.BaseinfoList.get(0).EnterAddtionID+new Build().MODEL));
                        ComRequerst.add("KeyNo",DataManager.BaseinfoList.get(0).EnterAddtionID);
                        ComRequerst.add("deviceId",new Build().MODEL);
                        ComRequerst.add("memberId",csp.getID());//csp.getID()
                        ComRequerst.add("complaintTitle",com_et_title.getText().toString());
                        ComRequerst.add("complainComment",com_et_conten.getText().toString());
                        ComRequerst.add("complainTypeId","");//投诉类型

                    }
                    break;
                default:
                    break;
            }

        }
    };


}
