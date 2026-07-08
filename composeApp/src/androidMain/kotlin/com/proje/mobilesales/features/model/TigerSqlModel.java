package com.proje.mobilesales.features.model;

import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.core.annotation.Column;

public class TigerSqlModel {

    @Column(name = ExifInterface.GPS_MEASUREMENT_IN_PROGRESS)
    String f1255A;

    String f1256B;

    public String getA() {
        return f1255A;
    }

    public void setA(final String str) {
        f1255A = str;
    }

    public String getB() {
        return f1256B;
    }

    public void setB(final String str) {
        f1256B = str;
    }
}
