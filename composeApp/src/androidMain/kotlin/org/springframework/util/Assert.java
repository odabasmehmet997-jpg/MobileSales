package org.springframework.util;

public abstract class Assert {
    public static void isTrue(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }
    public static void notNull(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }
    public static void hasLength(String str, String str2) {
        if (!StringUtils.hasLength(str)) {
            throw new IllegalArgumentException(str2);
        }
    }
    public static void hasText(String str, String str2) {
        if (!StringUtils.hasText(str)) {
            throw new IllegalArgumentException(str2);
        }
    }
    public static void isAssignable(Class<?> cls, Class<?> cls2) {
        isAssignable(cls, cls2, "");
    }
    public static void isAssignable(Class<?> cls, Class<?> cls2, String str) {
        notNull(cls, "Type to check against must not be null");
        if (cls2 == null || !cls.isAssignableFrom(cls2)) {
            throw new IllegalArgumentException(str + cls2 + " is not assignable to " + cls);
        }
    }
    public static void state(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }
    public static void state(boolean z) {
        state(z, "[Assertion failed] - this state invariant must be true");
    }
}
