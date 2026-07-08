package rx.internal.util;

import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import rx.exceptions.CompositeException;
public enum ExceptionsUtils {
    ;

    private static final Throwable TERMINATED = new Throwable("Terminated");

    public static boolean addThrowable(AtomicReference<Throwable> atomicReference, Throwable th) {
        Throwable th2;
        Throwable compositeException;
        do {
            th2 = atomicReference.get();
            if (th2 == TERMINATED) {
                return false;
            }
            if (th2 == null) {
                compositeException = th;
            } else if (th2 instanceof CompositeException) {
                ArrayList<Throwable> exceptions = (ArrayList<Throwable>) ((CompositeException) th2).getExceptions();
                exceptions.add(th);
                compositeException = new CompositeException(exceptions);
            } else {
                compositeException = new CompositeException(th2, th);
            }
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(atomicReference, th2, compositeException));
        return true;
    }

    public static Throwable terminate(AtomicReference<Throwable> atomicReference) {
        Throwable th = atomicReference.get();
        Throwable th2 = TERMINATED;
        return th != th2 ? atomicReference.getAndSet(th2) : th;
    }

    public static boolean isTerminated(AtomicReference<Throwable> atomicReference) {
        return isTerminated(atomicReference.get());
    }

    public static boolean isTerminated(Throwable th) {
        return th == TERMINATED;
    }
}
