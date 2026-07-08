package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.util.Log;
import android.widget.ProgressBar;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.base.R;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.api.internal.zabw;
import com.google.android.gms.common.api.internal.zabx;
import com.google.android.gms.common.internal.HideFirstParty;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.zag;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.InstantApps;
import com.google.android.gms.internal.base.zad;
import com.google.android.gms.internal.base.zae;
import com.google.android.gms.internal.base.zao;
import com.google.android.gms.internal.base.zap;
import com.google.errorprone.annotations.RestrictedInheritance;

@RestrictedInheritance(allowedOnPath = ".*java.*/com/google/android/gms.*", allowlistAnnotations = {zad.class, zae.class}, explanation = "Sub classing of GMS Core's APIs are restricted to GMS Core client libs and testing fakes.", link = "go/gmscore-restrictedinheritance")
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public class GoogleApiAvailability extends GoogleApiAvailabilityLight {
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final Object zaa = new Object();
    private static final GoogleApiAvailability zab = new GoogleApiAvailability();
    @GuardedBy("lock")
    private String zac;

    @NonNull
    public static GoogleApiAvailability getInstance() {
        return GoogleApiAvailability.zab;
    }

    @ShowFirstParty
    @KeepForSdk
    @Nullable
    public Intent getErrorResolutionIntent(@Nullable final Context context, final int i2, @Nullable final String str) {
        return super.getErrorResolutionIntent(context, i2, str);
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(@NonNull final Context context, final int i2, final int i3) {
        return super.getErrorResolutionPendingIntent(context, i2, i3);
    }

    @NonNull
    public final String getErrorString(final int i2) {
        return super.getErrorString(i2);
    }

    
    @HideFirstParty
    public int isGooglePlayServicesAvailable(@NonNull final Context context) {
        return super.isGooglePlayServicesAvailable(context);
    }

    public final boolean isUserResolvableError(final int i2) {
        return super.isUserResolvableError(i2);
    }

    public void showErrorNotification(@NonNull final Context context, final int i2) {
        this.zae(context, i2, null, this.getErrorResolutionPendingIntent(context, i2, 0, "n"));
    }

    public final android.app.Dialog zaa(@androidx.annotation.NonNull final android.content.Context r6, final int r7, @androidx.annotation.Nullable final com.google.android.gms.common.internal.zag r8, @androidx.annotation.Nullable final android.content.DialogInterface.OnCancelListener r9, @androidx.annotation.Nullable final android.content.DialogInterface.OnClickListener r10) {
        /*
            r5 = this;
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            android.util.TypedValue r1 = new android.util.TypedValue
            r1.<init>()
            android.content.res.ResourcesTheme r2 = r6.getTheme()
            r3 = 16843529(0x1010309, float:2.3695736E-38)
            r4 = 1
            r2.resolveAttribute(r3, r1, r4)
            android.content.res.Resources r2 = r6.getResources()
            int r1 = r1.resourceId
            java.lang.String r1 = r2.getResourceEntryName(r1)
            java.lang.String r2 = "Theme.Dialog.Alert"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x002c
            android.app.AlertDialogBuilder r0 = new android.app.AlertDialogBuilder
            r1 = 5
            r0.<init>(r6, r1)
        L_0x002c:
            if (r0 != 0) goto L_0x0033
            android.app.AlertDialogBuilder r0 = new android.app.AlertDialogBuilder
            r0.<init>(r6)
        L_0x0033:
            java.lang.String r1 = com.google.android.gms.common.internal.zac.zac(r6, r7)
            r0.setMessage(r1)
            if (r9 == 0) goto L_0x003f
            r0.setOnCancelListener(r9)
        L_0x003f:
            java.lang.String r9 = com.google.android.gms.common.internal.zac.zab(r6, r7)
            if (r9 == 0) goto L_0x004b
            if (r8 != 0) goto L_0x0048
            r8 = r10
        L_0x0048:
            r0.setPositiveButton(r9, r8)
        L_0x004b:
            java.lang.String r6 = com.google.android.gms.common.internal.zac.zaf(r6, r7)
            if (r6 == 0) goto L_0x0054
            r0.setTitle(r6)
        L_0x0054:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            java.lang.String r7 = "Creating dialog for Google Play services availability issue. ConnectionResult=%s"
            java.lang.String r6 = java.lang.String.format(r7, r6)
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            r7.<init>()
            java.lang.String r8 = "GoogleApiAvailability"
            android.util.Log.w(r8, r6, r7)
            android.app.AlertDialog r6 = r0.create()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.GoogleApiAvailability.zaa(android.content.Context, int, com.google.android.gms.common.internal.zag, android.content.DialogInterfaceOnCancelListener, android.content.DialogInterfaceOnClickListener):android.app.Dialog");
    }

    @NonNull
    public final Dialog zab(@NonNull final Activity activity, @NonNull final DialogInterface.OnCancelListener onCancelListener) {
        final ProgressBar progressBar = new ProgressBar(activity, null, 16842874);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(0);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(progressBar);
        builder.setMessage(this.zac.zac(activity, 18));
        builder.setPositiveButton("", null);
        final AlertDialog create = builder.create();
        this.zad(activity, create, "GooglePlayServicesUpdatingDialog", onCancelListener);
        return create;
    }

    
    @Nullable
    public final zabx zac(final Context context, final zabw zabw) {
        final IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        final zabx zabx = new zabx(zabw);
        zao.zaa(context, zabx, intentFilter);
        zabx.zaa(context);
        if (this.isUninstalledAppPossiblyUpdating(context, "com.google.android.gms")) {
            return zabx;
        }
        zabw.zaa();
        zabx.zab();
        return null;
    }

    
    public final void zad(final Activity activity, final Dialog dialog, final String str, @Nullable final DialogInterface.OnCancelListener onCancelListener) {
        try {
            if (activity instanceof FragmentActivity) {
                SupportErrorDialogFragment.newInstance(dialog, onCancelListener).show(((FragmentActivity) activity).getSupportFragmentManager(), str);
                return;
            }
        } catch (final NoClassDefFoundError unused) {
        }
        ErrorDialogFragment.newInstance(dialog, onCancelListener).show(activity.getFragmentManager(), str);
    }

    
    @TargetApi(20)
    public final void zae(final Context context, final int i2, @Nullable final String str, @Nullable final PendingIntent pendingIntent) {
        final int i3;
        String str2;
        Log.w("GoogleApiAvailability", String.format("GMS core API Availability. ConnectionResult=%s, tag=%s", Integer.valueOf(i2), null), new IllegalArgumentException());
        if (18 == i2) {
            this.zaf(context);
        } else if (null != pendingIntent) {
            final String zae = this.zac.zae(context, i2);
            final String zad = this.zac.zad(context, i2);
            final Resources resources = context.getResources();
            final NotificationManager notificationManager = Preconditions.checkNotNull(context.getSystemService("notification"));
            final NotificationCompat.Builder style = new NotificationCompat.Builder(context).setLocalOnly(true).setAutoCancel(true).setContentTitle(zae).setStyle(new NotificationCompat.BigTextStyle().bigText(zad));
            if (DeviceProperties.isWearable(context)) {
                Preconditions.checkState(PlatformVersion.isAtLeastKitKatWatch());
                style.setSmallIcon(context.getApplicationInfo().icon).setPriority(2);
                if (DeviceProperties.isWearableWithoutPlayStore(context)) {
                    style.addAction(R.drawable.common_full_open_on_phone, resources.getString(R.string.common_open_on_phone), pendingIntent);
                } else {
                    style.setContentIntent(pendingIntent);
                }
            } else {
                style.setSmallIcon(17301642).setTicker(resources.getString(R.string.common_google_play_services_notification_ticker)).setWhen(System.currentTimeMillis()).setContentIntent(pendingIntent).setContentText(zad);
            }
            if (PlatformVersion.isAtLeastO()) {
                Preconditions.checkState(PlatformVersion.isAtLeastO());
                synchronized (GoogleApiAvailability.zaa) {
                    str2 = zac;
                }
                if (null == str2) {
                    str2 = "com.google.android.gms.availability";
                    final NotificationChannel notificationChannel = notificationManager.getNotificationChannel(str2);
                    final String string = context.getResources().getString(R.string.common_google_play_services_notification_channel_name);
                    if (null == notificationChannel) {
                        notificationManager.createNotificationChannel(new NotificationChannel(str2, string, 4));
                    } else if (!string.contentEquals(notificationChannel.getName())) {
                        notificationChannel.setName(string);
                        notificationManager.createNotificationChannel(notificationChannel);
                    }
                }
                style.setChannelId(str2);
            }
            final Notification build = style.build();
            if (1 == i2 || 2 == i2 || 3 == i2) {
                GooglePlayServicesUtilLight.sCanceledAvailabilityNotification.set(false);
                i3 = 10436;
            } else {
                i3 = 39789;
            }
            notificationManager.notify(i3, build);
        } else if (6 == i2) {
            Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
        }
    }

    
    public final void zaf(final Context context) {
        new zad(this, context).sendEmptyMessageDelayed(1, 120000);
    }

    
    public final boolean zag(@NonNull final Activity activity, @NonNull final LifecycleFragment lifecycleFragment, final int i2, final int i3, @Nullable final DialogInterface.OnCancelListener onCancelListener) {
        final Dialog zaa2 = this.zaa(activity, i2, zag.zad(lifecycleFragment, this.getErrorResolutionIntent(activity, i2, "d"), 2), onCancelListener, null);
        if (null == zaa2) {
            return false;
        }
        this.zad(activity, zaa2, "GooglePlayServicesErrorDialog", onCancelListener);
        return true;
    }

    public final boolean zah(@NonNull final Context context, @NonNull final ConnectionResult connectionResult, final int i2) {
        final PendingIntent errorResolutionPendingIntent;
        if (InstantApps.isInstantApp(context) || null == (errorResolutionPendingIntent = getErrorResolutionPendingIntent(context, connectionResult))) {
            return false;
        }
        this.zae(context, connectionResult.getErrorCode(), null, PendingIntent.getActivity(context, 0, GoogleApiActivity.zaa(context, errorResolutionPendingIntent, i2, true), zap.zaa | 134217728));
        return true;
    }

    @Nullable
    public Dialog getErrorDialog(@NonNull final Activity activity, final int i2, final int i3, @Nullable final DialogInterface.OnCancelListener onCancelListener) {
        return this.zaa(activity, i2, zag.zab(activity, this.getErrorResolutionIntent(activity, i2, "d"), i3), onCancelListener, null);
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(@NonNull final Context context, @NonNull final ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            return connectionResult.getResolution();
        }
        return this.getErrorResolutionPendingIntent(context, connectionResult.getErrorCode(), 0);
    }

    @ShowFirstParty
    @KeepForSdk
    public int isGooglePlayServicesAvailable(@NonNull final Context context, final int i2) {
        return super.isGooglePlayServicesAvailable(context, i2);
    }

    
    public boolean showErrorDialogFragment(@NonNull final Activity activity, final int i2, final int i3, @Nullable final DialogInterface.OnCancelListener onCancelListener) {
        final Dialog errorDialog = this.getErrorDialog(activity, i2, i3, onCancelListener);
        if (null == errorDialog) {
            return false;
        }
        this.zad(activity, errorDialog, "GooglePlayServicesErrorDialog", onCancelListener);
        return true;
    }
}
