package com.proje.mobilesales.core.interfaces.di;

import com.proje.mobilesales.core.base.BaseDrawerActivity;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseErpActivityPreferences;
import com.proje.mobilesales.core.base.BaseFicheFragment;
import com.proje.mobilesales.core.base.BaseListActivity;
import com.proje.mobilesales.features.about.view.activity.AboutActivity;
import com.proje.mobilesales.features.activity.BarcodeScannerProductViewActivity;
import com.proje.mobilesales.features.activity.BarcodeScannerView;
import com.proje.mobilesales.features.activity.ImageDialogActivity;
import com.proje.mobilesales.features.activity.ImageViewActivity;
import com.proje.mobilesales.features.activity.LoginActivity;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.activity.TransferActivity;
import com.proje.mobilesales.features.activity.TransferGetActivity;
import com.proje.mobilesales.features.activity.TransferSendActivity;
import com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter;
import com.proje.mobilesales.features.adapter.KeyValueAdapter;
import com.proje.mobilesales.features.cabinoperation.adapter.CabinListRecyclerViewAdapter;
import com.proje.mobilesales.features.cabinoperation.adapter.CabinNewListRecyclerViewAdapter;
import com.proje.mobilesales.features.cabinoperation.view.activity.BarcodeScannerCabinView;
import com.proje.mobilesales.features.cabinoperation.view.fragment.CabinListFragment;
import com.proje.mobilesales.features.cabinoperation.view.fragment.CabinNewListFragment;
import com.proje.mobilesales.features.collections.casefiche.view.activity.CaseFicheActivity;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.adapter.CashCreditDetailListRecyclerViewAdapter;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.activity.CashAndCreditCardFicheActivity;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragment;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailListFragment;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheHeaderFragment;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.adapter.ChequeDeedDetailListRecyclerViewAdapter;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.view.activity.ChequeAndDeedFicheActivity;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.view.fragment.ChequeDeedFicheDetailListFragment;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.view.fragment.ChequeDeedFicheHeaderFragment;
import com.proje.mobilesales.features.collections.prefs.view.activity.SalesCaseCashHeaderEnterActivity;
import com.proje.mobilesales.features.collections.prefs.view.activity.SalesCashAndCreditHeaderEnterActivity;
import com.proje.mobilesales.features.collections.prefs.view.activity.SalesChequeAndDeedHeaderEnterActivity;
import com.proje.mobilesales.features.collections.receipt.adapter.ReceiptFicheListRecyclerViewAdapter;
import com.proje.mobilesales.features.collections.receipt.view.activity.ReceiptFicheListActivity;
import com.proje.mobilesales.features.collections.receipt.view.fragment.ReceiptFicheListFragment;
import com.proje.mobilesales.features.customer.view.communication.person.CustomerNetPersonRVAdapter;
import com.proje.mobilesales.features.customer.view.communication.ship.CustomerShipAddressFragment;
import com.proje.mobilesales.features.customer.view.general.CustomerActivity;
import com.proje.mobilesales.features.customer.view.general.CustomerGeneralRecyclerViewAdapter;
import com.proje.mobilesales.features.customer.view.info.CustomerCurrencyInfoActivity;
import com.proje.mobilesales.features.customer.view.info.CustomerInfoActivity;
import com.proje.mobilesales.features.customer.view.list.CustomerListActivity;
import com.proje.mobilesales.features.customer.view.list.CustomerListFragment;
import com.proje.mobilesales.features.customer.view.list.CustomerListShipFragment;
import com.proje.mobilesales.features.customer.view.list.CustomerRecyclerViewAdapter;
import com.proje.mobilesales.features.customer.view.newadd.CustomerNewActivity;
import com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragment;
import com.proje.mobilesales.features.customer.view.operation.CustomerOperationFragment;
import com.proje.mobilesales.features.customer.view.operation.CustomerOperationRecyclerViewAdapter;
import com.proje.mobilesales.features.customer.view.risk.CustomerRiskFragment;
import com.proje.mobilesales.features.customer.view.risk.CustomerRiskRecyclerViewAdapter;
import com.proje.mobilesales.features.customer.view.route.CustomerRouteListActivity;
import com.proje.mobilesales.features.customer.view.route.CustomerRouteRecyclerViewAdapter;
import com.proje.mobilesales.features.customer.view.summary.CustomerSummaryFragment;
import com.proje.mobilesales.features.dailyinformation.view.activity.DailyFicheListActivity;
import com.proje.mobilesales.features.driverinformation.view.activity.DriverInformationActivity;
import com.proje.mobilesales.features.driverinformation.view.activity.EDispatchExtraInfoActivity;
import com.proje.mobilesales.features.gpsinfo.view.activity.GpsInfoActivity;
import com.proje.mobilesales.features.notification.view.detail.NotificationDetailActivity;
import com.proje.mobilesales.features.notification.view.list.NotificationListActivity;
import com.proje.mobilesales.features.penetration.view.detail.PenetrationActivity;
import com.proje.mobilesales.features.penetration.view.detail.PenetrationLineRecyclerViewAdapter;
import com.proje.mobilesales.features.penetration.view.list.PenetrationListFragment;
import com.proje.mobilesales.features.penetration.view.list.PenetrationRecyclerViewAdapter;
import com.proje.mobilesales.features.product.view.detail.ProductDetailFragment;
import com.proje.mobilesales.features.product.view.list.ProductListFragment;
import com.proje.mobilesales.features.product.view.list.ProductRecyclerViewAdapter;
import com.proje.mobilesales.features.product.view.serial.ProductSerialFragment;
import com.proje.mobilesales.features.product.view.tree.ProductTreeActivity;
import com.proje.mobilesales.features.reports.view.activity.ItemReportsActivity;
import com.proje.mobilesales.features.reports.view.activity.ReportAllActivity;
import com.proje.mobilesales.features.reports.view.activity.ReportExtractActivity;
import com.proje.mobilesales.features.reports.view.activity.ReportWCFSalesOrdInvActivity;
import com.proje.mobilesales.features.reports.view.fragment.ItemReportsFragment;
import com.proje.mobilesales.features.sales.view.detail.SalesDetailActivity;
import com.proje.mobilesales.features.sales.view.detail.SalesDetailFragment;
import com.proje.mobilesales.features.sales.view.detail.SalesDetailLineRecyclerViewAdapter;
import com.proje.mobilesales.features.sales.view.detail.SalesDetailListFragment;
import com.proje.mobilesales.features.sales.view.distribution.DistributionListActivity;
import com.proje.mobilesales.features.sales.view.distribution.DistributionListRecyclerViewAdapter;
import com.proje.mobilesales.features.sales.view.list.LastOrderProductsActivity;
import com.proje.mobilesales.features.sales.view.list.SalesListActivity;
import com.proje.mobilesales.features.sales.view.list.SalesListFragment;
import com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapter;
import com.proje.mobilesales.features.sales.view.mans.SalesMansListActivity;
import com.proje.mobilesales.features.sales.view.mans.SalesMansListRecyclerViewAdapter;
import com.proje.mobilesales.features.sales.view.newadd.SalesActivityNew;
import com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragment;
import com.proje.mobilesales.features.sales.view.newadd.SalesLogisticFragment;
import com.proje.mobilesales.features.sales.view.newadd.SalesPaymentFragment;
import com.proje.mobilesales.features.sales.view.order.LastOrderProductsRecyclerViewAdapter;
import com.proje.mobilesales.features.sales.view.order.SalesOrderListActivity;
import com.proje.mobilesales.features.sales.view.order.SalesOrderListRecyclerViewAdapter;
import com.proje.mobilesales.features.sales.view.serialgroup.SalesSerialGroupActivity;
import com.proje.mobilesales.features.sales.view.serialgroup.SalesSerialGroupRecyclerViewAdapter;
import com.proje.mobilesales.features.sales.view.validation.OrderValidationListFragment;
import com.proje.mobilesales.features.sales.view.validation.OrderValidationRecyclerViewAdapter;
import com.proje.mobilesales.features.sales.view.variant.SalesVariantActivity;
import com.proje.mobilesales.features.settings.view.activity.PreferenceActivity;
import com.proje.mobilesales.features.settings.view.activity.SettingsActivity;
import com.proje.mobilesales.features.settings.view.fragment.PrinterServiceSettingsFragment;
import com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragment;
import com.proje.mobilesales.features.todo.view.activity.TodoListActivity;
import com.proje.mobilesales.features.todo.view.activity.TodoReadActivity;
import com.proje.mobilesales.features.tools.view.activity.StartInfoEnterActivity;
import com.proje.mobilesales.features.userreport.adapter.UserReportsParametersRecyclerViewAdapter;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsActivity;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsParametersActivity;
import com.proje.mobilesales.features.visit.adapter.VisitRecyclerViewAdapter;
import com.proje.mobilesales.features.visit.view.activity.VisitEnterActivityNew;
import com.proje.mobilesales.features.visit.view.fragment.VisitEnterFragment;
import com.proje.mobilesales.features.visit.view.fragment.VisitListFragment;

