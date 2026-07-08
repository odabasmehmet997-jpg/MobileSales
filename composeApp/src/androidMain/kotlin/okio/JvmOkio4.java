package okio;

import kotlin.jvm.internal.Intrinsics;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;

final class JvmOkio4 extends AsyncTimeout {
    private final Socket socket;
    public JvmOkio4(final Socket socket) {
        Intrinsics.checkNotNullParameter(socket, "socket");
        this.socket = socket;
    }
    protected IOException newTimeoutException(final IOException iOException) {
        final SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (null != iOException) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }
    protected void timedOut() {
        try {
            socket.close();
        } catch (final AssertionError e2) {
            if (Okio.isAndroidGetsocknameError(e2)) {
                JvmOkio2.logger.log(Level.WARNING, "Failed to close timed out socket " + socket, e2);
                return;
            }
            throw e2;
        } catch (final Exception e3) {
            JvmOkio2.logger.log(Level.WARNING, "Failed to close timed out socket " + socket, e3);
        }
    }
}
