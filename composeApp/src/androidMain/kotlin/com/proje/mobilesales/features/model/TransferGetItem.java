package com.proje.mobilesales.features.model;

import com.proje.mobilesales.core.enums.TransferGetType;

public class TransferGetItem {
    private TransferGetType transferGetType;
    private String transferError = "";
    private boolean select;
    private boolean complete;
    private long lastTransferDate;
    public TransferGetItem() {
    }
    public TransferGetItem(final TransferGetType transferGetType) {
        this.transferGetType = transferGetType;
    }
    public String getTransferError() {
        return transferError;
    }
    public void setTransferError(final String str) {
        transferError = str;
    }
    public long getLastTransferDate() {
        return lastTransferDate;
    }
    public void setLastTransferDate(final long j2) {
        lastTransferDate = j2;
    }
    public boolean isSelect() {
        return select;
    }
    public void setSelect(final boolean z) {
        select = z;
    }
    public TransferGetType getTransferGetType() {
        return transferGetType;
    }
    public void setTransferGetType(final TransferGetType transferGetType) {
        this.transferGetType = transferGetType;
    }
    public boolean isComplete() {
        return complete;
    }
    public void setComplete(final boolean z) {
        complete = z;
    }
}
