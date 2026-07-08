package com.proje.mobilesales.features.cabinoperation.view.activity;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.*;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.BarcodeReaderType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.core.view.SnappyLinearLayoutManager;
import com.proje.mobilesales.core.widget.SlideInLeftAnimator;
import com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter;
import com.proje.mobilesales.features.cabinoperation.interfaces.ICabinBarcodeSearchResult;
import com.proje.mobilesales.features.cabinoperation.repository.CabinRepository;
import com.proje.mobilesales.features.cabinoperation.viewmodel.CabinViewModel;
import com.proje.mobilesales.features.collections.casefiche.view.activity.CaseFicheActivity;
import com.proje.mobilesales.features.model.BarcodeCustomer;
import com.proje.mobilesales.features.model.BarcodeSettings;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class BarcodeScannerCabinView extends BaseInjectableActivity implements DecoratedBarcodeView.TorchListener, ICabinBarcodeSearchResult {
    public static final CaseFicheActivity.Companion Companion = new CaseFicheActivity.Companion(null);
    public static final int TRY_BARCODE_WITH_CUSTOMER = 1004;
    private BarcodeScannerViewRecyclerViewAdapter adapter;
    private DecoratedBarcodeView barcodeScannerView;
    private BarcodeUtil barcodeUtil;
    private EditText edtLaserBarcode;
    private AppCompatImageView imgFlash;
    private boolean isCustomerCabinSearch;
    private boolean isLaser;
    private LinearLayout lnBarcode;
    private LinearLayout lnLaser;
    private LinearLayout lnProductList;
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    private BarcodeRegex mBarcodeRegex;
    private BarcodeSettings mBarcodeSettings;
    private Drawable mCloseFlashDrawable;
    private boolean mFlashOpen;
    private boolean mForSearch;
    private Drawable mOpenFlashDrawable;
    private boolean mSingleSelect;
    private RecyclerView recyclerView;
    private RecyclerView rwProductList;
    private TextView txtEmptyList;
    private final CabinRepository repository = new CabinRepository();
    private final CabinViewModel viewModel = new CabinViewModel(repository);
    private final BarcodeCallback singleCallBack = new BarcodeCallback() {
        public void possibleResultPoints(final List<ResultPoint> resultPoints) {
            Intrinsics.checkNotNullParameter(resultPoints, "resultPoints");
        }

        void BarcodeScannerCabinViewsingleCallBack1() {
        }

        @Override // com.journeyapps.barcodescanner.BarcodeCallback
        public void barcodeResult(final BarcodeResult result) {
            final DecoratedBarcodeView decoratedBarcodeView;
            com.proje.mobilesales.features.model.BarcodeResult barcodeRegex;
            final boolean z;
            final BarcodeUtil barcodeUtil;
            final BarcodeUtil barcodeUtil2;
            Intrinsics.checkNotNullParameter(result, "result");
            decoratedBarcodeView = barcodeScannerView;
            Intrinsics.checkNotNull(decoratedBarcodeView);
            decoratedBarcodeView.pause();
            final BarcodeScannerCabinView barcodeScannerCabinView = BarcodeScannerCabinView.this;
            final String text = result.getText();
            Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
            barcodeRegex = barcodeScannerCabinView.getBarcodeRegex(text);
            final com.proje.mobilesales.features.model.BarcodeResult barcodeResult = new com.proje.mobilesales.features.model.BarcodeResult("");
            if (null == barcodeRegex) {
                barcodeResult.setBarcode(result.getText());
            }
            z = isCustomerCabinSearch;
            if (z) {
                barcodeUtil2 = BarcodeScannerCabinView.this.barcodeUtil;
                Intrinsics.checkNotNull(barcodeUtil2);
                if (null == barcodeRegex) {
                    barcodeRegex = barcodeResult;
                }
                barcodeUtil2.getCustomerFromCabinBarcode(barcodeRegex);
                return;
            }
            barcodeUtil = BarcodeScannerCabinView.this.barcodeUtil;
            Intrinsics.checkNotNull(barcodeUtil);
            if (null == barcodeRegex) {
                barcodeRegex = barcodeResult;
            }
            barcodeUtil.getAvailableCabinFromCabinBarcode(barcodeRegex);
        }
    };

    public void onConfigurationChanged(final Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
    }

    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        final Context context = ContextUtils.getmContext();
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mAlertDialogBuilder = new AlertDialogBuilder.Impl(context, (BaseInjectableActivity) activity);
        this.setContentView(R.layout.new_barcode);
        this.getIntentExtras();
        this.init();
        if (isLaser) {
            return;
        }
        final DecoratedBarcodeView decoratedBarcodeView = barcodeScannerView;
        Intrinsics.checkNotNull(decoratedBarcodeView);
        decoratedBarcodeView.resume();
    }

    private Unit getIntentExtras() {
        if (null != getIntent() && null != getIntent().getExtras()) {
            mSingleSelect = this.getIntent().getBooleanExtra(IntentExtraName.EXTRA_SELECT_TYPE, true);
            mForSearch = this.getIntent().getBooleanExtra(IntentExtraName.BARCODE_FOR_SEARCH, false);
            isCustomerCabinSearch = this.getIntent().getBooleanExtra(IntentExtraName.EXTRA_CUSTOMER_BARCODE_SEARCH, false);
        }
        return Unit.INSTANCE;
    }

    public void init() {
        final View findViewById = this.findViewById(R.id.ln_barcode);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.LinearLayout");
        lnBarcode = (LinearLayout) findViewById;
        final View findViewById2 = this.findViewById(R.id.ln_laser);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.LinearLayout");
        lnLaser = (LinearLayout) findViewById2;
        final View findViewById3 = this.findViewById(R.id.ln_product_list);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.LinearLayout");
        lnProductList = (LinearLayout) findViewById3;
        final View findViewById4 = this.findViewById(R.id.zxing_barcode_scanner);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type com.journeyapps.barcodescanner.DecoratedBarcodeView");
        barcodeScannerView = (DecoratedBarcodeView) findViewById4;
        final View findViewById5 = this.findViewById(R.id.img_switch_flashlight);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatImageView");
        imgFlash = (AppCompatImageView) findViewById5;
        mOpenFlashDrawable = ContextCompat.getDrawable(this, R.drawable.ic_flashlight_white_48dp);
        mCloseFlashDrawable = ContextCompat.getDrawable(this, R.drawable.ic_flashlight_off_white_48dp);
        final View findViewById6 = this.findViewById(R.id.edtLaserBarcode);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.EditText");
        final EditText editText = (EditText) findViewById6;
        edtLaserBarcode = editText;
        Intrinsics.checkNotNull(editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public void BarcodeScannerCabinViewExternalSyntheticLambda5() {
            }

            public boolean onEditorAction(final TextView textView, final int i2, final KeyEvent keyEvent) {
                final boolean initlambda0;
                initlambda0 = initlambda0(BarcodeScannerCabinView.this, textView, i2, keyEvent);
                return initlambda0;
            }
        });
        final View findViewById7 = this.findViewById(R.id.txtEmptyList);
        Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type android.widget.TextView");
        txtEmptyList = (TextView) findViewById7;
        final View findViewById8 = this.findViewById(R.id.rwProductList);
        Intrinsics.checkNotNull(findViewById8, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        rwProductList = (RecyclerView) findViewById8;
        final BarcodeSettings barcodeSettings = viewModel.getBaseErp().getBarcodeSettings();
        mBarcodeSettings = barcodeSettings;
        final Integer valueOf = null != barcodeSettings ? Integer.valueOf(barcodeSettings.getBarcodeTotalCharacter()) : null;
        Intrinsics.checkNotNull(valueOf);
        valueOf.intValue();
        mBarcodeRegex = new BarcodeRegex(this.getString(R.string.barcode_parse_empty_regex));
        final DecoratedBarcodeView decoratedBarcodeView = barcodeScannerView;
        Intrinsics.checkNotNull(decoratedBarcodeView);
        decoratedBarcodeView.setTorchListener(this);
        final DecoratedBarcodeView decoratedBarcodeView2 = barcodeScannerView;
        Intrinsics.checkNotNull(decoratedBarcodeView2);
        decoratedBarcodeView2.decodeContinuous(singleCallBack);
        this.initFlash();
        final RecyclerView recyclerView = this.findViewById(R.id.rwProductList);
        this.recyclerView = recyclerView;
        if (null != recyclerView) {
            recyclerView.setLayoutManager(new SnappyLinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new SlideInLeftAnimator(new OvershootInterpolator(2.0f)));
            final BarcodeScannerViewRecyclerViewAdapter barcodeScannerViewRecyclerViewAdapter = new BarcodeScannerViewRecyclerViewAdapter();
            adapter = barcodeScannerViewRecyclerViewAdapter;
            Intrinsics.checkNotNull(barcodeScannerViewRecyclerViewAdapter);
            barcodeScannerViewRecyclerViewAdapter.initDisplayOptions(this);
            recyclerView.setAdapter(adapter);
        }
        this.setToolbar();
        this.setTitle(R.string.str_barcode_scanning);
        barcodeUtil = new BarcodeUtil(this, viewModel.getSqlManager(), viewModel.getBaseErp());
        final BarcodeReaderType defaultBarcodeReaderType = viewModel.getBaseErp().getDefaultBarcodeReaderType();
        Intrinsics.checkNotNullExpressionValue(defaultBarcodeReaderType, "getDefaultBarcodeReaderType(...)");
        this.setLaser(defaultBarcodeReaderType);
        if (isLaser) {
            final LinearLayout linearLayout = lnLaser;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(View.VISIBLE);
            final LinearLayout linearLayout2 = lnBarcode;
            Intrinsics.checkNotNull(linearLayout2);
            linearLayout2.setVisibility(View.GONE);
            final LinearLayout linearLayout3 = lnProductList;
            Intrinsics.checkNotNull(linearLayout3);
            linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 90.0f));
            final DecoratedBarcodeView decoratedBarcodeView3 = barcodeScannerView;
            Intrinsics.checkNotNull(decoratedBarcodeView3);
            decoratedBarcodeView3.pauseAndWait();
            final EditText editText2 = edtLaserBarcode;
            Intrinsics.checkNotNull(editText2);
            editText2.requestFocus();
            return;
        }
        final LinearLayout linearLayout4 = lnLaser;
        Intrinsics.checkNotNull(linearLayout4);
        linearLayout4.setVisibility(View.GONE);
        final LinearLayout linearLayout5 = lnBarcode;
        Intrinsics.checkNotNull(linearLayout5);
        linearLayout5.setVisibility(View.VISIBLE);
        final LinearLayout linearLayout6 = lnProductList;
        Intrinsics.checkNotNull(linearLayout6);
        linearLayout6.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 60.0f));
        final DecoratedBarcodeView decoratedBarcodeView4 = barcodeScannerView;
        Intrinsics.checkNotNull(decoratedBarcodeView4);
        decoratedBarcodeView4.resume();
    }

    public static boolean initlambda0(final BarcodeScannerCabinView this0, final TextView textView, final int i2, final KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (3 != i2 && 6 != i2 && (0 != keyEvent.getAction() || 66 != keyEvent.getKeyCode())) {
            return false;
        }
        com.proje.mobilesales.features.model.BarcodeResult barcodeRegex = this0.getBarcodeRegex(textView.getText().toString());
        final com.proje.mobilesales.features.model.BarcodeResult barcodeResult = new com.proje.mobilesales.features.model.BarcodeResult("");
        if (null == barcodeRegex) {
            barcodeResult.setBarcode(textView.getText().toString());
        }
        if (this0.isCustomerCabinSearch) {
            final BarcodeUtil barcodeUtil = this0.barcodeUtil;
            Intrinsics.checkNotNull(barcodeUtil);
            if (null == barcodeRegex) {
                barcodeRegex = barcodeResult;
            }
            barcodeUtil.getCustomerFromCabinBarcode(barcodeRegex);
        } else {
            final BarcodeUtil barcodeUtil2 = this0.barcodeUtil;
            Intrinsics.checkNotNull(barcodeUtil2);
            if (null == barcodeRegex) {
                barcodeRegex = barcodeResult;
            }
            barcodeUtil2.getAvailableCabinFromCabinBarcode(barcodeRegex);
        }
        return true;
    }

    @Override
    // com.proje.mobilesales.core.base.BaseInjectableActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        final AlertDialogBuilder<?> alertDialogBuilder = mAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setTitle(R.string.str_cancel_barcode_question).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.cabinoperation.view.activity.BarcodeScannerCabinViewExternalSyntheticLambda3
            public void BarcodeScannerCabinViewExternalSyntheticLambda3() {
            }

            public void onClick(final DialogInterface dialogInterface, final int i2) {
                onBackPressedlambda2(BarcodeScannerCabinView.this, dialogInterface, i2);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                onBackPressedlambda3(dialogInterface, i2);
            }
        }).create().show();
    }

    public static void onBackPressedlambda2(final BarcodeScannerCabinView this0, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");

        dialog.dismiss();
    }

    public static void onBackPressedlambda3(final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    public void onResume() {
        super.onResume();
        if (isLaser) {
            return;
        }
        final DecoratedBarcodeView decoratedBarcodeView = barcodeScannerView;
        Intrinsics.checkNotNull(decoratedBarcodeView);
        decoratedBarcodeView.resume();
    }

    protected void onPause() {
        super.onPause();
        if (isLaser) {
            return;
        }
        final DecoratedBarcodeView decoratedBarcodeView = barcodeScannerView;
        Intrinsics.checkNotNull(decoratedBarcodeView);
        decoratedBarcodeView.pause();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public boolean onKeyDown(final int i2, final KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        final DecoratedBarcodeView decoratedBarcodeView = barcodeScannerView;
        Intrinsics.checkNotNull(decoratedBarcodeView);
        return decoratedBarcodeView.onKeyDown(i2, event) || super.onKeyDown(i2, event);
    }

    private boolean hasFlash() {
        return this.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }

    private void switchFlashlight() {
        final boolean z;
        if (mFlashOpen) {
            final DecoratedBarcodeView decoratedBarcodeView = barcodeScannerView;
            Intrinsics.checkNotNull(decoratedBarcodeView);
            decoratedBarcodeView.setTorchOff();
            z = false;
        } else {
            final DecoratedBarcodeView decoratedBarcodeView2 = barcodeScannerView;
            Intrinsics.checkNotNull(decoratedBarcodeView2);
            decoratedBarcodeView2.setTorchOn();
            z = true;
        }
        mFlashOpen = z;
    }

    private void initFlash() {
        if (!this.hasFlash()) {
            final AppCompatImageView appCompatImageView = imgFlash;
            Intrinsics.checkNotNull(appCompatImageView);
            appCompatImageView.setVisibility(View.GONE);
        } else {
            final AppCompatImageView appCompatImageView2 = imgFlash;
            Intrinsics.checkNotNull(appCompatImageView2);
            appCompatImageView2.setOnClickListener(new View.OnClickListener() {
                public void BarcodeScannerCabinViewExternalSyntheticLambda0() {
                }

                public void onClick(final View view) {
                    initFlashlambda4(BarcodeScannerCabinView.this, view);
                }
            });
        }
    }

    public static void initFlashlambda4(final BarcodeScannerCabinView this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.switchFlashlight();
    }

    public void onTorchOn() {
        final AppCompatImageView appCompatImageView = imgFlash;
        Intrinsics.checkNotNull(appCompatImageView);
        appCompatImageView.setImageDrawable(mCloseFlashDrawable);
    }

    public void onTorchOff() {
        final AppCompatImageView appCompatImageView = imgFlash;
        Intrinsics.checkNotNull(appCompatImageView);
        appCompatImageView.setImageDrawable(mOpenFlashDrawable);
    }

    public com.proje.mobilesales.features.model.BarcodeResult getBarcodeRegex(final String str) {
        final double d2;
        final BarcodeRegex barcodeRegex = mBarcodeRegex;
        Intrinsics.checkNotNull(barcodeRegex);
        final ArrayList<String> findBarcodeRegex = barcodeRegex.findBarcodeRegex(str);
        Intrinsics.checkNotNull(findBarcodeRegex);
        if (findBarcodeRegex.isEmpty()) {
            return null;
        }
        final String str2 = findBarcodeRegex.get(0);
        if (3 == findBarcodeRegex.size()) {
            final String format = String.format("%s.%s", Arrays.copyOf(new Object[]{findBarcodeRegex.get(1), findBarcodeRegex.get(2)}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            d2 = StringUtils.convertStringToDouble(format);
        } else {
            d2 = 0.0d;
        }
        return new com.proje.mobilesales.features.model.BarcodeResult(str2, d2);
    }

    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        final MenuInflater menuInflater = this.getMenuInflater();
        Intrinsics.checkNotNullExpressionValue(menuInflater, "getMenuInflater(...)");
        menuInflater.inflate(R.menu.menu_barcode, menu);
        menu.findItem(R.id.menu_ok).setVisible(false);
        final MenuItem findItem = menu.findItem(R.id.menu_fast_read);
        if (null != findItem) {
            if (isLaser) {
                findItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_barcode_scan_white_24dp));
            } else {
                findItem.setIcon(ContextCompat.getDrawable(this, R.drawable.laser_barcode_white));
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemId = item.getItemId();
        if (16908332 == itemId) {
            final AlertDialogBuilder<?> alertDialogBuilder = mAlertDialogBuilder;
            Intrinsics.checkNotNull(alertDialogBuilder);
            alertDialogBuilder.setTitle(R.string.str_cancel_barcode_question).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.cabinoperation.view.activity.BarcodeScannerCabinViewExternalSyntheticLambda1
                public void BarcodeScannerCabinViewExternalSyntheticLambda1() {
                }

                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    onOptionsItemSelectedlambda5(BarcodeScannerCabinView.this, dialogInterface, i2);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    onOptionsItemSelectedlambda6(dialogInterface, i2);
                }
            }).create().show();
            return true;
        }
        if (R.id.menu_fast_read != itemId) {
            if (R.id.menu_ok != itemId) {
                return super.onOptionsItemSelected(item);
            }
            return true;
        }
        final boolean z = isLaser;
        isLaser = !z;
        if (!z) {
            final LinearLayout linearLayout = lnLaser;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(View.VISIBLE);
            final LinearLayout linearLayout2 = lnBarcode;
            Intrinsics.checkNotNull(linearLayout2);
            linearLayout2.setVisibility(View.GONE);
            item.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_barcode_scan_white_24dp));
            final LinearLayout linearLayout3 = lnProductList;
            Intrinsics.checkNotNull(linearLayout3);
            linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 90.0f));
            final DecoratedBarcodeView decoratedBarcodeView = barcodeScannerView;
            Intrinsics.checkNotNull(decoratedBarcodeView);
            decoratedBarcodeView.pauseAndWait();
            final EditText editText = edtLaserBarcode;
            Intrinsics.checkNotNull(editText);
            editText.requestFocus();
        } else {
            final LinearLayout linearLayout4 = lnLaser;
            Intrinsics.checkNotNull(linearLayout4);
            linearLayout4.setVisibility(View.GONE);
            final LinearLayout linearLayout5 = lnBarcode;
            Intrinsics.checkNotNull(linearLayout5);
            linearLayout5.setVisibility(View.VISIBLE);
            item.setIcon(ContextCompat.getDrawable(this, R.drawable.laser_barcode_white));
            final LinearLayout linearLayout6 = lnProductList;
            Intrinsics.checkNotNull(linearLayout6);
            linearLayout6.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 60.0f));
            final DecoratedBarcodeView decoratedBarcodeView2 = barcodeScannerView;
            Intrinsics.checkNotNull(decoratedBarcodeView2);
            decoratedBarcodeView2.resume();
        }
        return true;
    }

    public static void onOptionsItemSelectedlambda5(final BarcodeScannerCabinView this0, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    public static void onOptionsItemSelectedlambda6(final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    private void setLaser(final BarcodeReaderType barcodeReaderType) {
        isLaser = barcodeReaderType == BarcodeReaderType.Laser;
    }

    public void getCabinSearchResult(final BarcodeCustomer barcodeCustomer) {
        final Intent intent = new Intent();
        Intrinsics.checkNotNull(barcodeCustomer);
        if (0 >= barcodeCustomer.getCabinRef()) {
            intent.putExtra("SCAN_RESULT", barcodeCustomer);
            this.setResult(1004, intent);
        } else {
            intent.putExtra("SCAN_RESULT", barcodeCustomer);
            this.setResult(-1, intent);
        }
        this.finish();

        final class Companion {
            public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }
}
