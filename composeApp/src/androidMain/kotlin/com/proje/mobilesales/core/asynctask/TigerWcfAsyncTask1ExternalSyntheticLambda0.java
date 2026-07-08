package com.proje.mobilesales.core.asynctask;

import com.proje.mobilesales.core.tigerwcf.TigerSelectResult;
import io.reactivex.functions.Function;
import retrofit2.Response;

public record TigerWcfAsyncTask1ExternalSyntheticLambda0(TigerWcfAsyncTask.AnonymousClass1 f0,
                                                         TigerSelectResult[] f1) implements Function {

    public Object apply(Object obj) {
        try {
            return this.f0.lambdaonNext0(this.f1, (Response) obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}