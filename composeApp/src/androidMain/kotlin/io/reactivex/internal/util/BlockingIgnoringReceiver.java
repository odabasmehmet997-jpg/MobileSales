package io.reactivex.internal.util;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.concurrent.CountDownLatch;



public final class BlockingIgnoringReceiver extends CountDownLatch implements Consumer<Throwable>, Action {
    public Throwable error;
    public BlockingIgnoringReceiver() {
        super(1);
    }
    public void accept(final Throwable th) {
        error = th;
        this.countDown();
    }
    public Object invoke(Object obj) {
        return null;
    }
    public void run() {
        this.countDown();
    }
}
