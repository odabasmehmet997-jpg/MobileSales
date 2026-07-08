package kotlin.jvm.internal;

import java.io.Serializable;


public final class RefByteRef implements Serializable {
    public boolean element;

    public String toString() {
        return String.valueOf((int) this.element);
    }
}
