package com.proje.mobilesales.core.utils;

import android.text.TextUtils;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.features.model.Disc;
import com.proje.mobilesales.features.model.FicheDiscountProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;


/* compiled from: CalculateUtils.kt */

public final class CalculateUtils {
    public static final CalculateUtils INSTANCE = new CalculateUtils();
    private static final String TAG = "CalculateUtils";

    public static double calculateVolume(double d2, double d3) {
        return d2 * d3;
    }

    public static double calculateWeight(double d2, double d3) {
        return d2 * d3;
    }

    public static double convertUnitPrice(double d2, double d3, double d4, double d5, double d6) {
        return (d3 == 0.0d || d6 == 0.0d) ? d2 : (((d2 * d4) / d3) / d6) * d5;
    }

    public static boolean customerOverRisk(double d2, double d3, double d4, double d5, double d6) {
        return ((d2 + d3) - d4) - d6 < d5;
    }

    public static double customerOverRiskAmount(double d2, double d3) {
        double d4 = d3 - d2;
        return d4 < 0.0d ? d4 * (-1) : d4;
    }

    public static double customerOverRiskAmount(double d2, double d3, double d4, double d5, double d6) {
        double d7 = (((d2 + d3) - d4) - d6) - d5;
        return d7 < 0.0d ? d7 * (-1) : d7;
    }

    public static double reCalculateActualStock(double d2, double d3, double d4, double d5, double d6) {
        return (((d2 * d4) / d3) / d6) * d5;
    }

    public double convertUnitPrice(double d2, double d3, double d4) {
        return (d2 * d4) / d3;
    }

    private CalculateUtils() {
    }

