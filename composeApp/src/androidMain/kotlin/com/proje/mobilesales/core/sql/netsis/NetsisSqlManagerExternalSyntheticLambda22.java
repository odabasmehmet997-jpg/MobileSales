package com.proje.mobilesales.core.sql.netsis;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.visit.model.Visit;
import io.reactivex.functions.Consumer;
 
public final   class NetsisSqlManagerExternalSyntheticLambda22 implements Consumer {
    public final   ResponseListener f0 = null;

    public NetsisSqlManagerExternalSyntheticLambda22(ResponseListener<Visit> responseListener) {
    }

    public void accept(Object obj) {
        try {
            this.f0.onResponse(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object invoke(Object obj) {
        try {
            accept(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}
