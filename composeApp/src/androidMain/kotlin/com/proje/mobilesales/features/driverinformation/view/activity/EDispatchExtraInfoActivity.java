package com.proje.mobilesales.features.driverinformation.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.searchdialog.BaseSearchDialogCompat;
import com.proje.mobilesales.core.searchdialog.SearchResultListener;
import com.proje.mobilesales.core.searchdialog.SimpleSearchDialogCompat;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.widget.FichePropertyEditView;
import com.proje.mobilesales.features.customer.model.Customer;
import com.proje.mobilesales.features.customer.view.list.CustomerListActivity;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;
import com.proje.mobilesales.features.driverinformation.repository.EDispatchAdditionalInformationRepository;
import com.proje.mobilesales.features.driverinformation.viewmodel.EDispatchAdditionalInformationViewModel;
import com.proje.mobilesales.features.model.BaseSearchModel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class EDispatchExtraInfoActivity extends BaseErpActivity {
    public static final int CUSTOMER_ACCOUNT_CODE = 999;
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_SALES_FICHE_MODE;
    private static final String STATE_SALES_EDISPATCH_ADDITIONAL = "state:salesEDispatchAdditional";
    private static final String STATE_SALES_FICHE_MODE = "state:salesFicheMode";
    private static final String TAG;
    private EDispatchAdditionalInfo additionalInfo;
    private FichePropertyEditView city;
    private FichePropertyEditView country;
    private FichePropertyEditView county;
    private FicheMode ficheMode;
    private FichePropertyEditView firstDriverDescription;
    private FichePropertyEditView firstDriverIdentityNr;
    private FichePropertyEditView firstDriverName;
    private FichePropertyEditView firstDriverSurname;
    private FichePropertyEditView firstTrailerPlate;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private FichePropertyEditView plateNr;
    private FichePropertyEditView postalCode;
    private final EDispatchAdditionalInformationRepository repository;
    private FichePropertyEditView taxNr;
    private FichePropertyEditView title;
    private final EDispatchAdditionalInformationViewModel viewModel;

    public EDispatchExtraInfoActivity() {
        EDispatchAdditionalInformationRepository eDispatchAdditionalInformationRepository = new EDispatchAdditionalInformationRepository();
        this.repository = eDispatchAdditionalInformationRepository;
        this.viewModel = new EDispatchAdditionalInformationViewModel(eDispatchAdditionalInformationRepository);
    }

    public EDispatchAdditionalInformationRepository getRepository() {
        return this.repository;
    }

    public EDispatchAdditionalInformationViewModel getViewModel() {
        return this.viewModel;
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        Context context = ContextUtils.getmContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context, (BaseInjectableActivity) activity);
        if (bundle != null) {
            this.additionalInfo = bundle.getParcelable(STATE_SALES_EDISPATCH_ADDITIONAL);
            this.ficheMode = FicheMode.values()[bundle.getInt(STATE_SALES_FICHE_MODE, 0)];
        } else {
            this.additionalInfo = getIntent().getParcelableExtra(IntentExtraName.EXTRA_EDESPATCH_EXTRA_INFO);
            this.ficheMode = (FicheMode) getIntent().getSerializableExtra(EXTRA_SALES_FICHE_MODE);
        }
        if (this.additionalInfo == null) {
            this.additionalInfo = new EDispatchAdditionalInfo();
        }
        setContentView(R.layout.activity_e_despatch_extra_info);
        setToolbar();
        ActionBar supportActionBar = getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setTitle(R.string.menu_sales_eDespatch_Additional);
        this.plateNr = findViewById(R.id.fpe_plateNr);
        this.taxNr = findViewById(R.id.fpe_TaxNr);
        this.title = findViewById(R.id.fpe_Title);
        this.county = findViewById(R.id.fpe_County);
        this.city = findViewById(R.id.fpe_City);
        this.country = findViewById(R.id.fpe_Country);
        this.postalCode = findViewById(R.id.fpe_postalCode);
        this.firstDriverName = findViewById(R.id.fpe_firstDriverName);
        this.firstDriverSurname = findViewById(R.id.fpe_firstDriverSurname);
        this.firstDriverDescription = findViewById(R.id.fpe_firstDriverDescription);
        this.firstDriverIdentityNr = findViewById(R.id.fpe_firstDriverIdentityNr);
        this.firstTrailerPlate = findViewById(R.id.fpe_firstTrailerPlate);
        FichePropertyEditView fichePropertyEditView = this.taxNr;
        EditText edtValue = fichePropertyEditView != null ? fichePropertyEditView.getEdtValue() : null;
        if (edtValue != null) {
            edtValue.setInputType(2);
        }
        FichePropertyEditView fichePropertyEditView2 = this.postalCode;
        EditText edtValue2 = fichePropertyEditView2 != null ? fichePropertyEditView2.getEdtValue() : null;
        if (edtValue2 != null) {
            edtValue2.setInputType(2);
        }
        FichePropertyEditView fichePropertyEditView3 = this.firstDriverIdentityNr;
        EditText edtValue3 = fichePropertyEditView3 != null ? fichePropertyEditView3.getEdtValue() : null;
        if (edtValue3 != null) {
            edtValue3.setInputType(2);
        }
        bindExtraInfo();
        setEnabled();
        if (this.ficheMode == FicheMode.ANALYSE) {
            return;
        }
        createImageButton(this.taxNr).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EDispatchExtraInfoActivity.onCreatelambda0(EDispatchExtraInfoActivity.this, view);
            }
        });
        createImageButton(this.country).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EDispatchExtraInfoActivity.onCreatelambda1(EDispatchExtraInfoActivity.this, view);
            }
        });
    }

    public static void onCreatelambda0(EDispatchExtraInfoActivity this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intent intent = new Intent(this0, CustomerListActivity.class);
        intent.putExtra(CustomerListActivity.EXTRA_CUSTOMER_SELECT_TYPE, 2);
        this0.startActivityForResult(intent, 999);
    }
    public static void onCreatelambda1(final EDispatchExtraInfoActivity this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        try {
            ArrayList<BaseSearchModel> baseSearchModelsFromCursor = this0.getBaseSearchModelsFromCursor();
            if (baseSearchModelsFromCursor.isEmpty()) {
                Toast.makeText(this0, this0.getString(R.string.empty_text), 0).show();
            } else {
                new SimpleSearchDialogCompat(this0, "", "", null, baseSearchModelsFromCursor, new SearchResultListener<BaseSearchModel>() {
                    public  void onSelected(BaseSearchDialogCompat baseSearchDialogCompat, BaseSearchModel baseSearchModel, int i2) {
                        onSelected2((BaseSearchDialogCompat<?>) baseSearchDialogCompat, baseSearchModel, i2);
                    }
                    public void onSelected2(BaseSearchDialogCompat<?> dialog, BaseSearchModel item, int i2) {
                        FichePropertyEditView fichePropertyEditView;
                        EditText edtValue;
                        Intrinsics.checkNotNullParameter(dialog, "dialog");
                        Intrinsics.checkNotNullParameter(item, "item");
                        fichePropertyEditView = EDispatchExtraInfoActivity.this.country;
                        if (fichePropertyEditView != null && (edtValue = fichePropertyEditView.getEdtValue()) != null) {
                            edtValue.setText(item.getTitle());
                        }
                        dialog.dismiss();
                    }
                    public void onCancelled(BaseSearchDialogCompat<?> dialog) {
                        FichePropertyEditView fichePropertyEditView;
                        EditText edtValue;
                        Intrinsics.checkNotNullParameter(dialog, "dialog");
                        fichePropertyEditView = EDispatchExtraInfoActivity.this.country;
                        if (fichePropertyEditView != null && (edtValue = fichePropertyEditView.getEdtValue()) != null) {
                            edtValue.setText("");
                        }
                        dialog.dismiss();
                    }
                }).show();
            }
        } catch (Exception e2) {
            Log.e(TAG, "createSearchDialogCursorSales: ", e2);
        }
    }

    private void setEnabled() {
        if (this.ficheMode != FicheMode.ANALYSE) {
            return;
        }
        FichePropertyEditView fichePropertyEditView = this.plateNr;
        Intrinsics.checkNotNull(fichePropertyEditView);
        fichePropertyEditView.getEdtValue().setEnabled(false);
        FichePropertyEditView fichePropertyEditView2 = this.taxNr;
        Intrinsics.checkNotNull(fichePropertyEditView2);
        fichePropertyEditView2.getEdtValue().setEnabled(false);
        FichePropertyEditView fichePropertyEditView3 = this.title;
        Intrinsics.checkNotNull(fichePropertyEditView3);
        fichePropertyEditView3.getEdtValue().setEnabled(false);
        FichePropertyEditView fichePropertyEditView4 = this.county;
        Intrinsics.checkNotNull(fichePropertyEditView4);
        fichePropertyEditView4.getEdtValue().setEnabled(false);
        FichePropertyEditView fichePropertyEditView5 = this.city;
        Intrinsics.checkNotNull(fichePropertyEditView5);
        fichePropertyEditView5.getEdtValue().setEnabled(false);
        FichePropertyEditView fichePropertyEditView6 = this.country;
        Intrinsics.checkNotNull(fichePropertyEditView6);
        fichePropertyEditView6.getEdtValue().setEnabled(false);
        FichePropertyEditView fichePropertyEditView7 = this.postalCode;
        Intrinsics.checkNotNull(fichePropertyEditView7);
        fichePropertyEditView7.getEdtValue().setEnabled(false);
        FichePropertyEditView fichePropertyEditView8 = this.firstDriverName;
        Intrinsics.checkNotNull(fichePropertyEditView8);
        fichePropertyEditView8.getEdtValue().setEnabled(false);
        FichePropertyEditView fichePropertyEditView9 = this.firstDriverSurname;
        Intrinsics.checkNotNull(fichePropertyEditView9);
        fichePropertyEditView9.getEdtValue().setEnabled(false);
        FichePropertyEditView fichePropertyEditView10 = this.firstDriverDescription;
        Intrinsics.checkNotNull(fichePropertyEditView10);
        fichePropertyEditView10.getEdtValue().setEnabled(false);
        FichePropertyEditView fichePropertyEditView11 = this.firstDriverIdentityNr;
        Intrinsics.checkNotNull(fichePropertyEditView11);
        fichePropertyEditView11.getEdtValue().setEnabled(false);
        FichePropertyEditView fichePropertyEditView12 = this.firstTrailerPlate;
        Intrinsics.checkNotNull(fichePropertyEditView12);
        fichePropertyEditView12.getEdtValue().setEnabled(false);
    }

    private void bindExtraInfo() {
        FichePropertyEditView fichePropertyEditView = this.plateNr;
        Intrinsics.checkNotNull(fichePropertyEditView);
        EditText edtValue = fichePropertyEditView.getEdtValue();
        EDispatchAdditionalInfo eDispatchAdditionalInfo = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo);
        edtValue.setText(eDispatchAdditionalInfo.firstDriverPlate);
        FichePropertyEditView fichePropertyEditView2 = this.taxNr;
        Intrinsics.checkNotNull(fichePropertyEditView2);
        EditText edtValue2 = fichePropertyEditView2.getEdtValue();
        EDispatchAdditionalInfo eDispatchAdditionalInfo2 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo2);
        edtValue2.setText(eDispatchAdditionalInfo2.carrierTaxNr);
        FichePropertyEditView fichePropertyEditView3 = this.title;
        Intrinsics.checkNotNull(fichePropertyEditView3);
        EditText edtValue3 = fichePropertyEditView3.getEdtValue();
        EDispatchAdditionalInfo eDispatchAdditionalInfo3 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo3);
        edtValue3.setText(eDispatchAdditionalInfo3.carrierName);
        FichePropertyEditView fichePropertyEditView4 = this.county;
        Intrinsics.checkNotNull(fichePropertyEditView4);
        EditText edtValue4 = fichePropertyEditView4.getEdtValue();
        EDispatchAdditionalInfo eDispatchAdditionalInfo4 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo4);
        edtValue4.setText(eDispatchAdditionalInfo4.carrierCounty);
        FichePropertyEditView fichePropertyEditView5 = this.city;
        Intrinsics.checkNotNull(fichePropertyEditView5);
        EditText edtValue5 = fichePropertyEditView5.getEdtValue();
        EDispatchAdditionalInfo eDispatchAdditionalInfo5 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo5);
        edtValue5.setText(eDispatchAdditionalInfo5.carrierCity);
        FichePropertyEditView fichePropertyEditView6 = this.country;
        Intrinsics.checkNotNull(fichePropertyEditView6);
        EditText edtValue6 = fichePropertyEditView6.getEdtValue();
        EDispatchAdditionalInfo eDispatchAdditionalInfo6 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo6);
        edtValue6.setText(eDispatchAdditionalInfo6.carrierCountry);
        FichePropertyEditView fichePropertyEditView7 = this.postalCode;
        Intrinsics.checkNotNull(fichePropertyEditView7);
        EditText edtValue7 = fichePropertyEditView7.getEdtValue();
        EDispatchAdditionalInfo eDispatchAdditionalInfo7 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo7);
        edtValue7.setText(eDispatchAdditionalInfo7.carrierPostCode);
        FichePropertyEditView fichePropertyEditView8 = this.firstDriverName;
        Intrinsics.checkNotNull(fichePropertyEditView8);
        EditText edtValue8 = fichePropertyEditView8.getEdtValue();
        EDispatchAdditionalInfo eDispatchAdditionalInfo8 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo8);
        edtValue8.setText(eDispatchAdditionalInfo8.firstDriverName);
        FichePropertyEditView fichePropertyEditView9 = this.firstDriverSurname;
        Intrinsics.checkNotNull(fichePropertyEditView9);
        EditText edtValue9 = fichePropertyEditView9.getEdtValue();
        EDispatchAdditionalInfo eDispatchAdditionalInfo9 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo9);
        edtValue9.setText(eDispatchAdditionalInfo9.firstDriverLastName);
        FichePropertyEditView fichePropertyEditView10 = this.firstDriverDescription;
        Intrinsics.checkNotNull(fichePropertyEditView10);
        EditText edtValue10 = fichePropertyEditView10.getEdtValue();
        EDispatchAdditionalInfo eDispatchAdditionalInfo10 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo10);
        edtValue10.setText(eDispatchAdditionalInfo10.firstDriverDescription);
        FichePropertyEditView fichePropertyEditView11 = this.firstDriverIdentityNr;
        Intrinsics.checkNotNull(fichePropertyEditView11);
        EditText edtValue11 = fichePropertyEditView11.getEdtValue();
        EDispatchAdditionalInfo eDispatchAdditionalInfo11 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo11);
        edtValue11.setText(eDispatchAdditionalInfo11.firstDriverIdentityNr);
        FichePropertyEditView fichePropertyEditView12 = this.firstTrailerPlate;
        Intrinsics.checkNotNull(fichePropertyEditView12);
        EditText edtValue12 = fichePropertyEditView12.getEdtValue();
        EDispatchAdditionalInfo eDispatchAdditionalInfo12 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo12);
        edtValue12.setText(eDispatchAdditionalInfo12.firstTrailerPlate);
    }

    private ImageButton createImageButton(FichePropertyEditView fichePropertyEditView) {
        Intrinsics.checkNotNull(fichePropertyEditView);
        EditText editText = fichePropertyEditView.findViewById(R.id.edt_fiche_property_edit);
        LinearLayout linearLayout = fichePropertyEditView.findViewById(R.id.ln_fiche_property);
        editText.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 0.5f));
        ImageButton imageButton = new ImageButton(this);
        imageButton.setImageResource(R.drawable.ic_magnify_black_18dp);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1, 0.05f);
        layoutParams.rightMargin = 5;
        imageButton.setBackgroundColor(0);
        imageButton.setLayoutParams(layoutParams);
        linearLayout.addView(imageButton);
        return imageButton;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            mapToEDispatchInfo();
            Intent intent = new Intent();
            intent.putExtra(IntentExtraName.EXTRA_EDESPATCH_EXTRA_INFO, this.additionalInfo);
            setResult(-1, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void mapToEDispatchInfo() {
        EDispatchAdditionalInfo eDispatchAdditionalInfo = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo);
        FichePropertyEditView fichePropertyEditView = this.plateNr;
        Intrinsics.checkNotNull(fichePropertyEditView);
        eDispatchAdditionalInfo.firstDriverPlate = fichePropertyEditView.getEdtValue().getText().toString();
        EDispatchAdditionalInfo eDispatchAdditionalInfo2 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo2);
        FichePropertyEditView fichePropertyEditView2 = this.taxNr;
        Intrinsics.checkNotNull(fichePropertyEditView2);
        eDispatchAdditionalInfo2.carrierTaxNr = fichePropertyEditView2.getEdtValue().getText().toString();
        EDispatchAdditionalInfo eDispatchAdditionalInfo3 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo3);
        FichePropertyEditView fichePropertyEditView3 = this.title;
        Intrinsics.checkNotNull(fichePropertyEditView3);
        eDispatchAdditionalInfo3.carrierName = fichePropertyEditView3.getEdtValue().getText().toString();
        EDispatchAdditionalInfo eDispatchAdditionalInfo4 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo4);
        FichePropertyEditView fichePropertyEditView4 = this.county;
        Intrinsics.checkNotNull(fichePropertyEditView4);
        eDispatchAdditionalInfo4.carrierCounty = fichePropertyEditView4.getEdtValue().getText().toString();
        EDispatchAdditionalInfo eDispatchAdditionalInfo5 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo5);
        FichePropertyEditView fichePropertyEditView5 = this.city;
        Intrinsics.checkNotNull(fichePropertyEditView5);
        eDispatchAdditionalInfo5.carrierCity = fichePropertyEditView5.getEdtValue().getText().toString();
        EDispatchAdditionalInfo eDispatchAdditionalInfo6 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo6);
        FichePropertyEditView fichePropertyEditView6 = this.country;
        Intrinsics.checkNotNull(fichePropertyEditView6);
        eDispatchAdditionalInfo6.carrierCountry = fichePropertyEditView6.getEdtValue().getText().toString();
        EDispatchAdditionalInfo eDispatchAdditionalInfo7 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo7);
        FichePropertyEditView fichePropertyEditView7 = this.postalCode;
        Intrinsics.checkNotNull(fichePropertyEditView7);
        eDispatchAdditionalInfo7.carrierPostCode = fichePropertyEditView7.getEdtValue().getText().toString();
        EDispatchAdditionalInfo eDispatchAdditionalInfo8 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo8);
        FichePropertyEditView fichePropertyEditView8 = this.firstDriverName;
        Intrinsics.checkNotNull(fichePropertyEditView8);
        eDispatchAdditionalInfo8.firstDriverName = fichePropertyEditView8.getEdtValue().getText().toString();
        EDispatchAdditionalInfo eDispatchAdditionalInfo9 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo9);
        FichePropertyEditView fichePropertyEditView9 = this.firstDriverSurname;
        Intrinsics.checkNotNull(fichePropertyEditView9);
        eDispatchAdditionalInfo9.firstDriverLastName = fichePropertyEditView9.getEdtValue().getText().toString();
        EDispatchAdditionalInfo eDispatchAdditionalInfo10 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo10);
        FichePropertyEditView fichePropertyEditView10 = this.firstDriverDescription;
        Intrinsics.checkNotNull(fichePropertyEditView10);
        eDispatchAdditionalInfo10.firstDriverDescription = fichePropertyEditView10.getEdtValue().getText().toString();
        EDispatchAdditionalInfo eDispatchAdditionalInfo11 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo11);
        FichePropertyEditView fichePropertyEditView11 = this.firstDriverIdentityNr;
        Intrinsics.checkNotNull(fichePropertyEditView11);
        eDispatchAdditionalInfo11.firstDriverIdentityNr = fichePropertyEditView11.getEdtValue().getText().toString();
        EDispatchAdditionalInfo eDispatchAdditionalInfo12 = this.additionalInfo;
        Intrinsics.checkNotNull(eDispatchAdditionalInfo12);
        FichePropertyEditView fichePropertyEditView12 = this.firstTrailerPlate;
        Intrinsics.checkNotNull(fichePropertyEditView12);
        eDispatchAdditionalInfo12.firstTrailerPlate = fichePropertyEditView12.getEdtValue().getText().toString();
    }
     public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putParcelable(STATE_SALES_EDISPATCH_ADDITIONAL, this.additionalInfo);
        super.onSaveInstanceState(outState);
    }
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 999 && -1 == i3) {
            Intrinsics.checkNotNull(intent);
            Bundle extras = intent.getExtras();
            Intrinsics.checkNotNull(extras);
            Customer customer = extras.getParcelable(IntentExtraName.EXTRA_CUSTOMER_ITEM);
            if (customer == null) {
                return;
            }
            this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
            this.viewModel.getEDispatchAdditionalInfo(customer, new EDispatchInfoListener(this));
        }
    }

    public void fillCustomerInfo(EDispatchAdditionalInfo eDispatchAdditionalInfo, String str) {
        this.mProgressDialogBuilder.dismiss();
        if (eDispatchAdditionalInfo != null) {
            FichePropertyEditView fichePropertyEditView = this.taxNr;
            Intrinsics.checkNotNull(fichePropertyEditView);
            fichePropertyEditView.getEdtValue().setText(eDispatchAdditionalInfo.carrierTaxNr);
            FichePropertyEditView fichePropertyEditView2 = this.title;
            Intrinsics.checkNotNull(fichePropertyEditView2);
            fichePropertyEditView2.getEdtValue().setText(eDispatchAdditionalInfo.carrierName);
            FichePropertyEditView fichePropertyEditView3 = this.county;
            Intrinsics.checkNotNull(fichePropertyEditView3);
            fichePropertyEditView3.getEdtValue().setText(eDispatchAdditionalInfo.carrierCounty);
            FichePropertyEditView fichePropertyEditView4 = this.city;
            Intrinsics.checkNotNull(fichePropertyEditView4);
            fichePropertyEditView4.getEdtValue().setText(eDispatchAdditionalInfo.carrierCity);
            FichePropertyEditView fichePropertyEditView5 = this.country;
            Intrinsics.checkNotNull(fichePropertyEditView5);
            fichePropertyEditView5.getEdtValue().setText(eDispatchAdditionalInfo.carrierCountry);
            FichePropertyEditView fichePropertyEditView6 = this.postalCode;
            Intrinsics.checkNotNull(fichePropertyEditView6);
            fichePropertyEditView6.getEdtValue().setText(eDispatchAdditionalInfo.carrierPostCode);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, 1).show();
    }

    private record EDispatchInfoListener(
            WeakReference<EDispatchExtraInfoActivity> mEDispatchExtraInfoActivityWeakReference) implements ResponseListener<EDispatchAdditionalInfo> {
            private EDispatchInfoListener(EDispatchExtraInfoActivity mEDispatchExtraInfoActivityWeakReference) {
                this.mEDispatchExtraInfoActivityWeakReference = new WeakReference<>(mEDispatchExtraInfoActivityWeakReference);
            }

            public void onResponse(PrintSlipModel eDispatchAdditionalInfo) {
                if (this.mEDispatchExtraInfoActivityWeakReference.get() != null) {
                    EDispatchExtraInfoActivity eDispatchExtraInfoActivity = this.mEDispatchExtraInfoActivityWeakReference.get();
                    Intrinsics.checkNotNull(eDispatchExtraInfoActivity);
                    if (eDispatchExtraInfoActivity.isActivityDestroyed()) {
                        return;
                    }
                    EDispatchExtraInfoActivity eDispatchExtraInfoActivity2 = this.mEDispatchExtraInfoActivityWeakReference.get();
                    Intrinsics.checkNotNull(eDispatchExtraInfoActivity2);
                    eDispatchExtraInfoActivity2.fillCustomerInfo(eDispatchAdditionalInfo, "");
                }
            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mEDispatchExtraInfoActivityWeakReference.get() != null) {
                    EDispatchExtraInfoActivity eDispatchExtraInfoActivity = this.mEDispatchExtraInfoActivityWeakReference.get();
                    Intrinsics.checkNotNull(eDispatchExtraInfoActivity);
                    if (eDispatchExtraInfoActivity.isActivityDestroyed()) {
                        return;
                    }
                    Log.d(EDispatchExtraInfoActivity.TAG, "onError: " + errorMessage);
                    EDispatchExtraInfoActivity eDispatchExtraInfoActivity2 = this.mEDispatchExtraInfoActivityWeakReference.get();
                    Intrinsics.checkNotNull(eDispatchExtraInfoActivity2);
                    eDispatchExtraInfoActivity2.fillCustomerInfo(null, errorMessage);
                }
            }
        }

    private ArrayList<BaseSearchModel> getBaseSearchModelsFromCursor() {
        ArrayList<BaseSearchModel> arrayList = new ArrayList<>();
        Cursor query = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query(getString(R.string.qry_country));
        if (query != null) {
            try {
                try {
                    if (query.getCount() > 0 && query.moveToFirst()) {
                        do {
                            String string = query.getString(query.getColumnIndex("NAME"));
                            FichePropertyEditView fichePropertyEditView = this.country;
                            Intrinsics.checkNotNull(fichePropertyEditView);
                            arrayList.add(new BaseSearchModel(-1, string, Intrinsics.areEqual(string, fichePropertyEditView.getEdtValue().getText().toString())));
                        } while (query.moveToNext());
                    }
                    if (!query.isClosed()) {
                        query.close();
                    }
                } catch (Exception e2) {
                    Log.e(TAG, "getBaseSearchModelsFromCursor: ", e2);
                    query.close();
                    return arrayList;
                }
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        }
        return arrayList;
    }

    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getEXTRA_SALES_FICHE_MODE() {
            return EDispatchExtraInfoActivity.EXTRA_SALES_FICHE_MODE;
        }
    }

    static {
        String name = EDispatchExtraInfoActivity.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        TAG = name;
        EXTRA_SALES_FICHE_MODE = EDispatchAdditionalInfo.class.getName() + ".EXTRA_SALES_FICHE_MODE";
    }
}
