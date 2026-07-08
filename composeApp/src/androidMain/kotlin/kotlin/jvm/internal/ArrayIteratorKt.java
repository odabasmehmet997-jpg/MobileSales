package kotlin.jvm.internal;

import java.util.Iterator;

public final class ArrayIteratorKt {
    public static <T> Iterator<T> iterator(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "array");
        return new ArrayIterator(tArr);
    }
}
