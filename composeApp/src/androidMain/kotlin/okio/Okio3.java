package okio;

import kotlin.jvm.internal.Intrinsics;

public final class Okio3 {
    public static BufferedSource buffer(Source source) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        return new RealBufferedSource(source);
    }
    public static BufferedSink buffer(Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        return new RealBufferedSink(sink);
    }
    public static Sink blackhole() {
        return new Okio2();
    }
}
