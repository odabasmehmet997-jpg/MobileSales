package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.internal.observers.BasicIntQueueDisposable;



public final class ObservableRange extends Observable<Integer> {
    private final long end;
    private final int start;

    public ObservableRange(int i2, int i3) {
        this.start = i2;
        this.end = i2 + i3;
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super Integer> observer) {
        RangeDisposable rangeDisposable = new RangeDisposable(observer, this.start, this.end);
        observer.onSubscribe(rangeDisposable);
        rangeDisposable.run();
    }

    static final class RangeDisposable extends BasicIntQueueDisposable<Integer> {
        private static final long serialVersionUID = 396518478098735504L;
        final Observer<? super Integer> downstream;
        final long end;
        boolean fused;
        long index;

        RangeDisposable(Observer<? super Integer> observer, long j2, long j3) {
            this.downstream = observer;
            this.index = j2;
            this.end = j3;
        }

        void run() {
            if (this.fused) {
                return;
            }
            Observer<? super Integer> observer = this.downstream;
            long j2 = this.end;
            for (long j3 = this.index; j3 != j2 && 0 == this.get(); j3++) {
                observer.onNext(Integer.valueOf((int) j3));
            }
            if (0 == this.get()) {
                this.lazySet(1);
                observer.onComplete();
            }
        }

        @Override // io.reactivex.internal.observers.BasicIntQueueDisposable, io.reactivex.internal.fuseable.SimpleQueue
        public Integer poll() throws Exception {
            long j2 = this.index;
            if (j2 != this.end) {
                this.index = 1 + j2;
                return Integer.valueOf((int) j2);
            }
            this.lazySet(1);
            return null;
        }

        @Override // io.reactivex.internal.observers.BasicIntQueueDisposable, io.reactivex.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return this.index == this.end;
        }

        @Override // io.reactivex.internal.observers.BasicIntQueueDisposable, io.reactivex.internal.fuseable.SimpleQueue
        public void clear() {
            this.index = this.end;
            this.lazySet(1);
        }

        @Override // io.reactivex.internal.observers.BasicIntQueueDisposable, io.reactivex.disposables.Disposable
        public void dispose() {
            this.set(1);
        }

        @Override // io.reactivex.internal.observers.BasicIntQueueDisposable, io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return 0 != this.get();
        }

        @Override // io.reactivex.internal.observers.BasicIntQueueDisposable, io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int i2) {
            if (0 == (i2 & 1)) {
                return 0;
            }
            this.fused = true;
            return 1;
        }
    }
}
