package com.proje.mobilesales.features.product.view.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.content.ContextCompat;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.TrackType;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.product.model.ItemUnit;
import com.proje.mobilesales.features.product.model.Product;
import com.proje.mobilesales.features.product.repository.ProductListRepository;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
 
public final class ProductView extends RelativeLayout {
    private ImageButton btnProductInfo;
    private EditText edtAmount;
    private ImageView imgProductImage;
    private final ImageView imgProductSelect;
    private final LinearLayout lnActualStock;
    private final LinearLayout lnProductDetail;
    private final LinearLayout lnProductUnit;
    private final LinearLayout lnRealStock;
    private final int mBackgroundColor;
    private final String mEmptyText;
    private final String mExistText;
    private final int mHighlightColor;
    private final String mLoadingText;
    private final String mNotExistText;
    private final int mPromotedColorResId;
    private final int mSecondaryTextColorResId;
    private boolean mShowExists;
    private final int mTertiaryTextColorResId;
    private final ProductListRepository repository;
    private RelativeLayout rltProductHeader;
    private final AppCompatSpinner spnProductUnit;
    private TextWatcher textWatcherAmount;
    private TextView toggle;
    private View toggleButton;
    private final TextView txtProductActualStock;
    private final TextView txtProductCode;
    private final TextView txtProductName;
    private final TextView txtProductName2;
    private final TextView txtProductPrice;
    private final TextView txtProductRealStock;
    private final ProductListViewModel viewModel;
    public ProductView(Context context) {
        this(context, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
    } 
    public ProductView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        ProductListRepository productListRepository = new ProductListRepository();
        this.repository = productListRepository;
        this.viewModel = new ProductListViewModel(productListRepository);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{16843282, 16842808, R.attr.colorCardBackground, R.attr.colorCardHighlight});
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        this.mTertiaryTextColorResId = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(0, 0));
        this.mSecondaryTextColorResId = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(1, 0));
        int color = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(2, 0));
        this.mBackgroundColor = color;
        this.mHighlightColor = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(3, 0));
        this.mPromotedColorResId = ContextCompat.getColor(context, R.color.greenA700);
        View.inflate(context, R.layout.product_view, this);
        setBackgroundColor(color);
        View findViewById = findViewById(R.id.rlt_product_header);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.RelativeLayout");
        this.rltProductHeader = (RelativeLayout) findViewById;
        View findViewById2 = findViewById(R.id.ln_product_real_stock);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.LinearLayout");
        this.lnRealStock = (LinearLayout) findViewById2;
        View findViewById3 = findViewById(R.id.ln_product_actual_stock);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.LinearLayout");
        this.lnActualStock = (LinearLayout) findViewById3;
        View findViewById4 = findViewById(R.id.img_product);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.ImageView");
        this.imgProductImage = (ImageView) findViewById4;
        View findViewById5 = findViewById(R.id.txt_productCode);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
        this.txtProductCode = (TextView) findViewById5;
        View findViewById6 = findViewById(R.id.txt_productName);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.TextView");
        this.txtProductName = (TextView) findViewById6;
        View findViewById7 = findViewById(R.id.txt_productName2);
        Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type android.widget.TextView");
        this.txtProductName2 = (TextView) findViewById7;
        View findViewById8 = findViewById(R.id.txt_product_price);
        Intrinsics.checkNotNull(findViewById8, "null cannot be cast to non-null type android.widget.TextView");
        this.txtProductPrice = (TextView) findViewById8;
        View findViewById9 = findViewById(R.id.txt_product_actual_stock);
        Intrinsics.checkNotNull(findViewById9, "null cannot be cast to non-null type android.widget.TextView");
        this.txtProductActualStock = (TextView) findViewById9;
        View findViewById10 = findViewById(R.id.txt_real_stock);
        Intrinsics.checkNotNull(findViewById10, "null cannot be cast to non-null type android.widget.TextView");
        this.txtProductRealStock = (TextView) findViewById10;
        View findViewById11 = findViewById(R.id.img_product_select);
        Intrinsics.checkNotNull(findViewById11, "null cannot be cast to non-null type android.widget.ImageView");
        this.imgProductSelect = (ImageView) findViewById11;
        View findViewById12 = findViewById(R.id.ln_detailContainer);
        Intrinsics.checkNotNull(findViewById12, "null cannot be cast to non-null type android.widget.LinearLayout");
        this.lnProductDetail = (LinearLayout) findViewById12;
        View findViewById13 = findViewById(R.id.ln_unit);
        Intrinsics.checkNotNull(findViewById13, "null cannot be cast to non-null type android.widget.LinearLayout");
        this.lnProductUnit = (LinearLayout) findViewById13;
        View findViewById14 = findViewById(R.id.edt_amount);
        Intrinsics.checkNotNull(findViewById14, "null cannot be cast to non-null type android.widget.EditText");
        this.edtAmount = (EditText) findViewById14;
        View findViewById15 = findViewById(R.id.spn_productUnitSpinner);
        Intrinsics.checkNotNull(findViewById15, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatSpinner");
        this.spnProductUnit = (AppCompatSpinner) findViewById15;
        View findViewById16 = findViewById(R.id.button_toggle);
        Intrinsics.checkNotNullExpressionValue(findViewById16, "findViewById(...)");
        this.toggleButton = findViewById16;
        View findViewById17 = findViewById(R.id.toggle);
        Intrinsics.checkNotNull(findViewById17, "null cannot be cast to non-null type android.widget.TextView");
        this.toggle = (TextView) findViewById17;
        View findViewById18 = findViewById(R.id.btn_productInfo);
        Intrinsics.checkNotNullExpressionValue(findViewById18, "findViewById(...)");
        this.btnProductInfo = (ImageButton) findViewById18;
        String string = getContext().getString(R.string.loading_text);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this.mLoadingText = string;
        String string2 = getContext().getString(R.string.empty_text);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        this.mEmptyText = string2;
        String string3 = getContext().getString(R.string.str_stock_in);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        this.mExistText = string3;
        String string4 = getContext().getString(R.string.str_stock_out);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        this.mNotExistText = string4;
        obtainStyledAttributes.recycle();
    }

    public RelativeLayout getRltProductHeader() {
        return this.rltProductHeader;
    }

    public void setRltProductHeader(RelativeLayout relativeLayout) {
        Intrinsics.checkNotNullParameter(relativeLayout, "<set-?>");
        this.rltProductHeader = relativeLayout;
    }

    public ImageView getImgProductImage() {
        return this.imgProductImage;
    }

    public void setImgProductImage(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.imgProductImage = imageView;
    }

    public EditText getEdtAmount() {
        return this.edtAmount;
    }

    public void setEdtAmount(EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "<set-?>");
        this.edtAmount = editText;
    }

    public View getToggleButton() {
        return this.toggleButton;
    }

    public void setToggleButton(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.toggleButton = view;
    }

    public TextView getToggle() {
        return this.toggle;
    }

    public void setToggle(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.toggle = textView;
    }

    public ImageButton getBtnProductInfo() {
        return this.btnProductInfo;
    }

    public void setBtnProductInfo(ImageButton imageButton) {
        Intrinsics.checkNotNullParameter(imageButton, "<set-?>");
        this.btnProductInfo = imageButton;
    }
    public ProductView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public ProductView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public void setProduct(Product product, boolean z, SalesType salesType, double d) {
        Intrinsics.checkNotNullParameter(product, "product");
        this.mShowExists = z;
        this.txtProductName.setText(product.getName());
        TextView textView = this.txtProductName2;
        String name2 = product.getName2();
        if (name2 == null) {
            name2 = StringUtils.empty();
        }
        textView.setText(name2);
        TextView textView2 = this.txtProductName2;
        String name22 = product.getName2();
        textView2.setVisibility((name22 == null || name22.length() == 0) ? View.GONE : View.VISIBLE);
        this.txtProductCode.setText(product.getCode());
        this.txtProductPrice.setText(product.getPriceWithDigits());
        setVisibilityStock(!product.getService());
        if (product.getSelect()) {
            if (product.getTrackType() == TrackType.NON_TRACK.getType() || salesType == SalesType.ORDER || salesType == SalesType.DEMAND) {
                if (product.getAmount() == 0.0d) {
                    product.setAmount(d);
                }
                if (product.getDivUnit()) {
                    this.edtAmount.setText(StringUtils.convertDoubleToString(Double.valueOf(product.getAmount())));
                    this.edtAmount.setInputType(8194);
                } else {
                    this.edtAmount.setText(StringUtils.convertIntToString((int) product.getAmount()));
                    this.edtAmount.setInputType(2);
                }
                this.edtAmount.setSelectAllOnFocus(true);
                ProductViewsetProduct1 productViewsetProduct1 = new TextWatcher(product, this) { 
                    final Product product;
                    final ProductView this0;
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        Intrinsics.checkNotNullParameter(charSequence, "charSequence");
                    }
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        Intrinsics.checkNotNullParameter(charSequence, "charSequence");
                    }
                    {
                        this.product = r1;
                        this.this0 = r2;
                    }
                    public void afterTextChanged(Editable editable) {
                        Intrinsics.checkNotNullParameter(editable, "editable");
                        this.product.setAmount(StringUtils.convertStringToDouble(this.this0.getEdtAmount().getText().toString()));
                    }
                };
                this.textWatcherAmount = productViewsetProduct1;
                this.edtAmount.addTextChangedListener(productViewsetProduct1);
            } else {
                this.edtAmount.setVisibility(View.GONE);
            }
            if (product.getMItemUnits() == null) {
                product.initUnitList(this.viewModel.getProductUnitsFromSqlHelper(product.getLogicalRef(), product.getService()));
                Log.d(MobileSales.TAG, "onproductview: " + product.isBarcode());
                if (product.isBarcode()) {
                    product.selectUnitLogicalRef(product.getBarcodeUnitRef());
                } else if (product.getSelectUnitIndex() > -1) {
                    product.selectUnitLastIndex();
                } else if (product.getDefUnitRef() > 0) {
                    product.selectUnitLogicalRef(product.getDefUnitRef());
                } else {
                    product.selectUnitIndexFirst();
                }
            }
            if (product.getMItemUnits() != null) {
                ArrayList<ItemUnit> mItemUnits = product.getMItemUnits();
                Intrinsics.checkNotNull(mItemUnits);
                if (mItemUnits.size() > 0) {
                    this.lnProductUnit.setVisibility(android.view.View.VISIBLE);
                    try {
                        Context context = getContext();
                        ArrayList<String> itemUnitCodeList = product.getItemUnitCodeList();
                        Intrinsics.checkNotNull(itemUnitCodeList, "null cannot be cast to non-null type kotlin.collections.List<kotlin.String>");
                        ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.layout_product_spinner_item, itemUnitCodeList);
                        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        this.spnProductUnit.setAdapter(arrayAdapter);
                        this.spnProductUnit.setSelection(product.getSelectUnitIndex());
                        if (!product.getNotUnitChange()) {
                            this.spnProductUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(product) { 
                                final   Product product; 
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                } 
                                {
                                    this.product = r1;
                                } 
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                                    Intrinsics.checkNotNullParameter(view, "view");
                                    this.product.selectUnitIndex(i);
                                }
                            });
                        } else {
                            this.spnProductUnit.setEnabled(false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            this.lnProductUnit.setVisibility(View.GONE);
        }
        if (!this.mShowExists) {
            this.txtProductActualStock.setText(StringUtils.formatStock(product.getActualStock()));
            this.txtProductRealStock.setText(StringUtils.formatStock(product.getRealStock()));
        } else {
            if (product.getActualStock() > 0.0d) {
                this.txtProductActualStock.setText(this.mExistText);
            } else {
                this.txtProductActualStock.setText(this.mNotExistText);
            }
            if (product.getRealStock() > 0.0d) {
                this.txtProductRealStock.setText(this.mExistText);
            } else {
                this.txtProductRealStock.setText(this.mNotExistText);
            }
        }
        selectProduct(product.getSelect());
        if (product.getImage() != null && product.isImageActive()) {
            this.imgProductImage.setImageBitmap(product.getImage());
        }
    }

    private void setVisibilityStock(boolean z) {
        boolean isHideActualStockAmount = this.viewModel.isHideActualStockAmount();
        boolean isHideRealStockAmount = this.viewModel.isHideRealStockAmount();
        int i = 8;
        this.lnActualStock.setVisibility((!z || isHideActualStockAmount) ? View.GONE : View.VISIBLE);
        LinearLayout linearLayout = this.lnRealStock;
        if (z && !isHideRealStockAmount) {
            i = 0;
        }
        linearLayout.setVisibility(View.VISIBLE);
    }

    private void selectProduct(boolean z) {
        if (z) {
            this.imgProductSelect.setVisibility(View.VISIBLE);
        } else {
            this.imgProductSelect.setVisibility(View.GONE);
        }
    }

    @SuppressLint({"SetTextI18n"})
    public void reset() {
        this.mShowExists = false;
        this.txtProductName.setText(this.mLoadingText);
        this.txtProductName2.setText(this.mLoadingText);
        this.txtProductCode.setText(this.mLoadingText);
        this.txtProductPrice.setText(this.mLoadingText);
        this.txtProductActualStock.setText(this.mLoadingText);
        this.txtProductRealStock.setText(this.mLoadingText);
        this.edtAmount.removeTextChangedListener(this.textWatcherAmount);
        this.edtAmount.setText("");
        this.imgProductImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_barcode_scan_grey600_48dp));
        this.imgProductSelect.setVisibility(View.GONE);
    }

    public void toggleExpand(boolean z) {
        toggleView(this.lnProductDetail, z);
    }

    public void toggleToggleExpand(boolean z) {
        toggleView(this.toggleButton, z);
    }

    private void toggleView(View view, boolean z) {
        if (z) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    public void focusAndShowKeyBoard() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {  
            public void run() {
                ProductView.focusAndShowKeyBoardlambda0(ProductView.this);
            }
        }, 100);
    } 
    public static void focusAndShowKeyBoardlambda0(ProductView productView) {
        Intrinsics.checkNotNullParameter(productView, "this0");
        productView.edtAmount.requestFocus();
        productView.edtAmount.setSelectAllOnFocus(true);
        Object systemService = productView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService).showSoftInput(productView.edtAmount, InputMethodManager.SHOW_IMPLICIT);
    }

    public void hideKeyboard() {
        Object systemService = getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService).hideSoftInputFromWindow(this.edtAmount.getWindowToken(), 0);
    }
}
