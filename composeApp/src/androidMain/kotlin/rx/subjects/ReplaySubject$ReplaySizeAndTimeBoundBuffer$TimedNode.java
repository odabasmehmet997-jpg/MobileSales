package rx.subjects;

import java.util.concurrent.atomic.AtomicReference;
final class ReplaySubject$ReplaySizeAndTimeBoundBuffer$TimedNode<T> extends AtomicReference<ReplaySubject$ReplaySizeAndTimeBoundBuffer$TimedNode<T>> {
    private static final long serialVersionUID = 3713592843205853725L;
    final long timestamp;
    final T value;
    public ReplaySubject$ReplaySizeAndTimeBoundBuffer$TimedNode(T t, long j2) {
        this.value = t;
        this.timestamp = j2;
    }
}
