package okhttp3;

import androidx.webkit.ProxyConfig;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;

import javax.net.ssl.SSLPeerUnverifiedException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static kotlin.jvm.internal.Intrinsics.*;

public final class CertificatePinner {
    public static final Companion Companion = new Companion(null);
    public static final CertificatePinner DEFAULT = new Builder().build();
    private final CertificateChainCleaner certificateChainCleaner;
    private final Set<Pin> pins;

    public CertificatePinner(final Set<Pin> pins, final CertificateChainCleaner certificateChainCleaner) {
        Intrinsics.checkNotNullParameter(pins, "pins");
        this.pins = pins;
        this.certificateChainCleaner = certificateChainCleaner;
    }

    public CertificatePinner(final Set set, final CertificateChainCleaner certificateChainCleaner, final int i2, final DefaultConstructorMarker defaultConstructorMarker) {
        this(set, 0 != (i2 & 2) ? null : certificateChainCleaner);
    }

    public static String pin(final Certificate certificate) {
        return CertificatePinner.Companion.pin(certificate);
    }

    public static ByteString sha1Hash(final X509Certificate x509Certificate) {
        return CertificatePinner.Companion.sha1Hash(x509Certificate);
    }

    public static ByteString sha256Hash(final X509Certificate x509Certificate) {
        return CertificatePinner.Companion.sha256Hash(x509Certificate);
    }

    public Set<Pin> getPins() {
        return pins;
    }

    public CertificateChainCleaner getCertificateChainCleanerokhttp() {
        return certificateChainCleaner;
    }

