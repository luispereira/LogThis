package com.lib.logthis;

import android.app.Application;
import android.os.Environment;

import com.lib.logthisannotations.aspect.Logger;

import java.io.File;

/**
 * @author lpereira on 11/01/2016.
 */
public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init().storeLogs(new File(Environment.getExternalStorageDirectory(), "test"));
    }
}
