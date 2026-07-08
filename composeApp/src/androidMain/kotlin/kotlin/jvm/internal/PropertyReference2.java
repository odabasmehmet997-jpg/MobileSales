package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty2;


public abstract class PropertyReference2 extends PropertyReference implements KProperty2 {
    public abstract Object get(Object obj, Object obj2);

    public PropertyReference2() {
    }

    public PropertyReference2(Class cls, String str, String str2, int i) {
        super(CallableReference.NO_RECEIVER, cls, str, str2, i);
    }
    protected KCallable computeReflected() {
        return Reflection.property2(this);
    }

    public Object invoke(Object obj, Object obj2) {
        return get(obj, obj2);
    }

    public KProperty2.Getter getGetter() {
        ((KProperty2) getReflected()).getGetter();
        return null;
    }

    public Object getDelegate(Object obj, Object obj2) {
        return ((KProperty2) getReflected()).getDelegate(obj, obj2);
    }
}
