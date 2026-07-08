package io.reactivex.internal.util;

import io.reactivex.functions.BiFunction;
import org.jetbrains.annotations.UnknownNullability;

import java.util.List;

 
public enum ListAddBiConsumer implements BiFunction<List, Object, List> {
    INSTANCE;

    public static <T> ListAddBiConsumer instance() {
        return ListAddBiConsumer.INSTANCE;
    }
 
    public List apply(final @UnknownNullability List list, final Object obj) throws Exception {
        list.add(obj);
        return list;
    }
}
