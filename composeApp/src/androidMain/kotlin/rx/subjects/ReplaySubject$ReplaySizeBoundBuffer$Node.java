package rx.subjects;

import java.util.concurrent.atomic.AtomicReference;

final class ReplaySubject$ReplaySizeBoundBuffer$Node<T> extends AtomicReference<ReplaySubject$ReplaySizeBoundBuffer$Node<T>> {
    private static final long serialVersionUID = 3713592843205853725L;
    final T value;
    public ReplaySubject$ReplaySizeBoundBuffer$Node(T value) {
        this.value = value;
    }
    public String toString() {
        return "ReplaySubject$ReplaySizeBoundBuffer$Node[value=" + value + "]";
    }
}
