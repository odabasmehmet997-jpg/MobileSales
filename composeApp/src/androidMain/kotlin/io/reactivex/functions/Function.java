package io.reactivex.functions;



public interface Function<T, R> {
    R apply(Object t) throws Exception;

    Object invoke(Object obj);
}
