package com.proje.mobilesales.core.netsis;

import android.text.TextUtils;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseQueryCreator;
import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.core.base.BaseServiceResult;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.DataObjectType;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.NetsisFicheTypeNo;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.TrackType;
import com.proje.mobilesales.core.enums.TransferGetType;
import com.proje.mobilesales.core.enums.TransferOperationName;
import com.proje.mobilesales.core.netsis.sendmodel.chequedeed.CheckAndPNotesMainParam;
import com.proje.mobilesales.core.netsis.sendmodel.chequedeed.NetsisChequeAndDeedType;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipParams;
import com.proje.mobilesales.core.netsis.sendmodel.recipt.MixedReceiptsMainParam;
import com.proje.mobilesales.core.netsis.sendmodel.safedeposit.SafeDepositParam;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlipHeader;
import com.proje.mobilesales.core.netsis.sendmodel.sales.NetsisInvoiceType;
import com.proje.mobilesales.core.netsis.sendmodel.sales.NetsisSlipType;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.core.utils.CompressUtil;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.FDateUtils;
import com.proje.mobilesales.core.utils.FicheTypeControlUtils;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.Cabin;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.model.database.ClCardIncharge;
import com.proje.mobilesales.features.dbmodel.Bank;
import com.proje.mobilesales.features.dbmodel.BankAccount;
import com.proje.mobilesales.features.dbmodel.Payment;
import com.proje.mobilesales.features.dbmodel.ShipAddress;
import com.proje.mobilesales.features.dbmodel.UnitBarcode;
import com.proje.mobilesales.features.dbmodel.User;
import com.proje.mobilesales.features.dbmodel.Variant;
import com.proje.mobilesales.features.gpsinfo.model.database.CustGpsInfo;
import com.proje.mobilesales.features.model.LastPurchaseInfoSqlParams;
import com.proje.mobilesales.features.model.SerialLotListQueryParam;
import com.proje.mobilesales.features.model.TransferGetItem;
import com.proje.mobilesales.features.model.UserSettings;
import com.proje.mobilesales.features.penetration.model.Penetration;
import com.proje.mobilesales.features.penetration.model.PenetrationLine;
import com.proje.mobilesales.features.product.model.ItemPriceSqlParams;
import com.proje.mobilesales.features.product.model.LastItemPriceSqlParams;
import com.proje.mobilesales.features.product.model.database.Item;
import com.proje.mobilesales.features.product.model.database.ItemGroupCode;
import com.proje.mobilesales.features.product.model.database.ItemImage;
import com.proje.mobilesales.features.product.model.database.ItemPrice;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import com.proje.mobilesales.features.todo.model.database.TodoInfoDb;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;
import java.util.List;
 
public class NetsisQueryCreator extends BaseQueryCreator<NetsisSelectResult, NetsisSelectResult.NetsisBuilder, BaseServiceResult> {
    private static final String TAG = "NetsisQueryCreator";
 
    public NetsisSelectResult getBranches() {
        return null;
    }
 
