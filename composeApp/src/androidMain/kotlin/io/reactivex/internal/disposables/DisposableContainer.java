package io.reactivex.internal.disposables;

import io.reactivex.disposables.Disposable;
import okhttp3.Challenge;


public interface DisposableContainer {
    boolean add(Challenge disposable);

    boolean delete(Disposable disposable);

    boolean remove(Disposable disposable);
}
