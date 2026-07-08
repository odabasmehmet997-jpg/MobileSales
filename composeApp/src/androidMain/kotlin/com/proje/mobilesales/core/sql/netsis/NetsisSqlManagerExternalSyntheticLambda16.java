package com.proje.mobilesales.core.sql.netsis;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.penetration.model.Penetration;
import io.reactivex.functions.Consumer;
 
public final  class NetsisSqlManagerExternalSyntheticLambda16 implements Consumer {
    public final  ResponseListener f0 = null;

    public NetsisSqlManagerExternalSyntheticLambda16(ResponseListener<Penetration> responseListener) {
    }

    public void accept(Object obj) {
        this.f0.onResponse(obj);
    }

    @Override
    public Object invoke(Object obj) {

        return obj;
    }
}
