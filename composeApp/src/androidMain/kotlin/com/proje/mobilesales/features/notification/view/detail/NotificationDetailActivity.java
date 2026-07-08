package com.proje.mobilesales.features.notification.view.detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.C2682R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.NotificationStatus;
import com.proje.mobilesales.core.extensions.ViewExtensions;
import com.proje.mobilesales.core.utils.UiState;
import com.proje.mobilesales.databinding.ActivityNotificationDetailBinding;
import com.proje.mobilesales.features.notification.model.NotificationDetailModel;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import com.proje.mobilesales.features.notification.repository.NotificationDetailRepository;
import com.proje.mobilesales.features.notification.util.NotificationConstants;
import com.proje.mobilesales.features.notification.view.list.NotifiedUserListAdapter;
import java.util.List;
import kotlin.Function;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationDetailActivity.kt */

public final class NotificationDetailActivity extends BaseInjectableActivity {
    private final String TAG = "NotificationDetail";
    private final Lazy bindingdelegate = LazyKt.lazy(new Functions<ActivityNotificationDetailBinding>(this) { // from class: com.proje.mobilesales.features.notification.view.detail.NotificationDetailActivitybinding2
        final NotificationDetailActivity this0;

        
        {
            this.this0 = r1;
        }

        
        public ActivityNotificationDetailBinding invoke() {
            return ActivityNotificationDetailBinding.inflate(this.this0.getLayoutInflater());
        }
    });
    private final NotificationDetailRepository notificationDetailRepository;
    private int notificationId;
    private int notifiedUserId;
    private boolean statusChanged;
    private final NotificationDetailViewModel viewModel;

    /* compiled from: NotificationDetailActivity.kt */
    
    public class WhenMappings {
        public static final int[] EnumSwitchMapping0;

