package com.proje.mobilesales.features.sales.view.serialgroup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.ActionViewResolver;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.SeriLotUtils;
import com.proje.mobilesales.core.view.ScreenControl;
import com.proje.mobilesales.databinding.SerialGroupBinding;
import com.proje.mobilesales.features.activity.BarcodeReaderView;
import com.proje.mobilesales.features.model.BarcodeItem;
import com.proje.mobilesales.features.model.CheckSeriGroup;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import com.proje.mobilesales.features.sales.repository.SalesSerialGroupRepository;
import com.proje.mobilesales.features.sales.view.serilot.SalesSeriLotActivity;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

import static com.proje.mobilesales.core.utils.AppUtils.playBeepSound;

public final class SalesSerialGroupActivity extends BaseErpActivity {
    private SalesSerialGroupRecyclerViewAdapter adapter;
    private SerialGroupBinding binding;
    private ImageButton btnSearch;
    private EditText edtSearch;
    private boolean isBarcodeScan;
    private int itemRef;
    private ActionViewResolver mActionViewResolver;
    private boolean mHasStockTrack;
    private boolean mHasVariant;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private int mVariantRef;
    private RecyclerView recyclerView;
    private final SalesSerialGroupRepository repository;
    private SalesType salesType;
    private ScreenControl screenControl;
    private ArrayList<Serilot> tmpList;
    private final SalesSerialGroupViewModel viewModel;
    private int wareHouse;
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_ITEM_REF = SalesSerialGroupActivity.class.getName() + ".EXTRA_ITEM_REF";
    public static final String EXTRA_WAREHOUSE_NR = SalesSerialGroupActivity.class.getName() + ".EXTRA_WAREHOUSE_NR";
    public static final String EXTRA_SALES_TYPE = SalesSerialGroupActivity.class.getName() + ".EXTRA_SALES_TYPE";
    public static final String EXTRA_VARIANT_REF = SalesSerialGroupActivity.class.getName() + ".EXTRA_VARIANT_REF";
    public static final String EXTRA_HAS_VARIANT = SalesSerialGroupActivity.class.getName() + ".EXTRA_HAS_VARIANT";
    public static final String EXTRA_HAS_STOCK_TRACK = SalesSerialGroupActivity.class.getName() + ".EXTRA_HAS_STOCK_TRACK";

