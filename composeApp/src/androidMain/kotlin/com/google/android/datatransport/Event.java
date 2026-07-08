package com.google.android.datatransport;

public abstract class Event<T> {
    public abstract Integer getCode();
    public abstract T getPayload();
    public abstract Priority getPriority();
    public static <T> Event<T> ofTelemetry(T t) {
        return new AutoValue_Event(null, t, Priority.VERY_LOW);
    }
    public static <T> Event<T> ofUrgent(T t) {
        return new AutoValue_Event(null, t, Priority.HIGHEST);
    }
}
