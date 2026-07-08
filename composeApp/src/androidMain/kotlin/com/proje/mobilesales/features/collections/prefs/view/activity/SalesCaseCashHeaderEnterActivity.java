package com.proje.mobilesales.features.collections.prefs.view.activity;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.preference.*;
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
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.interfaces.di.ActivityComponent;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.preferences.EditTextPref;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.sql.ISqlBriteDatabase;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.collections.prefs.repository.SalesCaseCashHeaderEnterRepository;
import com.proje.mobilesales.features.collections.prefs.viewmodel.SalesCaseCashHeaderEnterViewModel;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.reports.view.activity.ReportAllActivity;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesMan;
import com.proje.mobilesales.features.sales.view.mans.SalesMansListActivity;
import com.proje.mobilesales.features.settings.interfaces.MatterCheck;
import com.proje.mobilesales.features.settings.interfaces.PreferenceClear;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

import java.util.ArrayList;

import static com.proje.mobilesales.core.utils.AppUtils.alert;

public final class SalesCaseCashHeaderEnterActivity extends BaseErpActivityPreferences implements SharedPreferences.OnSharedPreferenceChangeListener, PreferenceClear, MatterCheck {
    private static final int CANCEL = 2;
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_AMOUNT = "EXTRA_AMOUNT." + SalesCaseCashHeaderEnterActivity.class.getName();
    private static final int REPORT = 1;
    private static final int SAVE = 0;
    private DialogInterface.OnClickListener dialogClickListener;
    private EditTextPreference etAmount;
    private Object etCurr;
    private EditTextPref etDescription;
    private EditTextPreference etReceiptNo;
    private ListPreference lpAuthCode;
    private ListPreference lpCase;
    private ListPreference lpDivide;
    private ListPreference lpProjectCode;
    private Preference lpSalesManCode;
    private ListPreference lpSpecialCode;
    private ListPreference lpTradingOperationGroup;
    private ListPreference lpWorkplace;
    private MatterSettings mMatterSettings;
    private boolean mNetsis;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private ReceiptType mReceiptType;
    private final SalesCaseCashHeaderEnterRepository repository;
    private int salesManRef;
    private SharedPreferences f1225sp;
    private final SalesCaseCashHeaderEnterViewModel viewModel;
    public SalesCaseCashHeaderEnterActivity() {
        final SalesCaseCashHeaderEnterRepository salesCaseCashHeaderEnterRepository = new SalesCaseCashHeaderEnterRepository();
        repository = salesCaseCashHeaderEnterRepository;
        viewModel = new SalesCaseCashHeaderEnterViewModel(salesCaseCashHeaderEnterRepository);
        dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                dialogClickListenerlambda3(SalesCaseCashHeaderEnterActivity.this, dialogInterface, i2);
            }
        };
    }
    public SalesCaseCashHeaderEnterRepository getRepository() {
        return repository;
    }
    public SalesCaseCashHeaderEnterViewModel getViewModel() {
        return viewModel;
    }
    public SharedPreferences getSp() {
        return f1225sp;
    }
    public void setSp(final SharedPreferences sharedPreferences) {
        f1225sp = sharedPreferences;
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
        super.onCreate(bundle);
        final ActivityComponent activityComponent = this.getActivityComponent();
        Intrinsics.checkNotNull(activityComponent);
        activityComponent.inject(this);
        this.addPreferencesFromResource(R.xml.cscheaderenter);
        this.getExtras();
        final Context context = ContextUtils.getmContext();
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context, (BaseInjectableActivity) activity);
        if (!viewModel.getBaseErp().checkRouteVisitOutOfOrder(this, customerRef, 0, 0)) {
            Toast.makeText(this, this.getString(R.string.str_comply_before_route), Toast.LENGTH_LONG).show();
            onBackPressed();
        }
        mNetsis = viewModel.getBaseErp().getErpType() == ErpType.NETSIS;
        final CustomerOperation customerOperation = this.customerOperation;
        if (null != customerOperation) {
            fromReceiptType = customerOperation.getReceiptType();
        } else {
            fromReceiptType = ReceiptType.Companion.fromReceiptType(MainActivity.fType.getValue());
        }
        mReceiptType = fromReceiptType;
        f1225sp = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        mMatterSettings = viewModel.getBaseErp().getMatterSettings(this, this.customerOperation.getFicheType());
        this.DefaultLoads();
        if (null != getIntent().getExtras() && this.customerOperation.getFicheMode() == FicheMode.NEW) {
            final EditTextPreference editTextPreference = etAmount;
            Intrinsics.checkNotNull(editTextPreference);
            final Bundle extras = this.getIntent().getExtras();
            Intrinsics.checkNotNull(extras);
            editTextPreference.setText(StringUtils.convertDoubleToString(Double.valueOf(extras.getDouble(SalesCaseCashHeaderEnterActivity.EXTRA_AMOUNT))));
            Preferences.initSummary(etAmount);
        }
        try {
            this.setVisibility();
        } catch (final NullPointerException unused) {
        }
    }
    private void setVisibility() {
        final String upVal = viewModel.getBaseErp().getLogoSqlHelper().upVal("80");
        Intrinsics.checkNotNullExpressionValue(upVal, "upVal(...)");
        int i2 = 1;
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
        final String obj = upVal.subSequence(i3, length + 1).toString();
        if (TextUtils.isEmpty(obj)) {
            return;
        }
        if (StringsKt.contains( obj,   "0", false)) {
            i2 = 0;
        } else {
            final Preference findPreference = this.findPreference("pgDigerBilgiler");
            Intrinsics.checkNotNull(findPreference, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference).removePreference(lpWorkplace);
        }
        if (!StringsKt.contains(  obj,  BuildConfig.NETSIS_DEMO_PASSWORD, false)) {
            final Preference findPreference2 = this.findPreference("pgDigerBilgiler");
            Intrinsics.checkNotNull(findPreference2, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference2).removePreference(lpDivide);
            i2++;
        }
        if (!StringsKt.contains( obj, ExifInterface.GPS_MEASUREMENT_2D, false)) {
            final Preference findPreference3 = this.findPreference("pgDigerBilgiler");
            Intrinsics.checkNotNull(findPreference3, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference3).removePreference(lpSpecialCode);
            i2++;
        }
        if (!StringsKt.contains( obj, ExifInterface.GPS_MEASUREMENT_3D, false)) {
            final Preference findPreference4 = this.findPreference("pgDigerBilgiler");
            Intrinsics.checkNotNull(findPreference4, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference4).removePreference(lpProjectCode);
            i2++;
        }
        if (4 == i2) {
            final Preference findPreference5 = this.findPreference("pgDigerBilgiler");
            Intrinsics.checkNotNull(findPreference5, "null cannot be cast to non-null type android.preference.PreferenceCategory");
            final Preference findPreference6 = this.findPreference("psCscHeader");
            Intrinsics.checkNotNull(findPreference6, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference6).removePreference(findPreference5);
        }
        if (!StringsKt.contains (obj, "4", false)) {
            final Preference findPreference7 = this.findPreference("pgCscGenelBilgiler");
            Intrinsics.checkNotNull(findPreference7, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference7).removePreference(lpAuthCode);
        }
        if (!StringsKt.contains (obj, "5", false)) {
            final Preference findPreference8 = this.findPreference("pgCscGenelBilgiler");
            Intrinsics.checkNotNull(findPreference8, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference8).removePreference(lpTradingOperationGroup);
        }
        if (StringsKt.contains (obj, "6", false)) {
            return;
        }
        final Preference findPreference9 = this.findPreference("pgDigerBilgiler");
        Intrinsics.checkNotNull(findPreference9, "null cannot be cast to non-null type android.preference.PreferenceGroup");
        ((PreferenceGroup) findPreference9).removePreference(lpSalesManCode);
    }
    private void DefaultLoads() {
        this.createControls();
        this.loadDivisions();
        this.loadWorkplaces();
        this.loadTradingOperationGroups();
        this.loadSpecialCodes();
        this.loadProjects();
        this.loadAuthCodes();
        this.loadCases();
        this.loadSalesManLists();
        this.getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.proje.mobilesales.features.collections.prefs.view.activity.SalesCaseCashHeaderEnterActivityExternalSyntheticLambda0
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
                final boolean DefaultLoadslambda1;
                DefaultLoadslambda1 = DefaultLoadslambda1(SalesCaseCashHeaderEnterActivity.this, adapterView, view, i2, j2);
                return DefaultLoadslambda1;
            }
        });
        if (0 >= MainActivity.sFicheRef) {
            this.openFirstRecord();
        }
        this.getReceiptInformation();
        Preferences.initSummary(lpWorkplace);
        Preferences.initSummary(lpDivide);
        Preferences.initSummary(lpCase);
        final Cursor query = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT LOGICALREF,KASA FROM USERCASE");
        if (null != query && query.moveToFirst() && 1 == query.getCount()) {
            final ListPreference listPreference = lpCase;
            Intrinsics.checkNotNull(listPreference);
            listPreference.setValueIndex(0);
            final ListPreference listPreference2 = lpCase;
            Intrinsics.checkNotNull(listPreference2);
            listPreference2.setSummary(query.getString(1));
            if (!query.isClosed()) {
                query.close();
            }
        }
        Preferences.initSummary(lpSpecialCode);
        Preferences.initSummary(lpProjectCode);
        Preferences.initSummary(lpAuthCode);
        Preferences.initSummary(etDescription);
        Preferences.initSummary(etAmount);
        Preferences.initSummary(lpTradingOperationGroup);
        Preferences.initSummary(lpSalesManCode);
    }
    public static boolean DefaultLoadslambda1(final SalesCaseCashHeaderEnterActivity this0, final AdapterView adapterView, final View view, final int i2, final long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNull(adapterView, "null cannot be cast to non-null type android.widget.ListView");
        final Object item = ((ListView) adapterView).getAdapter().getItem(i2);
        this0.etCurr = item;
        if (null == item || !(item instanceof View.OnLongClickListener)) {
            return false;
        }
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type android.view.View.OnLongClickListener");
        return ((View.OnLongClickListener) item).onLongClick(view);
    }
    protected void onActivityResult(final int i2, final int i3, final Intent data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (-1 == i3) {
            if (1073 != i2) {
                if (1234 != i2) {
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
                    return;
                } catch (final Exception unused) {
                    return;
                }
            }
            final Bundle extras = data.getExtras();
            Intrinsics.checkNotNull(extras);
            final SalesMan salesMan = (SalesMan) extras.get(IntentExtraName.SELECTED_SALESMAN);
            Intrinsics.checkNotNull(salesMan);
            salesManRef = salesMan.logicalRef;
            final Preference preference = lpSalesManCode;
            Intrinsics.checkNotNull(preference);
            preference.setSummary(salesMan.code + " - " + salesMan.defination);
        }
    }
    private void openFirstRecord() {
        try {
            final ISqlBriteDatabase logoSqlBriteDatabase = viewModel.getBaseErp().getLogoSqlBriteDatabase();
            final StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO CASECASH (");
            sb.append(mNetsis ? "CLCODE" : "CLREF");
            sb.append(",ISTRANSFER,PRINTCOUNT,DATEINT,GDATE,ENLEM,BOYLAM,BRANCHNR,DIVISNR,SPECODE,PROJECTREF,CYPHCODE,TRADINGGRP,");
            sb.append(mNetsis ? "CASECODE" : "CARDREF");
            sb.append(",ANDFICHENO) VALUES (");
            sb.append(customerRef);
            sb.append(",0,0,");
            sb.append(DateAndTimeUtils.getDateInt(DateAndTimeUtils.nowDate()));
            sb.append(",'");
            sb.append(DateAndTimeUtils.nowDateTime());
            sb.append("','");
            sb.append(ContextUtils.getLatitude());
            sb.append("','");
            sb.append(ContextUtils.getLongitude());
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("87"));
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("88"));
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("89"));
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("90"));
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("91"));
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("92"));
            sb.append("',");
            sb.append(StringUtils.convertStringToInt(viewModel.getBaseErp().getLogoSqlHelper().upVal("249")));
            sb.append(",'");
            final ErpType erpType = viewModel.getBaseErp().getErpType();
            Intrinsics.checkNotNullExpressionValue(erpType, "getErpType(...)");
            sb.append(StringUtils.getCreateAndFicheNo(erpType, 2));
            sb.append("')");
            logoSqlBriteDatabase.execute(sb.toString());
            final Cursor query = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT MAX(LOGICALREF) AS LOGICALREF FROM CASECASH");
            if (query.moveToFirst()) {
                do {
                    MainActivity.sFicheRef = query.getInt(0);
                } while (query.moveToNext());
            }
            query.close();
        } catch (final SQLException e2) {
            e2.printStackTrace();
            Toast.makeText(this, R.string.exp_25_database_write_error, Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }
    private void loadProjects() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT * FROM PROJECT", lpProjectCode, "PROJE", "LOGICALREF", ColType.sayi, Boolean.TRUE);
    }
    private void loadSalesManLists() {
        final Preference.OnPreferenceClickListener onPreferenceClickListener = new Preference.OnPreferenceClickListener() { // from class: com.proje.mobilesales.features.collections.prefs.view.activity.SalesCaseCashHeaderEnterActivityExternalSyntheticLambda2
            @Override // android.preference.Preference.OnPreferenceClickListener
            public boolean onPreferenceClick(final Preference preference) {
                final boolean loadSalesManListslambda2;
                loadSalesManListslambda2 = loadSalesManListslambda2(SalesCaseCashHeaderEnterActivity.this, preference);
                return loadSalesManListslambda2;
            }
        };
        final Preference preference = lpSalesManCode;
        Intrinsics.checkNotNull(preference);
        preference.setOnPreferenceClickListener(onPreferenceClickListener);
    }
    public static boolean loadSalesManListslambda2(final SalesCaseCashHeaderEnterActivity this0, final Preference preference) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.startActivityForResult(new Intent(this0, SalesMansListActivity.class), 1073);
        return false;
    }
    private void getReceiptInformation() {
        final Cursor query = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT * FROM CASECASH WHERE LOGICALREF=" + MainActivity.sFicheRef);
        if (query.moveToPosition(0)) {
            final EditTextPreference editTextPreference = etAmount;
            Intrinsics.checkNotNull(editTextPreference);
            final ISqlHelper logoSqlHelper = viewModel.getBaseErp().getLogoSqlHelper();
            final ColType colType = ColType.metin;
            editTextPreference.setText(logoSqlHelper.dbVal(query, "TOTAL", colType).toString());
            final ListPreference listPreference = lpTradingOperationGroup;
            Intrinsics.checkNotNull(listPreference);
            listPreference.setValue(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "TRADINGGRP", colType).toString());
            final ListPreference listPreference2 = lpSpecialCode;
            Intrinsics.checkNotNull(listPreference2);
            listPreference2.setValue(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "SPECODE", colType).toString());
            final ListPreference listPreference3 = lpProjectCode;
            Intrinsics.checkNotNull(listPreference3);
            listPreference3.setValue(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "PROJECTREF", colType).toString());
            final ListPreference listPreference4 = lpAuthCode;
            Intrinsics.checkNotNull(listPreference4);
            listPreference4.setValue(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "CYPHCODE", colType).toString());
            if (mNetsis) {
                customerCode = viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "CLCODE", ColType.sayi).toString();
                final ListPreference listPreference5 = lpCase;
                Intrinsics.checkNotNull(listPreference5);
                listPreference5.setValue(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "CASECODE", colType).toString());
            } else {
                final ISqlHelper logoSqlHelper2 = viewModel.getBaseErp().getLogoSqlHelper();
                final ColType colType2 = ColType.sayi;
                customerRef = StringUtils.convertStringToInt(logoSqlHelper2.dbVal(query, "CLREF", colType2).toString());
                final ListPreference listPreference6 = lpCase;
                Intrinsics.checkNotNull(listPreference6);
                listPreference6.setValue(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "CARDREF", colType2).toString());
            }
            final EditTextPref editTextPref = etDescription;
            Intrinsics.checkNotNull(editTextPref);
            editTextPref.setText(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "DESC", colType).toString());
            final EditTextPreference editTextPreference2 = etReceiptNo;
            Intrinsics.checkNotNull(editTextPreference2);
            editTextPreference2.setText(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "DOCNO", colType).toString());
            final ListPreference listPreference7 = lpDivide;
            Intrinsics.checkNotNull(listPreference7);
            final ISqlHelper logoSqlHelper3 = viewModel.getBaseErp().getLogoSqlHelper();
            final ColType colType3 = ColType.sayi;
            listPreference7.setValue(logoSqlHelper3.dbVal(query, "DIVISNR", colType3).toString());
            final ListPreference listPreference8 = lpWorkplace;
            Intrinsics.checkNotNull(listPreference8);
            listPreference8.setValue(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "BRANCHNR", colType3).toString());
            final Preference preference = lpSalesManCode;
            Intrinsics.checkNotNull(preference);
            preference.setSummary(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "SALESMAN_CODE", colType).toString());
            final Object dbVal = viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "SALESMANREF", colType3);
            Intrinsics.checkNotNull(dbVal, "null cannot be cast to non-null type kotlin.Int");
            salesManRef = ((Integer) dbVal).intValue();
        }
        query.close();
        Preferences.initSummary(lpWorkplace);
        Preferences.initSummary(lpDivide);
        Preferences.initSummary(etAmount);
        Preferences.initSummary(lpTradingOperationGroup);
        Preferences.initSummary(lpSpecialCode);
        Preferences.initSummary(lpProjectCode);
        Preferences.initSummary(lpAuthCode);
        Preferences.initSummary(etDescription);
        Preferences.initSummary(etReceiptNo);
    }
    private void createControls() {
        final Preference findPreference = this.findPreference("lpKasa");
        Intrinsics.checkNotNull(findPreference, "null cannot be cast to non-null type android.preference.ListPreference");
        lpCase = (ListPreference) findPreference;
        final Preference findPreference2 = this.findPreference("etTutar");
        Intrinsics.checkNotNull(findPreference2, "null cannot be cast to non-null type android.preference.EditTextPreference");
        etAmount = (EditTextPreference) findPreference2;
        final Preference findPreference3 = this.findPreference("lpTicariIslemGrubu");
        Intrinsics.checkNotNull(findPreference3, "null cannot be cast to non-null type android.preference.ListPreference");
        lpTradingOperationGroup = (ListPreference) findPreference3;
        final Preference findPreference4 = this.findPreference("lpOzelKodu");
        Intrinsics.checkNotNull(findPreference4, "null cannot be cast to non-null type android.preference.ListPreference");
        lpSpecialCode = (ListPreference) findPreference4;
        final Preference findPreference5 = this.findPreference("lpProjeKodu");
        Intrinsics.checkNotNull(findPreference5, "null cannot be cast to non-null type android.preference.ListPreference");
        lpProjectCode = (ListPreference) findPreference5;
        final Preference findPreference6 = this.findPreference("lpYetkiKodu");
        Intrinsics.checkNotNull(findPreference6, "null cannot be cast to non-null type android.preference.ListPreference");
        lpAuthCode = (ListPreference) findPreference6;
        final Preference findPreference7 = this.findPreference("etAciklama");
        Intrinsics.checkNotNull(findPreference7, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.EditTextPref");
        etDescription = (EditTextPref) findPreference7;
        final Preference findPreference8 = this.findPreference("etMakbuzNo");
        Intrinsics.checkNotNull(findPreference8, "null cannot be cast to non-null type android.preference.EditTextPreference");
        etReceiptNo = (EditTextPreference) findPreference8;
        final Preference findPreference9 = this.findPreference("lpIsyeri");
        Intrinsics.checkNotNull(findPreference9, "null cannot be cast to non-null type android.preference.ListPreference");
        lpWorkplace = (ListPreference) findPreference9;
        final Preference findPreference10 = this.findPreference("lpBolum");
        Intrinsics.checkNotNull(findPreference10, "null cannot be cast to non-null type android.preference.ListPreference");
        lpDivide = (ListPreference) findPreference10;
        final Preference findPreference11 = this.findPreference("lpSalesManCode");
        Intrinsics.checkNotNull(findPreference11, "null cannot be cast to non-null type android.preference.Preference");
        lpSalesManCode = findPreference11;
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
    private void loadDivisions() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT * FROM DIVISION", lpDivide, "BOLUM", "NR", ColType.sayi, false);
        final ListPreference listPreference = lpDivide;
        Intrinsics.checkNotNull(listPreference);
        listPreference.setValue("0");
    }
    private void loadWorkplaces() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT * FROM BRANCH", lpWorkplace, "ISYERI", "NR", ColType.sayi, false);
        final ListPreference listPreference = lpWorkplace;
        Intrinsics.checkNotNull(listPreference);
        listPreference.setValue("0");
    }
    private void loadTradingOperationGroups() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT * FROM TRADINGGRP", lpTradingOperationGroup, "CODE", "CODE", ColType.metin, true);
    }
    private void loadSpecialCodes() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT SPECODE FROM SPECODES WHERE CODETYPE=1 AND SPECODETYPE=44", lpSpecialCode, "SPECODE", "SPECODE", ColType.metin, true);
    }
    private void loadAuthCodes() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT SPECODE FROM SPECODES WHERE CODETYPE=2 AND SPECODETYPE=44", lpAuthCode, "SPECODE", "SPECODE", ColType.metin, true);
    }
    private void loadCases() {
        if (!mNetsis) {
            viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT LOGICALREF,KASA FROM USERCASE", lpCase, "KASA", "LOGICALREF", ColType.sayi, false);
        } else {
            viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT CODE,KASA FROM USERCASE", lpCase, "KASA", "CODE", ColType.metin, false);
        }
    }
    public boolean onKeyDown(final int i2, final KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (KeyEvent.KEYCODE_BACK == i2) {
            this.cancelReceipt();
            return true;
        }
        return super.onKeyDown(i2, event);
    }
    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        menu.add(0, 0, 0, ContextUtils.getStringResource(R.string.str_save)).setIcon(R.drawable.ic_menu_save);
        menu.add(0, 1, 0, ContextUtils.getStringResource(R.string.str_reports)).setIcon(R.drawable.ic_menu_directions);
        menu.add(0, 2, 0, ContextUtils.getStringResource(R.string.str_cancel)).setIcon(R.drawable.ic_menu_close_clear_cancel);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemId = item.getItemId();
        if (0 == itemId) {
            final MatterSettings matterSettings = mMatterSettings;
            Intrinsics.checkNotNull(matterSettings);
            if (matterSettings.isUseMatterNo()) {
                this.checkMatter();
            } else {
                this.saveComplete("");
            }
        } else if (1 == itemId) {
            this.startActivityForResult(new Intent(this, ReportAllActivity.class), 0);
        } else if (2 == itemId) {
            this.cancelReceipt();
        } else if (16908332 == itemId) {
            this.cancelReceipt();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setProSpecBranchDivision() {
        final Cursor cursor;
        if (!Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("200_2"), "")) {
            final ListPreference listPreference = lpSpecialCode;
            Intrinsics.checkNotNull(listPreference);
            listPreference.setValue(viewModel.getBaseErp().getLogoSqlHelper().upVal("200_2"));
            final ListPreference listPreference2 = lpSpecialCode;
            Intrinsics.checkNotNull(listPreference2);
            listPreference2.setSummary(viewModel.getBaseErp().getLogoSqlHelper().upVal("200_2"));
            final Cursor query = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select  specode FROM SPECODES WHERE  CODETYPE=1 AND Specodetype=44");
            if (query.moveToFirst()) {
                int i2 = 0;
                while (true) {
                    if (Intrinsics.areEqual(query.getString(0), viewModel.getBaseErp().getLogoSqlHelper().upVal("200_2"))) {
                        final ListPreference listPreference3 = lpSpecialCode;
                        Intrinsics.checkNotNull(listPreference3);
                        listPreference3.setValueIndex(i2 + 1);
                        final ListPreference listPreference4 = lpSpecialCode;
                        Intrinsics.checkNotNull(listPreference4);
                        listPreference4.setSummary(query.getString(0));
                        Log.d("MNTTAG", "lpOzelKodu se_ili index :" + i2);
                        break;
                    }
                    i2++;
                    if (!query.moveToNext()) {
                        break;
                    }
                }
            }
        }
        if (!Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("200_4"), "")) {
            cursor = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select PROJE,LogicalRef FROM PROJECT WHERE LOGICALREF='" + viewModel.getBaseErp().getLogoSqlHelper().upVal("200_4") + '\'');
        } else if (mReceiptType != ReceiptType.DEED || Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("200_4"), "")) {
            cursor = null;
        } else {
            cursor = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select PROJE,LogicalRef FROM PROJECT WHERE LOGICALREF='" + viewModel.getBaseErp().getLogoSqlHelper().upVal("200_4") + '\'');
        }
        if (null != cursor && cursor.moveToFirst()) {
            final Cursor query2 = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select LOGICALREF FROM PROJECT");
            if (query2.moveToFirst()) {
                int i3 = 0;
                while (true) {
                    if (Intrinsics.areEqual(query2.getString(0), cursor.getString(1))) {
                        final ListPreference listPreference5 = lpProjectCode;
                        Intrinsics.checkNotNull(listPreference5);
                        listPreference5.setValueIndex(i3 + 1);
                        final ListPreference listPreference6 = lpProjectCode;
                        Intrinsics.checkNotNull(listPreference6);
                        listPreference6.setSummary(cursor.getString(0));
                        break;
                    }
                    i3++;
                    if (!query2.moveToNext()) {
                        break;
                    }
                }
            }
        }
        String upVal = viewModel.getBaseErp().getLogoSqlHelper().upVal("200_1");
        final Cursor query3 = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select NR,ISYERI FROM BRANCH");
        if (query3.moveToFirst() && !Intrinsics.areEqual(upVal, "")) {
            int i4 = 0;
            while (true) {
                if (Intrinsics.areEqual(query3.getString(0), upVal)) {
                    final ListPreference listPreference7 = lpWorkplace;
                    Intrinsics.checkNotNull(listPreference7);
                    listPreference7.setValueIndex(i4);
                    final ListPreference listPreference8 = lpWorkplace;
                    Intrinsics.checkNotNull(listPreference8);
                    listPreference8.setSummary(query3.getString(1));
                    break;
                }
                i4++;
                if (!query3.moveToNext()) {
                    break;
                }
            }
        }
        if (!Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("200_3"), "")) {
            upVal = viewModel.getBaseErp().getLogoSqlHelper().upVal("200_3");
        }
        final Cursor query4 = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select NR,BOLUM FROM DIVISION");
        if (!query4.moveToFirst() || Intrinsics.areEqual(upVal, "")) {
            return;
        }
        int i5 = 0;
        while (!Intrinsics.areEqual(query4.getString(0), upVal)) {
            i5++;
            if (!query4.moveToNext()) {
                return;
            }
        }
        final ListPreference listPreference9 = lpDivide;
        Intrinsics.checkNotNull(listPreference9);
        listPreference9.setValueIndex(i5);
        final ListPreference listPreference10 = lpDivide;
        Intrinsics.checkNotNull(listPreference10);
        listPreference10.setSummary(query4.getString(1));
    }
    private void cancelReceipt() {
        new AlertDialog.Builder(this).setMessage(ContextUtils.getStringResource(R.string.str_question_want_close)).setPositiveButton(ContextUtils.getStringResource(R.string.str_yes), dialogClickListener).setNegativeButton(ContextUtils.getStringResource(R.string.str_no), dialogClickListener).show();
    }
    public DialogInterface.OnClickListener getDialogClickListener() {
        return dialogClickListener;
    }
    public void setDialogClickListener(final DialogInterface.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "<set-?>");
        dialogClickListener = onClickListener;
    }
    public static void dialogClickListenerlambda3(final SalesCaseCashHeaderEnterActivity this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (-1 != i2) {
            return;
        }
        this0.cancelFiche();
    }
    private void deleteCollection() {
        viewModel.getBaseErp().getLogoSqlBriteDatabase().execute("DELETE FROM CASECASH WHERE LOGICALREF=" + MainActivity.sFicheRef);
    }
    static boolean ficheSavedefault(final SalesCaseCashHeaderEnterActivity salesCaseCashHeaderEnterActivity, String str, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            str = "";
        }
        return salesCaseCashHeaderEnterActivity.ficheSave(str);
    }
    private boolean ficheSave(final String str) {
        final String obj;
        try {
            final SharedPreferences sharedPreferences = f1225sp;
            Intrinsics.checkNotNull(sharedPreferences);
            final String string = sharedPreferences.getString("etTutar", "0");
            Intrinsics.checkNotNull(string);
            final double d2 = StringUtils.toDouble(string);
            if (0.0d >= d2) {
                Toast.makeText(this, ContextUtils.getStringResource(R.string.str_question_enter_amount), Toast.LENGTH_SHORT).show();
                return false;
            }
            final SharedPreferences sharedPreferences2 = f1225sp;
            Intrinsics.checkNotNull(sharedPreferences2);
            if (TextUtils.isEmpty(sharedPreferences2.getString("lpKasa", ""))) {
                Toast.makeText(this, ContextUtils.getStringResource(R.string.str_question_select_safe), Toast.LENGTH_SHORT).show();
                return false;
            }
            final ContentValues contentValues = new ContentValues();
            contentValues.put("ENLEM", Double.valueOf(ContextUtils.getLatitude()));
            contentValues.put("BOYLAM", Double.valueOf(ContextUtils.getLongitude()));
            final SharedPreferences sharedPreferences3 = f1225sp;
            Intrinsics.checkNotNull(sharedPreferences3);
            contentValues.put("DIVISNR", Integer.valueOf(StringUtils.convertStringToInt(sharedPreferences3.getString("lpBolum", "0"))));
            final SharedPreferences sharedPreferences4 = f1225sp;
            Intrinsics.checkNotNull(sharedPreferences4);
            contentValues.put("BRANCHNR", Integer.valueOf(StringUtils.convertStringToInt(sharedPreferences4.getString("lpIsyeri", "0"))));
            contentValues.put("TOTAL", Double.valueOf(d2));
            if (mNetsis) {
                contentValues.put("CLCODE", viewModel.getBaseErp().getLogoSqlHelper().getClCode(customerRef));
                final SharedPreferences sharedPreferences5 = f1225sp;
                Intrinsics.checkNotNull(sharedPreferences5);
                contentValues.put("CASECODE", sharedPreferences5.getString("lpKasa", ""));
            } else {
                contentValues.put("CLREF", Integer.valueOf(customerRef));
                final SharedPreferences sharedPreferences6 = f1225sp;
                Intrinsics.checkNotNull(sharedPreferences6);
                contentValues.put("CARDREF", Integer.valueOf(StringUtils.convertStringToInt(sharedPreferences6.getString("lpKasa", "0"))));
            }
            final SharedPreferences sharedPreferences7 = f1225sp;
            Intrinsics.checkNotNull(sharedPreferences7);
            contentValues.put("TRADINGGRP", sharedPreferences7.getString("lpTicariIslemGrubu", ""));
            final ListPreference listPreference = lpSpecialCode;
            Intrinsics.checkNotNull(listPreference);
            contentValues.put("SPECODE", listPreference.getValue());
            final SharedPreferences sharedPreferences8 = f1225sp;
            Intrinsics.checkNotNull(sharedPreferences8);
            contentValues.put("PROJECTREF", sharedPreferences8.getString("lpProjeKodu", ""));
            final Preference preference = lpSalesManCode;
            Intrinsics.checkNotNull(preference);
            if (Intrinsics.areEqual(preference.getSummary(), "")) {
                obj = "";
            } else {
                final Preference preference2 = lpSalesManCode;
                Intrinsics.checkNotNull(preference2);
                obj = preference2.getSummary().toString();
            }
            contentValues.put("SALESMAN_CODE", obj);
            contentValues.put("SALESMANREF", Integer.valueOf(salesManRef));
            final SharedPreferences sharedPreferences9 = f1225sp;
            Intrinsics.checkNotNull(sharedPreferences9);
            contentValues.put("CYPHCODE", sharedPreferences9.getString("lpYetkiKodu", ""));
            final SharedPreferences sharedPreferences10 = f1225sp;
            Intrinsics.checkNotNull(sharedPreferences10);
            contentValues.put("DESC", sharedPreferences10.getString("etAciklama", ""));
            final SharedPreferences sharedPreferences11 = f1225sp;
            Intrinsics.checkNotNull(sharedPreferences11);
            contentValues.put("DOCNO", sharedPreferences11.getString("etMakbuzNo", ""));
            contentValues.put("FICHENO", str);
            if (0 >= MainActivity.sFicheRef) {
                contentValues.put("DATEINT", Integer.valueOf(DateAndTimeUtils.getDateInt(DateAndTimeUtils.nowDate())));
                viewModel.getBaseErp().getLogoSqlBriteDatabase().executeInsert("CASECASH", contentValues);
                return true;
            }
            viewModel.getBaseErp().getLogoSqlBriteDatabase().update("CASECASH", contentValues, "LOGICALREF=?", StringUtils.convertIntToString(MainActivity.sFicheRef));
            return true;
        } catch (final Exception e2) {
            alert(String.valueOf(e2.getMessage()));
            return false;
        }
    }
    public void clearEditor() {
        final SharedPreferences sharedPreferences = f1225sp;
        Intrinsics.checkNotNull(sharedPreferences);
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        final EditTextPreference editTextPreference = etAmount;
        Intrinsics.checkNotNull(editTextPreference);
        edit.remove(editTextPreference.getKey());
        final EditTextPreference editTextPreference2 = etReceiptNo;
        Intrinsics.checkNotNull(editTextPreference2);
        edit.remove(editTextPreference2.getKey());
        final EditTextPref editTextPref = etDescription;
        Intrinsics.checkNotNull(editTextPref);
        edit.remove(editTextPref.getKey());
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
            SalesCaseCashHeaderEnterActivity.saveCompletedefault(this, null, 1, null);
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
        if (customerOperation.getFicheMode() == FicheMode.NEW) {
            this.deleteCollection();
        }
        this.setResult(0);
        this.clearEditor();
        this.finish();
    }
    static void saveCompletedefault(final SalesCaseCashHeaderEnterActivity salesCaseCashHeaderEnterActivity, String str, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            str = "";
        }
        salesCaseCashHeaderEnterActivity.saveComplete(str);
    }
    private void saveComplete(final String str) {
        if (this.ficheSave(str)) {
            this.setResult(-1);
            this.clearEditor();
            final BaseErp<?> baseErp = viewModel.getBaseErp();
            final int i2 = MainActivity.sFicheRef;
            final ReceiptType receiptType = mReceiptType;
            Intrinsics.checkNotNull(receiptType);
            baseErp.insertFicheBroadcastMessage(i2, receiptType.mfType);
            MainActivity.sFicheRef = -1;
            this.finish();
        }
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
