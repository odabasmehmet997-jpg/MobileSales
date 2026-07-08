package com.proje.mobilesales.features.sales.view.list;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.EDocStatus;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.widget.TintableTextView;
import com.proje.mobilesales.features.sales.model.SalesShort;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SalesView extends RelativeLayout implements Checkable {
    private int mBackgroundColor = 0;
    private boolean mChecked;
    private int mDiffErrorColor = 0;
    private String mEmptyText = "";
    private int mHighlightColor = 0;
    private String mLoadingText = "";
    private View mMoreOption = null;
    private TextView mOrderFicheStatus = null;
    private int mPromotedColorResId = 0;
    private RelativeLayout mRltSalesDetail = null;
    private TextView mSalesCancel = null;
    private String mSalesColonText = "";
    private TextView mSalesDocumentNo = null;
    private String mSalesDocumentNoText = "";
    private TextView mSalesFicheDate = null;
    private TextView mSalesFicheDef = null;
    private TextView mSalesFicheId;
    private TextView mSalesPayPlan = null;
    private String mSalesPayPlanText = "";
    private TextView mSalesShipAddress = null;
    private TextView mSalesShipAddressCode = null;
    private String mSalesShipAddressText = "";
    private String mSalesSpaceText = "";
    private TextView mSalesSpecode = null;
    private String mSalesSpecodeText = "";
    private TextView mSalesStatus = null;
    private String mSalesStatusText = "";
    private TextView mSalesTotal = null;
    private String mSalesTotalText = "";
    private TextView mSalesTradeDay = null;
    private String mSalesTradeDayText = "";
    private TintableTextView mSalesTransfer = null;
    private int mSecondaryTextColorResId = 0;
    private int mTertiaryTextColorResId = 0;
    private View rlEdocStatus = null;
    private RelativeLayout rltSpacer = null;
    private TextView txtEdocStatus = null;

    public class WhenMappings {
        public static final int[] EnumSwitchMapping0;

        static {
            int[] iArr = new int[EDocStatus.values().length];
            try {
                iArr[Integer.parseInt(EDocStatus.Draft.ordinal())] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Integer.parseInt(EDocStatus.Send.ordinal())] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            EnumSwitchMapping0 = iArr;
        }
    }

    
    public SalesView(Context context) {
        this(context, null, 2, 0 != true ? 0 : 1);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    
    public SalesView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{R.attr.textColorTertiary, R.attr.textColorSecondary, R.attr.colorCardBackground, R.attr.colorCardHighlight});
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        this.mTertiaryTextColorResId = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(0, 0));
        this.mSecondaryTextColorResId = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(1, 0));
        int color = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(2, 0));
        this.mBackgroundColor = color;
        this.mHighlightColor = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(3, 0));
        this.mPromotedColorResId = ContextCompat.getColor(context, R.color.greenA700);
        this.mDiffErrorColor = ContextCompat.getColor(context, R.color.redA100);
        View.inflate(context, R.layout.sales_view, this);
        setBackgroundColor(color);
        View findViewById = findViewById(R.id.txt_sales_ship_address_definition);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.mSalesShipAddressCode = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.txt_sales_fiche_id);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.mSalesFicheId = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.txt_sales_fiche_date);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.mSalesFicheDate = (TextView) findViewById3;
        View findViewById4 = findViewById(R.id.txt_sales_fiche_def);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.mSalesFicheDef = (TextView) findViewById4;
        View findViewById5 = findViewById(R.id.rlt_customer_detail);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.mRltSalesDetail = (RelativeLayout) findViewById5;
        View findViewById6 = findViewById(R.id.rlt_spacer);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.rltSpacer = (RelativeLayout) findViewById6;
        View findViewById7 = findViewById(R.id.txt_sales_trade_day);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.mSalesTradeDay = (TextView) findViewById7;
        View findViewById8 = findViewById(R.id.txt_sales_specode);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.mSalesSpecode = (TextView) findViewById8;
        View findViewById9 = findViewById(R.id.txt_sales_document_no);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.mSalesDocumentNo = (TextView) findViewById9;
        View findViewById10 = findViewById(R.id.txt_sales_pay_plan);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
        this.mSalesPayPlan = (TextView) findViewById10;
        View findViewById11 = findViewById(R.id.txt_sales_status);
        Intrinsics.checkNotNullExpressionValue(findViewById11, "findViewById(...)");
        this.mSalesStatus = (TextView) findViewById11;
        View findViewById12 = findViewById(R.id.txt_sales_total);
        Intrinsics.checkNotNullExpressionValue(findViewById12, "findViewById(...)");
        this.mSalesTotal = (TextView) findViewById12;
        View findViewById13 = findViewById(R.id.txt_sales_ship_address);
        Intrinsics.checkNotNullExpressionValue(findViewById13, "findViewById(...)");
        this.mSalesShipAddress = (TextView) findViewById13;
        View findViewById14 = findViewById(R.id.txt_sales_transfer);
        Intrinsics.checkNotNullExpressionValue(findViewById14, "findViewById(...)");
        this.mSalesTransfer = (TintableTextView) findViewById14;
        View findViewById15 = findViewById(R.id.txt_fiche_cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById15, "findViewById(...)");
        this.mSalesCancel = (TextView) findViewById15;
        View findViewById16 = findViewById(R.id.rl_edoc_status);
        Intrinsics.checkNotNullExpressionValue(findViewById16, "findViewById(...)");
        this.rlEdocStatus = findViewById16;
        View findViewById17 = findViewById(R.id.txt_edoc_status);
        Intrinsics.checkNotNullExpressionValue(findViewById17, "findViewById(...)");
        this.txtEdocStatus = (TextView) findViewById17;
        View findViewById18 = findViewById(R.id.button_more);
        Intrinsics.checkNotNullExpressionValue(findViewById18, "findViewById(...)");
        this.mMoreOption = findViewById18;
        View findViewById19 = findViewById(R.id.txt_order_fiche_status);
        Intrinsics.checkNotNullExpressionValue(findViewById19, "findViewById(...)");
        this.mOrderFicheStatus = (TextView) findViewById19;
        String string = getContext().getString(R.string.str_sales_trading_group);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this.mSalesTradeDayText = string;
        String string2 = getContext().getString(R.string.str_sales_specode);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        this.mSalesSpecodeText = string2;
        String string3 = getContext().getString(R.string.str_sales_doc_tracking_no);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        this.mSalesDocumentNoText = string3;
        String string4 = getContext().getString(R.string.str_sales_pay_plan);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        this.mSalesPayPlanText = string4;
        String string5 = getContext().getString(R.string.str_sales_status);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
        this.mSalesStatusText = string5;
        String string6 = getContext().getString(R.string.str_sales_total);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
        this.mSalesTotalText = string6;
        String string7 = getContext().getString(R.string.str_customer_ship_address);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
        this.mSalesShipAddressText = string7;
        String string8 = getContext().getString(R.string.loading_text);
        Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
        this.mLoadingText = string8;
        String string9 = getContext().getString(R.string.empty_text);
        Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
        this.mEmptyText = string9;
        this.mSalesSpaceText = " - ";
        this.mSalesColonText = ":";
        obtainStyledAttributes.recycle();
    }
    public SalesView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }
    public SalesView(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }
    public void setSalesShowDetail(boolean z) {
        if (!z) {
            this.mRltSalesDetail.setVisibility(View.GONE);
            this.rltSpacer.setVisibility(View.VISIBLE);
        } else {
            this.mRltSalesDetail.setVisibility(View.VISIBLE);
            this.rltSpacer.setVisibility(View.GONE);
        }
    }
    public void setSales(SalesShort salesShort) {
        Intrinsics.checkNotNullParameter(salesShort, "salesShort");
        this.mSalesShipAddressCode.setText(salesShort.getShipAddressCode());
        if (ErpCreator.getInstance().getmBaseErp().getErpType() == ErpType.NETSIS) {
            this.mSalesFicheId.setText(salesShort.getFicheId());
        } else {
            this.mSalesFicheId.setText(salesShort.getFicheNo());
        }
        this.mSalesFicheDate.setText(salesShort.getFicheDate());
        this.mSalesFicheDef.setText(salesShort.getFicheDefinition());
        if (salesShort.isCancel()) {
            this.mSalesCancel.setVisibility(View.VISIBLE);
        } else {
            this.mSalesCancel.setVisibility(View.GONE);
        }
        if (this.mRltSalesDetail.getVisibility() == View.VISIBLE) {
            this.mSalesTradeDay.setText(cat(this.mSalesTradeDayText, salesShort.getTradingGroup()));
            this.mSalesSpecode.setText(cat(this.mSalesSpecodeText, salesShort.getSpeCode()));
            this.mSalesDocumentNo.setText(cat(this.mSalesDocumentNoText, salesShort.getDocumentNo()));
            this.mSalesPayPlan.setText(cat(this.mSalesPayPlanText, salesShort.getPayPlan()));
            if (salesShort.getType() == 1) {
                this.mSalesStatus.setText(cat(this.mSalesStatusText, getContext().getString(salesShort.getSalesOrderStatus().getmResId())));
            } else {
                this.mSalesStatus.setText(cat(this.mSalesStatusText, getContext().getString(salesShort.getInvoiceStatus().getmResId())));
            }
            this.mSalesTotal.setText(cat(this.mSalesTotalText, StringUtils.formatDouble(salesShort.getTotal())));
            if (SalesType.Companion.fromSalesType(salesShort.getSalesType()) == SalesType.DEMAND) {
                this.mSalesShipAddress.setVisibility(View.GONE);
            }
            this.mSalesShipAddress.setText(cat(this.mSalesShipAddressText + this.mSalesColonText, salesShort.getShipAddressName()));
            this.mOrderFicheStatus.setText(salesShort.getOrderFicheStatus());
        }
        if (this.rltSpacer.getVisibility() == View.VISIBLE || this.mRltSalesDetail.getVisibility() == View.VISIBLE) {
            if (salesShort.isTransfer()) {
                this.mSalesTransfer.setVisibility(View.VISIBLE);
                this.mSalesTransfer.setLeftDraw(salesShort.isEdit() ? R.drawable.ic_baseline_offline_pin_24 : R.drawable.ic_check_circle_grey600_24dp);
            } else {
                this.mSalesTransfer.setVisibility(View.GONE);
            }
            if (salesShort.isDiffError()) {
                setBackgroundColor(this.mDiffErrorColor);
            } else {
                setBackgroundColor(this.mBackgroundColor);
            }
        }
        int i2 = WhenMappings.EnumSwitchMapping0[Integer.parseInt(salesShort.getEDocumentStatus().ordinal())];
        if (i2 == 1) {
            this.rlEdocStatus.setVisibility(View.VISIBLE);
            this.txtEdocStatus.setText(R.string.str_edoc_draft_created);
        } else if (i2 == 2) {
            this.rlEdocStatus.setVisibility(View.VISIBLE);
            this.txtEdocStatus.setText(R.string.str_edoc_send);
        } else {
            this.rlEdocStatus.setVisibility(View.GONE);
        }
    }

    public void setOrderStatusClickListener(OnClickListener onClickListener) {
        this.mOrderFicheStatus.setOnClickListener(onClickListener);
    }

    public void reset() {
        this.mSalesShipAddressCode.setText(this.mLoadingText);
        this.mSalesFicheId.setText(this.mLoadingText);
        this.mSalesFicheDate.setText(this.mLoadingText);
        this.mSalesFicheDef.setText(this.mLoadingText);
        this.mSalesCancel.setVisibility(View.GONE);
        if (this.mRltSalesDetail.getVisibility() == View.VISIBLE) {
            this.mSalesTradeDay.setText(this.mLoadingText);
            this.mSalesSpecode.setText(this.mLoadingText);
            this.mSalesDocumentNo.setText(this.mLoadingText);
            this.mSalesPayPlan.setText(this.mLoadingText);
            this.mSalesStatus.setText(this.mLoadingText);
            this.mSalesTotal.setText(this.mLoadingText);
            this.mSalesShipAddress.setText(this.mLoadingText);
            this.mOrderFicheStatus.setText(this.mLoadingText);
        }
    }

    private String cat(String str, String str2) {
        if (str2 == null || TextUtils.isEmpty(str2)) {
            return str + " " + this.mEmptyText;
        }
        return str + " " + str2;
    }

    public View getmMoreOption() {
        return this.mMoreOption;
    }
 
    public void setChecked(boolean z) {
        this.mChecked = z;
        if (z) {
            setBackgroundColor(this.mHighlightColor);
        } else {
            setBackgroundColor(z ? this.mHighlightColor : this.mBackgroundColor);
        }
    }
 
    public boolean isChecked() {
        return this.mChecked;
    }
 
    public void toggle() {
        setChecked(!this.mChecked);
    }
}
