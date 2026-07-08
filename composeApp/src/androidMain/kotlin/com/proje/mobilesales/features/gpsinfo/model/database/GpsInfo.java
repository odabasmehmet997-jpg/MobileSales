package com.proje.mobilesales.features.gpsinfo.model.database;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
public final class GpsInfo {

    @Column(name = "DISTANCE", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    public double distance;

    @Column(name = "GPSDATE", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String gpsDate;

    @Column(name = "ID", shared = @ColumnProperty(isAutoIncrement = EmbeddingCompat.DEBUG, isPrimaryKey = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    public int f1251id;

    @Column(name = "LATITUDE", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    public double latitude;

    @Column(name = "LONGTITUDE", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    public double longtitude;

    @Column(name = "SPEED", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    public double speed;

    @Column(name = "TIMESPAN", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    public double timeSpan;

    @Column(name = "ISTRANSFER", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int transfer;
}
