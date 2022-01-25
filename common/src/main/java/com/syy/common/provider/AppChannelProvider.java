package com.syy.common.provider;

import android.content.Context;

public class AppChannelProvider {
    private static AppChannelProvider instance;

    private AppChannelProvider() {
    }

    public static AppChannelProvider getInstance() {
        if (instance == null) {
            instance = new AppChannelProvider();
        }
        return instance;
    }

    public boolean isRouterApp(Context context) {
        return context != null && "com.syy.router".equalsIgnoreCase(context.getPackageName());
    }
}