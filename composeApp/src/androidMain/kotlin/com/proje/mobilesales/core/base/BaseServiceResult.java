package com.proje.mobilesales.core.base;

import android.os.Parcel;
import android.os.Parcelable; 
import com.proje.mobilesales.core.enums.DataObjectType;

import java.util.Collections;
import java.util.List;

public class BaseServiceResult<T, S> extends BaseResult<T> implements Parcelable {
    public static final Parcelable.Creator<BaseServiceResult> CREATOR = new Parcelable.Creator<BaseServiceResult>() {
        public BaseServiceResult createFromParcel(Parcel parcel) {
            return new BaseServiceResult(parcel);
        }
        public BaseServiceResult[] newArray(int i2) {
            return new BaseServiceResult[i2];
        }
    };
    private S data;
    private int dataReference;
    private DataObjectType dataType;
    private String date;
    private String guid;
    private boolean notDuplicateControl;
    private T sendData;
    public int describeContents() {
        return 0;
    }
    protected BaseServiceResult(ServiceBuilder serviceBuilder) {
        super(serviceBuilder);
        setDataReference(serviceBuilder.dataReference);
        setDate(serviceBuilder.date);
        setDataType(serviceBuilder.dataType);
        setGuid(serviceBuilder.guid);
        setNotDuplicateControl(serviceBuilder.notDuplicateControl);
        setSendData((T) serviceBuilder.sendData);
        setData((S) serviceBuilder.data);
    }
    public int getDataReference() {
        return this.dataReference;
    }
    public void setDataReference(int i2) {
        this.dataReference = i2;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String str) {
        this.date = str;
    }
    public DataObjectType getDataType() {
        return this.dataType;
    }
    public void setDataType(DataObjectType dataObjectType) {
        this.dataType = dataObjectType;
    }
    public String getGuid() {
        return this.guid;
    }
    public void setGuid(String str) {
        this.guid = str;
    }
    public boolean isNotDuplicateControl() {
        return this.notDuplicateControl;
    }
    public void setNotDuplicateControl(boolean z) {
        this.notDuplicateControl = z;
    }
    public T getSendData() {
        return this.sendData;
    }
    public void setSendData(T t) {
        this.sendData = t;
    }
    public S getData() {
        return this.data;
    }
    public void setData(S s) {
        this.data = s;
    }
    public static abstract class ServiceBuilder<V extends BaseServiceResult, S extends ServiceBuilder<V, S>> extends BaseResult.Builder<BaseServiceResult, ServiceBuilder<V, S>> {
        private Object data;
        private int dataReference;
        private DataObjectType dataType;
        private String date;
        private String guid;
        private boolean notDuplicateControl;
        private Object sendData;
        protected ServiceBuilder() {
        }
        public S withDataReference(int i2) {
            this.dataReference = i2;
            return (S) this;
        }
        public S withDate(String str) {
            this.date = str;
            return (S) this;
        }
        public S withDataType(DataObjectType dataObjectType) {
            this.dataType = dataObjectType;
            return (S) this;
        }
        public S withGuid(String str) {
            this.guid = str;
            return (S) this;
        }
        public S withNotDuplicateControl(boolean z) {
            this.notDuplicateControl = z;
            return (S) this;
        }
        public S withData(Object obj) {
            this.data = obj;
            return (S) this;
        }
        public S withSendData(Object obj) {
            this.sendData = obj;
            return (S) this;
        }
    }
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.dataReference);
        DataObjectType dataObjectType = this.dataType;
        parcel.writeInt(dataObjectType == null ? -1 : dataObjectType.ordinal());
        parcel.writeString(this.date);
        parcel.writeString(this.guid);
        parcel.writeByte(this.notDuplicateControl ? (byte) 1 : (byte) 0);
    }
    protected BaseServiceResult(Parcel parcel) {
        super(parcel);
        this.dataReference = parcel.readInt();
        int readInt = parcel.readInt();
        this.dataType = readInt == -1 ? null : DataObjectType.values()[readInt];
        this.date = parcel.readString();
        this.guid = parcel.readString();
        this.notDuplicateControl = parcel.readByte() != 0;
    }
    public List<Object> getDataList() {
        return Collections.emptyList();
    }
}
