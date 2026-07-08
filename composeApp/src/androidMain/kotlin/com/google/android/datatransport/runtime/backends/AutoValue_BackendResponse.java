package com.google.android.datatransport.runtime.backends;

final class AutoValue_BackendResponse extends BackendResponse {
    private final long nextRequestWaitMillis;
    private final BackendResponse.Status status;
    AutoValue_BackendResponse(BackendResponse.Status status, long j2) {
        if (status == null) {
            throw new NullPointerException("Null status");
        }
        this.status = status;
        this.nextRequestWaitMillis = j2;
    }
    public BackendResponse.Status getStatus() {
        return this.status;
    }
    public long getNextRequestWaitMillis() {
        return this.nextRequestWaitMillis;
    }
    public String toString() {
        return "BackendResponse{status=" + this.status + ", nextRequestWaitMillis=" + this.nextRequestWaitMillis + "}";
    }
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BackendResponse)) {
            return false;
        }
        BackendResponse backendResponse = (BackendResponse) obj;
        return this.status.equals(backendResponse.getStatus()) && this.nextRequestWaitMillis == backendResponse.getNextRequestWaitMillis();
    }
    public int hashCode() {
        int iHashCode = (this.status.hashCode() ^ 1000003) * 1000003;
        long j2 = this.nextRequestWaitMillis;
        return ((int) (j2 ^ (j2 >>> 32))) ^ iHashCode;
    }
}
