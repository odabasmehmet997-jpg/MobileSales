package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.PrintDestination;
import com.proje.mobilesales.core.enums.PrintLanguage;
import com.proje.mobilesales.core.enums.PrintType;

public class PrintSettings implements Parcelable {
    public static final Parcelable.Creator<PrintSettings> CREATOR = new Parcelable.Creator<PrintSettings>() {
        public PrintSettings createFromParcel(final Parcel parcel) {
            return new PrintSettings(parcel);
        }

        public PrintSettings[] newArray(final int i2) {
            return new PrintSettings[i2];
        }
    };
    int detailLineSize;
    boolean dontShowPreview;
    String endCode;
    FicheType ficheType;
    int ficheTypee;
    int fontHeight;
    String fontId;
    int fontMargin;
    int fontSpace;
    int fontWidth;
    String lastMatterNo;
    PrintDestination mPrintDestination;
    PrintLanguage mPrintLanguage;
    PrintType mPrintType;
    int matterType;
    int previewTime;
    boolean printerFirst;
    boolean removeBlankLines;
    boolean showFooter;
    boolean showHeader;
    String startCode;
    boolean useMatterNo;
    int useTurkishCharacter;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PrintLanguage getPrintLanguage() {
        return mPrintLanguage;
    }

    public void setPrintLanguage(final PrintLanguage printLanguage) {
        mPrintLanguage = printLanguage;
    }

    public PrintType getPrintType() {
        return mPrintType;
    }

    public void setPrintType(final PrintType printType) {
        mPrintType = printType;
    }

    public PrintDestination getPrintDestination() {
        return mPrintDestination;
    }

    public void setPrintDestination(final PrintDestination printDestination) {
        mPrintDestination = printDestination;
    }

    public boolean isRemoveBlankLines() {
        return removeBlankLines;
    }

    public void setRemoveBlankLines(final boolean z) {
        removeBlankLines = z;
    }

    public String getFontId() {
        return fontId;
    }

    public void setFontId(final String str) {
        fontId = str;
    }

    public int getFontWidth() {
        return fontWidth;
    }

    public void setFontWidth(final int i2) {
        fontWidth = i2;
    }

    public int getFontHeight() {
        return fontHeight;
    }

    public void setFontHeight(final int i2) {
        fontHeight = i2;
    }

    public int getFontSpace() {
        return fontSpace;
    }

    public void setFontSpace(final int i2) {
        fontSpace = i2;
    }

    public FicheType getFicheType() {
        return ficheType;
    }

    public void setFicheType(final FicheType ficheType) {
        this.ficheType = ficheType;
    }

    public int getFicheTypee() {
        return ficheTypee;
    }

    public void setFicheTypee(final int i2) {
        ficheTypee = i2;
    }

    public boolean isShowHeader() {
        return showHeader;
    }

    public void setShowHeader(final boolean z) {
        showHeader = z;
    }

    public boolean isShowFooter() {
        return showFooter;
    }

    public void setShowFooter(final boolean z) {
        showFooter = z;
    }

    public boolean isUseMatterNo() {
        return useMatterNo;
    }

    public void setUseMatterNo(final boolean z) {
        useMatterNo = z;
    }

    public boolean isDontShowPreview() {
        return dontShowPreview;
    }

    public void setDontShowPreview(final boolean z) {
        dontShowPreview = z;
    }

    public boolean isPrinterFirst() {
        return printerFirst;
    }

    public void setPrinterFirst(final boolean z) {
        printerFirst = z;
    }

    public int getUseTurkishCharacter() {
        return useTurkishCharacter;
    }

    public void setUseTurkishCharacter(final int i2) {
        useTurkishCharacter = i2;
    }

    public int getPreviewTime() {
        return previewTime;
    }

    public void setPreviewTime(final int i2) {
        previewTime = i2;
    }

    public int getDetailLineSize() {
        return detailLineSize;
    }

    public void setDetailLineSize(final int i2) {
        detailLineSize = i2;
    }

    public int getMatterType() {
        return matterType;
    }

    public void setMatterType(final int i2) {
        matterType = i2;
    }

    public String getLastMatterNo() {
        return lastMatterNo;
    }

    public void setLastMatterNo(final String str) {
        lastMatterNo = str;
    }

    public String getStartCode() {
        return startCode;
    }

    public void setStartCode(final String str) {
        startCode = str;
    }

    public String getEndCode() {
        return endCode;
    }

    public void setEndCode(final String str) {
        endCode = str;
    }

    public int getFontMargin() {
        return fontMargin;
    }

    public void setFontMargin(final int i2) {
        fontMargin = i2;
    }

    public PrintSettings() {
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        final FicheType ficheType = this.ficheType;
        parcel.writeInt(null == ficheType ? -1 : ficheType.ordinal());
        parcel.writeInt(ficheTypee);
        parcel.writeByte(showHeader ? (byte) 1 : (byte) 0);
        parcel.writeByte(showFooter ? (byte) 1 : (byte) 0);
        parcel.writeByte(useMatterNo ? (byte) 1 : (byte) 0);
        parcel.writeByte(dontShowPreview ? (byte) 1 : (byte) 0);
        parcel.writeByte(printerFirst ? (byte) 1 : (byte) 0);
        parcel.writeInt(useTurkishCharacter);
        parcel.writeInt(previewTime);
        parcel.writeInt(detailLineSize);
        parcel.writeInt(matterType);
        parcel.writeString(lastMatterNo);
        parcel.writeString(startCode);
        parcel.writeString(endCode);
        parcel.writeByte(removeBlankLines ? (byte) 1 : (byte) 0);
        parcel.writeString(fontId);
        parcel.writeInt(fontWidth);
        parcel.writeInt(fontHeight);
        parcel.writeInt(fontSpace);
        parcel.writeInt(fontMargin);
        final PrintLanguage printLanguage = mPrintLanguage;
        parcel.writeInt(null == printLanguage ? -1 : printLanguage.ordinal());
        final PrintType printType = mPrintType;
        parcel.writeInt(null == printType ? -1 : printType.ordinal());
        final PrintDestination printDestination = mPrintDestination;
        parcel.writeInt(null != printDestination ? printDestination.ordinal() : -1);
    }

    protected PrintSettings(final Parcel parcel) {
        final int readInt = parcel.readInt();
        ficheType = -1 == readInt ? null : FicheType.values()[readInt];
        ficheTypee = parcel.readInt();
        showHeader = 0 != parcel.readByte();
        showFooter = 0 != parcel.readByte();
        useMatterNo = 0 != parcel.readByte();
        dontShowPreview = 0 != parcel.readByte();
        printerFirst = 0 != parcel.readByte();
        useTurkishCharacter = parcel.readInt();
        previewTime = parcel.readInt();
        detailLineSize = parcel.readInt();
        matterType = parcel.readInt();
        lastMatterNo = parcel.readString();
        startCode = parcel.readString();
        endCode = parcel.readString();
        removeBlankLines = 0 != parcel.readByte();
        fontId = parcel.readString();
        fontWidth = parcel.readInt();
        fontHeight = parcel.readInt();
        fontSpace = parcel.readInt();
        fontMargin = parcel.readInt();
        final int readInt2 = parcel.readInt();
        mPrintLanguage = -1 == readInt2 ? null : PrintLanguage.values()[readInt2];
        final int readInt3 = parcel.readInt();
        mPrintType = -1 == readInt3 ? null : PrintType.values()[readInt3];
        final int readInt4 = parcel.readInt();
        mPrintDestination = -1 != readInt4 ? PrintDestination.values()[readInt4] : null;
    }
}
