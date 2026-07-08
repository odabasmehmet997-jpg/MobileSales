package com.proje.mobilesales.core.asynctask;

import com.proje.mobilesales.core.tigerwcf.TigerSelectResult;
import io.reactivex.functions.Action;

public record TigerWcfAsyncTask1ExternalSyntheticLambda3(TigerWcfAsyncTask.AnonymousClass1 f0,
                                                         TigerSelectResult[] f1) implements Action {

    @Override
    public void run() {
        try {
            this.f0.lambdaonNext3(this.f1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}