package com.proje.mobilesales.features.collections.chequeanddeedfiche.view.activity;

import android.Manifest;
import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.RequiresPermission;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFicheDetail;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.repository.ChequeAndDeedFicheRepository;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.view.fragment.ChequeDeedFicheDetailEnterFragment;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.viewmodel.ChequeAndDeedFicheViewModel;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.reports.view.activity.ReportAllActivity;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import static com.proje.mobilesales.core.utils.AppUtils.alert;

public final class ChequeDeedFicheDetailEnterActivity extends BaseErpActivity {
    public static final Companion Companion = new Companion(null);
    private static final int Detay = 1;
    private static final int Kaydet = 0;
    private static final int Rapor = 2;
    private static final int Vazgec = 3;
    private ChequeDeedFicheDetail chequeDeedFicheDetail;
    private int invoiceRef;
    private int position;
    private final ChequeAndDeedFicheRepository repository = new ChequeAndDeedFicheRepository();
    private final ChequeAndDeedFicheViewModel viewModel = new ChequeAndDeedFicheViewModel(repository);
    private final DialogInterface.OnClickListener dialogClickListenerVade = new DialogInterface.OnClickListener() {
        public void ChequeDeedFicheDetailEnterActivityExternalSyntheticLambda2() {
        } 
        public void onClick(final DialogInterface dialogInterface, final int i2) {
            dialogClickListenerVadelambda3(ChequeDeedFicheDetailEnterActivity.this, dialogInterface, i2);
        }
    };
    private DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() { 
        public void ChequeDeedFicheDetailEnterActivityExternalSyntheticLambda3() {
        } 
        public void onClick(final DialogInterface dialogInterface, final int i2) {
            dialogClickListenerlambda4(ChequeDeedFicheDetailEnterActivity.this, dialogInterface, i2);
        }
    };
     @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_cheque_deed_fiche_detail_enter);
        this.setToolbar();
        this.getExtras();
        final Bundle extras = this.getIntent().getExtras();
        Intrinsics.checkNotNull(extras);
        final ChequeAndDeedFicheActivity.Companion companion = ChequeAndDeedFicheActivity.Companion;
        chequeDeedFicheDetail = extras.getParcelable(companion.getCHEQUE_DEED_DETAIL());
        position = this.getIntent().getIntExtra(companion.getCHEQUE_DEED_DETAIL_POSITION(), 0);
        invoiceRef = this.getIntent().getIntExtra(IntentExtraName.INVOICE_PAYMENTLINE_REF, 0);
        customerRef = this.getIntent().getIntExtra(IntentExtraName.EXTRA_CUSTOMER_REF, 0);
        if (customerOperation.getReceiptType() == ReceiptType.CHEQUE) {
            this.setTitle(this.getString(R.string.str_cheque_collection_entry));
        } else {
            this.setTitle(this.getString(R.string.str_payroll_note_entry));
        }
        final FloatingActionButton floatingActionButton = this.findViewById(R.id.fabFicheSave);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void ChequeDeedFicheDetailEnterActivityExternalSyntheticLambda4() {
            }
            public void onClick(final View view) {
                onCreatelambda0(ChequeDeedFicheDetailEnterActivity.this, view);
            }
        });
        if (customerOperation.getFicheMode() == FicheMode.ANALYSE) {
            floatingActionButton.setVisibility(View.GONE);
        }
        if (0 >= MainActivity.sFicheRef) {
            this.openFirstRecord();
        }
        this.getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, this.instantiateFragment(), ChequeDeedFicheDetailEnterFragment.CHEQUE_DEED_DETAIL_ENTER).commit();
    }
    public static void onCreatelambda0(final ChequeDeedFicheDetailEnterActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.controlDueDate();
    }
    private Fragment instantiateFragment() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(ChequeAndDeedFicheActivity.Companion.getCHEQUE_DEED_DETAIL(), chequeDeedFicheDetail);
        bundle.putParcelable(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, customerOperation);
        bundle.putInt(IntentExtraName.EXTRA_CUSTOMER_REF, customerRef);
        final Fragment instantiate = Fragment.instantiate(this, ChequeDeedFicheDetailEnterFragment.class.getName(), bundle);
        Intrinsics.checkNotNullExpressionValue(instantiate, "instantiate(...)");
        return instantiate;
    }
    private void openFirstRecord() {
        final Cursor query = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT MAX(LOGICALREF) AS LOGICALREF FROM CHEQUEDEED");
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
        menu.add(0, 0, 0, this.getString(R.string.str_save)).setIcon(R.drawable.ic_menu_save);
        menu.add(0, 2, 0, this.getString(R.string.str_reports)).setIcon(R.drawable.ic_menu_directions);
        menu.add(0, 3, 0, this.getString(R.string.str_close)).setIcon(R.drawable.ic_menu_close_clear_cancel);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemId = item.getItemId();
        if (0 == itemId) {
            this.controlDueDate();
        } else {
            if (16908332 == itemId) {
                this.cancelFiche();
                return true;
            }
            if (2 == itemId) {
                this.startActivityForResult(new Intent(this, ReportAllActivity.class), 0);
            } else if (3 == itemId) {
                this.cancelFiche();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    private void controlDueDate() {
        if (this.isAutoDateTimeAndZoneEnabled()) {
            final ChequeDeedFicheDetail chequeDeedFicheDetail = this.chequeDeedFicheDetail;
            Intrinsics.checkNotNull(chequeDeedFicheDetail);
            final String ficheStringProp = chequeDeedFicheDetail.getDueDate().toString();
            Intrinsics.checkNotNullExpressionValue(ficheStringProp, "toString(...)");
            if (TextUtils.isEmpty(ficheStringProp)) {
                Toast.makeText(this, this.getString(R.string.exp_expiry_date), Toast.LENGTH_SHORT).show();
                return;
            }
            if (customerOperation.getReceiptType() == ReceiptType.CHEQUE) {
                if (DateAndTimeUtils.getDateInt(ficheStringProp) < DateAndTimeUtils.getDateInt(DateAndTimeUtils.nowDate())) {
                    new AlertDialog.Builder(this).setMessage(R.string.str_overdue_check).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void ChequeDeedFicheDetailEnterActivityExternalSyntheticLambda0() {
                        }
                        public void onClick(final DialogInterface dialogInterface, final int i2) {
                            controlDueDatelambda1(ChequeDeedFicheDetailEnterActivity.this, dialogInterface, i2);
                        }
                    }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialogInterface, final int i2) {
                            controlDueDatelambda2(dialogInterface, i2);
                        }
                    }).setCancelable(false).create().show();
                    return;
                } else {
                    this.addReceiptLine();
                    return;
                }
            }
            this.addReceiptLine();
        }
    }
    public static void controlDueDatelambda1(final ChequeDeedFicheDetailEnterActivity this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.addReceiptLine();
    }
    public static void controlDueDatelambda2(final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }
    public static void dialogClickListenerVadelambda3(final ChequeDeedFicheDetailEnterActivity this0, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        if (-2 == i2) {
            dialog.dismiss();
        } else {
            if (-1 != i2) {
                return;
            }
            this0.addReceiptLine();
        }
    }
    private void addReceiptLine() {
        try {
            final ChequeDeedFicheDetail chequeDeedFicheDetail = this.chequeDeedFicheDetail;
            Intrinsics.checkNotNull(chequeDeedFicheDetail);
            final String ficheStringProp = chequeDeedFicheDetail.getTotal().toString();
            Intrinsics.checkNotNullExpressionValue(ficheStringProp, "toString(...)");
            if (0.0d >= StringUtils.toDouble(ficheStringProp)) {
                Toast.makeText(this, this.getString(R.string.str_question_enter_amount), Toast.LENGTH_SHORT).show();
                return;
            }
            final ChequeDeedFicheDetail chequeDeedFicheDetail2 = this.chequeDeedFicheDetail;
            Intrinsics.checkNotNull(chequeDeedFicheDetail2);
            chequeDeedFicheDetail2.setChequeDeedId(MainActivity.sFicheRef);
            final Intent intent = new Intent();
            final ChequeAndDeedFicheActivity.Companion companion = ChequeAndDeedFicheActivity.Companion;
            intent.putExtra(companion.getCHEQUE_DEED_DETAIL(), this.chequeDeedFicheDetail);
            intent.putExtra(companion.getCHEQUE_DEED_DETAIL_POSITION(), position);
            this.setResult(-1, intent);
            this.finish();
        } catch (final Exception e2) {
            alert(e2.getMessage());
        }
    }
    private void cancelFiche() {
        if (customerOperation.getFicheMode() != FicheMode.ANALYSE) {
            final ChequeDeedFicheDetail chequeDeedFicheDetail = this.chequeDeedFicheDetail;
            Intrinsics.checkNotNull(chequeDeedFicheDetail);
            final String ficheStringProp = chequeDeedFicheDetail.getTotal().toString();
            Intrinsics.checkNotNullExpressionValue(ficheStringProp, "toString(...)");
            if (0.0d < StringUtils.toDouble(ficheStringProp)) {
                if (0 < this.invoiceRef) {
                    Toast.makeText(this, this.getString(R.string.str_should_collections_save_before_leave_the_fiche), Toast.LENGTH_LONG).show();
                    return;
                } else {
                    new AlertDialog.Builder(this).setMessage(this.getString(R.string.str_question_want_close)).setPositiveButton(this.getString(R.string.str_yes), dialogClickListener).setNegativeButton(this.getString(R.string.str_no), dialogClickListener).setNeutralButton(this.getString(R.string.str_save), dialogClickListener).show();
                    return;
                }
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
    public static void dialogClickListenerlambda4(final ChequeDeedFicheDetailEnterActivity this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (-3 == i2) {
            this0.addReceiptLine();
        } else {
            if (-1 != i2) {
                return;
            }
            this0.setResult(0);
            this0.finish();
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
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
