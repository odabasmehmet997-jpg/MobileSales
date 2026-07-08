package com.proje.mobilesales.features.sales.view.serilot;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.util.Pair;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.TrackType;
import com.proje.mobilesales.core.interfaces.AsyncReportResponse;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.tigerwcf.*;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.databinding.SerialLotsBinding;
import com.proje.mobilesales.features.activity.BarcodeReaderView;
import com.proje.mobilesales.features.model.BarcodeItem;
import com.proje.mobilesales.features.model.SerialLotListQueryParam;
import com.proje.mobilesales.features.model.SerialLotModel;
import com.proje.mobilesales.features.model.USER;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import com.proje.mobilesales.features.sales.repository.SalesSeriLotRepository;
import dagger.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;

import static com.proje.mobilesales.core.utils.AppUtils.playBeepSound;

public final class SalesSeriLotActivity extends BaseErpActivity implements AsyncReportResponse {
    private static final int BARCODE = 0;
    public static final String SERILOT_LIST = "SERILOT_LIST";
    private static final String STATE_ADAPTER = "state:adapter";
    private static final String STATE_HAS_ORDER_REF = "state:hasOrderRef";
    private static final String STATE_HAS_STOCK_TRACK = "state:hasStockTrack";
    private static final String STATE_HAS_VARIANT = "state:hasVariant";
    private static final String STATE_IS_DIV_UNIT = "state:isDivUnit";
    private static final String STATE_ITEM_REF = "state:itemRef";
    private static final String STATE_ORDER_AVAILABLE_AMOUNT = "state:orderAvailableAmount";
    private static final String STATE_RANGE_SELECT = "state:rangeSelect";
    private static final String STATE_SALES_TYPE = "state:salesType";
    private static final String STATE_SURPLUS_AMOUNT = "state:surplusAmount";
    private static final String STATE_TRACK_TYPE = "state:trackType";
    private static final String STATE_VARIANT_REF = "state:variantRef";
    private static final String STATE_WAREHOUSE_NR = "state:wareHouseNr";
    public static final String SURPLUS_AMOUNT = "SURPLUS_AMOUNT";
    private static final String TAG = "SalesSeriLotActivity";
    private static final long serialVersionUID = 1;
    private final Lazy bindingdelegate;
    private final View.OnClickListener clickListener;
    private AppCompatEditText edLotNo;
    private EditText edtSurplusAmount;
    private AppCompatImageButton imgBtnSearch;
    private boolean isBarcodeScan;
    private boolean isDivUnit;
    private boolean isSurplusAmountEnabled;
    private LinearLayout linearProgress;
    private View lnSurplusAmount;
    private RecyclerView lvSerialLotNos;
    private SalesSeriLotAdapter mAdapter;
    private boolean mHasOrderRef;
    private boolean mHasStockTrack;
    private boolean mHasVariant;
    private int mItemRef;
    private double mOrderAvailableAmount;
    private boolean mRangeSelect;
    private SalesType mSalesType;
    private int mTrackType;
    private int mVariantRef;
    private int mWarehouseNr;
    private final SalesSeriLotRepository repository;
    private String seriLot;
    private String strSeriLot;
    private double surplusAmount;
    private AppCompatTextView tvAmount;
    private AppCompatTextView tvCheckedCount;
    private AppCompatTextView tvSerialNo;
    private AppCompatTextView tvUserAmount;
    private final SalesSeriLotViewModel viewModel;
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_ITEM_REF = SalesSeriLotActivity.class.getName() + ".EXTRA_ITEM_REF";
    public static final String EXTRA_TRACK_TYPE = SalesSeriLotActivity.class.getName() + ".EXTRA_TRACK_TYPE";
    public static final String EXTRA_WAREHOUSE_NR = SalesSeriLotActivity.class.getName() + ".EXTRA_WAREHOUSE_NR";
    public static final String EXTRA_HAS_ORDER_REF = SalesSeriLotActivity.class.getName() + ".EXTRA_HAS_ORDER_REF";
    public static final String EXTRA_ORDER_AVAILABLE_AMOUNT = SalesSeriLotActivity.class.getName() + "EXTRA_ORDER_AVAILABLE_AMOUNT";
    public static final String EXTRA_SALES_TYPE = SalesSeriLotActivity.class.getName() + ".EXTRA_SALES_TYPE";
    public static final String EXTRA_VARIANT_REF = SalesSeriLotActivity.class.getName() + ".EXTRA_VARIANT_REF";
    public static final String EXTRA_HAS_VARIANT = SalesSeriLotActivity.class.getName() + ".EXTRA_HAS_VARIANT";
    public static final String EXTRA_HAS_STOCK_TRACK = SalesSeriLotActivity.class.getName() + ".EXTRA_HAS_STOCK_TRACK";
    public static final String EXTRA_IS_DIV_UNIT = SalesSeriLotActivity.class.getName() + ".EXTRA_IS_DIV_UNIT";
    public static final String EXTRA_SURPLUS_AMOUNT = SalesSeriLotActivity.class.getName() + ".EXTRA_SURPLUS_AMOUNT";
    public interface SeriLotSelectListener {
        void onSelectChangedListener(int i2);
    }

    public SalesSeriLotActivity() {
        SalesSeriLotRepository salesSeriLotRepository = new SalesSeriLotRepository();
        this.repository = salesSeriLotRepository;
        this.viewModel = new SalesSeriLotViewModel(salesSeriLotRepository);
        this.bindingdelegate = (Lazy) LazyKt.lazy(new Function0<SerialLotsBinding>() {
            public SerialLotsBinding invoke() {
                return SerialLotsBinding.inflate(SalesSeriLotActivity.this.getLayoutInflater());
            }
        });
        this.seriLot = "";
        this.clickListener = new View.OnClickListener() {
            public  void SalesSeriLotActivityExternalSyntheticLambda3() {
            }
            public void onClick(View view) {
                SalesSeriLotActivity.clickListenerlambda1(SalesSeriLotActivity.this, view);
            }
        };
    }

