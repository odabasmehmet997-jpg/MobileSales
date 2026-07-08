package okhttp3;

import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;

import javax.net.ssl.SSLSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class ConnectionSpec {
    public static final ConnectionSpec CLEARTEXT;
    public static final ConnectionSpec COMPATIBLE_TLS;
    public static final Companion Companion = new Companion(null);
    public static final ConnectionSpec MODERN_TLS;
    public static final ConnectionSpec RESTRICTED_TLS;
    private static final CipherSuite[] APPROVED_CIPHER_SUITES;
    private static final CipherSuite[] RESTRICTED_CIPHER_SUITES;
    static {
        final CipherSuite cipherSuite = CipherSuite.TLS_AES_128_GCM_SHA256;
        final CipherSuite cipherSuite2 = CipherSuite.TLS_AES_256_GCM_SHA384;
        final CipherSuite cipherSuite3 = CipherSuite.TLS_CHACHA20_POLY1305_SHA256;
        final CipherSuite cipherSuite4 = CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256;
        final CipherSuite cipherSuite5 = CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256;
        final CipherSuite cipherSuite6 = CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384;
        final CipherSuite cipherSuite7 = CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384;
        final CipherSuite cipherSuite8 = CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256;
        final CipherSuite cipherSuite9 = CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256;
        final CipherSuite[] cipherSuiteArr = {cipherSuite, cipherSuite2, cipherSuite3, cipherSuite4, cipherSuite5, cipherSuite6, cipherSuite7, cipherSuite8, cipherSuite9};
        RESTRICTED_CIPHER_SUITES = cipherSuiteArr;
        final CipherSuite[] cipherSuiteArr2 = {cipherSuite, cipherSuite2, cipherSuite3, cipherSuite4, cipherSuite5, cipherSuite6, cipherSuite7, cipherSuite8, cipherSuite9, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA};
        APPROVED_CIPHER_SUITES = cipherSuiteArr2;
        final Builder builderCipherSuites = new Builder(true).cipherSuites(Arrays.copyOf(cipherSuiteArr, cipherSuiteArr.length));
        final TlsVersion tlsVersion = TlsVersion.TLS_1_3;
        final TlsVersion tlsVersion2 = TlsVersion.TLS_1_2;
        RESTRICTED_TLS = builderCipherSuites.tlsVersions(tlsVersion, tlsVersion2).supportsTlsExtensions(true).build();
        MODERN_TLS = new Builder(true).cipherSuites(Arrays.copyOf(cipherSuiteArr2, cipherSuiteArr2.length)).tlsVersions(tlsVersion, tlsVersion2).supportsTlsExtensions(true).build();
        COMPATIBLE_TLS = new Builder(true).cipherSuites(Arrays.copyOf(cipherSuiteArr2, cipherSuiteArr2.length)).tlsVersions(tlsVersion, tlsVersion2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0).supportsTlsExtensions(true).build();
        CLEARTEXT = new Builder(false).build();
    }
    private final String[] cipherSuitesAsString;
    private final boolean isTls;
    private final boolean supportsTlsExtensions;
    private final String[] tlsVersionsAsString;
    public ConnectionSpec(final boolean z, final boolean z2, final String[] strArr, final String[] strArr2) {
        isTls = z;
        supportsTlsExtensions = z2;
        cipherSuitesAsString = strArr;
        tlsVersionsAsString = strArr2;
    }
    public boolean isTls() {
        return isTls;
    }
    public boolean supportsTlsExtensions() {
        return supportsTlsExtensions;
    }
    public List<CipherSuite> cipherSuites() {
        final String[] strArr = cipherSuitesAsString;
        if (null == strArr) {
            return null;
        }
        final ArrayList arrayList = new ArrayList(strArr.length);
        for (final String str : strArr) {
            arrayList.add(CipherSuite.Companion.forJavaName(str));
        }
        return CollectionsKt.toList(arrayList);
    }
    public List<CipherSuite> deprecated_cipherSuites() {
        return this.cipherSuites();
    }
    public List<TlsVersion> tlsVersions() {
        final String[] strArr = tlsVersionsAsString;
        if (null == strArr) {
            return null;
        }
        final ArrayList arrayList = new ArrayList(strArr.length);
        for (final String str : strArr) {
            arrayList.add(TlsVersion.Companion.forJavaName(str));
        }
        return CollectionsKt.toList(arrayList);
    }
    public List<TlsVersion> deprecated_tlsVersions() {
        return this.tlsVersions();
    }
    public boolean deprecated_supportsTlsExtensions() {
        return supportsTlsExtensions;
    }
    public void applyokhttp(final SSLSocket sslSocket, final boolean z) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        final ConnectionSpec connectionSpecSupportedSpec = this.supportedSpec(sslSocket, z);
        if (null != connectionSpecSupportedSpec.tlsVersions())
            sslSocket.setEnabledProtocols(connectionSpecSupportedSpec.tlsVersionsAsString());
        if (null != connectionSpecSupportedSpec.cipherSuites()) {
            sslSocket.setEnabledCipherSuites(connectionSpecSupportedSpec.cipherSuitesAsString());
        }
    }
    private String[] cipherSuitesAsString() {
        return new String[0];
    }
    private String[] tlsVersionsAsString() {
        return new String[0];
    }
    private ConnectionSpec supportedSpec(final SSLSocket sSLSocket, final boolean z) {
        String[] cipherSuitesIntersection;
        final String[] tlsVersionsIntersection;
        if (null != this.cipherSuitesAsString) {
            final String[] enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
            checkNotNullExpressionValue(enabledCipherSuites, "sslSocket.enabledCipherSuites");
            cipherSuitesIntersection = Util.intersect(enabledCipherSuites, cipherSuitesAsString, CipherSuite.Companion.getORDER_BY_NAMEokhttp());
        } else {
            cipherSuitesIntersection = sSLSocket.getEnabledCipherSuites();
        }
        if (null != this.tlsVersionsAsString) {
            final String[] enabledProtocols = sSLSocket.getEnabledProtocols();
            checkNotNullExpressionValue(enabledProtocols, "sslSocket.enabledProtocols");
            tlsVersionsIntersection = Util.intersect(enabledProtocols, tlsVersionsAsString, ComparisonsKt.naturalOrder());
        } else {
            tlsVersionsIntersection = sSLSocket.getEnabledProtocols();
        }
        final String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        checkNotNullExpressionValue(supportedCipherSuites, "supportedCipherSuites");
        final int iIndexOf = Util.indexOf(supportedCipherSuites, "TLS_FALLBACK_SCSV", CipherSuite.Companion.getORDER_BY_NAMEokhttp());
        if (z && -1 != iIndexOf) {
            checkNotNullExpressionValue(cipherSuitesIntersection, "cipherSuitesIntersection");
            final String str = supportedCipherSuites[iIndexOf];
            checkNotNullExpressionValue(str, "supportedCipherSuites[indexOfFallbackScsv]");
            cipherSuitesIntersection = Util.concat(cipherSuitesIntersection, str);
        }
        final Builder builder = new Builder(this);
        checkNotNullExpressionValue(cipherSuitesIntersection, "cipherSuitesIntersection");
        final Builder builderCipherSuites = builder.cipherSuites(Arrays.copyOf(cipherSuitesIntersection, cipherSuitesIntersection.length));
        checkNotNullExpressionValue(tlsVersionsIntersection, "tlsVersionsIntersection");
        return builderCipherSuites.tlsVersions(Arrays.copyOf(tlsVersionsIntersection, tlsVersionsIntersection.length)).build();
    }
    public boolean isCompatible(final SSLSocket socket) {
        Intrinsics.checkNotNullParameter(socket, "socket");
        if (!isTls) {
            return false;
        }
        final String[] strArr = tlsVersionsAsString;
        if (null != strArr && !Util.hasIntersection(strArr, socket.getEnabledProtocols(), ComparisonsKt.naturalOrder())) {
            return false;
        }
        final String[] strArr2 = cipherSuitesAsString;
        return null == strArr2 || Util.hasIntersection(strArr2, socket.getEnabledCipherSuites(), CipherSuite.Companion.getORDER_BY_NAMEokhttp());
    }
    public boolean equals(final Object obj) {
        if (!(obj instanceof ConnectionSpec connectionSpec)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        final boolean z = isTls;
        if (z != connectionSpec.isTls) {
            return false;
        }
        return !z || (Arrays.equals(cipherSuitesAsString, connectionSpec.cipherSuitesAsString) && Arrays.equals(tlsVersionsAsString, connectionSpec.tlsVersionsAsString) && supportsTlsExtensions == connectionSpec.supportsTlsExtensions);
    }
    public int hashCode() {
        if (!isTls) {
            return 17;
        }
        final String[] strArr = cipherSuitesAsString;
        final int iHashCode = (527 + (null == strArr ? 0 : Arrays.hashCode(strArr))) * 31;
        final String[] strArr2 = tlsVersionsAsString;
        return ((iHashCode + (null != strArr2 ? Arrays.hashCode(strArr2) : 0)) * 31) + (!supportsTlsExtensions ? 1 : 0);
    }
    public String toString() {
        if (!isTls) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + Objects.toString(this.cipherSuites(), "[all enabled]") + ", tlsVersions=" + Objects.toString(this.tlsVersions(), "[all enabled]") + ", supportsTlsExtensions=" + supportsTlsExtensions + ')';
    }
    public void apply(SSLSocket sslSocket, boolean z) {
    }
    public static final class Builder {
        private String[] cipherSuites;
        private boolean supportsTlsExtensions;
        private boolean tls;
        private String[] tlsVersions;

        public Builder(final boolean z) {
            tls = z;
        }

        public Builder(final ConnectionSpec connectionSpec) {
            Intrinsics.checkNotNullParameter(connectionSpec, "connectionSpec");
            tls = connectionSpec.isTls();
            cipherSuites = connectionSpec.cipherSuitesAsString;
            tlsVersions = connectionSpec.tlsVersionsAsString;
            supportsTlsExtensions = connectionSpec.supportsTlsExtensions();
        }

        public boolean getTlsokhttp() {
            return tls;
        }

        public void setTlsokhttp(final boolean z) {
            tls = z;
        }

        public String[] getCipherSuitesokhttp() {
            return cipherSuites;
        }

        public void setCipherSuitesokhttp(final String[] strArr) {
            cipherSuites = strArr;
        }

        public String[] getTlsVersionsokhttp() {
            return tlsVersions;
        }

        public void setTlsVersionsokhttp(final String[] strArr) {
            tlsVersions = strArr;
        }

        public boolean getSupportsTlsExtensionsokhttp() {
            return supportsTlsExtensions;
        }

        public void setSupportsTlsExtensionsokhttp(final boolean z) {
            supportsTlsExtensions = z;
        }

        public Builder allEnabledCipherSuites() {
            if (!this.tls) {
                throw new IllegalArgumentException("no cipher suites for cleartext connections");
            }
            this.cipherSuites = null;
            return this;
        }

        public Builder cipherSuites(final CipherSuite... cipherSuites) {
            Intrinsics.checkNotNullParameter(cipherSuites, "cipherSuites");
            if (!this.tls) {
                throw new IllegalArgumentException("no cipher suites for cleartext connections");
            }
            final ArrayList arrayList = new ArrayList(cipherSuites.length);
            for (final CipherSuite cipherSuite : cipherSuites) {
                arrayList.add(cipherSuite.javaName());
            }
            final Object[] array = arrayList.toArray(new String[0]);
            if (null == array) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            }
            final String[] strArr = (String[]) array;
            return this.cipherSuites(Arrays.copyOf(strArr, strArr.length));
        }

        public Builder cipherSuites(final String... cipherSuites) {
            Intrinsics.checkNotNullParameter(cipherSuites, "cipherSuites");
            if (!this.tls) {
                throw new IllegalArgumentException("no cipher suites for cleartext connections");
            }
            if (0 == cipherSuites.length) {
                throw new IllegalArgumentException("At least one cipher suite is required");
            }
            this.cipherSuites = cipherSuites.clone();
            return this;
        }

        public Builder allEnabledTlsVersions() {
            if (!this.tls) {
                throw new IllegalArgumentException("no TLS versions for cleartext connections");
            }
            this.tlsVersions = null;
            return this;
        }

        public Builder tlsVersions(final TlsVersion... tlsVersions) {
            Intrinsics.checkNotNullParameter(tlsVersions, "tlsVersions");
            if (!this.tls) {
                throw new IllegalArgumentException("no TLS versions for cleartext connections");
            }
            final ArrayList arrayList = new ArrayList(tlsVersions.length);
            for (final TlsVersion tlsVersion : tlsVersions) {
                arrayList.add(tlsVersion.javaName());
            }
            final Object[] array = arrayList.toArray(new String[0]);
            if (null == array) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            }
            final String[] strArr = (String[]) array;
            return this.tlsVersions(Arrays.copyOf(strArr, strArr.length));
        }

        public Builder tlsVersions(final String... tlsVersions) {
            Intrinsics.checkNotNullParameter(tlsVersions, "tlsVersions");
            if (!this.tls) {
                throw new IllegalArgumentException("no TLS versions for cleartext connections");
            }
            if (0 == tlsVersions.length) {
                throw new IllegalArgumentException("At least one TLS version is required");
            }
            this.tlsVersions = tlsVersions.clone();
            return this;
        }

        public Builder supportsTlsExtensions(final boolean z) {
            if (!this.tls) {
                throw new IllegalArgumentException("no TLS extensions for cleartext connections");
            }
            this.supportsTlsExtensions = z;
            return this;
        }

        public ConnectionSpec build() {
            return new ConnectionSpec(tls, supportsTlsExtensions, cipherSuites, tlsVersions);
        }
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
