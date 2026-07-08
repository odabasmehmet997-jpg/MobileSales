package com.proje.mobilesales.features.penetration.view.detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.penetration.model.PenetrationLine;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;
 
public final class PenetrationLineView extends RelativeLayout {
    private final EditText edtProductAmount;
    private final ImageButton imgBtnPenetrationCam;
    private final ImageButton imgBtnPenetrationNot;
    private final ImageButton imgBtnProductAmountMinus;
    private final ImageButton imgBtnProductAmountPlus;
    private final int mBackgroundColor;
    private final boolean mChecked = false;
    private final String mEmptyText;
    private final String mExistText;
    private final int mHighlightColor;
    private final String mLoadingText;
    private final String mNotText;
    private final PenetrationLineAmountClickListener mPenetrationAmountMinusClickListener;
    private final PenetrationLineAmountClickListener mPenetrationAmountPlusClickListener;
    private final PenetrationLineAmountTextChangeListener mPenetrationAmountTextChangeListener;
    private final PenetraitonLineCheckedListener mPenetrationCheckedListener;
    private final TextView mPenetrationCode;
    private final TextView mPenetrationName;
    private final AppCompatEditText mPenetrationPrice;
    private final PenetrationLinePriceTextChangeListener mPriceChangedListener;
    private final Switch mProductSwitch;
    private final int mPromotedColorResId;
    private final int mSecondaryTextColorResId;
    private final int mTertiaryTextColorResId;
    private final RelativeLayout rlt_productOperationAmount;
 
