package io.reactivex.functions;



public interface BiConsumer<T1, T2> {
    void accept(? super U t1, Object t2) throws Exception;

    void accept(T1 u, Object t);
}
