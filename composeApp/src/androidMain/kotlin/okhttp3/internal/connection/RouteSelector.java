package okhttp3.internal.connection;

import com.proje.mobilesales.features.sales.view.newadd.T;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.*;
import okhttp3.internal.Util;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public final class RouteSelector {
    public static final Companion Companion = new Companion(null);
    private final Address address;
    private final Call call;
    private final EventListener eventListener;
    private final List<Route> postponedRoutes;
    private final RouteDatabase routeDatabase;
    private List<? extends InetSocketAddress> inetSocketAddresses;
    private int nextProxyIndex;
    private List<? extends Proxy> proxies;
    public RouteSelector(Address address, RouteDatabase routeDatabase, Call call, EventListener eventListener) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(routeDatabase, "routeDatabase");
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        this.address = address;
        this.routeDatabase = routeDatabase;
        this.call = call;
        this.eventListener = eventListener;
        this.proxies = CollectionsKt.emptyList();
        this.inetSocketAddresses = CollectionsKt.emptyList();
        this.postponedRoutes = new ArrayList();
        resetNextProxy(address.url(), address.proxy());
    }
    private static List<Proxy> resetNextProxyselectProxies(Proxy proxy, HttpUrl httpUrl, RouteSelector routeSelector) {
        if (null != proxy) {
            return CollectionsKt.listOf(proxy);
        }
        URI uri = httpUrl.uri();
        if (null == uri.getHost()) {
            return Util.immutableListOf(Proxy.NO_PROXY);
        }
        List<Proxy> proxiesOrNull = routeSelector.address.proxySelector().select(uri);
        if (null == proxiesOrNull || proxiesOrNull.isEmpty()) {
            return Util.immutableListOf(Proxy.NO_PROXY);
        }
        Intrinsics.checkNotNullExpressionValue(proxiesOrNull, "proxiesOrNull");
        return Util.toImmutableList(proxiesOrNull);
    }
    public boolean hasNext() {
        return hasNextProxy() || !this.postponedRoutes.isEmpty();
    }
    public Selection next() throws IOException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        ArrayList arrayList = new ArrayList();
        while (hasNextProxy()) {
            Proxy proxyNextProxy = nextProxy();
            Iterator<? extends InetSocketAddress> it = this.inetSocketAddresses.iterator();
            while (it.hasNext()) {
                Route route = new Route(this.address, proxyNextProxy, it.next());
                if (this.routeDatabase.shouldPostpone(route)) {
                    this.postponedRoutes.add(route);
                } else {
                    arrayList.add(route);
                }
            }
            if (!arrayList.isEmpty()) {
                break;
            }
        }
        if (arrayList.isEmpty()) {
            CollectionsKt.addAll(arrayList, this.postponedRoutes);
            this.postponedRoutes.clear();
        }
        return new Selection(arrayList);
    }
    private void resetNextProxy(HttpUrl httpUrl, Proxy proxy) {
        this.eventListener.proxySelectStart(call, httpUrl);
        List<Proxy> listResetNextProxyselectProxies = resetNextProxyselectProxies(proxy, httpUrl, this);
        this.proxies = listResetNextProxyselectProxies;
        this.nextProxyIndex = 0;
        this.eventListener.proxySelectEnd(call, httpUrl, listResetNextProxyselectProxies);
    }
    private boolean hasNextProxy() {
        return this.nextProxyIndex < this.proxies.size();
    }
    private Proxy nextProxy() throws IOException {
        if (!hasNextProxy()) {
            throw new SocketException("No route to " + this.address.url().host() + "; exhausted proxy configurations: " + this.proxies);
        }
        List<? extends Proxy> list = this.proxies;
        int i2 = this.nextProxyIndex;
        this.nextProxyIndex = i2 + 1;
        Proxy proxy = list.get(i2);
        resetNextInetSocketAddress(proxy);
        return proxy;
    }
    private void resetNextInetSocketAddress(Proxy proxy) throws IOException {
        String strHost;
        int iPort;
        List<InetAddress> listLookup;
        ArrayList arrayList = new ArrayList();
        this.inetSocketAddresses = arrayList;
        if (Proxy.Type.DIRECT == proxy.type() || Proxy.Type.SOCKS == proxy.type()) {
            strHost = this.address.url().host();
            iPort = this.address.url().port();
        } else {
            SocketAddress proxyAddress = proxy.address();
            if (!(proxyAddress instanceof final InetSocketAddress inetSocketAddress)) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Proxy.address() is not an InetSocketAddress: ", proxyAddress.getClass()));
            }
            Companion companion = Companion;
            Intrinsics.checkNotNullExpressionValue(proxyAddress, "proxyAddress");
            strHost = companion.getSocketHost(inetSocketAddress);
            iPort = inetSocketAddress.getPort();
        }
        if (1 > iPort || 65536 <= iPort) {
            throw new SocketException("No route to " + strHost + ':' + iPort + "; port is out of range");
        }
        if (Proxy.Type.SOCKS == proxy.type()) {
            arrayList.add(InetSocketAddress.createUnresolved(strHost, iPort));
            return;
        }
        if (Util.canParseAsIpAddress(strHost)) {
            listLookup = CollectionsKt.listOf(InetAddress.getByName(strHost));
        } else {
            this.eventListener.dnsStart(this.call, strHost);
            listLookup = this.address.dns().lookup(strHost);
            if (listLookup.isEmpty()) {
                throw new UnknownHostException(this.address.dns() + " returned no addresses for " + strHost);
            }
            this.eventListener.dnsEnd(this.call, strHost, listLookup);
        }
        Iterator<InetAddress> it = listLookup.iterator();
        while (it.hasNext()) {
            arrayList.add(new InetSocketAddress(it.next(), iPort));
        }
    }
    public static final class Selection {
        private final List<Route> routes;
        private int nextRouteIndex;

        public Selection(List<Route> routes) {
            Intrinsics.checkNotNullParameter(routes, "routes");
            this.routes = routes;
        }

        public List<Route> getRoutes() {
            return this.routes;
        }

        public boolean hasNext() {
            return this.nextRouteIndex < this.routes.size();
        }

        public Route next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            List<Route> list = this.routes;
            int i2 = this.nextRouteIndex;
            this.nextRouteIndex = i2 + 1;
            return list.get(i2);
        }
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
        public String getSocketHost(InetSocketAddress inetSocketAddress) {
            Intrinsics.checkNotNullParameter(inetSocketAddress, "<this>");
            InetAddress address = inetSocketAddress.getAddress();
            if (null == address) {
                String hostName = inetSocketAddress.getHostName();
                Intrinsics.checkNotNullExpressionValue(hostName, "hostName");
                return hostName;
            }
            String hostAddress = address.getHostAddress();
            Intrinsics.checkNotNullExpressionValue(hostAddress, "address.hostAddress");
            return hostAddress;
        }
    }
}
