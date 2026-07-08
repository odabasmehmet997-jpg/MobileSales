package okhttp3;

import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;

public abstract class WebSocketListener {
    public void onClosed(final WebSocket webSocket, final int i2, final String reason) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(reason, "reason");
    }
    public void onClosing(final WebSocket webSocket, final int i2, final String reason) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(reason, "reason");
    }
    public void onFailure(final WebSocket webSocket, final Throwable t, final Response response) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(t, "t");
    }
    public void onMessage(final WebSocket webSocket, final String text) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(text, "text");
    }
    public void onMessage(final WebSocket webSocket, final ByteString bytes) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
    }
    public void onOpen(final WebSocket webSocket, final Response response) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(response, "response");
    }
}
