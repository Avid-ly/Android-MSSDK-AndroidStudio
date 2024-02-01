package com.avidly.mssdk.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ms.sdk.MsBannerAd;
import com.ms.sdk.wrapper.banner.MsBannerAdListener;

public class BannerActivity extends Activity {
    private static final String TAG = "MsSdk_demo";

    LinearLayout banner_container;
    LinearLayout rectangle_container;

    MsBannerAd mBannerAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        mBannerAd = new MsBannerAd(BannerActivity.this, "sample_banner");
        mBannerAd.setBannerAdListener(new MsBannerAdListener() {
            @Override
            public void onClicked() {
                // 此处为广告点击的回调
            }

            @Override
            public void onDisplayed() {
                // 此处为广告显示的回调
            }
        });

        banner_container = (LinearLayout) findViewById(R.id.banner_container);
        banner_container.addView(mBannerAd.getBannerView());
        rectangle_container = (LinearLayout) findViewById(R.id.rectangle_container);

        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BannerActivity.this, "click", Toast.LENGTH_LONG).show();
            }
        });

    }

}
