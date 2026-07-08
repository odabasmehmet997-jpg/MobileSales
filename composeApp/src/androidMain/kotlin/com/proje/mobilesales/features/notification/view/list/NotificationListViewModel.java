package com.proje.mobilesales.features.notification.view.list;

import androidx.lifecycle.FlowLiveData;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.CachedPagingDataKt;
import androidx.paging.PagingData;
import androidx.paging.PagingDataTransforms__PagingDataTransformsKt;
import com.proje.mobilesales.core.utils.UiState;
import com.proje.mobilesales.features.notification.model.NotificationFilterModel;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import com.proje.mobilesales.features.notification.repository.NotificationListRepository;
import com.proje.mobilesales.features.notification.view.BaseNotificationViewModel;
import com.proje.mobilesales.features.notification.view.list.NotificationListEvents;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: NotificationListViewModel.kt */

public final class NotificationListViewModel extends BaseNotificationViewModel {
    final NotificationListRepository repository;
    private final String TAG = "NotificationListVM";
    private final MutableStateFlow<List<NotificationListEvents>> modificationEvents = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
    private final MutableLiveData<UiState<Integer>> deleteUiState = new MutableLiveData<>();
    private final MutableLiveData<UiState<List<NotifiedUserModel>>> notifiedUsersUiState = new MutableLiveData<>();
    private final MutableLiveData<UiState<NotificationModel>> getInsertedNotificationUiState = new MutableLiveData<>();
    private final MutableLiveData<PagingData<NotificationModel>> _notificationList = new MutableLiveData<>();

    public NotificationListViewModel(NotificationListRepository notificationListRepository) {
        super(notificationListRepository);
        Intrinsics.checkNotNullParameter(notificationListRepository, "repository");
        this.repository = notificationListRepository;
    }

    public LiveData<UiState<Integer>> deleteUiState() {
        return this.deleteUiState;
    }

    public LiveData<UiState<List<NotifiedUserModel>>> notifiedUsersUiState() {
        return this.notifiedUsersUiState;
    }

    public LiveData<UiState<NotificationModel>> getInsertedNotificationUiState() {
        return this.getInsertedNotificationUiState;
    }

    public LiveData<PagingData<NotificationModel>> getPagingList(NotificationFilterModel notificationFilterModel) {
        Intrinsics.checkNotNullParameter(notificationFilterModel, "filterModel");
        this.modificationEvents.setValue(CollectionsKt.emptyList());
        Flow flowCombine = FlowKt.flowCombine(CachedPagingDataKt.cachedIn(this.repository.getNotificationList(notificationFilterModel), ViewModelKt.getViewModelScope(this)), this.modificationEvents, new NotificationListViewModelgetPagingListcombined1(this, null));
        this._notificationList.setValue(FlowLiveData.asLiveDatadefault(flowCombine, (CoroutineContext) null, 0, 3, (Object) null).getValue());
        return FlowLiveData.asLiveDatadefault(flowCombine, (CoroutineContext) null, 0, 3, (Object) null);
    }

    public void deleteNotification(NotificationModel notificationModel) {
        Intrinsics.checkNotNullParameter(notificationModel, "notificationModel");
        this.deleteUiState.setValue(UiState.Loading.INSTANCE);
        Job unused = BuildersKt__Builders_commonKt.launchdefault(ViewModelKt.getViewModelScope(this), null, null, new NotificationListViewModeldeleteNotification1(notificationModel, this, null), 3, null);
    }

    public void getNotificationByGuid(String str) {
        Intrinsics.checkNotNullParameter(str, "notificationGuid");
        this.getInsertedNotificationUiState.setValue(UiState.Loading.INSTANCE);
        Job unused = BuildersKt__Builders_commonKt.launchdefault(ViewModelKt.getViewModelScope(this), null, null, new NotificationListViewModelgetNotificationByGuid1(this, str, null), 3, null);
    }

    public void getNotifiedUsers(NotificationModel notificationModel) {
        Intrinsics.checkNotNullParameter(notificationModel, "notificationModel");
        this.notifiedUsersUiState.setValue(UiState.Loading.INSTANCE);
        Job unused = BuildersKt__Builders_commonKt.launchdefault(ViewModelKt.getViewModelScope(this), null, null, new NotificationListViewModelgetNotifiedUsers1(this, notificationModel, null), 3, null);
    }

    public void onViewEvent(NotificationListEvents notificationListEvents) {
        Intrinsics.checkNotNullParameter(notificationListEvents, "notificationListEvents");
        MutableStateFlow<List<NotificationListEvents>> mutableStateFlow = this.modificationEvents;
        mutableStateFlow.setValue(CollectionsKt.plus(mutableStateFlow.getValue(), notificationListEvents));
    }

    
    public PagingData<NotificationModel> applyEvents(PagingData<NotificationModel> pagingData, NotificationListEvents notificationListEvents) {
        if (notificationListEvents instanceof NotificationListEvents.Delete) {
            return PagingDataTransforms__PagingDataTransformsKt.filter(pagingData, new NotificationListViewModelapplyEvents1(notificationListEvents, null));
        }
        if (notificationListEvents instanceof NotificationListEvents.StatusUpdate) {
            return PagingDataTransforms__PagingDataTransformsKt.map(pagingData, new NotificationListViewModelapplyEvents2(notificationListEvents, null));
        }
        if (notificationListEvents instanceof NotificationListEvents.ItemAdded) {
            return PagingDataTransforms__PagingDataTransformsKt.insertHeaderItemdefault(pagingData, null, ((NotificationListEvents.ItemAdded) notificationListEvents).getNotificationModel(), 1, null);
        }
        throw new NoWhenBranchMatchedException();
    }
}
