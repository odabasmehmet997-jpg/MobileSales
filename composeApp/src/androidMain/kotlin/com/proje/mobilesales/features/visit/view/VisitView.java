package com.proje.mobilesales.features.visit.view;
import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.google.common.base.Strings;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.widget.TintableTextView;
import com.proje.mobilesales.features.visit.model.VisitInfoShort;
import kotlin.jvm.internal.Intrinsics;
public final class VisitView extends RelativeLayout implements Checkable {
  private int mBackgroundColor = 0;
  private final boolean mChecked = false;
  private int mHighlightColor = 0;
  private String mLoadingText = "";
  private View mMoreOption = null;
  private int mPromotedColorResId = 0;
  private RelativeLayout mRltVisitDetail = null;
  private int mSecondaryTextColorResId = 0;
  private int mTertiaryTextColorResId = 0;
  private TextView mVisitDate = null;
  private String mVisitDateText = "";
  private String mVisitNotText = "";
  private TextView mVisitNote = null;
  private TextView mVisitReason = null;
  private String mVisitReasonText = "";
  private TextView mVisitShipAddress = null;
  private TextView mVisitShipAddressExplanation = null;
  private TintableTextView mVisitTransfer = null;
  public VisitView(Context context) {
    this(context, null, 2, 1);
    Intrinsics.checkNotNullParameter(context, "context");
  }
  public boolean isChecked() {
    return false;
  }
  public void setChecked(boolean z) {
  }
  public void toggle() {
  }
  public VisitView(Context context, AttributeSet attributeSet, int i2) {
    super(context, attributeSet, i2);
    Intrinsics.checkNotNullParameter(context, "context");
    TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{R.attr.textColorTertiary, R.attr.textColorSecondary, com.proje.mobilesales.R.attr.colorCardBackground, com.proje.mobilesales.R.attr.colorCardHighlight});
    Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "obtainStyledAttributes(...)");
    this.mTertiaryTextColorResId = ContextCompat.getColor(context, typedArrayObtainStyledAttributes.getResourceId(0, 0));
    this.mSecondaryTextColorResId = ContextCompat.getColor(context, typedArrayObtainStyledAttributes.getResourceId(1, 0));
    int color = ContextCompat.getColor(context, typedArrayObtainStyledAttributes.getResourceId(2, 0));
    this.mBackgroundColor = color;
    this.mHighlightColor = ContextCompat.getColor(context, typedArrayObtainStyledAttributes.getResourceId(3, 0));
    this.mPromotedColorResId = ContextCompat.getColor(context, com.proje.mobilesales.R.color.greenA700);
    View.inflate(context, com.proje.mobilesales.R.layout.visit_view, this);
    setBackgroundColor(color);
    View viewFindViewById = findViewById(com.proje.mobilesales.R.id.txt_visit_ship_address_definition);
    Intrinsics.checkNotNull(viewFindViewById, "null cannot be cast to non-null type android.widget.TextView");
    this.mVisitShipAddress = (TextView) viewFindViewById;
    View viewFindViewById2 = findViewById(com.proje.mobilesales.R.id.txt_visit_ship_address_explanation);
    Intrinsics.checkNotNull(viewFindViewById2, "null cannot be cast to non-null type android.widget.TextView");
    this.mVisitShipAddressExplanation = (TextView) viewFindViewById2;
    View viewFindViewById3 = findViewById(com.proje.mobilesales.R.id.rlt_visit_detail);
    Intrinsics.checkNotNull(viewFindViewById3, "null cannot be cast to non-null type android.widget.RelativeLayout");
    this.mRltVisitDetail = (RelativeLayout) viewFindViewById3;
    View viewFindViewById4 = findViewById(com.proje.mobilesales.R.id.txt_visit_reason);
    Intrinsics.checkNotNull(viewFindViewById4, "null cannot be cast to non-null type android.widget.TextView");
    this.mVisitReason = (TextView) viewFindViewById4;
    View viewFindViewById5 = findViewById(com.proje.mobilesales.R.id.txt_visit_date);
    Intrinsics.checkNotNull(viewFindViewById5, "null cannot be cast to non-null type android.widget.TextView");
    this.mVisitDate = (TextView) viewFindViewById5;
    View viewFindViewById6 = findViewById(com.proje.mobilesales.R.id.txt_visit_note);
    Intrinsics.checkNotNull(viewFindViewById6, "null cannot be cast to non-null type android.widget.TextView");
    this.mVisitNote = (TextView) viewFindViewById6;
    View viewFindViewById7 = findViewById(com.proje.mobilesales.R.id.txt_visit_transfer);
    Intrinsics.checkNotNull(viewFindViewById7, "null cannot be cast to non-null type com.proje.mobilesales.core.widget.TintableTextView");
    this.mVisitTransfer = (TintableTextView) viewFindViewById7;
    View viewFindViewById8 = findViewById(com.proje.mobilesales.R.id.button_more);
    Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "findViewById(...)");
    this.mMoreOption = viewFindViewById8;
    String string = getContext().getString(com.proje.mobilesales.R.string.str_note);
    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
    this.mVisitNotText = string;
    String string2 = getContext().getString(com.proje.mobilesales.R.string.str_visit_date);
    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
    this.mVisitDateText = string2;
    String string3 = getContext().getString(com.proje.mobilesales.R.string.str_visit_reason);
    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
    this.mVisitReasonText = string3;
    String string4 = getContext().getString(com.proje.mobilesales.R.string.loading_text);
    Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
    this.mLoadingText = string4;
    typedArrayObtainStyledAttributes.recycle();
  }
  public VisitView(Context context, AttributeSet attributeSet) {
    this(context, attributeSet, 0);
    Intrinsics.checkNotNullParameter(context, "context");
  }
  public VisitView(Context context, AttributeSet attributeSet, int i2, int defaultConstructorMarker) {
    this(context, (i2 & 2) != 0 ? null : attributeSet);
  }
  public final void setVisit(VisitInfoShort visit) {
    Intrinsics.checkNotNullParameter(visit, "visit");
    this.mVisitShipAddress.setText(!Strings.isNullOrEmpty(visit.getShipAddress()) ? visit.getShipAddress() : visit.getClName());
    this.mVisitShipAddressExplanation.setText(!Strings.isNullOrEmpty(visit.getShipAddressExplanation()) ? visit.getShipAddressExplanation() : visit.getClCode());
    this.mVisitReason.setText(StringUtils.catStringColon(this.mVisitReasonText, visit.getVisitReason()));
    this.mVisitDate.setText(StringUtils.catStringColon(this.mVisitDateText, visit.getVisitDate()));
    this.mVisitNote.setText(StringUtils.catStringColon(this.mVisitNotText, visit.getVisitNote()));
    if (visit.getTransfer()) {
      this.mVisitTransfer.setVisibility(0);
    }
  }
  public final void reset() {
    this.mVisitReason.setText(this.mLoadingText);
    this.mVisitDate.setText(this.mLoadingText);
    this.mVisitNote.setText(this.mLoadingText);
    this.mVisitShipAddress.setText(this.mLoadingText);
    this.mVisitShipAddressExplanation.setText(this.mLoadingText);
    this.mVisitTransfer.setVisibility(8);
  }
  public final View getmMoreOption() {
    return this.mMoreOption;
  }
}
