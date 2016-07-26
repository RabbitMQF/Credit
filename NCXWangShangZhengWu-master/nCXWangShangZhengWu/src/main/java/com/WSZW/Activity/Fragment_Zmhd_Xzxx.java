package com.WSZW.Activity;

import com.WSZW.data.InfoFile_;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.getSaveLetterManagerService;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_Zmhd_Xzxx extends Fragment {
	
	InfoFile_ infoFile_;
	private Spinner spinner,spinner_type;
	View view;
	private Button tj;
	public RadioButton radio_boy, radio_girl,radio_button;
	public RadioGroup radio_group;
	private EditText edt_name,edt_phone,edt_age,edt_email,edt_address,edt_zjhm,edt_zy,edt_zt,edt_nr;
	String name,phone,age,email,address,zjhm,zy,zt,nr,group,zjtype,type,typeId;
	TextView tv1,tv2,tv3;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_zmhd_xzxx, container,
				false);
		inited();
		tj.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				show();
				// TODO 自动生成的方法存根
				
			}
		});
		return view;
	}
	
	private void inited() {
		// TODO 自动生成的方法存根
		infoFile_=new InfoFile_(getActivity());
		spinner = (Spinner) view.findViewById(R.id.select);
		spinner.setOnItemSelectedListener(new OnItemSelectedListenerImpl());
		radio_boy = (RadioButton) view.findViewById(R.id.radio_boy);
		radio_girl = (RadioButton) view.findViewById(R.id.radio_girl);
		radio_group = (RadioGroup) view.findViewById(R.id.radioGroup);
		radio_button = (RadioButton) view.findViewById(radio_group.getCheckedRadioButtonId());
		spinner_type = (Spinner) view.findViewById(R.id.select_type);
		spinner_type.setOnItemSelectedListener(new OnItemSelectedListenerImpl());
		
		edt_name = (EditText) view.findViewById(R.id.edt_name);
		edt_phone = (EditText) view.findViewById(R.id.edt_phone);
		edt_age = (EditText) view.findViewById(R.id.edt_age);
		edt_address = (EditText) view.findViewById(R.id.edt_address);
		edt_zjhm = (EditText) view.findViewById(R.id.edt_num);
		edt_zy = (EditText) view.findViewById(R.id.edt_zhiye);
		edt_nr = (EditText) view.findViewById(R.id.edt_neirong);
		edt_email = (EditText) view.findViewById(R.id.edt_email);
		edt_zt = (EditText) view.findViewById(R.id.edt_zhuti);
		tj = (Button) view.findViewById(R.id.btn_list_1);
		
		//tv1 = (TextView) view.findViewById(R.id.tv_zt);
		//tv2 = (TextView) view.findViewById(R.id.tv_nr);
		//tv3 = (TextView) view.findViewById(R.id.tv_type);
	}
	private void show() {
		// TODO 自动生成的方法存根
		name = edt_name.getText().toString();
		phone = edt_phone.getText().toString();
		age = edt_age.getText().toString();
		email = edt_email.getText().toString();
		address = edt_address.getText().toString();
		zjhm = edt_zjhm.getText().toString();
		zy = edt_zy.getText().toString();
		nr = edt_nr.getText().toString();
		group = radio_button.getText().toString();
		zt = edt_zt.getText().toString();
		zjtype = spinner.getSelectedItem().toString();
		type = spinner_type.getSelectedItem().toString();
		
		/*SpannableStringBuilder builder1 = new SpannableStringBuilder(tv1.getText().toString());
		SpannableStringBuilder builder2 = new SpannableStringBuilder(tv2.getText().toString());
		SpannableStringBuilder builder3 = new SpannableStringBuilder(tv3.getText().toString());
		
		ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
		builder1.setSpan(redSpan, 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		builder2.setSpan(redSpan, 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); 
		builder3.setSpan(redSpan, 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); 
		
		tv1.setText(builder1);
		tv2.setText(builder2);
		tv3.setText(builder3);*/
		
		String[] metaJson = {"性别"+":"+group,"性别"+":"+group,"年龄"+":"+age,"地址"+":"+address,"证件类型"+":"+zjtype,
				"证件号码"+":"+zjhm,"职业"+":"+zy};
		StringBuffer sb = new StringBuffer(); 
		for(int j = 0; j < metaJson.length; j++){
			sb. append(metaJson[j]); 
		} 
		String newStr = sb.toString();
		String i = null;
		if(type.equals("咨询")){
			i = "1";
		}else if(type.equals("投诉")){
			i= "2";
		}else if(type.equals("建议")){
			i= "3";
		}else if(type.equals("意见")){
			i = "4";
		}else if(type.equals("其它")){
			i = "7";
		}else if(type.equals("求助")){
			i = "9";
		}
		
		getSaveLetter.getSaveLetter(getActivity(), 1, nr, email, i, type, newStr, phone, zt, name);
	}
	//下拉框选择事件  
	    private class OnItemSelectedListenerImpl implements OnItemSelectedListener {  
	        public void onItemSelected(AdapterView<?> parent, View view,  
	                int position, long id) {  
	            String city = parent.getItemAtPosition(position).toString();  
	        }  
	  
	        @Override  
	        public void onNothingSelected(AdapterView<?> parent) {  
	            // TODO Auto-generated method stub  
	        }  
  
	    }
	    String cxbh;
	    getSaveLetterManagerService getSaveLetter = new getSaveLetterManagerService() {

			@Override
			protected void handlerLoginInfo(Context paramActivity,
					HandleResult handleResult, int paramInt) {
				// TODO 自动生成的方法存根
				if(handleResult.getiSuccess() != null && handleResult.getiSuccess().equals("success")){
					cxbh = infoFile_.result().get().toString();
					showDaolog();
				}
			}

			
	    	
		};
		private void showDaolog() {
			if(zt.equals("") || nr.equals("")){
				Toast.makeText(getActivity(), "提交信息不能有空", Toast.LENGTH_LONG).show();
			}else{
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
				alertDialog.setMessage("查询编号"+cxbh);
				alertDialog.setTitle("提示框");
				alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO 自动生成的方法存根
						edt_address.setText("");
						edt_age.setText("");
						edt_email.setText("");
						edt_name.setText("");
						edt_nr.setText("");
						edt_phone.setText("");
						edt_zjhm.setText("");
						edt_zt.setText("");
						edt_zy.setText("");
					}
				}).setNegativeButton("返回", null);
				alertDialog.create().show();
			}
			
		}
}



