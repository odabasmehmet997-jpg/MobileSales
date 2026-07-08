package com.proje.mobilesales.core.sql.tiger;

import android.R;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.util.Log;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.asynctask.TigerWcfAsyncTaskExternalSyntheticLambda25;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.LineType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.sql.SqlManager;
import com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda10;
import com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda16;
import com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda22;
import com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda28;
import com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda39;
import com.proje.mobilesales.core.sql.netsis.NetsisSqlManagerExternalSyntheticLambda4;
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
import com.proje.mobilesales.features.model.*;
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
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import javax.inject.Inject; 
public class TigerSqlManager<T> extends SqlManager<T> {
    private static final String TAG = "com.proje.mobilesales.core.sql.tiger.TigerSqlManager";

    @Inject
    public TigerSqlManager(Scheduler scheduler) {
        super(scheduler);
    } 
    public static ObservableSource lambdagetVisitExam0(int i2) throws Exception {
        return Observable.just(Integer.valueOf(i2));
    } 
    @SuppressLint({"CheckResult"})
    public void getVisitExam(final int i2, final ResponseListener<Visit> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { 
            public Object call() {
                ObservableSource lambdagetVisitExam0;
                try {
                    lambdagetVisitExam0 = TigerSqlManager.lambdagetVisitExam0(i2);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetVisitExam0;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                List lambdagetVisitExam1;
                try {
                    lambdagetVisitExam1 = TigerSqlManager.lambdagetVisitExam1(i2, (Integer) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetVisitExam1;
            }
        }).filter(new Predicate() {
            public boolean test(Object obj) {
                boolean lambdagetVisitExam2;
                try {
                    lambdagetVisitExam2 = TigerSqlManager.lambdagetVisitExam2((List) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetVisitExam2;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                Visit lambdagetVisitExam3;
                try {
                    lambdagetVisitExam3 = TigerSqlManager.lambdagetVisitExam3(i2, (List) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetVisitExam3;
            }
        }).defaultIfEmpty(new Visit()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new NetsisSqlManagerExternalSyntheticLambda22(responseListener), new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerSqlManager.lambdagetVisitExam4(responseListener, (Throwable) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Object invoke(Object obj) {
                return obj;
            }
        });
    } 
    public static List lambdagetVisitExam1(int i2, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(VisitInfo.class, "ID=?", new String[]{String.valueOf(i2)});
    } 
    public static boolean lambdagetVisitExam2(List list) throws Exception {
        return list != null && list.size() == 1;
    } 
    public static Visit lambdagetVisitExam3(int i2, List list) throws Exception {
        new Visit();
        Visit convert = ((VisitInfo) list.get(0)).convert();
        Cursor cursor = null;
        try {
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_visit_edit), StringUtils.convertIntToString(i2));
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("CODE")));
                }
            } catch (Exception e2) {
                Log.e(MobileSales.TAG, "getVisitExam: ", e2);
            }
            return convert;
        } finally {
            cursor.close();
        }
    }

    
    public static void lambdagetVisitExam4(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    
    public static ObservableSource lambdagetPenetrationExam5(int i2) throws Exception {
        return Observable.just(Integer.valueOf(i2));
    }
    public void getPenetrationExam(final int i2, final ResponseListener<Penetration> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() {
            public Object call() {
                ObservableSource lambdagetPenetrationExam5;
                try {
                    lambdagetPenetrationExam5 = TigerSqlManager.lambdagetPenetrationExam5(i2);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetPenetrationExam5;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                List lambdagetPenetrationExam6;
                try {
                    lambdagetPenetrationExam6 = TigerSqlManager.lambdagetPenetrationExam6(i2, (Integer) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetPenetrationExam6;
            }
        }).filter(new Predicate() {
            public boolean test(Object obj) {
                boolean lambdagetPenetrationExam7;
                try {
                    lambdagetPenetrationExam7 = TigerSqlManager.lambdagetPenetrationExam7((List) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetPenetrationExam7;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                Penetration lambdagetPenetrationExam8;
                try {
                    lambdagetPenetrationExam8 = TigerSqlManager.lambdagetPenetrationExam8(i2, (List) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetPenetrationExam8;
            }
        }).defaultIfEmpty(new Penetration()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new NetsisSqlManagerExternalSyntheticLambda16(responseListener), new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerSqlManager.lambdagetPenetrationExam9(responseListener, (Throwable) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        });
    }

    
    public static List lambdagetPenetrationExam6(int i2, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(UserMainPenetration.class, "ID=?", new String[]{String.valueOf(i2)});
    }

    
    public static boolean lambdagetPenetrationExam7(List list) throws Exception {
        return list != null && list.size() == 1;
    }

    
    public static Penetration lambdagetPenetrationExam8(int i2, List list) throws Exception {
        new Penetration();
        Penetration convert = ((UserMainPenetration) list.get(0)).convert();
        Cursor cursor = null;
        try {
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_penetration_edit), StringUtils.convertIntToString(i2));
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    convert.setPenetrationName(cursor.getString(cursor.getColumnIndex("PDEFINITION")));
                }
            } catch (Exception e2) {
                Log.e(MobileSales.TAG, "getPenetrationExam: ", e2);
            }
            cursor.close();
            List<T> table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(UserPenetration.class, "PENETFICHEID=?", new String[]{String.valueOf(i2)});
            ArrayList arrayList = new ArrayList();
            try {
                try {
                    cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_penetration_line_edit), StringUtils.convertIntToString(i2));
                    for (int i3 = 0; i3 < table.size(); i3++) {
                        PenetrationLine convert2 = ((UserPenetration) table.get(i3)).convert();
                        if (cursor.moveToPosition(i3)) {
                            convert2.setProductName(cursor.getString(cursor.getColumnIndex("PRODUCTNAME")));
                            convert2.setProductCode(cursor.getString(cursor.getColumnIndex("PRODUCTCODE")));
                            convert2.setProductRef(cursor.getInt(cursor.getColumnIndex("PRODUCTREF")));
                            convert2.setUnit(cursor.getString(cursor.getColumnIndex("PUNIT")));
                            boolean z = true;
                            convert2.setImageActive(cursor.getInt(cursor.getColumnIndex("PER_PIC")) == 1);
                            convert2.setNotActive(cursor.getInt(cursor.getColumnIndex("PER_NOTE")) == 1);
                            if (cursor.getInt(cursor.getColumnIndex("PER_PRICE")) != 1) {
                                z = false;
                            }
                            convert2.setPriceActive(z);
                        }
                        arrayList.add(convert2);
                    }
                    convert.setPenetrations(arrayList);
                } catch (Exception e3) {
                    Log.e(MobileSales.TAG, "getPenetrationExam: ", e3);
                }
                return convert;
            } catch (Throwable th) {
                throw th;
            }
        } finally {
            cursor.close();
        }
    }

    
    public static void lambdagetPenetrationExam9(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    public List<Penetration> getPenetrations(int i2) {
        Cursor cursor;
        List<T> table;
        ArrayList arrayList;
        int i3;
        Cursor cursor2 = null;
        List<T> table2 = i2 == -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(UserMainPenetration.class, "ISTRANSFER=0 ", null) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(UserMainPenetration.class, "ISTRANSFER=0 AND ID=?", new String[]{String.valueOf(i2)});
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = table2.iterator();
        while (it.hasNext()) {
            Penetration convert = ((UserMainPenetration) it.next()).convert();
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_penetration_edit), StringUtils.convertIntToString(i2));
                if (cursor != null) {
                    try {
                        try {
                            if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                                convert.setPenetrationName(cursor.getString(cursor.getColumnIndex("PDEFINITION")));
                                convert.setExist(StringUtils.convertIntToBoolean(cursor.getInt(cursor.getColumnIndex("PTYPE"))));
                            }
                        } catch (Throwable th) {
                            th = th;
                            cursor2 = cursor;
                            cursor2.close();
                            throw th;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        Log.e(MobileSales.TAG, "getPenetrationExam: ", e);
                        cursor.close();
                        table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(UserPenetration.class, "PENETFICHEID=?", new String[]{String.valueOf(convert.getId())});
                        arrayList = new ArrayList();
                        try {
                            cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_penetration_line_edit), StringUtils.convertIntToString(R.attr.id));
                            while (i3 < table.size()) {
                            }
                            convert.setPenetrations(arrayList);
                            arrayList2.add(convert);
                        } catch (Exception e3) {
                            Log.e(MobileSales.TAG, "getPenetrations: ", e3);
                        }
                        cursor.close();
                    }
                }
            } catch (Exception e4) {
                e = e4;
                cursor = null;
            } catch (Throwable th2) {
                th = th2;
            }
            cursor.close();
            table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(UserPenetration.class, "PENETFICHEID=?", new String[]{String.valueOf(convert.getId())});
            arrayList = new ArrayList();
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_penetration_line_edit), StringUtils.convertIntToString(R.attr.id));
                for (i3 = 0; i3 < table.size(); i3++) {
                    PenetrationLine convert2 = ((UserPenetration) table.get(i3)).convert();
                    if (cursor.moveToPosition(i3)) {
                        convert2.setProductName(cursor.getString(cursor.getColumnIndex("PRODUCTNAME")));
                        convert2.setProductCode(cursor.getString(cursor.getColumnIndex("PRODUCTCODE")));
                        convert2.setProductRef(cursor.getInt(cursor.getColumnIndex("PRODUCTREF")));
                        convert2.setUnit(cursor.getString(cursor.getColumnIndex("PUNIT")));
                    }
                    arrayList.add(convert2);
                }
                convert.setPenetrations(arrayList);
                arrayList2.add(convert);
                cursor.close();
            } catch (Throwable th3) {
                cursor.close();
                throw th3;
            }
        }
        return arrayList2;
    }

    
    public static ObservableSource lambdagetSalesOrderOne10(int i2) throws Exception {
        return Observable.just(Integer.valueOf(i2));
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getSalesOrderOne(final int i2, final ResponseListener<Sales> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda8
            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource lambdagetSalesOrderOne10;
                lambdagetSalesOrderOne10 = TigerSqlManager.lambdagetSalesOrderOne10(i2);
                return lambdagetSalesOrderOne10;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda9
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                List lambdagetSalesOrderOne11;
                lambdagetSalesOrderOne11 = TigerSqlManager.lambdagetSalesOrderOne11(i2, (Integer) obj);
                return lambdagetSalesOrderOne11;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda10
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetSalesOrderOne12;
                lambdagetSalesOrderOne12 = TigerSqlManager.lambdagetSalesOrderOne12((List) obj);
                return lambdagetSalesOrderOne12;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda11
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Sales lambdagetSalesOrderOne13;
                lambdagetSalesOrderOne13 = TigerSqlManager.this.lambdagetSalesOrderOne13(i2, (List) obj);
                return lambdagetSalesOrderOne13;
            }
        }).defaultIfEmpty(new Sales()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new NetsisSqlManagerExternalSyntheticLambda4(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda12
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                TigerSqlManager.lambdagetSalesOrderOne14(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    
    public static List lambdagetSalesOrderOne11(int i2, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Order.class, "LOGICALREF=?", new String[]{String.valueOf(i2)});
    }

    
    public static boolean lambdagetSalesOrderOne12(List list) throws Exception {
        return list != null && list.size() == 1;
    }

    public Sales lambdagetSalesOrderOne13(int i2, List list) throws Exception {
        List<T> table;
        Iterator<T> it;
        String str;
        String str2;
        String str3;
        String stringResource;
        String str4;
        new Sales();
        Sales convertSalesFicheToSales = ((Order) list.get(0)).convertSalesFicheToSales();
        Cursor cursor = null;
        try {
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_sales_order_edit), StringUtils.convertIntToString(i2));
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("AMBAR")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("KARSILAMAAMBARI")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("ISYERI")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("FABRIKA")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("BOLUM")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("ODEMEPLAN")));
                    convertSalesFicheToSales.getTradeGroup().setLogicalRef(cursor.getInt(cursor.getColumnIndex("TREF")));
                    convertSalesFicheToSales.setNotUseGattribKdv(CalculateUtils.isGattribNotUseKdv(cursor.getInt(cursor.getColumnIndex("GATTRIB"))));
                    convertSalesFicheToSales.getSpeCode().setLogicalRef(cursor.getInt(cursor.getColumnIndex("SREF")));
                    convertSalesFicheToSales.getWarrantyCode().setLogicalRef(cursor.getInt(cursor.getColumnIndex("WREF")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("TITLE")));
                    try {
                        FicheStringProp.setDefinition(getShipAddressDefinition(convertSalesFicheToSales.getShipAddress().getLogicalRef()));
                        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("SHPCODE")));
                        convertSalesFicheToSales.getShipAgent().setLogicalRef(cursor.getInt(cursor.getColumnIndex("SHPREF")));
                        int i3 = 0;
                        while (i3 < convertSalesFicheToSales.getDiscountLength()) {
                            FicheDiscountRefProp discountCard = convertSalesFicheToSales.getDiscountCard(i3);
                            int i4 = i3 + 1;
                            String sb = "D" +
                                    i4;
                            FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(sb)));
                            convertSalesFicheToSales.getDiscountCard(i3).setLogicalRef(cursor.getInt(cursor.getColumnIndex("C" + i4)));
                            i3 = i4;
                        }
                        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("PROJE")));
                        convertSalesFicheToSales.setCampaingRefs(cursor.getString(cursor.getColumnIndex("CAMPAIGNREFS")));
                    } catch (Exception e2) {
                        e = e2;
                        Log.e("AA", "getSalesOrderOne: ", e);
                        cursor.close();
                        table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(OrderDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(i2)});
                        if (!ErpCreator.getInstance().getmBaseErp().getUseProductNameDescription()) {
                        }
                        if (table != null) {
                            it = table.iterator();
                            String str5 = "";
                            while (it.hasNext()) {
                            }
                        }
                        return convertSalesFicheToSales;
                    }
                }
            } catch (Exception e3) {
                e = e3;
            } catch (Throwable th) {
                th = th;
                cursor.close();
                throw th;
            }
            cursor.close();
            table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(OrderDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(i2)});
            String str6 = !ErpCreator.getInstance().getmBaseErp().getUseProductNameDescription() ? "I.NAME2" : "I.NAME";
            if (table != null && table.size() > 0) {
                it = table.iterator();
                String str52 = "";
                while (it.hasNext()) {
                    OrderDetail orderDetail = (OrderDetail) it.next();
                    if (orderDetail.lineType == LineType.SERVICE.value) {
                        str4 = ContextUtils.getStringResource(R.string.table_service);
                        stringResource = ContextUtils.getStringResource(R.string.table_service_units);
                        str3 = "0 AS ISVARYANT";
                        str2 = "0 AS DIVUNIT";
                        str = str52;
                    } else {
                        String stringResource2 = ContextUtils.getStringResource(R.string.table_item);
                        str = "LEFT JOIN UNITS UNT ON UNT.LOGICALREF=U.LOGICALREF";
                        str2 = "UNT.DIVUNIT AS DIVUNIT";
                        str3 = "I.ISVARYANT AS ISVARYANT";
                        stringResource = ContextUtils.getStringResource(R.string.table_item_units);
                        str4 = stringResource2;
                    }
                    Iterator<T> it2 = it;
                    Cursor query = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.qry_get_sales_order_detail_edit, str4, stringResource, str3, str6, str2, str), StringUtils.convertIntToString(orderDetail.logicalRef));
                    SalesDetail convertSalesFicheDetailToSalesDetail = orderDetail.convertSalesFicheDetailToSalesDetail();
                    if (query != null) {
                        try {
                            if (query.getCount() > 0 && query.moveToFirst()) {
                                convertSalesFicheDetailToSalesDetail.setName(query.getString(query.getColumnIndex("ITEMNAME")));
                                convertSalesFicheDetailToSalesDetail.setCode(query.getString(query.getColumnIndex("ITEMCODE")));
                                convertSalesFicheDetailToSalesDetail.setCardType(query.getInt(query.getColumnIndex("CARDTYPE")));
                                convertSalesFicheDetailToSalesDetail.setTrackType(query.getInt(query.getColumnIndex("TRACKTYPE")));
                                convertSalesFicheDetailToSalesDetail.getUnit().setCode(query.getString(query.getColumnIndex("UUCODE")));
                                FicheStringProp.setDefinition(query.getString(query.getColumnIndex("UCODE")));
                                FicheStringProp.setDefinition(query.getString(query.getColumnIndex("ODEMEPLAN")));
                                convertSalesFicheDetailToSalesDetail.getSpeCode().setLogicalRef(query.getInt(query.getColumnIndex("SREF")));
                                int i5 = 0;
                                while (i5 < convertSalesFicheDetailToSalesDetail.getDiscountLength()) {
                                    FicheDiscountRefProp discountCard2 = convertSalesFicheDetailToSalesDetail.getDiscountCard(i5);
                                    int i6 = i5 + 1;
                                    String sb2 = "D" +
                                            i6;
                                    FicheStringProp.setDefinition(query.getString(query.getColumnIndex(sb2)));
                                    convertSalesFicheDetailToSalesDetail.getDiscountCard(i5).setLogicalRef(query.getInt(query.getColumnIndex("C" + i6)));
                                    i5 = i6;
                                }
                                convertSalesFicheDetailToSalesDetail.setHasVariant(StringUtils.convertIntToBoolean(query.getInt(query.getColumnIndex("ISVARYANT"))));
                                FicheStringProp.setDefinition(query.getString(query.getColumnIndex("VNAME")));
                                convertSalesFicheDetailToSalesDetail.getVariant().setCode(query.getString(query.getColumnIndex("VCODE")));
                                boolean z = query.getInt(query.getColumnIndex("DIVUNIT")) == 1;
                                convertSalesFicheDetailToSalesDetail.setDivUnit(z);
                                convertSalesFicheDetailToSalesDetail.setBarcodeCount(query.getInt(query.getColumnIndex("BARCODECOUNT")));
                                convertSalesFicheDetailToSalesDetail.setCampaignDiscountRatio(query.getString(query.getColumnIndex("DISCOUNTRATE")));
                            }
                        } catch (Exception e4) {
                            Log.e("AA", "getSalesOrderOne: ", e4);
                        }
                    }
                    if (query != null && !query.isClosed()) {
                        query.close();
                    }
                    convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                    it = it2;
                    str52 = str;
                }
            }
            return convertSalesFicheToSales;
        } catch (Throwable th2) {
            th = th2;
            cursor.close();
            throw th;
        }
    }

    
    public static void lambdagetSalesOrderOne14(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    
    public static ObservableSource lambdagetSalesInvoiceExam15(int i2) throws Exception {
        return Observable.just(Integer.valueOf(i2));
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getSalesInvoiceExam(final int i2, final ResponseListener<Sales> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda13
            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource lambdagetSalesInvoiceExam15;
                lambdagetSalesInvoiceExam15 = TigerSqlManager.lambdagetSalesInvoiceExam15(i2);
                return lambdagetSalesInvoiceExam15;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda14
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                List lambdagetSalesInvoiceExam16;
                lambdagetSalesInvoiceExam16 = TigerSqlManager.lambdagetSalesInvoiceExam16(i2, (Integer) obj);
                return lambdagetSalesInvoiceExam16;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda15
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetSalesInvoiceExam17;
                lambdagetSalesInvoiceExam17 = TigerSqlManager.lambdagetSalesInvoiceExam17((List) obj);
                return lambdagetSalesInvoiceExam17;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda16
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Sales lambdagetSalesInvoiceExam18;
                lambdagetSalesInvoiceExam18 = TigerSqlManager.this.lambdagetSalesInvoiceExam18(i2, (List) obj);
                return lambdagetSalesInvoiceExam18;
            }
        }).defaultIfEmpty(new Sales()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new NetsisSqlManagerExternalSyntheticLambda4(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda17
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                TigerSqlManager.lambdagetSalesInvoiceExam19(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    
    public static List lambdagetSalesInvoiceExam16(int i2, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Invoice.class, "LOGICALREF=?", new String[]{String.valueOf(i2)});
    }

    
    public static boolean lambdagetSalesInvoiceExam17(List list) throws Exception {
        return list != null && list.size() == 1;
    }


    public Sales lambdagetSalesInvoiceExam18(int i2, List list) throws Exception {
        List<T> table;
        Iterator<T> it;
        String str;
        String str2;
        String str3;
        String str4;
        String stringResource;
        String str5;
        new Sales();
        Sales convertSalesFicheToSales = ((Invoice) list.get(0)).convertSalesFicheToSales();
        Cursor cursor = null;
        try {
            cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_sales_invoice_edit), StringUtils.convertIntToString(i2));
            if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("AMBAR")));
                FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("IADEAMBAR")));
                FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("ISYERI")));
                FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("FABRIKA")));
                FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("BOLUM")));
                FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("ODEMEPLAN")));
                convertSalesFicheToSales.getTradeGroup().setLogicalRef(cursor.getInt(cursor.getColumnIndex("TREF")));
                convertSalesFicheToSales.setNotUseGattribKdv(CalculateUtils.isGattribNotUseKdv(cursor.getInt(cursor.getColumnIndex("GATTRIB"))));
                convertSalesFicheToSales.getSpeCode().setLogicalRef(cursor.getInt(cursor.getColumnIndex("SREF")));
                convertSalesFicheToSales.getWarrantyCode().setLogicalRef(cursor.getInt(cursor.getColumnIndex("WREF")));
                FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("TITLE")));
                try {
                    try {
                        FicheStringProp.setDefinition(getShipAddressDefinition(convertSalesFicheToSales.getShipAddress().getLogicalRef()));
                        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("SHPCODE")));
                        convertSalesFicheToSales.getShipAgent().setLogicalRef(cursor.getInt(cursor.getColumnIndex("SHPREF")));
                        int i3 = 0;
                        while (i3 < convertSalesFicheToSales.getDiscountLength()) {
                            FicheDiscountRefProp discountCard = convertSalesFicheToSales.getDiscountCard(i3);
                            int i4 = i3 + 1;
                            String sb = "D" +
                                    i4;
                            FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(sb)));
                            convertSalesFicheToSales.getDiscountCard(i3).setLogicalRef(cursor.getInt(cursor.getColumnIndex("C" + i4)));
                            i3 = i4;
                        }
                        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("PROJE")));
                        convertSalesFicheToSales.getCabin().setCode(cursor.getString(cursor.getColumnIndex("CBNCODE")));
                        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("CBNNAME")));
                        convertSalesFicheToSales.setCampaingRefs(cursor.getString(cursor.getColumnIndex("CAMPAIGNREFS")));
                    } catch (Throwable th) {
                        th = th;
                        cursor.close();
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    Log.e("AA", "getSalesOrderOne: ", e);
                    cursor.close();
                    table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(InvoiceDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(i2)});
                    if (!ErpCreator.getInstance().getmBaseErp().getUseProductNameDescription()) {
                    }
                    if (table != null) {
                        it = table.iterator();
                        String str6 = "";
                        while (it.hasNext()) {
                        }
                    }
                    return convertSalesFicheToSales;
                }
            }
        } catch (Exception e3) {
            e = e3;
        } catch (Throwable th2) {
            th = th2;
            cursor.close();
            throw th;
        }
        cursor.close();
        table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(InvoiceDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(i2)});
        String str7 = !ErpCreator.getInstance().getmBaseErp().getUseProductNameDescription() ? "I.NAME2" : "I.NAME";
        if (table != null && table.size() > 0) {
            it = table.iterator();
            String str62 = "";
            while (it.hasNext()) {
                InvoiceDetail invoiceDetail = (InvoiceDetail) it.next();
                if (invoiceDetail.lineType == LineType.SERVICE.value) {
                    str5 = ContextUtils.getStringResource(R.string.table_service);
                    stringResource = ContextUtils.getStringResource(R.string.table_service_units);
                    str4 = "0 AS ISVARYANT";
                    str3 = "0 AS LOCTRACKING";
                    str2 = "0 AS DIVUNIT";
                    str = str62;
                } else {
                    String stringResource2 = ContextUtils.getStringResource(R.string.table_item);
                    str = "LEFT JOIN UNITS UNT ON UNT.LOGICALREF=U.LOGICALREF";
                    str2 = "UNT.DIVUNIT AS DIVUNIT";
                    str3 = "I.LOCTRACKING AS LOCTRACKING";
                    str4 = "I.ISVARYANT AS ISVARYANT";
                    stringResource = ContextUtils.getStringResource(R.string.table_item_units);
                    str5 = stringResource2;
                }
                Iterator<T> it2 = it;
                Cursor query = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.qry_get_sales_invoice_detail_edit, str5, stringResource, str4, str3, str7, str2, str), StringUtils.convertIntToString(invoiceDetail.logicalRef));
                SalesDetail convertSalesFicheDetailToSalesDetail = invoiceDetail.convertSalesFicheDetailToSalesDetail();
                if (query != null) {
                    try {
                        if (query.getCount() > 0 && query.moveToFirst()) {
                            convertSalesFicheDetailToSalesDetail.setName(query.getString(query.getColumnIndex("ITEMNAME")));
                            convertSalesFicheDetailToSalesDetail.setCode(query.getString(query.getColumnIndex("ITEMCODE")));
                            convertSalesFicheDetailToSalesDetail.setCardType(query.getInt(query.getColumnIndex("CARDTYPE")));
                            convertSalesFicheDetailToSalesDetail.setTrackType(query.getInt(query.getColumnIndex("TRACKTYPE")));
                            convertSalesFicheDetailToSalesDetail.getUnit().setCode(query.getString(query.getColumnIndex("UUCODE")));
                            FicheStringProp.setDefinition(query.getString(query.getColumnIndex("UCODE")));
                            FicheStringProp.setDefinition(query.getString(query.getColumnIndex("ODEMEPLAN")));
                            convertSalesFicheDetailToSalesDetail.getSpeCode().setLogicalRef(query.getInt(query.getColumnIndex("SREF")));
                            FicheStringProp.setDefinition(query.getString(query.getColumnIndex("DELIVERYCODE")));
                            int i5 = 0;
                            while (i5 < convertSalesFicheDetailToSalesDetail.getDiscountLength()) {
                                FicheDiscountRefProp discountCard2 = convertSalesFicheDetailToSalesDetail.getDiscountCard(i5);
                                int i6 = i5 + 1;
                                String sb2 = "D" +
                                        i6;
                                FicheStringProp.setDefinition(query.getString(query.getColumnIndex(sb2)));
                                convertSalesFicheDetailToSalesDetail.getDiscountCard(i5).setLogicalRef(query.getInt(query.getColumnIndex("C" + i6)));
                                i5 = i6;
                            }
                            FicheStringProp.setDefinition(query.getString(query.getColumnIndex("VNAME")));
                            convertSalesFicheDetailToSalesDetail.getVariant().setCode(query.getString(query.getColumnIndex("VCODE")));
                            convertSalesFicheDetailToSalesDetail.setHasVariant(StringUtils.convertIntToBoolean(query.getInt(query.getColumnIndex("ISVARYANT"))));
                            convertSalesFicheDetailToSalesDetail.setLocTracking(StringUtils.convertIntToBoolean(query.getInt(query.getColumnIndex("LOCTRACKING"))));
                            boolean z = query.getInt(query.getColumnIndex("DIVUNIT")) == 1;
                            convertSalesFicheDetailToSalesDetail.setDivUnit(z);
                            convertSalesFicheDetailToSalesDetail.setBarcodeCount(query.getInt(query.getColumnIndex("BARCODECOUNT")));
                            convertSalesFicheDetailToSalesDetail.setCampaignDiscountRatio(query.getString(query.getColumnIndex("DISCOUNTRATE")));
                            Cursor query2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_sales_serilot_detail_edit), StringUtils.convertIntToString(convertSalesFicheDetailToSalesDetail.getLogicalRef()), "0");
                            if (query2 != null) {
                                try {
                                    try {
                                        if (query2.getCount() > 0) {
                                            for (int i7 = 0; i7 < query2.getCount(); i7++) {
                                                if (query2.moveToPosition(i7)) {
                                                    Serilot serilot = new Serilot();
                                                    serilot.logicalRef = query2.getInt(query2.getColumnIndex("LOGICALREF"));
                                                    serilot.detailRef = query2.getInt(query2.getColumnIndex("DETAILREF"));
                                                    serilot.unit = query2.getString(query2.getColumnIndex("UNIT"));
                                                    serilot.code = query2.getString(query2.getColumnIndex("CODE"));
                                                    serilot.amount = query2.getDouble(query2.getColumnIndex("AMOUNT"));
                                                    serilot.expDate = query2.getString(query2.getColumnIndex("EXPDATE"));
                                                    serilot.grpBegCode = query2.getString(query2.getColumnIndex("GRP_BEG_CODE"));
                                                    serilot.grpEndCode = query2.getString(query2.getColumnIndex("GRP_END_CODE"));
                                                    serilot.locationCode = query2.getString(query2.getColumnIndex("LOCATIONCODE"));
                                                    serilot.stTransRef = query2.getInt(query2.getColumnIndex("STTRANSREF"));
                                                    serilot.mainUnitRef = query2.getInt(query2.getColumnIndex("MAINUNITREF"));
                                                    serilot.sourceUnitRef = query2.getInt(query2.getColumnIndex("SOURCEUNITREF"));
                                                    convertSalesFicheDetailToSalesDetail.getSalesSerialLots().add(serilot);
                                                }
                                            }
                                        }
                                    } catch (Exception e4) {
                                        Log.e(TAG, "getSalesInvoiceExam:  ", e4);
                                    }
                                } catch (Throwable th3) {
                                    query2.close();
                                    throw th3;
                                }
                            }
                            query2.close();
                        }
                    } catch (Exception e5) {
                        Log.e("AA", "getSalesInvoiceExam: ", e5);
                    }
                }
                if (query != null && !query.isClosed()) {
                    query.close();
                }
                convertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(convertSalesFicheDetailToSalesDetail.getSalesSerialLots(), convertSalesFicheDetailToSalesDetail.getTrackType()));
                convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                it = it2;
                str62 = str;
            }
        }
        return convertSalesFicheToSales;
    }

    
    public static void lambdagetSalesInvoiceExam19(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getCashCreditFiche(int i2, int i3, ResponseListener<CashCreditFiche> responseListener) {
        getCashCreditFiche(i2, i3, responseListener, false);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getCashCreditFiche(final int i2, final int i3, final ResponseListener<CashCreditFiche> responseListener, boolean z) {
        final String str = z ? "INVOICEREF" : "LOGICALREF";
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda23
            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource lambdagetCashCreditFiche20;
                lambdagetCashCreditFiche20 = TigerSqlManager.lambdagetCashCreditFiche20(i2);
                return lambdagetCashCreditFiche20;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda24
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                List lambdagetCashCreditFiche21;
                lambdagetCashCreditFiche21 = TigerSqlManager.lambdagetCashCreditFiche21(str, i2, i3, (Integer) obj);
                return lambdagetCashCreditFiche21;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda25
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetCashCreditFiche22;
                lambdagetCashCreditFiche22 = TigerSqlManager.lambdagetCashCreditFiche22((List) obj);
                return lambdagetCashCreditFiche22;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda26
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                CashCreditFiche lambdagetCashCreditFiche23;
                lambdagetCashCreditFiche23 = TigerSqlManager.lambdagetCashCreditFiche23(i3, i2, (List) obj);
                return lambdagetCashCreditFiche23;
            }
        }).defaultIfEmpty(new CashCreditFiche()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new NetsisSqlManagerExternalSyntheticLambda10(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda27
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                TigerSqlManager.lambdagetCashCreditFiche24(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    
    public static ObservableSource lambdagetCashCreditFiche20(int i2) throws Exception {
        return Observable.just(Integer.valueOf(i2));
    }

    
    public static List lambdagetCashCreditFiche21(String str, int i2, int i3, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(CashCredit.class, str + "=? AND FTYPE=?", new String[]{String.valueOf(i2), String.valueOf(i3)});
    }

    
    public static boolean lambdagetCashCreditFiche22(List list) throws Exception {
        return list != null && list.size() == 1;
    }

    
    public static CashCreditFiche lambdagetCashCreditFiche23(int i2, int i3, List list) throws Exception {
        new CashCreditFiche();
        CashCredit cashCredit = (CashCredit) list.get(0);
        CashCreditFiche convertFicheToCashCreditFiche = cashCredit.convertFicheToCashCreditFiche();
        Cursor cursor = null;
        try {
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_cashcredit_fiche_edit), StringUtils.convertIntToString(cashCredit.logicalRef), StringUtils.convertIntToString(i2));
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("ISYERI")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("BOLUM")));
                    convertFicheToCashCreditFiche.getTradinggrp().setLogicalRef(cursor.getInt(cursor.getColumnIndex("TREF")));
                    convertFicheToCashCreditFiche.getSpecode().setLogicalRef(cursor.getInt(cursor.getColumnIndex("SREF")));
                    convertFicheToCashCreditFiche.getCyphcode().setLogicalRef(cursor.getInt(cursor.getColumnIndex("WREF")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("PROJE")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("BANKDEF")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("BANKACCDEF")));
                }
            } catch (Exception e2) {
                Log.e("AA", "getSalesOrderOne: ", e2);
            }
            for (T t : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(CashCreditDetail.class, "CASHCREDITID=?", new String[]{String.valueOf(i3)})) {
                CashCreditFicheDetail convertFicheDetailToCashCreditFicheDetail = t.convertFicheDetailToCashCreditFicheDetail();
                Cursor query = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.qry_get_cashcreditdetail_fiche_edit), StringUtils.convertIntToString(t.logicalRef));
                if (query != null) {
                    try {
                        try {
                            if (query.getCount() > 0 && query.moveToFirst()) {
                                FicheStringProp.setDefinition(query.getString(query.getColumnIndex("ODEMEPLAN")));
                            }
                        } catch (Exception e3) {
                            Log.e("AA", "getSalesOrderOne: ", e3);
                        }
                    } finally {
                        query.close();
                    }
                }
                convertFicheToCashCreditFiche.addNewDetail(convertFicheDetailToCashCreditFicheDetail);
            }
            return convertFicheToCashCreditFiche;
        } finally {
            cursor.close();
        }
    }

    
    public static void lambdagetCashCreditFiche24(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getCaseFiche(int i2, ResponseListener<CaseFiche> responseListener) {
        getCaseFiche(i2, responseListener, false);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getCaseFiche(final int i2, final ResponseListener<CaseFiche> responseListener, boolean z) {
        final String str = z ? "INVOICEREF" : "LOGICALREF";
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda28
            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource lambdagetCaseFiche25;
                lambdagetCaseFiche25 = TigerSqlManager.lambdagetCaseFiche25(i2);
                return lambdagetCaseFiche25;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda29
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                List lambdagetCaseFiche26;
                lambdagetCaseFiche26 = TigerSqlManager.lambdagetCaseFiche26(str, i2, (Integer) obj);
                return lambdagetCaseFiche26;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda30
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetCaseFiche27;
                lambdagetCaseFiche27 = TigerSqlManager.lambdagetCaseFiche27((List) obj);
                return lambdagetCaseFiche27;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda31
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                CaseFiche lambdagetCaseFiche28;
                lambdagetCaseFiche28 = TigerSqlManager.lambdagetCaseFiche28((List) obj);
                return lambdagetCaseFiche28;
            }
        }).defaultIfEmpty(new CaseFiche()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new NetsisSqlManagerExternalSyntheticLambda39(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda32
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                TigerSqlManager.lambdagetCaseFiche29(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    
    public static ObservableSource lambdagetCaseFiche25(int i2) throws Exception {
        return Observable.just(Integer.valueOf(i2));
    }

    
    public static List lambdagetCaseFiche26(String str, int i2, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(CaseCash.class, str + "=?", new String[]{String.valueOf(i2)});
    }

    
    public static boolean lambdagetCaseFiche27(List list) throws Exception {
        return list != null && list.size() == 1;
    }

    
    public static CaseFiche lambdagetCaseFiche28(List list) throws Exception {
        new CaseFiche();
        CaseFiche convertFicheToCaseFiche = ((CaseCash) list.get(0)).convertFicheToCaseFiche();
        Cursor cursor = null;
        try {
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_case_fiche_edit), StringUtils.convertIntToString(convertFicheToCaseFiche.getLogicalRef()));
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("ISYERI")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("BOLUM")));
                    convertFicheToCaseFiche.getTradinggrp().setLogicalRef(cursor.getInt(cursor.getColumnIndex("TREF")));
                    convertFicheToCaseFiche.getSpecode().setLogicalRef(cursor.getInt(cursor.getColumnIndex("SREF")));
                    convertFicheToCaseFiche.getCyphcode().setLogicalRef(cursor.getInt(cursor.getColumnIndex("WREF")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("PROJE")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("KASA")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("CURRCODE")));
                }
            } catch (Exception e2) {
                Log.e("AA", "getSalesOrderOne: ", e2);
            }
            return convertFicheToCaseFiche;
        } finally {
            cursor.close();
        }
    }

    
    public static void lambdagetCaseFiche29(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getChequeDeedFiche(int i2, int i3, ResponseListener<ChequeDeedFiche> responseListener) {
        getChequeDeedFiche(i2, i3, responseListener, false);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getChequeDeedFiche(final int i2, final int i3, final ResponseListener<ChequeDeedFiche> responseListener, boolean z) {
        final String str = z ? "INVOICEREF" : "LOGICALREF";
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda18
            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource lambdagetChequeDeedFiche30;
                lambdagetChequeDeedFiche30 = TigerSqlManager.lambdagetChequeDeedFiche30(i2);
                return lambdagetChequeDeedFiche30;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda19
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                List lambdagetChequeDeedFiche31;
                lambdagetChequeDeedFiche31 = TigerSqlManager.lambdagetChequeDeedFiche31(str, i2, i3, (Integer) obj);
                return lambdagetChequeDeedFiche31;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda20
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetChequeDeedFiche32;
                lambdagetChequeDeedFiche32 = TigerSqlManager.lambdagetChequeDeedFiche32((List) obj);
                return lambdagetChequeDeedFiche32;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda21
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ChequeDeedFiche lambdagetChequeDeedFiche33;
                lambdagetChequeDeedFiche33 = TigerSqlManager.lambdagetChequeDeedFiche33(i3, (List) obj);
                return lambdagetChequeDeedFiche33;
            }
        }).defaultIfEmpty(new ChequeDeedFiche()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new NetsisSqlManagerExternalSyntheticLambda28(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda22
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                TigerSqlManager.lambdagetChequeDeedFiche34(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    
    public static ObservableSource lambdagetChequeDeedFiche30(int i2) throws Exception {
        return Observable.just(Integer.valueOf(i2));
    }

    
    public static List lambdagetChequeDeedFiche31(String str, int i2, int i3, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Chequedeed.class, str + "=? AND FTYPE=?", new String[]{String.valueOf(i2), String.valueOf(i3)});
    }

    
    public static boolean lambdagetChequeDeedFiche32(List list) throws Exception {
        return list != null && list.size() == 1;
    }

    
    public static ChequeDeedFiche lambdagetChequeDeedFiche33(int i2, List list) throws Exception {
        new ChequeDeedFiche();
        ChequeDeedFiche convertFicheToCashCreditFiche = ((Chequedeed) list.get(0)).convertFicheToCashCreditFiche();
        Cursor cursor = null;
        try {
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_chequedeed_fiche_edit), StringUtils.convertIntToString(convertFicheToCashCreditFiche.getLogicalRef()), StringUtils.convertIntToString(i2));
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("ISYERI")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("BOLUM")));
                    convertFicheToCashCreditFiche.getTradinggrp().setLogicalRef(cursor.getInt(cursor.getColumnIndex("TREF")));
                    convertFicheToCashCreditFiche.getSpecode().setLogicalRef(cursor.getInt(cursor.getColumnIndex("SREF")));
                    convertFicheToCashCreditFiche.getCyphcode().setLogicalRef(cursor.getInt(cursor.getColumnIndex("WREF")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("PROJE")));
                }
            } catch (Exception e2) {
                Log.e("AA", "getSalesOrderOne: ", e2);
            }
            Iterator<T> it = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ChequedeedDetail.class, "CHEQUEDEEDID=?", new String[]{String.valueOf(convertFicheToCashCreditFiche.getLogicalRef())}).iterator();
            while (it.hasNext()) {
                convertFicheToCashCreditFiche.addNewDetail(((ChequedeedDetail) it.next()).convertFicheDetailToCashCreditFicheDetail());
            }
            return convertFicheToCashCreditFiche;
        } finally {
            cursor.close();
        }
    }

    
    public static void lambdagetChequeDeedFiche34(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    public List<Sales> mapCursorToSalesOrderTest(int i2, int i3) {
        List<T> table;
        List<T> table2;
        Object obj;
        String stringResource;
        String str;
        Object obj2;
        String str2;
        boolean z;
        String str3 = "D";
        boolean z2 = true;
        if (i2 != -1) {
            ISqlHelper logoSqlHelper = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
            String sb = "ORDERTYPE=? " +
                    (i3 == 1 ? "" : " AND CLREF > 0") +
                    " AND LOGICALREF=? ";
            table = logoSqlHelper.getTable(Order.class, sb, new String[]{String.valueOf(i3), String.valueOf(i2)});
        } else {
            ISqlHelper logoSqlHelper2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
            String sb2 = "ORDERTYPE=? AND (ISTRANSFER<>? OR ISEDIT=1) " +
                    (i3 == 1 ? "" : " AND CLREF > 0");
            table = logoSqlHelper2.getTable(Order.class, sb2, new String[]{String.valueOf(i3), String.valueOf(1)});
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = table.iterator();
        while (it.hasNext()) {
            Order order = (Order) it.next();
            Sales convertSalesFicheToSales = order.convertSalesFicheToSales();
            Cursor cursor = null;
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_sales_order_edit), StringUtils.convertIntToString(order.logicalRef));
            } catch (Exception unused) {
            } catch (Throwable th) {
                th = th;
            }
            if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                convertSalesFicheToSales.setClCode(cursor.getString(cursor.getColumnIndex("CCCODE")));
                FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("AMBAR")));
                FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("KARSILAMAAMBARI")));
                FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("ISYERI")));
                FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("FABRIKA")));
                FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("BOLUM")));
                FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("ODEMEPLAN")));
                convertSalesFicheToSales.getTradeGroup().setLogicalRef(cursor.getInt(cursor.getColumnIndex("TREF")));
                convertSalesFicheToSales.getSpeCode().setLogicalRef(cursor.getInt(cursor.getColumnIndex("SREF")));
                convertSalesFicheToSales.getWarrantyCode().setLogicalRef(cursor.getInt(cursor.getColumnIndex("WREF")));
                FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("TITLE")));
                try {
                    FicheStringProp.setDefinition(getShipAddressDefinition(convertSalesFicheToSales.getShipAddress().getLogicalRef()));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("SHPCODE")));
                    convertSalesFicheToSales.getShipAgent().setLogicalRef(cursor.getInt(cursor.getColumnIndex("SHPREF")));
                    int i4 = 0;
                    while (i4 < convertSalesFicheToSales.getDiscountLength()) {
                        FicheDiscountRefProp discountCard = convertSalesFicheToSales.getDiscountCard(i4);
                        int i5 = i4 + 1;
                        String sb3 = str3 +
                                i5;
                        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(sb3)));
                        convertSalesFicheToSales.getDiscountCard(i4).setLogicalRef(cursor.getInt(cursor.getColumnIndex("C" + i5)));
                        i4 = i5;
                    }
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("PROJE")));
                    convertSalesFicheToSales.setTrRate(cursor.getDouble(cursor.getColumnIndex("TRRATE")));
                    convertSalesFicheToSales.setTrCurr(cursor.getInt(cursor.getColumnIndex("TRCURR")));
                    convertSalesFicheToSales.getErpInvoiceType().setLogicalRef(cursor.getInt(cursor.getColumnIndex("ERPINVOICETYPE")));
                } catch (Exception unused2) {
                } catch (Throwable th2) {
                    th = th2;
                    cursor.close();
                    throw th;
                }
                cursor.close();
                table2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(OrderDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(order.logicalRef)});
                String str4 = !ErpCreator.getInstance().getmBaseErp().getUseProductNameDescription() ? "I.NAME2" : "I.NAME";
                if (table2 != null && table2.size() > 0) {
                    Object obj3 = "";
                    for (T t : table2) {
                        if (t.lineType == LineType.SERVICE.value) {
                            String stringResource2 = ContextUtils.getStringResource(R.string.table_service);
                            obj = "0 AS DIVUNIT";
                            obj2 = "0 AS ISVARYANT";
                            stringResource = ContextUtils.getStringResource(R.string.table_service_units);
                            str = stringResource2;
                        } else {
                            String stringResource3 = ContextUtils.getStringResource(R.string.table_item);
                            obj = "UNT.DIVUNIT AS DIVUNIT";
                            stringResource = ContextUtils.getStringResource(R.string.table_item_units);
                            str = stringResource3;
                            obj3 = "LEFT JOIN UNITS UNT ON UNT.LOGICALREF=U.LOGICALREF";
                            obj2 = "I.ISVARYANT AS ISVARYANT";
                        }
                        Iterator<T> it2 = it;
                        Cursor query = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.qry_get_sales_order_detail_edit, str, stringResource, obj2, str4, obj, obj3), StringUtils.convertIntToString(t.logicalRef));
                        SalesDetail convertSalesFicheDetailToSalesDetail = t.convertSalesFicheDetailToSalesDetail();
                        if (query != null) {
                            if (query.getCount() > 0 && query.moveToFirst()) {
                                convertSalesFicheDetailToSalesDetail.setCode(query.getString(query.getColumnIndex("ITEMCODE")));
                                convertSalesFicheDetailToSalesDetail.setName(query.getString(query.getColumnIndex("ITEMNAME")));
                                convertSalesFicheDetailToSalesDetail.setCardType(query.getInt(query.getColumnIndex("CARDTYPE")));
                                convertSalesFicheDetailToSalesDetail.setTrackType(query.getInt(query.getColumnIndex("TRACKTYPE")));
                                convertSalesFicheDetailToSalesDetail.getUnit().setCode(query.getString(query.getColumnIndex("UUCODE")));
                                FicheStringProp.setDefinition(query.getString(query.getColumnIndex("UCODE")));
                                FicheStringProp.setDefinition(query.getString(query.getColumnIndex("ODEMEPLAN")));
                                convertSalesFicheDetailToSalesDetail.getSpeCode().setLogicalRef(query.getInt(query.getColumnIndex("SREF")));
                                int i6 = 0;
                                while (i6 < convertSalesFicheDetailToSalesDetail.getDiscountLength()) {
                                    FicheDiscountRefProp discountCard2 = convertSalesFicheDetailToSalesDetail.getDiscountCard(i6);
                                    StringBuilder sb4 = new StringBuilder();
                                    sb4.append(str3);
                                    str2 = str3;
                                    int i7 = i6 + 1;
                                    try {
                                        sb4.append(i7);
                                        FicheStringProp.setDefinition(query.getString(query.getColumnIndex(sb4.toString())));
                                        convertSalesFicheDetailToSalesDetail.getDiscountCard(i6).setLogicalRef(query.getInt(query.getColumnIndex("C" + i7)));
                                        i6 = i7;
                                        str3 = str2;
                                    } catch (Exception unused3) {
                                    }
                                }
                                str2 = str3;
                                FicheStringProp.setDefinition(query.getString(query.getColumnIndex("VNAME")));
                                convertSalesFicheDetailToSalesDetail.setHasVariant(StringUtils.convertIntToBoolean(query.getInt(query.getColumnIndex("ISVARYANT"))));
                                convertSalesFicheDetailToSalesDetail.getVariant().setCode(query.getString(query.getColumnIndex("VCODE")));
                                z = true;
                                try {
                                    convertSalesFicheDetailToSalesDetail.setDivUnit(query.getInt(query.getColumnIndex("DIVUNIT")) == 1);
                                    convertSalesFicheDetailToSalesDetail.setBarcodeCount(query.getInt(query.getColumnIndex("BARCODECOUNT")));
                                    convertSalesFicheDetailToSalesDetail.setCampaignDiscountRatio(query.getString(query.getColumnIndex("DISCOUNTRATE")));
                                } catch (Exception unused4) {
                                }
                                if (query != null && !query.isClosed()) {
                                    query.close();
                                }
                                convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                                it = it2;
                                z2 = z;
                                str3 = str2;
                            }
                        }
                        str2 = str3;
                        z = true;
                        if (query != null) {
                            query.close();
                        }
                        convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                        it = it2;
                        z2 = z;
                        str3 = str2;
                    }
                }
                arrayList.add(convertSalesFicheToSales);
                it = it;
                z2 = z2;
                str3 = str3;
            }
            cursor.close();
            table2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(OrderDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(order.logicalRef)});
            if (!ErpCreator.getInstance().getmBaseErp().getUseProductNameDescription()) {
            }
            if (table2 != null) {
                Object obj32 = "";
                while (r6.hasNext()) {
                }
            }
            arrayList.add(convertSalesFicheToSales);
            it = it;
            z2 = z2;
            str3 = str3;
        }
        return arrayList;
    }

    public List<Sales> mapCursorToInvoice(SalesType salesType, int i2, int i3, int i4) {
        List<T> table;
        int i5;
        Object obj;
        Object obj2;
        String stringResource;
        String stringResource2;
        String str;
        Object obj3;
        String str2;
        String str3;
        String str4;
        String str5 = "D";
        String str6 = "SREF";
        String str7 = "ODEMEPLAN";
        int i6 = 0;
        List<T> table2 = i4 == -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Invoice.class, "FTYPE=? AND INVTYPE=? AND ISTRANSFER<>? AND CLREF>?", new String[]{String.valueOf(i2), String.valueOf(i3), String.valueOf(1), String.valueOf(0)}) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Invoice.class, "FTYPE=? AND INVTYPE=? AND LOGICALREF=? AND CLREF>?", new String[]{String.valueOf(i2), String.valueOf(i3), String.valueOf(i4), String.valueOf(0)});
        ArrayList arrayList = new ArrayList();
        for (T t : table2) {
            Sales convertSalesFicheToSales = t.convertSalesFicheToSales();
            Cursor cursor = null;
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_sales_invoice_edit), StringUtils.convertIntToString(t.logicalRef));
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    convertSalesFicheToSales.setClCode(cursor.getString(cursor.getColumnIndex("CCCODE")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("AMBAR")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("IADEAMBAR")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("ISYERI")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("FABRIKA")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("BOLUM")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(str7)));
                    convertSalesFicheToSales.getTradeGroup().setLogicalRef(cursor.getInt(cursor.getColumnIndex("TREF")));
                    convertSalesFicheToSales.getSpeCode().setLogicalRef(cursor.getInt(cursor.getColumnIndex(str6)));
                    convertSalesFicheToSales.getWarrantyCode().setLogicalRef(cursor.getInt(cursor.getColumnIndex("WREF")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("TITLE")));
                    try {
                        try {
                            FicheStringProp.setDefinition(getShipAddressDefinition(convertSalesFicheToSales.getShipAddress().getLogicalRef()));
                            FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("SHPCODE")));
                            convertSalesFicheToSales.getShipAgent().setLogicalRef(cursor.getInt(cursor.getColumnIndex("SHPREF")));
                            int i7 = i6;
                            while (i7 < convertSalesFicheToSales.getDiscountLength()) {
                                FicheDiscountRefProp discountCard = convertSalesFicheToSales.getDiscountCard(i7);
                                int i8 = i7 + 1;
                                String sb = str5 +
                                        i8;
                                FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(sb)));
                                convertSalesFicheToSales.getDiscountCard(i7).setLogicalRef(cursor.getInt(cursor.getColumnIndex("C" + i8)));
                                i7 = i8;
                            }
                            FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("PROJE")));
                            convertSalesFicheToSales.getCabin().setCode(cursor.getString(cursor.getColumnIndex("CBNCODE")));
                            FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("CBNNAME")));
                            convertSalesFicheToSales.setTrRate(cursor.getDouble(cursor.getColumnIndex("TRRATE")));
                            convertSalesFicheToSales.setTrCurr(cursor.getInt(cursor.getColumnIndex("TRCURR")));
                            convertSalesFicheToSales.getErpInvoiceType().setLogicalRef(cursor.getInt(cursor.getColumnIndex("ERPINVOICETYPE")));
                            convertSalesFicheToSales.setCampaingRefs(cursor.getString(cursor.getColumnIndex("CAMPAIGNREFS")));
                        } catch (Throwable th) {
                            th = th;
                            cursor.close();
                            throw th;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        Log.e("AA", "getSalesOrderOne: ", e);
                        cursor.close();
                        table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(InvoiceDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(t.logicalRef)});
                        if (!ErpCreator.getInstance().getmBaseErp().getUseProductNameDescription()) {
                        }
                        if (table != null) {
                            String str8 = "";
                            while (r8.hasNext()) {
                            }
                        }
                        String str9 = str5;
                        String str10 = str6;
                        String str11 = str7;
                        convertSalesFicheToSales.setMSalesType(salesType.getmValue());
                        arrayList.add(convertSalesFicheToSales);
                        if (convertSalesFicheToSales.getEDespatch().isSelect()) {
                        }
                        i6 = i5;
                        str5 = str9;
                        str6 = str10;
                        str7 = str11;
                    }
                }
            } catch (Exception e3) {
                e = e3;
            } catch (Throwable th2) {
                th = th2;
            }
            cursor.close();
            table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(InvoiceDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(t.logicalRef)});
            String str12 = !ErpCreator.getInstance().getmBaseErp().getUseProductNameDescription() ? "I.NAME2" : "I.NAME";
            if (table != null && table.size() > 0) {
                String str82 = "";
                for (T t2 : table) {
                    if (t2.lineType == LineType.SERVICE.value) {
                        String stringResource3 = ContextUtils.getStringResource(R.string.table_service);
                        obj = "0 AS LOCTRACKING";
                        obj2 = "0 AS DIVUNIT";
                        stringResource2 = ContextUtils.getStringResource(R.string.table_service_units);
                        obj3 = "0 AS ISVARYANT";
                        stringResource = stringResource3;
                        str = str82;
                    } else {
                        obj = "I.LOCTRACKING AS LOCTRACKING";
                        obj2 = "UNT.DIVUNIT AS DIVUNIT";
                        stringResource = ContextUtils.getStringResource(R.string.table_item);
                        stringResource2 = ContextUtils.getStringResource(R.string.table_item_units);
                        str = "LEFT JOIN UNITS UNT ON UNT.LOGICALREF=U.LOGICALREF";
                        obj3 = "I.ISVARYANT AS ISVARYANT";
                    }
                    String str13 = str12;
                    Cursor query = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.qry_get_sales_invoice_detail_edit, stringResource, stringResource2, obj3, obj, str12, obj2, str), StringUtils.convertIntToString(t2.logicalRef));
                    SalesDetail convertSalesFicheDetailToSalesDetail = t2.convertSalesFicheDetailToSalesDetail();
                    if (query != null) {
                        try {
                        } catch (Exception e4) {
                            e = e4;
                            str2 = str5;
                        }
                        if (query.getCount() > 0 && query.moveToFirst()) {
                            convertSalesFicheDetailToSalesDetail.setCode(query.getString(query.getColumnIndex("ITEMCODE")));
                            convertSalesFicheDetailToSalesDetail.setName(query.getString(query.getColumnIndex("ITEMNAME")));
                            convertSalesFicheDetailToSalesDetail.setCardType(query.getInt(query.getColumnIndex("CARDTYPE")));
                            convertSalesFicheDetailToSalesDetail.setTrackType(query.getInt(query.getColumnIndex("TRACKTYPE")));
                            convertSalesFicheDetailToSalesDetail.getUnit().setCode(query.getString(query.getColumnIndex("UUCODE")));
                            FicheStringProp.setDefinition(query.getString(query.getColumnIndex("UCODE")));
                            FicheStringProp.setDefinition(query.getString(query.getColumnIndex(str7)));
                            convertSalesFicheDetailToSalesDetail.getSpeCode().setLogicalRef(query.getInt(query.getColumnIndex(str6)));
                            int i9 = 0;
                            while (i9 < convertSalesFicheDetailToSalesDetail.getDiscountLength()) {
                                FicheDiscountRefProp discountCard2 = convertSalesFicheDetailToSalesDetail.getDiscountCard(i9);
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(str5);
                                str2 = str5;
                                int i10 = i9 + 1;
                                try {
                                    sb2.append(i10);
                                    FicheStringProp.setDefinition(query.getString(query.getColumnIndex(sb2.toString())));
                                    convertSalesFicheDetailToSalesDetail.getDiscountCard(i9).setLogicalRef(query.getInt(query.getColumnIndex("C" + i10)));
                                    i9 = i10;
                                    str5 = str2;
                                } catch (Exception e5) {
                                    e = e5;
                                    str4 = str6;
                                    str3 = str7;
                                    Log.e("AA", "getSalesInvoiceExam: ", e);
                                    if (query == null) {
                                    }
                                    convertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(convertSalesFicheDetailToSalesDetail.getSalesSerialLots(), convertSalesFicheDetailToSalesDetail.getTrackType()));
                                    convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                                    str12 = str13;
                                    str82 = str;
                                    str5 = str2;
                                    str6 = str4;
                                    str7 = str3;
                                }
                            }
                            str2 = str5;
                            FicheStringProp.setDefinition(query.getString(query.getColumnIndex("VNAME")));
                            convertSalesFicheDetailToSalesDetail.getVariant().setCode(query.getString(query.getColumnIndex("VCODE")));
                            convertSalesFicheDetailToSalesDetail.setHasVariant(StringUtils.convertIntToBoolean(query.getInt(query.getColumnIndex("ISVARYANT"))));
                            convertSalesFicheDetailToSalesDetail.setLocTracking(StringUtils.convertIntToBoolean(query.getInt(query.getColumnIndex("LOCTRACKING"))));
                            convertSalesFicheDetailToSalesDetail.setDivUnit(query.getInt(query.getColumnIndex("DIVUNIT")) == 1);
                            convertSalesFicheDetailToSalesDetail.setBarcodeCount(query.getInt(query.getColumnIndex("BARCODECOUNT")));
                            convertSalesFicheDetailToSalesDetail.setCampaignDiscountRatio(query.getString(query.getColumnIndex("DISCOUNTRATE")));
                            Cursor query2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_sales_serilot_detail_edit), StringUtils.convertIntToString(convertSalesFicheDetailToSalesDetail.getLogicalRef()), "0");
                            if (query2 != null) {
                                try {
                                    if (query2.getCount() > 0) {
                                        int i11 = 0;
                                        while (i11 < query2.getCount()) {
                                            if (query2.moveToPosition(i11)) {
                                                Serilot serilot = new Serilot();
                                                serilot.logicalRef = query2.getInt(query2.getColumnIndex("LOGICALREF"));
                                                serilot.detailRef = query2.getInt(query2.getColumnIndex("DETAILREF"));
                                                serilot.unit = query2.getString(query2.getColumnIndex("UNIT"));
                                                serilot.code = query2.getString(query2.getColumnIndex("CODE"));
                                                str4 = str6;
                                                str3 = str7;
                                                try {
                                                    try {
                                                        serilot.amount = query2.getDouble(query2.getColumnIndex("AMOUNT"));
                                                        serilot.expDate = query2.getString(query2.getColumnIndex("EXPDATE"));
                                                        serilot.grpBegCode = query2.getString(query2.getColumnIndex("GRP_BEG_CODE"));
                                                        serilot.grpEndCode = query2.getString(query2.getColumnIndex("GRP_END_CODE"));
                                                        serilot.locationCode = query2.getString(query2.getColumnIndex("LOCATIONCODE"));
                                                        serilot.stTransRef = query2.getInt(query2.getColumnIndex("STTRANSREF"));
                                                        serilot.mainUnitRef = query2.getInt(query2.getColumnIndex("MAINUNITREF"));
                                                        serilot.sourceUnitRef = query2.getInt(query2.getColumnIndex("SOURCEUNITREF"));
                                                        convertSalesFicheDetailToSalesDetail.getSalesSerialLots().add(serilot);
                                                    } catch (Exception e6) {
                                                        e = e6;
                                                        Log.e(TAG, "getSalesInvoiceExam:  ", e);
                                                        query2.close();
                                                        if (query == null) {
                                                        }
                                                        convertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(convertSalesFicheDetailToSalesDetail.getSalesSerialLots(), convertSalesFicheDetailToSalesDetail.getTrackType()));
                                                        convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                                                        str12 = str13;
                                                        str82 = str;
                                                        str5 = str2;
                                                        str6 = str4;
                                                        str7 = str3;
                                                    }
                                                } catch (Throwable th3) {
                                                    th = th3;
                                                    query2.close();
                                                    throw th;
                                                }
                                            } else {
                                                str4 = str6;
                                                str3 = str7;
                                            }
                                            i11++;
                                            str6 = str4;
                                            str7 = str3;
                                        }
                                    }
                                } catch (Exception e7) {
                                    e = e7;
                                    str4 = str6;
                                    str3 = str7;
                                } catch (Throwable th4) {
                                    th = th4;
                                    str4 = str6;
                                    str3 = str7;
                                }
                            }
                            str4 = str6;
                            str3 = str7;
                            try {
                                query2.close();
                            } catch (Exception e8) {
                                e = e8;
                                Log.e("AA", "getSalesInvoiceExam: ", e);
                                if (query == null) {
                                }
                                convertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(convertSalesFicheDetailToSalesDetail.getSalesSerialLots(), convertSalesFicheDetailToSalesDetail.getTrackType()));
                                convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                                str12 = str13;
                                str82 = str;
                                str5 = str2;
                                str6 = str4;
                                str7 = str3;
                            }
                            if (query == null && !query.isClosed()) {
                                query.close();
                            }
                            convertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(convertSalesFicheDetailToSalesDetail.getSalesSerialLots(), convertSalesFicheDetailToSalesDetail.getTrackType()));
                            convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                            str12 = str13;
                            str82 = str;
                            str5 = str2;
                            str6 = str4;
                            str7 = str3;
                        }
                    }
                    str2 = str5;
                    str4 = str6;
                    str3 = str7;
                    if (query == null) {
                        query.close();
                    }
                    convertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(convertSalesFicheDetailToSalesDetail.getSalesSerialLots(), convertSalesFicheDetailToSalesDetail.getTrackType()));
                    convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                    str12 = str13;
                    str82 = str;
                    str5 = str2;
                    str6 = str4;
                    str7 = str3;
                }
            }
            String str92 = str5;
            String str102 = str6;
            String str112 = str7;
            convertSalesFicheToSales.setMSalesType(salesType.getmValue());
            arrayList.add(convertSalesFicheToSales);
            if (convertSalesFicheToSales.getEDespatch().isSelect()) {
                i5 = 0;
            } else {
                i5 = 0;
                convertSalesFicheToSales.setEDispatchAdditionalInfo((EDispatchAdditionalInfo) ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(EDispatchAdditionalInfo.class, "SALESFICHEID=? AND SALESTYPE=?", new String[]{String.valueOf(convertSalesFicheToSales.getLogicalRef()), String.valueOf(convertSalesFicheToSales.getmSalesType().getmValue())}).get(0));
            }
            i6 = i5;
            str5 = str92;
            str6 = str102;
            str7 = str112;
        }
        return arrayList;
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<CustomerNew> getNewCustomers(int i2) {
        List<T> table = i2 == -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ClCard.class, "ISTRANSFER=0", null) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ClCard.class, "ISTRANSFER=0 AND LOGICALREF=?", new String[]{String.valueOf(i2)});
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = table.iterator();
        while (it.hasNext()) {
            arrayList.add(((ClCard) it.next()).convert());
        }
        return arrayList;
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<CaseCash> getCaseCashs(int i2) {
        return i2 != -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(CaseCash.class, "ISTRANSFER=? AND LOGICALREF=? AND CLREF>?", new String[]{String.valueOf(0), String.valueOf(i2), String.valueOf(0)}) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(CaseCash.class, "ISTRANSFER=? AND CLREF>?", new String[]{String.valueOf(0), String.valueOf(0)});
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<VisitInfo> getVisits(int i2) {
        return i2 == -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTableWhere(VisitInfo.class, "AUTO=0 AND ISTRANSFER=? AND CLREF>?", new String[]{"0", "0"}) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTableWhere(VisitInfo.class, "AUTO=0 AND ISTRANSFER=? AND ID=? AND CLREF>?", new String[]{"0", String.valueOf(i2), "0"});
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<TodoInfoDb> getTodos(int i2) {
        return i2 == -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTableWhere(TodoInfoDb.class, "LATITUDE<>? AND LONGTITUDE<>? AND ISTRANSFERWORPROC=?", new String[]{"0", "0", "0"}) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTableWhere(TodoInfoDb.class, "LOGICALREF=?", new String[]{String.valueOf(i2)});
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<ChequeDeed> getChequesAndDeed(int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        for (T t : i3 == -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Chequedeed.class, "FTYPE=? AND ISTRANSFER=? AND CLREF>?", new String[]{String.valueOf(i2), String.valueOf(0), String.valueOf(0)}) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Chequedeed.class, "FTYPE=? AND ISTRANSFER=? AND LOGICALREF=? AND CLREF>?", new String[]{String.valueOf(i2), String.valueOf(0), String.valueOf(i3), String.valueOf(0)})) {
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
    public List<CashCreditX> getCreditAndCash(int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        for (T t : i3 == -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(CashCredit.class, "FTYPE=? AND ISTRANSFER=? AND CLREF>?", new String[]{String.valueOf(i2), String.valueOf(0), String.valueOf(0)}) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(CashCredit.class, "FTYPE=? AND ISTRANSFER=? AND LOGICALREF=? AND CLREF>?", new String[]{String.valueOf(i2), String.valueOf(0), String.valueOf(i3), String.valueOf(0)})) {
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

    
    public static ObservableSource lambdagetSalesWhTransfer35(int i2) throws Exception {
        return Observable.just(Integer.valueOf(i2));
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getSalesWhTransfer(final int i2, final ResponseListener<Sales> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda38
            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource lambdagetSalesWhTransfer35;
                lambdagetSalesWhTransfer35 = TigerSqlManager.lambdagetSalesWhTransfer35(i2);
                return lambdagetSalesWhTransfer35;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda39
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                List lambdagetSalesWhTransfer36;
                lambdagetSalesWhTransfer36 = TigerSqlManager.lambdagetSalesWhTransfer36(i2, (Integer) obj);
                return lambdagetSalesWhTransfer36;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda40
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetSalesWhTransfer37;
                lambdagetSalesWhTransfer37 = TigerSqlManager.lambdagetSalesWhTransfer37((List) obj);
                return lambdagetSalesWhTransfer37;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda41
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Sales lambdagetSalesWhTransfer38;
                lambdagetSalesWhTransfer38 = TigerSqlManager.this.lambdagetSalesWhTransfer38(i2, (List) obj);
                return lambdagetSalesWhTransfer38;
            }
        }).defaultIfEmpty(new Sales()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new NetsisSqlManagerExternalSyntheticLambda4(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda42
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                TigerSqlManager.lambdagetSalesWhTransfer39(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    
    public static List lambdagetSalesWhTransfer36(int i2, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(WhTransfer.class, "LOGICALREF=?", new String[]{String.valueOf(i2)});
    }

    
    public static boolean lambdagetSalesWhTransfer37(List list) throws Exception {
        return list != null && list.size() == 1;
    }


    public Sales lambdagetSalesWhTransfer38(int i2, List list) throws Exception {
        String str;
        String str2;
        String str3;
        String str4;
        String stringResource;
        String str5;
        String str6;
        String str7;
        boolean z;
        String str8 = "C";
        String str9 = "D";
        new Sales();
        WhTransfer whTransfer = (WhTransfer) list.get(0);
        Sales convertSalesFicheToSales = whTransfer.convertSalesFicheToSales();
        Cursor cursor = null;
        try {
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_sales_whTransfer_edit), StringUtils.convertIntToString(i2));
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("AMBAR")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("ISYERI")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("FABRIKA")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("BOLUM")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("ODEMEPLAN")));
                    convertSalesFicheToSales.getTradeGroup().setLogicalRef(cursor.getInt(cursor.getColumnIndex("TREF")));
                    convertSalesFicheToSales.setNotUseGattribKdv(CalculateUtils.isGattribNotUseKdv(cursor.getInt(cursor.getColumnIndex("GATTRIB"))));
                    convertSalesFicheToSales.getSpeCode().setLogicalRef(cursor.getInt(cursor.getColumnIndex("SREF")));
                    convertSalesFicheToSales.getWarrantyCode().setLogicalRef(cursor.getInt(cursor.getColumnIndex("WREF")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("TITLE")));
                    FicheStringProp.setDefinition(getShipAddressDefinition(convertSalesFicheToSales.getShipAddress().getLogicalRef()));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("SHPCODE")));
                    convertSalesFicheToSales.getShipAgent().setLogicalRef(cursor.getInt(cursor.getColumnIndex("SHPREF")));
                    int i3 = 0;
                    while (i3 < convertSalesFicheToSales.getDiscountLength()) {
                        FicheDiscountRefProp discountCard = convertSalesFicheToSales.getDiscountCard(i3);
                        int i4 = i3 + 1;
                        String sb = "D" +
                                i4;
                        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(sb)));
                        convertSalesFicheToSales.getDiscountCard(i3).setLogicalRef(cursor.getInt(cursor.getColumnIndex("C" + i4)));
                        i3 = i4;
                    }
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("PROJE")));
                    convertSalesFicheToSales.getCabin().setCode(cursor.getString(cursor.getColumnIndex("CBNCODE")));
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("CBNNAME")));
                    convertSalesFicheToSales.getEDespatch().setSelect(cursor.getInt(cursor.getColumnIndex("EDESPATCH")) == 1);
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("SERIAL")));
                    if (convertSalesFicheToSales.getEDespatch().isSelect()) {
                        mapCursorToEDespatch(whTransfer, cursor, convertSalesFicheToSales);
                    }
                }
            } catch (Exception e2) {
                Log.e("AA", "getSalesWhTransfer: ", e2);
            }
            cursor.close();
            List<T> table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(WhTransferDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(i2)});
            String str10 = ErpCreator.getInstance().getmBaseErp().getUseProductNameDescription() ? "I.NAME2" : "I.NAME";
            if (table != null && table.size() > 0) {
                String str11 = "";
                for (T t : table) {
                    if (t.lineType == LineType.SERVICE.value) {
                        str5 = ContextUtils.getStringResource(R.string.table_service);
                        stringResource = ContextUtils.getStringResource(R.string.table_service_units);
                        str4 = "0 AS ISVARYANT";
                        str3 = "0 AS LOCTRACKING";
                        str2 = "0 AS DIVUNIT";
                        str = str11;
                    } else {
                        String stringResource2 = ContextUtils.getStringResource(R.string.table_item);
                        str = "LEFT JOIN UNITS UNT ON UNT.LOGICALREF=U.LOGICALREF";
                        str2 = "UNT.DIVUNIT AS DIVUNIT";
                        str3 = "I.LOCTRACKING AS LOCTRACKING";
                        str4 = "I.ISVARYANT AS ISVARYANT";
                        stringResource = ContextUtils.getStringResource(R.string.table_item_units);
                        str5 = stringResource2;
                    }
                    Cursor query = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.qry_get_sales_whTransfer_detail_edit, str5, stringResource, str4, str3, str10, str2, str), StringUtils.convertIntToString(t.logicalRef));
                    SalesDetail convertSalesFicheDetailToSalesDetail = t.convertSalesFicheDetailToSalesDetail();
                    if (query != null) {
                        try {
                        } catch (Exception e3) {
                            e = e3;
                            str6 = str8;
                            str7 = str9;
                            z = true;
                        }
                        if (query.getCount() > 0 && query.moveToFirst()) {
                            convertSalesFicheDetailToSalesDetail.setName(query.getString(query.getColumnIndex("ITEMNAME")));
                            convertSalesFicheDetailToSalesDetail.setCode(query.getString(query.getColumnIndex("ITEMCODE")));
                            convertSalesFicheDetailToSalesDetail.setCardType(query.getInt(query.getColumnIndex("CARDTYPE")));
                            convertSalesFicheDetailToSalesDetail.setTrackType(query.getInt(query.getColumnIndex("TRACKTYPE")));
                            convertSalesFicheDetailToSalesDetail.getUnit().setCode(query.getString(query.getColumnIndex("UUCODE")));
                            FicheStringProp.setDefinition(query.getString(query.getColumnIndex("UCODE")));
                            FicheStringProp.setDefinition(query.getString(query.getColumnIndex("ODEMEPLAN")));
                            FicheStringProp.setDefinition(query.getString(query.getColumnIndex("DELIVERYCODE")));
                            convertSalesFicheDetailToSalesDetail.getSpeCode().setLogicalRef(query.getInt(query.getColumnIndex("SREF")));
                            int i5 = 0;
                            while (i5 < convertSalesFicheDetailToSalesDetail.getDiscountLength()) {
                                FicheDiscountRefProp discountCard2 = convertSalesFicheDetailToSalesDetail.getDiscountCard(i5);
                                int i6 = i5 + 1;
                                String sb2 = str9 +
                                        i6;
                                FicheStringProp.setDefinition(query.getString(query.getColumnIndex(sb2)));
                                convertSalesFicheDetailToSalesDetail.getDiscountCard(i5).setLogicalRef(query.getInt(query.getColumnIndex(str8 + i6)));
                                i5 = i6;
                            }
                            FicheStringProp.setDefinition(query.getString(query.getColumnIndex("VNAME")));
                            convertSalesFicheDetailToSalesDetail.getVariant().setCode(query.getString(query.getColumnIndex("VCODE")));
                            convertSalesFicheDetailToSalesDetail.setHasVariant(StringUtils.convertIntToBoolean(query.getInt(query.getColumnIndex("ISVARYANT"))));
                            convertSalesFicheDetailToSalesDetail.setLocTracking(StringUtils.convertIntToBoolean(query.getInt(query.getColumnIndex("LOCTRACKING"))));
                            z = true;
                            try {
                                convertSalesFicheDetailToSalesDetail.setDivUnit(query.getInt(query.getColumnIndex("DIVUNIT")) == 1);
                                Cursor query2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_sales_serilot_detail_edit), StringUtils.convertIntToString(convertSalesFicheDetailToSalesDetail.getLogicalRef()), BuildConfig.NETSIS_DEMO_PASSWORD);
                                if (query2 != null) {
                                    try {
                                        if (query2.getCount() > 0) {
                                            int i7 = 0;
                                            while (i7 < query2.getCount()) {
                                                if (query2.moveToPosition(i7)) {
                                                    Serilot serilot = new Serilot();
                                                    serilot.logicalRef = query2.getInt(query2.getColumnIndex("LOGICALREF"));
                                                    serilot.detailRef = query2.getInt(query2.getColumnIndex("DETAILREF"));
                                                    serilot.unit = query2.getString(query2.getColumnIndex("UNIT"));
                                                    serilot.code = query2.getString(query2.getColumnIndex("CODE"));
                                                    str6 = str8;
                                                    str7 = str9;
                                                    try {
                                                        try {
                                                            serilot.amount = query2.getDouble(query2.getColumnIndex("AMOUNT"));
                                                            serilot.expDate = query2.getString(query2.getColumnIndex("EXPDATE"));
                                                            serilot.grpBegCode = query2.getString(query2.getColumnIndex("GRP_BEG_CODE"));
                                                            serilot.grpEndCode = query2.getString(query2.getColumnIndex("GRP_END_CODE"));
                                                            serilot.locationCode = query2.getString(query2.getColumnIndex("LOCATIONCODE"));
                                                            serilot.stTransRef = query2.getInt(query2.getColumnIndex("STTRANSREF"));
                                                            serilot.mainUnitRef = query2.getInt(query2.getColumnIndex("MAINUNITREF"));
                                                            serilot.sourceUnitRef = query2.getInt(query2.getColumnIndex("SOURCEUNITREF"));
                                                            convertSalesFicheDetailToSalesDetail.getSalesSerialLots().add(serilot);
                                                        } catch (Throwable th) {
                                                            th = th;
                                                            query2.close();
                                                            throw th;
                                                        }
                                                    } catch (Exception e4) {
                                                        e = e4;
                                                        Log.e(TAG, "getSalesWhTransfer:  ", e);
                                                        query2.close();
                                                        if (query != null) {
                                                        }
                                                        convertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(convertSalesFicheDetailToSalesDetail.getSalesSerialLots(), convertSalesFicheDetailToSalesDetail.getTrackType()));
                                                        convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                                                        str8 = str6;
                                                        str9 = str7;
                                                        str11 = str;
                                                    }
                                                } else {
                                                    str6 = str8;
                                                    str7 = str9;
                                                }
                                                i7++;
                                                str8 = str6;
                                                str9 = str7;
                                            }
                                        }
                                    } catch (Exception e5) {
                                        e = e5;
                                        str6 = str8;
                                        str7 = str9;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        str6 = str8;
                                        str7 = str9;
                                    }
                                }
                                str6 = str8;
                                str7 = str9;
                                try {
                                    query2.close();
                                } catch (Exception e6) {
                                    e = e6;
                                    Log.e("AA", "getSalesWhTransfer: ", e);
                                    if (query != null) {
                                    }
                                    convertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(convertSalesFicheDetailToSalesDetail.getSalesSerialLots(), convertSalesFicheDetailToSalesDetail.getTrackType()));
                                    convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                                    str8 = str6;
                                    str9 = str7;
                                    str11 = str;
                                }
                            } catch (Exception e7) {
                                e = e7;
                                str6 = str8;
                                str7 = str9;
                            }
                            if (query != null && !query.isClosed()) {
                                query.close();
                            }
                            convertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(convertSalesFicheDetailToSalesDetail.getSalesSerialLots(), convertSalesFicheDetailToSalesDetail.getTrackType()));
                            convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                            str8 = str6;
                            str9 = str7;
                            str11 = str;
                        }
                    }
                    str6 = str8;
                    str7 = str9;
                    z = true;
                    if (query != null) {
                        query.close();
                    }
                    convertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(convertSalesFicheDetailToSalesDetail.getSalesSerialLots(), convertSalesFicheDetailToSalesDetail.getTrackType()));
                    convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                    str8 = str6;
                    str9 = str7;
                    str11 = str;
                }
            }
            return convertSalesFicheToSales;
        } finally {
            cursor.close();
        }
    }

    
    public static void lambdagetSalesWhTransfer39(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
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
        eDispatchAdditionalInfo.secondDriverName = cursor.getString(cursor.getColumnIndex("SECONDDRIVERNAME"));
        eDispatchAdditionalInfo.secondDriverLastName = cursor.getString(cursor.getColumnIndex("SECONDDRIVERLASTNAME"));
        eDispatchAdditionalInfo.secondDriverIdentityNr = cursor.getString(cursor.getColumnIndex("SECONDDRIVERIDENTITYNR"));
        eDispatchAdditionalInfo.secondDriverPlate = cursor.getString(cursor.getColumnIndex("SECONDDRIVERPLATE"));
        eDispatchAdditionalInfo.secondTrailerPlate = cursor.getString(cursor.getColumnIndex("SECONDTRAILERPLATE"));
        eDispatchAdditionalInfo.thirdDriverName = cursor.getString(cursor.getColumnIndex("THIRDDRIVERNAME"));
        eDispatchAdditionalInfo.thirdDriverLastName = cursor.getString(cursor.getColumnIndex("THIRDDRIVERLASTNAME"));
        eDispatchAdditionalInfo.thirdDriverIdentityNr = cursor.getString(cursor.getColumnIndex("THIRDDRIVERIDENTITYNR"));
        eDispatchAdditionalInfo.thirdDriverPlate = cursor.getString(cursor.getColumnIndex("THIRDDRIVEERPLATE"));
        eDispatchAdditionalInfo.thirdTrailerPlate = cursor.getString(cursor.getColumnIndex("THIRDTRAILERPLATE"));
        eDispatchAdditionalInfo.salesType = sales.getMSalesType();
        sales.setEDispatchAdditionalInfo(eDispatchAdditionalInfo);
    }

    public List<Sales> mapCursorToWhTransfer(int i2) {
        Object obj;
        Object obj2;
        String stringResource;
        String stringResource2;
        String str;
        Object obj3;
        String str2;
        String str3;
        String str4;
        TigerSqlManager<T> tigerSqlManager = this;
        String str5 = "D";
        String str6 = "SREF";
        String str7 = "ODEMEPLAN";
        int i3 = 0;
        List<T> table = i2 == -1 ? ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(WhTransfer.class, "ISTRANSFER=?", new String[]{String.valueOf(0)}) : ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(WhTransfer.class, "LOGICALREF=? AND ISTRANSFER=?", new String[]{String.valueOf(i2), String.valueOf(0)});
        ArrayList arrayList = new ArrayList();
        for (T t : table) {
            Sales convertSalesFicheToSales = t.convertSalesFicheToSales();
            Cursor cursor = null;
            try {
                try {
                    cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_sales_whTransfer_edit), StringUtils.convertIntToString(t.logicalRef));
                    if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("AMBAR")));
                        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("ISYERI")));
                        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("FABRIKA")));
                        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("BOLUM")));
                        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(str7)));
                        convertSalesFicheToSales.getTradeGroup().setLogicalRef(cursor.getInt(cursor.getColumnIndex("TREF")));
                        convertSalesFicheToSales.setNotUseGattribKdv(CalculateUtils.isGattribNotUseKdv(cursor.getInt(cursor.getColumnIndex("GATTRIB"))));
                        convertSalesFicheToSales.getSpeCode().setLogicalRef(cursor.getInt(cursor.getColumnIndex(str6)));
                        convertSalesFicheToSales.getWarrantyCode().setLogicalRef(cursor.getInt(cursor.getColumnIndex("WREF")));
                        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("TITLE")));
                        FicheStringProp.setDefinition(tigerSqlManager.getShipAddressDefinition(convertSalesFicheToSales.getShipAddress().getLogicalRef()));
                        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("SHPCODE")));
                        convertSalesFicheToSales.getShipAgent().setLogicalRef(cursor.getInt(cursor.getColumnIndex("SHPREF")));
                        int i4 = i3;
                        while (i4 < convertSalesFicheToSales.getDiscountLength()) {
                            FicheDiscountRefProp discountCard = convertSalesFicheToSales.getDiscountCard(i4);
                            int i5 = i4 + 1;
                            String sb = str5 +
                                    i5;
                            FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(sb)));
                            convertSalesFicheToSales.getDiscountCard(i4).setLogicalRef(cursor.getInt(cursor.getColumnIndex("C" + i5)));
                            i4 = i5;
                        }
                        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("PROJE")));
                        convertSalesFicheToSales.getCabin().setCode(cursor.getString(cursor.getColumnIndex("CBNCODE")));
                        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("CBNNAME")));
                        convertSalesFicheToSales.getEDespatch().setSelect(cursor.getInt(cursor.getColumnIndex("EDESPATCH")) == 1);
                        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex("SERIAL")));
                        if (convertSalesFicheToSales.getEDespatch().isSelect()) {
                            tigerSqlManager.mapCursorToEDespatch(t, cursor, convertSalesFicheToSales);
                        }
                    }
                } finally {
                    cursor.close();
                }
            } catch (Exception e2) {
                Log.e("AA", "getSalesOrderOne: ", e2);
            }
            cursor.close();
            List<T> table2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(WhTransferDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(t.logicalRef)});
            String str8 = ErpCreator.getInstance().getmBaseErp().getUseProductNameDescription() ? "I.NAME2" : "I.NAME";
            if (table2 != null && table2.size() > 0) {
                String str9 = "";
                for (T t2 : table2) {
                    if (t2.lineType == LineType.SERVICE.value) {
                        String stringResource3 = ContextUtils.getStringResource(R.string.table_service);
                        obj = "0 AS LOCTRACKING";
                        obj2 = "0 AS DIVUNIT";
                        stringResource2 = ContextUtils.getStringResource(R.string.table_service_units);
                        obj3 = "0 AS ISVARYANT";
                        stringResource = stringResource3;
                        str = str9;
                    } else {
                        obj = "I.LOCTRACKING AS LOCTRACKING";
                        obj2 = "UNT.DIVUNIT AS DIVUNIT";
                        stringResource = ContextUtils.getStringResource(R.string.table_item);
                        stringResource2 = ContextUtils.getStringResource(R.string.table_item_units);
                        str = "LEFT JOIN UNITS UNT ON UNT.LOGICALREF=U.LOGICALREF";
                        obj3 = "I.ISVARYANT AS ISVARYANT";
                    }
                    Cursor query = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.qry_get_sales_whTransfer_detail_edit, stringResource, stringResource2, obj3, obj, str8, obj2, str), StringUtils.convertIntToString(t2.logicalRef));
                    SalesDetail convertSalesFicheDetailToSalesDetail = t2.convertSalesFicheDetailToSalesDetail();
                    if (query != null) {
                        try {
                        } catch (Exception e3) {
                            e = e3;
                            str2 = str5;
                        }
                        if (query.getCount() > 0 && query.moveToFirst()) {
                            convertSalesFicheDetailToSalesDetail.setName(query.getString(query.getColumnIndex("ITEMNAME")));
                            convertSalesFicheDetailToSalesDetail.setCode(query.getString(query.getColumnIndex("ITEMCODE")));
                            convertSalesFicheDetailToSalesDetail.setCardType(query.getInt(query.getColumnIndex("CARDTYPE")));
                            convertSalesFicheDetailToSalesDetail.setTrackType(query.getInt(query.getColumnIndex("TRACKTYPE")));
                            convertSalesFicheDetailToSalesDetail.getUnit().setCode(query.getString(query.getColumnIndex("UUCODE")));
                            FicheStringProp.setDefinition(query.getString(query.getColumnIndex("UCODE")));
                            FicheStringProp.setDefinition(query.getString(query.getColumnIndex(str7)));
                            FicheStringProp.setDefinition(query.getString(query.getColumnIndex("DELIVERYCODE")));
                            convertSalesFicheDetailToSalesDetail.getSpeCode().setLogicalRef(query.getInt(query.getColumnIndex(str6)));
                            int i6 = 0;
                            while (i6 < convertSalesFicheDetailToSalesDetail.getDiscountLength()) {
                                FicheDiscountRefProp discountCard2 = convertSalesFicheDetailToSalesDetail.getDiscountCard(i6);
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(str5);
                                str2 = str5;
                                int i7 = i6 + 1;
                                try {
                                    sb2.append(i7);
                                    FicheStringProp.setDefinition(query.getString(query.getColumnIndex(sb2.toString())));
                                    convertSalesFicheDetailToSalesDetail.getDiscountCard(i6).setLogicalRef(query.getInt(query.getColumnIndex("C" + i7)));
                                    i6 = i7;
                                    str5 = str2;
                                } catch (Exception e4) {
                                    e = e4;
                                    str4 = str6;
                                    str3 = str7;
                                    Log.e("AA", "getSalesInvoiceExam: ", e);
                                    if (query == null) {
                                    }
                                    convertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(convertSalesFicheDetailToSalesDetail.getSalesSerialLots(), convertSalesFicheDetailToSalesDetail.getTrackType()));
                                    convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                                    str9 = str;
                                    str5 = str2;
                                    str6 = str4;
                                    str7 = str3;
                                }
                            }
                            str2 = str5;
                            FicheStringProp.setDefinition(query.getString(query.getColumnIndex("VNAME")));
                            convertSalesFicheDetailToSalesDetail.getVariant().setCode(query.getString(query.getColumnIndex("VCODE")));
                            convertSalesFicheDetailToSalesDetail.setHasVariant(StringUtils.convertIntToBoolean(query.getInt(query.getColumnIndex("ISVARYANT"))));
                            convertSalesFicheDetailToSalesDetail.setLocTracking(StringUtils.convertIntToBoolean(query.getInt(query.getColumnIndex("LOCTRACKING"))));
                            convertSalesFicheDetailToSalesDetail.setDivUnit(query.getInt(query.getColumnIndex("DIVUNIT")) == 1);
                            Cursor query2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_sales_serilot_detail_edit), StringUtils.convertIntToString(convertSalesFicheDetailToSalesDetail.getLogicalRef()), BuildConfig.NETSIS_DEMO_PASSWORD);
                            if (query2 != null) {
                                try {
                                    if (query2.getCount() > 0) {
                                        int i8 = 0;
                                        while (i8 < query2.getCount()) {
                                            if (query2.moveToPosition(i8)) {
                                                Serilot serilot = new Serilot();
                                                serilot.logicalRef = query2.getInt(query2.getColumnIndex("LOGICALREF"));
                                                serilot.detailRef = query2.getInt(query2.getColumnIndex("DETAILREF"));
                                                serilot.unit = query2.getString(query2.getColumnIndex("UNIT"));
                                                serilot.code = query2.getString(query2.getColumnIndex("CODE"));
                                                str4 = str6;
                                                str3 = str7;
                                                try {
                                                    try {
                                                        serilot.amount = query2.getDouble(query2.getColumnIndex("AMOUNT"));
                                                        serilot.expDate = query2.getString(query2.getColumnIndex("EXPDATE"));
                                                        serilot.grpBegCode = query2.getString(query2.getColumnIndex("GRP_BEG_CODE"));
                                                        serilot.grpEndCode = query2.getString(query2.getColumnIndex("GRP_END_CODE"));
                                                        serilot.locationCode = query2.getString(query2.getColumnIndex("LOCATIONCODE"));
                                                        serilot.stTransRef = query2.getInt(query2.getColumnIndex("STTRANSREF"));
                                                        serilot.mainUnitRef = query2.getInt(query2.getColumnIndex("MAINUNITREF"));
                                                        serilot.sourceUnitRef = query2.getInt(query2.getColumnIndex("SOURCEUNITREF"));
                                                        convertSalesFicheDetailToSalesDetail.getSalesSerialLots().add(serilot);
                                                    } catch (Exception e5) {
                                                        e = e5;
                                                        Log.e(TAG, "getSalesWhTransfer:  ", e);
                                                        query2.close();
                                                        if (query == null) {
                                                        }
                                                        convertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(convertSalesFicheDetailToSalesDetail.getSalesSerialLots(), convertSalesFicheDetailToSalesDetail.getTrackType()));
                                                        convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                                                        str9 = str;
                                                        str5 = str2;
                                                        str6 = str4;
                                                        str7 = str3;
                                                    }
                                                } catch (Throwable th) {
                                                    th = th;
                                                    query2.close();
                                                    throw th;
                                                }
                                            } else {
                                                str4 = str6;
                                                str3 = str7;
                                            }
                                            i8++;
                                            str6 = str4;
                                            str7 = str3;
                                        }
                                    }
                                } catch (Exception e6) {
                                    e = e6;
                                    str4 = str6;
                                    str3 = str7;
                                } catch (Throwable th2) {
                                    th = th2;
                                    str4 = str6;
                                    str3 = str7;
                                }
                            }
                            str4 = str6;
                            str3 = str7;
                            try {
                                query2.close();
                            } catch (Exception e7) {
                                e = e7;
                                Log.e("AA", "getSalesInvoiceExam: ", e);
                                if (query == null) {
                                }
                                convertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(convertSalesFicheDetailToSalesDetail.getSalesSerialLots(), convertSalesFicheDetailToSalesDetail.getTrackType()));
                                convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                                str9 = str;
                                str5 = str2;
                                str6 = str4;
                                str7 = str3;
                            }
                            if (query == null && !query.isClosed()) {
                                query.close();
                            }
                            convertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(convertSalesFicheDetailToSalesDetail.getSalesSerialLots(), convertSalesFicheDetailToSalesDetail.getTrackType()));
                            convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                            str9 = str;
                            str5 = str2;
                            str6 = str4;
                            str7 = str3;
                        }
                    }
                    str2 = str5;
                    str4 = str6;
                    str3 = str7;
                    if (query == null) {
                        query.close();
                    }
                    convertSalesFicheDetailToSalesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(convertSalesFicheDetailToSalesDetail.getSalesSerialLots(), convertSalesFicheDetailToSalesDetail.getTrackType()));
                    convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                    str9 = str;
                    str5 = str2;
                    str6 = str4;
                    str7 = str3;
                }
            }
            convertSalesFicheToSales.setMSalesType(SalesType.WHTRANSFER.getmValue());
            arrayList.add(convertSalesFicheToSales);
            tigerSqlManager = this;
            str5 = str5;
            str6 = str6;
            str7 = str7;
            i3 = 0;
        }
        return arrayList;
    }

    public EDespatchPdfHeader lambdagetEDocumentContent40(int i2) {
        EDespatchPdfHeader eDespatchPdfHeader = new EDespatchPdfHeader();
        Cursor cursor = null;
        try {
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.get_local_edespatch_pdf_header, Integer.valueOf(i2)));
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    eDespatchPdfHeader.setLogicalRef(cursor.getInt(cursor.getColumnIndex("LOGICALREF")));
                    eDespatchPdfHeader.setGuid(cursor.getString(cursor.getColumnIndex("GUID")));
                    eDespatchPdfHeader.setFicheNo(cursor.getString(cursor.getColumnIndex("FICHENO")));
                    eDespatchPdfHeader.setfTime(cursor.getInt(cursor.getColumnIndex("FTIME")));
                    eDespatchPdfHeader.setDate(cursor.getString(cursor.getColumnIndex("DATE_")));
                    eDespatchPdfHeader.setDocDate(cursor.getString(cursor.getColumnIndex("DOCDATE")));
                    eDespatchPdfHeader.setDocTime(cursor.getInt(cursor.getColumnIndex("DOCTIME")));
                    eDespatchPdfHeader.setShipDate(cursor.getString(cursor.getColumnIndex("SHIPDATE")));
                    eDespatchPdfHeader.setShipTime(cursor.getInt(cursor.getColumnIndex("SHIPTIME")));
                    eDespatchPdfHeader.setClientRef(cursor.getInt(cursor.getColumnIndex("CLIENTREF")));
                    eDespatchPdfHeader.setTrRate(cursor.getDouble(cursor.getColumnIndex("TRRATE")));
                    eDespatchPdfHeader.setTrCurr(cursor.getInt(cursor.getColumnIndex("TRCURR")));
                    eDespatchPdfHeader.setClientCode(cursor.getString(cursor.getColumnIndex("CCODE")));
                    eDespatchPdfHeader.setClientDef(cursor.getString(cursor.getColumnIndex("CDEFINITION")));
                    eDespatchPdfHeader.setClientAddr1(cursor.getString(cursor.getColumnIndex("ADDR1")));
                    eDespatchPdfHeader.setClientAddr2(cursor.getString(cursor.getColumnIndex("ADDR2")));
                    eDespatchPdfHeader.setClientCity(cursor.getString(cursor.getColumnIndex("CITY")));
                    eDespatchPdfHeader.setClientDistrict(cursor.getString(cursor.getColumnIndex("DISTRICT")));
                    eDespatchPdfHeader.setClientPhone1(cursor.getString(cursor.getColumnIndex("TELNRS1")));
                    eDespatchPdfHeader.setClientPhone2(cursor.getString(cursor.getColumnIndex("TELNRS2")));
                    eDespatchPdfHeader.setClientFax(cursor.getString(cursor.getColumnIndex("FAXNR")));
                    eDespatchPdfHeader.setClientTaxNr(cursor.getString(cursor.getColumnIndex("TAXNR")));
                    eDespatchPdfHeader.setClientTaxOfficeCode(cursor.getString(cursor.getColumnIndex("TAXOFFCODE")));
                    eDespatchPdfHeader.setClientTown(cursor.getString(cursor.getColumnIndex("TOWN")));
                    eDespatchPdfHeader.setClientTaxOffice(cursor.getString(cursor.getColumnIndex("TAXOFFICE")));
                    eDespatchPdfHeader.setClientInCharge(cursor.getString(cursor.getColumnIndex("INCHARGE")));
                    eDespatchPdfHeader.setClientEmailAddr(cursor.getString(cursor.getColumnIndex("EMAILADDR")));
                    eDespatchPdfHeader.setClientIdentityNr(cursor.getString(cursor.getColumnIndex("TCKNO")));
                    eDespatchPdfHeader.setClientWebAddr(cursor.getString(cursor.getColumnIndex("WEBADDR")));
                    eDespatchPdfHeader.setTotalVat(cursor.getDouble(cursor.getColumnIndex("TOTALVAT")));
                    eDespatchPdfHeader.setNetTotal(cursor.getDouble(cursor.getColumnIndex("NETTOTAL")));
                    eDespatchPdfHeader.setTotalDiscount(cursor.getDouble(cursor.getColumnIndex("TOTALDISCOUNTS")));
                    eDespatchPdfHeader.setGrossTotal(cursor.getDouble(cursor.getColumnIndex("GROSSTOTAL")));
                    eDespatchPdfHeader.setGenExp1(cursor.getString(cursor.getColumnIndex("GENEXP1")));
                    eDespatchPdfHeader.setGenExp2(cursor.getString(cursor.getColumnIndex("GENEXP2")));
                    eDespatchPdfHeader.setGenExp3(cursor.getString(cursor.getColumnIndex("GENEXP3")));
                    eDespatchPdfHeader.setGenExp4(cursor.getString(cursor.getColumnIndex("GENEXP4")));
                    eDespatchPdfHeader.setShipTypeCode(cursor.getString(cursor.getColumnIndex("SHPTYPCOD")));
                    eDespatchPdfHeader.setShipAgentCode(cursor.getString(cursor.getColumnIndex("SHPAGNCOD")));
                    eDespatchPdfHeader.setSourceIndex(cursor.getInt(cursor.getColumnIndex("SOURCEINDEX")));
                    eDespatchPdfHeader.setShipAgentTitle(cursor.getString(cursor.getColumnIndex("SATITLE")));
                    eDespatchPdfHeader.setShipAgentTaxNr(cursor.getString(cursor.getColumnIndex("SATAXNR")));
                    eDespatchPdfHeader.setPlateNum1(cursor.getString(cursor.getColumnIndex("PLATENUM1")));
                    eDespatchPdfHeader.setChassisNum1(cursor.getString(cursor.getColumnIndex("CHASSISNUM1")));
                    eDespatchPdfHeader.setDriverName1(cursor.getString(cursor.getColumnIndex("DRIVERNAME1")));
                    eDespatchPdfHeader.setDriverSurname1(cursor.getString(cursor.getColumnIndex("DRIVERSURNAME1")));
                    eDespatchPdfHeader.setDriverTckNo1(cursor.getString(cursor.getColumnIndex("DRIVERTCKNO1")));
                    eDespatchPdfHeader.setShipAddressTitle(cursor.getString(cursor.getColumnIndex("SHIPADDRESSTITLE")));
                }
            } catch (Exception e2) {
                Log.e(TAG, "getEdespatchPdfHeader: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return eDespatchPdfHeader;
    }

    public List<EDocumentPdfDetail> lambdagetEDocumentContent45(int i2) {
        List<T> table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(InvoiceDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(i2)});
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.get_local_edocument_pdf_detail, Integer.valueOf(i2)));
                if (cursor != null && cursor.getCount() > 0) {
                    for (int i3 = 0; i3 < table.size(); i3++) {
                        if (cursor.moveToPosition(i3)) {
                            EDocumentPdfDetail eDocumentPdfDetail = new EDocumentPdfDetail();
                            eDocumentPdfDetail.setStFicheRef(cursor.getInt(cursor.getColumnIndex("STFICHEREF")));
                            eDocumentPdfDetail.setLogicalRef(cursor.getInt(cursor.getColumnIndex("LOGICALREF")));
                            eDocumentPdfDetail.setStFicheLineNo(cursor.getInt(cursor.getColumnIndex("STFICHELNNO")));
                            eDocumentPdfDetail.setLineType(cursor.getInt(cursor.getColumnIndex("LINETYPE")));
                            eDocumentPdfDetail.setItemCode(cursor.getString(cursor.getColumnIndex("CODE")));
                            eDocumentPdfDetail.setItemName(cursor.getString(cursor.getColumnIndex("NAME")));
                            eDocumentPdfDetail.setUomRef(cursor.getInt(cursor.getColumnIndex("UOMREF")));
                            eDocumentPdfDetail.setUsRef(cursor.getInt(cursor.getColumnIndex("USREF")));
                            eDocumentPdfDetail.setAmount(cursor.getDouble(cursor.getColumnIndex("AMOUNT")));
                            eDocumentPdfDetail.setPrice(cursor.getDouble(cursor.getColumnIndex("PRICE")));
                            eDocumentPdfDetail.setTotal(cursor.getDouble(cursor.getColumnIndex("TOTAL")));
                            eDocumentPdfDetail.setDistDisc(cursor.getDouble(cursor.getColumnIndex("DISTDISC")));
                            eDocumentPdfDetail.setVat(cursor.getDouble(cursor.getColumnIndex("VAT")));
                            eDocumentPdfDetail.setVatAmount(cursor.getDouble(cursor.getColumnIndex("VATAMNT")));
                            eDocumentPdfDetail.setVatMatrah(cursor.getDouble(cursor.getColumnIndex("VATMATRAH")));
                            eDocumentPdfDetail.setLineExp(cursor.getString(cursor.getColumnIndex("LINEEXP")));
                            eDocumentPdfDetail.setItemRef(cursor.getInt(cursor.getColumnIndex("ITEMREF")));
                            eDocumentPdfDetail.setGuid(cursor.getString(cursor.getColumnIndex("GUID")));
                            eDocumentPdfDetail.setUnitCode(cursor.getString(cursor.getColumnIndex("UNITCODE")));
                            eDocumentPdfDetail.setDivUnit(cursor.getInt(cursor.getColumnIndex("DIVUNIT")));
                            eDocumentPdfDetail.setVatExceptReason(cursor.getString(cursor.getColumnIndex("VATEXCEPTREASON")));
                            arrayList.add(eDocumentPdfDetail);
                        }
                    }
                }
            } catch (Exception e2) {
                Log.e(TAG, "getEDespatchPdfDetail: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return java.util.Collections.emptyList();
    }

    public void lambdagetEDocumentContent44(int i2) {
        EInvoicePdfHeader eInvoicePdfHeader = new EInvoicePdfHeader();
        Cursor cursor = null;
        try {
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.get_local_einvoice_pdf_header, Integer.valueOf(i2)));
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    eInvoicePdfHeader.setLogicalRef(cursor.getInt(cursor.getColumnIndex("LOGICALREF")));
                    eInvoicePdfHeader.setGuid(cursor.getString(cursor.getColumnIndex("GUID")));
                    eInvoicePdfHeader.setDespatchNo(cursor.getString(cursor.getColumnIndex("FICHENO")));
                    eInvoicePdfHeader.setInvoiceNo(cursor.getString(cursor.getColumnIndex("INVOICENO")));
                    eInvoicePdfHeader.setInvoiceDate(cursor.getString(cursor.getColumnIndex("INVOICEDATE")));
                    eInvoicePdfHeader.setDocDate(cursor.getString(cursor.getColumnIndex("INVDOCDATE")));
                    eInvoicePdfHeader.setDespatchDate(cursor.getString(cursor.getColumnIndex("DOCDATE")));
                    eInvoicePdfHeader.setClientRef(cursor.getInt(cursor.getColumnIndex("CLIENTREF")));
                    eInvoicePdfHeader.setTrRate(cursor.getDouble(cursor.getColumnIndex("TRRATE")));
                    eInvoicePdfHeader.setTrCurr(cursor.getInt(cursor.getColumnIndex("TRCURR")));
                    eInvoicePdfHeader.setClientCode(cursor.getString(cursor.getColumnIndex("CCODE")));
                    eInvoicePdfHeader.setClientDef(cursor.getString(cursor.getColumnIndex("CDEFINITION")));
                    eInvoicePdfHeader.setClientAddr1(cursor.getString(cursor.getColumnIndex("ADDR1")));
                    eInvoicePdfHeader.setClientAddr2(cursor.getString(cursor.getColumnIndex("ADDR2")));
                    eInvoicePdfHeader.setClientCity(cursor.getString(cursor.getColumnIndex("CITY")));
                    eInvoicePdfHeader.setClientDistrict(cursor.getString(cursor.getColumnIndex("DISTRICT")));
                    eInvoicePdfHeader.setClientPhone1(cursor.getString(cursor.getColumnIndex("TELNRS1")));
                    eInvoicePdfHeader.setClientPhone2(cursor.getString(cursor.getColumnIndex("TELNRS2")));
                    eInvoicePdfHeader.setClientFax(cursor.getString(cursor.getColumnIndex("FAXNR")));
                    eInvoicePdfHeader.setClientTaxNr(cursor.getString(cursor.getColumnIndex("TAXNR")));
                    eInvoicePdfHeader.setClientTaxOfficeCode(cursor.getString(cursor.getColumnIndex("TAXOFFCODE")));
                    eInvoicePdfHeader.setClientTown(cursor.getString(cursor.getColumnIndex("TOWN")));
                    eInvoicePdfHeader.setClientTaxOffice(cursor.getString(cursor.getColumnIndex("TAXOFFICE")));
                    eInvoicePdfHeader.setClientInCharge(cursor.getString(cursor.getColumnIndex("INCHARGE")));
                    eInvoicePdfHeader.setClientEmailAddr(cursor.getString(cursor.getColumnIndex("EMAILADDR")));
                    eInvoicePdfHeader.setClientIdentityNr(cursor.getString(cursor.getColumnIndex("TCKNO")));
                    eInvoicePdfHeader.setClientWebAddr(cursor.getString(cursor.getColumnIndex("WEBADDR")));
                    eInvoicePdfHeader.setClientPostCode(cursor.getString(cursor.getColumnIndex("POSTCODE")));
                    eInvoicePdfHeader.setTotalVat(cursor.getDouble(cursor.getColumnIndex("TOTALVAT")));
                    eInvoicePdfHeader.setNetTotal(cursor.getDouble(cursor.getColumnIndex("NETTOTAL")));
                    eInvoicePdfHeader.setTotalDiscount(cursor.getDouble(cursor.getColumnIndex("TOTALDISCOUNTS")));
                    eInvoicePdfHeader.setGrossTotal(cursor.getDouble(cursor.getColumnIndex("GROSSTOTAL")));
                    eInvoicePdfHeader.setGenExp1(cursor.getString(cursor.getColumnIndex("GENEXP1")));
                    eInvoicePdfHeader.setGenExp2(cursor.getString(cursor.getColumnIndex("GENEXP2")));
                    eInvoicePdfHeader.setGenExp3(cursor.getString(cursor.getColumnIndex("GENEXP3")));
                    eInvoicePdfHeader.setGenExp4(cursor.getString(cursor.getColumnIndex("GENEXP4")));
                    eInvoicePdfHeader.setShipTypeCode(cursor.getString(cursor.getColumnIndex("SHPTYPCOD")));
                    eInvoicePdfHeader.setShipAgentCode(cursor.getString(cursor.getColumnIndex("SHPAGNCOD")));
                    eInvoicePdfHeader.setSourceIndex(cursor.getInt(cursor.getColumnIndex("SOURCEINDEX")));
                    eInvoicePdfHeader.setShipAgentTitle(cursor.getString(cursor.getColumnIndex("SATITLE")));
                    eInvoicePdfHeader.setShipAgentTaxNr(cursor.getString(cursor.getColumnIndex("SATAXNR")));
                    eInvoicePdfHeader.setPlateNum1(cursor.getString(cursor.getColumnIndex("PLATENUM1")));
                    eInvoicePdfHeader.setChassisNum1(cursor.getString(cursor.getColumnIndex("CHASSISNUM1")));
                    eInvoicePdfHeader.setDriverName1(cursor.getString(cursor.getColumnIndex("DRIVERNAME1")));
                    eInvoicePdfHeader.setDriverSurname1(cursor.getString(cursor.getColumnIndex("DRIVERSURNAME1")));
                    eInvoicePdfHeader.setDriverTckNo1(cursor.getString(cursor.getColumnIndex("DRIVERTCKNO1")));
                    eInvoicePdfHeader.setShipAddressTitle(cursor.getString(cursor.getColumnIndex("SHIPADDRESSTITLE")));
                    eInvoicePdfHeader.setShipAddressTaxNr(cursor.getString(cursor.getColumnIndex("SHIPADDRESSTAXNR")));
                }
            } catch (Exception e2) {
                Log.e(TAG, "eInvoicePdfHeader: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    public Disposable getEDocumentContent(final int i2, final ResponseListener responseListener, SalesType salesType) {
        if (salesType == SalesType.DISPATCH) {
            Observable<T> observeOn = Observable.zip(Observable.fromCallable(new Callable() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public Object call() {
                    EDespatchPdfHeader lambdagetEDocumentContent40;
                    lambdagetEDocumentContent40 = TigerSqlManager.this.lambdagetEDocumentContent40(i2);
                    return lambdagetEDocumentContent40;
                }
            }).subscribeOn(Schedulers.newThread()), Observable.fromCallable(new Callable() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda1
                @Override // java.util.concurrent.Callable
                public Object call() {
                    List lambdagetEDocumentContent41;
                    lambdagetEDocumentContent41 = TigerSqlManager.this.lambdagetEDocumentContent41(i2);
                    return lambdagetEDocumentContent41;
                }
            }).subscribeOn(Schedulers.newThread()), new BiFunction() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda2
                @Override // io.reactivex.functions.BiFunction
                public Object apply(Object obj, Object obj2) {
                    EDocumentPdfContent lambdagetEDocumentContent42;
                    lambdagetEDocumentContent42 = TigerSqlManager.lambdagetEDocumentContent42((EDespatchPdfHeader) obj, (List) obj2);
                    return lambdagetEDocumentContent42;
                }
            }).observeOn(AndroidSchedulers.mainThread());
            Objects.requireNonNull(responseListener);
            observeOn.subscribe(new TigerWcfAsyncTaskExternalSyntheticLambda25(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda3
                @Override // io.reactivex.functions.Consumer
                public void accept(Object obj) {
                    TigerSqlManager.lambdagetEDocumentContent43(ResponseListener.this, (Throwable) obj);
                }
            });
            return null;
        }
        if (salesType != SalesType.INVOICE) {
            return null;
        }
        Observable<T> observeOn2 = Observable.zip(Observable.fromCallable(new Callable() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda4
            @Override // java.util.concurrent.Callable
            public Object call() {
                EInvoicePdfHeader lambdagetEDocumentContent44;
                lambdagetEDocumentContent44 = TigerSqlManager.this.lambdagetEDocumentContent44(i2);
                return lambdagetEDocumentContent44;
            }
        }).subscribeOn(Schedulers.newThread()), Observable.fromCallable(new Callable() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda5
            @Override // java.util.concurrent.Callable
            public Object call() {
                List lambdagetEDocumentContent45;
                lambdagetEDocumentContent45 = TigerSqlManager.this.lambdagetEDocumentContent45(i2);
                return lambdagetEDocumentContent45;
            }
        }).subscribeOn(Schedulers.newThread()), new BiFunction() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda6
            @Override // io.reactivex.functions.BiFunction
            public Object apply(Object obj, Object obj2) {
                EDocumentPdfContent lambdagetEDocumentContent46;
                lambdagetEDocumentContent46 = TigerSqlManager.lambdagetEDocumentContent46((EInvoicePdfHeader) obj, (List) obj2);
                return lambdagetEDocumentContent46;
            }
        }).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn2.subscribe(new TigerWcfAsyncTaskExternalSyntheticLambda25(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.tiger.TigerSqlManagerExternalSyntheticLambda7
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                try {
                    TigerSqlManager.lambdagetEDocumentContent47(responseListener, (Throwable) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return null;
    }

    
    public static EDocumentPdfContent lambdagetEDocumentContent42(EDespatchPdfHeader eDespatchPdfHeader, List list) throws Exception {
        return new EDocumentPdfContent(eDespatchPdfHeader, (List<EDocumentPdfDetail>) list, "");
    }

    
    public static void lambdagetEDocumentContent43(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }
 
    public static EDocumentPdfContent lambdagetEDocumentContent46(EInvoicePdfHeader eInvoicePdfHeader, List list) throws Exception {
        return new EDocumentPdfContent(eInvoicePdfHeader, (List<EDocumentPdfDetail>) list, "");
    }
 
    public static void lambdagetEDocumentContent47(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }
}
