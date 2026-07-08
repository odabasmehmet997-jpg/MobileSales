package okhttp3.internal.ws;

import java.io.IOException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.internal.Util;

public final class WebSocketExtensions {
    public static final Companion Companion = new Companion(null);
    public       static final String HEADER_WEB_SOCKET_EXTENSION = "Sec-WebSocket-Extensions";
    public final Integer clientMaxWindowBits;
    public final boolean clientNoContextTakeover;
    public final boolean perMessageDeflate;
    public final Integer serverMaxWindowBits;
    public final boolean serverNoContextTakeover;
    public final boolean unknownValues;
    private boolean r0;

    public WebSocketExtensions() {
        this(false, null, false, null, false, false, 63, null);
    }
    public static   WebSocketExtensions copy$default(WebSocketExtensions webSocketExtensions, boolean z, Integer num, boolean z2, Integer num2, boolean z3, boolean z4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = webSocketExtensions.perMessageDeflate;
        }
        if ((i2 & 2) != 0) {
            num = webSocketExtensions.clientMaxWindowBits;
        }
        Integer num3 = num;
        if ((i2 & 4) != 0) {
            z2 = webSocketExtensions.clientNoContextTakeover;
        }
        boolean z5 = z2;
        if ((i2 & 8) != 0) {
            num2 = webSocketExtensions.serverMaxWindowBits;
        }
        Integer num4 = num2;
        if ((i2 & 16) != 0) {
            z3 = webSocketExtensions.serverNoContextTakeover;
        }
        boolean z6 = z3;
        if ((i2 & 32) != 0) {
            z4 = webSocketExtensions.unknownValues;
        }
        return webSocketExtensions.copy(z, num3, z5, num4, z6, z4);
    }
    public boolean component1() {
        return this.perMessageDeflate;
    }
    public Integer component2() {
        return this.clientMaxWindowBits;
    }
    public boolean component3() {
        return this.clientNoContextTakeover;
    }
    public Integer component4() {
        return this.serverMaxWindowBits;
    }
    public boolean component5() {
        return this.serverNoContextTakeover;
    }
    public boolean component6() {
        return this.unknownValues;
    }
    public WebSocketExtensions copy(boolean z, Integer num, boolean z2, Integer num2, boolean z3, boolean z4) {
        return new WebSocketExtensions(z, num, z2, num2, z3, z4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WebSocketExtensions)) {
            return false;
        }
        WebSocketExtensions webSocketExtensions = (WebSocketExtensions) obj;
        return this.perMessageDeflate == webSocketExtensions.perMessageDeflate && Intrinsics.areEqual(this.clientMaxWindowBits, webSocketExtensions.clientMaxWindowBits) && this.clientNoContextTakeover == webSocketExtensions.clientNoContextTakeover && Intrinsics.areEqual(this.serverMaxWindowBits, webSocketExtensions.serverMaxWindowBits) && this.serverNoContextTakeover == webSocketExtensions.serverNoContextTakeover && this.unknownValues == webSocketExtensions.unknownValues;
    }
    public int hashCode() {
        boolean z = this.perMessageDeflate;
        r0 = z;
        if (z) { r0 = 1; }
        int i2 = r0 * 31;
        Integer num = this.clientMaxWindowBits;
        int iHashCode = (i2 + (num == null ? 0 : num.hashCode())) * 31;
        boolean z2 = this.clientNoContextTakeover;
        r2 = z2;
        if (z2) {
            r2 = 1;
        }
        int i3 = (iHashCode + r2) * 31;
        Integer num2 = this.serverMaxWindowBits;
        int iHashCode2 = (i3 + (num2 != null ? num2.hashCode() : 0)) * 31;
        boolean z3 = this.serverNoContextTakeover;
        r22 = z3;
        if (z3) {
            r22 = 1;
        }
        int i4 = (iHashCode2 + r22) * 31;
        boolean z4 = this.unknownValues;
        return i4 + (z4 ? 1 : 0 );
    }
    public int hashCode1() {
        final boolean z = perMessageDeflate;
        r0 = z;
        if (z) { r0 = 1; }
        int i2 = r0 * 31;
        final Integer num = clientMaxWindowBits;
        final int iHashCode = (i2 + (null == num ? 0 : num.hashCode())) * 31;
        i2 = clientNoContextTakeover;
        int i3 = i2;
        if (0 != i2) {  i3 = 1;}
        final int i4 = (iHashCode + i3) * 31;
        final Integer num2 = serverMaxWindowBits;
        final int iHashCode2 = (i4 + (null != num2 ? num2.hashCode() : 0)) * 31;
        i2 = serverNoContextTakeover;
        int i5 = i2;
        if (0 != i2) {
            i5 = 1;
        }
        final int i6 = (iHashCode2 + i5) * 31;
        final boolean z2 = unknownValues;
        return i6 + (z2 ? 1 : z2 ? 1 : 0);
    }
    public String toString() {
        return "WebSocketExtensions(perMessageDeflate=" + this.perMessageDeflate + ", clientMaxWindowBits=" + this.clientMaxWindowBits + ", clientNoContextTakeover=" + this.clientNoContextTakeover + ", serverMaxWindowBits=" + this.serverMaxWindowBits + ", serverNoContextTakeover=" + this.serverNoContextTakeover + ", unknownValues=" + this.unknownValues + ')';
    }
    public WebSocketExtensions(boolean z, Integer num, boolean z2, Integer num2, boolean z3, boolean z4) {
        this.perMessageDeflate = z;
        this.clientMaxWindowBits = num;
        this.clientNoContextTakeover = z2;
        this.serverMaxWindowBits = num2;
        this.serverNoContextTakeover = z3;
        this.unknownValues = z4;
    }
    public  WebSocketExtensions(boolean z, Integer num, boolean z2, Integer num2, boolean z3, boolean z4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) == 0 && z, (i2 & 2) != 0 ? null : num, (i2 & 4) == 0 && z2, (i2 & 8) != 0 ? null : num2, (i2 & 16) == 0 && z3, (i2 & 32) == 0 && z4);
    }
    public boolean noContextTakeover(boolean z) {
        if (z) {
            return this.clientNoContextTakeover;
        }
        return this.serverNoContextTakeover;
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public WebSocketExtensions parse(Headers responseHeaders) throws IOException {
            Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders");
            int size = responseHeaders.size();
            int i2 = 0;
            boolean z = false;
            Integer intOrNull = null;
            boolean z2 = false;
            Integer intOrNull2 = null;
            boolean z3 = false;
            boolean z4 = false;
            while (i2 < size) {
                int i3 = i2 + 1;
                if (StringsKt.equals(responseHeaders.name(i2), WebSocketExtensions.HEADER_WEB_SOCKET_EXTENSION, true)) {
                    String strValue = responseHeaders.value(i2);
                    int i4 = 0;
                    while (i4 < strValue.length()) {
                        int iDelimiterOffset$default = Util.delimiterOffset$default(strValue, ',', i4, 0, 4, null);
                        int iDelimiterOffset = Util.delimiterOffset(strValue, ';', i4, iDelimiterOffset$default);
                        String strTrimSubstring = Util.trimSubstring(strValue, i4, iDelimiterOffset);
                        int i5 = iDelimiterOffset + 1;
                        if (StringsKt.equals(strTrimSubstring, "permessage-deflate", true)) {
                            if (z) {
                                z4 = true;
                            }
                            i4 = i5;
                            while (i4 < iDelimiterOffset$default) {
                                int iDelimiterOffset2 = Util.delimiterOffset(strValue, ';', i4, iDelimiterOffset$default);
                                int iDelimiterOffset3 = Util.delimiterOffset(strValue, '=', i4, iDelimiterOffset2);
                                String strTrimSubstring2 = Util.trimSubstring(strValue, i4, iDelimiterOffset3);
                                String strRemoveSurrounding = iDelimiterOffset3 < iDelimiterOffset2 ? StringsKt.removeSurrounding(Util.trimSubstring(strValue, iDelimiterOffset3 + 1, iDelimiterOffset2), "\"") : null;
                                i4 = iDelimiterOffset2 + 1;
                                if (StringsKt.equals(strTrimSubstring2, "client_max_window_bits", true)) {
                                    if (intOrNull != null) {
                                        z4 = true;
                                    }
                                    intOrNull = strRemoveSurrounding == null ? null : StringsKt.toIntOrNull(strRemoveSurrounding);
                                    if (intOrNull == null) {
                                        z4 = true;
                                    }
                                } else if (StringsKt.equals(strTrimSubstring2, "client_no_context_takeover", true)) {
                                    if (z2) {
                                        z4 = true;
                                    }
                                    if (strRemoveSurrounding != null) {
                                        z4 = true;
                                    }
                                    z2 = true;
                                } else if (StringsKt.equals(strTrimSubstring2, "server_max_window_bits", true)) {
                                    if (intOrNull2 != null) {
                                        z4 = true;
                                    }
                                    intOrNull2 = strRemoveSurrounding == null ? null : StringsKt.toIntOrNull(strRemoveSurrounding);
                                    if (intOrNull2 == null) {
                                        z4 = true;
                                    }
                                } else if (StringsKt.equals(strTrimSubstring2, "server_no_context_takeover", true)) {
                                    if (z3) {
                                        z4 = true;
                                    }
                                    if (strRemoveSurrounding != null) {
                                        z4 = true;
                                    }
                                    z3 = true;
                                } else {
                                    z4 = true;
                                }
                            }
                            z = true;
                        } else {
                            i4 = i5;
                            z4 = true;
                        }
                    }
                }
                i2 = i3;
            }
            return new WebSocketExtensions(z, intOrNull, z2, intOrNull2, z3, z4);
        }
    }
}
