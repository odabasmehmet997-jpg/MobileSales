package com.proje.mobilesales.features.customer.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheImageProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerNew implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private FicheStringProp TCNo;
    private Boolean active;
    private FicheStringProp address1;
    private FicheStringProp address2;
    private FicheDiscountRefProp city;
    private FicheStringProp code;
    private FicheDiscountRefProp country;
    private FicheStringProp customerName;
    private FicheStringProp customerSurname;
    private FicheStringProp email;
    private FicheStringProp email2;
    private FicheStringProp fax;
    private FicheStringProp groupCode;
    private FicheImageProp image;
    private FicheStringProp inCharge;
    private FicheStringProp inChargeDefinition;
    private FicheStringProp inChargeEmail;
    private FicheStringProp inChargeTel;
    private FicheStringProp inChargeTelCode;
    private boolean isTransfer;
    private FicheStringProp iskontoOran;
    private int logicalRef;
    private FicheStringProp name;
    private FicheRefProp payPlan;
    private FicheRefProp payType;
    private FicheBooleanProp personalCompany;
    private FicheStringProp relatedPerson;
    private FicheStringProp speCode;
    private FicheStringProp speCode2;
    private FicheStringProp speCode3;
    private FicheStringProp speCode4;
    private FicheStringProp speCode5;
    private FicheStringProp taxNo;
    private FicheStringProp taxOffice;
    private FicheStringProp tel1;
    private FicheStringProp tel2;
    private FicheDiscountRefProp town;
    private FicheStringProp vade;
    private FicheStringProp warrantyCode;
    private FicheStringProp zipCode;

    public CustomerNew() {
        this(0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, null, null, null, null, -1, 255, null);
    }

    public int component1() {
        return this.logicalRef;
    }

    public FicheStringProp component10() {
        return this.warrantyCode;
    }

    public FicheStringProp component11() {
        return this.relatedPerson;
    }

    public FicheStringProp component12() {
        return this.taxOffice;
    }

    public FicheStringProp component13() {
        return this.taxNo;
    }

    public FicheStringProp component14() {
        return this.customerName;
    }

    public FicheStringProp component15() {
        return this.customerSurname;
    }

    public FicheStringProp component16() {
        return this.email;
    }

    public FicheStringProp component17() {
        return this.email2;
    }

    public FicheStringProp component18() {
        return this.tel1;
    }

    public FicheStringProp component19() {
        return this.tel2;
    }

    public FicheStringProp component2() {
        return this.code;
    }

    public FicheStringProp component20() {
        return this.fax;
    }

    public FicheStringProp component21() {
        return this.address1;
    }

    public FicheStringProp component22() {
        return this.address2;
    }

    public FicheStringProp component23() {
        return this.zipCode;
    }

    public FicheRefProp component24() {
        return this.payPlan;
    }

    public FicheImageProp component25() {
        return this.image;
    }

    public Boolean component26() {
        return this.active;
    }

    public boolean component27() {
        return this.isTransfer;
    }

    public FicheStringProp component28() {
        return this.TCNo;
    }

    public FicheRefProp component29() {
        return this.payType;
    }

    public FicheStringProp component3() {
        return this.name;
    }

    public FicheStringProp component30() {
        return this.vade;
    }

    public FicheStringProp component31() {
        return this.iskontoOran;
    }

    public FicheDiscountRefProp component32() {
        return this.country;
    }

    public FicheDiscountRefProp component33() {
        return this.city;
    }

    public FicheBooleanProp component34() {
        return this.personalCompany;
    }

    public FicheDiscountRefProp component35() {
        return this.town;
    }

    public FicheStringProp component36() {
        return this.inCharge;
    }

    public FicheStringProp component37() {
        return this.inChargeDefinition;
    }

    public FicheStringProp component38() {
        return this.inChargeTel;
    }

    public FicheStringProp component39() {
        return this.inChargeTelCode;
    }

    public FicheStringProp component4() {
        return this.speCode;
    }

    public FicheStringProp component40() {
        return this.inChargeEmail;
    }

    public FicheStringProp component5() {
        return this.speCode2;
    }

    public FicheStringProp component6() {
        return this.speCode3;
    }

    public FicheStringProp component7() {
        return this.speCode4;
    }

    public FicheStringProp component8() {
        return this.speCode5;
    }

    public FicheStringProp component9() {
        return this.groupCode;
    }

    public CustomerNew copy(int i2, FicheStringProp ficheStringProp, FicheStringProp ficheStringProp2, FicheStringProp ficheStringProp3, FicheStringProp ficheStringProp4, FicheStringProp ficheStringProp5, FicheStringProp ficheStringProp6, FicheStringProp ficheStringProp7, FicheStringProp ficheStringProp8, FicheStringProp ficheStringProp9, FicheStringProp ficheStringProp10, FicheStringProp ficheStringProp11, FicheStringProp ficheStringProp12, FicheStringProp ficheStringProp13, FicheStringProp ficheStringProp14, FicheStringProp ficheStringProp15, FicheStringProp ficheStringProp16, FicheStringProp ficheStringProp17, FicheStringProp ficheStringProp18, FicheStringProp ficheStringProp19, FicheStringProp ficheStringProp20, FicheStringProp ficheStringProp21, FicheStringProp ficheStringProp22, FicheRefProp ficheRefProp, FicheImageProp ficheImageProp, Boolean bool, boolean z, FicheStringProp ficheStringProp23, FicheRefProp ficheRefProp2, FicheStringProp ficheStringProp24, FicheStringProp ficheStringProp25, FicheDiscountRefProp ficheDiscountRefProp, FicheDiscountRefProp ficheDiscountRefProp2, FicheBooleanProp ficheBooleanProp, FicheDiscountRefProp ficheDiscountRefProp3, FicheStringProp ficheStringProp26, FicheStringProp ficheStringProp27, FicheStringProp ficheStringProp28, FicheStringProp ficheStringProp29, FicheStringProp ficheStringProp30) {
        return new CustomerNew(i2, ficheStringProp, ficheStringProp2, ficheStringProp3, ficheStringProp4, ficheStringProp5, ficheStringProp6, ficheStringProp7, ficheStringProp8, ficheStringProp9, ficheStringProp10, ficheStringProp11, ficheStringProp12, ficheStringProp13, ficheStringProp14, ficheStringProp15, ficheStringProp16, ficheStringProp17, ficheStringProp18, ficheStringProp19, ficheStringProp20, ficheStringProp21, ficheStringProp22, ficheRefProp, ficheImageProp, bool, z, ficheStringProp23, ficheRefProp2, ficheStringProp24, ficheStringProp25, ficheDiscountRefProp, ficheDiscountRefProp2, ficheBooleanProp, ficheDiscountRefProp3, ficheStringProp26, ficheStringProp27, ficheStringProp28, ficheStringProp29, ficheStringProp30);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomerNew customerNew)) {
            return false;
        }
        return this.logicalRef == customerNew.logicalRef && Intrinsics.areEqual(this.code, customerNew.code) && Intrinsics.areEqual(this.name, customerNew.name) && Intrinsics.areEqual(this.speCode, customerNew.speCode) && Intrinsics.areEqual(this.speCode2, customerNew.speCode2) && Intrinsics.areEqual(this.speCode3, customerNew.speCode3) && Intrinsics.areEqual(this.speCode4, customerNew.speCode4) && Intrinsics.areEqual(this.speCode5, customerNew.speCode5) && Intrinsics.areEqual(this.groupCode, customerNew.groupCode) && Intrinsics.areEqual(this.warrantyCode, customerNew.warrantyCode) && Intrinsics.areEqual(this.relatedPerson, customerNew.relatedPerson) && Intrinsics.areEqual(this.taxOffice, customerNew.taxOffice) && Intrinsics.areEqual(this.taxNo, customerNew.taxNo) && Intrinsics.areEqual(this.customerName, customerNew.customerName) && Intrinsics.areEqual(this.customerSurname, customerNew.customerSurname) && Intrinsics.areEqual(this.email, customerNew.email) && Intrinsics.areEqual(this.email2, customerNew.email2) && Intrinsics.areEqual(this.tel1, customerNew.tel1) && Intrinsics.areEqual(this.tel2, customerNew.tel2) && Intrinsics.areEqual(this.fax, customerNew.fax) && Intrinsics.areEqual(this.address1, customerNew.address1) && Intrinsics.areEqual(this.address2, customerNew.address2) && Intrinsics.areEqual(this.zipCode, customerNew.zipCode) && Intrinsics.areEqual(this.payPlan, customerNew.payPlan) && Intrinsics.areEqual(this.image, customerNew.image) && Intrinsics.areEqual(this.active, customerNew.active) && this.isTransfer == customerNew.isTransfer && Intrinsics.areEqual(this.TCNo, customerNew.TCNo) && Intrinsics.areEqual(this.payType, customerNew.payType) && Intrinsics.areEqual(this.vade, customerNew.vade) && Intrinsics.areEqual(this.iskontoOran, customerNew.iskontoOran) && Intrinsics.areEqual(this.country, customerNew.country) && Intrinsics.areEqual(this.city, customerNew.city) && Intrinsics.areEqual(this.personalCompany, customerNew.personalCompany) && Intrinsics.areEqual(this.town, customerNew.town) && Intrinsics.areEqual(this.inCharge, customerNew.inCharge) && Intrinsics.areEqual(this.inChargeDefinition, customerNew.inChargeDefinition) && Intrinsics.areEqual(this.inChargeTel, customerNew.inChargeTel) && Intrinsics.areEqual(this.inChargeTelCode, customerNew.inChargeTelCode) && Intrinsics.areEqual(this.inChargeEmail, customerNew.inChargeEmail);
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.logicalRef) * 31;
        FicheStringProp ficheStringProp = this.code;
        int hashCode2 = (hashCode + (ficheStringProp == null ? 0 : ficheStringProp.hashCode())) * 31;
        FicheStringProp ficheStringProp2 = this.name;
        int hashCode3 = (hashCode2 + (ficheStringProp2 == null ? 0 : ficheStringProp2.hashCode())) * 31;
        FicheStringProp ficheStringProp3 = this.speCode;
        int hashCode4 = (hashCode3 + (ficheStringProp3 == null ? 0 : ficheStringProp3.hashCode())) * 31;
        FicheStringProp ficheStringProp4 = this.speCode2;
        int hashCode5 = (hashCode4 + (ficheStringProp4 == null ? 0 : ficheStringProp4.hashCode())) * 31;
        FicheStringProp ficheStringProp5 = this.speCode3;
        int hashCode6 = (hashCode5 + (ficheStringProp5 == null ? 0 : ficheStringProp5.hashCode())) * 31;
        FicheStringProp ficheStringProp6 = this.speCode4;
        int hashCode7 = (hashCode6 + (ficheStringProp6 == null ? 0 : ficheStringProp6.hashCode())) * 31;
        FicheStringProp ficheStringProp7 = this.speCode5;
        int hashCode8 = (hashCode7 + (ficheStringProp7 == null ? 0 : ficheStringProp7.hashCode())) * 31;
        FicheStringProp ficheStringProp8 = this.groupCode;
        int hashCode9 = (hashCode8 + (ficheStringProp8 == null ? 0 : ficheStringProp8.hashCode())) * 31;
        FicheStringProp ficheStringProp9 = this.warrantyCode;
        int hashCode10 = (hashCode9 + (ficheStringProp9 == null ? 0 : ficheStringProp9.hashCode())) * 31;
        FicheStringProp ficheStringProp10 = this.relatedPerson;
        int hashCode11 = (hashCode10 + (ficheStringProp10 == null ? 0 : ficheStringProp10.hashCode())) * 31;
        FicheStringProp ficheStringProp11 = this.taxOffice;
        int hashCode12 = (hashCode11 + (ficheStringProp11 == null ? 0 : ficheStringProp11.hashCode())) * 31;
        FicheStringProp ficheStringProp12 = this.taxNo;
        int hashCode13 = (hashCode12 + (ficheStringProp12 == null ? 0 : ficheStringProp12.hashCode())) * 31;
        FicheStringProp ficheStringProp13 = this.customerName;
        int hashCode14 = (hashCode13 + (ficheStringProp13 == null ? 0 : ficheStringProp13.hashCode())) * 31;
        FicheStringProp ficheStringProp14 = this.customerSurname;
        int hashCode15 = (hashCode14 + (ficheStringProp14 == null ? 0 : ficheStringProp14.hashCode())) * 31;
        FicheStringProp ficheStringProp15 = this.email;
        int hashCode16 = (hashCode15 + (ficheStringProp15 == null ? 0 : ficheStringProp15.hashCode())) * 31;
        FicheStringProp ficheStringProp16 = this.email2;
        int hashCode17 = (hashCode16 + (ficheStringProp16 == null ? 0 : ficheStringProp16.hashCode())) * 31;
        FicheStringProp ficheStringProp17 = this.tel1;
        int hashCode18 = (hashCode17 + (ficheStringProp17 == null ? 0 : ficheStringProp17.hashCode())) * 31;
        FicheStringProp ficheStringProp18 = this.tel2;
        int hashCode19 = (hashCode18 + (ficheStringProp18 == null ? 0 : ficheStringProp18.hashCode())) * 31;
        FicheStringProp ficheStringProp19 = this.fax;
        int hashCode20 = (hashCode19 + (ficheStringProp19 == null ? 0 : ficheStringProp19.hashCode())) * 31;
        FicheStringProp ficheStringProp20 = this.address1;
        int hashCode21 = (hashCode20 + (ficheStringProp20 == null ? 0 : ficheStringProp20.hashCode())) * 31;
        FicheStringProp ficheStringProp21 = this.address2;
        int hashCode22 = (hashCode21 + (ficheStringProp21 == null ? 0 : ficheStringProp21.hashCode())) * 31;
        FicheStringProp ficheStringProp22 = this.zipCode;
        int hashCode23 = (hashCode22 + (ficheStringProp22 == null ? 0 : ficheStringProp22.hashCode())) * 31;
        FicheRefProp ficheRefProp = this.payPlan;
        int hashCode24 = (hashCode23 + (ficheRefProp == null ? 0 : ficheRefProp.hashCode())) * 31;
        FicheImageProp ficheImageProp = this.image;
        int hashCode25 = (hashCode24 + (ficheImageProp == null ? 0 : ficheImageProp.hashCode())) * 31;
        Boolean bool = this.active;
        int hashCode26 = (((hashCode25 + (bool == null ? 0 : bool.hashCode())) * 31) + Boolean.hashCode(this.isTransfer)) * 31;
        FicheStringProp ficheStringProp23 = this.TCNo;
        int hashCode27 = (hashCode26 + (ficheStringProp23 == null ? 0 : ficheStringProp23.hashCode())) * 31;
        FicheRefProp ficheRefProp2 = this.payType;
        int hashCode28 = (hashCode27 + (ficheRefProp2 == null ? 0 : ficheRefProp2.hashCode())) * 31;
        FicheStringProp ficheStringProp24 = this.vade;
        int hashCode29 = (hashCode28 + (ficheStringProp24 == null ? 0 : ficheStringProp24.hashCode())) * 31;
        FicheStringProp ficheStringProp25 = this.iskontoOran;
        int hashCode30 = (hashCode29 + (ficheStringProp25 == null ? 0 : ficheStringProp25.hashCode())) * 31;
        FicheDiscountRefProp ficheDiscountRefProp = this.country;
        int hashCode31 = (hashCode30 + (ficheDiscountRefProp == null ? 0 : ficheDiscountRefProp.hashCode())) * 31;
        FicheDiscountRefProp ficheDiscountRefProp2 = this.city;
        int hashCode32 = (hashCode31 + (ficheDiscountRefProp2 == null ? 0 : ficheDiscountRefProp2.hashCode())) * 31;
        FicheBooleanProp ficheBooleanProp = this.personalCompany;
        int hashCode33 = (hashCode32 + (ficheBooleanProp == null ? 0 : ficheBooleanProp.hashCode())) * 31;
        FicheDiscountRefProp ficheDiscountRefProp3 = this.town;
        int hashCode34 = (hashCode33 + (ficheDiscountRefProp3 == null ? 0 : ficheDiscountRefProp3.hashCode())) * 31;
        FicheStringProp ficheStringProp26 = this.inCharge;
        int hashCode35 = (hashCode34 + (ficheStringProp26 == null ? 0 : ficheStringProp26.hashCode())) * 31;
        FicheStringProp ficheStringProp27 = this.inChargeDefinition;
        int hashCode36 = (hashCode35 + (ficheStringProp27 == null ? 0 : ficheStringProp27.hashCode())) * 31;
        FicheStringProp ficheStringProp28 = this.inChargeTel;
        int hashCode37 = (hashCode36 + (ficheStringProp28 == null ? 0 : ficheStringProp28.hashCode())) * 31;
        FicheStringProp ficheStringProp29 = this.inChargeTelCode;
        int hashCode38 = (hashCode37 + (ficheStringProp29 == null ? 0 : ficheStringProp29.hashCode())) * 31;
        FicheStringProp ficheStringProp30 = this.inChargeEmail;
        return hashCode38 + (ficheStringProp30 != null ? ficheStringProp30.hashCode() : 0);
    }

    public String toString() {
        return "CustomerNew(logicalRef=" + this.logicalRef + ", code=" + this.code + ", name=" + this.name + ", speCode=" + this.speCode + ", speCode2=" + this.speCode2 + ", speCode3=" + this.speCode3 + ", speCode4=" + this.speCode4 + ", speCode5=" + this.speCode5 + ", groupCode=" + this.groupCode + ", warrantyCode=" + this.warrantyCode + ", relatedPerson=" + this.relatedPerson + ", taxOffice=" + this.taxOffice + ", taxNo=" + this.taxNo + ", customerName=" + this.customerName + ", customerSurname=" + this.customerSurname + ", email=" + this.email + ", email2=" + this.email2 + ", tel1=" + this.tel1 + ", tel2=" + this.tel2 + ", fax=" + this.fax + ", address1=" + this.address1 + ", address2=" + this.address2 + ", zipCode=" + this.zipCode + ", payPlan=" + this.payPlan + ", image=" + this.image + ", active=" + this.active + ", isTransfer=" + this.isTransfer + ", TCNo=" + this.TCNo + ", payType=" + this.payType + ", vade=" + this.vade + ", iskontoOran=" + this.iskontoOran + ", country=" + this.country + ", city=" + this.city + ", personalCompany=" + this.personalCompany + ", town=" + this.town + ", inCharge=" + this.inCharge + ", inChargeDefinition=" + this.inChargeDefinition + ", inChargeTel=" + this.inChargeTel + ", inChargeTelCode=" + this.inChargeTelCode + ", inChargeEmail=" + this.inChargeEmail + ')';
    }

    public CustomerNew(int i2, FicheStringProp ficheStringProp, FicheStringProp ficheStringProp2, FicheStringProp ficheStringProp3, FicheStringProp ficheStringProp4, FicheStringProp ficheStringProp5, FicheStringProp ficheStringProp6, FicheStringProp ficheStringProp7, FicheStringProp ficheStringProp8, FicheStringProp ficheStringProp9, FicheStringProp ficheStringProp10, FicheStringProp ficheStringProp11, FicheStringProp ficheStringProp12, FicheStringProp ficheStringProp13, FicheStringProp ficheStringProp14, FicheStringProp ficheStringProp15, FicheStringProp ficheStringProp16, FicheStringProp ficheStringProp17, FicheStringProp ficheStringProp18, FicheStringProp ficheStringProp19, FicheStringProp ficheStringProp20, FicheStringProp ficheStringProp21, FicheStringProp ficheStringProp22, FicheRefProp ficheRefProp, FicheImageProp ficheImageProp, Boolean bool, boolean z, FicheStringProp ficheStringProp23, FicheRefProp ficheRefProp2, FicheStringProp ficheStringProp24, FicheStringProp ficheStringProp25, FicheDiscountRefProp ficheDiscountRefProp, FicheDiscountRefProp ficheDiscountRefProp2, FicheBooleanProp ficheBooleanProp, FicheDiscountRefProp ficheDiscountRefProp3, FicheStringProp ficheStringProp26, FicheStringProp ficheStringProp27, FicheStringProp ficheStringProp28, FicheStringProp ficheStringProp29, FicheStringProp ficheStringProp30) {
        this.logicalRef = i2;
        this.code = ficheStringProp;
        this.name = ficheStringProp2;
        this.speCode = ficheStringProp3;
        this.speCode2 = ficheStringProp4;
        this.speCode3 = ficheStringProp5;
        this.speCode4 = ficheStringProp6;
        this.speCode5 = ficheStringProp7;
        this.groupCode = ficheStringProp8;
        this.warrantyCode = ficheStringProp9;
        this.relatedPerson = ficheStringProp10;
        this.taxOffice = ficheStringProp11;
        this.taxNo = ficheStringProp12;
        this.customerName = ficheStringProp13;
        this.customerSurname = ficheStringProp14;
        this.email = ficheStringProp15;
        this.email2 = ficheStringProp16;
        this.tel1 = ficheStringProp17;
        this.tel2 = ficheStringProp18;
        this.fax = ficheStringProp19;
        this.address1 = ficheStringProp20;
        this.address2 = ficheStringProp21;
        this.zipCode = ficheStringProp22;
        this.payPlan = ficheRefProp;
        this.image = ficheImageProp;
        this.active = bool;
        this.isTransfer = z;
        this.TCNo = ficheStringProp23;
        this.payType = ficheRefProp2;
        this.vade = ficheStringProp24;
        this.iskontoOran = ficheStringProp25;
        this.country = ficheDiscountRefProp;
        this.city = ficheDiscountRefProp2;
        this.personalCompany = ficheBooleanProp;
        this.town = ficheDiscountRefProp3;
        this.inCharge = ficheStringProp26;
        this.inChargeDefinition = ficheStringProp27;
        this.inChargeTel = ficheStringProp28;
        this.inChargeTelCode = ficheStringProp29;
        this.inChargeEmail = ficheStringProp30;
    }

    public CustomerNew(int i2, FicheStringProp ficheStringProp, FicheStringProp ficheStringProp2, FicheStringProp ficheStringProp3, FicheStringProp ficheStringProp4, FicheStringProp ficheStringProp5, FicheStringProp ficheStringProp6, FicheStringProp ficheStringProp7, FicheStringProp ficheStringProp8, FicheStringProp ficheStringProp9, FicheStringProp ficheStringProp10, FicheStringProp ficheStringProp11, FicheStringProp ficheStringProp12, FicheStringProp ficheStringProp13, FicheStringProp ficheStringProp14, FicheStringProp ficheStringProp15, FicheStringProp ficheStringProp16, FicheStringProp ficheStringProp17, FicheStringProp ficheStringProp18, FicheStringProp ficheStringProp19, FicheStringProp ficheStringProp20, FicheStringProp ficheStringProp21, FicheStringProp ficheStringProp22, FicheRefProp ficheRefProp, FicheImageProp ficheImageProp, Boolean bool, boolean z, FicheStringProp ficheStringProp23, FicheRefProp ficheRefProp2, FicheStringProp ficheStringProp24, FicheStringProp ficheStringProp25, FicheDiscountRefProp ficheDiscountRefProp, FicheDiscountRefProp ficheDiscountRefProp2, FicheBooleanProp ficheBooleanProp, FicheDiscountRefProp ficheDiscountRefProp3, FicheStringProp ficheStringProp26, FicheStringProp ficheStringProp27, FicheStringProp ficheStringProp28, FicheStringProp ficheStringProp29, FicheStringProp ficheStringProp30, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2, (i3 & 2) != 0 ? null : ficheStringProp, (i3 & 4) != 0 ? null : ficheStringProp2, (i3 & 8) != 0 ? null : ficheStringProp3, (i3 & 16) != 0 ? null : ficheStringProp4, (i3 & 32) != 0 ? null : ficheStringProp5, (i3 & 64) != 0 ? null : ficheStringProp6, (i3 & 128) != 0 ? null : ficheStringProp7, (i3 & 256) != 0 ? null : ficheStringProp8, (i3 & 512) != 0 ? null : ficheStringProp9, (i3 & 1024) != 0 ? null : ficheStringProp10, (i3 & 2048) != 0 ? null : ficheStringProp11, (i3 & 4096) != 0 ? null : ficheStringProp12, (i3 & 8192) != 0 ? null : ficheStringProp13, (i3 & 16384) != 0 ? null : ficheStringProp14, (i3 & 32768) != 0 ? null : ficheStringProp15, (i3 & 65536) != 0 ? null : ficheStringProp16, (i3 & 131072) != 0 ? null : ficheStringProp17, (i3 & 262144) != 0 ? null : ficheStringProp18, (i3 & 524288) != 0 ? null : ficheStringProp19, (i3 & 1048576) != 0 ? null : ficheStringProp20, (i3 & 2097152) != 0 ? null : ficheStringProp21, (i3 & 4194304) != 0 ? null : ficheStringProp22, (i3 & 8388608) != 0 ? null : ficheRefProp, (i3 & 16777216) != 0 ? null : ficheImageProp, (i3 & 33554432) != 0 ? null : bool, (i3 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) == 0 && z, (i3 & 134217728) != 0 ? null : ficheStringProp23, (i3 & 268435456) != 0 ? null : ficheRefProp2, (i3 & 536870912) != 0 ? null : ficheStringProp24, (i3 & BasicMeasure.EXACTLY) != 0 ? null : ficheStringProp25, (i3 & Integer.MIN_VALUE) != 0 ? null : ficheDiscountRefProp, (i4 & 1) != 0 ? null : ficheDiscountRefProp2, (i4 & 2) != 0 ? null : ficheBooleanProp, (i4 & 4) != 0 ? null : ficheDiscountRefProp3, (i4 & 8) != 0 ? null : ficheStringProp26, (i4 & 16) != 0 ? null : ficheStringProp27, (i4 & 32) != 0 ? null : ficheStringProp28, (i4 & 64) != 0 ? null : ficheStringProp29, (i4 & 128) != 0 ? null : ficheStringProp30);
    }

    public int getLogicalRef() {
        return this.logicalRef;
    }

    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }

    public FicheStringProp getCode() {
        return this.code;
    }

    public void setCode(FicheStringProp ficheStringProp) {
        this.code = ficheStringProp;
    }

    public FicheStringProp getName() {
        return this.name;
    }

    public void setName(FicheStringProp ficheStringProp) {
        this.name = ficheStringProp;
    }

    public FicheStringProp getSpeCode() {
        return this.speCode;
    }

    public void setSpeCode(FicheStringProp ficheStringProp) {
        this.speCode = ficheStringProp;
    }

    public FicheStringProp getSpeCode2() {
        return this.speCode2;
    }

    public void setSpeCode2(FicheStringProp ficheStringProp) {
        this.speCode2 = ficheStringProp;
    }

    public FicheStringProp getSpeCode3() {
        return this.speCode3;
    }

    public void setSpeCode3(FicheStringProp ficheStringProp) {
        this.speCode3 = ficheStringProp;
    }

    public FicheStringProp getSpeCode4() {
        return this.speCode4;
    }

    public void setSpeCode4(FicheStringProp ficheStringProp) {
        this.speCode4 = ficheStringProp;
    }

    public FicheStringProp getSpeCode5() {
        return this.speCode5;
    }

    public void setSpeCode5(FicheStringProp ficheStringProp) {
        this.speCode5 = ficheStringProp;
    }

    public FicheStringProp getGroupCode() {
        return this.groupCode;
    }

    public void setGroupCode(FicheStringProp ficheStringProp) {
        this.groupCode = ficheStringProp;
    }

    public FicheStringProp getWarrantyCode() {
        return this.warrantyCode;
    }

    public void setWarrantyCode(FicheStringProp ficheStringProp) {
        this.warrantyCode = ficheStringProp;
    }

    public FicheStringProp getRelatedPerson() {
        return this.relatedPerson;
    }

    public void setRelatedPerson(FicheStringProp ficheStringProp) {
        this.relatedPerson = ficheStringProp;
    }

    public FicheStringProp getTaxOffice() {
        return this.taxOffice;
    }

    public void setTaxOffice(FicheStringProp ficheStringProp) {
        this.taxOffice = ficheStringProp;
    }

    public FicheStringProp getTaxNo() {
        return this.taxNo;
    }

    public void setTaxNo(FicheStringProp ficheStringProp) {
        this.taxNo = ficheStringProp;
    }

    public FicheStringProp getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(FicheStringProp ficheStringProp) {
        this.customerName = ficheStringProp;
    }

    public FicheStringProp getCustomerSurname() {
        return this.customerSurname;
    }

    public void setCustomerSurname(FicheStringProp ficheStringProp) {
        this.customerSurname = ficheStringProp;
    }

    public FicheStringProp getEmail() {
        return this.email;
    }

    public void setEmail(FicheStringProp ficheStringProp) {
        this.email = ficheStringProp;
    }

    public FicheStringProp getEmail2() {
        return this.email2;
    }

    public void setEmail2(FicheStringProp ficheStringProp) {
        this.email2 = ficheStringProp;
    }

    public FicheStringProp getTel1() {
        return this.tel1;
    }

    public void setTel1(FicheStringProp ficheStringProp) {
        this.tel1 = ficheStringProp;
    }

    public FicheStringProp getTel2() {
        return this.tel2;
    }

    public void setTel2(FicheStringProp ficheStringProp) {
        this.tel2 = ficheStringProp;
    }

    public FicheStringProp getFax() {
        return this.fax;
    }

    public void setFax(FicheStringProp ficheStringProp) {
        this.fax = ficheStringProp;
    }

    public FicheStringProp getAddress1() {
        return this.address1;
    }

    public void setAddress1(FicheStringProp ficheStringProp) {
        this.address1 = ficheStringProp;
    }

    public FicheStringProp getAddress2() {
        return this.address2;
    }

    public void setAddress2(FicheStringProp ficheStringProp) {
        this.address2 = ficheStringProp;
    }

    public FicheStringProp getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(FicheStringProp ficheStringProp) {
        this.zipCode = ficheStringProp;
    }

    public FicheRefProp getPayPlan() {
        return this.payPlan;
    }

    public void setPayPlan(FicheRefProp ficheRefProp) {
        this.payPlan = ficheRefProp;
    }

    public FicheImageProp getImage() {
        return this.image;
    }

    public void setImage(FicheImageProp ficheImageProp) {
        this.image = ficheImageProp;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean bool) {
        this.active = bool;
    }

    public boolean isTransfer() {
        return this.isTransfer;
    }

    public void setTransfer(boolean z) {
        this.isTransfer = z;
    }

    public FicheStringProp getTCNo() {
        return this.TCNo;
    }

    public void setTCNo(FicheStringProp ficheStringProp) {
        this.TCNo = ficheStringProp;
    }

    public FicheRefProp getPayType() {
        return this.payType;
    }

    public void setPayType(FicheRefProp ficheRefProp) {
        this.payType = ficheRefProp;
    }

    public FicheStringProp getVade() {
        return this.vade;
    }

    public void setVade(FicheStringProp ficheStringProp) {
        this.vade = ficheStringProp;
    }

    public FicheStringProp getIskontoOran() {
        return this.iskontoOran;
    }

    public void setIskontoOran(FicheStringProp ficheStringProp) {
        this.iskontoOran = ficheStringProp;
    }

    public FicheDiscountRefProp getCountry() {
        return this.country;
    }

    public void setCountry(FicheDiscountRefProp ficheDiscountRefProp) {
        this.country = ficheDiscountRefProp;
    }

    public FicheDiscountRefProp getCity() {
        return this.city;
    }

    public void setCity(FicheDiscountRefProp ficheDiscountRefProp) {
        this.city = ficheDiscountRefProp;
    }

    public FicheBooleanProp getPersonalCompany() {
        return this.personalCompany;
    }

    public void setPersonalCompany(FicheBooleanProp ficheBooleanProp) {
        this.personalCompany = ficheBooleanProp;
    }

    public FicheDiscountRefProp getTown() {
        return this.town;
    }

    public void setTown(FicheDiscountRefProp ficheDiscountRefProp) {
        this.town = ficheDiscountRefProp;
    }

    public FicheStringProp getInCharge() {
        return this.inCharge;
    }

    public void setInCharge(FicheStringProp ficheStringProp) {
        this.inCharge = ficheStringProp;
    }

    public FicheStringProp getInChargeDefinition() {
        return this.inChargeDefinition;
    }

    public void setInChargeDefinition(FicheStringProp ficheStringProp) {
        this.inChargeDefinition = ficheStringProp;
    }

    public FicheStringProp getInChargeTel() {
        return this.inChargeTel;
    }

    public void setInChargeTel(FicheStringProp ficheStringProp) {
        this.inChargeTel = ficheStringProp;
    }

    public FicheStringProp getInChargeTelCode() {
        return this.inChargeTelCode;
    }

    public void setInChargeTelCode(FicheStringProp ficheStringProp) {
        this.inChargeTelCode = ficheStringProp;
    }

    public FicheStringProp getInChargeEmail() {
        return this.inChargeEmail;
    }

    public void setInChargeEmail(FicheStringProp ficheStringProp) {
        this.inChargeEmail = ficheStringProp;
    }

    public CustomerNew(Parcel parcel) {
        this(0, null, null, null, null, null, null, null, null, null, null, null, null, r16, r16, r16, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, null, null, null, null, -1, 255, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        FicheStringProp ficheStringProp = null;
        this.logicalRef = parcel.readInt();
        this.code = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.name = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.speCode = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.speCode2 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.speCode3 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.speCode4 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.speCode5 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.groupCode = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.warrantyCode = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.relatedPerson = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.taxOffice = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.taxNo = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.customerName = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.customerSurname = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.email = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.email2 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.tel1 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.tel2 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.fax = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.address1 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.address2 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.zipCode = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.payPlan = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.image = parcel.readParcelable(FicheImageProp.class.getClassLoader());
        this.active = Boolean.valueOf(parcel.readByte() != 0);
        this.isTransfer = parcel.readByte() != 0;
        this.TCNo = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.payType = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.vade = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.iskontoOran = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.city = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        this.town = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        this.inCharge = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        this.inChargeDefinition = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.inChargeTel = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.inChargeTelCode = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.inChargeEmail = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.country = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        this.personalCompany = parcel.readParcelable(FicheBooleanProp.class.getClassLoader());
    }

    public CustomerNew(String str) {
        this(0, null, null, null, null, null, null, null, null, null, null, null, null, null, r16, r16, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, null, null, null, null, -1, 255, null);
        FicheStringProp ficheStringProp = null;
        this.code = new FicheStringProp(str);
        this.name = new FicheStringProp();
        this.speCode = new FicheStringProp();
        this.speCode2 = new FicheStringProp();
        this.speCode3 = new FicheStringProp();
        this.speCode4 = new FicheStringProp();
        this.speCode5 = new FicheStringProp();
        this.groupCode = new FicheStringProp();
        this.warrantyCode = new FicheStringProp();
        this.relatedPerson = new FicheStringProp();
        this.taxOffice = new FicheStringProp();
        this.taxNo = new FicheStringProp();
        this.customerName = new FicheStringProp();
        this.customerSurname = new FicheStringProp();
        this.email = new FicheStringProp();
        this.email2 = new FicheStringProp();
        this.tel1 = new FicheStringProp();
        this.tel2 = new FicheStringProp();
        this.fax = new FicheStringProp();
        this.address1 = new FicheStringProp();
        this.address2 = new FicheStringProp();
        this.zipCode = new FicheStringProp();
        this.payPlan = new FicheRefProp();
        this.image = new FicheImageProp();
        this.TCNo = new FicheStringProp();
        this.payType = new FicheRefProp(0, -1, "");
        this.vade = new FicheStringProp();
        this.iskontoOran = new FicheStringProp();
        this.city = new FicheDiscountRefProp();
        this.town = new FicheDiscountRefProp();
        this.inCharge = new FicheStringProp();
        this.inChargeDefinition = new FicheStringProp();
        this.inChargeTel = new FicheStringProp();
        this.inChargeTelCode = new FicheStringProp();
        this.inChargeEmail = new FicheStringProp();
        this.country = new FicheDiscountRefProp();
        this.personalCompany = new FicheBooleanProp();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(this.logicalRef);
        parcel.writeParcelable(this.code, i2);
        parcel.writeParcelable(this.name, i2);
        parcel.writeParcelable(this.speCode, i2);
        parcel.writeParcelable(this.speCode2, i2);
        parcel.writeParcelable(this.speCode3, i2);
        parcel.writeParcelable(this.speCode4, i2);
        parcel.writeParcelable(this.speCode5, i2);
        parcel.writeParcelable(this.groupCode, i2);
        parcel.writeParcelable(this.warrantyCode, i2);
        parcel.writeParcelable(this.relatedPerson, i2);
        parcel.writeParcelable(this.taxOffice, i2);
        parcel.writeParcelable(this.taxNo, i2);
        parcel.writeParcelable(this.customerName, i2);
        parcel.writeParcelable(this.customerSurname, i2);
        parcel.writeParcelable(this.email, i2);
        parcel.writeParcelable(this.email2, i2);
        parcel.writeParcelable(this.tel1, i2);
        parcel.writeParcelable(this.tel2, i2);
        parcel.writeParcelable(this.fax, i2);
        parcel.writeParcelable(this.address1, i2);
        parcel.writeParcelable(this.address2, i2);
        parcel.writeParcelable(this.zipCode, i2);
        parcel.writeParcelable(this.payPlan, i2);
        parcel.writeParcelable(this.image, i2);
        parcel.writeByte(Intrinsics.areEqual(this.active, Boolean.TRUE) ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isTransfer ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.TCNo, i2);
        parcel.writeParcelable(this.payType, i2);
        parcel.writeParcelable(this.vade, i2);
        parcel.writeParcelable(this.iskontoOran, i2);
        parcel.writeParcelable(this.city, i2);
        parcel.writeParcelable(this.town, i2);
        parcel.writeParcelable(this.inCharge, i2);
        parcel.writeParcelable(this.inChargeDefinition, i2);
        parcel.writeParcelable(this.inChargeTel, i2);
        parcel.writeParcelable(this.inChargeTelCode, i2);
        parcel.writeParcelable(this.inChargeEmail, i2);
        parcel.writeParcelable(this.country, i2);
        parcel.writeParcelable(this.personalCompany, i2);
    }

    /* compiled from: CustomerNew.kt */
    public static final class CREATOR implements Parcelable.Creator<CustomerNew> {
        public CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }

        
        
        public CustomerNew createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new CustomerNew(source);
        }

        
        
        public CustomerNew[] newArray(int i2) {
            return new CustomerNew[i2];
        }
    }
}
