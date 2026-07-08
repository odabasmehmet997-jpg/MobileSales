package com.proje.mobilesales.features.notification.view;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import androidx.lifecycle.LifecycleOwnerKt;
import com.proje.mobilesales.core.base.BaseActivity;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.databinding.ActivityNotificationWelcomeBinding;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.notification.util.NotificationConstants;
import com.proje.mobilesales.features.notification.view.detail.NotificationDetailActivity;
import com.proje.mobilesales.features.notification.view.list.NotificationListActivity;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Functions;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;

/* compiled from: NotificationWelcomeActivity.kt */

public final class NotificationWelcomeActivity extends BaseActivity {
    private final Lazy bindingdelegate = LazyKt.lazy(new Functions<ActivityNotificationWelcomeBinding>(this) { // from class: com.proje.mobilesales.features.notification.view.NotificationWelcomeActivitybinding2
        final NotificationWelcomeActivity this0;

        
        {
            this.this0 = r1;
        }

        
        public ActivityNotificationWelcomeBinding invoke() {
            return ActivityNotificationWelcomeBinding.inflate(this.this0.getLayoutInflater());
        }
    });
    private int notificationCount;
    private int notificationId;
    private int notifiedUserId;

    
    public ActivityNotificationWelcomeBinding getBinding() {
        return (ActivityNotificationWelcomeBinding) this.bindingdelegate.getValue();
    }

    
    @Override // com.proje.mobilesales.core.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getBinding().getRoot());
        onNewIntent(getIntent());
    }

    
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int i = -1;
        this.notificationCount = intent != null ? intent.getIntExtra(NotificationConstants.EXTRA_NOTIFICATION_COUNT, 0) : -1;
        this.notificationId = intent != null ? intent.getIntExtra(NotificationConstants.EXTRA_NOTIFICATION_ID, 0) : -1;
        if (intent != null) {
            i = intent.getIntExtra(NotificationConstants.EXTRA_NOTIFIEDUSER_ID, 0);
        }
        this.notifiedUserId = i;
        if (isTaskRoot()) {
            Job unused = BuildersKt__Builders_commonKt.launchdefault(LifecycleOwnerKt.getLifecycleScope(this), null, null, new NotificationWelcomeActivityonNewIntent1(this, ErpCreator.getInstance().getErp(Preferences.getErpType(this)), null), 3, null);
            return;
        }
        if (this.notificationCount == 1) {
            navigateToNotificationDetail(this.notificationId, this.notifiedUserId);
        } else {
            Intent intent2 = new Intent(this, NotificationListActivity.class);
            intent2.putExtra(NotificationConstants.EXTRA_NOTIFICATION_ID, this.notificationId);
            intent2.setFlags(603979776);
            startActivity(intent2);
        }
        finish();
    }

    private void navigateToNotificationDetail(int i, int i2) {
        Intent intent = new Intent(this, NotificationListActivity.class);
        intent.putExtra(NotificationConstants.EXTRA_NOTIFICATION_ID, i);
        intent.setFlags(603979776);
        Intent intent2 = new Intent(this, NotificationDetailActivity.class);
        intent2.putExtra(NotificationConstants.EXTRA_NOTIFICATION_ID, i);
        intent2.putExtra(NotificationConstants.EXTRA_NOTIFIEDUSER_ID, i2);
        intent2.setFlags(603979776);
        startActivities(new Intent[]{intent, intent2});
    }

    
    public void navigateToNotificationsFromRoot(int i, int i2, int i3) {
        TaskStackBuilder create = TaskStackBuilder.create(this);
        create.addParentStack(MainActivity.class);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(268435456);
        create.addNextIntent(intent);
        Intent intent2 = new Intent(this, NotificationListActivity.class);
        intent2.putExtra(NotificationConstants.EXTRA_NOTIFICATION_ID, i2);
        create.addNextIntent(intent2);
        if (i == 1) {
            Intent intent3 = new Intent(this, NotificationDetailActivity.class);
            intent3.putExtra(NotificationConstants.EXTRA_NOTIFICATION_ID, i2);
            intent3.putExtra(NotificationConstants.EXTRA_NOTIFIEDUSER_ID, i3);
            create.addNextIntent(intent3);
        }
        create.startActivities();
    }

    
    public boolean isAutoTimeAndTimeZoneEnabled() {
        int i = Settings.Global.getInt(getContentResolver(), "auto_time", 0);
        int i2 = Settings.Global.getInt(getContentResolver(), "auto_time_zone", 0);
        return i == 1 && i2 == 1;
    }
}
