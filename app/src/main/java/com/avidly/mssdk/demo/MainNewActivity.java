package com.avidly.mssdk.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aly.sdk.ALYAnalysis;
import com.ms.sdk.MsInterstitialAd;
import com.ms.sdk.MsRewardVideoAd;
import com.ms.sdk.MsSDK;
import com.ms.sdk.listener.MsSdkInitializationListener;
import com.ms.sdk.wrapper.banner.MsBannerAdListener;
import com.ms.sdk.wrapper.banner.MsGameEasyBannerWrapper;
import com.ms.sdk.wrapper.interstitial.MsInterstitialAdListener;
import com.ms.sdk.wrapper.interstitial.MsInterstitialLoadCallback;
import com.ms.sdk.wrapper.video.MsRewardVideoAdListener;
import com.ms.sdk.wrapper.video.MsRewardVideoLoadCallback;

public class MainNewActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "MsSDK-demo";
    MsInterstitialAd mInterstitialAd;
    MsRewardVideoAd mVideoAd;
    private Button btnInter, btnRew, btnText;
    private TextView textView;
    private String pid = "999999", gameId = "32408";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);

        btnInter = findViewById(R.id.btn_interAd);
        btnInter.setOnClickListener(this);
        btnRew = findViewById(R.id.btn_rewardedAd);
        btnRew.setOnClickListener(this);
        btnText = findViewById(R.id.btn_test);
        btnText.setOnClickListener(this);
        textView = findViewById(R.id.txt_con);


        ALYAnalysis.init(getApplicationContext(), pid, gameId, new ALYAnalysis.TasdkinitializdListener() {
            @Override
            public void onSuccess(String userid) {
                Log.i(TAG, "init success userId is   " + userid);
            }

            @Override
            public void onFail(String errorMsg) {
                Log.i(TAG, "init error  " + errorMsg);
            }
        });


        MsSDK.setDebuggable(true);
        MsSDK.onCreate(this);
        // 同意GDPR
//        MsSDK.grantConsent(this);

        MsSDK.init(this, new MsSdkInitializationListener() {
            @Override
            public void onInitializationSuccess() {
                Log.d(TAG, "onInitializationSuccess: 初始化成功");
                textView.setText("mssdk init success");
                MsGameEasyBannerWrapper.getInstance().initGameBannerWithActivity(MainNewActivity.this);
                MsGameEasyBannerWrapper.getInstance().addBannerCallbackAtADPlaceId("banner_aaa", new MsBannerAdListener() {
                    @Override
                    public void onClicked() {
                        Log.d(TAG, "onClicked: ");
                    }

                    @Override
                    public void onDisplayed() {
                        Log.d(TAG, "onDisplayed: ");


                    }
                });
                (new Handler(Looper.getMainLooper())).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MsGameEasyBannerWrapper.getInstance().showBottomBannerAtADPlaceId("banner_aaa");
                    }
                }, 200);

                mInterstitialAd = new MsInterstitialAd(MainNewActivity.this, "ca-app-pub-2152232918216707/4730706409");
                final MsInterstitialLoadCallback callback = new MsInterstitialLoadCallback() {
                    @Override
                    public void onLoadFailed(String s) {
                        Log.d(TAG, "onLoadFailed:MsInterstitialLoadCallback " + s);
                    }

                    @Override
                    public void onLoadSuccessed(String s) {
                        Log.d(TAG, "onLoadSuccessed: MsInterstitialLoadCallback" + s);

                    }
                };
                mInterstitialAd.setLoadCallBack(callback);
                mVideoAd = MsRewardVideoAd.getInstance(MainNewActivity.this);

            }

            @Override
            public void onInitializationFail(String reason) {
                Log.d(TAG, "onInitializationFail: 初始化失败" + reason);
                textView.setText("mssdk init Fail: " + reason);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_interAd:

                //回调
                mInterstitialAd.setInterstitialAdListener(new MsInterstitialAdListener() {
                    @Override
                    public void onClicked() {
                        Log.d(TAG, "onClicked: mInterstitialAd");
                    }

                    @Override
                    public void onClosed() {
                        Log.d(TAG, "onClosed: mInterstitialAd");
                    }

                    @Override
                    public void onDisplayed() {
                        Log.d(TAG, "onDisplayed: mInterstitialAd 展示");

                    }

                    @Override
                    public void onShowFailed(String s) {
                        Log.d(TAG, "\nonShowFailed: " + s);
                        textView.setText("onShowFailed" + s);
                    }
                });


                //展示
                if (mInterstitialAd != null && mInterstitialAd.isReady()) {
                    mInterstitialAd.show();
                } else {
                    Log.d(TAG, "onClick: interstitial ad not ready");
                    textView.setText("interstitial ad not ready");
                }
                break;
            case R.id.btn_rewardedAd:

                mVideoAd.setVideoAdListener(new MsRewardVideoAdListener() {
                    @Override
                    public void onVideoAdClicked() {
                        Log.d(TAG, "onVideoAdClicked: MsRewardVideoAdListener");
                    }

                    @Override
                    public void onVideoAdClosed() {
                        Log.d(TAG, "onVideoAdClosed: MsRewardVideoAdListener");
                    }

                    @Override
                    public void onVideoAdDisplayed() {
                        Log.d(TAG, "onVideoAdDisplayed: MsRewardVideoAdListener");
                    }

                    @Override
                    public void onVideoAdReward() {
                        Log.d(TAG, "onVideoAdReward: MsRewardVideoAdListener");
                    }

                    @Override
                    public void onVideoAdDontReward(String s) {
                        Log.d(TAG, "\nonVideoAdDontReward: MsRewardVideoAdListener " + s);

                    }

                    @Override
                    public void onVideoAdShowFailed(String s) {
                        Log.d(TAG, "\nonVideoAdShowFailed: MsRewardVideoAdListener " + s);

                    }
                });

                mVideoAd.setLoadCallback(new MsRewardVideoLoadCallback() {
                    @Override
                    public void onLoadFailed() {
                        Log.d(TAG, "onLoadFailed: MsRewardVideoLoadCallback");
                    }

                    @Override
                    public void onLoadSuccessed() {
                        Log.d(TAG, "onLoadSuccessed: MsRewardVideoLoadCallback");

                    }
                });

                if (mVideoAd != null && mVideoAd.isReady()) {
                    mVideoAd.show("ca-app-pub-2152232918216707/3343626633");
                    Bundle params = new Bundle();
                    params.putString("id", "video_aaa");
                    params.putString("type", "rewardVideo");
//                    mFirebase.logEvent("rewardVideo_show", params);
                } else {
                    Log.d(TAG, "onClick: videoad not ready");
                    textView.setText("videoad not ready");
                }
                break;

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        MsSDK.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MsSDK.onStop(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MsSDK.onDestroy(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MsSDK.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MsSDK.onResume(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        MsSDK.onRestart(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MsSDK.onBackPressed(this);
    }
}