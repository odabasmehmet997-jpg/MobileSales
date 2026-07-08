package com.proje.mobilesales.features.collections.prefs.view.activity;

import android.R;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
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
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.interfaces.di.ActivityComponent;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.preferences.EditTextPref;
import com.proje.mobilesales.core.preferences.LogoDatePreference;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.sql.ISqlBriteDatabase;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.utils.AppUtils;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.collections.prefs.repository.SalesChequeAndDeedHeaderEnterRepository;
import com.proje.mobilesales.features.collections.prefs.viewmodel.SalesChequeAndDeedHeaderEnterViewModel;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.reports.view.activity.ReportAllActivity;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.settings.interfaces.MatterCheck;
import com.proje.mobilesales.features.settings.interfaces.PreferenceClear;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import static com.proje.mobilesales.core.utils.AppUtils.alert;


public final class SalesChequeAndDeedHeaderEnterActivity extends BaseErpActivityPreferences implements SharedPreferences.OnSharedPreferenceChangeListener, PreferenceClear, MatterCheck {
    private static final int CANCEL = 4;
    private static final int COLLECTIONS = 0;
    public static final Companion Companion = new Companion(null);
    private static final int REPORT = 3;
    private static final int SAVE = 2;
    private LogoDatePreference dpCSDate;
    private Object etCurr;
    private EditTextPref etDocNo;
    private EditTextPref etExplanation1;
    private EditTextPref etExplanation2;
    private EditTextPref etExplanation3;
    private EditTextPref etExplanation4;
    private boolean isFicheNew;
    private ListPreference lpDivide;
    private ListPreference lpProjectCode;
    private ListPreference lpSpecialCode;
    private ListPreference lpTradingOperationGroup;
    private ListPreference lpWorkplace;
    private ListPreference lpYetkiKodu;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private MatterSettings mMatterSettings;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private ReceiptType mReceiptType;
    private Preference pEnterAmount;
    private final SalesChequeAndDeedHeaderEnterRepository repository; 
    private SharedPreferences f1229sp;
    private final SalesChequeAndDeedHeaderEnterViewModel viewModel;
    public SalesChequeAndDeedHeaderEnterActivity() {
        final SalesChequeAndDeedHeaderEnterRepository salesChequeAndDeedHeaderEnterRepository = new SalesChequeAndDeedHeaderEnterRepository();
        repository = salesChequeAndDeedHeaderEnterRepository;
        viewModel = new SalesChequeAndDeedHeaderEnterViewModel(salesChequeAndDeedHeaderEnterRepository);
    }
    public SalesChequeAndDeedHeaderEnterRepository getRepository() {
        return repository;
    }
    public SalesChequeAndDeedHeaderEnterViewModel getViewModel() {
        return viewModel;
    }
    public SharedPreferences getSp() {
        return f1229sp;
    }
    public void setSp(final SharedPreferences sharedPreferences) {
        f1229sp = sharedPreferences;
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
    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return mAlertDialogBuilder;
    }
    public void setMAlertDialogBuilder(final AlertDialogBuilder<?> alertDialogBuilder) {
        mAlertDialogBuilder = alertDialogBuilder;
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
        this.addPreferencesFromResource(R.xml.csheaderenter);
        final Context context = ContextUtils.getmContext();
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mAlertDialogBuilder = new AlertDialogBuilder.Impl(context, (BaseInjectableActivity) activity);
        final Context context2 = ContextUtils.getmContext();
        final Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context2, (BaseInjectableActivity) activity2);
        f1229sp = PreferenceManager.getDefaultSharedPreferences(this);
        this.getExtras();
        if (!viewModel.getBaseErp().checkRouteVisitOutOfOrder(this, customerRef, 0, 0)) {
            Toast.makeText(this, this.getString(R.string.str_comply_before_route), Toast.LENGTH_LONG).show();
            super.onBackPressed();
        }
        final CustomerOperation customerOperation = this.customerOperation;
        if (null != customerOperation) {
            fromReceiptType = customerOperation.getReceiptType();
        } else {
            fromReceiptType = ReceiptType.Companion.fromReceiptType(MainActivity.fType.getValue());
        }
        mReceiptType = fromReceiptType;
        isFicheNew = 0 >= MainActivity.sFicheRef;
        this.DefaultLoads();
        final LogoDatePreference logoDatePreference = dpCSDate;
        Intrinsics.checkNotNull(logoDatePreference);
        logoDatePreference.setDate(DateAndTimeUtils.nowDate());
        Preferences.initSummary(dpCSDate);
        if (mReceiptType == ReceiptType.CHEQUE) {
            this.setTitle(this.getString(R.string.str_check_collection));
        } else {
            this.setTitle(this.getString(R.string.str_payroll_note_collection));
        }
        mMatterSettings = viewModel.getBaseErp().getMatterSettings(this, this.customerOperation.getFicheType());
        this.getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.proje.mobilesales.features.collections.prefs.view.activity.SalesChequeAndDeedHeaderEnterActivityExternalSyntheticLambda3
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
                final boolean onCreatelambda0;
                onCreatelambda0 = onCreatelambda0(SalesChequeAndDeedHeaderEnterActivity.this, adapterView, view, i2, j2);
                return onCreatelambda0;
            }
        });
        final Preference preference = pEnterAmount;
        Intrinsics.checkNotNull(preference);
        preference.setSummary(this.getString(R.string.str_total_quantity) + viewModel.getBaseErp().getLogoSqlHelper().cekSenetToplaminiGetir());
        try {
            this.setVisibility();
        } catch (final NullPointerException e2) {
            e2.printStackTrace();
        }
        if (isFicheNew) {
            final Intent intent = new Intent(this, SalesChequeAndDeedDetailEnterActivity.class);
            intent.putExtra("RECEIPT", mReceiptType);
            final SharedPreferences sharedPreferences = f1229sp;
            intent.putExtra("BRANCH", StringUtils.convertStringToInt(null != sharedPreferences ? sharedPreferences.getString("lpIsyeri", "0") : null));
            this.startActivityForResult(intent, 1);
        }
    } 
    public static boolean onCreatelambda0(final SalesChequeAndDeedHeaderEnterActivity this0, final AdapterView parent, final View view, final int i2, final long j2) {
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
    private void setVisibility() {
        final String obj;
        int i2 = 1;
        if (mReceiptType == ReceiptType.CHEQUE) {
            final String upVal = viewModel.getBaseErp().getLogoSqlHelper().upVal("119");
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
            final String upVal2 = viewModel.getBaseErp().getLogoSqlHelper().upVal("132");
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
        if (TextUtils.isEmpty(obj)) {
            return;
        }
        try {
            if (StringsKt.contains(obj, "0", false)) {
                i2 = 0;
            } else {
                final Preference findPreference = this.findPreference("pgCsDigerBilgiler");
                Intrinsics.checkNotNull(findPreference, "null cannot be cast to non-null type android.preference.PreferenceGroup");
                ((PreferenceGroup) findPreference).removePreference(lpWorkplace);
            }
            if (!StringsKt.contains(obj, BuildConfig.NETSIS_DEMO_PASSWORD, false)) {
                final Preference findPreference2 = this.findPreference("pgCsDigerBilgiler");
                Intrinsics.checkNotNull(findPreference2, "null cannot be cast to non-null type android.preference.PreferenceGroup");
                ((PreferenceGroup) findPreference2).removePreference(lpDivide);
                i2++;
            }
            if (!StringsKt.contains(obj, ExifInterface.GPS_MEASUREMENT_2D, false)) {
                final Preference findPreference3 = this.findPreference("pgCsDigerBilgiler");
                Intrinsics.checkNotNull(findPreference3, "null cannot be cast to non-null type android.preference.PreferenceGroup");
                ((PreferenceGroup) findPreference3).removePreference(lpSpecialCode);
                i2++;
            }
            if (!StringsKt.contains(obj, ExifInterface.GPS_MEASUREMENT_3D, false)) {
                final Preference findPreference4 = this.findPreference("pgCsDigerBilgiler");
                Intrinsics.checkNotNull(findPreference4, "null cannot be cast to non-null type android.preference.PreferenceGroup");
                ((PreferenceGroup) findPreference4).removePreference(lpProjectCode);
                i2++;
            }
            if (4 == i2) {
                final Preference findPreference5 = this.findPreference("pgCsDigerBilgiler");
                Intrinsics.checkNotNull(findPreference5, "null cannot be cast to non-null type android.preference.PreferenceCategory");
                final Preference findPreference6 = this.findPreference("psCsHeader");
                Intrinsics.checkNotNull(findPreference6, "null cannot be cast to non-null type android.preference.PreferenceGroup");
                ((PreferenceGroup) findPreference6).removePreference(findPreference5);
            }
            if (!StringsKt.contains(obj, "4", false)) {
                final Preference findPreference7 = this.findPreference("pgCsGenelBilgiler");
                Intrinsics.checkNotNull(findPreference7, "null cannot be cast to non-null type android.preference.PreferenceGroup");
                ((PreferenceGroup) findPreference7).removePreference(lpYetkiKodu);
            }
            if (StringsKt.contains(obj, "5", false)) {
                return;
            }
            final Preference findPreference8 = this.findPreference("pgCsGenelBilgiler");
            Intrinsics.checkNotNull(findPreference8, "null cannot be cast to non-null type android.preference.PreferenceGroup");
            ((PreferenceGroup) findPreference8).removePreference(lpTradingOperationGroup);
        } catch (final Exception e2) {
            e2.printStackTrace();
        }
    }
    public void onDestroy() {
        super.onDestroy();
    }
    private void setProSpecBranchDivision() {
        final Cursor cursor;
        final String upVal;
        final ReceiptType receiptType = mReceiptType;
        if (receiptType == ReceiptType.CHEQUE) {
            if (!Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("204_2"), "")) {
                final ListPreference listPreference = lpSpecialCode;
                Intrinsics.checkNotNull(listPreference);
                listPreference.setValue(viewModel.getBaseErp().getLogoSqlHelper().upVal("204_2"));
                final ListPreference listPreference2 = lpSpecialCode;
                Intrinsics.checkNotNull(listPreference2);
                listPreference2.setSummary(viewModel.getBaseErp().getLogoSqlHelper().upVal("204_2"));
                final Cursor query = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select  specode FROM SPECODES WHERE  CODETYPE=1 AND Specodetype=30");
                if (query.moveToFirst()) {
                    int i2 = 0;
                    while (true) {
                        if (Intrinsics.areEqual(query.getString(0), viewModel.getBaseErp().getLogoSqlHelper().upVal("204_2"))) {
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
        } else if (receiptType == ReceiptType.DEED && !Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("206_2"), "")) {
            final ListPreference listPreference5 = lpSpecialCode;
            Intrinsics.checkNotNull(listPreference5);
            listPreference5.setValue(viewModel.getBaseErp().getLogoSqlHelper().upVal("206_2"));
            final ListPreference listPreference6 = lpSpecialCode;
            Intrinsics.checkNotNull(listPreference6);
            listPreference6.setSummary(viewModel.getBaseErp().getLogoSqlHelper().upVal("206_2"));
            final Cursor query2 = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select  specode FROM SPECODES WHERE  CODETYPE=1 AND Specodetype=30");
            if (query2.moveToFirst()) {
                int i3 = 0;
                while (true) {
                    if (Intrinsics.areEqual(query2.getString(0), viewModel.getBaseErp().getLogoSqlHelper().upVal("206_2"))) {
                        final ListPreference listPreference7 = lpSpecialCode;
                        Intrinsics.checkNotNull(listPreference7);
                        listPreference7.setValueIndex(i3 + 1);
                        final ListPreference listPreference8 = lpSpecialCode;
                        Intrinsics.checkNotNull(listPreference8);
                        listPreference8.setSummary(query2.getString(0));
                        break;
                    }
                    i3++;
                    if (!query2.moveToNext()) {
                        break;
                    }
                }
            }
        }
        String str = null;
        if (mReceiptType == ReceiptType.CHEQUE && !Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("204_4"), "")) {
            cursor = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select PROJE,LogicalRef FROM PROJECT WHERE LOGICALREF='" + viewModel.getBaseErp().getLogoSqlHelper().upVal("204_4") + '\'');
        } else if (mReceiptType != ReceiptType.DEED || Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("206_4"), "")) {
            cursor = null;
        } else {
            cursor = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select PROJE,LogicalRef FROM PROJECT WHERE LOGICALREF='" + viewModel.getBaseErp().getLogoSqlHelper().upVal("206_4") + '\'');
        }
        if (null != cursor && cursor.moveToFirst()) {
            final Cursor query3 = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select LOGICALREF FROM PROJECT");
            if (query3.moveToFirst()) {
                int i4 = 0;
                while (true) {
                    if (Intrinsics.areEqual(query3.getString(0), cursor.getString(1))) {
                        final ListPreference listPreference9 = lpProjectCode;
                        Intrinsics.checkNotNull(listPreference9);
                        listPreference9.setValueIndex(i4 + 1);
                        final ListPreference listPreference10 = lpProjectCode;
                        Intrinsics.checkNotNull(listPreference10);
                        listPreference10.setSummary(cursor.getString(0));
                        break;
                    }
                    i4++;
                    if (!query3.moveToNext()) {
                        break;
                    }
                }
            }
        }
        if (mReceiptType == ReceiptType.CHEQUE && !Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("204_1"), "")) {
            upVal = viewModel.getBaseErp().getLogoSqlHelper().upVal("204_1");
        } else {
            upVal = (mReceiptType != ReceiptType.DEED || Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("206_1"), "")) ? "" : viewModel.getBaseErp().getLogoSqlHelper().upVal("206_1");
        }
        final Cursor query4 = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select NR,ISYERI FROM BRANCH");
        if (query4.moveToFirst() && !Intrinsics.areEqual(upVal, "")) {
            int i5 = 0;
            while (true) {
                if (Intrinsics.areEqual(query4.getString(0), upVal)) {
                    final ListPreference listPreference11 = lpWorkplace;
                    Intrinsics.checkNotNull(listPreference11);
                    listPreference11.setValueIndex(i5);
                    final ListPreference listPreference12 = lpWorkplace;
                    Intrinsics.checkNotNull(listPreference12);
                    listPreference12.setSummary(query4.getString(1));
                    break;
                }
                i5++;
                if (!query4.moveToNext()) {
                    break;
                }
            }
        }
        if (mReceiptType == ReceiptType.CHEQUE && !Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("204_3"), "")) {
            str = viewModel.getBaseErp().getLogoSqlHelper().upVal("204_3");
        } else if (mReceiptType == ReceiptType.DEED && !Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().upVal("206_3"), "")) {
            str = viewModel.getBaseErp().getLogoSqlHelper().upVal("206_3");
        }
        final Cursor query5 = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("Select NR,BOLUM FROM DIVISION");
        if (!query5.moveToFirst() || Intrinsics.areEqual(str, "")) {
            return;
        }
        int i6 = 0;
        while (!Intrinsics.areEqual(query5.getString(0), str)) {
            i6++;
            if (!query5.moveToNext()) {
                return;
            }
        }
        final ListPreference listPreference13 = lpDivide;
        Intrinsics.checkNotNull(listPreference13);
        listPreference13.setValueIndex(i6);
        final ListPreference listPreference14 = lpDivide;
        Intrinsics.checkNotNull(listPreference14);
        listPreference14.setSummary(query5.getString(1));
    }
    private void DefaultLoads() {
        this.KontrolleriOlustur();
        this.BolumleriYukle();
        this.IsYerleriYukle();
        this.TIGrubuYukle();
        this.OzelKodlariYukle();
        this.ProjeleriYukle();
        this.YetkiKodlariYukle();
        this.DigerBilgiler();
        if (0 >= MainActivity.sFicheRef) {
            this.IlkKaydiAc();
        }
        this.FisBilgileriniGetir();
        Preferences.initSummary(lpWorkplace);
        Preferences.initSummary(lpDivide);
        Preferences.initSummary(lpSpecialCode);
        Preferences.initSummary(lpProjectCode);
        Preferences.initSummary(lpYetkiKodu);
        Preferences.initSummary(lpTradingOperationGroup);
    }
    private void IlkKaydiAc() {
        final Object valueOf;
        final Object valueOf2;
        final boolean z = viewModel.getBaseErp().getErpType() == ErpType.NETSIS;
        if (mReceiptType == ReceiptType.CHEQUE) {
            final ISqlBriteDatabase logoSqlBriteDatabase = viewModel.getBaseErp().getLogoSqlBriteDatabase();
            final StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO CHEQUEDEED (");
            sb.append(z ? "CLCODE" : "CLREF");
            sb.append(",ISTRANSFER,FTYPE,PRINTCOUNT,DATEINT,GDATE,ENLEM,BOYLAM,BRANCHNR,DIVISNR,SPECODE,PROJECTREF,CYPHCODE,TRADINGGRP,ANDFICHENO) VALUES (");
            if (z) {
                valueOf2 = '\'' + viewModel.getBaseErp().getLogoSqlHelper().getClCode(customerRef) + '\'';
            } else {
                valueOf2 = Integer.valueOf(customerRef);
            }
            sb.append(valueOf2);
            sb.append(",0,0,0,");
            sb.append(DateAndTimeUtils.getDateInt(DateAndTimeUtils.nowDate()));
            sb.append(",'");
            sb.append(DateAndTimeUtils.nowDateTime());
            sb.append("','");
            sb.append(ContextUtils.getLatitude());
            sb.append("','");
            sb.append(ContextUtils.getLongitude());
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("126"));
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("127"));
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("128"));
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("129"));
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("130"));
            sb.append("','");
            sb.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("131"));
            sb.append("','");
            final ErpType erpType = viewModel.getBaseErp().getErpType();
            Intrinsics.checkNotNullExpressionValue(erpType, "getErpType(...)");
            sb.append(StringUtils.getCreateAndFicheNo(erpType, 4));
            sb.append("')");
            logoSqlBriteDatabase.execute(sb.toString());
        } else {
            final ISqlBriteDatabase logoSqlBriteDatabase2 = viewModel.getBaseErp().getLogoSqlBriteDatabase();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("INSERT INTO CHEQUEDEED (");
            sb2.append(z ? "CLCODE" : "CLREF");
            sb2.append(",ISTRANSFER,FTYPE,PRINTCOUNT,DATEINT,GDATE,ENLEM,BOYLAM,BRANCHNR,DIVISNR,SPECODE,PROJECTREF,CYPHCODE,TRADINGGRP,ANDFICHENO) VALUES (");
            if (z) {
                valueOf = '\'' + viewModel.getBaseErp().getLogoSqlHelper().getClCode(customerRef) + '\'';
            } else {
                valueOf = Integer.valueOf(customerRef);
            }
            sb2.append(valueOf);
            sb2.append(",0,1,0,");
            sb2.append(DateAndTimeUtils.getDateInt(DateAndTimeUtils.nowDate()));
            sb2.append(",'");
            sb2.append(DateAndTimeUtils.nowDateTime());
            sb2.append("','");
            sb2.append(ContextUtils.getLatitude());
            sb2.append("','");
            sb2.append(ContextUtils.getLongitude());
            sb2.append("','");
            sb2.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("139"));
            sb2.append("','");
            sb2.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("140"));
            sb2.append("','");
            sb2.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("141"));
            sb2.append("','");
            sb2.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("142"));
            sb2.append("','");
            sb2.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("143"));
            sb2.append("','");
            sb2.append(viewModel.getBaseErp().getLogoSqlHelper().upVal("144"));
            sb2.append("','");
            final ErpType erpType2 = viewModel.getBaseErp().getErpType();
            Intrinsics.checkNotNullExpressionValue(erpType2, "getErpType(...)");
            sb2.append(StringUtils.getCreateAndFicheNo(erpType2, 4));
            sb2.append("')");
            logoSqlBriteDatabase2.execute(sb2.toString());
        }
        final Cursor query = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT MAX(LOGICALREF) AS LOGICALREF FROM CHEQUEDEED");
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
    private void FisBilgileriniGetir() {
        final Cursor query = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT * FROM CHEQUEDEED WHERE LOGICALREF=" + MainActivity.sFicheRef);
        if (query.moveToPosition(0)) {
            final ListPreference listPreference = lpTradingOperationGroup;
            Intrinsics.checkNotNull(listPreference);
            final ISqlHelper logoSqlHelper = viewModel.getBaseErp().getLogoSqlHelper();
            final ColType colType = ColType.metin;
            listPreference.setValue(logoSqlHelper.dbVal(query, "TRADINGGRP", colType).toString());
            final LogoDatePreference logoDatePreference = dpCSDate;
            Intrinsics.checkNotNull(logoDatePreference);
            logoDatePreference.setDate(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "CHEQUEDEEDDATE", ColType.tarih).toString());
            final ListPreference listPreference2 = lpSpecialCode;
            Intrinsics.checkNotNull(listPreference2);
            listPreference2.setValue(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "SPECODE", colType).toString());
            final ListPreference listPreference3 = lpProjectCode;
            Intrinsics.checkNotNull(listPreference3);
            final ISqlHelper logoSqlHelper2 = viewModel.getBaseErp().getLogoSqlHelper();
            final ColType colType2 = ColType.sayi;
            listPreference3.setValue(logoSqlHelper2.dbVal(query, "PROJECTREF", colType2).toString());
            final ListPreference listPreference4 = lpYetkiKodu;
            Intrinsics.checkNotNull(listPreference4);
            listPreference4.setValue(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "CYPHCODE", colType).toString());
            if (viewModel.getBaseErp().getErpType() == ErpType.NETSIS) {
                customerCode = viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "CLCODE", colType2).toString();
            } else {
                customerRef = StringUtils.convertStringToInt(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "CLREF", colType2).toString());
            }
            final EditTextPref editTextPref = etExplanation1;
            Intrinsics.checkNotNull(editTextPref);
            editTextPref.setText(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "DESC1", colType).toString());
            final EditTextPref editTextPref2 = etExplanation2;
            Intrinsics.checkNotNull(editTextPref2);
            editTextPref2.setText(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "DESC2", colType).toString());
            final EditTextPref editTextPref3 = etExplanation3;
            Intrinsics.checkNotNull(editTextPref3);
            editTextPref3.setText(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "DESC3", colType).toString());
            final EditTextPref editTextPref4 = etExplanation4;
            Intrinsics.checkNotNull(editTextPref4);
            editTextPref4.setText(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "DESC4", colType).toString());
            final EditTextPref editTextPref5 = etDocNo;
            Intrinsics.checkNotNull(editTextPref5);
            editTextPref5.setText(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "DOCNO", colType).toString());
            final ListPreference listPreference5 = lpDivide;
            Intrinsics.checkNotNull(listPreference5);
            listPreference5.setValue(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "DIVISNR", colType2).toString());
            final ListPreference listPreference6 = lpWorkplace;
            Intrinsics.checkNotNull(listPreference6);
            listPreference6.setValue(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "BRANCHNR", colType2).toString());
        }
        query.close();
        Preferences.initSummary(lpWorkplace);
        Preferences.initSummary(lpDivide);
        Preferences.initSummary(lpTradingOperationGroup);
        Preferences.initSummary(dpCSDate);
        Preferences.initSummary(lpSpecialCode);
        Preferences.initSummary(lpProjectCode);
        Preferences.initSummary(lpYetkiKodu);
        Preferences.initSummary(etExplanation1);
        Preferences.initSummary(etExplanation2);
        Preferences.initSummary(etExplanation3);
        Preferences.initSummary(etExplanation4);
        Preferences.initSummary(etDocNo);
    }
    protected void onActivityResult(final int i2, final int i3, final Intent data) {
        Intrinsics.checkNotNullParameter(data, "data");
        final Preference preference = pEnterAmount;
        Intrinsics.checkNotNull(preference);
        preference.setSummary(this.getString(R.string.str_total_quantity) + viewModel.getBaseErp().getLogoSqlHelper().cekSenetToplaminiGetir());
        if (1234 != i2 || -1 != i3) {
            if (-1 == i3) {
                this.setResult(-1);
                return;
            } else {
                if (0 == i3) {
                    this.openFinishDialog();
                    return;
                }
                return;
            }
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
    private void KontrolleriOlustur() {
        pEnterAmount = this.findPreference("pTutarGirisi");
        final Preference findPreference = this.findPreference("dpCSTarih");
        Intrinsics.checkNotNull(findPreference, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.LogoDatePreference");
        dpCSDate = (LogoDatePreference) findPreference;
        final Preference findPreference2 = this.findPreference("lpTicariIslemGrubu");
        Intrinsics.checkNotNull(findPreference2, "null cannot be cast to non-null type android.preference.ListPreference");
        lpTradingOperationGroup = (ListPreference) findPreference2;
        final Preference findPreference3 = this.findPreference("lpOzelKodu");
        Intrinsics.checkNotNull(findPreference3, "null cannot be cast to non-null type android.preference.ListPreference");
        lpSpecialCode = (ListPreference) findPreference3;
        final Preference findPreference4 = this.findPreference("lpProjeKodu");
        Intrinsics.checkNotNull(findPreference4, "null cannot be cast to non-null type android.preference.ListPreference");
        lpProjectCode = (ListPreference) findPreference4;
        final Preference findPreference5 = this.findPreference("lpYetkiKodu");
        Intrinsics.checkNotNull(findPreference5, "null cannot be cast to non-null type android.preference.ListPreference");
        lpYetkiKodu = (ListPreference) findPreference5;
        final Preference findPreference6 = this.findPreference("etExplanation1");
        Intrinsics.checkNotNull(findPreference6, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.EditTextPref");
        etExplanation1 = (EditTextPref) findPreference6;
        final Preference findPreference7 = this.findPreference("etExplanation2");
        Intrinsics.checkNotNull(findPreference7, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.EditTextPref");
        etExplanation2 = (EditTextPref) findPreference7;
        final Preference findPreference8 = this.findPreference("etExplanation3");
        Intrinsics.checkNotNull(findPreference8, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.EditTextPref");
        etExplanation3 = (EditTextPref) findPreference8;
        final Preference findPreference9 = this.findPreference("etExplanation4");
        Intrinsics.checkNotNull(findPreference9, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.EditTextPref");
        etExplanation4 = (EditTextPref) findPreference9;
        final Preference findPreference10 = this.findPreference("etDocNo");
        Intrinsics.checkNotNull(findPreference10, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.EditTextPref");
        etDocNo = (EditTextPref) findPreference10;
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
    private void DigerBilgiler() {
        final Preference preference = pEnterAmount;
        if (preference == null) {
            return;
        }
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(final Preference preference2) {
                final boolean DigerBilgilerlambda3;
                SalesChequeAndDeedHeaderEnterActivity this0 = null;
                DigerBilgilerlambda3 = DigerBilgilerlambda3(this0, preference2);
                return DigerBilgilerlambda3;
            }
        });
    }
    public static boolean DigerBilgilerlambda3(final SalesChequeAndDeedHeaderEnterActivity this0, final Preference preference) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final Intent intent = new Intent(this0, SalesChequeAndDeedDetailEnterActivity.class);
        final SharedPreferences sharedPreferences = this0.f1229sp;
        Intrinsics.checkNotNull(sharedPreferences);
        intent.putExtra("BRANCH", StringUtils.convertStringToInt(sharedPreferences.getString("lpIsyeri", "0")));
        intent.putExtra("RECEIPT", this0.mReceiptType);
        this0.startActivityForResult(intent, 1);
        return true;
    }
    private void BolumleriYukle() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT * FROM DIVISION", lpDivide, "BOLUM", "NR", ColType.sayi, Boolean.FALSE);
    }
    private void IsYerleriYukle() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT * FROM BRANCH", lpWorkplace, "ISYERI", "NR", ColType.sayi, Boolean.FALSE);
    }
    private void TIGrubuYukle() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT * FROM TRADINGGRP", lpTradingOperationGroup, "CODE", "CODE", ColType.metin, Boolean.TRUE);
    }
    private void OzelKodlariYukle() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT SPECODE FROM SPECODES WHERE CODETYPE=1 AND SPECODETYPE=30", lpSpecialCode, "SPECODE", "SPECODE", ColType.metin, Boolean.TRUE);
    }
    private void ProjeleriYukle() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT * FROM PROJECT", lpProjectCode, "PROJE", "LOGICALREF", ColType.metin, Boolean.TRUE);
    }
    private void YetkiKodlariYukle() {
        viewModel.getBaseErp().getLogoSqlHelper().loadPrefData("SELECT SPECODE FROM SPECODES WHERE CODETYPE=2 AND SPECODETYPE=30", lpYetkiKodu, "SPECODE", "SPECODE", ColType.metin, Boolean.TRUE);
    }
    public boolean onKeyDown(final int i2, final KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (4 == i2) {
            this.openFinishDialog();
            return true;
        }
        return super.onKeyDown(i2, event);
    }
    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        menu.add(0, 0, 0, this.getString(R.string.str_made_collections)).setIcon(R.drawable.ic_menu_agenda);
        menu.add(0, 2, 0, ContextUtils.getStringResource(R.string.str_save)).setIcon(R.drawable.ic_menu_save);
        menu.add(0, 3, 0, ContextUtils.getStringResource(R.string.str_reports)).setIcon(R.drawable.ic_menu_directions);
        menu.add(0, 4, 0, ContextUtils.getStringResource(R.string.str_cancel)).setIcon(R.drawable.ic_menu_close_clear_cancel);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemId = item.getItemId();
        if (0 == itemId) {
            this.startActivityForResult(new Intent(this, SalesChequeAndDeedDetailActivity.class), 1);
        } else {
            if (16908332 == itemId) {
                this.openFinishDialog();
                return true;
            }
            if (2 != itemId) {
                if (3 == itemId) {
                    this.startActivityForResult(new Intent(this, ReportAllActivity.class), 0);
                } else if (4 == itemId) {
                    this.openFinishDialog();
                }
            } else {
                if (!viewModel.getBaseErp().getLogoSqlHelper().cekSenetDetayiVarmi()) {
                    Toast.makeText(this, ContextUtils.getStringResource(R.string.str_collection_detail_not_added), Toast.LENGTH_SHORT).show();
                    return true;
                }
                final MatterSettings matterSettings = mMatterSettings;
                Intrinsics.checkNotNull(matterSettings);
                if (matterSettings.isUseMatterNo()) {
                    this.checkMatter();
                } else {
                    this.saveComplete("");
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        this.openFinishDialog();
    }
    private void openFinishDialog() {
        final AlertDialogBuilder<?> alertDialogBuilder = mAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setTitle(this.getString(R.string.str_return_sales_fiche_question)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.collections.prefs.view.activity.SalesChequeAndDeedHeaderEnterActivityExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                openFinishDialoglambda4(SalesChequeAndDeedHeaderEnterActivity.this, dialogInterface, i2);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.collections.prefs.view.activity.SalesChequeAndDeedHeaderEnterActivityExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                openFinishDialoglambda5(dialogInterface, i2);
            }
        }).create().show();
    }
    public static void openFinishDialoglambda4(final SalesChequeAndDeedHeaderEnterActivity this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.cancelFiche();
    }
    public static void openFinishDialoglambda5(final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        dialogInterface.dismiss();
    }
    static boolean saveFichedefault(final SalesChequeAndDeedHeaderEnterActivity salesChequeAndDeedHeaderEnterActivity, String str, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            str = "";
        }
        return salesChequeAndDeedHeaderEnterActivity.saveFiche(str);
    }
    private boolean saveFiche(final String str) {
        try {
            final ContentValues contentValues = new ContentValues();
            contentValues.put("ENLEM", Double.valueOf(ContextUtils.getLatitude()));
            contentValues.put("BOYLAM", Double.valueOf(ContextUtils.getLongitude()));
            if (viewModel.getBaseErp().getErpType() == ErpType.NETSIS) {
                contentValues.put("CLCODE", viewModel.getBaseErp().getLogoSqlHelper().getClCode(customerRef));
            } else {
                contentValues.put("CLREF", Integer.valueOf(customerRef));
            }
            final SharedPreferences sharedPreferences = f1229sp;
            Intrinsics.checkNotNull(sharedPreferences);
            contentValues.put("TRADINGGRP", sharedPreferences.getString("lpTicariIslemGrubu", ""));
            final SharedPreferences sharedPreferences2 = f1229sp;
            Intrinsics.checkNotNull(sharedPreferences2);
            contentValues.put("CHEQUEDEEDDATE", sharedPreferences2.getString("dpCSTarih", DateAndTimeUtils.nowDate()));
            final SharedPreferences sharedPreferences3 = f1229sp;
            Intrinsics.checkNotNull(sharedPreferences3);
            contentValues.put("DIVISNR", Integer.valueOf(StringUtils.convertStringToInt(sharedPreferences3.getString("lpBolum", "0"))));
            final SharedPreferences sharedPreferences4 = f1229sp;
            Intrinsics.checkNotNull(sharedPreferences4);
            contentValues.put("BRANCHNR", Integer.valueOf(StringUtils.convertStringToInt(sharedPreferences4.getString("lpIsyeri", "0"))));
            final ListPreference listPreference = lpSpecialCode;
            Intrinsics.checkNotNull(listPreference);
            contentValues.put("SPECODE", listPreference.getValue());
            final SharedPreferences sharedPreferences5 = f1229sp;
            Intrinsics.checkNotNull(sharedPreferences5);
            contentValues.put("PROJECTREF", sharedPreferences5.getString("lpProjeKodu", ""));
            final SharedPreferences sharedPreferences6 = f1229sp;
            Intrinsics.checkNotNull(sharedPreferences6);
            contentValues.put("CYPHCODE", sharedPreferences6.getString("lpYetkiKodu", ""));
            final SharedPreferences sharedPreferences7 = f1229sp;
            Intrinsics.checkNotNull(sharedPreferences7);
            contentValues.put("DESC1", sharedPreferences7.getString("etExplanation1", ""));
            final SharedPreferences sharedPreferences8 = f1229sp;
            Intrinsics.checkNotNull(sharedPreferences8);
            contentValues.put("DESC2", sharedPreferences8.getString("etExplanation2", ""));
            final SharedPreferences sharedPreferences9 = f1229sp;
            Intrinsics.checkNotNull(sharedPreferences9);
            contentValues.put("DESC3", sharedPreferences9.getString("etExplanation3", ""));
            final SharedPreferences sharedPreferences10 = f1229sp;
            Intrinsics.checkNotNull(sharedPreferences10);
            contentValues.put("DESC4", sharedPreferences10.getString("etExplanation4", ""));
            final SharedPreferences sharedPreferences11 = f1229sp;
            Intrinsics.checkNotNull(sharedPreferences11);
            contentValues.put("DOCNO", sharedPreferences11.getString("etDocNo", ""));
            contentValues.put("FICHENO", str);
            if (0 >= MainActivity.sFicheRef) {
                contentValues.put("DATEINT", Integer.valueOf(DateAndTimeUtils.getDateInt(DateAndTimeUtils.nowDate())));
                viewModel.getBaseErp().getLogoSqlBriteDatabase().executeInsert("CHEQUEDEED", contentValues);
                return true;
            }
            viewModel.getBaseErp().getLogoSqlBriteDatabase().update("CHEQUEDEED", contentValues, "LOGICALREF=" + MainActivity.sFicheRef);
            return true;
        } catch (final Exception e2) {
            alert(String.valueOf(e2.getMessage()));
            return false;
        }
    }
    public void clearEditor() {
        final SharedPreferences sharedPreferences = f1229sp;
        Intrinsics.checkNotNull(sharedPreferences);
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        final Preference preference = pEnterAmount;
        Intrinsics.checkNotNull(preference);
        edit.remove(preference.getKey());
        final LogoDatePreference logoDatePreference = dpCSDate;
        Intrinsics.checkNotNull(logoDatePreference);
        edit.remove(logoDatePreference.getKey());
        final EditTextPref editTextPref = etExplanation1;
        Intrinsics.checkNotNull(editTextPref);
        edit.remove(editTextPref.getKey());
        final EditTextPref editTextPref2 = etExplanation2;
        Intrinsics.checkNotNull(editTextPref2);
        edit.remove(editTextPref2.getKey());
        final EditTextPref editTextPref3 = etExplanation3;
        Intrinsics.checkNotNull(editTextPref3);
        edit.remove(editTextPref3.getKey());
        final EditTextPref editTextPref4 = etExplanation4;
        Intrinsics.checkNotNull(editTextPref4);
        edit.remove(editTextPref4.getKey());
        final EditTextPref editTextPref5 = etDocNo;
        Intrinsics.checkNotNull(editTextPref5);
        edit.remove(editTextPref5.getKey());
        edit.apply();
    }
    public void checkMatter() {
        final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.setMessage(this.getString(R.string.str_please_wait_get_matter_no)).show();
        viewModel.getBaseErp().getMaxMatterNo(customerOperation.getFicheType(), mMatterSettings, new MatterCheckListener(this));
    }
    public void onMatterCheck(final String str) {
        final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this, this.getString(R.string.exp_45_undefined), Toast.LENGTH_LONG).show();
            SalesChequeAndDeedHeaderEnterActivity.saveCompletedefault(this, null, 1, null);
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
            public void onFailure(Throwable throwable) { }
            public void onResponse(Boolean aBoolean) { }
            public void onResponse(Sales sales) { }
            public void onResponse(TigerServiceResult tigerServiceResult) { }
            public void onResponse(Object obj) { }
            public void onResponse(ArrayList<Object> obj) { }
            public void onResponse() { }
        });
    }
    public void cancelFiche() {
        this.setResult(0);
        if (customerOperation.getFicheMode() == FicheMode.NEW) {
            viewModel.getBaseErp().getLogoSqlHelper().cekSenetTahsilatSil();
        }
        this.clearEditor();
        this.finish();
    }
    static void saveCompletedefault(final SalesChequeAndDeedHeaderEnterActivity salesChequeAndDeedHeaderEnterActivity, String str, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            str = "";
        }
        salesChequeAndDeedHeaderEnterActivity.saveComplete(str);
    }
    private void saveComplete(final String str) {
        if (this.saveFiche(str)) {
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
    }
}
