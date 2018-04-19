package freelifer.android.common;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author kzhu on 2018/4/19.
 */
public class Screens {

    public static void initialize(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.getDefaultDisplay().getMetrics(metric);
        int w = metric.widthPixels; // 屏幕宽度（像素）
        int h = metric.heightPixels; // 屏幕宽度（像素）
    }
}
