package com.proje.mobilesales.features.model;

import com.proje.mobilesales.core.enums.TransferGetType;
import java.util.ArrayList;
import java.util.List;

public class TransferGet {
    private List<TransferGetItem> transferGetItems = new ArrayList();
    public List<TransferGetItem> getTransferGetItems() {
        return transferGetItems;
    }
    public void setTransferGetItems(final List<TransferGetItem> list) {
        transferGetItems = list;
    }
    public void removeItem(final TransferGetType transferGetType) {
        for (final TransferGetItem transferGetItem : transferGetItems) {
            if (transferGetItem.getTransferGetType() == transferGetType) {
                transferGetItems.remove(transferGetItem);
                return;
            }
        }
    }
}
