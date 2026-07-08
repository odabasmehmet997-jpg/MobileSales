package io.reactivex.internal.operators.single;

import java.util.NoSuchElementException;
import java.util.concurrent.Callable;



enum SingleInternalHelperNoSuchElementCallable implements Callable<NoSuchElementException> {
    INSTANCE;

    @Override // java.util.concurrent.Callable
    public NoSuchElementException call() throws Exception {
        return new NoSuchElementException();
    }
}
