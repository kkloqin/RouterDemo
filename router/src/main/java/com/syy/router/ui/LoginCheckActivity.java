package com.syy.router.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.syy.common.provider.UserInfoProvider;
import com.syy.router.R;
import com.syy.router.Router;
import com.syy.router.utils.HttpHelper;

import org.apache.commons.lang3.StringUtils;

public class LoginCheckActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_check);

        try {
            String schema = HttpHelper.getAppSchema(this);
            String redirectUrl = schema + ":/" + getIntent().getData().getPath() + "?" + getIntent().getData().getEncodedQuery();
            if (getIntent().getData() != null) {
                if (StringUtils.isEmpty(UserInfoProvider.getInstance().getUserId())) {
                    String url = schema + "://login?redirect=" + Uri.encode(redirectUrl);
                    Router.getInstance().redirect(LoginCheckActivity.this, url);
                } else {
                    Router.getInstance().redirect(LoginCheckActivity.this, redirectUrl);
                }
            }
        } catch (Exception e) {

        }
        finish();
    }
}
