package com.proje.mobilesales.core.sql.netsis;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.CustomerMenuType;
import com.proje.mobilesales.core.enums.LineType;
import com.proje.mobilesales.core.enums.ProductTreeGroupListType;
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
import com.proje.mobilesales.features.customer.model.CustomerRisk;
import com.proje.mobilesales.features.dbmodel.DefOrder;
import com.proje.mobilesales.features.dbmodel.Service;
import com.proje.mobilesales.features.model.AlternativeProductListOptions;
import com.proje.mobilesales.features.model.BarcodeResult;
import com.proje.mobilesales.features.product.model.ItemUnit;
import com.proje.mobilesales.features.product.model.ProductListParameter;
import com.proje.mobilesales.features.product.model.database.Item;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import com.proje.mobilesales.features.product.model.database.RecommendedProducts;
import java.util.ArrayList;
import java.util.List;

public class NetsisSqlHelper<T> extends SqlHelper<T> {
    public static final int DATABASE_VERSION = 249;
    private static final String TAG = "NetsisSqlHelper";

    public int getBarcodeScannerSql() {
        return R.string.net_qry_get_barcode_ref;
    }

    @Override
    public int getClCardCurrency(int i2) {
        return 0;
    }

    public int getDatabaseVersion() {
        return 249;
    }
    public double getDefinedPurchasePrice(int i2) {
        return 0.0d;
    }

    public int getNegativeRiskValue(int i2) {
        return i2 == 0 ? 1 : 0;
    }

    public int getSalesDetailProductUnitSql(boolean z) {
        return z ? R.string.net_qry_sales_detail_unit : R.string.net_qry_sales_detail_service_unit;
    }

    public int getSalesProductSetPriceSql() {
        return R.string.net_qry_get_price_local_set;
    }

    public Cursor getWhTransferDetailForPrint(int i2, boolean z, int i3) {
        return null;
    }
    public Cursor getWhTransferHeaderForPrint(int i2) {
        return null;
    }

    public NetsisSqlHelper(Context context, String str) {
        this(context, str, null, 249);
    }

