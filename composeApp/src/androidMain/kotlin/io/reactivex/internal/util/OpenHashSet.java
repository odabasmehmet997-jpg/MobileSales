package io.reactivex.internal.util;



public final class OpenHashSet<T> {
    T[] keys;
    final float loadFactor;
    int mask;
    int maxSize;
    int size;

    static int mix(final int i2) {
        final int i3 = i2 * (-1640531527);
        return i3 ^ (i3 >>> 16);
    }

    public OpenHashSet() {
        this(16, 0.75f);
    }

    public OpenHashSet(final int i2, final float f2) {
        loadFactor = f2;
        final int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(i2);
        mask = roundToPowerOfTwo - 1;
        maxSize = (int) (f2 * roundToPowerOfTwo);
        keys = (T[]) new Object[roundToPowerOfTwo];
    }

    public boolean add(final T t) {
        T t2;
        final T[] tArr = keys;
        final int i2 = mask;
        int mix = OpenHashSet.mix(t.hashCode()) & i2;
        final T t3 = tArr[mix];
        if (null != t3) {
            if (t3.equals(t)) {
                return false;
            }
            do {
                mix = (mix + 1) & i2;
                t2 = tArr[mix];
                if (null == t2) {
                }
            } while (!t2.equals(t));
            return false;
        }
        tArr[mix] = t;
        final int i3 = size + 1;
        size = i3;
        if (i3 >= maxSize) {
            this.rehash();
        }
        return true;
    }

    public boolean remove(final T t) {
        T t2;
        final T[] tArr = keys;
        final int i2 = mask;
        int mix = OpenHashSet.mix(t.hashCode()) & i2;
        final T t3 = tArr[mix];
        if (null == t3) {
            return false;
        }
        if (t3.equals(t)) {
            return this.removeEntry(mix, tArr, i2);
        }
        do {
            mix = (mix + 1) & i2;
            t2 = tArr[mix];
            if (null == t2) {
                return false;
            }
        } while (!t2.equals(t));
        return this.removeEntry(mix, tArr, i2);
    }

    boolean removeEntry(int i2, final T[] tArr, final int i3) {
        int i4;
        T t;
        size--;
        while (true) {
            int i5 = i2 + 1;
            while (true) {
                i4 = i5 & i3;
                t = tArr[i4];
                if (null == t) {
                    tArr[i2] = null;
                    return true;
                }
                final int mix = OpenHashSet.mix(t.hashCode()) & i3;
                if (i2 > i4) {
                    if (i2 >= mix && mix > i4) {
                        break;
                    }
                    i5 = i4 + 1;
                } else if (i2 < mix && mix <= i4) {
                    i5 = i4 + 1;
                }
            }
            tArr[i2] = t;
            i2 = i4;
        }
    }

    void rehash() {
        T t;
        final T[] tArr = keys;
        int length = tArr.length;
        final int i2 = length << 1;
        final int i3 = i2 - 1;
        final T[] tArr2 = (T[]) new Object[i2];
        int i4 = size;
        while (true) {
            final int i5 = i4 - 1;
            if (0 != i4) {
                do {
                    length--;
                    t = tArr[length];
                } while (null == t);
                int mix = OpenHashSet.mix(t.hashCode()) & i3;
                if (null != tArr2[mix]) {
                    do {
                        mix = (mix + 1) & i3;
                    } while (null != tArr2[mix]);
                }
                tArr2[mix] = tArr[length];
                i4 = i5;
            } else {
                mask = i3;
                maxSize = (int) (i2 * loadFactor);
                keys = tArr2;
                return;
            }
        }
    }

    public Object[] keys() {
        return keys;
    }

    public int size() {
        return size;
    }
}
