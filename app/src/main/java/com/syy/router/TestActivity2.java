package com.syy.router;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class TestActivity2 extends AppCompatActivity {
    private static final String TAG = Router.TAG + "_TestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_activity2);

        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra("result", "aaaaaa");
                //设置返回数据
                TestActivity2.this.setResult(RESULT_OK, intent);
                //关闭Activity
                TestActivity2.this.finish();
            }
        });

        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.getInstance().redirect(TestActivity2.this, "test1", new HashMap<>());
            }
        });
    }
}
