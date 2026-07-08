package okhttp3.internal.connection;

import kotlin.jvm.internal.Intrinsics;
import okhttp3.ConnectionSpec;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;

public final class ConnectionSpecSelector {
    private final List<ConnectionSpec> connectionSpecs;
    private boolean isFallback;
    private boolean isFallbackPossible;
    private int nextModeIndex;
    public ConnectionSpecSelector(List<ConnectionSpec> connectionSpecs) {
        Intrinsics.checkNotNullParameter(connectionSpecs, "connectionSpecs");
        this.connectionSpecs = connectionSpecs;
    }
    public ConnectionSpec configureSecureSocket(SSLSocket sslSocket) throws IOException {
        ConnectionSpec connectionSpec;
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        int i2 = this.nextModeIndex;
        int size = this.connectionSpecs.size();
        while (true) {
            if (i2 >= size) {
                connectionSpec = null;
                break;
            }
            int i3 = i2 + 1;
            connectionSpec = this.connectionSpecs.get(i2);
            if (connectionSpec.isCompatible(sslSocket)) {
                this.nextModeIndex = i3;
                break;
            }
            i2 = i3;
        }
        if (null != connectionSpec) {
            this.isFallbackPossible = isFallbackPossible(sslSocket);
            connectionSpec.applyokhttp(sslSocket, this.isFallback);
            return connectionSpec;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to find acceptable protocols. isFallback=");
        sb.append(this.isFallback);
        sb.append(", modes=");
        sb.append(this.connectionSpecs);
        sb.append(", supported protocols=");
        String[] enabledProtocols = sslSocket.getEnabledProtocols();
        Intrinsics.checkNotNull(enabledProtocols);
        String string = Arrays.toString(enabledProtocols);
        Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
        sb.append(string);
        throw new UnknownServiceException(sb.toString());
    }
    public boolean connectionFailed(IOException e2) {
        Intrinsics.checkNotNullParameter(e2, "e");
        this.isFallback = true;
        return this.isFallbackPossible && (!(e2 instanceof ProtocolException)) && (!(e2 instanceof InterruptedIOException)) && ((!(e2 instanceof SSLHandshakeException)) || (!(e2.getCause() instanceof CertificateException))) && (!(e2 instanceof SSLPeerUnverifiedException)) && e2 instanceof SSLException;
    }
    private boolean isFallbackPossible(SSLSocket sSLSocket) {
        int i2 = this.nextModeIndex;
        int size = this.connectionSpecs.size();
        while (i2 < size) {
            int i3 = i2 + 1;
            if (this.connectionSpecs.get(i2).isCompatible(sSLSocket)) {
                return true;
            }
            i2 = i3;
        }
        return false;
    }
}
