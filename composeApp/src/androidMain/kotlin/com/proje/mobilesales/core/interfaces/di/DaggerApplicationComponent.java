package com.proje.mobilesales.core.interfaces.di;

import android.content.Context;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.proje.mobilesales.core.ActivityModule;
import com.proje.mobilesales.core.ActivityModule_ProvideActionViewResolverFactory;
import com.proje.mobilesales.core.ActivityModule_ProvideAlertDialogBuilderFactory;
import com.proje.mobilesales.core.ActivityModule_ProvideBaseErpFactory;
import com.proje.mobilesales.core.ActivityModule_ProvideContextFactory;
import com.proje.mobilesales.core.ActivityModule_ProvideErpTypeFactory;
import com.proje.mobilesales.core.ActivityModule_ProvideIoSchedulerFactory;
import com.proje.mobilesales.core.ActivityModule_ProvideKeyDelegateFactory;
import com.proje.mobilesales.core.ActivityModule_ProvideLogoSharedPrerencesFactory;
import com.proje.mobilesales.core.ActivityModule_ProvideProgressDialogBuilderFactory;
import com.proje.mobilesales.core.ActivityModule_ProvideSqlManagerFactory;
import com.proje.mobilesales.core.CommunicationModule;
import com.proje.mobilesales.core.CommunicationModule_ProvideDispatcherFactory;
import com.proje.mobilesales.core.CommunicationModule_ProvideGetContextFactory;
import com.proje.mobilesales.core.CommunicationModule_ProvideNetsisCallFactoryFactory;
import com.proje.mobilesales.core.CommunicationModule_ProvideNetsisPublicCallFactoryFactory;
import com.proje.mobilesales.core.CommunicationModule_ProvideRestServiceFactoryFactory;
import com.proje.mobilesales.core.CommunicationModule_ProvideRestServicePublicFactoryFactory;
import com.proje.mobilesales.core.CommunicationModule_ProvideRestTokenApiFactory;
import com.proje.mobilesales.core.CommunicationModule_ProvideWcfCallFactoryFactory;
import com.proje.mobilesales.core.CommunicationModule_ProvideWcfServiceFactoryFactory;
import com.proje.mobilesales.core.ErpModule;
import com.proje.mobilesales.core.ErpModule_ProvideErpRightFactory;
import com.proje.mobilesales.core.ErpModule_ProvideUserFactory;
import com.proje.mobilesales.core.ErpModule_ProvideUserMenuRightsFactory;
import com.proje.mobilesales.core.ErpModule_ProvideUserSettingsFactory;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask_MembersInjector;
import com.proje.mobilesales.core.asynctask.TigerWcfAsyncTask;
import com.proje.mobilesales.core.asynctask.TigerWcfAsyncTask_MembersInjector;
import com.proje.mobilesales.core.base.ActionViewResolver;
import com.proje.mobilesales.core.base.BaseCommunication_MembersInjector;
import com.proje.mobilesales.core.base.BaseDrawerActivity;
import com.proje.mobilesales.core.base.BaseDrawerActivity_MembersInjector;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseErpActivityPreferences;
import com.proje.mobilesales.core.base.BaseErpActivityPreferences_MembersInjector;
import com.proje.mobilesales.core.base.BaseErpActivity_MembersInjector;
import com.proje.mobilesales.core.base.BaseErp_MembersInjector;
import com.proje.mobilesales.core.base.BaseFicheActivity_MembersInjector;
import com.proje.mobilesales.core.base.BaseFicheFragment;
import com.proje.mobilesales.core.base.BaseFicheFragment_MembersInjector;
import com.proje.mobilesales.core.base.BaseListActivity;
import com.proje.mobilesales.core.base.BaseListDrawerActivity_MembersInjector;
import com.proje.mobilesales.core.design.Netsis;
import com.proje.mobilesales.core.design.Tiger;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.netsis.NetsisRestPublicFactory;
import com.proje.mobilesales.core.netsis.NetsisRestServiceFactory;
import com.proje.mobilesales.core.netsis.NetsisRestTokenApi;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.tigerwcf.WcfServiceFactory;
import com.proje.mobilesales.core.utils.KeyDelegate;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.features.about.view.activity.AboutActivity;
import com.proje.mobilesales.features.activity.BarcodeScannerProductViewActivity;
import com.proje.mobilesales.features.activity.BarcodeScannerProductViewActivity_MembersInjector;
import com.proje.mobilesales.features.activity.BarcodeScannerView;
import com.proje.mobilesales.features.activity.BarcodeScannerView_MembersInjector;
import com.proje.mobilesales.features.activity.ImageDialogActivity;
import com.proje.mobilesales.features.activity.ImageDialogActivity_MembersInjector;
import com.proje.mobilesales.features.activity.ImageViewActivity;
import com.proje.mobilesales.features.activity.ImageViewActivity_MembersInjector;
import com.proje.mobilesales.features.activity.LoginActivity;
import com.proje.mobilesales.features.activity.LoginActivity_MembersInjector;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.activity.MainActivity_MembersInjector;
import com.proje.mobilesales.features.activity.TransferActivity;
import com.proje.mobilesales.features.activity.TransferActivity_MembersInjector;
import com.proje.mobilesales.features.activity.TransferGetActivity;
import com.proje.mobilesales.features.activity.TransferGetActivity_MembersInjector;
import com.proje.mobilesales.features.activity.TransferSendActivity;
import com.proje.mobilesales.features.activity.TransferSendActivity_MembersInjector;
import com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter;
import com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter_MembersInjector;
import com.proje.mobilesales.features.adapter.KeyValueAdapter;
import com.proje.mobilesales.features.adapter.KeyValueAdapter_MembersInjector;
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
import com.proje.mobilesales.features.dbmodel.User;
import com.proje.mobilesales.features.driverinformation.view.activity.DriverInformationActivity;
import com.proje.mobilesales.features.driverinformation.view.activity.EDispatchExtraInfoActivity;
import com.proje.mobilesales.features.gpsinfo.view.activity.GpsInfoActivity;
import com.proje.mobilesales.features.model.ErpRights;
import com.proje.mobilesales.features.model.UserMenuRights;
import com.proje.mobilesales.features.model.UserSettings;
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
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import io.reactivex.Scheduler;
import javax.inject.Provider;
import okhttp3.Call;
import okhttp3.Dispatcher;

public final class DaggerApplicationComponent {
    private DaggerApplicationComponent() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static ApplicationComponent create() {
        return new Builder().build();
    }

    public static final class Builder {
        private Builder() {
        }

        public ApplicationComponent build() {
            return new ApplicationComponentImpl();
        }
    }

    private static final class ActivityComponentImpl implements ActivityComponent {
        private final ActivityComponentImpl activityComponentImpl;
        private final ActivityModule activityModule;
        private final ApplicationComponentImpl applicationComponentImpl;
        private Provider<ActionViewResolver> provideActionViewResolverProvider;
        private Provider<Context> provideContextProvider;
        private Provider<ErpType> provideErpTypeProvider;
        private Provider<Scheduler> provideIoSchedulerProvider;
        private Provider<KeyDelegate> provideKeyDelegateProvider;
        private Provider<SharedPreferencesHelper> provideLogoSharedPrerencesProvider;
        private Provider<ISqlManager> provideSqlManagerProvider;

