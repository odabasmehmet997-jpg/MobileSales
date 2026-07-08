package com.proje.mobilesales.core.work;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.Spanned;
import android.util.Log;
import androidx.annotation.RequiresPermission;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.example.privacy_policy_lib.core.utils.PreferencesHelper;
import com.google.gson.Gson;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.NotificationStatus;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.dbmodel.User;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.util.NotificationConstants;
import com.proje.mobilesales.features.notification.view.NotificationWelcomeActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class PeriodicBackgroundNotification extends Worker {
    private final String TAG;
    private final Context context;
    private final String sharedPrefName;
    private final String userPrefKey;

    public PeriodicBackgroundNotification(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParameters, "workerParameters");
        this.context = context;
        this.TAG = "PNotificationWorker";
        this.sharedPrefName = PreferencesHelper.PREF_NAME;
        this.userPrefKey = "User";
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    public Result doWork() {
        getNotificationData();
        Result success = Result.success();
        Intrinsics.checkNotNullExpressionValue(success, "success(...)");
        return success;
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private void getNotificationData() {
        Log.d(this.TAG, this.context.getPackageName());
        boolean z = false;
        Log.d(this.TAG, String.valueOf(ContextUtils.getmContext() == null));
        if (ContextUtils.getmContext() == null) {
            ContextUtils.setmContext(this.context);
        }
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        User userInfoFromPreferences = getUserInfoFromPreferences();
        if (userInfoFromPreferences == null || !userInfoFromPreferences.isLoggedIn()) {
            String str = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("user is null: ");
            sb.append(userInfoFromPreferences == null);
            sb.append(" / user is loggedIn ");
            if (userInfoFromPreferences != null && userInfoFromPreferences.isLoggedIn()) {
                z = true;
            }
            sb.append(z);
            Log.d(str, sb.toString());
            return;
        }
        if (baseErp.isDeviceInUseByAnotherUser()) {
            return;
        }
        Log.d(this.TAG, "got device result");
        Log.d(this.TAG, userInfoFromPreferences.getUserName());
        Log.d(this.TAG, userInfoFromPreferences.getPassword());
        Log.d(this.TAG, String.valueOf(userInfoFromPreferences.isLoggedIn()));
        if (canLogin()) {
            List<NotificationModel> userNotificationsToBeNotified = baseErp.getUserNotificationsToBeNotified();
            Log.d(this.TAG, "got notification list");
            if (userNotificationsToBeNotified == null || userNotificationsToBeNotified.isEmpty()) {
                return;
            }
            Intrinsics.checkNotNull(userNotificationsToBeNotified);
            ArrayList arrayList = new ArrayList();
            for (Object obj : userNotificationsToBeNotified) {
                if (((NotificationModel) obj).getWorkingHours() == 1) {
                    arrayList.add(obj);
                }
            }
            if (CollectionsKt.any(arrayList) && !baseErp.isInWorkingHoursForShowingNotifications()) {
                userNotificationsToBeNotified.removeIf(obj2 -> {
                    boolean notificationDatalambda1;
                    notificationDatalambda1 = PeriodicBackgroundNotification.getNotificationDatalambda1(Function1.this, obj2);
                    return notificationDatalambda1;
                });
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : userNotificationsToBeNotified) {
                Integer status = ((NotificationModel) obj2).getStatus();
                int status2 = NotificationStatus.Delivered.getStatus();
                if (status == null || status.intValue() != status2) {
                    arrayList2.add(obj2);
                }
            }
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                baseErp.updateNotifiedUserNotificationAsDelivered(((NotificationModel) it.next()).getNotifiedUserId());
            }
            try {
                HashSet hashSet = new HashSet();
                ArrayList arrayList3 = new ArrayList();
                for (Object obj3 : userNotificationsToBeNotified) {
                    if (hashSet.add(((NotificationModel) obj3).getNotificationGuid())) {
                        arrayList3.add(obj3);
                    }
                }
                Iterator it2 = arrayList3.iterator();
                while (it2.hasNext()) {
                    baseErp.updateNotificationAsDeliveredIfAllDelivered(((NotificationModel) it2.next()).getNotificationGuid());
                }
            } catch (Exception e2) {
                Log.e(this.TAG, "updateNotificationAsDeliveredIfAllDelivered", e2);
            }
            String str2 = this.TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("notification send date:");
            String dateSend = userNotificationsToBeNotified.get(0).getDateSend();
            if (dateSend == null) {
                dateSend = "null date";
            }
            sb2.append(dateSend);
            Log.d(str2, sb2.toString());
            Log.d(this.TAG, "showing notification");
            showNotification(userNotificationsToBeNotified);
        }
    }

    public static boolean getNotificationDatalambda1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        return ((Boolean) tmp0.invoke(obj)).booleanValue();
    }

    private User getUserInfoFromPreferences() {
        String string = getApplicationContext().getSharedPreferences(this.sharedPrefName, 0).getString(this.userPrefKey, "");
        if (string != null && string.length() != 0) {
            try {
                return new Gson().fromJson(string, User.class);
            } catch (Exception e2) {
                Log.e(this.TAG, "error on get user from prefs", e2);
            }
        }
        return null;
    }

    private boolean canLogin() {
        BaseSelectResult loginFromPeriodicWorker = ErpCreator.getInstance().getmBaseErp().loginFromPeriodicWorker();
        return loginFromPeriodicWorker.isSuccess() && loginFromPeriodicWorker.getDataList().size() != 0;
    }
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private void showNotification(List<NotificationModel> list) {
        User userInfoFromPreferences = getUserInfoFromPreferences();
        if (userInfoFromPreferences == null || !userInfoFromPreferences.isLoggedIn()) {
            return;
        }
        int notificationId = list.size() == 1 ? list.get(0).getNotificationId() : -1;
        int notifiedUserId = list.size() == 1 ? list.get(0).getNotifiedUserId() : -1;
        Intent intent = new Intent(this.context, NotificationWelcomeActivity.class);
        intent.setFlags(603979776);
        intent.putExtra(NotificationConstants.EXTRA_NOTIFICATION_COUNT, list.size());
        intent.putExtra(NotificationConstants.EXTRA_NOTIFIEDUSER_ID, notifiedUserId);
        intent.putExtra(NotificationConstants.EXTRA_NOTIFICATION_ID, notificationId);
        PendingIntent activity = PendingIntent.getActivity(this.context, (int) System.currentTimeMillis(), intent, 201326592);
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.context, NotificationConstants.PERIODIC_NOTIFICATION_CHANNEL_ID);
        builder.setContentIntent(activity);
        Spanned fromHtml = HtmlCompat.fromHtml("<b>" + this.context.getString(R.string.str_you_have_new_notifications, Integer.valueOf(list.size())) + "</b>", 0);
        Intrinsics.checkNotNullExpressionValue(fromHtml, "fromHtml(...)");
        NotificationCompat.InboxStyle bigContentTitle = new NotificationCompat.InboxStyle().setBigContentTitle(fromHtml);
        Intrinsics.checkNotNullExpressionValue(bigContentTitle, "setBigContentTitle(...)");
        Spanned fromHtml2 = HtmlCompat.fromHtml("<b>" + list.get(0).getSenderName() + "</b>: <u>" + list.get(0).getTitle() + "</u> - " + list.get(0).getMessage(), 0);
        Intrinsics.checkNotNullExpressionValue(fromHtml2, "fromHtml(...)");
        for (NotificationModel notificationModel : list) {
            bigContentTitle.addLine(HtmlCompat.fromHtml("<b>" + notificationModel.getSenderName() + "</b>: <u>" + notificationModel.getTitle() + "</u> - " + notificationModel.getMessage(), 0));
        }
        builder.setStyle(bigContentTitle).setContentTitle(fromHtml).setContentText(fromHtml2).setSmallIcon(R.drawable.ic_stat_ms).setColor(ContextCompat.getColor(this.context, R.color.colorAccent)).setContentIntent(activity).setDefaults(-1).setCategory(NotificationCompat.CATEGORY_EMAIL).setPriority(1).setVisibility(NotificationCompat.VISIBILITY_PRIVATE).setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL).setNumber(0);
        NotificationManagerCompat.from(this.context).notify(NotificationConstants.PERIODIC_WORK_NOTIFICATION_ID, builder.build());
    }
}
