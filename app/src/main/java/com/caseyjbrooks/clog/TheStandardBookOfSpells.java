package com.caseyjbrooks.clog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TheStandardBookOfSpells {

    @Spell(name="uppercase")
    public static String toUpper(Object data) {
        return data.toString().toUpperCase();
    }

    @Spell(name="lowercase")
    public static String toLower(Object data) {
        return data.toString().toLowerCase();
    }

    @Spell
    public static String capitalize(Object data) {
        String input = data.toString();
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    @Spell
    public static String repeat(Object data, int times) {
        String output = "";
        for(int i = 0; i < times; i++) {
            if(data != null) {
                output += data.toString();
            }
        }

        return output;
    }

    /**
     * Based on the Android Open Source Project TestUtils.java class.
     *
     * See {@link <a href="https://android.googlesource.com/platform/frameworks/base/+/b2c4f0bf11f38fd31d80f1256c89b9db043a2929/core/java/android/text/TextUtils.java#280">TextUtils.java#280</a>}.
     *
     * @param data
     * @param separator
     * @return
     */
    @Spell
    public static String join(Object[] data, String separator) {
        StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        for (Object token: data) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(separator);
            }
            sb.append(token);
        }
        return sb.toString();
    }

    /**
     * Based on the Android Open Source Project TestUtils.java class.
     *
     * See {@link <a href="https://android.googlesource.com/platform/frameworks/base/+/b2c4f0bf11f38fd31d80f1256c89b9db043a2929/core/java/android/text/TextUtils.java#280">TextUtils.java#280</a>}.
     *
     * @param data
     * @param separator
     * @return
     */
    @Spell
    public static String join(Iterable data, String separator) {
        StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        for (Object token: data) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(separator);
            }
            sb.append(token);
        }
        return sb.toString();
    }

    @Spell(name="className")
    public static String getClassName(Object data) {
        return data.getClass().getSimpleName();
    }

    @Spell
    public static String length(String data) {
        return Integer.toString(data.length());
    }

    @Spell
    public static Object date(Calendar data) {
        return date(data, "yyyy/MM/dd HH:mm:ss");
    }

    @Spell
    public static Object date(Calendar data, String formatString) {
        if(data == null) {
            data = Calendar.getInstance();
        }

        if(formatString == null || formatString.length() == 0) {
            formatString = "yyyy/MM/dd HH:mm:ss";
        }

        return new SimpleDateFormat(formatString).format(data.getTime());
    }
}
