package freelifer.ads.internal;

import android.content.Context;

import freelifer.ads.AdRequest;

/**
 * @author kzhu on 2018/2/9.
 */
public interface AdPlatform {
    boolean loadAd(AdRequest adRequest, Context context);
}
