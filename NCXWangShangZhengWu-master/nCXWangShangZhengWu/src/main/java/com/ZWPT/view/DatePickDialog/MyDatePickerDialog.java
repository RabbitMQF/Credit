package com.ZWPT.view.DatePickDialog;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.WSZW.Activity.R;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

/**
 * 
 * @author meiyang
 * 
 */

public class MyDatePickerDialog extends AlertDialog {

	private static OnDateSetListener mCallBack;

	private static AlertDialog alertdialog;
	private View view;
	private static WheelView wv_year;
	private static WheelView wv_month;
	private static WheelView wv_day;
	private WheelView wv_hours;
	private WheelView wv_mins;
	public int screenheight;
	private boolean hasSelectTime;
	private static int START_YEAR = 1990, END_YEAR = 2100;

	private static final String YEAR = "year";
	private static final String MONTH = "month";
	private static final String DAY = "day";

	private TextView timepicker_titletextview;

	public static class MyOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			alertdialog.dismiss();
			if (mCallBack != null) {
				mCallBack.onDateSet(null, getYear(), getMonth(),
						getDayOfMonth());
			}

		}

	}

	public interface OnDateSetListener {

		/***
		 * @param view
		 *            The view associated with this listener.
		 * @param year
		 *            The year that was set.
		 * @param monthOfYear
		 *            The month that was set (0-11) for compatibility with
		 *            {@link java.util.Calendar}.
		 * @param dayOfMonth
		 *            The day of the month that was set.
		 */
		void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth);
	}

	public MyDatePickerDialog(Context context, OnDateSetListener callBack,
			int year, int monthOfYear, int dayOfMonth) {
		this(context, 0, callBack, year, monthOfYear, dayOfMonth);
	}

	public MyDatePickerDialog(Context context, int theme,
			OnDateSetListener callBack, int year, int monthOfYear,
			int dayOfMonth) {
		super(context, theme);

		mCallBack = callBack;

		alertdialog = this;

		Log.i("test", "" + (getButton(BUTTON_POSITIVE) == null));
		// setButton(BUTTON_POSITIVE, "����", this);
		// setButton(BUTTON_NEGATIVE, "ȡ��", (OnClickListener) null);
		setIcon(0);
		// setTitle("��������");

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// setCustomTitle(titleview);

		View view = inflater.inflate(R.layout.timepicker, null);
		timepicker_titletextview = (TextView) view
				.findViewById(R.id.timepicker_titletextview);
		timepicker_titletextview.setText(year + "年" + monthOfYear + "月"
				+ dayOfMonth + "日"
				+ getWeekOfDate(year, monthOfYear, dayOfMonth));
		this.view = view;
		setView(view);

		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metric = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(metric);
		this.screenheight = metric.heightPixels;
		initDateTimePicker(year, monthOfYear, dayOfMonth);

	}

	public void setMyButton(int whichButton, CharSequence text,
			MyOnClickListener listener) {

		if (whichButton == BUTTON_POSITIVE) {
			Button positive = (Button) view
					.findViewById(R.id.timepicker_positive);
			positive.setVisibility(View.VISIBLE);
			positive.setText(text);

			positive.setOnClickListener(listener);

		} else if (whichButton == BUTTON_NEGATIVE) {
			Button negative = (Button) view
					.findViewById(R.id.timepicker_negative);
			negative.setVisibility(View.VISIBLE);
			negative.setText(text);
			negative.setOnClickListener(listener);

		} else if (whichButton == BUTTON_NEUTRAL) {
			Button neutral = (Button) view
					.findViewById(R.id.timepicker_neutral);
			neutral.setVisibility(View.VISIBLE);
			neutral.setText(text);
			neutral.setOnClickListener(listener);
		}

	}

	public void initDateTimePicker(int year, int month, int day) {
		this.initDateTimePicker(year, month, day, 0, 0);
	}

	public void initDateTimePicker(int year, int month, int day, int h, int m) {
		// int year = calendar.get(Calendar.YEAR);
		// int month = calendar.get(Calendar.MONTH);
		// int day = calendar.get(Calendar.DATE);
		// 添加大小月月份并将其转换为list,方便之后的判�?
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };

		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);

		// �?
		wv_year = (WheelView) view.findViewById(R.id.year);
		wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"�?的显示数�?
		wv_year.setCyclic(true);// 可循环滚�?
		wv_year.setLabel("年");// 添加文字
		wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数�?

		// �?
		wv_month = (WheelView) view.findViewById(R.id.month);
		wv_month.setAdapter(new NumericWheelAdapter(1, 12));
		wv_month.setCyclic(true);
		wv_month.setLabel("月");
		wv_month.setCurrentItem(month);

		// �?
		wv_day = (WheelView) view.findViewById(R.id.day);
		wv_day.setCyclic(true);
		// 判断大小月及是否闰年,用来确定"�?的数�?
		if (list_big.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 31));
		} else if (list_little.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 30));
		} else {
			// 闰年
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				wv_day.setAdapter(new NumericWheelAdapter(1, 29));
			else
				wv_day.setAdapter(new NumericWheelAdapter(1, 28));
		}
		wv_day.setLabel("日");
		wv_day.setCurrentItem(day - 1);

		wv_hours = (WheelView) view.findViewById(R.id.hour);
		wv_mins = (WheelView) view.findViewById(R.id.min);
		if (hasSelectTime) {
			wv_hours.setVisibility(View.VISIBLE);
			wv_mins.setVisibility(View.VISIBLE);

			wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
			wv_hours.setCyclic(true);// 可循环滚�?
			wv_hours.setLabel("时");// 添加文字
			wv_hours.setCurrentItem(h);

			wv_mins.setAdapter(new NumericWheelAdapter(0, 59));
			wv_mins.setCyclic(true);// 可循环滚�?
			wv_mins.setLabel("分");// 添加文字
			wv_mins.setCurrentItem(m);
		} else {
			wv_hours.setVisibility(View.GONE);
			wv_mins.setVisibility(View.GONE);
		}

		// 添加"�?监听
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int year_num = newValue + START_YEAR;
				// 判断大小月及是否闰年,用来确定"�?的数�?
				if (list_big
						.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(wv_month
						.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if ((year_num % 4 == 0 && year_num % 100 != 0)
							|| year_num % 400 == 0)
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
					else
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
				}
				timepicker_titletextview.setText(getTime());

			}
		};
		// 添加"�?监听
		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				// 判断大小月及是否闰年,用来确定"�?的数�?
				if (list_big.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
							.getCurrentItem() + START_YEAR) % 100 != 0)
							|| (wv_year.getCurrentItem() + START_YEAR) % 400 == 0)
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
					else
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
				}

				timepicker_titletextview.setText(getTime());

			}
		};
		wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);
		wv_day.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				timepicker_titletextview.setText(getTime());
			}
		});
		// 根据屏幕密度来指定�?择器字体的大�?不同屏幕可能不同)
		int textSize = 0;
		if (hasSelectTime)
			textSize = (screenheight / 100) * 3;
		else
			textSize = (screenheight / 100) * 4;
		wv_day.TEXT_SIZE = textSize;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;
		wv_hours.TEXT_SIZE = textSize;
		wv_mins.TEXT_SIZE = textSize;

	}

	public String getTime() {
		StringBuffer sb = new StringBuffer();
		if (!hasSelectTime)
			sb.append(getYear())
					.append("年")
					.append(getMonth())
					.append("月")
					.append(getDayOfMonth() + "日")
					.append(getWeekOfDate(getYear(), getMonth(),
							getDayOfMonth()));
		else
			sb.append(getYear()).append("年").append(getMonth()).append("月")
					.append(getDayOfMonth()).append("日")
					.append(wv_hours.getCurrentItem()).append("时")
					.append(wv_mins.getCurrentItem()).append("分");
		return sb.toString();
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		int year = savedInstanceState.getInt(YEAR);
		int month = savedInstanceState.getInt(MONTH);
		int day = savedInstanceState.getInt(DAY);
		wv_year.setCurrentItem(year - START_YEAR);
		wv_month.setCurrentItem(month - 1);
		wv_day.setCurrentItem(day - 1);

	}

	@Override
	public Bundle onSaveInstanceState() {
		Bundle state = super.onSaveInstanceState();
		state.putInt(YEAR, wv_year.getCurrentItem() + START_YEAR);
		state.putInt(MONTH, wv_month.getCurrentItem() + 1);
		state.putInt(DAY, wv_day.getCurrentItem() + 1);
		return state;
	}

	private String getWeekOfDate(int year, int month, int day) {
		String[] weekDays = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" };
		Calendar cal = Calendar.getInstance();

		cal.set(year, month, day);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	public static int getYear() {
		return wv_year.getCurrentItem() + START_YEAR;
	}

	public static int getMonth() {
		return wv_month.getCurrentItem() + 1;
	}

	public static int getDayOfMonth() {
		return wv_day.getCurrentItem() + 1;
	}
}
