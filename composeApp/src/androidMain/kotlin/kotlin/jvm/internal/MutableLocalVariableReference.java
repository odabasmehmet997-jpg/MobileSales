package kotlin.jvm.internal;

import kotlin.reflect.KDeclarationContainer;

public class MutableLocalVariableReference extends MutablePropertyReference0 {
    public KDeclarationContainer getOwner() {
        Void unused = LocalVariableReferencesKt.notSupportedError();
        return null;
    }
    public Object get() {
        Void unused = LocalVariableReferencesKt.notSupportedError();

        return null;
    }
    public void set(Object obj) {
        Void unused = LocalVariableReferencesKt.notSupportedError();
    }
}
