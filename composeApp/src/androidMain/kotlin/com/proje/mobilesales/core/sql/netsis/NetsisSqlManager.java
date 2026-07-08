package com.proje.mobilesales.core.sql.netsis;

import android.R;
import android.database.Cursor;
import android.util.Log;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.DetailBarcodeFicheType;
import com.proje.mobilesales.core.enums.LineType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.sql.SqlManager;
import com.proje.mobilesales.core.utils.CalculateUtils;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.collections.casefiche.model.CaseFiche;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFiche;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFicheDetail;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditX;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCredit;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCreditDetail;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeed;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFiche;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.Chequedeed;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.ChequedeedDetail;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.dbmodel.Invoice;
import com.proje.mobilesales.features.dbmodel.InvoiceDetail;
import com.proje.mobilesales.features.dbmodel.Order;
import com.proje.mobilesales.features.dbmodel.OrderDetail;
import com.proje.mobilesales.features.dbmodel.WhTransfer;
import com.proje.mobilesales.features.dbmodel.WhTransferDetail;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.penetration.model.Penetration;
import com.proje.mobilesales.features.penetration.model.PenetrationLine;
import com.proje.mobilesales.features.penetration.model.database.UserMainPenetration;
import com.proje.mobilesales.features.penetration.model.database.UserPenetration;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import com.proje.mobilesales.features.todo.model.database.TodoInfoDb;
import com.proje.mobilesales.features.visit.model.Visit;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

