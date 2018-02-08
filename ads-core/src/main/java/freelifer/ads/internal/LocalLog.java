package freelifer.ads.internal;

import android.util.Log;

/**
 * @author kzhu on 2018/2/8.
 */
public class LocalLog {


    public static void i(boolean debug, String tag, String format, Object... args) {
        if (debug) {
            Log.i(tag, String.format(format, args));
        }
    }
}
