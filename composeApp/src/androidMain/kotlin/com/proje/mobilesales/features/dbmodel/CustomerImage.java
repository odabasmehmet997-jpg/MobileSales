package com.proje.mobilesales.features.dbmodel;

import android.util.Log;
import com.proje.mobilesales.core.enums.BinaryType;
import java.io.IOException;

public class CustomerImage extends ConvertBinaries {
    private double cmDate;
    private byte[] customerImage;
    private int customerRef;
    private int docNr;
    private int docType;
    public int getCustomerRef() {
        return this.customerRef;
    }
    public void setCustomerRef(int i2) {
        this.customerRef = i2;
    }
    public double getCmDate() {
        return this.cmDate;
    }
    public void setCmDate(double d2) {
        this.cmDate = d2;
    }
    public byte[] getCustomerImage() {
        return this.customerImage;
    }
    public void setCustomerImage(byte[] bArr) {
        this.customerImage = bArr;
    }
    public int getDocType() {
        return this.docType;
    }
    public void setDocType(int i2) {
        this.docType = i2;
    }
    public int getDocNr() {
        return this.docNr;
    }
    public void setDocNr(int i2) {
        this.docNr = i2;
    }
    public void checkBinaries() {
        try {
            int i2 = this.docNr;
            this.customerImage = Convert(i2 == 1 ? BinaryType.btSTRING : i2 == 11 ? BinaryType.btIMAGE : null, this.customerImage);
        } catch (IOException e2) {
            Log.e("CustomerImage.CB", e2.getMessage());
        }
    }
}
