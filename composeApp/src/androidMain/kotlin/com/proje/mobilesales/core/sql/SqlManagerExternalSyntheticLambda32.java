package com.proje.mobilesales.core.sql;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import io.reactivex.functions.Consumer;
public record SqlManagerExternalSyntheticLambda32(ResponseListener f0) implements Consumer {
   public void accept(Object obj) {
        this.f0.onResponse(obj);
    }
   public Object invoke(Object obj) {
    return null;
  }
}
