package com.lib.logthis;

import android.app.Application;

import com.lib.logthisannotations.Logger;

/**
 * @author lpereira on 11/01/2016.
 */
public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG) {
            Logger.init();
        }
    }
}
