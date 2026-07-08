package com.proje.mobilesales.core.base;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public abstract class BaseResult<T> implements Parcelable {
    static final AtomicLong NEXT_ID = new AtomicLong(0);
    private boolean hasLicenseError;
    private long f1169id;
    private String mClCode;
    private String mClName;
    private int mClRef;
    private boolean mCompleted;
    private int mErrorResourceId;
    private String mErrorString;
    private boolean mIsLocalDifferentFromRemote;
    private int mLogicalRef;
    private int mRepeatCount;
    private boolean mSuccess;
    public int describeContents() {
        return 0;
    }
    protected BaseResult(Builder builder) {
        this.f1169id = NEXT_ID.getAndIncrement();
        setClRef(builder.mClRef);
        setClName(builder.mClName);
        setClCode(builder.mClCode);
        setLogicalRef(builder.mLogicalRef);
    }
    public long getId() {
        return this.f1169id;
    }
    public void setId(long j2) {
        this.f1169id = j2;
    }
    public boolean isCompleted() {
        return this.mCompleted;
    }
    public void setCompleted(boolean z) {
        this.mCompleted = z;
    }
    public boolean isSuccess() {
        return this.mSuccess;
    }
    public void setSuccess(boolean z) {
        this.mSuccess = z;
    }
    public int getRepeatCount() {
        return this.mRepeatCount;
    }
    public void setRepeatCount(int i2) {
        this.mRepeatCount = i2;
    }
    public int getClRef() {
        return this.mClRef;
    }
    public void setClRef(int i2) {
        this.mClRef = i2;
    }
    public String getClName() {
        return this.mClName;
    }
    public void setClName(String str) {
        this.mClName = str;
    }
    public String getClCode() {
        return this.mClCode;
    }
    public void setClCode(String str) {
        this.mClCode = str;
    }
    public int getLogicalRef() {
        return this.mLogicalRef;
    }
    public void setLogicalRef(int i2) {
        this.mLogicalRef = i2;
    }
    public void setSuccess(String str) {
        this.mErrorString = str;
        this.mSuccess = false;
    }
    public void setErrorString(String str) {
        this.mErrorString = str;
    }
    public String getErrorString() {
        return this.mErrorString;
    }
    public BaseResult() {
        this.f1169id = NEXT_ID.getAndIncrement();
    }
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.f1169id);
        parcel.writeByte(this.mCompleted ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mSuccess ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mRepeatCount);
        parcel.writeInt(this.mClRef);
        parcel.writeString(this.mClName);
        parcel.writeString(this.mClCode);
        parcel.writeInt(this.mLogicalRef);
        parcel.writeString(this.mErrorString);
        parcel.writeByte(this.mIsLocalDifferentFromRemote ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mErrorResourceId);
    }
    protected BaseResult(Parcel parcel) {
        this.f1169id = NEXT_ID.getAndIncrement();
        this.f1169id = parcel.readLong();
        this.mCompleted = parcel.readByte() != 0;
        this.mSuccess = parcel.readByte() != 0;
        this.mRepeatCount = parcel.readInt();
        this.mClRef = parcel.readInt();
        this.mClName = parcel.readString();
        this.mClCode = parcel.readString();
        this.mLogicalRef = parcel.readInt();
        this.mErrorString = parcel.readString();
        this.mIsLocalDifferentFromRemote = parcel.readByte() != 0;
        this.mErrorResourceId = parcel.readInt();
    }
    public boolean isLocalDifferentFromRemote() {
        return this.mIsLocalDifferentFromRemote;
    }
    public void setLocalDifferentFromRemote(boolean z) {
        this.mIsLocalDifferentFromRemote = z;
    }
    public int getErrorResourceId() {
        return this.mErrorResourceId;
    }
    public void setErrorResourceId(int i2) {
        this.mErrorResourceId = i2;
    }
    public boolean isHasLicenseError() {
        return this.hasLicenseError;
    }
    public void setHasLicenseError(boolean z) {
        this.hasLicenseError = z;
    }
    public abstract List<Object> getDataList();
    public static abstract class Builder<S extends BaseResult, V extends Builder<S, V>> {
        private String mClCode;
        private String mClName;
        private int mClRef;
        private int mLogicalRef;

        public abstract S build();

        protected Builder() {
        }

        public V withMClRef(int i2) {
            this.mClRef = i2;
            return (V) this;
        }

        public V withClName(String str) {
            this.mClName = str;
            return (V) this;
        }

        public V withClCode(String str) {
            this.mClCode = str;
            return (V) this;
        }

        public V withLogicalRef(int i2) {
            this.mLogicalRef = i2;
            return (V) this;
        }
    }
}
