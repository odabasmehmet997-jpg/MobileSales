package okio.internal;

import kotlin.jvm.internal.Intrinsics;
import okio.SegmentedByteString;

public class _SegmentedByteStringKt {

    public static int binarySearch(final int[] iArr, final int i2, int i3, final int i4) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int i5 = i4 - 1;
        while (i3 <= i5) {
            final int i6 = (i3 + i5) >>> 1;
            final int i7 = iArr[i6];
            if (i7 < i2) {
                i3 = i6 + 1;
            } else {
                if (i7 <= i2) {
                    return i6;
                }
                i5 = i6 - 1;
            }
        }
        return (-i3) - 1;
    }
    public static int segment(final SegmentedByteString segmentedByteString, final int i2) {
        Intrinsics.checkNotNullParameter(segmentedByteString, "<this>");
        final int iBinarySearch = _SegmentedByteStringKt.binarySearch(segmentedByteString.getDirectoryokio(), i2 + 1, 0, segmentedByteString.getSegmentsokio().length);
        return 0 <= iBinarySearch ? iBinarySearch : ~iBinarySearch;
    }

}
