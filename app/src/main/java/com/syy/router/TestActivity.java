package com.syy.router;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.syy.common.utils.ModelStore;
import com.syy.router.bean.BigBean;

import org.apache.commons.lang3.StringUtils;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = Router.TAG + "_TestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_activity);
        try {
            if (getIntent().getData() != null) {
                String params1 = getIntent().getData().getQueryParameter("params1");
                String params2 = getIntent().getData().getQueryParameter("params2");
                String host = getIntent().getData().getHost();
                Log.d(TAG, "onCreate:  params1 = " + params1 + " params2 = " + params2 + "  host  = " + host);

                String infoIdValue = getIntent().getData().getQueryParameter("info");
                if (StringUtils.isNotEmpty(infoIdValue)) {
                    Integer infoId = Integer.parseInt(infoIdValue);
                    BigBean bigBean = (BigBean) ModelStore.getInstance().pop(infoId);
                    Log.d(TAG, "onCreate: " +bigBean.getName());
                }
            }
        } catch (Exception e) {

        }
    }
}
