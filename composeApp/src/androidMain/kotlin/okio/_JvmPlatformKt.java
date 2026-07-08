package okio;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class _JvmPlatformKt {
    public static String toUtf8String(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return new String(bArr, Charsets.UTF_8);
    }
    public static byte[] asUtf8ToByteArray(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return bytes;
    }
}
