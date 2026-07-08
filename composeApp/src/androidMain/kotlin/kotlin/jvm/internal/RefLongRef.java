package kotlin.jvm.internal;

import java.io.Serializable;


public final class RefLongRef implements Serializable {
    public long element;

    public String toString() {
        return String.valueOf(this.element);
    }
}
