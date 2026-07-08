package com.proje.mobilesales.features.notification.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.LinearLayout;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.core.extensions.ViewExtensions;
import com.proje.mobilesales.features.activity.LoginActivity;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
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

/* compiled from: NotificationWelcomeActivity.kt */

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.NotificationWelcomeActivityonNewIntent1", m22f = "NotificationWelcomeActivity.kt", m21l = {50}, m20m = "invokeSuspend")

public final class NotificationWelcomeActivityonNewIntent1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final BaseErp baseErp;
    int label;
    final NotificationWelcomeActivity this0;

    public NotificationWelcomeActivityonNewIntent1(NotificationWelcomeActivity notificationWelcomeActivity, BaseErp baseErp, Continuation<? super NotificationWelcomeActivityonNewIntent1> continuation) {
        super(2, continuation);
        this.this0 = notificationWelcomeActivity;
        this.baseErp = baseErp;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new NotificationWelcomeActivityonNewIntent1(this.this0, this.baseErp, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LinearLayout root = this.this0.getBinding().loadingBar.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
            ViewExtensions.setVisible(root);
            CoroutineDispatcher io = Dispatchers.getIO();
            NotificationWelcomeActivityonNewIntent1result1 notificationWelcomeActivityonNewIntent1result1 = new NotificationWelcomeActivityonNewIntent1result1(this.baseErp, null);
            this.label = 1;
            obj = BuildersKt.withContext(io, notificationWelcomeActivityonNewIntent1result1, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        final BaseSelectResult baseSelectResult = (BaseSelectResult) obj;
        if (!(this.this0.isAutoTimeAndTimeZoneEnabled())) {
            baseSelectResult.setSuccess(false);
            baseSelectResult.setErrorString(this.this0.getString(R.string.str_auto_time_enabled_error));
        }
        LinearLayout root2 = this.this0.getBinding().loadingBar.getRoot();
        Intrinsics.checkNotNullExpressionValue(root2, "getRoot(...)");
        ViewExtensions.setGone(root2);
        if (baseSelectResult.isSuccess()) {
            NotificationWelcomeActivity notificationWelcomeActivity = this.this0;
            notificationWelcomeActivity.navigateToNotificationsFromRoot(notificationWelcomeActivity.notificationCount, this.this0.notificationId, this.this0.notifiedUserId);
            this.this0.finish();
        } else {
            final NotificationWelcomeActivity notificationWelcomeActivity2 = this.this0;
            ViewExtensions.alertdefault(notificationWelcomeActivity2, 0, new Function1<AlertDialog.Builder, Unit>() {
                public Unit invoke(AlertDialog.Builder builder) {
                    invoke(builder);
                    return Unit.INSTANCE;
                }

                public void invoke(AlertDialog.Builder builder) {
                    Intrinsics.checkNotNullParameter(builder, "thisalert");
                    builder.setMessage(baseSelectResult.getErrorString());
                    final NotificationWelcomeActivity notificationWelcomeActivity3 = notificationWelcomeActivity2;
                    ViewExtensions.positiveButton(builder, R.string.str_exit, new Function1<DialogInterface, Unit>() {
                        public Unit invoke(DialogInterface dialogInterface) {
                            invoke(dialogInterface);
                            return Unit.INSTANCE;
                        }

                        public void invoke(DialogInterface dialogInterface) {
                            Intrinsics.checkNotNullParameter(dialogInterface, "it");
                            dialogInterface.dismiss();
                            exitApplication(notificationWelcomeActivity3, LoginActivity.class);
                        }
                    });
                }
            }, 1, null);
        }
        return Unit.INSTANCE;
    }
}
