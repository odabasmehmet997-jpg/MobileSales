package com.proje.mobilesales.features.reports.view.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.interfaces.AsyncReportResponse;
import com.proje.mobilesales.core.tigerwcf.REPORTXML;
import com.proje.mobilesales.core.tigerwcf.SelectResult;
import com.proje.mobilesales.core.tigerwcf.ServicesClientForTiger;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.reports.model.enums.ReportSortType;
import com.proje.mobilesales.features.reports.repository.ReportWcfQueriesRepository;
import com.proje.mobilesales.features.reports.viewmodel.ReportWcfQueriesViewModel;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

public class BaseReportWcfActivity extends BaseErpActivity implements View.OnClickListener, AsyncReportResponse {
    public static final Companion Companion = new Companion(null);
    public static final int DATE_DIALOG_ID1 = 998;
    public static final int DATE_DIALOG_ID2 = 999;
    private int clRef;
    private ServicesClientForTiger clientForTiger;
    protected boolean dateIsFirst;
    private int day;
    public LinearLayout linearDate1;
    public LinearLayout linearDate2;
    private int month;
    private Calendar myCalendar;
    private ReportSortType reportSortType;
    public ReportWcfQueriesRepository reportWcfQueriesRepository;
    public ReportWcfQueriesViewModel reportWcfQueriesViewModel;
    private ServicesClientForTiger.ReportRetrieve retrieve;
    private SelectResult selectResult;
    public AppCompatTextView tvDate1;
    public AppCompatTextView tvDate2;
    private ProcessType type;
    private int year;
    protected String strDate1 = "";
    protected String strDate2 = "";
    private DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() { // from class: com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivityExternalSyntheticLambda0
        public  void BaseReportWcfActivityExternalSyntheticLambda0() {
        }
 
        public void onDateSet(final DatePicker datePicker, final int i2, final int i3, final int i4) {
            _init_lambda0(BaseReportWcfActivity.this, datePicker, i2, i3, i4);
        }
    };
    private final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() { // from class: com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivityExternalSyntheticLambda1
        public  void BaseReportWcfActivityExternalSyntheticLambda1() {
        }

        public void onDateSet(final DatePicker datePicker, final int i2, final int i3, final int i4) {
            datePickerListenerlambda4(BaseReportWcfActivity.this, datePicker, i2, i3, i4);
        }
    };
    private final DatePickerDialog.OnDateSetListener datePickerListener2 = new DatePickerDialog.OnDateSetListener() { // from class: com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivityExternalSyntheticLambda2
        public  void BaseReportWcfActivityExternalSyntheticLambda2() {
        }
        public void onDateSet(final DatePicker datePicker, final int i2, final int i3, final int i4) {
            datePickerListener2lambda5(BaseReportWcfActivity.this, datePicker, i2, i3, i4);
        }
    };

    private static void datePickerListener2lambda5(final BaseReportWcfActivity baseReportWcfActivity, final DatePicker datePicker, final int i2, final int i3, final int i4) {
    }

    protected void clearFields() {
    }

    public void onPreExecute(final ProcessType processType) {
    }

    public void processFinish(final REPORTXML reportxml, final ProcessType processType) {
    }

    public final ReportWcfQueriesRepository getReportWcfQueriesRepository() {
        final ReportWcfQueriesRepository reportWcfQueriesRepository = this.reportWcfQueriesRepository;
        if (null != reportWcfQueriesRepository) {
            return reportWcfQueriesRepository;
        }
        Intrinsics.throwUninitializedPropertyAccessException("reportWcfQueriesRepository");
        return null;
    }

    public final void setReportWcfQueriesRepository(final ReportWcfQueriesRepository reportWcfQueriesRepository) {
        Intrinsics.checkNotNullParameter(reportWcfQueriesRepository, "<set-?>");
        this.reportWcfQueriesRepository = reportWcfQueriesRepository;
    }

