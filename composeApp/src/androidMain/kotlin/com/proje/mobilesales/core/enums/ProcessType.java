package com.proje.mobilesales.core.enums;

import androidx.annotation.StringRes;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.netsis.sendmodel.edocument.ShowEDocumentParam;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintExtraInfo;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.tigerwcf.RESULTXML;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.Cabin;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.CabinTrans;
import com.proje.mobilesales.features.customer.model.CustomerBalance;
import com.proje.mobilesales.features.customer.model.CustomerBeforeBalance;
import com.proje.mobilesales.features.customer.model.CustomerCampaignPoint;
import com.proje.mobilesales.features.customer.model.CustomerRisk;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.model.database.ClCardIncharge;
import com.proje.mobilesales.features.customer.model.database.ClRisk;
import com.proje.mobilesales.features.customer.model.database.CustomerDetailedRestriction;
import com.proje.mobilesales.features.customer.model.database.CustomerRiskLimit;
import com.proje.mobilesales.features.dbmodel.AddTax;
import com.proje.mobilesales.features.dbmodel.Bank;
import com.proje.mobilesales.features.dbmodel.BankAccount;
import com.proje.mobilesales.features.dbmodel.Branch;
import com.proje.mobilesales.features.dbmodel.City;
import com.proje.mobilesales.features.dbmodel.Country;
import com.proje.mobilesales.features.dbmodel.CreditAggr;
import com.proje.mobilesales.features.dbmodel.Curr;
import com.proje.mobilesales.features.dbmodel.CurrType;
import com.proje.mobilesales.features.dbmodel.CustomerImage;
import com.proje.mobilesales.features.dbmodel.DefOrder;
import com.proje.mobilesales.features.dbmodel.DefOrderDetail;
import com.proje.mobilesales.features.dbmodel.DesignFile;
import com.proje.mobilesales.features.dbmodel.DesignJson;
import com.proje.mobilesales.features.dbmodel.Discount;
import com.proje.mobilesales.features.dbmodel.Division;
import com.proje.mobilesales.features.dbmodel.EDocSerial;
import com.proje.mobilesales.features.dbmodel.EmailParam;
import com.proje.mobilesales.features.dbmodel.EmailTemplate;
import com.proje.mobilesales.features.dbmodel.Factory;
import com.proje.mobilesales.features.dbmodel.Firm;
import com.proje.mobilesales.features.dbmodel.GroupCode;
import com.proje.mobilesales.features.dbmodel.LastTransferDate;
import com.proje.mobilesales.features.dbmodel.LocTracking;
import com.proje.mobilesales.features.dbmodel.LocationStockTracking;
import com.proje.mobilesales.features.dbmodel.LogoParam;
import com.proje.mobilesales.features.dbmodel.ManagerOrder;
import com.proje.mobilesales.features.dbmodel.MarketingMessage;
import com.proje.mobilesales.features.dbmodel.MuhRefCodes;
import com.proje.mobilesales.features.dbmodel.OrderAvailableAmount;
import com.proje.mobilesales.features.dbmodel.OrderToFicheTotal;
import com.proje.mobilesales.features.dbmodel.OrderView;
import com.proje.mobilesales.features.dbmodel.Param;
import com.proje.mobilesales.features.dbmodel.Payment;
import com.proje.mobilesales.features.dbmodel.PaymentLine;
import com.proje.mobilesales.features.dbmodel.Price;
import com.proje.mobilesales.features.dbmodel.Project;
import com.proje.mobilesales.features.dbmodel.Route;
import com.proje.mobilesales.features.dbmodel.Routetr;
import com.proje.mobilesales.features.dbmodel.Service;
import com.proje.mobilesales.features.dbmodel.ServiceUnit;
import com.proje.mobilesales.features.dbmodel.ShipAddress;
import com.proje.mobilesales.features.dbmodel.ShipAgent;
import com.proje.mobilesales.features.dbmodel.ShipType;
import com.proje.mobilesales.features.dbmodel.SlsClRel;
import com.proje.mobilesales.features.dbmodel.Specodes;
import com.proje.mobilesales.features.dbmodel.SpecodesPrices;
import com.proje.mobilesales.features.dbmodel.Stcompln;
import com.proje.mobilesales.features.dbmodel.SuppAsgn;
import com.proje.mobilesales.features.dbmodel.Town;
import com.proje.mobilesales.features.dbmodel.Tradinggrp;
import com.proje.mobilesales.features.dbmodel.UnitBarcode;
import com.proje.mobilesales.features.dbmodel.Units;
import com.proje.mobilesales.features.dbmodel.User;
import com.proje.mobilesales.features.dbmodel.UserCase;
import com.proje.mobilesales.features.dbmodel.UserInfo;
import com.proje.mobilesales.features.dbmodel.UserParam;
import com.proje.mobilesales.features.dbmodel.Variant;
import com.proje.mobilesales.features.dbmodel.VariantStock;
import com.proje.mobilesales.features.dbmodel.VariantUnit;
import com.proje.mobilesales.features.dbmodel.WareHouse;
import com.proje.mobilesales.features.dbmodel.WorRoute;
import com.proje.mobilesales.features.dbmodel.WorRouteDay;
import com.proje.mobilesales.features.dbmodel.WorRoutePlan;
import com.proje.mobilesales.features.dbmodel.WorUserCustomers;
import com.proje.mobilesales.features.gpsinfo.model.database.CustGpsInfo;
import com.proje.mobilesales.features.model.ActivePeriod;
import com.proje.mobilesales.features.model.CheckFDateModel;
import com.proje.mobilesales.features.model.CheckSeriGroup;
import com.proje.mobilesales.features.model.CurrencyBalanceModel;
import com.proje.mobilesales.features.model.DocNo;
import com.proje.mobilesales.features.model.DueDateTotal;
import com.proje.mobilesales.features.model.EDespatchPdfHeader;
import com.proje.mobilesales.features.model.EDocumentPdfDetail;
import com.proje.mobilesales.features.model.EDocumentPdfDetailNetsis;
import com.proje.mobilesales.features.model.EDocumentPdfHeaderNetsis;
import com.proje.mobilesales.features.model.EInvoicePdfHeader;
import com.proje.mobilesales.features.model.FicheDuplicate;
import com.proje.mobilesales.features.model.GenericData;
import com.proje.mobilesales.features.model.Image;
import com.proje.mobilesales.features.model.KeyValuePair;
import com.proje.mobilesales.features.model.LowLevelCode;
import com.proje.mobilesales.features.model.MonthlyProductSales;
import com.proje.mobilesales.features.model.MonthlySales;
import com.proje.mobilesales.features.model.OrderFicheStatus;
import com.proje.mobilesales.features.model.OrderState;
import com.proje.mobilesales.features.model.PanelVersion;
import com.proje.mobilesales.features.model.PurchasePriceInfo;
import com.proje.mobilesales.features.model.RiskResult;
import com.proje.mobilesales.features.model.SerialLotModel;
import com.proje.mobilesales.features.model.SurplusDiscount;
import com.proje.mobilesales.features.model.TigerSqlModel;
import com.proje.mobilesales.features.model.TopProduct;
import com.proje.mobilesales.features.model.WarehouseItem;
import com.proje.mobilesales.features.model.WmsBarcodeModel;
import com.proje.mobilesales.features.model.WorTablesModel;
import com.proje.mobilesales.features.model.WorUserModel;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotificationUserSelectionModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import com.proje.mobilesales.features.penetration.model.database.Penetration;
import com.proje.mobilesales.features.penetration.model.database.PenetrationDetail;
import com.proje.mobilesales.features.penetration.model.database.UserMainPenetration;
import com.proje.mobilesales.features.penetration.model.database.UserPenetration;
import com.proje.mobilesales.features.product.model.database.Item;
import com.proje.mobilesales.features.product.model.database.ItemGroupCode;
import com.proje.mobilesales.features.product.model.database.ItemImage;
import com.proje.mobilesales.features.product.model.database.ItemPrice;
import com.proje.mobilesales.features.product.model.database.ItemSerialInfo;
import com.proje.mobilesales.features.product.model.database.ItemStock;
import com.proje.mobilesales.features.product.model.database.ItemTags;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import com.proje.mobilesales.features.product.model.database.PurchasePrice;
import com.proje.mobilesales.features.product.model.database.RecommendedProducts;
import com.proje.mobilesales.features.reports.view.activity.ReportWCFAllReportActivity;
import com.proje.mobilesales.features.sales.model.Distribution;
import com.proje.mobilesales.features.sales.model.SalesMan;
import com.proje.mobilesales.features.sales.model.UpdatedOrderStatus;
import com.proje.mobilesales.features.sales.model.database.LastOrderProducts;
import com.proje.mobilesales.features.sales.model.database.SalesView;
import com.proje.mobilesales.features.settings.model.DeviceId;
import com.proje.mobilesales.features.settings.model.Matter;
import com.proje.mobilesales.features.todo.model.database.TodoInfoDb;
import com.proje.mobilesales.features.userreport.model.database.UserReports;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import com.proje.mobilesales.features.visit.model.database.VisitReason;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.kxml2.wap.Wbxml;

