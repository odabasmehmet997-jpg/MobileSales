package com.proje.mobilesales.features.product.model.database;

import android.util.Log;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnIndex;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.TableIndex;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.enums.BinaryType;
import com.proje.mobilesales.features.dbmodel.ConvertBinaries;
import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ItemImage.kt */
@Tables(alterVersion = 94, name = "ITEMIMAGES")
@TableIndex(name = "idx_ItemImage")

public final class ItemImage extends ConvertBinaries {
    @Column(name = "CMDATE", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    private double cmDate;
    @Column(isAllSame = false, name = "CODE", netsis = @ColumnProperty(alterVersion = 115, type = Column.ColumnValueTypes.NVARCHAR, useUpdate = true), netsisName = "KOD")
    @ColumnIndex
    private String code;
    @Column(name = "ITEMIMAGE", netsisName = "BILGI", shared = @ColumnProperty(type = Column.ColumnValueTypes.BLOB))
    private byte[] itemImage;
    @Column(isAllSame = false, name = "ITEMREF", netsis = @ColumnProperty(isAutoIncrement = true, isPrimaryKey = true, type = Column.ColumnValueTypes.INTEGER, useWhereStatement = false), netsisName = "ITEMREF", shared = @ColumnProperty(isNotNull = true, isUnique = true, type = Column.ColumnValueTypes.INTEGER))
    @ColumnIndex
    private int itemRef;

    public int getItemRef() {
        return this.itemRef;
    }

    public void setItemRef(int i) {
        this.itemRef = i;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public double getCmDate() {
        return this.cmDate;
    }

    public void setCmDate(double d) {
        this.cmDate = d;
    }

    public byte[] getItemImage() {
        return this.itemImage;
    }

    public void setItemImage(byte[] bArr) {
        this.itemImage = bArr;
    }

    @Override // com.proje.mobilesales.features.dbmodel.ConvertBinaries
    public void checkBinaries() {
        try {
            this.itemImage = Convert(BinaryType.btIMAGE, this.itemImage);
        } catch (IOException e) {
            String message = e.getMessage();
            Intrinsics.checkNotNull(message);
            Log.e("CustomerImage.ItemImage", message);
        }
    }
}
