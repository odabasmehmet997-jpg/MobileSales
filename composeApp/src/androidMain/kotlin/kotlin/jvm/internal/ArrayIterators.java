package kotlin.jvm.internal;

import kotlin.collections.PrimitiveIterators;

import java.util.NoSuchElementException;

public final class ArrayIterators extends PrimitiveIterators {
    private final boolean[] array;
    private int index;

    public ArrayIterators(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "array");
        this.array = zArr;
    }
    public boolean hasNext() {
        return this.index < this.array.length;
    }

    public boolean nextBoolean() {
        try {
            int i = this.index;
            this.index = i + 1;
            return this.array[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.index--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