public final class ProcessType extends Enum<ProcessType> {
    private static final  EnumEntries ENTRIES;
    private static final  ProcessType[] VALUES;
    public static final Companion Companion;
    private Class<?> aClass;
    public int resId;
    public static final ProcessType LOGIN = new ProcessType("LOGIN", 0, User.class, R.string.type_login);
    public static final ProcessType GETUSERDEVICE = new ProcessType("GETUSERDEVICE", 1, DeviceId.class, R.string.type_get_user_device);
    public static final ProcessType GETLISANSINFO = new ProcessType("GETLISANSINFO", 2, Param.class, R.string.type_get_licence_info);
    public static final ProcessType GETUSERPARAM = new ProcessType("GETUSERPARAM", 3, UserParam.class, R.string.type_get_user_param);
    public static final ProcessType SAVEDEVICEID = new ProcessType("SAVEDEVICEID", 4, String.class, R.string.type_save_device_id);
    public static final ProcessType GETUSERINFO = new ProcessType("GETUSERINFO", 5, UserInfo.class, R.string.type_get_user_info);
    public static final ProcessType GETLOGOTRADEPARAMS = new ProcessType("GETLOGOTRADEPARAMS", 6, LogoParam.class, R.string.type_get_logo_trade_param);
    public static final ProcessType GETCURRTYPE = new ProcessType("GETCURRTYPE", 7, CurrType.class, R.string.type_get_curr_type);
    public static final ProcessType GETCURRRATE = new ProcessType("GETCURRRATE", 8, Curr.class, R.string.type_get_curr_rate);
    public static final ProcessType GETITEM = new ProcessType("GETITEM", 9, Item.class, R.string.type_get_item);
    public static final ProcessType GETITEMNAME = new ProcessType("GETITEMNAME", 10, KeyValuePair.class, R.string.type_get_item);
    public static final ProcessType GETITEMIMAGE = new ProcessType("GETITEMIMAGE", 11, ItemImage.class, R.string.type_get_item_image);
    public static final ProcessType GETSERVICE = new ProcessType("GETSERVICE", 12, Service.class, R.string.type_get_service);
    public static final ProcessType GETPROJECT = new ProcessType("GETPROJECT", 13, Project.class, R.string.type_get_project);
    public static final ProcessType GETROUTE = new ProcessType("GETROUTE", 14, Route.class, R.string.type_get_route);
    public static final ProcessType GETCUSTOMERSALESMANRELATION = new ProcessType("GETCUSTOMERSALESMANRELATION", 15, SlsClRel.class, R.string.type_get_customer_salesman_relation);
    public static final ProcessType GETCUSTOMERGPSLOCATION = new ProcessType("GETCUSTOMERGPSLOCATION", 16, CustGpsInfo.class, R.string.type_get_customer_gps_location);
    public static final ProcessType GETROUTETRS = new ProcessType("GETROUTETRS", 17, Routetr.class, R.string.type_get_routes);
    public static final ProcessType GETITEMUNIT = new ProcessType("GETITEMUNIT", 18, ItemUnits.class, R.string.type_get_item_unit);
    public static final ProcessType GETSERVICEUNIT = new ProcessType("GETSERVICEUNIT", 19, ServiceUnit.class, R.string.type_get_service_unit);
    public static final ProcessType GETUNIT = new ProcessType("GETUNIT", 20, Units.class, R.string.type_get_unit);
    public static final ProcessType GETWAREHOUSE = new ProcessType("GETWAREHOUSE", 21, WareHouse.class, R.string.type_get_warehouse);
    public static final ProcessType GETDIVISION = new ProcessType("GETDIVISION", 22, Division.class, R.string.type_get_division);
    public static final ProcessType GETBRANCH = new ProcessType("GETBRANCH", 23, Branch.class, R.string.type_get_branch);
    public static final ProcessType GETFACTORY = new ProcessType("GETFACTORY", 24, Factory.class, R.string.type_get_factory);
    public static final ProcessType GETSPECODE = new ProcessType("GETSPECODE", 25, Specodes.class, R.string.type_get_specode);
    public static final ProcessType GETSPECODEPRICES = new ProcessType("GETSPECODEPRICES", 26, SpecodesPrices.class, 0);
    public static final ProcessType GETDISCOUNT = new ProcessType("GETDISCOUNT", 27, Discount.class, R.string.type_get_discount);
    public static final ProcessType GETPAYMENT = new ProcessType("GETPAYMENT", 28, Payment.class, R.string.type_get_payment);
    public static final ProcessType GETPAYMENTLINE = new ProcessType("GETPAYMENTLINE", 29, PaymentLine.class, R.string.type_get_payment_lines);
    public static final ProcessType GETTRADINGGROUP = new ProcessType("GETTRADINGGROUP", 30, Tradinggrp.class, R.string.type_get_trading_group);
    public static final ProcessType GETSHIPTYPE = new ProcessType("GETSHIPTYPE", 31, ShipType.class, R.string.type_get_ship_type);
    public static final ProcessType GETSHIPAGENT = new ProcessType("GETSHIPAGENT", 32, ShipAgent.class, R.string.type_get_ship_agent);
    public static final ProcessType GETBANK = new ProcessType("GETBANK", 33, Bank.class, R.string.type_get_bank);
    public static final ProcessType GETBANKACCOUNT = new ProcessType("GETBANKACCOUNT", 34, BankAccount.class, R.string.type_get_bank_account);
    public static final ProcessType GETVISITREASON = new ProcessType("GETVISITREASON", 35, VisitReason.class, R.string.type_get_visit_reason);
    public static final ProcessType GETTODO = new ProcessType("GETTODO", 36, TodoInfoDb.class, R.string.type_get_todo);
    public static final ProcessType GETMARKETINGMESSAGE = new ProcessType("GETMARKETINGMESSAGE", 37, MarketingMessage.class, R.string.type_get_marketing_message);
    public static final ProcessType GETDEFORDER = new ProcessType("GETDEFORDER", 38, DefOrder.class, R.string.type_get_def_order);
    public static final ProcessType GETDEFORDERDETAIL = new ProcessType("GETDEFORDERDETAIL", 39, DefOrderDetail.class, R.string.type_get_def_order_detail);
    public static final ProcessType GETPENETRATION = new ProcessType("GETPENETRATION", 40, Penetration.class, R.string.type_get_penetration);
    public static final ProcessType GETPENETRATIONDETAIL = new ProcessType("GETPENETRATIONDETAIL", 41, PenetrationDetail.class, R.string.type_get_penetration_detail);
    public static final ProcessType GETCASE = new ProcessType("GETCASE", 42, UserCase.class, R.string.type_get_user_case);
    public static final ProcessType GETDESFILE = new ProcessType("GETDESFILE", 43, DesignFile.class, R.string.type_get_design_file);
    public static final ProcessType GETDESFILESTRJSON = new ProcessType("GETDESFILESTRJSON", 44, DesignJson.class, R.string.type_get_design_file_json);
    public static final ProcessType GETEMAILPARAM = new ProcessType("GETEMAILPARAM", 45, EmailParam.class, R.string.type_get_email_param);
    public static final ProcessType GETEMAILTEMPLATE = new ProcessType("GETEMAILTEMPLATE", 46, EmailTemplate.class, R.string.type_get_email_templates);
    public static final ProcessType GETREPORTS = new ProcessType("GETREPORTS", 47, UserReports.class, R.string.type_get_reports);
    public static final ProcessType GETVARIANT = new ProcessType("GETVARIANT", 48, Variant.class, R.string.type_get_variant);
    public static final ProcessType GETVARIANTUNIT = new ProcessType("GETVARIANTUNIT", 49, VariantUnit.class, R.string.type_get_variant_unit);
    public static final ProcessType GETSTCOMPLN = new ProcessType("GETSTCOMPLN", 50, Stcompln.class, R.string.type_get_st_compln);
    public static final ProcessType GETBARCODE = new ProcessType("GETBARCODE", 51, UnitBarcode.class, R.string.type_get_barcode);
    public static final ProcessType GETPRICE = new ProcessType("GETPRICE", 52, ItemPrice.class, R.string.type_get_price);
    public static final ProcessType GETSTOCK = new ProcessType("GETSTOCK", 53, ItemStock.class, R.string.type_get_stock);
    public static final ProcessType GETCLCARD = new ProcessType("GETCLCARD", 54, ClCard.class, R.string.type_get_clcard);
    public static final ProcessType GETCUSTOMERIMAGE = new ProcessType("GETCUSTOMERIMAGE", 55, CustomerImage.class, R.string.type_get_customer_images);
    public static final ProcessType GETSHIPADDRESS = new ProcessType("GETSHIPADDRESS", 56, ShipAddress.class, R.string.type_get_ship_address);
    public static final ProcessType GETCLCARDLASTTRANSFER = new ProcessType("GETCLCARDLASTTRANSFER", 57, LastTransferDate.class, R.string.type_get_clcard_last_transfer);
    public static final ProcessType GETITEMLASTTRANSFER = new ProcessType("GETITEMLASTTRANSFER", 58, LastTransferDate.class, R.string.type_get_item_last_transfer);
    public static final ProcessType GETCLCARDLOWLEVELCODE = new ProcessType("GETCLCARDLOWLEVELCODE", 59, LowLevelCode.class, R.string.type_get_clcard_low_level_code);
    public static final ProcessType GETSALESORDER = new ProcessType("GETSALESORDER", 60, ManagerOrder.class, R.string.type_get_sales_order);
    public static final ProcessType GETDISTRIBUTION = new ProcessType("GETDISTRIBUTION", 61, Distribution.class, 0);
    public static final ProcessType GETSALESMANS = new ProcessType("GETSALESMANS", 62, SalesMan.class, 0);
    public static final ProcessType GETSALESORDERDETAIL = new ProcessType("GETSALESORDERDETAIL", 63, SalesView.class, R.string.type_get_sales_order_detail);
    public static final ProcessType UPDATESALESORDER = new ProcessType("UPDATESALESORDER", 64, String.class, R.string.type_update_sales_order);
    public static final ProcessType UPDATETODOINFO = new ProcessType("UPDATETODOINFO", 65, String.class, R.string.type_update_todo_info);
    public static final ProcessType SENDSTARTINFO = new ProcessType("SENDSTARTINFO", 66, String.class, R.string.type_send_start_info);
    public static final ProcessType SENDGPSINFO = new ProcessType("SENDGPSINFO", 67, String.class, R.string.type_send_gps_info);
    public static final ProcessType GETCUSTOMERCAMPAIGN = new ProcessType("GETCUSTOMERCAMPAIGN", 68, CustomerCampaignPoint.class, R.string.type_get_customer_campaign_point);
    public static final ProcessType GETORDERSTATUS = new ProcessType("GETORDERSTATUS", 69, TigerSqlModel.class, R.string.type_get_order_status);
    public static final ProcessType GETCUSTOMERBALANCE = new ProcessType("GETCUSTOMERBALANCE", 70, CustomerBalance.class, R.string.empty_text);
    public static final ProcessType GETCUSTOMERTOPTENPRODUCT = new ProcessType("GETCUSTOMERTOPTENPRODUCT", 71, TopProduct.class, R.string.empty_text);
    public static final ProcessType GETCUSTOMERMONTHLYSALES = new ProcessType("GETCUSTOMERMONTHLYSALES", 72, MonthlySales.class, R.string.empty_text);
    public static final ProcessType GETONLINEPRICE = new ProcessType("GETONLINEPRICE", 73, Price.class, R.string.type_get_online_price);
    public static final ProcessType FILLREPORTORDER = new ProcessType("FILLREPORTORDER", 74, ReportWCFAllReportActivity.class, R.string.str_orders);
    public static final ProcessType FILLREPORTINVOICE = new ProcessType("FILLREPORTINVOICE", 75, ReportWCFAllReportActivity.class, R.string.str_invoices);
    public static final ProcessType FILLREPORTRETURNINVOICE = new ProcessType("FILLREPORTRETURNINVOICE", 76, ReportWCFAllReportActivity.class, R.string.str_return_invoice);
    public static final ProcessType FILLREPORTCASH = new ProcessType("FILLREPORTCASH", 77, ReportWCFAllReportActivity.class, R.string.str_cash_collections);
    public static final ProcessType FILLREPORTCREDIT = new ProcessType("FILLREPORTCREDIT", 78, ReportWCFAllReportActivity.class, R.string.str_credit_cart_collections);
    public static final ProcessType FILLREPORTCASHCASE = new ProcessType("FILLREPORTCASHCASE", 79, ReportWCFAllReportActivity.class, R.string.str_case_collections);
    public static final ProcessType FILLREPORTCHEQUE = new ProcessType("FILLREPORTCHEQUE", 80, ReportWCFAllReportActivity.class, R.string.str_check_collections);
    public static final ProcessType FILLREPORTDEED = new ProcessType("FILLREPORTDEED", 81, ReportWCFAllReportActivity.class, R.string.str_payroll_note_collections);
    public static final ProcessType FILLREPORTVEHICLESTATUS = new ProcessType("FILLREPORTVEHICLESTATUS", 82, ReportWCFAllReportActivity.class, R.string.str_vehicle_stock_status);
    public static final ProcessType FILLREPORTSALESUMMARY = new ProcessType("FILLREPORTSALESUMMARY", 83, ReportWCFAllReportActivity.class, R.string.str_sales_summary_report);
    public static final ProcessType FILLREPORTSALESORD = new ProcessType("FILLREPORTSALESORD", 84, ReportWCFAllReportActivity.class, R.string.str_sales_report_order);
    public static final ProcessType FILLREPORTORDERTOTAL = new ProcessType("FILLREPORTORDERTOTAL", 85, ReportWCFAllReportActivity.class, R.string.str_order_totals_report);
    public static final ProcessType FILLREPORTINVOICEAVGTIME = new ProcessType("FILLREPORTINVOICEAVGTIME", 86, ReportWCFAllReportActivity.class, R.string.str_invoice_average_expiry);
    public static final ProcessType FILLREPORTAVGCALC = new ProcessType("FILLREPORTAVGCALC", 87, ReportWCFAllReportActivity.class, R.string.str_average_expiry_account);
    public static final ProcessType FILLREPORTDEBITFOLLOW = new ProcessType("FILLREPORTDEBITFOLLOW", 88, ReportWCFAllReportActivity.class, R.string.str_debt_tracking_report);
    public static final ProcessType FILLREPORTSALESINV = new ProcessType("FILLREPORTSALESINV", 89, ReportWCFAllReportActivity.class, R.string.str_sales_report_invoice);
    public static final ProcessType FILLREPORTCOLLECTIONSLIST = new ProcessType("FILLREPORTCOLLECTIONSLIST", 90, ReportWCFAllReportActivity.class, R.string.str_detailed_collection_report);
    public static final ProcessType FILLREPORTORDERLIST = new ProcessType("FILLREPORTORDERLIST", 91, ReportWCFAllReportActivity.class, R.string.str_order_report);
    public static final ProcessType FILLSPIN = new ProcessType("FILLSPIN", 92, ReportWCFAllReportActivity.class, -1);
    public static final ProcessType REPORTEXTRACT = new ProcessType("REPORTEXTRACT", 93, ReportWCFAllReportActivity.class, -1);
    public static final ProcessType SUCSESS = new ProcessType("SUCSESS", 94, ReportWCFAllReportActivity.class, 0);
    public static final ProcessType FAILED = new ProcessType("FAILED", 95, ReportWCFAllReportActivity.class, 0);
    public static final ProcessType GETPRODUCTTOPTENCUSTOMER = new ProcessType("GETPRODUCTTOPTENCUSTOMER", 96, TopProduct.class, R.string.empty_text);
    public static final ProcessType GETPRODUCTMONTHLYSALES = new ProcessType("GETPRODUCTMONTHLYSALES", 97, MonthlyProductSales.class, R.string.empty_text);
    public static final ProcessType GETMAXPRINTMATTERAREA = new ProcessType("GETMAXPRINTMATTERAREA", 98, Matter.class, R.string.type_update_matter_area);
    public static final ProcessType PRINTHEADER = new ProcessType("PRINTHEADER", 99, RESULTXML.class, 0);
    public static final ProcessType PRINTDETAIL = new ProcessType("PRINTDETAIL", 100, RESULTXML.class, 0);
    public static final ProcessType PRINTDISCOUNT = new ProcessType("PRINTDISCOUNT", 101, RESULTXML.class, 0);
    public static final ProcessType GETORDERDETAIL = new ProcessType("GETORDERDETAIL", 102, OrderView.class, 0);
    public static final ProcessType GETRISKCALCULATE = new ProcessType("GETRISKCALCULATE", 103, RiskResult.class, 0);
    public static final ProcessType GETDOCODE = new ProcessType("GETDOCODE", 104, DocNo.class, 0);
    public static final ProcessType GETFICHEDUPLICATE = new ProcessType("GETFICHEDUPLICATE", 105, FicheDuplicate.class, 0);
    public static final ProcessType INSERTVISIT = new ProcessType("INSERTVISIT", 106, VisitInfo.class, R.string.type_insert_visit);
    public static final ProcessType INSERTWORPROCESS = new ProcessType("INSERTWORPROCESS", 107, String.class, R.string.type_insert_todo_wor_proc);
    public static final ProcessType INSERTWORROUTEPROCESS = new ProcessType("INSERTWORROUTEPROCESS", 108, String.class, 0);
    public static final ProcessType INSERTPENETRATION = new ProcessType("INSERTPENETRATION", 109, UserMainPenetration.class, R.string.type_insert_penetration);
    public static final ProcessType INSERTPENETRATIONDETAIL = new ProcessType("INSERTPENETRATIONDETAIL", 110, UserPenetration.class, R.string.type_insert_penetration);
    public static final ProcessType GETRISKINFO = new ProcessType("GETRISKINFO", 111, CustomerRisk.class, 0);
    public static final ProcessType GETIMAGE = new ProcessType("GETIMAGE", 112, Image.class, R.string.type_get_image);
    public static final ProcessType GETORDERFICHESTATUS = new ProcessType("GETORDERFICHESTATUS", 113, OrderFicheStatus.class, 0);
    public static final ProcessType GETCUSTOMERBEFOREBALACE = new ProcessType("GETCUSTOMERBEFOREBALACE", 114, CustomerBeforeBalance.class, 0);
    public static final ProcessType GETUSERS = new ProcessType("GETUSERS", 115, UserInfo.class, R.string.str_user_info);
    public static final ProcessType GETLOGOPARAMS = new ProcessType("GETLOGOPARAMS", 116, LogoParam.class, R.string.str_logo_parameters);
    public static final ProcessType GETCURRTYPES = new ProcessType("GETCURRTYPES", 117, CurrType.class, R.string.str_setup_types);
    public static final ProcessType GETITEMS = new ProcessType("GETITEMS", 118, Item.class, R.string.str_materials);
    public static final ProcessType GETPROJECTS = new ProcessType("GETPROJECTS", 119, Project.class, R.string.str_project_code);
    public static final ProcessType GETUSERPARAMS = new ProcessType("GETUSERPARAMS", 120, UserParam.class, R.string.str_user_parameters);
    public static final ProcessType GETROUTES = new ProcessType("GETROUTES", 121, Route.class, R.string.str_customer_route_list);
    public static final ProcessType GETCUSSLSRELATION = new ProcessType("GETCUSSLSRELATION", 122, SlsClRel.class, R.string.str_customer_relations);
    public static final ProcessType GETUNITS = new ProcessType("GETUNITS", 123, ItemUnits.class, R.string.str_item_units);
    public static final ProcessType GETUNITBARCODE = new ProcessType("GETUNITBARCODE", 124, UnitBarcode.class, R.string.str_unit_barcode);
    public static final ProcessType GETPURCHASEPRICE = new ProcessType("GETPURCHASEPRICE", 125, PurchasePrice.class, 0);
    public static final ProcessType GETSTOCKALL = new ProcessType("GETSTOCKALL", 126, ItemStock.class, R.string.str_warehouse_stock);
    public static final ProcessType GETDISCOUNTGENERAL = new ProcessType("GETDISCOUNTGENERAL", 127, Discount.class, R.string.str_discount_cards_general);
    public static final ProcessType GETDISCOUNTLINE = new ProcessType("GETDISCOUNTLINE", 128, Discount.class, R.string.str_discount_cards_line);
    public static final ProcessType GETCHECKSERILOT = new ProcessType("GETCHECKSERILOT", Wbxml.EXT_T_1, null, -1);
    public static final ProcessType SENDTODO = new ProcessType("SENDTODO", Wbxml.EXT_T_2, null, -1);
    public static final ProcessType SENDVISITINFO = new ProcessType("SENDVISITINFO", Wbxml.STR_T, null, -1);
    public static final ProcessType GETGENERALUNITS = new ProcessType("GETGENERALUNITS", Wbxml.LITERAL_A, Factory.class, R.string.str_organizational_unit);
    public static final ProcessType GETORDERFICHESTATE = new ProcessType("GETORDERFICHESTATE", 133, OrderState.class, 0);
    public static final ProcessType INSERTCUSTOMERIMAGE = new ProcessType("INSERTCUSTOMERIMAGE", 134, CustomerImage.class, R.string.type_insert_customer_image);
    public static final ProcessType DELETECUSTOMERIMAGE = new ProcessType("DELETECUSTOMERIMAGE", 135, null, R.string.type_delete_customer_image);
    public static final ProcessType GETDUEDATETOTAL = new ProcessType("GETDUEDATETOTAL", 136, DueDateTotal.class, 0);
    public static final ProcessType GETCREDITAGGRS = new ProcessType("GETCREDITAGGRS", 137, CreditAggr.class, R.string.type_get_credit_aggrs);
    public static final ProcessType GETKEYVALUES = new ProcessType("GETKEYVALUES", 138, GenericData.class, 0);
    public static final ProcessType GETCHECKSERIGROUP = new ProcessType("GETCHECKSERIGROUP", 139, CheckSeriGroup.class, 0);
    public static final ProcessType GETSUPPASGN = new ProcessType("GETSUPPASGN", 140, SuppAsgn.class, 0);
    public static final ProcessType GETORDERTOFICHETOTAL = new ProcessType("GETORDERTOFICHETOTAL", 141, OrderToFicheTotal.class, 0);
    public static final ProcessType GETORDERAVAILABLEAMOUNT = new ProcessType("GETORDERAVAILABLEAMOUNT", 142, OrderAvailableAmount.class, 0);
    public static final ProcessType GETMUHREFCODES = new ProcessType("GETMUHREFCODES", 143, MuhRefCodes.class, R.string.type_get_accounting_ref_codes);
    public static final ProcessType GETADDTAX = new ProcessType("GETADDTAX", 144, AddTax.class, R.string.type_get_add_taxes);
    public static final ProcessType GETWORROUTE = new ProcessType("GETWORROUTE", 145, WorRoute.class, R.string.type_get_route);
    public static final ProcessType GETWORROUTEPLAN = new ProcessType("GETWORROUTEPLAN", 146, WorRoutePlan.class, 0);
    public static final ProcessType GETWORROUTEDAY = new ProcessType("GETWORROUTEDAY", 147, WorRouteDay.class, 0);
    public static final ProcessType GETWORUSERCUSTOMERS = new ProcessType("GETWORUSERCUSTOMERS", 148, WorUserCustomers.class, 0);
    public static final ProcessType GETCLCARDINCHARGE = new ProcessType("GETCLCARDINCHARGE", 149, ClCardIncharge.class, R.string.str_get_clcard_incharge);
    public static final ProcessType GETLOCTRACKING = new ProcessType("GETLOCTRACKING", 150, LocTracking.class, 0);
    public static final ProcessType GETDETAILSTOCKTRACK = new ProcessType("GETDETAILSTOCKTRACK", 151, LocationStockTracking.class, 0);
    public static final ProcessType GETREPORTEXTRACT = new ProcessType("GETREPORTEXTRACT", 152, REPORTLINE.class, 0);
    public static final ProcessType GETITEMSURPLUSDISCOUNT = new ProcessType("GETITEMSURPLUSDISCOUNT", 153, SurplusDiscount.class, 0);
    public static final ProcessType GETCOUNTRIES = new ProcessType("GETCOUNTRIES", 154, Country.class, R.string.str_get_counties);
    public static final ProcessType GETCITIES = new ProcessType("GETCITIES", 155, City.class, R.string.str_get_cities);
    public static final ProcessType GETTOWNS = new ProcessType("GETTOWNS", 156, Town.class, R.string.str_get_towns);
    public static final ProcessType GETALTERNATIVEITEMREFS = new ProcessType("GETALTERNATIVEITEMREFS", 157, KeyValuePair.class, 0);
    public static final ProcessType GETCABINS = new ProcessType("GETCABINS", 158, Cabin.class, R.string.str_get_cabin);
    public static final ProcessType INSERTCABINTRANS = new ProcessType("INSERTCABINTRANS", 159, CabinTrans.class, 0);
    public static final ProcessType UPDATECABIN = new ProcessType("UPDATECABIN", 160, Cabin.class, 0);
    public static final ProcessType UPDATECLFLINE = new ProcessType("UPDATECLFLINE", 161, null, -1);
    public static final ProcessType GETWORTABLES = new ProcessType("GETWORTABLES", 162, WorTablesModel.class, 0);
    public static final ProcessType GETCURRENCYBALANCES = new ProcessType("GETCURRENCYBALANCES", 163, CurrencyBalanceModel.class, 0);
    public static final ProcessType GETWORUSERS = new ProcessType("GETWORUSERS", 164, WorUserModel.class, 0);
    public static final ProcessType EXECWMSBARCODESP = new ProcessType("EXECWMSBARCODESP", 165, WmsBarcodeModel.class, 0);
    public static final ProcessType CHECKFDATE = new ProcessType("CHECKFDATE", 166, CheckFDateModel.class, 0);
    public static final ProcessType GETVARIANTSTOCK = new ProcessType("GETVARIANTSTOCK", 167, VariantStock.class, R.string.type_get_variant_stock);
    public static final ProcessType GETPRINTSLIPEXTRAINFO = new ProcessType("GETPRINTSLIPEXTRAINFO", 168, PrintExtraInfo.class, 0);
    public static final ProcessType GETCUSTOMERRISKTOTALS = new ProcessType("GETCUSTOMERRISKTOTALS", 169, ClRisk.class, R.string.str_getting_customer_risk_totals);
    public static final ProcessType GETLASTPURCHASEINFO = new ProcessType("GETLASTPURCHASEINFO", 170, PurchasePriceInfo.class, R.string.str_get_last_purchase_info);
    public static final ProcessType GETITEMGROUPCODES = new ProcessType("GETITEMGROUPCODES", 171, ItemGroupCode.class, R.string.type_get_item_group_codes);
    public static final ProcessType GETEDOCSERIALS = new ProcessType("GETEDOCSERIALS", 172, EDocSerial.class, R.string.type_get_edoc_serials);
    public static final ProcessType GETFIRM = new ProcessType("GETFIRM", 173, Firm.class, R.string.type_getFirm);
    public static final ProcessType GETEDESPATCHPDFHEADER = new ProcessType("GETEDESPATCHPDFHEADER", 174, EDespatchPdfHeader.class, 0);
    public static final ProcessType GETEINVOICEPDFHEADER = new ProcessType("GETEINVOICEPDFHEADER", 175, EInvoicePdfHeader.class, 0);
    public static final ProcessType GETEDOCUMENTPDFHEADERNETSIS = new ProcessType("GETEDOCUMENTPDFHEADERNETSIS", 176, EDocumentPdfHeaderNetsis.class, 0);
    public static final ProcessType GETEDOCUMENTPDFDETAIL = new ProcessType("GETEDOCUMENTPDFDETAIL", 177, EDocumentPdfDetail.class, 0);
    public static final ProcessType GETEDOCUMENTPDFDETAILNETSIS = new ProcessType("GETEDOCUMENTPDFDETAILNETSIS", 178, EDocumentPdfDetailNetsis.class, 0);
    public static final ProcessType GETWAREHOUSEITEMS = new ProcessType("GETWAREHOUSEITEMS", 179, WarehouseItem.class, 0);
    public static final ProcessType INSERTWORLOG = new ProcessType("INSERTWORLOG", 180, String.class, 0);
    public static final ProcessType GETWORPROCESS = new ProcessType("GETWORPROCESS", 181, KeyValuePair.class, 0);
    public static final ProcessType GETKEYVALUEPAIR = new ProcessType("GETKEYVALUEPAIR", 182, KeyValuePair.class, 0);
    public static final ProcessType GETSHOWEDOCUMENTPARAM = new ProcessType("GETSHOWEDOCUMENTPARAM", 183, ShowEDocumentParam.class, 0);
    public static final ProcessType GETUSERNOTIFICATIONS = new ProcessType("GETUSERNOTIFICATIONS", 184, NotificationModel.class, 0);
    public static final ProcessType GETNOTIFIEDUSERS = new ProcessType("GETNOTIFIEDUSERS", 185, NotifiedUserModel.class, 0);
    public static final ProcessType GETNOTIFICATION = new ProcessType("GETNOTIFICATION", 186, NotificationModel.class, 0);
    public static final ProcessType GETCONNECTEDUSERS = new ProcessType("GETCONNECTEDUSERS", 187, NotificationUserSelectionModel.class, 0);
    public static final ProcessType SERIALLOT = new ProcessType("SERIALLOT", 188, SerialLotModel.class, 0);
    public static final ProcessType INSERTWORUSERPROCESS = new ProcessType("INSERTWORUSERPROCESS", 189, String.class, 0);
    public static final ProcessType GETACTIVEPERIOD = new ProcessType("GETACTIVEPERIOD", 190, ActivePeriod.class, 0);
    public static final ProcessType GETLASTORDERPRODUCTS = new ProcessType("GETLASTORDERPRODUCTS", 191, LastOrderProducts.class, 0);
    public static final ProcessType GETITEMTAGS = new ProcessType("GETITEMTAGS", Wbxml.EXT_0, ItemTags.class, R.string.str_get_item_tags);
    public static final ProcessType GETRISKLIMIT = new ProcessType("GETRISKLIMIT", Wbxml.EXT_1, CustomerRiskLimit.class, 0);
    public static final ProcessType GETRECOMMENDEDPRODUCTS = new ProcessType("GETRECOMMENDEDPRODUCTS", Wbxml.EXT_2, RecommendedProducts.class, 0);
    public static final ProcessType GETGROUPCODES = new ProcessType("GETGROUPCODES", Wbxml.OPAQUE, GroupCode.class, 0);
    public static final ProcessType GETORDERSFICHETATUS = new ProcessType("GETORDERSFICHETATUS", Wbxml.LITERAL_AC, UpdatedOrderStatus.class, 0);
    public static final ProcessType GETDETAILEDRESTRICTION = new ProcessType("GETDETAILEDRESTRICTION", 197, CustomerDetailedRestriction.class, 0);
    public static final ProcessType GETITEMSERIALINFO = new ProcessType("GETITEMSERIALINFO", 198, ItemSerialInfo.class, 0);
    public static final ProcessType GETPANELVERSION = new ProcessType("GETPANELVERSION", 199, PanelVersion.class, 0);

