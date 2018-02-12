package freelifer.app;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author kzhu on 2018/2/9.
 */
public class Calculator implements View.OnClickListener {

    private int screenWidth;
    private int screenHeight;
    private int space;
    private StringBuilder currentNumBuilder = new StringBuilder();
    private EditText edNumber;
    private IButton iButtons = new DefaultButton();

    // 计算的中间结果。
    private double resultNum = 0.0;

    private boolean isFirst = true;


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

    public void fillCalculatorBody(final Context context, ViewGroup calculatorBody, EditText edNumber) {
        this.edNumber = edNumber;
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
            view.setGravity(Gravity.CENTER);
            lp = new RelativeLayout.LayoutParams(space, space);
            int row = calData.location[0];
            int column = calData.location[1];
            lp.topMargin = space * row;
            lp.leftMargin = space * column;
            view.setLayoutParams(lp);
//            if (row % 2 == 0) {
//                if (column % 2 == 0) {
//                    view.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
//                } else {
//                    view.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
//                }
//            } else {
//                if (column % 2 == 0) {
//                    view.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
//                } else {
//                    view.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
//                }
//            }
            View v = CalculatorItems.getViewByType(context, space, space, calData.type, iButtons);
            v.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        Context ctx = view.getContext();
        int type = (int) view.getTag();
        switch (type) {
            case CalItemType.TYPE_ONE:
                fillNumber("1");
                break;
            case CalItemType.TYPE_TWO:
                fillNumber("2");
                break;
            case CalItemType.TYPE_THREE:
                fillNumber("3");
                break;
            case CalItemType.TYPE_FOUR:
                fillNumber("4");
                break;
            case CalItemType.TYPE_FIVE:
                fillNumber("5");
                break;
            case CalItemType.TYPE_SIX:
                fillNumber("6");
                break;
            case CalItemType.TYPE_SEVEN:
                fillNumber("7");
                break;
            case CalItemType.TYPE_EIGHT:
                fillNumber("8");
                break;
            case CalItemType.TYPE_NINE:
                fillNumber("9");
                break;
            case CalItemType.TYPE_ZERO:
                fillNumber("0");
                break;
            case CalItemType.TYPE_AC:
                acNumber();
                Toast.makeText(ctx, "TYPE_AC", Toast.LENGTH_SHORT).show();
                break;
            case CalItemType.TYPE_REVERSED:
                revNumber();
                Toast.makeText(ctx, "TYPE_REVERSED", Toast.LENGTH_SHORT).show();
                break;
            case CalItemType.TYPE_PERCENTAGE:
                Toast.makeText(ctx, "TYPE_PERCENTAGE", Toast.LENGTH_SHORT).show();
                break;
            case CalItemType.TYPE_PLUS:
                handleOperator(type);
                Toast.makeText(ctx, "TYPE_PLUS", Toast.LENGTH_SHORT).show();
                break;
            case CalItemType.TYPE_MINUS:
                handleOperator(type);
                Toast.makeText(ctx, "TYPE_MINUS", Toast.LENGTH_SHORT).show();
                break;
            case CalItemType.TYPE_MULTIPLY:
                handleOperator(type);
                Toast.makeText(ctx, "TYPE_MULTIPLY", Toast.LENGTH_SHORT).show();
                break;
            case CalItemType.TYPE_EXCEPT:
                Toast.makeText(ctx, "TYPE_EXCEPT", Toast.LENGTH_SHORT).show();
                break;
            case CalItemType.TYPE_COMPUTE:
                handleOperator(type);
                Toast.makeText(ctx, "TYPE_COMPUTE", Toast.LENGTH_SHORT).show();
                break;
            case CalItemType.TYPE_DOT:
                fillNumber(".");
                Toast.makeText(ctx, "TYPE_DOT", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void fillNumber(String number) {
        if (currentNumBuilder.length() == 0 && "0".equals(number)) {
            return;
        }

        if (".".equals(number)) {
            if (currentNumBuilder.length() == 0) {
                currentNumBuilder.append("0");
            } else {
                if (checkDot(currentNumBuilder.toString())) {
                    return;
                }
            }
        }
        currentNumBuilder.append(number);
        edNumber.setText(currentNumBuilder.toString());
    }

    private void handleOperator(int type) {
        if (isFirst) {
            isFirst = false;
            resultNum = getNum(currentNumBuilder);
            currentNumBuilder.setLength(0);
            return;
        }
        if (type == CalItemType.TYPE_PLUS) {
            resultNum += getNum(currentNumBuilder);
        } else if (type == CalItemType.TYPE_MINUS) {
            resultNum -= getNum(currentNumBuilder);
        } else if (type == CalItemType.TYPE_COMPUTE) {
            resultNum = getNum(currentNumBuilder);
        } else if (type == CalItemType.TYPE_MULTIPLY) {
            resultNum *= getNum(currentNumBuilder);
        }
        long t1;
        double t2;
        t1 = (long) resultNum;
        t2 = resultNum - t1;
        if (t2 == 0) {
            edNumber.setText(String.valueOf(t1));
        } else {
            edNumber.setText(String.valueOf(resultNum));
        }
        currentNumBuilder.setLength(0);
    }

    private void acNumber() {
        if (currentNumBuilder != null) {
            currentNumBuilder.setLength(0);
            edNumber.setText(R.string.cal_default_number);
        }
        resultNum = 0.0;
        isFirst = true;
    }

    private void revNumber() {
        if (currentNumBuilder != null) {
            if (currentNumBuilder.length() != 0) {
                String first = currentNumBuilder.substring(0, 1);
                if ("-".equals(first)) {
                    currentNumBuilder.deleteCharAt(0);
                } else {
                    currentNumBuilder.insert(0, "-");
                }
                edNumber.setText(currentNumBuilder.toString());
            }
        }
    }

    private boolean checkDot(@NonNull String src) {
        if (src.contains(".")) {
            return true;
        } else {
            return false;
        }
    }

    private double getNum(StringBuilder str) {
        if (str == null || str.length() <= 0) {
            return 0.0;
        }
        String numStr = str.toString();
        if (TextUtils.isEmpty(numStr)) {
            return 0.0;
        }
        try {
            return Double.valueOf(numStr);
        } catch (NumberFormatException e) {
            // ignore
        }
        return 0.0;
    }

    private void computeNum() {
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


    /**
     * 处理运算符键被按下的事件
     *
     * @param key
     */
//    private void handleOperator(String key) {
//        if (operator.equals("/")) {
    // 除法运算
    // 如果当前结果文本框中的值等于0
//            if (getNumberFromText() == 0.0) {
    // 操作不合法
//                operateValidFlag = false;
//                resultText.setText("除数不能为零");
//            } else {
//                resultNum /= getNumberFromText();
//            }
//        } else if (operator.equals("1/x")) {
    // 倒数运算
//            if (resultNum == 0.0) {
    // 操作不合法
//                operateValidFlag = false;
//                resultText.setText("零没有倒数");
//            } else {
//                resultNum = 1 / resultNum;
//            }
//        } else if (operator.equals("+")) {
    // 加法运算
//            resultNum += getNumberFromText();
//        } else if (operator.equals("-")) {
//             减法运算
//            resultNum -= getNumberFromText();
//        } else if (operator.equals("*")) {
    // 乘法运算
//            resultNum *= getNumberFromText();
//        } else if (operator.equals("sqrt")) {
    // 平方根运算
//            resultNum = Math.sqrt(resultNum);
//        } else if (operator.equals("%")) {
    // 百分号运算，除以100
//            resultNum = resultNum / 100;
//        } else if (operator.equals("+/-")) {
    // 正数负数运算
//            resultNum = resultNum * (-1);
//        } else if (operator.equals("=")) {
    // 赋值运算
//            resultNum = getNumberFromText();
//        }
//        if (operateValidFlag) {
    // 双精度浮点数的运算
//            long t1;
//            double t2;
//            t1 = (long) resultNum;
//            t2 = resultNum - t1;
//            if (t2 == 0) {
//                resultText.setText(String.valueOf(t1));
//            } else {
//                resultText.setText(String.valueOf(resultNum));
//            }
//        }
    // 运算符等于用户按的按钮
//        operator = key;
//        firstDigit = true;
//        operateValidFlag = true;
//    }

}
