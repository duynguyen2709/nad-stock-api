package com.nad.util;

/**
 * @author duynguyen
 */
public class NumberUtil {

    public static float parseFloat(Object obj) {
        try {
            return Float.parseFloat(obj.toString());
        } catch (Exception ignored) {
            return 0;
        }
    }
}
