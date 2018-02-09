package freelifer.app;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author kzhu on 2018/2/9.
 */
public class CalculatorItems {

    public static View getViewByType(Context context, int width, int height, int type) {
        TextView textView = new TextView(context);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width, height);
        textView.setText(type + "");
        textView.setTextColor(context.getResources().getColor(android.R.color.white));
        textView.setLayoutParams(lp);
        return textView;
    }
}
