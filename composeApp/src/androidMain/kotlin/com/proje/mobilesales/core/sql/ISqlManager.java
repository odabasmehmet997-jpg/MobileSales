package com.proje.mobilesales.core.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.google.android.gms.common.util.CollectionUtils;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MyCursorLoader;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.exception.ErpNotFoundException;
import com.proje.mobilesales.core.interfaces.ICursorGet;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.utils.CompressUtil;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
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
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import com.proje.mobilesales.features.dbmodel.ShipAddress;
import com.proje.mobilesales.features.gpsinfo.model.database.CustGpsInfo;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.penetration.model.Penetration;
import com.proje.mobilesales.features.penetration.model.database.PenetrationShort;
import com.proje.mobilesales.features.penetration.view.detail.PenetrationActivity;
import com.proje.mobilesales.features.product.model.ItemUnit;
import com.proje.mobilesales.features.product.model.Product;
import com.proje.mobilesales.features.product.model.ProductDetail;
import com.proje.mobilesales.features.product.model.ProductGroupModel;
import com.proje.mobilesales.features.product.model.ProductProcess;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesShort;
import com.proje.mobilesales.features.todo.model.database.TodoInfoDb;
import com.proje.mobilesales.features.userreport.model.database.UserReports;
import com.proje.mobilesales.features.visit.model.Visit;
import com.proje.mobilesales.features.visit.model.VisitInfoShort;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
 
public interface ISqlManager<T> {
    String ACTION_GET = "";
    String ACTION_GET_EXTRA_DATA = "";
    int LOADER = 0;
    static  void lambdaloadSalesFicheRef4(FicheRefProp ficheRefProp) throws Exception {
    }
    void deleteFactSalesFicheLocal(int i2, SalesType salesType);
    void deletePenetrationFicheLocal(int i2, int i3, ResponseListener<Integer> responseListener);
    void deleteReceiptFicheLocal(int i2, ReceiptType receiptType, int i3, ResponseListener<Integer> responseListener);

    void deleteSalesFicheLocal(int i2, SalesType salesType, int i3, ResponseListener<Integer> responseListener);

    void deleteSalesListFicheLocal(List<Integer> list, SalesType salesType, ResponseListener<List<Integer>> responseListener);

    void deleteVisitFicheLocal(int i2, int i3, ResponseListener<Integer> responseListener);

    void getBarcodeResult(Context context, ResponseListener responseListener, @StringRes int i2, @StringRes int i3, @NonNull String... strArr);

    Disposable getCabinList(String str, ResponseListener<ArrayList<Cabin>> responseListener);

    List<CabinTrans> getCabinTrans();

    List<CabinTrans> getCabinTrans(int i2);

    List<CaseCash> getCaseCashs();

    List<CaseCash> getCaseCashs(int i2);

    void getCaseFiche(int i2, ResponseListener<CaseFiche> responseListener);

    void getCaseFiche(int i2, ResponseListener<CaseFiche> responseListener, boolean z);

    void getCashCreditFiche(int i2, int i3, ResponseListener<CashCreditFiche> responseListener);

    void getCashCreditFiche(int i2, int i3, ResponseListener<CashCreditFiche> responseListener, boolean z);

    List<CashCreditX> getCashes();

    List<CashCreditX> getCashes(int i2);

    void getChequeDeedFiche(int i2, int i3, ResponseListener<ChequeDeedFiche> responseListener);

    void getChequeDeedFiche(int i2, int i3, ResponseListener<ChequeDeedFiche> responseListener, boolean z);

    List<ChequeDeed> getCheques();

    List<ChequeDeed> getCheques(int i2);

    List<ChequeDeed> getChequesAndDeed(int i2, int i3);

    List<CashCreditX> getCreditAndCash(int i2, int i3);

    List<CashCreditX> getCreditCards();

    List<CashCreditX> getCreditCards(int i2);

    Disposable getCustomer(int i2, ResponseListener<CustomerNew> responseListener);

    Disposable getCustomerList(String str, ResponseListener<ArrayList<Customer>> responseListener);

    void getDailyInfoList(String str, ResponseListener<ArrayList<DailyInfo>> responseListener);

    List<ChequeDeed> getDeeds();

    List<ChequeDeed> getDeeds(int i2);

    List<Sales> getDemandList();

    List<Sales> getDemandList(int i2);

    List<Sales> getDispatch(int i2);

    Disposable getEDocumentContent(int i2, ResponseListener responseListener, SalesType salesType);

    List<Sales> getInvoice(int i2);

    ItemUnit getItemUnit(String str, int i2, SalesType salesType);

    List<CustomerNew> getNewCustomers();

    List<CustomerNew> getNewCustomers(int i2);

    void getOne(Context context, ResponseListener responseListener, @StringRes int i2, @StringRes int i3, @NonNull String... strArr);

    void getOne(Context context, String str, ResponseListener<CustomerDetail> responseListener);

    List<Sales> getOneToOne(int i2);

    void getPenetrationExam(int i2, ResponseListener<Penetration> responseListener);

    Disposable getPenetrationList(String str, ResponseListener<ArrayList<PenetrationShort>> responseListener);

    List<Penetration> getPenetrations();

    List<Penetration> getPenetrations(int i2);

    void getProductDetail(String str, ResponseListener<ArrayList<ProductDetail>> responseListener);

    Observable<ArrayList<ProductDetail>> getProductDetailObservable(String str);

    Disposable getProductGroups(String str, ResponseListener<ArrayList<ProductGroupModel>> responseListener);

    Disposable getProductList(String str, int i2, ResponseListener<ArrayList<Product>> responseListener, String str2);

    void getProductPriceDetail(String str, ResponseListener<ArrayList<ProductDetail.ItemPrice>> responseListener);

    Observable<ArrayList<ProductDetail.ItemPrice>> getProductPriceDetailObservable(String str);

    Observable<ArrayList<ProductDetail.DetailItemUnit>> getProductUnitDetailObservable(String str);

    void getProductWareHouseDetail(String str, ResponseListener<ArrayList<ProductDetail.Ambar>> responseListener);

    Observable<ArrayList<ProductDetail.Ambar>> getProductWareHouseDetailObservable(String str);

    Disposable getReceiptCursorToList(Cursor cursor, ReceiptFicheListFragment.FicheListResponseListener responseListener);

    List<Sales> getRetailInvoice(int i2);

    List<Sales> getRetailReturnInvoice(int i2);

    List<Sales> getReturnDispatch(int i2);

    List<Sales> getReturnInvoice(int i2);

    Disposable getRouteList(String str, ResponseListener<ArrayList<Customer>> responseListener);

    Disposable getSalesCursorToList(Cursor cursor, ResponseListener<List<SalesShort>> responseListener);

    void getSalesInvoiceExam(int i2, ResponseListener<Sales> responseListener);

    List<Sales> getSalesList();

    List<Sales> getSalesList(int i2);

    void getSalesOrderList(ResponseListener<List<Sales>> responseListener);

    void getSalesOrderOne(int i2, ResponseListener<Sales> responseListener);

    void getSalesWhTransfer(int i2, ResponseListener<Sales> responseListener);

    String getShipAddressDefinition(int i2);

    String getShipAddressDefinitionWithCode(String str);

    BarcodeCustomer getSingleAvailableCabinFromBarcode(Context context, @StringRes int i2, @NonNull String... strArr);

    BarcodeProduct getSingleBarcodeResult(Context context, @StringRes int i2, @StringRes int i3, @NonNull String... strArr);

    BarcodeCustomer getSingleCabinBarcodeCustomerResult(Context context, @StringRes int i2, @NonNull String... strArr);

    Disposable getTableList(Class<?> cls, String str, String[] strArr, ResponseListener<List<T>> responseListener);

    Disposable getTableList(Class<?> cls, String str, @Nullable String[] strArr, @Nullable String str2, @Nullable String str3, @Nullable String str4, PenetrationActivity.PenetrationLineResponseListener responseListener);

    void getTodoList(String str, ResponseListener<ArrayList<TodoInfoDb>> responseListener);

    List<TodoInfoDb> getTodos();

    List<TodoInfoDb> getTodos(int i2);

    void getVisitExam(int i2, ResponseListener<Visit> responseListener);

    Disposable getVisitList(String str, ResponseListener<ArrayList<VisitInfoShort>> responseListener);

    List<VisitInfo> getVisits();

    List<VisitInfo> getVisits(int i2);

    List<Sales> mapCursorToInvoice(SalesType salesType);

    List<Sales> mapCursorToInvoice(SalesType salesType, int i2);

