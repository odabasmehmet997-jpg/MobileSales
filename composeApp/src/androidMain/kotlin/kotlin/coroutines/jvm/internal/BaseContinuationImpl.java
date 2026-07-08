package kotlin.coroutines.jvm.internal;

import java.io.Serializable;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.internal._FileSystemKtcommonListRecursively1;

public abstract class BaseContinuationImpl implements Continuation<Object>, CoroutineStackFrame, Serializable {
    private final Continuation<Object> completion;
    public abstract CoroutineContext getContext();
    public abstract Object invokeSuspend(Object obj);
    protected void releaseIntercepted() {
    }
    public BaseContinuationImpl() {
        this.completion = continuation;
    }
    public final Continuation<Object> getCompletion() {
        return this.completion;
    }
    public final void resumeWith(Object obj) {
        Object invokeSuspend = null;
        Continuation continuation = this;
        while (true) {
            DebugProbes.probeCoroutineResumed(continuation);
            BaseContinuationImpl baseContinuationImpl = (BaseContinuationImpl) continuation;
            Continuation continuation2 = baseContinuationImpl.completion;
            Intrinsics.checkNotNull(continuation2);
            try {
                invokeSuspend = baseContinuationImpl.invokeSuspend(obj);
            } catch (Throwable th) {
                Result.Companion companion = Result.Companion;
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            if (invokeSuspend != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                obj = Result.constructor-impl(invokeSuspend);
                baseContinuationImpl.releaseIntercepted();
                if (continuation2 instanceof BaseContinuationImpl) {
                    continuation = continuation2;
                } else {
                    continuation2.resumeWith(obj);
                    return;
                }
            } else {
                return;
            }
        }
    }
    public Continuation<Unit> create(Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        throw new UnsupportedOperationException("create(Continuation) has not been overridden");
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Continuation at ");
        Object stackTraceElement = getStackTraceElement();
        if (stackTraceElement == null) {
            stackTraceElement = getClass().getName();
        }
        sb.append(stackTraceElement);
        return sb.toString();
    }
    public CoroutineStackFrame getCallerFrame() {
        Continuation<Object> continuation = this.completion;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }
    public StackTraceElement getStackTraceElement() {
        return DebugMetadataKt.getStackTraceElement(this);
    }
    public abstract Object invoke(Object p0);
}
