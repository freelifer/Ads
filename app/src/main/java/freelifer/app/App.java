package freelifer.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author kzhu on 2018/2/9.
 */
public class App extends Application {

    private Point screenPoint;

    @Override
    public void onCreate() {
        super.onCreate();
        initScreen();
    }


    public Point getScreenPoint() {
        return screenPoint;
    }

    private void initScreen() {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.getDefaultDisplay().getMetrics(metric);
        int w = metric.widthPixels; // 屏幕宽度（像素）
        int h = metric.heightPixels; // 屏幕宽度（像素）
        screenPoint = new Point(w, h);
    }
}
