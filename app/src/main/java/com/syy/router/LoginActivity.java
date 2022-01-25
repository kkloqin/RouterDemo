package com.syy.router;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.syy.common.R;
import com.syy.router.ui.LoginCheckActivity;

public class LoginActivity extends AppCompatActivity {
    private String redirectUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        if (getIntent().getData() != null) {
            redirectUrl = getIntent().getData().getQueryParameter("redirect");
        }
        new Handler().postDelayed(() -> {
            Toast.makeText(LoginActivity.this, "已经登入完成", Toast.LENGTH_SHORT).show();
            redirectNextPage();
            finish();
        }, 4000);
    }

    private void redirectNextPage(){
        Router.getInstance().redirect(LoginActivity.this, redirectUrl);
    }
}
