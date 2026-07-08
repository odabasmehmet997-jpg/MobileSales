package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty0;


public abstract class PropertyReference0 extends PropertyReference implements KProperty0 {
    public abstract Object get();
    public PropertyReference0() {
    }
    public PropertyReference0(Object obj) {
        super(obj);
    }
    public PropertyReference0(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
    protected KCallable computeReflected() {
        return Reflection.property0(this);
    }
    public Object invoke() {
        return get();
    }
    public KProperty0.Getter getGetter() {
        ((KProperty0) getReflected()).getGetter();
        return null;
    }
    public Object getDelegate() {
        return ((KProperty0) getReflected()).getDelegate();
    }
}
