package freelifer.app;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author kzhu on 2018/2/9.
 */
public class DefaultButton implements IButton {

    @Override
    public View button(Context context, int type, int width, int height) {
        TextView textView = new TextView(context);
        textView.setPadding(10, 10, 10, 10);
        textView.setText(CalItemType.toString(type));
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
        textView.setBackgroundResource(R.drawable.textview_bg);
        textView.setTextColor(context.getResources().getColor(android.R.color.white));
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width - 28, height - 28);
        textView.setLayoutParams(lp);
        return textView;
    }

    @Override
    public void onClick(int type) {

    }
}
