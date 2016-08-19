package com.example.credit.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.Dialogs.WaitDialog_pdf;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends Activity {
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.gs1)
    TextView gs1;
    @ViewInject(R.id.gs2)
    TextView gs2;
    @ViewInject(R.id.gs3)
    TextView gs3;
    @ViewInject(R.id.gs4)
    TextView gs4;
    @ViewInject(R.id.gs5)
    TextView gs5;
    @ViewInject(R.id.gs6)
    TextView gs6;
    @ViewInject(R.id.gs7)
    TextView gs7;
    @ViewInject(R.id.gs8)
    TextView gs8;
    @ViewInject(R.id.gs9)
    TextView gs9;
    @ViewInject(R.id.gs10)
    TextView gs10;

    @ViewInject(R.id.zzcq1)
    TextView zzcq1;
    @ViewInject(R.id.zzcq2)
    TextView zzcq2;
    @ViewInject(R.id.zzcq3)
    TextView zzcq3;
    @ViewInject(R.id.zzcq4)
    TextView zzcq4;

    @ViewInject(R.id.qt1)
    TextView qt1;
    @ViewInject(R.id.qt2)
    TextView qt2;

    @ViewInject(R.id.xx1)
    EditText xx1;
    @ViewInject(R.id.xx2)
    EditText xx2;

    @ViewInject(R.id.bt_send)
    Button bt_send;
    public static Handler handler;
    public static List<Integer> list=new ArrayList<>();//点击事件
    public static WaitDialog wd;
    WaitDialog_pdf wp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ViewUtils.inject(this);
        wd=new WaitDialog(this);
        wp=new WaitDialog_pdf(this);
        b_topname.setText("信用报告");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        wd.dismiss();
                        wp.show();
                        GsonUtil request = new GsonUtil(URLconstant.URLINSER+URLconstant.REPORTURL2, RequestMethod.GET);
                        request.add("fileName",  DataManager.ReportText);
                        request.add("sendTo",xx1.getText().toString());//
                        CallServer.getInstance().add(ReportActivity.this, request, MyhttpCallBack.getInstance(), 0x702, true, false, true);
                        break;
                    case  1:
                        wd.dismiss();
                        Toast.show("信用报告请求失败!请重试!");
                        break;
                    case  2:
                        wp.dismiss();
                        Toast.show("信用报告发送失败!请重试!");
                        break;
                    case 3:
                        wp.dismiss();
                        Toast.show("信用报告发送成功!");
                        finish();
                        break;
                }

            }
        };
        init();
    }
    public void init(){
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);

        gs1.setOnClickListener(listener);
        gs2.setOnClickListener(listener);
        gs3.setOnClickListener(listener);
        gs4.setOnClickListener(listener);
        gs5.setOnClickListener(listener);
        gs6.setOnClickListener(listener);
        gs7.setOnClickListener(listener);
        gs8.setOnClickListener(listener);
        gs9.setOnClickListener(listener);
        gs10.setOnClickListener(listener);

        zzcq1.setOnClickListener(listener);
        zzcq2.setOnClickListener(listener);
        zzcq3.setOnClickListener(listener);
        zzcq4.setOnClickListener(listener);

        qt1.setOnClickListener(listener);
        qt2.setOnClickListener(listener);

        bt_send.setOnClickListener(listener);

    }
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.gs1:
                    if(list.get(0)==0){
                        list.set(0, 1);
                        gs1.setBackgroundResource(R.drawable.xxbaogao_tit);
                    }else{
                        list.set(0, 0);
                        gs1.setBackgroundResource(R.drawable.maintop_shape);
                    }
                    break;
                case R.id.gs2:
                    if(list.get(1)==0){
                        list.set(1, 1);
                        gs2.setBackgroundResource(R.drawable.xxbaogao_tit);
                    }else{
                        list.set(1, 0);
                        gs2.setBackgroundResource(R.drawable.maintop_shape);
                    }
                    break;
                case R.id.gs3:
                    if(list.get(2)==0){
                        list.set(2, 1);
                        gs3.setBackgroundResource(R.drawable.xxbaogao_tit);
                    }else{
                        list.set(2, 0);
                        gs3.setBackgroundResource(R.drawable.maintop_shape);
                    }
                    break;
                case R.id.gs4:
                    if(list.get(3)==0){
                        list.set(3, 1);
                        gs4.setBackgroundResource(R.drawable.xxbaogao_tit);
                    }else{
                        list.set(3, 0);
                        gs4.setBackgroundResource(R.drawable.maintop_shape);
                    }
                    break;
                case R.id.gs5:
                    if(list.get(4)==0){
                        list.set(4, 1);
                        gs5.setBackgroundResource(R.drawable.xxbaogao_tit);
                    }else{
                        list.set(4, 0);
                        gs5.setBackgroundResource(R.drawable.maintop_shape);
                    }
                    break;
                case R.id.gs6:
                    if(list.get(5)==0){
                        list.set(5, 1);
                        gs6.setBackgroundResource(R.drawable.xxbaogao_tit);
                    }else{
                        list.set(5, 0);
                        gs6.setBackgroundResource(R.drawable.maintop_shape);
                    }
                    break;
                case R.id.gs7:
                    if(list.get(6)==0){
                        list.set(6, 1);
                        gs7.setBackgroundResource(R.drawable.xxbaogao_tit);
                    }else{
                        list.set(6, 0);
                        gs7.setBackgroundResource(R.drawable.maintop_shape);
                    }
                    break;
                case R.id.gs8:
                    if(list.get(7)==0){
                        list.set(7, 1);
                        gs8.setBackgroundResource(R.drawable.xxbaogao_tit);
                    }else{
                        list.set(7, 0);
                        gs8.setBackgroundResource(R.drawable.maintop_shape);
                    }
                    break;
                case R.id.gs9:
                    if(list.get(8)==0){
                        list.set(8, 1);
                        gs9.setBackgroundResource(R.drawable.xxbaogao_tit);
                    }else{
                        list.set(8, 0);
                        gs9.setBackgroundResource(R.drawable.maintop_shape);
                    }
                    break;
                case R.id.gs10:
                    if(list.get(9)==0){
                        list.set(9, 1);
                        gs10.setBackgroundResource(R.drawable.xxbaogao_tit);
                    }else{
                        list.set(9, 0);
                        gs10.setBackgroundResource(R.drawable.maintop_shape);
                    }
                    break;
                case R.id.zzcq1:
                    if(list.get(10)==0){
                        list.set(10, 1);
                        zzcq1.setBackgroundResource(R.drawable.xxbaogao_tit);
                    }else{
                        list.set(10, 0);
                        zzcq1.setBackgroundResource(R.drawable.maintop_shape);
                    }
                    break;
                case R.id.zzcq2:
                    if(list.get(11)==0){
                        list.set(11, 1);
                        zzcq2.setBackgroundResource(R.drawable.xxbaogao_tit);
                    }else{
                        list.set(11, 0);
                        zzcq2.setBackgroundResource(R.drawable.maintop_shape);
                    }
                    break;
                case R.id.zzcq3:
                    if(list.get(12)==0){
                        list.set(12, 1);
                        zzcq3.setBackgroundResource(R.drawable.xxbaogao_tit);
                    }else{
                        list.set(12,0);
                        zzcq3.setBackgroundResource(R.drawable.maintop_shape);
                    }
                    break;
                case R.id.zzcq4:
                    if(list.get(13)==0){
                        list.set(13, 1);
                        zzcq4.setBackgroundResource(R.drawable.xxbaogao_tit);
                    }else{
                        list.set(13, 0);
                        zzcq4.setBackgroundResource(R.drawable.maintop_shape);
                    }
                    break;
                case R.id.qt1:
                    if(list.get(14)==0){
                        list.set(14, 1);
                        qt1.setBackgroundResource(R.drawable.xxbaogao_tit);
                    }else{
                        list.set(14, 0);
                        qt1.setBackgroundResource(R.drawable.maintop_shape);
                    }
                    break;
                case R.id.qt2:
                    if(list.get(15)==0){
                        list.set(15, 1);
                        qt2.setBackgroundResource(R.drawable.xxbaogao_tit);
                    }else{
                        list.set(15, 0);
                        qt2.setBackgroundResource(R.drawable.maintop_shape);
                    }
                    break;
                case R.id.bt_send:
                    String selectStr="";
                    for(int i=0;i<list.size();i++){
                        if(list.get(i)==1){
                            switch (i){
                                case 0:
                                    selectStr=selectStr+"股东信息,";
                                    break;
                                case 1:
                                    selectStr=selectStr+"主要人员信息,";
                                    break;
                                case 2:
                                    selectStr=selectStr+"分支机构信息,";
                                    break;
                                case 3:
                                    selectStr=selectStr+"变更记录,";
                                    break;
                                case 4:
                                    selectStr=selectStr+"失信被执行人,";
                                    break;
                                case 5:
                                    selectStr=selectStr+"法院判决文书,";
                                    break;
                                case 6:
                                    selectStr=selectStr+"经营异常,";
                                    break;
                                case 7:
                                    selectStr=selectStr+"年报信息,";
                                    break;
                                case 8:
                                    selectStr=selectStr+"股权出质,";
                                    break;
                                case 9:
                                    selectStr=selectStr+"动产抵押,";
                                    break;
                                case 10:
                                    selectStr=selectStr+"著作权,";
                                    break;
                                case 11:
                                    selectStr=selectStr+"软件著作权,";
                                    break;
                                case 12:
                                    selectStr=selectStr+"专利,";
                                    break;
                                case 13:
                                    selectStr=selectStr+"商标,";
                                    break;
                                case 14:
                                    selectStr=selectStr+"招投标,";
                                    break;
                                case 15:
                                    selectStr=selectStr+"招聘,";
                                    break;
                            }
                        }
                    }
                    if(selectStr.equals("")){
                        Toast.show("请选择模块!");
                    }else if(xx1.getText().toString().equals("")){
                        Toast.show("邮箱地址不能为空!");
                    }else if(!UserSetTowActivity.isEmail(xx1.getText().toString())){
                        Toast.show("邮箱地址格式不正确!");
                    }
//                    else if(xx2.getText().toString().equals("")){
//                        Toast.show("手机号码不能为空!");
//                    }else if(!UserSetTowActivity.isNumeric(xx2.getText().toString()) || (xx2.getText().toString()).length()!=11){
//                        Toast.show("手机号码格式不正确!");
//                    }
                    else{
                        wd.show();
                        GsonUtil request = new GsonUtil(URLconstant.URLINSER+URLconstant.REPORTURL1, RequestMethod.GET);
                        request.add("pripid",  DataManager.BaseinfoList.get(0).PRIPID);
                        request.add("select",selectStr);//
                        request.add("entname",  DataManager.BaseinfoList.get(0).ENTNAME);//
                        CallServer.getInstance().add(ReportActivity.this, request, MyhttpCallBack.getInstance(), 0x701, true, false, true);

                    }
                    break;
            }
        }
    };
}
