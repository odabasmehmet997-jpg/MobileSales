package com.proje.mobilesales.features.todo.model.database;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import org.kxml2.wap.Wbxml;

public final class TodoInfoDb {

    @Column(name = "BEGDATE", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String begDate;

    @Column(isAllSame = false, name = "CLCODE", netsisName = "CLREF", shared = @ColumnProperty(useProperty = false))
    private String clCode;

    @Column(isAllSame = false, name = "CLREF", netsis = @ColumnProperty(useProperty = false), shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int clRef;

    @Column(name = "COMPLATETIME", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    private String complateTime;

    @Column(name = "DESC_")
    private String desc;

    @Column(name = "EMAILADDR")
    public String emailAddr;

    @Column(name = "ENDDATE", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String endDate;

    @Column(name = "ISREAD", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int isRead;
    public String isToDo;

    @Column(name = "ISTRANSFER", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int isTransfer;

    @Column(name = "ISTRANSFERWORPROC", shared = @ColumnProperty(alterVersion = Wbxml.STR_T, type = Column.ColumnValueTypes.INTEGER))
    private int isTransferWorProc;

    @Column(name = "LATITUDE", shared = @ColumnProperty(alterVersion = Wbxml.STR_T, type = Column.ColumnValueTypes.REAL))
    public double latitude;

    @Column(name = "LOGICALREF", shared = @ColumnProperty(isNotNull = EmbeddingCompat.DEBUG, isUnique = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    public int logicalRef;

    @Column(name = "LONGTITUDE", shared = @ColumnProperty(alterVersion = Wbxml.STR_T, type = Column.ColumnValueTypes.REAL))
    public double longtitude;

    @Column(name = "NOTE")
    public String note;

    @Column(name = "PRIORITY", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int priority;

    @Column(name = "READTIME", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    private String readTime;

    @Column(name = "SALESMUST", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int salesMust;

    @Column(name = "SENDER")
    private String sender;

    @Column(name = "STATUS", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int status;

    @Column(name = "USERNOTE")
    public String userNote;

    public String getSender() {
        return this.sender;
    }

    public void setSender(String str) {
        this.sender = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getReadTime() {
        return this.readTime;
    }

    public void setReadTime(String str) {
        this.readTime = str;
    }

    public String getComplateTime() {
        return this.complateTime;
    }

    public void setComplateTime(String str) {
        this.complateTime = str;
    }

    public String getClCode() {
        return this.clCode;
    }

    public void setClCode(String str) {
        this.clCode = str;
    }

    public int isRead() {
        return this.isRead;
    }

    public void setRead(int i2) {
        this.isRead = i2;
    }

    public int getSalesMust() {
        return this.salesMust;
    }

    public void setSalesMust(int i2) {
        this.salesMust = i2;
    }

    public int isTransfer() {
        return this.isTransfer;
    }

    public void setTransfer(int i2) {
        this.isTransfer = i2;
    }

    public int isTransferWorProc() {
        return this.isTransferWorProc;
    }

    public void setTransferWorProc(int i2) {
        this.isTransferWorProc = i2;
    }
}
