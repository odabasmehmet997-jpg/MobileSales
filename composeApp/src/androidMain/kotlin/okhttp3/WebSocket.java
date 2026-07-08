package okhttp3;

import okio.ByteString;

public interface WebSocket {

    void cancel();

    boolean close(int i2, String str);

    long queueSize();

    Request request();

    boolean send(String str);

    boolean send(ByteString byteString);

    /* compiled from: WebSocket.kt */
    interface Factory {
        WebSocket newWebSocket(Request request, WebSocketListener webSocketListener);
    }
}
