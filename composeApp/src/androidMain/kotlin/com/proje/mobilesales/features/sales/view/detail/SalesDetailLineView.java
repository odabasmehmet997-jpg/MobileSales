package com.proje.mobilesales.features.sales.view.detail;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.LineType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.sales.model.SalesDetail;

public class SalesDetailLineView extends RelativeLayout implements Checkable {
    private final ImageButton btnProductInfo;
    private final ImageView btnProductOperation;
    LinearLayout lnBarcodeCount;
    LinearLayout lnDiscountRate;
    private final int mAlertColor;
    private final int mBackgroundColor;
    private boolean mChecked;
    private final int mHighlightColor;
    private final String mLoadingText;
    private boolean mNegativeStockAlert;
    private final int mPromotedColorResId;
    private boolean mPromotion;
    private final int mSecondaryTextColorResId;
    private final int mTertiaryTextColorResId;
    private final int mVariantColorResId;
    private boolean mVaryantProductAlert;
    RelativeLayout rltProductContainer;
    TextView txtBarcodeCount;
    TextView txtBarcodeCountHeader;
    TextView txtDiscountRate;
    TextView txtDiscountRateHeader;
    TextView txtProductCode;
    TextView txtProductName;
    TextView txtProductTotal;
    TextView txtProductTotalWithCurrency;
    TextView txtProductUnitPrice;
    TextView txtProductVariant;
    TextView txtProductVariantHeader;
    TextView txtPromotion;
    TextView txtSurplusAmount;
    TextView txtSurplusAmountHeader;
    public SalesDetailLineView(final Context context) {
        this(context, null);
    }
    public SalesDetailLineView(final Context context, final AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
    public SalesDetailLineView(final Context context, final AttributeSet attributeSet, final int i2) {
        super(context, attributeSet, i2);
        final TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{R.attr.textColorTertiary, R.attr.textColorSecondary, R.attr.colorCardBackground, R.attr.colorCardHighlight, R.attr.colorCardAlert});
        mTertiaryTextColorResId = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(0, 0));
        mSecondaryTextColorResId = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(1, 0));
        final int color = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(2, 0));
        mBackgroundColor = color;
        mHighlightColor = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(3, 0));
        mAlertColor = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(4, 0));
        mPromotedColorResId = ContextCompat.getColor(context, R.color.greenA700);
        mVariantColorResId = ContextCompat.getColor(context, R.color.greenA600);
        inflate(context, R.layout.sales_line_view, this);
        this.setBackgroundColor(color);
        rltProductContainer = this.findViewById(R.id.rlt_product_container);
        txtProductName = this.findViewById(R.id.txt_productName);
        txtProductCode = this.findViewById(R.id.txt_productCode);
        txtProductTotal = this.findViewById(R.id.txt_productTotal);
        txtProductTotalWithCurrency = this.findViewById(R.id.txt_productTotalWithCurrency);
        txtProductUnitPrice = this.findViewById(R.id.txt_productUnitPrice);
        txtProductVariant = this.findViewById(R.id.txt_productVariant);
        txtProductVariantHeader = this.findViewById(R.id.txt_productVariantHeader);
        btnProductInfo = this.findViewById(R.id.btn_productInfo);
        btnProductOperation = this.findViewById(R.id.btn_productOperation);
        txtSurplusAmountHeader = this.findViewById(R.id.txt_surplusAmountHeader);
        txtSurplusAmount = this.findViewById(R.id.txt_surplusAmount);
        txtPromotion = this.findViewById(R.id.txt_promotion);
        txtBarcodeCountHeader = this.findViewById(R.id.txt_barcode_count_header);
        txtBarcodeCount = this.findViewById(R.id.txt_barcode_count);
        lnBarcodeCount = this.findViewById(R.id.ln_barcode_count);
        lnDiscountRate = this.findViewById(R.id.ln_discount_rate);
        txtDiscountRateHeader = this.findViewById(R.id.txt_discount_rate_header);
        txtDiscountRate = this.findViewById(R.id.txt_discount_rate);
        mLoadingText = this.getContext().getString(R.string.loading_text);
        obtainStyledAttributes.recycle();
    }
    public void setChecked(final boolean z) {
        mChecked = z;
        if (z) {
            this.setBackgroundColor(mHighlightColor);
        } else {
            this.setBackgroundColor(mVaryantProductAlert ? mVariantColorResId : mNegativeStockAlert ? mAlertColor : mPromotion ? mHighlightColor : mBackgroundColor);
        }
    }
    public boolean isChecked() {
        return mChecked;
    }
    public void setNegativeStockAlert(final boolean z) {
        mNegativeStockAlert = z;
    }
    public void setVaryantProductAlert(final boolean z) {
        mVaryantProductAlert = z;
    }
    public void toggle() {
        this.setChecked(!mChecked);
    }
    public void setSalesDetail(final SalesDetail salesDetail) {
        txtProductName.setText(salesDetail.getName());
        txtProductCode.setText(salesDetail.getCode());
        txtProductTotal.setText(Html.fromHtml(this.getLineText(salesDetail)));
        txtProductTotalWithCurrency.setText(Html.fromHtml(this.getLineTextWithCurrency(salesDetail)));
        txtProductUnitPrice.setText(salesDetail.getUnitPriceText());
        if (0 == salesDetail.getBarcodeCount()) {
            lnBarcodeCount.setVisibility(View.GONE);
        } else {
            txtBarcodeCount.setText(String.valueOf(salesDetail.getBarcodeCount()));
        }
        if (!salesDetail.hasCampaign() && salesDetail.getCampaignDiscountRatio().isEmpty()) {
            lnDiscountRate.setVisibility(View.GONE);
        } else {
            lnDiscountRate.setVisibility(VISIBLE);
            txtDiscountRateHeader.setText(String.format("%s:", this.getContext().getString(R.string.str_discount_ratio)));
            final double roundTwoDecimals = salesDetail.getPromotion().isSelect() ? 100.0d : StringUtils.roundTwoDecimals(StringUtils.convertStringToDouble(salesDetail.getCampaignDiscountRatio()));
            txtDiscountRate.setText("%" + roundTwoDecimals);
        }
        mPromotion = salesDetail.getPromotion().isSelect();
        this.setVariant(salesDetail);
        this.setSurplusAmount(salesDetail);
        this.setProductOperationVisibility(salesDetail);
        this.setProductPromotionVisibility(salesDetail);
        this.setBarcodeCountVisibility(salesDetail.getmSalesType());
        this.setBackgroundColor(mVaryantProductAlert ? mVariantColorResId : mNegativeStockAlert ? mAlertColor : mPromotion ? mHighlightColor : mBackgroundColor);
    }
    private String getLineText(final SalesDetail salesDetail) {
        if (0.0d == salesDetail.getSecondAmount().getDefinitionDouble()) {
            return this.getContext().getString(R.string.sales_line_text, salesDetail.getTotalText(), salesDetail.getDiscountText(), salesDetail.getLineNetText());
        }
        txtProductTotal.setLineSpacing(0.0f, 1.25f);
        return this.getContext().getString(R.string.sales_line_text, salesDetail.getTotalText(), salesDetail.getDiscountText(), salesDetail.getLineNetText()) + String.format("<br>(%s %s)", salesDetail.getSecondAmount().getDefinition(), TextUtils.isEmpty(salesDetail.getSecondUnit().getCode()) ? "" : salesDetail.getSecondUnit().getCode());
    }
    private String getLineTextWithCurrency(final SalesDetail salesDetail) {
        if (0.0d == salesDetail.getSecondAmount().getDefinitionDouble()) {
            return this.getContext().getString(R.string.sales_line_text, salesDetail.getTotalText(), salesDetail.getDiscountTextWithCurrency(), salesDetail.getLineNetTextWithCurrency());
        }
        txtProductTotal.setLineSpacing(0.0f, 1.25f);
        return this.getContext().getString(R.string.sales_line_text, salesDetail.getTotalText(), salesDetail.getDiscountTextWithCurrency(), salesDetail.getLineNetTextWithCurrency()) + String.format("<br>(%s %s)", salesDetail.getSecondAmount().getDefinition(), TextUtils.isEmpty(salesDetail.getSecondUnit().getCode()) ? "" : salesDetail.getSecondUnit().getCode());
    }
    public void reset() {
        txtProductName.setText(mLoadingText);
        txtProductCode.setText(mLoadingText);
        txtProductTotal.setText(mLoadingText);
        txtProductTotalWithCurrency.setText(mLoadingText);
        txtProductVariant.setText(mLoadingText);
    }
    private void setVariant(final SalesDetail salesDetail) {
        if (salesDetail.isHasVariant()) {
            final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(3, txtProductVariant.getId());
            layoutParams.addRule(11);
            layoutParams.addRule(21);
            txtProductUnitPrice.setLayoutParams(layoutParams);
            txtProductVariant.setVisibility(VISIBLE);
            txtProductVariant.setText(this.getVariantLineText(salesDetail));
            txtProductVariantHeader.setVisibility(VISIBLE);
            return;
        }
        final RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(3, txtProductTotal.getId());
        layoutParams2.addRule(11);
        layoutParams2.addRule(21);
        txtProductVariant.setVisibility(GONE);
        txtProductVariantHeader.setVisibility(GONE);
    }
    private void setSurplusAmount(final SalesDetail salesDetail) {
        if (null != salesDetail.getSurplusAmount() && 0.0d != salesDetail.getSurplusAmount().getDefinitionDouble()) {
            txtSurplusAmount.setText(salesDetail.getSurplusAmount().toString());
            txtSurplusAmount.setVisibility(VISIBLE);
            txtSurplusAmountHeader.setVisibility(VISIBLE);
        } else {
            txtSurplusAmountHeader.setVisibility(GONE);
            txtSurplusAmount.setVisibility(GONE);
        }
    }
    private String getVariantLineText(final SalesDetail salesDetail) {
        return StringUtils.catString(salesDetail.getVariant().getCode(), salesDetail.getVariant().toString(), "-");
    }
    public ImageButton getBtnProductInfo() {
        return btnProductInfo;
    }
    public TextView getTxtProductTotalWithCurrency() {
        return txtProductTotalWithCurrency;
    }
    public ImageView getBtnProductOperation() {
        return btnProductOperation;
    }
    public void setProductOperationVisibility(final SalesDetail salesDetail) {
        if (salesDetail.hasCampaign() && salesDetail.getLineType() == LineType.PROMOTION.value) {
            btnProductOperation.setVisibility(VISIBLE);
        } else {
            btnProductOperation.setVisibility(GONE);
        }
    }
    public void setProductPromotionVisibility(final SalesDetail salesDetail) {
        if (salesDetail.getPromotion().isSelect()) {
            txtPromotion.setVisibility(VISIBLE);
        } else {
            txtPromotion.setVisibility(GONE);
        }
    }
    public void setBarcodeCountVisibility(final SalesType salesType) {
        if (ErpCreator.getInstance().getmBaseErp().getLineIntegration(salesType) && !SalesUtils.isSalesTypeDemand(salesType) && !SalesUtils.isSalesTypeWhTransfer(salesType)) {
            txtBarcodeCountHeader.setVisibility(VISIBLE);
            txtBarcodeCount.setVisibility(VISIBLE);
        } else {
            txtBarcodeCountHeader.setVisibility(GONE);
            txtBarcodeCount.setVisibility(GONE);
        }
    }
}
