package rx;


import java.util.Objects;

public final class Notification<T> {
    private static final Notification<Void> ON_COMPLETED = new Notification<>(Kind.OnCompleted, null, null);
    private final Kind kind;
    private final Throwable throwable;
    private final T value;
    public enum Kind {
        OnNext,
        OnError,
        OnCompleted
    }
    private Notification(Kind kind, T t, Throwable th) {
        this.value = t;
        this.throwable = th;
        this.kind = kind;
    }
    public Throwable getThrowable() {
        return this.throwable;
    }
    public T getValue() {
        return this.value;
    }
    public boolean hasValue() {
        return isOnNext() && this.value != null;
    }
    public boolean hasThrowable() {
        return isOnError() && this.throwable != null;
    }
    public Kind getKind() {
        return this.kind;
    }
    public boolean isOnError() {
        return getKind() == Kind.OnError;
    }
    public boolean isOnNext() {
        return getKind() == Kind.OnNext;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append('[');
        sb.append(super.toString());
        sb.append(' ');
        sb.append(getKind());
        if (hasValue()) {
            sb.append(' ');
            sb.append(getValue());
        }
        if (hasThrowable()) {
            sb.append(' ');
            sb.append(getThrowable().getMessage());
        }
        sb.append(']');
        return sb.toString();
    }
    public int hashCode() {
        int iHashCode = getKind().hashCode();
        if (hasValue()) {
            iHashCode = (iHashCode * 31) + getValue().hashCode();
        }
        return hasThrowable() ? (iHashCode * 31) + getThrowable().hashCode() : iHashCode;
    }
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj.getClass() != Notification.class) {
            return false;
        }
        Notification notification = (Notification) obj;
        if (notification.getKind() != getKind()) {
            return false;
        }
        T t = this.value;
        T t2 = (T) notification.value;
        if (!Objects.equals(t, t2)) {
            return false;
        }
        Throwable th = this.throwable;
        Throwable th2 = notification.throwable;
        return Objects.equals(th, th2);
    }
}
