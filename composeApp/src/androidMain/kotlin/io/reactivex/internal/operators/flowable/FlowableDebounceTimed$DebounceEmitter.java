package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableDebounceTimedDebounceEmitter<T> extends AtomicReference<Disposable> implements Runnable, Disposable {
    private static final long serialVersionUID = 6812032969491025141L;
    final long idx;
    final AtomicBoolean once = new AtomicBoolean();
    final FlowableDebounceTimedDebounceTimedSubscriber<T> parent;
    final T value;

    FlowableDebounceTimedDebounceEmitter(T t, long j2, FlowableDebounceTimedDebounceTimedSubscriber<T> flowableDebounceTimedDebounceTimedSubscriber) {
        this.value = t;
        this.idx = j2;
        this.parent = flowableDebounceTimedDebounceTimedSubscriber;
    }

    @Override // java.lang.Runnable
    public void run() {
        emit();
    }

    void emit() {
        if (this.once.compareAndSet(false, true)) {
            this.parent.emit(this.idx, this.value, this);
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.DISPOSED == this.get();
    }

    public void setResource(Disposable disposable) {
        DisposableHelper.replace(this, disposable);
    }
}
