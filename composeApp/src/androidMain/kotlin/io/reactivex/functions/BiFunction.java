package io.reactivex.functions;



public interface BiFunction<T1, T2, R> {
    R apply(R t1, Object t2) throws Exception;
}
