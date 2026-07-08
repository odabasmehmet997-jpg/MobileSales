package com.proje.mobilesales.core.sql.tiger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.CustomerMenuType;
import com.proje.mobilesales.core.enums.LineType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.SearchType;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.sql.SqlHelper;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.CustomerFilter;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.FTypeControlUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.dbmodel.DefOrder;
import com.proje.mobilesales.features.dbmodel.Service;
import com.proje.mobilesales.features.model.AlternativeProductListOptions;
import com.proje.mobilesales.features.model.BarcodeResult;
import com.proje.mobilesales.features.model.SpecodeGroup;
import com.proje.mobilesales.features.product.model.ItemUnit;
import com.proje.mobilesales.features.product.model.ProductListParameter;
import com.proje.mobilesales.features.product.model.database.Item;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import com.proje.mobilesales.features.product.model.database.RecommendedProducts;
import java.util.ArrayList;
import java.util.List;

public abstract class TigerSqlHelper<T> extends SqlHelper<T> {
    public static final int DATABASE_VERSION = 249;
    private static final String TAG = "TigerSqlHelper";
 
    public int getBarcodeScannerSql() {
        return R.string.qry_get_barcode_ref;
    } 
    public int getDatabaseVersion() {
        return 249;
    } 
    public int getSalesDetailProductUnitSql(boolean z) {
        return z ? R.string.qry_sales_detail_unit : R.string.qry_sales_detail_service_unit;
    } 
    public int getSalesProductSetPriceSql() {
        return R.string.qry_get_price_local_set;
    }

    public TigerSqlHelper(Context context, String str) {
        this(context, str, null, 249);
    }

