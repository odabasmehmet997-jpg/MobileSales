package com.proje.mobilesales.features.activity;

import android.Manifest;
import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.droidbyme.toastlib.ToastEnum;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.BarcodeReaderType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.TrackType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.IBarcodeSearchResult;
import com.proje.mobilesales.core.interfaces.ISeriLotCaller;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.core.view.SnappyLinearLayoutManager;
import com.proje.mobilesales.core.widget.SlideInLeftAnimator;
import com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter;
import com.proje.mobilesales.features.dbmodel.UnitBarcode;
import com.proje.mobilesales.features.dbmodel.Variant;
import com.proje.mobilesales.features.model.BarcodeItem;
import com.proje.mobilesales.features.model.BarcodeResult;
import com.proje.mobilesales.features.model.BarcodeSettings;
import com.proje.mobilesales.features.model.VariantPriceParams;
import com.proje.mobilesales.features.product.model.Product;
import com.proje.mobilesales.features.product.model.ProductOperationDiscount;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import com.proje.mobilesales.features.sales.view.serialgroup.SalesSerialGroupActivity;
import com.proje.mobilesales.features.sales.view.serilot.SalesSeriLotActivity;

import java.util.*;

import static com.proje.mobilesales.core.utils.AppUtils.playBeepSound;

