package com.proje.mobilesales.core.asynctask;

import com.proje.mobilesales.core.tigerwcf.TigerSelectResult;
import io.reactivex.functions.Predicate;

public record TigerWcfAsyncTask1ExternalSyntheticLambda2(TigerWcfAsyncTask.AnonymousClass1 f0,
                                                         TigerSelectResult[] f1) implements Predicate {

    @Override
    public boolean test(Object obj) {
        try {
            return this.f0.lambdaonNext2(this.f1, (TigerSelectResult) obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}