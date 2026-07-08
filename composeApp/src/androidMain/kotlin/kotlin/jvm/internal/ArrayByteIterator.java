package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.ByteIterator;

public final class ArrayByteIterator extends ByteIterator {
    private final byte[] array;
    private int index;
    public ArrayByteIterator(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "array");
        this.array = bArr;
    }
    public boolean hasNext() {
        return this.index < this.array.length;
    }
    public byte nextByte() {
        try {
            byte[] bArr = this.array;
            int i = this.index;
            this.index = i + 1;
            return bArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.index--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
