package com.proje.mobilesales.features.penetration.model.database;


public final class PenetrationDetail {
    private String itemCode;
    private String itemName;
    private String itemRef;
    private int itemType;
    private int logicalRef;
    private int perNote;
    private int perPic;
    private int perPrice;
    private int pntId;
    private int sequence;
    private int status;
    public int getLogicalRef() {
        return logicalRef;
    }
    public void setLogicalRef(final int i) {
        logicalRef = i;
    }
    public int getPntId() {
        return pntId;
    }
    public void setPntId(final int i) {
        pntId = i;
    }
    public String getItemRef() {
        return itemRef;
    }
    public void setItemRef(final String str) {
        itemRef = str;
    }
    public int getItemType() {
        return itemType;
    }
    public void setItemType(final int i) {
        itemType = i;
    }
    public int getSequence() {
        return sequence;
    }
    public void setSequence(final int i) {
        sequence = i;
    }
    public int getPerPrice() {
        return perPrice;
    }
    public void setPerPrice(final int i) {
        perPrice = i;
    }
    public int getPerNote() {
        return perNote;
    }
    public void setPerNote(final int i) {
        perNote = i;
    }
    public int getPerPic() {
        return perPic;
    }
    public void setPerPic(final int i) {
        perPic = i;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(final String str) {
        itemName = str;
    }
    public String getItemCode() {
        return itemCode;
    }
    public void setItemCode(final String str) {
        itemCode = str;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(final int i) {
        status = i;
    }
}