    public TigerSqlHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i2) {
        super(context, str, cursorFactory, i2);
        this.mISqlCreator = new TigerSqlCreator();
    } 
    public List<Class<?>> getTableList() {
        return super.getTableList();
    } 
    public String getTodoListSql(Context context) {
        return context.getString(R.string.qry_get_todo_list);
    }

    private String getShipmentColumn(boolean z) {
        if (!ErpCreator.getInstance().getmBaseErp().getRouteNewSystem()) {
            return " '' AS SHIPNAME , 0 AS SHIPREF , 0 AS ROUTEDAYREF, 0 AS ROUTEPLANREF, 0 AS ROUTEUSERCUSTOMERREF, ";
        }
        if (z) {
            return " SA.NAME AS SHIPNAME , SA.LOGICALREF AS SHIPREF ,IFNULL(WRD.LOGICALREF,0) AS ROUTEDAYREF, IFNULL(WRP.LOGICALREF,0) AS ROUTEPLANREF, WUC.LOGICALREF AS ROUTEUSERCUSTOMERREF, CASE WHEN WRP.SEQUENCE ISNULL THEN 0 ELSE 1 END PLANNED, ";
        }
        return " SA.NAME AS SHIPNAME , SA.LOGICALREF AS SHIPREF ,WRD.LOGICALREF AS ROUTEDAYREF, WRP.LOGICALREF AS ROUTEPLANREF, 0 AS ROUTEUSERCUSTOMERREF, 1 AS PLANNED, ";
    }

    private String getDefinitionColumn(int i2) {
        if (i2 == 2) {
            return " CASE WHEN LENGTH(C.DEFINITION2) > 0 THEN C.DEFINITION2 ELSE C.DEFINITION_ END AS DEFINITION_ , CASE WHEN LENGTH(C.DEFINITION_) > 0 THEN C.DEFINITION_ ELSE C.DEFINITION2 END AS DEFINITION2 ,";
        }
        return " CASE WHEN LENGTH(C.DEFINITION_) > 0 THEN C.DEFINITION_ ELSE C.DEFINITION2 END AS DEFINITION_ , CASE WHEN LENGTH(C.DEFINITION2) > 0 THEN C.DEFINITION2 ELSE C.DEFINITION_ END AS DEFINITION2 ,";
    }

    private String getDefinitionColumnOnWhere(int i2, int i3, String str) {
        if (i3 == 0) {
            return " OR C.DEFINITION2 LIKE '%" + str.replace(" ", "%") + "%' OR C.DEFINITION2 LIKE '%" + str.toLowerCase().replace(" ", "%") + "%' OR C.DEFINITION2 LIKE '%" + str.toUpperCase().replace(" ", "%") + "%'";
        }
        return " OR C.DEFINITION2 LIKE '" + str + "%'  OR C.DEFINITION2 LIKE '" + str.toLowerCase() + "%' OR C.DEFINITION2 LIKE '" + str.toUpperCase() + "%'";
    }

    public String getCustomerListSql(Context context, CustomerFilter customerFilter) {
        Exception exc;
        String str = "";
        double d2;
        String str2;
        String str3;
        String str4;
        boolean z;
        String str5;
        String str6;
        String str7 = "";
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        boolean routeNewSystem = baseErp.getRouteNewSystem();
        boolean z2 = baseErp.getOffRoadVisit() && customerFilter.isSelectedAllRoute();
        try {
            if (!customerFilter.isUseNear()) {
                try {
                    str = "SELECT (SELECT TITLE FROM SHIPAGENT WHERE SHIPAGENT.CODE=C.DELIVERYFIRM) AS TITLE,(SELECT DEFINITION_ FROM SHIPTYPE WHERE SHIPTYPE.CODE=C.DELIVERYMETHOD) AS SDEF , G.LATITUDE, G.LONGTITUDE , P.ODEMEPLAN AS PAYMENTDEF, MAX(O.GDATE) AS LASTORDERDATE, C.*, " + getShipmentColumn(z2) + getDefinitionColumn(customerFilter.getShowTitleType()) + "CIMG.CUSTOMERIMAGE, CDOC.CUSTOMERIMAGE INFONOTE, IFNULL(CUSTOP.OPERATIONSTODAY,0) OPERATIONSTODAY FROM CLCARD C";
                    d2 = 25.0d;
                    str2 = "%'  OR C.EDINO LIKE '";
                    str3 = "%'  OR C.INCHARGE LIKE '";
                } catch (Exception e2) {

                    Log.e(TAG, "GetCustomers: ", e2);
                    return str7;
                }
            } else {
                double latitude = baseErp.getLatitude();
                double longitude = baseErp.getLongitude();
                double d3 = (latitude * 3.141592653589793d) / 180.0d;
                str2 = "%'  OR C.EDINO LIKE '";
                str3 = "%'  OR C.INCHARGE LIKE '";
                double sin = Math.sin(d3);
                double cos = Math.cos(d3);
                double d4 = (longitude * 3.141592653589793d) / 180.0d;
                double sin2 = Math.sin(d4);
                double cos2 = Math.cos(d4);
                double cos3 = Math.cos(customerFilter.getNearRpDistance() / 6371.0d);
                StringBuilder sb = new StringBuilder();
                try {
                    sb.append("SELECT (SELECT TITLE FROM SHIPAGENT WHERE SHIPAGENT.CODE=C.DELIVERYFIRM) AS TITLE,(SELECT DEFINITION_ FROM SHIPTYPE WHERE SHIPTYPE.CODE=C.DELIVERYMETHOD) AS SDEF,(");
                    sb.append(sin);
                    sb.append(" * LAT_SIN + ");
                    sb.append(cos);
                    sb.append(" * LAT_COS * (");
                    sb.append(cos2);
                    sb.append(" * LONG_COS + ");
                    sb.append(sin2);
                    sb.append(" * LONG_SIN)) AS DIST, G.LATITUDE, G.LONGTITUDE , P.ODEMEPLAN AS PAYMENTDEF, MAX(O.GDATE) AS LASTORDERDATE,  C.*, ");
                    sb.append(getShipmentColumn(z2));
                    sb.append(getDefinitionColumn(customerFilter.getShowTitleType()));
                    sb.append("CIMG.CUSTOMERIMAGE, CDOC.CUSTOMERIMAGE INFONOTE, IFNULL(CUSTOP.OPERATIONSTODAY,0) OPERATIONSTODAY FROM CLCARD C");
                    str = sb.toString();
                    d2 = cos3;
                } catch (Exception e3) {
                    exc = e3;
                    str7 = "";
                    Log.e(TAG, "GetCustomers: ", exc);
                    return str7;
                }
            }
            if (!routeNewSystem) {
                if (customerFilter.getDayRef() > 0 || customerFilter.getCustomerType() == 1) {
                    str = str + " INNER JOIN SLSCLREL S ON S.CLREF=C.LOGICALREF ";
                }
                if (customerFilter.getRouteRef() > 0) {
                    str = str + " LEFT JOIN ROUTETRS R ON R.CLIENTREF=C.LOGICALREF";
                }
                str4 = str + " LEFT JOIN SHIPADDRESS SA ON SA.CLREF = C.LOGICALREF ";
            } else if (z2) {
                Boolean bool = Boolean.FALSE;
                String sb2 = ((str + " INNER JOIN WORUSERCUSTOMERS WUC ON WUC.CLIENTREF=C.LOGICALREF ") + " LEFT JOIN SHIPADDRESS SA ON WUC.SHIPMENTREF=SA.LOGICALREF ") + " LEFT JOIN WORROUTEPLAN WRP ON WRP.CLIENTREF=WUC.CLIENTREF AND WRP.SHIPMENTREF = WUC.SHIPMENTREF AND WRP.ROUTEID = (SELECT ROUTEID FROM WORROUTEDAY WHERE DAY=( " +
                        " SELECT ((CAST (((JulianDay(STRFTIME('%Y-%m-%d %H:%M:%S', '" +
                        DateAndTimeUtils.getSqlDate(bool) +
                        "')) - JulianDay(STRFTIME('%Y-%m-%d %H:%M:%S', PERIODSTARTDATE))) / 7) AS INTEGER) % " +
                        baseErp.getRouteNewSystemPeriod() +
                        ") * 7) + CASE (SELECT CAST (strftime('%w', date('" +
                        DateAndTimeUtils.getSqlDate(bool) +
                        "')) AS INTEGER)) WHEN 0 THEN 7 ELSE (SELECT CAST (strftime('%w', date('" +
                        DateAndTimeUtils.getSqlDate(bool) +
                        "')) AS INTEGER)) END AS DAY FROM WORROUTE)) ";
                str4 = sb2 + " LEFT JOIN WORROUTEDAY WRD ON WRD.ROUTEID=WRP.ROUTEID";
            } else {
                Boolean bool2 = Boolean.FALSE;
                String sb3 = ((str + " INNER JOIN WORROUTEPLAN WRP ON WRP.CLIENTREF=C.LOGICALREF ") + " LEFT JOIN SHIPADDRESS SA ON WRP.SHIPMENTREF=SA.LOGICALREF ") + " INNER JOIN WORROUTEDAY WRD ON WRD.ROUTEID=WRP.ROUTEID AND WRD.DAY=( " +
                        " SELECT ((CAST (((JulianDay(STRFTIME('%Y-%m-%d %H:%M:%S', '" +
                        DateAndTimeUtils.getSqlDate(bool2) +
                        "')) - JulianDay(STRFTIME('%Y-%m-%d %H:%M:%S', PERIODSTARTDATE))) / 7) AS INTEGER) % " +
                        baseErp.getRouteNewSystemPeriod() +
                        ") * 7) + CASE (SELECT CAST (strftime('%w', date('" +
                        DateAndTimeUtils.getSqlDate(bool2) +
                        "')) AS INTEGER)) WHEN 0 THEN 7 ELSE (SELECT CAST (strftime('%w', date('" +
                        DateAndTimeUtils.getSqlDate(bool2) +
                        "')) AS INTEGER)) END AS DAY FROM WORROUTE) ";
                str4 = sb3;
            }
            String str8 = ((((((str4 + " LEFT JOIN CUSTGPSINFO G ON G.CLIENTREF=C.LOGICALREF") + " LEFT JOIN PAYMENT P ON P.LOGICALREF=C.PAYMENTREF") + " LEFT JOIN ORDERS O ON O.CLREF=C.LOGICALREF") + " LEFT JOIN CUSTOMERIMAGE CIMG ON CIMG.CUSTOMERREF = C.LOGICALREF AND CIMG.DOCNR = 11 AND CIMG.DOCTYP = 0") + " LEFT JOIN CUSTOMERIMAGE CDOC ON CDOC.CUSTOMERREF = C.LOGICALREF AND CDOC.DOCNR = 1 AND CDOC.DOCTYP = 3") + " LEFT JOIN( " + ContextUtils.getStringResource(R.string.get_isCustomerHasDoneOperationToday) + ") CUSTOP ON CUSTOP.CLREF = C.LOGICALREF ") + " WHERE 1=1  AND (ACTIVE=0 OR (ACTIVE = 1 AND C.ISTRANSFER = 0)) ";
            if (customerFilter.isUseNear()) {
                str8 = str8 + " AND DIST>" + d2 + " ";
            }
            if (TextUtils.isEmpty(customerFilter.getFilter())) {
                z = routeNewSystem;
            } else {
                z = routeNewSystem;
                if (customerFilter.getSearchType() == SearchType.CONTAINING_SEARCH_WORDS.getValue()) {
                    str6 = str8 + " AND (C.DEFINITION_ LIKE '%" + customerFilter.getFilter().replace(" ", "%") + "%' OR C.DEFINITION_ LIKE '%" + customerFilter.getFilter().toLowerCase().replace(" ", "%") + "%' OR C.DEFINITION_ LIKE '%" + customerFilter.getFilter().toUpperCase().replace(" ", "%") + "%'" + getDefinitionColumnOnWhere(customerFilter.getShowTitleType(), customerFilter.getSearchType(), customerFilter.getFilter()) + " OR C.CODE LIKE '%" + customerFilter.getFilter().replace(" ", "%") + "%' OR C.CODE LIKE '%" + customerFilter.getFilter().toLowerCase().replace(" ", "%") + "%' OR C.CODE LIKE '%" + customerFilter.getFilter().toUpperCase().replace(" ", "%") + "%' OR C.EMAILADDR LIKE '%" + customerFilter.getFilter() + "%' OR C.EMAILADDR LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.EMAILADDR LIKE '%" + customerFilter.getFilter().toUpperCase() + "%' OR C.TELNRS1 LIKE '%" + customerFilter.getFilter() + "%' OR C.TELNRS1 LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.TELNRS1 LIKE '%" + customerFilter.getFilter().toUpperCase() + "%' OR C.INCHARGE LIKE '%" + customerFilter.getFilter() + "%' OR C.INCHARGE LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.INCHARGE LIKE '%" + customerFilter.getFilter().toUpperCase() + "%' OR C.EDINO LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.EDINO LIKE '%" + customerFilter.getFilter().toUpperCase() + "%' OR C.SPECODE LIKE '" + customerFilter.getFilter() + "' OR C.SPECODE2 LIKE '" + customerFilter.getFilter() + "' OR C.SPECODE3 LIKE '" + customerFilter.getFilter() + "' OR C.SPECODE4 LIKE '" + customerFilter.getFilter() + "' OR C.SPECODE5 LIKE '" + customerFilter.getFilter() + "' OR SA.NAME LIKE '%" + customerFilter.getFilter() + "%')";
                } else if (customerFilter.getSearchType() == SearchType.STARTING_WITH_SEARCH_WORD.getValue()) {
                    String str9 = str3;
                    String str10 = str2;
                    String sb4 = str8 +
                            " AND (C.DEFINITION_ LIKE '" +
                            customerFilter.getFilter() +
                            "%'  OR C.DEFINITION_ LIKE '" +
                            customerFilter.getFilter().toLowerCase() +
                            "%' OR C.DEFINITION_ LIKE '" +
                            customerFilter.getFilter().toUpperCase() +
                            "%'" +
                            getDefinitionColumnOnWhere(customerFilter.getShowTitleType(), customerFilter.getSearchType(), customerFilter.getFilter()) +
                            " OR C.CODE LIKE '" +
                            customerFilter.getFilter() +
                            "%'  OR C.CODE LIKE '" +
                            customerFilter.getFilter().toLowerCase() +
                            "%'  OR C.CODE LIKE '" +
                            customerFilter.getFilter().toUpperCase() +
                            "%'  OR C.EMAILADDR LIKE '" +
                            customerFilter.getFilter() +
                            "%'  OR C.EMAILADDR LIKE '" +
                            customerFilter.getFilter().toLowerCase() +
                            "%'  OR C.EMAILADDR LIKE '" +
                            customerFilter.getFilter().toUpperCase() +
                            "%'  OR C.TELNRS1 LIKE '" +
                            customerFilter.getFilter() +
                            "%'  OR C.TELNRS1 LIKE '" +
                            customerFilter.getFilter().toLowerCase() +
                            "%'  OR C.TELNRS1 LIKE '" +
                            customerFilter.getFilter().toUpperCase() +
                            str9 +
                            customerFilter.getFilter() +
                            str9 +
                            customerFilter.getFilter().toLowerCase() +
                            str9 +
                            customerFilter.getFilter().toUpperCase() +
                            str10 +
                            customerFilter.getFilter() +
                            str10 +
                            customerFilter.getFilter().toLowerCase() +
                            str10 +
                            customerFilter.getFilter().toUpperCase() +
                            "%'  OR C.SPECODE LIKE '" +
                            customerFilter.getFilter() +
                            "' OR C.SPECODE2 LIKE '" +
                            customerFilter.getFilter() +
                            "' OR C.SPECODE3 LIKE '" +
                            customerFilter.getFilter() +
                            "' OR C.SPECODE4 LIKE '" +
                            customerFilter.getFilter() +
                            "' OR C.SPECODE5 LIKE '" +
                            customerFilter.getFilter() +
                            "' OR SA.NAME LIKE '" +
                            customerFilter.getFilter() +
                            "%')";
                    str6 = sb4;
                } else {
                    str6 = str8 + " AND (C.DEFINITION_ LIKE '%" + customerFilter.getFilter() + "%' OR C.DEFINITION_ LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.DEFINITION_ LIKE '%" + customerFilter.getFilter().toUpperCase() + "%'" + getDefinitionColumnOnWhere(customerFilter.getShowTitleType(), customerFilter.getSearchType(), customerFilter.getFilter()) + " OR C.CODE LIKE '%" + customerFilter.getFilter() + "%' OR C.CODE LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.CODE LIKE '%" + customerFilter.getFilter().toUpperCase() + "%' OR C.EMAILADDR LIKE '%" + customerFilter.getFilter() + "%' OR C.EMAILADDR LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.EMAILADDR LIKE '%" + customerFilter.getFilter().toUpperCase() + "%' OR C.TELNRS1 LIKE '%" + customerFilter.getFilter() + "%' OR C.TELNRS1 LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.TELNRS1 LIKE '%" + customerFilter.getFilter().toUpperCase() + "%' OR C.INCHARGE LIKE '%" + customerFilter.getFilter() + "%' OR C.INCHARGE LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.INCHARGE LIKE '%" + customerFilter.getFilter().toUpperCase() + "%' OR C.EDINO LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.EDINO LIKE '%" + customerFilter.getFilter().toUpperCase() + "%' OR C.SPECODE LIKE '" + customerFilter.getFilter() + "' OR C.SPECODE2 LIKE '" + customerFilter.getFilter() + "' OR C.SPECODE3 LIKE '" + customerFilter.getFilter() + "' OR C.SPECODE4 LIKE '" + customerFilter.getFilter() + "' OR C.SPECODE5 LIKE '" + customerFilter.getFilter() + "' OR SA.NAME LIKE '%" + customerFilter.getFilter() + "%')";
                }
                str8 = str6;
            }
            if (!TextUtils.isEmpty(customerFilter.getCityFilter())) {
                str8 = str8 + " AND (UPPER(C.CITY) LIKE '" + customerFilter.getCityFilter().toUpperCase() + "%') ";
            }
            if (!TextUtils.isEmpty(customerFilter.getTownFilter())) {
                str8 = str8 + " AND (UPPER(C.TOWN) LIKE '" + customerFilter.getTownFilter().toUpperCase() + "%') ";
            }
            SpecodeGroup specodeGroup = customerFilter.getSpecodeGroup();
            if (!TextUtils.isEmpty(specodeGroup.getSpecode1())) {
                str8 = str8 + " AND (UPPER(C.SPECODE) LIKE '" + specodeGroup.getSpecode1().toUpperCase() + "%') ";
            }
            if (!TextUtils.isEmpty(specodeGroup.getSpecode2())) {
                str8 = str8 + " AND (UPPER(C.SPECODE2) LIKE '" + specodeGroup.getSpecode2().toUpperCase() + "%') ";
            }
            if (!TextUtils.isEmpty(specodeGroup.getSpecode3())) {
                str8 = str8 + " AND (UPPER(C.SPECODE3) LIKE '" + specodeGroup.getSpecode3().toUpperCase() + "%') ";
            }
            if (!TextUtils.isEmpty(specodeGroup.getSpecode4())) {
                str8 = str8 + " AND (UPPER(C.SPECODE4) LIKE '" + specodeGroup.getSpecode4().toUpperCase() + "%') ";
            }
            if (!TextUtils.isEmpty(specodeGroup.getSpecode5())) {
                str8 = str8 + " AND (UPPER(C.SPECODE5) LIKE '" + specodeGroup.getSpecode5().toUpperCase() + "%') ";
            }
            if (!z) {
                if (customerFilter.getRouteRef() > 0) {
                    str8 = str8 + " AND R.ROUTEREF=" + customerFilter.getRouteRef();
                }
                if (customerFilter.getDayRef() > 0) {
                    str8 = str8 + " AND S.VISITDAY=" + customerFilter.getDayRef();
                }
            }
            if (customerFilter.getSelectedDebitOrder() == 1) {
                str5 = str8 + " AND C.BAKIYE>0";
            } else {
                if (customerFilter.getSelectedDebitOrder() == 2) {
                    str5 = str8 + " AND C.BAKIYE<0";
                }
                String str11 = str8 + " GROUP BY C.LOGICALREF ";
                if (z) {
                    str11 = str11 + " , SA.LOGICALREF ";
                }
                if (!z) {
                    return str11 + " ORDER BY PLANNED DESC , WRP.SEQUENCE";
                }
                if (customerFilter.getRouteRef() > 0) {
                    return str11 + " ORDER BY R.LINENO_";
                }
                if (customerFilter.getDayRef() <= 0 && ErpCreator.getInstance().getmBaseErp().getRouteVisitinOutOfOrder()) {
                    if (customerFilter.getUserNerarType() != 3 && ((customerFilter.getUserNerarType() != 2 || !customerFilter.isUseNear()) && !customerFilter.isUseNear())) {
                        if (customerFilter.getSortType() == 0) {
                            return str11 + "ORDER BY UPPER(C.DEFINITION_) COLLATE UNICODE  ";
                        }
                        if (customerFilter.getSortType() == 1) {
                            return str11 + "ORDER BY UPPER(C.DEFINITION2) COLLATE UNICODE  ";
                        }
                        if (customerFilter.getSortType() == 2) {
                            return str11 + " ORDER BY C.DEBIT DESC ";
                        }
                        if (customerFilter.getSortType() != 3) {
                            return str11;
                        }
                        return str11 + " ORDER BY C.CODE ";
                    }
                    if (customerFilter.isUseOrderNameOrCode()) {
                        if (customerFilter.getSortType() == 0) {
                            return str11 + "ORDER BY UPPER(C.DEFINITION_) COLLATE UNICODE  ";
                        }
                        if (customerFilter.getSortType() == 1) {
                            return str11 + "ORDER BY UPPER(C.DEFINITION2) COLLATE UNICODE  ";
                        }
                        if (customerFilter.getSortType() == 2) {
                            return str11 + " ORDER BY C.DEBIT DESC ";
                        }
                        if (customerFilter.getSortType() != 3) {
                            return str11;
                        }
                        return str11 + " ORDER BY C.CODE ";
                    }
                    return str11 + "  ORDER BY  DIST DESC";
                }
                return str11 + " ORDER BY S.LINENO_";
            }
            str8 = str5;
            String str112 = str8 + " GROUP BY C.LOGICALREF ";
            if (z) {
            }
            if (!z) {
            }
        } catch (Exception e4) {
            Exception e = e4;
        }
        return str;
    }
    public String getRouteListSql(String str) {
        String str2 = "SELECT C.CODE, C.DEFINITION_, CASE WHEN WRP.SHIPMENTREF <> '' THEN SA.CITY ELSE C.CITY END AS CITY, CASE WHEN WRP.SHIPMENTREF <> '' THEN SA.CITY ELSE C.TOWN END AS TOWN, SA.NAME AS SHIPNAME FROM CLCARD C";
        try {
            str2 = (("SELECT C.CODE, C.DEFINITION_, CASE WHEN WRP.SHIPMENTREF <> '' THEN SA.CITY ELSE C.CITY END AS CITY, CASE WHEN WRP.SHIPMENTREF <> '' THEN SA.CITY ELSE C.TOWN END AS TOWN, SA.NAME AS SHIPNAME FROM CLCARD C INNER JOIN WORROUTEPLAN WRP ON WRP.CLIENTREF=C.LOGICALREF ") + " LEFT JOIN SHIPADDRESS SA ON WRP.SHIPMENTREF=SA.LOGICALREF ") + " INNER JOIN WORROUTEDAY WRD ON WRD.ROUTEID=WRP.ROUTEID AND WRD.DAY=( ";
            return str2 + " SELECT ((CAST (((JulianDay(STRFTIME('%Y-%m-%d %H:%M:%S', '" + DateAndTimeUtils.getSqlSelectedDate(str) + "')) - JulianDay(STRFTIME('%Y-%m-%d %H:%M:%S', PERIODSTARTDATE))) / 7) AS INTEGER) % " + ErpCreator.getInstance().getmBaseErp().getRouteNewSystemPeriod() + ") * 7) + CASE (SELECT CAST (strftime('%w', date('" + DateAndTimeUtils.getSqlSelectedDate(str) + "')) AS INTEGER)) WHEN 0 THEN 7 ELSE (SELECT CAST (strftime('%w', date('" + DateAndTimeUtils.getSqlSelectedDate(str) + "')) AS INTEGER)) END AS DAY FROM WORROUTE) ";
        } catch (Exception e2) {
            Log.e(TAG, "GetRoutes: ", e2);
            return str2;
        }
    }
 
    public String getDailyInfoListSql(Context context, int i2) {
        String str;
        String formatStringEnglish;
        boolean isMainFTypeVisit = FTypeControlUtils.isMainFTypeVisit();
        int i3 = R.string.qry_get_visit_info;
        String str2 = "";
        if (isMainFTypeVisit) {
            str = context.getString(R.string.qry_get_visit_info);
        } else {
            if (FTypeControlUtils.isMainFTypeOrder()) {
                i3 = R.string.qry_get_order_info;
            } else if (FTypeControlUtils.isMainFTypeInvoice()) {
                i3 = R.string.qry_get_invoice_info;
            } else if (FTypeControlUtils.isMainFTypeDispatch()) {
                i3 = R.string.qry_get_dispatch_note_info;
            } else if (FTypeControlUtils.isMainFTypeOneToOne()) {
                i3 = R.string.qry_get_one_to_one_info;
            } else if (FTypeControlUtils.isMainFTypeCashReceipt()) {
                i3 = R.string.qry_get_cash_info;
            } else if (FTypeControlUtils.isMainFTypeCreditReceipt()) {
                i3 = R.string.qry_get_credit_info;
            } else if (FTypeControlUtils.isMainFTypeCaseReceipt()) {
                i3 = R.string.qry_get_case_info;
            } else if (FTypeControlUtils.isMainFTypeCheckReceipt()) {
                i3 = R.string.qry_get_check_info;
            } else if (FTypeControlUtils.isMainFTypeDeedReceipt()) {
                i3 = R.string.qry_get_pnote_info;
            } else if (FTypeControlUtils.isMainFTypeDemand()) {
                i3 = R.string.qry_get_demand_info;
            } else if (FTypeControlUtils.isMainFTypeAll()) {
                i3 = R.string.query_daily_info_union;
            } else if (FTypeControlUtils.isMainFTypeRetailInvoice()) {
                i3 = R.string.qry_get_retail_invoice_info;
            } else if (FTypeControlUtils.isMainFTypeWhTransfer()) {
                i3 = R.string.qry_get_whtransfer_info;
            }
            str = "";
        }
        if (i2 == -1) {
            str2 = ContextUtils.formatStringEnglish(context.getString(R.string.qry_where_print), -1);
        } else if (i2 == 0) {
            str2 = context.getString(R.string.qry_where_non_print);
        } else if (i2 == 1) {
            str2 = ContextUtils.formatStringEnglish(context.getString(R.string.qry_where_print), 0);
        }
        if (FTypeControlUtils.isMainFTypeVisit()) {
            return str;
        }
        if (FTypeControlUtils.isMainFTypeInvoiceOrRetailInvoiceOrDispatch()) {
            if (FTypeControlUtils.isMainFatIrsTuruNormal()) {
                formatStringEnglish = ContextUtils.formatStringEnglish(context.getString(R.string.qry_where_invoice_type), 0);
            } else {
                formatStringEnglish = ContextUtils.formatStringEnglish(context.getString(R.string.qry_where_invoice_type), 1);
            }
            return String.format(context.getString(i3), str2, formatStringEnglish);
        }
        return String.format(context.getString(i3), str2);
    }

    
    public String getVisitListSql(Context context, String str, int i2, String str2, int i3, int i4) {
        String string = context.getString(R.string.qry_get_visit_list);
        try {
            String formatStringEnglish = ContextUtils.formatStringEnglish(string, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            if (str2 == null || str2.isEmpty()) {
                return formatStringEnglish;
            }
            return "SELECT * FROM (" + formatStringEnglish + ") AS ST WHERE ST.EXPLANATION LIKE '%" + str2 + "%'";
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "getLoaderSqlText: ", e2);
            return string;
        }
    }

    
    public String getVisitSql(Context context, int i2) {
        return ContextUtils.formatStringEnglish(context.getString(R.string.qry_get_visit_with_id), Integer.valueOf(i2));
    }

    
    public String getPenetrationListSql(Context context, String str, int i2, int i3, int i4) {
        String string = context.getString(R.string.qry_get_penetration_list);
        try {
            return ContextUtils.formatStringEnglish(string, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "getLoaderSqlText: ", e2);
            return string;
        }
    }

    
    public String getSalesListSql(Context context, int i2, CustomerOperation customerOperation, String str) {
        String str2;
        String str3 = "";
        try {
            CustomerMenuType menuType = customerOperation.getMenuType();
            CustomerMenuType customerMenuType = CustomerMenuType.SALES_ORDER;
            if (menuType == customerMenuType) {
                str3 = context.getString(R.string.qry_getSalesOrder);
            } else if (customerOperation.getMenuType() == CustomerMenuType.SALES_DEMAND) {
                str3 = context.getString(R.string.qry_getSalesDemand);
            } else if (customerOperation.getMenuType() == CustomerMenuType.SALES_INVOICE) {
                str3 = ((context.getString(R.string.qry_getSalesInvoice) + " I.FTYPE=1") + " AND I.INVTYPE=0") + " AND I.FACT<>1";
            } else if (customerOperation.getMenuType() == CustomerMenuType.SALES_RETURN_INVOICE) {
                str3 = ((context.getString(R.string.qry_getSalesInvoice) + " I.FTYPE=1") + " AND I.INVTYPE=1") + " AND I.FACT<>1";
            } else if (customerOperation.getMenuType() == CustomerMenuType.SALES_DISPATCH) {
                str3 = ((context.getString(R.string.qry_getSalesInvoice) + " I.FTYPE=0") + " AND I.INVTYPE=0") + " AND I.FACT<>1";
            } else if (customerOperation.getMenuType() == CustomerMenuType.SALES_RETURN_DISPATCH) {
                str3 = ((context.getString(R.string.qry_getSalesInvoice) + " I.FTYPE=0") + " AND I.INVTYPE=1") + " AND I.FACT<>1";
            } else if (customerOperation.getMenuType() == CustomerMenuType.SALES_RETAIL_INVOICE) {
                str3 = ((context.getString(R.string.qry_getSalesInvoice) + " I.FTYPE=3") + " AND I.INVTYPE=0") + " AND I.FACT<>1";
            } else if (customerOperation.getMenuType() == CustomerMenuType.SALES_RETAIL_RETURN_INVOICE) {
                str3 = ((context.getString(R.string.qry_getSalesInvoice) + " I.FTYPE=3") + " AND I.INVTYPE=1") + " AND I.FACT<>1";
            } else if (customerOperation.getMenuType() == CustomerMenuType.SALES_WHTRANSFER) {
                str3 = context.getString(R.string.qry_getSalesWhTransfer);
            }
            if (customerOperation.getMenuType() == CustomerMenuType.SALES_ONE_TO_ONE_CHANGE) {
                str3 = ((context.getString(R.string.qry_getSalesInvoice) + " I.FTYPE=2") + " AND I.INVTYPE=0") + " AND I.FACT<>1";
            }
            if (i2 != -1) {
                if (customerOperation.getMenuType() == customerMenuType) {
                    str2 = str3 + " AND O.CLREF=" + i2;
                } else {
                    str2 = str3 + " AND I.CLREF=" + i2;
                }
                str3 = str2;
            }
            if (!TextUtils.isEmpty(str)) {
                str3 = str3 + " AND (" + context.getString(R.string.column_document_track_no) + " LIKE '%" + str + "%' OR " + context.getString(R.string.column_document_track_no) + " LIKE '%" + str.toLowerCase() + "%' OR " + context.getString(R.string.column_document_track_no) + " LIKE '%" + str.toUpperCase() + "%' OR " + context.getString(R.string.column_desc) + " LIKE '%" + str + "%' OR " + context.getString(R.string.column_desc) + " LIKE '%" + str.toLowerCase() + "%' OR " + context.getString(R.string.column_desc) + " LIKE '%" + str.toUpperCase() + "%' OR " + context.getString(R.string.column_ship_name) + " LIKE '%" + str + "%' OR " + context.getString(R.string.column_ship_name) + " LIKE '%" + str.toLowerCase() + "%' OR " + context.getString(R.string.column_ship_name) + " LIKE '%" + str.toUpperCase() + "%' OR " + context.getString(R.string.column_fiche_no) + "=" + StringUtils.convertStringToInt(str) + ")";
            }
            return str3 + " " + context.getString(R.string.qry_getSalesOrderDesc);
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "getSalesListSql:  ", e2);
            return str3;
        }
    }

    public String getFicheListSql(Context context, int i2, CustomerOperation customerOperation, String str) {
        String str2;
        String str3 = "";
        try {
            ReceiptType receiptType = customerOperation.getReceiptType();
            ReceiptType receiptType2 = ReceiptType.CASH;
            if (receiptType != receiptType2 && customerOperation.getReceiptType() != ReceiptType.CREDIT) {
                if (customerOperation.getReceiptType() == ReceiptType.CASE) {
                    str3 = context.getString(R.string.qry_get_case);
                } else if (customerOperation.getReceiptType() == ReceiptType.CHEQUE || customerOperation.getReceiptType() == ReceiptType.DEED) {
                    str3 = context.getString(R.string.qry_get_chequedeed);
                }
                if (customerOperation.getReceiptType() != receiptType2 && customerOperation.getReceiptType() != ReceiptType.CHEQUE) {
                    if (customerOperation.getReceiptType() != ReceiptType.CREDIT || customerOperation.getReceiptType() == ReceiptType.DEED) {
                        str2 = str3 + " AND FTYPE = 1 ";
                        str3 = str2;
                    }
                    if (i2 != -1) {
                        str3 = str3 + " AND CC.CLREF=" + i2;
                    }
                    if (!TextUtils.isEmpty(str)) {
                        str3 = str3 + " AND (" + context.getString(R.string.column_desc1) + " LIKE '%" + str + "%' OR " + context.getString(R.string.column_desc1) + " LIKE '%" + str.toLowerCase() + "%' OR " + context.getString(R.string.column_desc1) + " LIKE '%" + str.toUpperCase() + "%' OR " + context.getString(R.string.column_fiche_no) + "=" + StringUtils.convertStringToInt(str) + ")";
                    }
                    return str3 + " " + context.getString(R.string.qry_getCashCreditOrderDesc);
                }
                str2 = str3 + " AND FTYPE = 0 ";
                str3 = str2;
                if (i2 != -1) {
                }
                if (!TextUtils.isEmpty(str)) {
                }
                return str3 + " " + context.getString(R.string.qry_getCashCreditOrderDesc);
            }
            str3 = context.getString(R.string.qry_get_cash);
            if (customerOperation.getReceiptType() != receiptType2) {
                if (customerOperation.getReceiptType() != ReceiptType.CREDIT) {
                }
                str2 = str3 + " AND FTYPE = 1 ";
                str3 = str2;
                if (i2 != -1) {
                }
                if (!TextUtils.isEmpty(str)) {
                }
                return str3 + " " + context.getString(R.string.qry_getCashCreditOrderDesc);
            }
            str2 = str3 + " AND FTYPE = 0 ";
            str3 = str2;
            if (i2 != -1) {
            }
            if (!TextUtils.isEmpty(str)) {
            }
            return str3 + " " + context.getString(R.string.qry_getCashCreditOrderDesc);
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "getSalesListSql:  ", e2);
            return str3;
        }
    }

    private String getClientCodePricePrioritySql() {
        return " (CASE     WHEN ITM.CLIENTCODE IS NOT NULL AND ITM.CLIENTCODE <> ''          AND (ITM.CLSPECODE IS NULL OR ITM.CLSPECODE = '') THEN 1     WHEN ITM.CLIENTCODE IS NOT NULL AND ITM.CLIENTCODE <> ''          AND ITM.CLSPECODE IS NOT NULL AND ITM.CLSPECODE <> '' THEN 2     ELSE 3   END)";
    }

    public String getProductListSql(Context context, ProductListParameter productListParameter, int i2, String str, int i3, ArrayList<BarcodeResult> arrayList, String str2, int i4, String str3, boolean z, ArrayList<Integer> arrayList2, int i5, String str4, String str5, int i6, ArrayList<RecommendedProducts> arrayList3) {
        boolean z2;
        String str6 = "";
        String str7 = "";
        String str8 = "";
        String str9 = "";
        String str10 = "";
        String str11 = "";
        boolean paramValueTrueCheck = false;
        boolean customerPriceFirst = false;
        String str12 = "";
        String str13 = "";
        String formatStringEnglish;
        Object obj = null;
        String str14;
        String str15 = "";
        String str16 = "";
        String replace;
        String str17 = "";
        String str18 = "";
        String str19 = "";
        String formatStringEnglish2;
        String str20 = "";
        Object obj2 = null;
        String str21 = "";
        String str22 = "";
        String str23 = "";
        Object obj3 = null;
        String barcodeText = "";
        if (!TextUtils.isEmpty(str4) && !str4.equals("0")) {
            z2 = false;
            if (productListParameter.isWhichMaterialDesc()) {
                str6 = "I.NAME";
            } else {
                str6 = "I.NAME2";
            }
            String str24 = str6;
            if (i2 > 0) {
                str7 = "";
            } else {
                str7 = " LEFT JOIN CLCARD CLL ON CLL.CODE=ITM.CLIENTCODE ";
            }
            String str25 = (str7 + "WHERE 1=1 AND ITM.PTYPE=2") + SqlLiteVariable._AND + ContextUtils.getStringResource(R.string.qry_where_pricelist_condition, DateAndTimeUtils.nowDate("yyyyMMdd"), "ITM.");
            StringBuilder sb = new StringBuilder();
            sb.append(str25);
            if (i6 < 0) {
                str8 = ContextUtils.getStringResource(R.string.qry_where_pricelist_division_condition, "ITM.", StringUtils.fillFormat(4, i6));
            } else {
                str8 = " AND ITM.DIVCODES = '-1'";
            }
            sb.append(str8);
            String sb2 = sb.toString();
            if (i2 > 0) {
                List<T> table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ClCard.class, "LOGICALREF=?", new String[]{String.valueOf(i2)});
                new ClCard();
                if (table != null && table.size() > 0) {
                    ClCard clCard = (ClCard) table.get(0);
                    str10 = clCard.getCode();
                    str9 = clCard.getSpecode();
                    str11 = TextUtils.isEmpty(str) ? "DONTMATCH" : str;
                    if (TextUtils.isEmpty(str9)) {
                        str9 = "DONTMATCH";
                    }
                    paramValueTrueCheck = StringUtils.paramValueTrueCheck(getParamValue(ParameterTypes.ptShowPricesBasedOnTheAuthCodeOfTheSalesPerson));
                    String str26 = productListParameter.isShowPrices() ? "" : " AND 1=2";
                    customerPriceFirst = ErpCreator.getInstance().getmBaseErp().getCustomerPriceFirst();
                    if (!TextUtils.isEmpty(str10) && i5 <= 0 && TextUtils.isEmpty(str11)) {
                        formatStringEnglish = "";
                        str12 = str24;
                    } else {
                        Integer valueOf = Integer.valueOf(i5 == 0 ? -1 : i5);
                        if (z2) {
                            str12 = str24;
                            str13 = "''";
                        } else {
                            str12 = str24;
                            str13 = str4;
                        }
                        formatStringEnglish = ContextUtils.formatStringEnglish(",CASE WHEN '%1s' LIKE replace(ITM.CLIENTCODE,'*','%%') AND ITM.TRADINGGRP='%2s' AND ITM.PAYPLANREF=%3d THEN 1 ELSE 0 END FULLMATCH,CASE WHEN ITM.CLIENTCODE='' AND ITM.TRADINGGRP='%2s' AND ITM.PAYPLANREF=%3d THEN 1 ELSE 0 END WITHOUTCLIENTMATCH,CASE WHEN ITM.CLIENTCODE='' AND ITM.TRADINGGRP='%2s' THEN 1 ELSE 0 END TRADINGGRPMATCH,CASE WHEN ITM.CLIENTCODE='' AND ITM.PAYPLANREF=%3d THEN 1 ELSE 0 END PAYPLANMATCH,CASE WHEN ITM.CLIENTCODE='' AND VAR.LOGICALREF=%4s THEN 1 ELSE 0 END VARIANTMATCH ,CASE WHEN ITM.CLIENTCODE='' AND ITM.CLSPECODE= '%5s' THEN 1 ELSE 0 END CLSPECODEMATCH ,CASE WHEN '%1s' LIKE replace(ITM.CLIENTCODE,'*','%%') AND ITM.GRPCODE = '' THEN 1 ELSE 0 END CLCODEMATCH", str10, str11, valueOf, str13, str9);
                    }
                    str14 = String.format(" AND VAR.LOGICALREF = %1s ", str4);
                    str15 = " LEFT JOIN VARIANTS V ON B.VARIANTREF = V.LOGICALREF ";
                    obj = " ,V.CODE VARIANTCODE ,V.LOGICALREF VARIANTREF, V.NAME VARIANTNAME ";
                    if (paramValueTrueCheck) {
                        sb2 = sb2 + str26 + " AND (ITM.CYPHCODE='" + ErpCreator.getInstance().getmBaseErp().getUser().getCyphCode() + "') ";
                    }
                    if (!TextUtils.isEmpty(formatStringEnglish)) {
                        if (!paramValueTrueCheck) {
                            sb2 = sb2 + str26 + " AND (ISDEFAULTPRICE=1 OR FULLMATCH=1 OR WITHOUTCLIENTMATCH=1 OR TRADINGGRPMATCH=1 OR PAYPLANMATCH=1 OR VARIANTMATCH = 1 OR CLSPECODEMATCH = 1 OR (ISOTHERPARAMSEMPTY =1 AND CLCODEMATCH)) " + str14;
                        }
                        str16 = sb2 + " ORDER BY ITM.ITEMREF,FULLMATCH DESC,WITHOUTCLIENTMATCH DESC,TRADINGGRPMATCH DESC,PAYPLANMATCH DESC,VARIANTMATCH DESC,CLSPECODEMATCH DESC,CLCODEMATCH DESC,ITM.PRIORITY ASC,ISDEFAULTPRICE ASC,ISOTHERPARAMSEMPTY ASC,ITM.LOGICALREF DESC";
                    } else {
                        str16 = sb2 + str26 + " AND (ISDEFAULTPRICE=1) " + str14 + " ORDER BY ITM.ITEMREF,ITM.PRIORITY ASC,ISDEFAULTPRICE ASC,ISOTHERPARAMSEMPTY ASC,ITM.LOGICALREF DESC";
                    }
                    if (customerPriceFirst) {
                        str16 = str16 + ", " + getClientCodePricePrioritySql();
                    }
                    String str27 = str16;
                    if (!productListParameter.isAllStock() || i3 == -1) {
                        replace = getParamValue(ParameterTypes.ptGeneralSettings_Warehouse).replace(";", ",");
                        if (replace.equals("")) {
                            str17 = "";
                        } else {
                            str17 = " AND STC.WAREHOUSENR IN (" + replace + ") ";
                        }
                    } else {
                        str17 = " AND STC.WAREHOUSENR=" + i3 + " ";
                    }
                    String str28 = str17;
                    String str29 = !productListParameter.isShowOnlyStock() ? "" : "HAVING IFNULL(SUM(STC.REALSTOCK), 0) >0 ";
                    if (!TextUtils.isEmpty(productListParameter.getCustomFilter())) {
                        str18 = productListParameter.getCustomFilter();
                    } else if (!TextUtils.isEmpty(str2)) {
                        if (z && arrayList2 != null && !arrayList2.isEmpty()) {
                            str18 = String.format("AND ( 1<>1 OR %s OR %s OR %s ) ", context.getString(R.string.qry_get_product_search_code, str2, str2.toLowerCase(), str2.toUpperCase()), context.getString(R.string.qry_get_product_search_barcode, str2), context.getString(R.string.qry_get_product_search_seri_barcode, StringUtils.getArrayToString(arrayList2, ",")));
                        } else {
                            if (productListParameter.getSearchType() == SearchType.CONTAINING_SEARCH_WORDS.getValue()) {
                                str18 = String.format("AND ( 1<>1 OR %s OR %s OR %s OR %s OR %s OR %s) ", productListParameter.getProductSearchOption().isDefinition() ? context.getString(R.string.qry_get_product_search_definition, str2.replace(" ", "%"), str2.toLowerCase().replace(" ", "%"), str2.toUpperCase().replace(" ", "%")) : "1<>1", (productListParameter.getProductSearchOption().isCode() || z) ? context.getString(R.string.qry_get_product_search_code, str2.replace(" ", "%"), str2.toLowerCase().replace(" ", "%"), str2.toUpperCase().replace(" ", "%")) : "1<>1", (productListParameter.getProductSearchOption().isBarcode() || z) ? context.getString(R.string.qry_get_product_search_containing_barcode, str2.replace(" ", "%")) : "1<>1", (productListParameter.getProductSearchOption().isCode() || z) ? context.getString(R.string.qry_get_product_search_mark_code, str2, str2.toLowerCase(), str2.toUpperCase()) : "1<>1", (productListParameter.getProductSearchOption().isCode() || z) ? context.getString(R.string.qry_get_product_search_producer_code, str2, str2.toLowerCase(), str2.toUpperCase()) : "1<>1", context.getString(R.string.qry_get_product_search_specode, str2));
                            } else if (productListParameter.getSearchType() == SearchType.STARTING_WITH_SEARCH_WORD.getValue()) {
                                str18 = String.format("AND ( 1<>1 OR %s OR %s OR %s OR %s OR %s OR %s) ", productListParameter.getProductSearchOption().isDefinition() ? context.getString(R.string.qry_get_product_search_pre_definition, str2, str2.toLowerCase(), str2.toUpperCase()) : "1<>1", (productListParameter.getProductSearchOption().isCode() || z) ? context.getString(R.string.qry_get_product_search_pre_code, str2, str2.toLowerCase(), str2.toUpperCase()) : "1<>1", (productListParameter.getProductSearchOption().isBarcode() || z) ? context.getString(R.string.qry_get_product_search_pre_barcode, str2) : "1<>1", (productListParameter.getProductSearchOption().isCode() || z) ? context.getString(R.string.qry_get_product_search_pre_mark_code, str2, str2.toLowerCase(), str2.toUpperCase()) : "1<>1", (productListParameter.getProductSearchOption().isCode() || z) ? context.getString(R.string.qry_get_product_search_pre_producer_code, str2, str2.toLowerCase(), str2.toUpperCase()) : "1<>1", context.getString(R.string.qry_get_product_search_specode, str2));
                            } else {
                                str18 = String.format("AND ( 1<>1 OR %s OR %s OR %s OR %s OR %s OR %s) ", productListParameter.getProductSearchOption().isDefinition() ? context.getString(R.string.qry_get_product_search_definition, str2, str2.toLowerCase(), str2.toUpperCase()) : "1<>1", (productListParameter.getProductSearchOption().isCode() || z) ? context.getString(R.string.qry_get_product_search_code, str2, str2.toLowerCase(), str2.toUpperCase()) : "1<>1", (productListParameter.getProductSearchOption().isBarcode() || z) ? context.getString(R.string.qry_get_product_search_containing_barcode, str2) : "1<>1", (productListParameter.getProductSearchOption().isCode() || z) ? context.getString(R.string.qry_get_product_search_mark_code, str2, str2.toLowerCase(), str2.toUpperCase()) : "1<>1", (productListParameter.getProductSearchOption().isCode() || z) ? context.getString(R.string.qry_get_product_search_producer_code, str2, str2.toLowerCase(), str2.toUpperCase()) : "1<>1", context.getString(R.string.qry_get_product_search_specode, str2));
                            }
                        }
                    } else if (arrayList == null || arrayList.isEmpty()) {
                        str18 = "";
                    } else if (arrayList2 != null && !arrayList2.isEmpty()) {
                        str18 = String.format(" AND %s", context.getString(R.string.qry_get_product_search_seri_barcode, StringUtils.getArrayToString(arrayList2, ",")));
                    } else {
                        str18 = String.format(" AND ( %s OR %s )", context.getString(R.string.qry_get_product_search_only_barcode, StringUtils.getBarcodeText(arrayList)), context.getString(R.string.qry_get_product_search_only_itemcode, StringUtils.getBarcodeText(arrayList)));
                    }
                    if (!TextUtils.isEmpty(str5)) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(str18);
                        sb3.append(" AND IFNULL(I.STGRPCODE, '')='");
                        String str30 = str5;
                        if (str30.equals(context.getString(R.string.str_others))) {
                            str30 = "";
                        }
                        sb3.append(str30);
                        sb3.append("' ");
                        str18 = sb3.toString();
                    }
                    if (arrayList3 != null && arrayList3.size() > 0) {
                        str18 = str18 + " AND I.LOGICALREF IN " + StringUtils.formatList(SqlHelper.convertToStringArrayList(arrayList3), false);
                    }
                    if (i4 != 0) {
                        List<T> tableWhere = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTableWhere(DefOrder.class, "LOGICALREF=?", new String[]{StringUtils.convertIntToString(i4)});
                        if (tableWhere != null && tableWhere.size() <= 0) {
                            obj2 = " , W.ISSLCTUNIT ";
                            str21 = " , W.LINENO_ ";
                            str22 = " , LINENO_ ";
                            str23 = " , W.UNITREF AS DUNITREF";
                            str20 = "";
                        } else {
                            DefOrder defOrder = (DefOrder) tableWhere.get(0);
                            if (!TextUtils.isEmpty(defOrder.getInnerSql())) {
                                str19 = str29;
                                formatStringEnglish2 = ContextUtils.formatStringEnglish(context.getString(R.string.qry_def_order_inner_sql), Integer.valueOf(i4), getLogoSqlCreator().getTableName(Item.class), defOrder.getInnerSql(), Integer.valueOf(LineType.PRODUCT.value));
                            } else {
                                str19 = str29;
                                formatStringEnglish2 = ContextUtils.formatStringEnglish(context.getString(R.string.qry_product_defOrder), Integer.valueOf(i4), Integer.valueOf(LineType.PRODUCT.value));
                            }
                            if (defOrder.getUseMinStock() == 1) {
                                str29 = " HAVING IFNULL(SUM(STC.REALSTOCK), 0) > " + defOrder.getMinStock();
                            } else {
                                str29 = str19;
                            }
                            str20 = formatStringEnglish2;
                            obj2 = " , W.ISSLCTUNIT ";
                            str21 = " , W.LINENO_ ";
                            str22 = " , LINENO_ ";
                            str23 = " , W.UNITREF AS DUNITREF";
                        }
                        obj3 = " , DUNITREF";
                    } else {
                        obj2 = " , 0 AS ISSLCTUNIT ";
                        obj3 = " , 0  AS DUNITREF ";
                        str20 = "";
                        str21 = str20;
                        str22 = str21;
                        str23 = str22;
                    }
                    String string = context.getString(R.string.qry_get_product_list);
                    Integer valueOf2 = Integer.valueOf(DateAndTimeUtils.getLogoDateInt());
                    if (TextUtils.isEmpty(StringUtils.getBarcodeText(arrayList))) {
                        barcodeText = "'" + str2 + "'";
                    } else {
                        barcodeText = StringUtils.getBarcodeText(arrayList);
                    }
                    return ContextUtils.formatStringEnglish(string, str12, " , ISSLCTUNIT ", obj2, str27, valueOf2, str20, str28, str18, str29, barcodeText, str3, str21, str22, str23, obj3, formatStringEnglish, obj, str15).replaceAll("\\s*[\\r\\n]+\\s*", "").trim();
                }
            }
            str9 = "";
            str10 = str9;
            TextUtils.isEmpty(str);
            TextUtils.isEmpty(str9);
            paramValueTrueCheck = StringUtils.paramValueTrueCheck(getParamValue(ParameterTypes.ptShowPricesBasedOnTheAuthCodeOfTheSalesPerson));
            customerPriceFirst = ErpCreator.getInstance().getmBaseErp().getCustomerPriceFirst();
            TextUtils.isEmpty(str10);
            Integer valueOf3 = i5 == 0 ? -1 : i5;
            formatStringEnglish = ContextUtils.formatStringEnglish(",CASE WHEN '%1s' LIKE replace(ITM.CLIENTCODE,'*','%%') AND ITM.TRADINGGRP='%2s' AND ITM.PAYPLANREF=%3d THEN 1 ELSE 0 END FULLMATCH,CASE WHEN ITM.CLIENTCODE='' AND ITM.TRADINGGRP='%2s' AND ITM.PAYPLANREF=%3d THEN 1 ELSE 0 END WITHOUTCLIENTMATCH,CASE WHEN ITM.CLIENTCODE='' AND ITM.TRADINGGRP='%2s' THEN 1 ELSE 0 END TRADINGGRPMATCH,CASE WHEN ITM.CLIENTCODE='' AND ITM.PAYPLANREF=%3d THEN 1 ELSE 0 END PAYPLANMATCH,CASE WHEN ITM.CLIENTCODE='' AND VAR.LOGICALREF=%4s THEN 1 ELSE 0 END VARIANTMATCH ,CASE WHEN ITM.CLIENTCODE='' AND ITM.CLSPECODE= '%5s' THEN 1 ELSE 0 END CLSPECODEMATCH ,CASE WHEN '%1s' LIKE replace(ITM.CLIENTCODE,'*','%%') AND ITM.GRPCODE = '' THEN 1 ELSE 0 END CLCODEMATCH", str10, str11, valueOf3, str13, str9);
            String str272 = str16;
            replace = getParamValue(ParameterTypes.ptGeneralSettings_Warehouse).replace(";", ",");
            String str282 = str17;
            TextUtils.isEmpty(productListParameter.getCustomFilter());
            TextUtils.isEmpty(str5);
            if (arrayList3 != null) {
                str18 = str18 + " AND I.LOGICALREF IN " + StringUtils.formatList(SqlHelper.convertToStringArrayList(arrayList3), false);
            }
            String string2 = context.getString(R.string.qry_get_product_list);
            Integer valueOf22 = DateAndTimeUtils.getLogoDateInt();
            TextUtils.isEmpty(StringUtils.getBarcodeText(arrayList));
            return ContextUtils.formatStringEnglish(string2, str12, " , ISSLCTUNIT ", obj2, str272, valueOf22, str20, str282, str18,   barcodeText, str3, str21, str22, str23, obj3, formatStringEnglish, obj, str15).replaceAll("\\s*[\\r\\n]+\\s*", "").trim();
        }
        z2 = true;
        String str242 = str6;
        String str252 = (str7 + "WHERE 1=1 AND ITM.PTYPE=2") + SqlLiteVariable._AND + ContextUtils.getStringResource(R.string.qry_where_pricelist_condition, DateAndTimeUtils.nowDate("yyyyMMdd"), "ITM.");
        String sb22 = str252 +
                str8;
        str9 = "";
        str10 = str9;

        paramValueTrueCheck = StringUtils.paramValueTrueCheck(getParamValue(ParameterTypes.ptShowPricesBasedOnTheAuthCodeOfTheSalesPerson));
        customerPriceFirst = ErpCreator.getInstance().getmBaseErp().getCustomerPriceFirst();
        TextUtils.isEmpty(str10);
        Integer valueOf32 = i5 == 0 ? -1 : i5;
        formatStringEnglish = ContextUtils.formatStringEnglish(",CASE WHEN '%1s' LIKE replace(ITM.CLIENTCODE,'*','%%') AND ITM.TRADINGGRP='%2s' AND ITM.PAYPLANREF=%3d THEN 1 ELSE 0 END FULLMATCH,CASE WHEN ITM.CLIENTCODE='' AND ITM.TRADINGGRP='%2s' AND ITM.PAYPLANREF=%3d THEN 1 ELSE 0 END WITHOUTCLIENTMATCH,CASE WHEN ITM.CLIENTCODE='' AND ITM.TRADINGGRP='%2s' THEN 1 ELSE 0 END TRADINGGRPMATCH,CASE WHEN ITM.CLIENTCODE='' AND ITM.PAYPLANREF=%3d THEN 1 ELSE 0 END PAYPLANMATCH,CASE WHEN ITM.CLIENTCODE='' AND VAR.LOGICALREF=%4s THEN 1 ELSE 0 END VARIANTMATCH ,CASE WHEN ITM.CLIENTCODE='' AND ITM.CLSPECODE= '%5s' THEN 1 ELSE 0 END CLSPECODEMATCH ,CASE WHEN '%1s' LIKE replace(ITM.CLIENTCODE,'*','%%') AND ITM.GRPCODE = '' THEN 1 ELSE 0 END CLCODEMATCH", str10, str11, valueOf32, str13, str9);
        TextUtils.isEmpty(formatStringEnglish);
        String str2722 = str16;
        replace = getParamValue(ParameterTypes.ptGeneralSettings_Warehouse).replace(";", ",");
        String str2822 = str17;
        TextUtils.isEmpty(productListParameter.getCustomFilter());
        TextUtils.isEmpty(str5);
        String string22 = context.getString(R.string.qry_get_product_list);
        Integer valueOf222 = DateAndTimeUtils.getLogoDateInt();
        TextUtils.isEmpty(StringUtils.getBarcodeText(arrayList));
        return ContextUtils.formatStringEnglish(string22, str12, " , ISSLCTUNIT ", obj2, str2722, valueOf222, str20, str2822, str18,   barcodeText, str3, str21, str22, str23, obj3, formatStringEnglish, obj, str15).replaceAll("\\s*[\\r\\n]+\\s*", "").trim();
    }
    public String getServiceListSql(Context context, ProductListParameter productListParameter, int i2, String str, int i3, ArrayList<BarcodeResult> arrayList, String str2, int i4, String str3, int i5, String str4) {
        String str5;
        String str6;
        String format;
        String formatStringEnglish;
        String str7 = "";
        String str8 = "";
        String str9 = "";
        String str10 = "";
        String str11 = "";
        Object obj = null;
        Object obj2 = null;
        String barcodeText = "";
        if (i2 <= 0) {
            str5 = "";
        } else {
            str5 = " LEFT JOIN CLCARD CLL ON CLL.CODE=ITM.CLIENTCODE ";
        }
        String str12 = (str5 + "WHERE 1=1 AND ITM.PTYPE=4") + SqlLiteVariable._AND + ContextUtils.getStringResource(R.string.qry_where_pricelist_condition, DateAndTimeUtils.nowDate("yyyyMMdd"), "ITM.");
        if (i2 > 0) {
            List<T> table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ClCard.class, "LOGICALREF=?", new String[]{String.valueOf(i2)});
            ClCard clCard = new ClCard();
            if (table != null && table.size() > 0) {
                clCard = (ClCard) table.get(0);
            }
            String code = clCard.getCode() == null ? "" : clCard.getCode();
            String specode = clCard.getSpecode() == null ? "" : clCard.getSpecode();
            String specode2 = clCard.getSpecode2() == null ? "" : clCard.getSpecode2();
            String specode3 = clCard.getSpecode3() == null ? "" : clCard.getSpecode3();
            String specode4 = clCard.getSpecode4() == null ? "" : clCard.getSpecode4();
            String specode5 = clCard.getSpecode5() == null ? "" : clCard.getSpecode5();
            String tradinggrp = clCard.getTradinggrp() == null ? "" : clCard.getTradinggrp();
            if (!TextUtils.isEmpty(code)) {
                str12 = str12 + " AND (CLL.LOGICALREF =" + i2 + " OR (ITM.CLIENTCODE='" + code + "' OR ITM.CLIENTCODE='' OR ITM.CLIENTCODE IS NULL)) ";
            }
            if (!TextUtils.isEmpty(specode)) {
                str12 = str12 + " AND (ITM.CLSPECODE='" + specode + "' OR ITM.CLSPECODE='' OR ITM.CLSPECODE IS NULL) ";
            }
            if (!TextUtils.isEmpty(specode2)) {
                str12 = str12 + " AND (ITM.CLSPECODE2='" + specode2 + "' OR ITM.CLSPECODE2='' OR ITM.CLSPECODE2 IS NULL) ";
            }
            if (!TextUtils.isEmpty(specode3)) {
                str12 = str12 + " AND (ITM.CLSPECODE3='" + specode3 + "' OR ITM.CLSPECODE3='' OR ITM.CLSPECODE3 IS NULL) ";
            }
            if (!TextUtils.isEmpty(specode4)) {
                str12 = str12 + " AND (ITM.CLSPECODE4='" + specode4 + "' OR ITM.CLSPECODE4='' OR ITM.CLSPECODE4 IS NULL) ";
            }
            if (!TextUtils.isEmpty(specode5)) {
                str12 = str12 + " AND (ITM.CLSPECODE5='" + specode5 + "' OR ITM.CLSPECODE5='' OR ITM.CLSPECODE5 IS NULL) ";
            }
            if (!TextUtils.isEmpty(tradinggrp)) {
                str12 = str12 + " AND (ITM.CLTRADINGGRP='" + tradinggrp + "' OR ITM.CLTRADINGGRP='' OR ITM.CLTRADINGGRP IS NULL) ";
            }
        }
        if (!TextUtils.isEmpty(str)) {
            str12 = str12 + " AND (ITM.TRADINGGRP ='" + str + "' OR ITM.TRADINGGRP LIKE '') ";
        }
        String str13 = (str12 + " AND (ITM.PAYPLANREF =" + i5 + " OR ITM.PAYPLANREF = 0) ") + " ORDER BY ITM.ITEMREF,ITM.PRIORITY ASC,ITM.CLSPECODE DESC,ITM.CLSPECODE2 DESC,ITM.CLSPECODE3 DESC,ITM.CLSPECODE4 DESC,ITM.CLSPECODE5 DESC,ITM.CLIENTCODE DESC,ISDEFAULTPRICE ASC,ITM.LOGICALREF ASC) A GROUP BY A.ITEMREF";
        if (!TextUtils.isEmpty(str2)) {
            if (productListParameter.getSearchType() == 0) {
                format = String.format("AND ( 1=1 AND %s OR %s OR %s ) ", productListParameter.getProductSearchOption().isDefinition() ? context.getString(R.string.qry_get_product_search_definition, str2, str2.toLowerCase(), str2.toUpperCase()) : "1=1", productListParameter.getProductSearchOption().isCode() ? context.getString(R.string.qry_get_product_search_code, str2, str2.toLowerCase(), str2.toUpperCase()) : "1=1", productListParameter.getProductSearchOption().isBarcode() ? context.getString(R.string.qry_get_product_search_barcode, str2) : "1=1");
            } else {
                format = String.format("AND ( 1=1 AND %s OR %s OR %s ) ", productListParameter.getProductSearchOption().isDefinition() ? context.getString(R.string.qry_get_product_search_pre_definition, str2, str2.toLowerCase(), str2.toUpperCase()) : "1=1", productListParameter.getProductSearchOption().isCode() ? context.getString(R.string.qry_get_product_search_pre_code, str2, str2.toLowerCase(), str2.toUpperCase()) : "1=1", productListParameter.getProductSearchOption().isBarcode() ? context.getString(R.string.qry_get_product_search_barcode, str2) : "1=1");
            }
        } else {
            if (arrayList == null || arrayList.size() <= 0) {
                str6 = "";
                if (i4 == 0) {
                    List<T> tableWhere = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTableWhere(DefOrder.class, "LOGICALREF=?", new String[]{StringUtils.convertIntToString(i4)});
                    if (tableWhere != null && tableWhere.size() <= 0) {
                        str8 = "";
                        str7 = str8;
                    } else {
                        DefOrder defOrder = (DefOrder) tableWhere.get(0);
                        if (!TextUtils.isEmpty(defOrder.getInnerSql())) {
                            formatStringEnglish = ContextUtils.formatStringEnglish(context.getString(R.string.qry_def_order_inner_sql), Integer.valueOf(i4), ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getLogoSqlCreator().getTableName(Service.class), defOrder.getInnerSql(), Integer.valueOf(LineType.SERVICE.value));
                        } else {
                            formatStringEnglish = ContextUtils.formatStringEnglish(context.getString(R.string.qry_product_defOrder), Integer.valueOf(i4), Integer.valueOf(LineType.SERVICE.value));
                        }
                        if (defOrder.getUseMinStock() != 1) {
                            str7 = "";
                        } else {
                            str7 = " ";
                        }
                        str8 = formatStringEnglish;
                    }
                    str9 = " , W.LINENO_ ";
                    str10 = " , LINENO_ ";
                    str11 = " , W.UNITREF AS DUNITREF";
                    obj = " , DUNITREF";
                    obj2 = " , W.ISSLCTUNIT ";
                } else {
                    obj2 = " , 0 AS ISSLCTUNIT ";
                    str8 = "";
                    str7 = str8;
                    str9 = str7;
                    str10 = str9;
                    str11 = str10;
                    obj = " , 0  AS DUNITREF ";
                }
                String string = context.getString(R.string.qry_get_service_list);
                Integer valueOf = Integer.valueOf(DateAndTimeUtils.getLogoDateInt());
                if (TextUtils.isEmpty(StringUtils.getBarcodeText(arrayList))) {
                    barcodeText = StringUtils.getBarcodeText(arrayList);
                } else {
                    barcodeText = "'" + str2 + "'";
                }
                return ContextUtils.formatStringEnglish(string, "I.NAME", " , ISSLCTUNIT ", obj2, str13, valueOf, str8, str6, str7, barcodeText, str3, str9, str10, str11, obj).replaceAll("\\s*[\\r\\n]+\\s*", "").trim();
            }
            format = String.format(" AND %s", context.getString(R.string.qry_get_product_search_only_barcode, StringUtils.getBarcodeText(arrayList)));
        }
        str6 = format;
        if (i4 == 0) {
        }
        String string2 = context.getString(R.string.qry_get_service_list);
        Integer valueOf2 = Integer.valueOf(DateAndTimeUtils.getLogoDateInt());
        if (TextUtils.isEmpty(StringUtils.getBarcodeText(arrayList))) {
        }
        return ContextUtils.formatStringEnglish(string2, "I.NAME", " , ISSLCTUNIT ", obj2, str13, valueOf2, str8, str6, str7, barcodeText, str3, str9, str10, str11, obj).replaceAll("\\s*[\\r\\n]+\\s*", "").trim();
    }
    public String getProductSortListSql(Context context, ProductListParameter productListParameter, int i2) {
        String str = " ORDER BY ITEMCODE ";
        if (i2 > 0) {
            List<T> tableWhere = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTableWhere(DefOrder.class, "LOGICALREF=?", new String[]{StringUtils.convertIntToString(i2)});
            if (tableWhere != null && !tableWhere.isEmpty()) {
                DefOrder defOrder = (DefOrder) tableWhere.get(0);
                if (!productListParameter.isSortChanged()) {
                    if (defOrder.getStype() == 2) {
                        str = " ORDER BY LINENO_ ";
                    }
                }
            } else {
                str = "";
            }
        }
        return str + " COLLATE NOCASE ";
    }
    public String getProductDetailPriceSql(int i2) {
        String str = "SELECT DISTINCT IP.* FROM ITEMPRICE IP LEFT JOIN TRADINGGRP T ON IP.TRADINGGRP = T.CODE LEFT JOIN TRADINGGRP CT ON IP.CLTRADINGGRP = CT.CODE LEFT JOIN CLCARD C ON C.CODE = IP.CLIENTCODE LEFT JOIN WORUSERCUSTOMERS S ON C.LOGICALREF = S.CLIENTREF WHERE ITEMREF ='" + i2 + "' AND IFNULL(T.ACTIVE,0)=0 AND IFNULL(CT.ACTIVE,0)=0 AND " + ContextUtils.getStringResource(R.string.qry_where_pricelist_condition, DateAndTimeUtils.nowDate("yyyyMMdd"), "IP.") + "AND (IP.CLIENTCODE IN (SELECT C.CODE FROM CLCARD C) OR IP.CLIENTCODE = '') ";
        if (!ErpCreator.getInstance().getmBaseErp().getRouteNewSystem()) {
            return str;
        }
        return str + " AND (S.USERID = " + ErpCreator.getInstance().getmBaseErp().getUser().getUserId() + " OR IP.CLIENTCODE = '') ";
    }
    public String getProductDetailStockSql(int i2, boolean z) {
        String str = z ? "SERVICEUNITS" : "ITEMUNITS";
        String replace = getParamValue(ParameterTypes.ptGeneralSettings_Warehouse).replace(";", ",");
        String str2 = "";
        if (!replace.equals("")) {
            str2 = " AND W.NR IN (" + replace + ") ";
        }
        return "SELECT S.* , W.* , IFNULL(U.CODE,'') AS UNITCODE,VS.ONHAND VONHAND,VS.REALSTOCK VREALSTOCK, V.CODE VCODE,V.NAME VNAME FROM ITEMSTOCK AS S  LEFT JOIN WAREHOUSE AS W ON S.WAREHOUSENR = W.NR  LEFT JOIN (SELECT * FROM " + str + " WHERE ITEMREF = " + i2 + " GROUP BY UNITSETREF) IU ON IU.ITEMREF = S.ITEMREF  LEFT JOIN Units AS U ON IU.UNITSETREF = U.UNITSETREF AND U.MAINUNIT = 1  LEFT JOIN VARIANTSTOCK VS ON VS.ITEMREF = S.ITEMREF AND VS.WAREHOUSENR = W.NR  LEFT JOIN VARIANTS V ON V.LOGICALREF = VS.VARIANTREF WHERE S.ITEMREF ='" + i2 + "'" + str2;
    }
    public String getProductDetailInfoSql(int i2, boolean z) {
        if (z) {
            return ContextUtils.getStringResource(R.string.qry_get_service_info, Integer.valueOf(i2));
        }
        return ContextUtils.getStringResource(R.string.qry_get_product_info, Integer.valueOf(i2));
    }
    public String getCustomerShipAddressSql(Context context, int i2, String str) {
        String str2 = "SELECT * FROM SHIPADDRESS WHERE CLREF=" + i2;
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        String replaceAll = str.replaceAll("'", "''");
        return ((str2 + " AND (CODE LIKE '%" + replaceAll + "%' OR CODE LIKE '%" + replaceAll.toLowerCase() + "%' OR CODE LIKE '%" + replaceAll.toUpperCase() + "%' ") + "  OR NAME LIKE '%" + replaceAll + "%' OR NAME LIKE '%" + replaceAll.toLowerCase() + "%' OR NAME LIKE '%" + replaceAll.toUpperCase() + "%' ") + "  OR ADDRESS LIKE '%" + replaceAll + "%' OR ADDRESS LIKE '%" + replaceAll.toLowerCase() + "%' OR ADDRESS LIKE '%" + replaceAll.toUpperCase() + "%')";
    }
    public ArrayList<ItemUnit> getProductUnits(int i2, boolean z) {
        String stringResource;
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList<ItemUnit> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                if (z) {
                    stringResource = ContextUtils.getStringResource(R.string.qry_get_service_units);
                } else {
                    stringResource = ContextUtils.getStringResource(R.string.qry_get_item_units);
                }
                cursor = readableDatabase.rawQuery(stringResource, new String[]{StringUtils.convertIntToString(i2)});
                ISqlManager.ItemUnitCursor itemUnitCursor = new ISqlManager.ItemUnitCursor(cursor);
                if (itemUnitCursor.moveToFirst()) {
                    do {
                        arrayList.add(itemUnitCursor.get());
                    } while (itemUnitCursor.moveToNext());
                }
                itemUnitCursor.close();
            } catch (Exception e2) {
                Log.e(TAG, "getProductUnits: ", e2);
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return arrayList;
    }
    public String getSalesDetailProductPriceSql(Context context, boolean z, String str) {
        String string = context.getString(z ? R.string.table_item_units : R.string.table_service_units);
        String stringResource = ContextUtils.getStringResource(R.string.qry_where_pricelist_condition, DateAndTimeUtils.nowDate("yyyyMMdd"), "P.");
        String str2 = "";
        String stringResource2 = (str == null || str.isEmpty()) ? "" : ContextUtils.getStringResource(R.string.qry_where_pricelist_variant_condition, "P.", str);
        if (str != null && !str.isEmpty()) {
            str2 = " VARIANTCODE DESC, ";
        }
        return ContextUtils.getStringResource(R.string.qry_get_price_local, string, stringResource, stringResource2, str2);
    }
    public String getSalesDetailProductPriceSql(Context context, int i2, boolean z, String str, int i3) {
        String string = context.getString(z ? R.string.table_item_units : R.string.table_service_units);
        String stringResource = ContextUtils.getStringResource(R.string.qry_where_pricelist_condition, DateAndTimeUtils.nowDate("yyyyMMdd"), "P.");
        String str2 = "";
        String stringResource2 = (str == null || str.isEmpty()) ? "" : ContextUtils.getStringResource(R.string.qry_where_pricelist_variant_condition, "P.", str);
        String str3 = (str == null || str.isEmpty()) ? "" : " VARIANTCODE DESC, ";
        if (i3 >= 0) {
            str2 = ContextUtils.getStringResource(R.string.qry_where_pricelist_division_condition, "P.", StringUtils.fillFormat(4, i3));
        }
        return ContextUtils.getStringResource(R.string.qry_get_price_local, string, stringResource, stringResource2, str3, str2);
    }
    public Cursor getInvoiceDetailForPrint(int i2, boolean z, int i3) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT I.*,I.VATMATRAH ,V.CODE AS VCODE,V.NAME AS VNAME,IT.CODE AS PCODE,IT.NAME AS PNAME,IT.NAME2 AS PNAME2 ,U.CODE AS UCODE, ");
        sb.append(" IT.GTIPCODE AS GTIP,P.CODE AS PAYCODE,P.DEFINITION_ AS PAYNAME,");
        sb.append(" (SELECT BARCODE FROM UNITBARCODE WHERE ITEMREF=I.ITEMREF AND UNITREF=I.UNIT LIMIT 1) AS Barkod ");
        sb.append(" , IT.TRACKTYPE,IT.LOGICALREF AS ITEMREF,NULL AS LOTNAME,NULL AS CODE,NULL AS LOTAMOUNT, NULL AS EXPDATE, 0 AS LOT, ");
        sb.append("  SP.ICUSTSUPCODE AS ICUSTSUPCODE, SP.ICUSTSUPNAME AS ICUSTSUPNAME, ");
        sb.append(" COALESCE((U.WEIGHT / WGT.CONVFACT1) * WGT.CONVFACT2, 0.00) AS WEIGHT, ");
        sb.append(" COALESCE((U.GROSSWEIGHT / GRSWGT.CONVFACT1) * GRSWGT.CONVFACT2, 0.00) AS GROSSWEIGHT, ");
        sb.append(" U.WIDTH || ' ' || IFNULL(W.CODE, '') AS WIDTH, ");
        sb.append(" U.LENGTH || ' ' || IFNULL(L.CODE , '') AS LENGTH, ");
        sb.append(" U.HEIGHT || ' ' || IFNULL(H.CODE , '') AS HEIGHT, ");
        sb.append(" COALESCE((U.VOLUME / VOL.CONVFACT1) * VOL.CONVFACT2 / 1000, 0.00) AS VOLUME ");
        sb.append(" FROM INVOICEDETAIL I ");
        sb.append(" INNER JOIN ITEMS IT ON IT.LOGICALREF=I.ITEMREF ");
        sb.append(" LEFT JOIN ITEMUNITS U ON U.LOGICALREF=I.UNIT AND U.ITEMREF=IT.LOGICALREF ");
        sb.append(" LEFT JOIN UNITS UT ON UT.LOGICALREF = I.UNIT ");
        sb.append(" LEFT JOIN UNITS WGT ON WGT.LOGICALREF = U.WEIGHTREF ");
        sb.append(" LEFT JOIN UNITS GRSWGT ON GRSWGT.LOGICALREF = U.GROSSWGHTREF ");
        sb.append(" LEFT JOIN UNITS W ON W.LOGICALREF = U.WIDTHREF ");
        sb.append(" LEFT JOIN UNITS L ON L.LOGICALREF = U.LENGTHREF ");
        sb.append(" LEFT JOIN UNITS H ON H.LOGICALREF = U.HEIGHTREF ");
        sb.append(" LEFT JOIN UNITS VOL ON VOL.LOGICALREF = U.VOLUMEREF ");
        sb.append(" LEFT JOIN VARIANTS V ON V.LOGICALREF=I.VARIANTREF AND V.ITEMREF=IT.LOGICALREF ");
        sb.append(" LEFT JOIN PAYMENT P ON P.LOGICALREF=I.PAYMENTREF ");
        sb.append(" LEFT JOIN SUPPASGN SP ON SP.ITEMREF = I.ITEMREF AND SP.CLIENTREF =");
        sb.append(i3);
        sb.append(" WHERE I.LINETYPE <> 4 AND I.SALESFICHEID=");
        sb.append(i2);
        sb.append(" UNION ");
        sb.append(" SELECT I.*,I.VATMATRAH ,V.CODE AS VCODE,V.NAME AS VNAME,IT.CODE AS PCODE,IT.NAME AS PNAME, '' AS PNAME2, U.CODE AS UCODE,");
        sb.append(" '' AS GTIP,P.CODE AS PAYCODE,P.DEFINITION_ AS PAYNAME,");
        sb.append(" (SELECT BARCODE FROM UNITBARCODE WHERE ITEMREF=I.ITEMREF AND UNITREF=I.UNIT LIMIT 1) AS Barkod ");
        sb.append(" , IT.TRACKTYPE,IT.LOGICALREF AS ITEMREF,NULL AS LOTNAME,NULL AS CODE,NULL AS LOTAMOUNT,NULL AS EXPDATE , 0 AS LOT , ");
        sb.append("  SP.ICUSTSUPCODE AS ICUSTSUPCODE, SP.ICUSTSUPNAME AS ICUSTSUPNAME, 0 AS WEIGHT, 0 AS GROSSWEIGHT,");
        sb.append(" 0 AS WIDTH, 0 AS LENGTH, 0 AS HEIGHT, 0 AS VOLUME");
        sb.append(" FROM INVOICEDETAIL I ");
        sb.append(" INNER JOIN SERVICE IT ON IT.LOGICALREF=I.ITEMREF ");
        sb.append(" LEFT JOIN SERVICEUNITS U ON U.LOGICALREF=I.UNIT AND U.ITEMREF=IT.LOGICALREF ");
        sb.append(" LEFT JOIN VARIANTS V ON V.LOGICALREF=I.VARIANTREF AND V.ITEMREF=IT.LOGICALREF ");
        sb.append(" LEFT JOIN PAYMENT P ON P.LOGICALREF=I.PAYMENTREF ");
        sb.append(" LEFT JOIN SUPPASGN SP ON SP.ITEMREF = I.ITEMREF AND SP.CLIENTREF =");
        sb.append(i3);
        sb.append(" WHERE I.LINETYPE = 4 AND I.SALESFICHEID=");
        sb.append(i2);
        if (z) {
            sb.append(" UNION ALL");
            sb.append(" SELECT I.*, I.VATMATRAH,NULL AS VCODE, NULL AS VNAME, NULL AS PCODE, NULL AS PNAME, NULL AS UCODE, NULL AS GTIP ");
            sb.append(" , NULL AS PAYCODE, NULL AS PAYNAME,NULL AS Barkod,NULL AS TRACKTYPE,I.ITEMREF AS ITEMREF,S.NAME AS LOTNAME,S.CODE,SUM(S.AMOUNT) AS LOTAMOUNT,S.EXPDATE ,1 AS LOT , ");
            sb.append(" NULL AS ICUSTSUPCODE , NULL AS ICUSTSUPNAME ");
            sb.append(" FROM SERILOT S ");
            sb.append(" INNER JOIN INVOICE D ON D.LOGICALREF = I.SALESFICHEID ");
            sb.append(" INNER JOIN INVOICEDETAIL I ON S.DETAILREF = I.LOGICALREF ");
            sb.append(" WHERE S.AMOUNT >0 AND I.SALESFICHEID=");
            sb.append(i2);
            sb.append(" AND S.SLFICHETYPE = 0 ");
            sb.append(" GROUP BY ITEMREF,CODE,EXPDATE,LOT,LOTNAME");
        }
        sb.append(" ORDER BY I.LINENR ASC,TRACKTYPE DESC ");
        return getReadableDatabase().rawQuery(sb.toString(), null);
    }
    public Cursor getInvoiceHedaerForPrint(int i2) {
        return getReadableDatabase().rawQuery(" SELECT I.*,R.CODE AS RCODE,R.DEFINITION_ AS RNAME,P.CODE AS PCODE,P.DEFINITION_ AS PNAME, P.LOGICALREF AS PAYPLANREF, F.CODE AS FCODE, F.NAME AS FNAME,  C.CODE AS CCODE,C.DEFINITION_ AS CNAME,C.SPECODE AS CSPECODE,C.CYPHCODE AS CCYPHCODE,C.ADDR1,C.ADDR2,  C.CITY,C.TOWN,C.TELNRS1,C.TELNRS2,C.FAXNR,C.TAXNR,C.TAXOFFICE,C.INCHARGE,C.DISCRATE,C.EMAILADDR, C.TRADINGGRP AS CTRADINGGRP,C.DEBIT_FLOAT,C.CREDIT_FLOAT,C.BAKIYE_FLOAT,R.ADDR1 AS RADDR1,R.ADDR2 AS RADDR2,R.CITY AS RCITY,R.DISTRICT AS RDISTRICT, F.ADDR1 AS FADDR1,F.ADDR2 AS FADDR2,F.CITY AS FCITY,F.DISTRICT AS FDISTRICT,C.DISTRICT AS CDISTRICT,C.TAXOFFCODE AS CTAXOFFCODE, F.LATITUTE AS FLATITUTE,F.LONGITUDE AS FLONGITUDE ,C.DEFINITION2 AS CNAME2 , C.TCKNO AS TC FROM INVOICE I  INNER JOIN CLCARD C ON C.LOGICALREF=I.CLREF  LEFT JOIN CLCARD R ON R.LOGICALREF=I.SHIPACCREF  LEFT JOIN SHIPADDRESS F ON F.LOGICALREF=I.SHIPADDRREF  LEFT JOIN PAYMENT P ON P.LOGICALREF=I.PAYMENTREF  WHERE I.LOGICALREF=" + i2, null);
    }
    public Cursor getOrderDetailForPrint(int i2) {
        return getReadableDatabase().rawQuery(" SELECT I.*,V.CODE AS VCODE,V.NAME AS VNAME,IT.CODE AS PCODE, IT.NAME AS PNAME, IT.NAME2 AS PNAME2, U.CODE AS UCODE, IT.GTIPCODE,P.CODE AS PAYCODE,P.DEFINITION_ AS PAYNAME, (SELECT BARCODE FROM UNITBARCODE WHERE ITEMREF=I.ITEMREF AND UNITREF=I.UNIT LIMIT 1) AS Barkod,COALESCE((U.WEIGHT / WGT.CONVFACT1) * WGT.CONVFACT2, 0.00) AS WEIGHT, COALESCE((U.GROSSWEIGHT / GRSWGT.CONVFACT1) * GRSWGT.CONVFACT2, 0.00) AS GROSSWEIGHT, U.WIDTH || ' ' || IFNULL(W.CODE, '') AS WIDTH, U.LENGTH || ' ' || IFNULL(L.CODE , '') AS LENGTH, U.HEIGHT || ' ' || IFNULL(H.CODE , '') AS HEIGHT, COALESCE((U.VOLUME / VOL.CONVFACT1) * VOL.CONVFACT2 / 1000, 0.00) AS VOLUME  FROM ORDERDETAIL I  INNER JOIN ITEMS IT ON IT.LOGICALREF=I.ITEMREF  LEFT JOIN ITEMUNITS U ON U.LOGICALREF=I.UNIT AND U.ITEMREF=IT.LOGICALREF  LEFT JOIN UNITS UT ON UT.LOGICALREF = I.UNIT  LEFT JOIN UNITS WGT ON WGT.LOGICALREF = U.WEIGHTREF  LEFT JOIN UNITS GRSWGT ON GRSWGT.LOGICALREF = U.GROSSWGHTREF  LEFT JOIN UNITS W ON W.LOGICALREF = U.WIDTHREF  LEFT JOIN UNITS L ON L.LOGICALREF = U.LENGTHREF  LEFT JOIN UNITS H ON H.LOGICALREF = U.HEIGHTREF  LEFT JOIN UNITS VOL ON VOL.LOGICALREF = U.VOLUMEREF  LEFT JOIN VARIANTS V ON V.LOGICALREF=I.VARIANTREF AND V.ITEMREF=IT.LOGICALREF  LEFT JOIN PAYMENT P ON P.LOGICALREF=I.PAYMENTREF  WHERE I.LINETYPE <> 4 AND I.SALESFICHEID=" + i2 + " UNION  SELECT I.*,V.CODE AS VCODE,V.NAME AS VNAME,IT.CODE AS PCODE,IT.NAME AS PNAME, '' AS PNAME2,U.CODE AS UCODE, '' AS GTIPCODE,P.CODE AS PAYCODE,P.DEFINITION_ AS PAYNAME, (SELECT BARCODE FROM UNITBARCODE WHERE ITEMREF=I.ITEMREF AND UNITREF=I.UNIT LIMIT 1) AS Barkod, 0 AS WEIGHT, 0 AS GROSSWEIGHT, 0 AS WIDTH, 0 AS LENGTH, 0 AS HEIGHT, 0 AS VOLUME  FROM ORDERDETAIL I  INNER JOIN SERVICE IT ON IT.LOGICALREF=I.ITEMREF  LEFT JOIN SERVICEUNITS U ON U.LOGICALREF=I.UNIT AND U.ITEMREF=IT.LOGICALREF  LEFT JOIN VARIANTS V ON V.LOGICALREF=I.VARIANTREF AND V.ITEMREF=IT.LOGICALREF  LEFT JOIN PAYMENT P ON P.LOGICALREF=I.PAYMENTREF  WHERE I.LINETYPE = 4 AND I.SALESFICHEID=" + i2 + " ORDER BY I.LINENR ASC", null);
    }
    public Cursor getOrderHedaerForPrint(int i2) {
        return getReadableDatabase().rawQuery(" SELECT I.*,R.CODE AS RCODE,R.DEFINITION_ AS RNAME,P.CODE AS PCODE,P.DEFINITION_ AS PNAME,F.CODE AS FCODE, F.NAME AS FNAME,  C.CODE AS CCODE,C.DEFINITION_ AS CNAME,C.SPECODE AS CSPECODE,C.CYPHCODE AS CCYPHCODE,C.ADDR1,C.ADDR2,  C.CITY,C.TOWN,C.TELNRS1,C.TELNRS2,C.FAXNR,C.TAXNR,C.TAXOFFICE,C.INCHARGE,C.DISCRATE,C.EMAILADDR, C.TRADINGGRP AS CTRADINGGRP,C.DEBIT_FLOAT,C.CREDIT_FLOAT,C.BAKIYE_FLOAT,R.ADDR1 AS RADDR1,R.ADDR2 AS RADDR2,R.CITY AS RCITY,R.DISTRICT AS RDISTRICT, F.ADDR1 AS FADDR1,F.ADDR2 AS FADDR2,F.CITY AS FCITY,F.DISTRICT AS FDISTRICT,C.DISTRICT AS CDISTRICT,C.TAXOFFCODE AS CTAXOFFCODE, F.LATITUTE AS FLATITUTE,F.LONGITUDE AS FLONGITUDE,C.TCKNO AS TC  FROM ORDERS I  INNER JOIN CLCARD C ON C.LOGICALREF=I.CLREF  LEFT JOIN CLCARD R ON R.LOGICALREF=I.SHIPACCREF  LEFT JOIN SHIPADDRESS F ON F.LOGICALREF=I.SHIPADDRREF  LEFT JOIN PAYMENT P ON P.LOGICALREF=I.PAYMENTREF  WHERE I.LOGICALREF=" + i2, null);
    }
    public Cursor getWhTransferHeaderForPrint(int i2) {
        return getReadableDatabase().rawQuery(" SELECT I.*,R.CODE AS RCODE,R.DEFINITION_ AS RNAME,P.CODE AS PCODE,P.DEFINITION_ AS PNAME, P.LOGICALREF AS PAYPLANREF, F.CODE AS FCODE, F.NAME AS FNAME,  C.CODE AS CCODE,C.DEFINITION_ AS CNAME,C.SPECODE AS CSPECODE,C.CYPHCODE AS CCYPHCODE,C.ADDR1,C.ADDR2,  C.CITY,C.TOWN,C.TELNRS1,C.TELNRS2,C.FAXNR,C.TAXNR,C.TAXOFFICE,C.INCHARGE,C.DISCRATE,C.EMAILADDR, C.TRADINGGRP AS CTRADINGGRP,C.DEBIT_FLOAT,C.CREDIT_FLOAT,C.BAKIYE_FLOAT,R.ADDR1 AS RADDR1,R.ADDR2 AS RADDR2,R.CITY AS RCITY,R.DISTRICT AS RDISTRICT, F.ADDR1 AS FADDR1,F.ADDR2 AS FADDR2,F.CITY AS FCITY,F.DISTRICT AS FDISTRICT,C.DISTRICT AS CDISTRICT,C.TAXOFFCODE AS CTAXOFFCODE, F.LATITUTE AS FLATITUTE,F.LONGITUDE AS FLONGITUDE ,C.DEFINITION2 AS CNAME2 , C.TCKNO AS TC, C.CREDIT AS CREDIT FROM WHTRANSFER I  INNER JOIN CLCARD C ON C.LOGICALREF=I.CLREF  LEFT JOIN CLCARD R ON R.LOGICALREF=I.SHIPACCREF  LEFT JOIN SHIPADDRESS F ON F.LOGICALREF=I.SHIPADDRREF  LEFT JOIN PAYMENT P ON P.LOGICALREF=I.PAYMENTREF  WHERE I.LOGICALREF=" + i2, null);
    }
    public Cursor getWhTransferDetailForPrint(int i2, boolean z, int i3) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT I.*,I.VATMATRAH ,V.CODE AS VCODE,V.NAME AS VNAME,IT.CODE AS PCODE,IT.NAME AS PNAME,IT.NAME2 AS PNAME2 ,U.CODE AS UCODE, ");
        sb.append(" IT.GTIPCODE AS GTIP,P.CODE AS PAYCODE,P.DEFINITION_ AS PAYNAME,");
        sb.append(" (SELECT BARCODE FROM UNITBARCODE WHERE ITEMREF=I.ITEMREF AND UNITREF=I.UNIT LIMIT 1) AS BARKOD ");
        sb.append(" , IT.TRACKTYPE,IT.LOGICALREF AS ITEMREF,NULL AS LOTNAME,NULL AS CODE,NULL AS LOTAMOUNT, NULL AS EXPDATE, 0 AS LOT, ");
        sb.append("  SP.ICUSTSUPCODE AS ICUSTSUPCODE, SP.ICUSTSUPNAME AS ICUSTSUPNAME, ");
        sb.append(" COALESCE((U.WEIGHT / WGT.CONVFACT1) * WGT.CONVFACT2, 0.00) AS WEIGHT, ");
        sb.append(" COALESCE((U.GROSSWEIGHT / GRSWGT.CONVFACT1) * GRSWGT.CONVFACT2, 0.00) AS GROSSWEIGHT ");
        sb.append(" FROM WHTRANSFERDETAIL I ");
        sb.append(" INNER JOIN ITEMS IT ON IT.LOGICALREF=I.ITEMREF ");
        sb.append(" LEFT JOIN ITEMUNITS U ON U.LOGICALREF=I.UNIT AND U.ITEMREF=IT.LOGICALREF ");
        sb.append(" LEFT JOIN UNITS UT ON UT.LOGICALREF = I.UNIT ");
        sb.append(" LEFT JOIN UNITS WGT ON WGT.LOGICALREF = U.WEIGHTREF ");
        sb.append(" LEFT JOIN UNITS GRSWGT ON GRSWGT.LOGICALREF = U.GROSSWGHTREF ");
        sb.append(" LEFT JOIN VARIANTS V ON V.LOGICALREF=I.VARIANTREF AND V.ITEMREF=IT.LOGICALREF ");
        sb.append(" LEFT JOIN PAYMENT P ON P.LOGICALREF=I.PAYMENTREF ");
        sb.append(" LEFT JOIN SUPPASGN SP ON SP.ITEMREF = I.ITEMREF AND SP.CLIENTREF =");
        sb.append(i3);
        sb.append(" WHERE I.LINETYPE <> 4 AND I.SALESFICHEID=");
        sb.append(i2);
        sb.append(" UNION ");
        sb.append(" SELECT I.*,I.VATMATRAH ,V.CODE AS VCODE,V.NAME AS VNAME,IT.CODE AS PCODE,IT.NAME AS PNAME, '' AS PNAME2, U.CODE AS UCODE,");
        sb.append(" '' AS GTIP,P.CODE AS PAYCODE,P.DEFINITION_ AS PAYNAME,");
        sb.append(" (SELECT BARCODE FROM UNITBARCODE WHERE ITEMREF=I.ITEMREF AND UNITREF=I.UNIT LIMIT 1) AS Barkod ");
        sb.append(" , IT.TRACKTYPE,IT.LOGICALREF AS ITEMREF,NULL AS LOTNAME,NULL AS CODE,NULL AS LOTAMOUNT,NULL AS EXPDATE , 0 AS LOT , ");
        sb.append("  SP.ICUSTSUPCODE AS ICUSTSUPCODE, SP.ICUSTSUPNAME AS ICUSTSUPNAME, 0 AS WEIGHT, 0 AS GROSSWEIGHT ");
        sb.append(" FROM WHTRANSFERDETAIL I ");
        sb.append(" INNER JOIN SERVICE IT ON IT.LOGICALREF=I.ITEMREF ");
        sb.append(" LEFT JOIN SERVICEUNITS U ON U.LOGICALREF=I.UNIT AND U.ITEMREF=IT.LOGICALREF ");
        sb.append(" LEFT JOIN VARIANTS V ON V.LOGICALREF=I.VARIANTREF AND V.ITEMREF=IT.LOGICALREF ");
        sb.append(" LEFT JOIN PAYMENT P ON P.LOGICALREF=I.PAYMENTREF ");
        sb.append(" LEFT JOIN SUPPASGN SP ON SP.ITEMREF = I.ITEMREF AND SP.CLIENTREF =");
        sb.append(i3);
        sb.append(" WHERE I.SALESFICHEID=");
        sb.append(i2);
        if (z) {
            sb.append(" UNION ALL");
            sb.append(" SELECT I.*, I.VATMATRAH,NULL AS VCODE, NULL AS VNAME, NULL AS PCODE, NULL AS PNAME, NULL AS UCODE, NULL AS GTIP ");
            sb.append(" , NULL AS PAYCODE, NULL AS PAYNAME,NULL AS Barkod,NULL AS TRACKTYPE,I.ITEMREF AS ITEMREF,S.NAME AS LOTNAME,S.CODE,SUM(S.AMOUNT) AS LOTAMOUNT,S.EXPDATE ,1 AS LOT , ");
            sb.append(" NULL AS ICUSTSUPCODE , NULL AS ICUSTSUPNAME ");
            sb.append(" FROM SERILOT S ");
            sb.append(" INNER JOIN INVOICE D ON D.LOGICALREF = I.SALESFICHEID ");
            sb.append(" INNER JOIN INVOICEDETAIL I ON S.DETAILREF = I.LOGICALREF ");
            sb.append(" WHERE S.AMOUNT >0 AND I.SALESFICHEID=");
            sb.append(i2);
            sb.append(" AND S.SLFICHETYPE = 0 ");
            sb.append(" GROUP BY ITEMREF,CODE,EXPDATE,LOT,LOTNAME");
        }
        sb.append(" ORDER BY I.LINENR ASC,TRACKTYPE DESC ");
        return getReadableDatabase().rawQuery(sb.toString(), null);
    }
    public Cursor getCustomerUnsentAccountBalance(int i2) {
        return getReadableDatabase().rawQuery("SELECT SUM(ALLDEBIT) ALLDEBIT, SUM(ALLCREDIT) ALLCREDIT, SUM(ALLDEBIT) - SUM(ALLCREDIT) AS ALLBALANCE FROM (     SELECT TOTALNET AS ALLDEBIT ,0 AS ALLCREDIT FROM INVOICE WHERE ISTRANSFER = 0 AND SALESTYPE = 2 AND CLREF = " + i2 + "    UNION ALL     SELECT 0 AS ALLDEBIT ,TOTALNET AS ALLCREDIT FROM INVOICE WHERE ISTRANSFER = 0 AND SALESTYPE = 3 AND CLREF = " + i2 + "    UNION ALL     SELECT TOTALNET AS ALLDEBIT ,0 AS ALLCREDIT FROM INVOICE WHERE ISTRANSFER = 0 AND SALESTYPE = 8 AND CLREF = " + i2 + "    UNION ALL     SELECT 0 AS ALLDEBIT ,TOTALNET AS ALLCREDIT FROM INVOICE WHERE ISTRANSFER = 0 AND SALESTYPE = 9 AND CLREF = " + i2 + "    UNION ALL     SELECT 0 AS ALLDEBIT ,TOTAL AS ALLCREDIT FROM CASECASH WHERE ISTRANSFER = 0 AND CLREF = " + i2 + "    UNION ALL     SELECT 0 AS ALLDEBIT ,TOTAL AS ALLCREDIT FROM CASHCREDIT WHERE ISTRANSFER = 0 AND CLREF = " + i2 + "    UNION ALL     SELECT 0 AS ALLDEBIT ,TOTAL AS ALLCREDIT FROM CHEQUEDEED WHERE ISTRANSFER = 0 AND CLREF = " + i2 + ") ", null);
    }
    public Cursor getCustomerBalanceInfoAfterDate(int i2, String str) {
        return getReadableDatabase().rawQuery("SELECT SUM(ALLDEBIT) ALLDEBIT, SUM(ALLCREDIT) ALLCREDIT, SUM(ALLDEBIT) - SUM(ALLCREDIT) AS ALLBALANCE FROM (     SELECT TOTALNET AS ALLDEBIT ,0 AS ALLCREDIT FROM INVOICE WHERE SALESTYPE = 2 AND CLREF = " + i2 + " AND GDATE >= '" + str + "'    UNION ALL     SELECT 0 AS ALLDEBIT ,TOTALNET AS ALLCREDIT FROM INVOICE WHERE SALESTYPE = 3 AND CLREF = " + i2 + " AND GDATE >= '" + str + "'    UNION ALL     SELECT TOTALNET AS ALLDEBIT ,0 AS ALLCREDIT FROM INVOICE WHERE SALESTYPE = 8 AND CLREF = " + i2 + " AND GDATE >= '" + str + "'    UNION ALL     SELECT 0 AS ALLDEBIT ,TOTALNET AS ALLCREDIT FROM INVOICE WHERE SALESTYPE = 9 AND CLREF = " + i2 + " AND GDATE >= '" + str + "'    UNION ALL     SELECT 0 AS ALLDEBIT ,TOTAL AS ALLCREDIT FROM CASECASH WHERE CLREF = " + i2 + " AND GDATE >= '" + str + "'    UNION ALL     SELECT 0 AS ALLDEBIT ,TOTAL AS ALLCREDIT FROM CASHCREDIT WHERE CLREF = " + i2 + " AND GDATE >= '" + str + "'    UNION ALL     SELECT 0 AS ALLDEBIT ,TOTAL AS ALLCREDIT FROM CHEQUEDEED WHERE CLREF = " + i2 + " AND GDATE >= '" + str + "') ", null);
    }
    public String getCustomerLastPaymentInfo(int i2) {
        double d2 = 0.0d;
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT TOTAL FROM ( SELECT TOTAL,GDATE FROM CASECASH WHERE CLREF = " + i2 + " AND GDATE BETWEEN '" + DateAndTimeUtils.nowDate() + " 00:00:00' AND '" + DateAndTimeUtils.nowDate() + " 23:59:59'  UNION ALL  SELECT TOTAL,GDATE FROM CASHCREDIT WHERE CLREF = " + i2 + "  AND GDATE BETWEEN '" + DateAndTimeUtils.nowDate() + " 00:00:00' AND '" + DateAndTimeUtils.nowDate() + " 23:59:59'  UNION ALL  SELECT TOTAL,GDATE FROM CHEQUEDEED WHERE CLREF = " + i2 + "  AND GDATE BETWEEN '" + DateAndTimeUtils.nowDate() + " 00:00:00' AND '" + DateAndTimeUtils.nowDate() + " 23:59:59' ) ORDER BY GDATE DESC LIMIT 1", null);
                if (cursor != null && cursor.moveToFirst()) {
                    d2 = cursor.getDouble(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getCustomerLastPaymentInfo: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return String.valueOf(d2);
    }
    public Cursor isCustomerEnterPenetrationToday(int i2, int i3) {
        return getReadableDatabase().rawQuery(" SELECT ID, ISTRANSFER, IFNULL(PNT_GUID,'') AS GUID FROM USERMAINPENETRATION WHERE CLIENTREF = " + i2 + " AND  PENETRATION_ID = " + i3 + " AND DATE BETWEEN '" + DateAndTimeUtils.nowDate() + " 00:00:00' AND '" + DateAndTimeUtils.nowDate() + " 23:59:59' ", null);
    }

    public double getCurrRateWithDate(int i2, String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String str2 = "1.0";
        Cursor cursor = null;
        try {
            try {
                cursor = readableDatabase.rawQuery(ContextUtils.getStringResource(R.string.qry_curr_rate), new String[]{StringUtils.convertIntToString(i2), StringUtils.convertIntToString(DateAndTimeUtils.getLogoDateInt(str))});
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    str2 = cursor.getString(cursor.getColumnIndex("RATE"));
                }
            } catch (Exception e2) {
                Log.e(TAG, "getCurrRates: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return Double.parseDouble(str2);
    }
    public String getAlternativeProductList(Context context, AlternativeProductListOptions alternativeProductListOptions) {
        alternativeProductListOptions.productListParameter().setCustomFilter(String.format(" AND %s", context.getString(R.string.qry_get_product_search_logicalref, StringUtils.getKeyValuePairArrayToString(alternativeProductListOptions.logicalRefList(), ","))));
        return getProductListSql(context, alternativeProductListOptions.productListParameter(), alternativeProductListOptions.customerRef(), alternativeProductListOptions.paymentTradeGroup(), alternativeProductListOptions.warehouseNumber(), null, "", 0, getProductSortListSql(context, alternativeProductListOptions.productListParameter(), 0), false, null, alternativeProductListOptions.paymentRef(), "", "", alternativeProductListOptions.divisionNumber(), null);
    }

    public String getProductDetailUnitSql(int i2, boolean z) {
        return ContextUtils.getStringResource(z ? R.string.qry_productDetail_serviceUnits : R.string.qry_productDetail_itemUnits, Integer.valueOf(i2));
    }

    public String getItemSizeSql(SalesType salesType) {
        return ContextUtils.getStringResource(R.string.get_item_sizes, getTableName(salesType).getDetailTable(), getTableName(salesType).getTable());
    }
    public double getLocalCreditTotal(int i2) {
        double d2 = 0.0d;
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery(ContextUtils.getStringResource(R.string.get_not_transferred_credit_total), new String[]{StringUtils.convertIntToString(i2)});
                if (cursor != null && cursor.moveToFirst()) {
                    d2 = cursor.getDouble(cursor.getColumnIndex(ContextUtils.getmContext().getString(R.string.column_total)));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursor != null) {
                }
            }
            return d2;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
    public double getLocalDebitTotal(int i2) {
        double d2 = 0.0d;
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery(ContextUtils.getStringResource(R.string.get_not_transferred_debit_total), new String[]{StringUtils.convertIntToString(i2)});
                if (cursor != null && cursor.moveToFirst()) {
                    d2 = cursor.getDouble(cursor.getColumnIndex(ContextUtils.getmContext().getString(R.string.column_total)));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursor != null) {
                }
            }
            return d2;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
    public List<ItemUnits> getItemUnits(int i2) {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ItemUnits.class, "ITEMREF=?", new String[]{String.valueOf(i2)});
    }
    public double getDefinedPurchasePrice(int i2) {
        double d2 = 0.0d;
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery(ContextUtils.getStringResource(R.string.get_defined_purchase_price), new String[]{StringUtils.convertIntToString(i2)});
                if (cursor != null && cursor.moveToFirst()) {
                    d2 = cursor.getDouble(cursor.getColumnIndex(ContextUtils.getmContext().getString(R.string.column_price)));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursor != null) {
                }
            }
            return d2;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
}
