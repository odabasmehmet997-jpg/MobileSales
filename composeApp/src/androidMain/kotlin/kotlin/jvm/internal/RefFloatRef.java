package kotlin.jvm.internal;

import java.io.Serializable;


public final class RefFloatRef implements Serializable {
    public float element;

    public String toString() {
        return String.valueOf(this.element);
    }
}
