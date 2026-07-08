package com.proje.mobilesales.features.reports.viewmodel;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.enums.InvoiceType;
import com.proje.mobilesales.core.enums.OrderStatus;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.tigerwcf.SelectResult;
import com.proje.mobilesales.features.reports.model.ReportListParameter;
import com.proje.mobilesales.features.reports.model.enums.ReportCurrencyUnit;
import com.proje.mobilesales.features.reports.model.enums.ReportDebitFilterType;
import com.proje.mobilesales.features.reports.model.enums.ReportSortType;
import com.proje.mobilesales.features.reports.repository.IReportWcfQueryRepository;
import java.util.ArrayList;

import com.proje.mobilesales.features.reports.view.activity.ReportExtractActivity;
import kotlin.jvm.internal.Intrinsics;

public final class ReportWcfQueriesViewModel extends BaseViewModel {
    private final IReportWcfQueryRepository repository;
    private final String tag;
    public ReportWcfQueriesViewModel(final IReportWcfQueryRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        tag = "BaseReportWcfQueriesViewModel";
    }

    public SelectResult getAverageTotalList(final int i2, final String createDateStart, final String createDateEnd) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        return repository.getAverageTotalList(i2, createDateStart, createDateEnd);
    }

    public SelectResult getOrderInvoice(final int i2, final String createDateStart, final String createDateEnd, final int i3, final ProcessType type, final int i4) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        Intrinsics.checkNotNullParameter(type, "type");
        return repository.getOrderInvoice(i2, createDateStart, createDateEnd, i3, type, i4);
    }

    public SelectResult getCashCredit(final int i2, final String createDateStart, final String createDateEnd, final int i3, final ProcessType type, final int i4) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        Intrinsics.checkNotNullParameter(type, "type");
        return repository.getCashCredit(i2, createDateStart, createDateEnd, i3, type, i4);
    }

    public SelectResult getCashCase(final int i2, final String createDateStart, final String createDateEnd, final int i3, final ProcessType processType, final int i4) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        return repository.getCashCase(i2, createDateStart, createDateEnd, i3, processType, i4);
    }

    public SelectResult getChequeDeed(final int i2, final String createDateStart, final String createDateEnd, final int i3, final ProcessType type, final int i4) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        Intrinsics.checkNotNullParameter(type, "type");
        return repository.getChequeDeed(i2, createDateStart, createDateEnd, i3, type, i4);
    }

    public SelectResult getSalesmans(final String salesmanCode, final int i2, final String[] filter) {
        Intrinsics.checkNotNullParameter(salesmanCode, "salesmanCode");
        Intrinsics.checkNotNullParameter(filter, "filter");
        return repository.getSalesmans(salesmanCode, i2, filter);
    }

    public SelectResult getInvoiceAvgLinePayPlan(final ArrayList<Integer> ficheRefs) {
        Intrinsics.checkNotNullParameter(ficheRefs, "ficheRefs");
        return repository.getInvoiceAvgLinePayPlan(ficheRefs);
    }

    public SelectResult getVehicleStatus(final int i2, final ProcessType processType, final int i3, final ReportSortType reportSortType) {
        Intrinsics.checkNotNullParameter(reportSortType, "reportSortType");
        return repository.getVehicleStatus(i2, processType, i3, reportSortType);
    }

    public SelectResult getOrderReportQuery(final int i2, final int i3, final String createDateStart, final String createDateEnd, final int i4, final int i5) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        return repository.getOrderReportQuery(i2, i3, createDateStart, createDateEnd, i4, i5);
    }

    public SelectResult getSalesSummary(final String str, final String str2, final int i2, final ProcessType processType, final int i3) {
        return repository.getSalesSummary(str, str2, i2, processType, i3);
    }

    public SelectResult getSalesOrd(final ReportListParameter params, final int i2, final ProcessType processType, final OrderStatus orderType, final boolean z, final int i3) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(orderType, "orderType");
        return repository.getSalesOrd(params, i2, processType, orderType, z, i3);
    }

    public SelectResult getSalesInv(final ReportListParameter params, final int i2, final ProcessType processType, final InvoiceType invType, final int i3) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(invType, "invType");
        return repository.getSalesInv(params, i2, processType, invType, i3);
    }

    public SelectResult getCollectionsListQuery(final int i2, final String createDateStart, final String createDateEnd, final ReportDebitFilterType reportDebitFilterType) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        return repository.getCollectionsListQuery(i2, createDateStart, createDateEnd, reportDebitFilterType);
    }

    public SelectResult getNotPaymentInvoice(final int i2, final String createDateStart, final String createDateEnd, final ReportDebitFilterType reportDebitFilterType) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        return repository.getNotPaymentInvoice(i2, createDateStart, createDateEnd, reportDebitFilterType);
    }

    public SelectResult getCustomerDebitTracking(final int i2) {
        return repository.getCustomerDebitTracking(i2);
    }

    public SelectResult getExtractList(final int i2, final String createDateStart, final String createDateEnd, final ReportCurrencyUnit currencyUnit, final int i3) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        Intrinsics.checkNotNullParameter(currencyUnit, "currencyUnit");
        return repository.getExtractList(i2, createDateStart, createDateEnd, currencyUnit, i3);
    }

    public SelectResult getTotalCreditDebit(final int i2, final String str, final String str2) {
        return repository.getTotalCreditDebit(i2, str, str2);
    }

    public void getCustomerExtractReport(final int i2, final String startDate, final String endDate, final String salesPersonRef, final ReportExtractActivity.ReportExtractResponseListener responseListener) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(salesPersonRef, "salesPersonRef");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.getBaseErp().getCustomerExtractReport(i2, startDate, endDate, salesPersonRef, responseListener);
    }

    public SelectResult getExtractInvoiceHeader(final int i2) {
        return repository.getExtractInvoiceHeader(i2);
    }

    public SelectResult getExtractOrderHeader(final int i2) {
        return repository.getExtractOrderHeader(i2);
    }

    public SelectResult getExtractInvoiceDetail(final int i2) {
        return repository.getExtractInvoiceDetail(i2);
    }

    public SelectResult getExtractOrderDetail(final int i2) {
        return repository.getExtractOrderDetail(i2);
    }

    public SelectResult getExtractChequeDeedHeader(final int i2) {
        return repository.getExtractChequeDeedHeader(i2);
    }

    public SelectResult getExtractChequeDeedDetail(final int i2, final int i3) {
        return repository.getExtractChequeDeedDetail(i2, i3);
    }

    public SelectResult getExtractCashCreditHeader(final int i2) {
        return repository.getExtractCashCreditHeader(i2);
    }

    public SelectResult getExtractCashCreditHeaderDueDate(final int i2) {
        return repository.getExtractCashCreditHeaderDueDate(i2);
    }

    public SelectResult getExtractCashCreditDetail(final int i2, final int i3, final int i4) {
        return repository.getExtractCashCreditDetail(i2, i3, i4);
    }

    public SelectResult getExtractMoneyOrderHeader(final int i2) {
        return repository.getExtractMoneyOrderHeader(i2);
    }

    public SelectResult getExtractCaseCashDetail(final int i2, final int i3) {
        return repository.getExtractCaseCashDetail(i2, i3);
    }
}
