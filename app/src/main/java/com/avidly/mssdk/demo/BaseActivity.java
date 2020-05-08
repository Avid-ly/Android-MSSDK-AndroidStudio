package com.avidly.mssdk.demo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ms.sdk.MsSDK;


public  class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MsSDK.onCreate(this);
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
