package com.proje.mobilesales.features.notification.view.create;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.utils.UiState;
import com.proje.mobilesales.features.notification.model.NotificationUserSelectionModel;
import com.proje.mobilesales.features.notification.repository.NotificationCreateRepository;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;

/* compiled from: NotificationUserSelectionViewModel.kt */

public final class NotificationUserSelectionViewModel extends BaseViewModel {
    private final String TAG = "NotificationUserVM";
    private final MutableLiveData<UiState<List<NotificationUserSelectionModel>>> notificationUsersUiState = new MutableLiveData<>();
    private final NotificationCreateRepository repository;

    
    public NotificationUserSelectionViewModel(NotificationCreateRepository notificationCreateRepository) {
        super(notificationCreateRepository);
        Intrinsics.checkNotNullParameter(notificationCreateRepository, "notificationCreateRepository");
        this.repository = notificationCreateRepository;
    }

    public LiveData<UiState<List<NotificationUserSelectionModel>>> notificationUsersUiState() {
        return this.notificationUsersUiState;
    }

    public void getUsersConnectedToMe(String str) {
        Intrinsics.checkNotNullParameter(str, "searchText");
        this.notificationUsersUiState.setValue(UiState.Loading.INSTANCE);
        BuildersKt.launchdefault(ViewModelKt.getViewModelScope(this), null, null, new NotificationUserSelectionViewModelgetUsersConnectedToMe1(this, str, null), 3, null);
    }
}
