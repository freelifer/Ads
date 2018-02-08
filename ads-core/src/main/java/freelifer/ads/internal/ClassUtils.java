package freelifer.ads.internal;

/**
 * @author kzhu on 2018/2/8.
 */
public class ClassUtils {


    public static boolean exist(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
