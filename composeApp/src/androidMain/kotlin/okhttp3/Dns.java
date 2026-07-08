package okhttp3;

import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;
import static kotlin.jvm.internal.Intrinsics.stringPlus;

public interface Dns {
    Dns SYSTEM = new Companion.DnsSystem();    Companion Companion = Dns.Companion.INSTANCE;
    List<InetAddress> lookup(String str) throws UnknownHostException;
    final class Companion {
        static final Companion INSTANCE = new Companion();
        private Companion() {
        }
        private static final class DnsSystem implements Dns {
            public List<InetAddress> lookup(String hostname) throws UnknownHostException {
                Intrinsics.checkNotNullParameter(hostname, "hostname");
                try {
                    InetAddress[] allByName = InetAddress.getAllByName(hostname);
                    checkNotNullExpressionValue(allByName, "getAllByName(hostname)");
                    return ArraysKt.toList(allByName);
                } catch (NullPointerException e2) {
                    UnknownHostException unknownHostException = new UnknownHostException(stringPlus("Broken system behaviour for dns lookup of ", hostname));
                    unknownHostException.initCause(e2);
                    throw unknownHostException;
                }
            }
        }
    }


}
