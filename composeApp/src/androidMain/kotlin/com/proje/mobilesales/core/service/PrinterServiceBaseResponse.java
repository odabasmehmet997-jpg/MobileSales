package com.proje.mobilesales.core.service;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.features.reports.model.ResponseError;
import java.util.ArrayList;


/* compiled from: PrinterServiceBaseResponse.kt */

public class PrinterServiceBaseResponse {

    @SerializedName("errors")
    @Expose
    private ArrayList<ResponseError> errors;

    @SerializedName(FirebaseAnalytics.Param.SUCCESS)
    @Expose
    private boolean isSuccess;

    public final boolean isSuccess() {
        return this.isSuccess;
    }

    public final void setSuccess(boolean z) {
        this.isSuccess = z;
    }

    public final ArrayList<ResponseError> getErrors() {
        return this.errors;
    }

    public final void setErrors(ArrayList<ResponseError> arrayList) {
        this.errors = arrayList;
    }
}
