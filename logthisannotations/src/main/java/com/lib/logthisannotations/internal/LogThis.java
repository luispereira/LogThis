package com.lib.logthisannotations.internal;

import android.util.Log;

/**
 * @author lpereira on 04/01/2016.
 */
public class LogThis {

    private LogThis() {
    }

    /**
     * Send a debug log message
     *
     * @param tag Source of a log message. It usually identifies the class or activity where the log
     * call occurs.
     * @param message The message you would like logged.
     */
    public static void log(String tag, String message, LoggerLevel loggerLevel) {
        switch (loggerLevel){
            case V:
                Log.v(tag, message);
                break;
            case D:
                Log.d(tag, message);
                break;
            case I:
                Log.i(tag, message);
                break;
            case W:
                Log.w(tag, message);
                break;
            case E:
                Log.e(tag, message);
                break;
            case WTF:
                Log.wtf(tag, message);
                break;
        }
    }
}
