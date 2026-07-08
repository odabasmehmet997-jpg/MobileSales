package com.proje.mobilesales.core.event;

import com.proje.mobilesales.features.model.TransferGet;

public class TransferEvent extends ResponseEvent {
    private TransferGet transferGet;
    public TransferGet getTransferGet() {
        return this.transferGet;
    }
    public void setTransferGet(TransferGet transferGet) {
        this.transferGet = transferGet;
    }
}