        public void inject(CabinListRecyclerViewAdapter cabinListRecyclerViewAdapter) {
        }
        public void inject(CabinNewListRecyclerViewAdapter cabinNewListRecyclerViewAdapter) {
        }

        public void inject(BarcodeScannerCabinView barcodeScannerCabinView) {
        }
        public void inject(CabinListFragment cabinListFragment) {
        }

        
        public void inject(CabinNewListFragment cabinNewListFragment) {
        }

        
        public void inject(CashCreditDetailListRecyclerViewAdapter cashCreditDetailListRecyclerViewAdapter) {
        }

        
        public void inject(CashCreditFicheDetailListFragment cashCreditFicheDetailListFragment) {
        }

        
        public void inject(ChequeDeedDetailListRecyclerViewAdapter chequeDeedDetailListRecyclerViewAdapter) {
        }

        
        public void inject(ChequeDeedFicheDetailListFragment chequeDeedFicheDetailListFragment) {
        }

        
        public void inject(ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter) {
        }

        
        public void inject(ReceiptFicheListFragment receiptFicheListFragment) {
        }

        
        public void inject(CustomerNetPersonRVAdapter customerNetPersonRVAdapter) {
        }

        
        public void inject(CustomerShipAddressFragment customerShipAddressFragment) {
        }

        
        public void inject(CustomerGeneralRecyclerViewAdapter customerGeneralRecyclerViewAdapter) {
        }

        public void inject(CustomerListFragment customerListFragment) {
        }

        
        public void inject(CustomerListShipFragment customerListShipFragment) {
        }

        
        public void inject(CustomerRecyclerViewAdapter customerRecyclerViewAdapter) {
        }

        
        public void inject(CustomerOperationFragment customerOperationFragment) {
        }

        
        public void inject(CustomerOperationRecyclerViewAdapter customerOperationRecyclerViewAdapter) {
        }

        
        public void inject(CustomerRiskFragment customerRiskFragment) {
        }

        
        public void inject(CustomerRiskRecyclerViewAdapter customerRiskRecyclerViewAdapter) {
        }

        
        public void inject(CustomerRouteRecyclerViewAdapter customerRouteRecyclerViewAdapter) {
        }

        
        public void inject(CustomerSummaryFragment customerSummaryFragment) {
        }

        
        public void inject(GpsInfoActivity gpsInfoActivity) {
        } 
        public void inject(NotificationDetailActivity notificationDetailActivity) {
        }
 
        public void inject(NotificationListActivity notificationListActivity) {
        }

        
        public void inject(PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter) {
        }

        
        public void inject(PenetrationListFragment penetrationListFragment) {
        }

        
        public void inject(PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter) {
        }

        
        public void inject(ProductDetailFragment productDetailFragment) {
        }

        
        public void inject(ProductListFragment productListFragment) {
        }

        
        public void inject(ProductRecyclerViewAdapter productRecyclerViewAdapter) {
        }

        
        public void inject(ProductSerialFragment productSerialFragment) {
        }

        
        public void inject(ProductTreeActivity productTreeActivity) {
        }

        
        public void inject(ItemReportsFragment itemReportsFragment) {
        }

        
        public void inject(SalesDetailLineRecyclerViewAdapter salesDetailLineRecyclerViewAdapter) {
        }

        
        public void inject(SalesDetailListFragment salesDetailListFragment) {
        }

        
        public void inject(DistributionListRecyclerViewAdapter distributionListRecyclerViewAdapter) {
        }

        
        public void inject(SalesListFragment salesListFragment) {
        }

        
        public void inject(SalesRecyclerViewAdapter salesRecyclerViewAdapter) {
        }

        
        public void inject(SalesMansListRecyclerViewAdapter salesMansListRecyclerViewAdapter) {
        }

        
        public void inject(LastOrderProductsRecyclerViewAdapter lastOrderProductsRecyclerViewAdapter) {
        }

        
        public void inject(SalesOrderListRecyclerViewAdapter salesOrderListRecyclerViewAdapter) {
        }

        
        public void inject(SalesSerialGroupRecyclerViewAdapter salesSerialGroupRecyclerViewAdapter) {
        }

        
        public void inject(OrderValidationListFragment orderValidationListFragment) {
        }

        
        public void inject(OrderValidationRecyclerViewAdapter orderValidationRecyclerViewAdapter) {
        }

        
        public void inject(PreferenceActivity preferenceActivity) {
        }

        
        public void inject(PrinterServiceSettingsFragment printerServiceSettingsFragment) {
        }

        
        public void inject(UserSettingsFragment userSettingsFragment) {
        }

        
        public void inject(UserReportsParametersRecyclerViewAdapter userReportsParametersRecyclerViewAdapter) {
        }

        
        public void inject(VisitRecyclerViewAdapter visitRecyclerViewAdapter) {
        }

        
        public void inject(VisitListFragment visitListFragment) {
        }

        private ActivityComponentImpl(ApplicationComponentImpl applicationComponentImpl, ActivityModule activityModule) {
            this.activityComponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.activityModule = activityModule;
            initialize(activityModule);
        }

        private ProgressDialogBuilder progressDialogBuilder() {
            return ActivityModule_ProvideProgressDialogBuilderFactory.provideProgressDialogBuilder(this.activityModule, this.provideContextProvider.get());
        }

        private AlertDialogBuilder alertDialogBuilder() {
            return ActivityModule_ProvideAlertDialogBuilderFactory.provideAlertDialogBuilder(this.activityModule, this.provideContextProvider.get());
        }

        private void initialize(ActivityModule activityModule) {
            Provider<Context> provider = DoubleCheck.provider(ActivityModule_ProvideContextFactory.create(activityModule));
            this.provideContextProvider = provider;
            this.provideLogoSharedPrerencesProvider = DoubleCheck.provider(ActivityModule_ProvideLogoSharedPrerencesFactory.create(activityModule, provider));
            this.provideIoSchedulerProvider = DoubleCheck.provider(ActivityModule_ProvideIoSchedulerFactory.create(activityModule));
            ActivityModule_ProvideErpTypeFactory create = ActivityModule_ProvideErpTypeFactory.create(activityModule, this.provideContextProvider);
            this.provideErpTypeProvider = create;
            this.provideSqlManagerProvider = DoubleCheck.provider(ActivityModule_ProvideSqlManagerFactory.create(activityModule, this.provideIoSchedulerProvider, create));
            this.provideKeyDelegateProvider = DoubleCheck.provider(ActivityModule_ProvideKeyDelegateFactory.create(activityModule));
            this.provideActionViewResolverProvider = DoubleCheck.provider(ActivityModule_ProvideActionViewResolverFactory.create(activityModule));
        }

        public void inject(LoginActivity loginActivity) {
            injectLoginActivity(loginActivity);
        }

        public void inject(MainActivity mainActivity) {
            injectMainActivity(mainActivity);
        }

