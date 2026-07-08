package com.proje.mobilesales.features.cabinoperation.model.dbmodel;

import android.text.TextUtils;
import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.features.cabinoperation.model.enums.CabinEnums;

public final class Cabin {
    public String barcode;
    public String brand;
    public String clientCode;
    public double cmDate;
    public int code;
    public String createdDate;
    public String firm;
    public int f1223id;
    public int isTransfer;
    public int locInfo;
    public String model;
    public String modifiedDate;
    public int salesmanRef;
    public int status;
    public int type;
    public int id;

    public boolean isCabinOnCustomerButNotDelivered() {
        return !TextUtils.isEmpty(this.clientCode) && this.salesmanRef > 0 && this.status == CabinEnums.CabinStatus.ACTIVE.getmValue() && this.locInfo == CabinEnums.CabinLocInfoEnum.CUSTOMER.getmValue();
    }

    public boolean isCabinOnCustomerAndDelivered() {
        return !TextUtils.isEmpty(this.clientCode) && this.salesmanRef <= 0 && this.status == CabinEnums.CabinStatus.ACTIVE.getmValue() && this.locInfo == CabinEnums.CabinLocInfoEnum.CUSTOMER.getmValue();
    }

    public boolean isCabinReceivedFromCustomerAndOnTheRoad() {
        return !TextUtils.isEmpty(this.clientCode) && this.salesmanRef > 0 && (this.status == CabinEnums.CabinStatus.ACTIVE.getmValue() || this.status == CabinEnums.CabinStatus.DEFECTIVE.getmValue()) && this.locInfo == CabinEnums.CabinLocInfoEnum.STORAGE.getmValue();
    }

    public boolean isCabinAvailableAndOnTheStorage() {
        return TextUtils.isEmpty(this.clientCode) && this.salesmanRef <= 0 && this.status == CabinEnums.CabinStatus.ACTIVE.getmValue() && this.locInfo == CabinEnums.CabinLocInfoEnum.STORAGE.getmValue();
    }
}
