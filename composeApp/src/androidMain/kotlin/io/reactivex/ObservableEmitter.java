package io.reactivex;

import com.proje.mobilesales.features.model.Resource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;

public interface ObservableEmitter<T> extends Emitter<T> {
    boolean isDisposed();
    void setCancellable(Cancellable cancellable);
    void setDisposable(Disposable disposable);
    void onNext(Resource.Loading loading);
    void onNext(Resource.Error error);
}
