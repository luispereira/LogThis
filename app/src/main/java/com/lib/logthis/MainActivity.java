package com.lib.logthis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lib.logthisannotations.annotation.LogThis;
import com.lib.logthisannotations.annotation.LogThisClassFields;
import com.lib.logthisannotations.internal.LoggerLevel;

@LogThisClassFields(LoggerLevel.W)
public class MainActivity extends AppCompatActivity {

    @LogThis
    public String mSampleField;

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = "test";
                logThisMethod(value);
                logThisReturnMethod();

                mSampleField = "supppp " + (i++);
            }
        });
    }

//    @LogThis(LoggerLevel.E)
    public void logThisMethod(String value) {
        // ...
    }

//    @LogThis
    public String logThisReturnMethod() {
        return "testing return value";
    }
}
