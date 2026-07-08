package io.reactivex.internal.operators.mixed;

import io.reactivex.CompletableObserver;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableSwitchMapCompletableSwitchMapCompletableObserver<T> implements FlowableSubscriber<T>, Disposable {

    static final class SwitchMapInnerObserver extends AtomicReference<Disposable> implements CompletableObserver {
        private static final long serialVersionUID = -8003404460084760287L;
        final FlowableSwitchMapCompletableSwitchMapCompletableObserver<?> parent;

        SwitchMapInnerObserver(FlowableSwitchMapCompletableSwitchMapCompletableObserver<?> flowableSwitchMapCompletableSwitchMapCompletableObserver) {
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            throw null;
        }

        @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
        public void onComplete() {
            throw null;
        }

        void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
