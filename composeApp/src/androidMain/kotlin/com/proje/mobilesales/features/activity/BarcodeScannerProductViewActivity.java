package com.proje.mobilesales.features.activity;

import android.Manifest;
import android.R;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.core.view.InputDeviceCompat;
import com.droidbyme.toastlib.ToastEnum;
import com.droidbyme.toastlib.ToastLib;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.utils.BarcodeRegex;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.adapter.BarcodeAdapter;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.sales.model.Sales;
import org.jetbrains.annotations.UnknownNullability;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BarcodeScannerProductViewActivity extends BaseInjectableActivity implements DecoratedBarcodeView.TorchListener {
    public static final String EXTRA_SELECT_TYPE = BarcodeScannerProductViewActivity.class.getName() + ".EXTRA_SELECT_TYPE";
    private static final String STATE_BARCODE_LIST = "state:mBarcodeList";
    private static final String STATE_BARCODE_SETTINGS = "state:mBarcodeSettings";
    private static final String STATE_CAMERA_PAUSE = "state:cameraPause";
    private static final String STATE_FLASH_OPEN = "state:flashOpen";
    private static final String STATE_SELECT_TYPE = "state:selectType";
    private BarcodeAdapter barcodeAdapter;
    private DecoratedBarcodeView barcodeScannerView;
    CaptureManager captureManager;
    private AppCompatImageView imgCameraPause;
    private AppCompatImageView imgClose;
    private AppCompatImageView imgComplete;
    private AppCompatImageView imgFlash;
    private AppCompatImageView imgLastBarcode;
    private ListView listViewBarcode;
    private LinearLayout lnBarcodeTextContainer;
    AlertDialogBuilder mAlertDialogBuilder;
    private BarcodeRegex mBarcodeRegex;
    private BarcodeSettings mBarcodeSettings;
    BaseErp mBaseErp;
    Drawable mCloseFlashDrawable;
    Drawable mOpenFlashDrawable;
    Drawable mPauseDrawable;
    Drawable mPlayDrawable;
    ISqlManager sqlManager;
    boolean mFlashOpen;
    boolean mCameraPause;
    boolean mSingleSelect;
    private HashMap<BarcodeResult, BarcodeNew> mBarcodeList = new HashMap<>();
    private final BarcodeCallback singleCallBack = new C26011();
    private final BarcodeCallback callback = new BarcodeCallback() {
        public void possibleResultPoints(final List<ResultPoint> list) {
        }
        public void barcodeResult(final com.journeyapps.barcodescanner.BarcodeResult barcodeResult) {
            final String text = null == barcodeResult.getText() ? "" : barcodeResult.getText();
            if (TextUtils.isEmpty(text)) {
                return;
            }
            if (mBarcodeList.containsKey(text)) {
                showErrorToast(R.string.str_barcode_scan_product_add_before);
                return;
            }
            barcodeScannerView.pause();
            showProgressToast(R.string.str_product_barcode_search_please_wait);
            BarcodeResult barcodeRegex = getBarcodeRegex(text);
            if (null == barcodeRegex) {
                barcodeRegex = new BarcodeResult(text);
            }
            final BarcodeScannerProductViewActivity barcodeScannerProductViewActivity = BarcodeScannerProductViewActivity.this;
            barcodeScannerProductViewActivity.sqlManager.getBarcodeResult(barcodeScannerProductViewActivity, new BarcodeProductAddListener(barcodeScannerProductViewActivity, barcodeResult, barcodeRegex), mBaseErp.getLogoSqlHelper().getBarcodeScannerSql(), R.string.column_item_ref, barcodeRegex.getBarcode());
        }
    };
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        this.setContentView(R.layout.activity_custom_barcode_view);
        barcodeScannerView = this.findViewById(R.id.zxing_barcode_scanner);
        imgFlash = this.findViewById(R.id.img_switch_flashlight);
        imgClose = this.findViewById(R.id.img_close);
        imgCameraPause = this.findViewById(R.id.img_pause);
        imgLastBarcode = this.findViewById(R.id.barcodePreview);
        imgComplete = this.findViewById(R.id.img_complete);
        lnBarcodeTextContainer = this.findViewById(R.id.ln_barcodeTextContainer);
        listViewBarcode = this.findViewById(R.id.lst_barcode);
        mOpenFlashDrawable = ContextCompat.getDrawable(this, R.drawable.ic_flashlight_white_48dp);
        mCloseFlashDrawable = ContextCompat.getDrawable(this, R.drawable.ic_flashlight_off_white_48dp);
        mPauseDrawable = ContextCompat.getDrawable(this, R.drawable.ic_pause_white_48dp);
        mPlayDrawable = ContextCompat.getDrawable(this, R.drawable.ic_play_white_48dp);
        if (null != bundle) {
            mFlashOpen = bundle.getBoolean(BarcodeScannerProductViewActivity.STATE_FLASH_OPEN, false);
            mCameraPause = bundle.getBoolean(BarcodeScannerProductViewActivity.STATE_CAMERA_PAUSE, false);
            mSingleSelect = bundle.getBoolean(BarcodeScannerProductViewActivity.STATE_SELECT_TYPE, true);
            mBarcodeSettings = bundle.getParcelable(BarcodeScannerProductViewActivity.STATE_BARCODE_SETTINGS);
            if (!mSingleSelect) {
                mBarcodeList = (HashMap) bundle.getSerializable(BarcodeScannerProductViewActivity.STATE_BARCODE_LIST);
            }
        } else {
            mSingleSelect = this.getIntent().getBooleanExtra(BarcodeScannerProductViewActivity.EXTRA_SELECT_TYPE, true);
            final BarcodeSettings barcodeSettings = ErpCreator.getInstance().getmBaseErp().getBarcodeSettings();
            mBarcodeSettings = barcodeSettings;
            if (0 < barcodeSettings.getBarcodeTotalCharacter()) {
                mBarcodeRegex = new BarcodeRegex(ContextUtils.formatStringEnglish(R.string.barcode_parse_regex, Integer.valueOf(mBarcodeSettings.getBarcodeTotalCharacter()), Integer.valueOf(mBarcodeSettings.getAmountTotalCharacter() - mBarcodeSettings.getDecimalPoint()), Integer.valueOf(mBarcodeSettings.getDecimalPoint())));
            } else {
                mBarcodeRegex = new BarcodeRegex(this.getString(R.string.barcode_parse_empty_regex));
            }
        }
        barcodeScannerView.setTorchListener(this);
        if (!mSingleSelect) {
            barcodeScannerView.decodeContinuous(callback);
            this.initComplete();
            this.initAdapter();
        } else {
            final CaptureManager captureManager = new CaptureManager(this, barcodeScannerView);
            this.captureManager = captureManager;
            captureManager.initializeFromIntent(this.getIntent(), bundle);
            barcodeScannerView.decodeSingle(singleCallBack);
            imgComplete.setVisibility(View.GONE);
            lnBarcodeTextContainer.setVisibility(View.GONE);
        }
        barcodeScannerView.setStatusText("");
        this.initFlash();
        this.initClose();
        this.initCameraPause();
    }
    private void initAdapter() {
        final BarcodeAdapter barcodeAdapter = new BarcodeAdapter(this);
        this.barcodeAdapter = barcodeAdapter;
        listViewBarcode.setAdapter(barcodeAdapter);
    }
    private void initComplete() {
        imgComplete.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                lambdainitComplete0(view);
            }
        });
    }
    public void lambdainitComplete0(final View view) {
        this.createIntentDialog();
    }
    private void initFlash() {
        if (!this.hasFlash()) {
            imgFlash.setVisibility(View.GONE);
        } else {
            imgFlash.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    lambdainitFlash1(view);
                }
            });
        }
    }
    public void lambdainitFlash1(final View view) {
        this.switchFlashlight();
    }
    private void initClose() {
        imgClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                lambdainitClose2(view);
            }
        });
    }
    public void lambdainitClose2(final View view) {
        this.onBackPressed();
    }
    public void onBackPressed() {
        super.onBackPressed();
        mAlertDialogBuilder.setTitle(R.string.str_cancel_barcode_question).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                lambdaonBackPressed3(dialogInterface, i2);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }
    public void lambdaonBackPressed3(final DialogInterface dialogInterface, final int i2) {
        super.onBackPressed();
        dialogInterface.dismiss();
    }
    private void createIntentDialog() {
        if (0 == this.mBarcodeList.size()) {
            mAlertDialogBuilder.setTitle(R.string.str_warning).setMessage(R.string.str_barcode_not_add_product).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    lambdacreateIntentDialog5(dialogInterface, i2);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        } else {
            mAlertDialogBuilder.setTitle(R.string.str_return_back_question).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    lambdacreateIntentDialog7(dialogInterface, i2);
                }
            }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        }
    }
    public void lambdacreateIntentDialog5(final DialogInterface dialogInterface, final int i2) {
        super.onBackPressed();
        dialogInterface.dismiss();
    }
    public void lambdacreateIntentDialog7(final DialogInterface dialogInterface, final int i2) {
        dialogInterface.dismiss();
        this.createIntent();
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    private void createIntent() {
        final Intent intent = new Intent();
        intent.putParcelableArrayListExtra("SCAN_RESULT", this.getIntentScanResult());
        this.setResult(-1, intent);
        this.finish();
    }
    private void initCameraPause() {
        imgCameraPause.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                lambdainitCameraPause9(view);
            }
        });
    }
    public void lambdainitCameraPause9(final View view) {
        this.toggleCamera();
    }
    private void toggleCamera() {
        if (mCameraPause) {
            barcodeScannerView.resume();
            mCameraPause = false;
            imgCameraPause.setImageDrawable(mPauseDrawable);
        } else {
            barcodeScannerView.pause();
            mCameraPause = true;
            imgCameraPause.setImageDrawable(mPlayDrawable);
        }
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onResume() {
        super.onResume();
        barcodeScannerView.resume();
    }
    protected void onPause() {
        super.onPause();
        barcodeScannerView.pause();
    }
    public void onDestroy() {
        super.onDestroy();
    }
    public void onSaveInstanceState(final Bundle bundle) {
        bundle.putBoolean(BarcodeScannerProductViewActivity.STATE_FLASH_OPEN, mFlashOpen);
        bundle.putBoolean(BarcodeScannerProductViewActivity.STATE_CAMERA_PAUSE, mCameraPause);
        bundle.putBoolean(BarcodeScannerProductViewActivity.STATE_SELECT_TYPE, mSingleSelect);
        bundle.putSerializable(BarcodeScannerProductViewActivity.STATE_BARCODE_LIST, mBarcodeList);
        bundle.putParcelable(BarcodeScannerProductViewActivity.STATE_BARCODE_SETTINGS, mBarcodeSettings);
        super.onSaveInstanceState(bundle);
    }
    public boolean onKeyDown(final int i2, final KeyEvent keyEvent) {
        return barcodeScannerView.onKeyDown(i2, keyEvent) || super.onKeyDown(i2, keyEvent);
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
    public void onTorchOn() {
        imgFlash.setImageDrawable(mCloseFlashDrawable);
    }
    public void onTorchOff() {
        imgFlash.setImageDrawable(mOpenFlashDrawable);
    }

    public void triggerScan(final View view) {
        barcodeScannerView.decodeSingle(callback);
    }

    private ArrayList<BarcodeResult> getIntentScanResult() {
        final ArrayList<BarcodeResult> arrayList = new ArrayList<>();
        final Iterator<Map.Entry<BarcodeResult, BarcodeNew>> it = mBarcodeList.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getKey());
        }
        return arrayList;
    }
    private boolean itemListControl(final com.journeyapps.barcodescanner.BarcodeResult barcodeResult) {
        try {
            final Iterator<Map.Entry<BarcodeResult, BarcodeNew>> it = mBarcodeList.entrySet().iterator();
            while (it.hasNext()) {
                if (it.next().getKey().getBarcode().equals(barcodeResult.getText())) {
                    return false;
                }
            }
            return true;
        } catch (final Exception e2) {
            Log.e(MobileSales.TAG, "itemListControl: ", e2);
            return true;
        }
    }
    public void returnResult(final BarcodeResult barcodeResult) {
        final Intent intent = new Intent();
        intent.putExtra("SCAN_RESULT", barcodeResult);
        this.setResult(-1, intent);
        this.finish();
    }
    class C26011 implements BarcodeCallback {
        public void possibleResultPoints(final List<ResultPoint> list) {
        }
        C26011() {
        }
        public void barcodeResult(final com.journeyapps.barcodescanner.BarcodeResult barcodeResult) {
            barcodeScannerView.pause();
            BarcodeResult barcodeRegex = getBarcodeRegex(barcodeResult.getText());
            BarcodeResult barcodeResult2 = new BarcodeResult("");
            if (null == barcodeRegex) {
                barcodeResult2.setBarcode(barcodeResult.getText());
            }
            final BarcodeScannerProductViewActivity barcodeScannerProductViewActivity = BarcodeScannerProductViewActivity.this;
            final BarcodeProduct singleBarcodeResult = barcodeScannerProductViewActivity.sqlManager.getSingleBarcodeResult(barcodeScannerProductViewActivity, barcodeScannerProductViewActivity.mBaseErp.getLogoSqlHelper().getBarcodeScannerSql(), R.string.column_item_ref, null == barcodeRegex ? barcodeResult2.getBarcode() : barcodeRegex.getBarcode());
            if (null == barcodeRegex) {
                barcodeResult2.setItemRef(singleBarcodeResult.getItemRef());
            } else {
                barcodeRegex.setItemRef(singleBarcodeResult.getItemRef());
            }
            new Handler().post(new Runnable() {
                public void run() {
                    lambdabarcodeResult0(barcodeRegex, barcodeResult2);
                }
            });
        }
        public void lambdabarcodeResult0(BarcodeResult barcodeResult, final BarcodeResult barcodeResult2) {
            final BarcodeScannerProductViewActivity barcodeScannerProductViewActivity = BarcodeScannerProductViewActivity.this;
            if (null == barcodeResult) {
                barcodeResult = barcodeResult2;
            }
            barcodeScannerProductViewActivity.returnResult(barcodeResult);
        }
    }
    public BarcodeResult getBarcodeRegex(final String str) {
        final ArrayList<String> findBarcodeRegex = mBarcodeRegex.findBarcodeRegex(str);
        if (!findBarcodeRegex.isEmpty()) {
            return new BarcodeResult(findBarcodeRegex.get(0), 3 == findBarcodeRegex.size() ? StringUtils.convertStringToDouble(String.format("%s.%s", findBarcodeRegex.get(1), findBarcodeRegex.get(2))) : 0.0d);
        }
        return null;
    }
    public void onItemFoundResult(final @UnknownNullability PrintSlipModel barcodeNew, final com.journeyapps.barcodescanner.BarcodeResult barcodeResult, final BarcodeResult barcodeResult2) {
        if (null != barcodeNew) {
            if (this.itemListControl(barcodeResult)) {
                barcodeResult2.setItemRef(barcodeNew.getItemRef());
                mBarcodeList.put(barcodeResult2, barcodeNew);
                barcodeAdapter.addItems(barcodeNew);
                this.showSuccessToast(R.string.str_barcode_scan_product_add_list);
                imgLastBarcode.setImageBitmap(barcodeResult.getBitmapWithResultPoints(InputDeviceCompat.SOURCE_ANY));
            } else {
                if (mBaseErp.ptScannedBarcodeIsScannedAgain().booleanValue()) {
                    this.setAlertDialogForScannedBarcodePreviouslyScanned(barcodeResult2, barcodeNew, barcodeResult);
                    return;
                }
                this.showErrorToast(R.string.str_barcode_scan_before);
            }
        } else {
            this.showErrorToast(R.string.str_barcode_scan_product_not_find);
        }
        barcodeScannerView.resume();
    }
    private void setAlertDialogForScannedBarcodePreviouslyScanned(BarcodeResult barcodeResult, BarcodeNew barcodeNew, @Nullable com.journeyapps.barcodescanner.BarcodeResult barcodeResult2) {
        new AlertDialog.Builder(this).setTitle(this.getString(R.string.str_warning)).setMessage(this.getString(R.string.str_barcode_scan_before)).setPositiveButton(R.string.str_accept, new DialogInterface.OnClickListener() {

            public void onClick(final DialogInterface dialogInterface, final int i2) {
                barcodeResult.setItemRef(barcodeNew.getItemRef());
                mBarcodeList.put(barcodeResult, barcodeNew);
                barcodeAdapter.addItems(barcodeNew);
                showSuccessToast(R.string.str_barcode_scan_product_add_list);
                imgLastBarcode.setImageBitmap(barcodeResult2.getBitmapWithResultPoints(InputDeviceCompat.SOURCE_ANY));
            }
        }).setNegativeButton(R.string.cancel, null).create().show();
    }
    public void onError(final String str) {
        if (!TextUtils.isEmpty(str)) {
            this.showErrorToast(str);
        }
        barcodeScannerView.resume();
    }
    public void showErrorToast(@StringRes final int i2) {
        this.showErrorToast(this.getString(i2));
    }

    private void showErrorToast(final String str) {
        new ToastLib.Builder(this, str).duration(ToastEnum.SHORT).backgroundColor(ContextCompat.getColor(this, R.color.red)).textColor(ContextCompat.getColor(this, R.color.white)).textSize(18).isBold(true).icon(this.getString(R.string.fa_error)).iconColor(ContextCompat.getColor(this, R.color.black)).iconSize(24, 2).corner(8).margin(56).padding(36).spacing(16).stroke(2, ContextCompat.getColor(this, R.color.dark_green)).gravity(80).show();
    }
    public void showProgressToast(@StringRes final int i2) {
        new ToastLib.Builder(this, this.getString(i2)).duration(ToastEnum.SHORT).backgroundColor(ContextCompat.getColor(this, R.color.red)).textColor(ContextCompat.getColor(this, R.color.white)).textSize(18).isBold(true).icon(this.getString(R.string.fa_spinner)).iconColor(ContextCompat.getColor(this, R.color.black)).iconSize(24, 2).corner(8).margin(56).padding(36).spacing(16).stroke(2, ContextCompat.getColor(this, R.color.dark_green)).gravity(80).show();
    }
    public void showSuccessToast(@StringRes final int i2) {
        new ToastLib.Builder(this, this.getString(i2)).duration(ToastEnum.SHORT).backgroundColor(ContextCompat.getColor(this, R.color.green)).textColor(ContextCompat.getColor(this, R.color.white)).textSize(18).isBold(true).icon(this.getString(R.string.fa_success)).iconColor(ContextCompat.getColor(this, R.color.black)).iconSize(24, 2).corner(8).margin(56).padding(36).spacing(16).stroke(2, ContextCompat.getColor(this, R.color.dark_green)).gravity(80).show();
    }

    private record BarcodeProductAddListener(
            WeakReference<BarcodeScannerProductViewActivity> mBarcodeScannerProductViewWeakReference,
            com.journeyapps.barcodescanner.BarcodeResult mBarcodeResult,
            BarcodeResult mParameterBarcodeResult) implements ResponseListener<BarcodeNew> {
            private BarcodeProductAddListener(final BarcodeScannerProductViewActivity mBarcodeScannerProductViewWeakReference, final com.journeyapps.barcodescanner.BarcodeResult mBarcodeResult, final BarcodeResult mParameterBarcodeResult) {
                this.mBarcodeScannerProductViewWeakReference = new WeakReference<>(mBarcodeScannerProductViewWeakReference);
                this.mBarcodeResult = mBarcodeResult;
                this.mParameterBarcodeResult = mParameterBarcodeResult;
            }

        public void onResponse(@Nullable final @UnknownNullability PrintSlipModel barcodeNew) {
                if (null == this.mBarcodeScannerProductViewWeakReference.get() || mBarcodeScannerProductViewWeakReference.get().isActivityDestroyed()) {
                    return;
                }
                mBarcodeScannerProductViewWeakReference.get().onItemFoundResult(barcodeNew, mBarcodeResult, mParameterBarcodeResult);
            }

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
            public void onResponse(BarcodeNew obj) {

            }

            @Override
            public void onResponse(ArrayList<BarcodeNew> obj) {

            }

            public void onError(final String str) {
                Log.d(MobileSales.TAG, "onError: " + str);
                if (null == this.mBarcodeScannerProductViewWeakReference.get() || mBarcodeScannerProductViewWeakReference.get().isActivityDestroyed()) {
                    return;
                }
                mBarcodeScannerProductViewWeakReference.get().onError(str);
            }
        }
}
