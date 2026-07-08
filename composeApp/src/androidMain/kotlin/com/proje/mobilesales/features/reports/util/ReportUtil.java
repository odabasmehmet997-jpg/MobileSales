package com.proje.mobilesales.features.reports.util;

import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.reportparser.Report;
import com.proje.mobilesales.core.reportparser.ReportColumn;
import com.proje.mobilesales.core.reportparser.ReportConstParam;
import com.proje.mobilesales.core.reportparser.ReportConstParamProp;
import com.proje.mobilesales.core.reportparser.ReportParam;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.reports.model.enums.ReportColumnDataType;
import com.proje.mobilesales.features.reports.model.enums.ReportDisplayType;
import com.proje.mobilesales.features.userreport.model.enums.UserReportsType;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsChartActivity;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsFormActivity;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivity;
import java.util.List;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
 
public final class ReportUtil {
    public static final ReportUtil INSTANCE = new ReportUtil();
    public static final String USER_REPORTS = "USER_REPORTS";
    public enum WhenMappings {
        ;
        public static final int[] EnumSwitchMapping0;
        public static final int[] EnumSwitchMapping1;
        public static final int[] EnumSwitchMapping2;

        static {
            final int[] iArr = new int[UserReportsType.values().length];
            try {
                iArr[UserReportsType.general.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                iArr[UserReportsType.customer.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                iArr[UserReportsType.item.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                iArr[UserReportsType.dashboard.ordinal()] = 4;
            } catch (final NoSuchFieldError unused4) {
            }
            EnumSwitchMapping0 = iArr;
            final int[] iArr2 = new int[ReportDisplayType.values().length];
            try {
                iArr2[ReportDisplayType.Form.ordinal()] = 1;
            } catch (final NoSuchFieldError unused5) {
            }
            try {
                iArr2[ReportDisplayType.Grid.ordinal()] = 2;
            } catch (final NoSuchFieldError unused6) {
            }
            try {
                iArr2[ReportDisplayType.Chart.ordinal()] = 3;
            } catch (final NoSuchFieldError unused7) {
            }
            EnumSwitchMapping1 = iArr2;
            final int[] iArr3 = new int[ReportColumnDataType.values().length];
            try {
                iArr3[ReportColumnDataType.DateTime.ordinal()] = 1;
            } catch (final NoSuchFieldError unused8) {
            }
            try {
                iArr3[ReportColumnDataType.Decimal.ordinal()] = 2;
            } catch (final NoSuchFieldError unused9) {
            }
            try {
                iArr3[ReportColumnDataType.String.ordinal()] = 3;
            } catch (final NoSuchFieldError unused10) {
            }
            try {
                iArr3[ReportColumnDataType.Numeric.ordinal()] = 4;
            } catch (final NoSuchFieldError unused11) {
            }
            EnumSwitchMapping2 = iArr3;
        }
    }

    private ReportUtil() {
    }
    private String convertToType(final String str, final String str2) {
        if (null == str) {
            return null;
        }
        final int hashCode = str.hashCode();
        if (-335760659 != hashCode) {
            if (2603341 != hashCode) {
                if (1857393595 == hashCode && str.equals(ExifInterface.TAG_DATETIME)) {
                    String sb = '\'' +
                            (10 >= str2.length() ? DateAndTimeUtils.convertDateSqlDateWithoutTime(str2) : DateAndTimeUtils.convertDateSqlDate(str2)) +
                            '\'';
                    return sb;
                }
            } else if ("Numeric".equals(str)) {
                if (0 < StringsKt.indexOf(str2, ".", 0, false)) {
                    return String.valueOf(StringUtils.convertStringToDouble(str2));
                }
                return String.valueOf(StringUtils.convertStringToDoubleNetsis(str2));
            }
        }
        return "";
    }

    private static ReportConstParam findConstantParam(final String str) {
        for (final ReportConstParam reportConstParam : ReportConstParam.values()) {
            if (Intrinsics.areEqual(reportConstParam.getProp(), str)) {
                return reportConstParam;
            }
        }
        return null;
    }

    public static   ReportConstParamProp getReportConsParamProp(final UserReportsType userReportsType) {
        final int i2 = null == userReportsType ? -1 : WhenMappings.EnumSwitchMapping0[userReportsType.ordinal()];
        if (1 == i2) {
            return ReportConstParamProp.None;
        }
        if (2 == i2) {
            return ReportConstParamProp.Cari;
        }
        if (3 == i2) {
            return ReportConstParamProp.Stok;
        }
        if (4 == i2) {
            return ReportConstParamProp.None;
        }
        return ReportConstParamProp.None;
    }

    public static void replaceParameters(final Report report, final ReportConstParamProp paramProp, final int i2) {
        char c2;
        Intrinsics.checkNotNullParameter(report, "report");
        Intrinsics.checkNotNullParameter(paramProp, "paramProp");
        if (null != report.getReportParams()) {
            String orgSql = report.getOrgSql();
            for (final ReportParam reportParam : report.getReportParams()) {
                Intrinsics.checkNotNull(orgSql);
                if (StringsKt.contains(orgSql, ":", false)) {
                    final ReportUtil reportUtil = ReportUtil.INSTANCE;
                    final String name = reportParam.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                    if (findConstantParam(name) == ReportConstParam.CariHesapKodu && ReportConstParamProp.Cari == paramProp) {
                        reportParam.setValues(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getClCode(i2));
                        Intrinsics.checkNotNull(orgSql);
                        final String str = ':' + reportParam.getName();
                        final String dataType = reportParam.getDataType();
                        final String valuesForSql = reportParam.getValuesForSql();
                        Intrinsics.checkNotNullExpressionValue(valuesForSql, "getValuesForSql(...)");
                        orgSql = StringsKt.replace(orgSql, str, reportUtil.convertToType(dataType, valuesForSql), false);
                    } else {
                        final String name2 = reportParam.getName();
                        Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
                        if (findConstantParam(name2) == ReportConstParam.MalzemeKodu && ReportConstParamProp.Stok == paramProp) {
                            reportParam.setValues(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getItemCode(i2));
                            Intrinsics.checkNotNull(orgSql);
                            final String str2 = ':' + reportParam.getName();
                            final String dataType2 = reportParam.getDataType();
                            final String valuesForSql2 = reportParam.getValuesForSql();
                            Intrinsics.checkNotNullExpressionValue(valuesForSql2, "getValuesForSql(...)");
                            orgSql = StringsKt.replace(orgSql, str2, reportUtil.convertToType(dataType2, valuesForSql2), false);
                        } else {
                            final String name3 = reportParam.getName();
                            Intrinsics.checkNotNullExpressionValue(name3, "getName(...)");
                            final ReportConstParam findConstantParam = findConstantParam(name3);
                            final ReportConstParam reportConstParam = ReportConstParam.AktifFirmaNo;
                            if (findConstantParam == reportConstParam) {
                                Intrinsics.checkNotNull(orgSql);
                                final StringBuilder sb = new StringBuilder();
                                sb.append('#');
                                sb.append(reportConstParam);
                                sb.append('#');
                                c2 = '#';
                                if (0 < StringsKt.indexOf(orgSql, sb.toString(), 0, false)) {
                                    Intrinsics.checkNotNull(orgSql);
                                    orgSql = reportUtil.replaceQueryByParameter(orgSql, "AktifFirmaNo", ErpCreator.getInstance().getmBaseErp().getUser().getFirmNr());
                                }
                            } else {
                                c2 = '#';
                            }
                            final String name4 = reportParam.getName();
                            Intrinsics.checkNotNullExpressionValue(name4, "getName(...)");
                            final ReportConstParam findConstantParam2 = findConstantParam(name4);
                            final ReportConstParam reportConstParam2 = ReportConstParam.AktifDonemNo;
                            if (findConstantParam2 == reportConstParam2) {
                                Intrinsics.checkNotNull(orgSql);
                                final StringBuilder sb2 = new StringBuilder();
                                sb2.append(c2);
                                sb2.append(reportConstParam2);
                                sb2.append(c2);
                                if (0 < StringsKt.indexOf(orgSql, sb2.toString(), 0, false)) {
                                    Intrinsics.checkNotNull(orgSql);
                                    orgSql = reportUtil.replaceQueryByParameter(orgSql, "AktifDonemNo", ErpCreator.getInstance().getmBaseErp().getUser().getPeridodNr());
                                }
                            }
                            if (!Intrinsics.areEqual(reportParam.getValuesForSql(), "")) {
                                Intrinsics.checkNotNull(orgSql);
                                final String str3 = ':' + reportParam.getName();
                                final String dataType3 = reportParam.getDataType();
                                final String valuesForSql3 = reportParam.getValuesForSql();
                                Intrinsics.checkNotNullExpressionValue(valuesForSql3, "getValuesForSql(...)");
                                orgSql = StringsKt.replace(orgSql, str3, reportUtil.convertToType(dataType3, valuesForSql3), false);
                            }
                        }
                    }
                }
            }
            report.setSql(orgSql);
        }
    }
    public static boolean checkOnlyStaticParams(final List<? extends ReportParam> list) {
        boolean z = true;
        if (null == list) {
            return z;
        }
        for (final ReportParam reportParam : list) {
            final ReportUtil reportUtil = ReportUtil.INSTANCE;
            final String name = reportParam.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            if (null == findConstantParam(name)) {
                z = false;
                break;
            }
        }
        return z;
    }

    private String replaceQueryByParameter(final String str, final String str2, final String str3) {
        final int indexOfdefault = StringsKt.indexOf(str, '#' + str2 + '#', 0, false);
        final String substring = str.substring(indexOfdefault + (-3), indexOfdefault);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        final int parseInt = Integer.parseInt(String.valueOf(substring.charAt(0)));
        final char charAt = substring.charAt(2);
        Intrinsics.checkNotNull(str3);
        final String parameterStringReplacer = StringUtils.parameterStringReplacer(parseInt, charAt, str3);
        return StringsKt.replace(StringsKt.replace(str, ':' + substring + '#' + str2 + '#', parameterStringReplacer, false), ':' + str2, parameterStringReplacer, false);
    }

    public static Class<?> getReportFromType(final Report report, final ReportConstParamProp paramProp, final int i2) {
        Intrinsics.checkNotNullParameter(report, "report");
        Intrinsics.checkNotNullParameter(paramProp, "paramProp");
        ReportUtil.replaceParameters(report, paramProp, i2);
        return ReportUtil.INSTANCE.getReportFromType(report.getDisplayType());
    }

    private Class<?> getReportFromType(final ReportDisplayType reportDisplayType) {
        final int i2 = null == reportDisplayType ? -1 : WhenMappings.EnumSwitchMapping1[reportDisplayType.ordinal()];
        if (1 == i2) {
            return UserReportsFormActivity.class;
        }
        if (2 == i2) {
            return UserReportsGridActivity.class;
        }
        if (3 != i2) {
            return null;
        }
        return UserReportsChartActivity.class;
    }

    public static String formatReportColumnValue(final ReportColumn column, final String value) {
        Intrinsics.checkNotNullParameter(column, "column");
        Intrinsics.checkNotNullParameter(value, "value");
        try {
            if (null == column.getColumnDataType()) {
                return value;
            }
            try {
                final ReportColumnDataType columnDataType = column.getColumnDataType();
                final int i2 = null == columnDataType ? -1 : WhenMappings.EnumSwitchMapping2[columnDataType.ordinal()];
                if (1 == i2) {
                    final String convertReportDateToSimpleDate = DateAndTimeUtils.convertReportDateToSimpleDate(value);
                    Intrinsics.checkNotNullExpressionValue(convertReportDateToSimpleDate, "convertReportDateToSimpleDate(...)");
                    return convertReportDateToSimpleDate;
                }
                if (2 == i2) {
                    return StringUtils.formatDouble(value);
                }
                if (3 != i2 && 4 != i2) {
                    throw new NoWhenBranchMatchedException();
                }
                return value;
            } catch (final Exception e2) {
                e2.printStackTrace();
                return "";
            }
        } catch (final Throwable unused) {
        }
        return value;
    }
    public static int getGridReportColumnAlignment(final ReportColumn column) {
        Intrinsics.checkNotNullParameter(column, "column");
        if (null == column.getColumnDataType()) {
            return 3;
        }
        final ReportColumnDataType columnDataType = column.getColumnDataType();
        final int i2 = null == columnDataType ? -1 : WhenMappings.EnumSwitchMapping2[columnDataType.ordinal()];
        if (1 != i2) {
            return (2 == i2 || (3 != i2 && 4 == i2)) ? 5 : 3;
        }
        return 3;
    }
}
