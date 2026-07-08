package com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import cardtek.masterpass.interfaces.CheckMasterPassListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.results.CheckMasterPassResult;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.masterpass.AccountStatus;
import com.proje.mobilesales.core.masterpass.AccountStatusResult;
import com.proje.mobilesales.core.masterpass.UIMessageHelper;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.fragment.BottomAlertDialogFragment;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: CashCreditFicheDetailEnterFragment.kt */
/* renamed from: com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragmentcreateImageButtonForCreditCardNumber11 */

public final class C2648x4a82d6e2 implements CheckMasterPassListener {
    final CashCreditFicheDetailEnterFragment this0;

    /* compiled from: CashCreditFicheDetailEnterFragment.kt */
    /* renamed from: com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragmentcreateImageButtonForCreditCardNumber11WhenMappings */
    public enum WhenMappings {
        ;
        public static final int[] EnumSwitchMapping0;

        static {
            final int[] iArr = new int[AccountStatus.values().length];
            try {
                iArr[AccountStatus.Linked.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                iArr[AccountStatus.NotLinked.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                iArr[AccountStatus.CannotBeUsed.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            EnumSwitchMapping0 = iArr;
        }
    }

    C2648x4a82d6e2(final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment) {
        this0 = cashCreditFicheDetailEnterFragment;
    }

    @Override // cardtek.masterpass.interfaces.CheckMasterPassListener
    public void onSuccess(final CheckMasterPassResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        final ProgressDialogBuilder<?> progressDialogBuilder = this0.masterPassProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        AccountStatusResult accountStatusResult = UIMessageHelper.getAccountStatusResult(result.getAccountStatus());
        final AccountStatus accountStatus = accountStatusResult.getAccountStatus();
        final int i2 = null == accountStatus ? -1 : WhenMappings.EnumSwitchMapping0[accountStatus.ordinal()];
        if (1 == i2) {
            final FragmentActivity activity = this0.getActivity();
            Intrinsics.checkNotNull(activity);
            CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment = this0;
            activity.runOnUiThread(new Runnable() { // from class: com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragmentcreateImageButtonForCreditCardNumber11ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public void run() {
                    onSuccesslambda0(CashCreditFicheDetailEnterFragment.this);
                }
            });
            return;
        }
        if (2 == i2) {
            final FragmentActivity activity2 = this0.getActivity();
            Intrinsics.checkNotNull(activity2);
            CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment2 = this0;
            activity2.runOnUiThread(new Runnable() { // from class: com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragmentcreateImageButtonForCreditCardNumber11ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public void run() {
                    onSuccesslambda3(AccountStatusResult.this, cashCreditFicheDetailEnterFragment2);
                }
            });
            return;
        }
        if (3 != i2) {
            return;
        }
        final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment3 = this0;
        final String string = cashCreditFicheDetailEnterFragment3.getString(R.string.str_masterpass);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        final String message = accountStatusResult.getMessage();
        Intrinsics.checkNotNullExpressionValue(message, "getMessage(...)");
        cashCreditFicheDetailEnterFragment3.showMasterPassAlertDialog(string, message);
    }

    
    public static void onSuccesslambda0(final CashCreditFicheDetailEnterFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (ContextUtils.checkInternetConnection()) {
            this0.getCards();
        }
    }

    
    public static void onSuccesslambda3(final AccountStatusResult accountStatusResult, CashCreditFicheDetailEnterFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        BottomAlertDialogFragment bottomAlertDialogFragment = new BottomAlertDialogFragment();
        bottomAlertDialogFragment.setMessage(accountStatusResult.getMessage());
        bottomAlertDialogFragment.setTitle(this0.getString(R.string.str_masterpass));
        bottomAlertDialogFragment.setCancelable(false);
        bottomAlertDialogFragment.setPositiveButton(this0.getString(R.string.str_link_my_card), new View.OnClickListener() { // from class: com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragmentcreateImageButtonForCreditCardNumber11ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                onSuccesslambda3lambda1(BottomAlertDialogFragment.this, this0, view);
            }
        });
        bottomAlertDialogFragment.setNegativeButton("", new View.OnClickListener() { // from class: com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragmentcreateImageButtonForCreditCardNumber11ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                onSuccesslambda3lambda2(BottomAlertDialogFragment.this, view);
            }
        });
        final FragmentActivity activity = this0.getActivity();
        Intrinsics.checkNotNull(activity);
        bottomAlertDialogFragment.show(activity.getSupportFragmentManager(), "BottomAlertDialogFragment");
    }

    
    public static void onSuccesslambda3lambda1(final BottomAlertDialogFragment notLinked, final CashCreditFicheDetailEnterFragment this0, final View view) {
        Intrinsics.checkNotNullParameter(notLinked, "notLinked");
        Intrinsics.checkNotNullParameter(this0, "this0");
        notLinked.dismiss();
        if (ContextUtils.checkInternetConnection()) {
            this0.linkCardToClient();
        }
    }

    
    public static void onSuccesslambda3lambda2(final BottomAlertDialogFragment notLinked, final View view) {
        Intrinsics.checkNotNullParameter(notLinked, "notLinked");
        notLinked.dismiss();
    }

    @Override // cardtek.masterpass.interfaces.CheckMasterPassListener
    public void onServiceError(final ServiceError result) {
        Intrinsics.checkNotNullParameter(result, "result");
        final ProgressDialogBuilder<?> progressDialogBuilder = this0.masterPassProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment = this0;
        final String responseCode = result.getResponseCode();
        Intrinsics.checkNotNullExpressionValue(responseCode, "getResponseCode(...)");
        final String responseDesc = result.getResponseDesc();
        Intrinsics.checkNotNullExpressionValue(responseDesc, "getResponseDesc(...)");
        cashCreditFicheDetailEnterFragment.showMasterPassAlertDialog(responseCode, responseDesc);
    }

    @Override // cardtek.masterpass.interfaces.CheckMasterPassListener
    public void onInternalError(final InternalError result) {
        Intrinsics.checkNotNullParameter(result, "result");
        final ProgressDialogBuilder<?> progressDialogBuilder = this0.masterPassProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment = this0;
        final String errorCode = result.getErrorCode();
        Intrinsics.checkNotNullExpressionValue(errorCode, "getErrorCode(...)");
        final String errorDesc = result.getErrorDesc();
        Intrinsics.checkNotNullExpressionValue(errorDesc, "getErrorDesc(...)");
        cashCreditFicheDetailEnterFragment.showMasterPassAlertDialog(errorCode, errorDesc);
    }
}
