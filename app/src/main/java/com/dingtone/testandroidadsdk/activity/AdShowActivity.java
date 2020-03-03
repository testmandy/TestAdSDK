package com.dingtone.testandroidadsdk.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.dingtone.testandroidadsdk.R;
import com.dingtone.testandroidadsdk.model.MyAdViewType;

import me.dt.nativeadlibary.ad.AdCallbackListener;
import me.dt.nativeadlibary.ad.ErrorMsg;
import me.dt.nativeadlibary.ad.data.BaseNativeAdData;
import me.dt.nativeadlibary.manager.AdCenterManager;
import me.dt.nativeadlibary.util.AdProviderType;

public class AdShowActivity extends Activity implements AdCallbackListener {

    private FrameLayout adViewContainer;
    private int mAdPosition;
    private String mAdViewType;
//    NativeAdBannerView nativeAdBannerView;

    public static void start(Activity activity, int adPosition) {
        Intent intent = new Intent(activity, AdShowActivity.class);
        intent.putExtra("adPosition", adPosition);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_show);

        mAdPosition = getIntent().getIntExtra("adPosition", AdProviderType.AD_PROVIDER_TYPE_ADMOB_NATIVE);
        adViewContainer = findViewById(R.id.ad_view_container);
    }

    public void onClickBanner(View view) {
        mAdViewType = "Banner";
//        nativeAdBannerView = findViewById(R.id.ad_banner_view);
//        nativeAdBannerView.setPlacement(0);
//        nativeAdBannerView.loadAd();
        new AdCenterManager().load(this, 0, MyAdViewType.BANNER,this);
    }

    public void onClickLoading(View view) {
        mAdViewType = "Loading";
        new AdCenterManager().load(this, 1, MyAdViewType.LOADING,this);
    }

    public void onClickInterstial(View view) {
        mAdViewType = "Interstial";
        new AdCenterManager().load(this, 2, MyAdViewType.INTERSTITIAL,this);
    }

    public void onClickIns(View view) {
        mAdViewType = "Ins";
        new AdCenterManager().load(this, 3, MyAdViewType.INS_SDK,this);
    }

    public void onClickLuckyBox(View view) {
        mAdViewType = "LuckyBox";
        new AdCenterManager().load(this, 4, MyAdViewType.LUCKY_BOX,this);
    }

    public void onClickSplash(View view) {
        mAdViewType = "Splash";
        new AdCenterManager().load(this, 5, MyAdViewType.SPLASH,this);
    }

    public void onClickVideoOffer(View view) {
        mAdViewType = "VideoOffer";
        new AdCenterManager().load(this, 6, MyAdViewType.VIDEO_OFFER,this);
    }

    public void onClickSpecialOffer(View view) {
        mAdViewType = "SpecialOffer";
        new AdCenterManager().load(this, 7, MyAdViewType.SPECIAL_OFFER,this);
    }

    @Override
    public void onLoadFailed(ErrorMsg errorMsg) {
        if (errorMsg != null && errorMsg.getErrorMsg() != null) {
            Toast.makeText(this, errorMsg.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoadNoCacheFailed(ErrorMsg errorMsg) {

    }

    @Override
    public void onLoadSuccess(BaseNativeAdData nativeAdData) {

        if ("Splash".equals(mAdViewType)) {
            findViewById(R.id.banner).setVisibility(View.GONE);
            findViewById(R.id.loading).setVisibility(View.GONE);
            findViewById(R.id.interstial).setVisibility(View.GONE);
            findViewById(R.id.ins).setVisibility(View.GONE);
            findViewById(R.id.lucky_box).setVisibility(View.GONE);
            findViewById(R.id.splash).setVisibility(View.GONE);
        } else {
            findViewById(R.id.banner).setVisibility(View.VISIBLE);
            findViewById(R.id.loading).setVisibility(View.VISIBLE);
            findViewById(R.id.interstial).setVisibility(View.VISIBLE);
            findViewById(R.id.ins).setVisibility(View.VISIBLE);
            findViewById(R.id.lucky_box).setVisibility(View.VISIBLE);
            findViewById(R.id.splash).setVisibility(View.VISIBLE);
        }

        Toast.makeText(this, mAdViewType + " load success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadSuccess(BaseNativeAdData nativeAdData, View adView) {

    }

    @Override
    public void onClick(int adType) {

    }

    @Override
    public void onImpression(int adType) {

    }

}
