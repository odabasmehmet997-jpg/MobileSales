package com.proje.mobilesales.features.licence.model.database;

import kotlin.jvm.internal.Intrinsics;

public final class LicenseInfo {
    private String encryptedProductInfo;
    private String licenseKey;
    private String licenseRenewalDate;
    private String productExpiryDate;
    private String toleranceDays;
    private String warningDays;

    public static  LicenseInfo copydefault(final LicenseInfo licenseInfo, String str, String str2, String str3, String str4, String str5, String str6, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            str = licenseInfo.encryptedProductInfo;
        }
        if (0 != (i2 & 2)) {
            str2 = licenseInfo.productExpiryDate;
        }
        final String str7 = str2;
        if (0 != (i2 & 4)) {
            str3 = licenseInfo.warningDays;
        }
        final String str8 = str3;
        if (0 != (i2 & 8)) {
            str4 = licenseInfo.toleranceDays;
        }
        final String str9 = str4;
        if (0 != (i2 & 16)) {
            str5 = licenseInfo.licenseRenewalDate;
        }
        final String str10 = str5;
        if (0 != (i2 & 32)) {
            str6 = licenseInfo.licenseKey;
        }
        return licenseInfo.copy(str, str7, str8, str9, str10, str6);
    }

    public String component1() {
        return encryptedProductInfo;
    }

    public String component2() {
        return productExpiryDate;
    }

    public String component3() {
        return warningDays;
    }

    public String component4() {
        return toleranceDays;
    }

    public String component5() {
        return licenseRenewalDate;
    }

    public String component6() {
        return licenseKey;
    }

    public LicenseInfo copy(final String encryptedProductInfo, final String productExpiryDate, final String warningDays, final String toleranceDays, final String licenseRenewalDate, final String licenseKey) {
        Intrinsics.checkNotNullParameter(encryptedProductInfo, "encryptedProductInfo");
        Intrinsics.checkNotNullParameter(productExpiryDate, "productExpiryDate");
        Intrinsics.checkNotNullParameter(warningDays, "warningDays");
        Intrinsics.checkNotNullParameter(toleranceDays, "toleranceDays");
        Intrinsics.checkNotNullParameter(licenseRenewalDate, "licenseRenewalDate");
        Intrinsics.checkNotNullParameter(licenseKey, "licenseKey");
        return new LicenseInfo(encryptedProductInfo, productExpiryDate, warningDays, toleranceDays, licenseRenewalDate, licenseKey);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LicenseInfo licenseInfo)) {
            return false;
        }
        return Intrinsics.areEqual(encryptedProductInfo, licenseInfo.encryptedProductInfo) && Intrinsics.areEqual(productExpiryDate, licenseInfo.productExpiryDate) && Intrinsics.areEqual(warningDays, licenseInfo.warningDays) && Intrinsics.areEqual(toleranceDays, licenseInfo.toleranceDays) && Intrinsics.areEqual(licenseRenewalDate, licenseInfo.licenseRenewalDate) && Intrinsics.areEqual(licenseKey, licenseInfo.licenseKey);
    }

    public int hashCode() {
        return (((((((((encryptedProductInfo.hashCode() * 31) + productExpiryDate.hashCode()) * 31) + warningDays.hashCode()) * 31) + toleranceDays.hashCode()) * 31) + licenseRenewalDate.hashCode()) * 31) + licenseKey.hashCode();
    }

    public String toString() {
        return "LicenseInfo(encryptedProductInfo=" + encryptedProductInfo + ", productExpiryDate=" + productExpiryDate + ", warningDays=" + warningDays + ", toleranceDays=" + toleranceDays + ", licenseRenewalDate=" + licenseRenewalDate + ", licenseKey=" + licenseKey + ')';
    }

    public LicenseInfo(final String encryptedProductInfo, final String productExpiryDate, final String warningDays, final String toleranceDays, final String licenseRenewalDate, final String licenseKey) {
        Intrinsics.checkNotNullParameter(encryptedProductInfo, "encryptedProductInfo");
        Intrinsics.checkNotNullParameter(productExpiryDate, "productExpiryDate");
        Intrinsics.checkNotNullParameter(warningDays, "warningDays");
        Intrinsics.checkNotNullParameter(toleranceDays, "toleranceDays");
        Intrinsics.checkNotNullParameter(licenseRenewalDate, "licenseRenewalDate");
        Intrinsics.checkNotNullParameter(licenseKey, "licenseKey");
        this.encryptedProductInfo = encryptedProductInfo;
        this.productExpiryDate = productExpiryDate;
        this.warningDays = warningDays;
        this.toleranceDays = toleranceDays;
        this.licenseRenewalDate = licenseRenewalDate;
        this.licenseKey = licenseKey;
    }

    public String getEncryptedProductInfo() {
        return encryptedProductInfo;
    }

    public void setEncryptedProductInfo(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        encryptedProductInfo = str;
    }

    public String getProductExpiryDate() {
        return productExpiryDate;
    }

    public void setProductExpiryDate(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        productExpiryDate = str;
    }

    public String getWarningDays() {
        return warningDays;
    }

    public void setWarningDays(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        warningDays = str;
    }

    public String getToleranceDays() {
        return toleranceDays;
    }

    public void setToleranceDays(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        toleranceDays = str;
    }

    public String getLicenseRenewalDate() {
        return licenseRenewalDate;
    }

    public void setLicenseRenewalDate(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        licenseRenewalDate = str;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        licenseKey = str;
    }

    public LicenseInfo() {
        this("", "", "", "", "", "");
    }
}
