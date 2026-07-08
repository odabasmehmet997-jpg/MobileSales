package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.base.BaseResult;
import com.proje.mobilesales.core.enums.TransferOperationName;
import com.proje.mobilesales.core.netsis.NetsisServiceResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class GroupItem<T extends BaseResult> extends NetsisServiceResult implements Parcelable {
    boolean complete;
    boolean error;
    String errorMessage;

    public long f1254id;
    List<T> mItemList;
    boolean mService;
    String mTitle;
    TransferOperationName mTransferOperationName;
    static final AtomicLong NEXT_ID = new AtomicLong(0);
    public static final Parcelable.Creator<GroupItem> CREATOR = new Parcelable.Creator<GroupItem>() {
        public GroupItem createFromParcel(final Parcel parcel) {
            return new GroupItem(parcel);
        }

        public GroupItem[] newArray(final int i2) {
            return new GroupItem[i2];
        }
    };
    public int describeContents() {
        return 0;
    }
    public GroupItem(final List<T> list, final String str, final TransferOperationName transferOperationName, final boolean z) {
        f1254id = GroupItem.NEXT_ID.getAndIncrement();
        mItemList = list;
        mTitle = str;
        mTransferOperationName = transferOperationName;
        mService = z;
    }
    public List<T> getItemList() {
        return mItemList;
    }
    public void setItemList(final List<T> list) {
        mItemList = list;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(final String str) {
        mTitle = str;
    }
    public TransferOperationName getTransferOperationName() {
        return mTransferOperationName;
    }
    public void setTransferOperationName(final TransferOperationName transferOperationName) {
        mTransferOperationName = transferOperationName;
    }
    public boolean isService() {
        return mService;
    }
    public void setService(final boolean z) {
        mService = z;
    }
    public boolean isComplete() {
        return complete;
    }
    public void setComplete(final boolean z) {
        complete = z;
    }
    public boolean isError() {
        return error;
    }
    public void setError(final boolean z) {
        error = z;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(final String str) {
        errorMessage = str;
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeLong(f1254id);
        final List<T> list = mItemList;
        if (null == list || 0 == list.size()) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(mItemList.size());
            parcel.writeSerializable(mItemList.get(0).getClass());
            parcel.writeList(mItemList);
        }
        parcel.writeString(mTitle);
        final TransferOperationName transferOperationName = mTransferOperationName;
        parcel.writeInt(null == transferOperationName ? -1 : transferOperationName.ordinal());
        parcel.writeByte(mService ? (byte) 1 : (byte) 0);
        parcel.writeByte(complete ? (byte) 1 : (byte) 0);
        parcel.writeByte(error ? (byte) 1 : (byte) 0);
        parcel.writeString(errorMessage);
    }
    protected GroupItem(final Parcel parcel) {
        f1254id = GroupItem.NEXT_ID.getAndIncrement();
        f1254id = parcel.readLong();
        final int readInt = parcel.readInt();
        if (0 == readInt) {
            mItemList = null;
        } else {
            final Class cls = (Class) parcel.readSerializable();
            final ArrayList arrayList = new ArrayList(readInt);
            mItemList = arrayList;
            parcel.readList(arrayList, cls.getClassLoader());
        }
        mTitle = parcel.readString();
        final int readInt2 = parcel.readInt();
        mTransferOperationName = -1 != readInt2 ? TransferOperationName.values()[readInt2] : null;
        mService = 0 != parcel.readByte();
        complete = 0 != parcel.readByte();
        error = 0 != parcel.readByte();
        errorMessage = parcel.readString();
    }
}
