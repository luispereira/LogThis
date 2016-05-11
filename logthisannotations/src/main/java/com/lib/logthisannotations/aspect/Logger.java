package com.lib.logthisannotations.aspect;

import com.lib.logthisannotations.internal.LogThis;

import java.io.File;

/**
 * @author lpereira on 11/01/2016.
 */
public class Logger {
    private static Logger sInstance;

    private LogThis mLoggerFolder;
    private final boolean mLogger;

    protected static Logger getInstance(){
        if (sInstance == null) {
            return null;
        }
        return sInstance;
    }

    private Logger() {
        mLoggerFolder = new LogThis(null);
        mLogger = true;
    }

    /**
     * Initialize the Logger to make it enable
     */
    public static Logger init(){
        sInstance = new Logger();
        return sInstance;
    }

    /**
     * Enables the SDK to start writing logs into a desired folder with the name of LogThis.txt
     * Keep in mind that the SDK does not enable any Storage permission, so the application must declare it and ask for it on Android M and above
     * @param folder the desired folder to save the file
     */
    public void storeLogs(File folder){
        String path = folder.getAbsolutePath();
        mLoggerFolder = new LogThis(path);
    }

    protected boolean isLoggerEnabled() {
        return mLogger;
    }

    protected LogThis getLoggerInstance() {
        return mLoggerFolder;
    }
}
