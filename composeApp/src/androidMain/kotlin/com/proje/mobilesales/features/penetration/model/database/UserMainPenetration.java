package com.proje.mobilesales.features.penetration.model.database;

import com.proje.mobilesales.core.interfaces.ConvertDb;
import com.proje.mobilesales.features.model.FicheImageProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.penetration.model.Penetration;
import kotlin.jvm.internal.Intrinsics;

public final class UserMainPenetration implements ConvertDb<Penetration> {
    private String clientCode;
    private int clientRef;
    private String date;
    private int id;
    private int isTransfer;
    private String note;
    private int penetrationId;
    private byte[] penetrationImage;
    private String pnt_GUID;
    private int visitInfoId;

    public int getId() {
        return this.id;
    }

    public void setId(final int i) {
        this.id = i;
    }
    public int getPenetrationId() {
        return this.penetrationId;
    }
    public void setPenetrationId(int i) {
        this.penetrationId = i;
    }
    public int getClientRef() {
        return this.clientRef;
    }
    public void setClientRef(int i) {
        this.clientRef = i;
    }
    public String getClientCode() {
        return this.clientCode;
    }
    public void setClientCode(String str) {
        this.clientCode = str;
    }
    public int isTransfer() {
        return this.isTransfer;
    }
    public void setTransfer(int i) {
        this.isTransfer = i;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String str) {
        this.date = str;
    }
    public byte[] getPenetrationImage() {
        return this.penetrationImage;
    }
    public void setPenetrationImage(byte[] bArr) {
        this.penetrationImage = bArr;
    }
    public String getNote() {
        return this.note;
    }
    public void setNote(String str) {
        this.note = str;
    }
    public String getPnt_GUID() {
        return this.pnt_GUID;
    }
    public void setPnt_GUID(String str) {
        this.pnt_GUID = str;
    }
    public int getVisitInfoId() {
        return this.visitInfoId;
    }
    public void setVisitInfoId(int i) {
        this.visitInfoId = i;
    }
    public void convertDbType(Penetration penetration) {
        Intrinsics.checkNotNullParameter(penetration, "object");
        this.clientRef = penetration.getClRef();
        this.clientCode = penetration.getClCode();
        this.date = penetration.getFicheDate();
        this.id = penetration.getId();
        this.isTransfer = penetration.isTransfer() ? 1 : 0;
        this.penetrationId = penetration.getPenetrationId();
        FicheImageProp image = penetration.getImage();
        Intrinsics.checkNotNull(image);
        this.penetrationImage = image.getImageArray();
        this.note = String.valueOf(penetration.getNot());
        this.pnt_GUID = penetration.getPnt_GUID();
    }
    public Penetration convert() {
        Penetration penetration = new Penetration();
        penetration.setId(this.id);
        penetration.setClCode(this.clientCode);
        penetration.setClRef(this.clientRef);
        penetration.setFicheDate(this.date);
        penetration.setPenetrationId(this.penetrationId);
        boolean z = this.isTransfer == 1;
        penetration.setTransfer(z);
        penetration.setNot(new FicheStringProp(this.note));
        penetration.setImage(new FicheImageProp(this.penetrationImage));
        penetration.setPnt_GUID(this.pnt_GUID);
        return penetration;
    }
}
