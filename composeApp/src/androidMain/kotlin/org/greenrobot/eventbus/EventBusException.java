package org.greenrobot.eventbus;

public class EventBusException extends RuntimeException {
    private static final long serialVersionUID = -2912559384646531479L;
    public EventBusException(final String str) {
        super(str);
    }
    public EventBusException(final Throwable th) {
        super(th);
    }
    public EventBusException(final String str, final Throwable th) {
        super(str, th);
    }
}
