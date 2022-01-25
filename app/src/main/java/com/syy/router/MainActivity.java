package com.syy.router;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;

import com.syy.common.provider.UserInfoProvider;
import com.syy.common.utils.ModelStore;
import com.syy.router.bean.BigBean;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        findViewById(R.id.btn_1).setOnClickListener(view -> {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("params1", "1");
            params.put("params2", "2");
            Router.getInstance().redirect(MainActivity.this, "test", params);
        });

        findViewById(R.id.btn_2).setOnClickListener(view -> {
            Router.getInstance().redirect(MainActivity.this, "router://test?params1=1&params2=2");
        });

        findViewById(R.id.btn_3).setOnClickListener(view -> {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("params1", "1");
            params.put("params2", "2");
            Router.getInstance().redirect(MainActivity.this, "testAlias", params);
        });

        findViewById(R.id.btn_4).setOnClickListener(view -> {
            UserInfoProvider.getInstance().setUserId("1111111");
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("params1", "1");
            params.put("params2", "2");
            Router.getInstance().redirect(MainActivity.this, "lc/test", params);
        });

        findViewById(R.id.btn_5).setOnClickListener(view -> {
            UserInfoProvider.getInstance().clearUserInfo();
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("params1", "1");
            params.put("params2", "2");
            Router.getInstance().redirect(MainActivity.this, "lc/test", params);
        });

        findViewById(R.id.btn_6).setOnClickListener(view -> {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("params1", "1");
            params.put("params2", "2");
            BigBean bigBean = new BigBean();
            bigBean.setName("name");
            params.put("info", String.valueOf(ModelStore.getInstance().put(bigBean)));
            Router.getInstance().redirect(MainActivity.this, "test", params);
        });
    }
}