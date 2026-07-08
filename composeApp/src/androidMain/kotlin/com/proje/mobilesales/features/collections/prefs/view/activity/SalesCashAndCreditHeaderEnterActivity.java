package com.proje.mobilesales.features.collections.prefs.view.activity;

import android.R;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseErpActivityPreferences;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.ColType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.interfaces.di.ActivityComponent;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.preferences.EditTextPref;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.utils.AppUtils;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.collections.prefs.repository.SalesCashAndCreditHeaderEnterRepository;
import com.proje.mobilesales.features.collections.prefs.viewmodel.SalesCashAndCreditHeaderEnterViewModel;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.reports.view.activity.ReportAllActivity;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.settings.interfaces.MatterCheck;
import com.proje.mobilesales.features.settings.interfaces.PreferenceClear;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

import java.util.ArrayList;

import static com.proje.mobilesales.core.utils.AppUtils.alert;


public final class SalesCashAndCreditHeaderEnterActivity extends BaseErpActivityPreferences implements SharedPreferences.OnSharedPreferenceChangeListener, PreferenceClear, MatterCheck {
    private static final int CANCEL = 4;
    public static final Companion Companion = new Companion(null);
    private static final int REPORT = 3;
    private static final int SAVE = 2;
    private Object etCurr;
    private EditTextPref etExplanation1;
    private EditTextPref etExplanation2;
    private EditTextPref etExplanation3;
    private EditTextPref etExplanation4;
    private ListPreference lpAuthCode;
    private ListPreference lpContractCode;
    private ListPreference lpDivide;
    private ListPreference lpProjectCode;
    private ListPreference lpSpeCode;
    private ListPreference lpTradingOperationGroup;
    private ListPreference lpWorkplace;
    private MatterSettings mMatterSettings;
    private boolean mNetsis;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private ReceiptType mReceiptType;
    private Preference pEnterAmount;
    private EditTextPref pInstallmentCount;
    private final SalesCashAndCreditHeaderEnterRepository repository;
    private SharedPreferences f1227sp;
    private final SalesCashAndCreditHeaderEnterViewModel viewModel;
    public static boolean getOtherInformationlambda3(final Preference preference) {
        return true;
    }
    public SalesCashAndCreditHeaderEnterActivity() {
        final SalesCashAndCreditHeaderEnterRepository salesCashAndCreditHeaderEnterRepository = new SalesCashAndCreditHeaderEnterRepository();
        repository = salesCashAndCreditHeaderEnterRepository;
        viewModel = new SalesCashAndCreditHeaderEnterViewModel(salesCashAndCreditHeaderEnterRepository);
    }
    public SalesCashAndCreditHeaderEnterRepository getRepository() {
        return repository;
    }
    public SalesCashAndCreditHeaderEnterViewModel getViewModel() {
        return viewModel;
    }
    public SharedPreferences getSp() {
        return f1227sp;
    }
    public void setSp(final SharedPreferences sharedPreferences) {
        f1227sp = sharedPreferences;
    }
    public ReceiptType getMReceiptType() {
        return mReceiptType;
    }
    public void setMReceiptType(final ReceiptType receiptType) {
        mReceiptType = receiptType;
    }
    public MatterSettings getMMatterSettings() {
        return mMatterSettings;
    }
    public void setMMatterSettings(final MatterSettings matterSettings) {
        mMatterSettings = matterSettings;
    }
    public boolean getMNetsis() {
        return mNetsis;
    }
    public void setMNetsis(final boolean z) {
        mNetsis = z;
    }
    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        return mProgressDialogBuilder;
    }
    public void setMProgressDialogBuilder(final ProgressDialogBuilder<?> progressDialogBuilder) {
        mProgressDialogBuilder = progressDialogBuilder;
    } 
    protected void onCreate(final Bundle bundle) {
        final ReceiptType fromReceiptType;
        final ReceiptType receiptType;
        super.onCreate(bundle);
        final ActivityComponent activityComponent = this.getActivityComponent();
        Intrinsics.checkNotNull(activityComponent);
        activityComponent.inject(this);
        final Context context = ContextUtils.getmContext();
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context, (BaseInjectableActivity) activity);
        Log.d("MNTTAG", "ACTIVITY : " + SalesCashAndCreditHeaderEnterActivity.class.getName());
        this.addPreferencesFromResource(R.xml.ccdheaderenter);
        mNetsis = viewModel.getBaseErp().getErpType() == ErpType.NETSIS;
        f1227sp = PreferenceManager.getDefaultSharedPreferences(this);
        this.getExtras();
        final CustomerOperation customerOperation = this.customerOperation;
        if (null != customerOperation) {
            fromReceiptType = customerOperation.getReceiptType();
        } else {
            fromReceiptType = ReceiptType.Companion.fromReceiptType(MainActivity.fType.getValue());
        }
        mReceiptType = fromReceiptType;
        if (null == fromReceiptType) {
            final Cursor query = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT * FROM CASHCREDIT WHERE LOGICALREF=" + MainActivity.sFicheRef);
            if (query.moveToFirst()) {
                if (Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "FTYPE", ColType.sayi).toString(), BuildConfig.NETSIS_DEMO_PASSWORD)) {
                    receiptType = ReceiptType.CREDIT;
                } else {
                    receiptType = ReceiptType.CASH;
                }
                mReceiptType = receiptType;
            }
        }
        this.DefaultLoads();
        this.getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {  
            public boolean onItemLongClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
                final boolean onCreatelambda0;
                onCreatelambda0 = onCreatelambda0(SalesCashAndCreditHeaderEnterActivity.this, adapterView, view, i2, j2);
                return onCreatelambda0;
            }
        });
        if (mReceiptType == ReceiptType.CASH) {
            this.setTitle(this.getString(R.string.str_cash_collection));
        } else {
            this.setTitle(this.getString(R.string.str_credit_card_slip));
        }
        mMatterSettings = viewModel.getBaseErp().getMatterSettings(this, this.customerOperation.getFicheType());
        try {
            this.setVisibility();
        } catch (final NullPointerException e2) {
            e2.printStackTrace();
        }
    }
    public static boolean onCreatelambda0(final SalesCashAndCreditHeaderEnterActivity this0, final AdapterView adapterView, final View view, final int i2, final long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNull(adapterView, "null cannot be cast to non-null type android.widget.ListView");
        final Object item = ((ListView) adapterView).getAdapter().getItem(i2);
        this0.etCurr = item;
        if (!(item instanceof View.OnLongClickListener)) {
            return false;
        }
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type android.view.View.OnLongClickListener");
        return ((View.OnLongClickListener) item).onLongClick(view);
    }
    private void setVisibility() {
        final String obj;
        int i2 = 1;
        if (mReceiptType == ReceiptType.CASH) {
            final String upVal = viewModel.getBaseErp().getLogoSqlHelper().upVal("93");
            Intrinsics.checkNotNullExpressionValue(upVal, "upVal(...)");
            int length = upVal.length() - 1;
            int i3 = 0;
            boolean z = false;
            while (i3 <= length) {
                final boolean z2 = 0 >= Intrinsics.compare(upVal.charAt(!z ? i3 : length), 32);
                if (z) {
                    if (!z2) {
                        break;
                    } else {
                        length--;
                    }
                } else if (z2) {
                    i3++;
                } else {
                    z = true;
                }
            }
            obj = upVal.subSequence(i3, length + 1).toString();
        } else {
            final String upVal2 = viewModel.getBaseErp().getLogoSqlHelper().upVal("106");
            Intrinsics.checkNotNullExpressionValue(upVal2, "upVal(...)");
            int length2 = upVal2.length() - 1;
            int i4 = 0;
            boolean z3 = false;
            while (i4 <= length2) {
                final boolean z4 = 0 >= Intrinsics.compare(upVal2.charAt(!z3 ? i4 : length2), 32);
                if (z3) {
                    if (!z4) {
                        break;
                    } else {
                        length2--;
                    }
                } else if (z4) {
                    i4++;
                } else {
                    z3 = true;
                }
            }
            obj = upVal2.subSequence(i4, length2 + 1).toString();
        }
        final boolean z5 = mNetsis;
        if (!z5 || (z5 && mReceiptType == ReceiptType.CASH)) {
            final Preference findPreference = this.findPreference("pgCsNetsisZorunluBilgiler");
            Intrinsics.checkNotNull(findPreference, "null cannot be cast to non-null type android.preference.PreferenceCategory");
            final Preference findPreference2 = this.findPreference("psCddHeader");
            Intrinsics.checkNotNull(findPreference2, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference2).removePreference(findPreference);
        }
        if (TextUtils.isEmpty(obj)) {
            return;
        }
        if (StringsKt.contains ( obj,  "0", false)) {
            i2 = 0;
        } else {
            final Preference findPreference3 = this.findPreference("pgCsDigerBilgiler");
            Intrinsics.checkNotNull(findPreference3, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference3).removePreference(lpWorkplace);
        }
        if (!StringsKt.contains (obj,  BuildConfig.NETSIS_DEMO_PASSWORD, false)) {
            final Preference findPreference4 = this.findPreference("pgCsDigerBilgiler");
            Intrinsics.checkNotNull(findPreference4, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference4).removePreference(lpDivide);
            i2++;
        }
        if (!StringsKt.contains(obj, ExifInterface.GPS_MEASUREMENT_2D, false)) {
            final Preference findPreference5 = this.findPreference("pgCsDigerBilgiler");
            Intrinsics.checkNotNull(findPreference5, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference5).removePreference(lpSpeCode);
            i2++;
        }
        if (!StringsKt.contains(obj, ExifInterface.GPS_MEASUREMENT_3D, false)) {
            final Preference findPreference6 = this.findPreference("pgCsDigerBilgiler");
            Intrinsics.checkNotNull(findPreference6, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference6).removePreference(lpProjectCode);
            i2++;
        }
        if (4 == i2) {
            final Preference findPreference7 = this.findPreference("pgCsDigerBilgiler");
            Intrinsics.checkNotNull(findPreference7, "null cannot be cast to non-null type android.preference.PreferenceCategory");
            final Preference findPreference8 = this.findPreference("psCddHeader");
            Intrinsics.checkNotNull(findPreference8, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference8).removePreference(findPreference7);
        }
        if (!StringsKt.contains(obj, "4", false)) {
            final Preference findPreference9 = this.findPreference("pgCddGenelBilgiler");
            Intrinsics.checkNotNull(findPreference9, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference9).removePreference(lpAuthCode);
        }
        if (StringsKt.contains(obj, "5", false)) {
            return;
        }
        final Preference findPreference10 = this.findPreference("pgCddGenelBilgiler");
        Intrinsics.checkNotNull(findPreference10, "null cannot be cast to non-null type android.preference.PreferenceGroup");
        ((PreferenceGroup) findPreference10).removePreference(lpTradingOperationGroup);
    }
    private void setProSpecBranchDivision() {
        final String str;
        final Cursor cursor;
        final ReceiptType receiptType = mReceiptType;
        if (receiptType == ReceiptType.CASH) {
            if (!Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("102"), "")) {
                final ListPreference listPreference = lpSpeCode;
                Intrinsics.checkNotNull(listPreference);
                if (Intrinsics.areEqual(listPreference.getSummary(), "")) {
                    final ListPreference listPreference2 = lpSpeCode;
                    Intrinsics.checkNotNull(listPreference2);
                    listPreference2.setValue(viewModel.getBaseErp().getLogoSqlHelper().upVal("102"));
                    final ListPreference listPreference3 = lpSpeCode;
                    Intrinsics.checkNotNull(listPreference3);
                    listPreference3.setSummary(viewModel.getBaseErp().getLogoSqlHelper().upVal("102"));
                    final Cursor query = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select  specode FROM SPECODES WHERE  CODETYPE=1 AND Specodetype=44");
                    if (query.moveToFirst()) {
                        int i2 = 0;
                        while (true) {
                            if (Intrinsics.areEqual(query.getString(0), viewModel.getBaseErp().getLogoSqlHelper().upVal("198_2"))) {
                                final ListPreference listPreference4 = lpSpeCode;
                                Intrinsics.checkNotNull(listPreference4);
                                listPreference4.setValueIndex(i2 + 1);
                                final ListPreference listPreference5 = lpSpeCode;
                                Intrinsics.checkNotNull(listPreference5);
                                listPreference5.setSummary(query.getString(0));
                                Log.d("MNTTAG", "lpOzelKodu se\u00e7ili index :" + i2);
                                break;
                            }
                            i2++;
                            if (!query.moveToNext()) {
                                break;
                            }
                        }
                    }
                }
            }
        } else if (receiptType == ReceiptType.CREDIT && !Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("115"), "")) {
            final ListPreference listPreference6 = lpSpeCode;
            Intrinsics.checkNotNull(listPreference6);
            if (Intrinsics.areEqual(listPreference6.getValue(), "")) {
                final ListPreference listPreference7 = lpSpeCode;
                Intrinsics.checkNotNull(listPreference7);
                listPreference7.setValue(viewModel.getBaseErp().getLogoSqlHelper().upVal("115"));
                final ListPreference listPreference8 = lpSpeCode;
                Intrinsics.checkNotNull(listPreference8);
                listPreference8.setSummary(viewModel.getBaseErp().getLogoSqlHelper().upVal("115"));
                final Cursor query2 = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select  specode FROM SPECODES WHERE  CODETYPE=1 AND Specodetype=44");
                if (query2.moveToFirst()) {
                    int i3 = 0;
                    while (true) {
                        if (Intrinsics.areEqual(query2.getString(0), viewModel.getBaseErp().getLogoSqlHelper().upVal("202_2"))) {
                            final ListPreference listPreference9 = lpSpeCode;
                            Intrinsics.checkNotNull(listPreference9);
                            listPreference9.setValueIndex(i3 + 1);
                            final ListPreference listPreference10 = lpSpeCode;
                            Intrinsics.checkNotNull(listPreference10);
                            listPreference10.setSummary(query2.getString(0));
                            break;
                        }
                        i3++;
                        if (!query2.moveToNext()) {
                            break;
                        }
                    }
                }
            }
        }
        final ListPreference listPreference11 = lpProjectCode;
        Intrinsics.checkNotNull(listPreference11);
        String str2 = null;
        if (Intrinsics.areEqual(listPreference11.getValue(), "")) {
            if (mReceiptType == ReceiptType.CASH && !Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("103"), "")) {
                cursor = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select PROJE,LogicalRef FROM PROJECT WHERE LOGICALREF='" + baseErp.getLogoSqlHelper().upVal("103") + '\'');
            } else if (mReceiptType != ReceiptType.CREDIT || Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("116"), "")) {
                cursor = null;
            } else {
                cursor = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select PROJE,LogicalRef FROM PROJECT WHERE LOGICALREF='" + viewModel.getBaseErp().getLogoSqlHelper().upVal("116") + '\'');
            }
            if (null != cursor && cursor.moveToFirst()) {
                final Cursor query3 = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select LOGICALREF FROM PROJECT");
                if (query3.moveToFirst()) {
                    int i4 = 0;
                    while (true) {
                        if (Intrinsics.areEqual(query3.getString(0), cursor.getString(1))) {
                            final ListPreference listPreference12 = lpProjectCode;
                            Intrinsics.checkNotNull(listPreference12);
                            listPreference12.setValueIndex(i4 + 1);
                            final ListPreference listPreference13 = lpProjectCode;
                            Intrinsics.checkNotNull(listPreference13);
                            listPreference13.setSummary(cursor.getString(0));
                            break;
                        }
                        i4++;
                        if (!query3.moveToNext()) {
                            break;
                        }
                    }
                }
            }
        }
        final ListPreference listPreference14 = lpWorkplace;
        Intrinsics.checkNotNull(listPreference14);
        if (Intrinsics.areEqual(listPreference14.getValue(), "")) {
            if (mReceiptType == ReceiptType.CASH && !Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("100"), "")) {
                str = viewModel.getBaseErp().getLogoSqlHelper().upVal("100");
                Intrinsics.checkNotNullExpressionValue(str, "upVal(...)");
            } else if (mReceiptType != ReceiptType.CREDIT || Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("113"), "")) {
                str = "";
            } else {
                str = viewModel.getBaseErp().getLogoSqlHelper().upVal("113");
                Intrinsics.checkNotNullExpressionValue(str, "upVal(...)");
            }
            final Cursor query4 = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select NR,ISYERI FROM BRANCH");
            if (query4.moveToFirst() && !Intrinsics.areEqual(str, "")) {
                int i5 = 0;
                while (true) {
                    if (Intrinsics.areEqual(query4.getString(0), str)) {
                        final ListPreference listPreference15 = lpWorkplace;
                        Intrinsics.checkNotNull(listPreference15);
                        listPreference15.setValueIndex(i5);
                        final ListPreference listPreference16 = lpWorkplace;
                        Intrinsics.checkNotNull(listPreference16);
                        listPreference16.setSummary(query4.getString(1));
                        break;
                    }
                    i5++;
                    if (!query4.moveToNext()) {
                        break;
                    }
                }
            }
        }
        final ListPreference listPreference17 = lpDivide;
        Intrinsics.checkNotNull(listPreference17);
        if (Intrinsics.areEqual(listPreference17.getValue(), "")) {
            if (mReceiptType == ReceiptType.CASH && !Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("101"), "")) {
                str2 = viewModel.getBaseErp().getLogoSqlHelper().upVal("101");
            } else if (mReceiptType == ReceiptType.CREDIT && !Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("114"), "")) {
                str2 = viewModel.getBaseErp().getLogoSqlHelper().upVal("114");
            }
            final Cursor query5 = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select NR,BOLUM FROM DIVISION");
            if (!query5.moveToFirst() || Intrinsics.areEqual(str2, "")) {
                return;
            }
            int i6 = 0;
            while (!Intrinsics.areEqual(query5.getString(0), str2)) {
                i6++;
                if (!query5.moveToNext()) {
                    return;
                }
            }
            final ListPreference listPreference18 = lpDivide;
            Intrinsics.checkNotNull(listPreference18);
            listPreference18.setValueIndex(i6);
            final ListPreference listPreference19 = lpDivide;
            Intrinsics.checkNotNull(listPreference19);
            listPreference19.setSummary(query5.getString(1));
        }
    }
    public void onDestroy() {
        super.onDestroy();
    }
    private void loadProjects() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT * FROM PROJECT", lpProjectCode, "PROJE", "LOGICALREF", ColType.sayi, Boolean.TRUE);
    }
    private void loadContracts() {
        final ISqlHelper logoSqlHelper = viewModel.getBaseErp().getLogoSqlHelper();
        final Boolean bool = Boolean.TRUE;
        String sb = "SELECT * FROM CREDITAGGR WHERE (BEGDATE <= '" +
                DateAndTimeUtils.getSqlDate(bool) +
                "' AND ENDDATE >= '" +
                DateAndTimeUtils.getSqlDate(bool) +
                "') ORDER BY CODE ASC ";
        logoSqlHelper.loadPrefData(sb, lpContractCode, "AGGREEMENT", "CODE", ColType.metin, Boolean.FALSE);
    }
    private void DefaultLoads() {
        this.createControls();
        this.loadDivisions();
        this.loadWorkplaces();
        this.loadTradingOperationGroup();
        this.loadSpecialCodes();
        this.loadProjects();
        if (mNetsis && mReceiptType != ReceiptType.CASH) {
            this.loadContracts();
        }
        this.loadAuthCodes();
        this.getOtherInformation();
        this.getFicheInformation();
        final Preference preference = pEnterAmount;
        Intrinsics.checkNotNull(preference);
        preference.setSummary(this.getString(R.string.str_total_quantity) + viewModel.getBaseErp().getLogoSqlHelper().nakitKrediKartiToplaminiGetir());
        Preferences.initSummary(lpWorkplace);
        Preferences.initSummary(lpDivide);
        Preferences.initSummary(lpSpeCode);
        Preferences.initSummary(lpProjectCode);
        if (mNetsis && mReceiptType != ReceiptType.CASH) {
            Preferences.initSummary(lpContractCode);
            Preferences.initSummary(pInstallmentCount);
        }
        Preferences.initSummary(lpAuthCode);
        Preferences.initSummary(lpTradingOperationGroup);
    }
    private void getFicheInformation() {
        final Cursor query = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT * FROM CASHCREDIT WHERE LOGICALREF=" + MainActivity.sFicheRef);
        if (query.moveToPosition(0)) {
            final ListPreference listPreference = lpTradingOperationGroup;
            Intrinsics.checkNotNull(listPreference);
            final ISqlHelper logoSqlHelper = viewModel.getBaseErp().getLogoSqlHelper();
            final ColType colType = ColType.metin;
            listPreference.setValue(logoSqlHelper.dbVal(query, "TRADINGGRP", colType).toString());
            final ListPreference listPreference2 = lpSpeCode;
            Intrinsics.checkNotNull(listPreference2);
            listPreference2.setValue(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "SPECODE", colType).toString());
            final ListPreference listPreference3 = lpProjectCode;
            Intrinsics.checkNotNull(listPreference3);
            listPreference3.setValue(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "PROJECTREF", colType).toString());
            final ListPreference listPreference4 = lpAuthCode;
            Intrinsics.checkNotNull(listPreference4);
            listPreference4.setValue(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "CYPHCODE", colType).toString());
            if (mNetsis) {
                final ISqlHelper logoSqlHelper2 = viewModel.getBaseErp().getLogoSqlHelper();
                final ColType colType2 = ColType.sayi;
                customerCode = logoSqlHelper2.dbVal(query, "CLCODE", colType2).toString();
                if (mReceiptType != ReceiptType.CASH) {
                    final ListPreference listPreference5 = lpContractCode;
                    Intrinsics.checkNotNull(listPreference5);
                    listPreference5.setValue(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "AGGRCODE", colType).toString());
                    final EditTextPref editTextPref = pInstallmentCount;
                    Intrinsics.checkNotNull(editTextPref);
                    editTextPref.setText(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "INSTALLMENTCOUNT", colType2).toString());
                }
            } else {
                customerRef = StringUtils.convertStringToInt(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "CLREF", ColType.sayi).toString());
            }
            final EditTextPref editTextPref2 = etExplanation1;
            Intrinsics.checkNotNull(editTextPref2);
            editTextPref2.setText(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "DESC1", colType).toString());
            final EditTextPref editTextPref3 = etExplanation2;
            Intrinsics.checkNotNull(editTextPref3);
            editTextPref3.setText(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "DESC2", colType).toString());
            final EditTextPref editTextPref4 = etExplanation3;
            Intrinsics.checkNotNull(editTextPref4);
            editTextPref4.setText(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "DESC3", colType).toString());
            final EditTextPref editTextPref5 = etExplanation4;
            Intrinsics.checkNotNull(editTextPref5);
            editTextPref5.setText(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "DESC4", colType).toString());
            final ListPreference listPreference6 = lpDivide;
            Intrinsics.checkNotNull(listPreference6);
            listPreference6.setValue(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "DIVISNR", colType).toString());
            final ListPreference listPreference7 = lpWorkplace;
            Intrinsics.checkNotNull(listPreference7);
            listPreference7.setValue(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "BRANCHNR", colType).toString());
        }
        query.close();
        Preferences.initSummary(lpWorkplace);
        Preferences.initSummary(lpDivide);
        Preferences.initSummary(lpTradingOperationGroup);
        Preferences.initSummary(lpSpeCode);
        Preferences.initSummary(lpProjectCode);
        if (mNetsis && mReceiptType != ReceiptType.CASH) {
            Preferences.initSummary(lpContractCode);
            Preferences.initSummary(pInstallmentCount);
        }
        Preferences.initSummary(lpAuthCode);
        Preferences.initSummary(etExplanation1);
        Preferences.initSummary(etExplanation2);
        Preferences.initSummary(etExplanation3);
        Preferences.initSummary(etExplanation4);
    }
    protected void onActivityResult(final int i2, final int i3, final Intent data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (1234 == i2 && -1 == i3) {
            final ArrayList<String> stringArrayListExtra = data.getStringArrayListExtra("android.speech.extra.RESULTS");
            try {
                final EditTextPref editTextPref = (EditTextPref) etCurr;
                Intrinsics.checkNotNull(editTextPref);
                Intrinsics.checkNotNull(stringArrayListExtra);
                editTextPref.setText(stringArrayListExtra.get(0));
                editTextPref.setSummary(stringArrayListExtra.get(0));
                Preferences.initSummary(editTextPref);
                return;
            } catch (final Exception unused) {
                return;
            }
        }
        final Preference preference = pEnterAmount;
        Intrinsics.checkNotNull(preference);
        preference.setSummary(this.getString(R.string.str_total_quantity) + viewModel.getBaseErp().getLogoSqlHelper().nakitKrediKartiToplaminiGetir());
    }
    private void createControls() {
        pEnterAmount = this.findPreference("pTutarGirisi");
        final Preference findPreference = this.findPreference("lpTicariIslemGrubu");
        Intrinsics.checkNotNull(findPreference, "null cannot be cast to non-null type android.preference.ListPreference");
        lpTradingOperationGroup = (ListPreference) findPreference;
        final Preference findPreference2 = this.findPreference("lpOzelKodu");
        Intrinsics.checkNotNull(findPreference2, "null cannot be cast to non-null type android.preference.ListPreference");
        lpSpeCode = (ListPreference) findPreference2;
        final Preference findPreference3 = this.findPreference("lpProjeKodu");
        Intrinsics.checkNotNull(findPreference3, "null cannot be cast to non-null type android.preference.ListPreference");
        lpProjectCode = (ListPreference) findPreference3;
        if (mNetsis && mReceiptType != ReceiptType.CASH) {
            final Preference findPreference4 = this.findPreference("lpSozKodu");
            Intrinsics.checkNotNull(findPreference4, "null cannot be cast to non-null type android.preference.ListPreference");
            lpContractCode = (ListPreference) findPreference4;
            final Preference findPreference5 = this.findPreference("pTaksitSayisi");
            Intrinsics.checkNotNull(findPreference5, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.EditTextPref");
            pInstallmentCount = (EditTextPref) findPreference5;
        }
        final Preference findPreference6 = this.findPreference("lpYetkiKodu");
        Intrinsics.checkNotNull(findPreference6, "null cannot be cast to non-null type android.preference.ListPreference");
        lpAuthCode = (ListPreference) findPreference6;
        final Preference findPreference7 = this.findPreference("etExplanation1");
        Intrinsics.checkNotNull(findPreference7, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.EditTextPref");
        etExplanation1 = (EditTextPref) findPreference7;
        final Preference findPreference8 = this.findPreference("etExplanation2");
        Intrinsics.checkNotNull(findPreference8, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.EditTextPref");
        etExplanation2 = (EditTextPref) findPreference8;
        final Preference findPreference9 = this.findPreference("etExplanation3");
        Intrinsics.checkNotNull(findPreference9, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.EditTextPref");
        etExplanation3 = (EditTextPref) findPreference9;
        final Preference findPreference10 = this.findPreference("etExplanation4");
        Intrinsics.checkNotNull(findPreference10, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.EditTextPref");
        etExplanation4 = (EditTextPref) findPreference10;
        final Preference findPreference11 = this.findPreference("lpIsyeri");
        Intrinsics.checkNotNull(findPreference11, "null cannot be cast to non-null type android.preference.ListPreference");
        lpWorkplace = (ListPreference) findPreference11;
        final Preference findPreference12 = this.findPreference("lpBolum");
        Intrinsics.checkNotNull(findPreference12, "null cannot be cast to non-null type android.preference.ListPreference");
        lpDivide = (ListPreference) findPreference12;
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
    private void getOtherInformation() {
        final Preference preference = pEnterAmount;
        if (null == preference) {
            return;
        }
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.proje.mobilesales.features.collections.prefs.view.activity.SalesCashAndCreditHeaderEnterActivityExternalSyntheticLambda0
            @Override // android.preference.Preference.OnPreferenceClickListener
            public boolean onPreferenceClick(final Preference preference2) {
                final boolean otherInformationlambda3;
                otherInformationlambda3 = getOtherInformationlambda3(preference2);
                return otherInformationlambda3;
            }
        });
    }
    private void loadDivisions() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT * FROM DIVISION", lpDivide, "BOLUM", "NR", ColType.metin, Boolean.FALSE);
    }
    private void loadWorkplaces() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT * FROM BRANCH", lpWorkplace, "ISYERI", "NR", ColType.metin, Boolean.FALSE);
        final ListPreference listPreference = lpWorkplace;
        Intrinsics.checkNotNull(listPreference);
        listPreference.setValue("0");
    }
    private void loadTradingOperationGroup() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT * FROM TRADINGGRP", lpTradingOperationGroup, "CODE", "CODE", ColType.metin, Boolean.TRUE);
    }
    private void loadSpecialCodes() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT SPECODE FROM SPECODES WHERE CODETYPE=1 AND SPECODETYPE=44", lpSpeCode, "SPECODE", "SPECODE", ColType.metin, Boolean.TRUE);
    }
    private void loadAuthCodes() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT SPECODE FROM SPECODES WHERE CODETYPE=2 AND SPECODETYPE=44", lpAuthCode, "SPECODE", "SPECODE", ColType.metin, Boolean.TRUE);
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
        menu.add(0, 2, 0, ContextUtils.getStringResource(R.string.str_save)).setIcon(R.drawable.ic_menu_save);
        menu.add(0, 3, 0, ContextUtils.getStringResource(R.string.str_reports)).setIcon(R.drawable.ic_menu_directions);
        menu.add(0, 4, 0, ContextUtils.getStringResource(R.string.str_cancel)).setIcon(R.drawable.ic_menu_close_clear_cancel);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemId = item.getItemId();
        if (2 == itemId) {
            if (mNetsis && mReceiptType != ReceiptType.CASH) {
                final SharedPreferences sharedPreferences = f1227sp;
                Intrinsics.checkNotNull(sharedPreferences);
                final String string = sharedPreferences.getString("pTaksitSayisi", "0");
                final SharedPreferences sharedPreferences2 = f1227sp;
                Intrinsics.checkNotNull(sharedPreferences2);
                final String string2 = sharedPreferences2.getString("lpSozKodu", "");
                Intrinsics.checkNotNull(string);
                if (0 < string.length()) {
                    Intrinsics.checkNotNull(string2);
                    if (0 == string2.length()) {
                        Toast.makeText(this, this.getString(R.string.str_select_contract_code), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                if (!viewModel.getBaseErp().getLogoSqlHelper().sozlesmeTaksitSayisiniKontrolEt(string2, string)) {
                    Toast.makeText(this, this.getString(R.string.str_incorrect_installment_number), Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
            if (!viewModel.getBaseErp().getLogoSqlHelper().nakitKrediKartiDetayiVarmi()) {
                Toast.makeText(this, this.getString(R.string.str_collection_detail_not_added), Toast.LENGTH_SHORT).show();
                return true;
            }
            final MatterSettings matterSettings = mMatterSettings;
            Intrinsics.checkNotNull(matterSettings);
            if (matterSettings.isUseMatterNo()) {
                this.checkMatter();
            } else {
                this.saveComplete("");
            }
        } else if (3 == itemId) {
            this.startActivityForResult(new Intent(this, ReportAllActivity.class), 0);
        } else if (4 == itemId) {
            this.setResult(0);
            this.clearEditor();
            this.finish();
        } else if (16908332 == itemId) {
            this.setResult(0);
            this.clearEditor();
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    static boolean saveFichedefault(final SalesCashAndCreditHeaderEnterActivity salesCashAndCreditHeaderEnterActivity, String str, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            str = "";
        }
        return salesCashAndCreditHeaderEnterActivity.saveFiche(str);
    }
    private boolean saveFiche(final String str) {
        try {
            final ContentValues contentValues = new ContentValues();
            contentValues.put("ENLEM", Double.valueOf(ContextUtils.getLatitude()));
            contentValues.put("BOYLAM", Double.valueOf(ContextUtils.getLongitude()));
            final ListPreference listPreference = lpDivide;
            Intrinsics.checkNotNull(listPreference);
            contentValues.put("DIVISNR", listPreference.getValue());
            final ListPreference listPreference2 = lpWorkplace;
            Intrinsics.checkNotNull(listPreference2);
            contentValues.put("BRANCHNR", listPreference2.getValue());
            if (mNetsis) {
                contentValues.put("CLCODE", viewModel.getBaseErp().getLogoSqlHelper().getClCode(customerRef));
                final SharedPreferences sharedPreferences = f1227sp;
                Intrinsics.checkNotNull(sharedPreferences);
                contentValues.put("AGGRCODE", sharedPreferences.getString("lpSozKodu", ""));
                final SharedPreferences sharedPreferences2 = f1227sp;
                Intrinsics.checkNotNull(sharedPreferences2);
                contentValues.put("INSTALLMENTCOUNT", sharedPreferences2.getString("pTaksitSayisi", "0"));
            } else {
                contentValues.put("CLREF", Integer.valueOf(customerRef));
            }
            final SharedPreferences sharedPreferences3 = f1227sp;
            Intrinsics.checkNotNull(sharedPreferences3);
            contentValues.put("TRADINGGRP", sharedPreferences3.getString("lpTicariIslemGrubu", ""));
            final ListPreference listPreference3 = lpSpeCode;
            Intrinsics.checkNotNull(listPreference3);
            contentValues.put("SPECODE", listPreference3.getValue());
            final SharedPreferences sharedPreferences4 = f1227sp;
            Intrinsics.checkNotNull(sharedPreferences4);
            contentValues.put("PROJECTREF", sharedPreferences4.getString("lpProjeKodu", ""));
            final SharedPreferences sharedPreferences5 = f1227sp;
            Intrinsics.checkNotNull(sharedPreferences5);
            contentValues.put("CYPHCODE", sharedPreferences5.getString("lpYetkiKodu", ""));
            final SharedPreferences sharedPreferences6 = f1227sp;
            Intrinsics.checkNotNull(sharedPreferences6);
            contentValues.put("DESC1", sharedPreferences6.getString("etExplanation1", ""));
            final SharedPreferences sharedPreferences7 = f1227sp;
            Intrinsics.checkNotNull(sharedPreferences7);
            contentValues.put("DESC2", sharedPreferences7.getString("etExplanation2", ""));
            final SharedPreferences sharedPreferences8 = f1227sp;
            Intrinsics.checkNotNull(sharedPreferences8);
            contentValues.put("DESC3", sharedPreferences8.getString("etExplanation3", ""));
            final SharedPreferences sharedPreferences9 = f1227sp;
            Intrinsics.checkNotNull(sharedPreferences9);
            contentValues.put("DESC4", sharedPreferences9.getString("etExplanation4", ""));
            contentValues.put("FICHENO", str);
            if (0 >= MainActivity.sFicheRef) {
                contentValues.put("DATEINT", Integer.valueOf(DateAndTimeUtils.getDateInt(DateAndTimeUtils.nowDate())));
                viewModel.getBaseErp().getLogoSqlBriteDatabase().executeInsert("CASHCREDIT", contentValues);
                return true;
            }
            viewModel.getBaseErp().getLogoSqlBriteDatabase().update("CASHCREDIT", contentValues, "LOGICALREF=" + MainActivity.sFicheRef);
            return true;
        } catch (final Exception e2) {
            alert(String.valueOf(e2.getMessage()));
            return false;
        }
    }
    public void clearEditor() {
        final SharedPreferences sharedPreferences = f1227sp;
        Intrinsics.checkNotNull(sharedPreferences);
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove("lpIsyeri");
        edit.remove("lpBolum");
        edit.remove("pTutarGirisi");
        if (mNetsis) {
            edit.remove("lpSozKodu");
            edit.remove("pTaksitSayisi");
        }
        edit.remove("lpTicariIslemGrubu");
        edit.remove("lpOzelKodu");
        edit.remove("lpYetkiKodu");
        edit.remove("etExplanation1");
        edit.remove("etExplanation2");
        edit.remove("etExplanation3");
        edit.remove("etExplanation4");
        edit.apply();
    }
    public void checkMatter() {
        final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.setMessage(this.getString(R.string.str_please_wait_get_matter_no)).show();
        viewModel.getBaseErp().getMaxMatterNo(customerOperation.getFicheType(), mMatterSettings, new MatterCheck.MatterCheckListener(this));
    }
    public void onMatterCheck(final String str) {
        final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this, this.getString(R.string.exp_45_undefined), Toast.LENGTH_LONG).show();
            SalesCashAndCreditHeaderEnterActivity.saveCompletedefault(this, null, 1, null);
        } else if (this.maxMatterNoControl(str)) {
            this.saveComplete(str);
        } else {
            this.setNewMaxMatterNo(str);
        }
    }
    public void onMatterError(final String str) {
        final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
    private boolean maxMatterNoControl(final String str) {
        final MatterSettings matterSettings = mMatterSettings;
        Intrinsics.checkNotNull(matterSettings);
        return AppUtils.maxMatterNoControl(str, matterSettings.getLastMatterNo());
    }
    public void setNewMaxMatterNo(String str) {
        ContextUtils.showMatterDialog(this, this.getString(R.string.str_matter_input_last_value_title), mMatterSettings, new ResponseListener<Object>() {
            public void onResponse(final PrintSlipModel obj) {
                final String valueOf = String.valueOf(obj);
                if (TextUtils.isEmpty(valueOf)) {
                    setNewMaxMatterNo(str);
                    return;
                }
                getViewModel().getBaseErp().setNewMatter(getApplicationContext(), customerOperation.getFicheType(), valueOf);
                final MatterSettings mMatterSettings = getMMatterSettings();
                Intrinsics.checkNotNull(mMatterSettings);
                mMatterSettings.setLastMatterNo(valueOf);
                checkMatter();
            }
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                cancelFiche();
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onResponse(Boolean aBoolean) {

            }

            @Override
            public void onResponse(Sales sales) {

            }

            @Override
            public void onResponse(TigerServiceResult tigerServiceResult) {

            }

            @Override
            public void onResponse(Object obj) {

            }

            @Override
            public void onResponse(ArrayList<Object> obj) {

            }

            @Override
            public void onResponse() {

            }
        });
    }
    public void cancelFiche() {
        this.setResult(0);
        this.clearEditor();
        this.finish();
    }
    static void saveCompletedefault(final SalesCashAndCreditHeaderEnterActivity salesCashAndCreditHeaderEnterActivity, String str, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            str = "";
        }
        salesCashAndCreditHeaderEnterActivity.saveComplete(str);
    }
    private void saveComplete(final String str) {
        if (this.saveFiche(str)) {
            this.clearEditor();
            final BaseErp<?> baseErp = viewModel.getBaseErp();
            final int i2 = MainActivity.sFicheRef;
            final ReceiptType receiptType = mReceiptType;
            Intrinsics.checkNotNull(receiptType);
            baseErp.insertFicheBroadcastMessage(i2, receiptType.mfType);
            this.setResult(-1);
            this.finish();
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
