package com.proje.mobilesales.features.cabinoperation.model.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;


/* compiled from: CabinTrans.kt */
@Tables(alterVersion = 154, name = "CABINTRANS")

public final class CabinTrans {

    @Column(name = "CABINID", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int cabinID;

    @Column(name = "CLIENTCODE")
    public String clientCode;

    @Column(name = "CLIENTREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int clientRef;

    @Column(name = "DATE", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String date;

    /* renamed from: id */
    @Column(name = "ID", shared = @ColumnProperty(isAutoIncrement = EmbeddingCompat.DEBUG, isPrimaryKey = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    public int f1224id;

    @Column(name = "ISTRANSFER", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int isTransfer;

    @Column(name = "LOCALFICHEREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int localFicheRef;

    @Column(name = "SALESMANREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int salesmanRef;

    @Column(name = "TRFICHENO")
    public String trFicheNo;

    @Column(name = "TRFICHEREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int trFicheRef;

    @Column(name = "TRCODE", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int trcode;

    @Column(name = "TRTYPE", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int trtype;

    public int getLocalFicheRef() {
        return this.localFicheRef;
    }

    public void setLocalFicheRef(int i2) {
        this.localFicheRef = i2;
    }

    public int isTransfer() {
        return this.isTransfer;
    }

    public void setTransfer(int i2) {
        this.isTransfer = i2;
    }

    public CabinTrans() {
    }

    public CabinTrans(int i2, int i3, String str, int i4, String str2, int i5, int i6) {
        this.cabinID = i2;
        this.localFicheRef = i3;
        this.clientCode = str;
        this.clientRef = i4;
        this.date = str2;
        this.salesmanRef = i5;
        this.trtype = i6;
    }
}
