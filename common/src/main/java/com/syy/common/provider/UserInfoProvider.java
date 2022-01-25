package com.syy.common.provider;

import android.content.Context;

import com.syy.common.utils.BasePreference;

public class UserInfoProvider {
    private static UserInfoProvider mUserInfoProvider;
    private Context context;
    private String userId;

    private UserInfoProvider() {
    }

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public static synchronized UserInfoProvider getInstance() {
        if (mUserInfoProvider == null) {
            mUserInfoProvider = new UserInfoProvider();
        }
        return mUserInfoProvider;
    }
    public void setUserId(String userId) {
        this.userId = userId;
        BasePreference.getInstance().putString(context, "userId", userId);
    }

    public String getUserId() {
        return BasePreference.getInstance().getString(context, "userId", "");
    }

    public void clearUserInfo() {
        BasePreference.getInstance().putString(context, "userId", "");
        this.userId = "";
    }
}
