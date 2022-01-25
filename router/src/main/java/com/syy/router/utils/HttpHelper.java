package com.syy.router.utils;

import android.content.Context;

import com.syy.common.provider.AppChannelProvider;

import org.apache.commons.lang3.StringUtils;

public class HttpHelper {

    public static String getAppSchema(Context context) {
        String alias;
        if (AppChannelProvider.getInstance().isRouterApp(context)) {
            alias = "router";
        } else {
            alias = "router";
        }
        return alias;
    }

    public static String getNotNullParam(String param) {
        return StringUtils.isEmpty(param) ? "" : param;
    }
}
