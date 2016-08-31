package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Utils.Toast;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSetTowActivity extends BaseActivity {
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
    //android:digits="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ."
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
                if(txt.equals("男")){
                    rb1.setChecked(true);
                }else{
                    rb2.setChecked(true);
                }
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
                for(int i=0;i<DataManager.ZdianS.data.dictionarie.size();i++){
                    data_list.add(DataManager.ZdianS.data.dictionarie.get(i).NAME);
                }
                arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, data_list);
                arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(arr_adapter);

                for(int i=0;i<data_list.size();i++){
                    if(data_list.get(i).equals(txt)){
                        spinner2.setSelection(i);
                    }
                }
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ustC_et.setText(data_list.get(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            case 6:
                ll1.setVisibility(View.GONE);
                ll3.setVisibility(View.VISIBLE);
                b_topname.setText("学历修改");
                data_list = new ArrayList<String>();
                for(int i=0;i<DataManager.ZdianS.data.dictionarie.size();i++){
                    data_list.add(DataManager.ZdianS.data.dictionarie.get(i).NAME);
                }
                arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
                arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(arr_adapter);

                for(int i=0;i<data_list.size();i++){
                    if(data_list.get(i).equals(txt)){
                        spinner2.setSelection(i);
                    }
                }

                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ustC_et.setText(data_list.get(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            case 7:
                b_topname.setText("手机号码修改");//
                break;
        }
    }
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.b_return:
                    i=new Intent();
                    i.putExtra("text",txt);
                    setResult(3, i);
                    finish();
                    break;
                case R.id.b_topY:
                    switch (type) {
                        case 4:
                            if(!isEmail(ustC_et.getText().toString())){
                                Toast.show("邮箱格式不正确！");
                            }else{
                                i=new Intent();
                                i.putExtra("text",ustC_et.getText().toString());
                                setResult(3, i);
                                finish();
                            }
                            break;
                        case 7:
                            if(!isNumeric(ustC_et.getText().toString())){
                                Toast.show("手机格式不正确！");
                            }else if(ustC_et.getText().length()!=11){
                                Toast.show("手机格式不正确！");
                            }else{
                                i=new Intent();
                                i.putExtra("text",ustC_et.getText().toString());
                                setResult(3, i);
                                finish();
                            }
                            break;
                        case 2:
                            if(ustC_et.getText().toString().length()>8){
                                Toast.show("昵称字数不能超过8个！");
                            }else{
                                i=new Intent();
                                i.putExtra("text",ustC_et.getText().toString());
                                setResult(3, i);
                                finish();
                            }
                            break;
                        default:
                            i=new Intent();
                            i.putExtra("text",ustC_et.getText().toString());
                            setResult(3, i);
                            finish();
                            break;
                    }
                    break;
            }
        }
    };
    //判断email格式是否正确
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }
    //判断是否全是数字
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
