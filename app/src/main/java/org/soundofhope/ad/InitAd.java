package org.soundofhope.ad;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

/**
 * Created by ok on 3/9/17.
 */

public class InitAd {

    // A menu item view type.
    public static final int MENU_ITEM_VIEW_TYPE = 0;

    // The Native  ad view type.
    public static final int NATIVE_EXPRESS_AD_VIEW_TYPE = 1;

    public static final int NATIVE_CONTENT_AD_VIEW_TYPE = 2;

    public static final int NATIVE_CUSTOM_TEMPLATE_AD_VIEW_TYPE = 3;

    public static final String DFP_AD_UNIT_ID = "/6499/example/native";

    public static final String SIMPLE_TEMPLATE_ID = "10104090";

    public static final StringBuffer headLine = new StringBuffer();
    public static final StringBuffer templateHeadLine = new StringBuffer();
    public static final StringBuffer templateCaption = new StringBuffer();

    // An Activity's Context.
    private final Context mContext;

    public InitAd( Context mContext ) {
        this.mContext = mContext;
    }

    public void initData() {

        this.initContent();
        this.initNativeTemplate();

    }

    private void initContent() {

        AdLoader.Builder builder = new AdLoader.Builder( mContext, DFP_AD_UNIT_ID);

        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            @Override
            public void onContentAdLoaded(NativeContentAd ad) {

                NativeContentAdView adView = (NativeContentAdView) ((Activity)mContext).getLayoutInflater()
                        .inflate(R.layout.ad_content, null);
                //populateContentAdView(ad, adView);
                System.out.println( "11111111111" + ad.getHeadline() );

                headLine.append( ad.getHeadline() );
            }
        });

        AdLoader adLoader = builder.build();

        adLoader.loadAd(new PublisherAdRequest.Builder().build());
    }

    private void initNativeTemplate(){

        AdLoader.Builder builder = new AdLoader.Builder(mContext, DFP_AD_UNIT_ID);

        builder.forCustomTemplateAd(SIMPLE_TEMPLATE_ID,
                new NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener() {
                    @Override
                    public void onCustomTemplateAdLoaded(NativeCustomTemplateAd ad) {
                        templateHeadLine.append( ad.getText("Headline") );
                        templateCaption.append( ad.getText("Caption") );
                    }
                },
                new NativeCustomTemplateAd.OnCustomClickListener() {
                    @Override
                    public void onCustomClick(NativeCustomTemplateAd ad, String s) {
                        Toast.makeText(mContext,
                                "A custom click has occurred in the simple template",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        AdLoader adLoader = builder.build();

        adLoader.loadAd(new PublisherAdRequest.Builder().build());
    }
}