package io.reactivex.internal.functions;

import com.proje.mobilesales.R;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class Array2Func implements Function<Object[], R> {
    public <T1, R, T2> Array2Func(BiFunction<? super T1, ? super T2, ? extends R> biFunction) {
    }

    @Override
    public R apply(Object t) throws Exception {
        return null;
    }
}
