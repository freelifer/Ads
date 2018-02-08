package freelifer.ads.internal;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;

import freelifer.ads.AdRequest;

/**
 * @author kzhu on 2018/2/9.
 */
public class AdmobPlatform implements AdPlatform{

    @Override
    public boolean loadAd(AdRequest adRequest, Context context) {
        AdView adView = new AdView(context);
        adView.setAdListener(new AdListener());
        return true;
    }
}
