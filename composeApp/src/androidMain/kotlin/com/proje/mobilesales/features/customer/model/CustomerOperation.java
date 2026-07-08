package com.proje.mobilesales.features.customer.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.PointerIconCompat;
import com.proje.mobilesales.core.base.BaseDbSalesFiche;
import com.proje.mobilesales.core.enums.CustomerMenuType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parceler;
 
public final class CustomerOperation implements Parcelable {
    private Class<?> activity;
    private Bundle bundle;
    private FicheMode ficheMode;
    private FicheType ficheType;
    private boolean hasSubMenu;
    private CustomerMenuType menuType;
    private int operationDrawableResId;
    private String operationName;
    private ReceiptType receiptType;
    private SalesType salesType;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<CustomerOperation> CREATOR = new Creator();
 
    public static final class Creator implements Parcelable.Creator<CustomerOperation> {
        
        public CustomerOperation createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return CustomerOperation.Companion.m1361create(parcel);
        } 
        public CustomerOperation[] newArray(int i2) {
            return new CustomerOperation[i2];
        }
    }

    public CustomerOperation() {
        this(null, 0, null, false, null, null, null, null, null, null, 1023, null);
    }

    public static Builder newBuilder(CustomerOperation customerOperation) {
        return Companion.newBuilder(customerOperation);
    }

    public String component1() {
        return this.operationName;
    }

    public Bundle component10() {
        return this.bundle;
    }

    public int component2() {
        return this.operationDrawableResId;
    }

    public Class<?> component3() {
        return this.activity;
    }

    public boolean component4() {
        return this.hasSubMenu;
    }

    public CustomerMenuType component5() {
        return this.menuType;
    }

    public SalesType component6() {
        return this.salesType;
    }

    public ReceiptType component7() {
        return this.receiptType;
    }

    public FicheMode component8() {
        return this.ficheMode;
    }

    public FicheType component9() {
        return this.ficheType;
    }

    public CustomerOperation copy(String str, int i2, Class<?> cls, boolean z, CustomerMenuType customerMenuType, SalesType salesType, ReceiptType receiptType, FicheMode ficheMode, FicheType ficheType, Bundle bundle) {
        return new CustomerOperation(str, i2, cls, z, customerMenuType, salesType, receiptType, ficheMode, ficheType, bundle);
    } 
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomerOperation customerOperation)) {
            return false;
        }
        return Intrinsics.areEqual(this.operationName, customerOperation.operationName) && this.operationDrawableResId == customerOperation.operationDrawableResId && Intrinsics.areEqual(this.activity, customerOperation.activity) && this.hasSubMenu == customerOperation.hasSubMenu && this.menuType == customerOperation.menuType && this.salesType == customerOperation.salesType && this.receiptType == customerOperation.receiptType && this.ficheMode == customerOperation.ficheMode && this.ficheType == customerOperation.ficheType && Intrinsics.areEqual(this.bundle, customerOperation.bundle);
    }

    public int hashCode() {
        String str = this.operationName;
        int hashCode = (((str == null ? 0 : str.hashCode()) * 31) + Integer.hashCode(this.operationDrawableResId)) * 31;
        Class<?> cls = this.activity;
        int hashCode2 = (((hashCode + (cls == null ? 0 : cls.hashCode())) * 31) + Boolean.hashCode(this.hasSubMenu)) * 31;
        CustomerMenuType customerMenuType = this.menuType;
        int hashCode3 = (hashCode2 + (customerMenuType == null ? 0 : customerMenuType.hashCode())) * 31;
        SalesType salesType = this.salesType;
        int hashCode4 = (hashCode3 + (salesType == null ? 0 : salesType.hashCode())) * 31;
        ReceiptType receiptType = this.receiptType;
        int hashCode5 = (hashCode4 + (receiptType == null ? 0 : receiptType.hashCode())) * 31;
        FicheMode ficheMode = this.ficheMode;
        int hashCode6 = (hashCode5 + (ficheMode == null ? 0 : ficheMode.hashCode())) * 31;
        FicheType ficheType = this.ficheType;
        int hashCode7 = (hashCode6 + (ficheType == null ? 0 : ficheType.hashCode())) * 31;
        Bundle bundle = this.bundle;
        return hashCode7 + (bundle != null ? bundle.hashCode() : 0);
    }

    public String toString() {
        return "CustomerOperation(operationName=" + this.operationName + ", operationDrawableResId=" + this.operationDrawableResId + ", activity=" + this.activity + ", hasSubMenu=" + this.hasSubMenu + ", menuType=" + this.menuType + ", salesType=" + this.salesType + ", receiptType=" + this.receiptType + ", ficheMode=" + this.ficheMode + ", ficheType=" + this.ficheType + ", bundle=" + this.bundle + ')';
    } 
    public void writeToParcel(Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        Companion.write(this, out, i2);
    }

    public CustomerOperation(String str, int i2, Class<?> cls, boolean z, CustomerMenuType customerMenuType, SalesType salesType, ReceiptType receiptType, FicheMode ficheMode, FicheType ficheType, Bundle bundle) {
        this.operationName = str;
        this.operationDrawableResId = i2;
        this.activity = cls;
        this.hasSubMenu = z;
        this.menuType = customerMenuType;
        this.salesType = salesType;
        this.receiptType = receiptType;
        this.ficheMode = ficheMode;
        this.ficheType = ficheType;
        this.bundle = bundle;
    }

    public   CustomerOperation(String str, int i2, Class cls, boolean z, CustomerMenuType customerMenuType, SalesType salesType, ReceiptType receiptType, FicheMode ficheMode, FicheType ficheType, Bundle bundle, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? -1 : i2, (i3 & 4) != 0 ? null : cls, (i3 & 8) == 0 && z, (i3 & 16) != 0 ? null : customerMenuType, (i3 & 32) != 0 ? SalesType.FREE : salesType, (i3 & 64) != 0 ? null : receiptType, (i3 & 128) != 0 ? null : ficheMode, (i3 & 256) != 0 ? FicheType.FREE : ficheType, (i3 & 512) == 0 ? bundle : null);
    }

    public String getOperationName() {
        return this.operationName;
    }

    public void setOperationName(String str) {
        this.operationName = str;
    }

    public int getOperationDrawableResId() {
        return this.operationDrawableResId;
    }

    public void setOperationDrawableResId(int i2) {
        this.operationDrawableResId = i2;
    }

    public Class<?> getActivity() {
        return this.activity;
    }

    public void setActivity(Class<?> cls) {
        this.activity = cls;
    }

    public boolean getHasSubMenu() {
        return this.hasSubMenu;
    }

    public void setHasSubMenu(boolean z) {
        this.hasSubMenu = z;
    }

    public CustomerMenuType getMenuType() {
        return this.menuType;
    }

    public void setMenuType(CustomerMenuType customerMenuType) {
        this.menuType = customerMenuType;
    }

    public SalesType getSalesType() {
        return this.salesType;
    }

    public void setSalesType(SalesType salesType) {
        this.salesType = salesType;
    }

    public ReceiptType getReceiptType() {
        return this.receiptType;
    }

    public void setReceiptType(ReceiptType receiptType) {
        this.receiptType = receiptType;
    }

    public FicheMode getFicheMode() {
        return this.ficheMode;
    }

    public void setFicheMode(FicheMode ficheMode) {
        this.ficheMode = ficheMode;
    }

    public FicheType getFicheType() {
        return this.ficheType;
    }

    public void setFicheType(FicheType ficheType) {
        this.ficheType = ficheType;
    }

    public Bundle getBundle() {
        return this.bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    
    public CustomerOperation(String operationName, Class<?> cls, Bundle bundle) {
        this(operationName, -1, cls, false, null, null, null, null, null, bundle, TypedValues.PositionType.TYPE_PERCENT_HEIGHT, null);
        Intrinsics.checkNotNullParameter(operationName, "operationName");
    }

    
    public CustomerOperation(String operationName, Class<?> cls, CustomerMenuType customerMenuType) {
        this(operationName, 0, cls, false, customerMenuType, null, null, null, null, null, PointerIconCompat.TYPE_HAND, null);
        Intrinsics.checkNotNullParameter(operationName, "operationName");
    }

    
    public CustomerOperation(SalesType salesType, FicheType ficheType, FicheMode ficheMode) {
        this(null, 0, null, false, null, salesType, null, ficheMode, ficheType, null, 543, null);
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        Intrinsics.checkNotNullParameter(ficheType, "ficheType");
    }

    
    public CustomerOperation(Builder builder) {
        this(null, 0, null, false, null, null, null, null, null, null, 1023, null);
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.operationName = builder.getMOperationName();
        this.operationDrawableResId = builder.getMOperationDrawableResId();
        this.activity = builder.getMActivity();
        this.hasSubMenu = builder.getMHasSubMenu();
        this.menuType = builder.getMMenuType();
        SalesType mSalesType = builder.getMSalesType();
        Intrinsics.checkNotNull(mSalesType);
        this.salesType = mSalesType;
        this.receiptType = builder.getMReceiptType();
        this.ficheMode = builder.getMFicheMode();
        FicheType mFicheType = builder.getMFicheType();
        Intrinsics.checkNotNull(mFicheType);
        this.ficheType = mFicheType;
        this.bundle = builder.getBundle();
    }

    public FicheType getFicheType(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        FicheType ficheType = FicheType.ALL;
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
        if (SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(salesType)) {
            return FicheType.RETAILINVOICE;
        }
        return SalesUtils.isSalesTypeWhTransfer(salesType) ? FicheType.WHTRANSFER : ficheType;
    }

    
    public CustomerOperation(Parcel parcel) {
        this(null, 0, null, false, null, null, null, null, null, null, 1023, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.operationName = parcel.readString();
        this.operationDrawableResId = parcel.readInt();
        this.activity = (Class) parcel.readSerializable();
        this.hasSubMenu = parcel.readByte() != 0;
        int readInt = parcel.readInt();
        this.menuType = readInt == -1 ? null : CustomerMenuType.values()[readInt];
        int readInt2 = parcel.readInt();
        this.salesType = readInt2 == -1 ? SalesType.FREE : SalesType.values()[readInt2];
        int readInt3 = parcel.readInt();
        this.receiptType = readInt3 == -1 ? null : ReceiptType.values()[readInt3];
        int readInt4 = parcel.readInt();
        this.ficheMode = readInt4 == -1 ? null : FicheMode.values()[readInt4];
        int readInt5 = parcel.readInt();
        this.ficheType = readInt5 != -1 ? FicheType.values()[readInt5] : null;
        this.bundle = parcel.readBundle(CustomerOperation.class.getClassLoader());
    }
    public static final class Builder {
        private Bundle bundle;
        private Class<?> mActivity;
        private FicheMode mFicheMode;
        private FicheType mFicheType;
        private boolean mHasSubMenu;
        private CustomerMenuType mMenuType;
        private int mOperationDrawableResId;
        private String mOperationName;
        private ReceiptType mReceiptType;
        private SalesType mSalesType;

        public String getMOperationName() {
            return this.mOperationName;
        }

        public void setMOperationName(String str) {
            this.mOperationName = str;
        }

        public int getMOperationDrawableResId() {
            return this.mOperationDrawableResId;
        }

        public void setMOperationDrawableResId(int i2) {
            this.mOperationDrawableResId = i2;
        }

        public Class<?> getMActivity() {
            return this.mActivity;
        }

        public void setMActivity(Class<?> cls) {
            this.mActivity = cls;
        }

        public boolean getMHasSubMenu() {
            return this.mHasSubMenu;
        }

        public void setMHasSubMenu(boolean z) {
            this.mHasSubMenu = z;
        }

        public CustomerMenuType getMMenuType() {
            return this.mMenuType;
        }

        public void setMMenuType(CustomerMenuType customerMenuType) {
            this.mMenuType = customerMenuType;
        }

        public SalesType getMSalesType() {
            return this.mSalesType;
        }

        public void setMSalesType(SalesType salesType) {
            this.mSalesType = salesType;
        }

        public ReceiptType getMReceiptType() {
            return this.mReceiptType;
        }

        public void setMReceiptType(ReceiptType receiptType) {
            this.mReceiptType = receiptType;
        }

        public FicheMode getMFicheMode() {
            return this.mFicheMode;
        }

        public void setMFicheMode(FicheMode ficheMode) {
            this.mFicheMode = ficheMode;
        }

        public FicheType getMFicheType() {
            return this.mFicheType;
        }

        public void setMFicheType(FicheType ficheType) {
            this.mFicheType = ficheType;
        }

        public Bundle getBundle() {
            return this.bundle;
        }

        public void setBundle(Bundle bundle) {
            this.bundle = bundle;
        }

        public CustomerOperation build() {
            return new CustomerOperation(this);
        }
    }
    public static final class Companion implements Parceler<CustomerOperation> {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public BaseDbSalesFiche[] newArray(int i2) {
            return  Parceler.DefaultImpls.newArray(this, i2);
        }

        public Builder newBuilder() {
            return new Builder();
        }

        public Builder newBuilder(CustomerOperation copy) {
            Intrinsics.checkNotNullParameter(copy, "copy");
            Builder builder = new Builder();
            builder.setMOperationName(copy.getOperationName());
            builder.setMOperationDrawableResId(copy.getOperationDrawableResId());
            builder.setMActivity(copy.getActivity());
            builder.setMHasSubMenu(copy.getHasSubMenu());
            builder.setMMenuType(copy.getMenuType());
            builder.setMSalesType(copy.getSalesType());
            builder.setMReceiptType(copy.getReceiptType());
            builder.setMFicheMode(copy.getFicheMode());
            builder.setMFicheType(copy.getFicheType());
            builder.setBundle(copy.getBundle());
            return builder;
        }

        public void write(CustomerOperation customerOperation, Parcel parcel, int i2) {
            int ordinal;
            int ordinal2;
            int ordinal3;
            int ordinal4;
            Intrinsics.checkNotNullParameter(customerOperation, "<this>");
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.writeString(customerOperation.getOperationName());
            parcel.writeInt(customerOperation.getOperationDrawableResId());
            parcel.writeSerializable(customerOperation.getActivity());
            parcel.writeByte(customerOperation.getHasSubMenu() ? (byte) 1 : (byte) 0);
            int i3 = -1;
            if (customerOperation.getMenuType() == null) {
                ordinal = -1;
            } else {
                CustomerMenuType menuType = customerOperation.getMenuType();
                Intrinsics.checkNotNull(menuType);
                ordinal = menuType.ordinal();
            }
            parcel.writeInt(ordinal);
            if (customerOperation.getSalesType() == null) {
                ordinal2 = -1;
            } else {
                SalesType salesType = customerOperation.getSalesType();
                Intrinsics.checkNotNull(salesType);
                ordinal2 = salesType.ordinal();
            }
            parcel.writeInt(ordinal2);
            if (customerOperation.getReceiptType() == null) {
                ordinal3 = -1;
            } else {
                ReceiptType receiptType = customerOperation.getReceiptType();
                Intrinsics.checkNotNull(receiptType);
                ordinal3 = receiptType.ordinal();
            }
            parcel.writeInt(ordinal3);
            if (customerOperation.getFicheMode() == null) {
                ordinal4 = -1;
            } else {
                FicheMode ficheMode = customerOperation.getFicheMode();
                Intrinsics.checkNotNull(ficheMode);
                ordinal4 = ficheMode.ordinal();
            }
            parcel.writeInt(ordinal4);
            if (customerOperation.getFicheType() != null) {
                FicheType ficheType = customerOperation.getFicheType();
                Intrinsics.checkNotNull(ficheType);
                i3 = ficheType.ordinal();
            }
            parcel.writeInt(i3);
            parcel.writeBundle(customerOperation.getBundle());
        }
        public CustomerOperation m1361create(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new CustomerOperation(parcel);
        }
    }
}
