package com.proje.mobilesales.core.sql.netsis;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import io.reactivex.functions.Consumer;

public record NetsisSqlManagerExternalSyntheticLambda39(ResponseListener f0) implements Consumer {
    public void accept(Object obj) {
        this.f0.onResponse(obj);
    }
}
