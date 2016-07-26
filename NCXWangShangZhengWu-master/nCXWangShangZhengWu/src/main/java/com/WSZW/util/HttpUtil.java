package com.WSZW.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

/**
 * http请求工具类
 * 
 * @author yuhuihui
 * @data 2014-5-30
 */
public class HttpUtil {
	/**
	 * 普通下载
	 * 
	 * @param url
	 *            下载地址
	 * @param filePath
	 *            保存路径
	 * @return File对象，如果长度为0则返回null
	 * @author yuhuihui
	 * @date 2014-6-3
	 */
	public static File downLoad(String url, String filePath) {
		if (StringUtil.isTrimBlank(url)) {
			return null;
		}
		try {
			URL connUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) connUrl
					.openConnection();
			conn.setConnectTimeout(30 * 1000);
			conn.setReadTimeout(30 * 1000);
			conn.setDoInput(true);
			conn.connect();

			File file = new File(filePath);
			file.createNewFile();
			InputStream is = conn.getInputStream();
			FileOutputStream fos = new FileOutputStream(file);
			byte[] temp = new byte[1024];
			int i = 0;
			while ((i = is.read(temp)) > 0) {
				fos.write(temp, 0, i);
			}
			fos.close();
			is.close();
			if (file.length() > 0) {
				return file;
			}
		} catch (IOException e) {
			Log.e("HttpUtil (down) --> ", e.toString());
		}
		return null;
	}

	/**
	 * 获取xml 类型数据
	 * 
	 * @param activity上下文
	 * @param methodName
	 *            方法
	 * @param paramList
	 *            参数
	 * @return result.toString
	 * @throws SoapFault
	 */
	public static String getXmlDataByMap(Context context, String methodName,
			String endPoint, Map<String, Object> map) throws SoapFault {
		SoapPrimitive result = null;
		SoapObject result_1 = null;
		String nameSpace = "http://services.publicservices.gov/";
		String soapAction = nameSpace + methodName;

		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);

		// 设置需调用WebService接口需要传入的参数
		if (map != null) {
			if (methodName.equals("ffxx")) {
				rpc.addProperty("catalogId", map.get("catalogId"));
				rpc.addProperty("adminOrgId", map.get("adminOrgId"));
				rpc.addProperty("xzcfAdminOrgId", map.get("xzcfAdminOrgId"));
				rpc.addProperty("xzzqName",map.get("xzzqCode"));
				rpc.addProperty("serviceSubjectName",
						map.get("serviceSubjectName"));
				rpc.addProperty("pageSize", map.get("pageSize"));
				rpc.addProperty("pageCount", map.get("pageCount"));
			}
		}

		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		// envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);

		HttpTransportSE transport = new HttpTransportSE(endPoint);
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			// result = (SoapPrimitive) envelope.getResponse();

			String str1 = envelope.getResponse().getClass().toString();
			if (str1.equals("class org.ksoap2.serialization.SoapPrimitive")) {
				result = (SoapPrimitive) envelope.getResponse();
				if (result != null) {
					return result.toString();
				}
			} else if (str1.equals("class org.ksoap2.serialization.SoapObject")) {
				result_1 = (SoapObject) envelope.getResponse();
				if (result_1 != null) {
					return result_1.toString();
				}
			}

		} else {
			result = null;
		}
		return "error";
	}

	/**
	 * 获取xml 类型数据
	 * 
	 * @param activity上下文
	 * @param methodName
	 *            方法
	 * @param paramList
	 *            参数
	 * @return result.toString
	 * @throws SoapFault
	 */
	public static String getXmlData(Context context, String methodName,
			String endPoint, String str) throws SoapFault {
		SoapPrimitive result = null;
		SoapObject result_1 = null;
		String nameSpace = "http://services.publicservices.gov/";
		String soapAction = nameSpace + methodName;

		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);

		// 设置需调用WebService接口需要传入的参数
		if (str != null) {
			if (methodName.equals("grbs")) {
				rpc.addProperty("code", str);
			}
			if(methodName.equals("ffxxInfo") || methodName.equals("xzcfFfxxInfo")){
				rpc.addProperty("serviceSubjectId", str);
			}
		}

		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		// envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);

		HttpTransportSE transport = new HttpTransportSE(endPoint);
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			// result = (SoapPrimitive) envelope.getResponse();

			String str1 = envelope.getResponse().getClass().toString();
			if (str1.equals("class org.ksoap2.serialization.SoapPrimitive")) {
				result = (SoapPrimitive) envelope.getResponse();
				if (result != null) {
					return result.toString();
				}
			} else if (str1.equals("class org.ksoap2.serialization.SoapObject")) {
				result_1 = (SoapObject) envelope.getResponse();
				if (result_1 != null) {
					return result_1.toString();
				}
			} else if(str1.equals("class org.ksoap2.serialization.SoapObject")){
				
			}

		} else {
			result = null;
		}
		return "error";
	}

	/**
	 * 获取xml 类型数据
	 * 
	 * @param activity上下文
	 * @param methodName
	 *            方法
	 * @param paramList
	 *            参数
	 * @return result.toString
	 * @throws SoapFault
	 */
	public static String getXmlData(Context context, String methodName,
			String endPoint, String str1,String str2) throws SoapFault {
		SoapPrimitive result = null;
		SoapObject result_1 = null;
		String nameSpace = "http://services.publicservices.gov/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (str1 != null&&str2!=null) {
			if (methodName.equals("doStartEvaluate1")) {
				rpc.addProperty("serialNumber",str1);
				rpc.addProperty("identityNumber", str2);
			}
			if (methodName.equals("doResultInquire")) {
				rpc.addProperty("serialNumber", str1);
				rpc.addProperty("userIdentityNumber", str2);
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		// envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			// result = (SoapPrimitive) envelope.getResponse();
			
			String str_1= envelope.getResponse().getClass().toString();
			if (str_1.equals("class org.ksoap2.serialization.SoapPrimitive")) {
				result = (SoapPrimitive) envelope.getResponse();
				if (result != null) {
					return result.toString();
				}
			} else if (str_1.equals("class org.ksoap2.serialization.SoapObject")) {
				result_1 = (SoapObject) envelope.getResponse();
				if (result_1 != null) {
					return result_1.toString();
				}
			}
			
		} else {
			result = null;
		}
		return "error";
	}
	
	public static String getXmlData2(Context context, String methodName,
			String endPoint, String str1,String str2,String str3,String str4) throws SoapFault {
		SoapPrimitive result = null;
		SoapObject result_1 = null;
		String nameSpace = "http://services.publicservices.gov/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (str1 != null&&str2!=null&&str3!=null&&str4!=null) {
			if (methodName.equals("doStartEvaluate2")) {
				rpc.addProperty("starLevel", str1);
				rpc.addProperty("evaluateContent", str2);
				rpc.addProperty("serialNumber", str3);
				rpc.addProperty("identityNumber", str4);
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		// envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			// result = (SoapPrimitive) envelope.getResponse();
			
			String str_1= envelope.getResponse().getClass().toString();
			if (str_1.equals("class org.ksoap2.serialization.SoapPrimitive")) {
				result = (SoapPrimitive) envelope.getResponse();
				if (result != null) {
					return result.toString();
				}
			} else if (str_1.equals("class org.ksoap2.serialization.SoapObject")) {
				result_1 = (SoapObject) envelope.getResponse();
				if (result_1 != null) {
					return result_1.toString();
				}
			}
			
		} else {
			result = null;
		}
		return "error";
	}
	
	static String foldername = Environment.getExternalStorageDirectory()
			.getPath() + "/log_2222.txt";
	static String foldername1 = Environment.getExternalStorageDirectory()
			.getPath() + "/log_ffdd.txt";
	
	public static void writeSDCardFile(String path, byte[] buffer)
			throws IOException {
		
		File file = new File(path);
		
		FileOutputStream fos = new FileOutputStream(file);
		
		fos.write(buffer);// 写入buffer数组。如果想写入一些简单的字符，可以将String.getBytes()再写入文件;
		
		fos.close();
		
	}
	/**
	 * 获取文档列表接口
	 */
	public static String getXML(Context context,String chanIds,String pageSize,String pageIndex,String statusIds) throws MalformedURLException, IOException{
		StringBuilder xml=new StringBuilder(); 
		xml.append("<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:loc='LocationService'>");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<loc:getDocuments>");
        xml.append("<chanIds>"+chanIds+"</chanIds>");
        xml.append("<pageSize>"+pageSize+"</pageSize>");
        xml.append("<pageIndex>"+pageIndex+"</pageIndex>");
        xml.append("<title></title>");
        xml.append("<cruser></cruser>");
        xml.append("<isImage></isImage>");
        xml.append("<statusIds>"+statusIds+"</statusIds>");
        xml.append("<where></where>");
        xml.append("<order>chandoc.topNum desc</order>");//writeTime desc
        xml.append("<isAllChild>false</isAllChild>");
        xml.append("</loc:getDocuments>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");
		
		 byte[] xmlbyte = xml.toString().getBytes("UTF-8");
		
		String path="http://117.40.244.143:8089/ecm/ws/DocumentService?wsdl";
		HttpURLConnection conn=(HttpURLConnection) new URL(path).openConnection();
		 conn.setConnectTimeout(5000);
         conn.setDoOutput(true);// 允许输出
         conn.setDoInput(true);
         conn.setUseCaches(false);// 不使用缓存
         conn.setRequestMethod("POST");
         conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
         conn.setRequestProperty("Charset", "UTF-8");
         conn.setRequestProperty("Content-Length",
         String.valueOf(xmlbyte.length));
         conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
         conn.setRequestProperty("X-ClientType", "2");//发送自定义的头信息
         conn.getOutputStream().write(xmlbyte);
         conn.getOutputStream().flush();
         conn.getOutputStream().close();

         if (conn.getResponseCode() != 200){
             throw new RuntimeException("请求url失败");
         }
         InputStream is = conn.getInputStream();// 获取返回数据
           

           // 使用输出流来输出字符(可选)
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         byte[] buf = new byte[1024];
         int len;
         while ((len = is.read(buf)) != -1) {
             out.write(buf, 0, len);
         }
         String string = out.toString("UTF-8");
         out.close();
		return string;
	}
	
	/**
	 * 获取文档列表接口
	 */
	public static String getXMLForFlash(Context context,String chanIds,String pageSize,String pageIndex,String statusIds) throws MalformedURLException, IOException{
		StringBuilder xml=new StringBuilder(); 
		xml.append("<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:loc='LocationService'>");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<loc:getDocuments>");
        xml.append("<chanIds>"+chanIds+"</chanIds>");
        xml.append("<pageSize>"+pageSize+"</pageSize>");
        xml.append("<pageIndex>"+pageIndex+"</pageIndex>");
        xml.append("<title></title>");
        xml.append("<cruser></cruser>");
        xml.append("<isImage>true</isImage>");
        xml.append("<statusIds>"+statusIds+"</statusIds>");
        xml.append("<where></where>");
        xml.append("<order>writeTime desc</order>");
        xml.append("<isAllChild>false</isAllChild>");
        xml.append("</loc:getDocuments>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");
		
		 byte[] xmlbyte = xml.toString().getBytes("UTF-8");
		
		String path="http://117.40.244.143:8089/ecm/ws/DocumentService?wsdl";
		HttpURLConnection conn=(HttpURLConnection) new URL(path).openConnection();
		 conn.setConnectTimeout(5000);
         conn.setDoOutput(true);// 允许输出
         conn.setDoInput(true);
         conn.setUseCaches(false);// 不使用缓存
         conn.setRequestMethod("POST");
         conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
         conn.setRequestProperty("Charset", "UTF-8");
         conn.setRequestProperty("Content-Length",
         String.valueOf(xmlbyte.length));
         conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
         conn.setRequestProperty("X-ClientType", "2");//发送自定义的头信息
         conn.getOutputStream().write(xmlbyte);
         conn.getOutputStream().flush();
         conn.getOutputStream().close();

         if (conn.getResponseCode() != 200){
             throw new RuntimeException("请求url失败");
         }
         InputStream is = conn.getInputStream();// 获取返回数据
           

           // 使用输出流来输出字符(可选)
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         byte[] buf = new byte[1024];
         int len;
         while ((len = is.read(buf)) != -1) {
             out.write(buf, 0, len);
         }
         String string = out.toString("UTF-8");
         out.close();
		return string;
	}
	
	/**
	 * 获取单篇文章接口
	 */
	public static String getDocumentXML(Context context,String chanDocId) throws MalformedURLException, IOException{
		StringBuilder xml=new StringBuilder(); 
		xml.append("<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:loc='LocationService'>");
		xml.append("<soapenv:Header/>");
	    xml.append("<soapenv:Body>");
		xml.append("<loc:getDocument>");
		xml.append("<chanDocId>"+chanDocId+"</chanDocId>");
		xml.append("</loc:getDocument>");
		xml.append("</soapenv:Body>");
	    xml.append("</soapenv:Envelope>");
		
		byte[] xmlbyte = xml.toString().getBytes("UTF-8");
		
		String path="http://117.40.244.143:8089/ecm/ws/DocumentService?wsdl";
		HttpURLConnection conn=(HttpURLConnection) new URL(path).openConnection();
		conn.setConnectTimeout(5000);
		conn.setDoOutput(true);// 允许输出
		conn.setDoInput(true);
		conn.setUseCaches(false);// 不使用缓存
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("Content-Length",
		String.valueOf(xmlbyte.length));
		conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
		conn.setRequestProperty("X-ClientType", "2");//发送自定义的头信息
		conn.getOutputStream().write(xmlbyte);
		conn.getOutputStream().flush();
		conn.getOutputStream().close();
		
		if (conn.getResponseCode() != 200){
			throw new RuntimeException("请求url失败");
		}
		InputStream is = conn.getInputStream();// 获取返回数据
		
		
		// 使用输出流来输出字符(可选)
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len;
		while ((len = is.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		String string = out.toString("UTF-8");
		out.close();
		return string;
	}
	
	/**
	 *  获取子栏目列表接口
	 */
	public static String getChannelsByParentIdXML(Context context, String methodName,
			String endPoint, Long arg0,Long arg1,int arg2) throws SoapFault {
		SoapPrimitive result = null;
		SoapObject result_1 = null;
//		String nameSpace = "http://services.publicservices.gov/";
		String nameSpace = "http://ws.channel.cms.intertid.com/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (arg0 != null&&arg1!=null) {
			if (methodName.equals("getChannelsByParentId")) {
				rpc.addProperty("arg0", arg0);
				rpc.addProperty("arg1", arg1);
				rpc.addProperty("arg2", arg2);
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		// envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			// result = (SoapPrimitive) envelope.getResponse();
			
			String str_1= envelope.getResponse().getClass().toString();
			if (str_1.equals("class org.ksoap2.serialization.SoapPrimitive")) {
				result = (SoapPrimitive) envelope.getResponse();
				if (result != null) {
					return result.toString();
				}
			} else if (str_1.equals("class org.ksoap2.serialization.SoapObject")) {
				result_1 = (SoapObject) envelope.getResponse();
				if (result_1 != null) {
					return result_1.toString();
				}
			}
			
		} else {
			result = null;
		}
		return "error";
	}
	/**
	 *  	获取信息公开目录一级分类对象集合接口
	 */
			public static String getGovInfoTypesByParent(Context context,String arg0) throws MalformedURLException, IOException{
		StringBuilder xml=new StringBuilder(); 
		xml.append("<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ws='http://ws.govinfo.intertid.com/'>");
		xml.append("<soapenv:Header/>");
	    xml.append("<soapenv:Body>");
		xml.append("<ws:getGovInfoTypesByParent>");
		xml.append("<arg0>"+arg0+"</arg0>");
		xml.append("<arg1></arg1>");
//		xml.append("<arg2>""</arg2>");
		xml.append("</ws:getGovInfoTypesByParent>");
		xml.append("</soapenv:Body>");
	    xml.append("</soapenv:Envelope>");
		
		byte[] xmlbyte = xml.toString().getBytes("UTF-8");
		
		String path="http://117.40.244.143:8089/ecm/ws/GovInfoService?wsdl";
		HttpURLConnection conn=(HttpURLConnection) new URL(path).openConnection();
		conn.setConnectTimeout(5000);
		conn.setDoOutput(true);// 允许输出
		conn.setDoInput(true);
		conn.setUseCaches(false);// 不使用缓存
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("Content-Length",
		String.valueOf(xmlbyte.length));
		conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
		conn.setRequestProperty("X-ClientType", "2");//发送自定义的头信息
		conn.getOutputStream().write(xmlbyte);
		conn.getOutputStream().flush();
		conn.getOutputStream().close();
		
		
		if (conn.getResponseCode() != 200){
			throw new RuntimeException("请求url失败"+conn.getResponseCode());
		}
		InputStream is = conn.getInputStream();// 获取返回数据
		
		
		// 使用输出流来输出字符(可选)
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len;
		while ((len = is.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		String string = out.toString("UTF-8");
		out.close();
		return string;
	}
			/**
			 *  	获取信息公开目录二级分类对象集合接口
			 */
			public static String getGovInfoTypesByParentTwo(Context context,String arg0,String arg2) throws MalformedURLException, IOException{
				StringBuilder xml=new StringBuilder(); 
				xml.append("<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ws='http://ws.govinfo.intertid.com/'>");
				xml.append("<soapenv:Header/>");
				xml.append("<soapenv:Body>");
				xml.append("<ws:getGovInfoTypesByParent>");
				xml.append("<arg0>"+arg0+"</arg0>");
				xml.append("<arg1>0</arg1>");
				xml.append("<arg2>"+arg2+"</arg2>");
				xml.append("</ws:getGovInfoTypesByParent>");
				xml.append("</soapenv:Body>");
				xml.append("</soapenv:Envelope>");
				
				byte[] xmlbyte = xml.toString().getBytes("UTF-8");
				
				String path="http://117.40.244.143:8089/ecm/ws/GovInfoService?wsdl";
				HttpURLConnection conn=(HttpURLConnection) new URL(path).openConnection();
				conn.setConnectTimeout(5000);
				conn.setDoOutput(true);// 允许输出
				conn.setDoInput(true);
				conn.setUseCaches(false);// 不使用缓存
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
				conn.setRequestProperty("Charset", "UTF-8");
				conn.setRequestProperty("Content-Length",
						String.valueOf(xmlbyte.length));
				conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
				conn.setRequestProperty("X-ClientType", "2");//发送自定义的头信息
				conn.getOutputStream().write(xmlbyte);
				conn.getOutputStream().flush();
				conn.getOutputStream().close();
				
				
				if (conn.getResponseCode() != 200){
					throw new RuntimeException("请求url失败"+conn.getResponseCode());
				}
				InputStream is = conn.getInputStream();// 获取返回数据
				
				
				// 使用输出流来输出字符(可选)
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				int len;
				while ((len = is.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				String string = out.toString("UTF-8");
				out.close();
				return string;
			}
			/**
			 *  	3.6.	获取信息公开文档列表数据接口
			 */
			public static String getGovInfoDocuments(Context context,String organId,String Id,String pageNum,String pageSize) throws MalformedURLException, IOException{
				StringBuilder xml=new StringBuilder(); 
				
				xml.append("<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ws='http://ws.govinfo.intertid.com/'>"+
				   "<soapenv:Header/>"+
				   "<soapenv:Body>"+
				      "<ws:getGovInfoDocuments>"+
				         "<arg0>"+organId+"</arg0>"+
				         "<arg1>"+Id+"</arg1>"+
				         "<arg2>"+
				            "<order></order>"+
				            "<paramValues>"+
				               "<entry>"+
				                  "<key></key>"+
				                  "<value></value>"+
				               "</entry>"+
				            "</paramValues>"+
				            "<where></where>"+
				         "</arg2>"+
				         "<arg3>"+pageNum+"</arg3>"+
				         "<arg4>"+pageSize+"</arg4>"+
				      "</ws:getGovInfoDocuments>"+
				   "</soapenv:Body>"+
				"</soapenv:Envelope>");

				byte[] xmlbyte = xml.toString().getBytes("UTF-8");
				
				String path="http://117.40.244.143:8089/ecm/ws/GovInfoService?wsdl";
				HttpURLConnection conn=(HttpURLConnection) new URL(path).openConnection();
				conn.setConnectTimeout(5000);
				conn.setDoOutput(true);// 允许输出
				conn.setDoInput(true);
				conn.setUseCaches(false);// 不使用缓存
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
				conn.setRequestProperty("Charset", "UTF-8");
				conn.setRequestProperty("Content-Length",
						String.valueOf(xmlbyte.length));
				conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
				conn.setRequestProperty("X-ClientType", "2");//发送自定义的头信息
				conn.getOutputStream().write(xmlbyte);
				conn.getOutputStream().flush();
				conn.getOutputStream().close();
				
				
				if (conn.getResponseCode() != 200){
					throw new RuntimeException("请求url失败"+conn.getResponseCode());
				}
				InputStream is = conn.getInputStream();// 获取返回数据
				
				
				// 使用输出流来输出字符(可选)
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				int len;
				while ((len = is.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				String string = out.toString("UTF-8");
				out.close();
				return string;
			}
	
	/**
	 * 信件列表
	 */
	
	public static String getqueryLetter(Context context,String channelId, String lettertypeId,String arg2, String arg3) throws MalformedURLException, IOException{
		
		StringBuilder xml=new StringBuilder(); 
		xml.append("<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ws='http://ws.mailbox.intertid.com/'>");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<ws:queryLetter>");
        xml.append("<arg0>");
        xml.append("<channelId>"+channelId+"</channelId>");
        xml.append("<lettertypeId>"+lettertypeId+"</lettertypeId>");
        xml.append("<mailType>0</mailType>");
        xml.append("</arg0>");
        xml.append("<arg2>"+arg2+"</arg2>");
        xml.append("<arg3>"+arg3+"</arg3>");
        xml.append("</ws:queryLetter>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");
		
        byte[] xmlbyte = xml.toString().getBytes("UTF-8");
		
		String path="http://117.40.244.143:8089/ecm/ws/MailboxService?wsdl";
		HttpURLConnection conn=(HttpURLConnection) new URL(path).openConnection();
		 conn.setConnectTimeout(5000);
         conn.setDoOutput(true);// 允许输出
         conn.setDoInput(true);
         conn.setUseCaches(false);// 不使用缓存
         conn.setRequestMethod("POST");
         conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
         conn.setRequestProperty("Charset", "UTF-8");
         conn.setRequestProperty("Content-Length",
         String.valueOf(xmlbyte.length));
         conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
         conn.setRequestProperty("X-ClientType", "2");//发送自定义的头信息
         conn.getOutputStream().write(xmlbyte);
         conn.getOutputStream().flush();
         conn.getOutputStream().close();

         if (conn.getResponseCode() != 200){
             throw new RuntimeException("请求url失败");
         }
         InputStream is = conn.getInputStream();// 获取返回数据
           

           // 使用输出流来输出字符(可选)
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         byte[] buf = new byte[1024];
         int len;
         while ((len = is.read(buf)) != -1) {
             out.write(buf, 0, len);
         }
         String string = out.toString("UTF-8");
         out.close();
		return string;
		
	}
	
	
	/**
	 * 县长信箱
	 */
	public static String saveLetter(Context context,String content,String email,String lettertypeId,String lettertypeName,
			String metaJson,String phone,String title,String writerName) throws MalformedURLException, IOException{
		
		StringBuilder xml=new StringBuilder(); 
		
		xml.append("<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ws='http://ws.mailbox.intertid.com/'>");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<ws:saveLetter>");
        xml.append("<arg0>");
        xml.append("<branchId>3</branchId>");
        xml.append("<branchName>县政府办</branchName>");
        xml.append("<channelId>1219</channelId>");
        xml.append("<common>true</common>");
        xml.append("<content>"+content+"</content>");
        xml.append("<email>"+email+"</email>");
        xml.append("<lettercategoryId>1</lettercategoryId>");
        xml.append("<lettercategoryName>城市环保</lettercategoryName>");
        xml.append("<lettertypeId>"+lettertypeId+"</lettertypeId>");
        xml.append("<lettertypeName>"+lettertypeName+"</lettertypeName>");
        xml.append("<mailboxId>2</mailboxId>");
        xml.append("<mailboxName>县长信箱</mailboxName>");
        xml.append("<metaJson>"+metaJson+"</metaJson>");
        xml.append("<openAble>false</openAble>");
        xml.append("<phone>"+phone+"</phone>");
        xml.append("<title>"+title+"</title>");
        xml.append("<writerName>"+writerName+"</writerName>");
        xml.append("</arg0>");
        xml.append(" </ws:saveLetter>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        byte[] xmlbyte = xml.toString().getBytes("UTF-8");
		
		String path="http://117.40.244.143:8089/ecm/ws/MailboxService?wsdl";
		HttpURLConnection conn=(HttpURLConnection) new URL(path).openConnection();
		 conn.setConnectTimeout(5000);
         conn.setDoOutput(true);// 允许输出
         conn.setDoInput(true);
         conn.setUseCaches(false);// 不使用缓存
         conn.setRequestMethod("POST");
         conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
         conn.setRequestProperty("Charset", "UTF-8");
         conn.setRequestProperty("Content-Length",
         String.valueOf(xmlbyte.length));
         conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
         conn.setRequestProperty("X-ClientType", "2");//发送自定义的头信息
         conn.getOutputStream().write(xmlbyte);
         conn.getOutputStream().flush();
         conn.getOutputStream().close();

         if (conn.getResponseCode() != 200){
             throw new RuntimeException("请求url失败");
         }
         InputStream is = conn.getInputStream();// 获取返回数据
           

           // 使用输出流来输出字符(可选)
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         byte[] buf = new byte[1024];
         int len;
         while ((len = is.read(buf)) != -1) {
             out.write(buf, 0, len);
         }
         String string = out.toString("UTF-8");
         out.close();
		return string;
	}
	/**
	 * 
	 * 在线申办后添加方法待修改
	 * 获取xml 类型数据
	 * 
	 * @param activity上下文
	 * @param methodName
	 *            方法
	 * @param paramList
	 *            参数
	 * @return result.toString
	 * @throws SoapFault
	 */
	public static String getXmlData(Context context, String methodName,
			String endPoint, List<Map<String, Object>> list) throws SoapFault {
		SoapPrimitive result = null;
		SoapObject result_1 = null;
		SoapPrimitive result_ysl = null;
		String nameSpace;
		String soapAction;
		boolean bool=false;
		if (methodName.equals("getAPPLogin")) {
			// 命名空间
			nameSpace = "http://services.publicservices.gov/";/*
															 * context.getResources
															 * (
															 * ).getString(com.ZWPT
															 * .
															 * activity.R.string
															 * .namespace);
															 */
			soapAction = nameSpace + "/" + methodName;
		} else {
			nameSpace = "http://services.publicservices.gov/";
			soapAction = nameSpace + methodName;
		}

		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);

		// 设置需调用WebService接口需要传入的参数
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (methodName.equals("getAcceptInfo")) {
					rpc.addProperty("serviceSubjectId",
							list.get(i).get("serviceSubjectId"));
				}
				if (methodName.equals("deleteCertificateImage")) {
					rpc.addProperty("imageId", list.get(i).get("imageId"));
				}
				if (methodName.equals("getAcceptInfo2")) {
					rpc.addProperty("serviceSubjectId",
							list.get(i).get("serviceSubjectId"));
					rpc.addProperty("resultCode", list.get(i).get("resultCode"));
				} else if (methodName.equals("getAcceptBsck")) {
					rpc.addProperty("serviceId",
							list.get(i).get("serviceSubjectId"));
				} else if (methodName.equals("onlineAcceptImage")) {
					rpc.addProperty("name", list.get(i).get("name"));
					rpc.addProperty("number", list.get(i).get("index")
							.toString());
					rpc.addProperty("imageStream",
							list.get(i).get("imageStream"));
					/*
					 * byte[] buffer =
					 * list.get(i).get("imageStream").toString().getBytes(); try
					 * { writeSDCardFile(foldername,buffer); } catch
					 * (IOException e) { // TODO 自动生成的 catch 块
					 * e.printStackTrace(); }
					 */
				} else if (methodName.equals("getServiceSubjectForm")) {
					rpc.addProperty("serviceSubjectName",
							list.get(i).get("serviceSubjectName"));
					if(list.get(i).get("serviceSubjectName").equals("《一孩生育服务证》办理")){
						bool=true;
					}
				} else if (methodName.equals("onlineAcceptSubmit")) {
					rpc.addProperty("serviceSubjectId",
							list.get(i).get("serviceSubjectId"));
					rpc.addProperty("userName", list.get(i).get("userName"));
					rpc.addProperty("identityNumber",
							list.get(i).get("identityNumber"));
					rpc.addProperty("serviceTargetPhone",
							list.get(i).get("serviceTargetPhone"));
					rpc.addProperty("serviceTargetEmail",
							list.get(i).get("serviceTargetEmail"));
					rpc.addProperty("organizationId",
							list.get(i).get("organizationId"));
					rpc.addProperty("resultCode", list.get(i).get("resultCode"));
					rpc.addProperty("fillForm", list.get(i).get("fillForm"));
					rpc.addProperty("isSendMsg", list.get(i).get("isSendMsg"));
					rpc.addProperty("bizArchivesAttachmentIds", list.get(i)
							.get("bizArchivesAttachmentIds"));
					// rpc.addProperty("bizArchivesAttachmentIds","");
				}
				// 匹配当前方法名
				if (methodName.equals("")) {
					// 添加入参版本号信息
				}
			}
		}

		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		// envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		HttpTransportSE transport;
		if (methodName.equals("getServiceSubjectForm")) {
			int timeout = 60000;
			transport = new HttpTransportSE(endPoint, timeout);
		} else {
			transport = new HttpTransportSE(endPoint);
		}
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("", endPoint.toString());
		}
		if (envelope.bodyIn != null) {
			// result = (SoapPrimitive) envelope.getResponse();
			if (methodName.equals("getBizArchivesOnlineForm")) {
				result_ysl = (SoapPrimitive) envelope.getResponse();
				return result_ysl.toString();
			} else {
				String str = envelope.getResponse().getClass().toString();
				if (str.equals("class org.ksoap2.serialization.SoapPrimitive")) {
					result = (SoapPrimitive) envelope.getResponse();
					if (result != null) {
						return result.toString();
					}
				} else if (str
						.equals("class org.ksoap2.serialization.SoapObject")) {
					result_1 = (SoapObject) envelope.getResponse();
					if (result_1 != null) {
						return result_1.toString();
					}
				}

			}

		} else {
			result = null;
		}
		/*if (methodName.equals("getServiceSubjectForm")&&bool) {
			return "<?xml version=\"1.0\" encoding=\"utf-8\"?><case><ZsoftInfo><ServiceSubjectColumn columnName='BH' name='编号' value='' dataType='STRING' length='50' nonEmpty='true' controlType='TEXTBOX' index='1' groupName='文件信息' groupIndex='1'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='SQRXM' name='申请人姓名' value='' dataType='STRING' length='50' nonEmpty='false' controlType='TEXTBOX' index='2' groupName='申请人信息' groupIndex='2'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='SQRSFZHM' name='申请人身份证号码' value='' dataType='STRING' length='50' nonEmpty='false' controlType='TEXTBOX' index='3' groupName='申请人信息' groupIndex='2'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='SQRCSNY' name='申请人出生年月' value='' dataType='DATETIME' length='12' nonEmpty='true' controlType='DATEFIELD' index='4' groupName='申请人信息' groupIndex='2'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='SQRHYZK' name='申请人婚姻状况' value='' dataType='STRING' length='20' nonEmpty='true' controlType='DROPDOWN_LIST' index='5' groupName='申请人信息' groupIndex='2'  isShowGroupTitle='true' inputControlValueScope='初婚,再婚'></ServiceSubjectColumn><ServiceSubjectColumn columnName='SQRHKSZDXZ' name='申请人户口所在地详址' value='' dataType='STRING' length='300' nonEmpty='true' controlType='ADDRESS' index='6' groupName='申请人信息' groupIndex='2'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='SQRHKSZDXZQH' name='申请人户口所在地行政区划' value='' dataType='STRING' length='300' nonEmpty='true' controlType='ADDRESS' index='7' groupName='申请人信息' groupIndex='2'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='SQRXJZDXZ' name='申请人现居住地详址' value='' dataType='STRING' length='300' nonEmpty='true' controlType='ADDRESS' index='8' groupName='申请人信息' groupIndex='2'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='SQRXJZDXZQH' name='申请人现居住地行政区划' value='' dataType='STRING' length='300' nonEmpty='true' controlType='ADDRESS' index='9' groupName='申请人信息' groupIndex='2'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='PODJRXM' name='配偶登记人姓名' value='' dataType='STRING' length='50' nonEmpty='true' controlType='TEXTBOX' index='10' groupName='配偶信息' groupIndex='3'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='POSFZHM' name='配偶身份证号码' value='' dataType='STRING' length='50' nonEmpty='true' controlType='TEXTBOX' index='11' groupName='配偶信息' groupIndex='3'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='POCSNY' name='配偶出生年月' value='' dataType='DATETIME' length='12' nonEmpty='true' controlType='DATEFIELD' index='12' groupName='配偶信息' groupIndex='3'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='POHYZK' name='配偶婚姻状况' value='' dataType='STRING' length='20' nonEmpty='true' controlType='DROPDOWN_LIST' index='13' groupName='配偶信息' groupIndex='3'  isShowGroupTitle='true' inputControlValueScope='初婚,再婚'></ServiceSubjectColumn><ServiceSubjectColumn columnName='POHKSZDXZ' name='配偶户口所在地详址' value='' dataType='STRING' length='300' nonEmpty='true' controlType='ADDRESS' index='14' groupName='配偶信息' groupIndex='3'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='POHKSZDXZQH' name='配偶户口所在地行政区划' value='' dataType='STRING' length='300' nonEmpty='true' controlType='ADDRESS' index='15' groupName='配偶信息' groupIndex='3'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='POXJZDXZ' name='配偶现居住地详址' value='' dataType='STRING' length='300' nonEmpty='true' controlType='ADDRESS' index='16' groupName='配偶信息' groupIndex='3'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn><ServiceSubjectColumn columnName='POXJZDXZQH' name='配偶现居住地行政区划' value='' dataType='STRING' length='300' nonEmpty='true' controlType='ADDRESS' index='17' groupName='配偶信息' groupIndex='3'  isShowGroupTitle='true' inputControlValueScope='null'></ServiceSubjectColumn></ZsoftInfo></case>";
		} else {*/
			return "error";
		//}
	}
	
	
	
	
}






