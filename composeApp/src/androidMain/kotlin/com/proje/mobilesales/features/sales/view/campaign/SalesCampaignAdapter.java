package com.proje.mobilesales.features.sales.view.campaign;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.LineType;
import com.proje.mobilesales.features.sales.model.SalesCampaignDetail;
import java.util.List;
import java.util.Objects;

import kotlin.jvm.internal.Intrinsics;

public final class SalesCampaignAdapter extends BaseAdapter {
    private final Activity activity;
    private final List<SalesCampaignDetail> details;
    public Object getItem(int i2) {
        return null;
    }
    public long getItemId(int i2) {
        return 0L;
    }
    public SalesCampaignAdapter(List<SalesCampaignDetail> details, Activity activity) {
        Intrinsics.checkNotNullParameter(details, "details");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.details = details;
        this.activity = activity;
    }
    public int getCount() {
        return this.details.size();
    }
    public View getView(int i2, View view, ViewGroup viewGroup) {
        View view2;
        SalesCampaignViewHolder salesCampaignViewHolder;
        String str;
        SalesCampaignDetail salesCampaignDetail;
        SalesCampaignDetail salesCampaignDetail2;
        LayoutInflater layoutInflater = this.activity.getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "getLayoutInflater(...)");
        if (view == null) {
            salesCampaignViewHolder = new SalesCampaignViewHolder();
            view2 = layoutInflater.inflate(R.layout.show_campaign_row, null);
            View findViewById = view2.findViewById(R.id.tvType);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            salesCampaignViewHolder.setTvType((AppCompatTextView) findViewById);
            View findViewById2 = view2.findViewById(R.id.tvCode);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            salesCampaignViewHolder.setTvCode((AppCompatTextView) findViewById2);
            View findViewById3 = view2.findViewById(R.id.tvName);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            salesCampaignViewHolder.setTvName((AppCompatTextView) findViewById3);
            View findViewById4 = view2.findViewById(R.id.tvCampaignPoint);
            Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            salesCampaignViewHolder.setTvCampaignPoint((AppCompatTextView) findViewById4);
            View findViewById5 = view2.findViewById(R.id.tvQuantityUnit);
            Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            salesCampaignViewHolder.setTvQuantityUnit((AppCompatTextView) findViewById5);
            View findViewById6 = view2.findViewById(R.id.tvSurplusDiscount);
            Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            salesCampaignViewHolder.setTvSurplusDiscount((AppCompatTextView) findViewById6);
            View findViewById7 = view2.findViewById(R.id.tvSurplusQuantityUnit);
            Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            salesCampaignViewHolder.setTvSurplusQuantityUnit((AppCompatTextView) findViewById7);
            View findViewById8 = view2.findViewById(R.id.tvDiscountRate);
            Intrinsics.checkNotNull(findViewById8, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            salesCampaignViewHolder.setTvDiscountRate((AppCompatTextView) findViewById8);
            View findViewById9 = view2.findViewById(R.id.linearMaterial1);
            Intrinsics.checkNotNull(findViewById9, "null cannot be cast to non-null type android.widget.LinearLayout");
            salesCampaignViewHolder.setLinearMaterial1((LinearLayout) findViewById9);
            View findViewById10 = view2.findViewById(R.id.linearMaterial2);
            Intrinsics.checkNotNull(findViewById10, "null cannot be cast to non-null type android.widget.LinearLayout");
            salesCampaignViewHolder.setLinearMaterial2((LinearLayout) findViewById10);
            View findViewById11 = view2.findViewById(R.id.lnSurplus);
            Intrinsics.checkNotNullExpressionValue(findViewById11, "findViewById(...)");
            salesCampaignViewHolder.setLnSurplus((LinearLayout) findViewById11);
            View findViewById12 = view2.findViewById(R.id.lnSurplusDiscount);
            Intrinsics.checkNotNullExpressionValue(findViewById12, "findViewById(...)");
            salesCampaignViewHolder.setLnSurplusDiscount((LinearLayout) findViewById12);
            View findViewById13 = view2.findViewById(R.id.lnCampaignPoint);
            Intrinsics.checkNotNullExpressionValue(findViewById13, "findViewById(...)");
            salesCampaignViewHolder.setLnCampaignPoint((LinearLayout) findViewById13);
            View findViewById14 = view2.findViewById(R.id.lnDiscountRatio);
            Intrinsics.checkNotNullExpressionValue(findViewById14, "findViewById(...)");
            salesCampaignViewHolder.setLnDiscountRatio((LinearLayout) findViewById14);
            View findViewById15 = view2.findViewById(R.id.lnDiscount1);
            Intrinsics.checkNotNullExpressionValue(findViewById15, "findViewById(...)");
            salesCampaignViewHolder.setLnDiscount1((LinearLayout) findViewById15);
            View findViewById16 = view2.findViewById(R.id.lnDiscount2);
            Intrinsics.checkNotNullExpressionValue(findViewById16, "findViewById(...)");
            salesCampaignViewHolder.setLnDiscount2((LinearLayout) findViewById16);
            View findViewById17 = view2.findViewById(R.id.lnDiscount3);
            Intrinsics.checkNotNullExpressionValue(findViewById17, "findViewById(...)");
            salesCampaignViewHolder.setLnDiscount3((LinearLayout) findViewById17);
            View findViewById18 = view2.findViewById(R.id.tvDiscount1);
            Intrinsics.checkNotNull(findViewById18, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            salesCampaignViewHolder.setTvDiscount1((AppCompatTextView) findViewById18);
            View findViewById19 = view2.findViewById(R.id.tvDiscount2);
            Intrinsics.checkNotNull(findViewById19, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            salesCampaignViewHolder.setTvDiscount2((AppCompatTextView) findViewById19);
            View findViewById20 = view2.findViewById(R.id.tvDiscount3);
            Intrinsics.checkNotNull(findViewById20, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            salesCampaignViewHolder.setTvDiscount3((AppCompatTextView) findViewById20);
            view2.setTag(salesCampaignViewHolder);
        } else {
            Object tag = view.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.view.campaign.SalesCampaignViewHolder");
            SalesCampaignViewHolder salesCampaignViewHolder2 = (SalesCampaignViewHolder) tag;
            view2 = view;
            salesCampaignViewHolder = salesCampaignViewHolder2;
        }
        SalesCampaignDetail salesCampaignDetail28 = this.details.get(i2);
        Intrinsics.checkNotNull(salesCampaignDetail28);
        int type = salesCampaignDetail28.getType();
        LineType lineType = LineType.PRODUCT;
        if (type != lineType.value) {
            str = "";
        } else {
            str = this.activity.getString(lineType.getResId());
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        }
        Intrinsics.checkNotNull(salesCampaignDetail28);
        int type2 = salesCampaignDetail28.getType();
        LineType lineType2 = LineType.PROMOTION;
        if (type2 == lineType2.value) {
            str = this.activity.getString(lineType2.getResId());
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        }
        Intrinsics.checkNotNull(salesCampaignDetail28);
        int type3 = salesCampaignDetail28.getType();
        LineType lineType3 = LineType.DISCOUNT;
        if (type3 == lineType3.value) {
            str = this.activity.getString(lineType3.getResId());
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        }
        Objects.requireNonNull(salesCampaignViewHolder.getTvType()).setText(str);
        AppCompatTextView tvCode = salesCampaignViewHolder.getTvCode();
        Intrinsics.checkNotNull(salesCampaignDetail28);
        assert tvCode != null;
        tvCode.setText(salesCampaignDetail28.getCode());
        AppCompatTextView tvName = salesCampaignViewHolder.getTvName();
        Intrinsics.checkNotNull(salesCampaignDetail28);
        Objects.requireNonNull(tvName).setText(salesCampaignDetail28.getName());
        AppCompatTextView tvQuantityUnit = salesCampaignViewHolder.getTvQuantityUnit();
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkNotNull(salesCampaignDetail28);
        sb.append(salesCampaignDetail28.getQuantity());
        sb.append(" / ");
        Intrinsics.checkNotNull(salesCampaignDetail28);
        sb.append(salesCampaignDetail28.getUnit());
        assert tvQuantityUnit != null;
        tvQuantityUnit.setText(sb.toString());
        AppCompatTextView tvDiscountRate = salesCampaignViewHolder.getTvDiscountRate();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("% ");
        Intrinsics.checkNotNull(salesCampaignDetail28);
        sb2.append(salesCampaignDetail28.getDiscountRate());
        assert tvDiscountRate != null;
        tvDiscountRate.setText(sb2.toString());
        Intrinsics.checkNotNull(salesCampaignDetail28);
        if (salesCampaignDetail28.getCampaignPoint() > 0.0f) {
            AppCompatTextView tvCampaignPoint = salesCampaignViewHolder.getTvCampaignPoint();
            StringBuilder sb3 = new StringBuilder();
            sb3.append("+ ");
            Intrinsics.checkNotNull(salesCampaignDetail28);
            sb3.append(salesCampaignDetail28.getCampaignPoint());
            assert tvCampaignPoint != null;
            tvCampaignPoint.setText(sb3.toString());
        } else {
            Intrinsics.checkNotNull(salesCampaignDetail28);
            if (salesCampaignDetail28.getCampaignPoint() < 0.0f) {
                AppCompatTextView tvCampaignPoint2 = salesCampaignViewHolder.getTvCampaignPoint();
                StringBuilder sb4 = new StringBuilder();
                sb4.append("- ");
                Intrinsics.checkNotNull(salesCampaignDetail28);
                sb4.append(salesCampaignDetail28.getCampaignPoint());
                Objects.requireNonNull(tvCampaignPoint2).setText(sb4.toString());
            } else {
                Objects.requireNonNull(salesCampaignViewHolder.getTvCampaignPoint()).setText("0");
            }
        }
        AppCompatTextView tvSurplusQuantityUnit = salesCampaignViewHolder.getTvSurplusQuantityUnit();
        StringBuilder sb5 = new StringBuilder();
        Intrinsics.checkNotNull(salesCampaignDetail28);
        sb5.append(salesCampaignDetail28.getSurplus());
        sb5.append(" / ");
        Intrinsics.checkNotNull(salesCampaignDetail28);
        sb5.append(salesCampaignDetail28.getUnit());
        assert tvSurplusQuantityUnit != null;
        tvSurplusQuantityUnit.setText(sb5.toString());
        AppCompatTextView tvSurplusDiscount = salesCampaignViewHolder.getTvSurplusDiscount();
        Intrinsics.checkNotNull(salesCampaignDetail28);
        if (tvSurplusDiscount != null) {
            tvSurplusDiscount.setText(String.valueOf(salesCampaignDetail28.getSurplusDiscount()));
        }
        AppCompatTextView tvDiscount1 = salesCampaignViewHolder.getTvDiscount1();
        Intrinsics.checkNotNull(salesCampaignDetail28);
        if (tvDiscount1 != null) {
            tvDiscount1.setText(String.valueOf(salesCampaignDetail28.getDiscount1()));
        }
        AppCompatTextView tvDiscount2 = salesCampaignViewHolder.getTvDiscount2();
        Intrinsics.checkNotNull(salesCampaignDetail28);
        Objects.requireNonNull(tvDiscount2).setText(String.valueOf(salesCampaignDetail28.getDiscount2()));
        AppCompatTextView tvDiscount3 = salesCampaignViewHolder.getTvDiscount3();
        Intrinsics.checkNotNull(salesCampaignDetail28);
        Objects.requireNonNull(tvDiscount3).setText(String.valueOf(salesCampaignDetail28.getDiscount3()));
        Intrinsics.checkNotNull(salesCampaignDetail28);
        if (salesCampaignDetail28.getType() == lineType3.value) {
            Objects.requireNonNull(salesCampaignViewHolder.getLinearMaterial1()).setVisibility(View.GONE);
            Objects.requireNonNull(salesCampaignViewHolder.getLinearMaterial2()).setVisibility(View.GONE);
        } else {
            Objects.requireNonNull(salesCampaignViewHolder.getLinearMaterial1()).setVisibility(View.VISIBLE);
            Objects.requireNonNull(salesCampaignViewHolder.getLinearMaterial2()).setVisibility(View.VISIBLE);
        }
        LinearLayout lnSurplus = salesCampaignViewHolder.getLnSurplus();
        Intrinsics.checkNotNull(salesCampaignDetail28);
        assert lnSurplus != null;
        lnSurplus.setVisibility(salesCampaignDetail28.getSurplus() == 0.0f ? View.GONE : View.VISIBLE);
        Objects.requireNonNull(salesCampaignViewHolder.getLnSurplusDiscount()).setVisibility(View.GONE);
        LinearLayout lnCampaignPoint = salesCampaignViewHolder.getLnCampaignPoint();
        Intrinsics.checkNotNull(salesCampaignDetail28);
        Objects.requireNonNull(lnCampaignPoint).setVisibility(salesCampaignDetail28.getForNetsis() ? View.GONE : View.VISIBLE);
        LinearLayout lnDiscountRatio = salesCampaignViewHolder.getLnDiscountRatio();
        Intrinsics.checkNotNull(salesCampaignDetail28);
        assert lnDiscountRatio != null;
        lnDiscountRatio.setVisibility(salesCampaignDetail28.getForNetsis() ? View.GONE : View.VISIBLE);
        LinearLayout lnDiscount1 = salesCampaignViewHolder.getLnDiscount1();
        Intrinsics.checkNotNull(salesCampaignDetail28);
        if (salesCampaignDetail28.getForNetsis()) {
            Intrinsics.checkNotNull(salesCampaignDetail28);
            if (salesCampaignDetail28.getDiscount1() != 0.0f) {
                assert lnDiscount1 != null;
                lnDiscount1.setVisibility(View.VISIBLE);
                LinearLayout lnDiscount2 = salesCampaignViewHolder.getLnDiscount2();
                salesCampaignDetail = salesCampaignDetail28;
                Intrinsics.checkNotNull(salesCampaignDetail);
                if (salesCampaignDetail.getForNetsis()) {
                    Intrinsics.checkNotNull(salesCampaignDetail28);
                    if (salesCampaignDetail28.getDiscount2() != 0.0f) {
                        assert lnDiscount2 != null;
                        lnDiscount2.setVisibility(View.VISIBLE);
                        LinearLayout lnDiscount3 = salesCampaignViewHolder.getLnDiscount3();
                        salesCampaignDetail2 = salesCampaignDetail28;
                        Intrinsics.checkNotNull(salesCampaignDetail2);
                        if (salesCampaignDetail2.getForNetsis()) {
                            Intrinsics.checkNotNull(salesCampaignDetail28);
                        }
                        assert lnDiscount3 != null;
                        lnDiscount3.setVisibility(View.VISIBLE);
                        return view2;
                    }
                }
                assert lnDiscount2 != null;
                lnDiscount2.setVisibility(View.VISIBLE);
                LinearLayout lnDiscount32 = salesCampaignViewHolder.getLnDiscount3();
                salesCampaignDetail2 = salesCampaignDetail28;
                Intrinsics.checkNotNull(salesCampaignDetail2);
                Objects.requireNonNull(lnDiscount32).setVisibility(View.VISIBLE);
                return view2;
            }
        }
        Objects.requireNonNull(lnDiscount1).setVisibility(View.VISIBLE);
        LinearLayout lnDiscount22 = salesCampaignViewHolder.getLnDiscount2();
        salesCampaignDetail = salesCampaignDetail28;
        Intrinsics.checkNotNull(salesCampaignDetail);
        Objects.requireNonNull(lnDiscount22).setVisibility(View.VISIBLE);
        LinearLayout lnDiscount322 = salesCampaignViewHolder.getLnDiscount3();
        salesCampaignDetail2 = salesCampaignDetail28;
        Intrinsics.checkNotNull(salesCampaignDetail2);
        assert lnDiscount322 != null;
        lnDiscount322.setVisibility(View.VISIBLE);
        return view2;
    }
}
