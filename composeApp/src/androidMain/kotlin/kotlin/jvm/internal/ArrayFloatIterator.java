package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.FloatIterator;

public final class ArrayFloatIterator extends FloatIterator {
    private final float[] array;
    private int index;
    public ArrayFloatIterator(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "array");
        this.array = fArr;
    }
    public boolean hasNext() {
        return this.index < this.array.length;
    }
    public float nextFloat() {
        try {
            float[] fArr = this.array;
            int i = this.index;
            this.index = i + 1;
            return fArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.index--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
