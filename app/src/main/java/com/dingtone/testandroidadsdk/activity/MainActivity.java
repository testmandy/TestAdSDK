package com.dingtone.testandroidadsdk.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.dingtone.testandroidadsdk.R;

import java.util.ArrayList;
import java.util.List;

import me.dt.nativeadlibary.config.NativeAdLibManager;
import me.dt.nativeadlibary.util.AdProviderType;

public class MainActivity extends Activity {
    private final String TAG = MainActivity.class.getSimpleName();
    private EditText etAdConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAdConfig = findViewById(R.id.et_ad_config);


    }

    public void updateAdConfig(View view) {
        Log.d(TAG, "update ad config");
        String testAdConfig = etAdConfig.getText().toString();
        NativeAdLibManager.Builder builder = new NativeAdLibManager.Builder(this.getApplication());
        builder.setCountryCode("cn")
                .setNativeAdLibConfig(testAdConfig);

    }

    public void onClickAdmob(View view) {

        List<Integer> adList = new ArrayList<>();
        adList.add(AdProviderType.AD_PROVIDER_TYPE_ADMOB_NATIVE);
        NativeAdLibManager.getInstance().addAdListTest(adList);

        AdShowActivity.start(this, AdProviderType.AD_PROVIDER_TYPE_ADMOB_NATIVE);
    }

    public void onClickFacebook(View view) {

        List<Integer> adList = new ArrayList<>();
        adList.add(AdProviderType.AD_PROVIDER_TYPE_FB_NATIVE);
        NativeAdLibManager.getInstance().addAdListTest(adList);

        AdShowActivity.start(this, AdProviderType.AD_PROVIDER_TYPE_FB_NATIVE);
    }

    public void onClickFlurry(View view) {

        List<Integer> adList = new ArrayList<>();
        adList.add(AdProviderType.AD_PROVIDER_TYPE_FLURRY_NATIVE);
        NativeAdLibManager.getInstance().addAdListTest(adList);

        AdShowActivity.start(this, AdProviderType.AD_PROVIDER_TYPE_FLURRY_NATIVE);
    }

    public void onClickInmobi(View view) {

        List<Integer> adList = new ArrayList<>();
        adList.add(AdProviderType.AD_PROVIDER_TYPE_INMOBI_NATIVE);
        NativeAdLibManager.getInstance().addAdListTest(adList);

        AdShowActivity.start(this, AdProviderType.AD_PROVIDER_TYPE_INMOBI_NATIVE);
    }

    public void onClickMopub(View view) {

        List<Integer> adList = new ArrayList<>();
        adList.add(AdProviderType.AD_PROVIDER_TYPE_MOPUB_NATIVE);
        NativeAdLibManager.getInstance().addAdListTest(adList);

        AdShowActivity.start(this, AdProviderType.AD_PROVIDER_TYPE_MOPUB_NATIVE);
    }

    public void onClickSmaato(View view) {

        List<Integer> adList = new ArrayList<>();
        adList.add(AdProviderType.AD_PROVIDER_TYPE_SMAATO_NATIVE);
        NativeAdLibManager.getInstance().addAdListTest(adList);

        AdShowActivity.start(this, AdProviderType.AD_PROVIDER_TYPE_SMAATO_NATIVE);
    }

    public void onClickTest(View view) {
        startActivity(new Intent(this, TestActivity.class));
    }
}
