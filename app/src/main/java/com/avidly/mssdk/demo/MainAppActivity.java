package com.avidly.mssdk.demo;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.aly.sdk.ALYAnalysis;
import com.ms.sdk.MsSDK;

;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainAppActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(MainAppActivity.this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainAppActivity.this, new String[]{WRITE_EXTERNAL_STORAGE}, 001);
        }
        ALYAnalysis.enalbeDebugMode(false);
        ALYAnalysis.init(getApplicationContext(), "999999", "32408");
        // 上线时请关闭
        MsSDK.setDebuggable(true);
        // 同意GDPR
        MsSDK.grantConsent(this);
        MsSDK.init(this);

        findViewById(R.id.btnVideo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAppActivity.this, VideoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
