package com.WSZW.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.WSZW.Activity.R;
import com.ZWPT.view.DatePickDialog.JudgeDate;
import com.ZWPT.view.DatePickDialog.ScreenInfo;
import com.ZWPT.view.DatePickDialog.WheelMain;
import com.ZWPT.view.DatePickDialog.WheelMain.OnChangeListener;

public class DatePickDialog {

	public static void showDateCheckDialog(Activity activity,
			final TextView tv, boolean flag, final OnClickListener c) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		final WheelMain wheelMain;
		LayoutInflater inflater = LayoutInflater.from(activity);
		final View timepickerview = inflater.inflate(R.layout.timepicker, null);
		ScreenInfo screenInfo = new ScreenInfo(activity);
		wheelMain = new WheelMain(timepickerview, flag);
		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		wheelMain.setOnchangerlistener(new OnChangeListener() {

			@Override
			public void change(String time) {
				// TODO Auto-generated method stub
				Log.i("test", time);
				builder.setTitle(time);
			}
		});
		wheelMain.screenheight = screenInfo.getHeight();
		String time = tv.getText().toString();
		Calendar calendar = Calendar.getInstance();
		if (JudgeDate.isDate(time, "yyyy-MM-dd")) {
			try {
				calendar.setTime(dateFormat.parse(time));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		wheelMain.initDateTimePicker(year, month, day, hour, minute, second);
		builder.setTitle("请选择日期").setView(timepickerview)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						tv.setText(wheelMain.getTime());
						if (c != null) {
							c.onClick(dialog, which);
						}
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});

		builder.show();

	}

	public static void showDateCheckDialog(Activity activity,
			final TextView tv, boolean flag) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		final WheelMain wheelMain;
		LayoutInflater inflater = LayoutInflater.from(activity);
		final View timepickerview = inflater.inflate(R.layout.timepicker, null);
		ScreenInfo screenInfo = new ScreenInfo(activity);
		wheelMain = new WheelMain(timepickerview, flag);
		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		wheelMain.setOnchangerlistener(new OnChangeListener() {

			@Override
			public void change(String time) {
				// TODO Auto-generated method stub
				Log.i("test", time);
				builder.setTitle(time);
			}
		});
		wheelMain.screenheight = screenInfo.getHeight();
		String time = tv.getText().toString();
		Calendar calendar = Calendar.getInstance();
		if (JudgeDate.isDate(time, "yyyy-MM-dd")) {
			try {
				calendar.setTime(dateFormat.parse(time));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		wheelMain.initDateTimePicker(year, month, day, hour, minute, second);
		builder.setTitle("请选择日期").setView(timepickerview)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						tv.setText(wheelMain.getTime());
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});

		builder.show();
	}
}