        static {
            int[] iArr = new int[NotificationStatus.values().length];
            try {
                iArr[NotificationStatus.Created.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[NotificationStatus.Delivered.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[NotificationStatus.Read.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[NotificationStatus.Deleted.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            EnumSwitchMapping0 = iArr;
        }
    }

    public NotificationDetailActivity() {
        NotificationDetailRepository notificationDetailRepository = new NotificationDetailRepository();
        this.notificationDetailRepository = notificationDetailRepository;
        this.viewModel = new NotificationDetailViewModel(notificationDetailRepository);
    }

    private ActivityNotificationDetailBinding getBinding() {
        return (ActivityNotificationDetailBinding) this.bindingdelegate.getValue();
    }

    
    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getBinding().getRoot());
        getActivityComponent().inject(this);
        setToolbar();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(getString(R.string.str_notification_detail));
        }
        NotificationManagerCompat.from(getApplicationContext()).cancel(NotificationConstants.PERIODIC_WORK_NOTIFICATION_ID);
        this.viewModel.notificationDetailUiState().observe(this, new Object(new Function1<UiState<? extends NotificationDetailModel>, Unit>(this) { // from class: com.proje.mobilesales.features.notification.view.detail.NotificationDetailActivityonCreate1
            final NotificationDetailActivity this0;

            
            {
                this.this0 = r1;
            }
            public  Unit invoke(UiState<? extends NotificationDetailModel> uiState) {
                invoke((UiState<NotificationDetailModel>) uiState);
                return Unit.INSTANCE;
            }

            public void invoke(UiState<NotificationDetailModel> uiState) {
                if (uiState != null) {
                    this.this0.render(uiState, new Function1<NotificationDetailModel, Unit>(this.this0) {
                        public  Unit invoke(NotificationDetailModel notificationDetailModel) {
                            invoke(notificationDetailModel);
                            return Unit.INSTANCE;
                        }

                        public void invoke(NotificationDetailModel notificationDetailModel) {
                            Intrinsics.checkNotNullParameter(notificationDetailModel, "p0");
                            ((NotificationDetailActivity) this.receiver).onGotNotification(notificationDetailModel);
                        }
                    });
                }
            }
        }) { // from class: com.proje.mobilesales.features.notification.view.detail.NotificationDetailActivitysamandroidx_lifecycle_Observer0
            private final Function1 function;

            
            {
                Intrinsics.checkNotNullParameter(r2, "function");
                this.function = r2;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof Observer) || !(obj instanceof FunctionAdapter)) {
                    return false;
                }
                return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
            }

            
            public Function<?> getFunctionDelegate() {
                return this.function;
            }

            public int hashCode() {
                return getFunctionDelegate().hashCode();
            }

            
            public void onChanged(Object obj) {
                this.function.invoke(obj);
            }
        });
        onNewIntent(getIntent());
    }

    
    @Override // com.proje.mobilesales.core.base.BaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        bundle.putInt(NotificationConstants.EXTRA_NOTIFICATION_ID, this.notificationId);
        bundle.putInt(NotificationConstants.EXTRA_NOTIFIEDUSER_ID, this.notifiedUserId);
        bundle.putBoolean(NotificationConstants.EXTRA_NOTIFICATION_STATUS_CHANGED, this.statusChanged);
    }

    
    protected void onRestoreInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "savedInstanceState");
        super.onRestoreInstanceState(bundle);
        this.notificationId = bundle.getInt(NotificationConstants.EXTRA_NOTIFICATION_ID, 0);
        this.notifiedUserId = bundle.getInt(NotificationConstants.EXTRA_NOTIFIEDUSER_ID, 0);
        this.statusChanged = bundle.getBoolean(NotificationConstants.EXTRA_NOTIFICATION_STATUS_CHANGED, false);
    }

    
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int i = -1;
        this.notificationId = intent != null ? intent.getIntExtra(NotificationConstants.EXTRA_NOTIFICATION_ID, -1) : -1;
        if (intent != null) {
            i = intent.getIntExtra(NotificationConstants.EXTRA_NOTIFIEDUSER_ID, -1);
        }
        this.notifiedUserId = i;
        getNotificationDetail(this.notificationId, i);
    }

    
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() == 16908332) {
            if (this.statusChanged) {
                Intent intent = new Intent();
                intent.putExtra(NotificationConstants.EXTRA_NOTIFICATION_ID, this.notificationId);
                intent.putExtra(NotificationConstants.EXTRA_NOTIFIEDUSER_ID, this.notifiedUserId);
                setResult(-1, intent);
                finish();
                return true;
            }
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void getNotificationDetail(int i, int i2) {
        this.viewModel.getNotificationDetails(i, i2);
    }

    
    public void onGotNotification(NotificationDetailModel notificationDetailModel) {
        NotificationModel notificationModel = notificationDetailModel.getNotificationModel();
        if (notificationModel != null) {
            getBinding().setNotificationDetail(notificationDetailModel);
            getBinding().tvStatus.setText(this.notifiedUserId == 0 ? getNotificationStatus(notificationModel.getStatus()) : getString(R.string.str_notification_status_read));
            getBinding().tvWorkingHours.setText(getString(notificationModel.getWorkingHours() == 1 ? R.string.str_yes : R.string.str_no));
            getBinding().notifiedUserList.setLayoutManager(new LinearLayoutManager(this));
            RecyclerView recyclerView = getBinding().notifiedUserList;
            List<NotifiedUserModel> notifiedUsers = notificationDetailModel.getNotifiedUsers();
            if (notifiedUsers == null) {
                notifiedUsers = CollectionsKt.emptyList();
            }
            recyclerView.setAdapter(new NotifiedUserListAdapter(notifiedUsers));
            getBinding().notifiedUserList.addItemDecoration(new DividerItemDecoration(this, 1));
            this.statusChanged = notificationDetailModel.getStatusChanged();
        }
    }

    public String getNotificationStatus(Integer num) {
        String str;
        if (num == null) {
            return "";
        }
        int i = WhenMappings.EnumSwitchMapping0[NotificationStatus.Companion.fromValue(num.intValue()).ordinal()];
        if (i == 1) {
            str = getString(R.string.str_notification_status_created);
        } else if (i == 2) {
            str = getString(R.string.str_notification_status_delivered);
        } else if (i == 3 || i == 4) {
            str = getString(R.string.str_notification_status_read);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        Intrinsics.checkNotNull(str);
        return str;
    }

    
    public <T> void render(UiState<? extends T> uiState, Function1<? super T, Unit> function1) {
        if (uiState instanceof UiState.Loading) {
            onLoad();
        } else if (uiState instanceof UiState.Success) {
            try {
                function1.invoke(((UiState.Success) uiState).getResult());
            } catch (Exception e) {
                Log.d(this.TAG, "successor function failure", e);
            }
            hideLoadingProgress();
        } else if (uiState instanceof UiState.Error) {
            onError((UiState.Error) uiState);
        }
    }

    private void onLoad() {
        LinearLayout root = getBinding().loadingBar.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewExtensions.setVisible(root);
    }

    private Unit onError(UiState.Error error) {
        getBinding();
        hideLoadingProgress();
        return ViewExtensions.toastdefault(this, ViewExtensions.uiStateError(this, error), 0, 2, null);
    }

    private void hideLoadingProgress() {
        LinearLayout root = getBinding().loadingBar.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewExtensions.setGone(root);
    }
}
