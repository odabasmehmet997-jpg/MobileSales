package kotlin.coroutines;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.kxml2.kdom.Element;

public abstract class AbstractCoroutineContextKey<B extends Element & CoroutineContext.Element, E extends B> implements CoroutineContext.Key<E> {
    private final Function1<Element, E> safeCast;
    private final CoroutineContext.Key<?> topmostKey;
    public AbstractCoroutineContextKey(CoroutineContext.Key<B> key, Function1<? super Element, ? extends E> function1) {
        Intrinsics.checkNotNullParameter(key, "baseKey");
        Intrinsics.checkNotNullParameter(function1, "safeCast");
        this.safeCast = ( Function1<Element, E> ) function1;
        this.topmostKey = key instanceof AbstractCoroutineContextKey ? (CoroutineContext.Key<B>) ((AbstractCoroutineContextKey) key).topmostKey : key;
    }
    public final Element tryCastkotlin_stdlib(Element element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.safeCast.invoke(element);
    }
    public boolean isSubKeykotlin_stdlib(CoroutineContext.Key<?> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return key == this || this.topmostKey == key;
    }
}
