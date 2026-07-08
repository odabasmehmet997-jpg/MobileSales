package com.fasterxml.jackson.databind.util;

import java.io.Serializable;

public abstract class NameTransformer {
    public static final NameTransformer NOP = new NopTransformer();

    public abstract String reverse(String str);

    public abstract String transform(String str);

    protected static final class NopTransformer extends NameTransformer implements Serializable {
        private static final long serialVersionUID = 1;

        public String reverse(final String str) {
            return str;
        }

        public String transform(final String str) {
            return str;
        }

        protected NopTransformer() {
        }
    }

    protected NameTransformer() {
    }

    public static NameTransformer simpleTransformer(String str, String str2) {
        boolean z = false;
        final boolean z2 = null != str && !str.isEmpty();
        if (null != str2 && !str2.isEmpty()) {
            z = true;
        }
        if (z2) {
            if (z) {
                return new NameTransformer() { // from class: com.fasterxml.jackson.databind.util.NameTransformer.1
                    @Override // com.fasterxml.jackson.databind.util.NameTransformer
                    public String transform(final String str3) {
                        return str + str3 + str2;
                    }

                    @Override // com.fasterxml.jackson.databind.util.NameTransformer
                    public String reverse(final String str3) {
                        if (!str3.startsWith(str)) {
                            return null;
                        }
                        final String strSubstring = str3.substring(str.length());
                        if (strSubstring.endsWith(str2)) {
                            return strSubstring.substring(0, strSubstring.length() - str2.length());
                        }
                        return null;
                    }

                    public String toString() {
                        return "[PreAndSuffixTransformer('" + str + "','" + str2 + "')]";
                    }
                };
            }
            return new NameTransformer() { // from class: com.fasterxml.jackson.databind.util.NameTransformer.2
                @Override // com.fasterxml.jackson.databind.util.NameTransformer
                public String transform(final String str3) {
                    return str + str3;
                }

                @Override // com.fasterxml.jackson.databind.util.NameTransformer
                public String reverse(final String str3) {
                    if (str3.startsWith(str)) {
                        return str3.substring(str.length());
                    }
                    return null;
                }

                public String toString() {
                    return "[PrefixTransformer('" + str + "')]";
                }
            };
        }
        if (z) {
            return new NameTransformer() { // from class: com.fasterxml.jackson.databind.util.NameTransformer.3
                @Override // com.fasterxml.jackson.databind.util.NameTransformer
                public String transform(final String str3) {
                    return str3 + str2;
                }

                @Override // com.fasterxml.jackson.databind.util.NameTransformer
                public String reverse(final String str3) {
                    if (str3.endsWith(str2)) {
                        return str3.substring(0, str3.length() - str2.length());
                    }
                    return null;
                }

                public String toString() {
                    return "[SuffixTransformer('" + str2 + "')]";
                }
            };
        }
        return NameTransformer.NOP;
    }

    public static NameTransformer chainedTransformer(final NameTransformer nameTransformer, final NameTransformer nameTransformer2) {
        return new Chained(nameTransformer, nameTransformer2);
    }

    public static class Chained extends NameTransformer implements Serializable {
        private static final long serialVersionUID = 1;
        protected final NameTransformer _t1;
        protected final NameTransformer _t2;

        public Chained(final NameTransformer nameTransformer, final NameTransformer nameTransformer2) {
            _t1 = nameTransformer;
            _t2 = nameTransformer2;
        }

        @Override // com.fasterxml.jackson.databind.util.NameTransformer
        public String transform(final String str) {
            return _t1.transform(_t2.transform(str));
        }

        @Override // com.fasterxml.jackson.databind.util.NameTransformer
        public String reverse(final String str) {
            final String strReverse = _t1.reverse(str);
            return null != strReverse ? _t2.reverse(strReverse) : strReverse;
        }

        public String toString() {
            return "[ChainedTransformer(" + _t1 + ", " + _t2 + ")]";
        }
    }
}
