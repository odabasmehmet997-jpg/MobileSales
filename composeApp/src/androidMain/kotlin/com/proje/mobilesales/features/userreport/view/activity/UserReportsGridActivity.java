package com.proje.mobilesales.features.userreport.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.OvershootInterpolator;
import android.widget.TableRow;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.PdfExportOption;
import com.proje.mobilesales.core.reportparser.Report;
import com.proje.mobilesales.core.reportparser.ReportColumn;
import com.proje.mobilesales.core.reportparser.ReportSummary;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.view.CustomHorizontalScrollView;
import com.proje.mobilesales.core.widget.SlideInLeftAnimator;
import com.proje.mobilesales.features.model.GenericData;
import com.proje.mobilesales.features.model.GenericDataPrimitive;
import com.proje.mobilesales.features.reports.model.enums.ReportColumnDataType;
import com.proje.mobilesales.features.reports.util.ReportUtil;
import com.proje.mobilesales.features.userreport.adapter.UserReportsGridRecyclerAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

import static android.text.TextUtils.TruncateAt;

public final class UserReportsGridActivity extends UserReportsActivity {
    private UserReportsGridRecyclerAdapter adapter;
    private CustomHorizontalScrollView bodyHCW;
    private CustomHorizontalScrollView footerDataHCW;
    private CustomHorizontalScrollView footerHCW;
    private CustomHorizontalScrollView headerHCW;
    private RecyclerView recyclerView;
    private ArrayList<GenericData> resultSet;
    private TableRow userReportFooterData;
    private TableRow userReportFooterRow;
    private TableRow userReportHeaderRow;
    public class WhenMappings {
        public int[] EnumSwitchMapping0 = new int[0];
        {
            int[] iArr = new int[ReportColumnDataType.values().length];
            try {
                iArr[ReportColumnDataType.Numeric.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReportColumnDataType.Decimal.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReportColumnDataType.DateTime.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            EnumSwitchMapping0 = iArr;
        }
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_user_reports_grid);
        setToolbar();
        setTitle(getIntent().getStringExtra("ReportName"));
        setListener(new UserReportActivityListener(this) {
            final UserReportsGridActivity this0 = null;
            public void dataReady() {
                this.this0.initialize();
            }
            public void dataError(String str) {
                Intrinsics.checkNotNullParameter(str, "errorMessage");
                Toast.makeText(this.this0, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void initialize() {
        setHorizontalScrollView();
        setResources();
        prepareHeader();
        setRecyclerView();
        prepareFooter();
    }
    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.userReportRecyclerView);
        this.recyclerView = recyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 != null) {
            recyclerView2.setHasFixedSize(true);
        }
        RecyclerView recyclerView3 = this.recyclerView;
        if (recyclerView3 != null) {
            recyclerView3.setItemAnimator(new SlideInLeftAnimator(new OvershootInterpolator(2.0f)));
        }
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, 1);
        RecyclerView recyclerView4 = this.recyclerView;
        if (recyclerView4 != null) {
            recyclerView4.addItemDecoration(dividerItemDecoration);
        }
        UserReportsGridRecyclerAdapter userReportsGridRecyclerAdapter = new UserReportsGridRecyclerAdapter();
        this.adapter = userReportsGridRecyclerAdapter;
        Intrinsics.checkNotNull(userReportsGridRecyclerAdapter);
        userReportsGridRecyclerAdapter.setData(this.resultSet, getReport());
        RecyclerView recyclerView5 = this.recyclerView;
        if (recyclerView5 != null) {
            recyclerView5.setAdapter(this.adapter);
        }
    }
    private void setResources() {
        this.userReportHeaderRow = findViewById(R.id.userReportHeaderRow);
        this.userReportFooterRow = findViewById(R.id.userReportFooterRow);
        this.userReportFooterData = findViewById(R.id.userReportFooterDataRow);
        this.resultSet = getData();
    }

    private void setHorizontalScrollView() {
        this.headerHCW = findViewById(R.id.reportHeaderHCW);
        this.bodyHCW = findViewById(R.id.reportBodyHCW);
        this.footerHCW = findViewById(R.id.reportFooterHCW);
        this.footerDataHCW = findViewById(R.id.reportFooterDataHCW);
        CustomHorizontalScrollView customHorizontalScrollView = this.headerHCW;
        if (customHorizontalScrollView != null) {
            customHorizontalScrollView.setOnScrollChangedListener(new CustomHorizontalScrollView.OnScrollChangedListener() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda0
                @Override // com.proje.mobilesales.core.view.CustomHorizontalScrollView.OnScrollChangedListener
                public void onScrollChanged(int i2, int i3, int i4, int i5) {
                    UserReportsGridActivity.setHorizontalScrollViewlambda0(UserReportsGridActivity.this, i2, i3, i4, i5);
                }
            });
        }
        CustomHorizontalScrollView customHorizontalScrollView2 = this.bodyHCW;
        if (customHorizontalScrollView2 != null) {
            customHorizontalScrollView2.setOnScrollChangedListener(new CustomHorizontalScrollView.OnScrollChangedListener() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda1
                @Override // com.proje.mobilesales.core.view.CustomHorizontalScrollView.OnScrollChangedListener
                public void onScrollChanged(int i2, int i3, int i4, int i5) {
                    UserReportsGridActivity.setHorizontalScrollViewlambda1(UserReportsGridActivity.this, i2, i3, i4, i5);
                }
            });
        }
        CustomHorizontalScrollView customHorizontalScrollView3 = this.footerHCW;
        if (customHorizontalScrollView3 != null) {
            customHorizontalScrollView3.setOnScrollChangedListener(new CustomHorizontalScrollView.OnScrollChangedListener() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda2
                @Override // com.proje.mobilesales.core.view.CustomHorizontalScrollView.OnScrollChangedListener
                public void onScrollChanged(int i2, int i3, int i4, int i5) {
                    UserReportsGridActivity.setHorizontalScrollViewlambda2(UserReportsGridActivity.this, i2, i3, i4, i5);
                }
            });
        }
        CustomHorizontalScrollView customHorizontalScrollView4 = this.footerDataHCW;
        if (customHorizontalScrollView4 != null) {
            customHorizontalScrollView4.setOnScrollChangedListener(new CustomHorizontalScrollView.OnScrollChangedListener() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda3
                @Override // com.proje.mobilesales.core.view.CustomHorizontalScrollView.OnScrollChangedListener
                public void onScrollChanged(int i2, int i3, int i4, int i5) {
                    UserReportsGridActivity.setHorizontalScrollViewlambda3(UserReportsGridActivity.this, i2, i3, i4, i5);
                }
            });
        }
    }

    
    public static void setHorizontalScrollViewlambda0(UserReportsGridActivity userReportsGridActivity, int i2, int i3, int i4, int i5) {
        Intrinsics.checkNotNullParameter(userReportsGridActivity, "this0");
        CustomHorizontalScrollView customHorizontalScrollView = userReportsGridActivity.bodyHCW;
        if (customHorizontalScrollView != null) {
            customHorizontalScrollView.scrollTo(i2, 0);
        }
        CustomHorizontalScrollView customHorizontalScrollView2 = userReportsGridActivity.footerHCW;
        if (customHorizontalScrollView2 != null) {
            customHorizontalScrollView2.scrollTo(i2, 0);
        }
        CustomHorizontalScrollView customHorizontalScrollView3 = userReportsGridActivity.footerDataHCW;
        if (customHorizontalScrollView3 != null) {
            customHorizontalScrollView3.scrollTo(i2, 0);
        }
    }

    
    public static void setHorizontalScrollViewlambda1(UserReportsGridActivity userReportsGridActivity, int i2, int i3, int i4, int i5) {
        Intrinsics.checkNotNullParameter(userReportsGridActivity, "this0");
        CustomHorizontalScrollView customHorizontalScrollView = userReportsGridActivity.headerHCW;
        if (customHorizontalScrollView != null) {
            customHorizontalScrollView.scrollTo(i2, 0);
        }
        CustomHorizontalScrollView customHorizontalScrollView2 = userReportsGridActivity.footerHCW;
        if (customHorizontalScrollView2 != null) {
            customHorizontalScrollView2.scrollTo(i2, 0);
        }
        CustomHorizontalScrollView customHorizontalScrollView3 = userReportsGridActivity.footerDataHCW;
        if (customHorizontalScrollView3 != null) {
            customHorizontalScrollView3.scrollTo(i2, 0);
        }
    }

    
    public static void setHorizontalScrollViewlambda2(UserReportsGridActivity userReportsGridActivity, int i2, int i3, int i4, int i5) {
        Intrinsics.checkNotNullParameter(userReportsGridActivity, "this0");
        CustomHorizontalScrollView customHorizontalScrollView = userReportsGridActivity.bodyHCW;
        if (customHorizontalScrollView != null) {
            customHorizontalScrollView.scrollTo(i2, 0);
        }
        CustomHorizontalScrollView customHorizontalScrollView2 = userReportsGridActivity.headerHCW;
        if (customHorizontalScrollView2 != null) {
            customHorizontalScrollView2.scrollTo(i2, 0);
        }
        CustomHorizontalScrollView customHorizontalScrollView3 = userReportsGridActivity.footerDataHCW;
        if (customHorizontalScrollView3 != null) {
            customHorizontalScrollView3.scrollTo(i2, 0);
        }
    }

    
    public static void setHorizontalScrollViewlambda3(UserReportsGridActivity userReportsGridActivity, int i2, int i3, int i4, int i5) {
        Intrinsics.checkNotNullParameter(userReportsGridActivity, "this0");
        CustomHorizontalScrollView customHorizontalScrollView = userReportsGridActivity.bodyHCW;
        if (customHorizontalScrollView != null) {
            customHorizontalScrollView.scrollTo(i2, 0);
        }
        CustomHorizontalScrollView customHorizontalScrollView2 = userReportsGridActivity.headerHCW;
        if (customHorizontalScrollView2 != null) {
            customHorizontalScrollView2.scrollTo(i2, 0);
        }
        CustomHorizontalScrollView customHorizontalScrollView3 = userReportsGridActivity.footerHCW;
        if (customHorizontalScrollView3 != null) {
            customHorizontalScrollView3.scrollTo(i2, 0);
        }
    }

    private void prepareHeader() {
        TableRow tableRow = this.userReportHeaderRow;
        Intrinsics.checkNotNull(tableRow);
        if (tableRow.getChildCount() == 0) {
            Report report = getReport();
            Intrinsics.checkNotNull(report);
            int size = report.getReportLayout().getReportColumns().size();
            for (int i2 = 0; i2 < size; i2++) {
                Report report2 = getReport();
                Intrinsics.checkNotNull(report2);
                if (report2.getReportLayout().getReportColumns().get(i2).isVisible()) {
                    AppCompatTextView appCompatTextView = new AppCompatTextView(this);
                    setHeaderTextViewAttr(i2, appCompatTextView);
                    TableRow tableRow2 = this.userReportHeaderRow;
                    Intrinsics.checkNotNull(tableRow2);
                    tableRow2.addView(appCompatTextView);
                }
            }
        }
    }

    private void prepareFooter() {
        Report report = getReport();
        Intrinsics.checkNotNull(report);
        if (report.getSummaries() != null) {
            Report report2 = getReport();
            Intrinsics.checkNotNull(report2);
            if (!report2.getSummaries().isEmpty()) {
                Report report3 = getReport();
                Intrinsics.checkNotNull(report3);
                int size = report3.getReportLayout().getReportColumns().size();
                for (int i2 = 0; i2 < size; i2++) {
                    Report report4 = getReport();
                    Intrinsics.checkNotNull(report4);
                    ReportColumn reportColumn = report4.getReportLayout().getReportColumns().get(i2);
                    if (reportColumn.isVisible()) {
                        Report report5 = getReport();
                        Intrinsics.checkNotNull(report5);
                        List list = (List) report5.getSummaries().stream().filter(new Predicate() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda4
                            @Override // java.util.function.Predicate
                            public boolean test(Object obj) {
                                return UserReportsGridActivity.prepareFooterlambda4(Function1.this, obj);
                            }
                        }).collect(Collectors.toList());
                        boolean z = list == null || list.isEmpty();
                        AppCompatTextView appCompatTextView = new AppCompatTextView(this);
                        String str = "";
                        String text = !z ? ((ReportSummary) list.get(0)).getText() : str;
                        Intrinsics.checkNotNull(text);
                        setSummaryHeaderTextViewAttr(i2, appCompatTextView, text);
                        TableRow tableRow = this.userReportFooterRow;
                        Intrinsics.checkNotNull(tableRow);
                        tableRow.addView(appCompatTextView);
                        AppCompatTextView appCompatTextView2 = new AppCompatTextView(this);
                        if (!z) {
                            Intrinsics.checkNotNull(reportColumn);
                            Object obj = list.get(0);
                            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                            ((ReportSummary) list.get(0)).setCalculatedValue(calculateSummaryValueForColumn(reportColumn, (ReportSummary) obj));
                        }
                        if (!z) {
                            str = ((ReportSummary) list.get(0)).getCalculatedValue();
                        }
                        Intrinsics.checkNotNull(str);
                        setSummaryHeaderTextViewAttr(i2, appCompatTextView2, str);
                        Intrinsics.checkNotNull(reportColumn);
                        appCompatTextView2.setGravity(ReportUtil.getGridReportColumnAlignment(reportColumn));
                        TableRow tableRow2 = this.userReportFooterData;
                        Intrinsics.checkNotNull(tableRow2);
                        tableRow2.addView(appCompatTextView2);
                    }
                }
                ArrayList<GenericData> arrayList = this.resultSet;
                if (arrayList != null) {
                    Intrinsics.checkNotNull(arrayList);
                    if (!arrayList.isEmpty()) {
                        return;
                    }
                }
                TableRow tableRow3 = this.userReportFooterData;
                Intrinsics.checkNotNull(tableRow3);
                tableRow3.setVisibility(8);
                return;
            }
        }
        TableRow tableRow4 = this.userReportFooterRow;
        Intrinsics.checkNotNull(tableRow4);
        tableRow4.setVisibility(8);
        TableRow tableRow5 = this.userReportFooterData;
        Intrinsics.checkNotNull(tableRow5);
        tableRow5.setVisibility(8);
    }

    
    public static boolean prepareFooterlambda4(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    private String calculateSummaryValueForColumn(ReportColumn reportColumn, ReportSummary reportSummary) {
        String type;
        ArrayList<GenericData> arrayList = this.resultSet;
        if (arrayList == null) {
            return "";
        }
        Intrinsics.checkNotNull(arrayList);
        if (arrayList.isEmpty()) {
            return "";
        }
        if (StringsKt.equals(reportSummary.getType(), "Count", true)) {
            ArrayList<GenericData> arrayList2 = this.resultSet;
            Intrinsics.checkNotNull(arrayList2);
            return StringUtils.convertIntToString(arrayList2.size());
        }
        String enConvertedKey = reportColumn.getTranslation() != null ? reportColumn.getTranslation().getEnConvertedKey() : reportColumn.getFieldName();
        ArrayList<GenericData> arrayList3 = this.resultSet;
        Intrinsics.checkNotNull(arrayList3);
        List<? extends GenericDataPrimitive> list = (List) arrayList3.stream().flatMap(new Function() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda8
            @Override // java.util.function.Function
            public Object apply(Object obj) {
                return UserReportsGridActivity.calculateSummaryValueForColumnlambda5(Function1.this, obj);
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda9
            @Override // java.util.function.Predicate
            public boolean test(Object obj) {
                return UserReportsGridActivity.calculateSummaryValueForColumnlambda6(Function1.this, obj);
            }
        }).collect(Collectors.toList());
        if (list == null || list.isEmpty() || (type = reportSummary.getType()) == null) {
            return "";
        }
        switch (type.hashCode()) {
            case 77124:
                if (!type.equals("Max")) {
                    return "";
                }
                ReportColumnDataType columnDataType = reportColumn.getColumnDataType();
                Intrinsics.checkNotNullExpressionValue(columnDataType, "getColumnDataType(...)");
                Intrinsics.checkNotNull(list);
                return calculateMaxForSummary(columnDataType, list);
            case 77362:
                if (!type.equals("Min")) {
                    return "";
                }
                ReportColumnDataType columnDataType2 = reportColumn.getColumnDataType();
                Intrinsics.checkNotNullExpressionValue(columnDataType2, "getColumnDataType(...)");
                Intrinsics.checkNotNull(list);
                return calculateMinForSummary(columnDataType2, list);
            case 83499:
                if (!type.equals("Sum")) {
                    return "";
                }
                ReportColumnDataType columnDataType3 = reportColumn.getColumnDataType();
                Intrinsics.checkNotNullExpressionValue(columnDataType3, "getColumnDataType(...)");
                Intrinsics.checkNotNull(list);
                return calculateSumForSummary(columnDataType3, list);
            case 1033205245:
                if (!type.equals("Average")) {
                    return "";
                }
                ReportColumnDataType columnDataType4 = reportColumn.getColumnDataType();
                Intrinsics.checkNotNullExpressionValue(columnDataType4, "getColumnDataType(...)");
                Intrinsics.checkNotNull(list);
                return calculateAverageForSummary(columnDataType4, list);
            default:
                return "";
        }
    }

    
    public static Stream calculateSummaryValueForColumnlambda5(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return (Stream) function1.invoke(obj);
    }

    
    public static boolean calculateSummaryValueForColumnlambda6(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    private String calculateMaxForSummary(ReportColumnDataType reportColumnDataType, List<? extends GenericDataPrimitive> list) {
        try {
            Calendar instance = Calendar.getInstance();
            instance.add(1, 99);
            int i2 = WhenMappings.EnumSwitchMapping0[reportColumnDataType.ordinal()];
            if (i2 == 1) {
                return String.valueOf(list.stream().mapToLong(new ToLongFunction() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda14
                    @Override // java.util.function.ToLongFunction
                    public long applyAsLong(Object obj) {
                        return UserReportsGridActivity.calculateMaxForSummarylambda7(Function1.this, obj);
                    }
                }).max().orElse(0));
            }
            if (i2 == 2) {
                return StringUtils.formatDouble(list.stream().mapToDouble(new ToDoubleFunction() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda15
                    @Override // java.util.function.ToDoubleFunction
                    public double applyAsDouble(Object obj) {
                        return UserReportsGridActivity.calculateMaxForSummarylambda8(Function1.this, obj);
                    }
                }).max().orElse(0.0d));
            }
            if (i2 != 3) {
                return "";
            }
            Optional max = list.stream().map(new Function() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda16
                @Override // java.util.function.Function
                public Object apply(Object obj) {
                    return UserReportsGridActivity.calculateMaxForSummarylambda9(Function1.this, obj);
                }
            }).max(new Comparator() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda17
                @Override // java.util.Comparator
                public int compare(Object obj, Object obj2) {
                    return UserReportsGridActivity.calculateMaxForSummarylambda10(Function2.this, obj, obj2);
                }
            });
            if (Intrinsics.areEqual(max.orElse(instance.getTime()), instance)) {
                return "";
            }
            String format = new SimpleDateFormat("dd.MM.yyyy").format((Date) max.get());
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        } catch (Exception e2) {
            while (true) {
                Log.e("UserReportsGridActivity", "calculateMaxForSummary", e2);
                return "";
            }
        }
    }

    
    public static long calculateMaxForSummarylambda7(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return ((Number) function1.invoke(obj)).longValue();
    }

    
    public static double calculateMaxForSummarylambda8(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return ((Number) function1.invoke(obj)).doubleValue();
    }

    
    public static Date calculateMaxForSummarylambda9(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return (Date) function1.invoke(obj);
    }

    
    public static int calculateMaxForSummarylambda10(Function2 function2, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(function2, "tmp0");
        return ((Number) function2.invoke(obj, obj2)).intValue();
    }

    private String calculateMinForSummary(ReportColumnDataType reportColumnDataType, List<? extends GenericDataPrimitive> list) {
        try {
            Calendar instance = Calendar.getInstance();
            instance.add(1, 99);
            int i2 = WhenMappings.EnumSwitchMapping0[reportColumnDataType.ordinal()];
            if (i2 == 1) {
                return String.valueOf(list.stream().mapToLong(new ToLongFunction() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda10
                    @Override // java.util.function.ToLongFunction
                    public long applyAsLong(Object obj) {
                        return UserReportsGridActivity.calculateMinForSummarylambda11(Function1.this, obj);
                    }
                }).min().orElse(0));
            }
            if (i2 == 2) {
                return StringUtils.formatDouble(list.stream().mapToDouble(new ToDoubleFunction() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda11
                    @Override // java.util.function.ToDoubleFunction
                    public double applyAsDouble(Object obj) {
                        return UserReportsGridActivity.calculateMinForSummarylambda12(Function1.this, obj);
                    }
                }).min().orElse(0.0d));
            }
            if (i2 != 3) {
                return "";
            }
            Optional min = list.stream().map(new Function() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda12
                @Override // java.util.function.Function
                public Object apply(Object obj) {
                    return UserReportsGridActivity.calculateMinForSummarylambda13(Function1.this, obj);
                }
            }).min(new Comparator() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda13
                @Override // java.util.Comparator
                public int compare(Object obj, Object obj2) {
                    return UserReportsGridActivity.calculateMinForSummarylambda14(Function2.this, obj, obj2);
                }
            });
            if (Intrinsics.areEqual(min.orElse(instance.getTime()), instance)) {
                return "";
            }
            String format = new SimpleDateFormat("dd.MM.yyyy").format((Date) min.get());
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        } catch (Exception e2) {
            while (true) {
                Log.e("UserReportsGridActivity", "calculateMinForSummary", e2);
                return "";
            }
        }
    }

    
    public static long calculateMinForSummarylambda11(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return ((Number) function1.invoke(obj)).longValue();
    }

    
    public static double calculateMinForSummarylambda12(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return ((Number) function1.invoke(obj)).doubleValue();
    }

    
    public static Date calculateMinForSummarylambda13(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return (Date) function1.invoke(obj);
    }

    
    public static int calculateMinForSummarylambda14(Function2 function2, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(function2, "tmp0");
        return ((Number) function2.invoke(obj, obj2)).intValue();
    }

    private String calculateAverageForSummary(ReportColumnDataType reportColumnDataType, List<? extends GenericDataPrimitive> list) {
        try {
            int i2 = WhenMappings.EnumSwitchMapping0[reportColumnDataType.ordinal()];
            if (i2 == 1 || i2 == 2) {
                return StringUtils.formatDouble(list.stream().mapToDouble(new ToDoubleFunction() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda7
                    @Override // java.util.function.ToDoubleFunction
                    public double applyAsDouble(Object obj) {
                        return UserReportsGridActivity.calculateAverageForSummarylambda15(Function1.this, obj);
                    }
                }).average().orElse(0.0d));
            }
            return "";
        } catch (Exception e2) {
            Log.e("UserReportsGridActivity", "calculateAverageForSummary", e2);
            return "";
        }
    }

    
    public static double calculateAverageForSummarylambda15(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return ((Number) function1.invoke(obj)).doubleValue();
    }

    private String calculateSumForSummary(ReportColumnDataType reportColumnDataType, List<? extends GenericDataPrimitive> list) {
        try {
            int i2 = WhenMappings.EnumSwitchMapping0[reportColumnDataType.ordinal()];
            if (i2 == 1) {
                return String.valueOf(list.stream().mapToLong(new ToLongFunction() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda5
                    @Override // java.util.function.ToLongFunction
                    public long applyAsLong(Object obj) {
                        return UserReportsGridActivity.calculateSumForSummarylambda16(Function1.this, obj);
                    }
                }).sum());
            }
            if (i2 != 2) {
                return "";
            }
            return StringUtils.formatDouble(list.stream().mapToDouble(new ToDoubleFunction() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivityExternalSyntheticLambda6
                @Override // java.util.function.ToDoubleFunction
                public double applyAsDouble(Object obj) {
                    return UserReportsGridActivity.calculateSumForSummarylambda17(Function1.this, obj);
                }
            }).sum());
        } catch (Exception e2) {
            while (true) {
                Log.e("UserReportsGridActivity", "calculateSumForSummary", e2);
                return "";
            }
        }
    }

    
    public static long calculateSumForSummarylambda16(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return ((Number) function1.invoke(obj)).longValue();
    }

    
    public static double calculateSumForSummarylambda17(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return ((Number) function1.invoke(obj)).doubleValue();
    }

    private void setHeaderTextViewAttr(int i2, AppCompatTextView appCompatTextView) {
        appCompatTextView.setTypeface(null, 1);
        appCompatTextView.setText(getColumnText(i2));
        setTextViewAttr(i2, appCompatTextView);
    }

    private void setSummaryHeaderTextViewAttr(int i2, AppCompatTextView appCompatTextView, String str) {
        appCompatTextView.setTypeface(null, 1);
        appCompatTextView.setText(str);
        setTextViewAttr(i2, appCompatTextView);
    }

    private void setTextViewAttr(int i2, AppCompatTextView appCompatTextView) {
        Report report = getReport();
        Intrinsics.checkNotNull(report);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(report.getReportLayout().getReportColumns().get(i2).getCalculatedWidth(), -2);
        layoutParams.setMargins(10, 4, 10, 4);
        appCompatTextView.setLayoutParams(layoutParams);
        appCompatTextView.setTextColor(getResources().getColor(R.color.black));
        appCompatTextView.setSingleLine(true);
        appCompatTextView.setEllipsize(TruncateAt.END);
        appCompatTextView.setMaxLines(1);
        appCompatTextView.setPadding(6, 4, 6, 4);
    }

    @Override
    public void exportPdf(PdfExportOption pdfExportOption) {
        ArrayList<GenericData> data = getData();
        if (data == null || data.isEmpty()) {
            Toast.makeText(this, ContextUtils.getStringResource(R.string.str_empty_list_text), 0).show();
        } else {
            exportPdf(null, pdfExportOption);
        }
    }
}
