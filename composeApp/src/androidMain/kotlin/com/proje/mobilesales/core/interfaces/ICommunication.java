package com.proje.mobilesales.core.interfaces;

import com.proje.mobilesales.core.base.BaseResult;
import com.proje.mobilesales.features.model.GroupItem;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.List;



public interface ICommunication {
    Observable<BaseResult> getObservableResult(GroupItem groupItem);

    Disposable transferFiche(GroupItem groupItem, ResponseListener<GroupItem> responseListener);

    Disposable transferFiche(GroupItem groupItem, ResponseListener<GroupItem> responseListener, boolean z);

    Disposable transferFiches(List<GroupItem> list, ResponseListener<GroupItem> responseListener);

    Disposable transferFiches(List<GroupItem> list, ResponseListener<GroupItem> responseListener, boolean z);
}
