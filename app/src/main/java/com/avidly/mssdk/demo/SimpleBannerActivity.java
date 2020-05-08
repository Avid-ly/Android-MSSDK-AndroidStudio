package com.avidly.mssdk.demo;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.LinearLayout;

import com.ms.sdk.MsBannerAd;
import com.ms.sdk.wrapper.banner.MsBannerAdListener;
import com.ms.sdk.wrapper.banner.MsGameEasyBannerWrapper;
//import com.openup.common.tool.Helper;

public class SimpleBannerActivity extends BaseActivity {
    private static final String TAG = "MSSdk_demo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        MsGameEasyBannerWrapper.getInstance().initGameBannerWithActivity(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MsGameEasyBannerWrapper.getInstance().showBottomBannerAtADPlaceId("sample_banner");
            }
        },1*1000);


        MsGameEasyBannerWrapper.getInstance().addBannerCallbackAtADPlaceId("sample_banner", new MsBannerAdListener() {
            @Override
            public void onClicked() {
                Log.i(TAG, "sample_banner onClicked ");
            }

            @Override
            public void onDisplayed() {
                Log.i(TAG, "sample_banner onDisplayed ");
            }
        });

    }




}
