package com.proje.mobilesales.core.base;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import io.reactivex.functions.Consumer;
import java.util.List;
 
final   class BaseErpExternalSyntheticLambda12 implements Consumer {
    public final   ResponseListener f0 = null;
    public void accept(Object obj) {
        this.f0.onResponse(obj);
    }
    @Override
    public Object invoke(Object obj) {

        return obj;
    }
}