    private SerialLotsBinding getBinding() {
        return (SerialLotsBinding) this.bindingdelegate.getValue();
    }

    public String getSeriLot() {
        return this.seriLot;
    }

    public void setSeriLot(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.seriLot = str;
    }

    public void onCreate(Bundle bundle) {
        ArrayList parcelableArrayList;
        super.onCreate(bundle);
        setContentView(getBinding().getRoot());
        setToolbar();
        if (bundle != null) {
            this.mItemRef = bundle.getInt(STATE_ITEM_REF);
            this.mWarehouseNr = bundle.getInt(STATE_WAREHOUSE_NR);
            this.mTrackType = bundle.getInt(STATE_TRACK_TYPE);
            this.mSalesType = SalesType.values()[bundle.getInt(STATE_SALES_TYPE, 0)];
            this.mHasOrderRef = bundle.getBoolean(STATE_HAS_ORDER_REF);
            this.mOrderAvailableAmount = bundle.getDouble(STATE_ORDER_AVAILABLE_AMOUNT);
            SalesSeriLotAdapter salesSeriLotAdapter = this.mAdapter;
            if (salesSeriLotAdapter != null) {
                Intrinsics.checkNotNull(salesSeriLotAdapter);
                salesSeriLotAdapter.saveState(bundle.getBundle("state:adapter"));
            }
            this.mRangeSelect = bundle.getBoolean(STATE_RANGE_SELECT);
            this.mVariantRef = bundle.getInt(STATE_VARIANT_REF);
            this.mHasVariant = bundle.getBoolean(STATE_HAS_VARIANT);
            this.mHasStockTrack = bundle.getBoolean(STATE_HAS_STOCK_TRACK);
            this.isDivUnit = bundle.getBoolean(STATE_IS_DIV_UNIT);
            this.surplusAmount = bundle.getDouble(STATE_SURPLUS_AMOUNT);
        } else {
            this.mItemRef = getIntent().getIntExtra(EXTRA_ITEM_REF, -1);
            this.mWarehouseNr = getIntent().getIntExtra(EXTRA_WAREHOUSE_NR, -1);
            this.mHasOrderRef = getIntent().getBooleanExtra(EXTRA_HAS_ORDER_REF, false);
            this.mOrderAvailableAmount = getIntent().getDoubleExtra(EXTRA_ORDER_AVAILABLE_AMOUNT, 0.0d);
            this.mTrackType = getIntent().getIntExtra(EXTRA_TRACK_TYPE, -1);
            this.mSalesType = (SalesType) getIntent().getSerializableExtra(EXTRA_SALES_TYPE);
            this.mVariantRef = getIntent().getIntExtra(EXTRA_VARIANT_REF, -1);
            this.mHasVariant = getIntent().getBooleanExtra(EXTRA_HAS_VARIANT, false);
            this.mHasStockTrack = getIntent().getBooleanExtra(EXTRA_HAS_STOCK_TRACK, false);
            this.isDivUnit = getIntent().getBooleanExtra(EXTRA_IS_DIV_UNIT, false);
            this.surplusAmount = getIntent().getDoubleExtra(EXTRA_SURPLUS_AMOUNT, 0.0d);
            this.mRangeSelect = false;
        }
        this.isSurplusAmountEnabled = this.viewModel.getSurplusAmountEnabled();
        initialize();
        setSurplusAmount();
        SalesType salesType = this.mSalesType;
        if (salesType != null && SalesUtils.isSalesTypeWhTransfer(salesType)) {
            this.mWarehouseNr = this.viewModel.user().getWarehouseNr();
        }
        boolean z = this.mTrackType == 1;
        boolean z2 = this.mHasOrderRef;
        double d2 = this.mOrderAvailableAmount;
        boolean z3 = this.isDivUnit;
        SalesType salesType2 = this.mSalesType;
        Intrinsics.checkNotNull(salesType2);
        SalesSeriLotAdapter salesSeriLotAdapter2 = new SalesSeriLotAdapter(this, z, z2, d2, z3, salesType2, new SeriLotSelectListener() { // from class: com.proje.mobilesales.features.sales.view.serilot.SalesSeriLotActivityonCreate1
            void SalesSeriLotActivityonCreate1() {
            }

            public void onSelectChangedListener(int i2) {
                AppCompatTextView appCompatTextView;
                appCompatTextView = SalesSeriLotActivity.this.tvCheckedCount;
                Intrinsics.checkNotNull(appCompatTextView);
                appCompatTextView.setText(String.valueOf(i2));
            }
        });
        this.mAdapter = salesSeriLotAdapter2;
        Intrinsics.checkNotNull(salesSeriLotAdapter2);
        salesSeriLotAdapter2.setHasStableIds(true);
        RecyclerView recyclerView = this.lvSerialLotNos;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = this.lvSerialLotNos;
        Intrinsics.checkNotNull(recyclerView2);
        recyclerView2.setAdapter(this.mAdapter);
        SalesSeriLotAdapter salesSeriLotAdapter3 = this.mAdapter;
        Intrinsics.checkNotNull(salesSeriLotAdapter3);
        salesSeriLotAdapter3.setItemRef(this.mItemRef);
        createListeners();
        int i2 = this.mTrackType;
        if (i2 == 1) {
            String string = getString(this.viewModel.erpType() == ErpType.NETSIS ? R.string.str_lot_number : R.string.str_lot_no_exp_date);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            this.seriLot = string;
        } else if (i2 == 2) {
            String string2 = getString(R.string.str_serial_number);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            this.seriLot = string2;
        }
        AppCompatTextView appCompatTextView = this.tvSerialNo;
        Intrinsics.checkNotNull(appCompatTextView);
        appCompatTextView.setText(this.seriLot);
        setTitle(this.seriLot);
        Bundle extras = getIntent().getExtras();
        if (extras == null || !extras.containsKey(SERILOT_LIST) || (parcelableArrayList = extras.getParcelableArrayList(SERILOT_LIST)) == null || parcelableArrayList.isEmpty()) {
            return;
        }
        ArrayList<SeriLotCheck> arrayList = new ArrayList<>();
        int size = parcelableArrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            SeriLotCheck seriLotCheck = new SeriLotCheck();
            seriLotCheck.isChecked = true;
            seriLotCheck.serilot = (Serilot) parcelableArrayList.get(i3);
            arrayList.add(seriLotCheck);
            SalesSeriLotAdapter salesSeriLotAdapter4 = this.mAdapter;
            Intrinsics.checkNotNull(salesSeriLotAdapter4);
            salesSeriLotAdapter4.addToSelectedItemList(seriLotCheck);
        }
        setListView(arrayList);
    }
    public void onActivityResult(int i2, int i3, Intent intent) {
        String barcode;
        super.onActivityResult(i2, i3, intent);
        IntentResult parseActivityResult = IntentIntegrator.parseActivityResult(i2, i3, intent);
        BarcodeItem barcodeItem = intent != null ? (BarcodeItem) intent.getParcelableExtra("SCAN_RESULT") : null;
        if (i2 != 49374 || parseActivityResult == null) {
            return;
        }
        if (parseActivityResult.getContents() == null && barcodeItem == null) {
            Log.d(MobileSales.TAG, "onActivityResult: barcode result null");
            return;
        }
        if (parseActivityResult.getContents() != null) {
            Log.i(MobileSales.TAG, "onActivityResult: " + parseActivityResult.getContents());
            barcode = parseActivityResult.getContents();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("onActivityResult: ");
            Intrinsics.checkNotNull(barcodeItem);
            sb.append(barcodeItem.getKey().getBarcode());
            Log.i(MobileSales.TAG, sb.toString());
            barcode = barcodeItem.getKey().getBarcode();
        }
        this.strSeriLot = barcode;
        AppCompatEditText appCompatEditText = this.edLotNo;
        Intrinsics.checkNotNull(appCompatEditText);
        appCompatEditText.setText(this.strSeriLot);
        this.isBarcodeScan = true;
        search();
    }

    private void initialize() {
        View view;
        AppCompatEditText appCompatEditText = getBinding().edLotNo;
        this.edLotNo = appCompatEditText;
        Intrinsics.checkNotNull(appCompatEditText);
        appCompatEditText.setText("");
        this.lvSerialLotNos = getBinding().lvSerialLotNos;
        this.imgBtnSearch = getBinding().imgBtnSearch;
        this.tvSerialNo = getBinding().tvSerialNo;
        this.linearProgress = getBinding().linearProgress;
        this.tvCheckedCount = getBinding().tvCheckedCount;
        this.tvUserAmount = getBinding().tvUserAmount;
        this.tvAmount = getBinding().tvAmount;
        if (this.mTrackType != 1) {
            AppCompatTextView appCompatTextView = this.tvUserAmount;
            Intrinsics.checkNotNull(appCompatTextView);
            appCompatTextView.setVisibility(View.GONE);
        }
        AppCompatEditText appCompatEditText2 = this.edLotNo;
        Intrinsics.checkNotNull(appCompatEditText2);
        appCompatEditText2.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.proje.mobilesales.features.sales.view.serilot.SalesSeriLotActivityExternalSyntheticLambda0
            public void SalesSeriLotActivityExternalSyntheticLambda0() {
            }
            public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
                boolean initializelambda0;
                initializelambda0 = SalesSeriLotActivity.initializelambda0(SalesSeriLotActivity.this, textView, i2, keyEvent);
                return initializelambda0;
            }
        });
        this.lnSurplusAmount = getBinding().lnSurplusAmount;
        this.edtSurplusAmount = getBinding().edtSurplusAmount;
        if (this.isSurplusAmountEnabled || (view = this.lnSurplusAmount) == null) {
            return;
        }
        view.setVisibility(View.GONE);
    }

    public static boolean initializelambda0(SalesSeriLotActivity this0, TextView textView, int i2, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (i2 != 3 && i2 != 6 && (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 66)) {
            return false;
        }
        this0.isBarcodeScan = true;
        this0.search();
        return true;
    }

    private void setSurplusAmount() {
        if (this.isSurplusAmountEnabled) {
            if (this.isDivUnit) {
                EditText editText = this.edtSurplusAmount;
                Intrinsics.checkNotNull(editText);
                double d2 = this.surplusAmount;
                editText.setText(d2 != 0.0d ? StringUtils.convertDoubleToString(Double.valueOf(d2)) : "");
                EditText editText2 = this.edtSurplusAmount;
                Intrinsics.checkNotNull(editText2);
                editText2.setInputType(8194);
                return;
            }
            EditText editText3 = this.edtSurplusAmount;
            Intrinsics.checkNotNull(editText3);
            double d3 = this.surplusAmount;
            editText3.setText(d3 != 0.0d ? StringUtils.convertIntToString((int) d3) : "");
            EditText editText4 = this.edtSurplusAmount;
            Intrinsics.checkNotNull(editText4);
            editText4.setInputType(2);
        }
    }

    public static void clickListenerlambda1(SalesSeriLotActivity this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (view.getId() == R.id.imgBtnSearch) {
            this0.search();
        }
    }

    private void search() {
        checkSeriLotNo(false);
        AppCompatEditText appCompatEditText = this.edLotNo;
        Intrinsics.checkNotNull(appCompatEditText);
        String valueOf = String.valueOf(appCompatEditText.getText());
        int length = valueOf.length() - 1;
        int i2 = 0;
        boolean z = false;
        while (i2 <= length) {
            boolean z2 = Intrinsics.compare(valueOf.charAt(!z ? i2 : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                } else {
                    length--;
                }
            } else if (z2) {
                i2++;
            } else {
                z = true;
            }
        }
        String obj = valueOf.subSequence(i2, length + 1).toString();
        if (this.viewModel.erpType() == ErpType.GO || this.viewModel.erpType() == ErpType.TIGER) {
            if (ContextUtils.checkInternetConnection()) {
                new ServicesClientForTiger(this).new ReportRetrieve(checkSeriLotListWCF(obj, false)).execute();
            }
        } else {
            int i3 = this.mWarehouseNr;
            int i4 = this.mItemRef;
            SalesType salesType = this.mSalesType;
            Intrinsics.checkNotNull(salesType);
            SerialLotListQueryParam serialLotListQueryParam = new SerialLotListQueryParam(i3, i4, salesType, obj, this.isBarcodeScan);
            LinearLayout linearLayout = this.linearProgress;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(View.VISIBLE);
            this.viewModel.getSerialLotList(serialLotListQueryParam, new SerialLotListListener(this));
        }
        SalesSeriLotAdapter salesSeriLotAdapter = this.mAdapter;
        Intrinsics.checkNotNull(salesSeriLotAdapter);
        salesSeriLotAdapter.setItemRef(this.mItemRef);
    }

    private void setListView(ArrayList<SeriLotCheck> arrayList) {
        SalesSeriLotAdapter salesSeriLotAdapter = this.mAdapter;
        Intrinsics.checkNotNull(salesSeriLotAdapter);
        salesSeriLotAdapter.setList(arrayList);
    }

    private void createListeners() {
        AppCompatImageButton appCompatImageButton = this.imgBtnSearch;
        Intrinsics.checkNotNull(appCompatImageButton);
        appCompatImageButton.setOnClickListener(this.clickListener);
    }
    public void onBackPressed() {
        super.onBackPressed();
        checkSeriLotNo(true);
    }

    private void checkSeriLotNo(boolean z) {
        if (z) {
            SalesSeriLotAdapter salesSeriLotAdapter = this.mAdapter;
            Intrinsics.checkNotNull(salesSeriLotAdapter);
            if (salesSeriLotAdapter.getItemCount() > 0) {
                SalesSeriLotAdapter salesSeriLotAdapter2 = this.mAdapter;
                Intrinsics.checkNotNull(salesSeriLotAdapter2);
                if (salesSeriLotAdapter2.getCheckedSeriLotList().size() > 0) {
                    if (!validateSurplusAmount()) {
                        Toast.makeText(this, getString(R.string.str_surplusAmount_exceeds_entered_amount), Toast.LENGTH_LONG).show();
                        return;
                    }
                    Intent intent = new Intent();
                    SalesSeriLotAdapter salesSeriLotAdapter3 = this.mAdapter;
                    Intrinsics.checkNotNull(salesSeriLotAdapter3);
                    intent.putParcelableArrayListExtra(SERILOT_LIST, salesSeriLotAdapter3.getCheckedSeriLotList());
                    intent.putExtra(IntentExtraName.EXTRA_ITEM_ID, this.mItemRef);
                    EditText editText = this.edtSurplusAmount;
                    Intrinsics.checkNotNull(editText);
                    intent.putExtra(SURPLUS_AMOUNT, StringUtils.convertStringToDouble(editText.getText().toString()));
                    setResult(-1, intent);
                } else {
                    Intent intent2 = new Intent();
                    intent2.putExtra(IntentExtraName.EXTRA_ITEM_ID, this.mItemRef);
                    setResult(0, intent2);
                }
            } else {
                Intent intent3 = new Intent();
                intent3.putExtra(IntentExtraName.EXTRA_ITEM_ID, this.mItemRef);
                setResult(0, intent3);
            }
            finish();
        }
    }

    private boolean validateSurplusAmount() {
        EditText editText = this.edtSurplusAmount;
        Intrinsics.checkNotNull(editText);
        if (TextUtils.isEmpty(editText.getText())) {
            return true;
        }
        EditText editText2 = this.edtSurplusAmount;
        Intrinsics.checkNotNull(editText2);
        double convertStringToDouble = StringUtils.convertStringToDouble(editText2.getText().toString());
        SalesSeriLotAdapter salesSeriLotAdapter = this.mAdapter;
        Intrinsics.checkNotNull(salesSeriLotAdapter);
        return convertStringToDouble <= salesSeriLotAdapter.getCheckedSeriLotList().stream().mapToDouble(new ToDoubleFunction() {

            public double applyAsDouble(Object obj) {
                double validateSurplusAmountlambda3;
                validateSurplusAmountlambda3 = SalesSeriLotActivity.validateSurplusAmountlambda3(SalesSeriLotActivity.this, obj);
                return validateSurplusAmountlambda3;
            }
        }).sum();
    }

    public static double validateSurplusAmountlambda3(SalesSeriLotActivity tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        return ((Number) obj).doubleValue();
    }
    public void onPreExecute(ProcessType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        LinearLayout linearLayout = this.linearProgress;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(View.VISIBLE);
    }
    public void processFinish(REPORTXML reportxml, ProcessType type) {
        boolean z;
        boolean z2;
        int size;
        Intrinsics.checkNotNullParameter(type, "type");
        LinearLayout linearLayout = this.linearProgress;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(View.INVISIBLE);
        ArrayList<SeriLotCheck> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        if ((reportxml != null ? reportxml.reportLines : null) == null || (size = reportxml.reportLines.size()) <= 0) {
            z = false;
            z2 = false;
        } else {
            if (this.isBarcodeScan) {
                playBeepSound();
            }
            z2 = false;
            for (int i2 = 0; i2 < size; i2++) {
                REPORTLINE reportline = reportxml.reportLines.get(i2);
                Intrinsics.checkNotNullExpressionValue(reportline, "get(...)");
                REPORTLINE reportline2 = reportline;
                Serilot serilot = new Serilot(0, 0, 0, null, 0.0d, null, null, null, 0.0d, null, null, null, 0, 0, 0, 0, 65535, null);
                serilot.logicalRef = StringUtils.convertStringToInt(reportline2.f1208X);
                serilot.code = reportline2.f1209Y;
                String Z = reportline2.f1210Z;
                Intrinsics.checkNotNullExpressionValue(Z, "Z");
                serilot.reAmount = Float.parseFloat(Z);
                serilot.unit = reportline2.f1207W;
                String A = reportline2.f1203A;
                Intrinsics.checkNotNullExpressionValue(A, "A");
                serilot.expDate = DateAndTimeUtils.convertYMDToDMY(((String[]) new Regex(ExifInterface.GPS_DIRECTION_TRUE).split(A, 0).toArray(new String[0]))[0]);
                String B = reportline2.f1204B;
                Intrinsics.checkNotNullExpressionValue(B, "B");
                serilot.name = B;
                String T = reportline2.f1206T;
                Intrinsics.checkNotNullExpressionValue(T, "T");
                serilot.locationCode = T;
                serilot.sourceUnitRef = reportline2.SOURCEUNITREF;
                serilot.mainUnitRef = reportline2.MAINUNITREF;
                serilot.stTransRef = reportline2.STTRANSREF;
                SeriLotCheck seriLotCheck = new SeriLotCheck();
                seriLotCheck.serilot = serilot;
                SalesSeriLotAdapter salesSeriLotAdapter = this.mAdapter;
                Intrinsics.checkNotNull(salesSeriLotAdapter);
                Pair<Double, Boolean> isItemChecked = salesSeriLotAdapter.isItemChecked(serilot.logicalRef);
                Intrinsics.checkNotNull(isItemChecked, "null cannot be cast to non-null type androidx.core.util.Pair<kotlin.Double?, kotlin.Boolean>");
                Boolean second = isItemChecked.second;
                Intrinsics.checkNotNullExpressionValue(second, "second");
                seriLotCheck.isChecked = second.booleanValue();
                Serilot serilot2 = seriLotCheck.serilot;
                Intrinsics.checkNotNull(serilot2);
                Double d2 = isItemChecked.first;
                Intrinsics.checkNotNull(d2);
                serilot2.amount = d2.doubleValue();
                if (this.mTrackType == TrackType.SERIAL.getType()) {
                    SalesType salesType = this.mSalesType;
                    Intrinsics.checkNotNull(salesType);
                    if (SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(salesType)) {
                        if (!CollectionsKt.contains(arrayList2, serilot.code)) {
                            String str = serilot.code;
                            Intrinsics.checkNotNull(str);
                            arrayList2.add(str);
                        }
                    }
                }
                arrayList.add(seriLotCheck);
                AppCompatEditText appCompatEditText = this.edLotNo;
                Intrinsics.checkNotNull(appCompatEditText);
                if (!TextUtils.isEmpty(String.valueOf(appCompatEditText.getText())) && size == 1) {
                    String str2 = serilot.code;
                    AppCompatEditText appCompatEditText2 = this.edLotNo;
                    Intrinsics.checkNotNull(appCompatEditText2);
                    if (StringsKt.equals(str2, String.valueOf(appCompatEditText2.getText()), true)) {
                        seriLotCheck.isChecked = true;
                        z2 = this.mTrackType == 1;
                        SalesSeriLotAdapter salesSeriLotAdapter2 = this.mAdapter;
                        Intrinsics.checkNotNull(salesSeriLotAdapter2);
                        salesSeriLotAdapter2.addToSelectedItemList(seriLotCheck);
                    }
                }
            }
            z = false;
        }
        this.isBarcodeScan = z;
        AppCompatEditText appCompatEditText3 = this.edLotNo;
        Intrinsics.checkNotNull(appCompatEditText3);
        appCompatEditText3.setText("");
        setListView(arrayList);
        if (z2) {
            focusAndShowKeyBoard();
        }
    }

    private void focusAndShowKeyBoard() {
        new Handler().postDelayed(new Runnable() { // from class: com.proje.mobilesales.features.sales.view.serilot.SalesSeriLotActivityExternalSyntheticLambda1
            public void SalesSeriLotActivityExternalSyntheticLambda1() {
            }
            public void run() {
                SalesSeriLotActivity.focusAndShowKeyBoardlambda4(SalesSeriLotActivity.this);
            }
        }, 100L);
    }

    public static void focusAndShowKeyBoardlambda4(SalesSeriLotActivity this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        RecyclerView recyclerView = this0.lvSerialLotNos;
        Intrinsics.checkNotNull(recyclerView);
        if (recyclerView.getLayoutManager() == null) {
            return;
        }
        RecyclerView recyclerView2 = this0.lvSerialLotNos;
        Intrinsics.checkNotNull(recyclerView2);
        RecyclerView.LayoutManager layoutManager = recyclerView2.getLayoutManager();
        Intrinsics.checkNotNull(layoutManager);
        View findViewByPosition = layoutManager.findViewByPosition(0);
        if (findViewByPosition != null) {
            EditText editText = findViewByPosition.findViewById(R.id.edtUserAmount);
            editText.requestFocus();
            editText.setSelectAllOnFocus(true);
            Object systemService = this0.getSystemService(Context.INPUT_METHOD_SERVICE);
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            ((InputMethodManager) systemService).showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        MenuInflater menuInflater = getMenuInflater();
        Intrinsics.checkNotNullExpressionValue(menuInflater, "getMenuInflater(...)");
        menuInflater.inflate(R.menu.menu_serial_lot, menu);
        return super.onCreateOptionsMenu(menu);
    }

    
    public boolean onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        menu.findItem(R.id.menu_range_select).setTitle(!this.mRangeSelect ? R.string.menu_range_select : R.string.menu_serial_select);
        MenuItem findItem = menu.findItem(R.id.menu_select_all);
        SalesSeriLotAdapter salesSeriLotAdapter = this.mAdapter;
        Intrinsics.checkNotNull(salesSeriLotAdapter);
        findItem.setTitle(!salesSeriLotAdapter.isAllChecked() ? R.string.menu_select_all : R.string.menu_unselect_all);
        return super.onPrepareOptionsMenu(menu);
    }

    
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        switch (item.getItemId()) {
            case R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_barcode /* 2131297297 */:
                scanFromActivity();
                break;
            case R.id.menu_range_select /* 2131297378 */:
                rangeSelect();
                break;
            case R.id.menu_select_all /* 2131297397 */:
                selectAll();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void rangeSelect() {
        SalesSeriLotAdapter salesSeriLotAdapter = this.mAdapter;
        Intrinsics.checkNotNull(salesSeriLotAdapter);
        if (salesSeriLotAdapter.getItemCount() > 0) {
            SalesSeriLotAdapter salesSeriLotAdapter2 = this.mAdapter;
            Intrinsics.checkNotNull(salesSeriLotAdapter2);
            if (salesSeriLotAdapter2.getItemCount() < 3) {
                Toast.makeText(this, getString(R.string.str_serial_range_select_min_product_amount_error), Toast.LENGTH_LONG).show();
                return;
            }
        }
        this.mRangeSelect = !this.mRangeSelect;
        Log.d(TAG, "rangeSelect: range = " + this.mRangeSelect);
        SalesSeriLotAdapter salesSeriLotAdapter3 = this.mAdapter;
        Intrinsics.checkNotNull(salesSeriLotAdapter3);
        salesSeriLotAdapter3.setRangeSelect(this.mRangeSelect);
    }

    private void selectAll() {
        SalesSeriLotAdapter salesSeriLotAdapter = this.mAdapter;
        Intrinsics.checkNotNull(salesSeriLotAdapter);
        if (!salesSeriLotAdapter.isAllChecked()) {
            SalesSeriLotAdapter salesSeriLotAdapter2 = this.mAdapter;
            Intrinsics.checkNotNull(salesSeriLotAdapter2);
            salesSeriLotAdapter2.selectAll();
        } else {
            SalesSeriLotAdapter salesSeriLotAdapter3 = this.mAdapter;
            Intrinsics.checkNotNull(salesSeriLotAdapter3);
            salesSeriLotAdapter3.unSelectAll();
        }
    }
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_TRACK_TYPE, this.mTrackType);
        outState.putInt(STATE_WAREHOUSE_NR, this.mWarehouseNr);
        outState.putInt(STATE_ITEM_REF, this.mItemRef);
        outState.putSerializable(STATE_SALES_TYPE, this.mSalesType);
        outState.putBoolean(STATE_HAS_ORDER_REF, this.mHasOrderRef);
        outState.putDouble(STATE_ORDER_AVAILABLE_AMOUNT, this.mOrderAvailableAmount);
        SalesSeriLotAdapter salesSeriLotAdapter = this.mAdapter;
        if (salesSeriLotAdapter != null) {
            Intrinsics.checkNotNull(salesSeriLotAdapter);
            outState.putBundle("state:adapter", salesSeriLotAdapter.outState());
        }
        outState.putBoolean(STATE_RANGE_SELECT, this.mRangeSelect);
        outState.putInt(STATE_VARIANT_REF, this.mVariantRef);
        outState.putBoolean(STATE_HAS_VARIANT, this.mHasVariant);
        outState.putBoolean(STATE_HAS_STOCK_TRACK, this.mHasStockTrack);
    }

    public void scanFromActivity() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(IntentIntegrator.ONE_D_CODE_TYPES.toString());
        arrayList.add("QR_CODE");
        IntentIntegrator captureActivity = new IntentIntegrator(this).setBeepEnabled(false).setDesiredBarcodeFormats(arrayList).setCaptureActivity(BarcodeReaderView.class);
        String str = IntentExtraName.EXTRA_SELECT_TYPE;
        Boolean bool = Boolean.TRUE;
        captureActivity.addExtra(str, bool).addExtra(IntentExtraName.BARCODE_FOR_SEARCH, bool).initiateScan();
    }

    public SelectResult checkSeriLotListWCF(String strSeriLot, boolean z) {
        StringBuilder sb;
        String str;
        Intrinsics.checkNotNullParameter(strSeriLot, "strSeriLot");
        LogoTigerTableName logoTigerTableName = new LogoTigerTableName(this.viewModel.user().getDbName(), USER.firmano, USER.periodNr);
        String str2 = this.mTrackType == 2 ? "ST.AMOUNT[Z]" : "ST.REMAMOUNT[Z]";
        SelectResult selectResult = new SelectResult();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT  ST.LOGICALREF [X], " + str2 + " , SL.CODE [Y], MAIN.CODE [W] , ST.EXPDATE [A] , SL.NAME [B]  , ISNULL(LOC.CODE,'') [T], ST.STTRANSREF, USLINE.LOGICALREF SOURCEUNITREF, MAIN.LOGICALREF MAINUNITREF FROM " + logoTigerTableName.SLTRANS + " ST WITH(NOLOCK) LEFT OUTER JOIN " + logoTigerTableName.SERILOTN + " SL WITH(NOLOCK) ON  (ST.SLREF  =  SL.LOGICALREF) LEFT OUTER JOIN " + logoTigerTableName.STFICHE + " STFIC WITH(NOLOCK) ON  (ST.STFICHEREF  =  STFIC.LOGICALREF) LEFT OUTER JOIN " + logoTigerTableName.UNITSETL + " USLINE WITH(NOLOCK)  ON (ST.UOMREF  =  USLINE.LOGICALREF) LEFT OUTER JOIN " + logoTigerTableName.UNITSETL + " MAIN WITH (NOLOCK) ON (MAIN.UNITSETREF = USLINE.UNITSETREF AND MAIN.MAINUNIT = 1) LEFT OUTER JOIN " + logoTigerTableName.LOCATION + " LOC WITH (NOLOCK) ON (ST.LOCREF = LOC.LOGICALREF) WHERE (ST.ITEMREF = " + this.mItemRef + " ) AND (ST.CANCELLED = 0) AND (ST.LPRODSTAT = 0) ");
        SalesType salesType = this.mSalesType;
        Intrinsics.checkNotNull(salesType);
        if (!SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(salesType)) {
            sb2.append("AND (ST.INVENNO = " + this.mWarehouseNr + ") ");
        }
        sb2.append("AND (ST.EXIMFCTYPE IN ( 0 , 4 , 7 )) AND (ST.STATUS = 0) AND (ST.DATE_ <= CONVERT(dateTime, GETDATE(), 101)) AND (ST.IOCODE IN (1,2)) ");
        if (z) {
            sb = new StringBuilder();
            sb.append("AND (SL.CODE = '");
            sb.append(strSeriLot);
            str = "' ) ";
        } else {
            sb = new StringBuilder();
            sb.append("AND (SL.CODE LIKE '%");
            sb.append(strSeriLot);
            str = "%' ) ";
        }
        sb.append(str);
        sb2.append(sb);
        sb2.append("AND (ST.SLTYPE=" + this.mTrackType + SqlLiteVariable._CLOSE_BRACKET);
        if (this.mTrackType == TrackType.SERIAL.getType()) {
            SalesType salesType2 = this.mSalesType;
            Intrinsics.checkNotNull(salesType2);
            sb2.append(SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(salesType2) ? "AND (ST.REMAMOUNT <= 0) AND (SL.STATE=2) " : "AND (ST.REMAMOUNT > 0) AND (SL.STATE=1) ");
        } else if (this.mTrackType == TrackType.LOT.getType()) {
            SalesType salesType3 = this.mSalesType;
            Intrinsics.checkNotNull(salesType3);
            if (!SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(salesType3)) {
                sb2.append("AND (ST.REMAMOUNT > 0) ");
            }
        }
        if (this.mHasVariant) {
            sb2.append(" AND (ST.VARIANTREF =" + this.mVariantRef + ") ");
        }
        if (this.mHasStockTrack) {
            sb2.append(" AND (ST.LOCREF <> 0) ");
        }
        selectResult.sql = sb2.toString();
        selectResult.orderByText = "ORDER BY Y,X ";
        selectResult.type = ProcessType.GETCHECKSERILOT;
        return selectResult;
    }
    public final class SeriLotCheck implements Serializable, Parcelable {
        private final Creator<SeriLotCheck> CREATOR;
        public boolean isChecked;
        public Serilot serilot;
        public int describeContents() {
            return 0;
        }
        public void writeToParcel(Parcel dest, int i2) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeParcelable(this.serilot, i2);
            dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
        }

        public SeriLotCheck() {
            this.CREATOR = new Parcelable.Creator<SeriLotCheck>() {
                void SalesSeriLotActivitySeriLotCheckCREATOR1() {
                }
                public SeriLotCheck createFromParcel(Parcel source) {
                    Intrinsics.checkNotNullParameter(source, "source");
                    return new SalesSeriLotActivity.SeriLotCheck(SalesSeriLotActivity.this, source);
                }
                public SalesSeriLotActivity.SeriLotCheck[] newArray(int i2) {
                    return new SalesSeriLotActivity.SeriLotCheck[i2];
                }
            };
        }

        private SeriLotCheck(SalesSeriLotActivity salesSeriLotActivity, Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            this.CREATOR = new Creator<SeriLotCheck>() {

                public SeriLotCheck createFromParcel(Parcel source) {
                    Intrinsics.checkNotNullParameter(source, "source");
                    return new SeriLotCheck(SalesSeriLotActivity.this, source);
                }

                public SeriLotCheck[] newArray(int i2) {
                    return new SeriLotCheck[i2];
                }
            };
            this.serilot = parcel.readParcelable(Serilot.class.getClassLoader());
            this.isChecked = parcel.readByte() != 0;
        }

        public Parcelable.Creator<SeriLotCheck> getCREATOR() {
            return this.CREATOR;
        }
    }

    private record SerialLotListListener(
            WeakReference<SalesSeriLotActivity> mSerialLotActivity) implements ResponseListener<List<? extends SerialLotModel>> {
            private SerialLotListListener(SalesSeriLotActivity mSerialLotActivity) {
                this.mSerialLotActivity = new WeakReference<>(mSerialLotActivity);
            }

        public void onResponse(PrintSlipModel list) {
                onResponse2((List<SerialLotModel>) list);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            public void onResponse2(List<SerialLotModel> list) {
                if (this.mSerialLotActivity.get() != null) {
                    SalesSeriLotActivity salesSeriLotActivity = this.mSerialLotActivity.get();
                    Intrinsics.checkNotNull(salesSeriLotActivity);
                    if (salesSeriLotActivity.isActivityDestroyed()) {
                        return;
                    }
                    if (list == null) {
                        SalesSeriLotActivity salesSeriLotActivity2 = this.mSerialLotActivity.get();
                        Intrinsics.checkNotNull(salesSeriLotActivity2);
                        LinearLayout linearLayout = salesSeriLotActivity2.linearProgress;
                        Intrinsics.checkNotNull(linearLayout);
                        linearLayout.setVisibility(View.INVISIBLE);
                        return;
                    }
                    SalesSeriLotActivity salesSeriLotActivity3 = this.mSerialLotActivity.get();
                    Intrinsics.checkNotNull(salesSeriLotActivity3);
                    salesSeriLotActivity3.onGotSerialLotResult(list);
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d(SalesSeriLotActivity.TAG, "onError: " + errorMessage);
                if (this.mSerialLotActivity.get() != null) {
                    SalesSeriLotActivity salesSeriLotActivity = this.mSerialLotActivity.get();
                    Intrinsics.checkNotNull(salesSeriLotActivity);
                    if (salesSeriLotActivity.isActivityDestroyed()) {
                        return;
                    }
                    SalesSeriLotActivity salesSeriLotActivity2 = this.mSerialLotActivity.get();
                    Intrinsics.checkNotNull(salesSeriLotActivity2);
                    LinearLayout linearLayout = salesSeriLotActivity2.linearProgress;
                    Intrinsics.checkNotNull(linearLayout);
                    linearLayout.setVisibility(View.INVISIBLE);
                    Toast.makeText(this.mSerialLotActivity.get(), errorMessage, Toast.LENGTH_LONG).show();
                }
            }
        }

    public void onGotSerialLotResult(List<SerialLotModel> list) {
        boolean z;
        ArrayList<SeriLotCheck> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        if (list.isEmpty()) {
            z = false;
        } else {
            String mainUnitCode = this.viewModel.getMainUnitCode(list.get(0).getItemCode());
            if (this.isBarcodeScan) {
                playBeepSound();
            }
            z = false;
            for (SerialLotModel serialLotModel : list) {
                String component1 = serialLotModel.component1();
                int component3 = serialLotModel.component3();
                double component4 = serialLotModel.component4();
                Serilot serilot = new Serilot(0, 0, 0, null, 0.0d, null, null, null, 0.0d, null, null, null, 0, 0, 0, 0, 65535, null);
                serilot.logicalRef = component3;
                serilot.code = component1;
                serilot.reAmount = component4;
                serilot.unit = mainUnitCode;
                SeriLotCheck seriLotCheck = new SeriLotCheck();
                seriLotCheck.serilot = serilot;
                SalesSeriLotAdapter salesSeriLotAdapter = this.mAdapter;
                Intrinsics.checkNotNull(salesSeriLotAdapter);
                Pair<Double, Boolean> isItemChecked = salesSeriLotAdapter.isItemChecked(serilot.logicalRef);
                Intrinsics.checkNotNull(isItemChecked, "null cannot be cast to non-null type androidx.core.util.Pair<kotlin.Double?, kotlin.Boolean>");
                Boolean second = isItemChecked.second;
                Intrinsics.checkNotNullExpressionValue(second, "second");
                seriLotCheck.isChecked = second.booleanValue();
                Serilot serilot2 = seriLotCheck.serilot;
                Intrinsics.checkNotNull(serilot2);
                Double d2 = isItemChecked.first;
                Intrinsics.checkNotNull(d2);
                serilot2.amount = d2.doubleValue();
                if (this.mTrackType == TrackType.SERIAL.getType()) {
                    SalesType salesType = this.mSalesType;
                    Intrinsics.checkNotNull(salesType);
                    if (SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(salesType)) {
                        if (!CollectionsKt.contains(arrayList2, serilot.code)) {
                            String str = serilot.code;
                            Intrinsics.checkNotNull(str);
                            arrayList2.add(str);
                        }
                    }
                }
                arrayList.add(seriLotCheck);
                AppCompatEditText appCompatEditText = this.edLotNo;
                Intrinsics.checkNotNull(appCompatEditText);
                if (!TextUtils.isEmpty(String.valueOf(appCompatEditText.getText())) && list.size() == 1) {
                    String str2 = serilot.code;
                    AppCompatEditText appCompatEditText2 = this.edLotNo;
                    Intrinsics.checkNotNull(appCompatEditText2);
                    if (StringsKt.equals(str2, String.valueOf(appCompatEditText2.getText()), true)) {
                        seriLotCheck.isChecked = true;
                        z = this.mTrackType == 1;
                        SalesSeriLotAdapter salesSeriLotAdapter2 = this.mAdapter;
                        Intrinsics.checkNotNull(salesSeriLotAdapter2);
                        salesSeriLotAdapter2.addToSelectedItemList(seriLotCheck);
                    }
                }
            }
        }
        this.isBarcodeScan = false;
        AppCompatEditText appCompatEditText3 = this.edLotNo;
        Intrinsics.checkNotNull(appCompatEditText3);
        appCompatEditText3.setText("");
        setListView(arrayList);
        if (z) {
            focusAndShowKeyBoard();
        }
        LinearLayout linearLayout = this.linearProgress;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(View.INVISIBLE);
    }

    /* compiled from: SalesSeriLotActivity.kt */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