        public void inject(AboutActivity aboutActivity) {
            injectAboutActivity(aboutActivity);
        }

        public void inject(BaseErpActivity baseErpActivity) {
            injectBaseErpActivity(baseErpActivity);
        }

        
        public void inject(BaseListActivity baseListActivity) {
            injectBaseListActivity(baseListActivity);
        }

        
        public void inject(BaseFicheFragment baseFicheFragment) {
            injectBaseFicheFragment(baseFicheFragment);
        }

        
        public void inject(BaseDrawerActivity baseDrawerActivity) {
            injectBaseDrawerActivity(baseDrawerActivity);
        }

        
        public void inject(CustomerActivity customerActivity) {
            injectCustomerActivity(customerActivity);
        }

        
        public void inject(CustomerNewActivity customerNewActivity) {
            injectCustomerNewActivity(customerNewActivity);
        }

        
        public void inject(CustomerNewFragment customerNewFragment) {
            injectCustomerNewFragment(customerNewFragment);
        }

        
        public void inject(CustomerListActivity customerListActivity) {
            injectCustomerListActivity(customerListActivity);
        }

        
        public void inject(KeyValueAdapter keyValueAdapter) {
            injectKeyValueAdapter(keyValueAdapter);
        }

        
        public void inject(TransferActivity transferActivity) {
            injectTransferActivity(transferActivity);
        }

        
        public void inject(TransferSendActivity transferSendActivity) {
            injectTransferSendActivity(transferSendActivity);
        }

        
        public void inject(BaseErpActivityPreferences baseErpActivityPreferences) {
            injectBaseErpActivityPreferences(baseErpActivityPreferences);
        }

        
        public void inject(CustomerInfoActivity customerInfoActivity) {
            injectCustomerInfoActivity(customerInfoActivity);
        }

        
        public void inject(SalesListActivity salesListActivity) {
            injectSalesListActivity(salesListActivity);
        }

        
        public void inject(DistributionListActivity distributionListActivity) {
            injectDistributionListActivity(distributionListActivity);
        }

        
        public void inject(DailyFicheListActivity dailyFicheListActivity) {
            injectDailyFicheListActivity(dailyFicheListActivity);
        }

        
        public void inject(TodoListActivity todoListActivity) {
            injectTodoListActivity(todoListActivity);
        }

        
        public void inject(TodoReadActivity todoReadActivity) {
            injectTodoReadActivity(todoReadActivity);
        }

        
        public void inject(TransferGetActivity transferGetActivity) {
            injectTransferGetActivity(transferGetActivity);
        }

        
        public void inject(SalesActivityNew salesActivityNew) {
            injectSalesActivityNew(salesActivityNew);
        }

        
        public void inject(SalesHeaderFragment salesHeaderFragment) {
            injectSalesHeaderFragment(salesHeaderFragment);
        }

        
        public void inject(SalesLogisticFragment salesLogisticFragment) {
            injectSalesLogisticFragment(salesLogisticFragment);
        }

        
        public void inject(SalesPaymentFragment salesPaymentFragment) {
            injectSalesPaymentFragment(salesPaymentFragment);
        }

        
        public void inject(SalesDetailActivity salesDetailActivity) {
            injectSalesDetailActivity(salesDetailActivity);
        }

        
        public void inject(SalesDetailFragment salesDetailFragment) {
            injectSalesDetailFragment(salesDetailFragment);
        }

        
        public void inject(SalesOrderListActivity salesOrderListActivity) {
            injectSalesOrderListActivity(salesOrderListActivity);
        }

        
        public void inject(SalesVariantActivity salesVariantActivity) {
            injectSalesVariantActivity(salesVariantActivity);
        }

        
        public void inject(EDispatchExtraInfoActivity eDispatchExtraInfoActivity) {
            injectEDispatchExtraInfoActivity(eDispatchExtraInfoActivity);
        }

        
        public void inject(DriverInformationActivity driverInformationActivity) {
            injectDriverInformationActivity(driverInformationActivity);
        }

        
        public void inject(BarcodeScannerProductViewActivity barcodeScannerProductViewActivity) {
            injectBarcodeScannerProductViewActivity(barcodeScannerProductViewActivity);
        }

        
        public void inject(BarcodeScannerView barcodeScannerView) {
            injectBarcodeScannerView(barcodeScannerView);
        }

        
        public void inject(BarcodeScannerViewRecyclerViewAdapter barcodeScannerViewRecyclerViewAdapter) {
            injectBarcodeScannerViewRecyclerViewAdapter(barcodeScannerViewRecyclerViewAdapter);
        }

        
        public void inject(SalesSerialGroupActivity salesSerialGroupActivity) {
            injectSalesSerialGroupActivity(salesSerialGroupActivity);
        }

        
        public void inject(SalesMansListActivity salesMansListActivity) {
            injectSalesMansListActivity(salesMansListActivity);
        }

        
        public void inject(ImageViewActivity imageViewActivity) {
            injectImageViewActivity(imageViewActivity);
        }

        
        public void inject(ImageDialogActivity imageDialogActivity) {
            injectImageDialogActivity(imageDialogActivity);
        }

        
        public void inject(PenetrationActivity penetrationActivity) {
            injectPenetrationActivity(penetrationActivity);
        }

        
        public void inject(ReportAllActivity reportAllActivity) {
            injectReportAllActivity(reportAllActivity);
        }

        
        public void inject(ReportWCFSalesOrdInvActivity reportWCFSalesOrdInvActivity) {
            injectReportWCFSalesOrdInvActivity(reportWCFSalesOrdInvActivity);
        }

        
        public void inject(ReportExtractActivity reportExtractActivity) {
            injectReportExtractActivity(reportExtractActivity);
        }

        
        public void inject(CustomerRouteListActivity customerRouteListActivity) {
            injectCustomerRouteListActivity(customerRouteListActivity);
        }

        
        public void inject(LastOrderProductsActivity lastOrderProductsActivity) {
            injectLastOrderProductsActivity(lastOrderProductsActivity);
        }

        
        public void inject(UserReportsParametersActivity userReportsParametersActivity) {
            injectUserReportsParametersActivity(userReportsParametersActivity);
        }

        
        public void inject(UserReportsActivity userReportsActivity) {
            injectUserReportsActivity(userReportsActivity);
        }

        
        public void inject(SalesCashAndCreditHeaderEnterActivity salesCashAndCreditHeaderEnterActivity) {
            injectSalesCashAndCreditHeaderEnterActivity(salesCashAndCreditHeaderEnterActivity);
        }

        
        public void inject(SalesCaseCashHeaderEnterActivity salesCaseCashHeaderEnterActivity) {
            injectSalesCaseCashHeaderEnterActivity(salesCaseCashHeaderEnterActivity);
        }

        
        public void inject(SalesChequeAndDeedHeaderEnterActivity salesChequeAndDeedHeaderEnterActivity) {
            injectSalesChequeAndDeedHeaderEnterActivity(salesChequeAndDeedHeaderEnterActivity);
        }

        
        public void inject(ReceiptFicheListActivity receiptFicheListActivity) {
            injectReceiptFicheListActivity(receiptFicheListActivity);
        }

        
        public void inject(CashAndCreditCardFicheActivity cashAndCreditCardFicheActivity) {
            injectCashAndCreditCardFicheActivity(cashAndCreditCardFicheActivity);
        }

        
        public void inject(CashCreditFicheHeaderFragment cashCreditFicheHeaderFragment) {
            injectCashCreditFicheHeaderFragment(cashCreditFicheHeaderFragment);
        }

        
        public void inject(CaseFicheActivity caseFicheActivity) {
            injectCaseFicheActivity(caseFicheActivity);
        }

        
        public void inject(ChequeAndDeedFicheActivity chequeAndDeedFicheActivity) {
            injectChequeAndDeedFicheActivity(chequeAndDeedFicheActivity);
        }

        
        public void inject(ChequeDeedFicheHeaderFragment chequeDeedFicheHeaderFragment) {
            injectChequeDeedFicheHeaderFragment(chequeDeedFicheHeaderFragment);
        }

        
        public void inject(StartInfoEnterActivity startInfoEnterActivity) {
            injectStartInfoEnterActivity(startInfoEnterActivity);
        }

        
        public void inject(VisitEnterActivityNew visitEnterActivityNew) {
            injectVisitEnterActivityNew(visitEnterActivityNew);
        }

        
        public void inject(VisitEnterFragment visitEnterFragment) {
            injectVisitEnterFragment(visitEnterFragment);
        }

        
        public void inject(SettingsActivity settingsActivity) {
            injectSettingsActivity(settingsActivity);
        }

        
        public void inject(CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment) {
            injectCashCreditFicheDetailEnterFragment(cashCreditFicheDetailEnterFragment);
        }

        
        public void inject(CustomerCurrencyInfoActivity customerCurrencyInfoActivity) {
            injectCustomerCurrencyInfoActivity(customerCurrencyInfoActivity);
        }

        
        public void inject(ItemReportsActivity itemReportsActivity) {
            injectItemReportsActivity(itemReportsActivity);
        }

        
        private LoginActivity injectLoginActivity(LoginActivity loginActivity) {
            LoginActivity_MembersInjector.injectMSharedPreferencesHelper(loginActivity, this.provideLogoSharedPrerencesProvider.get());
            return loginActivity;
        }

        
        private MainActivity injectMainActivity(MainActivity mainActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(mainActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(mainActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(mainActivity, alertDialogBuilder());
            BaseDrawerActivity_MembersInjector.injectMAlertDialogBuilder(mainActivity, alertDialogBuilder());
            MainActivity_MembersInjector.injectMAlertDialogBuilder(mainActivity, alertDialogBuilder());
            MainActivity_MembersInjector.injectMProgressDialogBuilder(mainActivity, progressDialogBuilder());
            MainActivity_MembersInjector.injectSqlManager(mainActivity, this.provideSqlManagerProvider.get());
            MainActivity_MembersInjector.injectMSharedPreferencesHelper(mainActivity, this.provideLogoSharedPrerencesProvider.get());
            return mainActivity;
        }

        
        private AboutActivity injectAboutActivity(AboutActivity aboutActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(aboutActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(aboutActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(aboutActivity, alertDialogBuilder());
            return aboutActivity;
        }

        
        private BaseErpActivity injectBaseErpActivity(BaseErpActivity baseErpActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(baseErpActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(baseErpActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(baseErpActivity, alertDialogBuilder());
            return baseErpActivity;
        }

        
        private BaseListActivity injectBaseListActivity(BaseListActivity baseListActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(baseListActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(baseListActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(baseListActivity, alertDialogBuilder());
            return baseListActivity;
        }

        
        private BaseFicheFragment injectBaseFicheFragment(BaseFicheFragment baseFicheFragment) {
            BaseFicheFragment_MembersInjector.injectMAlertDialogBuilder(baseFicheFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMArrayAlertDialogBuilder(baseFicheFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMDateAlertDialogBuilder(baseFicheFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMSqlManager(baseFicheFragment, this.provideSqlManagerProvider.get());
            BaseFicheFragment_MembersInjector.injectMProgressDialogBuilder(baseFicheFragment, progressDialogBuilder());
            return baseFicheFragment;
        }

        
        private BaseDrawerActivity injectBaseDrawerActivity(BaseDrawerActivity baseDrawerActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(baseDrawerActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(baseDrawerActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(baseDrawerActivity, alertDialogBuilder());
            BaseDrawerActivity_MembersInjector.injectMAlertDialogBuilder(baseDrawerActivity, alertDialogBuilder());
            return baseDrawerActivity;
        }

        
        private CustomerActivity injectCustomerActivity(CustomerActivity customerActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(customerActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(customerActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(customerActivity, alertDialogBuilder());
            return customerActivity;
        }

        
        private CustomerNewActivity injectCustomerNewActivity(CustomerNewActivity customerNewActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(customerNewActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(customerNewActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(customerNewActivity, alertDialogBuilder());
            return customerNewActivity;
        }

        
        private CustomerNewFragment injectCustomerNewFragment(CustomerNewFragment customerNewFragment) {
            BaseFicheFragment_MembersInjector.injectMAlertDialogBuilder(customerNewFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMArrayAlertDialogBuilder(customerNewFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMDateAlertDialogBuilder(customerNewFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMSqlManager(customerNewFragment, this.provideSqlManagerProvider.get());
            BaseFicheFragment_MembersInjector.injectMProgressDialogBuilder(customerNewFragment, progressDialogBuilder());
            return customerNewFragment;
        }

        
        private CustomerListActivity injectCustomerListActivity(CustomerListActivity customerListActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(customerListActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(customerListActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(customerListActivity, alertDialogBuilder());
            BaseDrawerActivity_MembersInjector.injectMAlertDialogBuilder(customerListActivity, alertDialogBuilder());
            BaseListDrawerActivity_MembersInjector.injectMKeyDelegate(customerListActivity, this.provideKeyDelegateProvider.get());
            BaseListDrawerActivity_MembersInjector.injectMActionViewResolver(customerListActivity, this.provideActionViewResolverProvider.get());
            return customerListActivity;
        }

        
        private KeyValueAdapter injectKeyValueAdapter(KeyValueAdapter keyValueAdapter) {
            KeyValueAdapter_MembersInjector.injectMAlertDialogBuilder(keyValueAdapter, alertDialogBuilder());
            return keyValueAdapter;
        }

        
        private TransferActivity injectTransferActivity(TransferActivity transferActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(transferActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(transferActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(transferActivity, alertDialogBuilder());
            TransferActivity_MembersInjector.injectMSharedPreferencesHelper(transferActivity, this.provideLogoSharedPrerencesProvider.get());
            TransferActivity_MembersInjector.injectMAlertDialogBuilder(transferActivity, alertDialogBuilder());
            TransferActivity_MembersInjector.injectMDeleteDataAlertDialogBuilder(transferActivity, alertDialogBuilder());
            TransferActivity_MembersInjector.injectMDeleteDataConfirmDialogBuilder(transferActivity, alertDialogBuilder());
            TransferActivity_MembersInjector.injectMUnsentCabinAlertDialogBuilder(transferActivity, alertDialogBuilder());
            return transferActivity;
        }

        
        private TransferSendActivity injectTransferSendActivity(TransferSendActivity transferSendActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(transferSendActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(transferSendActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(transferSendActivity, alertDialogBuilder());
            TransferSendActivity_MembersInjector.injectMAlertDialogBuilder(transferSendActivity, alertDialogBuilder());
            TransferSendActivity_MembersInjector.injectMBaseErp(transferSendActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            TransferSendActivity_MembersInjector.injectMSqlManager(transferSendActivity, this.provideSqlManagerProvider.get());
            TransferSendActivity_MembersInjector.injectMProgressDialogBuilder(transferSendActivity, progressDialogBuilder());
            return transferSendActivity;
        }

        
        private BaseErpActivityPreferences injectBaseErpActivityPreferences(BaseErpActivityPreferences baseErpActivityPreferences) {
            BaseErpActivityPreferences_MembersInjector.injectBaseErp(baseErpActivityPreferences, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            return baseErpActivityPreferences;
        }

        
        private CustomerInfoActivity injectCustomerInfoActivity(CustomerInfoActivity customerInfoActivity) {
            BaseErpActivityPreferences_MembersInjector.injectBaseErp(customerInfoActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            return customerInfoActivity;
        }

        
        private SalesListActivity injectSalesListActivity(SalesListActivity salesListActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(salesListActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(salesListActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(salesListActivity, alertDialogBuilder());
            return salesListActivity;
        }

        
        private DistributionListActivity injectDistributionListActivity(DistributionListActivity distributionListActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(distributionListActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(distributionListActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(distributionListActivity, alertDialogBuilder());
            return distributionListActivity;
        }

        
        private DailyFicheListActivity injectDailyFicheListActivity(DailyFicheListActivity dailyFicheListActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(dailyFicheListActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(dailyFicheListActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(dailyFicheListActivity, alertDialogBuilder());
            return dailyFicheListActivity;
        }

        
        private TodoListActivity injectTodoListActivity(TodoListActivity todoListActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(todoListActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(todoListActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(todoListActivity, alertDialogBuilder());
            return todoListActivity;
        }

        
        private TodoReadActivity injectTodoReadActivity(TodoReadActivity todoReadActivity) {
            BaseErpActivityPreferences_MembersInjector.injectBaseErp(todoReadActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            return todoReadActivity;
        }

        
        private TransferGetActivity injectTransferGetActivity(TransferGetActivity transferGetActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(transferGetActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(transferGetActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(transferGetActivity, alertDialogBuilder());
            TransferGetActivity_MembersInjector.injectMSharedPreferencesHelper(transferGetActivity, this.provideLogoSharedPrerencesProvider.get());
            TransferGetActivity_MembersInjector.injectMAlertDialogBuilder(transferGetActivity, alertDialogBuilder());
            return transferGetActivity;
        }

        
        private SalesActivityNew injectSalesActivityNew(SalesActivityNew salesActivityNew) {
            BaseErpActivity_MembersInjector.injectBaseErp(salesActivityNew, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(salesActivityNew, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(salesActivityNew, alertDialogBuilder());
            BaseFicheActivity_MembersInjector.injectMAlertDialogBuilder(salesActivityNew, alertDialogBuilder());
            BaseFicheActivity_MembersInjector.injectMPhotoShowAlertDialogBuilder(salesActivityNew, alertDialogBuilder());
            return salesActivityNew;
        }

        
        private SalesHeaderFragment injectSalesHeaderFragment(SalesHeaderFragment salesHeaderFragment) {
            BaseFicheFragment_MembersInjector.injectMAlertDialogBuilder(salesHeaderFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMArrayAlertDialogBuilder(salesHeaderFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMDateAlertDialogBuilder(salesHeaderFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMSqlManager(salesHeaderFragment, this.provideSqlManagerProvider.get());
            BaseFicheFragment_MembersInjector.injectMProgressDialogBuilder(salesHeaderFragment, progressDialogBuilder());
            return salesHeaderFragment;
        }

        
        private SalesLogisticFragment injectSalesLogisticFragment(SalesLogisticFragment salesLogisticFragment) {
            BaseFicheFragment_MembersInjector.injectMAlertDialogBuilder(salesLogisticFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMArrayAlertDialogBuilder(salesLogisticFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMDateAlertDialogBuilder(salesLogisticFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMSqlManager(salesLogisticFragment, this.provideSqlManagerProvider.get());
            BaseFicheFragment_MembersInjector.injectMProgressDialogBuilder(salesLogisticFragment, progressDialogBuilder());
            return salesLogisticFragment;
        }

        
        private SalesPaymentFragment injectSalesPaymentFragment(SalesPaymentFragment salesPaymentFragment) {
            BaseFicheFragment_MembersInjector.injectMAlertDialogBuilder(salesPaymentFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMArrayAlertDialogBuilder(salesPaymentFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMDateAlertDialogBuilder(salesPaymentFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMSqlManager(salesPaymentFragment, this.provideSqlManagerProvider.get());
            BaseFicheFragment_MembersInjector.injectMProgressDialogBuilder(salesPaymentFragment, progressDialogBuilder());
            return salesPaymentFragment;
        }

        
        private SalesDetailActivity injectSalesDetailActivity(SalesDetailActivity salesDetailActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(salesDetailActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(salesDetailActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(salesDetailActivity, alertDialogBuilder());
            BaseFicheActivity_MembersInjector.injectMAlertDialogBuilder(salesDetailActivity, alertDialogBuilder());
            BaseFicheActivity_MembersInjector.injectMPhotoShowAlertDialogBuilder(salesDetailActivity, alertDialogBuilder());
            return salesDetailActivity;
        }

        
        private SalesDetailFragment injectSalesDetailFragment(SalesDetailFragment salesDetailFragment) {
            BaseFicheFragment_MembersInjector.injectMAlertDialogBuilder(salesDetailFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMArrayAlertDialogBuilder(salesDetailFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMDateAlertDialogBuilder(salesDetailFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMSqlManager(salesDetailFragment, this.provideSqlManagerProvider.get());
            BaseFicheFragment_MembersInjector.injectMProgressDialogBuilder(salesDetailFragment, progressDialogBuilder());
            return salesDetailFragment;
        }

        
        private SalesOrderListActivity injectSalesOrderListActivity(SalesOrderListActivity salesOrderListActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(salesOrderListActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(salesOrderListActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(salesOrderListActivity, alertDialogBuilder());
            return salesOrderListActivity;
        }

        
        private SalesVariantActivity injectSalesVariantActivity(SalesVariantActivity salesVariantActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(salesVariantActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(salesVariantActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(salesVariantActivity, alertDialogBuilder());
            return salesVariantActivity;
        }

        
        private EDispatchExtraInfoActivity injectEDispatchExtraInfoActivity(EDispatchExtraInfoActivity eDispatchExtraInfoActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(eDispatchExtraInfoActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(eDispatchExtraInfoActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(eDispatchExtraInfoActivity, alertDialogBuilder());
            return eDispatchExtraInfoActivity;
        }

        
        private DriverInformationActivity injectDriverInformationActivity(DriverInformationActivity driverInformationActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(driverInformationActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(driverInformationActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(driverInformationActivity, alertDialogBuilder());
            return driverInformationActivity;
        }

        
        private BarcodeScannerProductViewActivity injectBarcodeScannerProductViewActivity(BarcodeScannerProductViewActivity barcodeScannerProductViewActivity) {
            BarcodeScannerProductViewActivity_MembersInjector.injectMAlertDialogBuilder(barcodeScannerProductViewActivity, alertDialogBuilder());
            BarcodeScannerProductViewActivity_MembersInjector.injectSqlManager(barcodeScannerProductViewActivity, this.provideSqlManagerProvider.get());
            BarcodeScannerProductViewActivity_MembersInjector.injectMBaseErp(barcodeScannerProductViewActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            return barcodeScannerProductViewActivity;
        }

        
        private BarcodeScannerView injectBarcodeScannerView(BarcodeScannerView barcodeScannerView) {
            BarcodeScannerView_MembersInjector.injectMAlertDialogBuilder(barcodeScannerView, alertDialogBuilder());
            BarcodeScannerView_MembersInjector.injectMProgressDialogBuilder(barcodeScannerView, progressDialogBuilder());
            BarcodeScannerView_MembersInjector.injectSqlManager(barcodeScannerView, this.provideSqlManagerProvider.get());
            BarcodeScannerView_MembersInjector.injectMBaseErp(barcodeScannerView, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            return barcodeScannerView;
        }

        
        private BarcodeScannerViewRecyclerViewAdapter injectBarcodeScannerViewRecyclerViewAdapter(BarcodeScannerViewRecyclerViewAdapter barcodeScannerViewRecyclerViewAdapter) {
            BarcodeScannerViewRecyclerViewAdapter_MembersInjector.injectBaseErp(barcodeScannerViewRecyclerViewAdapter, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BarcodeScannerViewRecyclerViewAdapter_MembersInjector.injectMProgressDialogBuilder(barcodeScannerViewRecyclerViewAdapter, progressDialogBuilder());
            BarcodeScannerViewRecyclerViewAdapter_MembersInjector.injectMAlertDialogBuilder(barcodeScannerViewRecyclerViewAdapter, alertDialogBuilder());
            return barcodeScannerViewRecyclerViewAdapter;
        }

        
        private SalesSerialGroupActivity injectSalesSerialGroupActivity(SalesSerialGroupActivity salesSerialGroupActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(salesSerialGroupActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(salesSerialGroupActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(salesSerialGroupActivity, alertDialogBuilder());
            return salesSerialGroupActivity;
        }

        
        private SalesMansListActivity injectSalesMansListActivity(SalesMansListActivity salesMansListActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(salesMansListActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(salesMansListActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(salesMansListActivity, alertDialogBuilder());
            return salesMansListActivity;
        }

        
        private ImageViewActivity injectImageViewActivity(ImageViewActivity imageViewActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(imageViewActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(imageViewActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(imageViewActivity, alertDialogBuilder());
            ImageViewActivity_MembersInjector.injectMProgressDialogBuilder(imageViewActivity, progressDialogBuilder());
            return imageViewActivity;
        }

        
        private ImageDialogActivity injectImageDialogActivity(ImageDialogActivity imageDialogActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(imageDialogActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(imageDialogActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(imageDialogActivity, alertDialogBuilder());
            ImageDialogActivity_MembersInjector.injectMPhotoAlertDialogBuilder(imageDialogActivity, alertDialogBuilder());
            return imageDialogActivity;
        }

        
        private PenetrationActivity injectPenetrationActivity(PenetrationActivity penetrationActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(penetrationActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(penetrationActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(penetrationActivity, alertDialogBuilder());
            BaseFicheActivity_MembersInjector.injectMAlertDialogBuilder(penetrationActivity, alertDialogBuilder());
            BaseFicheActivity_MembersInjector.injectMPhotoShowAlertDialogBuilder(penetrationActivity, alertDialogBuilder());
            return penetrationActivity;
        }

        
        private ReportAllActivity injectReportAllActivity(ReportAllActivity reportAllActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(reportAllActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(reportAllActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(reportAllActivity, alertDialogBuilder());
            return reportAllActivity;
        }

        
        private ReportWCFSalesOrdInvActivity injectReportWCFSalesOrdInvActivity(ReportWCFSalesOrdInvActivity reportWCFSalesOrdInvActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(reportWCFSalesOrdInvActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(reportWCFSalesOrdInvActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(reportWCFSalesOrdInvActivity, alertDialogBuilder());
            return reportWCFSalesOrdInvActivity;
        }

        
        private ReportExtractActivity injectReportExtractActivity(ReportExtractActivity reportExtractActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(reportExtractActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(reportExtractActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(reportExtractActivity, alertDialogBuilder());
            return reportExtractActivity;
        }

        
        private CustomerRouteListActivity injectCustomerRouteListActivity(CustomerRouteListActivity customerRouteListActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(customerRouteListActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(customerRouteListActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(customerRouteListActivity, alertDialogBuilder());
            return customerRouteListActivity;
        }

        
        private LastOrderProductsActivity injectLastOrderProductsActivity(LastOrderProductsActivity lastOrderProductsActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(lastOrderProductsActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(lastOrderProductsActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(lastOrderProductsActivity, alertDialogBuilder());
            return lastOrderProductsActivity;
        }

        
        private UserReportsParametersActivity injectUserReportsParametersActivity(UserReportsParametersActivity userReportsParametersActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(userReportsParametersActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(userReportsParametersActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(userReportsParametersActivity, alertDialogBuilder());
            return userReportsParametersActivity;
        }

        
        private UserReportsActivity injectUserReportsActivity(UserReportsActivity userReportsActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(userReportsActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(userReportsActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(userReportsActivity, alertDialogBuilder());
            return userReportsActivity;
        }

        
        private SalesCashAndCreditHeaderEnterActivity injectSalesCashAndCreditHeaderEnterActivity(SalesCashAndCreditHeaderEnterActivity salesCashAndCreditHeaderEnterActivity) {
            BaseErpActivityPreferences_MembersInjector.injectBaseErp(salesCashAndCreditHeaderEnterActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            return salesCashAndCreditHeaderEnterActivity;
        }

        
        private SalesCaseCashHeaderEnterActivity injectSalesCaseCashHeaderEnterActivity(SalesCaseCashHeaderEnterActivity salesCaseCashHeaderEnterActivity) {
            BaseErpActivityPreferences_MembersInjector.injectBaseErp(salesCaseCashHeaderEnterActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            return salesCaseCashHeaderEnterActivity;
        }

        
        private SalesChequeAndDeedHeaderEnterActivity injectSalesChequeAndDeedHeaderEnterActivity(SalesChequeAndDeedHeaderEnterActivity salesChequeAndDeedHeaderEnterActivity) {
            BaseErpActivityPreferences_MembersInjector.injectBaseErp(salesChequeAndDeedHeaderEnterActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            return salesChequeAndDeedHeaderEnterActivity;
        }

        
        private ReceiptFicheListActivity injectReceiptFicheListActivity(ReceiptFicheListActivity receiptFicheListActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(receiptFicheListActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(receiptFicheListActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(receiptFicheListActivity, alertDialogBuilder());
            return receiptFicheListActivity;
        }

        
        private CashAndCreditCardFicheActivity injectCashAndCreditCardFicheActivity(CashAndCreditCardFicheActivity cashAndCreditCardFicheActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(cashAndCreditCardFicheActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(cashAndCreditCardFicheActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(cashAndCreditCardFicheActivity, alertDialogBuilder());
            return cashAndCreditCardFicheActivity;
        }

        
        private CashCreditFicheHeaderFragment injectCashCreditFicheHeaderFragment(CashCreditFicheHeaderFragment cashCreditFicheHeaderFragment) {
            BaseFicheFragment_MembersInjector.injectMAlertDialogBuilder(cashCreditFicheHeaderFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMArrayAlertDialogBuilder(cashCreditFicheHeaderFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMDateAlertDialogBuilder(cashCreditFicheHeaderFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMSqlManager(cashCreditFicheHeaderFragment, this.provideSqlManagerProvider.get());
            BaseFicheFragment_MembersInjector.injectMProgressDialogBuilder(cashCreditFicheHeaderFragment, progressDialogBuilder());
            return cashCreditFicheHeaderFragment;
        }

        
        private CaseFicheActivity injectCaseFicheActivity(CaseFicheActivity caseFicheActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(caseFicheActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(caseFicheActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(caseFicheActivity, alertDialogBuilder());
            return caseFicheActivity;
        }

        
        private ChequeAndDeedFicheActivity injectChequeAndDeedFicheActivity(ChequeAndDeedFicheActivity chequeAndDeedFicheActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(chequeAndDeedFicheActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(chequeAndDeedFicheActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(chequeAndDeedFicheActivity, alertDialogBuilder());
            return chequeAndDeedFicheActivity;
        }

        
        private ChequeDeedFicheHeaderFragment injectChequeDeedFicheHeaderFragment(ChequeDeedFicheHeaderFragment chequeDeedFicheHeaderFragment) {
            BaseFicheFragment_MembersInjector.injectMAlertDialogBuilder(chequeDeedFicheHeaderFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMArrayAlertDialogBuilder(chequeDeedFicheHeaderFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMDateAlertDialogBuilder(chequeDeedFicheHeaderFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMSqlManager(chequeDeedFicheHeaderFragment, this.provideSqlManagerProvider.get());
            BaseFicheFragment_MembersInjector.injectMProgressDialogBuilder(chequeDeedFicheHeaderFragment, progressDialogBuilder());
            return chequeDeedFicheHeaderFragment;
        }

        
        private StartInfoEnterActivity injectStartInfoEnterActivity(StartInfoEnterActivity startInfoEnterActivity) {
            BaseErpActivityPreferences_MembersInjector.injectBaseErp(startInfoEnterActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            return startInfoEnterActivity;
        }

        
        private VisitEnterActivityNew injectVisitEnterActivityNew(VisitEnterActivityNew visitEnterActivityNew) {
            BaseErpActivity_MembersInjector.injectBaseErp(visitEnterActivityNew, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(visitEnterActivityNew, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(visitEnterActivityNew, alertDialogBuilder());
            BaseFicheActivity_MembersInjector.injectMAlertDialogBuilder(visitEnterActivityNew, alertDialogBuilder());
            BaseFicheActivity_MembersInjector.injectMPhotoShowAlertDialogBuilder(visitEnterActivityNew, alertDialogBuilder());
            return visitEnterActivityNew;
        }

        
        private VisitEnterFragment injectVisitEnterFragment(VisitEnterFragment visitEnterFragment) {
            BaseFicheFragment_MembersInjector.injectMAlertDialogBuilder(visitEnterFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMArrayAlertDialogBuilder(visitEnterFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMDateAlertDialogBuilder(visitEnterFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMSqlManager(visitEnterFragment, this.provideSqlManagerProvider.get());
            BaseFicheFragment_MembersInjector.injectMProgressDialogBuilder(visitEnterFragment, progressDialogBuilder());
            return visitEnterFragment;
        }

        
        private SettingsActivity injectSettingsActivity(SettingsActivity settingsActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(settingsActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(settingsActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(settingsActivity, alertDialogBuilder());
            return settingsActivity;
        }

        
        private CashCreditFicheDetailEnterFragment injectCashCreditFicheDetailEnterFragment(CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment) {
            BaseFicheFragment_MembersInjector.injectMAlertDialogBuilder(cashCreditFicheDetailEnterFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMArrayAlertDialogBuilder(cashCreditFicheDetailEnterFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMDateAlertDialogBuilder(cashCreditFicheDetailEnterFragment, alertDialogBuilder());
            BaseFicheFragment_MembersInjector.injectMSqlManager(cashCreditFicheDetailEnterFragment, this.provideSqlManagerProvider.get());
            BaseFicheFragment_MembersInjector.injectMProgressDialogBuilder(cashCreditFicheDetailEnterFragment, progressDialogBuilder());
            return cashCreditFicheDetailEnterFragment;
        }

        
        private CustomerCurrencyInfoActivity injectCustomerCurrencyInfoActivity(CustomerCurrencyInfoActivity customerCurrencyInfoActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(customerCurrencyInfoActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(customerCurrencyInfoActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(customerCurrencyInfoActivity, alertDialogBuilder());
            return customerCurrencyInfoActivity;
        }

        
        private ItemReportsActivity injectItemReportsActivity(ItemReportsActivity itemReportsActivity) {
            BaseErpActivity_MembersInjector.injectBaseErp(itemReportsActivity, ActivityModule_ProvideBaseErpFactory.provideBaseErp(this.activityModule));
            BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(itemReportsActivity, progressDialogBuilder());
            BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(itemReportsActivity, alertDialogBuilder());
            return itemReportsActivity;
        }
    }

    private static final class CommunicationComponentImpl implements CommunicationComponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final CommunicationComponentImpl communicationComponentImpl;
        private Provider<Dispatcher> provideDispatcherProvider;
        private Provider<Context> provideGetContextProvider;
        private Provider<Call.Factory> provideNetsisCallFactoryProvider;
        private Provider<Call.Factory> provideNetsisPublicCallFactoryProvider;
        private Provider<NetsisRestServiceFactory> provideRestServiceFactoryProvider;
        private Provider<NetsisRestPublicFactory> provideRestServicePublicFactoryProvider;
        private Provider<NetsisRestTokenApi> provideRestTokenApiProvider;
        private Provider<Call.Factory> provideWcfCallFactoryProvider;
        private Provider<WcfServiceFactory> provideWcfServiceFactoryProvider;

        private CommunicationComponentImpl(ApplicationComponentImpl applicationComponentImpl, CommunicationModule communicationModule) {
            this.communicationComponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(communicationModule);
        }

        private void initialize(CommunicationModule communicationModule) {
            this.provideGetContextProvider = DoubleCheck.provider(CommunicationModule_ProvideGetContextFactory.create(communicationModule));
            Provider<Call.Factory> provider = DoubleCheck.provider(CommunicationModule_ProvideWcfCallFactoryFactory.create(communicationModule));
            this.provideWcfCallFactoryProvider = provider;
            this.provideWcfServiceFactoryProvider = DoubleCheck.provider(CommunicationModule_ProvideWcfServiceFactoryFactory.create(communicationModule, provider));
            this.provideRestTokenApiProvider = DoubleCheck.provider(CommunicationModule_ProvideRestTokenApiFactory.create(communicationModule));
            Provider<Dispatcher> provider2 = DoubleCheck.provider(CommunicationModule_ProvideDispatcherFactory.create(communicationModule));
            this.provideDispatcherProvider = provider2;
            Provider<Call.Factory> provider3 = DoubleCheck.provider(CommunicationModule_ProvideNetsisCallFactoryFactory.create(communicationModule, this.provideRestTokenApiProvider, provider2));
            this.provideNetsisCallFactoryProvider = provider3;
            this.provideRestServiceFactoryProvider = DoubleCheck.provider(CommunicationModule_ProvideRestServiceFactoryFactory.create(communicationModule, provider3));
            Provider<Call.Factory> provider4 = DoubleCheck.provider(CommunicationModule_ProvideNetsisPublicCallFactoryFactory.create(communicationModule));
            this.provideNetsisPublicCallFactoryProvider = provider4;
            this.provideRestServicePublicFactoryProvider = DoubleCheck.provider(CommunicationModule_ProvideRestServicePublicFactoryFactory.create(communicationModule, provider4));
        }

        @Override // com.proje.mobilesales.core.interfaces.p011di.CommunicationComponent
        public void inject(TigerWcfAsyncTask tigerWcfAsyncTask) {
            injectTigerWcfAsyncTask(tigerWcfAsyncTask);
        }

        @Override // com.proje.mobilesales.core.interfaces.p011di.CommunicationComponent
        public void inject(NetsisRestAsyncTask netsisRestAsyncTask) {
            injectNetsisRestAsyncTask(netsisRestAsyncTask);
        }

        
        private TigerWcfAsyncTask injectTigerWcfAsyncTask(TigerWcfAsyncTask tigerWcfAsyncTask) {
            BaseCommunication_MembersInjector.injectMContext(tigerWcfAsyncTask, this.provideGetContextProvider.get());
            TigerWcfAsyncTask_MembersInjector.injectFactory(tigerWcfAsyncTask, this.provideWcfServiceFactoryProvider.get());
            return tigerWcfAsyncTask;
        }

        
        private NetsisRestAsyncTask injectNetsisRestAsyncTask(NetsisRestAsyncTask netsisRestAsyncTask) {
            BaseCommunication_MembersInjector.injectMContext(netsisRestAsyncTask, this.provideGetContextProvider.get());
            NetsisRestAsyncTask_MembersInjector.injectMFactory(netsisRestAsyncTask, this.provideRestServiceFactoryProvider.get());
            NetsisRestAsyncTask_MembersInjector.injectMPublicFactory(netsisRestAsyncTask, this.provideRestServicePublicFactoryProvider.get());
            return netsisRestAsyncTask;
        }
    }

    private static final class ErpComponentImpl implements ErpComponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final ErpComponentImpl erpComponentImpl;
        private Provider<ErpRights> provideErpRightProvider;
        private Provider<UserMenuRights> provideUserMenuRightsProvider;
        private Provider<User> provideUserProvider;
        private Provider<UserSettings> provideUserSettingsProvider;

        private ErpComponentImpl(ApplicationComponentImpl applicationComponentImpl, ErpModule erpModule) {
            this.erpComponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(erpModule);
        }

        private void initialize(ErpModule erpModule) {
            this.provideUserProvider = DoubleCheck.provider(ErpModule_ProvideUserFactory.create(erpModule));
            this.provideUserMenuRightsProvider = DoubleCheck.provider(ErpModule_ProvideUserMenuRightsFactory.create(erpModule));
            this.provideErpRightProvider = DoubleCheck.provider(ErpModule_ProvideErpRightFactory.create(erpModule));
            this.provideUserSettingsProvider = DoubleCheck.provider(ErpModule_ProvideUserSettingsFactory.create(erpModule));
        }

        @Override // com.proje.mobilesales.core.interfaces.p011di.ErpComponent
        public void inject(Netsis netsis) {
            injectNetsis(netsis);
        }

        @Override // com.proje.mobilesales.core.interfaces.p011di.ErpComponent
        public void inject(Tiger tiger) {
            injectTiger(tiger);
        }

        
        private Netsis injectNetsis(Netsis netsis) {
            BaseErp_MembersInjector.injectUser(netsis, this.provideUserProvider.get());
            BaseErp_MembersInjector.injectUserMenuRights(netsis, this.provideUserMenuRightsProvider.get());
            BaseErp_MembersInjector.injectErpRights(netsis, this.provideErpRightProvider.get());
            BaseErp_MembersInjector.injectMUserSettings(netsis, this.provideUserSettingsProvider.get());
            return netsis;
        }

        
        private Tiger injectTiger(Tiger tiger) {
            BaseErp_MembersInjector.injectUser(tiger, this.provideUserProvider.get());
            BaseErp_MembersInjector.injectUserMenuRights(tiger, this.provideUserMenuRightsProvider.get());
            BaseErp_MembersInjector.injectErpRights(tiger, this.provideErpRightProvider.get());
            BaseErp_MembersInjector.injectMUserSettings(tiger, this.provideUserSettingsProvider.get());
            return tiger;
        }
    }

    private static final class ApplicationComponentImpl implements ApplicationComponent {
        private final ApplicationComponentImpl applicationComponentImpl;

        private ApplicationComponentImpl() {
            this.applicationComponentImpl = this;
        }

        @Override // com.proje.mobilesales.core.interfaces.p011di.ApplicationComponent
        public ActivityComponent plus(ActivityModule activityModule) {
            Preconditions.checkNotNull(activityModule);
            return new ActivityComponentImpl(this.applicationComponentImpl, activityModule);
        }

        @Override // com.proje.mobilesales.core.interfaces.p011di.ApplicationComponent
        public CommunicationComponent plus(CommunicationModule communicationModule) {
            Preconditions.checkNotNull(communicationModule);
            return new CommunicationComponentImpl(this.applicationComponentImpl, communicationModule);
        }

        @Override // com.proje.mobilesales.core.interfaces.p011di.ApplicationComponent
        public ErpComponent plus(ErpModule erpModule) {
            Preconditions.checkNotNull(erpModule);
            return new ErpComponentImpl(this.applicationComponentImpl, erpModule);
        }
    }
}
