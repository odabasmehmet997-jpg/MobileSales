package okhttp3;

import com.proje.mobilesales.features.sales.view.newadd.T;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.connection.RealCall;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

public abstract class EventListener {
    public static final Companion Companion = new Companion(null);
    public static final EventListener NONE = new EventListener() { // from class: okhttp3.EventListenerCompanionNONE1
    };
    public void cacheConditionalHit(retrofit2.Call<T> call, Response cachedResponse) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(cachedResponse, "cachedResponse");
    }
    public void cacheHit(retrofit2.Call<T> call, Response response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
    }
    public void cacheMiss(retrofit2.Call<T> call) {
        Intrinsics.checkNotNullParameter(call, "call");
    }
    public void callEnd(RealCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
    }
    public void callFailed(RealCall call, IOException ioe) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(ioe, "ioe");
    }
    public void callStart(RealCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
    }
    public void canceled(RealCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
    }
    public void connectEnd(Call<T> call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(inetSocketAddress, "inetSocketAddress");
        Intrinsics.checkNotNullParameter(proxy, "proxy");
    }
    public void connectFailed(Call<T> call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol, IOException ioe) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(inetSocketAddress, "inetSocketAddress");
        Intrinsics.checkNotNullParameter(proxy, "proxy");
        Intrinsics.checkNotNullParameter(ioe, "ioe");
    }
    public void connectStart(Call<T> call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(inetSocketAddress, "inetSocketAddress");
        Intrinsics.checkNotNullParameter(proxy, "proxy");
    }
    public void connectionAcquired(RealCall call, Connection connection) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(connection, "connection");
    }
    public void connectionReleased(RealCall call, Connection connection) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(connection, "connection");
    }
    public void dnsEnd(Call<T> call, String domainName, List<InetAddress> inetAddressList) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(domainName, "domainName");
        Intrinsics.checkNotNullParameter(inetAddressList, "inetAddressList");
    }
    public void dnsStart(Call<T> call, String domainName) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(domainName, "domainName");
    }
    public void proxySelectEnd(Call<T> call, HttpUrl url, List<Proxy> proxies) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(proxies, "proxies");
    }
    public void proxySelectStart(Call<T> call, HttpUrl url) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(url, "url");
    }
    public void requestBodyEnd(RealCall call, long j2) {
        Intrinsics.checkNotNullParameter(call, "call");
    }
    public void requestBodyStart(RealCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
    }
    public void requestFailed(RealCall call, IOException ioe) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(ioe, "ioe");
    }
    public void requestHeadersEnd(RealCall call, Request request) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(request, "request");
    }
    public void requestHeadersStart(RealCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
    }
    public void responseBodyEnd(RealCall call, long j2) {
        Intrinsics.checkNotNullParameter(call, "call");
    }
    public void responseBodyStart(Call<T> call) {
        Intrinsics.checkNotNullParameter(call, "call");
    }
    public void responseFailed(RealCall call, IOException ioe) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(ioe, "ioe");
    }
    public void responseHeadersEnd(RealCall call, Response response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
    }
    public void responseHeadersStart(RealCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
    }
    public void satisfactionFailure(retrofit2.Call<T> call, Response response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
    }
    public void secureConnectEnd(Call<T> call, Handshake handshake) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(handshake, "handshake");
    }
    public void secureConnectStart(Call<T> call) {
        Intrinsics.checkNotNullParameter(call, "call");
    }
    public interface Factory {
        EventListener create(RealCall call);
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
