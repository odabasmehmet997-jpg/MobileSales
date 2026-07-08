package com.proje.mobilesales.features.sales.view.validation;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.LineType;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.sales.model.database.SalesView;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class OrderValidationDetailAdapter extends BaseAdapter {
    private final Context mContext;
    private final ArrayList<SalesView> mSalesViews;

    public OrderValidationDetailAdapter(ArrayList<SalesView> arrayList, Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.mSalesViews = arrayList;
        this.mContext = mContext;
    }
    public int getCount() {
        ArrayList<SalesView> arrayList = this.mSalesViews;
        if (arrayList == null) {
            return 0;
        }
        Intrinsics.checkNotNull(arrayList);
        return arrayList.size();
    }
    public SalesView getItem(int i2) {
        ArrayList<SalesView> arrayList = this.mSalesViews;
        Intrinsics.checkNotNull(arrayList);
        SalesView salesView = arrayList.get(i2);
        Intrinsics.checkNotNullExpressionValue(salesView, "get(...)");
        return salesView;
    }

    public long getItemId(int i2) {
        Intrinsics.checkNotNull(this.mSalesViews);
        return this.mSalesViews.get(i2).logicalRef;
    }

    public View getView(int i2, View convertView, ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(convertView, "convertView");
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        LayoutInflater.from(this.mContext);
        Object tag = convertView.getTag();
        Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.view.validation.OrderValidationDetailAdapter.ValidationDetailView");
        ValidationDetailView validationDetailView = (ValidationDetailView) tag;
        resetViewHolder(validationDetailView);
        SalesView item = getItem(i2);
        if (item != null) {
            AppCompatTextView txtViewLogicalRef = validationDetailView.getTxtViewLogicalRef();
            Intrinsics.checkNotNull(txtViewLogicalRef);
            txtViewLogicalRef.setText(StringUtils.convertIntToString(item.logicalRef));
            if (item.lineType != LineType.DISCOUNT.value) {
                AppCompatTextView txtViewName = validationDetailView.getTxtViewName();
                Intrinsics.checkNotNull(txtViewName);
                txtViewName.setText(item.name);
                AppCompatTextView txtViewCode = validationDetailView.getTxtViewCode();
                Intrinsics.checkNotNull(txtViewCode);
                txtViewCode.setText(item.code);
                AppCompatTextView txtViewUnit = validationDetailView.getTxtViewUnit();
                Intrinsics.checkNotNull(txtViewUnit);
                txtViewUnit.setText(item.unit);
                AppCompatTextView txtViewAmount = validationDetailView.getTxtViewAmount();
                Intrinsics.checkNotNull(txtViewAmount);
                txtViewAmount.setText(StringUtils.convertDoubleToString(Double.valueOf(item.amount)));
                AppCompatTextView txtViewPrice = validationDetailView.getTxtViewPrice();
                Intrinsics.checkNotNull(txtViewPrice);
                txtViewPrice.setText(StringUtils.formatDouble(item.price));
                AppCompatTextView txtViewVat = validationDetailView.getTxtViewVat();
                Intrinsics.checkNotNull(txtViewVat);
                txtViewVat.setText(StringUtils.convertDoubleToString(Double.valueOf(item.vatAmount)));
                AppCompatTextView txtViewNetTotal = validationDetailView.getTxtViewNetTotal();
                Intrinsics.checkNotNull(txtViewNetTotal);
                txtViewNetTotal.setText(StringUtils.formatDouble(item.netTotal));
            } else {
                setDiscountVisibility(validationDetailView);
                if (item.globTrans == 0) {
                    AppCompatTextView txtViewName2 = validationDetailView.getTxtViewName();
                    Intrinsics.checkNotNull(txtViewName2);
                    txtViewName2.setText(this.mContext.getString(R.string.str_line_discount));
                } else {
                    AppCompatTextView txtViewName3 = validationDetailView.getTxtViewName();
                    Intrinsics.checkNotNull(txtViewName3);
                    txtViewName3.setText(this.mContext.getString(R.string.str_general_discount));
                }
                if (!TextUtils.isEmpty(item.dCode)) {
                    AppCompatTextView txtViewCode2 = validationDetailView.getTxtViewCode();
                    Intrinsics.checkNotNull(txtViewCode2);
                    txtViewCode2.setText(item.dCode);
                } else {
                    AppCompatTextView txtViewCode3 = validationDetailView.getTxtViewCode();
                    Intrinsics.checkNotNull(txtViewCode3);
                    txtViewCode3.setVisibility(View.GONE);
                    AppCompatTextView txtCode = validationDetailView.getTxtCode();
                    Intrinsics.checkNotNull(txtCode);
                    txtCode.setVisibility(View.GONE);
                }
                AppCompatTextView txtViewVat2 = validationDetailView.getTxtViewVat();
                Intrinsics.checkNotNull(txtViewVat2);
                txtViewVat2.setText(StringUtils.convertDoubleToString(Double.valueOf(item.discPer)));
                AppCompatTextView txtViewNetTotal2 = validationDetailView.getTxtViewNetTotal();
                Intrinsics.checkNotNull(txtViewNetTotal2);
                txtViewNetTotal2.setText(StringUtils.formatDouble(item.total));
            }
        }
        return convertView;
    }  void resetViewHolder(ValidationDetailView validationDetailView) {
        AppCompatTextView txtViewCode = validationDetailView.getTxtViewCode();
        Intrinsics.checkNotNull(txtViewCode);
        txtViewCode.setVisibility(View.VISIBLE);
        AppCompatTextView txtCode = validationDetailView.getTxtCode();
        Intrinsics.checkNotNull(txtCode);
        txtCode.setVisibility(View.VISIBLE);
        AppCompatTextView txtViewUnit = validationDetailView.getTxtViewUnit();
        Intrinsics.checkNotNull(txtViewUnit);
        txtViewUnit.setVisibility(View.VISIBLE);
        AppCompatTextView txtUnit = validationDetailView.getTxtUnit();
        Intrinsics.checkNotNull(txtUnit);
        txtUnit.setVisibility(View.VISIBLE);
        AppCompatTextView txtViewAmount = validationDetailView.getTxtViewAmount();
        Intrinsics.checkNotNull(txtViewAmount);
        txtViewAmount.setVisibility(View.VISIBLE);
        AppCompatTextView txtAmount = validationDetailView.getTxtAmount();
        Intrinsics.checkNotNull(txtAmount);
        txtAmount.setVisibility(View.VISIBLE);
        AppCompatTextView txtViewPrice = validationDetailView.getTxtViewPrice();
        Intrinsics.checkNotNull(txtViewPrice);
        txtViewPrice.setVisibility(View.VISIBLE);
        AppCompatTextView txtPrice = validationDetailView.getTxtPrice();
        Intrinsics.checkNotNull(txtPrice);
        txtPrice.setVisibility(View.VISIBLE);
        AppCompatTextView txtName = validationDetailView.getTxtName();
        Intrinsics.checkNotNull(txtName);
        txtName.setText(this.mContext.getString(R.string.str_fiche_item_name));
        AppCompatTextView txtCode2 = validationDetailView.getTxtCode();
        Intrinsics.checkNotNull(txtCode2);
        txtCode2.setText(this.mContext.getString(R.string.str_fiche_item_code));
        AppCompatTextView txtVat = validationDetailView.getTxtVat();
        Intrinsics.checkNotNull(txtVat);
        txtVat.setText(this.mContext.getString(R.string.str_vat_ratio));
        AppCompatTextView txtNetTotal = validationDetailView.getTxtNetTotal();
        Intrinsics.checkNotNull(txtNetTotal);
        txtNetTotal.setText(this.mContext.getString(R.string.str_fiche_item_total));
    }

    private   void setDiscountVisibility(ValidationDetailView validationDetailView) {
        AppCompatTextView txtViewUnit = validationDetailView.getTxtViewUnit();
        Intrinsics.checkNotNull(txtViewUnit);
        txtViewUnit.setVisibility(View.GONE);
        AppCompatTextView txtUnit = validationDetailView.getTxtUnit();
        Intrinsics.checkNotNull(txtUnit);
        txtUnit.setVisibility(View.GONE);
        AppCompatTextView txtViewAmount = validationDetailView.getTxtViewAmount();
        Intrinsics.checkNotNull(txtViewAmount);
        txtViewAmount.setVisibility(View.GONE);
        AppCompatTextView txtAmount = validationDetailView.getTxtAmount();
        Intrinsics.checkNotNull(txtAmount);
        txtAmount.setVisibility(View.GONE);
        AppCompatTextView txtViewPrice = validationDetailView.getTxtViewPrice();
        Intrinsics.checkNotNull(txtViewPrice);
        txtViewPrice.setVisibility(View.GONE);
        AppCompatTextView txtPrice = validationDetailView.getTxtPrice();
        Intrinsics.checkNotNull(txtPrice);
        txtPrice.setVisibility(View.GONE);
        AppCompatTextView txtName = validationDetailView.getTxtName();
        Intrinsics.checkNotNull(txtName);
        txtName.setText(this.mContext.getString(R.string.str_discount_type));
        AppCompatTextView txtCode = validationDetailView.getTxtCode();
        Intrinsics.checkNotNull(txtCode);
        txtCode.setText(this.mContext.getString(R.string.str_discount_code));
        AppCompatTextView txtVat = validationDetailView.getTxtVat();
        Intrinsics.checkNotNull(txtVat);
        txtVat.setText(this.mContext.getString(R.string.str_discount_ratio));
        AppCompatTextView txtNetTotal = validationDetailView.getTxtNetTotal();
        Intrinsics.checkNotNull(txtNetTotal);
        txtNetTotal.setText(this.mContext.getString(R.string.str_discount_price));
    }
    public static final class ValidationDetailView {
        private AppCompatTextView txtAmount;
        private AppCompatTextView txtCode;
        private AppCompatTextView txtName;
        private AppCompatTextView txtNetTotal;
        private AppCompatTextView txtPrice;
        private AppCompatTextView txtUnit;
        private AppCompatTextView txtVat;
        private AppCompatTextView txtViewAmount;
        private AppCompatTextView txtViewCode;
        private AppCompatTextView txtViewLogicalRef;
        private AppCompatTextView txtViewName;
        private AppCompatTextView txtViewNetTotal;
        private AppCompatTextView txtViewPrice;
        private AppCompatTextView txtViewUnit;
        private AppCompatTextView txtViewVat;

        public   AppCompatTextView getTxtViewName() {
            return this.txtViewName;
        }

        public   void setTxtViewName(AppCompatTextView appCompatTextView) {
            this.txtViewName = appCompatTextView;
        }

        public   AppCompatTextView getTxtName() {
            return this.txtName;
        }

        public   void setTxtName(AppCompatTextView appCompatTextView) {
            this.txtName = appCompatTextView;
        }

        public   AppCompatTextView getTxtViewCode() {
            return this.txtViewCode;
        }

        public   void setTxtViewCode(AppCompatTextView appCompatTextView) {
            this.txtViewCode = appCompatTextView;
        }

        public   AppCompatTextView getTxtCode() {
            return this.txtCode;
        }

        public   void setTxtCode(AppCompatTextView appCompatTextView) {
            this.txtCode = appCompatTextView;
        }

        public   AppCompatTextView getTxtViewUnit() {
            return this.txtViewUnit;
        }

        public void setTxtViewUnit(AppCompatTextView appCompatTextView) {
            this.txtViewUnit = appCompatTextView;
        }

        public AppCompatTextView getTxtUnit() {
            return this.txtUnit;
        }

        public void setTxtUnit(AppCompatTextView appCompatTextView) {
            this.txtUnit = appCompatTextView;
        }

        public AppCompatTextView getTxtViewAmount() {
            return this.txtViewAmount;
        }

        public void setTxtViewAmount(AppCompatTextView appCompatTextView) {
            this.txtViewAmount = appCompatTextView;
        }

        public AppCompatTextView getTxtAmount() {
            return this.txtAmount;
        }

        public void setTxtAmount(AppCompatTextView appCompatTextView) {
            this.txtAmount = appCompatTextView;
        }

        public AppCompatTextView getTxtViewPrice() {
            return this.txtViewPrice;
        }

        public void setTxtViewPrice(AppCompatTextView appCompatTextView) {
            this.txtViewPrice = appCompatTextView;
        }

        public AppCompatTextView getTxtPrice() {
            return this.txtPrice;
        }

        public void setTxtPrice(AppCompatTextView appCompatTextView) {
            this.txtPrice = appCompatTextView;
        }

        public AppCompatTextView getTxtViewVat() {
            return this.txtViewVat;
        }

        public void setTxtViewVat(AppCompatTextView appCompatTextView) {
            this.txtViewVat = appCompatTextView;
        }

        public AppCompatTextView getTxtVat() {
            return this.txtVat;
        }

        public void setTxtVat(AppCompatTextView appCompatTextView) {
            this.txtVat = appCompatTextView;
        }

        public AppCompatTextView getTxtViewNetTotal() {
            return this.txtViewNetTotal;
        }

        public void setTxtViewNetTotal(AppCompatTextView appCompatTextView) {
            this.txtViewNetTotal = appCompatTextView;
        }

        public AppCompatTextView getTxtNetTotal() {
            return this.txtNetTotal;
        }

        public void setTxtNetTotal(AppCompatTextView appCompatTextView) {
            this.txtNetTotal = appCompatTextView;
        }

        public AppCompatTextView getTxtViewLogicalRef() {
            return this.txtViewLogicalRef;
        }

        public void setTxtViewLogicalRef(AppCompatTextView appCompatTextView) {
            this.txtViewLogicalRef = appCompatTextView;
        }
    }
}
