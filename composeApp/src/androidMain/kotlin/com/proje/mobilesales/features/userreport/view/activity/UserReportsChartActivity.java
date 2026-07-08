package com.proje.mobilesales.features.userreport.view.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.proje.mobilesales.R;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.ChartItem;
import com.proje.mobilesales.core.enums.PdfExportOption;
import com.proje.mobilesales.core.reportparser.Report;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.adapter.ChartDataAdapter;
import com.proje.mobilesales.features.model.BarChartItem;
import com.proje.mobilesales.features.model.GenericData;
import com.proje.mobilesales.features.model.LineChartItem;
import com.proje.mobilesales.features.model.PieChartItem;
import com.proje.mobilesales.features.reports.model.ReportArgumentValueModel;
import com.proje.mobilesales.features.reports.model.enums.ReportSerieViewType;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class UserReportsChartActivity extends UserReportsActivity {
    private ArrayList<ReportArgumentValueModel> chartList;
    private ArrayList<ChartItem> list;
    private ListView reportChartLV;
    private TextView reportChartTV;
    private List<? extends GenericData> resultSet;
    public static final class WhenMappings {
        public static int[] EnumSwitchMapping0 = new int[0];
        {
            int[] iArr = new int[ReportSerieViewType.values().length];
            try {
                iArr[ReportSerieViewType.Bar.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReportSerieViewType.Pie.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReportSerieViewType.Line.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ReportSerieViewType.Point.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            EnumSwitchMapping0 = iArr;
        }
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_user_reports_chart);
        setToolbar();
        setTitle(getIntent().getStringExtra("ReportName"));
        setListener(new UserReportActivityListener(this) {
            final UserReportsChartActivity this0 = null;

             public void dataReady() {
                this.this0.initialize();
            }

            public void dataError(String str) {
                Intrinsics.checkNotNullParameter(str, "errorMessage");
                Toast.makeText(this.this0, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setResources() {
        this.reportChartLV = findViewById(R.id.reportChartLV);
        this.reportChartTV = findViewById(R.id.reportChartTV);
        this.resultSet = getData();
        this.chartList = new ArrayList<>();
    }
    public void initialize() {
        setResources();
        setChartList();
        showListView();
    }
    private void setChartList() {
        String str;
        Report report = getReport();
        Intrinsics.checkNotNull(report);
        report.getReportLayout().getChart().getSeries();
        List<? extends GenericData> list = this.resultSet;
        Intrinsics.checkNotNull(list);
        if (!list.isEmpty()) {
            List<? extends GenericData> list2 = this.resultSet;
            Intrinsics.checkNotNull(list2);
            GenericData genericData = list2.get(0);
            Report report2 = getReport();
            Intrinsics.checkNotNull(report2);
            int size = report2.getReportLayout().getChart().getSeries().size();
            for (int i2 = 0; i2 < size; i2++) {
                ReportArgumentValueModel reportArgumentValueModel = new ReportArgumentValueModel();
                Report report3 = getReport();
                Intrinsics.checkNotNull(report3);
                reportArgumentValueModel.serieViewType = report3.getReportLayout().getChart().getSeries().get(i2).getSerieViewType();
                Report report4 = getReport();
                Intrinsics.checkNotNull(report4);
                if (report4.getReportLayout().getChart().getTitles() != null) {
                    Report report5 = getReport();
                    Intrinsics.checkNotNull(report5);
                    str = report5.getReportLayout().getChart().getTitles().get(i2);
                } else {
                    str = "";
                }
                reportArgumentValueModel.reportTitle = str;
                int size2 = genericData.getGenericDataPrimitives().size();
                boolean z = false;
                boolean z2 = false;
                for (int i3 = 0; i3 < size2; i3++) {
                    String name = genericData.getGenericDataPrimitives().get(i3).getName();
                    Report report6 = getReport();
                    Intrinsics.checkNotNull(report6);
                    String argumentDataMember = report6.getReportLayout().getChart().getSeries().get(i2).getArgumentDataMember();
                    Report report7 = getReport();
                    Intrinsics.checkNotNull(report7);
                    String valueDataMembersSerializable = report7.getReportLayout().getChart().getSeries().get(i2).getValueDataMembersSerializable();
                    if (StringsKt.equals(name, argumentDataMember, true)) {
                        reportArgumentValueModel.setmArgumentDataMember(name);
                        reportArgumentValueModel.setxColumnIndex(i3);
                        z = true;
                    } else if (StringsKt.equals(name, valueDataMembersSerializable, true)) {
                        reportArgumentValueModel.setmValueDataMember(name);
                        reportArgumentValueModel.setyColumnIndex(i3);
                        z2 = true;
                    }
                    if (!z || !z2) {
                    }
                }
                ArrayList<ReportArgumentValueModel> arrayList = this.chartList;
                Intrinsics.checkNotNull(arrayList);
                arrayList.add(reportArgumentValueModel);
            }
        }
    }
    private void showListView() {
        String str;
        this.list = new ArrayList<>();
        ArrayList<ReportArgumentValueModel> arrayList = this.chartList;
        Intrinsics.checkNotNull(arrayList);
        int size = arrayList.size();
        String str2 = "";
        for (int i2 = 0; i2 < size; i2++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            ArrayList<ReportArgumentValueModel> arrayList2 = this.chartList;
            Intrinsics.checkNotNull(arrayList2);
            sb.append(arrayList2.get(i2).reportTitle);
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2);
            ArrayList<ReportArgumentValueModel> arrayList3 = this.chartList;
            Intrinsics.checkNotNull(arrayList3);
            if (i2 != arrayList3.size() - 1) {
                str = System.getProperty("line.separator");
            } else {
                str = "";
            }
            sb3.append(str);
            str2 = sb3.toString();
            ArrayList<ReportArgumentValueModel> arrayList4 = this.chartList;
            Intrinsics.checkNotNull(arrayList4);
            ReportSerieViewType reportSerieViewType = arrayList4.get(i2).serieViewType;
            int i3 = reportSerieViewType == null ? -1 : WhenMappings.EnumSwitchMapping0[reportSerieViewType.ordinal()];
            if (i3 == 1) {
                ArrayList<ChartItem> arrayList5 = this.list;
                Intrinsics.checkNotNull(arrayList5);
                ArrayList<ReportArgumentValueModel> arrayList6 = this.chartList;
                Intrinsics.checkNotNull(arrayList6);
                ReportArgumentValueModel reportArgumentValueModel = arrayList6.get(i2);
                Intrinsics.checkNotNullExpressionValue(reportArgumentValueModel, "get(...)");
                arrayList5.add(new BarChartItem(getBarChartData(reportArgumentValueModel)));
            } else if (i3 == 2) {
                ArrayList<ChartItem> arrayList7 = this.list;
                Intrinsics.checkNotNull(arrayList7);
                ArrayList<ReportArgumentValueModel> arrayList8 = this.chartList;
                Intrinsics.checkNotNull(arrayList8);
                ReportArgumentValueModel reportArgumentValueModel2 = arrayList8.get(i2);
                Intrinsics.checkNotNullExpressionValue(reportArgumentValueModel2, "get(...)");
                arrayList7.add(new PieChartItem(getPieChartData(reportArgumentValueModel2)));
            } else if (i3 == 3) {
                ArrayList<ChartItem> arrayList9 = this.list;
                Intrinsics.checkNotNull(arrayList9);
                ArrayList<ReportArgumentValueModel> arrayList10 = this.chartList;
                Intrinsics.checkNotNull(arrayList10);
                ReportArgumentValueModel reportArgumentValueModel3 = arrayList10.get(i2);
                Intrinsics.checkNotNullExpressionValue(reportArgumentValueModel3, "get(...)");
                arrayList9.add(new LineChartItem(getLineChartData(reportArgumentValueModel3)));
            } else if (i3 != 4) {
                Toast.makeText(this, getString(R.string.exp_84_unsupported_report_type), Toast.LENGTH_SHORT).show();
            } else {
                ArrayList<ChartItem> arrayList11 = this.list;
                Intrinsics.checkNotNull(arrayList11);
                ArrayList<ReportArgumentValueModel> arrayList12 = this.chartList;
                Intrinsics.checkNotNull(arrayList12);
                ReportArgumentValueModel reportArgumentValueModel4 = arrayList12.get(i2);
                Intrinsics.checkNotNullExpressionValue(reportArgumentValueModel4, "get(...)");
                arrayList11.add(new LineChartItem(getLineChartData(reportArgumentValueModel4, true)));
            }
        }
        TextView textView = this.reportChartTV;
        Intrinsics.checkNotNull(textView);
        textView.setText(str2);
        ChartDataAdapter chartDataAdapter = new ChartDataAdapter(getApplicationContext(), this.list);
        ListView listView = this.reportChartLV;
        Intrinsics.checkNotNull(listView);
        listView.setAdapter(chartDataAdapter);
    }
    private BarData getBarChartData(ReportArgumentValueModel reportArgumentValueModel) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        List<? extends GenericData> list = this.resultSet;
        Intrinsics.checkNotNull(list);
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            List<? extends GenericData> list2 = this.resultSet;
            Intrinsics.checkNotNull(list2);
            GenericData genericData = list2.get(i2);
            String obj = genericData.getGenericDataPrimitives().get(reportArgumentValueModel.getxColumnIndex()).getValue().toString();
            arrayList2.add(i2, new BarEntry(Float.parseFloat(genericData.getGenericDataPrimitives().get(reportArgumentValueModel.getyColumnIndex()).getValue().toString()), i2, obj));
            arrayList.add(obj);
        }
        BarDataSet barDataSet = new BarDataSet(arrayList2, "");
        barDataSet.setBarBorderWidth(1.0f);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData = new BarData(arrayList, barDataSet);
        barData.setValueTextSize(11.0f);
        return barData;
    }
    private PieData getPieChartData(ReportArgumentValueModel reportArgumentValueModel) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        List<? extends GenericData> list = this.resultSet;
        Intrinsics.checkNotNull(list);
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            List<? extends GenericData> list2 = this.resultSet;
            Intrinsics.checkNotNull(list2);
            GenericData genericData = list2.get(i2);
            String obj = genericData.getGenericDataPrimitives().get(reportArgumentValueModel.getxColumnIndex()).getValue().toString();
            arrayList.add(new Entry(Float.parseFloat(genericData.getGenericDataPrimitives().get(reportArgumentValueModel.getyColumnIndex()).getValue().toString()), i2, obj));
            arrayList2.add(obj);
        }
        PieDataSet pieDataSet = new PieDataSet(arrayList, "");
        pieDataSet.setSelectionShift(5.0f);
        ArrayList arrayList3 = new ArrayList();
        int[] iArr = ColorTemplate.COLORFUL_COLORS;
        Intrinsics.checkNotNullExpressionValue(iArr, "COLORFUL_COLORS");
        for (int i3 : iArr) {
            arrayList3.add(Integer.valueOf(i3));
        }
        int[] iArr2 = ColorTemplate.JOYFUL_COLORS;
        Intrinsics.checkNotNullExpressionValue(iArr2, "JOYFUL_COLORS");
        for (int i4 : iArr2) {
            arrayList3.add(Integer.valueOf(i4));
        }
        pieDataSet.setColors(arrayList3);
        pieDataSet.setValueLinePart1OffsetPercentage(80.0f);
        pieDataSet.setValueLinePart1Length(0.2f);
        pieDataSet.setValueLinePart2Length(0.4f);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        PieData pieData = new PieData(arrayList2, pieDataSet);
        pieData.setValueFormatter(new DefaultValueFormatter(2));
        pieData.setValueTextSize(11.0f);
        pieData.setValueTextColor(ViewCompat.MEASURED_STATE_MASK);
        return pieData;
    }
    private LineData getLineChartData(ReportArgumentValueModel reportArgumentValueModel) {
        return getLineChartData(reportArgumentValueModel, false);
    }
    private LineData getLineChartData(ReportArgumentValueModel reportArgumentValueModel, boolean z) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        List<? extends GenericData> list = this.resultSet;
        Intrinsics.checkNotNull(list);
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            List<? extends GenericData> list2 = this.resultSet;
            Intrinsics.checkNotNull(list2);
            GenericData genericData = list2.get(i2);
            String obj = genericData.getGenericDataPrimitives().get(reportArgumentValueModel.getxColumnIndex()).getValue().toString();
            arrayList.add(new Entry(Float.parseFloat(genericData.getGenericDataPrimitives().get(reportArgumentValueModel.getyColumnIndex()).getValue().toString()), i2, obj));
            arrayList2.add(obj);
        }
        LineDataSet lineDataSet = new LineDataSet(arrayList, "");
        lineDataSet.setLineWidth(2.5f);
        lineDataSet.setHighLightColor(ViewCompat.MEASURED_STATE_MASK);
        lineDataSet.setDrawValues(true);
        if (z) {
            lineDataSet.setCircleColors(ColorTemplate.COLORFUL_COLORS);
            lineDataSet.setColor(0);
        }
        LineData lineData = new LineData(arrayList2, lineDataSet);
        lineData.setValueFormatter(new DefaultValueFormatter(2));
        lineData.setValueTextSize(11.0f);
        lineData.setValueTextColor(ViewCompat.MEASURED_STATE_MASK);
        return lineData;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        ArrayList<ChartItem> arrayList;
        Intrinsics.checkNotNullParameter(menuItem, "item");
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_pdf) {
            ArrayList<GenericData> data = getData();
            if (!(data == null || data.isEmpty() || (arrayList = this.list) == null)) {
                Intrinsics.checkNotNull(arrayList);
                if (!arrayList.isEmpty()) {
                    BottomSheetDialog bottomSheetDialog = getBottomSheetDialog();
                    if (bottomSheetDialog != null) {
                        bottomSheetDialog.show();
                    }
                    return true;
                }
            }
            Toast.makeText(this, ContextUtils.getStringResource(R.string.str_empty_list_text), Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId != R.id.menu_send_mail) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            ArrayList<ChartItem> arrayList2 = this.list;
            if (arrayList2 != null) {
                Intrinsics.checkNotNull(arrayList2);
                if (!arrayList2.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    Context context = ContextUtils.getmContext();
                    Intrinsics.checkNotNull(context);
                    sb.append(context.getCacheDir().toString());
                    sb.append("/emailAttacments/");
                    String sb2 = sb.toString();
                    ArrayList arrayList3 = new ArrayList();
                    ArrayList<ChartItem> arrayList4 = this.list;
                    Intrinsics.checkNotNull(arrayList4);
                    Iterator<ChartItem> it = arrayList4.iterator();
                    while (it.hasNext()) {
                        String uuid = UUID.randomUUID().toString();
                        Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
                        Locale locale = Locale.getDefault();
                        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                        String upperCase = uuid.toUpperCase(locale);
                        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                        if (it.next().saveChart(upperCase, sb2)) {
                            arrayList3.add(upperCase + ".png");
                        }
                    }
                    if (!arrayList3.isEmpty()) {
                        sendMail(arrayList3);
                    }
                    return true;
                }
            }
            Toast.makeText(this, ContextUtils.getStringResource(R.string.str_empty_list_text), Toast.LENGTH_SHORT).show();
            return true;
        }
    }
    public void exportPdf(PdfExportOption pdfExportOption) {
        ArrayList<ChartItem> arrayList = this.list;
        if (arrayList != null) {
            Intrinsics.checkNotNull(arrayList);
            if (!arrayList.isEmpty()) {
                ArrayList arrayList2 = new ArrayList();
                ArrayList<ChartItem> arrayList3 = this.list;
                Intrinsics.checkNotNull(arrayList3);
                Iterator<ChartItem> it = arrayList3.iterator();
                while (it.hasNext()) {
                    Bitmap chartBitmap = it.next().getChartBitmap();
                    Intrinsics.checkNotNullExpressionValue(chartBitmap, "getChartBitmap(...)");
                    String convertBitmapToBase64 = convertBitmapToBase64(chartBitmap);
                    if (!TextUtils.isEmpty(convertBitmapToBase64)) {
                        arrayList2.add(convertBitmapToBase64);
                    }
                }
                if (arrayList2.isEmpty()) {
                    Toast.makeText(this, ContextUtils.getStringResource(R.string.str_empty_list_text), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    exportPdf(arrayList2, pdfExportOption);
                    return;
                }
            }
        }
        Toast.makeText(this, ContextUtils.getStringResource(R.string.str_empty_list_text), Toast.LENGTH_SHORT).show();
    }
    private String convertBitmapToBase64(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (Exception e2) {
            Log.e("UserReportsChart", "convertBitmapToBase64", e2);
            return null;
        }
    }
}
