package com.johnmbiya.gads.ui;

import android.os.Bundle;
import android.os.Handler;

import com.johnmbiya.gads.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreenView();
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            MainActivity.start(SplashActivity.this);
            finish();
        }, 1500);
    }


}