package okhttp3.internal.connection;

import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.*;
import okhttp3.internal.Util;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;

import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

public final class ExchangeFinder {
    private final Address address;
    private final RealCall call;
    private final RealConnectionPool connectionPool;
    private final EventListener eventListener;
    private int connectionShutdownCount;
    private Route nextRouteToTry;
    private int otherFailureCount;
    private int refusedStreamCount;
    private RouteSelector.Selection routeSelection;
    private RouteSelector routeSelector;
    public ExchangeFinder(RealConnectionPool connectionPool, Address address, RealCall call, EventListener eventListener) {
        Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        this.connectionPool = connectionPool;
        this.address = address;
        this.call = call;
        this.eventListener = eventListener;
    }
    public  Address getAddressokhttp() {
        return this.address;
    }
    public ExchangeCodec find(OkHttpClient client, RealInterceptorChain chain) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(chain, "chain");
        try {
            return findHealthyConnection(chain.getConnectTimeoutMillisokhttp(), chain.getReadTimeoutMillisokhttp(), chain.getWriteTimeoutMillisokhttp(), client.pingIntervalMillis(), client.retryOnConnectionFailure(), !Intrinsics.areEqual(chain.getRequestokhttp().method(), "GET")).newCodecokhttp(client, chain);
        } catch (IOException e2) {
            trackFailure(e2);
            throw new RouteException(e2);
        } catch (RouteException e3) {
            trackFailure(e3.getLastConnectException());
            throw e3;
        }
    }
    private RealConnection findHealthyConnection(int i2, int i3, int i4, int i5, boolean z, boolean z2) throws IOException {
        while (true) {
            RealConnection realConnectionFindConnection = findConnection(i2, i3, i4, i5, z);
            if (realConnectionFindConnection.isHealthy(z2)) {
                return realConnectionFindConnection;
            }
            realConnectionFindConnection.noNewExchangesokhttp();
            if (null == nextRouteToTry) {
                RouteSelector.Selection selection = this.routeSelection;
                if (null == selection || selection.hasNext()) {
                    continue;
                } else {
                    RouteSelector routeSelector = this.routeSelector;
                    if (!(null == routeSelector || routeSelector.hasNext())) {
                        throw new IOException("exhausted all routes");
                    }
                }
            }
        }
    }
    private RealConnection findConnection(int i2, int i3, int i4, int i5, boolean z) throws IOException {
        List<Route> routes = Collections.emptyList();
        Socket socketReleaseConnectionNoEventsokhttp;
        if (this.call.isCanceled()) {
            throw new IOException("Canceled");
        }
        RealConnection connection = this.call.getConnection();
        if (null != connection) {
            synchronized (connection) {
                try {
                    socketReleaseConnectionNoEventsokhttp = (connection.getNoNewExchanges() || !sameHostAndPort(connection.route().address().url())) ? this.call.releaseConnectionNoEventsokhttp() : null;
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (null != call.getConnection()) {
                if (null == socketReleaseConnectionNoEventsokhttp) {
                    return connection;
                }
                throw new IllegalStateException("Check failed.");
            }
            if (null != socketReleaseConnectionNoEventsokhttp) {
                Util.closeQuietly(socketReleaseConnectionNoEventsokhttp);
            }
            this.eventListener.connectionReleased(this.call, connection);
        }
        this.refusedStreamCount = 0;
        this.connectionShutdownCount = 0;
        this.otherFailureCount = 0;
        if (this.connectionPool.callAcquirePooledConnection(this.address, this.call, null, false)) {
            RealConnection connection2 = this.call.getConnection();
            Intrinsics.checkNotNull(connection2);
            this.eventListener.connectionAcquired(this.call, connection2);
            return connection2;
        }
        Route next = this.nextRouteToTry;
        try {
            RealConnection realConnection = null;
            if (null != next) {
                Intrinsics.checkNotNull(next);
                this.nextRouteToTry = null;
            } else {
                RouteSelector.Selection selection = this.routeSelection;
                if (null != selection) {
                    Intrinsics.checkNotNull(selection);
                    if (selection.hasNext()) {
                        RouteSelector.Selection selection2 = this.routeSelection;
                        Intrinsics.checkNotNull(selection2);
                        next = selection2.next();
                    }
                }
                RouteSelector routeSelector = this.routeSelector;
                if (null == routeSelector) {
                    routeSelector = new RouteSelector(this.address, this.call.getClient().getRouteDatabase(), this.call, this.eventListener);
                    this.routeSelector = routeSelector;
                }
                RouteSelector.Selection next2 = routeSelector.next();
                this.routeSelection = next2;
                routes = next2.getRoutes();
                if (this.call.isCanceled()) {
                    throw new IOException("Canceled");
                }
                if (this.connectionPool.callAcquirePooledConnection(this.address, this.call, routes, false)) {
                    RealConnection connection3 = this.call.getConnection();
                    Intrinsics.checkNotNull(connection3);
                    this.eventListener.connectionAcquired(this.call, connection3);
                    return connection3;
                }
                next = next2.next();
                realConnection = new RealConnection(this.connectionPool, next);
                this.call.setConnectionToCancel(realConnection);
                realConnection.connect(i2, i3, i4, i5, z, this.call, this.eventListener);
                this.call.setConnectionToCancel(null);
                this.call.getClient().getRouteDatabase().connected(realConnection.route());
                if (!this.connectionPool.callAcquirePooledConnection(this.address, this.call, routes, true)) {
                    RealConnection connection4 = this.call.getConnection();
                    Intrinsics.checkNotNull(connection4);
                    this.nextRouteToTry = next;
                    Util.closeQuietly(realConnection.socket());
                    this.eventListener.connectionAcquired(this.call, connection4);
                    return connection4;
                }
                synchronized (realConnection) {
                    this.connectionPool.put(realConnection);
                    this.call.acquireConnectionNoEvents(realConnection);
                    Unit unit2 = Unit.INSTANCE;
                }
                this.eventListener.connectionAcquired(this.call, realConnection);
                return realConnection;
            }
            realConnection.connect(i2, i3, i4, i5, z, this.call, this.eventListener);
            this.call.setConnectionToCancel(null);
            this.call.getClient().getRouteDatabase().connected(realConnection.route());
            if (!this.connectionPool.callAcquirePooledConnection(this.address, this.call, routes, true)) {
            }
        } catch (Throwable th2) {
            this.call.setConnectionToCancel(null);
            try {
                throw th2;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        routes = null;
        RealConnection realConnection2 = new RealConnection(this.connectionPool, next);
        this.call.setConnectionToCancel(realConnection2);
        return connection;
    }
    public void trackFailure(IOException e2) {
        Intrinsics.checkNotNullParameter(e2, "e");
        this.nextRouteToTry = null;
        if ((e2 instanceof StreamResetException) && ErrorCode.REFUSED_STREAM == ((StreamResetException) e2).errorCode) {
            this.refusedStreamCount++;
        } else if (e2 instanceof ConnectionShutdownException) {
            this.connectionShutdownCount++;
        } else {
            this.otherFailureCount++;
        }
    }
    public boolean retryAfterFailure() {
        RouteSelector routeSelector;
        if (0 == refusedStreamCount && 0 == connectionShutdownCount && 0 == otherFailureCount) {
            return false;
        }
        if (null != nextRouteToTry) {
            return true;
        }
        Route routeRetryRoute = retryRoute();
        if (null != routeRetryRoute) {
            this.nextRouteToTry = routeRetryRoute;
            return true;
        }
        RouteSelector.Selection selection = this.routeSelection;
        if ((null != selection && selection.hasNext()) || null == (routeSelector = this.routeSelector)) {
            return true;
        }
        return routeSelector.hasNext();
    }
    private Route retryRoute() {
        RealConnection connection;
        if (1 < refusedStreamCount || 1 < connectionShutdownCount || 0 < otherFailureCount || null == (connection = call.getConnection())) {
            return null;
        }
        synchronized (connection) {
            if (0 != connection.getRouteFailureCountokhttp()) {
                return null;
            }
            if (Util.canReuseConnectionFor(connection.route().address().url(), address.url())) {
                return connection.route();
            }
            return null;
        }
    }
    public boolean sameHostAndPort(HttpUrl url) {
        Intrinsics.checkNotNullParameter(url, "url");
        HttpUrl httpUrlUrl = this.address.url();
        return url.port() == httpUrlUrl.port() && Intrinsics.areEqual(url.host(), httpUrlUrl.host());
    }
}
