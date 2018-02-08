package freelifer.ads.internal;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;

import freelifer.ads.AdRequest;

/**
 * @author kzhu on 2018/2/8.
 */
public class Ads {
    private static final boolean DBG = true;
    private static final String TAG = "Ads";

    private Context appContext;

    private boolean initialized = false;
    private boolean hasAdmob = false;

    private static class Holder {
        private static final Ads instance = new Ads();
    }

    public static Ads getInstance() {
        return Holder.instance;
    }

    public boolean init(Context context) {
        checkAdPlatform();
        this.appContext = context;

        initialized = true;
        return true;
    }

    public void initializeAd(AdRequest adRequest, Context context) {
    }

    public boolean loadAd(AdRequest adRequest, Context context) {
        AdPlatform adPlatform = null;
        if (hasAdmob) {
            adPlatform = new AdmobPlatform();
        }

        if (adPlatform == null) {
            LocalLog.i(DBG, TAG, "loadAd adPlatform is null");
            return false;
        }
        return adPlatform.loadAd(adRequest, context);
    }

    public boolean destroyAd(AdRequest adRequest) {
        return true;
    }

    private void checkAdPlatform() {
        hasAdmob = ClassUtils.exist("com.google.android.gms.ads.MobileAds");

        LocalLog.i(DBG, TAG, "checkAdPlatform admob[%b]", hasAdmob);
    }
}
