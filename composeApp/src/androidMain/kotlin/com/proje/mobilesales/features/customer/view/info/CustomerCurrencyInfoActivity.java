package com.proje.mobilesales.features.customer.view.info;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.widget.AppCompatButton;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.enums.CurrselDetailType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.searchdialog.BaseSearchDialogCompat;
import com.proje.mobilesales.core.searchdialog.SearchResultListener;
import com.proje.mobilesales.core.searchdialog.SimpleSearchDialogCompat;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.widget.FichePropertyTextView;
import com.proje.mobilesales.databinding.ActivityCustomerCurrencyInfoBinding;
import com.proje.mobilesales.features.collections.casefiche.model.CaseFiche;
import com.proje.mobilesales.features.customer.repository.BaseCustomerRepository;
import com.proje.mobilesales.features.model.BarcodeResult;
import com.proje.mobilesales.features.model.BaseSearchModel;
import com.proje.mobilesales.features.model.FicheRefProp;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static kotlin.jvm.internal.Intrinsics.checkNotNull;
import static kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException;

public final class CustomerCurrencyInfoActivity extends BaseErpActivity {
    public static final int CLEAR_CUSTOMER_CURRENCY_INFO = 104;
    public static final Companion Companion = new Companion (null);
    private static final String STATE_CASE_FICHE = "state:caseFiche";
    private static final String STATE_CUSTOMER_REF = "state:customerRef";
    private static final String STATE_FICHE_MODE = "state:caseFicheMode";
    private static final String TAG = CustomerCurrencyInfoActivity.class.getName ();
    private final CustomerInfoViewModel viewModel;
    private ActivityCustomerCurrencyInfoBinding binding;
    private AppCompatButton btnClear;
    private AppCompatButton btnOk;
    private CaseFiche caseFiche;
    private ArrayList<BaseSearchModel> currencyModels;
    private FicheMode ficheMode;
    private String localCurrCode;
    private int localCurrType;
    private  BaseSearchModel selectedCurrencyModel;
    private double selectedRate;
    private double total;
    private double trRate;
    private FichePropertyTextView txtCurrency;
    private FichePropertyTextView txtCurrencyAmount;
    private FichePropertyTextView txtRate;
    private FichePropertyTextView txtTotal;
    public CustomerCurrencyInfoActivity() {
        BaseCustomerRepository baseCustomerRepository = new BaseCustomerRepository ();
         this.viewModel = new CustomerInfoViewModel (baseCustomerRepository);
    }
    public static void savelambda7(DialogInterface dialogInterface, int i) {
    }
    public static void onCreatelambda0(CustomerCurrencyInfoActivity customerCurrencyInfoActivity, View view) {
        Intrinsics.checkNotNullParameter (customerCurrencyInfoActivity, "this0");
        customerCurrencyInfoActivity.save ();
    }
    public static void onCreatelambda1(CustomerCurrencyInfoActivity customerCurrencyInfoActivity, View view) {
        Intrinsics.checkNotNullParameter (customerCurrencyInfoActivity, "this0");
        customerCurrencyInfoActivity.clear ();
    }
    public static void onCreatelambda2(CustomerCurrencyInfoActivity customerCurrencyInfoActivity, View view) {
        Intrinsics.checkNotNullParameter (customerCurrencyInfoActivity, "this0");
        try {
            ArrayList<BaseSearchModel> currencySearchModelsFromCursor = customerCurrencyInfoActivity.getCurrencySearchModelsFromCursor ();
            customerCurrencyInfoActivity.currencyModels = currencySearchModelsFromCursor;
            checkNotNull (currencySearchModelsFromCursor);
            if (0 == currencySearchModelsFromCursor.size ()) {
                Toast.makeText (customerCurrencyInfoActivity, customerCurrencyInfoActivity.getString (R.string.empty_text), Toast.LENGTH_SHORT).show ();
            } else {
                new SimpleSearchDialogCompat(customerCurrencyInfoActivity, "", "", null, customerCurrencyInfoActivity.currencyModels,
                        new MyMyBaseSearchModelSearchResultListener(customerCurrencyInfoActivity)).show();
            }
        } catch (Exception e) {
            Log.e (TAG, "createSearchDialogCursorSales: ", e);
        }
    }
    public static boolean setSelectedCurrencyModellambda4(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter (function1, "tmp0");
        return ((Boolean) function1.invoke (obj)).booleanValue ();
    }
    public static void clearlambda5(CustomerCurrencyInfoActivity customerCurrencyInfoActivity, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter (customerCurrencyInfoActivity, "this0");
        Intrinsics.checkNotNullParameter (dialogInterface, "dialog");
        dialogInterface.dismiss ();
        customerCurrencyInfoActivity.setResult (104, new Intent ());
        customerCurrencyInfoActivity.finish ();
    }
    public static void clearlambda6(DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter (dialogInterface, "dialog");
        dialogInterface.dismiss ();
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(Bundle r41) {
        super.onCreate(r41);
        throw new UnsupportedOperationException ("com.proje.mobilesales.features.customer.view.info.CustomerCurrencyInfoActivity.onCreate(android.os.Bundle):void");
    }
    private void loadInfo() {
        String str;
        CaseFiche caseFiche = this.caseFiche;
        checkNotNull (caseFiche);
        FichePropertyTextView fichePropertyTextView = null;
        if (0 < caseFiche.getClCurr ().getLogicalRef ()) {
            CaseFiche caseFiche2 = this.caseFiche;
            if (null != caseFiche2) {
                FichePropertyTextView fichePropertyTextView2 = this.txtCurrency;
                if (null == fichePropertyTextView2) {
                    throwUninitializedPropertyAccessException ("txtCurrency");
                    fichePropertyTextView2 = null;
                }
                TextView txtValue = fichePropertyTextView2.getTxtValue ();
                if (TextUtils.isEmpty (caseFiche2.getClCurr ().getDefinition ())) {
                    str = this.viewModel.getCurrCode (caseFiche2.getClCurr ().getLogicalRef ());
                } else {
                    str = caseFiche2.getClCurr ().getDefinition ();
                }
                txtValue.setText (str);
                FichePropertyTextView fichePropertyTextView3 = this.txtRate;
                if (null == fichePropertyTextView3) {
                    throwUninitializedPropertyAccessException ("txtRate");
                    fichePropertyTextView3 = null;
                }
                fichePropertyTextView3.getTxtValue ().setText (StringUtils.formatDouble (caseFiche2.getClRate ()));
                if (0.0d != caseFiche2.getClRate ()) {
                    FichePropertyTextView fichePropertyTextView4 = this.txtCurrencyAmount;
                    if (null == fichePropertyTextView4) {
                        throwUninitializedPropertyAccessException ("txtCurrencyAmount");
                        fichePropertyTextView4 = null;
                    }
                    fichePropertyTextView4.getTxtValue ().setText (StringUtils.formatDouble (caseFiche2.getTotal ().getDefinitionDouble () / caseFiche2.getClRate ()));
                }
                this.selectedRate = caseFiche2.getClRate ();
                this.selectedCurrencyModel = new BaseSearchModel (caseFiche2.getClCurr ().getLogicalRef (), caseFiche2.getClCurr ().getDefinition (), false);
                calculateTotal();
                FichePropertyTextView fichePropertyTextView5 = this.txtTotal;
                if (null == fichePropertyTextView5) {
                    throwUninitializedPropertyAccessException ("txtTotal");
                } else {
                    fichePropertyTextView = fichePropertyTextView5;
                }
                fichePropertyTextView.getTxtValue ().setText (StringUtils.formatDouble (this.total));
                calculateCurrencyTotals();
                return;
            }
            return;
        }
        calculateTotal();
        FichePropertyTextView fichePropertyTextView6 = this.txtTotal;
        if (null == fichePropertyTextView6) {
            throwUninitializedPropertyAccessException ("txtTotal");
        } else {
            fichePropertyTextView = fichePropertyTextView6;
        }
        fichePropertyTextView.getTxtValue ().setText (StringUtils.formatDouble (this.total) + ' ' + this.localCurrCode);
        int clCardCurrency = this.viewModel.getClCardCurrency (this.customerRef);
        if (0 == clCardCurrency) {
            setSelectedCurrencyModel(this.localCurrType);
        } else {
            setSelectedCurrencyModel(clCardCurrency);
        }
        calculateCurrencyTotals();
    }
    private void calculateTotal() {
        int i;
        double d;
        if (this.viewModel.getBaseErp ().getCaseCashCurrselDetails () != CurrselDetailType.TRANSACTION_CURRENCY.getType ()) {
            i = 0;
        } else {
            CaseFiche caseFiche = this.caseFiche;
            checkNotNull (caseFiche);
            i = caseFiche.getCurrType ().getLogicalRef ();
        }
        if (0 < i) {
            if (i == this.localCurrType) {
                d = 1.0d;
            } else {
                d = this.viewModel.getCurrRateWithoutDefaultValue (i);
            }
            this.trRate = d;
            CaseFiche caseFiche2 = this.caseFiche;
            checkNotNull (caseFiche2);
            this.total = caseFiche2.getTotal ().getDefinitionDouble () * this.trRate;
            return;
        }
        CaseFiche caseFiche3 = this.caseFiche;
        checkNotNull (caseFiche3);
        this.total = caseFiche3.getTotal ().getDefinitionDouble ();
    }
    public void calculateCurrencyTotals() {
        double d = this.selectedRate;
        double d2 = 0.0d;
        if (0.0d != d) {
            d2 = this.total / d;
        }
        FichePropertyTextView fichePropertyTextView = this.txtCurrencyAmount;
        if (fichePropertyTextView == null) {
            throwUninitializedPropertyAccessException ("txtCurrencyAmount");
            fichePropertyTextView = null;
        }
        fichePropertyTextView.getTxtValue ().setText (StringUtils.formatDouble (d2));
    }
    public void setSelectedCurrencyModel(int i) {
        ArrayList<BaseSearchModel> arrayList = this.currencyModels;
        checkNotNull (arrayList);
        List list = (List) arrayList.stream ().filter (new Predicate () {
            public boolean test(Object obj) {
                return CustomerCurrencyInfoActivity.setSelectedCurrencyModellambda4(this,
                        obj);
            }
        }).collect (Collectors.toList ());
        if (0 < list.size ()) {
            this.selectedCurrencyModel = (BaseSearchModel) list.get (0);
            FichePropertyTextView fichePropertyTextView = this.txtCurrency;
            FichePropertyTextView fichePropertyTextView2 = null;
            if (null == fichePropertyTextView) {
                throwUninitializedPropertyAccessException ("txtCurrency");
                fichePropertyTextView = null;
            }
            TextView txtValue = fichePropertyTextView.getTxtValue ();
            BaseSearchModel baseSearchModel = this.selectedCurrencyModel;
            checkNotNull (baseSearchModel);
            txtValue.setText (baseSearchModel.getTitle ());
            this.selectedRate = i == this.localCurrType ? 1.0d : this.viewModel.getCurrRateWithoutDefaultValue (i);
            FichePropertyTextView fichePropertyTextView3 = this.txtRate;
            if (null == fichePropertyTextView3) {
                throwUninitializedPropertyAccessException ("txtRate");
            } else {
                fichePropertyTextView2 = fichePropertyTextView3;
            }
            fichePropertyTextView2.getTxtValue ().setText (StringUtils.convertDoubleToString (Double.valueOf (this.selectedRate)));
        }
    }
    private static boolean setSelectedCurrencyModellambda4(Predicate predicate, Object obj) {
        return false;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter (menuItem, "item");
        if (16908332 == menuItem.getItemId ()) {
            cancel();
        }
        return super.onOptionsItemSelected (menuItem);
    }
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter (bundle, "outState");
        bundle.putParcelable (STATE_CASE_FICHE, this.caseFiche);
        bundle.putSerializable (STATE_FICHE_MODE, this.ficheMode);
        bundle.putSerializable ("state:customerRef", Integer.valueOf (this.customerRef));
        super.onSaveInstanceState (bundle);
    }
    public void lambdabarcodeResult0(BarcodeResult f1, BarcodeResult f2) {

    }
    private void cancel() {
        this.setResult(0, new Intent ());
        this.finish();
    }
    @SuppressLint("ResourceType")
    private void clear() {
        this.mBaseAlertDialogBuilder.setMessage (this.getString(R.string.str_customer_currency_info_will_be_cleared)).setCancelable (false).setPositiveButton (17039370, new DialogInterface.OnClickListener () {
            public void onClick(DialogInterface dialogInterface, int i) {
                CustomerCurrencyInfoActivity.clearlambda5(CustomerCurrencyInfoActivity.this, dialogInterface, i);
            }
        }).setNegativeButton (17039360, new DialogInterface.OnClickListener () {
            public void onClick(DialogInterface dialogInterface, int i) {
                CustomerCurrencyInfoActivity.clearlambda6(dialogInterface, i);
            }
        }).create ().show ();
    }
    @SuppressLint("GestureBackNavigation")
    public void onBackPressed() {
        super.onBackPressed ();
        cancel();
    }
    @SuppressLint("ResourceType")
    private void save() {
        if (0.0d == selectedRate) {
            this.mBaseAlertDialogBuilder.setMessage (this.getString(R.string.str_exchange_rate_not_found_for)).setCancelable (false).setPositiveButton (17039370, new DialogInterface.OnClickListener () {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CustomerCurrencyInfoActivity.savelambda7(dialogInterface, i);
                }
            }).create ().show ();
            return;
        }
        CaseFiche caseFiche = this.caseFiche;
        if (null != caseFiche) {
            BaseSearchModel baseSearchModel = this.selectedCurrencyModel;
            checkNotNull (baseSearchModel);
            int logicalRef = baseSearchModel.getLogicalRef ();
            BaseSearchModel baseSearchModel2 = this.selectedCurrencyModel;
            checkNotNull (baseSearchModel2);
            caseFiche.setClCurr (new FicheRefProp (logicalRef, -1, baseSearchModel2.getTitle ()));
        }
        CaseFiche caseFiche2 = this.caseFiche;
        if (null != caseFiche2) {
            caseFiche2.setClRate (this.selectedRate);
        }
        Intent intent = new Intent ();
        intent.putExtra (IntentExtraName.EXTRA_CASE_FICHE, this.caseFiche);
        this.setResult(-1, intent);
        this.finish();
    }
    @SuppressLint("ResourceType")
    private ArrayList<BaseSearchModel> getCurrencySearchModelsFromCursor() {
        throw new UnsupportedOperationException ("com.proje.mobilesales.features.customer.view.info.CustomerCurrencyInfoActivity.getCurrencySearchModelsFromCursor():java.util.ArrayList");
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this ();
        }

        private Companion() {
        }
    }
    private static abstract class BaseSearchModelSearchResultListener implements SearchResultListener<BaseSearchModel> {
        final CustomerCurrencyInfoActivity this0;
        public BaseSearchModelSearchResultListener(CustomerCurrencyInfoActivity customerCurrencyInfoActivity) {
            super();
            this.this0 = customerCurrencyInfoActivity;
        }
        public void onSelected(BaseSearchDialogCompat<?> baseSearchDialogCompat, BaseSearchModel baseSearchModel, int i) {
            Intrinsics.checkNotNullParameter (baseSearchDialogCompat, "dialog");
            Intrinsics.checkNotNullParameter (baseSearchModel, "item");
            FichePropertyTextView fichePropertyTextView = this.this0.txtCurrency;
            if (fichePropertyTextView == null) {
                throwUninitializedPropertyAccessException ("txtCurrency");
                fichePropertyTextView = null;
            }
            fichePropertyTextView.getTxtValue ().setText (baseSearchModel.getTitle ());
            this.this0.setSelectedCurrencyModel (baseSearchModel.getLogicalRef ());
            this.this0.calculateCurrencyTotals ();
            baseSearchDialogCompat.dismiss ();
        }
        public abstract void onCancelled(BaseSearchDialogCompat baseSearchDialogCompat);
    }
    private static abstract class MyBaseSearchModelSearchResultListener extends BaseSearchModelSearchResultListener {
        public MyBaseSearchModelSearchResultListener(CustomerCurrencyInfoActivity customerCurrencyInfoActivity) {
            super(customerCurrencyInfoActivity);
        }
        @Override
        public abstract void onCancelled(BaseSearchDialogCompat baseSearchDialogCompat);
    }
    private static abstract class MyMyBaseSearchModelSearchResultListener extends MyBaseSearchModelSearchResultListener {
        public MyMyBaseSearchModelSearchResultListener(CustomerCurrencyInfoActivity customerCurrencyInfoActivity) {
            super(customerCurrencyInfoActivity);
        }
        @Override
        public void onCancelled(BaseSearchDialogCompat baseSearchDialogCompat) {
        }
    }

}
