package okhttp3.internal.proxy;

import kotlin.collections.CollectionsKt;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;

public final class NullProxySelector extends ProxySelector {
    public static final NullProxySelector INSTANCE = new NullProxySelector();
    private NullProxySelector() {
    }
    public void connectFailed(final URI uri, final SocketAddress socketAddress, final IOException iOException) {
    }
    public List<Proxy> select(final URI uri) {
        if (null == uri) {
            throw new IllegalArgumentException("uri must not be null");
        }
        return CollectionsKt.listOf(Proxy.NO_PROXY);
    }
}