public class NetsisSqlManager<T> extends SqlManager<T> {
    private static final String TAG = "com.proje.mobilesales.core.sql.netsis.NetsisSqlManager";
    public Disposable getEDocumentContent(int r1, ResponseListener responseListener, SalesType salesType) {
        return null;
    } 
    public NetsisSqlManager(Scheduler scheduler) {
        super(scheduler);
    } 
    public static ObservableSource lambdagetVisitExam0(int r0) throws Exception {
        return Observable.just(Integer.valueOf(r0));
    } 
    public void getVisitExam(final int r3, final ResponseListener<Visit> responseListener) {
        Observable<T> observableObserveOn = Observable.defer(new Callable() {  
            public Object call() {
                try {
                    return NetsisSqlManager.lambdagetVisitExam0(r3);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).map(new Function() {  
            public Object apply(Object obj) {
                try {
                    return NetsisSqlManager.lambdagetVisitExam1(r3, (Integer) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).filter(new Predicate() {  
            public boolean test(Object obj) {
                try {
                    return NetsisSqlManager.lambdagetVisitExam2((List) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).map(new Function() { 
            public Object apply(Object obj) {
                try {
                    return NetsisSqlManager.lambdagetVisitExam3((List) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).defaultIfEmpty(new Visit()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observableObserveOn.subscribe(new NetsisSqlManagerExternalSyntheticLambda22(responseListener), new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisSqlManager.lambdagetVisitExam4(responseListener, (Throwable) obj);
            }

            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        });
    } 
    public static List lambdagetVisitExam1(int r2, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(VisitInfo.class, "ID=?", new String[]{String.valueOf(r2)});
    } 
    public static boolean lambdagetVisitExam2(List list) throws Exception {
        return list != null && list.size() == 1;
    } 
    public static Visit lambdagetVisitExam3(List list) throws Exception {
        new Visit();
        return ((VisitInfo) list.get(0)).convert();
    } 
    public static void lambdagetVisitExam4(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    } 
    public static ObservableSource lambdagetPenetrationExam5(int r0) throws Exception {
        return Observable.just(Integer.valueOf(r0));
    } 
    public void getPenetrationExam(final int r3, final ResponseListener<Penetration> responseListener) {
        Observable<T> observableObserveOn = Observable.defer(new Callable() {  
            public Object call() {
                try {
                    return NetsisSqlManager.lambdagetPenetrationExam5(r3);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).map(new Function() {  
            public Object apply(Object obj) {
                try {
                    return NetsisSqlManager.lambdagetPenetrationExam6(r3, (Integer) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).filter(new Predicate() {  
            public boolean test(Object obj) {
                try {
                    return NetsisSqlManager.lambdagetPenetrationExam7((List) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).map(new Function() {  
            public Object apply(Object obj) {
                try {
                    return NetsisSqlManager.lambdagetPenetrationExam8(r3, (List) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).defaultIfEmpty(new Penetration()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observableObserveOn.subscribe(new NetsisSqlManagerExternalSyntheticLambda16(responseListener), new Consumer() { 
            public void accept(Object obj) throws Exception {
                NetsisSqlManager.lambdagetPenetrationExam9(responseListener, (Throwable) obj);
            }
            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        });
    } 
    public static List lambdagetPenetrationExam6(int r2, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(UserMainPenetration.class, "ID=?", new String[]{String.valueOf(r2)});
    } 
    public static boolean lambdagetPenetrationExam7(List list) throws Exception {
        return list != null && list.size() == 1;
    } 
    public static Penetration lambdagetPenetrationExam8(int r9, List list) throws Exception {
        new Penetration();
        Penetration penetrationConvert = ((UserMainPenetration) list.get(0)).convert();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_penetration_edit), StringUtils.convertIntToString(r9));
                if (cursorQuery != null && cursorQuery.getCount() > 0 && cursorQuery.moveToFirst()) {
                    penetrationConvert.setPenetrationName(cursorQuery.getString(Integer.parseInt("PDEFINITION")));
                }
            } finally {
                cursorQuery.close();
            }
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "getPenetrationExam: ", e2);
        }
        cursorQuery.close();
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(UserPenetration.class, "PENETFICHEID=?", new String[]{String.valueOf(r9)});
        ArrayList arrayList = new ArrayList();
        try {
            try {
                cursorQuery = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.net_qry_get_penetration_line_edit), StringUtils.convertIntToString(r9));
                for (int r92 = 0; r92 < table.size(); r92++) {
                    PenetrationLine penetrationLineConvert = ((UserPenetration) table.get(r92)).convert();
                    if (cursorQuery.moveToPosition(r92)) {
                        penetrationLineConvert.setProductName(cursorQuery.getString(Integer.parseInt("PRODUCTNAME")));
                        penetrationLineConvert.setProductCode(cursorQuery.getString(Integer.parseInt("PRODUCTCODE")));
                        penetrationLineConvert.setUnit(cursorQuery.getString(Integer.parseInt("PUNIT")));
                        boolean z = true;
                        penetrationLineConvert.setImageActive(cursorQuery.getInt(Integer.parseInt("PER_PIC")) == 1);
                        penetrationLineConvert.setNotActive(cursorQuery.getInt(Integer.parseInt("PER_NOTE")) == 1);
                        if (cursorQuery.getInt(Integer.parseInt("PER_PRICE")) != 1) {
                            z = false;
                        }
                        penetrationLineConvert.setPriceActive(z);
                    }
                    arrayList.add(penetrationLineConvert);
                }
                penetrationConvert.setPenetrations(arrayList);
            } catch (Exception e3) {
                Log.e(MobileSales.TAG, "getPenetrationExam: ", e3);
            }
            return penetrationConvert;
        } catch (Throwable th) {
            throw th;
        }
    }
    public static void lambdagetPenetrationExam9(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }
    public List<Penetration> getPenetrations(int r11) {
        Cursor cursorQuery = null;
        List table;
        ArrayList arrayList;
        int r7 = 0;
        Cursor cursor = null;
        List<T> table2 = r11 == -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(UserMainPenetration.class, "ISTRANSFER=0", null) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(UserMainPenetration.class, "ISTRANSFER=0 AND ID=?", new String[]{String.valueOf(r11)});
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = table2.iterator();
        while (it.hasNext()) {
            Penetration penetrationConvert = ((UserMainPenetration) it.next()).convert();
            try {
                cursorQuery = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_penetration_edit), StringUtils.convertIntToString(r11));
                if (cursorQuery != null) {
                    try {
                        try {
                            if (cursorQuery.getCount() > 0 && cursorQuery.moveToFirst()) {
                                penetrationConvert.setPenetrationName(cursorQuery.getString(Integer.parseInt("PDEFINITION")));
                                penetrationConvert.setExist(StringUtils.convertIntToBoolean(cursorQuery.getInt(Integer.parseInt("PTYPE"))));
                            }
                        } catch (Exception e2) {
                            Log.e(MobileSales.TAG, "getPenetrationExam: ", e2);
                            cursorQuery.close();
                            table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(UserPenetration.class, "PENETFICHEID=?", new String[]{String.valueOf(penetrationConvert.getId())});
                            arrayList = new ArrayList();
                            try {
                                cursorQuery = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.net_qry_get_penetration_line_edit), StringUtils.convertIntToString(R.attr.id));
                                while (r7 < table.size()) {
                                }
                                penetrationConvert.setPenetrations(arrayList);
                                arrayList2.add(penetrationConvert);
                            } catch (Exception e3) {
                                Log.e(MobileSales.TAG, "getPenetrations: ", e3);
                            }
                            cursorQuery.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        cursor.close();
                        throw th;
                    }
                }
            } catch (Exception e4) {

                cursorQuery = null;
            } catch (Throwable th2) {
                Throwable th = th2;
            }
            cursorQuery.close();
            table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(UserPenetration.class, "PENETFICHEID=?", new String[]{String.valueOf(penetrationConvert.getId())});
            arrayList = new ArrayList();
            try {
                cursorQuery = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.net_qry_get_penetration_line_edit), StringUtils.convertIntToString(R.attr.id));
                for (r7 = 0; r7 < table.size(); r7++) {
                    PenetrationLine penetrationLineConvert = ((UserPenetration) table.get(r7)).convert();
                    if (cursorQuery.moveToPosition(r7)) {
                        penetrationLineConvert.setProductName(cursorQuery.getString(Integer.parseInt("PRODUCTNAME")));
                        penetrationLineConvert.setProductCode(cursorQuery.getString(Integer.parseInt("PRODUCTCODE")));
                        penetrationLineConvert.setUnit(cursorQuery.getString(Integer.parseInt("PUNIT")));
                    }
                    arrayList.add(penetrationLineConvert);
                }
                penetrationConvert.setPenetrations(arrayList);
                arrayList2.add(penetrationConvert);
                cursorQuery.close();
            } catch (Throwable th3) {
                cursorQuery.close();
                throw th3;
            }
        }
        return arrayList2;
    }
    public static ObservableSource lambdagetSalesOrderOne10(int r0) throws Exception {
        return Observable.just(Integer.valueOf(r0));
    }
    public void getSalesOrderOne(final int r3, final ResponseListener<Sales> responseListener) {
        Observable observableObserveOn = Observable.defer(new Callable() {
            public Object call() {
                try {
                    return NetsisSqlManager.lambdagetSalesOrderOne10(r3);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                try {
                    return NetsisSqlManager.lambdagetSalesOrderOne11(r3, (Integer) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).filter(new Predicate() {
            public boolean test(Object obj) {
                try {
                    return NetsisSqlManager.lambdagetSalesOrderOne12((List) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                try {
                    return lambdagetSalesOrderOne13(r3, (List) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).defaultIfEmpty(new Sales()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observableObserveOn.subscribe(new NetsisSqlManagerExternalSyntheticLambda4(), new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisSqlManager.lambdagetSalesOrderOne14(responseListener, (Throwable) obj);
            }

            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        });
    }
    private static List lambdagetSalesOrderOne11(int r2, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Order.class, "LOGICALREF=?", new String[]{String.valueOf(r2)});
    }
    private static boolean lambdagetSalesOrderOne12(List list) throws Exception {
        return list != null && list.size() == 1;
    }
    private Sales lambdagetSalesOrderOne13(int r14, List list) throws Exception {
        String stringResource;
        String stringResource2;
        String str;
        Sales sales = new Sales();
        Sales salesConvertSalesFicheToSales = ((Order) list.get(0)).convertSalesFicheToSales();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.net_qry_get_sales_order_edit), StringUtils.convertIntToString(r14));
                if (cursorQuery != null && cursorQuery.getCount() > 0 && cursorQuery.moveToFirst()) {
                    FicheStringProp.setDefinition(cursorQuery.getString(Integer.parseInt("AMBAR")));
                    FicheStringProp.setDefinition(cursorQuery.getString(Integer.parseInt("ISYERI")));
                    FicheStringProp.setDefinition(cursorQuery.getString(Integer.parseInt("FABRIKA")));
                    FicheStringProp.setDefinition(cursorQuery.getString(Integer.parseInt("BOLUM")));
                    FicheStringProp.setDefinition(cursorQuery.getString(Integer.parseInt("ODEMEPLAN")));
                    salesConvertSalesFicheToSales.getTradeGroup().setLogicalRef(cursorQuery.getInt(Integer.parseInt("TREF")));
                    salesConvertSalesFicheToSales.setNotUseGattribKdv(CalculateUtils.isGattribNotUseKdv(cursorQuery.getInt(Integer.parseInt("GATTRIB"))));
                    salesConvertSalesFicheToSales.getSpeCode().setLogicalRef(cursorQuery.getInt(Integer.parseInt("SREF")));
                    salesConvertSalesFicheToSales.getWarrantyCode().setLogicalRef(cursorQuery.getInt(Integer.parseInt("WREF")));
                    FicheStringProp.setDefinition(cursorQuery.getString(Integer.parseInt("TITLE")));
                    FicheStringProp.setDefinition(getShipAddressDefinitionWithCode(salesConvertSalesFicheToSales.getShipAddress().getCode()));
                    FicheStringProp.setDefinition(cursorQuery.getString(Integer.parseInt("SHPCODE")));
                    salesConvertSalesFicheToSales.getShipAgent().setLogicalRef(cursorQuery.getInt(Integer.parseInt("SHPREF")));
                    int r13 = 0;
                    while (r13 < salesConvertSalesFicheToSales.getDiscountLength()) {
                        FicheDiscountRefProp discountCard = salesConvertSalesFicheToSales.getDiscountCard(r13);
                        int r10 = r13 + 1;
                        String sb = "D" +
                                r10;
                        FicheStringProp.setDefinition(cursorQuery.getString(Integer.parseInt(sb)));
                        salesConvertSalesFicheToSales.getDiscountCard(r13).setLogicalRef(cursorQuery.getInt(Integer.parseInt("C" + r10)));
                        r13 = r10;
                    }
                    FicheStringProp.setDefinition(cursorQuery.getString(Integer.parseInt("PROJE")));
                }
            } catch (Exception e2) {
                Log.e("AA", "getSalesOrderOne: ", e2);
            }
            cursorQuery.close();
            List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(OrderDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(Integer.parseInt(String.valueOf(r14)))});
            if (table != null && table.size() > 0) {
                for (Object t : table) {
                    int lineType = ((OrderDetail) t).lineType;
                    if (lineType == LineType.SERVICE.value) {
                        stringResource = ContextUtils.getStringResource(R.string.table_service);
                        stringResource2 = ContextUtils.getStringResource(R.string.table_service_units);
                        str = "0 AS ISVARYANT";
                    } else {
                        stringResource = ContextUtils.getStringResource(R.string.table_item);
                        stringResource2 = ContextUtils.getStringResource(R.string.table_item_units);
                        str = "I.ISVARYANT AS ISVARYANT";
                    }
                    Cursor cursorQuery2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.net_qry_get_sales_order_detail_edit, stringResource, stringResource2, str), StringUtils.convertIntToString(t.logicalRef));
                    if (!t.priceCode.isEmpty()) {
                        t.priceRef = StringUtils.convertStringToInt(t.priceCode);
                    }
                    SalesDetail salesDetailConvertSalesFicheDetailToSalesDetail = t.convertSalesFicheDetailToSalesDetail();
                    if (cursorQuery2 != null) {
                        try {
                            if (cursorQuery2.getCount() > 0 && cursorQuery2.moveToFirst()) {
                                salesDetailConvertSalesFicheDetailToSalesDetail.setName(cursorQuery2.getString(cursorQuery2.getColumnIndex("ITEMNAME")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setCode(cursorQuery2.getString(cursorQuery2.getColumnIndex("ITEMCODE")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setItemRef(cursorQuery2.getInt(cursorQuery2.getColumnIndex("ITEMREF")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setCardType(cursorQuery2.getInt(cursorQuery2.getColumnIndex("CARDTYPE")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setTrackType(cursorQuery2.getInt(cursorQuery2.getColumnIndex("TRACKTYPE")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.getUnit().setCode(cursorQuery2.getString(cursorQuery2.getColumnIndex("UUCODE")));
                                FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("UCODE")));
                                FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("ODEMEPLAN")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.getSpeCode().setLogicalRef(cursorQuery2.getInt(cursorQuery2.getColumnIndex("SREF")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setBarcodeCount(cursorQuery2.getInt(cursorQuery2.getColumnIndex("BARCODECOUNT")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setNetWeight(cursorQuery2.getDouble(cursorQuery2.getColumnIndex("WEIGHT")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setNetVolume(cursorQuery2.getDouble(cursorQuery2.getColumnIndex("VOLUME")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setWidth(cursorQuery2.getDouble(cursorQuery2.getColumnIndex("WIDTH")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setLength(cursorQuery2.getDouble(cursorQuery2.getColumnIndex("LENGTH")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setHeight(cursorQuery2.getDouble(cursorQuery2.getColumnIndex("HEIGHT")));
                                int r8 = 0;
                                while (r8 < salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountLength()) {
                                    FicheDiscountRefProp discountCard2 = salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountCard(r8);
                                    int r11 = r8 + 1;
                                    String sb2 = "D" +
                                            r11;
                                    FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex(sb2)));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountCard(r8).setLogicalRef(cursorQuery2.getInt(cursorQuery2.getColumnIndex("C" + r11)));
                                    r8 = r11;
                                }
                                FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("VNAME")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setHasVariant(StringUtils.convertIntToBoolean(cursorQuery2.getInt(cursorQuery2.getColumnIndex("ISVARYANT"))));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setSearchBarcodes(ErpCreator.getInstance().getmBaseErp().getFicheDetailBarcodesBySalesDetailRef(salesDetailConvertSalesFicheDetailToSalesDetail.getLogicalRef(), DetailBarcodeFicheType.Order.ordinal()));
                            }
                        } catch (Exception e3) {
                            Log.e("AA", "getSalesOrderOne: ", e3);
                        }
                    }
                    if (cursorQuery2 != null && !cursorQuery2.isClosed()) {
                        cursorQuery2.close();
                    }
                    salesDetailConvertSalesFicheDetailToSalesDetail.setDivUnit(true);
                    salesConvertSalesFicheToSales.getMSalesDetailList().add(salesDetailConvertSalesFicheDetailToSalesDetail);
                }
            }
            return salesConvertSalesFicheToSales;
        } finally {
            cursorQuery.close();
        }
    }
    public static void lambdagetSalesOrderOne14(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }
    public static ObservableSource lambdagetSalesInvoiceExam15(int r0) throws Exception {
        return Observable.just(Integer.valueOf(Integer.parseInt(String.valueOf(r0))));
    }
    public void getSalesInvoiceExam(final int r3, final ResponseListener<Sales> responseListener) {
        Observable<T> observableObserveOn = Observable.defer(new Callable() {
            public Object call() {
                try {
                    try {
                        return NetsisSqlManager.lambdagetSalesInvoiceExam15(Integer.parseInt(String.valueOf(r3)));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid input for sales invoice exam ID: " + r3, e);
                }
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                try {
                    return NetsisSqlManager.lambdagetSalesInvoiceExam16(r3, (Integer) obj);
                } catch (ClassCastException e) {
                    throw new RuntimeException("Invalid type for sales invoice exam result for ID: " + r3, e);
                } catch (Exception e) {
                    throw new RuntimeException("Error processing sales invoice exam result for ID: "  , e);
                }
            }
        }).filter(new Predicate() {
            public boolean test(Object obj) {
                try {
                    return NetsisSqlManager.lambdagetSalesInvoiceExam17((List) obj);
                } catch (ClassCastException e) {
                    throw new RuntimeException("Invalid type for sales invoice exam list for ID: "   , e);
                } catch (Exception e) {
                    throw new RuntimeException("Error processing sales invoice exam list for ID: "  , e);
                }
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                try {
                    return  lambdagetSalesInvoiceExam18(r3, (List) obj);
                } catch (ClassCastException e) {
                    throw new RuntimeException("Invalid type for sales invoice exam result list for ID: " + r3, e);
                } catch (Exception e) {
                    throw new RuntimeException("Error processing sales invoice exam result list for ID: "  , e);
                }
            }
        }).defaultIfEmpty(new Sales()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observableObserveOn.subscribe(new NetsisSqlManagerExternalSyntheticLambda4(), new Consumer() {
            public void accept(Object obj) throws Exception {
                try {
                    lambdagetSalesInvoiceExam19(responseListener, (Throwable) obj);
                } catch (ClassCastException e) {
                    throw new RuntimeException("Invalid type for sales invoice exam result for ID: " + r3, e);
                } catch (Exception e) {
                    throw new RuntimeException("Error processing sales invoice exam result for ID: " , e);
                }
            }
            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        });
    }
    public static List lambdagetSalesInvoiceExam16(int r2, Integer num) throws Exception {
        try {
            return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Invoice.class, "LOGICALREF=?", new String[]{String.valueOf(r2)});
        } catch (ClassCastException e) {
            throw new RuntimeException("Invalid type for sales invoice exam result for ID: " + r2, e);
        } catch (Exception e) {
            throw new RuntimeException("Error processing sales invoice exam result for ID: "  , e);
        }
    }
    public static boolean lambdagetSalesInvoiceExam17(List list) {
        try {
            return list != null && list.size() == 1;
        } catch (ClassCastException e) {
            throw new RuntimeException("Invalid type for sales invoice exam result list for ID: "  , e);
        } catch (Exception e) {
            throw new RuntimeException("Error processing sales invoice exam result list for ID: "  , e);
        }
    }
    public Sales lambdagetSalesInvoiceExam18(int r13, List list) throws Exception {
        String stringResource;
        String stringResource2;
        String str;
        new Sales();
        Invoice invoice = (Invoice) list.get(0);
        Sales salesConvertSalesFicheToSales = invoice.convertSalesFicheToSales();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.net_qry_get_sales_invoice_edit), StringUtils.convertIntToString(r13));
                if (cursorQuery != null && cursorQuery.getCount() > 0 && cursorQuery.moveToFirst()) {
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("AMBAR")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("IADEAMBAR")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("ISYERI")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("FABRIKA")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("BOLUM")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("ODEMEPLAN")));
                    salesConvertSalesFicheToSales.getTradeGroup().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("TREF")));
                    salesConvertSalesFicheToSales.setNotUseGattribKdv(CalculateUtils.isGattribNotUseKdv(cursorQuery.getInt(cursorQuery.getColumnIndex("GATTRIB"))));
                    salesConvertSalesFicheToSales.getSpeCode().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("SREF")));
                    salesConvertSalesFicheToSales.getWarrantyCode().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("WREF")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("TITLE")));
                    FicheStringProp.setDefinition(getShipAddressDefinitionWithCode(salesConvertSalesFicheToSales.getShipAddress().getCode()));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("SHPCODE")));
                    salesConvertSalesFicheToSales.getShipAgent().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("SHPREF")));
                    int r8 = 0;
                    while (r8 < salesConvertSalesFicheToSales.getDiscountLength()) {
                        FicheDiscountRefProp discountCard = salesConvertSalesFicheToSales.getDiscountCard(r8);
                        int r11 = r8 + 1;
                        String sb = "D" +
                                r11;
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex(sb)));
                        salesConvertSalesFicheToSales.getDiscountCard(r8).setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("C" + r11)));
                        r8 = r11;
                    }
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("PROJE")));
                    salesConvertSalesFicheToSales.getCabin().setCode(cursorQuery.getString(cursorQuery.getColumnIndex("CBNCODE")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("CBNNAME")));
                    if (salesConvertSalesFicheToSales.getmSalesType() == SalesType.DISPATCH) {
                        mapCursorToEDespatch(invoice, cursorQuery, salesConvertSalesFicheToSales);
                    }
                }
            } catch (Exception e2) {
                Log.e("AA", "getSalesOrderOne: ", e2);
            }
            cursorQuery.close();
            List<T> table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(InvoiceDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(r13)});
            if (table != null && table.size() > 0) {
                for (T t : table) {
                    if (t.lineType == LineType.SERVICE.value) {
                        stringResource = ContextUtils.getStringResource(R.string.table_service);
                        stringResource2 = ContextUtils.getStringResource(R.string.table_service_units);
                        str = "0 AS ISVARYANT";
                    } else {
                        stringResource = ContextUtils.getStringResource(R.string.table_item);
                        stringResource2 = ContextUtils.getStringResource(R.string.table_item_units);
                        str = "I.ISVARYANT AS ISVARYANT";
                    }
                    Cursor cursorQuery2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.net_qry_get_sales_invoice_detail_edit, stringResource, stringResource2, str), StringUtils.convertIntToString(t.logicalRef));
                    if (!t.priceCode.isEmpty()) {
                        t.priceRef = StringUtils.convertStringToInt(t.priceCode);
                    }
                    SalesDetail salesDetailConvertSalesFicheDetailToSalesDetail = t.convertSalesFicheDetailToSalesDetail();
                    if (cursorQuery2 != null) {
                        try {
                            if (cursorQuery2.getCount() > 0 && cursorQuery2.moveToFirst()) {
                                salesDetailConvertSalesFicheDetailToSalesDetail.setName(cursorQuery2.getString(cursorQuery2.getColumnIndex("ITEMNAME")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setCode(cursorQuery2.getString(cursorQuery2.getColumnIndex("ITEMCODE")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setItemRef(cursorQuery2.getInt(cursorQuery2.getColumnIndex("ITEMREF")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setCardType(cursorQuery2.getInt(cursorQuery2.getColumnIndex("CARDTYPE")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setTrackType(cursorQuery2.getInt(cursorQuery2.getColumnIndex("TRACKTYPE")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.getUnit().setCode(cursorQuery2.getString(cursorQuery2.getColumnIndex("UUCODE")));
                                FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("UCODE")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.getSecondUnit().setCode(cursorQuery2.getString(cursorQuery2.getColumnIndex("UUSCODE")));
                                FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("USCODE")));
                                FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("ODEMEPLAN")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setBarcodeCount(cursorQuery2.getInt(cursorQuery2.getColumnIndex("BARCODECOUNT")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.getSpeCode().setLogicalRef(cursorQuery2.getInt(cursorQuery2.getColumnIndex("SREF")));
                                int r7 = 0;
                                while (r7 < salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountLength()) {
                                    FicheDiscountRefProp discountCard2 = salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountCard(r7);
                                    int r10 = r7 + 1;
                                    String sb2 = "D" +
                                            r10;
                                    FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex(sb2)));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountCard(r7).setLogicalRef(cursorQuery2.getInt(cursorQuery2.getColumnIndex("C" + r10)));
                                    r7 = r10;
                                }
                                FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("VNAME")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setHasVariant(StringUtils.convertIntToBoolean(cursorQuery2.getInt(cursorQuery2.getColumnIndex("ISVARYANT"))));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setSearchBarcodes(ErpCreator.getInstance().getmBaseErp().getFicheDetailBarcodesBySalesDetailRef(salesDetailConvertSalesFicheDetailToSalesDetail.getLogicalRef(), DetailBarcodeFicheType.InvoiceDispatch.ordinal()));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setNetWeight(cursorQuery2.getDouble(cursorQuery2.getColumnIndex("WEIGHT")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setNetVolume(cursorQuery2.getDouble(cursorQuery2.getColumnIndex("VOLUME")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setWidth(cursorQuery2.getDouble(cursorQuery2.getColumnIndex("WIDTH")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setLength(cursorQuery2.getDouble(cursorQuery2.getColumnIndex("LENGTH")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setHeight(cursorQuery2.getDouble(cursorQuery2.getColumnIndex("HEIGHT")));
                                Cursor cursorQuery3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_sales_serilot_detail_edit), StringUtils.convertIntToString(salesDetailConvertSalesFicheDetailToSalesDetail.getLogicalRef()), "0");
                                if (cursorQuery3 != null) {
                                    try {
                                        try {
                                            if (cursorQuery3.getCount() > 0) {
                                                for (int r82 = 0; r82 < cursorQuery3.getCount(); r82++) {
                                                    if (cursorQuery3.moveToPosition(r82)) {
                                                        Serilot serilot = new Serilot();
                                                        serilot.logicalRef = cursorQuery3.getInt(cursorQuery3.getColumnIndex("LOGICALREF"));
                                                        serilot.detailRef = cursorQuery3.getInt(cursorQuery3.getColumnIndex("DETAILREF"));
                                                        serilot.unit = cursorQuery3.getString(cursorQuery3.getColumnIndex("UNIT"));
                                                        serilot.code = cursorQuery3.getString(cursorQuery3.getColumnIndex("CODE"));
                                                        serilot.amount = cursorQuery3.getDouble(cursorQuery3.getColumnIndex("AMOUNT"));
                                                        serilot.expDate = cursorQuery3.getString(cursorQuery3.getColumnIndex("EXPDATE"));
                                                        salesDetailConvertSalesFicheDetailToSalesDetail.getSalesSerialLots().add(serilot);
                                                    }
                                                }
                                            }
                                        } catch (Exception e3) {
                                            Log.e(TAG, "getSalesInvoiceExam:  ", e3);
                                        }
                                    } catch (Throwable th) {
                                        cursorQuery3.close();
                                        throw th;
                                    }
                                }
                                cursorQuery3.close();
                            }
                        } catch (Exception e4) {
                            Log.e("AA", "getSalesInvoiceExam: ", e4);
                        }
                    }
                    if (cursorQuery2 != null && !cursorQuery2.isClosed()) {
                        cursorQuery2.close();
                    }
                    salesDetailConvertSalesFicheDetailToSalesDetail.setDivUnit(true);
                    salesDetailConvertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(salesDetailConvertSalesFicheDetailToSalesDetail.getSalesSerialLots(), salesDetailConvertSalesFicheDetailToSalesDetail.getTrackType()));
                    salesConvertSalesFicheToSales.getMSalesDetailList().add(salesDetailConvertSalesFicheDetailToSalesDetail);
                }
            }
            return salesConvertSalesFicheToSales;
        } finally {
            cursorQuery.close();
        }
    }

    
    public static void lambdagetSalesInvoiceExam19(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    public void getCashCreditFiche(int r2, int r3, ResponseListener<CashCreditFiche> responseListener) {
        getCashCreditFiche(r2, r3, responseListener, false);
    }

    public void getCashCreditFiche(final int r3, final int r4, final ResponseListener<CashCreditFiche> responseListener, boolean z) {
        final String str = z ? "INVOICEREF" : "LOGICALREF";
        Observable<T> observableObserveOn = Observable.defer(new Callable() {
            public Object call() {
                return NetsisSqlManager.lambdagetCashCreditFiche20(r3);
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                return NetsisSqlManager.lambdagetCashCreditFiche21(str, r3, r4, (Integer) obj);
            }
        }).filter(new Predicate() {
            public boolean test(Object obj) {
                return NetsisSqlManager.lambdagetCashCreditFiche22((List) obj);
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                return NetsisSqlManager.lambdagetCashCreditFiche23(r4, r3, (List) obj);
            }
        }).defaultIfEmpty(new CashCreditFiche()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observableObserveOn.subscribe(new NetsisSqlManagerExternalSyntheticLambda10(responseListener), new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisSqlManager.lambdagetCashCreditFiche24(responseListener, (Throwable) obj);
            }
        });
    }

    
    public static ObservableSource lambdagetCashCreditFiche20(int r0) throws Exception {
        return Observable.just(Integer.valueOf(r0));
    }

    
    public static List lambdagetCashCreditFiche21(String str, int r2, int r3, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(CashCredit.class, str + "=? AND FTYPE=?", new String[]{String.valueOf(r2), String.valueOf(r3)});
    }

    
    public static boolean lambdagetCashCreditFiche22(List list) throws Exception {
        return list != null && list.size() == 1;
    }

    
    public static CashCreditFiche lambdagetCashCreditFiche23(int r6, int r7, List list) throws Exception {
        new CashCreditFiche();
        CashCreditFiche cashCreditFicheConvertFicheToCashCreditFiche = ((CashCredit) list.get(0)).convertFicheToCashCreditFiche();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.net_qry_get_cashcredit_fiche_edit), StringUtils.convertIntToString(cashCreditFicheConvertFicheToCashCreditFiche.getLogicalRef()), StringUtils.convertIntToString(r6));
                if (cursorQuery != null && cursorQuery.getCount() > 0 && cursorQuery.moveToFirst()) {
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("ISYERI")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("BOLUM")));
                    cashCreditFicheConvertFicheToCashCreditFiche.getTradinggrp().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("TREF")));
                    cashCreditFicheConvertFicheToCashCreditFiche.getSpecode().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("SREF")));
                    cashCreditFicheConvertFicheToCashCreditFiche.getCyphcode().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("WREF")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("PROJE")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("BANKDEF")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("BANKACCDEF")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("AGGREEMENT")));
                }
            } finally {
                cursorQuery.close();
            }
        } catch (Exception e2) {
            Log.e("AA", "getSalesOrderOne: ", e2);
        }
        for (T t : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(CashCreditDetail.class, "CASHCREDITID=?", new String[]{String.valueOf(r7)})) {
            CashCreditFicheDetail cashCreditFicheDetailConvertFicheDetailToCashCreditFicheDetail = t.convertFicheDetailToCashCreditFicheDetail();
            Cursor cursorQuery2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.net_qry_get_cashcreditdetail_fiche_edit), StringUtils.convertIntToString(t.logicalRef));
            if (cursorQuery2 != null) {
                try {
                    try {
                        if (cursorQuery2.getCount() > 0 && cursorQuery2.moveToFirst()) {
                            FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("ODEMEPLAN")));
                        }
                    } catch (Exception e3) {
                        Log.e("AA", "getSalesOrderOne: ", e3);
                    }
                } finally {
                    cursorQuery2.close();
                }
            }
            cashCreditFicheConvertFicheToCashCreditFiche.addNewDetail(cashCreditFicheDetailConvertFicheDetailToCashCreditFicheDetail);
        }
        return cashCreditFicheConvertFicheToCashCreditFiche;
    }

    
    public static void lambdagetCashCreditFiche24(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getCaseFiche(int r2, ResponseListener<CaseFiche> responseListener) {
        getCaseFiche(r2, responseListener, false);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getCaseFiche(final int r3, final ResponseListener<CaseFiche> responseListener, boolean z) {
        final String str = z ? "INVOICEREF" : "LOGICALREF";
        Observable<T> observableObserveOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda35
            @Override // java.util.concurrent.Callable
            public Object call() {
                return NetsisSqlManager.lambdagetCaseFiche25(r3);
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda36
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return NetsisSqlManager.lambdagetCaseFiche26(str, r3, (Integer) obj);
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda37
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                return NetsisSqlManager.lambdagetCaseFiche27((List) obj);
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda38
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return NetsisSqlManager.lambdagetCaseFiche28((List) obj);
            }
        }).defaultIfEmpty(new CaseFiche()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observableObserveOn.subscribe(new NetsisSqlManagerExternalSyntheticLambda39(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda40
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) throws Exception {
                NetsisSqlManager.lambdagetCaseFiche29(responseListener, (Throwable) obj);
            }
        });
    }

    
    public static ObservableSource lambdagetCaseFiche25(int r0) throws Exception {
        return Observable.just(Integer.valueOf(r0));
    }

    
    public static List lambdagetCaseFiche26(String str, int r2, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(CaseCash.class, str + "=?", new String[]{String.valueOf(r2)});
    }

    
    public static boolean lambdagetCaseFiche27(List list) throws Exception {
        return list != null && list.size() == 1;
    }

    
    public static CaseFiche lambdagetCaseFiche28(List list) throws Exception {
        new CaseFiche();
        CaseFiche caseFicheConvertFicheToCaseFiche = ((CaseCash) list.get(0)).convertFicheToCaseFiche();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.net_qry_get_case_fiche_edit), StringUtils.convertIntToString(caseFicheConvertFicheToCaseFiche.getLogicalRef()));
                if (cursorQuery != null && cursorQuery.getCount() > 0 && cursorQuery.moveToFirst()) {
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("ISYERI")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("BOLUM")));
                    caseFicheConvertFicheToCaseFiche.getTradinggrp().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("TREF")));
                    caseFicheConvertFicheToCaseFiche.getSpecode().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("SREF")));
                    caseFicheConvertFicheToCaseFiche.getCyphcode().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("WREF")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("PROJE")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("KASA")));
                }
            } catch (Exception e2) {
                Log.e("AA", "getSalesOrderOne: ", e2);
            }
            return caseFicheConvertFicheToCaseFiche;
        } finally {
            cursorQuery.close();
        }
    }

    
    public static void lambdagetCaseFiche29(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getChequeDeedFiche(int r2, int r3, ResponseListener<ChequeDeedFiche> responseListener) {
        getChequeDeedFiche(r2, r3, responseListener, false);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getChequeDeedFiche(final int r3, final int r4, final ResponseListener<ChequeDeedFiche> responseListener, boolean z) {
        final String str = z ? "INVOICEREF" : "LOGICALREF";
        Observable<T> observableObserveOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda24
            @Override // java.util.concurrent.Callable
            public Object call() {
                return NetsisSqlManager.lambdagetChequeDeedFiche30(r3);
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda25
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return NetsisSqlManager.lambdagetChequeDeedFiche31(str, r3, r4, (Integer) obj);
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda26
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                return NetsisSqlManager.lambdagetChequeDeedFiche32((List) obj);
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda27
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return NetsisSqlManager.lambdagetChequeDeedFiche33(r4, (List) obj);
            }
        }).defaultIfEmpty(new ChequeDeedFiche()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observableObserveOn.subscribe(new NetsisSqlManagerExternalSyntheticLambda28(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda29
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) throws Exception {
                NetsisSqlManager.lambdagetChequeDeedFiche34(responseListener, (Throwable) obj);
            }
        });
    }

    
    public static ObservableSource lambdagetChequeDeedFiche30(int r0) throws Exception {
        return Observable.just(Integer.valueOf(r0));
    }

    
    public static List lambdagetChequeDeedFiche31(String str, int r2, int r3, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Chequedeed.class, str + "=? AND FTYPE=?", new String[]{String.valueOf(r2), String.valueOf(r3)});
    }

    
    public static boolean lambdagetChequeDeedFiche32(List list) throws Exception {
        return list != null && list.size() == 1;
    }

    
    public static ChequeDeedFiche lambdagetChequeDeedFiche33(int r4, List list) throws Exception {
        new ChequeDeedFiche();
        ChequeDeedFiche chequeDeedFicheConvertFicheToCashCreditFiche = ((Chequedeed) list.get(0)).convertFicheToCashCreditFiche();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.net_qry_get_chequedeed_fiche_edit), StringUtils.convertIntToString(chequeDeedFicheConvertFicheToCashCreditFiche.getLogicalRef()), StringUtils.convertIntToString(r4));
                if (cursorQuery != null && cursorQuery.getCount() > 0 && cursorQuery.moveToFirst()) {
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("ISYERI")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("BOLUM")));
                    chequeDeedFicheConvertFicheToCashCreditFiche.getTradinggrp().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("TREF")));
                    chequeDeedFicheConvertFicheToCashCreditFiche.getSpecode().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("SREF")));
                    chequeDeedFicheConvertFicheToCashCreditFiche.getCyphcode().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("WREF")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("PROJE")));
                }
            } catch (Exception e2) {
                Log.e("AA", "getSalesOrderOne: ", e2);
            }
            Iterator<T> it = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ChequedeedDetail.class, "CHEQUEDEEDID=?", new String[]{String.valueOf(chequeDeedFicheConvertFicheToCashCreditFiche.getLogicalRef())}).iterator();
            while (it.hasNext()) {
                chequeDeedFicheConvertFicheToCashCreditFiche.addNewDetail(((ChequedeedDetail) it.next()).convertFicheDetailToCashCreditFicheDetail());
            }
            return chequeDeedFicheConvertFicheToCashCreditFiche;
        } finally {
            cursorQuery.close();
        }
    }

    
    public static void lambdagetChequeDeedFiche34(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<Sales> mapCursorToSalesOrderTest(int r17, int r18) {
        String stringResource;
        String stringResource2;
        String str;
        int r6 = 0;
        List<T> table = r17 != -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Order.class, "ORDERTYPE=? AND (ISTRANSFER=? OR ISEDIT=1) AND LOGICALREF=? ", new String[]{String.valueOf(r18), String.valueOf(0), String.valueOf(r17)}) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Order.class, "ORDERTYPE=? AND (ISTRANSFER=? OR ISEDIT=1) ", new String[]{String.valueOf(r18), String.valueOf(0)});
        ArrayList arrayList = new ArrayList();
        for (T t : table) {
            Sales salesConvertSalesFicheToSales = t.convertSalesFicheToSales();
            Cursor cursorQuery = null;
            try {
                try {
                    cursorQuery = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.net_qry_get_sales_order_edit), StringUtils.convertIntToString(t.logicalRef));
                } catch (Exception unused) {
                }
                if (cursorQuery != null && cursorQuery.getCount() > 0 && cursorQuery.moveToFirst()) {
                    salesConvertSalesFicheToSales.setClCode(cursorQuery.getString(cursorQuery.getColumnIndex("CCCODE")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("AMBAR")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("ISYERI")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("FABRIKA")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("BOLUM")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("ODEMEPLAN")));
                    salesConvertSalesFicheToSales.getTradeGroup().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("TREF")));
                    salesConvertSalesFicheToSales.getSpeCode().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("SREF")));
                    salesConvertSalesFicheToSales.getWarrantyCode().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("WREF")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("TITLE")));
                    try {
                        FicheStringProp.setDefinition(getShipAddressDefinition(salesConvertSalesFicheToSales.getShipAddress().getLogicalRef()));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("SHPCODE")));
                        salesConvertSalesFicheToSales.getShipAgent().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("SHPREF")));
                        int r10 = r6;
                        while (r10 < salesConvertSalesFicheToSales.getDiscountLength()) {
                            FicheDiscountRefProp discountCard = salesConvertSalesFicheToSales.getDiscountCard(r10);
                            int r14 = r10 + 1;
                            String sb = "D" +
                                    r14;
                            FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex(sb)));
                            salesConvertSalesFicheToSales.getDiscountCard(r10).setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("C" + r14)));
                            r10 = r14;
                        }
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("PROJE")));
                    } catch (Exception unused2) {
                    }
                }
                cursorQuery.close();
                List<T> table2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(OrderDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(t.logicalRef)});
                if (table2 != null && table2.size() > 0) {
                    for (T t2 : table2) {
                        if (t2.lineType == LineType.SERVICE.value) {
                            stringResource = ContextUtils.getStringResource(R.string.table_service);
                            stringResource2 = ContextUtils.getStringResource(R.string.table_service_units);
                            str = "0 AS ISVARYANT";
                        } else {
                            stringResource = ContextUtils.getStringResource(R.string.table_item);
                            stringResource2 = ContextUtils.getStringResource(R.string.table_item_units);
                            str = "I.ISVARYANT AS ISVARYANT";
                        }
                        Cursor cursorQuery2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.net_qry_get_sales_order_detail_edit, stringResource, stringResource2, str), StringUtils.convertIntToString(t2.logicalRef));
                        SalesDetail salesDetailConvertSalesFicheDetailToSalesDetail = t2.convertSalesFicheDetailToSalesDetail();
                        salesDetailConvertSalesFicheDetailToSalesDetail.setSearchBarcodes(ErpCreator.getInstance().getmBaseErp().getFicheDetailBarcodesBySalesDetailRef(salesDetailConvertSalesFicheDetailToSalesDetail.getLogicalRef(), DetailBarcodeFicheType.Order.ordinal()));
                        if (cursorQuery2 != null) {
                            try {
                                if (cursorQuery2.getCount() > 0 && cursorQuery2.moveToFirst()) {
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setCode(cursorQuery2.getString(cursorQuery2.getColumnIndex("ITEMCODE")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setName(cursorQuery2.getString(cursorQuery2.getColumnIndex("ITEMNAME")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setCardType(cursorQuery2.getInt(cursorQuery2.getColumnIndex("CARDTYPE")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setTrackType(cursorQuery2.getInt(cursorQuery2.getColumnIndex("TRACKTYPE")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.getUnit().setCode(cursorQuery2.getString(cursorQuery2.getColumnIndex("UUCODE")));
                                    FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("UCODE")));
                                    FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("ODEMEPLAN")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.getSpeCode().setLogicalRef(cursorQuery2.getInt(cursorQuery2.getColumnIndex("SREF")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setBarcodeCount(cursorQuery2.getInt(cursorQuery2.getColumnIndex("BARCODECOUNT")));
                                    int r102 = 0;
                                    while (r102 < salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountLength()) {
                                        FicheDiscountRefProp discountCard2 = salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountCard(r102);
                                        int r142 = r102 + 1;
                                        String sb2 = "D" +
                                                r142;
                                        FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex(sb2)));
                                        salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountCard(r102).setLogicalRef(cursorQuery2.getInt(cursorQuery2.getColumnIndex("C" + r142)));
                                        r102 = r142;
                                    }
                                    FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("VNAME")));
                                }
                            } catch (Exception unused3) {
                            }
                        }
                        if (cursorQuery2 != null && !cursorQuery2.isClosed()) {
                            cursorQuery2.close();
                        }
                        salesConvertSalesFicheToSales.getMSalesDetailList().add(salesDetailConvertSalesFicheDetailToSalesDetail);
                    }
                }
                arrayList.add(salesConvertSalesFicheToSales);
                r6 = 0;
            } finally {
                cursorQuery.close();
            }
        }
        return arrayList;
    }

    private void mapCursorToEDespatch(Invoice invoice, Cursor cursor, Sales sales) {
        EDispatchAdditionalInfo eDispatchAdditionalInfo = new EDispatchAdditionalInfo();
        eDispatchAdditionalInfo.salesFicheId = invoice.logicalRef;
        eDispatchAdditionalInfo.firstDriverPlate = cursor.getString(cursor.getColumnIndex("PLATE"));
        eDispatchAdditionalInfo.carrierTaxNr = cursor.getString(cursor.getColumnIndex("CARRIERTAXNR"));
        eDispatchAdditionalInfo.carrierName = cursor.getString(cursor.getColumnIndex("CARRIERNAME"));
        eDispatchAdditionalInfo.carrierCounty = cursor.getString(cursor.getColumnIndex("CARRIERCOUNTY"));
        eDispatchAdditionalInfo.carrierCity = cursor.getString(cursor.getColumnIndex("CARRIERCITY"));
        eDispatchAdditionalInfo.carrierCountry = cursor.getString(cursor.getColumnIndex("CARRIERCOUNTRY"));
        eDispatchAdditionalInfo.carrierPostCode = cursor.getString(cursor.getColumnIndex("CARRIERPOSTCODE"));
        eDispatchAdditionalInfo.firstDriverName = cursor.getString(cursor.getColumnIndex("FIRSTDRIVERNAME"));
        eDispatchAdditionalInfo.firstDriverLastName = cursor.getString(cursor.getColumnIndex("FIRSTDRIVERLASTNAME"));
        eDispatchAdditionalInfo.firstDriverDescription = cursor.getString(cursor.getColumnIndex("FIRSTDRIVERDESCRIPTION"));
        eDispatchAdditionalInfo.firstDriverIdentityNr = cursor.getString(cursor.getColumnIndex("FIRSTDRIVERIDENTITYNR"));
        eDispatchAdditionalInfo.firstTrailerPlate = cursor.getString(cursor.getColumnIndex("FIRSTTRAILERPLATE"));
        eDispatchAdditionalInfo.salesType = sales.getMSalesType();
        sales.setEDispatchAdditionalInfo(eDispatchAdditionalInfo);
    }

    private void mapCursorToEDespatch(WhTransfer whTransfer, Cursor cursor, Sales sales) {
        EDispatchAdditionalInfo eDispatchAdditionalInfo = new EDispatchAdditionalInfo();
        eDispatchAdditionalInfo.salesFicheId = whTransfer.logicalRef;
        eDispatchAdditionalInfo.firstDriverPlate = cursor.getString(cursor.getColumnIndex("PLATE"));
        eDispatchAdditionalInfo.carrierTaxNr = cursor.getString(cursor.getColumnIndex("CARRIERTAXNR"));
        eDispatchAdditionalInfo.carrierName = cursor.getString(cursor.getColumnIndex("CARRIERNAME"));
        eDispatchAdditionalInfo.carrierCounty = cursor.getString(cursor.getColumnIndex("CARRIERCOUNTY"));
        eDispatchAdditionalInfo.carrierCity = cursor.getString(cursor.getColumnIndex("CARRIERCITY"));
        eDispatchAdditionalInfo.carrierCountry = cursor.getString(cursor.getColumnIndex("CARRIERCOUNTRY"));
        eDispatchAdditionalInfo.carrierPostCode = cursor.getString(cursor.getColumnIndex("CARRIERPOSTCODE"));
        eDispatchAdditionalInfo.firstDriverName = cursor.getString(cursor.getColumnIndex("FIRSTDRIVERNAME"));
        eDispatchAdditionalInfo.firstDriverLastName = cursor.getString(cursor.getColumnIndex("FIRSTDRIVERLASTNAME"));
        eDispatchAdditionalInfo.firstDriverDescription = cursor.getString(cursor.getColumnIndex("FIRSTDRIVERDESCRIPTION"));
        eDispatchAdditionalInfo.firstDriverIdentityNr = cursor.getString(cursor.getColumnIndex("FIRSTDRIVERIDENTITYNR"));
        eDispatchAdditionalInfo.firstTrailerPlate = cursor.getString(cursor.getColumnIndex("FIRSTTRAILERPLATE"));
        eDispatchAdditionalInfo.salesType = sales.getMSalesType();
        sales.setEDispatchAdditionalInfo(eDispatchAdditionalInfo);
    }

    
    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<Sales> mapCursorToInvoice(SalesType salesType, int r18, int r19, int r20) {
        String stringResource;
        String stringResource2;
        String str;
        NetsisSqlManager<T> netsisSqlManager = this;
        int r8 = 0;
        List<T> table = r20 == -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Invoice.class, "FTYPE=? AND INVTYPE=? AND ISTRANSFER=? AND CLCODE IN (SELECT CODE FROM CLCARD WHERE ISTRANSFER = 1)", new String[]{String.valueOf(r18), String.valueOf(r19), String.valueOf(0)}) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Invoice.class, "FTYPE=? AND INVTYPE=? AND ISTRANSFER=? AND LOGICALREF=? AND CLCODE IN (SELECT CODE FROM CLCARD WHERE ISTRANSFER = 1)", new String[]{String.valueOf(r18), String.valueOf(r19), String.valueOf(0), String.valueOf(r20)});
        ArrayList arrayList = new ArrayList();
        for (T t : table) {
            Sales salesConvertSalesFicheToSales = t.convertSalesFicheToSales();
            Cursor cursorQuery = null;
            try {
                try {
                    cursorQuery = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.net_qry_get_sales_invoice_edit), StringUtils.convertIntToString(t.logicalRef));
                    if (cursorQuery != null && cursorQuery.getCount() > 0 && cursorQuery.moveToFirst()) {
                        salesConvertSalesFicheToSales.setClCode(cursorQuery.getString(cursorQuery.getColumnIndex("CCCODE")));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("AMBAR")));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("IADEAMBAR")));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("ISYERI")));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("FABRIKA")));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("BOLUM")));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("ODEMEPLAN")));
                        salesConvertSalesFicheToSales.getTradeGroup().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("TREF")));
                        salesConvertSalesFicheToSales.getSpeCode().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("SREF")));
                        salesConvertSalesFicheToSales.getWarrantyCode().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("WREF")));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("TITLE")));
                        FicheStringProp.setDefinition(netsisSqlManager.getShipAddressDefinition(salesConvertSalesFicheToSales.getShipAddress().getLogicalRef()));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("SHPCODE")));
                        salesConvertSalesFicheToSales.getShipAgent().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("SHPREF")));
                        int r0 = r8;
                        while (r0 < salesConvertSalesFicheToSales.getDiscountLength()) {
                            FicheDiscountRefProp discountCard = salesConvertSalesFicheToSales.getDiscountCard(r0);
                            int r15 = r0 + 1;
                            String sb = "D" +
                                    r15;
                            FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex(sb)));
                            salesConvertSalesFicheToSales.getDiscountCard(r0).setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("C" + r15)));
                            r0 = r15;
                        }
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("PROJE")));
                        salesConvertSalesFicheToSales.getCabin().setCode(cursorQuery.getString(cursorQuery.getColumnIndex("CBNCODE")));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("CBNNAME")));
                        FicheBooleanProp eDespatch = salesConvertSalesFicheToSales.getEDespatch();
                        boolean z = true;
                        if (cursorQuery.getInt(cursorQuery.getColumnIndex("EDESPATCH")) != 1) {
                            z = r8;
                        }
                        eDespatch.setSelect(z);
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("SERIAL")));
                        if (r18 == 0 && r19 == 0 && salesConvertSalesFicheToSales.getEDespatch().isSelect()) {
                            netsisSqlManager.mapCursorToEDespatch(t, cursorQuery, salesConvertSalesFicheToSales);
                        }
                    }
                } catch (Exception e2) {
                    Log.e("AA", "getSalesOrderOne: ", e2);
                }
                cursorQuery.close();
                List<T> table2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(InvoiceDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(t.logicalRef)});
                if (table2 != null && table2.size() > 0) {
                    for (T t2 : table2) {
                        if (t2.lineType == LineType.SERVICE.value) {
                            stringResource = ContextUtils.getStringResource(R.string.table_service);
                            stringResource2 = ContextUtils.getStringResource(R.string.table_service_units);
                            str = "0 AS ISVARYANT";
                        } else {
                            stringResource = ContextUtils.getStringResource(R.string.table_item);
                            stringResource2 = ContextUtils.getStringResource(R.string.table_item_units);
                            str = "I.ISVARYANT AS ISVARYANT";
                        }
                        Cursor cursorQuery2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.net_qry_get_sales_invoice_detail_edit, stringResource, stringResource2, str), StringUtils.convertIntToString(t2.logicalRef));
                        SalesDetail salesDetailConvertSalesFicheDetailToSalesDetail = t2.convertSalesFicheDetailToSalesDetail();
                        salesDetailConvertSalesFicheDetailToSalesDetail.setSearchBarcodes(ErpCreator.getInstance().getmBaseErp().getFicheDetailBarcodesBySalesDetailRef(salesDetailConvertSalesFicheDetailToSalesDetail.getLogicalRef(), DetailBarcodeFicheType.InvoiceDispatch.ordinal()));
                        if (cursorQuery2 != null) {
                            try {
                                if (cursorQuery2.getCount() > 0 && cursorQuery2.moveToFirst()) {
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setCode(cursorQuery2.getString(cursorQuery2.getColumnIndex("ITEMCODE")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setName(cursorQuery2.getString(cursorQuery2.getColumnIndex("ITEMNAME")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setCardType(cursorQuery2.getInt(cursorQuery2.getColumnIndex("CARDTYPE")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setTrackType(cursorQuery2.getInt(cursorQuery2.getColumnIndex("TRACKTYPE")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.getUnit().setCode(cursorQuery2.getString(cursorQuery2.getColumnIndex("UUCODE")));
                                    FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("UCODE")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.getSecondUnit().setCode(cursorQuery2.getString(cursorQuery2.getColumnIndex("UUSCODE")));
                                    FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("USCODE")));
                                    FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("ODEMEPLAN")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.getSpeCode().setLogicalRef(cursorQuery2.getInt(cursorQuery2.getColumnIndex("SREF")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setBarcodeCount(cursorQuery2.getInt(cursorQuery2.getColumnIndex("BARCODECOUNT")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setNetWeight(cursorQuery2.getDouble(cursorQuery2.getColumnIndex("WEIGHT")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setNetVolume(cursorQuery2.getDouble(cursorQuery2.getColumnIndex("VOLUME")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setWidth(cursorQuery2.getDouble(cursorQuery2.getColumnIndex("WIDTH")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setLength(cursorQuery2.getDouble(cursorQuery2.getColumnIndex("LENGTH")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setHeight(cursorQuery2.getDouble(cursorQuery2.getColumnIndex("HEIGHT")));
                                    int r02 = 0;
                                    while (r02 < salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountLength()) {
                                        FicheDiscountRefProp discountCard2 = salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountCard(r02);
                                        int r14 = r02 + 1;
                                        String sb2 = "D" +
                                                r14;
                                        FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex(sb2)));
                                        salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountCard(r02).setLogicalRef(cursorQuery2.getInt(cursorQuery2.getColumnIndex("C" + r14)));
                                        r02 = r14;
                                    }
                                    FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("VNAME")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setHasVariant(StringUtils.convertIntToBoolean(cursorQuery2.getInt(cursorQuery2.getColumnIndex("ISVARYANT"))));
                                    Cursor cursorQuery3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_sales_serilot_detail_edit), StringUtils.convertIntToString(salesDetailConvertSalesFicheDetailToSalesDetail.getLogicalRef()), "0");
                                    if (cursorQuery3 != null) {
                                        try {
                                            try {
                                                if (cursorQuery3.getCount() > 0) {
                                                    for (int r03 = 0; r03 < cursorQuery3.getCount(); r03++) {
                                                        if (cursorQuery3.moveToPosition(r03)) {
                                                            Serilot serilot = new Serilot();
                                                            serilot.logicalRef = cursorQuery3.getInt(cursorQuery3.getColumnIndex("LOGICALREF"));
                                                            serilot.detailRef = cursorQuery3.getInt(cursorQuery3.getColumnIndex("DETAILREF"));
                                                            serilot.unit = cursorQuery3.getString(cursorQuery3.getColumnIndex("UNIT"));
                                                            serilot.code = cursorQuery3.getString(cursorQuery3.getColumnIndex("CODE"));
                                                            serilot.amount = cursorQuery3.getDouble(cursorQuery3.getColumnIndex("AMOUNT"));
                                                            serilot.expDate = cursorQuery3.getString(cursorQuery3.getColumnIndex("EXPDATE"));
                                                            salesDetailConvertSalesFicheDetailToSalesDetail.getSalesSerialLots().add(serilot);
                                                        }
                                                    }
                                                }
                                            } catch (Throwable th) {
                                                cursorQuery3.close();
                                                throw th;
                                            }
                                        } catch (Exception e3) {
                                            Log.e(TAG, "getSalesInvoiceExam:  ", e3);
                                        }
                                    }
                                    cursorQuery3.close();
                                }
                            } catch (Exception e4) {
                                Log.e("AA", "getSalesInvoiceExam: ", e4);
                            }
                        }
                        if (cursorQuery2 != null && !cursorQuery2.isClosed()) {
                            cursorQuery2.close();
                        }
                        salesDetailConvertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(salesDetailConvertSalesFicheDetailToSalesDetail.getSalesSerialLots(), salesDetailConvertSalesFicheDetailToSalesDetail.getTrackType()));
                        salesConvertSalesFicheToSales.getMSalesDetailList().add(salesDetailConvertSalesFicheDetailToSalesDetail);
                    }
                }
                salesConvertSalesFicheToSales.setMSalesType(salesType.getmValue());
                arrayList.add(salesConvertSalesFicheToSales);
                netsisSqlManager = this;
                r8 = 0;
            } finally {
                cursorQuery.close();
            }
        }
        return arrayList;
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<CustomerNew> getNewCustomers(int r3) {
        List<T> table = r3 == -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ClCard.class, "ISTRANSFER=0", null) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ClCard.class, "ISTRANSFER=0 AND LOGICALREF=?", new String[]{String.valueOf(r3)});
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = table.iterator();
        while (it.hasNext()) {
            arrayList.add(((ClCard) it.next()).convert());
        }
        return arrayList;
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<CaseCash> getCaseCashs(int r2) {
        return getCaseCashs(r2, 0);
    }

    public List<CaseCash> getCaseCashs(int r2, int r3) {
        return r2 != -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(CaseCash.class, "ISTRANSFER=? AND LOGICALREF=? AND CLCODE IN (SELECT CODE FROM CLCARD WHERE ISTRANSFER = 1)", new String[]{String.valueOf(r3), String.valueOf(r2)}) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(CaseCash.class, "ISTRANSFER=? AND CLCODE IN (SELECT CODE FROM CLCARD WHERE ISTRANSFER = 1)", new String[]{String.valueOf(r3)});
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<VisitInfo> getVisits(int r3) {
        if (r3 == -1) {
            return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTableWhere(VisitInfo.class, "AUTO=0 AND ISTRANSFER=? AND CLCODE IN (SELECT CODE FROM CLCARD WHERE ISTRANSFER = 1)", new String[]{"0"});
        }
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTableWhere(VisitInfo.class, "AUTO=0 AND ISTRANSFER=? AND ID=? AND CLCODE IN (SELECT CODE FROM CLCARD WHERE ISTRANSFER = 1)", new String[]{"0", String.valueOf(r3)});
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<TodoInfoDb> getTodos(int r3) {
        return r3 == -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTableWhere(TodoInfoDb.class, "LATITUDE<>? AND LONGTITUDE<>? AND ISTRANSFERWORPROC=?", new String[]{"0", "0", "0"}) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTableWhere(TodoInfoDb.class, "LOGICALREF = ?", new String[]{String.valueOf(r3)});
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<ChequeDeed> getChequesAndDeed(int r2, int r3) {
        return getChequesAndDeed(r2, r3, 0);
    }

    public List<ChequeDeed> getChequesAndDeed(int r5, int r6, int r7) {
        ArrayList arrayList = new ArrayList();
        for (T t : r6 == -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Chequedeed.class, "FTYPE=? AND ISTRANSFER=? AND CLCODE IN (SELECT CODE FROM CLCARD WHERE ISTRANSFER = 1)", new String[]{String.valueOf(r5), String.valueOf(r7)}) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Chequedeed.class, "FTYPE=? AND ISTRANSFER=? AND LOGICALREF=? AND CLCODE IN (SELECT CODE FROM CLCARD WHERE ISTRANSFER = 1)", new String[]{String.valueOf(r5), String.valueOf(r7), String.valueOf(r6)})) {
            ChequeDeed chequeDeed = new ChequeDeed();
            List<ChequedeedDetail> table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ChequedeedDetail.class, "CHEQUEDEEDID=?", new String[]{String.valueOf(t.logicalRef)});
            if (table == null) {
                table = new ArrayList<>();
            }
            chequeDeed.setChequedeed(t);
            chequeDeed.setChequedeedDetail(table);
            arrayList.add(chequeDeed);
        }
        return arrayList;
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<CashCreditX> getCreditAndCash(int r2, int r3) {
        return getCreditAndCash(r2, r3, 0);
    }

    public List<CashCreditX> getCreditAndCash(int r5, int r6, int r7) {
        ArrayList arrayList = new ArrayList();
        for (T t : r6 == -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(CashCredit.class, "FTYPE=? AND ISTRANSFER=? AND CLCODE IN (SELECT CODE FROM CLCARD WHERE ISTRANSFER = 1)", new String[]{String.valueOf(r5), String.valueOf(r7)}) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(CashCredit.class, "FTYPE=? AND ISTRANSFER=? AND LOGICALREF=? AND CLCODE IN (SELECT CODE FROM CLCARD WHERE ISTRANSFER = 1)", new String[]{String.valueOf(r5), String.valueOf(r7), String.valueOf(r6)})) {
            CashCreditX cashCreditX = new CashCreditX();
            List<CashCreditDetail> table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(CashCreditDetail.class, "CASHCREDITID=?", new String[]{String.valueOf(t.logicalRef)});
            if (table == null) {
                table = new ArrayList<>();
            }
            cashCreditX.setCashCredit(t);
            cashCreditX.setCashCreditDetails(table);
            arrayList.add(cashCreditX);
        }
        return arrayList;
    }

    
    public static ObservableSource lambdagetSalesWhTransfer35(int r0) throws Exception {
        return Observable.just(Integer.valueOf(r0));
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getSalesWhTransfer(final int r3, final ResponseListener<Sales> responseListener) {
        Observable<T> observableObserveOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public Object call() {
                return NetsisSqlManager.lambdagetSalesWhTransfer35(r3);
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda1
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return NetsisSqlManager.lambdagetSalesWhTransfer36(r3, (Integer) obj);
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda2
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                return NetsisSqlManager.lambdagetSalesWhTransfer37((List) obj);
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda3
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return this.f0.lambdagetSalesWhTransfer38(r3, (List) obj);
            }
        }).defaultIfEmpty(new Sales()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observableObserveOn.subscribe(new NetsisSqlManagerExternalSyntheticLambda4(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda5
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) throws Exception {
                NetsisSqlManager.lambdagetSalesWhTransfer39(responseListener, (Throwable) obj);
            }
        });
    }

    
    public static List lambdagetSalesWhTransfer36(int r2, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(WhTransfer.class, "LOGICALREF=?", new String[]{String.valueOf(r2)});
    }

    
    public static boolean lambdagetSalesWhTransfer37(List list) throws Exception {
        return list != null && list.size() == 1;
    }

    
    public Sales lambdagetSalesWhTransfer38(int r14, List list) throws Exception {
        String stringResource;
        String stringResource2;
        String str;
        new Sales();
        WhTransfer whTransfer = (WhTransfer) list.get(0);
        Sales salesConvertSalesFicheToSales = whTransfer.convertSalesFicheToSales();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.net_qry_get_sales_whTransfer_edit), StringUtils.convertIntToString(r14));
                if (cursorQuery != null && cursorQuery.getCount() > 0 && cursorQuery.moveToFirst()) {
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("AMBAR")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("ISYERI")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("FABRIKA")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("BOLUM")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("ODEMEPLAN")));
                    salesConvertSalesFicheToSales.getTradeGroup().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("TREF")));
                    salesConvertSalesFicheToSales.setNotUseGattribKdv(CalculateUtils.isGattribNotUseKdv(cursorQuery.getInt(cursorQuery.getColumnIndex("GATTRIB"))));
                    salesConvertSalesFicheToSales.getSpeCode().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("SREF")));
                    salesConvertSalesFicheToSales.getWarrantyCode().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("WREF")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("TITLE")));
                    FicheStringProp.setDefinition(getShipAddressDefinition(salesConvertSalesFicheToSales.getShipAddress().getLogicalRef()));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("SHPCODE")));
                    salesConvertSalesFicheToSales.getShipAgent().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("SHPREF")));
                    int r9 = 0;
                    while (r9 < salesConvertSalesFicheToSales.getDiscountLength()) {
                        FicheDiscountRefProp discountCard = salesConvertSalesFicheToSales.getDiscountCard(r9);
                        int r12 = r9 + 1;
                        String sb = "D" +
                                r12;
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex(sb)));
                        salesConvertSalesFicheToSales.getDiscountCard(r9).setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("C" + r12)));
                        r9 = r12;
                    }
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("PROJE")));
                    salesConvertSalesFicheToSales.getCabin().setCode(cursorQuery.getString(cursorQuery.getColumnIndex("CBNCODE")));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("CBNNAME")));
                    salesConvertSalesFicheToSales.getEDespatch().setSelect(cursorQuery.getInt(cursorQuery.getColumnIndex("EDESPATCH")) == 1);
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("SERIAL")));
                    if (salesConvertSalesFicheToSales.getEDespatch().isSelect()) {
                        mapCursorToEDespatch(whTransfer, cursorQuery, salesConvertSalesFicheToSales);
                    }
                }
            } catch (Exception e2) {
                Log.e("AA", "getSalesOrderOne: ", e2);
            }
            cursorQuery.close();
            List<T> table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(WhTransferDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(r14)});
            if (table != null && table.size() > 0) {
                for (T t : table) {
                    if (t.lineType == LineType.SERVICE.value) {
                        stringResource = ContextUtils.getStringResource(R.string.table_service);
                        stringResource2 = ContextUtils.getStringResource(R.string.table_service_units);
                        str = "0 AS ISVARYANT";
                    } else {
                        stringResource = ContextUtils.getStringResource(R.string.table_item);
                        stringResource2 = ContextUtils.getStringResource(R.string.table_item_units);
                        str = "I.ISVARYANT AS ISVARYANT";
                    }
                    Cursor cursorQuery2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.net_qry_get_sales_whTransfer_detail_edit, stringResource, stringResource2, str), StringUtils.convertIntToString(t.logicalRef));
                    if (!t.priceCode.isEmpty()) {
                        t.priceRef = StringUtils.convertStringToInt(t.priceCode);
                    }
                    SalesDetail salesDetailConvertSalesFicheDetailToSalesDetail = t.convertSalesFicheDetailToSalesDetail();
                    if (cursorQuery2 != null) {
                        try {
                            if (cursorQuery2.getCount() > 0 && cursorQuery2.moveToFirst()) {
                                salesDetailConvertSalesFicheDetailToSalesDetail.setName(cursorQuery2.getString(cursorQuery2.getColumnIndex("ITEMNAME")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setCode(cursorQuery2.getString(cursorQuery2.getColumnIndex("ITEMCODE")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setItemRef(cursorQuery2.getInt(cursorQuery2.getColumnIndex("ITEMREF")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setCardType(cursorQuery2.getInt(cursorQuery2.getColumnIndex("CARDTYPE")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setTrackType(cursorQuery2.getInt(cursorQuery2.getColumnIndex("TRACKTYPE")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.getUnit().setCode(cursorQuery2.getString(cursorQuery2.getColumnIndex("UUCODE")));
                                FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("UCODE")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.getSecondUnit().setCode(cursorQuery2.getString(cursorQuery2.getColumnIndex("UUSCODE")));
                                FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("USCODE")));
                                FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("ODEMEPLAN")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.getSpeCode().setLogicalRef(cursorQuery2.getInt(cursorQuery2.getColumnIndex("SREF")));
                                int r8 = 0;
                                while (r8 < salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountLength()) {
                                    FicheDiscountRefProp discountCard2 = salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountCard(r8);
                                    int r11 = r8 + 1;
                                    String sb2 = "D" +
                                            r11;
                                    FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex(sb2)));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountCard(r8).setLogicalRef(cursorQuery2.getInt(cursorQuery2.getColumnIndex("C" + r11)));
                                    r8 = r11;
                                }
                                FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("VNAME")));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setHasVariant(StringUtils.convertIntToBoolean(cursorQuery2.getInt(cursorQuery2.getColumnIndex("ISVARYANT"))));
                                salesDetailConvertSalesFicheDetailToSalesDetail.setSearchBarcodes(ErpCreator.getInstance().getmBaseErp().getFicheDetailBarcodesBySalesDetailRef(salesDetailConvertSalesFicheDetailToSalesDetail.getLogicalRef(), DetailBarcodeFicheType.WhTransfer.ordinal()));
                                Cursor cursorQuery3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_sales_serilot_detail_edit), StringUtils.convertIntToString(salesDetailConvertSalesFicheDetailToSalesDetail.getLogicalRef()), "1");
                                if (cursorQuery3 != null) {
                                    try {
                                        try {
                                            if (cursorQuery3.getCount() > 0) {
                                                for (int r92 = 0; r92 < cursorQuery3.getCount(); r92++) {
                                                    if (cursorQuery3.moveToPosition(r92)) {
                                                        Serilot serilot = new Serilot();
                                                        serilot.logicalRef = cursorQuery3.getInt(cursorQuery3.getColumnIndex("LOGICALREF"));
                                                        serilot.detailRef = cursorQuery3.getInt(cursorQuery3.getColumnIndex("DETAILREF"));
                                                        serilot.unit = cursorQuery3.getString(cursorQuery3.getColumnIndex("UNIT"));
                                                        serilot.code = cursorQuery3.getString(cursorQuery3.getColumnIndex("CODE"));
                                                        serilot.amount = cursorQuery3.getDouble(cursorQuery3.getColumnIndex("AMOUNT"));
                                                        serilot.expDate = cursorQuery3.getString(cursorQuery3.getColumnIndex("EXPDATE"));
                                                        salesDetailConvertSalesFicheDetailToSalesDetail.getSalesSerialLots().add(serilot);
                                                    }
                                                }
                                            }
                                        } catch (Throwable th) {
                                            cursorQuery3.close();
                                            throw th;
                                        }
                                    } catch (Exception e3) {
                                        Log.e(TAG, "getSalesInvoiceExam:  ", e3);
                                    }
                                }
                                cursorQuery3.close();
                            }
                        } catch (Exception e4) {
                            Log.e("AA", "getSalesInvoiceExam: ", e4);
                        }
                    }
                    if (cursorQuery2 != null && !cursorQuery2.isClosed()) {
                        cursorQuery2.close();
                    }
                    salesDetailConvertSalesFicheDetailToSalesDetail.setDivUnit(true);
                    salesDetailConvertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(salesDetailConvertSalesFicheDetailToSalesDetail.getSalesSerialLots(), salesDetailConvertSalesFicheDetailToSalesDetail.getTrackType()));
                    salesConvertSalesFicheToSales.getMSalesDetailList().add(salesDetailConvertSalesFicheDetailToSalesDetail);
                }
            }
            return salesConvertSalesFicheToSales;
        } finally {
            cursorQuery.close();
        }
    }

    
    public static void lambdagetSalesWhTransfer39(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    public List<Sales> mapCursorToWhTransfer(int r19) throws Throwable {
        String stringResource;
        String stringResource2;
        String str;
        String str2;
        String str3;
        NetsisSqlManager<T> netsisSqlManager = this;
        String str4 = "C";
        String str5 = "D";
        int r8 = 0;
        List<T> table = r19 == -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(WhTransfer.class, "ISTRANSFER=?", new String[]{String.valueOf(0)}) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(WhTransfer.class, "LOGICALREF=? AND ISTRANSFER=?", new String[]{String.valueOf(r19), String.valueOf(0)});
        ArrayList arrayList = new ArrayList();
        for (T t : table) {
            Sales salesConvertSalesFicheToSales = t.convertSalesFicheToSales();
            Cursor cursorQuery = null;
            try {
                try {
                    cursorQuery = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.net_qry_get_sales_whTransfer_edit), StringUtils.convertIntToString(t.logicalRef));
                    if (cursorQuery != null && cursorQuery.getCount() > 0 && cursorQuery.moveToFirst()) {
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("AMBAR")));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("ISYERI")));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("FABRIKA")));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("BOLUM")));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("ODEMEPLAN")));
                        salesConvertSalesFicheToSales.getTradeGroup().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("TREF")));
                        salesConvertSalesFicheToSales.setNotUseGattribKdv(CalculateUtils.isGattribNotUseKdv(cursorQuery.getInt(cursorQuery.getColumnIndex("GATTRIB"))));
                        salesConvertSalesFicheToSales.getSpeCode().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("SREF")));
                        salesConvertSalesFicheToSales.getWarrantyCode().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("WREF")));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("TITLE")));
                        FicheStringProp.setDefinition(netsisSqlManager.getShipAddressDefinition(salesConvertSalesFicheToSales.getShipAddress().getLogicalRef()));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("SHPCODE")));
                        salesConvertSalesFicheToSales.getShipAgent().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("SHPREF")));
                        int r0 = r8;
                        while (r0 < salesConvertSalesFicheToSales.getDiscountLength()) {
                            FicheDiscountRefProp discountCard = salesConvertSalesFicheToSales.getDiscountCard(r0);
                            int r82 = r0 + 1;
                            String sb = str5 +
                                    r82;
                            FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex(sb)));
                            salesConvertSalesFicheToSales.getDiscountCard(r0).setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex(str4 + r82)));
                            r0 = r82;
                        }
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("PROJE")));
                        salesConvertSalesFicheToSales.getCabin().setCode(cursorQuery.getString(cursorQuery.getColumnIndex("CBNCODE")));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("CBNNAME")));
                        salesConvertSalesFicheToSales.getEDespatch().setSelect(cursorQuery.getInt(cursorQuery.getColumnIndex("EDESPATCH")) == 1);
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("SERIAL")));
                        if (salesConvertSalesFicheToSales.getEDespatch().isSelect()) {
                            netsisSqlManager.mapCursorToEDespatch(t, cursorQuery, salesConvertSalesFicheToSales);
                        }
                    }
                } catch (Exception e2) {
                    Log.e("AA", "getSalesOrderOne: ", e2);
                }
                cursorQuery.close();
                List<T> table2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(WhTransferDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(t.logicalRef)});
                ErpCreator.getInstance().getmBaseErp().getUseProductNameDescription();
                if (table2 != null && table2.size() > 0) {
                    for (T t2 : table2) {
                        if (t2.lineType == LineType.SERVICE.value) {
                            stringResource = ContextUtils.getStringResource(R.string.table_service);
                            stringResource2 = ContextUtils.getStringResource(R.string.table_service_units);
                            str = "0 AS ISVARYANT";
                        } else {
                            stringResource = ContextUtils.getStringResource(R.string.table_item);
                            stringResource2 = ContextUtils.getStringResource(R.string.table_item_units);
                            str = "I.ISVARYANT AS ISVARYANT";
                        }
                        Cursor cursorQuery2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.net_qry_get_sales_whTransfer_detail_edit, stringResource, stringResource2, str), StringUtils.convertIntToString(t2.logicalRef));
                        SalesDetail salesDetailConvertSalesFicheDetailToSalesDetail = t2.convertSalesFicheDetailToSalesDetail();
                        salesDetailConvertSalesFicheDetailToSalesDetail.setSearchBarcodes(ErpCreator.getInstance().getmBaseErp().getFicheDetailBarcodesBySalesDetailRef(salesDetailConvertSalesFicheDetailToSalesDetail.getLogicalRef(), DetailBarcodeFicheType.WhTransfer.ordinal()));
                        if (cursorQuery2 != null) {
                            try {
                                if (cursorQuery2.getCount() <= 0 || !cursorQuery2.moveToFirst()) {
                                    str2 = str4;
                                    str3 = str5;
                                } else {
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setName(cursorQuery2.getString(cursorQuery2.getColumnIndex("ITEMNAME")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setCode(cursorQuery2.getString(cursorQuery2.getColumnIndex("ITEMCODE")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setCardType(cursorQuery2.getInt(cursorQuery2.getColumnIndex("CARDTYPE")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setTrackType(cursorQuery2.getInt(cursorQuery2.getColumnIndex("TRACKTYPE")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.getUnit().setCode(cursorQuery2.getString(cursorQuery2.getColumnIndex("UUCODE")));
                                    FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("UCODE")));
                                    FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("ODEMEPLAN")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.getSpeCode().setLogicalRef(cursorQuery2.getInt(cursorQuery2.getColumnIndex("SREF")));
                                    int r02 = 0;
                                    while (r02 < salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountLength()) {
                                        FicheDiscountRefProp discountCard2 = salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountCard(r02);
                                        int r14 = r02 + 1;
                                        String sb2 = str5 +
                                                r14;
                                        FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex(sb2)));
                                        salesDetailConvertSalesFicheDetailToSalesDetail.getDiscountCard(r02).setLogicalRef(cursorQuery2.getInt(cursorQuery2.getColumnIndex(str4 + r14)));
                                        r02 = r14;
                                    }
                                    FicheStringProp.setDefinition(cursorQuery2.getString(cursorQuery2.getColumnIndex("VNAME")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.getVariant().setCode(cursorQuery2.getString(cursorQuery2.getColumnIndex("VCODE")));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setHasVariant(StringUtils.convertIntToBoolean(cursorQuery2.getInt(cursorQuery2.getColumnIndex("ISVARYANT"))));
                                    salesDetailConvertSalesFicheDetailToSalesDetail.setLocTracking(StringUtils.convertIntToBoolean(cursorQuery2.getInt(cursorQuery2.getColumnIndex("LOCTRACKING"))));
                                    try {
                                        salesDetailConvertSalesFicheDetailToSalesDetail.setDivUnit(cursorQuery2.getInt(cursorQuery2.getColumnIndex("DIVUNIT")) == 1);
                                        Cursor cursorQuery3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_sales_serilot_detail_edit), StringUtils.convertIntToString(salesDetailConvertSalesFicheDetailToSalesDetail.getLogicalRef()), "1");
                                        if (cursorQuery3 != null) {
                                            try {
                                                if (cursorQuery3.getCount() > 0) {
                                                    int r03 = 0;
                                                    while (r03 < cursorQuery3.getCount()) {
                                                        if (cursorQuery3.moveToPosition(r03)) {
                                                            Serilot serilot = new Serilot();
                                                            serilot.logicalRef = cursorQuery3.getInt(cursorQuery3.getColumnIndex("LOGICALREF"));
                                                            serilot.detailRef = cursorQuery3.getInt(cursorQuery3.getColumnIndex("DETAILREF"));
                                                            serilot.unit = cursorQuery3.getString(cursorQuery3.getColumnIndex("UNIT"));
                                                            serilot.code = cursorQuery3.getString(cursorQuery3.getColumnIndex("CODE"));
                                                            str2 = str4;
                                                            str3 = str5;
                                                            try {
                                                                try {
                                                                    serilot.amount = cursorQuery3.getDouble(cursorQuery3.getColumnIndex("AMOUNT"));
                                                                    serilot.expDate = cursorQuery3.getString(cursorQuery3.getColumnIndex("EXPDATE"));
                                                                    serilot.grpBegCode = cursorQuery3.getString(cursorQuery3.getColumnIndex("GRP_BEG_CODE"));
                                                                    serilot.grpEndCode = cursorQuery3.getString(cursorQuery3.getColumnIndex("GRP_END_CODE"));
                                                                    serilot.locationCode = cursorQuery3.getString(cursorQuery3.getColumnIndex("LOCATIONCODE"));
                                                                    serilot.stTransRef = cursorQuery3.getInt(cursorQuery3.getColumnIndex("STTRANSREF"));
                                                                    serilot.mainUnitRef = cursorQuery3.getInt(cursorQuery3.getColumnIndex("MAINUNITREF"));
                                                                    serilot.sourceUnitRef = cursorQuery3.getInt(cursorQuery3.getColumnIndex("SOURCEUNITREF"));
                                                                    salesDetailConvertSalesFicheDetailToSalesDetail.getSalesSerialLots().add(serilot);
                                                                } catch (Throwable th) {
                                                                    th = th;
                                                                    cursorQuery3.close();
                                                                    throw th;
                                                                }
                                                            } catch (Exception e3) {
                                                                e = e3;
                                                                Log.e(TAG, "getSalesWhTransfer:  ", e);
                                                                cursorQuery3.close();
                                                                if (cursorQuery2 != null) {
                                                                }
                                                                salesDetailConvertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(salesDetailConvertSalesFicheDetailToSalesDetail.getSalesSerialLots(), salesDetailConvertSalesFicheDetailToSalesDetail.getTrackType()));
                                                                salesConvertSalesFicheToSales.getMSalesDetailList().add(salesDetailConvertSalesFicheDetailToSalesDetail);
                                                                str4 = str2;
                                                                str5 = str3;
                                                            }
                                                        } else {
                                                            str2 = str4;
                                                            str3 = str5;
                                                        }
                                                        r03++;
                                                        str4 = str2;
                                                        str5 = str3;
                                                    }
                                                }
                                                str2 = str4;
                                                str3 = str5;
                                            } catch (Exception e4) {
                                                e = e4;
                                                str2 = str4;
                                                str3 = str5;
                                            } catch (Throwable th2) {
                                                th = th2;
                                                str2 = str4;
                                                str3 = str5;
                                            }
                                            try {
                                                cursorQuery3.close();
                                            } catch (Exception e5) {
                                                e = e5;
                                                Log.e("AA", "getSalesInvoiceExam: ", e);
                                                if (cursorQuery2 != null) {
                                                }
                                                salesDetailConvertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(salesDetailConvertSalesFicheDetailToSalesDetail.getSalesSerialLots(), salesDetailConvertSalesFicheDetailToSalesDetail.getTrackType()));
                                                salesConvertSalesFicheToSales.getMSalesDetailList().add(salesDetailConvertSalesFicheDetailToSalesDetail);
                                                str4 = str2;
                                                str5 = str3;
                                            }
                                        } else {
                                            str2 = str4;
                                            str3 = str5;
                                            cursorQuery3.close();
                                        }
                                    } catch (Exception e6) {
                                        e = e6;
                                        str2 = str4;
                                        str3 = str5;
                                    }
                                }
                            } catch (Exception e7) {
                                e = e7;
                                str2 = str4;
                                str3 = str5;
                            }
                        }
                        if (cursorQuery2 != null && !cursorQuery2.isClosed()) {
                            cursorQuery2.close();
                        }
                        salesDetailConvertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(salesDetailConvertSalesFicheDetailToSalesDetail.getSalesSerialLots(), salesDetailConvertSalesFicheDetailToSalesDetail.getTrackType()));
                        salesConvertSalesFicheToSales.getMSalesDetailList().add(salesDetailConvertSalesFicheDetailToSalesDetail);
                        str4 = str2;
                        str5 = str3;
                    }
                }
                salesConvertSalesFicheToSales.setMSalesType(SalesType.WHTRANSFER.getmValue());
                arrayList.add(salesConvertSalesFicheToSales);
                netsisSqlManager = this;
                str4 = str4;
                str5 = str5;
                r8 = 0;
            } finally {
                cursorQuery.close();
            }
        }
        return arrayList;
    }
}
