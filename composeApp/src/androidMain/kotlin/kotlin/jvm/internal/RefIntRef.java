package kotlin.jvm.internal;

import java.io.Serializable;


public final class RefIntRef implements Serializable {
    public int element;

    public String toString() {
        return String.valueOf(this.element);
    }
}
