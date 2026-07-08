package kotlin.jvm.internal;

import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KProperty;


public abstract class MutablePropertyReference extends PropertyReference implements KProperty {
    public abstract Getter getGetter();
    public abstract KMutableProperty2.Setter getSetter();
    public MutablePropertyReference() {
    }
    public MutablePropertyReference(Object obj) {
        super(obj);
    }
    public MutablePropertyReference(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
