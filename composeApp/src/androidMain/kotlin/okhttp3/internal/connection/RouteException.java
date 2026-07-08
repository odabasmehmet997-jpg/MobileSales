package okhttp3.internal.connection;

import kotlin.ExceptionsKt;
import kotlin.jvm.internal.Intrinsics;

import java.io.IOException;

public final class RouteException extends RuntimeException {
    private final IOException firstConnectException;
    private IOException lastConnectException;
    public RouteException(IOException firstConnectException) {
        super(firstConnectException);
        Intrinsics.checkNotNullParameter(firstConnectException, "firstConnectException");
        this.firstConnectException = firstConnectException;
        this.lastConnectException = firstConnectException;
    }
    public IOException getFirstConnectException() {
        return this.firstConnectException;
    }
    public IOException getLastConnectException() {
        return this.lastConnectException;
    }
    public void addConnectException(IOException e2) {
        Intrinsics.checkNotNullParameter(e2, "e");
        ExceptionsKt.addSuppressed(this.firstConnectException, e2);
        this.lastConnectException = e2;
    }
}
