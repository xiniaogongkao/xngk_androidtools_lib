package com.xngk.xngklibrary.date;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class TimeUtils {
    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年

    public static Timer mTimer = null;
    private static TimerTask mTimerTask = null;
    public static Handler mHandler = null;
    public static int count = 0;
    public static boolean isPause = false;
    private static int delay = 1000; //1s
    private static int period = 1000; //1s
    public static final int UPDATE_TEXTVIEW = 0;

    public static void startTimer() {
        if (mTimer == null) {
            mTimer = new Timer();
        }

        if (mTimerTask == null) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    sendMessage(UPDATE_TEXTVIEW);
                    do {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                    } while (isPause);

                    count++;
                }
            };
        }

        if (mTimer != null && mTimerTask != null)
            try {
                mTimer.schedule(mTimerTask, delay, period);
            } catch (IllegalStateException e) {

            }


    }

    public static void sendMessage(int id) {
        if (mHandler != null) {
            Message message = Message.obtain(mHandler, id);
            mHandler.sendMessage(message);
        }
    }

    /**
     * 在onDestroy调用这个方法 否则会异常
     */
    public static void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
        count = 0;
    }


    public static String secToTime(int time) {
        if (time >= 999 * 60) {
            return "999:00";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Integer hour = time / 60;
        Integer minute = time % 60;
        if (hour < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(hour);

        stringBuilder.append(":");
        if (minute < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(minute);
        return stringBuilder.toString();
    }


    /**
     * 返回文字描述的日期
     *
     * @param date
     * @return
     */
    public static String getTimeFormatText(Date date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    public static String getTimeFormatText(String date) {
        return getTimeFormatText(DateUtils.str2Date(date));
    }

    public static String formatTime(long time) {
        return SystemUtils.formatTime("mm:ss", time);
    }



    public static String formatTimems(long time) {
        Date date = new Date(time);
        return DateUtils.date2Str(date, "yyyy.MM.dd HH:mm");
    }

    public static String formatTimemHH(long time) {
        Date date = new Date(time);
        return DateUtils.date2Str(date, "HH:mm");
    }

    //根据秒数转化为时分秒   00:00:00
    public static String getTime(int second) {
        if (second < 10) {
            return "00:0" + second;
        }
        if (second < 60) {
            return "00:" + second;
        }
        if (second < 3600) {
            int minute = second / 60;
            second = second - minute * 60;
            if (minute < 10) {
                if (second < 10) {
                    return "0" + minute + ":0" + second;
                }
                return "0" + minute + ":" + second;
            }
            if (second < 10) {
                return minute + ":0" + second;
            }
            return minute + ":" + second;
        }
        int hour = second / 3600;
        int minute = (second - hour * 3600) / 60;
        second = second - hour * 3600 - minute * 60;
        if (hour < 10) {
            if (minute < 10) {
                if (second < 10) {
                    return "0" + hour + ":0" + minute + ":0" + second;
                }
                return "0" + hour + ":0" + minute + ":" + second;
            }
            if (second < 10) {
                return "0" + hour + minute + ":0" + second;
            }
            return "0" + hour + minute + ":" + second;
        }
        if (minute < 10) {
            if (second < 10) {
                return hour + ":0" + minute + ":0" + second;
            }
            return hour + ":0" + minute + ":" + second;
        }
        if (second < 10) {
            return hour + minute + ":0" + second;
        }
        return hour + minute + ":" + second;
    }

    //根据秒数转化为时分秒   00时00分00秒
    public static String calSFM(int second) {
        int h = 0;
        int d = 0;
        int s = 0;
        int temp = second % 3600;
        if (second > 3600) {
            h = second / 3600;
            if (temp != 0) {
                if (temp > 60) {
                    d = temp / 60;
                    if (temp % 60 != 0) {
                        s = temp % 60;
                    }
                } else {
                    s = temp;
                }
            }
        } else {
            d = second / 60;
            if (second % 60 != 0) {
                s = second % 60;
            }
        }
        if (h <= 0) {
            return d + "分" + s + "秒";
        } else return h + "时" + d + "分" + s + "秒";
    }

    /**
     * 秒转分 秒
     *
     * @param duration
     * @return
     */
    public static String timeParse(long duration) {
        String time = "";
        long minute = duration / 60;
        long seconds = duration % 60;
        long second = Math.round((float) seconds / 1);
        if (minute < 10) {
            time += "0";
        }
        time += minute + "分";
        if (second < 10) {
            time += "0";
        }
        time += second + "秒";
        return time;
    }


    /**
     * 秒转分 秒
     *
     * @param duration
     * @return
     */
    public static String timeParse1(long duration) {
        String time = "";
        long minute = duration / 60;
        long seconds = duration % 60;
        long second = Math.round((float) seconds / 1);
        if (minute < 10) {
            time += "";
        }
        time += minute + ".";
        if (second < 10) {
            time += "";
        }
        time += second;
        return time;
    }

    /**
     * 秒转 分 秒
     * 2'48''
     *
     * @param duration
     * @return
     */
    public static String timeParseKF(long duration) {
        String time = "";
        long minute = duration / 60;
        long seconds = duration % 60;
        long second = Math.round((float) seconds / 1);
        if (minute <= 0) {
            time += "";
        } else
            time += minute + "'";
        if (second <= 0) {
            time += "";
        } else
            time += second + "''";
        return time;
    }


    public static String formatTimeS(long seconds) {
        int temp = 0;
        StringBuffer sb = new StringBuffer();
        if (seconds > 3600) {
            temp = (int) (seconds / 3600);
            sb.append((seconds / 3600) < 10 ? temp + "个小时" : temp + "个小时");
            temp = (int) (seconds % 3600 / 60);
            changeSeconds(seconds, temp, sb);
        } else {
            temp = (int) (seconds % 3600 / 60);
            changeSeconds(seconds, temp, sb);
        }
        return sb.toString();
    }

    private static void changeSeconds(long seconds, int temp, StringBuffer sb) {
        sb.append((temp < 10) ? temp + "分钟" : "" + temp + "分钟");
//        temp = (int) (seconds % 3600 % 60);
//        sb.append((temp < 10) ? "0" + temp : "" + temp);
    }

    public static String FormatTimes(String time) {
        if (TextUtils.isEmpty(time)) return "";
        if (time.length() >= 10) {
            return time.substring(0, 10).replaceAll("-", ".");
        }
        return "";
    }

    public static boolean comTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date ten = null;
        Date now = null;
        try {
            ten = format.parse(time);
            now = new Date();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return !ten.before(now);

    }

    public static boolean comTime(String time, String time2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date ten = null;
        Date ten1 = null;
        try {
            ten = format.parse(time);
            ten1 = format.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return !ten.before(ten1);

    }

    /**
     * 由秒数得到日期几天几小时几分几秒
     *
     * @param mss 秒数
     * @return
     */
    public static String parseTimeSeconds(long mss) {
        String DateTimes;
        if (mss <= 0) {
            DateTimes = "0" + "天" + "0" + "时" + "0" + "分";

        } else {
            String days = mss / (60 * 60 * 24) < 10 ? "" + mss / (60 * 60 * 24) : mss / (60 * 60 * 24) + "";
            String hours = (mss % (60 * 60 * 24)) / (60 * 60) < 10 ? "" + (mss % (60 * 60 * 24)) / (60 * 60) : (mss % (60 * 60 * 24)) / (60 * 60) + "";
            String minutes = (mss % (60 * 60)) / 60 < 10 ? "" + (mss % (60 * 60)) / 60 : (mss % (60 * 60)) / 60 + "";
            String seconds = mss % 60 < 10 ? "" + mss % 60 : mss % 60 + "";

//        if (days > 0) {
//            DateTimes = days + "天" + hours + "时" + minutes + "分"
//                    + seconds + "秒";
//        } else if (hours > 0) {
//            DateTimes = hours + "时" + minutes + "分"
//                    + seconds + "秒";
//        } else if (minutes > 0) {
//            DateTimes = minutes + "分"
//                    + seconds + "秒";
//        } else {
//            DateTimes = seconds + "秒";
//        }

            days = "0".equals(days) ? "" : days + "天";
            hours = "0".equals(hours) ? "0时" : hours + "时";
            minutes = "0".equals(minutes) ? "" : minutes + "分";

            DateTimes = days + hours + minutes;

        }
        return DateTimes;
    }

    public static String parseTimeSeconds1(long mss) {
        String DateTimes = null;
        long days = mss / (60 * 60 * 24);
        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % (60 * 60)) / 60;
        long seconds = mss % 60;
//        if (days > 0) {
//            DateTimes = days + "-" + hours + "-" + minutes + "-"
//                    + seconds;
//        } else if (hours > 0) {
//            DateTimes = 0 + "-" + hours + "-" + minutes + "-"
//                    + seconds;
//        } else if (minutes > 0) {
//            DateTimes = 0 + "-" + 0 + "-" + minutes + "-"
//                    + seconds;
//        } else {
//            DateTimes = 0 + "-" + 0 + "-" + 0 + "-"
//                    + seconds;
//        }
        DateTimes = days + "-" + hours + "-" + minutes + "-"
                + seconds;
        return DateTimes;
    }

    /**
     * 由秒数得到日期几天几小时几分几秒
     *
     * @param mss 秒数
     * @return
     */
    public static String parseTimeSeconds3(long mss) {
        String DateTimes = null;
        long days = mss / (60 * 60 * 24);
        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % (60 * 60)) / 60;
        long seconds = mss % 60;
        if (days > 0) {
            DateTimes = days + "天" + hours + "时" + minutes + "分";
        } else if (hours > 0) {
            DateTimes = hours + "时" + minutes + "分";
        } else if (minutes > 0) {
            DateTimes = minutes + "分";
        } else {
            DateTimes = "0";
        }
        return DateTimes;
    }


    public static String parseTimeSeconds2(int mss) {
        String DateTimes = null;
        long days = mss / (60 * 60 * 24);
        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % (60 * 60)) / 60;
        long seconds = mss % 60;
        if (days > 0) {
            DateTimes = days + "-" + hours + "-" + minutes + "-" + seconds;
        } else if (hours > 0) {
            DateTimes = 0 + "-" + hours + "-" + minutes + "-" + seconds;
        } else if (minutes > 0) {
            DateTimes = minutes + ":" + seconds;
        } else {
            DateTimes = "00:" + seconds;
        }
        return DateTimes;
    }

    /**
     * 时间戳转换成字符窜
     *
     * @param time
     * @param format 格式
     * @return
     */
    public static String timeStamp2Date(String time, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdr = new SimpleDateFormat(format);
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * 秒转时间格式
     *
     * @param second
     * @return
     */
    public static String getTimeS(int second) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH时mm分ss秒");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String hms = formatter.format(second);
        return hms;
    }


}

