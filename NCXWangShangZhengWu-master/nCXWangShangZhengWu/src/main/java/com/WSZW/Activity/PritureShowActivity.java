package com.WSZW.Activity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.Sjdj_PritureManagerService;
import com.WSZW.util.PriUtil;
import com.WSZW.util.StringUtil;
import com.WSZW.view.AlertDialog;
import com.WSZW.view.LoadingDialog;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.KeyEvent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

/**
 * 图片展示activity
 * 
 * @author Administrator
 * 
 */
@EActivity(R.layout.layout_priture_show)
public class PritureShowActivity extends Activity {

	@Pref
	InfoFile_ infoFile_;

	@ViewById
	ImageButton ib_return, ib_priture_muen;

	@ViewById
	TextView tv_void_priture, tv_priture_show_title;
	@ViewById
	Button btn_wancheng_priture, btn_add_priture;
	@ViewById
	LinearLayout ll_tupianliulanqi, ll_priture_info;
	@ViewById
	ImageSwitcher is_priture_show;

	private List<Bitmap> oBitmaps = new ArrayList<Bitmap>();// 图片集合
	private List<String> pritureId_list = new ArrayList<String>();// 服务端图片id，用于删除图片
	private ImageView[] imageViews;// 动态生成imageview个数

	private int index;

	private LoadingDialog loadingDialog;// 进度对话框
	private List<String> imgList = new ArrayList<String>();// 服务端图片路径集合，用于电子材料图片展示

	DisplayMetrics dm;

	Map<String, Object> map_savepictures = null;
	String name;
	boolean addnew = false;

	int index_pic_site = 0;

	@AfterViews
	void initView() {
		name = getIntent().getStringExtra("postinfoname");// 获取电子材料adapter，传递的材料名
		index_pic_site = getIntent().getIntExtra("index", 0);
		String imgUrl = getIntent().getStringExtra("pritureUrl");// 获取电子材料adapter，传递的图片路径
		dm = new DisplayMetrics();
		dm = this.getApplicationContext().getResources().getDisplayMetrics();
		if (Constants.list_tjcl_for_save_uploaded_pictures != null
				&& Constants.list_tjcl_for_save_uploaded_pictures.size() != 0) {
			for (int i = 0; i < Constants.list_tjcl_for_save_uploaded_pictures
					.size(); i++) {
				if (Constants.list_tjcl_for_save_uploaded_pictures.get(i)
						.get("postinfoname").equals(name)) {
					map_savepictures = Constants.list_tjcl_for_save_uploaded_pictures
							.get(i);
				}
			}
		}
		if (map_savepictures == null) {
			addnew = true;
			map_savepictures = new HashMap<String, Object>();
			map_savepictures.put("postinfoname", name);
		}

		// 调接口，如果返回的数据为空，则显示textview “暂无数据”
		// 如果有数据获取这些数据存入List<Bitmap>,并且让tv_void_priture隐藏,ll_tupianliulanqi显示
		tv_void_priture.setVisibility(View.VISIBLE);
		ll_tupianliulanqi.setVisibility(View.GONE);
		ib_priture_muen.setVisibility(View.INVISIBLE);

		ib_return.setOnClickListener(onClickListener);
		btn_add_priture.setOnClickListener(onClickListener);
		btn_wancheng_priture.setOnClickListener(onClickListener);
		// is_priture_show.setOnLongClickListener(onLongClickListener);

		// imageswitcher初始化工厂，用于图片的切换显示
		is_priture_show.setFactory(new ViewFactory() {

			@Override
			public View makeView() {
				// 创建iamgeview，设置大小格式
				ImageView img = new ImageView(PritureShowActivity.this);
				img.setScaleType(ScaleType.FIT_CENTER);
				img.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				return img;
			}
		});

		if (name != null) {
			tv_priture_show_title.setText(name);
		}
		if (imgUrl != null) {

			imgList = StringUtil.stringToList(imgUrl);// 如果电子材料传递的数据不为空，以“，”分割图片路径
			btn_add_priture.setVisibility(View.GONE);// 将添加图片按钮隐藏
			btn_wancheng_priture.setVisibility(View.GONE);// 将完成按钮隐藏
			loadingDialog = new LoadingDialog(PritureShowActivity.this, "",
					true);// 初始化进度框
			new ImgShowThread().start();// 开启线程异步获取图片

		} else {
			if (Constants.list_tjcl_for_save_uploaded_pictures != null
					&& Constants.list_tjcl_for_save_uploaded_pictures.size() != 0) {
				for (int i = 0; i < Constants.list_tjcl_for_save_uploaded_pictures
						.size(); i++) {
					if (Constants.list_tjcl_for_save_uploaded_pictures.get(i)
							.get("postinfoname").equals(name)) {
						pritureId_list = (List<String>) Constants.list_tjcl_for_save_uploaded_pictures
								.get(i).get("list_pictures_id");
						imgList = (List<String>) Constants.list_tjcl_for_save_uploaded_pictures
								.get(i).get("list_pictures_path");
					}
				}
				if (imgList != null) {
					for (int j = 0; j < imgList.size(); j++) {
						showPriture(imgList.get(j));
					}
				}
			}
			is_priture_show.setOnLongClickListener(onLongClickListener);
		}
	}

