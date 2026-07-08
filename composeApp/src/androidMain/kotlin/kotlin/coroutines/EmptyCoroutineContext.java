package kotlin.coroutines;

import java.io.Serializable;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;


public final class EmptyCoroutineContext implements CoroutineContext, Serializable {
    public static final EmptyCoroutineContext INSTANCE = new EmptyCoroutineContext();
    private static final long serialVersionUID = 0;
    public <R> R fold(R r, Function2<? super R, ? super Element, ? extends R> function2) {
        Intrinsics.checkNotNullParameter(function2, "operation");
        return r;
    }
    public <E extends Element> E get(Key<E> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return null;
    }
    public int hashCode() {
        return 0;
    }
    public CoroutineContext minusKey(Key<?> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this;
    }
    public CoroutineContext plus(CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(coroutineContext, "context");
        return coroutineContext;
    }
    private EmptyCoroutineContext() {
    }
    private Object readResolve() {
        return INSTANCE;
    }
    public String toString() {
        return "EmptyCoroutineContext";
    }
}
