package com.WSZW.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

public class FileUtil {
	public static void openFile(Context context, File file) {

		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// 设置intent的Action属性
		intent.setAction(Intent.ACTION_VIEW);
		// 获取文件file的MIME类型
		String type = getMIMEType(file);
		// 设置intent的data和Type属性。
		intent.setDataAndType(/* uri */Uri.fromFile(file), type);
		// 跳转
		context.startActivity(intent);

	}

	/**
	 * 根据文件后缀名获得对应的MIME类型。
	 * 
	 * @param file
	 */
	private static String getMIMEType(File file) {

		String type = "*/*";
		String fName = file.getName();
		// 获取后缀名前的分隔符"."在fName中的位置。
		int dotIndex = fName.lastIndexOf(".");
		if (dotIndex < 0) {
			return type;
		}
		/* 获取文件的后缀名 */
		String end = fName.substring(dotIndex, fName.length()).toLowerCase();
		if (end == "")
			return type;
		// 在MIME和文件类型的匹配表中找到对应的MIME类型。
		for (int i = 0; i < MIME_MapTable.length; i++) { // MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
			if (end.equals(MIME_MapTable[i][0]))
				type = MIME_MapTable[i][1];
		}
		return type;
	}

	private final static String[][] MIME_MapTable = {
			// {后缀名， MIME类型}
			{ ".3gp", "video/3gpp" },
			{ ".apk", "application/vnd.android.package-archive" },
			{ ".asf", "video/x-ms-asf" },
			{ ".avi", "video/x-msvideo" },
			{ ".bin", "application/octet-stream" },
			{ ".bmp", "image/bmp" },
			{ ".c", "text/plain" },
			{ ".class", "application/octet-stream" },
			{ ".conf", "text/plain" },
			{ ".cpp", "text/plain" },
			{ ".doc", "application/msword" },
			{ ".docx",
					"application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
			{ ".xls", "application/vnd.ms-excel" },
			{ ".xlsx",
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
			{ ".exe", "application/octet-stream" },
			{ ".gif", "image/gif" },
			{ ".gtar", "application/x-gtar" },
			{ ".gz", "application/x-gzip" },
			{ ".h", "text/plain" },
			{ ".htm", "text/html" },
			{ ".html", "text/html" },
			{ ".jar", "application/java-archive" },
			{ ".java", "text/plain" },
			{ ".jpeg", "image/jpeg" },
			{ ".jpg", "image/jpeg" },
			{ ".js", "application/x-javascript" },
			{ ".log", "text/plain" },
			{ ".m3u", "audio/x-mpegurl" },
			{ ".m4a", "audio/mp4a-latm" },
			{ ".m4b", "audio/mp4a-latm" },
			{ ".m4p", "audio/mp4a-latm" },
			{ ".m4u", "video/vnd.mpegurl" },
			{ ".m4v", "video/x-m4v" },
			{ ".mov", "video/quicktime" },
			{ ".mp2", "audio/x-mpeg" },
			{ ".mp3", "audio/x-mpeg" },
			{ ".mp4", "video/mp4" },
			{ ".mpc", "application/vnd.mpohun.certificate" },
			{ ".mpe", "video/mpeg" },
			{ ".mpeg", "video/mpeg" },
			{ ".mpg", "video/mpeg" },
			{ ".mpg4", "video/mp4" },
			{ ".mpga", "audio/mpeg" },
			{ ".msg", "application/vnd.ms-outlook" },
			{ ".ogg", "audio/ogg" },
			{ ".pdf", "application/pdf" },
			{ ".png", "image/png" },
			{ ".pps", "application/vnd.ms-powerpoint" },
			{ ".ppt", "application/vnd.ms-powerpoint" },
			{ ".pptx",
					"application/vnd.openxmlformats-officedocument.presentationml.presentation" },
			{ ".prop", "text/plain" }, { ".rc", "text/plain" },
			{ ".rmvb", "audio/x-pn-realaudio" }, { ".rtf", "application/rtf" },
			{ ".sh", "text/plain" }, { ".tar", "application/x-tar" },
			{ ".tgz", "application/x-compressed" }, { ".txt", "text/plain" },
			{ ".wav", "audio/x-wav" }, { ".wma", "audio/x-ms-wma" },
			{ ".wmv", "audio/x-ms-wmv" },
			{ ".wps", "application/vnd.ms-works" }, { ".xml", "text/plain" },
			{ ".z", "application/x-compress" },
			{ ".zip", "application/x-zip-compressed" }, { "", "*/*" } };

	/**
	 * Get a file path from a Uri. This will get the the path for Storage Access
	 * Framework Documents, as well as the _data field for the MediaStore and
	 * other file-based ContentProviders.
	 * 
	 * @param context
	 *            The context.
	 * @param uri
	 *            The Uri to query.
	 * @author paulburke
	 */
	public static String getPath(final Context context, final Uri uri) {

		final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

		// DocumentProvider
		if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
			// ExternalStorageProvider
			if (isExternalStorageDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/"
							+ split[1];
				}

				// TODO handle non-primary volumes
			}
			// DownloadsProvider
			else if (isDownloadsDocument(uri)) {

				final String id = DocumentsContract.getDocumentId(uri);
				final Uri contentUri = ContentUris.withAppendedId(
						Uri.parse("content://downloads/public_downloads"),
						Long.valueOf(id));

				return getDataColumn(context, contentUri, null, null);
			}
			// MediaProvider
			else if (isMediaDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}

				final String selection = "_id=?";
				final String[] selectionArgs = new String[] { split[1] };

				return getDataColumn(context, contentUri, selection,
						selectionArgs);
			}
		}
		// MediaStore (and general)
		else if ("content".equalsIgnoreCase(uri.getScheme())) {
			return getDataColumn(context, uri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(uri.getScheme())) {
			return uri.getPath();
		}

		return null;
	}

