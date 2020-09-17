package org.io.hydoskyzero.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * java时间与cron表达式互转 工具类
 * @author 念着倒才子傻
 */
public class DateUtil {

	private static final String DATEFORMAT = "ss mm HH dd MM ? yyyy";

	/***
	 * 时间转cron表达式 返回格式自定义
	 * @param date 日期
	 * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatDateByPattern(Date date, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String formatTimeStr = null;
		if (date != null) {
			formatTimeStr = sdf.format(date);
		}
		return formatTimeStr;
	}

	/**
	 * cron表达式 转 时间类型 返回格式自定义
	 * @param cron
	 * @param dateFormat 日期格式
	 * @return
	 * @throws ParseException
	 */
	public static Date parseStringToDate(String cron, String dateFormat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = null;
		if (cron != null) {
			date = sdf.parse(cron);
		}
		return date;
	}

	/***
	 * 时间转cron表达式
	 * convert Date to cron ,eg.  "0 06 10 15 1 ? 2014"
	 * @param date  : 时间点
	 * @return
	 */
	public static String getCron(Date date) {
		return formatDateByPattern(date, DATEFORMAT);
	}

	/***
	 *  cron表达式 转 时间类型yyyy-MM-dd HH:mm:ss
	 * convert cron to Date
	 * @param cron  : cron表达式 cron表达式仅限于周为*
	 * @return
	 */
	public static Date getDate(String cron) throws ParseException {
		return parseStringToDate(cron, DATEFORMAT);
	}
}
