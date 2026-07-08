package kotlin.jvm.internal;

import kotlin.reflect.*;


public abstract class MutablePropertyReference2 extends MutablePropertyReference implements KMutableProperty2 {
    public abstract Object get(Object obj, Object obj2);

    public abstract void set(Object obj, Object obj2, Object obj3);

    public MutablePropertyReference2() {
    }

    public MutablePropertyReference2(Class cls, String str, String str2, int i) {
        super(CallableReference.NO_RECEIVER, cls, str, str2, i);
    }

    protected KCallable computeReflected() {
        return Reflection.mutableProperty2(this);
    }

    public Object invoke(Object obj, Object obj2) {
        return get(obj, obj2);
    }

    public KProperty2.Getter getGetter() {
        ((KMutableProperty2) getReflected()).getGetter();
        return null;
    }
    public Setter getSetter() {
        ((KMutableProperty2) getReflected()).getSetter();
        return null;
    }
    public Object getDelegate(Object obj, Object obj2) {
        return ((KMutableProperty2) getReflected()).getDelegate(obj, obj2);
    }
}