    public PenetrationLineView(Context context) {
        this(context, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
    } 
    public PenetrationLineView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{16843282, 16842808, R.attr.colorCardBackground, R.attr.colorCardHighlight});
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        this.mTertiaryTextColorResId = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(0, 0));
        this.mSecondaryTextColorResId = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(1, 0));
        int color = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(2, 0));
        this.mBackgroundColor = color;
        this.mHighlightColor = ContextCompat.getColor(context, obtainStyledAttributes.getResourceId(3, 0));
        this.mPromotedColorResId = ContextCompat.getColor(context, R.color.greenA700);
        View.inflate(context, R.layout.penetration_line_view, this);
        setBackgroundColor(color);
        View findViewById = findViewById(R.id.txt_productName);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.mPenetrationName = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.txt_productCode);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.mPenetrationCode = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.et_productPrice);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        AppCompatEditText appCompatEditText = (AppCompatEditText) findViewById3;
        this.mPenetrationPrice = appCompatEditText;
        View findViewById4 = findViewById(R.id.mProductSwitch);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        Switch r2 = (Switch) findViewById4;
        this.mProductSwitch = r2;
        View findViewById5 = findViewById(R.id.rlt_productOperationAmount);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.rlt_productOperationAmount = (RelativeLayout) findViewById5;
        View findViewById6 = findViewById(R.id.imgBtn_product_minus);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        ImageButton imageButton = (ImageButton) findViewById6;
        this.imgBtnProductAmountMinus = imageButton;
        View findViewById7 = findViewById(R.id.edt_product_amount);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        EditText editText = (EditText) findViewById7;
        this.edtProductAmount = editText;
        PenetrationLineAmountTextChangeListener penetrationLineAmountTextChangeListener = new PenetrationLineAmountTextChangeListener();
        this.mPenetrationAmountTextChangeListener = penetrationLineAmountTextChangeListener;
        editText.addTextChangedListener(penetrationLineAmountTextChangeListener);
        View findViewById8 = findViewById(R.id.imgBtn_product_plus);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        ImageButton imageButton2 = (ImageButton) findViewById8;
        this.imgBtnProductAmountPlus = imageButton2;
        PenetrationLineAmountClickListener penetrationLineAmountClickListener = new PenetrationLineAmountClickListener(true);
        this.mPenetrationAmountPlusClickListener = penetrationLineAmountClickListener;
        imageButton2.setOnClickListener(penetrationLineAmountClickListener);
        PenetrationLineAmountClickListener penetrationLineAmountClickListener2 = new PenetrationLineAmountClickListener(false);
        this.mPenetrationAmountMinusClickListener = penetrationLineAmountClickListener2;
        imageButton.setOnClickListener(penetrationLineAmountClickListener2);
        PenetraitonLineCheckedListener penetraitonLineCheckedListener = new PenetraitonLineCheckedListener();
        this.mPenetrationCheckedListener = penetraitonLineCheckedListener;
        r2.setOnCheckedChangeListener(penetraitonLineCheckedListener);
        PenetrationLinePriceTextChangeListener penetrationLinePriceTextChangeListener = new PenetrationLinePriceTextChangeListener();
        this.mPriceChangedListener = penetrationLinePriceTextChangeListener;
        appCompatEditText.addTextChangedListener(penetrationLinePriceTextChangeListener);
        View findViewById9 = findViewById(R.id.imgBtn_penetration_cam);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.imgBtnPenetrationCam = (ImageButton) findViewById9;
        View findViewById10 = findViewById(R.id.imgBtn_penetration_not);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
        this.imgBtnPenetrationNot = (ImageButton) findViewById10;
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
        this.mNotText = string4;
        obtainStyledAttributes.recycle();
    }

    public ImageButton getImgBtnPenetrationCam() {
        return this.imgBtnPenetrationCam;
    }

    public ImageButton getImgBtnPenetrationNot() {
        return this.imgBtnPenetrationNot;
    }
    public PenetrationLineView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public  PenetrationLineView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public void setPenetrationLine(PenetrationLine penetrationLine, boolean z, FicheMode ficheMode) {
        Intrinsics.checkNotNullParameter(penetrationLine, "penetrationLine");
        Intrinsics.checkNotNullParameter(ficheMode, "ficheMode");
        this.mPenetrationName.setText(penetrationLine.getProductName());
        this.mPenetrationCode.setText(penetrationLine.getProductCode());
        this.mPenetrationPrice.setText(checkString(String.valueOf(penetrationLine.getPrice())));
        if (z) {
            this.mProductSwitch.setVisibility(View.VISIBLE);
            Switch r5 = this.mProductSwitch;
            FicheBooleanProp exist = penetrationLine.getExist();
            Intrinsics.checkNotNull(exist);
            r5.setChecked(exist.isSelect());
            this.rlt_productOperationAmount.setVisibility(View.GONE);
        } else {
            this.edtProductAmount.setText(checkString(String.valueOf(penetrationLine.getAmount())));
            this.rlt_productOperationAmount.setVisibility(View.VISIBLE);
            this.mProductSwitch.setVisibility(View.GONE);
        }
        if (ficheMode != FicheMode.ANALYSE) {
            this.mPenetrationAmountPlusClickListener.changePenetration(penetrationLine);
            this.mPenetrationAmountMinusClickListener.changePenetration(penetrationLine);
            this.mPenetrationCheckedListener.changePenetration(penetrationLine);
            this.mPriceChangedListener.changePenetration(penetrationLine);
            this.mPenetrationAmountTextChangeListener.changePenetration(penetrationLine);
        } else {
            setViewActive(this.mPenetrationPrice, false);
            setViewActive(this.mProductSwitch, false);
            setViewActive(this.edtProductAmount, false);
            setViewActive(this.imgBtnProductAmountMinus, false);
            setViewActive(this.imgBtnProductAmountPlus, false);
        }
        setViewActive(this.mPenetrationPrice, penetrationLine.isPriceActive());
    }

    private void setViewActive(View view, boolean z) {
        view.setFocusable(z);
        view.setClickable(z);
    }

    private String getPenetrationText(PenetrationLine penetrationLine, boolean z) {
        String str;
        PrimitiveCompanionObjects stringCompanionObject = PrimitiveCompanionObjects.INSTANCE;
        if (z) {
            FicheBooleanProp exist = penetrationLine.getExist();
            Intrinsics.checkNotNull(exist);
            str = exist.isSelect() ? this.mExistText : this.mNotText;
        } else {
            str = checkString(String.valueOf(penetrationLine.getAmount()));
        }
        String checkString = checkString(penetrationLine.getUnit());
        String checkString2 = checkString(String.valueOf(penetrationLine.getPrice()));
        FicheDiscountRefProp currency = penetrationLine.getCurrency();
        Intrinsics.checkNotNull(currency);
        String format = String.format("%s %s %s %s", Arrays.copyOf(new Object[]{str, checkString, checkString2, checkString(currency.getCode())}, 4));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    private String checkString(String str) {
        return !TextUtils.isEmpty(str) ? str : "";
    }

    public void reset() {
        this.mProductSwitch.setChecked(false);
        this.mPenetrationName.setText("");
        this.mPenetrationCode.setText("");
        this.mPenetrationPrice.setText("");
        this.edtProductAmount.setText("");
    }

    private final class PenetrationLinePriceTextChangeListener implements TextWatcher {
        private boolean isReady;
        private PenetrationLine mPenetrationLine;
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            Intrinsics.checkNotNullParameter(charSequence, "s");
        }
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            Intrinsics.checkNotNullParameter(charSequence, "s");
        }
        public PenetrationLinePriceTextChangeListener() {
        }

        public void changePenetration(PenetrationLine penetrationLine) {
            this.mPenetrationLine = penetrationLine;
            this.isReady = true;
        }
        public void afterTextChanged(Editable editable) {
            Intrinsics.checkNotNullParameter(editable, "s");
            if (this.isReady) {
                PenetrationLine penetrationLine = this.mPenetrationLine;
                Intrinsics.checkNotNull(penetrationLine);
                penetrationLine.setPrice(new FicheStringProp(String.valueOf(PenetrationLineView.this.mPenetrationPrice.getText())));
            }
        }
    }
    private final class PenetraitonLineCheckedListener implements CompoundButton.OnCheckedChangeListener {
        private boolean isReady;
        private PenetrationLine mPenetrationLine;

        public PenetraitonLineCheckedListener() {
        }

        public void changePenetration(PenetrationLine penetrationLine) {
            this.mPenetrationLine = penetrationLine;
            this.isReady = true;
        }
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            Intrinsics.checkNotNullParameter(compoundButton, "buttonView");
            if (this.isReady) {
                PenetrationLine penetrationLine = this.mPenetrationLine;
                Intrinsics.checkNotNull(penetrationLine);
                FicheBooleanProp exist = penetrationLine.getExist();
                Intrinsics.checkNotNull(exist);
                exist.setSelect(z);
            }
        }
    }
    private final class PenetrationLineAmountClickListener implements OnClickListener {
        private PenetrationLine mPenetrationLine;
        private final boolean mPlus;

        public PenetrationLineAmountClickListener(boolean z) {
            this.mPlus = z;
        }

        public void changePenetration(PenetrationLine penetrationLine) {
            this.mPenetrationLine = penetrationLine;
        }
        public void onClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            PenetrationLine penetrationLine = this.mPenetrationLine;
            if (penetrationLine != null) {
                boolean z = this.mPlus;
                Intrinsics.checkNotNull(penetrationLine);
                if (z) {
                    penetrationLine.plusAmount();
                } else {
                    penetrationLine.minusAmount();
                }
                EditText editText = PenetrationLineView.this.edtProductAmount;
                PenetrationLine penetrationLine2 = this.mPenetrationLine;
                Intrinsics.checkNotNull(penetrationLine2);
                editText.setText(String.valueOf(penetrationLine2.getAmount()));
            }
        }
    }
    private final class PenetrationLineAmountTextChangeListener implements TextWatcher {
        private boolean isReady;
        private PenetrationLine mPenetrationLine;

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            Intrinsics.checkNotNullParameter(charSequence, "s");
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            Intrinsics.checkNotNullParameter(charSequence, "s");
        }

        public PenetrationLineAmountTextChangeListener() {
        }

        public void changePenetration(PenetrationLine penetrationLine) {
            this.mPenetrationLine = penetrationLine;
            this.isReady = true;
        } 
        public void afterTextChanged(Editable editable) {
            Intrinsics.checkNotNullParameter(editable, "s");
            if (this.isReady) {
                PenetrationLine penetrationLine = this.mPenetrationLine;
                Intrinsics.checkNotNull(penetrationLine);
                penetrationLine.setAmount(new FicheStringProp(PenetrationLineView.this.edtProductAmount.getText().toString()));
            }
        }
    }
}
