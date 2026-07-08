package com.proje.mobilesales.core.asynctask;

import com.proje.mobilesales.core.tigerwcf.RequestEnvelope;
import com.proje.mobilesales.core.tigerwcf.TigerSelectResult;
import io.reactivex.functions.Function;
import retrofit2.Response;

public record TigerWcfAsyncTask1ExternalSyntheticLambda1(TigerWcfAsyncTask.AnonymousClass1 f0, TigerSelectResult[] f1,
                                                         RequestEnvelope[] f2) implements Function {

    @Override
    public Object apply(Object obj) {
        try {
            return this.f0.lambdaonNext1(this.f1, this.f2, (Response) obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object invoke(Object obj) {
        return null;
    }
}