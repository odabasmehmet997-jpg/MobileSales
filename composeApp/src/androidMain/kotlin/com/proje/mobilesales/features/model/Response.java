package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Response<T> implements Parcelable {
    public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() {
        public Response createFromParcel(final Parcel parcel) {
            return new Response(parcel);
        }
        public Response[] newArray(final int i) {
            return new Response[i];
        }
    };
    private String message;
    private boolean success;
    public int describeContents() {
        return 0;
    }

    public Response(final boolean z) {
        success = z;
    }

    public Response(final String str, final boolean z) {
        message = str;
        success = z;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String str) {
        message = str;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(final boolean z) {
        success = z;
    }

    public void writeToParcel(final Parcel parcel, final int i) {
        parcel.writeString(message);
        parcel.writeByte(success ? (byte) 1 : 0);
    }

    public Response() {
    }

    protected Response(final Parcel parcel) {
        message = parcel.readString();
        success = 0 != parcel.readByte();
    }
}