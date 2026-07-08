package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import java.util.Iterator;



final class FlowableFromIterableIteratorConditionalSubscription<T> extends FlowableFromIterableBaseRangeSubscription<T> {
    private static final long serialVersionUID = -6022804456014692607L;
    final ConditionalSubscriber<? super T> downstream;

    FlowableFromIterableIteratorConditionalSubscription(ConditionalSubscriber<? super T> conditionalSubscriber, Iterator<? extends T> it) {
        super(it);
        this.downstream = conditionalSubscriber;
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableFromIterableBaseRangeSubscription
    void fastPath() {
        Iterator<? extends T> it = this.it;
        ConditionalSubscriber<? super T> conditionalSubscriber = this.downstream;
        while (!this.cancelled) {
            try {
                T next = it.next();
                if (this.cancelled) {
                    return;
                }
                if (null == next) {
                    conditionalSubscriber.onError(new NullPointerException("Iterator.next() returned a null value"));
                    return;
                }
                conditionalSubscriber.tryOnNext(next);
                if (this.cancelled) {
                    return;
                }
                try {
                    if (!it.hasNext()) {
                        if (this.cancelled) {
                            return;
                        }
                        conditionalSubscriber.onComplete();
                        return;
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    conditionalSubscriber.onError(th);
                    return;
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                conditionalSubscriber.onError(th2);
                return;
            }
        }
    }

    void slowPath(long j2) {
        Iterator<? extends T> it = this.it;
        ConditionalSubscriber<? super T> conditionalSubscriber = this.downstream;
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
                            conditionalSubscriber.onError(new NullPointerException("Iterator.next() returned a null value"));
                            return;
                        }
                        boolean tryOnNext = conditionalSubscriber.tryOnNext(next);
                        if (this.cancelled) {
                            return;
                        }
                        try {
                            if (!it.hasNext()) {
                                if (this.cancelled) {
                                    return;
                                }
                                conditionalSubscriber.onComplete();
                                return;
                            } else if (tryOnNext) {
                                j3++;
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            conditionalSubscriber.onError(th);
                            return;
                        }
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        conditionalSubscriber.onError(th2);
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
