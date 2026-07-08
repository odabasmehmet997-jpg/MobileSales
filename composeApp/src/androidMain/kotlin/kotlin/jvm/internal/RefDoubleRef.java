package kotlin.jvm.internal;

import java.io.Serializable;


public final class RefDoubleRef implements Serializable {
    public double element;

    public String toString() {
        return String.valueOf(this.element);
    }
}
