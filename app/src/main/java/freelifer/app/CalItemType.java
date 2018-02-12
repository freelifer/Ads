package freelifer.app;

/**
 * @author kzhu on 2018/2/9.
 */
public class CalItemType {
    public static final int TYPE_AC = (-1);
    public static final int TYPE_REVERSED = (-2);
    public static final int TYPE_PERCENTAGE = (-3);
    public static final int TYPE_PLUS = (-4);
    public static final int TYPE_MINUS = (-5);
    public static final int TYPE_MULTIPLY = (-6);
    public static final int TYPE_EXCEPT = (-7);
    public static final int TYPE_COMPUTE = (-8);
    public static final int TYPE_DOT = (-9);
    public static final int TYPE_NUMBER = (0);
    public static final int TYPE_ZERO = (0);
    public static final int TYPE_ONE = (1);
    public static final int TYPE_TWO = (2);
    public static final int TYPE_THREE = (3);
    public static final int TYPE_FOUR = (4);
    public static final int TYPE_FIVE = (5);
    public static final int TYPE_SIX = (6);
    public static final int TYPE_SEVEN = (7);
    public static final int TYPE_EIGHT = (8);
    public static final int TYPE_NINE = (9);

    public int type;

    private CalItemType(int type) {
        this.type = type;
    }

    public static String toString(int type) {
        switch (type) {
            case CalItemType.TYPE_ONE:
            case CalItemType.TYPE_TWO:
            case CalItemType.TYPE_THREE:
            case CalItemType.TYPE_FOUR:
            case CalItemType.TYPE_FIVE:
            case CalItemType.TYPE_SIX:
            case CalItemType.TYPE_SEVEN:
            case CalItemType.TYPE_EIGHT:
            case CalItemType.TYPE_NINE:
            case CalItemType.TYPE_ZERO:
                return type + "";
            case CalItemType.TYPE_AC:
                return "AC";
            case CalItemType.TYPE_REVERSED:
                return "+/-";
            case CalItemType.TYPE_PERCENTAGE:
                return "%";
            case CalItemType.TYPE_PLUS:
                return "+";
            case CalItemType.TYPE_MINUS:
                return "－";
            case CalItemType.TYPE_MULTIPLY:
                return "×";
            case CalItemType.TYPE_EXCEPT:
                return "÷";
            case CalItemType.TYPE_COMPUTE:
                return "=";
            case CalItemType.TYPE_DOT:
                return ".";
            default:
                break;
        }
        return "";
    }
}
