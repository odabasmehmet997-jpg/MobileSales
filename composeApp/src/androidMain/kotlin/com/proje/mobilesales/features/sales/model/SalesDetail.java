package com.proje.mobilesales.features.sales.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.common.base.Strings;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.ItemCardType;
import com.proje.mobilesales.core.enums.LineType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.utils.CalculateUtils;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.dbmodel.VariantStock;
import com.proje.mobilesales.features.model.Disc;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheDateProp;
import com.proje.mobilesales.features.model.FicheDiscountProp;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.product.model.Product;
import com.proje.mobilesales.features.product.model.database.ItemStock;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import com.proje.mobilesales.features.sales.repository.SalesDetailRepository;
import com.proje.mobilesales.features.sales.view.detail.SalesDetailViewModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

public final class SalesDetail implements Parcelable, Cloneable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private double actualStock;
    private int addTaxRef;
    private FicheStringProp amount;
    private int barcodeCount;
    private double calculateCurrPrice;
    private String campaignCode;
    private String campaignCode2;
    private String campaignCode3;
    private String campaignCode4;
    private String campaignCode5;
    private String campaignDiscountRatio;
    private String campaignLineNo;
    private String campaignLineNo2;
    private String campaignLineNo3;
    private String campaignLineNo4;
    private String campaignLineNo5;
    private int cardType;
    private String code;
    private double convFact1;
    private double convFact2;
    public String curCodeStr;
    private FicheRefProp currType;
    private FicheRefProp deliveryCode;
    private FicheDateProp deliveryDate;
    private int distributionLineRef;
    private int distributionRef;
    private FicheDateProp dueDate;
    private double enteryPrice;
    private FicheStringProp explanation;
    private int globalLineType;
    private double grossVolume;
    private double grossWeight;
    public String guid;
    private double height;

    private long f1275id;
    private FicheBooleanProp includeVat;
    private boolean isDivUnit;
    private boolean isFoundByStoredProcedure;
    private boolean isHasVariant;
    private boolean isLocTracking;
    private boolean isNotUnitChange;
    private boolean isPriceSet;
    private boolean isProduct;
    private int itemRef;
    private double length;
    private int lineNr;
    private int lineNumber;
    public int lineType;
    private int logicalRef;
    private String name;
    private double netVolume;
    private double netWeight;
    private double orderAmount;
    private double orderAvailableAmount;
    private int orderDetailReference;
    private int orderReference;
    private FicheDiscountRefProp payment;
    public int prCurrType;
    private double prRate;
    public int previousPriceRef;
    private FicheRefProp price;
    private String priceWithCurCode;
    private String priceWithDigit;
    private double productAmountTotal;
    private double productDiscountTotal;
    private double productGrossTotal;
    private double productLineNet;
    private double productTotal;
    private double productVatAmnt;
    private double productVatMatrah;
    private FicheBooleanProp promotion;
    private FicheRefProp referenceCode;
    private final SalesDetailRepository repository;
    private FicheBooleanProp reserve;
    private SalesFicheDiscountProps salesFicheDiscountProps;
    private int salesFicheId;
    private ArrayList<Serilot> salesSerialLots;
    private int salesType;
    private ArrayList<String> searchBarcodes;
    private FicheStringProp secondAmount;
    private FicheDiscountRefProp secondUnit;
    private FicheDiscountRefProp selectedPrice;
    private FicheRefProp selectedPriceType;
    public String serialLotCodeList;
    private int serialLotTransfer;
    private int sipKont;
    private String sipNum;
    private FicheRefProp speCode;
    private FicheStringProp surplusAmount;
    private int trackType;
    private FicheDiscountRefProp unit;
    private double usePrice;
    private FicheDiscountRefProp variant;
    private FicheStringProp vat;
    private final SalesDetailViewModel viewModel;
    private FicheRefProp wareHouse;
    private double width;

    public SalesDetail() {
        this(0L, 0, 0, null, 0, 0, null, 0, false, false, 0, 0, null, 0, 0, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0, null, 0.0d, null, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, false, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, null, null, 0, false, 0, 0.0d, 0.0d, 0, null, 0, 0, 0, 0, false, null, null, 0, false, null, null, null, 0, null, -1, -1, Integer.MAX_VALUE, null);
    }

    public static  SalesDetail copydefault(SalesDetail salesDetail, long j2, int r27, int r28, String str, int r30, int r31, String str2, int r33, boolean z, boolean z2, int r36, int r37, String str3, int r39, int r40, double d2, double d3, int r45, double d4, double d5, double d6, int r52, String str4, double d7, String str5, String str6, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17, double d18, double d19, double d20, double d21, double d22, boolean z3, FicheRefProp ficheRefProp, FicheRefProp ficheRefProp2, ArrayList arrayList, FicheStringProp ficheStringProp, FicheDiscountRefProp ficheDiscountRefProp, FicheRefProp ficheRefProp3, FicheRefProp ficheRefProp4, FicheDiscountRefProp ficheDiscountRefProp2, FicheBooleanProp ficheBooleanProp, FicheDiscountRefProp ficheDiscountRefProp3, FicheStringProp ficheStringProp2, FicheBooleanProp ficheBooleanProp2, FicheDateProp ficheDateProp, FicheBooleanProp ficheBooleanProp3, FicheDiscountRefProp ficheDiscountRefProp4, FicheRefProp ficheRefProp5, FicheRefProp ficheRefProp6, FicheStringProp ficheStringProp3, SalesFicheDiscountProps salesFicheDiscountProps, FicheRefProp ficheRefProp7, boolean z4, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, int r121, boolean z5, int r123, double d23, double d24, int r128, String str18, int r130, int r131, int r132, int r133, boolean z6, FicheStringProp ficheStringProp4, FicheDiscountRefProp ficheDiscountRefProp5, int r137, boolean z7, ArrayList arrayList2, FicheDateProp ficheDateProp2, FicheStringProp ficheStringProp5, int r142, String str19, int r144, int r145, int r146, Object obj) {
        long j3 = (r144 & 1) != 0 ? salesDetail.f1275id : j2;
        int r6 = (r144 & 2) != 0 ? salesDetail.logicalRef : r27;
        int r7 = (r144 & 4) != 0 ? salesDetail.itemRef : r28;
        String str20 = (r144 & 8) != 0 ? salesDetail.code : str;
        int r9 = (r144 & 16) != 0 ? salesDetail.salesFicheId : r30;
        int r10 = (r144 & 32) != 0 ? salesDetail.salesType : r31;
        String str21 = (r144 & 64) != 0 ? salesDetail.name : str2;
        int r12 = (r144 & 128) != 0 ? salesDetail.trackType : r33;
        boolean z8 = (r144 & 256) != 0 ? salesDetail.isProduct : z;
        boolean z9 = (r144 & 512) != 0 ? salesDetail.isHasVariant : z2;
        int r362 = (r144 & 1024) != 0 ? salesDetail.lineNumber : r36;
        int r372 = (r144 & 2048) != 0 ? salesDetail.serialLotTransfer : r37;
        String str22 = (r144 & 4096) != 0 ? salesDetail.serialLotCodeList : str3;
        int r392 = (r144 & 8192) != 0 ? salesDetail.lineType : r39;
        boolean z10 = z9;
        int r402 = (r144 & 16384) != 0 ? salesDetail.cardType : r40;
        double d25 = (r144 & 32768) != 0 ? salesDetail.convFact1 : d2;
        double d26 = (r144 & 65536) != 0 ? salesDetail.convFact2 : d3;
        boolean z11 = z8;
        int r452 = (r144 & 131072) != 0 ? salesDetail.lineNr : r45;
        double d27 = (r144 & 262144) != 0 ? salesDetail.calculateCurrPrice : d4;
        double d28 = (r144 & 524288) != 0 ? salesDetail.usePrice : d5;
        double d29 = (r144 & 1048576) != 0 ? salesDetail.enteryPrice : d6;
        return salesDetail.copy(j3, r6, r7, str20, r9, r10, str21, r12, z11, z10, r362, r372, str22, r392, r402, d25, d26, r452, d27, d28, d29, (r144 & 2097152) != 0 ? salesDetail.prCurrType : r52, (r144 & 4194304) != 0 ? salesDetail.curCodeStr : str4, (r144 & 8388608) != 0 ? salesDetail.prRate : d7, (r144 & 16777216) != 0 ? salesDetail.priceWithDigit : str5, (33554432 & r144) != 0 ? salesDetail.priceWithCurCode : str6, (r144 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? salesDetail.productVatAmnt : d8, (r144 & 134217728) != 0 ? salesDetail.productVatMatrah : d9, (r144 & 268435456) != 0 ? salesDetail.productDiscountTotal : d10, (r144 & 536870912) != 0 ? salesDetail.productAmountTotal : d11, (r144 & BasicMeasure.EXACTLY) != 0 ? salesDetail.productLineNet : d12, (r144 & Integer.MIN_VALUE) != 0 ? salesDetail.productGrossTotal : d13, (r145 & 1) != 0 ? salesDetail.productTotal : d14, (r145 & 2) != 0 ? salesDetail.netVolume : d15, (r145 & 4) != 0 ? salesDetail.width : d16, (r145 & 8) != 0 ? salesDetail.length : d17, (r145 & 16) != 0 ? salesDetail.height : d18, (r145 & 32) != 0 ? salesDetail.grossVolume : d19, (r145 & 64) != 0 ? salesDetail.netWeight : d20, (r145 & 128) != 0 ? salesDetail.grossWeight : d21, (r145 & 256) != 0 ? salesDetail.actualStock : d22, (r145 & 512) != 0 ? salesDetail.isPriceSet : z3, (r145 & 1024) != 0 ? salesDetail.wareHouse : ficheRefProp, (r145 & 2048) != 0 ? salesDetail.referenceCode : ficheRefProp2, (r145 & 4096) != 0 ? salesDetail.salesSerialLots : arrayList, (r145 & 8192) != 0 ? salesDetail.amount : ficheStringProp, (r145 & 16384) != 0 ? salesDetail.unit : ficheDiscountRefProp, (r145 & 32768) != 0 ? salesDetail.price : ficheRefProp3, (r145 & 65536) != 0 ? salesDetail.selectedPriceType : ficheRefProp4, (r145 & 131072) != 0 ? salesDetail.selectedPrice : ficheDiscountRefProp2, (r145 & 262144) != 0 ? salesDetail.promotion : ficheBooleanProp, (r145 & 524288) != 0 ? salesDetail.variant : ficheDiscountRefProp3, (r145 & 1048576) != 0 ? salesDetail.vat : ficheStringProp2, (r145 & 2097152) != 0 ? salesDetail.includeVat : ficheBooleanProp2, (r145 & 4194304) != 0 ? salesDetail.deliveryDate : ficheDateProp, (r145 & 8388608) != 0 ? salesDetail.reserve : ficheBooleanProp3, (r145 & 16777216) != 0 ? salesDetail.payment : ficheDiscountRefProp4, (r145 & 33554432) != 0 ? salesDetail.speCode : ficheRefProp5, (r145 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? salesDetail.deliveryCode : ficheRefProp6, (r145 & 134217728) != 0 ? salesDetail.explanation : ficheStringProp3, (r145 & 268435456) != 0 ? salesDetail.salesFicheDiscountProps : salesFicheDiscountProps, (r145 & 536870912) != 0 ? salesDetail.currType : ficheRefProp7, (r145 & BasicMeasure.EXACTLY) != 0 ? salesDetail.isNotUnitChange : z4, (r145 & Integer.MIN_VALUE) != 0 ? salesDetail.campaignCode : str7, (r146 & 1) != 0 ? salesDetail.campaignCode2 : str8, (r146 & 2) != 0 ? salesDetail.campaignCode3 : str9, (r146 & 4) != 0 ? salesDetail.campaignCode4 : str10, (r146 & 8) != 0 ? salesDetail.campaignCode5 : str11, (r146 & 16) != 0 ? salesDetail.campaignLineNo : str12, (r146 & 32) != 0 ? salesDetail.campaignLineNo2 : str13, (r146 & 64) != 0 ? salesDetail.campaignLineNo3 : str14, (r146 & 128) != 0 ? salesDetail.campaignLineNo4 : str15, (r146 & 256) != 0 ? salesDetail.campaignLineNo5 : str16, (r146 & 512) != 0 ? salesDetail.guid : str17, (r146 & 1024) != 0 ? salesDetail.globalLineType : r121, (r146 & 2048) != 0 ? salesDetail.isDivUnit : z5, (r146 & 4096) != 0 ? salesDetail.orderDetailReference : r123, (r146 & 8192) != 0 ? salesDetail.orderAvailableAmount : d23, (r146 & 16384) != 0 ? salesDetail.orderAmount : d24, (r146 & 32768) != 0 ? salesDetail.orderReference : r128, (r146 & 65536) != 0 ? salesDetail.sipNum : str18, (r146 & 131072) != 0 ? salesDetail.sipKont : r130, (r146 & 262144) != 0 ? salesDetail.distributionRef : r131, (r146 & 524288) != 0 ? salesDetail.distributionLineRef : r132, (r146 & 1048576) != 0 ? salesDetail.addTaxRef : r133, (r146 & 2097152) != 0 ? salesDetail.isLocTracking : z6, (r146 & 4194304) != 0 ? salesDetail.secondAmount : ficheStringProp4, (r146 & 8388608) != 0 ? salesDetail.secondUnit : ficheDiscountRefProp5, (r146 & 16777216) != 0 ? salesDetail.previousPriceRef : r137, (r146 & 33554432) != 0 ? salesDetail.isFoundByStoredProcedure : z7, (r146 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? salesDetail.searchBarcodes : arrayList2, (r146 & 134217728) != 0 ? salesDetail.dueDate : ficheDateProp2, (r146 & 268435456) != 0 ? salesDetail.surplusAmount : ficheStringProp5, (r146 & 536870912) != 0 ? salesDetail.barcodeCount : r142, (r146 & BasicMeasure.EXACTLY) != 0 ? salesDetail.campaignDiscountRatio : str19);
    }

    public long component1() {
        return this.f1275id;
    }

    public boolean component10() {
        return this.isHasVariant;
    }

    public int component11() {
        return this.lineNumber;
    }

    public int component12() {
        return this.serialLotTransfer;
    }

    public String component13() {
        return this.serialLotCodeList;
    }

    public int component14() {
        return this.lineType;
    }

    public int component15() {
        return this.cardType;
    }

    public double component16() {
        return this.convFact1;
    }

    public double component17() {
        return this.convFact2;
    }

    public int component18() {
        return this.lineNr;
    }

    public double component19() {
        return this.calculateCurrPrice;
    }

    public int component2() {
        return this.logicalRef;
    }

    public double component20() {
        return this.usePrice;
    }

    public double component21() {
        return this.enteryPrice;
    }

    public int component22() {
        return this.prCurrType;
    }

    public String component23() {
        return this.curCodeStr;
    }

    public double component24() {
        return this.prRate;
    }

    public String component25() {
        return this.priceWithDigit;
    }

    public String component26() {
        return this.priceWithCurCode;
    }

    public double component27() {
        return this.productVatAmnt;
    }

    public double component28() {
        return this.productVatMatrah;
    }

    public double component29() {
        return this.productDiscountTotal;
    }

    public int component3() {
        return this.itemRef;
    }

    public double component30() {
        return this.productAmountTotal;
    }

    public double component31() {
        return this.productLineNet;
    }

    public double component32() {
        return this.productGrossTotal;
    }

    public double component33() {
        return this.productTotal;
    }

    public double component34() {
        return this.netVolume;
    }

    public double component35() {
        return this.width;
    }

    public double component36() {
        return this.length;
    }

    public double component37() {
        return this.height;
    }

    public double component38() {
        return this.grossVolume;
    }

    public double component39() {
        return this.netWeight;
    }

    public String component4() {
        return this.code;
    }

    public double component40() {
        return this.grossWeight;
    }

    public double component41() {
        return this.actualStock;
    }

    public boolean component42() {
        return this.isPriceSet;
    }

    public FicheRefProp component43() {
        return this.wareHouse;
    }

    public FicheRefProp component44() {
        return this.referenceCode;
    }

    public ArrayList<Serilot> component45() {
        return this.salesSerialLots;
    }

    public FicheStringProp component46() {
        return this.amount;
    }

    public FicheDiscountRefProp component47() {
        return this.unit;
    }

    public FicheRefProp component48() {
        return this.price;
    }

    public FicheRefProp component49() {
        return this.selectedPriceType;
    }

    public int component5() {
        return this.salesFicheId;
    }

    public FicheDiscountRefProp component50() {
        return this.selectedPrice;
    }

    public FicheBooleanProp component51() {
        return this.promotion;
    }

    public FicheDiscountRefProp component52() {
        return this.variant;
    }

    public FicheStringProp component53() {
        return this.vat;
    }

    public FicheBooleanProp component54() {
        return this.includeVat;
    }

    public FicheDateProp component55() {
        return this.deliveryDate;
    }

    public FicheBooleanProp component56() {
        return this.reserve;
    }

    public FicheDiscountRefProp component57() {
        return this.payment;
    }

    public FicheRefProp component58() {
        return this.speCode;
    }

    public FicheRefProp component59() {
        return this.deliveryCode;
    }

    public int component6() {
        return this.salesType;
    }

    public FicheStringProp component60() {
        return this.explanation;
    }

    public SalesFicheDiscountProps component61() {
        return this.salesFicheDiscountProps;
    }

    public FicheRefProp component62() {
        return this.currType;
    }

    public boolean component63() {
        return this.isNotUnitChange;
    }

    public String component64() {
        return this.campaignCode;
    }

    public String component65() {
        return this.campaignCode2;
    }

    public String component66() {
        return this.campaignCode3;
    }

    public String component67() {
        return this.campaignCode4;
    }

    public String component68() {
        return this.campaignCode5;
    }

    public String component69() {
        return this.campaignLineNo;
    }

    public String component7() {
        return this.name;
    }

    public String component70() {
        return this.campaignLineNo2;
    }

    public String component71() {
        return this.campaignLineNo3;
    }

    public String component72() {
        return this.campaignLineNo4;
    }

    public String component73() {
        return this.campaignLineNo5;
    }

    public String component74() {
        return this.guid;
    }

    public int component75() {
        return this.globalLineType;
    }

    public boolean component76() {
        return this.isDivUnit;
    }

    public int component77() {
        return this.orderDetailReference;
    }

    public double component78() {
        return this.orderAvailableAmount;
    }

    public double component79() {
        return this.orderAmount;
    }

    public int component8() {
        return this.trackType;
    }

    public int component80() {
        return this.orderReference;
    }

    public String component81() {
        return this.sipNum;
    }

    public int component82() {
        return this.sipKont;
    }

    public int component83() {
        return this.distributionRef;
    }

    public int component84() {
        return this.distributionLineRef;
    }

    public int component85() {
        return this.addTaxRef;
    }

    public boolean component86() {
        return this.isLocTracking;
    }

    public FicheStringProp component87() {
        return this.secondAmount;
    }

    public FicheDiscountRefProp component88() {
        return this.secondUnit;
    }

    public int component89() {
        return this.previousPriceRef;
    }

    public boolean component9() {
        return this.isProduct;
    }

    public boolean component90() {
        return this.isFoundByStoredProcedure;
    }

    public ArrayList<String> component91() {
        return this.searchBarcodes;
    }

    public FicheDateProp component92() {
        return this.dueDate;
    }

    public FicheStringProp component93() {
        return this.surplusAmount;
    }

    public int component94() {
        return this.barcodeCount;
    }

    public String component95() {
        return this.campaignDiscountRatio;
    }

    public SalesDetail copy(long j2, int r124, int r125, String str, int r127, int r128, String str2, int r130, boolean z, boolean z2, int r133, int r134, String serialLotCodeList, int r136, int r137, double d2, double d3, int r142, double d4, double d5, double d6, int r149, String str3, double d7, String str4, String str5, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17, double d18, double d19, double d20, double d21, double d22, boolean z3, FicheRefProp wareHouse, FicheRefProp referenceCode, ArrayList<Serilot> salesSerialLots, FicheStringProp amount, FicheDiscountRefProp unit, FicheRefProp price, FicheRefProp selectedPriceType, FicheDiscountRefProp selectedPrice, FicheBooleanProp promotion, FicheDiscountRefProp variant, FicheStringProp vat, FicheBooleanProp includeVat, FicheDateProp deliveryDate, FicheBooleanProp reserve, FicheDiscountRefProp payment, FicheRefProp speCode, FicheRefProp deliveryCode, FicheStringProp explanation, SalesFicheDiscountProps salesFicheDiscountProps, FicheRefProp currType, boolean z4, String campaignCode, String campaignCode2, String campaignCode3, String campaignCode4, String campaignCode5, String campaignLineNo, String campaignLineNo2, String campaignLineNo3, String campaignLineNo4, String campaignLineNo5, String guid, int r218, boolean z5, int r220, double d23, double d24, int r225, String str6, int r227, int r228, int r229, int r230, boolean z6, FicheStringProp secondAmount, FicheDiscountRefProp secondUnit, int r234, boolean z7, ArrayList<String> arrayList, FicheDateProp ficheDateProp, FicheStringProp ficheStringProp, int r239, String campaignDiscountRatio) {
        Intrinsics.checkNotNullParameter(serialLotCodeList, "serialLotCodeList");
        Intrinsics.checkNotNullParameter(wareHouse, "wareHouse");
        Intrinsics.checkNotNullParameter(referenceCode, "referenceCode");
        Intrinsics.checkNotNullParameter(salesSerialLots, "salesSerialLots");
        Intrinsics.checkNotNullParameter(amount, "amount");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(selectedPriceType, "selectedPriceType");
        Intrinsics.checkNotNullParameter(selectedPrice, "selectedPrice");
        Intrinsics.checkNotNullParameter(promotion, "promotion");
        Intrinsics.checkNotNullParameter(variant, "variant");
        Intrinsics.checkNotNullParameter(vat, "vat");
        Intrinsics.checkNotNullParameter(includeVat, "includeVat");
        Intrinsics.checkNotNullParameter(deliveryDate, "deliveryDate");
        Intrinsics.checkNotNullParameter(reserve, "reserve");
        Intrinsics.checkNotNullParameter(payment, "payment");
        Intrinsics.checkNotNullParameter(speCode, "speCode");
        Intrinsics.checkNotNullParameter(deliveryCode, "deliveryCode");
        Intrinsics.checkNotNullParameter(explanation, "explanation");
        Intrinsics.checkNotNullParameter(salesFicheDiscountProps, "salesFicheDiscountProps");
        Intrinsics.checkNotNullParameter(currType, "currType");
        Intrinsics.checkNotNullParameter(campaignCode, "campaignCode");
        Intrinsics.checkNotNullParameter(campaignCode2, "campaignCode2");
        Intrinsics.checkNotNullParameter(campaignCode3, "campaignCode3");
        Intrinsics.checkNotNullParameter(campaignCode4, "campaignCode4");
        Intrinsics.checkNotNullParameter(campaignCode5, "campaignCode5");
        Intrinsics.checkNotNullParameter(campaignLineNo, "campaignLineNo");
        Intrinsics.checkNotNullParameter(campaignLineNo2, "campaignLineNo2");
        Intrinsics.checkNotNullParameter(campaignLineNo3, "campaignLineNo3");
        Intrinsics.checkNotNullParameter(campaignLineNo4, "campaignLineNo4");
        Intrinsics.checkNotNullParameter(campaignLineNo5, "campaignLineNo5");
        Intrinsics.checkNotNullParameter(guid, "guid");
        Intrinsics.checkNotNullParameter(secondAmount, "secondAmount");
        Intrinsics.checkNotNullParameter(secondUnit, "secondUnit");
        Intrinsics.checkNotNullParameter(campaignDiscountRatio, "campaignDiscountRatio");
        return new SalesDetail(j2, r124, r125, str, r127, r128, str2, r130, z, z2, r133, r134, serialLotCodeList, r136, r137, d2, d3, r142, d4, d5, d6, r149, str3, d7, str4, str5, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20, d21, d22, z3, wareHouse, referenceCode, salesSerialLots, amount, unit, price, selectedPriceType, selectedPrice, promotion, variant, vat, includeVat, deliveryDate, reserve, payment, speCode, deliveryCode, explanation, salesFicheDiscountProps, currType, z4, campaignCode, campaignCode2, campaignCode3, campaignCode4, campaignCode5, campaignLineNo, campaignLineNo2, campaignLineNo3, campaignLineNo4, campaignLineNo5, guid, r218, z5, r220, d23, d24, r225, str6, r227, r228, r229, r230, z6, secondAmount, secondUnit, r234, z7, arrayList, ficheDateProp, ficheStringProp, r239, campaignDiscountRatio);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesDetail salesDetail)) {
            return false;
        }
        return this.f1275id == salesDetail.f1275id && this.logicalRef == salesDetail.logicalRef && this.itemRef == salesDetail.itemRef && Intrinsics.areEqual(this.code, salesDetail.code) && this.salesFicheId == salesDetail.salesFicheId && this.salesType == salesDetail.salesType && Intrinsics.areEqual(this.name, salesDetail.name) && this.trackType == salesDetail.trackType && this.isProduct == salesDetail.isProduct && this.isHasVariant == salesDetail.isHasVariant && this.lineNumber == salesDetail.lineNumber && this.serialLotTransfer == salesDetail.serialLotTransfer && Intrinsics.areEqual(this.serialLotCodeList, salesDetail.serialLotCodeList) && this.lineType == salesDetail.lineType && this.cardType == salesDetail.cardType && Double.compare(this.convFact1, salesDetail.convFact1) == 0 && Double.compare(this.convFact2, salesDetail.convFact2) == 0 && this.lineNr == salesDetail.lineNr && Double.compare(this.calculateCurrPrice, salesDetail.calculateCurrPrice) == 0 && Double.compare(this.usePrice, salesDetail.usePrice) == 0 && Double.compare(this.enteryPrice, salesDetail.enteryPrice) == 0 && this.prCurrType == salesDetail.prCurrType && Intrinsics.areEqual(this.curCodeStr, salesDetail.curCodeStr) && Double.compare(this.prRate, salesDetail.prRate) == 0 && Intrinsics.areEqual(this.priceWithDigit, salesDetail.priceWithDigit) && Intrinsics.areEqual(this.priceWithCurCode, salesDetail.priceWithCurCode) && Double.compare(this.productVatAmnt, salesDetail.productVatAmnt) == 0 && Double.compare(this.productVatMatrah, salesDetail.productVatMatrah) == 0 && Double.compare(this.productDiscountTotal, salesDetail.productDiscountTotal) == 0 && Double.compare(this.productAmountTotal, salesDetail.productAmountTotal) == 0 && Double.compare(this.productLineNet, salesDetail.productLineNet) == 0 && Double.compare(this.productGrossTotal, salesDetail.productGrossTotal) == 0 && Double.compare(this.productTotal, salesDetail.productTotal) == 0 && Double.compare(this.netVolume, salesDetail.netVolume) == 0 && Double.compare(this.width, salesDetail.width) == 0 && Double.compare(this.length, salesDetail.length) == 0 && Double.compare(this.height, salesDetail.height) == 0 && Double.compare(this.grossVolume, salesDetail.grossVolume) == 0 && Double.compare(this.netWeight, salesDetail.netWeight) == 0 && Double.compare(this.grossWeight, salesDetail.grossWeight) == 0 && Double.compare(this.actualStock, salesDetail.actualStock) == 0 && this.isPriceSet == salesDetail.isPriceSet && Intrinsics.areEqual(this.wareHouse, salesDetail.wareHouse) && Intrinsics.areEqual(this.referenceCode, salesDetail.referenceCode) && Intrinsics.areEqual(this.salesSerialLots, salesDetail.salesSerialLots) && Intrinsics.areEqual(this.amount, salesDetail.amount) && Intrinsics.areEqual(this.unit, salesDetail.unit) && Intrinsics.areEqual(this.price, salesDetail.price) && Intrinsics.areEqual(this.selectedPriceType, salesDetail.selectedPriceType) && Intrinsics.areEqual(this.selectedPrice, salesDetail.selectedPrice) && Intrinsics.areEqual(this.promotion, salesDetail.promotion) && Intrinsics.areEqual(this.variant, salesDetail.variant) && Intrinsics.areEqual(this.vat, salesDetail.vat) && Intrinsics.areEqual(this.includeVat, salesDetail.includeVat) && Intrinsics.areEqual(this.deliveryDate, salesDetail.deliveryDate) && Intrinsics.areEqual(this.reserve, salesDetail.reserve) && Intrinsics.areEqual(this.payment, salesDetail.payment) && Intrinsics.areEqual(this.speCode, salesDetail.speCode) && Intrinsics.areEqual(this.deliveryCode, salesDetail.deliveryCode) && Intrinsics.areEqual(this.explanation, salesDetail.explanation) && Intrinsics.areEqual(this.salesFicheDiscountProps, salesDetail.salesFicheDiscountProps) && Intrinsics.areEqual(this.currType, salesDetail.currType) && this.isNotUnitChange == salesDetail.isNotUnitChange && Intrinsics.areEqual(this.campaignCode, salesDetail.campaignCode) && Intrinsics.areEqual(this.campaignCode2, salesDetail.campaignCode2) && Intrinsics.areEqual(this.campaignCode3, salesDetail.campaignCode3) && Intrinsics.areEqual(this.campaignCode4, salesDetail.campaignCode4) && Intrinsics.areEqual(this.campaignCode5, salesDetail.campaignCode5) && Intrinsics.areEqual(this.campaignLineNo, salesDetail.campaignLineNo) && Intrinsics.areEqual(this.campaignLineNo2, salesDetail.campaignLineNo2) && Intrinsics.areEqual(this.campaignLineNo3, salesDetail.campaignLineNo3) && Intrinsics.areEqual(this.campaignLineNo4, salesDetail.campaignLineNo4) && Intrinsics.areEqual(this.campaignLineNo5, salesDetail.campaignLineNo5) && Intrinsics.areEqual(this.guid, salesDetail.guid) && this.globalLineType == salesDetail.globalLineType && this.isDivUnit == salesDetail.isDivUnit && this.orderDetailReference == salesDetail.orderDetailReference && Double.compare(this.orderAvailableAmount, salesDetail.orderAvailableAmount) == 0 && Double.compare(this.orderAmount, salesDetail.orderAmount) == 0 && this.orderReference == salesDetail.orderReference && Intrinsics.areEqual(this.sipNum, salesDetail.sipNum) && this.sipKont == salesDetail.sipKont && this.distributionRef == salesDetail.distributionRef && this.distributionLineRef == salesDetail.distributionLineRef && this.addTaxRef == salesDetail.addTaxRef && this.isLocTracking == salesDetail.isLocTracking && Intrinsics.areEqual(this.secondAmount, salesDetail.secondAmount) && Intrinsics.areEqual(this.secondUnit, salesDetail.secondUnit) && this.previousPriceRef == salesDetail.previousPriceRef && this.isFoundByStoredProcedure == salesDetail.isFoundByStoredProcedure && Intrinsics.areEqual(this.searchBarcodes, salesDetail.searchBarcodes) && Intrinsics.areEqual(this.dueDate, salesDetail.dueDate) && Intrinsics.areEqual(this.surplusAmount, salesDetail.surplusAmount) && this.barcodeCount == salesDetail.barcodeCount && Intrinsics.areEqual(this.campaignDiscountRatio, salesDetail.campaignDiscountRatio);
    }

    public int hashCode() {
        int r0 = ((((Long.hashCode(this.f1275id) * 31) + Integer.hashCode(this.logicalRef)) * 31) + Integer.hashCode(this.itemRef)) * 31;
        String str = this.code;
        int r02 = (((((r0 + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.salesFicheId)) * 31) + Integer.hashCode(this.salesType)) * 31;
        String str2 = this.name;
        int r03 = (((((((((((((((((((((((((((((((r02 + (str2 == null ? 0 : str2.hashCode())) * 31) + Integer.hashCode(this.trackType)) * 31) + Boolean.hashCode(this.isProduct)) * 31) + Boolean.hashCode(this.isHasVariant)) * 31) + Integer.hashCode(this.lineNumber)) * 31) + Integer.hashCode(this.serialLotTransfer)) * 31) + this.serialLotCodeList.hashCode()) * 31) + Integer.hashCode(this.lineType)) * 31) + Integer.hashCode(this.cardType)) * 31) + Double.hashCode(this.convFact1)) * 31) + Double.hashCode(this.convFact2)) * 31) + Integer.hashCode(this.lineNr)) * 31) + Double.hashCode(this.calculateCurrPrice)) * 31) + Double.hashCode(this.usePrice)) * 31) + Double.hashCode(this.enteryPrice)) * 31) + Integer.hashCode(this.prCurrType)) * 31;
        String str3 = this.curCodeStr;
        int r04 = (((r03 + (str3 == null ? 0 : str3.hashCode())) * 31) + Double.hashCode(this.prRate)) * 31;
        String str4 = this.priceWithDigit;
        int r05 = (r04 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.priceWithCurCode;
        int r06 = (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((r05 + (str5 == null ? 0 : str5.hashCode())) * 31) + Double.hashCode(this.productVatAmnt)) * 31) + Double.hashCode(this.productVatMatrah)) * 31) + Double.hashCode(this.productDiscountTotal)) * 31) + Double.hashCode(this.productAmountTotal)) * 31) + Double.hashCode(this.productLineNet)) * 31) + Double.hashCode(this.productGrossTotal)) * 31) + Double.hashCode(this.productTotal)) * 31) + Double.hashCode(this.netVolume)) * 31) + Double.hashCode(this.width)) * 31) + Double.hashCode(this.length)) * 31) + Double.hashCode(this.height)) * 31) + Double.hashCode(this.grossVolume)) * 31) + Double.hashCode(this.netWeight)) * 31) + Double.hashCode(this.grossWeight)) * 31) + Double.hashCode(this.actualStock)) * 31) + Boolean.hashCode(this.isPriceSet)) * 31) + this.wareHouse.hashCode()) * 31) + this.referenceCode.hashCode()) * 31) + this.salesSerialLots.hashCode()) * 31) + this.amount.hashCode()) * 31) + this.unit.hashCode()) * 31) + this.price.hashCode()) * 31) + this.selectedPriceType.hashCode()) * 31) + this.selectedPrice.hashCode()) * 31) + this.promotion.hashCode()) * 31) + this.variant.hashCode()) * 31) + this.vat.hashCode()) * 31) + this.includeVat.hashCode()) * 31) + this.deliveryDate.hashCode()) * 31) + this.reserve.hashCode()) * 31) + this.payment.hashCode()) * 31) + this.speCode.hashCode()) * 31) + this.deliveryCode.hashCode()) * 31) + this.explanation.hashCode()) * 31) + this.salesFicheDiscountProps.hashCode()) * 31) + this.currType.hashCode()) * 31) + Boolean.hashCode(this.isNotUnitChange)) * 31) + this.campaignCode.hashCode()) * 31) + this.campaignCode2.hashCode()) * 31) + this.campaignCode3.hashCode()) * 31) + this.campaignCode4.hashCode()) * 31) + this.campaignCode5.hashCode()) * 31) + this.campaignLineNo.hashCode()) * 31) + this.campaignLineNo2.hashCode()) * 31) + this.campaignLineNo3.hashCode()) * 31) + this.campaignLineNo4.hashCode()) * 31) + this.campaignLineNo5.hashCode()) * 31) + this.guid.hashCode()) * 31) + Integer.hashCode(this.globalLineType)) * 31) + Boolean.hashCode(this.isDivUnit)) * 31) + Integer.hashCode(this.orderDetailReference)) * 31) + Double.hashCode(this.orderAvailableAmount)) * 31) + Double.hashCode(this.orderAmount)) * 31) + Integer.hashCode(this.orderReference)) * 31;
        String str6 = this.sipNum;
        int r07 = (((((((((((((((((((r06 + (str6 == null ? 0 : str6.hashCode())) * 31) + Integer.hashCode(this.sipKont)) * 31) + Integer.hashCode(this.distributionRef)) * 31) + Integer.hashCode(this.distributionLineRef)) * 31) + Integer.hashCode(this.addTaxRef)) * 31) + Boolean.hashCode(this.isLocTracking)) * 31) + this.secondAmount.hashCode()) * 31) + this.secondUnit.hashCode()) * 31) + Integer.hashCode(this.previousPriceRef)) * 31) + Boolean.hashCode(this.isFoundByStoredProcedure)) * 31;
        ArrayList<String> arrayList = this.searchBarcodes;
        int r08 = (r07 + (arrayList == null ? 0 : arrayList.hashCode())) * 31;
        FicheDateProp ficheDateProp = this.dueDate;
        int r09 = (r08 + (ficheDateProp == null ? 0 : ficheDateProp.hashCode())) * 31;
        FicheStringProp ficheStringProp = this.surplusAmount;
        return ((((r09 + (ficheStringProp != null ? ficheStringProp.hashCode() : 0)) * 31) + Integer.hashCode(this.barcodeCount)) * 31) + this.campaignDiscountRatio.hashCode();
    }

    public String toString() {
        return "SalesDetail(id=" + this.f1275id + ", logicalRef=" + this.logicalRef + ", itemRef=" + this.itemRef + ", code=" + this.code + ", salesFicheId=" + this.salesFicheId + ", salesType=" + this.salesType + ", name=" + this.name + ", trackType=" + this.trackType + ", isProduct=" + this.isProduct + ", isHasVariant=" + this.isHasVariant + ", lineNumber=" + this.lineNumber + ", serialLotTransfer=" + this.serialLotTransfer + ", serialLotCodeList=" + this.serialLotCodeList + ", lineType=" + this.lineType + ", cardType=" + this.cardType + ", convFact1=" + this.convFact1 + ", convFact2=" + this.convFact2 + ", lineNr=" + this.lineNr + ", calculateCurrPrice=" + this.calculateCurrPrice + ", usePrice=" + this.usePrice + ", enteryPrice=" + this.enteryPrice + ", prCurrType=" + this.prCurrType + ", curCodeStr=" + this.curCodeStr + ", prRate=" + this.prRate + ", priceWithDigit=" + this.priceWithDigit + ", priceWithCurCode=" + this.priceWithCurCode + ", productVatAmnt=" + this.productVatAmnt + ", productVatMatrah=" + this.productVatMatrah + ", productDiscountTotal=" + this.productDiscountTotal + ", productAmountTotal=" + this.productAmountTotal + ", productLineNet=" + this.productLineNet + ", productGrossTotal=" + this.productGrossTotal + ", productTotal=" + this.productTotal + ", netVolume=" + this.netVolume + ", width=" + this.width + ", length=" + this.length + ", height=" + this.height + ", grossVolume=" + this.grossVolume + ", netWeight=" + this.netWeight + ", grossWeight=" + this.grossWeight + ", actualStock=" + this.actualStock + ", isPriceSet=" + this.isPriceSet + ", wareHouse=" + this.wareHouse + ", referenceCode=" + this.referenceCode + ", salesSerialLots=" + this.salesSerialLots + ", amount=" + this.amount + ", unit=" + this.unit + ", price=" + this.price + ", selectedPriceType=" + this.selectedPriceType + ", selectedPrice=" + this.selectedPrice + ", promotion=" + this.promotion + ", variant=" + this.variant + ", vat=" + this.vat + ", includeVat=" + this.includeVat + ", deliveryDate=" + this.deliveryDate + ", reserve=" + this.reserve + ", payment=" + this.payment + ", speCode=" + this.speCode + ", deliveryCode=" + this.deliveryCode + ", explanation=" + this.explanation + ", salesFicheDiscountProps=" + this.salesFicheDiscountProps + ", currType=" + this.currType + ", isNotUnitChange=" + this.isNotUnitChange + ", campaignCode=" + this.campaignCode + ", campaignCode2=" + this.campaignCode2 + ", campaignCode3=" + this.campaignCode3 + ", campaignCode4=" + this.campaignCode4 + ", campaignCode5=" + this.campaignCode5 + ", campaignLineNo=" + this.campaignLineNo + ", campaignLineNo2=" + this.campaignLineNo2 + ", campaignLineNo3=" + this.campaignLineNo3 + ", campaignLineNo4=" + this.campaignLineNo4 + ", campaignLineNo5=" + this.campaignLineNo5 + ", guid=" + this.guid + ", globalLineType=" + this.globalLineType + ", isDivUnit=" + this.isDivUnit + ", orderDetailReference=" + this.orderDetailReference + ", orderAvailableAmount=" + this.orderAvailableAmount + ", orderAmount=" + this.orderAmount + ", orderReference=" + this.orderReference + ", sipNum=" + this.sipNum + ", sipKont=" + this.sipKont + ", distributionRef=" + this.distributionRef + ", distributionLineRef=" + this.distributionLineRef + ", addTaxRef=" + this.addTaxRef + ", isLocTracking=" + this.isLocTracking + ", secondAmount=" + this.secondAmount + ", secondUnit=" + this.secondUnit + ", previousPriceRef=" + this.previousPriceRef + ", isFoundByStoredProcedure=" + this.isFoundByStoredProcedure + ", searchBarcodes=" + this.searchBarcodes + ", dueDate=" + this.dueDate + ", surplusAmount=" + this.surplusAmount + ", barcodeCount=" + this.barcodeCount + ", campaignDiscountRatio=" + this.campaignDiscountRatio + ')';
    }

    public SalesDetail(long j2, int r19, int r20, String str, int r22, int r23, String str2, int r25, boolean z, boolean z2, int r28, int r29, String serialLotCodeList, int r31, int r32, double d2, double d3, int r37, double d4, double d5, double d6, int r44, String str3, double d7, String str4, String str5, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17, double d18, double d19, double d20, double d21, double d22, boolean z3, FicheRefProp wareHouse, FicheRefProp referenceCode, ArrayList<Serilot> salesSerialLots, FicheStringProp amount, FicheDiscountRefProp unit, FicheRefProp price, FicheRefProp selectedPriceType, FicheDiscountRefProp selectedPrice, FicheBooleanProp promotion, FicheDiscountRefProp variant, FicheStringProp vat, FicheBooleanProp includeVat, FicheDateProp deliveryDate, FicheBooleanProp reserve, FicheDiscountRefProp payment, FicheRefProp speCode, FicheRefProp deliveryCode, FicheStringProp explanation, SalesFicheDiscountProps salesFicheDiscountProps, FicheRefProp currType, boolean z4, String campaignCode, String campaignCode2, String campaignCode3, String campaignCode4, String campaignCode5, String campaignLineNo, String campaignLineNo2, String campaignLineNo3, String campaignLineNo4, String campaignLineNo5, String guid, int r113, boolean z5, int r115, double d23, double d24, int r120, String str6, int r122, int r123, int r124, int r125, boolean z6, FicheStringProp secondAmount, FicheDiscountRefProp secondUnit, int r129, boolean z7, ArrayList<String> arrayList, FicheDateProp ficheDateProp, FicheStringProp ficheStringProp, int r134, String campaignDiscountRatio) {
        Intrinsics.checkNotNullParameter(serialLotCodeList, "serialLotCodeList");
        Intrinsics.checkNotNullParameter(wareHouse, "wareHouse");
        Intrinsics.checkNotNullParameter(referenceCode, "referenceCode");
        Intrinsics.checkNotNullParameter(salesSerialLots, "salesSerialLots");
        Intrinsics.checkNotNullParameter(amount, "amount");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(selectedPriceType, "selectedPriceType");
        Intrinsics.checkNotNullParameter(selectedPrice, "selectedPrice");
        Intrinsics.checkNotNullParameter(promotion, "promotion");
        Intrinsics.checkNotNullParameter(variant, "variant");
        Intrinsics.checkNotNullParameter(vat, "vat");
        Intrinsics.checkNotNullParameter(includeVat, "includeVat");
        Intrinsics.checkNotNullParameter(deliveryDate, "deliveryDate");
        Intrinsics.checkNotNullParameter(reserve, "reserve");
        Intrinsics.checkNotNullParameter(payment, "payment");
        Intrinsics.checkNotNullParameter(speCode, "speCode");
        Intrinsics.checkNotNullParameter(deliveryCode, "deliveryCode");
        Intrinsics.checkNotNullParameter(explanation, "explanation");
        Intrinsics.checkNotNullParameter(salesFicheDiscountProps, "salesFicheDiscountProps");
        Intrinsics.checkNotNullParameter(currType, "currType");
        Intrinsics.checkNotNullParameter(campaignCode, "campaignCode");
        Intrinsics.checkNotNullParameter(campaignCode2, "campaignCode2");
        Intrinsics.checkNotNullParameter(campaignCode3, "campaignCode3");
        Intrinsics.checkNotNullParameter(campaignCode4, "campaignCode4");
        Intrinsics.checkNotNullParameter(campaignCode5, "campaignCode5");
        Intrinsics.checkNotNullParameter(campaignLineNo, "campaignLineNo");
        Intrinsics.checkNotNullParameter(campaignLineNo2, "campaignLineNo2");
        Intrinsics.checkNotNullParameter(campaignLineNo3, "campaignLineNo3");
        Intrinsics.checkNotNullParameter(campaignLineNo4, "campaignLineNo4");
        Intrinsics.checkNotNullParameter(campaignLineNo5, "campaignLineNo5");
        Intrinsics.checkNotNullParameter(guid, "guid");
        Intrinsics.checkNotNullParameter(secondAmount, "secondAmount");
        Intrinsics.checkNotNullParameter(secondUnit, "secondUnit");
        Intrinsics.checkNotNullParameter(campaignDiscountRatio, "campaignDiscountRatio");
        this.f1275id = j2;
        this.logicalRef = r19;
        this.itemRef = r20;
        this.code = str;
        this.salesFicheId = r22;
        this.salesType = r23;
        this.name = str2;
        this.trackType = r25;
        this.isProduct = z;
        this.isHasVariant = z2;
        this.lineNumber = r28;
        this.serialLotTransfer = r29;
        this.serialLotCodeList = serialLotCodeList;
        this.lineType = r31;
        this.cardType = r32;
        this.convFact1 = d2;
        this.convFact2 = d3;
        this.lineNr = r37;
        this.calculateCurrPrice = d4;
        this.usePrice = d5;
        this.enteryPrice = d6;
        this.prCurrType = r44;
        this.curCodeStr = str3;
        this.prRate = d7;
        this.priceWithDigit = str4;
        this.priceWithCurCode = str5;
        this.productVatAmnt = d8;
        this.productVatMatrah = d9;
        this.productDiscountTotal = d10;
        this.productAmountTotal = d11;
        this.productLineNet = d12;
        this.productGrossTotal = d13;
        this.productTotal = d14;
        this.netVolume = d15;
        this.width = d16;
        this.length = d17;
        this.height = d18;
        this.grossVolume = d19;
        this.netWeight = d20;
        this.grossWeight = d21;
        this.actualStock = d22;
        this.isPriceSet = z3;
        this.wareHouse = wareHouse;
        this.referenceCode = referenceCode;
        this.salesSerialLots = salesSerialLots;
        this.amount = amount;
        this.unit = unit;
        this.price = price;
        this.selectedPriceType = selectedPriceType;
        this.selectedPrice = selectedPrice;
        this.promotion = promotion;
        this.variant = variant;
        this.vat = vat;
        this.includeVat = includeVat;
        this.deliveryDate = deliveryDate;
        this.reserve = reserve;
        this.payment = payment;
        this.speCode = speCode;
        this.deliveryCode = deliveryCode;
        this.explanation = explanation;
        this.salesFicheDiscountProps = salesFicheDiscountProps;
        this.currType = currType;
        this.isNotUnitChange = z4;
        this.campaignCode = campaignCode;
        this.campaignCode2 = campaignCode2;
        this.campaignCode3 = campaignCode3;
        this.campaignCode4 = campaignCode4;
        this.campaignCode5 = campaignCode5;
        this.campaignLineNo = campaignLineNo;
        this.campaignLineNo2 = campaignLineNo2;
        this.campaignLineNo3 = campaignLineNo3;
        this.campaignLineNo4 = campaignLineNo4;
        this.campaignLineNo5 = campaignLineNo5;
        this.guid = guid;
        this.globalLineType = r113;
        this.isDivUnit = z5;
        this.orderDetailReference = r115;
        this.orderAvailableAmount = d23;
        this.orderAmount = d24;
        this.orderReference = r120;
        this.sipNum = str6;
        this.sipKont = r122;
        this.distributionRef = r123;
        this.distributionLineRef = r124;
        this.addTaxRef = r125;
        this.isLocTracking = z6;
        this.secondAmount = secondAmount;
        this.secondUnit = secondUnit;
        this.previousPriceRef = r129;
        this.isFoundByStoredProcedure = z7;
        this.searchBarcodes = arrayList;
        this.dueDate = ficheDateProp;
        this.surplusAmount = ficheStringProp;
        this.barcodeCount = r134;
        this.campaignDiscountRatio = campaignDiscountRatio;
        SalesDetailRepository salesDetailRepository = new SalesDetailRepository();
        this.repository = salesDetailRepository;
        this.viewModel = new SalesDetailViewModel(salesDetailRepository);
        addPropertyChangeListenerForSelectedPriceRef(this);
    }

    public /* synthetic */ SalesDetail(long j2, int r107, int r108, String str, int r110, int r111, String str2, int r113, boolean z, boolean z2, int r116, int r117, String str3, int r119, int r120, double d2, double d3, int r125, double d4, double d5, double d6, int r132, String str4, double d7, String str5, String str6, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17, double d18, double d19, double d20, double d21, double d22, boolean z3, FicheRefProp ficheRefProp, FicheRefProp ficheRefProp2, ArrayList arrayList, FicheStringProp ficheStringProp, FicheDiscountRefProp ficheDiscountRefProp, FicheRefProp ficheRefProp3, FicheRefProp ficheRefProp4, FicheDiscountRefProp ficheDiscountRefProp2, FicheBooleanProp ficheBooleanProp, FicheDiscountRefProp ficheDiscountRefProp3, FicheStringProp ficheStringProp2, FicheBooleanProp ficheBooleanProp2, FicheDateProp ficheDateProp, FicheBooleanProp ficheBooleanProp3, FicheDiscountRefProp ficheDiscountRefProp4, FicheRefProp ficheRefProp5, FicheRefProp ficheRefProp6, FicheStringProp ficheStringProp3, SalesFicheDiscountProps salesFicheDiscountProps, FicheRefProp ficheRefProp7, boolean z4, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, int r201, boolean z5, int r203, double d23, double d24, int r208, String str18, int r210, int r211, int r212, int r213, boolean z6, FicheStringProp ficheStringProp4, FicheDiscountRefProp ficheDiscountRefProp5, int r217, boolean z7, ArrayList arrayList2, FicheDateProp ficheDateProp2, FicheStringProp ficheStringProp5, int r222, String str19, int r224, int r225, int r226, DefaultConstructorMarker defaultConstructorMarker) {
        int r1202;
        SalesFicheDiscountProps salesFicheDiscountProps2;
        FicheRefProp ficheRefProp8;
        String string;
        long jGenerateNextId = (r224 & 1) != 0 ? IdGenerator.INSTANCE.generateNextId() : j2;
        int r5 = (r224 & 2) != 0 ? 0 : r107;
        int r7 = (r224 & 4) != 0 ? 0 : r108;
        String str20 = (r224 & 8) != 0 ? null : str;
        int r10 = (r224 & 16) != 0 ? 0 : r110;
        int r11 = (r224 & 32) != 0 ? 0 : r111;
        String str21 = (r224 & 64) != 0 ? null : str2;
        int r13 = (r224 & 128) != 0 ? 0 : r113;
        boolean z8 = (r224 & 256) == 0 && z;
        boolean z9 = (r224 & 512) == 0 && z2;
        int r6 = (r224 & 1024) != 0 ? 0 : r116;
        int r1172 = (r224 & 2048) != 0 ? 0 : r117;
        String str22 = (r224 & 4096) != 0 ? "" : str3;
        int r1192 = (r224 & 8192) != 0 ? 0 : r119;
        int r9 = (r224 & 16384) != 0 ? 0 : r120;
        double d25 = (r224 & 32768) != 0 ? 0.0d : d2;
        double d26 = (r224 & 65536) != 0 ? 0.0d : d3;
        int r26 = (r224 & 131072) != 0 ? 0 : r125;
        double d27 = (r224 & 262144) != 0 ? 0.0d : d4;
        double d28 = (r224 & 524288) != 0 ? 0.0d : d5;
        double d29 = (r224 & 1048576) != 0 ? 0.0d : d6;
        int r33 = (r224 & 2097152) != 0 ? 0 : r132;
        String str23 = (r224 & 4194304) != 0 ? null : str4;
        double d30 = (r224 & 8388608) != 0 ? 1.0d : d7;
        String str24 = (r224 & 16777216) != 0 ? null : str5;
        String str25 = (r224 & 33554432) != 0 ? null : str6;
        double d31 = (r224 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? 0.0d : d8;
        double d32 = (r224 & 134217728) != 0 ? 0.0d : d9;
        double d33 = (r224 & 268435456) != 0 ? 0.0d : d10;
        double d34 = (r224 & 536870912) != 0 ? 0.0d : d11;
        double d35 = (r224 & BasicMeasure.EXACTLY) != 0 ? 0.0d : d12;
        double d36 = (r224 & Integer.MIN_VALUE) != 0 ? 0.0d : d13;
        double d37 = (r225 & 1) != 0 ? 0.0d : d14;
        double d38 = (r225 & 2) != 0 ? 0.0d : d15;
        double d39 = (r225 & 4) != 0 ? 0.0d : d16;
        double d40 = (r225 & 8) != 0 ? 0.0d : d17;
        double d41 = (r225 & 16) != 0 ? 0.0d : d18;
        double d42 = (r225 & 32) != 0 ? 0.0d : d19;
        double d43 = (r225 & 64) != 0 ? 0.0d : d20;
        double d44 = (r225 & 128) != 0 ? 0.0d : d21;
        double d45 = (r225 & 256) != 0 ? 0.0d : d22;
        boolean z10 = (r225 & 512) == 0 && z3;
        FicheRefProp ficheRefProp9 = (r225 & 1024) != 0 ? new FicheRefProp() : ficheRefProp;
        FicheRefProp ficheRefProp10 = (r225 & 2048) != 0 ? new FicheRefProp() : ficheRefProp2;
        ArrayList arrayList3 = (r225 & 4096) != 0 ? new ArrayList() : arrayList;
        FicheStringProp ficheStringProp6 = (r225 & 8192) != 0 ? new FicheStringProp() : ficheStringProp;
        FicheDiscountRefProp ficheDiscountRefProp6 = (r225 & 16384) != 0 ? new FicheDiscountRefProp() : ficheDiscountRefProp;
        FicheRefProp ficheRefProp11 = (r225 & 32768) != 0 ? new FicheRefProp() : ficheRefProp3;
        FicheRefProp ficheRefProp12 = (r225 & 65536) != 0 ? new FicheRefProp() : ficheRefProp4;
        FicheDiscountRefProp ficheDiscountRefProp7 = (r225 & 131072) != 0 ? new FicheDiscountRefProp() : ficheDiscountRefProp2;
        FicheBooleanProp ficheBooleanProp4 = (r225 & 262144) != 0 ? new FicheBooleanProp() : ficheBooleanProp;
        FicheDiscountRefProp ficheDiscountRefProp8 = (r225 & 524288) != 0 ? new FicheDiscountRefProp() : ficheDiscountRefProp3;
        FicheStringProp ficheStringProp7 = (r225 & 1048576) != 0 ? new FicheStringProp() : ficheStringProp2;
        FicheBooleanProp ficheBooleanProp5 = (r225 & 2097152) != 0 ? new FicheBooleanProp() : ficheBooleanProp2;
        FicheDateProp ficheDateProp3 = (r225 & 4194304) != 0 ? new FicheDateProp() : ficheDateProp;
        FicheBooleanProp ficheBooleanProp6 = (r225 & 8388608) != 0 ? new FicheBooleanProp() : ficheBooleanProp3;
        FicheDiscountRefProp ficheDiscountRefProp9 = (r225 & 16777216) != 0 ? new FicheDiscountRefProp() : ficheDiscountRefProp4;
        FicheRefProp ficheRefProp13 = (r225 & 33554432) != 0 ? new FicheRefProp() : ficheRefProp5;
        FicheRefProp ficheRefProp14 = (r225 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? new FicheRefProp() : ficheRefProp6;
        FicheStringProp ficheStringProp8 = (r225 & 134217728) != 0 ? new FicheStringProp() : ficheStringProp3;
        FicheDiscountRefProp ficheDiscountRefProp10 = ficheDiscountRefProp6;
        if ((r225 & 268435456) != 0) {
            r1202 = r9;
            salesFicheDiscountProps2 = new SalesFicheDiscountProps(5);
        } else {
            r1202 = r9;
            salesFicheDiscountProps2 = salesFicheDiscountProps;
        }
        FicheRefProp ficheRefProp15 = (536870912 & r225) != 0 ? new FicheRefProp() : ficheRefProp7;
        boolean z11 = (r225 & BasicMeasure.EXACTLY) == 0 && z4;
        String str26 = (r225 & Integer.MIN_VALUE) != 0 ? "" : str7;
        String str27 = (r226 & 1) != 0 ? "" : str8;
        String str28 = (r226 & 2) != 0 ? "" : str9;
        String str29 = (r226 & 4) != 0 ? "" : str10;
        String str30 = (r226 & 8) != 0 ? "" : str11;
        String str31 = (r226 & 16) != 0 ? "" : str12;
        String str32 = (r226 & 32) != 0 ? "" : str13;
        String str33 = (r226 & 64) != 0 ? "" : str14;
        String str34 = str26;
        String str35 = (r226 & 128) != 0 ? "" : str15;
        String str36 = (r226 & 256) != 0 ? "" : str16;
        if ((r226 & 512) != 0) {
            string = UUID.randomUUID().toString();
            ficheRefProp8 = ficheRefProp15;
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        } else {
            ficheRefProp8 = ficheRefProp15;
            string = str17;
        }
        this(jGenerateNextId, r5, r7, str20, r10, r11, str21, r13, z8, z9, r6, r1172, str22, r1192, r1202, d25, d26, r26, d27, d28, d29, r33, str23, d30, str24, str25, d31, d32, d33, d34, d35, d36, d37, d38, d39, d40, d41, d42, d43, d44, d45, z10, ficheRefProp9, ficheRefProp10, arrayList3, ficheStringProp6, ficheDiscountRefProp10, ficheRefProp11, ficheRefProp12, ficheDiscountRefProp7, ficheBooleanProp4, ficheDiscountRefProp8, ficheStringProp7, ficheBooleanProp5, ficheDateProp3, ficheBooleanProp6, ficheDiscountRefProp9, ficheRefProp13, ficheRefProp14, ficheStringProp8, salesFicheDiscountProps2, ficheRefProp8, z11, str34, str27, str28, str29, str30, str31, str32, str33, str35, str36, string, (r226 & 1024) != 0 ? 0 : r201, (r226 & 2048) == 0 && z5, (r226 & 4096) != 0 ? 0 : r203, (r226 & 8192) != 0 ? 0.0d : d23, (r226 & 16384) == 0 ? d24 : 0.0d, (r226 & 32768) != 0 ? 0 : r208, (r226 & 65536) != 0 ? null : str18, (r226 & 131072) != 0 ? 0 : r210, (r226 & 262144) != 0 ? 0 : r211, (r226 & 524288) != 0 ? 0 : r212, (r226 & 1048576) != 0 ? 0 : r213, (r226 & 2097152) == 0 && z6, (r226 & 4194304) != 0 ? new FicheStringProp() : ficheStringProp4, (r226 & 8388608) != 0 ? new FicheDiscountRefProp() : ficheDiscountRefProp5, (r226 & 16777216) != 0 ? 0 : r217, (r226 & 33554432) == 0 && z7, (r226 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? new ArrayList() : arrayList2, (r226 & 134217728) != 0 ? new FicheDateProp() : ficheDateProp2, (r226 & 268435456) != 0 ? new FicheStringProp() : ficheStringProp5, (r226 & 536870912) != 0 ? 0 : r222, (r226 & BasicMeasure.EXACTLY) == 0 ? str19 : "");
    }

    public long getId() {
        return this.f1275id;
    }

    public void setId(long j2) {
        this.f1275id = j2;
    }

    public int getLogicalRef() {
        return this.logicalRef;
    }

    public void setLogicalRef(int r1) {
        this.logicalRef = r1;
    }

    public int getItemRef() {
        return this.itemRef;
    }

    public void setItemRef(int r1) {
        this.itemRef = r1;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public int getSalesFicheId() {
        return this.salesFicheId;
    }

    public void setSalesFicheId(int r1) {
        this.salesFicheId = r1;
    }

    public int getSalesType() {
        return this.salesType;
    }

    public void setSalesType(int r1) {
        this.salesType = r1;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getTrackType() {
        return this.trackType;
    }

    public void setTrackType(int r1) {
        this.trackType = r1;
    }

    public boolean isProduct() {
        return this.isProduct;
    }

    public void setProduct(boolean z) {
        this.isProduct = z;
    }

    public boolean isHasVariant() {
        return this.isHasVariant;
    }

    public void setHasVariant(boolean z) {
        this.isHasVariant = z;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public void setLineNumber(int r1) {
        this.lineNumber = r1;
    }

    public int getSerialLotTransfer() {
        return this.serialLotTransfer;
    }

    public void setSerialLotTransfer(int r1) {
        this.serialLotTransfer = r1;
    }

    public int getCardType() {
        return this.cardType;
    }

    public void setCardType(int r1) {
        this.cardType = r1;
    }

    public double getConvFact1() {
        return this.convFact1;
    }

    public void setConvFact1(double d2) {
        this.convFact1 = d2;
    }

    public double getConvFact2() {
        return this.convFact2;
    }

    public void setConvFact2(double d2) {
        this.convFact2 = d2;
    }

    public int getLineNr() {
        return this.lineNr;
    }

    public void setLineNr(int r1) {
        this.lineNr = r1;
    }

    public double getCalculateCurrPrice() {
        return this.calculateCurrPrice;
    }

    public void setCalculateCurrPrice(double d2) {
        this.calculateCurrPrice = d2;
    }

    public double getUsePrice() {
        return this.usePrice;
    }

    public void setUsePrice(double d2) {
        this.usePrice = d2;
    }

    public double getEnteryPrice() {
        return this.enteryPrice;
    }

    public void setEnteryPrice(double d2) {
        this.enteryPrice = d2;
    }

    public double getPrRate() {
        return this.prRate;
    }

    public void setPrRate(double d2) {
        this.prRate = d2;
    }

    public String getPriceWithDigit() {
        return this.priceWithDigit;
    }

    public void setPriceWithDigit(String str) {
        this.priceWithDigit = str;
    }

    public String getPriceWithCurCode() {
        return this.priceWithCurCode;
    }

    public void setPriceWithCurCode(String str) {
        this.priceWithCurCode = str;
    }

    public double getProductVatAmnt() {
        return this.productVatAmnt;
    }

    public void setProductVatAmnt(double d2) {
        this.productVatAmnt = d2;
    }

    public double getProductVatMatrah() {
        return this.productVatMatrah;
    }

    public void setProductVatMatrah(double d2) {
        this.productVatMatrah = d2;
    }

    public double getProductDiscountTotal() {
        return this.productDiscountTotal;
    }

    public void setProductDiscountTotal(double d2) {
        this.productDiscountTotal = d2;
    }

    public double getProductAmountTotal() {
        return this.productAmountTotal;
    }

    public void setProductAmountTotal(double d2) {
        this.productAmountTotal = d2;
    }

    public double getProductLineNet() {
        return this.productLineNet;
    }

    public void setProductLineNet(double d2) {
        this.productLineNet = d2;
    }

    public double getProductGrossTotal() {
        return this.productGrossTotal;
    }

    public void setProductGrossTotal(double d2) {
        this.productGrossTotal = d2;
    }

    public double getProductTotal() {
        return this.productTotal;
    }

    public void setProductTotal(double d2) {
        this.productTotal = d2;
    }

    public double getNetVolume() {
        return this.netVolume;
    }

    public void setNetVolume(double d2) {
        this.netVolume = d2;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double d2) {
        this.width = d2;
    }

    public double getLength() {
        return this.length;
    }

    public void setLength(double d2) {
        this.length = d2;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double d2) {
        this.height = d2;
    }

    public double getGrossVolume() {
        return this.grossVolume;
    }

    public void setGrossVolume(double d2) {
        this.grossVolume = d2;
    }

    public double getNetWeight() {
        return this.netWeight;
    }

    public void setNetWeight(double d2) {
        this.netWeight = d2;
    }

    public double getGrossWeight() {
        return this.grossWeight;
    }

    public void setGrossWeight(double d2) {
        this.grossWeight = d2;
    }

    public double getActualStock() {
        return this.actualStock;
    }

    public void setActualStock(double d2) {
        this.actualStock = d2;
    }

    public boolean isPriceSet() {
        return this.isPriceSet;
    }

    public void setPriceSet(boolean z) {
        this.isPriceSet = z;
    }

    public FicheRefProp getWareHouse() {
        return this.wareHouse;
    }

    public void setWareHouse(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.wareHouse = ficheRefProp;
    }

    public FicheRefProp getReferenceCode() {
        return this.referenceCode;
    }

    public void setReferenceCode(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.referenceCode = ficheRefProp;
    }

    public ArrayList<Serilot> getSalesSerialLots() {
        return this.salesSerialLots;
    }

    public void setSalesSerialLots(ArrayList<Serilot> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.salesSerialLots = arrayList;
    }

    public FicheStringProp getAmount() {
        return this.amount;
    }

    public void setAmount(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.amount = ficheStringProp;
    }

    public FicheDiscountRefProp getUnit() {
        return this.unit;
    }

    public void setUnit(FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        this.unit = ficheDiscountRefProp;
    }

    public FicheRefProp getPrice() {
        return this.price;
    }

    public void setPrice(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.price = ficheRefProp;
    }

    public FicheRefProp getSelectedPriceType() {
        return this.selectedPriceType;
    }

    public void setSelectedPriceType(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.selectedPriceType = ficheRefProp;
    }

    public FicheDiscountRefProp getSelectedPrice() {
        return this.selectedPrice;
    }

    public void setSelectedPrice(FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        this.selectedPrice = ficheDiscountRefProp;
    }

    public FicheBooleanProp getPromotion() {
        return this.promotion;
    }

    public void setPromotion(FicheBooleanProp ficheBooleanProp) {
        Intrinsics.checkNotNullParameter(ficheBooleanProp, "<set-?>");
        this.promotion = ficheBooleanProp;
    }

    public FicheDiscountRefProp getVariant() {
        return this.variant;
    }

    public void setVariant(FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        this.variant = ficheDiscountRefProp;
    }

    public FicheStringProp getVat() {
        return this.vat;
    }

    public void setVat(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.vat = ficheStringProp;
    }

    public FicheBooleanProp getIncludeVat() {
        return this.includeVat;
    }

    public void setIncludeVat(FicheBooleanProp ficheBooleanProp) {
        Intrinsics.checkNotNullParameter(ficheBooleanProp, "<set-?>");
        this.includeVat = ficheBooleanProp;
    }

    public FicheDateProp getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(FicheDateProp ficheDateProp) {
        Intrinsics.checkNotNullParameter(ficheDateProp, "<set-?>");
        this.deliveryDate = ficheDateProp;
    }

    public FicheBooleanProp getReserve() {
        return this.reserve;
    }

    public void setReserve(FicheBooleanProp ficheBooleanProp) {
        Intrinsics.checkNotNullParameter(ficheBooleanProp, "<set-?>");
        this.reserve = ficheBooleanProp;
    }

    public FicheDiscountRefProp getPayment() {
        return this.payment;
    }

    public void setPayment(FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        this.payment = ficheDiscountRefProp;
    }

    public FicheRefProp getSpeCode() {
        return this.speCode;
    }

    public void setSpeCode(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.speCode = ficheRefProp;
    }

    public FicheRefProp getDeliveryCode() {
        return this.deliveryCode;
    }

    public void setDeliveryCode(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.deliveryCode = ficheRefProp;
    }

    public FicheStringProp getExplanation() {
        return this.explanation;
    }

    public void setExplanation(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.explanation = ficheStringProp;
    }

    public SalesFicheDiscountProps getSalesFicheDiscountProps() {
        return this.salesFicheDiscountProps;
    }

    public void setSalesFicheDiscountProps(SalesFicheDiscountProps salesFicheDiscountProps) {
        Intrinsics.checkNotNullParameter(salesFicheDiscountProps, "<set-?>");
        this.salesFicheDiscountProps = salesFicheDiscountProps;
    }

    public FicheRefProp getCurrType() {
        return this.currType;
    }

    public void setCurrType(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.currType = ficheRefProp;
    }

    public boolean isNotUnitChange() {
        return this.isNotUnitChange;
    }

    public void setNotUnitChange(boolean z) {
        this.isNotUnitChange = z;
    }

    public String getCampaignCode() {
        return this.campaignCode;
    }

    public void setCampaignCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.campaignCode = str;
    }

    public String getCampaignCode2() {
        return this.campaignCode2;
    }

    public void setCampaignCode2(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.campaignCode2 = str;
    }

    public String getCampaignCode3() {
        return this.campaignCode3;
    }

    public void setCampaignCode3(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.campaignCode3 = str;
    }

    public String getCampaignCode4() {
        return this.campaignCode4;
    }

    public void setCampaignCode4(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.campaignCode4 = str;
    }

    public String getCampaignCode5() {
        return this.campaignCode5;
    }

    public void setCampaignCode5(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.campaignCode5 = str;
    }

    public String getCampaignLineNo() {
        return this.campaignLineNo;
    }

    public void setCampaignLineNo(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.campaignLineNo = str;
    }

    public String getCampaignLineNo2() {
        return this.campaignLineNo2;
    }

    public void setCampaignLineNo2(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.campaignLineNo2 = str;
    }

    public String getCampaignLineNo3() {
        return this.campaignLineNo3;
    }

    public void setCampaignLineNo3(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.campaignLineNo3 = str;
    }

    public String getCampaignLineNo4() {
        return this.campaignLineNo4;
    }

    public void setCampaignLineNo4(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.campaignLineNo4 = str;
    }

    public String getCampaignLineNo5() {
        return this.campaignLineNo5;
    }

    public void setCampaignLineNo5(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.campaignLineNo5 = str;
    }

    public int getGlobalLineType() {
        return this.globalLineType;
    }

    public void setGlobalLineType(int r1) {
        this.globalLineType = r1;
    }

    public boolean isDivUnit() {
        return this.isDivUnit;
    }

    public void setDivUnit(boolean z) {
        this.isDivUnit = z;
    }

    public int getOrderDetailReference() {
        return this.orderDetailReference;
    }

    public void setOrderDetailReference(int r1) {
        this.orderDetailReference = r1;
    }

    public double getOrderAvailableAmount() {
        return this.orderAvailableAmount;
    }

    public void setOrderAvailableAmount(double d2) {
        this.orderAvailableAmount = d2;
    }

    public double getOrderAmount() {
        return this.orderAmount;
    }

    public void setOrderAmount(double d2) {
        this.orderAmount = d2;
    }

    public int getOrderReference() {
        return this.orderReference;
    }

    public void setOrderReference(int r1) {
        this.orderReference = r1;
    }

    public String getSipNum() {
        return this.sipNum;
    }

    public void setSipNum(String str) {
        this.sipNum = str;
    }

    public int getSipKont() {
        return this.sipKont;
    }

    public void setSipKont(int r1) {
        this.sipKont = r1;
    }

    public int getDistributionRef() {
        return this.distributionRef;
    }

    public void setDistributionRef(int r1) {
        this.distributionRef = r1;
    }

    public int getDistributionLineRef() {
        return this.distributionLineRef;
    }

    public void setDistributionLineRef(int r1) {
        this.distributionLineRef = r1;
    }

    public int getAddTaxRef() {
        return this.addTaxRef;
    }

    public void setAddTaxRef(int r1) {
        this.addTaxRef = r1;
    }

    public boolean isLocTracking() {
        return this.isLocTracking;
    }

    public void setLocTracking(boolean z) {
        this.isLocTracking = z;
    }

    public FicheStringProp getSecondAmount() {
        return this.secondAmount;
    }

    public void setSecondAmount(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.secondAmount = ficheStringProp;
    }

    public FicheDiscountRefProp getSecondUnit() {
        return this.secondUnit;
    }

    public void setSecondUnit(FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        this.secondUnit = ficheDiscountRefProp;
    }

    public boolean isFoundByStoredProcedure() {
        return this.isFoundByStoredProcedure;
    }

    public void setFoundByStoredProcedure(boolean z) {
        this.isFoundByStoredProcedure = z;
    }

    public ArrayList<String> getSearchBarcodes() {
        return this.searchBarcodes;
    }

    public void setSearchBarcodes(ArrayList<String> arrayList) {
        this.searchBarcodes = arrayList;
    }

    public FicheDateProp getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(FicheDateProp ficheDateProp) {
        this.dueDate = ficheDateProp;
    }

    public FicheStringProp getSurplusAmount() {
        return this.surplusAmount;
    }

    public void setSurplusAmount(FicheStringProp ficheStringProp) {
        this.surplusAmount = ficheStringProp;
    }

    public int getBarcodeCount() {
        return this.barcodeCount;
    }

    public void setBarcodeCount(int r1) {
        this.barcodeCount = r1;
    }

    public String getCampaignDiscountRatio() {
        return this.campaignDiscountRatio;
    }

    public void setCampaignDiscountRatio(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.campaignDiscountRatio = str;
    }

    public SalesDetail(int r125) {
        this(0L, 0, 0, null, 0, 0, null, 0, false, false, 0, 0, null, 0, 0, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0, null, 0.0d, null, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, false, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, null, null, 0, false, 0, 0.0d, 0.0d, 0, null, 0, 0, 0, 0, false, null, null, 0, false, null, null, null, 0, null, -1, -1, Integer.MAX_VALUE, null);
        this.salesType = r125;
    }

    private SalesDetail(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        int r16 = 0;
        this(0L, 0, 0, null, 0, 0, null, 0, false, false, 0, 0, null, r16, r16, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0, null, 0.0d, null, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, false, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, null, null, 0, false, 0, 0.0d, 0.0d, 0, null, 0, 0, 0, 0, false, null, null, 0, false, null, null, null, 0, null, -1, -1, Integer.MAX_VALUE, null);
        this.f1275id = parcel.readLong();
        this.logicalRef = parcel.readInt();
        this.itemRef = parcel.readInt();
        this.code = parcel.readString();
        this.salesFicheId = parcel.readInt();
        this.salesType = parcel.readInt();
        this.name = parcel.readString();
        this.trackType = parcel.readInt();
        this.isProduct = parcel.readByte() != 0;
        this.isHasVariant = parcel.readByte() != 0;
        this.lineNumber = parcel.readInt();
        this.serialLotTransfer = parcel.readInt();
        String string = parcel.readString();
        this.serialLotCodeList = string == null ? "" : string;
        this.lineType = parcel.readInt();
        this.cardType = parcel.readInt();
        this.convFact1 = parcel.readDouble();
        this.convFact2 = parcel.readDouble();
        this.lineNr = parcel.readInt();
        this.calculateCurrPrice = parcel.readDouble();
        this.usePrice = parcel.readDouble();
        this.enteryPrice = parcel.readDouble();
        this.prCurrType = parcel.readInt();
        this.curCodeStr = parcel.readString();
        this.prRate = parcel.readDouble();
        this.priceWithDigit = parcel.readString();
        this.priceWithCurCode = parcel.readString();
        this.productVatAmnt = parcel.readDouble();
        this.productVatMatrah = parcel.readDouble();
        this.productDiscountTotal = parcel.readDouble();
        this.productAmountTotal = parcel.readDouble();
        this.productLineNet = parcel.readDouble();
        this.productGrossTotal = parcel.readDouble();
        this.productTotal = parcel.readDouble();
        this.netVolume = parcel.readDouble();
        this.width = parcel.readDouble();
        this.length = parcel.readDouble();
        this.height = parcel.readDouble();
        this.grossVolume = parcel.readDouble();
        this.netWeight = parcel.readDouble();
        this.grossWeight = parcel.readDouble();
        this.actualStock = parcel.readDouble();
        this.isPriceSet = parcel.readByte() != 0;
        Parcelable parcelable = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable);
        this.wareHouse = (FicheRefProp) parcelable;
        Parcelable parcelable2 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable2);
        this.referenceCode = (FicheRefProp) parcelable2;
        ArrayList<Serilot> arrayListCreateTypedArrayList = parcel.createTypedArrayList(Serilot.CREATOR);
        Intrinsics.checkNotNull(arrayListCreateTypedArrayList);
        this.salesSerialLots = arrayListCreateTypedArrayList;
        Parcelable parcelable3 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable3);
        this.amount = (FicheStringProp) parcelable3;
        Parcelable parcelable4 = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable4);
        this.unit = (FicheDiscountRefProp) parcelable4;
        Parcelable parcelable5 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable5);
        this.price = (FicheRefProp) parcelable5;
        Parcelable parcelable6 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable6);
        this.selectedPriceType = (FicheRefProp) parcelable6;
        Parcelable parcelable7 = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable7);
        this.selectedPrice = (FicheDiscountRefProp) parcelable7;
        Parcelable parcelable8 = parcel.readParcelable(FicheBooleanProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable8);
        this.promotion = (FicheBooleanProp) parcelable8;
        Parcelable parcelable9 = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable9);
        this.variant = (FicheDiscountRefProp) parcelable9;
        Parcelable parcelable10 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable10);
        this.vat = (FicheStringProp) parcelable10;
        Parcelable parcelable11 = parcel.readParcelable(FicheBooleanProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable11);
        this.includeVat = (FicheBooleanProp) parcelable11;
        Parcelable parcelable12 = parcel.readParcelable(FicheDateProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable12);
        this.deliveryDate = (FicheDateProp) parcelable12;
        Parcelable parcelable13 = parcel.readParcelable(FicheBooleanProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable13);
        this.reserve = (FicheBooleanProp) parcelable13;
        Parcelable parcelable14 = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable14);
        this.payment = (FicheDiscountRefProp) parcelable14;
        Parcelable parcelable15 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable15);
        this.speCode = (FicheRefProp) parcelable15;
        Parcelable parcelable16 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable16);
        this.deliveryCode = (FicheRefProp) parcelable16;
        Parcelable parcelable17 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable17);
        this.explanation = (FicheStringProp) parcelable17;
        Parcelable parcelable18 = parcel.readParcelable(SalesFicheDiscountProps.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable18);
        this.salesFicheDiscountProps = (SalesFicheDiscountProps) parcelable18;
        this.isNotUnitChange = parcel.readByte() != 0;
        String string2 = parcel.readString();
        this.campaignCode = string2 == null ? "" : string2;
        String string3 = parcel.readString();
        this.campaignCode2 = string3 == null ? "" : string3;
        String string4 = parcel.readString();
        this.campaignCode3 = string4 == null ? "" : string4;
        String string5 = parcel.readString();
        this.campaignCode4 = string5 == null ? "" : string5;
        String string6 = parcel.readString();
        this.campaignCode5 = string6 == null ? "" : string6;
        String string7 = parcel.readString();
        this.campaignLineNo = string7 == null ? "" : string7;
        String string8 = parcel.readString();
        this.campaignLineNo2 = string8 == null ? "" : string8;
        String string9 = parcel.readString();
        this.campaignLineNo3 = string9 == null ? "" : string9;
        String string10 = parcel.readString();
        this.campaignLineNo4 = string10 == null ? "" : string10;
        String string11 = parcel.readString();
        this.campaignLineNo5 = string11 == null ? "" : string11;
        String string12 = parcel.readString();
        this.guid = string12 == null ? "" : string12;
        this.globalLineType = parcel.readInt();
        this.isDivUnit = parcel.readByte() != 0;
        this.orderDetailReference = parcel.readInt();
        this.orderAvailableAmount = parcel.readDouble();
        this.orderAmount = parcel.readDouble();
        this.orderReference = parcel.readInt();
        this.sipNum = parcel.readString();
        this.sipKont = parcel.readInt();
        this.distributionRef = parcel.readInt();
        this.distributionLineRef = parcel.readInt();
        this.addTaxRef = parcel.readInt();
        Parcelable parcelable19 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable19);
        this.currType = (FicheRefProp) parcelable19;
        this.isLocTracking = parcel.readByte() != 0;
        Parcelable parcelable20 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable20);
        this.secondAmount = (FicheStringProp) parcelable20;
        Parcelable parcelable21 = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable21);
        this.secondUnit = (FicheDiscountRefProp) parcelable21;
        this.previousPriceRef = parcel.readInt();
        addPropertyChangeListenerForSelectedPriceRef(this);
        this.isFoundByStoredProcedure = parcel.readByte() != 0;
        this.searchBarcodes = parcel.createStringArrayList();
        this.dueDate = parcel.readParcelable(FicheDateProp.class.getClassLoader());
        this.surplusAmount = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.barcodeCount = parcel.readInt();
        String string13 = parcel.readString();
        this.campaignDiscountRatio = string13 != null ? string13 : "";
    }
    public SalesDetail(int r125, Product product, String str, boolean z) {
        Intrinsics.checkNotNullParameter(product, "product");
        int r16 = 0;
        this(0L, 0, 0, null, 0, 0, null, 0, false, false, 0, 0, null, r16, r16, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0, null, 0.0d, null, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, false, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, null, null, 0, false, 0, 0.0d, 0.0d, 0, null, 0, 0, 0, 0, false, null, null, 0, false, null, null, null, 0, null, -1, -1, Integer.MAX_VALUE, null);
        this.salesType = r125;
        this.itemRef = product.getLogicalRef();
        this.name = product.getName();
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(product.getAmount())));
        this.unit.setLogicalRef(product.getUnitRef());
        FicheStringProp.setDefinition(product.getUnitCode());
        this.unit.setCode(product.getUnitCode2());
        this.isHasVariant = product.getVariant();
        this.isProduct = !product.getService();
        this.trackType = product.getTrackType();
        this.cardType = product.getCardType();
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(getProductVatValueViaSalesType(product))));
        this.includeVat.setSelect(product.getIncVat());
        this.prRate = product.getRate();
        this.prCurrType = product.getCurNr();
        this.curCodeStr = product.getCurCode();
        this.currType.setLogicalRef(product.getCurNr());
        FicheStringProp.setDefinition(product.getCurCode());
        this.code = product.getCode();
        if (product.getPriceRef() >= 0) {
            if (SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(getmSalesType())) {
                FicheStringProp.setDefinition(String.valueOf(CalculateUtils.calculatePriceAddVat(product.getPrice(), SalesUtils.isSalesTypeOnlyRetailInvoice(getmSalesType()) ? product.getRetailVat() : product.getRetailReturnVat(), product.getIncVat())));
            } else {
                this.selectedPrice.setLogicalRef(product.getPriceRef());
                FicheStringProp.setDefinition(product.getCPrice());
                this.selectedPrice.setCode(String.valueOf(product.getPriceRef()));
                this.enteryPrice = product.getPrice();
            }
        } else if (product.getPrice() != 0.0d) {
            FicheStringProp.setDefinition(String.valueOf(product.getPrice()));
        }
        int length = product.getDiscountRatio().length;
        for (int r4 = 0; r4 < length; r4++) {
            if (product.getDiscountRatio()[r4]) {
                getDiscountRatio(r4);
                FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(product.getDiscount()[r4])));
            } else {
                getDiscountTotal(r4);
                FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(product.getDiscount()[r4])));
            }
        }
        if (!TextUtils.isEmpty(str)) {
            FicheStringProp.setDefinition(str);
        }
        FicheStringProp.setDefinition(product.getExplanation());
        this.reserve.setSelect(z);
        this.netVolume = product.getNetVolume();
        this.grossVolume = product.getGrossVolume();
        this.netWeight = product.getNetWeight();
        this.grossWeight = product.getGrossWeight();
        this.isPriceSet = false;
        this.actualStock = product.getActualStock();
        this.convFact1 = product.getConvfact1();
        this.convFact2 = product.getConvfact2();
        this.lineNr = product.getLineNr();
        this.isNotUnitChange = product.getNotUnitChange();
        this.isDivUnit = product.getDivUnit();
        this.promotion.setSelect(product.getPromotion());
        this.globalLineType = product.getGlobalLineType();
        ArrayList<Serilot> salesSerialLots = product.getSalesSerialLots();
        this.salesSerialLots = salesSerialLots;
        this.serialLotCodeList = StringUtils.getSerialLotCode(salesSerialLots, this.trackType);
        if (product.isBarcode() || (product.getVariant() && !TextUtils.isEmpty(product.getVariantCode()))) {
            this.variant.setCode(product.getVariantCode());
            this.variant.setLogicalRef(product.getVariantRef());
            FicheStringProp.setDefinition(product.getVariantName());
        }
        this.addTaxRef = product.getAddTaxRef();
        this.isLocTracking = StringUtils.convertIntToBoolean(product.getLocTracking());
        String campaignCode = product.getCampaignCode();
        this.campaignCode = campaignCode == null ? "" : campaignCode;
        String campaignLineNo = product.getCampaignLineNo();
        this.campaignLineNo = campaignLineNo != null ? campaignLineNo : "";
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(product.getSecondAmount())));
        this.secondUnit.setLogicalRef(product.getSecondUnitRef());
        FicheStringProp.setDefinition(product.getSecondUnitCode());
        this.secondUnit.setCode(product.getSecondUnitCode2());
        this.isFoundByStoredProcedure = product.getFoundByStoredProcedure();
        if (!TextUtils.isEmpty(product.getSearchBarcode())) {
            ArrayList<String> arrayList = this.searchBarcodes;
            Intrinsics.checkNotNull(arrayList);
            arrayList.add(product.getSearchBarcode());
        }
        FicheStringProp ficheStringProp = this.surplusAmount;
        Intrinsics.checkNotNull(ficheStringProp);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(product.getSurplusAmount())));
    }

    private double getProductVatValueViaSalesType(Product product) {
        if (SalesUtils.isSalesTypeOnlyRetailInvoice(getmSalesType())) {
            return product.getRetailVat();
        }
        if (SalesUtils.isSalesTypeOnlyRetailReturnInvoice(getmSalesType())) {
            return product.getRetailReturnVat();
        }
        if (this.viewModel.erpType() != ErpType.NETSIS && SalesUtils.isSalesTypeOnlyReturnInvoice(getmSalesType())) {
            return product.getReturnVat();
        }
        return product.getVat();
    }

    public int getPrCurrType() {
        if (this.currType.getLogicalRef() != -1) {
            return this.currType.getLogicalRef();
        }
        return this.prCurrType;
    }

    public String getCurCodeStr() {
        if (this.currType.getDefinition() != null) {
            String definition = this.currType.getDefinition();
            Intrinsics.checkNotNullExpressionValue(definition, "getDefinition(...)");
            if (definition.length() != 0) {
                return this.currType.getDefinition();
            }
        }
        return this.curCodeStr;
    }

    public String getDiscountText() {
        double d2 = this.productDiscountTotal;
        if (d2 > 0.0d) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("-(%s %s)", Arrays.copyOf(new Object[]{StringUtils.formatDouble(d2), this.viewModel.getLocalCurrencyCode()}, 2));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            return str;
        }
        return "";
    }

    public String getDiscountTextWithCurrency() {
        double d2 = this.productDiscountTotal;
        if (d2 > 0.0d) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("-(%s %s)", Arrays.copyOf(new Object[]{StringUtils.formatDouble(d2 / this.prRate), getCurCodeStr()}, 2));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            return str;
        }
        return "";
    }

    public String getTotalText() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("(%s %s X %s %s )", Arrays.copyOf(new Object[]{this.amount.getDefinition(), TextUtils.isEmpty(this.unit.getCode()) ? "" : this.unit.getCode(), StringUtils.formatDoubleNormal(this.usePrice), Strings.isNullOrEmpty(getCurCodeStr()) ? "" : getCurCodeStr()}, 4));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public String getLineNetText() {
        double definitionDouble;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        if (this.includeVat.isSelect()) {
            definitionDouble = (this.calculateCurrPrice * this.amount.getDefinitionDouble()) - this.productDiscountTotal;
        } else {
            definitionDouble = this.productLineNet;
        }
        String str = String.format(" = %s %s", Arrays.copyOf(new Object[]{StringUtils.formatDoubleNormal(definitionDouble), this.viewModel.getLocalCurrencyCode()}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public String getLineNetTextWithCurrency() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        double d2 = this.productLineNet;
        double d3 = this.prRate;
        if (d3 == 0.0d) {
            d3 = 1.0d;
        }
        String doubleNormal = StringUtils.formatDoubleNormal(d2 / d3);
        String curCodeStr = getCurCodeStr();
        if (curCodeStr == null) {
            curCodeStr = StringUtils.empty();
        }
        String str = String.format(" = %s %s", Arrays.copyOf(new Object[]{doubleNormal, curCodeStr}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public String getUnitPriceText() {
        if (this.amount.getDefinitionDouble() <= 0.0d) {
            return "";
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("(%s/%s)", Arrays.copyOf(new Object[]{StringUtils.formatDoubleNormal(this.productLineNet / this.amount.getDefinitionDouble()), TextUtils.isEmpty(this.unit.getCode()) ? "" : this.unit.getCode()}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public int getLineType() {
        int r0 = LineType.SERVICE.value;
        if (this.isProduct) {
            int r02 = this.cardType;
            ItemCardType itemCardType = ItemCardType.TICARIMAL;
            if ((r02 == itemCardType.getValue() || this.cardType == ItemCardType.HAMMADDE.getValue() || this.cardType == ItemCardType.YARIMAMUL.getValue() || this.cardType == ItemCardType.MAMUL.getValue()) && this.promotion.isSelect()) {
                r0 = LineType.PROMOTION.value;
            } else if (((this.cardType != itemCardType.getValue() && this.cardType != ItemCardType.HAMMADDE.getValue() && this.cardType != ItemCardType.YARIMAMUL.getValue() && this.cardType != ItemCardType.MAMUL.getValue()) || this.promotion.isSelect()) && this.cardType == ItemCardType.KARMAKOLI.getValue()) {
                r0 = LineType.COMPOSITE_COLI.value;
            } else {
                r0 = LineType.PRODUCT.value;
            }
        }
        this.lineType = r0;
        return r0;
    }
    public boolean checkPrice() {
        boolean z;
        double dConvertStringToDouble = 0;
        try {
            dConvertStringToDouble = StringUtils.convertStringToDouble(this.price.toString());
            z = true;
        } catch (Exception e2) {
            Log.e("AA", "checkPrice: ", e2);
            z = false;
        }
        if (dConvertStringToDouble > 0.0d) {
            this.usePrice = dConvertStringToDouble;
        } else if (this.selectedPrice.getLogicalRef() >= 0) {
            this.usePrice = this.enteryPrice;
        } else {
            z = false;
        }
        return z;
    }

    public double getProductAmountTotalConvfact() {
        return this.productAmountTotal * this.convFact2;
    }

    public SalesType getmSalesType() {
        return SalesType.values()[this.salesType];
    }

    public void calculateFiche(boolean z) {
        double dCalculateIncludeVatPrice;
        double dCalculateIncludeVatPrice2;
        this.productTotal = 0.0d;
        this.productAmountTotal = 0.0d;
        this.productVatAmnt = 0.0d;
        this.productVatMatrah = 0.0d;
        this.productDiscountTotal = 0.0d;
        this.productLineNet = 0.0d;
        this.productGrossTotal = 0.0d;
        if (checkPrice()) {
            this.lineType = getLineType();
            double d2 = this.prRate;
            double dRoundFive = d2 <= 0.0d ? 1.0d : CalculateUtils.roundFive(d2);
            this.prRate = dRoundFive;
            this.calculateCurrPrice = CalculateUtils.roundFive(this.usePrice * dRoundFive);
            this.productAmountTotal = CalculateUtils.roundFive(getProductAmountTotalConvfact());
            this.productTotal = this.calculateCurrPrice * this.amount.getDefinitionDouble();
            int r2 = this.lineType;
            if (r2 == LineType.PRODUCT.value || r2 == LineType.SERVICE.value || r2 == LineType.COMPOSITE_COLI.value) {
                if (!this.includeVat.isSelect()) {
                    dCalculateIncludeVatPrice = CalculateUtils.roundFive(this.calculateCurrPrice * this.amount.getDefinitionDouble());
                } else {
                    dCalculateIncludeVatPrice = CalculateUtils.calculateIncludeVatPrice(this.calculateCurrPrice * this.amount.getDefinitionDouble(), this.vat.getDefinitionDouble());
                }
                this.productGrossTotal = dCalculateIncludeVatPrice;
                Log.d(MobileSales.TAG, "calculateFiche: " + z);
                if (!z) {
                    this.productVatAmnt = CalculateUtils.roundFive((this.productGrossTotal * this.vat.getDefinitionDouble()) / 100);
                }
                double d3 = this.productGrossTotal;
                this.productVatMatrah = d3;
                this.productLineNet = d3;
                Disc disc = new Disc();
                disc.lineNet = this.productLineNet;
                disc.vat = this.productVatAmnt;
                disc.vatMatrah = this.productVatMatrah;
                disc.grossTotal = this.productGrossTotal;
                Disc discCalculateDiscountLineSalesDetail = CalculateUtils.calculateDiscountLineSalesDetail(disc, this.salesFicheDiscountProps.getCustomerDiscount().getDefinitionDouble(), 0.0d);
                setProductDiscountTotal(discCalculateDiscountLineSalesDetail);
                int discountLength = getDiscountLength();
                for (int r1 = 0; r1 < discountLength; r1++) {
                    discCalculateDiscountLineSalesDetail = CalculateUtils.calculateDiscountLineSalesDetail(discCalculateDiscountLineSalesDetail, getDiscountRatio(r1), getDiscountTotal(r1));
                    setProductDiscountTotal(discCalculateDiscountLineSalesDetail);
                }
                return;
            }
            if (r2 == LineType.PROMOTION.value) {
                if (this.includeVat.isSelect()) {
                    dCalculateIncludeVatPrice2 = CalculateUtils.calculateIncludeVatPrice(this.calculateCurrPrice, this.vat.getDefinitionDouble());
                } else {
                    dCalculateIncludeVatPrice2 = this.calculateCurrPrice;
                }
                this.productGrossTotal = this.amount.getDefinitionDouble() * dCalculateIncludeVatPrice2;
                double definitionDouble = dCalculateIncludeVatPrice2 * this.amount.getDefinitionDouble();
                this.productTotal = definitionDouble;
                if (z) {
                    this.productVatAmnt = (definitionDouble * this.vat.getDefinitionDouble()) / 100;
                }
                this.productLineNet = 0.0d;
                double d4 = this.productTotal;
                this.productDiscountTotal = d4;
                this.productVatMatrah = d4;
            }
        }
    }
    public void setProductDiscountTotal(Disc disc) {
        Intrinsics.checkNotNullParameter(disc, "disc");
        this.productVatAmnt = CalculateUtils.roundFive(disc.vat);
        this.productVatMatrah = CalculateUtils.roundFive(disc.vatMatrah);
        this.productLineNet = CalculateUtils.roundFive(disc.lineNet);
        this.productDiscountTotal += CalculateUtils.roundFive(disc.discount);
    }
    public String getSerialLotCodeList() {
        return this.serialLotCodeList;
    }
    public void setSerialLotCodeList(String serialLotCodeList) {
        Intrinsics.checkNotNullParameter(serialLotCodeList, "serialLotCodeList");
        this.serialLotCodeList = serialLotCodeList;
    }
    public boolean amountControl() {
        double definitionDouble = this.amount.getDefinitionDouble();
        FicheStringProp ficheStringProp = this.surplusAmount;
        Intrinsics.checkNotNull(ficheStringProp);
        return definitionDouble + ficheStringProp.getDefinitionDouble() > 0.0d;
    }
    public boolean serialLotControl() {
        if (this.salesType == SalesType.ORDER.getmValue() || this.salesType == SalesType.DEMAND.getmValue()) {
            return true;
        }
        int r0 = this.trackType;
        return (r0 != 1 && r0 != 2) || this.salesSerialLots.size() != 0;
    }
    public boolean priceControl() {
        try {
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "checkPrice: ", e2);
        }
        if (this.selectedPrice.getLogicalRef() > 0) {
            return true;
        }
        if (this.price.getDefinitionDouble() > 0.0d) {
            this.usePrice = this.enteryPrice;
            return true;
        }
        return false;
    }
    public boolean unitControl() {
        return !TextUtils.isEmpty(this.unit.getCode());
    }
    public boolean isNegativeStockAlertControl() {
        return this.isProduct && this.amount.getDefinitionDouble() > this.actualStock;
    }
    public int getPType() {
        return this.isProduct ? 2 : 4;
    }
    public boolean hasCampaign() {
        return !TextUtils.isEmpty(this.campaignCode) || !TextUtils.isEmpty(this.campaignCode2) || !TextUtils.isEmpty(this.campaignCode3) || !TextUtils.isEmpty(this.campaignCode4) || !TextUtils.isEmpty(this.campaignCode5);
    }
    public void clearCampaign() {
        this.campaignCode = "";
        this.campaignCode2 = "";
        this.campaignCode3 = "";
        this.campaignCode4 = "";
        this.campaignCode5 = "";
        this.campaignLineNo = "";
        this.campaignLineNo2 = "";
        this.campaignLineNo3 = "";
        this.campaignLineNo4 = "";
        this.campaignLineNo5 = "";
    }
    public void setGuid(String guid) {
        Intrinsics.checkNotNullParameter(guid, "guid");
        this.guid = guid;
    }
    public FicheDiscountProp getDiscountRatio(int r1) {
        FicheDiscountProp discountRatio = this.salesFicheDiscountProps.getDiscountRatio(r1);
        return discountRatio == null ? new FicheDiscountProp() : discountRatio;
    }
    public FicheDiscountProp getDiscountTotal(int r1) {
        FicheDiscountProp discountTotal = this.salesFicheDiscountProps.getDiscountTotal(r1);
        return discountTotal == null ? new FicheDiscountProp() : discountTotal;
    }
    public FicheDiscountRefProp getDiscountCard(int r1) {
        FicheDiscountRefProp discountCard = this.salesFicheDiscountProps.getDiscountCard(r1);
        return discountCard == null ? new FicheDiscountRefProp() : discountCard;
    }
    public void setDiscountCard(int r1, FicheDiscountRefProp ficheDiscountRefProp) {
        if (ficheDiscountRefProp != null) {
            this.salesFicheDiscountProps.setDiscountCard(r1, ficheDiscountRefProp);
        }
    }
    public int getDiscountLength() {
        return this.salesFicheDiscountProps.getDiscountCount();
    }
    public String getDiscountGuid(int r7) {
        if (!TextUtils.isEmpty(getDiscountCard(r7).getCode())) {
            String guid = getDiscountCard(r7).getGuid();
            Intrinsics.checkNotNullExpressionValue(guid, "getGuid(...)");
            return guid;
        }
        if (getDiscountRatio(r7).getDefinitionDouble() > 0.0d) {
            String guid2 = getDiscountRatio(r7).getGuid();
            Intrinsics.checkNotNullExpressionValue(guid2, "getGuid(...)");
            return guid2;
        }
        if (getDiscountTotal(r7).getDefinitionDouble() <= 0.0d) {
            return "";
        }
        String guid3 = getDiscountTotal(r7).getGuid();
        Intrinsics.checkNotNullExpressionValue(guid3, "getGuid(...)");
        return guid3;
    }
    public void setDiscountGuid(int r5, String str) {
        if (!TextUtils.isEmpty(getDiscountCard(r5).getCode())) {
            getDiscountCard(r5).setGuid(str);
        } else if (getDiscountRatio(r5).getDefinitionDouble() > 0.0d) {
            getDiscountRatio(r5).setGuid(str);
        } else if (getDiscountTotal(r5).getDefinitionDouble() > 0.0d) {
            getDiscountTotal(r5).setGuid(str);
        }
    }
    public String getDiscountCampaign(int r5) {
        if (!TextUtils.isEmpty(getDiscountCard(r5).getCode())) {
            return getDiscountCard(r5).getCampaignCode();
        }
        if (getDiscountRatio(r5).getDefinitionDouble() > 0.0d) {
            return getDiscountRatio(r5).getCampaignCode();
        }
        if (getDiscountTotal(r5).getDefinitionDouble() <= 0.0d) {
            return "";
        }
        return getDiscountTotal(r5).getCampaignCode();
    }
    public void setDiscountCampaign(int r5, String str) {
        if (!TextUtils.isEmpty(getDiscountCard(r5).getCode())) {
            getDiscountCard(r5).setCampaignCode(str);
        } else if (getDiscountRatio(r5).getDefinitionDouble() > 0.0d) {
            getDiscountRatio(r5).setCampaignCode(str);
        } else if (getDiscountTotal(r5).getDefinitionDouble() > 0.0d) {
            getDiscountTotal(r5).setCampaignCode(str);
        }
    }
    public String getDiscountCampaignLineNo(int r5) {
        if (!TextUtils.isEmpty(getDiscountCard(r5).getCode())) {
            return getDiscountCard(r5).getCampaignLineNo();
        }
        if (getDiscountRatio(r5).getDefinitionDouble() > 0.0d) {
            return getDiscountRatio(r5).getCampaignLineNo();
        }
        if (getDiscountTotal(r5).getDefinitionDouble() <= 0.0d) {
            return "";
        }
        return getDiscountTotal(r5).getCampaignLineNo();
    }
    public void setDiscountCampaignLineNo(int r5, String str) {
        if (!TextUtils.isEmpty(getDiscountCard(r5).getCode())) {
            getDiscountCard(r5).setCampaignLineNo(str);
        } else if (getDiscountRatio(r5).getDefinitionDouble() > 0.0d) {
            getDiscountRatio(r5).setCampaignLineNo(str);
        } else if (getDiscountTotal(r5).getDefinitionDouble() > 0.0d) {
            getDiscountTotal(r5).setCampaignLineNo(str);
        }
    }
    public double getCustomerDiscRatio() {
        return this.salesFicheDiscountProps.getCustomerDiscount().getDefinitionDouble();
    }
    public void setCustomerDiscRatio(double d2) {
        FicheStringProp.setDefinition(String.valueOf(d2));
    }
    public String getCustomerDiscGuid() {
        return this.salesFicheDiscountProps.getCustomerDiscount().getGuid();
    }
    public void setCustomerDiscGuid(String str) {
        this.salesFicheDiscountProps.getCustomerDiscount().setGuid(str);
    }
    public String getCustomerCampaignCode() {
        return this.salesFicheDiscountProps.getCustomerDiscount().getCampaignCode();
    }
    public void setCustomerCampaignCode(String str) {
        this.salesFicheDiscountProps.getCustomerDiscount().setCampaignCode(str);
    }
    public boolean findDiscountByGuid(String guid) {
        Intrinsics.checkNotNullParameter(guid, "guid");
        return this.salesFicheDiscountProps.findDiscountByGuid(guid) != null;
    }
    public FicheDiscountProp getDiscountByGuid(String guid) {
        Intrinsics.checkNotNullParameter(guid, "guid");
        FicheDiscountProp ficheDiscountPropFindDiscountByGuid = this.salesFicheDiscountProps.findDiscountByGuid(guid);
        return ficheDiscountPropFindDiscountByGuid == null ? new FicheDiscountProp() : ficheDiscountPropFindDiscountByGuid;
    }
    public int findDiscountIndexByGuid(String guid) {
        Intrinsics.checkNotNullParameter(guid, "guid");
        return this.salesFicheDiscountProps.findDiscountIndexByGuid(guid);
    }
    public FicheDiscountProp getCustomerDisc() {
        return this.salesFicheDiscountProps.getCustomerDiscount();
    }
    public void resetSelectedPrice() {
        this.selectedPrice.reset();
        this.priceWithDigit = "";
        this.priceWithCurCode = "";
        this.enteryPrice = 0.0d;
        this.usePrice = 0.0d;
    }
    public boolean hasSeriLotWithoutLocCode() {
        if (!this.isLocTracking) {
            return false;
        }
        if (this.salesSerialLots.isEmpty() || this.salesSerialLots.size() == 0) {
            return true;
        }
        Iterator<Serilot> it = this.salesSerialLots.iterator();
        while (it.hasNext()) {
            if (TextUtils.isEmpty(it.next().locationCode)) {
                return true;
            }
        }
        return false;
    }
    public void setPreviousPriceRef(int r1) {
        this.previousPriceRef = r1;
    }
    private void addPropertyChangeListenerForSelectedPriceRef(final SalesDetail salesDetail) {
        if (salesDetail.selectedPrice.hasPropertyChangeListener("logicalRef")) {
            return;
        }
        salesDetail.selectedPrice.addPropertyChangeListener(new PropertyChangeListener() { // from class: com.proje.mobilesales.features.sales.model.SalesDetailExternalSyntheticLambda0
            @Override // java.beans.PropertyChangeListener
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                SalesDetail.addPropertyChangeListenerForSelectedPriceReflambda0(this.f0, propertyChangeEvent);
            }
        });
    }
    public static void addPropertyChangeListenerForSelectedPriceReflambda0(SalesDetail detail, PropertyChangeEvent evt) {
        Intrinsics.checkNotNullParameter(detail, "detail");
        Intrinsics.checkNotNullParameter(evt, "evt");
        if (Intrinsics.areEqual(evt.getPropertyName(), "logicalRef")) {
            Object newValue = evt.getNewValue();
            Intrinsics.checkNotNull(newValue, "null cannot be cast to non-null type kotlin.Int");
            int r3 = ((Integer) newValue).intValue();
            if (r3 > 0) {
                detail.setPreviousPriceRef(r3);
            }
        }
    }
    public void resetPromotionPrice() {
        if (this.promotion.isSelect()) {
            this.selectedPrice.reset();
            this.priceWithDigit = "";
            this.priceWithCurCode = "";
            this.enteryPrice = 0.0d;
            this.usePrice = 0.0d;
            this.price.reset();
        }
    }
    public boolean isCampaignLine() {
        String str = this.campaignLineNo;
        if (str != null && str.length() != 0) {
            return true;
        }
        String str2 = this.campaignLineNo2;
        if (str2 != null && str2.length() != 0) {
            return true;
        }
        String str3 = this.campaignLineNo3;
        if (str3 != null && str3.length() != 0) {
            return true;
        }
        String str4 = this.campaignLineNo4;
        if (str4 != null && str4.length() != 0) {
            return true;
        }
        String str5 = this.campaignLineNo5;
        return str5 != null && str5.length() != 0;
    }
    public double getMainAmount(boolean z) {
        if (z) {
            return this.amount.getDefinitionDouble();
        }
        return (this.convFact2 * this.amount.getDefinitionDouble()) / this.convFact1;
    }
    public void writeToParcel(Parcel dest, int r4) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeLong(this.f1275id);
        dest.writeInt(this.logicalRef);
        dest.writeInt(this.itemRef);
        dest.writeString(this.code);
        dest.writeInt(this.salesFicheId);
        dest.writeInt(this.salesType);
        dest.writeString(this.name);
        dest.writeInt(this.trackType);
        dest.writeByte(this.isProduct ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isHasVariant ? (byte) 1 : (byte) 0);
        dest.writeInt(this.lineNumber);
        dest.writeInt(this.serialLotTransfer);
        dest.writeString(this.serialLotCodeList);
        dest.writeInt(this.lineType);
        dest.writeInt(this.cardType);
        dest.writeDouble(this.convFact1);
        dest.writeDouble(this.convFact2);
        dest.writeInt(this.lineNr);
        dest.writeDouble(this.calculateCurrPrice);
        dest.writeDouble(this.usePrice);
        dest.writeDouble(this.enteryPrice);
        dest.writeInt(this.prCurrType);
        dest.writeString(this.curCodeStr);
        dest.writeDouble(this.prRate);
        dest.writeString(this.priceWithDigit);
        dest.writeString(this.priceWithCurCode);
        dest.writeDouble(this.productVatAmnt);
        dest.writeDouble(this.productVatMatrah);
        dest.writeDouble(this.productDiscountTotal);
        dest.writeDouble(this.productAmountTotal);
        dest.writeDouble(this.productLineNet);
        dest.writeDouble(this.productGrossTotal);
        dest.writeDouble(this.productTotal);
        dest.writeDouble(this.netVolume);
        dest.writeDouble(this.width);
        dest.writeDouble(this.length);
        dest.writeDouble(this.height);
        dest.writeDouble(this.grossVolume);
        dest.writeDouble(this.netWeight);
        dest.writeDouble(this.grossWeight);
        dest.writeDouble(this.actualStock);
        dest.writeByte(this.isPriceSet ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.wareHouse, r4);
        dest.writeParcelable(this.referenceCode, r4);
        dest.writeTypedList(this.salesSerialLots);
        dest.writeParcelable(this.amount, r4);
        dest.writeParcelable(this.unit, r4);
        dest.writeParcelable(this.price, r4);
        dest.writeParcelable(this.selectedPriceType, r4);
        dest.writeParcelable(this.selectedPrice, r4);
        dest.writeParcelable(this.promotion, r4);
        dest.writeParcelable(this.variant, r4);
        dest.writeParcelable(this.vat, r4);
        dest.writeParcelable(this.includeVat, r4);
        dest.writeParcelable(this.deliveryDate, r4);
        dest.writeParcelable(this.reserve, r4);
        dest.writeParcelable(this.payment, r4);
        dest.writeParcelable(this.speCode, r4);
        dest.writeParcelable(this.deliveryCode, r4);
        dest.writeParcelable(this.explanation, r4);
        dest.writeParcelable(this.salesFicheDiscountProps, r4);
        dest.writeByte(this.isNotUnitChange ? (byte) 1 : (byte) 0);
        dest.writeString(this.campaignCode);
        dest.writeString(this.campaignCode2);
        dest.writeString(this.campaignCode3);
        dest.writeString(this.campaignCode4);
        dest.writeString(this.campaignCode5);
        dest.writeString(this.campaignLineNo);
        dest.writeString(this.campaignLineNo2);
        dest.writeString(this.campaignLineNo3);
        dest.writeString(this.campaignLineNo4);
        dest.writeString(this.campaignLineNo5);
        dest.writeString(this.guid);
        dest.writeInt(this.globalLineType);
        dest.writeByte(this.isDivUnit ? (byte) 1 : (byte) 0);
        dest.writeInt(this.orderDetailReference);
        dest.writeDouble(this.orderAvailableAmount);
        dest.writeDouble(this.orderAmount);
        dest.writeInt(this.orderReference);
        dest.writeString(this.sipNum);
        dest.writeInt(this.sipKont);
        dest.writeInt(this.distributionRef);
        dest.writeInt(this.distributionLineRef);
        dest.writeInt(this.addTaxRef);
        dest.writeParcelable(this.currType, r4);
        dest.writeByte(this.isLocTracking ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.secondAmount, r4);
        dest.writeParcelable(this.secondUnit, r4);
        dest.writeInt(this.previousPriceRef);
        dest.writeByte(this.isFoundByStoredProcedure ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.searchBarcodes);
        dest.writeParcelable(this.dueDate, r4);
        dest.writeParcelable(this.surplusAmount, r4);
        dest.writeInt(this.barcodeCount);
        dest.writeString(this.campaignDiscountRatio);
    }
    public SalesDetail m1315clone() throws CloneNotSupportedException {
        Object objClone = super.clone();
        Intrinsics.checkNotNull(objClone, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.model.SalesDetail");
        SalesDetail salesDetail = (SalesDetail) objClone;
        salesDetail.f1275id = IdGenerator.INSTANCE.generateNextId();
        String str = this.name;
        if (str == null) {
            str = "";
        }
        salesDetail.name = str;
        salesDetail.serialLotCodeList = this.serialLotCodeList;
        String str2 = this.code;
        if (str2 == null) {
            str2 = "";
        }
        salesDetail.code = str2;
        String str3 = this.curCodeStr;
        if (str3 == null) {
            str3 = "";
        }
        salesDetail.curCodeStr = str3;
        String str4 = this.priceWithDigit;
        if (str4 == null) {
            str4 = "";
        }
        salesDetail.priceWithDigit = str4;
        String str5 = this.priceWithCurCode;
        if (str5 == null) {
            str5 = "";
        }
        salesDetail.priceWithCurCode = str5;
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        salesDetail.guid = string;
        ArrayList<Serilot> arrayList = new ArrayList<>(this.salesSerialLots.size());
        Iterator<Serilot> it = this.salesSerialLots.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().clone());
        }
        salesDetail.salesSerialLots = arrayList;
        FicheStringProp ficheStringPropMo1244clone = this.amount.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone, "clone(...)");
        salesDetail.amount = ficheStringPropMo1244clone;
        FicheDiscountRefProp ficheDiscountRefPropMo1244clone = this.unit.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDiscountRefPropMo1244clone, "clone(...)");
        salesDetail.unit = ficheDiscountRefPropMo1244clone;
        FicheRefProp ficheRefPropMo1244clone = this.price.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone, "clone(...)");
        salesDetail.price = ficheRefPropMo1244clone;
        FicheRefProp ficheRefPropMo1244clone2 = this.selectedPriceType.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone2, "clone(...)");
        salesDetail.selectedPriceType = ficheRefPropMo1244clone2;
        FicheDiscountRefProp ficheDiscountRefPropMo1244clone2 = this.selectedPrice.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDiscountRefPropMo1244clone2, "clone(...)");
        salesDetail.selectedPrice = ficheDiscountRefPropMo1244clone2;
        FicheBooleanProp ficheBooleanPropMo1244clone = this.promotion.clone();
        Intrinsics.checkNotNullExpressionValue(ficheBooleanPropMo1244clone, "clone(...)");
        salesDetail.promotion = ficheBooleanPropMo1244clone;
        FicheDiscountRefProp ficheDiscountRefPropMo1244clone3 = this.variant.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDiscountRefPropMo1244clone3, "clone(...)");
        salesDetail.variant = ficheDiscountRefPropMo1244clone3;
        FicheStringProp ficheStringPropMo1244clone2 = this.vat.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone2, "clone(...)");
        salesDetail.vat = ficheStringPropMo1244clone2;
        FicheBooleanProp ficheBooleanPropMo1244clone2 = this.includeVat.clone();
        Intrinsics.checkNotNullExpressionValue(ficheBooleanPropMo1244clone2, "clone(...)");
        salesDetail.includeVat = ficheBooleanPropMo1244clone2;
        FicheDateProp ficheDatePropMo1244clone = this.deliveryDate.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDatePropMo1244clone, "clone(...)");
        salesDetail.deliveryDate = ficheDatePropMo1244clone;
        FicheBooleanProp ficheBooleanPropMo1244clone3 = this.reserve.clone();
        Intrinsics.checkNotNullExpressionValue(ficheBooleanPropMo1244clone3, "clone(...)");
        salesDetail.reserve = ficheBooleanPropMo1244clone3;
        FicheDiscountRefProp ficheDiscountRefPropMo1244clone4 = this.payment.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDiscountRefPropMo1244clone4, "clone(...)");
        salesDetail.payment = ficheDiscountRefPropMo1244clone4;
        FicheRefProp ficheRefPropMo1244clone3 = this.speCode.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone3, "clone(...)");
        salesDetail.speCode = ficheRefPropMo1244clone3;
        FicheRefProp ficheRefPropMo1244clone4 = this.deliveryCode.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone4, "clone(...)");
        salesDetail.deliveryCode = ficheRefPropMo1244clone4;
        FicheStringProp ficheStringPropMo1244clone3 = this.explanation.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone3, "clone(...)");
        salesDetail.explanation = ficheStringPropMo1244clone3;
        salesDetail.salesFicheDiscountProps = this.salesFicheDiscountProps.clone();
        salesDetail.campaignCode = this.campaignCode;
        salesDetail.campaignCode2 = this.campaignCode2;
        salesDetail.campaignCode3 = this.campaignCode3;
        salesDetail.campaignCode4 = this.campaignCode4;
        salesDetail.campaignCode5 = this.campaignCode5;
        salesDetail.campaignLineNo = this.campaignLineNo;
        salesDetail.campaignLineNo2 = this.campaignLineNo2;
        salesDetail.campaignLineNo3 = this.campaignLineNo3;
        salesDetail.campaignLineNo4 = this.campaignLineNo4;
        salesDetail.campaignLineNo5 = this.campaignLineNo5;
        FicheRefProp ficheRefPropMo1244clone5 = this.wareHouse.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone5, "clone(...)");
        salesDetail.wareHouse = ficheRefPropMo1244clone5;
        FicheRefProp ficheRefPropMo1244clone6 = this.referenceCode.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone6, "clone(...)");
        salesDetail.referenceCode = ficheRefPropMo1244clone6;
        FicheRefProp ficheRefPropMo1244clone7 = this.currType.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone7, "clone(...)");
        salesDetail.currType = ficheRefPropMo1244clone7;
        FicheStringProp ficheStringPropMo1244clone4 = this.secondAmount.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone4, "clone(...)");
        salesDetail.secondAmount = ficheStringPropMo1244clone4;
        FicheDiscountRefProp ficheDiscountRefPropMo1244clone5 = this.secondUnit.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDiscountRefPropMo1244clone5, "clone(...)");
        salesDetail.secondUnit = ficheDiscountRefPropMo1244clone5;
        salesDetail.previousPriceRef = this.previousPriceRef;
        salesDetail.isFoundByStoredProcedure = false;
        ArrayList<String> arrayList2 = new ArrayList<>();
        ArrayList<String> arrayList3 = this.searchBarcodes;
        if (arrayList3 != null) {
            Intrinsics.checkNotNull(arrayList3);
            Iterator<String> it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                arrayList2.add(it2.next());
            }
        }
        salesDetail.searchBarcodes = arrayList2;
        addPropertyChangeListenerForSelectedPriceRef(salesDetail);
        FicheStringProp ficheStringProp = this.surplusAmount;
        Intrinsics.checkNotNull(ficheStringProp);
        salesDetail.surplusAmount = ficheStringProp.clone();
        salesDetail.barcodeCount = this.barcodeCount;
        salesDetail.campaignDiscountRatio = this.campaignDiscountRatio;
        return salesDetail;
    }
    public static final class IdGenerator {
        public static final IdGenerator INSTANCE = new IdGenerator();
        private static final AtomicLong NEXT_ID = new AtomicLong(0);

        private IdGenerator() {
        }

        public long generateNextId() {
            return NEXT_ID.getAndIncrement();
        }
    }
    public static final class CREATOR implements Creator<SalesDetail> {
        public   CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }
        public SalesDetail createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new SalesDetail(parcel);
        }
        public SalesDetail[] newArray(int r1) {
            return new SalesDetail[r1];
        }
    }
    public void onWarehouseChange() {
        ErpType erpType = this.viewModel.erpType();
        ErpType erpType2 = ErpType.NETSIS;
        String str = erpType == erpType2 ? "ITEMCODE" : "ITEMREF";
        String strValueOf = this.viewModel.erpType() == erpType2 ? this.code : String.valueOf(this.itemRef);
        if (this.viewModel.erpType() == ErpType.TIGER && this.isHasVariant) {
            List table = this.viewModel.getBaseErp().getLogoSqlHelper().getTable(VariantStock.class, str + "=? AND VARIANTREF=? AND WAREHOUSENR=?", new String[]{strValueOf, String.valueOf(this.variant.getLogicalRef()), String.valueOf(this.wareHouse.getLogicalRef())});
            Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.VariantStock>");
            if (table.isEmpty()) {
                return;
            }
            this.actualStock = ((VariantStock) table.get(0)).getOnHand();
            return;
        }
        List table2 = this.viewModel.getBaseErp().getLogoSqlHelper().getTable(ItemStock.class, str + "=? AND WAREHOUSENR=?", new String[]{strValueOf, String.valueOf(this.wareHouse.getLogicalRef())});
        Intrinsics.checkNotNull(table2, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.product.model.database.ItemStock>");
        if (table2.isEmpty()) {
            return;
        }
        this.actualStock = ((ItemStock) table2.get(0)).onHand;
    }
}
