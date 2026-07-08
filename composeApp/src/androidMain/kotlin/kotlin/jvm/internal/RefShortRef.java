package kotlin.jvm.internal;

import java.io.Serializable;


public final class RefShortRef implements Serializable {
    public short element;

    public String toString() {
        return String.valueOf(this.element);
    }
}
