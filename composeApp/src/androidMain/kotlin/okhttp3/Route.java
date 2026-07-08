package okhttp3;

import java.net.InetSocketAddress;
import java.net.Proxy;
import kotlin.jvm.internal.Intrinsics;

public final class Route {
  private final Address address;
  private final Proxy proxy;
  private final InetSocketAddress socketAddress;
  public Route(Address address, Proxy proxy, InetSocketAddress socketAddress) {
    Intrinsics.checkNotNullParameter(address, "address");
    Intrinsics.checkNotNullParameter(proxy, "proxy");
    Intrinsics.checkNotNullParameter(socketAddress, "socketAddress");
    this.address = address;
    this.proxy = proxy;
    this.socketAddress = socketAddress;
  }
  public Address address() {
    return this.address;
  }
  public Proxy proxy() {
    return this.proxy;
  }
  public InetSocketAddress socketAddress() {
    return this.socketAddress;
  }
  public Address deprecated_address() {
    return this.address;
  }
  public Proxy deprecated_proxy() {
    return this.proxy;
  }
  public InetSocketAddress deprecated_socketAddress() {
    return this.socketAddress;
  }
  public boolean requiresTunnel() {
    return this.address.sslSocketFactory() != null && this.proxy.type() == Proxy.Type.HTTP;
  }
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Route) {
      Route route = (Route) obj;
      if (Intrinsics.areEqual(route.address, this.address) && Intrinsics.areEqual(route.proxy, this.proxy) && Intrinsics.areEqual(route.socketAddress, this.socketAddress)) {
        return true;
      }
    }
    return false;
  }
  @Override
  public int hashCode() {
    return ((((527 + this.address.hashCode()) * 31) + this.proxy.hashCode()) * 31) + this.socketAddress.hashCode();
  }
  @Override
  public String toString() {
    return "Route{" + this.socketAddress + '}';
  }
}
