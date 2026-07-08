package kotlin.coroutines;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;


public abstract class CoroutineContextImpl implements CoroutineContext.Element {
    private final Key<?> key;
    public CoroutineContextImpl(Key<?> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.key = key;
    }
    public <R> R fold(R r, Function2<? super R, ? super Element, ? extends R> function2) {
        return DefaultImpls.fold(this, r, function2);
    }
    public <E extends Element> E get(Key<E> key) {
        return DefaultImpls.get(this, key);
    }
    public CoroutineContext minusKey(Key<?> key) {
        return DefaultImpls.minusKey(this, key);
    }
    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return DefaultImpls.plus(this, coroutineContext);
    }
    public Key<?> getKey() {
        return this.key;
    }
}
