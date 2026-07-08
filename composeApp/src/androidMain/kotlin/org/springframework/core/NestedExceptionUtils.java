package org.springframework.core;

public abstract class NestedExceptionUtils {
    public static String buildMessage(String str, Throwable th) {
        if (th == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
            sb.append("; ");
        }
        sb.append("nested exception is ");
        sb.append(th);
        return sb.toString();
    }
}
