package com.proje.mobilesales.features.customer.view.list;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.customer.model.Customer;
import com.proje.mobilesales.features.customer.utils.BalanceCalculateUtil;
import com.proje.mobilesales.features.gpsinfo.model.database.CustGpsInfo;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerView extends RelativeLayout {
    public static final Companion Companion = new Companion(null);
    private static final int VOTE_DELAY_MILLIS = 500;
    private LinearLayout lnCustomerDebit = null;
    private int mBackgroundColor = 0;
    private TextView mCustomerAverage = null;
    private String mCustomerAverageText = "";
    private TextView mCustomerBalance = null;
    private TextView mCustomerCode = null;
    private TextView mCustomerCredit = null;
    private TextView mCustomerDebit;
    private TextView mCustomerEmail = null;
    private String mCustomerEmailText = "";
    private ImageView mCustomerImage = null;
    private TextView mCustomerInfoNot = null;
    private String mCustomerInfoNotText = "";
    private TextView mCustomerLastOrderDate = null;
    private String mCustomerLastOrderDateText = "";
    private ImageButton mCustomerLocation = null;
    private TextView mCustomerName = null;
    private TextView mCustomerPayPlan = null;
    private String mCustomerPayPlanText = "";
    private TextView mCustomerPerson = null;
    private String mCustomerPersonText = "";
    private TextView mCustomerSecondTitle = null;
    private TextView mCustomerTel = null;
    private String mCustomerTelText = "";
    private int mCustomerWithDailyOperationResId = 0;
    private Drawable mDefaultCustomerImage = null;
    private int mDefaultTextColor = 0;
    private String mEmptyText = "";
    private int mHighlightColor = 0;
    private String mLoadingText = "";
    private int mPromotedColorResId = 0;
    private RelativeLayout mRltCustomerDetail = null;
    private RelativeLayout mRltEInvoiceUser = null;
    private int mSecondaryTextColorResId = 0;
    private int mTertiaryTextColorResId = 0;

    public CustomerView(Context context) {
        this(context, null, 2, (0 == true ? 1 : 0));
        Intrinsics.checkNotNullParameter(context, "context");
    }
    public CustomerView(Context context, AttributeSet attributeSet, int i2) {
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
        this.mCustomerWithDailyOperationResId = ContextCompat.getColor(context, R.color.greenCustomerWithDailyOperation);
        View.inflate(context, R.layout.customer_view, this);
        setBackgroundColor(color);
        View findViewById = findViewById(R.id.txt_productName);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.mCustomerName = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.txt_secondCustomerTitle);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.mCustomerSecondTitle = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.txt_productCode);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        TextView textView = (TextView) findViewById3;
        this.mCustomerCode = textView;
        View findViewById4 = findViewById(R.id.txt_customer_debit);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.mCustomerDebit = (TextView) findViewById4;
        View findViewById5 = findViewById(R.id.txt_real_stock);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.mCustomerCredit = (TextView) findViewById5;
        View findViewById6 = findViewById(R.id.txt_product_price);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.mCustomerBalance = (TextView) findViewById6;
        View findViewById7 = findViewById(R.id.ln_customer_debit);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.lnCustomerDebit = (LinearLayout) findViewById7;
        View findViewById8 = findViewById(R.id.rlt_customer_detail);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.mRltCustomerDetail = (RelativeLayout) findViewById8;
        View findViewById9 = findViewById(R.id.txt_customer_last_order_date);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.mCustomerLastOrderDate = (TextView) findViewById9;
        View findViewById10 = findViewById(R.id.txt_customer_pay_plan);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
        this.mCustomerPayPlan = (TextView) findViewById10;
        View findViewById11 = findViewById(R.id.txt_customer_average);
        Intrinsics.checkNotNullExpressionValue(findViewById11, "findViewById(...)");
        this.mCustomerAverage = (TextView) findViewById11;
        View findViewById12 = findViewById(R.id.txt_customer_person);
        Intrinsics.checkNotNullExpressionValue(findViewById12, "findViewById(...)");
        this.mCustomerPerson = (TextView) findViewById12;
        View findViewById13 = findViewById(R.id.txt_customer_tel);
        Intrinsics.checkNotNullExpressionValue(findViewById13, "findViewById(...)");
        this.mCustomerTel = (TextView) findViewById13;
        View findViewById14 = findViewById(R.id.txt_customer_email);
        Intrinsics.checkNotNullExpressionValue(findViewById14, "findViewById(...)");
        this.mCustomerEmail = (TextView) findViewById14;
        View findViewById15 = findViewById(R.id.txt_customer_info_not);
        Intrinsics.checkNotNullExpressionValue(findViewById15, "findViewById(...)");
        this.mCustomerInfoNot = (TextView) findViewById15;
        View findViewById16 = findViewById(R.id.img_customer_location);
        Intrinsics.checkNotNullExpressionValue(findViewById16, "findViewById(...)");
        this.mCustomerLocation = (ImageButton) findViewById16;
        View findViewById17 = findViewById(R.id.rlt_customer_e_invoice);
        Intrinsics.checkNotNullExpressionValue(findViewById17, "findViewById(...)");
        this.mRltEInvoiceUser = (RelativeLayout) findViewById17;
        View findViewById18 = findViewById(R.id.img_product);
        Intrinsics.checkNotNullExpressionValue(findViewById18, "findViewById(...)");
        this.mCustomerImage = (ImageView) findViewById18;
        String string = getContext().getString(R.string.str_customer_list_last_order);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this.mCustomerLastOrderDateText = string;
        String string2 = getContext().getString(R.string.str_customer_list_pay_plan);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        this.mCustomerPayPlanText = string2;
        String string3 = getContext().getString(R.string.str_customer_list_average);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        this.mCustomerAverageText = string3;
        String string4 = getContext().getString(R.string.str_customer_list_person);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        this.mCustomerPersonText = string4;
        String string5 = getContext().getString(R.string.str_customer_list_tel);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
        this.mCustomerTelText = string5;
        String string6 = getContext().getString(R.string.str_customer_list_email);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
        this.mCustomerEmailText = string6;
        String string7 = getContext().getString(R.string.str_customer_list_info_not);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
        this.mCustomerInfoNotText = string7;
        String string8 = getContext().getString(R.string.loading_text);
        Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
        this.mLoadingText = string8;
        String string9 = getContext().getString(R.string.empty_text);
        Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
        this.mEmptyText = string9;
        this.mDefaultCustomerImage = ContextCompat.getDrawable(getContext(), R.drawable.ic_account_circle_grey600_36dp);
        this.mDefaultTextColor = textView.getCurrentTextColor();
        obtainStyledAttributes.recycle();
    }
    public CustomerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public   CustomerView(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    public void setCustomerShowDetail(boolean z) {
        if (!z) {
            this.mRltCustomerDetail.setVisibility(View.GONE);
        } else {
            this.mRltCustomerDetail.setVisibility(View.VISIBLE);
        }
    }

    public void setCustomer(Customer customer) {
        Intrinsics.checkNotNullParameter(customer, "customer");
        this.mCustomerName.setText(customer.getTitle());
        if (customer.getSecondTitle() != null && !TextUtils.isEmpty(customer.getSecondTitle())) {
            this.mCustomerSecondTitle.setText(customer.getSecondTitle());
            this.mCustomerSecondTitle.setVisibility(View.VISIBLE);
        } else {
            this.mCustomerSecondTitle.setVisibility(View.GONE);
        }
        this.mCustomerCode.setText(customer.getCode());
        if (ErpCreator.getInstance().getmBaseErp().isHideCustomerBalance()) {
            this.lnCustomerDebit.setVisibility(View.GONE);
        } else {
            TextView textView = this.mCustomerDebit;
            BalanceCalculateUtil balanceCalculateUtil = BalanceCalculateUtil.INSTANCE;
            textView.setText(StringUtils.formatDouble(balanceCalculateUtil.getTotalDebit(customer.getLogicalRef(), customer.getDebit())));
            this.mCustomerCredit.setText(StringUtils.formatDouble(balanceCalculateUtil.getTotalCredit(customer.getLogicalRef(), customer.getCredit())));
            this.mCustomerBalance.setText(StringUtils.formatDouble(BalanceCalculateUtil.getTotalBalance(customer.getLogicalRef(), customer.getBalance())));
        }
        if (this.mRltCustomerDetail.getVisibility() == View.VISIBLE) {
            BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
            this.mCustomerLastOrderDate.setText(cat(this.mCustomerLastOrderDateText, customer.getLastOrderDate()));
            this.mCustomerPayPlan.setText(cat(this.mCustomerPayPlanText, customer.getPayPlan()));
            this.mCustomerAverage.setText(cat(this.mCustomerAverageText, customer.getAverage()));
            if (baseErp.getErpType() == ErpType.NETSIS) {
                customer.setPerson(baseErp.getCustomerInCharge(customer.getCode()));
            }
            this.mCustomerPerson.setText(cat(this.mCustomerPersonText, customer.getPerson()));
            TextView textView2 = this.mCustomerTel;
            String str = this.mCustomerTelText;
            StringBuilder sb = new StringBuilder();
            sb.append(customer.getTel());
            String str2 = "";
            if (customer.getTel() != "" && customer.getTel2() != "") {
                str2 = " / ";
            }
            sb.append(str2);
            sb.append(customer.getTel2());
            textView2.setText(cat(str, sb.toString()));
            Linkify.addLinks(this.mCustomerTel, Patterns.PHONE, "tel:");
            this.mCustomerEmail.setText(cat(this.mCustomerEmailText, customer.getEmail()));
            this.mCustomerInfoNot.setText(cat(this.mCustomerInfoNotText, customer.getInfoNote()));
            if (!customer.getEInvoiceUser()) {
                this.mRltEInvoiceUser.setVisibility(View.GONE);
            } else {
                this.mRltEInvoiceUser.setVisibility(View.VISIBLE);
            }
        }
        if (customer.getImage() != null) {
            this.mCustomerImage.setImageBitmap(customer.getImage());
        }
        if (customer.getHasDailyOperation()) {
            this.mCustomerCode.setTextColor(this.mCustomerWithDailyOperationResId);
            this.mCustomerName.setTextColor(this.mCustomerWithDailyOperationResId);
            this.mCustomerSecondTitle.setTextColor(this.mCustomerWithDailyOperationResId);
        } else {
            this.mCustomerCode.setTextColor(this.mDefaultTextColor);
            this.mCustomerName.setTextColor(this.mDefaultTextColor);
            this.mCustomerSecondTitle.setTextColor(this.mDefaultTextColor);
        }
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(CustGpsInfo.class, "CLCODE=?", new String[]{customer.getCode()});
        if (table != null && !table.isEmpty()) {
            this.mCustomerLocation.setImageResource(R.drawable.ic_map_marker_36dp_red);
        } else {
            this.mCustomerLocation.setImageResource(R.drawable.ic_map_marker_36dp_grey);
        }
    }

    public void reset() {
        this.mCustomerName.setText(this.mLoadingText);
        this.mCustomerCode.setText(this.mLoadingText);
        this.mCustomerDebit.setText(this.mLoadingText);
        this.mCustomerCredit.setText(this.mLoadingText);
        this.mCustomerBalance.setText(this.mLoadingText);
        this.mCustomerImage.setImageDrawable(this.mDefaultCustomerImage);
        if (this.mRltCustomerDetail.getVisibility() == View.VISIBLE) {
            this.mCustomerPerson.setText(this.mLoadingText);
            this.mCustomerTel.setText(this.mLoadingText);
            this.mCustomerLastOrderDate.setText(this.mLoadingText);
            this.mCustomerPayPlan.setText(this.mLoadingText);
            this.mCustomerAverage.setText(this.mLoadingText);
            this.mCustomerPerson.setText(this.mLoadingText);
            this.mCustomerTel.setText(this.mLoadingText);
            this.mCustomerEmail.setText(this.mLoadingText);
            this.mCustomerInfoNot.setText(this.mLoadingText);
        }
    }

    private String cat(String str, String str2) {
        if (str2 == null || TextUtils.isEmpty(str2)) {
            return str + " " + this.mEmptyText;
        }
        return str + " " + str2;
    }

    public void setOnEmailClickListener(OnClickListener onClickListener) {
        this.mCustomerEmail.setOnClickListener(onClickListener);
    }

    public void setOnTelClickListener(OnClickListener onClickListener) {
        this.mCustomerTel.setOnClickListener(onClickListener);
    }

    public void setOnMapClickListener(OnClickListener onClickListener) {
        this.mCustomerLocation.setOnClickListener(onClickListener);
    }

    public void setOnImageClickListener(OnClickListener onClickListener) {
        this.mCustomerImage.setOnClickListener(onClickListener);
    }

    public void setOnCustomerInfoNotListener(OnClickListener onClickListener) {
        this.mCustomerInfoNot.setOnClickListener(onClickListener);
    }
    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
