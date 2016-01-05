package com.lib.mylibrary.internal;

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
    public static void log(String tag, String message) {
        Log.d(tag, message);
    }
}
