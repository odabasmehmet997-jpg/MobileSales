package com.proje.mobilesales.features.penetration.model.database;

import android.util.Log;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.enums.BinaryType;
import com.proje.mobilesales.core.interfaces.ConvertDb;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.dbmodel.ConvertBinaries;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheImageProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.penetration.model.PenetrationLine;
import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;

public final class UserPenetration extends ConvertBinaries implements ConvertDb<PenetrationLine> {
    private double amount;
    private String cCode;
    private int cid;
    private String currency;
    private String date;
    private String defField1;
    private String defField10;
    private String defField2;
    private String defField3;
    private String defField4;
    private String defField5;
    private String defField6;
    private String defField7;
    private String defField8;
    private String defField9;
    private int f1260id;
    private int isTransfer;
    private int modified;
    private String note;
    private int penetFicheId;
    private byte[] penetrationImage;
    private int pntId;
    private int pntdetId;
    private double price;
    private int sourceType;
    private String uniqeVal;
    private int f1261vy;

    public int getId() {
        return f1260id;
    }

    public void setId(final int i) {
        f1260id = i;
    }

    public int getPntId() {
        return pntId;
    }

    public void setPntId(final int i) {
        pntId = i;
    }

    public int isTransfer() {
        return isTransfer;
    }

    public void setTransfer(final int i) {
        isTransfer = i;
    }

    public int getPntdetId() {
        return pntdetId;
    }

    public void setPntdetId(final int i) {
        pntdetId = i;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(final int i) {
        cid = i;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String str) {
        date = str;
    }

    public int getVy() {
        return f1261vy;
    }

    public void setVy(final int i) {
        f1261vy = i;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double d) {
        amount = d;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double d) {
        price = d;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String str) {
        currency = str;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(final int i) {
        sourceType = i;
    }

    public String getUniqeVal() {
        return uniqeVal;
    }

    public void setUniqeVal(final String str) {
        uniqeVal = str;
    }

    public int getPenetFicheId() {
        return penetFicheId;
    }

    public void setPenetFicheId(final int i) {
        penetFicheId = i;
    }

    public String getDefField1() {
        return defField1;
    }

    public void setDefField1(final String str) {
        defField1 = str;
    }

    public String getDefField2() {
        return defField2;
    }

    public void setDefField2(final String str) {
        defField2 = str;
    }

    public String getDefField3() {
        return defField3;
    }

    public void setDefField3(final String str) {
        defField3 = str;
    }

    public String getDefField4() {
        return defField4;
    }

    public void setDefField4(final String str) {
        defField4 = str;
    }

    public String getDefField5() {
        return defField5;
    }

    public void setDefField5(final String str) {
        defField5 = str;
    }

    public String getDefField6() {
        return defField6;
    }

    public void setDefField6(final String str) {
        defField6 = str;
    }

    public String getDefField7() {
        return defField7;
    }

    public void setDefField7(final String str) {
        defField7 = str;
    }

    public String getDefField8() {
        return defField8;
    }

    public void setDefField8(final String str) {
        defField8 = str;
    }

    public String getDefField9() {
        return defField9;
    }

    public void setDefField9(final String str) {
        defField9 = str;
    }

    public String getDefField10() {
        return defField10;
    }

    public void setDefField10(final String str) {
        defField10 = str;
    }

    public byte[] getPenetrationImage() {
        return penetrationImage;
    }

    public void setPenetrationImage(final byte[] bArr) {
        penetrationImage = bArr;
    }

    public int getModified() {
        return modified;
    }

    public void setModified(final int i) {
        modified = i;
    }

    public String getNote() {
        return note;
    }

    public void setNote(final String str) {
        note = str;
    }

    public void convertDbType(final PenetrationLine penetrationLine) {
        Intrinsics.checkNotNullParameter(penetrationLine, "object");
        f1260id = penetrationLine.getId();
        final FicheDiscountRefProp currency = penetrationLine.getCurrency();
        Intrinsics.checkNotNull(currency);
        this.currency = currency.getCode();
        final FicheStringProp amount = penetrationLine.getAmount();
        Intrinsics.checkNotNull(amount);
        this.amount = amount.getDefinitionDouble();
        final FicheStringProp price = penetrationLine.getPrice();
        Intrinsics.checkNotNull(price);
        this.price = price.getDefinitionDouble();
        pntdetId = penetrationLine.getPenetrationDetailId();
        final FicheBooleanProp exist = penetrationLine.getExist();
        Intrinsics.checkNotNull(exist);
        f1261vy = exist.isSelect() ? 1 : 0;
        pntdetId = penetrationLine.getPenetrationDetailId();
        final FicheImageProp image = penetrationLine.getImage();
        Intrinsics.checkNotNull(image);
        penetrationImage = image.getImageArray();
        note = String.valueOf(penetrationLine.getNot());
    }
    public PenetrationLine convert() {
        final PenetrationLine penetrationLine = new PenetrationLine();
        penetrationLine.setCurrency(new FicheDiscountRefProp());
        penetrationLine.setExist(new FicheBooleanProp());
        penetrationLine.setAmount(new FicheDiscountRefProp());
        penetrationLine.setPrice(new FicheDiscountRefProp());
        penetrationLine.setImage(new FicheImageProp());
        final FicheDiscountRefProp currency = penetrationLine.getCurrency();
        Intrinsics.checkNotNull(currency);
        currency.setCode(this.currency);
        final FicheBooleanProp exist = penetrationLine.getExist();
        Intrinsics.checkNotNull(exist);
        boolean z = 1 == this.f1261vy;
        exist.setSelect(z);
        final FicheStringProp amount = penetrationLine.getAmount();
        Intrinsics.checkNotNull(amount);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(this.amount)));
        final FicheStringProp price = penetrationLine.getPrice();
        Intrinsics.checkNotNull(price);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(this.price)));
        final FicheImageProp image = penetrationLine.getImage();
        Intrinsics.checkNotNull(image);
        image.setImageArray(penetrationImage);
        penetrationLine.setPenetrationDetailId(pntdetId);
        penetrationLine.setId(f1260id);
        penetrationLine.setNot(new FicheStringProp(note));
        return penetrationLine;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(final String str) {
        cCode = str;
    }
    public void checkBinaries() {
        try {
            penetrationImage = this.Convert(BinaryType.btIMAGE, penetrationImage);
        } catch (final IOException e) {
            final String message = e.getMessage();
            Intrinsics.checkNotNull(message);
            Log.e("CustomerImage.UserP", message);
        }
    }
}
