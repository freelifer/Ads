package freelifer.app;

import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author kzhu on 2018/2/9.
 */
public class Calculator {

    private int screenWidth;
    private int screenHeight;
    private int space;

    private Calculator(Point screenPoint) {
        if (screenPoint != null) {
            screenWidth = screenPoint.x;
            screenHeight = screenPoint.y;
            space = screenWidth / 4;
        }
    }

    public static Calculator create(Point screenPoint) {
        return new Calculator(screenPoint);
    }

    public void fillCalculatorBody(final Context context, ViewGroup calculatorBody) {
        String json = readFile(context);
        CalDataMap calDataMap = CalDataMap.create(json);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) calculatorBody.getLayoutParams();
        lp.width = space * 4;
        lp.height = space * 5;
        calculatorBody.setLayoutParams(lp);

        if (calDataMap == null || calDataMap.calDatas == null) {
            return;
        }
        int len = calDataMap.calDatas.length;
        for (int i = 0; i < len; i++) {
            CalDataMap.CalData calData = calDataMap.calDatas[i];
            if (calData.location == null) {
                return;
            }
            int locationLen = calData.location.length;
            RelativeLayout view = new RelativeLayout(context);
            lp = new RelativeLayout.LayoutParams(space, space);
            int row = calData.location[0];
            int column = calData.location[1];
            lp.topMargin = space * row;
            lp.leftMargin = space * column;
            view.setLayoutParams(lp);
            if (row % 2 == 0) {
                if (column % 2 == 0) {
                    view.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                } else {
                    view.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                }
            } else {
                if (column % 2 == 0) {
                    view.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                } else {
                    view.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                }
            }
            View v = CalculatorItems.getViewByType(context, space, space, calData.type);
            view.addView(v);
            calculatorBody.addView(view);
        }
//        for (int row = 0; row < 5; row++) {
//            for (int column = 0; column < 4; column++) {
//                View view = new View(context);
//                lp = new RelativeLayout.LayoutParams(space, space);
//                lp.topMargin = space * row;
//                lp.leftMargin = space * column;
//                view.setLayoutParams(lp);
//                if (row % 2 == 0) {
//                    if (column % 2 == 0) {
//                        view.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
//                    } else {
//                        view.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
//                    }
//                } else {
//                    if (column % 2 == 0) {
//                        view.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
//                    } else {
//                        view.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
//                    }
//                }
//
//                calculatorBody.addView(view);
//                final int x = row;
//                final int y = column;
//                view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(context, x + "," + y, Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        }
    }

    private String readFile(final Context context) {
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            is = context.getAssets().open("cal.json");
            bos = new ByteArrayOutputStream(is.available());
            byte[] buffer = new byte[1024];
            int size;
            while ((size = is.read(buffer)) > 0) {
                bos.write(buffer, 0, size);
            }
            return new String(bos.toByteArray(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();

                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
