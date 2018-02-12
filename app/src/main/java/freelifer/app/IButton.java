package freelifer.app;

import android.content.Context;
import android.view.View;

/**
 * @author kzhu on 2018/2/9.
 */
public interface IButton {

    View button(Context context, int type, int width, int height);

    void onClick(int type);

}
