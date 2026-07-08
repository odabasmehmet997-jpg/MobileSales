package io.reactivex;


import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public interface ObservableSource<T> {
    Disposable subscribe(Observer<DataResponse<ItemSlip>> observer);

    Observable<Object> flatMap(Function function);
}
