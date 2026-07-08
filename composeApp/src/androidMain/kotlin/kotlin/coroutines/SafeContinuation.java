package kotlin.coroutines;


import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

import kotlin.Result;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SafeContinuation<T> implements Continuation<T>, CoroutineStackFrame {
    private static final Companion Companion = new Companion(null);
    private static final AtomicReferenceFieldUpdater<SafeContinuation, Object> RESULT = AtomicReferenceFieldUpdater.newUpdater(SafeContinuation.class, Object.class, "result");
    private final Continuation<T> delegate;
    private final Object result;
    public StackTraceElement getStackTraceElement() {
        return null;
    }
    public SafeContinuation(Continuation<? super T> delegate, Object obj) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");

        this.delegate = ( Continuation<T> ) delegate;
        this.result = obj;
    }
    public SafeContinuation(Continuation<? super T> delegate) {
        this(delegate, CoroutineSingletons.UNDECIDED);
        Intrinsics.checkNotNullParameter(delegate, "delegate");
    }
    public CoroutineContext getContext() {
        return this.delegate.getContext();
    }
    private static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
    public void resumeWith(Object obj) {
        while (true) {
            Object obj2 = this.result;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.UNDECIDED;
            if (obj2 == coroutineSingletons) {

            } else {
                if (obj2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    throw new IllegalStateException("Already resumed");
                }

            }
        }
    }
    @Override
    public T invokeSuspend(T instance) {
        return null;
    }
    public Object getOrThrow() throws Throwable {
        Object obj = this.result;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.UNDECIDED;
        if (obj == coroutineSingletons) {
            if ( m16m(RESULT, this, coroutineSingletons, IntrinsicsKt.getCOROUTINE_SUSPENDED())) {
                return IntrinsicsKt.getCOROUTINE_SUSPENDED();
            }
            obj = this.result;
        }
        if (obj == CoroutineSingletons.RESUMED) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if (obj instanceof Result.Failure) {
            throw ((Result.Failure) obj).exception;
        }
        return obj;
    }
    private boolean m16m(AtomicReferenceFieldUpdater<SafeContinuation, Object> result, SafeContinuation<T> tSafeContinuation, CoroutineSingletons coroutineSingletons, Object coroutineSuspended) {
        return false;
    }
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.delegate;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }
    public String toString() {
        return "SafeContinuation for " + this.delegate;
    }
}
