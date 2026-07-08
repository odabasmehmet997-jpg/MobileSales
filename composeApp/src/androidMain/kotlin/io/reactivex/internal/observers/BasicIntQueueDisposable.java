package io.reactivex.internal.observers;

import io.reactivex.internal.fuseable.QueueDisposable;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BasicIntQueueDisposable<T> extends AtomicInteger implements QueueDisposable<T> {
    private static final long serialVersionUID = -1001730202384742097L;
    public abstract void clear();
    public abstract void dispose(); 
    public abstract boolean isDisposed(); 
    public abstract boolean isEmpty();
    public abstract T poll() throws Exception; 
    public abstract int requestFusion(int i2); 
    public final boolean offer(final T t) {
        throw new UnsupportedOperationException("Should not be called");
    }
    public final boolean offer(final T t, final T t2) {
        throw new UnsupportedOperationException("Should not be called");
    }
}
