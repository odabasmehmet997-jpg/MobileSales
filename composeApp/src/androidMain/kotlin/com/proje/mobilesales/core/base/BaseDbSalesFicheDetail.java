package com.proje.mobilesales.core.base;

import com.proje.mobilesales.core.interfaces.ConvertSalesDetail;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;
public abstract class BaseDbSalesFicheDetail implements ConvertSalesDetail {
    public ArrayList<String> ficheDetailBarcodes;
    public String itemCode;
    public int lineType;
    public int logicalRef;
    public String priceCode;
    public int priceRef;
    public int salesFicheId;
    private double actualStock;
    private int addTexRef;
    private double amount;
    private double amountTotal;
    private String campCode;
    private String campCode2;
    private String campCode3;
    private String campCode4;
    private String campCode5;
    private String campLineNo;
    private double convFact1;
    private double convFact2;
    private String curCode;
    private String custDiscCamp;
    private String custDiscGuid;
    private double custDiscRatio;
    private String desc;
    private String discCamp1;
    private String discCamp2;
    private String discCamp3;
    private String discCamp4;
    private String discCamp5;
    private String discCampLineNo1;
    private String discCampLineNo2;
    private String discCampLineNo3;
    private String discCampLineNo4;
    private String discCampLineNo5;
    private String discCardCode1;
    private String discCardCode2;
    private String discCardCode3;
    private String discCardCode4;
    private String discCardCode5;
    private String discGuid1;
    private String discGuid2;
    private String discGuid3;
    private String discGuid4;
    private String discGuid5;
    private double discRatio1;
    private double discRatio2;
    private double discRatio3;
    private double discRatio4;
    private double discRatio5;
    private double discTotal;
    private double discTotal1;
    private double discTotal2;
    private double discTotal3;
    private double discTotal4;
    private double discTotal5;
    private String dueDate;
    private int foundByStoredProcedure;
    private int globalLineType;
    private String guid;
    private int isNotUnitChange;
    private int itemRef;
    private int lineIndex;
    private double lineNet;
    private int lineNr;
    private int muhRefCode;
    private int paymentRef;
    private double prPrice;
    private int prcurr;
    private int previousPriceRef;
    private double price;
    private String priceDef;
    private int product;
    private double prrate;
    private int salesType;
    private double secondAmount;
    private int secondUnit;
    private String secondUnitCode;
    private String speCode;
    private double surplusAmount;
    private double total;
    private int unit;
    private String unitCode;
    private String variantCode;
    private int variantRef;
    private double vat;
    private double vatAmnt;
    private int vatInc;
    private double vatMatrah;
    private String warehouseName;

    private String paymentCode;