    public SalesSerialGroupActivity() {
        SalesSerialGroupRepository salesSerialGroupRepository = new SalesSerialGroupRepository();
        this.repository = salesSerialGroupRepository;
        this.viewModel = new SalesSerialGroupViewModel(salesSerialGroupRepository);
        this.salesType = SalesType.FREE;
    }

    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        return this.mProgressDialogBuilder;
    }

    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        this.mProgressDialogBuilder = progressDialogBuilder;
    }

    public ActionViewResolver getMActionViewResolver() {
        return this.mActionViewResolver;
    }

    public void setMActionViewResolver(ActionViewResolver actionViewResolver) {
        this.mActionViewResolver = actionViewResolver;
    }
 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        SerialGroupBinding inflate = SerialGroupBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        SerialGroupBinding serialGroupBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity);
        this.mActionViewResolver = new ActionViewResolver();
        SerialGroupBinding serialGroupBinding2 = this.binding;
        if (serialGroupBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            serialGroupBinding2 = null;
        }
        this.edtSearch = serialGroupBinding2.edtSearch;
        SerialGroupBinding serialGroupBinding3 = this.binding;
        if (serialGroupBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            serialGroupBinding3 = null;
        }
        this.btnSearch = serialGroupBinding3.btnSearch;
        this.itemRef = getIntent().getIntExtra(EXTRA_ITEM_REF, -1);
        this.wareHouse = getIntent().getIntExtra(EXTRA_WAREHOUSE_NR, -1);
        Serializable serializableExtra = getIntent().getSerializableExtra(EXTRA_SALES_TYPE);
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.proje.mobilesales.core.enums.SalesType");
        this.salesType = (SalesType) serializableExtra;
        this.mVariantRef = getIntent().getIntExtra(EXTRA_VARIANT_REF, -1);
        this.mHasVariant = getIntent().getBooleanExtra(EXTRA_HAS_VARIANT, false);
        this.mHasStockTrack = getIntent().getBooleanExtra(EXTRA_HAS_STOCK_TRACK, false);
        ImageButton imageButton = this.btnSearch;
        Intrinsics.checkNotNull(imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                SalesSerialGroupActivity.onCreatelambda0(SalesSerialGroupActivity.this, view);
            }
        });
        EditText editText = this.edtSearch;
        Intrinsics.checkNotNull(editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
                boolean onCreatelambda1;
                onCreatelambda1 = SalesSerialGroupActivity.onCreatelambda1(SalesSerialGroupActivity.this, textView, i2, keyEvent);
                return onCreatelambda1;
            }
        });
        SerialGroupBinding serialGroupBinding4 = this.binding;
        if (serialGroupBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            serialGroupBinding = serialGroupBinding4;
        }
        RecyclerView recyclerView = serialGroupBinding.rwSeriGrup;
        this.recyclerView = recyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SalesSerialGroupRecyclerViewAdapter salesSerialGroupRecyclerViewAdapter = new SalesSerialGroupRecyclerViewAdapter();
        this.adapter = salesSerialGroupRecyclerViewAdapter;
        Intrinsics.checkNotNull(salesSerialGroupRecyclerViewAdapter);
        salesSerialGroupRecyclerViewAdapter.initDisplayOptions(this);
        SalesSerialGroupRecyclerViewAdapter salesSerialGroupRecyclerViewAdapter2 = this.adapter;
        Intrinsics.checkNotNull(salesSerialGroupRecyclerViewAdapter2);
        salesSerialGroupRecyclerViewAdapter2.setHasStableIds(true);
        RecyclerView recyclerView2 = this.recyclerView;
        Intrinsics.checkNotNull(recyclerView2);
        recyclerView2.setAdapter(this.adapter);
        setToolbar();
        this.screenControl = new ScreenControl(this);
        setTitle(R.string.str_serial_group);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(SalesSeriLotActivity.SERILOT_LIST)) {
            this.tmpList = extras.getParcelableArrayList(SalesSeriLotActivity.SERILOT_LIST);
        }
        loadData(this.itemRef, this.wareHouse, this.salesType, this.mHasVariant, this.mVariantRef, this.mHasStockTrack);
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity2);
    }

    public static void onCreatelambda0(SalesSerialGroupActivity this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.loadData(this0.itemRef, this0.wareHouse, this0.salesType, this0.mHasVariant, this0.mVariantRef, this0.mHasStockTrack);
    }

    public static boolean onCreatelambda1(SalesSerialGroupActivity this0, TextView textView, int i2, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (i2 != 3 && i2 != 6 && (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 66)) {
            return false;
        }
        this0.isBarcodeScan = true;
        this0.loadData(this0.itemRef, this0.wareHouse, this0.salesType, this0.mHasVariant, this0.mVariantRef, this0.mHasStockTrack);
        return true;
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
        menu.findItem(R.id.menu_range_select).setVisible(false);
        menu.findItem(R.id.menu_select_all).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        int itemId = item.getItemId();
        if (itemId == 16908332) {
            onBackPressed();
            return false;
        }
        if (itemId == R.id.menu_barcode) {
            scanFromActivity();
            return false;
        }
        return super.onOptionsItemSelected(item);
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
            Intrinsics.checkNotNull(barcode);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("onActivityResult: ");
            Intrinsics.checkNotNull(barcodeItem);
            sb.append(barcodeItem.getKey().getBarcode());
            Log.i(MobileSales.TAG, sb.toString());
            barcode = barcodeItem.getKey().getBarcode();
            Intrinsics.checkNotNull(barcode);
        }
        this.isBarcodeScan = true;
        EditText editText = this.edtSearch;
        Intrinsics.checkNotNull(editText);
        editText.setText(barcode);
        loadData(this.itemRef, this.wareHouse, this.salesType, this.mHasVariant, this.mVariantRef, this.mHasStockTrack);
    }

    public void loadData(int i2, int i3, SalesType salesType, boolean z, int i4, boolean z2) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        if (ContextUtils.checkInternetConnection()) {
            this.mProgressDialogBuilder.setMessage(getString(R.string.str_get_product_please_wait)).show();
            this.viewModel.getCheckSeriGroup(i2, i3, salesType, z, i4, z2, new SalesSeriGrupResponseListener(this));
        }
    }

    private record SalesSeriGrupResponseListener(
            WeakReference<SalesSerialGroupActivity> mSalesSeriGrupActivity) implements ResponseListener<ArrayList<CheckSeriGroup>> {
            private SalesSeriGrupResponseListener(SalesSerialGroupActivity mSalesSeriGrupActivity) {
                this.mSalesSeriGrupActivity = new WeakReference<>(mSalesSeriGrupActivity);
            }

            public void onResponse(PrintSlipModel arrayList) {
                if (this.mSalesSeriGrupActivity.get() != null) {
                    SalesSerialGroupActivity salesSerialGroupActivity = this.mSalesSeriGrupActivity.get();
                    Intrinsics.checkNotNull(salesSerialGroupActivity);
                    if (salesSerialGroupActivity.isActivityDestroyed()) {
                        return;
                    }
                    SalesSerialGroupActivity salesSerialGroupActivity2 = this.mSalesSeriGrupActivity.get();
                    Intrinsics.checkNotNull(salesSerialGroupActivity2);
                    salesSerialGroupActivity2.sendResponse(arrayList, "");
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mSalesSeriGrupActivity.get() != null) {
                    SalesSerialGroupActivity salesSerialGroupActivity = this.mSalesSeriGrupActivity.get();
                    Intrinsics.checkNotNull(salesSerialGroupActivity);
                    if (salesSerialGroupActivity.isActivityDestroyed()) {
                        return;
                    }
                    Log.d(MobileSales.TAG, "onError: " + errorMessage);
                    SalesSerialGroupActivity salesSerialGroupActivity2 = this.mSalesSeriGrupActivity.get();
                    Intrinsics.checkNotNull(salesSerialGroupActivity2);
                    salesSerialGroupActivity2.sendResponse(null, errorMessage);
                }
            }
        }
    public void onBackPressed() {
        super.onBackPressed();
        checkSeriGroup(true);
    }

    public void checkSeriGroup(boolean z) {
        if (z) {
            SalesSerialGroupRecyclerViewAdapter salesSerialGroupRecyclerViewAdapter = this.adapter;
            Intrinsics.checkNotNull(salesSerialGroupRecyclerViewAdapter);
            if (salesSerialGroupRecyclerViewAdapter.getItemCount() > 0) {
                SalesSerialGroupRecyclerViewAdapter salesSerialGroupRecyclerViewAdapter2 = this.adapter;
                Intrinsics.checkNotNull(salesSerialGroupRecyclerViewAdapter2);
                if (salesSerialGroupRecyclerViewAdapter2.getCheckedGroupListSize() > 0) {
                    Intent intent = new Intent();
                    SalesSerialGroupRecyclerViewAdapter salesSerialGroupRecyclerViewAdapter3 = this.adapter;
                    Intrinsics.checkNotNull(salesSerialGroupRecyclerViewAdapter3);
                    intent.putParcelableArrayListExtra(SalesSeriLotActivity.SERILOT_LIST, salesSerialGroupRecyclerViewAdapter3.getCheckedGroupList());
                    intent.putExtra(IntentExtraName.EXTRA_ITEM_ID, this.itemRef);
                    setResult(-1, intent);
                } else {
                    Intent intent2 = new Intent();
                    intent2.putExtra(IntentExtraName.EXTRA_ITEM_ID, this.itemRef);
                    setResult(0, intent2);
                }
            } else {
                Intent intent3 = new Intent();
                intent3.putExtra(IntentExtraName.EXTRA_ITEM_ID, this.itemRef);
                setResult(0, intent3);
            }
            finish();
        }
    }

    public void sendResponse(ArrayList<CheckSeriGroup> arrayList, String str) {
        if (arrayList == null) {
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        } else {
            RecyclerView recyclerView = this.recyclerView;
            Intrinsics.checkNotNull(recyclerView);
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            Intrinsics.checkNotNull(layoutManager);
            layoutManager.removeAllViews();
            EditText editText = this.edtSearch;
            Intrinsics.checkNotNull(editText);
            ArrayList<CheckSeriGroup> extractAvailableSeriGroups = extractAvailableSeriGroups(arrayList, editText.getText().toString(), this.tmpList);
            SalesSerialGroupRecyclerViewAdapter salesSerialGroupRecyclerViewAdapter = this.adapter;
            Intrinsics.checkNotNull(salesSerialGroupRecyclerViewAdapter);
            salesSerialGroupRecyclerViewAdapter.setData(extractAvailableSeriGroups);
            if (extractAvailableSeriGroups == null || extractAvailableSeriGroups.size() == 0) {
                Toast.makeText(this, R.string.str_data_not_found, Toast.LENGTH_LONG).show();
            }
            if (extractAvailableSeriGroups != null && extractAvailableSeriGroups.size() > 0 && this.isBarcodeScan) {
                playBeepSound();
            }
        }
        this.isBarcodeScan = false;
        EditText editText2 = this.edtSearch;
        Intrinsics.checkNotNull(editText2);
        editText2.setText("");
        this.mProgressDialogBuilder.dismiss();
    }

    private ArrayList<CheckSeriGroup> extractAvailableSeriGroups(ArrayList<CheckSeriGroup> arrayList, String str, ArrayList<Serilot> arrayList2) {
        ArrayList<CheckSeriGroup> extractAvailableSeriGroups = SeriLotUtils.extractAvailableSeriGroups(arrayList, str, arrayList2);
        if (extractAvailableSeriGroups == null) {
            return null;
        }
        SalesSerialGroupRecyclerViewAdapter salesSerialGroupRecyclerViewAdapter = this.adapter;
        Intrinsics.checkNotNull(salesSerialGroupRecyclerViewAdapter);
        ArrayList<CheckSeriGroup> mSerialGroupChecks = salesSerialGroupRecyclerViewAdapter.getMSerialGroupChecks();
        Intrinsics.checkNotNull(mSerialGroupChecks);
        if (mSerialGroupChecks.size() > 0) {
            SalesSerialGroupRecyclerViewAdapter salesSerialGroupRecyclerViewAdapter2 = this.adapter;
            Intrinsics.checkNotNull(salesSerialGroupRecyclerViewAdapter2);
            ArrayList<CheckSeriGroup> mSerialGroupChecks2 = salesSerialGroupRecyclerViewAdapter2.getMSerialGroupChecks();
            Intrinsics.checkNotNull(mSerialGroupChecks2);
            Iterator<CheckSeriGroup> it = mSerialGroupChecks2.iterator();
            while (it.hasNext()) {
                CheckSeriGroup next = it.next();
                Iterator<CheckSeriGroup> it2 = extractAvailableSeriGroups.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        CheckSeriGroup next2 = it2.next();
                        if (next.getLogicalRef() == next2.getLogicalRef() && SeriLotUtils.checkStartNumberInRange(next.getGrpBegCode(), next2.getGrpBegCode(), next2.getGrpEndCode())) {
                            next2.setUsedGrpBegCode(next.getGrpBegCode());
                            next2.setUsedGrpEndCode(next.getGrpEndCode());
                            next2.setUsedAmount(next.getUsedAmount());
                            next2.setId(next.getId());
                            next2.setDetailRef(next.getDetailRef());
                            next2.setLocationCode(next.getLocationCode());
                            next2.setChecked(true);
                            next2.setSeriLotNo(next.getSeriLotNo());
                            next2.setStTransRef(next.getStTransRef());
                            break;
                        }
                    }
                }
            }
        }
        return extractAvailableSeriGroups;
    }

    public void hideKeyboard() {
        ScreenControl screenControl = this.screenControl;
        Intrinsics.checkNotNull(screenControl);
        screenControl.hideKeyboard(this);
    }

    /* compiled from: SalesSerialGroupActivity.kt */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
