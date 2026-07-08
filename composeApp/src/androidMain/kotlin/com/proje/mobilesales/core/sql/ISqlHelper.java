package com.proje.mobilesales.core.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.ListPreference;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.proje.mobilesales.core.enums.ColType;
import com.proje.mobilesales.core.enums.EDocStatus;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.SqlReturnType;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.core.utils.CustomerFilter;
import com.proje.mobilesales.features.customer.model.CustomerDiscount;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.customer.model.database.ClRisk;
import com.proje.mobilesales.features.gpsinfo.model.database.CustGpsInfo;
import com.proje.mobilesales.features.model.AlternativeProductListOptions;
import com.proje.mobilesales.features.model.BarcodeResult;
import com.proje.mobilesales.features.product.model.ItemUnit;
import com.proje.mobilesales.features.product.model.ProductListParameter;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import com.proje.mobilesales.features.product.model.database.RecommendedProducts;
import com.proje.mobilesales.features.userreport.model.database.UserReports;
import java.util.ArrayList;
import java.util.List;

public interface ISqlHelper<T> {
    void addParamDbTwo(String str, String str2, String str3);

    void addSalesCond(int i2, int i3);

    void alterTables(SQLiteDatabase sQLiteDatabase, int i2, int i3);

    void alterTrigger(SQLiteDatabase sQLiteDatabase);

    boolean cekSenetDetayiVarmi();

    void cekSenetTahsilatSil();

    double cekSenetToplaminiGetir();

    void cekSenetToplamlariGuncelle();

    void createIndex(SQLiteDatabase sQLiteDatabase);

    void createTables(SQLiteDatabase sQLiteDatabase);

    void createTrigger(SQLiteDatabase sQLiteDatabase);

    Object dbVal(Cursor cursor, String str, ColType colType);

    boolean fisSatisKosuluUygulandiMi(int i2, int i3);

    String getAlternativeProductList(Context context, AlternativeProductListOptions alternativeProductListOptions);

    Cursor getAvailableAddTax(int i2, String str, String str2);

    String getAvailableCabinListOnTheStorageSql(Context context, int i2, int i3, String str);

    int getAvailableCabinOnTheStorageBarcodeScannerSql();

    int getAvailableOfflineCustomerRef();

    String getBankAccCode(int i2);

    int getBarcodeScannerSql();

    int getCabinBarcodeScannerSql();

    String getCabinListSql(Context context, String str, int i2, int i3);

    String getCaseCashCode(int i2);

    Cursor getCaseCreditDetailForPrint(int i2);

    Cursor getCaseCreditHedaerForPrint(int i2);

    Cursor getCaseForPrint(int i2);

    String getCaseMuhCode(int i2);

    Cursor getCheequeDeedDetailForPrint(int i2);

    Cursor getCheequeDeedHedaerForPrint(int i2);

    double getClCardBalance(int i2);

    String getClCardCurrCode(int i2);

    int getClCardCurrency(int i2);

    int getClCardDueDate(int i2);

    int getClCardEArchiveInsteadOfDesp(int i2);

    int getClCardIsPerCurr(int i2);

    int getClCardSendMod(int i2);

    String getClCardTradingGrp(int i2);

    String getClCode(int i2);

    int getClEDespatchUser(int i2);

    int getClEInvoiceTyp(int i2);

    int getClEInvoiceUser(int i2);

    int getClEInvoiceUser(String str);

    String getClEmail(int i2);

    int getClLogicalRef(String str);

    String getClName(int i2);

    String getClName(String str);

    int getClProfileId(int i2);

    int getColumnIndex(Cursor cursor, String str);

    boolean getCurRateInt();

    String getCurrCode(int i2);

    double getCurrRate(int i2);

    Double getCurrRate(String str);

    double getCurrRateWithControl(int i2, int i3);

    double getCurrRateWithDate(int i2, String str);

    double getCurrRateWithoutDefaultValue(int i2);

    Cursor getCustomerBalanceInfoAfterDate(int i2, String str);

    int getCustomerCabinCount(String str, String str2);

    String getCustomerConditionCode(int i2);

    CustomerDiscount getCustomerDiscountRate(int i2);

    String getCustomerLastPaymentInfo(int i2);

    String getCustomerListSql(Context context, CustomerFilter customerFilter);

    ArrayList<CustGpsInfo> getCustomerLocations();

    String getCustomerShipAddressSql(Context context, int i2, String str);

    Cursor getCustomerUnsentAccountBalance(int i2);

    String getDailyInfoListSql(Context context, int i2);

    int getDatabaseVersion();

    double getDefinedPurchasePrice(int i2);

    Cursor getDeliveryNoteDetailForPrint(int i2);

    String getEmailParamValue(String str);

    int getFicheCustomerRef(String str, int i2);

    double[] getFicheGpsInfo(Class<?> cls, int i2);

    String getFicheListSql(Context context, int i2, CustomerOperation customerOperation, String str);

    List<String> getFicheNoAndRef(Class<?> cls, int i2);

    Cursor getInvoiceDetailForPrint(int i2, boolean z, int i3);

    Cursor getInvoiceHedaerForPrint(int i2);

    boolean getInvoiceReceiptRelationIfTransfered(int i2, boolean z);

    String getItemCode(int i2);

    int getItemLogicalRef(String str);

    String getItemName(String str);

    String getItemSizeSql(SalesType salesType);

    int getItemTrackType(int i2);

    List<ItemUnits> getItemUnits(int i2);

    List<String> getList(Class<?> cls, String str, String str2, boolean z);

    double getLocalCreditTotal(int i2);

    double getLocalDebitTotal(int i2);

    String getLogoParamValue(String str);

