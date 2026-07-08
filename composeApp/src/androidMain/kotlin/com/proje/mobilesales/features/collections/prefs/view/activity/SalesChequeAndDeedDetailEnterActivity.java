package com.proje.mobilesales.features.collections.prefs.view.activity;

import android.R;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.*;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseErpActivityPreferences;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.preferences.LogoDatePreference;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.collections.prefs.repository.SalesChequeAndDeedDetailEnterRepository;
import com.proje.mobilesales.features.collections.prefs.viewmodel.SalesChequeAndDeedDetailEnterViewModel;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.reports.view.activity.ReportAllActivity;
import com.proje.mobilesales.features.settings.interfaces.PreferenceClear;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import static com.proje.mobilesales.core.utils.AppUtils.alert;

public final class SalesChequeAndDeedDetailEnterActivity extends BaseErpActivityPreferences implements SharedPreferences.OnSharedPreferenceChangeListener, PreferenceClear {
    private static final int ADD = 0;
    private static final int CANCEL = 4;
    public static final Companion Companion = new Companion(null);
    private static final int DETAIL = 2;
    private static final int REPORT = 3;
    private static final int SAVE = 1;
    private int branchNr;
    private DialogInterface.OnClickListener dialogClickListener;
    private final DialogInterface.OnClickListener dialogClickListenerDue;
    private LogoDatePreference dpDueDate;
    private EditTextPreference etDebtor;
    private ReceiptType mReceiptType;
    private final SalesChequeAndDeedDetailEnterRepository repository;
    private SharedPreferences f1228sp;
    private final SalesChequeAndDeedDetailEnterViewModel viewModel;
    public SalesChequeAndDeedDetailEnterActivity() {
        final SalesChequeAndDeedDetailEnterRepository salesChequeAndDeedDetailEnterRepository = new SalesChequeAndDeedDetailEnterRepository();
        repository = salesChequeAndDeedDetailEnterRepository;
        viewModel = new SalesChequeAndDeedDetailEnterViewModel(salesChequeAndDeedDetailEnterRepository);
        dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                dialogClickListenerlambda0(SalesChequeAndDeedDetailEnterActivity.this, dialogInterface, i2);
            }
        };
        dialogClickListenerDue = new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                dialogClickListenerDuelambda1(SalesChequeAndDeedDetailEnterActivity.this, dialogInterface, i2);
            }
        };
    }
    public SalesChequeAndDeedDetailEnterRepository getRepository() {
        return repository;
    }
    public SalesChequeAndDeedDetailEnterViewModel getViewModel() {
        return viewModel;
    }
    public SharedPreferences getSp() {
        return f1228sp;
    }
    public void setSp(final SharedPreferences sharedPreferences) {
        f1228sp = sharedPreferences;
    }
    public ReceiptType getMReceiptType() {
        return mReceiptType;
    }
    public void setMReceiptType(final ReceiptType receiptType) {
        mReceiptType = receiptType;
    }
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final Bundle extras = this.getIntent().getExtras();
        if (null != extras && extras.containsKey("BRANCH")) {
            branchNr = extras.getInt("BRANCH");
        }
        this.addPreferencesFromResource(R.xml.csdetailenter);
        f1228sp = PreferenceManager.getDefaultSharedPreferences(this);
        this.getExtras();
        Intrinsics.checkNotNull(extras);
        mReceiptType = (ReceiptType) extras.get("RECEIPT");
        this.createControls();
        final LogoDatePreference logoDatePreference = dpDueDate;
        Intrinsics.checkNotNull(logoDatePreference);
        logoDatePreference.setDate(DateAndTimeUtils.nowDate());
        Preferences.initSummary(dpDueDate);
        final EditTextPreference editTextPreference = etDebtor;
        Intrinsics.checkNotNull(editTextPreference);
        editTextPreference.setText(viewModel.getBaseErp().getLogoSqlHelper().getClName(customerRef));
        final EditTextPreference editTextPreference2 = etDebtor;
        Intrinsics.checkNotNull(editTextPreference2);
        editTextPreference2.setSummary(viewModel.getBaseErp().getLogoSqlHelper().getClName(customerRef));
        Preferences.initSummary(etDebtor);
        if (mReceiptType == ReceiptType.CHEQUE) {
            final Preference findPreference = this.findPreference("etKefil");
            Intrinsics.checkNotNull(findPreference, "null cannot be cast to non-null type android.preference.EditTextPreference");
            final Preference findPreference2 = this.findPreference("pgcsTutar");
            Intrinsics.checkNotNull(findPreference2, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference2).removePreference(findPreference);
            final Preference findPreference3 = this.findPreference("etPul");
            Intrinsics.checkNotNull(findPreference3, "null cannot be cast to non-null type android.preference.EditTextPreference");
            final Preference findPreference4 = this.findPreference("pgcsTutar");
            Intrinsics.checkNotNull(findPreference4, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference4).removePreference(findPreference3);
            final Preference findPreference5 = this.findPreference("etOdemeYeri");
            Intrinsics.checkNotNull(findPreference5, "null cannot be cast to non-null type android.preference.EditTextPreference");
            final Preference findPreference6 = this.findPreference("pgcsTutar");
            Intrinsics.checkNotNull(findPreference6, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference6).removePreference(findPreference5);
            this.setTitle(this.getString(R.string.str_cheque_collection_entry));
            return;
        }
        this.setTitle(this.getString(R.string.str_payroll_note_entry));
    }
    public void onDestroy() {
        super.onDestroy();
    }
    private void createControls() {
        final Preference findPreference = this.findPreference("dpVadeTarihi");
        Intrinsics.checkNotNull(findPreference, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.LogoDatePreference");
        dpDueDate = (LogoDatePreference) findPreference;
        final Preference findPreference2 = this.findPreference("etBorclu");
        Intrinsics.checkNotNull(findPreference2, "null cannot be cast to non-null type android.preference.EditTextPreference");
        etDebtor = (EditTextPreference) findPreference2;
    }
    private void clearFormField() {
        try {
            final Preference findPreference = this.findPreference("etTutar");
            Intrinsics.checkNotNull(findPreference, "null cannot be cast to non-null type android.preference.EditTextPreference");
            ((EditTextPreference) findPreference).setText("");
            final Preference findPreference2 = this.findPreference("dpVadeTarihi");
            Intrinsics.checkNotNull(findPreference2, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.LogoDatePreference");
            ((LogoDatePreference) findPreference2).setDate(DateAndTimeUtils.nowDate());
            final Preference findPreference3 = this.findPreference("lpOzelKodu");
            Intrinsics.checkNotNull(findPreference3, "null cannot be cast to non-null type android.preference.ListPreference");
            ((ListPreference) findPreference3).setValue("");
            final Preference findPreference4 = this.findPreference("etBankaAdi");
            Intrinsics.checkNotNull(findPreference4, "null cannot be cast to non-null type android.preference.EditTextPreference");
            ((EditTextPreference) findPreference4).setText("");
            final Preference findPreference5 = this.findPreference("etSube");
            Intrinsics.checkNotNull(findPreference5, "null cannot be cast to non-null type android.preference.EditTextPreference");
            ((EditTextPreference) findPreference5).setText("");
            final Preference findPreference6 = this.findPreference("etSeriNo");
            Intrinsics.checkNotNull(findPreference6, "null cannot be cast to non-null type android.preference.EditTextPreference");
            ((EditTextPreference) findPreference6).setText("");
            final Preference findPreference7 = this.findPreference("etHesap");
            Intrinsics.checkNotNull(findPreference7, "null cannot be cast to non-null type android.preference.EditTextPreference");
            ((EditTextPreference) findPreference7).setText("");
            final Preference findPreference8 = this.findPreference("etKefil");
            Intrinsics.checkNotNull(findPreference8, "null cannot be cast to non-null type android.preference.EditTextPreference");
            ((EditTextPreference) findPreference8).setText("");
            final Preference findPreference9 = this.findPreference("etOdemeYeri");
            Intrinsics.checkNotNull(findPreference9, "null cannot be cast to non-null type android.preference.EditTextPreference");
            ((EditTextPreference) findPreference9).setText("");
            final Preference findPreference10 = this.findPreference("etPul");
            Intrinsics.checkNotNull(findPreference10, "null cannot be cast to non-null type android.preference.EditTextPreference");
            ((EditTextPreference) findPreference10).setText("");
        } catch (final Exception unused) {
        }
    }
    protected void onResume() {
        super.onResume();
        this.getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
    protected void onPause() {
        super.onPause();
        this.getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String str) {
        Preferences.updatePrefSummary(this.findPreference(str));
    }
    public boolean onKeyDown(final int i2, final KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (4 == i2) {
            this.setResult(-1);
            this.clearEditor();
            this.finish();
            return true;
        }
        return super.onKeyDown(i2, event);
    }
    public void clearEditor() {
        final SharedPreferences sharedPreferences = f1228sp;
        Intrinsics.checkNotNull(sharedPreferences);
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove("etTutar");
        edit.remove("dpVadeTarihi");
        edit.remove("lpOzelKodu");
        edit.remove("etSeriNo");
        edit.remove("etBorclu");
        edit.remove("etBankaAdi");
        edit.remove("etSube");
        edit.remove("etHesap");
        edit.remove("etKefil");
        edit.remove("etOdemeYeri");
        edit.remove("etPul");
        edit.apply();
    }
    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        menu.add(0, 0, 0, this.getString(R.string.str_add)).setIcon(R.drawable.ic_menu_add);
        menu.add(0, 2, 0, this.getString(R.string.str_detail)).setIcon(R.drawable.ic_menu_recent_history);
        menu.add(0, 1, 0, this.getString(R.string.str_save)).setIcon(R.drawable.ic_menu_save);
        menu.add(0, 3, 0, this.getString(R.string.str_reports)).setIcon(R.drawable.ic_menu_directions);
        menu.add(0, 4, 0, this.getString(R.string.str_cancel)).setIcon(R.drawable.ic_menu_close_clear_cancel);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemId = item.getItemId();
        if (0 == itemId) {
            this.controlFicheDue();
        } else if (1 != itemId) {
            if (2 == itemId) {
                this.setResult(-1);
                this.finish();
            } else if (3 == itemId) {
                this.startActivityForResult(new Intent(this, ReportAllActivity.class), 0);
            } else if (4 == itemId) {
                this.cancelFiche();
            } else if (16908332 == itemId) {
                this.setResult(-1);
                this.clearEditor();
                this.finish();
                return true;
            }
        } else {
            if (!viewModel.getBaseErp().getLogoSqlHelper().cekSenetDetayiVarmi()) {
                Toast.makeText(this, this.getString(R.string.str_collection_detail_not_added), Toast.LENGTH_SHORT).show();
                return true;
            }
            this.setResult(0);
            final BaseErp<?> baseErp = viewModel.getBaseErp();
            final int i2 = MainActivity.sFicheRef;
            final ReceiptType receiptType = mReceiptType;
            Intrinsics.checkNotNull(receiptType);
            baseErp.insertFicheBroadcastMessage(i2, receiptType.mfType);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void cancelFiche() {
        new AlertDialog.Builder(this).setMessage(this.getString(R.string.str_question_want_close)).setPositiveButton(this.getString(R.string.str_yes), dialogClickListener).setNegativeButton(this.getString(R.string.str_no), dialogClickListener).show();
    }
    public DialogInterface.OnClickListener getDialogClickListener() {
        return dialogClickListener;
    }
    public void setDialogClickListener(final DialogInterface.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "<set-?>");
        dialogClickListener = onClickListener;
    }
    public static void dialogClickListenerlambda0(final SalesChequeAndDeedDetailEnterActivity this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (-2 == i2) {
            dialogInterface.dismiss();
            return;
        }
        if (-1 != i2) {
            return;
        }
        if (this0.customerOperation.getFicheMode() == FicheMode.NEW) {
            this0.viewModel.getBaseErp().getLogoSqlHelper().cekSenetTahsilatSil();
        }
        this0.setResult(0);
        this0.clearEditor();
        this0.finish();
    }
    private void controlFicheDue() {
        final SharedPreferences sharedPreferences = f1228sp;
        Intrinsics.checkNotNull(sharedPreferences);
        final String string = sharedPreferences.getString("dpVadeTarihi", DateAndTimeUtils.nowDate());
        if (mReceiptType == ReceiptType.CHEQUE) {
            DateAndTimeUtils.getDateInt(string);
        }
        this.addFicheLine();
    }
    public static void dialogClickListenerDuelambda1(final SalesChequeAndDeedDetailEnterActivity this0, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        if (-2 == i2) {
            dialog.dismiss();
        } else {
            if (-1 != i2) {
                return;
            }
            this0.addFicheLine();
        }
    }
    private void addFicheLine() {
        try {
            final SharedPreferences sharedPreferences = f1228sp;
            Intrinsics.checkNotNull(sharedPreferences);
            final String string = sharedPreferences.getString("etTutar", "0");
            Intrinsics.checkNotNull(string);
            final double d2 = StringUtils.toDouble(string);
            if (0.0d >= d2) {
                Toast.makeText(this, this.getString(R.string.str_question_enter_amount), Toast.LENGTH_SHORT).show();
                return;
            }
            final ContentValues contentValues = new ContentValues();
            contentValues.put("TOTAL", Double.valueOf(d2));
            final SharedPreferences sharedPreferences2 = f1228sp;
            Intrinsics.checkNotNull(sharedPreferences2);
            contentValues.put("DUEDATE", sharedPreferences2.getString("dpVadeTarihi", DateAndTimeUtils.nowDate()));
            final SharedPreferences sharedPreferences3 = f1228sp;
            Intrinsics.checkNotNull(sharedPreferences3);
            contentValues.put("SPECODE", sharedPreferences3.getString("lpOzelKodu", ""));
            final SharedPreferences sharedPreferences4 = f1228sp;
            Intrinsics.checkNotNull(sharedPreferences4);
            contentValues.put("BANKNAME", sharedPreferences4.getString("etBankaAdi", ""));
            final SharedPreferences sharedPreferences5 = f1228sp;
            Intrinsics.checkNotNull(sharedPreferences5);
            contentValues.put("BANKBRANCHNAME", sharedPreferences5.getString("etSube", ""));
            final SharedPreferences sharedPreferences6 = f1228sp;
            Intrinsics.checkNotNull(sharedPreferences6);
            contentValues.put("SERIALNO", sharedPreferences6.getString("etSeriNo", ""));
            final SharedPreferences sharedPreferences7 = f1228sp;
            Intrinsics.checkNotNull(sharedPreferences7);
            contentValues.put("DEBITED", sharedPreferences7.getString("etBorclu", ""));
            final SharedPreferences sharedPreferences8 = f1228sp;
            Intrinsics.checkNotNull(sharedPreferences8);
            contentValues.put("ACCNO", sharedPreferences8.getString("etHesap", ""));
            final SharedPreferences sharedPreferences9 = f1228sp;
            Intrinsics.checkNotNull(sharedPreferences9);
            contentValues.put("INCHARGE", sharedPreferences9.getString("etKefil", ""));
            final SharedPreferences sharedPreferences10 = f1228sp;
            Intrinsics.checkNotNull(sharedPreferences10);
            contentValues.put("PAYWHERE", sharedPreferences10.getString("etOdemeYeri", ""));
            final SharedPreferences sharedPreferences11 = f1228sp;
            Intrinsics.checkNotNull(sharedPreferences11);
            contentValues.put("PUL", sharedPreferences11.getString("etPul", ""));
            contentValues.put("BRANCH", Integer.valueOf(branchNr));
            contentValues.put("CHEQUEDEEDID", Integer.valueOf(MainActivity.sFicheRef));
            if (0 >= MainActivity.DetailRef) {
                viewModel.getBaseErp().getLogoSqlBriteDatabase().executeInsert("CHEQUEDEEDDETAIL", contentValues);
            } else {
                viewModel.getBaseErp().getLogoSqlBriteDatabase().execute("CHEQUEDEEDDETAIL", "LOGICALREF=" + MainActivity.DetailRef, null);
            }
            viewModel.getBaseErp().getLogoSqlHelper().cekSenetToplamlariGuncelle();
            MainActivity.DetailRef = -1;
            this.clearEditor();
            this.clearFormField();
            this.setResult(-1);
            this.finish();
        } catch (final Exception e2) {
            alert(String.valueOf(e2.getMessage()));
        }
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
