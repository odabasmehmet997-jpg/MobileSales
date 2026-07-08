package dagger.internal;

public enum Preconditions {
    ;
    public static <T> T checkNotNull(final T t) {
        t.getClass();
        return t;
    }
    public static <T> T checkNotNull(final T t, final String str) {
        if (null != t) {
            return t;
        }
        throw new NullPointerException(str);
    }
    public static <T> T checkNotNullFromProvides(final T t) {
        if (null != t) {
            return t;
        }
        throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    public static void checkArgument(final boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException();
        }
    }
    public static void checkState(final boolean condition) {
        if (!condition) {
            throw new IllegalStateException();
        }
    }
}
