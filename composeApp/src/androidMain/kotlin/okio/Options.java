package okio;

import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;
import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class Options extends AbstractList<ByteString> implements RandomAccess {
    public static final Companion Companion = new Companion(null);
    private final ByteString[] byteStrings;
    private final int[] trie;
    public Options(final ByteString[] byteStringArr, final int[] iArr, final DefaultConstructorMarker defaultConstructorMarker) {
        this(byteStringArr, iArr);
    }
    public boolean contains(final Object obj) {
        if (obj instanceof ByteString) {
            return this.contains((ByteString) obj);
        }
        return false;
    }
    public boolean contains(final ByteString byteString) {
        return super.contains(byteString);
    }
    public int indexOf(final Object obj) {
        if (obj instanceof ByteString) {
            return this.indexOf((ByteString) obj);
        }
        return -1;
    }
    public int indexOf(final ByteString byteString) {
        return super.indexOf(byteString);
    }
    public int lastIndexOf(final Object obj) {
        if (obj instanceof ByteString) {
            return this.lastIndexOf((ByteString) obj);
        }
        return -1;
    }
    public int lastIndexOf(final ByteString byteString) {
        return super.lastIndexOf(byteString);
    }
    public ByteString[] getByteStringsokio() {
        return byteStrings;
    }
    public int[] getTrieokio() {
        return trie;
    }
    private Options(final ByteString[] byteStringArr, final int[] iArr) {
        byteStrings = byteStringArr;
        trie = iArr;
    }
    public int getSize() {
        return byteStrings.length;
    }
    public ByteString get(final int i2) {
        return byteStrings[i2];
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private Companion() {
        }
        public Options m659of(final ByteString... byteStrings) throws IOException {
            Intrinsics.checkNotNullParameter(byteStrings, "byteStrings");
            final DefaultConstructorMarker defaultConstructorMarker = null;
            int i2 = 0;
            if (0 == byteStrings.length) {
                return new Options(new ByteString[0], new int[]{0, -1}, defaultConstructorMarker);
            }
            final List mutableList = ArraysKt.toMutableList(byteStrings);
            CollectionsKt.sort(mutableList);
            final ArrayList arrayList = new ArrayList(byteStrings.length);
            for (final ByteString byteString : byteStrings) {
                arrayList.add(-1);
            }
            final Object[] array = arrayList.toArray(new Integer[0]);
            if (null == array) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            }
            final Integer[] numArr = (Integer[]) array;
            final List listMutableListOf = CollectionsKt.mutableListOf(Arrays.copyOf(numArr, numArr.length));
            final int length = byteStrings.length;
            int i3 = 0;
            int i4 = 0;
            while (i3 < length) {
                listMutableListOf.set(CollectionsKt.binarySearch(mutableList, byteStrings[i3], 0, 0), Integer.valueOf(i4));
                i3++;
                i4++;
            }
            if (0 >= ((ByteString) mutableList.get(0)).size()) {
                throw new IllegalArgumentException("the empty byte string is not a supported option");
            }
            int i5 = 0;
            while (i5 < mutableList.size()) {
                final ByteString byteString2 = (ByteString) mutableList.get(i5);
                final int i6 = i5 + 1;
                int i7 = i6;
                while (i7 < mutableList.size()) {
                    final ByteString byteString3 = (ByteString) mutableList.get(i7);
                    if (byteString3.startsWith(byteString2)) {
                        if (byteString3.size() == byteString2.size()) {
                            throw new IllegalArgumentException(("duplicate option: " + byteString3));
                        }
                        if (((Number) listMutableListOf.get(i7)).intValue() > ((Number) listMutableListOf.get(i5)).intValue()) {
                            mutableList.remove(i7);
                            listMutableListOf.remove(i7);
                        } else {
                            i7++;
                        }
                    }
                }
                i5 = i6;
            }
            final Buffer buffer = new Buffer();
            buildTrieRecursivedefault(this, 0L, buffer, 0, mutableList, 0, 0, listMutableListOf, 53, null);
            final int[] iArr = new int[(int) this.getIntCount(buffer)];
            while (!buffer.exhausted()) {
                iArr[i2] = buffer.readInt();
                i2++;
            }
            final Object[] objArrCopyOf = Arrays.copyOf(byteStrings, byteStrings.length);
            checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, size)");
            return new Options((ByteString[]) objArrCopyOf, iArr, defaultConstructorMarker);
        }

        static void buildTrieRecursivedefault(final Companion companion, final long j2, final Buffer buffer, final int i2, final List list, final int i3, final int i4, final List list2, final int i5, final Object obj) throws IOException {
            companion.buildTrieRecursive(0 != (i5 & 1) ? 0L : j2, buffer, 0 != (i5 & 4) ? 0 : i2, list, 0 != (i5 & 16) ? 0 : i3, 0 != (i5 & 32) ? list.size() : i4, list2);
        }

        private void buildTrieRecursive(final long j2, final Buffer buffer, final int i2, final List<? extends ByteString> list, final int i3, final int i4, final List<Integer> list2) throws IOException {
            int i5;
            final int i6;
            int i7;
            int i8;
            Buffer buffer2;
            int i9 = i2;
            if (i3 >= i4) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            for (int i10 = i3; i10 < i4; i10++) {
                if (list.get(i10).size() < i9) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
            }
            ByteString byteString = list.get(i3);
            final ByteString byteString2 = list.get(i4 - 1);
            int i11 = -1;
            if (i9 == byteString.size()) {
                final int iIntValue = list2.get(i3).intValue();
                final int i12 = i3 + 1;
                final ByteString byteString3 = list.get(i12);
                i5 = i12;
                i6 = iIntValue;
                byteString = byteString3;
            } else {
                i5 = i3;
                i6 = -1;
            }
            if (byteString.getByte(i9) != byteString2.getByte(i9)) {
                int i13 = 1;
                for (int i14 = i5 + 1; i14 < i4; i14++) {
                    if (list.get(i14 - 1).getByte(i9) != list.get(i14).getByte(i9)) {
                        i13++;
                    }
                }
                final long intCount = j2 + this.getIntCount(buffer) + 2 + (i13 * 2L);
                buffer.writeInt(i13);
                buffer.writeInt(i6);
                for (int i15 = i5; i15 < i4; i15++) {
                    final byte b2 = list.get(i15).getByte(i9);
                    if (i15 == i5 || b2 != list.get(i15 - 1).getByte(i9)) {
                        buffer.writeInt(b2 & 255);
                    }
                }
                Buffer buffer3 = new Buffer();
                while (i5 < i4) {
                    final byte b3 = list.get(i5).getByte(i9);
                    final int i16 = i5 + 1;
                    int i17 = i16;
                    while (true) {
                        if (i17 >= i4) {
                            i7 = i4;
                            break;
                        } else {
                            if (b3 != list.get(i17).getByte(i9)) {
                                i7 = i17;
                                break;
                            }
                            i17++;
                        }
                    }
                    if (i16 == i7 && i9 + 1 == list.get(i5).size()) {
                        buffer.writeInt(list2.get(i5).intValue());
                        i8 = i7;
                        buffer2 = buffer3;
                    } else {
                        buffer.writeInt(((int) (intCount + this.getIntCount(buffer3))) * i11);
                        i8 = i7;
                        buffer2 = buffer3;
                        this.buildTrieRecursive(intCount, buffer3, i9 + 1, list, i5, i7, list2);
                    }
                    buffer3 = buffer2;
                    i5 = i8;
                    i11 = -1;
                }
                buffer.writeAll(buffer3);
                return;
            }
            final int iMin = Math.min(byteString.size(), byteString2.size());
            int i18 = 0;
            for (int i19 = i9; i19 < iMin && byteString.getByte(i19) == byteString2.getByte(i19); i19++) {
                i18++;
            }
            final long intCount2 = j2 + this.getIntCount(buffer) + 2 + i18 + 1;
            buffer.writeInt(-i18);
            buffer.writeInt(i6);
            final int i20 = i18 + i9;
            while (i9 < i20) {
                buffer.writeInt(byteString.getByte(i9) & 255);
                i9++;
            }
            if (i5 + 1 == i4) {
                if (i20 != list.get(i5).size()) {
                    throw new IllegalStateException("Check failed.");
                }
                buffer.writeInt(list2.get(i5).intValue());
            } else {
                final Buffer buffer4 = new Buffer();
                buffer.writeInt(((int) (this.getIntCount(buffer4) + intCount2)) * (-1));
                this.buildTrieRecursive(intCount2, buffer4, i20, list, i5, i4, list2);
                buffer.writeAll(buffer4);
            }
        }
        private long getIntCount(final Buffer buffer) {
            return buffer.size() / 4;
        }

        public Options of(ByteString efbbbf, ByteString feff, ByteString fffe, ByteString byteString, ByteString ffff0000) {
            return null;
        }
    }
}
