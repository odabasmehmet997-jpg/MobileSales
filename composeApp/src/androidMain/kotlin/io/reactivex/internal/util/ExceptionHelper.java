package io.reactivex.internal.util;

import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import io.reactivex.exceptions.CompositeException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;



public final class ExceptionHelper {
    public static final Throwable TERMINATED = new Termination();

    private ExceptionHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static RuntimeException wrapOrThrow(final Throwable th) {
        if (th instanceof Error) {
            throw ((Error) th);
        }
        if (th instanceof RuntimeException) {
            return (RuntimeException) th;
        }
        return new RuntimeException(th);
    }

    public static <T> boolean addThrowable(final AtomicReference<Throwable> atomicReference, final Throwable th) {
        Throwable th2;
        do {
            th2 = atomicReference.get();
            if (th2 == ExceptionHelper.TERMINATED) {
                return false;
            }
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(atomicReference, th2, null == th2 ? th : new CompositeException(th2, th)));
        return true;
    }

    public static <T> Throwable terminate(final AtomicReference<Throwable> atomicReference) {
        final Throwable th = atomicReference.get();
        final Throwable th2 = ExceptionHelper.TERMINATED;
        return th != th2 ? atomicReference.getAndSet(th2) : th;
    }

    public static String timeoutMessage(final long j2, final TimeUnit timeUnit) {
        return "The source did not signal an event for " + j2 + " " + timeUnit.toString().toLowerCase() + " and has been terminated.";
    }

    static final class Termination extends Throwable {
        private static final long serialVersionUID = -4649703670690200604L;

        @Override // java.lang.Throwable
        public Throwable fillInStackTrace() {
            return this;
        }

        Termination() {
            super("No further exceptions");
        }
    }
}
