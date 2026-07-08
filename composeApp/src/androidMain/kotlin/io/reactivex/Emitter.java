package io.reactivex;


import com.proje.mobilesales.features.model.Resource;


public interface Emitter<T> {
    void onComplete();

    void onError(Throwable th);

    void onNext(Resource.Success t);
}
