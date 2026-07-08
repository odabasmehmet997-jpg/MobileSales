package okhttp3.internal.publicsuffix;

import androidx.webkit.ProxyConfig;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.Closeable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public final class PublicSuffixDatabase {
    public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
    public static final Companion Companion = new Companion(null);
    private static final char EXCEPTION_MARKER = '!';
    private static final byte[] WILDCARD_LABEL = {42};
    private static final List<String> PREVAILING_RULE = CollectionsKt.listOf(ProxyConfig.MATCH_ALL_SCHEMES);
    private static final PublicSuffixDatabase instance = new PublicSuffixDatabase();
    private final AtomicBoolean listRead = new AtomicBoolean(false);
    private final CountDownLatch readCompleteLatch = new CountDownLatch(1);
    private byte[] publicSuffixExceptionListBytes;
    private byte[] publicSuffixListBytes;
    public String getEffectiveTldPlusOne(String domain) throws InterruptedException {
        int size;
        int size2;
        Intrinsics.checkNotNullParameter(domain, "domain");
        String unicodeDomain = IDN.toUnicode(domain);
        Intrinsics.checkNotNullExpressionValue(unicodeDomain, "unicodeDomain");
        List<String> listSplitDomain = splitDomain(unicodeDomain);
        List<String> listFindMatchingRule = findMatchingRule(listSplitDomain);
        if (listSplitDomain.size() == listFindMatchingRule.size() && '!' != listFindMatchingRule.get(0).charAt(0)) {
            return null;
        }
        if ('!' == listFindMatchingRule.get(0).charAt(0)) {
            size = listSplitDomain.size();
            size2 = listFindMatchingRule.size();
        } else {
            size = listSplitDomain.size();
            size2 = listFindMatchingRule.size() + 1;
        }
        return SequencesKt.joinToString(SequencesKt.drop(CollectionsKt.asSequence(splitDomain(domain)), size - size2), ".", null, null, 0, null, null);
    }
    private List<String> splitDomain(String str) {
        List<String> listSplitdefault = StringsKt.split(str, new char[]{'.'}, false, 0);
        return Intrinsics.areEqual(CollectionsKt.last(listSplitdefault), "") ? CollectionsKt.dropLast(listSplitdefault, 1) : listSplitdefault;
    }
    private List<String> findMatchingRule(List<String> list) throws InterruptedException {
        String str;
        String str2;
        String strBinarySearch;
        if (!this.listRead.get() && this.listRead.compareAndSet(false, true)) {
            readTheListUninterruptibly();
        } else {
            try {
                this.readCompleteLatch.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
        if (null == publicSuffixListBytes) {
            throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
        }
        int size = list.size();
        byte[][] bArr = new byte[size][];
        for (int i2 = 0; i2 < size; i2++) {
            String str3 = list.get(i2);
            Charset UTF_8 = StandardCharsets.UTF_8;
            Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
            byte[] bytes = str3.getBytes(UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            bArr[i2] = bytes;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                str = null;
                break;
            }
            int i4 = i3 + 1;
            Companion companion = Companion;
            byte[] bArr2 = this.publicSuffixListBytes;
            if (null == bArr2) {
                Intrinsics.throwUninitializedPropertyAccessException("publicSuffixListBytes");
                bArr2 = null;
            }
            String strBinarySearch2 = companion.binarySearch(bArr2, bArr, i3);
            if (null != strBinarySearch2) {
                str = strBinarySearch2;
                break;
            }
            i3 = i4;
        }
        if (1 < size) {
            byte[][] bArr3 = bArr.clone();
            int length = bArr3.length - 1;
            int i5 = 0;
            while (i5 < length) {
                int i6 = i5 + 1;
                bArr3[i5] = WILDCARD_LABEL;
                Companion companion2 = Companion;
                byte[] bArr4 = this.publicSuffixListBytes;
                if (null == bArr4) {
                    Intrinsics.throwUninitializedPropertyAccessException("publicSuffixListBytes");
                    bArr4 = null;
                }
                String strBinarySearch3 = companion2.binarySearch(bArr4, bArr3, i5);
                if (null != strBinarySearch3) {
                    str2 = strBinarySearch3;
                    break;
                }
                i5 = i6;
            }
            str2 = null;
        } else {
            str2 = null;
        }
        if (null != str2) {
            int i7 = size - 1;
            int i8 = 0;
            while (i8 < i7) {
                int i9 = i8 + 1;
                Companion companion3 = Companion;
                byte[] bArr5 = this.publicSuffixExceptionListBytes;
                if (null == bArr5) {
                    Intrinsics.throwUninitializedPropertyAccessException("publicSuffixExceptionListBytes");
                    bArr5 = null;
                }
                strBinarySearch = companion3.binarySearch(bArr5, bArr, i8);
                if (null != strBinarySearch) {
                    break;
                }
                i8 = i9;
            }
            strBinarySearch = null;
        } else {
            strBinarySearch = null;
        }
        if (null != strBinarySearch) {
            return StringsKt.split(Intrinsics.stringPlus("!", strBinarySearch), new char[]{'.'}, false, 0);
        }
        if (null == str && null == str2) {
            return PREVAILING_RULE;
        }
        List<String> listSplitdefault = null == str ? null : StringsKt.split(str, new char[]{'.'}, false, 0);
        if (null == listSplitdefault) {
            listSplitdefault = CollectionsKt.emptyList();
        }
        List<String> listSplitdefault2 = null != str2 ? StringsKt.split(str2, new char[]{'.'}, false, 0) : null;
        if (null == listSplitdefault2) {
            listSplitdefault2 = CollectionsKt.emptyList();
        }
        return listSplitdefault.size() > listSplitdefault2.size() ? listSplitdefault : listSplitdefault2;
    }
    private void readTheListUninterruptibly() {
        boolean z = false;
        while (true) {
            try {
                try {
                    readTheList();
                    break;
                } catch (InterruptedIOException unused) {
                    Thread.interrupted();
                    z = true;
                } catch (IOException e2) {
                    Platform.Companion.get().log("Failed to read public suffix list", 5, e2);
                    if (z) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    return;
                }
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }
    private void readTheList() throws IOException {
        InputStream resourceAsStream = PublicSuffixDatabase.class.getResourceAsStream(PUBLIC_SUFFIX_RESOURCE);
        if (null == resourceAsStream) {
            return;
        }
        BufferedSource bufferedSourceBuffer = Okio.buffer(new GzipSource(Okio.source(resourceAsStream)));
        final byte[] byteArray = bufferedSourceBuffer.readByteArray(bufferedSourceBuffer.readInt());
        final byte[] byteArray2 = bufferedSourceBuffer.readByteArray(bufferedSourceBuffer.readInt());
        final Unit unit = Unit.INSTANCE;
        Closeable.closeFinally(bufferedSourceBuffer, null);
        synchronized (this) {
            Intrinsics.checkNotNull(byteArray);
            publicSuffixListBytes = byteArray;
            Intrinsics.checkNotNull(byteArray2);
            publicSuffixExceptionListBytes = byteArray2;
        }
        readCompleteLatch.countDown();
    }
    public void setListBytes(byte[] publicSuffixListBytes, byte[] publicSuffixExceptionListBytes) {
        Intrinsics.checkNotNullParameter(publicSuffixListBytes, "publicSuffixListBytes");
        Intrinsics.checkNotNullParameter(publicSuffixExceptionListBytes, "publicSuffixExceptionListBytes");
        this.publicSuffixListBytes = publicSuffixListBytes;
        this.publicSuffixExceptionListBytes = publicSuffixExceptionListBytes;
        this.listRead.set(true);
        this.readCompleteLatch.countDown();
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public PublicSuffixDatabase get() {
            return PublicSuffixDatabase.instance;
        }

        private String binarySearch(byte[] bArr, byte[][] bArr2, int i2) {
            int i3;
            int iAnd;
            boolean z;
            int iAnd2;
            int length = bArr.length;
            int i4 = 0;
            while (i4 < length) {
                int i5 = (i4 + length) / 2;
                while (-1 < i5 && 10 != bArr[i5]) {
                    i5--;
                }
                int i6 = i5 + 1;
                int i7 = 1;
                while (true) {
                    i3 = i6 + i7;
                    if (10 == bArr[i3]) {
                        break;
                    }
                    i7++;
                }
                int i8 = i3 - i6;
                int i9 = i2;
                boolean z2 = false;
                int i10 = 0;
                int i11 = 0;
                while (true) {
                    if (z2) {
                        iAnd = 46;
                        z = false;
                    } else {
                        boolean z3 = z2;
                        iAnd = Util.and(bArr2[i9][i10], 255);
                        z = z3;
                    }
                    iAnd2 = iAnd - Util.and(bArr[i6 + i11], 255);
                    if (0 != iAnd2) {
                        break;
                    }
                    i11++;
                    i10++;
                    if (i11 == i8) {
                        break;
                    }
                    if (bArr2[i9].length != i10) {
                        z2 = z;
                    } else {
                        if (i9 == bArr2.length - 1) {
                            break;
                        }
                        i9++;
                        z2 = true;
                        i10 = -1;
                    }
                }
                if (0 <= iAnd2) {
                    if (0 >= iAnd2) {
                        int i12 = i8 - i11;
                        int length2 = bArr2[i9].length - i10;
                        int length3 = bArr2.length;
                        for (int i13 = i9 + 1; i13 < length3; i13++) {
                            length2 += bArr2[i13].length;
                        }
                        if (length2 >= i12) {
                            if (length2 <= i12) {
                                Charset UTF_8 = StandardCharsets.UTF_8;
                                Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
                                return new String(bArr, i6, i8, UTF_8);
                            }
                        }
                    }
                    i4 = i3 + 1;
                }
                length = i5;
            }
            return null;
        }
    }
}
