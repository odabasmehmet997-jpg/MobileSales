package okhttp3;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import static kotlin.jvm.internal.Intrinsics.stringPlus;

public enum TlsVersion {
    TLS_1_3("TLSv1.3"),
    TLS_1_2("TLSv1.2"),
    TLS_1_1("TLSv1.1"),
    TLS_1_0("TLSv1"),
    SSL_3_0("SSLv3");
    public static final Companion Companion = new Companion(null);
    private final String javaName;
    TlsVersion(final String str) {
        javaName = str;
    }
    public static final TlsVersion forJavaName(final String str) {
        return TlsVersion.Companion.forJavaName(str);
    }
    public final String javaName() {
        return javaName;
    }
    public final String m1848deprecated_javaName() {
        return javaName;
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
        public TlsVersion forJavaName(final String javaName) {
            Intrinsics.checkNotNullParameter(javaName, "javaName");
            final int iHashCode = javaName.hashCode();
            if (79201641 != iHashCode) {
                if (79923350 != iHashCode) {
                    switch (iHashCode) {
                        case -503070503:
                            if ("TLSv1.1".equals(javaName)) {
                                return TLS_1_1;
                            }
                            break;
                        case -503070502:
                            if ("TLSv1.2".equals(javaName)) {
                                return TLS_1_2;
                            }
                            break;
                        case -503070501:
                            if ("TLSv1.3".equals(javaName)) {
                                return TLS_1_3;
                            }
                            break;
                    }
                } else if ("TLSv1".equals(javaName)) {
                    return TLS_1_0;
                }
            } else if ("SSLv3".equals(javaName)) {
                return SSL_3_0;
            }
            throw new IllegalArgumentException(stringPlus("Unexpected TLS version: ", javaName));
        }
    }
}