    List<Sales> mapCursorToInvoice(SalesType salesType, int i2, int i3, int i4);

    List<Sales> mapCursorToSalesOrderTest(int i2);

    List<Sales> mapCursorToSalesOrderTest(int i2, int i3);

    List<Sales> mapCursorToWhTransfer(int i2);

    void saveCaseFiche(CaseFiche caseFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener);

    void saveCashCreditFiche(CashCreditFiche cashCreditFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener);

    void saveChequeDeedFiche(ChequeDeedFiche chequeDeedFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener);

    Disposable saveFactSalesFiche(Sales sales, SalesType salesType, ResponseListener<Long> responseListener);

    void saveNewCustomer(CustomerNew customerNew, ResponseListener<Boolean> responseListener);

    void savePenetrationFiche(Penetration penetration, ResponseListener<Boolean> responseListener);

    void saveSalesFiche(Sales sales, SalesType salesType, ResponseListener<Boolean> responseListener);

    void saveVisitFiche(Visit visit, ResponseListener<Boolean> responseListener);

    void updateCaseFiche(CaseFiche caseFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener);

    void updateCashCreditFiche(CashCreditFiche cashCreditFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener);

    void updateChequeDeedFiche(ChequeDeedFiche chequeDeedFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener);

    void updateNewCustomer(CustomerNew customerNew, ResponseListener<Boolean> responseListener);

    void updatePenetrationFiche(Penetration penetration, ResponseListener<Boolean> responseListener);

    void updateSalesFiche(Sales sales, SalesType salesType, ResponseListener<Boolean> responseListener);

    void updateVisitFiche(Visit visit, ResponseListener<Boolean> responseListener);

