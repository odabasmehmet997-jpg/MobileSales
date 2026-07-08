package com.proje.mobilesales.core.utils;

import com.google.gson.Gson;

public class SerialUtils {
    public static String serializeObject(Object obj) {
        return new Gson().toJson(obj);
    }
    public static Object unserializeObject(String str, Object obj) {
        return new Gson().fromJson(str, (Class) obj.getClass());
    }
    public static Object cloneObject(Object obj) {
        return unserializeObject(serializeObject(obj), obj);
    }
}
