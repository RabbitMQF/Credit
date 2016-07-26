package com.WSZW.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static final String EMPTY = "";

	public static boolean isNotBlank(String str) {
		return (str != null) && (str.length() != 0);
	}

	public static boolean isBlank(String str) {
		return (str == null) || (str.length() == 0);
	}

	public static boolean isNotTrimBlank(String str) {
		return (str != null) && (str.trim().length() != 0);
	}

	public static boolean isNotTrimBlanks(String... strs) {
		boolean result = true;
		for (String str : strs) {
			result = result && (str != null) && (str.trim().length() != 0);
		}
		return result;
	}

	public static boolean isTrimBlank(String str) {
		return (str == null) || (str.trim().length() == 0);
	}

	public static String capFirstUpperCase(String str) {
		if (isBlank(str)) {
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String capFirstLowerCase(String str) {
		if (isBlank(str)) {
			return str;
		}
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	public static boolean isIdNo(String idNo) {
		if (isTrimBlank(idNo)) {
			return false;
		}
		Pattern p = Pattern
				.compile("^([1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3})|([1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[X,x]))$");
		Matcher matcher = p.matcher(idNo);
		return matcher.find();
	}

	public static boolean isMobile(String mobile) {
		if (isTrimBlank(mobile)) {
			return false;
		}
		Pattern p = Pattern.compile("^(?:13\\d|15\\d|18\\d)-?\\d{8}$");
		Matcher matcher = p.matcher(mobile);
		return matcher.find();
	}
	
	/**
	 * 判断邮箱是否合法
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (null == email || "".equals(email))
			return false;
		// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
		Pattern p = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	
	
	
	

	public static String num2String(Long no) {
		if (no.longValue() < 10L)
			return String.valueOf(no);
		if (no.longValue() < 36L)
			return String.valueOf((char) (int) (no.longValue() - 10L + 65L));
		if (no.longValue() < 62L) {
			return String.valueOf((char) (int) (no.longValue() - 36L + 97L));
		}
		return num2String(Long.valueOf(no.longValue() / 62L))
				+ num2String(Long.valueOf(no.longValue() % 62L));
	}

	/**
	 * 分割多个图片地址
	 * 
	 * @param imgUrl
	 *            图片路径
	 * @return 图片集合
	 */
	public static List<String> stringToList(String imgUrl) {
		List<String> oList = null;
		if (isNotTrimBlank(imgUrl)) {
			oList = new ArrayList<String>();
			String[] imgs = imgUrl.split(",");
			for (String string : imgs) {
				// 图片地址路径更改后，注释掉这些
				String[] imgitem = string.split("\\/");
				String str = imgitem[0] + "//117.40.244.134:8081/";
				for (int i = 3; i < imgitem.length; i++) {
					if (i == imgitem.length - 1) {
						str += imgitem[i];
					} else {
						str += imgitem[i] + "/";
					}
				}

				oList.add(str);
			}
		}
		return oList;
	}

	public static List<String> stringToNormalList(String str) {
		List<String> oList = null;
		if (isNotTrimBlank(str)) {
			oList = new ArrayList<String>();
			String[] imgs = str.split(",");
			for (String string : imgs) {
				// String[] imgitem=string.split("\\/");
				// String str=imgitem[0]+"//117.40.244.134:8081/";
				// for (int i = 3; i < imgitem.length; i++) {
				// if (i==imgitem.length-1) {
				// str+=imgitem[i];
				// }else {
				// str+=imgitem[i]+"/";
				// }
				// }
				// String str=string.substring(0, 6);
				// String str1=string.substring(7, string.length());
				// int index=str1.
				oList.add(string);
			}
		}
		return oList;
	}

	public static List<String> stringToNormalListBy(String str) {
		List<String> oList = null;
		if (isNotTrimBlank(str)) {
			oList = new ArrayList<String>();
			String[] imgs = str.split("\\|");
			for (String string : imgs) {
				oList.add(string);
			}
		}
		return oList;
	}
	
	
	public static List<String> stringToNormalListBy1(String str) {
		List<String> oList = null;
		if (isNotTrimBlank(str)) {
			oList = new ArrayList<String>();
			String[] imgs = str.split("\\(");
			for (String string : imgs) {
				oList.add(string);
			}
		}
		return oList;
	}
	
	/**
	 * 判断是否有更新
	 * 
	 * @param currentVerssion
	 *            当前版本号
	 * @param newVerssion
	 *            回调版本号
	 * @return type 0表示为最新版本号，1表示有版本需要更新，2表示无效的版本号
	 */
	public static int stringToString(String currentVerssion, String newVerssion) {
		int type = 0;// 0表示为最新版本号，1表示有版本需要更新，2表示无效的版本号
		String[] currents = currentVerssion.split("\\.");// 按“.”进行分割
		String[] news = newVerssion.split("\\.");// 按“.”进行分割
		// 如果两者按点分割的数组一样
		if (currents.length == news.length) {
			for (int i = 0; i < news.length; i++) {
				// int one=Integer.valueOf(currents[i]);
				// int two=Integer.valueOf(news[i]);
				int item = getVerssionInfo(currents[i], news[i]);
				if (item != 0) {
					type = item;
					return type;
				}
			}
		}

		if (currents.length > news.length) {
			for (int j = 0; j < news.length; j++) {
				int item = getVerssionInfo(currents[j], news[j]);
				if (item != 0) {
					type = item;
					return type;
				}
			}
			type = 2;
			return type;
		}

		if (currents.length < news.length) {
			for (int i = 0; i < currents.length; i++) {
				int item = getVerssionInfo(currents[i], news[i]);
				if (item != 0) {
					type = item;
					return type;
				}
			}
			type = 1;
			return type;
		}
		return type;
	}

	public static int getVerssionInfo(String itemCurrent, String itemNewVerssion) {
		int itemType = 0;
		// 两者的某项的长度一样
		if (itemCurrent.length() == itemNewVerssion.length()) {
			for (int j = 0; j < itemNewVerssion.length(); j++) {
				// 获取每一位，并转换成int类型
				int one = Integer.valueOf(itemCurrent.charAt(j));
				int two = Integer.valueOf(itemNewVerssion.charAt(j));
				// 比较每一位的大小
				if (one > two) {
					itemType = 2;
					return itemType;
				} else if (one < two) {
					itemType = 1;
					return itemType;
				}
			}
		}
		if (itemCurrent.length() > itemNewVerssion.length()) {
			for (int j = 0; j < itemNewVerssion.length(); j++) {
				int one = Integer.valueOf(itemCurrent.charAt(j));
				int two = Integer.valueOf(itemNewVerssion.charAt(j));
				if (one > two) {
					itemType = 2;
					return itemType;
				} else if (one < two) {
					itemType = 1;
					return itemType;
				}
			}
			itemType = 2;
			return itemType;
		}

		if (itemCurrent.length() < itemNewVerssion.length()) {
			for (int j = 0; j < itemCurrent.length(); j++) {
				int one = Integer.valueOf(itemCurrent.charAt(j));
				int two = Integer.valueOf(itemNewVerssion.charAt(j));
				if (one > two) {
					itemType = 2;
					return itemType;
				} else if (one < two) {
					itemType = 1;
					return itemType;
				}
			}
			itemType = 1;
			return itemType;
		}
		return itemType;
	}

	/**
	 * 功能：身份证的有效验证
	 * 
	 * @param IDStr
	 *            身份证号
	 * @return 有效：返回"" 无效：返回String信息
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	public static String IDCardValidate(String IDStr) {
		String errorInfo = "";// 记录错误信息
		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
				"3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
				"9", "10", "5", "8", "4", "2" };
		String Ai = "";
		// ================ 号码的长度 15位或18位 ================
		if (IDStr.length() != 15 && IDStr.length() != 18) {
			errorInfo = "身份证号码长度应该为15位或18位。";
			return errorInfo;
		}
		// =======================(end)========================

		// ================ 数字 除最后以为都为数字 ================
		if (IDStr.length() == 18) {
			Ai = IDStr.substring(0, 17);
		} else if (IDStr.length() == 15) {
			Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
		}
		if (isNumeric(Ai) == false) {
			errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
			return errorInfo;
		}
		// =======================(end)========================

		// ================ 出生年月是否有效 ================
		String strYear = Ai.substring(6, 10);// 年份
		String strMonth = Ai.substring(10, 12);// 月份
		String strDay = Ai.substring(12, 14);// 月份
		if (isDataFormat(strYear + "-" + strMonth + "-" + strDay) == false) {
			errorInfo = "身份证生日无效。";
			return errorInfo;
		}
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
					|| (gc.getTime().getTime() - s.parse(
							strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
				errorInfo = "身份证生日不在有效范围。";
				return errorInfo;
			}
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return errorInfo = "身份证生日无效";
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return errorInfo = "身份证生日无效";
		}
		if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
			errorInfo = "身份证月份无效";
			return errorInfo;
		}
		if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
			errorInfo = "身份证日期无效";
			return errorInfo;
		}
		// =====================(end)=====================

		// ================ 地区码时候有效 ================
		Hashtable h = GetAreaCode();
		if (h.get(Ai.substring(0, 2)) == null) {
			errorInfo = "身份证地区编码错误。";
			return errorInfo;
		}
		// ==============================================

		// ================ 判断最后一位的值 ================
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi
					+ Integer.parseInt(String.valueOf(Ai.charAt(i)))
					* Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];
		Ai = Ai + strVerifyCode;

		if (IDStr.length() == 18) {
			if (Ai.equals(IDStr) == false) {
				errorInfo = "身份证无效，不是合法的身份证号码";
				return errorInfo;
			}
		} else {
			return "";
		}
		// =====================(end)=====================
		return "";
	}

	/**
	 * 功能：设置地区编码
	 * 
	 * @return Hashtable 对象
	 */
	private static Hashtable GetAreaCode() {
		Hashtable hashtable = new Hashtable();
		hashtable.put("11", "北京");
		hashtable.put("12", "天津");
		hashtable.put("13", "河北");
		hashtable.put("14", "山西");
		hashtable.put("15", "内蒙古");
		hashtable.put("21", "辽宁");
		hashtable.put("22", "吉林");
		hashtable.put("23", "黑龙江");
		hashtable.put("31", "上海");
		hashtable.put("32", "江苏");
		hashtable.put("33", "浙江");
		hashtable.put("34", "安徽");
		hashtable.put("35", "福建");
		hashtable.put("36", "江西");
		hashtable.put("37", "山东");
		hashtable.put("41", "河南");
		hashtable.put("42", "湖北");
		hashtable.put("43", "湖南");
		hashtable.put("44", "广东");
		hashtable.put("45", "广西");
		hashtable.put("46", "海南");
		hashtable.put("50", "重庆");
		hashtable.put("51", "四川");
		hashtable.put("52", "贵州");
		hashtable.put("53", "云南");
		hashtable.put("54", "西藏");
		hashtable.put("61", "陕西");
		hashtable.put("62", "甘肃");
		hashtable.put("63", "青海");
		hashtable.put("64", "宁夏");
		hashtable.put("65", "新疆");
		hashtable.put("71", "台湾");
		hashtable.put("81", "香港");
		hashtable.put("82", "澳门");
		hashtable.put("91", "国外");
		return hashtable;
	}

	/**
	 * 验证日期字符串是否是YYYY-MM-DD格式
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDataFormat(String str) {
		boolean flag = false;
		// String
		// regxStr="[1-9][0-9]{3}-[0-1][0-2]-((0[1-9])|([12][0-9])|(3[01]))";
		String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern pattern1 = Pattern.compile(regxStr);
		Matcher isNo = pattern1.matcher(str);
		if (isNo.matches()) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 将时间格式去除
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateFormat(String date) {
		String dateInfo = null;

		try {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			Date date2 = dateFormat.parse(date);
			c.setTime(date2);
			dateInfo = dateFormat.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateInfo;
	}

	/**
	 * 匹配掉空格
	 * 
	 * @param info
	 * @return
	 */
	public static String replaceSpace(String info) {
		String str = null;
		if (info != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(info);
			str = m.replaceAll("");
		}
		return str;
	}

	/**
	 * 功能：判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public static String checkStringValue(String str) {
		if (str == null || str.equals("null") || str.equals("")) {
			str = "-";
		}
		return str;
	}

	// 身份证号码验证：end

	// 以下生成一个随机的字符串
	private static long id = 0;
	private static String prefix = randomString(5) + "-";

	public static String getPacketID() {
		return prefix + Long.toString(id++);
	}

	public static String randomString(int length) {
		char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
				.toCharArray();
		if (length < 1) {
			return null;
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[new Random().nextInt(71)];
		}
		return new String(randBuffer);
	}

}