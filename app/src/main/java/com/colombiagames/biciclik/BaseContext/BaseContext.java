package com.colombiagames.biciclik.BaseContext;

import android.app.Application;
import android.content.Context;

public class BaseContext extends Application {

    private static Context context;

    public static Context getContext() {
        return BaseContext.context;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        BaseContext.context = getApplicationContext();
    }

}