    public int convertMillisWebOrder(long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j2);
        return (calendar.get(5) - 1) + (calendar.get(2) * 256) + ((calendar.get(1) - 1970) * 65536);
    }

    public double calculateIncludeVatPrice(String str, String str2) {
        return calculateIncludeVatPrice(StringUtils.convertStringToDouble(str), StringUtils.convertStringToDouble(str2));
    }

    public static double calculateIncludeVatPrice(double d2, String str) {
        return calculateIncludeVatPrice(d2, StringUtils.convertStringToDouble(str));
    }

    public static double calculateIncludeVatPrice(double d2, double d3) {
        return roundFive(d2 / (1 + (d3 / 100)));
    }

    public static double calculatePriceAddVat(double d2, double d3, boolean z) {
        if (!z) {
            d2 *= 1 + (d3 / 100);
        }
        return roundFive(d2);
    }

    public static Disc calculateDiscountLineSalesDetail(Disc disc, double d2, double d3) {
        double roundFive;
        Intrinsics.checkNotNullParameter(disc, "disc");
        double roundFive2 = roundFive(d2);
        double roundFive3 = roundFive(d3);
        disc.discount = 0.0d;
        disc.discountVat = 0.0d;
        double d4 = disc.grossTotal;
        if (roundFive2 > 0.0d) {
            roundFive = roundFive(roundFive2);
        } else {
            roundFive = roundFive3 > 0.0d ? roundFive((100 * roundFive3) / d4) : 0.0d;
        }
        if (roundFive > 0.0d) {
            if (roundFive > 100.0d) {
                roundFive = 100.0d;
            }
            double d5 = 100;
            double roundFive4 = roundFive((d4 * roundFive) / d5);
            double roundFive5 = roundFive((disc.vat * roundFive) / d5);
            disc.discount = roundFive(roundFive4);
            disc.discountVat = roundFive(roundFive5);
            disc.lineNet = roundFive(disc.lineNet - roundFive4);
            disc.vat = roundFive(disc.vat - roundFive5);
            double d6 = d4 - roundFive4;
            disc.vatMatrah = roundFive(d6);
            disc.grossTotal = roundFive(d6);
        }
        return disc;
    }

    public static Disc calculateDiscountLineSalesDetail(Disc disc, FicheDiscountProp ratioProp, FicheDiscountProp totalProp) {
        double roundFive;
        Intrinsics.checkNotNullParameter(disc, "disc");
        Intrinsics.checkNotNullParameter(ratioProp, "ratioProp");
        Intrinsics.checkNotNullParameter(totalProp, "totalProp");
        double roundFive2 = roundFive(ratioProp.getDefinitionDouble());
        double roundFive3 = roundFive(totalProp.getDefinitionDouble());
        disc.discount = 0.0d;
        disc.discountVat = 0.0d;
        double d2 = disc.grossTotal;
        if (roundFive2 > 0.0d) {
            roundFive = roundFive(roundFive2);
        } else {
            roundFive = (roundFive3 <= 0.0d || d2 <= 0.0d) ? 0.0d : roundFive((100 * roundFive3) / d2);
        }
        if (roundFive > 0.0d) {
            if (roundFive > 100.0d) {
                FicheStringProp.setDefinition("100");
                FicheStringProp.setDefinition("0");
                roundFive = 100.0d;
            }
            double d3 = 100;
            double roundFive4 = roundFive((d2 * roundFive) / d3);
            double roundFive5 = roundFive((disc.vat * roundFive) / d3);
            disc.discount = roundFive(roundFive4);
            disc.discountVat = roundFive(roundFive5);
            disc.lineNet = roundFive(disc.lineNet - roundFive4);
            disc.vat = roundFive(disc.vat - roundFive5);
            double d4 = d2 - roundFive4;
            disc.vatMatrah = roundFive(d4);
            disc.grossTotal = roundFive(d4);
        }
        return disc;
    }

    public static Disc calculateDiscountGeneral(Disc disc, double d2, double d3) {
        double roundFive;
        Intrinsics.checkNotNullParameter(disc, "disc");
        double roundFive2 = roundFive(d2);
        double roundFive3 = roundFive(d3);
        disc.discount = 0.0d;
        disc.discountVat = 0.0d;
        double d4 = disc.grossTotal;
        if (roundFive2 > 0.0d) {
            roundFive = roundFive(roundFive2);
        } else {
            roundFive = roundFive3 > 0.0d ? roundFive((100 * roundFive3) / disc.productTotal) : 0.0d;
        }
        if (roundFive > 0.0d) {
            if (roundFive > 100.0d) {
                roundFive = 100.0d;
            }
            double d5 = 100;
            double roundFive4 = roundFive((disc.lineNet * roundFive) / d5);
            double roundFive5 = roundFive((disc.vat * roundFive) / d5);
            disc.discount = roundFive(roundFive4);
            disc.discountVat = roundFive(roundFive5);
            disc.lineNet = roundFive(disc.lineNet - roundFive4);
            disc.vat = roundFive(disc.vat - roundFive5);
            double d6 = d4 - roundFive4;
            disc.vatMatrah = roundFive(d6);
            disc.grossTotal = roundFive(d6);
        }
        return disc;
    }

    public Disc calculateDiscount(double d2, Disc disc, double d3, double d4) {
        double roundFive;
        Intrinsics.checkNotNullParameter(disc, "disc");
        double roundFive2 = roundFive(d3);
        double roundFive3 = roundFive(d4);
        disc.discount = 0.0d;
        disc.discountVat = 0.0d;
        disc.grossTotal = roundFive(d2);
        if (roundFive2 > 0.0d) {
            roundFive = roundFive(roundFive2);
        } else {
            roundFive = roundFive3 > 0.0d ? roundFive((100 * roundFive3) / d2) : 0.0d;
        }
        if (roundFive > 0.0d) {
            if (roundFive > 100.0d) {
                roundFive = 100.0d;
            }
            double d5 = 100;
            double roundFive4 = roundFive((disc.lineNet * roundFive) / d5);
            double roundFive5 = roundFive((disc.vat * roundFive) / d5);
            disc.discount = roundFive(roundFive4);
            disc.discountVat = roundFive(roundFive5);
            disc.grossTotal = roundFive(disc.grossTotal - roundFive4);
            disc.lineNet = roundFive(disc.lineNet - roundFive4);
            disc.vatMatrah = roundFive(d2 - roundFive4);
            disc.vat = roundFive(disc.vat - roundFive5);
        }
        return disc;
    }

    public static double calculateDiscountRatioToTotal(double d2, double d3) {
        return (roundFive(d2) * roundFive(d3)) / 100;
    }

    public static double roundFive(double d2) {
        return INSTANCE.round(d2, 5);
    }

    public double roundTwo(double d2) {
        return round(d2, 2);
    }

    public double round(double d2, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        return Math.round(d2 * r0) / ((long) Math.pow(10.0d, i2));
    }

    public static boolean isGattribNotUseKdv(int i2) {
        String binaryString = Integer.toBinaryString(i2);
        if (TextUtils.isEmpty(binaryString)) {
            return false;
        }
        Intrinsics.checkNotNull(binaryString);
        return StringsKt.endsWithdefault(binaryString, BuildConfig.NETSIS_DEMO_PASSWORD, false, 2, (Object) null);
    }

    public static double calculateSerialLotTotals(ArrayList<Serilot> arrayList, double d2, double d3) {
        double d4 = 0.0d;
        if (arrayList == null || arrayList.size() == 0) {
            return 0.0d;
        }
        double d5 = d3 == 0.0d ? 1.0d : d2 / d3;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            d4 += arrayList.get(i2).amount;
        }
        return d4 * d5;
    }

    public static double calculateSeriLotAmount(List<ItemUnits> list, ArrayList<Serilot> arrayList) {
        if (arrayList == null || arrayList.isEmpty() || list == null || list.isEmpty()) {
            return 0.0d;
        }
        Iterator<Serilot> it = arrayList.iterator();
        double d2 = 0.0d;
        double d3 = 0.0d;
        while (it.hasNext()) {
            Serilot next = it.next();
            for (ItemUnits itemUnits : list) {
                if (Intrinsics.areEqual(itemUnits.code, next.unit)) {
                    double d4 = itemUnits.convfact1;
                    d3 = (d4 == 0.0d ? 1.0d : itemUnits.convfact2 / d4) * next.amount;
                }
            }
            d2 += d3;
        }
        return d2;
    }
}
