package com.proje.mobilesales.features.customer.view.info;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivityPreferences;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.core.enums.ColType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.customer.model.CustomerCampaignPoint;
import com.proje.mobilesales.features.customer.repository.BaseCustomerRepository;
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.*;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;
import static kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException;

public final class CustomerInfoActivity extends BaseErpActivityPreferences {
    public static final Companion Companion = new Companion (null);
    public static final String EXTRAS_CLREF = "clRef";
    private static final int BakiyeBilgileriniGuncelle = 0;
    private static final int CustomerCampaignInfo = 1;
    private final CoroutineScope customScope = CoroutineScopeKt.CoroutineScope (Dispatchers.getMain ().plus (JobKt.Job (null)));
    private final BaseCustomerRepository repository;
    private final CustomerInfoViewModel viewModel;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private int clRef;

    public CustomerInfoActivity() {
        BaseCustomerRepository baseCustomerRepository = new BaseCustomerRepository ();
        this.repository = baseCustomerRepository;
        this.viewModel = new CustomerInfoViewModel (baseCustomerRepository);
    }

    public static void showCustomerCampaignPointlambda0(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss ();
    }

    public int getClRef() {
        return this.clRef;
    }

    public void setClRef(int i) {
        this.clRef = i;
    }

    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        if (null != progressDialogBuilder) {
            return progressDialogBuilder;
        }
        throwUninitializedPropertyAccessException ("mProgressDialogBuilder");
        return null;
    }

    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        Intrinsics.checkNotNullParameter (progressDialogBuilder, "<set-?>");
        this.mProgressDialogBuilder = progressDialogBuilder;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate (bundle);
        com.proje.mobilesales.core.interfaces.di.ActivityComponent activityComponent = this.getActivityComponent();
        if (null != activityComponent) {
            activityComponent.inject (this);
        }
        Activity activity = ContextUtils.getmActivity ();
        checkNotNull (activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMProgressDialogBuilder(new ProgressDialogBuilder.Impl (this, (BaseInjectableActivity) activity));
        this.addPreferencesFromResource(R.xml.customerinfo);
        int intExtra = this.getIntent().getIntExtra (EXTRAS_CLREF, 0);
        this.clRef = intExtra;
        if (0 == intExtra) {
            this.finish();
        }
        updateCustomerInfo();
    }

    private void updateCustomerInfo() {
        Preference findPreference = this.findPreference("pCari");
        Preference findPreference2 = this.findPreference("pTicariIslemGrubu");
        Preference findPreference3 = this.findPreference("pOzelKodu1");
        Preference findPreference4 = this.findPreference("pOzelKodu2");
        Preference findPreference5 = this.findPreference("pOzelKodu3");
        Preference findPreference6 = this.findPreference("pOzelKodu4");
        Preference findPreference7 = this.findPreference("pOzelKodu5");
        Preference findPreference8 = this.findPreference("pVergiNo");
        Preference findPreference9 = this.findPreference("pVergiDaire");
        Preference findPreference10 = this.findPreference("pEmail");
        Preference findPreference11 = this.findPreference("pTelefon");
        Preference findPreference12 = this.findPreference("pFax");
        Preference findPreference13 = this.findPreference("pSehir");
        Preference findPreference14 = this.findPreference("pIlce");
        Preference findPreference15 = this.findPreference("pAdres");
        Preference findPreference16 = this.findPreference("pBorcBakiye");
        Preference findPreference17 = this.findPreference("pAlacakBakiye");
        Preference findPreference18 = this.findPreference("pToplamBakiye");
        Preference findPreference19 = this.findPreference("pIndirimOrani");
        Preference findPreference20 = this.findPreference("pIlgili1");
        Preference findPreference21 = this.findPreference("pIlgili2");
        Preference findPreference22 = this.findPreference("pIlgili3");
        Preference findPreference23 = this.findPreference("pAcikHesap");
        Preference findPreference24 = this.findPreference("pKendiCekSenet");
        Preference findPreference25 = this.findPreference("pMusteriCekSenet");
        Preference findPreference26 = this.findPreference("pCiroCekSenet");
        Preference findPreference27 = this.findPreference("pIrsaliye");
        Preference findPreference28 = this.findPreference("pIrsaliyeOneri");
        Preference findPreference29 = this.findPreference("pSiparisSevk");
        Preference findPreference30 = this.findPreference("pSiparisOneri");
        CustomerInfoViewModel customerInfoViewModel = this.viewModel;
        Cursor rowQueryInReadableDatabase = customerInfoViewModel.getRowQueryInReadableDatabase ("SELECT * FROM CLCARD WHERE LOGICALREF=" + this.clRef, null);
        if (rowQueryInReadableDatabase.moveToFirst ()) {
            CustomerInfoViewModel customerInfoViewModel2 = this.viewModel;
            ColType colType = ColType.metin;
            Object dBVal = customerInfoViewModel2.getDBVal (rowQueryInReadableDatabase, "DEFINITION_", colType);
            checkNotNull (dBVal, "null cannot be cast to non-null type kotlin.String");
            findPreference.setTitle ((String) dBVal);
            Object dBVal2 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "CODE", colType);
            checkNotNull (dBVal2, "null cannot be cast to non-null type kotlin.String");
            findPreference.setSummary ((String) dBVal2);
            Object dBVal3 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "ADDR1", colType);
            checkNotNull (dBVal3, "null cannot be cast to non-null type kotlin.String");
            findPreference15.setSummary ((String) dBVal3);
            Object dBVal4 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "TELNRS1", colType);
            checkNotNull (dBVal4, "null cannot be cast to non-null type kotlin.String");
            findPreference11.setSummary ((String) dBVal4);
            Object dBVal5 = this.viewModel.getDBVal (rowQueryInReadableDatabase, DailyInfo.CREDIT, colType);
            checkNotNull (dBVal5, "null cannot be cast to non-null type kotlin.String");
            findPreference17.setSummary ((String) dBVal5);
            Object dBVal6 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "DEBIT", colType);
            checkNotNull (dBVal6, "null cannot be cast to non-null type kotlin.String");
            findPreference16.setSummary ((String) dBVal6);
            Object dBVal7 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "EMAILADDR", colType);
            checkNotNull (dBVal7, "null cannot be cast to non-null type kotlin.String");
            findPreference10.setSummary ((String) dBVal7);
            Object dBVal8 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "FAXNR", colType);
            checkNotNull (dBVal8, "null cannot be cast to non-null type kotlin.String");
            findPreference12.setSummary ((String) dBVal8);
            Object dBVal9 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "TRADINGGRP", colType);
            checkNotNull (dBVal9, "null cannot be cast to non-null type kotlin.String");
            findPreference2.setSummary ((String) dBVal9);
            Object dBVal10 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "DISCRATE", colType);
            checkNotNull (dBVal10, "null cannot be cast to non-null type kotlin.String");
            findPreference19.setSummary ((String) dBVal10);
            Object dBVal11 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "CITY", colType);
            checkNotNull (dBVal11, "null cannot be cast to non-null type kotlin.String");
            findPreference13.setSummary ((String) dBVal11);
            Object dBVal12 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "TOWN", colType);
            checkNotNull (dBVal12, "null cannot be cast to non-null type kotlin.String");
            findPreference14.setSummary ((String) dBVal12);
            Object dBVal13 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "BAKIYE", colType);
            checkNotNull (dBVal13, "null cannot be cast to non-null type kotlin.String");
            findPreference18.setSummary ((String) dBVal13);
            Object dBVal14 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "SPECODE", colType);
            checkNotNull (dBVal14, "null cannot be cast to non-null type kotlin.String");
            findPreference3.setSummary ((String) dBVal14);
            Object dBVal15 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "SPECODE2", colType);
            checkNotNull (dBVal15, "null cannot be cast to non-null type kotlin.String");
            findPreference4.setSummary ((String) dBVal15);
            Object dBVal16 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "SPECODE3", colType);
            checkNotNull (dBVal16, "null cannot be cast to non-null type kotlin.String");
            findPreference5.setSummary ((String) dBVal16);
            Object dBVal17 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "SPECODE4", colType);
            checkNotNull (dBVal17, "null cannot be cast to non-null type kotlin.String");
            findPreference6.setSummary ((String) dBVal17);
            Object dBVal18 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "SPECODE5", colType);
            checkNotNull (dBVal18, "null cannot be cast to non-null type kotlin.String");
            findPreference7.setSummary ((String) dBVal18);
            Object dBVal19 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "TAXNR", colType);
            checkNotNull (dBVal19, "null cannot be cast to non-null type kotlin.String");
            findPreference8.setSummary ((String) dBVal19);
            Object dBVal20 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "TAXOFFICE", colType);
            checkNotNull (dBVal20, "null cannot be cast to non-null type kotlin.String");
            findPreference9.setSummary ((String) dBVal20);
            Object dBVal21 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "INCHARGE", colType);
            checkNotNull (dBVal21, "null cannot be cast to non-null type kotlin.String");
            findPreference20.setSummary ((String) dBVal21);
            Object dBVal22 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "INCHARGE2", colType);
            checkNotNull (dBVal22, "null cannot be cast to non-null type kotlin.String");
            findPreference21.setSummary ((String) dBVal22);
            Object dBVal23 = this.viewModel.getDBVal (rowQueryInReadableDatabase, "INCHARGE3", colType);
            checkNotNull (dBVal23, "null cannot be cast to non-null type kotlin.String");
            findPreference22.setSummary ((String) dBVal23);
            findPreference20.setTitle (this.getString(R.string.str_incharge) + " -1");
            findPreference21.setTitle (this.getString(R.string.str_incharge) + " -2");
            findPreference22.setTitle (this.getString(R.string.str_incharge) + " -3");
            checkNotNull (findPreference23);
            setRisk(findPreference23, this.viewModel.getDBVal (rowQueryInReadableDatabase, "ACCRISKLIMIT", colType).toString (), this.viewModel.getDBVal (rowQueryInReadableDatabase, "ACCRSKBLNCED", colType).toString (), this.viewModel.getDBVal (rowQueryInReadableDatabase, "ACCRISKTOTAL", colType).toString ());
            checkNotNull (findPreference24);
            setRisk(findPreference24, this.viewModel.getDBVal (rowQueryInReadableDatabase, "MYCSRISKLIMIT", colType).toString (), this.viewModel.getDBVal (rowQueryInReadableDatabase, "MYCSRSKBLNCED", colType).toString (), this.viewModel.getDBVal (rowQueryInReadableDatabase, "MYCSRISKTOTAL", colType).toString ());
            checkNotNull (findPreference25);
            setRisk(findPreference25, this.viewModel.getDBVal (rowQueryInReadableDatabase, "CSTCSRISKLIMIT", colType).toString (), this.viewModel.getDBVal (rowQueryInReadableDatabase, "CSTCSRSKBLNCED", colType).toString (), this.viewModel.getDBVal (rowQueryInReadableDatabase, "CSTCSOWNRISKTOTAL", colType).toString ());
            checkNotNull (findPreference26);
            setRisk(findPreference26, this.viewModel.getDBVal (rowQueryInReadableDatabase, "CSTCSCIRORISKLIMIT", colType).toString (), this.viewModel.getDBVal (rowQueryInReadableDatabase, "CSTCSCIRORSKBLNCED", colType).toString (), this.viewModel.getDBVal (rowQueryInReadableDatabase, "CSTCSCIRORISKTOTAL", colType).toString ());
            checkNotNull (findPreference27);
            setRisk(findPreference27, this.viewModel.getDBVal (rowQueryInReadableDatabase, "DESPRISKLIMIT", colType).toString (), this.viewModel.getDBVal (rowQueryInReadableDatabase, "DESPRSKBLNCED", colType).toString (), this.viewModel.getDBVal (rowQueryInReadableDatabase, "DESPRISKTOTAL", colType).toString ());
            checkNotNull (findPreference28);
            setRisk(findPreference28, this.viewModel.getDBVal (rowQueryInReadableDatabase, "DESPRISKLIMITSUG", colType).toString (), this.viewModel.getDBVal (rowQueryInReadableDatabase, "DESPRSKBLNCEDSUG", colType).toString (), this.viewModel.getDBVal (rowQueryInReadableDatabase, "DESPRISKTOTALSUG", colType).toString ());
            checkNotNull (findPreference29);
            setRisk(findPreference29, this.viewModel.getDBVal (rowQueryInReadableDatabase, "ORDRISKLIMIT", colType).toString (), this.viewModel.getDBVal (rowQueryInReadableDatabase, "ORDRSKBLNCED", colType).toString (), this.viewModel.getDBVal (rowQueryInReadableDatabase, "ORDRISKTOTAL", colType).toString ());
            checkNotNull (findPreference30);
            setRisk(findPreference30, this.viewModel.getDBVal (rowQueryInReadableDatabase, "ORDRISKLIMITSUGG", colType).toString (), this.viewModel.getDBVal (rowQueryInReadableDatabase, "ORDRSKBLNCEDSUG", colType).toString (), this.viewModel.getDBVal (rowQueryInReadableDatabase, "ORDRISKTOTALSUGG", colType).toString ());
        }
        rowQueryInReadableDatabase.close ();
    }

    private void setRisk(Preference preference, String str, String str2, String str3) {
        preference.setSummary (((this.getString(R.string.str_limit) + " : " + StringUtils.formatDouble (str) + " \n") + this.getString(R.string.str_closed_risk) + " : " + StringUtils.formatDouble (str2) + " \n") + this.getString(R.string.str_total_quantity) + " : " + StringUtils.formatDouble (str3));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter (menu, "menu");
        menu.add (0, 0, 0, this.getString(R.string.str_customer_information_update)).setIcon (android.R.drawable.ic_menu_recent_history);
        return super.onCreateOptionsMenu (menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter (menuItem, "item");
        int itemId = menuItem.getItemId ();
        if (0 == itemId) {
            getMProgressDialogBuilder().setMessage (this.getString(R.string.str_customer_information_updating)).show ();
            Job unused = BuildersKt.launch (this.customScope, null, null, new CustomerInfoActivityonOptionsItemSelected1 (this, null));
        } else if (1 == itemId) {
            getMProgressDialogBuilder().setMessage (this.getString(R.string.type_get_customer_campaign_point)).show ();
            this.viewModel.getCustomerCampaignPoint (this.clRef);
        } else if (16908332 == itemId) {
            this.finish();
        }
        return super.onOptionsItemSelected (menuItem);
    }

    public void onStart() {
        super.onStart ();
        EventBus.getDefault ().register (this);
    }

    public void onStop() {
        EventBus.getDefault ().unregister (this);
        super.onStop ();
    }

    public void baseResultEvent(BaseSelectResult<?> baseSelectResult) {
        Intrinsics.checkNotNullParameter (baseSelectResult, "baseResult");
        getMProgressDialogBuilder().dismiss ();
        if (!baseSelectResult.isSuccess ()) {
            Toast.makeText (this, baseSelectResult.getErrorString (), Toast.LENGTH_LONG).show ();
        } else if (baseSelectResult.getProcessType () == ProcessType.GETCLCARD) {
            Toast.makeText (this, this.getString(R.string.str_customer_information_updated), Toast.LENGTH_LONG).show ();
            updateCustomerInfo();
        } else if (baseSelectResult.getProcessType () != ProcessType.GETCUSTOMERCAMPAIGN) {
        } else {
            if (0 < baseSelectResult.getDataList ().size ()) {
                List<?> dataList = baseSelectResult.getDataList ();
                checkNotNull (dataList, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.customer.model.CustomerCampaignPoint>");
                showCustomerCampaignPoint(dataList);
                return;
            }
            Toast.makeText (this, this.getString(R.string.str_customer_campaing_detail_not_found), Toast.LENGTH_LONG).show ();
        }
    }

    private void showCustomerCampaignPoint(List<CustomerCampaignPoint> list) {
        try {
            View inflate = LayoutInflater.from (this).inflate (R.layout.fichelist, null);
            AlertDialog.Builder builder = new AlertDialog.Builder (this);
            builder.setTitle (this.getString(R.string.str_customer_campaing_detail_title));
            builder.setView (inflate);
            View findViewById = inflate.findViewById (R.id.list_fiche_list);
            checkNotNull (findViewById, "null cannot be cast to non-null type android.widget.ListView");
            ListView listView = (ListView) findViewById;
            String[] strArr = {"CODE", "NAME", "AMOUNT", "CAMPOINT"};
            int[] iArr = {R.id.FVCODE, R.id.FVNAME, R.id.FVAMOUNT, R.id.FVPOINT};
            ArrayList arrayList = new ArrayList ();
            for (CustomerCampaignPoint customerCampaignPoint : list) {
                HashMap hashMap = new HashMap ();
                hashMap.put ("CODE", customerCampaignPoint.getCampaignCode ());
                hashMap.put ("NAME", customerCampaignPoint.getCampaignName ());
                hashMap.put ("AMOUNT", StringUtils.formatDouble (customerCampaignPoint.getAmount ()));
                hashMap.put ("CAMPOINT", StringUtils.formatDouble (customerCampaignPoint.getCampaignPoint ()));
                arrayList.add (hashMap);
            }
            listView.setAdapter (new SimpleAdapter (this, arrayList, R.layout.campaign_info, strArr, iArr));
            builder.setPositiveButton (android.R.string.ok, new DialogInterface.OnClickListener () {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CustomerInfoActivity.showCustomerCampaignPointlambda0(dialogInterface, i);
                }
            });
            builder.show ();
        } catch (Exception e) {
            Log.e ("AA", "showCustomerCampaignPoint: ", e);
        }
    }

    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this ();
        }

        private Companion() {
        }
    }
}
