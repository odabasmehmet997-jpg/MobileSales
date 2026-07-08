package okio;

import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import java.util.Arrays;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;
import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class Segment {
    public static final Companion Companion = new Companion(null);
    public final byte[] data;
    public int limit;
    public Segment next;
    public boolean owner;
    public int pos;
    public Segment prev;
    public boolean shared;
    public Segment() {
        data = new byte[8192];
        owner = true;
        shared = false;
    }
    public Segment(final byte[] data, final int i2, final int i3, final boolean z, final boolean z2) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
        pos = i2;
        limit = i3;
        shared = z;
        owner = z2;
    }
    public Segment sharedCopy() {
        shared = true;
        return new Segment(data, pos, limit, true, false);
    }

    public Segment unsharedCopy() {
        final byte[] bArr = data;
        final byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        checkNotNullExpressionValue(bArrCopyOf, "copyOf(this, size)");
        return new Segment(bArrCopyOf, pos, limit, false, true);
    }

    public Segment pop() {
        Segment segment = next;
        if (segment == this) {
            segment = null;
        }
        final Segment segment2 = prev;
        checkNotNull(segment2);
        segment2.next = next;
        final Segment segment3 = next;
        checkNotNull(segment3);
        segment3.prev = prev;
        next = null;
        prev = null;
        return segment;
    }

    public Segment push(final Segment segment) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        segment.prev = this;
        segment.next = next;
        final Segment segment2 = next;
        checkNotNull(segment2);
        segment2.prev = segment;
        next = segment;
        return segment;
    }

    public Segment split(final int i2) {
        final Segment segmentTake;
        if (0 >= i2 || i2 > limit - pos) {
            throw new IllegalArgumentException("byteCount out of range");
        }
        if (1024 <= i2) {
            segmentTake = this.sharedCopy();
        } else {
            segmentTake = SegmentPool.take();
            final byte[] bArr = data;
            final byte[] bArr2 = segmentTake.data;
            final int i3 = pos;
            ArraysKt.copyInto(bArr, bArr2, 0, i3, i3 + i2);
        }
        segmentTake.limit = segmentTake.pos + i2;
        pos += i2;
        final Segment segment = prev;
        checkNotNull(segment);
        segment.push(segmentTake);
        return segmentTake;
    }

    public void compact() {
        final int i2;
        final Segment segment = prev;
        if (segment == this) {
            throw new IllegalStateException("cannot compact");
        }
        checkNotNull(segment);
        if (segment.owner) {
            final int i3 = limit - pos;
            final Segment segment2 = prev;
            checkNotNull(segment2);
            final int i4 = 8192 - segment2.limit;
            final Segment segment3 = prev;
            checkNotNull(segment3);
            if (segment3.shared) {
                i2 = 0;
            } else {
                final Segment segment4 = prev;
                checkNotNull(segment4);
                i2 = segment4.pos;
            }
            if (i3 > i4 + i2) {
                return;
            }
            final Segment segment5 = prev;
            checkNotNull(segment5);
            this.writeTo(segment5, i3);
            this.pop();
            SegmentPool.recycle(this);
        }
    }

    public void writeTo(final Segment sink, final int i2) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (!sink.owner) {
            throw new IllegalStateException("only owner can write");
        }
        final int i3 = sink.limit;
        if (8192 < i3 + i2) {
            if (sink.shared) {
                throw new IllegalArgumentException();
            }
            final int i4 = sink.pos;
            if (8192 < (i3 + i2) - i4) {
                throw new IllegalArgumentException();
            }
            final byte[] bArr = sink.data;
            ArraysKt.copyInto(bArr, bArr, 0, i4, i3);
            sink.limit -= sink.pos;
            sink.pos = 0;
        }
        final byte[] bArr2 = data;
        final byte[] bArr3 = sink.data;
        final int i5 = sink.limit;
        final int i6 = pos;
        ArraysKt.copyInto(bArr2, bArr3, i5, i6, i6 + i2);
        sink.limit += i2;
        pos += i2;
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
