package com.avidly.mssdk.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ms.sdk.MsInterstitialAd;
import com.ms.sdk.wrapper.interstitial.MsInterstitialAdListener;
import com.ms.sdk.wrapper.interstitial.MsInterstitialLoadCallback;


public class InterstitialActivity extends Activity {
    private static final String TAG = "MSSdk_demo";
    MsInterstitialAd mInterstitialAd;
    Button mButtonShow;
    String placeidAA = "sample_inter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
        mButtonShow = (Button) findViewById(R.id.buttonShowEEE);
        mInterstitialAd = new MsInterstitialAd(InterstitialActivity.this, placeidAA);
        mInterstitialAd.setLoadCallBack(new MyLoadCallBack());

        mInterstitialAd.setInterstitialAdListener(new MsInterstitialAdListener() {
            @Override
            public void onClicked() {
                Log.d(TAG, "onClicked: ");
            }

            @Override
            public void onClosed() {
                Log.d(TAG, "onClosed: ");
            }

            @Override
            public void onDisplayed() {
                Log.d(TAG, "onDisplayed: ");
            }

            @Override
            public void onShowFailed(String reason) {
                Log.d(TAG, "onShowFailed: ");

            }
        });

        mButtonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd != null && mInterstitialAd.isReady()) {
                    mInterstitialAd.show();
                } else {
                    Toast.makeText(getApplicationContext(), " inter ads not ready", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class MyLoadCallBack implements MsInterstitialLoadCallback {
        @Override
        public void onLoadFailed(String placement) {
            Log.i(TAG, "InterstitialAd " + placement + " onLoadFailed:");
            Toast.makeText(InterstitialActivity.this, placement + " loadAd fail", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onLoadSuccessed(String placement) {
            Log.i(TAG, "InterstitialAd " + placement + " onLoadSuccessed:");
            Toast.makeText(InterstitialActivity.this, placement + " loadAd success", Toast.LENGTH_SHORT).show();
        }
    }
}
