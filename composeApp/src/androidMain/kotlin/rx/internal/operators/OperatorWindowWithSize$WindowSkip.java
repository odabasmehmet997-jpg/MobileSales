package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subjects.Subject;
import rx.subjects.UnicastSubject;


final class OperatorWindowWithSize$WindowSkip<T> extends Subscriber<T> implements Action0 {
    final Subscriber<? super Observable<T>> actual;
    int index;
    final int size;
    final int skip;
    Subject<T, T> window;
    final AtomicInteger wip;

    @Override // rx.Observer
    public void onNext(Object t) {
        int i2 = this.index;
        UnicastSubject unicastSubjectCreate = this.window;
        if (i2 == 0) {
            this.wip.getAndIncrement();
            unicastSubjectCreate = UnicastSubject.create(this.size, this);
            this.window = unicastSubjectCreate;
            this.actual.onNext(unicastSubjectCreate);
        }
        int i3 = i2 + 1;
        if (unicastSubjectCreate != null) {
            unicastSubjectCreate.onNext(t);
        }
        if (i3 == this.size) {
            this.index = i3;
            this.window = null;
            unicastSubjectCreate.onCompleted();
        } else if (i3 == this.skip) {
            this.index = 0;
        } else {
            this.index = i3;
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        Subject<T, T> subject = this.window;
        if (subject != null) {
            this.window = null;
            subject.onError(th);
        }
        this.actual.onError(th);
    }

    @Override // rx.Observer
    public void onCompleted() {
        Subject<T, T> subject = this.window;
        if (subject != null) {
            this.window = null;
            subject.onCompleted();
        }
        this.actual.onCompleted();
    }

    @Override // rx.functions.Action0
    public void call() {
        if (this.wip.decrementAndGet() == 0) {
            unsubscribe();
        }
    }

    final class WindowSkipProducer extends AtomicBoolean implements Producer {
        private static final long serialVersionUID = 4625807964358024108L;

        WindowSkipProducer() {
        }

        @Override // rx.Producer
        public void request(long j2) {
            if (j2 < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j2);
            }
            if (j2 != 0) {
                OperatorWindowWithSize$WindowSkip operatorWindowWithSize$WindowSkip = OperatorWindowWithSize$WindowSkip.this;
                if (get() || !compareAndSet(false, true)) {
                    operatorWindowWithSize$WindowSkip.request(BackpressureUtils.multiplyCap(j2, operatorWindowWithSize$WindowSkip.skip));
                } else {
                    operatorWindowWithSize$WindowSkip.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(j2, operatorWindowWithSize$WindowSkip.size), BackpressureUtils.multiplyCap(operatorWindowWithSize$WindowSkip.skip - operatorWindowWithSize$WindowSkip.size, j2 - 1)));
                }
            }
        }
    }
}
