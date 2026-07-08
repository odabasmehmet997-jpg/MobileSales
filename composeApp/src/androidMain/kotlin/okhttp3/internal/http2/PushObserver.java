package okhttp3.internal.http2;

import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;

import java.io.IOException;
import java.util.List;

public interface PushObserver {
    PushObserver CANCEL = new Companion.PushObserverCancel();    Companion Companion = PushObserver.Companion.INSTANCE;
    boolean onData(int i2, BufferedSource bufferedSource, int i3, boolean z) throws IOException;
    boolean onHeaders(int i2, List<Header> list, boolean z);
    boolean onRequest(int i2, List<Header> list);
    void onReset(int i2, ErrorCode errorCode);
    final class Companion {
        static final Companion INSTANCE = new Companion();
        private Companion() {
        }
        private static final class PushObserverCancel implements PushObserver {
            
            public boolean onHeaders(final int i2, final List<Header> responseHeaders, final boolean z) {
                Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders");
                return true;
            }
            public boolean onRequest(final int i2, final List<Header> requestHeaders) {
                Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
                return true;
            }
            public void onReset(final int i2, final ErrorCode errorCode) {
                Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            }
            public boolean onData(final int i2, final BufferedSource source, final int i3, final boolean z) throws IOException {
                Intrinsics.checkNotNullParameter(source, "source");
                source.skip(i3);
                return true;
            }
        }
    }


}
