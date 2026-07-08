package kotlin.jvm.internal;

import kotlin.coroutines.Continuation;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;
import kotlinx.coroutines.CoroutineScope;


public class MutablePropertyReference2Impl extends MutablePropertyReference2 {
    public MutablePropertyReference2Impl(KDeclarationContainer kDeclarationContainer, String str, String str2) {
        super(((ClassBasedDeclarationContainer) kDeclarationContainer).getJClass(), str, str2, !(kDeclarationContainer instanceof KClass) ? 1 : 0);
    }
    public MutablePropertyReference2Impl(Class cls, String str, String str2, int i) {
        super(cls, str, str2, i);
    }
    public Object get(Object obj, Object obj2) {
        getGetter();
        throw null;
    }
    public void set(Object obj, Object obj2, Object obj3) {
        getSetter();
        throw null;
    }
    public Object invoke(CoroutineScope p1, Continuation p2) {
        return null;
    }
}
