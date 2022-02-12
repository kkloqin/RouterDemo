package com.syy.router;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.syy.router.bean.ModuleInfo;
import com.syy.router.provider.ModuleInfoProvider;
import com.syy.router.utils.HttpHelper;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {
    public static final String TAG = "Router";

    private static Router mRouter;

    private Router() {

    }

    public synchronized static Router getInstance() {
        if (mRouter == null) {
            mRouter = new Router();
        }
        return mRouter;
    }

    public void initialize(Context context) throws Exception {
        String product = "";
        Bundle appMeta = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData;
        if (appMeta != null) {
            product = appMeta.getString("productName");
        }
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        for (ActivityInfo activityInfo : packageInfo.activities) {
            ActivityInfo metaInfo = context.getPackageManager().getActivityInfo(new ComponentName(activityInfo.packageName, activityInfo.name),
                    PackageManager.GET_META_DATA);
            if (metaInfo.metaData != null) {
                String moduleName = metaInfo.metaData.getString("moduleName");
                String target = metaInfo.metaData.getString("target");
                if (StringUtils.isEmpty(moduleName)) {
                    continue;
                }
                if (StringUtils.isNotEmpty(target)) {
                    if (StringUtils.isNotEmpty(product)) {
                        if (target.contains("!")) {
                            if (target.replace("!", "").equals(product)) {
                                continue;
                            }
                        } else {
                            List<String> targets = Arrays.asList(target.split("\\|"));
                            if (!targets.contains(product)) {
                                continue;
                            }
                        }
                    } else {
                        continue;
                    }
                }
                ModuleInfo moduleInfo = new ModuleInfo();
                moduleInfo.className = activityInfo.name;
                List<String> moduleNames = Arrays.asList(moduleName.split("\\|"));
                for (String moduleKey : moduleNames) {
                    if (!StringUtils.isEmpty(moduleKey)) {
                        if (ModuleInfoProvider.getInstance().isModuleExist(moduleKey)) {
                            throw new Exception("module name: " + moduleKey + " duplicated! Rename or add target is optional.");
                        }
                        ModuleInfoProvider.getInstance().addModuleInfo(moduleKey.trim(), moduleInfo);
                    }
                }
            }
        }
    }

    private void launchModule(Context context, String uriString, Bundle bundle, List<Integer> flags, int requestCode, Callback callback) {
        Log.d(TAG, "launchModule: uriString = " + uriString);
        try {
            if (TextUtils.isEmpty(uriString)) {
                return;
            }

            Uri uri = Uri.parse(uriString);
            String schema = uri.getScheme();
            String moduleName = uri.getHost();
            ModuleInfo targetModule = ModuleInfoProvider.getInstance().getModuleInfo(moduleName);
            if (targetModule != null) {
                Log.w("跳转的类","=="+targetModule.className);
                Intent intent = new Intent();
                intent.setClassName(context.getPackageName(), targetModule.className);
                intent.setData(uri);
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                if (flags != null && !flags.isEmpty()) {
                    for (Integer flag : flags) {
                        intent.addFlags(flag);
                    }
                }
                if (callback == null) {
                    context.startActivity(intent);
                } else {
                    final RouterCallbackFragment callbackFragment = RouterCallbackFragment.getInstance((Activity) context);
                    callbackFragment.setCallback((requestCode1, resultCode, data) -> {
                        callback.onActivityResult(requestCode1, resultCode ,data);
                    });
                    callbackFragment.startActivityForResult(intent, requestCode);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "launchModule: exception=" + e.toString());
        }

    }

    @Deprecated
    public void redirect(Context context, String uriString) {
        launchModule(context, uriString, null, null, -1 , null);
    }

    @Deprecated
    public void redirect(Context context, String uriString, Bundle bundle) {
        launchModule(context, uriString, bundle, null, -1 , null);
    }

    public void redirect(Context context, String moduleName, Map<String, String> params) {
        redirect(context, moduleName, params, null, null, -1, null);
    }

    public void redirect(Context context, String moduleName, Map<String, String> params, int flag) {
        List<Integer> flags = new ArrayList<Integer>();
        flags.add(flag);
        redirect(context, moduleName, params, null, flags,-1, null);
    }

    public void redirect(Context context, String moduleName, Map<String, String> params, List<Integer> flags) {
        redirect(context, moduleName, params, null, flags,-1, null);
    }

    public void redirect(Context context, String moduleName, Map<String, String> params, Bundle bundle, List<Integer> flags,int requestCode, Callback callback) {
        String url;
        Uri uri = Uri.parse(HttpHelper.getAppSchema(context) + "://" + moduleName);
        if (params != null) {
            Uri.Builder builder = uri.buildUpon();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.appendQueryParameter(entry.getKey(), HttpHelper.getNotNullParam(entry.getValue()));
            }
            url = builder.build().toString();
        } else {
            url = uri.toString();
        }
        launchModule(context, url, bundle, flags, requestCode, callback);
    }

    public void redirectForResult(Context context, String moduleName, HashMap<String, String> params,int requestCode, Callback callback) {
        redirect(context, moduleName, params, null, null, requestCode, callback);
    }

    public interface Callback {
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
