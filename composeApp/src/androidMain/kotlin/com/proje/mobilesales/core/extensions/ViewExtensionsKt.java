package com.proje.mobilesales.core.extensions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.IBinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.annotation.StyleRes;
import com.fasterxml.jackson.databind.EnumNamingStrategies;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.Connectivity;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.core.utils.UiState;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public enum ViewExtensionsKt {
    ;

    public static  Unit toastdefault(final Context context, final CharSequence charSequence, int i2, final int i3, final Object obj) {
        if (0 != (i3 & 2)) {
            i2 = 1;
        }
        return ViewExtensionsKt.toast(context, charSequence, i2);
    }

    public static Unit toast(final Context context, final CharSequence text, final int i2) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (null == context) {
            return null;
        }
        Toast.makeText(context, text, i2).show();
        return Unit.INSTANCE;
    }

    public static String uiStateError(final Context context, final UiState.Error uiStateError) {
        Intrinsics.checkNotNullParameter(uiStateError, "uiStateError");
        if (null == context) {
            return "";
        }
        if (uiStateError.getErrorModel().resourceIds().isEmpty()) {
            final String message = uiStateError.getErrorModel().message();
            if (null != message) {
                return message;
            }
            final String string = context.getString(R.string.exp_29_io);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }
        final StringBuilder sb = new StringBuilder();
        final Iterator<Integer> it = uiStateError.getErrorModel().resourceIds().iterator();
        while (it.hasNext()) {
            sb.append(context.getString(it.next().intValue()));
            if (1 < uiStateError.getErrorModel().resourceIds().size()) {
                sb.append(System.getProperty("line.separator"));
            }
        }
        final String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public static String privacyPolicyUrl(final Context context) {
        if (null == context) {
            return "https://www.proje.com.tr/logo-gizlilik-politikalari/mobilesales";
        }
        final String applicationLanguage = new SharedPreferencesHelper(context).getApplicationLanguage();
        if (Intrinsics.areEqual(applicationLanguage, "tr")) {
            return "https://www.proje.com.tr/logo-gizlilik-politikalari/mobilesales";
        }
        return "https://www.proje.com.tr/logo-gizlilik-politikalari/logo_mobile_sale_" + applicationLanguage;
    }

    public static String privacyPolicyFile(final Context context) {
        if (null == context) {
            return "file:///android_asset/privacypolicy/privacy_policy_tr.html";
        }
        final String applicationLanguage = new SharedPreferencesHelper(context).getApplicationLanguage();
        if (Intrinsics.areEqual(applicationLanguage, "tr")) {
            return "file:///android_asset/privacypolicy/privacy_policy_tr.html";
        }
        return "file:///android_asset/privacypolicy/privacy_policy_" + applicationLanguage + ".html";
    }

    public static void hideKeyboard(final Context context, final IBinder windowToken) {
        Intrinsics.checkNotNullParameter(windowToken, "windowToken");
        if (null == context) {
            return;
        }
        final Object systemService = context.getSystemService(Context.INPUT_METHOD_SERVICE);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService).hideSoftInputFromWindow(windowToken, 0);
    }

    public static  boolean isConnecteddefault(final Context context, boolean z, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            z = true;
        }
        return ViewExtensionsKt.isConnected(context, z);
    }

    public static boolean isConnected(final Context context, final boolean z) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        final boolean isConnected = Connectivity.isConnected(context);
        if (!isConnected && z) {
            final String string = context.getString(R.string.exp_23_internet_connection_not_found);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            ViewExtensionsKt.toastdefault(context, string, 0, 2, null);
        }
        return isConnected;
    }

    public static void setBottomSheetHeight(final View view, final BottomSheetBehavior<?> behavior, final double d2, final WindowManager windowManager) {
        Intrinsics.checkNotNullParameter(behavior, "behavior");
        if (null == view) {
            return;
        }
        final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        final int usableScreenHeight = WindowManagerExtensions.getUsableScreenHeight(windowManager);
        if (null != layoutParams) {
            layoutParams.height = (int) (usableScreenHeight * d2);
        }
        view.setLayoutParams(layoutParams);
        behavior.setState(3);
        behavior.setPeekHeight((int) (usableScreenHeight * d2));
    }

    public static void setVisible(final View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(View.VISIBLE);
    }

    public static void setInvisible(final View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(View.INVISIBLE);
    }

    public static void setGone(final View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(View.GONE);
    }

    public static void alertdefault(final Context context, int i2, final Function1 function1, final int i3, final Object obj) {
        if (0 != (i3 & 1)) {
            i2 = 0;
        }
        ViewExtensionsKt.alert(context, i2, function1);
    }

    public static void alert(final Context context, @StyleRes final int i2, final Function1<? super AlertDialog.Builder, Unit> dialogBuilder) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(dialogBuilder, "dialogBuilder");
        final AlertDialog.Builder builder = new AlertDialog.Builder(context, i2);
        builder.setCancelable(false);
        dialogBuilder.invoke(builder);
        builder.create();
        builder.show();
    }

    public static void negativeButtondefault(final AlertDialog.Builder builder, int i2, Function1 function1, final int i3, final Object obj) {
        if (0 != (i3 & 1)) {
            i2 = R.string.str_cancel;
        }
        if (0 != (i3 & 2)) {
            final EnumNamingStrategies.KebabCaseStrategy ViewExtensionsKtnegativeButton1 = null;
            function1 = (Function1) EnumNamingStrategies.KebabCaseStrategy.INSTANCE;
        }
        ViewExtensionsKt.negativeButton(builder, i2, function1);
    }

    public static void negativeButton(final AlertDialog.Builder builder, final int i2, final Function1<? super DialogInterface, Unit> handleClick) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(handleClick, "handleClick");
        builder.setNegativeButton(i2, new DialogInterface.OnClickListener() {
            public void ViewExtensionsKtExternalSyntheticLambda2() {
            }

            public void onClick(final DialogInterface dialogInterface, final int i3) {
                negativeButtonlambda2(handleClick, dialogInterface, i3);
            }
        });
    }

    public static void negativeButtonlambda2(final Function1<? super DialogInterface, Unit> handleClick, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(handleClick, "handleClick");
        Intrinsics.checkNotNull(dialogInterface);
        handleClick.invoke(dialogInterface);
    }

    public static void positiveButtondefault(final AlertDialog.Builder builder, int i2, Function1<? super DialogInterface, Unit> function1, final int i3, final Object obj) {
        if (0 != (i3 & 1)) {
            i2 = R.string.str_okey;
        }
        if (0 != (i3 & 2)) {
            final EnumNamingStrategies.KebabCaseStrategy ViewExtensionsKtpositiveButton1 = null;
            function1 = (Function1<? super DialogInterface, Unit>) EnumNamingStrategies.KebabCaseStrategy.INSTANCE;
        }
        ViewExtensionsKt.positiveButton(builder, i2, function1);
    }

    public static void positiveButton(final AlertDialog.Builder builder, final int i2, final Function1<? super DialogInterface, Unit> handleClick) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(handleClick, "handleClick");
        builder.setPositiveButton(i2, new DialogInterface.OnClickListener() {
            public void ViewExtensionsKtExternalSyntheticLambda0() {
            }
            public void onClick(final DialogInterface dialogInterface, final int i3) {
                positiveButtonlambda3(handleClick, dialogInterface, i3);
            }
        });
    }

    public static void positiveButtonlambda3(final Function1<? super DialogInterface, Unit> handleClick, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(handleClick, "handleClick");
        Intrinsics.checkNotNull(dialogInterface);
        handleClick.invoke(dialogInterface);
    }

    public static void neutralButtondefault(final AlertDialog.Builder builder, int i2, Function1<? super DialogInterface, Unit> function1, final int i3, final Object obj) {
        if (0 != (i3 & 1)) {
            i2 = R.string.str_okey;
        }
        if (0 != (i3 & 2)) {
            final EnumNamingStrategies.KebabCaseStrategy ViewExtensionsKtneutralButton1 = null;
            function1 = (Function1<? super DialogInterface, Unit>) EnumNamingStrategies.KebabCaseStrategy.INSTANCE;
        }
        ViewExtensionsKt.neutralButton(builder, i2, function1);
    }

    public static void neutralButton(final AlertDialog.Builder builder, final int i2, final Function1<? super DialogInterface, Unit> handleClick) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(handleClick, "handleClick");
        builder.setNeutralButton(i2, new DialogInterface.OnClickListener() {
            public void ViewExtensionsKtExternalSyntheticLambda1() {
            }
            public void onClick(final DialogInterface dialogInterface, final int i3) {
                neutralButtonlambda4(handleClick,
                        dialogInterface,
                        i3);
            }
        });
    }

    public static void neutralButtonlambda4(final Function1<? super DialogInterface, Unit> handleClick, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(handleClick, "handleClick");
        Intrinsics.checkNotNull(dialogInterface);
        handleClick.invoke(dialogInterface);
    }
}
