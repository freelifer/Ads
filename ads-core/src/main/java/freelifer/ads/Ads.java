package freelifer.ads;

import android.content.Context;

/**
 * @author kzhu on 2018/2/8.
 */
public class Ads {

    public static boolean init(Context context) {
        return freelifer.ads.internal.Ads.getInstance().init(context);
    }
}
