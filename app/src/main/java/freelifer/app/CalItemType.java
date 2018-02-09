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

    public int type;

    private CalItemType(int type) {
        this.type = type;
    }
}