    public void check(String hostname, List<? extends Certificate> peerCertificates) throws SSLPeerUnverifiedException {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        Intrinsics.checkNotNullParameter(peerCertificates, "peerCertificates");
        this.checkokhttp(hostname, new Function0<List<? extends X509Certificate>>() {
             {
                super(0);
            }

            public List<? extends X509Certificate> invoke() {
                final CertificateChainCleaner certificateChainCleanerokhttp = getCertificateChainCleanerokhttp();
                List<Certificate> listClean = null == certificateChainCleanerokhttp ? null : certificateChainCleanerokhttp.clean(peerCertificates, hostname);
                if (null == listClean) {
                    listClean = peerCertificates;
                }
                final ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listClean, 10));
                final Iterator<T> it = listClean.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next());
                }
                return arrayList;
            }
        });
    }

    public void checkokhttp(final String hostname, final Function0<? extends List<? extends X509Certificate>> cleanedPeerCertificatesFn) throws SSLPeerUnverifiedException {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        Intrinsics.checkNotNullParameter(cleanedPeerCertificatesFn, "cleanedPeerCertificatesFn");
        final List<Pin> listFindMatchingPins = this.findMatchingPins(hostname);
        if (listFindMatchingPins.isEmpty()) {
            return;
        }
        final List<? extends X509Certificate> listInvoke = cleanedPeerCertificatesFn.invoke();
        for (final X509Certificate x509Certificate : listInvoke) {
            ByteString byteStringSha256Hash = null;
            ByteString byteStringSha1Hash = null;
            for (final Pin pin : listFindMatchingPins) {
                final String hashAlgorithm = pin.getHashAlgorithm();
                if (areEqual(hashAlgorithm, "sha256")) {
                    if (null == byteStringSha256Hash) {
                        byteStringSha256Hash = CertificatePinner.Companion.sha256Hash(x509Certificate);
                    }
                    if (areEqual(pin.getHash(), byteStringSha256Hash)) {
                        return;
                    }
                } else if (areEqual(hashAlgorithm, "sha1")) {
                    if (null == byteStringSha1Hash) {
                        byteStringSha1Hash = CertificatePinner.Companion.sha1Hash(x509Certificate);
                    }
                    if (areEqual(pin.getHash(), byteStringSha1Hash)) {
                        return;
                    }
                } else {
                    throw new AssertionError(stringPlus("unsupported hashAlgorithm: ", pin.getHashAlgorithm()));
                }
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Certificate pinning failure!");
        sb.append("\n  Peer certificate chain:");
        for (final X509Certificate x509Certificate2 : listInvoke) {
            sb.append("\n    ");
            sb.append(CertificatePinner.Companion.pin(x509Certificate2));
            sb.append(": ");
            sb.append(x509Certificate2.getSubjectDN().getName());
        }
        sb.append("\n  Pinned certificates for ");
        sb.append(hostname);
        sb.append(":");
        for (final Pin pin2 : listFindMatchingPins) {
            sb.append("\n    ");
            sb.append(pin2);
        }
        final String string = sb.toString();
        checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        throw new SSLPeerUnverifiedException(string);
    }

    public void check(final String hostname, final Certificate... peerCertificates) throws SSLPeerUnverifiedException {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        Intrinsics.checkNotNullParameter(peerCertificates, "peerCertificates");
        this.check(hostname, ArraysKt.toList(peerCertificates));
    }

    public List<Pin> findMatchingPins(final String hostname) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        final Set<Pin> set = pins;
        List<Pin> listEmptyList = CollectionsKt.emptyList();
        for (final Object obj : set) {
            if (((Pin) obj).matchesHostname(hostname)) {
                if (listEmptyList.isEmpty()) {
                    listEmptyList = new ArrayList<>();
                }
                TypeIntrinsics.asMutableList(listEmptyList).add(obj);
            }
        }
        return listEmptyList;
    }

    public CertificatePinner withCertificateChainCleanerokhttp(final CertificateChainCleaner certificateChainCleaner) {
        Intrinsics.checkNotNullParameter(certificateChainCleaner, "certificateChainCleaner");
        return areEqual(this.certificateChainCleaner, certificateChainCleaner) ? this : new CertificatePinner(pins, certificateChainCleaner);
    }

    public boolean equals(final Object obj) {
        if (obj instanceof CertificatePinner certificatePinner) {
            return areEqual(certificatePinner.pins, pins) && areEqual(certificatePinner.certificateChainCleaner, certificateChainCleaner);
        }
        return false;
    }

    public int hashCode() {
        final int iHashCode = (1517 + pins.hashCode()) * 41;
        final CertificateChainCleaner certificateChainCleaner = this.certificateChainCleaner;
        return iHashCode + (null != certificateChainCleaner ? certificateChainCleaner.hashCode() : 0);
    }

    /* compiled from: CertificatePinner.kt */
    public static final class Pin {
        private final ByteString hash;
        private final String hashAlgorithm;
        private final String pattern;

        public Pin(final String pattern, final String pin) {
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            Intrinsics.checkNotNullParameter(pin, "pin");
            if ((!StringsKt.startsWithdefault(pattern, "*.", false, 2, (Object) null) || -1 != StringsKt.indexOfdefault((CharSequence) pattern, ProxyConfig.MATCH_ALL_SCHEMES, 1, false, 4, (Object) null)) && ((!StringsKt.startsWithdefault(pattern, "**.", false, 2, (Object) null) || -1 != StringsKt.indexOfdefault((CharSequence) pattern, ProxyConfig.MATCH_ALL_SCHEMES, 2, false, 4, (Object) null)) && -1 != StringsKt.indexOfdefault((CharSequence) pattern, ProxyConfig.MATCH_ALL_SCHEMES, 0, false, 6, (Object) null))) {
                throw new IllegalArgumentException(stringPlus("Unexpected pattern: ", pattern));
            }
            final String canonicalHost = hostnames.toCanonicalHost(pattern);
            if (null != canonicalHost) {
                this.pattern = canonicalHost;
                if (StringsKt.startsWithdefault(pin, "sha1/", false, 2, (Object) null)) {
                    hashAlgorithm = "sha1";
                    final ByteString.Companion companion = ByteString.Companion;
                    final String strSubstring = pin.substring(5);
                    checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                    final ByteString byteStringDecodeBase64 = companion.decodeBase64(strSubstring);
                    if (null == byteStringDecodeBase64) {
                        throw new IllegalArgumentException(stringPlus("Invalid pin hash: ", pin));
                    }
                    hash = byteStringDecodeBase64;
                    return;
                }
                if (StringsKt.startsWithdefault(pin, "sha256/", false, 2, (Object) null)) {
                    hashAlgorithm = "sha256";
                    final ByteString.Companion companion2 = ByteString.Companion;
                    final String strSubstring2 = pin.substring(7);
                    checkNotNullExpressionValue(strSubstring2, "this as java.lang.String).substring(startIndex)");
                    final ByteString byteStringDecodeBase642 = companion2.decodeBase64(strSubstring2);
                    if (null == byteStringDecodeBase642) {
                        throw new IllegalArgumentException(stringPlus("Invalid pin hash: ", pin));
                    }
                    hash = byteStringDecodeBase642;
                    return;
                }
                throw new IllegalArgumentException(stringPlus("pins must start with 'sha256/' or 'sha1/': ", pin));
            }
            throw new IllegalArgumentException(stringPlus("Invalid pattern: ", pattern));
        }

        public String getPattern() {
            return pattern;
        }

        public String getHashAlgorithm() {
            return hashAlgorithm;
        }

        public ByteString getHash() {
            return hash;
        }

        public boolean matchesHostname(final String hostname) {
            Intrinsics.checkNotNullParameter(hostname, "hostname");
            if (StringsKt.startsWithdefault(pattern, "**.", false, 2, (Object) null)) {
                final int length = pattern.length() - 3;
                final int length2 = hostname.length() - length;
                if (!StringsKt.regionMatchesdefault(hostname, hostname.length() - length, pattern, 3, length, false, 16, null)) {
                    return false;
                }
                return 0 == length2 || '.' == hostname.charAt(length2 - 1);
            } else if (StringsKt.startsWithdefault(pattern, "*.", false, 2, (Object) null)) {
                final int length3 = pattern.length() - 1;
                final int length4 = hostname.length() - length3;
                return StringsKt.regionMatchesdefault(hostname, hostname.length() - length3, pattern, 1, length3, false, 16, null) && -1 == StringsKt.lastIndexOfdefault((CharSequence) hostname, '.', length4 - 1, false, 4, (Object) null);
            } else {
                return areEqual(hostname, pattern);
            }
        }

        public boolean matchesCertificate(final X509Certificate certificate) {
            Intrinsics.checkNotNullParameter(certificate, "certificate");
            final String str = hashAlgorithm;
            if (areEqual(str, "sha256")) {
                return areEqual(hash, Companion.sha256Hash(certificate));
            }
            if (areEqual(str, "sha1")) {
                return areEqual(hash, Companion.sha1Hash(certificate));
            }
            return false;
        }

        public String toString() {
            return hashAlgorithm + '/' + hash.base64();
        }

        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Pin pin)) {
                return false;
            }
            return areEqual(pattern, pin.pattern) && areEqual(hashAlgorithm, pin.hashAlgorithm) && areEqual(hash, pin.hash);
        }

        public int hashCode() {
            return (((pattern.hashCode() * 31) + hashAlgorithm.hashCode()) * 31) + hash.hashCode();
        }
    }

    /* compiled from: CertificatePinner.kt */
    public static final class Builder {
        private final List<Pin> pins = new ArrayList();

        public List<Pin> getPins() {
            return pins;
        }

        public Builder add(final String pattern, final String... pins) {
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            Intrinsics.checkNotNullParameter(pins, "pins");
            final int length = pins.length;
            int i2 = 0;
            while (i2 < length) {
                final String str = pins[i2];
                i2++;
                this.pins.add(new Pin(pattern, str));
            }
            return this;
        }

        public CertificatePinner build() {
            return new CertificatePinner(CollectionsKt.toSet(pins), null, 2, 0 == true ? 1 : 0);
        }
    }

    /* compiled from: CertificatePinner.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public ByteString sha1Hash(final X509Certificate x509Certificate) {
            Intrinsics.checkNotNullParameter(x509Certificate, "<this>");
            final ByteString.Companion companion = ByteString.Companion;
            final byte[] encoded = x509Certificate.getPublicKey().getEncoded();
            checkNotNullExpressionValue(encoded, "publicKey.encoded");
            return ByteString.Companion.ofdefault(companion, encoded, 0, 0, 3, null).sha1();
        }

        public ByteString sha256Hash(final X509Certificate x509Certificate) {
            Intrinsics.checkNotNullParameter(x509Certificate, "<this>");
            final ByteString.Companion companion = ByteString.Companion;
            final byte[] encoded = x509Certificate.getPublicKey().getEncoded();
            checkNotNullExpressionValue(encoded, "publicKey.encoded");
            return ByteString.Companion.ofdefault(companion, encoded, 0, 0, 3, null).sha256();
        }

        public String pin(final Certificate certificate) {
            Intrinsics.checkNotNullParameter(certificate, "certificate");
            if (!(certificate instanceof X509Certificate)) {
                throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
            }
            return stringPlus("sha256/", this.sha256Hash((X509Certificate) certificate).base64());
        }
    }
}
