package com.WSZW.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * @author yuhuihui
 * @data 2014-6-5
 */
public class BaseFragment extends Fragment {
	protected Context context;
	private Class<? extends Activity> destActivity;
	protected static final int CODE_REQUEST_LOGIN = 100;
	protected static ActivityApp app;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.context = getActivity();
		if (app == null) {
			app = (ActivityApp) context.getApplicationContext();
		}
	}

	/**
	 * 长时间的toast
	 * 
	 * @author yuhuihui
	 * @data 2014-6-5
	 */
	public void showToastLong(String... value) {
		if (value == null || value.length == 0) {
			Toast.makeText(context, "网络不给力!", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(context, value[0], Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 短时间的toast
	 * 
	 * @author yuhuihui
	 * @data 2014-6-5
	 */
	public void showToastShort(String... value) {
		if (value == null || value.length == 0) {
			Toast.makeText(context, "网络不给力!", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(app, value[0], Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// // 可以根据多个请求代码来作相应的操作
		// if (requestCode == CODE_REQUEST_LOGIN && resultCode ==
		// Activity.RESULT_OK) {
		// boolean loginResult = data.getBooleanExtra("loginResult", false);
		// if (loginResult ) {
		// startActivity(new Intent(context, destActivity));
		// }
		// }
	}
}
