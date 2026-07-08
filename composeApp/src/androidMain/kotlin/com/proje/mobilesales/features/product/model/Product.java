package com.proje.mobilesales.features.product.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.proje.mobilesales.core.utils.CalculateUtils;
import com.proje.mobilesales.features.model.SelectedVariant;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parceler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Product implements Cloneable, Parcelable {
    private double actualStock;
    private int addTaxRef;
    private double amount;
    private int barcodeUnitRef;
    private String cPrice;
    private String campaignCode;
    private String campaignLineNo;
    private int cardType;
    private String code;
    private double convfact1;
    private double convfact2;
    private String curCode;
    private int curNr;
    private int defUnitRef;
    private double[] discount;
    private boolean[] discountRatio;
    private boolean divUnit;
    private String explanation;
    private boolean foundByStoredProcedure;
    private int globalLineType;
    private double grossVolume;
    private double grossWeight;
    private boolean hasOrderReference;
    private Bitmap image;
    private boolean incVat;
    private boolean isBarcode;
    private boolean isImageActive;
    private List<String> itemSecondUnitCodeList;
    private ArrayList<String> itemUnitCodeList;
    private int lineNr;
    private int locTracking;
    private int logicalRef;
    private List<ItemUnit> mItemSecondUnits;
    private ArrayList<ItemUnit> mItemUnits;
    private ArrayList<SelectedVariant> mSelectedVariants;
    private String name;
    private String name2;
    private double netVolume;
    private double netWeight;
    private boolean notUnitChange;
    private String paymentCode;
    private String paymentDef;
    private int paymentRef;
    private double price;
    private int priceRef;
    private boolean priceSetFromBarcode;
    private String priceWithDigits;
    private ProductOperationDiscount productOperationDiscount;
    private boolean promotion;
    private double rate;
    private double realStock;
    private double retailReturnVat;
    private double retailVat;
    private double returnVat;
    private ArrayList<Serilot> salesSerialLots;
    private String searchBarcode;
    private double secondAmount;
    private double secondConvfact1;
    private double secondConvfact2;
    private int secondDefUnitRef;
    private int secondSelectionIndex;
    private String secondUnitCode;
    private String secondUnitCode2;
    private int secondUnitRef;
    private boolean select;
    private int selectionIndex;
    private boolean service;
    private boolean showExplanation;
    private double surplusAmount;
    private String tempCPrice;
    private double tempPrice;
    private int tempPriceRef;
    private String tempPriceWithDigits;
    private int trackType;
    private String unitCode;
    private String unitCode2;
    private String unitCodes;
    private boolean unitConvert;
    private int unitRef;
    private boolean variant;
    private String variantCode;
    private String variantName;
    private int variantRef;
    private double vat;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<Product> CREATOR = new Creator();

    public static final class Creator implements Parcelable.Creator<Product> {
        public Product createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return Product.Companion.create(parcel);
        }

        public Product[] newArray(int i2) {
            return new Product[i2];
        }
    }

    public Product() {
        this(0, null, null, null, 0.0d, null, 0, 0.0d, 0.0d, false, 0.0d, null, null, 0, false, false, 0.0d, 0.0d, false, 0, null, null, 0, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0.0d, null, null, null, null, 0.0d, 0.0d, 0, 0, null, null, false, false, false, null, false, false, 0, null, false, 0, false, false, 0, null, 0, null, 0, 0, 0.0d, 0.0d, null, null, null, null, 0.0d, 0, 0, null, null, 0.0d, 0.0d, 0, null, null, false, 0, null, null, 0, 0.0d, null, null, false, null, 0.0d, -1, -1, 1048575, null);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        Companion.write(this, parcel, i2);
    }

    public Product(int i2, String str, String str2, String str3, double d2, String str4, int i3, double d3, double d4, boolean z, double d5, double[] dArr, boolean[] zArr, int i4, boolean z2, boolean z3, double d6, double d7, boolean z4, int i5, String str5, String str6, int i6, double d8, int i7, double d9, double d10, double d11, double d12, String str7, ArrayList<ItemUnit> arrayList, ArrayList<String> arrayList2, ProductOperationDiscount productOperationDiscount, double d13, double d14, int i8, int i9, String str8, String str9, boolean z5, boolean z6, boolean z7, String str10, boolean z8, boolean z9, int i10, Bitmap bitmap, boolean z10, int i11, boolean z11, boolean z12, int i12, String str11, int i13, String str12, int i14, int i15, double d15, double d16, String str13, String str14, ArrayList<Serilot> arrayList3, ArrayList<SelectedVariant> arrayList4, double d17, int i16, int i17, String str15, String str16, double d18, double d19, int i18, List<ItemUnit> list, List<String> list2, boolean z13, int i19, String str17, String str18, int i20, double d20, String str19, String str20, boolean z14, String str21, double d21) {
        Intrinsics.checkNotNullParameter(dArr, FirebaseAnalytics.Param.DISCOUNT);
        Intrinsics.checkNotNullParameter(zArr, "discountRatio");
        Intrinsics.checkNotNullParameter(arrayList3, "salesSerialLots");
        Intrinsics.checkNotNullParameter(arrayList4, "mSelectedVariants");
        this.logicalRef = i2;
        this.code = str;
        this.name = str2;
        this.name2 = str3;
        this.price = d2;
        this.cPrice = str4;
        this.priceRef = i3;
        this.realStock = d3;
        this.actualStock = d4;
        this.select = z;
        this.amount = d5;
        this.discount = dArr;
        this.discountRatio = zArr;
        this.trackType = i4;
        this.variant = z2;
        this.service = z3;
        this.vat = d6;
        this.returnVat = d7;
        this.incVat = z4;
        this.unitRef = i5;
        this.unitCode = str5;
        this.unitCode2 = str6;
        this.cardType = i6;
        this.rate = d8;
        this.curNr = i7;
        this.netVolume = d9;
        this.grossVolume = d10;
        this.netWeight = d11;
        this.grossWeight = d12;
        this.unitCodes = str7;
        this.mItemUnits = arrayList;
        this.itemUnitCodeList = arrayList2;
        this.productOperationDiscount = productOperationDiscount;
        this.convfact1 = d13;
        this.convfact2 = d14;
        this.selectionIndex = i8;
        this.lineNr = i9;
        this.curCode = str8;
        this.priceWithDigits = str9;
        this.unitConvert = z5;
        this.notUnitChange = z6;
        this.divUnit = z7;
        this.explanation = str10;
        this.showExplanation = z8;
        this.isBarcode = z9;
        this.barcodeUnitRef = i10;
        this.image = bitmap;
        this.isImageActive = z10;
        this.defUnitRef = i11;
        this.promotion = z11;
        this.hasOrderReference = z12;
        this.globalLineType = i12;
        this.variantCode = str11;
        this.variantRef = i13;
        this.variantName = str12;
        this.addTaxRef = i14;
        this.locTracking = i15;
        this.retailVat = d15;
        this.retailReturnVat = d16;
        this.campaignCode = str13;
        this.campaignLineNo = str14;
        this.salesSerialLots = arrayList3;
        this.mSelectedVariants = arrayList4;
        this.secondAmount = d17;
        this.secondUnitRef = i16;
        this.secondDefUnitRef = i17;
        this.secondUnitCode = str15;
        this.secondUnitCode2 = str16;
        this.secondConvfact1 = d18;
        this.secondConvfact2 = d19;
        this.secondSelectionIndex = i18;
        this.mItemSecondUnits = list;
        this.itemSecondUnitCodeList = list2;
        this.priceSetFromBarcode = z13;
        this.paymentRef = i19;
        this.paymentCode = str17;
        this.paymentDef = str18;
        this.tempPriceRef = i20;
        this.tempPrice = d20;
        this.tempPriceWithDigits = str19;
        this.tempCPrice = str20;
        this.foundByStoredProcedure = z14;
        this.searchBarcode = str21;
        this.surplusAmount = d21;
        initDiscount();
    }

    public final int getLogicalRef() {
        return this.logicalRef;
    }

    public final void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }

    public final String getCode() {
        return this.code;
    }

    public final void setCode(String str) {
        this.code = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String getName2() {
        return this.name2;
    }

    public final void setName2(String str) {
        this.name2 = str;
    }

    public final double getPrice() {
        return this.price;
    }

    public final void setPrice(double d2) {
        this.price = d2;
    }

    public final String getCPrice() {
        return this.cPrice;
    }

    public final void setCPrice(String str) {
        this.cPrice = str;
    }

    public final int getPriceRef() {
        return this.priceRef;
    }

    public final void setPriceRef(int i2) {
        this.priceRef = i2;
    }

    public final double getRealStock() {
        return this.realStock;
    }

    public final void setRealStock(double d2) {
        this.realStock = d2;
    }

    public final double getActualStock() {
        return this.actualStock;
    }

    public final void setActualStock(double d2) {
        this.actualStock = d2;
    }

    public final boolean getSelect() {
        return this.select;
    }

    public final void setSelect(boolean z) {
        this.select = z;
    }

    public final double getAmount() {
        return this.amount;
    }

    public final void setAmount(double d2) {
        this.amount = d2;
    }

    public Product(int r90, java.lang.String r91, java.lang.String r92, java.lang.String r93, double r94, java.lang.String r96, int r97, double r98, double r100, boolean r102, double r103, double[] r105, boolean[] r106, int r107, boolean r108, boolean r109, double r110, double r112, boolean r114, int r115, java.lang.String r116, java.lang.String r117, int r118, double r119, int r121, double r122, double r124, double r126, double r128, java.lang.String r130, java.util.ArrayList r131, java.util.ArrayList r132, com.proje.mobilesales.features.product.model.ProductOperationDiscount r133, double r134, double r136, int r138, int r139, java.lang.String r140, java.lang.String r141, boolean r142, boolean r143, boolean r144, java.lang.String r145, boolean r146, boolean r147, int r148, android.graphics.Bitmap r149, boolean r150, int r151, boolean r152, boolean r153, int r154, java.lang.String r155, int r156, java.lang.String r157, int r158, int r159, double r160, double r162, java.lang.String r164, java.lang.String r165, java.util.ArrayList r166, java.util.ArrayList r167, double r168, int r170, int r171, java.lang.String r172, java.lang.String r173, double r174, double r176, int r178, java.util.List r179, java.util.List r180, boolean r181, int r182, java.lang.String r183, java.lang.String r184, int r185, double r186, java.lang.String r188, java.lang.String r189, boolean r190, java.lang.String r191, double r192, int r194, int r195, int r196, kotlin.jvm.internal.DefaultConstructorMarker r197) {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.product.model.Product.<init>(int, java.lang.String, java.lang.String, java.lang.String, double, java.lang.String, int, double, double, boolean, double, double[], boolean[], int, boolean, boolean, double, double, boolean, int, java.lang.String, java.lang.String, int, double, int, double, double, double, double, java.lang.String, java.util.ArrayList, java.util.ArrayList, com.proje.mobilesales.features.product.model.ProductOperationDiscount, double, double, int, int, java.lang.String, java.lang.String, boolean, boolean, boolean, java.lang.String, boolean, boolean, int, android.graphics.Bitmap, boolean, int, boolean, boolean, int, java.lang.String, int, java.lang.String, int, int, double, double, java.lang.String, java.lang.String, java.util.ArrayList, java.util.ArrayList, double, int, int, java.lang.String, java.lang.String, double, double, int, java.util.List, java.util.List, boolean, int, java.lang.String, java.lang.String, int, double, java.lang.String, java.lang.String, boolean, java.lang.String, double, int, int, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final double[] getDiscount() {
        return this.discount;
    }

    public final void setDiscount(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<set-?>");
        this.discount = dArr;
    }

    public final boolean[] getDiscountRatio() {
        return this.discountRatio;
    }

    public final void setDiscountRatio(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<set-?>");
        this.discountRatio = zArr;
    }

    public final int getTrackType() {
        return this.trackType;
    }

    public final void setTrackType(int i2) {
        this.trackType = i2;
    }

    public final boolean getVariant() {
        return this.variant;
    }

    public final void setVariant(boolean z) {
        this.variant = z;
    }

    public final boolean getService() {
        return this.service;
    }

    public final void setService(boolean z) {
        this.service = z;
    }

    public final double getVat() {
        return this.vat;
    }

    public final void setVat(double d2) {
        this.vat = d2;
    }

    public final double getReturnVat() {
        return this.returnVat;
    }

    public final void setReturnVat(double d2) {
        this.returnVat = d2;
    }

    public final boolean getIncVat() {
        return this.incVat;
    }

    public final void setIncVat(boolean z) {
        this.incVat = z;
    }

    public final int getUnitRef() {
        return this.unitRef;
    }

    public final void setUnitRef(int i2) {
        this.unitRef = i2;
    }

    public final String getUnitCode() {
        return this.unitCode;
    }

    public final void setUnitCode(String str) {
        this.unitCode = str;
    }

    public final String getUnitCode2() {
        return this.unitCode2;
    }

    public final void setUnitCode2(String str) {
        this.unitCode2 = str;
    }

    public final int getCardType() {
        return this.cardType;
    }

    public final void setCardType(int i2) {
        this.cardType = i2;
    }

    public final double getRate() {
        return this.rate;
    }

    public final void setRate(double d2) {
        this.rate = d2;
    }

    public final int getCurNr() {
        return this.curNr;
    }

    public final void setCurNr(int i2) {
        this.curNr = i2;
    }

    public final double getNetVolume() {
        return this.netVolume;
    }

    public final void setNetVolume(double d2) {
        this.netVolume = d2;
    }

    public final double getGrossVolume() {
        return this.grossVolume;
    }

    public final void setGrossVolume(double d2) {
        this.grossVolume = d2;
    }

    public final double getNetWeight() {
        return this.netWeight;
    }

    public final void setNetWeight(double d2) {
        this.netWeight = d2;
    }

    public final double getGrossWeight() {
        return this.grossWeight;
    }

    public final void setGrossWeight(double d2) {
        this.grossWeight = d2;
    }

    public final String getUnitCodes() {
        return this.unitCodes;
    }

    public final void setUnitCodes(String str) {
        this.unitCodes = str;
    }

    public final ArrayList<ItemUnit> getMItemUnits() {
        return this.mItemUnits;
    }

    public final void setMItemUnits(ArrayList<ItemUnit> arrayList) {
        this.mItemUnits = arrayList;
    }

    public final ArrayList<String> getItemUnitCodeList() {
        return this.itemUnitCodeList;
    }

    public final void setItemUnitCodeList(ArrayList<String> arrayList) {
        this.itemUnitCodeList = arrayList;
    }

    public final ProductOperationDiscount getProductOperationDiscount() {
        return this.productOperationDiscount;
    }

    public final void setProductOperationDiscount(ProductOperationDiscount productOperationDiscount) {
        this.productOperationDiscount = productOperationDiscount;
    }

    public final double getConvfact1() {
        return this.convfact1;
    }

    public final void setConvfact1(double d2) {
        this.convfact1 = d2;
    }

    public final double getConvfact2() {
        return this.convfact2;
    }

    public final void setConvfact2(double d2) {
        this.convfact2 = d2;
    }

    public final int getSelectionIndex() {
        return this.selectionIndex;
    }

    public final void setSelectionIndex(int i2) {
        this.selectionIndex = i2;
    }

    public final int getLineNr() {
        return this.lineNr;
    }

    public final void setLineNr(int i2) {
        this.lineNr = i2;
    }

    public final String getCurCode() {
        return this.curCode;
    }

    public final void setCurCode(String str) {
        this.curCode = str;
    }

    public final String getPriceWithDigits() {
        return this.priceWithDigits;
    }

    public final void setPriceWithDigits(String str) {
        this.priceWithDigits = str;
    }

    public final boolean getUnitConvert() {
        return this.unitConvert;
    }

    public final void setUnitConvert(boolean z) {
        this.unitConvert = z;
    }

    public final boolean getNotUnitChange() {
        return this.notUnitChange;
    }

    public final void setNotUnitChange(boolean z) {
        this.notUnitChange = z;
    }

    public final boolean getDivUnit() {
        return this.divUnit;
    }

    public final void setDivUnit(boolean z) {
        this.divUnit = z;
    }

    public final String getExplanation() {
        return this.explanation;
    }

    public final void setExplanation(String str) {
        this.explanation = str;
    }

    public final boolean getShowExplanation() {
        return this.showExplanation;
    }

    public final void setShowExplanation(boolean z) {
        this.showExplanation = z;
    }

    public final boolean isBarcode() {
        return this.isBarcode;
    }

    public final void setBarcode(boolean z) {
        this.isBarcode = z;
    }

    public final int getBarcodeUnitRef() {
        return this.barcodeUnitRef;
    }

    public final void setBarcodeUnitRef(int i2) {
        this.barcodeUnitRef = i2;
    }

    public final Bitmap getImage() {
        return this.image;
    }

    public final void setImage(Bitmap bitmap) {
        this.image = bitmap;
    }

    public final boolean isImageActive() {
        return this.isImageActive;
    }

    public final void setImageActive(boolean z) {
        this.isImageActive = z;
    }

    public final int getDefUnitRef() {
        return this.defUnitRef;
    }

    public final void setDefUnitRef(int i2) {
        this.defUnitRef = i2;
    }

    public final boolean getPromotion() {
        return this.promotion;
    }

    public final void setPromotion(boolean z) {
        this.promotion = z;
    }

    public final boolean getHasOrderReference() {
        return this.hasOrderReference;
    }

    public final void setHasOrderReference(boolean z) {
        this.hasOrderReference = z;
    }

    public final int getGlobalLineType() {
        return this.globalLineType;
    }

    public final void setGlobalLineType(int i2) {
        this.globalLineType = i2;
    }

    public final String getVariantCode() {
        return this.variantCode;
    }

    public final void setVariantCode(String str) {
        this.variantCode = str;
    }

    public final int getVariantRef() {
        return this.variantRef;
    }

    public final void setVariantRef(int i2) {
        this.variantRef = i2;
    }

    public final String getVariantName() {
        return this.variantName;
    }

    public final void setVariantName(String str) {
        this.variantName = str;
    }

    public final int getAddTaxRef() {
        return this.addTaxRef;
    }

    public final void setAddTaxRef(int i2) {
        this.addTaxRef = i2;
    }

    public final int getLocTracking() {
        return this.locTracking;
    }

    public final void setLocTracking(int i2) {
        this.locTracking = i2;
    }

    public final double getRetailVat() {
        return this.retailVat;
    }

    public final void setRetailVat(double d2) {
        this.retailVat = d2;
    }

    public final double getRetailReturnVat() {
        return this.retailReturnVat;
    }

    public final void setRetailReturnVat(double d2) {
        this.retailReturnVat = d2;
    }

    public final String getCampaignCode() {
        return this.campaignCode;
    }

    public final void setCampaignCode(String str) {
        this.campaignCode = str;
    }

    public final String getCampaignLineNo() {
        return this.campaignLineNo;
    }

    public final void setCampaignLineNo(String str) {
        this.campaignLineNo = str;
    }

    public final ArrayList<Serilot> getSalesSerialLots() {
        return this.salesSerialLots;
    }

    public final void setSalesSerialLots(ArrayList<Serilot> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.salesSerialLots = arrayList;
    }

    public final ArrayList<SelectedVariant> getMSelectedVariants() {
        return this.mSelectedVariants;
    }

    public final void setMSelectedVariants(ArrayList<SelectedVariant> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.mSelectedVariants = arrayList;
    }

    public final double getSecondAmount() {
        return this.secondAmount;
    }

    public final void setSecondAmount(double d2) {
        this.secondAmount = d2;
    }

    public final int getSecondUnitRef() {
        return this.secondUnitRef;
    }


    public final void m712setSecondUnitRef(int i2) {
        this.secondUnitRef = i2;
    }

    public final int getSecondDefUnitRef() {
        return this.secondDefUnitRef;
    }


    public final void m709setSecondDefUnitRef(int i2) {
        this.secondDefUnitRef = i2;
    }

    public final String getSecondUnitCode() {
        return this.secondUnitCode;
    }

    public final void setSecondUnitCode(String str) {
        this.secondUnitCode = str;
    }

    public final String getSecondUnitCode2() {
        return this.secondUnitCode2;
    }


    public final void m711setSecondUnitCode2(String str) {
        this.secondUnitCode2 = str;
    }

    public final double getSecondConvfact1() {
        return this.secondConvfact1;
    }
    public final void m707setSecondConvfact1(double d2) {
        this.secondConvfact1 = d2;
    }

    public final double getSecondConvfact2() {
        return this.secondConvfact2;
    }
    public final void m708setSecondConvfact2(double d2) {
        this.secondConvfact2 = d2;
    }

    public final int getSecondSelectionIndex() {
        return this.secondSelectionIndex;
    }
    public final void m710setSecondSelectionIndex(int i2) {
        this.secondSelectionIndex = i2;
    }

    public final List<ItemUnit> getMItemSecondUnits() {
        return this.mItemSecondUnits;
    }

    public final void setMItemSecondUnits(List<ItemUnit> list) {
        this.mItemSecondUnits = list;
    }

    public final List<String> getItemSecondUnitCodeList() {
        return this.itemSecondUnitCodeList;
    }

    public final void setItemSecondUnitCodeList(List<String> list) {
        this.itemSecondUnitCodeList = list;
    }

    public final boolean getPriceSetFromBarcode() {
        return this.priceSetFromBarcode;
    }

    public final void setPriceSetFromBarcode(boolean z) {
        this.priceSetFromBarcode = z;
    }

    public final int getPaymentRef() {
        return this.paymentRef;
    }

    public final void setPaymentRef(int i2) {
        this.paymentRef = i2;
    }

    public final String getPaymentCode() {
        return this.paymentCode;
    }

    public final void setPaymentCode(String str) {
        this.paymentCode = str;
    }

    public final String getPaymentDef() {
        return this.paymentDef;
    }

    public final void setPaymentDef(String str) {
        this.paymentDef = str;
    }

    public final int getTempPriceRef() {
        return this.tempPriceRef;
    }

    public final void setTempPriceRef(int i2) {
        this.tempPriceRef = i2;
    }

    public final double getTempPrice() {
        return this.tempPrice;
    }

    public final void setTempPrice(double d2) {
        this.tempPrice = d2;
    }

    public final String getTempPriceWithDigits() {
        return this.tempPriceWithDigits;
    }

    public final void setTempPriceWithDigits(String str) {
        this.tempPriceWithDigits = str;
    }

    public final String getTempCPrice() {
        return this.tempCPrice;
    }

    public final void setTempCPrice(String str) {
        this.tempCPrice = str;
    }

    public final boolean getFoundByStoredProcedure() {
        return this.foundByStoredProcedure;
    }

    public final void setFoundByStoredProcedure(boolean z) {
        this.foundByStoredProcedure = z;
    }

    public final String getSearchBarcode() {
        return this.searchBarcode;
    }

    public final void setSearchBarcode(String str) {
        this.searchBarcode = str;
    }

    public final double getSurplusAmount() {
        return this.surplusAmount;
    }

    public final void setSurplusAmount(double d2) {
        this.surplusAmount = d2;
    }

    public Product(Parcel parcel) {
        this(0, null, null, null, 0.0d, null, 0, 0.0d, 0.0d, false, 0.0d, null, null, 0, false, false, 0.0d, 0.0d, false, 0, null, null, 0, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0.0d, null, null, null, null, 0.0d, 0.0d, 0, 0, null, null, false, false, false, null, false, false, 0, null, false, 0, false, false, 0, null, 0, null, 0, 0, 0.0d, 0.0d, null, null, null, null, 0.0d, 0, 0, null, null, 0.0d, 0.0d, 0, null, null, false, 0, null, null, 0, 0.0d, null, null, false, null, 0.0d, -1, -1, 1048575, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.logicalRef = parcel.readInt();
        this.code = parcel.readString();
        this.name = parcel.readString();
        this.name2 = parcel.readString();
        this.price = parcel.readDouble();
        this.cPrice = parcel.readString();
        this.priceRef = parcel.readInt();
        this.realStock = parcel.readDouble();
        this.actualStock = parcel.readDouble();
        boolean z = false;
        this.select = parcel.readByte() != 0;
        this.amount = parcel.readDouble();
        double[] createDoubleArray = parcel.createDoubleArray();
        Intrinsics.checkNotNull(createDoubleArray);
        this.discount = createDoubleArray;
        boolean[] createBooleanArray = parcel.createBooleanArray();
        Intrinsics.checkNotNull(createBooleanArray);
        this.discountRatio = createBooleanArray;
        this.trackType = parcel.readInt();
        this.variant = parcel.readByte() != 0;
        this.service = parcel.readByte() != 0;
        this.vat = parcel.readDouble();
        this.returnVat = parcel.readDouble();
        this.incVat = parcel.readByte() != 0;
        this.unitRef = parcel.readInt();
        this.unitCode = parcel.readString();
        this.unitCode2 = parcel.readString();
        this.cardType = parcel.readInt();
        this.rate = parcel.readDouble();
        this.curNr = parcel.readInt();
        this.netVolume = parcel.readDouble();
        this.grossVolume = parcel.readDouble();
        this.netWeight = parcel.readDouble();
        this.grossWeight = parcel.readDouble();
        this.unitCodes = parcel.readString();
        Parcelable.Creator<ItemUnit> creator = ItemUnit.CREATOR;
        this.mItemUnits = parcel.createTypedArrayList(creator);
        this.itemUnitCodeList = parcel.createStringArrayList();
        this.productOperationDiscount = parcel.readParcelable(ProductOperationDiscount.class.getClassLoader());
        this.convfact1 = parcel.readDouble();
        this.convfact2 = parcel.readDouble();
        this.selectionIndex = parcel.readInt();
        this.lineNr = parcel.readInt();
        this.curCode = parcel.readString();
        this.priceWithDigits = parcel.readString();
        this.unitConvert = parcel.readByte() != 0;
        this.notUnitChange = parcel.readByte() != 0;
        this.divUnit = parcel.readByte() != 0;
        this.explanation = parcel.readString();
        this.showExplanation = parcel.readByte() != 0;
        this.isBarcode = parcel.readByte() != 0;
        this.barcodeUnitRef = parcel.readInt();
        this.image = parcel.readParcelable(Bitmap.class.getClassLoader());
        this.isImageActive = parcel.readByte() != 0;
        this.defUnitRef = parcel.readInt();
        this.promotion = parcel.readByte() != 0;
        this.hasOrderReference = parcel.readByte() != 0;
        this.globalLineType = parcel.readInt();
        this.variantCode = parcel.readString();
        this.variantRef = parcel.readInt();
        this.variantName = parcel.readString();
        this.addTaxRef = parcel.readInt();
        this.locTracking = parcel.readInt();
        this.retailVat = parcel.readDouble();
        this.retailReturnVat = parcel.readDouble();
        ArrayList<Serilot> createTypedArrayList = parcel.createTypedArrayList(Serilot.CREATOR);
        Intrinsics.checkNotNull(createTypedArrayList);
        this.salesSerialLots = createTypedArrayList;
        this.campaignCode = parcel.readString();
        this.campaignLineNo = parcel.readString();
        this.secondAmount = setSecondMAmount(parcel.readDouble());
        this.secondUnitRef = setSecondUnitRef(parcel.readInt());
        this.secondDefUnitRef = setSecondDefUnitRef(parcel.readInt());
        this.secondUnitCode = setSecondUnitCode1(parcel.readString());
        this.secondUnitCode2 = setSecondUnitCode2(parcel.readString());
        this.secondConvfact1 = setSecondConvfact1(parcel.readDouble());
        this.secondConvfact2 = setSecondConvfact2(parcel.readDouble());
        this.secondSelectionIndex = setSecondSelectionIndex(parcel.readInt());
        this.mItemSecondUnits = parcel.createTypedArrayList(creator);
        this.itemSecondUnitCodeList = parcel.createStringArrayList();
        this.priceSetFromBarcode = parcel.readByte() != 0;
        this.paymentRef = parcel.readInt();
        this.paymentCode = parcel.readString();
        this.paymentDef = parcel.readString();
        this.tempPriceRef = parcel.readInt();
        this.tempPrice = parcel.readDouble();
        this.tempPriceWithDigits = parcel.readString();
        this.tempCPrice = parcel.readString();
        this.foundByStoredProcedure = parcel.readByte() != 0 || z;
        ArrayList<SelectedVariant> createTypedArrayList2 = parcel.createTypedArrayList(SelectedVariant.CREATOR);
        Intrinsics.checkNotNull(createTypedArrayList2);
        this.mSelectedVariants = createTypedArrayList2;
        this.searchBarcode = parcel.readString();
        this.surplusAmount = setSurplusMAmount(parcel.readDouble());
    }

    public final double setSecondMAmount(double d2) {
        this.secondAmount = d2;
        return d2;
    }

    public final int setSecondUnitRef(int i2) {
        this.secondUnitRef = i2;
        return i2;
    }

    public final int setSecondDefUnitRef(int i2) {
        this.secondDefUnitRef = i2;
        return i2;
    }

    public final String setSecondUnitCode1(String str) {
        this.secondUnitCode = str;
        return str;
    }

    public final String setSecondUnitCode2(String str) {
        this.secondUnitCode2 = str;
        return str;
    }

    public final double setSecondConvfact1(double d2) {
        this.secondConvfact1 = d2;
        return d2;
    }

    public final double setSecondConvfact2(double d2) {
        this.secondConvfact2 = d2;
        return d2;
    }

    public final int setSecondSelectionIndex(int i2) {
        this.secondSelectionIndex = i2;
        return i2;
    }

    public final double setSurplusMAmount(double d2) {
        this.surplusAmount = d2;
        return d2;
    }

    public final void plusAmount() {
        this.amount += 1.0d;
    }

    public final void minusAmount() {
        double d2 = this.amount - 4.0d;
        this.amount = d2;
        if (d2 < 0.0d) {
            this.amount = 0.0d;
        }
    }

    public final void initUnitList(ArrayList<ItemUnit> arrayList) {
        if (arrayList != null) {
            this.mItemUnits = arrayList;
            ArrayList<String> arrayList2 = this.itemUnitCodeList;
            if (arrayList2 == null) {
                this.itemUnitCodeList = new ArrayList<>();
            } else {
                Intrinsics.checkNotNull(arrayList2);
                arrayList2.clear();
            }
            this.itemUnitCodeList = getItemShortUnitsCode();
        }
    }

    public final void initSecondUnitList(ArrayList<ItemUnit> arrayList) {
        if (arrayList != null) {
            this.mItemSecondUnits = arrayList;
            List<String> list = this.itemSecondUnitCodeList;
            if (list == null) {
                this.itemSecondUnitCodeList = new ArrayList();
            } else {
                Intrinsics.checkNotNull(list);
                list.clear();
            }
            this.itemSecondUnitCodeList = getItemSecondShortUnitsCode();
        }
    }

    private final ArrayList<String> getItemShortUnitsCode() {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<ItemUnit> arrayList2 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList2);
        Iterator<ItemUnit> it = arrayList2.iterator();
        while (it.hasNext()) {
            String str = it.next().unitCode;
            Intrinsics.checkNotNull(str);
            arrayList.add(str);
        }
        return arrayList;
    }

    private final ArrayList<String> getItemSecondShortUnitsCode() {
        ArrayList<String> arrayList = new ArrayList<>();
        List<ItemUnit> list = this.mItemSecondUnits;
        Intrinsics.checkNotNull(list);
        for (ItemUnit itemUnit : list) {
            String str = itemUnit.unitCode;
            Intrinsics.checkNotNull(str);
            arrayList.add(str);
        }
        return arrayList;
    }

    public final int getSelectUnitIndex() {
        ArrayList<String> arrayList = this.itemUnitCodeList;
        if (arrayList != null) {
            Intrinsics.checkNotNull(arrayList);
            if (!arrayList.isEmpty()) {
                ArrayList<String> arrayList2 = this.itemUnitCodeList;
                Intrinsics.checkNotNull(arrayList2);
                int size = arrayList2.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    ArrayList<ItemUnit> arrayList3 = this.mItemUnits;
                    Intrinsics.checkNotNull(arrayList3);
                    if (Intrinsics.areEqual(arrayList3.get(i2).unitCode, this.unitCode2)) {
                        this.selectionIndex = i2;
                        break;
                    }
                    i2++;
                }
            }
        }
        return this.selectionIndex;
    }

    public final int getSecondSelectUnitIndex() {
        List<String> list = this.itemSecondUnitCodeList;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            if (!list.isEmpty()) {
                List<String> list2 = this.itemSecondUnitCodeList;
                Intrinsics.checkNotNull(list2);
                int size = list2.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    List<ItemUnit> list3 = this.mItemSecondUnits;
                    Intrinsics.checkNotNull(list3);
                    if (Intrinsics.areEqual(list3.get(i2).unitCode, this.secondUnitCode2)) {
                        this.secondSelectionIndex = i2;
                        break;
                    }
                    i2++;
                }
            }
        }
        return this.secondSelectionIndex;
    }

    public final void selectUnitIndex(int i2) {
        int i3 = 0;
        if (i2 < 0) {
            i2 = 0;
        }
        this.selectionIndex = i2;
        ArrayList<String> arrayList = this.itemUnitCodeList;
        if (arrayList != null) {
            Intrinsics.checkNotNull(arrayList);
            if (!arrayList.isEmpty()) {
                ArrayList<String> arrayList2 = this.itemUnitCodeList;
                Intrinsics.checkNotNull(arrayList2);
                String str = arrayList2.get(i2);
                Intrinsics.checkNotNullExpressionValue(str, "get(...)");
                String str2 = str;
                ArrayList<ItemUnit> arrayList3 = this.mItemUnits;
                Intrinsics.checkNotNull(arrayList3);
                int size = arrayList3.size();
                while (i3 < size && !controlSelectedUnitCode(i3, str2)) {
                    i3++;
                }
            }
        }
    }

    private final boolean controlSelectedUnitCode(int i2, String str) {
        double d2;
        ArrayList<ItemUnit> arrayList = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList);
        if (!Intrinsics.areEqual(arrayList.get(i2).unitCode, str)) {
            return false;
        }
        ArrayList<ItemUnit> arrayList2 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList2);
        this.unitCode = arrayList2.get(i2).code;
        ArrayList<ItemUnit> arrayList3 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList3);
        this.unitCode2 = arrayList3.get(i2).unitCode;
        ArrayList<ItemUnit> arrayList4 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList4);
        this.unitRef = arrayList4.get(i2).logicalRef;
        double d3 = this.convfact1;
        double d4 = this.convfact2;
        ArrayList<ItemUnit> arrayList5 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList5);
        this.convfact1 = arrayList5.get(i2).convFact1;
        ArrayList<ItemUnit> arrayList6 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList6);
        this.convfact2 = arrayList6.get(i2).convFact2;
        ArrayList<ItemUnit> arrayList7 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList7);
        this.divUnit = arrayList7.get(i2).isDivUnit();
        if (d3 == 0.0d || d4 == 0.0d) {
            d2 = d4;
        } else {
            d2 = d4;
            this.actualStock = CalculateUtils.reCalculateActualStock(this.actualStock, d3, d4, this.convfact1, this.convfact2);
        }
        if (this.unitConvert) {
            this.price = CalculateUtils.convertUnitPrice(this.price, this.convfact1, this.convfact2, d3, d2);
        }
        ArrayList<ItemUnit> arrayList8 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList8);
        this.grossWeight = arrayList8.get(i2).grossWeight;
        ArrayList<ItemUnit> arrayList9 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList9);
        this.netWeight = arrayList9.get(i2).netWeight;
        ArrayList<ItemUnit> arrayList10 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList10);
        this.grossVolume = arrayList10.get(i2).grossVolume;
        ArrayList<ItemUnit> arrayList11 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList11);
        this.netVolume = arrayList11.get(i2).netVolume;
        return true;
    }

    public final void selectSecondUnitIndex(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.secondSelectionIndex = i2;
        List<String> list = this.itemSecondUnitCodeList;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            if (!list.isEmpty()) {
                List<String> list2 = this.itemSecondUnitCodeList;
                Intrinsics.checkNotNull(list2);
                String str = list2.get(i2);
                List<ItemUnit> list3 = this.mItemSecondUnits;
                Intrinsics.checkNotNull(list3);
                int size = list3.size();
                for (int i3 = 0; i3 < size; i3++) {
                    List<ItemUnit> list4 = this.mItemSecondUnits;
                    Intrinsics.checkNotNull(list4);
                    if (Intrinsics.areEqual(list4.get(i3).unitCode, str)) {
                        List<ItemUnit> list5 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list5);
                        setSecondUnitCode1(list5.get(i3).code);
                        List<ItemUnit> list6 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list6);
                        setSecondUnitCode2(list6.get(i3).unitCode);
                        List<ItemUnit> list7 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list7);
                        setSecondUnitRef(list7.get(i3).logicalRef);
                        List<ItemUnit> list8 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list8);
                        setSecondConvfact1(list8.get(i3).convFact1);
                        List<ItemUnit> list9 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list9);
                        setSecondConvfact2(list9.get(i3).convFact2);
                        return;
                    }
                }
            }
        }
    }

    public final void selectUnitIndexFirst() {
        selectUnitIndex(0);
    }

    public final void selectSecondUnitIndexFirst() {
        selectSecondUnitIndex(0);
    }

    public final void selectUnitLineNrFirst() {
        selectUnitLineNr(1);
    }

    public final void selectSecondUnitLineNrFirst() {
        selectSecondUnitLineNr(1);
    }

    public final void selectUnitLastIndex() {
        selectUnitIndex(this.selectionIndex);
    }

    public final void selectSecondUnitLastIndex() {
        selectSecondUnitIndex(this.secondSelectionIndex);
    }

    public final void selectUnitLineNr(int i2) {
        ArrayList<String> arrayList = this.itemUnitCodeList;
        if (arrayList != null) {
            Intrinsics.checkNotNull(arrayList);
            if (!arrayList.isEmpty()) {
                ArrayList<ItemUnit> arrayList2 = this.mItemUnits;
                Intrinsics.checkNotNull(arrayList2);
                int size = arrayList2.size();
                int i3 = 0;
                while (i3 < size && !controlSelectedLineNr(i3, i2)) {
                    i3++;
                }
            }
        }
    }

    private final boolean controlSelectedLineNr(int i2, int i3) {
        double d2;
        ArrayList<ItemUnit> arrayList = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList);
        if (arrayList.get(i2).lineNr != i3) {
            return false;
        }
        ArrayList<ItemUnit> arrayList2 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList2);
        this.unitCode = arrayList2.get(i2).code;
        ArrayList<ItemUnit> arrayList3 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList3);
        this.unitCode2 = arrayList3.get(i2).unitCode;
        ArrayList<ItemUnit> arrayList4 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList4);
        this.unitRef = arrayList4.get(i2).logicalRef;
        double d3 = this.convfact1;
        double d4 = this.convfact2;
        ArrayList<ItemUnit> arrayList5 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList5);
        this.convfact1 = arrayList5.get(i2).convFact1;
        ArrayList<ItemUnit> arrayList6 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList6);
        this.convfact2 = arrayList6.get(i2).convFact2;
        ArrayList<ItemUnit> arrayList7 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList7);
        this.divUnit = arrayList7.get(i2).isDivUnit();
        if (d3 == 0.0d || d4 == 0.0d) {
            d2 = d4;
        } else {
            d2 = d4;
            this.actualStock = CalculateUtils.reCalculateActualStock(this.actualStock, d3, d4, this.convfact1, this.convfact2);
        }
        if (this.unitConvert) {
            this.price = CalculateUtils.convertUnitPrice(this.price, this.convfact1, this.convfact2, d3, d2);
        }
        ArrayList<ItemUnit> arrayList8 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList8);
        this.grossWeight = arrayList8.get(i2).grossWeight;
        ArrayList<ItemUnit> arrayList9 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList9);
        this.netWeight = arrayList9.get(i2).netWeight;
        ArrayList<ItemUnit> arrayList10 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList10);
        this.grossVolume = arrayList10.get(i2).grossVolume;
        ArrayList<ItemUnit> arrayList11 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList11);
        this.netVolume = arrayList11.get(i2).netVolume;
        this.selectionIndex = getSelectUnitIndex();
        return true;
    }

    public final boolean selectUnitByCode(String str) {
        Intrinsics.checkNotNullParameter(str, "unitCode");
        ArrayList<String> arrayList = this.itemUnitCodeList;
        if (arrayList != null) {
            Intrinsics.checkNotNull(arrayList);
            if (!arrayList.isEmpty()) {
                ArrayList<ItemUnit> arrayList2 = this.mItemUnits;
                Intrinsics.checkNotNull(arrayList2);
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (controlSelectedByUnitCode(i2, str)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private final boolean controlSelectedByUnitCode(int i2, String str) {
        double d2;
        ArrayList<ItemUnit> arrayList = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList);
        if (!Intrinsics.areEqual(arrayList.get(i2).unitCode, str)) {
            return false;
        }
        ArrayList<ItemUnit> arrayList2 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList2);
        this.unitCode = arrayList2.get(i2).code;
        ArrayList<ItemUnit> arrayList3 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList3);
        this.unitCode2 = arrayList3.get(i2).unitCode;
        ArrayList<ItemUnit> arrayList4 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList4);
        this.unitRef = arrayList4.get(i2).logicalRef;
        double d3 = this.convfact1;
        double d4 = this.convfact2;
        ArrayList<ItemUnit> arrayList5 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList5);
        this.convfact1 = arrayList5.get(i2).convFact1;
        ArrayList<ItemUnit> arrayList6 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList6);
        this.convfact2 = arrayList6.get(i2).convFact2;
        ArrayList<ItemUnit> arrayList7 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList7);
        this.divUnit = arrayList7.get(i2).isDivUnit();
        if (d3 == 0.0d || d4 == 0.0d) {
            d2 = d4;
        } else {
            d2 = d4;
            this.actualStock = CalculateUtils.reCalculateActualStock(this.actualStock, d3, d4, this.convfact1, this.convfact2);
        }
        if (this.unitConvert) {
            this.price = CalculateUtils.convertUnitPrice(this.price, this.convfact1, this.convfact2, d3, d2);
        }
        ArrayList<ItemUnit> arrayList8 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList8);
        this.grossWeight = arrayList8.get(i2).grossWeight;
        ArrayList<ItemUnit> arrayList9 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList9);
        this.netWeight = arrayList9.get(i2).netWeight;
        ArrayList<ItemUnit> arrayList10 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList10);
        this.grossVolume = arrayList10.get(i2).grossVolume;
        ArrayList<ItemUnit> arrayList11 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList11);
        this.netVolume = arrayList11.get(i2).netVolume;
        this.selectionIndex = getSelectUnitIndex();
        return true;
    }

    public final void selectSecondUnitLineNr(int i2) {
        List<String> list = this.itemSecondUnitCodeList;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            if (!list.isEmpty()) {
                List<ItemUnit> list2 = this.mItemSecondUnits;
                Intrinsics.checkNotNull(list2);
                int size = list2.size();
                for (int i3 = 0; i3 < size; i3++) {
                    List<ItemUnit> list3 = this.mItemSecondUnits;
                    Intrinsics.checkNotNull(list3);
                    if (list3.get(i3).lineNr == i2) {
                        List<ItemUnit> list4 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list4);
                        setSecondUnitCode1(list4.get(i3).code);
                        List<ItemUnit> list5 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list5);
                        setSecondUnitCode2(list5.get(i3).unitCode);
                        List<ItemUnit> list6 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list6);
                        setSecondUnitRef(list6.get(i3).logicalRef);
                        List<ItemUnit> list7 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list7);
                        setSecondConvfact1(list7.get(i3).convFact1);
                        List<ItemUnit> list8 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list8);
                        setSecondConvfact2(list8.get(i3).convFact2);
                        this.secondSelectionIndex = getSecondSelectUnitIndex();
                        return;
                    }
                }
            }
        }
    }

    public final boolean selectSecondUnitByCode(String str) {
        Intrinsics.checkNotNullParameter(str, "unitCode");
        List<String> list = this.itemSecondUnitCodeList;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            if (!list.isEmpty()) {
                List<ItemUnit> list2 = this.mItemSecondUnits;
                Intrinsics.checkNotNull(list2);
                int size = list2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    List<ItemUnit> list3 = this.mItemSecondUnits;
                    Intrinsics.checkNotNull(list3);
                    if (Intrinsics.areEqual(list3.get(i2).unitCode, str)) {
                        List<ItemUnit> list4 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list4);
                        setSecondUnitCode1(list4.get(i2).code);
                        List<ItemUnit> list5 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list5);
                        setSecondUnitCode2(list5.get(i2).unitCode);
                        List<ItemUnit> list6 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list6);
                        setSecondUnitRef(list6.get(i2).logicalRef);
                        List<ItemUnit> list7 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list7);
                        setSecondConvfact1(list7.get(i2).convFact1);
                        List<ItemUnit> list8 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list8);
                        setSecondConvfact2(list8.get(i2).convFact2);
                        this.secondSelectionIndex = getSecondSelectUnitIndex();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final void selectUnitLogicalRef(int i2) {
        ArrayList<String> arrayList = this.itemUnitCodeList;
        if (arrayList != null) {
            Intrinsics.checkNotNull(arrayList);
            if (!arrayList.isEmpty()) {
                ArrayList<ItemUnit> arrayList2 = this.mItemUnits;
                Intrinsics.checkNotNull(arrayList2);
                int size = arrayList2.size();
                int i3 = 0;
                while (i3 < size && !controlSelectedLogicalRef(i3, i2)) {
                    i3++;
                }
            }
        }
    }

    private final boolean controlSelectedLogicalRef(int i2, int i3) {
        double d2;
        ArrayList<ItemUnit> arrayList = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList);
        if (arrayList.get(i2).logicalRef != i3) {
            return false;
        }
        ArrayList<ItemUnit> arrayList2 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList2);
        this.unitCode = arrayList2.get(i2).code;
        ArrayList<ItemUnit> arrayList3 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList3);
        this.unitCode2 = arrayList3.get(i2).unitCode;
        ArrayList<ItemUnit> arrayList4 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList4);
        this.unitRef = arrayList4.get(i2).logicalRef;
        double d3 = this.convfact1;
        double d4 = this.convfact2;
        ArrayList<ItemUnit> arrayList5 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList5);
        this.convfact1 = arrayList5.get(i2).convFact1;
        ArrayList<ItemUnit> arrayList6 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList6);
        this.convfact2 = arrayList6.get(i2).convFact2;
        ArrayList<ItemUnit> arrayList7 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList7);
        this.divUnit = arrayList7.get(i2).isDivUnit();
        if (d3 == 0.0d || d4 == 0.0d) {
            d2 = d4;
        } else {
            d2 = d4;
            this.actualStock = CalculateUtils.reCalculateActualStock(this.actualStock, d3, d4, this.convfact1, this.convfact2);
        }
        if (this.unitConvert) {
            this.price = CalculateUtils.convertUnitPrice(this.price, this.convfact1, this.convfact2, d3, d2);
        }
        ArrayList<ItemUnit> arrayList8 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList8);
        this.grossWeight = arrayList8.get(i2).grossWeight;
        ArrayList<ItemUnit> arrayList9 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList9);
        this.netWeight = arrayList9.get(i2).netWeight;
        ArrayList<ItemUnit> arrayList10 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList10);
        this.grossVolume = arrayList10.get(i2).grossVolume;
        ArrayList<ItemUnit> arrayList11 = this.mItemUnits;
        Intrinsics.checkNotNull(arrayList11);
        this.netVolume = arrayList11.get(i2).netVolume;
        this.selectionIndex = getSelectUnitIndex();
        return true;
    }

    public final void selectSecondUnitLogicalRef(int i2) {
        List<String> list = this.itemSecondUnitCodeList;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            if (!list.isEmpty()) {
                List<ItemUnit> list2 = this.mItemSecondUnits;
                Intrinsics.checkNotNull(list2);
                int size = list2.size();
                for (int i3 = 0; i3 < size; i3++) {
                    List<ItemUnit> list3 = this.mItemSecondUnits;
                    Intrinsics.checkNotNull(list3);
                    if (list3.get(i3).logicalRef == i2) {
                        List<ItemUnit> list4 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list4);
                        setSecondUnitCode1(list4.get(i3).code);
                        List<ItemUnit> list5 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list5);
                        setSecondUnitCode2(list5.get(i3).unitCode);
                        List<ItemUnit> list6 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list6);
                        setSecondUnitRef(list6.get(i3).logicalRef);
                        List<ItemUnit> list7 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list7);
                        setSecondConvfact1(list7.get(i3).convFact1);
                        List<ItemUnit> list8 = this.mItemSecondUnits;
                        Intrinsics.checkNotNull(list8);
                        setSecondConvfact2(list8.get(i3).convFact2);
                        this.secondSelectionIndex = getSecondSelectUnitIndex();
                        return;
                    }
                }
            }
        }
    }
    public Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type com.proje.mobilesales.features.product.model.Product");
        return clone;
    }

    private final void initDiscount() {
        int length = this.discountRatio.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.discountRatio[i2] = true;
        }
    }
    public static final class Companion implements Parceler<Product> {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public void write(Product product, Parcel parcel, int i2) {
            Intrinsics.checkNotNullParameter(product, "<this>");
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.writeInt(product.getLogicalRef());
            parcel.writeString(product.getCode());
            parcel.writeString(product.getName());
            parcel.writeString(product.getName2());
            parcel.writeDouble(product.getPrice());
            parcel.writeString(product.getCPrice());
            parcel.writeInt(product.getPriceRef());
            parcel.writeDouble(product.getRealStock());
            parcel.writeDouble(product.getActualStock());
            parcel.writeByte(product.getSelect() ? (byte) 1 : 0);
            parcel.writeDouble(product.getAmount());
            parcel.writeDoubleArray(product.getDiscount());
            parcel.writeBooleanArray(product.getDiscountRatio());
            parcel.writeInt(product.getTrackType());
            parcel.writeByte(product.getVariant() ? (byte) 1 : 0);
            parcel.writeByte(product.getService() ? (byte) 1 : 0);
            parcel.writeDouble(product.getVat());
            parcel.writeDouble(product.getReturnVat());
            parcel.writeByte(product.getIncVat() ? (byte) 1 : 0);
            parcel.writeInt(product.getUnitRef());
            parcel.writeString(product.getUnitCode());
            parcel.writeString(product.getUnitCode2());
            parcel.writeInt(product.getCardType());
            parcel.writeDouble(product.getRate());
            parcel.writeInt(product.getCurNr());
            parcel.writeDouble(product.getNetVolume());
            parcel.writeDouble(product.getGrossVolume());
            parcel.writeDouble(product.getNetWeight());
            parcel.writeDouble(product.getGrossWeight());
            parcel.writeString(product.getUnitCodes());
            parcel.writeTypedList(product.getMItemUnits());
            parcel.writeStringList(product.getItemUnitCodeList());
            parcel.writeParcelable(product.getProductOperationDiscount(), i2);
            parcel.writeDouble(product.getConvfact1());
            parcel.writeDouble(product.getConvfact2());
            parcel.writeInt(product.getSelectionIndex());
            parcel.writeInt(product.getLineNr());
            parcel.writeString(product.getCurCode());
            parcel.writeString(product.getPriceWithDigits());
            parcel.writeByte(product.getUnitConvert() ? (byte) 1 : 0);
            parcel.writeByte(product.getNotUnitChange() ? (byte) 1 : 0);
            parcel.writeByte(product.getDivUnit() ? (byte) 1 : 0);
            parcel.writeString(product.getExplanation());
            parcel.writeByte(product.getShowExplanation() ? (byte) 1 : 0);
            parcel.writeByte(product.isBarcode() ? (byte) 1 : 0);
            parcel.writeInt(product.getBarcodeUnitRef());
            parcel.writeParcelable(product.getImage(), i2);
            parcel.writeByte(product.isImageActive() ? (byte) 1 : 0);
            parcel.writeInt(product.getDefUnitRef());
            parcel.writeByte(product.getPromotion() ? (byte) 1 : 0);
            parcel.writeByte(product.getHasOrderReference() ? (byte) 1 : 0);
            parcel.writeInt(product.getGlobalLineType());
            parcel.writeString(product.getVariantCode());
            parcel.writeInt(product.getVariantRef());
            parcel.writeString(product.getVariantName());
            parcel.writeInt(product.getAddTaxRef());
            parcel.writeInt(product.getLocTracking());
            parcel.writeDouble(product.getRetailVat());
            parcel.writeDouble(product.getRetailReturnVat());
            parcel.writeTypedList(product.getSalesSerialLots());
            parcel.writeString(product.getCampaignCode());
            parcel.writeString(product.getCampaignLineNo());
            parcel.writeDouble(product.getSecondAmount());
            parcel.writeInt(product.getSecondUnitRef());
            parcel.writeInt(product.getSecondDefUnitRef());
            parcel.writeString(product.getSecondUnitCode());
            parcel.writeString(product.getSecondUnitCode2());
            parcel.writeDouble(product.getSecondConvfact1());
            parcel.writeDouble(product.getSecondConvfact2());
            parcel.writeInt(product.getSecondSelectionIndex());
            parcel.writeTypedList(product.getMItemSecondUnits());
            parcel.writeStringList(product.getItemSecondUnitCodeList());
            parcel.writeByte(product.getPriceSetFromBarcode() ? (byte) 1 : 0);
            parcel.writeInt(product.getPaymentRef());
            parcel.writeString(product.getPaymentCode());
            parcel.writeString(product.getPaymentDef());
            parcel.writeInt(product.getTempPriceRef());
            parcel.writeDouble(product.getTempPrice());
            parcel.writeString(product.getPriceWithDigits());
            parcel.writeString(product.getTempCPrice());
            parcel.writeByte(product.getFoundByStoredProcedure() ? (byte) 1 : 0);
            parcel.writeTypedList(product.getMSelectedVariants());
            parcel.writeString(product.getSearchBarcode());
            parcel.writeDouble(product.getSurplusAmount());
        }

        public Product create(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new Product(parcel);
        }
    }
}
