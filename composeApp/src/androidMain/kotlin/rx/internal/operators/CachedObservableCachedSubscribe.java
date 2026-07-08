package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Subscriber;

final class CachedObservableCachedSubscribe<T> extends AtomicBoolean implements Observable.OnSubscribe<T> {
    private static final long serialVersionUID = -2817751667698696782L;
    final CachedObservableCacheState<T> state;
    public CachedObservableCachedSubscribe(CachedObservableCacheState<T> cachedObservable$CacheState) {
        this.state = cachedObservable$CacheState;
    }
    public void call(Subscriber<? super T> subscriber) {
        new CachedObservable$ReplayProducer(subscriber, this.state);
    }
}