	private class ImgShowThread extends Thread {
		@Override
		public void run() {
			super.run();
			List<Bitmap> oList = new ArrayList<Bitmap>();
			for (int i = 0; i < imgList.size(); i++) {
				Bitmap bitmap = PriUtil.getBitmapforZxsb(imgList.get(i));// 获取图片资源
				oList.add(bitmap);// 添加至集合中，用于展示
			}

			Message message = handler.obtainMessage(0x123);
			message.obj = oList;
			handler.sendMessage(message);
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				List<Bitmap> bitmaps = (List<Bitmap>) msg.obj;
				for (int i = 0; i < bitmaps.size(); i++) {
					oBitmaps.add(bitmaps.get(i));
				}
				// 获取到图片资源，显示展示图片view
				if (ll_tupianliulanqi.getVisibility() == View.GONE) {
					ll_tupianliulanqi.setVisibility(View.VISIBLE);
				}
				// 隐藏textview
				if (tv_void_priture.getVisibility() == View.VISIBLE) {
					tv_void_priture.setVisibility(View.GONE);
				}
				initImageView(oBitmaps);// 将获取的图片资源集合进行展示

			}
		};
	};

	/*
	 * 长按事件
	 */
	private OnLongClickListener onLongClickListener = new OnLongClickListener() {

		@Override
		public boolean onLongClick(View v) {
			AlertDialog dialog = new AlertDialog(PritureShowActivity.this);
			dialog.setTitle("提示信息").setMessage("是否删除该图片？").setEnsureText("确定")
					.setCancelText("取消")
					.setOnClickListener(new AlertDialog.OnClickAdapter() {
						@Override
						public void onEnsureClick(AlertDialog dialog) {
							// 调用删除图片接口
							List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
							Map<String, Object> oMap = new HashMap<String, Object>();
							oMap.put("imageId", pritureId_list.get(index));
							list.add(oMap);
							imgList.remove(index);
							map_savepictures.put("list_pictures_path", imgList);
							pritureManagerService.deletePritureInfo(
									PritureShowActivity.this, 2, list);
							// Toast.makeText(PritureShowActivity.this,
							// "删除图片"+index+"id"+pritureId_list.get(index),
							// Toast.LENGTH_SHORT).show();
						}

						@Override
						public void onCancelClick(AlertDialog dialog) {
							dialog.dismiss();
						}
					}).cancelable(false).show();
			return false;
		}
	};

	/*
	 * 点击事件
	 */
	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ib_return:
				// 返回
				if (addnew) {
					if (Constants.list_tjcl_for_save_uploaded_pictures != null) {
						Constants.list_tjcl_for_save_uploaded_pictures
								.add(map_savepictures);
					}else {Constants.list_tjcl_for_save_uploaded_pictures=new ArrayList<Map<String, Object>>();
					Constants.list_tjcl_for_save_uploaded_pictures
					.add(map_savepictures);
					}
				} else {
					if (Constants.list_tjcl_for_save_uploaded_pictures.size() != 0) {
						for (int i = 0; i < Constants.list_tjcl_for_save_uploaded_pictures
								.size(); i++) {
							if (Constants.list_tjcl_for_save_uploaded_pictures
									.get(i).get("postinfoname").equals(name)) {
								if (map_savepictures.get("list_pictures_id") != null) {
									Constants.list_tjcl_for_save_uploaded_pictures
											.set(i, map_savepictures);
								}
							}
						}
					}
				}
				finish();
				break;
			case R.id.btn_add_priture:
				// 弹出对话框选择获取图片方式
				Intent intent = new Intent(PritureShowActivity.this,
						ChiocePritureMethodActiivty_.class);
				startActivityForResult(intent, 1);
				break;
			case R.id.btn_wancheng_priture:

				if (addnew) {
					
					if (Constants.list_tjcl_for_save_uploaded_pictures != null) {
						Constants.list_tjcl_for_save_uploaded_pictures
								.add(map_savepictures);
					}else {Constants.list_tjcl_for_save_uploaded_pictures=new ArrayList<Map<String, Object>>();
					Constants.list_tjcl_for_save_uploaded_pictures
					.add(map_savepictures);
					}
				} else {
					if (Constants.list_tjcl_for_save_uploaded_pictures.size() != 0) {
						for (int i = 0; i < Constants.list_tjcl_for_save_uploaded_pictures
								.size(); i++) {
							if (Constants.list_tjcl_for_save_uploaded_pictures
									.get(i).get("postinfoname").equals(name)) {
								if (map_savepictures.get("list_pictures_id") != null) {
									Constants.list_tjcl_for_save_uploaded_pictures
											.set(i, map_savepictures);
								}
							}
						}
					}
				}

				finish();
				break;
			}
		}
	};

	/**
	 * 初始化图片浏览器
	 * 
	 * @param bitmaps
	 */
	public void initImageView(List<Bitmap> bitmaps) {

		if (bitmaps.size() > 0) {
			// 清空线性布局里面的imageview
			if (ll_priture_info.getChildCount() > 0) {
				ll_priture_info.removeAllViews();
			}

			int screenWidth = dm.widthPixels / 4;

			int screenHeight = dm.heightPixels / 7;
			if (bitmaps.size() > 0) {
				// 默认显示第一张
				is_priture_show.setImageDrawable(new BitmapDrawable(bitmaps
						.get(0)));
				// 初始化imageview数组
				imageViews = new ImageView[bitmaps.size()];
				for (int i = 0; i < imageViews.length; i++) {
					// 初始化imageview，设置imageview的参数
					imageViews[i] = new ImageView(PritureShowActivity.this);
					imageViews[i].setTag(bitmaps.get(i));
					imageViews[i].setImageBitmap(bitmaps.get(i));
					imageViews[i].setLayoutParams(new LayoutParams(screenWidth,
							screenHeight));
					imageViews[i].setPadding(0, 0, 5, 0);
					imageViews[i].setOnClickListener(new MyListener());
					imageViews[i].setAlpha(100);
					ll_priture_info.addView(imageViews[i]);
				}
				imageViews[0].setAlpha(255);
			}

			// 当进度框不为空时，隐藏
			if (loadingDialog != null) {
				loadingDialog.dismiss();
			}
		} else {
			tv_void_priture.setVisibility(View.VISIBLE);
			Toast.makeText(PritureShowActivity.this, "未获取图片信息",
					Toast.LENGTH_SHORT).show();
		}
	}

	/*
	 * 多张图片的点击事件
	 */
	private class MyListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Bitmap bitmap = (Bitmap) v.getTag();// 获取当前单击的图片对象
			is_priture_show.setImageDrawable(new BitmapDrawable(bitmap));// 在imageswitcher中显示当前的图片资源
			for (int i = 0; i < oBitmaps.size(); i++) {
				if (oBitmaps.get(i).equals(bitmap)) {
					index = i;// 循环遍历，获取当前图片的下标，用于查找当前图片对应的图片id
				}
			}
			setAlpha(imageViews);
			((ImageView) v).setAlpha(255);// 当前imageview的图片透明度设为高亮
		}

	}

	/*
	 * 设置所有图片的透明度
	 */
	public void setAlpha(ImageView[] imageViews) {
		for (int i = 0; i < imageViews.length; i++) {
			imageViews[i].setAlpha(100);
		}
	}

	/*
	 * 获取接口回调的数据
	 */
	private Sjdj_PritureManagerService pritureManagerService = new Sjdj_PritureManagerService() {

		@Override
		public void handlerUpLoadPritureInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
			switch (paramInt) {
			case 1:
				if (paramHandleResult.getUploadPritureSuccess().equals(
						"success")) {
					Constants.imgbool = false;

					// 获取上传图片的id
					pritureId_list.add(paramHandleResult.getPriture_list().get(
							0));
					// Constants.list_uploadedPictures_ID = pritureId_list;
					map_savepictures.put("list_pictures_id", pritureId_list);
					Log.e("**********upload**************", "上传图片id"
							+ paramHandleResult.getPriture_list().get(0));
					Toast.makeText(PritureShowActivity.this, "图片上传成功！",
							Toast.LENGTH_SHORT).show();
					showPriture(path_upload);
				} else if (paramHandleResult.getUploadPritureSuccess().equals(
						"fail")) {
					 Constants.imgbool = true;
					Toast.makeText(PritureShowActivity.this, "图片上传失败，请重新选择图片！",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case 2:
				if (paramHandleResult.getDeletePritureSuccess().equals(
						"success")) {
					Toast.makeText(PritureShowActivity.this,
							paramHandleResult.getGetDeletePritureInfo(),
							Toast.LENGTH_SHORT).show();
					Log.e("********delete************", "删除图片" + index + "id"
							+ pritureId_list.get(index));
					// 图片删除成功后，删除该当前下标的图片资源
					oBitmaps.remove(index);
					// 删除当前下标下的图片id
					pritureId_list.remove(index);
					map_savepictures.put("list_pictures_id", pritureId_list);
					// 下标清空
					index = 0;
					// 刷新图片浏览
					initImageView(oBitmaps);
					// 当图片全部删除后，隐藏线性布局，展示textview
					if (oBitmaps.size() == 0) {
						if (ll_tupianliulanqi.getVisibility() == View.VISIBLE) {
							ll_tupianliulanqi.setVisibility(View.GONE);
						}
						if (tv_void_priture.getVisibility() == View.GONE) {
							tv_void_priture.setVisibility(View.VISIBLE);
						}
					}
				} else if (paramHandleResult.getUploadPritureSuccess().equals(
						"fail")) {
					Toast.makeText(PritureShowActivity.this,
							paramHandleResult.getGetDeletePritureInfo(),
							Toast.LENGTH_SHORT).show();
				}
				break;
			}
		}
	};

	/**
	 * 弹出对话框，用户确认上传图片
	 * 
	 * @param path
	 */
	public void showIsUploadDialog(final String path) {
		AlertDialog dialog = new AlertDialog(PritureShowActivity.this);
		dialog.setTitle("提示信息").setMessage("是否确认上传该图片？").setEnsureText("确定")
				.setCancelText("取消")
				.setOnClickListener(new AlertDialog.OnClickAdapter() {
					@Override
					public void onEnsureClick(AlertDialog dialog) {
						// 获取压缩后的图片二进制流
						String ss = getimage(path);
						List<Map<String, Object>> oList = new ArrayList<Map<String, Object>>();
						Map<String, Object> oMap = new HashMap<String, Object>();
						// 获取入参信息
						String accountId = infoFile_.infoUserId().get();
						String serviceSubjectId = infoFile_.serviceSubjectId()
								.get();
						String name = infoFile_.postinfoname().get();
						String holderName = infoFile_.shengqingrenname().get();
						String holderIdentityNumber = infoFile_
								.shengqingrenCardId().get();

						String identityType = infoFile_.postinfotype().get();
						String materialId = infoFile_.postinfoid().get();

						oMap.put("accountId", accountId);// 用户id
						oMap.put("serviceSubjectId", serviceSubjectId);// 事项id
						oMap.put("name", name);// 提交材料名
						oMap.put("holderName", holderName);// 申请人姓名
						oMap.put("holderIdentityNumber", holderIdentityNumber);// 申请人身份证号
						oMap.put("identityType", identityType);// 提交材料的类型
						oMap.put("materialId", materialId);// 提交材料的ID
						oMap.put("imageStream", ss);// 二进制图片流
						oMap.put("index", index_pic_site + 1);

						oList.add(oMap);
						// 传递入参调接口
						pritureManagerService.upLoadPritureInfo(
								PritureShowActivity.this, 1, oList);

						imgList.add(path);
						map_savepictures.put("list_pictures_path", imgList);
						// showPriture(path);

					}

					@Override
					public void onCancelClick(AlertDialog dialog) {
						dialog.dismiss();
						// 返回选择获取图片资源方式
						Intent intent = new Intent(PritureShowActivity.this,
								ChiocePritureMethodActiivty_.class);
						startActivityForResult(intent, 1);
					}
				}).cancelable(false).show();
	}

	/*
	 * 获取本地图片按照手机屏幕大小进行显示
	 */
	public void showPriture(String path) {
		// 取得屏幕的显示大小

		Display currentDisplay = getWindowManager().getDefaultDisplay();
		DisplayMetrics displayMetrics = new DisplayMetrics();
		currentDisplay.getMetrics(displayMetrics);
		int dw = displayMetrics.widthPixels;
		int dh = displayMetrics.heightPixels;
		BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
		bmpFactoryOptions.inJustDecodeBounds = true;
		Bitmap bmp = BitmapFactory.decodeFile(path, bmpFactoryOptions);
		int heightRatio = (int) Math.ceil(bmpFactoryOptions.outHeight
				/ (float) dh);
		int widthRatio = (int) Math.ceil(bmpFactoryOptions.outWidth
				/ (float) dw);

		if (heightRatio > 1 && widthRatio > 1) {

			if (heightRatio > widthRatio) {
				// 表示将图片的容量变为原来容量的1/heightRatio,这么做的作用是为了避免因为图片太大占据内存太多造成
				// 内存溢出
				bmpFactoryOptions.inSampleSize = heightRatio;
			} else {
				bmpFactoryOptions.inSampleSize = widthRatio;
			}

		}

		bmpFactoryOptions.inJustDecodeBounds = false;
		bmp = BitmapFactory.decodeFile(path, bmpFactoryOptions);

		oBitmaps.add(bmp);

		if (ll_tupianliulanqi.getVisibility() == View.GONE) {
			ll_tupianliulanqi.setVisibility(View.VISIBLE);
		}
		if (tv_void_priture.getVisibility() == View.VISIBLE) {
			tv_void_priture.setVisibility(View.GONE);
		}
		 if (Constants.imgbool) { oBitmaps.clear(); }
		 
		 
		initImageView(oBitmaps);
		
	}

	String path_upload;

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
			// 将图片地址保存，用于展示图片
			path_upload = data
					.getStringExtra(ChiocePritureMethodActiivty_.KEY_PHOTO_PATH);
			if (path_upload != null) {

				// 上传前弹出对话框，用户确认上传该图片，避免用户选错图片上传
				showIsUploadDialog(path_upload);
			}
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			if (addnew) {
				Constants.list_tjcl_for_save_uploaded_pictures
						.add(map_savepictures);
			} else {
				if (Constants.list_tjcl_for_save_uploaded_pictures.size() != 0) {
					for (int i = 0; i < Constants.list_tjcl_for_save_uploaded_pictures
							.size(); i++) {
						if (Constants.list_tjcl_for_save_uploaded_pictures
								.get(i).get("postinfoname").equals(name)) {
							if (map_savepictures.get("list_pictures_id") != null) {
								Constants.list_tjcl_for_save_uploaded_pictures
										.set(i, map_savepictures);
							}
						}
					}
				}
			}
			finish();
		}
		return false;
	}

	/**
	 * 图片压缩至100KB以下，避免上传时造成内存溢出
	 * 
	 * @param image
	 * @return 二进制流
	 */
	private String compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
		}
		// ByteArrayInputStream isBm = new
		// ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
		// Bitmap bitmap = BitmapFactory.decodeStream(isBm, null,
		// null);//把ByteArrayInputStream数据生成图片

		String str = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
		return str;
	}

	/**
	 * 通过图片路径压缩图片
	 * 
	 * @param srcPath
	 * @return
	 */
	private String getimage(String srcPath) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;// 这里设置高度为800f
		float ww = 480f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
	}

}
