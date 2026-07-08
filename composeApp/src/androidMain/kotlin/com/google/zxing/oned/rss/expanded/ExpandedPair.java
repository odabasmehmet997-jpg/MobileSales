package com.google.zxing.oned.rss.expanded;

import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;
import com.proje.mobilesales.core.sql.SqlLiteVariable;

final class ExpandedPair {
    private final FinderPattern finderPattern;
    private final DataCharacter leftChar;
    private final boolean mayBeLast;
    private final DataCharacter rightChar;

    ExpandedPair(final DataCharacter dataCharacter, final DataCharacter dataCharacter2, final FinderPattern finderPattern2, final boolean z) {
        leftChar = dataCharacter;
        rightChar = dataCharacter2;
        finderPattern = finderPattern2;
        mayBeLast = z;
    }

    
    public DataCharacter getLeftChar() {
        return leftChar;
    }

    
    public DataCharacter getRightChar() {
        return rightChar;
    }

    
    public FinderPattern getFinderPattern() {
        return finderPattern;
    }

    public boolean mustBeLast() {
        return null == this.rightChar;
    }

    public String toString() {
        final Object obj;
        final StringBuilder sb = new StringBuilder("[ ");
        sb.append(leftChar);
        sb.append(SqlLiteVariable._COMMA_SEP);
        sb.append(rightChar);
        sb.append(" : ");
        final FinderPattern finderPattern2 = finderPattern;
        if (null == finderPattern2) {
            obj = "null";
        } else {
            obj = Integer.valueOf(finderPattern2.getValue());
        }
        sb.append(obj);
        sb.append(" ]");
        return sb.toString();
    }

    public boolean equals(final Object obj) {
        if (!(obj instanceof ExpandedPair expandedPair)) {
            return false;
        }
        return ExpandedPair.equalsOrNull(leftChar, expandedPair.leftChar) && ExpandedPair.equalsOrNull(rightChar, expandedPair.rightChar) && ExpandedPair.equalsOrNull(finderPattern, expandedPair.finderPattern);
    }

    private static boolean equalsOrNull(final Object obj, final Object obj2) {
        if (null == obj) {
            return null == obj2;
        }
        return obj.equals(obj2);
    }

    public int hashCode() {
        return (ExpandedPair.hashNotNull(leftChar) ^ ExpandedPair.hashNotNull(rightChar)) ^ ExpandedPair.hashNotNull(finderPattern);
    }

    private static int hashNotNull(final Object obj) {
        if (null == obj) {
            return 0;
        }
        return obj.hashCode();
    }
}
