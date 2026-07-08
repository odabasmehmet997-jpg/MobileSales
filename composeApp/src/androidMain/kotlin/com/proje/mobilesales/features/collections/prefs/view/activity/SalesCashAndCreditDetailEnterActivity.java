package com.proje.mobilesales.features.collections.prefs.view.activity;

import android.R;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.*;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivityPreferences;
import com.proje.mobilesales.core.enums.ColType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.preferences.EditTextPref;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.sql.ISqlBriteDatabase;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.collections.prefs.repository.SalesCashAndCreditDetailEnterRepository;
import com.proje.mobilesales.features.collections.prefs.viewmodel.SalesCashAndCreditDetailEnterViewModel;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.reports.view.activity.ReportAllActivity;
import com.proje.mobilesales.features.settings.interfaces.PreferenceClear;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;

import static com.proje.mobilesales.core.utils.AppUtils.alert;

public final class SalesCashAndCreditDetailEnterActivity extends BaseErpActivityPreferences implements SharedPreferences.OnSharedPreferenceChangeListener, PreferenceClear {
    private static final int CANCEL = 3;
    private static final int DETAIL = 1;
    private static final int REPORT = 2;
    private static final int SAVE = 0;
    private DialogInterface.OnClickListener dialogClickListener;
    private EditTextPreference etAmount;
    private EditTextPreference etCreditCardNo;
    private Object etCurr;
    private ListPreference lpAccount;
    private ListPreference lpBank;
    private ListPreference lpPayments;
    private boolean mNetsis;
    private ReceiptType mReceiptType;
    private final SalesCashAndCreditDetailEnterRepository repository;
    private SharedPreferences f1226sp;
    private final SalesCashAndCreditDetailEnterViewModel viewModel;
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_AMOUNT = "EXTRA_AMOUNT." + SalesCashAndCreditDetailEnterActivity.class.getName();
    public SalesCashAndCreditDetailEnterActivity() {
        final SalesCashAndCreditDetailEnterRepository salesCashAndCreditDetailEnterRepository = new SalesCashAndCreditDetailEnterRepository();
        repository = salesCashAndCreditDetailEnterRepository;
        viewModel = new SalesCashAndCreditDetailEnterViewModel(salesCashAndCreditDetailEnterRepository);
        dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                dialogClickListenerlambda2(SalesCashAndCreditDetailEnterActivity.this, dialogInterface, i2);
            }
        };
    }
    public SalesCashAndCreditDetailEnterRepository getRepository() {
        return repository;
    }
    public SalesCashAndCreditDetailEnterViewModel getViewModel() {
        return viewModel;
    }
    public SharedPreferences getSp() {
        return f1226sp;
    }
    public void setSp(final SharedPreferences sharedPreferences) {
        f1226sp = sharedPreferences;
    }
    public ReceiptType getMReceiptType() {
        return mReceiptType;
    }
    public void setMReceiptType(final ReceiptType receiptType) {
        mReceiptType = receiptType;
    }
    public boolean getMNetsis() {
        return mNetsis;
    }
    public void setMNetsis(final boolean z) {
        mNetsis = z;
    }
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.addPreferencesFromResource(R.xml.ccddetailenter);
        f1226sp = PreferenceManager.getDefaultSharedPreferences(this);
        this.getExtras();
        if ((customerOperation.getFicheMode() == FicheMode.NEW || customerOperation.getFicheMode() == FicheMode.COPY) && !viewModel.getBaseErp().checkRouteVisitOutOfOrder(this, customerRef, 0, 0)) {
            Toast.makeText(this, this.getString(R.string.str_comply_before_route), Toast.LENGTH_LONG).show();
            onBackPressed();
        }
        mNetsis = viewModel.getBaseErp().getErpType() == ErpType.NETSIS;
        mReceiptType = customerOperation.getReceiptType();
        this.createControls();
        this.loadOtherInformation();
        if (null == bundle) {
            final EditTextPreference editTextPreference = etAmount;
            Intrinsics.checkNotNull(editTextPreference);
            final Bundle extras = this.getIntent().getExtras();
            Intrinsics.checkNotNull(extras);
            editTextPreference.setText(StringUtils.convertDoubleToString(Double.valueOf(extras.getDouble(SalesCashAndCreditDetailEnterActivity.EXTRA_AMOUNT))));
            Preferences.initSummary(etAmount);
        }
        if (mReceiptType == ReceiptType.CREDIT) {
            this.loadBanks();
            Preferences.initSummary(lpBank);
            this.loadBankAccounts("");
            try {
                final SharedPreferences sharedPreferences = f1226sp;
                this.loadBankAccounts(null != sharedPreferences ? sharedPreferences.getString("lpBanka", "0") : null);
            } catch (final Exception unused) {
            }
        }
        this.getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
                final boolean onCreatelambda0;
                onCreatelambda0 = onCreatelambda0(SalesCashAndCreditDetailEnterActivity.this, adapterView, view, i2, j2);
                return onCreatelambda0;
            }
        });
        Preferences.initSummary(lpPayments);
        Preferences.initSummary(lpBank);
        Preferences.initSummary(lpAccount);
        if (mReceiptType == ReceiptType.CASH) {
            final Preference findPreference = this.findPreference("pgBanka");
            Intrinsics.checkNotNull(findPreference, "null cannot be cast to non-null type android.preference.PreferenceCategory");
            this.getPreferenceScreen().removePreference(findPreference);
            final Preference findPreference2 = this.findPreference("pgccdTutar");
            Intrinsics.checkNotNull(findPreference2, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference2).removePreference(lpPayments);
            final Preference findPreference3 = this.findPreference("pgccdTutar");
            Intrinsics.checkNotNull(findPreference3, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference3).removePreference(etCreditCardNo);
            this.setTitle(this.getString(R.string.str_cash_entry));
        } else {
            this.setTitle(this.getString(R.string.str_credit_cart_entry));
        }
        if (0 >= MainActivity.sFicheRef) {
            this.openFirstRecord();
        }
    }
    public static boolean onCreatelambda0(final SalesCashAndCreditDetailEnterActivity this0, final AdapterView parent, final View view, final int i2, final long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(parent, "parent");
        final Object item = ((ListView) parent).getAdapter().getItem(i2);
        this0.etCurr = item;
        if (null == item || !(item instanceof View.OnLongClickListener)) {
            return false;
        }
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type android.view.View.OnLongClickListener");
        return ((View.OnLongClickListener) item).onLongClick(view);
    }
    private void openFirstRecord() {
        if (mReceiptType == ReceiptType.CASH) {
            final ISqlBriteDatabase logoSqlBriteDatabase = viewModel.getBaseErp().getLogoSqlBriteDatabase();
            final StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO CASHCREDIT (");
            sb.append(mNetsis ? "CLCODE" : "CLREF");
            sb.append(",ISTRANSFER,FTYPE,PRINTCOUNT,DATEINT,GDATE,ENLEM,BOYLAM,BRANCHNR,DIVISNR,SPECODE,PROJECTREF,CYPHCODE,TRADINGGRP,ANDFICHENO) VALUES (");
            sb.append(customerRef);
            sb.append(",0,0,0,");
            sb.append(DateAndTimeUtils.getDateInt(DateAndTimeUtils.nowDate()));
            sb.append(",'");
            sb.append(DateAndTimeUtils.nowDateTime());
            sb.append("','");
            sb.append(ContextUtils.getLatitude());
            sb.append("','");
            sb.append(ContextUtils.getLongitude());
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("100"));
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("101"));
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("102"));
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("103"));
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("104"));
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("105"));
            sb.append("','");
            final ErpType erpType = viewModel.getBaseErp().getErpType();
            Intrinsics.checkNotNullExpressionValue(erpType, "getErpType(...)");
            sb.append(StringUtils.getCreateAndFicheNo(erpType, 3));
            sb.append("')");
            logoSqlBriteDatabase.execute(sb.toString());
        } else {
            final ISqlBriteDatabase logoSqlBriteDatabase2 = viewModel.getBaseErp().getLogoSqlBriteDatabase();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("INSERT INTO CASHCREDIT (");
            sb2.append(mNetsis ? "CLCODE" : "CLREF");
            sb2.append(",ISTRANSFER,FTYPE,PRINTCOUNT,DATEINT,GDATE,ENLEM,BOYLAM,BRANCHNR,DIVISNR,SPECODE,PROJECTREF,CYPHCODE,TRADINGGRP,ANDFICHENO) VALUES (");
            sb2.append(customerRef);
            sb2.append(",0,1,0,");
            sb2.append(DateAndTimeUtils.getDateInt(DateAndTimeUtils.nowDate()));
            sb2.append(",'");
            sb2.append(DateAndTimeUtils.nowDateTime());
            sb2.append("','");
            sb2.append(ContextUtils.getLatitude());
            sb2.append("','");
            sb2.append(ContextUtils.getLongitude());
            sb2.append("','");
            sb2.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("113"));
            sb2.append("','");
            sb2.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("114"));
            sb2.append("','");
            sb2.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("115"));
            sb2.append("','");
            sb2.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("116"));
            sb2.append("','");
            sb2.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("117"));
            sb2.append("','");
            sb2.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("118"));
            sb2.append("','");
            final ErpType erpType2 = viewModel.getBaseErp().getErpType();
            Intrinsics.checkNotNullExpressionValue(erpType2, "getErpType(...)");
            sb2.append(StringUtils.getCreateAndFicheNo(erpType2, 3));
            sb2.append("')");
            logoSqlBriteDatabase2.execute(sb2.toString());
        }
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
    public void onDestroy() {
        super.onDestroy();
    }
    private void createControls() {
        final Preference findPreference = this.findPreference("lpOdemeler");
        Intrinsics.checkNotNull(findPreference, "null cannot be cast to non-null type android.preference.ListPreference");
        lpPayments = (ListPreference) findPreference;
        final Preference findPreference2 = this.findPreference("etKrediKartNo");
        Intrinsics.checkNotNull(findPreference2, "null cannot be cast to non-null type android.preference.EditTextPreference");
        etCreditCardNo = (EditTextPreference) findPreference2;
        final Preference findPreference3 = this.findPreference("lpBanka");
        Intrinsics.checkNotNull(findPreference3, "null cannot be cast to non-null type android.preference.ListPreference");
        lpBank = (ListPreference) findPreference3;
        final Preference findPreference4 = this.findPreference("lpHesap");
        Intrinsics.checkNotNull(findPreference4, "null cannot be cast to non-null type android.preference.ListPreference");
        lpAccount = (ListPreference) findPreference4;
        final Preference findPreference5 = this.findPreference("etTutar");
        Intrinsics.checkNotNull(findPreference5, "null cannot be cast to non-null type android.preference.EditTextPreference");
        etAmount = (EditTextPreference) findPreference5;
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
    private void loadOtherInformation() {
        if (mReceiptType == ReceiptType.CREDIT) {
            if (mNetsis) {
                viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT CODE,ODEMEPLAN FROM PAYMENT", lpPayments, "ODEMEPLAN", "CODE", ColType.metin, Boolean.TRUE);
            } else {
                viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT LOGICALREF,ODEMEPLAN FROM PAYMENT", lpPayments, "ODEMEPLAN", "LOGICALREF", ColType.sayi, Boolean.TRUE);
            }
        }
    }
    private void loadBanks() {
        final ListPreference listPreference = lpBank;
        Intrinsics.checkNotNull(listPreference);
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.proje.mobilesales.features.collections.prefs.view.activity.SalesCashAndCreditDetailEnterActivityExternalSyntheticLambda0
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(final Preference preference, final Object obj) {
                final boolean loadBankslambda1;
                loadBankslambda1 = loadBankslambda1(SalesCashAndCreditDetailEnterActivity.this, preference, obj);
                return loadBankslambda1;
            }
        });
        if (!mNetsis) {
            final ISqlHelper logoSqlHelper = viewModel.getBaseErp().getLogoSqlHelper();
            final ListPreference listPreference2 = lpBank;
            final ColType colType = ColType.sayi;
            final Boolean bool = Boolean.FALSE;
            logoSqlHelper.loadPrefData("SELECT LOGICALREF,DEFINITION_ FROM BANKS ", listPreference2, "DEFINITION_", "LOGICALREF", colType, bool, bool, Boolean.TRUE);
        } else {
            final ISqlHelper logoSqlHelper2 = viewModel.getBaseErp().getLogoSqlHelper();
            final ListPreference listPreference3 = lpBank;
            final ColType colType2 = ColType.metin;
            final Boolean bool2 = Boolean.FALSE;
            logoSqlHelper2.loadPrefData("SELECT CODE,DEFINITION_ FROM BANKS ", listPreference3, "DEFINITION_", "CODE", colType2, bool2, bool2, Boolean.TRUE);
        }
        Preferences.initSummary(lpBank);
    }
    public static boolean loadBankslambda1(final SalesCashAndCreditDetailEnterActivity this0, final Preference preference, final Object newValue) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(newValue, "newValue");
        this0.loadBankAccounts(newValue.toString());
        return true;
    }
    private void loadBankAccounts(final String str) {
        if (!mNetsis) {
            viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT LOGICALREF,DEFINITION_ FROM BANKACCOUNTS WHERE BANKREF=" + StringUtils.convertStringToInt(str), lpAccount, "DEFINITION_", "LOGICALREF", ColType.sayi, Boolean.FALSE);
        } else {
            viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT CODE,DEFINITION_ FROM BANKACCOUNTS WHERE BANKCODE='" + str + '\'', lpAccount, "DEFINITION_", "CODE", ColType.metin, Boolean.FALSE);
        }
        Preferences.initSummary(lpAccount);
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
        menu.add(0, 1, 0, this.getString(R.string.str_detail)).setIcon(R.drawable.ic_menu_recent_history);
        menu.add(0, 2, 0, this.getString(R.string.str_reports)).setIcon(R.drawable.ic_menu_directions);
        menu.add(0, 3, 0, this.getString(R.string.str_close)).setIcon(R.drawable.ic_menu_close_clear_cancel);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemId = item.getItemId();
        if (0 == itemId) {
            this.addFicheLine();
        } else if (1 == itemId) {
            if (customerOperation.getFicheMode() != FicheMode.NEW) {
                customerOperation.setFicheMode(FicheMode.EDIT);
            }
            final Intent intent = new Intent(this, SalesCashAndCreditHeaderEnterActivity.class);
            intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, customerOperation);
            intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, customerRef);
            this.startActivityForResult(intent, 1);
        } else if (2 == itemId) {
            this.startActivityForResult(new Intent(this, ReportAllActivity.class), 0);
        } else if (3 == itemId) {
            this.cancelFiche();
        } else if (16908332 == itemId) {
            this.cancelFiche();
            return true;
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
    public static void dialogClickListenerlambda2(final SalesCashAndCreditDetailEnterActivity this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (-1 != i2) {
            return;
        }
        if (this0.customerOperation.getFicheMode() == FicheMode.NEW) {
            this0.viewModel.getBaseErp().getLogoSqlHelper().nakitKrediTahsilatSil();
        }
        this0.setResult(0);
        this0.clearEditor();
        this0.finish();
    }
    private void addFicheLine() {
        try {
            final SharedPreferences sharedPreferences = f1226sp;
            Intrinsics.checkNotNull(sharedPreferences);
            final String string = sharedPreferences.getString("etTutar", "0");
            Intrinsics.checkNotNull(string);
            final double d2 = StringUtils.toDouble(string);
            if (0.0d >= d2) {
                Toast.makeText(this, this.getString(R.string.str_question_enter_amount), Toast.LENGTH_SHORT).show();
                return;
            }
            final ReceiptType receiptType = mReceiptType;
            final ReceiptType receiptType2 = ReceiptType.CREDIT;
            if (receiptType == receiptType2) {
                final SharedPreferences sharedPreferences2 = f1226sp;
                Intrinsics.checkNotNull(sharedPreferences2);
                if (TextUtils.isEmpty(sharedPreferences2.getString("lpBanka", ""))) {
                    Toast.makeText(this, this.getString(R.string.str_question_select_bank), Toast.LENGTH_SHORT).show();
                    return;
                }
                final SharedPreferences sharedPreferences3 = f1226sp;
                Intrinsics.checkNotNull(sharedPreferences3);
                if (TextUtils.isEmpty(sharedPreferences3.getString("lpHesap", ""))) {
                    Toast.makeText(this, this.getString(R.string.str_question_select_bank), Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            final ContentValues contentValues = new ContentValues();
            contentValues.put("TOTAL", Double.valueOf(d2));
            final SharedPreferences sharedPreferences4 = f1226sp;
            Intrinsics.checkNotNull(sharedPreferences4);
            contentValues.put("DOCNO", sharedPreferences4.getString("etMakbuzNo", ""));
            if (mNetsis) {
                final SharedPreferences sharedPreferences5 = f1226sp;
                Intrinsics.checkNotNull(sharedPreferences5);
                contentValues.put("PAYMENTCODE", sharedPreferences5.getString("lpOdemeler", "0"));
            } else {
                final SharedPreferences sharedPreferences6 = f1226sp;
                Intrinsics.checkNotNull(sharedPreferences6);
                contentValues.put("PAYMENTREF", Integer.valueOf(StringUtils.convertStringToInt(sharedPreferences6.getString("lpOdemeler", "0"))));
            }
            final SharedPreferences sharedPreferences7 = f1226sp;
            Intrinsics.checkNotNull(sharedPreferences7);
            contentValues.put("CREDICARDNO", sharedPreferences7.getString("etKrediKartNo", ""));
            contentValues.put("CASHCREDITID", Integer.valueOf(MainActivity.sFicheRef));
            viewModel.getBaseErp().getLogoSqlBriteDatabase().executeInsert("CASHCREDITDETAIL", contentValues);
            if (mReceiptType != receiptType2 || this.updateBank()) {
                viewModel.getBaseErp().getLogoSqlHelper().nakitKrediKartiToplamlariGuncelle();
                this.setResult(-1);
                this.clearEditor();
                final Intent intent = new Intent(this, SalesCashAndCreditHeaderEnterActivity.class);
                intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, customerOperation);
                intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, customerRef);
                this.startActivityForResult(intent, 1);
            }
        } catch (final Exception e2) {
            alert(e2.getMessage());
        }
    }
    private boolean updateBank() {
        try {
            final ContentValues contentValues = new ContentValues();
            if (mNetsis) {
                final SharedPreferences sharedPreferences = f1226sp;
                Intrinsics.checkNotNull(sharedPreferences);
                contentValues.put("BANKCODE", sharedPreferences.getString("lpBanka", "0"));
                final SharedPreferences sharedPreferences2 = f1226sp;
                Intrinsics.checkNotNull(sharedPreferences2);
                contentValues.put("BANKACCCODE", sharedPreferences2.getString("lpHesap", "0"));
            } else {
                final SharedPreferences sharedPreferences3 = f1226sp;
                Intrinsics.checkNotNull(sharedPreferences3);
                contentValues.put("BANKREF", sharedPreferences3.getString("lpBanka", "0"));
                final SharedPreferences sharedPreferences4 = f1226sp;
                Intrinsics.checkNotNull(sharedPreferences4);
                contentValues.put("BANKACCREF", sharedPreferences4.getString("lpHesap", "0"));
            }
            viewModel.getBaseErp().getLogoSqlBriteDatabase().update("CASHCREDIT", contentValues, "LOGICALREF=?", StringUtils.convertIntToString(MainActivity.sFicheRef));
            return true;
        } catch (final Exception e2) {
            alert(String.valueOf(e2.getMessage()));
            return false;
        }
    }
    protected void onActivityResult(final int i2, final int i3, final Intent data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (1234 != i2 || -1 != i3) {
            if (1 == i2 && -1 == i3) {
                this.finish();
                return;
            }
            return;
        }
        final ArrayList<String> stringArrayListExtra = data.getStringArrayListExtra("android.speech.extra.RESULTS");
        try {
            final EditTextPref editTextPref = (EditTextPref) etCurr;
            Intrinsics.checkNotNull(editTextPref);
            Intrinsics.checkNotNull(stringArrayListExtra);
            editTextPref.setText(stringArrayListExtra.get(0));
            editTextPref.setSummary(stringArrayListExtra.get(0));
            Preferences.initSummary(editTextPref);
        } catch (final Exception unused) {
        }
    }
    public void clearEditor() {
        final SharedPreferences sharedPreferences = f1226sp;
        Intrinsics.checkNotNull(sharedPreferences);
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove("etTutar");
        edit.remove("etMakbuzNo");
        edit.remove("lpOdemeler");
        edit.remove("etKrediKartNo");
        edit.remove("lpBanka");
        edit.remove("lpHesap");
        edit.apply();
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getEXTRA_AMOUNT() {
            return EXTRA_AMOUNT;
        }
    }
}
