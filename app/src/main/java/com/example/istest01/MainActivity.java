package com.example.istest01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.BannerListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IronSource.init(this, "10b17aa7d", IronSource.AD_UNIT.BANNER);

        IntegrationHelper.validateIntegration(this);

        final FrameLayout bannerContainer = findViewById(R.id.bannerContainer);
        IronSourceBannerLayout banner = IronSource.createBanner(this, ISBannerSize.BANNER);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        bannerContainer.addView(banner, 0, layoutParams);

        banner.setBannerListener(new BannerListener() {
            @Override
            public void onBannerAdLoaded() {
                banner.setVisibility(View.VISIBLE);
            }

            @Override
            public void onBannerAdLoadFailed(IronSourceError error) {
                // Called after a banner has attempted to load an ad but failed.

                Log.d("TAG", "onBannerAdLoadFailed: " + error.getErrorMessage());
                Log.d("TAG", "onBannerAdLoadFailed: " + error.getErrorCode());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bannerContainer.removeAllViews();
                    }
                });
            }

            @Override
            public void onBannerAdClicked() {
                // Called after a banner has been clicked.
            }

            @Override
            public void onBannerAdScreenPresented() {
                // Called when a banner is about to present a full screen content.
            }

            @Override
            public void onBannerAdScreenDismissed() {
                // Called after a full screen content has been dismissed
            }

            @Override
            public void onBannerAdLeftApplication() {
                // Called when a user would be taken out of the application context.
            }
        });

        IronSource.loadBanner(banner, "DefaultBanner");
        // YOUR OTHER CODE //
        // YOUR OTHER CODE //
        // YOUR OTHER CODE //
    }

    protected void onResume() {
        super.onResume();
        IronSource.onResume(this);
    }
    protected void onPause() {
        super.onPause();
        IronSource.onPause(this);
    }
}