    protected BaseDbSalesFicheDetail() {
        this (0, 0, 0, 0, null, 0.0d, 0.0d, 0, null, 0.0d, 0.0d, 0, null, 0.0d, 0, 0, null, null, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0, 0, null, 0, null, null, 0, 0, 0.0d, 0.0d, 0.0d, 0.0d, 0, null, null, 0, 0, 0, null, null, null, null, null, null, null, null, null, null, 0.0d, 0, null, 0, 0, null, 0.0d, null, null, -1, -1, 67108863, null);
    }
    protected BaseDbSalesFicheDetail(final int i, final int i2, final int i3, final int i4, final String str, final double d, final double d2, final int i5, final String str2, final double d3, final double d4, final int i6, final String str3, final double d5, final int i7, final int i8, final String str4, final String str5, final String str6, final double d6, final double d7, final double d8, final double d9, final double d10, final double d11, final double d12, final double d13, final double d14, final double d15, final String str7, final String str8, final String str9, final String str10, final String str11, final String str12, final String str13, final String str14, final String str15, final String str16, final String str17, final String str18, final String str19, final String str20, final String str21, final String str22, final String str23, final double d16, final double d17, final double d18, final double d19, final double d20, final double d21, final int i9, final int i10, final int i11, final String str24, final int i12, final String str25, final String str26, final int i13, final int i14, final double d22, final double d23, final double d24, final double d25, final int i15, final String str27, final String str28, final int i16, final int i17, final int i18, final String str29, final String str30, final String str31, final String str32, final String str33, final String str34, final String str35, final String str36, final String str37, final String str38, final double d26, final int i19, final String str39, final int i20, final int i21, final String str40, final double d27, final ArrayList<String> arrayList, final String str41) {
        Intrinsics.checkNotNullParameter (str28, "campCode");
        Intrinsics.checkNotNullParameter (str29, "campCode2");
        Intrinsics.checkNotNullParameter (str30, "campCode3");
        Intrinsics.checkNotNullParameter (str31, "campCode4");
        Intrinsics.checkNotNullParameter (str32, "campCode5");
        Intrinsics.checkNotNullParameter (str33, "campLineNo");
        Intrinsics.checkNotNullParameter (arrayList, "ficheDetailBarcodes");
        logicalRef = i;
        salesFicheId = i2;
        lineType = i3;
        itemRef = i4;
        itemCode = str;
        vat = d;
        amount = d2;
        unit = i5;
        unitCode = str2;
        price = d3;
        prPrice = d4;
        prcurr = i6;
        curCode = str3;
        prrate = d5;
        vatInc = i7;
        paymentRef = i8;
        paymentCode = str4;
        speCode = str5;
        desc = str6;
        discTotal1 = d6;
        discRatio1 = d7;
        discTotal2 = d8;
        discRatio2 = d9;
        discTotal3 = d10;
        discRatio3 = d11;
        discTotal4 = d12;
        discRatio4 = d13;
        discTotal5 = d14;
        discRatio5 = d15;
        discCardCode1 = str7;
        discCardCode2 = str8;
        discCardCode3 = str9;
        discCardCode4 = str10;
        discCardCode5 = str11;
        discGuid1 = str12;
        discGuid2 = str13;
        discGuid3 = str14;
        discGuid4 = str15;
        discGuid5 = str16;
        custDiscGuid = str17;
        discCamp1 = str18;
        discCamp2 = str19;
        discCamp3 = str20;
        discCamp4 = str21;
        discCamp5 = str22;
        custDiscCamp = str23;
        total = d16;
        vatMatrah = d17;
        vatAmnt = d18;
        discTotal = d19;
        amountTotal = d20;
        lineNet = d21;
        lineIndex = i9;
        lineNr = i10;
        variantRef = i11;
        variantCode = str24;
        priceRef = i12;
        priceCode = str25;
        priceDef = str26;
        salesType = i13;
        product = i14;
        actualStock = d22;
        convFact1 = d23;
        convFact2 = d24;
        custDiscRatio = d25;
        isNotUnitChange = i15;
        guid = str27;
        campCode = str28;
        globalLineType = i16;
        muhRefCode = i17;
        addTexRef = i18;
        campCode2 = str29;
        campCode3 = str30;
        campCode4 = str31;
        campCode5 = str32;
        campLineNo = str33;
        discCampLineNo1 = str34;
        discCampLineNo2 = str35;
        discCampLineNo3 = str36;
        discCampLineNo4 = str37;
        discCampLineNo5 = str38;
        secondAmount = d26;
        secondUnit = i19;
        secondUnitCode = str39;
        previousPriceRef = i20;
        foundByStoredProcedure = i21;
        dueDate = str40;
        surplusAmount = d27;
        ficheDetailBarcodes = arrayList;
        warehouseName = str41;
    }
    protected BaseDbSalesFicheDetail(final int r117, final int r118, final int r119, final int r120, final String r121, final double r122, final double r124, final int r126, final String r127, final double r128, final double r130, final int r132, final String r133, final double r134, final int r136, final int r137, final String r138, final String r139, final String r140, final double r141, final double r143, final double r145, final double r147, final double r149, final double r151, final double r153, final double r155, final double r157, final double r159, final String r161, final String r162, final String r163, final String r164, final String r165, final String r166, final String r167, final String r168, final String r169, final String r170, final String r171, final String r172, final String r173, final String r174, final String r175, final String r176, final String r177, final double r178, final double r180, final double r182, final double r184, final double r186, final double r188, final int r190, final int r191, final int r192, final String r193, final int r194, final String r195, final String r196, final int r197, final int r198, final double r199, final double r201, final double r203, final double r205, final int r207, final String r208, final String r209, final int r210, final int r211, final int r212, final String r213, final String r214, final String r215, final String r216, final String r217, final String r218, final String r219, final String r220, final String r221, final String r222, final double r223, final int r225, final String r226, final int r227, final int r228, final String r229, final double r230, final ArrayList r232, final String r233, final int r234, final int r235, final int r236, final kotlin.jvm.internal.DefaultConstructorMarker r237) {
        throw new UnsupportedOperationException (" com.proje.mobilesales.core.base.BaseDbSalesFicheDetail.<init>(int, int, int, int, java.lang.String, double, double, int, java.lang.String, double, double, int, java.lang.String, double, int, int, java.lang.String, java.lang.String, java.lang.String, double, double, double, double, double, double, double, double, double, double, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, double, double, double, double, double, double, int, int, int, java.lang.String, int, java.lang.String, java.lang.String, int, int, double, double, double, double, int, java.lang.String, java.lang.String, int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, double, int, java.lang.String, int, int, java.lang.String, double, java.util.ArrayList, java.lang.String, int, int, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final int getItemRef() {
        return itemRef;
    }

    public final void setItemRef(final int i) {
        itemRef = i;
    }

    public final double getVat() {
        return vat;
    }

    public final void setVat(final double d) {
        vat = d;
    }

    public final double getAmount() {
        return amount;
    }

    public final void setAmount(final double d) {
        amount = d;
    }

    public final int getUnit() {
        return unit;
    }

    public final void setUnit(final int i) {
        unit = i;
    }

    public final String getUnitCode() {
        return unitCode;
    }

    public final void setUnitCode(final String str) {
        unitCode = str;
    }

    public final double getPrice() {
        return price;
    }

    public final void setPrice(final double d) {
        price = d;
    }

    public final double getPrPrice() {
        return prPrice;
    }

    public final void setPrPrice(final double d) {
        prPrice = d;
    }

    public final int getPrcurr() {
        return prcurr;
    }

    public final void setPrcurr(final int i) {
        prcurr = i;
    }

    public final String getCurCode() {
        return curCode;
    }

    public final void setCurCode(final String str) {
        curCode = str;
    }

    public final double getPrrate() {
        return prrate;
    }

    public final void setPrrate(final double d) {
        prrate = d;
    }

    public final int getVatInc() {
        return vatInc;
    }

    public final void setVatInc(final int i) {
        vatInc = i;
    }

    public final int getPaymentRef() {
        return paymentRef;
    }

    public final void setPaymentRef(final int i) {
        paymentRef = i;
    }

    public final String getPaymentCode() {
        return paymentCode;
    }

    public final void setPaymentCode(final String str) {
        paymentCode = str;
    }

    public final String getSpeCode() {
        return speCode;
    }

    public final void setSpeCode(final String str) {
        speCode = str;
    }

    public final String getDesc() {
        return desc;
    }

    public final void setDesc(final String str) {
        desc = str;
    }

    public final double getDiscTotal1() {
        return discTotal1;
    }

    public final void setDiscTotal1(final double d) {
        discTotal1 = d;
    }

    public final double getDiscRatio1() {
        return discRatio1;
    }

    public final void setDiscRatio1(final double d) {
        discRatio1 = d;
    }

    public final double getDiscTotal2() {
        return discTotal2;
    }

    public final void setDiscTotal2(final double d) {
        discTotal2 = d;
    }

    public final double getDiscRatio2() {
        return discRatio2;
    }

    public final void setDiscRatio2(final double d) {
        discRatio2 = d;
    }

    public final double getDiscTotal3() {
        return discTotal3;
    }

    public final void setDiscTotal3(final double d) {
        discTotal3 = d;
    }

    public final double getDiscRatio3() {
        return discRatio3;
    }

    public final void setDiscRatio3(final double d) {
        discRatio3 = d;
    }

    public final double getDiscTotal4() {
        return discTotal4;
    }

    public final void setDiscTotal4(final double d) {
        discTotal4 = d;
    }

    public final double getDiscRatio4() {
        return discRatio4;
    }

    public final void setDiscRatio4(final double d) {
        discRatio4 = d;
    }

    public final double getDiscTotal5() {
        return discTotal5;
    }

    public final void setDiscTotal5(final double d) {
        discTotal5 = d;
    }

    public final double getDiscRatio5() {
        return discRatio5;
    }

    public final void setDiscRatio5(final double d) {
        discRatio5 = d;
    }

    public final String getDiscCardCode1() {
        return discCardCode1;
    }

    public final void setDiscCardCode1(final String str) {
        discCardCode1 = str;
    }

    public final String getDiscCardCode2() {
        return discCardCode2;
    }

    public final void setDiscCardCode2(final String str) {
        discCardCode2 = str;
    }

    public final String getDiscCardCode3() {
        return discCardCode3;
    }

    public final void setDiscCardCode3(final String str) {
        discCardCode3 = str;
    }

    public final String getDiscCardCode4() {
        return discCardCode4;
    }

    public final void setDiscCardCode4(final String str) {
        discCardCode4 = str;
    }

    public final String getDiscCardCode5() {
        return discCardCode5;
    }

    public final void setDiscCardCode5(final String str) {
        discCardCode5 = str;
    }

    public final String getDiscGuid1() {
        return discGuid1;
    }

    public final void setDiscGuid1(final String str) {
        discGuid1 = str;
    }

    public final String getDiscGuid2() {
        return discGuid2;
    }

    public final void setDiscGuid2(final String str) {
        discGuid2 = str;
    }

    public final String getDiscGuid3() {
        return discGuid3;
    }

    public final void setDiscGuid3(final String str) {
        discGuid3 = str;
    }

    public final String getDiscGuid4() {
        return discGuid4;
    }

    public final void setDiscGuid4(final String str) {
        discGuid4 = str;
    }

    public final String getDiscGuid5() {
        return discGuid5;
    }

    public final void setDiscGuid5(final String str) {
        discGuid5 = str;
    }

    public final String getCustDiscGuid() {
        return custDiscGuid;
    }

    public final void setCustDiscGuid(final String str) {
        custDiscGuid = str;
    }

    public final String getDiscCamp1() {
        return discCamp1;
    }

    public final void setDiscCamp1(final String str) {
        discCamp1 = str;
    }

    public final String getDiscCamp2() {
        return discCamp2;
    }

    public final void setDiscCamp2(final String str) {
        discCamp2 = str;
    }

    public final String getDiscCamp3() {
        return discCamp3;
    }

    public final void setDiscCamp3(final String str) {
        discCamp3 = str;
    }

    public final String getDiscCamp4() {
        return discCamp4;
    }

    public final void setDiscCamp4(final String str) {
        discCamp4 = str;
    }

    public final String getDiscCamp5() {
        return discCamp5;
    }

    public final void setDiscCamp5(final String str) {
        discCamp5 = str;
    }

    public final String getCustDiscCamp() {
        return custDiscCamp;
    }

    public final void setCustDiscCamp(final String str) {
        custDiscCamp = str;
    }

    public final double getTotal() {
        return total;
    }

    public final void setTotal(final double d) {
        total = d;
    }

    public final double getVatMatrah() {
        return vatMatrah;
    }

    public final void setVatMatrah(final double d) {
        vatMatrah = d;
    }

    public final double getVatAmnt() {
        return vatAmnt;
    }

    public final void setVatAmnt(final double d) {
        vatAmnt = d;
    }

    public final double getDiscTotal() {
        return discTotal;
    }

    public final void setDiscTotal(final double d) {
        discTotal = d;
    }

    public final double getAmountTotal() {
        return amountTotal;
    }

    public final void setAmountTotal(final double d) {
        amountTotal = d;
    }

    public final double getLineNet() {
        return lineNet;
    }

    public final void setLineNet(final double d) {
        lineNet = d;
    }

    public final int getLineIndex() {
        return lineIndex;
    }

    public final void setLineIndex(final int i) {
        lineIndex = i;
    }

    public final int getLineNr() {
        return lineNr;
    }

    public final void setLineNr(final int i) {
        lineNr = i;
    }

    public final int getVariantRef() {
        return variantRef;
    }

    public final void setVariantRef(final int i) {
        variantRef = i;
    }

    public final String getVariantCode() {
        return variantCode;
    }

    public final void setVariantCode(final String str) {
        variantCode = str;
    }

    public final String getPriceDef() {
        return priceDef;
    }

    public final void setPriceDef(final String str) {
        priceDef = str;
    }

    public final int getSalesType() {
        return salesType;
    }

    public final void setSalesType(final int i) {
        salesType = i;
    }

    public final int getProduct() {
        return product;
    }

    public final void setProduct(final int i) {
        product = i;
    }

    public final double getActualStock() {
        return actualStock;
    }

    public final void setActualStock(final double d) {
        actualStock = d;
    }

    public final double getConvFact1() {
        return convFact1;
    }

    public final void setConvFact1(final double d) {
        convFact1 = d;
    }

    public final double getConvFact2() {
        return convFact2;
    }

    public final void setConvFact2(final double d) {
        convFact2 = d;
    }

    public final double getCustDiscRatio() {
        return custDiscRatio;
    }

    public final void setCustDiscRatio(final double d) {
        custDiscRatio = d;
    }

    public final int isNotUnitChange() {
        return isNotUnitChange;
    }

    public final void setNotUnitChange(final int i) {
        isNotUnitChange = i;
    }

    public final String getGuid() {
        return guid;
    }

    public final void setGuid(final String str) {
        guid = str;
    }

    public final String getCampCode() {
        return campCode;
    }

    public final void setCampCode(final String str) {
        Intrinsics.checkNotNullParameter (str, "<set-?>");
        campCode = str;
    }

    public final int getGlobalLineType() {
        return globalLineType;
    }

    public final void setGlobalLineType(final int i) {
        globalLineType = i;
    }

    public final int getMuhRefCode() {
        return muhRefCode;
    }

    public final void setMuhRefCode(final int i) {
        muhRefCode = i;
    }

    public final int getAddTexRef() {
        return addTexRef;
    }

    public final void setAddTexRef(final int i) {
        addTexRef = i;
    }

    public final String getCampCode2() {
        return campCode2;
    }

    public final void setCampCode2(final String str) {
        Intrinsics.checkNotNullParameter (str, "<set-?>");
        campCode2 = str;
    }

    public final String getCampCode3() {
        return campCode3;
    }

    public final void setCampCode3(final String str) {
        Intrinsics.checkNotNullParameter (str, "<set-?>");
        campCode3 = str;
    }

    public final String getCampCode4() {
        return campCode4;
    }

    public final void setCampCode4(final String str) {
        Intrinsics.checkNotNullParameter (str, "<set-?>");
        campCode4 = str;
    }

    public final String getCampCode5() {
        return campCode5;
    }

    public final void setCampCode5(final String str) {
        Intrinsics.checkNotNullParameter (str, "<set-?>");
        campCode5 = str;
    }

    public final String getCampLineNo() {
        return campLineNo;
    }

    public final void setCampLineNo(final String str) {
        Intrinsics.checkNotNullParameter (str, "<set-?>");
        campLineNo = str;
    }

    public final String getDiscCampLineNo1() {
        return discCampLineNo1;
    }

    public final void setDiscCampLineNo1(final String str) {
        discCampLineNo1 = str;
    }

    public final String getDiscCampLineNo2() {
        return discCampLineNo2;
    }

    public final void setDiscCampLineNo2(final String str) {
        discCampLineNo2 = str;
    }

    public final String getDiscCampLineNo3() {
        return discCampLineNo3;
    }

    public final void setDiscCampLineNo3(final String str) {
        discCampLineNo3 = str;
    }

    public final String getDiscCampLineNo4() {
        return discCampLineNo4;
    }

    public final void setDiscCampLineNo4(final String str) {
        discCampLineNo4 = str;
    }

    public final String getDiscCampLineNo5() {
        return discCampLineNo5;
    }

    public final void setDiscCampLineNo5(final String str) {
        discCampLineNo5 = str;
    }

    public final double getSecondAmount() {
        return secondAmount;
    }

    public final void setSecondAmount(final double d) {
        secondAmount = d;
    }

    public final int getSecondUnit() {
        return secondUnit;
    }

    public final void setSecondUnit(final int i) {
        secondUnit = i;
    }

    public final String getSecondUnitCode() {
        return secondUnitCode;
    }

    public final void setSecondUnitCode(final String str) {
        secondUnitCode = str;
    }

    public final int getPreviousPriceRef() {
        return previousPriceRef;
    }

    public final void setPreviousPriceRef(final int i) {
        previousPriceRef = i;
    }

    public final int getFoundByStoredProcedure() {
        return foundByStoredProcedure;
    }

    public final void setFoundByStoredProcedure(final int i) {
        foundByStoredProcedure = i;
    }

    public final String getDueDate() {
        return dueDate;
    }

    public final void setDueDate(final String str) {
        dueDate = str;
    }

    public final double getSurplusAmount() {
        return surplusAmount;
    }

    public final void setSurplusAmount(final double d) {
        surplusAmount = d;
    }

    public final String getWarehouseName() {
        return warehouseName;
    }

    public final void setWarehouseName(final String str) {
        warehouseName = str;
    }

    public void convertSalesDetail(final SalesDetail salesDetail, final int i) {
        Intrinsics.checkNotNullParameter (salesDetail, "salesDetail");
        logicalRef = salesDetail.getLogicalRef ();
        salesFicheId = salesDetail.getSalesFicheId ();
        salesType = salesDetail.getSalesType ();
        lineType = salesDetail.lineType;
        itemRef = salesDetail.getItemRef ();
        itemCode = salesDetail.getCode ();
        warehouseName = salesDetail.getWareHouse ().getDefinition ();
        vat = salesDetail.getVat ().getDefinitionDouble ();
        amount = salesDetail.getAmount ().getDefinitionDouble ();
        unit = salesDetail.getUnit ().getLogicalRef ();
        unitCode = salesDetail.getUnit ().getCode ();
        price = salesDetail.getCalculateCurrPrice ();
        prPrice = salesDetail.getUsePrice ();
        prcurr = salesDetail.prCurrType;
        curCode = salesDetail.curCodeStr;
        prrate = salesDetail.getPrRate ();
        vatInc = StringUtils.convertBooleanToInt (Boolean.valueOf (salesDetail.getIncludeVat ().isSelect ()));
        paymentRef = salesDetail.getPayment ().getLogicalRef ();
        paymentCode = salesDetail.getPayment ().getCode ();
        speCode = salesDetail.getSpeCode ().toString ();
        variantRef = salesDetail.getVariant ().getLogicalRef ();
        variantCode = salesDetail.getVariant ().getCode ();
        priceRef = salesDetail.getSelectedPrice ().getLogicalRef ();
        priceCode = salesDetail.getSelectedPrice ().getCode ();
        priceDef = salesDetail.getSelectedPrice ().getDefinition ();
        desc = salesDetail.getExplanation ().toString ();
        discRatio1 = salesDetail.getDiscountRatio (0).getDefinitionDouble ();
        discTotal1 = salesDetail.getDiscountTotal (0).getDefinitionDouble ();
        discCardCode1 = salesDetail.getDiscountCard (0).getCode ();
        discGuid1 = salesDetail.getDiscountGuid (0);
        discCamp1 = salesDetail.getDiscountCampaign (0);
        discCampLineNo1 = salesDetail.getDiscountCampaignLineNo (0);
        discRatio2 = salesDetail.getDiscountRatio (1).getDefinitionDouble ();
        discTotal2 = salesDetail.getDiscountTotal (1).getDefinitionDouble ();
        discCardCode2 = salesDetail.getDiscountCard (1).getCode ();
        discGuid2 = salesDetail.getDiscountGuid (1);
        discCamp2 = salesDetail.getDiscountCampaign (1);
        discCampLineNo2 = salesDetail.getDiscountCampaignLineNo (1);
        discRatio3 = salesDetail.getDiscountRatio (2).getDefinitionDouble ();
        discTotal3 = salesDetail.getDiscountTotal (2).getDefinitionDouble ();
        discCardCode3 = salesDetail.getDiscountCard (2).getCode ();
        discGuid3 = salesDetail.getDiscountGuid (2);
        discCamp3 = salesDetail.getDiscountCampaign (2);
        discCampLineNo3 = salesDetail.getDiscountCampaignLineNo (2);
        discRatio4 = salesDetail.getDiscountRatio (3).getDefinitionDouble ();
        discTotal4 = salesDetail.getDiscountTotal (3).getDefinitionDouble ();
        discCardCode4 = salesDetail.getDiscountCard (3).getCode ();
        discGuid4 = salesDetail.getDiscountGuid (3);
        discCamp4 = salesDetail.getDiscountCampaign (3);
        discCampLineNo4 = salesDetail.getDiscountCampaignLineNo (3);
        discRatio5 = salesDetail.getDiscountRatio (4).getDefinitionDouble ();
        discTotal5 = salesDetail.getDiscountTotal (4).getDefinitionDouble ();
        discCardCode5 = salesDetail.getDiscountCard (4).getCode ();
        discGuid5 = salesDetail.getDiscountGuid (4);
        discCamp5 = salesDetail.getDiscountCampaign (4);
        discCampLineNo5 = salesDetail.getDiscountCampaignLineNo (4);
        custDiscRatio = salesDetail.getCustomerDiscRatio ();
        custDiscGuid = salesDetail.getCustomerDiscGuid ();
        custDiscCamp = salesDetail.getCustomerCampaignCode ();
        variantRef = salesDetail.getVariant ().getLogicalRef ();
        lineIndex = 0;
        lineNr = i;
        total = salesDetail.getProductTotal ();
        vatMatrah = salesDetail.getProductVatMatrah ();
        vatAmnt = salesDetail.getProductVatAmnt ();
        discTotal = salesDetail.getProductDiscountTotal ();
        amountTotal = salesDetail.getProductAmountTotalConvfact ();
        lineNet = salesDetail.getProductLineNet ();
        lineType = salesDetail.lineType;
        product = salesDetail.isProduct () ? 1 : 0;
        actualStock = salesDetail.getActualStock ();
        convFact1 = salesDetail.getConvFact1 ();
        convFact2 = salesDetail.getConvFact2 ();
        isNotUnitChange = salesDetail.isNotUnitChange () ? 1 : 0;
        guid = salesDetail.guid;
        globalLineType = salesDetail.getGlobalLineType ();
        muhRefCode = salesDetail.getReferenceCode ().getLogicalRef ();
        addTexRef = salesDetail.getAddTaxRef ();
        campCode = salesDetail.getCampaignCode ();
        campCode2 = salesDetail.getCampaignCode2 ();
        campCode3 = salesDetail.getCampaignCode3 ();
        campCode4 = salesDetail.getCampaignCode4 ();
        campCode5 = salesDetail.getCampaignCode5 ();
        campLineNo = salesDetail.getCampaignLineNo ();
        secondAmount = salesDetail.getSecondAmount ().getDefinitionDouble ();
        secondUnit = salesDetail.getSecondUnit ().getLogicalRef ();
        secondUnitCode = salesDetail.getSecondUnit ().getCode ();
        previousPriceRef = salesDetail.previousPriceRef;
        foundByStoredProcedure = salesDetail.isFoundByStoredProcedure () ? 1 : 0;
        final ArrayList<String> searchBarcodes = salesDetail.getSearchBarcodes ();
        checkNotNull (searchBarcodes);
        ficheDetailBarcodes = searchBarcodes;
        dueDate = String.valueOf (salesDetail.getDueDate ());
        final FicheStringProp surplusAmount = salesDetail.getSurplusAmount ();
        checkNotNull (surplusAmount);
        this.surplusAmount = surplusAmount.getDefinitionDouble ();
    }

    public  SalesDetail convertSalesFicheDetailToSalesDetail() {
        return null;
    }
}
