package kotlin.coroutines;

import kotlin.jvm.internal.Intrinsics;
import org.kxml2.kdom.Element;


public interface ContinuationInterceptor extends CoroutineContext.Element {
    Key Key = ContinuationInterceptor.Key.INSTANCE;
    <T> Continuation<T> interceptContinuation(Continuation<? super T> continuation);
    void releaseInterceptedContinuation(Continuation<?> continuation);
    final class Key implements CoroutineContext.Key<ContinuationInterceptor> {
        static final Key INSTANCE = new Key();

        private Key() {
        }
    }
    final class DefaultImpls {
        public static <E extends Element> E get(ContinuationInterceptor continuationInterceptor, CoroutineContext.Key<E> key) {
            E e;
            Intrinsics.checkNotNullParameter(key, "key");
            if (key instanceof AbstractCoroutineContextKey abstractCoroutineContextKey) {
                if (!abstractCoroutineContextKey.isSubKeykotlin_stdlib(continuationInterceptor.getKey()) || (e = (E) abstractCoroutineContextKey.tryCastkotlin_stdlib(( org.kxml2.kdom.Element ) continuationInterceptor)) == null) {
                    return null;
                }
                return e;
            } else if (ContinuationInterceptor.Key != key) {
                return null;
            } else {
                Intrinsics.checkNotNull(continuationInterceptor, "null cannot be cast to non-null type E of kotlin.coroutines.ContinuationInterceptor.get");
                return ( E ) continuationInterceptor;
            }
        }

        public static CoroutineContext minusKey(ContinuationInterceptor continuationInterceptor, CoroutineContext.Key<?> key) {
            Intrinsics.checkNotNullParameter(key, "key");
            if (!(key instanceof AbstractCoroutineContextKey abstractCoroutineContextKey)) {
                return ContinuationInterceptor.Key == key ? EmptyCoroutineContext.INSTANCE : continuationInterceptor;
            }
            return (!abstractCoroutineContextKey.isSubKeykotlin_stdlib(continuationInterceptor.getKey()) || abstractCoroutineContextKey.tryCastkotlin_stdlib(( org.kxml2.kdom.Element ) continuationInterceptor) == null) ? continuationInterceptor : EmptyCoroutineContext.INSTANCE;
        }
    }
}
