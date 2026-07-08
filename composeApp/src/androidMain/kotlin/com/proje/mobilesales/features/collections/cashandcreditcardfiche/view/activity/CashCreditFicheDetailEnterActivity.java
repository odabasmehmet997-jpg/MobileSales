package com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.activity;

import android.Manifest;
import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.RequiresPermission;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.interfaces.PurchaseCompleteListener;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFicheDetail;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.repository.CashAndCreditCardFicheRepository;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragment;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.viewmodel.CashAndCreditCardFicheViewModel;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.fragment.BottomAlertDialogFragment;
import com.proje.mobilesales.features.reports.view.activity.ReportAllActivity;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import static com.proje.mobilesales.core.utils.AppUtils.alert;

public final class CashCreditFicheDetailEnterActivity extends BaseErpActivity {
    public static final Companion Companion = new Companion(null);
    private static final int Kaydet = 0;
    private static final int PayAndSave = 4;
    private static final int Rapor = 2;
    private static final String STATE_CUSTOMER_OPERATION = "state:customerOperation";
    private static final String STATE_CUSTOMER_REF = "state:mycustomerRef";
    private static final String STATE_DETAIL = "state:cashCreditDetail";
    private static final String STATE_INVOICE_REF = "state:invoiceRef";
    private static final String STATE_POSITION = "state:position";
    private static final int Vazgec = 3;
    private static FragmentActivity this02;
    private CashCreditFicheDetail cashCreditFicheDetail;
    private int invoiceRef;
    private CashCreditFicheDetailEnterFragment mDetailEnterFragment;
    private int position;
    private final CashAndCreditCardFicheRepository repository = new CashAndCreditCardFicheRepository();
    private final CashAndCreditCardFicheViewModel viewModel = new CashAndCreditCardFicheViewModel(repository);
    private DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        public void CashCreditFicheDetailEnterActivityExternalSyntheticLambda4() { }
        public void onClick(final DialogInterface dialogInterface, final int i2) {
            dialogClickListenerlambda7(CashCreditFicheDetailEnterActivity.this, dialogInterface, i2);
        }
    };
    private static CashCreditFicheDetailEnterActivity r2;
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_cash_credit_fiche_detail_enter);
        this.setToolbar();
        this.getExtras();
        if (bundle == null) {
            final Bundle extras = this.getIntent().getExtras();
            Intrinsics.checkNotNull(extras);
            final CashAndCreditCardFicheActivity.Companion companion = CashAndCreditCardFicheActivity.Companion;
            cashCreditFicheDetail = extras.getParcelable(companion.getCASH_CREDIT_DETAIL());
            position = this.getIntent().getIntExtra(companion.getCASH_CREDIT_DETAIL_POSITION(), 0);
            invoiceRef = this.getIntent().getIntExtra(IntentExtraName.INVOICE_PAYMENTLINE_REF, 0);
            customerRef = this.getIntent().getIntExtra(IntentExtraName.EXTRA_CUSTOMER_REF, 0);
        } else {
            cashCreditFicheDetail = bundle.getParcelable(CashCreditFicheDetailEnterActivity.STATE_DETAIL);
            position = bundle.getInt(CashCreditFicheDetailEnterActivity.STATE_POSITION, 0);
            invoiceRef = bundle.getInt(CashCreditFicheDetailEnterActivity.STATE_INVOICE_REF, 0);
            customerRef = bundle.getInt(CashCreditFicheDetailEnterActivity.STATE_CUSTOMER_REF, 0);
            customerOperation = bundle.getParcelable(CashCreditFicheDetailEnterActivity.STATE_CUSTOMER_OPERATION);
        }
        if (customerOperation.getReceiptType() == ReceiptType.CASH) {
            this.setTitle(this.getString(R.string.str_cash_entry));
        } else {
            this.setTitle(this.getString(R.string.str_credit_cart_entry));
        }
        final CashCreditFicheDetail cashCreditFicheDetail = this.cashCreditFicheDetail;
        if (null != cashCreditFicheDetail) {
            Intrinsics.checkNotNull(cashCreditFicheDetail);
            if (cashCreditFicheDetail.isPayedOnline() && customerOperation.getReceiptType() == ReceiptType.CREDIT) {
                customerOperation.setFicheMode(FicheMode.ANALYSE);
            }
        }
        final FloatingActionButton floatingActionButton = this.findViewById(R.id.fabFicheSave);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void CashCreditFicheDetailEnterActivityExternalSyntheticLambda3() { }
            public void onClick(final View view) {
                onCreatelambda0(CashCreditFicheDetailEnterActivity.this, view);
            }
        });
        if (customerOperation.getFicheMode() == FicheMode.ANALYSE) {
            floatingActionButton.setVisibility(View.GONE);
        }
        if (0 >= MainActivity.sFicheRef) {
            this.openFirstRecord();
        }
        final Fragment instantiateFragment = this.instantiateFragment();
        Intrinsics.checkNotNull(instantiateFragment, "null cannot be cast to non-null type com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragment");
        mDetailEnterFragment = (CashCreditFicheDetailEnterFragment) instantiateFragment;
        final FragmentTransaction beginTransaction = this.getSupportFragmentManager().beginTransaction();
        final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment = mDetailEnterFragment;
        Intrinsics.checkNotNull(cashCreditFicheDetailEnterFragment);
        beginTransaction.replace(R.id.content_layout, cashCreditFicheDetailEnterFragment, CashCreditFicheDetailEnterFragment.CASH_CREDIT_DETAIL_ENTER).commit();
    }
    public static void onCreatelambda0(final CashCreditFicheDetailEnterActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment = this0.mDetailEnterFragment;
        Intrinsics.checkNotNull(cashCreditFicheDetailEnterFragment);
        if (null == cashCreditFicheDetailEnterFragment.getSelectedCard()) {
            this0.addReceiptLine();
        } else {
            this0.payAndSave();
        }
    }
    private Fragment instantiateFragment() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(CashAndCreditCardFicheActivity.Companion.getCASH_CREDIT_DETAIL(), cashCreditFicheDetail);
        bundle.putParcelable(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, customerOperation);
        bundle.putInt(IntentExtraName.EXTRA_CUSTOMER_REF, customerRef);
        final Fragment instantiate = Fragment.instantiate(this, CashCreditFicheDetailEnterFragment.class.getName(), bundle);
        Intrinsics.checkNotNullExpressionValue(instantiate, "instantiate(...)");
        return instantiate;
    }
    private void openFirstRecord() {
        final Cursor query = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT MAX(LOGICALREF) AS LOGICALREF FROM CASHCREDIT");
        Intrinsics.checkNotNull(query);
        if (query.moveToFirst()) {
            do {
                MainActivity.sFicheRef = query.getInt(0);
            } while (query.moveToNext());
        }
        if (query.isClosed()) {
            return;
        }
        query.close();
    }
    public boolean onKeyDown(final int i2, final KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (4 == i2) {
            this.cancelFiche();
            return true;
        }
        return super.onKeyDown(i2, event);
    }
    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        if (viewModel.getBaseErp().useOnlinePayment() && customerOperation.getReceiptType() == ReceiptType.CREDIT) {
            final MenuItem add = menu.add(0, 4, 0, this.getString(R.string.str_pay_and_save));
            add.setIcon(R.drawable.ic_menu_save);
            add.setVisible(customerOperation.getFicheMode() != FicheMode.ANALYSE);
        }
        final MenuItem add2 = menu.add(0, 0, 0, this.getString(R.string.str_save));
        add2.setIcon(R.drawable.ic_menu_save);
        menu.add(0, 2, 0, this.getString(R.string.str_reports)).setIcon(R.drawable.ic_menu_directions);
        menu.add(0, 3, 0, this.getString(R.string.str_close)).setIcon(R.drawable.ic_menu_close_clear_cancel);
        add2.setVisible(customerOperation.getFicheMode() != FicheMode.ANALYSE);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemId = item.getItemId();
        if (0 == itemId) {
            this.addReceiptLine();
        } else {
            if (16908332 == itemId) {
                this.cancelFiche();
                return true;
            }
            if (2 == itemId) {
                this.startActivityForResult(new Intent(this, ReportAllActivity.class), 0);
            } else if (3 == itemId) {
                this.cancelFiche();
            } else if (4 == itemId) {
                this.payAndSave();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    private void payAndSave() {
        final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment = mDetailEnterFragment;
        Intrinsics.checkNotNull(cashCreditFicheDetailEnterFragment);
        if (null == cashCreditFicheDetailEnterFragment.getSelectedCard()) {
            this.showAlertDialog(this.getString(R.string.str_masterpass), this.getString(R.string.str_choose_masterpass_card_to_continue));
            return;
        }
        final CashCreditFicheDetail cashCreditFicheDetail = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail);
        if (cashCreditFicheDetail.isPayedOnline()) {
            this.showAlertDialog(this.getString(R.string.str_warning), this.getString(R.string.str_cannot_change_online_paid_transaction));
            return;
        }
        final CashCreditFicheDetail cashCreditFicheDetail2 = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail2);
        final String ficheStringProp = cashCreditFicheDetail2.getTotal().toString();
        Intrinsics.checkNotNullExpressionValue(ficheStringProp, "toString(...)");
        if (0.0d >= StringUtils.toDouble(ficheStringProp)) {
            Toast.makeText(this, this.getString(R.string.str_question_enter_amount), Toast.LENGTH_SHORT).show();
            return;
        }
        final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment2 = mDetailEnterFragment;
        Intrinsics.checkNotNull(cashCreditFicheDetailEnterFragment2);
        if (null == cashCreditFicheDetailEnterFragment2.getMasterPassServices()) {
            this.showAlertDialog(this.getString(R.string.str_masterpass), this.getString(R.string.str_masterpass_info_not_found));
            return;
        }
        final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment3 = mDetailEnterFragment;
        Intrinsics.checkNotNull(cashCreditFicheDetailEnterFragment3);
        cashCreditFicheDetailEnterFragment3.purchaseCompleteListener = new PurchaseCompleteListener() {
            public void CashCreditFicheDetailEnterActivityExternalSyntheticLambda6() {
            }
            public void onComplete(final String str) {
                payAndSavelambda3(CashCreditFicheDetailEnterActivity.this, str);
            }
        };
        final CashCreditFicheDetail cashCreditFicheDetail3 = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail3);
        this.runOnUiThread(new Runnable() {
            public final String f1 = "";
            public void CashCreditFicheDetailEnterActivityExternalSyntheticLambda7(final CashCreditFicheDetailEnterActivity str) {
                r2 = str;
            }
            public void run() {
                payAndSavelambda6(CashCreditFicheDetailEnterActivity.this, String.valueOf(r2));
            }
        });
    }
    public void payAndSavelambda3(final CashCreditFicheDetailEnterActivity this0, final String str) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final CashCreditFicheDetail cashCreditFicheDetail = this0.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail);
        cashCreditFicheDetail.setPayedOnline(true);
        this0.runOnUiThread(new Runnable() {
            public void CashCreditFicheDetailEnterActivityExternalSyntheticLambda5() {
            }
            public void run() {
                payAndSavelambda3lambda2(CashCreditFicheDetailEnterActivity.this);
            }
        });
    }
    public static void payAndSavelambda3lambda2(final CashCreditFicheDetailEnterActivity this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final BottomAlertDialogFragment bottomAlertDialogFragment = new BottomAlertDialogFragment();
        bottomAlertDialogFragment.setMessage(this0.getString(R.string.str_master_pass_purchase_complete));
        bottomAlertDialogFragment.setTitle(this0.getString(R.string.str_masterpass));
        bottomAlertDialogFragment.setCancelable(false);
        bottomAlertDialogFragment.setShowNegativeButton(false);
        bottomAlertDialogFragment.setPositiveButton("", new View.OnClickListener() {
            public final CashCreditFicheDetailEnterActivity f1 = null;
            public void CashCreditFicheDetailEnterActivityExternalSyntheticLambda0(final CashCreditFicheDetailEnterActivity this02) {
                r2 = this02;
            }
            public void onClick(final View view) {
                payAndSavelambda3lambda2lambda1(bottomAlertDialogFragment, r2, view);
            }
        });
        bottomAlertDialogFragment.show(this02.getSupportFragmentManager(), "BottomAlertDialogFragment");
    }
    public static void payAndSavelambda3lambda2lambda1(final BottomAlertDialogFragment completeDialog, final CashCreditFicheDetailEnterActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(completeDialog, "completeDialog");
        Intrinsics.checkNotNullParameter(this0, "this0");
        completeDialog.dismiss();
        this0.addReceiptLine();
    }
    public static void payAndSavelambda6(final CashCreditFicheDetailEnterActivity this0, final String totalDefiniton) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(totalDefiniton, "totalDefiniton");
        final BottomAlertDialogFragment bottomAlertDialogFragment = new BottomAlertDialogFragment();
        bottomAlertDialogFragment.setMessage(this0.getString(R.string.str_approve_masterpass_payment));
        bottomAlertDialogFragment.setTitle(totalDefiniton);
        bottomAlertDialogFragment.setCancelable(false);
        bottomAlertDialogFragment.setPositiveButton(this0.getString(R.string.str_okey), new View.OnClickListener() {
            public final CashCreditFicheDetailEnterActivity f1 = null;
            public void CashCreditFicheDetailEnterActivityExternalSyntheticLambda1(final CashCreditFicheDetailEnterActivity this02) {
                r2 = this02;
            }
            public void onClick(final View view) {
                payAndSavelambda6lambda4(bottomAlertDialogFragment, r2, view);
            }
        });
        bottomAlertDialogFragment.setNegativeButton("", new View.OnClickListener() {
            public void CashCreditFicheDetailEnterActivityExternalSyntheticLambda2() {
            }
            public void onClick(final View view) {
                payAndSavelambda6lambda5(bottomAlertDialogFragment, view);
            }
        });
        bottomAlertDialogFragment.show(this02.getSupportFragmentManager(), "ConfirmPaymentBottomAlertDialogFragment");
    }
    public static void payAndSavelambda6lambda4(final BottomAlertDialogFragment confirmPayment, final CashCreditFicheDetailEnterActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(confirmPayment, "confirmPayment");
        Intrinsics.checkNotNullParameter(this0, "this0");
        confirmPayment.dismiss();
        if (ContextUtils.checkInternetConnection()) {
            final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment = this0.mDetailEnterFragment;
            Intrinsics.checkNotNull(cashCreditFicheDetailEnterFragment);
            cashCreditFicheDetailEnterFragment.purchase();
        }
    }
    public static void payAndSavelambda6lambda5(final BottomAlertDialogFragment confirmPayment, final View view) {
        Intrinsics.checkNotNullParameter(confirmPayment, "confirmPayment");
        confirmPayment.dismiss();
    }
    private void addReceiptLine() {
        try {
            final CashCreditFicheDetail cashCreditFicheDetail = this.cashCreditFicheDetail;
            Intrinsics.checkNotNull(cashCreditFicheDetail);
            final String ficheStringProp = cashCreditFicheDetail.getTotal().toString();
            Intrinsics.checkNotNullExpressionValue(ficheStringProp, "toString(...)");
            if (0.0d >= StringUtils.toDouble(ficheStringProp)) {
                Toast.makeText(this, this.getString(R.string.str_question_enter_amount), Toast.LENGTH_SHORT).show();
                return;
            }
            final CashCreditFicheDetail cashCreditFicheDetail2 = this.cashCreditFicheDetail;
            Intrinsics.checkNotNull(cashCreditFicheDetail2);
            cashCreditFicheDetail2.setCashCreditId(MainActivity.sFicheRef);
            final Intent intent = new Intent();
            final CashAndCreditCardFicheActivity.Companion companion = CashAndCreditCardFicheActivity.Companion;
            intent.putExtra(companion.getCASH_CREDIT_DETAIL(), this.cashCreditFicheDetail);
            intent.putExtra(companion.getCASH_CREDIT_DETAIL_POSITION(), position);
            this.setResult(-1, intent);
            this.finish();
        } catch (final Exception e2) {
            alert(e2.getMessage());
        }
    }
    private void cancelFiche() {
        if (customerOperation.getFicheMode() != FicheMode.ANALYSE) {
            final CashCreditFicheDetail cashCreditFicheDetail = this.cashCreditFicheDetail;
            Intrinsics.checkNotNull(cashCreditFicheDetail);
            final String ficheStringProp = cashCreditFicheDetail.getTotal().toString();
            Intrinsics.checkNotNullExpressionValue(ficheStringProp, "toString(...)");
            if (0.0d < StringUtils.toDouble(ficheStringProp)) {
                if (0 < this.invoiceRef) {
                    Toast.makeText(this, this.getString(R.string.str_should_collections_save_before_leave_the_fiche), Toast.LENGTH_LONG).show();
                    return;
                }
                final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment = mDetailEnterFragment;
                Intrinsics.checkNotNull(cashCreditFicheDetailEnterFragment);
                final String string = this.getString(null == cashCreditFicheDetailEnterFragment.getSelectedCard() ? R.string.str_save : R.string.str_pay_and_save);
                Intrinsics.checkNotNull(string);
                new AlertDialog.Builder(this).setMessage(this.getString(R.string.str_question_want_close)).setPositiveButton(this.getString(R.string.str_yes), dialogClickListener).setNegativeButton(this.getString(R.string.str_no), dialogClickListener).setNeutralButton(string, dialogClickListener).show();
                return;
            }
        }
        this.finish();
    }
    public DialogInterface.OnClickListener getDialogClickListener() {
        return dialogClickListener;
    }
    public void setDialogClickListener(final DialogInterface.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "<set-?>");
        dialogClickListener = onClickListener;
    }
    public static void dialogClickListenerlambda7(final CashCreditFicheDetailEnterActivity this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (-3 != i2) {
            if (-1 != i2) {
                return;
            }
            this0.setResult(0);
            this0.finish();
            return;
        }
        final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment = this0.mDetailEnterFragment;
        Intrinsics.checkNotNull(cashCreditFicheDetailEnterFragment);
        if (null == cashCreditFicheDetailEnterFragment.getSelectedCard()) {
            this0.addReceiptLine();
        } else {
            this0.payAndSave();
        }
    }
    public boolean dispatchTouchEvent(final MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (0 == event.getAction()) {
            final View currentFocus = this.getCurrentFocus();
            if (currentFocus instanceof EditText editText) {
                final Rect rect = new Rect();
                editText.getGlobalVisibleRect(rect);
                if (!rect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    editText.clearFocus();
                    final Object systemService = this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                    ((InputMethodManager) systemService).hideSoftInputFromWindow(editText.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
    public void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putParcelable(CashCreditFicheDetailEnterActivity.STATE_DETAIL, cashCreditFicheDetail);
        outState.putParcelable(CashCreditFicheDetailEnterActivity.STATE_CUSTOMER_OPERATION, customerOperation);
        outState.putInt(CashCreditFicheDetailEnterActivity.STATE_CUSTOMER_REF, customerRef);
        outState.putInt(CashCreditFicheDetailEnterActivity.STATE_INVOICE_REF, invoiceRef);
        outState.putInt(CashCreditFicheDetailEnterActivity.STATE_POSITION, position);
        super.onSaveInstanceState(outState);
    }
    public void onDestroy() {
        super.onDestroy();
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
