package okhttp3.internal.tls;

import kotlin.jvm.internal.Intrinsics;

import javax.security.auth.x500.X500Principal;
import java.security.cert.X509Certificate;
import java.util.*;

import static kotlin.jvm.internal.Intrinsics.areEqual;
import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class BasicTrustRootIndex implements TrustRootIndex {
    private final Map<X500Principal, Set<X509Certificate>> subjectToCaCerts;
    public BasicTrustRootIndex(final X509Certificate... caCerts) {
        Intrinsics.checkNotNullParameter(caCerts, "caCerts");
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        final int length = caCerts.length;
        int i2 = 0;
        while (i2 < length) {
            final X509Certificate x509Certificate = caCerts[i2];
            i2++;
            final X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
            checkNotNullExpressionValue(subjectX500Principal, "caCert.subjectX500Principal");
            Object linkedHashSet = linkedHashMap.get(subjectX500Principal);
            if (null == linkedHashSet) {
                linkedHashSet = new LinkedHashSet();
                linkedHashMap.put(subjectX500Principal, linkedHashSet);
            }
            ((Set) linkedHashSet).add(x509Certificate);
        }
        subjectToCaCerts = linkedHashMap;
    }
    public X509Certificate findByIssuerAndSignature(final X509Certificate cert) {
        Intrinsics.checkNotNullParameter(cert, "cert");
        final Set<X509Certificate> set = subjectToCaCerts.get(cert.getIssuerX500Principal());
        Object obj = null;
        if (null == set) {
            return null;
        }
        final Iterator<X509Certificate> it = set.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            final Object next = it.next();
            try {
                cert.verify(((X509Certificate) next).getPublicKey());
                obj = next;
                break;
            } catch (final Exception unused) {
            }
        }
        return (X509Certificate) obj;
    }
    public boolean equals(final Object obj) {
        return obj == this || ((obj instanceof BasicTrustRootIndex) && areEqual(((BasicTrustRootIndex) obj).subjectToCaCerts, subjectToCaCerts));
    }
    public int hashCode() {
        return subjectToCaCerts.hashCode();
    }
}
