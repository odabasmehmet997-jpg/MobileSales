package com.proje.mobilesales.features.product.model.database;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;

public final class ItemUnits {
    public double area;
    private double areaRef;
    private double cmdate;
    public String code;
    public double convfact1;
    public double convfact2;
    public double grossVolume;
    private int grossVolumeRef;
    public double grossWeight;
    private int grossWeightRef;
    public double height;
    private double heightRef;
    private String itemCode;
    private int itemRef;
    public double length;
    private double lengthRef;
    public int linenr;
    public int logicalRef;
    private int mtrlClas;
    private int purchClas;
    private int salesClas;
    private int salesPriority;
    private int unitSetRef;
    private int uomSetRef;
    private String uomSetRefCode;
    private String uomSetRefDesc;
    public double volume;
    private int volumeRef;
    public double weight;
    private int weightRef;
    public double width;
    private double widthRef;
    public int getItemRef() {
        return itemRef;
    }
    public void setItemRef(final int i) {
        itemRef = i;
    }
    public String getItemCode() {
        return itemCode;
    }
    public void setItemCode(final String str) {
        itemCode = str;
    }
    public int getUnitSetRef() {
        return unitSetRef;
    }
    public void setUnitSetRef(final int i) {
        unitSetRef = i;
    }
    public int getMtrlClas() {
        return mtrlClas;
    }
    public void setMtrlClas(final int i) {
        mtrlClas = i;
    }
    public int getPurchClas() {
        return purchClas;
    }
    public void setPurchClas(final int i) {
        purchClas = i;
    }
    public int getSalesClas() {
        return salesClas;
    }
    public void setSalesClas(final int i) {
        salesClas = i;
    }
    public int getUomSetRef() {
        return uomSetRef;
    }
    public void setUomSetRef(final int i) {
        uomSetRef = i;
    }
    public String getUomSetRefCode() {
        return uomSetRefCode;
    }
    public void setUomSetRefCode(final String str) {
        uomSetRefCode = str;
    }
    public String getUomSetRefDesc() {
        return uomSetRefDesc;
    }
    public void setUomSetRefDesc(final String str) {
        uomSetRefDesc = str;
    }
    public int getVolumeRef() {
        return volumeRef;
    }
    public void setVolumeRef(final int i) {
        volumeRef = i;
    }
    public int getWeightRef() {
        return weightRef;
    }
    public void setWeightRef(final int i) {
        weightRef = i;
    }
    public int getGrossWeightRef() {
        return grossWeightRef;
    }
    public void setGrossWeightRef(final int i) {
        grossWeightRef = i;
    }
    public int getGrossVolumeRef() {
        return grossVolumeRef;
    }
    public void setGrossVolumeRef(final int i) {
        grossVolumeRef = i;
    }
    public double getCmdate() {
        return cmdate;
    }
    public void setCmdate(final double d) {
        cmdate = d;
    }
    public double getWidthRef() {
        return widthRef;
    }
    public void setWidthRef(final double d) {
        widthRef = d;
    }
    public double getLengthRef() {
        return lengthRef;
    }
    public void setLengthRef(final double d) {
        lengthRef = d;
    }
    public double getHeightRef() {
        return heightRef;
    }
    public void setHeightRef(final double d) {
        heightRef = d;
    }
    public double getAreaRef() {
        return areaRef;
    }
    public void setAreaRef(final double d) {
        areaRef = d;
    }
    public int getSalesPriority() {
        return salesPriority;
    }
    public void setSalesPriority(final int i) {
        salesPriority = i;
    }
}
