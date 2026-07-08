package com.google.android.datatransport.cct.internal;

final class AutoValue_LogResponse extends LogResponse {
    private final long nextRequestWaitMillis;
    AutoValue_LogResponse(long j2) {
        this.nextRequestWaitMillis = j2;
    }
    public long getNextRequestWaitMillis() {
        return this.nextRequestWaitMillis;
    }
    public String toString() {
        return "LogResponse{nextRequestWaitMillis=" + this.nextRequestWaitMillis + "}";
    }
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogResponse)) {
            return false;
        }
        return this.nextRequestWaitMillis == ((LogResponse) obj).getNextRequestWaitMillis();
    }
    public int hashCode() {
        long j2 = this.nextRequestWaitMillis;
        return ((int) (j2 ^ (j2 >>> 32))) ^ 1000003;
    }
}
