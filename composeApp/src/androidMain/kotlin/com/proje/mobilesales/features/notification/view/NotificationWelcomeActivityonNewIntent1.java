package com.proje.mobilesales.features.notification.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.LinearLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.core.extensions.ViewExtensionsKt;
import com.proje.mobilesales.databinding.ActivityNotificationWelcomeBinding;
import com.proje.mobilesales.features.activity.LoginActivity;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

import static com.proje.mobilesales.core.utils.AppUtils.exitApplication;

final class NotificationWelcomeActivityonNewIntent1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final BaseErp baseErp;
    int label;
    final NotificationWelcomeActivity this0;

    NotificationWelcomeActivityonNewIntent1(final NotificationWelcomeActivity notificationWelcomeActivity, final BaseErp baseErp, final Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this0 = notificationWelcomeActivity;
        this.baseErp = baseErp;
    }

    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new NotificationWelcomeActivityonNewIntent1(this0, baseErp, continuation);
    }

    public Object invoke(final Object coroutineScope, final CoroutineContext.Element continuation) {
        final Object o = this.create(coroutineScope, (Continuation<?>) continuation).invokeSuspend(Unit.INSTANCE);
        return o;
    }
    public Object invokeSuspend(Object obj) {
        final ActivityNotificationWelcomeBinding binding;
        final boolean isAutoTimeAndTimeZoneEnabled;
        final ActivityNotificationWelcomeBinding binding2;
        final int i2;
        final int i3;
        final int i4;
        final Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        final int i5 = label;
        if (0 == i5) {
            ResultKt.throwOnFailure(obj);
            binding = this0.getBinding();
            final LinearLayout root = binding.loadingBar.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
            ViewExtensionsKt.setVisible(root);
            final CoroutineDispatcher io2 = Dispatchers.getIO();
            final NotificationWelcomeActivityonNewIntent1result1 notificationWelcomeActivityonNewIntent1result1 = new NotificationWelcomeActivityonNewIntent1result1(baseErp, null);
            label = 1;
            obj = BuildersKt.withContext(io2, notificationWelcomeActivityonNewIntent1result1, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (1 != i5) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        BaseSelectResult baseSelectResult = (BaseSelectResult) obj;
        isAutoTimeAndTimeZoneEnabled = this0.isAutoTimeAndTimeZoneEnabled();
        if (!isAutoTimeAndTimeZoneEnabled) {
            baseSelectResult.setSuccess(false);
            baseSelectResult.setErrorString(this0.getString(R.string.str_auto_time_enabled_error));
        }
        binding2 = this0.getBinding();
        final LinearLayout root2 = binding2.loadingBar.getRoot();
        Intrinsics.checkNotNullExpressionValue(root2, "getRoot(...)");
        ViewExtensionsKt.setGone(root2);
        if (baseSelectResult.isSuccess()) {
            final NotificationWelcomeActivity notificationWelcomeActivity = this0;
            i2 = notificationWelcomeActivity.notificationId;
            i3 = this0.notificationId;
            i4 = this0.notifiedUserId;
            notificationWelcomeActivity.navigateToNotificationsFromRoot(i2, i3, i4);
            this0.finish();
        } else {
            NotificationWelcomeActivity notificationWelcomeActivity2 = this0;
            ViewExtensionsKt.alert(notificationWelcomeActivity2, 0, new Function1<AlertDialog.Builder, Unit>() {

                public   Unit invoke(final Object builder) {
                    this.invoke2((AlertDialog.Builder) builder);
                    return Unit.INSTANCE;
                }
                public void invoke2(final AlertDialog.Builder alert) {
                    Intrinsics.checkNotNullParameter(alert, "thisalert");
                    alert.setMessage(BaseSelectResult.this.getErrorString());
                    NotificationWelcomeActivity notificationWelcomeActivity3 = notificationWelcomeActivity2;
                    ViewExtensionsKt.positiveButton(alert, R.string.str_exit, new Function1<DialogInterface, Unit>() {

                        public  Unit invoke(final Object dialogInterface) {
                            this.invoke2((DialogInterface) dialogInterface);
                            return Unit.INSTANCE;
                        }

                        public void invoke2(final DialogInterface it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            it.dismiss();
                            exitApplication(NotificationWelcomeActivity.this, LoginActivity.class);
                        }
                    });
                }
            }, 1, null);
        }
        return Unit.INSTANCE;
    }
}
