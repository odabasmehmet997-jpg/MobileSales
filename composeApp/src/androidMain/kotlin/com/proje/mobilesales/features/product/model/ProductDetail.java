package com.proje.mobilesales.features.product.model;

import androidx.core.app.FrameMetricsAggregator;
import com.proje.mobilesales.features.model.ItemVariantStock;
import com.proje.mobilesales.features.model.MonthlyProductSales;
import com.proje.mobilesales.features.model.TopProduct;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ProductDetail extends Product {
    private ArrayList<Ambar> ambar;
    private String grupcode;
    private ArrayList<ItemPrice> itemPrices;
    private ArrayList<DetailItemUnit> itemUnits;
    private String mark;
    private ArrayList<MonthlyProductSales> monthlyProductSalesList;
    private String paymentPlan;
    private String specode1;
    private String specode2;
    private String specode3;
    private String specode4;
    private String specode5;
    private ArrayList<TopProduct> topProducts;

    public ProductDetail() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
    }

    public String component1() {
        return this.specode1;
    }

    public ArrayList<ItemPrice> component10() {
        return this.itemPrices;
    }

    public ArrayList<DetailItemUnit> component11() {
        return this.itemUnits;
    }

    public ArrayList<MonthlyProductSales> component12() {
        return this.monthlyProductSalesList;
    }

    public ArrayList<TopProduct> component13() {
        return this.topProducts;
    }

    public String component2() {
        return this.specode2;
    }

    public String component3() {
        return this.specode3;
    }

    public String component4() {
        return this.specode4;
    }

    public String component5() {
        return this.specode5;
    }

    public String component6() {
        return this.grupcode;
    }

    public String component7() {
        return this.mark;
    }

    public String component8() {
        return this.paymentPlan;
    }

    public ArrayList<Ambar> component9() {
        return this.ambar;
    }

    public ProductDetail copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, ArrayList<Ambar> arrayList, ArrayList<ItemPrice> arrayList2, ArrayList<DetailItemUnit> arrayList3, ArrayList<MonthlyProductSales> arrayList4, ArrayList<TopProduct> arrayList5) {
        return new ProductDetail(str, str2, str3, str4, str5, str6, str7, str8, arrayList, arrayList2, arrayList3, arrayList4, arrayList5);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProductDetail productDetail)) {
            return false;
        }
        return Intrinsics.areEqual(this.specode1, productDetail.specode1) && Intrinsics.areEqual(this.specode2, productDetail.specode2) && Intrinsics.areEqual(this.specode3, productDetail.specode3) && Intrinsics.areEqual(this.specode4, productDetail.specode4) && Intrinsics.areEqual(this.specode5, productDetail.specode5) && Intrinsics.areEqual(this.grupcode, productDetail.grupcode) && Intrinsics.areEqual(this.mark, productDetail.mark) && Intrinsics.areEqual(this.paymentPlan, productDetail.paymentPlan) && Intrinsics.areEqual(this.ambar, productDetail.ambar) && Intrinsics.areEqual(this.itemPrices, productDetail.itemPrices) && Intrinsics.areEqual(this.itemUnits, productDetail.itemUnits) && Intrinsics.areEqual(this.monthlyProductSalesList, productDetail.monthlyProductSalesList) && Intrinsics.areEqual(this.topProducts, productDetail.topProducts);
    }

    public int hashCode() {
        String str = this.specode1;
        int i2 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.specode2;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.specode3;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.specode4;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.specode5;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.grupcode;
        int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.mark;
        int hashCode7 = (hashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.paymentPlan;
        int hashCode8 = (hashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        ArrayList<Ambar> arrayList = this.ambar;
        int hashCode9 = (hashCode8 + (arrayList == null ? 0 : arrayList.hashCode())) * 31;
        ArrayList<ItemPrice> arrayList2 = this.itemPrices;
        int hashCode10 = (hashCode9 + (arrayList2 == null ? 0 : arrayList2.hashCode())) * 31;
        ArrayList<DetailItemUnit> arrayList3 = this.itemUnits;
        int hashCode11 = (hashCode10 + (arrayList3 == null ? 0 : arrayList3.hashCode())) * 31;
        ArrayList<MonthlyProductSales> arrayList4 = this.monthlyProductSalesList;
        int hashCode12 = (hashCode11 + (arrayList4 == null ? 0 : arrayList4.hashCode())) * 31;
        ArrayList<TopProduct> arrayList5 = this.topProducts;
        if (arrayList5 != null) {
            i2 = arrayList5.hashCode();
        }
        return hashCode12 + i2;
    }

    public String toString() {
        return "ProductDetail(specode1=" + this.specode1 + ", specode2=" + this.specode2 + ", specode3=" + this.specode3 + ", specode4=" + this.specode4 + ", specode5=" + this.specode5 + ", grupcode=" + this.grupcode + ", mark=" + this.mark + ", paymentPlan=" + this.paymentPlan + ", ambar=" + this.ambar + ", itemPrices=" + this.itemPrices + ", itemUnits=" + this.itemUnits + ", monthlyProductSalesList=" + this.monthlyProductSalesList + ", topProducts=" + this.topProducts + ')';
    }
    public ProductDetail(java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.util.ArrayList r22, java.util.ArrayList r23, java.util.ArrayList r24, java.util.ArrayList r25, java.util.ArrayList r26, int r27, kotlin.jvm.internal.DefaultConstructorMarker r28) {
        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.product.model.ProductDetail.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public String getSpecode1() {
        return this.specode1;
    }

    public void setSpecode1(String str) {
        this.specode1 = str;
    }

    public String getSpecode2() {
        return this.specode2;
    }

    public void setSpecode2(String str) {
        this.specode2 = str;
    }

    public String getSpecode3() {
        return this.specode3;
    }

    public void setSpecode3(String str) {
        this.specode3 = str;
    }

    public String getSpecode4() {
        return this.specode4;
    }

    public void setSpecode4(String str) {
        this.specode4 = str;
    }

    public String getSpecode5() {
        return this.specode5;
    }

    public void setSpecode5(String str) {
        this.specode5 = str;
    }

    public String getGrupcode() {
        return this.grupcode;
    }

    public void setGrupcode(String str) {
        this.grupcode = str;
    }

    public String getMark() {
        return this.mark;
    }

    public void setMark(String str) {
        this.mark = str;
    }

    public String getPaymentPlan() {
        return this.paymentPlan;
    }

    public void setPaymentPlan(String str) {
        this.paymentPlan = str;
    }

    public ArrayList<Ambar> getAmbar() {
        return this.ambar;
    }

    public void setAmbar(ArrayList<Ambar> arrayList) {
        this.ambar = arrayList;
    }

    public ArrayList<ItemPrice> getItemPrices() {
        return this.itemPrices;
    }

    public void setItemPrices(ArrayList<ItemPrice> arrayList) {
        this.itemPrices = arrayList;
    }

    public ArrayList<DetailItemUnit> getItemUnits() {
        return this.itemUnits;
    }

    public void setItemUnits(ArrayList<DetailItemUnit> arrayList) {
        this.itemUnits = arrayList;
    }

    public ArrayList<MonthlyProductSales> getMonthlyProductSalesList() {
        return this.monthlyProductSalesList;
    }

    public void setMonthlyProductSalesList(ArrayList<MonthlyProductSales> arrayList) {
        this.monthlyProductSalesList = arrayList;
    }

    public ArrayList<TopProduct> getTopProducts() {
        return this.topProducts;
    }

    public void setTopProducts(ArrayList<TopProduct> arrayList) {
        this.topProducts = arrayList;
    }

    public ProductDetail(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, ArrayList<Ambar> arrayList, ArrayList<ItemPrice> arrayList2, ArrayList<DetailItemUnit> arrayList3, ArrayList<MonthlyProductSales> arrayList4, ArrayList<TopProduct> arrayList5) {
        super(0, null, null, null, 0.0d, null, 0, 0.0d, 0.0d, false, 0.0d, null, null, 0, false, false, 0.0d, 0.0d, false, 0, null, null, 0, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0.0d, null, null, null, null, 0.0d, 0.0d, 0, 0, null, null, false, false, false, null, false, false, 0, null, false, 0, false, false, 0, null, 0, null, 0, 0, 0.0d, 0.0d, null, null, null, null, 0.0d, 0, 0, null, null, 0.0d, 0.0d, 0, null, null, false, 0, null, null, 0, 0.0d, null, null, false, null, 0.0d, -1, -1, 1048575, null);
        this.specode1 = str;
        this.specode2 = str2;
        this.specode3 = str3;
        this.specode4 = str4;
        this.specode5 = str5;
        this.grupcode = str6;
        this.mark = str7;
        this.paymentPlan = str8;
        this.ambar = arrayList;
        this.itemPrices = arrayList2;
        this.itemUnits = arrayList3;
        this.monthlyProductSalesList = arrayList4;
        this.topProducts = arrayList5;
    }

    public static final class Ambar {
        private String ambarName;
        private double fiiliStok;
        private double gercekStok;
        private String unitCode;
        private double variantActualStok;
        private String variantName;
        private double variantRealStok;
        private List<? extends ItemVariantStock> variantStocks;
        private String varintCode;

        public Ambar() {
            this(null, 0.0d, 0.0d, null, null, 0.0d, 0.0d, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
        }

        public String component1() {
            return this.ambarName;
        }

        public double component2() {
            return this.fiiliStok;
        }

        public double component3() {
            return this.gercekStok;
        }

        public String component4() {
            return this.unitCode;
        }

        public String component5() {
            return this.varintCode;
        }

        public double component6() {
            return this.variantActualStok;
        }

        public double component7() {
            return this.variantRealStok;
        }

        public String component8() {
            return this.variantName;
        }
        public List<ItemVariantStock> component9() {
            return (List<ItemVariantStock>) this.variantStocks;
        }

        public Ambar copy(String str, double d2, double d3, String str2, String str3, double d4, double d5, String str4, List<? extends ItemVariantStock> list) {
            return new Ambar(str, d2, d3, str2, str3, d4, d5, str4, list);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Ambar ambar)) {
                return false;
            }
            return Intrinsics.areEqual(this.ambarName, ambar.ambarName) && Double.compare(this.fiiliStok, ambar.fiiliStok) == 0 && Double.compare(this.gercekStok, ambar.gercekStok) == 0 && Intrinsics.areEqual(this.unitCode, ambar.unitCode) && Intrinsics.areEqual(this.varintCode, ambar.varintCode) && Double.compare(this.variantActualStok, ambar.variantActualStok) == 0 && Double.compare(this.variantRealStok, ambar.variantRealStok) == 0 && Intrinsics.areEqual(this.variantName, ambar.variantName) && Intrinsics.areEqual(this.variantStocks, ambar.variantStocks);
        }

        public int hashCode() {
            String str = this.ambarName;
            int i2 = 0;
            int hashCode = (((((str == null ? 0 : str.hashCode()) * 31) + Double.hashCode(this.fiiliStok)) * 31) + Double.hashCode(this.gercekStok)) * 31;
            String str2 = this.unitCode;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.varintCode;
            int hashCode3 = (((((hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + Double.hashCode(this.variantActualStok)) * 31) + Double.hashCode(this.variantRealStok)) * 31;
            String str4 = this.variantName;
            int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            List<? extends ItemVariantStock> list = this.variantStocks;
            if (list != null) {
                i2 = list.hashCode();
            }
            return hashCode4 + i2;
        }

        public String toString() {
            return "Ambar(ambarName=" + this.ambarName + ", fiiliStok=" + this.fiiliStok + ", gercekStok=" + this.gercekStok + ", unitCode=" + this.unitCode + ", varintCode=" + this.varintCode + ", variantActualStok=" + this.variantActualStok + ", variantRealStok=" + this.variantRealStok + ", variantName=" + this.variantName + ", variantStocks=" + this.variantStocks + ')';
        }

        public Ambar(String str, double d2, double d3, String str2, String str3, double d4, double d5, String str4, List<? extends ItemVariantStock> list) {
            this.ambarName = str;
            this.fiiliStok = d2;
            this.gercekStok = d3;
            this.unitCode = str2;
            this.varintCode = str3;
            this.variantActualStok = d4;
            this.variantRealStok = d5;
            this.variantName = str4;
            this.variantStocks = list;
        } 
        public Ambar(java.lang.String r15, double r16, double r18, java.lang.String r20, java.lang.String r21, double r22, double r24, java.lang.String r26, java.util.List r27, int r28, kotlin.jvm.internal.DefaultConstructorMarker r29) {
                throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.product.model.ProductDetail.Ambar.<init>(java.lang.String, double, double, java.lang.String, java.lang.String, double, double, java.lang.String, java.util.List, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public String getAmbarName() {
            return this.ambarName;
        }

        public void setAmbarName(String str) {
            this.ambarName = str;
        }

        public double getFiiliStok() {
            return this.fiiliStok;
        }

        public void setFiiliStok(double d2) {
            this.fiiliStok = d2;
        }

        public double getGercekStok() {
            return this.gercekStok;
        }

        public void setGercekStok(double d2) {
            this.gercekStok = d2;
        }

        public String getUnitCode() {
            return this.unitCode;
        }

        public void setUnitCode(String str) {
            this.unitCode = str;
        }

        public String getVarintCode() {
            return this.varintCode;
        }

        public void setVarintCode(String str) {
            this.varintCode = str;
        }

        public double getVariantActualStok() {
            return this.variantActualStok;
        }

        public void setVariantActualStok(double d2) {
            this.variantActualStok = d2;
        }

        public double getVariantRealStok() {
            return this.variantRealStok;
        }

        public void setVariantRealStok(double d2) {
            this.variantRealStok = d2;
        }

        public String getVariantName() {
            return this.variantName;
        }

        public void setVariantName(String str) {
            this.variantName = str;
        }
         public List<ItemVariantStock> getVariantStocks() {
            return (List<ItemVariantStock>) this.variantStocks;
        }

        public void setVariantStocks(List<? extends ItemVariantStock> list) {
            this.variantStocks = list;
        }
    }
    public static final class ItemPrice {
        private int begDate;
        private String cPrice;
        private String clientCode;
        private String clspeCode;
        private String clspeCode2;
        private String clspeCode3;
        private String clspeCode4;
        private String clspeCode5;
        private String cltradingGrp;
        private String clycphCode;
        private String cyphCode;
        private String definition;
        private int endDate;
        private String grpCode;
        private int leadtime;
        private int logRef;
        private int markRef;
        private int ordernr;
        private int payPlanRef;
        private double price;
        private String priceCode;
        private int priority;
        private String shipTyp;
        private String tradingGrp;
        private String variantCode;

        public ItemPrice() {
            this(0, null, 0.0d, null, null, null, null, null, 0, 0, 0, null, null, null, null, null, null, null, 0, null, 0, null, null, 0, 0, 33554431, null);
        }

        public int component1() {
            return this.logRef;
        }

        public int component10() {
            return this.ordernr;
        }

        public int component11() {
            return this.markRef;
        }

        public String component12() {
            return this.clientCode;
        }

        public String component13() {
            return this.clspeCode;
        }

        public String component14() {
            return this.clspeCode2;
        }

        public String component15() {
            return this.clspeCode3;
        }

        public String component16() {
            return this.clspeCode4;
        }

        public String component17() {
            return this.clspeCode5;
        }

        public String component18() {
            return this.clycphCode;
        }

        public int component19() {
            return this.payPlanRef;
        }

        public String component2() {
            return this.priceCode;
        }

        public String component20() {
            return this.tradingGrp;
        }

        public int component21() {
            return this.priority;
        }

        public String component22() {
            return this.definition;
        }

        public String component23() {
            return this.variantCode;
        }

        public int component24() {
            return this.begDate;
        }

        public int component25() {
            return this.endDate;
        }

        public double component3() {
            return this.price;
        }

        public String component4() {
            return this.cPrice;
        }

        public String component5() {
            return this.shipTyp;
        }

        public String component6() {
            return this.cyphCode;
        }

        public String component7() {
            return this.grpCode;
        }

        public String component8() {
            return this.cltradingGrp;
        }

        public int component9() {
            return this.leadtime;
        }

        public ItemPrice copy(int i2, String str, double d2, String str2, String str3, String str4, String str5, String str6, int i3, int i4, int i5, String str7, String str8, String str9, String str10, String str11, String str12, String str13, int i6, String str14, int i7, String str15, String str16, int i8, int i9) {
            return new ItemPrice(i2, str, d2, str2, str3, str4, str5, str6, i3, i4, i5, str7, str8, str9, str10, str11, str12, str13, i6, str14, i7, str15, str16, i8, i9);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ItemPrice itemPrice)) {
                return false;
            }
            return this.logRef == itemPrice.logRef && Intrinsics.areEqual(this.priceCode, itemPrice.priceCode) && Double.compare(this.price, itemPrice.price) == 0 && Intrinsics.areEqual(this.cPrice, itemPrice.cPrice) && Intrinsics.areEqual(this.shipTyp, itemPrice.shipTyp) && Intrinsics.areEqual(this.cyphCode, itemPrice.cyphCode) && Intrinsics.areEqual(this.grpCode, itemPrice.grpCode) && Intrinsics.areEqual(this.cltradingGrp, itemPrice.cltradingGrp) && this.leadtime == itemPrice.leadtime && this.ordernr == itemPrice.ordernr && this.markRef == itemPrice.markRef && Intrinsics.areEqual(this.clientCode, itemPrice.clientCode) && Intrinsics.areEqual(this.clspeCode, itemPrice.clspeCode) && Intrinsics.areEqual(this.clspeCode2, itemPrice.clspeCode2) && Intrinsics.areEqual(this.clspeCode3, itemPrice.clspeCode3) && Intrinsics.areEqual(this.clspeCode4, itemPrice.clspeCode4) && Intrinsics.areEqual(this.clspeCode5, itemPrice.clspeCode5) && Intrinsics.areEqual(this.clycphCode, itemPrice.clycphCode) && this.payPlanRef == itemPrice.payPlanRef && Intrinsics.areEqual(this.tradingGrp, itemPrice.tradingGrp) && this.priority == itemPrice.priority && Intrinsics.areEqual(this.definition, itemPrice.definition) && Intrinsics.areEqual(this.variantCode, itemPrice.variantCode) && this.begDate == itemPrice.begDate && this.endDate == itemPrice.endDate;
        }

        public int hashCode() {
            int hashCode = Integer.hashCode(this.logRef) * 31;
            String str = this.priceCode;
            int i2 = 0;
            int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + Double.hashCode(this.price)) * 31;
            String str2 = this.cPrice;
            int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.shipTyp;
            int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.cyphCode;
            int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.grpCode;
            int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.cltradingGrp;
            int hashCode7 = (((((((hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31) + Integer.hashCode(this.leadtime)) * 31) + Integer.hashCode(this.ordernr)) * 31) + Integer.hashCode(this.markRef)) * 31;
            String str7 = this.clientCode;
            int hashCode8 = (hashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
            String str8 = this.clspeCode;
            int hashCode9 = (hashCode8 + (str8 == null ? 0 : str8.hashCode())) * 31;
            String str9 = this.clspeCode2;
            int hashCode10 = (hashCode9 + (str9 == null ? 0 : str9.hashCode())) * 31;
            String str10 = this.clspeCode3;
            int hashCode11 = (hashCode10 + (str10 == null ? 0 : str10.hashCode())) * 31;
            String str11 = this.clspeCode4;
            int hashCode12 = (hashCode11 + (str11 == null ? 0 : str11.hashCode())) * 31;
            String str12 = this.clspeCode5;
            int hashCode13 = (hashCode12 + (str12 == null ? 0 : str12.hashCode())) * 31;
            String str13 = this.clycphCode;
            int hashCode14 = (((hashCode13 + (str13 == null ? 0 : str13.hashCode())) * 31) + Integer.hashCode(this.payPlanRef)) * 31;
            String str14 = this.tradingGrp;
            int hashCode15 = (((hashCode14 + (str14 == null ? 0 : str14.hashCode())) * 31) + Integer.hashCode(this.priority)) * 31;
            String str15 = this.definition;
            int hashCode16 = (hashCode15 + (str15 == null ? 0 : str15.hashCode())) * 31;
            String str16 = this.variantCode;
            if (str16 != null) {
                i2 = str16.hashCode();
            }
            return ((((hashCode16 + i2) * 31) + Integer.hashCode(this.begDate)) * 31) + Integer.hashCode(this.endDate);
        }

        public String toString() {
            return "ItemPrice(logRef=" + this.logRef + ", priceCode=" + this.priceCode + ", price=" + this.price + ", cPrice=" + this.cPrice + ", shipTyp=" + this.shipTyp + ", cyphCode=" + this.cyphCode + ", grpCode=" + this.grpCode + ", cltradingGrp=" + this.cltradingGrp + ", leadtime=" + this.leadtime + ", ordernr=" + this.ordernr + ", markRef=" + this.markRef + ", clientCode=" + this.clientCode + ", clspeCode=" + this.clspeCode + ", clspeCode2=" + this.clspeCode2 + ", clspeCode3=" + this.clspeCode3 + ", clspeCode4=" + this.clspeCode4 + ", clspeCode5=" + this.clspeCode5 + ", clycphCode=" + this.clycphCode + ", payPlanRef=" + this.payPlanRef + ", tradingGrp=" + this.tradingGrp + ", priority=" + this.priority + ", definition=" + this.definition + ", variantCode=" + this.variantCode + ", begDate=" + this.begDate + ", endDate=" + this.endDate + ')';
        }

        public ItemPrice(int i2, String str, double d2, String str2, String str3, String str4, String str5, String str6, int i3, int i4, int i5, String str7, String str8, String str9, String str10, String str11, String str12, String str13, int i6, String str14, int i7, String str15, String str16, int i8, int i9) {
            this.logRef = i2;
            this.priceCode = str;
            this.price = d2;
            this.cPrice = str2;
            this.shipTyp = str3;
            this.cyphCode = str4;
            this.grpCode = str5;
            this.cltradingGrp = str6;
            this.leadtime = i3;
            this.ordernr = i4;
            this.markRef = i5;
            this.clientCode = str7;
            this.clspeCode = str8;
            this.clspeCode2 = str9;
            this.clspeCode3 = str10;
            this.clspeCode4 = str11;
            this.clspeCode5 = str12;
            this.clycphCode = str13;
            this.payPlanRef = i6;
            this.tradingGrp = str14;
            this.priority = i7;
            this.definition = str15;
            this.variantCode = str16;
            this.begDate = i8;
            this.endDate = i9;
        }

        public ItemPrice(int i2, String str, double d2, String str2, String str3, String str4, String str5, String str6, int i3, int i4, int i5, String str7, String str8, String str9, String str10, String str11, String str12, String str13, int i6, String str14, int i7, String str15, String str16, int i8, int i9, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? 0 : i2, (i10 & 2) != 0 ? "" : str, (i10 & 4) != 0 ? 0.0d : d2, (i10 & 8) != 0 ? "" : str2, (i10 & 16) != 0 ? "" : str3, (i10 & 32) != 0 ? "" : str4, (i10 & 64) != 0 ? "" : str5, (i10 & 128) != 0 ? "" : str6, (i10 & 256) != 0 ? 0 : i3, (i10 & 512) != 0 ? 0 : i4, (i10 & 1024) != 0 ? 0 : i5, (i10 & 2048) != 0 ? "" : str7, (i10 & 4096) != 0 ? "" : str8, (i10 & 8192) != 0 ? "" : str9, (i10 & 16384) != 0 ? "" : str10, (i10 & 32768) != 0 ? "" : str11, (i10 & 65536) != 0 ? "" : str12, (i10 & 131072) != 0 ? "" : str13, (i10 & 262144) != 0 ? 0 : i6, (i10 & 524288) != 0 ? "" : str14, (i10 & 1048576) != 0 ? 0 : i7, (i10 & 2097152) != 0 ? "" : str15, (i10 & 4194304) != 0 ? "" : str16, (i10 & 8388608) != 0 ? 0 : i8, (i10 & 16777216) != 0 ? 0 : i9);
        }

        public int getLogRef() {
            return this.logRef;
        }

        public void setLogRef(int i2) {
            this.logRef = i2;
        }

        public String getPriceCode() {
            return this.priceCode;
        }

        public void setPriceCode(String str) {
            this.priceCode = str;
        }

        public double getPrice() {
            return this.price;
        }

        public void setPrice(double d2) {
            this.price = d2;
        }

        public String getCPrice() {
            return this.cPrice;
        }

        public void setCPrice(String str) {
            this.cPrice = str;
        }

        public String getShipTyp() {
            return this.shipTyp;
        }

        public void setShipTyp(String str) {
            this.shipTyp = str;
        }

        public String getCyphCode() {
            return this.cyphCode;
        }

        public void setCyphCode(String str) {
            this.cyphCode = str;
        }

        public String getGrpCode() {
            return this.grpCode;
        }

        public void setGrpCode(String str) {
            this.grpCode = str;
        }

        public String getCltradingGrp() {
            return this.cltradingGrp;
        }

        public void setCltradingGrp(String str) {
            this.cltradingGrp = str;
        }

        public int getLeadtime() {
            return this.leadtime;
        }

        public void setLeadtime(int i2) {
            this.leadtime = i2;
        }

        public int getOrdernr() {
            return this.ordernr;
        }

        public void setOrdernr(int i2) {
            this.ordernr = i2;
        }

        public int getMarkRef() {
            return this.markRef;
        }

        public void setMarkRef(int i2) {
            this.markRef = i2;
        }

        public String getClientCode() {
            return this.clientCode;
        }

        public void setClientCode(String str) {
            this.clientCode = str;
        }

        public String getClspeCode() {
            return this.clspeCode;
        }

        public void setClspeCode(String str) {
            this.clspeCode = str;
        }

        public String getClspeCode2() {
            return this.clspeCode2;
        }

        public void setClspeCode2(String str) {
            this.clspeCode2 = str;
        }

        public String getClspeCode3() {
            return this.clspeCode3;
        }

        public void setClspeCode3(String str) {
            this.clspeCode3 = str;
        }

        public String getClspeCode4() {
            return this.clspeCode4;
        }

        public void setClspeCode4(String str) {
            this.clspeCode4 = str;
        }

        public String getClspeCode5() {
            return this.clspeCode5;
        }

        public void setClspeCode5(String str) {
            this.clspeCode5 = str;
        }

        public String getClycphCode() {
            return this.clycphCode;
        }

        public void setClycphCode(String str) {
            this.clycphCode = str;
        }

        public int getPayPlanRef() {
            return this.payPlanRef;
        }

        public void setPayPlanRef(int i2) {
            this.payPlanRef = i2;
        }

        public String getTradingGrp() {
            return this.tradingGrp;
        }

        public void setTradingGrp(String str) {
            this.tradingGrp = str;
        }

        public int getPriority() {
            return this.priority;
        }

        public void setPriority(int i2) {
            this.priority = i2;
        }

        public String getDefinition() {
            return this.definition;
        }

        public void setDefinition(String str) {
            this.definition = str;
        }

        public String getVariantCode() {
            return this.variantCode;
        }

        public void setVariantCode(String str) {
            this.variantCode = str;
        }

        public int getBegDate() {
            return this.begDate;
        }

        public void setBegDate(int i2) {
            this.begDate = i2;
        }

        public int getEndDate() {
            return this.endDate;
        }

        public void setEndDate(int i2) {
            this.endDate = i2;
        }
    }
    public static final class DetailItemUnit {
        private String area;
        private List<String> barcode;
        private String convfact1;
        private String convfact2;
        private String grossVolume;
        private String grossWeight;
        private String height;
        private String itemCode;
        private String length;
        private String unitCode;
        private String unitDesc;
        private String volume;
        private String weight;
        private String width;

        public DetailItemUnit() {
            this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, 16383, null);
        }

        public String component1() {
            return this.itemCode;
        }

        public String component10() {
            return this.grossVolume;
        }

        public String component11() {
            return this.volume;
        }

        public String component12() {
            return this.grossWeight;
        }

        public String component13() {
            return this.weight;
        }

        public List<String> component14() {
            return this.barcode;
        }

        public String component2() {
            return this.unitCode;
        }

        public String component3() {
            return this.convfact1;
        }

        public String component4() {
            return this.convfact2;
        }

        public String component5() {
            return this.unitDesc;
        }

        public String component6() {
            return this.width;
        }

        public String component7() {
            return this.length;
        }

        public String component8() {
            return this.height;
        }

        public String component9() {
            return this.area;
        }

        public DetailItemUnit copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, List<String> list) {
            return new DetailItemUnit(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, list);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DetailItemUnit detailItemUnit)) {
                return false;
            }
            return Intrinsics.areEqual(this.itemCode, detailItemUnit.itemCode) && Intrinsics.areEqual(this.unitCode, detailItemUnit.unitCode) && Intrinsics.areEqual(this.convfact1, detailItemUnit.convfact1) && Intrinsics.areEqual(this.convfact2, detailItemUnit.convfact2) && Intrinsics.areEqual(this.unitDesc, detailItemUnit.unitDesc) && Intrinsics.areEqual(this.width, detailItemUnit.width) && Intrinsics.areEqual(this.length, detailItemUnit.length) && Intrinsics.areEqual(this.height, detailItemUnit.height) && Intrinsics.areEqual(this.area, detailItemUnit.area) && Intrinsics.areEqual(this.grossVolume, detailItemUnit.grossVolume) && Intrinsics.areEqual(this.volume, detailItemUnit.volume) && Intrinsics.areEqual(this.grossWeight, detailItemUnit.grossWeight) && Intrinsics.areEqual(this.weight, detailItemUnit.weight) && Intrinsics.areEqual(this.barcode, detailItemUnit.barcode);
        }

        public int hashCode() {
            String str = this.itemCode;
            int i2 = 0;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.unitCode;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.convfact1;
            int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.convfact2;
            int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.unitDesc;
            int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.width;
            int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
            String str7 = this.length;
            int hashCode7 = (hashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
            String str8 = this.height;
            int hashCode8 = (hashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
            String str9 = this.area;
            int hashCode9 = (hashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
            String str10 = this.grossVolume;
            int hashCode10 = (hashCode9 + (str10 == null ? 0 : str10.hashCode())) * 31;
            String str11 = this.volume;
            int hashCode11 = (hashCode10 + (str11 == null ? 0 : str11.hashCode())) * 31;
            String str12 = this.grossWeight;
            int hashCode12 = (hashCode11 + (str12 == null ? 0 : str12.hashCode())) * 31;
            String str13 = this.weight;
            int hashCode13 = (hashCode12 + (str13 == null ? 0 : str13.hashCode())) * 31;
            List<String> list = this.barcode;
            if (list != null) {
                i2 = list.hashCode();
            }
            return hashCode13 + i2;
        }

        public String toString() {
            return "DetailItemUnit(itemCode=" + this.itemCode + ", unitCode=" + this.unitCode + ", convfact1=" + this.convfact1 + ", convfact2=" + this.convfact2 + ", unitDesc=" + this.unitDesc + ", width=" + this.width + ", length=" + this.length + ", height=" + this.height + ", area=" + this.area + ", grossVolume=" + this.grossVolume + ", volume=" + this.volume + ", grossWeight=" + this.grossWeight + ", weight=" + this.weight + ", barcode=" + this.barcode + ')';
        }

        public DetailItemUnit(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, List<String> list) {
            this.itemCode = str;
            this.unitCode = str2;
            this.convfact1 = str3;
            this.convfact2 = str4;
            this.unitDesc = str5;
            this.width = str6;
            this.length = str7;
            this.height = str8;
            this.area = str9;
            this.grossVolume = str10;
            this.volume = str11;
            this.grossWeight = str12;
            this.weight = str13;
            this.barcode = list;
        }

        public DetailItemUnit(java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, java.lang.String r28, java.util.List r29, int r30, kotlin.jvm.internal.DefaultConstructorMarker r31) {
            throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.product.model.ProductDetail.DetailItemUnit.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public String getItemCode() {
            return this.itemCode;
        }

        public void setItemCode(String str) {
            this.itemCode = str;
        }

        public String getUnitCode() {
            return this.unitCode;
        }

        public void setUnitCode(String str) {
            this.unitCode = str;
        }

        public String getConvfact1() {
            return this.convfact1;
        }

        public void setConvfact1(String str) {
            this.convfact1 = str;
        }

        public String getConvfact2() {
            return this.convfact2;
        }

        public void setConvfact2(String str) {
            this.convfact2 = str;
        }

        public String getUnitDesc() {
            return this.unitDesc;
        }

        public void setUnitDesc(String str) {
            this.unitDesc = str;
        }

        public String getWidth() {
            return this.width;
        }

        public void setWidth(String str) {
            this.width = str;
        }

        public String getLength() {
            return this.length;
        }

        public void setLength(String str) {
            this.length = str;
        }

        public String getHeight() {
            return this.height;
        }

        public void setHeight(String str) {
            this.height = str;
        }

        public String getArea() {
            return this.area;
        }

        public void setArea(String str) {
            this.area = str;
        }

        public String getGrossVolume() {
            return this.grossVolume;
        }

        public void setGrossVolume(String str) {
            this.grossVolume = str;
        }

        public String getVolume() {
            return this.volume;
        }

        public void setVolume(String str) {
            this.volume = str;
        }

        public String getGrossWeight() {
            return this.grossWeight;
        }

        public void setGrossWeight(String str) {
            this.grossWeight = str;
        }

        public String getWeight() {
            return this.weight;
        }

        public void setWeight(String str) {
            this.weight = str;
        }

        public List<String> getBarcode() {
            return this.barcode;
        }

        public void setBarcode(List<String> list) {
            this.barcode = list;
        }
    }
}
