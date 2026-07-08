package com.proje.mobilesales.features.sales.model.fiche;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
 
public  class SalesFicheHeaderFields extends SalesFicheFields {
    public static  CREATOR CREATOR = new CREATOR(null);
    private int defaultBranch;
    private int defaultDivision;
    private String defaultDocumentNo;
    private int defaultEInvoiceTypSGK;
    private String defaultExplanation1;
    private int defaultFactory;
    private String defaultProject;
    private int defaultReturnWareHouse;
    private String defaultSpeCode;
    private String defaultTaxPayerCode;
    private String defaultTaxPayerName;
    private String defaultTradeGroup;
    private int defaultWareHouse;
    private String defaultWarrantyCode;
    private boolean isBranch;
    private boolean isConsignee;
    private boolean isCustomerOrderNo;
    private boolean isDivision;
    private boolean isDocumentNo;
    private boolean isDocumentTrackingNo;
    private boolean isExplanation1;
    private boolean isExplanation10;
    private boolean isExplanation2;
    private boolean isExplanation3;
    private boolean isExplanation4;
    private boolean isExplanation5;
    private boolean isExplanation6;
    private boolean isExplanation7;
    private boolean isExplanation8;
    private boolean isExplanation9;
    private boolean isFactory;
    private boolean isIncludeVAT;
    private boolean isProject;
    private boolean isReserveOrder;
    private boolean isShipAccount;
    private boolean isShipAddress;
    private boolean isShipAgent;
    private boolean isShipType;
    private boolean isTradeGroup;
    private boolean isWarrantyCode;
    public int describeContents() {
        return 0;
    }
    public boolean isTradeGroup() {
        return this.isTradeGroup;
    }
    public void setTradeGroup(boolean z) {
        this.isTradeGroup = z;
    }
    public String getDefaultTradeGroup() {
        return this.defaultTradeGroup;
    }
    public void setDefaultTradeGroup(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.defaultTradeGroup = str;
    }
    public boolean isDocumentNo() {
        return this.isDocumentNo;
    }
    public void setDocumentNo(boolean z) {
        this.isDocumentNo = z;
    }
    public String getDefaultDocumentNo() {
        return this.defaultDocumentNo;
    }
    public void setDefaultDocumentNo(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.defaultDocumentNo = str;
    }
    public String getDefaultSpeCode() {
        return this.defaultSpeCode;
    }
    public  void setDefaultSpeCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.defaultSpeCode = str;
    }
    public  boolean isWarrantyCode() {
        return this.isWarrantyCode;
    }
    public  void setWarrantyCode(boolean z) {
        this.isWarrantyCode = z;
    }
    public  String getDefaultWarrantyCode() {
        return this.defaultWarrantyCode;
    }
    public  void setDefaultWarrantyCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.defaultWarrantyCode = str;
    }
    public  boolean isReserveOrder() {
        return this.isReserveOrder;
    }
    public  void setReserveOrder(boolean z) {
        this.isReserveOrder = z;
    }
    public  boolean isShipAddress() {
        return this.isShipAddress;
    }
    public  void setShipAddress(boolean z) {
        this.isShipAddress = z;
    }
    public  boolean isShipAccount() {
        return this.isShipAccount;
    }
    public  void setShipAccount(boolean z) {
        this.isShipAccount = z;
    }
    public  boolean isShipType() {
        return this.isShipType;
    }
    public  void setShipType(boolean z) {
        this.isShipType = z;
    }
    public  boolean isShipAgent() {
        return this.isShipAgent;
    }
    public  void setShipAgent(boolean z) {
        this.isShipAgent = z;
    }
    public  boolean isExplanation1() {
        return this.isExplanation1;
    }
    public  void setExplanation1(boolean z) {
        this.isExplanation1 = z;
    }
    public  String getDefaultExplanation1() {
        return this.defaultExplanation1;
    }
    public  void setDefaultExplanation1(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.defaultExplanation1 = str;
    }
    public  boolean isExplanation2() {
        return this.isExplanation2;
    }
    public  void setExplanation2(boolean z) {
        this.isExplanation2 = z;
    }
    public  boolean isExplanation3() {
        return this.isExplanation3;
    }
    public  void setExplanation3(boolean z) {
        this.isExplanation3 = z;
    }
    public  boolean isExplanation4() {
        return this.isExplanation4;
    }
    public  void setExplanation4(boolean z) {
        this.isExplanation4 = z;
    }
    public  boolean isExplanation5() {
        return this.isExplanation5;
    }
    public  void setExplanation5(boolean z) {
        this.isExplanation5 = z;
    }
    public  boolean isExplanation6() {
        return this.isExplanation6;
    }
    public  void setExplanation6(boolean z) {
        this.isExplanation6 = z;
    }
    public  boolean isExplanation7() {
        return this.isExplanation7;
    }
    public  void setExplanation7(boolean z) {
        this.isExplanation7 = z;
    }
    public  boolean isExplanation8() {
        return this.isExplanation8;
    }
    public  void setExplanation8(boolean z) {
        this.isExplanation8 = z;
    }
    public  boolean isExplanation9() {
        return this.isExplanation9;
    }
    public  void setExplanation9(boolean z) {
        this.isExplanation9 = z;
    }
    public  boolean isExplanation10() {
        return this.isExplanation10;
    }
    public  void setExplanation10(boolean z) {
        this.isExplanation10 = z;
    }
    public  boolean isBranch() {
        return this.isBranch;
    }
    public  void setBranch(boolean z) {
        this.isBranch = z;
    }
    public  int getDefaultWareHouse() {
        return this.defaultWareHouse;
    }
    public  void setDefaultWareHouse(int i2) {
        this.defaultWareHouse = i2;
    }
    public  int getDefaultReturnWareHouse() {
        return this.defaultReturnWareHouse;
    }
    public  void setDefaultReturnWareHouse(int i2) {
        this.defaultReturnWareHouse = i2;
    }
    public  int getDefaultBranch() {
        return this.defaultBranch;
    }
    public  void setDefaultBranch(int i2) {
        this.defaultBranch = i2;
    }
    public  boolean isDivision() {
        return this.isDivision;
    }
    public  void setDivision(boolean z) {
        this.isDivision = z;
    }
    public  int getDefaultDivision() {
        return this.defaultDivision;
    }
    public  void setDefaultDivision(int i2) {
        this.defaultDivision = i2;
    }
    public  boolean isFactory() {
        return this.isFactory;
    }
    public  void setFactory(boolean z) {
        this.isFactory = z;
    }
    public  boolean isProject() {
        return this.isProject;
    }
    public  void setProject(boolean z) {
        this.isProject = z;
    }
    public  String getDefaultProject() {
        return this.defaultProject;
    }
    public  void setDefaultProject(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.defaultProject = str;
    }
    public  boolean isCustomerOrderNo() {
        return this.isCustomerOrderNo;
    }
    public  void setCustomerOrderNo(boolean z) {
        this.isCustomerOrderNo = z;
    }
    public  boolean isDocumentTrackingNo() {
        return this.isDocumentTrackingNo;
    }
    public  void setDocumentTrackingNo(boolean z) {
        this.isDocumentTrackingNo = z;
    }
    public  int getDefaultFactory() {
        return this.defaultFactory;
    }
    public  void setDefaultFactory(int i2) {
        this.defaultFactory = i2;
    }
    public  boolean isConsignee() {
        return this.isConsignee;
    }
    public  void setConsignee(boolean z) {
        this.isConsignee = z;
    }
    public  boolean isIncludeVAT() {
        return this.isIncludeVAT;
    }
    public  void setIncludeVAT(boolean z) {
        this.isIncludeVAT = z;
    }
    public  String getDefaultTaxPayerCode() {
        return this.defaultTaxPayerCode;
    }
    public  void setDefaultTaxPayerCode(String str) {
        this.defaultTaxPayerCode = str;
    }
    public  String getDefaultTaxPayerName() {
        return this.defaultTaxPayerName;
    }
    public  void setDefaultTaxPayerName(String str) {
        this.defaultTaxPayerName = str;
    }
    public  int getDefaultEInvoiceTypSGK() {
        return this.defaultEInvoiceTypSGK;
    }
    public  void setDefaultEInvoiceTypSGK(int i2) {
        this.defaultEInvoiceTypSGK = i2;
    }
    public SalesFicheHeaderFields() {
        this.defaultTradeGroup = "";
        this.defaultDocumentNo = "";
        this.defaultSpeCode = "";
        this.defaultWarrantyCode = "";
        this.defaultExplanation1 = "";
        this.defaultProject = "";
    }
    public SalesFicheHeaderFields(ErpType erpType, SalesType salesType, String str, int i2, int i3, int i4, boolean z) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        int i5;
        int i6;
        int i7;
        boolean z2;
        int i8;
        Object obj;
        Object obj2;
        Exception exc;
        int i9;
        String str9;
        Intrinsics.checkNotNullParameter(erpType, "erpType");
        this.defaultTradeGroup = "";
        this.defaultDocumentNo = "";
        this.defaultSpeCode = "";
        this.defaultWarrantyCode = "";
        this.defaultExplanation1 = "";
        this.defaultProject = "";
        setDoSalesDiscount(z);
        Intrinsics.checkNotNull(str);
        String[] split = StringUtils.split(str, ",");
        Intrinsics.checkNotNull(salesType);
        String str10 = "10";
        String str11 = "9";
        String str12 = "8";
        String str13 = "7";
        String str14 = "5";
        Object obj3 = "16";
        String str15 = "4";
        Object obj4 = "15";
        Object obj5 = "14";
        Object obj6 = "13";
        Object obj7 = "12";
        String str16 = "0";
        Object obj8 = "11";
        if (SalesUtils.isSalesTypeOrder(salesType)) {
            try {
                Intrinsics.checkNotNull(split);
                int length = split.length;
                int i10 = 0;
                while (i10 < length) {
                    int i11 = length;
                    String str17 = split[i10];
                    Intrinsics.checkNotNull(str17);
                    String str18 = str10;
                    String str19 = str11;
                    int length2 = str17.length() - 1;
                    int i12 = 0;
                    boolean z3 = false;
                    while (true) {
                        str2 = str12;
                        if (i12 > length2) {
                            str3 = str13;
                            break;
                        }
                        str3 = str13;
                        boolean z4 = Intrinsics.compare(str17.charAt(!z3 ? i12 : length2), 32) <= 0;
                        if (z3) {
                            if (!z4) {
                                break;
                            } else {
                                length2--;
                            }
                        } else if (z4) {
                            i12++;
                        } else {
                            str12 = str2;
                            str13 = str3;
                            z3 = true;
                        }
                        str12 = str2;
                        str13 = str3;
                    }
                    if (Intrinsics.areEqual(str17.subSequence(i12, length2 + 1).toString(), str16)) {
                        this.isTradeGroup = true;
                    }
                    String str20 = split[i10];
                    Intrinsics.checkNotNull(str20);
                    int length3 = str20.length() - 1;
                    int i13 = 0;
                    boolean z5 = false;
                    while (true) {
                        if (i13 > length3) {
                            str4 = str16;
                            break;
                        }
                        str4 = str16;
                        boolean z6 = Intrinsics.compare(str20.charAt(!z5 ? i13 : length3), 32) <= 0;
                        if (z5) {
                            if (!z6) {
                                break;
                            } else {
                                length3--;
                            }
                        } else if (z6) {
                            i13++;
                        } else {
                            str16 = str4;
                            z5 = true;
                        }
                        str16 = str4;
                    }
                    if (Intrinsics.areEqual(str20.subSequence(i13, length3 + 1).toString(), BuildConfig.NETSIS_DEMO_PASSWORD)) {
                        super.setPayment(true);
                    }
                    String str21 = split[i10];
                    Intrinsics.checkNotNull(str21);
                    int length4 = str21.length() - 1;
                    int i14 = 0;
                    boolean z7 = false;
                    while (i14 <= length4) {
                        boolean z8 = Intrinsics.compare(str21.charAt(!z7 ? i14 : length4), 32) <= 0;
                        if (z7) {
                            if (!z8) {
                                break;
                            } else {
                                length4--;
                            }
                        } else if (z8) {
                            i14++;
                        } else {
                            z7 = true;
                        }
                    }
                    if (Intrinsics.areEqual(str21.subSequence(i14, length4 + 1).toString(), ExifInterface.GPS_MEASUREMENT_2D)) {
                        this.isDocumentNo = true;
                    }
                    String str22 = split[i10];
                    Intrinsics.checkNotNull(str22);
                    int length5 = str22.length() - 1;
                    int i15 = 0;
                    boolean z9 = false;
                    while (i15 <= length5) {
                        boolean z10 = Intrinsics.compare(str22.charAt(!z9 ? i15 : length5), 32) <= 0;
                        if (z9) {
                            if (!z10) {
                                break;
                            } else {
                                length5--;
                            }
                        } else if (z10) {
                            i15++;
                        } else {
                            z9 = true;
                        }
                    }
                    if (Intrinsics.areEqual(str22.subSequence(i15, length5 + 1).toString(), ExifInterface.GPS_MEASUREMENT_3D)) {
                        super.setSpeCode(true);
                    }
                    String str23 = split[i10];
                    Intrinsics.checkNotNull(str23);
                    int length6 = str23.length() - 1;
                    int i16 = 0;
                    boolean z11 = false;
                    while (i16 <= length6) {
                        boolean z12 = Intrinsics.compare(str23.charAt(!z11 ? i16 : length6), 32) <= 0;
                        if (z11) {
                            if (!z12) {
                                break;
                            } else {
                                length6--;
                            }
                        } else if (z12) {
                            i16++;
                        } else {
                            z11 = true;
                        }
                    }
                    if (Intrinsics.areEqual(str23.subSequence(i16, length6 + 1).toString(), "4")) {
                        this.isWarrantyCode = true;
                    }
                    String str24 = split[i10];
                    Intrinsics.checkNotNull(str24);
                    int length7 = str24.length() - 1;
                    int i17 = 0;
                    boolean z13 = false;
                    while (i17 <= length7) {
                        boolean z14 = Intrinsics.compare(str24.charAt(!z13 ? i17 : length7), 32) <= 0;
                        if (z13) {
                            if (!z14) {
                                break;
                            } else {
                                length7--;
                            }
                        } else if (z14) {
                            i17++;
                        } else {
                            z13 = true;
                        }
                    }
                    if (Intrinsics.areEqual(str24.subSequence(i17, length7 + 1).toString(), str14)) {
                        this.isReserveOrder = true;
                    }
                    String str25 = split[i10];
                    Intrinsics.checkNotNull(str25);
                    int length8 = str25.length() - 1;
                    int i18 = 0;
                    boolean z15 = false;
                    while (i18 <= length8) {
                        boolean z16 = Intrinsics.compare(str25.charAt(!z15 ? i18 : length8), 32) <= 0;
                        if (z15) {
                            if (!z16) {
                                break;
                            } else {
                                length8--;
                            }
                        } else if (z16) {
                            i18++;
                        } else {
                            z15 = true;
                        }
                    }
                    if (Intrinsics.areEqual(str25.subSequence(i18, length8 + 1).toString(), "6")) {
                        this.isShipType = true;
                    }
                    String str26 = split[i10];
                    Intrinsics.checkNotNull(str26);
                    int length9 = str26.length() - 1;
                    int i19 = 0;
                    boolean z17 = false;
                    while (i19 <= length9) {
                        boolean z18 = Intrinsics.compare(str26.charAt(!z17 ? i19 : length9), 32) <= 0;
                        if (z17) {
                            if (!z18) {
                                break;
                            } else {
                                length9--;
                            }
                        } else if (z18) {
                            i19++;
                        } else {
                            z17 = true;
                        }
                    }
                    String obj9 = str26.subSequence(i19, length9 + 1).toString();
                    String str27 = str3;
                    if (Intrinsics.areEqual(obj9, str27)) {
                        this.isShipAccount = true;
                    }
                    String str28 = split[i10];
                    Intrinsics.checkNotNull(str28);
                    int length10 = str28.length() - 1;
                    int i20 = 0;
                    boolean z19 = false;
                    while (true) {
                        if (i20 > length10) {
                            str5 = str14;
                            break;
                        }
                        str5 = str14;
                        boolean z20 = Intrinsics.compare(str28.charAt(!z19 ? i20 : length10), 32) <= 0;
                        if (z19) {
                            if (!z20) {
                                break;
                            } else {
                                length10--;
                            }
                        } else if (z20) {
                            i20++;
                        } else {
                            str14 = str5;
                            z19 = true;
                        }
                        str14 = str5;
                    }
                    String obj10 = str28.subSequence(i20, length10 + 1).toString();
                    String str29 = str2;
                    if (Intrinsics.areEqual(obj10, str29)) {
                        this.isShipAddress = true;
                    }
                    String str30 = split[i10];
                    Intrinsics.checkNotNull(str30);
                    int length11 = str30.length() - 1;
                    int i21 = 0;
                    boolean z21 = false;
                    while (true) {
                        if (i21 > length11) {
                            str6 = str29;
                            break;
                        }
                        str6 = str29;
                        boolean z22 = Intrinsics.compare(str30.charAt(!z21 ? i21 : length11), 32) <= 0;
                        if (z21) {
                            if (!z22) {
                                break;
                            } else {
                                length11--;
                            }
                        } else if (z22) {
                            i21++;
                        } else {
                            str29 = str6;
                            z21 = true;
                        }
                        str29 = str6;
                    }
                    String str31 = str19;
                    if (Intrinsics.areEqual(str30.subSequence(i21, length11 + 1).toString(), str31)) {
                        this.isShipAgent = true;
                    }
                    String str32 = split[i10];
                    Intrinsics.checkNotNull(str32);
                    int length12 = str32.length() - 1;
                    int i22 = 0;
                    boolean z23 = false;
                    while (true) {
                        if (i22 > length12) {
                            str7 = str31;
                            break;
                        }
                        str7 = str31;
                        boolean z24 = Intrinsics.compare(str32.charAt(!z23 ? i22 : length12), 32) <= 0;
                        if (z23) {
                            if (!z24) {
                                break;
                            } else {
                                length12--;
                            }
                        } else if (z24) {
                            i22++;
                        } else {
                            str31 = str7;
                            z23 = true;
                        }
                        str31 = str7;
                    }
                    String str33 = str18;
                    if (Intrinsics.areEqual(str32.subSequence(i22, length12 + 1).toString(), str33)) {
                        this.isExplanation1 = true;
                    }
                    String str34 = split[i10];
                    Intrinsics.checkNotNull(str34);
                    int length13 = str34.length() - 1;
                    int i23 = 0;
                    boolean z25 = false;
                    while (true) {
                        if (i23 > length13) {
                            str8 = str33;
                            break;
                        }
                        str8 = str33;
                        boolean z26 = Intrinsics.compare(str34.charAt(!z25 ? i23 : length13), 32) <= 0;
                        if (z25) {
                            if (!z26) {
                                break;
                            } else {
                                length13--;
                            }
                        } else if (z26) {
                            i23++;
                        } else {
                            str33 = str8;
                            z25 = true;
                        }
                        str33 = str8;
                    }
                    Object obj11 = obj8;
                    if (Intrinsics.areEqual(str34.subSequence(i23, length13 + 1).toString(), obj11)) {
                        this.isExplanation2 = true;
                    }
                    String str35 = split[i10];
                    Intrinsics.checkNotNull(str35);
                    int length14 = str35.length() - 1;
                    int i24 = 0;
                    boolean z27 = false;
                    while (true) {
                        if (i24 > length14) {
                            obj8 = obj11;
                            break;
                        }
                        obj8 = obj11;
                        boolean z28 = Intrinsics.compare(str35.charAt(!z27 ? i24 : length14), 32) <= 0;
                        if (z27) {
                            if (!z28) {
                                break;
                            } else {
                                length14--;
                            }
                        } else if (z28) {
                            i24++;
                        } else {
                            obj11 = obj8;
                            z27 = true;
                        }
                        obj11 = obj8;
                    }
                    Object obj12 = obj7;
                    if (Intrinsics.areEqual(str35.subSequence(i24, length14 + 1).toString(), obj12)) {
                        this.isExplanation3 = true;
                    }
                    String str36 = split[i10];
                    Intrinsics.checkNotNull(str36);
                    int length15 = str36.length() - 1;
                    int i25 = 0;
                    boolean z29 = false;
                    while (true) {
                        if (i25 > length15) {
                            obj7 = obj12;
                            break;
                        }
                        obj7 = obj12;
                        boolean z30 = Intrinsics.compare(str36.charAt(!z29 ? i25 : length15), 32) <= 0;
                        if (z29) {
                            if (!z30) {
                                break;
                            } else {
                                length15--;
                            }
                        } else if (z30) {
                            i25++;
                        } else {
                            obj12 = obj7;
                            z29 = true;
                        }
                        obj12 = obj7;
                    }
                    Object obj13 = obj6;
                    if (Intrinsics.areEqual(str36.subSequence(i25, length15 + 1).toString(), obj13)) {
                        this.isExplanation4 = true;
                    }
                    String str37 = split[i10];
                    Intrinsics.checkNotNull(str37);
                    int length16 = str37.length() - 1;
                    int i26 = 0;
                    boolean z31 = false;
                    while (true) {
                        if (i26 > length16) {
                            obj6 = obj13;
                            break;
                        }
                        obj6 = obj13;
                        boolean z32 = Intrinsics.compare(str37.charAt(!z31 ? i26 : length16), 32) <= 0;
                        if (z31) {
                            if (!z32) {
                                break;
                            } else {
                                length16--;
                            }
                        } else if (z32) {
                            i26++;
                        } else {
                            obj13 = obj6;
                            z31 = true;
                        }
                        obj13 = obj6;
                    }
                    Object obj14 = obj5;
                    if (Intrinsics.areEqual(str37.subSequence(i26, length16 + 1).toString(), obj14)) {
                        this.isBranch = true;
                    }
                    String str38 = split[i10];
                    Intrinsics.checkNotNull(str38);
                    int length17 = str38.length() - 1;
                    int i27 = 0;
                    boolean z33 = false;
                    while (true) {
                        if (i27 > length17) {
                            obj5 = obj14;
                            break;
                        }
                        obj5 = obj14;
                        boolean z34 = Intrinsics.compare(str38.charAt(!z33 ? i27 : length17), 32) <= 0;
                        if (z33) {
                            if (!z34) {
                                break;
                            } else {
                                length17--;
                            }
                        } else if (z34) {
                            i27++;
                        } else {
                            obj14 = obj5;
                            z33 = true;
                        }
                        obj14 = obj5;
                    }
                    Object obj15 = obj4;
                    if (Intrinsics.areEqual(str38.subSequence(i27, length17 + 1).toString(), obj15)) {
                        this.isFactory = true;
                    }
                    String str39 = split[i10];
                    Intrinsics.checkNotNull(str39);
                    int length18 = str39.length() - 1;
                    int i28 = 0;
                    boolean z35 = false;
                    while (true) {
                        if (i28 > length18) {
                            obj4 = obj15;
                            break;
                        }
                        obj4 = obj15;
                        boolean z36 = Intrinsics.compare(str39.charAt(!z35 ? i28 : length18), 32) <= 0;
                        if (z35) {
                            if (!z36) {
                                break;
                            } else {
                                length18--;
                            }
                        } else if (z36) {
                            i28++;
                        } else {
                            obj15 = obj4;
                            z35 = true;
                        }
                        obj15 = obj4;
                    }
                    Object obj16 = obj3;
                    if (Intrinsics.areEqual(str39.subSequence(i28, length18 + 1).toString(), obj16)) {
                        this.isDivision = true;
                    }
                    String str40 = split[i10];
                    Intrinsics.checkNotNull(str40);
                    int length19 = str40.length() - 1;
                    int i29 = 0;
                    boolean z37 = false;
                    while (true) {
                        if (i29 > length19) {
                            obj3 = obj16;
                            break;
                        }
                        obj3 = obj16;
                        boolean z38 = Intrinsics.compare(str40.charAt(!z37 ? i29 : length19), 32) <= 0;
                        if (z37) {
                            if (!z38) {
                                break;
                            } else {
                                length19--;
                            }
                        } else if (z38) {
                            i29++;
                        } else {
                            obj16 = obj3;
                            z37 = true;
                        }
                        obj16 = obj3;
                    }
                    if (Intrinsics.areEqual(str40.subSequence(i29, length19 + 1).toString(), "17")) {
                        this.isProject = true;
                    }
                    String str41 = split[i10];
                    Intrinsics.checkNotNull(str41);
                    int length20 = str41.length() - 1;
                    int i30 = 0;
                    boolean z39 = false;
                    while (i30 <= length20) {
                        boolean z40 = Intrinsics.compare(str41.charAt(!z39 ? i30 : length20), 32) <= 0;
                        if (z39) {
                            if (!z40) {
                                break;
                            } else {
                                length20--;
                            }
                        } else if (z40) {
                            i30++;
                        } else {
                            z39 = true;
                        }
                    }
                    if (Intrinsics.areEqual(str41.subSequence(i30, length20 + 1).toString(), "18")) {
                        this.isExplanation5 = true;
                    }
                    String str42 = split[i10];
                    Intrinsics.checkNotNull(str42);
                    int length21 = str42.length() - 1;
                    int i31 = 0;
                    boolean z41 = false;
                    while (i31 <= length21) {
                        boolean z42 = Intrinsics.compare(str42.charAt(!z41 ? i31 : length21), 32) <= 0;
                        if (z41) {
                            if (!z42) {
                                break;
                            } else {
                                length21--;
                            }
                        } else if (z42) {
                            i31++;
                        } else {
                            z41 = true;
                        }
                    }
                    if (Intrinsics.areEqual(str42.subSequence(i31, length21 + 1).toString(), "19")) {
                        this.isExplanation6 = true;
                    }
                    String str43 = split[i10];
                    Intrinsics.checkNotNull(str43);
                    int length22 = str43.length() - 1;
                    int i32 = 0;
                    boolean z43 = false;
                    while (i32 <= length22) {
                        boolean z44 = Intrinsics.compare(str43.charAt(!z43 ? i32 : length22), 32) <= 0;
                        if (z43) {
                            if (!z44) {
                                break;
                            } else {
                                length22--;
                            }
                        } else if (z44) {
                            i32++;
                        } else {
                            z43 = true;
                        }
                    }
                    if (Intrinsics.areEqual(str43.subSequence(i32, length22 + 1).toString(), "20")) {
                        this.isExplanation7 = true;
                    }
                    String str44 = split[i10];
                    Intrinsics.checkNotNull(str44);
                    int length23 = str44.length() - 1;
                    int i33 = 0;
                    boolean z45 = false;
                    while (i33 <= length23) {
                        boolean z46 = Intrinsics.compare(str44.charAt(!z45 ? i33 : length23), 32) <= 0;
                        if (z45) {
                            if (!z46) {
                                break;
                            } else {
                                length23--;
                            }
                        } else if (z46) {
                            i33++;
                        } else {
                            z45 = true;
                        }
                    }
                    if (Intrinsics.areEqual(str44.subSequence(i33, length23 + 1).toString(), "21")) {
                        this.isExplanation8 = true;
                    }
                    String str45 = split[i10];
                    Intrinsics.checkNotNull(str45);
                    int length24 = str45.length() - 1;
                    int i34 = 0;
                    boolean z47 = false;
                    while (i34 <= length24) {
                        boolean z48 = Intrinsics.compare(str45.charAt(!z47 ? i34 : length24), 32) <= 0;
                        if (z47) {
                            if (!z48) {
                                break;
                            } else {
                                length24--;
                            }
                        } else if (z48) {
                            i34++;
                        } else {
                            z47 = true;
                        }
                    }
                    if (Intrinsics.areEqual(str45.subSequence(i34, length24 + 1).toString(), "22")) {
                        this.isExplanation9 = true;
                    }
                    String str46 = split[i10];
                    Intrinsics.checkNotNull(str46);
                    int length25 = str46.length() - 1;
                    int i35 = 0;
                    boolean z49 = false;
                    while (i35 <= length25) {
                        boolean z50 = Intrinsics.compare(str46.charAt(!z49 ? i35 : length25), 32) <= 0;
                        if (z49) {
                            if (!z50) {
                                break;
                            } else {
                                length25--;
                            }
                        } else if (z50) {
                            i35++;
                        } else {
                            z49 = true;
                        }
                    }
                    if (Intrinsics.areEqual(str46.subSequence(i35, length25 + 1).toString(), "23")) {
                        this.isExplanation10 = true;
                    }
                    i10++;
                    str13 = str27;
                    length = i11;
                    str16 = str4;
                    str10 = str8;
                    str11 = str7;
                    str12 = str6;
                    str14 = str5;
                }
                this.isCustomerOrderNo = true;
                this.isDocumentTrackingNo = true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            Object obj17 = "0";
            Object obj18 = "10";
            Object obj19 = "9";
            Object obj20 = "8";
            Object obj21 = "7";
            Exception e;
            if (SalesUtils.isSalesTypeDemand(salesType)) {
                try {
                    this.isTradeGroup = false;
                    super.setPayment(false);
                    this.isDocumentNo = true;
                    super.setSpeCode(true);
                    this.isWarrantyCode = true;
                    this.isReserveOrder = false;
                    this.isShipType = false;
                    this.isShipAccount = false;
                    this.isShipAddress = false;
                    this.isShipAgent = false;
                    this.isExplanation1 = false;
                    this.isExplanation2 = false;
                    this.isExplanation3 = false;
                    this.isExplanation4 = false;
                    this.isExplanation5 = false;
                    this.isExplanation6 = false;
                    this.isExplanation7 = false;
                    this.isExplanation8 = false;
                    this.isExplanation9 = false;
                    this.isExplanation10 = false;
                    this.isBranch = true;
                    this.isFactory = true;
                    this.isDivision = true;
                    this.isProject = true;
                    setDeliveryDate(false);
                    setPayment(false);
                    this.isCustomerOrderNo = false;
                    this.isDocumentTrackingNo = false;
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } else if (SalesUtils.isSalesTypeWhTransfer(salesType)) {
                try {
                    this.isTradeGroup = false;
                    super.setPayment(false);
                    try {
                        this.isDocumentNo = true;
                        super.setSpeCode(true);
                        ErpType erpType2 = ErpType.TIGER;
                        try {
                            this.isWarrantyCode = erpType == erpType2;
                            try {
                                this.isShipType = false;
                                this.isShipAccount = false;
                                this.isShipAddress = false;
                                this.isShipAgent = false;
                                this.isExplanation1 = true;
                                this.isExplanation2 = true;
                                this.isExplanation3 = true;
                                this.isExplanation4 = true;
                                this.isExplanation5 = true;
                                this.isExplanation6 = true;
                                this.isExplanation7 = true;
                                this.isExplanation8 = true;
                                this.isExplanation9 = true;
                                this.isExplanation10 = true;
                                this.isBranch = erpType == erpType2;
                                this.isFactory = erpType == erpType2;
                                this.isDivision = erpType == erpType2;
                            } catch (Exception e4) {
                                e = e4;
                            }
                        } catch (Exception e5) {
                            e = e5;
                            e.printStackTrace();
                            i7 = i2;
                            i6 = i3;
                            i5 = i4;
                            z2 = true;
                            setDiscount(i7, i6, i5);
                            this.isIncludeVAT = erpType != ErpType.NETSIS && !SalesUtils.isSalesTypeWhTransfer(salesType) && z2;
                        }
                    } catch (Exception e6) {
                        e = e6;
                    }
                } catch (Exception e7) {
                    e = e7;
                }
                try {
                    this.isProject = false;
                    this.isIncludeVAT = false;
                    this.isCustomerOrderNo = false;
                    this.isDocumentTrackingNo = true;
                } catch (Exception e8) {
                    e = e8;
                    e.printStackTrace();
                    i7 = i2;
                    i6 = i3;
                    i5 = i4;
                    z2 = true;
                    setDiscount(i7, i6, i5);
                    this.isIncludeVAT = erpType != ErpType.NETSIS && !SalesUtils.isSalesTypeWhTransfer(salesType) && z2;
                }
            } else {
                int i36 = 0;
                if (!SalesUtils.m570x90f54721(salesType)) {
                    Object obj22 = obj21;
                    if (SalesUtils.isSalesTypeDispatchOrReturnDispatch(salesType)) {
                        try {
                            Intrinsics.checkNotNull(split);
                            int length26 = split.length;
                            int i37 = 0;
                            while (i37 < length26) {
                                try {
                                    String str47 = split[i37];
                                    Intrinsics.checkNotNull(str47);
                                    int length27 = str47.length() - 1;
                                    int i38 = 0;
                                    boolean z51 = false;
                                    while (true) {
                                        if (i38 > length27) {
                                            i9 = length26;
                                            break;
                                        }
                                        i9 = length26;
                                        boolean z52 = Intrinsics.compare(str47.charAt(!z51 ? i38 : length27), 32) <= 0;
                                        if (z51) {
                                            if (!z52) {
                                                break;
                                            } else {
                                                length27--;
                                            }
                                        } else if (z52) {
                                            i38++;
                                        } else {
                                            length26 = i9;
                                            z51 = true;
                                        }
                                        length26 = i9;
                                    }
                                    String obj23 = str47.subSequence(i38, length27 + 1).toString();
                                    Object obj24 = obj17;
                                    if (Intrinsics.areEqual(obj23, obj24)) {
                                        this.isTradeGroup = true;
                                    }
                                    String str48 = split[i37];
                                    Intrinsics.checkNotNull(str48);
                                    int length28 = str48.length() - 1;
                                    int i39 = 0;
                                    boolean z53 = false;
                                    while (true) {
                                        if (i39 > length28) {
                                            obj17 = obj24;
                                            break;
                                        }
                                        obj17 = obj24;
                                        boolean z54 = Intrinsics.compare(str48.charAt(!z53 ? i39 : length28), 32) <= 0;
                                        if (z53) {
                                            if (!z54) {
                                                break;
                                            } else {
                                                length28--;
                                            }
                                        } else if (z54) {
                                            i39++;
                                        } else {
                                            obj24 = obj17;
                                            z53 = true;
                                        }
                                        obj24 = obj17;
                                    }
                                    if (Intrinsics.areEqual(str48.subSequence(i39, length28 + 1).toString(), BuildConfig.NETSIS_DEMO_PASSWORD)) {
                                        super.setPayment(true);
                                    }
                                    String str49 = split[i37];
                                    Intrinsics.checkNotNull(str49);
                                    int length29 = str49.length() - 1;
                                    int i40 = 0;
                                    boolean z55 = false;
                                    while (i40 <= length29) {
                                        boolean z56 = Intrinsics.compare(str49.charAt(!z55 ? i40 : length29), 32) <= 0;
                                        if (z55) {
                                            if (!z56) {
                                                break;
                                            } else {
                                                length29--;
                                            }
                                        } else if (z56) {
                                            i40++;
                                        } else {
                                            z55 = true;
                                        }
                                    }
                                    if (Intrinsics.areEqual(str49.subSequence(i40, length29 + 1).toString(), ExifInterface.GPS_MEASUREMENT_2D)) {
                                        this.isDocumentNo = true;
                                    }
                                    String str50 = split[i37];
                                    Intrinsics.checkNotNull(str50);
                                    int length30 = str50.length() - 1;
                                    int i41 = 0;
                                    boolean z57 = false;
                                    while (i41 <= length30) {
                                        boolean z58 = Intrinsics.compare(str50.charAt(!z57 ? i41 : length30), 32) <= 0;
                                        if (z57) {
                                            if (!z58) {
                                                break;
                                            } else {
                                                length30--;
                                            }
                                        } else if (z58) {
                                            i41++;
                                        } else {
                                            z57 = true;
                                        }
                                    }
                                    if (Intrinsics.areEqual(str50.subSequence(i41, length30 + 1).toString(), ExifInterface.GPS_MEASUREMENT_3D)) {
                                        super.setSpeCode(true);
                                    }
                                    String str51 = split[i37];
                                    Intrinsics.checkNotNull(str51);
                                    int length31 = str51.length() - 1;
                                    int i42 = 0;
                                    boolean z59 = false;
                                    while (i42 <= length31) {
                                        boolean z60 = Intrinsics.compare(str51.charAt(!z59 ? i42 : length31), 32) <= 0;
                                        if (z59) {
                                            if (!z60) {
                                                break;
                                            } else {
                                                length31--;
                                            }
                                        } else if (z60) {
                                            i42++;
                                        } else {
                                            z59 = true;
                                        }
                                    }
                                    if (Intrinsics.areEqual(str51.subSequence(i42, length31 + 1).toString(), str15)) {
                                        this.isWarrantyCode = true;
                                    }
                                    String str52 = split[i37];
                                    Intrinsics.checkNotNull(str52);
                                    int length32 = str52.length() - 1;
                                    int i43 = 0;
                                    boolean z61 = false;
                                    while (i43 <= length32) {
                                        boolean z62 = Intrinsics.compare(str52.charAt(!z61 ? i43 : length32), 32) <= 0;
                                        if (z61) {
                                            if (!z62) {
                                                break;
                                            } else {
                                                length32--;
                                            }
                                        } else if (z62) {
                                            i43++;
                                        } else {
                                            z61 = true;
                                        }
                                    }
                                    if (Intrinsics.areEqual(str52.subSequence(i43, length32 + 1).toString(), "5")) {
                                        this.isShipType = true;
                                    }
                                    String str53 = split[i37];
                                    Intrinsics.checkNotNull(str53);
                                    int length33 = str53.length() - 1;
                                    int i44 = 0;
                                    boolean z63 = false;
                                    while (i44 <= length33) {
                                        boolean z64 = Intrinsics.compare(str53.charAt(!z63 ? i44 : length33), 32) <= 0;
                                        if (z63) {
                                            if (!z64) {
                                                break;
                                            } else {
                                                length33--;
                                            }
                                        } else if (z64) {
                                            i44++;
                                        } else {
                                            z63 = true;
                                        }
                                    }
                                    if (Intrinsics.areEqual(str53.subSequence(i44, length33 + 1).toString(), "6")) {
                                        this.isShipAccount = true;
                                    }
                                    String str54 = split[i37];
                                    Intrinsics.checkNotNull(str54);
                                    int length34 = str54.length() - 1;
                                    int i45 = 0;
                                    boolean z65 = false;
                                    while (i45 <= length34) {
                                        boolean z66 = Intrinsics.compare(str54.charAt(!z65 ? i45 : length34), 32) <= 0;
                                        if (z65) {
                                            if (!z66) {
                                                break;
                                            } else {
                                                length34--;
                                            }
                                        } else if (z66) {
                                            i45++;
                                        } else {
                                            z65 = true;
                                        }
                                    }
                                    String obj25 = str54.subSequence(i45, length34 + 1).toString();
                                    Object obj26 = obj22;
                                    if (Intrinsics.areEqual(obj25, obj26)) {
                                        this.isShipAddress = true;
                                    }
                                    String str55 = split[i37];
                                    Intrinsics.checkNotNull(str55);
                                    int length35 = str55.length() - 1;
                                    int i46 = 0;
                                    boolean z67 = false;
                                    while (true) {
                                        if (i46 > length35) {
                                            str9 = str15;
                                            break;
                                        }
                                        str9 = str15;
                                        boolean z68 = Intrinsics.compare(str55.charAt(!z67 ? i46 : length35), 32) <= 0;
                                        if (z67) {
                                            if (!z68) {
                                                break;
                                            } else {
                                                length35--;
                                            }
                                        } else if (z68) {
                                            i46++;
                                        } else {
                                            str15 = str9;
                                            z67 = true;
                                        }
                                        str15 = str9;
                                    }
                                    Object obj27 = obj20;
                                    if (Intrinsics.areEqual(str55.subSequence(i46, length35 + 1).toString(), obj27)) {
                                        this.isShipAgent = true;
                                    }
                                    String str56 = split[i37];
                                    Intrinsics.checkNotNull(str56);
                                    int length36 = str56.length() - 1;
                                    int i47 = 0;
                                    boolean z69 = false;
                                    while (true) {
                                        if (i47 > length36) {
                                            obj20 = obj27;
                                            break;
                                        }
                                        obj20 = obj27;
                                        boolean z70 = Intrinsics.compare(str56.charAt(!z69 ? i47 : length36), 32) <= 0;
                                        if (z69) {
                                            if (!z70) {
                                                break;
                                            } else {
                                                length36--;
                                            }
                                        } else if (z70) {
                                            i47++;
                                        } else {
                                            obj27 = obj20;
                                            z69 = true;
                                        }
                                        obj27 = obj20;
                                    }
                                    Object obj28 = obj19;
                                    if (Intrinsics.areEqual(str56.subSequence(i47, length36 + 1).toString(), obj28)) {
                                        this.isExplanation1 = true;
                                    }
                                    String str57 = split[i37];
                                    Intrinsics.checkNotNull(str57);
                                    int length37 = str57.length() - 1;
                                    int i48 = 0;
                                    boolean z71 = false;
                                    while (true) {
                                        if (i48 > length37) {
                                            obj19 = obj28;
                                            break;
                                        }
                                        obj19 = obj28;
                                        boolean z72 = Intrinsics.compare(str57.charAt(!z71 ? i48 : length37), 32) <= 0;
                                        if (z71) {
                                            if (!z72) {
                                                break;
                                            } else {
                                                length37--;
                                            }
                                        } else if (z72) {
                                            i48++;
                                        } else {
                                            obj28 = obj19;
                                            z71 = true;
                                        }
                                        obj28 = obj19;
                                    }
                                    Object obj29 = obj18;
                                    if (Intrinsics.areEqual(str57.subSequence(i48, length37 + 1).toString(), obj29)) {
                                        this.isExplanation2 = true;
                                    }
                                    String str58 = split[i37];
                                    Intrinsics.checkNotNull(str58);
                                    int length38 = str58.length() - 1;
                                    int i49 = 0;
                                    boolean z73 = false;
                                    while (true) {
                                        if (i49 > length38) {
                                            obj18 = obj29;
                                            break;
                                        }
                                        obj18 = obj29;
                                        boolean z74 = Intrinsics.compare(str58.charAt(!z73 ? i49 : length38), 32) <= 0;
                                        if (z73) {
                                            if (!z74) {
                                                break;
                                            } else {
                                                length38--;
                                            }
                                        } else if (z74) {
                                            i49++;
                                        } else {
                                            obj29 = obj18;
                                            z73 = true;
                                        }
                                        obj29 = obj18;
                                    }
                                    Object obj30 = obj8;
                                    if (Intrinsics.areEqual(str58.subSequence(i49, length38 + 1).toString(), obj30)) {
                                        this.isExplanation3 = true;
                                    }
                                    String str59 = split[i37];
                                    Intrinsics.checkNotNull(str59);
                                    int length39 = str59.length() - 1;
                                    int i50 = 0;
                                    boolean z75 = false;
                                    while (true) {
                                        if (i50 > length39) {
                                            obj8 = obj30;
                                            break;
                                        }
                                        obj8 = obj30;
                                        boolean z76 = Intrinsics.compare(str59.charAt(!z75 ? i50 : length39), 32) <= 0;
                                        if (z75) {
                                            if (!z76) {
                                                break;
                                            } else {
                                                length39--;
                                            }
                                        } else if (z76) {
                                            i50++;
                                        } else {
                                            obj30 = obj8;
                                            z75 = true;
                                        }
                                        obj30 = obj8;
                                    }
                                    Object obj31 = obj7;
                                    if (Intrinsics.areEqual(str59.subSequence(i50, length39 + 1).toString(), obj31)) {
                                        this.isExplanation4 = true;
                                    }
                                    String str60 = split[i37];
                                    Intrinsics.checkNotNull(str60);
                                    int length40 = str60.length() - 1;
                                    int i51 = 0;
                                    boolean z77 = false;
                                    while (true) {
                                        if (i51 > length40) {
                                            obj7 = obj31;
                                            break;
                                        }
                                        obj7 = obj31;
                                        boolean z78 = Intrinsics.compare(str60.charAt(!z77 ? i51 : length40), 32) <= 0;
                                        if (z77) {
                                            if (!z78) {
                                                break;
                                            } else {
                                                length40--;
                                            }
                                        } else if (z78) {
                                            i51++;
                                        } else {
                                            obj31 = obj7;
                                            z77 = true;
                                        }
                                        obj31 = obj7;
                                    }
                                    Object obj32 = obj6;
                                    if (Intrinsics.areEqual(str60.subSequence(i51, length40 + 1).toString(), obj32)) {
                                        this.isBranch = true;
                                    }
                                    String str61 = split[i37];
                                    Intrinsics.checkNotNull(str61);
                                    int length41 = str61.length() - 1;
                                    int i52 = 0;
                                    boolean z79 = false;
                                    while (true) {
                                        if (i52 > length41) {
                                            obj6 = obj32;
                                            break;
                                        }
                                        obj6 = obj32;
                                        boolean z80 = Intrinsics.compare(str61.charAt(!z79 ? i52 : length41), 32) <= 0;
                                        if (z79) {
                                            if (!z80) {
                                                break;
                                            } else {
                                                length41--;
                                            }
                                        } else if (z80) {
                                            i52++;
                                        } else {
                                            obj32 = obj6;
                                            z79 = true;
                                        }
                                        obj32 = obj6;
                                    }
                                    Object obj33 = obj5;
                                    if (Intrinsics.areEqual(str61.subSequence(i52, length41 + 1).toString(), obj33)) {
                                        this.isFactory = true;
                                    }
                                    String str62 = split[i37];
                                    Intrinsics.checkNotNull(str62);
                                    int length42 = str62.length() - 1;
                                    int i53 = 0;
                                    boolean z81 = false;
                                    while (true) {
                                        if (i53 > length42) {
                                            obj5 = obj33;
                                            break;
                                        }
                                        obj5 = obj33;
                                        boolean z82 = Intrinsics.compare(str62.charAt(!z81 ? i53 : length42), 32) <= 0;
                                        if (z81) {
                                            if (!z82) {
                                                break;
                                            } else {
                                                length42--;
                                            }
                                        } else if (z82) {
                                            i53++;
                                        } else {
                                            obj33 = obj5;
                                            z81 = true;
                                        }
                                        obj33 = obj5;
                                    }
                                    Object obj34 = obj4;
                                    if (Intrinsics.areEqual(str62.subSequence(i53, length42 + 1).toString(), obj34)) {
                                        this.isDivision = true;
                                    }
                                    String str63 = split[i37];
                                    Intrinsics.checkNotNull(str63);
                                    int length43 = str63.length() - 1;
                                    int i54 = 0;
                                    boolean z83 = false;
                                    while (true) {
                                        if (i54 > length43) {
                                            obj4 = obj34;
                                            break;
                                        }
                                        obj4 = obj34;
                                        boolean z84 = Intrinsics.compare(str63.charAt(!z83 ? i54 : length43), 32) <= 0;
                                        if (z83) {
                                            if (!z84) {
                                                break;
                                            } else {
                                                length43--;
                                            }
                                        } else if (z84) {
                                            i54++;
                                        } else {
                                            obj34 = obj4;
                                            z83 = true;
                                        }
                                        obj34 = obj4;
                                    }
                                    Object obj35 = obj3;
                                    if (Intrinsics.areEqual(str63.subSequence(i54, length43 + 1).toString(), obj35)) {
                                        this.isProject = true;
                                    }
                                    String str64 = split[i37];
                                    Intrinsics.checkNotNull(str64);
                                    int length44 = str64.length() - 1;
                                    int i55 = 0;
                                    boolean z85 = false;
                                    while (true) {
                                        if (i55 > length44) {
                                            obj3 = obj35;
                                            break;
                                        }
                                        obj3 = obj35;
                                        boolean z86 = Intrinsics.compare(str64.charAt(!z85 ? i55 : length44), 32) <= 0;
                                        if (z85) {
                                            if (!z86) {
                                                break;
                                            } else {
                                                length44--;
                                            }
                                        } else if (z86) {
                                            i55++;
                                        } else {
                                            obj35 = obj3;
                                            z85 = true;
                                        }
                                        obj35 = obj3;
                                    }
                                    if (Intrinsics.areEqual(str64.subSequence(i55, length44 + 1).toString(), "17")) {
                                        this.isExplanation5 = true;
                                    }
                                    String str65 = split[i37];
                                    Intrinsics.checkNotNull(str65);
                                    int length45 = str65.length() - 1;
                                    int i56 = 0;
                                    boolean z87 = false;
                                    while (i56 <= length45) {
                                        boolean z88 = Intrinsics.compare(str65.charAt(!z87 ? i56 : length45), 32) <= 0;
                                        if (z87) {
                                            if (!z88) {
                                                break;
                                            } else {
                                                length45--;
                                            }
                                        } else if (z88) {
                                            i56++;
                                        } else {
                                            z87 = true;
                                        }
                                    }
                                    if (Intrinsics.areEqual(str65.subSequence(i56, length45 + 1).toString(), "18")) {
                                        this.isExplanation6 = true;
                                    }
                                    String str66 = split[i37];
                                    Intrinsics.checkNotNull(str66);
                                    int length46 = str66.length() - 1;
                                    int i57 = 0;
                                    boolean z89 = false;
                                    while (i57 <= length46) {
                                        boolean z90 = Intrinsics.compare(str66.charAt(!z89 ? i57 : length46), 32) <= 0;
                                        if (z89) {
                                            if (!z90) {
                                                break;
                                            } else {
                                                length46--;
                                            }
                                        } else if (z90) {
                                            i57++;
                                        } else {
                                            z89 = true;
                                        }
                                    }
                                    if (Intrinsics.areEqual(str66.subSequence(i57, length46 + 1).toString(), "19")) {
                                        this.isExplanation7 = true;
                                    }
                                    String str67 = split[i37];
                                    Intrinsics.checkNotNull(str67);
                                    int length47 = str67.length() - 1;
                                    int i58 = 0;
                                    boolean z91 = false;
                                    while (i58 <= length47) {
                                        boolean z92 = Intrinsics.compare(str67.charAt(!z91 ? i58 : length47), 32) <= 0;
                                        if (z91) {
                                            if (!z92) {
                                                break;
                                            } else {
                                                length47--;
                                            }
                                        } else if (z92) {
                                            i58++;
                                        } else {
                                            z91 = true;
                                        }
                                    }
                                    if (Intrinsics.areEqual(str67.subSequence(i58, length47 + 1).toString(), "20")) {
                                        this.isExplanation8 = true;
                                    }
                                    String str68 = split[i37];
                                    Intrinsics.checkNotNull(str68);
                                    int length48 = str68.length() - 1;
                                    int i59 = 0;
                                    boolean z93 = false;
                                    while (i59 <= length48) {
                                        boolean z94 = Intrinsics.compare(str68.charAt(!z93 ? i59 : length48), 32) <= 0;
                                        if (z93) {
                                            if (!z94) {
                                                break;
                                            } else {
                                                length48--;
                                            }
                                        } else if (z94) {
                                            i59++;
                                        } else {
                                            z93 = true;
                                        }
                                    }
                                    if (Intrinsics.areEqual(str68.subSequence(i59, length48 + 1).toString(), "21")) {
                                        this.isExplanation9 = true;
                                    }
                                    String str69 = split[i37];
                                    Intrinsics.checkNotNull(str69);
                                    int length49 = str69.length() - 1;
                                    int i60 = 0;
                                    boolean z95 = false;
                                    while (i60 <= length49) {
                                        boolean z96 = Intrinsics.compare(str69.charAt(!z95 ? i60 : length49), 32) <= 0;
                                        if (z95) {
                                            if (!z96) {
                                                break;
                                            } else {
                                                length49--;
                                            }
                                        } else if (z96) {
                                            i60++;
                                        } else {
                                            z95 = true;
                                        }
                                    }
                                    if (Intrinsics.areEqual(str69.subSequence(i60, length49 + 1).toString(), "22")) {
                                        this.isExplanation10 = true;
                                    }
                                    i37++;
                                    str15 = str9;
                                    length26 = i9;
                                    obj22 = obj26;
                                } catch (Exception e9) {
                                    exc = e9;
                                    z2 = true;
                                    exc.printStackTrace();
                                    i7 = i2;
                                    i6 = i3;
                                    i5 = i4;
                                    setDiscount(i7, i6, i5);
                                    this.isIncludeVAT = erpType != ErpType.NETSIS && !SalesUtils.isSalesTypeWhTransfer(salesType) && z2;
                                }
                            }
                            z2 = true;
                            try {
                                this.isConsignee = true;
                                this.isCustomerOrderNo = true;
                                this.isDocumentTrackingNo = true;
                            } catch (Exception e10) {
                                e = e10;
                                exc = e;
                                exc.printStackTrace();
                                i7 = i2;
                                i6 = i3;
                                i5 = i4;
                                setDiscount(i7, i6, i5);
                                this.isIncludeVAT = erpType != ErpType.NETSIS && !SalesUtils.isSalesTypeWhTransfer(salesType) && z2;
                            }
                        } catch (Exception e11) {
                            e = e11;
                            z2 = true;
                        }
                    } else {
                        z2 = true;
                    }
                    i7 = i2;
                    i6 = i3;
                    i5 = i4;
                    setDiscount(i7, i6, i5);
                    this.isIncludeVAT = erpType != ErpType.NETSIS && !SalesUtils.isSalesTypeWhTransfer(salesType) && z2;
                }
                try {
                    Intrinsics.checkNotNull(split);
                    int length50 = split.length;
                    int i61 = 0;
                    while (i61 < length50) {
                        String str70 = split[i61];
                        Intrinsics.checkNotNull(str70);
                        int i62 = i36;
                        int length51 = str70.length() - 1;
                        int i63 = i62;
                        while (true) {
                            i8 = length50;
                            if (i63 > length51) {
                                break;
                            }
                            boolean z97 = Intrinsics.compare(str70.charAt(i62 == 0 ? i63 : length51), 32) <= 0;
                            if (i62 == 0) {
                                if (z97) {
                                    i63++;
                                } else {
                                    length50 = i8;
                                    i62 = 1;
                                }
                            } else if (!z97) {
                                break;
                            } else {
                                length51--;
                            }
                            length50 = i8;
                        }
                        Object obj36 = obj17;
                        if (Intrinsics.areEqual(str70.subSequence(i63, length51 + 1).toString(), obj36)) {
                            this.isTradeGroup = true;
                        }
                        String str71 = split[i61];
                        Intrinsics.checkNotNull(str71);
                        int length52 = str71.length() - 1;
                        int i64 = 0;
                        boolean z98 = false;
                        while (true) {
                            obj17 = obj36;
                            if (i64 > length52) {
                                obj = obj21;
                                break;
                            }
                            obj = obj21;
                            boolean z99 = Intrinsics.compare(str71.charAt(!z98 ? i64 : length52), 32) <= 0;
                            if (z98) {
                                if (!z99) {
                                    break;
                                } else {
                                    length52--;
                                }
                            } else if (z99) {
                                i64++;
                            } else {
                                obj21 = obj;
                                obj36 = obj17;
                                z98 = true;
                            }
                            obj21 = obj;
                            obj36 = obj17;
                        }
                        if (Intrinsics.areEqual(str71.subSequence(i64, length52 + 1).toString(), BuildConfig.NETSIS_DEMO_PASSWORD)) {
                            super.setPayment(true);
                        }
                        String str72 = split[i61];
                        Intrinsics.checkNotNull(str72);
                        int length53 = str72.length() - 1;
                        int i65 = 0;
                        boolean z100 = false;
                        while (i65 <= length53) {
                            boolean z101 = Intrinsics.compare(str72.charAt(!z100 ? i65 : length53), 32) <= 0;
                            if (z100) {
                                if (!z101) {
                                    break;
                                } else {
                                    length53--;
                                }
                            } else if (z101) {
                                i65++;
                            } else {
                                z100 = true;
                            }
                        }
                        if (Intrinsics.areEqual(str72.subSequence(i65, length53 + 1).toString(), ExifInterface.GPS_MEASUREMENT_2D)) {
                            this.isDocumentNo = true;
                        }
                        String str73 = split[i61];
                        Intrinsics.checkNotNull(str73);
                        int length54 = str73.length() - 1;
                        int i66 = 0;
                        boolean z102 = false;
                        while (i66 <= length54) {
                            boolean z103 = Intrinsics.compare(str73.charAt(!z102 ? i66 : length54), 32) <= 0;
                            if (z102) {
                                if (!z103) {
                                    break;
                                } else {
                                    length54--;
                                }
                            } else if (z103) {
                                i66++;
                            } else {
                                z102 = true;
                            }
                        }
                        if (Intrinsics.areEqual(str73.subSequence(i66, length54 + 1).toString(), ExifInterface.GPS_MEASUREMENT_3D)) {
                            super.setSpeCode(true);
                        }
                        String str74 = split[i61];
                        Intrinsics.checkNotNull(str74);
                        int length55 = str74.length() - 1;
                        int i67 = 0;
                        boolean z104 = false;
                        while (i67 <= length55) {
                            boolean z105 = Intrinsics.compare(str74.charAt(!z104 ? i67 : length55), 32) <= 0;
                            if (z104) {
                                if (!z105) {
                                    break;
                                } else {
                                    length55--;
                                }
                            } else if (z105) {
                                i67++;
                            } else {
                                z104 = true;
                            }
                        }
                        if (Intrinsics.areEqual(str74.subSequence(i67, length55 + 1).toString(), "4")) {
                            this.isWarrantyCode = true;
                        }
                        String str75 = split[i61];
                        Intrinsics.checkNotNull(str75);
                        int length56 = str75.length() - 1;
                        int i68 = 0;
                        boolean z106 = false;
                        while (i68 <= length56) {
                            boolean z107 = Intrinsics.compare(str75.charAt(!z106 ? i68 : length56), 32) <= 0;
                            if (z106) {
                                if (!z107) {
                                    break;
                                } else {
                                    length56--;
                                }
                            } else if (z107) {
                                i68++;
                            } else {
                                z106 = true;
                            }
                        }
                        if (Intrinsics.areEqual(str75.subSequence(i68, length56 + 1).toString(), "5")) {
                            this.isShipType = true;
                        }
                        String str76 = split[i61];
                        Intrinsics.checkNotNull(str76);
                        int length57 = str76.length() - 1;
                        int i69 = 0;
                        boolean z108 = false;
                        while (i69 <= length57) {
                            boolean z109 = Intrinsics.compare(str76.charAt(!z108 ? i69 : length57), 32) <= 0;
                            if (z108) {
                                if (!z109) {
                                    break;
                                } else {
                                    length57--;
                                }
                            } else if (z109) {
                                i69++;
                            } else {
                                z108 = true;
                            }
                        }
                        if (Intrinsics.areEqual(str76.subSequence(i69, length57 + 1).toString(), "6")) {
                            this.isShipAccount = true;
                        }
                        String str77 = split[i61];
                        Intrinsics.checkNotNull(str77);
                        int length58 = str77.length() - 1;
                        int i70 = 0;
                        boolean z110 = false;
                        while (i70 <= length58) {
                            boolean z111 = Intrinsics.compare(str77.charAt(!z110 ? i70 : length58), 32) <= 0;
                            if (z110) {
                                if (!z111) {
                                    break;
                                } else {
                                    length58--;
                                }
                            } else if (z111) {
                                i70++;
                            } else {
                                z110 = true;
                            }
                        }
                        String obj37 = str77.subSequence(i70, length58 + 1).toString();
                        Object obj38 = obj;
                        if (Intrinsics.areEqual(obj37, obj38)) {
                            this.isShipAddress = true;
                        }
                        String str78 = split[i61];
                        Intrinsics.checkNotNull(str78);
                        int length59 = str78.length() - 1;
                        int i71 = 0;
                        boolean z112 = false;
                        while (true) {
                            if (i71 > length59) {
                                obj2 = obj38;
                                break;
                            }
                            obj2 = obj38;
                            boolean z113 = Intrinsics.compare(str78.charAt(!z112 ? i71 : length59), 32) <= 0;
                            if (z112) {
                                if (!z113) {
                                    break;
                                } else {
                                    length59--;
                                }
                            } else if (z113) {
                                i71++;
                            } else {
                                obj38 = obj2;
                                z112 = true;
                            }
                            obj38 = obj2;
                        }
                        Object obj39 = obj20;
                        if (Intrinsics.areEqual(str78.subSequence(i71, length59 + 1).toString(), obj39)) {
                            this.isShipAgent = true;
                        }
                        String str79 = split[i61];
                        Intrinsics.checkNotNull(str79);
                        int length60 = str79.length() - 1;
                        int i72 = 0;
                        boolean z114 = false;
                        while (true) {
                            if (i72 > length60) {
                                obj20 = obj39;
                                break;
                            }
                            obj20 = obj39;
                            boolean z115 = Intrinsics.compare(str79.charAt(!z114 ? i72 : length60), 32) <= 0;
                            if (z114) {
                                if (!z115) {
                                    break;
                                } else {
                                    length60--;
                                }
                            } else if (z115) {
                                i72++;
                            } else {
                                obj39 = obj20;
                                z114 = true;
                            }
                            obj39 = obj20;
                        }
                        Object obj40 = obj19;
                        if (Intrinsics.areEqual(str79.subSequence(i72, length60 + 1).toString(), obj40)) {
                            this.isExplanation1 = true;
                        }
                        String str80 = split[i61];
                        Intrinsics.checkNotNull(str80);
                        int length61 = str80.length() - 1;
                        int i73 = 0;
                        boolean z116 = false;
                        while (true) {
                            if (i73 > length61) {
                                obj19 = obj40;
                                break;
                            }
                            obj19 = obj40;
                            boolean z117 = Intrinsics.compare(str80.charAt(!z116 ? i73 : length61), 32) <= 0;
                            if (z116) {
                                if (!z117) {
                                    break;
                                } else {
                                    length61--;
                                }
                            } else if (z117) {
                                i73++;
                            } else {
                                obj40 = obj19;
                                z116 = true;
                            }
                            obj40 = obj19;
                        }
                        Object obj41 = obj18;
                        if (Intrinsics.areEqual(str80.subSequence(i73, length61 + 1).toString(), obj41)) {
                            this.isExplanation2 = true;
                        }
                        String str81 = split[i61];
                        Intrinsics.checkNotNull(str81);
                        int length62 = str81.length() - 1;
                        int i74 = 0;
                        boolean z118 = false;
                        while (true) {
                            if (i74 > length62) {
                                obj18 = obj41;
                                break;
                            }
                            obj18 = obj41;
                            boolean z119 = Intrinsics.compare(str81.charAt(!z118 ? i74 : length62), 32) <= 0;
                            if (z118) {
                                if (!z119) {
                                    break;
                                } else {
                                    length62--;
                                }
                            } else if (z119) {
                                i74++;
                            } else {
                                obj41 = obj18;
                                z118 = true;
                            }
                            obj41 = obj18;
                        }
                        Object obj42 = obj8;
                        if (Intrinsics.areEqual(str81.subSequence(i74, length62 + 1).toString(), obj42)) {
                            this.isExplanation3 = true;
                        }
                        String str82 = split[i61];
                        Intrinsics.checkNotNull(str82);
                        int length63 = str82.length() - 1;
                        int i75 = 0;
                        boolean z120 = false;
                        while (true) {
                            if (i75 > length63) {
                                obj8 = obj42;
                                break;
                            }
                            obj8 = obj42;
                            boolean z121 = Intrinsics.compare(str82.charAt(!z120 ? i75 : length63), 32) <= 0;
                            if (z120) {
                                if (!z121) {
                                    break;
                                } else {
                                    length63--;
                                }
                            } else if (z121) {
                                i75++;
                            } else {
                                obj42 = obj8;
                                z120 = true;
                            }
                            obj42 = obj8;
                        }
                        Object obj43 = obj7;
                        if (Intrinsics.areEqual(str82.subSequence(i75, length63 + 1).toString(), obj43)) {
                            this.isExplanation4 = true;
                        }
                        String str83 = split[i61];
                        Intrinsics.checkNotNull(str83);
                        int length64 = str83.length() - 1;
                        int i76 = 0;
                        boolean z122 = false;
                        while (true) {
                            if (i76 > length64) {
                                obj7 = obj43;
                                break;
                            }
                            obj7 = obj43;
                            boolean z123 = Intrinsics.compare(str83.charAt(!z122 ? i76 : length64), 32) <= 0;
                            if (z122) {
                                if (!z123) {
                                    break;
                                } else {
                                    length64--;
                                }
                            } else if (z123) {
                                i76++;
                            } else {
                                obj43 = obj7;
                                z122 = true;
                            }
                            obj43 = obj7;
                        }
                        Object obj44 = obj6;
                        if (Intrinsics.areEqual(str83.subSequence(i76, length64 + 1).toString(), obj44)) {
                            this.isBranch = true;
                        }
                        String str84 = split[i61];
                        Intrinsics.checkNotNull(str84);
                        int length65 = str84.length() - 1;
                        int i77 = 0;
                        boolean z124 = false;
                        while (true) {
                            if (i77 > length65) {
                                obj6 = obj44;
                                break;
                            }
                            obj6 = obj44;
                            boolean z125 = Intrinsics.compare(str84.charAt(!z124 ? i77 : length65), 32) <= 0;
                            if (z124) {
                                if (!z125) {
                                    break;
                                } else {
                                    length65--;
                                }
                            } else if (z125) {
                                i77++;
                            } else {
                                obj44 = obj6;
                                z124 = true;
                            }
                            obj44 = obj6;
                        }
                        Object obj45 = obj5;
                        if (Intrinsics.areEqual(str84.subSequence(i77, length65 + 1).toString(), obj45)) {
                            this.isFactory = true;
                        }
                        String str85 = split[i61];
                        Intrinsics.checkNotNull(str85);
                        int length66 = str85.length() - 1;
                        int i78 = 0;
                        boolean z126 = false;
                        while (true) {
                            if (i78 > length66) {
                                obj5 = obj45;
                                break;
                            }
                            obj5 = obj45;
                            boolean z127 = Intrinsics.compare(str85.charAt(!z126 ? i78 : length66), 32) <= 0;
                            if (z126) {
                                if (!z127) {
                                    break;
                                } else {
                                    length66--;
                                }
                            } else if (z127) {
                                i78++;
                            } else {
                                obj45 = obj5;
                                z126 = true;
                            }
                            obj45 = obj5;
                        }
                        Object obj46 = obj4;
                        if (Intrinsics.areEqual(str85.subSequence(i78, length66 + 1).toString(), obj46)) {
                            this.isDivision = true;
                        }
                        String str86 = split[i61];
                        Intrinsics.checkNotNull(str86);
                        int length67 = str86.length() - 1;
                        int i79 = 0;
                        boolean z128 = false;
                        while (true) {
                            if (i79 > length67) {
                                obj4 = obj46;
                                break;
                            }
                            obj4 = obj46;
                            boolean z129 = Intrinsics.compare(str86.charAt(!z128 ? i79 : length67), 32) <= 0;
                            if (z128) {
                                if (!z129) {
                                    break;
                                } else {
                                    length67--;
                                }
                            } else if (z129) {
                                i79++;
                            } else {
                                obj46 = obj4;
                                z128 = true;
                            }
                            obj46 = obj4;
                        }
                        Object obj47 = obj3;
                        if (Intrinsics.areEqual(str86.subSequence(i79, length67 + 1).toString(), obj47)) {
                            this.isProject = true;
                        }
                        String str87 = split[i61];
                        Intrinsics.checkNotNull(str87);
                        int length68 = str87.length() - 1;
                        int i80 = 0;
                        boolean z130 = false;
                        while (true) {
                            if (i80 > length68) {
                                obj3 = obj47;
                                break;
                            }
                            obj3 = obj47;
                            boolean z131 = Intrinsics.compare(str87.charAt(!z130 ? i80 : length68), 32) <= 0;
                            if (z130) {
                                if (!z131) {
                                    break;
                                } else {
                                    length68--;
                                }
                            } else if (z131) {
                                i80++;
                            } else {
                                obj47 = obj3;
                                z130 = true;
                            }
                            obj47 = obj3;
                        }
                        if (Intrinsics.areEqual(str87.subSequence(i80, length68 + 1).toString(), "17")) {
                            this.isExplanation5 = true;
                        }
                        String str88 = split[i61];
                        Intrinsics.checkNotNull(str88);
                        int length69 = str88.length() - 1;
                        int i81 = 0;
                        boolean z132 = false;
                        while (i81 <= length69) {
                            boolean z133 = Intrinsics.compare(str88.charAt(!z132 ? i81 : length69), 32) <= 0;
                            if (z132) {
                                if (!z133) {
                                    break;
                                } else {
                                    length69--;
                                }
                            } else if (z133) {
                                i81++;
                            } else {
                                z132 = true;
                            }
                        }
                        if (Intrinsics.areEqual(str88.subSequence(i81, length69 + 1).toString(), "18")) {
                            this.isExplanation6 = true;
                        }
                        String str89 = split[i61];
                        Intrinsics.checkNotNull(str89);
                        int length70 = str89.length() - 1;
                        int i82 = 0;
                        boolean z134 = false;
                        while (i82 <= length70) {
                            boolean z135 = Intrinsics.compare(str89.charAt(!z134 ? i82 : length70), 32) <= 0;
                            if (z134) {
                                if (!z135) {
                                    break;
                                } else {
                                    length70--;
                                }
                            } else if (z135) {
                                i82++;
                            } else {
                                z134 = true;
                            }
                        }
                        if (Intrinsics.areEqual(str89.subSequence(i82, length70 + 1).toString(), "19")) {
                            this.isExplanation7 = true;
                        }
                        String str90 = split[i61];
                        Intrinsics.checkNotNull(str90);
                        int length71 = str90.length() - 1;
                        int i83 = 0;
                        boolean z136 = false;
                        while (i83 <= length71) {
                            boolean z137 = Intrinsics.compare(str90.charAt(!z136 ? i83 : length71), 32) <= 0;
                            if (z136) {
                                if (!z137) {
                                    break;
                                } else {
                                    length71--;
                                }
                            } else if (z137) {
                                i83++;
                            } else {
                                z136 = true;
                            }
                        }
                        if (Intrinsics.areEqual(str90.subSequence(i83, length71 + 1).toString(), "20")) {
                            this.isExplanation8 = true;
                        }
                        String str91 = split[i61];
                        Intrinsics.checkNotNull(str91);
                        int length72 = str91.length() - 1;
                        int i84 = 0;
                        boolean z138 = false;
                        while (i84 <= length72) {
                            boolean z139 = Intrinsics.compare(str91.charAt(!z138 ? i84 : length72), 32) <= 0;
                            if (z138) {
                                if (!z139) {
                                    break;
                                } else {
                                    length72--;
                                }
                            } else if (z139) {
                                i84++;
                            } else {
                                z138 = true;
                            }
                        }
                        if (Intrinsics.areEqual(str91.subSequence(i84, length72 + 1).toString(), "21")) {
                            this.isExplanation9 = true;
                        }
                        String str92 = split[i61];
                        Intrinsics.checkNotNull(str92);
                        int length73 = str92.length() - 1;
                        int i85 = 0;
                        boolean z140 = false;
                        while (i85 <= length73) {
                            boolean z141 = Intrinsics.compare(str92.charAt(!z140 ? i85 : length73), 32) <= 0;
                            if (z140) {
                                if (!z141) {
                                    break;
                                } else {
                                    length73--;
                                }
                            } else if (z141) {
                                i85++;
                            } else {
                                z140 = true;
                            }
                        }
                        if (Intrinsics.areEqual(str92.subSequence(i85, length73 + 1).toString(), "22")) {
                            this.isExplanation10 = true;
                        }
                        i61++;
                        obj21 = obj2;
                        length50 = i8;
                        i36 = 0;
                    }
                    this.isCustomerOrderNo = true;
                    this.isDocumentTrackingNo = true;
                } catch (Exception e12) {
                    e12.printStackTrace();
                }
            }
        }
        i7 = i2;
        i6 = i3;
        i5 = i4;
        z2 = true;
        setDiscount(i7, i6, i5);
        this.isIncludeVAT = erpType != ErpType.NETSIS && !SalesUtils.isSalesTypeWhTransfer(salesType) && z2;
    }
    public SalesFicheHeaderFields(Parcel parcel) {
        super(parcel);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.defaultTradeGroup = "";
        this.defaultDocumentNo = "";
        this.defaultSpeCode = "";
        this.defaultWarrantyCode = "";
        this.defaultExplanation1 = "";
        this.defaultProject = "";
        this.isTradeGroup = parcel.readByte() != 0;
        String readString = parcel.readString();
        this.defaultTradeGroup = readString == null ? "" : readString;
        this.isDocumentNo = parcel.readByte() != 0;
        String readString2 = parcel.readString();
        this.defaultDocumentNo = readString2 == null ? "" : readString2;
        String readString3 = parcel.readString();
        this.defaultSpeCode = readString3 == null ? "" : readString3;
        this.isWarrantyCode = parcel.readByte() != 0;
        String readString4 = parcel.readString();
        this.defaultWarrantyCode = readString4 == null ? "" : readString4;
        this.isReserveOrder = parcel.readByte() != 0;
        this.isShipAddress = parcel.readByte() != 0;
        this.isShipAccount = parcel.readByte() != 0;
        this.isShipType = parcel.readByte() != 0;
        this.isShipAgent = parcel.readByte() != 0;
        this.isExplanation1 = parcel.readByte() != 0;
        String readString5 = parcel.readString();
        this.defaultExplanation1 = readString5 == null ? "" : readString5;
        this.isExplanation2 = parcel.readByte() != 0;
        this.isExplanation3 = parcel.readByte() != 0;
        this.isExplanation4 = parcel.readByte() != 0;
        this.isExplanation5 = parcel.readByte() != 0;
        this.isExplanation6 = parcel.readByte() != 0;
        this.isExplanation7 = parcel.readByte() != 0;
        this.isExplanation8 = parcel.readByte() != 0;
        this.isExplanation9 = parcel.readByte() != 0;
        this.isExplanation10 = parcel.readByte() != 0;
        this.isBranch = parcel.readByte() != 0;
        this.defaultWareHouse = parcel.readInt();
        this.defaultBranch = parcel.readInt();
        this.isDivision = parcel.readByte() != 0;
        this.defaultDivision = parcel.readInt();
        this.isFactory = parcel.readByte() != 0;
        this.isProject = parcel.readByte() != 0;
        String readString6 = parcel.readString();
        this.defaultProject = readString6 == null ? "" : readString6;
        this.isCustomerOrderNo = parcel.readByte() != 0;
        this.isDocumentTrackingNo = parcel.readByte() != 0;
        this.defaultFactory = parcel.readInt();
        this.isConsignee = parcel.readByte() != 0;
        String readString7 = parcel.readString();
        this.defaultTaxPayerCode = readString7 == null ? "" : readString7;
        String readString8 = parcel.readString();
        this.defaultTaxPayerName = readString8 != null ? readString8 : "";
        this.defaultEInvoiceTypSGK = parcel.readInt();
    } 
    public void writeToParcel(Parcel dest, int i2) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        super.writeToParcel(dest, i2);
        dest.writeByte(this.isTradeGroup ? (byte) 1 : (byte) 0);
        dest.writeString(this.defaultTradeGroup);
        dest.writeByte(this.isDocumentNo ? (byte) 1 : (byte) 0);
        dest.writeString(this.defaultDocumentNo);
        dest.writeString(this.defaultSpeCode);
        dest.writeByte(this.isWarrantyCode ? (byte) 1 : (byte) 0);
        dest.writeString(this.defaultWarrantyCode);
        dest.writeByte(this.isReserveOrder ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isShipAddress ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isShipAccount ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isShipType ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isShipAgent ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isExplanation1 ? (byte) 1 : (byte) 0);
        dest.writeString(this.defaultExplanation1);
        dest.writeByte(this.isExplanation2 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isExplanation3 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isExplanation4 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isExplanation5 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isExplanation6 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isExplanation7 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isExplanation8 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isExplanation9 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isExplanation10 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isBranch ? (byte) 1 : (byte) 0);
        dest.writeInt(this.defaultWareHouse);
        dest.writeInt(this.defaultBranch);
        dest.writeByte(this.isDivision ? (byte) 1 : (byte) 0);
        dest.writeInt(this.defaultDivision);
        dest.writeByte(this.isFactory ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isProject ? (byte) 1 : (byte) 0);
        dest.writeString(this.defaultProject);
        dest.writeByte(this.isCustomerOrderNo ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isDocumentTrackingNo ? (byte) 1 : (byte) 0);
        dest.writeInt(this.defaultFactory);
        dest.writeByte(this.isConsignee ? (byte) 1 : (byte) 0);
        dest.writeString(this.defaultTaxPayerCode);
        dest.writeString(this.defaultTaxPayerName);
        dest.writeInt(this.defaultEInvoiceTypSGK);
    } 
    public static  class CREATOR implements Parcelable.Creator<SalesFicheHeaderFields> {
        public  CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private CREATOR() {
        }
        public SalesFicheHeaderFields createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new SalesFicheHeaderFields(source);
        }
        public SalesFicheHeaderFields[] newArray(int i2) {
            return new SalesFicheHeaderFields[i2];
        }
    }
}
