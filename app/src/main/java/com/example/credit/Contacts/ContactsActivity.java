package com.example.credit.Contacts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;



import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.credit.Entitys.SortModel;
import com.example.credit.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class ContactsActivity extends Activity
{
	public PinyinComparator comparator = new PinyinComparator();
	@ViewInject(R.id.txt_user_list_user_num)
	private TextView userListNumTxt;

	private String userListNumStr;

	@ViewInject(R.id.sild_bar)
	private SideBar  sideBar;

	@ViewInject(R.id.list_view_user_list)
	private ListView sortListView;

	@ViewInject(R.id.txt_dialog)
	private TextView dialogTxt;

	private SearchEditText mSearchEditText;
	private SortAdapter adapter;

	private List<SortModel> sortModelList;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		ViewUtils.inject(this);
		sideBar.setmTextDialog(dialogTxt);
		// on touching listener of side bar
		sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener()
		{

			public void onTouchingLetterChanged(String str)
			{
				int position =  adapter.getPositionForSection(str.charAt(0));
				if (position != -1)
					sortListView.setSelection(position);
			}
		});

		sortListView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		sortListView.setOnItemClickListener(new OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id)
			{
				Toast.makeText(getApplicationContext(), ((SortModel)adapter.getItem(position)).name, Toast.LENGTH_SHORT).show();
			}
		});


		// call filledData to get datas
		try
		{
			sortModelList =  filledData(getResources().getStringArray(R.array.date));
		} catch (BadHanyuPinyinOutputFormatCombination e1)
		{
			e1.printStackTrace();
		}

		userListNumTxt.setText("全部："+"\t"+sortModelList.size()+"个联系人");

		// sort by a-z
		Collections.sort(sortModelList, comparator);
		adapter = new SortAdapter(getApplicationContext(), sortModelList);
		sortListView.setAdapter(adapter);


		// search 
		mSearchEditText = (SearchEditText) findViewById(R.id.txt_filter_edit);

		// filter 
		mSearchEditText.addTextChangedListener(new TextWatcher()
		{

			public void onTextChanged(CharSequence str, int arg1, int arg2, int arg3)
			{
				try
				{
					filerData(str.toString());
				}
				catch (BadHanyuPinyinOutputFormatCombination e)
				{
					e.printStackTrace();
				}
			}

			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3)
			{
			}

			public void afterTextChanged(Editable arg0)
			{
			}
		});
	}

	private List<SortModel> filledData(String [] date) throws BadHanyuPinyinOutputFormatCombination{
		List<SortModel> mSortList = new ArrayList<SortModel>();

		for(int i=0; i<date.length; i++){
			SortModel sortModel=new SortModel();
			sortModel.name=(date[i]);
			//汉字转换成拼音
			String pinyin = PinYinKit.getPingYin(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// 正则表达式，判断首字母是否是英文字母
			if(sortString.matches("[A-Z]")){
				sortModel.sortLetters=(sortString.toUpperCase());
			}else{
				sortModel.sortLetters=("#");
			}

			mSortList.add(sortModel);
		}
		return mSortList;

	}

	private void filerData(String str) throws BadHanyuPinyinOutputFormatCombination
	{
		List<SortModel> fSortModels = new ArrayList<SortModel>();

		if (TextUtils.isEmpty(str))
			fSortModels = sortModelList;
		else
		{
			fSortModels.clear();
			for (SortModel sortModel : sortModelList)
			{
				String name = sortModel.name;
				if (name.indexOf(str.toString()) != -1 ||
						PinYinKit.getPingYin(name).startsWith(str.toString()) || PinYinKit.getPingYin(name).startsWith(str.toUpperCase().toString()))
				{
					fSortModels.add(sortModel);
				}
			}

		}
		Collections.sort(fSortModels, comparator);
		adapter.updateListView(fSortModels);
	}

	public void changeDatas(List<SortModel> mSortList , String str)
	{
		userListNumTxt.setText(str+":"+"\t"+mSortList.size()+"个联系人");

		Collections.sort(mSortList, comparator);
		adapter.updateListView(mSortList);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
