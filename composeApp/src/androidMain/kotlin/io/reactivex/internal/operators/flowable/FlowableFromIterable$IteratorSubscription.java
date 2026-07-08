package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.Exceptions;
import org.reactivestreams.Subscriber;

import java.util.Iterator;



final class FlowableFromIterableIteratorSubscription<T> extends FlowableFromIterableBaseRangeSubscription<T> {
    private static final long serialVersionUID = -6022804456014692607L;
    final Subscriber<? super T> downstream;

    FlowableFromIterableIteratorSubscription(Subscriber<? super T> subscriber, Iterator<? extends T> it) {
        super(it);
        this.downstream = subscriber;
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableFromIterableBaseRangeSubscription
    void fastPath() {
        Iterator<? extends T> it = this.it;
        Subscriber<? super T> subscriber = this.downstream;
        while (!this.cancelled) {
            try {
                T next = it.next();
                if (this.cancelled) {
                    return;
                }
                if (null == next) {
                    subscriber.onError(new NullPointerException("Iterator.next() returned a null value"));
                    return;
                }
                subscriber.onNext(next);
                if (this.cancelled) {
                    return;
                }
                try {
                    if (!it.hasNext()) {
                        if (this.cancelled) {
                            return;
                        }
                        subscriber.onComplete();
                        return;
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    subscriber.onError(th);
                    return;
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                subscriber.onError(th2);
                return;
            }
        }
    }

    void slowPath(long j2) {
        Iterator<? extends T> it = this.it;
        Subscriber<? super T> subscriber = this.downstream;
        do {
            long j3 = 0;
            while (true) {
                if (j3 != j2) {
                    if (this.cancelled) {
                        return;
                    }
                    try {
                        T next = it.next();
                        if (this.cancelled) {
                            return;
                        }
                        if (null == next) {
                            subscriber.onError(new NullPointerException("Iterator.next() returned a null value"));
                            return;
                        }
                        subscriber.onNext(next);
                        if (this.cancelled) {
                            return;
                        }
                        try {
                            if (!it.hasNext()) {
                                if (this.cancelled) {
                                    return;
                                }
                                subscriber.onComplete();
                                return;
                            }
                            j3++;
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            subscriber.onError(th);
                            return;
                        }
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        subscriber.onError(th2);
                        return;
                    }
                } else {
                    j2 = this.get();
                    if (j3 == j2) {
                        break;
                    }
                }
            }
        } while (0 != j2);
    }
}
