package okhttp3.internal.tls;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.platform.Platform;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

public abstract class CertificateChainCleaner {
    public static final Companion Companion = new Companion(null);
    public abstract List<X509Certificate> clean(List<? extends Certificate> list, String str) throws SSLPeerUnverifiedException;
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private Companion() {
        }
        public CertificateChainCleaner get(X509TrustManager trustManager) {
            Intrinsics.checkNotNullParameter(trustManager, "trustManager");
            return Platform.Companion.get().buildCertificateChainCleaner(trustManager);
        }
        public CertificateChainCleaner get(X509Certificate... caCerts) {
            Intrinsics.checkNotNullParameter(caCerts, "caCerts");
            return new BasicCertificateChainCleaner(new BasicTrustRootIndex(Arrays.copyOf(caCerts, caCerts.length)));
        }
    }
}
