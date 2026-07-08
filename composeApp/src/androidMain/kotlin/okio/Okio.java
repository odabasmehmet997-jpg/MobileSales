package okio;

import java.io.*;
import java.net.Socket;

public final class Okio {
    public static Sink appendingSink(File file) throws FileNotFoundException {
        return JvmOkio2.appendingSink(file);
    }
    public static Sink blackhole() {
        return Okio3.blackhole();
    }
    public static BufferedSink buffer(Sink sink) {
        return Okio3.buffer(sink);
    }
    public static BufferedSource buffer(Source source) {
        return Okio3.buffer(source);
    }
    public static boolean isAndroidGetsocknameError(AssertionError assertionError) {
        return JvmOkio2.isAndroidGetsocknameError(assertionError);
    }
    public static Sink sink(File file, boolean z) throws FileNotFoundException {
        return JvmOkio2.sink(file, z);
    }
    public static Sink sink(OutputStream outputStream) {
        return JvmOkio2.sink(outputStream);
    }
    public static Sink sink(Socket socket) throws IOException {
        return JvmOkio2.sink(socket);
    }
    public static Source source(File file) throws FileNotFoundException {
        return JvmOkio2.source(file);
    }
    public static Source source(InputStream inputStream) {
        return JvmOkio2.source(inputStream);
    }
    public static Source source(Socket socket) throws IOException {
        return JvmOkio2.source(socket);
    }
}
