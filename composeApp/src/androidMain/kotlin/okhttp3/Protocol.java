package okhttp3;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import java.io.IOException;

import static kotlin.jvm.internal.Intrinsics.areEqual;
import static kotlin.jvm.internal.Intrinsics.stringPlus;

public enum Protocol {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
    QUIC("quic");

    public static final Companion Companion = new Companion(null);
    private final String protocol;

    Protocol(final String str) {
        protocol = str;
    }

    public static final Protocol get(final String str) throws IOException {
        return Protocol.Companion.get(str);
    }
    public String toString() {
        return protocol;
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public Protocol get(final String protocol) throws IOException {
            Intrinsics.checkNotNullParameter(protocol, "protocol");
            Protocol protocol2 = HTTP_1_0;
            if (!areEqual(protocol, protocol2.protocol)) {
                protocol2 = HTTP_1_1;
                if (!areEqual(protocol, protocol2.protocol)) {
                    protocol2 = H2_PRIOR_KNOWLEDGE;
                    if (!areEqual(protocol, protocol2.protocol)) {
                        protocol2 = HTTP_2;
                        if (!areEqual(protocol, protocol2.protocol)) {
                            protocol2 = SPDY_3;
                            if (!areEqual(protocol, protocol2.protocol)) {
                                protocol2 = QUIC;
                                if (!areEqual(protocol, protocol2.protocol)) {
                                    throw new IOException(stringPlus("Unexpected protocol: ", protocol));
                                }
                            }
                        }
                    }
                }
            }
            return protocol2;
        }
    }
}
