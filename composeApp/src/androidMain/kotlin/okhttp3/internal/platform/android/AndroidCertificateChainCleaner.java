package okhttp3.internal.platform.android;

import android.net.http.X509TrustManagerExtensions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.SuppressSignatureCheck;
import okhttp3.internal.tls.CertificateChainCleaner;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class AndroidCertificateChainCleaner extends CertificateChainCleaner {
    public static final Companion Companion = new Companion(null);
    private final X509TrustManager trustManager;
    private final X509TrustManagerExtensions x509TrustManagerExtensions;

    public AndroidCertificateChainCleaner(final X509TrustManager trustManager, final X509TrustManagerExtensions x509TrustManagerExtensions) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        Intrinsics.checkNotNullParameter(x509TrustManagerExtensions, "x509TrustManagerExtensions");
        this.trustManager = trustManager;
        this.x509TrustManagerExtensions = x509TrustManagerExtensions;
    }
    public List<X509Certificate> clean(final List<? extends Certificate> chain, final String hostname) throws SSLPeerUnverifiedException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        final Object[] array = chain.toArray(new X509Certificate[0]);
        if (null == array) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        try {
            final List<X509Certificate> listCheckServerTrusted = x509TrustManagerExtensions.checkServerTrusted((X509Certificate[]) array, "RSA", hostname);
            checkNotNullExpressionValue(listCheckServerTrusted, "x509TrustManagerExtensio\u2026ficates, \"RSA\", hostname)");
            return listCheckServerTrusted;
        } catch (final CertificateException e2) {
            final SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e2.getMessage(), e2);
            throw sSLPeerUnverifiedException;
        }
    }

    public boolean equals(final Object obj) {
        return (obj instanceof AndroidCertificateChainCleaner) && ((AndroidCertificateChainCleaner) obj).trustManager == trustManager;
    }

    public int hashCode() {
        return System.identityHashCode(trustManager);
    }

    /* compiled from: AndroidCertificateChainCleaner.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @SuppressSignatureCheck
        public AndroidCertificateChainCleaner buildIfSupported(final X509TrustManager trustManager) {
            X509TrustManagerExtensions x509TrustManagerExtensions;
            Intrinsics.checkNotNullParameter(trustManager, "trustManager");
            try {
                x509TrustManagerExtensions = new X509TrustManagerExtensions(trustManager);
            } catch (final IllegalArgumentException unused) {
                x509TrustManagerExtensions = null;
            }
            if (null != x509TrustManagerExtensions) {
                return new AndroidCertificateChainCleaner(trustManager, x509TrustManagerExtensions);
            }
            return null;
        }
    }
}
