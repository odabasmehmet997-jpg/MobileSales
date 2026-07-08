package com.proje.mobilesales.features.reports.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.reports.model.ReportListRowModel;
import com.proje.mobilesales.features.userreport.model.database.UserReports;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class AdapterListView extends BaseExpandableListAdapter {
    private Activity context;
    private final HashMap<String, ArrayList<ReportListRowModel>> expandableListDetail;
    private final List<String> expandableListTitle;
    private String[] menuList;
    public long getChildId(final int i2, final int i3) {
        return i3;
    }
    public long getGroupId(final int i2) {
        return i2;
    }
    public boolean hasStableIds() {
        return false;
    }
    public boolean isChildSelectable(final int i2, final int i3) {
        return true;
    }
    public AdapterListView(final Activity activity, final List<String> expandableListTitle, final HashMap<String, ArrayList<ReportListRowModel>> expandableListDetail, final int i2) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(expandableListTitle, "expandableListTitle");
        Intrinsics.checkNotNullParameter(expandableListDetail, "expandableListDetail");
        final String string = activity.getResources().getString(R.string.str_sales);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        final String string2 = activity.getResources().getString(R.string.str_daily_operations);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        final String string3 = activity.getResources().getString(R.string.str_my_tasks);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        final String string4 = activity.getResources().getString(R.string.str_my_messages);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        final String string5 = activity.getResources().getString(R.string.str_marketing_messages);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
        final String string6 = activity.getResources().getString(R.string.str_gps_location_info);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
        final String string7 = activity.getResources().getString(R.string.str_data_transfer);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
        final String string8 = activity.getResources().getString(R.string.str_about);
        Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
        final String string9 = activity.getResources().getString(R.string.str_other_process);
        Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
        final String string10 = activity.getResources().getString(R.string.str_reports);
        Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
        final String string11 = activity.getResources().getString(R.string.str_import_distribution_ordinance);
        Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
        final String string12 = activity.getResources().getString(R.string.str_order_approval);
        Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
        final String string13 = activity.getResources().getString(R.string.str_demand);
        Intrinsics.checkNotNullExpressionValue(string13, "getString(...)");
        menuList = new String[]{string, string2, "", string3, string4, string5, string6, string7, string8, "", string9, string10, string11, string12, string13};
        if (1 == i2) {
            final String stringResource = ContextUtils.getStringResource(R.string.str_daily_operations);
            Intrinsics.checkNotNullExpressionValue(stringResource, "getStringResource(...)");
            final String stringResource2 = ContextUtils.getStringResource(R.string.str_order);
            Intrinsics.checkNotNullExpressionValue(stringResource2, "getStringResource(...)");
            final String stringResource3 = ContextUtils.getStringResource(R.string.str_invoice);
            Intrinsics.checkNotNullExpressionValue(stringResource3, "getStringResource(...)");
            final String stringResource4 = ContextUtils.getStringResource(R.string.str_dispatch);
            Intrinsics.checkNotNullExpressionValue(stringResource4, "getStringResource(...)");
            final String stringResource5 = ContextUtils.getStringResource(R.string.str_cash_collection);
            Intrinsics.checkNotNullExpressionValue(stringResource5, "getStringResource(...)");
            final String stringResource6 = ContextUtils.getStringResource(R.string.str_safe_deposit);
            Intrinsics.checkNotNullExpressionValue(stringResource6, "getStringResource(...)");
            final String stringResource7 = ContextUtils.getStringResource(R.string.str_credit_card_slip);
            Intrinsics.checkNotNullExpressionValue(stringResource7, "getStringResource(...)");
            final String stringResource8 = ContextUtils.getStringResource(R.string.str_check_collection);
            Intrinsics.checkNotNullExpressionValue(stringResource8, "getStringResource(...)");
            final String stringResource9 = ContextUtils.getStringResource(R.string.str_payroll_note_collection);
            Intrinsics.checkNotNullExpressionValue(stringResource9, "getStringResource(...)");
            final String stringResource10 = ContextUtils.getStringResource(R.string.str_visit);
            Intrinsics.checkNotNullExpressionValue(stringResource10, "getStringResource(...)");
            final String stringResource11 = ContextUtils.getStringResource(R.string.str_send_info);
            Intrinsics.checkNotNullExpressionValue(stringResource11, "getStringResource(...)");
            final String stringResource12 = ContextUtils.getStringResource(R.string.str_exchange);
            Intrinsics.checkNotNullExpressionValue(stringResource12, "getStringResource(...)");
            final String stringResource13 = ContextUtils.getStringResource(R.string.str_reports);
            Intrinsics.checkNotNullExpressionValue(stringResource13, "getStringResource(...)");
            final String stringResource14 = ContextUtils.getStringResource(R.string.str_survey);
            Intrinsics.checkNotNullExpressionValue(stringResource14, "getStringResource(...)");
            final String stringResource15 = ContextUtils.getStringResource(R.string.str_penetration);
            Intrinsics.checkNotNullExpressionValue(stringResource15, "getStringResource(...)");
            final String stringResource16 = ContextUtils.getStringResource(R.string.str_demand);
            Intrinsics.checkNotNullExpressionValue(stringResource16, "getStringResource(...)");
            final String stringResource17 = ContextUtils.getStringResource(R.string.str_return_dispatch);
            Intrinsics.checkNotNullExpressionValue(stringResource17, "getStringResource(...)");
            final String stringResource18 = ContextUtils.getStringResource(R.string.str_return_invoice);
            Intrinsics.checkNotNullExpressionValue(stringResource18, "getStringResource(...)");
            menuList = new String[]{stringResource, "Mobile Sales", stringResource2, stringResource3, stringResource4, stringResource5, stringResource6, stringResource7, stringResource8, stringResource9, stringResource10, stringResource11, stringResource12, stringResource13, stringResource14, stringResource15, stringResource16, stringResource17, stringResource18};
        }
        if (2 == i2) {
            final String stringResource19 = ContextUtils.getStringResource(R.string.str_account_extract);
            Intrinsics.checkNotNullExpressionValue(stringResource19, "getStringResource(...)");
            final String stringResource20 = ContextUtils.getStringResource(R.string.str_debt_tracking);
            Intrinsics.checkNotNullExpressionValue(stringResource20, "getStringResource(...)");
            final String stringResource21 = ContextUtils.getStringResource(R.string.str_detailed_slip_list);
            Intrinsics.checkNotNullExpressionValue(stringResource21, "getStringResource(...)");
            final String stringResource22 = ContextUtils.getStringResource(R.string.str_order_report);
            Intrinsics.checkNotNullExpressionValue(stringResource22, "getStringResource(...)");
            final String stringResource23 = ContextUtils.getStringResource(R.string.str_average_invoice_expiry);
            Intrinsics.checkNotNullExpressionValue(stringResource23, "getStringResource(...)");
            final String stringResource24 = ContextUtils.getStringResource(R.string.str_average_date_report);
            Intrinsics.checkNotNullExpressionValue(stringResource24, "getStringResource(...)");
            final String stringResource25 = ContextUtils.getStringResource(R.string.str_sales_report_order);
            Intrinsics.checkNotNullExpressionValue(stringResource25, "getStringResource(...)");
            final String stringResource26 = ContextUtils.getStringResource(R.string.str_sales_report_invoice);
            Intrinsics.checkNotNullExpressionValue(stringResource26, "getStringResource(...)");
            final String stringResource27 = ContextUtils.getStringResource(R.string.str_stock_query);
            Intrinsics.checkNotNullExpressionValue(stringResource27, "getStringResource(...)");
            final String stringResource28 = ContextUtils.getStringResource(R.string.str_price_query);
            Intrinsics.checkNotNullExpressionValue(stringResource28, "getStringResource(...)");
            final String stringResource29 = ContextUtils.getStringResource(R.string.str_warehouse_status);
            Intrinsics.checkNotNullExpressionValue(stringResource29, "getStringResource(...)");
            final String stringResource30 = ContextUtils.getStringResource(R.string.str_warehouse_status_in_out);
            Intrinsics.checkNotNullExpressionValue(stringResource30, "getStringResource(...)");
            final String stringResource31 = ContextUtils.getStringResource(R.string.str_orders);
            Intrinsics.checkNotNullExpressionValue(stringResource31, "getStringResource(...)");
            final String stringResource32 = ContextUtils.getStringResource(R.string.str_invoices);
            Intrinsics.checkNotNullExpressionValue(stringResource32, "getStringResource(...)");
            final String stringResource33 = ContextUtils.getStringResource(R.string.str_refund_invoices);
            Intrinsics.checkNotNullExpressionValue(stringResource33, "getStringResource(...)");
            final String stringResource34 = ContextUtils.getStringResource(R.string.str_cash_collections);
            Intrinsics.checkNotNullExpressionValue(stringResource34, "getStringResource(...)");
            final String stringResource35 = ContextUtils.getStringResource(R.string.str_credit_cart_collections);
            Intrinsics.checkNotNullExpressionValue(stringResource35, "getStringResource(...)");
            final String stringResource36 = ContextUtils.getStringResource(R.string.str_case_collections);
            Intrinsics.checkNotNullExpressionValue(stringResource36, "getStringResource(...)");
            final String stringResource37 = ContextUtils.getStringResource(R.string.str_check_collections);
            Intrinsics.checkNotNullExpressionValue(stringResource37, "getStringResource(...)");
            final String stringResource38 = ContextUtils.getStringResource(R.string.str_payroll_note_collections);
            Intrinsics.checkNotNullExpressionValue(stringResource38, "getStringResource(...)");
            final String stringResource39 = ContextUtils.getStringResource(R.string.str_vehicle_stock_status);
            Intrinsics.checkNotNullExpressionValue(stringResource39, "getStringResource(...)");
            final String stringResource40 = ContextUtils.getStringResource(R.string.str_sales_summary_report);
            Intrinsics.checkNotNullExpressionValue(stringResource40, "getStringResource(...)");
            final String stringResource41 = ContextUtils.getStringResource(R.string.str_sales_report_order);
            Intrinsics.checkNotNullExpressionValue(stringResource41, "getStringResource(...)");
            final String stringResource42 = ContextUtils.getStringResource(R.string.str_sales_report_invoice);
            Intrinsics.checkNotNullExpressionValue(stringResource42, "getStringResource(...)");
            final String stringResource43 = ContextUtils.getStringResource(R.string.str_order_totals_report);
            Intrinsics.checkNotNullExpressionValue(stringResource43, "getStringResource(...)");
            final String stringResource44 = ContextUtils.getStringResource(R.string.str_defined_reports);
            Intrinsics.checkNotNullExpressionValue(stringResource44, "getStringResource(...)");
            menuList = new String[]{stringResource19, stringResource20, stringResource21, stringResource22, stringResource23, stringResource24, stringResource25, stringResource26, stringResource27, stringResource28, stringResource29, stringResource30, stringResource31, stringResource32, stringResource33, stringResource34, stringResource35, stringResource36, stringResource37, stringResource38, stringResource39, stringResource40, stringResource41, stringResource42, stringResource43, stringResource44, "deneme"};
        }
        if (3 == i2) {
            final String stringResource45 = ContextUtils.getStringResource(R.string.str_new_customer);
            Intrinsics.checkNotNullExpressionValue(stringResource45, "getStringResource(...)");
            final String stringResource46 = ContextUtils.getStringResource(R.string.str_average_expiry_account);
            Intrinsics.checkNotNullExpressionValue(stringResource46, "getStringResource(...)");
            final String stringResource47 = ContextUtils.getStringResource(R.string.str_work_info);
            Intrinsics.checkNotNullExpressionValue(stringResource47, "getStringResource(...)");
            menuList = new String[]{stringResource45, stringResource46, stringResource47};
        }
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        context = activity;
    }
    public Activity getContext() {
        return context;
    }
    public void setContext(final Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<set-?>");
        context = activity;
    }
    public Object getChild(final int i2, final int i3) {
        final ArrayList<ReportListRowModel> arrayList = expandableListDetail.get(expandableListTitle.get(i2));
        if (null != arrayList) {
            return arrayList.get(i3);
        }
        return null;
    }
    public View getChildView(final int i2, final int i3, final boolean z, View view, final ViewGroup parent) {
        String str;
        Intrinsics.checkNotNullParameter(parent, "parent");
        final Object child = this.getChild(i2, i3);
        Intrinsics.checkNotNull(child, "null cannot be cast to non-null type com.proje.mobilesales.features.reports.model.ReportListRowModel");
        final ReportListRowModel reportListRowModel = (ReportListRowModel) child;
        if (null == view) {
            final Object systemService = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
            view = ((LayoutInflater) systemService).inflate(R.layout.user_report_list_item, parent, false);
        }
        Intrinsics.checkNotNull(view);
        final TextView textView = view.findViewById(R.id.expandedListItem);
        if (reportListRowModel.isUserDefined) {
            final UserReports userReports = reportListRowModel.userReport;
            if (null == userReports || null == (str = userReports.reportName)) {
                str = "";
            }
        } else {
            str = menuList[reportListRowModel.f1271id];
        }
        textView.setText(str);
        return view;
    }
    public int getChildrenCount(final int i2) {
        final ArrayList<ReportListRowModel> arrayList = expandableListDetail.get(expandableListTitle.get(i2));
        if (null != arrayList) {
            return arrayList.size();
        }
        return 0;
    }
    public Object getGroup(final int i2) {
        return expandableListTitle.get(i2);
    }
    public int getGroupCount() {
        return expandableListTitle.size();
    }
    public View getGroupView(final int i2, final boolean z, View view, final ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        final Object group = this.getGroup(i2);
        Intrinsics.checkNotNull(group, "null cannot be cast to non-null type kotlin.String");
        final String str = (String) group;
        if (null == view) {
            final Object systemService = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
            view = ((LayoutInflater) systemService).inflate(R.layout.user_report_group_header, parent, false);
        }
        Intrinsics.checkNotNull(view);
        final TextView textView = view.findViewById(R.id.listTitle);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(str);
        return view;
    }
}
