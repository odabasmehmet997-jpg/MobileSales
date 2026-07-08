package okio;

import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import kotlin.jvm.internal.Intrinsics;

import java.util.concurrent.atomic.AtomicReference;

public final class SegmentPool {
    private static final int HASH_BUCKET_COUNT;
    private static final AtomicReference<Segment>[] hashBuckets;
    public static final SegmentPool INSTANCE = new SegmentPool();
    private static final int MAX_SIZE = 65536;
    private static final Segment LOCK = new Segment(new byte[0], 0, 0, false, false);

    private SegmentPool() {
    }

    static {
        final int iHighestOneBit = Integer.highestOneBit((Runtime.getRuntime().availableProcessors() * 2) - 1);
        HASH_BUCKET_COUNT = iHighestOneBit;
        final AtomicReference<Segment>[] atomicReferenceArr = new AtomicReference[iHighestOneBit];
        for (int i2 = 0; i2 < iHighestOneBit; i2++) {
            atomicReferenceArr[i2] = new AtomicReference<>();
        }
        hashBuckets = atomicReferenceArr;
    }

    public static Segment take() {
        final AtomicReference<Segment> atomicReferenceFirstRef = SegmentPool.INSTANCE.firstRef();
        final Segment segment = SegmentPool.LOCK;
        final Segment andSet = atomicReferenceFirstRef.getAndSet(segment);
        if (andSet == segment) {
            return new Segment();
        }
        if (null == andSet) {
            atomicReferenceFirstRef.set(null);
            return new Segment();
        }
        atomicReferenceFirstRef.set(andSet.next);
        andSet.next = null;
        andSet.limit = 0;
        return andSet;
    }

    public static void recycle(final Segment segment) {
        final AtomicReference<Segment> atomicReferenceFirstRef;
        final Segment segment2;
        Intrinsics.checkNotNullParameter(segment, "segment");
        if (null != segment.next || null != segment.prev) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (segment.shared || (segment2 = (atomicReferenceFirstRef = SegmentPool.INSTANCE.firstRef()).get()) == SegmentPool.LOCK) {
            return;
        }
        final int i2 = null != segment2 ? segment2.limit : 0;
        if (SegmentPool.MAX_SIZE <= i2) {
            return;
        }
        segment.next = segment2;
        segment.pos = 0;
        segment.limit = i2 + 8192;
        if (LifecycleKtExternalSyntheticBackportWithForwarding0.m212m(atomicReferenceFirstRef, segment2, segment)) {
            return;
        }
        segment.next = null;
    }

    private AtomicReference<Segment> firstRef() {
        return SegmentPool.hashBuckets[(int) (Thread.currentThread().getId() & (SegmentPool.HASH_BUCKET_COUNT - 1))];
    }
}
