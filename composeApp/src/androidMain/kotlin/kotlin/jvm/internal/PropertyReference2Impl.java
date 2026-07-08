package kotlin.jvm.internal;

import kotlin.coroutines.Continuation;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;
import kotlinx.coroutines.CoroutineScope;


public class PropertyReference2Impl extends PropertyReference2 {
    public PropertyReference2Impl(KDeclarationContainer kDeclarationContainer, String str, String str2) {
        super(((ClassBasedDeclarationContainer) kDeclarationContainer).getJClass(), str, str2, !(kDeclarationContainer instanceof KClass) ? 1 : 0);
    }
    public PropertyReference2Impl(Class cls, String str, String str2, int i) {
        super(cls, str, str2, i);
    }
    public Object get(Object obj, Object obj2) {
        getGetter();
        throw null;
    }
    public Object invoke(CoroutineScope p1, Continuation p2) {
        return null;
    }
}
