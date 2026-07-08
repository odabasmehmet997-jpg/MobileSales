package com.proje.mobilesales.features.notification.view.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.proje.mobilesales.core.utils.UiState;
import com.proje.mobilesales.features.notification.model.NotificationDetailModel;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import com.proje.mobilesales.features.notification.repository.NotificationDetailRepository;
import com.proje.mobilesales.features.notification.view.BaseNotificationViewModel;
import java.util.List;
import kotlin.Triple;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;

/* compiled from: NotificationDetailViewModel.kt */

public final class NotificationDetailViewModel extends BaseNotificationViewModel {
    private final String TAG = "NotificationDetailVM";
    private final MutableLiveData<UiState<NotificationDetailModel>> notificationDetailUiState = new MutableLiveData<>();
    private final NotificationDetailRepository repository;

    public NotificationDetailViewModel(NotificationDetailRepository notificationDetailRepository) {
        super(notificationDetailRepository);
        Intrinsics.checkNotNullParameter(notificationDetailRepository, "repository");
        this.repository = notificationDetailRepository;
    }

    public LiveData<UiState<NotificationDetailModel>> notificationDetailUiState() {
        return this.notificationDetailUiState;
    }

    public void getNotificationDetails(int i, int i2) {
        Triple<Deferred<NotificationModel>, Deferred<List<NotifiedUserModel>>, Deferred<NotifiedUserModel>> notificationData = getNotificationData(i, i2);
        Job unused = BuildersKt__Builders_commonKt.launchdefault(ViewModelKt.getViewModelScope(this), null, null, new NotificationDetailViewModelgetNotificationDetails1(this, notificationData.component1(), notificationData.component2(), notificationData.component3(), i2, null), 3, null);
    }
    public Object controlNotifiedUser(com.proje.mobilesales.features.notification.model.NotifiedUserModel r7, int r8, com.proje.mobilesales.features.notification.model.NotificationDetailModel r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.notification.view.detail.NotificationDetailViewModel.controlNotifiedUser(com.proje.mobilesales.features.notification.model.NotifiedUserModel, int, com.proje.mobilesales.features.notification.model.NotificationDetailModel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private Triple<Deferred<NotificationModel>, Deferred<List<NotifiedUserModel>>, Deferred<NotifiedUserModel>> getNotificationData(int i, int i2) {
        return new Triple<>(BuildersKt__Builders_commonKt.asyncdefault(ViewModelKt.getViewModelScope(this), null, null, new C3040xea44d9fe(this, i, null), 3, null), BuildersKt__Builders_commonKt.asyncdefault(ViewModelKt.getViewModelScope(this), null, null, new C3044x6af0b45b(i2, this, i, null), 3, null), BuildersKt__Builders_commonKt.asyncdefault(ViewModelKt.getViewModelScope(this), null, null, new C3042x141f466(i2, this, null), 3, null));
    }
}
