package com.proje.mobilesales.features.product.view.list;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.TrackType;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.utils.CalculateUtils;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.fragment.VariantListDialogFragment;
import com.proje.mobilesales.features.model.VariantSelectionParams;
import com.proje.mobilesales.features.product.model.Product;
import com.proje.mobilesales.features.product.model.ProductOperationDiscount;
import com.proje.mobilesales.features.product.model.database.ItemPrice;
import com.proje.mobilesales.features.product.repository.ProductListRepository;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
 
public final class ProductOperationView extends RelativeLayout {
    private CheckBox chkSalesDetailPromotion; 
    private int coinGrey600;
    private View[] discount;
    private EditText edtPrice;
    private EditText edtProductAmount;
    private EditText[] edtProductDiscount;
    private EditText edtSurplusAmount;
    private ImageButton imgBtnProductAmountMinus;
    private ImageButton imgBtnProductAmountPlus;
    private ImageButton[] imgBtnProductDiscountChange;
    private boolean isPromotionItemPriceEnabled;
    private View lnAmountDialogOpener;
    private LinearLayout lnProductDiscountContainer;
    private LinearLayout lnSalesDetailPromotion;
    private View lnSurplusAmount;
    private final AmountTextWatcher mAmountTextWatcher;
    private final int mBackgroundColor;
    private final ArrayAdapter<String> mDataAdapter;
    private DiscountTextWatcher[] mDiscountTextWatchers;
    private EditText mExplanationEditText;
    private final ExplanationTextWatcher mExplanationTextWatcher;
    private final String mLoadingText;
    private final ProductAmountClickListener mProductAmountMinusClickListener;
    private final ProductAmountClickListener mProductAmountPlusClickListener;
    private ProductDiscountClickListener[] mProductDiscountClickListeners;
    private final SpinnerClickListener mSpinnerClickListener;
    private final SurplusAmountTextWatcher mSurplusAmountTextWatcher; 
    private int percentGrey600;
    private View priceContainer;
    private View priceInfoContainer;
    private View priceLayout;
    private final PriceTextListener priceTextListener;
    private RelativeLayout rltProductContainer;
    private RelativeLayout rltProductOperationAmount;
    private RelativeLayout rltProductUnitSelect;
    private Spinner spnLineType;
    private AppCompatSpinner spnProductUnits;
    private TextView toggle;
    private View toggleButton;
    private TextView txtDialogSelectedAmount;
    private TextView txtPriceInfo;
    private TextView txtProductAmountTitle;
    private TextView txtProductName;
    private VariantListDialogFragment.IVariantSelectionListener variantSelectionListener;
    private VariantSelectionParams variantSelectionParams; 
    public ProductOperationView(final Context context) {
        this(context, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }
    public ProductOperationView(final Context context, final AttributeSet attributeSet, final int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        priceTextListener = new PriceTextListener();
        final TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{16843282, 16842808, R.attr.colorCardBackground, R.attr.colorCardHighlight});
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        final int color = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(2, 0));
        mBackgroundColor = color;
        inflate(context, R.layout.product_operation_view, this);
        this.setBackgroundColor(color);
        discount = new View[3];
        edtProductDiscount = new EditText[3];
        imgBtnProductDiscountChange = new ImageButton[3];
        final View findViewById = this.findViewById(R.id.rlt_product_container);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.RelativeLayout");
        rltProductContainer = (RelativeLayout) findViewById;
        final View findViewById2 = this.findViewById(R.id.rlt_productOperationAmount);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.RelativeLayout");
        rltProductOperationAmount = (RelativeLayout) findViewById2;
        final View findViewById3 = this.findViewById(R.id.ln_discountContainer);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.LinearLayout");
        lnProductDiscountContainer = (LinearLayout) findViewById3;
        final View findViewById4 = this.findViewById(R.id.rlt_productUnitSelect);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        rltProductUnitSelect = (RelativeLayout) findViewById4;
        final View findViewById5 = this.findViewById(R.id.txt_productName);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
        txtProductName = (TextView) findViewById5;
        final View findViewById6 = this.findViewById(R.id.txt_product_amount_title);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.TextView");
        txtProductAmountTitle = (TextView) findViewById6;
        final View findViewById7 = this.findViewById(R.id.imgBtn_product_minus);
        Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type android.widget.ImageButton");
        imgBtnProductAmountMinus = (ImageButton) findViewById7;
        final View findViewById8 = this.findViewById(R.id.edt_product_amount);
        Intrinsics.checkNotNull(findViewById8, "null cannot be cast to non-null type android.widget.EditText");
        edtProductAmount = (EditText) findViewById8;
        final View findViewById9 = this.findViewById(R.id.imgBtn_product_plus);
        Intrinsics.checkNotNull(findViewById9, "null cannot be cast to non-null type android.widget.ImageButton");
        imgBtnProductAmountPlus = (ImageButton) findViewById9;
        discount[0] = this.findViewById(R.id.inc_discount1);
        final EditText[] editTextArr = edtProductDiscount;
        final View view = discount[0];
        View view2 = null;
        final View findViewById10 = null != view ? view.findViewById(R.id.edt_product_discount) : null;
        Intrinsics.checkNotNull(findViewById10, "null cannot be cast to non-null type android.widget.EditText");
        editTextArr[0] = (EditText) findViewById10;
        final ImageButton[] imageButtonArr = imgBtnProductDiscountChange;
        final View view3 = discount[0];
        final View findViewById11 = null != view3 ? view3.findViewById(R.id.imgBtn_product_discount_type_change) : null;
        Intrinsics.checkNotNull(findViewById11, "null cannot be cast to non-null type android.widget.ImageButton");
        imageButtonArr[0] = (ImageButton) findViewById11;
        discount[1] = this.findViewById(R.id.inc_discount2);
        final EditText[] editTextArr2 = edtProductDiscount;
        final View view4 = discount[1];
        final View findViewById12 = null != view4 ? view4.findViewById(R.id.edt_product_discount) : null;
        Intrinsics.checkNotNull(findViewById12, "null cannot be cast to non-null type android.widget.EditText");
        editTextArr2[1] = (EditText) findViewById12;
        final ImageButton[] imageButtonArr2 = imgBtnProductDiscountChange;
        final View view5 = discount[1];
        final View findViewById13 = null != view5 ? view5.findViewById(R.id.imgBtn_product_discount_type_change) : null;
        Intrinsics.checkNotNull(findViewById13, "null cannot be cast to non-null type android.widget.ImageButton");
        imageButtonArr2[1] = (ImageButton) findViewById13;
        discount[2] = this.findViewById(R.id.inc_discount3);
        final EditText[] editTextArr3 = edtProductDiscount;
        final View view6 = discount[2];
        final View findViewById14 = null != view6 ? view6.findViewById(R.id.edt_product_discount) : null;
        Intrinsics.checkNotNull(findViewById14, "null cannot be cast to non-null type android.widget.EditText");
        editTextArr3[2] = (EditText) findViewById14;
        final ImageButton[] imageButtonArr3 = imgBtnProductDiscountChange;
        final View view7 = discount[2];
        view2 = null != view7 ? view7.findViewById(R.id.imgBtn_product_discount_type_change) : view2;
        Intrinsics.checkNotNull(view2, "null cannot be cast to non-null type android.widget.ImageButton");
        imageButtonArr3[2] = (ImageButton) view2;
        final View findViewById15 = this.findViewById(R.id.txt_productExplanation);
        Intrinsics.checkNotNull(findViewById15, "null cannot be cast to non-null type android.widget.EditText");
        mExplanationEditText = (EditText) findViewById15;
        final View findViewById16 = this.findViewById(R.id.button_toggle);
        Intrinsics.checkNotNullExpressionValue(findViewById16, "findViewById(...)");
        toggleButton = findViewById16;
        final View findViewById17 = this.findViewById(R.id.toggle);
        Intrinsics.checkNotNull(findViewById17, "null cannot be cast to non-null type android.widget.TextView");
        toggle = (TextView) findViewById17;
        final View findViewById18 = this.findViewById(R.id.spn_productUnitSpinner);
        Intrinsics.checkNotNull(findViewById18, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatSpinner");
        spnProductUnits = (AppCompatSpinner) findViewById18;
        final String string = this.getContext().getString(R.string.loading_text);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        mLoadingText = string;
        percentGrey600 = R.drawable.ic_sale_grey600_24dp;
        coinGrey600 = R.drawable.ic_do_not_disturb_grey600_24dp;
        final AmountTextWatcher amountTextWatcher = new AmountTextWatcher();
        mAmountTextWatcher = amountTextWatcher;
        edtProductAmount.addTextChangedListener(amountTextWatcher);
        final ExplanationTextWatcher explanationTextWatcher = new ExplanationTextWatcher();
        mExplanationTextWatcher = explanationTextWatcher;
        mExplanationEditText.addTextChangedListener(explanationTextWatcher);
        this.initDiscountWatcher();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this.getContext(), R.layout.layout_product_spinner_item);
        mDataAdapter = arrayAdapter;
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        final SpinnerClickListener spinnerClickListener = new SpinnerClickListener();
        mSpinnerClickListener = spinnerClickListener;
        spnProductUnits.setOnItemSelectedListener(spinnerClickListener);
        spnProductUnits.setAdapter(arrayAdapter);
        final ProductAmountClickListener productAmountClickListener = new ProductAmountClickListener(true);
        mProductAmountPlusClickListener = productAmountClickListener;
        imgBtnProductAmountPlus.setOnClickListener(productAmountClickListener);
        final ProductAmountClickListener productAmountClickListener2 = new ProductAmountClickListener(false);
        mProductAmountMinusClickListener = productAmountClickListener2;
        imgBtnProductAmountMinus.setOnClickListener(productAmountClickListener2);
        final View findViewById19 = this.findViewById(R.id.ln_salesDetailPromotion);
        Intrinsics.checkNotNullExpressionValue(findViewById19, "findViewById(...)");
        lnSalesDetailPromotion = (LinearLayout) findViewById19;
        final View findViewById20 = this.findViewById(R.id.chk_salesDetailPromotion);
        Intrinsics.checkNotNullExpressionValue(findViewById20, "findViewById(...)");
        chkSalesDetailPromotion = (CheckBox) findViewById20;
        final View findViewById21 = this.findViewById(R.id.spn_lineType);
        Intrinsics.checkNotNullExpressionValue(findViewById21, "findViewById(...)");
        spnLineType = (Spinner) findViewById21;
        final View findViewById22 = this.findViewById(R.id.priceContainer);
        Intrinsics.checkNotNullExpressionValue(findViewById22, "findViewById(...)");
        priceLayout = findViewById22;
        final View findViewById23 = this.findViewById(R.id.ln_price);
        Intrinsics.checkNotNullExpressionValue(findViewById23, "findViewById(...)");
        priceContainer = findViewById23;
        final View findViewById24 = this.findViewById(R.id.edtPrice);
        Intrinsics.checkNotNullExpressionValue(findViewById24, "findViewById(...)");
        final EditText editText = (EditText) findViewById24;
        edtPrice = editText;
        editText.addTextChangedListener(priceTextListener);
        final View findViewById25 = this.findViewById(R.id.lnAmountDialogOpener);
        Intrinsics.checkNotNullExpressionValue(findViewById25, "findViewById(...)");
        lnAmountDialogOpener = findViewById25;
        final View findViewById26 = this.findViewById(R.id.txtDialogSelectedAmount);
        Intrinsics.checkNotNullExpressionValue(findViewById26, "findViewById(...)");
        txtDialogSelectedAmount = (TextView) findViewById26;
        final View findViewById27 = this.findViewById(R.id.txtPriceInfo);
        Intrinsics.checkNotNullExpressionValue(findViewById27, "findViewById(...)");
        txtPriceInfo = (TextView) findViewById27;
        final View findViewById28 = this.findViewById(R.id.priceInfoContainer);
        Intrinsics.checkNotNullExpressionValue(findViewById28, "findViewById(...)");
        priceInfoContainer = findViewById28;
        final View findViewById29 = this.findViewById(R.id.ln_surplusAmount);
        Intrinsics.checkNotNullExpressionValue(findViewById29, "findViewById(...)");
        lnSurplusAmount = findViewById29;
        final View findViewById30 = this.findViewById(R.id.edt_surplusAmount);
        Intrinsics.checkNotNullExpressionValue(findViewById30, "findViewById(...)");
        edtSurplusAmount = (EditText) findViewById30;
        final SurplusAmountTextWatcher surplusAmountTextWatcher = new SurplusAmountTextWatcher();
        mSurplusAmountTextWatcher = surplusAmountTextWatcher;
        edtSurplusAmount.addTextChangedListener(surplusAmountTextWatcher);
        this.initDiscountClick();
        obtainStyledAttributes.recycle();
    }
    public RelativeLayout getRltProductContainer() {
        return rltProductContainer;
    }
    public void setRltProductContainer(final RelativeLayout relativeLayout) {
        Intrinsics.checkNotNullParameter(relativeLayout, "<set-?>");
        rltProductContainer = relativeLayout;
    }
    public RelativeLayout getRltProductOperationAmount() {
        return rltProductOperationAmount;
    }
    public void setRltProductOperationAmount(final RelativeLayout relativeLayout) {
        Intrinsics.checkNotNullParameter(relativeLayout, "<set-?>");
        rltProductOperationAmount = relativeLayout;
    }
    public RelativeLayout getRltProductUnitSelect() {
        return rltProductUnitSelect;
    }
    public void setRltProductUnitSelect(final RelativeLayout relativeLayout) {
        Intrinsics.checkNotNullParameter(relativeLayout, "<set-?>");
        rltProductUnitSelect = relativeLayout;
    }
    public LinearLayout getLnProductDiscountContainer() {
        return lnProductDiscountContainer;
    }
    public void setLnProductDiscountContainer(final LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        lnProductDiscountContainer = linearLayout;
    }
    public LinearLayout getLnSalesDetailPromotion() {
        return lnSalesDetailPromotion;
    }
    public void setLnSalesDetailPromotion(final LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        lnSalesDetailPromotion = linearLayout;
    }
    public ImageButton getImgBtnProductAmountPlus() {
        return imgBtnProductAmountPlus;
    }
    public void setImgBtnProductAmountPlus(final ImageButton imageButton) {
        Intrinsics.checkNotNullParameter(imageButton, "<set-?>");
        imgBtnProductAmountPlus = imageButton;
    }
    public ImageButton getImgBtnProductAmountMinus() {
        return imgBtnProductAmountMinus;
    }
    public void setImgBtnProductAmountMinus(final ImageButton imageButton) {
        Intrinsics.checkNotNullParameter(imageButton, "<set-?>");
        imgBtnProductAmountMinus = imageButton;
    }
    public AppCompatSpinner getSpnProductUnits() {
        return spnProductUnits;
    }
    public void setSpnProductUnits(final AppCompatSpinner appCompatSpinner) {
        Intrinsics.checkNotNullParameter(appCompatSpinner, "<set-?>");
        spnProductUnits = appCompatSpinner;
    }
    public View[] getDiscount() {
        return discount;
    }
    public void setDiscount(final View[] viewArr) {
        Intrinsics.checkNotNullParameter(viewArr, "<set-?>");
        discount = viewArr;
    }
    public EditText[] getEdtProductDiscount() {
        return edtProductDiscount;
    }
    public void setEdtProductDiscount(final EditText[] editTextArr) {
        Intrinsics.checkNotNullParameter(editTextArr, "<set-?>");
        edtProductDiscount = editTextArr;
    }
    public ImageButton[] getImgBtnProductDiscountChange() {
        return imgBtnProductDiscountChange;
    }
    public void setImgBtnProductDiscountChange(final ImageButton[] imageButtonArr) {
        Intrinsics.checkNotNullParameter(imageButtonArr, "<set-?>");
        imgBtnProductDiscountChange = imageButtonArr;
    }
    public CheckBox getChkSalesDetailPromotion() {
        return chkSalesDetailPromotion;
    }
    public void setChkSalesDetailPromotion(final CheckBox checkBox) {
        Intrinsics.checkNotNullParameter(checkBox, "<set-?>");
        chkSalesDetailPromotion = checkBox;
    }
    public Spinner getSpnLineType() {
        return spnLineType;
    }
    public void setSpnLineType(final Spinner spinner) {
        Intrinsics.checkNotNullParameter(spinner, "<set-?>");
        spnLineType = spinner;
    }
    public View getPriceLayout() {
        return priceLayout;
    }
    public void setPriceLayout(final View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        priceLayout = view;
    }
    public View getPriceContainer() {
        return priceContainer;
    }
    public void setPriceContainer(final View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        priceContainer = view;
    }
    public View getLnAmountDialogOpener() {
        return lnAmountDialogOpener;
    }
    public void setLnAmountDialogOpener(final View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        lnAmountDialogOpener = view;
    }
    public View getPriceInfoContainer() {
        return priceInfoContainer;
    }
    public void setPriceInfoContainer(final View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        priceInfoContainer = view;
    }
    public View getLnSurplusAmount() {
        return lnSurplusAmount;
    }
    public void setLnSurplusAmount(final View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        lnSurplusAmount = view;
    }
    public View getToggleButton() {
        return toggleButton;
    }
    public void setToggleButton(final View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        toggleButton = view;
    }
    public TextView getTxtProductName() {
        return txtProductName;
    }
    public void setTxtProductName(final TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        txtProductName = textView;
    }
    public TextView getTxtProductAmountTitle() {
        return txtProductAmountTitle;
    }
    public void setTxtProductAmountTitle(final TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        txtProductAmountTitle = textView;
    }
    public TextView getToggle() {
        return toggle;
    }
    public void setToggle(final TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        toggle = textView;
    }
    public TextView getTxtDialogSelectedAmount() {
        return txtDialogSelectedAmount;
    }
    public void setTxtDialogSelectedAmount(final TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        txtDialogSelectedAmount = textView;
    }
    public TextView getTxtPriceInfo() {
        return txtPriceInfo;
    }
    public void setTxtPriceInfo(final TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        txtPriceInfo = textView;
    }
    public EditText getEdtSurplusAmount() {
        return edtSurplusAmount;
    }
    public void setEdtSurplusAmount(final EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "<set-?>");
        edtSurplusAmount = editText;
    }
    public EditText getEdtPrice() {
        return edtPrice;
    }
    public void setEdtPrice(final EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "<set-?>");
        edtPrice = editText;
    }
    public EditText getMExplanationEditText() {
        return mExplanationEditText;
    }
    public void setMExplanationEditText(final EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "<set-?>");
        mExplanationEditText = editText;
    }
    public EditText getEdtProductAmount() {
        return edtProductAmount;
    }
    public void setEdtProductAmount(final EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "<set-?>");
        edtProductAmount = editText;
    }
    public VariantListDialogFragment.IVariantSelectionListener getVariantSelectionListener() {
        return variantSelectionListener;
    }
    public void setVariantSelectionListener(final VariantListDialogFragment.IVariantSelectionListener iVariantSelectionListener) {
        variantSelectionListener = iVariantSelectionListener;
    }
    public VariantSelectionParams getVariantSelectionParams() {
        return variantSelectionParams;
    }
    public void setVariantSelectionParams(final VariantSelectionParams variantSelectionParams) {
        this.variantSelectionParams = variantSelectionParams;
    }
    public boolean isPromotionItemPriceEnabled() {
        return isPromotionItemPriceEnabled;
    }
    public void setPromotionItemPriceEnabled(final boolean z) {
        isPromotionItemPriceEnabled = z;
    }
    public int getPercentGrey600() {
        return percentGrey600;
    }
    public void setPercentGrey600(final int i) {
        percentGrey600 = i;
    }
    public int getCoinGrey600() {
        return coinGrey600;
    }
    public void setCoinGrey600(final int i) {
        coinGrey600 = i;
    }
    public ProductOperationView(final Context context, final AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }
    public ProductOperationView(final Context context, final AttributeSet attributeSet, final int i, final DefaultConstructorMarker defaultConstructorMarker) {
        this(context, 0 != (i & 2) ? null : attributeSet);
    }
    private void initDiscountWatcher() {
        final EditText[] editTextArr = edtProductDiscount;
        mDiscountTextWatchers = new DiscountTextWatcher[editTextArr.length];
        final int length = editTextArr.length;
        for (int i = 0; i < length; i++) {
            DiscountTextWatcher[] discountTextWatcherArr = mDiscountTextWatchers;
            DiscountTextWatcher[] discountTextWatcherArr2 = null;
            if (null == discountTextWatcherArr) {
                Intrinsics.throwUninitializedPropertyAccessException("mDiscountTextWatchers");
                discountTextWatcherArr = null;
            }
            discountTextWatcherArr[i] = new DiscountTextWatcher(i);
            final EditText editText = edtProductDiscount[i];
            Intrinsics.checkNotNull(editText);
            final DiscountTextWatcher[] discountTextWatcherArr3 = mDiscountTextWatchers;
            if (null == discountTextWatcherArr3) {
                Intrinsics.throwUninitializedPropertyAccessException("mDiscountTextWatchers");
            } else {
                discountTextWatcherArr2 = discountTextWatcherArr3;
            }
            editText.addTextChangedListener(discountTextWatcherArr2[i]);
        }
    }
    private void initDiscountClick() {
        final ImageButton[] imageButtonArr = imgBtnProductDiscountChange;
        mProductDiscountClickListeners = new ProductDiscountClickListener[imageButtonArr.length];
        final int length = imageButtonArr.length;
        for (int i = 0; i < length; i++) {
            ProductDiscountClickListener[] productDiscountClickListenerArr = mProductDiscountClickListeners;
            ProductDiscountClickListener[] productDiscountClickListenerArr2 = null;
            if (null == productDiscountClickListenerArr) {
                Intrinsics.throwUninitializedPropertyAccessException("mProductDiscountClickListeners");
                productDiscountClickListenerArr = null;
            }
            productDiscountClickListenerArr[i] = new ProductDiscountClickListener(i);
            final ImageButton imageButton = imgBtnProductDiscountChange[i];
            Intrinsics.checkNotNull(imageButton);
            final ProductDiscountClickListener[] productDiscountClickListenerArr3 = mProductDiscountClickListeners;
            if (null == productDiscountClickListenerArr3) {
                Intrinsics.throwUninitializedPropertyAccessException("mProductDiscountClickListeners");
            } else {
                productDiscountClickListenerArr2 = productDiscountClickListenerArr3;
            }
            imageButton.setOnClickListener(productDiscountClickListenerArr2[i]);
        }
    }
    private void initVariantSelectionDialog() {
        rltProductOperationAmount.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.product.view.list.ProductOperationViewExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                initVariantSelectionDialoglambda0(ProductOperationView.this, view);
            }
        });
    }
    public static void initVariantSelectionDialoglambda0(final ProductOperationView productOperationView, final View view) {
        Intrinsics.checkNotNullParameter(productOperationView, "this0");
        final VariantListDialogFragment.Companion companion = VariantListDialogFragment.Companion;
        final VariantSelectionParams variantSelectionParams = productOperationView.variantSelectionParams;
        Intrinsics.checkNotNull(variantSelectionParams);
        final VariantListDialogFragment newInstance = companion.newInstance(variantSelectionParams);
        final VariantListDialogFragment.IVariantSelectionListener iVariantSelectionListener = productOperationView.variantSelectionListener;
        Intrinsics.checkNotNull(iVariantSelectionListener);
        newInstance.setVariantSelectionListener(iVariantSelectionListener);
        newInstance.setCancelable(false);
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        newInstance.show(((FragmentActivity) activity).getSupportFragmentManager(), "");
    }
    public void setProductProcess(final Product r8, final SalesType r9, final int r10, final boolean r11, final boolean r12, final boolean r13, final boolean r14) {
        Intrinsics.checkNotNullParameter(r8, "product");
        Intrinsics.checkNotNullParameter(r9, "salesType");
        Intrinsics.checkNotNullParameter(r11, "isVariant");
        Intrinsics.checkNotNullParameter(r12, "isDiscount");
        Intrinsics.checkNotNullParameter(r13, "isSurplus");
        Intrinsics.checkNotNullParameter(r14, "isGlobalLineType");
        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.product.view.list.ProductOperationView.setProductProcess(com.proje.mobilesales.features.product.model.Product, com.proje.mobilesales.core.enums.SalesType, int, boolean, boolean, boolean, boolean):void");
    }
    private void setAmount(final Product product) {
        if (0.0d == product.getAmount()) {
            product.setAmount(StringUtils.convertStringToDouble(edtProductAmount.getText().toString()));
        }
        if (product.getDivUnit()) {
            edtProductAmount.setText(StringUtils.convertDoubleToString(Double.valueOf(product.getAmount())));
            edtProductAmount.setInputType(8194);
            return;
        }
        edtProductAmount.setText(StringUtils.convertIntToString((int) product.getAmount()));
        edtProductAmount.setInputType(2);
    }
    private void setSurplusAmount(final Product product) {
        if (0.0d == product.getAmount()) {
            product.setSurplusAmount(StringUtils.convertStringToDouble(edtSurplusAmount.getText().toString()));
        }
        String str = "";
        if (product.getDivUnit()) {
            final EditText editText = edtSurplusAmount;
            if (0.0d != product.getSurplusAmount()) {
                str = StringUtils.convertDoubleToString(Double.valueOf(product.getSurplusAmount()));
            }
            editText.setText(str);
            edtSurplusAmount.setInputType(8194);
            return;
        }
        final EditText editText2 = edtSurplusAmount;
        if (0.0d != product.getSurplusAmount()) {
            str = StringUtils.convertIntToString((int) product.getSurplusAmount());
        }
        editText2.setText(str);
        edtSurplusAmount.setInputType(2);
    }
    private   void initPromotion(final Product product) {
        chkSalesDetailPromotion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) {
            public final   ProductOperationView f1 = null;
            public void onCheckedChanged(final CompoundButton compoundButton, final boolean z) {
                initPromotionlambda1(product, f1, compoundButton, z);
            }
        });
        this.checkPromotionEnterPrice(product.getPromotion());
    }
    public static   void initPromotionlambda1(final Product product, final ProductOperationView productOperationView, final CompoundButton compoundButton, final boolean z) {
        Intrinsics.checkNotNullParameter(product, "product");
        Intrinsics.checkNotNullParameter(productOperationView, "this0");
        product.setPromotion(z);
        productOperationView.checkPromotionEnterPrice(z);
    }
    private   void checkPromotionEnterPrice(final boolean z) {
        if (!isPromotionItemPriceEnabled) {
            if (z) {
                edtPrice.setText("");
                edtPrice.setEnabled(false);
                return;
            }
            edtPrice.setEnabled(true);
        }
    }
    private   void initGlobalLineType(final Product product) {
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this.getContext(), R.layout.layout_product_spinner_item, this.getContext().getResources().getStringArray(R.array.array_global_line_type));
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnLineType.setAdapter(arrayAdapter);
        spnLineType.setSelection(product.getGlobalLineType());
        if (!product.getHasOrderReference()) {
            spnLineType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(product, this) {
                final Product product = null;
                final ProductOperationView this0 = null;
                public void onItemSelected(final AdapterView<?> adapterView, final View view, final int i, final long j) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    product.setGlobalLineType(this0.getContext().getResources().getIntArray(R.array.array_global_line_values)[i]);
                }
                public void onNothingSelected(final AdapterView<?> parent) {

                }
            });
        }
    }
    private void goneToggleButton() {
        toggleButton.setVisibility(GONE);
    }
    private void setProductDiscount(final Product product) {
        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.product.view.list.ProductOperationView.setProductDiscount(com.proje.mobilesales.features.product.model.Product):void");
    }
    private void toggleDiscountVisibility(final boolean z) {
        if (!z) {
            lnProductDiscountContainer.setVisibility(GONE);
        } else {
            lnProductDiscountContainer.setVisibility(VISIBLE);
        }
    }
    public void toggleDiscountImage(final int i, final boolean z) {
        if (!z) {
            final ImageButton imageButton = imgBtnProductDiscountChange[i];
            Intrinsics.checkNotNull(imageButton);
            imageButton.setImageDrawable(ResourcesCompat.getDrawable(this.getResources(), coinGrey600, null));
            return;
        }
        final ImageButton imageButton2 = imgBtnProductDiscountChange[i];
        Intrinsics.checkNotNull(imageButton2);
        imageButton2.setImageDrawable(ResourcesCompat.getDrawable(this.getResources(), percentGrey600, null));
    }
    public void reset(final double d, final double d2) {
        this.toggleDiscountVisibility(true);
        txtProductName.setText(mLoadingText);
        mAmountTextWatcher.setReady(false);
        mExplanationTextWatcher.setReady(false);
        edtProductAmount.setText(StringUtils.convertDoubleToString(d));
        mSurplusAmountTextWatcher.setReady(false);
        edtSurplusAmount.setText(StringUtils.convertDoubleToString(d));
        this.resetDiscount(d2);
    }
    private void resetDiscount(final double d) {
        final int length = edtProductDiscount.length;
        for (int i = 0; i < length; i++) {
            final DiscountTextWatcher discountTextWatcher = mDiscountTextWatchers[i];
            Intrinsics.checkNotNull(discountTextWatcher);
            discountTextWatcher.setReady(false);
            final EditText editText = edtProductDiscount[i];
            Intrinsics.checkNotNull(editText);
            editText.setText(StringUtils.convertDoubleToString(d));
            final ImageButton imageButton = imgBtnProductDiscountChange[i];
            Intrinsics.checkNotNull(imageButton);
            imageButton.setImageDrawable(ResourcesCompat.getDrawable(this.getResources(), percentGrey600, null));
        }
    }
    public void toggleExpand(final Product product, final boolean z, final int i, final boolean z2, final boolean z3) {
        Intrinsics.checkNotNullParameter(product, "product");
        for (int i2 = 1; i2 < 3; i2++) {
            final ProductOperationDiscount productOperationDiscount = product.getProductOperationDiscount();
            Intrinsics.checkNotNull(productOperationDiscount);
            this.toggleDiscount(productOperationDiscount.isDoRatio(i2), z, i2);
            final ProductOperationDiscount productOperationDiscount2 = product.getProductOperationDiscount();
            Intrinsics.checkNotNull(productOperationDiscount2);
            this.toggleDiscount(productOperationDiscount2.isDoTotal(i2), z, i2);
        }
        if (product.getShowExplanation()) {
            this.toggleExplanation(z);
        }
        if (0 < i + product.getLineNr() && z2) {
            this.togglePromotion(z);
        }
        if (z3 && product.getTrackType() == TrackType.NON_TRACK.getType()) {
            this.toggleSurplusAmount(z);
        }
    }
    private void toggleExplanation(final boolean z) {
        if (z) {
            mExplanationEditText.setVisibility(VISIBLE);
        } else {
            mExplanationEditText.setVisibility(GONE);
        }
    }
    private void toggleDiscount(final boolean z, final boolean z2, final int i) {
        if (z) {
            if (z2) {
                final View view = discount[i];
                Intrinsics.checkNotNull(view);
                view.setVisibility(VISIBLE);
                return;
            }
            final View view2 = discount[i];
            Intrinsics.checkNotNull(view2);
            view2.setVisibility(GONE);
        }
    }
    private void togglePromotion(final boolean z) {
        if (z) {
            lnSalesDetailPromotion.setVisibility(VISIBLE);
        } else {
            lnSalesDetailPromotion.setVisibility(GONE);
        }
    }
    private void toggleSurplusAmount(final boolean z) {
        if (z) {
            lnSurplusAmount.setVisibility(VISIBLE);
        } else {
            lnSurplusAmount.setVisibility(GONE);
        }
    }
    public void resetPrice(final Product product) {
        product.setPrice(0.0d);
        product.setPriceRef(-1);
        product.setPriceWithDigits("");
    }
    public void backUpPrice(final Product product) {
        if (0 < product.getPriceRef()) {
            product.setTempPriceRef(product.getPriceRef());
            product.setTempPrice(product.getPrice());
            product.setTempCPrice(product.getCPrice());
            product.setTempPriceWithDigits(product.getPriceWithDigits());
        }
    }
    public abstract class ProductTextWatcher implements TextWatcher {
        private Product mProduct;
        private boolean mReady;

        public void beforeTextChanged(final CharSequence charSequence, final int i, final int i2, final int i3) {
            Intrinsics.checkNotNullParameter(charSequence, "s");
        }

        public void onTextChanged(final CharSequence charSequence, final int i, final int i2, final int i3) {
            Intrinsics.checkNotNullParameter(charSequence, "s");
        }

        protected ProductTextWatcher() {
        }

        protected final Product getMProduct() {
            return mProduct;
        }

        protected final void setMProduct(final Product product) {
            mProduct = product;
        }

        protected final boolean getMReady() {
            return mReady;
        }

        protected final void setMReady(final boolean z) {
            mReady = z;
        }

        public final void changeProduct(final Product product) {
            mProduct = product;
            mReady = true;
        }

        public final void setReady(final boolean z) {
            mReady = z;
        }
    }
    private final class AmountTextWatcher extends ProductTextWatcher {
        public AmountTextWatcher() {
        }

        public void afterTextChanged(final Editable editable) {
            Intrinsics.checkNotNullParameter(editable, "s");
            if (this.getMReady()) {
                final Product mProduct = this.getMProduct();
                Intrinsics.checkNotNull(mProduct);
                mProduct.setAmount(StringUtils.convertStringToDouble(getEdtProductAmount().getText().toString()));
            }
        }
    }
    private final class SurplusAmountTextWatcher extends ProductTextWatcher {
        public SurplusAmountTextWatcher() {
        }
        public void afterTextChanged(final Editable editable) {
            Intrinsics.checkNotNullParameter(editable, "s");
            if (this.getMReady()) {
                final Product mProduct = this.getMProduct();
                Intrinsics.checkNotNull(mProduct);
                mProduct.setSurplusAmount(StringUtils.convertStringToDouble(getEdtSurplusAmount().getText().toString()));
            }
        }
    }
    private final class ExplanationTextWatcher extends ProductTextWatcher {
        public ExplanationTextWatcher() {
        }
        public void afterTextChanged(final Editable editable) {
            Intrinsics.checkNotNullParameter(editable, "s");
            if (this.getMReady()) {
                final Product mProduct = this.getMProduct();
                Intrinsics.checkNotNull(mProduct);
                mProduct.setExplanation(editable.toString());
            }
        }
    }
    public final class DiscountTextWatcher extends ProductTextWatcher {
        private final int index;

        public DiscountTextWatcher(final int i) {
            index = i;
        }
        public void afterTextChanged(final Editable editable) {
            Intrinsics.checkNotNullParameter(editable, "editable");
            if (this.getMReady()) {
                final EditText editText = getEdtProductDiscount()[index];
                Intrinsics.checkNotNull(editText);
                final double convertStringToDouble = StringUtils.convertStringToDouble(editText.getText().toString());
                final Product mProduct = this.getMProduct();
                Intrinsics.checkNotNull(mProduct);
                if (!mProduct.getDiscountRatio()[index]) {
                    final Product mProduct2 = this.getMProduct();
                    Intrinsics.checkNotNull(mProduct2);
                    mProduct2.getDiscount()[index] = convertStringToDouble;
                } else if (100.0d < convertStringToDouble) {
                    final EditText editText2 = getEdtProductDiscount()[index];
                    Intrinsics.checkNotNull(editText2);
                    final Product mProduct3 = this.getMProduct();
                    Intrinsics.checkNotNull(mProduct3);
                    editText2.setText(StringUtils.convertDoubleToString(Double.valueOf(mProduct3.getDiscount()[index])));
                } else if (0.0d > convertStringToDouble) {
                    final EditText editText3 = getEdtProductDiscount()[index];
                    Intrinsics.checkNotNull(editText3);
                    final Product mProduct4 = this.getMProduct();
                    Intrinsics.checkNotNull(mProduct4);
                    editText3.setText(StringUtils.convertDoubleToString(Double.valueOf(mProduct4.getDiscount()[index])));
                } else {
                    final Product mProduct5 = this.getMProduct();
                    Intrinsics.checkNotNull(mProduct5);
                    mProduct5.getDiscount()[index] = convertStringToDouble;
                }
            }
        }
    }
    private static final class SpinnerClickListener implements AdapterView.OnItemSelectedListener {
        private Product mProduct;
        private ProductOperationView mView;
        public void onNothingSelected(final AdapterView<?> adapterView) {
        }
        public void setProduct(final Product product) {
            mProduct = product;
        }
        public void setView(final ProductOperationView productOperationView) {
            mView = productOperationView;
        }

        public void onItemSelected(final AdapterView<?> adapterView, final View view, final int i, final long j) {
            final String str;
            Intrinsics.checkNotNullParameter(view, "view");
            final Product product = mProduct;
            if (null != product) {
                Intrinsics.checkNotNull(product);
                product.selectUnitIndex(i);
                if (null != this.mView) {
                    final Product product2 = mProduct;
                    Intrinsics.checkNotNull(product2);
                    if (product2.getDivUnit()) {
                        final ProductOperationView productOperationView = mView;
                        Intrinsics.checkNotNull(productOperationView);
                        final EditText edtProductAmount = productOperationView.getEdtProductAmount();
                        final Product product3 = mProduct;
                        Intrinsics.checkNotNull(product3);
                        edtProductAmount.setText(StringUtils.convertDoubleToString(Double.valueOf(product3.getAmount())));
                        final ProductOperationView productOperationView2 = mView;
                        Intrinsics.checkNotNull(productOperationView2);
                        productOperationView2.getEdtProductAmount().setInputType(8194);
                    } else {
                        final ProductOperationView productOperationView3 = mView;
                        Intrinsics.checkNotNull(productOperationView3);
                        final EditText edtProductAmount2 = productOperationView3.getEdtProductAmount();
                        final Product product4 = mProduct;
                        Intrinsics.checkNotNull(product4);
                        edtProductAmount2.setText(StringUtils.convertIntToString((int) product4.getAmount()));
                        final ProductOperationView productOperationView4 = mView;
                        Intrinsics.checkNotNull(productOperationView4);
                        productOperationView4.getEdtProductAmount().setInputType(2);
                    }
                    final ProductOperationView productOperationView5 = mView;
                    Intrinsics.checkNotNull(productOperationView5);
                    final VariantSelectionParams variantSelectionParams = productOperationView5.getVariantSelectionParams();
                    Intrinsics.checkNotNull(variantSelectionParams);
                    final Product product5 = mProduct;
                    Intrinsics.checkNotNull(product5);
                    final String unitCode2 = product5.getUnitCode2();
                    Intrinsics.checkNotNull(unitCode2);
                    variantSelectionParams.setUnitCode(unitCode2);
                    final ProductOperationView productOperationView6 = mView;
                    Intrinsics.checkNotNull(productOperationView6);
                    final VariantSelectionParams variantSelectionParams2 = productOperationView6.getVariantSelectionParams();
                    Intrinsics.checkNotNull(variantSelectionParams2);
                    final Product product6 = mProduct;
                    Intrinsics.checkNotNull(product6);
                    variantSelectionParams2.setUnitRef(product6.getUnitRef());
                    final ProductListViewModel productListViewModel = null;
                    if (productListViewModel.erpType() != ErpType.NETSIS) {
                        final Product product7 = mProduct;
                        Intrinsics.checkNotNull(product7);
                        final String valueOf = String.valueOf(product7.getPriceRef());
                        final Product product8 = mProduct;
                        Intrinsics.checkNotNull(product8);
                        if (null != product8.getVariantCode()) {
                            final Product product9 = mProduct;
                            Intrinsics.checkNotNull(product9);
                            str = product9.getVariantCode();
                        } else {
                            str = "";
                        }
                        final List<ItemPrice> itemPriceTypeTableFromLogoSqlHelper = productListViewModel.getItemPriceTypeTableFromLogoSqlHelper(ItemPrice.class, "LOGICALREF=? AND (VARIANTCODE = ? OR VARIANTCODE='')", new String[]{valueOf, str});
                        if (null != itemPriceTypeTableFromLogoSqlHelper && !itemPriceTypeTableFromLogoSqlHelper.isEmpty() && 0 != itemPriceTypeTableFromLogoSqlHelper.get(0).getUnitConvert()) {
                            final ItemPrice itemPrice = itemPriceTypeTableFromLogoSqlHelper.get(0);
                            final ISqlHelper<?> sqlHelper = productListViewModel.getSqlHelper();
                            final int unitRef = itemPrice.getUnitRef();
                            final Product product10 = mProduct;
                            Intrinsics.checkNotNull(product10);
                            final double[] unitConvfact = sqlHelper.getUnitConvfact(unitRef, product10.getLogicalRef());
                            Intrinsics.checkNotNullExpressionValue(unitConvfact, "getUnitConvfact(...)");
                            final Product product11 = mProduct;
                            Intrinsics.checkNotNull(product11);
                            final double d = itemPrice.price;
                            final Product product12 = mProduct;
                            Intrinsics.checkNotNull(product12);
                            final double convfact1 = product12.getConvfact1();
                            final Product product13 = mProduct;
                            Intrinsics.checkNotNull(product13);
                            product11.setPrice(CalculateUtils.convertUnitPrice(d, convfact1, product13.getConvfact2(), unitConvfact[0], unitConvfact[1]));
                            final ProductOperationView productOperationView7 = mView;
                            Intrinsics.checkNotNull(productOperationView7);
                            final TextView txtPriceInfo = productOperationView7.getTxtPriceInfo();
                            final Product product14 = mProduct;
                            Intrinsics.checkNotNull(product14);
                            txtPriceInfo.setText(StringUtils.convertDoubleToString(Double.valueOf(product14.getPrice())));
                        }
                    }
                }
            }
        }
    }
    private final class ProductAmountClickListener implements View.OnClickListener {
        private final boolean mPlus;
        private Product mProduct;
        public ProductAmountClickListener(final boolean z) {
            mPlus = z;
        }
        public void changeProduct(final Product product) {
            mProduct = product;
        }
        public void onClick(final View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            final Product product = mProduct;
            if (null != product) {
                final boolean z = mPlus;
                Intrinsics.checkNotNull(product);
                if (z) {
                    product.plusAmount();
                } else {
                    product.minusAmount();
                }
                final Product product2 = mProduct;
                Intrinsics.checkNotNull(product2);
                if (product2.getDivUnit()) {
                    final EditText edtProductAmount = getEdtProductAmount();
                    final Product product3 = mProduct;
                    Intrinsics.checkNotNull(product3);
                    edtProductAmount.setText(StringUtils.convertDoubleToString(Double.valueOf(product3.getAmount())));
                    return;
                }
                final EditText edtProductAmount2 = getEdtProductAmount();
                final Product product4 = mProduct;
                Intrinsics.checkNotNull(product4);
                edtProductAmount2.setText(StringUtils.convertIntToString((int) product4.getAmount()));
            }
        }
    }
    public final class ProductDiscountClickListener implements View.OnClickListener {
        private final int mIndex;
        private Product mProduct;
        public ProductDiscountClickListener(final int i) {
            mIndex = i;
        }
        public void changeProduct(final Product product) {
            mProduct = product;
        } 
        public void onClick(final View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            final Product product = mProduct;
            if (null != product) {
                Intrinsics.checkNotNull(product);
                if (product.getDiscountRatio()[mIndex]) {
                    final Product product2 = mProduct;
                    Intrinsics.checkNotNull(product2);
                    product2.getDiscountRatio()[mIndex] = false;
                } else {
                    final Product product3 = mProduct;
                    Intrinsics.checkNotNull(product3);
                    product3.getDiscountRatio()[mIndex] = true;
                }
                final ProductOperationView productOperationView = ProductOperationView.this;
                final int i = mIndex;
                final Product product4 = mProduct;
                Intrinsics.checkNotNull(product4);
                productOperationView.toggleDiscountImage(i, product4.getDiscountRatio()[mIndex]);
            }
        }
    }
    public final class PriceTextListener extends CustomTextListener { 
        public PriceTextListener() {
        }
         public void afterTextChanged(final Editable editable) {
            Intrinsics.checkNotNullParameter(editable, "s");
            super.afterTextChanged(editable);
            if (null != get_data()) {
                if (TextUtils.isEmpty(editable)) {
                    final Product product = this.get_data();
                    Intrinsics.checkNotNull(product);
                    final Product product2 = this.get_data();
                    Intrinsics.checkNotNull(product2);
                    product.setPriceRef(product2.getTempPriceRef());
                    final Product product3 = this.get_data();
                    Intrinsics.checkNotNull(product3);
                    final Product product4 = this.get_data();
                    Intrinsics.checkNotNull(product4);
                    product3.setPrice(product4.getTempPrice());
                    final Product product5 = this.get_data();
                    Intrinsics.checkNotNull(product5);
                    final Product product6 = this.get_data();
                    Intrinsics.checkNotNull(product6);
                    product5.setPriceWithDigits(product6.getTempPriceWithDigits());
                    final Product product7 = this.get_data();
                    Intrinsics.checkNotNull(product7);
                    final Product product8 = this.get_data();
                    Intrinsics.checkNotNull(product8);
                    product7.setCPrice(product8.getTempCPrice());
                    return;
                }
                final double convertStringToDouble = StringUtils.convertStringToDouble(editable.toString());
                final int i = (0.0d < convertStringToDouble ? 1 : (0.0d == convertStringToDouble ? 0 : -1));
                if (0 < i) {
                    final ProductOperationView productOperationView = ProductOperationView.this;
                    final Product product9 = this.get_data();
                    Intrinsics.checkNotNull(product9);
                    productOperationView.backUpPrice(product9);
                }
                final Product product10 = this.get_data();
                Intrinsics.checkNotNull(product10);
                product10.setPrice(convertStringToDouble);
                if (0 < i) {
                    final ProductOperationView productOperationView2 = ProductOperationView.this;
                    final Product product11 = this.get_data();
                    Intrinsics.checkNotNull(product11);
                    productOperationView2.resetPrice(product11);
                    final Product product12 = this.get_data();
                    Intrinsics.checkNotNull(product12);
                    product12.setPrice(StringUtils.convertStringToDouble(editable.toString()));
                }
            }
        }
    }
    public class CustomTextListener implements TextWatcher {
        private Product _data; 
        public void afterTextChanged(final Editable editable) {
            Intrinsics.checkNotNullParameter(editable, "s");
        }
 
        public void beforeTextChanged(final CharSequence charSequence, final int i, final int i2, final int i3) {
            Intrinsics.checkNotNullParameter(charSequence, "s");
        } 
        public void onTextChanged(final CharSequence charSequence, final int i, final int i2, final int i3) {
            Intrinsics.checkNotNullParameter(charSequence, "s");
        } 
        public CustomTextListener() {
        }
        public final void updateData(final Product product) {
            this._data = product;
        }
        public final Product get_data() {
            return _data;
        }
        public final void set_data(final Product product) {
            _data = product;
        }
    }
}
