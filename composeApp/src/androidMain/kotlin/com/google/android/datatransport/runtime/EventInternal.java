package com.google.android.datatransport.runtime;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class EventInternal {
    protected abstract Map<String, String> getAutoMetadata();
    public abstract Integer getCode();
    public abstract EncodedPayload getEncodedPayload();
    public abstract long getEventMillis();
    public abstract String getTransportName();
    public abstract long getUptimeMillis();
    public final Map<String, String> getMetadata() {
        return Collections.unmodifiableMap(getAutoMetadata());
    }
    public final int getInteger(String str) {
        String str2 = getAutoMetadata().get(str);
        if (str2 == null) {
            return 0;
        }
        return Integer.parseInt(str2);
    }
    public final long getLong(String str) {
        String str2 = getAutoMetadata().get(str);
        if (str2 == null) {
            return 0L;
        }
        return Long.parseLong(str2);
    }
    public final String get(String str) {
        String str2 = getAutoMetadata().get(str);
        return str2 == null ? "" : str2;
    }
    public Builder toBuilder() {
        return new AutoValue_EventInternal.Builder().setTransportName(getTransportName()).setCode(getCode()).setEncodedPayload(getEncodedPayload()).setEventMillis(getEventMillis()).setUptimeMillis(getUptimeMillis()).setAutoMetadata(new HashMap<>(getAutoMetadata()));
    }
    public static Builder builder() {
        return new AutoValue_EventInternal.Builder().setAutoMetadata(new HashMap<>());
    }
    public static abstract class Builder {
        public abstract EventInternal build();
        protected abstract Map<String, String> getAutoMetadata();
        protected abstract Builder setAutoMetadata(Map<String, String> map);
        public abstract Builder setCode(Integer num);
        public abstract Builder setEncodedPayload(EncodedPayload encodedPayload);
        public abstract Builder setEventMillis(long j2);
        public abstract Builder setTransportName(String str);
        public abstract Builder setUptimeMillis(long j2);
        public final Builder addMetadata(String str, String str2) {
            getAutoMetadata().put(str, str2);
            return this;
        }
        public final Builder addMetadata(String str, long j2) {
            getAutoMetadata().put(str, String.valueOf(j2));
            return this;
        }
        public final Builder addMetadata(String str, int i2) {
            getAutoMetadata().put(str, String.valueOf(i2));
            return this;
        }
    }
}
