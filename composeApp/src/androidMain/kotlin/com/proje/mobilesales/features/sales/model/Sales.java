package com.proje.mobilesales.features.sales.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.emailreplacer.EmailObject;
import com.proje.mobilesales.core.emailreplacer.EmailReplacer;
import com.proje.mobilesales.core.enums.EInvoiceTyp;
import com.proje.mobilesales.core.enums.EmailTemplateType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.GlobalLineType;
import com.proje.mobilesales.core.enums.LineType;
import com.proje.mobilesales.core.enums.PurchasePriceParamValues;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.TrackType;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.core.utils.CalculateUtils;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.SeriLotUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.customer.model.CustomerBeforeBalance;
import com.proje.mobilesales.features.dbmodel.ShipAddress;
import com.proje.mobilesales.features.dbmodel.WareHouse;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;
import com.proje.mobilesales.features.model.CheckSeriGroup;
import com.proje.mobilesales.features.model.Disc;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheDateProp;
import com.proje.mobilesales.features.model.FicheDiscountProp;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.model.RequiredFields;
import com.proje.mobilesales.features.model.ValidationResult;
import com.proje.mobilesales.features.model.WarehouseItem;
import com.proje.mobilesales.features.product.model.database.Item;
import com.proje.mobilesales.features.product.model.database.ItemStock;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheHeaderFields;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheParameters;
import com.proje.mobilesales.features.sales.repository.BaseSalesRepository;
import com.proje.mobilesales.features.sales.utils.PurchasePriceUtils;
import com.proje.mobilesales.features.sales.view.BaseSalesViewModel;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.JobKt;

