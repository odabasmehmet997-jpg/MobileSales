package com.fasterxml.jackson.databind.jsonFormatVisitors;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.fasterxml.jackson.annotation.JsonValue;

public enum JsonValueFormat {
    COLOR(TypedValues.Custom.S_COLOR),
    DATE("date"),
    DATE_TIME("date-time"),
    EMAIL(NotificationCompat.CATEGORY_EMAIL),
    HOST_NAME("host-name"),
    IP_ADDRESS("ip-address"),
    IPV6("ipv6"),
    PHONE("phone"),
    REGEX("regex"),
    STYLE("style"),
    TIME("time"),
    URI("uri"),
    UTC_MILLISEC("utc-millisec"),
    UUID("uuid");

    private final String _desc;

    JsonValueFormat(final String str) {
        _desc = str;
    }

    @Override // java.lang.Enum
    @JsonValue
    public String toString() {
        return _desc;
    }
}
