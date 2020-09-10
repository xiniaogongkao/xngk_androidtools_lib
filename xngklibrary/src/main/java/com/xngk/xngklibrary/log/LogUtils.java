package com.xngk.xngklibrary.log;

import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * Log工具类
 * 通过设置打印等级决定是否要打印Log
 */
public class LogUtils {
    private static final String LOG_TAG = "XNServant";
    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_WARNING = 3;
    public static final int LEVEL_ERROR = 4;

    public static final int LEVEL_NO_LOG = 5;

    private static int mLevel = LEVEL_VERBOSE;


    public static void init() {
        Logger.init(LOG_TAG);
    }

    public static void setLogLevel(int level) {
        mLevel = level;
    }

    public static void setIsLog(boolean isLog) {
        mLevel = isLog ? LEVEL_VERBOSE : LEVEL_NO_LOG;
    }

    public static void setIsLog(String isLog) {
        mLevel = "true".equals(isLog) ? LEVEL_VERBOSE : LEVEL_NO_LOG;
    }

    public static void v(String msg) {
        if (mLevel > LEVEL_VERBOSE) {
            return;
        }
        Logger.v(msg);
    }

    public static void v(String msg, Throwable throwable) {
        if (mLevel > LEVEL_VERBOSE) {
            return;
        }
        Logger.v(msg, throwable);
    }

    public static void debug(String msg) {
        Log.v("XNServant", msg);
    }

    public static void d(String msg) {
        if (msg == null)
            return;
        if (mLevel > LEVEL_DEBUG) {
            return;
        }
        Logger.d(msg);
    }

    public static void d(String msg, Throwable throwable) {
        if (mLevel > LEVEL_DEBUG) {
            return;
        }
        Logger.d(msg, throwable);
    }

    public static void i(String msg) {
        if (mLevel > LEVEL_INFO) {
            return;
        }
        Logger.i(msg);
    }

    public static void i(String msg, Throwable throwable) {
        if (mLevel > LEVEL_INFO) {
            return;
        }
        Logger.i(msg, throwable);
    }

    public static void w(String msg) {
        if (mLevel > LEVEL_WARNING) {
            return;
        }
        Logger.w(msg);
    }

    public static void w(String msg, Throwable throwable) {
        if (mLevel > LEVEL_WARNING) {
            return;
        }
        Logger.w(msg, throwable);
    }

    public static void e(String tagStr) {
        if (mLevel > LEVEL_ERROR) {
            return;
        }

        if (tagStr.length() > 4000) {
            for (int i = 0; i < tagStr.length(); i += 4000) {
                if (i + 4000 < tagStr.length())
                    llog("", tagStr.substring(i, i + 4000));
                else
                    llog("", tagStr.substring(i, tagStr.length()));
            }
        } else {
            llog(LOG_TAG, tagStr);
        }

    }

    public static void e(String msg, Throwable throwable) {
        if (mLevel > LEVEL_ERROR) {
            return;
        }

        Logger.e(msg, throwable);
    }

    /**
     * 执行打印方法
     *
     * @param tagStr
     * @param obj
     */
    public static void llog(String tagStr, Object obj) {

        String msg;

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        //获取打印位置的下标
        int index = 4;
        String className = stackTrace[index].getFileName();
        String methodName = stackTrace[index].getMethodName();
        int lineNumber = stackTrace[index].getLineNumber();

        String tag = (tagStr == null ? className : tagStr);
        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ (").append(className).append(":").append(lineNumber).append(")#").append(methodName).append(" ] ");

        stringBuilder.append("[]");

        if (obj == null) {
            msg = "Log with null Object";
        } else {
            msg = obj.toString();
        }
        if (msg != null) {
            stringBuilder.append(msg);
        }

        String logStr = stringBuilder.toString();

        Log.e(tag, logStr);
    }
}
