package io.reactivex.internal.functions;

import io.reactivex.functions.BiPredicate;

import java.util.Objects;

public final class ObjectHelper {
    static final BiPredicate<Object, Object> EQUALS = new BiObjectPredicate();

    public static int compare(final int i2, final int i3) {
        if (i2 < i3) {
            return -1;
        }
        return i2 > i3 ? 1 : 0;
    }

    public static int compare(final long j2, final long j3) {
        if (j2 < j3) {
            return -1;
        }
        return j2 > j3 ? 1 : 0;
    }

    private ObjectHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> T requireNonNull(final T t, final String str) {
        if (null != t) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static boolean equals(final Object obj, final Object obj2) {
        return Objects.equals (obj, obj2);
    }

    public static <T> BiPredicate<T, T> equalsPredicate() {
        return ( BiPredicate<T, T> ) ObjectHelper.EQUALS;
    }

    public static int verifyPositive(final int i2, final String str) {
        if (0 < i2) {
            return i2;
        }
        throw new IllegalArgumentException(str + " > 0 required but it was " + i2);
    }

    public static long verifyPositive(final long j2, final String str) {
        if (0 < j2) {
            return j2;
        }
        throw new IllegalArgumentException(str + " > 0 required but it was " + j2);
    }

    static final class BiObjectPredicate implements BiPredicate<Object, Object> {
        BiObjectPredicate() {
        }

        @Override // io.reactivex.functions.BiPredicate
        public boolean test(final Object obj, final Object obj2) {
            return ObjectHelper.equals(obj, obj2);
        }
    }
}