    static void loadSalesFicheRef(FicheRefProp ficheRefProp, TextView textView, @StringRes int i2, @StringRes int i3) {
        Observable.defer(new Callable() {  
            public void ISqlManagerExternalSyntheticLambda0() {
            }
            public Object call() {
                ObservableSource just;
                just = Observable.just(FicheRefProp.this);
                return just;
            }
        }).map(new Function() {
            public final int f0;
            public final FicheRefProp f1;

            public void ISqlManagerExternalSyntheticLambda1(int i22, FicheRefProp ficheRefProp2) {
                r1 = i22;
                r2 = ficheRefProp2;
            }
            public Object apply(Object obj) {
                Cursor lambdaloadSalesFicheRef1;
                lambdaloadSalesFicheRef1 = ISqlManager.lambdaloadSalesFicheRef1(r1, r2, (FicheRefProp) obj);
                return lambdaloadSalesFicheRef1;
            }
        }).filter(new Predicate() {
            public boolean test(Object obj) {
                boolean lambdaloadSalesFicheRef2;
                try {
                    lambdaloadSalesFicheRef2 = ISqlManager.lambdaloadSalesFicheRef2((Cursor) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdaloadSalesFicheRef2;
            }
        }).map(new Function() {
            public final int f1;
            public final TextView f2;
            public void ISqlManagerExternalSyntheticLambda3(int i32, TextView textView2) {
                r2 = i32;
                r3 = textView2;
            }

            public Object apply(Object obj) {
                FicheRefProp lambdaloadSalesFicheRef3;
                try {
                    lambdaloadSalesFicheRef3 = ISqlManager.lambdaloadSalesFicheRef3(FicheRefProp.this, r2, r3, (Cursor) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdaloadSalesFicheRef3;
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    ISqlManager.lambdaloadSalesFicheRef4((FicheRefProp) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    static Cursor lambdaloadSalesFicheRef1(int i2, FicheRefProp ficheRefProp, FicheRefProp ficheRefProp2) throws Exception {
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(i2), StringUtils.convertIntToString(ficheRefProp.getLogicalRef()));
    }

    static boolean lambdaloadSalesFicheRef2(Cursor cursor) throws Exception {
        return cursor != null && cursor.getCount() > 0;
    }

    static FicheRefProp lambdaloadSalesFicheRef3(FicheRefProp ficheRefProp, int i2, TextView textView, Cursor cursor) throws Exception {
        if (cursor.moveToFirst()) {
            FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(ContextUtils.getStringResource(i2))));
        }
        textView.setText(ficheRefProp.toString());
        return ficheRefProp;
    }

    static ArrayList<CompositeDetail> getCompositeDetails(int i2) {
        ArrayList<CompositeDetail> arrayList = new ArrayList<>();
        CompositeDetailCursor compositeDetailCursor = null;
        ErpNotFoundException e;
        try {
            try {
                CompositeDetailCursor compositeDetailCursor2 = new CompositeDetailCursor(ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query("SELECT S.AMOUNT, S.STOCKREF, S.VAT, S.ITEMNAME AS NAME, S.ITEMCODE AS CODE, S.UNITLINEREF, S.UNITSETREF, S.CONVFACT1, S.CONVFACT2, S.UNITCODE, S.PRICE, S.PERC, S.VRNTREF, S.VRNTCODE FROM STCOMPLN S WHERE S.MAINCREF=?", String.valueOf(i2)));
                try {
                    if (compositeDetailCursor2.getCount() > 0 && compositeDetailCursor2.moveToFirst()) {
                        do {
                            arrayList.add(compositeDetailCursor2.get());
                        } while (compositeDetailCursor2.moveToNext());
                    }
                    compositeDetailCursor2.close();
                } catch (ErpNotFoundException e2) {
                    e = e2;
                    compositeDetailCursor = compositeDetailCursor2;
                    e.printStackTrace();
                    if (compositeDetailCursor != null) {
                        compositeDetailCursor.close();
                    }
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    compositeDetailCursor = compositeDetailCursor2;
                    if (compositeDetailCursor != null) {
                        compositeDetailCursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                Throwable th = th2;
            }
        } catch (ErpNotFoundException e3) {
            e = e3;
        }
        return arrayList;
    }
    class Loader extends MyCursorLoader {
        public Loader(Context context, String str) {
            super(context, str);
        }
    }
    class CustomerCursor extends CursorWrapper implements ICursorGet {
        public CustomerCursor(Cursor cursor) {
            super(cursor);
        }

        public Customer getCustomer() throws IOException {
            Customer customer = new Customer();
            try {
                customer.setLogicalRef(getInt(getColumnIndex("LOGICALREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LOGICALREF from cursor", e);
            }
            try {
                customer.setTitle(getString(getColumnIndex("DEFINITION_")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DEFINITION_ from cursor", e);
            }
            customer.setSecondTitle(getString(getColumnIndex("DEFINITION2")));
            try {
                customer.setCode(getString(getColumnIndex("CODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CODE from cursor", e);
            }
            customer.setCredit(getDouble(getColumnIndex(DailyInfo.CREDIT)));
            try {
                customer.setDebit(getDouble(getColumnIndex("DEBIT")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DEBIT from cursor", e);
            }
            try {
                customer.setBalance(getDouble(getColumnIndex("BAKIYE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving BAKIYE from cursor", e);
            }
            try {
                customer.setTel(getString(getColumnIndex("TELCODES1")) + getString(getColumnIndex("TELNRS1")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving TELCODES1 or TELNRS1 from cursor", e);
            }
            try {
                customer.setName(getString(getColumnIndex("NAME")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving NAME from cursor", e);
            }
            try {
                customer.setSurname(getString(getColumnIndex("SURNAME")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SURNAME from cursor", e);
            }
            try {
                customer.setTel2(getString(getColumnIndex("TELCODES2")) + getString(getColumnIndex("TELNRS2")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving TELCODES2 or TELNRS2 from cursor", e);
            }
            try {
                customer.setEmail(getString(getColumnIndex("EMAILADDR")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving EMAILADDR from cursor", e);
            }
            try {
                customer.setPerson(getString(getColumnIndex("INCHARGE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving INCHARGE from cursor", e);
            }
            try {
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving INCHARGE from cursor", e);
            }
            try {
                customer.setPayPlan(getString(getColumnIndex("PAYMENTDEF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving PAYMENTDEF from cursor", e);
            }
            try {
                customer.setLastOrderDate(getString(getColumnIndex("LASTORDERDATE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LASTORDERDATE from cursor", e);
            }
            try {
                customer.setLatitude(StringUtils.convertStringToDouble(getString(getColumnIndex("LATITUDE"))));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LATITUDE from cursor", e);
            }
            try {
                customer.setLongtitude(StringUtils.convertStringToDouble(getString(getColumnIndex("LONGTITUDE"))));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LONGTITUDE from cursor", e);
            }
            try {
                customer.setEInvoiceUser(getInt(getColumnIndex("ACCEPTEINV")) == 1);
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ACCEPTEINV from cursor", e);
            }
            try {
                customer.setImage(CompressUtil.deCompressBitmap(getBlob(getColumnIndex("CUSTOMERIMAGE"))));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CUSTOMERIMAGE from cursor", e);
            }
            if (customer.getSecondTitle() != null && customer.getTitle() != null && customer.getTitle().equals(customer.getSecondTitle())) {
                customer.setSecondTitle("");
            }
            customer.setTitle(customer.getTitle() + "-" + getString(getColumnIndex("SHIPNAME")));
            try {
                if (getColumnIndex("OPERATIONSTODAY") != -1) {
                    customer.setHasDailyOperation(getInt(getColumnIndex("OPERATIONSTODAY")) > 0);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving OPERATIONSTODAY from cursor", e);
            }
            return customer;
        }
        public Customer get() throws IOException {
            Customer customer = new Customer();
            try {
                customer.setLogicalRef(getInt(getColumnIndex("LOGICALREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LOGICALREF from cursor", e);
            }
            customer.setTitle(getString(getColumnIndex("DEFINITION_")));

            try {
                customer.setSecondTitle(getString(getColumnIndex("DEFINITION2")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DEFINITION2 from cursor", e);
            }
            try {
                customer.setCode(getString(getColumnIndex("CODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CODE from cursor", e);
            }
            try {
                customer.setCredit(getDouble(getColumnIndex(DailyInfo.CREDIT)));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving " + DailyInfo.CREDIT + " from cursor", e);
            }

            try {
                customer.setDebit(getDouble(getColumnIndex("DEBIT")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DEBIT from cursor", e);
            }
            try {
                customer.setBalance(getDouble(getColumnIndex("BAKIYE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving BAKIYE from cursor", e);
            }
            try {
                customer.setTel(getString(getColumnIndex("TELCODES1")) + getString(getColumnIndex("TELNRS1")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving TELCODES1 or TELNRS1 from cursor", e);
            }
            try {
                customer.setTel2(getString(getColumnIndex("TELCODES2")) + getString(getColumnIndex("TELNRS2")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving TELCODES2 or TELNRS2 from cursor", e);
            }
            try {
                customer.setName(getString(getColumnIndex("NAME")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving NAME from cursor", e);
            }
            try {
                customer.setSurname(getString(getColumnIndex("SURNAME")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SURNAME from cursor", e);
            }
            try {
                customer.setEmail(getString(getColumnIndex("EMAILADDR")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving EMAILADDR from cursor", e);
            }
            try {
                customer.setPerson(getString(getColumnIndex("INCHARGE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving INCHARGE from cursor", e);
            }
            try {
                customer.setLastOrderDate(getString(getColumnIndex("LASTORDERDATE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LASTORDERDATE from cursor", e);
            }
            try {
                customer.setPayPlan(getString(getColumnIndex("PAYMENTDEF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving PAYMENTDEF from cursor", e);
            }
            customer.setEInvoiceUser(StringUtils.convertIntToBoolean(getInt(getColumnIndex("ACCEPTEINV"))));

            try {
                customer.setLatitude(StringUtils.convertStringToDouble(getString(getColumnIndex("LATITUDE"))));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LATITUDE from cursor", e);
            }
            try {
                customer.setLongtitude(StringUtils.convertStringToDouble(getString(getColumnIndex("LONGTITUDE"))));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LONGTITUDE from cursor", e);
            }
            try {
                customer.setImage(CompressUtil.deCompressBitmap(getBlob(getColumnIndex("CUSTOMERIMAGE"))));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CUSTOMERIMAGE from cursor", e);
            }
            if (ErpCreator.getInstance().getmBaseErp().getErpType() == ErpType.NETSIS) {
                customer.setInfoNote(getString(getColumnIndex("INFONOTE")));
            } else {
                try {
                    customer.setInfoNote(StringUtils.convertBlobToString(getBlob(getColumnIndex("INFONOTE")), ContextUtils.getStringResource(R.string.xml_encoding)));
                } catch (Exception e) {
                    throw new RuntimeException("Error retrieving INFONOTE from cursor", e);
                }
            }
            if (customer.getSecondTitle() != null && customer.getTitle() != null && customer.getTitle().equals(customer.getSecondTitle())) {
                customer.setSecondTitle("");
            }
            try {
                int i2 = getInt(getColumnIndex("SHIPREF"));
                if (i2 != 0) {
                    customer.setShipRef(i2);
                    customer.setTitle(customer.getTitle() + "-" + getString(getColumnIndex("SHIPNAME")));
                }
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SHIPREF or SHIPNAME from cursor", e);
            }
            try {
                customer.setRouteDayRef(getInt(getColumnIndex("ROUTEDAYREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ROUTEDAYREF from cursor", e);
            }
            try {
                customer.setRoutePlanRef(getInt(getColumnIndex("ROUTEPLANREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ROUTEPLANREF from cursor", e);
            }
            try {
                customer.setRouteUserCustomerRef(getInt(getColumnIndex("ROUTEUSERCUSTOMERREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ROUTEUSERCUSTOMERREF from cursor", e);
            }
            try {
                if (getColumnIndex("OPERATIONSTODAY") != -1) {
                    customer.setHasDailyOperation(getInt(getColumnIndex("OPERATIONSTODAY")) > 0);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving OPERATIONSTODAY from cursor", e);
            }
            return customer;
        }

        public Customer getRoute() throws IOException {
            Customer customer = new Customer();
            customer.setTitle(getString(getColumnIndex("DEFINITION_")));
            customer.setCode(getString(getColumnIndex("CODE")));
            customer.setCity(getString(getColumnIndex("CITY")));
            customer.setTown(getString(getColumnIndex("TOWN")));
            customer.setShipName(getString(getColumnIndex("SHIPNAME")));
            return customer;
        }
    }
    class CustomerGPSCursor extends CursorWrapper implements ICursorGet {
        public CustomerGPSCursor(Cursor cursor) {
            super(cursor);
        }

        public CustGpsInfo get() {
            CustGpsInfo custGpsInfo = new CustGpsInfo();

            try {
                custGpsInfo.logicalRef = getInt(getColumnIndex("LOGICALREF"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LOGICALREF from cursor", e);
            }

            try {
                custGpsInfo.f1250id = getInt(getColumnIndex("ID"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ID from cursor", e);
            }

            if (ErpCreator.getInstance().getmBaseErp().getErpType() != ErpType.NETSIS) {
                try {
                    custGpsInfo.clientRef = getInt(getColumnIndex("CLIENTREF"));
                } catch (Exception e) {
                    throw new RuntimeException("Error retrieving CLIENTREF from cursor", e);
                }
            }
            try {
                custGpsInfo.clCode = getString(getColumnIndex("CLCODE"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CLCODE from cursor", e);
            }
            try {
                custGpsInfo.clName = getString(getColumnIndex("CLNAME"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CLNAME from cursor", e);
            }
            try {
                custGpsInfo.latitude = getDouble(getColumnIndex("LATITUDE"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LATITUDE from cursor", e);
            }
            try {
                custGpsInfo.longtitude = getDouble(getColumnIndex("LONGTITUDE"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LONGTITUDE from cursor", e);
            }
            try {
                custGpsInfo.isTransfer = getInt(getColumnIndex("ISTRANSFER"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ISTRANSFER from cursor", e);
            }
            custGpsInfo.latCos = getDouble(getColumnIndex("LAT_COS"));
            custGpsInfo.latSin = getDouble(getColumnIndex("LAT_SIN"));
            custGpsInfo.longCos = getDouble(getColumnIndex("LONG_COS"));
            custGpsInfo.longSin = getDouble(getColumnIndex("LONG_SIN"));
            custGpsInfo.shippingRef = getInt(getColumnIndex("SHIPINFOREF"));
            custGpsInfo.shipInfoCode = getString(getColumnIndex("SHIPINFOCODE"));
            return custGpsInfo;
        }
    }
    class CustomerDetailCursor extends CursorWrapper {
        CustomerDetailCursor(Cursor cursor) {
            super(cursor);
        }

        CustomerDetail getCustomerDetail() {
            CustomerDetail customerDetail = new CustomerDetail();
            try {
                customerDetail.setLogicalRef(getInt(getColumnIndex("LOGICALREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LOGICALREF from cursor", e);
            }
            try {
                customerDetail.setTitle(getString(getColumnIndex("DEFINITION_")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DEFINITION_ from cursor", e);
            }

            try {
                customerDetail.setSecondTitle(getString(getColumnIndex("DEFINITION2")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DEFINITION2 from cursor", e);
            }
            try {
                customerDetail.setCode(getString(getColumnIndex("CODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CODE from cursor", e);
            }
            customerDetail.setCredit(getDouble(getColumnIndex(DailyInfo.CREDIT)));
            try {
                customerDetail.setDebit(getDouble(getColumnIndex("DEBIT")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DEBIT from cursor", e);
            }
            try {
                customerDetail.setBalance(getDouble(getColumnIndex("BAKIYE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving BAKIYE from cursor", e);
            }
            customerDetail.setTel(getString(getColumnIndex("TELCODES1")) + getString(getColumnIndex("TELNRS1")));

            try {
                customerDetail.setTel2(getString(getColumnIndex("TELCODES2")) + getString(getColumnIndex("TELNRS2")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving TELCODES2 or TELNRS2 from cursor", e);
            }
            try {
                customerDetail.setEmail(getString(getColumnIndex("EMAILADDR")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving EMAILADDR from cursor", e);
            }
            try {
                customerDetail.setPerson(getString(getColumnIndex("INCHARGE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving INCHARGE from cursor", e);
            }
            try {
                customerDetail.setPerson2(getString(getColumnIndex("INCHARGE2")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving INCHARGE2 from cursor", e);
            }
            try {
                customerDetail.setPerson3(getString(getColumnIndex("INCHARGE3")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving INCHARGE3 from cursor", e);
            }
            customerDetail.setEInvoiceUser(getInt(getColumnIndex("ACCEPTEINV")) == 1);
            try {
                customerDetail.setSpeCode1(getString(getColumnIndex("SPECODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SPECODE from cursor", e);
            }
            try {
                customerDetail.setSpeCode2(getString(getColumnIndex("SPECODE2")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SPECODE2 from cursor", e);
            }
            try {
                customerDetail.setSpeCode3(getString(getColumnIndex("SPECODE3")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SPECODE3 from cursor", e);
            }
            try {
                customerDetail.setSpeCode4(getString(getColumnIndex("SPECODE4")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SPECODE4 from cursor", e);
            }
            try {
                customerDetail.setSpeCode5(getString(getColumnIndex("SPECODE5")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SPECODE5 from cursor", e);
            }
            customerDetail.setGroupCode(getString(getColumnIndex("GROUPCODE")));

            try {
                customerDetail.setTaxOffice(getString(getColumnIndex("TAXOFFICE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving TAXOFFICE from cursor", e);
            }
            try {
                customerDetail.setTaxNo(getString(getColumnIndex("TAXNR")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving TAXNR from cursor", e);
            }
            try {
                customerDetail.setTcNo(getString(getColumnIndex("TCKNO")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving TCKNO from cursor", e);
            }
            try {
                customerDetail.setWarrantyCode(getString(getColumnIndex("WARRANTYCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving WARRANTYCODE from cursor", e);
            }
            try {
                customerDetail.setPersonalCompany(StringUtils.convertIntToBoolean(getInt(getColumnIndex("ISPERSCOMP"))));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ISPERSCOMP from cursor", e);
            }
            try {
                customerDetail.setName(getString(getColumnIndex("NAME")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving NAME from cursor", e);
            }
            try {
                customerDetail.setSurname(getString(getColumnIndex("SURNAME")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SURNAME from cursor", e);
            }
            try {
                customerDetail.setTradingGroup(getString(getColumnIndex("TRADINGGRP")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving TRADINGGRP from cursor", e);
            }

            customerDetail.setAddress1(getString(getColumnIndex("ADDR1")));
            try {
                customerDetail.setAddress2(getString(getColumnIndex("ADDR2")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ADDR2 from cursor", e);
            }
            try {
                customerDetail.setCountryCode(getString(getColumnIndex("COUNTRYCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving COUNTRYCODE from cursor", e);
            }
            try {
                customerDetail.setCityCode(getString(getColumnIndex("CITYCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CITYCODE from cursor", e);
            }
            try {
                customerDetail.setTownCode(getString(getColumnIndex("TOWNCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving TOWNCODE from cursor", e);
            }
            if (ErpCreator.getInstance().getmBaseErp().getErpType() != ErpType.NETSIS) {
                customerDetail.setCountry(getString(getColumnIndex("COUNTRY")));
                customerDetail.setCity(getString(getColumnIndex("CITY")));
                customerDetail.setTown(getString(getColumnIndex("TOWN")));
            } else {
                customerDetail.setCountry(getString(getColumnIndex("COUNTRYCODE")));
                customerDetail.setCity(getString(getColumnIndex("CITYCODE")));
                customerDetail.setTown(getString(getColumnIndex("TOWNCODE")));
            }
            customerDetail.setSAddress1(getString(getColumnIndex("SSADDR1")));
            try {
                customerDetail.setSAddress2(getString(getColumnIndex("SSADDR2")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SSADDR2 from cursor", e);
            }
            customerDetail.setSCity(getString(getColumnIndex("SSCITY")));
            try {
                customerDetail.setSCode(getString(getColumnIndex("SSCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SSCODE from cursor", e);
            }
            try {
                customerDetail.setSRef(getInt(getColumnIndex("SHIPREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SHIPREF from cursor", e);
            }
            try {
                customerDetail.setDiscRate(getDouble(getColumnIndex("DISCRATE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DISCRATE from cursor", e);
            }
            try {
                customerDetail.setPaymentType(getInt(getColumnIndex("PAYMENTTYPE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving PAYMENTTYPE from cursor", e);
            }
            try {
                customerDetail.setDueDate(getInt(getColumnIndex("DUEDATE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DUEDATE from cursor", e);
            }
            try {
                if (customerDetail.getSecondTitle() != null && customerDetail.getTitle() != null && customerDetail.getTitle().equals(customerDetail.getSecondTitle())) {
                    customerDetail.setSecondTitle("");
                }
            } catch (Exception e) {
                throw new RuntimeException("Error setting second title for customer", e);
            }
            return customerDetail;
        }
    }
    class UserReportsCursor extends CursorWrapper implements ICursorGet {
        public UserReportsCursor(Cursor cursor) {
            super(cursor);
        }

        public UserReports get() {
            UserReports userReports = new UserReports();

            try {
                userReports.f1283id = getInt(getColumnIndex("ID"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ID from cursor", e);
            }
            try {
                userReports.repIndex = getInt(getColumnIndex("REPINDEX"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving REPINDEX from cursor", e);
            }
            try {
                userReports.reportName = getString(getColumnIndex("REPORTNAME"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving REPORTNAME from cursor", e);
            }
            try {
                userReports.setrType(getInt(getColumnIndex("RTYPE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving RTYPE from cursor", e);
            }
            try {
                userReports.reportContent = getString(getColumnIndex("REPORTCONTENT"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving REPORTCONTENT from cursor", e);
            }
            try {
                userReports.subType = getInt(getColumnIndex("SUBTYPE"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SUBTYPE from cursor", e);
            }
            return userReports;
        }
    }
    class ShipAddressCursor extends CursorWrapper {
        public ShipAddressCursor(Cursor cursor) {
            super(cursor);
        }

        public ShipAddress getShipAddress() {
            ShipAddress shipAddress = new ShipAddress();

            try {
                shipAddress.setLogicalRef(getInt(getColumnIndex("LOGICALREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LOGICALREF from cursor", e);
            }

            try {
                shipAddress.setClRef(getInt(getColumnIndex("CLREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CLREF from cursor", e);
            }

            try {
                shipAddress.setCode(getString(getColumnIndex("CODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CODE from cursor", e);
            }

            try {
                shipAddress.setCity(getString(getColumnIndex("CITY")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CITY from cursor", e);
            }
            try {
                shipAddress.setAddress(getString(getColumnIndex("ADDRESS")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ADDRESS from cursor", e);
            }

            try {
                shipAddress.setAddr1(getString(getColumnIndex("ADDR1")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ADDR1 from cursor", e);
            }
            try {
                shipAddress.setAddr2(getString(getColumnIndex("ADDR2")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ADDR2 from cursor", e);
            }
            try {
                shipAddress.setDistrict(getString(getColumnIndex("DISTRICT")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DISTRICT from cursor", e);
            }
            try {
                shipAddress.setLatitute(getString(getColumnIndex("LATITUTE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LATITUTE from cursor", e);
            }
            try {
                shipAddress.setLongtitude(getString(getColumnIndex("LONGITUDE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LONGITUDE from cursor", e);
            }
            try {
                shipAddress.setName(getString(getColumnIndex("NAME")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving NAME from cursor", e);
            }
            return shipAddress;
        }
    }
    class SalesShortCursor extends CursorWrapper {
        public SalesShortCursor(Cursor cursor) {
            super(cursor);
        }

        public SalesShort getSales() {
            SalesShort salesShort = new SalesShort();
            try {
                salesShort.setLogicalRef(getInt(getColumnIndex("REF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving REF from cursor", e);
            }
            salesShort.setTradingGroup(getString(getColumnIndex("TRADE")));
            salesShort.setSpeCode(getString(getColumnIndex("SPE")));
            salesShort.setFicheDefinition(getString(getColumnIndex("DESC")));

            try {
                salesShort.setFicheDate(getString(getColumnIndex("SDATE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SDATE from cursor", e);
            }
            try {
                salesShort.setFicheId(getString(getColumnIndex("FICHENO")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving FICHENO from cursor", e);
            }
            try {
                if (ErpCreator.getInstance().getmBaseErp().getErpType() == ErpType.TIGER) {
                    salesShort.setFicheNo(getString(getColumnIndex("TRANSFERFICHENO")));
                    salesShort.setClRef(getInt(getColumnIndex("CLREF")));
                }
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving additional fields from cursor", e);
            }
            try {
                salesShort.setOrderFicheStatus(getString(getColumnIndex("ORDERFICHESTATUS")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ORDERFICHESTATUS from cursor", e);
            }
            try {
                salesShort.setDocumentNo(getString(getColumnIndex("DOCTRACINGNR")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DOCTRACINGNR from cursor", e);
            }
            try {
                salesShort.setOrderStatus(getInt(getColumnIndex("STATUS")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving STATUS from cursor", e);
            }
            try {
                salesShort.setFicheStatus(getInt(getColumnIndex("FICHESTATUS")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving FICHESTATUS from cursor", e);
            }
            try {
                salesShort.setTotal(getDouble(getColumnIndex("TOTAL")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving TOTAL from cursor", e);
            }
            try {
                salesShort.setShipAddressCode(getString(getColumnIndex("SHIPCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SHIPCODE from cursor", e);
            }
            try {
                salesShort.setShipAddressName(getString(getColumnIndex("SHIPNAME")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SHIPNAME from cursor", e);
            }
            try {
                salesShort.setSalesType(getInt(getColumnIndex("SALESTYPE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SALESTYPE from cursor", e);
            }
            try {
                salesShort.setPayPlan(getString(getColumnIndex("PAYPLAN")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving PAYPLAN from cursor", e);
            }
            try {
                salesShort.setType(getInt(getColumnIndex("TYPE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving TYPE from cursor", e);
            }
            try {
                salesShort.setTransfer(StringUtils.convertIntToBoolean(getInt(getColumnIndex("ISTRANSFER"))));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ISTRANSFER from cursor", e);
            }
            try {
                salesShort.setEdit(StringUtils.convertIntToBoolean(getInt(getColumnIndex("ISEDIT"))));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ISEDIT from cursor", e);
            }
            try {
                salesShort.setCancel(StringUtils.convertIntToBoolean(getInt(getColumnIndex("CANCELLED"))));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CANCELLED from cursor", e);
            }
            salesShort.setDiffError(getInt(getColumnIndex("ISTRANSFER")) == 2);
            salesShort.setEDocStatus(getInt(getColumnIndex("EDOCSTATUS")));
            salesShort.setErpInvoiceType(getInt(getColumnIndex("ERPINVOICETYPE")));
            return salesShort;
        }
    }
    class FicheShortCursor extends CursorWrapper {
        public FicheShortCursor(Cursor cursor) {
            super(cursor);
        }

        public FicheShort getFiche() {
            FicheShort ficheShort = new FicheShort();

            try {
                ficheShort.setLogicalRef(getInt(getColumnIndex("LOGICALREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LOGICALREF from cursor", e);
            }

            try {
                ficheShort.setFicheDate(getString(getColumnIndex("FICHEDATE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving FICHEDATE from cursor", e);
            }

            try {
                ficheShort.setFicheRef(getString(getColumnIndex("FICHEREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving FICHEREF from cursor", e);
            }

            try {
                ficheShort.setTotal(getDouble(getColumnIndex("TOTAL")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving TOTAL from cursor", e);
            }
            try {
                ficheShort.setTransfer(StringUtils.convertIntToBoolean(getInt(getColumnIndex("ISTRANSFER"))));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ISTRANSFER from cursor", e);
            }
            try {
                ficheShort.setType(getInt(getColumnIndex("FTYPE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving FTYPE from cursor", e);
            }
            try {
                ficheShort.setExplanation(getString(getColumnIndex("DESC1")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DESC1 from cursor", e);
            }
            return ficheShort;
        }
    }
    class ProductCursor extends CursorWrapper implements ICursorGet {
        public ProductCursor(Cursor cursor) {
            super(cursor);
        }
        public Product get() throws IOException {
            Product product = new Product();
            product.setLogicalRef(getInt(getColumnIndex("_id")));
            try {
                product.setCode(getString(getColumnIndex("ITEMCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ITEMCODE from cursor", e);
            }

            try {
                product.setName(getString(getColumnIndex("DEFINITION")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DEFINITION from cursor", e);
            }

            try {
                product.setVariant(StringUtils.convertIntToBoolean(getInt(getColumnIndex("ISVARYANT"))));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ISVARYANT from cursor", e);
            }

            if (!product.getVariant()) {
                product.setPrice(getDouble(getColumnIndex("PRICE")));
                product.setCPrice(getString(getColumnIndex("CPRICE")));
                product.setPriceRef(getInt(getColumnIndex("PRICEREF")));
            }

            try {
                product.setIncVat(StringUtils.convertIntToBoolean(getInt(getColumnIndex("INCVAT"))));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving INCVAT from cursor", e);
            }

            try {
                product.setActualStock(getDouble(getColumnIndex("ACTUALSTOCK")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ACTUALSTOCK from cursor", e);
            }

            try {
                product.setRealStock(getDouble(getColumnIndex("REALSTOCK")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving REALSTOCK from cursor", e);
            }

            try {
                product.setTrackType(getInt(getColumnIndex("TRACKTYPE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving TRACKTYPE from cursor", e);
            }
            product.setTrackType(getInt(getColumnIndex("TRACKTYPE")));

            try {
                product.setCardType(getInt(getColumnIndex("CARDTYPE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CARDTYPE from cursor", e);
            }
            try {
                product.setVat(getDouble(getColumnIndex("VAT")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving VAT from cursor", e);
            }

            try {
                product.setRate(getDouble(getColumnIndex("RATE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving RATE from cursor", e);
            }

            try {
                product.setCurNr(getInt(getColumnIndex("CURNR")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CURNR from cursor", e);
            }

            try {
                product.setCurCode(getString(getColumnIndex("CURCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CURCODE from cursor", e);
            }

            try {
                product.setUnitConvert(getInt(getColumnIndex("UNITCONVERT")) == 1);
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving UNITCONVERT from cursor", e);
            }
            product.setNotUnitChange(getInt(getColumnIndex("ISSLCTUNIT")) != 0);

            try {
                product.setService(getInt(getColumnIndex("SERVICE")) == 1);
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SERVICE from cursor", e);
            }

            try {
                product.setBarcodeUnitRef(getInt(getColumnIndex("BUNITREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving BUNITREF from cursor", e);
            }

            try {
                product.setBarcode(getInt(getColumnIndex("ISBARCODE")) == 1);
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ISBARCODE from cursor", e);
            }

            try {
                product.setImage(CompressUtil.deCompressBitmap(getBlob(getColumnIndex("ITEMIMAGE"))));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ITEMIMAGE from cursor", e);
            }

            try {
                product.setImageActive(getInt(getColumnIndex("IMAGEINC")) == 1);
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving IMAGEINC from cursor", e);
            }
            product.setDefUnitRef(getInt(getColumnIndex("DUNITREF")));

            try {
                product.setAddTaxRef(getInt(getColumnIndex("ADDTAXREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ADDTAXREF from cursor", e);
            }
            if (product.isBarcode()) {
                product.setVariantCode(getString(getColumnIndex("VARIANTCODE")));
                try {
                    product.setVariantRef(getInt(getColumnIndex("VARIANTREF")));
                } catch (Exception e) {
                    throw new RuntimeException("Error retrieving VARIANTREF from cursor", e);
                }

                try {
                    product.setVariantName(getString(getColumnIndex("VARIANTNAME")));
                } catch (Exception e) {
                    throw new RuntimeException("Error retrieving VARIANTNAME from cursor", e);
                }
            }
            try {
                product.setLocTracking(getInt(getColumnIndex("LOCTRACKING")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LOCTRACKING from cursor", e);
            }

            try {
                product.setRetailVat(getDouble(getColumnIndex("RETAILVAT")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving RETAILVAT from cursor", e);
            }
            try {
                product.setRetailReturnVat(getDouble(getColumnIndex("RETAILRETURNVAT")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving RETAILRETURNVAT from cursor", e);
            }

            try {
                product.setPaymentRef(getInt(getColumnIndex("PAYMENTREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving PAYMENTREF from cursor", e);
            }

            try {
                product.setPaymentCode(getString(getColumnIndex("PAYMENTCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving PAYMENTCODE from cursor", e);
            }
            try {
                product.setPaymentDef(getString(getColumnIndex("PAYMENTDEF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving PAYMENTDEF from cursor", e);
            }
            if (ErpCreator.getInstance().getmBaseErp().getErpType() == ErpType.TIGER) {
                try {
                    product.setDiscount(new double[]{getDouble(getColumnIndex("SALESDISPRATETOT")), 0.0d, 0.0d});
                } catch (Exception e) {
                    throw new RuntimeException("Error retrieving SALESDISPRATETOT from cursor", e);
                }
                try {
                    product.setReturnVat(getDouble(getColumnIndex("RETURNVAT")));
                } catch (Exception e) {
                    throw new RuntimeException("Error retrieving RETURNVAT from cursor", e);
                }
                try {
                    product.setName2(getString(getColumnIndex("NAME2")));
                } catch (Exception e) {
                    throw new RuntimeException("Error retrieving NAME2 from cursor", e);
                }
            }
            return product;
        }
    }
    class VisitInfoShortCursor extends CursorWrapper implements ICursorGet {
        public VisitInfoShortCursor(Cursor cursor) {
            super(cursor);
        }
        public VisitInfoShort get() {
            VisitInfoShort visitInfoShort = new VisitInfoShort();
            try {
                visitInfoShort.setId(getInt(getColumnIndex("ID")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ID from cursor", e);
            }
            visitInfoShort.setShipAddress(getString(getColumnIndex("SHIPADDRESS")));
            try {
                visitInfoShort.setShipAddressExplanation(getString(getColumnIndex("EXPLANATION")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving EXPLANATION from cursor", e);
            }
            try {
                visitInfoShort.setClCode(getString(getColumnIndex("CLCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CLCODE from cursor", e);
            }

            try {
                visitInfoShort.setClName(getString(getColumnIndex("CLNAME")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CLNAME from cursor", e);
            }

            try {
                visitInfoShort.setVisitReason(getString(getColumnIndex("REASON")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving REASON from cursor", e);
            }

            try {
                visitInfoShort.setVisitDate(getString(getColumnIndex("VISITDATE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving VISITDATE from cursor", e);
            }

            try {
                visitInfoShort.setVisitNote(getString(getColumnIndex("NOTE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving NOTE from cursor", e);
            }

            try {
                visitInfoShort.setTransfer(StringUtils.convertIntToBoolean(getInt(getColumnIndex("ISTRANSFER"))));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ISTRANSFER from cursor", e);
            }

            try {
                visitInfoShort.setAuto(getInt(getColumnIndex("AUTO")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving AUTO from cursor", e);
            }
            return visitInfoShort;
        }
    }
    class CabinInfoCursor extends CursorWrapper implements ICursorGet {
        public CabinInfoCursor(Cursor cursor) {
            super(cursor);
        }

        public Cabin get() {
            Cabin cabin = new Cabin();
            cabin.f1223id = getInt(getColumnIndex("ID"));
            cabin.brand = getString(getColumnIndex("BRAND"));
            cabin.model = getString(getColumnIndex("MODEL"));

            try {
                cabin.code = Integer.parseInt(getString(getColumnIndex("CODE")));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Error parsing CODE as integer from cursor", e);
            }
            cabin.status = getInt(getColumnIndex("STATUS"));
            cabin.locInfo = getInt(getColumnIndex("LOCINFO"));
            cabin.salesmanRef = getInt(getColumnIndex("SALESMANREF"));

            try {
                cabin.modifiedDate = getString(getColumnIndex("MODIFIEDDATE"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving MODIFIEDDATE from cursor", e);
            }
            cabin.clientCode = getString(getColumnIndex("CLIENTCODE"));
            cabin.barcode = getString(getColumnIndex("BARCODE"));
            try {
                cabin.clientCode = getString(getColumnIndex("CLIENTCODE"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CLIENTCODE from cursor", e);
            }
            cabin.cmDate = getDouble(getColumnIndex("CMDATE"));

            cabin.isTransfer = getInt(getColumnIndex("ISTRANSFER"));
            cabin.type = getInt(getColumnIndex("TYPE"));
            try {
                cabin.createdDate = getString(getColumnIndex("CREATEDDATE"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CREATEDDATE from cursor", e);
            }
            try {
                cabin.firm = getString(getColumnIndex("FIRM"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving FIRM from cursor", e);
            }
            try {
                cabin.id = getInt(getColumnIndex("ID"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ID from cursor", e);
            }
            return cabin;
        }
    }
    class ProductDetailCursor extends CursorWrapper implements ICursorGet {
        public ProductDetailCursor(Cursor cursor) {
            super(cursor);
        }
        public ProductDetail get() {
            ProductDetail productDetail = new ProductDetail();

            try {
                productDetail.setCode(getString(getColumnIndex("ICODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ICODE from cursor", e);
            }

            try {
                productDetail.setName(getString(getColumnIndex("NAME")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving NAME from cursor", e);
            }

            try {
                productDetail.setSpecode1(getString(getColumnIndex("SPECODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SPECODE from cursor", e);
            }

            try {
                productDetail.setSpecode2(getString(getColumnIndex("SPECODE2")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SPECODE2 from cursor", e);
            }

            try {
                productDetail.setSpecode3(getString(getColumnIndex("SPECODE3")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SPECODE3 from cursor", e);
            }

            try {
                productDetail.setSpecode4(getString(getColumnIndex("SPECODE4")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SPECODE4 from cursor", e);
            }

            try {
                productDetail.setSpecode5(getString(getColumnIndex("SPECODE5")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SPECODE5 from cursor", e);
            }

            try {
                productDetail.setGrupcode(getString(getColumnIndex("STGRPCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving STGRPCODE from cursor", e);
            }
            try {
                productDetail.setMark(getString(getColumnIndex("MARK")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving MARK from cursor", e);
            }
            try {
                productDetail.setVat(getDouble(getColumnIndex("VAT")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving VAT from cursor", e);
            }
            try {
                productDetail.setPaymentPlan(getString(getColumnIndex("ODEMEPLAN")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ODEMEPLAN from cursor", e);
            }
            try {
                productDetail.setReturnVat(getDouble(getColumnIndex("RETURNVAT")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving RETURNVAT from cursor", e);
            }
            try {
                productDetail.setReturnVat(getDouble(getColumnIndex("RETURNVAT")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving RETURNVAT from cursor", e);
            }
            return productDetail;
        }
    }
    class ProductDetailStockCursor extends CursorWrapper implements ICursorGet {
        public ProductDetailStockCursor(Cursor cursor) {
            super(cursor);
        }
        public ProductDetail.Ambar get() {
            ProductDetail.Ambar ambar = new ProductDetail.Ambar();

            try {
                ambar.setAmbarName(getString(getColumnIndex("AMBAR")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving AMBAR from cursor", e);
            }

            try {
                ambar.setFiiliStok(getDouble(getColumnIndex("ONHAND")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ONHAND from cursor", e);
            }

            try {
                ambar.setGercekStok(getDouble(getColumnIndex("REALSTOCK")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving REALSTOCK from cursor", e);
            }
            try {
                ambar.setUnitCode(getString(getColumnIndex("UNITCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving UNITCODE from cursor", e);
            }
            try {
                ambar.setVarintCode(getString(getColumnIndex("VCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving VCODE from cursor", e);
            }
            try {
                ambar.setVariantActualStok(getDouble(getColumnIndex("VONHAND")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving VONHAND from cursor", e);
            }
            try {
                ambar.setVariantRealStok(getDouble(getColumnIndex("VREALSTOCK")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving VREALSTOCK from cursor", e);
            }
            try {
                ambar.setVariantName(getString(getColumnIndex("VNAME")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving VNAME from cursor", e);
            }
            return ambar;
        }
    }

    class ProductDetailPriceCursor extends CursorWrapper implements ICursorGet {
        public ProductDetailPriceCursor(Cursor cursor) {
            super(cursor);
        }
        public ProductDetail.ItemPrice get() {
            ProductDetail.ItemPrice itemPrice = new ProductDetail.ItemPrice();
            try {
                itemPrice.setLogRef(getInt(getColumnIndex("LOGICALREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LOGICALREF from cursor", e);
            }
            itemPrice.setPriceCode(getString(getColumnIndex("PRICECODE")));
            try {
                itemPrice.setPrice(getDouble(getColumnIndex("PRICE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving PRICE from cursor", e);
            }
            try {
                itemPrice.setShipTyp(getString(getColumnIndex("SHIPTYP")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SHIPTYP from cursor", e);
            }
            try {
                itemPrice.setGrpCode(getString(getColumnIndex("GRPCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving GRPCODE from cursor", e);
            }
            try {
                itemPrice.setCyphCode(getString(getColumnIndex("CYPHCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CYPHCODE from cursor", e);
            }
            try {
                itemPrice.setClientCode(getString(getColumnIndex("CLIENTCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CLIENTCODE from cursor", e);
            }
            try {
                itemPrice.setClspeCode(getString(getColumnIndex("CLSPECODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CLSPECODE from cursor", e);
            }
            itemPrice.setClspeCode2(getString(getColumnIndex("CLSPECODE2")));
            try {
                itemPrice.setClspeCode3(getString(getColumnIndex("CLSPECODE3")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CLSPECODE3 from cursor", e);
            }
            try {
                itemPrice.setClspeCode4(getString(getColumnIndex("CLSPECODE4")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CLSPECODE5 from cursor", e);
            }
            try {
                itemPrice.setCltradingGrp(getString(getColumnIndex("CLTRADINGGRP")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CLTRADINGGRP from cursor", e);
            }
            try {
                itemPrice.setClycphCode(getString(getColumnIndex("CLCYPHCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CLCYPHCODE from cursor", e);
            }
            try {
                itemPrice.setPayPlanRef(getInt(getColumnIndex("PAYPLANREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving PAYPLANREF from cursor", e);
            }
            try {
                itemPrice.setTradingGrp(getString(getColumnIndex("TRADINGGRP")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving TRADINGGRP from cursor", e);
            }
            try {
                itemPrice.setLeadtime(getInt(getColumnIndex("LEADTIME")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LEADTIME from cursor", e);
            }
            try {
                itemPrice.setOrdernr(getInt(getColumnIndex("ORDERNR")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ORDERNR from cursor", e);
            }
            try {
                itemPrice.setPriority(getInt(getColumnIndex("PRIORITY")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving PRIORITY from cursor", e);
            }
            try {
                itemPrice.setMarkRef(getInt(getColumnIndex("MARKREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving MARKREF from cursor", e);
            }
            try {
                itemPrice.setDefinition(getString(getColumnIndex("DEFINITION_")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DEFINITION_ from cursor", e);
            }
            try {
                itemPrice.setVariantCode(getString(getColumnIndex("VARIANTCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving VARIANTCODE from cursor", e);
            }
            try {
                itemPrice.setCPrice(itemPrice.getPrice() + "/" + getString(getColumnIndex("CURCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CURCODE from cursor", e);
            }
            try {
                itemPrice.setBegDate(getInt(getColumnIndex("BEGDATE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving BEGDATE from cursor", e);
            }
            try {
                itemPrice.setEndDate(getInt(getColumnIndex("ENDDATE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ENDDATE from cursor", e);
            }
            try {
                itemPrice.setPriceType(getString(getColumnIndex("PRICETYPE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving PRICETYPE from cursor", e);
            }
            return itemPrice;
        }
    }
    class ProductProcessCursor extends CursorWrapper implements ICursorGet {
        public ProductProcessCursor(Cursor cursor) {
            super(cursor);
        }
        @Override
        public ProductProcess get() {
            ProductProcess productProcess = new ProductProcess();
            try {
                productProcess.setLogicalRef(getInt(getColumnIndex("LOGICALREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LOGICALREF from cursor", e);
            }
            try {
                productProcess.setDefinition(getString(getColumnIndex("NAME")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving NAME from cursor", e);
            }
            return productProcess;
        }
    }

    class TodoCursor extends CursorWrapper implements ICursorGet {
        public TodoCursor(Cursor cursor) {
            super(cursor);
        }

        public TodoInfoDb get() {
            TodoInfoDb todoInfoDb = new TodoInfoDb();
            try {
                todoInfoDb.logicalRef = getInt(getColumnIndex("_id"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving _id from cursor", e);
            }
            try {
                todoInfoDb.setDesc(getString(getColumnIndex("DESC_")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DESC_ from cursor", e);
            }
            try {
                todoInfoDb.note = getString(getColumnIndex("INFO"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving INFO from cursor", e);
            }
            try {
                todoInfoDb.begDate = getString(getColumnIndex("BEGDATE"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving BEGDATE from cursor", e);
            }

            try {
                todoInfoDb.priority = getInt(getColumnIndex("PRI"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving PRI from cursor", e);
            }
            try {
                todoInfoDb.isToDo = getString(getColumnIndex("ISTODO"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ISTODO from cursor", e);
            }
            try {
                todoInfoDb.status = getInt(getColumnIndex("STAT"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving STAT from cursor", e);
            }
            return todoInfoDb;
        }
    }
    class DailyInfoCursor extends CursorWrapper implements ICursorGet {
        public DailyInfoCursor(Cursor cursor) {
            super(cursor);
        }
        public DailyInfo get() {
            DailyInfo dailyInfo = new DailyInfo();
            try {
                dailyInfo.setdDate(getString(getColumnIndex("DDATE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DDATE from cursor", e);
            }
            dailyInfo.customerName = getString(getColumnIndex("CUSTOMERNAME"));

            try {
                dailyInfo.customerCode = getString(getColumnIndex("CUSTOMERCODE"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CUSTOMERCODE from cursor", e);
            }
            try {
                dailyInfo.visitReason = getString(getColumnIndex("REASON"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving REASON from cursor", e);
            }
            try {
                dailyInfo.visitNote = getString(getColumnIndex("NOTE"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving NOTE from cursor", e);
            }
            try {
                dailyInfo.shipAddress = getString(getColumnIndex("SHIPADDRESS"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving SHIPADDRESS from cursor", e);
            }
            dailyInfo.setTransfer(getInt(getColumnIndex("ISTRANSFER")) > 0);

            try {
                dailyInfo.total = getDouble(getColumnIndex("TOTAL"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving TOTAL from cursor", e);
            }
            try {
                dailyInfo.paymentPlan = getString(getColumnIndex("ODEMEPLAN"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ODEMEPLAN from cursor", e);
            }
            try {
                dailyInfo.orderType = getInt(getColumnIndex("ORDERTYPE"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ORDERTYPE from cursor", e);
            }
            try {
                dailyInfo.setfType(getInt(getColumnIndex("FTYPE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving FTYPE from cursor", e);
            }
            dailyInfo.bankName = getString(getColumnIndex("BANKNAME"));

            try {
                dailyInfo.bankBranch = getString(getColumnIndex("BANKBRANCH"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving BANKBRANCH from cursor", e);
            }
            try {
                dailyInfo.accNo = getString(getColumnIndex("ACCNO"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ACCNO from cursor", e);
            }
            try {
                dailyInfo.debited = getString(getColumnIndex("DEBITED"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DEBITED from cursor", e);
            }
            try {
                dailyInfo.dueDate = getString(getColumnIndex("DUEDATE"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DUEDATE from cursor", e);
            }
            try {
                dailyInfo.itemType = getString(getColumnIndex("ITEMTYPE"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ITEMTYPE from cursor", e);
            }
            try {
                dailyInfo.logicalRef = getInt(getColumnIndex("LOGICALREF"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LOGICALREF from cursor", e);
            }
            try {
                dailyInfo.ficheRef = getInt(getColumnIndex("FICHEREF"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving FICHEREF from cursor", e);
            }
            return dailyInfo;
        }
    }

    class PenetrationInfoShortCursor extends CursorWrapper implements ICursorGet {
        public PenetrationInfoShortCursor(Cursor cursor) {
            super(cursor);
        }
        public PenetrationShort get() {
            PenetrationShort penetrationShort = new PenetrationShort();
            try {
                penetrationShort.setId(getInt(getColumnIndex("ID")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ID from cursor", e);
            }
            try {
                penetrationShort.setDate(getString(getColumnIndex("DATE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving DATE from cursor", e);
            }
            try {
                penetrationShort.setPenetrationName(getString(getColumnIndex("PDEFINITION")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving PDEFINITION from cursor", e);
            }
            try {
                penetrationShort.setTransfer(StringUtils.convertIntToBoolean(getInt(getColumnIndex("ISTRANSFER"))));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving ISTRANSFER from cursor", e);
            }
            try {
                penetrationShort.setGuid(getString(getColumnIndex("PNT_GUID")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving PNT_GUID from cursor", e);
            }
            try {
                penetrationShort.setPenetrationID(getInt(getColumnIndex("PENETRATION_ID")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving PENETRATION_ID from cursor", e);
            }
            return penetrationShort;
        }
    }
    class ItemUnitCursor extends CursorWrapper implements ICursorGet {
        public ItemUnitCursor(Cursor cursor) {
            super(cursor);
        }
        public ItemUnit get() {
            ItemUnit itemUnit = new ItemUnit();

            try {
                itemUnit.logicalRef = getInt(getColumnIndex("LOGICALREF"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LOGICALREF from cursor", e);
            }
            itemUnit.unitCode = getString(getColumnIndex("CODE"));
            itemUnit.code = getString(getColumnIndex("UNITCODE"));

            try {
                itemUnit.lineNr = getInt(getColumnIndex("LINENR"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving LINENR from cursor", e);
            }
            itemUnit.convFact1 = getDouble(getColumnIndex("CONVFACT1"));
            try {
                itemUnit.convFact2 = getDouble(getColumnIndex("CONVFACT2"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CONVFACT2 from cursor", e);
            }
            itemUnit.grossVolume = getDouble(getColumnIndex("GROSSVOLUME"));
            try {
                itemUnit.grossWeight = getDouble(getColumnIndex("GROSSWEIGHT"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving GROSSWEIGHT from cursor", e);
            }
            try {
                itemUnit.netVolume = getDouble(getColumnIndex("NETVOLUME"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving NETVOLUME from cursor", e);
            }
            try {
                itemUnit.netWeight = getDouble(getColumnIndex("NETWEIGHT"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving NETWEIGHT from cursor", e);
            }
            if (ErpCreator.getInstance().getmBaseErp().getErpType() == ErpType.NETSIS) {
                itemUnit.setDivUnit(true);
            } else {
                try {
                    itemUnit.setDivUnit(getInt(getColumnIndex("DIVUNIT")) == 1);
                } catch (Exception e) {
                    throw new RuntimeException("Error retrieving DIVUNIT from cursor", e);
                }
            }
            return itemUnit;
        }
    }
    class CompositeDetailCursor extends CursorWrapper implements ICursorGet {
        public CompositeDetailCursor(Cursor cursor) {
            super(cursor);
        }
        public CompositeDetail get() {
            CompositeDetail compositeDetail = new CompositeDetail();

            try {
                compositeDetail.setStockRef(getInt(getColumnIndex("STOCKREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving STOCKREF from cursor", e);
            }
            try {
                compositeDetail.setCode(getString(getColumnIndex("CODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CODE from cursor", e);
            }
            try {
                compositeDetail.setName(getString(getColumnIndex("NAME")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving NAME from cursor", e);
            }
            try {
                compositeDetail.setVat(getDouble(getColumnIndex("VAT")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving VAT from cursor", e);
            }
            try {
                compositeDetail.setAmount(getDouble(getColumnIndex("AMOUNT")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving AMOUNT from cursor", e);
            }
            compositeDetail.setPrice(getDouble(getColumnIndex("PRICE")));
            try {
                compositeDetail.setPerc(getDouble(getColumnIndex("PERC")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving PERC from cursor", e);
            }
            compositeDetail.setUnitLineRef(getInt(getColumnIndex("UNITLINEREF")));
            try {
                compositeDetail.setUnitCode(getString(getColumnIndex("UNITCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving UNITCODE from cursor", e);
            }
            compositeDetail.setConvFact1(getDouble(getColumnIndex("CONVFACT1")));
            try {
                compositeDetail.setConvFact2(getDouble(getColumnIndex("CONVFACT2")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CONVFACT2 from cursor", e);
            }
            try {
                compositeDetail.setVariantRef(getInt(getColumnIndex("VRNTREF")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving VRNTREF from cursor", e);
            }
            try {
                compositeDetail.setVariantCode(getString(getColumnIndex("VRNTCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving VRNTCODE from cursor", e);
            }
            return compositeDetail;
        }
    }
    class ProductGroupModelCursor extends CursorWrapper implements ICursorGet {
        public ProductGroupModelCursor(Cursor cursor) {
            super(cursor);
        }
        public ProductGroupModel get() {
            ProductGroupModel productGroupModel = new ProductGroupModel();
            try {
                productGroupModel.setGrpCount(getInt(getColumnIndex("GRPCOUNT")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving GRPCOUNT from cursor", e);
            }
            try {
                String string = getString(getColumnIndex("GRPCODE"));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving GRPCODE from cursor", e);
            }
            try {
                productGroupModel.setGrpName(R.string.equals("OTHERS") ? "" : getString(getColumnIndex("GRPNAME")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving GRPNAME from cursor", e);
            }
            if (R.string.equals("OTHERS")) {
                string = ContextUtils.getStringResource(R.string.str_others);
            }
            try {
                productGroupModel.setGrpCode(string);
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving GRPCODE from cursor", e);
            }
            return productGroupModel;
        }
    }
    class ProductDetailUnitCursor extends CursorWrapper implements ICursorGet {
        public ProductDetailUnitCursor(Cursor cursor) {
            super(cursor);
        }
        public ProductDetail.DetailItemUnit get() {

            ProductDetail.DetailItemUnit detailItemUnit = new ProductDetail.DetailItemUnit();
            detailItemUnit.setItemCode(getString(getColumnIndex("ITEMCODE")));
            try {
                detailItemUnit.setUnitCode(getString(getColumnIndex("UNITCODE")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving UNITCODE from cursor", e);
            }
            try {
                detailItemUnit.setConvfact1(getString(getColumnIndex("CONVFACT1")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CONVFACT1 from cursor", e);
            }
            try {
                detailItemUnit.setConvfact2(getString(getColumnIndex("CONVFACT2")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving CONVFACT2 from cursor", e);
            }
            try {
                detailItemUnit.setUnitDesc(getString(getColumnIndex("UNITDESC")));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving UNITDESC from cursor", e);
            }
            try {
                detailItemUnit.setBarcode(CollectionUtils.listOf(getString(getColumnIndex("BARCODE"))));
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving BARCODE from cursor", e);
            }
            if (ErpCreator.getInstance().getmBaseErp().getErpType() != ErpType.NETSIS) {
                detailItemUnit.setWidth(getString(getColumnIndex("WIDTH")));
                try {
                    detailItemUnit.setLength(getString(getColumnIndex("LENGTH")));
                } catch (Exception e) {
                    throw new RuntimeException("Error retrieving LENGTH from cursor", e);
                }
                try {
                    detailItemUnit.setHeight(getString(getColumnIndex("HEIGHT")));
                } catch (Exception e) {
                    throw new RuntimeException("Error retrieving HEIGHT from cursor", e);
                }
                detailItemUnit.setArea(getString(getColumnIndex("AREA")));
                try {
                    detailItemUnit.setGrossVolume(getString(getColumnIndex("GROSSVOLUME")));
                } catch (Exception e) {
                    throw new RuntimeException("Error retrieving GROSSVOLUME from cursor", e);
                }
                try {
                    detailItemUnit.setVolume(getString(getColumnIndex("VOLUME")));
                } catch (Exception e) {
                    throw new RuntimeException("Error retrieving VOLUME from cursor", e);
                }
                try {
                    detailItemUnit.setGrossWeight(getString(getColumnIndex("GROSSWEIGHT")));
                } catch (Exception e) {
                    throw new RuntimeException("Error retrieving GROSSWEIGHT from cursor", e);
                }
                try {
                    detailItemUnit.setWeight(getString(getColumnIndex("WEIGHT")));
                } catch (Exception e) {
                    throw new RuntimeException("Error retrieving WEIGHT from cursor", e);
                }
            }
            return detailItemUnit;
        }
    }
}
