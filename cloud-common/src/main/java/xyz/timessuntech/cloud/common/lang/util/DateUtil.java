package xyz.timessuntech.cloud.common.lang.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author joeshing
 *
 */
public class DateUtil {
	// 定义比较日期的类型
	public final static int ERROR = -1000;

	public final static int ON_DAY = 1;

	public final static int ON_MONTH = 2;

	public final static int ON_YEAR = 3;

	public final static int ON_HOUR = 4;

	public final static int ON_MINUTE = 5;

	public final static int ON_SECOND = 6;

	public final static int ON_SYSTEM = 7;

	// 定义日期常量
	public final static int YEAR_MONTHS = 12;

	public final static int DAY_HOURS = 24;

	public final static int HOUR_MINUTES = 60;

	public final static int MINUTE_SECONDS = 60;

	// 一天=86400000(24*60*60*1000)毫秒(MSEL)millisecond
	public final static long DAY_MSELS = 86400000;

	// 定义格式化日期的模板
	public final static String PATTERN_SP1 = ":"; // 分隔符号

	public final static String PATTERN_DDHHMM = "dd:hh:mm"; // 天：小时：分钟

	public DateUtil() {

	}

	public static String getCurDateTime() {
		return format(new Date());
	}

	/**
	 * 得到当前日期"####-##-##"
	 * 
	 * @return 当前日期
	 */
	public static String getCurDate() {
		String fullDate = getCurYearMonth();
		int temp = getCurDay();
		if (temp < 10) {
			fullDate += "-0" + temp;
		} else {
			fullDate += "-" + temp;
		}
		return fullDate;
	}

	/**
	 * 
	 * 得到当前日期"####年##月##"
	 * 
	 * @author wangjc
	 * @date 2008-9-3 上午11:54:15
	 * @return
	 */
	public static String getCurDate1() {
		String fullDate = getCurYear() + "年";
		fullDate += getCurMonth() + "月";
		fullDate += getCurDay() + "日";
		return fullDate;
	}

	// ####-##
	public static String getCurYearMonth() {
		String fullDate = String.valueOf(getCurYear());
		int temp = getCurMonth();
		if (temp < 10) {
			fullDate += "-0" + temp;
		} else {
			fullDate += "-" + temp;
		}
		return fullDate;
	}

	// ##:##:##
	public static String getCurTime() {
		String time = getCurHourMinute();
		int temp = getCurSecond();
		if (temp < 10) {
			time += ":0" + temp;
		} else {
			time += ":" + temp;
		}
		return time;
	}

	// ##:##
	public static String getCurHourMinute() {
		String time;
		int temp = getCurHour();
		if (temp < 10) {
			time = "0" + temp + ":";
		} else {
			time = temp + ":";
		}
		temp = getCurMinute();
		if (temp < 10) {
			time += "0" + temp;
		} else {
			time += temp;
		}
		return time;
	}

