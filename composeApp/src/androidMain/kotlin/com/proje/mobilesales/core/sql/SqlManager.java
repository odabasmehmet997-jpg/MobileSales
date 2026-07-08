package com.proje.mobilesales.core.sql;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.google.common.base.Strings;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseErpExternalSyntheticLambda12;
import com.proje.mobilesales.core.data.SendCreatorImplExternalSyntheticLambda3;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda6;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.LineType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.core.utils.CalculateUtils;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.utils.UnitPriceFormatter;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.Cabin;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.CabinTrans;
import com.proje.mobilesales.features.collections.casefiche.model.CaseFiche;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFiche;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditX;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeed;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFiche;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.collections.receipt.view.fragment.ReceiptFicheListFragment;
import com.proje.mobilesales.features.customer.model.Customer;
import com.proje.mobilesales.features.customer.model.CustomerDetail;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import com.proje.mobilesales.features.dbmodel.CustomerImage;
import com.proje.mobilesales.features.dbmodel.Order;
import com.proje.mobilesales.features.dbmodel.OrderDetail;
import com.proje.mobilesales.features.dbmodel.ShipAddress;
import com.proje.mobilesales.features.dbmodel.SpecodesPrices;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.penetration.model.Penetration;
import com.proje.mobilesales.features.penetration.model.database.PenetrationShort;
import com.proje.mobilesales.features.penetration.view.detail.PenetrationActivity;
import com.proje.mobilesales.features.product.model.ItemUnit;
import com.proje.mobilesales.features.product.model.Product;
import com.proje.mobilesales.features.product.model.ProductDetail;
import com.proje.mobilesales.features.product.model.ProductGroupModel;
import com.proje.mobilesales.features.product.model.database.ItemPrice;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.SalesShort;
import com.proje.mobilesales.features.todo.model.database.TodoInfoDb;
import com.proje.mobilesales.features.visit.model.Visit;
import com.proje.mobilesales.features.visit.model.VisitInfoShort;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import org.springframework.http.HttpHeaders;

public abstract class SqlManager<T> implements ISqlManager<T> {
    private static final String TAG = "SqlManager";
    protected final Scheduler mIoScheduler;

    public static  boolean lambdagetProductDetailObservable164(Cursor cursor) throws Exception {
        return cursor != null;
    }

    public static boolean lambdagetProductPriceDetailObservable172(Cursor cursor) throws Exception {
        return cursor != null;
    }

    public static boolean lambdagetProductUnitDetailObservable176(Cursor cursor) throws Exception {
        return cursor != null;
    }

    public static boolean lambdagetProductWareHouseDetailObservable168(Cursor cursor) throws Exception {
        return cursor != null;
    }

