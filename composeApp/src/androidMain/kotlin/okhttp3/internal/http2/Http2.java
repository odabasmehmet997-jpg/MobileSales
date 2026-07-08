package okhttp3.internal.http2;

import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okio.ByteString;

import static kotlin.jvm.internal.Intrinsics.*;

public final class Http2 {
    public static final int FLAG_ACK = 1;
    public static final int FLAG_COMPRESSED = 32;
    public static final int FLAG_END_HEADERS = 4;
    public static final int FLAG_END_PUSH_PROMISE = 4;
    public static final int FLAG_END_STREAM = 1;
    public static final int FLAG_NONE = 0;
    public static final int FLAG_PADDED = 8;
    public static final int FLAG_PRIORITY = 32;
    public static final int INITIAL_MAX_FRAME_SIZE = 16384;
    public static final int TYPE_CONTINUATION = 9;
    public static final int TYPE_DATA = 0;
    public static final int TYPE_GOAWAY = 7;
    public static final int TYPE_HEADERS = 1;
    public static final int TYPE_PING = 6;
    public static final int TYPE_PRIORITY = 2;
    public static final int TYPE_PUSH_PROMISE = 5;
    public static final int TYPE_RST_STREAM = 3;
    public static final int TYPE_SETTINGS = 4;
    public static final int TYPE_WINDOW_UPDATE = 8;
    public static final Http2 INSTANCE = new Http2();
    public static final ByteString CONNECTION_PREFACE = ByteString.Companion.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    private static final String[] BINARY;
    private static final String[] FRAME_NAMES = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
    private static final String[] FLAGS = new String[64];

    static {
        final String[] strArr = new String[256];
        int i2 = 0;
        for (int i3 = 0; 256 > i3; i3++) {
            final String binaryString = Integer.toBinaryString(i3);
            checkNotNullExpressionValue(binaryString, "toBinaryString(it)");
            strArr[i3] = StringsKt.replace(Util.format("%8s", binaryString), ' ', '0', false);
        }
        BINARY = strArr;
        final String[] strArr2 = Http2.FLAGS;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        final int[] iArr = {1};
        strArr2[8] = "PADDED";
        final int i4 = iArr[0];
        strArr2[i4 | 8] = stringPlus(strArr2[i4], "|PADDED");
        strArr2[4] = "END_HEADERS";
        strArr2[32] = "PRIORITY";
        strArr2[36] = "END_HEADERS|PRIORITY";
        final int[] iArr2 = {4, 32, 36};
        int i5 = 0;
        while (3 > i5) {
            final int i6 = iArr2[i5];
            i5++;
            final int i7 = iArr[0];
            final String[] strArr3 = Http2.FLAGS;
            final int i8 = i7 | i6;
            String sb = String.valueOf(strArr3[i7]) +
                    '|' +
                    strArr3[i6];
            strArr3[i8] = sb;
            strArr3[i8 | 8] = strArr3[i7] + '|' + strArr3[i6] + "|PADDED";
        }
        final int length = Http2.FLAGS.length;
        while (i2 < length) {
            final int i9 = i2 + 1;
            final String[] strArr4 = Http2.FLAGS;
            if (null == strArr4[i2]) {
                strArr4[i2] = Http2.BINARY[i2];
            }
            i2 = i9;
        }
    }

    private Http2() {
    }

    public String frameLog(final boolean z, final int i2, final int i3, final int i4, final int i5) {
        return Util.format("%s 0x%08x %5d %-13s %s", z ? "<<" : ">>", Integer.valueOf(i2), Integer.valueOf(i3), this.formattedTypeokhttp(i4), this.formatFlags(i4, i5));
    }

    public String formattedTypeokhttp(final int i2) {
        final String[] strArr = Http2.FRAME_NAMES;
        return i2 < strArr.length ? strArr[i2] : Util.format("0x%02x", Integer.valueOf(i2));
    }

    public String formatFlags(final int i2, final int i3) {
        final String str;
        if (0 == i3) {
            return "";
        }
        if (2 != i2 && 3 != i2) {
            if (4 == i2 || 6 == i2) {
                return 1 == i3 ? "ACK" : Http2.BINARY[i3];
            }
            if (7 != i2 && 8 != i2) {
                final String[] strArr = Http2.FLAGS;
                if (i3 < strArr.length) {
                    str = strArr[i3];
                    checkNotNull(str);
                } else {
                    str = Http2.BINARY[i3];
                }
                final String str2 = str;
                if (5 != i2 || 0 == (i3 & 4)) {
                    return (0 != i2 || 0 == (i3 & 32)) ? str2 : StringsKt.replace(str2, "PRIORITY", "COMPRESSED", false);
                }
                return StringsKt.replace(str2, "HEADERS", "PUSH_PROMISE", false);
            }
        }
        return Http2.BINARY[i3];
    }
}
