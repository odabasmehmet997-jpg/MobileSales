package com.proje.mobilesales.features.sales.view.validation;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Checkable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.widget.TintableTextView;
import com.proje.mobilesales.features.dbmodel.ManagerOrder;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;
import kotlin.text.Regex;

public final class SalesValidationView extends RelativeLayout implements Checkable {
    private int mBackgroundColor = 0;
    private boolean mChecked;
    private String mEmptyText = "";
    private int mHighlightColor = 0;
    private String mLoadingText = "";
    private View mMoreOption = null;
    private int mPromotedColorResId = 0;
    private RelativeLayout mRltSalesDetail = null;
    private TextView mSalesCustomerName = null;
    private TextView mSalesDocumentNo = null;
    private String mSalesDocumentNoText = "";
    private TextView mSalesFicheDate = null;
    private TextView mSalesFicheDef = null;
    private TextView mSalesFicheId = null;
    private TextView mSalesInfoNot = null;
    private String mSalesInfoNotText = "";
    private TextView mSalesPayPlan = null;
    private String mSalesPayPlanText = "";
    private TextView mSalesShipAddressName = null;
    private TextView mSalesSlsDefinition = null;
    private String mSalesSlsDefinitionText = "";
    private String mSalesSpaceText = "";
    private TextView mSalesSpeCode = null;
    private String mSalesSpeCodeText = "";
    private TextView mSalesStatus = null;
    private String mSalesStatusText = "";
    private TextView mSalesTotal = null;
    private String mSalesTotalText = "";
    private TextView mSalesTradeDay = null;
    private String mSalesTradeDayText = "";
    private TintableTextView mSalesTransfer = null;
    private int mSecondaryTextColorResId = 0;
    private int mTertiaryTextColorResId = 0;

    public SalesValidationView(Context context) {
        this(context, null, 2,  0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public SalesValidationView(Context context, AttributeSet attributeSet, int i2) {
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
        View.inflate(context, R.layout.sales_validation_view, this);
        setBackgroundColor(color);
        View findViewById = findViewById(R.id.txt_sales_customer_name);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        this.mSalesCustomerName = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.txt_sales_ship_address_definition);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        this.mSalesShipAddressName = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.txt_sales_fiche_id);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
        this.mSalesFicheId = (TextView) findViewById3;
        View findViewById4 = findViewById(R.id.txt_sales_fiche_date);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.TextView");
        this.mSalesFicheDate = (TextView) findViewById4;
        View findViewById5 = findViewById(R.id.txt_sales_fiche_def);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
        this.mSalesFicheDef = (TextView) findViewById5;
        View findViewById6 = findViewById(R.id.rlt_customer_detail);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.RelativeLayout");
        this.mRltSalesDetail = (RelativeLayout) findViewById6;
        View findViewById7 = findViewById(R.id.txt_sales_trade_day);
        Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type android.widget.TextView");
        this.mSalesTradeDay = (TextView) findViewById7;
        View findViewById8 = findViewById(R.id.txt_sales_specode);
        Intrinsics.checkNotNull(findViewById8, "null cannot be cast to non-null type android.widget.TextView");
        this.mSalesSpeCode = (TextView) findViewById8;
        View findViewById9 = findViewById(R.id.txt_sales_document_no);
        Intrinsics.checkNotNull(findViewById9, "null cannot be cast to non-null type android.widget.TextView");
        this.mSalesDocumentNo = (TextView) findViewById9;
        View findViewById10 = findViewById(R.id.txt_sales_pay_plan);
        Intrinsics.checkNotNull(findViewById10, "null cannot be cast to non-null type android.widget.TextView");
        this.mSalesPayPlan = (TextView) findViewById10;
        View findViewById11 = findViewById(R.id.txt_sales_status);
        Intrinsics.checkNotNull(findViewById11, "null cannot be cast to non-null type android.widget.TextView");
        this.mSalesStatus = (TextView) findViewById11;
        View findViewById12 = findViewById(R.id.txt_sales_total);
        Intrinsics.checkNotNull(findViewById12, "null cannot be cast to non-null type android.widget.TextView");
        this.mSalesTotal = (TextView) findViewById12;
        View findViewById13 = findViewById(R.id.txt_sales_info_not);
        Intrinsics.checkNotNull(findViewById13, "null cannot be cast to non-null type android.widget.TextView");
        this.mSalesInfoNot = (TextView) findViewById13;
        View findViewById14 = findViewById(R.id.txt_sales_transfer);
        Intrinsics.checkNotNull(findViewById14, "null cannot be cast to non-null type com.proje.mobilesales.core.widget.TintableTextView");
        this.mSalesTransfer = (TintableTextView) findViewById14;
        View findViewById15 = findViewById(R.id.txt_sales_slsDefinition);
        Intrinsics.checkNotNullExpressionValue(findViewById15, "findViewById(...)");
        this.mSalesSlsDefinition = (TextView) findViewById15;
        View findViewById16 = findViewById(R.id.button_more);
        Intrinsics.checkNotNullExpressionValue(findViewById16, "findViewById(...)");
        this.mMoreOption = findViewById16;
        String string = getContext().getString(R.string.str_sales_trading_group);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this.mSalesTradeDayText = string;
        String string2 = getContext().getString(R.string.str_sales_specode);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        this.mSalesSpeCodeText = string2;
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
        String string7 = getContext().getString(R.string.str_sales_note);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
        this.mSalesInfoNotText = string7;
        String string8 = getContext().getString(R.string.str_sales_slsdefinition);
        Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
        this.mSalesSlsDefinitionText = string8;
        String string9 = getContext().getString(R.string.loading_text);
        Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
        this.mLoadingText = string9;
        String string10 = getContext().getString(R.string.empty_text);
        Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
        this.mEmptyText = string10;
        this.mSalesSpaceText = " - ";
        obtainStyledAttributes.recycle();
    }