    public NetsisSqlHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i2) {
        super(context, str, cursorFactory, i2);
        this.mISqlCreator = new NetsisSqlCreator();
    }

    public String getTodoListSql(Context context) {
        return context.getString(R.string.net_qry_get_todo_list);
    }

    public String getCustomerListSql(Context context, CustomerFilter customerFilter) {
        Exception exc;
        String str = "";
        double d2;
        String str2;
        String str3;
        boolean z;
        String str4 = "";
        BaseErp baseErp;
        String str5 = "";
        String str6;
        String str7 = "";
        BaseErp baseErp2 = ErpCreator.getInstance().getmBaseErp();
        boolean routeNewSystem = baseErp2.getRouteNewSystem();
        boolean z2 = baseErp2.getOffRoadVisit() && customerFilter.isSelectedAllRoute();
        boolean showMainCustomers = baseErp2.getShowMainCustomers();
        Exception e;
        try {
            if (!customerFilter.isUseNear()) {
                try {
                    str = "SELECT (SELECT TITLE FROM SHIPAGENT WHERE SHIPAGENT.CODE=C.DELIVERYFIRM) AS TITLE,(SELECT DEFINITION_ FROM SHIPTYPE WHERE SHIPTYPE.CODE=C.DELIVERYMETHOD) AS SDEF , G.LATITUDE, G.LONGTITUDE , P.ODEMEPLAN AS PAYMENTDEF, MAX(O.GDATE) AS LASTORDERDATE, C.* , CIMG.CUSTOMERIMAGE, " + getShipmentColumn(z2) + "EXPLANATION1 AS INFONOTE , IFNULL(CUSTOP.OPERATIONSTODAY,0) OPERATIONSTODAY  FROM CLCARD C";
                    d2 = 25.0d;
                    str2 = "%'  OR C.INCHARGE LIKE '";
                    str3 = "%'  OR C.TELNRS1 LIKE '";
                    z = showMainCustomers;
                } catch (Exception e2) {
                    e = e2;
                    exc = e;
                    Log.e(TAG, "GetCustomers: ", exc);
                    return str7;
                }
            } else {
                double latitude = baseErp2.getLatitude();
                double longitude = baseErp2.getLongitude();
                double d3 = (latitude * 3.141592653589793d) / 180.0d;
                z = showMainCustomers;
                double sin = Math.sin(d3);
                double cos = Math.cos(d3);
                double d4 = (longitude * 3.141592653589793d) / 180.0d;
                str3 = "%'  OR C.TELNRS1 LIKE '";
                double sin2 = Math.sin(d4);
                double cos2 = Math.cos(d4);
                double cos3 = Math.cos(customerFilter.getNearRpDistance() / 6371.0d);
                try {
                    StringBuilder sb = new StringBuilder();
                    str2 = "%'  OR C.INCHARGE LIKE '";
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
                    sb.append("CIMG.CUSTOMERIMAGE, EXPLANATION1 AS INFONOTE, IFNULL(CUSTOP.OPERATIONSTODAY,0) OPERATIONSTODAY FROM CLCARD C");
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
                    str4 = str + " LEFT JOIN ROUTETRS R ON R.CLIENTREF=C.LOGICALREF";
                }
                String str8 = (((((str + " LEFT JOIN CUSTGPSINFO G ON G.CLCODE=C.CODE") + " LEFT JOIN PAYMENT P ON P.CODE=C.PAYMENTCODE") + " LEFT JOIN ORDERS O ON O.CLCODE=C.CODE") + " LEFT JOIN CUSTOMERIMAGE CIMG ON CIMG.CUSTOMERREF=C.LOGICALREF") + " LEFT JOIN( " + ContextUtils.getStringResource(R.string.net_get_isCustomerHasDoneOperationToday) + ") CUSTOP ON CUSTOP.CLCODE = C.CODE ") + " WHERE 1=1  AND (ACTIVE=0 OR (ACTIVE = 1 AND C.ISTRANSFER = 0)) ";
                if (customerFilter.isUseNear()) {
                    str8 = str8 + " AND DIST>" + d2 + " ";
                }
                if (TextUtils.isEmpty(customerFilter.getFilter())) {
                    baseErp = baseErp2;
                    if (customerFilter.getSearchType() == SearchType.CONTAINING_SEARCH_WORDS.getValue()) {
                        str6 = str8 + " AND (C.DEFINITION_ LIKE '%" + customerFilter.getFilter().replace(" ", "%") + "%' OR C.DEFINITION_ LIKE '%" + customerFilter.getFilter().toLowerCase().replace(" ", "%") + "%' OR C.DEFINITION_ LIKE '%" + customerFilter.getFilter().toUpperCase().replace(" ", "%") + "%' OR C.CODE LIKE '%" + customerFilter.getFilter().replace(" ", "%") + "%' OR C.CODE LIKE '%" + customerFilter.getFilter().toLowerCase().replace(" ", "%") + "%' OR C.CODE LIKE '%" + customerFilter.getFilter().toUpperCase().replace(" ", "%") + "%' OR C.EMAILADDR LIKE '%" + customerFilter.getFilter() + "%' OR C.EMAILADDR LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.EMAILADDR LIKE '%" + customerFilter.getFilter().toUpperCase() + "%' OR C.TELNRS1 LIKE '%" + customerFilter.getFilter() + "%' OR C.TELNRS1 LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.TELNRS1 LIKE '%" + customerFilter.getFilter().toUpperCase() + "%' OR C.INCHARGE LIKE '%" + customerFilter.getFilter() + "%' OR C.INCHARGE LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.INCHARGE LIKE '%" + customerFilter.getFilter().toUpperCase() + "%' OR C.SPECODE LIKE '" + customerFilter.getFilter() + "' OR C.SPECODE2 LIKE '" + customerFilter.getFilter() + "' OR C.SPECODE3 LIKE '" + customerFilter.getFilter() + "' OR C.SPECODE4 LIKE '" + customerFilter.getFilter() + "' OR C.SPECODE5 LIKE '" + customerFilter.getFilter() + "')";
                    } else if (customerFilter.getSearchType() == SearchType.STARTING_WITH_SEARCH_WORD.getValue()) {
                        String str9 = str3;
                        String str10 = str2;
                        String sb2 = str8 +
                                " AND (C.DEFINITION_ LIKE '" +
                                customerFilter.getFilter() +
                                "%'  OR C.DEFINITION_ LIKE '" +
                                customerFilter.getFilter().toLowerCase() +
                                "%' OR C.DEFINITION_ LIKE '" +
                                customerFilter.getFilter().toUpperCase() +
                                "%' OR C.CODE LIKE '" +
                                customerFilter.getFilter() +
                                "%'  OR C.CODE LIKE '" +
                                customerFilter.getFilter().toLowerCase() +
                                "%'  OR C.CODE LIKE '" +
                                customerFilter.getFilter().toUpperCase() +
                                "%' OR C.EMAILADDR LIKE '" +
                                customerFilter.getFilter() +
                                "%'  OR C.EMAILADDR LIKE '" +
                                customerFilter.getFilter().toLowerCase() +
                                "%'  OR C.EMAILADDR LIKE '" +
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
                                "')";
                        str6 = sb2;
                    } else {
                        str6 = str8 + " AND (C.DEFINITION_ LIKE '%" + customerFilter.getFilter() + "%' OR C.DEFINITION_ LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.DEFINITION_ LIKE '%" + customerFilter.getFilter().toUpperCase() + "%' OR C.CODE LIKE '%" + customerFilter.getFilter() + "%' OR C.CODE LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.CODE LIKE '%" + customerFilter.getFilter().toUpperCase() + "%' OR C.EMAILADDR LIKE '%" + customerFilter.getFilter() + "%' OR C.EMAILADDR LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.EMAILADDR LIKE '%" + customerFilter.getFilter().toUpperCase() + "%' OR C.TELNRS1 LIKE '%" + customerFilter.getFilter() + "%' OR C.TELNRS1 LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.TELNRS1 LIKE '%" + customerFilter.getFilter().toUpperCase() + "%' OR C.INCHARGE LIKE '%" + customerFilter.getFilter() + "%' OR C.INCHARGE LIKE '%" + customerFilter.getFilter().toLowerCase() + "%' OR C.INCHARGE LIKE '%" + customerFilter.getFilter().toUpperCase() + "%')";
                    }
                    str8 = str6;
                } else {
                    baseErp = baseErp2;
                }
                if (!TextUtils.isEmpty(customerFilter.getCityFilter())) {
                    str8 = str8 + " AND (UPPER(C.CITYCODE) LIKE '" + customerFilter.getCityFilter() + "%') ";
                }
                if (!TextUtils.isEmpty(customerFilter.getTownFilter())) {
                    str8 = str8 + " AND (UPPER(C.TOWNCODE) LIKE '" + customerFilter.getTownFilter() + "%') ";
                }
                if (!routeNewSystem) {
                    if (customerFilter.getRouteRef() > 0) {
                        str8 = str8 + " AND R.ROUTEREF=" + customerFilter.getRouteRef();
                    }
                    if (customerFilter.getDayRef() > 0) {
                        str8 = str8 + " AND S.VISITDAY=" + customerFilter.getDayRef();
                    }
                }
                if (customerFilter.getSelectedDebitOrder() != 1) {
                    str5 = str8 + " AND C.BAKIYE>0";
                } else {
                    if (customerFilter.getSelectedDebitOrder() == 2) {
                        str5 = str8 + " AND C.BAKIYE<0";
                    }
                    if (!z && baseErp.getMainClCodeList().size() > 0) {
                        str8 = str8 + " AND C.CODE NOT IN " + baseErp.getMainClCodeListNotInQuery();
                    }
                    String str11 = str8 + " GROUP BY C.LOGICALREF ";
                    if (routeNewSystem) {
                        str11 = str11 + " , SA.LOGICALREF ";
                    }
                    if (routeNewSystem) {
                        return str11 + " ORDER BY PLANNED DESC , WRP.SEQUENCE";
                    }
                    if (customerFilter.getRouteRef() > 0) {
                        return str11 + " ORDER BY R.LINENO_";
                    }
                    if (customerFilter.getDayRef() <= 0 && ErpCreator.getInstance().getmBaseErp().getRouteVisitinOutOfOrder()) {
                        if (customerFilter.getUserNerarType() != 3 && ((customerFilter.getUserNerarType() != 2 || !customerFilter.isUseNear()) && !customerFilter.isUseNear())) {
                            if (customerFilter.getSortType() != 0 && customerFilter.getSortType() != 1) {
                                if (customerFilter.getSortType() == 2) {
                                    return str11 + " ORDER BY C.DEBIT DESC ";
                                }
                                if (customerFilter.getSortType() != 3) {
                                    return str11;
                                }
                                return str11 + " ORDER BY C.CODE ";
                            }
                            return str11 + "ORDER BY UPPER(C.DEFINITION_) COLLATE UNICODE  ";
                        }
                        if (customerFilter.isUseOrderNameOrCode()) {
                            if (customerFilter.getSortType() != 0 && customerFilter.getSortType() != 1) {
                                if (customerFilter.getSortType() == 2) {
                                    return str11 + " ORDER BY C.DEBIT DESC ";
                                }
                                if (customerFilter.getSortType() != 3) {
                                    return str11;
                                }
                                return str11 + " ORDER BY C.CODE ";
                            }
                            return str11 + "ORDER BY UPPER(C.DEFINITION_) COLLATE UNICODE  ";
                        }
                        return str11 + "  ORDER BY  DIST DESC";
                    }
                    return str11 + " ORDER BY S.LINENO_";
                }
                str8 = str5;
                if (!z) {
                    str8 = str8 + " AND C.CODE NOT IN " + baseErp.getMainClCodeListNotInQuery();
                }
                String str112 = str8 + " GROUP BY C.LOGICALREF ";
            } else if (z2) {
                Boolean bool = Boolean.FALSE;
                String sb3 = ((str + " INNER JOIN WORUSERCUSTOMERS WUC ON WUC.CLIENTREF=C.CODE ") + " LEFT JOIN SHIPADDRESS SA ON WUC.CLIENTREF=SA.CODE ") + " LEFT JOIN WORROUTEPLAN WRP ON WRP.CLIENTREF=C.CODE AND WRP.SHIPMENTREF = WUC.SHIPMENTREF AND WRP.ROUTEID = (SELECT ROUTEID FROM WORROUTEDAY WHERE DAY=( " +
                        " SELECT ((CAST (((JulianDay(STRFTIME('%Y-%m-%d %H:%M:%S', '" +
                        DateAndTimeUtils.getSqlDate(bool) +
                        "')) - JulianDay(STRFTIME('%Y-%m-%d %H:%M:%S', PERIODSTARTDATE))) / 7) AS INTEGER) % " +
                        baseErp2.getRouteNewSystemPeriod() +
                        ") * 7) + CASE (SELECT CAST (strftime('%w', date('" +
                        DateAndTimeUtils.getSqlDate(bool) +
                        "')) AS INTEGER)) WHEN 0 THEN 7 ELSE (SELECT CAST (strftime('%w', date('" +
                        DateAndTimeUtils.getSqlDate(bool) +
                        "')) AS INTEGER)) END AS DAY FROM WORROUTE)) ";
                str4 = sb3 + " LEFT JOIN WORROUTEDAY WRD ON WRD.ROUTEID=WRP.ROUTEID ";
            } else {
                Boolean bool2 = Boolean.FALSE;
                String sb4 = ((str + " INNER JOIN WORROUTEPLAN WRP ON WRP.CLIENTREF=C.CODE ") + " LEFT JOIN SHIPADDRESS SA ON WRP.CLIENTREF=SA.CODE ") + " INNER JOIN WORROUTEDAY WRD ON WRD.ROUTEID=WRP.ROUTEID AND WRD.DAY=( " +
                        " SELECT ((CAST (((JulianDay(STRFTIME('%Y-%m-%d %H:%M:%S', '" +
                        DateAndTimeUtils.getSqlDate(bool2) +
                        "')) - JulianDay(STRFTIME('%Y-%m-%d %H:%M:%S', PERIODSTARTDATE))) / 7) AS INTEGER) % " +
                        baseErp2.getRouteNewSystemPeriod() +
                        ") * 7) + CASE (SELECT CAST (strftime('%w', date('" +
                        DateAndTimeUtils.getSqlDate(bool2) +
                        "')) AS INTEGER)) WHEN 0 THEN 7 ELSE (SELECT CAST (strftime('%w', date('" +
                        DateAndTimeUtils.getSqlDate(bool2) +
                        "')) AS INTEGER)) END AS DAY FROM WORROUTE) ";
                str4 = sb4;
            }
            str = str4;
            String str82 = (((((str + " LEFT JOIN CUSTGPSINFO G ON G.CLCODE=C.CODE") + " LEFT JOIN PAYMENT P ON P.CODE=C.PAYMENTCODE") + " LEFT JOIN ORDERS O ON O.CLCODE=C.CODE") + " LEFT JOIN CUSTOMERIMAGE CIMG ON CIMG.CUSTOMERREF=C.LOGICALREF") + " LEFT JOIN( " + ContextUtils.getStringResource(R.string.net_get_isCustomerHasDoneOperationToday) + ") CUSTOP ON CUSTOP.CLCODE = C.CODE ") + " WHERE 1=1  AND (ACTIVE=0 OR (ACTIVE = 1 AND C.ISTRANSFER = 0)) ";
            TextUtils.isEmpty(customerFilter.getFilter());
            TextUtils.isEmpty(customerFilter.getCityFilter());
            TextUtils.isEmpty(customerFilter.getTownFilter());
            str82 = str5;
            String str1122 = str82 + " GROUP BY C.LOGICALREF ";
        } catch (Exception e4) {
            e = e4;
        }
        return str;
    }

    public String getRouteListSql(String str) {
        String str2 = "SELECT C.CODE, C.DEFINITION_, CASE WHEN WRP.SHIPMENTREF <> '' THEN SA.CITY ELSE C.CITYCODE END AS CITY, CASE WHEN WRP.SHIPMENTREF <> '' THEN SA.CITY ELSE C.TOWNCODE END AS TOWN, SA.NAME AS SHIPNAME FROM CLCARD C";
        try {
            str2 = (("SELECT C.CODE, C.DEFINITION_, CASE WHEN WRP.SHIPMENTREF <> '' THEN SA.CITY ELSE C.CITYCODE END AS CITY, CASE WHEN WRP.SHIPMENTREF <> '' THEN SA.CITY ELSE C.TOWNCODE END AS TOWN, SA.NAME AS SHIPNAME FROM CLCARD C INNER JOIN WORROUTEPLAN WRP ON WRP.CLIENTREF=C.CODE ") + " LEFT JOIN SHIPADDRESS SA ON WRP.CLIENTREF=SA.CODE ") + " INNER JOIN WORROUTEDAY WRD ON WRD.ROUTEID=WRP.ROUTEID AND WRD.DAY=( ";
            return str2 + " SELECT ((CAST (((JulianDay(STRFTIME('%Y-%m-%d %H:%M:%S', '" + DateAndTimeUtils.getSqlSelectedDate(str) + "')) - JulianDay(STRFTIME('%Y-%m-%d %H:%M:%S', PERIODSTARTDATE))) / 7) AS INTEGER) % " + ErpCreator.getInstance().getmBaseErp().getRouteNewSystemPeriod() + ") * 7) + CASE (SELECT CAST (strftime('%w', date('" + DateAndTimeUtils.getSqlSelectedDate(str) + "')) AS INTEGER)) WHEN 0 THEN 7 ELSE (SELECT CAST (strftime('%w', date('" + DateAndTimeUtils.getSqlSelectedDate(str) + "')) AS INTEGER)) END AS DAY FROM WORROUTE) ";
        } catch (Exception e2) {
            Log.e(TAG, "GetRoutes: ", e2);
            return str2;
        }
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
    public String getDailyInfoListSql(Context context, int i2) {
        String str;
        String formatStringEnglish;
        boolean isMainFTypeVisit = FTypeControlUtils.isMainFTypeVisit();
        int i3 = R.string.net_qry_get_visit_info;
        String str2 = "";
        if (isMainFTypeVisit) {
            str = context.getString(R.string.net_qry_get_visit_info);
        } else {
            if (FTypeControlUtils.isMainFTypeOrder()) {
                i3 = R.string.net_qry_get_order_info;
            } else if (FTypeControlUtils.isMainFTypeInvoice()) {
                i3 = R.string.net_qry_get_fatura_info;
            } else if (FTypeControlUtils.isMainFTypeDispatch()) {
                i3 = R.string.net_qry_get_irsaliye_info;
            } else if (FTypeControlUtils.isMainFTypeOneToOne()) {
                i3 = R.string.net_qry_get_birebir_info;
            } else if (FTypeControlUtils.isMainFTypeCashReceipt()) {
                i3 = R.string.net_qry_get_nakit_info;
            } else if (FTypeControlUtils.isMainFTypeCreditReceipt()) {
                i3 = R.string.net_qry_get_credit_info;
            } else if (FTypeControlUtils.isMainFTypeCaseReceipt()) {
                i3 = R.string.net_qry_get_kasa_info;
            } else if (FTypeControlUtils.isMainFTypeCheckReceipt()) {
                i3 = R.string.net_qry_get_cek_info;
            } else if (FTypeControlUtils.isMainFTypeDeedReceipt()) {
                i3 = R.string.net_qry_get_senet_info;
            } else if (FTypeControlUtils.isMainFTypeAll()) {
                i3 = R.string.net_query_daily_info_union;
            } else if (FTypeControlUtils.isMainFTypeRetailInvoice()) {
                i3 = R.string.net_qry_get_perakendefatura_info;
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
        try {
            String formatStringEnglish = ContextUtils.formatStringEnglish(context.getString(R.string.net_qry_get_visit_list), str, Integer.valueOf(i3), Integer.valueOf(i4));
            if (str2 == null || str2.isEmpty()) {
                return formatStringEnglish;
            }
            return "SELECT * FROM (" + formatStringEnglish + ") AS ST WHERE ST.EXPLANATION LIKE '%" + str2 + "%'";
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "getVisitListSql: ", e2);
            return "";
        }
    }
    public String getVisitSql(Context context, int i2) {
        return ContextUtils.formatStringEnglish(context.getString(R.string.net_qry_get_visit_with_id), Integer.valueOf(i2));
    }

    public String getPenetrationListSql(Context context, String str, int i2, int i3, int i4) {
        try {
            return ContextUtils.formatStringEnglish(context.getString(R.string.net_qry_get_penetration_list), str, Integer.valueOf(i3), Integer.valueOf(i4));
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "getPenetrationListSql: ", e2);
            return "";
        }
    }
    public String getSalesListSql(Context context, int i2, CustomerOperation customerOperation, String str) {
        String str2;
        String str3 = "";
        try {
            CustomerMenuType menuType = customerOperation.getMenuType();
            CustomerMenuType customerMenuType = CustomerMenuType.SALES_ORDER;
            if (menuType == customerMenuType) {
                str3 = context.getString(R.string.net_qry_getSalesOrder);
            } else if (customerOperation.getMenuType() == CustomerMenuType.SALES_DEMAND) {
                str3 = context.getString(R.string.net_qry_getSalesDemand);
            } else if (customerOperation.getMenuType() == CustomerMenuType.SALES_INVOICE) {
                str3 = ((context.getString(R.string.net_qry_getSalesInvoice) + " I.FTYPE=1") + " AND I.INVTYPE=0") + " AND I.FACT<>1";
            } else if (customerOperation.getMenuType() == CustomerMenuType.SALES_RETURN_INVOICE) {
                str3 = ((context.getString(R.string.net_qry_getSalesInvoice) + " I.FTYPE=1") + " AND I.INVTYPE=1") + " AND I.FACT<>1";
            } else if (customerOperation.getMenuType() == CustomerMenuType.SALES_DISPATCH) {
                str3 = ((context.getString(R.string.net_qry_getSalesInvoice) + " I.FTYPE=0") + " AND I.INVTYPE=0") + " AND I.FACT<>1";
            } else if (customerOperation.getMenuType() == CustomerMenuType.SALES_RETURN_DISPATCH) {
                str3 = ((context.getString(R.string.net_qry_getSalesInvoice) + " I.FTYPE=0") + " AND I.INVTYPE=1") + " AND I.FACT<>1";
            } else if (customerOperation.getMenuType() == CustomerMenuType.SALES_RETAIL_INVOICE) {
                str3 = ((context.getString(R.string.net_qry_getSalesInvoice) + " I.FTYPE=3") + " AND I.INVTYPE=0") + " AND I.FACT<>1";
            } else if (customerOperation.getMenuType() == CustomerMenuType.SALES_RETAIL_RETURN_INVOICE) {
                str3 = ((context.getString(R.string.net_qry_getSalesInvoice) + " I.FTYPE=3") + " AND I.INVTYPE=1") + " AND I.FACT<>1";
            } else if (customerOperation.getMenuType() == CustomerMenuType.SALES_WHTRANSFER) {
                str3 = context.getString(R.string.net_qry_getSalesWhTransfer);
            }
            if (customerOperation.getMenuType() == CustomerMenuType.SALES_ONE_TO_ONE_CHANGE) {
                str3 = ((context.getString(R.string.net_qry_getSalesInvoice) + " I.FTYPE=2") + " AND I.INVTYPE=0") + " AND I.FACT<>1";
            }
            if (i2 != -1) {
                if (customerOperation.getMenuType() == customerMenuType) {
                    str2 = str3 + " AND O.CLCODE='" + getClCode(i2) + "'";
                } else {
                    str2 = str3 + " AND I.CLCODE='" + getClCode(i2) + "'";
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
                    str3 = context.getString(R.string.net_qry_get_case);
                } else if (customerOperation.getReceiptType() == ReceiptType.CHEQUE || customerOperation.getReceiptType() == ReceiptType.DEED) {
                    str3 = context.getString(R.string.net_qry_get_chequedeed);
                }
                if (customerOperation.getReceiptType() != receiptType2 && customerOperation.getReceiptType() != ReceiptType.CHEQUE) {
                    if (customerOperation.getReceiptType() != ReceiptType.CREDIT) {
                        str2 = str3 + " AND FTYPE = 1 ";
                        str3 = str2;
                    }
                    if (i2 != -1) {
                        str3 = str3 + " AND CC.CLCODE='" + getClCode(i2) + "'";
                    }
                    if (!TextUtils.isEmpty(str)) {
                        str3 = str3 + " AND (" + context.getString(R.string.column_desc1) + " LIKE '%" + str + "%' OR " + context.getString(R.string.column_desc1) + " LIKE '%" + str.toLowerCase() + "%' OR " + context.getString(R.string.column_desc1) + " LIKE '%" + str.toUpperCase() + "%' OR " + context.getString(R.string.column_fiche_no) + "=" + StringUtils.convertStringToInt(str) + ")";
                    }
                    return str3 + " " + context.getString(R.string.qry_getCashCreditOrderDesc);
                }
                str2 = str3 + " AND FTYPE = 0 ";
                str3 = str2;
                TextUtils.isEmpty(str);
                return str3 + " " + context.getString(R.string.qry_getCashCreditOrderDesc);
            }
            str3 = context.getString(R.string.net_qry_get_cash);
            if (customerOperation.getReceiptType() != receiptType2) {
                str2 = str3 + " AND FTYPE = 1 ";
                str3 = str2;
                TextUtils.isEmpty(str);
                return str3 + " " + context.getString(R.string.qry_getCashCreditOrderDesc);
            }
            str2 = str3 + " AND FTYPE = 0 ";
            str3 = str2;
            TextUtils.isEmpty(str);
            return str3 + " " + context.getString(R.string.qry_getCashCreditOrderDesc);
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "getSalesListSql:  ", e2);
            return str3;
        }
    }
    public String getProductListSql(Context context, ProductListParameter productListParameter, int i2, String str, int i3, ArrayList<BarcodeResult> arrayList, String str2, int i4, String str3, boolean z, ArrayList<Integer> arrayList2, int i5, String str4, String str5, int i6, ArrayList<RecommendedProducts> arrayList3) {
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        String str16;
        String str17;
        String str18;
        String formatStringEnglish;
        String str19;
        String str20;
        String str21;
        String str22 = "";
        String str23 = "";
        String barcodeText = "";
        boolean equals = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getLogoParamValue("FIAT_LISTE").equals(ExifInterface.LONGITUDE_EAST);
        boolean equals2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getLogoParamValue("FIAT_SISTEMI").equals(ExifInterface.LONGITUDE_EAST);
        boolean z2 = equals2 && equals;
        boolean z3 = equals2 && !equals;
        String str24 = productListParameter.isShowPrices() ? "" : " AND 1=2";
        if (!productListParameter.isWhichMaterialDesc()) {
            str6 = "I.NAME";
        } else {
            str6 = "I.NAME2";
        }
        if (i2 <= 0) {
            str7 = "I.VAT";
            str8 = "";
        } else {
            str8 = " LEFT JOIN CLCARD CLL ON CLL.CODE=ITM.CLIENTCODE ";
            str7 = "(SELECT CASE WHEN USECUSTOMERBASEDVAT=1 THEN CUSTOMERSALESVAT ELSE I.VAT END FROM CLCARD WHERE LOGICALREF = " + i2 + ") VAT";
        }
        String str25 = str8 + "WHERE 1=1 AND ITM.PTYPE=2" + str24;
        String nowDate = DateAndTimeUtils.nowDate("yyyyMMdd");
        if (z2) {
            str25 = str25 + " AND ITM.GRPCODE='" + (i2 > 0 ? getCustomerPriceGroup(i2) : "") + "' AND ITM.BEGDATE<=" + nowDate + " AND (ITM.ENDDATE = 0 OR ITM.ENDDATE>=" + nowDate + ")";
        } else if (z3) {
            str25 = str25 + " AND ((ITM.GRPCODE='" + (i2 > 0 ? getCustomerPriceGroup(i2) : "") + "' AND ITM.BEGDATE<=" + nowDate + " AND (ITM.ENDDATE = 0 OR ITM.ENDDATE>=" + nowDate + ")) OR ITM.BEGDATE=0)";
        } else if (!equals2) {
            str25 = str25 + " AND ITM.BEGDATE=0";
        }
        if (i2 > 0) {
            str25 = str25 + " AND (CLL.LOGICALREF=" + i2 + " OR ITM.CLIENTCODE='' OR ITM.CLIENTCODE='*') ";
        }
        if (!TextUtils.isEmpty(str)) {
            str25 = str25 + " AND (ITM.TRADINGGRP LIKE '%" + str + "%' OR ITM.TRADINGGRP LIKE '') ";
        }
        String str26 = str25 + SqlLiteVariable._CLOSE_BRACKET;
        if (productListParameter.isAllStock() || i3 == -1) {
            String replace = getParamValue(ParameterTypes.ptGeneralSettings_Warehouse).replace(";", ",");
            str9 = replace.isEmpty() ? "" : " AND STC.WAREHOUSENR IN (" + replace + ") ";
        } else {
            str9 = " AND STC.WAREHOUSENR=" + i3 + " ";
        }
        String str27 = !productListParameter.isShowOnlyStock() ? "" : "HAVING IFNULL(SUM(STC.REALSTOCK), 0) >0 ";
        if (!TextUtils.isEmpty(str2)) {
            if (z && arrayList2 != null && !arrayList2.isEmpty()) {
                str10 = String.format("AND ( 1<>1 OR %s OR %s OR %s ) ", context.getString(R.string.qry_get_product_search_code, str2, str2.toLowerCase(), str2.toUpperCase()), context.getString(R.string.qry_get_product_search_barcode, str2), context.getString(R.string.qry_get_product_search_seri_barcode, StringUtils.getArrayToString(arrayList2, ",")));
            } else {
                if (productListParameter.getSearchType() == SearchType.CONTAINING_SEARCH_WORDS.getValue()) {
                    str10 = String.format("AND ( 1<>1 OR %s OR %s OR %s OR %s OR %s) ", productListParameter.getProductSearchOption().isDefinition() ? context.getString(R.string.qry_get_product_search_definition, str2.replace(" ", "%"), str2.toLowerCase().replace(" ", "%"), str2.toUpperCase().replace(" ", "%")) : "1<>1", (productListParameter.getProductSearchOption().isCode() || z) ? context.getString(R.string.net_qry_get_product_search_code, str2.replace(" ", "%"), str2.toLowerCase().replace(" ", "%"), str2.toUpperCase().replace(" ", "%")) : "1<>1", (productListParameter.getProductSearchOption().isBarcode() || z) ? context.getString(R.string.qry_get_product_search_containing_barcode, str2.replace(" ", "%")) : "1<>1", context.getString(R.string.net_qry_get_product_search_tag, str2), context.getString(R.string.qry_get_product_search_specode, str2));
                } else if (productListParameter.getSearchType() == SearchType.STARTING_WITH_SEARCH_WORD.getValue()) {
                    str10 = String.format("AND ( 1<>1 OR %s OR %s OR %s OR %s OR %s) ", productListParameter.getProductSearchOption().isDefinition() ? context.getString(R.string.qry_get_product_search_pre_definition, str2, str2.toLowerCase(), str2.toUpperCase()) : "1<>1", (productListParameter.getProductSearchOption().isCode() || z) ? context.getString(R.string.net_qry_get_product_search_code, str2, str2.toLowerCase(), str2.toUpperCase()) : "1<>1", (productListParameter.getProductSearchOption().isBarcode() || z) ? context.getString(R.string.qry_get_product_search_pre_barcode, str2) : "1<>1", context.getString(R.string.net_qry_get_product_search_tag, str2), context.getString(R.string.qry_get_product_search_specode, str2));
                } else {
                    str10 = String.format("AND ( 1<>1 OR %s OR %s OR %s OR %s OR %s) ", productListParameter.getProductSearchOption().isDefinition() ? context.getString(R.string.qry_get_product_search_definition, str2, str2.toLowerCase(), str2.toUpperCase()) : "1<>1", (productListParameter.getProductSearchOption().isCode() || z) ? context.getString(R.string.net_qry_get_product_search_code, str2, str2.toLowerCase(), str2.toUpperCase()) : "1<>1", (productListParameter.getProductSearchOption().isBarcode() || z) ? context.getString(R.string.qry_get_product_search_containing_barcode, str2) : "1<>1", context.getString(R.string.net_qry_get_product_search_tag, str2), context.getString(R.string.qry_get_product_search_specode, str2));
                }
            }
        } else if (arrayList == null || arrayList.isEmpty()) {
            str10 = "";
        } else if (arrayList2 != null && !arrayList2.isEmpty()) {
            str10 = String.format(" AND %s", context.getString(R.string.qry_get_product_search_seri_barcode, StringUtils.getArrayToString(arrayList2, ",")));
        } else {
            str10 = String.format(" AND ( %s OR %s )", context.getString(R.string.qry_get_product_search_only_barcode, StringUtils.getBarcodeText(arrayList)), context.getString(R.string.qry_get_product_search_only_itemcode, StringUtils.getBarcodeText(arrayList))).replace("ITEMCODE", "I.CODE");
        }
        if (!TextUtils.isEmpty(str5)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str10);
            sb.append(" AND IFNULL(I.STGRPCODE, '')='");
            String str28 = str5;
            if (str28.equals(context.getString(R.string.str_others))) {
                str28 = "";
            }
            sb.append(str28);
            sb.append("' ");
            str10 = sb.toString();
        }
        if (arrayList3 != null && !arrayList3.isEmpty()) {
            List<String> convertToStringArrayList = SqlHelper.convertToStringArrayList(arrayList3);
            str10 = str10 + " AND ( I.CODE IN " + StringUtils.formatList(convertToStringArrayList, true) + " OR UPPER(I.CODE) IN " + StringUtils.formatList(convertToStringArrayList, true) + " OR LOWER(I.CODE) IN " + StringUtils.formatList(convertToStringArrayList, true) + " )";
        }
        String[] strArr = {"I.STGRPCODE", "I.SPECODE", "I.SPECODE2", "I.SPECODE3", "I.SPECODE4", "I.SPECODE5"};
        if (productListParameter.getProductTreeList() != null && productListParameter.getProductTreeList().size() > 0) {
            for (int i7 = 0; i7 < productListParameter.getProductTreeList().size(); i7++) {
                str10 = str10 + SqlLiteVariable._AND + strArr[i7] + "='" + productListParameter.getProductTreeList().get(i7) + "' ";
            }
        }
        String str29 = productListParameter.getProductTreeGroupListType() == ProductTreeGroupListType.Others ? str10 + " AND TRIM(IFNULL(" + strArr[productListParameter.getProductTreeList().size()] + ",'')) = ''" : str10;
        if (i4 == 0) {
            str19 = " , 0 AS ISSLCTUNIT ";
            str14 = " , 0  AS DUNITREF ";
            str11 = "";
            str12 = str11;
            str13 = str12;
            str21 = str13;
        } else {
            List<T> tableWhere = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTableWhere(DefOrder.class, "LOGICALREF=?", new String[]{StringUtils.convertIntToString(i4)});
            str11 = " , W.LINENO_ ";
            str12 = " , LINENO_ ";
            str13 = " , 0 AS DUNITREF";
            str14 = " , DUNITREF";
            if (tableWhere != null && tableWhere.size() <= 0) {
                str19 = " , W.ISSLCTUNIT ";
                str21 = "";
            } else {
                String str30 = str27;
                DefOrder defOrder = (DefOrder) tableWhere.get(0);
                if (!TextUtils.isEmpty(defOrder.getInnerSql())) {
                    str15 = " , W.ISSLCTUNIT ";
                    str16 = " , W.LINENO_ ";
                    str17 = " , LINENO_ ";
                    str18 = " , 0 AS DUNITREF";
                    formatStringEnglish = ContextUtils.formatStringEnglish(context.getString(R.string.net_qry_def_order_inner_sql), Integer.valueOf(i4), getLogoSqlCreator().getTableName(Item.class), defOrder.getInnerSql(), Integer.valueOf(LineType.PRODUCT.value));
                } else {
                    str15 = " , W.ISSLCTUNIT ";
                    str16 = " , W.LINENO_ ";
                    str17 = " , LINENO_ ";
                    str18 = " , 0 AS DUNITREF";
                    formatStringEnglish = ContextUtils.formatStringEnglish(context.getString(R.string.net_qry_product_defOrder), Integer.valueOf(i4), Integer.valueOf(LineType.PRODUCT.value));
                }
                if (defOrder.getUseMinStock() == 1) {
                    str11 = str16;
                    str12 = str17;
                    str13 = str18;
                    str20 = " HAVING IFNULL(SUM(STC.REALSTOCK), 0) > " + defOrder.getMinStock();
                    str21 = "";
                    str19 = str15;
                } else {
                    str19 = str15;
                    str20 = str30;
                    str11 = str16;
                    str12 = str17;
                    str13 = str18;
                    str21 = "";
                }
                String string = context.getString(R.string.net_qry_get_product_list);
                Integer valueOf = Integer.valueOf(DateAndTimeUtils.getNetsisDateInt());
                if (TextUtils.isEmpty(StringUtils.getBarcodeText(arrayList))) {
                    str22 = string;
                    str23 = str7;
                    barcodeText = StringUtils.getBarcodeText(arrayList);
                } else {
                    str22 = string;
                    StringBuilder sb2 = new StringBuilder();
                    str23 = str7;
                    sb2.append("'");
                    sb2.append(str2);
                    sb2.append("'");
                    barcodeText = sb2.toString();
                }
                return ContextUtils.formatStringEnglish(str22, str6, " , ISSLCTUNIT ", str19, str26, valueOf, formatStringEnglish, str9, str29, str20, barcodeText, str3.replaceAll("TMP.ITEMCODE", "ITEMCODE"), str11, str12, str13, str14, str23).replaceAll("\\s*[\\r\\n]+\\s*", str21).trim();
            }
        }
        str20 = str27;
        formatStringEnglish = str21;
        String string2 = context.getString(R.string.net_qry_get_product_list);
        Integer valueOf2 = Integer.valueOf(DateAndTimeUtils.getNetsisDateInt());
        TextUtils.isEmpty(StringUtils.getBarcodeText(arrayList));
        return ContextUtils.formatStringEnglish(str22, str6, " , ISSLCTUNIT ", str19, str26, valueOf2, formatStringEnglish, str9, str29, str20, barcodeText, str3.replaceAll("TMP.ITEMCODE", "ITEMCODE"), str11, str12, str13, str14, str23).replaceAll("\\s*[\\r\\n]+\\s*", str21).trim();
    }
    public String getServiceListSql(Context context, ProductListParameter productListParameter, int i2, String str, int i3, ArrayList<BarcodeResult> arrayList, String str2, int i4, String str3, int i5, String str4) {
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String format;
        Object obj;
        String formatStringEnglish;
        String str10 = "";
        String str11 = "";
        String str12 = "";
        Object obj2 = null;
        Object obj3 = null;
        String str13 = "";
        String barcodeText = "";
        if (!productListParameter.isWhichMaterialDesc()) {
            str5 = "I.NAME";
        } else {
            str5 = "I.NAME2";
        }
        String str14 = str5;
        if (i2 <= 0) {
            str6 = "";
        } else {
            str6 = " LEFT JOIN CLCARD CLL ON CLL.CODE=ITM.CLIENTCODE ";
        }
        String str15 = str6 + "WHERE 1=1 AND ITM.PTYPE=4";
        if (i2 > 0) {
            str15 = str15 + " AND (CLL.LOGICALREF=" + i2 + " OR ITM.CLIENTCODE='' OR ITM.CLIENTCODE='*') ";
        }
        if (!TextUtils.isEmpty(str)) {
            str15 = str15 + " AND (ITM.TRADINGGRP LIKE '%" + str + "%' OR ITM.TRADINGGRP LIKE '') ";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str15);
        sb.append(" GROUP BY ITM.ITEMCODE ");
        if (TextUtils.isEmpty(str)) {
            str7 = "";
        } else {
            str7 = " , ITM.TRADINGGRP ";
        }
        sb.append(str7);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        sb3.append(" ORDER BY ITM.PRIORITY ");
        if (TextUtils.isEmpty(str)) {
            str8 = "";
        } else {
            str8 = ", ITM.TRADINGGRP ASC ";
        }
        sb3.append(str8);
        String sb4 = sb3.toString();
        productListParameter.isAllStock();
        if (!TextUtils.isEmpty(str2)) {
            if (productListParameter.getSearchType() == 0) {
                format = String.format("AND ( 1=1 AND %s OR %s OR %s OR %s ) ", productListParameter.getProductSearchOption().isDefinition() ? context.getString(R.string.qry_get_product_search_definition, str2, str2.toLowerCase(), str2.toUpperCase()) : "1=1", productListParameter.getProductSearchOption().isCode() ? context.getString(R.string.net_qry_get_product_search_code, str2, str2.toLowerCase(), str2.toUpperCase()) : "1=1", productListParameter.getProductSearchOption().isBarcode() ? context.getString(R.string.qry_get_product_search_barcode, str2) : "1=1", context.getString(R.string.net_qry_get_product_search_tag, str2));
            } else {
                format = String.format("AND ( 1=1 AND %s OR %s OR %s OR %s ) ", productListParameter.getProductSearchOption().isDefinition() ? context.getString(R.string.qry_get_product_search_pre_definition, str2, str2.toLowerCase(), str2.toUpperCase()) : "1=1", productListParameter.getProductSearchOption().isCode() ? context.getString(R.string.net_qry_get_product_search_pre_code, str2, str2.toLowerCase(), str2.toUpperCase()) : "1=1", productListParameter.getProductSearchOption().isBarcode() ? context.getString(R.string.qry_get_product_search_barcode, str2) : "1=1", context.getString(R.string.net_qry_get_product_search_tag, str2));
            }
        } else {
            if (arrayList == null || arrayList.size() <= 0) {
                str9 = "";
                if (i4 != 0) {
                    obj3 = " , 0 AS ISSLCTUNIT ";
                    str13 = "";
                    str10 = str13;
                    str11 = str10;
                    str12 = str11;
                    obj2 = " , 0  AS DUNITREF ";
                } else {
                    List<T> tableWhere = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTableWhere(DefOrder.class, "LOGICALREF=?", new String[]{StringUtils.convertIntToString(i4)});
                    if (tableWhere != null && tableWhere.size() <= 0) {
                        str10 = " , W.LINENO_ ";
                        str11 = " , LINENO_ ";
                        str12 = " , 0 AS DUNITREF";
                        obj2 = " , DUNITREF";
                        str13 = "";
                        obj3 = " , W.ISSLCTUNIT ";
                    } else {
                        DefOrder defOrder = (DefOrder) tableWhere.get(0);
                        if (!TextUtils.isEmpty(defOrder.getInnerSql())) {
                            obj = " , W.ISSLCTUNIT ";
                            formatStringEnglish = ContextUtils.formatStringEnglish(context.getString(R.string.net_qry_def_order_inner_sql), Integer.valueOf(i4), getLogoSqlCreator().getTableName(Service.class), defOrder.getInnerSql(), Integer.valueOf(LineType.SERVICE.value));
                        } else {
                            obj = " , W.ISSLCTUNIT ";
                            formatStringEnglish = ContextUtils.formatStringEnglish(context.getString(R.string.net_qry_product_defOrder), Integer.valueOf(i4), Integer.valueOf(LineType.SERVICE.value));
                        }
                        defOrder.getUseMinStock();
                        str10 = " , W.LINENO_ ";
                        str11 = " , LINENO_ ";
                        str12 = " , 0 AS DUNITREF";
                        obj2 = " , DUNITREF";
                        obj3 = obj;
                        str13 = formatStringEnglish;
                    }
                }
                String string = context.getString(R.string.net_qry_get_service_list);
                Integer valueOf = DateAndTimeUtils.getLogoDateInt();
                if (TextUtils.isEmpty(StringUtils.getBarcodeText(arrayList))) {
                    barcodeText = StringUtils.getBarcodeText(arrayList);
                } else {
                    barcodeText = "'" + str2 + "'";
                }
                return ContextUtils.formatStringEnglish(string, str14, " , ISSLCTUNIT ", obj3, sb4, valueOf, str13, str9, "", barcodeText, str3.replaceAll("TMP.ITEMCODE", "ITEMCODE"), str10, str11, str12, obj2).replaceAll("\\s*[\\r\\n]+\\s*", "").trim();
            }
            format = String.format(" AND %s", context.getString(R.string.qry_get_product_search_only_barcode, StringUtils.getBarcodeText(arrayList)));
        }
        str9 = format;
        String string2 = context.getString(R.string.net_qry_get_service_list);
        Integer valueOf2 = DateAndTimeUtils.getLogoDateInt();
        TextUtils.isEmpty(StringUtils.getBarcodeText(arrayList));
        return ContextUtils.formatStringEnglish(string2, str14, " , ISSLCTUNIT ", obj3, sb4, valueOf2, str13, str9, "", barcodeText, str3.replaceAll("TMP.ITEMCODE", "ITEMCODE"), str10, str11, str12, obj2).replaceAll("\\s*[\\r\\n]+\\s*", "").trim();
    }
    public String getProductSortListSql(Context context, ProductListParameter productListParameter, int i2) {
        String str = " ORDER BY TMP.ITEMCODE ";
        if (i2 > 0) {
            List<T> tableWhere = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTableWhere(DefOrder.class, "LOGICALREF=?", new String[]{StringUtils.convertIntToString(i2)});
            if (tableWhere != null && !tableWhere.isEmpty()) {
                DefOrder defOrder = (DefOrder) tableWhere.get(0);
                if (productListParameter.isSortChanged()) {
                    if (productListParameter.getSortType() != 0) {
                        str = " ORDER BY ITEMCODE ";
                    }
                    str = " ORDER BY DEFINITION ";
                } else if (defOrder.getStype() == 2) {
                    str = " ORDER BY LINENO_ ";
                }
            } else {
                str = "";
            }
        }
        return str + " COLLATE NOCASE ";
    }

    public String getProductDetailPriceSql(int i2) {
        return "SELECT * FROM ITEMPRICE WHERE ITEMCODE ='" + getItemCode(i2) + "' ORDER BY GRPCODE,VARIANTCODE";
    }

    public String getProductDetailStockSql(int i2, boolean z) {
        String str = z ? "SERVICEUNITS" : "ITEMUNITS";
        String replace = getParamValue(ParameterTypes.ptGeneralSettings_Warehouse).replace(";", ",");
        String itemCode = getItemCode(i2);
        String str2 = "";
        if (!replace.isEmpty()) {
            str2 = " AND W.NR IN (" + replace + ") ";
        }
        return "SELECT S.* ,W.* , IFNULL(IU.CODE,'') AS UNITCODE, VS.ONHAND VONHAND,VS.REALSTOCK VREALSTOCK, V.CODE VCODE,V.NAME VNAME FROM ITEMSTOCK AS S  INNER JOIN WAREHOUSE AS W ON S.WAREHOUSENR = W.NR  LEFT JOIN (SELECT * FROM " + str + " WHERE ITEMCODE = '" + itemCode + "' ORDER BY SALESPRIORITY, LINENR LIMIT 1) IU ON IU.ITEMCODE = S.ITEMCODE  LEFT JOIN VARIANTSTOCK VS ON VS.ITEMCODE = S.ITEMCODE AND VS.WAREHOUSENR = S.WAREHOUSENR   LEFT JOIN VARIANTS V ON V.CODE = VS.VARIANTCODE AND V.ITEMCODE = VS.ITEMCODE WHERE S.ITEMCODE ='" + itemCode + "'" + str2;
    }

    public String getProductDetailInfoSql(int i2, boolean z) {
        return "SELECT *, I.CODE AS ICODE, '' AS ODEMEPLAN FROM ITEMS AS I \n WHERE I.LOGICALREF ='" + i2 + "'";
    }
    public String getCustomerShipAddressSql(Context context, int i2, String str) {
        String str2 = "SELECT * FROM SHIPADDRESS WHERE COALESCE(TRIM(CLCODE), '')='" + getClCode(i2) + "'";
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
                    stringResource = ContextUtils.getStringResource(R.string.net_qry_get_service_units);
                } else {
                    stringResource = ContextUtils.getStringResource(R.string.net_qry_get_item_units);
                }
                cursor = readableDatabase.rawQuery(stringResource, new String[]{getItemCode(i2)});
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

    public void updateCustomerRiskValues(CustomerRisk customerRisk, int i2) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        readableDatabase.beginTransaction();
        try {
            try {
                readableDatabase.execSQL("UPDATE CLCARD SET SIPRISKDAVRAN = " + customerRisk.getSipRiskDavran() + ",SEVKRISKDAVRAN = " + customerRisk.getSevkRiskDavran() + ",YUKRISKDAVRAN = " + customerRisk.getYukRiskDavran() + ",IRSRISKDAVRAN = " + customerRisk.getIrsRiskDavran() + ",FATRISKDAVRAN = " + customerRisk.getFatRiskDavran() + " WHERE LOGICALREF=" + i2);
                readableDatabase.setTransactionSuccessful();
            } catch (SQLException e2) {
                Log.e(TAG, "cekSenetToplamlariGuncelle: ", e2);
            }
        } finally {
            readableDatabase.endTransaction();
        }
    }
    public String getSalesDetailProductPriceSql(Context context, boolean z, String str) {
        String string;
        if (z) {
            string = context.getString(R.string.table_item_units);
        } else {
            string = context.getString(R.string.table_service_units);
        }
        return context.getString(R.string.net_qry_get_price_local, string, Integer.valueOf(DateAndTimeUtils.getNetsisDateInt()), "");
    }
    public String getSalesDetailProductPriceSql(Context context, int i2, boolean z, String str, int i3) {
        String string;
        boolean equals = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getLogoParamValue("FIAT_LISTE").equals(ExifInterface.LONGITUDE_EAST);
        boolean equals2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getLogoParamValue("FIAT_SISTEMI").equals(ExifInterface.LONGITUDE_EAST);
        boolean z2 = false;
        boolean z3 = equals2 && equals;
        if (equals2 && !equals) {
            z2 = true;
        }
        String nowDate = DateAndTimeUtils.nowDate("yyyyMMdd");
        String str2 = "";
        if (z3) {
            str2 = " AND P.GRPCODE='" + (i2 > 0 ? getCustomerPriceGroup(i2) : "") + "' AND P.BEGDATE<=" + nowDate + " AND (P.ENDDATE=0 OR P.ENDDATE>=" + nowDate + ")";
        } else if (z2) {
            str2 = " AND ((P.GRPCODE='" + (i2 > 0 ? getCustomerPriceGroup(i2) : "") + "' AND P.BEGDATE<=" + nowDate + " AND (P.ENDDATE=0 OR P.ENDDATE>=" + nowDate + ")) OR P.BEGDATE=0)";
        } else if (!equals2) {
            str2 = " AND P.BEGDATE=0 ";
        }
        if (!TextUtils.isEmpty(str)) {
            str2 = str2 + ContextUtils.getStringResource(R.string.qry_where_pricelist_variant_condition, "P.", str);
        }
        if (z) {
            string = context.getString(R.string.table_item_units);
        } else {
            string = context.getString(R.string.table_service_units);
        }
        return context.getString(R.string.net_qry_get_price_local, string, Integer.valueOf(DateAndTimeUtils.getNetsisDateInt()), str2);
    }

    public Cursor getInvoiceDetailForPrint(int i2, boolean z, int i3) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT I.*,I.VATMATRAH ,V.CODE AS VCODE,V.NAME AS VNAME,IT.CODE AS PCODE,IT.NAME AS PNAME,U.CODE AS UCODE,  IT.GTIPCODE AS GTIP,P.CODE AS PAYCODE,P.DEFINITION_ AS PAYNAME, (SELECT BARCODE FROM UNITBARCODE WHERE ITEMCODE = I.ITEMCODE AND UNITCODE = I.UNITCODE LIMIT 1) AS Barkod  , IT.TRACKTYPE,IT.LOGICALREF AS ITEMREF,NULL AS LOTNAME,NULL AS CODE,NULL AS LOTAMOUNT,NULL AS EXPDATE , 0 AS LOT  FROM INVOICEDETAIL I  INNER JOIN ITEMS IT ON IT.CODE = I.ITEMCODE  LEFT JOIN ITEMUNITS U ON U.CODE = I.UNITCODE AND U.ITEMCODE = IT.CODE  LEFT JOIN VARIANTS V ON V.CODE = I.VARIANTCODE AND V.ITEMCODE = IT.CODE  LEFT JOIN PAYMENT P ON P.CODE = I.PAYMENTCODE  WHERE I.SALESFICHEID=");
        sb.append(i2);
        sb.append(" UNION ");
        sb.append("SELECT I.*,I.VATMATRAH ,V.CODE AS VCODE,V.NAME AS VNAME,IT.CODE AS PCODE,IT.NAME AS PNAME,U.CODE AS UCODE, '' AS GTIP,P.CODE AS PAYCODE,P.DEFINITION_ AS PAYNAME, (SELECT BARCODE FROM UNITBARCODE WHERE ITEMCODE = I.ITEMCODE AND UNITCODE = I.UNITCODE LIMIT 1) AS Barkod  , IT.TRACKTYPE,IT.LOGICALREF AS ITEMREF,NULL AS LOTNAME,NULL AS CODE,NULL AS LOTAMOUNT,NULL AS EXPDATE , 0 AS LOT  FROM INVOICEDETAIL I  INNER JOIN SERVICE IT ON IT.CODE = I.ITEMCODE  LEFT JOIN SERVICEUNITS U ON U.CODE = I.UNITCODE AND U.ITEMREF = IT.CODE  LEFT JOIN VARIANTS V ON V.CODE = I.VARIANTCODE AND V.ITEMCODE = IT.CODE  LEFT JOIN PAYMENT P ON P.CODE = I.PAYMENTCODE  WHERE I.SALESFICHEID=");
        sb.append(i2);
        if (z) {
            sb.append(" UNION ");
            sb.append(" SELECT I.*, I.VATMATRAH,NULL AS VCODE, NULL AS VNAME, NULL AS PCODE, NULL AS PNAME, NULL AS UCODE, NULL AS GTIP  , NULL AS PAYCODE, NULL AS PAYNAME,NULL AS Barkod,NULL AS TRACKTYPE,I.ITEMCODE AS ITEMREF,S.NAME AS LOTNAME,S.CODE,S.AMOUNT AS LOTAMOUNT,S.EXPDATE ,1 AS LOT  FROM SERILOT S  INNER JOIN INVOICE D ON D.LOGICALREF = I.SALESFICHEID  INNER JOIN INVOICEDETAIL I ON S.DETAILREF = I.LOGICALREF  WHERE S.AMOUNT >0 AND I.SALESFICHEID=");
            sb.append(i2);
            sb.append(" ORDER BY I.LINENR,TRACKTYPE DESC ");
        }
        return getReadableDatabase().rawQuery(sb.toString(), null);
    }

    public Cursor getInvoiceHedaerForPrint(int i2) {
        return getReadableDatabase().rawQuery(" SELECT I.*,R.CODE AS RCODE,R.DEFINITION_ AS RNAME,P.CODE AS PCODE,P.DEFINITION_ AS PNAME,F.CODE AS FCODE, F.NAME AS FNAME,  C.CODE AS CCODE,C.DEFINITION_ AS CNAME,C.SPECODE AS CSPECODE,C.CYPHCODE AS CCYPHCODE,C.ADDR1,C.ADDR2,  C.CITY,C.TOWN,C.TELNRS1,C.TELNRS2,C.FAXNR,C.TAXNR,C.TAXOFFICE,C.INCHARGE,C.DISCRATE,C.EMAILADDR, C.TRADINGGRP AS CTRADINGGRP,C.DEBIT AS DEBIT_FLOAT,C.CREDIT AS CREDIT_FLOAT,C.BAKIYE AS BAKIYE_FLOAT,R.ADDR1 AS RADDR1,R.ADDR2 AS RADDR2,R.CITY AS RCITY,R.DISTRICT AS RDISTRICT, F.ADDR1 AS FADDR1,F.ADDR2 AS FADDR2,F.CITY AS FCITY,F.DISTRICT AS FDISTRICT,C.DISTRICT AS CDISTRICT,C.TAXOFFCODE AS CTAXOFFCODE, F.LATITUTE AS FLATITUTE,F.LONGITUDE AS FLONGITUDE ,C.DEFINITION2 AS CNAME2 , C.TCKNO AS TC FROM INVOICE I  INNER JOIN CLCARD C ON C.CODE=I.CLCODE  LEFT JOIN CLCARD R ON R.CODE=I.SHIPACCCODE  LEFT JOIN SHIPADDRESS F ON F.CODE=I.SHIPADDRCODE  LEFT JOIN PAYMENT P ON P.CODE=I.PAYMENTCODE  WHERE I.LOGICALREF=" + i2, null);
    }
    public Cursor getOrderDetailForPrint(int i2) {
        return getReadableDatabase().rawQuery(" SELECT I.*,V.CODE AS VCODE,V.NAME AS VNAME,IT.CODE AS PCODE,IT.NAME AS PNAME,U.CODE AS UCODE, IT.GTIPCODE,P.CODE AS PAYCODE,P.DEFINITION_ AS PAYNAME, (SELECT BARCODE FROM UNITBARCODE WHERE ITEMCODE = I.ITEMCODE AND UNITCODE = I.UNITCODE LIMIT 1) AS Barkod  FROM ORDERDETAIL I  INNER JOIN ITEMS IT ON IT.CODE = I.ITEMCODE  LEFT JOIN ITEMUNITS U ON U.CODE = I.UNITCODE AND U.ITEMCODE = IT.CODE  LEFT JOIN VARIANTS V ON V.CODE = I.VARIANTCODE AND V.ITEMCODE = IT.CODE  LEFT JOIN PAYMENT P ON P.CODE = I.PAYMENTCODE  WHERE I.SALESFICHEID=" + i2 + " UNION  SELECT I.*,V.CODE AS VCODE,V.NAME AS VNAME,IT.CODE AS PCODE,IT.NAME AS PNAME,U.CODE AS UCODE, '' AS GTIPCODE,P.CODE AS PAYCODE,P.DEFINITION_ AS PAYNAME, (SELECT BARCODE FROM UNITBARCODE WHERE ITEMCODE = I.ITEMCODE AND UNITCODE = I.UNITCODE LIMIT 1) AS Barkod  FROM ORDERDETAIL I  INNER JOIN SERVICE IT ON IT.CODE = I.ITEMCODE  LEFT JOIN SERVICEUNITS U ON U.CODE = I.UNITCODE AND U.ITEMREF = IT.CODE  LEFT JOIN VARIANTS V ON V.CODE = I.VARIANTCODE AND V.ITEMCODE = IT.CODE  LEFT JOIN PAYMENT P ON P.CODE = I.PAYMENTCODE  WHERE I.SALESFICHEID=" + i2, null);
    }

    public Cursor getOrderHedaerForPrint(int i2) {
        return getReadableDatabase().rawQuery(" SELECT I.*,R.CODE AS RCODE,R.DEFINITION_ AS RNAME,P.CODE AS PCODE,P.DEFINITION_ AS PNAME,F.CODE AS FCODE, F.NAME AS FNAME,  C.CODE AS CCODE,C.DEFINITION_ AS CNAME,C.SPECODE AS CSPECODE,C.CYPHCODE AS CCYPHCODE,C.ADDR1,C.ADDR2,  C.CITY,C.TOWN,C.TELNRS1,C.TELNRS2,C.FAXNR,C.TAXNR,C.TAXOFFICE,C.INCHARGE,C.DISCRATE,C.EMAILADDR, C.TRADINGGRP AS CTRADINGGRP,C.DEBIT_FLOAT,C.CREDIT_FLOAT,C.BAKIYE_FLOAT,R.ADDR1 AS RADDR1,R.ADDR2 AS RADDR2,R.CITY AS RCITY,R.DISTRICT AS RDISTRICT, F.ADDR1 AS FADDR1,F.ADDR2 AS FADDR2,F.CITY AS FCITY,F.DISTRICT AS FDISTRICT,C.DISTRICT AS CDISTRICT,C.TAXOFFCODE AS CTAXOFFCODE, F.LATITUTE AS FLATITUTE,F.LONGITUDE AS FLONGITUDE  FROM ORDERS I  INNER JOIN CLCARD C ON C.CODE=I.CLCODE  LEFT JOIN CLCARD R ON R.CODE=I.SHIPACCCODE  LEFT JOIN SHIPADDRESS F ON F.CODE=I.SHIPADDRCODE  LEFT JOIN PAYMENT P ON P.CODE = I.PAYMENTCODE  WHERE I.LOGICALREF=" + i2, null);
    }

    public Cursor getCustomerUnsentAccountBalance(int i2) {
        String clCode = getClCode(i2);
        return getReadableDatabase().rawQuery("SELECT SUM(ALLDEBIT) ALLDEBIT, SUM(ALLCREDIT) ALLCREDIT, SUM(ALLDEBIT) - SUM(ALLCREDIT) AS ALLBALANCE FROM (     SELECT TOTALNET AS ALLDEBIT ,0 AS ALLCREDIT FROM INVOICE WHERE ISTRANSFER = 0 AND SALESTYPE = 2 AND CLCODE = '" + clCode + "'     UNION ALL     SELECT 0 AS ALLDEBIT ,TOTALNET AS ALLCREDIT FROM INVOICE WHERE ISTRANSFER = 0 AND SALESTYPE = 3 AND CLCODE = '" + clCode + "'     UNION ALL     SELECT TOTALNET AS ALLDEBIT ,0 AS ALLCREDIT FROM INVOICE WHERE ISTRANSFER = 0 AND SALESTYPE = 8 AND CLCODE = '" + clCode + "'     UNION ALL     SELECT 0 AS ALLDEBIT ,TOTALNET AS ALLCREDIT FROM INVOICE WHERE ISTRANSFER = 0 AND SALESTYPE = 9 AND CLCODE = '" + clCode + "'     UNION ALL     SELECT 0 AS ALLDEBIT ,TOTAL AS ALLCREDIT FROM CASECASH WHERE ISTRANSFER = 0 AND CLCODE = '" + clCode + "'     UNION ALL     SELECT 0 AS ALLDEBIT ,TOTAL AS ALLCREDIT FROM CASHCREDIT WHERE ISTRANSFER = 0 AND CLCODE = '" + clCode + "'     UNION ALL     SELECT 0 AS ALLDEBIT ,TOTAL AS ALLCREDIT FROM CHEQUEDEED WHERE ISTRANSFER = 0 AND CLCODE = '" + clCode + "' ) ", null);
    }

    public Cursor getCustomerBalanceInfoAfterDate(int i2, String str) {
        return getReadableDatabase().rawQuery("SELECT SUM(ALLDEBIT) ALLDEBIT, SUM(ALLCREDIT) ALLCREDIT, SUM(ALLDEBIT) - SUM(ALLCREDIT) AS ALLBALANCE FROM (     SELECT TOTALNET AS ALLDEBIT ,0 AS ALLCREDIT FROM INVOICE WHERE SALESTYPE = 2 AND CLREF = " + i2 + " GDATE >= '" + str + "'    UNION ALL     SELECT 0 AS ALLDEBIT ,TOTALNET AS ALLCREDIT FROM INVOICE WHERE SALESTYPE = 3 AND CLREF = " + i2 + " GDATE >= '" + str + "'    UNION ALL     SELECT TOTALNET AS ALLDEBIT ,0 AS ALLCREDIT FROM INVOICE WHERE SALESTYPE = 8 AND CLREF = " + i2 + " GDATE >= '" + str + "'    UNION ALL     SELECT 0 AS ALLDEBIT ,TOTALNET AS ALLCREDIT FROM INVOICE WHERE SALESTYPE = 9 AND CLREF = " + i2 + " GDATE >= '" + str + "'    UNION ALL     SELECT 0 AS ALLDEBIT ,TOTAL AS ALLCREDIT FROM CASECASH WHERE CLREF = " + i2 + " GDATE >= '" + str + "'    UNION ALL     SELECT 0 AS ALLDEBIT ,TOTAL AS ALLCREDIT FROM CASHCREDIT WHERE CLREF = " + i2 + " GDATE >= '" + str + "'    UNION ALL     SELECT 0 AS ALLDEBIT ,TOTAL AS ALLCREDIT FROM CHEQUEDEED WHERE CLREF = " + i2 + " GDATE >= '" + str + "') ", null);
    }

    public String getCustomerLastPaymentInfo(int i2) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String clCode = getClCode(i2);
        double d2 = 0.0d;
        Cursor cursor = null;
        try {
            try {
                cursor = readableDatabase.rawQuery("SELECT TOTAL FROM ( SELECT TOTAL,GDATE FROM CASECASH WHERE CLCODE = '" + clCode + "'  AND GDATE BETWEEN '" + DateAndTimeUtils.nowDate() + " 00:00:00' AND '" + DateAndTimeUtils.nowDate() + " 23:59:59'  UNION ALL  SELECT TOTAL,GDATE FROM CASHCREDIT WHERE CLCODE = '" + clCode + "'   AND GDATE BETWEEN '" + DateAndTimeUtils.nowDate() + " 00:00:00' AND '" + DateAndTimeUtils.nowDate() + " 23:59:59'  UNION ALL  SELECT TOTAL,GDATE FROM CHEQUEDEED WHERE CLCODE = '" + clCode + "'   AND GDATE BETWEEN '" + DateAndTimeUtils.nowDate() + " 00:00:00' AND '" + DateAndTimeUtils.nowDate() + " 23:59:59' ) ORDER BY GDATE DESC LIMIT 1", null);
                if (cursor.moveToFirst()) {
                    d2 = cursor.getDouble(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClCardCurrCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return clCode;
    }

    public Cursor isCustomerEnterPenetrationToday(int i2, int i3) {
        return getReadableDatabase().rawQuery(" SELECT ID, ISTRANSFER, IFNULL(PNT_GUID,'') AS GUID FROM USERMAINPENETRATION WHERE CLIENTCODE = '" + getClCode(i2) + "' AND  PENETRATION_ID = " + i3 + " AND DATE BETWEEN '" + DateAndTimeUtils.nowDate() + " 00:00:00' AND '" + DateAndTimeUtils.nowDate() + " 23:59:59' ", null);
    }

    public double getCurrRate(int i2) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        double d2 = 1.0d;
        Cursor cursor = null;
        try {
            try {
                cursor = readableDatabase.rawQuery(ContextUtils.getStringResource(R.string.qry_curr_rate), new String[]{StringUtils.convertIntToString(i2), StringUtils.convertIntToString(DateAndTimeUtils.getNetsisDateInt())});
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    d2 = cursor.getDouble(Integer.parseInt("RATE"));
                }
            } catch (Exception e2) {
                Log.e(TAG, "getCurrRates: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return d2;
    }

    public double getCurrRateWithoutDefaultValue(int i2) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        double d2 = 0.0d;
        Cursor cursor = null;
        try {
            try {
                cursor = readableDatabase.rawQuery(ContextUtils.getStringResource(R.string.qry_curr_rate), new String[]{StringUtils.convertIntToString(i2), StringUtils.convertIntToString(DateAndTimeUtils.getNetsisDateInt())});
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    d2 = cursor.getDouble(Integer.parseInt("RATE"));
                }
            } catch (Exception e2) {
                Log.e(TAG, "getCurrRates: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return d2;
    }
    public double getCurrRateWithDate(int i2, String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        double d2 = 1.0d;
        Cursor cursor = null;
        try {
            try {
                cursor = readableDatabase.rawQuery(ContextUtils.getStringResource(R.string.qry_curr_rate), new String[]{StringUtils.convertIntToString(i2), StringUtils.convertIntToString(DateAndTimeUtils.getNetsisDateInt(str))});
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    d2 = cursor.getDouble(Integer.parseInt("RATE"));
                }
            } catch (Exception e2) {
                Log.e(TAG, "getCurrRates: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return d2;
    }

    public String getAlternativeProductList(Context context, AlternativeProductListOptions alternativeProductListOptions) {
        return "";
    }

    public String getCustomerPriceGroup(int i2) {
        int mainClRef;
        SQLiteDatabase readableDatabase = getReadableDatabase();
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        if (baseErp.getUseMainCustomerPriceList() && (mainClRef = baseErp.getMainClRef(i2)) != 0) {
            i2 = mainClRef;
        }
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = readableDatabase.rawQuery("SELECT PRICEGRP FROM CLCARD WHERE LOGICALREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }

    public String getProductDetailUnitSql(int i2, boolean z) {
        return ContextUtils.getStringResource(R.string.net_qry_productDetail_itemUnits, getItemCode(i2));
    }
    public String getItemSizeSql(SalesType salesType) {
        return ContextUtils.getStringResource(R.string.net_get_item_sizes, getTableName(salesType).getDetailTable(), getTableName(salesType).getTable());
    }

    public List<ItemUnits> getItemUnits(int i2) {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ItemUnits.class, "ITEMCODE=?", new String[]{String.valueOf(getItemCode(i2))});
    }

    public double getLocalCreditTotal(int i2) {
        double d2 = 0.0d;
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery(ContextUtils.getStringResource(R.string.net_get_not_transferred_credit_total), new String[]{getClCode(i2)});
                if (cursor != null && cursor.moveToFirst()) {
                    d2 = cursor.getDouble( R.string.column_total);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return d2;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    public double getLocalDebitTotal(int i2) {
        double d2 = 0.0d;
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery(ContextUtils.getStringResource(R.string.net_get_not_transferred_debit_total), new String[]{getClCode(i2)});
                if (cursor != null && cursor.moveToFirst()) {
                    d2 = cursor.getDouble(R.string.column_total);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
            }
            return d2;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
