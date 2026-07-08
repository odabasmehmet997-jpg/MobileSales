package com.proje.mobilesales.features.userreport.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.reportparser.*;
import com.proje.mobilesales.core.view.ScreenControl;
import com.proje.mobilesales.features.model.GenericData;
import com.proje.mobilesales.features.model.GenericDataPrimitive;
import com.proje.mobilesales.features.reports.model.enums.ReportLayoutItemType;
import com.proje.mobilesales.features.reports.util.ReportUtil;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsFormActivity;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static android.text.TextUtils.TruncateAt;
import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;
import static com.proje.mobilesales.features.userreport.view.activity.UserReportsActivity.WhenMappings.EnumSwitchMapping0;

public final class UserReportsFormActivityRecyclerViewAdapter extends RecyclerView.Adapter<UserReportsFormActivityRecyclerViewAdapter.ItemViewHolder> {
    public static final Companion Companion = new Companion(null);
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    private float density;
    private int displayX;
    private GradientDrawable drawable;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private DisplayMetrics metrics;
    private int rotation;
    private final ScreenControl screenControl = null;
    private ArrayList<GenericData> mDatas = new ArrayList<>();
    private ReportLayoutViewCard mCardView = new ReportLayoutViewCard();
    public class WhenMappings {
    public final int[] EnumSwitchMapping0;
      {
            int[] iArr = new int[ReportLayoutItemType.values().length];
            try {
                iArr[ReportLayoutItemType.Column.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReportLayoutItemType.TabbedGroup.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReportLayoutItemType.LayoutGroup.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ReportLayoutItemType.Label.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ReportLayoutItemType.Row.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            EnumSwitchMapping0 = iArr;
        }
    }
    public GradientDrawable getDrawable() {
        return this.drawable;
    }
    public void setDrawable(GradientDrawable gradientDrawable) {
        this.drawable = gradientDrawable;
    }
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        this.mContext = context;
        this.mLayoutInflater = createLayoutInflater(context);
        Context context2 = this.mContext;
        Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type com.proje.mobilesales.features.userreport.view.activity.UserReportsFormActivity");
        Display defaultDisplay = ((UserReportsFormActivity) context2).getWindowManager().getDefaultDisplay();
        this.metrics = new DisplayMetrics();
        Context context3 = this.mContext;
        Intrinsics.checkNotNull(context3, "null cannot be cast to non-null type com.proje.mobilesales.features.userreport.view.activity.UserReportsFormActivity");
        ((UserReportsFormActivity) context3).getWindowManager().getDefaultDisplay().getMetrics(this.metrics);
        DisplayMetrics displayMetrics = this.metrics;
        Intrinsics.checkNotNull(displayMetrics);
        this.displayX = displayMetrics.widthPixels;
        DisplayMetrics displayMetrics2 = this.metrics;
        Intrinsics.checkNotNull(displayMetrics2);
        this.density = displayMetrics2.density;
        this.rotation = defaultDisplay.getRotation();
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.drawable = gradientDrawable;
        Intrinsics.checkNotNull(gradientDrawable);
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        GradientDrawable gradientDrawable2 = this.drawable;
        Intrinsics.checkNotNull(gradientDrawable2);
        gradientDrawable2.setStroke(1, ViewCompat.MEASURED_STATE_MASK);
    }
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
        }
        super.onDetachedFromRecyclerView(recyclerView);
        this.mContext = null;
    }
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflater = this.mLayoutInflater;
        Intrinsics.checkNotNull(layoutInflater);
        View inflate = layoutInflater.inflate(R.layout.item_report_forms, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ItemViewHolder(inflate);
    }
    public void onBindViewHolder(ItemViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        bindData(holder, getProperty(i2), i2);
    }
    private void createTextView(String str, float f2, ViewGroup viewGroup, boolean z, ReportAppearanceItemCaption reportAppearanceItemCaption) {
        TextView textView = new TextView(this.mContext);
        textView.setText(str);
        textView.setSingleLine(true);
        textView.setEllipsize(TruncateAt.END);
        textView.setMaxLines(1);
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, -2, f2));
        if (z) {
            textView.setPadding(0, 0, 20, 0);
        }
        if (reportAppearanceItemCaption != null) {
            if (reportAppearanceItemCaption.isBold() && reportAppearanceItemCaption.isItalic()) {
                textView.setTypeface(null, Typeface.BOLD_ITALIC);
            } else if (reportAppearanceItemCaption.isBold()) {
                textView.setTypeface(null, Typeface.BOLD);
            } else if (reportAppearanceItemCaption.isItalic()) {
                textView.setTypeface(null, Typeface.ITALIC);
            }
            String foreColor = reportAppearanceItemCaption.getForeColor();
            Intrinsics.checkNotNullExpressionValue(foreColor, "getForeColor(...)");
            if (foreColor.length() > 0) {
                try {
                    textView.setTextColor(Color.parseColor(reportAppearanceItemCaption.getForeColor()));
                } catch (Exception e2) {
                    Log.d("UserFromReportSetColor", "Unknown color error : " + reportAppearanceItemCaption.getForeColor() + ' ' + e2.getMessage());
                }
            }
            if (reportAppearanceItemCaption.isUnderline()) {
                textView.setPaintFlags(textView.getPaintFlags() | 8);
            }
            double fontSize = reportAppearanceItemCaption.getFontSize();
            if (0.0d <= fontSize && fontSize <= 8.0d) {
                textView.setTextSize(10.0f);
            } else {
                double fontSize2 = reportAppearanceItemCaption.getFontSize();
                if (9.0d <= fontSize2 && fontSize2 <= 12.0d) {
                    textView.setTextSize(14.0f);
                } else {
                    double fontSize3 = reportAppearanceItemCaption.getFontSize();
                    if (13.0d <= fontSize3 && fontSize3 <= 17.0d) {
                        textView.setTextSize(18.0f);
                    } else if (reportAppearanceItemCaption.getFontSize() >= 18.0f) {
                        textView.setTextSize(22.0f);
                    }
                }
            }
        }
        viewGroup.addView(textView);
    }
    private String getValueFromData(GenericData genericData, ReportColumn reportColumn) {
        for (GenericDataPrimitive genericDataPrimitive : genericData.getGenericDataPrimitives()) {
            if (Intrinsics.areEqual(genericDataPrimitive.getName(), reportColumn.getFieldName())) {
                return ReportUtil.formatReportColumnValue(reportColumn, genericDataPrimitive.getValue().toString());
            }
        }
        return "";
    }
    private void createViewFromTemplate(ItemViewHolder itemViewHolder, ViewGroup viewGroup, ReportLayoutItem reportLayoutItem, GenericData genericData, int i2) {
        if (reportLayoutItem.getItemType() != null) {
            ReportLayoutItemType itemType = reportLayoutItem.getItemType();
            int i3;
            if (itemType == null) i3 = -1;
            else i3 = EnumSwitchMapping0[itemType.ordinal()];
            if (i3 == 1) {
                Intrinsics.checkNotNull(reportLayoutItem, "null cannot be cast to non-null type com.proje.mobilesales.core.reportparser.ReportLayoutColumn");
                ReportLayoutColumn reportLayoutColumn = (ReportLayoutColumn) reportLayoutItem;
                if (reportLayoutColumn.getReportColumn().isVisible()) {
                    ViewGroup linearLayout = new LinearLayout(this.mContext);
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(0, -2, reportLayoutColumn.getWeight()));
                    linearLayout.setPadding(10, 0, 0, 0);
                    if (reportLayoutColumn.isFormTextVisible()) {
                        createTextView(reportLayoutColumn.getFormText(), reportLayoutColumn.getFormTextWeight(), linearLayout, true, reportLayoutColumn.getTextAppearanceItemCaption());
                        ReportColumn reportColumn = reportLayoutColumn.getReportColumn();
                        Intrinsics.checkNotNullExpressionValue(reportColumn, "getReportColumn(...)");
                        createTextView(getValueFromData(genericData, reportColumn), reportLayoutColumn.getFormValueWeight(), linearLayout, false, reportLayoutColumn.getValueAppearanceItemCaption());
                    } else {
                        ReportColumn reportColumn2 = reportLayoutColumn.getReportColumn();
                        Intrinsics.checkNotNullExpressionValue(reportColumn2, "getReportColumn(...)");
                        createTextView(getValueFromData(genericData, reportColumn2), 1.0f, linearLayout, false, reportLayoutColumn.getValueAppearanceItemCaption());
                    }
                    Intrinsics.checkNotNull(viewGroup);
                    viewGroup.addView(linearLayout);
                    return;
                }
                return;
            }
            if (i3 == 2) {
                Intrinsics.checkNotNull(reportLayoutItem, "null cannot be cast to non-null type com.proje.mobilesales.core.reportparser.ReportLayoutTabbedGroup");
                LinearLayout linearLayout2 = new LinearLayout(this.mContext);
                linearLayout2.setOrientation(LinearLayout.VERTICAL);
                ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                linearLayout2.setLayoutParams(layoutParams);
                Intrinsics.checkNotNull(viewGroup);
                viewGroup.addView(linearLayout2);
                linearLayout2.setPadding(10, 10, 10, 10);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, 80, 1.0f);
                LinearLayout linearLayout3 = new LinearLayout(this.mContext);
                linearLayout3.setLayoutParams(layoutParams);
                linearLayout3.setPadding(0, 10, 0, 0);
                linearLayout3.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout3.setWeightSum(0.0f);
                linearLayout2.addView(linearLayout3);
                HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this.mContext);
                horizontalScrollView.setBackground(this.drawable);
                linearLayout2.addView(horizontalScrollView);
                ViewGroup linearLayout4 = new LinearLayout(this.mContext);
                linearLayout4.setPadding(0, 10, 0, 10);
                linearLayout4.setLayoutParams(layoutParams);
                horizontalScrollView.addView(linearLayout4);
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setShape(GradientDrawable.RECTANGLE);
                gradientDrawable.setStroke(1, ViewCompat.MEASURED_STATE_MASK);
                gradientDrawable.setColor(Color.parseColor("#F0F2F3"));
                for (int i4 = 0; i4 < reportLayoutItem.getChildItems().size(); i4++) {
                    if (reportLayoutItem.getChildItems().get(i4).getItemType() == ReportLayoutItemType.Row) {
                        for (int i5 = 0; i5 < reportLayoutItem.getChildItems().get(i4).getChildItems().size(); i5++) {
                            Button button = new Button(this.mContext);
                            button.setText(reportLayoutItem.getChildItems().get(i4).getChildItems().get(i5).getFormText());
                            button.setLayoutParams(layoutParams2);
                            button.setTextSize(10.0f);
                            Object[] objArr = {horizontalScrollView, Integer.valueOf(i5)};
                            button.setTag(objArr);
                            button.setBackground(gradientDrawable);
                            button.setOnClickListener(new View.OnClickListener() { 
                                public final Object[] f0 = new Object[0];
 
                                public void onClick(View view) {
                                    Object[] r1 = new Object[0];
                                    UserReportsFormActivityRecyclerViewAdapter.createViewFromTemplatelambda0(r1, view);
                                }
                            });
                            linearLayout3.addView(button);
                            linearLayout3.setWeightSum(linearLayout3.getWeightSum() + 1);
                        }
                    }
                }
                for (int i6 = 0; i6 < reportLayoutItem.getChildItems().size(); i6++) {
                    ReportLayoutItem reportLayoutItem2 = reportLayoutItem.getChildItems().get(i6);
                    Intrinsics.checkNotNullExpressionValue(reportLayoutItem2, "get(...)");
                    createViewFromTemplate(itemViewHolder, linearLayout4, reportLayoutItem2, genericData, i2);
                }
                return;
            }
            if (i3 != 3) {
                if (i3 == 4) {
                    Intrinsics.checkNotNull(reportLayoutItem, "null cannot be cast to non-null type com.proje.mobilesales.core.reportparser.ReportLayoutDefaultItem");
                    ReportLayoutDefaultItem reportLayoutDefaultItem = (ReportLayoutDefaultItem) reportLayoutItem;
                    ViewGroup linearLayout5 = new LinearLayout(this.mContext);
                    linearLayout5.setLayoutParams(new LinearLayout.LayoutParams(0, -2, reportLayoutDefaultItem.getWeight()));
                    linearLayout5.setPadding(10, 0, 0, 0);
                    createTextView(reportLayoutDefaultItem.getFormText(), 1.0f, linearLayout5, false, reportLayoutDefaultItem.getTextAppearanceItemCaption());
                    Intrinsics.checkNotNull(viewGroup);
                    viewGroup.addView(linearLayout5);
                    return;
                }
                if (i3 != 5) {
                    return;
                }
                if (viewGroup != null && Intrinsics.areEqual(viewGroup.getParent().getClass(), HorizontalScrollView.class)) {
                    for (int i7 = 0; i7 < reportLayoutItem.getChildItems().size(); i7++) {
                        ReportLayoutItem reportLayoutItem3 = reportLayoutItem.getChildItems().get(i7);
                        Intrinsics.checkNotNullExpressionValue(reportLayoutItem3, "get(...)");
                        createViewFromTemplate(itemViewHolder, viewGroup, reportLayoutItem3, genericData, i2);
                    }
                    return;
                }
                LinearLayout linearLayout6 = new LinearLayout(this.mContext);
                linearLayout6.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                linearLayout6.setOrientation(LinearLayout.HORIZONTAL);
                if (viewGroup == null) {
                    itemViewHolder.getLnForm().addView(linearLayout6);
                } else {
                    viewGroup.addView(linearLayout6);
                }
                for (int i8 = 0; i8 < reportLayoutItem.getChildItems().size(); i8++) {
                    ReportLayoutItem reportLayoutItem4 = reportLayoutItem.getChildItems().get(i8);
                    Intrinsics.checkNotNullExpressionValue(reportLayoutItem4, "get(...)");
                    createViewFromTemplate(itemViewHolder, linearLayout6, reportLayoutItem4, genericData, i2);
                }
                return;
            }
            Intrinsics.checkNotNull(reportLayoutItem, "null cannot be cast to non-null type com.proje.mobilesales.core.reportparser.ReportLayoutGroup");
            ReportLayoutGroup reportLayoutGroup = (ReportLayoutGroup) reportLayoutItem;
            if (!reportLayoutGroup.isTabGroup()) {
                LinearLayout linearLayout7 = new LinearLayout(this.mContext);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams3.setMargins(20, 10, 20, 0);
                linearLayout7.setLayoutParams(layoutParams3);
                linearLayout7.setOrientation(LinearLayout.VERTICAL);
                linearLayout7.setBackground(this.drawable);
                TextView textView = new TextView(this.mContext);
                textView.setText(reportLayoutGroup.getFormText());
                textView.setTypeface(null, Typeface.BOLD);
                textView.setPadding(10, 0, 0, 0);
                linearLayout7.addView(textView);
                Intrinsics.checkNotNull(viewGroup);
                viewGroup.addView(linearLayout7);
                for (int i9 = 0; i9 < reportLayoutItem.getChildItems().size(); i9++) {
                    ReportLayoutItem reportLayoutItem5 = reportLayoutItem.getChildItems().get(i9);
                    Intrinsics.checkNotNullExpressionValue(reportLayoutItem5, "get(...)");
                    createViewFromTemplate(itemViewHolder, linearLayout7, reportLayoutItem5, genericData, i2);
                }
                return;
            }
            LinearLayout linearLayout8 = new LinearLayout(this.mContext);
            linearLayout8.setTag(Integer.valueOf(i2));
            Object tag = linearLayout8.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type kotlin.Int");
            linearLayout8.setOnLongClickListener(getLongClickListener(((Integer) tag).intValue()));
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -1);
            Intrinsics.checkNotNull(viewGroup);
            layoutParams4.setMargins(viewGroup.getChildCount() == 0 ? 0 : 15, 0, 0, 0);
            linearLayout8.setLayoutParams(layoutParams4);
            LinearLayout linearLayout9 = new LinearLayout(this.mContext);
            linearLayout9.setOrientation(LinearLayout.VERTICAL);
            linearLayout9.setLayoutParams(layoutParams4);
            linearLayout9.setPadding(20, 0, 0, 0);
            linearLayout8.addView(linearLayout9);
            viewGroup.addView(linearLayout8);
            linearLayout8.setMinimumWidth(this.displayX);
            for (int i10 = 0; i10 < reportLayoutItem.getChildItems().size(); i10++) {
                ReportLayoutItem reportLayoutItem6 = reportLayoutItem.getChildItems().get(i10);
                Intrinsics.checkNotNullExpressionValue(reportLayoutItem6, "get(...)");
                createViewFromTemplate(itemViewHolder, linearLayout9, reportLayoutItem6, genericData, i2);
            }
            return;
        }
        if (reportLayoutItem.getChildItems() != null) {
            List<ReportLayoutItem> childItems = reportLayoutItem.getChildItems();
            Intrinsics.checkNotNullExpressionValue(childItems, "getChildItems(...)");
            if (childItems.isEmpty()) {
                return;
            }
            int size = reportLayoutItem.getChildItems().size();
            for (int i11 = 0; i11 < size; i11++) {
                ReportLayoutItem reportLayoutItem7 = reportLayoutItem.getChildItems().get(i11);
                Intrinsics.checkNotNullExpressionValue(reportLayoutItem7, "get(...)");
                createViewFromTemplate(itemViewHolder, null, reportLayoutItem7, genericData, i2);
            }
        }
    }
    public static void createViewFromTemplatelambda0(Object[] sub, View view) {
        Intrinsics.checkNotNullParameter(sub, "sub");
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) sub[0];
        Intrinsics.checkNotNull(horizontalScrollView);
        View childAt = horizontalScrollView.getChildAt(0);
        Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type android.widget.LinearLayout");
        Object obj = sub[1];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
        View childAt2 = ((LinearLayout) childAt).getChildAt(((Integer) obj).intValue());
        Intrinsics.checkNotNull(childAt2, "null cannot be cast to non-null type android.widget.LinearLayout");
        horizontalScrollView.scrollTo(childAt2.getLeft(), 0);
    }
    private View.OnLongClickListener getLongClickListener(int i2) {
        return new View.OnLongClickListener() { 
            public int f1 = 0;

            public void ViewOnLongClickListenerC2921x721eba14(int i22) {
                f1 = i22;
            } 
            public boolean onLongClick(View view) {
                boolean longClickListenerlambda1;
                int r2 = 0;
                longClickListenerlambda1 = UserReportsFormActivityRecyclerViewAdapter.getLongClickListenerlambda1(UserReportsFormActivityRecyclerViewAdapter.this, r2, view);
                return longClickListenerlambda1;
            }
        };
    }
    public static boolean getLongClickListenerlambda1(UserReportsFormActivityRecyclerViewAdapter this0, int i2, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Context context = this0.mContext;
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.features.userreport.view.activity.UserReportsFormActivity");
        ((UserReportsFormActivity) context).getReportDetail(i2);
        return false;
    }
    public void bindData(ItemViewHolder holder, GenericData data, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(data, "data");
        holder.getLnForm().setHasTransientState(true);
        holder.getLnForm().setTag(Integer.valueOf(i2));
        LinearLayout lnForm = holder.getLnForm();
        Object tag = holder.getLnForm().getTag();
        Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type kotlin.Int");
        lnForm.setOnLongClickListener(getLongClickListener(((Integer) tag).intValue()));
        createViewFromTemplate(holder, null, this.mCardView, data, i2);
    }
    public GenericData getProperty(int i2) {
        GenericData genericData = this.mDatas.get(i2);
        Intrinsics.checkNotNullExpressionValue(genericData, "get(...)");
        return genericData;
    }
    public int getItemCount() {
        return this.mDatas.size();
    }
    public boolean isAttached() {
        return this.mContext != null;
    }
    public void initDisplayOptions(Context context) {
        if (isAttached()) {
            notifyDataSetChanged();
        }
    }
    public void setData(ArrayList<GenericData> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        this.mDatas = arrayList;
        notifyDataSetChanged();
    }
    public void setFormTemplate(ReportLayoutViewCard reportLayoutViewCard) {
        if (reportLayoutViewCard == null) {
            return;
        }
        this.mCardView = reportLayoutViewCard;
        notifyDataSetChanged();
    }
    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout lnForm;

        
        public ItemViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.ln_form);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.LinearLayout");
            this.lnForm = (LinearLayout) findViewById;
        }

        public LinearLayout getLnForm() {
            return this.lnForm;
        }
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
