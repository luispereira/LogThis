package com.lib.logthis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lib.logthisannotations.annotation.LogThis;
import com.lib.logthisannotations.internal.LoggerLevel;

public class MainActivity extends AppCompatActivity {

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
            }
        });
    }

    @LogThis(LoggerLevel.E)
    public void logThisMethod(String value) {
        // ...
    }

    @LogThis
    public String logThisReturnMethod() {
        return "testing return value";
    }
}