public interface ActivityComponent extends IComponent {
    void inject(BaseDrawerActivity baseDrawerActivity);

    void inject(BaseErpActivity baseErpActivity);

    void inject(BaseErpActivityPreferences baseErpActivityPreferences);

    void inject(BaseFicheFragment baseFicheFragment);

    void inject(BaseListActivity baseListActivity);

    void inject(AboutActivity aboutActivity);

    void inject(BarcodeScannerProductViewActivity barcodeScannerProductViewActivity);

    void inject(BarcodeScannerView barcodeScannerView);

    void inject(ImageDialogActivity imageDialogActivity);

    void inject(ImageViewActivity imageViewActivity);

    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);

    void inject(TransferActivity transferActivity);

    void inject(TransferGetActivity transferGetActivity);

    void inject(TransferSendActivity transferSendActivity);

    void inject(BarcodeScannerViewRecyclerViewAdapter barcodeScannerViewRecyclerViewAdapter);

    void inject(KeyValueAdapter keyValueAdapter);

    void inject(CabinListRecyclerViewAdapter cabinListRecyclerViewAdapter);

    void inject(CabinNewListRecyclerViewAdapter cabinNewListRecyclerViewAdapter);

    void inject(BarcodeScannerCabinView barcodeScannerCabinView);

    void inject(CabinListFragment cabinListFragment);

    void inject(CabinNewListFragment cabinNewListFragment);

    void inject(CaseFicheActivity caseFicheActivity);

    void inject(CashCreditDetailListRecyclerViewAdapter cashCreditDetailListRecyclerViewAdapter);

    void inject(CashAndCreditCardFicheActivity cashAndCreditCardFicheActivity);

    void inject(CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment);

    void inject(CashCreditFicheDetailListFragment cashCreditFicheDetailListFragment);

    void inject(CashCreditFicheHeaderFragment cashCreditFicheHeaderFragment);

    void inject(ChequeDeedDetailListRecyclerViewAdapter chequeDeedDetailListRecyclerViewAdapter);

    void inject(ChequeAndDeedFicheActivity chequeAndDeedFicheActivity);

    void inject(ChequeDeedFicheDetailListFragment chequeDeedFicheDetailListFragment);

    void inject(ChequeDeedFicheHeaderFragment chequeDeedFicheHeaderFragment);

    void inject(SalesCaseCashHeaderEnterActivity salesCaseCashHeaderEnterActivity);

    void inject(SalesCashAndCreditHeaderEnterActivity salesCashAndCreditHeaderEnterActivity);

    void inject(SalesChequeAndDeedHeaderEnterActivity salesChequeAndDeedHeaderEnterActivity);

    void inject(ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter);

    void inject(ReceiptFicheListActivity receiptFicheListActivity);

    void inject(ReceiptFicheListFragment receiptFicheListFragment);

    void inject(CustomerNetPersonRVAdapter customerNetPersonRVAdapter);

    void inject(CustomerShipAddressFragment customerShipAddressFragment);

    void inject(CustomerActivity customerActivity);

    void inject(CustomerGeneralRecyclerViewAdapter customerGeneralRecyclerViewAdapter);

    void inject(CustomerCurrencyInfoActivity customerCurrencyInfoActivity);

    void inject(CustomerInfoActivity customerInfoActivity);

    void inject(CustomerListActivity customerListActivity);

    void inject(CustomerListFragment customerListFragment);

    void inject(CustomerListShipFragment customerListShipFragment);

    void inject(CustomerRecyclerViewAdapter customerRecyclerViewAdapter);

    void inject(CustomerNewActivity customerNewActivity);

    void inject(CustomerNewFragment customerNewFragment);

    void inject(CustomerOperationFragment customerOperationFragment);

    void inject(CustomerOperationRecyclerViewAdapter customerOperationRecyclerViewAdapter);

    void inject(CustomerRiskFragment customerRiskFragment);

    void inject(CustomerRiskRecyclerViewAdapter customerRiskRecyclerViewAdapter);

    void inject(CustomerRouteListActivity customerRouteListActivity);

    void inject(CustomerRouteRecyclerViewAdapter customerRouteRecyclerViewAdapter);

    void inject(CustomerSummaryFragment customerSummaryFragment);

    void inject(DailyFicheListActivity dailyFicheListActivity);

    void inject(DriverInformationActivity driverInformationActivity);

    void inject(EDispatchExtraInfoActivity eDispatchExtraInfoActivity);

    void inject(GpsInfoActivity gpsInfoActivity);

    void inject(NotificationDetailActivity notificationDetailActivity);

    void inject(NotificationListActivity notificationListActivity);

    void inject(PenetrationActivity penetrationActivity);

    void inject(PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter);

    void inject(PenetrationListFragment penetrationListFragment);

    void inject(PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter);

    void inject(ProductDetailFragment productDetailFragment);

    void inject(ProductListFragment productListFragment);

    void inject(ProductRecyclerViewAdapter productRecyclerViewAdapter);

    void inject(ProductSerialFragment productSerialFragment);

    void inject(ProductTreeActivity productTreeActivity);

    void inject(ItemReportsActivity itemReportsActivity);

    void inject(ReportAllActivity reportAllActivity);

    void inject(ReportExtractActivity reportExtractActivity);

    void inject(ReportWCFSalesOrdInvActivity reportWCFSalesOrdInvActivity);

    void inject(ItemReportsFragment itemReportsFragment);

    void inject(SalesDetailActivity salesDetailActivity);

    void inject(SalesDetailFragment salesDetailFragment);

    void inject(SalesDetailLineRecyclerViewAdapter salesDetailLineRecyclerViewAdapter);

    void inject(SalesDetailListFragment salesDetailListFragment);

    void inject(DistributionListActivity distributionListActivity);

    void inject(DistributionListRecyclerViewAdapter distributionListRecyclerViewAdapter);

    void inject(LastOrderProductsActivity lastOrderProductsActivity);

    void inject(SalesListActivity salesListActivity);

    void inject(SalesListFragment salesListFragment);

    void inject(SalesRecyclerViewAdapter salesRecyclerViewAdapter);

    void inject(SalesMansListActivity salesMansListActivity);

    void inject(SalesMansListRecyclerViewAdapter salesMansListRecyclerViewAdapter);

    void inject(SalesActivityNew salesActivityNew);

    void inject(SalesHeaderFragment salesHeaderFragment);

    void inject(SalesLogisticFragment salesLogisticFragment);

    void inject(SalesPaymentFragment salesPaymentFragment);

    void inject(LastOrderProductsRecyclerViewAdapter lastOrderProductsRecyclerViewAdapter);

    void inject(SalesOrderListActivity salesOrderListActivity);

    void inject(SalesOrderListRecyclerViewAdapter salesOrderListRecyclerViewAdapter);

    void inject(SalesSerialGroupActivity salesSerialGroupActivity);

    void inject(SalesSerialGroupRecyclerViewAdapter salesSerialGroupRecyclerViewAdapter);

    void inject(OrderValidationListFragment orderValidationListFragment);

    void inject(OrderValidationRecyclerViewAdapter orderValidationRecyclerViewAdapter);

    void inject(SalesVariantActivity salesVariantActivity);

    void inject(PreferenceActivity preferenceActivity);

    void inject(SettingsActivity settingsActivity);

    void inject(PrinterServiceSettingsFragment printerServiceSettingsFragment);

    void inject(UserSettingsFragment userSettingsFragment);

    void inject(TodoListActivity todoListActivity);

    void inject(TodoReadActivity todoReadActivity);

    void inject(StartInfoEnterActivity startInfoEnterActivity);

    void inject(UserReportsParametersRecyclerViewAdapter userReportsParametersRecyclerViewAdapter);

    void inject(UserReportsActivity userReportsActivity);

    void inject(UserReportsParametersActivity userReportsParametersActivity);

    void inject(VisitRecyclerViewAdapter visitRecyclerViewAdapter);

    void inject(VisitEnterActivityNew visitEnterActivityNew);

    void inject(VisitEnterFragment visitEnterFragment);

    void inject(VisitListFragment visitListFragment);
}
