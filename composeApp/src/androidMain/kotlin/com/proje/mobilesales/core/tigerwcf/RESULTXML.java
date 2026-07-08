package com.proje.mobilesales.core.tigerwcf;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.window.embedding.EmbeddingCompat;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


@Root

public class RESULTXML implements Parcelable {
    public static final Parcelable.Creator<RESULTXML> CREATOR = new Parcelable.Creator<RESULTXML>() { // from class: com.proje.mobilesales.core.tigerwcf.RESULTXML.1
        
        
        public RESULTXML createFromParcel(Parcel parcel) {
            return new RESULTXML(parcel);
        }

        
        
        public RESULTXML[] newArray(int i2) {
            return new RESULTXML[i2];
        }
    };

    @ElementList(inline = EmbeddingCompat.DEBUG)
    public List<RESULTLINE> resultLines;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<RESULTLINE> getResultLine() {
        return this.resultLines;
    }

    public void setResultLine(List<RESULTLINE> list) {
        this.resultLines = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeList(this.resultLines);
    }

    public RESULTXML() {
    }

    protected RESULTXML(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        this.resultLines = arrayList;
        parcel.readList(arrayList, RESULTLINE.class.getClassLoader());
    }
}
