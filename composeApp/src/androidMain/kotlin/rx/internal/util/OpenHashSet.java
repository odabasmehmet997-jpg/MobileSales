package rx.internal.util;

import rx.internal.util.unsafe.Pow2;

public final class OpenHashSet<T> {
    T[] keys;
    final float loadFactor;
    int mask;
    int maxSize;
    int size;
    static int mix(int i2) {
        int i3 = i2 * (-1640531527);
        return i3 ^ (i3 >>> 16);
    }
    public OpenHashSet() {
        this(16, 0.75f);
    }
    public OpenHashSet(int i2, float f2) {
        this.loadFactor = f2;
        int iRoundToPowerOfTwo = Pow2.roundToPowerOfTwo(i2);
        this.mask = iRoundToPowerOfTwo - 1;
        this.maxSize = (int) (f2 * iRoundToPowerOfTwo);
        this.keys = (T[]) new Object[iRoundToPowerOfTwo];
    }
    public boolean add(T t) {
        T t2;
        T[] tArr = this.keys;
        int i2 = this.mask;
        int iMix = mix(t.hashCode()) & i2;
        T t3 = tArr[iMix];
        if (t3 != null) {
            if (t3.equals(t)) {
                return false;
            }
            do {
                iMix = (iMix + 1) & i2;
                t2 = tArr[iMix];
                if (t2 == null) {
                }
            } while (!t2.equals(t));
            return false;
        }
        tArr[iMix] = t;
        int i3 = this.size + 1;
        this.size = i3;
        if (i3 >= this.maxSize) {
            rehash();
        }
        return true;
    }
    public boolean remove(T t) {
        T t2;
        T[] tArr = this.keys;
        int i2 = this.mask;
        int iMix = mix(t.hashCode()) & i2;
        T t3 = tArr[iMix];
        if (t3 == null) {
            return false;
        }
        if (t3.equals(t)) {
            return removeEntry(iMix, tArr, i2);
        }
        do {
            iMix = (iMix + 1) & i2;
            t2 = tArr[iMix];
            if (t2 == null) {
                return false;
            }
        } while (!t2.equals(t));
        return removeEntry(iMix, tArr, i2);
    }
    boolean removeEntry(int i2, T[] tArr, int i3) {
        int i4;
        T t;
        this.size--;
        while (true) {
            int i5 = i2 + 1;
            while (true) {
                i4 = i5 & i3;
                t = tArr[i4];
                if (t == null) {
                    tArr[i2] = null;
                    return true;
                }
                int iMix = mix(t.hashCode()) & i3;
                if (i2 <= i4) {
                    if (i2 >= iMix || iMix > i4) {
                        break;
                    }
                    i5 = i4 + 1;
                } else if (i2 < iMix || iMix <= i4) {
                    i5 = i4 + 1;
                }
            }
            tArr[i2] = t;
            i2 = i4;
        }
    }
    public void terminate() {
        this.size = 0;
        this.keys = (T[]) new Object[0];
    }
    void rehash() {
        T t;
        T[] tArr = this.keys;
        int length = tArr.length;
        int i2 = length << 1;
        int i3 = i2 - 1;
        T[] tArr2 = (T[]) new Object[i2];
        int i4 = this.size;
        while (true) {
            int i5 = i4 - 1;
            if (i4 != 0) {
                do {
                    length--;
                    t = tArr[length];
                } while (t == null);
                int iMix = mix(t.hashCode()) & i3;
                if (tArr2[iMix] != null) {
                    do {
                        iMix = (iMix + 1) & i3;
                    } while (tArr2[iMix] != null);
                }
                tArr2[iMix] = tArr[length];
                i4 = i5;
            } else {
                this.mask = i3;
                this.maxSize = (int) (i2 * this.loadFactor);
                this.keys = tArr2;
                return;
            }
        }
    }
    public boolean isEmpty() {
        return this.size == 0;
    }
    public T[] values() {
        return this.keys;
    }
}
