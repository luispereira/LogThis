package com.lib.logthisannotations;

/**
 * @author lpereira on 11/01/2016.
 */
public class Logger {
    private static Logger sInstance;
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
    public static void init(){
        sInstance = new Logger();
    }

    public boolean isLoggerEnabled() {
        return mLogger;
    }
}
