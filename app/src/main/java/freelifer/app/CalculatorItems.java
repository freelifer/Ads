package freelifer.app;

import android.content.Context;
import android.view.View;

/**
 * @author kzhu on 2018/2/9.
 */
public class CalculatorItems {

    public static View getViewByType(Context context, int width, int height, int type, IButton iButtons) {
        View textView = iButtons.button(context, type, width, height);
        textView.setTag(type);
        return textView;
    }


}
