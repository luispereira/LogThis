package com.lib.logthisannotations.internal;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author lpereira on 04/01/2016.
 */
public class LogThis {
    private static final String LOG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(LOG_DATE_FORMAT);
    private static FileOutputStream fos = null;

    public LogThis(String path) {
        if (!TextUtils.isEmpty(path)) {
            try {
                File logFile;
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdirs();

                    logFile = new File(file, "LogThis.txt");
                    file.createNewFile();
                }else{
                    logFile = new File(file, "LogThis.txt");
                }

                fos = new FileOutputStream(logFile, true);
            } catch (IOException e) {
                //ignored directory should exist
            }
        }
    }

    /**
     * Send a debug log message
     *  @param tag Source of a log message. It usually identifies the class or activity where the log
     * call occurs.
     * @param message The message you would like logged.
     * @param write if the log should be written to the storage
     */
    public void log(String tag, String message, LoggerLevel loggerLevel, boolean write) {
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

        if(fos != null && write){
            writeLog(tag, message);
        }
    }

    private void writeLog( String tag, String message) {
        // log to file
        try {
            if (fos != null) {
                fos.write(("[" + formatter.format(Calendar.getInstance().getTime()) + "] " + tag + ": " + message + "\n").getBytes());
            }
        } catch (IOException e) {
            Log.e(tag, "Failed to write to log file. Info: " + e.getCause());
        }
    }
}
