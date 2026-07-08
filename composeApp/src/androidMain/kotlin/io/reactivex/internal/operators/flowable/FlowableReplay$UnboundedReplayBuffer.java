package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.util.NotificationLite;
import org.reactivestreams.Subscriber;

import java.util.ArrayList;



final class FlowableReplayUnboundedReplayBuffer<T> extends ArrayList<Object> implements FlowableReplayReplayBuffer<T> {
    private static final long serialVersionUID = 7063189396499112664L;
    volatile int size;

    FlowableReplayUnboundedReplayBuffer(int i2) {
        super(i2);
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableReplayReplayBuffer
    public void next(T t) {
        add(NotificationLite.next(t));
        this.size++;
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableReplayReplayBuffer
    public void error(Throwable th) {
        add(NotificationLite.error(th));
        this.size++;
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableReplayReplayBuffer
    public void complete() {
        add(NotificationLite.complete());
        this.size++;
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableReplayReplayBuffer
    public void replay(FlowableReplayInnerSubscription<T> flowableReplayInnerSubscription) {
        synchronized (flowableReplayInnerSubscription) {
            if (flowableReplayInnerSubscription.emitting) {
                flowableReplayInnerSubscription.missed = true;
                return;
            }
            flowableReplayInnerSubscription.emitting = true;
            final Subscriber<? super T> subscriber = flowableReplayInnerSubscription.child;
            while (!flowableReplayInnerSubscription.isDisposed()) {
                final int i2 = size;
                final Integer num = flowableReplayInnerSubscription.index();
                int intValue = null != num ? num.intValue() : 0;
                final long j2 = flowableReplayInnerSubscription.get();
                long j3 = j2;
                long j4 = 0;
                while (0 != j3 && intValue < i2) {
                    final Object obj = this.get(intValue);
                    try {
                        if (NotificationLite.accept(obj, subscriber) || flowableReplayInnerSubscription.isDisposed()) {
                            return;
                        }
                        intValue++;
                        j3--;
                        j4++;
                    } catch (final Throwable th) {
                        Exceptions.throwIfFatal(th);
                        flowableReplayInnerSubscription.dispose();
                        if (NotificationLite.isError(obj) || NotificationLite.isComplete(obj)) {
                            return;
                        }
                        subscriber.onError(th);
                        return;
                    }
                }
                if (0 != j4) {
                    flowableReplayInnerSubscription.index = Integer.valueOf(intValue);
                    if (LocationRequestCompat.PASSIVE_INTERVAL != j2) {
                        flowableReplayInnerSubscription.produced(j4);
                    }
                }
                synchronized (flowableReplayInnerSubscription) {
                    if (!flowableReplayInnerSubscription.missed) {
                        flowableReplayInnerSubscription.emitting = false;
                        return;
                    }
                    flowableReplayInnerSubscription.missed = false;
                }
            }
        }
    }
}