	/**
	 * Get the value of the data column for this Uri. This is useful for
	 * MediaStore Uris, and other file-based ContentProviders.
	 * 
	 * @param context
	 *            The context.
	 * @param uri
	 *            The Uri to query.
	 * @param selection
	 *            (Optional) Filter used in the query.
	 * @param selectionArgs
	 *            (Optional) Selection arguments used in the query.
	 * @return The value of the _data column, which is typically a file path.
	 */
	public static String getDataColumn(Context context, Uri uri,
			String selection, String[] selectionArgs) {

		Cursor cursor = null;
		final String column = "_data";
		final String[] projection = { column };

		try {
			cursor = context.getContentResolver().query(uri, projection,
					selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				final int column_index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(column_index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri
				.getAuthority());
	}

	// 返回一个byte数组

	public static byte[] getBytesFromFile(File file) throws IOException {

		InputStream is = new FileInputStream(file);

		// 获取文件大小

		long length = file.length();

		if (length > Integer.MAX_VALUE) {

			// 文件太大，无法读取

			throw new IOException("File is to large " + file.getName());

		}

		// 创建一个数据来保存文件数据

		byte[] bytes = new byte[(int) length];

		// 读取数据到byte数组中

		int offset = 0;

		int numRead = 0;

		while (offset < bytes.length

		&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {

			offset += numRead;

		}

		// 确保所有数据均被读取

		if (offset < bytes.length) {

			throw new IOException("Could not completely read file "
					+ file.getName());

		}

		// Close the input stream and return bytes

		is.close();

		return bytes;

	}

	// 写文件
	public static void writeSDFile(File file, byte[] bytes)
			throws IOException {

		FileOutputStream fos = new FileOutputStream(file);

		fos.write(bytes);

		fos.close();
	}

	/**
	 * decoderBase64File:(将base64字符解码保存文件). <br/>
	 * 
	 * @author guhaizhou@126.com
	 * @param base64Code
	 *            编码后的字串
	 * @param savePath
	 *            文件保存路径
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static void decoderBase64File(String base64Code, File file)
			throws Exception {
		// byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
		byte[] buffer = Base64.decode(base64Code, Base64.DEFAULT);
		FileOutputStream out = new FileOutputStream(file);
		out.write(buffer);
		out.close();
	}
	
	public static void writeBase64StringToFile(String strBase64, String path) {
		File file2 = new File(path);
		if (!file2.exists()) {
			try {
				file2.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			decoderBase64File(strBase64, file2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void DownFile(String urlString,Handler handler,String fileName)
	    {
		 InputStream inputStream = null;
		     URLConnection connection = null;
		     OutputStream outputStream;
		     int FileLength;
		     int DownedFileLength=0;   
	        /*
	         * 连接到服务器
	         */
	         
	        try {
	             URL url=new URL(urlString);
	             connection=url.openConnection();
	             if (connection.getReadTimeout()==5) {
	                Log.i("---------->", "当前网络有问题");
	                // return;
	               }
	             inputStream=connection.getInputStream();
	             
	        } catch (MalformedURLException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	         
	        /*
	         * 文件的保存路径和和文件名其中Nobody.mp3是在手机SD卡上要保存的路径，如果不存在则新建
	         */
	        String savePAth=Environment.getExternalStorageDirectory()+"/DownFile";
	        File file1=new File(savePAth);
	        if (!file1.exists()) {
	            file1.mkdir();
	        }
	        String savePathString=Environment.getExternalStorageDirectory()+"/DownFile/"+fileName;
	        File file =new File(savePathString);
	        if (!file.exists()) {
	            try {
	                file.createNewFile();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }  
	        }
	        /*
	         * 向SD卡中写入文件,用Handle传递线程
	         */
	        Message message=new Message();
	        try {
	            outputStream=new FileOutputStream(file);
	            byte [] buffer=new byte[1024*4];
	            FileLength=connection.getContentLength();
	            message.what=0;
	            handler.sendMessage(message);
	            while (DownedFileLength<FileLength) {
	                outputStream.write(buffer);
	                DownedFileLength+=inputStream.read(buffer);
	                Log.i("-------->", DownedFileLength+"");
	                Message message1=new Message();
	                message1.what=1;
	                handler.sendMessage(message1);
	            }
	            Message message2=new Message();
	            message2.what=2;
	            handler.sendMessage(message2);
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	 
	

/**
         * 获取文件后缀名
         * 
         * @param fileName
         * @return 文件后缀名
         */
        public static String getFileType(String fileName) {
                if (fileName != null) {
                        int typeIndex = fileName.lastIndexOf(".");
                        if (typeIndex != -1) {
                                String fileType = fileName.substring(typeIndex + 1)
                                                .toLowerCase();
                                return fileType;
                        }
                }
                return "";
        }

        /**
         * 根据后缀名判断是否是图片文件
         * 
         * @param type
         * @return 是否是图片结果true or false
         */
        public static boolean isImage(String type) {
                if (type != null
                                && (type.equals("jpg") || type.equals("gif")
                                                || type.equals("png") || type.equals("jpeg")
                                                || type.equals("bmp") || type.equals("wbmp")
                                                || type.equals("ico") || type.equals("jpe"))) {
                        return true;
                }
                return false;
        }
        
        /**
    	 * 通过图片路径压缩图片
    	 * 
    	 * @param srcPath
    	 * @return
    	 */
    	public static String getimage(String srcPath) {
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
    	
    	/**
    	 * 图片压缩至100KB以下，避免上传时造成内存溢出
    	 * 
    	 * @param image
    	 * @return 二进制流
    	 */
    	public static String compressImage(Bitmap image) {

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
}
