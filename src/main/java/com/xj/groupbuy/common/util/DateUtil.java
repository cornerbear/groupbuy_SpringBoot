package com.xj.groupbuy.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能描述：日期工具类
 *
 * @author 王本源
 * @version 1.0
 * @Date 2013-11-5
 */
public class DateUtil {

    public static Date date = null;

    public static DateFormat dateFormat = null;

    public static Calendar calendar = null;

    /**
     * 功能描述：格式化日期
     * 用法：parseDate("2014-1-4 15:57:00", "yyyy/MM/dd HH:mm:ss")
     *
     * @param dateStr String 字符型日期
     * @param format  String 格式
     * @return Date 日期
     */
    public static Date parseDate(String dateStr, String format) {
        try {
            dateFormat = new SimpleDateFormat(format);
            String dt = dateStr.replaceAll("-", "/");
            if ((!dt.equals("")) && (dt.length() < format.length())) {
                dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]",
                        "0");
            }
            date = (Date) dateFormat.parse(dt);
        } catch (Exception e) {
        }
        return date;
    }

    /**
     * 功能描述：格式化日期
     *
     * @param dateStr String 字符型日期：YYYY-MM-DD 格式
     * @return Date
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, "yyyy/MM/dd");
    }


    /**
     * 功能描述：格式化日期
     *
     * @param dateStr String 字符型日期：YYYY-MM-DD 格式
     * @return Date yyyyMMdd
     */
    public static Date parseDate2(String dateStr) {
        return parseDate(dateStr, "yyyyMMdd");
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     * 功能描述：返回字符型日期时间
     *
     * @param date Date 日期
     * @return 返回字符型日期时间 yyyy/MM/dd HH:mm:ss 格式
     */
    public static Date getDateTime(String dateStr) {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return (Date) dateFormat.parse(dateStr);
        } catch (ParseException e) {
        }
        return date;
    }

    /**
     * 功能描述：格式化输出日期
     *
     * @param date   Date 日期
     * @param format String 格式
     * @return 返回字符型日期
     */
    public static String format(Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                dateFormat = new SimpleDateFormat(format);
                result = dateFormat.format(date);
            }
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 功能描述：
     *
     * @param date Date 日期
     * @return
     */
    public static String format(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 功能描述：返回年份
     *
     * @param date Date 日期
     * @return 返回年份
     */
    public static int getYear(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 功能描述：返回月份
     *
     * @param date Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 功能描述：返回日份
     *
     * @param date Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能描述：返回小时
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分钟
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 将YYYYMMDD格式的日期修改为YYYY年MM月DD格式
     *
     * @param date YYYYMMDD格式的日期字符串
     * @return 格式化后的日期
     */
    public static String parseFormat(String date) {
        String d = "";
        if (date != null && date.length() == 8) {
            String year = date.substring(0, 4);
            String month = date.substring(4, 6);
            String day = date.substring(6);
            if (day.length() == 1) {
                day = "0" + day;
            }
            d = year + "-" + month + "-" + day;
        } else {
            d = format(new Date(), "yyyy-MM-dd");
        }
        return d;
    }

    /**
     * 返回秒钟
     *
     * @param date Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     * 功能描述：返回字符型日期
     *
     * @param date 日期
     * @return 返回字符型日期 yyyy/MM/dd 格式
     */
    public static String getDate(Date date) {
        return format(date, "yyyy/MM/dd");
    }


    /**
     * 功能描述：返回字符型时间
     *
     * @param date Date 日期
     * @return 返回字符型时间 HH:mm:ss 格式
     */
    public static String getTime(Date date) {
        return format(date, "HH:mm:ss");
    }

    /**
     * 功能描述：返回字符型日期时间
     *
     * @param date Date 日期
     * @return 返回字符型日期时间 yyyy/MM/dd HH:mm:ss 格式
     */
    public static String getDateTime(Date date) {
        return format(date, "yyyy/MM/dd HH:mm:ss");
    }

    /**
     * 功能描述：日期相加
     *
     * @param date Date 日期
     * @param day  int 天数
     * @return 返回相加后的日期
     */
    public static Date addDate(Date date, int day) {
        calendar = Calendar.getInstance();
        long millis = getMillis(date) + ((long) day) * 24 * 3600 * 1000;
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    /**
     * 功能描述：日期相减
     *
     * @param date  Date 日期
     * @param date1 Date 日期
     * @return 返回相减后的日期
     */
    public static int diffDate(Date date, Date date1) {
        return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
    }

    /**
     * 功能描述：取得指定月份的第一天
     *
     * @param strdate String 字符型日期
     * @return String yyyy-MM-dd 格式
     */
    public static String getMonthBegin(String strdate) {
        date = parseDate(strdate);
        return format(date, "yyyy-MM") + "-01";
    }

    /**
     * 功能描述：取得指定月份的最后一天
     *
     * @param strdate String 字符型日期
     * @return String 日期字符串 yyyy-MM-dd格式
     */
    public static String getMonthEnd(String strdate) {
        date = parseDate(getMonthBegin(strdate));
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return formatDate(calendar.getTime());
    }

    /**
     * 功能描述：常用的格式化日期
     *
     * @param date Date 日期
     * @return String 日期字符串 yyyy-MM-dd格式
     */
    public static String formatDate(Date date) {
        return formatDateByFormat(date, "yyyy-MM-dd");
    }

    /**
     * 功能描述：以指定的格式来格式化日期
     *
     * @param date   Date 日期
     * @param format String 格式
     * @return String 日期字符串
     */
    public static String formatDateByFormat(Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String getCurDateTimeStr() {
        Date newdate = new Date();
        long datetime = newdate.getTime();
        return new StringBuffer().append(datetime).toString();
    }

    public static String getCurDateTime() {
        Date newdate = new Date();
        long datetime = newdate.getTime();
        Timestamp timestamp = new Timestamp(datetime);
        return (timestamp.toString()).substring(0, 19);
    }

    public static String getCurrentDate() {
        return DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
//		Date newdate = new Date();
//		long datetime = newdate.getTime();
//		Timestamp timestamp = new Timestamp(datetime);
//		String currentdate = (timestamp.toString()).substring(0, 4) + "-"
//				+ (timestamp.toString()).substring(5, 7) + "-"
//				+ (timestamp.toString()).substring(8, 10);
//		return currentdate;
    }

    // 返回"yyyyMMdd"格式日期
    public static String getDateYYYYMMDD() {
        Date newdate = new Date();
        long datetime = newdate.getTime();
        Timestamp timestamp = new Timestamp(datetime);
        String currentdate = (timestamp.toString()).substring(0, 4)
                + (timestamp.toString()).substring(5, 7)
                + (timestamp.toString()).substring(8, 10);
        return currentdate;
    }

    // 返回"yyMMdd"格式日期
    public static String getDateYYMMDD() {
        Date newdate = new Date();
        long datetime = newdate.getTime();
        Timestamp timestamp = new Timestamp(datetime);
        String currentdate = (timestamp.toString()).substring(2, 4)
                + (timestamp.toString()).substring(5, 7)
                + (timestamp.toString()).substring(8, 10);
        return currentdate;
    }

    //获取开始时间和结束时间之间的天数
    public static long getDisDays(String datebegin, String dateend) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateBegin = sdf.parse(datebegin);
            Date dateEnd = sdf.parse(dateend);
            return (dateEnd.getTime() - dateBegin.getTime()) / (3600 * 24 * 1000) + 1;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 比较两个日期相差的分钟数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 结束日期和开始日期之间的分钟数
     */
    public static long compareMinite(Date startDate, Date endDate) {
        long startMinute = 0;
        if (startDate != null) {
            startMinute = startDate.getTime() / (1000 * 60);
        }
        long endMinute = 0;
        if (endDate != null) {
            endMinute = endDate.getTime() / (1000 * 60);
        }
        return endMinute - startMinute;
    }

    /**
     * 比较两个日期相差的秒数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 结束日期和开始日期之间的秒数
     */
    public static long compareSecond(Date startDate, Date endDate) {
        long startMinute = startDate.getTime() / (1000);
        long endMinute = endDate.getTime() / (1000);
        return endMinute - startMinute;
    }

    /**
     * 日期转化字符串
     */
    public static String dateToString(Date date, String format, String defaultStr) {
        String ds = defaultStr;
        if (date == null) {
            return defaultStr;
        } else {
            try {
                SimpleDateFormat fmt = new SimpleDateFormat(format);
                ds = fmt.format(date);
            } catch (Exception e) {
                return defaultStr;
            }
        }
        return ds;
    }

    /**
     * 得到yyyy-MM-dd 格式的时间
     */
    public static Date getDatePareDate() {
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 得到当前时间的前2个月的时间
     */
    public static Date getCurDateBeforeTwoMon() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -2);
        Date date = c.getTime();
        return date;
    }

    /**
     * 得到当前时间的后一天
     */
    public static Date getCurDateAfterOne(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, +1);
        Date date1 = c.getTime();
        return date1;
    }

    /**
     * 功能描述：得到时间的后一天
     * @return dateStr yyyy-MM-dd
     */
    public static String getCurDateAfterOne2(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, +1);
        Date date1 = c.getTime();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        return dateStr;
    }

    /**
     * 得到yyyy/MM/dd 格式的时间
     *
     * @param date yyyyMMdd
     * @return dateStr yyyy/MM/dd
     */
    public static String getDateNext(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date1 = null;
        try {
            date1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
        String dateStr = sdf2.format(date1);
        return dateStr;
    }

    /**
     * 功能描述：得到yyyy/MM/dd 格式的时间
     *
     * @param date yyyy-MM-dd
     * @return dateStr  yyyy/MM/dd
     */
    public static String getDateNext2(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
        String dateStr = sdf2.format(date1);
        return dateStr;
    }

    /**
     * 功能描述：得到时间yyyy/MM/dd 格式的时间
     *
     * @param date yyyyMMdd
     * @return date3 yyyy/MM/dd
     */
    public static String getCurDateNext(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date1 = sdf.format(date);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        Date date2 = null;
        try {
            date2 = sdf2.parse(date1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/MM/dd");
        String date3 = sdf3.format(date2);
        return date3;
    }

    public static String getCurrentMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(new Date());
    }

    public static String getPreMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        date = calendar.getTime();
        String accDate = format.format(date);
        return accDate;
    }

    public static String getPreMonth(String ym) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        Date date = parseDate(ym + "01", "yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        date = calendar.getTime();
        String accDate = format.format(date);
        return accDate;
    }


    public static void main(String[] args) throws ParseException {
        System.out.println(getPreMonth("202001"));
    }

}
