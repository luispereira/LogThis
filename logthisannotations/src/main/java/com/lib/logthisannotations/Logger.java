package com.lib.logthisannotations;

import java.io.File;

/**
 * @author lpereira on 11/01/2016.
 */
public class Logger {
    private static Logger sInstance;

    private boolean mStoreLogs;
    private final boolean mLogger;

    public static Logger getInstance(){
        if (sInstance == null) {
           return null;
        }
        return sInstance;
    }

    private Logger() {
        mLogger = true;
    }

    /**
     * Initialize the Logger to make it enable
     */
    public static Logger init(){
        sInstance = new Logger();
        return sInstance;
    }

    public void storeLogs(File folder){
        mStoreLogs = true;
    }

    public boolean isLoggerEnabled() {
        return mLogger;
    }

    public boolean isStoreLogs() {
        return mStoreLogs;
    }
}