    public SalesValidationView(Context context, AttributeSet attributeSet, int i2, int i) {
        this(context, attributeSet, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public  SalesValidationView(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, 2, 0);
    }

    public void setSales(ManagerOrder managerOrder) {
        List emptyList;
        Intrinsics.checkNotNullParameter(managerOrder, "managerOrder");
        try {
            this.mSalesCustomerName.setText(managerOrder.getcName());
            this.mSalesShipAddressName.setText(cat("", managerOrder.getsCode()));
            this.mSalesFicheId.setText(managerOrder.getFicheNo());
            TextView textView = this.mSalesFicheDate;
            PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
            String date = managerOrder.getDate();
            Intrinsics.checkNotNullExpressionValue(date, "getDate(...)");
            List<String> split = new Regex(ExifInterface.GPS_DIRECTION_TRUE).split(date, 0);
            if (!split.isEmpty()) {
                ListIterator<String> listIterator = split.listIterator(split.size());
                while (listIterator.hasPrevious()) {
                    if (listIterator.previous().length() != 0) {
                        emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            }
            emptyList = CollectionsKt.emptyList();
            String format = String.format("%s %s", Arrays.copyOf(new Object[]{DateAndTimeUtils.convertYMDToDMY(((String[]) emptyList.toArray(new String[0]))[0]), DateAndTimeUtils.intToNowTimeLogo(managerOrder.getTime())}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            textView.setText(format);
            this.mSalesFicheDef.setVisibility(View.GONE);
            this.mSalesTradeDay.setText(cat(this.mSalesTradeDayText, managerOrder.getTradingGrp()));
            this.mSalesSpeCode.setText(cat(this.mSalesSpeCodeText, managerOrder.getSpeCode()));
            this.mSalesDocumentNo.setText(cat(this.mSalesDocumentNoText, managerOrder.getDocTrackingNr()));
            this.mSalesPayPlan.setText(cat(this.mSalesPayPlanText, managerOrder.getPaymentDefinition()));
            this.mSalesTotal.setText(cat(this.mSalesTotalText, StringUtils.formatDouble(managerOrder.getTotal())));
            this.mSalesInfoNot.setText(cat(this.mSalesInfoNotText, managerOrder.getExplanation()));
            this.mSalesStatus.setVisibility(View.INVISIBLE);
            this.mSalesTransfer.setVisibility(View.INVISIBLE);
            this.mSalesSlsDefinition.setText(getSalesManText(managerOrder));
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "setSales: ", e2);
        }
    }

    private String getSalesManText(ManagerOrder managerOrder) {
        String slsCode;
        if (managerOrder.getSlsDefinition() != null) {
            slsCode = managerOrder.getSlsDefinition();
            if (slsCode.length() == 0) {
                slsCode = managerOrder.getSlsCode();
            }
        } else {
            slsCode = managerOrder.getSlsCode();
        }
        return cat(this.mSalesSlsDefinitionText, slsCode);
    }

    public void reset() {
        this.mSalesCustomerName.setText(this.mLoadingText);
        this.mSalesShipAddressName.setText(this.mLoadingText);
        this.mSalesFicheId.setText(this.mLoadingText);
        this.mSalesFicheDate.setText(this.mLoadingText);
        this.mSalesFicheDef.setText(this.mLoadingText);
        if (this.mRltSalesDetail.getVisibility() == View.VISIBLE) {
            this.mSalesTradeDay.setText(this.mLoadingText);
            this.mSalesSpeCode.setText(this.mLoadingText);
            this.mSalesDocumentNo.setText(this.mLoadingText);
            this.mSalesPayPlan.setText(this.mLoadingText);
            this.mSalesStatus.setText(this.mLoadingText);
            this.mSalesTotal.setText(this.mLoadingText);
            this.mSalesInfoNot.setText(this.mLoadingText);
        }
    }

    private String cat(String str, String str2) {
        if (str2 == null || TextUtils.isEmpty(str2)) {
            return str + " " + this.mEmptyText;
        }
        return str + " " + str2;
    }

    public View getMoreOption() {
        return this.mMoreOption;
    }

    public void setChecked(boolean z) {
        if (this.mChecked == z) {
            return;
        }
        this.mChecked = z;
        setBackgroundColor(z ? this.mHighlightColor : this.mBackgroundColor);
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    public void toggle() {
        setChecked(!this.mChecked);
    }
}
