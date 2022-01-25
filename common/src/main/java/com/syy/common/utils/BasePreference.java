package com.syy.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class BasePreference {

    private static BasePreference basePreference;

    protected BasePreference() {
    }

    public synchronized static BasePreference getInstance() {
        if (basePreference == null) {
            basePreference = new BasePreference();
        }
        return basePreference;
    }

    // default preference name is application package name.
    // override it if needed.
    protected String getName() {
        return "com.syy";
    }

    public void putString(Context context, String key, String value) {
        SharedPreferences sharedPreference = context.getSharedPreferences(getName(), Context.MODE_PRIVATE);
        sharedPreference.edit().putString(key, value).apply();
    }

    public String getString(Context context, String key, String defValue) {
        SharedPreferences sharedPreference = context.getSharedPreferences(getName(), Context.MODE_PRIVATE);
        return sharedPreference.getString(key, defValue);
    }

    public void putInt(Context context, String key, int value) {
        SharedPreferences sharedPreference = context.getSharedPreferences(getName(), Context.MODE_PRIVATE);
        sharedPreference.edit().putInt(key, value).apply();
    }

    public int getInt(Context context, String key, int defValue) {
        SharedPreferences sharedPreference = context.getSharedPreferences(getName(), Context.MODE_PRIVATE);
        return sharedPreference.getInt(key, defValue);
    }

    public void putLong(Context context, String key, Long value) {
        SharedPreferences sharedPreference = context.getSharedPreferences(getName(), Context.MODE_PRIVATE);
        sharedPreference.edit().putLong(key, value).apply();
    }

    public long getLong(Context context, String key, long defValue) {
        SharedPreferences sharedPreference = context.getSharedPreferences(getName(), Context.MODE_PRIVATE);
        return sharedPreference.getLong(key, defValue);
    }

    public void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreference = context.getSharedPreferences(getName(), Context.MODE_PRIVATE);
        sharedPreference.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(Context context, String key, boolean defValue) {
        try {
            SharedPreferences sharedPreference = context.getSharedPreferences(getName(), Context.MODE_PRIVATE);
            return sharedPreference.getBoolean(key, defValue);
        } catch (Exception e) {
            return defValue;
        }
    }

    public void deleteValue(Context context, String key) {
        SharedPreferences sharedPreference = context.getSharedPreferences(getName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.remove(key);
        editor.apply();
    }

    public void clearAll(Context context) {
        SharedPreferences sharedPreference = context.getSharedPreferences(getName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.clear();
        editor.apply();
    }
}
