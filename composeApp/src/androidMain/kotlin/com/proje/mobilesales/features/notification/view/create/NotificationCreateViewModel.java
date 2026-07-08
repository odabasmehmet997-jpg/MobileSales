package com.proje.mobilesales.features.notification.view.create;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.proje.mobilesales.C2682R;
import com.proje.mobilesales.core.extensions.DateExtensions;
import com.proje.mobilesales.core.utils.UiState;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import com.proje.mobilesales.features.notification.repository.NotificationCreateRepository;
import com.proje.mobilesales.features.notification.view.BaseNotificationViewModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;

/* compiled from: NotificationCreateViewModel.kt */

public final class NotificationCreateViewModel extends BaseNotificationViewModel {
    final NotificationCreateRepository repository;
    private final String TAG = "NotificationCreateVM";
    private final String dateFormat = "dd.MM.yyyy";
    private final MutableLiveData<UiState<String>> saveNotificationUiState = new MutableLiveData<>();

    
    public NotificationCreateViewModel(NotificationCreateRepository notificationCreateRepository) {
        super(notificationCreateRepository);
        Intrinsics.checkNotNullParameter(notificationCreateRepository, "repository");
        this.repository = notificationCreateRepository;
    }

    public LiveData<UiState<String>> saveNotificationUiState() {
        return this.saveNotificationUiState;
    }

    public void saveNotification(NotificationModel notificationModel, List<NotifiedUserModel> list) {
        Intrinsics.checkNotNullParameter(notificationModel, "notificationModel");
        Intrinsics.checkNotNullParameter(list, "notifiedUsers");
        this.saveNotificationUiState.setValue(UiState.Loading.INSTANCE);
        List<Integer> validateNotification = validateNotification(notificationModel, list);
        if (validateNotification.isEmpty()) {
            BuildersKt.launchdefault(ViewModelKt.getViewModelScope(this), null, null, new NotificationCreateViewModelsaveNotification1(this, notificationModel, list, null), 3, null);
        } else {
            this.saveNotificationUiState.setValue(new UiState.Error(validateNotification));
        }
    }

    private List<Integer> validateNotification(NotificationModel notificationModel, List<NotifiedUserModel> list) {
        ArrayList arrayList = new ArrayList();
        String title = notificationModel.getTitle();
        if (title == null || title.length() == 0) {
            arrayList.add(Integer.valueOf((int) R.string.str_notification_title_cannotBeEmpty));
        }
        String message = notificationModel.getMessage();
        if (message == null || message.length() == 0) {
            arrayList.add(Integer.valueOf((int) R.string.str_notification_message_cannotBeEmpty));
        }
        Date date = DateExtensions.toDate(notificationModel.getDateSend(), this.dateFormat);
        if (date == null) {
            arrayList.add(Integer.valueOf((int) R.string.str_notification_senDate_cannotBeEmpty));
        }
        Date date2 = DateExtensions.toDate(DateExtensions.formatDate(Calendar.getInstance().getTime(), this.dateFormat), this.dateFormat);
        if (date != null && date.before(date2)) {
            arrayList.add(Integer.valueOf((int) R.string.str_notification_senDate_cannotBeBeforeToday));
        }
        if (list == null || list.isEmpty()) {
            arrayList.add(Integer.valueOf((int) R.string.str_notification_users_mustBeSelected));
        }
        return arrayList;
    }
}
