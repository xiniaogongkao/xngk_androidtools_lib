package com.xngk.xngklibrary.date;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author zhousu
 * date 2017/8/14 10:38
 * description
 **/

public class DateUtils {

    private static final String FORMAT_CIRCLE = "yyyyMMddHHmmss";
    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMATS = "HH:mm:ss";
    //    private static final SimpleDateFormat datetimeFormatss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//:ss
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat timeFormatCourse = new SimpleDateFormat("yyyy.MM.dd");

    public static Date str2Date(String str) {
        return str2Date(str, null);
    }

    /**
     * 字符串转时间HH:mm:ss
     */
    public static Date str2Dates(String str) {
        return str2Dates(str, null);

    }

    public static long getToLong(String DateTime) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = sdf.parse(DateTime);
        return time.getTime();
    }

    /**
     * 获取系统时间
     *
     * @param format 要转换的时间格式（yyyy年MM月dd日 HH:mm:ss）
     * @return
     */
    public static String getSystemDate(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public static long getSystemcurrentTimeMillis(){
        return System.currentTimeMillis();
    }

    /**
     * 字符串转时间
     *
     * @param str
     * @param format
     * @return
     */
    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;

    }
    /**
     * 判断目标日期距离当前日期多长时间
     * time2 一周前
     * time3 两周前
     * time4 三周前
     * @param timeStamp
     * @return
     */
    public static String convertTimeToFormat(long timeStamp) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - timeStamp;
        long time2 = (3600 * 24 * 13) - 3600;
        long time3 = 3600 * 24 * 21;
        long time4=3600 * 24 * 30;
        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 7) {
            return time / 3600 / 24 + "天前";
        } else if (time >= 3600 * 24 * 7 && time < 3600 * 24 * 30) {
            if (time < time2) {
                return "1周前";
//                return time / 3600 / 24 + "周前";
            } else if (time > time2 && time < time3) {
                return "2周前";
            }else if(time>time3&&time<time4){
                return "3周前";
            }
            return "刚刚";
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + "月前";
//        }
//        else if (time >= 3600 * 24 * 30 * 12) {
//            return time / 3600 / 24 / 30 / 12 + "年";
        }
        else {
            return "刚刚";
        }

    }



    /**
     * 字符串转时间HH:mm:ss
     *
     * @param str
     * @param format
     * @return
     */
    public static Date str2Dates(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMATS;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;

    }

    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);
    }

    public static Calendar str2Calendar(String str, String format) {
        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;

    }

    public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return date2Str(c, null);
    }


    public static String date2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }


    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }


    /**
     * 获得当前日期的字符串格式
     *
     * @param format
     * @return
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return date2Str(c, format);
    }

    /**
     * <<<<<<< HEAD
     * 获取两个月之后的日期
     *
     * @param format
     * @return
     */
    public static String getDateTwoMonthLater(String format) {
        Date da = new Date();//获取当前时间
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(da);//把当前时间赋给日历
        calendar.add(Calendar.MONTH, 2);  //设置为后2月，-2前两个月
        da = calendar.getTime();//获取2个月前的时间
        return formatDateString(da, format);
    }

    /**
     * 获取当前时间的格式  yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCircleTime() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    }

    /**
     * 获取当前时间的格式  yyyyMMddHHmmss
     *
     * @return
     */
    public static String getGuangChangTuijianTime() {
        return new SimpleDateFormat(FORMAT).format(new Date());

    }

    /**
     * 格式到秒
     *
     * @param time
     * @return
     */
    public static String getMillon(long time) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);

    }

    /**
     * 格式到天
     *
     * @param time
     * @return
     */
    public static String getDay(long time) {
        return new SimpleDateFormat("yyyy-MM-dd").format(time);

    }

    /**
     * 格式到毫秒
     *
     * @param time
     * @return
     */
    public static String getSMillon(long time) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);

    }

    /**
     * 字符串转换到时间格式
     *
     * @param dateStr   需要转换的字符串
     * @param formatStr 需要格式的目标字符串 举例 yyyy-MM-dd
     * @return Date 返回转换后的时间
     * @throws ParseException 转换异常
     */
    public static Date StringToDate(String dateStr, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 时间格式转换
     *
     * @param date    时间
     * @param format  本来格式
     * @param format1 要转换的格式
     * @return
     */
    public static String dateFormatCourse(String date, String format, String format1) {
        SimpleDateFormat timeFormatCourse = new SimpleDateFormat(format1);
        try {
            String pat1 = format;
            SimpleDateFormat sdf1 = new SimpleDateFormat(pat1);
            return timeFormatCourse.format(sdf1.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
            return "1970-01-01 00:00";
        }
    }

    /**
     * 时间戳转日期
     *
     * @param time
     * @param timeType
     * @return
     */
    public static String formatTime(String time, String timeType) {
        SimpleDateFormat sdr = new SimpleDateFormat(timeType);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }


    /**
     * 获取六个月之前的当月第一天
     *
     * @param format
     * @return
     */

    public static String getDateMonthAgo(String format) {
        Date da = new Date();//获取当前时间
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(da);//把当前时间赋给日历
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, -6);  //设置为前6月
        calendar.set(Calendar.HOUR_OF_DAY, 0);// H置零
        calendar.set(Calendar.MINUTE, 0);// m置零
        calendar.set(Calendar.SECOND, 0);// s置零
        calendar.set(Calendar.MILLISECOND, 0);// S置零
        da = calendar.getTime();
        String date = formatDateString(da, format);
        return date;
    }

    /**
     * 22      * 日期格式字符串转换成时间戳
     * 23      * @param date 字符串日期
     * 24      * @param format 如：yyyy-MM-dd HH:mm:ss
     * 25      * @return
     * 26
     */
    public static long date2TimeStamp(String date_str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
            return sdf.parse(date_str).getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 转化时间输入时间与当前时间的间隔
     *
     * @param date：2017-10-12 20:07
     * @return
     */
    public static String converTime(String date) {
        return converTime(date2TimeStamp(date));
    }

    public static String converTime(long timestamp) {
        long currentSeconds = System.currentTimeMillis() / 1000;
        long timeGap = currentSeconds - timestamp;// 与现在时间相差秒数
        String timeStr = null;
        if (timeGap > 24 * 60 * 60) {// 1天以上
            timeStr = timeGap / (24 * 60 * 60) + "天前";
        } else if (timeGap > 60 * 60) {// 1小时-24小时
            timeStr = timeGap / (60 * 60) + "小时前";
        } else if (timeGap > 60) {// 1分钟-59分钟
            timeStr = timeGap / 60 + "分钟前";
        } else {// 1秒钟-59秒钟
            timeStr = "刚刚";
        }
        return timeStr;
    }


    /**
     * 获得当前日期时间 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String currentDatetime() {
        return datetimeFormat.format(now());
    }

    /**
     * 获得当前日期时间 日期时间格式HH:mm:ss
     *
     * @return
     */
    public static String currentDatetimes() {
        return timeFormat.format(now());
    }


    /**
     * 把毫秒数 转换成 HH:mm:ss
     *
     * @return
     */
    public static String getDateFrommillisecond(long ms) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String hms = formatter.format(ms);
        System.out.println(hms);
        return hms;
    }

    /**
     * 格式化日期时间 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String formatDatetime(Date date) {
        return datetimeFormat.format(date);
    }

    /**
     * 格式化日期时间 日期时间格式HH:mm:ss
     *
     * @return
     */
    public static String formatDatetimes(Date date) {
        return timeFormat.format(date);
    }

    public static String formatDatetime(String timestamp) {
        return datetimeFormat.format(getDate(timestamp));
    }

    public static Date getDate(String timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = now();
        try {
            date = sdf.parse(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获得当前时间的<code>java.util.Date</code>对象
     *
     * @return
     */
    public static Date now() {
        return new Date();
    }

    public static Calendar calendar() {
        Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        return cal;
    }

    /**
     * <<<<<<< HEAD
     * 获得当前时间的毫秒数
     * <p>
     *
     * @return
     */
    public static long millis() {
        return System.currentTimeMillis();
    }

    /**
     * 获得当前Chinese月份
     *
     * @return
     */
    public static int month() {
        return calendar().get(Calendar.MONTH) + 1;
    }

    /**
     * 获得月份中的第几天
     *
     * @return
     */
    public static int dayOfMonth() {
        return calendar().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 今天是星期的第几天
     *
     * @return
     */
    public static int dayOfWeek() {
        return calendar().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 今天是年中的第几天
     *
     * @return
     */
    public static int dayOfYear() {
        return calendar().get(Calendar.DAY_OF_YEAR);
    }

    /**
     * =======
     * >>>>>>> 71745ec17ca37526920784ccb7803382ffe13453
     * 判断原日期是否在目标日期之前
     *
     * @param src
     * @param dst
     * @return
     */
    public static boolean isBefore(Date src, Date dst) {
        return src.before(dst);
    }

    /**
     * 判断原日期是否在目标日期之后
     *
     * @param src
     * @param dst
     * @return
     */
    public static boolean isAfter(Date src, Date dst) {
        return src.after(dst);
    }

    /**
     * 判断两日期是否相同
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isEqual(Date date1, Date date2) {
        return date1.compareTo(date2) == 0;
    }

    /**
     * 判断某个日期是否在某个日期范围
     *
     * @param beginDate 日期范围开始
     * @param endDate   日期范围结束
     * @param src       需要判断的日期
     * @return
     */
    public static boolean between(Date beginDate, Date endDate, Date src) {
        return beginDate.before(src) && endDate.after(src);
    }

    /**
     * 获得当前月的最后一天
     * <p>
     * HH:mm:ss为0，毫秒为999
     *
     * @return
     */
    public static Date lastDayOfMonth() {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_MONTH, 0); // M月置零
        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
        cal.set(Calendar.MINUTE, 0);// m置零
        cal.set(Calendar.SECOND, 0);// s置零
        cal.set(Calendar.MILLISECOND, 0);// S置零
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
        cal.set(Calendar.MILLISECOND, -1);// 毫秒-1
        return cal.getTime();
    }

    /**
     * 获得当前月的第一天
     * <p>
     * HH:mm:ss SS为零
     *
     * @return
     */
    public static Date firstDayOfMonth() {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
        cal.set(Calendar.MINUTE, 0);// m置零
        cal.set(Calendar.SECOND, 0);// s置零
        cal.set(Calendar.MILLISECOND, 0);// S置零
        return cal.getTime();
    }

    private static Date weekDay(int week) {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_WEEK, week);
        return cal.getTime();
    }

    /**
     * 获得周五日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date friday() {
        return weekDay(Calendar.FRIDAY);
    }

    /**
     * 获得周六日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date saturday() {
        return weekDay(Calendar.SATURDAY);
    }

    /**
     * 获得周日日期 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date sunday() {
        return weekDay(Calendar.SUNDAY);
    }


    /**
     * 比较时间大小
     *
     * @param begin
     * @param end
     * @return
     */
    public static int compareDate(String begin, String end, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            Date beginDate = df.parse(begin);
            Date endDate = df.parse(end);
            if (beginDate.getTime() <= endDate.getTime()) {
                return 1;
            } else if (beginDate.getTime() >= endDate.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 年份月份是否相等
     *
     * @param begin
     * @param end
     * @param format
     * @return
     */
    public static boolean isSameDate(String begin, String end, String format) {

        DateFormat df = new SimpleDateFormat(format);
        try {
            Date date1 = df.parse(begin);
            Date date2 = df.parse(end);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);

            boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);

            boolean isSameMonth = cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);

            boolean isSameDate = isSameYear && isSameMonth;

            return isSameDate;
        } catch (Exception e) {
        }
        return false;


    }

    /**
     * <<<<<<< HEAD
     * 获得年份
     *
     * @param date
     * @return
     */
    public int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获得月份
     *
     * @param date
     * @return
     */
    public int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得星期几
     *
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获得日期
     *
     * @param date
     * @return
     */
    public int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DATE);
    }

    /**
     * 获得天数差
     *
     * @param begin
     * @param end
     * @return
     */
    public long getDayDiff(Date begin, Date end) {
        long day = 1;
        if (end.getTime() < begin.getTime()) {
            day = -1;
        } else if (end.getTime() == begin.getTime()) {
            day = 1;
        } else {
            day += (end.getTime() - begin.getTime()) / (24 * 60 * 60 * 1000);
        }
        return day;
    }


    /**
     * @param date
     * @param format
     * @return
     */
    public static String formatDateString(Date date, String format) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }

    }

    /***
     * 时间往后推几天
     *
     * @param i
     * @return
     */
    public static String addDateString(int i) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, i);
        return sf.format(c.getTime());
    }

    public static String weekDateString(int i) {
        Calendar calendar = Calendar.getInstance();
        int todayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int afterNDay = (todayOfWeek + i) % 7;
        return String.valueOf(afterNDay + 1);
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param i
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(int i) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        int afterNDay = (w + i) % 7;
        if (w < 0)
            w = 0;
        return weekDays[afterNDay];
    }

    /**
     * 获取剩余时间的
     *
     * @param endDateStr 结束时间的string
     * @return
     */
    public static String getLeftTime(String endDateStr) {
        String leftTime = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date currDate = new Date(System.currentTimeMillis());

            Date date = df.parse(endDateStr);
            long l = date.getTime() - currDate.getTime();
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            leftTime = "" + day + "天" + hour + "小时" + min + "分" + s + "秒";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return leftTime;
    }

    /**
     * 将2019-06-24 13:44:50 转成 “今日13:44” 或者“明日13:44”
     *
     * @param
     * @return
     */
    public static String convertLiveTime(String dateStr) {
        String pat1 = "yyyy-MM-dd HH:mm";
        SimpleDateFormat sd = new SimpleDateFormat(pat1);
        Date date = null;
        try {
            date = sd.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sfHour = new SimpleDateFormat("HH:mm");
        String hour = sfHour.format(date);
        //获取今天的日期
        int nowDay = Integer.valueOf(sf.format(now));
        //对比的时间
        int day = Integer.valueOf(sf.format(date));
        if (day == nowDay) {
            return "今日 " + hour;
        } else if (nowDay + 1 == day) {
            return "明天" + hour;
        } else {
            return "今日 " + hour;
            //return sd.format(date);
        }

    }


    public static String convertLiveTime1(String dateStr) {
        if (TextUtils.isEmpty(dateStr)) return "";
        String pat1 = "yyyy-MM-dd HH:mm";
        String pat2 = "yyyy-MM-dd";
        SimpleDateFormat sd = new SimpleDateFormat(pat1);
        SimpleDateFormat sd1 = new SimpleDateFormat(pat2);
        Date date = null;
        Date date1 = null;
        try {
            date = sd.parse(dateStr);
            date1 = sd1.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sfHour = new SimpleDateFormat("HH:mm");
        String hour = sfHour.format(date);
        //获取今天的日期
        int nowDay = Integer.valueOf(sf.format(now));
        //对比的时间
        int day = Integer.valueOf(sf.format(date));
        if (day == nowDay) {
            return "今日  " + hour;
        } else if (nowDay - 1 == day) {
            return "昨日 " + hour;
        } else {
            return sd1.format(date1);
        }

    }

    /**
     * 是否是今天
     *
     * @param date1
     * @param date2
     * @param format
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String ds1 = sdf.format(date1);
        String ds2 = sdf.format(date2);
        if (ds1.equals(ds2)) {
            return true;
        } else
            return false;
    }

    public static String convertLiveTime3(String dateStr) {
        String pat1 = "yyyy-MM-dd HH:mm:ss";
        String pat2 = "MM.dd HH:mm";
        SimpleDateFormat sd = new SimpleDateFormat(pat1);
        SimpleDateFormat sd1 = new SimpleDateFormat(pat2);
        Date date = null;
        Date date1 = null;
        try {
            date = sd.parse(dateStr);
            date1 = sd1.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sfHour = new SimpleDateFormat("HH:mm");
        String hour = sfHour.format(date);
        //获取今天的日期
        int nowDay = Integer.valueOf(sf.format(now));
        //对比的时间
        int day = Integer.valueOf(sf.format(date));
        if (day == nowDay) {
            return "今天" + hour;
        }
//        else if (nowDay + 1 == day) {
//            return "明日 " + hour;
//        } else if (nowDay - 1 == day) {
//            return "昨日 " + hour;
//        }
        else {
            return sd1.format(date);
        }

    }


    public static int convertLiveTime4(Long time) {
        String pat1 = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sd = new SimpleDateFormat(pat1);
        Date date = new Date(time);
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sfHour = new SimpleDateFormat("HH:mm");
        String hour = sfHour.format(date);
        //获取今天的日期
        int nowDay = Integer.valueOf(sf.format(now));
        //对比的时间
        int day = Integer.valueOf(sf.format(date));
        if (day == nowDay) {
            return 1;
        } else if (differentDays(date, now) < 6) {
            return 2;
        } else {
            return 3;
        }

    }


    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    public static String getWeekDateString(int week) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, week);
        return formatDateString(cal.getTime(), "yyyy-MM-dd");
    }

    public static Date getWeekDate(int week) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, week);
        return cal.getTime();
    }


    public static String getYYMMdd(String dateStr) {
        if (TextUtils.isEmpty(dateStr)) return "";
        String pat1 = "yyyy-MM-dd";
        SimpleDateFormat sd = new SimpleDateFormat(pat1);
        Date date = null;
        try {
            date = sd.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sd.format(date);
    }

    public static String Stringweek() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return mWay;
    }

    public static int getWeek() {
        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return 6;
            case 2:
                return 0;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 3;
            case 6:
                return 4;
            case 7:
                return 5;
            default:
                return -1;
        }
    }


    public static String StringweekEg(String mWay) {
        if ("天".equals(mWay)) {
            mWay = "Sunday";
        } else if ("一".equals(mWay)) {
            mWay = "Monday";
        } else if ("二".equals(mWay)) {
            mWay = "Tuesday";
        } else if ("三".equals(mWay)) {
            mWay = "Wednesday";
        } else if ("四".equals(mWay)) {
            mWay = "Thursday";
        } else if ("五".equals(mWay)) {
            mWay = "Friday";
        } else if ("六".equals(mWay)) {
            mWay = "Saturday";
        }
        return mWay;
    }


    /**
     * 两个日期获取时间差(天时分秒)
     *
     * @param fromDate 前的时间
     * @param toDate   后的时间
     * @return
     */
    public static Long[] getTimeMillis(String fromDate, String toDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long day = 0, hour = 0, min = 0, seconds = 0;
        try {
            //前的时间
            Date fd = df.parse(fromDate);
            //后的时间
            Date td = df.parse(toDate);
            //两时间差,精确到毫秒
            long diff = td.getTime() - fd.getTime();
            if (diff <= 0) {
                return null;
            }
            day = diff / 86400000;                         //以天数为单位取整
            hour = diff % 86400000 / 3600000;               //以小时为单位取整
            min = diff % 86400000 % 3600000 / 60000;       //以分钟为单位取整
            seconds = diff % 86400000 % 3600000 % 60000 / 1000;   //以秒为单位取整
            Long[] time = new Long[4];
            time[0] = day;
            time[1] = hour;
            time[2] = min;
            time[3] = seconds;
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 判断给定字符串时间是否为今日(效率不是很高，不过也是一种方法)
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date today = new Date();
        String nowDate = dateFormater2.format(today);
        String timeDate = sdate;
        if (nowDate.equals(timeDate)) {
            b = true;
        }
        return b;
    }

    /**
     * 判断是否为昨天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean isToday1(String day) {
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try {
            date = getDateFormat().parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR);
            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }

    public static SimpleDateFormat getDateFormat() {
        if (null == DateLocal.get()) {
            DateLocal.set(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA));
        }
        return DateLocal.get();
    }

    private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<>();


    private static SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }


    /**
     * 比较时间大小
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isDateOneBigger(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
            if (dt1.getTime() > dt2.getTime()) {
                isBigger = true;
            } else if (dt1.getTime() < dt2.getTime()) {
                isBigger = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isBigger;
    }


}
