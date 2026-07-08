package com.proje.mobilesales.features.licence;

import android.text.TextUtils;
import android.util.Log;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.utils.AesEncryption;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.dbmodel.Param;
import com.proje.mobilesales.features.licence.model.LicenseInformationType;
import com.proje.mobilesales.features.licence.model.database.LicenseInfo;
import com.proje.mobilesales.features.licence.repository.LicenseRepository;
import com.proje.mobilesales.features.licence.viewmodel.LicenseViewModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;
 
public final class LicenseUtils {
    public static final LicenseUtils INSTANCE = new LicenseUtils();
    private static final LicenseInfo licenseInformation = new LicenseInfo();
    private static final LicenseRepository repository;
    private static final LicenseViewModel viewModel;
 
    public class WhenMappings {
        public static final int[] EnumSwitchMapping0;

        static {
            int[] iArr = new int[LicenseInformationType.values().length];
            try {
                iArr[LicenseInformationType.PRODUCT_EXPIRY_DATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LicenseInformationType.WARNING_DAYS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[LicenseInformationType.TOLERANCE_DAYS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[LicenseInformationType.LICENSE_RENEWAL_DATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[LicenseInformationType.LICENSE_KEY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            EnumSwitchMapping0 = iArr;
        }
    }

    private LicenseUtils() {
    }

    static {
        LicenseRepository licenseRepository = new LicenseRepository();
        repository = licenseRepository;
        viewModel = new LicenseViewModel(licenseRepository);
    }

    public static void setProductInfo(List<? extends Param> paramList) {
        Intrinsics.checkNotNullParameter(paramList, "paramList");
        for (Param param : paramList) {
            if (Intrinsics.areEqual(param.getParamNo(), "ProductInfo")) {
                LicenseInfo licenseInfo = licenseInformation;
                String paramValue = param.getParamValue();
                Intrinsics.checkNotNullExpressionValue(paramValue, "getParamValue(...)");
                licenseInfo.setEncryptedProductInfo(paramValue);
            }
        }
    }

    public static String getEncryptedProductInfo() {
        return licenseInformation.getEncryptedProductInfo();
    }

    public static void changeLicenseKey(String encryptedProductInfo) {
        LicenseUtils licenseUtils;
        String decryptLicenseKey;
        Intrinsics.checkNotNullParameter(encryptedProductInfo, "encryptedProductInfo");
        if (TextUtils.isEmpty(encryptedProductInfo) || (decryptLicenseKey = (licenseUtils = INSTANCE).decryptLicenseKey(encryptedProductInfo)) == null) {
            return;
        }
        licenseUtils.setLicenseKeyValues(decryptLicenseKey);
    }

    private String decryptLicenseKey(String str) {
        try {
            return new AesEncryption().decrypt(str);
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "License Key Error", e2);
            return null;
        }
    }

    private void setLicenseKeyValues(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            for (LicenseInformationType licenseInformationType : LicenseInformationType.values()) {
                String string = jSONObject.getString(String.valueOf(licenseInformationType.getId()));
                if (string != null) {
                    int i2 = WhenMappings.EnumSwitchMapping0[licenseInformationType.ordinal()];
                    if (i2 == 1) {
                        licenseInformation.setProductExpiryDate(string);
                    } else if (i2 == 2) {
                        licenseInformation.setWarningDays(string);
                    } else if (i2 == 3) {
                        licenseInformation.setToleranceDays(string);
                    } else if (i2 == 4) {
                        licenseInformation.setLicenseRenewalDate(string);
                    } else if (i2 == 5) {
                        licenseInformation.setLicenseKey(string);
                    }
                }
            }
            viewModel.insertLicenseInfo(licenseInformation);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static String getLicenseKey() {
        return licenseInformation.getLicenseKey();
    }

    public static String getProductExpiryDate() {
        return licenseInformation.getProductExpiryDate();
    }

    public static String getWarningDays() {
        return licenseInformation.getWarningDays();
    }

    public static String getToleranceDays() {
        return licenseInformation.getToleranceDays();
    }

    public static String getLicenseRenewalDate() {
        return licenseInformation.getLicenseRenewalDate();
    }

    private Date getToday() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
        return parse;
    }

    private Date getExpiryDate() {
        Date parse = null;
        try {
            parse = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).parse(getProductExpiryDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
        return parse;
    }

    public static boolean isExpired() {
        LicenseUtils licenseUtils = INSTANCE;
        return licenseUtils.getToday().after(licenseUtils.getExpiryDate());
    }

    public static boolean isInWarningDays() {
        int convertStringToInt = StringUtils.convertStringToInt(getWarningDays());
        TimeUnit timeUnit = TimeUnit.DAYS;
        LicenseUtils licenseUtils = INSTANCE;
        return ((int) timeUnit.convert(licenseUtils.getExpiryDate().getTime() - licenseUtils.getToday().getTime(), TimeUnit.MILLISECONDS)) <= convertStringToInt;
    }
}
