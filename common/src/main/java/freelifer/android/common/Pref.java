package freelifer.android.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author kzhu on 2018/4/19.
 */
public class Pref {
    private static final String FILE_NAME = "f_c";

    public static void initialize(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        sharedPreferences.edit().putInt("", 0).apply();
    }

    private EncodeTools encodeTools;

    private EncryptSharedPref() {
        encodeTools = new SpEncode("W2v4xI1L8dlM10O5");
    }

    private static class Singleton {
        private static final EncryptSharedPref instance = new EncryptSharedPref();
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Context context, String name, String key, @NonNull T defValue) {
        SharedPreferences pref = getPref(context, name);
        final String encodeKey = encodeTools.encodeKey(key);
        if (!pref.contains(encodeKey)) {
            return defValue;
        }

        try {
            String value = encodeTools.decodeValue(pref.getString(encodeKey, ""));
            if (value == null) {
                return defValue;
            }

            if (defValue instanceof Long) {
                return (T) Long.valueOf(value);
            } else if (defValue instanceof String) {
                return (T) String.valueOf(value);
            } else if (defValue instanceof Integer) {
                return (T) Integer.valueOf(value);
            } else if (defValue instanceof Boolean) {
                return (T) Boolean.valueOf(value);
            } else if (defValue instanceof Float) {
                return (T) Float.valueOf(value);
            }
        } catch (Exception e) {
            return defValue;
        }

        return defValue;
    }

    public void put(Context context, String name, String key, Object object) {
        SharedPreferences.Editor editor = getPref(context, name).edit();
        String encodeKey = encodeTools.encodeKey(key);
        String encodeValue = null;
        if (object instanceof Long) {
            encodeValue = encodeTools.encodeValue(Long.toString((Long) object));
        } else if (object instanceof String) {
            encodeValue = encodeTools.encodeValue((String) object);
        } else if (object instanceof Integer) {
            encodeValue = encodeTools.encodeValue(Integer.toString((Integer) object));
        } else if (object instanceof Boolean) {
            encodeValue = encodeTools.encodeValue(Boolean.toString((Boolean) object));
        } else if (object instanceof Float) {
            encodeValue = encodeTools.encodeValue(Float.toString((Float) object));
        }
        if (encodeValue == null) {
            return;
        }
        editor.putString(encodeKey, encodeValue);
        editor.apply();
    }

    public Editor edit(Context context, String name) {
        return new Editor(encodeTools, getPref(context, name).edit());
    }

    private SharedPreferences getPref(Context context, String name) {
        return context.getSharedPreferences(encodeTools.encodeName(name), MODE_PRIVATE);
    }

    public static class Editor {
        private android.content.SharedPreferences.Editor editor;
        private EncodeTools encodeTools;

        Editor(EncodeTools encodeTools, android.content.SharedPreferences.Editor editor) {
            this.encodeTools = encodeTools;
            this.editor = editor;
        }

        public EncryptSharedPref.Editor put(String key, Object value) {
            String encodeKey = encodeTools.encodeKey(key);
            String encodeValue = null;
            if (value instanceof Long) {
                encodeValue = encodeTools.encodeValue(Long.toString((Long) value));
            } else if (value instanceof String) {
                encodeValue = encodeTools.encodeValue((String) value);
            } else if (value instanceof Integer) {
                encodeValue = encodeTools.encodeValue(Integer.toString((Integer) value));
            } else if (value instanceof Boolean) {
                encodeValue = encodeTools.encodeValue(Boolean.toString((Boolean) value));
            } else if (value instanceof Float) {
                encodeValue = encodeTools.encodeValue(Float.toString((Float) value));
            }
            if (encodeValue != null) {
                this.editor.putString(encodeKey, encodeValue);
            }
            return this;
        }

        public void commit() {
            this.editor.commit();
        }

        public void apply() {
            this.editor.apply();
        }


    }
}
