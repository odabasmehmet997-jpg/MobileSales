package com.proje.mobilesales.core.reportparser;

public final class ReportConstParam extends Enum<ReportConstParam> {
    private static final ReportConstParam[] VALUES;
    public static final ReportConstParam AktifDonemNo;
    public static final ReportConstParam AktifFirmaNo;
    public static final ReportConstParam CariHesapKodu;
    public static final ReportConstParam FirstDayOfThisMonth;
    public static final ReportConstParam FirstDayOfThisWeek;
    public static final ReportConstParam FirstDayOfThisYear;
    public static final ReportConstParam KullaniciSaticiKodu;
    public static final ReportConstParam LastDayOfThisMonth;
    public static final ReportConstParam LastDayOfThisWeek;
    public static final ReportConstParam LastDayOfThisYear;
    public static final ReportConstParam MalzemeKodu;
    public static final ReportConstParam Today;
    public static final ReportConstParam Tomorrow;
    public static final ReportConstParam Yesterday;
    private final ReportConstParamProp _prop;

    private static ReportConstParam[] values() {
        return new ReportConstParam[]{ReportConstParam.AktifFirmaNo, ReportConstParam.AktifDonemNo, ReportConstParam.KullaniciSaticiKodu, ReportConstParam.CariHesapKodu, ReportConstParam.MalzemeKodu, ReportConstParam.Today, ReportConstParam.Yesterday, ReportConstParam.Tomorrow, ReportConstParam.FirstDayOfThisWeek, ReportConstParam.LastDayOfThisWeek, ReportConstParam.FirstDayOfThisMonth, ReportConstParam.LastDayOfThisMonth, ReportConstParam.FirstDayOfThisYear, ReportConstParam.LastDayOfThisYear};
    }

    public static ReportConstParam valueOf(final String str) {
        return Enum.valueOf(ReportConstParam.class, str);
    }

    public static ReportConstParam[] values() {
        return ReportConstParam.VALUES.clone();
    }

    static {
        final ReportConstParamProp reportConstParamProp = ReportConstParamProp.Sabit;
        AktifFirmaNo = new ReportConstParam("AktifFirmaNo", 0, reportConstParamProp);
        AktifDonemNo = new ReportConstParam("AktifDonemNo", 1, reportConstParamProp);
        KullaniciSaticiKodu = new ReportConstParam("KullaniciSaticiKodu", 2, reportConstParamProp);
        CariHesapKodu = new ReportConstParam("CariHesapKodu", 3, ReportConstParamProp.Cari);
        MalzemeKodu = new ReportConstParam("MalzemeKodu", 4, ReportConstParamProp.Stok);
        Today = new ReportConstParam("Today", 5, reportConstParamProp);
        Yesterday = new ReportConstParam("Yesterday", 6, reportConstParamProp);
        Tomorrow = new ReportConstParam("Tomorrow", 7, reportConstParamProp);
        FirstDayOfThisWeek = new ReportConstParam("FirstDayOfThisWeek", 8, reportConstParamProp);
        LastDayOfThisWeek = new ReportConstParam("LastDayOfThisWeek", 9, reportConstParamProp);
        FirstDayOfThisMonth = new ReportConstParam("FirstDayOfThisMonth", 10, reportConstParamProp);
        LastDayOfThisMonth = new ReportConstParam("LastDayOfThisMonth", 11, reportConstParamProp);
        FirstDayOfThisYear = new ReportConstParam("FirstDayOfThisYear", 12, reportConstParamProp);
        LastDayOfThisYear = new ReportConstParam("LastDayOfThisYear", 13, reportConstParamProp);
        VALUES = ReportConstParam.values();
    }

    private ReportConstParam(final String str, final int i2, final ReportConstParamProp reportConstParamProp) {
        _prop = reportConstParamProp;
    }

    public ReportConstParamProp getProp() {
        return _prop;
    }
}
