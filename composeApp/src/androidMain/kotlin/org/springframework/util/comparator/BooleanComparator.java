package org.springframework.util.comparator;

import java.io.Serializable;
import java.util.Comparator;

public final class BooleanComparator implements Comparator<Boolean>, Serializable {
    private final boolean trueLow;
    public static final BooleanComparator TRUE_LOW = new BooleanComparator(true);
    public static final BooleanComparator TRUE_HIGH = new BooleanComparator(false);
    public BooleanComparator(boolean z) {
        this.trueLow = z;
    }
    public int compare(Boolean bool, Boolean bool2) {
        if (bool2.booleanValue() ^ bool.booleanValue()) {
            return bool.booleanValue() ^ this.trueLow ? 1 : -1;
        }
        return 0;
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BooleanComparator) && this.trueLow == ((BooleanComparator) obj).trueLow;
    }
    public int hashCode() {
        return (this.trueLow ? -1 : 1) * BooleanComparator.class.hashCode();
    }
    public String toString() {
        final String sb = "BooleanComparator: " +
                (this.trueLow ? "true low" : "true high");
        return sb;
    }
}