    private static ProcessType[] values() {
        return new ProcessType[]{LOGIN, GETUSERDEVICE, GETLISANSINFO, GETUSERPARAM, SAVEDEVICEID, GETUSERINFO, GETLOGOTRADEPARAMS, GETCURRTYPE, GETCURRRATE, GETITEM, GETITEMNAME, GETITEMIMAGE, GETSERVICE, GETPROJECT, GETROUTE, GETCUSTOMERSALESMANRELATION, GETCUSTOMERGPSLOCATION, GETROUTETRS, GETITEMUNIT, GETSERVICEUNIT, GETUNIT, GETWAREHOUSE, GETDIVISION, GETBRANCH, GETFACTORY, GETSPECODE, GETSPECODEPRICES, GETDISCOUNT, GETPAYMENT, GETPAYMENTLINE, GETTRADINGGROUP, GETSHIPTYPE, GETSHIPAGENT, GETBANK, GETBANKACCOUNT, GETVISITREASON, GETTODO, GETMARKETINGMESSAGE, GETDEFORDER, GETDEFORDERDETAIL, GETPENETRATION, GETPENETRATIONDETAIL, GETCASE, GETDESFILE, GETDESFILESTRJSON, GETEMAILPARAM, GETEMAILTEMPLATE, GETREPORTS, GETVARIANT, GETVARIANTUNIT, GETSTCOMPLN, GETBARCODE, GETPRICE, GETSTOCK, GETCLCARD, GETCUSTOMERIMAGE, GETSHIPADDRESS, GETCLCARDLASTTRANSFER, GETITEMLASTTRANSFER, GETCLCARDLOWLEVELCODE, GETSALESORDER, GETDISTRIBUTION, GETSALESMANS, GETSALESORDERDETAIL, UPDATESALESORDER, UPDATETODOINFO, SENDSTARTINFO, SENDGPSINFO, GETCUSTOMERCAMPAIGN, GETORDERSTATUS, GETCUSTOMERBALANCE, GETCUSTOMERTOPTENPRODUCT, GETCUSTOMERMONTHLYSALES, GETONLINEPRICE, FILLREPORTORDER, FILLREPORTINVOICE, FILLREPORTRETURNINVOICE, FILLREPORTCASH, FILLREPORTCREDIT, FILLREPORTCASHCASE, FILLREPORTCHEQUE, FILLREPORTDEED, FILLREPORTVEHICLESTATUS, FILLREPORTSALESUMMARY, FILLREPORTSALESORD, FILLREPORTORDERTOTAL, FILLREPORTINVOICEAVGTIME, FILLREPORTAVGCALC, FILLREPORTDEBITFOLLOW, FILLREPORTSALESINV, FILLREPORTCOLLECTIONSLIST, FILLREPORTORDERLIST, FILLSPIN, REPORTEXTRACT, SUCSESS, FAILED, GETPRODUCTTOPTENCUSTOMER, GETPRODUCTMONTHLYSALES, GETMAXPRINTMATTERAREA, PRINTHEADER, PRINTDETAIL, PRINTDISCOUNT, GETORDERDETAIL, GETRISKCALCULATE, GETDOCODE, GETFICHEDUPLICATE, INSERTVISIT, INSERTWORPROCESS, INSERTWORROUTEPROCESS, INSERTPENETRATION, INSERTPENETRATIONDETAIL, GETRISKINFO, GETIMAGE, GETORDERFICHESTATUS, GETCUSTOMERBEFOREBALACE, GETUSERS, GETLOGOPARAMS, GETCURRTYPES, GETITEMS, GETPROJECTS, GETUSERPARAMS, GETROUTES, GETCUSSLSRELATION, GETUNITS, GETUNITBARCODE, GETPURCHASEPRICE, GETSTOCKALL, GETDISCOUNTGENERAL, GETDISCOUNTLINE, GETCHECKSERILOT, SENDTODO, SENDVISITINFO, GETGENERALUNITS, GETORDERFICHESTATE, INSERTCUSTOMERIMAGE, DELETECUSTOMERIMAGE, GETDUEDATETOTAL, GETCREDITAGGRS, GETKEYVALUES, GETCHECKSERIGROUP, GETSUPPASGN, GETORDERTOFICHETOTAL, GETORDERAVAILABLEAMOUNT, GETMUHREFCODES, GETADDTAX, GETWORROUTE, GETWORROUTEPLAN, GETWORROUTEDAY, GETWORUSERCUSTOMERS, GETCLCARDINCHARGE, GETLOCTRACKING, GETDETAILSTOCKTRACK, GETREPORTEXTRACT, GETITEMSURPLUSDISCOUNT, GETCOUNTRIES, GETCITIES, GETTOWNS, GETALTERNATIVEITEMREFS, GETCABINS, INSERTCABINTRANS, UPDATECABIN, UPDATECLFLINE, GETWORTABLES, GETCURRENCYBALANCES, GETWORUSERS, EXECWMSBARCODESP, CHECKFDATE, GETVARIANTSTOCK, GETPRINTSLIPEXTRAINFO, GETCUSTOMERRISKTOTALS, GETLASTPURCHASEINFO, GETITEMGROUPCODES, GETEDOCSERIALS, GETFIRM, GETEDESPATCHPDFHEADER, GETEINVOICEPDFHEADER, GETEDOCUMENTPDFHEADERNETSIS, GETEDOCUMENTPDFDETAIL, GETEDOCUMENTPDFDETAILNETSIS, GETWAREHOUSEITEMS, INSERTWORLOG, GETWORPROCESS, GETKEYVALUEPAIR, GETSHOWEDOCUMENTPARAM, GETUSERNOTIFICATIONS, GETNOTIFIEDUSERS, GETNOTIFICATION, GETCONNECTEDUSERS, SERIALLOT, INSERTWORUSERPROCESS, GETACTIVEPERIOD, GETLASTORDERPRODUCTS, GETITEMTAGS, GETRISKLIMIT, GETRECOMMENDEDPRODUCTS, GETGROUPCODES, GETORDERSFICHETATUS, GETDETAILEDRESTRICTION, GETITEMSERIALINFO, GETPANELVERSION};
    }

    public static ProcessType customerMenuToProcessType(CustomerMenuType customerMenuType) {
        return Companion.customerMenuToProcessType(customerMenuType);
    }

    public static EnumEntries<ProcessType> getEntries() {
        return ENTRIES;
    }

    public static ProcessType valueOf(String str) {
        return Enum.valueOf(ProcessType.class, str);
    }

    public static ProcessType[] values() {
        return VALUES.clone();
    }

    private ProcessType(String str, @StringRes int i2, Class cls, int i3) {
        super(str, i2);
        this.aClass = cls;
        this.resId = i3;
    }

    static {
        ProcessType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
        Companion = new Companion(null);
    }

    public Class<?> getaClass() {
        return this.aClass;
    }

    public void setaClass(Class<?> aClass) {
        Intrinsics.checkNotNullParameter(aClass, "aClass");
        this.aClass = aClass;
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public ProcessType customerMenuToProcessType(CustomerMenuType customerMenuType) {
            Intrinsics.checkNotNullParameter(customerMenuType, "customerMenuType");
            for (ProcessType processType : ProcessType.getEntries()) {
                if (Intrinsics.areEqual(processType.name(), customerMenuType.name())) {
                    return processType;
                }
            }
            return ProcessType.LOGIN;
        }
    }
}
