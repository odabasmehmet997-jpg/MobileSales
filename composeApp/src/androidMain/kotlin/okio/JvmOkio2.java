package okio;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public class JvmOkio2 {
    static final Logger logger = Logger.getLogger("okio.Okio");
    public static Sink sink(OutputStream outputStream) {
        Intrinsics.checkNotNullParameter(outputStream, "<this>");
        return new JvmOkio3(outputStream, new Timeout());
    }
    public static Source source(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "<this>");
        return new JvmOkio(inputStream, new Timeout());
    }
    public static Sink sink(Socket socket) throws IOException {
        Intrinsics.checkNotNullParameter(socket, "<this>");
        JvmOkio4 jvmOkio4 = new JvmOkio4(socket);
        OutputStream outputStream = socket.getOutputStream();
        checkNotNullExpressionValue(outputStream, "getOutputStream()");
        return jvmOkio4.sink(new JvmOkio3(outputStream, jvmOkio4));
    }
    public static Source source(Socket socket) throws IOException {
        Intrinsics.checkNotNullParameter(socket, "<this>");
        JvmOkio4 jvmOkio4 = new JvmOkio4(socket);
        InputStream inputStream = socket.getInputStream();
        checkNotNullExpressionValue(inputStream, "getInputStream()");
        return jvmOkio4.source(new JvmOkio(inputStream, jvmOkio4));
    }
    public static Sink sink(File file, boolean z) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return Okio.sink(new FileOutputStream(file, z));
    }
    public static Sink sink(File file, boolean z, int i2, Object obj) throws FileNotFoundException {
        if (0 != (i2 & 1)) {
            z = false;
        }
        return Okio.sink(file, z);
    }
    public static Sink appendingSink(File file) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return Okio.sink(new FileOutputStream(file, true));
    }
    public static Source source(File file) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return new JvmOkio(new FileInputStream(file), Timeout.NONE);
    }
    public static boolean isAndroidGetsocknameError(AssertionError assertionError) {
        Intrinsics.checkNotNullParameter(assertionError, "<this>");
        if (null == assertionError.getCause()) {
            return false;
        }
        String message = assertionError.getMessage();
        return null != message && StringsKt.contains(message, "getsockname failed", false);
    }
}