    public final ReportWcfQueriesViewModel getReportWcfQueriesViewModel() {
        final ReportWcfQueriesViewModel reportWcfQueriesViewModel = this.reportWcfQueriesViewModel;
        if (null != reportWcfQueriesViewModel) {
            return reportWcfQueriesViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("reportWcfQueriesViewModel");
        return null;
    }

    public final void setReportWcfQueriesViewModel(final ReportWcfQueriesViewModel reportWcfQueriesViewModel) {
        Intrinsics.checkNotNullParameter(reportWcfQueriesViewModel, "<set-?>");
        this.reportWcfQueriesViewModel = reportWcfQueriesViewModel;
    }

    public final Calendar getMyCalendar() {
        return myCalendar;
    }

    public final void setMyCalendar(final Calendar calendar) {
        myCalendar = calendar;
    }

    public final DatePickerDialog.OnDateSetListener getDate() {
        return date;
    }

    public final void setDate(final DatePickerDialog.OnDateSetListener onDateSetListener) {
        date = onDateSetListener;
    }

    public final int getClRef() {
        return clRef;
    }

    public final void setClRef(final int i2) {
        clRef = i2;
    }

    public final ProcessType getType() {
        return type;
    }

    public final void setType(final ProcessType processType) {
        type = processType;
    }

    public final SelectResult getSelectResult() {
        return selectResult;
    }

    public final void setSelectResult(final SelectResult selectResult) {
        this.selectResult = selectResult;
    }

    public final ServicesClientForTiger getClientForTiger() {
        return clientForTiger;
    }

    public final void setClientForTiger(final ServicesClientForTiger servicesClientForTiger) {
        clientForTiger = servicesClientForTiger;
    }

    public final ServicesClientForTiger.ReportRetrieve getRetrieve() {
        return retrieve;
    }

    public final void setRetrieve(final ServicesClientForTiger.ReportRetrieve reportRetrieve) {
        retrieve = reportRetrieve;
    }

    protected final int getYear() {
        return year;
    }

    protected final void setYear(final int i2) {
        year = i2;
    }

    protected final int getDay() {
        return day;
    }

    protected final void setDay(final int i2) {
        day = i2;
    }

    protected final int getMonth() {
        return month;
    }

    protected final void setMonth(final int i2) {
        month = i2;
    }

    protected final ReportSortType getReportSortType() {
        return reportSortType;
    }

    protected final void setReportSortType(final ReportSortType reportSortType) {
        this.reportSortType = reportSortType;
    }

    public static final void _init_lambda0(final BaseReportWcfActivity this0, final DatePicker datePicker, final int i2, final int i3, final int i4) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final Calendar calendar = this0.myCalendar;
        if (null != calendar) {
            calendar.set(1, i2);
        }
        final Calendar calendar2 = this0.myCalendar;
        if (null != calendar2) {
            calendar2.set(2, i3);
        }
        final Calendar calendar3 = this0.myCalendar;
        if (null != calendar3) {
            calendar3.set(5, i4);
        }
        this0.updateDateTxtView(this0.dateIsFirst);
    }
     public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
         this.setContentView(R.layout.activity_base_report_wcf);
         this.setReportWcfQueriesRepository(new ReportWcfQueriesRepository());
         this.setReportWcfQueriesViewModel(new ReportWcfQueriesViewModel(this.getReportWcfQueriesRepository()));
        tvDate1 = this.findViewById(R.id.tvDate1);
        tvDate2 = this.findViewById(R.id.tvDate2);
        linearDate1 = this.findViewById(R.id.linearDate1);
        final LinearLayout linearLayout = this.findViewById(R.id.linearDate2);
        linearDate2 = linearLayout;
        if (null != linearLayout) {
            linearLayout.setOnClickListener(this);
        }
        final LinearLayout linearLayout2 = linearDate1;
        if (null != linearLayout2) {
            linearLayout2.setOnClickListener(this);
        }
        myCalendar = Calendar.getInstance();
         this.initDateTxtView();
        clientForTiger = new ServicesClientForTiger(this);
         this.getExtras();
        final int i2 = customerRef;
        clRef = i2;
        if (0 != i2) {
            type = this.CustomerToType(customerOperation.getMenuType());
        }
        reportSortType = ReportSortType.PRODUCT_CODE;
    }

    public final void showDatePicker(final boolean z) {
        dateIsFirst = z;
        final DatePickerDialog.OnDateSetListener onDateSetListener = date;
        final Calendar calendar = myCalendar;
        final int i2 = null != calendar ? calendar.get(1) : -1;
        final Calendar calendar2 = myCalendar;
        final int i3 = null != calendar2 ? calendar2.get(2) : -1;
        final Calendar calendar3 = myCalendar;
        new DatePickerDialog(this, onDateSetListener, i2, i3, null != calendar3 ? calendar3.get(5) : -1).show();
    }

    public final boolean validateDates() {
        final AppCompatTextView appCompatTextView = tvDate1;
        final String valueOf = String.valueOf(null != appCompatTextView ? appCompatTextView.getText() : null);
        final AppCompatTextView appCompatTextView2 = tvDate2;
        return DateAndTimeUtils.compareDates(valueOf, String.valueOf(null != appCompatTextView2 ? appCompatTextView2.getText() : null), "dd/MM/yyyy");
    }

    private final void updateDateTxtView(final boolean z) {
        final Date time;
        final Date time2;
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String str = null;
        if (z) {
            final AppCompatTextView appCompatTextView = tvDate1;
            if (null == appCompatTextView) {
                return;
            }
            final Calendar calendar = myCalendar;
            if (null != calendar && null != (time2 = calendar.getTime())) {
                str = simpleDateFormat.format(time2);
            }
            appCompatTextView.setText(str);
            return;
        }
        final AppCompatTextView appCompatTextView2 = tvDate2;
        if (null == appCompatTextView2) {
            return;
        }
        final Calendar calendar2 = myCalendar;
        if (null != calendar2 && null != (time = calendar2.getTime())) {
            str = simpleDateFormat.format(time);
        }
        appCompatTextView2.setText(str);
    }

    public void initDateTxtView() {
        final Date time;
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        final AppCompatTextView appCompatTextView = tvDate2;
        if (null != appCompatTextView) {
            final Calendar calendar = myCalendar;
            appCompatTextView.setText((null == calendar || null == (time = calendar.getTime())) ? null : simpleDateFormat.format(time));
        }
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.set(5, calendar2.get(5) - 1);
        final AppCompatTextView appCompatTextView2 = tvDate1;
        if (null == appCompatTextView2) {
            return;
        }
        appCompatTextView2.setText(simpleDateFormat.format(calendar2.getTime()));
    }

    protected Dialog onCreateDialog(final int i2) {
        if (998 == i2) {
            return new DatePickerDialog(this, datePickerListener, year, month, day);
        }
        if (999 != i2) {
            return null;
        }
        return new DatePickerDialog(this, datePickerListener2, year, month, day);
    }

    public static final void datePickerListenerlambda4(final BaseReportWcfActivity this0, final DatePicker datePicker, final int i2, final int i3, final int i4) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.year = i2;
        this0.month = i3;
        this0.day = i4;
        final String fillFormat = StringUtils.fillFormat(4, i2);
        final String fillFormat2 = StringUtils.fillFormat(2, i3 + 1);
        final String fillFormat3 = StringUtils.fillFormat(2, this0.day);
        if (this0.dateIsFirst) {
            final AppCompatTextView appCompatTextView = this0.tvDate1;
            Intrinsics.checkNotNull(appCompatTextView);
            final String format = String.format("%s.%s.%s", Arrays.copyOf(new Object[]{fillFormat3, fillFormat2, fillFormat}, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            appCompatTextView.setText(format);
            return;
        }
        final AppCompatTextView appCompatTextView2 = this0.tvDate2;
        Intrinsics.checkNotNull(appCompatTextView2);
        final String format2 = String.format("%s.%s.%s", Arrays.copyOf(new Object[]{fillFormat3, fillFormat2, fillFormat}, 3));
        Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
        appCompatTextView2.setText(format2);
    }

    public static final void datePickerListener2lambda5(final BaseReportWcfActivity this0, final DatePicker datePicker, final int i2, final int i3, final int i4) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.year = i2;
        this0.month = i3;
        this0.day = i4;
        final String fillFormat = StringUtils.fillFormat(4, i2);
        final String fillFormat2 = StringUtils.fillFormat(2, i3 + 1);
        final String fillFormat3 = StringUtils.fillFormat(2, this0.day);
        if (this0.dateIsFirst) {
            final AppCompatTextView appCompatTextView = this0.tvDate1;
            Intrinsics.checkNotNull(appCompatTextView);
            final String format = String.format("%s.%s.%s", Arrays.copyOf(new Object[]{fillFormat3, fillFormat2, fillFormat}, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            appCompatTextView.setText(format);
            return;
        }
        final AppCompatTextView appCompatTextView2 = this0.tvDate2;
        Intrinsics.checkNotNull(appCompatTextView2);
        final String format2 = String.format("%s.%s.%s", Arrays.copyOf(new Object[]{fillFormat3, fillFormat2, fillFormat}, 3));
        Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
        appCompatTextView2.setText(format2);
    }

    protected final void setDate() {
        final String obj;
        List emptyList;
        if (dateIsFirst) {
            final AppCompatTextView appCompatTextView = tvDate1;
            Intrinsics.checkNotNull(appCompatTextView);
            obj = appCompatTextView.getText().toString();
        } else {
            final AppCompatTextView appCompatTextView2 = tvDate2;
            Intrinsics.checkNotNull(appCompatTextView2);
            obj = appCompatTextView2.getText().toString();
        }
        final List<String> split = new Regex("\\.").split(obj, 0);
        if (!split.isEmpty()) {
            final ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (0 != listIterator.previous().length()) {
                    emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList = CollectionsKt.emptyList();
        final String[] strArr = (String[]) emptyList.toArray(new String[0]);
        day = Integer.parseInt(strArr[0]);
        month = Integer.parseInt(strArr[1]) - 1;
        year = Integer.parseInt(strArr[2]);
    }

    public void setCurrentDateOnView() {
        final Calendar calendar = Calendar.getInstance();
        final int i2 = calendar.get(1);
        final int i3 = calendar.get(2) + 1;
        final int i4 = calendar.get(5);
        final String fillFormat = StringUtils.fillFormat(4, i2);
        final String fillFormat2 = StringUtils.fillFormat(2, i3);
        final String fillFormat3 = StringUtils.fillFormat(2, i4);
        if (Intrinsics.areEqual(strDate2, "")) {
            final AppCompatTextView appCompatTextView = tvDate2;
            Intrinsics.checkNotNull(appCompatTextView);
            final String format = String.format("%s.%s.%s", Arrays.copyOf(new Object[]{fillFormat3, fillFormat2, fillFormat}, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            appCompatTextView.setText(format);
        } else {
            final AppCompatTextView appCompatTextView2 = tvDate2;
            Intrinsics.checkNotNull(appCompatTextView2);
            appCompatTextView2.setText(strDate2);
        }
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(calendar.getTime());
        if (1 == i4) {
            calendar2.add(2, -1);
            calendar2.set(5, calendar2.getActualMaximum(5));
        } else {
            calendar2.add(5, -1);
        }
        final int i5 = calendar2.get(5);
        final int i6 = calendar2.get(2) + 1;
        final int i7 = calendar2.get(1);
        final String fillFormat4 = StringUtils.fillFormat(2, i5);
        final String fillFormat5 = StringUtils.fillFormat(2, i6);
        final String fillFormat6 = StringUtils.fillFormat(4, i7);
        if (Intrinsics.areEqual(strDate1, "")) {
            final AppCompatTextView appCompatTextView3 = tvDate1;
            Intrinsics.checkNotNull(appCompatTextView3);
            final String format2 = String.format("%s.%s.%s", Arrays.copyOf(new Object[]{fillFormat4, fillFormat5, fillFormat6}, 3));
            Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
            appCompatTextView3.setText(format2);
            return;
        }
        final AppCompatTextView appCompatTextView4 = tvDate1;
        Intrinsics.checkNotNull(appCompatTextView4);
        appCompatTextView4.setText(strDate1);
    }

    public void onClick(final View view) {
        final Integer valueOf = null != view ? Integer.valueOf(view.getId()) : null;
        if (null != valueOf && R.id.linearDate1 == valueOf.intValue()) {
            this.showDatePicker(true);
            return;
        }
        if (null != valueOf && R.id.linearDate2 == valueOf.intValue()) {
            this.showDatePicker(false);
            return;
        }
        if (null != valueOf && R.id.imgList == valueOf.intValue()) {
            this.clearFields();
            if (this.validateDates()) {
                return;
            }
            Toast.makeText(this, this.getString(R.string.exp_64_begin_date_bigger_than_end_date), Toast.LENGTH_LONG).show();
        }
    }

    protected final void toggleSort(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final ReportSortType reportSortType = this.reportSortType;
        final ReportSortType reportSortType2 = ReportSortType.PRODUCT_CODE;
        if (reportSortType == reportSortType2) {
            this.reportSortType = ReportSortType.PRODUCT_NAME;
            item.setTitle(R.string.menu_sort_product_code);
        } else if (ReportSortType.PRODUCT_NAME == reportSortType) {
            this.reportSortType = reportSortType2;
            item.setTitle(R.string.menu_sort_product_name);
        }
    }

    public static final class Companion {
        public  Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
