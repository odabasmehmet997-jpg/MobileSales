package com.proje.mobilesales.features.sales.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.ContextUtils;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SalesFicheUserRights implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private int applyCampaign;
    private int applySalesCondition;
    private int generalCurrencyState;
    private boolean isChangeInvoiceDate;
    private boolean isChangeOfferOrder;
    private boolean isChangePrice;
    private boolean isChangeVat;
    private boolean isCheckPriceEnter;
    private boolean isDivFactWareHouseControl;
    private boolean isDoCampaignSalesCondition;
    private boolean isDoPromotion;
    private boolean isEnterPaymentPlan;
    private boolean isEnterPrice;
    private boolean isGetAllCustomerLastBuyPrice;
    private boolean isGetAllCustomerLastSellPrice;
    private boolean isGetCustomerLastBuyPrice;
    private boolean isGetCustomerLastSellPrice;
    private boolean isLineIntegration;
    private boolean isNotChangeReserve;
    private boolean isNotChangeUnit;
    private boolean isNotEnterPrice;
    private boolean isOnlyInvoiceStockProduct;
    private boolean isOpenClassic;
    private boolean isOpenForm;
    private boolean isReserve;
    private boolean isSurplusAmountEnabled;
    private int lineCurrencyState;
    private int orderStatus;

    public SalesFicheUserRights() {
        this(false, false, false, false, false, false, false, false, false, false, false, 0, 0, 0, 0, false, false, false, false, 0, false, false, false, false, false, false, false, false, 268435455, null);
    }

    public boolean component1() {
        return this.isEnterPrice;
    }

    public boolean component10() {
        return this.isGetAllCustomerLastSellPrice;
    }

    public boolean component11() {
        return this.isGetAllCustomerLastBuyPrice;
    }

    public int component12() {
        return this.generalCurrencyState;
    }

    public int component13() {
        return this.lineCurrencyState;
    }

    public int component14() {
        return this.applyCampaign;
    }

    public int component15() {
        return this.applySalesCondition;
    }

    public boolean component16() {
        return this.isDoPromotion;
    }

    public boolean component17() {
        return this.isNotEnterPrice;
    }

    public boolean component18() {
        return this.isOnlyInvoiceStockProduct;
    }

    public boolean component19() {
        return this.isChangeInvoiceDate;
    }

    public boolean component2() {
        return this.isChangePrice;
    }

    public int component20() {
        return this.orderStatus;
    }

    public boolean component21() {
        return this.isReserve;
    }

    public boolean component22() {
        return this.isNotChangeReserve;
    }

    public boolean component23() {
        return this.isChangeOfferOrder;
    }

    public boolean component24() {
        return this.isOpenForm;
    }

    public boolean component25() {
        return this.isOpenClassic;
    }

    public boolean component26() {
        return this.isDivFactWareHouseControl;
    }

    public boolean component27() {
        return this.isNotChangeUnit;
    }

    public boolean component28() {
        return this.isSurplusAmountEnabled;
    }

    public boolean component3() {
        return this.isChangeVat;
    }

    public boolean component4() {
        return this.isDoCampaignSalesCondition;
    }

    public boolean component5() {
        return this.isEnterPaymentPlan;
    }

    public boolean component6() {
        return this.isCheckPriceEnter;
    }

    public boolean component7() {
        return this.isLineIntegration;
    }

    public boolean component8() {
        return this.isGetCustomerLastSellPrice;
    }

    public boolean component9() {
        return this.isGetCustomerLastBuyPrice;
    }

    public SalesFicheUserRights copy(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, int r42, int r43, int r44, int r45, boolean z12, boolean z13, boolean z14, boolean z15, int r50, boolean z16, boolean z17, boolean z18, boolean z19, boolean z20, boolean z21, boolean z22, boolean z23) {
        return new SalesFicheUserRights(z, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, r42, r43, r44, r45, z12, z13, z14, z15, r50, z16, z17, z18, z19, z20, z21, z22, z23);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesFicheUserRights salesFicheUserRights)) {
            return false;
        }
        return this.isEnterPrice == salesFicheUserRights.isEnterPrice && this.isChangePrice == salesFicheUserRights.isChangePrice && this.isChangeVat == salesFicheUserRights.isChangeVat && this.isDoCampaignSalesCondition == salesFicheUserRights.isDoCampaignSalesCondition && this.isEnterPaymentPlan == salesFicheUserRights.isEnterPaymentPlan && this.isCheckPriceEnter == salesFicheUserRights.isCheckPriceEnter && this.isLineIntegration == salesFicheUserRights.isLineIntegration && this.isGetCustomerLastSellPrice == salesFicheUserRights.isGetCustomerLastSellPrice && this.isGetCustomerLastBuyPrice == salesFicheUserRights.isGetCustomerLastBuyPrice && this.isGetAllCustomerLastSellPrice == salesFicheUserRights.isGetAllCustomerLastSellPrice && this.isGetAllCustomerLastBuyPrice == salesFicheUserRights.isGetAllCustomerLastBuyPrice && this.generalCurrencyState == salesFicheUserRights.generalCurrencyState && this.lineCurrencyState == salesFicheUserRights.lineCurrencyState && this.applyCampaign == salesFicheUserRights.applyCampaign && this.applySalesCondition == salesFicheUserRights.applySalesCondition && this.isDoPromotion == salesFicheUserRights.isDoPromotion && this.isNotEnterPrice == salesFicheUserRights.isNotEnterPrice && this.isOnlyInvoiceStockProduct == salesFicheUserRights.isOnlyInvoiceStockProduct && this.isChangeInvoiceDate == salesFicheUserRights.isChangeInvoiceDate && this.orderStatus == salesFicheUserRights.orderStatus && this.isReserve == salesFicheUserRights.isReserve && this.isNotChangeReserve == salesFicheUserRights.isNotChangeReserve && this.isChangeOfferOrder == salesFicheUserRights.isChangeOfferOrder && this.isOpenForm == salesFicheUserRights.isOpenForm && this.isOpenClassic == salesFicheUserRights.isOpenClassic && this.isDivFactWareHouseControl == salesFicheUserRights.isDivFactWareHouseControl && this.isNotChangeUnit == salesFicheUserRights.isNotChangeUnit && this.isSurplusAmountEnabled == salesFicheUserRights.isSurplusAmountEnabled;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((Boolean.hashCode(this.isEnterPrice) * 31) + Boolean.hashCode(this.isChangePrice)) * 31) + Boolean.hashCode(this.isChangeVat)) * 31) + Boolean.hashCode(this.isDoCampaignSalesCondition)) * 31) + Boolean.hashCode(this.isEnterPaymentPlan)) * 31) + Boolean.hashCode(this.isCheckPriceEnter)) * 31) + Boolean.hashCode(this.isLineIntegration)) * 31) + Boolean.hashCode(this.isGetCustomerLastSellPrice)) * 31) + Boolean.hashCode(this.isGetCustomerLastBuyPrice)) * 31) + Boolean.hashCode(this.isGetAllCustomerLastSellPrice)) * 31) + Boolean.hashCode(this.isGetAllCustomerLastBuyPrice)) * 31) + Integer.hashCode(this.generalCurrencyState)) * 31) + Integer.hashCode(this.lineCurrencyState)) * 31) + Integer.hashCode(this.applyCampaign)) * 31) + Integer.hashCode(this.applySalesCondition)) * 31) + Boolean.hashCode(this.isDoPromotion)) * 31) + Boolean.hashCode(this.isNotEnterPrice)) * 31) + Boolean.hashCode(this.isOnlyInvoiceStockProduct)) * 31) + Boolean.hashCode(this.isChangeInvoiceDate)) * 31) + Integer.hashCode(this.orderStatus)) * 31) + Boolean.hashCode(this.isReserve)) * 31) + Boolean.hashCode(this.isNotChangeReserve)) * 31) + Boolean.hashCode(this.isChangeOfferOrder)) * 31) + Boolean.hashCode(this.isOpenForm)) * 31) + Boolean.hashCode(this.isOpenClassic)) * 31) + Boolean.hashCode(this.isDivFactWareHouseControl)) * 31) + Boolean.hashCode(this.isNotChangeUnit)) * 31) + Boolean.hashCode(this.isSurplusAmountEnabled);
    }

    public String toString() {
        return "SalesFicheUserRights(isEnterPrice=" + this.isEnterPrice + ", isChangePrice=" + this.isChangePrice + ", isChangeVat=" + this.isChangeVat + ", isDoCampaignSalesCondition=" + this.isDoCampaignSalesCondition + ", isEnterPaymentPlan=" + this.isEnterPaymentPlan + ", isCheckPriceEnter=" + this.isCheckPriceEnter + ", isLineIntegration=" + this.isLineIntegration + ", isGetCustomerLastSellPrice=" + this.isGetCustomerLastSellPrice + ", isGetCustomerLastBuyPrice=" + this.isGetCustomerLastBuyPrice + ", isGetAllCustomerLastSellPrice=" + this.isGetAllCustomerLastSellPrice + ", isGetAllCustomerLastBuyPrice=" + this.isGetAllCustomerLastBuyPrice + ", generalCurrencyState=" + this.generalCurrencyState + ", lineCurrencyState=" + this.lineCurrencyState + ", applyCampaign=" + this.applyCampaign + ", applySalesCondition=" + this.applySalesCondition + ", isDoPromotion=" + this.isDoPromotion + ", isNotEnterPrice=" + this.isNotEnterPrice + ", isOnlyInvoiceStockProduct=" + this.isOnlyInvoiceStockProduct + ", isChangeInvoiceDate=" + this.isChangeInvoiceDate + ", orderStatus=" + this.orderStatus + ", isReserve=" + this.isReserve + ", isNotChangeReserve=" + this.isNotChangeReserve + ", isChangeOfferOrder=" + this.isChangeOfferOrder + ", isOpenForm=" + this.isOpenForm + ", isOpenClassic=" + this.isOpenClassic + ", isDivFactWareHouseControl=" + this.isDivFactWareHouseControl + ", isNotChangeUnit=" + this.isNotChangeUnit + ", isSurplusAmountEnabled=" + this.isSurplusAmountEnabled + ')';
    }

    public SalesFicheUserRights(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, int r14, int r15, int r16, int r17, boolean z12, boolean z13, boolean z14, boolean z15, int r22, boolean z16, boolean z17, boolean z18, boolean z19, boolean z20, boolean z21, boolean z22, boolean z23) {
        this.isEnterPrice = z;
        this.isChangePrice = z2;
        this.isChangeVat = z3;
        this.isDoCampaignSalesCondition = z4;
        this.isEnterPaymentPlan = z5;
        this.isCheckPriceEnter = z6;
        this.isLineIntegration = z7;
        this.isGetCustomerLastSellPrice = z8;
        this.isGetCustomerLastBuyPrice = z9;
        this.isGetAllCustomerLastSellPrice = z10;
        this.isGetAllCustomerLastBuyPrice = z11;
        this.generalCurrencyState = r14;
        this.lineCurrencyState = r15;
        this.applyCampaign = r16;
        this.applySalesCondition = r17;
        this.isDoPromotion = z12;
        this.isNotEnterPrice = z13;
        this.isOnlyInvoiceStockProduct = z14;
        this.isChangeInvoiceDate = z15;
        this.orderStatus = r22;
        this.isReserve = z16;
        this.isNotChangeReserve = z17;
        this.isChangeOfferOrder = z18;
        this.isOpenForm = z19;
        this.isOpenClassic = z20;
        this.isDivFactWareHouseControl = z21;
        this.isNotChangeUnit = z22;
        this.isSurplusAmountEnabled = z23;
    }

    public /* synthetic */ SalesFicheUserRights(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, int r41, int r42, int r43, int r44, boolean z12, boolean z13, boolean z14, boolean z15, int r49, boolean z16, boolean z17, boolean z18, boolean z19, boolean z20, boolean z21, boolean z22, boolean z23, int r58, DefaultConstructorMarker defaultConstructorMarker) {
        this((r58 & 1) == 0 && z, (r58 & 2) == 0 && z2, (r58 & 4) == 0 && z3, (r58 & 8) == 0 && z4, (r58 & 16) == 0 && z5, (r58 & 32) == 0 && z6, (r58 & 64) == 0 && z7, (r58 & 128) == 0 && z8, (r58 & 256) == 0 && z9, (r58 & 512) == 0 && z10, (r58 & 1024) == 0 && z11, (r58 & 2048) != 0 ? 0 : r41, (r58 & 4096) != 0 ? 0 : r42, (r58 & 8192) != 0 ? 0 : r43, (r58 & 16384) != 0 ? 0 : r44, (r58 & 32768) == 0 && z12, (r58 & 65536) == 0 && z13, (r58 & 131072) == 0 && z14, (r58 & 262144) == 0 && z15, (r58 & 524288) != 0 ? 0 : r49, (r58 & 1048576) == 0 && z16, (r58 & 2097152) == 0 && z17, (r58 & 4194304) == 0 && z18, (r58 & 8388608) == 0 && z19, (r58 & 16777216) == 0 && z20, (r58 & 33554432) == 0 && z21, (r58 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) == 0 && z22, (r58 & 134217728) == 0 && z23);
    }

    public boolean isEnterPrice() {
        return this.isEnterPrice;
    }

    public void setEnterPrice(boolean z) {
        this.isEnterPrice = z;
    }

    public boolean isChangePrice() {
        return this.isChangePrice;
    }

    public void setChangePrice(boolean z) {
        this.isChangePrice = z;
    }

    public boolean isChangeVat() {
        return this.isChangeVat;
    }

    public void setChangeVat(boolean z) {
        this.isChangeVat = z;
    }

    public boolean isDoCampaignSalesCondition() {
        return this.isDoCampaignSalesCondition;
    }

    public void setDoCampaignSalesCondition(boolean z) {
        this.isDoCampaignSalesCondition = z;
    }

    public boolean isEnterPaymentPlan() {
        return this.isEnterPaymentPlan;
    }

    public void setEnterPaymentPlan(boolean z) {
        this.isEnterPaymentPlan = z;
    }

    public boolean isCheckPriceEnter() {
        return this.isCheckPriceEnter;
    }

    public void setCheckPriceEnter(boolean z) {
        this.isCheckPriceEnter = z;
    }

    public boolean isLineIntegration() {
        return this.isLineIntegration;
    }

    public void setLineIntegration(boolean z) {
        this.isLineIntegration = z;
    }

    public boolean isGetCustomerLastSellPrice() {
        return this.isGetCustomerLastSellPrice;
    }

    public void setGetCustomerLastSellPrice(boolean z) {
        this.isGetCustomerLastSellPrice = z;
    }

    public boolean isGetCustomerLastBuyPrice() {
        return this.isGetCustomerLastBuyPrice;
    }

    public void setGetCustomerLastBuyPrice(boolean z) {
        this.isGetCustomerLastBuyPrice = z;
    }

    public boolean isGetAllCustomerLastSellPrice() {
        return this.isGetAllCustomerLastSellPrice;
    }

    public void setGetAllCustomerLastSellPrice(boolean z) {
        this.isGetAllCustomerLastSellPrice = z;
    }

    public boolean isGetAllCustomerLastBuyPrice() {
        return this.isGetAllCustomerLastBuyPrice;
    }

    public void setGetAllCustomerLastBuyPrice(boolean z) {
        this.isGetAllCustomerLastBuyPrice = z;
    }

    public int getGeneralCurrencyState() {
        return this.generalCurrencyState;
    }

    public void setGeneralCurrencyState(int r1) {
        this.generalCurrencyState = r1;
    }

    public int getLineCurrencyState() {
        return this.lineCurrencyState;
    }

    public void setLineCurrencyState(int r1) {
        this.lineCurrencyState = r1;
    }

    public int getApplyCampaign() {
        return this.applyCampaign;
    }

    public void setApplyCampaign(int r1) {
        this.applyCampaign = r1;
    }

    public int getApplySalesCondition() {
        return this.applySalesCondition;
    }

    public void setApplySalesCondition(int r1) {
        this.applySalesCondition = r1;
    }

    public boolean isDoPromotion() {
        return this.isDoPromotion;
    }

    public void setDoPromotion(boolean z) {
        this.isDoPromotion = z;
    }

    public boolean isNotEnterPrice() {
        return this.isNotEnterPrice;
    }

    public void setNotEnterPrice(boolean z) {
        this.isNotEnterPrice = z;
    }

    public boolean isOnlyInvoiceStockProduct() {
        return this.isOnlyInvoiceStockProduct;
    }

    public void setOnlyInvoiceStockProduct(boolean z) {
        this.isOnlyInvoiceStockProduct = z;
    }

    public boolean isChangeInvoiceDate() {
        return this.isChangeInvoiceDate;
    }

    public void setChangeInvoiceDate(boolean z) {
        this.isChangeInvoiceDate = z;
    }

    public int getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(int r1) {
        this.orderStatus = r1;
    }

    public boolean isReserve() {
        return this.isReserve;
    }

    public void setReserve(boolean z) {
        this.isReserve = z;
    }

    public boolean isNotChangeReserve() {
        return this.isNotChangeReserve;
    }

    public void setNotChangeReserve(boolean z) {
        this.isNotChangeReserve = z;
    }

    public boolean isChangeOfferOrder() {
        return this.isChangeOfferOrder;
    }

    public void setChangeOfferOrder(boolean z) {
        this.isChangeOfferOrder = z;
    }

    public boolean isOpenForm() {
        return this.isOpenForm;
    }

    public void setOpenForm(boolean z) {
        this.isOpenForm = z;
    }

    public boolean isOpenClassic() {
        return this.isOpenClassic;
    }

    public void setOpenClassic(boolean z) {
        this.isOpenClassic = z;
    }

    public boolean isDivFactWareHouseControl() {
        return this.isDivFactWareHouseControl;
    }

    public void setDivFactWareHouseControl(boolean z) {
        this.isDivFactWareHouseControl = z;
    }

    public boolean isNotChangeUnit() {
        return this.isNotChangeUnit;
    }

    public void setNotChangeUnit(boolean z) {
        this.isNotChangeUnit = z;
    }

    public boolean isSurplusAmountEnabled() {
        return this.isSurplusAmountEnabled;
    }

    public void setSurplusAmountEnabled(boolean z) {
        this.isSurplusAmountEnabled = z;
    }
    private SalesFicheUserRights(Parcel parcel) {
        SalesFicheUserRights salesFicheUserRights;
        boolean z;
        this(false, false, false, false, false, false, false, false, false, false, false, 0, 0, 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, false, false, false, 0, false, false, false, false, false, false, false, false, 268435455, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        if (parcel.readByte() != 0) {
            salesFicheUserRights = this;
            z = true;
        } else {
            salesFicheUserRights = this;
            z = false;
        }
        salesFicheUserRights.isEnterPrice = z;
        salesFicheUserRights.isChangePrice = parcel.readByte() != 0;
        salesFicheUserRights.isChangeVat = parcel.readByte() != 0;
        salesFicheUserRights.isDoCampaignSalesCondition = parcel.readByte() != 0;
        salesFicheUserRights.isEnterPaymentPlan = parcel.readByte() != 0;
        salesFicheUserRights.isCheckPriceEnter = parcel.readByte() != 0;
        salesFicheUserRights.isLineIntegration = parcel.readByte() != 0;
        salesFicheUserRights.isGetCustomerLastSellPrice = parcel.readByte() != 0;
        salesFicheUserRights.isGetCustomerLastBuyPrice = parcel.readByte() != 0;
        salesFicheUserRights.isGetAllCustomerLastSellPrice = parcel.readByte() != 0;
        salesFicheUserRights.isGetAllCustomerLastBuyPrice = parcel.readByte() != 0;
        salesFicheUserRights.generalCurrencyState = parcel.readInt();
        salesFicheUserRights.lineCurrencyState = parcel.readInt();
        salesFicheUserRights.applyCampaign = parcel.readInt();
        salesFicheUserRights.applySalesCondition = parcel.readInt();
        salesFicheUserRights.isDoPromotion = parcel.readByte() != 0;
        salesFicheUserRights.isNotEnterPrice = parcel.readByte() != 0;
        salesFicheUserRights.isOnlyInvoiceStockProduct = parcel.readByte() != 0;
        salesFicheUserRights.isChangeInvoiceDate = parcel.readByte() != 0;
        salesFicheUserRights.orderStatus = parcel.readInt();
        salesFicheUserRights.isReserve = parcel.readByte() != 0;
        salesFicheUserRights.isNotChangeReserve = parcel.readByte() != 0;
        salesFicheUserRights.isChangeOfferOrder = parcel.readByte() != 0;
        salesFicheUserRights.isOpenForm = parcel.readByte() != 0;
        salesFicheUserRights.isOpenClassic = parcel.readByte() != 0;
        salesFicheUserRights.isDivFactWareHouseControl = parcel.readByte() != 0;
        salesFicheUserRights.isNotChangeUnit = parcel.readByte() != 0;
        salesFicheUserRights.isSurplusAmountEnabled = parcel.readByte() != 0;
    }

    public CharSequence[] getCustomerSelectedPriceType() {
        ArrayList arrayList = new ArrayList();
        if (this.isGetCustomerLastSellPrice) {
            Context context = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context);
            String string = context.getString(R.string.str_get_customer_last_sell_price);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            arrayList.add(string);
        }
        if (this.isGetCustomerLastBuyPrice) {
            Context context2 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context2);
            String string2 = context2.getString(R.string.str_get_customer_last_buy_price);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            arrayList.add(string2);
        }
        if (this.isGetAllCustomerLastSellPrice) {
            Context context3 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context3);
            String string3 = context3.getString(R.string.str_get_all_customer_sell_price);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            arrayList.add(string3);
        }
        if (this.isGetAllCustomerLastBuyPrice) {
            Context context4 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context4);
            String string4 = context4.getString(R.string.str_get_all_customer_buy_price);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            arrayList.add(string4);
        }
        return (CharSequence[]) arrayList.toArray(new CharSequence[0]);
    }
    public void writeToParcel(Parcel dest, int r2) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeByte(this.isEnterPrice ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isChangePrice ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isChangeVat ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isDoCampaignSalesCondition ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isEnterPaymentPlan ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isCheckPriceEnter ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isLineIntegration ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isGetCustomerLastSellPrice ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isGetCustomerLastBuyPrice ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isGetAllCustomerLastSellPrice ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isGetAllCustomerLastBuyPrice ? (byte) 1 : (byte) 0);
        dest.writeInt(this.generalCurrencyState);
        dest.writeInt(this.lineCurrencyState);
        dest.writeInt(this.applyCampaign);
        dest.writeInt(this.applySalesCondition);
        dest.writeByte(this.isDoPromotion ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isNotEnterPrice ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isOnlyInvoiceStockProduct ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isChangeInvoiceDate ? (byte) 1 : (byte) 0);
        dest.writeInt(this.orderStatus);
        dest.writeByte(this.isReserve ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isNotChangeReserve ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isChangeOfferOrder ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isOpenForm ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isOpenClassic ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isDivFactWareHouseControl ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isNotChangeUnit ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isSurplusAmountEnabled ? (byte) 1 : (byte) 0);
    }
    public static final class CREATOR implements Creator<SalesFicheUserRights> {
        public CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private CREATOR() {
        }
        public SalesFicheUserRights createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new SalesFicheUserRights(source);
        }
        public SalesFicheUserRights[] newArray(int r1) {
            return new SalesFicheUserRights[r1];
        }
    }
}
