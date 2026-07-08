package dagger;

public interface Lazy<T> {
    T get();

    default Object getValue() {
        return get();
    }
}
