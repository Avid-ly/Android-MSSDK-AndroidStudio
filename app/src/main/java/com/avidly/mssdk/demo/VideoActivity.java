package com.avidly.mssdk.demo;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.sdk.MsRewardVideoAd;
import com.ms.sdk.wrapper.video.MsRewardVideoAdListener;
import com.ms.sdk.wrapper.video.MsRewardVideoLoadCallback;

public class VideoActivity extends BaseActivity {
    private static final String TAG = "MSSdk_demo";
    private int coins;

    Button btnPlay;
    Button btnVideo;
    Button btnLoad;
    TextView title;
    TextView text;
    TextView coin;

    MsRewardVideoAd mVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnVideo = (Button) findViewById(R.id.btnVideo);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        text = (TextView) findViewById(R.id.text);
        title = (TextView) findViewById(R.id.title);
        coin = (TextView) findViewById(R.id.coin);

        title.setText(getPackageName());
        coins = 0;

        mVideoAd = MsRewardVideoAd.getInstance(VideoActivity.this);

        mVideoAd.setVideoAdListener(new MsRewardVideoAdListener() {
            @Override
            public void onVideoAdClicked() {
                Log.d(TAG, "onVideoAdClicked: ");
            }

            @Override
            public void onVideoAdClosed() {
                Log.d(TAG, "onVideoAdClosed: ");
                mVideoAd.setLoadCallback(new MyLoadCallBack());
            }

            @Override
            public void onVideoAdDisplayed() {
                Log.d(TAG, "onVideoAdDisplayed: ");
            }

            @Override
            public void onVideoAdReward() {

                Log.d(TAG, "onVideoAdReward: ");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(VideoActivity.this, "发奖励发奖励发奖励", Toast.LENGTH_SHORT).show();
                        coins = coins + 300;
                        coin.setText(coins + " coins");
                    }
                });
            }

            @Override
            public void onVideoAdDontReward(String reason) {

                Log.d(TAG, "onVideoAdDontReward: ");
            }

            @Override
            public void onVideoAdShowFailed(String reason) {
                Log.d(TAG, "onVideoAdShowFailed: ");
            }
        });


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("playing ... ...");
                new Handler(getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameOver();
                    }
                }, 2 * 1000);
            }
        });

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAd();
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("loading ... ...");
                mVideoAd.setLoadCallback(new MyLoadCallBack());
            }
        });
    }
    private void gameOver() {
        text.setText("game over");

        if (mVideoAd.isReady()) {

        } else {
            Toast.makeText(this, "视频广告没有加载成功", Toast.LENGTH_SHORT).show();

        }
    }

    private void showAd() {
        if (mVideoAd.isReady()) {
            mVideoAd.show("RewardVideo_Shop");
        } else {
            Toast.makeText(VideoActivity.this, "广告还没准备好", Toast.LENGTH_SHORT).show();
        }
    }

    private  class MyLoadCallBack implements MsRewardVideoLoadCallback {

        @Override
        public void onLoadFailed() {
            Log.i(TAG, "VideoActivity onLoadFailed: ");
            text.setText("game");
            Toast.makeText(VideoActivity.this, "视频广告加载失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLoadSuccessed() {
            Log.i(TAG, "VideoActivity onLoadSuccessed: ");
            text.setText("game");
            Toast.makeText(VideoActivity.this, "视频广告加载成功", Toast.LENGTH_SHORT).show();
        }
    }
}
