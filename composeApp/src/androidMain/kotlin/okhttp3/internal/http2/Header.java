package okhttp3.internal.http2;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;

import static kotlin.jvm.internal.Intrinsics.areEqual;

public final class Header {
    public static final Companion Companion = new Companion(null);
    public static final ByteString PSEUDO_PREFIX;
    public static final ByteString RESPONSE_STATUS;
    public static final String RESPONSE_STATUS_UTF8 = ":status";
    public static final ByteString TARGET_AUTHORITY;
    public static final String TARGET_AUTHORITY_UTF8 = ":authority";
    public static final ByteString TARGET_METHOD;
    public static final String TARGET_METHOD_UTF8 = ":method";
    public static final ByteString TARGET_PATH;
    public static final String TARGET_PATH_UTF8 = ":path";
    public static final ByteString TARGET_SCHEME;
    public static final String TARGET_SCHEME_UTF8 = ":scheme";

    static {
        final ByteString.Companion companion = ByteString.Companion;
        PSEUDO_PREFIX = companion.encodeUtf8(":");
        RESPONSE_STATUS = companion.encodeUtf8(Header.RESPONSE_STATUS_UTF8);
        TARGET_METHOD = companion.encodeUtf8(Header.TARGET_METHOD_UTF8);
        TARGET_PATH = companion.encodeUtf8(Header.TARGET_PATH_UTF8);
        TARGET_SCHEME = companion.encodeUtf8(Header.TARGET_SCHEME_UTF8);
        TARGET_AUTHORITY = companion.encodeUtf8(Header.TARGET_AUTHORITY_UTF8);
    }

    public final int hpackSize;
    public final ByteString name;
    public final ByteString value;

    public Header(final ByteString name, final ByteString value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        this.name = name;
        this.value = value;
        hpackSize = name.size() + 32 + value.size();
    }

    public Header(final String name, final String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        final ByteString.Companion companion = ByteString.Companion;
        this(companion.encodeUtf8(name), companion.encodeUtf8(value));
    }
    public Header(final ByteString name, final String value) {
        this(name, ByteString.Companion.encodeUtf8(value));
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
    }

    public static Header copydefault(final Header header, ByteString byteString, ByteString byteString2, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            byteString = header.name;
        }
        if (0 != (i2 & 2)) {
            byteString2 = header.value;
        }
        return header.copy(byteString, byteString2);
    }

    public ByteString component1() {
        return name;
    }

    public ByteString component2() {
        return value;
    }

    public Header copy(final ByteString name, final ByteString value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        return new Header(name, value);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Header header)) {
            return false;
        }
        return areEqual(name, header.name) && areEqual(value, header.value);
    }

    public int hashCode() {
        return (name.hashCode() * 31) + value.hashCode();
    }

    public String toString() {
        return name.utf8() + ": " + value.utf8();
    }

    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
