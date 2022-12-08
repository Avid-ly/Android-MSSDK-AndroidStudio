package com.avidly.mssdk.demo;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.aly.sdk.ALYAnalysis;
import com.ms.sdk.MsSDK;
import com.ms.sdk.listener.MsSdkInitializationListener;
import com.ms.sdk.listener.MssdkConsentDialogListener;

;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainAppActivity extends BaseActivity {
    private static final String TAG="MSSdk_demo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ALYAnalysis.enalbeDebugMode(false);
        MsSDK.setDebuggable(true);
        ALYAnalysis.init(getApplicationContext(), "600208", "32408", new ALYAnalysis.TasdkinitializdListener() {
            @Override
            public void onSuccess(String s) {
                MsSDK.init(MainAppActivity.this, new MsSdkInitializationListener() {
                    @Override
                    public void onInitializationSuccess() {
                        Log.d(TAG, "onInitializationSuccess: 初始化成功");

                    }

                    @Override
                    public void onInitializationFail(String reason) {
                        Log.d(TAG, "onInitializationFail: 初始化失败");
                    }
                });
            }

            @Override
            public void onFail(String s) {

            }
        });


        findViewById(R.id.btnVideo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAppActivity.this, VideoActivity.class);
                startActivity(intent);
            }
        });

    }


    public void goToSimpleBanner(View view) {
        startActivity(new Intent(MainAppActivity.this, SimpleBannerActivity.class));
    }

    public void goToBanner(View view) {
        Intent intent = new Intent(MainAppActivity.this, BannerActivity.class);
        startActivity(intent);
    }

    public void goToInterAds(View view) {
        Intent intent = new Intent(MainAppActivity.this, InterstitialActivity.class);
        startActivity(intent);
    }
}
