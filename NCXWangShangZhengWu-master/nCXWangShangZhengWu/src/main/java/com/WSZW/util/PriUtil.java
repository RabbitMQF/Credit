package com.WSZW.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 通过图片路径获取图片
 * 
 * @author Administrator
 * 
 */
public class PriUtil {

	public static Bitmap getBitmap(String imgUrl) {
		Bitmap bitmap = null;
		URL imageUrl;
		try {
			imageUrl = new URL(imgUrl);
			HttpURLConnection conn = (HttpURLConnection) imageUrl
					.openConnection();
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(60000);
			conn.setInstanceFollowRedirects(true);
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			conn.disconnect();
						
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bitmap;
	}
	
	public static Bitmap getBitmapforZxsb(String imgUrl) {
		Bitmap bitmap = null;
		URL imageUrl;
		InputStream is;
		try {
			imageUrl = new URL(imgUrl);

			HttpURLConnection conn = (HttpURLConnection) imageUrl
					.openConnection();
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(60000);
			conn.setInstanceFollowRedirects(true);

			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				is = conn.getInputStream();

			} else {
				is = null;
			}

			if (is == null) {
				throw new RuntimeException("stream is null");
			} else {
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = false;
				options.inSampleSize = 10; // width，hight设为原来的十分一
				bitmap = BitmapFactory.decodeStream(is, null, options);
				return bitmap;
				/*
				 * try { byte[] data =readStream(is);
				 * if(data!=null){
				 * bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
				 * return bitmap;
				 * 
				 * 
				 * } } catch (Exception e) { e.printStackTrace(); }
				 */

			}
			/*
			 * bitmap = BitmapFactory.decodeStream(is); conn.disconnect();
			 */

			/* return bitmap; */
			/* is.close(); */

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!bitmap.isRecycled()) {
			bitmap.recycle(); // 回收图片所占的内存
			System.gc(); // 提醒系统及时回收
		}
		return null;
	}

	
}