    public NetsisSelectResult getCheckSeriGroup(int i2, int i3, SalesType salesType, boolean z, int i4, boolean z2) {
        return null;
    }

    
    public NetsisSelectResult getDiscounts(boolean z) {
        return null;
    }

    
    public NetsisSelectResult getDistributionList(int i2) {
        return null;
    }

    
    public NetsisSelectResult getDivisions() {
        return null;
    }

    
    public NetsisSelectResult getFactories() {
        return null;
    }

    
    public NetsisSelectResult getItemAddTaxLines(boolean z) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintCaseDetail(int i2) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintCaseHeader(int i2) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintCashDetail(int i2) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintCashHeader(int i2) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintChequeDetail(int i2) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintChequeHeader(int i2) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintCreditCardDetail(int i2) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintCreditCardHeader(int i2) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintDispatchDetail(int i2, int i3) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintDispatchDiscount(int i2) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintDispatchHeader(int i2) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintInvoiceDetail(int i2, int i3) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintInvoiceDiscount(int i2) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintInvoiceHeader(int i2) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintItemStock(int i2, int i3) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintOrderDetail(int i2, int i3) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintOrderDiscount(int i2) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintOrderHeader(int i2) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintWhTransferDetail(int i2, int i3) {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.PrintQuerable
    public NetsisSelectResult getPrintWhTransferHeader(int i2) {
        return null;
    }

    
    public NetsisSelectResult getSelectedDistributions(int i2, int i3) {
        return null;
    }

    
    public NetsisSelectResult getSuppAsgns() {
        return null;
    }

    
    public NetsisSelectResult getUsers() {
        return null;
    }

    @Override // com.proje.mobilesales.core.interfaces.AppQuerable
    public NetsisSelectResult loadCurrencyBalances(int i2, String str, String str2) {
        return null;
    }

    
    public NetsisSelectResult getOrderAvailableAmounts(ArrayList arrayList) {
        return getOrderAvailableAmounts2((ArrayList<String>) arrayList);
    }

    public NetsisQueryCreator(ISqlHelper iSqlHelper, User user, UserSettings userSettings) {
        super(iSqlHelper, user, userSettings);
    }
 
    public Preferences.NetsisUserSettings getUserSettings() {
        return (Preferences.NetsisUserSettings) this.mUserSettings;
    }
 
    public NetsisSelectResult.NetsisBuilder getV() {
        return NetsisSelectResult.newBuilder();
    }

    public void setUserSettings(UserSettings userSettings) {
        this.mUserSettings = userSettings;
    }
 
    public List<NetsisSelectResult> getTransferList(TransferGetItem transferGetItem, boolean z) {
        boolean transferGetOptionsType = !z && Preferences.getTransferGetOptionsType(ContextUtils.getmContext());
        ArrayList arrayList = new ArrayList();
        switch (C25271.SwitchMapcomprojemobilesalescoreenumsTransferGetType[transferGetItem.getTransferGetType().ordinal()]) {
            case 1:
                arrayList.add(getReports());
                arrayList.add(getCreditAggrs());
                arrayList.add(getEmailTemplates());
                arrayList.add(getMuhRefCodes());
                arrayList.add(getCountries());
                arrayList.add(getCities());
                arrayList.add(getTowns());
                arrayList.add(getEDocSerials());
                arrayList.add(getGroupCodes());
                if (ErpCreator.getInstance().getmBaseErp().getRouteNewSystem()) {
                    arrayList.add(getWorRoute());
                    arrayList.add(getWorRouteDay());
                    arrayList.add(getWorRoutePlan());
                    arrayList.add(getWorUserCustomers());
                }
                arrayList.add(getCabins(transferGetOptionsType));
                break;
            case 2:
                arrayList.add(getWareHouse());
                break;
            case 3:
                arrayList.add(getTodo());
                break;
            case 4:
                arrayList.add(getMarketingMessage());
                break;
            case 5:
                arrayList.add(getVisitReason());
                break;
            case 6:
                arrayList.add(getPenetration());
                arrayList.add(getPenetrationDetail());
                break;
            case 7:
                arrayList.add(getSpeCodes());
                arrayList.add(getSpeCodePrices());
                break;
            case 8:
                arrayList.add(getPayments(transferGetOptionsType));
                break;
            case 9:
                arrayList.add(getItems(transferGetOptionsType));
                arrayList.add(getCompositeColies(transferGetOptionsType));
                arrayList.add(getItemGroupCodes(transferGetOptionsType));
                arrayList.add(getItemTags());
                break;
            case 10:
                arrayList.add(getItemImages(transferGetOptionsType));
                break;
            case 11:
                arrayList.add(getVariants(transferGetOptionsType));
                break;
            case 12:
                arrayList.add(getItemUnits(transferGetOptionsType));
                break;
            case 13:
                arrayList.add(getItemBarcode(transferGetOptionsType));
                break;
            case 14:
                arrayList.add(getDefOrder());
                arrayList.add(getDefOrderDetail());
                break;
            case 15:
                arrayList.add(getItemStock(transferGetOptionsType));
                arrayList.add(getVariantStock(transferGetOptionsType));
                break;
            case 16:
                if (z) {
                    transferGetOptionsType = true;
                }
                arrayList.add(getItemPrices(transferGetOptionsType));
                break;
            case 17:
                arrayList.add(getGeneralDiscounts(transferGetOptionsType));
                break;
            case 18:
                arrayList.add(getCustomers(-1, transferGetOptionsType));
                arrayList.add(getCustomersIncharge(transferGetOptionsType));
                arrayList.add(getCustomerGpsLocation(String.format("%s|%s", getUser().getFirmNr(), Integer.valueOf(getUser().getPeridodNrInt()))));
                arrayList.add(getCustomerDetailedRestriction());
                break;
            case 19:
                arrayList.add(getShipAddress(transferGetOptionsType));
                break;
            case 20:
                arrayList.add(getBanks(transferGetOptionsType));
                arrayList.add(getBankAccounts(transferGetOptionsType));
                break;
            case 21:
                arrayList.add(getCases());
                break;
            case 22:
                try {
                    arrayList.add(getDesFile());
                    arrayList.add(getDesFileJson());
                    break;
                } catch (Exception e2) {
                    Log.e(TAG, "getTransferList: ", e2);
                    break;
                }
            case 23:
                arrayList.add(getCurrTypes());
                arrayList.add(getCurrRates());
                break;
            case 24:
                arrayList.add(getProjects());
                break;
            case 25:
                arrayList.add(getEmailParam());
                break;
            case 26:
                arrayList.add(getNetsisParameters());
                break;
        }
        return arrayList;
    }

    
    public NetsisSelectResult getCustomers(int i2, boolean z) {
        String str;
        String str2;
        String str3 = "";
        if (i2 <= -1) {
            str = "";
        } else {
            str = " AND CL.CARI_KOD='" + getSqlHelper().getClCode(i2) + "' ";
        }
        if (z) {
            str2 = "";
        } else {
            str2 = getOnlyChangeWithTraEqualSql(ClCard.class, "CL.KAYITTARIHI", "CL.DUZELTMETARIHI", "CH.TARIH", "CH.DUZELTMETARIHI", "CR.KAYITTARIHI", "CR.DUZELTMETARIHI");
        }
        if (ErpCreator.getInstance().getmBaseErp().getRouteNewSystem()) {
            if (ErpCreator.getInstance().getmBaseErp().getOffRoadVisit()) {
                str3 = " INNER JOIN WOR_USERCUSTOMERS UC WITH(NOLOCK) ON CL.CARI_KOD=UC.CLIENTREF AND UC.USERID=" + getUser().getUserId();
            } else {
                str3 = " INNER JOIN WOR_ROUTEPLAN RP WITH(NOLOCK) ON CL.CARI_KOD=RP.CLIENTREF INNER JOIN WOR_ROUTE R WITH(NOLOCK) ON R.LOGICALREF=RP.ROUTEID AND R.USERID=" + getUser().getUserId();
            }
        }
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_customers, str, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), str2, getCustomersFilter("CL"), str3)).withProcessType(ProcessType.GETCLCARD).withDatabaseSave(true).withTableDelete(TextUtils.isEmpty(str2)).withDeleteCondition("ISTRANSFER <> 0").build();
    }
 
    public NetsisSelectResult listAllCustomersOnline(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            str2 = " AND (CL.CARI_KOD LIKE '%" + str + "%' OR CL.CARI_ISIM LIKE '%" + str + "%')";
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_list_all_customers, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), str2)).withProcessType(ProcessType.GETCLCARD).withDatabaseSave(false).withTableDelete(false).build();
    }

    private String getCustomersFilter(String str) {
        StringBuilder sb = new StringBuilder();
        String[] split = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards).split("\\;");
        String trim = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_AuxiliaryCode).trim();
        String trim2 = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_AuxiliaryCode2).trim();
        String trim3 = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_AuxiliaryCode3).trim();
        String trim4 = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_AuxiliaryCode4).trim();
        String trim5 = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_AuxiliaryCode5).trim();
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_SQLSentence);
        if (split != null && split.length == 2) {
            String str2 = split[0];
            String str3 = split[1];
            if (!str2.equals("") && !str3.equals("")) {
                sb.append(SqlLiteVariable._AND + str + ".CARI_KOD BETWEEN '" + str2 + "' AND '" + str3 + "' ");
            }
        }
        if (!trim.equals("")) {
            sb.append(SqlLiteVariable._AND + str + ".RAPOR_KODU1='" + trim + "' ");
        }
        if (!trim2.equals("")) {
            sb.append(SqlLiteVariable._AND + str + ".RAPOR_KODU2='" + trim2 + "' ");
        }
        if (!trim3.equals("")) {
            sb.append(SqlLiteVariable._AND + str + ".RAPOR_KODU3='" + trim3 + "' ");
        }
        if (!trim4.equals("")) {
            sb.append(SqlLiteVariable._AND + str + ".RAPOR_KODU4='" + trim4 + "' ");
        }
        if (!trim5.equals("")) {
            sb.append(SqlLiteVariable._AND + str + ".RAPOR_KODU5='" + trim5 + "' ");
        }
        if (paramValue.trim().length() > 0) {
            sb.append(SqlLiteVariable._AND + str + ".CARI_KOD IN ( " + paramValue + ")");
        }
        return sb.toString();
    }

    
    public NetsisSelectResult getShipCustomers(String str) {
        if (ErpCreator.getInstance().getmBaseErp().getSevkiyatHesabiTeslimatCari()) {
            return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_ship_customers, Integer.valueOf(getUserSettings().getBranchCode()))).withProcessType(ProcessType.GETCLCARD).build();
        }
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptShippingCustomerFilter);
        String formatStringEnglish = ContextUtils.formatStringEnglish(R.string.net_get_connected_customers, str, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()));
        if (!TextUtils.isEmpty(paramValue)) {
            formatStringEnglish = formatStringEnglish + " AND CL.CARI_KOD IN (" + paramValue + ")";
        }
        return NetsisSelectResult.newBuilder().withSql(formatStringEnglish).withProcessType(ProcessType.GETCLCARD).build();
    }
 
    public NetsisSelectResult getCustomersIncharge(boolean z) {
        String str;
        if (z) {
            str = "";
        } else {
            str = getOnlyChangeSql(ClCardIncharge.class, "CE.KAYITTARIHI", "CE.DUZELTMETARIHI");
        }
        String formatStringEnglish = ContextUtils.formatStringEnglish(R.string.net_get_customers_incharge, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), str, getCustomersFilter("CL"));
        return NetsisSelectResult.newBuilder().withSql(formatStringEnglish).withProcessType(ProcessType.GETCLCARDINCHARGE).withDatabaseSave(true).withTableDelete(TextUtils.isEmpty(str)).build();
    }

    public NetsisSelectResult getItemImage(int i2) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.get_netsis_item_image, ErpCreator.getInstance().getmBaseErp().getItemCode(i2))).withProcessType(ProcessType.GETIMAGE).build();
    }

    public NetsisSelectResult getCustomerRisk(int i2) {
        String customerClCode = ErpCreator.getInstance().getmBaseErp().getCustomerClCode(i2);
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_customer_risks, customerClCode, DateAndTimeUtils.getSqlDate(Boolean.TRUE), customerClCode)).withProcessType(ProcessType.GETRISKINFO).withDatabaseSave(false).build();
    }

    public NetsisSelectResult getCustomerRiskCalculate(int i2) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_customer_risk, ErpCreator.getInstance().getmBaseErp().getCustomerClCode(i2), DateAndTimeUtils.getSqlDate(Boolean.TRUE))).withProcessType(ProcessType.GETCUSTOMERRISKTOTALS).withDatabaseSave(true).build();
    }

    private String getOnlyChangeSql(Class<?> cls) {
        return getOnlyChangeSql(cls, "KAYITTARIHI", "DUZELTMETARIHI");
    }

    private String getOnlyChangeSql(Class<?> cls, String str, String str2) {
        double maxCMDate = getSqlHelper().getMaxCMDate(cls);
        if (maxCMDate == 0.0d) {
            return "";
        }
        return ContextUtils.getStringResource(R.string.net_only_change_condition, str, str2, String.valueOf(maxCMDate));
    }

    private String getOnlyChangeEqualSql(Class<?> cls) {
        return getOnlyChangeSql(cls, "KAYITTARIHI", "DUZELTMETARIHI");
    }

    private String getOnlyChangeEqualSql(Class<?> cls, String str, String str2) {
        double maxCMDate = getSqlHelper().getMaxCMDate(cls);
        if (maxCMDate == 0.0d) {
            return "";
        }
        return ContextUtils.getStringResource(R.string.net_only_change_condition_equal, str, str2, String.valueOf(maxCMDate));
    }

    private String getOnlyChangeWithTraEqualSql(Class<?> cls, String str, String str2, String str3, String str4, String str5, String str6) {
        double maxCMDate = getSqlHelper().getMaxCMDate(cls);
        if (maxCMDate == 0.0d) {
            return "";
        }
        return ContextUtils.getStringResource(R.string.net_only_change_with_tra_condition_equal, str, str2, String.valueOf(maxCMDate), str3, str4, str5, str6);
    }

    
    public NetsisSelectResult getItems(boolean z) {
        String str;
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_SqlSentence);
        if (!TextUtils.isEmpty(paramValue)) {
            paramValue = " AND ST.STOK_KODU IN ( " + paramValue + ")";
        }
        if (z) {
            str = "";
        } else {
            str = getOnlyChangeSql(Item.class, "SEK.KAYITTARIHI", "SEK.DUZELTMETARIHI");
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_items, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), str, "", paramValue)).withOrderBy(ContextUtils.getStringResource(R.string.net_get_items_order_by)).withProcessType(ProcessType.GETITEM).withDatabaseSave(true).withTableDelete(TextUtils.isEmpty(str)).build();
    }
 
    public NetsisSelectResult getItemImages(boolean z) {
        String str;
        if (z) {
            str = "";
        } else {
            str = getOnlyChangeSql(ItemImage.class, "KAYITTAR", "DUZELTTAR");
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_item_images, str)).withProcessType(ProcessType.GETITEMIMAGE).withDatabaseSave(true).withTableDelete(TextUtils.isEmpty(str)).build();
    } 
    public NetsisSelectResult getCompositeColies(boolean z) {
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_SqlSentence);
        if (!TextUtils.isEmpty(paramValue)) {
            paramValue = " AND KOLISTOK_KODU IN ( " + paramValue + ")";
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_composite_coli, paramValue)).withOrderBy(ContextUtils.getStringResource(R.string.net_get_composite_coli_order_by)).withProcessType(ProcessType.GETSTCOMPLN).withDatabaseSave(true).withTableDelete(true).build();
    }
 
    public NetsisSelectResult getItemUnits(boolean z) {
        String str;
        if (z) {
            str = "";
        } else {
            str = getOnlyChangeSql(ItemUnits.class);
        }
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_SqlSentence);
        if (!TextUtils.isEmpty(paramValue)) {
            paramValue = " AND STOK_KODU IN ( " + paramValue + ")";
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_item_units, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), str, paramValue)).withProcessType(ProcessType.GETITEMUNIT).withDatabaseSave(true).withTableDelete(TextUtils.isEmpty(str)).build();
    }
 
    public NetsisSelectResult getItemPrices(boolean z) {
        String str;
        boolean equals = getSqlHelper().getLogoParamValue("FIAT_LISTE").equals(ExifInterface.LONGITUDE_EAST);
        boolean equals2 = getSqlHelper().getLogoParamValue("FIAT_SISTEMI").equals(ExifInterface.LONGITUDE_EAST);
        boolean z2 = equals2 && equals;
        boolean z3 = equals2 && !equals;
        String sb = "useOnlySystemPrices:" +
                z2 +
                " useSystemAndStockPrices:" +
                z3 +
                " useStockPrices:" +
                !equals2;
        Log.d("getItemPrices", sb);
        boolean equals3 = getSqlHelper().getLogoParamValue("SATISFIAT_DOVFIAT").equals(ExifInterface.LONGITUDE_EAST);
        String logoParamValue = getSqlHelper().getLogoParamValue("LOKAL_KUR");
        String stringResource = ContextUtils.getStringResource(R.string.str_price_with_currency);
        String stringResource2 = ContextUtils.getStringResource(R.string.str_price);
        String stringResource3 = ContextUtils.getStringResource(R.string.str_list_price_abbr);
        StringBuilder sb2 = new StringBuilder();
        ItemPriceSqlParams itemPriceSqlParams = new ItemPriceSqlParams();
        itemPriceSqlParams.setDeleteAndTransfer(z);
        itemPriceSqlParams.setUseSalesPriceAsCurrencyPrice(equals3);
        itemPriceSqlParams.currencyPriceDescription = stringResource;
        itemPriceSqlParams.localCurrency = logoParamValue;
        if (z2) {
            itemPriceSqlParams.setGetPriceFromList(true);
            itemPriceSqlParams.priceDescription = stringResource3;
            itemPriceSqlParams.cmDateSql = z ? "" : getOnlyChangeSql(ItemPrice.class, "SEK.KAYITTAR", "SEK.DEGISIKTAR");
            sb2.append("SELECT * FROM (");
            sb2.append(buildGetPriceSql(itemPriceSqlParams));
            sb2.append(" UNION (");
            sb2.append(getVarianPriceListSql(z, itemPriceSqlParams));
            sb2.append(")) AS R");
            str = "getItemPrices";
        } else {
            str = "getItemPrices";
            if (z3) {
                itemPriceSqlParams.setGetPriceFromList(true);
                itemPriceSqlParams.priceDescription = stringResource3;
                itemPriceSqlParams.cmDateSql = z ? "" : getOnlyChangeSql(ItemPrice.class, "SEK.KAYITTAR", "SEK.DEGISIKTAR");
                sb2.append("SELECT * FROM (");
                sb2.append(buildGetPriceSql(itemPriceSqlParams));
                sb2.append(" UNION (");
                itemPriceSqlParams.setGetPriceFromList(false);
                itemPriceSqlParams.priceDescription = stringResource2;
                itemPriceSqlParams.cmDateSql = z ? "" : getOnlyChangeSql(ItemPrice.class, "SEK.KAYITTARIHI", "SEK.DUZELTMETARIHI");
                sb2.append(buildGetPriceSql(itemPriceSqlParams));
                sb2.append(" UNION ");
                sb2.append(getVarianPriceListSql(z, itemPriceSqlParams));
                sb2.append(")) AS R");
            } else if (!equals2) {
                itemPriceSqlParams.setGetPriceFromList(false);
                itemPriceSqlParams.priceDescription = stringResource2;
                itemPriceSqlParams.cmDateSql = z ? "" : getOnlyChangeSql(ItemPrice.class, "SEK.KAYITTARIHI", "SEK.DUZELTMETARIHI");
                sb2.append(buildGetPriceSql(itemPriceSqlParams));
            }
        }
        StringUtils.logLargeContent(str, sb2.toString());
        return NetsisSelectResult.newBuilder().withSql(sb2.toString()).withProcessType(ProcessType.GETPRICE).withDatabaseSave(true).withTableDelete(TextUtils.isEmpty(itemPriceSqlParams.cmDateSql)).build();
    }

    private String buildGetPriceSql(ItemPriceSqlParams itemPriceSqlParams) {
        String str;
        String priceSql = getPriceSql(itemPriceSqlParams.isGetPriceFromList(), itemPriceSqlParams.cmDateSql, itemPriceSqlParams.isGetPriceFromList() ? "SEK.FIYAT1" : "SATIS_FIAT1", itemPriceSqlParams.isGetPriceFromList() ? "SEK.FIYATDOVIZTIPI" : itemPriceSqlParams.isUseSalesPriceAsCurrencyPrice() ? "SAT_DOV_TIP" : "0", ContextUtils.formatStringEnglish("%s %d", itemPriceSqlParams.priceDescription, 1), (itemPriceSqlParams.isGetPriceFromList() || itemPriceSqlParams.isUseSalesPriceAsCurrencyPrice()) ? itemPriceSqlParams.getNextPriority() : itemPriceSqlParams.getPriorityWithGap());
        boolean isGetPriceFromList = itemPriceSqlParams.isGetPriceFromList();
        String str2 = itemPriceSqlParams.cmDateSql;
        String str3 = itemPriceSqlParams.isGetPriceFromList() ? "SEK.FIYAT2" : "SATIS_FIAT2";
        String str4 = itemPriceSqlParams.isGetPriceFromList() ? "SEK.FIYATDOVIZTIPI" : itemPriceSqlParams.isUseSalesPriceAsCurrencyPrice() ? "SAT_DOV_TIP" : "0";
        String formatStringEnglish = ContextUtils.formatStringEnglish("%s %d", itemPriceSqlParams.priceDescription, 2);
        if (!itemPriceSqlParams.isGetPriceFromList()) {
            itemPriceSqlParams.isUseSalesPriceAsCurrencyPrice();
        }
        String priceSql2 = getPriceSql(isGetPriceFromList, str2, str3, str4, formatStringEnglish, itemPriceSqlParams.getNextPriority());
        boolean isGetPriceFromList2 = itemPriceSqlParams.isGetPriceFromList();
        String str5 = itemPriceSqlParams.cmDateSql;
        String str6 = itemPriceSqlParams.isGetPriceFromList() ? "SEK.FIYAT3" : "SATIS_FIAT3";
        String str7 = itemPriceSqlParams.isGetPriceFromList() ? "SEK.FIYATDOVIZTIPI" : itemPriceSqlParams.isUseSalesPriceAsCurrencyPrice() ? "SAT_DOV_TIP" : "0";
        String formatStringEnglish2 = ContextUtils.formatStringEnglish("%s %d", itemPriceSqlParams.priceDescription, 3);
        if (!itemPriceSqlParams.isGetPriceFromList()) {
            itemPriceSqlParams.isUseSalesPriceAsCurrencyPrice();
        }
        String priceSql3 = getPriceSql(isGetPriceFromList2, str5, str6, str7, formatStringEnglish2, itemPriceSqlParams.getNextPriority());
        boolean isGetPriceFromList3 = itemPriceSqlParams.isGetPriceFromList();
        String str8 = itemPriceSqlParams.cmDateSql;
        String str9 = itemPriceSqlParams.isGetPriceFromList() ? "SEK.FIYAT4" : "SATIS_FIAT4";
        String str10 = itemPriceSqlParams.isGetPriceFromList() ? "SEK.FIYATDOVIZTIPI" : itemPriceSqlParams.isUseSalesPriceAsCurrencyPrice() ? "SAT_DOV_TIP" : "0";
        String formatStringEnglish3 = ContextUtils.formatStringEnglish("%s %d", itemPriceSqlParams.priceDescription, 4);
        if (!itemPriceSqlParams.isGetPriceFromList()) {
            itemPriceSqlParams.isUseSalesPriceAsCurrencyPrice();
        }
        String priceSql4 = getPriceSql(isGetPriceFromList3, str8, str9, str10, formatStringEnglish3, itemPriceSqlParams.getNextPriority());
        if (itemPriceSqlParams.isGetPriceFromList()) {
            str = "";
        } else {
            str = " UNION (" + getPriceSql(false, itemPriceSqlParams.cmDateSql, "DOV_SATIS_FIAT", "SAT_DOV_TIP", itemPriceSqlParams.currencyPriceDescription, "99") + ")";
        }
        return ContextUtils.formatStringEnglish(R.string.net_get_item_prices, priceSql, priceSql2, priceSql3, priceSql4, str, itemPriceSqlParams.localCurrency);
    }

    private String getPriceSql(boolean z, String str, String str2, String str3, String str4, String str5) {
        int i2 = z ? R.string.net_get_prices_list_repeat : R.string.net_get_prices_repeat;
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_SqlSentence);
        if (!TextUtils.isEmpty(paramValue)) {
            paramValue = " AND ST.STOK_KODU IN ( " + paramValue + ")";
        }
        return ContextUtils.formatStringEnglish(i2, str2, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), str, paramValue, str3, str4, str5);
    }

    private String getVarianPriceListSql(boolean z, ItemPriceSqlParams itemPriceSqlParams) {
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_SqlSentence);
        String onlyChangeSql = z ? "" : getOnlyChangeSql(ItemPrice.class, "STOKFIAT.KAYITTAR", "STOKFIAT.DEGISIKTAR");
        if (!TextUtils.isEmpty(paramValue)) {
            paramValue = " AND YPLNDRSTOKKOD IN ( " + paramValue + ")";
        }
        String str = paramValue;
        String str2 = onlyChangeSql;
        return ContextUtils.formatStringEnglish(R.string.net_get_item_prices, ContextUtils.formatStringEnglish(R.string.net_get_variant_prices_list_repeat, "V.Fiyat 1", -4, "FIYAT1", str, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), str2), ContextUtils.formatStringEnglish(R.string.net_get_variant_prices_list_repeat, "V.Fiyat 2", -3, "FIYAT2", str, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), str2), ContextUtils.formatStringEnglish(R.string.net_get_variant_prices_list_repeat, "V.Fiyat 3", -2, "FIYAT3", str, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), str2), ContextUtils.formatStringEnglish(R.string.net_get_variant_prices_list_repeat, "V.Fiyat 4", -1, "FIYAT4", str, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), str2), "", itemPriceSqlParams.localCurrency);
    }

    
    public NetsisSelectResult getItemStock(boolean z) {
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_SqlSentence);
        if (!TextUtils.isEmpty(paramValue)) {
            paramValue = " AND PH.STOK_KODU IN ( " + paramValue + ")";
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_item_stocks, Integer.valueOf(getUserSettings().getBranchCode()), paramValue)).withProcessType(ProcessType.GETSTOCK).withDatabaseSave(true).withTableDelete(true).build();
    }
 
    public NetsisSelectResult getVariantStock(boolean z) {
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_SqlSentence);
        if (!TextUtils.isEmpty(paramValue)) {
            paramValue = " AND PH.STOK_KODU IN ( " + paramValue + ")";
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_variant_stocks, Integer.valueOf(getUserSettings().getBranchCode()), paramValue)).withProcessType(ProcessType.GETVARIANTSTOCK).withDatabaseSave(true).withTableDelete(true).build();
    }
 
    public NetsisSelectResult getItemBarcode(boolean z) {
        String str;
        if (z) {
            str = "";
        } else {
            str = getOnlyChangeSql(UnitBarcode.class);
        }
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_SqlSentence);
        if (!TextUtils.isEmpty(paramValue)) {
            paramValue = " AND STOK_KODU IN ( " + paramValue + ")";
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_unit_barcode, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), str, paramValue)).withProcessType(ProcessType.GETBARCODE).withDatabaseSave(true).withTableDelete(TextUtils.isEmpty(str)).build();
    }

    
    public NetsisSelectResult getVariants(boolean z) {
        String str;
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_SqlSentence);
        if (!TextUtils.isEmpty(paramValue)) {
            paramValue = " AND ST.STOK_KODU IN ( " + paramValue + ")";
        }
        if (z) {
            str = "";
        } else {
            str = getOnlyChangeSql(Variant.class, "B.KAYITTARIHI", "B.DUZELTMETARIHI");
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_variant, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), paramValue, str, "")).withOrderBy(ContextUtils.getStringResource(R.string.net_get_variant_order_by)).withProcessType(ProcessType.GETVARIANT).withDatabaseSave(true).withTableDelete(TextUtils.isEmpty(str)).build();
    }

    
    public NetsisSelectResult getVariantUnits(boolean z) {
        return NetsisSelectResult.newBuilder().withProcessType(ProcessType.GETVARIANTUNIT).withDatabaseSave(true).withTableDelete(true).build();
    }

    
    public NetsisSelectResult getWareHouse() {
        String str;
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptGeneralSettings_Warehouse);
        if (!paramValue.isEmpty()) {
            str = " AND DEPO_KODU IN ( " + paramValue + SqlLiteVariable._CLOSE_BRACKET;
        } else {
            str = " AND DEPO_KODU = '" + paramValue + "'";
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_warehouses, Integer.valueOf(getUserSettings().getBranchCode()), str)).withProcessType(ProcessType.GETWAREHOUSE).withDatabaseSave(true).withTableDelete(true).build();
    }

    
    public NetsisSelectResult getSpeCodes() {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_speCodes, Integer.valueOf(getUserSettings().getEnterpriseCode()))).withProcessType(ProcessType.GETSPECODE).withTableDelete(true).withDatabaseSave(true).build();
    }

    public NetsisSelectResult getSpeCodePrices() {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_specode_prices)).withProcessType(ProcessType.GETSPECODEPRICES).withTableDelete(true).withDatabaseSave(true).build();
    }

    public NetsisSelectResult getDiscounts(boolean z, boolean z2) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(z ? R.string.net_get_detail_discount : R.string.net_get_general_discounts, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()))).withProcessType(ProcessType.GETDISCOUNT).withTableDelete(z2).withDatabaseSave(true).build();
    }

    public NetsisSelectResult getGeneralDiscounts(boolean z) {
        return getDiscounts(false, true);
    }

    public NetsisSelectResult getDetailDiscounts(boolean z) {
        return getDiscounts(true, true);
    }

    
    public NetsisSelectResult getPayments(boolean z) {
        String str;
        if (z) {
            str = "";
        } else {
            str = getOnlyChangeSql(Payment.class, "ODM.KAYITTARIHI", "ODM.DUZELTMETARIHI");
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_payments, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), str)).withProcessType(ProcessType.GETPAYMENT).withDatabaseSave(true).withTableDelete(TextUtils.isEmpty(str)).build();
    }

    
    public NetsisSelectResult getShipAddress(boolean z) {
        String str;
        String str2 = "";
        if (z) {
            str = "";
        } else {
            str = getOnlyChangeSql(ShipAddress.class, "CS.KAYITTARIHI", "CS.DUZELTMETARIHI");
        }
        String customersFilter = getCustomersFilter("CS");
        boolean isEmpty = TextUtils.isEmpty(str);
        if (ErpCreator.getInstance().getmBaseErp().getSevkiyatHesabiTeslimatCari()) {
            str2 = "OR CS.ISLETME_KODU =-3";
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_ship_address, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), str, customersFilter, str2)).withProcessType(ProcessType.GETSHIPADDRESS).withDatabaseSave(true).withTableDelete(isEmpty).build();
    }

    
    public NetsisSelectResult getBanks(boolean z) {
        String str;
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptBanks_SQLSentence);
        if (z) {
            str = "";
        } else {
            str = getOnlyChangeSql(Bank.class);
        }
        String str2 = str;
        if (!paramValue.equals("")) {
            str2 = str2 + " AND NETBANKAKODU IN (" + paramValue + ")";
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_banks, str2)).withProcessType(ProcessType.GETBANK).withDatabaseSave(true).withTableDelete(TextUtils.isEmpty(str)).build();
    }

    
    public NetsisSelectResult getBankAccounts(boolean z) {
        String str;
        if (z) {
            str = "";
        } else {
            str = getOnlyChangeSql(BankAccount.class);
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_bank_accounts, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), str)).withProcessType(ProcessType.GETBANKACCOUNT).withDatabaseSave(true).withTableDelete(TextUtils.isEmpty(str)).build();
    }

    
    public NetsisSelectResult getCurrTypes() {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_curr_type)).withProcessType(ProcessType.GETCURRTYPE).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public NetsisSelectResult getCurrRates() {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_curr_rate)).withProcessType(ProcessType.GETCURRRATE).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public NetsisSelectResult getLoginUserInformation() {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_login_query, Integer.valueOf(getUser().getUserId()), getUser().getUserName(), Integer.valueOf(getUserSettings().getEnterpriseCode()), getUser().getPassword().replace("'", "''"))).withProcessType(ProcessType.LOGIN).withDatabaseSave(false).withTableDelete(false).build();
    }

    public NetsisSelectResult getUserAuthorizations(String str, String str2) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.app_get_user_authorizations, Integer.valueOf(getUser().getUserId()), str, str2)).withProcessType(ProcessType.GETACTIVEPERIOD).withDatabaseSave(false).withTableDelete(false).build();
    }

    
    public NetsisSelectResult getSalesMansList() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT PLASIYER_KODU AS CODE, PLASIYER_ACIKLAMA AS DEFINITION_ FROM TBLCARIPLASIYER WHERE ISLETME_KODU = " + getUserSettings().getEnterpriseCode() + " AND SUBE_KODU = " + getUserSettings().getBranchCode());
        String[] salesManagerSalesmanFilter = ErpCreator.getInstance().getmBaseErp().getSalesManagerSalesmanFilter();
        if (!TextUtils.isEmpty(salesManagerSalesmanFilter[0]) && !TextUtils.isEmpty(salesManagerSalesmanFilter[1])) {
            sb.append(" AND PLASIYER_KODU BETWEEN '" + salesManagerSalesmanFilter[0] + "' AND '" + salesManagerSalesmanFilter[1] + "' ");
        }
        return NetsisSelectResult.newBuilder().withSql(sb.toString()).withProcessType(ProcessType.GETSALESMANS).withOrderBy("ORDER BY PLASIYER_KODU").withDatabaseSave(false).build();
    }

    
    public NetsisSelectResult getSalesOrderList(SalesType salesType, int i2, boolean z, ArrayList arrayList) {
        String str;
        String str2;
        String str3;
        String str4;
        if (!z) {
            str = "";
            str2 = str;
        } else {
            str = ",STK.STOK_KODU AS STOCKCODE ,STK.STOK_ADI AS STOCKNAME ,(SIPATRA.STHAR_GCMIK - (SIPATRA.FIRMA_DOVTUT + SIPATRA.FIRMA_DOVMAL)) AS AVAILABLEAMOUNT ,SIPATRA.INCKEYNO AS ORDERDETAILREF ";
            str2 = "LEFT OUTER JOIN TBLSTSABIT STK WITH (NOLOCK) ON STK.STOK_KODU = SIPATRA.STOK_KODU ";
        }
        String orderShipmentType = ErpCreator.getInstance().getmBaseErp().getOrderShipmentType(salesType);
        if (orderShipmentType.trim().length() <= 0 || !orderShipmentType.equals(BuildConfig.NETSIS_DEMO_PASSWORD)) {
            str3 = "";
        } else {
            str3 = "AND SIPAMAS.PLA_KODU = '" + getUser().getCode() + "' ";
        }
        if (arrayList != null && arrayList.size() > 0) {
            if (z) {
                str3 = str3 + "AND SIPATRA.INCKEYNO NOT IN ( " + TextUtils.join(",", arrayList) + SqlLiteVariable._CLOSE_BRACKET;
            } else {
                str3 = str3 + "AND (SIPATRA.FISNO NOT IN (SELECT FISNO FROM TBLSIPATRA WHERE INCKEYNO IN ( " + TextUtils.join(",", arrayList) + " )))";
            }
        }
        String str5 = str3 + " AND SIPATRA.DEPO_KODU IN (" + getSqlHelper().getParamValue(ParameterTypes.ptGeneralSettings_Warehouse) + ")";
        NetsisSelectResult.NetsisBuilder newBuilder = NetsisSelectResult.newBuilder();
        if (z) {
            str4 = "";
        } else {
            str4 = " DISTINCT ";
        }
        if (!z) {
            str = "";
        }
        return newBuilder.withSql(ContextUtils.getStringResource(R.string.net_get_sales_order_list, str4, str, z ? str2 : "", ErpCreator.getInstance().getmBaseErp().getCustomerClCode(i2), str5)).withProcessType(ProcessType.GETSALESORDER).withDatabaseSave(false).withTableDelete(false).build();
    }

    
    /* renamed from: getOrderAvailableAmounts */
    public NetsisSelectResult getOrderAvailableAmounts2(ArrayList<String> arrayList) {
        String str = arrayList.get(0).split(";")[0];
        String str2 = arrayList.get(0).split(";")[2];
        String[] strArr = new String[arrayList.size()];
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptGeneralSettings_Warehouse);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            strArr[i2] = "'" + arrayList.get(i2).split(";")[1] + "'";
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_order_available_amount, str, TextUtils.join(",", strArr), str2, paramValue)).withProcessType(ProcessType.GETORDERAVAILABLEAMOUNT).withDatabaseSave(false).build();
    }

    
    public NetsisSelectResult getOrderAvailableAmountsFromDetailRef(int i2) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_order_available_amount_from_detail, Integer.valueOf(i2))).withProcessType(ProcessType.GETORDERAVAILABLEAMOUNT).withDatabaseSave(false).build();
    }

    
    public NetsisSelectResult getOrderAvailableAmountsFromDetailWithRefs(ArrayList arrayList) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_order_available_amount_from_detail_with_refs, TextUtils.join(",", arrayList))).withProcessType(ProcessType.GETORDERAVAILABLEAMOUNT).withDatabaseSave(false).build();
    }

    
    public NetsisSelectResult getProjects() {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_projects, Integer.valueOf(getUserSettings().getEnterpriseCode()))).withProcessType(ProcessType.GETPROJECTS).withDatabaseSave(true).withTableDelete(true).build();
    }

    
    public NetsisSelectResult getCases() {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_cases, Integer.valueOf(getUserSettings().getBranchCode()), StringUtils.encloseWithQuotes(getSqlHelper().getParamValue(ParameterTypes.ptSafeDeposits)))).withProcessType(ProcessType.GETCASE).withDatabaseSave(true).withTableDelete(true).build();
    }

    public NetsisSelectResult updateOrderStatus(List<String> list, NetsisInvoiceType netsisInvoiceType) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_update_order_status, Integer.valueOf(netsisInvoiceType.ordinal()), StringUtils.catStringSpecial(",", list), Integer.valueOf(getUserSettings().getBranchCode()), Integer.valueOf(getUserSettings().getEnterpriseCode()))).withProcessType(ProcessType.UPDATESALESORDER).build();
    }

    public NetsisSelectResult updateOrderLinesAsRejected(String str, String str2) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_update_order_line_rejected, str, str2, Integer.valueOf(getUserSettings().getBranchCode()))).withProcessType(ProcessType.UPDATESALESORDER).build();
    }

    private NetsisSelectResult getNetsisParameters() {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_netsis_params, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()))).withProcessType(ProcessType.GETLOGOTRADEPARAMS).withTableDelete(true).withDatabaseSave(true).build();
    }

    public NetsisSelectResult deleteBeforeCustomerGpsLocation(CustGpsInfo custGpsInfo) {
        if (getUser().getPanelVersion() < 16200) {
            return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_app_delete_customer_gps_location, custGpsInfo.clCode)).withProcessType(ProcessType.SUCSESS).build();
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_app_delete_customer_gps_location_with_firmNr, custGpsInfo.clCode, String.format("%s|%s", getUser().getFirmNr(), Integer.valueOf(getUser().getPeridodNrInt())))).withProcessType(ProcessType.SUCSESS).build();
    }

    @Override // com.proje.mobilesales.core.interfaces.AppQuerable
    public NetsisSelectResult insertCustomerGpsLocation(CustGpsInfo custGpsInfo) {
        if (getUser().getPanelVersion() < 16200) {
            return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.app_insert_customer_gps_location, Integer.valueOf(custGpsInfo.clientRef), Double.valueOf(custGpsInfo.latitude), Double.valueOf(custGpsInfo.longtitude), custGpsInfo.clCode, custGpsInfo.clName)).withProcessType(ProcessType.SUCSESS).build();
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_app_insert_customer_gps_location_with_firmNr, Integer.valueOf(custGpsInfo.clientRef), Double.valueOf(custGpsInfo.latitude), Double.valueOf(custGpsInfo.longtitude), custGpsInfo.clCode, custGpsInfo.clName, String.format("%s|%s", getUser().getFirmNr(), Integer.valueOf(getUser().getPeridodNrInt())))).withProcessType(ProcessType.SUCSESS).build();
    }

    @Override // com.proje.mobilesales.core.interfaces.AppQuerable
    public NetsisSelectResult insertVisit(VisitInfo visitInfo) {
        String str = !TextUtils.isEmpty(visitInfo.clCode) ? visitInfo.clCode : "";
        String clName = getSqlHelper().getClName(visitInfo.clCode);
        if (FDateUtils.getInstance().getFDateModel().getAppVersionOnWorProcess() == 0) {
            return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_app_insert_visit, DateAndTimeUtils.convertDateSqlDate(visitInfo.visitDate), Integer.valueOf(getUser().getUserId()), visitInfo.clCode, 9, 0, 0, visitInfo.reason.replace("'", "''"), visitInfo.note.replace("'", "''"), 0, str, clName.replace("'", "''"), visitInfo.clCode, TextUtils.isEmpty(visitInfo.enlem) ? IdManager.DEFAULT_VERSION_NAME : visitInfo.enlem, TextUtils.isEmpty(visitInfo.boylam) ? IdManager.DEFAULT_VERSION_NAME : visitInfo.boylam, Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())), Integer.valueOf(visitInfo.userTitle), CompressUtil.base64CompressImage(visitInfo.visitImage), org.springframework.util.StringUtils.isEmpty(visitInfo.startDate) ? null : org.springframework.util.StringUtils.quote(DateAndTimeUtils.convertDateSqlDate(visitInfo.startDate)), org.springframework.util.StringUtils.isEmpty(visitInfo.endDate) ? null : org.springframework.util.StringUtils.quote(DateAndTimeUtils.convertDateSqlDate(visitInfo.endDate)), Integer.valueOf(visitInfo.logicalRef))).withLogicalRef(visitInfo.id).withClCode(str).withClName(clName).withProcessType(ProcessType.INSERTVISIT).build();
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_app_insert_visit_withVersion, DateAndTimeUtils.convertDateSqlDate(visitInfo.visitDate), Integer.valueOf(getUser().getUserId()), visitInfo.clCode, 9, 0, 0, visitInfo.reason.replace("'", "''"), visitInfo.note.replace("'", "''"), 0, str, clName.replace("'", "''"), visitInfo.clCode, TextUtils.isEmpty(visitInfo.enlem) ? IdManager.DEFAULT_VERSION_NAME : visitInfo.enlem, TextUtils.isEmpty(visitInfo.boylam) ? IdManager.DEFAULT_VERSION_NAME : visitInfo.boylam, Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())), Integer.valueOf(visitInfo.userTitle), CompressUtil.base64CompressImage(visitInfo.visitImage), org.springframework.util.StringUtils.isEmpty(visitInfo.startDate) ? null : org.springframework.util.StringUtils.quote(DateAndTimeUtils.convertDateSqlDate(visitInfo.startDate)), org.springframework.util.StringUtils.isEmpty(visitInfo.endDate) ? null : org.springframework.util.StringUtils.quote(DateAndTimeUtils.convertDateSqlDate(visitInfo.endDate)), Integer.valueOf(visitInfo.logicalRef), BuildConfig.VERSION_NAME)).withLogicalRef(visitInfo.id).withClCode(str).withClName(clName).withProcessType(ProcessType.INSERTVISIT).build();
    }

    @Override // com.proje.mobilesales.core.interfaces.AppQuerable
    public NetsisSelectResult insertTodoWorProc(TodoInfoDb todoInfoDb) {
        String clCode = getSqlHelper().getClCode(todoInfoDb.clRef);
        String clName = getSqlHelper().getClName(todoInfoDb.clRef);
        if (FDateUtils.getInstance().getFDateModel().getAppVersionOnWorProcess() == 0) {
            return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_app_insert_todoworproc, DateAndTimeUtils.convertDateSqlDate(todoInfoDb.begDate), Integer.valueOf(getUser().getUserId()), Integer.valueOf(todoInfoDb.clRef), 13, todoInfoDb.getDesc().replace("'", "''"), todoInfoDb.note.replace("'", "''"), clCode, clName.replace("'", "''"), Double.valueOf(todoInfoDb.latitude), Double.valueOf(todoInfoDb.longtitude), Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())), 0, 0)).withLogicalRef(todoInfoDb.logicalRef).withProcessType(ProcessType.INSERTWORPROCESS).build();
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_app_insert_todoworproc_withVersion, DateAndTimeUtils.convertDateSqlDate(todoInfoDb.begDate), Integer.valueOf(getUser().getUserId()), Integer.valueOf(todoInfoDb.clRef), 13, todoInfoDb.getDesc().replace("'", "''"), todoInfoDb.note.replace("'", "''"), clCode, clName.replace("'", "''"), Double.valueOf(todoInfoDb.latitude), Double.valueOf(todoInfoDb.longtitude), Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())), 0, 0, BuildConfig.VERSION_NAME)).withLogicalRef(todoInfoDb.logicalRef).withProcessType(ProcessType.INSERTWORPROCESS).build();
    }

    @Override // com.proje.mobilesales.core.interfaces.AppQuerable
    public String insertPenetrationHeader(Penetration penetration) {
        String str;
        Integer valueOf = Integer.valueOf(penetration.getPenetrationId());
        String clCode = penetration.getClCode();
        Integer valueOf2 = Integer.valueOf(getUser().getUserId());
        if (penetration.getImage().getImageArray() == null) {
            str = "null";
        } else {
            str = "CONVERT(VARBINARY(MAX),' " + CompressUtil.base64CompressImage(penetration.getImage().getImageArray()) + "')";
        }
        return ContextUtils.formatStringEnglish(R.string.net_app_insert_penetration_header, valueOf, clCode, valueOf2, str, penetration.getNot().toString(), DateAndTimeUtils.convertDateSqlDate(penetration.getFicheDate()), penetration.getPnt_GUID());
    }

    @Override // com.proje.mobilesales.core.interfaces.AppQuerable
    public String insertPenetrationTrans(Penetration penetration, PenetrationLine penetrationLine) {
        String str;
        String ficheStringProp = penetration.isExist() ? BuildConfig.NETSIS_DEMO_PASSWORD : penetrationLine.getAmount().toString();
        Integer valueOf = Integer.valueOf(penetration.getPenetrationId());
        Integer valueOf2 = Integer.valueOf(penetrationLine.getPenetrationDetailId());
        String clCode = penetration.getClCode();
        Integer valueOf3 = Integer.valueOf(getUser().getUserId());
        String pnt_GUID = penetration.getPnt_GUID();
        String ficheStringProp2 = penetrationLine.getPrice().toString();
        String ficheStringProp3 = penetrationLine.getNot().toString();
        if (penetrationLine.getImage().getImageArray() == null) {
            str = "null";
        } else {
            str = "CONVERT(VARBINARY(MAX),' " + CompressUtil.base64CompressImage(penetrationLine.getImage().getImageArray()) + "')";
        }
        return ContextUtils.formatStringEnglish(R.string.net_app_insert_penetration_trans, valueOf, valueOf2, clCode, valueOf3, pnt_GUID, ficheStringProp2, ficheStringProp3, str, ficheStringProp, DateAndTimeUtils.convertDateSqlDate(penetration.getFicheDate()));
    }

    @Override // com.proje.mobilesales.core.interfaces.AppQuerable
    public String getFicheProcess(BaseServiceResult baseServiceResult, int i2, double[] dArr, int i3) {
        NetsisServiceResult netsisServiceResult = (NetsisServiceResult) baseServiceResult;
        if (FDateUtils.getInstance().getFDateModel().getAppVersionOnWorProcess() == 0) {
            return ContextUtils.formatStringEnglish(R.string.net_app_insert_fiche_process, getFormattedDate(baseServiceResult), Integer.valueOf(getUser().getUserId()), baseServiceResult.getClCode(), Integer.valueOf(i2), netsisServiceResult.getFicheNo(), baseServiceResult.getClCode(), baseServiceResult.getClName().replace("'", "''"), Double.valueOf(dArr[0]), Double.valueOf(dArr[1]), Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())), Integer.valueOf(i3));
        }
        return ContextUtils.formatStringEnglish(R.string.net_app_insert_fiche_process_withVersion, getFormattedDate(baseServiceResult), Integer.valueOf(getUser().getUserId()), baseServiceResult.getClCode(), Integer.valueOf(i2), netsisServiceResult.getFicheNo(), baseServiceResult.getClCode(), baseServiceResult.getClName().replace("'", "''"), Double.valueOf(dArr[0]), Double.valueOf(dArr[1]), Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())), Integer.valueOf(i3), BuildConfig.VERSION_NAME);
    }

    private String getFormattedDate(BaseServiceResult baseServiceResult) {
        int i2 = C25271.SwitchMapcomprojemobilesalescoreenumsDataObjectType[baseServiceResult.getDataType().ordinal()];
        if (i2 == 1 || i2 == 2) {
            return DateAndTimeUtils.convertStringDate(baseServiceResult.getDate(), "dd.MM.yyyy", "yyyy-MM-dd HH:mm:ss");
        }
        return DateAndTimeUtils.convertStringDate(baseServiceResult.getDate(), "dd.MM.yyyy HH:mm:ss", "yyyy-MM-dd HH:mm:ss");
    }

    public NetsisSelectResult deletePenetrationHeader(String str) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.app_delete_customer_penetration_header, str)).withProcessType(ProcessType.SUCSESS).build();
    }

    public NetsisSelectResult deletePenetrationDetail(String str) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.app_delete_customer_penetration_detail, str)).withProcessType(ProcessType.SUCSESS).build();
    }

    @Override // com.proje.mobilesales.core.interfaces.AppQuerable
    public String insertPenetrationLine(Penetration penetration, PenetrationLine penetrationLine) {
        return ContextUtils.formatStringEnglish(R.string.net_app_insert_penetration, Integer.valueOf(penetration.getPenetrationId()), Integer.valueOf(penetrationLine.getPenetrationDetailId()), Integer.valueOf(getUser().getUserId()), penetration.getClCode(), DateAndTimeUtils.convertDateSqlDate(penetration.getFicheDate()), Integer.valueOf(penetrationLine.getExist().isSelect() ? 1 : 0), penetrationLine.getAmount().toString(), penetrationLine.getPrice().toString(), penetrationLine.getCurrency().getCode(), 0, "", "", "", "", "", "", "", "", "", "", CompressUtil.base64CompressImage(penetrationLine.getImage().getImageArray()));
    }

    
    public NetsisSelectResult getCreditAggrs() {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_credit_aggrs, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()))).withProcessType(ProcessType.GETCREDITAGGRS).withDatabaseSave(true).withTableDelete(true).build();
    }

    
    public NetsisSelectResult getMuhRefCodes() {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_muhrefcodes, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()))).withProcessType(ProcessType.GETMUHREFCODES).withDatabaseSave(true).withTableDelete(true).build();
    }

    
    public NetsisSelectResult getOrderGrossTotal(int i2) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_order_gross_total, Integer.valueOf(i2))).withProcessType(ProcessType.GETORDERTOFICHETOTAL).withTableDelete(false).withDatabaseSave(false).build();
    }

    public NetsisSelectResult getMaxMatterNo(FicheType ficheType, MatterSettings matterSettings) {
        String str = " AND FTIRSIP = '1'";
        String str2 = "TBLFATUIRS";
        String str3 = "FATIRS_NO";
        if (!FicheTypeControlUtils.isFicheTypeOnlyInvoice(ficheType)) {
            if (FicheTypeControlUtils.isFicheTypeDispatch(ficheType)) {
                str = " AND FTIRSIP = '3'";
            } else if (FicheTypeControlUtils.isFicheTypeOrder(ficheType)) {
                str2 = "TBLSIPAMAS";
                str = " AND FTIRSIP = '6'";
            } else {
                if (FicheTypeControlUtils.isFicheTypeCashReceipt(ficheType)) {
                    str = " AND TIP = 'C' AND KRTSOZMASINCKEYNO=0";
                } else if (FicheTypeControlUtils.isFicheTypeCreditReceipt(ficheType)) {
                    str = " AND TIP = 'C' AND KRTSOZMASINCKEYNO<>0";
                } else {
                    if (FicheTypeControlUtils.isFicheTypeCheckReceipt(ficheType)) {
                        str2 = "TBLMCEK";
                    } else if (FicheTypeControlUtils.isFicheTypeDeedReceipt(ficheType)) {
                        str2 = "TBLMSEN";
                    } else if (FicheTypeControlUtils.isFicheTypeCaseReceipt(ficheType)) {
                        str = " AND TIP = 'C' AND KRTSOZMASINCKEYNO IS NULL";
                    } else if (!FicheTypeControlUtils.isFicheTypeOnlyRetailInvoice(ficheType)) {
                        str = "";
                        str2 = str;
                        str3 = str2;
                    }
                    str3 = "SC_ALB_NO";
                    str = "";
                }
                str3 = "FISNO";
                str2 = "TBLKASA";
            }
        }
        return NetsisSelectResult.newBuilder().withSql(getMaxMatterNoAreaSql(matterSettings, str2, str3, str)).withProcessType(ProcessType.GETMAXPRINTMATTERAREA).build();
    }

    private String getMaxMatterNoAreaSql(MatterSettings matterSettings, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2)) {
            str2 = "FATIRS_NO";
        }
        return ContextUtils.getmContext().getString(R.string.net_select_max_matter_no, matterSettings.getFirstMatterNo(), matterSettings.getLastMatterNo(), str, str2, str3);
    }

    public NetsisSelectResult getSalesOrderValidationList(String[] strArr, int i2, int i3, String str) {
        String str2;
        try {
            String formatStringEnglish = ContextUtils.formatStringEnglish(R.string.get_qry_where_paging_str, Integer.valueOf(i2), Integer.valueOf(i3));
            if (!TextUtils.isEmpty(str)) {
                str = String.format(ContextUtils.getStringResource(R.string.net_online_get_order_list_filter_qry), str, str.toLowerCase(), str.toUpperCase());
            }
            str2 = ContextUtils.getmContext().getString(R.string.net_select_sales_order, Integer.valueOf(getUserSettings().getBranchCode()), Integer.valueOf(getUserSettings().getEnterpriseCode()), getSalesmansFilterInSql(0), str, formatStringEnglish, getCustomersFilter("CASABIT"));
        } catch (Exception e2) {
            Log.e(TAG, "getSalesOrder: ", e2);
            str2 = "";
        }
        return NetsisSelectResult.newBuilder().withSql(str2).withProcessType(ProcessType.GETSALESORDER).withTableDelete(false).withDatabaseSave(false).build();
    }

    public static @UnknownNullability NetsisServiceResult getVisitLogicalRef(int i2) {
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(VisitInfo.class, "ID=?", new String[]{String.valueOf(i2)});
        VisitInfo visitInfo = new VisitInfo();
        if (table != null && table.size() > 0) {
            visitInfo = (VisitInfo) table.get(0);
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish("SELECT ID AS LOGICALREF FROM WOR_PROCESS WHERE PTYPE=9 AND DATE_ = CONVERT(datetime,'%1s',120) AND USERID = %2d AND CLIENTREF = '%3s'", DateAndTimeUtils.convertDateSqlDate(visitInfo.visitDate), Integer.valueOf(ErpCreator.getInstance().getmBaseErp().getUser().getUserId()), visitInfo.clCode)).withProcessType(ProcessType.GETDOCODE).withTableDelete(false).withDatabaseSave(false).build();
    }

    public NetsisSelectResult insertCustomerInCharge(ClCardIncharge clCardIncharge) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.app_net_insert_customer_incharge, clCardIncharge.getClCode(), clCardIncharge.getInCharge(), clCardIncharge.getDefinition(), clCardIncharge.getTel(), clCardIncharge.getEMail(), clCardIncharge.getCreatedDate(), getUserSettings().getUsername(), Integer.valueOf(clCardIncharge.getActive()), clCardIncharge.getTelCode())).withProcessType(ProcessType.SUCSESS).build();
    }

    public NetsisSelectResult getCustomerInChargeRecIDAfterTransfer(ClCardIncharge clCardIncharge) {
        return NetsisSelectResult.newBuilder().withSql("SELECT RECID FROM TBLCARIEMAIL WHERE CARI_KOD = '" + clCardIncharge.getClCode() + "' AND KAYITTARIHI = '" + clCardIncharge.getCreatedDate() + "'").withProcessType(ProcessType.SUCSESS).build();
    }

    public NetsisSelectResult getCustomerExtractReport(int i2, String str, String str2, String str3) {
        String str4;
        boolean salesPersonCanSeeTheirOwnOperations = ErpCreator.getInstance().getmBaseErp().getSalesPersonCanSeeTheirOwnOperations();
        String clCode = getSqlHelper().getClCode(i2);
        if (!salesPersonCanSeeTheirOwnOperations) {
            str4 = "";
        } else {
            str4 = "  AND PLASIYER_KODU = '" + str3 + "'";
        }
        return NetsisSelectResult.newBuilder().withSql(String.format(ContextUtils.getStringResource(R.string.app_net_get_customer_extract_report), clCode, str, str2, str4)).withProcessType(ProcessType.GETREPORTEXTRACT).build();
    }

    public NetsisSelectResult getItemSurplusDiscount(int i2, String str) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_item_surplus_discount, getSqlHelper().getItemCode(i2), str)).withProcessType(ProcessType.GETITEMSURPLUSDISCOUNT).build();
    }

    
    public NetsisSelectResult getCountries() {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_countries)).withDatabaseSave(true).withTableDelete(true).withProcessType(ProcessType.GETCOUNTRIES).build();
    }

    
    public NetsisSelectResult getCities() {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_cities)).withDatabaseSave(true).withTableDelete(true).withProcessType(ProcessType.GETCITIES).build();
    }

    
    public NetsisSelectResult getTowns() {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_towns)).withDatabaseSave(true).withTableDelete(true).withProcessType(ProcessType.GETTOWNS).build();
    }

    
    public NetsisSelectResult getCustomerRiskTotals(int i2, boolean z) {
        String clCode = getSqlHelper().getClCode(i2);
        String customersFilter = getCustomersFilter("CS");
        StringBuilder sb = new StringBuilder();
        if (i2 != -1) {
            sb.append(" AND C.CARIKOD = '" + clCode + "'");
        }
        if (!TextUtils.isEmpty(customersFilter)) {
            sb.append(customersFilter);
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_customer_risk_totals, sb.toString())).withProcessType(ProcessType.GETCUSTOMERRISKTOTALS).withDatabaseSave(true).withTableDelete(z).build();
    }

    
    public String getSalesmansFilterInSql(int i2) {
        String str;
        String[] split;
        int intValue = Integer.valueOf(ErpCreator.getInstance().getmBaseErp().getUser().getType()).intValue();
        if (intValue == 2) {
            str = "";
        } else {
            str = ErpCreator.getInstance().getmBaseErp().getUser().getCode();
        }
        String[] salesManagerSalesmanFilter = ErpCreator.getInstance().getmBaseErp().getSalesManagerSalesmanFilter();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT PLS.PLASIYER_KODU FROM TBLCARIPLASIYER PLS   LEFT JOIN WOR_USERS US WITH(NOLOCK) ON PLS.PLASIYER_KODU=US.CODE WHERE (ISLETME_KODU = " + getUserSettings().getEnterpriseCode() + " AND SUBE_KODU = " + getUserSettings().getBranchCode());
        if (!str.equals("")) {
            sb.append(" AND PLS.PLASIYER_KODU = '" + str + "'");
        }
        if (i2 > 0) {
            sb.append(")");
        } else if (intValue == 2 && TextUtils.isEmpty(str) && i2 == 0) {
            if (!TextUtils.isEmpty(salesManagerSalesmanFilter[0]) && !TextUtils.isEmpty(salesManagerSalesmanFilter[1])) {
                sb.append(" AND PLS.PLASIYER_KODU BETWEEN '" + salesManagerSalesmanFilter[0] + "' AND '" + salesManagerSalesmanFilter[1] + "' ");
            }
            String paramValue = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getParamValue(ParameterTypes.ptSalesManagerSalespersonUserSpeCode);
            if (paramValue != null && !paramValue.isEmpty() && (split = paramValue.split("\\;")) != null && split.length > 0) {
                sb.append(" AND US.SPECODE IN (");
                for (String str2 : split) {
                    if (!str2.isEmpty()) {
                        sb.append("'");
                        sb.append(str2);
                        sb.append("',");
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append(")");
            }
            sb.append(") OR PLS.PLASIYER_KODU = '");
            sb.append(ErpCreator.getInstance().getmBaseErp().getUser().getCode());
            sb.append("'");
        }
        return sb.toString();
    }

    @Override // com.proje.mobilesales.core.interfaces.AppQuerable
    public NetsisSelectResult getCabins(boolean z) {
        String str;
        if (z) {
            str = "";
        } else {
            str = getOnlyChangeSql(Cabin.class, "C.CREATEDDATE", "C.MODIFIEDDATE") + " AND FIRM = '" + ErpCreator.getInstance().getmBaseErp().getUser().getFirmNr() + "'";
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.app_get_cabin, " dbo.GetWebOrderDateInt(C.CREATEDDATE,C.MODIFIEDDATE) [CMDATE], ", str)).withProcessType(ProcessType.GETCABINS).withDatabaseSave(true).withTableDelete(z).build();
    }

    @Override // com.proje.mobilesales.core.interfaces.AppQuerable
    public NetsisSelectResult getWorProcess(TransferOperationName transferOperationName, BaseServiceResult baseServiceResult) {
        return getV().withSql(ContextUtils.formatStringEnglish(R.string.net_app_get_fiche_process, ((NetsisServiceResult) baseServiceResult).getFicheNo(), baseServiceResult.getClCode(), Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())), Integer.valueOf(transferOperationName.getProcessType()), Integer.valueOf(getUser().getUserId()))).withProcessType(ProcessType.GETWORPROCESS).build();
    }

    public NetsisSelectResult getProductTopTenCustomer(String str) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_product_top_ten_customer, str, getCustomersFilter("CS"))).withProcessType(ProcessType.GETPRODUCTTOPTENCUSTOMER).build();
    }

    /* renamed from: com.proje.mobilesales.core.netsis.NetsisQueryCreator1 */
    static class C25271 {
        static final int[] SwitchMapcomprojemobilesalescoreenumsDataObjectType;
        static final int[] SwitchMapcomprojemobilesalescoreenumsTransferGetType;

        /* renamed from: SwitchMapcomprojemobilesalescorenetsissendmodelsalesNetsisSlipType */
        static final int[] f1185x2d5b644;

        static {
            int[] iArr = new int[NetsisSlipType.values().length];
            f1185x2d5b644 = iArr;
            try {
                iArr[NetsisSlipType.ftSFat.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1185x2d5b644[NetsisSlipType.ftSIrs.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1185x2d5b644[NetsisSlipType.ftLokalDepo.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1185x2d5b644[NetsisSlipType.ftSSip.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1185x2d5b644[NetsisSlipType.ftAFat.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1185x2d5b644[NetsisSlipType.ftAIrs.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[DataObjectType.values().length];
            SwitchMapcomprojemobilesalescoreenumsDataObjectType = iArr2;
            try {
                iArr2[DataObjectType.ADDCHEQUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsDataObjectType[DataObjectType.ADDDEED.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr3 = new int[TransferGetType.values().length];
            SwitchMapcomprojemobilesalescoreenumsTransferGetType = iArr3;
            try {
                iArr3[TransferGetType.OTHER_INFO.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.WAREHOUSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.TODO.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.MARKETING_MESSAGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.PENETRATION.ordinal()] = 6;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.SPECODE.ordinal()] = 7;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.PAYMENT.ordinal()] = 8;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.ITEM.ordinal()] = 9;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.ITEM_IMAGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.VARIANT.ordinal()] = 11;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.UNIT.ordinal()] = 12;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.BARCODE.ordinal()] = 13;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.FORM.ordinal()] = 14;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.STOCK.ordinal()] = 15;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.PRICE.ordinal()] = 16;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.DISCOUNT.ordinal()] = 17;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.CUSTOMER.ordinal()] = 18;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.SHIP_ADDRESS.ordinal()] = 19;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.BANK.ordinal()] = 20;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.CASE.ordinal()] = 21;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.DESIGN_FILES.ordinal()] = 22;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.CURRENCY.ordinal()] = 23;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.PROJECT.ordinal()] = 24;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.EMAIL.ordinal()] = 25;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.ERP_PARAMS.ordinal()] = 26;
            } catch (NoSuchFieldError unused34) {
            }
        }
    }

    public NetsisSelectResult getPrintSlipExtraInfo(PrintSlipParams printSlipParams) {
        int i2 = C25271.f1185x2d5b644[NetsisSlipType.values()[printSlipParams.getSlipType()].ordinal()];
        if (i2 == 1 || i2 == 2) {
            return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_printInvoiceDispatchSlipExtra, printSlipParams.getSlipNo(), Integer.valueOf(printSlipParams.getSlipType() + 1), printSlipParams.getClCode(), Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), (printSlipParams.getSlipType() == 2 || ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getClEInvoiceUser(printSlipParams.getClCode()) == 1) ? "TBLEFATURA" : "TBLEARSIV")).withProcessType(ProcessType.GETPRINTSLIPEXTRAINFO).build();
        }
        if (i2 == 3) {
            return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_printWhTransferSlipExtra, printSlipParams.getSlipNo(), Integer.valueOf(printSlipParams.getSlipType() + 3), Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()))).withProcessType(ProcessType.GETPRINTSLIPEXTRAINFO).build();
        }
        if (i2 == 4) {
            return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_printOrderSlipExtra, printSlipParams.getSlipNo(), Integer.valueOf(printSlipParams.getSlipType() - 1), printSlipParams.getClCode(), Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()))).withProcessType(ProcessType.GETPRINTSLIPEXTRAINFO).build();
        }
        return NetsisSelectResult.newBuilder().withSql("SELECT 'UNKNOWN' AS PLASIYER_ACIKLAMA").withProcessType(ProcessType.GETPRINTSLIPEXTRAINFO).build();
    }

    @Override // com.proje.mobilesales.core.interfaces.AppQuerable
    public NetsisSelectResult getDefOrder() {
        return getV().withSql(ContextUtils.getStringResource(R.string.net_app_get_deforder, Integer.valueOf(getUser().getUserId()), getUser().getFirmNr(), getUser().getCode())).withProcessType(ProcessType.GETDEFORDER).withTableDelete(true).withDatabaseSave(true).build();
    }

    @Override // com.proje.mobilesales.core.interfaces.AppQuerable
    public NetsisSelectResult getDefOrderDetail() {
        return getV().withSql(ContextUtils.getStringResource(R.string.net_app_get_deforder_detail, Integer.valueOf(getUser().getUserId()), getUser().getFirmNr())).withProcessType(ProcessType.GETDEFORDERDETAIL).withTableDelete(true).withDatabaseSave(true).build();
    }

    public NetsisSelectResult getItemGroupCodes(boolean z) {
        String str;
        if (z) {
            str = "";
        } else {
            str = getOnlyChangeSql(ItemGroupCode.class);
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_itmgrpCodes, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), str)).withProcessType(ProcessType.GETITEMGROUPCODES).withTableDelete(z).withDatabaseSave(true).build();
    }

    public NetsisSelectResult getEDocSerials() {
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_edoc_serial, Integer.valueOf(getUserSettings().getBranchCode()))).withProcessType(ProcessType.GETEDOCSERIALS).withTableDelete(true).withDatabaseSave(true).build();
    }

    public NetsisSelectResult getEDocumentStatus(ItemSlip itemSlip, boolean z) {
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_edoc_status, Integer.valueOf(itemSlip.getSlipType().ordinal() + 1), Integer.valueOf(getUserSettings().getBranchCode()), itemSlip.getSlipHeader().getSlipNo(), itemSlip.getSlipHeader().getCustomerCode(), z ? "TBLEARSIV" : "TBLEFATURA")).withProcessType(ProcessType.GETKEYVALUEPAIR).withTableDelete(false).withDatabaseSave(false).build();
    }

    public NetsisSelectResult getEDocumentShowInfo(ItemSlip itemSlip, boolean z) {
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(z ? R.string.net_get_edoc_showInfo_forEArchive : R.string.net_get_edoc_showInfo, Integer.valueOf(itemSlip.getSlipType().ordinal() + 1), Integer.valueOf(getUserSettings().getBranchCode()), itemSlip.getSlipHeader().getSlipNo(), itemSlip.getSlipHeader().getCustomerCode())).withProcessType(ProcessType.GETSHOWEDOCUMENTPARAM).withTableDelete(false).withDatabaseSave(false).build();
    }

    public NetsisSelectResult getAppVersionSql() {
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(SqlLiteVariable._SELECT + ContextUtils.getStringResource(R.string.app_get_appVersionOnWorProcess)).withProcessType(ProcessType.CHECKFDATE).withDatabaseSave(false).withTableDelete(false).build();
    }

    public static NetsisSelectResult getFicheDuplicateControlSql(ItemSlipHeader itemSlipHeader) {
        int i2 = 1;
        boolean z = itemSlipHeader.getSlipType() != NetsisSlipType.ftLokalDepo;
        switch (C25271.f1185x2d5b644[itemSlipHeader.getSlipType().ordinal()]) {
            case 1:
                break;
            case 2:
                i2 = 3;
                break;
            case 3:
                i2 = 8;
                break;
            case 4:
                i2 = 6;
                break;
            case 5:
                i2 = 2;
                break;
            case 6:
                i2 = 4;
                break;
            default:
                i2 = 0;
                break;
        }
        if (i2 == 0) {
            return null;
        }
        if (z) {
            return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(ContextUtils.getStringResource(R.string.net_get_fiche_duplicate_with_customer), itemSlipHeader.getCustomerCode(), Integer.valueOf(i2), Integer.valueOf(ErpCreator.getInstance().getmBaseErp().getUser().getPeridodNrInt()), itemSlipHeader.getAppendixExplanation15())).withProcessType(ProcessType.GETKEYVALUEPAIR).withTableDelete(false).withDatabaseSave(false).build();
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(ContextUtils.getStringResource(R.string.net_get_fiche_duplicate_without_customer), Integer.valueOf(i2), Integer.valueOf(ErpCreator.getInstance().getmBaseErp().getUser().getPeridodNrInt()), itemSlipHeader.getAppendixExplanation15())).withProcessType(ProcessType.GETKEYVALUEPAIR).withTableDelete(false).withDatabaseSave(false).build();
    }

    public BaseSelectResult getPrintMixedReceiptExtraInfo(MixedReceiptsMainParam mixedReceiptsMainParam) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_printMixedReceiptExtra, mixedReceiptsMainParam.getCaseCode(), mixedReceiptsMainParam.getDocumentNumber(), mixedReceiptsMainParam.getCustomerCode(), mixedReceiptsMainParam.getDate(), Integer.valueOf(ErpCreator.getInstance().getmBaseErp().getUser().getPeridodNrInt()), ErpCreator.getInstance().getmBaseErp().getUser().getName())).withProcessType(ProcessType.GETPRINTSLIPEXTRAINFO).build();
    }

    public NetsisSelectResult getPrintCheckAndPNotesExtraInfo(CheckAndPNotesMainParam checkAndPNotesMainParam) {
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_printCheckAndPNotesExtra, ErpCreator.getInstance().getmBaseErp().getUser().getName(), checkAndPNotesMainParam.getNetsisChequeAndDeedType() == NetsisChequeAndDeedType.csMSEN ? "TBLMSEN" : "TBLMCEK", checkAndPNotesMainParam.getBordroNo(), Integer.valueOf(ErpCreator.getInstance().getmBaseErp().getUser().getPeridodNrInt()))).withProcessType(ProcessType.GETPRINTSLIPEXTRAINFO).build();
    }

    public NetsisSelectResult getPrintSafeDepositExtraInfo(SafeDepositParam safeDepositParam) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_printSafeDepositExtra, ErpCreator.getInstance().getmBaseErp().getUser().getName(), safeDepositParam.getFicheNo(), Integer.valueOf(safeDepositParam.getBranchCode()), safeDepositParam.getCustomerCode())).withProcessType(ProcessType.GETPRINTSLIPEXTRAINFO).build();
    }

    public NetsisSelectResult getLastPurchaseInfo(LastPurchaseInfoSqlParams lastPurchaseInfoSqlParams) {
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_last_purchase_info, lastPurchaseInfoSqlParams.getItemCode(), Integer.valueOf(getUserSettings().getBranchCode()), lastPurchaseInfoSqlParams.getWhNr(), lastPurchaseInfoSqlParams.getVariantCode())).withProcessType(ProcessType.GETLASTPURCHASEINFO).build();
    }

    public NetsisSelectResult getLastItemPriceSalesPurchase(LastItemPriceSqlParams lastItemPriceSqlParams, String str) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_last_item_price_sales_purchase, str, Integer.valueOf(lastItemPriceSqlParams.getWhNr()), Integer.valueOf(getUserSettings().getBranchCode()), Integer.valueOf(getUserSettings().getEnterpriseCode()), lastItemPriceSqlParams.getCustomerCode(), lastItemPriceSqlParams.getItemCode(), lastItemPriceSqlParams.getVariantCode())).withProcessType(ProcessType.GETONLINEPRICE).build();
    }

    public NetsisSelectResult getOrderStatus(String str, String str2) {
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_order_tipi, str, str2, Integer.valueOf(getUserSettings().getBranchCode()), Integer.valueOf(getUserSettings().getEnterpriseCode()))).withProcessType(ProcessType.GETORDERFICHESTATUS).withTableDelete(false).withDatabaseSave(false).build();
    }

    public String getInsertWorBarcodesRequestSql(BaseServiceResult baseServiceResult, String str, String str2) {
        return ContextUtils.formatStringEnglish(R.string.net_app_insert_barcode_tracking, ((NetsisServiceResult) baseServiceResult).getFicheNo(), getUser().getUserName(), baseServiceResult.getClCode(), str, str2);
    }

    public NetsisSelectResult getCustomerTopTenProduct(int i2) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_customer_top_ten_product, getSqlHelper().getClCode(i2), Integer.valueOf(getUserSettings().getBranchCode()))).withProcessType(ProcessType.GETCUSTOMERTOPTENPRODUCT).withTableDelete(false).withDatabaseSave(false).build();
    }

    public NetsisSelectResult getCustomerMonthWeekSales(int i2, boolean z) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_customer_monthweek_sales, getSqlHelper().getClCode(i2), Integer.valueOf(getUserSettings().getBranchCode()), z ? "WK" : "MM")).withProcessType(ProcessType.GETCUSTOMERMONTHLYSALES).withTableDelete(false).withDatabaseSave(false).build();
    }

    public NetsisSelectResult getCustomerLastBalance(int i2) {
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.net_get_customer_last_balance, getSqlHelper().getClCode(i2), Integer.valueOf(getUserSettings().getBranchCode()), Integer.valueOf(getUserSettings().getEnterpriseCode()))).withProcessType(ProcessType.GETCUSTOMERBALANCE).withTableDelete(false).withDatabaseSave(false).build();
    }

    
    public NetsisSelectResult getUsersConnectedToMe(String str) {
        String str2;
        String str3;
        String[] salesManagerSalesmanFilter = ErpCreator.getInstance().getmBaseErp().getSalesManagerSalesmanFilter();
        String str4 = salesManagerSalesmanFilter[0];
        boolean z = true;
        String str5 = salesManagerSalesmanFilter[1];
        boolean z2 = !TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str5);
        String paramValue = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getParamValue(ParameterTypes.ptSalesManagerSalespersonUserSpeCode);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(paramValue)) {
            String[] split = paramValue.split("\\;");
            if (split.length > 0) {
                for (String str6 : split) {
                    if (!TextUtils.isEmpty(str6)) {
                        sb.append("'");
                        sb.append(str6);
                        sb.append("',");
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        if (!z2 && TextUtils.isEmpty(paramValue) && sb.length() == 0) {
            str3 = "NODATA";
            str2 = str3;
        } else {
            str2 = str5;
            str3 = str4;
            z = z2;
        }
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_usersConnectedToMe, Integer.valueOf(getUserSettings().getEnterpriseCode()), Integer.valueOf(getUserSettings().getBranchCode()), z ? "X" : "", str3, str2, TextUtils.isEmpty(paramValue) ? "" : "X", sb.length() == 0 ? "''" : sb.toString(), str, Integer.valueOf(getUser().getUserId()))).withProcessType(ProcessType.GETCONNECTEDUSERS).withTableDelete(false).withDatabaseSave(false).build();
    }

    public NetsisSelectResult getSerialLotList(SerialLotListQueryParam serialLotListQueryParam) {
        Item itemInfo = ErpCreator.getInstance().getmBaseErp().getItemInfo(serialLotListQueryParam.itemRef());
        String str = "HAVING(SUM(ISNULL(TMP.M1, 0)) - SUM(ISNULL(TMP.M2, 0))) > 0";
        String str2 = "";
        if (itemInfo.trackType == TrackType.SERIAL.getType()) {
            if (SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(serialLotListQueryParam.salesType())) {
                str = "HAVING(SUM(ISNULL(TMP.M1, 0)) - SUM(ISNULL(TMP.M2, 0))) <= 0";
            }
        } else if (itemInfo.trackType != TrackType.LOT.getType() || SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(serialLotListQueryParam.salesType())) {
            str = "";
        }
        if (!TextUtils.isEmpty(serialLotListQueryParam.searchKeyword())) {
            if (serialLotListQueryParam.forBarcode()) {
                str2 = " AND SERI_NO = '" + serialLotListQueryParam.searchKeyword() + "'";
            } else {
                str2 = " AND SERI_NO = '%" + serialLotListQueryParam.searchKeyword() + "%'";
            }
        }
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_serialLot_list, Integer.valueOf(getUserSettings().getBranchCode()), Integer.valueOf(serialLotListQueryParam.whNr()), itemInfo.code, str2, str)).withProcessType(ProcessType.SERIALLOT).withTableDelete(false).withDatabaseSave(false).build();
    }

    public BaseSelectResult updateWorProcess(String str, int i2) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.update_wor_process, DateAndTimeUtils.convertDateSqlDate(str), Integer.valueOf(i2))).withDatabaseSave(false).withTableDelete(false).withProcessType(ProcessType.INSERTWORPROCESS).build();
    }

    public NetsisSelectResult getLastOrderProducts(int i2, boolean z) {
        String clCode = getSqlHelper().getClCode(i2);
        String str = "SELECT CONVERT(VARCHAR, S.STHAR_TARIH, 4) AS DATE, S.STOK_KODU AS STOCKCODE, ST.STOK_ADI AS STOCKNAME, S.STHAR_GCMIK AS AMOUNT, S.FIRMA_DOVTUT AS SHIPPEDAMOUNT, S.STHAR_BF AS PRICE, ((S.STHAR_BF - S.STHAR_NF) * S.STHAR_GCMIK) AS DISCOUNT, (S.STHAR_NF * S.STHAR_GCMIK) AS LINENET FROM TBLSIPATRA S LEFT JOIN TBLSTSABIT ST ON ST.STOK_KODU = S.STOK_KODU WHERE S.FISNO IN (SELECT TOP 1 FATIRS_NO FROM TBLSIPAMAS WHERE ";
        if (!z) {
            str = "SELECT CONVERT(VARCHAR, S.STHAR_TARIH, 4) AS DATE, S.STOK_KODU AS STOCKCODE, ST.STOK_ADI AS STOCKNAME, S.STHAR_GCMIK AS AMOUNT, S.FIRMA_DOVTUT AS SHIPPEDAMOUNT, S.STHAR_BF AS PRICE, ((S.STHAR_BF - S.STHAR_NF) * S.STHAR_GCMIK) AS DISCOUNT, (S.STHAR_NF * S.STHAR_GCMIK) AS LINENET FROM TBLSIPATRA S LEFT JOIN TBLSTSABIT ST ON ST.STOK_KODU = S.STOK_KODU WHERE S.FISNO IN (SELECT TOP 1 FATIRS_NO FROM TBLSIPAMAS WHERE PLA_KODU = '" + getUser().getCode() + "' AND ";
        }
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(str + " CARI_KODU = '" + clCode + "' AND FTIRSIP = 6 ORDER BY TARIH DESC) AND STHAR_FTIRSIP = 6 ").withProcessType(ProcessType.GETLASTORDERPRODUCTS).build();
    }

    public NetsisSelectResult getItemTags() {
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_item_tags)).withProcessType(ProcessType.GETITEMTAGS).withDatabaseSave(true).withTableDelete(true).build();
    }

    public static NetsisSelectResult getRiskLimit(int i2) {
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_risk_limit, DateAndTimeUtils.dateAddToSqlDate(ErpCreator.getInstance().getmBaseErp().getAverageExpiryDay()), ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getClCode(i2))).withProcessType(ProcessType.GETRISKLIMIT).build();
    }

    public NetsisSelectResult getRecommendedProducts(int i2) {
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_recommended_products, getSqlHelper().getClCode(i2), Integer.valueOf(getUserSettings().getBranchCode()))).withProcessType(ProcessType.GETRECOMMENDEDPRODUCTS).build();
    }

    public NetsisSelectResult getEDocumentPdfHeader(String str, SalesType salesType) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_edocument_pdf_header, str, Integer.valueOf(NetsisFicheTypeNo.fromSalesType(salesType)), Integer.valueOf(getUserSettings().getBranchCode()))).withProcessType(ProcessType.GETEDOCUMENTPDFHEADERNETSIS).build();
    }

    public NetsisSelectResult getEdocumentPdfDetail(String str, SalesType salesType) {
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_edocument_pdf_detail, str, Integer.valueOf(NetsisFicheTypeNo.fromSalesType(salesType)), Integer.valueOf(getUserSettings().getBranchCode()))).withProcessType(ProcessType.GETEDOCUMENTPDFDETAILNETSIS).build();
    }

    public NetsisSelectResult getGroupCodes() {
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_group_codes, Integer.valueOf(getUserSettings().getBranchCode()), Integer.valueOf(getUserSettings().getEnterpriseCode()))).withProcessType(ProcessType.GETGROUPCODES).withTableDelete(true).withDatabaseSave(true).build();
    }

    public NetsisSelectResult getOrderFicheStatus(ArrayList<String> arrayList) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_order_status, StringUtils.formatList(arrayList, true), Integer.valueOf(getUserSettings().getBranchCode()))).withProcessType(ProcessType.GETORDERSFICHETATUS).build();
    }

    public NetsisSelectResult getCustomerDetailedRestriction() {
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_customer_detailed_restriction)).withProcessType(ProcessType.GETDETAILEDRESTRICTION).withTableDelete(true).withDatabaseSave(true).build();
    }

    @Override // com.proje.mobilesales.core.interfaces.AppQuerable
    public NetsisSelectResult execBarcodeSp(String str) {
        return NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish("exec spMSParseBarkod '%1s',0,'',''", str)).withProcessType(ProcessType.EXECWMSBARCODESP).withTableDelete(false).withDatabaseSave(false).build();
    }

    public NetsisSelectResult geProductSerialInfo(String str) {
        return (NetsisSelectResult) NetsisSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.net_get_product_serial_info, str, Integer.valueOf(getUserSettings().getBranchCode()))).withProcessType(ProcessType.GETITEMSERIALINFO).withTableDelete(false).withDatabaseSave(false).build();
    }
}
