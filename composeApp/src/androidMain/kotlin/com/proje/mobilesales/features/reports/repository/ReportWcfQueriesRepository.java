package com.proje.mobilesales.features.reports.repository;

import android.text.TextUtils;
import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.enums.InvoiceType;
import com.proje.mobilesales.core.enums.OrderStatus;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.tigerwcf.LogoTigerTableName;
import com.proje.mobilesales.core.tigerwcf.SelectResult;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.dbmodel.User;
import com.proje.mobilesales.features.model.USER;
import com.proje.mobilesales.features.reports.model.ReportListParameter;
import com.proje.mobilesales.features.reports.model.enums.ReportCurrencyUnit;
import com.proje.mobilesales.features.reports.model.enums.ReportDebitFilterType;
import com.proje.mobilesales.features.reports.model.enums.ReportSortType;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import static com.proje.mobilesales.features.gpsinfo.repository.GpsInfoRepository.baseRepositorybaseErp2;

public final class ReportWcfQueriesRepository extends BaseRepository implements IReportWcfQueryRepository {
    private int clRef;
    private String createDateEnd;
    private String createDateStart;
    private final LogoTigerTableName logoTigerTableName;
    private SelectResult result;
    private int salesmanRef;
    private String sql;
    private String strSql;
    public ReportWcfQueriesRepository() {
        super(baseRepositorybaseErp2);
        final User user = this.getBaseErp().getUser();
        logoTigerTableName = new LogoTigerTableName(user.getDbName(), user.getFirmNr(), user.getPeridodNr());
        strSql = "";
    }
    public SelectResult getOrderInvoice(final int i2, final String createDateStart, final String createDateEnd, final int i3, final ProcessType type, final int i4) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        Intrinsics.checkNotNullParameter(type, "type");
        final ProcessType processType = ProcessType.FILLREPORTORDER;
        final String str = type == processType ? logoTigerTableName.ORFICHE : logoTigerTableName.INVOICE;
        if (null == this.result) {
            result = new SelectResult();
        }
        String sb = "SELECT TOP 50000 R.DATE_ , R.TOTALDISCOUNTS , R.GENEXP1 ,R.FICHENO,R.CODE, R.DEFINITION_,R.GROSSTOTAL, R.NETTOTAL, R.LOGICALREF " +
                (type == processType ? ", R.X " : " ") +
                "FROM (SELECT ROW_NUMBER() OVER (ORDER BY DATE_) AS RowNum, O.FICHENO, C.CODE, C.DEFINITION_,O.GROSSTOTAL, O.NETTOTAL , O.TOTALDISCOUNTS, O.GENEXP1 , O.DATE_ , O.LOGICALREF" +
                (type == processType ? ", F.NAME [X] " : " ") +
                "  FROM " +
                str +
                "  O WITH(NOLOCK) LEFT JOIN " +
                logoTigerTableName.CLCARD +
                "  C WITH(NOLOCK) ON C.LOGICALREF = O.CLIENTREF ";
        sql = sb;
        if (type == processType) {
            sql += " LEFT JOIN " + logoTigerTableName.FICHESTATUS + " F WITH(NOLOCK) ON F.LOGICALREF=O.FCSTATUSREF ";
        }
        sql += " LEFT JOIN " + logoTigerTableName.SLSMAN + " SL ON SL.LOGICALREF = O.SALESMANREF";
        sql += " WHERE DATE_ BETWEEN " + createDateStart + SqlLiteVariable._AND + createDateEnd;
        sql += " AND O.SALESMANREF IN (" + this.getSalesmansInSql(i3) + ')';
        if (0 < i4) {
            sql += " AND O.CLIENTREF=" + i4;
        }
        if (type == ProcessType.FILLREPORTINVOICE) {
            sql += " AND O.TRCODE=" + InvoiceType.NORMAL.getValue() + " AND O.CANCELLED=0";
        } else if (type == ProcessType.FILLREPORTRETURNINVOICE) {
            sql += " AND O.TRCODE=" + InvoiceType.RETURN.getValue() + " AND O.CANCELLED=0";
        } else if (type == ProcessType.FILLREPORTINVOICEAVGTIME) {
            sql += " AND O.CANCELLED=0";
        }
        sql += " ) R WHERE R.RowNum > " + i2;
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = type;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getInvoiceAvgLinePayPlan(final ArrayList<Integer> ficheRefs) {
        Intrinsics.checkNotNullParameter(ficheRefs, "ficheRefs");
        final SelectResult selectResult = new SelectResult();
        selectResult.sql = "SELECT PTRNS.PROCDATE AS X,       PTRNS.LOGICALREF,       PTRNS.TOTAL,       PTRNS.SIGN,       PTRNS.DATE_ AS Y FROM " + logoTigerTableName.PAYTRANS + " PTRNS WITH (NOLOCK)     LEFT OUTER JOIN " + logoTigerTableName.CLCARD + " CLNTC WITH (NOLOCK) ON(PTRNS.CARDREF = CLNTC.LOGICALREF)WHERE       (PTRNS.CANCELLED <> 1)     AND (PTRNS.PAIDINCASH = 0)     AND (((PTRNS.MODULENR = 4)    AND (PTRNS.TRCODE IN(1, 2, 3, 4, 6, 7, 8, 9, 13, 14, 26, 41, 42))))AND (CLNTC.CARDTYPE <> 22) AND PTRNS.FICHEREF IN(" + StringUtils.getArrayToString(ficheRefs, ",") + SqlLiteVariable._CLOSE_BRACKET;
        selectResult.type = ProcessType.FILLREPORTAVGCALC;
        return selectResult;
    }
    public SelectResult getSalesmans(final String salesmanCode, final int i2, final String[] filter) {
        List emptyList;
        Intrinsics.checkNotNullParameter(salesmanCode, "salesmanCode");
        Intrinsics.checkNotNullParameter(filter, "filter");
        if (null == this.result) {
            result = new SelectResult();
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT SL.LOGICALREF [X], (SL.CODE + ', '+ SL.DEFINITION_) [Y] FROM " + logoTigerTableName.SLSMAN + "  SL WITH(NOLOCK) LEFT JOIN WOR_USERS U WITH(NOLOCK) ON SL.CODE=U.CODE WHERE (SL.ACTIVE=0 AND SL.FIRMNR=" + USER.firmano);
        if (!Intrinsics.areEqual(salesmanCode, "")) {
            sb.append(" AND SL.CODE = '" + salesmanCode + '\'');
        }
        if (2 == i2 && TextUtils.isEmpty(salesmanCode)) {
            if (!TextUtils.isEmpty(filter[0]) && !TextUtils.isEmpty(filter[1])) {
                sb.append(" AND SL.CODE BETWEEN '" + filter[0] + "' AND '" + filter[1] + "' ");
            }
            if (!TextUtils.isEmpty(filter[2])) {
                sb.append(" AND SL.SPECODE='" + filter[2] + "' ");
            }
            final String paramValue = this.getLogoSqlHelper().getParamValue(ParameterTypes.ptSalesManagerSalespersonUserSpeCode);
            if (null != paramValue && 0 != paramValue.length()) {
                Intrinsics.checkNotNull(paramValue);
                final List<String> split = new Regex("\\;").split(paramValue, 0);
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
                if (0 != strArr.length) {
                    sb.append(" AND U.SPECODE IN (");
                    for (final String str : strArr) {
                        if (0 != str.length()) {
                            sb.append("'");
                            sb.append(str);
                            sb.append("',");
                        }
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(")");
                }
            }
            sb.append(") OR SL.LOGICALREF = ");
            sb.append(this.getBaseErp().getUser().getSalesPersonRef());
        } else {
            sb.append(")");
        }
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sb.toString();
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = ProcessType.FILLSPIN;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public String getSalesmansInSql(final int i2) {
        final String str;
        List emptyList;
        final String type = this.getBaseErp().getUser().getType();
        Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
        final int parseInt = Integer.parseInt(type);
        if (2 != parseInt) {
            str = this.getBaseErp().getUser().getCode();
            Intrinsics.checkNotNullExpressionValue(str, "getCode(...)");
        } else {
            str = "";
        }
        final String[] salesManagerSalesmanFilter = this.getBaseErp().getSalesManagerSalesmanFilter();
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT SLM.LOGICALREF FROM " + logoTigerTableName.SLSMAN + "  SLM WITH(NOLOCK) LEFT JOIN WOR_USERS US WITH(NOLOCK) ON SLM.CODE=US.CODE WHERE (SLM.ACTIVE=0 AND SLM.FIRMNR=" + USER.firmano);
        if (!Intrinsics.areEqual(str, "")) {
            sb.append(" AND SLM.CODE = '" + str + '\'');
        }
        if (0 < i2) {
            sb.append(" AND SLM.LOGICALREF = ");
            sb.append(i2);
            sb.append(")");
        } else if (2 == parseInt && TextUtils.isEmpty(str) && 0 == i2) {
            if (!TextUtils.isEmpty(salesManagerSalesmanFilter[0]) && !TextUtils.isEmpty(salesManagerSalesmanFilter[1])) {
                sb.append(" AND SLM.CODE BETWEEN '" + salesManagerSalesmanFilter[0] + "' AND '" + salesManagerSalesmanFilter[1] + "' ");
            }
            if (!TextUtils.isEmpty(salesManagerSalesmanFilter[2])) {
                sb.append(" AND SLM.SPECODE='" + salesManagerSalesmanFilter[2] + "' ");
            }
            final String paramValue = this.getLogoSqlHelper().getParamValue(ParameterTypes.ptSalesManagerSalespersonUserSpeCode);
            if (null != paramValue && 0 != paramValue.length()) {
                Intrinsics.checkNotNull(paramValue);
                final List<String> split = new Regex("\\;").split(paramValue, 0);
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
                if (0 != strArr.length) {
                    sb.append(" AND US.SPECODE IN (");
                    for (final String str2 : strArr) {
                        if (0 != str2.length()) {
                            sb.append("'");
                            sb.append(str2);
                            sb.append("',");
                        }
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(")");
                }
            }
            sb.append(") OR SLM.LOGICALREF = ");
            sb.append(this.getBaseErp().getUser().getSalesPersonRef());
        }
        final String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }
    public SelectResult getCashCredit(final int i2, final String createDateStart, final String createDateEnd, final int i3, final ProcessType type, final int i4) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        Intrinsics.checkNotNullParameter(type, "type");
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = "SELECT TOP 50000 R.FICHENO, R.CODE, R.DEFINITION_, SUM(R.AMOUNT) AS GROSSTOTAL, SUM(R.AMOUNT) AS NETTOTAL FROM (SELECT  ROW_NUMBER() OVER (ORDER BY O.DATE_) AS RowNum, O.FICHENO, CL.CODE, CL.DEFINITION_, C.AMOUNT FROM " + logoTigerTableName.CLFICHE + " AS O LEFT JOIN " + logoTigerTableName.CLFLINE + " AS C ON C.SOURCEFREF = O.LOGICALREF LEFT JOIN  " + logoTigerTableName.CLCARD + " AS CL ON C.CLIENTREF = CL.LOGICALREF";
        sql += " LEFT JOIN " + logoTigerTableName.SLSMAN + " SL ON SL.LOGICALREF = O.SALESMANREF";
        sql += " WHERE C.MODULENR = 5 AND O.DATE_ BETWEEN " + createDateStart + SqlLiteVariable._AND + createDateEnd;
        sql += " AND O.SALESMANREF IN (" + this.getSalesmansInSql(i3) + ')';
        if (0 < i4) {
            sql += " AND C.CLIENTREF=" + i4;
        }
        String sb = sql +
                (type == ProcessType.FILLREPORTCASH ? " AND O.TRCODE=1" : " AND O.TRCODE=70");
        sql = sb;
        sql += " ) R WHERE R.RowNum > " + i2;
        sql += " GROUP BY R.FICHENO, R.CODE, R.DEFINITION_";
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = type;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getCashCase(final int i2, final String createDateStart, final String createDateEnd, final int i3, final ProcessType processType, final int i4) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = "SELECT TOP 50000 R.FICHENO, R.CODE, R.DEFINITION_, R.GROSSTOTAL, R.NETTOTAL FROM (SELECT  ROW_NUMBER() OVER (ORDER BY O.DATE_) AS RowNum, O.FICHENO, CL.CODE, CL.DEFINITION_, O.AMOUNT [GROSSTOTAL], O.AMOUNT [NETTOTAL] FROM " + logoTigerTableName.KSLINES + " AS O LEFT JOIN " + logoTigerTableName.CLFLINE + " AS C ON C.SOURCEFREF = O.LOGICALREF LEFT JOIN  " + logoTigerTableName.CLCARD + " AS CL ON C.CLIENTREF = CL.LOGICALREF";
        sql += " LEFT JOIN " + logoTigerTableName.SLSMAN + " SL ON SL.LOGICALREF = O.SALESMANREF";
        sql += " WHERE C.MODULENR = 10 AND C.TRCODE = 1 AND O.DATE_ BETWEEN " + createDateStart + SqlLiteVariable._AND + createDateEnd;
        sql += " AND O.SALESMANREF IN (" + this.getSalesmansInSql(i3) + ')';
        if (0 < i4) {
            sql += " AND C.CLIENTREF=" + i4;
        }
        sql += " ) R WHERE R.RowNum > " + i2;
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = processType;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getChequeDeed(final int i2, final String createDateStart, final String createDateEnd, final int i3, final ProcessType type, final int i4) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        Intrinsics.checkNotNullParameter(type, "type");
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = "SELECT TOP 50000 R.FICHENO, R.CODE, R.DEFINITION_, R.GROSSTOTAL, R.NETTOTAL FROM (SELECT ROW_NUMBER() OVER (ORDER BY O.DATE_) AS RowNum, O.ROLLNO [FICHENO], C.CODE, C.DEFINITION_, O.TOTAL [GROSSTOTAL], O.TOTAL [NETTOTAL] FROM " + logoTigerTableName.CSROLL + " AS O LEFT JOIN " + logoTigerTableName.CLCARD + " AS C ON C.LOGICALREF = O.CARDREF";
        sql += " LEFT JOIN " + logoTigerTableName.SLSMAN + " SL ON SL.LOGICALREF = O.SALESMANREF";
        sql += " WHERE O.DATE_ BETWEEN " + createDateStart + SqlLiteVariable._AND + createDateEnd;
        sql += " AND O.SALESMANREF IN (" + this.getSalesmansInSql(i3) + ')';
        if (0 < i4) {
            sql += " AND O.CARDREF=" + i4;
        }
        String sb = sql +
                (type == ProcessType.FILLREPORTCHEQUE ? " AND O.TRCODE=1" : " AND O.TRCODE=2");
        sql = sb;
        sql += " ) R WHERE R.RowNum > " + i2;
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = type;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getVehicleStatus(final int i2, final ProcessType processType, final int i3, final ReportSortType reportSortType) {
        Intrinsics.checkNotNullParameter(reportSortType, "reportSortType");
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = "SELECT TOP 50000 X, Y, Z FROM (SELECT ROW_NUMBER() OVER (ORDER BY " + reportSortType.getType() + ") AS RowNum, I.CODE [X], I.NAME [Y], ROUND(SUM(ISNULL(IV.ONHAND, 0)), 7) [Z] FROM " + logoTigerTableName.STINVTOT + " AS IV, " + logoTigerTableName.ITEMS + " AS I WHERE I.LOGICALREF = IV.STOCKREF AND (IV.DATE_ > CONVERT(dateTime, '5-19-1919', 101)) AND (IV.DATE_ <= " + this.getBaseErp().getStockTotalsWhereDateExpression() + ')';
        if (-1 < i3) {
            sql += " AND IV.INVENNO = " + i3;
        }
        sql += " AND I.ACTIVE = 0 ";
        sql += " GROUP BY IV.INVENNO, I.CODE, I.NAME, IV.STOCKREF, I.CARDTYPE";
        sql += " HAVING ROUND(SUM(ISNULL(IV.ONHAND, 0)), 7) > 0 ";
        sql += " ) R WHERE R.RowNum > " + i2;
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = processType;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getSalesSummary(final String str, final String str2, final int i2, final ProcessType processType, final int i3) {
        if (null == this.result) {
            result = new SelectResult();
        }
        createDateStart = str;
        createDateEnd = str2;
        salesmanRef = i2;
        clRef = i3;
        sql = this.getSalesSummaryOrder();
        sql += " UNION ALL ";
        sql += this.getSalesSummaryInvoice(8);
        sql += " UNION ALL ";
        sql += this.getSalesSummaryInvoice(3);
        sql += " UNION ALL ";
        sql += this.getSalesSummaryCashCredit(1);
        sql += " UNION ALL ";
        sql += this.getSalesSummaryCashCredit(70);
        sql += " UNION ALL ";
        sql += this.getSalesSummaryCashCase();
        sql += " UNION ALL ";
        sql += this.getSalesSummaryChequeDeed(1);
        sql += " UNION ALL ";
        sql += this.getSalesSummaryChequeDeed(2);
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = processType;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public String getSalesSummaryOrder() {
        strSql = "SELECT count(*) [X], ISNULL(CONVERT(VARCHAR, CAST(SUM(GROSSTOTAL-TOTALDISCOUNTS) AS MONEY), 1), '') [Y], ISNULL(CONVERT(VARCHAR, CAST(SUM(TOTALVAT) AS MONEY), 1), '') [Z], ISNULL(CONVERT(VARCHAR, CAST(SUM(TOTALDISCOUNTS) AS MONEY), 1), '') [W], ISNULL(CONVERT(VARCHAR, CAST(SUM(NETTOTAL) AS MONEY), 1), '') [T] FROM " + logoTigerTableName.ORFICHE + " AS O INNER JOIN " + logoTigerTableName.CLCARD + " AS C ON C.LOGICALREF = O.CLIENTREF LEFT JOIN " + logoTigerTableName.CLRNUMS + " CL ON CL.CLCARDREF = O.CLIENTREF LEFT JOIN " + logoTigerTableName.SLSMAN + " SL ON SL.LOGICALREF = O.SALESMANREF\tWHERE  DATE_ BETWEEN " + createDateStart + SqlLiteVariable._AND + createDateEnd;
        String sb = strSql +
                " AND O.SALESMANREF IN (" +
                this.getSalesmansInSql(salesmanRef) +
                ')';
        strSql = sb;
        if (0 < this.clRef) {
            strSql += " AND O.CLIENTREF=" + clRef;
        }
        return strSql;
    }
    public String getSalesSummaryInvoice(final int i2) {
        strSql = "SELECT COUNT(*) [X], ISNULL(CONVERT(VARCHAR, CAST(SUM(GROSSTOTAL-TOTALDISCOUNTS) AS MONEY), 1), '') [Y], ISNULL(CONVERT(VARCHAR, CAST(SUM(TOTALVAT) AS MONEY), 1), '') [Z], ISNULL(CONVERT(VARCHAR, CAST(SUM(TOTALDISCOUNTS) AS MONEY), 1), '') [W], ISNULL(CONVERT(VARCHAR, CAST(SUM(NETTOTAL) AS MONEY), 1), '') [T]\tFROM " + logoTigerTableName.INVOICE + " AS O LEFT JOIN " + logoTigerTableName.CLCARD + " AS C ON C.LOGICALREF = O.CLIENTREF\tWHERE O.CANCELLED = 0 AND O.GRPCODE = 2 AND DATE_ BETWEEN " + createDateStart + SqlLiteVariable._AND + createDateEnd + " AND TRCODE = " + i2;
        String sb = strSql +
                " AND O.SALESMANREF IN (" +
                this.getSalesmansInSql(salesmanRef) +
                ')';
        strSql = sb;
        if (0 < this.clRef) {
            strSql += " AND O.CLIENTREF=" + clRef;
        }
        return strSql;
    }
    public String getSalesSummaryCashCredit(final int i2) {
        strSql = "SELECT COUNT(*) [X], ISNULL(CONVERT(VARCHAR, CAST(SUM(C.AMOUNT) AS MONEY), 1), '') [Y], '' [Z], '' [W], '' [T] FROM " + logoTigerTableName.CLFICHE + " AS O, " + logoTigerTableName.CLFLINE + " AS C, " + logoTigerTableName.CLCARD + " AS CL WHERE C.CLIENTREF = CL.LOGICALREF AND C.SOURCEFREF = O.LOGICALREF AND C.MODULENR = 5 AND O.CANCELLED = 0 AND O.DATE_ BETWEEN " + createDateStart + SqlLiteVariable._AND + createDateEnd + " AND O.TRCODE = " + i2;
        String sb = strSql +
                " AND C.SALESMANREF IN (" +
                this.getSalesmansInSql(salesmanRef) +
                ')';
        strSql = sb;
        if (0 < this.clRef) {
            strSql += " AND CL.CLIENTREF=" + clRef;
        }
        return strSql;
    }
    public String getSalesSummaryCashCase() {
        strSql = "SELECT COUNT(*) [X], ISNULL(CONVERT(VARCHAR, CAST(SUM(O.AMOUNT) AS MONEY), 1), '') [Y], '' [Z], '' [W], '' [T] FROM " + logoTigerTableName.KSLINES + " AS O, " + logoTigerTableName.CLFLINE + " AS C, " + logoTigerTableName.CLCARD + " AS CL WHERE C.CLIENTREF = CL.LOGICALREF AND C.SOURCEFREF = O.LOGICALREF AND C.MODULENR = 10 AND C.TRCODE = 1 AND O.DATE_ BETWEEN " + createDateStart + SqlLiteVariable._AND + createDateEnd;
        String sb = strSql +
                " AND C.SALESMANREF IN (" +
                this.getSalesmansInSql(salesmanRef) +
                ')';
        strSql = sb;
        if (0 < this.clRef) {
            strSql += " AND CL.CLIENTREF=" + clRef;
        }
        return strSql;
    }
    public String getSalesSummaryChequeDeed(final int i2) {
        strSql = "SELECT COUNT(*) [X], ISNULL(CONVERT(VARCHAR, CAST(SUM(O.TOTAL) AS MONEY), 1), '') [Y], '' [Z], '' [W], '' [T] FROM " + logoTigerTableName.CSROLL + " AS O, " + logoTigerTableName.CLCARD + " AS C WHERE  C.LOGICALREF = O.CARDREF AND O.DATE_ BETWEEN " + createDateStart + SqlLiteVariable._AND + createDateEnd + " AND O.TRCODE = " + i2;
        String sb = strSql +
                " AND C.SALESMANREF IN (" +
                this.getSalesmansInSql(salesmanRef) +
                ')';
        strSql = sb;
        if (0 < this.clRef) {
            strSql += " AND C.CARDREF=" + clRef;
        }
        return strSql;
    }
    public SelectResult getSalesOrd(final ReportListParameter params, final int i2, final ProcessType processType, final OrderStatus orderType, final boolean z, final int i3) {
        final String str;
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(orderType, "orderType");
        if (null == this.result) {
            result = new SelectResult();
        }
        if (z) {
            str = " CONVERT(VARCHAR, CAST(SUM(OL.AMOUNT * OL.UINFO2) AS MONEY), 1) [Z],CONVERT(VARCHAR, CAST(SUM(OL.TOTAL) AS MONEY), 1) [W],CONVERT(VARCHAR, CAST(SUM(OL.LINENET) AS MONEY), 1) [T], OL.LINETYPE [A] ";
        } else {
            str = " CONVERT(VARCHAR, CAST( OL.AMOUNT  AS MONEY),1) [Z], CONVERT(VARCHAR, CAST(TOTAL AS MONEY),1) [W], CONVERT(VARCHAR, CAST(LINENET AS MONEY),1) [T], OL.LINETYPE [A] ";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT TOP 50000 X,Y,Z,W,T,A FROM(SELECT ROW_NUMBER() OVER (ORDER BY ");
        final ReportSortType reportSortType = params.getReportSortType();
        Intrinsics.checkNotNull(reportSortType);
        sb.append(reportSortType.getValue());
        sb.append(") AS RowNum, X, Y, Z, W, T,A  FROM (");
        sql = sb.toString();
        sql += "SELECT  I.CODE [X], I.NAME [Y], " + str;
        sql += SqlLiteVariable._FROM + logoTigerTableName.ORFLINE + " AS OL";
        final ProcessType processType2 = ProcessType.FILLREPORTSALESORD;
        if (processType == processType2) {
            sql += " INNER JOIN " + logoTigerTableName.ORFICHE + " AS O ON O.LOGICALREF = OL.ORDFICHEREF";
        }
        sql += " LEFT JOIN " + logoTigerTableName.ITEMS + " AS I ON OL.STOCKREF = I.LOGICALREF";
        sql += " LEFT JOIN " + logoTigerTableName.UNITSETL + " AS U ON U.LOGICALREF = OL.UOMREF";
        sql += " LEFT JOIN " + logoTigerTableName.CURRENCYLIST + " AS CR ON CR.CURTYPE = OL.PRCURR AND CR.FIRMNR = " + USER.firmano;
        sql += " LEFT JOIN " + logoTigerTableName.DECARDS + " AS D ON D.LOGICALREF = OL.STOCKREF AND D.CARDTYPE = 2";
        sql += " WHERE OL.LINETYPE NOT IN (2, 4) AND OL.DATE_ BETWEEN " + params.getCreateDateStart() + SqlLiteVariable._AND + params.getCreateDateEnd();
        if (processType == processType2) {
            sql += " AND OL.SOURCEINDEX = " + params.getWarehouseNo();
        }
        sql += " AND OL.SALESMANREF IN (" + this.getSalesmansInSql(params.getSalesmanRef()) + ')';
        if (processType == processType2 && 0 < i3) {
            sql += " AND O.CLIENTREF=" + i3;
        }
        final OrderStatus orderStatus = OrderStatus.ALL;
        if (orderType != orderStatus) {
            sql += " AND OL.STATUS=" + orderType.getmStatus();
        }
        if (z) {
            sql += " GROUP BY I.CODE,I.NAME";
        }
        sql += " UNION ALL ";
        sql += " SELECT I.CODE [X], I.DEFINITION_ [Y], " + str;
        sql += SqlLiteVariable._FROM + logoTigerTableName.ORFLINE + " AS OL ";
        if (processType == processType2) {
            sql += " INNER JOIN " + logoTigerTableName.ORFICHE + " AS O ON O.LOGICALREF = OL.ORDFICHEREF ";
        }
        sql += " LEFT JOIN " + logoTigerTableName.SRVCARD + " AS I ON OL.STOCKREF = I.LOGICALREF ";
        sql += " LEFT JOIN " + logoTigerTableName.UNITSETL + " AS U ON U.LOGICALREF = OL.UOMREF AND U.LOGICALREF = OL.UOMREF ";
        sql += " LEFT JOIN " + logoTigerTableName.CURRENCYLIST + " AS CR ON CR.CURTYPE = OL.PRCURR AND CR.FIRMNR =" + USER.firmano;
        sql += " LEFT JOIN " + logoTigerTableName.DECARDS + " AS D ON D.LOGICALREF = OL.STOCKREF AND D.CARDTYPE = 2 ";
        sql += " WHERE OL.LINETYPE = 4 AND OL.DATE_ BETWEEN " + params.getCreateDateStart() + SqlLiteVariable._AND + params.getCreateDateEnd();
        if (processType == processType2) {
            sql += " AND OL.SOURCEINDEX = " + params.getWarehouseNo();
        }
        sql += " AND OL.SALESMANREF IN (" + this.getSalesmansInSql(params.getSalesmanRef()) + ')';
        if (processType == processType2 && 0 < i3) {
            sql += " AND O.CLIENTREF=" + i3;
        }
        if (orderType != orderStatus) {
            sql += " AND OL.STATUS=" + orderType.getmStatus();
        }
        if (z) {
            sql += " GROUP BY I.CODE, I.DEFINITION_";
        }
        sql += ") R ) R WHERE R.RowNum > " + i2;
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = processType;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getSalesInv(final ReportListParameter params, final int i2, final ProcessType processType, final InvoiceType invType, final int i3) {
        final String str;
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(invType, "invType");
        if (null == this.result) {
            result = new SelectResult();
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT TOP 50000 X,Y,Z,W,T,A FROM(SELECT ROW_NUMBER() OVER (ORDER BY ");
        final ReportSortType reportSortType = params.getReportSortType();
        Intrinsics.checkNotNull(reportSortType);
        sb.append(reportSortType.getValue());
        sb.append(") AS RowNum, X, Y, Z, W, T,A  FROM (");
        sql = sb.toString();
        sql += "SELECT I.CODE [X], I.NAME [Y],  CONVERT(VARCHAR, CAST( OL.AMOUNT  AS MONEY),1) [Z], CONVERT(VARCHAR, CAST(TOTAL AS MONEY),1) [W], CONVERT(VARCHAR, CAST(LINENET AS MONEY),1) [T], OL.LINETYPE [A] ";
        sql += SqlLiteVariable._FROM + logoTigerTableName.STLINE + " AS OL";
        sql += " INNER JOIN " + logoTigerTableName.INVOICE + " AS INV ON OL.INVOICEREF = INV.LOGICALREF";
        sql += " LEFT JOIN " + logoTigerTableName.ITEMS + " AS I ON OL.STOCKREF = I.LOGICALREF";
        sql += " LEFT JOIN " + logoTigerTableName.UNITSETL + " AS U ON U.LOGICALREF = OL.UOMREF AND U.LOGICALREF = OL.UOMREF";
        sql += " LEFT JOIN " + logoTigerTableName.CURRENCYLIST + " AS CR ON CR.CURTYPE = OL.PRCURR AND CR.FIRMNR = " + USER.firmano;
        sql += " LEFT JOIN " + logoTigerTableName.DECARDS + " AS D ON D.LOGICALREF = OL.STOCKREF AND D.CARDTYPE = 2";
        sql += " WHERE INV.TRCODE = " + invType.getValue() + " AND OL.LINETYPE NOT IN (2, 4) AND OL.DATE_ BETWEEN " + params.getCreateDateStart() + SqlLiteVariable._AND + params.getCreateDateEnd() + " AND INV.CANCELLED = 0 AND OL.SOURCEINDEX = " + params.getWarehouseNo();
        sql += " AND OL.SALESMANREF IN (" + this.getSalesmansInSql(params.getSalesmanRef()) + ')';
        if (0 < i3) {
            final StringBuilder sb2 = new StringBuilder();
            str = SqlLiteVariable._AND;
            sb2.append(sql);
            sb2.append(" AND INV.CLIENTREF=");
            sb2.append(i3);
            sql = sb2.toString();
        } else {
            str = SqlLiteVariable._AND;
        }
        sql += " UNION ";
        sql += " SELECT I.CODE [X], I.DEFINITION_ [Y],  CONVERT(VARCHAR, CAST( OL.AMOUNT  AS MONEY),1) [Z], CONVERT(VARCHAR, CAST(TOTAL AS MONEY),1) [W], CONVERT(VARCHAR, CAST(LINENET AS MONEY),1) [T], OL.LINETYPE [A] ";
        sql += SqlLiteVariable._FROM + logoTigerTableName.STLINE + " AS OL";
        sql += " INNER JOIN " + logoTigerTableName.INVOICE + " AS INV ON OL.INVOICEREF = INV.LOGICALREF";
        sql += " LEFT JOIN " + logoTigerTableName.SRVCARD + " AS I ON OL.STOCKREF = I.LOGICALREF";
        sql += " LEFT JOIN " + logoTigerTableName.UNITSETL + " AS U ON U.LOGICALREF = OL.UOMREF AND U.LOGICALREF = OL.UOMREF";
        sql += " LEFT JOIN " + logoTigerTableName.CURRENCYLIST + " AS CR ON CR.CURTYPE = OL.PRCURR AND CR.FIRMNR = " + USER.firmano;
        sql += " LEFT JOIN " + logoTigerTableName.DECARDS + " AS D ON D.LOGICALREF = OL.STOCKREF AND D.CARDTYPE = 2";
        sql += " WHERE INV.TRCODE = " + invType.getValue() + " AND OL.LINETYPE IN (4) AND OL.DATE_ BETWEEN " + params.getCreateDateStart() + str + params.getCreateDateEnd() + " AND INV.CANCELLED = 0 AND OL.SOURCEINDEX = " + params.getWarehouseNo();
        sql += " AND OL.SALESMANREF IN (" + this.getSalesmansInSql(params.getSalesmanRef()) + ')';
        if (0 < i3) {
            sql += " AND INV.CLIENTREF=" + i3;
        }
        sql += ") R ) R WHERE R.RowNum > " + i2;
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = processType;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getExtractCashCreditHeader(final int i2) {
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = "SELECT  CONVERT(VARCHAR(10), DATE_, 104) + ' ' + CONVERT(VARCHAR(8),DATE_, 108) DATE_, FICHENO  , SPECCODE  , GENEXP1  ,GENEXP2,GENEXP3,GENEXP4   FROM " + logoTigerTableName.CLFICHE + " WHERE LOGICALREF =" + i2;
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = null;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getExtractCashCreditHeaderDueDate(final int i2) {
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = "SELECT  CONVERT(VARCHAR(10), DATE_, 104) + ' ' + CONVERT(VARCHAR(8),DATE_, 108) DATE_, FICHENO  , SPECCODE  , GENEXP1  ,GENEXP2,GENEXP3,GENEXP4   FROM " + logoTigerTableName.CLFICHE + " WHERE INVOREF =" + i2;
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = null;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getExtractMoneyOrderHeader(final int i2) {
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = "SELECT CONVERT(VARCHAR(10), DATE_, 104) + ' ' + CONVERT(VARCHAR(8), DATE_, 108) DATE_ ,SPECODE SPECCODE ,FICHENO  ,GENEXP1  ,GENEXP2  ,GENEXP3  ,GENEXP4,LOGICALREF FROM " + logoTigerTableName.BNFICHE + " WHERE LOGICALREF = (SELECT SOURCEFREF FROM " + logoTigerTableName.BNFLINE + " WHERE LOGICALREF = " + i2 + ')';
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = null;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getExtractCashCreditDetail(final int i2, final int i3, final int i4) {
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = "SELECT AMOUNT, SPECODE, LINEEXP,DOCODE FROM " + logoTigerTableName.CLFLINE + " WHERE SOURCEFREF=" + i2 + " AND CLIENTREF=" + i4 + " AND TRCODE=" + i3;
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = null;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getExtractCaseCashDetail(final int i2, final int i3) {
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = "SELECT CONVERT(VARCHAR(10), CLF.DATE_, 104) + ' ' + CONVERT(VARCHAR(8), CLF.DATE_, 108) DATE_, FICHENO,CLF.SPECODE,CLF.LINEEXP, CLF.DOCODE,CLF.AMOUNT FROM " + logoTigerTableName.CLFLINE + " CLF LEFT JOIN " + logoTigerTableName.KSLINES + " KS ON CLF.SOURCEFREF = KS.LOGICALREF WHERE CLF.SOURCEFREF= " + i2 + "  AND CLF.CLIENTREF = " + i3;
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = null;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getExtractInvoiceHeader(final int i2) {
        result = new SelectResult();
        sql = "SELECT INV.FICHENO, CONVERT(VARCHAR(10), INV.DATE_, 104) DATE_, C.CODE + ' / ' + C.DEFINITION_ [CUSTOMER], CAST(BR.NR AS VARCHAR(3)) + ', ' + BR.NAME [BRANCH], CAST(D.NR AS VARCHAR(3)) + ', ' + D.NAME [DEPARTMENT], CAST(F.NR AS VARCHAR(3)) + ', ' + F.NAME [FACTORY], CAST(W.NR AS VARCHAR(3)) + ', ' + W.NAME [WAREHOUSE], P.CODE + '/ ' + P.DEFINITION_ [PAYMENT], INV.TRADINGGRP, INV.DOCODE, INV.SPECODE, INV.CYPHCODE, SLS.CODE [SALESMAN], PR.CODE [PROJECTCODE], (SELECT CODE + ' / ' + DEFINITION_ [SHIPACCOUNT]";
        sql += SqlLiteVariable._FROM + logoTigerTableName.CLCARD + " WHERE LOGICALREF = INV.RECVREF) [SHIPACCOUNT]";
        sql += ", SHPINF.CODE + ', ' + SHPINF.NAME [SHIPADDRESS], INV.SHPTYPCOD [SHIPDELIVERYMETHOD], INV.SHPAGNCOD [SHIPAGENT], INV.FRGTYPCOD [SHIPTRANSTYPE],INV.GENEXP1 [DESC1],INV.GENEXP2 [DESC2],INV.GENEXP3 [DESC3],INV.GENEXP4 [DESC4]";
        sql += SqlLiteVariable._FROM + logoTigerTableName.INVOICE + " INV";
        sql += " LEFT JOIN " + logoTigerTableName.CLCARD + " C ON C.LOGICALREF = INV.CLIENTREF";
        sql += " LEFT JOIN " + logoTigerTableName.CAPIDIV + " BR ON BR.NR = INV.BRANCH";
        sql += " LEFT JOIN " + logoTigerTableName.CAPIDEPT + " D ON D.NR = INV.DEPARTMENT";
        sql += " LEFT JOIN " + logoTigerTableName.CAPIFACTORY + " F ON F.NR = INV.FACTORYNR";
        sql += " LEFT JOIN " + logoTigerTableName.CAPIWHOUSE + " W ON W.NR = INV.SOURCEINDEX";
        sql += " LEFT JOIN " + logoTigerTableName.PAYPLANS + " P ON P.LOGICALREF = INV.PAYDEFREF";
        sql += " LEFT JOIN " + logoTigerTableName.SLSMAN + " SLS ON SLS.LOGICALREF = INV.SALESMANREF";
        sql += " LEFT JOIN " + logoTigerTableName.PROJECT + " PR ON PR.LOGICALREF = INV.PROJECTREF";
        sql += " LEFT JOIN " + logoTigerTableName.SHIPINFO + " SHPINF ON SHPINF.LOGICALREF = INV.SHIPINFOREF";
        sql += " WHERE INV.LOGICALREF = " + i2 + " AND BR.FIRMNR = " + USER.firmano + " AND D.FIRMNR = " + USER.firmano + " AND F.FIRMNR = " + USER.firmano + " AND W.FIRMNR = " + USER.firmano;
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = null;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getExtractInvoiceDetail(final int i2) {
        result = new SelectResult();
        sql = "SELECT  'DEFINITION_'=  CASE L.LINETYPE  WHEN 2 THEN ''  WHEN 4 THEN   ( SELECT CODE+' / '+DEFINITION_ FROM " + logoTigerTableName.SRVCARD + " WHERE LOGICALREF=L.STOCKREF)  ELSE ( SELECT CODE+' / '+NAME  FROM " + logoTigerTableName.ITEMS + "  WHERE LOGICALREF=L.STOCKREF)  END , CAST(L.AMOUNT AS MONEY) [AMOUNT],CAST (L.PRICE AS MONEY) [PRICE], CAST(L.PRPRICE AS MONEY) [PRPRICE], CAST(L.TOTAL AS MONEY) [TOTAL], L.VAT,CAST(L.VATAMNT AS MONEY) [VATAMOUNT], L.LINETYPE, L.GLOBTRANS";
        sql += SqlLiteVariable._FROM + logoTigerTableName.STLINE + " L ";
        sql += " WHERE L.INVOICEREF = " + i2 + " AND L.FIRMNR = " + USER.firmano;
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = null;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getExtractOrderHeader(final int i2) {
        result = new SelectResult();
        sql = "SELECT ORF.FICHENO, CONVERT(VARCHAR(10), ORF.DATE_, 104) DATE_, C.CODE + ' / ' + C.DEFINITION_ [CUSTOMER], CAST(BR.NR AS VARCHAR(3)) + ', ' + BR.NAME [BRANCH], CAST(D.NR AS VARCHAR(3)) + ', ' + D.NAME [DEPARTMENT], CAST(F.NR AS VARCHAR(3)) + ', ' + F.NAME [FACTORY], CAST(W.NR AS VARCHAR(3)) + ', ' + W.NAME [WAREHOUSE], P.CODE + '/ ' + P.DEFINITION_ [PAYMENT], ORF.TRADINGGRP, ORF.DOCODE, ORF.SPECODE, ORF.CYPHCODE, SLS.CODE [SALESMAN], PR.CODE [PROJECTCODE], (SELECT CODE + ' / ' + DEFINITION_ [SHIPACCOUNT]";
        sql += SqlLiteVariable._FROM + logoTigerTableName.CLCARD + " WHERE LOGICALREF = ORF.RECVREF) [SHIPACCOUNT]";
        sql += ", SHPINF.CODE + ', ' + SHPINF.NAME [SHIPADDRESS], ORF.SHPTYPCOD [SHIPDELIVERYMETHOD], ORF.SHPAGNCOD [SHIPAGENT], ORF.SHPTYPCOD [SHIPTRANSTYPE],ORF.GENEXP1 [DESC1],ORF.GENEXP2 [DESC2],ORF.GENEXP3 [DESC3],ORF.GENEXP4 [DESC4]";
        sql += SqlLiteVariable._FROM + logoTigerTableName.ORFICHE + " ORF";
        sql += " LEFT JOIN " + logoTigerTableName.CLCARD + " C ON C.LOGICALREF = ORF.CLIENTREF";
        sql += " LEFT JOIN " + logoTigerTableName.CAPIDIV + " BR ON BR.NR = ORF.BRANCH";
        sql += " LEFT JOIN " + logoTigerTableName.CAPIDEPT + " D ON D.NR = ORF.DEPARTMENT";
        sql += " LEFT JOIN " + logoTigerTableName.CAPIFACTORY + " F ON F.NR = ORF.FACTORYNR";
        sql += " LEFT JOIN " + logoTigerTableName.CAPIWHOUSE + " W ON W.NR = ORF.SOURCEINDEX";
        sql += " LEFT JOIN " + logoTigerTableName.PAYPLANS + " P ON P.LOGICALREF = ORF.PAYDEFREF";
        sql += " LEFT JOIN " + logoTigerTableName.SLSMAN + " SLS ON SLS.LOGICALREF = ORF.SALESMANREF";
        sql += " LEFT JOIN " + logoTigerTableName.PROJECT + " PR ON PR.LOGICALREF = ORF.PROJECTREF";
        sql += " LEFT JOIN " + logoTigerTableName.SHIPINFO + " SHPINF ON SHPINF.LOGICALREF = ORF.SHIPINFOREF";
        sql += " WHERE ORF.LOGICALREF = " + i2 + " AND BR.FIRMNR = " + USER.firmano + " AND D.FIRMNR = " + USER.firmano + " AND F.FIRMNR = " + USER.firmano + " AND W.FIRMNR = " + USER.firmano;
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = null;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getExtractOrderDetail(final int i2) {
        result = new SelectResult();
        sql = "SELECT  'DEFINITION_'=  CASE L.LINETYPE  WHEN 2 THEN ''  WHEN 4 THEN   ( SELECT CODE+' / '+DEFINITION_ FROM " + logoTigerTableName.SRVCARD + " WHERE LOGICALREF=L.STOCKREF)  ELSE ( SELECT CODE+' / '+NAME  FROM " + logoTigerTableName.ITEMS + "  WHERE LOGICALREF=L.STOCKREF)  END , CAST(L.AMOUNT AS MONEY) [AMOUNT],CAST (L.PRICE AS MONEY) [PRICE], CAST(L.PRPRICE AS MONEY) [PRPRICE], CAST(L.TOTAL AS MONEY) [TOTAL], L.VAT,CAST(L.VATAMNT AS MONEY) [VATAMOUNT], L.LINETYPE, L.GLOBTRANS";
        sql += SqlLiteVariable._FROM + logoTigerTableName.ORFLINE + " L ";
        sql += " WHERE L.ORDFICHEREF = " + i2;
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = null;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getExtractList(final int i2, final String createDateStart, final String createDateEnd, final ReportCurrencyUnit currencyUnit, final int i3) {
        final String str;
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        Intrinsics.checkNotNullParameter(currencyUnit, "currencyUnit");
        final boolean salesPersonCanSeeTheirOwnOperations = this.getBaseErp().getSalesPersonCanSeeTheirOwnOperations();
        if (null == this.result) {
            result = new SelectResult();
        }
        if (ReportCurrencyUnit.LOCAL == currencyUnit) {
            str = "AMOUNT";
        } else if (ReportCurrencyUnit.REPORT == currencyUnit) {
            str = "REPORTNET";
        } else if (ReportCurrencyUnit.OPERATION != currencyUnit) {
            str = "";
        } else {
            str = "TRNET";
        }
        sql = "SELECT *, 0 AS TOTAL_DEBIT, 0 AS TOTAL_CREDIT, 0 AS BALANCE FROM (SELECT *,CASE SIGN WHEN 0 THEN " + str + " ELSE 0 END AS DEBIT, CASE SIGN WHEN 1 THEN " + str + " ELSE 0 END AS CREDIT FROM (";
        sql += "SELECT CTRNS.LOGICALREF, CTRNS.DATE_, CTRNS.TRANNO [FICHENO],CTRNS.TRCODE, CTRNS.SIGN, CTRNS.AMOUNT, CTRNS.DOCODE, CTRNS.LINEEXP, CTRNS.REPORTNET, CTRNS.TRCURR, CTRNS.TRNET, CTRNS.SOURCEFREF, CTRNS.MODULENR,CTRNS.FTIME ";
        sql += SqlLiteVariable._FROM + logoTigerTableName.CLFLINE + " CTRNS WITH (NOLOCK)";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.PAYPLANS + " PAYPL WITH (NOLOCK) ON (CTRNS.PAYDEFREF = PAYPL.LOGICALREF)";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.CLFICHE + " CLFIC WITH (NOLOCK) ON (CTRNS.SOURCEFREF = CLFIC.LOGICALREF)";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.INVOICE + " INVFC WITH (NOLOCK) ON (CTRNS.SOURCEFREF = INVFC.LOGICALREF)";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.CSROLL + " RLFIC WITH (NOLOCK) ON (CTRNS.SOURCEFREF = RLFIC.LOGICALREF)";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.EMFICHE + " GLFIC WITH (NOLOCK) ON (CTRNS.ACCFICHEREF = GLFIC.LOGICALREF)";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.CLCARD + " CLNTC WITH (NOLOCK) ON (CTRNS.CLIENTREF = CLNTC.LOGICALREF)";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.ORFICHE + " ORFIC WITH (NOLOCK) ON (CTRNS.SOURCEFREF = ORFIC.LOGICALREF)";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.PURCHOFFER + " POFFER WITH (NOLOCK) ON (CTRNS.OFFERREF = POFFER.LOGICALREF)";
        sql += " WHERE  CTRNS.CANCELLED=0 AND  (CTRNS.TRCODE IN (31, 32, 33, 34, 36, 37, 38, 39, 43, 44, 56, 1, 2, 3, 4, 5, 6, 12, 14, 41, 42, 45, 46, 70, 71, 72, 73, 20, 21, 24, 25, 28, 29, 61, 62, 63, 64, 75, 81, 82)) AND (CTRNS.CLIENTREF = " + i2 + ") AND (CTRNS.DATE_  BETWEEN " + createDateStart + "AND " + createDateEnd;
        if (salesPersonCanSeeTheirOwnOperations) {
            sql += " AND CTRNS.SALESMANREF = " + i3;
        }
        sql += ") UNION ";
        sql += " SELECT CTRNS.LOGICALREF, CTRNS.DATE_, CTRNS.TRANNO [FICHENO], CASE WHEN CTRNS.TRCODE IN(38,31,33,36,34) AND INVFC.FROMKASA=1 THEN 99 WHEN CTRNS.TRCODE=31 AND INVFC.FROMKASA=1 THEN 99 ELSE CTRNS.TRCODE END AS TRCODE , CASE WHEN CTRNS.SIGN=0 AND INVFC.FROMKASA=1 AND CTRNS.TRCODE IN (38,36) THEN 1  WHEN CTRNS.SIGN=1 AND INVFC.FROMKASA=1 AND CTRNS.TRCODE IN (31,33,34) THEN 0 ELSE CTRNS.SIGN END AS SIGN , CTRNS.AMOUNT, CTRNS.DOCODE, CTRNS.LINEEXP, CTRNS.REPORTNET, CTRNS.TRCURR, CTRNS.TRNET, CTRNS.SOURCEFREF, CTRNS.MODULENR,CTRNS.FTIME ";
        sql += SqlLiteVariable._FROM + logoTigerTableName.CLFLINE + " CTRNS WITH (NOLOCK)";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.INVOICE + " INVFC WITH (NOLOCK) ON (CTRNS.SOURCEFREF = INVFC.LOGICALREF)";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.KSLINES + " KSLINE WITH (NOLOCK) ON  (CTRNS.SOURCEFREF = KSLINE.TRANSREF) AND KSLINE.TRANSREF=INVFC.LOGICALREF AND INVFC.FROMKASA=1 ";
        sql += " WHERE CTRNS.CANCELLED=0 AND  (CTRNS.TRCODE IN (31, 32, 33, 34, 36, 37, 38, 39, 43, 44, 56, 1, 2, 3, 4, 5, 6, 12, 14, 41, 42, 45, 46, 70, 71, 72, 73, 20, 21, 24, 25, 28, 29, 61, 62, 63, 64, 75, 81, 82)) AND (CTRNS.CLIENTREF = " + i2 + ") AND (CTRNS.DATE_  BETWEEN " + createDateStart + "AND " + createDateEnd;
        if (salesPersonCanSeeTheirOwnOperations) {
            sql += " AND CTRNS.SALESMANREF = " + i3 + " AND KSLINE.SALESMANREF = " + i3;
        }
        sql += ")) TMPTABLE) TMP";
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.orderByText = "ORDER BY  DATE_, FTIME, LOGICALREF";
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        selectResult3.type = ProcessType.REPORTEXTRACT;
        final SelectResult selectResult4 = result;
        Intrinsics.checkNotNull(selectResult4);
        return selectResult4;
    }
    public SelectResult getAverageTotalList(final int i2, final String createDateStart, final String createDateEnd) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = "SELECT P.PROCDATE , P.TOTAL , P.SIGN , P.DATE_  FROM " + logoTigerTableName.PAYTRANS + " P ";
        sql += " WHERE (CARDREF=" + i2 + ") AND (CANCELLED <> 1) AND (PAIDINCASH = 0) AND";
        sql += "(P.PROCDATE BETWEEN " + createDateStart + SqlLiteVariable._AND + createDateEnd + ") AND ";
        sql += " (((MODULENR = 5) AND (TRCODE IN (1,2,3,4,5,6,12,14,41,42,45,46,70,71))) OR ";
        sql += " ((MODULENR = 7) AND  (TRCODE IN (3,4))) OR ((MODULENR = 6) AND (TRCODE IN (1,2,3,4))) OR ";
        sql += " ((MODULENR = 3) AND (TRCODE IN (1,2))) OR ((MODULENR IN (61,62)) AND (TRCODE IN (3))) OR ";
        sql += " ((MODULENR IN (61,62)) AND (TRCODE IN (4))) OR ((MODULENR = 10) AND (TRCODE IN (1,2))) OR ";
        sql += " ((MODULENR = 4) AND (TRCODE IN (1,2,3,4,6,7,8,9,13,14,26,41,42)))) ";
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.orderByText = " ORDER BY SIGN, DATE_";
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        selectResult3.type = ProcessType.FILLREPORTAVGCALC;
        final SelectResult selectResult4 = result;
        Intrinsics.checkNotNull(selectResult4);
        return selectResult4;
    }
    public SelectResult getCustomerDebitTracking(final int i2) {
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = " SELECT   ";
        sql += " PTRNS.PROCDATE PROCDATE , ";
        sql += " PTRNS.DATE_ DATE_ ,";
        sql += " PTRNS.MODULENR MODULENR ,";
        sql += " PTRNS.TRCODE TRCODE ,";
        sql += " PTRNS.TRCURR TRCURR ,";
        sql += " LC.CURCODE CURCODE ,";
        sql += " B=CASE PTRNS.SIGN WHEN 0 THEN PTRNS.TOTAL ELSE 0 END ,";
        sql += " A=CASE PTRNS.SIGN WHEN 1 THEN PTRNS.TOTAL ELSE 0 END ,";
        sql += " K=CASE PTRNS.CROSSREF WHEN 0 THEN PTRNS.TOTAL ELSE 0 END ,";
        sql += " (CASE PTRNS.MODULENR WHEN 4 THEN INVFC.FICHENO WHEN 5 THEN CLFIC.FICHENO WHEN 6 THEN RLFIC.ROLLNO WHEN 7 THEN BNFIC.FICHENO WHEN 10 THEN CASHTR.FICHENO ELSE '' END) FICHENO ";
        sql += " , (CASE PTRNS.MODULENR WHEN 4 THEN (case PTRNS.TRCODE ";
        sql += " WHEN 1 then 'Mal al\u0131m faturas\u0131'";
        sql += " WHEN 2 then 'Perakende s. iade faturas\u0131'";
        sql += " WHEN 3 then 'Toptan s. iade faturas\u0131' ";
        sql += " WHEN 4 then 'Al\u0131nan hizmet faturas\u0131' ";
        sql += " WHEN 5 then 'Al\u0131nan proforma fatura'";
        sql += " WHEN 6 then 'Al\u0131m iade faturas\u0131'";
        sql += " WHEN 7 then 'Perakende sat\u0131\u015f faturas\u0131'";
        sql += " WHEN 8 then 'Toptan sat\u0131\u015f faturas\u0131'";
        sql += " WHEN 9 then 'Verilen hizmet faturas\u0131'";
        sql += " WHEN 10 then 'Verilen proforma fatura'";
        sql += " WHEN 11 then 'Verilen vade fark\u0131 faturas\u0131'";
        sql += " WHEN 12 then 'Al\u0131nan vade fark\u0131 faturas\u0131'";
        sql += " WHEN 13 then 'Al\u0131nan fiyat fark\u0131 faturas\u0131'";
        sql += " WHEN 14 then 'Verilen fiyat fark\u0131 faturas\u0131'";
        sql += " else '' end) WHEN 5 THEN (case CTRNS.TRCODE ";
        sql += " WHEN 1 then 'Nakit tahsilat'";
        sql += " WHEN 2 then 'Nakit \u00f6deme'";
        sql += " WHEN 3 then 'Bor\u00e7 dekontu'";
        sql += " WHEN 4 then 'Alacak dekontu'";
        sql += " WHEN 5 then 'Virman i\u015flemi'";
        sql += " WHEN 6 then 'Kur fark\u0131 i\u015flemi'";
        sql += " WHEN 12 then '\u00d6zel i\u015flem'";
        sql += " WHEN 14 then 'A\u00e7\u0131l\u0131\u015f i\u015flemi'";
        sql += " WHEN 70 then 'Kredi Kart\u0131 Fi\u015fi'";
        sql += " WHEN 71 then 'Kredi Kart\u0131 \u0130ade Fi\u015fi'";
        sql += " else '' end) WHEN 6 THEN (case RLFIC.TRCODE ";
        sql += " WHEN 1 then '\u00c7ek Giri\u015fi'";
        sql += " WHEN 2 then 'Senet Giri\u015fi'";
        sql += " WHEN 3 then '\u00c7ek \u00c7\u0131k\u0131\u015f (Cari Hesaba)'";
        sql += " WHEN 4 then 'Senet \u00c7\u0131k\u0131\u015f (Cari Hesaba)'";
        sql += " WHEN 5 then '\u00c7ek \u00c7\u0131k\u0131\u015f (Banka Tahsil)'";
        sql += " WHEN 6 then 'Senet \u00c7\u0131k\u0131\u015f (Banka Tahsil)'";
        sql += " WHEN 7 then '\u00c7ek \u00c7\u0131k\u0131\u015f (Banka Teminat)'";
        sql += " WHEN 8 then 'Senet \u00c7\u0131k\u0131\u015f (Banka Teminat)'";
        sql += " WHEN 9 then '\u0130\u015flem Bordrosu (M\u00fc\u015fteri \u00c7eki)'";
        sql += " WHEN 10 then '\u0130\u015flem Bordrosu (M\u00fc\u015fteri Senedi)'";
        sql += " WHEN 11 then '\u0130\u015flem Bordrosu (Kendi \u00c7ekimiz)'";
        sql += " WHEN 12 then '\u0130\u015flem Bordrosu (Bor\u00e7 Senedimiz)'";
        sql += " WHEN 13 then '\u0130\u015fyerleri Aras\u0131 \u0130. Bord. (M\u00fc\u015fteri \u00c7eki)'";
        sql += " WHEN 14 then '\u0130\u015fyerleri Aras\u0131 \u0130. Bord. (M\u00fc\u015fteri Senedi)'";
        sql += " else '' end) WHEN 7 THEN (case BTRNS.TRCODE ";
        sql += " WHEN 3 then 'GELEN HAVALELER'";
        sql += " WHEN 4 then 'G\u00d6NDER\u0130LEN HAVALELER'";
        sql += " WHEN 7 then 'D\u00d6V\u0130Z ALI\u015e BELGES\u0130'";
        sql += " WHEN 8 then 'D\u00d6V\u0130Z SATI\u015e BELGES\u0130'";
        sql += " else '' end) WHEN 10 THEN (case PTRNS.TRCODE ";
        sql += " WHEN 1 then 'C/H Tahsilat'";
        sql += " WHEN 2 then 'C/H \u00d6deme '";
        sql += " WHEN 21 then 'Bankaya Yat\u0131r\u0131lan'";
        sql += " WHEN 22 then 'Bankadan \u00c7ekilen'";
        sql += " WHEN 31 then 'Mal Al\u0131m Faturas\u0131'";
        sql += " WHEN 32 then 'Perakende S. \u0130ade Faturas\u0131'";
        sql += " WHEN 33 then 'Toptan S. iade Faturas\u0131'";
        sql += " WHEN 34 then 'Al\u0131nan Hizmet Faturas\u0131'";
        sql += " WHEN 35 then 'Al\u0131m \u0130ade Faturas\u0131'";
        sql += " WHEN 36 then 'Perakende Sat\u0131\u015f Faturas\u0131'";
        sql += " WHEN 37 then 'Toptan Sat\u0131\u015f Faturas\u0131'";
        sql += " WHEN 38 then 'Verilen Hizmet Faturas\u0131'";
        sql += " WHEN 39 then 'M\u00fcstahsil Makbuzu'";
        sql += " WHEN 41 then 'Muh. Tahsil \u0130\u015flemi'";
        sql += " WHEN 42 then 'Muh. Tediye \u0130\u015flemi'";
        sql += " WHEN 51 then 'Personel Bor\u00e7lanmas\u0131'";
        sql += " WHEN 52 then 'Personel Geri \u00d6demesi'";
        sql += " WHEN 61 then '\u00c7ek Tahsili'";
        sql += " WHEN 62 then 'Senet Tahsili'";
        sql += " WHEN 63 then '\u00c7ek \u00f6demesi'";
        sql += " WHEN 64 then 'Senet \u00d6demesi'";
        sql += " WHEN 71 then 'A\u00e7\u0131l\u0131\u015f (Bor\u00e7)'";
        sql += " WHEN 72 then 'A\u00e7\u0131l\u0131\u015f (Alacak)'";
        sql += " WHEN 73 then 'Virman (Bor\u00e7)'";
        sql += " WHEN 74 then 'Virman (Alacak)'";
        sql += " WHEN 75 then 'Gider Pusulas\u0131'";
        sql += " WHEN 76 then 'Verilen Serbest Meslek Makbuzu'";
        sql += " WHEN 77 then 'Al\u0131nan Serbest Meslek Makbuzu'";
        sql += " else '' end)END) TRCODEDESC ";
        sql += SqlLiteVariable._FROM + logoTigerTableName.PAYTRANS + " PTRNS WITH(NOLOCK) LEFT OUTER JOIN " + logoTigerTableName.CLCARD + " CLNTC WITH(NOLOCK) ON (PTRNS.CARDREF = CLNTC.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.INVOICE + " INVFC WITH(NOLOCK) ON (PTRNS.FICHEREF = INVFC.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.CLFLINE + " CTRNS WITH(NOLOCK) ON (PTRNS.FICHEREF = CTRNS.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.KSLINES + " CASHTR WITH(NOLOCK) ON (PTRNS.FICHEREF = CASHTR.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.BNFLINE + " BTRNS WITH(NOLOCK) ON (PTRNS.FICHEREF = BTRNS.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.CSROLL + " RLFIC WITH(NOLOCK) ON (PTRNS.FICHEREF = RLFIC.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.CLFICHE + " CLFIC WITH(NOLOCK) ON (CTRNS.SOURCEFREF = CLFIC.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.BNFICHE + " BNFIC WITH(NOLOCK) ON (BTRNS.SOURCEFREF = BNFIC.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.PAYTRANS + " PTRNS_X WITH(NOLOCK) ON (PTRNS.CROSSREF = PTRNS_X.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.INVOICE + " INVFC_X WITH(NOLOCK) ON (PTRNS_X.FICHEREF = INVFC_X.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.CLFLINE + " CTRNS_X WITH(NOLOCK) ON (PTRNS_X.FICHEREF = CTRNS_X.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.KSLINES + " CASHTR_X WITH(NOLOCK) ON (PTRNS_X.FICHEREF = CASHTR_X.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.BNFLINE + " BTRNS_X WITH(NOLOCK) ON (PTRNS_X.FICHEREF = BTRNS_X.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.CSROLL + " RLFIC_X WITH(NOLOCK) ON (PTRNS_X.FICHEREF = RLFIC_X.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.CLFICHE + " CLFIC_X WITH(NOLOCK) ON (CTRNS_X.SOURCEFREF = CLFIC_X.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.BNFICHE + " BNFIC_X WITH(NOLOCK) ON (BTRNS_X.SOURCEFREF = BNFIC_X.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.L_CURRENCYLIST + " LC WITH (NOLOCK) ON (LC.CURTYPE = PTRNS.TRCURR AND LC.FIRMNR =" + USER.firmano + ')';
        sql += " WHERE CLNTC.LOGICALREF=" + i2 + " AND (PTRNS.CANCELLED <> 1) ";
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = ProcessType.FILLREPORTDEBITFOLLOW;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getOrderReportQuery(final int i2, final int i3, final String createDateStart, final String createDateEnd, final int i4, final int i5) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = " SELECT TOP 1000 O.LOGICALREF , ";
        sql += "O.STATUS AS B,O.DATE_,O.FICHENO,O.NETTOTAL  , ";
        sql += "(SELECT O.LOGICALREF FROM  " + logoTigerTableName.ORFLINE + " OL ";
        sql += "WHERE O.LOGICALREF=OL.ORDFICHEREF   AND OL.SHIPPEDAMOUNT<>0) AS  A ";
        sql += " ,(SELECT SUM(AMOUNT-SHIPPEDAMOUNT) FROM " + logoTigerTableName.ORFLINE + " WITH(NOLOCK) WHERE ORDFICHEREF=O.LOGICALREF) AS X ";
        sql += " ,(SELECT SUM(AMOUNT) FROM " + logoTigerTableName.ORFLINE + " WITH(NOLOCK) WHERE ORDFICHEREF=O.LOGICALREF) AS Y ";
        sql += SqlLiteVariable._FROM + logoTigerTableName.ORFICHE + " AS O WITH(NOLOCK) ," + logoTigerTableName.CLCARD + " AS C WITH(NOLOCK) WHERE C.LOGICALREF=O.CLIENTREF ";
        sql += " AND SALESMANREF=" + i3;
        sql += " AND CLIENTREF=" + i2;
        sql += " AND DATE_ BETWEEN " + createDateStart + SqlLiteVariable._AND + createDateEnd;
        if (-1 != i4) {
            sql += " AND STATUS=" + i4;
        }
        if (-1 != i5) {
            if (1 == i5) {
                sql += " AND (SELECT SUM(AMOUNT-SHIPPEDAMOUNT) FROM " + logoTigerTableName.ORFLINE + " WITH(NOLOCK) WHERE ORDFICHEREF=O.LOGICALREF)=0 ";
            } else if (2 == i5) {
                sql += " AND (SELECT SUM(AMOUNT-SHIPPEDAMOUNT) FROM " + logoTigerTableName.ORFLINE + " WITH(NOLOCK) WHERE ORDFICHEREF=O.LOGICALREF)<>0 AND (SELECT SUM(AMOUNT) FROM " + logoTigerTableName.ORFLINE + " WHERE ORDFICHEREF=O.LOGICALREF)<>(SELECT SUM(AMOUNT-SHIPPEDAMOUNT) FROM " + logoTigerTableName.ORFLINE + " WHERE ORDFICHEREF=O.LOGICALREF) ";
            } else if (3 == i5) {
                sql += " AND (SELECT SUM(AMOUNT) FROM " + logoTigerTableName.ORFLINE + " WITH(NOLOCK) WHERE ORDFICHEREF=O.LOGICALREF)=(SELECT SUM(AMOUNT-SHIPPEDAMOUNT) FROM " + logoTigerTableName.ORFLINE + " WHERE ORDFICHEREF=O.LOGICALREF) ";
            }
        }
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.orderByText = " ORDER BY DATE_ DESC";
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        selectResult3.type = ProcessType.FILLREPORTCOLLECTIONSLIST;
        final SelectResult selectResult4 = result;
        Intrinsics.checkNotNull(selectResult4);
        return selectResult4;
    }
    public SelectResult getCollectionsListQuery(final int i2, final String createDateStart, final String createDateEnd, final ReportDebitFilterType reportDebitFilterType) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = " SELECT CTRNS_SIGN SIGN, CTRNS_TRCODE AS X,PTRNS_DATE_ AS Y,PTRNS_PROCDATE AS Z, PTRNS_FICHENO AS FICHENO,";
        sql += " PTRNS_BELGENO AS B , PTRNS_TOTAL AS A, PTRNS_UNCLOSEDTOT AS K, DATEDIFF(day,GETDATE(),PTRNS_DATE_) AS T, PTRNS_MODULENR, PTRNS_REF, PTRNS_FICHEREF FROM ";
        sql += " (SELECT PTRNS.SIGN CTRNS_SIGN,PTRNS.PROCDATE PTRNS_PROCDATE, PTRNS.MODULENR AS PTRNS_MODULENR, PTRNS.LOGICALREF AS PTRNS_REF, PTRNS.FICHEREF AS PTRNS_FICHEREF, ";
        sql += " (CASE PTRNS.MODULENR WHEN 4 THEN INVFC.FICHENO WHEN 5 THEN CLFIC.FICHENO WHEN 6 THEN RLFIC.ROLLNO WHEN 7 THEN BNFIC.FICHENO WHEN 10 THEN CASHTR.FICHENO ELSE '' END) PTRNS_FICHENO ";
        sql += ", PTRNS.DATE_ PTRNS_DATE_ ";
        sql += " , PTRNS.TOTAL PTRNS_TOTAL ";
        sql += " , ((PTRNS.TOTAL - PTRNS.PAID)) PTRNS_UNCLOSEDTOT ";
        sql += " , CLNTC.CODE CLNTC_CODE ";
        sql += " , CLNTC.DEFINITION_ CLNTC_DEFINITION_ ";
        sql += " ,(CASE PTRNS.MODULENR WHEN 4 THEN INVFC.DOCODE WHEN 5 THEN CLFIC.DOCODE WHEN 6 THEN RLFIC.ROLLNO WHEN 7 THEN BNFIC.FICHENO WHEN 10 THEN CASHTR.FICHENO ELSE '' END) PTRNS_BELGENO ";
        sql += " , (CASE PTRNS.MODULENR WHEN 4 THEN (case PTRNS.TRCODE ";
        sql += " WHEN 1 then 'Mal al\u0131m faturas\u0131'";
        sql += " WHEN 2 then 'Perakende s. iade faturas\u0131'";
        sql += " WHEN 3 then 'Toptan s. iade faturas\u0131' ";
        sql += " WHEN 4 then 'Al\u0131nan hizmet faturas\u0131' ";
        sql += " WHEN 5 then 'Al\u0131nan proforma fatura'";
        sql += " WHEN 6 then 'Al\u0131m iade faturas\u0131'";
        sql += " WHEN 7 then 'Perakende sat\u0131\u015f faturas\u0131'";
        sql += " WHEN 8 then 'Toptan sat\u0131\u015f faturas\u0131'";
        sql += " WHEN 9 then 'Verilen hizmet faturas\u0131'";
        sql += " WHEN 10 then 'Verilen proforma fatura'";
        sql += " WHEN 11 then 'Verilen vade fark\u0131 faturas\u0131'";
        sql += " WHEN 12 then 'Al\u0131nan vade fark\u0131 faturas\u0131'";
        sql += " WHEN 13 then 'Al\u0131nan fiyat fark\u0131 faturas\u0131'";
        sql += " WHEN 14 then 'Verilen fiyat fark\u0131 faturas\u0131'";
        sql += " else '' end) WHEN 5 THEN (case CTRNS.TRCODE ";
        sql += " WHEN 1 then 'Nakit tahsilat'";
        sql += " WHEN 2 then 'Nakit \u00f6deme'";
        sql += " WHEN 3 then 'Bor\u00e7 dekontu'";
        sql += " WHEN 4 then 'Alacak dekontu'";
        sql += " WHEN 5 then 'Virman i\u015flemi'";
        sql += " WHEN 6 then 'Kur fark\u0131 i\u015flemi'";
        sql += " WHEN 12 then '\u00d6zel i\u015flem'";
        sql += " WHEN 14 then 'A\u00e7\u0131l\u0131\u015f i\u015flemi'";
        sql += " WHEN 70 then 'Kredi Kart\u0131 Fi\u015fi'";
        sql += " WHEN 71 then 'Kredi Kart\u0131 \u0130ade Fi\u015fi'";
        sql += " else '' end) WHEN 6 THEN (case RLFIC.TRCODE ";
        sql += " WHEN 1 then '\u00c7ek Giri\u015fi'";
        sql += " WHEN 2 then 'Senet Giri\u015fi'";
        sql += " WHEN 3 then '\u00c7ek \u00c7\u0131k\u0131\u015f (Cari Hesaba)'";
        sql += " WHEN 4 then 'Senet \u00c7\u0131k\u0131\u015f (Cari Hesaba)'";
        sql += " WHEN 5 then '\u00c7ek \u00c7\u0131k\u0131\u015f (Banka Tahsil)'";
        sql += " WHEN 6 then 'Senet \u00c7\u0131k\u0131\u015f (Banka Tahsil)'";
        sql += " WHEN 7 then '\u00c7ek \u00c7\u0131k\u0131\u015f (Banka Teminat)'";
        sql += " WHEN 8 then 'Senet \u00c7\u0131k\u0131\u015f (Banka Teminat)'";
        sql += " WHEN 9 then '\u0130\u015flem Bordrosu (M\u00fc\u015fteri \u00c7eki)'";
        sql += " WHEN 10 then '\u0130\u015flem Bordrosu (M\u00fc\u015fteri Senedi)'";
        sql += " WHEN 11 then '\u0130\u015flem Bordrosu (Kendi \u00c7ekimiz)'";
        sql += " WHEN 12 then '\u0130\u015flem Bordrosu (Bor\u00e7 Senedimiz)'";
        sql += " WHEN 13 then '\u0130\u015fyerleri Aras\u0131 \u0130. Bord. (M\u00fc\u015fteri \u00c7eki)'";
        sql += " WHEN 14 then '\u0130\u015fyerleri Aras\u0131 \u0130. Bord. (M\u00fc\u015fteri Senedi)'";
        sql += " else '' end) WHEN 7 THEN (case BTRNS.TRCODE ";
        sql += " WHEN 3 then 'GELEN HAVALELER'";
        sql += " WHEN 4 then 'G\u00d6NDER\u0130LEN HAVALELER'";
        sql += " WHEN 7 then 'D\u00d6V\u0130Z ALI\u015e BELGES\u0130'";
        sql += " WHEN 8 then 'D\u00d6V\u0130Z SATI\u015e BELGES\u0130'";
        sql += " else '' end) WHEN 10 THEN (case PTRNS.TRCODE ";
        sql += " WHEN 1 then 'C/H Tahsilat'";
        sql += " WHEN 2 then 'C/H \u00d6deme '";
        sql += " WHEN 21 then 'Bankaya Yat\u0131r\u0131lan'";
        sql += " WHEN 22 then 'Bankadan \u00c7ekilen'";
        sql += " WHEN 31 then 'Mal Al\u0131m Faturas\u0131'";
        sql += " WHEN 32 then 'Perakende S. \u0130ade Faturas\u0131'";
        sql += " WHEN 33 then 'Toptan S. iade Faturas\u0131'";
        sql += " WHEN 34 then 'Al\u0131nan Hizmet Faturas\u0131'";
        sql += " WHEN 35 then 'Al\u0131m \u0130ade Faturas\u0131'";
        sql += " WHEN 36 then 'Perakende Sat\u0131\u015f Faturas\u0131'";
        sql += " WHEN 37 then 'Toptan Sat\u0131\u015f Faturas\u0131'";
        sql += " WHEN 38 then 'Verilen Hizmet Faturas\u0131'";
        sql += " WHEN 39 then 'M\u00fcstahsil Makbuzu'";
        sql += " WHEN 41 then 'Muh. Tahsil \u0130\u015flemi'";
        sql += " WHEN 42 then 'Muh. Tediye \u0130\u015flemi'";
        sql += " WHEN 51 then 'Personel Bor\u00e7lanmas\u0131'";
        sql += " WHEN 52 then 'Personel Geri \u00d6demesi'";
        sql += " WHEN 61 then '\u00c7ek Tahsili'";
        sql += " WHEN 62 then 'Senet Tahsili'";
        sql += " WHEN 63 then '\u00c7ek \u00f6demesi'";
        sql += " WHEN 64 then 'Senet \u00d6demesi'";
        sql += " WHEN 71 then 'A\u00e7\u0131l\u0131\u015f (Bor\u00e7)'";
        sql += " WHEN 72 then 'A\u00e7\u0131l\u0131\u015f (Alacak)'";
        sql += " WHEN 73 then 'Virman (Bor\u00e7)'";
        sql += " WHEN 74 then 'Virman (Alacak)'";
        sql += " WHEN 75 then 'Gider Pusulas\u0131'";
        sql += " WHEN 76 then 'Verilen Serbest Meslek Makbuzu'";
        sql += " WHEN 77 then 'Al\u0131nan Serbest Meslek Makbuzu'";
        sql += " else '' end)END) CTRNS_TRCODE ";
        sql += SqlLiteVariable._FROM + logoTigerTableName.PAYTRANS + " PTRNS WITH(NOLOCK) LEFT OUTER JOIN " + logoTigerTableName.CLCARD + " CLNTC WITH(NOLOCK) ON (PTRNS.CARDREF = CLNTC.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.INVOICE + " INVFC WITH(NOLOCK) ON (PTRNS.FICHEREF = INVFC.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.CLFLINE + " CTRNS WITH(NOLOCK) ON (PTRNS.FICHEREF = CTRNS.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.KSLINES + " CASHTR WITH(NOLOCK) ON (PTRNS.FICHEREF = CASHTR.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.BNFLINE + " BTRNS WITH(NOLOCK) ON (PTRNS.FICHEREF = BTRNS.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.CSROLL + " RLFIC WITH(NOLOCK) ON (PTRNS.FICHEREF = RLFIC.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.CLFICHE + " CLFIC WITH(NOLOCK) ON (CTRNS.SOURCEFREF = CLFIC.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.BNFICHE + " BNFIC WITH(NOLOCK) ON (BTRNS.SOURCEFREF = BNFIC.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.PAYTRANS + " PTRNS_X WITH(NOLOCK) ON (PTRNS.CROSSREF = PTRNS_X.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.INVOICE + " INVFC_X WITH(NOLOCK) ON (PTRNS_X.FICHEREF = INVFC_X.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.CLFLINE + " CTRNS_X WITH(NOLOCK) ON (PTRNS_X.FICHEREF = CTRNS_X.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.KSLINES + " CASHTR_X WITH(NOLOCK) ON (PTRNS_X.FICHEREF = CASHTR_X.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.BNFLINE + " BTRNS_X WITH(NOLOCK) ON (PTRNS_X.FICHEREF = BTRNS_X.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.CSROLL + " RLFIC_X WITH(NOLOCK) ON (PTRNS_X.FICHEREF = RLFIC_X.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.CLFICHE + " CLFIC_X WITH(NOLOCK) ON (CTRNS_X.SOURCEFREF = CLFIC_X.LOGICALREF) ";
        sql += " LEFT OUTER JOIN " + logoTigerTableName.BNFICHE + " BNFIC_X WITH(NOLOCK) ON (BTRNS_X.SOURCEFREF = BNFIC_X.LOGICALREF) ";
        sql += " WHERE CLNTC.LOGICALREF=" + i2 + " AND (PTRNS.CANCELLED <> 1) AND PTRNS.PAID = 0 AND PTRNS.TRCURR = 0 ";
        sql += " AND PTRNS.PROCDATE BETWEEN " + createDateStart + SqlLiteVariable._AND + createDateEnd;
        sql += " AND ( ";
        sql += " (PTRNS.MODULENR=4 AND PTRNS.TRCODE IN (1,2,3,4,5,6,7,8,9,10,11,12,13,14))";
        sql += " OR (PTRNS.MODULENR=5 AND CTRNS.TRCODE IN (1,2,3,4,5,6,12,14,70,71))";
        sql += " OR (PTRNS.MODULENR=6 AND RLFIC.TRCODE IN (1,2,3,4,5,6,7,8,9,10,11,12,13,14)) ";
        sql += " OR (PTRNS.MODULENR=7 AND BTRNS.TRCODE IN (3,4,7,8)) ";
        sql += " OR (PTRNS.MODULENR=10 AND CASHTR.TRCODE IN (11,12,21,22,31,32,33,34,35,36,37,38,39,41,42,51,52,61,62,63,64,71,72,73,74,75,76,77)) ) ";
        sql += " AND PTRNS.PAIDINCASH = 0 ";
        sql += " ) Tmp ";
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.orderByText = "ORDER BY Y,SIGN,PTRNS_MODULENR,PTRNS_FICHEREF,PTRNS_REF";
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        selectResult3.type = ProcessType.FILLREPORTCOLLECTIONSLIST;
        final SelectResult selectResult4 = result;
        Intrinsics.checkNotNull(selectResult4);
        return selectResult4;
    }
    public SelectResult getNotPaymentInvoice(final int i2, final String createDateStart, final String createDateEnd, final ReportDebitFilterType reportDebitFilterType) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = "SELECT P.DATE_, P.TOTAL, P.SIGN,P.MODULENR,P.LOGICALREF,P.FICHEREF AS PTRNS_FICHEREF  FROM " + logoTigerTableName.PAYTRANS + " P WITH(NOLOCK)   WHERE P.CANCELLED<>1 AND P.PAID = 0 AND P.TRCURR = 0";
        sql += " AND P.CARDREF=" + i2;
        sql += " AND P.PROCDATE  BETWEEN " + createDateStart + SqlLiteVariable._AND + createDateEnd;
        sql += " AND P.PAIDINCASH = 0 ";
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.orderByText = "ORDER BY DATE_,SIGN,MODULENR,PTRNS_FICHEREF,LOGICALREF";
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        selectResult3.type = ProcessType.FILLREPORTAVGCALC;
        final SelectResult selectResult4 = result;
        Intrinsics.checkNotNull(selectResult4);
        return selectResult4;
    }
    public SelectResult getNotPaymentInvoice(final int i2, final String createDateStart, final String createDateEnd) {
        Intrinsics.checkNotNullParameter(createDateStart, "createDateStart");
        Intrinsics.checkNotNullParameter(createDateEnd, "createDateEnd");
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = "SELECT P.DATE_, P.TOTAL, P.SIGN,P.MODULENR,P.LOGICALREF,P.FICHEREF AS PTRNS_FICHEREF  FROM " + logoTigerTableName.PAYTRANS + " P," + logoTigerTableName.INVOICE + " I WHERE I.LOGICALREF=P.FICHEREF AND I.CANCELLED<>1 ";
        sql += " AND I.CLIENTREF=" + i2 + " AND MODULENR=4 AND CROSSREF=0 AND P.TRCODE=8";
        sql += " AND P.DATE_  BETWEEN " + createDateStart + SqlLiteVariable._AND + createDateEnd;
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.orderByText = "ORDER BY DATE_,SIGN,MODULENR,PTRNS_FICHEREF,LOGICALREF";
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        selectResult3.type = ProcessType.FILLREPORTAVGCALC;
        final SelectResult selectResult4 = result;
        Intrinsics.checkNotNull(selectResult4);
        return selectResult4;
    }
    public SelectResult getTotalCreditDebit(final int i2, final String str, final String str2) {
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = "SELECT PTRNS.TRCURR, SUM((PTRNS.TOTAL - PTRNS.PAID)*(1- PTRNS.SIGN)) AS A, SUM((PTRNS.TOTAL - PTRNS.PAID) * PTRNS.SIGN) AS B, SUM(PTRNS.PAID*(1- PTRNS.SIGN)) AS X, SUM(PTRNS.PAID * PTRNS.SIGN) AS Y, SUM((PTRNS.TOTAL - PTRNS.PAID)*(1- PTRNS.SIGN)* PTRNS.PAIDINCASH) AS Z, SUM((PTRNS.TOTAL - PTRNS.PAID) * PTRNS.SIGN*PTRNS.PAIDINCASH) AS W  FROM ";
        sql += logoTigerTableName.PAYTRANS + " PTRNS WHERE PTRNS.CARDREF=" + i2 + " AND (PTRNS.CANCELLED = 0 ) ";
        sql += " GROUP BY PTRNS.TRCURR";
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.orderByText = "";
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        selectResult3.resultXML = "";
        final SelectResult selectResult4 = result;
        Intrinsics.checkNotNull(selectResult4);
        selectResult4.type = ProcessType.FILLREPORTAVGCALC;
        final SelectResult selectResult5 = result;
        Intrinsics.checkNotNull(selectResult5);
        return selectResult5;
    }
    public SelectResult getExtractChequeDeedHeader(final int i2) {
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = "SELECT  CONVERT(VARCHAR(10), DATE_, 104) + ' ' + CONVERT(VARCHAR(8),DATE_, 108) DATE_, ROLLNO  , SPECODE  , GENEXP1  ,GENEXP2,GENEXP3,GENEXP4   FROM " + logoTigerTableName.CSROLL + " WHERE LOGICALREF =" + i2;
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = null;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
    public SelectResult getExtractChequeDeedDetail(final int i2, final int i3) {
        if (null == this.result) {
            result = new SelectResult();
        }
        sql = "SELECT C.DUEDATE AS VAR1,C.AMOUNT AS VAR2 , C.NEWSERINO AS VAR3  FROM " + logoTigerTableName.CSCARD + " AS C , " + logoTigerTableName.CSTRANS + " AS T  WHERE C.LOGICALREF=T.CSREF AND ROLLREF=" + i2;
        final SelectResult selectResult = result;
        Intrinsics.checkNotNull(selectResult);
        selectResult.sql = sql;
        final SelectResult selectResult2 = result;
        Intrinsics.checkNotNull(selectResult2);
        selectResult2.type = null;
        final SelectResult selectResult3 = result;
        Intrinsics.checkNotNull(selectResult3);
        return selectResult3;
    }
}