public class BarcodeScannerView extends BaseInjectableActivity implements DecoratedBarcodeView.TorchListener, IBarcodeSearchResult, ISeriLotCaller {
    public static final int REQUEST_SERIAL_LOT_NO = 201;
    public static final int TYPE_UNIT_BARCODE_WITH_AMOUNT = 1;
    public static final int UNIT_BARCODE_AMOUNT_CHAR_COUNT = 5;
    public static final int UNIT_BARCODE_BARCODE_CHAR_COUNT = 7;
    public static final int UNIT_BARCODE_WITH_AMOUNT_TOTAL_COUNT = 13;
    private BarcodeScannerViewRecyclerViewAdapter adapter;
    private DecoratedBarcodeView barcodeScannerView;
    private BarcodeUtil barcodeUtil;
    private Toast currentToast;
    private EditText edtLaserBarcode;
    private AppCompatImageView imgFlash;
    private LinearLayout ln_barcode;
    private LinearLayout ln_laser;
    private LinearLayout ln_product_list;
    AlertDialogBuilder mAlertDialogBuilder;
    private BarcodeRegex mBarcodeRegex;
    private BarcodeSettings mBarcodeSettings;
    BaseErp mBaseErp;
    private Drawable mCloseFlashDrawable;
    private int mCustomerRef;
    private int mDefOrderId;
    private int mDivisionNr;
    private Drawable mOpenFlashDrawable;
    private int mPaymentRef;
    private String mPaymentTradeGroup;
    private String mProductGroupCode;
    ProgressDialogBuilder mProgressDialogBuilder;
    SalesType mSalesType;
    private String mSpecode;
    private int mWareHouseNr;
    private RecyclerView recyclerView;
    private RecyclerView rwProductList;
    ISqlManager sqlManager;
    private TextView txtEmptyList;
    public static final String EXTRA_CUSTOMER_REF = BarcodeScannerView.class.getName() + ".EXTRA_CUSTOMER_REF";
    public static final String EXTRA_SALES_TYPE = BarcodeScannerView.class.getName() + ".EXTRA_SALES_TYPE";
    public static final String EXTRA_WAREHOUSE_NR = BarcodeScannerView.class.getName() + ".EXTRA_WAREHOUSE_NR";
    public static final String EXTRA_DEF_ORDER_ID = BarcodeScannerView.class.getName() + ".EXTRA_DEF_ORDER_ID";
    public static final String EXTRA_PAYMENT_TRADE_GROUP = BarcodeScannerView.class.getName() + ".EXTRA_PAYMENT_TRADE_GROUP";
    public static final String EXTRA_PAYMENT_REF = BarcodeScannerView.class.getName() + ".EXTRA_PAYMENT_REF";
    public static final String EXTRA_PRODUCT_GROUP_CODE = BarcodeScannerView.class.getName() + ".EXTRA_PRODUCT_GROUP_CODE";
    public static final String EXTRA_DIVISION_NR = BarcodeScannerView.class.getName() + ".EXTRA_DIVISION_NR";
    public static final String EXTRA_SPECODE = BarcodeScannerView.class.getName() + ".EXTRA_SPECODE";
    boolean mFlashOpen;
    private boolean laser;
    boolean mSingleSelect;
    boolean mForSearch;
    private final ArrayList<BarcodeItem> mDatas = new ArrayList<>();
    private final HashMap<BarcodeResult, Product> mBarcodeList = new HashMap<>();
    private final ArrayList<Product> selectedProducts = new ArrayList<>();
    private final BarcodeCallback singleCallBack = new BarcodeCallback() {
        public void possibleResultPoints(final List<ResultPoint> list) {
        } 
        public void barcodeResult(final com.journeyapps.barcodescanner.BarcodeResult barcodeResult) {
            barcodeScannerView.pause();
            BarcodeResult checkBarcode = checkBarcode(barcodeResult.getText());
            final BarcodeResult barcodeResult2 = new BarcodeResult("");
            if (null == checkBarcode) {
                barcodeResult2.setBarcode(barcodeResult.getText());
            }
            final BarcodeUtil barcodeUtil = BarcodeScannerView.this.barcodeUtil;
            if (null == checkBarcode) {
                checkBarcode = barcodeResult2;
            }
            barcodeUtil.getProductFromBarcode(checkBarcode);
        }
    };
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        this.setContentView(R.layout.new_barcode);
        this.getIntentExtras();
        this.init();
        if (this.laser) {
            return;
        }
        barcodeScannerView.resume();
    }
    private void getIntentExtras() {
        if (null == getIntent() || null == getIntent().getExtras()) {
            return;
        }
        mCustomerRef = this.getIntent().getExtras().getInt(BarcodeScannerView.EXTRA_CUSTOMER_REF, 0);
        mSalesType = SalesType.fromSalesType(this.getIntent().getExtras().getInt(BarcodeScannerView.EXTRA_SALES_TYPE, -1));
        mWareHouseNr = this.getIntent().getExtras().getInt(BarcodeScannerView.EXTRA_WAREHOUSE_NR, 0);
        mDefOrderId = this.getIntent().getExtras().getInt(BarcodeScannerView.EXTRA_DEF_ORDER_ID, 0);
        mPaymentTradeGroup = this.getIntent().getExtras().getString(BarcodeScannerView.EXTRA_PAYMENT_TRADE_GROUP, "");
        mPaymentRef = this.getIntent().getExtras().getInt(BarcodeScannerView.EXTRA_PAYMENT_REF, 0);
        mSingleSelect = this.getIntent().getBooleanExtra(IntentExtraName.EXTRA_SELECT_TYPE, true);
        mForSearch = this.getIntent().getBooleanExtra(IntentExtraName.BARCODE_FOR_SEARCH, false);
        mProductGroupCode = this.getIntent().getExtras().getString(BarcodeScannerView.EXTRA_PRODUCT_GROUP_CODE, "");
        mDivisionNr = this.getIntent().getExtras().getInt(BarcodeScannerView.EXTRA_DIVISION_NR, -99);
        mSpecode = this.getIntent().getExtras().getString(BarcodeScannerView.EXTRA_SPECODE, "");
    }
    public void init() {
        ln_barcode = this.findViewById(R.id.ln_barcode);
        ln_laser = this.findViewById(R.id.ln_laser);
        ln_product_list = this.findViewById(R.id.ln_product_list);
        barcodeScannerView = this.findViewById(R.id.zxing_barcode_scanner);
        imgFlash = this.findViewById(R.id.img_switch_flashlight);
        mOpenFlashDrawable = ContextCompat.getDrawable(this, R.drawable.ic_flashlight_white_48dp);
        mCloseFlashDrawable = ContextCompat.getDrawable(this, R.drawable.ic_flashlight_off_white_48dp);
        final EditText editText = this.findViewById(R.id.edtLaserBarcode);
        edtLaserBarcode = editText;
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {  
            public boolean onEditorAction(final TextView textView, final int i2, final KeyEvent keyEvent) {
                BarcodeResult checkBarcode = checkBarcode(textView.getText().toString());
                final BarcodeResult barcodeResult = new BarcodeResult("", ErpCreator.getInstance().getmBaseErp().getProductDefaultAmount(mSalesType));
                if (null == checkBarcode) {
                    barcodeResult.setBarcode(textView.getText().toString());
                }
                final BarcodeUtil barcodeUtil = BarcodeScannerView.this.barcodeUtil;
                if (null == checkBarcode) {
                    checkBarcode = barcodeResult;
                }
                barcodeUtil.getProductFromBarcode(checkBarcode);
                return false;
            }
        });
        txtEmptyList = this.findViewById(R.id.txtEmptyList);
        rwProductList = this.findViewById(R.id.rwProductList);
        final BarcodeSettings barcodeSettings = ErpCreator.getInstance().getmBaseErp().getBarcodeSettings();
        mBarcodeSettings = barcodeSettings;
        if (0 < barcodeSettings.getBarcodeTotalCharacter()) {
            mBarcodeRegex = new BarcodeRegex(ContextUtils.formatStringEnglish(R.string.barcode_parse_regex, Integer.valueOf(mBarcodeSettings.getBarcodeTotalCharacter()), Integer.valueOf(mBarcodeSettings.getAmountTotalCharacter() - mBarcodeSettings.getDecimalPoint()), Integer.valueOf(mBarcodeSettings.getDecimalPoint())));
        } else {
            mBarcodeRegex = new BarcodeRegex(this.getString(R.string.barcode_parse_empty_regex));
        }
        barcodeScannerView.setTorchListener(this);
        barcodeScannerView.decodeContinuous(singleCallBack);
        this.initFlash();
        final RecyclerView recyclerView = this.findViewById(R.id.rwProductList);
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new SnappyLinearLayoutManager(this));
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setItemAnimator(new SlideInLeftAnimator(new OvershootInterpolator(2.0f)));
        final BarcodeScannerViewRecyclerViewAdapter barcodeScannerViewRecyclerViewAdapter = new BarcodeScannerViewRecyclerViewAdapter();
        adapter = barcodeScannerViewRecyclerViewAdapter;
        barcodeScannerViewRecyclerViewAdapter.setVariantPriceParams(new VariantPriceParams(mCustomerRef, mPaymentRef, mPaymentTradeGroup, mBaseErp.getLogoSqlHelper().getClCardTradingGrp(mCustomerRef)));
        adapter.setProductOperationDiscount(SalesUtils.isSalesTypeDemandOrWhTransfer(mSalesType) ? new ProductOperationDiscount() : mBaseErp.getSalesFicheOperationDiscount());
        adapter.setSalesType(mSalesType);
        adapter.setCustomerRef(mCustomerRef);
        adapter.setPaymentRef(mPaymentRef);
        adapter.setTradingGrp(mPaymentTradeGroup);
        adapter.setDivisionNr(mDivisionNr);
        adapter.setWhRef(mWareHouseNr);
        adapter.setSpecode(mSpecode);
        adapter.initDisplayOptions(this);
        this.recyclerView.setAdapter(adapter);
        this.setToolbar();
        this.setTitle(R.string.str_barcode_scanning);
        adapter.setData(mDatas);
        barcodeUtil = new BarcodeUtil(this, sqlManager, mBaseErp, mCustomerRef, mSalesType, mWareHouseNr, mPaymentTradeGroup, mPaymentRef, mProductGroupCode, mDivisionNr, mSpecode);
        this.setLaser(mBaseErp.getDefaultBarcodeReaderType());
        if (this.laser) {
            ln_laser.setVisibility(View.VISIBLE);
            ln_barcode.setVisibility(View.GONE);
            ln_product_list.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 90.0f));
            try {
                barcodeScannerView.pauseAndWait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            edtLaserBarcode.requestFocus();
            return;
        }
        ln_laser.setVisibility(View.GONE);
        ln_barcode.setVisibility(View.VISIBLE);
        ln_product_list.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 60.0f));
        barcodeScannerView.resume();
    }
    public void onBackPressed() {
        mAlertDialogBuilder.setTitle(R.string.str_cancel_barcode_question).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                lambdaonBackPressed0(dialogInterface, i2);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { 
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }
    public  void lambdaonBackPressed0(final DialogInterface dialogInterface, final int i2) {
        dialogInterface.dismiss();
        super.onBackPressed();
    }
    private void showProgressToast(@StringRes final int i2) {
        new ToastLib.Builder(this, this.getString(i2)).duration(ToastEnum.SHORT).backgroundColor(ContextCompat.getColor(this, R.color.red)).textColor(ContextCompat.getColor(this, R.color.white)).textSize(18).isBold(true).icon(this.getString(R.string.fa_spinner)).iconColor(ContextCompat.getColor(this, R.color.black)).iconSize(24, 2).corner(8).margin(56).padding(36).spacing(16).stroke(2, ContextCompat.getColor(this, R.color.dark_green)).gravity(80).show();
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onResume() {
        super.onResume();
        if (this.laser) {
            return;
        }
        barcodeScannerView.resume();
    }
    protected void onPause() {
        super.onPause();
        if (this.laser) {
            return;
        }
        barcodeScannerView.pause();
    }
    public void onDestroy() {
        super.onDestroy();
    }
    public boolean onKeyDown(final int i2, final KeyEvent keyEvent) {
        return barcodeScannerView.onKeyDown(i2, keyEvent) || super.onKeyDown(i2, keyEvent);
    }
    private ArrayList<BarcodeResult> getIntentScanResultList() {
        final ArrayList<BarcodeResult> arrayList = new ArrayList<>();
        final Iterator<Map.Entry<BarcodeResult, Product>> it = this.mBarcodeList.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getKey());
        }
        return arrayList;
    }
    private BarcodeItem getIntentScanResult() {
        final BarcodeItem barcodeItem = new BarcodeItem();
        if (0 >= getmBarcodeList().size()) {
            return null;
        }
        for (final Map.Entry<BarcodeResult, Product> entry : this.mBarcodeList.entrySet()) {
            barcodeItem.setKey(entry.getKey());
            barcodeItem.setProduct(entry.getValue());
        }
        return barcodeItem;
    }
    private boolean hasFlash() {
        return this.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }
    public void switchFlashlight() {
        if (mFlashOpen) {
            barcodeScannerView.setTorchOff();
            mFlashOpen = false;
        } else {
            barcodeScannerView.setTorchOn();
            mFlashOpen = true;
        }
    }
    private void initFlash() {
        if (!this.hasFlash()) {
            imgFlash.setVisibility(View.GONE);
        } else {
            imgFlash.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    lambdainitFlash2(view);
                }
            });
        }
    } 
    public  void lambdainitFlash2(final View view) {
        this.switchFlashlight();
    }
    public void confProduct(final BarcodeResult barcodeResult, final Product product) {
        this.mBarcodeList.put(barcodeResult, product);
        this.createIntent();
    } 
    public void onTorchOn() {
        imgFlash.setImageDrawable(mCloseFlashDrawable);
    }
    public void onTorchOff() {
        imgFlash.setImageDrawable(mOpenFlashDrawable);
    }
    private void showErrorToast(final Context context, @StringRes final int i2) {
        final Toast toast = currentToast;
        if (null != toast) {
            toast.cancel();
        }
        this.showToast(context, context.getString(i2));
    }
    private void showToast(final Context context, final String str) {
        final Toast toast = currentToast;
        if (null != toast) {
            toast.cancel();
        }
        currentToast = new ToastLib.Builder(context, str).duration(ToastEnum.SHORT).backgroundColor(ContextCompat.getColor(context, R.color.orange400)).textColor(ContextCompat.getColor(context, R.color.white)).textSize(18).isBold(true).icon(context.getString(R.string.fa_error)).iconColor(ContextCompat.getColor(context, R.color.black)).iconSize(24, 2).corner(8).margin(56).padding(36).spacing(16).stroke(2, ContextCompat.getColor(context, R.color.dark_green)).gravity(80).show().getToast();
    }
    private BarcodeResult getBarcodeRegex(final String str) {
        final ArrayList<String> findBarcodeRegex = mBarcodeRegex.findBarcodeRegex(str);
        if (0 < findBarcodeRegex.size()) {
            return new BarcodeResult(findBarcodeRegex.get(0), 3 == findBarcodeRegex.size() ? StringUtils.convertStringToDouble(String.format("%s.%s", findBarcodeRegex.get(1), findBarcodeRegex.get(2))) : 0.0d);
        }
        return null;
    }
    public BarcodeResult checkBarcode(final String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (mBaseErp.getErpType() == ErpType.TIGER && 13 == str.length()) {
            final BarcodeResult parseUnitBarcode = this.parseUnitBarcode(str);
            return null != parseUnitBarcode ? parseUnitBarcode : this.parseWithBarcodeSettings(str);
        }
        return this.parseWithBarcodeSettings(str);
    }

    private BarcodeResult parseUnitBarcode(final String str) {
        final double convertStringToDouble;
        try {
            final String substring = str.substring(0, 7);
            final List table = mBaseErp.getLogoSqlHelper().getTable(UnitBarcode.class, "BARCODE=? AND TYP=?", new String[]{substring, Integer.toString(1)});
            if (0 == table.size()) {
                return null;
            }
            final String substring2 = str.substring(7, 12);
            int barcodeShift = ((UnitBarcode) table.get(0)).getBarcodeShift();
            if (0 >= barcodeShift) {
                convertStringToDouble = StringUtils.convertStringToDouble(substring2);
            } else {
                if (5 < barcodeShift) {
                    barcodeShift = 5;
                }
                final int i2 = 12 - barcodeShift;
                String sb = str.substring(7, i2) +
                        "." +
                        str.substring(i2, 12);
                convertStringToDouble = StringUtils.convertStringToDouble(sb);
            }
            return new BarcodeResult(substring, convertStringToDouble);
        } catch (final Exception e2) {
            Log.e("BarcodeParseError", "parseUnitBarcode", e2);
            return null;
        }
    }
    private BarcodeResult parseWithBarcodeSettings(final String str) {
        final double convertStringToDouble;
        if (0 >= this.mBarcodeSettings.getBarcodeTotalCharacter()) {
            return null;
        }
        try {
            final String substring = str.substring(mBarcodeSettings.getBarcodeStartCharacter() - 1, mBarcodeSettings.getBarcodeTotalCharacter());
            if (0 == this.mBaseErp.getLogoSqlHelper().getTable(UnitBarcode.class, "BARCODE=?", new String[]{substring}).size()) {
                return null;
            }
            final int barcodeTotalCharacter = mBarcodeSettings.getBarcodeTotalCharacter() + mBarcodeSettings.getAmountTotalCharacter();
            final String substring2 = str.substring(mBarcodeSettings.getBarcodeTotalCharacter(), barcodeTotalCharacter);
            if (0 >= this.mBarcodeSettings.getDecimalPoint()) {
                convertStringToDouble = StringUtils.convertStringToDouble(substring2);
            } else {
                convertStringToDouble = StringUtils.convertStringToDouble(str.substring(mBarcodeSettings.getBarcodeTotalCharacter(), barcodeTotalCharacter - mBarcodeSettings.getDecimalPoint()) + "." + str.substring(barcodeTotalCharacter - mBarcodeSettings.getDecimalPoint(), barcodeTotalCharacter));
            }
            return new BarcodeResult(substring, convertStringToDouble);
        } catch (final Exception e2) {
            Log.e("BarcodeParseError", "parseUnitBarcode", e2);
            return null;
        }
    }
    private boolean itemListControl(final String str) {
        try {
            final Iterator<Map.Entry<BarcodeResult, Product>> it = this.mBarcodeList.entrySet().iterator();
            while (it.hasNext()) {
                if (it.next().getKey().getBarcode().equals(str)) {
                    return false;
                }
            }
            return true;
        } catch (final Exception e2) {
            Log.e(MobileSales.TAG, "itemListControl: ", e2);
            return true;
        }
    }

    private void showSuccessToast(@StringRes final int i2) {
        new ToastLib.Builder(this, this.getString(i2)).duration(ToastEnum.SHORT).backgroundColor(ContextCompat.getColor(this, R.color.green)).textColor(ContextCompat.getColor(this, R.color.white)).textSize(18).isBold(true).icon(this.getString(R.string.fa_success)).iconColor(ContextCompat.getColor(this, R.color.black)).iconSize(24, 2).corner(8).margin(56).padding(36).spacing(16).stroke(2, ContextCompat.getColor(this, R.color.dark_green)).gravity(80).show();
    }

    public boolean showConfButton() {
        final boolean z = mForSearch;
        return (!z || !mSingleSelect) && !z && mSingleSelect;
    }
    public void addToList(final BarcodeResult barcodeResult, final Product product) {
        txtEmptyList.setVisibility(View.GONE);
        rwProductList.setVisibility(View.VISIBLE);
        if (null != this.barcodeUtil.getBarcodeProduct() && mBaseErp.getUseStoredProcedureForBarcode()) {
            ln_product_list.setFocusableInTouchMode(true);
            ln_product_list.setDescendantFocusability(131072);
        } else {
            ln_product_list.setFocusableInTouchMode(false);
            ln_product_list.setDescendantFocusability(262144);
        }
        mDatas.add(0, new BarcodeItem(barcodeResult, product));
        adapter.setData(mDatas);
        ln_product_list.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(final View view, final boolean z) {
                lambdaaddToList3(view, z);
            }
        });
    }
    public  void lambdaaddToList3(final View view, final boolean z) {
        if (z) {
            edtLaserBarcode.requestFocus();
        }
    }

    private void returnResult(final BarcodeResult barcodeResult) {
        final Intent intent = new Intent();
        intent.putExtra("SCAN_RESULT", barcodeResult);
        this.setResult(-1, intent);
        this.finish();
    }

    public HashMap<BarcodeResult, Product> getmBarcodeList() {
        return mBarcodeList;
    }
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_barcode, menu);
        menu.findItem(R.id.menu_ok).setVisible(!this.showConfButton());
        final MenuItem findItem = menu.findItem(R.id.menu_fast_read);
        if (null != findItem) {
            if (this.laser) {
                findItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_barcode_scan_white_24dp));
            } else {
                findItem.setIcon(ContextCompat.getDrawable(this, R.drawable.laser_barcode_white));
            }
        }
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        final int itemId = menuItem.getItemId();
        if (16908332 == itemId) {
            mAlertDialogBuilder.setTitle(R.string.str_cancel_barcode_question).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    lambdaonOptionsItemSelected4(dialogInterface, i2);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {  
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    dialogInterface.dismiss();
                }
            }).create().show();
            return true;
        }
        if (R.id.menu_fast_read != itemId) {
            if (R.id.menu_ok == itemId) {
                this.createIntentDialog();
                return true;
            }
            return super.onOptionsItemSelected(menuItem);
        }
        this.laser = !this.laser;
        if (this.laser) {
            ln_laser.setVisibility(View.VISIBLE);
            ln_barcode.setVisibility(View.GONE);
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_barcode_scan_white_24dp));
            ln_product_list.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 90.0f));
            try {
                barcodeScannerView.pauseAndWait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            edtLaserBarcode.requestFocus();
        } else {
            ln_laser.setVisibility(View.GONE);
            ln_barcode.setVisibility(View.VISIBLE);
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.laser_barcode_white));
            ln_product_list.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 60.0f));
            barcodeScannerView.resume();
        }
        return true;
    } 
    public void lambdaonOptionsItemSelected4(final DialogInterface dialogInterface, final int i2) {
        super.onBackPressed();
        dialogInterface.dismiss();
    }

    private void createIntentDialog() {
        if (0 == this.selectedProducts.size()) {
            mAlertDialogBuilder.setTitle(R.string.str_warning).setMessage(R.string.str_barcode_not_add_product).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    lambdacreateIntentDialog6(dialogInterface, i2);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {  
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        } else {
            mAlertDialogBuilder.setTitle(R.string.str_return_back_question).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    lambdacreateIntentDialog8(dialogInterface, i2);
                }
            }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {  
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        }
    }

    
    public void lambdacreateIntentDialog6(final DialogInterface dialogInterface, final int i2) {
        super.onBackPressed();
        dialogInterface.dismiss();
    }

    
    public void lambdacreateIntentDialog8(final DialogInterface dialogInterface, final int i2) {
        dialogInterface.dismiss();
        this.createIntent();
    }

    private void createIntent() {
        final Intent intent = new Intent();
        if (mSingleSelect) {
            intent.putExtra("SCAN_RESULT", this.getIntentScanResult());
        } else {
            intent.putParcelableArrayListExtra("SCAN_RESULT", this.getIntentScanResultList());
        }
        if (!mForSearch && !this.showConfButton()) {
            intent.putExtra("bigdata:synccode", mBaseErp.saveObject(selectedProducts));
        } else {
            selectedProducts.clear();
        }
        this.setResult(-1, intent);
        this.finish();
    }

    private void laserClearAndFocus() {
        if (this.laser) {
            edtLaserBarcode.getText().clear();
            edtLaserBarcode.setEnabled(true);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    lambdalaserClearAndFocus10();
                }
            }, 200L);
            return;
        }
        barcodeScannerView.resume();
    }

    
    public void lambdalaserClearAndFocus10() {
        edtLaserBarcode.requestFocus();
    }

    public boolean showPromotion(final int i2) {
        if (mBaseErp.getSalesFicheApplyPromotion(mSalesType)) {
            return 0 >= this.selectedProducts.size() || selectedProducts.get(0).getLogicalRef() != i2;
        }
        return false;
    }

    public void removeSelectedProducts(final int i2) {
        final Iterator<Product> it = selectedProducts.iterator();
        while (it.hasNext()) {
            final Product next = it.next();
            if (next.getLogicalRef() == i2) {
                selectedProducts.remove(next);
                return;
            }
        }
    }
    public void getSearchResult(@Nullable final Product product) {
        boolean z;
        if (null == product) {
            this.showErrorToast(ContextUtils.getmContext(), R.string.str_barcode_scan_product_not_find);
        } else {
            if (0 < product.getSalesSerialLots().size()) {
                final Iterator<Product> it = selectedProducts.iterator();
                z = false;
                while (it.hasNext()) {
                    final Product next = it.next();
                    if (next.getLogicalRef() == product.getLogicalRef()) {
                        final Iterator<Serilot> it2 = product.getSalesSerialLots().iterator();
                        while (it2.hasNext()) {
                            final Serilot next2 = it2.next();
                            final Iterator<Serilot> it3 = next.getSalesSerialLots().iterator();
                            while (it3.hasNext()) {
                                if (it3.next().code.equals(next2.code)) {
                                    if (mBaseErp.ptScannedBarcodeIsScannedAgain().booleanValue()) {
                                        this.setAlertDialogForScannedBarcodePreviouslyScanned(next);
                                        this.laserClearAndFocus();
                                        return;
                                    }
                                    this.showErrorToast(ContextUtils.getmContext(), R.string.str_barcode_scan_before);
                                }
                            }
                            next.getSalesSerialLots().add(next2);
                            next.setAmount(next.getAmount() + 1.0d);
                        }
                        z = true;
                    }
                }
            } else {
                final Iterator<Product> it4 = selectedProducts.iterator();
                while (it4.hasNext()) {
                    final Product next3 = it4.next();
                    if (next3.getLogicalRef() == product.getLogicalRef()) {
                        if (mBaseErp.ptScannedBarcodeIsScannedAgain().booleanValue()) {
                            this.setAlertDialogForScannedBarcodePreviouslyScanned(next3);
                            this.laserClearAndFocus();
                            return;
                        }
                        this.showErrorToast(ContextUtils.getmContext(), R.string.str_barcode_scan_before);
                    }
                }
                z = false;
            }
            if (!z) {
                selectedProducts.add(product);
                final BarcodeResult barcodeResult = new BarcodeResult();
                if (0.0d != this.barcodeUtil.getmBarcodeFilter().get(0).getAmount()) {
                    product.setAmount(barcodeUtil.getmBarcodeFilter().get(0).getAmount());
                }
                if (0.0d != this.barcodeUtil.getmBarcodeFilter().get(0).getSecondAmount()) {
                    product.setSecondAmount(barcodeUtil.getmBarcodeFilter().get(0).getSecondAmount());
                }
                if (!TextUtils.isEmpty(barcodeUtil.getmBarcodeFilter().get(0).getSecondUnitCode())) {
                    product.setSecondUnitCode(barcodeUtil.getmBarcodeFilter().get(0).getSecondUnitCode());
                }
                if (null != this.barcodeUtil.getmBarcodeFilter().get(0).getSelectedPriceRefProp() && 0 < this.barcodeUtil.getmBarcodeFilter().get(0).getSelectedPriceRefProp().getLogicalRef()) {
                    product.setPriceRef(barcodeUtil.getmBarcodeFilter().get(0).getSelectedPriceRefProp().getLogicalRef());
                }
                if (null != this.barcodeUtil.getmBarcodeFilter().get(0).getPriceProp() && 0.0d != this.barcodeUtil.getmBarcodeFilter().get(0).getPriceProp().getDefinitionDouble()) {
                    product.setPriceRef(-1);
                    product.setPrice(barcodeUtil.getmBarcodeFilter().get(0).getPriceProp().getDefinitionDouble());
                }
                barcodeResult.setUnitCode(barcodeUtil.getmBarcodeFilter().get(0).getUnitCode());
                barcodeResult.setSecondUnitCode(barcodeUtil.getmBarcodeFilter().get(0).getSecondUnitCode());
                barcodeResult.setAmount(product.getAmount());
                barcodeResult.setSecondAmount(product.getSecondAmount());
                barcodeResult.setBarcode(barcodeUtil.getmBarcodeFilter().get(0).getBarcode());
                barcodeResult.setItemRef(product.getLogicalRef());
                barcodeResult.setSelectedPriceRefProp(barcodeUtil.getmBarcodeFilter().get(0).getSelectedPriceRefProp());
                barcodeResult.setPriceProp(barcodeUtil.getmBarcodeFilter().get(0).getPriceProp());
                product.setSelect(true);
                this.fillVariantInfo(product, barcodeUtil.getBarcodeProduct().getVariant());
                product.setFoundByStoredProcedure(barcodeUtil.getBarcodeProduct().isFoundByStoredProcedure());
                if (product.getFoundByStoredProcedure()) {
                    product.setSearchBarcode(barcodeUtil.getBarcodeProduct().getSearchBarcode());
                }
                this.mBarcodeList.put(barcodeResult, product);
                playBeepSound();
                if (mForSearch) {
                    this.createIntent();
                } else {
                    adapter.initUnitList(new BarcodeItem(barcodeResult, product));
                    this.addToList(barcodeResult, product);
                    this.startFeatureSelectionProcess(product);
                }
            } else {
                adapter.updateData();
            }
        }
        this.laserClearAndFocus();
    }

    private void setAlertDialogForScannedBarcodePreviouslyScanned(Product product) {
        new AlertDialog.Builder(this).setTitle(this.getString(R.string.str_warning)).setMessage(this.getString(R.string.str_barcode_scan_before)).setPositiveButton(this.getString(R.string.str_accept), new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                final BarcodeResult barcodeResult = new BarcodeResult();
                playBeepSound();
                addToList(barcodeResult, product);
                selectedProducts.add(product);
                startFeatureSelectionProcess(product);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                edtLaserBarcode.requestFocus();
            }
        }).create().show();
    }

    private void fillVariantInfo(final Product product, final String str) {
        final List table;
        if (TextUtils.isEmpty(str)) {
            product.setVariantCode("");
            return;
        }
        if (mBaseErp.getErpType() == ErpType.NETSIS) {
            table = mBaseErp.getLogoSqlHelper().getTable(Variant.class, "ITEMCODE = ? AND CODE = ?", new String[]{product.getCode(), str});
        } else {
            table = mBaseErp.getLogoSqlHelper().getTable(Variant.class, "ITEMREF = ? AND CODE = ?", new String[]{StringUtils.convertIntToString(product.getLogicalRef()), str});
        }
        if (null == table || table.isEmpty()) {
            return;
        }
        product.setVariantCode(str);
        product.setVariantName(((Variant) table.get(0)).getName());
        product.setVariantRef(((Variant) table.get(0)).getLogicalRef());
    }

    
    public void startFeatureSelectionProcess(final Product product) {
        if (product.getVariant()) {
            adapter.showVariantSelectionDialog(product, 0);
        } else {
            if (0 == product.getTrackType() || mSalesType == SalesType.ORDER) {
                return;
            }
            this.openSeriLotActivity(product);
        }
    }
    public void onActivityResult(final int i2, final int i3, final Intent intent) {
        final Product findProduct;
        final ArrayList<Serilot> parcelableArrayList;
        final Product findProduct2;
        super.onActivityResult(i2, i3, intent);
        if (201 == i2) {
            final Bundle extras = intent.getExtras();
            final int i4 = null != extras ? extras.getInt(IntentExtraName.EXTRA_ITEM_ID, 0) : 0;
            if (-1 != i3) {
                if (0 != i3 || null == (findProduct = findProduct(i4))) {
                    return;
                }
                findProduct.getSalesSerialLots().clear();
                findProduct.setAmount(0.0d);
                adapter.updateData();
                return;
            }
            if (null == extras || !extras.containsKey(SalesSeriLotActivity.SERILOT_LIST) || null == (parcelableArrayList = extras.getParcelableArrayList(SalesSeriLotActivity.SERILOT_LIST)) || null == (findProduct2 = findProduct(i4))) {
                return;
            }
            findProduct2.setSalesSerialLots(parcelableArrayList);
            final double calculateSeriLotAmount = CalculateUtils.calculateSeriLotAmount(mBaseErp.getLogoSqlHelper().getItemUnits(findProduct2.getLogicalRef()), findProduct2.getSalesSerialLots());
            final double d2 = extras.getDouble(SalesSeriLotActivity.SURPLUS_AMOUNT, 0.0d);
            findProduct2.setAmount(calculateSeriLotAmount - d2);
            findProduct2.setSurplusAmount(d2);
            adapter.updateData();
        }
    }

    private Product findProduct(final int i2) {
        final Iterator<BarcodeItem> it = mDatas.iterator();
        while (it.hasNext()) {
            final BarcodeItem next = it.next();
            if (next.getKey().getItemRef() == i2) {
                return next.getProduct();
            }
        }
        return null;
    }

    public boolean isLaser() {
        return laser;
    }

    public void setLaser(final boolean z) {
        laser = z;
    }

    public void setLaser(final BarcodeReaderType barcodeReaderType) {
        laser = barcodeReaderType == BarcodeReaderType.Laser;
    }

    public void openSeriLotActivity(final Product product) {
        if (product.getTrackType() != TrackType.SERIAL_GROUP.getType()) {
            final Intent intent = new Intent(this, SalesSeriLotActivity.class);
            intent.putParcelableArrayListExtra(SalesSeriLotActivity.SERILOT_LIST, product.getSalesSerialLots());
            intent.putExtra(SalesSeriLotActivity.EXTRA_TRACK_TYPE, product.getTrackType());
            intent.putExtra(SalesSeriLotActivity.EXTRA_ITEM_REF, product.getLogicalRef());
            intent.putExtra(SalesSeriLotActivity.EXTRA_SALES_TYPE, mSalesType.toString());
            intent.putExtra(SalesSeriLotActivity.EXTRA_WAREHOUSE_NR, mWareHouseNr);
            intent.putExtra(SalesSeriLotActivity.EXTRA_HAS_ORDER_REF, false);
            intent.putExtra(SalesSeriLotActivity.EXTRA_ORDER_AVAILABLE_AMOUNT, 0);
            intent.putExtra("INVENNO", mWareHouseNr);
            intent.putExtra(SalesSeriLotActivity.EXTRA_HAS_VARIANT, product.getVariant());
            intent.putExtra(SalesSeriLotActivity.EXTRA_VARIANT_REF, product.getVariantRef());
            intent.putExtra(SalesSeriLotActivity.EXTRA_HAS_STOCK_TRACK, 1 == product.getLocTracking());
            intent.putExtra(SalesSeriLotActivity.EXTRA_IS_DIV_UNIT, product.getDivUnit());
            intent.putExtra(SalesSeriLotActivity.EXTRA_SURPLUS_AMOUNT, product.getSurplusAmount());
            this.startActivityForResult(intent, 201);
            return;
        }
        final Intent intent2 = new Intent(this, SalesSerialGroupActivity.class);
        intent2.putExtra(SalesSerialGroupActivity.EXTRA_ITEM_REF, product.getLogicalRef());
        intent2.putExtra(SalesSerialGroupActivity.EXTRA_SALES_TYPE, mSalesType.toString());
        intent2.putExtra(SalesSerialGroupActivity.EXTRA_WAREHOUSE_NR, mWareHouseNr);
        intent2.putExtra(SalesSerialGroupActivity.EXTRA_HAS_VARIANT, product.getVariant());
        intent2.putExtra(SalesSerialGroupActivity.EXTRA_VARIANT_REF, product.getVariantRef());
        intent2.putExtra(SalesSerialGroupActivity.EXTRA_HAS_STOCK_TRACK, 1 == product.getLocTracking());
        intent2.putParcelableArrayListExtra(SalesSeriLotActivity.SERILOT_LIST, product.getSalesSerialLots());
        this.startActivityForResult(intent2, 201);
    }
}