	public static int getCurYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static int getCurMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public static int getCurDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	public static int getCurHour() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}

	public static int getCurMinute() {
		return Calendar.getInstance().get(Calendar.MINUTE);
	}

	public static int getCurSecond() {
		return Calendar.getInstance().get(Calendar.SECOND);
	}

	public static String getCurWeek() {
		int week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		switch (week) {
		case 2:
			return "星期一";
		case 3:
			return "星期二";
		case 4:
			return "星期三";
		case 5:
			return "星期四";
		case 6:
			return "星期五";
		case 7:
			return "星期六";
		default:
			return "星期日";
		}
	}

	/**
	 * 得到一个日期对象
	 * 
	 * @return Date
	 */
	public static Date getDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 得到一个SQL日期对象
	 * 
	 * @return Date
	 */
	public static java.sql.Date getSQLDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	/**
	 * 得到一个SQL日期对象
	 * 
	 * @return Date
	 */
	public static java.sql.Timestamp getSQLTimestamp() {
		return new java.sql.Timestamp(System.currentTimeMillis());
	}

	/**
	 * 自己编写的判断闰年的方法
	 * 
	 * @param year
	 *            int 年份
	 * @return boolean（true=闰年，false=非闰年）
	 */
	public static boolean isLeapYear(int year) {
		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			return true;
		}
		return false;
	}

	/**
	 * 自己编写的得到一年的天数的方法
	 * 
	 * @param year
	 *            int 年份
	 * @return int （非闰年=365，闰年=366）
	 */
	public static int getYearDays(int year) {
		if (isLeapYear(year)) {
			return 366;
		}
		return 365;
	}

	/**
	 * 根据比较深度来比较两个日期，并且按比较深度返回d1-d2
	 * 
	 * @param d1
	 *            Date
	 * @param d2
	 *            Date
	 * @param scope
	 *            int 比较级别
	 * @return long
	 */
	public static long compareDate(Date d1, Date d2, int scope) {
		switch (scope) {
		case ON_YEAR:
			return compareDateOnYear(d1, d2);
		case ON_MONTH:
			return compareDateOnMonth(d1, d2);
		case ON_DAY:
			return compareDateOnDay(d1, d2);
		case ON_HOUR:
			return compareDateOnHour(d1, d2);
		case ON_MINUTE:
			return compareDateOnMinute(d1, d2);
		case ON_SECOND:
			return compareDateOnSecond(d1, d2);
		case ON_SYSTEM:
			return compareDateOnSystem(d1, d2);
		default:
			return ERROR;
		}
	}

	public static Date parse(String dateString, String pattern) {
		if (dateString == null || dateString.trim().length() == 0) {
			return null;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			return formatter.parse(dateString);
		} catch (ParseException ex) {
			throw new IllegalArgumentException("Illegal datetime string " + dateString);
		}
	}

	/**
	 * 比较两个日期的年份差距
	 * 
	 * @param d1
	 *            Date
	 * @param d2
	 *            Date
	 * @return int
	 */
	public static int compareDateOnYear(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		return y1 - y2;
	}

	/**
	 * 比较两个日期在月份上的差距
	 * 
	 * @param d1
	 *            Date
	 * @param d2
	 *            Date
	 * @return int
	 */
	public static int compareDateOnMonth(Date d1, Date d2) {
		if (d1.getTime() == d2.getTime()) {
			return 0; // 日期相同返回0
		}
		int flag = -1;
		// 比较两个日期使日期较小的日期排在前面
		if (d1.getTime() > d2.getTime()) { // 日期一在日期二之后
			Date temp = d1;
			d1 = d2;
			d2 = temp;
			flag = 1;
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		int mon1 = c1.get(Calendar.MONTH);
		int mon2 = c2.get(Calendar.MONTH);
		int mons = 0;
		for (int i = 1; i <= y2 - y1; i++) {
			mons += YEAR_MONTHS; // 一年有12个月
		}
		return (mons - mon1 + mon2) * flag;
	}

	/**
	 * 比较两个日期并返回两个日期相差多少天(d1－d2)
	 * 
	 * @param d1
	 *            Date
	 * @param d2
	 *            Date
	 * @return int
	 */
	public static int compareDateOnDay(Date d1, Date d2) {
		if (d1.getTime() == d2.getTime()) {
			return 0; // 日期相同返回0
		}
		int flag = -1;
		// 比较两个日期使日期较小的日期排在前面
		if (d1.getTime() > d2.getTime()) { // 日期一在日期二之后
			Date temp = d1;
			d1 = d2;
			d2 = temp;
			flag = 1;
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		int day1 = c1.get(Calendar.DAY_OF_YEAR);
		int day2 = c2.get(Calendar.DAY_OF_YEAR);
		int days = 0;
		for (int i = 1; i <= y2 - y1; i++) {
			days += getYearDays(y1);
		}
		return (days - day1 + day2) * flag;
	}

	public static long compareDateOnHour(Date d1, Date d2) {
		if (d1.getTime() == d2.getTime()) {
			return 0; // 日期相同返回0
		}
		int flag = -1; // d1<d2
		// 比较两个日期使日期较小的日期排在前面
		if (d1.getTime() > d2.getTime()) { // 日期一在日期二之后
			Date temp = d1;
			d1 = d2;
			d2 = temp;
			flag = 1;
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		int day1 = c1.get(Calendar.DAY_OF_YEAR);
		int day2 = c2.get(Calendar.DAY_OF_YEAR);
		int days = 0;
		for (int i = 1; i <= y2 - y1; i++) {
			days += getYearDays(y1);
		}
		days = (days - day1 + day2);
		int h1 = c1.get(Calendar.HOUR_OF_DAY);
		int h2 = c2.get(Calendar.HOUR_OF_DAY);
		return (days * DAY_HOURS - h1 + h2) * flag;
	}

	public static long compareDateOnMinute(Date d1, Date d2) {
		if (d1.getTime() == d2.getTime()) {
			return 0; // 日期相同返回0
		}
		int flag = -1; // d1<d2
		// 比较两个日期使日期较小的日期排在前面
		if (d1.getTime() > d2.getTime()) { // 日期一在日期二之后
			Date temp = d1;
			d1 = d2;
			d2 = temp;
			flag = 1;
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		int day1 = c1.get(Calendar.DAY_OF_YEAR);
		int day2 = c2.get(Calendar.DAY_OF_YEAR);
		int days = 0;
		for (int i = 1; i <= y2 - y1; i++) {
			days += getYearDays(y1);
		}
		days = (days - day1 + day2);
		int h1 = c1.get(Calendar.HOUR_OF_DAY);
		int h2 = c2.get(Calendar.HOUR_OF_DAY);
		long hours = days * DAY_HOURS - h1 + h2;
		int m1 = c1.get(Calendar.MINUTE);
		int m2 = c2.get(Calendar.MINUTE);
		return (hours * HOUR_MINUTES - m1 + m2) * flag;
	}

	public static long compareDateOnSecond(Date d1, Date d2) {
		if (d1.getTime() == d2.getTime()) {
			return 0; // 日期相同返回0
		}
		int flag = -1; // d1<d2
		// 比较两个日期使日期较小的日期排在前面
		if (d1.getTime() > d2.getTime()) { // 日期一在日期二之后
			Date temp = d1;
			d1 = d2;
			d2 = temp;
			flag = 1;
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		int day1 = c1.get(Calendar.DAY_OF_YEAR);
		int day2 = c2.get(Calendar.DAY_OF_YEAR);
		int days = 0;
		for (int i = 1; i <= y2 - y1; i++) {
			days += getYearDays(y1);
		}
		days = (days - day1 + day2);
		int h1 = c1.get(Calendar.HOUR_OF_DAY);
		int h2 = c2.get(Calendar.HOUR_OF_DAY);
		long hours = days * DAY_HOURS - h1 + h2;
		int m1 = c1.get(Calendar.MINUTE);
		int m2 = c2.get(Calendar.MINUTE);
		long minutes = hours * HOUR_MINUTES - m1 + m2;
		int s1 = c1.get(Calendar.SECOND);
		int s2 = c2.get(Calendar.SECOND);
		return (minutes * MINUTE_SECONDS - s1 + s2) * flag;
	}

	public static int compareDateOnSystem(Date d1, Date d2) {
		return (int) (d1.getTime() - d2.getTime());
	}

	/**
	 * 将日期转换在指定的级别上转换为数字
	 * 
	 * @param source
	 *            String
	 * @param pattern
	 *            String
	 * @param scope
	 *            int
	 * @return long
	 */
	public static long convertTo(String source, String pattern, int scope) {
		switch (scope) {
		case ON_MINUTE:
			return convertToMSEL(source, pattern);
		default:
			return ERROR;
		}
	}

	/**
	 * 格式化一个日期对象，默认的格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            Date
	 * @param pattern
	 *            String
	 * @return String
	 */
	public static String format(Date date, String pattern) {
		String fmtStr = "";
		if (date != null) {
			DateFormat df = new SimpleDateFormat(pattern);
			fmtStr = df.format(date);
		}
		return fmtStr;
	}

	/**
	 * 将一个字符串格式化为一个Date对象
	 * 
	 * @param obj
	 *            Object
	 * @return Date
	 */
	public static Date parse(Object obj) {
		try {
			if (obj == null) {
				return null;
			}
			String dateString = obj.toString().trim();
			if (dateString.length() == 0) {
				return null;
			}
			if (dateString.length() == 10) {
				dateString += " 00:00:00";
			}
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.parse(dateString);
		} catch (ParseException ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 根据指定的pattern格式化类型为String的日期 update by zhuzf
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static String format(String dateStr, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return format(date, pattern);
	}

	/**
	 * 毫秒 MSEL millisecond
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static long convertToMSEL(String source, String pattern) {
		if (pattern == null) {
			return ERROR;
		}
		pattern = pattern.trim().toLowerCase();
		if (!PATTERN_DDHHMM.equals(pattern)) {
			return ERROR;
		}
		String[] ms = StringUtil.toArray(source, PATTERN_SP1);
		if (ms.length != 3) {
			return ERROR;
		}
		int dd = 0;
		int hh = 0;
		int mm = 0;
		try {
			dd = Integer.parseInt(ms[0]);
			hh = Integer.parseInt(ms[1]);
			mm = Integer.parseInt(ms[2]);
		} catch (NumberFormatException ex) {
			return ERROR;
		}
		return ((dd * DAY_HOURS + hh) * HOUR_MINUTES + mm) * 60 * 1000;
	}

	public final static List<String> separateDate(String startDate, String endDate, long step) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = parse(startDate);
		Date d2 = parse(endDate);
		long start = d1.getTime();
		long end = d2.getTime();
		List<String> rtn = new ArrayList<String>();
		long tmp = start;
		while (end > tmp) {
			rtn.add(df.format(new Date(tmp)));
			tmp += step;
		}
		rtn.add(df.format(new Date(tmp)));
		return rtn;
	}

	/**
	 * 返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 日期相加
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static Date addDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 日期相加(跳过周末)
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static Date addDateIgnoreWeekend(Date date, int day) {
		Date result = date;
		int index = 1;
		while (index <= day) {
			result = addDate(result, 1);
			if (!(result.getDay() == 0 || result.getDay() == 6)) {
				index++;
			}
		}
		return result;
	}

	/**
	 * 日期相减
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static Date deleteDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 
	 * 得到一个和当前日期间隔days天数的日期
	 * 
	 * @author wangjc
	 * @date 2008-8-20 上午09:12:16
	 * @param days
	 * @return
	 */
	public static String getDate(int days) {
		long millis = System.currentTimeMillis();
		millis = millis + days * DAY_MSELS;
		Date date = new Date(millis);
		String day = format(date, "yyyy-MM-dd");
		return day;
	}

	public static String getFromTime(String dateFrom) {
		if (StringUtil.isEmpty(dateFrom))
			return dateFrom;
		return dateFrom + " 00:00:00";
	}

	public static String getToTime(String dateTo) {
		if (StringUtil.isEmpty(dateTo))
			return dateTo;
		return dateTo + " 24:00:00";
	}

}
