package com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment;

import android.view.View;
import android.widget.EditText;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.FragmentActivity;
import cardtek.masterpass.data.MasterPassCard;
import cardtek.masterpass.interfaces.GetCardsListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.results.GetCardsResult;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.interfaces.MasterPassCardResultListener;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.masterpass.MasterPassCardItem;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFicheDetail;
import com.proje.mobilesales.features.fragment.MasterPassCardFragment;
import com.proje.mobilesales.features.model.FicheStringProp;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class CashCreditFicheDetailEnterFragmentcards1 implements GetCardsListener {
    final CashCreditFicheDetailEnterFragment this0;

    CashCreditFicheDetailEnterFragmentcards1(final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment) {
        this0 = cashCreditFicheDetailEnterFragment;
    }

    @Override // cardtek.masterpass.interfaces.GetCardsListener
    public void onSuccess(GetCardsResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        final FragmentActivity activity = this0.getActivity();
        Intrinsics.checkNotNull(activity);
        CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment = this0;
        activity.runOnUiThread(new Runnable() { // from class: com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragmentcards1ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public void run() {
                onSuccesslambda0(CashCreditFicheDetailEnterFragment.this, result);
            }
        });
    }

    
    public static void onSuccesslambda0(CashCreditFicheDetailEnterFragment this0, final GetCardsResult result) {
        final ArrayList<MasterPassCardItem> cardItems;
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(result, "result");
        final ProgressDialogBuilder<?> progressDialogBuilder = this0.masterPassProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        final MasterPassCardFragment.Companion companion = MasterPassCardFragment.Companion;
        final String string = this0.getString(R.string.str_select_card);
        final ArrayList<MasterPassCard> cards = result.getCards();
        Intrinsics.checkNotNullExpressionValue(cards, "getCards(...)");
        cardItems = this0.getCardItems(cards);
        final MasterPassCardFragment newInstance = companion.newInstance(string, cardItems, false);
        newInstance.setCancelable(false);
        newInstance.setCardResultListener(new MasterPassCardResultListener() { // from class: com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragmentcards1onSuccess11
            @Override // com.proje.mobilesales.core.interfaces.MasterPassCardResultListener
            public void onSelected(final BottomSheetDialog dialog, final MasterPassCardItem item, final int i2) {
                final EditText editText;
                final EditText editText2;
                final EditText editText3;
                final AppCompatCheckBox appCompatCheckBox;
                final View view;
                Intrinsics.checkNotNullParameter(dialog, "dialog");
                Intrinsics.checkNotNullParameter(item, "item");
                CashCreditFicheDetailEnterFragment.this.selectedCard = item;
                final CashCreditFicheDetail cashCreditFicheDetail = CashCreditFicheDetailEnterFragment.this.getCashCreditFicheDetail();
                Intrinsics.checkNotNull(cashCreditFicheDetail);
                cashCreditFicheDetail.setCreditCardNo(new FicheStringProp(item.getMaskedPan()));
                editText = CashCreditFicheDetailEnterFragment.this.etCreditCardNo;
                Intrinsics.checkNotNull(editText);
                editText.setText(item.getMaskedPan());
                editText2 = CashCreditFicheDetailEnterFragment.this.etCreditCardNo;
                Intrinsics.checkNotNull(editText2);
                editText2.setEnabled(false);
                editText3 = CashCreditFicheDetailEnterFragment.this.edtPhone;
                Intrinsics.checkNotNull(editText3);
                editText3.setEnabled(false);
                appCompatCheckBox = CashCreditFicheDetailEnterFragment.this.chkUse3d;
                Intrinsics.checkNotNull(appCompatCheckBox);
                appCompatCheckBox.setEnabled(true);
                view = CashCreditFicheDetailEnterFragment.this.lnUse3d;
                Intrinsics.checkNotNull(view);
                view.setVisibility(0);
                dialog.dismiss();
            }

            @Override // com.proje.mobilesales.core.interfaces.MasterPassCardResultListener
            public void onCancelled(final BottomSheetDialog dialog) {
                Intrinsics.checkNotNullParameter(dialog, "dialog");
                CashCreditFicheDetailEnterFragment.this.clearMasterPassCardSelection();
                dialog.dismiss();
            }
        });
        final FragmentActivity activity = this0.getActivity();
        Intrinsics.checkNotNull(activity);
        newInstance.show(activity.getSupportFragmentManager(), "MasterPassCardFragment");
    }

    @Override // cardtek.masterpass.interfaces.GetCardsListener
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

    @Override // cardtek.masterpass.interfaces.GetCardsListener
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
