package okhttp3.internal.http;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Protocol;
import okhttp3.Response;

import java.io.IOException;
import java.net.ProtocolException;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;
import static kotlin.jvm.internal.Intrinsics.stringPlus;

public record StatusLine(Protocol protocol, int code, String message) {
    public static final Companion Companion = new Companion(null);
    public static final int HTTP_CONTINUE = 100;
    public static final int HTTP_MISDIRECTED_REQUEST = 421;
    public static final int HTTP_PERM_REDIRECT = 308;
    public static final int HTTP_TEMP_REDIRECT = 307;

    public StatusLine {
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        Intrinsics.checkNotNullParameter(message, "message");
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (Protocol.HTTP_1_0 == this.protocol) {
            sb.append("HTTP/1.0");
        } else {
            sb.append("HTTP/1.1");
        }
        sb.append(' ');
        sb.append(code);
        sb.append(' ');
        sb.append(message);
        final String string = sb.toString();
        checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public StatusLine get(final Response response) {
            Intrinsics.checkNotNullParameter(response, "response");
            return new StatusLine(response.protocol(), response.code(), response.message());
        }

        public StatusLine parse(final String statusLine) throws NumberFormatException, IOException {
            final Protocol protocol;
            final int i2;
            final String strSubstring;
            Intrinsics.checkNotNullParameter(statusLine, "statusLine");
            if (StringsKt.startsWith(statusLine, "HTTP/1.", false)) {
                i2 = 9;
                if (9 > statusLine.length() || ' ' != statusLine.charAt(8)) {
                    throw new ProtocolException(stringPlus("Unexpected status line: ", statusLine));
                }
                final int iCharAt = statusLine.charAt(7) - '0';
                if (0 == iCharAt) {
                    protocol = Protocol.HTTP_1_0;
                } else if (1 == iCharAt) {
                    protocol = Protocol.HTTP_1_1;
                } else {
                    throw new ProtocolException(stringPlus("Unexpected status line: ", statusLine));
                }
            } else if (StringsKt.startsWith(statusLine, "ICY ", false)) {
                protocol = Protocol.HTTP_1_0;
                i2 = 4;
            } else {
                throw new ProtocolException(stringPlus("Unexpected status line: ", statusLine));
            }
            final int i3 = i2 + 3;
            if (statusLine.length() < i3) {
                throw new ProtocolException(stringPlus("Unexpected status line: ", statusLine));
            }
            try {
                final String strSubstring2 = statusLine.substring(i2, i3);
                checkNotNullExpressionValue(strSubstring2, "this as java.lang.String\u2026ing(startIndex, endIndex)");
                final int i4 = Integer.parseInt(strSubstring2);
                if (statusLine.length() <= i3) {
                    strSubstring = "";
                } else {
                    if (' ' != statusLine.charAt(i3)) {
                        throw new ProtocolException(stringPlus("Unexpected status line: ", statusLine));
                    }
                    strSubstring = statusLine.substring(i2 + 4);
                    checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                }
                return new StatusLine(protocol, i4, strSubstring);
            } catch (final NumberFormatException unused) {
                throw new ProtocolException(stringPlus("Unexpected status line: ", statusLine));
            }
        }
    }
}