    public SqlManager(Scheduler scheduler) {
        this.mIoScheduler = scheduler;
    }
    public void getOne(Context context, String str, ResponseListener<CustomerDetail> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() {
            public final String f0 = "";
            public void SqlManagerExternalSyntheticLambda57(String str2) {
                r1 = str2;
            }
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() {
            public final String f0 = "";

            public void SqlManagerExternalSyntheticLambda58(String str2) {
                  r1 = str2;
            }
            public Object apply(Object obj) {
                Cursor lambdagetOne1;
                lambdagetOne1 = SqlManager.lambdagetOne1(r1, (String) obj);
                return lambdagetOne1;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda59
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetOne2;
                lambdagetOne2 = SqlManager.lambdagetOne2((Cursor) obj);
                return lambdagetOne2;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda60
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return new CustomerDetailCursor((Cursor) obj);
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda61
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                CustomerDetail lambdagetOne3;
                lambdagetOne3 = SqlManager.lambdagetOne3(( CustomerDetailCursor) obj);
                return lambdagetOne3;
            }
        }).defaultIfEmpty(new CustomerDetail()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda62
            public SqlManagerExternalSyntheticLambda62() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                ResponseListener.this.onResponse((CustomerDetail) obj);
            }
        }, new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda63
            public SqlManagerExternalSyntheticLambda63() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetOne4(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Cursor lambdagetOne1(String str, String str2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(str);
    }

    public static boolean lambdagetOne2(Cursor cursor) throws Exception {
        return cursor != null && cursor.moveToFirst();
    }

    public static CustomerDetail lambdagetOne3(CustomerDetailCursor customerDetailCursor) throws Exception {
        CustomerDetail customerDetail = new CustomerDetail();
        if (customerDetailCursor.getCount() > 0 && customerDetailCursor.moveToFirst()) {
            customerDetail = customerDetailCursor.getCustomerDetail();
        }
        if (!customerDetailCursor.isClosed()) {
            customerDetailCursor.close();
        }
        return customerDetail;
    }

    public static void lambdagetOne4(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public Disposable getCustomerList(String str, ResponseListener<ArrayList<Customer>> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda23
            public final String f0;

            public SqlManagerExternalSyntheticLambda23(String str2) {
                r1 = str2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda24
            public final String f0;

            public SqlManagerExternalSyntheticLambda24(String str2) {
                r1 = str2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetCustomerList6;
                lambdagetCustomerList6 = SqlManager.lambdagetCustomerList6(r1, (String) obj);
                return lambdagetCustomerList6;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda25
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetCustomerList7;
                lambdagetCustomerList7 = SqlManager.lambdagetCustomerList7((Cursor) obj);
                return lambdagetCustomerList7;
            }
        }).map(new SqlManagerExternalSyntheticLambda26()).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda27
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetCustomerList8;
                lambdagetCustomerList8 = SqlManager.lambdagetCustomerList8(( CustomerCursor) obj);
                return lambdagetCustomerList8;
            }
        }).doOnError(new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda28
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                Log.e(SqlManager.TAG, "accept: ", (Throwable) obj);
            }
        }).defaultIfEmpty(new ArrayList()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        return observeOn.subscribe(new NetsisExternalSyntheticLambda6(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda29
            public SqlManagerExternalSyntheticLambda29() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetCustomerList10(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Cursor lambdagetCustomerList6(String str, String str2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(str);
    }

    public static boolean lambdagetCustomerList7(Cursor cursor) throws Exception {
        if (cursor != null && cursor.moveToFirst()) {
            return true;
        }
        if (cursor.isClosed()) {
            return false;
        }
        cursor.close();
        return false;
    }

    public static ArrayList lambdagetCustomerList8(CustomerCursor customerCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (customerCursor.getCount() > 0 && customerCursor.moveToFirst()) {
            do {
                arrayList.add(customerCursor.get());
            } while (customerCursor.moveToNext());
        }
        if (!customerCursor.isClosed()) {
            customerCursor.close();
        }
        return arrayList;
    }

    public static void lambdagetCustomerList10(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public Disposable getRouteList(String str, ResponseListener<ArrayList<Customer>> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda108
            public final String f0;

            public SqlManagerExternalSyntheticLambda108(String str2) {
                r1 = str2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda109
            public final String f0;

            public SqlManagerExternalSyntheticLambda109(String str2) {
                r1 = str2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetRouteList12;
                lambdagetRouteList12 = SqlManager.lambdagetRouteList12(r1, (String) obj);
                return lambdagetRouteList12;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda110
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetRouteList13;
                lambdagetRouteList13 = SqlManager.lambdagetRouteList13((Cursor) obj);
                return lambdagetRouteList13;
            }
        }).map(new SqlManagerExternalSyntheticLambda26()).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda111
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetRouteList14;
                lambdagetRouteList14 = SqlManager.lambdagetRouteList14(( CustomerCursor) obj);
                return lambdagetRouteList14;
            }
        }).doOnError(new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda112
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                Log.e(SqlManager.TAG, "accept: ", (Throwable) obj);
            }
        }).defaultIfEmpty(new ArrayList()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        return observeOn.subscribe(new NetsisExternalSyntheticLambda6(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda113
            public SqlManagerExternalSyntheticLambda113() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetRouteList16(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Cursor lambdagetRouteList12(String str, String str2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(str);
    }

    public static boolean lambdagetRouteList13(Cursor cursor) throws Exception {
        if (cursor != null && cursor.moveToFirst()) {
            return true;
        }
        if (cursor.isClosed()) {
            return false;
        }
        cursor.close();
        return false;
    }

    public static ArrayList lambdagetRouteList14(CustomerCursor customerCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (customerCursor.getCount() > 0 && customerCursor.moveToFirst()) {
            do {
                arrayList.add(customerCursor.getRoute());
            } while (customerCursor.moveToNext());
        }
        if (!customerCursor.isClosed()) {
            customerCursor.close();
        }
        return arrayList;
    }

    public static void lambdagetRouteList16(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public Disposable getSalesCursorToList(Cursor cursor, ResponseListener<List<SalesShort>> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda67
            public final Cursor f0;

            public SqlManagerExternalSyntheticLambda67(Cursor cursor2) {
                r1 = cursor2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda68
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetSalesCursorToList18;
                lambdagetSalesCursorToList18 = SqlManager.lambdagetSalesCursorToList18((Cursor) obj);
                return lambdagetSalesCursorToList18;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda69
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return new SalesShortCursor((Cursor) obj);
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda70
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetSalesCursorToList19;
                lambdagetSalesCursorToList19 = SqlManager.lambdagetSalesCursorToList19(( SalesShortCursor) obj);
                return lambdagetSalesCursorToList19;
            }
        }).defaultIfEmpty(new ArrayList()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        return observeOn.subscribe(new NetsisExternalSyntheticLambda6(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda71
            public SqlManagerExternalSyntheticLambda71() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetSalesCursorToList20(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static boolean lambdagetSalesCursorToList18(Cursor cursor) throws Exception {
        if (cursor != null && !cursor.isClosed() && cursor.moveToFirst()) {
            return true;
        }
        if (cursor.isClosed()) {
            return false;
        }
        cursor.close();
        return false;
    }

    public static ArrayList lambdagetSalesCursorToList19(SalesShortCursor salesShortCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (salesShortCursor.getCount() > 0 && salesShortCursor.moveToFirst()) {
            do {
                arrayList.add(salesShortCursor.getSales());
            } while (salesShortCursor.moveToNext());
        }
        if (!salesShortCursor.isClosed()) {
            salesShortCursor.close();
        }
        return arrayList;
    }

    public static void lambdagetSalesCursorToList20(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public Disposable getReceiptCursorToList(Cursor cursor, ReceiptFicheListFragment.FicheListResponseListener responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda156
            public final Cursor f0;

            public SqlManagerExternalSyntheticLambda156(Cursor cursor2) {
                r1 = cursor2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda157
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetReceiptCursorToList22;
                lambdagetReceiptCursorToList22 = SqlManager.lambdagetReceiptCursorToList22((Cursor) obj);
                return lambdagetReceiptCursorToList22;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda158
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return new FicheShortCursor((Cursor) obj);
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda159
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetReceiptCursorToList23;
                lambdagetReceiptCursorToList23 = SqlManager.lambdagetReceiptCursorToList23(( FicheShortCursor) obj);
                return lambdagetReceiptCursorToList23;
            }
        }).defaultIfEmpty(new ArrayList()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        return observeOn.subscribe(new NetsisExternalSyntheticLambda6(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda160
            public SqlManagerExternalSyntheticLambda160() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetReceiptCursorToList24(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static boolean lambdagetReceiptCursorToList22(Cursor cursor) throws Exception {
        if (cursor != null && !cursor.isClosed() && cursor.moveToFirst()) {
            return true;
        }
        if (cursor.isClosed()) {
            return false;
        }
        cursor.close();
        return false;
    }

    public static ArrayList lambdagetReceiptCursorToList23(FicheShortCursor ficheShortCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (ficheShortCursor.getCount() > 0 && ficheShortCursor.moveToFirst()) {
            do {
                arrayList.add(ficheShortCursor.getFiche());
            } while (ficheShortCursor.moveToNext());
        }
        if (!ficheShortCursor.isClosed()) {
            ficheShortCursor.close();
        }
        return arrayList;
    }

    public static void lambdagetReceiptCursorToList24(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public Disposable getProductList(String str, int i2, ResponseListener<ArrayList<Product>> responseListener, String str2) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda42
            public final String f0;

            public SqlManagerExternalSyntheticLambda42(String str3) {
                r1 = str3;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda43
            public final String f0;

            public SqlManagerExternalSyntheticLambda43(String str3) {
                r1 = str3;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetProductList26;
                lambdagetProductList26 = SqlManager.lambdagetProductList26(r1, (String) obj);
                return lambdagetProductList26;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda44
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetProductList27;
                lambdagetProductList27 = SqlManager.lambdagetProductList27((Cursor) obj);
                return lambdagetProductList27;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda45
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return new ProductCursor((Cursor) obj);
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda46
            public final int f0;
            public final String f1;

            public SqlManagerExternalSyntheticLambda46(int i22, String str22) {
                r1 = i22;
                r2 = str22;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetProductList28;
                lambdagetProductList28 = SqlManager.lambdagetProductList28(r1, r2, ( ProductCursor) obj);
                return lambdagetProductList28;
            }
        }).defaultIfEmpty(new ArrayList()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        return observeOn.subscribe(new NetsisExternalSyntheticLambda6(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda47
            public SqlManagerExternalSyntheticLambda47() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetProductList29(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Cursor lambdagetProductList26(String str, String str2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(str);
    }

    public static boolean lambdagetProductList27(Cursor cursor) throws Exception {
        if (cursor != null && cursor.moveToFirst()) {
            return true;
        }
        if (cursor == null) {
            return false;
        }
        cursor.close();
        return false;
    }

    public static ArrayList lambdagetProductList28(int i2, String str, ProductCursor productCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (productCursor.getCount() > 0 && productCursor.moveToFirst()) {
            Product product = null;
            do {
                try {
                    product = productCursor.get();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                if (product != null) {
                    setSpecodePrices(product, i2, str);
                    arrayList.add(product);
                }
            } while (productCursor.moveToNext());
        }
        if (!productCursor.isClosed()) {
            productCursor.close();
        }
        return arrayList;
    }

    public static void lambdagetProductList29(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    public static void setSpecodePrices(Product product, int i2, String str) {
        String str2;
        UnitPriceFormatter unitPriceFormatter = UnitPriceFormatter.getInstance(i2);
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        if (baseErp.getErpType() == ErpType.NETSIS) {
            List<T> table = baseErp.getLogoSqlHelper().getTable(ItemPrice.class, "ITEMCODE=? AND NOT CURNR=0 ", new String[]{product.getCode()});
            if (table != null && table.size() > 0) {
                product.setPriceWithDigits(String.format("%s / %s", unitPriceFormatter.getFormattedPrice(((ItemPrice) table.get(0)).price), ((ItemPrice) table.get(0)).curCode));
                product.setPrice(((ItemPrice) table.get(0)).price);
                product.setPriceRef(((ItemPrice) table.get(0)).logicalRef);
                product.setCPrice(((ItemPrice) table.get(0)).getcPrice());
                product.setCurCode(((ItemPrice) table.get(0)).curCode);
                product.setCurNr(((ItemPrice) table.get(0)).curNr);
                return;
            }
            if (!Strings.isNullOrEmpty(str)) {
                String[] splitInitValue = StringUtils.splitInitValue(baseErp.getLogoSqlHelper().getParamValue(ParameterTypes.ptSpeCodeUsage), ",", 2);
                ArrayList arrayList = new ArrayList();
                for (String str3 : splitInitValue) {
                    arrayList.add(str3.trim());
                }
                if (arrayList.contains(BuildConfig.NETSIS_DEMO_PASSWORD)) {
                    List<T> table2 = baseErp.getLogoSqlHelper().getTable(SpecodesPrices.class, "BRANCHCODE=? AND SPECODE1='E' ", new String[]{StringUtils.convertIntToString(((Preferences.NetsisUserSettings) baseErp.getUserSettings()).getBranchCode())});
                    if (table2 != null && table2.size() == 1) {
                        SpecodesPrices specodesPrices = (SpecodesPrices) table2.get(0);
                        if (specodesPrices.getSpecode11().equals(str)) {
                            str2 = specodesPrices.getSpecode12();
                        } else if (specodesPrices.getSpecode21().equals(str)) {
                            str2 = specodesPrices.getSpecode22();
                        } else if (specodesPrices.getSpecode31().equals(str)) {
                            str2 = specodesPrices.getSpecode32();
                        } else if (!specodesPrices.getSpecode41().equals(str)) {
                            str2 = "";
                        } else {
                            str2 = specodesPrices.getSpecode42();
                        }
                        if (!str2.isEmpty()) {
                            List<T> table3 = baseErp.getLogoSqlHelper().getTable(ItemPrice.class, "ITEMCODE=? AND PRICECODE=? ", new String[]{product.getCode(), String.format("%s %s", ContextUtils.getStringResource(R.string.str_price), str2)});
                            if (table3 == null || table3.size() <= 0) {
                                return;
                            }
                            String format = String.format("%s / %s", unitPriceFormatter.getFormattedPrice(((ItemPrice) table3.get(0)).price), ((ItemPrice) table3.get(0)).curCode);
                            product.setPriceWithDigits(format);
                            product.setCPrice(format);
                            product.setPrice(((ItemPrice) table3.get(0)).price);
                            product.setPriceRef(((ItemPrice) table3.get(0)).logicalRef);
                            return;
                        }
                        setItemPrice(product, i2);
                        return;
                    }
                    setItemPrice(product, i2);
                    return;
                }
                setItemPrice(product, i2);
                return;
            }
            setItemPrice(product, i2);
            return;
        }
        setItemPrice(product, i2);
    }

    private static void setItemPrice(Product product, int i2) {
        if (product.getPrice() != 0.0d) {
            product.setPriceWithDigits(String.format("%s / %s", UnitPriceFormatter.getInstance(i2).getFormattedPrice(product.getPrice()), product.getCurCode()));
        } else {
            product.setPriceWithDigits("");
        }
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getProductDetail(String str, ResponseListener<ArrayList<ProductDetail>> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda92
            public final String f0;

            public SqlManagerExternalSyntheticLambda92(String str2) {
                r1 = str2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda93
            public final String f0;

            public SqlManagerExternalSyntheticLambda93(String str2) {
                r1 = str2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetProductDetail31;
                lambdagetProductDetail31 = SqlManager.lambdagetProductDetail31(r1, (String) obj);
                return lambdagetProductDetail31;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda94
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetProductDetail32;
                lambdagetProductDetail32 = SqlManager.lambdagetProductDetail32((Cursor) obj);
                return lambdagetProductDetail32;
            }
        }).map(new SqlManagerExternalSyntheticLambda37()).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda95
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetProductDetail33;
                lambdagetProductDetail33 = SqlManager.lambdagetProductDetail33(( ProductDetailCursor) obj);
                return lambdagetProductDetail33;
            }
        }).defaultIfEmpty(new ArrayList()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new NetsisExternalSyntheticLambda6(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda96
            public SqlManagerExternalSyntheticLambda96() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetProductDetail34(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Cursor lambdagetProductDetail31(String str, String str2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(str);
    }

    public static boolean lambdagetProductDetail32(Cursor cursor) throws Exception {
        return cursor != null && cursor.moveToFirst();
    }

    public static ArrayList lambdagetProductDetail33(ProductDetailCursor productDetailCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (productDetailCursor.getCount() > 0 && productDetailCursor.moveToFirst()) {
            do {
                arrayList.add(productDetailCursor.get());
            } while (productDetailCursor.moveToNext());
        }
        if (!productDetailCursor.isClosed()) {
            productDetailCursor.close();
        }
        return arrayList;
    }

    public static void lambdagetProductDetail34(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public Disposable getVisitList(String str, ResponseListener<ArrayList<VisitInfoShort>> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda114
            public final String f0;

            public SqlManagerExternalSyntheticLambda114(String str2) {
                r1 = str2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda115
            public final String f0;

            public SqlManagerExternalSyntheticLambda115(String str2) {
                r1 = str2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetVisitList36;
                lambdagetVisitList36 = SqlManager.lambdagetVisitList36(r1, (String) obj);
                return lambdagetVisitList36;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda116
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetVisitList37;
                lambdagetVisitList37 = SqlManager.lambdagetVisitList37((Cursor) obj);
                return lambdagetVisitList37;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda117
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return new VisitInfoShortCursor((Cursor) obj);
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda118
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetVisitList38;
                lambdagetVisitList38 = SqlManager.lambdagetVisitList38(( VisitInfoShortCursor) obj);
                return lambdagetVisitList38;
            }
        }).defaultIfEmpty(new ArrayList()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        return observeOn.subscribe(new NetsisExternalSyntheticLambda6(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda119
            public SqlManagerExternalSyntheticLambda119() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetVisitList39(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Cursor lambdagetVisitList36(String str, String str2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(str);
    }

    public static boolean lambdagetVisitList37(Cursor cursor) throws Exception {
        if (cursor != null && cursor.moveToFirst()) {
            return true;
        }
        if (cursor.isClosed()) {
            return false;
        }
        cursor.close();
        return false;
    }

    public static ArrayList lambdagetVisitList38(VisitInfoShortCursor visitInfoShortCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (visitInfoShortCursor.getCount() > 0 && visitInfoShortCursor.moveToFirst()) {
            do {
                arrayList.add(visitInfoShortCursor.get());
            } while (visitInfoShortCursor.moveToNext());
        }
        if (!visitInfoShortCursor.isClosed()) {
            visitInfoShortCursor.close();
        }
        return arrayList;
    }

    public static void lambdagetVisitList39(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public Disposable getCabinList(String str, ResponseListener<ArrayList<Cabin>> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda150
            public final String f0;

            public SqlManagerExternalSyntheticLambda150(String str2) {
                r1 = str2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda151
            public final String f0;

            public SqlManagerExternalSyntheticLambda151(String str2) {
                r1 = str2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetCabinList41;
                lambdagetCabinList41 = SqlManager.lambdagetCabinList41(r1, (String) obj);
                return lambdagetCabinList41;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda152
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetCabinList42;
                lambdagetCabinList42 = SqlManager.lambdagetCabinList42((Cursor) obj);
                return lambdagetCabinList42;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda153
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return new CabinInfoCursor((Cursor) obj);
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda154
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetCabinList43;
                lambdagetCabinList43 = SqlManager.lambdagetCabinList43(( CabinInfoCursor) obj);
                return lambdagetCabinList43;
            }
        }).defaultIfEmpty(new ArrayList()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        return observeOn.subscribe(new NetsisExternalSyntheticLambda6(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda155
            public SqlManagerExternalSyntheticLambda155() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetCabinList44(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Cursor lambdagetCabinList41(String str, String str2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(str);
    }

    public static boolean lambdagetCabinList42(Cursor cursor) throws Exception {
        if (cursor != null && cursor.moveToFirst()) {
            return true;
        }
        if (cursor.isClosed()) {
            return false;
        }
        cursor.close();
        return false;
    }

    public static ArrayList lambdagetCabinList43(CabinInfoCursor cabinInfoCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (cabinInfoCursor.getCount() > 0 && cabinInfoCursor.moveToFirst()) {
            do {
                arrayList.add(cabinInfoCursor.get());
            } while (cabinInfoCursor.moveToNext());
        }
        if (!cabinInfoCursor.isClosed()) {
            cabinInfoCursor.close();
        }
        return arrayList;
    }

    public static void lambdagetCabinList44(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getProductWareHouseDetail(String str, ResponseListener<ArrayList<ProductDetail.Ambar>> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda164
            public final String f0;

            public SqlManagerExternalSyntheticLambda164(String str2) {
                r1 = str2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda165
            public final String f0;

            public SqlManagerExternalSyntheticLambda165(String str2) {
                r1 = str2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetProductWareHouseDetail46;
                lambdagetProductWareHouseDetail46 = SqlManager.lambdagetProductWareHouseDetail46(r1, (String) obj);
                return lambdagetProductWareHouseDetail46;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda166
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetProductWareHouseDetail47;
                lambdagetProductWareHouseDetail47 = SqlManager.lambdagetProductWareHouseDetail47((Cursor) obj);
                return lambdagetProductWareHouseDetail47;
            }
        }).map(new SqlManagerExternalSyntheticLambda126()).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda167
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetProductWareHouseDetail48;
                lambdagetProductWareHouseDetail48 = SqlManager.lambdagetProductWareHouseDetail48(( ProductDetailStockCursor) obj);
                return lambdagetProductWareHouseDetail48;
            }
        }).defaultIfEmpty(new ArrayList()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new NetsisExternalSyntheticLambda6(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda168
            public SqlManagerExternalSyntheticLambda168() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetProductWareHouseDetail49(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Cursor lambdagetProductWareHouseDetail46(String str, String str2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(str);
    }

    public static boolean lambdagetProductWareHouseDetail47(Cursor cursor) throws Exception {
        return cursor != null && cursor.moveToFirst();
    }

    public static ArrayList lambdagetProductWareHouseDetail48(ProductDetailStockCursor productDetailStockCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (productDetailStockCursor.getCount() > 0 && productDetailStockCursor.moveToFirst()) {
            do {
                ProductDetail.Ambar ambar = productDetailStockCursor.get();
                if (TextUtils.isEmpty(ambar.getVarintCode())) {
                    arrayList.add(ambar);
                } else {
                    if (arrayList.size() == 0) {
                        arrayList.add(ambar);
                    }
                    Iterator it = arrayList.iterator();
                    boolean z = false;
                    while (it.hasNext()) {
                        ProductDetail.Ambar ambar2 = (ProductDetail.Ambar) it.next();
                        if (ambar2.getAmbarName().equals(ambar.getAmbarName())) {
                            if (ambar2.getVariantStocks() == null) {
                                ambar2.setVariantStocks(new ArrayList());
                            }
                            ItemVariantStock itemVariantStock = new ItemVariantStock();
                            itemVariantStock.setVarintCode(ambar.getVarintCode());
                            itemVariantStock.setVariantName(ambar.getVariantName());
                            itemVariantStock.setVariantActualStok(ambar.getVariantActualStok());
                            itemVariantStock.setVariantRealStok(ambar.getVariantRealStok());
                            ambar2.getVariantStocks().add(itemVariantStock);
                            z = true;
                        }
                    }
                    if (!z) {
                        ambar.setVariantStocks(new ArrayList());
                        ItemVariantStock itemVariantStock2 = new ItemVariantStock();
                        itemVariantStock2.setVarintCode(ambar.getVarintCode());
                        itemVariantStock2.setVariantName(ambar.getVariantName());
                        itemVariantStock2.setVariantActualStok(ambar.getVariantActualStok());
                        itemVariantStock2.setVariantRealStok(ambar.getVariantRealStok());
                        ambar.getVariantStocks().add(itemVariantStock2);
                        arrayList.add(ambar);
                    }
                }
            } while (productDetailStockCursor.moveToNext());
        }
        if (!productDetailStockCursor.isClosed()) {
            productDetailStockCursor.close();
        }
        return arrayList;
    }

    public static void lambdagetProductWareHouseDetail49(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getProductPriceDetail(String str, ResponseListener<ArrayList<ProductDetail.ItemPrice>> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda131
            public final String f0;

            public SqlManagerExternalSyntheticLambda131(String str2) {
                r1 = str2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda132
            public final String f0;

            public SqlManagerExternalSyntheticLambda132(String str2) {
                r1 = str2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetProductPriceDetail51;
                lambdagetProductPriceDetail51 = SqlManager.lambdagetProductPriceDetail51(r1, (String) obj);
                return lambdagetProductPriceDetail51;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda133
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetProductPriceDetail52;
                lambdagetProductPriceDetail52 = SqlManager.lambdagetProductPriceDetail52((Cursor) obj);
                return lambdagetProductPriceDetail52;
            }
        }).map(new SqlManagerExternalSyntheticLambda103()).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda134
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetProductPriceDetail53;
                lambdagetProductPriceDetail53 = SqlManager.lambdagetProductPriceDetail53(( ProductDetailPriceCursor) obj);
                return lambdagetProductPriceDetail53;
            }
        }).defaultIfEmpty(new ArrayList()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new NetsisExternalSyntheticLambda6(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda135
            public SqlManagerExternalSyntheticLambda135() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetProductPriceDetail54(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Cursor lambdagetProductPriceDetail51(String str, String str2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(str);
    }

    public static boolean lambdagetProductPriceDetail52(Cursor cursor) throws Exception {
        return cursor != null && cursor.moveToFirst();
    }

    public static ArrayList lambdagetProductPriceDetail53(ProductDetailPriceCursor productDetailPriceCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (productDetailPriceCursor.getCount() > 0 && productDetailPriceCursor.moveToFirst()) {
            do {
                arrayList.add(productDetailPriceCursor.get());
            } while (productDetailPriceCursor.moveToNext());
        }
        if (!productDetailPriceCursor.isClosed()) {
            productDetailPriceCursor.close();
        }
        return arrayList;
    }

    public static void lambdagetProductPriceDetail54(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getTodoList(String str, ResponseListener<ArrayList<TodoInfoDb>> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda187
            public final String f0;

            public SqlManagerExternalSyntheticLambda187(String str2) {
                r1 = str2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda188
            public final String f0;

            public SqlManagerExternalSyntheticLambda188(String str2) {
                r1 = str2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetTodoList56;
                lambdagetTodoList56 = SqlManager.lambdagetTodoList56(r1, (String) obj);
                return lambdagetTodoList56;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda189
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetTodoList57;
                lambdagetTodoList57 = SqlManager.lambdagetTodoList57((Cursor) obj);
                return lambdagetTodoList57;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda190
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return new TodoCursor((Cursor) obj);
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda191
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetTodoList58;
                lambdagetTodoList58 = SqlManager.lambdagetTodoList58(( TodoCursor) obj);
                return lambdagetTodoList58;
            }
        }).defaultIfEmpty(new ArrayList()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new NetsisExternalSyntheticLambda6(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda192
            public SqlManagerExternalSyntheticLambda192() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetTodoList59(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Cursor lambdagetTodoList56(String str, String str2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(str);
    }

    public static boolean lambdagetTodoList57(Cursor cursor) throws Exception {
        if (cursor != null && cursor.moveToFirst()) {
            return true;
        }
        if (cursor.isClosed()) {
            return false;
        }
        cursor.close();
        return false;
    }

    public static ArrayList lambdagetTodoList58(TodoCursor todoCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (todoCursor.getCount() > 0 && todoCursor.moveToFirst()) {
            do {
                arrayList.add(todoCursor.get());
            } while (todoCursor.moveToNext());
        }
        if (!todoCursor.isClosed()) {
            todoCursor.close();
        }
        return arrayList;
    }

    public static void lambdagetTodoList59(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getDailyInfoList(String str, ResponseListener<ArrayList<DailyInfo>> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda136
            public final String f0;

            public SqlManagerExternalSyntheticLambda136(String str2) {
                r1 = str2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda137
            public final String f0;

            public SqlManagerExternalSyntheticLambda137(String str2) {
                r1 = str2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetDailyInfoList61;
                lambdagetDailyInfoList61 = SqlManager.lambdagetDailyInfoList61(r1, (String) obj);
                return lambdagetDailyInfoList61;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda138
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetDailyInfoList62;
                lambdagetDailyInfoList62 = SqlManager.lambdagetDailyInfoList62((Cursor) obj);
                return lambdagetDailyInfoList62;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda139
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return new DailyInfoCursor((Cursor) obj);
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda140
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetDailyInfoList63;
                lambdagetDailyInfoList63 = SqlManager.lambdagetDailyInfoList63(( DailyInfoCursor) obj);
                return lambdagetDailyInfoList63;
            }
        }).defaultIfEmpty(new ArrayList()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new NetsisExternalSyntheticLambda6(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda141
            public SqlManagerExternalSyntheticLambda141() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetDailyInfoList64(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Cursor lambdagetDailyInfoList61(String str, String str2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(str);
    }

    public static boolean lambdagetDailyInfoList62(Cursor cursor) throws Exception {
        if (cursor != null && cursor.moveToFirst()) {
            return true;
        }
        if (cursor.isClosed()) {
            return false;
        }
        cursor.close();
        return false;
    }

    public static ArrayList lambdagetDailyInfoList63(DailyInfoCursor dailyInfoCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (dailyInfoCursor.getCount() > 0 && dailyInfoCursor.moveToFirst()) {
            do {
                arrayList.add(dailyInfoCursor.get());
            } while (dailyInfoCursor.moveToNext());
        }
        if (!dailyInfoCursor.isClosed()) {
            dailyInfoCursor.close();
        }
        return arrayList;
    }

    public static void lambdagetDailyInfoList64(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public String getShipAddressDefinition(int i2) {
        List<T> table;
        if (i2 <= 0 || (table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ShipAddress.class, "LOGICALREF=?", new String[]{String.valueOf(i2)})) == null || table.size() <= 0) {
            return "";
        }
        return table.get(0).toString();
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public String getShipAddressDefinitionWithCode(String str) {
        List<T> table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ShipAddress.class, "CODE=?", new String[]{String.valueOf(str)});
        if (table != null && table.size() > 0) {
            return table.get(0).toString();
        }
        return "";
    }

    public static ObservableSource lambdagetSalesOrderList65() throws Exception {
        return Observable.just(Boolean.TRUE);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getSalesOrderList(ResponseListener<List<Sales>> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource lambdagetSalesOrderList65;
                lambdagetSalesOrderList65 = SqlManager.lambdagetSalesOrderList65();
                return lambdagetSalesOrderList65;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda1
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                List lambdagetSalesOrderList66;
                lambdagetSalesOrderList66 = SqlManager.lambdagetSalesOrderList66((Boolean) obj);
                return lambdagetSalesOrderList66;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda2
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetSalesOrderList67;
                lambdagetSalesOrderList67 = SqlManager.lambdagetSalesOrderList67((List) obj);
                return lambdagetSalesOrderList67;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda3
            public SqlManagerExternalSyntheticLambda3() {
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetSalesOrderList68;
                lambdagetSalesOrderList68 = SqlManager.this.lambdagetSalesOrderList68((List) obj);
                return lambdagetSalesOrderList68;
            }
        }).defaultIfEmpty(new ArrayList()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new NetsisExternalSyntheticLambda6(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda4
            public SqlManagerExternalSyntheticLambda4() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetSalesOrderList69(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static List lambdagetSalesOrderList66(Boolean bool) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Order.class);
    }

    public static boolean lambdagetSalesOrderList67(List list) throws Exception {
        return list != null && list.size() == 1;
    }

    public ArrayList lambdagetSalesOrderList68(List list) throws Exception {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            arrayList.add(mapCursorToSalesOrder(list, i2));
        }
        return arrayList;
    }

    public static void lambdagetSalesOrderList69(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    private Sales mapCursorToSalesOrder(List<Order> list, int i2) {
        List<T> table;
        String str;
        String str2;
        String str3;
        String stringResource;
        String str4;
        boolean z;
        new Sales();
        Sales convertSalesFicheToSales = list.get(i2).convertSalesFicheToSales();
        Cursor cursor = null;
        try {
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.qry_get_sales_order_edit), StringUtils.convertIntToString(USER.logicalRef));
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
                        convertSalesFicheToSales.setTrRate(cursor.getDouble(cursor.getColumnIndex("TRRATE")));
                        convertSalesFicheToSales.setTrCurr(cursor.getInt(cursor.getColumnIndex("TRCURR")));
                        convertSalesFicheToSales.getErpInvoiceType().setLogicalRef(cursor.getColumnIndex("ERPINVOICETYPE"));
                        convertSalesFicheToSales.setCampaingRefs(cursor.getString(cursor.getColumnIndex("CAMPAIGNREFS")));
                        convertSalesFicheToSales.getReserved().setSelect(cursor.getInt(cursor.getColumnIndex("RESERVED")) == 1);
                    } catch (Exception e2) {
                        e = e2;
                        Log.e("AA", "getSalesOrderOne: ", e);
                        cursor.close();
                        table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(OrderDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(USER.logicalRef)});
                        if (!ErpCreator.getInstance().getmBaseErp().getUseProductNameDescription()) {
                        }
                        if (table != null) {
                            String str5 = "";
                            while (r11.hasNext()) {
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
            table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(OrderDetail.class, "SALESFICHEID=?", new String[]{String.valueOf(USER.logicalRef)});
            String str6 = !ErpCreator.getInstance().getmBaseErp().getUseProductNameDescription() ? "I.NAME2" : "I.NAME";
            if (table != null && table.size() > 0) {
                String str52 = "";
                for (T t : table) {
                    if (t.lineType == LineType.SERVICE.value) {
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
                    Cursor query = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.qry_get_sales_order_detail_edit, str4, stringResource, str3, str6, str2, str), StringUtils.convertIntToString(t.logicalRef));
                    SalesDetail convertSalesFicheDetailToSalesDetail = t.convertSalesFicheDetailToSalesDetail();
                    if (query != null) {
                        try {
                        } catch (Exception e4) {
                            e = e4;
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
                            convertSalesFicheDetailToSalesDetail.getVariant().setCode(query.getString(query.getColumnIndex("VCODE")));
                            FicheStringProp.setDefinition(query.getString(query.getColumnIndex("VNAME")));
                            z = true;
                            try {
                                convertSalesFicheDetailToSalesDetail.setDivUnit(query.getInt(query.getColumnIndex("DIVUNIT")) == 1);
                            } catch (Exception e5) {
                                e = e5;
                                Log.e("AA", "getSalesOrderOne: ", e);
                                if (query == null) {
                                }
                                convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                                str52 = str;
                            }
                            if (query == null && !query.isClosed()) {
                                query.close();
                            }
                            convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
                            str52 = str;
                        }
                    }
                    z = true;
                    if (query == null) {
                        query.close();
                    }
                    convertSalesFicheToSales.getMSalesDetailList().add(convertSalesFicheDetailToSalesDetail);
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

    public static ObservableSource lambdadeleteFactSalesFicheLocal70() throws Exception {
        return Observable.just(Integer.valueOf(R.layout.sales));
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void deleteFactSalesFicheLocal(int i2, SalesType salesType) {
        Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda77
            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource lambdadeleteFactSalesFicheLocal70;
                lambdadeleteFactSalesFicheLocal70 = SqlManager.lambdadeleteFactSalesFicheLocal70();
                return lambdadeleteFactSalesFicheLocal70;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda78
            public final int f0;
            public final SalesType f1;

            public SqlManagerExternalSyntheticLambda78(int i22, SalesType salesType2) {
                r1 = i22;
                r2 = salesType2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Integer lambdadeleteFactSalesFicheLocal71;
                lambdadeleteFactSalesFicheLocal71 = SqlManager.lambdadeleteFactSalesFicheLocal71(r1, r2, (Integer) obj);
                return lambdadeleteFactSalesFicheLocal71;
            }
        }).defaultIfEmpty(0).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<Integer>() { // from class: com.proje.mobilesales.core.sql.SqlManager.1
            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onNext(Object num) {
            }

            C25641() {
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                Log.d(SqlManager.TAG, "onCompleted: ");
            }
        });
    }

    public static Integer lambdadeleteFactSalesFicheLocal71(int i2, SalesType salesType, Integer num) throws Exception {
        if (ErpCreator.getInstance().getmBaseErp().deleteSalesLocal(i2, salesType)) {
            return num;
        }
        return 0;
    }

    /* renamed from: com.proje.mobilesales.core.sql.SqlManager1 */
    class C25641 extends DisposableObserver<Integer> {
        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
        }

        @Override // io.reactivex.Observer
        public void onNext(Object num) {
        }

        C25641() {
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            Log.d(SqlManager.TAG, "onCompleted: ");
        }
    }

    public static ObservableSource lambdadeleteSalesFicheLocal72() throws Exception {
        return Observable.just(Integer.valueOf(R.layout.sales));
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void deleteSalesFicheLocal(int i2, SalesType salesType, int i3, ResponseListener<Integer> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda181
            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource lambdadeleteSalesFicheLocal72;
                lambdadeleteSalesFicheLocal72 = SqlManager.lambdadeleteSalesFicheLocal72();
                return lambdadeleteSalesFicheLocal72;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda182
            public final int f0;
            public final SalesType f1;
            public final int f2;

            public SqlManagerExternalSyntheticLambda182(int i22, SalesType salesType2, int i32) {
                r1 = i22;
                r2 = salesType2;
                r3 = i32;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Integer lambdadeleteSalesFicheLocal73;
                lambdadeleteSalesFicheLocal73 = SqlManager.lambdadeleteSalesFicheLocal73(r1, r2, r3, (Integer) obj);
                return lambdadeleteSalesFicheLocal73;
            }
        }).defaultIfEmpty(0).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SqlManagerExternalSyntheticLambda32(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda183
            public SqlManagerExternalSyntheticLambda183() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdadeleteSalesFicheLocal74(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Integer lambdadeleteSalesFicheLocal73(int i2, SalesType salesType, int i3, Integer num) throws Exception {
        if (ErpCreator.getInstance().getmBaseErp().deleteSalesLocal(i2, salesType)) {
            return Integer.valueOf(i3);
        }
        return 0;
    }

    public static void lambdadeleteSalesFicheLocal74(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void deleteSalesListFicheLocal(List<Integer> list, SalesType salesType, ResponseListener<List<Integer>> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda120
            public final List f0;

            public SqlManagerExternalSyntheticLambda120(List list2) {
                r1 = list2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda121
            public SqlManagerExternalSyntheticLambda121() {
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                List lambdadeleteSalesListFicheLocal76;
                lambdadeleteSalesListFicheLocal76 = SqlManager.lambdadeleteSalesListFicheLocal76(SalesType.this, (List) obj);
                return lambdadeleteSalesListFicheLocal76;
            }
        }).defaultIfEmpty(Collections.emptyList()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new BaseErpExternalSyntheticLambda12(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda122
            public SqlManagerExternalSyntheticLambda122() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdadeleteSalesListFicheLocal77(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static List lambdadeleteSalesListFicheLocal76(SalesType salesType, List list) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().deleteMultiSalesLocal(list, salesType) ? list : Collections.emptyList();
    }

    public static void lambdadeleteSalesListFicheLocal77(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    public static ObservableSource lambdadeleteReceiptFicheLocal78() throws Exception {
        return Observable.just(Integer.valueOf(R.layout.sales));
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void deleteReceiptFicheLocal(int i2, ReceiptType receiptType, int i3, ResponseListener<Integer> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda30
            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource lambdadeleteReceiptFicheLocal78;
                lambdadeleteReceiptFicheLocal78 = SqlManager.lambdadeleteReceiptFicheLocal78();
                return lambdadeleteReceiptFicheLocal78;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda31
            public final int f0;
            public final ReceiptType f1;
            public final int f2;

            public SqlManagerExternalSyntheticLambda31(int i22, ReceiptType receiptType2, int i32) {
                r1 = i22;
                r2 = receiptType2;
                r3 = i32;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Integer lambdadeleteReceiptFicheLocal79;
                lambdadeleteReceiptFicheLocal79 = SqlManager.lambdadeleteReceiptFicheLocal79(r1, r2, r3, (Integer) obj);
                return lambdadeleteReceiptFicheLocal79;
            }
        }).defaultIfEmpty(0).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SqlManagerExternalSyntheticLambda32(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda33
            public SqlManagerExternalSyntheticLambda33() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdadeleteReceiptFicheLocal80(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Integer lambdadeleteReceiptFicheLocal79(int i2, ReceiptType receiptType, int i3, Integer num) throws Exception {
        if (ErpCreator.getInstance().getmBaseErp().deleteReceiptFicheLocal(i2, receiptType)) {
            return Integer.valueOf(i3);
        }
        return 0;
    }

    public static void lambdadeleteReceiptFicheLocal80(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    public static ObservableSource lambdadeleteVisitFicheLocal81() throws Exception {
        return Observable.just(Integer.valueOf(R.layout.sales));
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void deleteVisitFicheLocal(int i2, int i3, ResponseListener<Integer> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda128
            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource lambdadeleteVisitFicheLocal81;
                lambdadeleteVisitFicheLocal81 = SqlManager.lambdadeleteVisitFicheLocal81();
                return lambdadeleteVisitFicheLocal81;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda129
            public final int f0;
            public final int f1;

            public SqlManagerExternalSyntheticLambda129(int i22, int i32) {
                r1 = i22;
                r2 = i32;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Integer lambdadeleteVisitFicheLocal82;
                lambdadeleteVisitFicheLocal82 = SqlManager.lambdadeleteVisitFicheLocal82(r1, r2, (Integer) obj);
                return lambdadeleteVisitFicheLocal82;
            }
        }).defaultIfEmpty(0).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SqlManagerExternalSyntheticLambda32(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda130
            public SqlManagerExternalSyntheticLambda130() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdadeleteVisitFicheLocal83(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Integer lambdadeleteVisitFicheLocal82(int i2, int i3, Integer num) throws Exception {
        if (ErpCreator.getInstance().getmBaseErp().deleteVisit(i2)) {
            return Integer.valueOf(i3);
        }
        return 0;
    }

    public static void lambdadeleteVisitFicheLocal83(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    public static ObservableSource lambdadeletePenetrationFicheLocal84() throws Exception {
        return Observable.just(Integer.valueOf(R.layout.sales));
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void deletePenetrationFicheLocal(int i2, int i3, ResponseListener<Integer> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda169
            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource lambdadeletePenetrationFicheLocal84;
                lambdadeletePenetrationFicheLocal84 = SqlManager.lambdadeletePenetrationFicheLocal84();
                return lambdadeletePenetrationFicheLocal84;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda170
            public final int f0;
            public final int f1;

            public SqlManagerExternalSyntheticLambda170(int i22, int i32) {
                r1 = i22;
                r2 = i32;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Integer lambdadeletePenetrationFicheLocal85;
                lambdadeletePenetrationFicheLocal85 = SqlManager.lambdadeletePenetrationFicheLocal85(r1, r2, (Integer) obj);
                return lambdadeletePenetrationFicheLocal85;
            }
        }).defaultIfEmpty(0).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SqlManagerExternalSyntheticLambda32(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda171
            public SqlManagerExternalSyntheticLambda171() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdadeletePenetrationFicheLocal86(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Integer lambdadeletePenetrationFicheLocal85(int i2, int i3, Integer num) throws Exception {
        if (ErpCreator.getInstance().getmBaseErp().deletePenetration(i2)) {
            return Integer.valueOf(i3);
        }
        return 0;
    }

    public static void lambdadeletePenetrationFicheLocal86(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    public static ObservableSource lambdagetCustomer87(int i2) throws Exception {
        return Observable.just(Integer.valueOf(i2));
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public Disposable getCustomer(int i2, ResponseListener<CustomerNew> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda82
            public final int f0;

            public SqlManagerExternalSyntheticLambda82(int i22) {
                r1 = i22;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource lambdagetCustomer87;
                lambdagetCustomer87 = SqlManager.lambdagetCustomer87(r1);
                return lambdagetCustomer87;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda83
            public final int f0;

            public SqlManagerExternalSyntheticLambda83(int i22) {
                r1 = i22;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                List lambdagetCustomer88;
                lambdagetCustomer88 = SqlManager.lambdagetCustomer88(r1, (Integer) obj);
                return lambdagetCustomer88;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda84
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetCustomer89;
                lambdagetCustomer89 = SqlManager.lambdagetCustomer89((List) obj);
                return lambdagetCustomer89;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda85
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                CustomerNew lambdagetCustomer90;
                lambdagetCustomer90 = SqlManager.lambdagetCustomer90((List) obj);
                return lambdagetCustomer90;
            }
        }).doOnNext(new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda86
            public final int f0;

            public SqlManagerExternalSyntheticLambda86(int i22) {
                r1 = i22;
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetCustomer91(r1, (CustomerNew) obj);
            }
        }).defaultIfEmpty(new CustomerNew()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        return observeOn.subscribe(new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda87
            public SqlManagerExternalSyntheticLambda87() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                ResponseListener.this.onResponse((CustomerNew) obj);
            }
        }, new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda88
            public SqlManagerExternalSyntheticLambda88() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetCustomer92(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static List lambdagetCustomer88(int i2, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ClCard.class, "LOGICALREF=?", new String[]{String.valueOf(i2)});
    }

    public static boolean lambdagetCustomer89(List list) throws Exception {
        return list != null && list.size() == 1;
    }

    public static CustomerNew lambdagetCustomer90(List list) throws Exception {
        new CustomerNew();
        return ((ClCard) list.get(0)).convert();
    }

    public static void lambdagetCustomer91(int i2, CustomerNew customerNew) throws Exception {
        List<T> table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(CustomerImage.class, "CUSTOMERREF=?", new String[]{String.valueOf(i2)});
        if (table != null && table.size() > 0) {
            customerNew.setImage(new FicheImageProp());
            customerNew.getImage().setImageArray(((CustomerImage) table.get(0)).getCustomerImage());
        } else {
            customerNew.setImage(new FicheImageProp());
        }
    }

    public static void lambdagetCustomer92(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public Disposable saveFactSalesFiche(Sales sales, SalesType salesType, ResponseListener<Long> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda142
            public SqlManagerExternalSyntheticLambda142() {
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(Sales.this);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda143
            public SqlManagerExternalSyntheticLambda143() {
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Long lambdasaveFactSalesFiche94;
                lambdasaveFactSalesFiche94 = SqlManager.lambdasaveFactSalesFiche94(SalesType.this, (Sales) obj);
                return lambdasaveFactSalesFiche94;
            }
        }).defaultIfEmpty(1L).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        return observeOn.subscribe(new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda144
            public SqlManagerExternalSyntheticLambda144() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                ResponseListener.this.onResponse((Long) obj);
            }
        }, new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda145
            public SqlManagerExternalSyntheticLambda145() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdasaveFactSalesFiche95(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Long lambdasaveFactSalesFiche94(SalesType salesType, Sales sales) throws Exception {
        return Long.valueOf(ErpCreator.getInstance().getmBaseErp().saveFactSales(sales, salesType));
    }

    public static void lambdasaveFactSalesFiche95(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void saveSalesFiche(Sales sales, SalesType salesType, ResponseListener<Boolean> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda97
            public SqlManagerExternalSyntheticLambda97() {
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(Sales.this);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda98
            public SqlManagerExternalSyntheticLambda98() {
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Boolean lambdasaveSalesFiche97;
                lambdasaveSalesFiche97 = SqlManager.lambdasaveSalesFiche97(SalesType.this, (Sales) obj);
                return lambdasaveSalesFiche97;
            }
        }).defaultIfEmpty(Boolean.FALSE).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SendCreatorImplExternalSyntheticLambda3(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda99
            public SqlManagerExternalSyntheticLambda99() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdasaveSalesFiche98(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Boolean lambdasaveSalesFiche97(SalesType salesType, Sales sales) throws Exception {
        return Boolean.valueOf(ErpCreator.getInstance().getmBaseErp().saveSales(sales, salesType));
    }

    public static void lambdasaveSalesFiche98(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void updateSalesFiche(Sales sales, SalesType salesType, ResponseListener<Boolean> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda178
            public SqlManagerExternalSyntheticLambda178() {
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(Sales.this);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda179
            public SqlManagerExternalSyntheticLambda179() {
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Boolean lambdaupdateSalesFiche100;
                lambdaupdateSalesFiche100 = SqlManager.lambdaupdateSalesFiche100(SalesType.this, (Sales) obj);
                return lambdaupdateSalesFiche100;
            }
        }).defaultIfEmpty(Boolean.FALSE).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SendCreatorImplExternalSyntheticLambda3(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda180
            public SqlManagerExternalSyntheticLambda180() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdaupdateSalesFiche101(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Boolean lambdaupdateSalesFiche100(SalesType salesType, Sales sales) throws Exception {
        return Boolean.valueOf(ErpCreator.getInstance().getmBaseErp().updateSales(sales, salesType));
    }

    public static void lambdaupdateSalesFiche101(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void saveCashCreditFiche(CashCreditFiche cashCreditFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda105
            public SqlManagerExternalSyntheticLambda105() {
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(CashCreditFiche.this);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda106
            public SqlManagerExternalSyntheticLambda106() {
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Boolean lambdasaveCashCreditFiche103;
                lambdasaveCashCreditFiche103 = SqlManager.lambdasaveCashCreditFiche103(ReceiptType.this, (CashCreditFiche) obj);
                return lambdasaveCashCreditFiche103;
            }
        }).defaultIfEmpty(Boolean.FALSE).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SendCreatorImplExternalSyntheticLambda3(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda107
            public SqlManagerExternalSyntheticLambda107() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdasaveCashCreditFiche104(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Boolean lambdasaveCashCreditFiche103(ReceiptType receiptType, CashCreditFiche cashCreditFiche) throws Exception {
        return Boolean.valueOf(ErpCreator.getInstance().getmBaseErp().saveCashCreditFiche(cashCreditFiche, receiptType));
    }

    public static void lambdasaveCashCreditFiche104(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void updateCashCreditFiche(CashCreditFiche cashCreditFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda64
            public SqlManagerExternalSyntheticLambda64() {
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(CashCreditFiche.this);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda65
            public SqlManagerExternalSyntheticLambda65() {
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Boolean lambdaupdateCashCreditFiche106;
                lambdaupdateCashCreditFiche106 = SqlManager.lambdaupdateCashCreditFiche106(ReceiptType.this, (CashCreditFiche) obj);
                return lambdaupdateCashCreditFiche106;
            }
        }).defaultIfEmpty(Boolean.FALSE).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SendCreatorImplExternalSyntheticLambda3(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda66
            public SqlManagerExternalSyntheticLambda66() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdaupdateCashCreditFiche107(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Boolean lambdaupdateCashCreditFiche106(ReceiptType receiptType, CashCreditFiche cashCreditFiche) throws Exception {
        return Boolean.valueOf(ErpCreator.getInstance().getmBaseErp().updateCashCreditFiche(cashCreditFiche, receiptType));
    }

    public static void lambdaupdateCashCreditFiche107(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void saveCaseFiche(CaseFiche caseFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda184
            public SqlManagerExternalSyntheticLambda184() {
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(CaseFiche.this);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda185
            public SqlManagerExternalSyntheticLambda185() {
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Boolean lambdasaveCaseFiche109;
                lambdasaveCaseFiche109 = SqlManager.lambdasaveCaseFiche109(ReceiptType.this, (CaseFiche) obj);
                return lambdasaveCaseFiche109;
            }
        }).defaultIfEmpty(Boolean.FALSE).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SendCreatorImplExternalSyntheticLambda3(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda186
            public SqlManagerExternalSyntheticLambda186() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdasaveCaseFiche110(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Boolean lambdasaveCaseFiche109(ReceiptType receiptType, CaseFiche caseFiche) throws Exception {
        return Boolean.valueOf(ErpCreator.getInstance().getmBaseErp().saveCaseFiche(caseFiche, receiptType));
    }

    public static void lambdasaveCaseFiche110(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void updateCaseFiche(CaseFiche caseFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda39
            public SqlManagerExternalSyntheticLambda39() {
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(CaseFiche.this);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda40
            public SqlManagerExternalSyntheticLambda40() {
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Boolean lambdaupdateCaseFiche112;
                lambdaupdateCaseFiche112 = SqlManager.lambdaupdateCaseFiche112(ReceiptType.this, (CaseFiche) obj);
                return lambdaupdateCaseFiche112;
            }
        }).defaultIfEmpty(Boolean.FALSE).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SendCreatorImplExternalSyntheticLambda3(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda41
            public SqlManagerExternalSyntheticLambda41() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdaupdateCaseFiche113(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Boolean lambdaupdateCaseFiche112(ReceiptType receiptType, CaseFiche caseFiche) throws Exception {
        return Boolean.valueOf(ErpCreator.getInstance().getmBaseErp().updateCaseFiche(caseFiche, receiptType));
    }

    public static void lambdaupdateCaseFiche113(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void saveChequeDeedFiche(ChequeDeedFiche chequeDeedFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda54
            public SqlManagerExternalSyntheticLambda54() {
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(ChequeDeedFiche.this);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda55
            public SqlManagerExternalSyntheticLambda55() {
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Boolean lambdasaveChequeDeedFiche115;
                lambdasaveChequeDeedFiche115 = SqlManager.lambdasaveChequeDeedFiche115(ReceiptType.this, (ChequeDeedFiche) obj);
                return lambdasaveChequeDeedFiche115;
            }
        }).defaultIfEmpty(Boolean.FALSE).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SendCreatorImplExternalSyntheticLambda3(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda56
            public SqlManagerExternalSyntheticLambda56() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdasaveChequeDeedFiche116(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Boolean lambdasaveChequeDeedFiche115(ReceiptType receiptType, ChequeDeedFiche chequeDeedFiche) throws Exception {
        return Boolean.valueOf(ErpCreator.getInstance().getmBaseErp().saveChequeDeedFiche(chequeDeedFiche, receiptType));
    }

    public static void lambdasaveChequeDeedFiche116(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void updateChequeDeedFiche(ChequeDeedFiche chequeDeedFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda11
            public SqlManagerExternalSyntheticLambda11() {
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(ChequeDeedFiche.this);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda12
            public SqlManagerExternalSyntheticLambda12() {
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Boolean lambdaupdateChequeDeedFiche118;
                lambdaupdateChequeDeedFiche118 = SqlManager.lambdaupdateChequeDeedFiche118(ReceiptType.this, (ChequeDeedFiche) obj);
                return lambdaupdateChequeDeedFiche118;
            }
        }).defaultIfEmpty(Boolean.FALSE).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SendCreatorImplExternalSyntheticLambda3(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda13
            public SqlManagerExternalSyntheticLambda13() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdaupdateChequeDeedFiche119(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Boolean lambdaupdateChequeDeedFiche118(ReceiptType receiptType, ChequeDeedFiche chequeDeedFiche) throws Exception {
        return Boolean.valueOf(ErpCreator.getInstance().getmBaseErp().updateChequeDeedFiche(chequeDeedFiche, receiptType));
    }

    public static void lambdaupdateChequeDeedFiche119(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void saveVisitFiche(Visit visit, ResponseListener<Boolean> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda175
            public SqlManagerExternalSyntheticLambda175() {
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(Visit.this);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda176
            public SqlManagerExternalSyntheticLambda176() {
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Boolean lambdasaveVisitFiche121;
                lambdasaveVisitFiche121 = SqlManager.lambdasaveVisitFiche121(Visit.this, (Visit) obj);
                return lambdasaveVisitFiche121;
            }
        }).defaultIfEmpty(Boolean.FALSE).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SendCreatorImplExternalSyntheticLambda3(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda177
            public SqlManagerExternalSyntheticLambda177() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdasaveVisitFiche122(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Boolean lambdasaveVisitFiche121(Visit visit, Visit visit2) throws Exception {
        return Boolean.valueOf(ErpCreator.getInstance().getmBaseErp().saveVisit(visit));
    }

    public static void lambdasaveVisitFiche122(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void updateVisitFiche(Visit visit, ResponseListener<Boolean> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda172
            public SqlManagerExternalSyntheticLambda172() {
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(Visit.this);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda173
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Boolean lambdaupdateVisitFiche124;
                lambdaupdateVisitFiche124 = SqlManager.lambdaupdateVisitFiche124((Visit) obj);
                return lambdaupdateVisitFiche124;
            }
        }).defaultIfEmpty(Boolean.FALSE).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SendCreatorImplExternalSyntheticLambda3(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda174
            public SqlManagerExternalSyntheticLambda174() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdaupdateVisitFiche125(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Boolean lambdaupdateVisitFiche124(Visit visit) throws Exception {
        return Boolean.valueOf(ErpCreator.getInstance().getmBaseErp().updateVisit(visit));
    }

    public static void lambdaupdateVisitFiche125(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void savePenetrationFiche(Penetration penetration, ResponseListener<Boolean> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda79
            public SqlManagerExternalSyntheticLambda79() {
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(Penetration.this);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda80
            public SqlManagerExternalSyntheticLambda80() {
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Boolean lambdasavePenetrationFiche127;
                lambdasavePenetrationFiche127 = SqlManager.lambdasavePenetrationFiche127(Penetration.this, (Penetration) obj);
                return lambdasavePenetrationFiche127;
            }
        }).defaultIfEmpty(Boolean.FALSE).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SendCreatorImplExternalSyntheticLambda3(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda81
            public SqlManagerExternalSyntheticLambda81() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdasavePenetrationFiche128(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Boolean lambdasavePenetrationFiche127(Penetration penetration, Penetration penetration2) throws Exception {
        return Boolean.valueOf(ErpCreator.getInstance().getmBaseErp().savePenetration(penetration));
    }

    public static void lambdasavePenetrationFiche128(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void updatePenetrationFiche(Penetration penetration, ResponseListener<Boolean> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda89
            public SqlManagerExternalSyntheticLambda89() {
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(Penetration.this);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda90
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Boolean lambdaupdatePenetrationFiche130;
                lambdaupdatePenetrationFiche130 = SqlManager.lambdaupdatePenetrationFiche130((Penetration) obj);
                return lambdaupdatePenetrationFiche130;
            }
        }).defaultIfEmpty(Boolean.FALSE).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SendCreatorImplExternalSyntheticLambda3(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda91
            public SqlManagerExternalSyntheticLambda91() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdaupdatePenetrationFiche131(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Boolean lambdaupdatePenetrationFiche130(Penetration penetration) throws Exception {
        return Boolean.valueOf(ErpCreator.getInstance().getmBaseErp().updatePenetration(penetration));
    }

    public static void lambdaupdatePenetrationFiche131(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    public static ObservableSource lambdagetBarcodeResult132(int i2) throws Exception {
        return Observable.just(Integer.valueOf(i2));
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getBarcodeResult(Context context, ResponseListener responseListener, @StringRes int i2, @StringRes int i3, @NonNull String... strArr) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda17
            public final int f0;

            public SqlManagerExternalSyntheticLambda17(int i22) {
                r1 = i22;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource lambdagetBarcodeResult132;
                lambdagetBarcodeResult132 = SqlManager.lambdagetBarcodeResult132(r1);
                return lambdagetBarcodeResult132;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda18
            public final Context f0;
            public final int f1;
            public final String[] f2;

            public SqlManagerExternalSyntheticLambda18(Context context2, int i22, String[] strArr2) {
                r1 = context2;
                r2 = i22;
                r3 = strArr2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetBarcodeResult133;
                lambdagetBarcodeResult133 = SqlManager.lambdagetBarcodeResult133(r1, r2, r3, (Integer) obj);
                return lambdagetBarcodeResult133;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda19
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetBarcodeResult134;
                lambdagetBarcodeResult134 = SqlManager.lambdagetBarcodeResult134((Cursor) obj);
                return lambdagetBarcodeResult134;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda20
            public final Context f0;
            public final int f1;

            public SqlManagerExternalSyntheticLambda20(Context context2, int i32) {
                r1 = context2;
                r2 = i32;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                BarcodeNew lambdagetBarcodeResult135;
                lambdagetBarcodeResult135 = SqlManager.lambdagetBarcodeResult135(r1, r2, (Cursor) obj);
                return lambdagetBarcodeResult135;
            }
        }).defaultIfEmpty(new BarcodeNew()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda21
            public SqlManagerExternalSyntheticLambda21() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                ResponseListener.this.onResponse((BarcodeNew) obj);
            }
        }, new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda22
            public SqlManagerExternalSyntheticLambda22() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetBarcodeResult136(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Cursor lambdagetBarcodeResult133(Context context, int i2, String[] strArr, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(context.getString(i2, strArr));
    }

    public static boolean lambdagetBarcodeResult134(Cursor cursor) throws Exception {
        return cursor != null && cursor.moveToFirst();
    }

    public static BarcodeNew lambdagetBarcodeResult135(Context context, int i2, Cursor cursor) throws Exception {
        BarcodeNew barcodeNew = new BarcodeNew();
        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            barcodeNew.setItemRef(cursor.getInt(cursor.getColumnIndex(context.getString(i2))));
            barcodeNew.setCode(cursor.getString(cursor.getColumnIndex("CODE")));
            barcodeNew.setName(cursor.getString(cursor.getColumnIndex("NAME")));
        }
        cursor.close();
        return barcodeNew;
    }

    public static void lambdagetBarcodeResult136(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "Sql Manager Get One Error Int");
    }

    public BarcodeProduct getSingleBarcodeResult(Context context, int i2, int i3, @NonNull String... strArr) {
        BarcodeProduct barcodeProduct = new BarcodeProduct();
        Cursor cursor = null;
        try {
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(context.getString(i2, strArr));
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    barcodeProduct.setItemRef(cursor.getInt(cursor.getColumnIndex(context.getString(i3))));
                    barcodeProduct.setVariant(cursor.getString(cursor.getColumnIndex("VARIANT")));
                }
            } catch (Exception e2) {
                Log.e(TAG, e2.getMessage());
                if (cursor != null) {
                }
            }
            return barcodeProduct;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    public BarcodeCustomer getSingleCabinBarcodeCustomerResult(Context context, int i2, @NonNull String... strArr) {
        BarcodeCustomer barcodeCustomer = new BarcodeCustomer();
        Cursor cursor = null;
        try {
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(context.getString(i2, strArr));
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    barcodeCustomer.setCabinBarcode(cursor.getString(cursor.getColumnIndex("CABINBARCODE")));
                    barcodeCustomer.setCabinRef(cursor.getInt(cursor.getColumnIndex("CABINREF")));
                    barcodeCustomer.setLogicalRef(cursor.getInt(cursor.getColumnIndex("CLREF")));
                    barcodeCustomer.setCode(cursor.getString(cursor.getColumnIndex("CLCODE")));
                }
            } catch (Exception e2) {
                Log.e(TAG, e2.getMessage());
                if (cursor != null) {
                }
            }
            return barcodeCustomer;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    public BarcodeCustomer getSingleAvailableCabinFromBarcode(Context context, int i2, @NonNull String... strArr) {
        BarcodeCustomer barcodeCustomer = new BarcodeCustomer();
        Cursor cursor = null;
        try {
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(context.getString(i2, strArr));
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    barcodeCustomer.setCabinBarcode(cursor.getString(cursor.getColumnIndex("CABINBARCODE")));
                    barcodeCustomer.setCabinRef(cursor.getInt(cursor.getColumnIndex("CABINREF")));
                    barcodeCustomer.setCabinCode(cursor.getString(cursor.getColumnIndex("CABINCODE")));
                }
            } catch (Exception e2) {
                Log.e(TAG, e2.getMessage());
                if (cursor != null) {
                }
            }
            return barcodeCustomer;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    public static ObservableSource lambdagetOne137(int i2) throws Exception {
        return Observable.just(Integer.valueOf(i2));
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void getOne(Context context, ResponseListener responseListener, @StringRes int i2, @StringRes int i3, @NonNull String... strArr) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda193
            public final int f0;

            public SqlManagerExternalSyntheticLambda193(int i22) {
                r1 = i22;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource lambdagetOne137;
                lambdagetOne137 = SqlManager.lambdagetOne137(r1);
                return lambdagetOne137;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda194
            public final Context f0;
            public final int f1;
            public final String[] f2;

            public SqlManagerExternalSyntheticLambda194(Context context2, int i22, String[] strArr2) {
                r1 = context2;
                r2 = i22;
                r3 = strArr2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetOne138;
                lambdagetOne138 = SqlManager.lambdagetOne138(r1, r2, r3, (Integer) obj);
                return lambdagetOne138;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda195
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetOne139;
                lambdagetOne139 = SqlManager.lambdagetOne139((Cursor) obj);
                return lambdagetOne139;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda196
            public final Context f0;
            public final int f1;

            public SqlManagerExternalSyntheticLambda196(Context context2, int i32) {
                r1 = context2;
                r2 = i32;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Integer lambdagetOne140;
                lambdagetOne140 = SqlManager.lambdagetOne140(r1, r2, (Cursor) obj);
                return lambdagetOne140;
            }
        }).defaultIfEmpty(-1).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SqlManagerExternalSyntheticLambda32(responseListener), new Consumer() {
            public SqlManagerExternalSyntheticLambda197() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetOne141(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Cursor lambdagetOne138(Context context, int i2, String[] strArr, Integer num) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(context.getString(i2), strArr);
    }

    public static boolean lambdagetOne139(Cursor cursor) throws Exception {
        return cursor != null && cursor.moveToFirst();
    }

    public static Integer lambdagetOne140(Context context, int i2, Cursor cursor) throws Exception {
        int i3 = (cursor.getCount() <= 0 || !cursor.moveToFirst()) ? -1 : cursor.getInt(cursor.getColumnIndex(context.getString(i2)));
        cursor.close();
        return Integer.valueOf(i3);
    }

    public static void lambdagetOne141(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "Sql Manager Get One Error Int");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public Disposable getPenetrationList(String str, ResponseListener<ArrayList<PenetrationShort>> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda5
            public final String f0;

            public SqlManagerExternalSyntheticLambda5(String str2) {
                r1 = str2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda6
            public final String f0;

            public SqlManagerExternalSyntheticLambda6(String str2) {
                r1 = str2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetPenetrationList143;
                lambdagetPenetrationList143 = SqlManager.lambdagetPenetrationList143(r1, (String) obj);
                return lambdagetPenetrationList143;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda7
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetPenetrationList144;
                lambdagetPenetrationList144 = SqlManager.lambdagetPenetrationList144((Cursor) obj);
                return lambdagetPenetrationList144;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda8
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return new PenetrationInfoShortCursor((Cursor) obj);
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda9
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetPenetrationList145;
                lambdagetPenetrationList145 = SqlManager.lambdagetPenetrationList145(( PenetrationInfoShortCursor) obj);
                return lambdagetPenetrationList145;
            }
        }).defaultIfEmpty(new ArrayList()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        return observeOn.subscribe(new NetsisExternalSyntheticLambda6(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda10
            public SqlManagerExternalSyntheticLambda10() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetPenetrationList146(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Cursor lambdagetPenetrationList143(String str, String str2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(str);
    }

    public static boolean lambdagetPenetrationList144(Cursor cursor) throws Exception {
        if (cursor != null && cursor.moveToFirst()) {
            return true;
        }
        if (cursor.isClosed()) {
            return false;
        }
        cursor.close();
        return false;
    }

    public static ArrayList lambdagetPenetrationList145(PenetrationInfoShortCursor penetrationInfoShortCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (penetrationInfoShortCursor.getCount() > 0 && penetrationInfoShortCursor.moveToFirst()) {
            do {
                arrayList.add(penetrationInfoShortCursor.get());
            } while (penetrationInfoShortCursor.moveToNext());
        }
        if (!penetrationInfoShortCursor.isClosed()) {
            penetrationInfoShortCursor.close();
        }
        return arrayList;
    }

    public static void lambdagetPenetrationList146(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public Disposable getTableList(Class<?> cls, String str, String[] strArr, ResponseListener<List<T>> responseListener) {
        return getTableList(cls, str, strArr, null, null, null, responseListener);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public Disposable getTableList(Class<?> cls, String str, @Nullable String[] strArr, @Nullable String str2, @Nullable String str3, @Nullable String str4, PenetrationActivity.PenetrationLineResponseListener responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda146
            public final Class f0;

            public SqlManagerExternalSyntheticLambda146(Class cls2) {
                r1 = cls2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda147
            public final Class f0;
            public final String f1;
            public final String[] f2;
            public final String f3;
            public final String f4;
            public final String f5;

            public SqlManagerExternalSyntheticLambda147(Class cls2, String str5, String[] strArr2, String str22, String str32, String str42) {
                r1 = cls2;
                r2 = str5;
                r3 = strArr2;
                r4 = str22;
                r5 = str32;
                r6 = str42;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                List lambdagetTableList148;
                lambdagetTableList148 = SqlManager.lambdagetTableList148(r1, r2, r3, r4, r5, r6, (Class) obj);
                return lambdagetTableList148;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda148
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetTableList149;
                lambdagetTableList149 = SqlManager.lambdagetTableList149((List) obj);
                return lambdagetTableList149;
            }
        }).defaultIfEmpty(new ArrayList()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        return observeOn.subscribe(new BaseErpExternalSyntheticLambda12(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda149
            public SqlManagerExternalSyntheticLambda149() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetTableList150(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static List lambdagetTableList148(Class cls, String str, String[] strArr, String str2, String str3, String str4, Class cls2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(cls, str, strArr, str2, str3, str4);
    }

    public static boolean lambdagetTableList149(List list) throws Exception {
        return list != null && list.size() > 0;
    }

    public static void lambdagetTableList150(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<Sales> getSalesList() {
        return mapCursorToSalesOrderTest(0);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<Sales> getSalesList(int i2) {
        return mapCursorToSalesOrderTest(i2, 0);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<Sales> getDemandList() {
        return mapCursorToSalesOrderTest(1);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<Sales> getDemandList(int i2) {
        return mapCursorToSalesOrderTest(i2, 1);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<Sales> mapCursorToSalesOrderTest(int i2) {
        return mapCursorToSalesOrderTest(-1, i2);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<Sales> mapCursorToInvoice(SalesType salesType) {
        return mapCursorToInvoice(salesType, -1);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<Sales> getReturnInvoice(int i2) {
        return mapCursorToInvoice(SalesType.RETURN_INVOICE, i2);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<Sales> getInvoice(int i2) {
        return mapCursorToInvoice(SalesType.INVOICE, i2);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<Sales> getReturnDispatch(int i2) {
        return mapCursorToInvoice(SalesType.RETURN_DISPATCH, i2);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<Sales> getRetailReturnInvoice(int i2) {
        return mapCursorToInvoice(SalesType.RETAIL_RETURN_INVOICE, i2);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<Sales> getRetailInvoice(int i2) {
        return mapCursorToInvoice(SalesType.RETAIL_INVOICE, i2);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<Sales> getDispatch(int i2) {
        return mapCursorToInvoice(SalesType.DISPATCH, i2);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<Sales> getOneToOne(int i2) {
        return mapCursorToInvoice(SalesType.ONE_TO_ONE_CHANGE, i2);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<Sales> mapCursorToInvoice(SalesType salesType, int i2) {
        int i3 = 1;
        if (!SalesUtils.isSalesTypeOnlyInvoice(salesType)) {
            if (SalesUtils.isSalesTypeOnlyReturnInvoice(salesType)) {
                r2 = 1;
            } else if (SalesUtils.isSalesTypeDispatch(salesType)) {
                i3 = 0;
            } else if (SalesUtils.isSalesTypeReturnDispatch(salesType)) {
                r2 = 1;
                i3 = 0;
            } else if (SalesUtils.isSalesTypeOneToOne(salesType)) {
                i3 = 2;
            } else {
                if (!SalesUtils.isSalesTypeOnlyRetailInvoice(salesType)) {
                    r2 = SalesUtils.isSalesTypeOnlyRetailReturnInvoice(salesType) ? 1 : 0;
                }
                i3 = 3;
            }
        }
        return mapCursorToInvoice(salesType, i3, r2, i2);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<CaseCash> getCaseCashs() {
        return getCaseCashs(-1);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<ChequeDeed> getCheques() {
        return getChequesAndDeed(0, -1);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<ChequeDeed> getCheques(int i2) {
        return getChequesAndDeed(0, i2);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<ChequeDeed> getDeeds() {
        return getChequesAndDeed(1, -1);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<ChequeDeed> getDeeds(int i2) {
        return getChequesAndDeed(1, i2);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<CashCreditX> getCashes() {
        return getCreditAndCash(0, -1);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<CashCreditX> getCashes(int i2) {
        return getCreditAndCash(0, i2);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<CashCreditX> getCreditCards() {
        return getCreditAndCash(1, -1);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<CashCreditX> getCreditCards(int i2) {
        return getCreditAndCash(1, i2);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<VisitInfo> getVisits() {
        return getVisits(-1);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<TodoInfoDb> getTodos() {
        return getTodos(-1);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<Penetration> getPenetrations() {
        return getPenetrations(-1);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void saveNewCustomer(CustomerNew customerNew, ResponseListener<Boolean> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda161
            public SqlManagerExternalSyntheticLambda161() {
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(CustomerNew.this);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda162
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Boolean lambdasaveNewCustomer152;
                lambdasaveNewCustomer152 = SqlManager.lambdasaveNewCustomer152((CustomerNew) obj);
                return lambdasaveNewCustomer152;
            }
        }).defaultIfEmpty(Boolean.FALSE).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SendCreatorImplExternalSyntheticLambda3(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda163
            public SqlManagerExternalSyntheticLambda163() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdasaveNewCustomer153(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Boolean lambdasaveNewCustomer152(CustomerNew customerNew) throws Exception {
        return Boolean.valueOf(ErpCreator.getInstance().getmBaseErp().saveCustomer(customerNew));
    }

    public static void lambdasaveNewCustomer153(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    public static ObservableSource lambdaupdateNewCustomer154() throws Exception {
        return Observable.just(Integer.valueOf(R.layout.sales));
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public void updateNewCustomer(CustomerNew customerNew, ResponseListener<Boolean> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda14
            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource lambdaupdateNewCustomer154;
                lambdaupdateNewCustomer154 = SqlManager.lambdaupdateNewCustomer154();
                return lambdaupdateNewCustomer154;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda15
            public SqlManagerExternalSyntheticLambda15() {
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Boolean lambdaupdateNewCustomer155;
                lambdaupdateNewCustomer155 = SqlManager.lambdaupdateNewCustomer155(CustomerNew.this, (Integer) obj);
                return lambdaupdateNewCustomer155;
            }
        }).defaultIfEmpty(Boolean.FALSE).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        observeOn.subscribe(new SendCreatorImplExternalSyntheticLambda3(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda16
            public SqlManagerExternalSyntheticLambda16() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdaupdateNewCustomer156(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Boolean lambdaupdateNewCustomer155(CustomerNew customerNew, Integer num) throws Exception {
        return Boolean.valueOf(ErpCreator.getInstance().getmBaseErp().updateCustomer(customerNew));
    }

    public static void lambdaupdateNewCustomer156(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<CustomerNew> getNewCustomers() {
        return getNewCustomers(-1);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<CabinTrans> getCabinTrans() {
        return getCabinTrans(-1);
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public List<CabinTrans> getCabinTrans(int i2) {
        if (i2 == -1) {
            return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTableWhere(CabinTrans.class, "ISTRANSFER=? AND TRTYPE=?", new String[]{"0", "9"});
        }
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTableWhere(CabinTrans.class, "ISTRANSFER=? AND TRTYPE=? AND ID=?", new String[]{"0", "9", String.valueOf(i2)});
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public Disposable getProductGroups(String str, ResponseListener<ArrayList<ProductGroupModel>> responseListener) {
        Observable<T> observeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda48
            public final String f0;

            public SqlManagerExternalSyntheticLambda48(String str2) {
                r1 = str2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda49
            public final String f0;

            public SqlManagerExternalSyntheticLambda49(String str2) {
                r1 = str2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetProductGroups158;
                lambdagetProductGroups158 = SqlManager.lambdagetProductGroups158(r1, (String) obj);
                return lambdagetProductGroups158;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda50
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetProductGroups159;
                lambdagetProductGroups159 = SqlManager.lambdagetProductGroups159((Cursor) obj);
                return lambdagetProductGroups159;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda51
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return new ProductGroupModelCursor((Cursor) obj);
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda52
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetProductGroups160;
                lambdagetProductGroups160 = SqlManager.lambdagetProductGroups160(( ProductGroupModelCursor) obj);
                return lambdagetProductGroups160;
            }
        }).defaultIfEmpty(new ArrayList()).subscribeOn(this.mIoScheduler).observeOn(AndroidSchedulers.mainThread());
        Objects.requireNonNull(responseListener);
        return observeOn.subscribe(new NetsisExternalSyntheticLambda6(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda53
            public SqlManagerExternalSyntheticLambda53() {
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) {
                SqlManager.lambdagetProductGroups161(ResponseListener.this, (Throwable) obj);
            }
        });
    }

    public static Cursor lambdagetProductGroups158(String str, String str2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(str);
    }

    public static boolean lambdagetProductGroups159(Cursor cursor) throws Exception {
        if (cursor != null && cursor.moveToFirst()) {
            return true;
        }
        if (cursor.isClosed()) {
            return false;
        }
        cursor.close();
        return false;
    }

    public static ArrayList lambdagetProductGroups160(ProductGroupModelCursor productGroupModelCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (productGroupModelCursor.getCount() > 0 && productGroupModelCursor.moveToFirst()) {
            do {
                arrayList.add(productGroupModelCursor.get());
            } while (productGroupModelCursor.moveToNext());
        }
        if (!productGroupModelCursor.isClosed()) {
            productGroupModelCursor.close();
        }
        return arrayList;
    }

    public static void lambdagetProductGroups161(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public Observable<ArrayList<ProductDetail>> getProductDetailObservable(String str) {
        return Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda34
            public final String f0;

            public SqlManagerExternalSyntheticLambda34(String str2) {
                r1 = str2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda35
            public final String f0;

            public SqlManagerExternalSyntheticLambda35(String str2) {
                r1 = str2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetProductDetailObservable163;
                lambdagetProductDetailObservable163 = SqlManager.lambdagetProductDetailObservable163(r1, (String) obj);
                return lambdagetProductDetailObservable163;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda36
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetProductDetailObservable164;
                lambdagetProductDetailObservable164 = SqlManager.lambdagetProductDetailObservable164((Cursor) obj);
                return lambdagetProductDetailObservable164;
            }
        }).map(new SqlManagerExternalSyntheticLambda37()).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda38
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetProductDetailObservable165;
                lambdagetProductDetailObservable165 = SqlManager.lambdagetProductDetailObservable165(( ProductDetailCursor) obj);
                return lambdagetProductDetailObservable165;
            }
        });
    }

    public static Cursor lambdagetProductDetailObservable163(String str, String str2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(str);
    }

    public static ArrayList lambdagetProductDetailObservable165(ProductDetailCursor productDetailCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (productDetailCursor.getCount() > 0 && productDetailCursor.moveToFirst()) {
            do {
                arrayList.add(productDetailCursor.get());
            } while (productDetailCursor.moveToNext());
        }
        if (!productDetailCursor.isClosed()) {
            productDetailCursor.close();
        }
        return arrayList;
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public Observable<ArrayList<ProductDetail.Ambar>> getProductWareHouseDetailObservable(String str) {
        return Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda123
            public final String f0;

            public SqlManagerExternalSyntheticLambda123(String str2) {
                r1 = str2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda124
            public final String f0;

            public SqlManagerExternalSyntheticLambda124(String str2) {
                r1 = str2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetProductWareHouseDetailObservable167;
                lambdagetProductWareHouseDetailObservable167 = SqlManager.lambdagetProductWareHouseDetailObservable167(r1, (String) obj);
                return lambdagetProductWareHouseDetailObservable167;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda125
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetProductWareHouseDetailObservable168;
                lambdagetProductWareHouseDetailObservable168 = SqlManager.lambdagetProductWareHouseDetailObservable168((Cursor) obj);
                return lambdagetProductWareHouseDetailObservable168;
            }
        }).map(new SqlManagerExternalSyntheticLambda126()).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda127
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetProductWareHouseDetailObservable169;
                lambdagetProductWareHouseDetailObservable169 = SqlManager.lambdagetProductWareHouseDetailObservable169(( ProductDetailStockCursor) obj);
                return lambdagetProductWareHouseDetailObservable169;
            }
        });
    }

    public static Cursor lambdagetProductWareHouseDetailObservable167(String str, String str2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(str);
    }

    public static ArrayList lambdagetProductWareHouseDetailObservable169(ProductDetailStockCursor productDetailStockCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (productDetailStockCursor.getCount() > 0 && productDetailStockCursor.moveToFirst()) {
            do {
                ProductDetail.Ambar ambar = productDetailStockCursor.get();
                if (TextUtils.isEmpty(ambar.getVarintCode())) {
                    arrayList.add(ambar);
                } else {
                    if (arrayList.size() == 0) {
                        arrayList.add(ambar);
                    }
                    Iterator it = arrayList.iterator();
                    boolean z = false;
                    while (it.hasNext()) {
                        ProductDetail.Ambar ambar2 = (ProductDetail.Ambar) it.next();
                        if (ambar2.getAmbarName().equals(ambar.getAmbarName())) {
                            if (ambar2.getVariantStocks() == null) {
                                ambar2.setVariantStocks(new ArrayList());
                            }
                            ItemVariantStock itemVariantStock = new ItemVariantStock();
                            itemVariantStock.setVarintCode(ambar.getVarintCode());
                            itemVariantStock.setVariantName(ambar.getVariantName());
                            itemVariantStock.setVariantActualStok(ambar.getVariantActualStok());
                            itemVariantStock.setVariantRealStok(ambar.getVariantRealStok());
                            ambar2.getVariantStocks().add(itemVariantStock);
                            z = true;
                        }
                    }
                    if (!z) {
                        ambar.setVariantStocks(new ArrayList());
                        ItemVariantStock itemVariantStock2 = new ItemVariantStock();
                        itemVariantStock2.setVarintCode(ambar.getVarintCode());
                        itemVariantStock2.setVariantName(ambar.getVariantName());
                        itemVariantStock2.setVariantActualStok(ambar.getVariantActualStok());
                        itemVariantStock2.setVariantRealStok(ambar.getVariantRealStok());
                        ambar.getVariantStocks().add(itemVariantStock2);
                        arrayList.add(ambar);
                    }
                }
            } while (productDetailStockCursor.moveToNext());
        }
        if (!productDetailStockCursor.isClosed()) {
            productDetailStockCursor.close();
        }
        return arrayList;
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public Observable<ArrayList<ProductDetail.ItemPrice>> getProductPriceDetailObservable(String str) {
        return Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda100
            public final String f0;

            public SqlManagerExternalSyntheticLambda100(String str2) {
                r1 = str2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda101
            public final String f0;

            public SqlManagerExternalSyntheticLambda101(String str2) {
                r1 = str2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetProductPriceDetailObservable171;
                lambdagetProductPriceDetailObservable171 = SqlManager.lambdagetProductPriceDetailObservable171(r1, (String) obj);
                return lambdagetProductPriceDetailObservable171;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda102
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetProductPriceDetailObservable172;
                lambdagetProductPriceDetailObservable172 = SqlManager.lambdagetProductPriceDetailObservable172((Cursor) obj);
                return lambdagetProductPriceDetailObservable172;
            }
        }).map(new SqlManagerExternalSyntheticLambda103()).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda104
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetProductPriceDetailObservable173;
                lambdagetProductPriceDetailObservable173 = SqlManager.lambdagetProductPriceDetailObservable173(( ProductDetailPriceCursor) obj);
                return lambdagetProductPriceDetailObservable173;
            }
        });
    }

    public static Cursor lambdagetProductPriceDetailObservable171(String str, String str2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(str);
    }

    public static ArrayList lambdagetProductPriceDetailObservable173(ProductDetailPriceCursor productDetailPriceCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (productDetailPriceCursor.getCount() > 0 && productDetailPriceCursor.moveToFirst()) {
            do {
                arrayList.add(productDetailPriceCursor.get());
            } while (productDetailPriceCursor.moveToNext());
        }
        if (!productDetailPriceCursor.isClosed()) {
            productDetailPriceCursor.close();
        }
        return arrayList;
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    public Observable<ArrayList<ProductDetail.DetailItemUnit>> getProductUnitDetailObservable(String str) {
        return Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda72
            public final String f0;

            public SqlManagerExternalSyntheticLambda72(String str2) {
                r1 = str2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                ObservableSource just;
                just = Observable.just(r1);
                return just;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda73
            public final String f0;

            public SqlManagerExternalSyntheticLambda73(String str2) {
                r1 = str2;
            }

            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                Cursor lambdagetProductUnitDetailObservable175;
                lambdagetProductUnitDetailObservable175 = SqlManager.lambdagetProductUnitDetailObservable175(r1, (String) obj);
                return lambdagetProductUnitDetailObservable175;
            }
        }).filter(new Predicate() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda74
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                boolean lambdagetProductUnitDetailObservable176;
                lambdagetProductUnitDetailObservable176 = SqlManager.lambdagetProductUnitDetailObservable176((Cursor) obj);
                return lambdagetProductUnitDetailObservable176;
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda75
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return new ProductDetailUnitCursor((Cursor) obj);
            }
        }).map(new Function() { // from class: com.proje.mobilesales.core.sql.SqlManagerExternalSyntheticLambda76
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                ArrayList lambdagetProductUnitDetailObservable177;
                lambdagetProductUnitDetailObservable177 = SqlManager.lambdagetProductUnitDetailObservable177(( ProductDetailUnitCursor) obj);
                return lambdagetProductUnitDetailObservable177;
            }
        });
    }

    public static Cursor lambdagetProductUnitDetailObservable175(String str, String str2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(str);
    }

    public static ArrayList lambdagetProductUnitDetailObservable177(ProductDetailUnitCursor productDetailUnitCursor) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (productDetailUnitCursor.getCount() > 0 && productDetailUnitCursor.moveToFirst()) {
            do {
                arrayList.add(productDetailUnitCursor.get());
            } while (productDetailUnitCursor.moveToNext());
        }
        if (!productDetailUnitCursor.isClosed()) {
            productDetailUnitCursor.close();
        }
        return arrayList;
    }

    @Override // com.proje.mobilesales.core.sql.ISqlManager
    @SuppressLint({HttpHeaders.RANGE})
    public ItemUnit getItemUnit(String str, int i2, SalesType salesType) {
        String itemSizeSql = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getItemSizeSql(salesType);
        ItemUnit itemUnit = new ItemUnit();
        try {
            Cursor query = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(itemSizeSql, str, String.valueOf(i2));
            if (query != null && query.getCount() > 0 && query.moveToFirst()) {
                itemUnit.width = query.getString(query.getColumnIndex("WIDTH"));
                itemUnit.length = query.getString(query.getColumnIndex("LENGTH"));
                itemUnit.height = query.getString(query.getColumnIndex("HEIGHT"));
            }
        } catch (Exception e2) {
            Log.e("AA", "getLocalInvoiceForPrint: ", e2);
        }
        return itemUnit;
    }
}