public final class Sales extends EmailReplacer implements Parcelable, Cloneable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private String andFicheNo;
    private FicheDateProp beginDate;
    private FicheRefProp branch;
    private FicheDiscountRefProp cabin;
    private int campaign;
    private String campaingRefs;
    private String clCode;
    private int clRef;
    private FicheBooleanProp consignee;
    private final CoroutineScope customScope;
    private FicheStringProp customerOrderNo;
    private FicheDateProp deliveryDate;
    private double discTotal;
    private FicheRefProp division;
    private FicheStringProp documentNo;
    private FicheStringProp documentTrackingNo;
    private FicheDateProp dueDate;
    private FicheBooleanProp eDespatch;
    private EDispatchAdditionalInfo eDispatchAdditionalInfo;
    private FicheRefProp eDocSerial;
    private FicheStringProp eInvoiceSGKDocumentNo;
    private FicheRefProp eInvoiceTypSgk;
    private FicheDateProp endDate;
    private FicheRefProp erpInvoiceType;
    private FicheStringProp explanation1;
    private FicheStringProp explanation10;
    private FicheStringProp explanation2;
    private FicheStringProp explanation3;
    private FicheStringProp explanation4;
    private FicheStringProp explanation5;
    private FicheStringProp explanation6;
    private FicheStringProp explanation7;
    private FicheStringProp explanation8;
    private FicheStringProp explanation9;
    private FicheRefProp factory;
    private String ficheCreateDate;
    private int ficheCreateDateInt;
    private String ficheNo;
    private int ficheRef;
    private FicheDiscountRefProp firstSpeCode;
    private String gDate;
    private double grossTotal;
    private FicheBooleanProp includeVat;
    private FicheBooleanProp insteadOfEDespatch;
    private String invoiceAddress;
    private int isEdit;
    private boolean isNotUseGattribKdv;
    private boolean isSaleVatCanBeChange;
    private boolean isSaleVatDefaultChecked;
    private double latitude;
    private int logicalRef;
    private double longitude;
    private ArrayList<SalesDetail> mSalesDetailList;
    private int mSalesType;
    private String orderFicheStatus;
    private int orderRef;
    private int orderType;
    private FicheDiscountRefProp payPlan;
    private FicheBooleanProp paymentOrder;
    private int printCount;
    private FicheDiscountRefProp projectCode;
    private final BaseSalesRepository repository;
    private FicheBooleanProp reserved;
    private FicheRefProp returnWareHouse;
    private int salesCondition;
    private SalesFicheDiscountProps salesFicheDiscountProps;
    private int salesStatus;
    private FicheDiscountRefProp secondSpeCode;
    private FicheDiscountRefProp shipAccount;
    private FicheDiscountRefProp shipAddress;
    private FicheRefProp shipAgent;
    private FicheRefProp shipType;
    private FicheRefProp sourceWareHouse;
    private FicheRefProp speCode;
    private FicheStringProp taxPayerCode;
    private FicheStringProp taxPayerName;
    private ArrayList<SalesDetail> tempSalesDetailList;
    private double total;
    private double totalExpenses;
    private double totalGrossVolume;
    private double totalGrossWeight;
    private double totalNet;
    private double totalNetVolume;
    private double totalNetWeight;
    private double totalSku;
    private double totalVat;
    private int trCurr;
    private double trRate;
    private FicheRefProp tradeGroup;
    private int transferCount;
    private FicheRefProp transferSourceBranch;
    private FicheRefProp transferSourceCostGrp;
    private FicheRefProp transferSourceDivision;
    private FicheRefProp transferSourceFactory;
    private FicheRefProp transferSourceWareHouse;
    final BaseSalesViewModel viewModel;
    private int visitInfoId;
    private FicheRefProp wareHouse;
    private FicheRefProp warrantyCode;

    final class C30381 extends ContinuationImpl {
        int I0;
        Object L0;
        Object L1;
        Object L2;
        Object L3;
        Object L4;
        Object L5;
        boolean Z0;
        boolean Z1;
        int label;
        Object result;
        C30381(Continuation<? super C30381> continuation) {
            super((Continuation<Object>) continuation);
        }
        public Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            try {
                return Sales.this.salesDetailListControl(false, null, null, this);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }
    public Sales() {
        this(0, 0, null, 0, null, null, 0, 0, null, null, 0, 0, 0, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, 0, null, false, false, null, null, null, null, null, null, null, null, null, 0.0d, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, null, -1, -1, -1, 1, null);
    }
    public static  Sales copydefault(Sales sales, int r25, int r26, String str, int r28, String str2, String str3, int r31, int r32, String str4, String str5, int r35, int r36, int r37, double d2, double d3, double d4, double d5, double d6, double d7, int r50, double d8, double d9, double d10, double d11, double d12, double d13, double d14, int r65, int r66, FicheRefProp ficheRefProp, FicheRefProp ficheRefProp2, FicheRefProp ficheRefProp3, FicheRefProp ficheRefProp4, FicheRefProp ficheRefProp5, FicheRefProp ficheRefProp6, FicheRefProp ficheRefProp7, FicheRefProp ficheRefProp8, FicheStringProp ficheStringProp, FicheStringProp ficheStringProp2, FicheStringProp ficheStringProp3, FicheDateProp ficheDateProp, FicheBooleanProp ficheBooleanProp, FicheBooleanProp ficheBooleanProp2, FicheStringProp ficheStringProp4, FicheStringProp ficheStringProp5, FicheStringProp ficheStringProp6, FicheStringProp ficheStringProp7, FicheStringProp ficheStringProp8, FicheStringProp ficheStringProp9, FicheStringProp ficheStringProp10, FicheStringProp ficheStringProp11, FicheStringProp ficheStringProp12, FicheStringProp ficheStringProp13, FicheDiscountRefProp ficheDiscountRefProp, FicheDiscountRefProp ficheDiscountRefProp2, FicheRefProp ficheRefProp9, FicheRefProp ficheRefProp10, String str6, FicheRefProp ficheRefProp11, FicheDiscountRefProp ficheDiscountRefProp3, FicheDiscountRefProp ficheDiscountRefProp4, SalesFicheDiscountProps salesFicheDiscountProps, boolean z, int r101, FicheBooleanProp ficheBooleanProp3, boolean z2, boolean z3, FicheStringProp ficheStringProp14, FicheStringProp ficheStringProp15, FicheStringProp ficheStringProp16, FicheRefProp ficheRefProp12, FicheDateProp ficheDateProp2, FicheDateProp ficheDateProp3, FicheDiscountRefProp ficheDiscountRefProp5, FicheDiscountRefProp ficheDiscountRefProp6, FicheDiscountRefProp ficheDiscountRefProp7, double d15, int r116, int r117, FicheRefProp ficheRefProp13, FicheRefProp ficheRefProp14, FicheRefProp ficheRefProp15, FicheRefProp ficheRefProp16, FicheRefProp ficheRefProp17, FicheRefProp ficheRefProp18, FicheBooleanProp ficheBooleanProp4, FicheBooleanProp ficheBooleanProp5, FicheRefProp ficheRefProp19, FicheDateProp ficheDateProp4, String str7, FicheBooleanProp ficheBooleanProp6, int r130, String str8, ArrayList arrayList, EDispatchAdditionalInfo eDispatchAdditionalInfo, ArrayList arrayList2, CoroutineScope coroutineScope, int r136, int r137, int r138, int r139, Object obj) {
        int r4 = (r136 & 1) != 0 ? sales.logicalRef : r25;
        int r5 = (r136 & 2) != 0 ? sales.clRef : r26;
        String str9 = (r136 & 4) != 0 ? sales.clCode : str;
        int r7 = (r136 & 8) != 0 ? sales.ficheRef : r28;
        String str10 = (r136 & 16) != 0 ? sales.andFicheNo : str2;
        String str11 = (r136 & 32) != 0 ? sales.ficheNo : str3;
        int r10 = (r136 & 64) != 0 ? sales.mSalesType : r31;
        int r11 = (r136 & 128) != 0 ? sales.salesStatus : r32;
        String str12 = (r136 & 256) != 0 ? sales.ficheCreateDate : str4;
        String str13 = (r136 & 512) != 0 ? sales.gDate : str5;
        int r14 = (r136 & 1024) != 0 ? sales.ficheCreateDateInt : r35;
        return sales.copy(r4, r5, str9, r7, str10, str11, r10, r11, str12, str13, r14, (r136 & 2048) != 0 ? sales.transferCount : r36, (r136 & 4096) != 0 ? sales.printCount : r37, (r136 & 8192) != 0 ? sales.grossTotal : d2, (r136 & 16384) != 0 ? sales.total : d3, (r136 & 32768) != 0 ? sales.totalNet : d4, (r136 & 65536) != 0 ? sales.totalVat : d5, (r136 & 131072) != 0 ? sales.discTotal : d6, (r136 & 262144) != 0 ? sales.totalExpenses : d7, (r136 & 524288) != 0 ? sales.orderType : r50, (r136 & 1048576) != 0 ? sales.latitude : d8, (r136 & 2097152) != 0 ? sales.longitude : d9, (r136 & 4194304) != 0 ? sales.totalNetVolume : d10, (r136 & 8388608) != 0 ? sales.totalGrossVolume : d11, (r136 & 16777216) != 0 ? sales.totalNetWeight : d12, (r136 & 33554432) != 0 ? sales.totalGrossWeight : d13, (r136 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? sales.totalSku : d14, (r136 & 134217728) != 0 ? sales.campaign : r65, (r136 & 268435456) != 0 ? sales.salesCondition : r66, (r136 & 536870912) != 0 ? sales.branch : ficheRefProp, (r136 & BasicMeasure.EXACTLY) != 0 ? sales.division : ficheRefProp2, (r136 & Integer.MIN_VALUE) != 0 ? sales.factory : ficheRefProp3, (r137 & 1) != 0 ? sales.wareHouse : ficheRefProp4, (r137 & 2) != 0 ? sales.returnWareHouse : ficheRefProp5, (r137 & 4) != 0 ? sales.sourceWareHouse : ficheRefProp6, (r137 & 8) != 0 ? sales.speCode : ficheRefProp7, (r137 & 16) != 0 ? sales.warrantyCode : ficheRefProp8, (r137 & 32) != 0 ? sales.documentNo : ficheStringProp, (r137 & 64) != 0 ? sales.documentTrackingNo : ficheStringProp2, (r137 & 128) != 0 ? sales.customerOrderNo : ficheStringProp3, (r137 & 256) != 0 ? sales.deliveryDate : ficheDateProp, (r137 & 512) != 0 ? sales.paymentOrder : ficheBooleanProp, (r137 & 1024) != 0 ? sales.consignee : ficheBooleanProp2, (r137 & 2048) != 0 ? sales.explanation1 : ficheStringProp4, (r137 & 4096) != 0 ? sales.explanation2 : ficheStringProp5, (r137 & 8192) != 0 ? sales.explanation3 : ficheStringProp6, (r137 & 16384) != 0 ? sales.explanation4 : ficheStringProp7, (r137 & 32768) != 0 ? sales.explanation5 : ficheStringProp8, (r137 & 65536) != 0 ? sales.explanation6 : ficheStringProp9, (r137 & 131072) != 0 ? sales.explanation7 : ficheStringProp10, (r137 & 262144) != 0 ? sales.explanation8 : ficheStringProp11, (r137 & 524288) != 0 ? sales.explanation9 : ficheStringProp12, (r137 & 1048576) != 0 ? sales.explanation10 : ficheStringProp13, (r137 & 2097152) != 0 ? sales.shipAccount : ficheDiscountRefProp, (r137 & 4194304) != 0 ? sales.shipAddress : ficheDiscountRefProp2, (r137 & 8388608) != 0 ? sales.shipAgent : ficheRefProp9, (r137 & 16777216) != 0 ? sales.shipType : ficheRefProp10, (r137 & 33554432) != 0 ? sales.invoiceAddress : str6, (r137 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? sales.tradeGroup : ficheRefProp11, (r137 & 134217728) != 0 ? sales.payPlan : ficheDiscountRefProp3, (r137 & 268435456) != 0 ? sales.projectCode : ficheDiscountRefProp4, (r137 & 536870912) != 0 ? sales.salesFicheDiscountProps : salesFicheDiscountProps, (r137 & BasicMeasure.EXACTLY) != 0 ? sales.isNotUseGattribKdv : z, (r137 & Integer.MIN_VALUE) != 0 ? sales.isEdit : r101, (r138 & 1) != 0 ? sales.includeVat : ficheBooleanProp3, (r138 & 2) != 0 ? sales.isSaleVatCanBeChange : z2, (r138 & 4) != 0 ? sales.isSaleVatDefaultChecked : z3, (r138 & 8) != 0 ? sales.eInvoiceSGKDocumentNo : ficheStringProp14, (r138 & 16) != 0 ? sales.taxPayerCode : ficheStringProp15, (r138 & 32) != 0 ? sales.taxPayerName : ficheStringProp16, (r138 & 64) != 0 ? sales.eInvoiceTypSgk : ficheRefProp12, (r138 & 128) != 0 ? sales.beginDate : ficheDateProp2, (r138 & 256) != 0 ? sales.endDate : ficheDateProp3, (r138 & 512) != 0 ? sales.cabin : ficheDiscountRefProp5, (r138 & 1024) != 0 ? sales.firstSpeCode : ficheDiscountRefProp6, (r138 & 2048) != 0 ? sales.secondSpeCode : ficheDiscountRefProp7, (r138 & 4096) != 0 ? sales.trRate : d15, (r138 & 8192) != 0 ? sales.trCurr : r116, (r138 & 16384) != 0 ? sales.orderRef : r117, (r138 & 32768) != 0 ? sales.transferSourceBranch : ficheRefProp13, (r138 & 65536) != 0 ? sales.transferSourceDivision : ficheRefProp14, (r138 & 131072) != 0 ? sales.transferSourceFactory : ficheRefProp15, (r138 & 262144) != 0 ? sales.transferSourceWareHouse : ficheRefProp16, (r138 & 524288) != 0 ? sales.transferSourceCostGrp : ficheRefProp17, (r138 & 1048576) != 0 ? sales.eDocSerial : ficheRefProp18, (r138 & 2097152) != 0 ? sales.eDespatch : ficheBooleanProp4, (r138 & 4194304) != 0 ? sales.insteadOfEDespatch : ficheBooleanProp5, (r138 & 8388608) != 0 ? sales.erpInvoiceType : ficheRefProp19, (r138 & 16777216) != 0 ? sales.dueDate : ficheDateProp4, (r138 & 33554432) != 0 ? sales.campaingRefs : str7, (r138 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? sales.reserved : ficheBooleanProp6, (r138 & 134217728) != 0 ? sales.visitInfoId : r130, (r138 & 268435456) != 0 ? sales.orderFicheStatus : str8, (r138 & 536870912) != 0 ? sales.mSalesDetailList : arrayList, (r138 & BasicMeasure.EXACTLY) != 0 ? sales.eDispatchAdditionalInfo : eDispatchAdditionalInfo, (r138 & Integer.MIN_VALUE) != 0 ? sales.tempSalesDetailList : arrayList2, (r139 & 1) != 0 ? sales.customScope : coroutineScope);
    }
    public  int component1() {
        return this.logicalRef;
    }
    public  String component10() {
        return this.gDate;
    }
    public  int component11() {
        return this.ficheCreateDateInt;
    }
    public  int component12() {
        return this.transferCount;
    }
    public  int component13() {
        return this.printCount;
    }
    public  double component14() {
        return this.grossTotal;
    }
    public  double component15() {
        return this.total;
    }
    public  double component16() {
        return this.totalNet;
    }
    public  double component17() {
        return this.totalVat;
    }
    public  double component18() {
        return this.discTotal;
    }
    public  double component19() {
        return this.totalExpenses;
    }
    public  int component2() {
        return this.clRef;
    }
    public  int component20() {
        return this.orderType;
    }
    public  double component21() {
        return this.latitude;
    }
    public  double component22() {
        return this.longitude;
    }
    public  double component23() {
        return this.totalNetVolume;
    }
    public  double component24() {
        return this.totalGrossVolume;
    }
    public  double component25() {
        return this.totalNetWeight;
    }
    public  double component26() {
        return this.totalGrossWeight;
    }
    public  double component27() {
        return this.totalSku;
    }
    public  int component28() {
        return this.campaign;
    }
    public  int component29() {
        return this.salesCondition;
    }
    public  String component3() {
        return this.clCode;
    }
    public  FicheRefProp component30() {
        return this.branch;
    }
    public  FicheRefProp component31() {
        return this.division;
    }
    public  FicheRefProp component32() {
        return this.factory;
    }
    public  FicheRefProp component33() {
        return this.wareHouse;
    }
    public  FicheRefProp component34() {
        return this.returnWareHouse;
    }
    public  FicheRefProp component35() {
        return this.sourceWareHouse;
    }
    public  FicheRefProp component36() {
        return this.speCode;
    }
    public  FicheRefProp component37() {
        return this.warrantyCode;
    }
    public  FicheStringProp component38() {
        return this.documentNo;
    }
    public  FicheStringProp component39() {
        return this.documentTrackingNo;
    }
    public  int component4() {
        return this.ficheRef;
    }
    public  FicheStringProp component40() {
        return this.customerOrderNo;
    }
    public  FicheDateProp component41() {
        return this.deliveryDate;
    }
    public  FicheBooleanProp component42() {
        return this.paymentOrder;
    }
    public  FicheBooleanProp component43() {
        return this.consignee;
    }
    public  FicheStringProp component44() {
        return this.explanation1;
    }
    public  FicheStringProp component45() {
        return this.explanation2;
    }
    public  FicheStringProp component46() {
        return this.explanation3;
    }

    public  FicheStringProp component47() {
        return this.explanation4;
    }

    public  FicheStringProp component48() {
        return this.explanation5;
    }

    public  FicheStringProp component49() {
        return this.explanation6;
    }

    public  String component5() {
        return this.andFicheNo;
    }

    public  FicheStringProp component50() {
        return this.explanation7;
    }

    public  FicheStringProp component51() {
        return this.explanation8;
    }

    public  FicheStringProp component52() {
        return this.explanation9;
    }

    public  FicheStringProp component53() {
        return this.explanation10;
    }

    public  FicheDiscountRefProp component54() {
        return this.shipAccount;
    }

    public  FicheDiscountRefProp component55() {
        return this.shipAddress;
    }

    public  FicheRefProp component56() {
        return this.shipAgent;
    }

    public  FicheRefProp component57() {
        return this.shipType;
    }

    public  String component58() {
        return this.invoiceAddress;
    }

    public  FicheRefProp component59() {
        return this.tradeGroup;
    }

    public  String component6() {
        return this.ficheNo;
    }

    public  FicheDiscountRefProp component60() {
        return this.payPlan;
    }

    public  FicheDiscountRefProp component61() {
        return this.projectCode;
    }

    public  SalesFicheDiscountProps component62() {
        return this.salesFicheDiscountProps;
    }

    public  boolean component63() {
        return this.isNotUseGattribKdv;
    }

    public  int component64() {
        return this.isEdit;
    }

    public  FicheBooleanProp component65() {
        return this.includeVat;
    }

    public  boolean component66() {
        return this.isSaleVatCanBeChange;
    }

    public  boolean component67() {
        return this.isSaleVatDefaultChecked;
    }

    public  FicheStringProp component68() {
        return this.eInvoiceSGKDocumentNo;
    }

    public  FicheStringProp component69() {
        return this.taxPayerCode;
    }

    public  int component7() {
        return this.mSalesType;
    }

    public  FicheStringProp component70() {
        return this.taxPayerName;
    }

    public  FicheRefProp component71() {
        return this.eInvoiceTypSgk;
    }

    public  FicheDateProp component72() {
        return this.beginDate;
    }

    public  FicheDateProp component73() {
        return this.endDate;
    }

    public  FicheDiscountRefProp component74() {
        return this.cabin;
    }

    public  FicheDiscountRefProp component75() {
        return this.firstSpeCode;
    }

    public  FicheDiscountRefProp component76() {
        return this.secondSpeCode;
    }

    public  double component77() {
        return this.trRate;
    }

    public  int component78() {
        return this.trCurr;
    }

    public  int component79() {
        return this.orderRef;
    }

    public  int component8() {
        return this.salesStatus;
    }

    public  FicheRefProp component80() {
        return this.transferSourceBranch;
    }

    public  FicheRefProp component81() {
        return this.transferSourceDivision;
    }

    public  FicheRefProp component82() {
        return this.transferSourceFactory;
    }

    public  FicheRefProp component83() {
        return this.transferSourceWareHouse;
    }

    public  FicheRefProp component84() {
        return this.transferSourceCostGrp;
    }

    public  FicheRefProp component85() {
        return this.eDocSerial;
    }

    public  FicheBooleanProp component86() {
        return this.eDespatch;
    }

    public  FicheBooleanProp component87() {
        return this.insteadOfEDespatch;
    }

    public  FicheRefProp component88() {
        return this.erpInvoiceType;
    }

    public  FicheDateProp component89() {
        return this.dueDate;
    }

    public  String component9() {
        return this.ficheCreateDate;
    }

    public  String component90() {
        return this.campaingRefs;
    }

    public  FicheBooleanProp component91() {
        return this.reserved;
    }

    public  int component92() {
        return this.visitInfoId;
    }

    public  String component93() {
        return this.orderFicheStatus;
    }

    public  ArrayList<SalesDetail> component94() {
        return this.mSalesDetailList;
    }

    public  EDispatchAdditionalInfo component95() {
        return this.eDispatchAdditionalInfo;
    }

    public  ArrayList<SalesDetail> component96() {
        return this.tempSalesDetailList;
    }

    public  CoroutineScope component97() {
        return this.customScope;
    }

    public  Sales copy(int r114, int r115, String clCode, int r117, String str, String str2, int r120, int r121, String str3, String str4, int r124, int r125, int r126, double d2, double d3, double d4, double d5, double d6, double d7, int r139, double d8, double d9, double d10, double d11, double d12, double d13, double d14, int r154, int r155, FicheRefProp branch, FicheRefProp division, FicheRefProp factory, FicheRefProp wareHouse, FicheRefProp returnWareHouse, FicheRefProp sourceWareHouse, FicheRefProp speCode, FicheRefProp warrantyCode, FicheStringProp documentNo, FicheStringProp documentTrackingNo, FicheStringProp customerOrderNo, FicheDateProp deliveryDate, FicheBooleanProp paymentOrder, FicheBooleanProp consignee, FicheStringProp explanation1, FicheStringProp explanation2, FicheStringProp explanation3, FicheStringProp explanation4, FicheStringProp explanation5, FicheStringProp explanation6, FicheStringProp explanation7, FicheStringProp explanation8, FicheStringProp explanation9, FicheStringProp explanation10, FicheDiscountRefProp shipAccount, FicheDiscountRefProp shipAddress, FicheRefProp shipAgent, FicheRefProp shipType, String str5, FicheRefProp tradeGroup, FicheDiscountRefProp payPlan, FicheDiscountRefProp projectCode, SalesFicheDiscountProps salesFicheDiscountProps, boolean z, int r190, FicheBooleanProp includeVat, boolean z2, boolean z3, FicheStringProp eInvoiceSGKDocumentNo, FicheStringProp taxPayerCode, FicheStringProp taxPayerName, FicheRefProp eInvoiceTypSgk, FicheDateProp beginDate, FicheDateProp endDate, FicheDiscountRefProp cabin, FicheDiscountRefProp firstSpeCode, FicheDiscountRefProp secondSpeCode, double d15, int r205, int r206, FicheRefProp transferSourceBranch, FicheRefProp transferSourceDivision, FicheRefProp transferSourceFactory, FicheRefProp transferSourceWareHouse, FicheRefProp transferSourceCostGrp, FicheRefProp eDocSerial, FicheBooleanProp eDespatch, FicheBooleanProp insteadOfEDespatch, FicheRefProp erpInvoiceType, FicheDateProp dueDate, String str6, FicheBooleanProp reserved, int r219, String str7, ArrayList<SalesDetail> arrayList, EDispatchAdditionalInfo eDispatchAdditionalInfo, ArrayList<SalesDetail> tempSalesDetailList, CoroutineScope customScope) {
        Intrinsics.checkNotNullParameter(clCode, "clCode");
        Intrinsics.checkNotNullParameter(branch, "branch");
        Intrinsics.checkNotNullParameter(division, "division");
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(wareHouse, "wareHouse");
        Intrinsics.checkNotNullParameter(returnWareHouse, "returnWareHouse");
        Intrinsics.checkNotNullParameter(sourceWareHouse, "sourceWareHouse");
        Intrinsics.checkNotNullParameter(speCode, "speCode");
        Intrinsics.checkNotNullParameter(warrantyCode, "warrantyCode");
        Intrinsics.checkNotNullParameter(documentNo, "documentNo");
        Intrinsics.checkNotNullParameter(documentTrackingNo, "documentTrackingNo");
        Intrinsics.checkNotNullParameter(customerOrderNo, "customerOrderNo");
        Intrinsics.checkNotNullParameter(deliveryDate, "deliveryDate");
        Intrinsics.checkNotNullParameter(paymentOrder, "paymentOrder");
        Intrinsics.checkNotNullParameter(consignee, "consignee");
        Intrinsics.checkNotNullParameter(explanation1, "explanation1");
        Intrinsics.checkNotNullParameter(explanation2, "explanation2");
        Intrinsics.checkNotNullParameter(explanation3, "explanation3");
        Intrinsics.checkNotNullParameter(explanation4, "explanation4");
        Intrinsics.checkNotNullParameter(explanation5, "explanation5");
        Intrinsics.checkNotNullParameter(explanation6, "explanation6");
        Intrinsics.checkNotNullParameter(explanation7, "explanation7");
        Intrinsics.checkNotNullParameter(explanation8, "explanation8");
        Intrinsics.checkNotNullParameter(explanation9, "explanation9");
        Intrinsics.checkNotNullParameter(explanation10, "explanation10");
        Intrinsics.checkNotNullParameter(shipAccount, "shipAccount");
        Intrinsics.checkNotNullParameter(shipAddress, "shipAddress");
        Intrinsics.checkNotNullParameter(shipAgent, "shipAgent");
        Intrinsics.checkNotNullParameter(shipType, "shipType");
        Intrinsics.checkNotNullParameter(tradeGroup, "tradeGroup");
        Intrinsics.checkNotNullParameter(payPlan, "payPlan");
        Intrinsics.checkNotNullParameter(projectCode, "projectCode");
        Intrinsics.checkNotNullParameter(salesFicheDiscountProps, "salesFicheDiscountProps");
        Intrinsics.checkNotNullParameter(includeVat, "includeVat");
        Intrinsics.checkNotNullParameter(eInvoiceSGKDocumentNo, "eInvoiceSGKDocumentNo");
        Intrinsics.checkNotNullParameter(taxPayerCode, "taxPayerCode");
        Intrinsics.checkNotNullParameter(taxPayerName, "taxPayerName");
        Intrinsics.checkNotNullParameter(eInvoiceTypSgk, "eInvoiceTypSgk");
        Intrinsics.checkNotNullParameter(beginDate, "beginDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(cabin, "cabin");
        Intrinsics.checkNotNullParameter(firstSpeCode, "firstSpeCode");
        Intrinsics.checkNotNullParameter(secondSpeCode, "secondSpeCode");
        Intrinsics.checkNotNullParameter(transferSourceBranch, "transferSourceBranch");
        Intrinsics.checkNotNullParameter(transferSourceDivision, "transferSourceDivision");
        Intrinsics.checkNotNullParameter(transferSourceFactory, "transferSourceFactory");
        Intrinsics.checkNotNullParameter(transferSourceWareHouse, "transferSourceWareHouse");
        Intrinsics.checkNotNullParameter(transferSourceCostGrp, "transferSourceCostGrp");
        Intrinsics.checkNotNullParameter(eDocSerial, "eDocSerial");
        Intrinsics.checkNotNullParameter(eDespatch, "eDespatch");
        Intrinsics.checkNotNullParameter(insteadOfEDespatch, "insteadOfEDespatch");
        Intrinsics.checkNotNullParameter(erpInvoiceType, "erpInvoiceType");
        Intrinsics.checkNotNullParameter(dueDate, "dueDate");
        Intrinsics.checkNotNullParameter(reserved, "reserved");
        Intrinsics.checkNotNullParameter(tempSalesDetailList, "tempSalesDetailList");
        Intrinsics.checkNotNullParameter(customScope, "customScope");
        return new Sales(r114, r115, clCode, r117, str, str2, r120, r121, str3, str4, r124, r125, r126, d2, d3, d4, d5, d6, d7, r139, d8, d9, d10, d11, d12, d13, d14, r154, r155, branch, division, factory, wareHouse, returnWareHouse, sourceWareHouse, speCode, warrantyCode, documentNo, documentTrackingNo, customerOrderNo, deliveryDate, paymentOrder, consignee, explanation1, explanation2, explanation3, explanation4, explanation5, explanation6, explanation7, explanation8, explanation9, explanation10, shipAccount, shipAddress, shipAgent, shipType, str5, tradeGroup, payPlan, projectCode, salesFicheDiscountProps, z, r190, includeVat, z2, z3, eInvoiceSGKDocumentNo, taxPayerCode, taxPayerName, eInvoiceTypSgk, beginDate, endDate, cabin, firstSpeCode, secondSpeCode, d15, r205, r206, transferSourceBranch, transferSourceDivision, transferSourceFactory, transferSourceWareHouse, transferSourceCostGrp, eDocSerial, eDespatch, insteadOfEDespatch, erpInvoiceType, dueDate, str6, reserved, r219, str7, arrayList, eDispatchAdditionalInfo, tempSalesDetailList, customScope);
    }
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Sales sales)) {
            return false;
        }
        return this.logicalRef == sales.logicalRef && this.clRef == sales.clRef && Intrinsics.areEqual(this.clCode, sales.clCode) && this.ficheRef == sales.ficheRef && Intrinsics.areEqual(this.andFicheNo, sales.andFicheNo) && Intrinsics.areEqual(this.ficheNo, sales.ficheNo) && this.mSalesType == sales.mSalesType && this.salesStatus == sales.salesStatus && Intrinsics.areEqual(this.ficheCreateDate, sales.ficheCreateDate) && Intrinsics.areEqual(this.gDate, sales.gDate) && this.ficheCreateDateInt == sales.ficheCreateDateInt && this.transferCount == sales.transferCount && this.printCount == sales.printCount && Double.compare(this.grossTotal, sales.grossTotal) == 0 && Double.compare(this.total, sales.total) == 0 && Double.compare(this.totalNet, sales.totalNet) == 0 && Double.compare(this.totalVat, sales.totalVat) == 0 && Double.compare(this.discTotal, sales.discTotal) == 0 && Double.compare(this.totalExpenses, sales.totalExpenses) == 0 && this.orderType == sales.orderType && Double.compare(this.latitude, sales.latitude) == 0 && Double.compare(this.longitude, sales.longitude) == 0 && Double.compare(this.totalNetVolume, sales.totalNetVolume) == 0 && Double.compare(this.totalGrossVolume, sales.totalGrossVolume) == 0 && Double.compare(this.totalNetWeight, sales.totalNetWeight) == 0 && Double.compare(this.totalGrossWeight, sales.totalGrossWeight) == 0 && Double.compare(this.totalSku, sales.totalSku) == 0 && this.campaign == sales.campaign && this.salesCondition == sales.salesCondition && Intrinsics.areEqual(this.branch, sales.branch) && Intrinsics.areEqual(this.division, sales.division) && Intrinsics.areEqual(this.factory, sales.factory) && Intrinsics.areEqual(this.wareHouse, sales.wareHouse) && Intrinsics.areEqual(this.returnWareHouse, sales.returnWareHouse) && Intrinsics.areEqual(this.sourceWareHouse, sales.sourceWareHouse) && Intrinsics.areEqual(this.speCode, sales.speCode) && Intrinsics.areEqual(this.warrantyCode, sales.warrantyCode) && Intrinsics.areEqual(this.documentNo, sales.documentNo) && Intrinsics.areEqual(this.documentTrackingNo, sales.documentTrackingNo) && Intrinsics.areEqual(this.customerOrderNo, sales.customerOrderNo) && Intrinsics.areEqual(this.deliveryDate, sales.deliveryDate) && Intrinsics.areEqual(this.paymentOrder, sales.paymentOrder) && Intrinsics.areEqual(this.consignee, sales.consignee) && Intrinsics.areEqual(this.explanation1, sales.explanation1) && Intrinsics.areEqual(this.explanation2, sales.explanation2) && Intrinsics.areEqual(this.explanation3, sales.explanation3) && Intrinsics.areEqual(this.explanation4, sales.explanation4) && Intrinsics.areEqual(this.explanation5, sales.explanation5) && Intrinsics.areEqual(this.explanation6, sales.explanation6) && Intrinsics.areEqual(this.explanation7, sales.explanation7) && Intrinsics.areEqual(this.explanation8, sales.explanation8) && Intrinsics.areEqual(this.explanation9, sales.explanation9) && Intrinsics.areEqual(this.explanation10, sales.explanation10) && Intrinsics.areEqual(this.shipAccount, sales.shipAccount) && Intrinsics.areEqual(this.shipAddress, sales.shipAddress) && Intrinsics.areEqual(this.shipAgent, sales.shipAgent) && Intrinsics.areEqual(this.shipType, sales.shipType) && Intrinsics.areEqual(this.invoiceAddress, sales.invoiceAddress) && Intrinsics.areEqual(this.tradeGroup, sales.tradeGroup) && Intrinsics.areEqual(this.payPlan, sales.payPlan) && Intrinsics.areEqual(this.projectCode, sales.projectCode) && Intrinsics.areEqual(this.salesFicheDiscountProps, sales.salesFicheDiscountProps) && this.isNotUseGattribKdv == sales.isNotUseGattribKdv && this.isEdit == sales.isEdit && Intrinsics.areEqual(this.includeVat, sales.includeVat) && this.isSaleVatCanBeChange == sales.isSaleVatCanBeChange && this.isSaleVatDefaultChecked == sales.isSaleVatDefaultChecked && Intrinsics.areEqual(this.eInvoiceSGKDocumentNo, sales.eInvoiceSGKDocumentNo) && Intrinsics.areEqual(this.taxPayerCode, sales.taxPayerCode) && Intrinsics.areEqual(this.taxPayerName, sales.taxPayerName) && Intrinsics.areEqual(this.eInvoiceTypSgk, sales.eInvoiceTypSgk) && Intrinsics.areEqual(this.beginDate, sales.beginDate) && Intrinsics.areEqual(this.endDate, sales.endDate) && Intrinsics.areEqual(this.cabin, sales.cabin) && Intrinsics.areEqual(this.firstSpeCode, sales.firstSpeCode) && Intrinsics.areEqual(this.secondSpeCode, sales.secondSpeCode) && Double.compare(this.trRate, sales.trRate) == 0 && this.trCurr == sales.trCurr && this.orderRef == sales.orderRef && Intrinsics.areEqual(this.transferSourceBranch, sales.transferSourceBranch) && Intrinsics.areEqual(this.transferSourceDivision, sales.transferSourceDivision) && Intrinsics.areEqual(this.transferSourceFactory, sales.transferSourceFactory) && Intrinsics.areEqual(this.transferSourceWareHouse, sales.transferSourceWareHouse) && Intrinsics.areEqual(this.transferSourceCostGrp, sales.transferSourceCostGrp) && Intrinsics.areEqual(this.eDocSerial, sales.eDocSerial) && Intrinsics.areEqual(this.eDespatch, sales.eDespatch) && Intrinsics.areEqual(this.insteadOfEDespatch, sales.insteadOfEDespatch) && Intrinsics.areEqual(this.erpInvoiceType, sales.erpInvoiceType) && Intrinsics.areEqual(this.dueDate, sales.dueDate) && Intrinsics.areEqual(this.campaingRefs, sales.campaingRefs) && Intrinsics.areEqual(this.reserved, sales.reserved) && this.visitInfoId == sales.visitInfoId && Intrinsics.areEqual(this.orderFicheStatus, sales.orderFicheStatus) && Intrinsics.areEqual(this.mSalesDetailList, sales.mSalesDetailList) && Intrinsics.areEqual(this.eDispatchAdditionalInfo, sales.eDispatchAdditionalInfo) && Intrinsics.areEqual(this.tempSalesDetailList, sales.tempSalesDetailList) && Intrinsics.areEqual(this.customScope, sales.customScope);
    }

    public int hashCode() {
        int r0 = ((((((Integer.hashCode(this.logicalRef) * 31) + Integer.hashCode(this.clRef)) * 31) + this.clCode.hashCode()) * 31) + Integer.hashCode(this.ficheRef)) * 31;
        String str = this.andFicheNo;
        int r02 = (r0 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.ficheNo;
        int r03 = (((((r02 + (str2 == null ? 0 : str2.hashCode())) * 31) + Integer.hashCode(this.mSalesType)) * 31) + Integer.hashCode(this.salesStatus)) * 31;
        String str3 = this.ficheCreateDate;
        int r04 = (r03 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.gDate;
        int r05 = (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((r04 + (str4 == null ? 0 : str4.hashCode())) * 31) + Integer.hashCode(this.ficheCreateDateInt)) * 31) + Integer.hashCode(this.transferCount)) * 31) + Integer.hashCode(this.printCount)) * 31) + Double.hashCode(this.grossTotal)) * 31) + Double.hashCode(this.total)) * 31) + Double.hashCode(this.totalNet)) * 31) + Double.hashCode(this.totalVat)) * 31) + Double.hashCode(this.discTotal)) * 31) + Double.hashCode(this.totalExpenses)) * 31) + Integer.hashCode(this.orderType)) * 31) + Double.hashCode(this.latitude)) * 31) + Double.hashCode(this.longitude)) * 31) + Double.hashCode(this.totalNetVolume)) * 31) + Double.hashCode(this.totalGrossVolume)) * 31) + Double.hashCode(this.totalNetWeight)) * 31) + Double.hashCode(this.totalGrossWeight)) * 31) + Double.hashCode(this.totalSku)) * 31) + Integer.hashCode(this.campaign)) * 31) + Integer.hashCode(this.salesCondition)) * 31) + this.branch.hashCode()) * 31) + this.division.hashCode()) * 31) + this.factory.hashCode()) * 31) + this.wareHouse.hashCode()) * 31) + this.returnWareHouse.hashCode()) * 31) + this.sourceWareHouse.hashCode()) * 31) + this.speCode.hashCode()) * 31) + this.warrantyCode.hashCode()) * 31) + this.documentNo.hashCode()) * 31) + this.documentTrackingNo.hashCode()) * 31) + this.customerOrderNo.hashCode()) * 31) + this.deliveryDate.hashCode()) * 31) + this.paymentOrder.hashCode()) * 31) + this.consignee.hashCode()) * 31) + this.explanation1.hashCode()) * 31) + this.explanation2.hashCode()) * 31) + this.explanation3.hashCode()) * 31) + this.explanation4.hashCode()) * 31) + this.explanation5.hashCode()) * 31) + this.explanation6.hashCode()) * 31) + this.explanation7.hashCode()) * 31) + this.explanation8.hashCode()) * 31) + this.explanation9.hashCode()) * 31) + this.explanation10.hashCode()) * 31) + this.shipAccount.hashCode()) * 31) + this.shipAddress.hashCode()) * 31) + this.shipAgent.hashCode()) * 31) + this.shipType.hashCode()) * 31;
        String str5 = this.invoiceAddress;
        int r06 = (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((r05 + (str5 == null ? 0 : str5.hashCode())) * 31) + this.tradeGroup.hashCode()) * 31) + this.payPlan.hashCode()) * 31) + this.projectCode.hashCode()) * 31) + this.salesFicheDiscountProps.hashCode()) * 31) + Boolean.hashCode(this.isNotUseGattribKdv)) * 31) + Integer.hashCode(this.isEdit)) * 31) + this.includeVat.hashCode()) * 31) + Boolean.hashCode(this.isSaleVatCanBeChange)) * 31) + Boolean.hashCode(this.isSaleVatDefaultChecked)) * 31) + this.eInvoiceSGKDocumentNo.hashCode()) * 31) + this.taxPayerCode.hashCode()) * 31) + this.taxPayerName.hashCode()) * 31) + this.eInvoiceTypSgk.hashCode()) * 31) + this.beginDate.hashCode()) * 31) + this.endDate.hashCode()) * 31) + this.cabin.hashCode()) * 31) + this.firstSpeCode.hashCode()) * 31) + this.secondSpeCode.hashCode()) * 31) + Double.hashCode(this.trRate)) * 31) + Integer.hashCode(this.trCurr)) * 31) + Integer.hashCode(this.orderRef)) * 31) + this.transferSourceBranch.hashCode()) * 31) + this.transferSourceDivision.hashCode()) * 31) + this.transferSourceFactory.hashCode()) * 31) + this.transferSourceWareHouse.hashCode()) * 31) + this.transferSourceCostGrp.hashCode()) * 31) + this.eDocSerial.hashCode()) * 31) + this.eDespatch.hashCode()) * 31) + this.insteadOfEDespatch.hashCode()) * 31) + this.erpInvoiceType.hashCode()) * 31) + this.dueDate.hashCode()) * 31;
        String str6 = this.campaingRefs;
        int r07 = (((((r06 + (str6 == null ? 0 : str6.hashCode())) * 31) + this.reserved.hashCode()) * 31) + Integer.hashCode(this.visitInfoId)) * 31;
        String str7 = this.orderFicheStatus;
        int r08 = (r07 + (str7 == null ? 0 : str7.hashCode())) * 31;
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        int r09 = (r08 + (arrayList == null ? 0 : arrayList.hashCode())) * 31;
        EDispatchAdditionalInfo eDispatchAdditionalInfo = this.eDispatchAdditionalInfo;
        return ((((r09 + (eDispatchAdditionalInfo != null ? eDispatchAdditionalInfo.hashCode() : 0)) * 31) + this.tempSalesDetailList.hashCode()) * 31) + this.customScope.hashCode();
    }

    public String toString() {
        return "Sales(logicalRef=" + this.logicalRef + ", clRef=" + this.clRef + ", clCode=" + this.clCode + ", ficheRef=" + this.ficheRef + ", andFicheNo=" + this.andFicheNo + ", ficheNo=" + this.ficheNo + ", mSalesType=" + this.mSalesType + ", salesStatus=" + this.salesStatus + ", ficheCreateDate=" + this.ficheCreateDate + ", gDate=" + this.gDate + ", ficheCreateDateInt=" + this.ficheCreateDateInt + ", transferCount=" + this.transferCount + ", printCount=" + this.printCount + ", grossTotal=" + this.grossTotal + ", total=" + this.total + ", totalNet=" + this.totalNet + ", totalVat=" + this.totalVat + ", discTotal=" + this.discTotal + ", totalExpenses=" + this.totalExpenses + ", orderType=" + this.orderType + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", totalNetVolume=" + this.totalNetVolume + ", totalGrossVolume=" + this.totalGrossVolume + ", totalNetWeight=" + this.totalNetWeight + ", totalGrossWeight=" + this.totalGrossWeight + ", totalSku=" + this.totalSku + ", campaign=" + this.campaign + ", salesCondition=" + this.salesCondition + ", branch=" + this.branch + ", division=" + this.division + ", factory=" + this.factory + ", wareHouse=" + this.wareHouse + ", returnWareHouse=" + this.returnWareHouse + ", sourceWareHouse=" + this.sourceWareHouse + ", speCode=" + this.speCode + ", warrantyCode=" + this.warrantyCode + ", documentNo=" + this.documentNo + ", documentTrackingNo=" + this.documentTrackingNo + ", customerOrderNo=" + this.customerOrderNo + ", deliveryDate=" + this.deliveryDate + ", paymentOrder=" + this.paymentOrder + ", consignee=" + this.consignee + ", explanation1=" + this.explanation1 + ", explanation2=" + this.explanation2 + ", explanation3=" + this.explanation3 + ", explanation4=" + this.explanation4 + ", explanation5=" + this.explanation5 + ", explanation6=" + this.explanation6 + ", explanation7=" + this.explanation7 + ", explanation8=" + this.explanation8 + ", explanation9=" + this.explanation9 + ", explanation10=" + this.explanation10 + ", shipAccount=" + this.shipAccount + ", shipAddress=" + this.shipAddress + ", shipAgent=" + this.shipAgent + ", shipType=" + this.shipType + ", invoiceAddress=" + this.invoiceAddress + ", tradeGroup=" + this.tradeGroup + ", payPlan=" + this.payPlan + ", projectCode=" + this.projectCode + ", salesFicheDiscountProps=" + this.salesFicheDiscountProps + ", isNotUseGattribKdv=" + this.isNotUseGattribKdv + ", isEdit=" + this.isEdit + ", includeVat=" + this.includeVat + ", isSaleVatCanBeChange=" + this.isSaleVatCanBeChange + ", isSaleVatDefaultChecked=" + this.isSaleVatDefaultChecked + ", eInvoiceSGKDocumentNo=" + this.eInvoiceSGKDocumentNo + ", taxPayerCode=" + this.taxPayerCode + ", taxPayerName=" + this.taxPayerName + ", eInvoiceTypSgk=" + this.eInvoiceTypSgk + ", beginDate=" + this.beginDate + ", endDate=" + this.endDate + ", cabin=" + this.cabin + ", firstSpeCode=" + this.firstSpeCode + ", secondSpeCode=" + this.secondSpeCode + ", trRate=" + this.trRate + ", trCurr=" + this.trCurr + ", orderRef=" + this.orderRef + ", transferSourceBranch=" + this.transferSourceBranch + ", transferSourceDivision=" + this.transferSourceDivision + ", transferSourceFactory=" + this.transferSourceFactory + ", transferSourceWareHouse=" + this.transferSourceWareHouse + ", transferSourceCostGrp=" + this.transferSourceCostGrp + ", eDocSerial=" + this.eDocSerial + ", eDespatch=" + this.eDespatch + ", insteadOfEDespatch=" + this.insteadOfEDespatch + ", erpInvoiceType=" + this.erpInvoiceType + ", dueDate=" + this.dueDate + ", campaingRefs=" + this.campaingRefs + ", reserved=" + this.reserved + ", visitInfoId=" + this.visitInfoId + ", orderFicheStatus=" + this.orderFicheStatus + ", mSalesDetailList=" + this.mSalesDetailList + ", eDispatchAdditionalInfo=" + this.eDispatchAdditionalInfo + ", tempSalesDetailList=" + this.tempSalesDetailList + ", customScope=" + this.customScope + ')';
    }

    public  int getLogicalRef() {
        return this.logicalRef;
    }

    public  void setLogicalRef(int r1) {
        this.logicalRef = r1;
    }

    public  int getClRef() {
        return this.clRef;
    }

    public  void setClRef(int r1) {
        this.clRef = r1;
    }

    public Sales(int r95, int r96, String str, int r98, String str2, String str3, int r101, int r102, String str4, String str5, int r105, int r106, int r107, double d2, double d3, double d4, double d5, double d6, double d7, int r120, double d8, double d9, double d10, double d11, double d12, double d13, double d14, int r135, int r136, FicheRefProp ficheRefProp, FicheRefProp ficheRefProp2, FicheRefProp ficheRefProp3, FicheRefProp ficheRefProp4, FicheRefProp ficheRefProp5, FicheRefProp ficheRefProp6, FicheRefProp ficheRefProp7, FicheRefProp ficheRefProp8, FicheStringProp ficheStringProp, FicheStringProp ficheStringProp2, FicheStringProp ficheStringProp3, FicheDateProp ficheDateProp, FicheBooleanProp ficheBooleanProp, FicheBooleanProp ficheBooleanProp2, FicheStringProp ficheStringProp4, FicheStringProp ficheStringProp5, FicheStringProp ficheStringProp6, FicheStringProp ficheStringProp7, FicheStringProp ficheStringProp8, FicheStringProp ficheStringProp9, FicheStringProp ficheStringProp10, FicheStringProp ficheStringProp11, FicheStringProp ficheStringProp12, FicheStringProp ficheStringProp13, FicheDiscountRefProp ficheDiscountRefProp, FicheDiscountRefProp ficheDiscountRefProp2, FicheRefProp ficheRefProp9, FicheRefProp ficheRefProp10, String str6, FicheRefProp ficheRefProp11, FicheDiscountRefProp ficheDiscountRefProp3, FicheDiscountRefProp ficheDiscountRefProp4, SalesFicheDiscountProps salesFicheDiscountProps, boolean z, int r171, FicheBooleanProp ficheBooleanProp3, boolean z2, boolean z3, FicheStringProp ficheStringProp14, FicheStringProp ficheStringProp15, FicheStringProp ficheStringProp16, FicheRefProp ficheRefProp12, FicheDateProp ficheDateProp2, FicheDateProp ficheDateProp3, FicheDiscountRefProp ficheDiscountRefProp5, FicheDiscountRefProp ficheDiscountRefProp6, FicheDiscountRefProp ficheDiscountRefProp7, double d15, int r186, int r187, FicheRefProp ficheRefProp13, FicheRefProp ficheRefProp14, FicheRefProp ficheRefProp15, FicheRefProp ficheRefProp16, FicheRefProp ficheRefProp17, FicheRefProp ficheRefProp18, FicheBooleanProp ficheBooleanProp4, FicheBooleanProp ficheBooleanProp5, FicheRefProp ficheRefProp19, FicheDateProp ficheDateProp4, String str7, FicheBooleanProp ficheBooleanProp6, int r200, String str8, ArrayList arrayList, EDispatchAdditionalInfo eDispatchAdditionalInfo, ArrayList arrayList2, CoroutineScope coroutineScope, int r206, int r207, int r208, int r209, DefaultConstructorMarker defaultConstructorMarker) {
        FicheRefProp ficheRefProp20;
        FicheStringProp ficheStringProp17;
        SalesFicheDiscountProps salesFicheDiscountProps2;
        boolean z4;
        int r1872;
        CoroutineScope CoroutineScope;
        int r3 = (r206 & 1) != 0 ? 0 : r95;
        int r5 = (r206 & 2) != 0 ? 0 : r96;
        String str9 = (r206 & 4) != 0 ? "" : str;
        int r7 = (r206 & 8) != 0 ? 0 : r98;
        String str10 = (r206 & 16) != 0 ? null : str2;
        String str11 = (r206 & 32) != 0 ? null : str3;
        int r11 = (r206 & 64) != 0 ? 0 : r101;
        int r12 = (r206 & 128) != 0 ? 0 : r102;
        String str12 = (r206 & 256) != 0 ? null : str4;
        String str13 = (r206 & 512) != 0 ? null : str5;
        int r15 = (r206 & 1024) != 0 ? 0 : r105;
        int r4 = (r206 & 2048) != 0 ? 0 : r106;
        int r1072 = (r206 & 4096) != 0 ? 0 : r107;
        double d16 = (r206 & 8192) != 0 ? 0.0d : d2;
        double d17 = (r206 & 16384) != 0 ? 0.0d : d3;
        double d18 = (r206 & 32768) != 0 ? 0.0d : d4;
        double d19 = (r206 & 65536) != 0 ? 0.0d : d5;
        double d20 = (r206 & 131072) != 0 ? 0.0d : d6;
        double d21 = (r206 & 262144) != 0 ? 0.0d : d7;
        int r33 = (r206 & 524288) != 0 ? 0 : r120;
        double d22 = (r206 & 1048576) != 0 ? 0.0d : d8;
        double d23 = (r206 & 2097152) != 0 ? 0.0d : d9;
        double d24 = (r206 & 4194304) != 0 ? 0.0d : d10;
        double d25 = (r206 & 8388608) != 0 ? 0.0d : d11;
        double d26 = (r206 & 16777216) != 0 ? 0.0d : d12;
        double d27 = (r206 & 33554432) != 0 ? 0.0d : d13;
        double d28 = (r206 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? 0.0d : d14;
        int r48 = (r206 & 134217728) != 0 ? 0 : r135;
        int r49 = (r206 & 268435456) != 0 ? 0 : r136;
        FicheRefProp ficheRefProp21 = (r206 & 536870912) != 0 ? new FicheRefProp() : ficheRefProp;
        FicheRefProp ficheRefProp22 = (r206 & BasicMeasure.EXACTLY) != 0 ? new FicheRefProp() : ficheRefProp2;
        FicheRefProp ficheRefProp23 = (r206 & Integer.MIN_VALUE) != 0 ? new FicheRefProp() : ficheRefProp3;
        FicheRefProp ficheRefProp24 = (r207 & 1) != 0 ? new FicheRefProp() : ficheRefProp4;
        FicheRefProp ficheRefProp25 = (r207 & 2) != 0 ? new FicheRefProp() : ficheRefProp5;
        FicheRefProp ficheRefProp26 = (r207 & 4) != 0 ? new FicheRefProp() : ficheRefProp6;
        FicheRefProp ficheRefProp27 = (r207 & 8) != 0 ? new FicheRefProp() : ficheRefProp7;
        FicheRefProp ficheRefProp28 = (r207 & 16) != 0 ? new FicheRefProp() : ficheRefProp8;
        FicheStringProp ficheRefProp29 = (r207 & 32) != 0 ? new FicheRefProp() : ficheStringProp;
        FicheStringProp ficheStringProp18 = (r207 & 64) != 0 ? new FicheStringProp() : ficheStringProp2;
        FicheStringProp ficheStringProp19 = (r207 & 128) != 0 ? new FicheStringProp() : ficheStringProp3;
        FicheDateProp ficheDateProp5 = (r207 & 256) != 0 ? new FicheDateProp() : ficheDateProp;
        FicheBooleanProp ficheBooleanProp7 = (r207 & 512) != 0 ? new FicheBooleanProp() : ficheBooleanProp;
        FicheBooleanProp ficheBooleanProp8 = (r207 & 1024) != 0 ? new FicheBooleanProp() : ficheBooleanProp2;
        FicheStringProp ficheStringProp20 = (r207 & 2048) != 0 ? new FicheStringProp() : ficheStringProp4;
        FicheStringProp ficheStringProp21 = (r207 & 4096) != 0 ? new FicheStringProp() : ficheStringProp5;
        FicheStringProp ficheStringProp22 = (r207 & 8192) != 0 ? new FicheStringProp() : ficheStringProp6;
        FicheStringProp ficheStringProp23 = (r207 & 16384) != 0 ? new FicheStringProp() : ficheStringProp7;
        FicheStringProp ficheStringProp24 = (r207 & 32768) != 0 ? new FicheStringProp() : ficheStringProp8;
        FicheStringProp ficheStringProp25 = (r207 & 65536) != 0 ? new FicheStringProp() : ficheStringProp9;
        FicheStringProp ficheStringProp26 = (r207 & 131072) != 0 ? new FicheStringProp() : ficheStringProp10;
        FicheStringProp ficheStringProp27 = (r207 & 262144) != 0 ? new FicheStringProp() : ficheStringProp11;
        FicheStringProp ficheStringProp28 = (r207 & 524288) != 0 ? new FicheStringProp() : ficheStringProp12;
        FicheStringProp ficheStringProp29 = (r207 & 1048576) != 0 ? new FicheStringProp() : ficheStringProp13;
        FicheDiscountRefProp ficheDiscountRefProp8 = (r207 & 2097152) != 0 ? new FicheDiscountRefProp() : ficheDiscountRefProp;
        FicheDiscountRefProp ficheDiscountRefProp9 = (r207 & 4194304) != 0 ? new FicheDiscountRefProp() : ficheDiscountRefProp2;
        FicheRefProp ficheRefProp30 = (r207 & 8388608) != 0 ? new FicheRefProp() : ficheRefProp9;
        FicheRefProp ficheRefProp31 = (r207 & 16777216) != 0 ? new FicheRefProp() : ficheRefProp10;
        String str14 = (r207 & 33554432) != 0 ? null : str6;
        FicheRefProp ficheRefProp32 = (r207 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? new FicheRefProp() : ficheRefProp11;
        FicheDiscountRefProp ficheDiscountRefProp10 = (r207 & 134217728) != 0 ? new FicheDiscountRefProp() : ficheDiscountRefProp3;
        FicheDiscountRefProp ficheDiscountRefProp11 = (r207 & 268435456) != 0 ? new FicheDiscountRefProp() : ficheDiscountRefProp4;
        if ((r207 & 536870912) != 0) {
            ficheStringProp17 = ficheStringProp23;
            ficheRefProp20 = ficheRefProp23;
            salesFicheDiscountProps2 = new SalesFicheDiscountProps(5);
        } else {
            ficheRefProp20 = ficheRefProp23;
            ficheStringProp17 = ficheStringProp23;
            salesFicheDiscountProps2 = salesFicheDiscountProps;
        }
        boolean z5 = (1073741824 & r207) == 0 && z;
        int r1 = (r207 & Integer.MIN_VALUE) != 0 ? 0 : r171;
        FicheBooleanProp ficheBooleanProp9 = (r208 & 1) != 0 ? new FicheBooleanProp() : ficheBooleanProp3;
        boolean z6 = (r208 & 2) == 0 && z2;
        boolean z7 = (r208 & 4) == 0 && z3;
        FicheStringProp ficheStringProp30 = (r208 & 8) != 0 ? new FicheStringProp() : ficheStringProp14;
        FicheStringProp ficheStringProp31 = (r208 & 16) != 0 ? new FicheStringProp() : ficheStringProp15;
        FicheStringProp ficheStringProp32 = (r208 & 32) != 0 ? new FicheStringProp() : ficheStringProp16;
        FicheRefProp ficheRefProp33 = (r208 & 64) != 0 ? new FicheRefProp() : ficheRefProp12;
        int r1712 = r1;
        FicheDateProp ficheDateProp6 = (r208 & 128) != 0 ? new FicheDateProp() : ficheDateProp2;
        FicheDateProp ficheDateProp7 = (r208 & 256) != 0 ? new FicheDateProp() : ficheDateProp3;
        FicheDiscountRefProp ficheDiscountRefProp12 = (r208 & 512) != 0 ? new FicheDiscountRefProp() : ficheDiscountRefProp5;
        FicheDiscountRefProp ficheDiscountRefProp13 = (r208 & 1024) != 0 ? new FicheDiscountRefProp() : ficheDiscountRefProp6;
        FicheDiscountRefProp ficheDiscountRefProp14 = (r208 & 2048) != 0 ? new FicheDiscountRefProp() : ficheDiscountRefProp7;
        double d29 = (r208 & 4096) == 0 ? d15 : 0.0d;
        int r1862 = (r208 & 8192) != 0 ? 0 : r186;
        int r13 = (r208 & 16384) != 0 ? 0 : r187;
        FicheRefProp ficheRefProp34 = (r208 & 32768) != 0 ? new FicheRefProp() : ficheRefProp13;
        FicheRefProp ficheRefProp35 = (r208 & 65536) != 0 ? new FicheRefProp() : ficheRefProp14;
        FicheRefProp ficheRefProp36 = (r208 & 131072) != 0 ? new FicheRefProp() : ficheRefProp15;
        FicheRefProp ficheRefProp37 = (r208 & 262144) != 0 ? new FicheRefProp() : ficheRefProp16;
        FicheRefProp ficheRefProp38 = (r208 & 524288) != 0 ? new FicheRefProp() : ficheRefProp17;
        FicheRefProp ficheRefProp39 = (r208 & 1048576) != 0 ? new FicheRefProp() : ficheRefProp18;
        FicheBooleanProp ficheBooleanProp10 = (r208 & 2097152) != 0 ? new FicheBooleanProp() : ficheBooleanProp4;
        FicheBooleanProp ficheBooleanProp11 = (r208 & 4194304) != 0 ? new FicheBooleanProp() : ficheBooleanProp5;
        FicheRefProp ficheRefProp40 = (r208 & 8388608) != 0 ? new FicheRefProp() : ficheRefProp19;
        FicheDateProp ficheDateProp8 = (r208 & 16777216) != 0 ? new FicheDateProp() : ficheDateProp4;
        String str15 = (r208 & 33554432) != 0 ? null : str7;
        FicheBooleanProp ficheBooleanProp12 = (r208 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? new FicheBooleanProp() : ficheBooleanProp6;
        int r89 = (r208 & 134217728) != 0 ? 0 : r200;
        String str16 = (r208 & 268435456) != 0 ? null : str8;
        ArrayList arrayList3 = (r208 & 536870912) != 0 ? new ArrayList() : arrayList;
        EDispatchAdditionalInfo eDispatchAdditionalInfo2 = (r208 & BasicMeasure.EXACTLY) != 0 ? new EDispatchAdditionalInfo() : eDispatchAdditionalInfo;
        arrayList2 = (r208 & Integer.MIN_VALUE) != 0 ? new ArrayList() : arrayList2;
        if ((r209 & 1) != 0) {
            r1872 = r13;
            z4 = z5;
            CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getMain().plus(JobKt.Job(null)));
        } else {
            z4 = z5;
            r1872 = r13;
            CoroutineScope = coroutineScope;
        }
        this(r3, r5, str9, r7, str10, str11, r11, r12, str12, str13, r15, r4, r1072, d16, d17, d18, d19, d20, d21, r33, d22, d23, d24, d25, d26, d27, d28, r48, r49, ficheRefProp21, ficheRefProp22, ficheRefProp20, ficheRefProp24, ficheRefProp25, ficheRefProp26, ficheRefProp27, ficheRefProp28, ficheRefProp29, ficheStringProp18, ficheStringProp19, ficheDateProp5, ficheBooleanProp7, ficheBooleanProp8, ficheStringProp20, ficheStringProp21, ficheStringProp22, ficheStringProp17, ficheStringProp24, ficheStringProp25, ficheStringProp26, ficheStringProp27, ficheStringProp28, ficheStringProp29, ficheDiscountRefProp8, ficheDiscountRefProp9, ficheRefProp30, ficheRefProp31, str14, ficheRefProp32, ficheDiscountRefProp10, ficheDiscountRefProp11, salesFicheDiscountProps2, z4, r1712, ficheBooleanProp9, z6, z7, ficheStringProp30, ficheStringProp31, ficheStringProp32, ficheRefProp33, ficheDateProp6, ficheDateProp7, ficheDiscountRefProp12, ficheDiscountRefProp13, ficheDiscountRefProp14, d29, r1862, r1872, ficheRefProp34, ficheRefProp35, ficheRefProp36, ficheRefProp37, ficheRefProp38, ficheRefProp39, ficheBooleanProp10, ficheBooleanProp11, ficheRefProp40, ficheDateProp8, str15, ficheBooleanProp12, r89, str16, arrayList3, eDispatchAdditionalInfo2, arrayList2, CoroutineScope);
    }

    public  String getClCode() {
        return this.clCode;
    }

    public  void setClCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.clCode = str;
    }

    public  int getFicheRef() {
        return this.ficheRef;
    }

    public  void setFicheRef(int r1) {
        this.ficheRef = r1;
    }

    public  String getAndFicheNo() {
        return this.andFicheNo;
    }

    public  void setAndFicheNo(String str) {
        this.andFicheNo = str;
    }

    public  String getFicheNo() {
        return this.ficheNo;
    }

    public  void setFicheNo(String str) {
        this.ficheNo = str;
    }

    public  int getMSalesType() {
        return this.mSalesType;
    }

    public  void setMSalesType(int r1) {
        this.mSalesType = r1;
    }

    public  int getSalesStatus() {
        return this.salesStatus;
    }

    public  void setSalesStatus(int r1) {
        this.salesStatus = r1;
    }

    public  String getFicheCreateDate() {
        return this.ficheCreateDate;
    }

    public  void setFicheCreateDate(String str) {
        this.ficheCreateDate = str;
    }

    public  String getGDate() {
        return this.gDate;
    }

    public  void setGDate(String str) {
        this.gDate = str;
    }

    public  int getFicheCreateDateInt() {
        return this.ficheCreateDateInt;
    }

    public  void setFicheCreateDateInt(int r1) {
        this.ficheCreateDateInt = r1;
    }

    public  int getTransferCount() {
        return this.transferCount;
    }

    public  void setTransferCount(int r1) {
        this.transferCount = r1;
    }

    public  int getPrintCount() {
        return this.printCount;
    }

    public  void setPrintCount(int r1) {
        this.printCount = r1;
    }

    public  double getGrossTotal() {
        return this.grossTotal;
    }

    public  void setGrossTotal(double d2) {
        this.grossTotal = d2;
    }

    public  double getTotal() {
        return this.total;
    }

    public  void setTotal(double d2) {
        this.total = d2;
    }

    public  double getTotalNet() {
        return this.totalNet;
    }

    public  void setTotalNet(double d2) {
        this.totalNet = d2;
    }

    public  double getTotalVat() {
        return this.totalVat;
    }

    public  void setTotalVat(double d2) {
        this.totalVat = d2;
    }

    public  double getDiscTotal() {
        return this.discTotal;
    }

    public  void setDiscTotal(double d2) {
        this.discTotal = d2;
    }

    public  double getTotalExpenses() {
        return this.totalExpenses;
    }

    public  void setTotalExpenses(double d2) {
        this.totalExpenses = d2;
    }

    public  int getOrderType() {
        return this.orderType;
    }

    public  void setOrderType(int r1) {
        this.orderType = r1;
    }

    public  double getLatitude() {
        return this.latitude;
    }

    public  void setLatitude(double d2) {
        this.latitude = d2;
    }

    public  double getLongitude() {
        return this.longitude;
    }

    public  void setLongitude(double d2) {
        this.longitude = d2;
    }

    public  double getTotalNetVolume() {
        return this.totalNetVolume;
    }

    public  void setTotalNetVolume(double d2) {
        this.totalNetVolume = d2;
    }

    public double getTotalGrossVolume() {
        return this.totalGrossVolume;
    }

    public  void setTotalGrossVolume(double d2) {
        this.totalGrossVolume = d2;
    }

    public  double getTotalNetWeight() {
        return this.totalNetWeight;
    }

    public  void setTotalNetWeight(double d2) {
        this.totalNetWeight = d2;
    }

    public  double getTotalGrossWeight() {
        return this.totalGrossWeight;
    }

    public  void setTotalGrossWeight(double d2) {
        this.totalGrossWeight = d2;
    }

    public  double getTotalSku() {
        return this.totalSku;
    }

    public  void setTotalSku(double d2) {
        this.totalSku = d2;
    }

    public  int getCampaign() {
        return this.campaign;
    }

    public  void setCampaign(int r1) {
        this.campaign = r1;
    }

    public  int getSalesCondition() {
        return this.salesCondition;
    }

    public  void setSalesCondition(int r1) {
        this.salesCondition = r1;
    }

    public  FicheRefProp getBranch() {
        return this.branch;
    }

    public  void setBranch(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.branch = ficheRefProp;
    }

    public  FicheRefProp getDivision() {
        return this.division;
    }

    public  void setDivision(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.division = ficheRefProp;
    }

    public  FicheRefProp getFactory() {
        return this.factory;
    }

    public  void setFactory(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.factory = ficheRefProp;
    }

    public  FicheRefProp getWareHouse() {
        return this.wareHouse;
    }

    public  void setWareHouse(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.wareHouse = ficheRefProp;
    }

    public  FicheRefProp getReturnWareHouse() {
        return this.returnWareHouse;
    }

    public  void setReturnWareHouse(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.returnWareHouse = ficheRefProp;
    }

    public  FicheRefProp getSourceWareHouse() {
        return this.sourceWareHouse;
    }

    public  void setSourceWareHouse(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.sourceWareHouse = ficheRefProp;
    }

    public  FicheRefProp getSpeCode() {
        return this.speCode;
    }

    public  void setSpeCode(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.speCode = ficheRefProp;
    }

    public  FicheRefProp getWarrantyCode() {
        return this.warrantyCode;
    }

    public  void setWarrantyCode(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.warrantyCode = ficheRefProp;
    }

    public  FicheStringProp getDocumentNo() {
        return this.documentNo;
    }

    public  void setDocumentNo(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.documentNo = ficheStringProp;
    }

    public  FicheStringProp getDocumentTrackingNo() {
        return this.documentTrackingNo;
    }

    public  void setDocumentTrackingNo(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.documentTrackingNo = ficheStringProp;
    }

    public  FicheStringProp getCustomerOrderNo() {
        return this.customerOrderNo;
    }

    public  void setCustomerOrderNo(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.customerOrderNo = ficheStringProp;
    }

    public  FicheDateProp getDeliveryDate() {
        return this.deliveryDate;
    }

    public  void setDeliveryDate(FicheDateProp ficheDateProp) {
        Intrinsics.checkNotNullParameter(ficheDateProp, "<set-?>");
        this.deliveryDate = ficheDateProp;
    }

    public  FicheBooleanProp getPaymentOrder() {
        return this.paymentOrder;
    }

    public  void setPaymentOrder(FicheBooleanProp ficheBooleanProp) {
        Intrinsics.checkNotNullParameter(ficheBooleanProp, "<set-?>");
        this.paymentOrder = ficheBooleanProp;
    }

    public  FicheBooleanProp getConsignee() {
        return this.consignee;
    }

    public  void setConsignee(FicheBooleanProp ficheBooleanProp) {
        Intrinsics.checkNotNullParameter(ficheBooleanProp, "<set-?>");
        this.consignee = ficheBooleanProp;
    }

    public  FicheStringProp getExplanation1() {
        return this.explanation1;
    }

    public  void setExplanation1(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.explanation1 = ficheStringProp;
    }

    public  FicheStringProp getExplanation2() {
        return this.explanation2;
    }

    public  void setExplanation2(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.explanation2 = ficheStringProp;
    }

    public  FicheStringProp getExplanation3() {
        return this.explanation3;
    }

    public  void setExplanation3(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.explanation3 = ficheStringProp;
    }

    public  FicheStringProp getExplanation4() {
        return this.explanation4;
    }

    public  void setExplanation4(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.explanation4 = ficheStringProp;
    }

    public  FicheStringProp getExplanation5() {
        return this.explanation5;
    }

    public  void setExplanation5(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.explanation5 = ficheStringProp;
    }

    public  FicheStringProp getExplanation6() {
        return this.explanation6;
    }

    public  void setExplanation6(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.explanation6 = ficheStringProp;
    }

    public  FicheStringProp getExplanation7() {
        return this.explanation7;
    }

    public  void setExplanation7(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.explanation7 = ficheStringProp;
    }

    public  FicheStringProp getExplanation8() {
        return this.explanation8;
    }

    public  void setExplanation8(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.explanation8 = ficheStringProp;
    }

    public  FicheStringProp getExplanation9() {
        return this.explanation9;
    }

    public  void setExplanation9(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.explanation9 = ficheStringProp;
    }

    public  FicheStringProp getExplanation10() {
        return this.explanation10;
    }

    public  void setExplanation10(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.explanation10 = ficheStringProp;
    }

    public  FicheDiscountRefProp getShipAccount() {
        return this.shipAccount;
    }

    public  void setShipAccount(FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        this.shipAccount = ficheDiscountRefProp;
    }

    public  FicheDiscountRefProp getShipAddress() {
        return this.shipAddress;
    }

    public  void setShipAddress(FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        this.shipAddress = ficheDiscountRefProp;
    }

    public  FicheRefProp getShipAgent() {
        return this.shipAgent;
    }

    public  void setShipAgent(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.shipAgent = ficheRefProp;
    }

    public  FicheRefProp getShipType() {
        return this.shipType;
    }

    public  void setShipType(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.shipType = ficheRefProp;
    }

    public  String getInvoiceAddress() {
        return this.invoiceAddress;
    }

    public  void setInvoiceAddress(String str) {
        this.invoiceAddress = str;
    }

    public  FicheRefProp getTradeGroup() {
        return this.tradeGroup;
    }

    public  void setTradeGroup(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.tradeGroup = ficheRefProp;
    }

    public  FicheDiscountRefProp getPayPlan() {
        return this.payPlan;
    }

    public  void setPayPlan(FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        this.payPlan = ficheDiscountRefProp;
    }

    public  FicheDiscountRefProp getProjectCode() {
        return this.projectCode;
    }

    public  void setProjectCode(FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        this.projectCode = ficheDiscountRefProp;
    }

    public  SalesFicheDiscountProps getSalesFicheDiscountProps() {
        return this.salesFicheDiscountProps;
    }

    public  void setSalesFicheDiscountProps(SalesFicheDiscountProps salesFicheDiscountProps) {
        Intrinsics.checkNotNullParameter(salesFicheDiscountProps, "<set-?>");
        this.salesFicheDiscountProps = salesFicheDiscountProps;
    }

    public  boolean isNotUseGattribKdv() {
        return this.isNotUseGattribKdv;
    }

    public  void setNotUseGattribKdv(boolean z) {
        this.isNotUseGattribKdv = z;
    }

    public  int isEdit() {
        return this.isEdit;
    }

    public  void setEdit(int r1) {
        this.isEdit = r1;
    }

    public  FicheBooleanProp getIncludeVat() {
        return this.includeVat;
    }

    public  void setIncludeVat(FicheBooleanProp ficheBooleanProp) {
        Intrinsics.checkNotNullParameter(ficheBooleanProp, "<set-?>");
        this.includeVat = ficheBooleanProp;
    }

    public  boolean isSaleVatCanBeChange() {
        return this.isSaleVatCanBeChange;
    }

    public  void setSaleVatCanBeChange(boolean z) {
        this.isSaleVatCanBeChange = z;
    }

    public  boolean isSaleVatDefaultChecked() {
        return this.isSaleVatDefaultChecked;
    }

    public  void setSaleVatDefaultChecked(boolean z) {
        this.isSaleVatDefaultChecked = z;
    }

    public  FicheStringProp getEInvoiceSGKDocumentNo() {
        return this.eInvoiceSGKDocumentNo;
    }

    public  void setEInvoiceSGKDocumentNo(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.eInvoiceSGKDocumentNo = ficheStringProp;
    }

    public  FicheStringProp getTaxPayerCode() {
        return this.taxPayerCode;
    }

    public  void setTaxPayerCode(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.taxPayerCode = ficheStringProp;
    }

    public  FicheStringProp getTaxPayerName() {
        return this.taxPayerName;
    }

    public  void setTaxPayerName(FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        this.taxPayerName = ficheStringProp;
    }

    public  FicheRefProp getEInvoiceTypSgk() {
        return this.eInvoiceTypSgk;
    }

    public  void setEInvoiceTypSgk(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.eInvoiceTypSgk = ficheRefProp;
    }

    public  FicheDateProp getBeginDate() {
        return this.beginDate;
    }

    public  void setBeginDate(FicheDateProp ficheDateProp) {
        Intrinsics.checkNotNullParameter(ficheDateProp, "<set-?>");
        this.beginDate = ficheDateProp;
    }

    public  FicheDateProp getEndDate() {
        return this.endDate;
    }

    public  void setEndDate(FicheDateProp ficheDateProp) {
        Intrinsics.checkNotNullParameter(ficheDateProp, "<set-?>");
        this.endDate = ficheDateProp;
    }

    public  FicheDiscountRefProp getCabin() {
        return this.cabin;
    }

    public  void setCabin(FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        this.cabin = ficheDiscountRefProp;
    }

    public  FicheDiscountRefProp getFirstSpeCode() {
        return this.firstSpeCode;
    }

    public  void setFirstSpeCode(FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        this.firstSpeCode = ficheDiscountRefProp;
    }

    public  FicheDiscountRefProp getSecondSpeCode() {
        return this.secondSpeCode;
    }

    public  void setSecondSpeCode(FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        this.secondSpeCode = ficheDiscountRefProp;
    }

    public  double getTrRate() {
        return this.trRate;
    }

    public  void setTrRate(double d2) {
        this.trRate = d2;
    }

    public  int getTrCurr() {
        return this.trCurr;
    }

    public  void setTrCurr(int r1) {
        this.trCurr = r1;
    }

    public  int getOrderRef() {
        return this.orderRef;
    }

    public  void setOrderRef(int r1) {
        this.orderRef = r1;
    }

    public  FicheRefProp getTransferSourceBranch() {
        return this.transferSourceBranch;
    }

    public  void setTransferSourceBranch(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.transferSourceBranch = ficheRefProp;
    }

    public  FicheRefProp getTransferSourceDivision() {
        return this.transferSourceDivision;
    }

    public  void setTransferSourceDivision(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.transferSourceDivision = ficheRefProp;
    }

    public  FicheRefProp getTransferSourceFactory() {
        return this.transferSourceFactory;
    }

    public  void setTransferSourceFactory(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.transferSourceFactory = ficheRefProp;
    }

    public  FicheRefProp getTransferSourceWareHouse() {
        return this.transferSourceWareHouse;
    }

    public  void setTransferSourceWareHouse(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.transferSourceWareHouse = ficheRefProp;
    }

    public  FicheRefProp getTransferSourceCostGrp() {
        return this.transferSourceCostGrp;
    }

    public  void setTransferSourceCostGrp(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.transferSourceCostGrp = ficheRefProp;
    }

    public  FicheRefProp getEDocSerial() {
        return this.eDocSerial;
    }

    public  void setEDocSerial(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.eDocSerial = ficheRefProp;
    }

    public  FicheBooleanProp getEDespatch() {
        return this.eDespatch;
    }

    public  void setEDespatch(FicheBooleanProp ficheBooleanProp) {
        Intrinsics.checkNotNullParameter(ficheBooleanProp, "<set-?>");
        this.eDespatch = ficheBooleanProp;
    }

    public  FicheBooleanProp getInsteadOfEDespatch() {
        return this.insteadOfEDespatch;
    }

    public  void setInsteadOfEDespatch(FicheBooleanProp ficheBooleanProp) {
        Intrinsics.checkNotNullParameter(ficheBooleanProp, "<set-?>");
        this.insteadOfEDespatch = ficheBooleanProp;
    }

    public  FicheRefProp getErpInvoiceType() {
        return this.erpInvoiceType;
    }

    public  void setErpInvoiceType(FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        this.erpInvoiceType = ficheRefProp;
    }

    public  FicheDateProp getDueDate() {
        return this.dueDate;
    }

    public  void setDueDate(FicheDateProp ficheDateProp) {
        Intrinsics.checkNotNullParameter(ficheDateProp, "<set-?>");
        this.dueDate = ficheDateProp;
    }

    public  String getCampaingRefs() {
        return this.campaingRefs;
    }

    public  void setCampaingRefs(String str) {
        this.campaingRefs = str;
    }

    public  FicheBooleanProp getReserved() {
        return this.reserved;
    }

    public  void setReserved(FicheBooleanProp ficheBooleanProp) {
        Intrinsics.checkNotNullParameter(ficheBooleanProp, "<set-?>");
        this.reserved = ficheBooleanProp;
    }

    public  int getVisitInfoId() {
        return this.visitInfoId;
    }

    public  void setVisitInfoId(int r1) {
        this.visitInfoId = r1;
    }

    public  String getOrderFicheStatus() {
        return this.orderFicheStatus;
    }

    public  void setOrderFicheStatus(String str) {
        this.orderFicheStatus = str;
    }

    public  ArrayList<SalesDetail> getMSalesDetailList() {
        return this.mSalesDetailList;
    }

    public  void setMSalesDetailList(ArrayList<SalesDetail> arrayList) {
        this.mSalesDetailList = arrayList;
    }

    public  EDispatchAdditionalInfo getEDispatchAdditionalInfo() {
        return this.eDispatchAdditionalInfo;
    }

    public  void setEDispatchAdditionalInfo(EDispatchAdditionalInfo eDispatchAdditionalInfo) {
        this.eDispatchAdditionalInfo = eDispatchAdditionalInfo;
    }

    public  ArrayList<SalesDetail> getTempSalesDetailList() {
        return this.tempSalesDetailList;
    }

    public  void setTempSalesDetailList(ArrayList<SalesDetail> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.tempSalesDetailList = arrayList;
    }

    public  CoroutineScope getCustomScope() {
        return this.customScope;
    }

    public Sales(int r17, int r18, String clCode, int r20, String str, String str2, int r23, int r24, String str3, String str4, int r27, int r28, int r29, double d2, double d3, double d4, double d5, double d6, double d7, int r42, double d8, double d9, double d10, double d11, double d12, double d13, double d14, int r57, int r58, FicheRefProp branch, FicheRefProp division, FicheRefProp factory, FicheRefProp wareHouse, FicheRefProp returnWareHouse, FicheRefProp sourceWareHouse, FicheRefProp speCode, FicheRefProp warrantyCode, FicheStringProp documentNo, FicheStringProp documentTrackingNo, FicheStringProp customerOrderNo, FicheDateProp deliveryDate, FicheBooleanProp paymentOrder, FicheBooleanProp consignee, FicheStringProp explanation1, FicheStringProp explanation2, FicheStringProp explanation3, FicheStringProp explanation4, FicheStringProp explanation5, FicheStringProp explanation6, FicheStringProp explanation7, FicheStringProp explanation8, FicheStringProp explanation9, FicheStringProp explanation10, FicheDiscountRefProp shipAccount, FicheDiscountRefProp shipAddress, FicheRefProp shipAgent, FicheRefProp shipType, String str5, FicheRefProp tradeGroup, FicheDiscountRefProp payPlan, FicheDiscountRefProp projectCode, SalesFicheDiscountProps salesFicheDiscountProps, boolean z, int r93, FicheBooleanProp includeVat, boolean z2, boolean z3, FicheStringProp eInvoiceSGKDocumentNo, FicheStringProp taxPayerCode, FicheStringProp taxPayerName, FicheRefProp eInvoiceTypSgk, FicheDateProp beginDate, FicheDateProp endDate, FicheDiscountRefProp cabin, FicheDiscountRefProp firstSpeCode, FicheDiscountRefProp secondSpeCode, double d15, int r108, int r109, FicheRefProp transferSourceBranch, FicheRefProp transferSourceDivision, FicheRefProp transferSourceFactory, FicheRefProp transferSourceWareHouse, FicheRefProp transferSourceCostGrp, FicheRefProp eDocSerial, FicheBooleanProp eDespatch, FicheBooleanProp insteadOfEDespatch, FicheRefProp erpInvoiceType, FicheDateProp dueDate, String str6, FicheBooleanProp reserved, int r122, String str7, ArrayList<SalesDetail> arrayList, EDispatchAdditionalInfo eDispatchAdditionalInfo, ArrayList<SalesDetail> tempSalesDetailList, CoroutineScope customScope) {
        Intrinsics.checkNotNullParameter(clCode, "clCode");
        Intrinsics.checkNotNullParameter(branch, "branch");
        Intrinsics.checkNotNullParameter(division, "division");
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(wareHouse, "wareHouse");
        Intrinsics.checkNotNullParameter(returnWareHouse, "returnWareHouse");
        Intrinsics.checkNotNullParameter(sourceWareHouse, "sourceWareHouse");
        Intrinsics.checkNotNullParameter(speCode, "speCode");
        Intrinsics.checkNotNullParameter(warrantyCode, "warrantyCode");
        Intrinsics.checkNotNullParameter(documentNo, "documentNo");
        Intrinsics.checkNotNullParameter(documentTrackingNo, "documentTrackingNo");
        Intrinsics.checkNotNullParameter(customerOrderNo, "customerOrderNo");
        Intrinsics.checkNotNullParameter(deliveryDate, "deliveryDate");
        Intrinsics.checkNotNullParameter(paymentOrder, "paymentOrder");
        Intrinsics.checkNotNullParameter(consignee, "consignee");
        Intrinsics.checkNotNullParameter(explanation1, "explanation1");
        Intrinsics.checkNotNullParameter(explanation2, "explanation2");
        Intrinsics.checkNotNullParameter(explanation3, "explanation3");
        Intrinsics.checkNotNullParameter(explanation4, "explanation4");
        Intrinsics.checkNotNullParameter(explanation5, "explanation5");
        Intrinsics.checkNotNullParameter(explanation6, "explanation6");
        Intrinsics.checkNotNullParameter(explanation7, "explanation7");
        Intrinsics.checkNotNullParameter(explanation8, "explanation8");
        Intrinsics.checkNotNullParameter(explanation9, "explanation9");
        Intrinsics.checkNotNullParameter(explanation10, "explanation10");
        Intrinsics.checkNotNullParameter(shipAccount, "shipAccount");
        Intrinsics.checkNotNullParameter(shipAddress, "shipAddress");
        Intrinsics.checkNotNullParameter(shipAgent, "shipAgent");
        Intrinsics.checkNotNullParameter(shipType, "shipType");
        Intrinsics.checkNotNullParameter(tradeGroup, "tradeGroup");
        Intrinsics.checkNotNullParameter(payPlan, "payPlan");
        Intrinsics.checkNotNullParameter(projectCode, "projectCode");
        Intrinsics.checkNotNullParameter(salesFicheDiscountProps, "salesFicheDiscountProps");
        Intrinsics.checkNotNullParameter(includeVat, "includeVat");
        Intrinsics.checkNotNullParameter(eInvoiceSGKDocumentNo, "eInvoiceSGKDocumentNo");
        Intrinsics.checkNotNullParameter(taxPayerCode, "taxPayerCode");
        Intrinsics.checkNotNullParameter(taxPayerName, "taxPayerName");
        Intrinsics.checkNotNullParameter(eInvoiceTypSgk, "eInvoiceTypSgk");
        Intrinsics.checkNotNullParameter(beginDate, "beginDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(cabin, "cabin");
        Intrinsics.checkNotNullParameter(firstSpeCode, "firstSpeCode");
        Intrinsics.checkNotNullParameter(secondSpeCode, "secondSpeCode");
        Intrinsics.checkNotNullParameter(transferSourceBranch, "transferSourceBranch");
        Intrinsics.checkNotNullParameter(transferSourceDivision, "transferSourceDivision");
        Intrinsics.checkNotNullParameter(transferSourceFactory, "transferSourceFactory");
        Intrinsics.checkNotNullParameter(transferSourceWareHouse, "transferSourceWareHouse");
        Intrinsics.checkNotNullParameter(transferSourceCostGrp, "transferSourceCostGrp");
        Intrinsics.checkNotNullParameter(eDocSerial, "eDocSerial");
        Intrinsics.checkNotNullParameter(eDespatch, "eDespatch");
        Intrinsics.checkNotNullParameter(insteadOfEDespatch, "insteadOfEDespatch");
        Intrinsics.checkNotNullParameter(erpInvoiceType, "erpInvoiceType");
        Intrinsics.checkNotNullParameter(dueDate, "dueDate");
        Intrinsics.checkNotNullParameter(reserved, "reserved");
        Intrinsics.checkNotNullParameter(tempSalesDetailList, "tempSalesDetailList");
        Intrinsics.checkNotNullParameter(customScope, "customScope");
        this.logicalRef = r17;
        this.clRef = r18;
        this.clCode = clCode;
        this.ficheRef = r20;
        this.andFicheNo = str;
        this.ficheNo = str2;
        this.mSalesType = r23;
        this.salesStatus = r24;
        this.ficheCreateDate = str3;
        this.gDate = str4;
        this.ficheCreateDateInt = r27;
        this.transferCount = r28;
        this.printCount = r29;
        this.grossTotal = d2;
        this.total = d3;
        this.totalNet = d4;
        this.totalVat = d5;
        this.discTotal = d6;
        this.totalExpenses = d7;
        this.orderType = r42;
        this.latitude = d8;
        this.longitude = d9;
        this.totalNetVolume = d10;
        this.totalGrossVolume = d11;
        this.totalNetWeight = d12;
        this.totalGrossWeight = d13;
        this.totalSku = d14;
        this.campaign = r57;
        this.salesCondition = r58;
        this.branch = branch;
        this.division = division;
        this.factory = factory;
        this.wareHouse = wareHouse;
        this.returnWareHouse = returnWareHouse;
        this.sourceWareHouse = sourceWareHouse;
        this.speCode = speCode;
        this.warrantyCode = warrantyCode;
        this.documentNo = documentNo;
        this.documentTrackingNo = documentTrackingNo;
        this.customerOrderNo = customerOrderNo;
        this.deliveryDate = deliveryDate;
        this.paymentOrder = paymentOrder;
        this.consignee = consignee;
        this.explanation1 = explanation1;
        this.explanation2 = explanation2;
        this.explanation3 = explanation3;
        this.explanation4 = explanation4;
        this.explanation5 = explanation5;
        this.explanation6 = explanation6;
        this.explanation7 = explanation7;
        this.explanation8 = explanation8;
        this.explanation9 = explanation9;
        this.explanation10 = explanation10;
        this.shipAccount = shipAccount;
        this.shipAddress = shipAddress;
        this.shipAgent = shipAgent;
        this.shipType = shipType;
        this.invoiceAddress = str5;
        this.tradeGroup = tradeGroup;
        this.payPlan = payPlan;
        this.projectCode = projectCode;
        this.salesFicheDiscountProps = salesFicheDiscountProps;
        this.isNotUseGattribKdv = z;
        this.isEdit = r93;
        this.includeVat = includeVat;
        this.isSaleVatCanBeChange = z2;
        this.isSaleVatDefaultChecked = z3;
        this.eInvoiceSGKDocumentNo = eInvoiceSGKDocumentNo;
        this.taxPayerCode = taxPayerCode;
        this.taxPayerName = taxPayerName;
        this.eInvoiceTypSgk = eInvoiceTypSgk;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.cabin = cabin;
        this.firstSpeCode = firstSpeCode;
        this.secondSpeCode = secondSpeCode;
        this.trRate = d15;
        this.trCurr = r108;
        this.orderRef = r109;
        this.transferSourceBranch = transferSourceBranch;
        this.transferSourceDivision = transferSourceDivision;
        this.transferSourceFactory = transferSourceFactory;
        this.transferSourceWareHouse = transferSourceWareHouse;
        this.transferSourceCostGrp = transferSourceCostGrp;
        this.eDocSerial = eDocSerial;
        this.eDespatch = eDespatch;
        this.insteadOfEDespatch = insteadOfEDespatch;
        this.erpInvoiceType = erpInvoiceType;
        this.dueDate = dueDate;
        this.campaingRefs = str6;
        this.reserved = reserved;
        this.visitInfoId = r122;
        this.orderFicheStatus = str7;
        this.mSalesDetailList = arrayList;
        this.eDispatchAdditionalInfo = eDispatchAdditionalInfo;
        this.tempSalesDetailList = tempSalesDetailList;
        this.customScope = customScope;
        BaseSalesRepository baseSalesRepository = new BaseSalesRepository();
        this.repository = baseSalesRepository;
        this.viewModel = new BaseSalesViewModel(baseSalesRepository);
    }

    public Sales(int r118) {
        this(0, 0, null, 0, null, null, 0, 0, null, null, 0, 0, 0, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, 0, null, false, false, null, null, null, null, null, null, null, null, null, 0.0d, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, null, -1, -1, -1, 1, null);
        this.mSalesType = r118;
    }

    public  int getDiscountLength() {
        return this.salesFicheDiscountProps.getDiscountCount();
    }

    public  FicheDiscountProp getDiscountRatio(int r1) {
        FicheDiscountProp discountRatio = this.salesFicheDiscountProps.getDiscountRatio(r1);
        return discountRatio == null ? new FicheDiscountRefProp() : discountRatio;
    }

    public  FicheDiscountProp getDiscountTotal(int r1) {
        FicheDiscountProp discountTotal = this.salesFicheDiscountProps.getDiscountTotal(r1);
        return discountTotal == null ? new FicheDiscountRefProp() : discountTotal;
    }

    public  FicheDiscountRefProp getDiscountCard(int r1) {
        FicheDiscountRefProp discountCard = this.salesFicheDiscountProps.getDiscountCard(r1);
        return discountCard == null ? new FicheDiscountRefProp() : discountCard;
    }

    public  String getDiscountGuid(int r7) {
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

    public  void setDiscountGuid(int r5, String str) {
        if (!TextUtils.isEmpty(getDiscountCard(r5).getCode())) {
            getDiscountCard(r5).setGuid(str);
        } else if (getDiscountRatio(r5).getDefinitionDouble() > 0.0d) {
            getDiscountRatio(r5).setGuid(str);
        } else if (getDiscountTotal(r5).getDefinitionDouble() > 0.0d) {
            getDiscountTotal(r5).setGuid(str);
        }
    }

    public  String getDiscountCampaign(int r5) {
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

    public  void setDiscountCampaign(int r5, String str) {
        if (!TextUtils.isEmpty(getDiscountCard(r5).getCode())) {
            getDiscountCard(r5).setCampaignCode(str);
        } else if (getDiscountRatio(r5).getDefinitionDouble() > 0.0d) {
            getDiscountRatio(r5).setCampaignCode(str);
        } else if (getDiscountTotal(r5).getDefinitionDouble() > 0.0d) {
            getDiscountTotal(r5).setCampaignCode(str);
        }
    }

    public  SalesType getmSalesType() {
        return SalesType.values()[this.mSalesType];
    }

    public  FicheType getFicheType(SalesType salesType) {
        FicheType ficheType = FicheType.ALL;
        Intrinsics.checkNotNull(salesType);
        if (SalesUtils.isSalesTypeOrder(salesType)) {
            return FicheType.ORDER;
        }
        if (SalesUtils.isSalesTypeDemand(salesType)) {
            return FicheType.DEMAND;
        }
        if (SalesUtils.isSalesTypeInvoiceOrReturnInvoice(salesType)) {
            return FicheType.INVOICE;
        }
        if (SalesUtils.isSalesTypeDispatchOrReturnDispatch(salesType)) {
            return FicheType.DISPATCH;
        }
        if (SalesUtils.isSalesTypeOneToOne(salesType)) {
            return FicheType.ONE_TO_ONE;
        }
        if (SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(salesType)) {
            return FicheType.RETAILINVOICE;
        }
        return SalesUtils.isSalesTypeWhTransfer(salesType) ? FicheType.WHTRANSFER : ficheType;
    }

    public  FicheType getFicheType() {
        return getFicheType(getmSalesType());
    }

    public  EmailTemplateType getEmailTemplateType(SalesType salesType) {
        EmailTemplateType emailTemplateType = EmailTemplateType.Unknown;
        Intrinsics.checkNotNull(salesType);
        if (SalesUtils.isSalesTypeOrder(salesType)) {
            return EmailTemplateType.Order;
        }
        if (SalesUtils.isSalesTypeInvoiceOrReturnInvoiceOrRetailInvOrRetailReturnInv(salesType)) {
            return EmailTemplateType.Invoice;
        }
        return SalesUtils.isSalesTypeDispatchOrReturnDispatch(salesType) ? EmailTemplateType.Dispatch : emailTemplateType;
    }

    public  EmailTemplateType getEmailTemplateType() {
        return getEmailTemplateType(getmSalesType());
    }

    public  boolean addSalesDetailItems(SalesDetail salesDetail) {
        if (salesDetail == null) {
            return false;
        }
        try {
            try {
                ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
                Intrinsics.checkNotNull(arrayList);
                return arrayList.add(salesDetail);
            } catch (Exception e2) {
                Log.e("AA", "removeSalesDetailItem: ", e2);
                return false;
            }
        } catch (Throwable unused) {
            return false;
        }
    }

    public  int addSalesDetailItems(ArrayList<SalesDetail> arrayList, boolean z) {
        int r1;
        if (arrayList == null) {
            return 0;
        }
        try {
            try {
                if (z) {
                    r1 = addSalesDetailItemsLineIntegration(arrayList);
                } else {
                    r1 = addSalesDetailItems(arrayList);
                }
                return r1;
            } catch (Exception e2) {
                e2.printStackTrace();
                return 0;
            }
        } catch (Throwable unused) {
            return 0;
        }
    }

    public  int addAlternativePromotionDetailItems(SalesDetail mainPromotionItemDetail, int r3, ArrayList<SalesDetail> arrayList) {
        Intrinsics.checkNotNullParameter(mainPromotionItemDetail, "mainPromotionItemDetail");
        if (arrayList == null) {
            return 0;
        }
        try {
            try {
                ArrayList<SalesDetail> arrayList2 = this.mSalesDetailList;
                Intrinsics.checkNotNull(arrayList2);
                arrayList2.remove(mainPromotionItemDetail);
                ArrayList<SalesDetail> arrayList3 = this.mSalesDetailList;
                Intrinsics.checkNotNull(arrayList3);
                arrayList3.addAll(r3, arrayList);
                return arrayList.size();
            } catch (Exception e2) {
                Log.e("AA", "removeSalesDetailItem: ", e2);
                return arrayList.size();
            }
        } catch (Throwable unused) {
            return arrayList.size();
        }
    }

    public  int addSalesDetailItems(ArrayList<SalesDetail> mSalesDetails) {
        Intrinsics.checkNotNullParameter(mSalesDetails, "mSalesDetails");
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList);
        arrayList.addAll(mSalesDetails);
        return mSalesDetails.size();
    }

    public  int addSalesDetailItemsLineIntegration(ArrayList<SalesDetail> salesDetailList) {
        Object next;
        Intrinsics.checkNotNullParameter(salesDetailList, "salesDetailList");
        int r0 = 0;
        try {
            try {
                Iterator<SalesDetail> it = salesDetailList.iterator();
                while (it.hasNext()) {
                    SalesDetail next2 = it.next();
                    ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
                    Intrinsics.checkNotNull(arrayList);
                    Iterator<T> it2 = arrayList.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it2.next();
                        if (Intrinsics.areEqual(((SalesDetail) next).getCode(), next2.getCode())) {
                            break;
                        }
                    }
                    SalesDetail salesDetail = (SalesDetail) next;
                    if (salesDetail != null) {
                        salesDetail.setBarcodeCount(salesDetail.getBarcodeCount() + 1);
                    } else {
                        next2.setBarcodeCount(1);
                    }
                    int r2 = searchSalesDetail(next2.getItemRef(), next2.getPromotion().isSelect(), next2.getGlobalLineType(), next2.getCampaignCode(), next2.getVariant(), next2.isFoundByStoredProcedure(), next2.isProduct());
                    if (r2 != -1) {
                        ArrayList<SalesDetail> arrayList2 = this.mSalesDetailList;
                        SalesDetail salesDetail2 = arrayList2 != null ? arrayList2.get(r2) : null;
                        Intrinsics.checkNotNull(salesDetail2);
                        if (salesDetail2.getItemRef() == next2.getItemRef()) {
                            Intrinsics.checkNotNull(next2);
                            addCurrentSalesDetailtoTemp(salesDetail2, next2);
                        } else {
                            ArrayList<SalesDetail> arrayList3 = this.mSalesDetailList;
                            Intrinsics.checkNotNull(arrayList3);
                            arrayList3.add(next2);
                        }
                    } else {
                        ArrayList<SalesDetail> arrayList4 = this.mSalesDetailList;
                        Intrinsics.checkNotNull(arrayList4);
                        arrayList4.add(next2);
                    }
                    r0++;
                }
                return r0;
            } catch (Exception e2) {
                e2.printStackTrace();
                return r0;
            }
        } catch (Throwable unused) {
            return r0;
        }
    }
    private  void addCurrentSalesDetailtoTemp(SalesDetail salesDetail, SalesDetail salesDetail2) {
        salesDetail.setActualStock(salesDetail2.getActualStock());
        double definitionDouble = salesDetail.getDiscountRatio(0).getDefinitionDouble() + salesDetail2.getDiscountRatio(0).getDefinitionDouble();
        double definitionDouble2 = salesDetail.getDiscountTotal(0).getDefinitionDouble() + salesDetail2.getDiscountTotal(0).getDefinitionDouble();
        if (definitionDouble != 0.0d) {
            salesDetail.getDiscountRatio(0);
            FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(definitionDouble)));
        } else {
            salesDetail.getDiscountTotal(0);
            FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(definitionDouble2)));
        }
        if (this.viewModel.erpType() != ErpType.NETSIS) {
            if (salesDetail.getUnit().getLogicalRef() == salesDetail2.getUnit().getLogicalRef()) {
                FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(salesDetail.getAmount().getDefinitionDouble() + salesDetail2.getAmount().getDefinitionDouble())));
            } else {
                FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(((salesDetail2.getAmount().getDefinitionDouble() * (salesDetail2.getConvFact2() / salesDetail2.getConvFact1())) / salesDetail.getConvFact2()) + salesDetail.getAmount().getDefinitionDouble())));
            }
        } else {
            if (Intrinsics.areEqual(salesDetail.getUnit().getCode(), salesDetail2.getUnit().getCode())) {
                FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(salesDetail.getAmount().getDefinitionDouble() + salesDetail2.getAmount().getDefinitionDouble())));
                FicheStringProp surplusAmount = salesDetail.getSurplusAmount();
                Intrinsics.checkNotNull(surplusAmount);
                double definitionDouble3 = surplusAmount.getDefinitionDouble();
                FicheStringProp surplusAmount2 = salesDetail2.getSurplusAmount();
                Intrinsics.checkNotNull(surplusAmount2);
                double definitionDouble4 = definitionDouble3 + surplusAmount2.getDefinitionDouble();
                FicheStringProp surplusAmount3 = salesDetail.getSurplusAmount();
                Intrinsics.checkNotNull(surplusAmount3);
                FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(definitionDouble4)));
            } else {
                FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(((salesDetail2.getAmount().getDefinitionDouble() * (salesDetail2.getConvFact2() / salesDetail2.getConvFact1())) / (salesDetail.getConvFact2() / salesDetail.getConvFact1())) + salesDetail.getAmount().getDefinitionDouble())));
                FicheStringProp surplusAmount4 = salesDetail2.getSurplusAmount();
                Intrinsics.checkNotNull(surplusAmount4);
                double definitionDouble5 = (surplusAmount4.getDefinitionDouble() * (salesDetail2.getConvFact2() / salesDetail2.getConvFact1())) / (salesDetail.getConvFact2() / salesDetail.getConvFact1());
                FicheStringProp surplusAmount5 = salesDetail.getSurplusAmount();
                Intrinsics.checkNotNull(surplusAmount5);
                double definitionDouble6 = definitionDouble5 + surplusAmount5.getDefinitionDouble();
                FicheStringProp surplusAmount6 = salesDetail.getSurplusAmount();
                Intrinsics.checkNotNull(surplusAmount6);
                FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(definitionDouble6)));
            }
            if (Intrinsics.areEqual(salesDetail.getSecondUnit().getCode(), salesDetail2.getSecondUnit().getCode())) {
                FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(salesDetail.getSecondAmount().getDefinitionDouble() + salesDetail2.getSecondAmount().getDefinitionDouble())));
            } else {
                double[] unitConvfact = this.viewModel.getSqlHelper().getUnitConvfact(salesDetail2.getSecondUnit().getCode(), salesDetail2.getCode());
                double[] unitConvfact2 = this.viewModel.getSqlHelper().getUnitConvfact(salesDetail.getSecondUnit().getCode(), salesDetail.getCode());
                FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(((salesDetail2.getSecondAmount().getDefinitionDouble() * (unitConvfact[1] / unitConvfact[0])) / (unitConvfact2[1] / unitConvfact2[0])) + salesDetail.getSecondAmount().getDefinitionDouble())));
            }
        }
        if (salesDetail.getTrackType() == TrackType.SERIAL.getType()) {
            Iterator<Serilot> it = salesDetail2.getSalesSerialLots().iterator();
            while (it.hasNext()) {
                Serilot next = it.next();
                Intrinsics.checkNotNull(next);
                if (isSerialAlreadyAdded(next, salesDetail.getSalesSerialLots()) == null) {
                    salesDetail.getSalesSerialLots().add(next);
                } else {
                    FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(salesDetail.getAmount().getDefinitionDouble() - calculateAmountForSerialLotItem(salesDetail, salesDetail2, next))));
                }
            }
            salesDetail.serialLotCodeList = StringUtils.getSerialLotCode(salesDetail.getSalesSerialLots(), salesDetail.getTrackType());
        } else if (salesDetail.getTrackType() == TrackType.LOT.getType()) {
            Iterator<Serilot> it2 = salesDetail2.getSalesSerialLots().iterator();
            while (it2.hasNext()) {
                Serilot next2 = it2.next();
                Intrinsics.checkNotNull(next2);
                Serilot serilotIsSerialAlreadyAdded = isSerialAlreadyAdded(next2, salesDetail.getSalesSerialLots());
                if (serilotIsSerialAlreadyAdded == null) {
                    salesDetail.getSalesSerialLots().add(next2);
                } else {
                    serilotIsSerialAlreadyAdded.amount += calculateAmountForSerialLotItem(salesDetail, salesDetail2, next2);
                }
            }
            salesDetail.serialLotCodeList = StringUtils.getSerialLotCode(salesDetail.getSalesSerialLots(), salesDetail.getTrackType());
        } else if (salesDetail.getTrackType() == TrackType.SERIAL_GROUP.getType()) {
            Iterator<Serilot> it3 = salesDetail2.getSalesSerialLots().iterator();
            while (it3.hasNext()) {
                Serilot next3 = it3.next();
                Intrinsics.checkNotNull(next3);
                Serilot serilotIsSerialGroupAlreadyAdded = isSerialGroupAlreadyAdded(next3, salesDetail.getSalesSerialLots());
                if (serilotIsSerialGroupAlreadyAdded == null) {
                    salesDetail.getSalesSerialLots().add(next3);
                } else {
                    serilotIsSerialGroupAlreadyAdded.amount += calculateAmountForSerialLotItem(salesDetail, salesDetail2, next3);
                }
            }
            salesDetail.serialLotCodeList = StringUtils.getSerialLotCode(salesDetail.getSalesSerialLots(), salesDetail.getTrackType());
        }
        if (salesDetail2.getSearchBarcodes() != null) {
            ArrayList<String> searchBarcodes = salesDetail2.getSearchBarcodes();
            Intrinsics.checkNotNull(searchBarcodes);
            if (searchBarcodes.isEmpty()) {
                return;
            }
            if (salesDetail.getSearchBarcodes() == null) {
                salesDetail.setSearchBarcodes(new ArrayList<>());
            }
            ArrayList<String> searchBarcodes2 = salesDetail.getSearchBarcodes();
            Intrinsics.checkNotNull(searchBarcodes2);
            ArrayList<String> searchBarcodes3 = salesDetail2.getSearchBarcodes();
            Intrinsics.checkNotNull(searchBarcodes3);
            searchBarcodes2.addAll(searchBarcodes3);
        }
    }

    private  double calculateAmountForSerialLotItem(SalesDetail salesDetail, SalesDetail salesDetail2, Serilot serilot) {
        if (salesDetail.getUnit().getLogicalRef() == salesDetail2.getUnit().getLogicalRef()) {
            return serilot.amount;
        }
        return (salesDetail2.getAmount().getDefinitionDouble() * (salesDetail2.getConvFact2() / salesDetail2.getConvFact1())) / salesDetail.getConvFact2();
    }

    private  Serilot isSerialAlreadyAdded(Serilot serilot, ArrayList<Serilot> arrayList) {
        Iterator<Serilot> it = arrayList.iterator();
        while (it.hasNext()) {
            Serilot next = it.next();
            if (next.logicalRef == serilot.logicalRef && Intrinsics.areEqual(next.code, serilot.code)) {
                return next;
            }
        }
        return null;
    }

    private  Serilot isSerialGroupAlreadyAdded(Serilot serilot, ArrayList<Serilot> arrayList) {
        Iterator<Serilot> it = arrayList.iterator();
        while (it.hasNext()) {
            Serilot next = it.next();
            if (next.logicalRef == serilot.logicalRef && Intrinsics.areEqual(next.code, serilot.code) && Intrinsics.areEqual(next.grpBegCode, serilot.grpBegCode)) {
                return next;
            }
        }
        return null;
    }

    private  int searchSalesDetail(int r8, boolean z, int r10, String str, FicheDiscountRefProp ficheDiscountRefProp, boolean z2, boolean z3) {
        try {
            try {
                ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
                Intrinsics.checkNotNull(arrayList);
                int size = arrayList.size();
                for (int r2 = 0; r2 < size; r2++) {
                    ArrayList<SalesDetail> arrayList2 = this.mSalesDetailList;
                    Intrinsics.checkNotNull(arrayList2);
                    SalesDetail salesDetail = arrayList2.get(r2);
                    Intrinsics.checkNotNullExpressionValue(salesDetail, "get(...)");
                    SalesDetail salesDetail2 = salesDetail;
                    if (salesDetail2.getItemRef() == r8 && salesDetail2.isProduct() == z3 && salesDetail2.getPromotion().isSelect() == z && salesDetail2.getGlobalLineType() == r10 && isSameCampaignPromotionItemDetail(z, str, salesDetail2.getCampaignCode())) {
                        ErpType erpType = this.viewModel.erpType();
                        ErpType erpType2 = ErpType.NETSIS;
                        if (erpType != erpType2 || !Intrinsics.areEqual(salesDetail2.getVariant().getCode(), ficheDiscountRefProp.getCode())) {
                            if (this.viewModel.erpType() != erpType2 && salesDetail2.getVariant().getLogicalRef() == ficheDiscountRefProp.getLogicalRef()) {
                                if (salesDetail2.isFoundByStoredProcedure() == z2) {
                                    return r2;
                                }
                            }
                        }
                    }
                }
                return -1;
            } catch (Exception e2) {
                e2.printStackTrace();
                return -1;
            }
        } catch (Throwable unused) {
            return -1;
        }
    }

    private  boolean isSameCampaignPromotionItemDetail(boolean z, String str, String str2) {
        if (!z) {
            return true;
        }
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return true;
        }
        return !TextUtils.isEmpty(str) && StringsKt.equals(str, str2, true);
    }

    public  int getCampaignWithControl() {
        if (hasCampaignApplied()) {
            return 0;
        }
        return this.campaign;
    }

    public  void calculateSalesTotal() {
        Disc disc;
        Iterator<SalesDetail> it;
        double dRoundFive;
        Disc disc2 = new Disc();
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList);
        Iterator<SalesDetail> it2 = arrayList.iterator();
        double d2 = 0.0d;
        double productLineNet = 0.0d;
        while (it2.hasNext()) {
            SalesDetail next = it2.next();
            next.calculateFiche(this.isNotUseGattribKdv);
            if (!next.getPromotion().isSelect()) {
                productLineNet += next.getProductLineNet();
            }
        }
        disc2.productTotal = productLineNet;
        ArrayList<SalesDetail> arrayList2 = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList2);
        Iterator<SalesDetail> it3 = arrayList2.iterator();
        double dRoundFive2 = 0.0d;
        double d3 = 0.0d;
        double dRoundFive3 = 0.0d;
        double dRoundFive4 = 0.0d;
        while (it3.hasNext()) {
            SalesDetail next2 = it3.next();
            disc2.vat = CalculateUtils.roundFive(next2.getProductVatAmnt());
            disc2.lineNet = CalculateUtils.roundFive(next2.getProductLineNet());
            disc2.grossTotal = CalculateUtils.roundFive(next2.getProductGrossTotal());
            disc2.vatMatrah = CalculateUtils.roundFive(next2.getProductVatMatrah());
            int r14 = next2.lineType;
            if (r14 == 0 || r14 == 4 || r14 == 6) {
                disc2 = CalculateUtils.calculateDiscountGeneral(disc2, this.salesFicheDiscountProps.getCustomerDiscount().getDefinitionDouble(), d2);
                next2.setProductDiscountTotal(disc2);
                int discountLength = getDiscountLength();
                int r15 = 0;
                while (r15 < discountLength) {
                    disc2 = CalculateUtils.calculateDiscountGeneral(disc2, getDiscountRatio(r15).getDefinitionDouble(), getDiscountTotal(r15).getDefinitionDouble());
                    next2.setProductDiscountTotal(disc2);
                    r15++;
                    d3 = d3;
                }
            }
            double d4 = d3;
            dRoundFive3 += CalculateUtils.roundFive(next2.getProductGrossTotal());
            dRoundFive4 += CalculateUtils.roundFive(next2.getProductDiscountTotal());
            if (next2.lineType != 1) {
                dRoundFive2 += CalculateUtils.roundFive(next2.getProductLineNet());
                List<Item> tableForItemFromSqlHelper = null;
                try {
                    tableForItemFromSqlHelper = this.viewModel.getTableForItemFromSqlHelper(Item.class, "LOGICALREF=?", new String[]{String.valueOf(next2.getItemRef())});
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Intrinsics.checkNotNull(tableForItemFromSqlHelper);
                if (tableForItemFromSqlHelper.size() == 1) {
                    Item item = tableForItemFromSqlHelper.get(0);
                    if (item.candeduct == 1) {
                        disc = disc2;
                        it = it3;
                        dRoundFive = CalculateUtils.roundFive(((StringUtils.convertStringToDouble(item.getVat()) - (StringUtils.convertStringToDouble(item.getVat()) * (item.salesDeductPart1 / item.salesDeductPart2))) * dRoundFive2) / 100);
                    } else {
                        disc = disc2;
                        it = it3;
                        dRoundFive = CalculateUtils.roundFive(next2.getProductVatAmnt());
                    }
                    d3 = d4 + dRoundFive;
                    it3 = it;
                    disc2 = disc;
                } else {
                    d3 = d4;
                }
            }
            d2 = 0.0d;
        }
        double d5 = d3;
        double d6 = dRoundFive2 + this.totalExpenses;
        double dRoundFive5 = CalculateUtils.roundFive(d6 + d5);
        this.total = CalculateUtils.roundFive(d6);
        this.totalVat = CalculateUtils.roundFive(d5);
        this.totalNet = CalculateUtils.roundFive(dRoundFive5);
        this.grossTotal = CalculateUtils.roundFive(dRoundFive3);
        this.discTotal = CalculateUtils.roundFive(dRoundFive4);
    }

    public  double getProductTotal() {
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList);
        Iterator<SalesDetail> it = arrayList.iterator();
        double productLineNet = 0.0d;
        while (it.hasNext()) {
            productLineNet += it.next().getProductLineNet();
        }
        return productLineNet;
    }

    public  boolean salesDetailSizeControl() {
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList);
        return arrayList.size() > 0;
    }

    public  String salesHeaderControl(ErpType erpType) {
        Intrinsics.checkNotNullParameter(erpType, "erpType");
        ErpType erpType2 = ErpType.NETSIS;
        String string = "";
        if (erpType != erpType2 && !branchControl()) {
            StringBuilder sb = new StringBuilder();
            Context context = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context);
            Context context2 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context2);
            sb.append(context.getString(R.string.exp_41_sales_header_not_select, context2.getString(R.string.str_department)));
            string = sb.toString();
        }
        if (erpType != erpType2 && !divisionControl()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(string);
            Context context3 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context3);
            Context context4 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context4);
            sb2.append(context3.getString(R.string.exp_41_sales_header_not_select, context4.getString(R.string.str_section)));
            string = sb2.toString();
        }
        if (erpType != erpType2 && !factoryControl()) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(string);
            Context context5 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context5);
            Context context6 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context6);
            sb3.append(context5.getString(R.string.exp_41_sales_header_not_select, context6.getString(R.string.str_factory)));
            string = sb3.toString();
        }
        if (!wareHouseControl()) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(string);
            Context context7 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context7);
            Context context8 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context8);
            sb4.append(context7.getString(R.string.exp_41_sales_header_not_select, context8.getString(R.string.str_warehouse)));
            string = sb4.toString();
        }
        if (!returnWareHouseControl()) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(string);
            Context context9 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context9);
            Context context10 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context10);
            sb5.append(context9.getString(R.string.exp_41_sales_header_not_select, context10.getString(R.string.str_return_ware_house)));
            string = sb5.toString();
        }
        if (erpType != erpType2 && !sourceWareHouseControl()) {
            StringBuilder sb6 = new StringBuilder();
            sb6.append(string);
            Context context11 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context11);
            Context context12 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context12);
            sb6.append(context11.getString(R.string.exp_41_sales_header_not_select, context12.getString(R.string.str_source_ware_house)));
            string = sb6.toString();
        }
        if (isCustomerHaveCabinAndNotSelected()) {
            StringBuilder sb7 = new StringBuilder();
            sb7.append(string);
            Context context13 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context13);
            Context context14 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context14);
            sb7.append(context13.getString(R.string.exp_41_sales_header_not_select, context14.getString(R.string.str_cabin)));
            string = sb7.toString();
        }
        if (erpType == erpType2 || !isHeaderSpeCodeNotSelected()) {
            return string;
        }
        StringBuilder sb8 = new StringBuilder();
        sb8.append(string);
        Context context15 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context15);
        Context context16 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context16);
        sb8.append(context15.getString(R.string.exp_41_sales_header_not_select, context16.getString(R.string.str_spe_code)));
        return sb8.toString();
    }

    private  boolean wareHouseControl() {
        return SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(getmSalesType()) || this.wareHouse.getLogicalRef() != -1 || !TextUtils.isEmpty(this.wareHouse.getDefinition());
    }

    private  boolean returnWareHouseControl() {
        return !SalesUtils.m415xd12e7b8b(getmSalesType()) || this.returnWareHouse.getLogicalRef() != -1 || !TextUtils.isEmpty(this.returnWareHouse.getDefinition());
    }

    private  boolean sourceWareHouseControl() {
        return !SalesUtils.isSalesTypeDemand(getmSalesType()) || this.sourceWareHouse.getLogicalRef() != -1 || !TextUtils.isEmpty(this.sourceWareHouse.getDefinition());
    }

    private  boolean divisionControl() {
        return this.division.getLogicalRef() != -1 || !TextUtils.isEmpty(this.division.getDefinition());
    }

    private  boolean branchControl() {
        return this.branch.getLogicalRef() != -1 || !TextUtils.isEmpty(this.branch.getDefinition()) || !this.viewModel.shouldValidateBranch();
    }

    private  boolean factoryControl() {
        return this.factory.getLogicalRef() != -1 || !TextUtils.isEmpty(this.factory.getDefinition()) || !this.viewModel.shouldValidateFactory();
    }

    private  boolean isCustomerHaveCabinAndNotSelected() {
        if (!SalesUtils.isSalesTypeOrderOrDemandOrWhTransfer(getmSalesType()) && this.cabin.getLogicalRef() <= 0) {
            return this.viewModel.getSqlHelper().getCustomerCabinCount(this.viewModel.erpType() == ErpType.NETSIS ? this.clCode : this.viewModel.getBaseErp().getCustomerClCode(this.clRef), this.viewModel.user().getFirmNr()) > 0;
        }
        return false;
    }

    private  boolean isHeaderSpeCodeNotSelected() {
        return SalesUtils.isSalesTypeOrder(getmSalesType()) && Intrinsics.areEqual(this.viewModel.getSqlHelper().getParamValue(ParameterTypes.ptHeaderSpeCodeRequiredForOrder), "1") && this.speCode.getLogicalRef() <= 0;
    }

    public  String checkRequiredFields(ErpType erpType) throws InterruptedException {
        Intrinsics.checkNotNullParameter(erpType, "erpType");
        if (!SalesUtils.isSalesTypeOrderOrInvoiceOrDispatch(getmSalesType())) {
            return "";
        }
        SalesFicheParameters salesFicheParameters = this.viewModel.getSalesFicheParameters(getmSalesType());
        Intrinsics.checkNotNull(salesFicheParameters);
        SalesFicheHeaderFields mSalesFicheHeaderFields = salesFicheParameters.getMSalesFicheHeaderFields();
        RequiredFields requiredFields = this.viewModel.requiredFields(getmSalesType());
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields);
        sb.append(getHeaderRequiredFields(requiredFields, mSalesFicheHeaderFields, erpType));
        return (sb + getLogisticRequiredFields(requiredFields, mSalesFicheHeaderFields)) + getPaymentRequiredFields(requiredFields, mSalesFicheHeaderFields);
    }

    private  boolean isCustomerEInvoiceSgkType() {
        if (this.viewModel.canOrderCanTransferEInvoiceOrEArchive() && this.viewModel.getSqlHelper().getClEInvoiceUser(this.clRef) == 1) {
            return EInvoiceTyp.Companion.fromEInvoiceTyp(this.viewModel.getSqlHelper().getClEInvoiceTyp(this.clRef)) == EInvoiceTyp.Sgk;
        }
        return false;
    }

    private  boolean hasCustomerShipAddress() throws InterruptedException {
        Pair pairM475to = this.viewModel.erpType() == ErpType.NETSIS ? TuplesKt.to("CODE", this.clCode) : TuplesKt.to("CLREF", String.valueOf(this.clRef));
        String str = (String) pairM475to.component1();
        String str2 = (String) pairM475to.component2();
        Intrinsics.checkNotNull(this.viewModel.getTableForShipAddressFromSqlHelper(ShipAddress.class, str + "=?", new String[]{str2}), "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.ShipAddress>");
        return !r3.isEmpty();
    }

    private  String getHeaderRequiredFields(RequiredFields requiredFields, SalesFicheHeaderFields salesFicheHeaderFields, ErpType erpType) {
        String string = "";
        if (requiredFields.isBranchRequired() && salesFicheHeaderFields.isBranch() && TextUtils.isEmpty(this.branch.toString())) {
            StringBuilder sb = new StringBuilder();
            Context context = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context);
            Context context2 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context2);
            sb.append(context.getString(R.string.exp_41_sales_header_not_select, context2.getString(R.string.str_branch)));
            string = sb.toString();
        }
        if (requiredFields.isDivisionRequired() && salesFicheHeaderFields.isDivision() && TextUtils.isEmpty(this.division.toString())) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(string);
            Context context3 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context3);
            Context context4 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context4);
            sb2.append(context3.getString(R.string.exp_41_sales_header_not_select, context4.getString(R.string.str_division)));
            string = sb2.toString();
        }
        if (requiredFields.isFactoryRequired() && salesFicheHeaderFields.isFactory() && TextUtils.isEmpty(this.factory.toString())) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(string);
            Context context5 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context5);
            Context context6 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context6);
            sb3.append(context5.getString(R.string.exp_41_sales_header_not_select, context6.getString(R.string.str_factory)));
            string = sb3.toString();
        }
        if (requiredFields.isWarehouseRequired() && TextUtils.isEmpty(this.wareHouse.toString())) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(string);
            Context context7 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context7);
            Context context8 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context8);
            sb4.append(context7.getString(R.string.exp_41_sales_header_not_select, context8.getString(R.string.str_warehouse)));
            string = sb4.toString();
        }
        if (requiredFields.isInvoiceTypeRequired() && isCustomerEInvoiceSgkType() && TextUtils.isEmpty(this.erpInvoiceType.toString())) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(string);
            Context context9 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context9);
            Context context10 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context10);
            sb5.append(context9.getString(R.string.exp_41_sales_header_not_select, context10.getString(R.string.str_erp_invoice_type)));
            string = sb5.toString();
        }
        if (requiredFields.isDeliveryDateRequired() && salesFicheHeaderFields.isDeliveryDate() && TextUtils.isEmpty(this.deliveryDate.toString())) {
            StringBuilder sb6 = new StringBuilder();
            sb6.append(string);
            Context context11 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context11);
            Context context12 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context12);
            sb6.append(context11.getString(R.string.exp_41_sales_header_not_select, context12.getString(R.string.str_delivery_date)));
            string = sb6.toString();
        }
        if (requiredFields.isCustomerOrderNoRequired() && salesFicheHeaderFields.isCustomerOrderNo() && TextUtils.isEmpty(this.customerOrderNo.toString())) {
            StringBuilder sb7 = new StringBuilder();
            sb7.append(string);
            Context context13 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context13);
            Context context14 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context14);
            sb7.append(context13.getString(R.string.exp_41_sales_header_not_select, context14.getString(R.string.str_customer_order_no)));
            string = sb7.toString();
        }
        if (requiredFields.isCabinRequired() && TextUtils.isEmpty(this.cabin.getDefinition())) {
            StringBuilder sb8 = new StringBuilder();
            sb8.append(string);
            Context context15 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context15);
            Context context16 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context16);
            sb8.append(context15.getString(R.string.exp_41_sales_header_not_select, context16.getString(R.string.str_cabin)));
            string = sb8.toString();
        }
        if (requiredFields.isDocumentTrackingNoRequired() && salesFicheHeaderFields.isDocumentTrackingNo() && TextUtils.isEmpty(this.documentTrackingNo.getDefinition())) {
            StringBuilder sb9 = new StringBuilder();
            sb9.append(string);
            Context context17 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context17);
            Context context18 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context18);
            sb9.append(context17.getString(R.string.exp_41_sales_header_not_select, context18.getString(R.string.str_document_tracking_no)));
            string = sb9.toString();
        }
        if (requiredFields.isDocumentNoRequired() && salesFicheHeaderFields.isDocumentNo() && TextUtils.isEmpty(this.documentNo.getDefinition())) {
            StringBuilder sb10 = new StringBuilder();
            sb10.append(string);
            Context context19 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context19);
            Context context20 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context20);
            sb10.append(context19.getString(R.string.exp_41_sales_header_not_select, context20.getString(R.string.str_document_no)));
            string = sb10.toString();
        }
        if (requiredFields.isSpecialCodeRequired() && salesFicheHeaderFields.isSpeCode()) {
            if (erpType == ErpType.NETSIS && TextUtils.isEmpty(this.firstSpeCode.getDefinition())) {
                StringBuilder sb11 = new StringBuilder();
                sb11.append(string);
                Context context21 = ContextUtils.getmContext();
                Intrinsics.checkNotNull(context21);
                Context context22 = ContextUtils.getmContext();
                Intrinsics.checkNotNull(context22);
                sb11.append(context21.getString(R.string.exp_41_sales_header_not_select, context22.getString(R.string.str_customer_specode_1)));
                string = sb11.toString();
            } else if (erpType == ErpType.TIGER && this.speCode.getLogicalRef() <= 0) {
                StringBuilder sb12 = new StringBuilder();
                sb12.append(string);
                Context context23 = ContextUtils.getmContext();
                Intrinsics.checkNotNull(context23);
                Context context24 = ContextUtils.getmContext();
                Intrinsics.checkNotNull(context24);
                sb12.append(context23.getString(R.string.exp_41_sales_header_not_select, context24.getString(R.string.str_spe_code)));
                string = sb12.toString();
            }
        }
        if (requiredFields.isAuthorizationCodeRequired() && salesFicheHeaderFields.isWarrantyCode()) {
            if (erpType == ErpType.NETSIS && TextUtils.isEmpty(this.secondSpeCode.getDefinition())) {
                StringBuilder sb13 = new StringBuilder();
                sb13.append(string);
                Context context25 = ContextUtils.getmContext();
                Intrinsics.checkNotNull(context25);
                Context context26 = ContextUtils.getmContext();
                Intrinsics.checkNotNull(context26);
                sb13.append(context25.getString(R.string.exp_41_sales_header_not_select, context26.getString(R.string.str_customer_specode_2)));
                string = sb13.toString();
            } else if (erpType == ErpType.TIGER && TextUtils.isEmpty(this.warrantyCode.getDefinition())) {
                StringBuilder sb14 = new StringBuilder();
                sb14.append(string);
                Context context27 = ContextUtils.getmContext();
                Intrinsics.checkNotNull(context27);
                Context context28 = ContextUtils.getmContext();
                Intrinsics.checkNotNull(context28);
                sb14.append(context27.getString(R.string.exp_41_sales_header_not_select, context28.getString(R.string.str_warranty_code)));
                string = sb14.toString();
            }
        }
        if (requiredFields.isExplanation1Required() && salesFicheHeaderFields.isExplanation1() && TextUtils.isEmpty(this.explanation1.toString())) {
            StringBuilder sb15 = new StringBuilder();
            sb15.append(string);
            Context context29 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context29);
            Context context30 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context30);
            sb15.append(context29.getString(R.string.exp_41_sales_header_not_select, context30.getString(R.string.str_explanation_1)));
            string = sb15.toString();
        }
        if (requiredFields.isExplanation2Required() && salesFicheHeaderFields.isExplanation2() && TextUtils.isEmpty(this.explanation2.toString())) {
            StringBuilder sb16 = new StringBuilder();
            sb16.append(string);
            Context context31 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context31);
            Context context32 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context32);
            sb16.append(context31.getString(R.string.exp_41_sales_header_not_select, context32.getString(R.string.str_explanation_2)));
            string = sb16.toString();
        }
        if (requiredFields.isExplanation3Required() && salesFicheHeaderFields.isExplanation3() && TextUtils.isEmpty(this.explanation3.toString())) {
            StringBuilder sb17 = new StringBuilder();
            sb17.append(string);
            Context context33 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context33);
            Context context34 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context34);
            sb17.append(context33.getString(R.string.exp_41_sales_header_not_select, context34.getString(R.string.str_explanation_3)));
            string = sb17.toString();
        }
        if (!requiredFields.isExplanation4Required() || !salesFicheHeaderFields.isExplanation4() || !TextUtils.isEmpty(this.explanation4.toString())) {
            return string;
        }
        StringBuilder sb18 = new StringBuilder();
        sb18.append(string);
        Context context35 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context35);
        Context context36 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context36);
        sb18.append(context35.getString(R.string.exp_41_sales_header_not_select, context36.getString(R.string.str_explanation_4)));
        return sb18.toString();
    }

    private  String getLogisticRequiredFields(RequiredFields requiredFields, SalesFicheHeaderFields salesFicheHeaderFields) {
        String string = "";
        if (requiredFields.isShipTypeRequired() && salesFicheHeaderFields.isShipType() && TextUtils.isEmpty(this.shipType.toString())) {
            StringBuilder sb = new StringBuilder();
            Context context = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context);
            Context context2 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context2);
            sb.append(context.getString(R.string.exp_41_sales_header_not_select, context2.getString(R.string.str_shipType)));
            string = sb.toString();
        }
        if (requiredFields.isShippingAccountRequired() && salesFicheHeaderFields.isShipAccount() && TextUtils.isEmpty(this.shipAccount.toString())) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(string);
            Context context3 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context3);
            Context context4 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context4);
            sb2.append(context3.getString(R.string.exp_41_sales_header_not_select, context4.getString(R.string.str_ship_account)));
            string = sb2.toString();
        }
        if (requiredFields.isShippingAddressRequired() && ((salesFicheHeaderFields.isShipAddress() || this.viewModel.isDifferentShipAddress()) && ((!this.viewModel.isRequiredShippingAddressIgnored() || hasCustomerShipAddress()) && TextUtils.isEmpty(this.shipAddress.toString())))) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(string);
            Context context5 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context5);
            Context context6 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context6);
            sb3.append(context5.getString(R.string.exp_41_sales_header_not_select, context6.getString(R.string.str_ship_address)));
            string = sb3.toString();
        }
        if (requiredFields.isShipAgentRequired() && salesFicheHeaderFields.isShipAgent() && TextUtils.isEmpty(this.shipAgent.toString())) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(string);
            Context context7 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context7);
            Context context8 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context8);
            sb4.append(context7.getString(R.string.exp_41_sales_header_not_select, context8.getString(R.string.str_shipment_firm)));
            string = sb4.toString();
        }
        if (!requiredFields.isInvoiceAddressRequired() || !TextUtils.isEmpty(String.valueOf(this.invoiceAddress))) {
            return string;
        }
        StringBuilder sb5 = new StringBuilder();
        sb5.append(string);
        Context context9 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context9);
        Context context10 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context10);
        sb5.append(context9.getString(R.string.exp_41_sales_header_not_select, context10.getString(R.string.str_invoice_address)));
        return sb5.toString();
    }

    private  String getPaymentRequiredFields(RequiredFields requiredFields, SalesFicheHeaderFields salesFicheHeaderFields) {
        String string = "";
        if (requiredFields.isTradeGroupRequired() && salesFicheHeaderFields.isTradeGroup() && this.tradeGroup.getLogicalRef() <= 0) {
            StringBuilder sb = new StringBuilder();
            Context context = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context);
            Context context2 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context2);
            sb.append(context.getString(R.string.exp_41_sales_header_not_select, context2.getString(R.string.str_trade_group)));
            string = sb.toString();
        }
        if (requiredFields.isPaymentPlanRequired() && salesFicheHeaderFields.isPayment() && TextUtils.isEmpty(this.payPlan.getDefinition())) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(string);
            Context context3 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context3);
            Context context4 = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context4);
            sb2.append(context3.getString(R.string.exp_41_sales_header_not_select, context4.getString(R.string.str_pay_plan)));
            string = sb2.toString();
        }
        if (!requiredFields.isProjectCodeRequired() || !salesFicheHeaderFields.isProject() || !TextUtils.isEmpty(this.projectCode.toString())) {
            return string;
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(string);
        Context context5 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context5);
        Context context6 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context6);
        sb3.append(context5.getString(R.string.exp_41_sales_header_not_select, context6.getString(R.string.str_project_code)));
        return sb3.toString();
    }
    public  Object salesDetailListControl(boolean z, ErpType erpType, SalesType salesType, Continuation<? super String> continuation) throws Throwable {
        C30381 c30381 = null;
        String string = "";
        boolean z2;
        C30381 c303812;
        Iterator<SalesDetail> it;
        int r11;
        boolean z3;
        ErpType erpType2;
        SalesType salesType2;
        String str;
        String str2;
        Sales sales;
        SalesType salesType3;
        Object obj;
        SalesDetail salesDetail;
        boolean z4;
        int r4;
        ErpType erpType3;
        boolean z5;
        double dDoubleValue;
        PurchasePriceUtils purchasePriceUtils;
        Object obj2;
        int r23;
        int r112;
        int lastPurchasePriceParam;
        PurchasePriceParamValues purchasePriceParamValues;
        SalesUtils salesUtils;
        String string2;
        String str3;
        Sales sales2 = this;
        if (continuation instanceof C30381) {
            c30381 = (C30381) continuation;
            int r3 = c30381.label;
            if ((r3 & Integer.MIN_VALUE) != 0) {
                c30381.label = r3 - Integer.MIN_VALUE;
            } else {
                c30381 = sales2.new C30381((Continuation<? super C30381>) continuation);
            }
        }
        Object obj3 = c30381.result;
        Object obj4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r42 = c30381.label;
        String str4 = "\n                    \n                    ";
        String str5 = "\n                    ";
        try {
            try {
                if (r42 == 0) {
                    ResultKt.throwOnFailure(obj3);
                    string = "";
                    if (sales2.viewModel.erpType() == ErpType.NETSIS) {
                        ValidationResult validationResultControlSecondUnitConversions = sales2.viewModel.controlSecondUnitConversions(sales2);
                        if (!validationResultControlSecondUnitConversions.isSuccess()) {
                            string = validationResultControlSecondUnitConversions.getErrorMessage();
                        }
                    }
                    ValidationResult validationResultValidateOrderMinLimit = validateOrderMinLimit();
                    if (!validationResultValidateOrderMinLimit.isSuccess()) {
                        String sb = string +
                                StringsKt.trimIndent("\n                    " + validationResultValidateOrderMinLimit.getErrorMessage() + "\n                    \n                    ");
                        string = sb;
                    }
                    boolean zIsPromotionItemPriceEnabled = sales2.viewModel.isPromotionItemPriceEnabled();
                    ArrayList<SalesDetail> arrayList = sales2.mSalesDetailList;
                    Intrinsics.checkNotNull(arrayList);
                    z2 = zIsPromotionItemPriceEnabled;
                    c303812 = c30381;
                    it = arrayList.iterator();
                    r11 = 0;
                    z3 = z;
                    erpType2 = erpType;
                    salesType2 = salesType;
                    if (!it.hasNext()) {
                    }
                } else if (r42 == 1) {
                    z5 = c30381.Z1;
                    r4 = c30381.I0;
                    z4 = c30381.Z0;
                    salesDetail = (SalesDetail) c30381.L5;
                    Iterator<SalesDetail> it2 = (Iterator) c30381.L4;
                    string = (String) c30381.L3;
                    salesType3 = (SalesType) c30381.L2;
                    ErpType erpType4 = (ErpType) c30381.L1;
                    sales = (Sales) c30381.L0;
                    ResultKt.throwOnFailure(obj3);
                    str2 = "\n                    \n                    ";
                    str = "\n                    ";
                    obj = obj4;
                    erpType3 = erpType4;
                    it = it2;
                    dDoubleValue = ((Number) obj3).doubleValue();
                    purchasePriceUtils = PurchasePriceUtils.INSTANCE;
                    Intrinsics.checkNotNull(salesDetail);
                    if (purchasePriceUtils.getSalesDetailPrice(salesDetail) > dDoubleValue) {
                    }
                    if (!TextUtils.isEmpty(string)) {
                    }
                    str4 = str2;
                    str5 = str;
                    Object obj5 = obj;
                    r11 = ı;
                    obj4 = obj5;
                    if (!it.hasNext()) {
                    }
                } else {
                    if (r42 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    boolean z6 = c30381.Z1;
                    int r43 = c30381.I0;
                    boolean z7 = c30381.Z0;
                    SalesDetail next = (SalesDetail) c30381.L5;
                    it = (Iterator) c30381.L4;
                    string = (String) c30381.L3;
                    SalesType salesType4 = (SalesType) c30381.L2;
                    ErpType erpType5 = (ErpType) c30381.L1;
                    Sales sales3 = (Sales) c30381.L0;
                    ResultKt.throwOnFailure(obj3);
                    str2 = "\n                    \n                    ";
                    str = "\n                    ";
                    obj = obj4;
                    boolean z8 = z7;
                    double dDoubleValue2 = ((Number) obj3).doubleValue();
                    PurchasePriceUtils purchasePriceUtils2 = PurchasePriceUtils.INSTANCE;
                    Intrinsics.checkNotNull(next);
                    if (purchasePriceUtils2.getSalesDetailPrice(next) < dDoubleValue2) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(string);
                        Context context = ContextUtils.getmContext();
                        Intrinsics.checkNotNull(context);
                        sb2.append(context.getString(R.string.exp_price_below_defined_purchase_price, next.getCode(), Boxing.boxDouble(dDoubleValue2)));
                        sb2.append('\n');
                        string = sb2.toString();
                    }
                    z3 = z8;
                    int r32 = r43;
                    salesType2 = salesType4;
                    ErpType erpType6 = erpType5;
                    z2 = z6;
                    sales2 = sales3;
                    c303812 = c30381;
                    erpType2 = erpType6;
                    if (!TextUtils.isEmpty(string)) {
                        string = string + '\n';
                    }
                    str4 = str2;
                    str5 = str;
                    Object obj52 = obj;
                    r11 = r32;
                    obj4 = obj52;
                    if (!it.hasNext()) {
                        try {
                            next = it.next();
                            if (next.amountControl()) {
                                str2 = str4;
                                str = str5;
                            } else {
                                try {
                                    StringBuilder sb3 = new StringBuilder();
                                    sb3.append(string);
                                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                    str2 = str4;
                                    String stringResource = ContextUtils.getStringResource(R.string.exp_33_sales_detail_amount_not_enter);
                                    Intrinsics.checkNotNullExpressionValue(stringResource, "getStringResource(...)");
                                    str = str5;
                                    String str6 = String.format(stringResource, Arrays.copyOf(new Object[]{next.getName()}, 1));
                                    Intrinsics.checkNotNullExpressionValue(str6, "format(...)");
                                    sb3.append(str6);
                                    sb3.append('\n');
                                    string = sb3.toString();
                                } catch (Exception e2) {
                                    e = e2;
                                    string = str3;
                                    Log.e(MobileSales.TAG, "salesDetailListControl: ", e);
                                    return string;
                                } catch (Throwable unused) {
                                    return str3;
                                }
                                str3 = string;
                            }
                            if (z3) {
                                Intrinsics.checkNotNull(salesType2);
                                if (!SalesUtils.isSalesTypeDemandOrWhTransfer(salesType2)) {
                                    if (next.getPromotion().isSelect()) {
                                        if (z2) {
                                        }
                                    }
                                    if (!next.priceControl()) {
                                        StringBuilder sb4 = new StringBuilder();
                                        sb4.append(string);
                                        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                                        String stringResource2 = ContextUtils.getStringResource(R.string.exp_34_sales_detail_price_not_enter);
                                        Intrinsics.checkNotNullExpressionValue(stringResource2, "getStringResource(...)");
                                        String str7 = String.format(stringResource2, Arrays.copyOf(new Object[]{next.getName()}, 1));
                                        Intrinsics.checkNotNullExpressionValue(str7, "format(...)");
                                        sb4.append(str7);
                                        sb4.append('\n');
                                        string = sb4.toString();
                                    }
                                    if (next.unitControl()) {
                                        StringBuilder sb5 = new StringBuilder();
                                        sb5.append(string);
                                        StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                                        String stringResource3 = ContextUtils.getStringResource(R.string.exp_48_sales_detail_unit_not_found);
                                        Intrinsics.checkNotNullExpressionValue(stringResource3, "getStringResource(...)");
                                        String str8 = String.format(stringResource3, Arrays.copyOf(new Object[]{next.getName()}, 1));
                                        Intrinsics.checkNotNullExpressionValue(str8, "format(...)");
                                        sb5.append(str8);
                                        sb5.append('\n');
                                        string = sb5.toString();
                                    }
                                    if (next.serialLotControl()) {
                                        StringBuilder sb6 = new StringBuilder();
                                        sb6.append(string);
                                        if (next.getTrackType() == TrackType.SERIAL_GROUP.getType()) {
                                            StringBuilder sb7 = new StringBuilder();
                                            StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                                            String stringResource4 = ContextUtils.getStringResource(R.string.exp_51_sales_detail_serial_group_not_enter);
                                            Intrinsics.checkNotNullExpressionValue(stringResource4, "getStringResource(...)");
                                            r23 = r11;
                                            obj2 = obj4;
                                            String str9 = String.format(stringResource4, Arrays.copyOf(new Object[]{next.getName()}, 1));
                                            Intrinsics.checkNotNullExpressionValue(str9, "format(...)");
                                            sb7.append(str9);
                                            sb7.append("\n\n");
                                            string2 = sb7.toString();
                                        } else {
                                            obj2 = obj4;
                                            r23 = r11;
                                            StringBuilder sb8 = new StringBuilder();
                                            StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
                                            String stringResource5 = ContextUtils.getStringResource(R.string.exp_35_sales_detail_serial_lot_not_enter);
                                            Intrinsics.checkNotNullExpressionValue(stringResource5, "getStringResource(...)");
                                            String str10 = String.format(stringResource5, Arrays.copyOf(new Object[]{next.getName()}, 1));
                                            Intrinsics.checkNotNullExpressionValue(str10, "format(...)");
                                            sb8.append(str10);
                                            sb8.append("\n\n");
                                            string2 = sb8.toString();
                                        }
                                        sb6.append(string2);
                                        string = sb6.toString();
                                    } else {
                                        obj2 = obj4;
                                        r23 = r11;
                                    }
                                    Intrinsics.checkNotNull(next);
                                    r32 = (!SalesUtils.isVaryantProductAlert(next, erpType2) && next.isHasVariant() && next.getVariant().getLogicalRef() == -1) ? 1 : r23;
                                    if (sales2.viewModel.erpType() == ErpType.TIGER) {
                                        Intrinsics.checkNotNull(salesType2);
                                        if (SalesUtils.isSalesTypeReturnDispatch(salesType2) && sales2.viewModel.getBaseErp().isRequiredFieldForReturnDispatch()) {
                                            String definition = next.getExplanation().getDefinition();
                                            Intrinsics.checkNotNullExpressionValue(definition, "getDefinition(...)");
                                            if (definition.length() == 0) {
                                                StringBuilder sb9 = new StringBuilder();
                                                sb9.append(string);
                                                StringCompanionObject stringCompanionObject6 = StringCompanionObject.INSTANCE;
                                                String stringResource6 = ContextUtils.getStringResource(R.string.exp_97_return_dispatch_description);
                                                Intrinsics.checkNotNullExpressionValue(stringResource6, "getStringResource(...)");
                                                r112 = 0;
                                                String str11 = String.format(stringResource6, Arrays.copyOf(new Object[0], 0));
                                                Intrinsics.checkNotNullExpressionValue(str11, "format(...)");
                                                sb9.append(str11);
                                                string = sb9.toString();
                                            }
                                            if (next.isLocTracking()) {
                                                Intrinsics.checkNotNull(salesType2);
                                                if (SalesUtils.isSalesSeriCheckLocationTracking(salesType2)) {
                                                    Iterator<Serilot> it3 = next.getSalesSerialLots().iterator();
                                                    double d2 = 0.0d;
                                                    int r6 = r112;
                                                    while (it3.hasNext()) {
                                                        Serilot next2 = it3.next();
                                                        int r113 = next2.mainUnitRef;
                                                        Iterator<Serilot> it4 = it3;
                                                        if (next2.locationCode.length() > 0) {
                                                            d2 += next2.amount;
                                                        }
                                                        it3 = it4;
                                                        r6 = r113;
                                                    }
                                                    if (next.getMainAmount(r6 == next.getUnit().getLogicalRef()) != d2) {
                                                        StringBuilder sb10 = new StringBuilder();
                                                        sb10.append(string);
                                                        StringCompanionObject stringCompanionObject7 = StringCompanionObject.INSTANCE;
                                                        String stringResource7 = ContextUtils.getStringResource(R.string.exp_55_sales_detail_location_amount);
                                                        Intrinsics.checkNotNullExpressionValue(stringResource7, "getStringResource(...)");
                                                        String str12 = String.format(stringResource7, Arrays.copyOf(new Object[]{next.getName()}, 1));
                                                        Intrinsics.checkNotNullExpressionValue(str12, "format(...)");
                                                        sb10.append(str12);
                                                        sb10.append('\n');
                                                        string = sb10.toString();
                                                    }
                                                }
                                            }
                                            if (sales2.viewModel.erpType() == ErpType.NETSIS && sales2.viewModel.getBaseErp().stopNegativeBalance()) {
                                                salesUtils = SalesUtils.INSTANCE;
                                                Intrinsics.checkNotNull(salesType2);
                                                if (salesUtils.isSales(salesType2) && !sales2.isExistInSalesDetailList(next)) {
                                                    string = string + sales2.exceptionNegativeBalanceMessage(next) + '\n';
                                                }
                                            }
                                            lastPurchasePriceParam = sales2.viewModel.getBaseErp().getLastPurchasePriceParam(salesType2);
                                            purchasePriceParamValues = PurchasePriceParamValues.BLOCK;
                                            if (lastPurchasePriceParam == purchasePriceParamValues.getValue()) {
                                                PurchasePriceUtils.INSTANCE.initialize(sales2);
                                                if (ContextUtils.checkInternetConnection()) {
                                                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                                                    SalessalesDetailListControllastPurchasePrice1 salessalesDetailListControllastPurchasePrice1 = new SalessalesDetailListControllastPurchasePrice1(next, null);
                                                    c303812.L0 = sales2;
                                                    c303812.L1 = erpType2;
                                                    c303812.L2 = salesType2;
                                                    c303812.L3 = string;
                                                    c303812.L4 = it;
                                                    c303812.L5 = next;
                                                    c303812.Z0 = z3;
                                                    c303812.I0 = r32;
                                                    c303812.Z1 = z2;
                                                    c303812.label = 1;
                                                    Object objWithContext = BuildersKt.withContext(coroutineDispatcher, salessalesDetailListControllastPurchasePrice1, c303812);
                                                    obj = obj2;
                                                    if (objWithContext == obj) {
                                                        return obj;
                                                    }
                                                    sales = sales2;
                                                    z5 = z2;
                                                    z4 = z3;
                                                    obj3 = objWithContext;
                                                    int r22 = r32;
                                                    erpType3 = erpType2;
                                                    c30381 = c303812;
                                                    salesDetail = next;
                                                    salesType3 = salesType2;
                                                    r4 = r22;
                                                    dDoubleValue = ((Number) obj3).doubleValue();
                                                    purchasePriceUtils = PurchasePriceUtils.INSTANCE;
                                                    Intrinsics.checkNotNull(salesDetail);
                                                    if (purchasePriceUtils.getSalesDetailPrice(salesDetail) > dDoubleValue) {
                                                        StringBuilder sb11 = new StringBuilder();
                                                        sb11.append(string);
                                                        Context context2 = ContextUtils.getmContext();
                                                        Intrinsics.checkNotNull(context2);
                                                        sb11.append(context2.getString(R.string.exp_price_below_last_purchase_price, salesDetail.getCode(), Boxing.boxDouble(purchasePriceUtils.getPurchasePrice())));
                                                        sb11.append('\n');
                                                        string = sb11.toString();
                                                        c303812 = c30381;
                                                        erpType2 = erpType3;
                                                        r32 = r4;
                                                        z3 = z4;
                                                        salesType2 = salesType3;
                                                        z2 = z5;
                                                        sales2 = sales;
                                                    } else {
                                                        c303812 = c30381;
                                                        erpType2 = erpType3;
                                                        r32 = r4;
                                                        z3 = z4;
                                                        salesType2 = salesType3;
                                                        z2 = z5;
                                                        sales2 = sales;
                                                    }
                                                } else {
                                                    obj = obj2;
                                                }
                                            } else {
                                                obj = obj2;
                                                if (sales2.viewModel.getBaseErp().getDefinedPurchasePriceParam(salesType2) == purchasePriceParamValues.getValue()) {
                                                    PurchasePriceUtils.INSTANCE.initialize(sales2);
                                                    CoroutineDispatcher coroutineDispatcher2 = Dispatchers.getIO();
                                                    SalessalesDetailListControldefinedPurchasePrice1 salessalesDetailListControldefinedPurchasePrice1 = new SalessalesDetailListControldefinedPurchasePrice1(sales2, next, null);
                                                    c303812.L0 = sales2;
                                                    c303812.L1 = erpType2;
                                                    c303812.L2 = salesType2;
                                                    c303812.L3 = string;
                                                    c303812.L4 = it;
                                                    c303812.L5 = next;
                                                    c303812.Z0 = z3;
                                                    c303812.I0 = r32;
                                                    c303812.Z1 = z2;
                                                    c303812.label = 2;
                                                    Object objWithContext2 = BuildersKt.withContext(coroutineDispatcher2, salessalesDetailListControldefinedPurchasePrice1, c303812);
                                                    if (objWithContext2 == obj) {
                                                        return obj;
                                                    }
                                                    salesType4 = salesType2;
                                                    r43 = r32;
                                                    z8 = z3;
                                                    obj3 = objWithContext2;
                                                    C30381 c303813 = c303812;
                                                    sales3 = sales2;
                                                    z6 = z2;
                                                    erpType5 = erpType2;
                                                    c30381 = c303813;
                                                    double dDoubleValue22 = ((Number) obj3).doubleValue();
                                                    PurchasePriceUtils purchasePriceUtils22 = PurchasePriceUtils.INSTANCE;
                                                    Intrinsics.checkNotNull(next);
                                                    if (purchasePriceUtils22.getSalesDetailPrice(next) < dDoubleValue22) {
                                                    }
                                                    z3 = z8;
                                                    int r322 = r43;
                                                    salesType2 = salesType4;
                                                    ErpType erpType62 = erpType5;
                                                    z2 = z6;
                                                    sales2 = sales3;
                                                    c303812 = c30381;
                                                    erpType2 = erpType62;
                                                }
                                            }
                                            if (!TextUtils.isEmpty(string)) {
                                            }
                                            str4 = str2;
                                            str5 = str;
                                            Object obj522 = obj;
                                            r11 = r322;
                                            obj4 = obj522;
                                            if (!it.hasNext()) {
                                                String str13 = str4;
                                                String str14 = str5;
                                                str3 = string;
                                                if (r11 != 0) {
                                                    String sb12 = str3 +
                                                            StringsKt.trimIndent(str14 + ContextUtils.getStringResource(R.string.str_variant_not_entered_error) + str13);
                                                    string = sb12;
                                                } else {
                                                    string = str3;
                                                }
                                                sales2.tempSalesDetailList.clear();
                                                return string;
                                            }
                                        }
                                    }
                                    r112 = 0;
                                    if (next.isLocTracking()) {
                                    }
                                    if (sales2.viewModel.erpType() == ErpType.NETSIS) {
                                        salesUtils = SalesUtils.INSTANCE;
                                        Intrinsics.checkNotNull(salesType2);
                                        if (salesUtils.isSales(salesType2)) {
                                            string = string + sales2.exceptionNegativeBalanceMessage(next) + '\n';
                                        }
                                    }
                                    lastPurchasePriceParam = sales2.viewModel.getBaseErp().getLastPurchasePriceParam(salesType2);
                                    purchasePriceParamValues = PurchasePriceParamValues.BLOCK;
                                    if (lastPurchasePriceParam == purchasePriceParamValues.getValue()) {
                                    }
                                    if (!TextUtils.isEmpty(string)) {
                                    }
                                    str4 = str2;
                                    str5 = str;
                                    Object obj5222 = obj;
                                    r11 = r322;
                                    obj4 = obj5222;
                                    if (!it.hasNext()) {
                                    }
                                }
                            }
                            string = string;
                            if (next.unitControl()) {
                            }
                            if (next.serialLotControl()) {
                            }
                            Intrinsics.checkNotNull(next);
                            if (!SalesUtils.isVaryantProductAlert(next, erpType2)) {
                            }
                            if (sales2.viewModel.erpType() == ErpType.TIGER) {
                            }
                            r112 = 0;
                            if (next.isLocTracking()) {
                            }
                            if (sales2.viewModel.erpType() == ErpType.NETSIS) {
                            }
                            lastPurchasePriceParam = sales2.viewModel.getBaseErp().getLastPurchasePriceParam(salesType2);
                            purchasePriceParamValues = PurchasePriceParamValues.BLOCK;
                            if (lastPurchasePriceParam == purchasePriceParamValues.getValue()) {
                            }
                            if (!TextUtils.isEmpty(string)) {
                            }
                            str4 = str2;
                            str5 = str;
                            Object obj52222 = obj;
                            r11 = r322;
                            obj4 = obj52222;
                        } catch (Exception e3) {
                            e = e3;
                            Log.e(MobileSales.TAG, "salesDetailListControl: ", e);
                            return string;
                        } catch (Throwable unused2) {
                            return string;
                        }
                        if (!it.hasNext()) {
                        }
                    }
                }
            } catch (Exception e4) {
                e = e4;
            }
        } catch (Throwable unused3) {
            return string;
        }
    }

    private  boolean isExistInSalesDetailList(SalesDetail salesDetail) {
        Object next;
        Iterator<T> it = this.tempSalesDetailList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((SalesDetail) next).getCode(), salesDetail.getCode())) {
                break;
            }
        }
        if (next != null) {
            return true;
        }
        this.tempSalesDetailList.add(salesDetail);
        return false;
    }

    private  WareHouse getWareHouse(SalesDetail salesDetail) {
        List table = this.viewModel.getBaseErp().getLogoSqlHelper().getTable(WareHouse.class, "LOGICALREF=?", new String[]{String.valueOf(salesDetail.getWareHouse().getLogicalRef())});
        Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.WareHouse>");
        if (!table.isEmpty()) {
            return (WareHouse) table.get(0);
        }
        return new WareHouse();
    }

    public  String exceptionNegativeBalanceMessage(SalesDetail salesDetail) {
        double actualStock;
        Intrinsics.checkNotNullParameter(salesDetail, "salesDetail");
        if (!Intrinsics.areEqual(getWareHouse(salesDetail).getNegativeBalanceControl(), ExifInterface.LONGITUDE_EAST) || !Intrinsics.areEqual(getWareHouse(salesDetail).getNegativeBalanceShow(), ExifInterface.LONGITUDE_EAST)) {
            return "";
        }
        List table = this.viewModel.getBaseErp().getLogoSqlHelper().getTable(ItemStock.class, "ITEMCODE=? AND WAREHOUSENR=?", new String[]{salesDetail.getCode(), String.valueOf(salesDetail.getWareHouse().getLogicalRef())});
        Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.product.model.database.ItemStock>");
        if (!table.isEmpty()) {
            actualStock = ((ItemStock) table.get(0)).onHand;
        } else {
            actualStock = totalAmount(salesDetail) > salesDetail.getActualStock() ? salesDetail.getActualStock() : 0.0d;
        }
        if (totalAmount(salesDetail) <= actualStock) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String stringResource = ContextUtils.getStringResource(R.string.exp_item_has_negative_balance);
        Intrinsics.checkNotNullExpressionValue(stringResource, "getStringResource(...)");
        String str = String.format(stringResource, Arrays.copyOf(new Object[]{salesDetail.getCode(), StringUtils.formatDoubleNormal(totalAmount(salesDetail) - actualStock)}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        sb.append(str);
        return sb.toString();
    }

    private  double totalAmount(SalesDetail salesDetail) {
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList);
        Iterator<SalesDetail> it = arrayList.iterator();
        double definitionDouble = 0.0d;
        while (it.hasNext()) {
            SalesDetail next = it.next();
            if (Intrinsics.areEqual(next.getCode(), salesDetail.getCode())) {
                definitionDouble += next.getAmount().getDefinitionDouble();
            }
        }
        return definitionDouble;
    }

    public  void calculateVolumeAndWeightInfo(boolean z) {
        int size;
        this.totalNetVolume = 0.0d;
        this.totalGrossVolume = 0.0d;
        this.totalNetWeight = 0.0d;
        this.totalGrossWeight = 0.0d;
        this.totalSku = 0.0d;
        HashMap map = new HashMap();
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList);
        Iterator<SalesDetail> it = arrayList.iterator();
        while (it.hasNext()) {
            SalesDetail next = it.next();
            this.totalNetVolume += CalculateUtils.calculateVolume(next.getAmount().getDefinitionDouble(), next.getNetVolume());
            this.totalGrossVolume += CalculateUtils.calculateVolume(next.getAmount().getDefinitionDouble(), next.getGrossVolume());
            this.totalNetWeight += CalculateUtils.calculateWeight(next.getAmount().getDefinitionDouble(), next.getNetWeight());
            this.totalGrossWeight += CalculateUtils.calculateWeight(next.getAmount().getDefinitionDouble(), next.getGrossWeight());
            String string = String.valueOf(next.getItemRef()) +
                    '_' +
                    (next.isProduct() ? "1" : "0");
            if (!map.containsKey(string)) {
                map.put(string, 1);
            }
        }
        if (z) {
            ArrayList<SalesDetail> arrayList2 = this.mSalesDetailList;
            Intrinsics.checkNotNull(arrayList2);
            size = arrayList2.size();
        } else {
            size = map.size();
        }
        this.totalSku = size;
    }

    public Sales m1314clone() throws CloneNotSupportedException {
        EDispatchAdditionalInfo eDispatchAdditionalInfo;
        Object objClone = super.clone();
        Intrinsics.checkNotNull(objClone, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.model.Sales");
        Sales sales = (Sales) objClone;
        sales.clCode = this.clCode;
        String str = this.andFicheNo;
        if (str == null) {
            str = "";
        }
        sales.andFicheNo = str;
        String str2 = this.ficheNo;
        if (str2 == null) {
            str2 = "";
        }
        sales.ficheNo = str2;
        String str3 = this.ficheCreateDate;
        if (str3 == null) {
            str3 = "";
        }
        sales.ficheCreateDate = str3;
        String str4 = this.gDate;
        if (str4 == null) {
            str4 = "";
        }
        sales.gDate = str4;
        String str5 = this.invoiceAddress;
        if (str5 == null) {
            str5 = "";
        }
        sales.invoiceAddress = str5;
        FicheRefProp ficheRefPropMo1244clone = this.branch.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone, "clone(...)");
        sales.branch = ficheRefPropMo1244clone;
        FicheRefProp ficheRefPropMo1244clone2 = this.division.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone2, "clone(...)");
        sales.division = ficheRefPropMo1244clone2;
        FicheRefProp ficheRefPropMo1244clone3 = this.factory.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone3, "clone(...)");
        sales.factory = ficheRefPropMo1244clone3;
        FicheRefProp ficheRefPropMo1244clone4 = this.wareHouse.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone4, "clone(...)");
        sales.wareHouse = ficheRefPropMo1244clone4;
        FicheRefProp ficheRefPropMo1244clone5 = this.returnWareHouse.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone5, "clone(...)");
        sales.returnWareHouse = ficheRefPropMo1244clone5;
        FicheRefProp ficheRefPropMo1244clone6 = this.sourceWareHouse.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone6, "clone(...)");
        sales.sourceWareHouse = ficheRefPropMo1244clone6;
        FicheRefProp ficheRefPropMo1244clone7 = this.speCode.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone7, "clone(...)");
        sales.speCode = ficheRefPropMo1244clone7;
        FicheRefProp ficheRefPropMo1244clone8 = this.warrantyCode.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone8, "clone(...)");
        sales.warrantyCode = ficheRefPropMo1244clone8;
        FicheStringProp ficheStringPropMo1244clone = this.documentNo.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone, "clone(...)");
        sales.documentNo = ficheStringPropMo1244clone;
        FicheStringProp ficheStringPropMo1244clone2 = this.documentTrackingNo.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone2, "clone(...)");
        sales.documentTrackingNo = ficheStringPropMo1244clone2;
        FicheStringProp ficheStringPropMo1244clone3 = this.customerOrderNo.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone3, "clone(...)");
        sales.customerOrderNo = ficheStringPropMo1244clone3;
        FicheDateProp ficheDatePropMo1244clone = this.deliveryDate.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDatePropMo1244clone, "clone(...)");
        sales.deliveryDate = ficheDatePropMo1244clone;
        FicheBooleanProp ficheBooleanPropMo1244clone = this.paymentOrder.clone();
        Intrinsics.checkNotNullExpressionValue(ficheBooleanPropMo1244clone, "clone(...)");
        sales.paymentOrder = ficheBooleanPropMo1244clone;
        FicheStringProp ficheStringPropMo1244clone4 = this.explanation1.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone4, "clone(...)");
        sales.explanation1 = ficheStringPropMo1244clone4;
        FicheStringProp ficheStringPropMo1244clone5 = this.explanation2.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone5, "clone(...)");
        sales.explanation2 = ficheStringPropMo1244clone5;
        FicheStringProp ficheStringPropMo1244clone6 = this.explanation3.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone6, "clone(...)");
        sales.explanation3 = ficheStringPropMo1244clone6;
        FicheStringProp ficheStringPropMo1244clone7 = this.explanation4.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone7, "clone(...)");
        sales.explanation4 = ficheStringPropMo1244clone7;
        FicheStringProp ficheStringPropMo1244clone8 = this.explanation5.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone8, "clone(...)");
        sales.explanation5 = ficheStringPropMo1244clone8;
        FicheStringProp ficheStringPropMo1244clone9 = this.explanation6.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone9, "clone(...)");
        sales.explanation6 = ficheStringPropMo1244clone9;
        FicheStringProp ficheStringPropMo1244clone10 = this.explanation7.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone10, "clone(...)");
        sales.explanation7 = ficheStringPropMo1244clone10;
        FicheStringProp ficheStringPropMo1244clone11 = this.explanation8.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone11, "clone(...)");
        sales.explanation8 = ficheStringPropMo1244clone11;
        FicheStringProp ficheStringPropMo1244clone12 = this.explanation9.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone12, "clone(...)");
        sales.explanation9 = ficheStringPropMo1244clone12;
        FicheStringProp ficheStringPropMo1244clone13 = this.explanation10.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone13, "clone(...)");
        sales.explanation10 = ficheStringPropMo1244clone13;
        FicheDiscountRefProp ficheDiscountRefPropMo1244clone = this.shipAccount.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDiscountRefPropMo1244clone, "clone(...)");
        sales.shipAccount = ficheDiscountRefPropMo1244clone;
        FicheDiscountRefProp ficheDiscountRefPropMo1244clone2 = this.shipAddress.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDiscountRefPropMo1244clone2, "clone(...)");
        sales.shipAddress = ficheDiscountRefPropMo1244clone2;
        FicheRefProp ficheRefPropMo1244clone9 = this.shipAgent.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone9, "clone(...)");
        sales.shipAgent = ficheRefPropMo1244clone9;
        FicheRefProp ficheRefPropMo1244clone10 = this.shipType.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone10, "clone(...)");
        sales.shipType = ficheRefPropMo1244clone10;
        FicheRefProp ficheRefPropMo1244clone11 = this.tradeGroup.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone11, "clone(...)");
        sales.tradeGroup = ficheRefPropMo1244clone11;
        FicheDiscountRefProp ficheDiscountRefPropMo1244clone3 = this.payPlan.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDiscountRefPropMo1244clone3, "clone(...)");
        sales.payPlan = ficheDiscountRefPropMo1244clone3;
        FicheDiscountRefProp ficheDiscountRefPropMo1244clone4 = this.projectCode.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDiscountRefPropMo1244clone4, "clone(...)");
        sales.projectCode = ficheDiscountRefPropMo1244clone4;
        sales.salesFicheDiscountProps = this.salesFicheDiscountProps.clone();
        FicheBooleanProp ficheBooleanPropMo1244clone2 = this.includeVat.clone();
        Intrinsics.checkNotNullExpressionValue(ficheBooleanPropMo1244clone2, "clone(...)");
        sales.includeVat = ficheBooleanPropMo1244clone2;
        FicheStringProp ficheStringPropMo1244clone14 = this.eInvoiceSGKDocumentNo.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone14, "clone(...)");
        sales.eInvoiceSGKDocumentNo = ficheStringPropMo1244clone14;
        FicheStringProp ficheStringPropMo1244clone15 = this.taxPayerCode.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone15, "clone(...)");
        sales.taxPayerCode = ficheStringPropMo1244clone15;
        FicheStringProp ficheStringPropMo1244clone16 = this.taxPayerName.clone();
        Intrinsics.checkNotNullExpressionValue(ficheStringPropMo1244clone16, "clone(...)");
        sales.taxPayerName = ficheStringPropMo1244clone16;
        FicheRefProp ficheRefPropMo1244clone12 = this.eInvoiceTypSgk.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone12, "clone(...)");
        sales.eInvoiceTypSgk = ficheRefPropMo1244clone12;
        FicheDateProp ficheDatePropMo1244clone2 = this.beginDate.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDatePropMo1244clone2, "clone(...)");
        sales.beginDate = ficheDatePropMo1244clone2;
        FicheDateProp ficheDatePropMo1244clone3 = this.endDate.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDatePropMo1244clone3, "clone(...)");
        sales.endDate = ficheDatePropMo1244clone3;
        FicheDiscountRefProp ficheDiscountRefPropMo1244clone5 = this.cabin.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDiscountRefPropMo1244clone5, "clone(...)");
        sales.cabin = ficheDiscountRefPropMo1244clone5;
        FicheDiscountRefProp ficheDiscountRefPropMo1244clone6 = this.firstSpeCode.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDiscountRefPropMo1244clone6, "clone(...)");
        sales.firstSpeCode = ficheDiscountRefPropMo1244clone6;
        FicheDiscountRefProp ficheDiscountRefPropMo1244clone7 = this.secondSpeCode.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDiscountRefPropMo1244clone7, "clone(...)");
        sales.secondSpeCode = ficheDiscountRefPropMo1244clone7;
        FicheRefProp ficheRefPropMo1244clone13 = this.transferSourceWareHouse.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone13, "clone(...)");
        sales.transferSourceWareHouse = ficheRefPropMo1244clone13;
        FicheRefProp ficheRefPropMo1244clone14 = this.transferSourceFactory.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone14, "clone(...)");
        sales.transferSourceFactory = ficheRefPropMo1244clone14;
        FicheRefProp ficheRefPropMo1244clone15 = this.transferSourceDivision.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone15, "clone(...)");
        sales.transferSourceDivision = ficheRefPropMo1244clone15;
        FicheRefProp ficheRefPropMo1244clone16 = this.transferSourceBranch.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone16, "clone(...)");
        sales.transferSourceBranch = ficheRefPropMo1244clone16;
        FicheRefProp ficheRefPropMo1244clone17 = this.transferSourceCostGrp.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone17, "clone(...)");
        sales.transferSourceCostGrp = ficheRefPropMo1244clone17;
        FicheRefProp ficheRefPropMo1244clone18 = this.eDocSerial.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone18, "clone(...)");
        sales.eDocSerial = ficheRefPropMo1244clone18;
        FicheBooleanProp ficheBooleanPropMo1244clone3 = this.eDespatch.clone();
        Intrinsics.checkNotNullExpressionValue(ficheBooleanPropMo1244clone3, "clone(...)");
        sales.eDespatch = ficheBooleanPropMo1244clone3;
        FicheBooleanProp ficheBooleanPropMo1244clone4 = this.insteadOfEDespatch.clone();
        Intrinsics.checkNotNullExpressionValue(ficheBooleanPropMo1244clone4, "clone(...)");
        sales.insteadOfEDespatch = ficheBooleanPropMo1244clone4;
        EDispatchAdditionalInfo eDispatchAdditionalInfo2 = this.eDispatchAdditionalInfo;
        if (eDispatchAdditionalInfo2 != null) {
            Intrinsics.checkNotNull(eDispatchAdditionalInfo2);
            eDispatchAdditionalInfo = eDispatchAdditionalInfo2.clone();
        } else {
            eDispatchAdditionalInfo = new EDispatchAdditionalInfo();
        }
        sales.eDispatchAdditionalInfo = eDispatchAdditionalInfo;
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList);
        ArrayList<SalesDetail> arrayList2 = new ArrayList<>(arrayList.size());
        ArrayList<SalesDetail> arrayList3 = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList3);
        Iterator<SalesDetail> it = arrayList3.iterator();
        while (it.hasNext()) {
            arrayList2.add(it.next().clone());
        }
        sales.mSalesDetailList = arrayList2;
        FicheRefProp ficheRefPropMo1244clone19 = this.erpInvoiceType.clone();
        Intrinsics.checkNotNullExpressionValue(ficheRefPropMo1244clone19, "clone(...)");
        sales.erpInvoiceType = ficheRefPropMo1244clone19;
        FicheDateProp ficheDatePropMo1244clone4 = this.dueDate.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDatePropMo1244clone4, "clone(...)");
        sales.dueDate = ficheDatePropMo1244clone4;
        FicheBooleanProp ficheBooleanPropMo1244clone5 = this.reserved.clone();
        Intrinsics.checkNotNullExpressionValue(ficheBooleanPropMo1244clone5, "clone(...)");
        sales.reserved = ficheBooleanPropMo1244clone5;
        String str6 = this.orderFicheStatus;
        sales.orderFicheStatus = str6 != null ? str6 : "";
        return sales;
    }

    public  double getCustomerDiscRatio() {
        return this.salesFicheDiscountProps.getCustomerDiscount().getDefinitionDouble();
    }

    public  void setCustomerDiscRatio(double d2) {
        FicheStringProp.setDefinition(String.valueOf(d2));
    }

    public  String getCustomerDiscGuid() {
        return this.salesFicheDiscountProps.getCustomerDiscount().getGuid();
    }

    public  void setCustomerDiscGuid(String str) {
        this.salesFicheDiscountProps.getCustomerDiscount().setGuid(str);
    }

    public  String getCustomerCampaignCode() {
        return this.salesFicheDiscountProps.getCustomerDiscount().getCampaignCode();
    }

    public  void setCustomerCampaignCode(String str) {
        this.salesFicheDiscountProps.getCustomerDiscount().setCampaignCode(str);
    }

    public  boolean findDiscountByGuid(String guid) {
        Intrinsics.checkNotNullParameter(guid, "guid");
        return this.salesFicheDiscountProps.findDiscountByGuid(guid) != null;
    }

    public  FicheDiscountProp getDiscountByGuid(String guid) {
        Intrinsics.checkNotNullParameter(guid, "guid");
        return this.salesFicheDiscountProps.findDiscountByGuid(guid);
    }

    public  FicheDiscountProp getCustomerDisc() {
        return this.salesFicheDiscountProps.getCustomerDiscount();
    }

    public  int findDiscountIndexByGuid(String guid) {
        Intrinsics.checkNotNullParameter(guid, "guid");
        return this.salesFicheDiscountProps.findDiscountIndexByGuid(guid);
    }

    public  void swapDiscountIndexes(int r4, int r5) {
        SalesFicheDiscount salesFicheDiscountRemove = this.salesFicheDiscountProps.getDiscounts().remove(r4);
        Intrinsics.checkNotNullExpressionValue(salesFicheDiscountRemove, "removeAt(...)");
        SalesFicheDiscount salesFicheDiscountRemove2 = this.salesFicheDiscountProps.getDiscounts().remove(r5);
        Intrinsics.checkNotNullExpressionValue(salesFicheDiscountRemove2, "removeAt(...)");
        this.salesFicheDiscountProps.getDiscounts().add(r5, salesFicheDiscountRemove);
        this.salesFicheDiscountProps.getDiscounts().add(r4, salesFicheDiscountRemove2);
    }

    public  boolean hasOrderReference() {
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList);
        Iterator<SalesDetail> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().getOrderDetailReference() != 0) {
                return true;
            }
        }
        return false;
    }

    public  boolean hasStockTracking() {
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList);
        Iterator<SalesDetail> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().isLocTracking()) {
                return true;
            }
        }
        return false;
    }

    public Sales(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        double d2 = 0.0d;
        this(0, 0, null, 0, null, null, 0, 0, null, null, 0, 0, 0, d2, d2, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, 0, null, false, false, null, null, null, null, null, null, null, null, null, 0.0d, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, null, -1, -1, -1, 1, null);
        this.logicalRef = parcel.readInt();
        this.clRef = parcel.readInt();
        String string = parcel.readString();
        this.clCode = string == null ? "" : string;
        this.ficheRef = parcel.readInt();
        this.andFicheNo = parcel.readString();
        this.ficheNo = parcel.readString();
        this.mSalesType = parcel.readInt();
        this.salesStatus = parcel.readInt();
        this.ficheCreateDate = parcel.readString();
        this.gDate = parcel.readString();
        this.ficheCreateDateInt = parcel.readInt();
        this.transferCount = parcel.readInt();
        this.printCount = parcel.readInt();
        this.grossTotal = parcel.readDouble();
        this.total = parcel.readDouble();
        this.totalNet = parcel.readDouble();
        this.totalVat = parcel.readDouble();
        this.discTotal = parcel.readDouble();
        this.totalExpenses = parcel.readDouble();
        this.orderType = parcel.readInt();
        this.latitude = parcel.readDouble();
        this.longitude = parcel.readDouble();
        this.totalNetVolume = parcel.readDouble();
        this.totalGrossVolume = parcel.readDouble();
        this.totalNetWeight = parcel.readDouble();
        this.totalGrossWeight = parcel.readDouble();
        this.totalSku = parcel.readDouble();
        this.campaign = parcel.readInt();
        this.salesCondition = parcel.readInt();
        FicheRefProp ficheRefProp = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.branch = ficheRefProp == null ? new FicheRefProp() : ficheRefProp;
        FicheRefProp ficheRefProp2 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.division = ficheRefProp2 == null ? new FicheRefProp() : ficheRefProp2;
        FicheRefProp ficheRefProp3 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.factory = ficheRefProp3 == null ? new FicheRefProp() : ficheRefProp3;
        FicheRefProp ficheRefProp4 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.wareHouse = ficheRefProp4 == null ? new FicheRefProp() : ficheRefProp4;
        FicheRefProp ficheRefProp5 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.returnWareHouse = ficheRefProp5 == null ? new FicheRefProp() : ficheRefProp5;
        FicheRefProp ficheRefProp6 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.sourceWareHouse = ficheRefProp6 == null ? new FicheRefProp() : ficheRefProp6;
        FicheRefProp ficheRefProp7 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.speCode = ficheRefProp7 == null ? new FicheRefProp() : ficheRefProp7;
        FicheRefProp ficheRefProp8 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.warrantyCode = ficheRefProp8 == null ? new FicheRefProp() : ficheRefProp8;
        FicheStringProp ficheStringProp = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.documentNo = ficheStringProp == null ? new FicheStringProp() : ficheStringProp;
        FicheStringProp ficheStringProp2 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.documentTrackingNo = ficheStringProp2 == null ? new FicheStringProp() : ficheStringProp2;
        FicheStringProp ficheStringProp3 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.customerOrderNo = ficheStringProp3 == null ? new FicheStringProp() : ficheStringProp3;
        FicheDateProp ficheDateProp = parcel.readParcelable(FicheDateProp.class.getClassLoader());
        this.deliveryDate = ficheDateProp == null ? new FicheDateProp() : ficheDateProp;
        FicheBooleanProp ficheBooleanProp = parcel.readParcelable(FicheBooleanProp.class.getClassLoader());
        this.paymentOrder = ficheBooleanProp == null ? new FicheBooleanProp() : ficheBooleanProp;
        FicheBooleanProp ficheBooleanProp2 = parcel.readParcelable(FicheBooleanProp.class.getClassLoader());
        this.consignee = ficheBooleanProp2 == null ? new FicheBooleanProp() : ficheBooleanProp2;
        FicheStringProp ficheStringProp4 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.explanation1 = ficheStringProp4 == null ? new FicheStringProp() : ficheStringProp4;
        FicheStringProp ficheStringProp5 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.explanation2 = ficheStringProp5 == null ? new FicheStringProp() : ficheStringProp5;
        FicheStringProp ficheStringProp6 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.explanation3 = ficheStringProp6 == null ? new FicheStringProp() : ficheStringProp6;
        FicheStringProp ficheStringProp7 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.explanation4 = ficheStringProp7 == null ? new FicheStringProp() : ficheStringProp7;
        FicheStringProp ficheStringProp8 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.explanation5 = ficheStringProp8 == null ? new FicheStringProp() : ficheStringProp8;
        FicheStringProp ficheStringProp9 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.explanation6 = ficheStringProp9 == null ? new FicheStringProp() : ficheStringProp9;
        FicheStringProp ficheStringProp10 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.explanation7 = ficheStringProp10 == null ? new FicheStringProp() : ficheStringProp10;
        FicheStringProp ficheStringProp11 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.explanation8 = ficheStringProp11 == null ? new FicheStringProp() : ficheStringProp11;
        FicheStringProp ficheStringProp12 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.explanation9 = ficheStringProp12 == null ? new FicheStringProp() : ficheStringProp12;
        FicheStringProp ficheStringProp13 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.explanation10 = ficheStringProp13 == null ? new FicheStringProp() : ficheStringProp13;
        FicheDiscountRefProp ficheDiscountRefProp = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        this.shipAccount = ficheDiscountRefProp == null ? new FicheDiscountRefProp() : ficheDiscountRefProp;
        FicheDiscountRefProp ficheDiscountRefProp2 = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        this.shipAddress = ficheDiscountRefProp2 == null ? new FicheDiscountRefProp() : ficheDiscountRefProp2;
        FicheRefProp ficheRefProp9 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.shipAgent = ficheRefProp9 == null ? new FicheRefProp() : ficheRefProp9;
        FicheRefProp ficheRefProp10 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.shipType = ficheRefProp10 == null ? new FicheRefProp() : ficheRefProp10;
        this.invoiceAddress = parcel.readString();
        FicheRefProp ficheRefProp11 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.tradeGroup = ficheRefProp11 == null ? new FicheRefProp() : ficheRefProp11;
        FicheDiscountRefProp ficheDiscountRefProp3 = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        this.payPlan = ficheDiscountRefProp3 == null ? new FicheDiscountRefProp() : ficheDiscountRefProp3;
        FicheDiscountRefProp ficheDiscountRefProp4 = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        this.projectCode = ficheDiscountRefProp4 == null ? new FicheDiscountRefProp() : ficheDiscountRefProp4;
        Parcelable parcelable = parcel.readParcelable(SalesFicheDiscountProps.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable);
        this.salesFicheDiscountProps = (SalesFicheDiscountProps) parcelable;
        this.isNotUseGattribKdv = parcel.readByte() != 0;
        this.isEdit = parcel.readInt();
        this.mSalesDetailList = parcel.createTypedArrayList(SalesDetail.CREATOR);
        FicheBooleanProp ficheBooleanProp3 = parcel.readParcelable(FicheBooleanProp.class.getClassLoader());
        this.includeVat = ficheBooleanProp3 == null ? new FicheBooleanProp() : ficheBooleanProp3;
        FicheStringProp ficheStringProp14 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.eInvoiceSGKDocumentNo = ficheStringProp14 == null ? new FicheStringProp() : ficheStringProp14;
        FicheStringProp ficheStringProp15 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.taxPayerCode = ficheStringProp15 == null ? new FicheStringProp() : ficheStringProp15;
        FicheStringProp ficheStringProp16 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.taxPayerName = ficheStringProp16 == null ? new FicheStringProp() : ficheStringProp16;
        FicheRefProp ficheRefProp12 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.eInvoiceTypSgk = ficheRefProp12 == null ? new FicheRefProp() : ficheRefProp12;
        FicheDateProp ficheDateProp2 = parcel.readParcelable(FicheDateProp.class.getClassLoader());
        this.beginDate = ficheDateProp2 == null ? new FicheDateProp() : ficheDateProp2;
        FicheDateProp ficheDateProp3 = parcel.readParcelable(FicheDateProp.class.getClassLoader());
        this.endDate = ficheDateProp3 == null ? new FicheDateProp() : ficheDateProp3;
        this.orderRef = parcel.readInt();
        FicheDiscountRefProp ficheDiscountRefProp5 = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        this.cabin = ficheDiscountRefProp5 == null ? new FicheDiscountRefProp() : ficheDiscountRefProp5;
        FicheDiscountRefProp ficheDiscountRefProp6 = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        this.firstSpeCode = ficheDiscountRefProp6 == null ? new FicheDiscountRefProp() : ficheDiscountRefProp6;
        FicheDiscountRefProp ficheDiscountRefProp7 = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        this.secondSpeCode = ficheDiscountRefProp7 == null ? new FicheDiscountRefProp() : ficheDiscountRefProp7;
        FicheRefProp ficheRefProp13 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.transferSourceWareHouse = ficheRefProp13 == null ? new FicheRefProp() : ficheRefProp13;
        FicheRefProp ficheRefProp14 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.transferSourceFactory = ficheRefProp14 == null ? new FicheRefProp() : ficheRefProp14;
        FicheRefProp ficheRefProp15 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.transferSourceBranch = ficheRefProp15 == null ? new FicheRefProp() : ficheRefProp15;
        FicheRefProp ficheRefProp16 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.transferSourceCostGrp = ficheRefProp16 == null ? new FicheRefProp() : ficheRefProp16;
        FicheRefProp ficheRefProp17 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.transferSourceDivision = ficheRefProp17 == null ? new FicheRefProp() : ficheRefProp17;
        FicheRefProp ficheRefProp18 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.eDocSerial = ficheRefProp18 == null ? new FicheRefProp() : ficheRefProp18;
        FicheBooleanProp ficheBooleanProp4 = parcel.readParcelable(FicheBooleanProp.class.getClassLoader());
        this.eDespatch = ficheBooleanProp4 == null ? new FicheBooleanProp() : ficheBooleanProp4;
        FicheBooleanProp ficheBooleanProp5 = parcel.readParcelable(FicheBooleanProp.class.getClassLoader());
        this.insteadOfEDespatch = ficheBooleanProp5 == null ? new FicheBooleanProp() : ficheBooleanProp5;
        this.eDispatchAdditionalInfo = parcel.readParcelable(EDispatchAdditionalInfo.class.getClassLoader());
        FicheRefProp ficheRefProp19 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.erpInvoiceType = ficheRefProp19 == null ? new FicheRefProp() : ficheRefProp19;
        FicheDateProp ficheDateProp4 = parcel.readParcelable(FicheDateProp.class.getClassLoader());
        this.dueDate = ficheDateProp4 == null ? new FicheDateProp() : ficheDateProp4;
        FicheBooleanProp ficheBooleanProp6 = parcel.readParcelable(FicheBooleanProp.class.getClassLoader());
        this.reserved = ficheBooleanProp6 == null ? new FicheBooleanProp() : ficheBooleanProp6;
        this.visitInfoId = parcel.readInt();
        this.orderFicheStatus = parcel.readString();
    }

    public  ArrayList<Serilot> getAllSeriLots(int r5) {
        ArrayList<Serilot> arrayList = new ArrayList<>();
        ArrayList<SalesDetail> arrayList2 = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList2);
        int size = arrayList2.size();
        for (int r2 = 0; r2 < size; r2++) {
            if (r2 != r5) {
                ArrayList<SalesDetail> arrayList3 = this.mSalesDetailList;
                Intrinsics.checkNotNull(arrayList3);
                arrayList.addAll(arrayList3.get(r2).getSalesSerialLots());
            }
        }
        return arrayList;
    }

    public  EmailObject replaceHtml(EmailTemplateType templateType, CustomerBeforeBalance customerBeforeBalance) {
        Intrinsics.checkNotNullParameter(templateType, "templateType");
        EmailObject emailObjectRelaceFicheHTML = relaceFicheHTML(this, customerBeforeBalance);
        Intrinsics.checkNotNullExpressionValue(emailObjectRelaceFicheHTML, "relaceFicheHTML(...)");
        return emailObjectRelaceFicheHTML;
    }

    public  boolean hasCampaignApplied() {
        boolean zHasCampaignApplied = this.salesFicheDiscountProps.hasCampaignApplied();
        if (zHasCampaignApplied) {
            return zHasCampaignApplied;
        }
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList);
        Iterator<SalesDetail> it = arrayList.iterator();
        while (it.hasNext()) {
            SalesDetail next = it.next();
            if (next.lineType == LineType.PROMOTION.value) {
                if (next.hasCampaign()) {
                    return true;
                }
            } else if (next.getSalesFicheDiscountProps().hasCampaignApplied()) {
                return true;
            }
        }
        return zHasCampaignApplied;
    }

    public  void clearCampaign() {
        this.salesFicheDiscountProps.clearCampaign();
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList);
        int size = arrayList.size() - 1;
        if (size < 0) {
            return;
        }
        while (true) {
            int r1 = size - 1;
            ArrayList<SalesDetail> arrayList2 = this.mSalesDetailList;
            Intrinsics.checkNotNull(arrayList2);
            SalesDetail salesDetail = arrayList2.get(size);
            Intrinsics.checkNotNullExpressionValue(salesDetail, "get(...)");
            SalesDetail salesDetail2 = salesDetail;
            if (salesDetail2.lineType == LineType.PROMOTION.value) {
                if (salesDetail2.hasCampaign()) {
                    ArrayList<SalesDetail> arrayList3 = this.mSalesDetailList;
                    Intrinsics.checkNotNull(arrayList3);
                    arrayList3.remove(size);
                }
            } else {
                salesDetail2.getSalesFicheDiscountProps().clearCampaign();
                salesDetail2.clearCampaign();
            }
            if (r1 < 0) {
                return;
            } else {
                size = r1;
            }
        }
    }

    public  ValidationResult hasDetailsWithZeroQuantity() {
        ValidationResult validationResult = new ValidationResult();
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList);
        Iterator<SalesDetail> it = arrayList.iterator();
        String string = "";
        while (it.hasNext()) {
            SalesDetail next = it.next();
            if (!next.amountControl()) {
                String code = TextUtils.isEmpty(next.getName()) ? next.getCode() : next.getName();
                if (!validationResult.getKeyWords().contains(code)) {
                    validationResult.getKeyWords().add(code);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(string);
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String stringResource = ContextUtils.getStringResource(R.string.exp_33_sales_detail_amount_not_enter);
                Intrinsics.checkNotNullExpressionValue(stringResource, "getStringResource(...)");
                String str = String.format(stringResource, Arrays.copyOf(new Object[]{TextUtils.isEmpty(next.getName()) ? next.getCode() : next.getName()}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                sb.append(str);
                sb.append('\n');
                string = sb.toString();
                validationResult.setSuccess(false);
            }
        }
        validationResult.setErrorMessage(string);
        return validationResult;
    }

    public  void removeLinesWithZeroQuantity() {
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList);
        int size = arrayList.size() - 1;
        if (size < 0) {
            return;
        }
        while (true) {
            int r1 = size - 1;
            ArrayList<SalesDetail> arrayList2 = this.mSalesDetailList;
            Intrinsics.checkNotNull(arrayList2);
            SalesDetail salesDetail = arrayList2.get(size);
            Intrinsics.checkNotNullExpressionValue(salesDetail, "get(...)");
            if (!salesDetail.amountControl()) {
                ArrayList<SalesDetail> arrayList3 = this.mSalesDetailList;
                Intrinsics.checkNotNull(arrayList3);
                arrayList3.remove(size);
            }
            if (r1 < 0) {
                return;
            } else {
                size = r1;
            }
        }
    }

    public  SalesDetail findDetailByLineNr(int r3) {
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList);
        Iterator<SalesDetail> it = arrayList.iterator();
        while (it.hasNext()) {
            SalesDetail next = it.next();
            if (next.getLineNr() == r3) {
                return next;
            }
        }
        return null;
    }

    public  boolean convertWhouseItemsToSalesDetails(List<WarehouseItem> warehouseItems) {
        int r18;
        double onHand;
        String strConvertYMDToDMY;
        List listEmptyList;
        Intrinsics.checkNotNullParameter(warehouseItems, "warehouseItems");
        ArrayList arrayList = new ArrayList();
        try {
            try {
                Stream<WarehouseItem> stream = warehouseItems.stream();
                final SalesconvertWhouseItemsToSalesDetailswithoutTrackingItems1 salesconvertWhouseItemsToSalesDetailswithoutTrackingItems1 = new Function1<WarehouseItem, Boolean>() {
                    @Override
                    public Boolean invoke(Object p1) {
                        return null;
                    }
                    public Boolean invoke(WarehouseItem warehouseItem) {
                        Intrinsics.checkNotNullParameter(warehouseItem, "warehouseItem");
                        return Boolean.valueOf(warehouseItem.getTrackType() == 0 && warehouseItem.getLockTracking() == 0);
                    }
                };
                Stream<WarehouseItem> streamFilter = stream.filter(new Predicate() {
                    public boolean test(Object obj) {
                        return Sales.convertWhouseItemsToSalesDetailslambda2(salesconvertWhouseItemsToSalesDetailswithoutTrackingItems1, obj);
                    }
                });
                final SalesconvertWhouseItemsToSalesDetailswithoutTrackingItems2 salesconvertWhouseItemsToSalesDetailswithoutTrackingItems2 = new Function1<WarehouseItem, Integer>() {
                    @Override
                    public Integer invoke(Object p1) {
                        return 0;
                    }

                    public Integer invoke(WarehouseItem obj) {
                        Intrinsics.checkNotNullParameter(obj, "obj");
                        return Integer.valueOf(obj.getStockRef());
                    }
                };
                int r6 = 1;
                for (Object warehouseItem : (List) streamFilter.sorted(Comparator.comparing(new Function() {
                    @Override
                    public Object apply(Object obj) {
                        return Sales.convertWhouseItemsToSalesDetailslambda3(salesconvertWhouseItemsToSalesDetailswithoutTrackingItems2, obj);
                    }
                })).collect(Collectors.toList())) {
                    Intrinsics.checkNotNull(warehouseItem);
                    arrayList.add(salesDetailFromWarehouseItem((WarehouseItem) warehouseItem, r6));
                    r6++;
                }
                Stream<WarehouseItem> stream2 = warehouseItems.stream();
                final SalesconvertWhouseItemsToSalesDetailslistMap1 salesconvertWhouseItemsToSalesDetailslistMap1 = new Function1<WarehouseItem, Boolean>() {
                    @Override
                    public Boolean invoke(Object p1) {
                        return null;
                    }
                    public Boolean invoke(WarehouseItem warehouseItem2) {
                        Intrinsics.checkNotNullParameter(warehouseItem2, "warehouseItem");
                        boolean z = warehouseItem2.getTrackType() != 0 || warehouseItem2.getLockTracking() == 1;
                        return Boolean.valueOf(z);
                    }
                };
                Stream<WarehouseItem> streamFilter2 = stream2.filter(new Predicate() { // from class: com.proje.mobilesales.features.sales.model.SalesExternalSyntheticLambda3
                    @Override // java.util.function.Predicate
                    public boolean test(Object obj) {
                        return Sales.convertWhouseItemsToSalesDetailslambda4(salesconvertWhouseItemsToSalesDetailslistMap1, obj);
                    }
                });
                final SalesconvertWhouseItemsToSalesDetailslistMap2 salesconvertWhouseItemsToSalesDetailslistMap2 = new Function1<WarehouseItem, Integer>() {
                    @Override
                    public Integer invoke(Object p1) {
                        return 0;
                    }
                    public Integer invoke(WarehouseItem obj) {
                        Intrinsics.checkNotNullParameter(obj, "obj");
                        return Integer.valueOf(obj.getStockRef());
                    }
                };
                Stream<WarehouseItem> streamSorted = streamFilter2.sorted(Comparator.comparing(new Function() {
                    @Override
                    public Object apply(Object obj) {
                        return Sales.convertWhouseItemsToSalesDetailslambda5(salesconvertWhouseItemsToSalesDetailslistMap2, obj);
                    }
                    @Override
                    public Object apply(Object obj) {
                        return Sales.convertWhouseItemsToSalesDetailslambda5(salesconvertWhouseItemsToSalesDetailslistMap2, obj);
                    }
                }));
                final SalesconvertWhouseItemsToSalesDetailslistMap3 salesconvertWhouseItemsToSalesDetailslistMap3 = new Function1<WarehouseItem, Integer>() {
                    @Override
                    public Integer invoke(Object p1) {
                        return 0;
                    }
                    public Integer invoke(WarehouseItem obj) {
                        Intrinsics.checkNotNullParameter(obj, "obj");
                        return Integer.valueOf(obj.getStockRef());
                    }
                    public Integer invoke(WarehouseItem obj) {
                        Intrinsics.checkNotNullParameter(obj, "obj");
                        return Integer.valueOf(obj.getStockRef());
                    }
                };
                Function function = new Function() {
                    public Object apply(Object obj) {
                        return Sales.convertWhouseItemsToSalesDetailslambda6(salesconvertWhouseItemsToSalesDetailslistMap3, obj);
                    }
                };
                final SalesconvertWhouseItemsToSalesDetailslistMap4 salesconvertWhouseItemsToSalesDetailslistMap4 = new Function1<WarehouseItem, Integer>() {
                    @Override
                    public Integer invoke(Object p1) {
                        return 0;
                    }
                    public Integer invoke(WarehouseItem obj) {
                        Intrinsics.checkNotNullParameter(obj, "obj");
                        return Integer.valueOf(obj.getVariantRef());
                    }
                };
                Collector collectorGroupingBy = Collectors.groupingBy(new Function() {
                    public Object apply(Object obj) {
                        return Sales.convertWhouseItemsToSalesDetailslambda7(salesconvertWhouseItemsToSalesDetailslistMap4, obj);
                    }
                });
                Intrinsics.checkNotNull(collectorGroupingBy, "null cannot be cast to non-null type java.util.stream.Collector<in com.proje.mobilesales.features.model.WarehouseItem, kotlin.Int, kotlin.collections.Map<out kotlin.Int, kotlin.collections.List<com.proje.mobilesales.features.model.WarehouseItem>>>");
                Map map = (Map) streamSorted.collect(Collectors.groupingBy(function, collectorGroupingBy));
                Iterator it = map.keySet().iterator();
                while (it.hasNext()) {
                    Object obj = map.get(it.next());
                    Intrinsics.checkNotNull(obj);
                    for (List<WarehouseItem> list : ((Map) obj).values()) {
                        if (!list.isEmpty()) {
                            SalesDetail salesDetailSalesDetailFromWarehouseItem = salesDetailFromWarehouseItem(list.get(0), r6);
                            salesDetailSalesDetailFromWarehouseItem.setSalesSerialLots(new ArrayList<>());
                            if (salesDetailSalesDetailFromWarehouseItem.getTrackType() == TrackType.SERIAL_GROUP.getType()) {
                                ArrayList arrayList2 = new ArrayList();
                                for (WarehouseItem warehouseItem2 : list) {
                                    CheckSeriGroup checkSeriGroup = new CheckSeriGroup();
                                    checkSeriGroup.setOrgGrpBegCode(warehouseItem2.getGrpBegCode());
                                    checkSeriGroup.setOrgGrpEndCode(warehouseItem2.getGrpEndCode());
                                    checkSeriGroup.setUsedGrpBegCode(warehouseItem2.getUsedGrpBegCode());
                                    checkSeriGroup.setUsedGrpEndCode(warehouseItem2.getUsedGrpEndCode());
                                    checkSeriGroup.setRemAmount(warehouseItem2.getOnHand());
                                    checkSeriGroup.setOrgAmount(warehouseItem2.getMainAmount());
                                    checkSeriGroup.setExpDate(warehouseItem2.getExpDate());
                                    checkSeriGroup.setUnit(warehouseItem2.getUnitCode());
                                    checkSeriGroup.setLogicalRef(warehouseItem2.getSltLogicalRef());
                                    checkSeriGroup.setStTransRef(warehouseItem2.getStTransRef());
                                    checkSeriGroup.setSourceUnitRef(warehouseItem2.getUnitRef());
                                    checkSeriGroup.setSeriLotNo(warehouseItem2.getSeriLotn());
                                    arrayList2.add(checkSeriGroup);
                                }
                                Iterator<CheckSeriGroup> it2 = SeriLotUtils.extractAvailableSeriGroups(arrayList2, "", null).iterator();
                                onHand = 0.0d;
                                while (it2.hasNext()) {
                                    CheckSeriGroup next = it2.next();
                                    Serilot serilot = new Serilot(0, 0, 0, null, 0.0d, null, null, null, 0.0d, null, null, null, 0, 0, 0, 0, 65535, null);
                                    serilot.f1277id = next.getId();
                                    serilot.logicalRef = next.getLogicalRef();
                                    serilot.amount = next.getRemAmount();
                                    serilot.code = next.getSeriLotNo();
                                    serilot.expDate = next.getExpDate();
                                    String grpBegCode = next.getGrpBegCode();
                                    Intrinsics.checkNotNullExpressionValue(grpBegCode, "getGrpBegCode(...)");
                                    serilot.grpBegCode = grpBegCode;
                                    String grpEndCode = next.getGrpEndCode();
                                    Intrinsics.checkNotNullExpressionValue(grpEndCode, "getGrpEndCode(...)");
                                    serilot.grpEndCode = grpEndCode;
                                    serilot.unit = next.getUnit();
                                    serilot.reAmount = next.getRemAmount();
                                    serilot.detailRef = next.getDetailRef();
                                    String name = next.getName();
                                    Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                                    serilot.name = name;
                                    String locationCode = next.getLocationCode();
                                    Intrinsics.checkNotNullExpressionValue(locationCode, "getLocationCode(...)");
                                    serilot.locationCode = locationCode;
                                    serilot.stTransRef = next.getStTransRef();
                                    serilot.sourceUnitRef = next.getSourceUnitRef();
                                    onHand += serilot.amount;
                                    salesDetailSalesDetailFromWarehouseItem.getSalesSerialLots().add(serilot);
                                    r6 = r6;
                                }
                                r18 = r6;
                            } else {
                                r18 = r6;
                                onHand = 0.0d;
                                for (WarehouseItem warehouseItem3 : list) {
                                    Serilot serilot2 = new Serilot(0, 0, 0, null, 0.0d, null, null, null, 0.0d, null, null, null, 0, 0, 0, 0, 65535, null);
                                    serilot2.logicalRef = warehouseItem3.getSltLogicalRef();
                                    serilot2.stTransRef = warehouseItem3.getStTransRef();
                                    serilot2.amount = warehouseItem3.getOnHand();
                                    serilot2.reAmount = warehouseItem3.getOnHand();
                                    serilot2.code = warehouseItem3.getSeriLotn();
                                    String locCode = warehouseItem3.getLocCode();
                                    if (locCode == null) {
                                        locCode = "";
                                    }
                                    serilot2.locationCode = locCode;
                                    serilot2.unit = warehouseItem3.getUnitCode();
                                    if (TextUtils.isEmpty(warehouseItem3.getExpDate())) {
                                        strConvertYMDToDMY = "";
                                    } else {
                                        List<String> listSplit = new Regex(ExifInterface.GPS_DIRECTION_TRUE).split(warehouseItem3.getExpDate(), 0);
                                        if (!listSplit.isEmpty()) {
                                            ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
                                            while (listIterator.hasPrevious()) {
                                                if (listIterator.previous().length() != 0) {
                                                    listEmptyList = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                                                    break;
                                                }
                                            }
                                            listEmptyList = CollectionsKt.emptyList();
                                            strConvertYMDToDMY = DateAndTimeUtils.convertYMDToDMY(((String[]) listEmptyList.toArray(new String[0]))[0]);
                                        } else {
                                            listEmptyList = CollectionsKt.emptyList();
                                            strConvertYMDToDMY = DateAndTimeUtils.convertYMDToDMY(((String[]) listEmptyList.toArray(new String[0]))[0]);
                                        }
                                    }
                                    serilot2.expDate = strConvertYMDToDMY;
                                    serilot2.sourceUnitRef = warehouseItem3.getUnitRef();
                                    onHand += warehouseItem3.getOnHand();
                                    salesDetailSalesDetailFromWarehouseItem.getSalesSerialLots().add(serilot2);
                                }
                            }
                            salesDetailSalesDetailFromWarehouseItem.setActualStock(onHand);
                            salesDetailSalesDetailFromWarehouseItem.setAmount(new FicheStringProp(StringUtils.convertDoubleToString(Double.valueOf(onHand))));
                            salesDetailSalesDetailFromWarehouseItem.serialLotCodeList = StringUtils.getSerialLotCode(salesDetailSalesDetailFromWarehouseItem.getSalesSerialLots(), salesDetailSalesDetailFromWarehouseItem.getTrackType());
                            arrayList.add(salesDetailSalesDetailFromWarehouseItem);
                            r6 = r18 + 1;
                        }
                    }
                }
                ArrayList<SalesDetail> arrayList3 = this.mSalesDetailList;
                Intrinsics.checkNotNull(arrayList3);
                arrayList3.clear();
                ArrayList<SalesDetail> arrayList4 = this.mSalesDetailList;
                Intrinsics.checkNotNull(arrayList4);
                arrayList4.addAll(arrayList);
                return true;
            } catch (Exception e2) {
                Log.e("WhItemToSales", "onGetWarehouseItems", e2);
                return false;
            }
        } catch (Throwable unused) {
            return false;
        }
    }
    public static boolean convertWhouseItemsToSalesDetailslambda2(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        return ((Boolean) tmp0.invoke(obj)).booleanValue();
    }
    public static Integer convertWhouseItemsToSalesDetailslambda3(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        return (Integer) tmp0.invoke(obj);
    }
    public static boolean convertWhouseItemsToSalesDetailslambda4(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        return ((Boolean) tmp0.invoke(obj)).booleanValue();
    }
    public static Integer convertWhouseItemsToSalesDetailslambda5(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        return (Integer) tmp0.invoke(obj);
    }
    public static Integer convertWhouseItemsToSalesDetailslambda6(SalesconvertWhouseItemsToSalesDetailslistMap3 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        return (Integer) tmp0.invoke(obj);
    }
    public static Integer convertWhouseItemsToSalesDetailslambda7(SalesconvertWhouseItemsToSalesDetailslistMap4 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        return (Integer) tmp0.invoke(obj);
    }
    private SalesDetail salesDetailFromWarehouseItem(WarehouseItem warehouseItem, int r128) {
        SalesDetail salesDetail = new SalesDetail(0L, 0, 0, null, 0, 0, null, 0, false, false, 0, 0, null, 0, 0, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0, null, 0.0d, null, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, false, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, null, null, 0, false, 0, 0.0d, 0.0d, 0, null, 0, 0, 0, 0, false, null, null, 0, false, null, null, null, 0, null, -1, -1, Integer.MAX_VALUE, null);
        salesDetail.setItemRef(warehouseItem.getStockRef());
        salesDetail.setCode(warehouseItem.getItemCode());
        salesDetail.setName(warehouseItem.getItemName());
        salesDetail.setUnit(new FicheDiscountRefProp(warehouseItem.getUnitRef(), -1, warehouseItem.getUnitCode(), warehouseItem.getUnitCode()));
        salesDetail.setConvFact1(warehouseItem.getConvFact1());
        salesDetail.setConvFact2(warehouseItem.getConvFact2());
        salesDetail.setTrackType(warehouseItem.getTrackType());
        salesDetail.setLocTracking(warehouseItem.getLockTracking() == 1);
        salesDetail.setCardType(warehouseItem.getCardType());
        salesDetail.setDivUnit(warehouseItem.getDivUnit() == 1);
        salesDetail.setLineNr(r128);
        salesDetail.setProduct(true);
        salesDetail.setLineNumber(r128);
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        salesDetail.guid = string;
        salesDetail.setActualStock(warehouseItem.getOnHand());
        salesDetail.setSalesType(this.mSalesType);
        salesDetail.setAmount(new FicheStringProp(StringUtils.convertDoubleToString(Double.valueOf(warehouseItem.getOnHand()))));
        if (warehouseItem.getVariantRef() > 0) {
            salesDetail.setVariant(new FicheDiscountRefProp(warehouseItem.getVariantRef(), -1, warehouseItem.getVariantName(), warehouseItem.getVariantCode()));
            salesDetail.setHasVariant(true);
        }
        return salesDetail;
    }
    public double getTotalAmount() {
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList);
        Iterator<SalesDetail> it = arrayList.iterator();
        double definitionDouble = 0.0d;
        while (it.hasNext()) {
            definitionDouble += it.next().getAmount().getDefinitionDouble();
        }
        return definitionDouble;
    }
    public static boolean moveGeneralPromotionLineToTheLastlambda9(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        return ((Boolean) tmp0.invoke(obj)).booleanValue();
    }
    public void moveGeneralPromotionLineToTheLast() {
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        Intrinsics.checkNotNull(arrayList);
        Stream<SalesDetail> stream = arrayList.stream();
        final SalesmoveGeneralPromotionLineToTheLastgeneralPromotionItems1 salesmoveGeneralPromotionLineToTheLastgeneralPromotionItems1 = new Function1<SalesDetail, Boolean>() { // from class: com.proje.mobilesales.features.sales.model.SalesmoveGeneralPromotionLineToTheLastgeneralPromotionItems1
            @Override // kotlin.jvm.functions.Function1
            public Boolean invoke(SalesDetail salesDetail) {
                Intrinsics.checkNotNullParameter(salesDetail, "salesDetail");
                return Boolean.valueOf(salesDetail.lineType == LineType.PROMOTION.value && salesDetail.getGlobalLineType() == GlobalLineType.GENERAL.getValue());
            }
        };
        List<SalesDetail> list = (List) stream.filter(new Predicate() { // from class: com.proje.mobilesales.features.sales.model.SalesExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public boolean test(Object obj) {
                return Sales.moveGeneralPromotionLineToTheLastlambda9(salesmoveGeneralPromotionLineToTheLastgeneralPromotionItems1, obj);
            }
        }).collect(Collectors.toList());
        if (list == null || list.size() == 0) {
            return;
        }
        for (SalesDetail salesDetail : list) {
            ArrayList<SalesDetail> arrayList2 = this.mSalesDetailList;
            Intrinsics.checkNotNull(arrayList2);
            int r2 = arrayList2.indexOf(salesDetail);
            ArrayList<SalesDetail> arrayList3 = this.mSalesDetailList;
            Intrinsics.checkNotNull(arrayList3);
            if (r2 <= arrayList3.size() - list.size()) {
                for (SalesDetail salesDetail2 : list) {
                    ArrayList<SalesDetail> arrayList4 = this.mSalesDetailList;
                    Intrinsics.checkNotNull(arrayList4);
                    arrayList4.remove(salesDetail2);
                }
                for (SalesDetail salesDetail3 : list) {
                    ArrayList<SalesDetail> arrayList5 = this.mSalesDetailList;
                    Intrinsics.checkNotNull(arrayList5);
                    arrayList5.add(salesDetail3);
                }
                return;
            }
        }
    }
    public ValidationResult validateOrderMinLimit() {
        ValidationResult validationResult = new ValidationResult();
        if (getmSalesType() == SalesType.ORDER && this.viewModel.orderMinLimit() > 0.0d && this.totalNet < this.viewModel.orderMinLimit()) {
            validationResult.setSuccess(false);
            validationResult.setErrorMessage(ContextUtils.getStringResource(R.string.str_order_min_limit_validation_error));
        }
        return validationResult;
    }
    public void onWarehouseChange() {
        ArrayList<SalesDetail> arrayList = this.mSalesDetailList;
        if (arrayList != null) {
            Intrinsics.checkNotNull(arrayList);
            if (arrayList.isEmpty()) {
                return;
            }
            ArrayList<SalesDetail> arrayList2 = this.mSalesDetailList;
            Intrinsics.checkNotNull(arrayList2);
            Iterator<SalesDetail> it = arrayList2.iterator();
            while (it.hasNext()) {
                SalesDetail next = it.next();
                if (SalesUtils.isSalesTypeDemand(getmSalesType())) {
                    FicheStringProp.setDefinition(this.sourceWareHouse.getDefinition());
                    next.getWareHouse().setWhich(this.sourceWareHouse.getWhich());
                    next.getWareHouse().setLogicalRef(this.sourceWareHouse.getLogicalRef());
                } else if (SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(getmSalesType())) {
                    FicheStringProp.setDefinition(this.returnWareHouse.getDefinition());
                    next.getWareHouse().setWhich(this.returnWareHouse.getWhich());
                    next.getWareHouse().setLogicalRef(this.returnWareHouse.getLogicalRef());
                } else {
                    FicheStringProp.setDefinition(this.wareHouse.getDefinition());
                    next.getWareHouse().setWhich(this.wareHouse.getWhich());
                    next.getWareHouse().setLogicalRef(this.wareHouse.getLogicalRef());
                }
            }
        }
    }
    public static final class CREATOR implements Creator<Sales> {
        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }

        /*  WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Sales createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new Sales(source);
        }

        /*  WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Sales[] newArray(int r1) {
            return new Sales[r1];
        }
    }
    public void writeToParcel(Parcel dest, int r4) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeInt(this.logicalRef);
        dest.writeInt(this.clRef);
        dest.writeString(this.clCode);
        dest.writeInt(this.ficheRef);
        dest.writeString(this.andFicheNo);
        dest.writeString(this.ficheNo);
        dest.writeInt(this.mSalesType);
        dest.writeInt(this.salesStatus);
        dest.writeString(this.ficheCreateDate);
        dest.writeString(this.gDate);
        dest.writeInt(this.ficheCreateDateInt);
        dest.writeInt(this.transferCount);
        dest.writeInt(this.printCount);
        dest.writeDouble(this.grossTotal);
        dest.writeDouble(this.total);
        dest.writeDouble(this.totalNet);
        dest.writeDouble(this.totalVat);
        dest.writeDouble(this.discTotal);
        dest.writeDouble(this.totalExpenses);
        dest.writeInt(this.orderType);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.totalNetVolume);
        dest.writeDouble(this.totalGrossVolume);
        dest.writeDouble(this.totalNetWeight);
        dest.writeDouble(this.totalGrossWeight);
        dest.writeDouble(this.totalSku);
        dest.writeInt(this.campaign);
        dest.writeInt(this.salesCondition);
        dest.writeParcelable(this.branch, r4);
        dest.writeParcelable(this.division, r4);
        dest.writeParcelable(this.factory, r4);
        dest.writeParcelable(this.wareHouse, r4);
        dest.writeParcelable(this.returnWareHouse, r4);
        dest.writeParcelable(this.sourceWareHouse, r4);
        dest.writeParcelable(this.speCode, r4);
        dest.writeParcelable(this.warrantyCode, r4);
        dest.writeParcelable(this.documentNo, r4);
        dest.writeParcelable(this.documentTrackingNo, r4);
        dest.writeParcelable(this.customerOrderNo, r4);
        dest.writeParcelable(this.deliveryDate, r4);
        dest.writeParcelable(this.paymentOrder, r4);
        dest.writeParcelable(this.consignee, r4);
        dest.writeParcelable(this.explanation1, r4);
        dest.writeParcelable(this.explanation2, r4);
        dest.writeParcelable(this.explanation3, r4);
        dest.writeParcelable(this.explanation4, r4);
        dest.writeParcelable(this.explanation5, r4);
        dest.writeParcelable(this.explanation6, r4);
        dest.writeParcelable(this.explanation7, r4);
        dest.writeParcelable(this.explanation8, r4);
        dest.writeParcelable(this.explanation9, r4);
        dest.writeParcelable(this.explanation10, r4);
        dest.writeParcelable(this.shipAccount, r4);
        dest.writeParcelable(this.shipAddress, r4);
        dest.writeParcelable(this.shipAgent, r4);
        dest.writeParcelable(this.shipType, r4);
        dest.writeString(this.invoiceAddress);
        dest.writeParcelable(this.tradeGroup, r4);
        dest.writeParcelable(this.payPlan, r4);
        dest.writeParcelable(this.projectCode, r4);
        dest.writeParcelable(this.salesFicheDiscountProps, r4);
        dest.writeByte(this.isNotUseGattribKdv ? (byte) 1 : (byte) 0);
        dest.writeInt(this.isEdit);
        dest.writeTypedList(this.mSalesDetailList);
        dest.writeParcelable(this.includeVat, r4);
        dest.writeParcelable(this.eInvoiceSGKDocumentNo, r4);
        dest.writeParcelable(this.taxPayerCode, r4);
        dest.writeParcelable(this.taxPayerName, r4);
        dest.writeParcelable(this.eInvoiceTypSgk, r4);
        dest.writeParcelable(this.beginDate, r4);
        dest.writeParcelable(this.endDate, r4);
        dest.writeInt(this.orderRef);
        dest.writeParcelable(this.cabin, r4);
        dest.writeParcelable(this.firstSpeCode, r4);
        dest.writeParcelable(this.secondSpeCode, r4);
        dest.writeParcelable(this.transferSourceWareHouse, r4);
        dest.writeParcelable(this.transferSourceFactory, r4);
        dest.writeParcelable(this.transferSourceBranch, r4);
        dest.writeParcelable(this.transferSourceCostGrp, r4);
        dest.writeParcelable(this.transferSourceDivision, r4);
        dest.writeParcelable(this.eDocSerial, r4);
        dest.writeParcelable(this.eDespatch, r4);
        dest.writeParcelable(this.insteadOfEDespatch, r4);
        dest.writeParcelable(this.eDispatchAdditionalInfo, r4);
        dest.writeParcelable(this.erpInvoiceType, r4);
        dest.writeParcelable(this.dueDate, r4);
        dest.writeParcelable(this.reserved, r4);
        dest.writeInt(this.visitInfoId);
        dest.writeString(this.orderFicheStatus);
    }
}
