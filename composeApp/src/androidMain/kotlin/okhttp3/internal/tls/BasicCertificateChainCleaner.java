package okhttp3.internal.tls;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import javax.net.ssl.SSLPeerUnverifiedException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static kotlin.jvm.internal.Intrinsics.*;

public final class BasicCertificateChainCleaner extends CertificateChainCleaner {
    public static final Companion Companion = new Companion(null);
    private static final int MAX_SIGNERS = 9;
    private final TrustRootIndex trustRootIndex;

    public BasicCertificateChainCleaner(final TrustRootIndex trustRootIndex) {
        Intrinsics.checkNotNullParameter(trustRootIndex, "trustRootIndex");
        this.trustRootIndex = trustRootIndex;
    }
    public List<X509Certificate> clean(final List<? extends Certificate> chain, final String hostname) throws SSLPeerUnverifiedException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        final ArrayDeque arrayDeque = new ArrayDeque(chain);
        final ArrayList arrayList = new ArrayList();
        final Object objRemoveFirst = arrayDeque.removeFirst();
        checkNotNullExpressionValue(objRemoveFirst, "queue.removeFirst()");
        arrayList.add(objRemoveFirst);
        int i2 = 0;
        boolean z = false;
        while (9 > i2) {
            i2++;
            final X509Certificate x509Certificate = (X509Certificate) arrayList.get(arrayList.size() - 1);
            final X509Certificate x509CertificateFindByIssuerAndSignature = trustRootIndex.findByIssuerAndSignature(x509Certificate);
            if (null != x509CertificateFindByIssuerAndSignature) {
                if (1 < arrayList.size() || !areEqual(x509Certificate, x509CertificateFindByIssuerAndSignature)) {
                    arrayList.add(x509CertificateFindByIssuerAndSignature);
                }
                try {
                    if (this.verifySignature(x509CertificateFindByIssuerAndSignature, x509CertificateFindByIssuerAndSignature)) {
                        return arrayList;
                    }
                } catch (final NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                } catch (final SignatureException e) {
                    throw new RuntimeException(e);
                } catch (final InvalidKeyException e) {
                    throw new RuntimeException(e);
                } catch (final CertificateException e) {
                    throw new RuntimeException(e);
                } catch (final NoSuchProviderException e) {
                    throw new RuntimeException(e);
                }
                z = true;
            } else {
                final Iterator it = arrayDeque.iterator();
                checkNotNullExpressionValue(it, "queue.iterator()");
                while (it.hasNext()) {
                    final Object next = it.next();
                    if (null == next) {
                        throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
                    }
                    final X509Certificate x509Certificate2 = (X509Certificate) next;
                    try {
                        if (this.verifySignature(x509Certificate, x509Certificate2)) {
                            it.remove();
                            arrayList.add(x509Certificate2);
                        }
                    } catch (final NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    } catch (final SignatureException e) {
                        throw new RuntimeException(e);
                    } catch (final InvalidKeyException e) {
                        throw new RuntimeException(e);
                    } catch (final CertificateException e) {
                        throw new RuntimeException(e);
                    } catch (final NoSuchProviderException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (z) {
                    return arrayList;
                }
                throw new SSLPeerUnverifiedException(stringPlus("Failed to find a trusted cert that signed ", x509Certificate));
            }
        }
        throw new SSLPeerUnverifiedException(stringPlus("Certificate chain too long: ", arrayList));
    }

    private boolean verifySignature(final X509Certificate x509Certificate, final X509Certificate x509Certificate2) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException, NoSuchProviderException {
        if (!areEqual(x509Certificate.getIssuerDN(), x509Certificate2.getSubjectDN())) {
            return false;
        }
        try {
            x509Certificate.verify(x509Certificate2.getPublicKey());
            return true;
        } catch (final GeneralSecurityException unused) {
            return false;
        }
    }

    public int hashCode() {
        return trustRootIndex.hashCode();
    }

    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof BasicCertificateChainCleaner) && areEqual(((BasicCertificateChainCleaner) obj).trustRootIndex, trustRootIndex);
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
