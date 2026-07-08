package io.reactivex.internal.disposables;

import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReferenceArray;


public final class ArrayCompositeDisposable extends AtomicReferenceArray<Disposable> implements Disposable {
    private static final long serialVersionUID = 2746389416410565408L;

    public ArrayCompositeDisposable(int i2) {
        super(i2);
    }

    public boolean setResource(int i2, Disposable disposable) {
        Disposable disposable2;
        do {
            disposable2 = get (i2);
            if (DisposableHelper.DISPOSED == disposable2) {
                disposable.dispose();
                return false;
            }
        } while (!compareAndSet (i2, disposable2, disposable));
        if (null == disposable2) {
            return true;
        }
        disposable2.dispose();
        return true;
    }

    public Disposable replaceResource(int i2, Disposable disposable) {
        Disposable disposable2;
        do {
            disposable2 = get (i2);
            if (DisposableHelper.DISPOSED == disposable2) {
                disposable.dispose();
                return null;
            }
        } while (!compareAndSet (i2, disposable2, disposable));
        return disposable2;
    }

    public void dispose() {
        Disposable andSet;
        if (DisposableHelper.DISPOSED != this.get(0)) {
            int length = length ();
            for (int i2 = 0; i2 < length; i2++) {
                Disposable disposable = get (i2);
                final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
                if (disposableHelper != disposable && disposableHelper != (andSet = this.getAndSet(i2, disposableHelper)) && null != andSet) {
                    andSet.dispose();
                }
            }
        }
    }

    public boolean isDisposed() {
        return DisposableHelper.DISPOSED == this.get(0);
    }
}
