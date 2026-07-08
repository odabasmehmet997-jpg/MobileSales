package kotlin.jvm.internal;

import kotlin.reflect.*;


public abstract class MutablePropertyReference1 extends MutablePropertyReference implements KMutableProperty1 {
    public abstract Object get(Object obj);

    public abstract void set(Object obj, Object obj2);

    public MutablePropertyReference1() {
    }

    public MutablePropertyReference1(Object obj) {
        super(obj);
    }

    public MutablePropertyReference1(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }

    protected KCallable computeReflected() {
        return Reflection.mutableProperty1(this);
    }

    public Object invoke(Object obj) {
        return get(obj);
    }

    public KProperty1.Getter getGetter() {
        ((KMutableProperty1) getReflected()).getGetter();
        return null;
    }
    public Setter getSetter() {
        ((KMutableProperty1) getReflected()).getSetter();
        return null;
    }
    public Object getDelegate(Object obj) {
        return ((KMutableProperty1) getReflected()).getDelegate(obj);
    }
}
