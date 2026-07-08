package com.proje.mobilesales.features.penetration.view.list;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Checkable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.widget.TintableTextView;
import com.proje.mobilesales.features.penetration.model.database.PenetrationShort;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
 
public final class PenetrationView extends RelativeLayout implements Checkable {
    private final int mBackgroundColor;
    private boolean mChecked;
    private final String mEmptyText;
    private final int mHighlightColor;
    private final String mLoadingText;
    private final View mMoreOption;
    private final TextView mPenetrationDate;
    private final String mPenetrationDateText;
    private final TextView mPenetrationDefinition;
    private final int mPromotedColorResId;
    private final int mSecondaryTextColorResId;
    private final int mTertiaryTextColorResId;
    private final TintableTextView mTransfer;
 
    public PenetrationView(Context context) {
        this(context, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }
 
    public PenetrationView(Context context, AttributeSet attributeSet, int i) {
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
        View.inflate(context, R.layout.penetration_view, this);
        setBackgroundColor(color);
        View findViewById = findViewById(R.id.txt_penetrationDefinition);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        this.mPenetrationDefinition = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.txt_penetrationDate);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        this.mPenetrationDate = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.txt_penetrationTransfer);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type com.proje.mobilesales.core.widget.TintableTextView");
        this.mTransfer = (TintableTextView) findViewById3;
        View findViewById4 = findViewById(R.id.button_more);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.mMoreOption = findViewById4;
        String string = getContext().getString(R.string.str_penetration_date);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this.mPenetrationDateText = string;
        String string2 = getContext().getString(R.string.loading_text);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        this.mLoadingText = string2;
        String string3 = getContext().getString(R.string.empty_text);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        this.mEmptyText = string3;
        obtainStyledAttributes.recycle();
    }

    public PenetrationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public  PenetrationView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public void setPenetration(PenetrationShort penetrationShort) {
        Intrinsics.checkNotNullParameter(penetrationShort, "penetrationShort");
        this.mPenetrationDefinition.setText(penetrationShort.getPenetrationName());
        this.mPenetrationDate.setText(StringUtils.catStringColon(this.mPenetrationDateText, penetrationShort.getDate()));
        Log.d(MobileSales.TAG, "setPenetration: " + penetrationShort.isTransfer());
        if (penetrationShort.isTransfer()) {
            this.mTransfer.setVisibility(View.VISIBLE);
        }
    }

    public void reset() {
        this.mPenetrationDefinition.setText(this.mLoadingText);
        this.mPenetrationDate.setText(this.mLoadingText);
        this.mTransfer.setVisibility(View.GONE);
    }

    public View getmMoreOption() {
        return this.mMoreOption;
    }

    public void setChecked(boolean z) {
        this.mChecked = z;
        setBackgroundColor(z ? this.mHighlightColor : this.mBackgroundColor);
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    public void toggle() {
        this.mChecked = !this.mChecked;
    }
}
