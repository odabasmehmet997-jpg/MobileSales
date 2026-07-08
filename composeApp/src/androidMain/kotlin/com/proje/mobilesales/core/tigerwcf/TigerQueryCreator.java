package com.proje.mobilesales.core.tigerwcf;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseQueryCreator;
import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.edocument.EDocumentType;
import com.proje.mobilesales.core.enums.CustomerDocumentType;
import com.proje.mobilesales.core.enums.DataObjectType;
import com.proje.mobilesales.core.enums.DispatchGroupCode;
import com.proje.mobilesales.core.enums.ErpParamType;
import com.proje.mobilesales.core.enums.FDateField;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.OrderStatus;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.TransferGetType;
import com.proje.mobilesales.core.enums.TransferOperationName;
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
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditX;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCredit;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeed;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.Chequedeed;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.dbmodel.AddTax;
import com.proje.mobilesales.features.dbmodel.Bank;
import com.proje.mobilesales.features.dbmodel.BankAccount;
import com.proje.mobilesales.features.dbmodel.CustomerImage;
import com.proje.mobilesales.features.dbmodel.Invoice;
import com.proje.mobilesales.features.dbmodel.Order;
import com.proje.mobilesales.features.dbmodel.Payment;
import com.proje.mobilesales.features.dbmodel.PaymentLine;
import com.proje.mobilesales.features.dbmodel.Service;
import com.proje.mobilesales.features.dbmodel.ServiceUnit;
import com.proje.mobilesales.features.dbmodel.ShipAddress;
import com.proje.mobilesales.features.dbmodel.UnitBarcode;
import com.proje.mobilesales.features.dbmodel.User;
import com.proje.mobilesales.features.gpsinfo.model.database.CustGpsInfo;
import com.proje.mobilesales.features.model.FicheImageProp;
import com.proje.mobilesales.features.model.TransferGetItem;
import com.proje.mobilesales.features.model.USER;
import com.proje.mobilesales.features.model.UserSettings;
import com.proje.mobilesales.features.penetration.model.Penetration;
import com.proje.mobilesales.features.penetration.model.PenetrationLine;
import com.proje.mobilesales.features.product.model.OnlineItemPriceParameters;
import com.proje.mobilesales.features.product.model.database.Item;
import com.proje.mobilesales.features.product.model.database.ItemImage;
import com.proje.mobilesales.features.product.model.database.ItemPrice;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import com.proje.mobilesales.features.product.model.database.PurchasePrice;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import com.proje.mobilesales.features.settings.model.enums.MatterArea;
import com.proje.mobilesales.features.todo.model.database.TodoInfoDb;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
 
public abstract class TigerQueryCreator extends BaseQueryCreator<TigerSelectResult, TigerSelectResult.TigerBuilder, TigerServiceResult> {
    private static final String TAG = "TigerQueryCreator";
    private static LogoTigerTableName mLogoTigerTableName;

    
    public TigerSelectResult getCreditAggrs() {
        return null;
    }

    
    public TigerSelectResult getCustomersIncharge(boolean z) {
        return null;
    }

    
    public TigerSelectResult getLoginUserInformation() {
        return null;
    }

    
    public TigerSelectResult getMuhRefCodes() {
        return null;
    }

    
    public TigerSelectResult getShipCustomers(String str) {
        return null;
    }

    
    public TigerSelectResult listAllCustomersOnline(String str) {
        return null;
    }

    
    public  TigerSelectResult getOrderAvailableAmounts(ArrayList arrayList) {
        return getOrderAvailableAmounts2((ArrayList<String>) arrayList);
    }

    public TigerQueryCreator(ISqlHelper iSqlHelper, User user, UserSettings userSettings) {
        super(iSqlHelper, user, userSettings);
        mLogoTigerTableName = new LogoTigerTableName(getUser().getDbName(), getUser().getFirmNr(), getUser().getPeridodNr());
    }

    
    public void update(User user) {
        setUser(user);
        mLogoTigerTableName = new LogoTigerTableName(getUser().getDbName(), getUser().getFirmNr(), getUser().getPeridodNr());
    }

    
    public Preferences.TigerUserSettings getUserSettings() {
        return (Preferences.TigerUserSettings) super.getUserSettings();
    }

    
    public TigerSelectResult.TigerBuilder getV() {
        return TigerSelectResult.newBuilder();
    }

    
    public TigerSelectResult getCheckSeriGroup(int i2, int i3, SalesType salesType, boolean z, int i4, boolean z2) {
        LogoTigerTableName logoTigerTableName = new LogoTigerTableName(ErpCreator.getInstance().getmBaseErp().getUser().getDbName(), USER.firmano, USER.periodNr);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT SLTR.LOGICALREF, SLTR.MAINAMOUNT [ORGAMOUNT], SLTR.REMAMOUNT, SLTR.EXPDATE, SLTR.GRPBEGCODE [ORGGRPBEGCODE] ,SLTR.GRPENDCODE [ORGGRPENDCODE],SL.NAME, SLTR2.MAINAMOUNT [USEDAMOUNT], SLTR2.GRPBEGCODE [USEDGRPBEGCODE], SLTR2.GRPENDCODE [USEDGRPENDCODE], USLINE.CODE [UNIT], SLTR.DATE_, SLTR2.LOGICALREF [USEDLREF], ISNULL(LOC.CODE,'') LOCATIONCODE, SLTR.STTRANSREF, SL.CODE SERILOTN, USLINE.LOGICALREF SOURCEUNITREF FROM ");
        sb2.append(logoTigerTableName.SLTRANS);
        sb2.append(" SLTR WITH(NOLOCK) LEFT OUTER JOIN ");
        sb2.append(logoTigerTableName.SLTRANS);
        sb2.append(" SLTR2 WITH (NOLOCK) ON (SLTR.LOGICALREF = SLTR2.INSLTRANSREF) AND ((ISNULL(SLTR2.CANCELLED, 0) = 0)) LEFT OUTER JOIN ");
        sb2.append(logoTigerTableName.UNITSETL);
        sb2.append(" USLINE WITH(NOLOCK) ON SLTR.UOMREF=USLINE.LOGICALREF LEFT OUTER JOIN ");
        sb2.append(logoTigerTableName.SERILOTN);
        sb2.append(" SL WITH(NOLOCK) ON\u00a0\u00a0(SLTR.SLREF\u00a0\u00a0=\u00a0\u00a0SL.LOGICALREF) LEFT OUTER JOIN ");
        sb2.append(logoTigerTableName.LOCATION);
        sb2.append(" LOC WITH (NOLOCK) ON (SLTR.LOCREF = LOC.LOGICALREF) WHERE (SLTR.ITEMREF = ");
        sb2.append(i2);
        sb2.append(") AND (SLTR.VARIANTREF = ");
        if (!z) {
            i4 = 0;
        }
        sb2.append(i4);
        sb2.append(") AND (SLTR.STATUS = 0) AND (SLTR.CANCELLED = 0) AND (SLTR.LPRODSTAT = 0) AND (SLTR.EXIMFCTYPE IN (0,4,7) ) AND (SLTR.IOCODE IN ( 1 ,2)) AND (SLTR.DATE_ <= CONVERT(dateTime, GETDATE(), 101)) ");
        sb.append(sb2);
        if (z2) {
            sb.append("AND SLTR.LOCREF <> 0 ");
        }
        if (!SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(salesType)) {
            sb.append("AND (SLTR.INVENNO = " + i3 + ") ");
            sb.append("AND (SLTR.REMAMOUNT > 0) ");
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb.toString()).withOrderBy("ORDER BY DATE_,ORGGRPBEGCODE,LOGICALREF,USEDGRPBEGCODE,USEDLREF").withProcessType(ProcessType.GETCHECKSERIGROUP).build();
    }

    public List<TigerSelectResult> getTransferList(TransferGetItem transferGetItem, boolean z) {
        ArrayList arrayList = new ArrayList();
        boolean transferGetOptionsType = !z && Preferences.getTransferGetOptionsType(ContextUtils.getmContext());
        switch (C25681.SwitchMapcomprojemobilesalescoreenumsTransferGetType[transferGetItem.getTransferGetType().ordinal()]) {
            case 1:
                arrayList.add(getReports());
                arrayList.add(getEmailTemplates());
                arrayList.add(getCountries());
                arrayList.add(getCities());
                arrayList.add(getTowns());
                break;
            case 2:
                arrayList.add(getUsers());
                arrayList.add(getRoute());
                arrayList.add(getRoutetrs());
                if (ErpCreator.getInstance().getmBaseErp().getRouteNewSystem()) {
                    arrayList.add(getWorRoute());
                    arrayList.add(getWorRouteDay());
                    arrayList.add(getWorRoutePlan());
                    arrayList.add(getWorUserCustomers());
                }
                arrayList.add(getLocTracking());
                arrayList.add(getCabins(transferGetOptionsType));
                break;
            case 3:
                arrayList.add(getWareHouse());
                arrayList.add(getFirm());
                break;
            case 4:
                arrayList.add(getBranches());
                break;
            case 5:
                arrayList.add(getFactories());
                break;
            case 6:
                arrayList.add(getDivisions());
                break;
            case 7:
                arrayList.add(getTodo());
                break;
            case 8:
                arrayList.add(getMarketingMessage());
                break;
            case 9:
                arrayList.add(getVisitReason());
                break;
            case 10:
                arrayList.add(getPenetration());
                arrayList.add(getPenetrationDetail());
                break;
            case 11:
                arrayList.add(getSpeCodes());
                break;
            case 12:
                arrayList.add(getTradingGroup());
                break;
            case 13:
                arrayList.add(getPayments(transferGetOptionsType));
                arrayList.add(getPaymentLines(transferGetOptionsType));
                break;
            case 14:
                arrayList.add(getShipType());
                break;
            case 15:
                arrayList.add(getShipAgent());
                break;
            case 16:
                arrayList.add(getItems(transferGetOptionsType));
                arrayList.add(getService(transferGetOptionsType));
                arrayList.add(getCompositeColies(transferGetOptionsType));
                if (StringUtils.paramValueNumberCheck(getSqlHelper().getParamValue(ParameterTypes.ptUseTedarikciCode))) {
                    arrayList.add(getSuppAsgns());
                }
                arrayList.add(getItemAddTaxLines(transferGetOptionsType));
                break;
            case 17:
                arrayList.add(getItemImages(transferGetOptionsType));
                break;
            case 18:
                arrayList.add(getVariants(transferGetOptionsType));
                arrayList.add(getVariantUnits(transferGetOptionsType));
                break;
            case 19:
                arrayList.add(getItemUnits(transferGetOptionsType));
                arrayList.add(getUnit(transferGetOptionsType));
                arrayList.add(getServiceUnit(transferGetOptionsType));
                break;
            case 20:
                arrayList.add(getItemBarcode(true));
                break;
            case 21:
                arrayList.add(getDefOrder());
                arrayList.add(getDefOrderDetail());
                break;
            case 22:
                arrayList.add(getItemStock(transferGetOptionsType));
                arrayList.add(getVariantStock(transferGetOptionsType));
                break;
            case 23:
                if (z) {
                    transferGetOptionsType = true;
                }
                arrayList.add(getItemPrices(transferGetOptionsType));
                break;
            case 24:
                arrayList.add(getDiscounts(transferGetOptionsType));
                break;
            case 25:
                arrayList.add(getCustomers(-1, transferGetOptionsType));
                arrayList.add(getCustomerSalesmanRelation());
                arrayList.add(getCustomerGpsLocation(getUser().getFirmNr()));
                arrayList.add(getCustomerDocs(-1, transferGetOptionsType, CustomerDocumentType.cdtINFONOTE));
                arrayList.add(getCustomerRiskTotals(-1, true));
                break;
            case 26:
                arrayList.add(getCustomerDocs(-1, transferGetOptionsType, CustomerDocumentType.cdtIMAGE));
                break;
            case 27:
                arrayList.add(getShipAddress(transferGetOptionsType));
                break;
            case 28:
                arrayList.add(getBanks(transferGetOptionsType));
                break;
            case 29:
                arrayList.add(getBankAccounts(transferGetOptionsType));
                break;
            case 30:
                arrayList.add(getCases());
                break;
            case 32:
                try {
                    arrayList.add(getDesFile());
                    arrayList.add(getDesFileJson());
                    break;
                } catch (Exception e2) {
                    Log.e(TAG, "getTransferList: ", e2);
                    break;
                }
            case 33:
                arrayList.add(getCurrTypes());
                arrayList.add(getCurrRates());
                break;
            case 34:
                arrayList.add(getProjects());
                break;
            case 35:
                arrayList.add(getEmailParam());
                break;
            case 36:
                arrayList.add(getLogoTradeParam());
                break;
        }
        return arrayList;
    }

    
    public TigerSelectResult getUsers() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM (SELECT U.USERID [USERID], (U.FNAME + ' ' +U.LNAME) AS [NAME], U.DOSHIP [DOSHIP], U.DISTVEHICLE [DISTVEHICLE], S.DEFINITION_  [SALESMANNAME], U.CODE [CODE], ROW_NUMBER() OVER (ORDER BY U.USERID) AS RowNum FROM WOR_USERS U WITH (NOLOCK), " + mLogoTigerTableName.SLSMAN + " S WITH(NOLOCK) WHERE U.CODE=S.CODE AND U.APPTYPE=1 AND S.ACTIVE=0 AND S.FIRMNR = '" + getUser().getFirmNr() + "') R ").withProcessType(ProcessType.GETUSERINFO).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getCustomers(int i2, boolean z) {
        if (FDateUtils.getInstance().getFDateModel().getfDateOnClfLine() == 1 && FDateUtils.getInstance().getFDateModel().getfDateOnClCard() == 1 && FDateUtils.getInstance().getFDateModel().getfDateOnDeletedRecs() == 1) {
            return getCustomersWithFDate(i2, z);
        }
        String trim = getSqlHelper().getParamValue(ParameterTypes.ptARAPType).trim();
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_SQLSentence);
        boolean routeNewSystem = ErpCreator.getInstance().getmBaseErp().getRouteNewSystem();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ( ");
        sb.append("SELECT K.*, CAST(SUM(ISNULL(CF.DEBIT, 0)) as decimal(28, 8)) [DEBIT], CAST(SUM(ISNULL(CF.CREDIT, 0)) AS decimal(28, 8)) [CREDIT], CAST(SUM(ISNULL(CF.DEBIT, 0)) - SUM (ISNULL(CF.CREDIT, 0)) as decimal(28, 8)) [BAKIYE], CAST(K.DCR AS decimal(28, 8)) [DISCRATE], CAST(SUM(ISNULL(CF.DEBIT, 0)) AS decimal(28, 8)) [DEBIT_FLOAT], CAST(SUM(ISNULL(CF.CREDIT, 0)) AS decimal(28, 8)) [CREDIT_FLOAT], CAST(SUM(ISNULL(CF.DEBIT, 0)) - SUM(ISNULL(CF.CREDIT, 0)) AS decimal(28, 8)) [BAKIYE_FLOAT], 1 AS ISTRANSFER, ROW_NUMBER() OVER (ORDER BY K.LOGICALREF) AS RowNum FROM ( ");
        sb.append("SELECT DISTINCT C.LOGICALREF, C.CODE, C.DEFINITION_,  C.DISCRATE AS DCR, C.TRADINGGRP, C.PAYMENTREF, C.SPECODE, C.SPECODE2, C.SPECODE3, C.SPECODE4, C.SPECODE5, C.EMAILADDR, C.CITY, C.CITYCODE, C.TELNRS1, C.FAXNR, C.ADDR1 , C.ADDR2, C.ACCEPTEINV  ,C.ACCEPTEDESP,C.PROFILEIDDESP, C.DEFINITION2, C.TCKNO , C.ISPERSCOMP, C.TELCODES1, C.CARDTYPE, C.INSTEADOFDESP, C.TELNRS2, C.TELCODES2, C.NAME, C.SURNAME, C.COUNTRY, C.COUNTRYCODE, C.CYPHCODE AS WARRANTYCODE, C.SALESBRWS, ");
        if (trim.equals(BuildConfig.NETSIS_DEMO_PASSWORD) && !routeNewSystem) {
            sb.append(" SC.VISITDAY ,");
        } else {
            sb.append(" 0 as VISITDAY ,");
        }
        sb.append("C.TAXNR, dbo.GetWebOrderDateInt(C.CAPIBLOCK_CREADEDDATE, C.CAPIBLOCK_MODIFIEDDATE) [CMDATE], C.TOWN, C.TOWNCODE, C.CYPHCODE, C.INCHARGE, C.INCHARGE2, C.INCHARGE3, C.DISTRICT, C.TAXOFFCODE, C.TAXOFFICE, C.CCURRENCY, C.DELIVERYMETHOD, C.DELIVERYFIRM, C.EDINO");
        if (trim.equals(ExifInterface.GPS_MEASUREMENT_2D) && !routeNewSystem) {
            sb.append(",RT.ROUTEREF");
        } else {
            sb.append(", 0 AS ROUTEREF");
        }
        sb.append(",CAST(ISNULL(CL.ACCRISKLIMIT, 0) AS decimal(28, 8)) [ACCRISKLIMIT] , CAST(ISNULL(MYCSRISKLIMIT, 0) AS decimal(28, 8)) [MYCSRISKLIMIT], CAST(ISNULL(CL.CSTCSRISKLIMIT, 0) AS decimal(28, 8)) [CSTCSRISKLIMIT], CAST(ISNULL(CL.CSTCSCIRORISKLIMIT, 0) AS decimal(28, 8)) [CSTCSCIRORISKLIMIT],  CAST(ISNULL(CL.DESPRISKLIMIT, 0) AS decimal(28, 8)) [DESPRISKLIMIT], CAST(ISNULL(CL.DESPRISKLIMITSUG, 0) AS decimal(28, 8)) [DESPRISKLIMITSUG], CAST(ISNULL(ORDRISKLIMIT, 0) AS decimal(28, 8)) [ORDRISKLIMIT], CAST(ISNULL(ORDRISKLIMITSUGG, 0) AS decimal(28, 8)) [ORDRISKLIMITSUGG], CAST(ISNULL(CL.ACCRSKBLNCED, 0) AS decimal(28, 8)) [ACCRSKBLNCED], CAST(ISNULL(CL.MYCSRSKBLNCED, 0) AS decimal(28, 8)) [MYCSRSKBLNCED], CAST(ISNULL(CL.CSTCSRSKBLNCED, 0) AS decimal(28, 8)) [CSTCSRSKBLNCED], CAST(ISNULL(CSTCSCIRORSKBLNCED, 0) AS decimal(28, 8)) [CSTCSCIRORSKBLNCED], CAST(ISNULL(DESPRSKBLNCED, 0) AS decimal(28, 8)) [DESPRSKBLNCED],CAST(ISNULL(DESPRSKBLNCEDSUG, 0) AS decimal(28, 8)) [DESPRSKBLNCEDSUG], CAST(ISNULL(ORDRSKBLNCED, 0) AS decimal(28, 8)) [ORDRSKBLNCED], CAST(ISNULL(ORDRSKBLNCEDSUG, 0) AS decimal(28, 8)) [ORDRSKBLNCEDSUG], CAST(ISNULL(ACCRISKTOTAL, 0) AS decimal(28, 8)) [ACCRISKTOTAL],CAST(ISNULL(MYCSRISKTOTAL, 0) AS decimal(28, 8)) [MYCSRISKTOTAL], CAST(ISNULL(CSTCSOWNRISKTOTAL, 0) AS decimal(28, 8)) [CSTCSOWNRISKTOTAL],CAST(ISNULL(CSTCSCIRORISKTOTAL, 0) AS decimal(28, 8)) [CSTCSCIRORISKTOTAL], CAST(ISNULL(DESPRISKTOTAL, 0) AS decimal(28, 8)) [DESPRISKTOTAL],CAST(ISNULL(DESPRISKTOTALSUG, 0) AS decimal(28, 8)) [DESPRISKTOTALSUG], CAST(ISNULL(ORDRISKTOTAL, 0) AS decimal(28, 8)) [ORDRISKTOTAL],CAST(ISNULL(ORDRISKTOTALSUGG, 0) AS decimal(28, 8)) [ORDRISKTOTALSUGG], ACCRISKOVER , CSTCSCIRORISKOVER , CSTCSRISKOVER , DESPRISKOVER, DESPRISKOVERSUG , MYCSRISKOVER , ORDRISKOVERSUGG , ORDRISKOVER, RISKOVER, DISCTYPE, C.ACTIVE, C.SENDMOD,  C.PROFILEID, C.DUEDATELIMIT, C.DUEDATECOUNT, C.DUEDATETRACK, C.DUEDATECONTROL2 AS DUEDATECONTROLINV, C.DUEDATECONTROL3 AS DUEDATECONTROLORD, C.EINVOICETYP, C.ORDSENDEMAILADDR, C.DSPSENDEMAILADDR, C.INVSENDEMAILADDR, C.EXTSENDEMAILADDR, ISNULL(EM.CODE,'') AS MUHACC_CODE, C.ISPERCURR [ISPERCURR], P.CODE AS PROJECTCODE FROM " + mLogoTigerTableName.CLCARD + " C WITH(NOLOCK) LEFT JOIN " + mLogoTigerTableName.CLRNUMS + " CL WITH(NOLOCK) ON C.LOGICALREF = CL.CLCARDREF LEFT JOIN " + mLogoTigerTableName.CRDACREF + " CR WITH (NOLOCK) ON C.LOGICALREF = CR.CARDREF AND CR.TRCODE = 5 AND CR.TYP=1 LEFT JOIN " + mLogoTigerTableName.EMUHACC + " EM WITH (NOLOCK) ON EM.LOGICALREF = CR.ACCOUNTREF LEFT JOIN " + mLogoTigerTableName.PROJECT + " P WITH (NOLOCK) ON P.LOGICALREF = C.PROJECTREF ");
        if (routeNewSystem) {
            if (ErpCreator.getInstance().getmBaseErp().getOffRoadVisit()) {
                sb.append("INNER JOIN WOR_USERCUSTOMERS UC WITH(NOLOCK) ON C.LOGICALREF=UC.CLIENTREF AND UC.USERID=" + getUser().getUserId());
            } else {
                sb.append("INNER JOIN WOR_ROUTEPLAN RP WITH(NOLOCK) ON C.LOGICALREF=RP.CLIENTREF INNER JOIN WOR_ROUTE R WITH(NOLOCK) ON R.LOGICALREF=RP.ROUTEID AND R.USERID=" + getUser().getUserId());
            }
        } else {
            if (trim.equals(BuildConfig.NETSIS_DEMO_PASSWORD)) {
                sb.append("INNER JOIN " + mLogoTigerTableName.SLSCLREL + " SC WITH(NOLOCK) ON  C.LOGICALREF = SC.CLIENTREF INNER JOIN " + mLogoTigerTableName.SLSMAN + " SLSMAN WITH(NOLOCK) ON SLSMAN.LOGICALREF = SC.SALESMANREF AND SLSMAN.CODE ='" + getUser().getCode() + "' AND SLSMAN.FIRMNR =" + getUser().getFirmNr() + " ");
            }
            if (trim.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                sb.append("INNER JOIN " + mLogoTigerTableName.ROUTETRS + " RT WITH(NOLOCK) ON RT.CLIENTREF = C.LOGICALREF INNER JOIN " + mLogoTigerTableName.ROUTE + " R WITH(NOLOCK) ON R.LOGICALREF = RT.ROUTEREF INNER JOIN " + mLogoTigerTableName.SLSMAN + " SLSMAN WITH(NOLOCK) ON SLSMAN.LOGICALREF = R.SALESMANREF AND SLSMAN .CODE = '" + getUser().getCode() + "' AND SLSMAN.FIRMNR =" + getUser().getFirmNr() + " ");
            }
        }
        sb.append("WHERE C.CARDTYPE NOT IN (4,22) ");
        if (trim.equals(BuildConfig.NETSIS_DEMO_PASSWORD) && !routeNewSystem) {
            sb.append("AND (SC.BEGDATE<= GETDATE() OR SC.BEGDATE IS NULL) ");
        }
        sb.append(buildSqlCondition(getCustomersFilter("C"), false));
        if (i2 != -1) {
            sb.append("AND C.LOGICALREF=" + i2 + " ");
        }
        if (!z) {
            double lastDate = getSharedPreferencesHelper().getLastDate(true);
            if (lastDate != 0.0d) {
                sb.append(" AND ( dbo.GetWebOrderDateInt(C.CAPIBLOCK_CREADEDDATE, C.CAPIBLOCK_MODIFIEDDATE) > " + getSqlHelper().getMaxCMDate(ClCard.class) + " OR  C.LOGICALREF IN (SELECT CLIENTREF FROM (SELECT CLFLINE.CLIENTREF , MAX(CLFLINE.CAPIBLOCK_CREADEDDATE) AS CREADEDDATE, MAX(CLFLINE.CAPIBLOCK_MODIFIEDDATE) AS MODIFIEDDATE FROM " + mLogoTigerTableName.CLFLINE + " CLFLINE WITH(NOLOCK)  INNER JOIN " + mLogoTigerTableName.CLCARD + " CL ON CL.LOGICALREF=CLFLINE.CLIENTREF WHERE CL.CARDTYPE NOT IN (4,22) ");
                if (paramValue.trim().length() > 0) {
                    String sb2 = "AND CL.LOGICALREF IN ( " +
                            paramValue.replace("_XXX_", "_" + getUser().getFirmNr() + "_").replace("_XX_", "_" + getUser().getPeridodNr() + "_") +
                            SqlLiteVariable._CLOSE_BRACKET;
                    sb.append(sb2);
                }
                sb.append(" GROUP BY CLFLINE.CLIENTREF) AS TMP_TBL WHERE dbo.GetWebOrderDateInt(CREADEDDATE, MODIFIEDDATE) > " + lastDate + " ) )");
                sb.append(" OR C.LOGICALREF IN (SELECT DEL.LOGICALREF FROM WOR_DELETEDRECS DEL INNER JOIN " + mLogoTigerTableName.CLCARD + " C ON C.LOGICALREF=DEL.LOGICALREF WHERE FIRMNR = " + Integer.valueOf(getUser().getFirmNr()) + " AND PERIODNR = " + Integer.valueOf(getUser().getPeridodNr()) + " AND RECTYPE = 2 AND dbo.GetWebOrderDateInt(DELETEDDATE, DELETEDDATE) > " + lastDate + " AND C.CARDTYPE NOT IN (4,22) " + buildSqlCondition(getCustomersFilter("C"), false) + "GROUP BY DEL.LOGICALREF)");
            } else {
                z = true;
            }
        }
        if (z) {
            sb.append(" AND C.ACTIVE=0 ");
        }
        sb.append(") K ");
        sb.append("LEFT JOIN " + mLogoTigerTableName.CLTOTFIL + " CF WITH(NOLOCK) on K.LOGICALREF = CF.CARDREF AND CF.TOTTYP = 1 ");
        sb.append("GROUP BY ACCRISKLIMIT, EDINO, DELIVERYMETHOD, DELIVERYFIRM,EMAILADDR, PAYMENTREF, INCHARGE, INCHARGE2, INCHARGE3, TAXOFFICE, TAXNR , CITY , CITYCODE, DCR, TRADINGGRP, K.LOGICALREF, CODE, DEFINITION_ ,SPECODE, SPECODE2, SPECODE3, SPECODE4, SPECODE5, ADDR1, ADDR2, TOWN, TOWNCODE, FAXNR,TELNRS1, CYPHCODE , DISTRICT, TAXOFFCODE, CCURRENCY, ACCEPTEINV,ACCEPTEDESP,PROFILEIDDESP,SENDMOD,  PROFILEID, MYCSRISKLIMIT, CSTCSRISKLIMIT, CSTCSCIRORISKLIMIT, DESPRISKLIMIT, DESPRISKLIMITSUG, ORDRISKLIMIT, ORDRISKLIMITSUGG, ACCRSKBLNCED, MYCSRSKBLNCED, CSTCSRSKBLNCED, CSTCSCIRORSKBLNCED, DESPRSKBLNCED, DESPRSKBLNCEDSUG,ORDRSKBLNCED, ORDRSKBLNCEDSUG, ACCRISKTOTAL, MYCSRISKTOTAL, CSTCSOWNRISKTOTAL,CSTCSCIRORISKTOTAL, DESPRISKTOTAL, DESPRISKTOTALSUG, ORDRISKTOTAL, ORDRISKTOTALSUGG , DEFINITION2, TCKNO, ISPERSCOMP, TELCODES1, CARDTYPE, ACCRISKOVER , CSTCSCIRORISKOVER , CSTCSRISKOVER , DESPRISKOVER ,DESPRISKOVERSUG , MYCSRISKOVER , ORDRISKOVERSUGG , ORDRISKOVER, RISKOVER , ACTIVE , DISCTYPE, DUEDATELIMIT, DUEDATECOUNT, ROUTEREF, VISITDAY, DUEDATETRACK, EINVOICETYP, CMDATE, ROUTEREF, DUEDATECONTROLINV, DUEDATECONTROLORD,ORDSENDEMAILADDR, DSPSENDEMAILADDR, INVSENDEMAILADDR, EXTSENDEMAILADDR, MUHACC_CODE, ISPERCURR, INSTEADOFDESP, TELNRS2, TELCODES2, NAME, SURNAME, COUNTRY, COUNTRYCODE, PROJECTCODE, WARRANTYCODE, SALESBRWS  ");
        sb.append(") R ");
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb.toString()).withOrderBy("ORDER BY LOGICALREF").withProcessType(ProcessType.GETCLCARD).withTableDelete(z).withDeleteCondition("LOGICALREF > 0").withDatabaseSave(true).build();
    }

    private TigerSelectResult getCustomersWithFDate(int i2, boolean z) {
        String trim = getSqlHelper().getParamValue(ParameterTypes.ptARAPType).trim();
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_SQLSentence);
        boolean routeNewSystem = ErpCreator.getInstance().getmBaseErp().getRouteNewSystem();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ( ");
        sb.append("SELECT K.*, CAST(SUM(ISNULL(CF.DEBIT, 0)) as decimal(28, 8)) [DEBIT], CAST(SUM(ISNULL(CF.CREDIT, 0)) AS decimal(28, 8)) [CREDIT], CAST(SUM(ISNULL(CF.DEBIT, 0)) - SUM (ISNULL(CF.CREDIT, 0)) as decimal(28, 8)) [BAKIYE], CAST(K.DCR AS decimal(28, 8)) [DISCRATE], CAST(SUM(ISNULL(CF.DEBIT, 0)) AS decimal(28, 8)) [DEBIT_FLOAT], CAST(SUM(ISNULL(CF.CREDIT, 0)) AS decimal(28, 8)) [CREDIT_FLOAT], CAST(SUM(ISNULL(CF.DEBIT, 0)) - SUM(ISNULL(CF.CREDIT, 0)) AS decimal(28, 8)) [BAKIYE_FLOAT], 1 AS ISTRANSFER, ROW_NUMBER() OVER (ORDER BY K.LOGICALREF) AS RowNum FROM ( ");
        sb.append("SELECT DISTINCT C.LOGICALREF, C.CODE, C.DEFINITION_,  C.DISCRATE AS DCR, C.TRADINGGRP, C.PAYMENTREF, C.SPECODE, C.SPECODE2, C.SPECODE3, C.SPECODE4, C.SPECODE5, C.EMAILADDR, C.CITY, C.CITYCODE,C.TELNRS1, C.FAXNR, C.ADDR1 , C.ADDR2,  C.ACCEPTEINV,C.ACCEPTEDESP,C.PROFILEIDDESP  , C.DEFINITION2, C.TCKNO , C.ISPERSCOMP, C.NAME, C.SURNAME, C.COUNTRY, C.COUNTRYCODE, C.TELCODES1, C.CARDTYPE, C.INSTEADOFDESP,C.TELNRS2, C.TELCODES2, C.CYPHCODE AS WARRANTYCODE, C.SALESBRWS, ");
        if (trim.equals(BuildConfig.NETSIS_DEMO_PASSWORD) && !routeNewSystem) {
            sb.append(" SC.VISITDAY ,");
        } else {
            sb.append(" 0 as VISITDAY ,");
        }
        sb.append("C.TAXNR, C.FDATE [CMDATE], C.TOWN, C.TOWNCODE, C.CYPHCODE, C.INCHARGE, C.INCHARGE2, C.INCHARGE3, C.DISTRICT, C.TAXOFFCODE, C.TAXOFFICE, C.CCURRENCY, C.DELIVERYMETHOD, C.DELIVERYFIRM, C.EDINO");
        if (trim.equals(ExifInterface.GPS_MEASUREMENT_2D) && !routeNewSystem) {
            sb.append(",RT.ROUTEREF");
        } else {
            sb.append(", 0 AS ROUTEREF");
        }
        sb.append(",CAST(ISNULL(CL.ACCRISKLIMIT, 0) AS decimal(28, 8)) [ACCRISKLIMIT] , CAST(ISNULL(MYCSRISKLIMIT, 0) AS decimal(28, 8)) [MYCSRISKLIMIT], CAST(ISNULL(CL.CSTCSRISKLIMIT, 0) AS decimal(28, 8)) [CSTCSRISKLIMIT], CAST(ISNULL(CL.CSTCSCIRORISKLIMIT, 0) AS decimal(28, 8)) [CSTCSCIRORISKLIMIT],  CAST(ISNULL(CL.DESPRISKLIMIT, 0) AS decimal(28, 8)) [DESPRISKLIMIT], CAST(ISNULL(CL.DESPRISKLIMITSUG, 0) AS decimal(28, 8)) [DESPRISKLIMITSUG], CAST(ISNULL(ORDRISKLIMIT, 0) AS decimal(28, 8)) [ORDRISKLIMIT], CAST(ISNULL(ORDRISKLIMITSUGG, 0) AS decimal(28, 8)) [ORDRISKLIMITSUGG], CAST(ISNULL(CL.ACCRSKBLNCED, 0) AS decimal(28, 8)) [ACCRSKBLNCED], CAST(ISNULL(CL.MYCSRSKBLNCED, 0) AS decimal(28, 8)) [MYCSRSKBLNCED], CAST(ISNULL(CL.CSTCSRSKBLNCED, 0) AS decimal(28, 8)) [CSTCSRSKBLNCED], CAST(ISNULL(CSTCSCIRORSKBLNCED, 0) AS decimal(28, 8)) [CSTCSCIRORSKBLNCED], CAST(ISNULL(DESPRSKBLNCED, 0) AS decimal(28, 8)) [DESPRSKBLNCED],CAST(ISNULL(DESPRSKBLNCEDSUG, 0) AS decimal(28, 8)) [DESPRSKBLNCEDSUG], CAST(ISNULL(ORDRSKBLNCED, 0) AS decimal(28, 8)) [ORDRSKBLNCED], CAST(ISNULL(ORDRSKBLNCEDSUG, 0) AS decimal(28, 8)) [ORDRSKBLNCEDSUG], CAST(ISNULL(ACCRISKTOTAL, 0) AS decimal(28, 8)) [ACCRISKTOTAL],CAST(ISNULL(MYCSRISKTOTAL, 0) AS decimal(28, 8)) [MYCSRISKTOTAL], CAST(ISNULL(CSTCSOWNRISKTOTAL, 0) AS decimal(28, 8)) [CSTCSOWNRISKTOTAL],CAST(ISNULL(CSTCSCIRORISKTOTAL, 0) AS decimal(28, 8)) [CSTCSCIRORISKTOTAL], CAST(ISNULL(DESPRISKTOTAL, 0) AS decimal(28, 8)) [DESPRISKTOTAL],CAST(ISNULL(DESPRISKTOTALSUG, 0) AS decimal(28, 8)) [DESPRISKTOTALSUG], CAST(ISNULL(ORDRISKTOTAL, 0) AS decimal(28, 8)) [ORDRISKTOTAL],CAST(ISNULL(ORDRISKTOTALSUGG, 0) AS decimal(28, 8)) [ORDRISKTOTALSUGG], ACCRISKOVER , CSTCSCIRORISKOVER , CSTCSRISKOVER , DESPRISKOVER, DESPRISKOVERSUG , MYCSRISKOVER , ORDRISKOVERSUGG , ORDRISKOVER, RISKOVER, DISCTYPE, C.ACTIVE, C.SENDMOD,  C.PROFILEID, C.DUEDATELIMIT, C.DUEDATECOUNT, C.DUEDATETRACK, C.DUEDATECONTROL2 AS DUEDATECONTROLINV, C.DUEDATECONTROL3 AS DUEDATECONTROLORD, C.EINVOICETYP, C.ORDSENDEMAILADDR, C.DSPSENDEMAILADDR, C.INVSENDEMAILADDR, C.EXTSENDEMAILADDR, ISNULL(EM.CODE,'') AS MUHACC_CODE, C.ISPERCURR [ISPERCURR], P.CODE AS PROJECTCODE FROM " + mLogoTigerTableName.CLCARD + " C WITH(NOLOCK) LEFT JOIN " + mLogoTigerTableName.CLRNUMS + " CL WITH(NOLOCK) ON C.LOGICALREF = CL.CLCARDREF LEFT JOIN " + mLogoTigerTableName.CRDACREF + " CR WITH (NOLOCK) ON C.LOGICALREF = CR.CARDREF AND CR.TRCODE = 5 AND CR.TYP=1 LEFT JOIN " + mLogoTigerTableName.EMUHACC + " EM WITH (NOLOCK) ON EM.LOGICALREF = CR.ACCOUNTREF LEFT JOIN " + mLogoTigerTableName.PROJECT + " P WITH (NOLOCK) ON P.LOGICALREF = C.PROJECTREF ");
        if (routeNewSystem) {
            if (ErpCreator.getInstance().getmBaseErp().getOffRoadVisit()) {
                sb.append("INNER JOIN WOR_USERCUSTOMERS UC WITH(NOLOCK) ON C.LOGICALREF=UC.CLIENTREF AND UC.USERID=" + getUser().getUserId());
            } else {
                sb.append("INNER JOIN WOR_ROUTEPLAN RP WITH(NOLOCK) ON C.LOGICALREF=RP.CLIENTREF INNER JOIN WOR_ROUTE R WITH(NOLOCK) ON R.LOGICALREF=RP.ROUTEID AND R.USERID=" + getUser().getUserId());
            }
        } else {
            if (trim.equals(BuildConfig.NETSIS_DEMO_PASSWORD)) {
                sb.append("INNER JOIN " + mLogoTigerTableName.SLSCLREL + " SC WITH(NOLOCK) ON  C.LOGICALREF = SC.CLIENTREF INNER JOIN " + mLogoTigerTableName.SLSMAN + " SLSMAN WITH(NOLOCK) ON SLSMAN.LOGICALREF = SC.SALESMANREF AND SLSMAN.CODE ='" + getUser().getCode() + "' AND SLSMAN.FIRMNR =" + getUser().getFirmNr() + " ");
            }
            if (trim.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                sb.append("INNER JOIN " + mLogoTigerTableName.ROUTETRS + " RT WITH(NOLOCK) ON RT.CLIENTREF = C.LOGICALREF INNER JOIN " + mLogoTigerTableName.ROUTE + " R WITH(NOLOCK) ON R.LOGICALREF = RT.ROUTEREF INNER JOIN " + mLogoTigerTableName.SLSMAN + " SLSMAN WITH(NOLOCK) ON SLSMAN.LOGICALREF = R.SALESMANREF AND SLSMAN .CODE = '" + getUser().getCode() + "' AND SLSMAN.FIRMNR =" + getUser().getFirmNr() + " ");
            }
        }
        sb.append("WHERE C.CARDTYPE NOT IN (4,22) ");
        if (trim.equals(BuildConfig.NETSIS_DEMO_PASSWORD) && !routeNewSystem) {
            sb.append("AND (SC.BEGDATE<= GETDATE() OR SC.BEGDATE IS NULL) ");
        }
        sb.append(buildSqlCondition(getCustomersFilter("C"), false));
        if (i2 != -1) {
            sb.append("AND C.LOGICALREF=" + i2 + " ");
        }
        if (!z) {
            double lastDate = getSharedPreferencesHelper().getLastDate(true);
            if (lastDate != 0.0d) {
                sb.append(" AND FDATE  > " + getSqlHelper().getMaxCMDate(ClCard.class) + " OR  C.LOGICALREF IN (SELECT CLIENTREF FROM (SELECT CLFLINE.CLIENTREF ,  MAX(CLFLINE.FDATE) FDATE FROM " + mLogoTigerTableName.CLFLINE + " CLFLINE WITH(NOLOCK)  INNER JOIN " + mLogoTigerTableName.CLCARD + " CL ON CL.LOGICALREF=CLFLINE.CLIENTREF WHERE CL.CARDTYPE NOT IN (4,22) " + buildSqlCondition(getCustomersFilter("CL"), false));
                if (paramValue.trim().length() > 0) {
                    String sb2 = "AND CL.LOGICALREF IN ( " +
                            paramValue.replace("_XXX_", "_" + getUser().getFirmNr() + "_").replace("_XX_", "_" + getUser().getPeridodNr() + "_") +
                            SqlLiteVariable._CLOSE_BRACKET;
                    sb.append(sb2);
                }
                sb.append(" GROUP BY CLFLINE.CLIENTREF) AS TMP_TBL WHERE FDATE > " + lastDate + "  )");
                sb.append(" OR C.LOGICALREF IN (SELECT DEL.LOGICALREF FROM WOR_DELETEDRECS DEL INNER JOIN " + mLogoTigerTableName.CLCARD + " C ON C.LOGICALREF=DEL.LOGICALREF WHERE FIRMNR = " + Integer.valueOf(getUser().getFirmNr()) + " AND PERIODNR = " + Integer.valueOf(getUser().getPeridodNr()) + " AND RECTYPE = 2 AND DEL.FDATE > " + lastDate + " AND C.CARDTYPE NOT IN (4,22) " + buildSqlCondition(getCustomersFilter("C"), false) + "GROUP BY DEL.LOGICALREF)");
            } else {
                z = true;
            }
        }
        if (z) {
            sb.append(" AND C.ACTIVE=0 ");
        }
        sb.append(") K ");
        sb.append("LEFT JOIN " + mLogoTigerTableName.CLTOTFIL + " CF WITH(NOLOCK) on K.LOGICALREF = CF.CARDREF AND CF.TOTTYP = 1 ");
        sb.append("GROUP BY ACCRISKLIMIT, EDINO, DELIVERYMETHOD, DELIVERYFIRM,EMAILADDR, PAYMENTREF, INCHARGE, INCHARGE2, INCHARGE3, TAXOFFICE, TAXNR , CITY, CITYCODE, DCR, TRADINGGRP, K.LOGICALREF, CODE, DEFINITION_ ,SPECODE, SPECODE2, SPECODE3, SPECODE4, SPECODE5, ADDR1, ADDR2, TOWN, TOWNCODE, FAXNR,TELNRS1, CYPHCODE , DISTRICT, TAXOFFCODE, CCURRENCY, ACCEPTEINV,ACCEPTEDESP,PROFILEIDDESP,SENDMOD,  PROFILEID, MYCSRISKLIMIT, CSTCSRISKLIMIT, CSTCSCIRORISKLIMIT, DESPRISKLIMIT, DESPRISKLIMITSUG, ORDRISKLIMIT, ORDRISKLIMITSUGG, ACCRSKBLNCED, MYCSRSKBLNCED, CSTCSRSKBLNCED, CSTCSCIRORSKBLNCED, DESPRSKBLNCED, DESPRSKBLNCEDSUG,ORDRSKBLNCED, ORDRSKBLNCEDSUG, ACCRISKTOTAL, MYCSRISKTOTAL, CSTCSOWNRISKTOTAL,CSTCSCIRORISKTOTAL, DESPRISKTOTAL, DESPRISKTOTALSUG, ORDRISKTOTAL, ORDRISKTOTALSUGG , DEFINITION2, TCKNO, ISPERSCOMP, TELCODES1, CARDTYPE, ACCRISKOVER , CSTCSCIRORISKOVER , CSTCSRISKOVER , DESPRISKOVER ,DESPRISKOVERSUG , MYCSRISKOVER , ORDRISKOVERSUGG , ORDRISKOVER, RISKOVER , ACTIVE , DISCTYPE, DUEDATELIMIT, DUEDATECOUNT, ROUTEREF, VISITDAY, DUEDATETRACK, EINVOICETYP, CMDATE, ROUTEREF, DUEDATECONTROLINV, DUEDATECONTROLORD,ORDSENDEMAILADDR, DSPSENDEMAILADDR, INVSENDEMAILADDR, EXTSENDEMAILADDR, MUHACC_CODE, ISPERCURR, INSTEADOFDESP,TELNRS2,TELCODES2, NAME, SURNAME, COUNTRY, COUNTRYCODE, PROJECTCODE, WARRANTYCODE, SALESBRWS  ");
        sb.append(") R ");
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb.toString()).withOrderBy("ORDER BY LOGICALREF").withProcessType(ProcessType.GETCLCARD).withTableDelete(z).withDeleteCondition("LOGICALREF > 0").withDatabaseSave(true).build();
    }

    public TigerSelectResult getCustomerDocs(int i2, boolean z, CustomerDocumentType customerDocumentType) {
        String trim = getSqlHelper().getParamValue(ParameterTypes.ptARAPType).trim();
        boolean routeNewSystem = ErpCreator.getInstance().getmBaseErp().getRouteNewSystem();
        StringBuilder sb = new StringBuilder();
        FDateField fDateField = FDateField.CL_CARD;
        String sb2 = "SELECT * FROM ( SELECT C.LOGICALREF AS CUSTOMERREF, DOC.DOCNR, DOC.DOCTYP,  CAST(DOC.LDATA AS VARBINARY(MAX)) AS CUSTOMERIMAGE, " +
                checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " C") +
                "AS CMDATE, ROW_NUMBER() OVER (ORDER BY C.LOGICALREF) AS RowNum FROM " +
                mLogoTigerTableName.CLCARD +
                " C WITH(NOLOCK)INNER JOIN " +
                mLogoTigerTableName.FIRMDOC +
                " DOC WITH(NOLOCK) ON DOC.INFOREF=C.LOGICALREF AND DOC.DOCNR=" +
                customerDocumentType.docNumber +
                " AND DOC.INFOTYP=21 AND DOC.DOCTYP=" +
                customerDocumentType.docType +
                " ";
        sb.append(sb2);
        if (routeNewSystem) {
            if (ErpCreator.getInstance().getmBaseErp().getOffRoadVisit()) {
                sb.append(" INNER JOIN (SELECT CLIENTREF FROM WOR_USERCUSTOMERS UC WITH (NOLOCK) WHERE UC.USERID = " + getUser().getUserId() + " GROUP BY CLIENTREF) RO ON RO.CLIENTREF = C.LOGICALREF ");
            } else {
                sb.append(" INNER JOIN (SELECT RP.CLIENTREF FROM WOR_ROUTEPLAN RP WITH (NOLOCK)  INNER JOIN WOR_ROUTE R WITH (NOLOCK) ON R.LOGICALREF = RP.ROUTEID AND R.USERID = " + getUser().getUserId() + " AND R.PERIODSTARTDATE <= CAST(GETDATE() AS DATE) GROUP BY RP.CLIENTREF) RO ON RO.CLIENTREF = C.LOGICALREF ");
            }
        } else {
            if (trim.equals(BuildConfig.NETSIS_DEMO_PASSWORD)) {
                sb.append("INNER JOIN " + mLogoTigerTableName.SLSCLREL + " SC WITH(NOLOCK) ON C.LOGICALREF = SC.CLIENTREF INNER JOIN " + mLogoTigerTableName.SLSMAN + " SLSMAN WITH(NOLOCK) ON SLSMAN.LOGICALREF = SC.SALESMANREF AND SLSMAN.CODE ='" + getUser().getCode() + "' AND  SLSMAN.FIRMNR =" + getUser().getFirmNr() + " ");
            }
            if (trim.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                sb.append("INNER JOIN " + mLogoTigerTableName.ROUTETRS + " RT WITH(NOLOCK) ON RT.CLIENTREF = C.LOGICALREF INNER JOIN " + mLogoTigerTableName.ROUTE + " R WITH(NOLOCK) ON R.LOGICALREF = RT.ROUTEREF INNER JOIN " + mLogoTigerTableName.SLSMAN + " SLSMAN WITH(NOLOCK) ON SLSMAN.LOGICALREF = R.SALESMANREF AND SLSMAN .CODE = '" + getUser().getCode() + "' AND  SLSMAN.FIRMNR =" + getUser().getFirmNr() + " ");
            }
        }
        sb.append("WHERE C.CARDTYPE NOT IN (4,22) AND C.ACTIVE=0 ");
        CustomerDocumentType customerDocumentType2 = CustomerDocumentType.cdtIMAGE;
        if (customerDocumentType == customerDocumentType2) {
            sb.append("AND C.IMAGEINC=1 ");
        } else if (customerDocumentType == customerDocumentType2) {
            sb.append("AND C.TEXTINC=1 ");
        }
        if (!routeNewSystem && trim.equals(BuildConfig.NETSIS_DEMO_PASSWORD)) {
            sb.append("AND (SC.BEGDATE<= GETDATE() OR SC.BEGDATE IS NULL) ");
        }
        sb.append(buildSqlCondition(getCustomersFilter("C"), false));
        if (i2 != -1) {
            sb.append("AND C.LOGICALREF=" + i2 + " ");
        }
        if (!z) {
            double maxCMDate = getSqlHelper().getMaxCMDate(CustomerImage.class, "DOCNR=" + customerDocumentType.docNumber + " AND DOCTYP=" + customerDocumentType.docType);
            if (maxCMDate != 0.0d) {
                sb.append("AND " + checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " C") + ">" + maxCMDate);
            } else {
                z = true;
            }
        }
        sb.append(") R ");
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb.toString()).withOrderBy("ORDER BY CUSTOMERREF").withProcessType(ProcessType.GETCUSTOMERIMAGE).withTableDelete(z).withDeleteCondition("(DOCNR=" + customerDocumentType.docNumber + " OR DOCNR IS NULL) AND (DOCTYP=" + customerDocumentType.docType + " OR DOCTYP IS NULL)").withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getItems(boolean z) {
        String str;
        String str2;
        int languageId = ContextUtils.getLanguageId();
        if (languageId != 1) {
            str = "SELECT * FROM (SELECT  I.LOGICALREF, I.CODE,ISNULL(LN.FIELDCONT,I.NAME) AS NAME,I.NAME3 [NAME2],CAST(I.SELLVAT AS decimal(10,2)) [VAT],CAST(I.RETURNVAT AS decimal(10,2)) [RETURNVAT],I.SPECODE [SPECODE],I.STGRPCODE [STGRPCODE],SP.DEFINITION_ [STGRPNAME], I.PAYMENTREF [PAYMENTREF],I.MARKREF [MARKREF],I.CARDTYPE [CARDTYPE],M.DESCR [MARK],M.CODE [MARKCODE],I.TRACKTYPE [TRACKTYPE],I.GTIPCODE [GTIPCODE], " + checkFDateColumn(FDateUtils.isFDateFieldEnabled(FDateField.ITEMS), " I") + " AS CMDATE,I.SPECODE2 [SPECODE2],I.SPECODE3 [SPECODE3],I.SPECODE4 [SPECODE4],I.SPECODE5 [SPECODE5], I.CANCONFIGURE [ISVARYANT], I.ACTIVE [ISACTIVE],I.IMAGEINC, I.ADDTAXREF [ADDTAXREF] ,I.LOCTRACKING, CAST(I.SELLPRVAT AS decimal(10,2)) [RETAILVAT], CAST(I.RETURNPRVAT AS decimal(10,2)) [RETAILRETURNVAT], I.PRODUCERCODE, I.CANDEDUCT, I.DEDUCTCODE, I.SALEDEDUCTPART1, I.SALEDEDUCTPART2, I.COMPKDVUSE, I.SALESDISPRATETOT";
        } else {
            str = "SELECT * FROM (SELECT  I.LOGICALREF, I.CODE, I.NAME,I.NAME3 [NAME2],CAST(I.SELLVAT AS decimal(10,2)) [VAT],CAST(I.RETURNVAT AS decimal(10,2)) [RETURNVAT],I.SPECODE [SPECODE],I.STGRPCODE [STGRPCODE],SP.DEFINITION_ [STGRPNAME],I.PAYMENTREF [PAYMENTREF],I.MARKREF [MARKREF],I.CARDTYPE [CARDTYPE],M.DESCR [MARK],M.CODE [MARKCODE],I.TRACKTYPE [TRACKTYPE],I.GTIPCODE [GTIPCODE], " + checkFDateColumn(FDateUtils.isFDateFieldEnabled(FDateField.ITEMS), " I") + " AS CMDATE,I.SPECODE2 [SPECODE2],I.SPECODE3 [SPECODE3],I.SPECODE4 [SPECODE4],I.SPECODE5 [SPECODE5], I.CANCONFIGURE [ISVARYANT] , I.ACTIVE [ISACTIVE],I.IMAGEINC, I.ADDTAXREF [ADDTAXREF] ,I.LOCTRACKING, CAST(I.SELLPRVAT AS decimal(10,2)) [RETAILVAT], CAST(I.RETURNPRVAT AS decimal(10,2)) [RETAILRETURNVAT], I.PRODUCERCODE, I.CANDEDUCT, I.DEDUCTCODE, I.SALEDEDUCTPART1, I.SALEDEDUCTPART2, I.COMPKDVUSE, I.SALESDISPRATETOT";
        }
        String str3 = str + " , ROW_NUMBER() OVER (ORDER BY I.LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.ITEMS + " I WITH(NOLOCK)   LEFT JOIN " + mLogoTigerTableName.SPECODES + " SP WITH(NOLOCK)  ON SP.SPECODE=I.STGRPCODE AND SP.CODETYPE=4 AND SP.SPECODETYPE=0 LEFT JOIN " + mLogoTigerTableName.MARK + " M WITH(NOLOCK)  ON M.LOGICALREF=I.MARKREF  LEFT JOIN " + mLogoTigerTableName.STINVTOT + " AS IV WITH(NOLOCK)  ON IV.STOCKREF=I.LOGICALREF  AND IV.INVENNO<>-1 AND (IV.DATE_ > CONVERT(dateTime, '5-19-1919', 101)) AND (IV.DATE_ <= " + ErpCreator.getInstance().getmBaseErp().getStockTotalsWhereDateExpression() + ") ";
        if (languageId != 1) {
            str3 = str3 + " LEFT JOIN " + mLogoTigerTableName.LNGEXCSETS + " LN WITH(NOLOCK) ON LN.DOCREF=I.LOGICALREF AND LN.FIELDID=1 AND LN.LANGID=" + languageId;
        }
        String str4 = str3 + "WHERE I.CARDTYPE<>22 AND I.CARDTYPE NOT IN (20,21,22) ";
        if (!z) {
            double maxCMDate = getSqlHelper().getMaxCMDate(Item.class);
            if (maxCMDate != 0.0d) {
                str4 = str4 + SqlLiteVariable._AND + checkFDateColumn(FDateUtils.isFDateFieldEnabled(FDateField.ITEMS), " I") + " > " + maxCMDate;
            } else {
                z = true;
            }
        }
        if (z) {
            str4 = str4 + " AND I.ACTIVE=0 ";
        }
        String str5 = str4 + buildSqlCondition(getItemsFilter(false, false, "I"), false);
        if (languageId == 1) {
            str2 = str5 + " GROUP BY I.PRODUCERCODE, I.LOGICALREF,I.PAYMENTREF, I.CODE, I.NAME,I.NAME3,I.MARKREF, M.DESCR,M.CODE ,I.ISONR,I.SELLVAT, I.RETURNVAT,I.SELLPRVAT, I.SPECODE,I.SPECODE2,I.SPECODE3,I.SPECODE4,I.SPECODE5,I.STGRPCODE,SP.DEFINITION_, I.TRACKTYPE,I.CARDTYPE,I.LOCTRACKING,I.CANCONFIGURE  ,I.CAPIBLOCK_CREADEDDATE,I.CAPIBLOCK_MODIFIEDDATE,I.GTIPCODE,I.EXPCTGNO,I.B2CCODE,I.PRODCOUNTRY,I.SHELFLIFE,I.SHELFDATE, I.ACTIVE,I.IMAGEINC,I.ADDTAXREF ,I.LOCTRACKING, I.SELLPRVAT, I.RETURNPRVAT, I.CANDEDUCT, I.DEDUCTCODE, I.SALEDEDUCTPART1, I.SALEDEDUCTPART2, I.COMPKDVUSE, I.SALESDISPRATETOT";
        } else {
            str2 = str5 + " GROUP BY I.PRODUCERCODE, I.LOGICALREF,I.PAYMENTREF, I.CODE,I.NAME,I.NAME3,I.MARKREF, M.DESCR ,M.CODE,I.ISONR,I.SELLVAT, I.RETURNVAT,I.SELLPRVAT, I.SPECODE,I.SPECODE2,I.SPECODE3,I.SPECODE4,I.SPECODE5,I.STGRPCODE,SP.DEFINITION_, I.TRACKTYPE,I.CARDTYPE,I.LOCTRACKING,I.CANCONFIGURE  ,I.CAPIBLOCK_CREADEDDATE,I.CAPIBLOCK_MODIFIEDDATE,I.GTIPCODE,I.EXPCTGNO,I.B2CCODE,I.PRODCOUNTRY,I.SHELFLIFE,I.SHELFDATE ,LN.FIELDCONT, I.ACTIVE,I.IMAGEINC,I.ADDTAXREF ,I.LOCTRACKING, I.SELLPRVAT, I.RETURNPRVAT, I.CANDEDUCT, I.DEDUCTCODE, I.SALEDEDUCTPART1, I.SALEDEDUCTPART2, I.COMPKDVUSE, I.SALESDISPRATETOT";
        }
        if (FDateUtils.isFDateFieldEnabled(FDateField.ITEMS)) {
            str2 = str2 + ", " + getFDateSql(" I");
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(str2 + " ) R ").withOrderBy("ORDER BY LOGICALREF").withProcessType(ProcessType.GETITEM).withTableDelete(z).withDatabaseSave(true).build();
    }

    public static TigerSelectResult getLoginQuery(User user, String str) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.tiger_login_query, Integer.valueOf(user.getUserId()), user.getUserName(), str, user.getPassword().replace("'", "''"))).withProcessType(ProcessType.LOGIN).build();
    }

    public TigerSelectResult getRoute() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM ( SELECT L.LOGICALREF,(L.CODE + ', '+ L.DEFINITION_) [DEFINITION_] ,ROW_NUMBER() OVER (ORDER BY L.LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.ROUTE + " L WITH(NOLOCK)  WHERE L.SALESMANREF=(SELECT S.LOGICALREF FROM " + mLogoTigerTableName.SLSMAN + " S WITH(NOLOCK) WHERE S.ACTIVE=0 AND S.FIRMNR=" + getUser().getFirmNr() + " AND S.CODE='" + getUser().getCode() + "')) R ").withProcessType(ProcessType.GETROUTE).withDatabaseSave(true).withTableDelete(true).build();
    }

    public TigerSelectResult getRoutetrs() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(("SELECT * FROM ( SELECT LOGICALREF, ROUTEREF, LINENO_, CLIENTREF ,ROW_NUMBER() OVER (ORDER BY LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.ROUTETRS) + " WHERE ROUTEREF IN (SELECT LOGICALREF FROM " + mLogoTigerTableName.ROUTE + " WITH(NOLOCK)  WHERE SALESMANREF = (SELECT LOGICALREF FROM " + mLogoTigerTableName.SLSMAN + " WITH(NOLOCK)  WHERE CODE = '" + getUser().getCode() + "' AND FIRMNR = " + getUser().getFirmNr() + ") AND STATUS = 1)) R ").withProcessType(ProcessType.GETROUTETRS).withDatabaseSave(true).withTableDelete(true).build();
    }

    public TigerSelectResult getCustomerSalesmanRelation() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM ( SELECT L.LINENO_ [LINENO_], L.CLIENTREF[CLREF], L.VISITDAY [VISITDAY] ,  CONVERT(VARCHAR(10),L.BEGDATE,105) AS  BEGDATE , ROW_NUMBER() OVER (ORDER BY L.LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.SLSCLREL + " L WITH(NOLOCK) WHERE L.SALESMANREF=(SELECT S.LOGICALREF [LOGICALREF] FROM " + mLogoTigerTableName.SLSMAN + " S WITH(NOLOCK) WHERE S.ACTIVE=0 AND (BEGDATE<=GETDATE() OR BEGDATE IS NULL) AND S.FIRMNR=" + getUser().getFirmNr() + " AND S.CODE='" + getUser().getCode() + "')) R ").withProcessType(ProcessType.GETCUSTOMERSALESMANRELATION).withDatabaseSave(true).withTableDelete(true).build();
    }

    
    public TigerSelectResult getItemImages(boolean z) {
        FDateField fDateField = FDateField.ITEMS;
        String sb = "SELECT * FROM (SELECT  I.LOGICALREF AS ITEMREF,  CAST(IMG.LDATA AS VARBINARY(MAX)) AS ITEMIMAGE, " +
                checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " I") +
                " AS CMDATE ";
        String str = (sb + " , ROW_NUMBER() OVER (ORDER BY I.LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.ITEMS + " I WITH(NOLOCK)   INNER JOIN " + mLogoTigerTableName.FIRMDOC + " IMG WITH(NOLOCK) ON IMG.INFOREF=I.LOGICALREF AND IMG.DOCNR=11 AND IMG.INFOTYP=20 AND IMG.DOCTYP=0 ") + "WHERE I.CARDTYPE<>22 AND I.ACTIVE=0 AND I.IMAGEINC=1  AND I.CARDTYPE NOT IN (20,21,22) ";
        if (!z) {
            double maxCMDate = getSqlHelper().getMaxCMDate(ItemImage.class);
            if (maxCMDate != 0.0d) {
                str = str + SqlLiteVariable._AND + checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " I") + " > " + maxCMDate;
            } else {
                z = true;
            }
        }
        String str2 = (str + buildSqlCondition(getItemsFilter(false, false, "I"), false)) + " GROUP BY I.LOGICALREF, I.CAPIBLOCK_CREADEDDATE,I.CAPIBLOCK_MODIFIEDDATE , CAST(IMG.LDATA AS varbinary(MAX)) ";
        if (FDateUtils.isFDateFieldEnabled(fDateField)) {
            str2 = str2 + ", " + getFDateSql(" I");
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(str2 + " ) R ").withOrderBy("ORDER BY ITEMREF").withProcessType(ProcessType.GETITEMIMAGE).withTableDelete(z).withDatabaseSave(true).build();
    }

    public TigerSelectResult getService(boolean z) {
        String str;
        String str2;
        int languageId = ContextUtils.getLanguageId();
        if (languageId == 1) {
            str = "SELECT * FROM ( SELECT  I.LOGICALREF,I.CODE [CODE], I.DEFINITION_ [NAME], CAST(I.VAT AS decimal(10,2)) [VAT],I.SPECODE [SPECODE],I.PAYMENTREF [PAYMENTREF],I.CARDTYPE , 0 AS TRACKTYPE, " + checkFDateColumn(FDateUtils.isFDateFieldEnabled(FDateField.SERVICES), " I") + " AS CMDATE, ROW_NUMBER() OVER (ORDER BY I.LOGICALREF) AS RowNum, I.ADDTAXREF [ADDTAXREF] FROM ";
        } else {
            str = "SELECT * FROM ( SELECT  I.LOGICALREF,I.CODE [CODE],  ISNULL(LN.FIELDCONT,'Malzeme Adi Bulunamadi') AS NAME, CAST(I.VAT AS decimal(10,2)) [VAT],I.SPECODE [SPECODE],I.PAYMENTREF [PAYMENTREF],I.CARDTYPE , 0 AS TRACKTYPE, " + checkFDateColumn(FDateUtils.isFDateFieldEnabled(FDateField.SERVICES), " I") + " AS CMDATE, ROW_NUMBER() OVER (ORDER BY I.LOGICALREF) AS RowNum, I.ADDTAXREF [ADDTAXREF] FROM ";
        }
        String str3 = str + mLogoTigerTableName.SRVCARD + " I  WITH(NOLOCK) LEFT JOIN " + mLogoTigerTableName.SRVTOT + " IV  WITH(NOLOCK) ON IV.CARDREF = I.LOGICALREF AND IV.INVENNO <> - 1 ";
        if (languageId != 1) {
            str3 = str3 + " LEFT JOIN " + mLogoTigerTableName.LNGEXCSETS + " LN  WITH(NOLOCK)  ON LN.DOCREF=I.LOGICALREF AND LN.FIELDID=7 AND LN .LANGID = " + languageId;
        }
        String str4 = str3 + " WHERE I.CARDTYPE <> 22 AND I.ACTIVE = 0  AND I.CARDTYPE =2";
        if (!z) {
            double maxCMDate = getSqlHelper().getMaxCMDate(Service.class);
            if (maxCMDate != 0.0d) {
                str4 = str4 + SqlLiteVariable._AND + checkFDateColumn(FDateUtils.isFDateFieldEnabled(FDateField.SERVICES), " I") + " > " + maxCMDate;
            } else {
                z = true;
            }
        }
        String str5 = str4 + buildSqlCondition(getItemsFilter(false, true, "I"), false);
        if (languageId == 1) {
            str2 = str5 + " GROUP BY I.LOGICALREF, I.PAYMENTREF, I.CODE, I.DEFINITION_, I.VAT,I.RETURNVAT, I.SPECODE, I.CARDTYPE, I.CAPIBLOCK_CREADEDDATE, I.CAPIBLOCK_MODIFIEDDATE, I.ADDTAXREF";
        } else {
            str2 = str5 + " GROUP BY I.LOGICALREF, I.PAYMENTREF, I.CODE, LN.FIELDCONT, I.VAT,I.RETURNVAT, I.SPECODE, I.CARDTYPE, I.CAPIBLOCK_CREADEDDATE, I.CAPIBLOCK_MODIFIEDDATE, I.ADDTAXREF";
        }
        if (FDateUtils.isFDateFieldEnabled(FDateField.SERVICES)) {
            str2 = str2 + ", " + getFDateSql(" I");
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(str2 + " ) R ").withOrderBy("ORDER BY LOGICALREF").withProcessType(ProcessType.GETSERVICE).withDatabaseSave(true).withTableDelete(z).build();
    }

    
    public TigerSelectResult getCompositeColies(boolean z) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM ( SELECT S.STCREF [STOCKREF], S.AMNT [AMOUNT], S.PRICE [PRICE],  S.PERC [PERC], S.MAINCREF [MAINCREF],S.LINENO_ [LINENO_], S.SOURCEINDEX [SOURCEINDEX], S.UOMREF [UOMREF], S.VRNTREF, V.CODE [VRNTCODE], I.NAME [ITEMNAME], I.CODE [ITEMCODE],  U.UNITLINEREF [UNITLINEREF], L.UNITSETREF [UNITSETREF], L.CODE [UNITCODE], I.VAT [VAT], CAST(U.CONVFACT1 AS decimal(10,2)) [CONVFACT1], CAST(U.CONVFACT2 AS DECIMAL(10,2)) [CONVFACT2],  ROW_NUMBER() OVER (ORDER BY S.LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.STCOMPLN + " S WITH(NOLOCK) LEFT JOIN " + mLogoTigerTableName.VARIANT + " V WITH(NOLOCK) ON V.LOGICALREF = S.VRNTREF LEFT JOIN " + mLogoTigerTableName.ITEMS + " I WITH(NOLOCK) ON I.LOGICALREF = S.STCREF  LEFT JOIN " + mLogoTigerTableName.ITMUNITA + " U WITH(NOLOCK) ON U.ITEMREF = S.STCREF AND U.LINENR = 1  LEFT JOIN " + mLogoTigerTableName.UNITSETL + " L WITH(NOLOCK) ON U.UNITLINEREF = L.LOGICALREF AND U.LINENR = 1  WHERE S.MAINCREF IN (" + getItemsLogicalRef() + ")) R ").withProcessType(ProcessType.GETSTCOMPLN).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getItemUnits(boolean z) {
        FDateField fDateField = FDateField.ITEMS;
        String sb2 = "SELECT * FROM  (SELECT IT.ITEMREF [ITEMREF], L.LOGICALREF, L.CODE, CAST(IT.CONVFACT1 AS decimal(10,2)) [CONVFACT1], CAST(IT.CONVFACT2 AS decimal(10,2)) [CONVFACT2], L.UNITSETREF [UNITSETREF], L.LINENR [LINENR], IT.MTRLCLAS, IT.PURCHCLAS, IT.SALESCLAS,IT.VOLUME_ AS VOLUME, IT.WEIGHT, GROSSVOLUME,IT.GROSSWEIGHT,IT.VOLUMEREF,IT.WEIGHTREF,IT.GROSSWGHTREF,IT .GROSSVOLREF, IT.SALESPRIORITY,  IT.WIDTH, IT.WIDTHREF, IT.LENGTH, IT.LENGTHREF, IT.HEIGHT, IT.HEIGHTREF, IT.AREA, IT.AREAREF, " +
                checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " I") +
                " AS [CMDATE], ROW_NUMBER() OVER (ORDER BY IT.ITEMREF, IT.LOGICALREF) AS RowNum FROM " +
                mLogoTigerTableName.ITEMS +
                " I WITH(NOLOCK) INNER JOIN  " +
                mLogoTigerTableName.ITMUNITA +
                " IT WITH(NOLOCK) ON I.LOGICALREF = IT.ITEMREF INNER JOIN " +
                mLogoTigerTableName.UNITSETL +
                " L WITH(NOLOCK) ON IT.UNITLINEREF = L.LOGICALREF WHERE I.ACTIVE=0  AND I.CARDTYPE NOT IN (20,21,22) AND IT.SALESCLAS=1 AND IT.VARIANTREF=0 ";
        if (!z) {
            double maxCMDate = getSqlHelper().getMaxCMDate(ItemUnits.class);
            if (maxCMDate != 0.0d) {
                sb2 = sb2 + SqlLiteVariable._AND + checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " I") + " > " + maxCMDate + " ";
            } else {
                z = true;
            }
        }
        String str = (sb2 + buildSqlCondition(getItemsFilter(false, false, "I"), false)) + " GROUP BY IT.ITEMREF, IT.LOGICALREF ,L.LOGICALREF, L.CODE, IT.CONVFACT1, IT.CONVFACT2, L.UNITSETREF, L.LINENR,IT.MTRLCLAS,IT.PURCHCLAS,IT .SALESCLAS  ,IT.VOLUME_, IT.WEIGHT, GROSSVOLUME,IT.GROSSWEIGHT,IT.VOLUMEREF,IT.WEIGHTREF,IT.GROSSWGHTREF,IT.GROSSVOLREF, IT.SALESPRIORITY, I.CAPIBLOCK_CREADEDDATE, I.CAPIBLOCK_MODIFIEDDATE , IT.WIDTH, IT.LENGTH, IT.HEIGHT, IT.AREA, IT.WIDTHREF, IT.LENGTHREF, IT.HEIGHTREF, IT.AREAREF ";
        if (FDateUtils.isFDateFieldEnabled(fDateField)) {
            str = str + ", " + getFDateSql(" I");
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(str + ") R ").withProcessType(ProcessType.GETITEMUNIT).withTableDelete(z).withDatabaseSave(true).build();
    }

    public TigerSelectResult getServiceUnit(boolean z) {
        FDateField fDateField = FDateField.SERVICES;
        String sb2 = "SELECT * FROM  (SELECT L.LOGICALREF,IT.SRVREF AS ITEMREF,L.CODE,L.CONVFACT1,L.CONVFACT2,L.UNITSETREF, L.LINENR, IT.PRIORITY, " +
                checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " I") +
                " AS [CMDATE], ROW_NUMBER() OVER (ORDER BY IT.SRVREF, IT.LOGICALREF) AS RowNum FROM " +
                mLogoTigerTableName.SRVCARD +
                " I WITH(NOLOCK) INNER JOIN  " +
                mLogoTigerTableName.SRVUNITA +
                " IT WITH(NOLOCK) ON I.LOGICALREF = IT.SRVREF INNER JOIN " +
                mLogoTigerTableName.UNITSETL +
                " L WITH(NOLOCK) ON IT.UNITLINEREF = L.LOGICALREF WHERE I.ACTIVE=0  AND I.CARDTYPE NOT IN (22)  AND I.CARDTYPE=2";
        if (!z) {
            double maxCMDate = getSqlHelper().getMaxCMDate(ServiceUnit.class);
            if (maxCMDate != 0.0d) {
                sb2 = sb2 + SqlLiteVariable._AND + checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " I") + " > " + maxCMDate + " ";
            } else {
                z = true;
            }
        }
        String str = (sb2 + buildSqlCondition(getItemsFilter(false, true, "I"), false)) + " GROUP BY IT.SRVREF, IT.LOGICALREF ,L.LOGICALREF, L.CODE, L.CONVFACT1, L.CONVFACT2, L.UNITSETREF, L.LINENR,I.CAPIBLOCK_CREADEDDATE, I.CAPIBLOCK_MODIFIEDDATE, IT.PRIORITY ";
        if (FDateUtils.isFDateFieldEnabled(fDateField)) {
            str = str + ", " + getFDateSql(" I");
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(str + ") R ").withProcessType(ProcessType.GETSERVICEUNIT).withTableDelete(z).withDatabaseSave(true).build();
    }

    public TigerSelectResult getUnit(boolean z) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM ( SELECT LOGICALREF, CODE ,NAME ,UNITSETREF ,LINENR , MAINUNIT ,  CONVFACT1, CONVFACT2 ,WIDTH , LENGTH , HEIGHT , AREA ,VOLUME_ AS VOLUME , WEIGHT , WIDTHREF ,LENGTHREF ,HEIGHTREF ,AREAREF, VOLUMEREF, WEIGHTREF, GLOBALCODE, DIVUNIT,   ROW_NUMBER() OVER (ORDER BY LOGICALREF ) AS RowNum  FROM " + mLogoTigerTableName.UNITSETL + "  WITH (NOLOCK) ) R ").withProcessType(ProcessType.GETUNIT).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getItemPrices(boolean z) {
        String priceSqlUnion = getPriceSqlUnion(false, z);
        String priceSqlUnion2 = getPriceSqlUnion(true, z);
        String str = "SELECT  * FROM (" + priceSqlUnion;
        if (!z) {
            double maxCMDate = getSqlHelper().getMaxCMDate(ItemPrice.class);
            if (maxCMDate != 0.0d) {
                str = str + SqlLiteVariable._AND + checkFDateColumn(FDateUtils.isFDateFieldEnabled(FDateField.PRICE_LIST), " P") + " > " + maxCMDate + " ";
            } else {
                z = false;
            }
        }
        String str2 = str + "  UNION ALL " + priceSqlUnion2;
        if (!z) {
            double maxCMDate2 = getSqlHelper().getMaxCMDate(ItemPrice.class);
            if (maxCMDate2 != 0.0d) {
                str2 = str2 + SqlLiteVariable._AND + checkFDateColumn(FDateUtils.isFDateFieldEnabled(FDateField.PRICE_LIST), " P") + " > " + maxCMDate2 + " ";
            }
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(str2 + ") R ").withProcessType(ProcessType.GETPRICE).withDatabaseSave(true).withTableDelete(z).build();
    }

    private String getPriceSqlUnion(boolean z, boolean z2) {
        String str;
        String[] strArr;
        String str2;
        String str3;
        int i2;
        StringBuilder sb = new StringBuilder();
        if (z) {
            str = mLogoTigerTableName.SRVCARD;
            strArr = getSqlHelper().getParamValue(ParameterTypes.ptServiceCards).split("\\;");
            str2 = getSqlHelper().getParamValue(ParameterTypes.ptServiceCards_AuxiliaryCode).trim();
            str3 = getSqlHelper().getParamValue(ParameterTypes.ptServiceCards_SqlSentence);
            i2 = 4;
        } else {
            str = mLogoTigerTableName.ITEMS;
            String[] split = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards).split("\\;");
            String trim = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_AuxiliaryCode).trim();
            String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_SqlSentence);
            String trim2 = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_AuxiliaryCode2).trim();
            if (!trim2.equals("")) {
                sb.append("AND I.SPECODE2='" + trim2 + "' ");
            }
            String trim3 = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_AuxiliaryCode3).trim();
            if (!trim3.equals("")) {
                sb.append("AND I.SPECODE3='" + trim3 + "' ");
            }
            String trim4 = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_AuxiliaryCode4).trim();
            if (!trim4.equals("")) {
                sb.append("AND I.SPECODE4='" + trim4 + "' ");
            }
            String trim5 = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_AuxiliaryCode5).trim();
            if (!trim5.equals("")) {
                sb.append("AND I.SPECODE5='" + trim5 + "' ");
            }
            String trim6 = getSqlHelper().getParamValue(ParameterTypes.ptGroupCode).trim();
            if (!trim6.equals("")) {
                sb.append("AND I.STGRPCODE='" + trim6 + "' ");
            }
            String[] split2 = getSqlHelper().getParamValue(ParameterTypes.ptIsoNr).split("\\;");
            if (split2 != null && split2.length == 2) {
                sb.append("AND I.ISONR BETWEEN '" + split2[0] + "' AND '" + split2[1] + "' ");
            }
            strArr = split;
            str2 = trim;
            str3 = paramValue;
            i2 = 2;
        }
        if (strArr != null && strArr.length == 2) {
            sb.append(" AND I.CODE BETWEEN '" + strArr[0] + "' AND '" + strArr[1] + "' ");
        }
        if (!str2.equals("")) {
            sb.append("AND I.SPECODE='" + str2 + "' ");
        }
        if (str3.trim().length() > 0) {
            String sb2 = " AND I.LOGICALREF IN ( " +
                    str3.replace("_XXX_", "_" + getUser().getFirmNr() + "_").replace("_XX_", "_" + getUser().getPeridodNr() + "_") +
                    SqlLiteVariable._CLOSE_BRACKET;
            sb.append(sb2);
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("SELECT P.LOGICALREF, I.LOGICALREF [ITEMREF], P.UOMREF [UNITREF], CAST(P.PRICE AS DECIMAL(18, 5)) [PRICE], (CONVERT(NVARCHAR(18), CAST(P.PRICE AS DECIMAL(18, 5))) + ' /' + C.CURCODE) [CPRICE], C.CURCODE, P.CURRENCY [CURNR], P.INCVAT, P.PTYPE, " + checkFDateColumn(FDateUtils.isFDateFieldEnabled(FDateField.PRICE_LIST), " P") + " AS [CMDATE], P.CLIENTCODE, P.CLSPECODE, P.CLSPECODE2, P.CLSPECODE3, P.CLSPECODE4, P.CLSPECODE5, P.TRADINGGRP, P.SHIPTYP, P.PAYPLANREF, P.UNITCONVERT, P.PRIORITY, P.CLCYPHCODE, CONVERT(VARCHAR, P.BEGDATE ,112)  [BEGDATE], CONVERT(VARCHAR, P.ENDDATE ,112) [ENDDATE], P.CODE AS PRICECODE, ROW_NUMBER() OVER (ORDER BY P.LOGICALREF) AS RowNum, P.CYPHCODE, P.GRPCODE , P.CLTRADINGGRP , P.LEADTIME , P.ORDERNR , P.MARKREF , P.DEFINITION_ , P.ACTIVE , P.VARIANTCODE,  PD.DIVCODES FROM  " + mLogoTigerTableName.PRCLIST + " P  WITH(NOLOCK) ");
        sb3.append("INNER JOIN " + str + " I  WITH(NOLOCK)  ON I.LOGICALREF = P.CARDREF INNER JOIN " + mLogoTigerTableName.UNITSETL + " U  WITH(NOLOCK)  ON U.LOGICALREF = P.UOMREF INNER JOIN " + mLogoTigerTableName.CURRENCYLIST + " C  WITH(NOLOCK)  ON C.CURTYPE = P.CURRENCY ");
        String sb4 = "LEFT JOIN " +
                mLogoTigerTableName.TRADGRP +
                " T WITH (NOLOCK) ON T.GCODE = P.TRADINGGRP ";
        sb3.append(sb4);
        sb3.append("LEFT JOIN " + mLogoTigerTableName.TRADGRP + " CT WITH (NOLOCK) ON CT.GCODE = P.CLTRADINGGRP ");
        sb3.append("LEFT JOIN " + mLogoTigerTableName.PRCLSTDIV + " PD WITH (NOLOCK) ON PD.PARENTPRCREF = P.LOGICALREF ");
        sb3.append("WHERE  C.FIRMNR = " + getUser().getFirmNr() + " AND P.CARDREF = I.LOGICALREF AND P.PTYPE = " + i2 + " AND I.ACTIVE = 0 AND I.CARDTYPE NOT IN (20, 21, 22) ");
        sb3.append(" AND (T.ACTIVE = 0 OR T.ACTIVE IS NULL ) ");
        sb3.append(" AND (CT.ACTIVE = 0 OR CT.ACTIVE IS NULL ) ");
        if (z2) {
            sb3.append(" AND GETDATE() <= CONVERT(DATETIME, P.ENDDATE + '23:59:59', 110) AND P.ACTIVE = 0 ");
        }
        sb3.append(sb);
        int convertStringToInt = StringUtils.convertStringToInt(getSqlHelper().getParamValue(ParameterTypes.ptPricePriority));
        if (convertStringToInt > 0) {
            sb3.append(" AND P.PRIORITY=" + convertStringToInt);
        }
        return sb3.toString();
    }

    
    public TigerSelectResult getItemAddTaxLines(boolean z) {
        String str;
        String str2;
        String str3;
        boolean z2;
        String str4;
        String str5;
        String sb;
        String addTaxSqlUnion = getAddTaxSqlUnion(false);
        String addTaxSqlUnion2 = getAddTaxSqlUnion(true);
        String str6 = "SELECT  * FROM (" + addTaxSqlUnion;
        String str7 = "";
        if (z) {
            str = addTaxSqlUnion2;
            str2 = " I";
            str3 = " OR ";
            z2 = z;
        } else {
            str = addTaxSqlUnion2;
            double maxCMDate = getSqlHelper().getMaxCMDate(AddTax.class);
            double lastDate = getSharedPreferencesHelper().getLastDate(false);
            if (maxCMDate != 0.0d) {
                str5 = SqlLiteVariable._AND + checkFDateColumn(FDateUtils.isFDateFieldEnabled(FDateField.ADD_TAX), " TAX") + " > " + maxCMDate + " ";
                z2 = z;
            } else {
                str5 = "";
                z2 = true;
            }
            if (z2 || lastDate == 0.0d) {
                str2 = " I";
                str3 = " OR ";
            } else {
                if (str5.isEmpty()) {
                    sb = str5 + SqlLiteVariable._AND;
                    str3 = " OR ";
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str5);
                    str3 = " OR ";
                    sb2.append(str3);
                    sb = sb2.toString();
                }
                StringBuilder sb3 = new StringBuilder();
                sb3.append(sb);
                str2 = " I";
                sb3.append(checkFDateColumn(FDateUtils.isFDateFieldEnabled(FDateField.ITEMS), str2));
                sb3.append(" > ");
                sb3.append(lastDate);
                sb3.append(" ");
                str5 = sb3.toString();
            }
            if (!str5.isEmpty()) {
                str6 = str6 + str5;
            }
        }
        String str8 = str6 + "  UNION ALL " + str;
        if (!z2) {
            double maxCMDate2 = getSqlHelper().getMaxCMDate(AddTax.class);
            double lastDate2 = getSharedPreferencesHelper().getLastDate(false);
            if (maxCMDate2 != 0.0d) {
                str7 = SqlLiteVariable._AND + checkFDateColumn(FDateUtils.isFDateFieldEnabled(FDateField.ADD_TAX), " TAX") + " > " + lastDate2 + " ";
            }
            if (lastDate2 != 0.0d) {
                if (str7.isEmpty()) {
                    str4 = str7 + SqlLiteVariable._AND;
                } else {
                    str4 = str7 + str3;
                }
                str7 = str4 + checkFDateColumn(FDateUtils.isFDateFieldEnabled(FDateField.ITEMS), str2) + " > " + lastDate2 + " ";
            }
            if (!str7.isEmpty()) {
                str8 = str8 + str7;
            }
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(str8 + ") R ").withProcessType(ProcessType.GETADDTAX).withDatabaseSave(true).withTableDelete(z2).build();
    }

    private String getAddTaxSqlUnion(boolean z) {
        String str;
        int i2;
        if (z) {
            str = mLogoTigerTableName.SRVCARD;
            i2 = 4;
        } else {
            str = mLogoTigerTableName.ITEMS;
            i2 = 2;
        }
        String sb2 = " INNER JOIN " +
                str +
                " I  WITH(NOLOCK)  ON TAX.LOGICALREF = I.ADDTAXREF ";
        String sb = "SELECT ROW_NUMBER() OVER (ORDER BY TAX.LOGICALREF) AS RowNum, MYLINE.LOGICALREF [LOGICALREF],  TAXCODE , TAXDEF,  MYLINE.ADDTAXREF, CONVERT(varchar,MYLINE.BEGDATE,112) AS BEGDATE, MYLINE.TAXTYPE, MYLINE.AMOUNT, MYLINE.RATE, MYLINE.DISCAMOUNT, MYLINE.UNITREF,  MYLINE.FCSPECODE, MYLINE.TRSPECODE, MYLINE.TRSPECODE2, " + checkFDateColumn(FDateUtils.isFDateFieldEnabled(FDateField.ADD_TAX), " TAX") + " AS [CMDATE], GLOBALCODE , EFFECTKDV ," + i2 + " AS PTYPE FROM  " + mLogoTigerTableName.ADDTAX + " TAX  WITH(NOLOCK) " +
                sb2 +
                " INNER JOIN " + mLogoTigerTableName.ADDTAXLINE + " MYLINE ON MYLINE.ADDTAXREF = TAX.LOGICALREF " +
                " WHERE I.ACTIVE = 0 " +
                buildSqlCondition(getItemsFilter(false, z, "I"), false);
        return sb;
    }

    public TigerSelectResult getItemStock(boolean z) {
        String str;
        if (FDateUtils.getInstance().getFDateModel().getfDateOnOrFiche() == 1 && FDateUtils.getInstance().getFDateModel().getfDateOnDeletedRecs() == 1 && FDateUtils.getInstance().getFDateModel().getfDateOnStFiche() == 1) {
            return (TigerSelectResult) getItemStockWithFDateWithExecSp(z);
        }
        String replace = getSqlHelper().getParamValue(ParameterTypes.ptGeneralSettings_Warehouse).replace(";", ",");
        String str2 = "";
        if (!z) {
            double lastDate = getSharedPreferencesHelper().getLastDate(false);
            if (lastDate != 0.0d) {
                str = " INNER JOIN  (SELECT DISTINCT(STOCKREF) FROM (SELECT STLINE.STOCKREF , MAX(STFICHE.CAPIBLOCK_CREADEDDATE) AS CREADEDDATE, MAX(STFICHE.CAPIBLOCK_MODIFIEDDATE) AS MODIFIEDDATE FROM " + mLogoTigerTableName.STFICHE + " STFICHE  WITH(NOLOCK)  INNER JOIN " + mLogoTigerTableName.STLINE + " STLINE WITH(NOLOCK)  ON STFICHE.LOGICALREF = STLINE.STFICHEREF WHERE  STLINE.LINETYPE IN (0,1,4,6,9) AND  dbo.GetWebOrderDateInt(STFICHE.CAPIBLOCK_CREADEDDATE, STFICHE.CAPIBLOCK_MODIFIEDDATE) > " + lastDate + " GROUP BY STLINE.STOCKREF UNION ALL  SELECT ORFLINE.STOCKREF, MAX(ORFICHE.CAPIBLOCK_CREADEDDATE) AS CREADEDDATE,  MAX(ORFICHE.CAPIBLOCK_MODIFIEDDATE) AS MODIFIEDDATE  FROM " + mLogoTigerTableName.ORFICHE + " ORFICHE WITH (NOLOCK)  INNER JOIN " + mLogoTigerTableName.ORFLINE + " ORFLINE WITH (NOLOCK) ON ORFICHE.LOGICALREF = ORFLINE.ORDFICHEREF  WHERE ORFLINE.LINETYPE IN(0, 1, 4, 6, 9)  AND dbo.GetWebOrderDateInt(ORFICHE.CAPIBLOCK_CREADEDDATE,ORFICHE.CAPIBLOCK_MODIFIEDDATE) > " + lastDate + " GROUP BY ORFLINE.STOCKREF UNION ALL  SELECT DEL.LOGICALREF, MAX(DEL.DELETEDDATE) AS CREADEDDATE, MAX(DEL.DELETEDDATE) AS MODIFIEDDATE  FROM WOR_DELETEDRECS DEL WITH (NOLOCK)  INNER JOIN " + mLogoTigerTableName.ITEMS + " I WITH (NOLOCK) ON DEL.LOGICALREF = I.LOGICALREF  WHERE FIRMNR = " + Integer.valueOf(getUser().getFirmNr()) + " AND PERIODNR = " + Integer.valueOf(getUser().getPeridodNr()) + " AND RECTYPE = 1 AND dbo.GetWebOrderDateInt(DEL.DELETEDDATE, DEL.DELETEDDATE) > " + lastDate + buildSqlCondition(getItemsFilter(false, false, "I"), false) + " AND I.CARDTYPE NOT IN (20,21,22)  GROUP BY DEL.LOGICALREF ) AS TMP_TBL ) AS TMP_TBLX ON TMP_TBLX.STOCKREF = I.LOGICALREF ";
                StringBuilder sb = new StringBuilder();
                sb.append("SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY S.INVENNO) [RowNum], S.INVENNO [WAREHOUSENR], (CONVERT(NVARCHAR(10), W.NR) + ', ' + W.NAME) AS AMBAR, S.STOCKREF [ITEMREF], ROUND((SUM(ISNULL(S.ONHAND, 0)) - SUM(ISNULL(S.RESERVED, 0)) - SUM(ISNULL(S.TEMPIN, 0)) + SUM(ISNULL(S.TEMPOUT, 0))), 7) AS REALSTOCK , ROUND(SUM(ISNULL(S.ONHAND, 0)), 7) AS ONHAND FROM ");
                sb.append(mLogoTigerTableName.STINVTOT);
                sb.append(" S WITH(NOLOCK)  INNER JOIN ");
                sb.append(mLogoTigerTableName.CAPIWHOUSE);
                sb.append(" W WITH(NOLOCK) ON S.INVENNO = W.NR AND W.FIRMNR = ");
                sb.append(Integer.valueOf(getUser().getFirmNr()));
                sb.append(" AND S.INVENNO<>-1 ");
                if (!replace.equals("")) {
                    str2 = " AND S.INVENNO IN (" + replace + ") ";
                }
                sb.append(str2);
                sb.append(" AND  (S.DATE_ > CONVERT(dateTime, '5-19-1919', 101)) AND (S.DATE_ <= ");
                sb.append(ErpCreator.getInstance().getmBaseErp().getStockTotalsWhereDateExpression());
                sb.append(") INNER JOIN ");
                sb.append(mLogoTigerTableName.ITEMS);
                sb.append(" I WITH(NOLOCK) ON I.LOGICALREF = S.STOCKREF  AND I.ACTIVE=0 AND I.CARDTYPE NOT IN (20,21,22) ");
                sb.append(str);
                return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((sb + buildSqlCondition(getItemsFilter(false, false, "I"), true)) + " GROUP BY S.INVENNO, S.STOCKREF, W.NAME, W.NR) R ").withProcessType(ProcessType.GETSTOCK).withTableDelete(z).withDatabaseSave(true).build();
            }
            z = true;
        }
        str = "";
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY S.INVENNO) [RowNum], S.INVENNO [WAREHOUSENR], (CONVERT(NVARCHAR(10), W.NR) + ', ' + W.NAME) AS AMBAR, S.STOCKREF [ITEMREF], ROUND((SUM(ISNULL(S.ONHAND, 0)) - SUM(ISNULL(S.RESERVED, 0)) - SUM(ISNULL(S.TEMPIN, 0)) + SUM(ISNULL(S.TEMPOUT, 0))), 7) AS REALSTOCK , ROUND(SUM(ISNULL(S.ONHAND, 0)), 7) AS ONHAND FROM ");
        sb2.append(mLogoTigerTableName.STINVTOT);
        sb2.append(" S WITH(NOLOCK)  INNER JOIN ");
        sb2.append(mLogoTigerTableName.CAPIWHOUSE);
        sb2.append(" W WITH(NOLOCK) ON S.INVENNO = W.NR AND W.FIRMNR = ");
        sb2.append(Integer.valueOf(getUser().getFirmNr()));
        sb2.append(" AND S.INVENNO<>-1 ");
        if (!replace.equals("")) {
        }
        sb2.append(str2);
        sb2.append(" AND  (S.DATE_ > CONVERT(dateTime, '5-19-1919', 101)) AND (S.DATE_ <= ");
        sb2.append(ErpCreator.getInstance().getmBaseErp().getStockTotalsWhereDateExpression());
        sb2.append(") INNER JOIN ");
        sb2.append(mLogoTigerTableName.ITEMS);
        sb2.append(" I WITH(NOLOCK) ON I.LOGICALREF = S.STOCKREF  AND I.ACTIVE=0 AND I.CARDTYPE NOT IN (20,21,22) ");
        sb2.append(str);
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((sb2 + buildSqlCondition(getItemsFilter(false, false, "I"), true)) + " GROUP BY S.INVENNO, S.STOCKREF, W.NAME, W.NR) R ").withProcessType(ProcessType.GETSTOCK).withTableDelete(z).withDatabaseSave(true).build();
    }

    public TigerSelectResult getItemStockWithFDate(boolean z) {
        String str;
        String replace = getSqlHelper().getParamValue(ParameterTypes.ptGeneralSettings_Warehouse).replace(";", ",");
        String str2 = "";
        if (!z) {
            double lastDate = getSharedPreferencesHelper().getLastDate(false);
            if (lastDate != 0.0d) {
                str = " INNER JOIN (SELECT REF FROM (SELECT STLINE.STOCKREF REF FROM " + mLogoTigerTableName.STFICHE + " STFICHE WITH(NOLOCK) INNER JOIN " + mLogoTigerTableName.STLINE + " STLINE WITH(NOLOCK) ON STFICHE.LOGICALREF = STLINE.STFICHEREF  WHERE STLINE.LINETYPE IN(0, 1, 4, 6, 9) AND FDATE >" + lastDate + " GROUP BY STLINE.STOCKREF  UNION  SELECT ORFLINE.STOCKREF REF  FROM " + mLogoTigerTableName.ORFICHE + " ORFICHE WITH(NOLOCK)  INNER JOIN " + mLogoTigerTableName.ORFLINE + " ORFLINE WITH(NOLOCK) ON ORFICHE.LOGICALREF = ORFLINE.ORDFICHEREF WHERE ORFLINE.LINETYPE IN(0, 1, 4, 6, 9)  AND FDATE >" + lastDate + " GROUP BY ORFLINE.STOCKREF  UNION  SELECT DEL.LOGICALREF REF  FROM WOR_DELETEDRECS DEL WITH(NOLOCK)  INNER JOIN " + mLogoTigerTableName.ITEMS + " I WITH(NOLOCK) ON DEL.LOGICALREF = I.LOGICALREF  WHERE FIRMNR = " + Integer.valueOf(getUser().getFirmNr()) + " AND PERIODNR = " + Integer.valueOf(getUser().getPeridodNr()) + " AND RECTYPE = 1 AND DEL.FDATE >" + lastDate + buildSqlCondition(getItemsFilter(false, false, "I"), false) + " AND I.CARDTYPE NOT IN (20,21,22)  GROUP BY DEL.LOGICALREF   ) AS TMP_TBL) AS TMP_TBLX ON TMP_TBLX.REF = X.LOGICALREF";
                StringBuilder sb = new StringBuilder();
                sb.append("SELECT RowNum,WAREHOUSENR,AMBAR,ITEMREF,REALSTOCK,ONHAND FROM (SELECT ROW_NUMBER() OVER(ORDER BY X.INVENNO) As [RowNum],X.INVENNO [WAREHOUSENR], AMBAR,  X.ITEMREF, (SUMONHAND - SUMRESERVED - SUMTEMPIN + SUMTEMPOUT) REALSTOCK,SUMONHAND ONHAND FROM ( SELECT DISTINCT S.STOCKREF [ITEMREF], S.INVENNO INVENNO, SUM(ISNULL(S.ONHAND,0)) OVER (PARTITION BY S.STOCKREF, S.INVENNO) SUMONHAND, SUM(ISNULL(S.RESERVED, 0)) OVER (PARTITION BY S.STOCKREF, S.INVENNO) SUMRESERVED , SUM(ISNULL(S.TEMPIN, 0)) OVER (PARTITION BY S.STOCKREF, S.INVENNO) SUMTEMPIN, SUM(ISNULL(S.TEMPOUT, 0)) OVER (PARTITION BY S.STOCKREF, S.INVENNO) SUMTEMPOUT, (CONVERT( NVARCHAR(10), W.NR)+', '+W.NAME) AS AMBAR, I.LOGICALREF, I.MARKREF, I.ACTIVE, I.CARDTYPE, I.CODE, I.ISONR, I.SPECODE, I.SPECODE2, I.SPECODE3, I.SPECODE4, I.SPECODE5, I.CANCONFIGURE, I.STGRPCODE FROM ");
                sb.append(mLogoTigerTableName.STINVTOT);
                sb.append(" S WITH(NOLOCK) INNER JOIN ");
                sb.append(mLogoTigerTableName.ITEMS);
                sb.append(" I WITH(NOLOCK) ON I.LOGICALREF = S.STOCKREF AND I.ACTIVE=0 AND I.CARDTYPE NOT IN (20,21,22) INNER JOIN ");
                sb.append(mLogoTigerTableName.CAPIWHOUSE);
                sb.append(" W WITH(NOLOCK) ON S.INVENNO = W.NR  AND W.FIRMNR = ");
                sb.append(Integer.valueOf(getUser().getFirmNr()));
                sb.append(" WHERE S.INVENNO<>-1 ");
                if (!replace.equals("")) {
                    str2 = " AND S.INVENNO IN (" + replace + ") ";
                }
                sb.append(str2);
                sb.append(" AND (S.DATE_ > CONVERT(DATETIME, '5-19-1919', 101)) AND (S.DATE_ <= ");
                sb.append(ErpCreator.getInstance().getmBaseErp().getStockTotalsWhereDateExpression());
                sb.append(") ) X ");
                sb.append(str);
                return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((sb + buildSqlCondition(getItemsFilter(false, false, "X"), true)) + ") R").withProcessType(ProcessType.GETSTOCK).withTableDelete(z).withDatabaseSave(true).build();
            }
            z = true;
        }
        str = "";
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT RowNum,WAREHOUSENR,AMBAR,ITEMREF,REALSTOCK,ONHAND FROM (SELECT ROW_NUMBER() OVER(ORDER BY X.INVENNO) As [RowNum],X.INVENNO [WAREHOUSENR], AMBAR,  X.ITEMREF, (SUMONHAND - SUMRESERVED - SUMTEMPIN + SUMTEMPOUT) REALSTOCK,SUMONHAND ONHAND FROM ( SELECT DISTINCT S.STOCKREF [ITEMREF], S.INVENNO INVENNO, SUM(ISNULL(S.ONHAND,0)) OVER (PARTITION BY S.STOCKREF, S.INVENNO) SUMONHAND, SUM(ISNULL(S.RESERVED, 0)) OVER (PARTITION BY S.STOCKREF, S.INVENNO) SUMRESERVED , SUM(ISNULL(S.TEMPIN, 0)) OVER (PARTITION BY S.STOCKREF, S.INVENNO) SUMTEMPIN, SUM(ISNULL(S.TEMPOUT, 0)) OVER (PARTITION BY S.STOCKREF, S.INVENNO) SUMTEMPOUT, (CONVERT( NVARCHAR(10), W.NR)+', '+W.NAME) AS AMBAR, I.LOGICALREF, I.MARKREF, I.ACTIVE, I.CARDTYPE, I.CODE, I.ISONR, I.SPECODE, I.SPECODE2, I.SPECODE3, I.SPECODE4, I.SPECODE5, I.CANCONFIGURE, I.STGRPCODE FROM ");
        sb2.append(mLogoTigerTableName.STINVTOT);
        sb2.append(" S WITH(NOLOCK) INNER JOIN ");
        sb2.append(mLogoTigerTableName.ITEMS);
        sb2.append(" I WITH(NOLOCK) ON I.LOGICALREF = S.STOCKREF AND I.ACTIVE=0 AND I.CARDTYPE NOT IN (20,21,22) INNER JOIN ");
        sb2.append(mLogoTigerTableName.CAPIWHOUSE);
        sb2.append(" W WITH(NOLOCK) ON S.INVENNO = W.NR  AND W.FIRMNR = ");
        sb2.append(Integer.valueOf(getUser().getFirmNr()));
        sb2.append(" WHERE S.INVENNO<>-1 ");
        if (!replace.equals("")) {
        }
        sb2.append(str2);
        sb2.append(" AND (S.DATE_ > CONVERT(DATETIME, '5-19-1919', 101)) AND (S.DATE_ <= ");
        sb2.append(ErpCreator.getInstance().getmBaseErp().getStockTotalsWhereDateExpression());
        sb2.append(") ) X ");
        sb2.append(str);
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((sb2 + buildSqlCondition(getItemsFilter(false, false, "X"), true)) + ") R").withProcessType(ProcessType.GETSTOCK).withTableDelete(z).withDatabaseSave(true).build();
    }

    public BaseSelectResult getItemStockWithFDateWithExecSp(boolean z) {
        String str;
        String replace = getSqlHelper().getParamValue(ParameterTypes.ptGeneralSettings_Warehouse).replace(";", ",");
        String str2 = "";
        if (!z) {
            double lastDate = getSharedPreferencesHelper().getLastDate(false);
            if (lastDate != 0.0d) {
                str = " WITH TMP_TBLX AS (SELECT REF FROM (SELECT STLINE.STOCKREF REF FROM " + mLogoTigerTableName.STFICHE + " STFICHE WITH(NOLOCK) INNER JOIN " + mLogoTigerTableName.STLINE + " STLINE WITH(NOLOCK) ON STFICHE.LOGICALREF = STLINE.STFICHEREF  WHERE STLINE.LINETYPE IN(0, 1, 4, 6, 9) AND FDATE >" + lastDate + " GROUP BY STLINE.STOCKREF  UNION  SELECT ORFLINE.STOCKREF REF  FROM " + mLogoTigerTableName.ORFICHE + " ORFICHE WITH(NOLOCK)  INNER JOIN " + mLogoTigerTableName.ORFLINE + " ORFLINE WITH(NOLOCK) ON ORFICHE.LOGICALREF = ORFLINE.ORDFICHEREF WHERE ORFLINE.LINETYPE IN(0, 1, 4, 6, 9)  AND FDATE >" + lastDate + " GROUP BY ORFLINE.STOCKREF  UNION  SELECT DEL.LOGICALREF REF  FROM WOR_DELETEDRECS DEL WITH(NOLOCK)  INNER JOIN " + mLogoTigerTableName.ITEMS + " I WITH(NOLOCK) ON DEL.LOGICALREF = I.LOGICALREF  WHERE FIRMNR = " + Integer.valueOf(getUser().getFirmNr()) + " AND PERIODNR = " + Integer.valueOf(getUser().getPeridodNr()) + " AND RECTYPE = 1 AND DEL.FDATE >" + lastDate + buildSqlCondition(getItemsFilter(false, false, "I"), false) + " AND I.CARDTYPE NOT IN (20,21,22)  GROUP BY DEL.LOGICALREF   ) AS TMP_TBL)";
                String str3 = str + "SELECT RowNum,WAREHOUSENR,AMBAR,ITEMREF,REALSTOCK,ONHAND FROM (SELECT ROW_NUMBER() OVER(ORDER BY X.INVENNO) As [RowNum],X.INVENNO [WAREHOUSENR], AMBAR,  X.ITEMREF, (SUMONHAND - SUMRESERVED - SUMTEMPIN + SUMTEMPOUT) REALSTOCK,SUMONHAND ONHAND FROM ( SELECT DISTINCT S.STOCKREF [ITEMREF], S.INVENNO INVENNO, SUM(ISNULL(S.ONHAND,0)) OVER (PARTITION BY S.STOCKREF, S.INVENNO) SUMONHAND, SUM(ISNULL(S.RESERVED, 0)) OVER (PARTITION BY S.STOCKREF, S.INVENNO) SUMRESERVED , SUM(ISNULL(S.TEMPIN, 0)) OVER (PARTITION BY S.STOCKREF, S.INVENNO) SUMTEMPIN, SUM(ISNULL(S.TEMPOUT, 0)) OVER (PARTITION BY S.STOCKREF, S.INVENNO) SUMTEMPOUT, (CONVERT( NVARCHAR(10), W.NR)+', '+W.NAME) AS AMBAR, I.LOGICALREF, I.MARKREF, I.ACTIVE, I.CARDTYPE, I.CODE, I.ISONR, I.SPECODE, I.SPECODE2, I.SPECODE3, I.SPECODE4, I.SPECODE5, I.CANCONFIGURE, I.STGRPCODE FROM " + mLogoTigerTableName.STINVTOT + " S WITH(NOLOCK) INNER JOIN " + mLogoTigerTableName.ITEMS + " I WITH(NOLOCK) ON I.LOGICALREF = S.STOCKREF AND I.ACTIVE=0 AND I.CARDTYPE NOT IN (20,21,22) INNER JOIN " + mLogoTigerTableName.CAPIWHOUSE + " W WITH(NOLOCK) ON S.INVENNO = W.NR  AND W.FIRMNR = " + Integer.valueOf(getUser().getFirmNr());
                if (!z) {
                    str3 = str3 + " INNER JOIN TMP_TBLX ON TMP_TBLX.REF = S.STOCKREF ";
                }
                StringBuilder sb = new StringBuilder();
                sb.append(str3);
                sb.append(" WHERE S.INVENNO<>-1 ");
                if (!replace.isEmpty()) {
                    str2 = " AND ISNULL(S.INVENNO, '') IN (" + replace + ") ";
                }
                sb.append(str2);
                sb.append(" AND (S.DATE_ > CONVERT(DATETIME, '5-19-1919', 101)) AND (S.DATE_ <= ");
                sb.append(ErpCreator.getInstance().getmBaseErp().getStockTotalsWhereDateExpression());
                sb.append(") ) X ");
                return TigerSelectResult.newBuilder().withSql((sb + buildSqlCondition(getItemsFilter(false, false, "X"), true)) + ") R").withProcessType(ProcessType.GETSTOCK).withTableDelete(z).withDatabaseSave(true).build();
            }
            z = true;
        }
        str = "";
        String str32 = str + "SELECT RowNum,WAREHOUSENR,AMBAR,ITEMREF,REALSTOCK,ONHAND FROM (SELECT ROW_NUMBER() OVER(ORDER BY X.INVENNO) As [RowNum],X.INVENNO [WAREHOUSENR], AMBAR,  X.ITEMREF, (SUMONHAND - SUMRESERVED - SUMTEMPIN + SUMTEMPOUT) REALSTOCK,SUMONHAND ONHAND FROM ( SELECT DISTINCT S.STOCKREF [ITEMREF], S.INVENNO INVENNO, SUM(ISNULL(S.ONHAND,0)) OVER (PARTITION BY S.STOCKREF, S.INVENNO) SUMONHAND, SUM(ISNULL(S.RESERVED, 0)) OVER (PARTITION BY S.STOCKREF, S.INVENNO) SUMRESERVED , SUM(ISNULL(S.TEMPIN, 0)) OVER (PARTITION BY S.STOCKREF, S.INVENNO) SUMTEMPIN, SUM(ISNULL(S.TEMPOUT, 0)) OVER (PARTITION BY S.STOCKREF, S.INVENNO) SUMTEMPOUT, (CONVERT( NVARCHAR(10), W.NR)+', '+W.NAME) AS AMBAR, I.LOGICALREF, I.MARKREF, I.ACTIVE, I.CARDTYPE, I.CODE, I.ISONR, I.SPECODE, I.SPECODE2, I.SPECODE3, I.SPECODE4, I.SPECODE5, I.CANCONFIGURE, I.STGRPCODE FROM " + mLogoTigerTableName.STINVTOT + " S WITH(NOLOCK) INNER JOIN " + mLogoTigerTableName.ITEMS + " I WITH(NOLOCK) ON I.LOGICALREF = S.STOCKREF AND I.ACTIVE=0 AND I.CARDTYPE NOT IN (20,21,22) INNER JOIN " + mLogoTigerTableName.CAPIWHOUSE + " W WITH(NOLOCK) ON S.INVENNO = W.NR  AND W.FIRMNR = " + Integer.valueOf(getUser().getFirmNr());
        if (!z) {
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str32);
        sb2.append(" WHERE S.INVENNO<>-1 ");
        if (!replace.isEmpty()) {
        }
        sb2.append(str2);
        sb2.append(" AND (S.DATE_ > CONVERT(DATETIME, '5-19-1919', 101)) AND (S.DATE_ <= ");
        sb2.append(ErpCreator.getInstance().getmBaseErp().getStockTotalsWhereDateExpression());
        sb2.append(") ) X ");
        return TigerSelectResult.newBuilder().withSql((sb2 + buildSqlCondition(getItemsFilter(false, false, "X"), true)) + ") R").withProcessType(ProcessType.GETSTOCK).withTableDelete(z).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getItemBarcode(boolean z) {
        FDateField fDateField = FDateField.ITEMS;
        String sb2 = "SELECT * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY  B.UNITLINEREF, B.ITEMREF  ) AS RowNum, B.UNITLINEREF [UNITREF], B.ITEMREF [ITEMREF], B.BARCODE [BARCODE],B.TYP [TYP],B.WBARCODESHIFT [BARCODESHIFT],B.VARIANTREF [VARIANTREF], " +
                checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " I") +
                " AS [CMDATE]  FROM  " +
                mLogoTigerTableName.UNITBARCODE +
                " B  WITH(NOLOCK) , " +
                mLogoTigerTableName.ITEMS +
                " I  WITH(NOLOCK) WHERE B.ITEMREF=I.LOGICALREF AND  I.ACTIVE=0 AND B.VARIANTREF IS NOT NULL " +
                buildSqlCondition(getItemsFilter(false, false, "I"), false);
        if (!z) {
            double maxCMDate = getSqlHelper().getMaxCMDate(UnitBarcode.class);
            if (maxCMDate != 0.0d) {
                sb2 = sb2 + SqlLiteVariable._AND + checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " I") + " > " + maxCMDate + " ";
            } else {
                z = true;
            }
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb2 + ") R ").withProcessType(ProcessType.GETBARCODE).withTableDelete(z).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getVariants(boolean z) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM ( SELECT  V.LOGICALREF [LOGICALREF],V.ITEMREF [ITEMREF],V.CODE [CODE],V.NAME [NAME] ,  ROW_NUMBER() OVER (ORDER BY V.LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.VARIANT + " V  WITH(NOLOCK)  WHERE ITEMREF IN ( SELECT  I.LOGICALREF [LOGICALREF] FROM " + mLogoTigerTableName.ITEMS + " I  WITH(NOLOCK)  LEFT JOIN " + mLogoTigerTableName.MARK + " M  WITH(NOLOCK)  ON M.LOGICALREF=I.MARKREF  LEFT JOIN " + mLogoTigerTableName.STINVTOT + " AS  IV  WITH(NOLOCK)  ON IV.STOCKREF=I.LOGICALREF  AND IV.INVENNO<>-1 AND (IV.DATE_ > CONVERT(dateTime, '5-19-1919', 101)) AND (IV.DATE_ <= " + ErpCreator.getInstance().getmBaseErp().getStockTotalsWhereDateExpression() + ") WHERE I.CARDTYPE<>22 AND I.ACTIVE=0 " + buildSqlCondition(getItemsFilter(true, false, "I"), false) + ")) R ").withProcessType(ProcessType.GETVARIANT).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getVariantUnits(boolean z) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY  L.LOGICALREF) RowNum, L.LOGICALREF [LOGICALREF],IT.ITEMREF [ITEMREF],L.CODE [CODE],IT.CONVFACT1 [CONVFACT1],IT.CONVFACT2 [CONVFACT2], IT.VOLUME_ AS VOLUME ,IT.WEIGHT,IT.GROSSVOLUME,IT.GROSSWEIGHT,IT.VOLUMEREF,IT.WEIGHTREF,IT.GROSSWGHTREF,IT.GROSSVOLREF  ,L.UNITSETREF [UNITSETREF],IT.VARIANTREF [VARIANTREF] FROM " + mLogoTigerTableName.UNITSETL + " L WITH(NOLOCK)  INNER JOIN   " + mLogoTigerTableName.ITMUNITA + " IT WITH(NOLOCK)  ON IT.UNITLINEREF=L.LOGICALREF INNER JOIN " + mLogoTigerTableName.ITEMS + " I WITH(NOLOCK)  ON L.UNITSETREF = I.UNITSETREF AND I.LOGICALREF=IT.ITEMREF WHERE IT.VARIANTREF<>0 AND I.CARDTYPE<>22 AND I.ACTIVE=0 " + buildSqlCondition(getItemsFilter(true, false, "I"), false) + ") R ").withProcessType(ProcessType.GETVARIANTUNIT).withTableDelete(true).withDatabaseSave(true).build();
    }

    public TigerSelectResult getVariantStock(boolean z) {
        String str;
        if (FDateUtils.getInstance().getFDateModel().getfDateOnOrFiche() == 1 && FDateUtils.getInstance().getFDateModel().getfDateOnDeletedRecs() == 1 && FDateUtils.getInstance().getFDateModel().getfDateOnStFiche() == 1) {
            return getVariantStockWithFDateWithExecSp(z);
        }
        String replace = getSqlHelper().getParamValue(ParameterTypes.ptGeneralSettings_Warehouse).replace(";", ",");
        String str2 = "";
        if (!z) {
            double lastDate = getSharedPreferencesHelper().getLastDate(false);
            if (lastDate != 0.0d) {
                str = " INNER JOIN (SELECT REF FROM (SELECT STLINE.VARIANTREF REF FROM " + mLogoTigerTableName.STFICHE + " STFICHE  WITH(NOLOCK) INNER JOIN " + mLogoTigerTableName.STLINE + " STLINE WITH(NOLOCK) ON STFICHE.LOGICALREF = STLINE.STFICHEREF  WHERE STLINE.LINETYPE IN(0, 1, 4, 6, 9) AND  dbo.GetWebOrderDateInt(STFICHE.CAPIBLOCK_CREADEDDATE, STFICHE.CAPIBLOCK_MODIFIEDDATE) >" + lastDate + " GROUP BY STLINE.VARIANTREF  UNION  SELECT ORFLINE.VARIANTREF REF  FROM " + mLogoTigerTableName.ORFICHE + " ORFICHE WITH(NOLOCK)  INNER JOIN " + mLogoTigerTableName.ORFLINE + " ORFLINE WITH(NOLOCK) ON ORFICHE.LOGICALREF = ORFLINE.ORDFICHEREF WHERE ORFLINE.LINETYPE IN(0, 1, 4, 6, 9)  AND dbo.GetWebOrderDateInt(ORFICHE.CAPIBLOCK_CREADEDDATE,ORFICHE.CAPIBLOCK_MODIFIEDDATE) >" + lastDate + " GROUP BY ORFLINE.VARIANTREF  UNION  SELECT V.LOGICALREF REF  FROM WOR_DELETEDRECS DEL WITH(NOLOCK)  INNER JOIN " + mLogoTigerTableName.ITEMS + " I WITH(NOLOCK) ON DEL.LOGICALREF = I.LOGICALREF  INNER JOIN " + mLogoTigerTableName.VARIANT + " V WITH (NOLOCK) ON V.ITEMREF = I.LOGICALREF WHERE FIRMNR = " + Integer.valueOf(getUser().getFirmNr()) + " AND PERIODNR = " + Integer.valueOf(getUser().getPeridodNr()) + " AND RECTYPE = 1 AND dbo.GetWebOrderDateInt(DEL.DELETEDDATE, DEL.DELETEDDATE) >" + lastDate + buildSqlCondition(getItemsFilter(false, false, "I"), false) + " GROUP BY V.LOGICALREF   ) AS TMP_TBL) AS TMP_TBLX ON TMP_TBLX.REF = X.VARIANTREF";
                StringBuilder sb = new StringBuilder();
                sb.append("SELECT RowNum,WAREHOUSENR,WAREHOUSE,ITEMREF,ITEMCODE,VARIANTREF,VARIANTCODE,REALSTOCK,ONHAND FROM (SELECT ROW_NUMBER() OVER(ORDER BY X.INVENNO) As [RowNum],X.INVENNO [WAREHOUSENR], WAREHOUSE,  X.ITEMREF, X.ITEMCODE,X.VARIANTREF,X.VARIANTCODE, (SUMONHAND - SUMRESERVED - SUMTEMPIN + SUMTEMPOUT) REALSTOCK,SUMONHAND ONHAND FROM ( SELECT DISTINCT S.STOCKREF [ITEMREF],I.CODE ITEMCODE, S.INVENNO INVENNO,S.VARIANTREF,V.CODE VARIANTCODE, SUM(ISNULL(S.ONHAND,0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMONHAND, SUM(ISNULL(S.RESERVED, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMRESERVED , SUM(ISNULL(S.TEMPIN, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMTEMPIN, SUM(ISNULL(S.TEMPOUT, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMTEMPOUT, (CONVERT( NVARCHAR(10), W.NR)+', '+W.NAME) AS WAREHOUSE, I.LOGICALREF, I.MARKREF, I.ACTIVE, I.CARDTYPE, I.CODE,I.ISONR,I.SPECODE,I.SPECODE2,I.SPECODE3,I.SPECODE4,I.SPECODE5,I.CANCONFIGURE,I.STGRPCODE FROM ");
                sb.append(mLogoTigerTableName.VRNTINVTOT);
                sb.append(" S WITH(NOLOCK) INNER JOIN ");
                sb.append(mLogoTigerTableName.CAPIWHOUSE);
                sb.append(" W WITH(NOLOCK) ON S.INVENNO = W.NR  AND W.FIRMNR = ");
                sb.append(Integer.valueOf(getUser().getFirmNr()));
                sb.append(" INNER JOIN ");
                sb.append(mLogoTigerTableName.ITEMS);
                sb.append(" I WITH(NOLOCK) ON I.LOGICALREF = S.STOCKREF  AND I.ACTIVE=0 AND I.CARDTYPE NOT IN (20,21,22)  INNER JOIN ");
                sb.append(mLogoTigerTableName.VARIANT);
                sb.append(" V WITH(NOLOCK) ON V.LOGICALREF = S.VARIANTREF WHERE S.INVENNO <>-1 ");
                if (!replace.equals("")) {
                    str2 = " AND S.INVENNO IN (" + replace + ") ";
                }
                sb.append(str2);
                sb.append(" AND (S.DATE_ > CONVERT(DATETIME, '5-19-1919', 101)) AND (S.DATE_ <= ");
                sb.append(ErpCreator.getInstance().getmBaseErp().getStockTotalsWhereDateExpression());
                sb.append(") ) X ");
                sb.append(str);
                return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((sb + buildSqlCondition(getItemsFilter(false, false, "X"), true)) + ") R").withProcessType(ProcessType.GETVARIANTSTOCK).withTableDelete(z).withDatabaseSave(true).build();
            }
            z = true;
        }
        str = "";
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT RowNum,WAREHOUSENR,WAREHOUSE,ITEMREF,ITEMCODE,VARIANTREF,VARIANTCODE,REALSTOCK,ONHAND FROM (SELECT ROW_NUMBER() OVER(ORDER BY X.INVENNO) As [RowNum],X.INVENNO [WAREHOUSENR], WAREHOUSE,  X.ITEMREF, X.ITEMCODE,X.VARIANTREF,X.VARIANTCODE, (SUMONHAND - SUMRESERVED - SUMTEMPIN + SUMTEMPOUT) REALSTOCK,SUMONHAND ONHAND FROM ( SELECT DISTINCT S.STOCKREF [ITEMREF],I.CODE ITEMCODE, S.INVENNO INVENNO,S.VARIANTREF,V.CODE VARIANTCODE, SUM(ISNULL(S.ONHAND,0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMONHAND, SUM(ISNULL(S.RESERVED, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMRESERVED , SUM(ISNULL(S.TEMPIN, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMTEMPIN, SUM(ISNULL(S.TEMPOUT, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMTEMPOUT, (CONVERT( NVARCHAR(10), W.NR)+', '+W.NAME) AS WAREHOUSE, I.LOGICALREF, I.MARKREF, I.ACTIVE, I.CARDTYPE, I.CODE,I.ISONR,I.SPECODE,I.SPECODE2,I.SPECODE3,I.SPECODE4,I.SPECODE5,I.CANCONFIGURE,I.STGRPCODE FROM ");
        sb2.append(mLogoTigerTableName.VRNTINVTOT);
        sb2.append(" S WITH(NOLOCK) INNER JOIN ");
        sb2.append(mLogoTigerTableName.CAPIWHOUSE);
        sb2.append(" W WITH(NOLOCK) ON S.INVENNO = W.NR  AND W.FIRMNR = ");
        sb2.append(Integer.valueOf(getUser().getFirmNr()));
        sb2.append(" INNER JOIN ");
        sb2.append(mLogoTigerTableName.ITEMS);
        sb2.append(" I WITH(NOLOCK) ON I.LOGICALREF = S.STOCKREF  AND I.ACTIVE=0 AND I.CARDTYPE NOT IN (20,21,22)  INNER JOIN ");
        sb2.append(mLogoTigerTableName.VARIANT);
        sb2.append(" V WITH(NOLOCK) ON V.LOGICALREF = S.VARIANTREF WHERE S.INVENNO <>-1 ");
        if (!replace.equals("")) {
        }
        sb2.append(str2);
        sb2.append(" AND (S.DATE_ > CONVERT(DATETIME, '5-19-1919', 101)) AND (S.DATE_ <= ");
        sb2.append(ErpCreator.getInstance().getmBaseErp().getStockTotalsWhereDateExpression());
        sb2.append(") ) X ");
        sb2.append(str);
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((sb2 + buildSqlCondition(getItemsFilter(false, false, "X"), true)) + ") R").withProcessType(ProcessType.GETVARIANTSTOCK).withTableDelete(z).withDatabaseSave(true).build();
    }

    public TigerSelectResult getVariantStockWithFDate(boolean z) {
        String str;
        String replace = getSqlHelper().getParamValue(ParameterTypes.ptGeneralSettings_Warehouse).replace(";", ",");
        String str2 = "";
        if (!z) {
            double lastDate = getSharedPreferencesHelper().getLastDate(false);
            if (lastDate != 0.0d) {
                str = " INNER JOIN (SELECT REF FROM (SELECT STLINE.VARIANTREF REF FROM " + mLogoTigerTableName.STFICHE + " STFICHE  WITH(NOLOCK) INNER JOIN " + mLogoTigerTableName.STLINE + " STLINE WITH(NOLOCK) ON STFICHE.LOGICALREF = STLINE.STFICHEREF  WHERE STLINE.LINETYPE IN(0, 1, 4, 6, 9) AND FDATE >" + lastDate + " GROUP BY STLINE.VARIANTREF  UNION  SELECT ORFLINE.VARIANTREF REF  FROM " + mLogoTigerTableName.ORFICHE + " ORFICHE WITH(NOLOCK)  INNER JOIN " + mLogoTigerTableName.ORFLINE + " ORFLINE WITH(NOLOCK) ON ORFICHE.LOGICALREF = ORFLINE.ORDFICHEREF WHERE ORFLINE.LINETYPE IN(0, 1, 4, 6, 9)  AND FDATE >" + lastDate + " GROUP BY ORFLINE.VARIANTREF  UNION  SELECT V.LOGICALREF REF  FROM WOR_DELETEDRECS DEL WITH(NOLOCK)  INNER JOIN " + mLogoTigerTableName.ITEMS + " I WITH(NOLOCK) ON DEL.LOGICALREF = I.LOGICALREF  INNER JOIN " + mLogoTigerTableName.VARIANT + " V WITH (NOLOCK) ON V.ITEMREF = I.LOGICALREF WHERE FIRMNR = " + Integer.valueOf(getUser().getFirmNr()) + " AND PERIODNR = " + Integer.valueOf(getUser().getPeridodNr()) + " AND RECTYPE = 1 AND DEL.FDATE >" + lastDate + buildSqlCondition(getItemsFilter(false, false, "I"), false) + " GROUP BY V.LOGICALREF   ) AS TMP_TBL) AS TMP_TBLX ON TMP_TBLX.REF = X.VARIANTREF";
                StringBuilder sb = new StringBuilder();
                sb.append("SELECT RowNum,WAREHOUSENR,WAREHOUSE,ITEMREF,ITEMCODE,VARIANTREF,VARIANTCODE,REALSTOCK,ONHAND FROM (SELECT ROW_NUMBER() OVER(ORDER BY X.INVENNO) As [RowNum],X.INVENNO [WAREHOUSENR], WAREHOUSE,  X.ITEMREF, X.ITEMCODE,X.VARIANTREF,X.VARIANTCODE, (SUMONHAND - SUMRESERVED - SUMTEMPIN + SUMTEMPOUT) REALSTOCK,SUMONHAND ONHAND FROM ( SELECT DISTINCT S.STOCKREF [ITEMREF],I.CODE ITEMCODE, S.INVENNO INVENNO,S.VARIANTREF,V.CODE VARIANTCODE, SUM(ISNULL(S.ONHAND,0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMONHAND, SUM(ISNULL(S.RESERVED, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMRESERVED , SUM(ISNULL(S.TEMPIN, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMTEMPIN, SUM(ISNULL(S.TEMPOUT, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMTEMPOUT, (CONVERT( NVARCHAR(10), W.NR)+', '+W.NAME) AS WAREHOUSE, I.LOGICALREF, I.MARKREF, I.ACTIVE, I.CARDTYPE, I.CODE,I.ISONR,I.SPECODE,I.SPECODE2,I.SPECODE3,I.SPECODE4,I.SPECODE5,I.CANCONFIGURE,I.STGRPCODE FROM ");
                sb.append(mLogoTigerTableName.VRNTINVTOT);
                sb.append(" S WITH(NOLOCK) INNER JOIN ");
                sb.append(mLogoTigerTableName.CAPIWHOUSE);
                sb.append(" W WITH(NOLOCK) ON S.INVENNO = W.NR  AND W.FIRMNR = ");
                sb.append(Integer.valueOf(getUser().getFirmNr()));
                sb.append(" INNER JOIN ");
                sb.append(mLogoTigerTableName.ITEMS);
                sb.append(" I WITH(NOLOCK) ON I.LOGICALREF = S.STOCKREF  AND I.ACTIVE=0 AND I.CARDTYPE NOT IN (20,21,22)  INNER JOIN ");
                sb.append(mLogoTigerTableName.VARIANT);
                sb.append(" V WITH(NOLOCK) ON V.LOGICALREF = S.VARIANTREF WHERE S.INVENNO <>-1 ");
                if (!replace.isEmpty()) {
                    str2 = " AND S.INVENNO IN (" + replace + ") ";
                }
                sb.append(str2);
                sb.append(" AND (S.DATE_ > CONVERT(DATETIME, '5-19-1919', 101)) AND (S.DATE_ <= ");
                sb.append(ErpCreator.getInstance().getmBaseErp().getStockTotalsWhereDateExpression());
                sb.append(") ) X ");
                sb.append(str);
                return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((sb + buildSqlCondition(getItemsFilter(false, false, "X"), true)) + ") R").withProcessType(ProcessType.GETVARIANTSTOCK).withTableDelete(z).withDatabaseSave(true).build();
            }
            z = true;
        }
        str = "";
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT RowNum,WAREHOUSENR,WAREHOUSE,ITEMREF,ITEMCODE,VARIANTREF,VARIANTCODE,REALSTOCK,ONHAND FROM (SELECT ROW_NUMBER() OVER(ORDER BY X.INVENNO) As [RowNum],X.INVENNO [WAREHOUSENR], WAREHOUSE,  X.ITEMREF, X.ITEMCODE,X.VARIANTREF,X.VARIANTCODE, (SUMONHAND - SUMRESERVED - SUMTEMPIN + SUMTEMPOUT) REALSTOCK,SUMONHAND ONHAND FROM ( SELECT DISTINCT S.STOCKREF [ITEMREF],I.CODE ITEMCODE, S.INVENNO INVENNO,S.VARIANTREF,V.CODE VARIANTCODE, SUM(ISNULL(S.ONHAND,0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMONHAND, SUM(ISNULL(S.RESERVED, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMRESERVED , SUM(ISNULL(S.TEMPIN, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMTEMPIN, SUM(ISNULL(S.TEMPOUT, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMTEMPOUT, (CONVERT( NVARCHAR(10), W.NR)+', '+W.NAME) AS WAREHOUSE, I.LOGICALREF, I.MARKREF, I.ACTIVE, I.CARDTYPE, I.CODE,I.ISONR,I.SPECODE,I.SPECODE2,I.SPECODE3,I.SPECODE4,I.SPECODE5,I.CANCONFIGURE,I.STGRPCODE FROM ");
        sb2.append(mLogoTigerTableName.VRNTINVTOT);
        sb2.append(" S WITH(NOLOCK) INNER JOIN ");
        sb2.append(mLogoTigerTableName.CAPIWHOUSE);
        sb2.append(" W WITH(NOLOCK) ON S.INVENNO = W.NR  AND W.FIRMNR = ");
        sb2.append(Integer.valueOf(getUser().getFirmNr()));
        sb2.append(" INNER JOIN ");
        sb2.append(mLogoTigerTableName.ITEMS);
        sb2.append(" I WITH(NOLOCK) ON I.LOGICALREF = S.STOCKREF  AND I.ACTIVE=0 AND I.CARDTYPE NOT IN (20,21,22)  INNER JOIN ");
        sb2.append(mLogoTigerTableName.VARIANT);
        sb2.append(" V WITH(NOLOCK) ON V.LOGICALREF = S.VARIANTREF WHERE S.INVENNO <>-1 ");
        if (!replace.isEmpty()) {
        }
        sb2.append(str2);
        sb2.append(" AND (S.DATE_ > CONVERT(DATETIME, '5-19-1919', 101)) AND (S.DATE_ <= ");
        sb2.append(ErpCreator.getInstance().getmBaseErp().getStockTotalsWhereDateExpression());
        sb2.append(") ) X ");
        sb2.append(str);
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((sb2 + buildSqlCondition(getItemsFilter(false, false, "X"), true)) + ") R").withProcessType(ProcessType.GETVARIANTSTOCK).withTableDelete(z).withDatabaseSave(true).build();
    }

    public TigerSelectResult getVariantStockWithFDateWithExecSp(boolean z) {
        String str;
        String replace = getSqlHelper().getParamValue(ParameterTypes.ptGeneralSettings_Warehouse).replace(";", ",");
        String str2 = "";
        if (!z) {
            double lastDate = getSharedPreferencesHelper().getLastDate(false);
            if (lastDate != 0.0d) {
                str = " WITH TMP_TBLX AS (SELECT REF FROM (SELECT STLINE.VARIANTREF REF FROM " + mLogoTigerTableName.STFICHE + " STFICHE  WITH(NOLOCK) INNER JOIN " + mLogoTigerTableName.STLINE + " STLINE WITH(NOLOCK) ON STFICHE.LOGICALREF = STLINE.STFICHEREF  WHERE STLINE.LINETYPE IN(0, 1, 4, 6, 9) AND FDATE >" + lastDate + " GROUP BY STLINE.VARIANTREF  UNION  SELECT ORFLINE.VARIANTREF REF  FROM " + mLogoTigerTableName.ORFICHE + " ORFICHE WITH(NOLOCK)  INNER JOIN " + mLogoTigerTableName.ORFLINE + " ORFLINE WITH(NOLOCK) ON ORFICHE.LOGICALREF = ORFLINE.ORDFICHEREF WHERE ORFLINE.LINETYPE IN(0, 1, 4, 6, 9)  AND FDATE >" + lastDate + " GROUP BY ORFLINE.VARIANTREF  UNION  SELECT V.LOGICALREF REF  FROM WOR_DELETEDRECS DEL WITH(NOLOCK)  INNER JOIN " + mLogoTigerTableName.ITEMS + " I WITH(NOLOCK) ON DEL.LOGICALREF = I.LOGICALREF  INNER JOIN " + mLogoTigerTableName.VARIANT + " V WITH (NOLOCK) ON V.ITEMREF = I.LOGICALREF WHERE FIRMNR = " + Integer.valueOf(getUser().getFirmNr()) + " AND PERIODNR = " + Integer.valueOf(getUser().getPeridodNr()) + " AND RECTYPE = 1 AND DEL.FDATE >" + lastDate + buildSqlCondition(getItemsFilter(false, false, "I"), false) + " GROUP BY V.LOGICALREF   ) AS TMP_TBL) ";
                String str3 = str + "SELECT RowNum,WAREHOUSENR,WAREHOUSE,ITEMREF,ITEMCODE,VARIANTREF,VARIANTCODE,REALSTOCK,ONHAND FROM (SELECT ROW_NUMBER() OVER(ORDER BY X.INVENNO) As [RowNum],X.INVENNO [WAREHOUSENR], WAREHOUSE,  X.ITEMREF, X.ITEMCODE,X.VARIANTREF,X.VARIANTCODE, (SUMONHAND - SUMRESERVED - SUMTEMPIN + SUMTEMPOUT) REALSTOCK,SUMONHAND ONHAND FROM ( SELECT DISTINCT S.STOCKREF [ITEMREF],I.CODE ITEMCODE, S.INVENNO INVENNO,S.VARIANTREF,V.CODE VARIANTCODE, SUM(ISNULL(S.ONHAND,0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMONHAND, SUM(ISNULL(S.RESERVED, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMRESERVED , SUM(ISNULL(S.TEMPIN, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMTEMPIN, SUM(ISNULL(S.TEMPOUT, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMTEMPOUT, (CONVERT( NVARCHAR(10), W.NR)+', '+W.NAME) AS WAREHOUSE, I.LOGICALREF, I.MARKREF, I.ACTIVE, I.CARDTYPE, I.CODE,I.ISONR,I.SPECODE,I.SPECODE2,I.SPECODE3,I.SPECODE4,I.SPECODE5,I.CANCONFIGURE,I.STGRPCODE FROM " + mLogoTigerTableName.VRNTINVTOT + " S WITH(NOLOCK) INNER JOIN " + mLogoTigerTableName.CAPIWHOUSE + " W WITH(NOLOCK) ON S.INVENNO = W.NR  AND W.FIRMNR = " + Integer.valueOf(getUser().getFirmNr()) + " INNER JOIN " + mLogoTigerTableName.ITEMS + " I WITH(NOLOCK) ON I.LOGICALREF = S.STOCKREF  AND I.ACTIVE=0 AND I.CARDTYPE NOT IN (20,21,22)  INNER JOIN " + mLogoTigerTableName.VARIANT + " V WITH(NOLOCK) ON V.LOGICALREF = S.VARIANTREF";
                if (!z) {
                    str3 = str3 + " INNER JOIN TMP_TBLX ON TMP_TBLX.REF = S.VARIANTREF ";
                }
                StringBuilder sb = new StringBuilder();
                sb.append(str3);
                sb.append(" WHERE S.INVENNO <>-1 ");
                if (!replace.isEmpty()) {
                    str2 = " AND S.INVENNO IN (" + replace + ") ";
                }
                sb.append(str2);
                sb.append(" AND (S.DATE_ > CONVERT(DATETIME, '5-19-1919', 101)) AND (S.DATE_ <= ");
                sb.append(ErpCreator.getInstance().getmBaseErp().getStockTotalsWhereDateExpression());
                sb.append(") ) X ");
                return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((sb + buildSqlCondition(getItemsFilter(false, false, "X"), true)) + ") R").withProcessType(ProcessType.GETVARIANTSTOCK).withTableDelete(z).withDatabaseSave(true).build();
            }
            z = true;
        }
        str = "";
        String str32 = str + "SELECT RowNum,WAREHOUSENR,WAREHOUSE,ITEMREF,ITEMCODE,VARIANTREF,VARIANTCODE,REALSTOCK,ONHAND FROM (SELECT ROW_NUMBER() OVER(ORDER BY X.INVENNO) As [RowNum],X.INVENNO [WAREHOUSENR], WAREHOUSE,  X.ITEMREF, X.ITEMCODE,X.VARIANTREF,X.VARIANTCODE, (SUMONHAND - SUMRESERVED - SUMTEMPIN + SUMTEMPOUT) REALSTOCK,SUMONHAND ONHAND FROM ( SELECT DISTINCT S.STOCKREF [ITEMREF],I.CODE ITEMCODE, S.INVENNO INVENNO,S.VARIANTREF,V.CODE VARIANTCODE, SUM(ISNULL(S.ONHAND,0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMONHAND, SUM(ISNULL(S.RESERVED, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMRESERVED , SUM(ISNULL(S.TEMPIN, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMTEMPIN, SUM(ISNULL(S.TEMPOUT, 0)) OVER (PARTITION BY S.VARIANTREF, S.INVENNO) SUMTEMPOUT, (CONVERT( NVARCHAR(10), W.NR)+', '+W.NAME) AS WAREHOUSE, I.LOGICALREF, I.MARKREF, I.ACTIVE, I.CARDTYPE, I.CODE,I.ISONR,I.SPECODE,I.SPECODE2,I.SPECODE3,I.SPECODE4,I.SPECODE5,I.CANCONFIGURE,I.STGRPCODE FROM " + mLogoTigerTableName.VRNTINVTOT + " S WITH(NOLOCK) INNER JOIN " + mLogoTigerTableName.CAPIWHOUSE + " W WITH(NOLOCK) ON S.INVENNO = W.NR  AND W.FIRMNR = " + Integer.valueOf(getUser().getFirmNr()) + " INNER JOIN " + mLogoTigerTableName.ITEMS + " I WITH(NOLOCK) ON I.LOGICALREF = S.STOCKREF  AND I.ACTIVE=0 AND I.CARDTYPE NOT IN (20,21,22)  INNER JOIN " + mLogoTigerTableName.VARIANT + " V WITH(NOLOCK) ON V.LOGICALREF = S.VARIANTREF";
        if (!z) {
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str32);
        sb2.append(" WHERE S.INVENNO <>-1 ");
        if (!replace.isEmpty()) {
        }
        sb2.append(str2);
        sb2.append(" AND (S.DATE_ > CONVERT(DATETIME, '5-19-1919', 101)) AND (S.DATE_ <= ");
        sb2.append(ErpCreator.getInstance().getmBaseErp().getStockTotalsWhereDateExpression());
        sb2.append(") ) X ");
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((sb2 + buildSqlCondition(getItemsFilter(false, false, "X"), true)) + ") R").withProcessType(ProcessType.GETVARIANTSTOCK).withTableDelete(z).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getWareHouse() {
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptGeneralSettings_Warehouse);
        String str = "SELECT * FROM (SELECT L.LOGICALREF [LOGICALREF], L.NR [NR], (CONVERT(NVARCHAR(10), L.NR) + ', '+ L.NAME) [AMBAR], L.DIVISNR [DIVISNR], L.FACTNR [FACTNR], L.COSTGRP [COSTGRP]  ,ROW_NUMBER() OVER (ORDER BY L.LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.CAPIWHOUSE + " L WITH(NOLOCK) WHERE L.FIRMNR=" + getUser().getFirmNr();
        if (!paramValue.equals("")) {
            str = str + " AND L.NR IN (" + paramValue + ")";
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(str + " ) R ").withOrderBy("ORDER BY NR").withProcessType(ProcessType.GETWAREHOUSE).withTableDelete(true).withDatabaseSave(true).build();
    }

    public TigerSelectResult getFirm() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM ( SELECT LOGICALREF, NR, NAME, TITLE, OFFICALTITLE, STREET, ROAD, DOORNR, DISTRICT,CITY, \n       COUNTRY, ZIPCODE, PHONE1,PHONE2, FAX, TAXOFF, TAXNR, WEBADDR, PROFILEID, EMAILADDR, \n       FIRMEMAILADDR,ROW_NUMBER() OVER (ORDER BY L.LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.CAPIFIRM + " L WITH(NOLOCK) WHERE NR='" + USER.firmano + "') R ").withOrderBy("ORDER BY NR").withProcessType(ProcessType.GETFIRM).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getDivisions() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM ( SELECT L.LOGICALREF [LOGICALREF], L.NR [NR], (CONVERT(NVARCHAR(10),L.NR) + ', '+ L.NAME) [BOLUM] ,ROW_NUMBER() OVER (ORDER BY L.LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.CAPIDEPT + " L WITH(NOLOCK) WHERE FIRMNR='" + USER.firmano + "') R ").withOrderBy("ORDER BY NR").withProcessType(ProcessType.GETDIVISION).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getBranches() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(" SELECT * FROM ( SELECT L.LOGICALREF [LOGICALREF], L.NR [NR], L.USEEARCHIVE,L.USEEDESPATCH, L.USEEINV, L.EARCHIVETYPE, (CONVERT(NVARCHAR(10),L.NR) + ', '+ L.NAME) [ISYERI] ,ROW_NUMBER() OVER (ORDER BY L.LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.CAPIDIV + " L WITH(NOLOCK) WHERE L.FIRMNR='" + getUser().getFirmNr() + "') R ").withOrderBy("ORDER BY NR").withProcessType(ProcessType.GETBRANCH).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getFactories() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM ( SELECT L.LOGICALREF [LOGICALREF], L.NR [NR], (CONVERT(NVARCHAR(10),L.NR) + ', '+ L.NAME) [FABRIKA], L.DIVISNR [DIVISNR] ,ROW_NUMBER() OVER (ORDER BY L.LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.CAPIFACTORY + " L WITH(NOLOCK)  WHERE FIRMNR='" + getUser().getFirmNr() + "') R ").withOrderBy("ORDER BY NR").withProcessType(ProcessType.GETFACTORY).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getSpeCodes() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM ( SELECT L.LOGICALREF , L.CODETYPE [CODETYPE], L.SPECODETYPE [SPECODETYPE], L.SPECODE [SPECODE], L.SPETYP1, L.SPETYP2, L.SPETYP3, L.SPETYP4, L.SPETYP5, (L.SPECODE + '- ' + ISNULL(L.DEFINITION_,'')) DEFINITION, ROW_NUMBER() OVER (ORDER BY L.LOGICALREF) AS RowNum  FROM " + mLogoTigerTableName.SPECODES + " L WITH(NOLOCK)  WHERE L.CODETYPE IN (1,2,3) AND L.SPECODETYPE IN (44,27,30,29,40,25,23,21,19,14,16,0,115,2,3,26)) R ").withProcessType(ProcessType.GETSPECODE).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getDiscounts(boolean z) {
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptDiscountsAppliedToTotal);
        String paramValue2 = getSqlHelper().getParamValue(ParameterTypes.ptDiscountsAppliedToLine);
        if (paramValue.isEmpty()) {
            paramValue = "0";
        }
        if (paramValue2.isEmpty()) {
            paramValue2 = "0";
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM ( SELECT LOGICALREF, CODE, (CODE + ', ' + DEFINITION_) [INDIRIM], 1 [TYPE], FORMULA ,ROW_NUMBER() OVER (ORDER BY LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.DECARDS + " WITH(NOLOCK)  WHERE ACTIVE = 0 AND LOGICALREF IN (" + paramValue + ")  UNION ALL SELECT LOGICALREF, CODE, (CODE + ', ' + DEFINITION_) [INDIRIM], 0 [TYPE], FORMULA ,ROW_NUMBER() OVER (ORDER BY LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.DECARDS + " WITH(NOLOCK)  WHERE ACTIVE = 0 AND LOGICALREF IN (" + paramValue2 + ")) R ").withOrderBy("ORDER BY LOGICALREF").withProcessType(ProcessType.GETDISCOUNT).withTableDelete(true).withDatabaseSave(true).build();
    }

    public TigerSelectResult getPayments(boolean z) {
        String str;
        String str2;
        String sb;
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptPaymentPlanCards);
        if (!paramValue.equals("")) {
            String[] split = paramValue.split("\\;");
            if (split.length == 2) {
                str2 = split[0];
                str = split[1];
                FDateField fDateField = FDateField.PAY_PLANS;
                String sb2 = "SELECT * FROM ( SELECT L.LOGICALREF, (L.CODE + ', '+ L.DEFINITION_) [ODEMEPLAN], L.CODE, L.DEFINITION_, L.SPECODE ,ROW_NUMBER() OVER (ORDER BY L.LOGICALREF) AS RowNum, " +
                        checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " L") +
                        " AS CMDATE FROM " +
                        mLogoTigerTableName.PAYPLANS +
                        " L WITH(NOLOCK)  WHERE L.ACTIVE=0";
                sb = sb2;
                if (!str2.equals("") && !str.equals("")) {
                    sb = sb + " AND CODE BETWEEN '" + str2 + "' AND '" + str + "'";
                }
                if (!z) {
                    double maxCMDate = getSqlHelper().getMaxCMDate(Payment.class);
                    if (maxCMDate != 0.0d) {
                        sb = sb + SqlLiteVariable._AND + checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " L") + " > " + maxCMDate;
                    } else {
                        z = false;
                    }
                }
                return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb + ") R ").withOrderBy("ORDER BY DEFINITION_").withProcessType(ProcessType.GETPAYMENT).withTableDelete(z).withDatabaseSave(true).build();
            }
        }
        str = "";
        str2 = str;
        FDateField fDateField2 = FDateField.PAY_PLANS;
        String sb22 = "SELECT * FROM ( SELECT L.LOGICALREF, (L.CODE + ', '+ L.DEFINITION_) [ODEMEPLAN], L.CODE, L.DEFINITION_, L.SPECODE ,ROW_NUMBER() OVER (ORDER BY L.LOGICALREF) AS RowNum, " +
                checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField2), " L") +
                " AS CMDATE FROM " +
                mLogoTigerTableName.PAYPLANS +
                " L WITH(NOLOCK)  WHERE L.ACTIVE=0";
        sb = sb22;
        if (!str2.equals("")) {
            sb = sb + " AND CODE BETWEEN '" + str2 + "' AND '" + str + "'";
        }
        if (!z) {
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb + ") R ").withOrderBy("ORDER BY DEFINITION_").withProcessType(ProcessType.GETPAYMENT).withTableDelete(z).withDatabaseSave(true).build();
    }

    public TigerSelectResult getPaymentLines(boolean z) {
        String str;
        String str2;
        String sb;
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptPaymentPlanCards);
        if (!paramValue.equals("")) {
            String[] split = paramValue.split("\\;");
            if (split.length == 2) {
                str2 = split[0];
                str = split[1];
                FDateField fDateField = FDateField.PAY_PLANS;
                String sb2 = "SELECT * FROM ( SELECT PL.LOGICALREF, PL.PAYPLANREF, PL.PAYMENTTYPE, P.DEFINITION_, PL.FORMULA ,PL.BANKACCREF ,ISNULL(BA.BANKREF,0) AS BANKREF, PL.DAY_ DAY, PL.MOUNTH MONTH, PL.YEAR_ YEAR , ROW_NUMBER() OVER (ORDER BY PL.LOGICALREF) AS RowNum, " +
                        checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " P") +
                        " AS CMDATE FROM " +
                        mLogoTigerTableName.PAYLINES +
                        " PL WITH(NOLOCK)  INNER JOIN " +
                        mLogoTigerTableName.PAYPLANS +
                        " P WITH(NOLOCK) ON PL.PAYPLANREF=P.LOGICALREF  LEFT JOIN " +
                        mLogoTigerTableName.BANKACC +
                        " BA WITH (NOLOCK) ON PL.BANKACCREF = BA.LOGICALREF  WHERE P.ACTIVE=0 ";
                sb = sb2;
                if (!str2.equals("") && !str.equals("")) {
                    sb = sb + " AND P.CODE BETWEEN '" + str2 + "' AND '" + str + "'";
                }
                if (!z) {
                    double maxCMDate = getSqlHelper().getMaxCMDate(PaymentLine.class);
                    if (maxCMDate != 0.0d) {
                        sb = sb + SqlLiteVariable._AND + checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " P") + " > " + maxCMDate;
                    } else {
                        z = false;
                    }
                }
                return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb + ") R ").withOrderBy("ORDER BY DEFINITION_").withProcessType(ProcessType.GETPAYMENTLINE).withTableDelete(z).withDatabaseSave(true).build();
            }
        }
        str = "";
        str2 = str;
        FDateField fDateField2 = FDateField.PAY_PLANS;
        String sb22 = "SELECT * FROM ( SELECT PL.LOGICALREF, PL.PAYPLANREF, PL.PAYMENTTYPE, P.DEFINITION_, PL.FORMULA ,PL.BANKACCREF ,ISNULL(BA.BANKREF,0) AS BANKREF, PL.DAY_ DAY, PL.MOUNTH MONTH, PL.YEAR_ YEAR , ROW_NUMBER() OVER (ORDER BY PL.LOGICALREF) AS RowNum, " +
                checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField2), " P") +
                " AS CMDATE FROM " +
                mLogoTigerTableName.PAYLINES +
                " PL WITH(NOLOCK)  INNER JOIN " +
                mLogoTigerTableName.PAYPLANS +
                " P WITH(NOLOCK) ON PL.PAYPLANREF=P.LOGICALREF  LEFT JOIN " +
                mLogoTigerTableName.BANKACC +
                " BA WITH (NOLOCK) ON PL.BANKACCREF = BA.LOGICALREF  WHERE P.ACTIVE=0 ";
        sb = sb22;
        if (!str2.equals("")) {
            sb = sb + " AND P.CODE BETWEEN '" + str2 + "' AND '" + str + "'";
        }
        if (!z) {
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb + ") R ").withOrderBy("ORDER BY DEFINITION_").withProcessType(ProcessType.GETPAYMENTLINE).withTableDelete(z).withDatabaseSave(true).build();
    }

    public TigerSelectResult getTradingGroup() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM ( SELECT L.LOGICALREF [LOGICALREF], L.GCODE [CODE], L.GATTRIB [GATTRIB] , L.ACTIVE [ACTIVE], ROW_NUMBER() OVER (ORDER BY L.LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.TRADGRP + " L WITH(NOLOCK)) R ").withProcessType(ProcessType.GETTRADINGGROUP).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getShipAddress(boolean z) {
        String trim = getSqlHelper().getParamValue(ParameterTypes.ptARAPType).trim();
        boolean routeNewSystem = ErpCreator.getInstance().getmBaseErp().getRouteNewSystem();
        FDateField fDateField = FDateField.SHIP_INFO;
        String sb2 = "SELECT * FROM ( SELECT  S.LOGICALREF, S.CLIENTREF [CLREF], S.CODE, S.CITY, CONVERT (nvarchar(max), S.ADDR1+' '+ S.ADDR2) [ADDRESS] , S.ADDR1, S.ADDR2, S.DISTRICT, S.LATITUTE, S.LONGITUDE, S.NAME, S.TOWN , S.DEFAULTFLG, ROW_NUMBER() OVER (ORDER BY S.CLIENTREF, S.LOGICALREF) AS RowNum, " +
                checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " S") +
                " AS CMDATE FROM " +
                mLogoTigerTableName.SHIPINFO +
                " S WITH(NOLOCK) INNER JOIN " +
                mLogoTigerTableName.CLCARD +
                " C WITH(NOLOCK) ON S.CLIENTREF = C.LOGICALREF ";
        if (routeNewSystem) {
            if (ErpCreator.getInstance().getmBaseErp().getOffRoadVisit()) {
                sb2 = sb2 + "INNER JOIN WOR_USERCUSTOMERS UC WITH (NOLOCK) ON UC.SHIPMENTREF = S.LOGICALREF AND UC.USERID =" + getUser().getUserId();
            } else {
                sb2 = sb2 + "INNER JOIN WOR_ROUTEPLAN RP WITH(NOLOCK) ON RP.SHIPMENTREF = S.LOGICALREF INNER JOIN WOR_ROUTE R WITH(NOLOCK) ON R.LOGICALREF=RP.ROUTEID AND R.USERID=" + getUser().getUserId() + " AND R.PERIODSTARTDATE <= CAST(GETDATE() AS DATE) ";
            }
        } else if (!TextUtils.isEmpty(trim)) {
            if (trim.equals(BuildConfig.NETSIS_DEMO_PASSWORD)) {
                sb2 = ((sb2 + "INNER JOIN " + mLogoTigerTableName.SLSCLREL + " SC WITH(NOLOCK) ON ") + " C.LOGICALREF = SC.CLIENTREF") + " INNER JOIN " + mLogoTigerTableName.SLSMAN + " SLSMAN WITH(NOLOCK) ON SLSMAN.LOGICALREF = SC.SALESMANREF AND SLSMAN.CODE ='" + getUser().getCode() + "' AND  SLSMAN.FIRMNR =" + getUser().getFirmNr() + " ";
            } else if (trim.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                sb2 = ((sb2 + " INNER JOIN " + mLogoTigerTableName.ROUTETRS + " RT WITH(NOLOCK) ON RT.CLIENTREF = C.LOGICALREF") + " INNER JOIN " + mLogoTigerTableName.ROUTE + " R WITH(NOLOCK) ON R.LOGICALREF = RT.ROUTEREF") + " INNER JOIN " + mLogoTigerTableName.SLSMAN + " SLSMAN WITH(NOLOCK) ON SLSMAN.LOGICALREF = R.SALESMANREF AND SLSMAN.CODE ='" + getUser().getCode() + "' AND  SLSMAN.FIRMNR =" + getUser().getFirmNr() + " ";
            }
        }
        String str = (sb2 + " WHERE  C.ACTIVE=0  AND S.ACTIVE=0 ") + "AND C.CARDTYPE NOT IN (4,22) ";
        if (!routeNewSystem && trim.equals(BuildConfig.NETSIS_DEMO_PASSWORD)) {
            str = str + " AND (SC.BEGDATE<= GETDATE() OR SC.BEGDATE IS NULL) ";
        }
        if (!z) {
            double maxCMDate = getSqlHelper().getMaxCMDate(ShipAddress.class);
            if (maxCMDate != 0.0d) {
                str = str + SqlLiteVariable._AND + checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " S") + " > " + maxCMDate;
            } else {
                z = true;
            }
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((str + buildSqlCondition(getCustomersFilter("C"), false)) + ") R ").withOrderBy("ORDER BY LOGICALREF").withProcessType(ProcessType.GETSHIPADDRESS).withTableDelete(z).withDatabaseSave(true).build();
    }

    public TigerSelectResult getShipType() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM ( SELECT L.LOGICALREF [LOGICALREF] , L.SCODE [CODE], L.SDEF [DEFINITION_] ,ROW_NUMBER() OVER (ORDER BY L.LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.SHPTYPES + " L WITH(NOLOCK) ) R  ").withProcessType(ProcessType.GETSHIPTYPE).withTableDelete(true).withDatabaseSave(true).build();
    }

    public TigerSelectResult getShipAgent() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM ( SELECT L.LOGICALREF [LOGICALREF] , L.CODE [CODE], L.TITLE [TITLE],TAXNR, ROW_NUMBER() OVER (ORDER BY L.LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.SHPAGENT + " L WITH(NOLOCK)) R  ").withProcessType(ProcessType.GETSHIPAGENT).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getBanks(boolean z) {
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptAuxiliaryCode);
        String paramValue2 = getSqlHelper().getParamValue(ParameterTypes.getBanks_AuthorizationCode);
        String paramValue3 = getSqlHelper().getParamValue(ParameterTypes.ptBanks_SQLSentence);
        FDateField fDateField = FDateField.BANKS;
        String sb2 = "SELECT * FROM (SELECT L.LOGICALREF [LOGICALREF], L.DEFINITION_, L.CODE [CODE], ROW_NUMBER() OVER (ORDER BY L.LOGICALREF) AS RowNum, " +
                checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " L") +
                " AS CMDATE FROM " +
                mLogoTigerTableName.BNCARD +
                " L WITH(NOLOCK)  WHERE L.ACTIVE=0";
        if (!paramValue.equals("")) {
            sb2 = sb2 + " AND SPECODE LIKE '" + paramValue + "'";
        }
        if (!paramValue2.equals("")) {
            sb2 = sb2 + " AND CYPHCODE LIKE '" + paramValue2 + "'";
        }
        if (!z) {
            double maxCMDate = getSqlHelper().getMaxCMDate(Bank.class);
            if (maxCMDate != 0.0d) {
                sb2 = sb2 + SqlLiteVariable._AND + checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " L") + " > " + maxCMDate;
            } else {
                z = false;
            }
        }
        if (!paramValue3.equals("")) {
            String sb3 = sb2 +
                    " AND LOGICALREF IN ( " +
                    paramValue3.replace("_XXX_", "_" + getUser().getFirmNr() + "_").replace("_XX_", "_" + getUser().getPeridodNr() + "_") +
                    SqlLiteVariable._CLOSE_BRACKET;
            sb2 = sb3;
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb2 + ") R ").withProcessType(ProcessType.GETBANK).withTableDelete(z).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getBankAccounts(boolean z) {
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptAuxiliaryCode);
        String paramValue2 = getSqlHelper().getParamValue(ParameterTypes.getBanks_AuthorizationCode);
        String paramValue3 = getSqlHelper().getParamValue(ParameterTypes.ptBanks_SQLSentence);
        String paramValue4 = getSqlHelper().getParamValue(ParameterTypes.ptListOfARAP_AuxiliaryCode);
        String paramValue5 = getSqlHelper().getParamValue(ParameterTypes.ptListOfARAP_AuthorizationCode);
        String paramValue6 = getSqlHelper().getParamValue(ParameterTypes.ptListOfARAP_SQLSentence);
        FDateField fDateField = FDateField.BANK_ACCOUNTS;
        String sb2 = "SELECT * FROM (SELECT A.LOGICALREF [LOGICALREF], B.LOGICALREF [BANKREF], A.DEFINITION_, A.CODE [CODE] ,ROW_NUMBER() OVER (ORDER BY A.LOGICALREF) AS RowNum, " +
                checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " A") +
                " AS CMDATE FROM " +
                mLogoTigerTableName.BANKACC +
                " A WITH(NOLOCK) LEFT JOIN  " +
                mLogoTigerTableName.BNCARD +
                " B  WITH(NOLOCK) ON A.BANKREF=B.LOGICALREF  WHERE A.ACTIVE=0 AND B.ACTIVE=0 AND (A.CARDTYPE = 5 OR A.KKUSAGE=1) ";
        if (!paramValue4.equals("")) {
            sb2 = sb2 + " AND A.SPECODE LIKE '" + paramValue4 + "'";
        }
        if (!paramValue5.equals("")) {
            sb2 = sb2 + " AND A.CYPHCODE LIKE '" + paramValue5 + "'";
        }
        if (!z) {
            double maxCMDate = getSqlHelper().getMaxCMDate(BankAccount.class);
            if (maxCMDate != 0.0d) {
                sb2 = sb2 + SqlLiteVariable._AND + checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " A") + " > " + maxCMDate;
            } else {
                z = false;
            }
        }
        if (!paramValue6.equals("")) {
            String sb3 = sb2 +
                    " AND A.LOGICALREF IN ( " +
                    paramValue6.replace("_XXX_", "_" + getUser().getFirmNr() + "_").replace("_XX_", "_" + getUser().getPeridodNr() + "_") +
                    SqlLiteVariable._CLOSE_BRACKET;
            sb2 = sb3;
        }
        if (!paramValue.equals("")) {
            sb2 = sb2 + " AND B.SPECODE LIKE '" + paramValue + "'";
        }
        if (!paramValue2.equals("")) {
            sb2 = sb2 + " AND B.CYPHCODE LIKE '" + paramValue2 + "'";
        }
        if (!paramValue3.equals("")) {
            String sb4 = sb2 +
                    " AND B.LOGICALREF IN ( " +
                    paramValue3.replace("_XXX_", "_" + getUser().getFirmNr() + "_").replace("_XX_", "_" + getUser().getPeridodNr() + "_") +
                    SqlLiteVariable._CLOSE_BRACKET;
            sb2 = sb4;
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb2 + ") R ").withOrderBy("ORDER BY BANKREF").withProcessType(ProcessType.GETBANKACCOUNT).withTableDelete(z).withDatabaseSave(true).build();
    }

    public BaseSelectResult getLogoTradeParam() {
        return TigerSelectResult.newBuilder().withSql((((((((((("SELECT * FROM ( SELECT PARAMNO = CASE CODE WHEN 'SALES_AUTOASGDLVADRORD' THEN '10461' WHEN 'SALES_AUTOASGDLVADRDSP' THEN '10462'    WHEN 'SALES_AUTOASGDLVADRINV' THEN '10463' WHEN 'SALES_DISPORDAMNTCONT' THEN '10106'  WHEN 'SALES_DESPATCHSTAT' THEN '10464'  WHEN 'SALES_INVOICESTAT'  THEN '10465'  WHEN 'FIN_MONDECDIGUNITPRICE'  THEN '10466'  WHEN 'ITEM_CHECKDIVFACTINV'  THEN '10467'  WHEN 'SALES_EARCFIRMDEFSTATUS'  THEN '10468'  WHEN 'FIN_COLLRISKSALORD'  THEN '" + ErpParamType.FIN_COLLRISKSALORD.getmValue() + "'  WHEN 'FIN_COLLRISKSALDSP'  THEN '" + ErpParamType.FIN_COLLRISKSALDSP.getmValue() + "'  WHEN 'FIN_COLLRISKSALINV'  THEN '" + ErpParamType.FIN_COLLRISKSALINV.getmValue() + "'  WHEN 'FIN_COLLRISKBNKTR'  THEN '" + ErpParamType.FIN_COLLRISKBNKTR.getmValue() + "'  WHEN 'FIN_COLLRISKCSHTR'  THEN '" + ErpParamType.FIN_COLLRISKCSHTR.getmValue() + "'  WHEN 'FIN_COLLRISK'  THEN '" + ErpParamType.FIN_COLLRISK.getmValue() + "'  WHEN 'FIN_COLLRISKCLITR'  THEN '" + ErpParamType.FIN_COLLRISKCLITR.getmValue() + "'  WHEN 'SALES_EDITLINESRCIDX'  THEN '" + ErpParamType.SALES_EDITLINESRCIDX.getmValue() + "'  WHEN 'DEMMGMT_EDITLINESRCIDX'  THEN '" + ErpParamType.DEMMGMT_EDITLINESRCIDX.getmValue() + "'  WHEN 'SALES_SINTPAYMENTTYP'  THEN '" + ErpParamType.SALES_SINTPAYMENTTYP.getmValue() + "'  WHEN 'SALES_SPROMPRICECTRL'  THEN '" + ErpParamType.SALES_SPROMPRICECTRL.getmValue() + "'  WHEN 'SALES_UPDPRCCURRFORTR'  THEN '" + ErpParamType.SALES_UPDPRCCURRFORTR.getmValue() + "'  WHEN 'SALES_UPDTRNCURRFORTR'  THEN '" + ErpParamType.SALES_UPDTRNCURRFORTR.getmValue() + "'  WHEN 'SALES_ASSIGNDUEDATE'  THEN '" + ErpParamType.SALES_ASSIGNDUEDATE.getmValue() + "'  WHEN 'DEMMGMT_DEMFICHESTAT'  THEN '" + ErpParamType.DEMMGMT_DEMFICHESTAT.getmValue() + "'  WHEN 'SALES_SALEPURCHPRICEINV'  THEN '" + ErpParamType.SALES_SALEPURCHPRICEINV.getmValue() + "'  WHEN 'SALES_SALEPURCHPRICEDSP'  THEN '" + ErpParamType.SALES_SALEPURCHPRICEDSP.getmValue() + "'  WHEN 'SALES_SALEPURCHPRCCNTRL'  THEN '" + ErpParamType.SALES_SALEPURCHPRCCNTRL.getmValue() + "'  WHEN 'SALES_SALEPURCHPRCDEFINV'  THEN '" + ErpParamType.SALES_SALEPURCHPRCDEFINV.getmValue() + "'  WHEN 'SALES_SALEPURCHPRCDEFDSP'  THEN '" + ErpParamType.SALES_SALEPURCHPRCDEFDSP.getmValue() + "'  WHEN 'SALES_SALEPURCHPRCDEFORD'  THEN '" + ErpParamType.SALES_SALEPURCHPRCDEFORD.getmValue() + "'  WHEN 'SALES_CANTCALCDEDUCT'  THEN '" + ErpParamType.SALES_CANTCALCDEDUCT.getmValue() + "'  WHEN 'SALES_ORDPAYTYPE'  THEN '" + ErpParamType.SALES_ORDPAYTYPE.getmValue() + "'  ELSE  CODE  END, L.VALUE [PARAMVALUE] ,ROW_NUMBER() OVER (ORDER BY LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.FIRMPARAMS + " L WHERE L.VALUE<>''  AND L.MODULENR  IN(11,10,9,1,8)  AND (L.GROUPNR IN (46,106,79,80,56,25,251,104,66,6,383,434,81,12,29,3,143,343,344,345,346,347,141,76))  AND (L.CODE = 'SALES_AUTOASGDLVADRORD' OR L.CODE = 'SALES_AUTOASGDLVADRDSP'  OR L.CODE = 'SALES_AUTOASGDLVADRINV' OR L.CODE = 'SALES_DISPORDAMNTCONT'  OR L.CODE = 'SALES_DESPATCHSTAT' OR L.CODE = 'SALES_INVOICESTAT'  OR L.CODE = 'FIN_MONDECDIGUNITPRICE' OR L.CODE = 'ITEM_CHECKDIVFACTINV' OR L.CODE = 'SALES_EARCFIRMDEFSTATUS' OR L.CODE = 'SALES_EARCFIRMDEFSTATUS' OR L.CODE = 'FIN_COLLRISKSALORD' OR L.CODE = 'FIN_COLLRISKSALDSP' OR L.CODE = 'FIN_COLLRISKSALINV'  OR L.CODE = 'FIN_COLLRISKBNKTR' OR L.CODE = 'FIN_COLLRISKCSHTR'  OR L.CODE = 'FIN_COLLRISK' OR L.CODE = 'FIN_COLLRISKCLITR'  OR L.CODE = 'SALES_EDITLINESRCIDX' OR L.CODE = 'DEMMGMT_EDITLINESRCIDX' OR L.CODE = 'SALES_SINTPAYMENTTYP'  OR L.CODE = 'SALES_SPROMPRICECTRL' OR L.CODE = 'SALES_UPDPRCCURRFORTR' OR L.CODE = 'SALES_UPDTRNCURRFORTR' OR L.CODE = 'SALES_ASSIGNDUEDATE'  OR L.CODE = 'DEMMGMT_DEMFICHESTAT' OR L.CODE = 'SALES_SALEPURCHPRICEINV' OR L.CODE = 'SALES_SALEPURCHPRICEDSP' OR L.CODE = 'SALES_SALEPURCHPRCCNTRL'  OR L.CODE = 'SALES_SALEPURCHPRCDEFINV' OR L.CODE = 'SALES_SALEPURCHPRCDEFDSP' OR L.CODE = 'SALES_SALEPURCHPRCDEFORD'  OR L.CODE = 'SALES_CANTCALCDEDUCT' OR L.CODE = 'SALES_ORDPAYTYPE') AND L.FIRMNR ='" + getUser().getFirmNr() + "'") + " UNION SELECT  'TIGERV' [PARAMNO],CAST(MAJVERSNR AS varchar)+'.'+ CAST(MINVERSNR AS varchar)+'.00.00' [PARAMVALUE] ,ROW_NUMBER() OVER (ORDER BY LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.CAPIFIRM + " WITH(NOLOCK)  WHERE NR=" + getUser().getFirmNr()) + " UNION SELECT  'FEXC' AS PARAMNO, CAST(SEPEXCHTABLE AS nvarchar)  AS PARAMVALUE ,ROW_NUMBER() OVER (ORDER BY LOGICALREF) AS RowNum FROM L_CAPIFIRM WHERE NR=" + getUser().getFirmNr()) + " UNION SELECT  'LOCALCTYP' AS PARAMNO, CAST(LOCALCTYP AS nvarchar)  AS PARAMVALUE ,ROW_NUMBER() OVER (ORDER BY LOGICALREF) AS RowNum  FROM L_CAPIFIRM WHERE NR=" + getUser().getFirmNr()) + " UNION SELECT  'FIRMREPCURR' AS PARAMNO, CAST(FIRMREPCURR AS nvarchar)  AS PARAMVALUE ,ROW_NUMBER() OVER (ORDER BY LOGICALREF) AS RowNum FROM L_CAPIFIRM WHERE NR=" + getUser().getFirmNr()) + " UNION SELECT  'USEEARCHIVE' AS PARAMNO, CAST(USEEARCHIVE AS nvarchar)  AS PARAMVALUE ,ROW_NUMBER() OVER (ORDER BY LOGICALREF) AS RowNum FROM L_CAPIFIRM WHERE NR=" + getUser().getFirmNr()) + " UNION SELECT  'USEEDESPATCH' AS PARAMNO, CAST(USEEDESPATCH AS nvarchar)  AS PARAMVALUE ,ROW_NUMBER() OVER (ORDER BY LOGICALREF) AS RowNum FROM L_CAPIFIRM WHERE NR=" + getUser().getFirmNr()) + " UNION SELECT  'USEEINVOICE' AS PARAMNO, CAST(ACCEPTEINV AS nvarchar)  AS PARAMVALUE ,ROW_NUMBER() OVER (ORDER BY LOGICALREF) AS RowNum FROM L_CAPIFIRM WHERE NR=" + getUser().getFirmNr()) + " UNION SELECT  'EINVCONTTYPE' AS PARAMNO, CAST(EINVCONTTYPE AS nvarchar)  AS PARAMVALUE ,ROW_NUMBER() OVER (ORDER BY LOGICALREF) AS RowNum FROM L_CAPIFIRM WHERE NR=" + getUser().getFirmNr()) + " UNION SELECT 'SITEID' AS PARAMNO, CAST(CASE WHEN F.SITEID = 0 THEN ISNULL((SELECT TOP 1 S.SITEID FROM L_CAPISITE S WHERE S.CURFLG = 1), 0) ELSE F.SITEID END AS nvarchar) AS PARAMVALUE, ROW_NUMBER() OVER (ORDER BY LOGICALREF) AS RowNum FROM L_CAPIFIRM F WHERE F.NR = " + getUser().getFirmNr()) + " ) R ").withProcessType(ProcessType.GETLOGOTRADEPARAMS).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getCurrTypes() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM ( SELECT  L.CURTYPE [CURRTYPE], L.CURCODE [CURRCODE], L.SUBNAME, ROW_NUMBER() OVER (ORDER BY LOGICALREF) AS RowNum FROM  " + mLogoTigerTableName.CURRENCYLIST + " L WITH(NOLOCK) WHERE L.FIRMNR ='" + getUser().getFirmNr() + "') R ").withProcessType(ProcessType.GETCURRTYPE).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getCurrRates() {
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptCurrencyType);
        String logoParamValue = getSqlHelper().getLogoParamValue("FEXC");
        String str = mLogoTigerTableName.DAILYEXCHANGES;
        if (logoParamValue.equals(BuildConfig.NETSIS_DEMO_PASSWORD)) {
            str = mLogoTigerTableName.LG_EXCHANGE;
        }
        if (paramValue.equals("")) {
            paramValue = BuildConfig.NETSIS_DEMO_PASSWORD;
        }
        Calendar.getInstance();
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM (SELECT E.DATE_, C.CURTYPE [CURRTYPE], C.CURCODE [CURRCODE], CAST(RATES" + paramValue + " AS decimal(15,10)) [RATE] ,ROW_NUMBER() OVER (ORDER BY E.LREF) AS RowNum FROM " + str + " E WITH(NOLOCK) , " + mLogoTigerTableName.CURRENCYLIST + " C WITH(NOLOCK) WHERE E.CRTYPE=C.CURTYPE AND  E.DATE_=" + DateAndTimeUtils.getLogoDateInt() + " AND C.FIRMNR='" + getUser().getFirmNr() + "') R ").withProcessType(ProcessType.GETCURRRATE).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getProjects() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM ( SELECT L.LOGICALREF [LOGICALREF],(CONVERT(NVARCHAR(10),L.CODE) + ', '+ L.NAME) [PROJE], L.CODE [CODE] ,ROW_NUMBER() OVER (ORDER BY L.LOGICALREF) AS RowNum FROM  " + mLogoTigerTableName.PROJECT + " L WITH(NOLOCK) WHERE L.ACTIVE=0) R ").withProcessType(ProcessType.GETPROJECT).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getCases() {
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptSafeDeposits);
        String str = "SELECT * FROM ( SELECT L.LOGICALREF, (L.CODE + ', '+ L.NAME) [KASA], L.CODE ,CCURRENCY, CURRATETYPE, FIXEDCURRTYPE, ROW_NUMBER() OVER (ORDER BY LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.KSCARD + " L WITH(NOLOCK)  WHERE L.ACTIVE=0";
        if (!paramValue.equals("")) {
            str = str + " AND L.LOGICALREF IN(" + paramValue + ")";
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(str + ") R ").withProcessType(ProcessType.GETCASE).withTableDelete(true).withDatabaseSave(true).build();
    }

    public TigerSelectResult getItemLastChangesDate() {
        String str;
        if (!FDateUtils.isFDateFieldEnabled(FDateField.DELETED_RECS)) {
            str = "dbo.GetWebOrderDateInt(MAX(DELETEDDATE), MAX(DELETEDDATE))";
        } else {
            str = " MAX(" + getFDateSql(" DEL") + ") ";
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT MAX(LASTTRAN_DATE) AS LASTTRAN_DATE FROM ( SELECT MAX(" + checkFDateColumn(FDateUtils.isFDateFieldEnabled(FDateField.ST_FICHE), "STFICHE") + ") AS LASTTRAN_DATE FROM " + mLogoTigerTableName.STFICHE + " STFICHE  WITH(NOLOCK)  INNER JOIN " + mLogoTigerTableName.STLINE + " STLINE WITH(NOLOCK)  ON STFICHE.LOGICALREF = STLINE.STFICHEREF WHERE STLINE.LINETYPE IN (0,1,4,6,9)  GROUP BY STLINE.STOCKREF UNION ALL  SELECT MAX(" + checkFDateColumn(FDateUtils.isFDateFieldEnabled(FDateField.OR_FICHE), "ORFICHE") + ") AS LASTTRAN_DATE  FROM " + mLogoTigerTableName.ORFICHE + " ORFICHE WITH (NOLOCK)  INNER JOIN " + mLogoTigerTableName.ORFLINE + " ORFLINE WITH (NOLOCK) ON ORFICHE.LOGICALREF = ORFLINE.ORDFICHEREF  WHERE ORFLINE.LINETYPE IN(0, 1, 4, 6, 9)  GROUP BY ORFLINE.STOCKREF  UNION ALL  SELECT " + str + " AS LASTTRAN_DATE FROM WOR_DELETEDRECS DEL WITH (NOLOCK) INNER JOIN " + mLogoTigerTableName.ITEMS + " I WITH (NOLOCK) ON DEL.LOGICALREF = I.LOGICALREF WHERE FIRMNR = " + Integer.valueOf(getUser().getFirmNr()) + " AND PERIODNR = " + Integer.valueOf(getUser().getPeridodNr()) + " AND RECTYPE = 1  GROUP BY DEL.LOGICALREF ) AS TMP_TBL ").withProcessType(ProcessType.GETITEMLASTTRANSFER).withTableDelete(true).withDatabaseSave(true).build();
    }

    public TigerSelectResult getClCardLastChangesDate() {
        String str;
        if (!FDateUtils.isFDateFieldEnabled(FDateField.DELETED_RECS)) {
            str = "dbo.GetWebOrderDateInt(MAX(DELETEDDATE), MAX(DELETEDDATE))";
        } else {
            str = " MAX(" + getFDateSql(" DEL") + ") ";
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT MAX(LASTTRAN_DATE) AS LASTTRAN_DATE FROM ( SELECT  MAX(" + checkFDateColumn(FDateUtils.isFDateFieldEnabled(FDateField.CLF_LINE), "CLFLINE") + ") AS LASTTRAN_DATE  FROM " + mLogoTigerTableName.CLFLINE + " CLFLINE  WITH(NOLOCK)UNION ALL  SELECT " + str + " AS LASTTRAN_DATE FROM WOR_DELETEDRECS DEL WITH (NOLOCK) INNER JOIN " + mLogoTigerTableName.CLCARD + " C WITH (NOLOCK) ON C.LOGICALREF = DEL.LOGICALREF WHERE FIRMNR = " + Integer.valueOf(getUser().getFirmNr()) + " AND PERIODNR = " + Integer.valueOf(getUser().getPeridodNr()) + " AND RECTYPE = 2 AND C.CARDTYPE NOT IN (4,22)  GROUP BY DEL.LOGICALREF ) AS TMP_TBL ").withProcessType(ProcessType.GETCLCARDLASTTRANSFER).withTableDelete(true).withDatabaseSave(true).build();
    }

    private String getItemsLogicalRef() {
        return "SELECT I.LOGICALREF [LOGICALREF] FROM " + mLogoTigerTableName.ITEMS + " I WITH(NOLOCK)   LEFT JOIN " + mLogoTigerTableName.MARK + " M WITH(NOLOCK)  ON M.LOGICALREF=I.MARKREF  LEFT JOIN " + mLogoTigerTableName.STINVTOT + " AS IV WITH(NOLOCK) ON IV.STOCKREF=I.LOGICALREF  AND IV.INVENNO<>-1 AND (IV.DATE_ > CONVERT(dateTime, '5-19-1919', 101)) AND (IV.DATE_ <= " + ErpCreator.getInstance().getmBaseErp().getStockTotalsWhereDateExpression() + ") WHERE I.CARDTYPE<>22 AND I.ACTIVE=0 ";
    }

    private String checkFDateColumn(boolean z, String str) {
        if (z) {
            return getFDateSql(str);
        }
        return getWebOrderDateIntSql(str);
    }

    private String getFDateSql(String str) {
        return str + ".FDATE ";
    }

    private String getWebOrderDateIntSql(String str) {
        return " dbo.GetWebOrderDateInt(" + str + ".CAPIBLOCK_CREADEDDATE, " + str + ".CAPIBLOCK_MODIFIEDDATE) ";
    }

    private String buildSqlCondition(String str, boolean z) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        String sb = (z ? SqlLiteVariable._WHERE : SqlLiteVariable._AND) +
                str;
        return sb;
    }

    private void addFilter(List<String> list, boolean z, String str) {
        if (z) {
            if (list.isEmpty()) {
                list.add(str);
                return;
            }
            list.add(SqlLiteVariable._AND + str);
        }
    }

    private String getItemsFilter(boolean z, boolean z2, String str) {
        String[] strArr;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        boolean z3;
        ArrayList arrayList = new ArrayList();
        String[] split = getSqlHelper().getParamValue(ParameterTypes.ptServiceCards).split("\\;");
        String trim = getSqlHelper().getParamValue(ParameterTypes.ptServiceCards_AuxiliaryCode).trim();
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptServiceCards_SqlSentence);
        if (z2) {
            strArr = null;
            str2 = paramValue;
            str3 = "";
            str4 = str3;
            str5 = str4;
            str6 = str5;
            str7 = str6;
        } else {
            split = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards).split("\\;");
            trim = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_AuxiliaryCode).trim();
            str3 = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_AuxiliaryCode2).trim();
            str4 = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_AuxiliaryCode3).trim();
            str5 = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_AuxiliaryCode4).trim();
            str6 = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_AuxiliaryCode5).trim();
            str7 = getSqlHelper().getParamValue(ParameterTypes.ptGroupCode).trim();
            strArr = getSqlHelper().getParamValue(ParameterTypes.ptIsoNr).split("\\;");
            str2 = getSqlHelper().getParamValue(ParameterTypes.ptMaterialCards_SqlSentence);
        }
        String str11 = "";
        if (split == null || split.length != 2) {
            str8 = str7;
            str9 = str2;
            str10 = str11;
        } else {
            str10 = split[0];
            String str12 = split[1];
            if (str10.isEmpty() || str12.isEmpty()) {
                str9 = str2;
                z3 = false;
            } else {
                str9 = str2;
                z3 = true;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            str8 = str7;
            sb.append(".CODE BETWEEN '");
            sb.append(str10);
            sb.append("' AND '");
            sb.append(str12);
            sb.append("' ");
            addFilter(arrayList, z3, sb.toString());
            str11 = str12;
        }
        if (strArr != null && strArr.length == 2) {
            String str13 = strArr[0];
            String str14 = strArr[1];
            addFilter(arrayList, !str10.isEmpty() && !str11.isEmpty(), str + ".ISONR BETWEEN '" + str13 + "' AND '" + str14 + "' ");
        }
        addFilter(arrayList, !trim.isEmpty(), str + ".SPECODE='" + trim + "'");
        addFilter(arrayList, !str3.isEmpty(), str + ".SPECODE2='" + str3 + "'");
        addFilter(arrayList, !str4.isEmpty(), str + ".SPECODE3='" + str4 + "'");
        addFilter(arrayList, !str5.isEmpty(), str + ".SPECODE4='" + str5 + "'");
        addFilter(arrayList, !str6.isEmpty(), str + ".SPECODE5='" + str6 + "'");
        addFilter(arrayList, !str8.isEmpty(), str + ".STGRPCODE='" + str8 + "'");
        boolean isEmpty = !str9.trim().isEmpty();
        String sb2 = str +
                ".LOGICALREF IN ( " +
                str9.replace("_XXX_", "_" + getUser().getFirmNr() + "_").replace("_XX_", "_" + getUser().getPeridodNr() + "_") +
                SqlLiteVariable._CLOSE_BRACKET;
        addFilter(arrayList, isEmpty, sb2);
        addFilter(arrayList, z, str + ".CANCONFIGURE=1 ");
        return String.join(" ", arrayList);
    }

    public TigerSelectResult getSalesOrderValidationList(String[] strArr, int i2, int i3, String str) {
        String str2;
        try {
            String formatStringEnglish = ContextUtils.formatStringEnglish(R.string.get_qry_where_paging_str, Integer.valueOf(i2), Integer.valueOf(i3));
            if (!TextUtils.isEmpty(str)) {
                str = String.format(ContextUtils.getStringResource(R.string.online_get_order_list_filter_qry), str, str.toLowerCase(), str.toUpperCase());
            }
            String str3 = str;
            String stringResource = ContextUtils.getStringResource(R.string.online_get_order_list_qry);
            LogoTigerTableName logoTigerTableName = mLogoTigerTableName;
            String str4 = logoTigerTableName.ORFICHE;
            String str5 = logoTigerTableName.CLCARD;
            str2 = String.format(stringResource, str4, str5, logoTigerTableName.SLSMAN, str5, logoTigerTableName.SHIPINFO, logoTigerTableName.PAYPLANS, getSalesmansFilterInSql(0), str3, formatStringEnglish, buildSqlCondition(getCustomersFilter("C"), false));
        } catch (Exception e2) {
            Log.e(TAG, "getSalesOrder: ", e2);
            str2 = "";
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(str2).withProcessType(ProcessType.GETSALESORDER).withTableDelete(false).withDatabaseSave(false).build();
    }

    
    public TigerSelectResult getSalesMansList() {
        String[] split;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT SL.LOGICALREF AS LOGICALREF, SL.CODE AS CODE, SL.DEFINITION_  AS DEFINITION_ FROM " + mLogoTigerTableName.SLSMAN + "  SL WITH(NOLOCK) LEFT JOIN WOR_USERS U WITH(NOLOCK) ON SL.CODE=U.CODE WHERE SL.ACTIVE=0 AND SL.FIRMNR=" + USER.firmano);
        String[] salesManagerSalesmanFilter = ErpCreator.getInstance().getmBaseErp().getSalesManagerSalesmanFilter();
        if (!TextUtils.isEmpty(salesManagerSalesmanFilter[0]) && !TextUtils.isEmpty(salesManagerSalesmanFilter[1])) {
            sb.append(" AND SL.CODE BETWEEN '" + salesManagerSalesmanFilter[0] + "' AND '" + salesManagerSalesmanFilter[1] + "' ");
        }
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptSalesManagerSalespersonUserSpeCode);
        if (paramValue != null && !paramValue.isEmpty() && (split = paramValue.split("\\;")) != null && split.length > 0) {
            sb.append(" AND U.SPECODE IN (");
            for (String str : split) {
                if (!str.isEmpty()) {
                    sb.append("'");
                    sb.append(str);
                    sb.append("',");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb.toString()).withProcessType(ProcessType.GETSALESMANS).withOrderBy("ORDER BY CODE").withDatabaseSave(false).build();
    }

    
    public TigerSelectResult getDistributionList(int i2) {
        String format;
        if (i2 == -9999) {
            String stringResource = ContextUtils.getStringResource(R.string.online_get_distribution_from_menu);
            LogoTigerTableName logoTigerTableName = mLogoTigerTableName;
            format = String.format(stringResource, logoTigerTableName.DISTORD, logoTigerTableName.DISTORDLINE, logoTigerTableName.CLCARD, logoTigerTableName.DISTVEHICLE, Integer.valueOf(USER.vehicleRef));
        } else {
            String stringResource2 = ContextUtils.getStringResource(R.string.online_get_distribution_from_fiche);
            LogoTigerTableName logoTigerTableName2 = mLogoTigerTableName;
            format = String.format(stringResource2, logoTigerTableName2.DISTORD, logoTigerTableName2.DISTORDLINE, logoTigerTableName2.ITEMS, logoTigerTableName2.VARIANT, logoTigerTableName2.ITMUNITA, logoTigerTableName2.CLCARD, logoTigerTableName2.DISTVEHICLE, logoTigerTableName2.SLSMAN, logoTigerTableName2.ORFLINE, logoTigerTableName2.ORFICHE, logoTigerTableName2.SHIPINFO, Integer.valueOf(USER.vehicleRef), Integer.valueOf(i2));
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(format).withProcessType(ProcessType.GETDISTRIBUTION).withOrderBy("ORDER BY GODATE DESC").withDatabaseSave(false).build();
    }

    
    public TigerSelectResult getSelectedDistributions(int i2, int i3) {
        String sb = "SELECT DISTINCT DISTORDLINE.LOGICALREF, DISTORDLINE.SHIPAMOUNT, DISTORDLINE.REMAMOUNT, DISTORDLINE.DISTORDERREF, DISTORDLINE.ITEMREF, DISTORDLINE.ORDFICHEREF, DISTORDLINE.ORDLINEREF, DISTORDLINE.CLIENTREF, DISTORDLINE.SALESMAN, DISTORDLINE.DATE_, DISTORDLINE.DUEDATE, DISTORDLINE.UOMREF, DISTORDLINE.BRANCH, DISTORDLINE.FACTORY, DISTORDLINE.SOURCEINDEX, DISTORDLINE.CAMPAIGNREFS1, DISTORDLINE.CAMPAIGNREFS2,DISTORDLINE.CAMPAIGNREFS3, DISTORDLINE.CAMPAIGNREFS4, DISTORDLINE.CAMPAIGNREFS5, DISTORDLINE.POINTCAMPREF, DISTORDLINE.DORDSTATUS, DISTORDLINE.VARIANTREF " +
                "FROM " + mLogoTigerTableName.DISTORD + " DISTORD WITH (NOLOCK)" +
                "LEFT OUTER JOIN " + mLogoTigerTableName.DISTORDLINE + " DISTORDLINE WITH (NOLOCK) ON(DISTORDLINE.DISTORDERREF = DISTORD.LOGICALREF)" +
                "LEFT OUTER JOIN " + mLogoTigerTableName.ITEMS + " ITMSC WITH (NOLOCK) ON(DISTORDLINE.ITEMREF = ITMSC.LOGICALREF) AND ((ITMSC.ACTIVE = 0) AND (ITMSC.LOGICALREF <> 0))" +
                "LEFT OUTER JOIN " + mLogoTigerTableName.VARIANT + " VARIANT WITH (NOLOCK) ON (ISNULL(DISTORDLINE.VARIANTREF, 0) = ISNULL(VARIANT.LOGICALREF, 0)) AND ((ISNULL(VARIANT.ACTIVE, 0) = 0) AND (ISNULL(VARIANT.LOGICALREF, 0) <> 0))" +
                "LEFT OUTER JOIN " + mLogoTigerTableName.ITMUNITA + " ITMUASGN WITH (NOLOCK) ON(DISTORDLINE.UOMREF = ITMUASGN.UNITLINEREF) AND (ITMUASGN.LOGICALREF <> 0)" +
                "WHERE (ITMSC.CARDTYPE <> 4) AND (DISTORDLINE.DISTORDERREF = " + i3 + ") AND (DISTORDLINE.REMAMOUNT > 0) AND (DISTORDLINE.LINETYPE IN(0, 1, 6, 9)) AND (DISTORDLINE.CLIENTREF = " + i2 + ") AND (DISTORDLINE.DORDSTATUS = 1)";
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb).withProcessType(ProcessType.GETDISTRIBUTION).withDatabaseSave(false).build();
    }

    
    public TigerSelectResult getSalesOrderList(SalesType salesType, int i2, boolean z, ArrayList arrayList) {
        StringBuilder sb = new StringBuilder();
        sb.append(SqlLiteVariable._SELECT);
        if (!z) {
            sb.append("DISTINCT ");
        }
        sb.append("O.LOGICALREF, O.FICHENO, O.DATE_ AS DATE, O.TIME_ AS TIME, C.CODE AS CCODE, C.DEFINITION_ AS CNAME, S.CODE AS SCODE, S.DEFINITION_ AS SNAME ");
        if (z) {
            sb.append(",ITM.CODE AS STOCKCODE ,ITM.NAME AS STOCKNAME ,(L.AMOUNT - (L.SHIPPEDAMOUNT + L.ONVEHICLE + L.DISTRESERVED)) AS AVAILABLEAMOUNT ,L.LOGICALREF AS ORDERDETAILREF ");
        }
        sb.append("FROM " + mLogoTigerTableName.ORFICHE + " O WITH (NOLOCK) LEFT OUTER JOIN " + mLogoTigerTableName.CLCARD + " C WITH (NOLOCK) ON C.LOGICALREF = O.CLIENTREF LEFT OUTER JOIN LG_SLSMAN S WITH (NOLOCK) ON S.LOGICALREF = O.SALESMANREF AND S.FIRMNR = " + StringUtils.convertStringToInt(getUser().getFirmNr()) + " LEFT OUTER JOIN WOR_USERS U WITH (NOLOCK) ON S.CODE = U.CODE LEFT OUTER JOIN " + mLogoTigerTableName.ORFLINE + " L WITH (NOLOCK) ON L.ORDFICHEREF = O.LOGICALREF ");
        if (z) {
            sb.append("LEFT OUTER JOIN " + mLogoTigerTableName.ITEMS + " ITM WITH (NOLOCK) ON ITM.LOGICALREF = L.STOCKREF ");
        }
        sb.append("WHERE O.TRCODE = 1 AND L.SOURCEINDEX IN (" + getSqlHelper().getParamValue(ParameterTypes.ptGeneralSettings_Warehouse) + ") AND O.STATUS = 4 AND O.CLIENTREF = " + i2 + " AND L.AMOUNT > L.SHIPPEDAMOUNT + L.ONVEHICLE + L.DISTRESERVED ");
        if (arrayList != null && arrayList.size() > 0) {
            if (z) {
                sb.append("AND L.LOGICALREF NOT IN ( " + TextUtils.join(",", arrayList) + SqlLiteVariable._CLOSE_BRACKET);
            } else {
                sb.append("AND (L.ORDFICHEREF NOT IN (SELECT ORDFICHEREF FROM " + mLogoTigerTableName.ORFLINE + " WHERE LOGICALREF IN ( " + TextUtils.join(",", arrayList) + " )))");
            }
        }
        String orderShipmentType = ErpCreator.getInstance().getmBaseErp().getOrderShipmentType(salesType);
        if (orderShipmentType.trim().length() > 0 && orderShipmentType.equals(BuildConfig.NETSIS_DEMO_PASSWORD)) {
            sb.append("AND S.CODE = '" + getUser().getCode() + "' ");
        }
        if (getSqlHelper().getParamValue(ParameterTypes.ptFilterOrdersByUserSpecode).equals(BuildConfig.NETSIS_DEMO_PASSWORD)) {
            sb.append(" AND U.SPECODE='" + getUser().getSpeCode() + "'");
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb.toString()).withProcessType(ProcessType.GETSALESORDER).withOrderBy("ORDER BY DATE DESC").withDatabaseSave(false).build();
    }

    public TigerSelectResult getOrderAvailableAmounts2(ArrayList<String> arrayList) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT O.LOGICALREF AS ORDERLOGICALREF, L.LOGICALREF AS ORDERLINELOGICALREF, L.AMOUNT AS ORDERAMOUNT, (L.AMOUNT - (L.SHIPPEDAMOUNT + L.ONVEHICLE + L.DISTRESERVED)) AS AVAILABLEAMOUNT FROM " + mLogoTigerTableName.ORFICHE + " O WITH (NOLOCK) LEFT OUTER JOIN " + mLogoTigerTableName.ORFLINE + " L WITH (NOLOCK) ON L.ORDFICHEREF = O.LOGICALREF WHERE O.LOGICALREF IN (" + TextUtils.join(",", arrayList) + ") AND L.AMOUNT > L.SHIPPEDAMOUNT + L.ONVEHICLE + L.DISTRESERVED  ").withProcessType(ProcessType.GETORDERAVAILABLEAMOUNT).withDatabaseSave(false).build();
    }
    public TigerSelectResult getOrderAvailableAmountsFromDetailRef(int i2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT O.LOGICALREF AS ORDERLOGICALREF, L.LOGICALREF AS ORDERLINELOGICALREF, L.AMOUNT AS ORDERAMOUNT, (L.AMOUNT - (L.SHIPPEDAMOUNT + L.ONVEHICLE + L.DISTRESERVED)) AS AVAILABLEAMOUNT FROM " + mLogoTigerTableName.ORFICHE + " O WITH (NOLOCK) LEFT OUTER JOIN " + mLogoTigerTableName.ORFLINE + " L WITH (NOLOCK) ON L.ORDFICHEREF = O.LOGICALREF WHERE O.LOGICALREF = (SELECT ORDFICHEREF FROM " + mLogoTigerTableName.ORFLINE + " WHERE LOGICALREF=" + i2 + ") AND L.AMOUNT > L.SHIPPEDAMOUNT + L.ONVEHICLE + L.DISTRESERVED ").withProcessType(ProcessType.GETORDERAVAILABLEAMOUNT).withDatabaseSave(false).build();
    }

    
    public TigerSelectResult getOrderAvailableAmountsFromDetailWithRefs(ArrayList arrayList) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT O.LOGICALREF AS ORDERLOGICALREF, L.LOGICALREF AS ORDERLINELOGICALREF, L.AMOUNT AS ORDERAMOUNT, (L.AMOUNT - (L.SHIPPEDAMOUNT + L.ONVEHICLE + L.DISTRESERVED)) AS AVAILABLEAMOUNT FROM " + mLogoTigerTableName.ORFICHE + " O WITH (NOLOCK) LEFT OUTER JOIN " + mLogoTigerTableName.ORFLINE + " L WITH (NOLOCK) ON L.ORDFICHEREF = O.LOGICALREF WHERE L.LOGICALREF IN (" + TextUtils.join(",", arrayList) + ") AND L.AMOUNT > L.SHIPPEDAMOUNT + L.ONVEHICLE + L.DISTRESERVED ").withProcessType(ProcessType.GETORDERAVAILABLEAMOUNT).withDatabaseSave(false).build();
    }

    public TigerSelectResult updateSalesOrderStatus(List<Integer> list, OrderStatus orderStatus) {
        String str;
        try {
            String catIntegerSpecial = StringUtils.catIntegerSpecial(",", list);
            str = String.format("%s ; %s ; ", ContextUtils.formatStringEnglish(R.string.online_update_order_list_qry, mLogoTigerTableName.ORFICHE, Integer.valueOf(orderStatus.getmStatus()), catIntegerSpecial), ContextUtils.formatStringEnglish(R.string.online_update_order_list_detail_qry, mLogoTigerTableName.ORFLINE, Integer.valueOf(orderStatus.getmStatus()), catIntegerSpecial));
        } catch (Exception e2) {
            Log.e(TAG, "updateSalesOrderStatus: ", e2);
            str = "";
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(str).withProcessType(ProcessType.UPDATESALESORDER).build();
    }

    public TigerSelectResult getSalesOrderDetails(int i2) {
        String str;
        try {
            Context context = ContextUtils.getmContext();
            LogoTigerTableName logoTigerTableName = mLogoTigerTableName;
            str = context.getString(R.string.online_get_order_detail_qry, logoTigerTableName.ORFLINE, logoTigerTableName.ITEMS, logoTigerTableName.DECARDS, logoTigerTableName.PAYPLANS, logoTigerTableName.UNITSETL, logoTigerTableName.ORFICHE, Integer.valueOf(i2));
        } catch (Exception e2) {
            Log.e(TAG, "getSalesOrderDetails: ", e2);
            str = "";
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(str).withOrderBy(ContextUtils.getmContext().getString(R.string.order_by, ContextUtils.getStringResource(R.string.column_lineno_))).withProcessType(ProcessType.GETSALESORDERDETAIL).withTableDelete(false).withDatabaseSave(false).build();
    }

    public BaseSelectResult getCustomerCampaignPoint(int i2) {
        return TigerSelectResult.newBuilder().withSql("SELECT CODE, NAME, SUM(AMOUNT) AS AMOUNT,SUM(CAMPPOINT) AS CAMPPOINT FROM (  SELECT STRNS.AMOUNT, STRNS.CAMPPOINT, CAMPCARD.CODE, CAMPCARD.NAME  FROM " + mLogoTigerTableName.STLINE + " STRNS WITH(NOLOCK)  LEFT OUTER JOIN " + mLogoTigerTableName.STFICHE + " STFIC WITH(NOLOCK) ON (STRNS.STFICHEREF = STFIC.LOGICALREF)  LEFT OUTER JOIN " + mLogoTigerTableName.CLCARD + " CLNTC WITH(NOLOCK) ON (STFIC.CLIENTREF = CLNTC.LOGICALREF)  LEFT OUTER JOIN " + mLogoTigerTableName.CAMPAIGN + " CAMPCARD WITH(NOLOCK) ON (STRNS.POINTCAMPREF = CAMPCARD.LOGICALREF)  LEFT OUTER JOIN " + mLogoTigerTableName.CMPGNLINE + " CAMPLINE WITH(NOLOCK) ON (CAMPCARD.LOGICALREF = CAMPLINE.CAMPCARDREF AND STRNS.CMPGLINEREF = CAMPLINE.LOGICALREF ) WHERE (STRNS.CANCELLED = 0)  AND (CLNTC.LOGICALREF =" + i2 + ")  AND (CAMPCARD.LOGICALREF <> 0)  AND (CAMPLINE.LINETYPE IN( 4,5))  AND STFIC.TRCODE IN(2,3,4,7,8,9,35,36,37,38,39) UNION ALL  SELECT STRNS.AMOUNT, STRNS.CAMPPOINT, CAMPCARD.CODE, CAMPCARD.NAME  FROM " + mLogoTigerTableName.STLINE + " STRNS WITH(NOLOCK)  LEFT OUTER JOIN " + mLogoTigerTableName.STFICHE + " STFIC WITH(NOLOCK) ON (STRNS.STFICHEREF = STFIC.LOGICALREF)  LEFT OUTER JOIN " + mLogoTigerTableName.CLCARD + " CLNTC WITH(NOLOCK) ON (STFIC.CLIENTREF = CLNTC.LOGICALREF)  LEFT OUTER JOIN " + mLogoTigerTableName.CAMPAIGN + " CAMPCARD WITH(NOLOCK) ON (STRNS.POINTCAMPREF = CAMPCARD.LOGICALREF)  LEFT OUTER JOIN " + mLogoTigerTableName.CMPGNLINE + " CAMPLINE WITH(NOLOCK) ON (CAMPCARD.LOGICALREF = CAMPLINE.CAMPCARDREF AND STRNS.CMPGLINEREFS1 = CAMPLINE.LOGICALREF ) WHERE (STRNS.CANCELLED = 0)  AND (CLNTC.LOGICALREF =" + i2 + ")  AND (CAMPCARD.LOGICALREF <> 0)  AND (CAMPLINE.LINETYPE IN( 4,5))  AND STFIC.TRCODE IN(2,3,4,7,8,9,35,36,37,38,39) UNION ALL  SELECT STRNS.AMOUNT, STRNS.CAMPPOINT, CAMPCARD.CODE, CAMPCARD.NAME  FROM " + mLogoTigerTableName.STLINE + " STRNS WITH(NOLOCK)  LEFT OUTER JOIN " + mLogoTigerTableName.STFICHE + " STFIC WITH(NOLOCK) ON (STRNS.STFICHEREF = STFIC.LOGICALREF)  LEFT OUTER JOIN " + mLogoTigerTableName.CLCARD + " CLNTC WITH(NOLOCK) ON (STFIC.CLIENTREF = CLNTC.LOGICALREF)  LEFT OUTER JOIN " + mLogoTigerTableName.CAMPAIGN + " CAMPCARD WITH(NOLOCK) ON (STRNS.POINTCAMPREF = CAMPCARD.LOGICALREF)  LEFT OUTER JOIN " + mLogoTigerTableName.CMPGNLINE + " CAMPLINE WITH(NOLOCK) ON (CAMPCARD.LOGICALREF = CAMPLINE.CAMPCARDREF AND STRNS.CMPGLINEREFS2 = CAMPLINE.LOGICALREF ) WHERE (STRNS.CANCELLED = 0)  AND (CLNTC.LOGICALREF =" + i2 + ")  AND (CAMPCARD.LOGICALREF <> 0)  AND (CAMPLINE.LINETYPE IN( 4,5))  AND STFIC.TRCODE IN(2,3,4,7,8,9,35,36,37,38,39) UNION ALL  SELECT STRNS.AMOUNT, STRNS.CAMPPOINT, CAMPCARD.CODE, CAMPCARD.NAME  FROM " + mLogoTigerTableName.STLINE + " STRNS WITH(NOLOCK)  LEFT OUTER JOIN " + mLogoTigerTableName.STFICHE + " STFIC WITH(NOLOCK) ON (STRNS.STFICHEREF = STFIC.LOGICALREF)  LEFT OUTER JOIN " + mLogoTigerTableName.CLCARD + " CLNTC WITH(NOLOCK) ON (STFIC.CLIENTREF = CLNTC.LOGICALREF)  LEFT OUTER JOIN " + mLogoTigerTableName.CAMPAIGN + " CAMPCARD WITH(NOLOCK) ON (STRNS.POINTCAMPREF = CAMPCARD.LOGICALREF)  LEFT OUTER JOIN " + mLogoTigerTableName.CMPGNLINE + " CAMPLINE WITH(NOLOCK) ON (CAMPCARD.LOGICALREF = CAMPLINE.CAMPCARDREF AND STRNS.CMPGLINEREFS3 = CAMPLINE.LOGICALREF ) WHERE (STRNS.CANCELLED = 0)  AND (CLNTC.LOGICALREF =" + i2 + ")  AND (CAMPCARD.LOGICALREF <> 0)  AND (CAMPLINE.LINETYPE IN( 4,5))  AND STFIC.TRCODE IN(2,3,4,7,8,9,35,36,37,38,39) UNION ALL  SELECT STRNS.AMOUNT, STRNS.CAMPPOINT, CAMPCARD.CODE, CAMPCARD.NAME  FROM " + mLogoTigerTableName.STLINE + " STRNS WITH(NOLOCK)  LEFT OUTER JOIN " + mLogoTigerTableName.STFICHE + " STFIC WITH(NOLOCK) ON (STRNS.STFICHEREF = STFIC.LOGICALREF)  LEFT OUTER JOIN " + mLogoTigerTableName.CLCARD + " CLNTC WITH(NOLOCK) ON (STFIC.CLIENTREF = CLNTC.LOGICALREF)  LEFT OUTER JOIN " + mLogoTigerTableName.CAMPAIGN + " CAMPCARD WITH(NOLOCK) ON (STRNS.POINTCAMPREF = CAMPCARD.LOGICALREF)  LEFT OUTER JOIN " + mLogoTigerTableName.CMPGNLINE + " CAMPLINE WITH(NOLOCK) ON (CAMPCARD.LOGICALREF = CAMPLINE.CAMPCARDREF AND STRNS.CMPGLINEREFS4 = CAMPLINE.LOGICALREF ) WHERE (STRNS.CANCELLED = 0)  AND (CLNTC.LOGICALREF =" + i2 + ")  AND (CAMPCARD.LOGICALREF <> 0)  AND (CAMPLINE.LINETYPE IN( 4,5))  AND STFIC.TRCODE IN(2,3,4,7,8,9,35,36,37,38,39)) AS TMP_TBL GROUP BY CODE, NAME ").withOrderBy("ORDER BY CODE").withProcessType(ProcessType.GETCUSTOMERCAMPAIGN).withTableDelete(false).withDatabaseSave(false).build();
    }

    public TigerSelectResult getCustomerLastBalance(int i2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT 'BALANCE' as TYP, SUM (-1*GNCLTOT.DEBIT) + SUM (GNCLTOT.CREDIT) AS TOTAL FROM " + mLogoTigerTableName.GNTOTCLV + " GNCLTOT WITH(NOLOCK) WHERE (GNCLTOT.CARDREF =" + i2 + ") AND (GNCLTOT.TOTTYP = 1) UNION ALL SELECT 'INVOICE' AS TYP, SUM((STRNS.LINENET + STRNS.VATAMNT) *  (CASE WHEN STRNS.IOCODE IN(4,8) THEN -1 ELSE 1 END)) AS TOTAT FROM " + mLogoTigerTableName.STLINE + " STRNS WITH(NOLOCK) LEFT OUTER JOIN " + mLogoTigerTableName.STFICHE + " STFIC WITH(NOLOCK) ON (STRNS.STFICHEREF =  STFIC.LOGICALREF) WHERE (STRNS.CLIENTREF =" + i2 + ") AND (STRNS.BILLED = 0) AND (STRNS.CANCELLED = 0) AND (STRNS.STATUS = 0) AND (STRNS.STFICHEREF <> 0) AND (STRNS.TRCODE NOT IN (4,10)) UNION ALL SELECT 'ORDER' as TYP, (ORDER_TOTAL - ORDER_SHIPPED_TOTAL) * - 1 AS TOTAL FROM ( SELECT SUM( (OTRNS.LINENET + OTRNS.VATAMNT) * (CASE WHEN OTRNS.TRCODE = 1 THEN 1 ELSE -1 END)) AS ORDER_TOTAL,  SUM(CASE WHEN (OTRNS.LINENET + OTRNS.VATAMNT > 0 AND OTRNS.SHIPPEDAMOUNT > 0) THEN ( ((OTRNS.LINENET + OTRNS.VATAMNT)/OTRNS.AMOUNT*OTRNS.SHIPPEDAMOUNT) * (CASE WHEN OTRNS.TRCODE = 1 THEN 1 ELSE -1 END)) ELSE 0 END) AS ORDER_SHIPPED_TOTAL FROM " + mLogoTigerTableName.ORFLINE + " OTRNS WITH(NOLOCK) LEFT OUTER JOIN " + mLogoTigerTableName.ORFICHE + " ORFIC WITH(NOLOCK) ON (OTRNS.ORDFICHEREF = ORFIC.LOGICALREF) WHERE (OTRNS.CLIENTREF =" + i2 + " ) AND (OTRNS.ORDFICHEREF <> 0) AND (OTRNS.CLOSED = 0) AND (OTRNS.STATUS IN (1,4)) AND (ORFIC.WITHPAYTRANS = 0) AND OTRNS.LINETYPE=0) AS TMP").withProcessType(ProcessType.GETCUSTOMERBALANCE).withTableDelete(false).withDatabaseSave(false).build();
    }

    public TigerSelectResult getCustomerTopTenProduct(int i2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT TOP 10 ITEMS.CODE, ITEMS.NAME, SUM(STRNS.LINENET) as TOTAL FROM " + mLogoTigerTableName.STLINE + " STRNS WITH(NOLOCK) LEFT OUTER JOIN " + mLogoTigerTableName.STFICHE + " STFIC WITH(NOLOCK) ON (STRNS.STFICHEREF = STFIC.LOGICALREF) LEFT OUTER JOIN " + mLogoTigerTableName.ITEMS + " ITEMS WITH(NOLOCK) ON (ITEMS.LOGICALREF = STRNS.STOCKREF) WHERE (ITEMS.CARDTYPE NOT IN (21,22,23)) AND (STRNS.CLIENTREF =" + i2 + ") AND (STRNS.BILLED = 1) AND (STRNS.CANCELLED = 0) AND (STRNS.STATUS = 0) AND (STRNS.STFICHEREF <> 0) AND (STRNS.IOCODE=4) AND (STRNS.TRCODE NOT IN (4,10)) GROUP BY ITEMS.CODE, ITEMS.NAME ").withOrderBy("ORDER BY 3 DESC").withProcessType(ProcessType.GETCUSTOMERTOPTENPRODUCT).build();
    }

    public TigerSelectResult getCustomerMonthWeekSales(int i2, boolean z) {
        String str = z ? "WK" : "MM";
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT CAST(DATEPART(YYYY,STRNS.DATE_) AS VARCHAR(4))+'-'+RIGHT('0'+ LTRIM(CAST(DATEPART(" + str + ",STRNS.DATE_) AS VARCHAR (2))),2) AS PROP, SUM(STRNS.LINENET + STRNS.VATAMNT) AS TOTAT FROM " + mLogoTigerTableName.STLINE + " STRNS WITH(NOLOCK) LEFT OUTER JOIN " + mLogoTigerTableName.STFICHE + " STFIC WITH(NOLOCK) ON (STRNS.STFICHEREF = STFIC.LOGICALREF) WHERE (STRNS.CLIENTREF =" + i2 + ") AND (STRNS.BILLED = 1) AND (STRNS.CANCELLED = 0) AND (STRNS.STATUS = 0) AND (STRNS.STFICHEREF <> 0) AND (STRNS.IOCODE=4) AND (STRNS.TRCODE NOT IN (4,10)) GROUP BY CAST(DATEPART(YYYY,STRNS.DATE_) AS VARCHAR(4))+'-'+RIGHT('0'+ LTRIM(CAST(DATEPART(" + str + ",STRNS.DATE_) AS VARCHAR(2))),2)").withOrderBy("ORDER BY 1").withProcessType(ProcessType.GETCUSTOMERMONTHLYSALES).build();
    }

    public TigerSelectResult getItemPriceOnline(int i2, int i3, int i4, boolean z, int i5) {
        String str = ((("SELECT P.LOGICALREF AS PRICEREF, (CONVERT(NVARCHAR(10),P.PRICE) +' /'+ C.CURCODE) AS CPRICE  ,C.CURCODE ,C.CURTYPE ,P.PRICE , P.INCVAT FROM  " + mLogoTigerTableName.PRCLIST + " P WITH (NOLOCK) ") + " INNER JOIN " + mLogoTigerTableName.ITEMS + " I WITH (NOLOCK) ON I.LOGICALREF = P.CARDREF") + " INNER JOIN " + mLogoTigerTableName.UNITSETL + " U WITH (NOLOCK) ON U.LOGICALREF = P.UOMREF") + " INNER JOIN " + mLogoTigerTableName.CURRENCYLIST + " C ON C.CURTYPE = P.CURRENCY";
        if (i3 > 0) {
            str = str + " LEFT JOIN " + mLogoTigerTableName.CLCARD + " CL WITH (NOLOCK) ON CL.CODE = P.CLIENTCODE";
        }
        String str2 = (str + " WHERE GETDATE() BETWEEN P.BEGDATE AND CONVERT(DATETIME, P.ENDDATE + '23:59:59', 110) AND P.ACTIVE = 0 AND C.FIRMNR = " + getUser().getFirmNr() + " AND P.CARDREF = I.LOGICALREF AND P.PTYPE = 2 AND I.CARDTYPE <> 22 AND I.ACTIVE = 0 AND I.CARDTYPE NOT IN (20, 21, 22)") + " AND P.CARDREF=" + i2;
        if (i4 != -1) {
            str2 = str2 + " AND P.UOMREF=" + i4;
        }
        if (z) {
            str2 = str2 + " AND P.CYPHCODE =(SELECT CYPHCODE FROM " + mLogoTigerTableName.SLSMAN + "  WHERE ACTIVE=0 AND CODE='" + getUser().getCode() + "' AND  FIRMNR=" + getUser().getFirmNr() + ") ";
        }
        if (i5 > 0) {
            str2 = str2 + " AND P.PRIORITY=" + i5;
        }
        if (i3 > 0) {
            str2 = str2 + "AND (CL.LOGICALREF=" + i3 + " OR P.CLIENTCODE='') ";
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(str2).withOrderBy("ORDER BY PRICEREF").withProcessType(ProcessType.GETONLINEPRICE).withTableDelete(true).withDatabaseSave(true).build();
    }

    public TigerSelectResult getItemPriceOnlineWebService(int i2, int i3, int i4, boolean z, int i5, boolean z2, int i6, int i7, String str, int i8, String str2, int i9) {
        String str3;
        String str4 = ((((((((("SELECT P.LOGICALREF AS PRICEREF, (CONVERT(NVARCHAR(10),P.PRICE) +' /'+ C.CURCODE +' ('+ U.CODE +' )') + CASE WHEN LEN(ISNULL(P.DEFINITION_, '')) > 0 THEN  ' - ' + P.DEFINITION_ ELSE '' END AS CPRICE  ,C.CURCODE ,C.CURTYPE ,P.PRICE , P.INCVAT , P.CLIENTCODE , P.CLSPECODE , P.CLSPECODE2, P.CLSPECODE3 , P.CLSPECODE4 ,P.CLSPECODE5 ,P.PRIORITY ,P.TRADINGGRP, P.BRANCH, P.UNITCONVERT, ITM.CONVFACT1, ITM.CONVFACT2, P.VARIANTCODE " + SqlLiteVariable._FROM + mLogoTigerTableName.PRCLIST + " AS P WITH(NOLOCK) ") + " INNER JOIN " + mLogoTigerTableName.UNITSETL + " AS U WITH(NOLOCK) ON U.LOGICALREF=P.UOMREF ") + "INNER JOIN " + mLogoTigerTableName.ITMUNITA + " AS ITM WITH (NOLOCK) ON ITM.UNITLINEREF=P.UOMREF AND ITM.ITEMREF=P.CARDREF") + " LEFT JOIN " + mLogoTigerTableName.L_CURRENCYLIST + " AS C WITH(NOLOCK) ON C.CURTYPE=P.CURRENCY ") + "LEFT JOIN " + mLogoTigerTableName.TRADGRP + " AS T WITH (NOLOCK) ON T.GCODE = P.TRADINGGRP ") + "LEFT JOIN " + mLogoTigerTableName.TRADGRP + " AS CT WITH (NOLOCK) ON CT.GCODE = P.CLTRADINGGRP ") + "LEFT JOIN " + mLogoTigerTableName.PRCLSTDIV + " PD WITH (NOLOCK) ON PD.PARENTPRCREF = P.LOGICALREF ") + " WHERE P.ACTIVE=0 AND C.FIRMNR=" + getUser().getFirmNr()) + " AND GETDATE() BETWEEN P.BEGDATE AND CONVERT(DATETIME,P.ENDDATE + '23:59:59', 110) ") + " AND P.CARDREF=" + i2 + " AND (PD.DIVCODES = '-1' OR PD.DIVCODES LIKE '%;" + StringUtils.fillFormat(4, i9) + "%')";
        List table = getSqlHelper().getTable(ClCard.class, "LOGICALREF=?", new String[]{String.valueOf(i3)});
        ClCard clCard = new ClCard();
        if (table != null && table.size() > 0) {
            clCard = (ClCard) table.get(0);
        }
        List table2 = getSqlHelper().getTable(Item.class, "LOGICALREF=?", new String[]{String.valueOf(i2)});
        Item item = new Item();
        if (table2 != null && table2.size() > 0) {
            item = (Item) table2.get(0);
        }
        String str5 = "";
        String code = clCard.getCode() == null ? "" : clCard.getCode();
        String specode = clCard.getSpecode() == null ? "" : clCard.getSpecode();
        String specode2 = clCard.getSpecode2() == null ? "" : clCard.getSpecode2();
        String specode3 = clCard.getSpecode3() == null ? "" : clCard.getSpecode3();
        String specode4 = clCard.getSpecode4() == null ? "" : clCard.getSpecode4();
        String specode5 = clCard.getSpecode5() == null ? "" : clCard.getSpecode5();
        String tradinggrp = clCard.getTradinggrp() == null ? "" : clCard.getTradinggrp();
        String str6 = item.specode;
        String str7 = ((((((str4 + " AND ('" + code + "' LIKE REPLACE(P.CLIENTCODE, '*','%') OR P.CLIENTCODE='' OR P.CLIENTCODE IS NULL) ") + " AND (P.CLSPECODE='" + specode + "' OR P.CLSPECODE='' OR P.CLSPECODE IS NULL) ") + " AND (P.CLSPECODE2='" + specode2 + "' OR P.CLSPECODE2='' OR P.CLSPECODE2 IS NULL) ") + " AND (P.CLSPECODE3='" + specode3 + "' OR P.CLSPECODE3='' OR P.CLSPECODE3 IS NULL) ") + " AND (P.CLSPECODE4='" + specode4 + "' OR P.CLSPECODE4='' OR P.CLSPECODE4 IS NULL) ") + " AND (P.CLSPECODE5='" + specode5 + "' OR P.CLSPECODE5='' OR P.CLSPECODE5 IS NULL) ") + " AND (P.CLTRADINGGRP='" + tradinggrp + "' OR P.CLTRADINGGRP='' OR P.CLTRADINGGRP IS NULL) ";
        if (i4 != 0) {
            str7 = str7 + " AND ((P.UOMREF=" + i4 + ") OR (P.UNITCONVERT=1))";
        }
        if (i5 > 0) {
            str7 = str7 + " AND P.PRIORITY=" + i5;
        }
        if (i8 != 0) {
            str7 = str7 + " AND P.PTYPE=" + i8;
        }
        if (!TextUtils.isEmpty(str2)) {
            str7 = str7 + " AND (P.VARIANTCODE='" + str2 + "' OR P.VARIANTCODE='' OR P.VARIANTCODE IS NULL) ";
        }
        String str8 = ((str7 + String.format("AND (%s AND %s ) ", " (P.TRADINGGRP='" + str + "' OR P.TRADINGGRP='') ", " (P.PAYPLANREF=" + i6 + " OR P.PAYPLANREF=0 ) ")) + " AND (T.ACTIVE = 0 OR T.ACTIVE IS NULL ) ") + " AND (CT.ACTIVE = 0 OR CT.ACTIVE IS NULL ) ";
        if (z) {
            str8 = str8 + " AND (P.CYPHCODE = '" + ErpCreator.getInstance().getmBaseErp().getUser().getCyphCode() + "' ) ";
        }
        if (!TextUtils.isEmpty(str2)) {
            str5 = " VARIANTCODE DESC, ";
        }
        if (z) {
            str3 = " ORDER BY " + str5 + "PRIORITY,CLIENTCODE DESC,CLSPECODE DESC,CLSPECODE2 DESC,CLSPECODE3 DESC,CLSPECODE4 DESC,CLSPECODE5 DESC,PRICEREF DESC ";
        } else {
            str3 = " ORDER BY " + str5 + "PRIORITY,CLSPECODE DESC,CLSPECODE2 DESC,CLSPECODE3 DESC,CLSPECODE4 DESC,CLSPECODE5 DESC,CLIENTCODE DESC,BRANCH DESC,PRICEREF DESC";
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(str8).withOrderBy(str3).withProcessType(ProcessType.GETONLINEPRICE).build();
    }

    public TigerSelectResult getLastItemPriceSalesPurchase(int i2, int i3, DispatchGroupCode dispatchGroupCode) {
        int localCurrType = ErpCreator.getInstance().getmBaseErp().getLocalCurrType();
        int reportCurrType = ErpCreator.getInstance().getmBaseErp().getReportCurrType();
        String logoParamValue = getSqlHelper().getLogoParamValue("FEXC");
        String str = mLogoTigerTableName.DAILYEXCHANGES;
        if (logoParamValue.equals(BuildConfig.NETSIS_DEMO_PASSWORD)) {
            str = mLogoTigerTableName.LG_EXCHANGE;
        }
        String str2 = (" SELECT TOP 1 IIF(PRPRICE = 0 OR PRPRICE IS NULL OR PRRATE = 0, CASE OLD.RATES1 WHEN 0 THEN PRICE WHEN NULL THEN PRICE ELSE ISNULL(PRICE, 0) / ISNULL(OLD.RATES1, 1) * ISNULL(NEW.RATES1, ISNULL(OLD.RATES1, 1)) END, PRPRICE * ISNULL(NEW.RATES1, 1)) AS PRICE, IIF(SL.PRCLISTREF = 0, 100, SL.PRCLISTREF) AS PRICEREF, ST.DATE_, SL.LOGICALREF, (CONVERT(NVARCHAR(10), ISNULL(PRICE, 0)/(CASE OLD.RATES1 WHEN 0 THEN NEW.RATES1 WHEN NULL THEN NEW.RATES1 END)) + ' /' + C.CURCODE) AS CPRICE, SL.VATINC AS INCVAT, C.CURCODE, C.CURTYPE, ROW_NUMBER() OVER (ORDER BY ST.CAPIBLOCK_CREADEDDATE DESC) AS RowNum FROM " + mLogoTigerTableName.STLINE + " SL WITH (NOLOCK) LEFT JOIN " + mLogoTigerTableName.STFICHE + " ST WITH (NOLOCK) ON ST.LOGICALREF = SL.STFICHEREF LEFT JOIN " + mLogoTigerTableName.L_CURRENCYLIST + " AS C WITH(NOLOCK) ON C.CURTYPE = IIF(SL.PRCURR = 0, " + localCurrType + ", SL.PRCURR) LEFT JOIN " + mLogoTigerTableName.L_CURRENCYLIST + " AS C2 WITH(NOLOCK) ON C2.CURTYPE =  CASE ST.LINEEXCTYP  WHEN 0 THEN " + localCurrType + " WHEN 1 THEN " + reportCurrType + " WHEN 2 THEN SL.TRCURR  WHEN 3 THEN 20  WHEN 4 THEN SL.PRCURR END LEFT JOIN " + mLogoTigerTableName.ITEMS + " AS I WITH(NOLOCK) ON I.LOGICALREF = SL.STOCKREF LEFT JOIN " + str + " AS NEW WITH(NOLOCK) ON NEW.CRTYPE = C2.CURTYPE AND NEW.EDATE = '" + DateAndTimeUtils.convertDateSqlDateWithoutTime(DateAndTimeUtils.getNowDateTime()) + "'LEFT JOIN " + str + " AS OLD WITH(NOLOCK) ON OLD.CRTYPE = C2.CURTYPE AND OLD.EDATE = ST.DATE_ ") + " WHERE SL.PRICE > 0 AND SL.CANCELLED = 0 AND SL.LINETYPE = 0 AND SL.STOCKREF = " + i2 + " AND ST.GRPCODE = " + dispatchGroupCode.getmValue();
        if (dispatchGroupCode == DispatchGroupCode.SALES) {
            str2 = str2 + " AND SL.TRCODE IN (7, 8, 9) ";
        } else if (dispatchGroupCode == DispatchGroupCode.BUYING) {
            str2 = str2 + " AND SL.TRCODE IN (1,4) ";
        }
        if (i3 != -1) {
            str2 = str2 + " AND ST.CLIENTREF = " + i3;
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(str2).withProcessType(ProcessType.GETONLINEPRICE).build();
    }

    public TigerSelectResult getProductTopTenCustomer(int i2) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        String[] split = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards).split("\\;");
        if (split != null && split.length == 2) {
            sb.append("AND CLCARD.CODE BETWEEN '" + split[0] + "' AND '" + split[1] + "' ");
        }
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_SQLSentence);
        if (paramValue.trim().length() > 0) {
            sb.append("AND CLCARD.LOGICALREF IN (" + paramValue + ") ");
        }
        String trim = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_AuxiliaryCode).trim();
        if (!trim.equals("")) {
            sb.append("AND CLCARD.SPECODE='" + trim + "' ");
        }
        String trim2 = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_AuxiliaryCode2).trim();
        if (!trim2.equals("")) {
            sb.append("AND CLCARD.SPECODE2='" + trim2 + "' ");
        }
        String trim3 = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_AuxiliaryCode3).trim();
        if (!trim3.equals("")) {
            sb.append("AND CLCARD.SPECODE3='" + trim3 + "' ");
        }
        String trim4 = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_AuxiliaryCode4).trim();
        if (!trim4.equals("")) {
            sb.append("AND CLCARD.SPECODE4='" + trim4 + "' ");
        }
        String trim5 = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_AuxiliaryCode5).trim();
        if (!trim5.equals("")) {
            sb.append("AND CLCARD.SPECODE5='" + trim5 + "' ");
        }
        String trim6 = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_TradingGroup).trim();
        if (!trim6.equals("")) {
            sb.append("AND CLCARD.TRADINGGRP='" + trim6 + "' ");
        }
        if (getSqlHelper().getParamValue(ParameterTypes.ptARAPType).trim().equals(BuildConfig.NETSIS_DEMO_PASSWORD)) {
            sb.append("AND SLSMAN.CODE='" + getUser().getCode() + "' ");
            sb.append("AND SLSMAN.FIRMNR='" + getUser().getFirmNr() + "' ");
            sb2.append("INNER JOIN " + mLogoTigerTableName.SLSCLREL + " SC WITH(NOLOCK) ON CLCARD.LOGICALREF = SC.CLIENTREF ");
            sb2.append("INNER JOIN " + mLogoTigerTableName.SLSMAN + " SLSMAN WITH(NOLOCK) ON SLSMAN.LOGICALREF = SC.SALESMANREF ");
        }
        TigerSelectResult.TigerBuilder newBuilder = TigerSelectResult.newBuilder();
        LogoTigerTableName logoTigerTableName = mLogoTigerTableName;
        return (TigerSelectResult) newBuilder.withSql(ContextUtils.formatStringEnglish(R.string.get_product_top_ten_customer, logoTigerTableName.STLINE, logoTigerTableName.STFICHE, logoTigerTableName.INVOICE, logoTigerTableName.CLCARD, Integer.valueOf(i2), sb2.toString(), sb.toString())).withOrderBy(ContextUtils.getmContext().getString(R.string.get_product_top_ten_customer_order)).withProcessType(ProcessType.GETPRODUCTTOPTENCUSTOMER).build();
    }

    public TigerSelectResult getProductMonthlySales(int i2) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.get_product_monthly_sales, mLogoTigerTableName.STINVENS, Integer.valueOf(i2), Integer.valueOf(Year.now().getValue()))).withProcessType(ProcessType.GETPRODUCTMONTHLYSALES).build();
        }
        return null;
    }

    public TigerSelectResult getDocNo(FicheType ficheType, String str) {
        String str2;
        if (FicheTypeControlUtils.isFicheTypeInvoiceOrRetailInvoiceOrDispatch(ficheType)) {
            str2 = mLogoTigerTableName.STFICHE;
        } else if (FicheTypeControlUtils.isFicheTypeOrder(ficheType)) {
            str2 = mLogoTigerTableName.ORFICHE;
        } else if (FicheTypeControlUtils.isFicheTypeCashReceipt(ficheType)) {
            str2 = mLogoTigerTableName.CLFICHE;
        } else if (FicheTypeControlUtils.isFicheTypeCreditReceipt(ficheType)) {
            str2 = mLogoTigerTableName.CLFICHE;
        } else if (FicheTypeControlUtils.isFicheTypeCheckReceipt(ficheType)) {
            str2 = mLogoTigerTableName.CLFICHE;
        } else if (!FicheTypeControlUtils.isFicheTypeDeedReceipt(ficheType)) {
            str2 = "";
        } else {
            str2 = mLogoTigerTableName.CSROLL;
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.getmContext().getString(R.string.get_docode_unique, str2, str)).withProcessType(ProcessType.GETDOCODE).build();
    }

    public TigerSelectResult getMaxMatterNo(FicheType ficheType, MatterSettings matterSettings) {
        String str = "";
        String str2 = "";
        String str3;
        int i2;
        int i3 = 0;
        String str4;
        if (FicheTypeControlUtils.isFicheTypeOnlyInvoice(ficheType)) {
            str3 = mLogoTigerTableName.INVOICE;
            i2 = 8;
        } else if (FicheTypeControlUtils.isFicheTypeOnlyRetailInvoice(ficheType)) {
            str3 = mLogoTigerTableName.INVOICE;
            i2 = 7;
        } else {
            if (FicheTypeControlUtils.isFicheTypeDispatch(ficheType)) {
                str4 = mLogoTigerTableName.STFICHE;
            } else if (FicheTypeControlUtils.isFicheTypeOrder(ficheType)) {
                str4 = mLogoTigerTableName.ORFICHE;
            } else {
                if (FicheTypeControlUtils.isFicheTypeCashReceipt(ficheType)) {
                    str = mLogoTigerTableName.CLFICHE;
                    str2 = "";
                    i3 = 1;
                } else if (FicheTypeControlUtils.isFicheTypeCreditReceipt(ficheType)) {
                    str3 = mLogoTigerTableName.CLFICHE;
                    i2 = 70;
                } else {
                    if (FicheTypeControlUtils.isFicheTypeCheckReceipt(ficheType)) {
                        str = mLogoTigerTableName.CSROLL;
                        i3 = 1;
                    } else if (FicheTypeControlUtils.isFicheTypeDeedReceipt(ficheType)) {
                        i3 = 2;
                        str = mLogoTigerTableName.CSROLL;
                    } else if (FicheTypeControlUtils.isFicheTypeCaseReceipt(ficheType)) {
                        str3 = mLogoTigerTableName.KSLINES;
                        i2 = 11;
                    } else {
                        str = "";
                        str2 = str;
                        i3 = -1;
                    }
                    str2 = "ROLLNO";
                }
                return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(getMaxMatterNoAreaSql(matterSettings, str, str, i3, str2)).withProcessType(ProcessType.GETMAXPRINTMATTERAREA).build();
            }
            str = str4;
            str2 = "";
            i3 = -1;
            return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(getMaxMatterNoAreaSql(matterSettings, str, str, i3, str2)).withProcessType(ProcessType.GETMAXPRINTMATTERAREA).build();
        }
        str = str3;
        i3 = i2;
        str2 = "";
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(getMaxMatterNoAreaSql(matterSettings, str, str, i3, str2)).withProcessType(ProcessType.GETMAXPRINTMATTERAREA).build();
    }

    private String getMaxMatterNoAreaSql(MatterSettings matterSettings, String str, String str2, int i2, String str3) {
        String str4;
        if (matterSettings.getMatterArea() == MatterArea.FICHE_NO) {
            if (TextUtils.isEmpty(str3)) {
                str3 = ContextUtils.getmContext().getString(R.string.tiger_column_fiche_no);
            }
        } else {
            str3 = ContextUtils.getmContext().getString(R.string.tiger_column_docode);
            str = str2;
        }
        if (i2 <= 0) {
            str4 = "";
        } else {
            str4 = " AND TRCODE=" + i2;
        }
        return ContextUtils.getmContext().getString(R.string.select_max_matter_no, matterSettings.getFirstMatterNo(), matterSettings.getLastMatterNo(), str, str3, str4);
    }

    private static String getBalanceSql(String str) {
        return "(SELECT CARDREF, 1 AS TOTTYP, SUM(ISNULL(DEBIT, 0)) DEBIT,SUM(ISNULL(CREDIT, 0)) CREDIT,SUM(ISNULL(DEBIT, 0))-SUM(ISNULL(CREDIT, 0)) BAKIYE FROM " + str + " WHERE TOTTYP=1 GROUP BY CARDREF)";
    }

    private String getBeforeBalanceSql(Class cls, int i2) {
        try {
            List table = getSqlHelper().getTable(cls, "FICHEREF=?", new String[]{String.valueOf(i2)}, null, null, "LOGICALREF DESC");
            if (table == null || table.size() <= 0) {
                return "0 AS BEFOREBALANCE,";
            }
            Object obj = table.get(0);
            Integer num = (Integer) obj.getClass().getMethod("getClRef", null).invoke(obj, null);
            String obj2 = obj.getClass().getMethod("getGDate", null).invoke(obj, null).toString();
            DateAndTimeUtils.convertDateSqlDateWithoutTime(obj2);
            DateAndTimeUtils.getLogoDateTimeCode(obj2);
            return "(SELECT SUM(AMOUNT) FROM (SELECT SUM(CTRNS.AMOUNT * CASE WHEN CTRNS.SIGN = 0 THEN 1 ELSE -1 END) AMOUNT , CTRNS.SIGN FROM " + mLogoTigerTableName.CLFLINE + " CTRNS WHERE CTRNS.CANCELLED = 0 AND CTRNS.CLIENTREF = " + num + " AND  CONVERT(DATETIME,CONVERT(VARCHAR,(CONVERT(DATE, CTRNS.DATE_, 121))) + ' ' +CONVERT(VARCHAR, CTRNS.FTIME/16777216) + ':' +CONVERT(VARCHAR, (CTRNS.FTIME % 16777216)/65536) + ':' +CONVERT(VARCHAR, (CTRNS.FTIME % 16777216 % 65536)/256),121)< '" + (cls.equals(CaseCash.class) ? DateAndTimeUtils.convertDateSqlDateWithoutSecond(obj2) : DateAndTimeUtils.convertDateSqlDate(obj2)) + "'GROUP BY CTRNS.SIGN) A ) AS BEFOREBALANCE,";
        } catch (Exception e2) {
            Log.e(TAG, "getBeforeBalanceSql: ", e2);
            return "0 AS BEFOREBALANCE,";
        }
    }

    
    public TigerSelectResult getPrintInvoiceHeader(int i2) {
        String beforeBalanceSql = getBeforeBalanceSql(Invoice.class, i2);
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((((((((((((((((((((((((((((((((" SELECT C.TCKNO,C.ISPERSCOMP,'' AS 'TOTALTEXT',I.TRCODE, I.LOGICALREF AS INVOICEREF,I.FICHENO, I.DATE_, I.TIME_ , I.DOCODE , I.SPECODE , I.CYPHCODE ,SF.DATE_ AS IRSDATE,SF.FICHENO AS IRSNO,I.SHIPINFOREF,I.RECVREF,I.CLIENTREF,I.GUID, C.CODE AS CCODE , C.DEFINITION_ AS CDEFINITION , C.SPECODE AS CSPECODE , C.CYPHCODE AS CCYPHCODE, C.ADDR1 , ") + " C.ADDR2 , C.CITY , C.DISTRICT , C.TELNRS1 , C.TELNRS2 , C.FAXNR , C.TAXNR ,C.TAXOFFCODE,C.TOWN, ") + " C.TAXOFFICE , C.INCHARGE , C.DISCRATE , C.EMAILADDR , C.TRADINGGRP AS CTRADINGGRP , C.DEFINITION2 , ") + " (SELECT CODE FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RCODE , ") + " (SELECT DEFINITION_ FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RNAME , ") + " (SELECT ADDR1 FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RADDR1 , ") + " (SELECT ADDR2 FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RADDR2 , ") + " (SELECT CITY FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RCITY , ") + " (SELECT TOWN FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RTOWN , ") + " (SELECT ADDR1 FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS SADDR1 , ") + " (SELECT ADDR2 FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS SADDR2 , ") + " (SELECT CITY FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS SCITY , ") + " (SELECT TOWN FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS STOWN , ") + " ISNULL(CT.DEBIT,0) AS DEBIT , ISNULL(CT.CREDIT,0) AS CREDIT ,") + " (ISNULL(CT.DEBIT, 0) - ISNULL(CT.CREDIT, 0)) AS BAKIYE, ") + " I.TOTALVAT , I.NETTOTAL , I.TOTALDISCOUNTS , I.GROSSTOTAL , I.GENEXP1 , ") + " I.GENEXP2 , I.GENEXP3 , I.GENEXP4 , I.SHPTYPCOD , I.SHPAGNCOD , I.TRADINGGRP, I.TRRATE, I.TRCURR, ") + " I.SOURCEINDEX , S.CODE AS SCODE , ") + " S.DEFINITION_ AS SDEFINITION , P.CODE AS PCODE,P.DEFINITION_ AS PDEFINITION , P.LOGICALREF AS PAYPLANREF, ") + " F.CODE AS FCODE , F.NAME AS FNAME ,") + " (SELECT TOP 1 ISNULL(AMOUNT,0) FROM " + mLogoTigerTableName.CLFLINE + " WHERE MODULENR IN (5,10,6) AND CLIENTREF=I.CLIENTREF AND DATE_ BETWEEN '" + DateAndTimeUtils.nowDateSqlPrint() + " 00:00:00' AND '" + DateAndTimeUtils.nowDateSqlPrint() + " 23:59:59'  ORDER BY DATE_ DESC,FTIME DESC,LOGICALREF DESC ) AS CARISONBAKIYE , ") + beforeBalanceSql) + "(SELECT LATITUTE FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS LATITUTE ,") + "(SELECT LATITUTE FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS LONGITUDE ") + SqlLiteVariable._FROM + mLogoTigerTableName.INVOICE + " AS I ") + " LEFT JOIN " + mLogoTigerTableName.STFICHE + " AS SF ON I.LOGICALREF=SF.INVOICEREF ") + " INNER JOIN " + mLogoTigerTableName.CLCARD + " AS C ON I.CLIENTREF=C.LOGICALREF ") + " LEFT JOIN " + mLogoTigerTableName.LG_SLSMAN + " AS S ON I.SALESMANREF=S.LOGICALREF ") + " LEFT JOIN " + mLogoTigerTableName.PAYPLANS + " AS P ON P.LOGICALREF=I .PAYDEFREF ") + " LEFT JOIN " + mLogoTigerTableName.SHIPINFO + " AS F ON F.LOGICALREF=I.SHIPINFOREF ") + " LEFT JOIN " + getBalanceSql(mLogoTigerTableName.CLTOTFIL) + " AS CT ON C.LOGICALREF=CT.CARDREF AND TOTTYP=1") + " WHERE I.LOGICALREF=" + i2).withProcessType(ProcessType.GETONLINEPRICE).build();
    }

    
    public TigerSelectResult getPrintInvoiceDetail(int i2, int i3) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((((((((((((((((((((((((((((((((((((((((((" SELECT S.INVOICEREF,S.LOGICALREF ,S.STFICHELNNO , S.LINETYPE, I.CODE, I.NAME, I.NAME3 AS NAME2, D.CODE AS DCODE, D.DEFINITION_ AS DNAME ,O.FICHENO , P.CODE AS PCODE,P.DEFINITION_ AS PDEFINITION , U.CODE AS UCODE ,U.NAME AS UNAME ,I.GTIPCODE, S.STOCKREF, S.UOMREF, S.USREF, ") + " S.SOURCEINDEX , S.SPECODE , S.DELVRYCODE , S.AMOUNT , ") + " S.PRICE , S.TOTAL , S.DISCPER , S.VAT , S.VATAMNT , S.VATMATRAH , S.LINEEXP ,S.VARIANTREF,S.GLOBTRANS, ") + " (SELECT TOP 1 BARCODE FROM " + mLogoTigerTableName.UNITBARCODE + " WHERE ITEMREF=S.STOCKREF AND S.UOMREF=UNITLINEREF) AS Barkod") + " , SP.ICUSTSUPCODE , SP.ICUSTSUPNAME, SV.CODE AS VCODE,SV.NAME AS VNAME , I.TRACKTYPE , I.LOGICALREF AS ITEMREF, S.GUID , ") + " COALESCE((UNIT.WEIGHT / WGT.CONVFACT1) * WGT.CONVFACT2, 0.00) AS WEIGHT, ") + " COALESCE((UNIT.GROSSWEIGHT / GRSWGT.CONVFACT1) * GRSWGT.CONVFACT2, 0.00) AS GROSSWEIGHT, ") + " COALESCE((UNIT.VOLUME_ / VOL.CONVFACT1) * VOL.CONVFACT2 / 1000, 0.00) AS VOLUME, ") + " CAST(UNIT.WIDTH AS VARCHAR(MAX)) + ' ' + ISNULL(W.CODE, '') AS WIDTH, ") + " CAST(UNIT.LENGTH AS VARCHAR(MAX)) + ' ' + ISNULL(L.CODE, '') AS LENGTH, ") + " CAST(UNIT.HEIGHT AS VARCHAR(MAX)) + ' ' + ISNULL(H.CODE, '') AS HEIGHT ") + SqlLiteVariable._FROM + mLogoTigerTableName.STLINE + " AS S ") + " LEFT JOIN " + mLogoTigerTableName.ITMUNITA + " AS UNIT ON UNIT.ITEMREF = S.STOCKREF AND UNIT.UNITLINEREF = S.UOMREF ") + " LEFT JOIN " + mLogoTigerTableName.ITEMS + " AS I ON I.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + mLogoTigerTableName.DECARDS + " AS D ON D.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + mLogoTigerTableName.ORFICHE + " AS O ON O.LOGICALREF=S .ORDFICHEREF ") + " LEFT JOIN " + mLogoTigerTableName.PAYPLANS + " AS P ON P.LOGICALREF=S .PAYDEFREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS U ON U.LOGICALREF=S.UOMREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS WGT ON WGT.LOGICALREF = UNIT.WEIGHTREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS GRSWGT ON GRSWGT.LOGICALREF = UNIT.GROSSWGHTREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS VOL ON VOL.LOGICALREF = UNIT.VOLUMEREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS W ON W.LOGICALREF = UNIT.WIDTHREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS L ON L.LOGICALREF = UNIT.LENGTHREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS H ON H.LOGICALREF = UNIT.HEIGHTREF ") + " LEFT JOIN " + mLogoTigerTableName.SUPPASGN + " AS SP ON SP.ITEMREF=I .LOGICALREF AND SP.CLIENTREF =" + i3) + " LEFT JOIN " + mLogoTigerTableName.VARIANT + " AS SV ON SV.ITEMREF=S.VARIANTREF ") + " WHERE S.LINETYPE NOT IN(4,2) AND S.INVOICEREF=" + i2) + " UNION ") + " SELECT S.INVOICEREF,S.LOGICALREF ,S.STFICHELNNO , S.LINETYPE, I.CODE ,I.DEFINITION_ AS NAME, '' AS NAME2, D.CODE AS DCODE, D.DEFINITION_ AS DNAME ,O.FICHENO ,") + " P.CODE AS PCODE,P.DEFINITION_ AS PDEFINITION , U.CODE AS UCODE ,U.NAME AS UNAME , '' AS GTIPCODE, S.STOCKREF, S.UOMREF, S.USREF, ") + " S.SOURCEINDEX , S.SPECODE , S.DELVRYCODE , S.AMOUNT , ") + " S.PRICE , S.TOTAL , S.DISCPER , S.VAT , S.VATAMNT , S.VATMATRAH , S.LINEEXP ,S.VARIANTREF,S.GLOBTRANS, '' AS Barkod  ") + " , SP.ICUSTSUPCODE , SP.ICUSTSUPNAME, SV.CODE AS VCODE,SV.NAME AS VNAME ,0 AS TRACKTYPE , I.LOGICALREF AS ITEMREF, S.GUID , 0 AS WEIGHT, 0 AS GROSSWEIGHT, 0 AS VOLUME, '' AS WIDTH, '' AS LENGTH, '' AS HEIGHT ") + SqlLiteVariable._FROM + mLogoTigerTableName.STLINE + " AS S ") + " LEFT JOIN " + mLogoTigerTableName.SRVCARD + " AS I ON I.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + mLogoTigerTableName.DECARDS + " AS D ON D.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + mLogoTigerTableName.ORFICHE + " AS O ON O.LOGICALREF=S .ORDFICHEREF ") + " LEFT JOIN " + mLogoTigerTableName.PAYPLANS + " AS P ON P.LOGICALREF=S .PAYDEFREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS U ON U.LOGICALREF=S.UOMREF ") + " LEFT JOIN " + mLogoTigerTableName.SUPPASGN + " AS SP ON SP.ITEMREF=I .LOGICALREF ") + " LEFT JOIN " + mLogoTigerTableName.VARIANT + " AS SV ON SV.ITEMREF=S.VARIANTREF ") + " WHERE S.LINETYPE=4 AND S.INVOICEREF=" + i2).withProcessType(ProcessType.PRINTDETAIL).build();
    }

    
    public TigerSelectResult getPrintInvoiceDiscount(int i2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT GLOBTRANS , STFICHELNNO, LINETYPE,DISCPER,TOTAL , LOGICALREF FROM " + mLogoTigerTableName.STLINE + " WHERE INVOICEREF=" + i2).withOrderBy("ORDER BY STFICHELNNO").withProcessType(ProcessType.PRINTDISCOUNT).build();
    }

    
    public TigerSelectResult getPrintOrderHeader(int i2) {
        String beforeBalanceSql = getBeforeBalanceSql(Order.class, i2);
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((((((((((((((((((((((((((((((((" SELECT C.TCKNO,C.ISPERSCOMP,'' AS TOTALTEXT,I.LOGICALREF ,I.FICHENO , I.DATE_ , I.TIME_ , I.DOCODE , I.SPECODE , I.CYPHCODE ,I.SHIPINFOREF,I.RECVREF, I.CLIENTREF, C.EMAILADDR,C.CODE AS CCODE , C.DEFINITION_ AS CDEFINITION , C.SPECODE AS CSPECODE , C.CYPHCODE AS CCYPHCODE, C.ADDR1 , ") + " C.ADDR2 , C.CITY , C.DISTRICT , C.TELNRS1 , C.TELNRS2 , C.FAXNR , C.TAXNR ,C.TAXOFFCODE,C.TOWN, ") + " C.TAXOFFICE , C.INCHARGE , C.DISCRATE , C.TRADINGGRP AS CTRADINGGRP, ") + " (SELECT CODE FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RCODE , ") + " (SELECT DEFINITION_ FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RNAME , ") + " (SELECT ADDR1 FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RADDR1 , ") + " (SELECT ADDR2 FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RADDR2 , ") + " (SELECT CITY FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RCITY , ") + " (SELECT TOWN FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RTOWN , ") + " (SELECT ADDR1 FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS SADDR1 , ") + " (SELECT ADDR2 FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS SADDR2 , ") + " (SELECT CITY FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS SCITY , ") + " (SELECT TOWN FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS STOWN , ") + " ISNULL(CT.DEBIT,0) AS DEBIT , ISNULL(CT.CREDIT,0) AS CREDIT ,") + " (ISNULL(CT.DEBIT, 0) - ISNULL(CT.CREDIT, 0)) AS BAKIYE, ") + " I.TOTALVAT , I.NETTOTAL , I.TOTALDISCOUNTS , I.GROSSTOTAL , I.GENEXP1 , ") + " I.GENEXP2 , I.GENEXP3 , I.GENEXP4 , I.SHPTYPCOD , I.SHPAGNCOD , I.TRADINGGRP, ") + " I.SOURCEINDEX, I.TRRATE, I.TRCURR, S.CODE AS SCODE , ") + " S.DEFINITION_ AS SDEFINITION , P.CODE AS PCODE,P.DEFINITION_ AS PDEFINITION , ") + " F.CODE AS FCODE , F.NAME AS FNAME ,") + " (SELECT ISNULL(SUM(NETTOTAL),0) FROM " + mLogoTigerTableName.INVOICE + " WHERE TRCODE=8 AND CLIENTREF=I.CLIENTREF AND DATE_ < '" + DateAndTimeUtils.nowDateSqlPrint() + " 00:00:00' ) AS CARIBAKIYE , ") + " (SELECT TOP 1 ISNULL(AMOUNT,0) FROM " + mLogoTigerTableName.CLFLINE + " WHERE MODULENR IN (5,10,6) AND CLIENTREF=I.CLIENTREF AND DATE_ BETWEEN '" + DateAndTimeUtils.nowDateSqlPrint() + " 00:00:00' AND '" + DateAndTimeUtils.nowDateSqlPrint() + " 23:59:59'  ORDER BY DATE_ DESC,FTIME DESC,LOGICALREF DESC ) AS CARISONBAKIYE , ") + beforeBalanceSql) + "(SELECT LATITUTE FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS LATITUTE ,") + "(SELECT LATITUTE FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS LONGITUDE ") + SqlLiteVariable._FROM + mLogoTigerTableName.ORFICHE + " AS I ") + " INNER JOIN " + mLogoTigerTableName.CLCARD + " AS C ON I.CLIENTREF=C.LOGICALREF ") + " LEFT JOIN " + mLogoTigerTableName.LG_SLSMAN + " AS S ON I.SALESMANREF=S.LOGICALREF ") + " LEFT JOIN " + mLogoTigerTableName.PAYPLANS + " AS P ON P.LOGICALREF=I .PAYDEFREF ") + " LEFT JOIN " + mLogoTigerTableName.SHIPINFO + " AS F ON F.LOGICALREF=I.SHIPINFOREF ") + " LEFT JOIN " + getBalanceSql(mLogoTigerTableName.CLTOTFIL) + " AS CT ON C.LOGICALREF=CT.CARDREF AND TOTTYP=1 ") + " WHERE I.LOGICALREF=" + i2).withProcessType(ProcessType.PRINTHEADER).build();
    }

    
    public TigerSelectResult getPrintOrderDetail(int i2, int i3) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(((((((((((((((((((((((((((((((((((((((((" SELECT S.ORDFICHEREF , S.LOGICALREF ,S.LINENO_ , S.LINETYPE, I.CODE, I.NAME, I.NAME3 AS NAME2, D.CODE AS DCODE, D.DEFINITION_ AS DNAME ,  P.CODE AS PCODE,P.DEFINITION_ AS PDEFINITION , U.CODE AS UCODE ,U.NAME AS UNAME , I.GTIPCODE, ") + " S.SOURCEINDEX , S.SPECODE , S.DELVRYCODE , S.AMOUNT , S.SHIPPEDAMOUNT , S.DUEDATE , S.VATINC , S.STOCKREF, S.UOMREF, S.USREF, ") + " S.PRICE , S.TOTAL , S.DISCPER , S.VAT , S.VATAMNT , S.VATMATRAH , S.DORESERVE , S.LINEEXP,S.VARIANTREF, ") + " (SELECT TOP 1 BARCODE FROM " + mLogoTigerTableName.UNITBARCODE + " WHERE ITEMREF=S.STOCKREF AND S.UOMREF=UNITLINEREF) AS Barkod ,") + " SP.ICUSTSUPCODE , SP.ICUSTSUPNAME, SV.CODE AS VCODE,SV.NAME AS VNAME, ") + " COALESCE((UNIT.WEIGHT / WGT.CONVFACT1) * WGT.CONVFACT2, 0.00) AS WEIGHT, ") + " COALESCE((UNIT.GROSSWEIGHT / GRSWGT.CONVFACT1) * GRSWGT.CONVFACT2, 0.00) AS GROSSWEIGHT, ") + " COALESCE((UNIT.VOLUME_ / VOL.CONVFACT1) * VOL.CONVFACT2 / 1000, 0.00) AS VOLUME, ") + " CAST(UNIT.WIDTH AS VARCHAR(MAX)) + ' ' + ISNULL(W.CODE, '') AS WIDTH, ") + " CAST(UNIT.LENGTH AS VARCHAR(MAX)) + ' ' + ISNULL(L.CODE, '') AS LENGTH, ") + " CAST(UNIT.HEIGHT AS VARCHAR(MAX)) + ' ' + ISNULL(H.CODE, '') AS HEIGHT ") + SqlLiteVariable._FROM + mLogoTigerTableName.ORFLINE + " AS S ") + " LEFT JOIN " + mLogoTigerTableName.ITMUNITA + " AS UNIT ON UNIT.ITEMREF = S.STOCKREF AND UNIT.UNITLINEREF = S.UOMREF ") + " LEFT JOIN " + mLogoTigerTableName.ITEMS + " AS I ON I.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + mLogoTigerTableName.DECARDS + " AS D ON D.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + mLogoTigerTableName.PAYPLANS + " AS P ON P.LOGICALREF=S .PAYDEFREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS U ON U.LOGICALREF=S.UOMREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS WGT ON WGT.LOGICALREF = UNIT.WEIGHTREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS GRSWGT ON GRSWGT.LOGICALREF = UNIT.GROSSWGHTREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS VOL ON VOL.LOGICALREF = UNIT.VOLUMEREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS W ON W.LOGICALREF = UNIT.WIDTHREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS L ON L.LOGICALREF = UNIT.LENGTHREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS H ON H.LOGICALREF = UNIT.HEIGHTREF ") + " LEFT JOIN " + mLogoTigerTableName.SUPPASGN + " AS SP ON SP.ITEMREF=I.LOGICALREF AND SP.CLIENTREF =" + i3) + " LEFT JOIN " + mLogoTigerTableName.VARIANT + " AS SV ON SV.ITEMREF=S.VARIANTREF ") + " WHERE S.LINETYPE NOT IN(2,4) AND S.ORDFICHEREF=" + i2) + " UNION ") + " SELECT S.ORDFICHEREF , S.LOGICALREF ,S.LINENO_ , S.LINETYPE, I.CODE ,I.DEFINITION_ AS NAME, '' AS NAME2 , D.CODE AS DCODE, D.DEFINITION_ AS DNAME , ") + " P.CODE AS PCODE,P.DEFINITION_ AS PDEFINITION , U.CODE AS UCODE ,U.NAME AS UNAME , '' AS GTIPCODE,") + " S.SOURCEINDEX , S.SPECODE , S.DELVRYCODE , S.AMOUNT , S.SHIPPEDAMOUNT , S.DUEDATE , S.VATINC , S.STOCKREF, S.UOMREF, S.USREF, ") + " S.PRICE , S.TOTAL , S.DISCPER , S.VAT , S.VATAMNT , S.VATMATRAH , S.DORESERVE , S.LINEEXP,S.VARIANTREF, '' AS Barkod ,") + "  SP.ICUSTSUPCODE, SP.ICUSTSUPNAME, SV.CODE AS VCODE,SV.NAME AS VNAME, 0 AS WEIGHT, 0 AS GROSSWEIGHT, ") + " 0 AS VOLUME, '' AS WIDTH, '' AS LENGTH, '' AS HEIGHT ") + SqlLiteVariable._FROM + mLogoTigerTableName.ORFLINE + " AS S ") + " LEFT JOIN " + mLogoTigerTableName.SRVCARD + " AS I ON I.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + mLogoTigerTableName.DECARDS + " AS D ON D.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + mLogoTigerTableName.PAYPLANS + " AS P ON P.LOGICALREF=S .PAYDEFREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS U ON U.LOGICALREF=S.UOMREF ") + " LEFT JOIN " + mLogoTigerTableName.SUPPASGN + " AS SP ON SP.ITEMREF=I .LOGICALREF ") + " LEFT JOIN " + mLogoTigerTableName.VARIANT + " AS SV ON SV.ITEMREF=S.VARIANTREF ") + " WHERE S.LINETYPE=4 AND S.ORDFICHEREF=" + i2).withProcessType(ProcessType.PRINTDETAIL).build();
    }

    
    public TigerSelectResult getPrintOrderDiscount(int i2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT GLOBTRANS , LINETYPE,DISCPER,TOTAL , LOGICALREF , LINENO_ FROM " + mLogoTigerTableName.ORFLINE + " WHERE ORDFICHEREF=" + i2).withOrderBy("ORDER BY LINENO_").withProcessType(ProcessType.PRINTDISCOUNT).build();
    }

    public TigerSelectResult getPrintCreditCardHeader(int i2) {
        String beforeBalanceSql = getBeforeBalanceSql(CashCredit.class, i2);
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(((((((((((((((((" SELECT CL.CLCARDREF,CL.FICHENO , CL.DATE_ , CL.TIME , CL.SPECCODE , CL.CYPHCODE , CL.TRADINGGRP , CL.GENEXP1 , CL.GENEXP2 , CL.GENEXP3 , CL.GENEXP4 ,  C.CODE AS CCODE , C.DEFINITION_ AS CDEFINITION , C.SPECODE AS CSPECODE , C.CYPHCODE AS CCYPHCODE, C.ADDR1 , ") + " C.ADDR2 , C.CITY , C.DISTRICT , C.TELNRS1 , C.TELNRS2 , C.FAXNR , C.TAXNR ,C.TAXOFFCODE,C.TOWN, ") + " C.TAXOFFICE , C.INCHARGE , C.DISCRATE , C.EMAILADDR , C.TRADINGGRP AS CTRADINGGRP , ") + " ISNULL(CT.DEBIT,0) AS DEBIT , ISNULL(CT.CREDIT,0) AS CREDIT ,") + " (ISNULL(CT.DEBIT, 0) - ISNULL(CT.CREDIT, 0)) AS BAKIYE, ") + " BA.CODE AS BACODE , BA.DEFINITION_ AS BADEFINITION , ") + " B.CODE AS BCODE , B.DEFINITION_ AS BDEFINITION, S.CODE AS SCODE, S.DEFINITION_ AS SDEFINITION, C.TCKNO, C.ISPERSCOMP, ") + " (SELECT ISNULL(SUM(NETTOTAL),0) FROM " + mLogoTigerTableName.INVOICE + " WHERE TRCODE=8 AND CLIENTREF=CL.CLCARDREF AND DATE_ < '" + DateAndTimeUtils.nowDateSqlPrint() + " 00:00:00' ) AS CARIBAKIYE , ") + beforeBalanceSql) + " (SELECT TOP 1 ISNULL(AMOUNT,0) FROM " + mLogoTigerTableName.CLFLINE + " WHERE MODULENR IN (5,10,6) AND CLIENTREF=CL.CLCARDREF AND DATE_ BETWEEN '" + DateAndTimeUtils.nowDateSqlPrint() + " 00:00:00' AND '" + DateAndTimeUtils.nowDateSqlPrint() + " 23:59:59'  ORDER BY DATE_ DESC,FTIME DESC,LOGICALREF DESC ) AS CARISONBAKIYE  ") + SqlLiteVariable._FROM + mLogoTigerTableName.CLFICHE + " AS CL ") + " INNER JOIN " + mLogoTigerTableName.CLCARD + " AS C ON C.LOGICALREF=CL .CLCARDREF ") + " LEFT JOIN " + getBalanceSql(mLogoTigerTableName.CLTOTFIL) + " AS CT ON C.LOGICALREF=CT.CARDREF AND CT.TOTTYP=1 ") + " LEFT JOIN " + mLogoTigerTableName.LG_SLSMAN + " AS S ON CL.SALESMANREF=S.LOGICALREF ") + " INNER JOIN " + mLogoTigerTableName.BANKACC + " AS BA ON BA.LOGICALREF=CL.BANKACCREF ") + " INNER JOIN " + mLogoTigerTableName.BNCARD + " AS B ON B.LOGICALREF=BA.BANKREF ") + " WHERE CL.LOGICALREF=" + i2).withProcessType(ProcessType.PRINTHEADER).build();
    }

    
    public TigerSelectResult getPrintCashHeader(int i2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(((" SELECT CL.CREDIT,CL.FICHENO , CL.DATE_ , CL.SPECCODE , CL.CYPHCODE , CL.GENEXP1 , CL.GENEXP2 , CL.GENEXP3 , CL.GENEXP4, S.CODE AS SCODE, S.DEFINITION_ AS SDEFINITION   " + SqlLiteVariable._FROM + mLogoTigerTableName.CLFICHE + " AS CL ") + " LEFT JOIN " + mLogoTigerTableName.LG_SLSMAN + " AS S ON CL.SALESMANREF=S.LOGICALREF ") + " WHERE CL.LOGICALREF=" + i2).withProcessType(ProcessType.PRINTHEADER).build();
    }

    
    public TigerSelectResult getPrintCashDetail(int i2) {
        String beforeBalanceSql = getBeforeBalanceSql(CashCredit.class, i2);
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(((((((((((((" SELECT CL.CLIENTREF,CL.SPECODE , CL.LINEEXP , CL.AMOUNT , CL.DOCODE ,  C.CODE AS CCODE , C.DEFINITION_ AS CDEFINITION , C.SPECODE AS CSPECODE , C.CYPHCODE AS CCYPHCODE, C.ADDR1 , ") + " C.ADDR2 , C.CITY , C.DISTRICT , C.TELNRS1 , C.TELNRS2 , C.FAXNR , C.TAXNR ,C.TAXOFFCODE,C.TOWN, ") + " C.TAXOFFICE , C.INCHARGE , C.DISCRATE , C.EMAILADDR , C.TRADINGGRP AS CTRADINGGRP ,") + " ISNULL(CT.DEBIT,0) AS DEBIT , ISNULL(CT.CREDIT,0) AS CREDIT ,") + " (ISNULL(CT.DEBIT,0)-ISNULL(CT.CREDIT,0)) AS DEBITACCOUNT , ") + " (ISNULL(CT.CREDIT,0)-ISNULL(CT.DEBIT,0)) AS CREDITACCOUNT, C.TCKNO,C.ISPERSCOMP , ") + " (SELECT ISNULL(SUM(NETTOTAL),0) FROM " + mLogoTigerTableName.INVOICE + " WHERE TRCODE=8 AND CLIENTREF=CL.CLIENTREF AND DATE_ < '" + DateAndTimeUtils.nowDateSqlPrint() + " 00:00:00' ) AS CARIBAKIYE , ") + beforeBalanceSql) + " (SELECT TOP 1 ISNULL(AMOUNT,0) FROM " + mLogoTigerTableName.CLFLINE + " WHERE MODULENR IN (5,10,6) AND CLIENTREF=CL.CLIENTREF AND DATE_ BETWEEN '" + DateAndTimeUtils.nowDateSqlPrint() + " 00:00:00' AND '" + DateAndTimeUtils.nowDateSqlPrint() + " 23:59:59'  ORDER BY DATE_ DESC,FTIME DESC,LOGICALREF DESC ) AS CARISONBAKIYE  ") + SqlLiteVariable._FROM + mLogoTigerTableName.CLFLINE + " AS CL ") + " INNER JOIN " + mLogoTigerTableName.CLCARD + " AS C ON C.LOGICALREF=CL .CLIENTREF ") + " LEFT JOIN " + getBalanceSql(mLogoTigerTableName.CLTOTFIL) + " AS CT ON C.LOGICALREF=CT.CARDREF AND CT.TOTTYP=1 ") + " WHERE CL.MODULENR=5 AND CL.TRCODE=1 AND CL.SOURCEFREF=" + i2).withProcessType(ProcessType.PRINTDETAIL).build();
    }

    
    public TigerSelectResult getPrintCreditCardDetail(int i2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(((" SELECT CL.CLIENTREF,CL.SPECODE , CL.AMOUNT , CL.DOCODE , P.CODE AS PCODE,P.DEFINITION_ AS PDEFINITION " + SqlLiteVariable._FROM + mLogoTigerTableName.CLFLINE + " AS CL ") + " LEFT JOIN " + mLogoTigerTableName.PAYPLANS + " AS P ON P.LOGICALREF=CL .PAYDEFREF ") + " WHERE CL.MODULENR=5 AND CL.TRCODE=70 AND CL.SOURCEFREF=" + i2).withProcessType(ProcessType.PRINTDETAIL).build();
    }

    
    public TigerSelectResult getPrintChequeHeader(int i2) {
        String beforeBalanceSql = getBeforeBalanceSql(Chequedeed.class, i2);
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((((((((((((((" SELECT R.CARDREF,R.TOTAL,R.ROLLNO , R.DATE_ , R.SPECODE , R.CYPHCODE , R.GENEXP1 , R.GENEXP2 , R.GENEXP3 , R.GENEXP4 ,  C.CODE AS CCODE , C.DEFINITION_ AS CDEFINITION , C.SPECODE AS CSPECODE , C.CYPHCODE AS CCYPHCODE, C.ADDR1 , ") + " C.ADDR2 , C.CITY , C.DISTRICT , C.TELNRS1 , C.TELNRS2 , C.FAXNR , C.TAXNR ,C.TAXOFFCODE,C.TOWN, ") + " C.TAXOFFICE , C.INCHARGE , C.DISCRATE , C.EMAILADDR , C.TRADINGGRP AS CTRADINGGRP , ") + " ISNULL(CT.DEBIT,0) AS DEBIT , ISNULL(CT.CREDIT,0) AS CREDIT ,") + " (ISNULL(CT.DEBIT, 0) - ISNULL(CT.CREDIT, 0)) AS BAKIYE, ") + " S.CODE AS SCODE, S.DEFINITION_ AS SDEFINITION , C.TCKNO,C.ISPERSCOMP, ") + " (SELECT ISNULL(SUM(NETTOTAL),0) FROM " + mLogoTigerTableName.INVOICE + " WHERE TRCODE=8 AND CLIENTREF=R.CARDREF AND DATE_ < '" + DateAndTimeUtils.nowDateSqlPrint() + " 00:00:00' ) AS CARIBAKIYE , ") + beforeBalanceSql) + " (SELECT TOP 1 ISNULL(AMOUNT,0) FROM " + mLogoTigerTableName.CLFLINE + " WHERE MODULENR IN (5,10,6) AND CLIENTREF=R.CARDREF AND DATE_ BETWEEN '" + DateAndTimeUtils.nowDateSqlPrint() + " 00:00:00' AND '" + DateAndTimeUtils.nowDateSqlPrint() + " 23:59:59'  ORDER BY DATE_ DESC,FTIME DESC,LOGICALREF DESC ) AS CARISONBAKIYE  ") + SqlLiteVariable._FROM + mLogoTigerTableName.CSROLL + " AS R ") + " INNER JOIN " + mLogoTigerTableName.CLCARD + " AS C ON C.LOGICALREF=R.CARDREF ") + " LEFT JOIN " + mLogoTigerTableName.LG_SLSMAN + " AS S ON R.SALESMANREF=S.LOGICALREF ") + " LEFT JOIN " + getBalanceSql(mLogoTigerTableName.CLTOTFIL) + " AS CT ON C.LOGICALREF=CT .CARDREF ") + " WHERE R.LOGICALREF=" + i2).withProcessType(ProcessType.PRINTHEADER).build();
    }

    
    public TigerSelectResult getPrintChequeDetail(int i2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((((" SELECT C.PORTFOYNO , C.SETDATE , C.DUEDATE , C.SPECODE , C.CYPHCODE ,  C.MUHABIR , C.NEWSERINO , C.CITY , C.KEFIL , C.OWING , C.STAMP , C.AMOUNT , ") + " C.BANKNAME , C.BNBRANCHNO , C.BNACCOUNTNO ") + SqlLiteVariable._FROM + mLogoTigerTableName.CSCARD + " AS C ") + " WHERE C.LOGICALREF IN (SELECT CSREF FROM " + mLogoTigerTableName.CSTRANS + " WHERE ROLLREF=" + i2 + ")").withProcessType(ProcessType.PRINTDETAIL).build();
    }

    
    public TigerSelectResult getPrintWhTransferHeader(int i2) {
        String beforeBalanceSql = getBeforeBalanceSql(Invoice.class, i2);
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((((((((((((((((((((((((((((((((" SELECT C.TCKNO,C.ISPERSCOMP,'' AS 'TOTALTEXT',I.TRCODE, I.LOGICALREF AS INVOICEREF,I.FICHENO, I.DATE_, I.DOCODE , I.SPECODE , I.CYPHCODE,  I.SHIPINFOREF,I.RECVREF,I.CLIENTREF,I.GUID, C.CODE AS CCODE , C.DEFINITION_ AS CDEFINITION , C.SPECODE AS CSPECODE , C.CYPHCODE AS CCYPHCODE, C.ADDR1 , ") + " C.ADDR2 , C.CITY , C.DISTRICT , C.TELNRS1 , C.TELNRS2 , C.FAXNR , C.TAXNR ,C.TAXOFFCODE,C.TOWN, ") + " C.TAXOFFICE , C.INCHARGE , C.DISCRATE , C.EMAILADDR , C.TRADINGGRP AS CTRADINGGRP , C.DEFINITION2 , ") + " (SELECT CODE FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RCODE , ") + " (SELECT DEFINITION_ FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RNAME , ") + " (SELECT ADDR1 FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RADDR1 , ") + " (SELECT ADDR2 FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RADDR2 , ") + " (SELECT CITY FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RCITY , ") + " (SELECT TOWN FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=I.RECVREF) AS RTOWN , ") + " (SELECT ADDR1 FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS SADDR1 , ") + " (SELECT ADDR2 FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS SADDR2 , ") + " (SELECT CITY FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS SCITY , ") + " (SELECT TOWN FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS STOWN , ") + " ISNULL(CT.DEBIT,0) AS DEBIT , ISNULL(CT.CREDIT,0) AS CREDIT ,") + " (ISNULL(CT.DEBIT, 0) - ISNULL(CT.CREDIT, 0)) AS BAKIYE, ") + " I.TOTALVAT , I.NETTOTAL , I.TOTALDISCOUNTS , I.GROSSTOTAL , I.GENEXP1 , ") + " I.DESTINDEX AS AMBAR, I.COMPBRANCH AS ISYERI, I.COMPDEPARTMENT AS BOLUM, I.COMPFACTORY AS FABRIKA, ") + " I.GENEXP2 , I.GENEXP3 , I.GENEXP4 , I.SHPTYPCOD , I.SHPAGNCOD , I.TRADINGGRP, I.TRRATE, I.TRCURR, ") + " I.SOURCEINDEX , S.CODE AS SCODE , ") + " S.DEFINITION_ AS SDEFINITION , P.CODE AS PCODE,P.DEFINITION_ AS PDEFINITION , P.LOGICALREF AS PAYPLANREF, ") + " F.CODE AS FCODE , F.NAME AS FNAME ,") + " (SELECT TOP 1 ISNULL(AMOUNT,0) FROM " + mLogoTigerTableName.CLFLINE + " WHERE MODULENR IN (5,10,6) AND CLIENTREF=I.CLIENTREF AND DATE_ BETWEEN '" + DateAndTimeUtils.nowDateSqlPrint() + " 00:00:00' AND '" + DateAndTimeUtils.nowDateSqlPrint() + " 23:59:59'  ORDER BY DATE_ DESC,FTIME DESC,LOGICALREF DESC ) AS CARISONBAKIYE , ") + beforeBalanceSql) + "(SELECT LATITUTE FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS LATITUTE ,") + "(SELECT LATITUTE FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=I.SHIPINFOREF) AS LONGITUDE ") + SqlLiteVariable._FROM + mLogoTigerTableName.STFICHE + " AS I ") + " INNER JOIN " + mLogoTigerTableName.CLCARD + " AS C ON I.CLIENTREF=C.LOGICALREF ") + " LEFT JOIN " + mLogoTigerTableName.LG_SLSMAN + " AS S ON I.SALESMANREF=S.LOGICALREF ") + " LEFT JOIN " + mLogoTigerTableName.PAYPLANS + " AS P ON P.LOGICALREF=I .PAYDEFREF ") + " LEFT JOIN " + mLogoTigerTableName.SHIPINFO + " AS F ON F.LOGICALREF=I.SHIPINFOREF ") + " LEFT JOIN " + getBalanceSql(mLogoTigerTableName.CLTOTFIL) + " AS CT ON C.LOGICALREF=CT.CARDREF AND TOTTYP=1") + " WHERE I.IOCODE=2 AND I.GRPCODE=3 AND I.TRCODE=25 AND I.LOGICALREF=" + i2).withProcessType(ProcessType.GETONLINEPRICE).build();
    }

    
    public TigerSelectResult getPrintWhTransferDetail(int i2, int i3) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((((((((((((((((((((((((((((((((((((((((((" SELECT S.INVOICEREF,S.LOGICALREF ,S.STFICHELNNO , S.LINETYPE, I.CODE, I.NAME, I.NAME3 AS NAME2, D.CODE AS DCODE, D.DEFINITION_ AS DNAME ,O.FICHENO , P.CODE AS PCODE,P.DEFINITION_ AS PDEFINITION , U.CODE AS UCODE ,U.NAME AS UNAME ,I.GTIPCODE, S.STOCKREF, S.UOMREF, S.USREF, ") + " S.SOURCEINDEX , S.SPECODE , S.DELVRYCODE , S.AMOUNT , ") + " S.PRICE , S.TOTAL , S.DISCPER , S.VAT , S.VATAMNT , S.VATMATRAH , S.LINEEXP ,S.VARIANTREF,S.GLOBTRANS, ") + " (SELECT TOP 1 BARCODE FROM " + mLogoTigerTableName.UNITBARCODE + " WHERE ITEMREF=S.STOCKREF AND S.UOMREF=UNITLINEREF) AS Barkod") + " , SP.ICUSTSUPCODE , SP.ICUSTSUPNAME, SV.CODE AS VCODE,SV.NAME AS VNAME , I.TRACKTYPE , I.LOGICALREF AS ITEMREF, S.GUID , ") + " COALESCE((UNIT.WEIGHT / WGT.CONVFACT1) * WGT.CONVFACT2, 0.00) AS WEIGHT, ") + " COALESCE((UNIT.GROSSWEIGHT / GRSWGT.CONVFACT1) * GRSWGT.CONVFACT2, 0.00) AS GROSSWEIGHT, ") + " COALESCE((UNIT.VOLUME_ / VOL.CONVFACT1) * VOL.CONVFACT2 / 1000, 0.00) AS VOLUME, ") + " CAST(UNIT.WIDTH AS VARCHAR(MAX)) + ' ' + ISNULL(W.CODE, '') AS WIDTH, ") + " CAST(UNIT.LENGTH AS VARCHAR(MAX)) + ' ' + ISNULL(L.CODE, '') AS LENGTH, ") + " CAST(UNIT.HEIGHT AS VARCHAR(MAX)) + ' ' + ISNULL(H.CODE, '') AS HEIGHT ") + SqlLiteVariable._FROM + mLogoTigerTableName.STLINE + " AS S ") + " LEFT JOIN " + mLogoTigerTableName.ITMUNITA + " AS UNIT ON UNIT.ITEMREF = S.STOCKREF AND UNIT.UNITLINEREF = S.UOMREF ") + " LEFT JOIN " + mLogoTigerTableName.ITEMS + " AS I ON I.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + mLogoTigerTableName.DECARDS + " AS D ON D.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + mLogoTigerTableName.ORFICHE + " AS O ON O.LOGICALREF=S .ORDFICHEREF ") + " LEFT JOIN " + mLogoTigerTableName.PAYPLANS + " AS P ON P.LOGICALREF=S .PAYDEFREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS U ON U.LOGICALREF=S.UOMREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS WGT ON WGT.LOGICALREF = UNIT.WEIGHTREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS GRSWGT ON GRSWGT.LOGICALREF = UNIT.GROSSWGHTREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS VOL ON VOL.LOGICALREF = UNIT.VOLUMEREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS W ON W.LOGICALREF = UNIT.WIDTHREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS L ON L.LOGICALREF = UNIT.LENGTHREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS H ON H.LOGICALREF = UNIT.HEIGHTREF ") + " LEFT JOIN " + mLogoTigerTableName.SUPPASGN + " AS SP ON SP.ITEMREF=I .LOGICALREF AND SP.CLIENTREF =" + i3) + " LEFT JOIN " + mLogoTigerTableName.VARIANT + " AS SV ON SV.ITEMREF=S.VARIANTREF ") + " WHERE S.LINETYPE NOT IN(4,2) AND S.IOCODE=2 AND S.TRCODE=25 AND S.STFICHEREF=" + i2) + " UNION ") + " SELECT S.INVOICEREF,S.LOGICALREF ,S.STFICHELNNO , S.LINETYPE, I.CODE ,I.DEFINITION_ AS NAME, '' AS NAME2, D.CODE AS DCODE, D.DEFINITION_ AS DNAME ,O.FICHENO ,") + " P.CODE AS PCODE,P.DEFINITION_ AS PDEFINITION , U.CODE AS UCODE ,U.NAME AS UNAME , '' AS GTIPCODE, S.STOCKREF, S.UOMREF, S.USREF, ") + " S.SOURCEINDEX , S.SPECODE , S.DELVRYCODE , S.AMOUNT , ") + " S.PRICE , S.TOTAL , S.DISCPER , S.VAT , S.VATAMNT , S.VATMATRAH , S.LINEEXP ,S.VARIANTREF,S.GLOBTRANS, '' AS Barkod  ") + " , SP.ICUSTSUPCODE , SP.ICUSTSUPNAME, SV.CODE AS VCODE,SV.NAME AS VNAME ,0 AS TRACKTYPE , I.LOGICALREF AS ITEMREF, S.GUID , 0 AS WEIGHT, 0 AS GROSSWEIGHT, 0 AS VOLUME, '' AS WIDTH, '' AS LENGTH, '' AS HEIGHT ") + SqlLiteVariable._FROM + mLogoTigerTableName.STLINE + " AS S ") + " LEFT JOIN " + mLogoTigerTableName.SRVCARD + " AS I ON I.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + mLogoTigerTableName.DECARDS + " AS D ON D.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + mLogoTigerTableName.ORFICHE + " AS O ON O.LOGICALREF=S .ORDFICHEREF ") + " LEFT JOIN " + mLogoTigerTableName.PAYPLANS + " AS P ON P.LOGICALREF=S .PAYDEFREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS U ON U.LOGICALREF=S.UOMREF ") + " LEFT JOIN " + mLogoTigerTableName.SUPPASGN + " AS SP ON SP.ITEMREF=I .LOGICALREF ") + " LEFT JOIN " + mLogoTigerTableName.VARIANT + " AS SV ON SV.ITEMREF=S.VARIANTREF ") + " WHERE S.LINETYPE=4 AND S.IOCODE=2 AND S.TRCODE=25 AND S.STFICHEREF=" + i2).withProcessType(ProcessType.PRINTDETAIL).build();
    }

    
    public TigerSelectResult getPrintItemStock(int i2, int i3) {
        String str;
        String str2 = (" SELECT IV.STOCKREF,I.CODE,I.NAME,I.CARDTYPE,IV.INVENNO, ROUND(SUM(ISNULL(IV.ONHAND, 0)), 7) AS ONHAND , GETDATE() AS DATE_" + SqlLiteVariable._FROM + mLogoTigerTableName.STINVTOT) + " AS IV," + mLogoTigerTableName.ITEMS + " AS I WHERE I.LOGICALREF=IV.STOCKREF AND I.ACTIVE = 0 AND (IV.DATE_ > CONVERT(dateTime, '5-19-1919', 101)) AND (IV.DATE_ <= " + ErpCreator.getInstance().getmBaseErp().getStockTotalsWhereDateExpression() + ") ";
        if (i2 != -1) {
            str2 = str2 + " AND IV.STOCKREF=" + i2;
        }
        if (i3 == -1) {
            str = str2 + " AND IV.INVENNO<>-1";
        } else {
            str = str2 + " AND IV.INVENNO=" + i3;
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((str + " GROUP BY IV.INVENNO , I.CODE , I.NAME,IV.STOCKREF,I.CARDTYPE") + " HAVING ROUND(SUM(ISNULL(IV.ONHAND, 0)), 7) > 0").withProcessType(ProcessType.PRINTHEADER).build();
    }

    
    public TigerSelectResult getPrintDispatchHeader(int i2) {
        String beforeBalanceSql = getBeforeBalanceSql(Invoice.class, i2);
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(((((((((((((((((((((((((((((((((" SELECT '' AS TOTALTEXT,SF.LOGICALREF AS INVOICEREF,SF.FICHENO ,SF.FTIME, SF.DATE_ , SF.DOCODE , SF.SPECODE , SF.CYPHCODE ,SF.SHIPINFOREF,SF.RECVREF,SF.CLIENTREF,SF.TRRATE, SF.TRCURR, SF.GUID, C.CODE AS CCODE , C.DEFINITION_ AS CDEFINITION , C.SPECODE AS CSPECODE , C.CYPHCODE AS CCYPHCODE, C.ADDR1 , ") + " C.ADDR2 , C.CITY , C.DISTRICT , C.TELNRS1 , C.TELNRS2 , C.FAXNR , C.TAXNR ,C.TAXOFFCODE,C.TOWN, ") + " C.TAXOFFICE , C.INCHARGE , C.DISCRATE , C.EMAILADDR , C.TRADINGGRP AS CTRADINGGRP, ") + " (SELECT CODE FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=SF.RECVREF) AS RCODE , ") + " (SELECT DEFINITION_ FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=SF.RECVREF) AS RNAME , ") + " (SELECT ADDR1 FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=SF.RECVREF) AS RADDR1 , ") + " (SELECT ADDR2 FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=SF.RECVREF) AS RADDR2 , ") + " (SELECT CITY FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=SF.RECVREF) AS RCITY , ") + " (SELECT TOWN FROM " + mLogoTigerTableName.CLCARD + " WHERE LOGICALREF=SF.RECVREF) AS RTOWN , ") + " (SELECT ADDR1 FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=SF.SHIPINFOREF) AS SADDR1 , ") + " (SELECT ADDR2 FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=SF.SHIPINFOREF) AS SADDR2 , ") + " (SELECT CITY FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=SF.SHIPINFOREF) AS SCITY , ") + " (SELECT TOWN FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=SF.SHIPINFOREF) AS STOWN , ") + " ISNULL(CT.DEBIT,0) AS DEBIT , ISNULL(CT.CREDIT,0) AS CREDIT ,") + " (ISNULL(CT.DEBIT, 0) - ISNULL(CT.CREDIT, 0)) AS BAKIYE, ") + " SF.TOTALVAT , SF.NETTOTAL , SF.TOTALDISCOUNTS , SF.GROSSTOTAL , SF.GENEXP1 , ") + " SF.GENEXP2 , SF.GENEXP3 , SF.GENEXP4 , SF.SHPTYPCOD , SF.SHPAGNCOD , SF.TRADINGGRP , ") + " SF.SOURCEINDEX , S.CODE AS SCODE , ") + " S.DEFINITION_ AS SDEFINITION , P.CODE AS PCODE,P.DEFINITION_ AS PDEFINITION ,P.LOGICALREF AS PAYPLANREF, ") + " F.CODE AS FCODE , F.NAME AS FNAME , ") + " (SELECT ISNULL(SUM(NETTOTAL),0) FROM " + mLogoTigerTableName.INVOICE + " WHERE TRCODE=8 AND CLIENTREF=SF.CLIENTREF AND DATE_ < '" + DateAndTimeUtils.nowDateSqlPrint() + " 00:00:00' ) AS CARIBAKIYE , ") + " (SELECT TOP 1 ISNULL(AMOUNT,0) FROM " + mLogoTigerTableName.CLFLINE + " WHERE MODULENR IN (5,10,6) AND CLIENTREF=SF.CLIENTREF AND DATE_ BETWEEN '" + DateAndTimeUtils.nowDateSqlPrint() + " 00:00:00' AND '" + DateAndTimeUtils.nowDateSqlPrint() + " 23:59:59'  ORDER BY DATE_ DESC,FTIME DESC,LOGICALREF DESC ) AS CARISONBAKIYE , ") + beforeBalanceSql) + "(SELECT LATITUTE FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=SF.SHIPINFOREF) AS LATITUTE ,") + "(SELECT LATITUTE FROM " + mLogoTigerTableName.SHIPINFO + " WHERE LOGICALREF=SF.SHIPINFOREF) AS LONGITUDE , ") + " C.TCKNO ") + SqlLiteVariable._FROM + mLogoTigerTableName.STFICHE + " AS SF ") + " INNER JOIN " + mLogoTigerTableName.CLCARD + " AS C ON SF.CLIENTREF=C .LOGICALREF ") + " LEFT JOIN " + mLogoTigerTableName.LG_SLSMAN + " AS S ON SF.SALESMANREF=S.LOGICALREF ") + " LEFT JOIN " + mLogoTigerTableName.PAYPLANS + " AS P ON P.LOGICALREF=SF .PAYDEFREF ") + " LEFT JOIN " + mLogoTigerTableName.SHIPINFO + " AS F ON F.LOGICALREF=SF.SHIPINFOREF ") + " LEFT JOIN " + getBalanceSql(mLogoTigerTableName.CLTOTFIL) + " AS CT ON C.LOGICALREF=CT.CARDREF AND TOTTYP=1") + " WHERE SF.LOGICALREF=" + i2).withProcessType(ProcessType.PRINTHEADER).build();
    }

    
    public TigerSelectResult getPrintDispatchDetail(int i2, int i3) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(((((((((((((((((((((((((((((((((((((((((((" SELECT S.STFICHEREF,S.LOGICALREF ,S.STFICHELNNO , S.LINETYPE, I.CODE, I.NAME, I.NAME3 AS NAME2, D.CODE AS DCODE, D.DEFINITION_ AS DNAME ,O.FICHENO , P.CODE AS PCODE,P.DEFINITION_ AS PDEFINITION , U.CODE AS UCODE ,U.NAME AS UNAME , I.GTIPCODE, S.STOCKREF, S.UOMREF, S.USREF,  ") + " S.SOURCEINDEX , S.SPECODE , S.DELVRYCODE , S.AMOUNT , ") + " S.PRICE , S.TOTAL , S.DISCPER , S.VAT , S.VATAMNT , S.VATMATRAH , S.LINEEXP,S.VARIANTREF, ") + " (SELECT TOP 1 BARCODE FROM " + mLogoTigerTableName.UNITBARCODE + " WHERE ITEMREF=S.STOCKREF AND S.UOMREF=UNITLINEREF) AS Barkod") + " , SP.ICUSTSUPCODE , SP.ICUSTSUPNAME, SV.CODE AS VCODE,SV.NAME AS VNAME, I.TRACKTYPE , I.LOGICALREF AS ITEMREF, S.GUID, ") + " COALESCE((UNIT.WEIGHT / WGT.CONVFACT1) * WGT.CONVFACT2, 0.00) AS WEIGHT, ") + " COALESCE((UNIT.GROSSWEIGHT / GRSWGT.CONVFACT1) * GRSWGT.CONVFACT2, 0.00) AS GROSSWEIGHT, ") + " COALESCE((UNIT.VOLUME_ / VOL.CONVFACT1) * VOL.CONVFACT2 / 1000, 0.00) AS VOLUME, ") + " CAST(UNIT.WIDTH AS VARCHAR(MAX)) + ' ' + ISNULL(W.CODE, '') AS WIDTH, ") + " CAST(UNIT.LENGTH AS VARCHAR(MAX)) + ' ' + ISNULL(L.CODE, '') AS LENGTH, ") + " CAST(UNIT.HEIGHT AS VARCHAR(MAX)) + ' ' + ISNULL(H.CODE, '') AS HEIGHT ") + SqlLiteVariable._FROM + mLogoTigerTableName.STLINE + " AS S ") + " LEFT JOIN " + mLogoTigerTableName.ITMUNITA + " AS UNIT ON UNIT.ITEMREF = S.STOCKREF AND UNIT.UNITLINEREF = S.UOMREF ") + " LEFT JOIN " + mLogoTigerTableName.ITEMS + " AS I ON I.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + mLogoTigerTableName.DECARDS + " AS D ON D.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + mLogoTigerTableName.ORFICHE + " AS O ON O.LOGICALREF=S .ORDFICHEREF ") + " LEFT JOIN " + mLogoTigerTableName.PAYPLANS + " AS P ON P.LOGICALREF=S .PAYDEFREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS U ON U.LOGICALREF=S.UOMREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS WGT ON WGT.LOGICALREF = UNIT.WEIGHTREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS GRSWGT ON GRSWGT.LOGICALREF = UNIT.GROSSWGHTREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS VOL ON VOL.LOGICALREF = UNIT.VOLUMEREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS W ON W.LOGICALREF = UNIT.WIDTHREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS L ON L.LOGICALREF = UNIT.LENGTHREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS H ON H.LOGICALREF = UNIT.HEIGHTREF ") + " LEFT JOIN " + mLogoTigerTableName.SUPPASGN + " AS SP ON SP.ITEMREF=I .LOGICALREF AND SP.CLIENTREF =" + i3) + " LEFT JOIN " + mLogoTigerTableName.VARIANT + " AS SV ON SV.ITEMREF=S.VARIANTREF ") + " WHERE S.LINETYPE NOT IN(4,2) AND S.STFICHEREF=" + i2) + " UNION ") + " SELECT S.STFICHEREF,S.LOGICALREF ,S.STFICHELNNO , S.LINETYPE, I.CODE ,I.DEFINITION_ AS NAME, '' AS NAME2, D.CODE AS DCODE, D.DEFINITION_ AS DNAME ,O.FICHENO ,") + " P.CODE AS PCODE,P.DEFINITION_ AS PDEFINITION , U.CODE AS UCODE ,U.NAME AS UNAME ,'' AS GTIPCODE, S.STOCKREF, S.UOMREF, S.USREF,  ") + " S.SOURCEINDEX , S.SPECODE , S.DELVRYCODE , S.AMOUNT , ") + " S.PRICE , S.TOTAL , S.DISCPER , S.VAT , S.VATAMNT , S.VATMATRAH , S.LINEEXP,S.VARIANTREF, '' AS Barkod ") + " , SP.ICUSTSUPCODE , SP.ICUSTSUPNAME, SV.CODE AS VCODE,SV.NAME AS VNAME , 0 AS TRACKTYPE , I.LOGICALREF AS ITEMREF, S.GUID, 0 AS WEIGHT, 0 AS GROSSWEIGHT, ") + " 0 AS VOLUME, '' AS WIDTH, '' AS LENGTH, '' AS HEIGHT ") + SqlLiteVariable._FROM + mLogoTigerTableName.STLINE + " AS S ") + " LEFT JOIN " + mLogoTigerTableName.SRVCARD + " AS I ON I.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + mLogoTigerTableName.DECARDS + " AS D ON D.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + mLogoTigerTableName.ORFICHE + " AS O ON O.LOGICALREF=S .ORDFICHEREF ") + " LEFT JOIN " + mLogoTigerTableName.PAYPLANS + " AS P ON P.LOGICALREF=S .PAYDEFREF ") + " LEFT JOIN " + mLogoTigerTableName.UNITSETL + " AS U ON U.LOGICALREF=S.UOMREF ") + " LEFT JOIN " + mLogoTigerTableName.SUPPASGN + " AS SP ON SP.ITEMREF=I .LOGICALREF ") + " LEFT JOIN " + mLogoTigerTableName.VARIANT + " AS SV ON SV.ITEMREF=S.VARIANTREF ") + " WHERE S.LINETYPE=4 AND S.STFICHEREF=" + i2).withProcessType(ProcessType.PRINTDETAIL).build();
    }

    
    public TigerSelectResult getPrintDispatchDiscount(int i2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT GLOBTRANS , STFICHELNNO, LINETYPE,DISCPER,TOTAL , LOGICALREF FROM " + mLogoTigerTableName.STLINE + " WHERE STFICHEREF=" + i2).withOrderBy("ORDER BY STFICHELNNO").withProcessType(ProcessType.PRINTDISCOUNT).build();
    }

    
    public TigerSelectResult getPrintCaseHeader(int i2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql((((" SELECT CL.AMOUNT,CL.FICHENO , CL.DATE_ , CL.SPECODE , CL.CYPHCODE , CL.LINEEXP, S.CODE AS SCODE, S.DEFINITION_ AS SDEFINITION " + SqlLiteVariable._FROM + mLogoTigerTableName.KSLINES + " AS CL ") + " INNER JOIN " + mLogoTigerTableName.CLFLINE + " AS CF ON CF.SOURCEFREF=CL.LOGICALREF AND CF.MODULENR=10 AND CF.TRCODE=1 ") + " LEFT JOIN " + mLogoTigerTableName.LG_SLSMAN + " AS S ON CF.SALESMANREF=S.LOGICALREF ") + " WHERE CL.LOGICALREF=" + i2).withProcessType(ProcessType.PRINTHEADER).build();
    }

    
    public TigerSelectResult getPrintCaseDetail(int i2) {
        String beforeBalanceSql = getBeforeBalanceSql(CaseCash.class, i2);
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(((((((((((((" SELECT CL.AMOUNT , CL.DOCODE ,C.LOGICALREF AS CLIENTREF,  C.CODE AS CCODE , C.DEFINITION_ AS CDEFINITION , C.SPECODE AS CSPECODE , C.CYPHCODE AS CCYPHCODE, C.ADDR1 , ") + " C.ADDR2 , C.CITY , C.DISTRICT , C.TELNRS1 , C.TELNRS2 , C.FAXNR , C.TAXNR , C.TAXOFFCODE,C.TOWN, ") + " C.TAXOFFICE , C.INCHARGE , C.DISCRATE , C.EMAILADDR , C.TRADINGGRP AS CTRADINGGRP ,") + " ISNULL(CT.DEBIT,0) AS DEBIT , ISNULL(CT.CREDIT,0) AS CREDIT ,") + " (ISNULL(CT.DEBIT,0)-ISNULL(CT.CREDIT,0)) AS DEBITACCOUNT , ") + " (ISNULL(CT.CREDIT,0)-ISNULL(CT.DEBIT,0)) AS CREDITACCOUNT, C.TCKNO,C .ISPERSCOMP  , ") + " (SELECT ISNULL(SUM(NETTOTAL),0) FROM " + mLogoTigerTableName.INVOICE + " WHERE TRCODE=8 AND CLIENTREF=CL.CLIENTREF AND DATE_ < '" + DateAndTimeUtils.nowDateSqlPrint() + " 00:00:00' ) AS CARIBAKIYE , ") + beforeBalanceSql) + " (SELECT TOP 1 ISNULL(AMOUNT,0) FROM " + mLogoTigerTableName.CLFLINE + " WHERE MODULENR IN (5,10,6) AND CLIENTREF=CL.CLIENTREF AND DATE_ BETWEEN '" + DateAndTimeUtils.nowDateSqlPrint() + " 00:00:00' AND '" + DateAndTimeUtils.nowDateSqlPrint() + " 23:59:59'  ORDER BY DATE_ DESC,FTIME DESC,LOGICALREF DESC ) AS CARISONBAKIYE  ") + SqlLiteVariable._FROM + mLogoTigerTableName.CLFLINE + " AS CL ") + " INNER JOIN " + mLogoTigerTableName.CLCARD + " AS C ON C.LOGICALREF=CL .CLIENTREF ") + " LEFT JOIN " + getBalanceSql(mLogoTigerTableName.CLTOTFIL) + " AS CT ON C.LOGICALREF=CT.CARDREF AND CT.TOTTYP=1 ") + " WHERE CL.MODULENR=10 AND CL.TRCODE=1 AND CL.SOURCEFREF=" + i2).withProcessType(ProcessType.PRINTDETAIL).build();
    }

    public static BaseSelectResult getOrderDetailForOrderView(int i2) {
        LogoTigerTableName logoTigerTableName = new LogoTigerTableName(ErpCreator.getInstance().getmBaseErp().getUser().getDbName(), ErpCreator.getInstance().getmBaseErp().getUser().getFirmNr(), ErpCreator.getInstance().getmBaseErp().getUser().getPeridodNr());
        return TigerSelectResult.newBuilder().withSql((((((((((((((((((((((((" SELECT S.ORDFICHEREF AS ID, I.CODE ,I.NAME ,  U.NAME AS UNIT ,  ") + " S.AMOUNT , ") + " S.PRICE , S.VAT , S.LINENET AS NETTOTAL ") + SqlLiteVariable._FROM + logoTigerTableName.ORFLINE + " AS S ") + " LEFT JOIN " + logoTigerTableName.ITEMS + " AS I ON I.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + logoTigerTableName.DECARDS + " AS D ON D.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + logoTigerTableName.PAYPLANS + " AS P ON P.LOGICALREF=S .PAYDEFREF ") + " LEFT JOIN " + logoTigerTableName.UNITSETL + " AS U ON U.LOGICALREF=S.UOMREF ") + " LEFT JOIN " + logoTigerTableName.SUPPASGN + " AS SP ON SP.ITEMREF=I .LOGICALREF ") + " LEFT JOIN " + logoTigerTableName.VARIANT + " AS SV ON SV.ITEMREF=S.VARIANTREF ") + " WHERE S.LINETYPE NOT IN(2,4) AND S.ORDFICHEREF=" + i2) + " UNION ") + " SELECT S.ORDFICHEREF AS ID, I.CODE ,I.DEFINITION_ , ") + " U.NAME AS UNIT ,  ") + " S.AMOUNT , ") + " S.PRICE , S.VAT , S.LINENET AS NETTOTAL ") + SqlLiteVariable._FROM + logoTigerTableName.ORFLINE + " AS S ") + " LEFT JOIN " + logoTigerTableName.SRVCARD + " AS I ON I.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + logoTigerTableName.DECARDS + " AS D ON D.LOGICALREF=S.STOCKREF ") + " LEFT JOIN " + logoTigerTableName.PAYPLANS + " AS P ON P.LOGICALREF=S .PAYDEFREF ") + " LEFT JOIN " + logoTigerTableName.UNITSETL + " AS U ON U.LOGICALREF=S.UOMREF ") + " LEFT JOIN " + logoTigerTableName.SUPPASGN + " AS SP ON SP.ITEMREF=I .LOGICALREF ") + " LEFT JOIN " + logoTigerTableName.VARIANT + " AS SV ON SV.ITEMREF=S.VARIANTREF ") + " WHERE S.LINETYPE=4 AND S.ORDFICHEREF=" + i2).withProcessType(ProcessType.GETORDERDETAIL).withDatabaseSave(true).withTableDelete(false).build();
    }

    public TigerSelectResult getRiskCalculateQuery(int i2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.get_risk_calculate, mLogoTigerTableName.CLRNUMS, Integer.valueOf(i2))).withProcessType(ProcessType.GETRISKCALCULATE).build();
    }

    public BaseSelectResult getCustomerRiskInfo(int i2) {
        return TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.get_customer_risk_info, mLogoTigerTableName.CLRNUMS, Integer.valueOf(i2))).withProcessType(ProcessType.GETRISKINFO).build();
    }

    public TigerSelectResult getCustomerNowAndBeforeBalance(int i2, Class cls, int i3) {
        String sb = SqlLiteVariable._SELECT +
                getBeforeBalanceSql(cls, i3) +
                " CT.BAKIYE AS BALANCE FROM " +
                getBalanceSql(mLogoTigerTableName.CLTOTFIL) + " AS CT " +
                "WHERE CT.CARDREF = " + i2;
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb).withProcessType(ProcessType.GETCUSTOMERBEFOREBALACE).build();
    }

    public static TigerSelectResult getFicheDuplicateControlSql(DataObjectType dataObjectType, String str) {
        String str2;
        User user = ErpCreator.getInstance().getmBaseErp().getUser();
        String format = String.format("%sLG_%s_%s_", user.getDbName(), user.getFirmNr(), user.getPeridodNr());
        if (dataObjectType == DataObjectType.ADDORDER) {
            str2 = format + "ORFICHE";
        } else if (dataObjectType == DataObjectType.ADDINVOICE) {
            str2 = format + "INVOICE";
        } else if (dataObjectType == DataObjectType.ADDDISPATCH || dataObjectType == DataObjectType.ADDWHTRANSFER) {
            str2 = format + "STFICHE";
        } else if (dataObjectType == DataObjectType.ADDCASH || dataObjectType == DataObjectType.ADDCREDIT) {
            str2 = format + "CLFICHE";
        } else if (dataObjectType == DataObjectType.ADDCHEQUE || dataObjectType == DataObjectType.ADDDEED) {
            str2 = format + "CSROLL";
        } else if (dataObjectType == DataObjectType.ADDCASECASH) {
            str2 = format + "KSLINES";
        } else if (dataObjectType == DataObjectType.ADDREQEST) {
            str2 = format + "DEMANDFICHE";
        } else {
            str2 = "";
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(TextUtils.isEmpty(str2) ? "" : ContextUtils.getmContext().getString(R.string.get_fiche_duplicate, str2, str)).withProcessType(ProcessType.GETFICHEDUPLICATE).build();
    }

    public TigerSelectResult getItemImage(int i2) {
        TigerSelectResult.TigerBuilder newBuilder = TigerSelectResult.newBuilder();
        LogoTigerTableName logoTigerTableName = mLogoTigerTableName;
        return (TigerSelectResult) newBuilder.withSql(ContextUtils.formatStringEnglish(R.string.get_logo_image, logoTigerTableName.FIRMDOC, logoTigerTableName.ITEMS, Integer.valueOf(i2), 11, 20, 0)).withProcessType(ProcessType.GETIMAGE).build();
    }

    public TigerSelectResult getCustomerImage(int i2) {
        TigerSelectResult.TigerBuilder newBuilder = TigerSelectResult.newBuilder();
        LogoTigerTableName logoTigerTableName = mLogoTigerTableName;
        return (TigerSelectResult) newBuilder.withSql(ContextUtils.formatStringEnglish(R.string.get_logo_image, logoTigerTableName.FIRMDOC, logoTigerTableName.CLCARD, Integer.valueOf(i2), 11, 21, 0)).withProcessType(ProcessType.GETIMAGE).build();
    }

    public TigerSelectResult getOrderStatus(int i2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.get_order_fiche_status, mLogoTigerTableName.ORFICHE, Integer.valueOf(i2))).withProcessType(ProcessType.GETORDERFICHESTATUS).build();
    }

    public TigerSelectResult getOrderState(int i2) {
        TigerSelectResult.TigerBuilder newBuilder = TigerSelectResult.newBuilder();
        LogoTigerTableName logoTigerTableName = mLogoTigerTableName;
        return (TigerSelectResult) newBuilder.withSql(ContextUtils.formatStringEnglish(R.string.tiger_get_order_state, logoTigerTableName.ORFICHE, logoTigerTableName.FICHESTATUS, Integer.valueOf(i2))).withProcessType(ProcessType.GETORDERFICHESTATE).build();
    }

    public BaseSelectResult getUpdateCustomerImageInc(int i2) {
        return TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.tiger_update_customer_inc, mLogoTigerTableName.CLCARD, Integer.valueOf(i2))).withProcessType(ProcessType.SUCSESS).build();
    }

    public TigerSelectResult getInsertCustomerImage(int i2, FicheImageProp ficheImageProp) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.tiger_insert_customer_image, mLogoTigerTableName.FIRMDOC, Integer.valueOf(i2), StringUtils.stringAddHexStart(CompressUtil.bytesToHex(ficheImageProp.getImageArray())))).withProcessType(ProcessType.INSERTCUSTOMERIMAGE).build();
    }

    public TigerSelectResult getDeleteCustomerImage(int i2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.tiger_delete_customer_image, mLogoTigerTableName.FIRMDOC, Integer.valueOf(i2))).withProcessType(ProcessType.DELETECUSTOMERIMAGE).build();
    }

    public TigerSelectResult getCustomerPayTransSumForDueDate(int i2, String str) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.tiger_get_customer_pay_trans_sum_for_due_date, mLogoTigerTableName.PAYTRANS, Integer.valueOf(i2), str)).withProcessType(ProcessType.GETDUEDATETOTAL).build();
    }

    
    public TigerSelectResult insertCustomerGpsLocation(CustGpsInfo custGpsInfo) {
        if (getUser().getPanelVersion() < 18500) {
            return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(String.format("%s ; %s ; ", ContextUtils.getStringResource(R.string.app_delete_customer_gps_location_with_firmNr, Integer.valueOf(custGpsInfo.clientRef), getUser().getFirmNr()), ContextUtils.getStringResource(R.string.app_insert_customer_gps_location_with_firmNr, Integer.valueOf(custGpsInfo.clientRef), Double.valueOf(custGpsInfo.latitude), Double.valueOf(custGpsInfo.longtitude), custGpsInfo.clCode, custGpsInfo.clName, getUser().getFirmNr()))).withProcessType(ProcessType.SUCSESS).build();
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(String.format("%s ; %s ; ", ContextUtils.getStringResource(R.string.app_delete_customer_gps_location_with_shipInfo, Integer.valueOf(custGpsInfo.clientRef), getUser().getFirmNr(), Integer.valueOf(custGpsInfo.shippingRef)), ContextUtils.getStringResource(R.string.app_insert_customer_gps_location_with_shipInfo, Integer.valueOf(custGpsInfo.clientRef), Double.valueOf(custGpsInfo.latitude), Double.valueOf(custGpsInfo.longtitude), custGpsInfo.clCode, custGpsInfo.clName, getUser().getFirmNr(), Integer.valueOf(custGpsInfo.shippingRef)))).withProcessType(ProcessType.SUCSESS).build();
    }
    public TigerSelectResult insertVisit(VisitInfo visitInfo) {
        String clCode = getSqlHelper().getClCode(visitInfo.clRef);
        String clName = getSqlHelper().getClName(visitInfo.clRef);
        if (visitInfo.reason == null) {
            visitInfo.reason = "";
        }
        if (visitInfo.note == null) {
            visitInfo.note = "";
        }
        if (clName == null) {
            clName = "";
        }
        if (FDateUtils.getInstance().getFDateModel().getAppVersionOnWorProcess() == 0) {
            return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.app_insert_visit, DateAndTimeUtils.convertDateSqlDate(visitInfo.visitDate), Integer.valueOf(getUser().getUserId()), Integer.valueOf(visitInfo.clRef), 9, 0, 0, visitInfo.reason.replace("'", "''"), visitInfo.note.replace("'", "''"), 0, clCode, clName.replace("'", "''"), Integer.valueOf(visitInfo.shipAddrRef), TextUtils.isEmpty(visitInfo.enlem) ? IdManager.DEFAULT_VERSION_NAME : visitInfo.enlem, TextUtils.isEmpty(visitInfo.boylam) ? IdManager.DEFAULT_VERSION_NAME : visitInfo.boylam, Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())), Integer.valueOf(visitInfo.userTitle), CompressUtil.base64CompressImage(visitInfo.visitImage), org.springframework.util.StringUtils.isEmpty(visitInfo.startDate) ? null : org.springframework.util.StringUtils.quote(DateAndTimeUtils.convertDateSqlDate(visitInfo.startDate)), org.springframework.util.StringUtils.isEmpty(visitInfo.endDate) ? null : org.springframework.util.StringUtils.quote(DateAndTimeUtils.convertDateSqlDate(visitInfo.endDate)), Integer.valueOf(visitInfo.logicalRef))).withLogicalRef(visitInfo.id).withClCode(clCode).withClName(clName).withProcessType(ProcessType.INSERTVISIT).build();
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.app_insert_visit_withVersion, DateAndTimeUtils.convertDateSqlDate(visitInfo.visitDate), Integer.valueOf(getUser().getUserId()), Integer.valueOf(visitInfo.clRef), 9, 0, 0, visitInfo.reason.replace("'", "''"), visitInfo.note.replace("'", "''"), 0, clCode, clName.replace("'", "''"), Integer.valueOf(visitInfo.shipAddrRef), TextUtils.isEmpty(visitInfo.enlem) ? IdManager.DEFAULT_VERSION_NAME : visitInfo.enlem, TextUtils.isEmpty(visitInfo.boylam) ? IdManager.DEFAULT_VERSION_NAME : visitInfo.boylam, Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())), Integer.valueOf(visitInfo.userTitle), CompressUtil.base64CompressImage(visitInfo.visitImage), org.springframework.util.StringUtils.isEmpty(visitInfo.startDate) ? null : org.springframework.util.StringUtils.quote(DateAndTimeUtils.convertDateSqlDate(visitInfo.startDate)), org.springframework.util.StringUtils.isEmpty(visitInfo.endDate) ? null : org.springframework.util.StringUtils.quote(DateAndTimeUtils.convertDateSqlDate(visitInfo.endDate)), Integer.valueOf(visitInfo.logicalRef), BuildConfig.VERSION_NAME)).withLogicalRef(visitInfo.id).withClCode(clCode).withClName(clName).withProcessType(ProcessType.INSERTVISIT).build();
    }
    public TigerSelectResult insertTodoWorProc(TodoInfoDb todoInfoDb) {
        String clCode = getSqlHelper().getClCode(todoInfoDb.clRef);
        String clName = getSqlHelper().getClName(todoInfoDb.clRef);
        if (todoInfoDb.getDesc() == null) {
            todoInfoDb.setDesc("");
        }
        if (todoInfoDb.note == null) {
            todoInfoDb.note = "";
        }
        if (clName == null) {
            clName = "";
        }
        if (FDateUtils.getInstance().getFDateModel().getAppVersionOnWorProcess() == 0) {
            return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.app_insert_todoworproc, DateAndTimeUtils.convertDateSqlDate(todoInfoDb.begDate), Integer.valueOf(getUser().getUserId()), Integer.valueOf(todoInfoDb.clRef), 13, todoInfoDb.getDesc().replace("'", "''"), todoInfoDb.note.replace("'", "''"), clCode, clName.replace("'", "''"), Double.valueOf(todoInfoDb.latitude), Double.valueOf(todoInfoDb.longtitude), Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())), 0, 0)).withLogicalRef(todoInfoDb.logicalRef).withProcessType(ProcessType.INSERTWORPROCESS).build();
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.app_insert_todoworproc_withVersion, DateAndTimeUtils.convertDateSqlDate(todoInfoDb.begDate), Integer.valueOf(getUser().getUserId()), Integer.valueOf(todoInfoDb.clRef), 13, todoInfoDb.getDesc().replace("'", "''"), todoInfoDb.note.replace("'", "''"), clCode, clName.replace("'", "''"), Double.valueOf(todoInfoDb.latitude), Double.valueOf(todoInfoDb.longtitude), Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())), 0, 0, BuildConfig.VERSION_NAME)).withLogicalRef(todoInfoDb.logicalRef).withProcessType(ProcessType.INSERTWORPROCESS).build();
    }

    public String insertPenetrationHeader(Penetration penetration) {
        String formatStringEnglish = ContextUtils.formatStringEnglish(R.string.app_insert_penetration_header, Integer.valueOf(penetration.getPenetrationId()), Integer.valueOf(penetration.getClRef()), Integer.valueOf(getUser().getUserId()), penetration.getImage().getImageArray() == null ? "null" : "CONVERT(VARBINARY(MAX),' " + CompressUtil.base64CompressImage(penetration.getImage().getImageArray()) + "')", penetration.getNot().toString(), DateAndTimeUtils.convertDateSqlDate(penetration.getFicheDate()), penetration.getPnt_GUID());
        for (PenetrationLine penetrationLine : penetration.getPenetrations()) {
            if ((penetration.isExist() && penetrationLine.getExist().isSelect()) || (!penetration.isExist() && penetrationLine.getAmount().getDefinitionDouble() > 0.0d)) {
                formatStringEnglish = formatStringEnglish + insertPenetrationTrans(penetration, penetrationLine);
            }
        }
        return formatStringEnglish;
    }

    public TigerSelectResult getActivePeriod() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.tiger_get_active_period, getUser().getFirmNr())).withDatabaseSave(false).withTableDelete(false).withProcessType(ProcessType.GETACTIVEPERIOD).build();
    }

    public TigerSelectResult getUserAuthorizations(String str) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.app_get_user_authorizations, Integer.valueOf(getUser().getUserId()), getUser().getFirmNr(), str)).withTableDelete(true).withDatabaseSave(true).withProcessType(ProcessType.GETACTIVEPERIOD).build();
    }

    
    public String insertPenetrationTrans(Penetration penetration, PenetrationLine penetrationLine) {
        String str;
        String ficheStringProp = penetration.isExist() ? BuildConfig.NETSIS_DEMO_PASSWORD : penetrationLine.getAmount().toString();
        Integer valueOf = Integer.valueOf(penetration.getPenetrationId());
        Integer valueOf2 = Integer.valueOf(penetrationLine.getPenetrationDetailId());
        String convertIntToString = StringUtils.convertIntToString(penetration.getClRef());
        Integer valueOf3 = Integer.valueOf(getUser().getUserId());
        String pnt_GUID = penetration.getPnt_GUID();
        String ficheStringProp2 = penetrationLine.getPrice().toString();
        String ficheStringProp3 = penetrationLine.getNot().toString();
        if (penetrationLine.getImage().getImageArray() == null) {
            str = "null";
        } else {
            str = "CONVERT(VARBINARY(MAX),' " + CompressUtil.base64CompressImage(penetrationLine.getImage().getImageArray()) + "')";
        }
        return ContextUtils.formatStringEnglish(R.string.app_insert_penetration_trans, valueOf, valueOf2, convertIntToString, valueOf3, pnt_GUID, ficheStringProp2, ficheStringProp3, str, ficheStringProp, DateAndTimeUtils.convertDateSqlDate(penetration.getFicheDate()));
    }

    
    public String getFicheProcess(TigerServiceResult tigerServiceResult, int i2, double[] dArr, int i3) {
        if (FDateUtils.getInstance().getFDateModel().getAppVersionOnWorProcess() == 0) {
            return ContextUtils.formatStringEnglish(R.string.app_insert_fiche_process, DateAndTimeUtils.convertStringDate(tigerServiceResult.getDate(), "dd.MM.yyyy HH:mm:ss", "yyyy-MM-dd HH:mm:ss"), Integer.valueOf(getUser().getUserId()), Integer.valueOf(tigerServiceResult.getClRef()), Integer.valueOf(i2), Integer.valueOf(tigerServiceResult.getDataReference()), tigerServiceResult.getClCode(), tigerServiceResult.getClName().replace("'", "''"), Double.valueOf(dArr[0]), Double.valueOf(dArr[1]), Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())), Integer.valueOf(i3));
        }
        return ContextUtils.formatStringEnglish(R.string.app_insert_fiche_process_withVersion, DateAndTimeUtils.convertStringDate(tigerServiceResult.getDate(), "dd.MM.yyyy HH:mm:ss", "yyyy-MM-dd HH:mm:ss"), Integer.valueOf(getUser().getUserId()), Integer.valueOf(tigerServiceResult.getClRef()), Integer.valueOf(i2), Integer.valueOf(tigerServiceResult.getDataReference()), tigerServiceResult.getClCode(), tigerServiceResult.getClName().replace("'", "''"), Double.valueOf(dArr[0]), Double.valueOf(dArr[1]), Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())), Integer.valueOf(i3), BuildConfig.VERSION_NAME);
    }

    public TigerSelectResult deletePenetrationHeader(String str) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.app_delete_customer_penetration_header, str) + ContextUtils.getStringResource(R.string.app_delete_customer_penetration_detail, str)).withProcessType(ProcessType.SUCSESS).build();
    }

    
    public String insertPenetrationLine(Penetration penetration, PenetrationLine penetrationLine) {
        return ContextUtils.formatStringEnglish(R.string.app_insert_penetration, Integer.valueOf(penetration.getPenetrationId()), Integer.valueOf(penetrationLine.getPenetrationDetailId()), Integer.valueOf(getUser().getUserId()), Integer.valueOf(penetration.getClRef()), DateAndTimeUtils.convertDateSqlDate(penetration.getFicheDate()), Integer.valueOf(penetrationLine.getExist().isSelect() ? 1 : 0), penetrationLine.getAmount().toString(), penetrationLine.getPrice().toString(), penetrationLine.getCurrency().getCode(), 0, "", "", "", "", "", "", "", "", "", "", CompressUtil.base64CompressImage(penetrationLine.getImage().getImageArray()));
    }

    
    public TigerSelectResult getSuppAsgns() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(("SELECT * FROM ( SELECT ITEMREF,CLIENTREF,ICUSTSUPCODE,ICUSTSUPNAME ,ROW_NUMBER() OVER (ORDER BY S.LOGICALREF) AS RowNum FROM  " + mLogoTigerTableName.SUPPASGN + " S WITH(NOLOCK) ") + ") R ").withProcessType(ProcessType.GETSUPPASGN).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getOrderGrossTotal(int i2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT  M.LOGICALREF, M.GROSSTOTAL, M.ADDDISCOUNTS FROM " + mLogoTigerTableName.ORFLINE + " D INNER JOIN " + mLogoTigerTableName.ORFICHE + " M ON D.ORDFICHEREF = M.LOGICALREF WHERE D.LOGICALREF = " + i2).withProcessType(ProcessType.GETORDERTOFICHETOTAL).withTableDelete(false).withDatabaseSave(false).build();
    }

    private String getCustomersFilter(String str) {
        ArrayList arrayList = new ArrayList();
        String[] split = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards).split("\\;");
        String trim = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_AuxiliaryCode).trim();
        String trim2 = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_AuxiliaryCode2).trim();
        String trim3 = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_AuxiliaryCode3).trim();
        String trim4 = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_AuxiliaryCode4).trim();
        String trim5 = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_AuxiliaryCode5).trim();
        String trim6 = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_TradingGroup).trim();
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptARAPCards_SQLSentence);
        if (split != null && split.length == 2) {
            String str2 = split[0];
            String str3 = split[1];
            addFilter(arrayList, !str2.equals("") && !str3.equals(""), str + ".CODE BETWEEN '" + str2 + "' AND '" + str3 + "' ");
        }
        addFilter(arrayList, !trim.isEmpty(), str + ".SPECODE='" + trim + "'");
        addFilter(arrayList, !trim2.isEmpty(), str + ".SPECODE2='" + trim2 + "'");
        addFilter(arrayList, !trim3.isEmpty(), str + ".SPECODE3='" + trim3 + "'");
        addFilter(arrayList, !trim4.isEmpty(), str + ".SPECODE4='" + trim4 + "'");
        addFilter(arrayList, !trim5.isEmpty(), str + ".SPECODE5='" + trim5 + "'");
        addFilter(arrayList, !trim6.isEmpty(), str + ".TRADINGGRP='" + trim6 + "'");
        boolean z = paramValue.trim().length() > 0;
        String sb = str +
                ".LOGICALREF IN ( " +
                paramValue.replace("_XXX_", "_" + getUser().getFirmNr() + "_").replace("_XX_", "_" + getUser().getPeridodNr() + "_") +
                SqlLiteVariable._CLOSE_BRACKET;
        addFilter(arrayList, z, sb);
        return String.join(" ", arrayList);
    }

    public static TigerSelectResult getItemRemoteFromCode(String str, String str2) {
        TigerSelectResult.TigerBuilder newBuilder = TigerSelectResult.newBuilder();
        LogoTigerTableName logoTigerTableName = mLogoTigerTableName;
        return (TigerSelectResult) newBuilder.withSql(ContextUtils.formatStringEnglish(R.string.get_logo_item_name, logoTigerTableName.ITEMS, str, logoTigerTableName.SRVCARD, str2)).withProcessType(ProcessType.GETITEMNAME).build();
    }

    public static TigerSelectResult getVisitLogicalRef(long j2) {
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(VisitInfo.class, "ID=?", new String[]{String.valueOf(j2)});
        VisitInfo visitInfo = new VisitInfo();
        if (table != null && table.size() > 0) {
            visitInfo = (VisitInfo) table.get(0);
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish("SELECT ID AS LOGICALREF FROM WOR_PROCESS WHERE PTYPE=9 AND DATE_ = CONVERT(datetime,'%1s',120) AND USERID = %2d AND CLIENTREF = %3d", DateAndTimeUtils.convertDateSqlDate(visitInfo.visitDate), Integer.valueOf(ErpCreator.getInstance().getmBaseErp().getUser().getUserId()), Integer.valueOf(visitInfo.clRef))).withProcessType(ProcessType.GETDOCODE).withTableDelete(false).withDatabaseSave(false).build();
    }

    public TigerSelectResult getLocTracking() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM ( SELECT L.LOGICALREF [LOGICALREF], L.CODE [CODE], L.NAME [NAME], ROW_NUMBER() OVER (ORDER BY L.LOGICALREF) AS RowNum FROM " + mLogoTigerTableName.LOCATION + " L WITH(NOLOCK)) R ").withProcessType(ProcessType.GETLOCTRACKING).withTableDelete(true).withDatabaseSave(true).build();
    }

    public TigerSelectResult getSalesDetailStockTracking(SalesDetail salesDetail, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ST.LOGICALREF LOGICALREF, USLINE.CODE UNIT, ST.REMAMOUNT REMAMOUNT, ST.STTRANSREF, ST.UOMREF, CONVERT(DATE,ST.EXPDATE,110) EXPDATE ,ST.LOCREF LOCREF, LOC.CODE LOCCODE FROM ");
        sb.append(mLogoTigerTableName.SLTRANS);
        sb.append(" ST WITH (NOLOCK) LEFT OUTER JOIN ");
        sb.append(mLogoTigerTableName.STFICHE);
        sb.append(" STFIC WITH (NOLOCK) ON (ST.STFICHEREF = STFIC.LOGICALREF) LEFT OUTER JOIN ");
        sb.append(mLogoTigerTableName.UNITSETL);
        sb.append(" USLINE WITH (NOLOCK) ON (ST.UOMREF = USLINE.LOGICALREF) LEFT OUTER JOIN ");
        sb.append(mLogoTigerTableName.LOCATION);
        sb.append(" LOC WITH (NOLOCK) ON (ST.LOCREF = LOC.LOGICALREF) ");
        sb.append(" WHERE (ST.ITEMREF = " + salesDetail.getItemRef() + ")");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(" AND (ST.INVENNO = ");
        if (salesDetail.getWareHouse().getLogicalRef() != -1) {
            i2 = salesDetail.getWareHouse().getLogicalRef();
        }
        sb2.append(i2);
        sb2.append(") ");
        sb.append(sb2);
        sb.append(" AND (ST.CANCELLED = 0) AND (ST.LPRODSTAT = 0) AND (ST.LOCREF<>0) ");
        sb.append(" AND (ST.EXIMFCTYPE IN ( 0 , 4 , 7 )) AND (ST.IOCODE IN (1,2)) ");
        sb.append(" AND (ST.STATUS = 0) AND (ST.DATE_ <= CONVERT(dateTime, GETDATE(), 101)) ");
        if (SalesUtils.isSalesTypeInvoiceOrRetailInvoiceOrDispatch(salesDetail.getmSalesType()) || SalesUtils.isSalesTypeWhTransfer(salesDetail.getmSalesType())) {
            sb.append(" AND (ST.REMAMOUNT > 0) ");
        }
        if (salesDetail.isHasVariant()) {
            sb.append(" AND (ST.VARIANTREF =" + salesDetail.getVariant().getLogicalRef() + ") ");
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb.toString()).withProcessType(ProcessType.GETDETAILSTOCKTRACK).build();
    }

    public TigerSelectResult getCampaignPromotionRef(String str, String str2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT CAMPLINE.LOGICALREF KEYPAIR, CAMPLINE.ITEMREF VALUEPAIR FROM " + mLogoTigerTableName.CMPGNLINE + " CAMPLINE WITH(NOLOCK) WHERE  (CAMPLINE.CAMPCARDREF = (SELECT LOGICALREF FROM " + mLogoTigerTableName.CAMPAIGN + " WITH(NOLOCK) WHERE CODE='" + str + "'  AND LINENR = " + str2 + "))").withProcessType(ProcessType.GETALTERNATIVEITEMREFS).build();
    }

    public TigerSelectResult getAlternativePromotionItemRefs(String str) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.get_alternative_promotion_item_refs_inner, mLogoTigerTableName.ITMCLSAS, str)).withProcessType(ProcessType.GETALTERNATIVEITEMREFS).build();
    }

    
    public TigerSelectResult getCountries() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.tiger_get_countries)).withDatabaseSave(true).withTableDelete(true).withProcessType(ProcessType.GETCOUNTRIES).build();
    }

    
    public TigerSelectResult getCities() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.tiger_get_cities)).withDatabaseSave(true).withTableDelete(true).withProcessType(ProcessType.GETCITIES).build();
    }

    
    public TigerSelectResult getTowns() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.tiger_get_towns)).withDatabaseSave(true).withTableDelete(true).withProcessType(ProcessType.GETTOWNS).build();
    }

    
    public TigerSelectResult getCustomerRiskTotals(int i2, boolean z) {
        String customersFilter = getCustomersFilter("C");
        StringBuilder sb = new StringBuilder();
        if (i2 != -1) {
            sb.append(" WHERE CLNUM.CLCARDREF = ");
            sb.append(i2);
        }
        if (!TextUtils.isEmpty(customersFilter)) {
            if (sb.length() > 0) {
                sb.append(" ");
                sb.append(buildSqlCondition(customersFilter, false));
            } else {
                sb.append(SqlLiteVariable._WHERE);
                sb.append(customersFilter);
            }
        }
        TigerSelectResult.TigerBuilder newBuilder = TigerSelectResult.newBuilder();
        LogoTigerTableName logoTigerTableName = mLogoTigerTableName;
        return (TigerSelectResult) newBuilder.withSql(ContextUtils.formatStringEnglish(R.string.get_customer_risk_totals, logoTigerTableName.CLRNUMS, logoTigerTableName.CLCARD, sb.toString())).withProcessType(ProcessType.GETCUSTOMERRISKTOTALS).withTableDelete(z).build();
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
        sb.append("SELECT SLM.LOGICALREF FROM " + mLogoTigerTableName.SLSMAN + "  SLM WITH(NOLOCK) LEFT JOIN WOR_USERS US WITH(NOLOCK) ON SLM.CODE=US.CODE WHERE (SLM.ACTIVE=0 AND SLM.FIRMNR=" + USER.firmano);
        if (!str.equals("")) {
            sb.append(" AND SLM.CODE = '" + str + "'");
        }
        if (i2 > 0) {
            sb.append(" AND SLM.LOGICALREF = ");
            sb.append(i2);
            sb.append(")");
        } else if (intValue == 2 && TextUtils.isEmpty(str) && i2 == 0) {
            if (!TextUtils.isEmpty(salesManagerSalesmanFilter[0]) && !TextUtils.isEmpty(salesManagerSalesmanFilter[1])) {
                sb.append(" AND SLM.CODE BETWEEN '" + salesManagerSalesmanFilter[0] + "' AND '" + salesManagerSalesmanFilter[1] + "' ");
            }
            if (!TextUtils.isEmpty(salesManagerSalesmanFilter[2])) {
                sb.append(" AND SLM.SPECODE='" + salesManagerSalesmanFilter[2] + "' ");
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
            sb.append(") OR SLM.LOGICALREF = ");
            sb.append(ErpCreator.getInstance().getmBaseErp().getUser().getSalesPersonRef());
        }
        return sb.toString();
    }

    
    public TigerSelectResult getCabins(boolean z) {
        String str;
        if (!FDateUtils.isFDateFieldEnabled(FDateField.CABINS)) {
            str = "dbo.GetWebOrderDateInt(C.CREATEDDATE, C.MODIFIEDDATE)";
        } else {
            str = getFDateSql(" C");
        }
        String str2 = str + " AS [CMDATE], ";
        String str3 = "";
        if (!z) {
            double maxCMDate = getSqlHelper().getMaxCMDate(Cabin.class);
            if (maxCMDate != 0.0d) {
                str3 = SqlLiteVariable._AND + str + " > " + maxCMDate;
            } else {
                str3 = " AND FIRM = '" + ErpCreator.getInstance().getmBaseErp().getUser().getFirmNr() + "'";
                z = false;
            }
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.getStringResource(R.string.app_get_cabin, str2, str3)).withProcessType(ProcessType.GETCABINS).withDatabaseSave(true).withTableDelete(z).build();
    }

    public static TigerSelectResult getUpdateClfline(TransferOperationName transferOperationName, TigerServiceResult tigerServiceResult) {
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        try {
            User user = ErpCreator.getInstance().getmBaseErp().getUser();
            int dataReference = tigerServiceResult.getDataReference();
            String format = String.format("%sLG_%s_%s_", user.getDbName(), user.getFirmNr(), user.getPeridodNr());
            sb.append(SqlLiteVariable._UPDATE);
            sb.append(format);
            sb.append("CLFLINE SET FTIME=");
            Object data = tigerServiceResult.getData();
            int i3 = C25681.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[transferOperationName.ordinal()];
            if (i3 == 1) {
                CashCreditX cashCreditX = (CashCreditX) data;
                i2 = DateAndTimeUtils.getLogoDateTimeCode(cashCreditX.getCashCredit().getgDate());
                sb.append(i2);
                sb.append(" WHERE SOURCEFREF=");
                sb.append(dataReference);
                sb.append(" AND CLIENTREF=");
                sb.append(cashCreditX.getCashCredit().clRef);
                sb.append(SqlLiteVariable._AND);
                sb.append("MODULENR=5 AND TRCODE=1");
            } else if (i3 == 2) {
                CashCreditX cashCreditX2 = (CashCreditX) data;
                i2 = DateAndTimeUtils.getLogoDateTimeCode(cashCreditX2.getCashCredit().getgDate());
                sb.append(i2);
                sb.append(" WHERE SOURCEFREF=");
                sb.append(dataReference);
                sb.append(" AND CLIENTREF=");
                sb.append(cashCreditX2.getCashCredit().clRef);
                sb.append(SqlLiteVariable._AND);
                sb.append("MODULENR=5 AND TRCODE=70");
            } else if (i3 == 3) {
                CaseCash caseCash = (CaseCash) data;
                i2 = DateAndTimeUtils.getLogoDateTimeCode(caseCash.getgDate());
                sb.append(i2);
                sb.append(" WHERE SOURCEFREF=");
                sb.append(dataReference);
                sb.append(" AND CLIENTREF=");
                sb.append(caseCash.clRef);
                sb.append(SqlLiteVariable._AND);
                sb.append("MODULENR=10 AND TRCODE=1");
            } else if (i3 == 4) {
                ChequeDeed chequeDeed = (ChequeDeed) data;
                i2 = DateAndTimeUtils.getLogoDateTimeCode(chequeDeed.getChequedeed().getgDate());
                sb.append(i2);
                sb.append(" WHERE SOURCEFREF=");
                sb.append(dataReference);
                sb.append(" AND CLIENTREF=");
                sb.append(chequeDeed.getChequedeed().clRef);
                sb.append(SqlLiteVariable._AND);
                sb.append("MODULENR=6 AND TRCODE=61");
            } else {
                if (i3 != 5) {
                    return null;
                }
                ChequeDeed chequeDeed2 = (ChequeDeed) data;
                i2 = DateAndTimeUtils.getLogoDateTimeCode(chequeDeed2.getChequedeed().getgDate());
                sb.append(i2);
                sb.append(" WHERE SOURCEFREF=");
                sb.append(dataReference);
                sb.append(" AND CLIENTREF=");
                sb.append(chequeDeed2.getChequedeed().clRef);
                sb.append(SqlLiteVariable._AND);
                sb.append("MODULENR=6 AND TRCODE=62");
            }
        } catch (Exception e2) {
            Log.e(TAG, "getupdateClflineSql: ", e2);
        }
        if (i2 == 0) {
            return null;
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb.toString()).withProcessType(ProcessType.UPDATECLFLINE).build();
    }

    
    public TigerSelectResult loadCurrencyBalances(int i2, String str, String str2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql("SELECT * FROM ( SELECT SUM(AMOUNT) LOCALSUM  ,SUM(CASE WHEN CLFLINE.TRRATE = 1 OR CLFLINE.TRCURR = 0 THEN AMOUNT ELSE TRNET END) TRSUM  ,SUM(REPORTNET) REPORTSUM  ,(CASE SIGN WHEN 0 THEN - 1 ELSE SIGN END) SIGN  ,ISNULL(C.CURCODE,'" + ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode() + "') CURCODE  FROM " + mLogoTigerTableName.CLFLINE + " CLFLINE WITH (NOLOCK)  LEFT JOIN L_CURRENCYLIST C WITH (NOLOCK) ON CLFLINE.TRCURR = C.CURTYPE  AND C.FIRMNR = 1 WHERE CLFLINE.CANCELLED=0 AND ( ( (CLIENTREF = " + i2 + ")   AND (DATE_ = CONVERT(DATETIME, '" + str + "', 101))   AND (MODULENR = 0)   AND (TRCODE >= 0)   )  OR ( (CLIENTREF =" + i2 + ")  AND (DATE_ = CONVERT(DATETIME, '" + str + "', 101))   AND (MODULENR > 0))   OR ( (CLIENTREF = " + i2 + ")   AND (DATE_ > CONVERT(DATETIME, '" + str + "', 101))   )  OR ((CLIENTREF > " + i2 + "))  )  AND (  (  (CLIENTREF = " + i2 + ")  AND (DATE_ <= CONVERT(DATETIME, '" + str2 + "', 101))  )  OR ((CLIENTREF < " + i2 + "))  ) GROUP BY C.CURCODE  ,CLFLINE.SIGN ) A").withProcessType(ProcessType.GETCURRENCYBALANCES).withDatabaseSave(false).withTableDelete(false).build();
    }

    
    public TigerSelectResult getWorProcess(TransferOperationName transferOperationName, TigerServiceResult tigerServiceResult) {
        return (TigerSelectResult) getV().withSql(ContextUtils.formatStringEnglish(R.string.app_get_fiche_process, Integer.valueOf(tigerServiceResult.getDataReference()), Integer.valueOf(tigerServiceResult.getClRef()), Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())), Integer.valueOf(transferOperationName.getProcessType()), Integer.valueOf(getUser().getUserId()))).withProcessType(ProcessType.GETWORPROCESS).build();
    }

    public TigerSelectResult getCheckFDateSql() {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(SqlLiteVariable._SELECT + ContextUtils.formatStringEnglish(R.string.app_get_fdate, mLogoTigerTableName.ORFICHE, "ORFICHE") + "," + ContextUtils.formatStringEnglish(R.string.app_get_fdate, mLogoTigerTableName.STFICHE, "STFICHE") + "," + ContextUtils.formatStringEnglish(R.string.app_get_fdate, mLogoTigerTableName.CLCARD, "CLCARD") + "," + ContextUtils.formatStringEnglish(R.string.app_get_fdate, mLogoTigerTableName.CLFLINE, "CLFLINE") + "," + ContextUtils.formatStringEnglish(R.string.app_get_fdate, mLogoTigerTableName.ITEMS, "ITEMS") + "," + ContextUtils.formatStringEnglish(R.string.app_get_fdate, mLogoTigerTableName.SRVCARD, "SRVCARD") + "," + ContextUtils.formatStringEnglish(R.string.app_get_fdate, mLogoTigerTableName.PRCLIST, "PRCLIST") + "," + ContextUtils.formatStringEnglish(R.string.app_get_fdate, mLogoTigerTableName.PAYPLANS, "PAYPLANS") + "," + ContextUtils.formatStringEnglish(R.string.app_get_fdate, mLogoTigerTableName.SHIPINFO, "SHIPINFO") + "," + ContextUtils.formatStringEnglish(R.string.app_get_fdate, mLogoTigerTableName.BNCARD, "BNCARD") + "," + ContextUtils.formatStringEnglish(R.string.app_get_fdate, mLogoTigerTableName.BANKACC, "BANKACC") + "," + ContextUtils.formatStringEnglish(R.string.app_get_fdate, mLogoTigerTableName.ADDTAX, "ADDTAX") + "," + ContextUtils.formatStringEnglish(R.string.app_get_fdate, "WOR_DELETEDRECS", "DELETEDRECS") + "," + ContextUtils.formatStringEnglish(R.string.app_get_fdate, "WOR_CABIN", "WORCABIN") + "," + ContextUtils.getStringResource(R.string.app_get_appVersionOnWorProcess)).withProcessType(ProcessType.CHECKFDATE).withDatabaseSave(false).withTableDelete(false).build();
    }

    public TigerSelectResult getLastPurchaseInfo(int i2) {
        TigerSelectResult.TigerBuilder newBuilder = TigerSelectResult.newBuilder();
        LogoTigerTableName logoTigerTableName = mLogoTigerTableName;
        return (TigerSelectResult) newBuilder.withSql(ContextUtils.formatStringEnglish(R.string.get_last_purchase_info, logoTigerTableName.STLINE, logoTigerTableName.CLCARD, logoTigerTableName.STFICHE, Integer.valueOf(i2))).withProcessType(ProcessType.GETLASTPURCHASEINFO).build();
    }

    
    public TigerSelectResult getDefOrder() {
        return (TigerSelectResult) getV().withSql(ContextUtils.getStringResource(R.string.app_get_deforder, Integer.valueOf(getUser().getUserId()), getUser().getFirmNr(), Integer.valueOf(getUser().getSalesPersonRef()))).withProcessType(ProcessType.GETDEFORDER).withTableDelete(true).withDatabaseSave(true).build();
    }

    
    public TigerSelectResult getDefOrderDetail() {
        return (TigerSelectResult) getV().withSql(ContextUtils.getStringResource(R.string.app_get_deforder_detail, Integer.valueOf(getUser().getUserId()), getUser().getFirmNr())).withProcessType(ProcessType.GETDEFORDERDETAIL).withTableDelete(true).withDatabaseSave(true).build();
    }

    public TigerSelectResult getEdespatchPdfHeader(String str, Boolean bool) {
        String str2 = bool.booleanValue() ? mLogoTigerTableName.EARCHIVEDET : mLogoTigerTableName.EINVOICEDET;
        TigerSelectResult.TigerBuilder newBuilder = TigerSelectResult.newBuilder();
        LogoTigerTableName logoTigerTableName = mLogoTigerTableName;
        return (TigerSelectResult) newBuilder.withSql(ContextUtils.formatStringEnglish(R.string.get_edespatch_pdf_header, logoTigerTableName.STFICHE, logoTigerTableName.CLCARD, logoTigerTableName.SHPAGENT, str2, logoTigerTableName.SHIPINFO, Integer.valueOf(StringUtils.convertStringToInt(str)))).withProcessType(ProcessType.GETEDESPATCHPDFHEADER).withTableDelete(false).build();
    }

    public TigerSelectResult getEInvoicePdfHeader(String str, Boolean bool) {
        String str2 = bool.booleanValue() ? mLogoTigerTableName.EARCHIVEDET : mLogoTigerTableName.EINVOICEDET;
        TigerSelectResult.TigerBuilder newBuilder = TigerSelectResult.newBuilder();
        LogoTigerTableName logoTigerTableName = mLogoTigerTableName;
        return (TigerSelectResult) newBuilder.withSql(ContextUtils.formatStringEnglish(R.string.get_einvoice_pdf_header, logoTigerTableName.INVOICE, logoTigerTableName.CLCARD, logoTigerTableName.STFICHE, logoTigerTableName.SHPAGENT, logoTigerTableName.SHIPINFO, str2, Integer.valueOf(StringUtils.convertStringToInt(str)))).withProcessType(ProcessType.GETEINVOICEPDFHEADER).withTableDelete(false).build();
    }

    public TigerSelectResult getEdocumentPdfDetail(String str, SalesType salesType) {
        String str2;
        String str3;
        String str4 = null;
        if (SalesUtils.isSalesTypeDispatchOrWhTransfer(salesType)) {
            str3 = "STFICHEREF";
        } else if (SalesUtils.isSalesTypeOnlyInvoice(salesType)) {
            str3 = "INVOICEREF";
        } else {
            str2 = "";
            str4 = SalesUtils.isSalesTypeWhTransfer(salesType) ? "AND TRCODE=25 AND IOCODE=2" : "";
            TigerSelectResult.TigerBuilder newBuilder = TigerSelectResult.newBuilder();
            LogoTigerTableName logoTigerTableName = mLogoTigerTableName;
            return (TigerSelectResult) newBuilder.withSql(ContextUtils.formatStringEnglish(R.string.get_edocument_pdf_detail, logoTigerTableName.STLINE, logoTigerTableName.ITEMS, logoTigerTableName.UNITSETL, logoTigerTableName.SRVCARD, Integer.valueOf(StringUtils.convertStringToInt(str)), str2, str4)).withProcessType(ProcessType.GETEDOCUMENTPDFDETAIL).withOrderBy("ORDER BY STFICHELNNO").withTableDelete(false).build();
        }
        str2 = str3;
        if (SalesUtils.isSalesTypeWhTransfer(salesType)) {
        }
        TigerSelectResult.TigerBuilder newBuilder2 = TigerSelectResult.newBuilder();
        LogoTigerTableName logoTigerTableName2 = mLogoTigerTableName;
        return (TigerSelectResult) newBuilder2.withSql(ContextUtils.formatStringEnglish(R.string.get_edocument_pdf_detail, logoTigerTableName2.STLINE, logoTigerTableName2.ITEMS, logoTigerTableName2.UNITSETL, logoTigerTableName2.SRVCARD, Integer.valueOf(StringUtils.convertStringToInt(str)), str2, str4)).withProcessType(ProcessType.GETEDOCUMENTPDFDETAIL).withOrderBy("ORDER BY STFICHELNNO").withTableDelete(false).build();
    }

    public TigerSelectResult getWarehouseItems(int i2) {
        String itemsFilter = getItemsFilter(false, false, "ITM");
        TigerSelectResult.TigerBuilder newBuilder = TigerSelectResult.newBuilder();
        LogoTigerTableName logoTigerTableName = mLogoTigerTableName;
        return (TigerSelectResult) newBuilder.withSql(ContextUtils.formatStringEnglish(R.string.get_warehouse_items, logoTigerTableName.STINVTOT, logoTigerTableName.ITEMS, logoTigerTableName.UNITSETL, logoTigerTableName.VRNTINVTOT, logoTigerTableName.VARIANT, logoTigerTableName.SLTRANS, logoTigerTableName.LOCATION, logoTigerTableName.SERILOTN, logoTigerTableName.STFICHE, Integer.valueOf(i2), itemsFilter, mLogoTigerTableName.CAPIWHOUSE, Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())))).withProcessType(ProcessType.GETWAREHOUSEITEMS).build();
    }

    public TigerSelectResult getLastCustomerSalesDateOfItem(int i2, int i3) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(("SELECT TOP 1 SL.DATE_ VALUEPAIR FROM " + mLogoTigerTableName.STLINE + " SL WITH (NOLOCK) ") + " WHERE SL.CANCELLED=0 AND SL.LINETYPE=0 AND SL.STOCKREF=" + i2 + " AND SL.TRCODE IN (7, 8, 9)  AND SL.CLIENTREF=" + i3 + " ORDER BY SL.DATE_ DESC").withProcessType(ProcessType.GETKEYVALUEPAIR).build();
    }

    public void setWorDeletedRecsExistsFromResult(TigerSelectResult worDeletedRecsExistsQuery) {
    }

    public TigerSelectResult getItemPriceOnlineWebService(OnlineItemPriceParameters onlineItemPriceParameters) {
        return null;
    }

    static class C25681 {
        static final int[] SwitchMapcomprojemobilesalescoreedocumentEDocumentType;
        static final int[] SwitchMapcomprojemobilesalescoreenumsTransferGetType;
        static final int[] SwitchMapcomprojemobilesalescoreenumsTransferOperationName;

        static {
            int[] iArr = new int[EDocumentType.values().length];
            SwitchMapcomprojemobilesalescoreedocumentEDocumentType = iArr;
            try {
                iArr[EDocumentType.ebtEFatura.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                SwitchMapcomprojemobilesalescoreedocumentEDocumentType[EDocumentType.ebtArsiv.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                SwitchMapcomprojemobilesalescoreedocumentEDocumentType[EDocumentType.ebtEIrs.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[TransferOperationName.values().length];
            SwitchMapcomprojemobilesalescoreenumsTransferOperationName = iArr2;
            try {
                iArr2[TransferOperationName.CASH.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CREDIT_CARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CASE_CASH.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CHEQUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.DEED.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr3 = new int[TransferGetType.values().length];
            SwitchMapcomprojemobilesalescoreenumsTransferGetType = iArr3;
            try {
                iArr3[TransferGetType.OTHER_INFO.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.USER_INFORMATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.WAREHOUSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.BRANCHES.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.FACTORY.ordinal()] = 5;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.DIVISION.ordinal()] = 6;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.TODO.ordinal()] = 7;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.MARKETING_MESSAGE.ordinal()] = 8;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.VISIT.ordinal()] = 9;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.PENETRATION.ordinal()] = 10;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.SPECODE.ordinal()] = 11;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.TRADING.ordinal()] = 12;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.PAYMENT.ordinal()] = 13;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.SHIP_TYPE.ordinal()] = 14;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.SHIP_AGENT.ordinal()] = 15;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.ITEM.ordinal()] = 16;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.ITEM_IMAGE.ordinal()] = 17;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.VARIANT.ordinal()] = 18;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.UNIT.ordinal()] = 19;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.BARCODE.ordinal()] = 20;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.FORM.ordinal()] = 21;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.STOCK.ordinal()] = 22;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.PRICE.ordinal()] = 23;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.DISCOUNT.ordinal()] = 24;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.CUSTOMER.ordinal()] = 25;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.CUSTOMER_IMAGE.ordinal()] = 26;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.SHIP_ADDRESS.ordinal()] = 27;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.BANK.ordinal()] = 28;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.BANKACCOUNT.ordinal()] = 29;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.CASE.ordinal()] = 30;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.VEHICLE.ordinal()] = 31;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.DESIGN_FILES.ordinal()] = 32;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.CURRENCY.ordinal()] = 33;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.PROJECT.ordinal()] = 34;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.EMAIL.ordinal()] = 35;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferGetType[TransferGetType.ERP_PARAMS.ordinal()] = 36;
            } catch (NoSuchFieldError unused44) {
            }
        }
    }

    public TigerSelectResult getEDocumentStatus(int i2, EDocumentType eDocumentType) {
        int i3 = C25681.SwitchMapcomprojemobilesalescoreedocumentEDocumentType[eDocumentType.ordinal()];
        String str = "SELECT %1s VALUEPAIR FROM %2s WHERE %3s = %4d";
        if (i3 == 1) {
            str = ContextUtils.formatStringEnglish("SELECT %1s VALUEPAIR FROM %2s WHERE %3s = %4d", "ESTATUS", mLogoTigerTableName.INVOICE, "LOGICALREF", Integer.valueOf(i2));
        } else if (i3 == 2) {
            str = ContextUtils.formatStringEnglish("SELECT %1s VALUEPAIR FROM %2s WHERE %3s = %4d", "EARCHIVESTATUS", mLogoTigerTableName.EARCHIVEDET, "INVOICEREF", Integer.valueOf(i2));
        } else if (i3 == 3) {
            str = ContextUtils.formatStringEnglish("SELECT %1s VALUEPAIR FROM %2s WHERE %3s = %4d", "EDESPSTATUS", mLogoTigerTableName.STFICHE, "LOGICALREF", Integer.valueOf(i2));
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(str).withProcessType(ProcessType.GETKEYVALUEPAIR).build();
    }

    
    public TigerSelectResult getUsersConnectedToMe(String str) {
        String str2;
        String str3;
        String[] salesManagerSalesmanFilter = ErpCreator.getInstance().getmBaseErp().getSalesManagerSalesmanFilter();
        String str4 = salesManagerSalesmanFilter[0];
        boolean z = true;
        String str5 = salesManagerSalesmanFilter[1];
        String str6 = salesManagerSalesmanFilter[2];
        boolean z2 = !TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str5);
        String paramValue = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getParamValue(ParameterTypes.ptSalesManagerSalespersonUserSpeCode);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(paramValue)) {
            String[] split = paramValue.split("\\;");
            if (split.length > 0) {
                for (String str7 : split) {
                    if (!TextUtils.isEmpty(str7)) {
                        sb.append("'");
                        sb.append(str7);
                        sb.append("',");
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        if (!z2 && TextUtils.isEmpty(paramValue) && sb.length() == 0) {
            str2 = "NODATA";
            str3 = str2;
        } else {
            z = z2;
            str2 = str4;
            str3 = str5;
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.get_usersConnectedToMe, Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())), z ? "X" : "", str2, str3, str6, TextUtils.isEmpty(paramValue) ? "" : "X", sb.length() == 0 ? "''" : sb.toString(), str, Integer.valueOf(getUser().getUserId()))).withProcessType(ProcessType.GETCONNECTEDUSERS).withDatabaseSave(false).withTableDelete(false).build();
    }

    public TigerSelectResult insertSalesPersonCustomerRelation(int i2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.app_insert_sales_person_customer_relation, Integer.valueOf(getUser().getUserId()), Integer.valueOf(i2))).withProcessType(ProcessType.INSERTWORUSERPROCESS).withDatabaseSave(false).withTableDelete(false).build();
    }

    public BaseSelectResult getSalesPersonCustomerRelationLineNo() {
        return TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.tiger_get_salesman_customer_relation_line_no, mLogoTigerTableName.SLSCLREL)).withProcessType(ProcessType.GETKEYVALUEPAIR).withDatabaseSave(false).withTableDelete(false).build();
    }

    public TigerSelectResult insertSalesPersonCustomerRelationToTiger(int i2, int i3) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.tiger_insert_salesman_customer_relation, mLogoTigerTableName.SLSCLREL, Integer.valueOf(getUser().getSalesPersonRef()), Integer.valueOf(i2), Integer.valueOf(i3))).withProcessType(ProcessType.INSERTWORUSERPROCESS).withDatabaseSave(false).withTableDelete(false).build();
    }

    public TigerSelectResult updateWorProcess(String str, int i2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.update_wor_process, DateAndTimeUtils.convertDateSqlDate(str), Integer.valueOf(i2))).withDatabaseSave(false).withTableDelete(false).withProcessType(ProcessType.INSERTWORPROCESS).build();
    }

    public TigerSelectResult getLastOrderProducts(int i2, boolean z) {
        String str = "SELECT CONVERT(VARCHAR, O.DATE_, 4) AS DATE, I.CODE AS STOCKCODE, I.NAME AS STOCKNAME, O.AMOUNT, O.SHIPPEDAMOUNT, O.PRICE, O.DISTDISC AS DISCOUNT, O.LINENET FROM " + mLogoTigerTableName.ORFLINE + " O WITH(NOLOCK) LEFT JOIN " + mLogoTigerTableName.ITEMS + " I WITH(NOLOCK) ON  I.LOGICALREF = O.STOCKREF WHERE O.ORDFICHEREF IN (SELECT TOP 1 LOGICALREF FROM " + mLogoTigerTableName.ORFICHE + " WITH (NOLOCK) WHERE ";
        if (!z) {
            str = str + "SALESMANREF = " + getUser().getSalesPersonRef() + SqlLiteVariable._AND;
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(str + " CLIENTREF = " + i2 + " AND TRCODE = 1 ORDER BY LOGICALREF DESC) AND LINETYPE IN (0, 1, 6)").withProcessType(ProcessType.GETLASTORDERPRODUCTS).build();
    }

    public TigerSelectResult getRecommendedProducts(int i2) {
        TigerSelectResult.TigerBuilder newBuilder = TigerSelectResult.newBuilder();
        LogoTigerTableName logoTigerTableName = mLogoTigerTableName;
        return (TigerSelectResult) newBuilder.withSql(ContextUtils.formatStringEnglish(R.string.get_recommended_products, logoTigerTableName.STLINE, logoTigerTableName.STFICHE, Integer.valueOf(i2))).withProcessType(ProcessType.GETRECOMMENDEDPRODUCTS).build();
    }

    public static TigerSelectResult getRiskLimit(int i2) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.get_risk_limit, mLogoTigerTableName.PAYTRANS, Integer.valueOf(i2), DateAndTimeUtils.dateAddToSqlDate(ErpCreator.getInstance().getmBaseErp().getAverageExpiryDay()))).withProcessType(ProcessType.GETRISKLIMIT).build();
    }

    public TigerSelectResult getOrderFicheStatus(ArrayList<String> arrayList) {
        TigerSelectResult.TigerBuilder newBuilder = TigerSelectResult.newBuilder();
        LogoTigerTableName logoTigerTableName = mLogoTigerTableName;
        return (TigerSelectResult) newBuilder.withSql(ContextUtils.formatStringEnglish(R.string.get_order_status, logoTigerTableName.ORFLINE, logoTigerTableName.ORFICHE, StringUtils.formatList(arrayList, false))).withProcessType(ProcessType.GETORDERSFICHETATUS).build();
    }

    
    public TigerSelectResult execBarcodeSp(String str) {
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(ContextUtils.formatStringEnglish(R.string.get_stored_procedure, str)).withProcessType(ProcessType.EXECWMSBARCODESP).withTableDelete(false).withDatabaseSave(false).build();
    }

    public TigerSelectResult getPurchasePrices(boolean z) {
        FDateField fDateField = FDateField.PRICE_LIST;
        String sb2 = "SELECT * FROM (SELECT P.LOGICALREF, P.CODE, P.CURRENCY, P.CARDREF, P.PRICE, ROW_NUMBER() OVER (ORDER BY LOGICALREF) AS RowNum," +
                checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " P") +
                " AS [CMDATE] FROM " +
                mLogoTigerTableName.PRCLIST +
                " P WITH(NOLOCK) WHERE PTYPE = 1 ";
        if (!z) {
            double maxCMDate = getSqlHelper().getMaxCMDate(PurchasePrice.class);
            if (maxCMDate != 0.0d) {
                sb2 = sb2 + SqlLiteVariable._AND + checkFDateColumn(FDateUtils.isFDateFieldEnabled(fDateField), " P") + " > " + maxCMDate;
            } else {
                z = true;
            }
        }
        return (TigerSelectResult) TigerSelectResult.newBuilder().withSql(sb2 + ") R").withProcessType(ProcessType.GETPURCHASEPRICE).withTableDelete(z).withDatabaseSave(true).build();
    }
}
