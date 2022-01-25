package com.syy.router;

import android.app.Application;

import com.syy.common.provider.UserInfoProvider;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            UserInfoProvider.getInstance().init(getApplicationContext());
            Router.getInstance().initialize(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