    ISqlCreator getLogoSqlCreator();

    String getMainCaseCashCode();

    double getMaxCMDate(Class<?> cls);

    double getMaxCMDate(Class<?> cls, String str);

    String getMuhAccCode(int i2);

    Cursor getOrderDetailForPrint(int i2);

    Cursor getOrderHedaerForPrint(int i2);

    String getParam(String str);

    Object getParamValue(ParameterTypes parameterTypes, SqlReturnType sqlReturnType);

    String getParamValue(ParameterTypes parameterTypes);

    String getPaymentCode(int i2);

    String getPenetrationListSql(Context context, String str, int i2, int i3, int i4);

    String getProductDetailInfoSql(int i2, boolean z);

    String getProductDetailPriceSql(int i2);

    String getProductDetailStockSql(int i2, boolean z);

    String getProductDetailUnitSql(int i2, boolean z);

    String getProductListSql(Context context, ProductListParameter productListParameter, int i2, String str, int i3, ArrayList<BarcodeResult> arrayList, String str2, int i4, String str3, boolean z, ArrayList<Integer> arrayList2, int i5, String str4, String str5, int i6, ArrayList<RecommendedProducts> arrayList3);

    String getProductSortListSql(Context context, ProductListParameter productListParameter, int i2);

    ArrayList<ItemUnit> getProductUnits(int i2, boolean z);

    String getProjectCode(int i2);

    SQLiteDatabase getReadableDatabase();

    String getReferenceCode(int i2);

    String getReportXML(int i2);

    String getReportXML(String str);

    String getRouteListSql(String str);

    String getSalesDetailProductPriceSql(Context context, int i2, boolean z, String str, int i3);

    String getSalesDetailProductPriceSql(Context context, boolean z, String str);

    int getSalesDetailProductUnitSql(boolean z);

    String getSalesListSql(Context context, int i2, CustomerOperation customerOperation, String str);

    int getSalesProductSetPriceSql();

    String getServiceListSql(Context context, ProductListParameter productListParameter, int i2, String str, int i3, ArrayList<BarcodeResult> arrayList, String str2, int i4, String str3, int i5, String str4);

    int getServiceUnitLogicalRef(String str, int i2);

    String getShipAddressCode(int i2, int i3);

    String getSingleField(Class<?> cls, String str, String str2);

    String getSingleField(String str, String str2, String str3);

    String getSingleField(String str, String str2, String str3, boolean z);

    boolean getSingleFieldBoolean(Class<?> cls, String str, String str2);

    String getSpeCodeLogicalRef(int i2);

    String getString(Cursor cursor, String str);

    List<T> getTable(Class<T> cls);

    List<T> getTable(Class<T> cls, String str, String[] strArr);

    List<T> getTable(Class<T> cls, String str, String[] strArr, @Nullable String str2, @Nullable String str3, @Nullable String str4);

    List<Class<?>> getTableList();

    List<T> getTableWhere(Class<T> cls, String str, String[] strArr);

    List<T> getTableWhere(Class<T> cls, String str, String[] strArr, @Nullable String str2);

    String getTodoListSql(Context context);

    String getUnitCode(int i2, boolean z);

    double[] getUnitConvfact(int i2, int i3);

    double[] getUnitConvfact(String str, int i2);

    double[] getUnitConvfact(String str, String str2);

    int getUnitLineNr(String str, String str2, boolean z);

    int getUnitLogicalRef(int i2, int i3);

    int getUnitLogicalRef(String str, int i2);

    String getUserCode(int i2);

    String getUserName(int i2);

    ArrayList<UserReports> getUserReports(String str);

    String[] getVariantCode(int i2);

    String getVisitListSql(Context context, String str, int i2, String str2, int i3, int i4);

    String getVisitSql(Context context, int i2);

    int getWareHouseCode(String str);

    int getWareHouseCostGrp(int i2);

    ArrayList<Integer> getWareHouseCostGrpFactoryDivision(int i2);

    Cursor getWhTransferDetailForPrint(int i2, boolean z, int i3);

    Cursor getWhTransferHeaderForPrint(int i2);

    SQLiteDatabase getWritableDatabase();

    Cursor isCustomerEnterPenetrationToday(int i2, int i3);

    boolean isEInvoiceOrEArchiveCustomer(int i2);

    void loadDataPref(String str, ListPreference listPreference, String str2, String str3, ColType colType, Boolean bool);

    void loadDataPref(String str, ListPreference listPreference, String str2, String str3, ColType colType, Boolean bool, Boolean bool2);

    void loadPrefData(String str, ListPreference listPreference, String str2, String str3, ColType colType, Boolean bool);

    void loadPrefData(String str, ListPreference listPreference, String str2, String str3, ColType colType, Boolean bool, Boolean bool2);

    void loadPrefData(String str, ListPreference listPreference, String str2, String str3, ColType colType, Boolean bool, Boolean bool2, Boolean bool3);

    boolean nakitKrediKartiDetayiVarmi();

    double nakitKrediKartiToplaminiGetir();

    void nakitKrediKartiToplamlariGuncelle();

    void nakitKrediTahsilatSil();

    void saveCustomerRiskTotals(ClRisk clRisk);

    Cursor selectCursorQuery(@StringRes int i2, String... strArr);

    void siparisDetayTeslimTarihleriniGuncelle(String str);

    boolean sozlesmeTaksitSayisiniKontrolEt(String str, String str2);

    String upVal(String str);

    String upValRest(String str);

    void updateCustomerRiskTotals(ClRisk clRisk);

    void updateEDocStatus(EDocStatus eDocStatus, int i2);

    void updateTransferedCustomer(String str);
}
