package okhttp3.internal.authenticator;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.*;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.net.*;
import java.net.Authenticator;
import java.util.List;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class JavaNetAuthenticator extends Authenticator {
    private Dns defaultDns = null;
    public JavaNetAuthenticator() {
        this(null);
    }
    public JavaNetAuthenticator(Dns defaultDns) {
        Intrinsics.checkNotNullParameter(defaultDns, "defaultDns");
        this.defaultDns = defaultDns;
    }
    public JavaNetAuthenticator(Dns dns, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i2 & 1) ? Dns.SYSTEM : dns);
    }
    public Request authenticate(Route route, Response response) throws IOException {
        Address address;
        PasswordAuthentication passwordAuthenticationRequestPasswordAuthentication;
        Intrinsics.checkNotNullParameter(response, "response");
        List<Challenge> listChallenges = response.challenges();
        Request request = response.request();
        HttpUrl httpUrlUrl = request.url();
        boolean z = 407 == response.code();
        Proxy proxy = null == route ? null : route.proxy();
        if (null == proxy) {
            proxy = Proxy.NO_PROXY;
        }
        for (Challenge challenge : listChallenges) {
            if (StringsKt.equals("Basic", challenge.scheme(), true)) {
                Dns dns = (null == route || null == (address = route.address())) ? null : address.dns();
                if (null == dns) {
                    dns = this.defaultDns;
                }
                if (z) {
                    SocketAddress socketAddressAddress = proxy.address();
                    if (null == socketAddressAddress) {
                        throw new NullPointerException("null cannot be cast to non-null type java.net.InetSocketAddress");
                    }
                    InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressAddress;
                    String hostName = inetSocketAddress.getHostName();
                    checkNotNullExpressionValue(proxy, "proxy");
                    passwordAuthenticationRequestPasswordAuthentication = Authenticator.requestPasswordAuthentication(hostName, connectToInetAddress(proxy, httpUrlUrl, dns), inetSocketAddress.getPort(), httpUrlUrl.scheme(), challenge.realm(), challenge.scheme(), httpUrlUrl.url(), RequestorType.PROXY);
                } else {
                    String strHost = httpUrlUrl.host();
                    checkNotNullExpressionValue(proxy, "proxy");
                    passwordAuthenticationRequestPasswordAuthentication = Authenticator.requestPasswordAuthentication(strHost, connectToInetAddress(proxy, httpUrlUrl, dns), httpUrlUrl.port(), httpUrlUrl.scheme(), challenge.realm(), challenge.scheme(), httpUrlUrl.url(), RequestorType.SERVER);
                }
                if (null != passwordAuthenticationRequestPasswordAuthentication) {
                    String str = z ? HttpHeaders.PROXY_AUTHORIZATION : HttpHeaders.AUTHORIZATION;
                    String userName = passwordAuthenticationRequestPasswordAuthentication.getUserName();
                    checkNotNullExpressionValue(userName, "auth.userName");
                    char[] password = passwordAuthenticationRequestPasswordAuthentication.getPassword();
                    checkNotNullExpressionValue(password, "auth.password");
                    return request.newBuilder().header(str, Credentials.basic(userName, new String(password), challenge.charset())).build();
                }
            }
        }
        return null;
    }
    private InetAddress connectToInetAddress(Proxy proxy, HttpUrl httpUrl, Dns dns) throws IOException {
        Proxy.Type type = proxy.type();
        if (1 == (null == type ? -1 : WhenMappings.EnumSwitchMapping0[type.ordinal()])) {
            return (InetAddress) CollectionsKt.first((List) dns.lookup(httpUrl.host()));
        }
        SocketAddress socketAddressAddress = proxy.address();
        if (null == socketAddressAddress) {
            throw new NullPointerException("null cannot be cast to non-null type java.net.InetSocketAddress");
        }
        InetAddress address = ((InetSocketAddress) socketAddressAddress).getAddress();
        checkNotNullExpressionValue(address, "address() as InetSocketAddress).address");
        return address;
    }
    public enum WhenMappings {
        ;
        public static final int[] EnumSwitchMapping0;

        static {
            int[] iArr = new int[Proxy.Type.values().length];
            iArr[Proxy.Type.DIRECT.ordinal()] = 1;
            EnumSwitchMapping0 = iArr;
        }
    }
}
