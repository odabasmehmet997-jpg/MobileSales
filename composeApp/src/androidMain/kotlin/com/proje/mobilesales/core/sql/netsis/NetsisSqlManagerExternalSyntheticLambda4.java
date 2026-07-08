package com.proje.mobilesales.core.sql.netsis;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.sales.model.Sales;
import io.reactivex.functions.Consumer;
 
public final  class NetsisSqlManagerExternalSyntheticLambda4 implements Consumer {
    public final  ResponseListener f0 = null;
    public void accept(Object obj) {
        this.f0.onResponse((Sales) obj);
    }

    @Override
    public Object invoke(Object obj) {

        return obj;
    }
}
