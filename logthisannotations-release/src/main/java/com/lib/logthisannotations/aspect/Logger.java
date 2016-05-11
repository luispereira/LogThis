package com.lib.logthisannotations.aspect;

import java.io.File;

/**
 * @author lpereira on 11/01/2016.
 */
public class Logger {
    public static Logger init(){
        return new Logger();
    }

    public void storeLogs(File folder){
        //empty for release build
    }
}
