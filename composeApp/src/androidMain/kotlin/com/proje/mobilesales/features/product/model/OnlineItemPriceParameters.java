package com.proje.mobilesales.features.product.model;

import kotlin.jvm.internal.Intrinsics;

public final class OnlineItemPriceParameters {
    private final int clRef;
    private final int divisionNr;
    private final int itemRef;
    private final int pType;
    private final int payPlanRef;
    private final boolean priceWithSlsmanCyphCode;
    private final int priority;
    private final String tradingGroup;
    private final int unitRef;
    private final String variantCode;

    public int component1() {
        return itemRef;
    }

    public int component10() {
        return divisionNr;
    }

    public int component2() {
        return clRef;
    }

    public int component3() {
        return unitRef;
    }

    public boolean component4() {
        return priceWithSlsmanCyphCode;
    }

    public int component5() {
        return priority;
    }

    public int component6() {
        return payPlanRef;
    }

    public String component7() {
        return tradingGroup;
    }

    public int component8() {
        return pType;
    }

    public String component9() {
        return variantCode;
    }

    public OnlineItemPriceParameters copy(final int i2, final int i3, final int i4, final boolean z, final int i5, final int i6, final String str, final int i7, final String str2, final int i8) {
        Intrinsics.checkNotNullParameter(str, "tradingGroup");
        return new OnlineItemPriceParameters(i2, i3, i4, z, i5, i6, str, i7, str2, i8);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OnlineItemPriceParameters onlineItemPriceParameters)) {
            return false;
        }
        return itemRef == onlineItemPriceParameters.itemRef && clRef == onlineItemPriceParameters.clRef && unitRef == onlineItemPriceParameters.unitRef && priceWithSlsmanCyphCode == onlineItemPriceParameters.priceWithSlsmanCyphCode && priority == onlineItemPriceParameters.priority && payPlanRef == onlineItemPriceParameters.payPlanRef && Intrinsics.areEqual(tradingGroup, onlineItemPriceParameters.tradingGroup) && pType == onlineItemPriceParameters.pType && Intrinsics.areEqual(variantCode, onlineItemPriceParameters.variantCode) && divisionNr == onlineItemPriceParameters.divisionNr;
    }

    public int hashCode() {
        final int hashCode = ((((((((((((((Integer.hashCode(itemRef) * 31) + Integer.hashCode(clRef)) * 31) + Integer.hashCode(unitRef)) * 31) + Boolean.hashCode(priceWithSlsmanCyphCode)) * 31) + Integer.hashCode(priority)) * 31) + Integer.hashCode(payPlanRef)) * 31) + tradingGroup.hashCode()) * 31) + Integer.hashCode(pType)) * 31;
        final String str = variantCode;
        return ((hashCode + (null == str ? 0 : str.hashCode())) * 31) + Integer.hashCode(divisionNr);
    }

    public String toString() {
        return "OnlineItemPriceParameters(itemRef=" + itemRef + ", clRef=" + clRef + ", unitRef=" + unitRef + ", priceWithSlsmanCyphCode=" + priceWithSlsmanCyphCode + ", priority=" + priority + ", payPlanRef=" + payPlanRef + ", tradingGroup=" + tradingGroup + ", pType=" + pType + ", variantCode=" + variantCode + ", divisionNr=" + divisionNr + ')';
    }

    public OnlineItemPriceParameters(final int i2, final int i3, final int i4, final boolean z, final int i5, final int i6, final String str, final int i7, final String str2, final int i8) {
        Intrinsics.checkNotNullParameter(str, "tradingGroup");
        itemRef = i2;
        clRef = i3;
        unitRef = i4;
        priceWithSlsmanCyphCode = z;
        priority = i5;
        payPlanRef = i6;
        tradingGroup = str;
        pType = i7;
        variantCode = str2;
        divisionNr = i8;
    }

    public int getItemRef() {
        return itemRef;
    }

    public int getClRef() {
        return clRef;
    }

    public int getUnitRef() {
        return unitRef;
    }

    public boolean getPriceWithSlsmanCyphCode() {
        return priceWithSlsmanCyphCode;
    }

    public int getPriority() {
        return priority;
    }

    public int getPayPlanRef() {
        return payPlanRef;
    }

    public String getTradingGroup() {
        return tradingGroup;
    }

    public int getPType() {
        return pType;
    }

    public String getVariantCode() {
        return variantCode;
    }

    public int getDivisionNr() {
        return divisionNr;
    }

    public static final class Builder {
        private int clRef;
        private int divisionNr;
        private int itemRef;
        private int pType;
        private int payPlanRef;
        private boolean priceWithSlsmanCyphCode;
        private int priority;
        private String tradingGroup = "";
        private int unitRef;
        private String variantCode;

        public Builder itemRef(final int i2) {
            itemRef = i2;
            return this;
        }

        public Builder clRef(final int i2) {
            clRef = i2;
            return this;
        }

        public Builder unitRef(final int i2) {
            unitRef = i2;
            return this;
        }

        public Builder priceWithSlsmanCyphCode(final boolean z) {
            priceWithSlsmanCyphCode = z;
            return this;
        }

        public Builder priority(final int i2) {
            priority = i2;
            return this;
        }

        public Builder payPlanRef(final int i2) {
            payPlanRef = i2;
            return this;
        }

        public Builder tradingGroup(final String str) {
            Intrinsics.checkNotNullParameter(str, "value");
            tradingGroup = str;
            return this;
        }

        public Builder pType(final int i2) {
            pType = i2;
            return this;
        }

        public Builder variantCode(final String str) {
            variantCode = str;
            return this;
        }

        public Builder divisionNr(final int i2) {
            divisionNr = i2;
            return this;
        }

        public OnlineItemPriceParameters build() {
            return new OnlineItemPriceParameters(itemRef, clRef, unitRef, priceWithSlsmanCyphCode, priority, payPlanRef, tradingGroup, pType, variantCode, divisionNr);
        }
    }
}
