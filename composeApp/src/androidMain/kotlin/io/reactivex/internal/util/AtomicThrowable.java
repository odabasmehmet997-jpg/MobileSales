package io.reactivex.internal.util;

import java.io.Serial;
import java.util.concurrent.atomic.AtomicReference;


public final class AtomicThrowable extends AtomicReference<Throwable> {
    @Serial
    private static final long serialVersionUID = 3949248817947090603L;

    public boolean addThrowable(final Throwable th) {
        return ExceptionHelper.addThrowable(this, th);
    }

    public Throwable terminate() {
        return ExceptionHelper.terminate(this);
    }

    public boolean isTerminated() {
        return this.get() == ExceptionHelper.TERMINATED;
    }
}
