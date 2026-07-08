package com.proje.mobilesales.core.sql;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.ListPreference;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.exifinterface.media.ExifInterface;
import androidx.webkit.ProxyConfig;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.Cabin;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.CabinTrans;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCredit;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCreditDetail;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.Chequedeed;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.ChequedeedDetail;
import com.proje.mobilesales.features.customer.model.CustomerDiscount;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.model.database.ClCardIncharge;
import com.proje.mobilesales.features.customer.model.database.ClRisk;
import com.proje.mobilesales.features.customer.model.database.CustomerDetailedRestriction;
import com.proje.mobilesales.features.dbmodel.*;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;
import com.proje.mobilesales.features.gpsinfo.model.database.CustGpsInfo;
import com.proje.mobilesales.features.gpsinfo.model.database.GpsInfo;
import com.proje.mobilesales.features.licence.model.database.LicenseInfo;
import com.proje.mobilesales.features.penetration.model.database.Penetration;
import com.proje.mobilesales.features.penetration.model.database.PenetrationDetail;
import com.proje.mobilesales.features.penetration.model.database.UserMainPenetration;
import com.proje.mobilesales.features.penetration.model.database.UserPenetration;
import com.proje.mobilesales.features.product.model.database.*;
import com.proje.mobilesales.features.reports.model.database.ReportMenu;
import com.proje.mobilesales.features.reports.model.database.ReportParam;
import com.proje.mobilesales.features.sales.model.database.SalesCondctrl;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import com.proje.mobilesales.features.todo.model.database.TodoInfoDb;
import com.proje.mobilesales.features.tools.model.database.StartInfo;
import com.proje.mobilesales.features.userreport.model.database.UserReports;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import com.proje.mobilesales.features.visit.model.database.VisitReason;

import java.io.Closeable;
import java.lang.reflect.Field;
import java.util.*;

import static com.proje.mobilesales.core.utils.AppUtils.alert;
public abstract class SqlHelper<T> extends SQLiteOpenHelper implements ISqlHelper<T>, Closeable {
  protected static final String COLUMN_PARAMVALUE = "PARAMVALUE";
  private static final String TAG = "SqlHelper";
  protected ISqlCreator mISqlCreator;
  private Throwable th;
  private Exception e;
  public void alterTrigger(SQLiteDatabase sQLiteDatabase) {
    }
    public int getAvailableCabinOnTheStorageBarcodeScannerSql() {
        return R.string.qry_available_cabin_on_the_storage_barcode;
    }
    public int getCabinBarcodeScannerSql() {
        return R.string.qry_cabin_barcode_get_customer;
    }
    public abstract int getDatabaseVersion();
    public SqlHelper(Context context, String str, int i2) {
        super(context, str, null, i2);
    }
    public SqlHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i2) {
        super(context, str, cursorFactory, i2);
        setWriteAheadLoggingEnabled(true);
        Log.d(TAG, "setWriteAheadLoggingEnabled");
    }
    public SqlHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i2, DatabaseErrorHandler databaseErrorHandler) {
        super(context, str, cursorFactory, i2, databaseErrorHandler);
    }
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        if (i2 < i3) {
            alterTables(sQLiteDatabase, i2, i3);
            if (i2 < 171) {
                sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS Delete_InvoiceDetail_trigger");
            }
            if (i2 < 172) {
                sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS Delete_Invoice_trigger");
            }
            if (i2 < 173) {
                sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS Delete_Invoice_trigger");
                sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS Delete_WhTrans_trigger");
            }
            if (i2 < 167) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS CUSTGPSINFO");
                String crateTableFromClass = this.mISqlCreator.crateTableFromClass(CustGpsInfo.class, getDatabaseVersion());
                if (!crateTableFromClass.isEmpty()) {
                    sQLiteDatabase.execSQL(crateTableFromClass);
                }
            }
            if (i2 < 183 && getDatabaseName().contains("NETSIS")) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS PAYMENT");
                String crateTableFromClass2 = this.mISqlCreator.crateTableFromClass(Payment.class, getDatabaseVersion());
                if (!crateTableFromClass2.isEmpty()) {
                    sQLiteDatabase.execSQL(crateTableFromClass2);
                }
            }
            if (i2 < 185 && getDatabaseName().contains("NETSIS")) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS VARIANTS");
                String crateTableFromClass3 = this.mISqlCreator.crateTableFromClass(Variant.class, getDatabaseVersion());
                if (!crateTableFromClass3.isEmpty()) {
                    sQLiteDatabase.execSQL(crateTableFromClass3);
                }
            }
            if (i2 < 191) {
                sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS Delete_InvoiceDetail_trigger");
                sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS Delete_WhTransDetail_trigger");
            }
            createTrigger(sQLiteDatabase);
            createIndex(sQLiteDatabase);
        }
    }
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        createTables(sQLiteDatabase);
        createTrigger(sQLiteDatabase);
        createIndex(sQLiteDatabase);
    }
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        super.onDowngrade(sQLiteDatabase, i2, i3);
    }
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
    }
    public synchronized void close() {
        super.close();
    }
    public ISqlCreator getLogoSqlCreator() {
        return this.mISqlCreator;
    }
    public List<Class<?>> getTableList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BankAccount.class);
        arrayList.add(Bank.class);
        arrayList.add(Branch.class);
        arrayList.add(CampaingView.class);
        arrayList.add(CaseCash.class);
        arrayList.add(CashCredit.class);
        arrayList.add(CashCreditDetail.class);
        arrayList.add(Chequedeed.class);
        arrayList.add(ChequedeedDetail.class);
        arrayList.add(ClCard.class);
        arrayList.add(CustomerImage.class);
        arrayList.add(EmailTemplate.class);
        arrayList.add(Curr.class);
        arrayList.add(CurrType.class);
        arrayList.add(CustGpsInfo.class);
        arrayList.add(DefOrder.class);
        arrayList.add(DefOrderDetail.class);
        arrayList.add(DesignFile.class);
        arrayList.add(DesignJson.class);
        arrayList.add(Discount.class);
        arrayList.add(DispatchView.class);
        arrayList.add(Division.class);
        arrayList.add(EmailParam.class);
        arrayList.add(Factory.class);
        arrayList.add(GpsInfo.class);
        arrayList.add(Invoice.class);
        arrayList.add(InvoiceDetail.class);
        arrayList.add(InvoiceView.class);
        arrayList.add(ItemPrice.class);
        arrayList.add(Item.class);
        arrayList.add(ItemImage.class);
        arrayList.add(ItemLanguage.class);
        arrayList.add(ItemStock.class);
        arrayList.add(ItemUnits.class);
        arrayList.add(LogoParam.class);
        arrayList.add(MarketingMessage.class);
        arrayList.add(OrderDetail.class);
        arrayList.add(Order.class);
        arrayList.add(OrderView.class);
        arrayList.add(Param.class);
        arrayList.add(Payment.class);
        arrayList.add(PaymentLine.class);
        arrayList.add(Penetration.class);
        arrayList.add(PenetrationDetail.class);
        arrayList.add(Price.class);
        arrayList.add(Project.class);
        arrayList.add(ReportMenu.class);
        arrayList.add(ReportParam.class);
        arrayList.add(Route.class);
        arrayList.add(Routetr.class);
        arrayList.add(MarketingMessage.class);
        arrayList.add(SalesCondctrl.class);
        arrayList.add(Serilot.class);
        arrayList.add(Service.class);
        arrayList.add(ServiceUnit.class);
        arrayList.add(ShipAddress.class);
        arrayList.add(ShipAgent.class);
        arrayList.add(ShipType.class);
        arrayList.add(SlsClRel.class);
        arrayList.add(Specodes.class);
        arrayList.add(SpecodesPrices.class);
        arrayList.add(Stcompln.class);
        arrayList.add(SuppAsgn.class);
        arrayList.add(StartInfo.class);
        arrayList.add(TodoInfoDb.class);
        arrayList.add(Tradinggrp.class);
        arrayList.add(TransferInfo.class);
        arrayList.add(UnitBarcode.class);
        arrayList.add(Units.class);
        arrayList.add(UserCase.class);
        arrayList.add(UserInfo.class);
        arrayList.add(UserParam.class);
        arrayList.add(UserPenetration.class);
        arrayList.add(UserMainPenetration.class);
        arrayList.add(User.class);
        arrayList.add(Variant.class);
        arrayList.add(VariantUnit.class);
        arrayList.add(VisitReason.class);
        arrayList.add(VisitInfo.class);
        arrayList.add(WareHouse.class);
        arrayList.add(UserReports.class);
        arrayList.add(CreditAggr.class);
        arrayList.add(MuhRefCodes.class);
        arrayList.add(AddTax.class);
        arrayList.add(TransferDate.class);
        arrayList.add(WorRoute.class);
        arrayList.add(WorRouteDay.class);
        arrayList.add(WorRoutePlan.class);
        arrayList.add(WorUserCustomers.class);
        arrayList.add(WorRouteProcess.class);
        arrayList.add(ClCardIncharge.class);
        arrayList.add(LocTracking.class);
        arrayList.add(Country.class);
        arrayList.add(City.class);
        arrayList.add(Town.class);
        arrayList.add(Cabin.class);
        arrayList.add(CabinTrans.class);
        arrayList.add(WorTables.class);
        arrayList.add(VariantStock.class);
        arrayList.add(ClRisk.class);
        arrayList.add(ItemGroupCode.class);
        arrayList.add(WhTransfer.class);
        arrayList.add(WhTransferDetail.class);
        arrayList.add(EDocSerial.class);
        arrayList.add(EDispatchAdditionalInfo.class);
        arrayList.add(Firm.class);
        arrayList.add(FicheDetailBarcode.class);
        arrayList.add(LicenseInfo.class);
        arrayList.add(ItemTags.class);
        arrayList.add(GroupCode.class);
        arrayList.add(CustomerDetailedRestriction.class);
        arrayList.add(PurchasePrice.class);
        return arrayList;
    }
    public void createTables(SQLiteDatabase sQLiteDatabase) {
        try {
            List<Class<?>> tableList = getTableList();
            if (sQLiteDatabase == null) {
                return;
            }
            Iterator<Class<?>> it = tableList.iterator();
            while (it.hasNext()) {
                String crateTableFromClass = this.mISqlCreator.crateTableFromClass(it.next(), getDatabaseVersion());
                if (!crateTableFromClass.isEmpty()) {
                    sQLiteDatabase.execSQL(crateTableFromClass);
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "createTables: ", e2);
        }
    }
    public void alterTables(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        try {
            List<Class<?>> tableList = getTableList();
            if (sQLiteDatabase == null) {
                return;
            }
            for (Class<?> cls : tableList) {
                if (cls.getAnnotation(Tables.class).alterVersion() > i2) {
                    sQLiteDatabase.execSQL(this.mISqlCreator.deleteTableFromClass(cls));
                    sQLiteDatabase.execSQL(this.mISqlCreator.crateTableFromClass(cls, i3));
                } else {
                    List<String> alterTableFromClass = this.mISqlCreator.alterTableFromClass(cls, i2, i3);
                    if (!alterTableFromClass.isEmpty()) {
                        Iterator<String> it = alterTableFromClass.iterator();
                        while (it.hasNext()) {
                            sQLiteDatabase.execSQL(it.next());
                        }
                    }
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "alterTables: ", e2);
        }
    }
    public void createTrigger(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(this.mISqlCreator.getTriggerSqlDelete(R.string.trigger_delete_order, Order.class, OrderDetail.class, R.string.column_sales_fiche_id, R.string.column_logical_ref));
          sQLiteDatabase.execSQL("CREATE TRIGGER IF NOT EXISTS Delete_Invoice_trigger\n AFTER DELETE ON INVOICE\n FOR EACH ROW \n BEGIN \n DELETE FROM EDESPATCHADDITIONALINFO WHERE SALESFICHEID =  OLD.LOGICALREF  AND SALESTYPE = OLD.SALESTYPE \n BEGIN \n DELETE FROM INVOICEDETAIL WHERE SALESFICHEID =  OLD.LOGICALREF \n END");
            sQLiteDatabase.execSQL(ContextUtils.getStringResource(R.string.trigger_delete_invoice_detail_sql, "0"));
            sQLiteDatabase.execSQL(this.mISqlCreator.getTriggerSqlDelete(R.string.trigger_delete_penetration_detail, UserMainPenetration.class, UserPenetration.class, R.string.column_penet_fiche_id, R.string.column_id_u));
            sQLiteDatabase.execSQL(this.mISqlCreator.getTriggerSqlDelete(R.string.trigger_delete_invoice_route, Invoice.class, WorRouteProcess.class, R.string.column_fiche_ref, R.string.column_logical_ref));
            sQLiteDatabase.execSQL(this.mISqlCreator.getTriggerSqlDelete(R.string.trigger_delete_order_route, Order.class, WorRouteProcess.class, R.string.column_fiche_ref, R.string.column_logical_ref));
            sQLiteDatabase.execSQL(this.mISqlCreator.getTriggerSqlDelete(R.string.trigger_delete_casecash_route, CaseCash.class, WorRouteProcess.class, R.string.column_fiche_ref, R.string.column_logical_ref));
            sQLiteDatabase.execSQL(this.mISqlCreator.getTriggerSqlDelete(R.string.trigger_delete_cashcredit_route, CashCredit.class, WorRouteProcess.class, R.string.column_fiche_ref, R.string.column_logical_ref));
            sQLiteDatabase.execSQL(this.mISqlCreator.getTriggerSqlDelete(R.string.trigger_delete_chequedeed_route, Chequedeed.class, WorRouteProcess.class, R.string.column_fiche_ref, R.string.column_logical_ref));
            sQLiteDatabase.execSQL(this.mISqlCreator.getTriggerSqlDelete(R.string.trigger_delete_userpenetration_route, UserMainPenetration.class, WorRouteProcess.class, R.string.column_fiche_ref, R.string.column_id_u));
            sQLiteDatabase.execSQL(this.mISqlCreator.getTriggerSqlDelete(R.string.trigger_delete_visitinfo_route, VisitInfo.class, WorRouteProcess.class, R.string.column_fiche_ref, R.string.column_id_u));
            sQLiteDatabase.execSQL(this.mISqlCreator.getTriggerSqlDelete(R.string.trigger_delete_invoice_cabintrans, Invoice.class, CabinTrans.class, R.string.column_local_fiche_ref, R.string.column_logical_ref));
            sQLiteDatabase.execSQL(this.mISqlCreator.getTriggerSqlDelete(R.string.trigger_delete_cabin_cabintrans, Cabin.class, CabinTrans.class, R.string.column_cabin_id, R.string.column_id_u, R.string.column_transfer, BuildConfig.NETSIS_DEMO_PASSWORD));
            sQLiteDatabase.execSQL("CREATE TRIGGER IF NOT EXISTS Delete_WhTrans_trigger\n AFTER DELETE ON WHTRANSFER\n FOR EACH ROW \n BEGIN \n DELETE FROM EDESPATCHADDITIONALINFO WHERE SALESFICHEID =  OLD.LOGICALREF AND SALESTYPE = OLD.SALESTYPE \n BEGIN  \n DELETE FROM WHTRANSFERDETAIL WHERE SALESFICHEID =  OLD.LOGICALREF  END");
            sQLiteDatabase.execSQL(ContextUtils.getStringResource(R.string.trigger_delete_whTransfer_detail_sql, BuildConfig.NETSIS_DEMO_PASSWORD));
            sQLiteDatabase.execSQL(ContextUtils.getStringResource(R.string.trigger_delete_order_detail_sql, ExifInterface.GPS_MEASUREMENT_2D));
        } catch (Exception e2) {
            Log.e(TAG, "createTrigger: ", e2);
        }
    }
    public void createIndex(SQLiteDatabase sQLiteDatabase) {
        try {
            execIndexSql(ItemStock.class, sQLiteDatabase);
            execIndexSql(VariantStock.class, sQLiteDatabase);
            execIndexSql(ClRisk.class, sQLiteDatabase);
            if (getDatabaseName().contains("NETSIS")) {
                execIndexSql(Variant.class, sQLiteDatabase);
            }
            sQLiteDatabase.execSQL("DROP INDEX IF EXISTS idx_CustomerImage");
          try {
            checkCustomerDocIndex(sQLiteDatabase, th);
          } catch (Throwable e) {
            throw new RuntimeException(e);
          }
          createRawIndex(sQLiteDatabase, "CREATE UNIQUE INDEX IF NOT EXISTS idx_customerDocs ON CUSTOMERIMAGE(CUSTOMERREF,DOCNR,DOCTYP)");
            createRawIndex(sQLiteDatabase, "CREATE INDEX IF NOT EXISTS idx_items ON ITEMS(LOGICALREF,CODE)");
            createRawIndex(sQLiteDatabase, "CREATE INDEX IF NOT EXISTS idx_unitBarcode ON UNITBARCODE(ITEMREF)");
            createRawIndex(sQLiteDatabase, "CREATE INDEX IF NOT EXISTS idx_unitPrice ON ITEMPRICE(ITEMREF,PTYPE,PRIORITY)");
            createRawIndex(sQLiteDatabase, "CREATE INDEX IF NOT EXISTS idx_Curr ON CURR(CURRTYPE)");
            sQLiteDatabase.execSQL("DROP INDEX IF EXISTS idx_unit");
            createRawIndex(sQLiteDatabase, "CREATE INDEX IF NOT EXISTS idx_unit ON ITEMUNITS(ITEMREF)");
            sQLiteDatabase.execSQL("DROP INDEX IF EXISTS idx_unitCode");
            createRawIndex(sQLiteDatabase, "CREATE INDEX IF NOT EXISTS idx_unitCode ON ITEMUNITS(LOGICALREF,CODE)");
        } catch (Exception e2) {
            Log.e(TAG, "createIndex: ", e2);
        }
    }
    private void createRawIndex(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL(str);
        } catch (Exception e2) {
            Log.e(TAG, "createRawIndex: ", e2);
        }
    }
    private void checkCustomerDocIndex(SQLiteDatabase sQLiteDatabase, Throwable th) throws Throwable {
        Cursor cursor;
        Cursor cursor2 = null;
        Cursor cursor3;
        Cursor cursor4 = null;
        try {
            Exception e;
            try {
                cursor = sQLiteDatabase.rawQuery("SELECT * FROM SQLITE_MASTER WHERE TYPE='index' AND NAME='idx_customerDocs'", null);
                if (cursor != null) {
                    try {
                        if (cursor.getCount() > 0) {
                            if (cursor.isClosed()) {
                                return;
                            }
                            cursor.close();
                            return;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        Log.e(TAG, "checkCustomerImageIndex: ", e);
                        if (cursor != null) {
                        }
                        try {
                            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT CUSTOMERREF,MAX(ROWID) AS LREF FROM CUSTOMERIMAGE WHERE DOCNR=11 AND DOCTYP=0 GROUP BY CUSTOMERREF HAVING COUNT(CUSTOMERREF)>1 ", null);
                            if (rawQuery != null) {
                                try {
                                    if (rawQuery.getCount() > 0 && rawQuery.moveToFirst()) {
                                        do {
                                            sQLiteDatabase.delete("CUSTOMERIMAGE", "CUSTOMERREF=" + Integer.valueOf(rawQuery.getInt(getColumnIndex(rawQuery, "CUSTOMERREF"))) + " AND ROWID<>" + Integer.valueOf(rawQuery.getInt(getColumnIndex(rawQuery, "LREF"))) + " AND DOCNR=11 AND DOCTYP=0", null);
                                        } while (rawQuery.moveToNext());
                                    }
                                } catch (Exception e3) {
                                    e = e3;
                                    cursor3 = null;
                                    cursor4 = rawQuery;
                                  Log.e(TAG, "checkCustomerImageIndex: ", e);
                                  if (cursor4 != null && !cursor4.isClosed()) {
                                      cursor4.close();
                                  }
                                  if (cursor3 != null && cursor3.isClosed()) {
                                      return;
                                  }
                                  cursor3.close();
                                  return;
                                } catch (Throwable th2) {
                                    this.th = th2;
                                    cursor3 = null;
                                    cursor4 = rawQuery;
                                    if (cursor4 != null) {
                                        cursor4.close();
                                    }
                                    if (cursor3 != null) {
                                        cursor3.close();
                                    }
                                    throw this.th;
                                }
                            }
                            cursor3 = sQLiteDatabase.rawQuery("SELECT CUSTOMERREF,MAX(ROWID) AS LREF FROM CUSTOMERIMAGE WHERE DOCNR=1 AND DOCTYP=3 GROUP BY CUSTOMERREF HAVING COUNT(CUSTOMERREF)>1 ", null);
                            if (cursor3 != null) {
                                try {
                                    if (cursor3.getCount() > 0 && cursor3.moveToFirst()) {
                                        do {
                                            sQLiteDatabase.delete("CUSTOMERIMAGE", "CUSTOMERREF=" + Integer.valueOf(cursor3.getInt(getColumnIndex(cursor3, "CUSTOMERREF"))) + " AND ROWID<>" + Integer.valueOf(cursor3.getInt(getColumnIndex(cursor3, "LREF"))) + " AND DOCNR=1 AND DOCTYP=3", null);
                                        } while (cursor3.moveToNext());
                                    }
                                } catch (Exception e4) {
                                    e = e4;
                                    cursor4 = rawQuery;
                                    Log.e(TAG, "checkCustomerImageIndex: ", e);
                                    if (cursor4 != null) {
                                        cursor4.close();
                                    }
                                    if (cursor3 != null) {
                                        return;
                                    } else {
                                        return;
                                    }
                                } catch (Throwable th3) {
                                    this.th = th3;
                                    cursor4 = rawQuery;
                                    if (cursor4 != null) {
                                    }
                                    if (cursor3 != null) {
                                    }
                                    throw this.th;
                                }
                            }
                            if (rawQuery != null && !rawQuery.isClosed()) {
                                rawQuery.close();
                            }
                            if (cursor3 == null || cursor3.isClosed()) {
                                return;
                            }
                        } catch (Exception e5) {
                            e = e5;
                            cursor3 = null;
                        } catch (Throwable th4) {
                            this.th = th4;
                            cursor3 = null;
                        }
                        cursor3.close();
                        return;
                    }
                }
                cursor.close();
            } catch (Exception e6) {
                e = e6;
                cursor = null;
            } catch (Throwable th5) {
                this.th = th5;
                if (cursor4 != null) {
                    cursor4.close();
                }
                throw this.th;
            }
        } catch (Throwable th6) {
            this.th = th6;
            cursor4 = cursor2;
            if (cursor4 != null && !cursor4.isClosed()) {
                cursor4.close();
            }
          try {
            throw this.th;
          } catch (Throwable e) {
            throw new RuntimeException(e);
          }
        }
    }
    private void execIndexSql(Class<?> cls, SQLiteDatabase sQLiteDatabase) {
        try {
            String createIndex = this.mISqlCreator.getCreateIndex(cls);
            if (TextUtils.isEmpty(createIndex)) {
                return;
            }
            Log.d(TAG, "execIndexSql: " + createIndex);
            sQLiteDatabase.execSQL(createIndex);
        } catch (SQLException e2) {
            Log.e(TAG, "execIndexSql: ", e2);
        }
    }
    public List<T> getTable(Class<T> cls) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList arrayList = new ArrayList();
        HashMap<String, String> tableColumnName = this.mISqlCreator.getTableColumnName(cls);
        Cursor cursor = null;
        try {
            try {
                cursor = readableDatabase.query(this.mISqlCreator.getTableName(cls), new String[]{ProxyConfig.MATCH_ALL_SCHEMES}, null, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        T newInstance = cls.newInstance();
                        newInstance.getClass();
                        for (String str : cursor.getColumnNames()) {
                            int columnIndex = cursor.getColumnIndex(str);
                            String str2 = tableColumnName.get(str);
                            Class<?> cls2 = newInstance.getClass();
                            do {
                                if (!cls2.getName().equalsIgnoreCase("java.lang.Object")) {
                                    Exception e;
                                    try {
                                        if (cls2.getDeclaredFields().length == 0) {
                                            cls2 = cls2.getSuperclass();
                                        } else {
                                            Field declaredField = cls2.getDeclaredField(str2);
                                            declaredField.setAccessible(true);
                                            if (cursor.getType(columnIndex) == Cursor.FIELD_TYPE_INTEGER) {
                                                declaredField.setInt(newInstance, cursor.getInt(columnIndex));
                                            } else if (cursor.getType(columnIndex) == Cursor.FIELD_TYPE_FLOAT) {
                                                try {
                                                    declaredField.setDouble(newInstance, cursor.getDouble(columnIndex));
                                                } catch (Exception e2) {
                                                    declaredField.setFloat(newInstance, cursor.getFloat(columnIndex));
                                                    Log.e(TAG, "getTable: ", e2);
                                                }
                                            } else if (cursor.getType(columnIndex) == Cursor.FIELD_TYPE_BLOB) {
                                                declaredField.set(newInstance, cursor.getBlob(columnIndex));
                                            } else if (cursor.getType(columnIndex) == Cursor.FIELD_TYPE_STRING) {
                                                declaredField.set(newInstance, cursor.getString(columnIndex));
                                            }
                                            try {
                                                declaredField.setAccessible(false);
                                            } catch (Exception e3) {
                                                e = e3;
                                                Log.e(TAG, "getTable: ", e);
                                                cls2 = cls2.getSuperclass();
                                            }
                                        }
                                    } catch (Exception e4) {
                                        e = e4;
                                    }
                                }
                                cls2 = cls2.getSuperclass();
                            } while (cls2 != null);
                        }
                        arrayList.add(newInstance);
                    }
                }
            } catch (Exception e5) {
                Log.e(TAG, "getTable: ", e5);
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return Collections.emptyList();
    }
    public List<T> getTable(Class<T> cls, String str, String[] strArr, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList arrayList = new ArrayList();
        HashMap<String, String> tableColumnName = this.mISqlCreator.getTableColumnName(cls);
        Cursor cursor = null;
        try {
            try {
                cursor = readableDatabase.query(this.mISqlCreator.getTableName(cls), new String[]{ProxyConfig.MATCH_ALL_SCHEMES}, str, strArr, str2, str3, str4, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        T newInstance = cls.newInstance();
                        newInstance.getClass();
                        for (String str5 : cursor.getColumnNames()) {
                            int columnIndex = cursor.getColumnIndex(str5);
                            String str6 = tableColumnName.get(str5);
                            Class<?> cls2 = newInstance.getClass();
                            do {
                                try {
                                    Field declaredField = cls2.getDeclaredField(str6);
                                    declaredField.setAccessible(true);
                                    if (cursor.getType(columnIndex) == Cursor.FIELD_TYPE_INTEGER) {
                                        declaredField.setInt(newInstance, cursor.getInt(columnIndex));
                                    } else if (cursor.getType(columnIndex) == Cursor.FIELD_TYPE_FLOAT) {
                                        try {
                                            declaredField.setDouble(newInstance, cursor.getDouble(columnIndex));
                                        } catch (Exception unused) {
                                            declaredField.setFloat(newInstance, cursor.getFloat(columnIndex));
                                        }
                                    } else if (cursor.getType(columnIndex) == Cursor.FIELD_TYPE_BLOB) {
                                        declaredField.set(newInstance, cursor.getBlob(columnIndex));
                                    } else if (cursor.getType(columnIndex) == Cursor.FIELD_TYPE_STRING) {
                                        declaredField.set(newInstance, cursor.getString(columnIndex));
                                    }
                                    declaredField.setAccessible(false);
                                } catch (Exception unused2) {
                                }
                                cls2 = cls2.getSuperclass();
                            } while (cls2 != null);
                        }
                        arrayList.add(newInstance);
                    }
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Exception e2) {
            Log.e(TAG, "getTable: ", e2);
        }
        return Collections.emptyList();
    }
    public List<T> getTableWhere(Class<T> cls, String str, String[] strArr) {
        return getTableWhere(cls, str, strArr, null);
    }
    public List<T> getTableWhere(Class<T> cls, String str, String[] strArr, @Nullable String str2) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList arrayList = new ArrayList();
        HashMap<String, String> tableColumnName = this.mISqlCreator.getTableColumnName(cls);
        Cursor cursor = null;
        try {
            try {
                cursor = readableDatabase.query(this.mISqlCreator.getTableName(cls), new String[]{ProxyConfig.MATCH_ALL_SCHEMES}, str, strArr, null, null, str2);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        T newInstance = cls.newInstance();
                        for (String str3 : cursor.getColumnNames()) {
                            int columnIndex = cursor.getColumnIndex(str3);
                            Field declaredField = newInstance.getClass().getDeclaredField(tableColumnName.get(str3));
                            declaredField.setAccessible(true);
                            if (cursor.getType(columnIndex) == Cursor.FIELD_TYPE_INTEGER) {
                                declaredField.setInt(newInstance, cursor.getInt(columnIndex));
                            } else if (cursor.getType(columnIndex) == Cursor.FIELD_TYPE_FLOAT) {
                                try {
                                    declaredField.setDouble(newInstance, cursor.getDouble(columnIndex));
                                } catch (Exception unused) {
                                    declaredField.setFloat(newInstance, cursor.getFloat(columnIndex));
                                }
                            } else if (cursor.getType(columnIndex) == Cursor.FIELD_TYPE_BLOB) {
                                declaredField.set(newInstance, cursor.getBlob(columnIndex));
                            } else if (cursor.getType(columnIndex) == Cursor.FIELD_TYPE_STRING) {
                                declaredField.set(newInstance, StringUtils.convertNullStringToString(cursor.getString(columnIndex)));
                            }
                            declaredField.setAccessible(false);
                        }
                        arrayList.add(newInstance);
                    }
                }
            } catch (Exception e2) {
                Log.e(TAG, "getTable: ", e2);
            }
        } catch (Throwable th) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
        return arrayList;
    }
    public List<T> getTable(Class<T> cls, String str, String[] strArr) {
        return getTable(cls, str, strArr, null, null, null);
    }
    public ArrayList<UserReports> getUserReports(String str) {
        ArrayList<UserReports> arrayList = new ArrayList<>();
        try {
            ISqlManager.UserReportsCursor userReportsCursor = new ISqlManager.UserReportsCursor(getReadableDatabase().rawQuery(str, new String[0]));
            if (userReportsCursor.moveToFirst()) {
                do {
                    arrayList.add(userReportsCursor.get());
                } while (userReportsCursor.moveToNext());
            }
            userReportsCursor.close();
        } catch (Exception e2) {
            Log.e(TAG, "selectCursorQuery: ", e2);
        }
        return arrayList;
    }
    public String getParamValue(ParameterTypes parameterTypes) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery(ContextUtils.getStringResource(R.string.qry_getParamValue), new String[]{String.valueOf(parameterTypes.getEnumId())});
                try {
                    try {
                        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                          str = cursor.getString(cursor.getColumnIndex(this.getString('COLUMN_PARAMVALUE')));
                        }
                    } catch (Exception e2) {
                        Log.e(TAG, "getParamValue: ", e2);
                    }
                } finally {
                    cursor.close();
                }
            } catch (Exception e3) {
                Log.e(TAG, "getParamValue: ", e3);
            }
        } finally {
            if (0 != 0) {
            }
        }
        return str;
    }
    public String getLogoParamValue(String str) {
        String str2 = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery(ContextUtils.getStringResource(R.string.qry_getLogoParamValue), new String[]{str});
                try {
                    if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                        str2 = cursor.getString(cursor.getColumnIndex(COLUMN_PARAMVALUE));
                    }
                } catch (Exception e2) {
                    Log.e(TAG, "getParamValue: ", e2);
                }
            } catch (Exception e3) {
                Log.e(TAG, "getParamValue: ", e3);
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
              cursor.close();
            }
        }
        return str2;
    }
    public ArrayList<CustGpsInfo> getCustomerLocations() {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        ArrayList<CustGpsInfo> arrayList = new ArrayList<>();
        try {
            ISqlManager.CustomerGPSCursor customerGPSCursor = new ISqlManager.CustomerGPSCursor(getReadableDatabase().rawQuery("SELECT G.* FROM CLCARD C INNER JOIN CUSTGPSINFO G ON G." + (baseErp.getErpType() == ErpType.NETSIS ? "CLCODE = C.CODE" : "CLIENTREF = C.LOGICALREF"), new String[0]));
            if (customerGPSCursor.moveToFirst()) {
                do {
                    arrayList.add(customerGPSCursor.get());
                } while (customerGPSCursor.moveToNext());
            }
            customerGPSCursor.close();
        } catch (Exception e2) {
            Log.e(TAG, "selectCursorQuery: ", e2);
        }
        return arrayList;
    }
    public String getEmailParamValue(String str) {
        String str2 = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery(ContextUtils.getStringResource(R.string.qry_getEmailParamValue), new String[]{str});
                try {
                    if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                        str2 = cursor.getString(cursor.getColumnIndex(COLUMN_PARAMVALUE));
                    }
                } catch (Exception e2) {
                    Log.e(TAG, "getParamValue: ", e2);
                }
            } catch (Exception e3) {
                Log.e(TAG, "getParamValue: ", e3);
            }
        } finally {
            if (0 != 0) {
            }
        }
        return str2;
    }
    public Object getParamValue(ParameterTypes parameterTypes, SqlReturnType sqlReturnType) {
        String paramValue = getParamValue(parameterTypes);
        if (sqlReturnType == SqlReturnType.INT) {
            return Integer.valueOf(StringUtils.convertStringToInt(paramValue));
        }
        if (sqlReturnType == SqlReturnType.BOOL) {
            return Boolean.valueOf(StringUtils.convertStringToBooleanParams(paramValue));
        }
        if (sqlReturnType == SqlReturnType.FLOAT) {
            return Float.valueOf(StringUtils.convertStringToFloat(paramValue));
        }
        if (sqlReturnType == SqlReturnType.DOUBLE) {
            return Double.valueOf(StringUtils.convertStringToDouble(paramValue));
        }
        return sqlReturnType == SqlReturnType.DATE ? StringUtils.convertStringToDate(paramValue) : paramValue;
    }
    public Cursor selectCursorQuery(@StringRes int i2, String... strArr) {
        try {
            return getReadableDatabase().rawQuery(ContextUtils.getStringResource(i2), new String[0]);
        } catch (Exception e2) {
            Log.e(TAG, "selectCursorQuery: ", e2);
            return null;
        }
    }
    public String getSingleField(Class<?> cls, String str, String str2) {
        try {
            String str3 = SqlLiteVariable._SELECT + str + SqlLiteVariable._FROM + this.mISqlCreator.getTableName(cls);
            if (!str2.equals("")) {
                str3 = str3 + SqlLiteVariable._WHERE + str2;
            }
            Cursor rawQuery = getReadableDatabase().rawQuery(str3, null);
            if (rawQuery == null || !rawQuery.moveToFirst()) {
                return "";
            }
            return rawQuery.getString(0);
        } catch (Exception e2) {
            Log.e(TAG, "getSingleField: ", e2);
            return "";
        }
    }
    public boolean getSingleFieldBoolean(Class<?> cls, String str, String str2) {
        String str3 = SqlLiteVariable._SELECT + str + SqlLiteVariable._FROM + this.mISqlCreator.getTableName(cls);
        if (!str2.equals("")) {
            str3 = str3 + SqlLiteVariable._WHERE + str2;
        }
        Cursor cursor = null;
        boolean z = false;
        try {
            try {
                cursor = getReadableDatabase().rawQuery(str3, null);
                if (cursor.moveToFirst()) {
                    if (cursor.getInt(0) == 1) {
                        z = true;
                    }
                }
            } catch (Exception e2) {
                Log.e(TAG, "getSingleField: ", e2);
            }
            cursor.close();
            return z;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }
    public double getMaxCMDate(Class<?> cls) {
        double d2 = 0.0d;
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT MAX(CMDATE) FROM " + this.mISqlCreator.getTableName(cls), null);
                if (cursor != null && cursor.moveToFirst()) {
                    d2 = cursor.getDouble(0);
                }
            } catch (Exception e2) {
                Log.e(TAG, "getMaxCMDate: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return d2;
    }
    public double getMaxCMDate(Class<?> cls, String str) {
        double d2 = 0.0d;
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT MAX(CMDATE) FROM " + this.mISqlCreator.getTableName(cls) + SqlLiteVariable._WHERE + str, null);
                if (cursor != null && cursor.moveToFirst()) {
                    d2 = cursor.getDouble(0);
                }
            } catch (Exception e2) {
                Log.e(TAG, "getMaxCMDate: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
      return d2;
    }
    public String getSingleField(String str, String str2, String str3) {
        String str4 = SqlLiteVariable._SELECT + str2 + SqlLiteVariable._FROM + str;
        if (!str3.equals("")) {
            str4 = str4 + SqlLiteVariable._WHERE + str3;
        }
        try {
            Cursor rawQuery = getReadableDatabase().rawQuery(str4, null);
            if (rawQuery == null || !rawQuery.moveToFirst()) {
                return "";
            }
            return rawQuery.getString(0);
        } catch (Exception e2) {
            Log.e(TAG, "getSingleField: ", e2);
            return "";
        }
    }
    public void loadDataPref(String str, ListPreference listPreference, String str2, String str3, ColType colType, Boolean bool) {
        loadDataPref(str, listPreference, str2, str3, colType, bool, Boolean.FALSE);
    }
    public void loadDataPref(String str, ListPreference listPreference, String str2, String str3, ColType colType, Boolean bool, Boolean bool2) {
        int i2;
        Cursor rawQuery = getReadableDatabase().rawQuery(str, null);
        CharSequence[] charSequenceArr = new String[0];
        String[] strArr = new String[0];
        if (rawQuery != null) {
            if (bool.booleanValue()) {
                charSequenceArr = new String[rawQuery.getCount() + 1];
                strArr = new String[rawQuery.getCount() + 1];
                charSequenceArr[0] = "";
                strArr[0] = "";
                i2 = 0;
            } else {
                charSequenceArr = new String[rawQuery.getCount()];
                strArr = new String[rawQuery.getCount()];
                i2 = -1;
            }
            for (int i3 = 0; i3 < rawQuery.getCount(); i3++) {
                if (rawQuery.moveToPosition(i3)) {
                    i2++;
                    ColType colType2 = ColType.metin;
                    charSequenceArr[i2] = (String) dbVal(rawQuery, str2, colType2);
                    ColType colType3 = ColType.sayi;
                    if (colType == colType3) {
                        strArr[i2] = String.valueOf(dbVal(rawQuery, str3, colType3));
                    } else {
                        strArr[i2] = (String) dbVal(rawQuery, str3, colType2);
                    }
                }
            }
        }
        listPreference.setEntries(charSequenceArr);
        listPreference.setEntryValues(strArr);
        if (charSequenceArr.length > 0) {
            if (bool2.booleanValue()) {
                if (charSequenceArr.length > 1) {
                    listPreference.setValue(strArr[1]);
                } else {
                    listPreference.setValue(strArr[0]);
                }
            } else {
                listPreference.setValue(strArr[0]);
            }
        } else {
            listPreference.setValue("");
        }
        rawQuery.close();
    }
    public boolean getCurRateInt() {
        boolean z = false;
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery(ContextUtils.getStringResource(R.string.qry_curr_rate_date), new String[]{StringUtils.convertIntToString(DateAndTimeUtils.getLogoDateInt())});
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        z = true;
                    }
                }
            } catch (Exception e2) {
                Log.e(TAG, "getCurRateInt: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return z;
    }
    public double getCurrRate(int i2) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        double d2 = 0.0d;
        Cursor cursor = null;
        try {
            try {
                cursor = readableDatabase.rawQuery(ContextUtils.getStringResource(R.string.qry_curr_rate), new String[]{StringUtils.convertIntToString(i2), StringUtils.convertIntToString(DateAndTimeUtils.getLogoDateInt())});
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    d2 = cursor.getDouble(cursor.getColumnIndex("RATE"));
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
                cursor = readableDatabase.rawQuery(ContextUtils.getStringResource(R.string.qry_curr_rate), new String[]{StringUtils.convertIntToString(i2), StringUtils.convertIntToString(DateAndTimeUtils.getLogoDateInt())});
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    d2 = cursor.getDouble(cursor.getColumnIndex("RATE"));
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
    public double getCurrRateWithControl(int i2, int i3) {
        if (i2 == i3) {
            return 1.0d;
        }
        return getCurrRate(i2);
    }
    public int getClCardSendMod(int i2) {
        Cursor cursor = null;
        int i3 = 0;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT SENDMOD FROM CLCARD WHERE LOGICALREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    i3 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClCardSendMod: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i3;
    }
    public String getClCardCurrCode(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT T.CURRCODE  FROM CLCARD C LEFT JOIN CURRTYPE T ON C.CCURRENCY=T.CURRTYPE WHERE C.LOGICALREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClCardCurrCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public String getCurrCode(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT T.CURRCODE  FROM CURRTYPE T WHERE T.CURRTYPE =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClCardCurrCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public int getWareHouseCostGrp(int i2) {
        Cursor cursor = null;
        int i3 = 0;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT COSTGRP FROM WAREHOUSE WHERE NR =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    i3 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getWareHouseCostGrp: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i3;
    }
    public ArrayList<Integer> getWareHouseCostGrpFactoryDivision(int i2) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String str = "SELECT COSTGRP, FACTNR, DIVISNR FROM WAREHOUSE WHERE NR =" + i2;
        ArrayList<Integer> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursor = readableDatabase.rawQuery(str, null);
                if (cursor != null && cursor.moveToFirst()) {
                    arrayList.add(Integer.valueOf(cursor.getInt(0)));
                    arrayList.add(Integer.valueOf(cursor.getInt(1)));
                    arrayList.add(Integer.valueOf(cursor.getInt(2)));
                }
            } catch (Exception e2) {
                Log.i(TAG, "getWareHouseCostGrp: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return arrayList;
    }
    public String getReferenceCode(int i2) {
        String str = ".";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT CODE FROM MUHREFCODES WHERE LOGICALREF = " + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getReferenceCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public int getWareHouseCode(String str) {
        Cursor cursor = null;
        int i2 = 0;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT CODE FROM WAREHOUSE WHERE AMBAR ='" + str + "'", null);
                if (cursor != null && cursor.moveToFirst()) {
                    i2 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getWareHouseCostGrp: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i2;
    }
    public String getClCode(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT CODE FROM CLCARD WHERE LOGICALREF =" + i2, null);
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
    public String getMuhAccCode(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT MUHACC_CODE FROM CLCARD WHERE LOGICALREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getMuhAccCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public int getClLogicalRef(String str) {
        Cursor cursor = null;
        int i2 = 0;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT LOGICALREF FROM CLCARD WHERE CODE ='" + str + "'", null);
                if (cursor != null && cursor.moveToFirst()) {
                    i2 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i2;
    }
    public String getClEmail(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT EMAILADDR FROM CLCARD WHERE LOGICALREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClEmail: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public String getClName(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT DEFINITION_ FROM CLCARD WHERE LOGICALREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClName: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public String getClName(String str) {
        String str2 = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT DEFINITION_ FROM CLCARD WHERE CODE ='" + str + "'", null);
                if (cursor != null && cursor.moveToFirst()) {
                    str2 = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClName: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str2;
    }
    public int getClEInvoiceUser(int i2) {
        Cursor cursor = null;
        int i3 = 0;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT ACCEPTEINV FROM CLCARD WHERE LOGICALREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    i3 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i3;
    }
    public String getPaymentCode(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT CODE FROM PAYMENT WHERE LOGICALREF=" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getPaymentCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public String getProjectCode(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT CODE FROM PROJECT WHERE LOGICALREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getProjectCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public String[] getVariantCode(int i2) {
        String[] strArr = {"", ""};
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT CODE,NAME FROM VARIANTS WHERE LOGICALREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    if (cursor.moveToPosition(0)) {
                        strArr[0] = cursor.getString(0);
                    }
                    if (cursor.moveToPosition(1)) {
                        strArr[1] = cursor.getString(1);
                    }
                }
            } catch (Exception e2) {
                Log.i(TAG, "getVariantCode: ", e2);
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return strArr;
    }
    public String getUnitCode(int i2, boolean z) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String sb2 = "SELECT CODE FROM " +
                (z ? "ITEMUNITS" : "SERVICEUNITS") +
                " WHERE LOGICALREF =" +
                i2;
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = readableDatabase.rawQuery(sb2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getUnitCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public int getUnitLineNr(String str, String str2, boolean z) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT LINENR FROM ");
        sb.append(z ? "ITEMUNITS" : "SERVICEUNITS");
        sb.append(" WHERE ITEMCODE = '");
        sb.append(str);
        sb.append("' AND CODE ='");
        sb.append(str2);
        sb.append("'");
        Cursor cursor = null;
        int i2 = 1;
        try {
            try {
                cursor = readableDatabase.rawQuery(sb.toString(), null);
                if (cursor != null && cursor.moveToFirst()) {
                    i2 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getUnitLineNr: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i2;
    }
    public int getUnitLogicalRef(String str, int i2) {
        Cursor cursor = null;
        int i3 = 0;
        try {
            try {
                cursor = getReadableDatabase().query("ITEMUNITS", new String[]{"LOGICALREF"}, "CODE=? AND ITEMREF=?", new String[]{str, String.valueOf(i2)}, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    i3 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getUnitLogicalRef: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i3;
    }
    public int getUnitLogicalRef(int i2, int i3) {
        Cursor cursor = null;
        int i4 = 0;
        try {
            try {
                cursor = getReadableDatabase().query("ITEMUNITS", new String[]{"LOGICALREF"}, "LINENR=? AND ITEMREF=?", new String[]{String.valueOf(i2), String.valueOf(i3)}, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    i4 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getUnitLogicalRef: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i4;
    }
    public int getServiceUnitLogicalRef(String str, int i2) {
        Cursor cursor = null;
        int i3 = 0;
        try {
            try {
                cursor = getReadableDatabase().query("SERVICEUNITS", new String[]{"LOGICALREF"}, "CODE=? AND ITEMREF=?", new String[]{str, String.valueOf(i2)}, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    i3 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getServiceUnitLogicalRef: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i3;
    }
    public double[] getUnitConvfact(int i2, int i3) {
        double[] dArr = {1.0d, 1.0d};
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT CONVFACT1,CONVFACT2 FROM ITEMUNITS WHERE LOGICALREF=" + i2 + " AND ITEMREF =" + i3, null);
                if (cursor != null && cursor.moveToFirst()) {
                    dArr[0] = cursor.getDouble(0);
                    dArr[1] = cursor.getDouble(1);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getUnitConvfact: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return dArr;
    }
    public double[] getUnitConvfact(String str, String str2) {
        double[] dArr = {1.0d, 1.0d};
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT CONVFACT1,CONVFACT2 FROM ITEMUNITS WHERE CODE='" + str + "' AND ITEMCODE ='" + str2 + "'", null);
                if (cursor != null && cursor.moveToFirst()) {
                    dArr[0] = cursor.getDouble(0);
                    dArr[1] = cursor.getDouble(1);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getUnitConvfact: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return dArr;
    }
    public double[] getUnitConvfact(String str, int i2) {
        double[] dArr = {1.0d, 1.0d};
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT CONVFACT1,CONVFACT2 FROM ITEMUNITS WHERE CODE='" + str + "' AND ITEMREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    dArr[0] = cursor.getDouble(0);
                    dArr[1] = cursor.getDouble(1);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getUnitConvfact: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return dArr;
    }
    public double[] getFicheGpsInfo(Class<?> cls, int i2) {
        double[] dArr = {0.0d, 0.0d};
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT ENLEM , BOYLAM  from " + this.mISqlCreator.getTableName(cls) + " WHERE LOGICALREF=" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    try {
                        dArr[0] = cursor.getDouble(0);
                        dArr[1] = cursor.getDouble(1);
                    } catch (Exception unused) {
                        if (cursor.moveToFirst()) {
                            dArr[0] = StringUtils.convertStringToDouble(cursor.getString(0));
                            dArr[1] = StringUtils.convertStringToDouble(cursor.getString(1));
                        }
                    }
                }
            } catch (Exception e2) {
                Log.i(TAG, "getShipLocCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return dArr;
    }
    public String getMainCaseCashCode() {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT CODE FROM USERCASE", null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getCaseCashCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public String getCaseCashCode(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT CODE FROM USERCASE WHERE LOGICALREF=" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getCaseCashCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public String getCaseMuhCode(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT MUHCODE FROM USERCASE WHERE LOGICALREF=" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getCaseMuhCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public String getBankAccCode(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT CODE FROM BANKACCOUNTS WHERE LOGICALREF=" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getBankAccCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public String getSpeCodeLogicalRef(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT SPECODE AS CODE FROM SPECODES WHERE LOGICALREF=" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getSpeCodeLogicalRef: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public String getShipAddressCode(int i2, int i3) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().query("SHIPADDRESS", new String[]{"CODE"}, "LOGICALREF=? AND CLREF=?", new String[]{String.valueOf(i2), String.valueOf(i3)}, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getArpCodeShpm: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public CustomerDiscount getCustomerDiscountRate(int i2) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        CustomerDiscount customerDiscount = new CustomerDiscount();
        Cursor cursor = null;
        try {
            try {
                cursor = readableDatabase.query("CLCARD", new String[]{"DISCRATE", "DISCTYPE"}, "LOGICALREF=?", new String[]{String.valueOf(i2)}, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    customerDiscount.setRatio(cursor.getDouble(cursor.getColumnIndex(ContextUtils.getStringResource(R.string.column_discrate))));
                    customerDiscount.setType(cursor.getInt(cursor.getColumnIndex(ContextUtils.getStringResource(R.string.column_disctype))));
                }
            } catch (Exception e2) {
                Log.e(TAG, "getCustomerDiscountRate: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return customerDiscount;
    }
    public int getItemTrackType(int i2) {
        Cursor cursor = null;
        int i3 = 0;
        try {
            try {
                cursor = getReadableDatabase().query("ITEMS", new String[]{"TRACKTYPE"}, "LOGICALREF=?", new String[]{String.valueOf(i2)}, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    i3 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getItemTrackType: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i3;
    }
    public int getItemLogicalRef(String str) {
        Cursor cursor = null;
        int i2 = 0;
        try {
            try {
                cursor = getReadableDatabase().query("ITEMS", new String[]{"LOGICALREF"}, "CODE=?", new String[]{str}, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    i2 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getItemLogicalRef: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i2;
    }
    public String getItemCode(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().query("ITEMS", new String[]{"CODE"}, "LOGICALREF=?", new String[]{String.valueOf(i2)}, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getItemCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public String getItemName(String str) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        String str2 = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().query("ITEMS", new String[]{(baseErp.getUseProductNameDescription() && baseErp.getErpType() == ErpType.TIGER) ? "NAME2" : "NAME"}, "CODE=?", new String[]{str}, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str2 = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getItemName: ", e2);
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return str2;
    }
    public void loadPrefData(String str, ListPreference listPreference, String str2, String str3, ColType colType, Boolean bool) {
        loadPrefData(str, listPreference, str2, str3, colType, bool, Boolean.FALSE);
    }
    public void loadPrefData(String str, ListPreference listPreference, String str2, String str3, ColType colType, Boolean bool, Boolean bool2) {
        int i2;
        Cursor rawQuery = getReadableDatabase().rawQuery(str, null);
        CharSequence[] charSequenceArr = new String[0];
        String[] strArr = new String[0];
        if (rawQuery != null) {
            if (bool.booleanValue()) {
                charSequenceArr = new String[rawQuery.getCount() + 1];
                strArr = new String[rawQuery.getCount() + 1];
                charSequenceArr[0] = "";
                strArr[0] = "";
                i2 = 0;
            } else {
                charSequenceArr = new String[rawQuery.getCount()];
                strArr = new String[rawQuery.getCount()];
                i2 = -1;
            }
            for (int i3 = 0; i3 < rawQuery.getCount(); i3++) {
                if (rawQuery.moveToPosition(i3)) {
                    i2++;
                    ColType colType2 = ColType.metin;
                    charSequenceArr[i2] = (String) dbVal(rawQuery, str2, colType2);
                    ColType colType3 = ColType.sayi;
                    if (colType == colType3) {
                        strArr[i2] = String.valueOf(dbVal(rawQuery, str3, colType3));
                    } else {
                        strArr[i2] = (String) dbVal(rawQuery, str3, colType2);
                    }
                }
            }
        }
        listPreference.setEntries(charSequenceArr);
        listPreference.setEntryValues(strArr);
        if (charSequenceArr.length > 0) {
            if (bool2.booleanValue()) {
                if (charSequenceArr.length > 1) {
                    listPreference.setValue(strArr[1]);
                } else {
                    listPreference.setValue(strArr[0]);
                }
            } else {
                listPreference.setValue(strArr[0]);
            }
        } else {
            listPreference.setValue("");
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
    }
    public void loadPrefData(String str, ListPreference listPreference, String str2, String str3, ColType colType, Boolean bool, Boolean bool2, Boolean bool3) {
        int i2;
        Cursor rawQuery = getReadableDatabase().rawQuery(str, null);
        CharSequence[] charSequenceArr = new String[0];
        String[] strArr = new String[0];
        if (rawQuery != null) {
            if (bool.booleanValue()) {
                charSequenceArr = new String[rawQuery.getCount() + 1];
                strArr = new String[rawQuery.getCount() + 1];
                charSequenceArr[0] = "";
                strArr[0] = "";
                i2 = 0;
            } else {
                charSequenceArr = new String[rawQuery.getCount()];
                strArr = new String[rawQuery.getCount()];
                i2 = -1;
            }
            for (int i3 = 0; i3 < rawQuery.getCount(); i3++) {
                if (rawQuery.moveToPosition(i3)) {
                    i2++;
                    ColType colType2 = ColType.metin;
                    charSequenceArr[i2] = (String) dbVal(rawQuery, str2, colType2);
                    ColType colType3 = ColType.sayi;
                    if (colType == colType3) {
                        strArr[i2] = String.valueOf(dbVal(rawQuery, str3, colType3));
                    } else {
                        strArr[i2] = (String) dbVal(rawQuery, str3, colType2);
                    }
                }
            }
        }
        listPreference.setEntries(charSequenceArr);
        listPreference.setEntryValues(strArr);
        if (charSequenceArr.length > 0) {
            if (bool2.booleanValue()) {
                if (charSequenceArr.length > 1) {
                    listPreference.setValue(strArr[1]);
                } else {
                    listPreference.setValue(strArr[0]);
                }
            } else if (!bool3.booleanValue()) {
                listPreference.setValue(strArr[0]);
            } else {
                listPreference.setValue("");
            }
        } else {
            listPreference.setValue("");
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
    }
    public Object dbVal(Cursor cursor, String str, ColType colType) {
        Object obj;
        try {
            if (colType == ColType.sayi) {
                obj = Integer.valueOf(cursor.getInt(cursor.getColumnIndex(str)));
            } else if (colType == ColType.virgullu) {
                obj = Float.valueOf(cursor.getFloat(cursor.getColumnIndex(str)));
            } else if (colType == ColType.blob) {
                obj = cursor.getBlob(cursor.getColumnIndex(str));
            } else {
                obj = cursor.getString(cursor.getColumnIndex(str));
            }
        } catch (Exception e2) {
            Log.e(TAG, "dbVal: ", e2);
            obj = null;
        }
        if (obj != null) {
            return obj;
        }
        if (colType == ColType.tarih) {
            return DateAndTimeUtils.nowDateDMY();
        }
        if (colType == ColType.metin) {
            return "";
        }
        if (colType == ColType.sayi) {
            return 0;
        }
        if (colType == ColType.blob) {
            return null;
        }
        return colType == ColType.virgullu ? Double.valueOf(0.0d) : obj;
    }
    public String getParam(String str) {
        String str2 = "";
        Cursor cursor = null;
        String str3 = null;
        String str4 = null;
        cursor = null;
        try {
            try {
                Cursor rawQuery = getReadableDatabase().rawQuery("SELECT * FROM PARAM WHERE PARAMNO='" + str + "'", null);
                try {
                    if (rawQuery.moveToFirst()) {
                        str4 = "";
                        str3 = (String) dbVal(rawQuery, COLUMN_PARAMVALUE, ColType.metin);
                    }
                    rawQuery.close();
                    return str3;
                } catch (Exception e2) {
                    e = e2;
                    String str5 = str4;
                    cursor = rawQuery;
                    str2 = str5;
                    Log.e(TAG, "getParam: ", e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return str2;
                } catch (Throwable th) {
                    th = th;
                    cursor = rawQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
            str2 = null;
        }
        return str2;
    }
    public String upVal(String str) {
        String str2;
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT PARAMVALUE FROM USERPARAMS WHERE PARAMNO='" + str + "'", null);
        try {
            try {
                rawQuery.moveToFirst();
                str2 = (String) dbVal(rawQuery, COLUMN_PARAMVALUE, ColType.metin);
                rawQuery.close();
            } catch (Exception e2) {
                Log.e("upVal", "upVal: ", e2);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                str2 = "";
            }
            return str2;
        } catch (Throwable th) {
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }
    public String upValRest(String str) {
        String str2;
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT PARAMVALUE FROM USERPARAMS WHERE PARAMNO='" + str + "'", null);
        try {
            try {
                rawQuery.moveToFirst();
                str2 = (String) dbVal(rawQuery, COLUMN_PARAMVALUE, ColType.metin);
                rawQuery.close();
            } catch (Exception e2) {
                Log.e("upValRest", "upValRest: ", e2);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                str2 = "";
            }
            return str2;
        } catch (Throwable th) {
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }
    public boolean sozlesmeTaksitSayisiniKontrolEt(String str, String str2) {
        boolean z = false;
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT INSTALLMENTINTERVAL FROM CREDITAGGR WHERE CODE = '" + str + "'", null);
                cursor.moveToFirst();
                if (Arrays.asList(((String) dbVal(cursor, "INSTALLMENTINTERVAL", ColType.metin)).split(",")).contains(str2)) {
                    z = true;
                }
            } catch (Exception e2) {
                Log.e(TAG, "sozlesmeTaksitSayisiniKontrolEt", e2);
            }
            cursor.close();
            return z;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }
    public boolean nakitKrediKartiDetayiVarmi() {
        boolean z = false;
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT LOGICALREF FROM CASHCREDITDETAIL WHERE CASHCREDITID=" + MainActivity.sFicheRef, null);
                if (cursor.getCount() > 0) {
                    z = true;
                }
            } catch (Exception e2) {
                Log.e(TAG, "nakitKrediKartiDetayiVarmi: ", e2);
            }
            cursor.close();
            return z;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }
    public boolean cekSenetDetayiVarmi() {
        boolean z = false;
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT LOGICALREF FROM CHEQUEDEEDDETAIL WHERE CHEQUEDEEDID=" + MainActivity.sFicheRef, null);
                if (cursor.getCount() > 0) {
                    z = true;
                }
            } catch (Exception e2) {
                Log.e(TAG, "cekSenetDetayiVarmi: ", e2);
            }
            cursor.close();
            return z;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }
    public void cekSenetToplamlariGuncelle() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        readableDatabase.beginTransaction();
        try {
            try {
                readableDatabase.execSQL("UPDATE CHEQUEDEED SET TOTAL=" + cekSenetToplaminiGetir() + " WHERE LOGICALREF=" + MainActivity.sFicheRef);
                readableDatabase.setTransactionSuccessful();
            } catch (SQLException e2) {
                Log.e(TAG, "cekSenetToplamlariGuncelle: ", e2);
            }
        } finally {
            readableDatabase.endTransaction();
        }
    }
    public double cekSenetToplaminiGetir() {
        double d2 = 0.0d;
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT TOTAL FROM CHEQUEDEEDDETAIL WHERE CHEQUEDEEDID=" + MainActivity.sFicheRef, null);
                if (cursor != null) {
                    for (int i2 = 0; i2 <= cursor.getCount() - 1; i2++) {
                        if (cursor.moveToPosition(i2)) {
                            d2 += StringUtils.toDouble(dbVal(cursor, "TOTAL", ColType.metin).toString());
                        }
                    }
                }
            } catch (Exception e2) {
                Log.e(TAG, "cekSenetToplaminiGetir: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return d2;
    }
    public void nakitKrediKartiToplamlariGuncelle() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            try {
                writableDatabase.execSQL("UPDATE CASHCREDIT SET TOTAL=" + nakitKrediKartiToplaminiGetir() + " WHERE LOGICALREF=" + MainActivity.sFicheRef);
                writableDatabase.setTransactionSuccessful();
            } catch (SQLException e2) {
                Log.e(TAG, "nakitKrediKartiToplamlariGuncelle: ", e2);
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }
    public double nakitKrediKartiToplaminiGetir() {
        double d2 = 0.0d;
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT TOTAL FROM CASHCREDITDETAIL WHERE CASHCREDITID=" + MainActivity.sFicheRef, null);
                if (cursor != null) {
                    for (int i2 = 0; i2 <= cursor.getCount() - 1; i2++) {
                        if (cursor.moveToPosition(i2)) {
                            d2 += StringUtils.toDouble(dbVal(cursor, "TOTAL", ColType.metin).toString());
                        }
                    }
                }
            } catch (Exception e2) {
                Log.e(TAG, "nakitKrediKartiToplaminiGetir: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return d2;
    }
    public Double getCurrRate(String str) {
        double d2 = 0;
        String str2;
        Cursor cursor = null;
        double d3 = 0.0d;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT RATE FROM CURR WHERE CURRCODE='" + str + "'", null);
                if (!cursor.moveToNext()) {
                    str2 = BuildConfig.NETSIS_DEMO_PASSWORD;
                } else {
                    str2 = dbVal(cursor, "RATE", ColType.metin).toString();
                }
                cursor.close();
                d2 = StringUtils.toDouble(str2);
            } catch (Exception e2) {
                e = e2;
            }
            if (d2 <= 0.0d) {
                try {
                    Double valueOf = Double.valueOf(1.0d);
                    cursor.close();
                    return valueOf;
                } catch (Exception e3) {
                    e = e3;
                    d3 = d2;
                    alert(e.getMessage());
                    d2 = d3;
                    return Double.valueOf(d2);
                }
            }
            cursor.close();
            return Double.valueOf(d2);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    public void nakitKrediTahsilatSil() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            try {
                writableDatabase.execSQL("DELETE FROM CASHCREDITDETAIL WHERE CASHCREDITID=" + MainActivity.sFicheRef);
                writableDatabase.execSQL("DELETE FROM CASHCREDIT WHERE LOGICALREF=" + MainActivity.sFicheRef);
                writableDatabase.setTransactionSuccessful();
            } catch (SQLException e2) {
                Log.e(TAG, "nakitKrediTahsilatSil: ", e2);
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }
    public void cekSenetTahsilatSil() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            try {
                writableDatabase.execSQL("DELETE FROM CHEQUEDEEDDETAIL WHERE CHEQUEDEEDID=" + MainActivity.sFicheRef);
                writableDatabase.execSQL("DELETE FROM CHEQUEDEED WHERE LOGICALREF=" + MainActivity.sFicheRef);
                writableDatabase.setTransactionSuccessful();
            } catch (SQLException e2) {
                Log.e(TAG, "cekSenetTahsilatSil: ", e2);
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }
    public void siparisDetayTeslimTarihleriniGuncelle(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            try {
                writableDatabase.execSQL("UPDATE ORDERDETAIL SET SHIPDATE='" + str + "' WHERE SALESFICHEID=" + MainActivity.sFicheRef);
                writableDatabase.setTransactionSuccessful();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }
    public void addSalesCond(int i2, int i3) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            try {
                writableDatabase.execSQL("INSERT INTO SALESCONDCTRL (FICHEID,TYPE) VALUES (" + i2 + "," + i3 + ")");
                writableDatabase.setTransactionSuccessful();
            } catch (SQLException e2) {
                Log.e(TAG, "addSalesCond: ", e2);
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }
    public boolean fisSatisKosuluUygulandiMi(int i2, int i3) {
        int i4;
        Cursor cursor = null;
        boolean z = true;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT ID FROM SALESCONDCTRL WHERE FICHEID=" + MainActivity.sFicheRef + " AND TYPE=" + i3, null);
                if (cursor.moveToFirst()) {
                    do {
                        i4 = cursor.getInt(0);
                    } while (cursor.moveToNext());
                } else {
                    i4 = 0;
                }
                if (i4 == 0) {
                    z = false;
                }
            } catch (Exception e2) {
                Log.e(TAG, "fisSatisKosuluUygulandiMi: ", e2);
            }
            cursor.close();
            return z;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }
    public String getUserCode(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT CODE FROM USERINFO WHERE USERID=" + i2, null);
                if (cursor != null && cursor.moveToPosition(0)) {
                    str = dbVal(cursor, "CODE", ColType.metin).toString();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public String getUserName(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT SALESMANNAME FROM USERINFO WHERE USERID=" + i2, null);
                if (cursor != null && cursor.moveToPosition(0)) {
                    str = dbVal(cursor, "SALESMANNAME", ColType.metin).toString();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public String getSingleField(String str, String str2, String str3, boolean z) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String str4 = SqlLiteVariable._SELECT + str2 + SqlLiteVariable._FROM + str;
        String str5 = "";
        if (!str3.equals("")) {
            str4 = str4 + SqlLiteVariable._WHERE + str3;
        }
        Cursor cursor = null;
        try {
            try {
                cursor = readableDatabase.rawQuery(str4, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str5 = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getSingleField: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str5;
    }
    public String getString(Cursor cursor, String str) {
        return cursor.getString(getColumnIndex(cursor, str));
    }
    public int getColumnIndex(Cursor cursor, String str) {
        return cursor.getColumnIndex(str);
    }
    public void addParamDbTwo(String str, String str2, String str3) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            try {
                writableDatabase.execSQL(SqlLiteVariable._DELETE + str + " WHERE PARAMNO='" + str2 + "'");
                writableDatabase.execSQL(SqlLiteVariable._INSERT_INTO + str + " (PARAMNO,PARAMVALUE) VALUES ('" + str2 + "','" + str3 + "')");
                writableDatabase.setTransactionSuccessful();
            } catch (SQLException e2) {
                Log.e(TAG, "addParamDbTwo: ", e2);
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }
    public List<String> getList(Class<?> cls, String str, String str2, boolean z) {
        Throwable th;
        Cursor cursor;
        ArrayList arrayList;
        Exception e2;
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String str3 = SqlLiteVariable._SELECT + str + SqlLiteVariable._FROM + cls.getSimpleName();
        if (!str2.equals("")) {
            str3 = str3 + SqlLiteVariable._WHERE + str2;
        }
        ArrayList arrayList2 = null;
        try {
            cursor = readableDatabase.rawQuery(str3, null);
            if (cursor != null) {
                try {
                    try {
                        if (cursor.moveToFirst()) {
                            arrayList = new ArrayList();
                            do {
                                try {
                                    arrayList.add(cursor.getString(0));
                                } catch (Exception e3) {
                                    e2 = e3;
                                    Log.e(TAG, "getList: ", e2);
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    return arrayList;
                                }
                            } while (cursor.moveToNext());
                            arrayList2 = arrayList;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                } catch (Exception e4) {
                    arrayList = null;
                    e2 = e4;
                }
            }
            if (cursor == null) {
                return arrayList2;
            }
            cursor.close();
            return arrayList2;
        } catch (Exception e5) {
            arrayList = null;
            e2 = e5;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            if (cursor != null) {
            }
            try {
                throw th;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        return Collections.emptyList();
    }
    public Cursor getCaseCreditHedaerForPrint(int i2) {
        return getReadableDatabase().rawQuery(" SELECT I.*,  C.CODE AS CCODE,C.DEFINITION_ AS CNAME,C.SPECODE AS CSPECODE,C.CYPHCODE AS CCYPHCODE,C.ADDR1,C.ADDR2,  C.CITY,C.TOWN,C.TELNRS1,C.TELNRS2,C.FAXNR,C.TAXNR,C.TAXOFFICE,C.INCHARGE,C.DISCRATE,C.EMAILADDR, C.TRADINGGRP AS CTRADINGGRP,C.DEBIT_FLOAT,C.CREDIT_FLOAT,C.BAKIYE_FLOAT, C.DISTRICT AS CDISTRICT,C.TAXOFFCODE AS CTAXOFFCODE, B.CODE AS BCODE,B.DEFINITION_ AS BNAME, BA.CODE AS BACODE,BA.DEFINITION_ AS BANAME  FROM CASHCREDIT I  INNER JOIN CLCARD C ON C.LOGICALREF=I.CLREF  LEFT JOIN BANKS B ON B.LOGICALREF=I.BANKREF  LEFT JOIN BANKACCOUNTS BA ON B.LOGICALREF=I.BANKACCREF  WHERE I.LOGICALREF=" + i2, null);
    }
    public Cursor getCaseCreditDetailForPrint(int i2) {
        return getReadableDatabase().rawQuery(" SELECT I.*, P.CODE AS PAYCODE,P.DEFINITION_ AS PAYNAME FROM CASHCREDITDETAIL I  LEFT JOIN PAYMENT P ON P.LOGICALREF=I.PAYMENTREF  WHERE I.CASHCREDITID=" + i2, null);
    }
    public Cursor getCheequeDeedHedaerForPrint(int i2) {
        return getReadableDatabase().rawQuery(" SELECT I.*,  C.CODE AS CCODE,C.DEFINITION_ AS CNAME,C.SPECODE AS CSPECODE,C.CYPHCODE AS CCYPHCODE,C.ADDR1,C.ADDR2,  C.CITY,C.TOWN,C.TELNRS1,C.TELNRS2,C.FAXNR,C.TAXNR,C.TAXOFFICE,C.INCHARGE,C.DISCRATE,C.EMAILADDR, C.TRADINGGRP AS CTRADINGGRP,C.DEBIT_FLOAT,C.CREDIT_FLOAT,C.BAKIYE_FLOAT, C.DISTRICT AS CDISTRICT,C.TAXOFFCODE AS CTAXOFFCODE FROM CHEQUEDEED I  INNER JOIN CLCARD C ON C.LOGICALREF=I.CLREF  WHERE I.LOGICALREF=" + i2, null);
    }
    public Cursor getCheequeDeedDetailForPrint(int i2) {
        return getReadableDatabase().rawQuery(" SELECT I.* FROM CHEQUEDEEDDETAIL I  WHERE I.CHEQUEDEEDID=" + i2, null);
    }
    public Cursor getCaseForPrint(int i2) {
        return getReadableDatabase().rawQuery(" SELECT I.*,  C.CODE AS CCODE,C.DEFINITION_ AS CNAME,C.SPECODE AS CSPECODE,C.CYPHCODE AS CCYPHCODE,C.ADDR1,C.ADDR2,  C.CITY,C.TOWN,C.TELNRS1,C.TELNRS2,C.FAXNR,C.TAXNR,C.TAXOFFICE,C.INCHARGE,C.DISCRATE,C.EMAILADDR, C.TRADINGGRP AS CTRADINGGRP,C.DEBIT_FLOAT,C.CREDIT_FLOAT,C.BAKIYE_FLOAT, C.DISTRICT AS CDISTRICT,C.TAXOFFCODE AS CTAXOFFCODE FROM CASECASH I  INNER JOIN CLCARD C ON C.LOGICALREF=I.CLREF  WHERE I.LOGICALREF=" + i2, null);
    }
    public Cursor getDeliveryNoteDetailForPrint(int i2) {
        return getReadableDatabase().rawQuery(" SELECT I.LOGICALREF AS _id , STC.WAREHOUSENR  , I.CODE  , I.NAME  , IFNULL(SUM(STC.ONHAND), 0) AS ACTUALSTOCK  FROM ITEMS I  LEFT JOIN ITEMSTOCK STC ON STC.ITEMREF = I.LOGICALREF  WHERE STC.WAREHOUSENR=" + i2 + " GROUP BY I.LOGICALREF , STC.WAREHOUSENR , I.CODE , I.NAME  HAVING  IFNULL(SUM(STC.ONHAND), 0) > 0  ORDER BY I.NAME ", null);
    }
    public boolean isEInvoiceOrEArchiveCustomer(int i2) {
        return getLogoParamValue("USEEARCHIVE").equals(BuildConfig.NETSIS_DEMO_PASSWORD) || getClEInvoiceUser(i2) > 0;
    }
    public String getReportXML(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery(ContextUtils.getStringResource(R.string.qry_getReportXML), new String[]{String.valueOf(i2)});
                try {
                    if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                        str = cursor.getString(0);
                    }
                } catch (Exception e2) {
                    Log.e(TAG, "getReportXML: ", e2);
                }
            } catch (Exception e3) {
                Log.e(TAG, "getReportXML: ", e3);
            }
        } finally {
            if (0 != 0) {
            }
        }
        return str;
    }
    public String getReportXML(String str) {
        String str2 = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery(ContextUtils.getStringResource(R.string.qry_getReportXML_with_name), new String[]{str});
                try {
                    try {
                        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                            str2 = cursor.getString(0);
                        }
                    } finally {
                        cursor.close();
                    }
                } catch (Exception e2) {
                    Log.e(TAG, "getReportXML: ", e2);
                }
            } catch (Exception e3) {
                Log.e(TAG, "getReportXML: ", e3);
            }
        } finally {
            if (0 != 0) {
            }
        }
        return str2;
    }
    public int getClEInvoiceTyp(int i2) {
        Cursor cursor = null;
        int i3 = 0;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT EINVOICETYP FROM CLCARD WHERE LOGICALREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    i3 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i3;
    }
    public int getAvailableOfflineCustomerRef() {
        Cursor cursor = null;
        int i2 = 0;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT MIN(LOGICALREF) AS MIN FROM CLCARD", null);
                if (cursor != null && cursor.moveToFirst()) {
                    i2 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getAvailableOfflineCustomerRef: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i2;
    }
    public int getFicheCustomerRef(String str, int i2) {
        String str2 = "SELECT CLREF FROM " + str + " WHERE LOGICALREF =" + i2;
        Cursor cursor = null;
        int i3 = 0;
        try {
            try {
                cursor = getReadableDatabase().rawQuery(str2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    i3 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "isFicheCustomerTransfered: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i3;
    }
    public void updateTransferedCustomer(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        readableDatabase.beginTransaction();
        try {
            try {
                readableDatabase.execSQL("UPDATE CLCARD SET ISTRANSFER=1 WHERE CODE = '" + str + "'");
                readableDatabase.setTransactionSuccessful();
            } catch (SQLException e2) {
                Log.e(TAG, "updateTransferedCustomer: ", e2);
            }
        } finally {
            readableDatabase.endTransaction();
        }
    }
    public Cursor getAvailableAddTax(int i2, String str, String str2) {
        return getReadableDatabase().rawQuery(ContextUtils.getStringResource(R.string.qry_get_availableaddtax, String.valueOf(i2), DateAndTimeUtils.nowDate("yyyyMMdd"), str, str2), null);
    }
    public List<String> getFicheNoAndRef(Class<?> cls, int i2) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String str = "SELECT FICHENO, FICHEREF FROM " + this.mISqlCreator.getTableName(cls) + " WHERE LOGICALREF =" + i2;
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                cursor = readableDatabase.rawQuery(str, null);
                if (cursor != null && cursor.moveToFirst()) {
                    arrayList.add(cursor.getString(0));
                    arrayList.add(cursor.getString(1));
                }
            } catch (Exception e2) {
                Log.i(TAG, "isFicheCustomerTransfered: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return Collections.emptyList();
    }
    public boolean getInvoiceReceiptRelationIfTransfered(int i2, boolean z) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String stringResource = ContextUtils.getStringResource(z ? R.string.qry_select_receipt_invoice_transfered : R.string.qry_select_invoice_of_receipt_transfer, Integer.valueOf(i2));
        Cursor cursor = null;
        boolean z2 = false;
        try {
            try {
                cursor = readableDatabase.rawQuery(stringResource, null);
                if (cursor != null && cursor.moveToFirst()) {
                    if (cursor.getInt(0) == 1) {
                        z2 = true;
                    }
                }
            } catch (Exception e2) {
                Log.i(TAG, "isFicheCustomerTransfered: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return z2;
    }
    public double getClCardBalance(int i2) {
        Cursor cursor = null;
        double d2 = 0.0d;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT BAKIYE FROM CLCARD WHERE LOGICALREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    d2 = cursor.getDouble(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClCardBalance: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return d2;
    }
    public String getClCardTradingGrp(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT TRADINGGRP FROM CLCARD WHERE LOGICALREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    str = cursor.getString(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClCardBalance: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return str;
    }
    public int getClCardIsPerCurr(int i2) {
        Cursor cursor = null;
        int i3 = 0;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT ISPERCURR FROM CLCARD WHERE LOGICALREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    i3 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClCardIsPerCurr: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i3;
    }
    public int getClCardEArchiveInsteadOfDesp(int i2) {
        Cursor cursor = null;
        int i3 = 0;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT INSTEADOFDESP FROM CLCARD WHERE LOGICALREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    i3 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClCardEArchiveInsteadOfDesp: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i3;
    }
    public String getCustomerConditionCode(int i2) {
        String str = "";
        Cursor cursor = null;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT CONDITIONCODE FROM CLCARD WHERE LOGICALREF =" + i2, null);
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
    public int getCustomerCabinCount(String str, String str2) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String stringResource = ContextUtils.getStringResource(R.string.qry_get_customer_cabin_count, str, str2);
        int i2 = 0;
        Cursor cursor = null;
        try {
            try {
                cursor = readableDatabase.rawQuery(stringResource, null);
                if (cursor != null && cursor.moveToFirst()) {
                    i2 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClCode: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i2;
    }
    public String getCabinListSql(Context context, String str, int i2, int i3) {
        String string = context.getString(R.string.qry_get_cabin_list);
        try {
            return ContextUtils.formatStringEnglish(string, str, Integer.valueOf(i2), Integer.valueOf(i3), ErpCreator.getInstance().getmBaseErp().getUser().getFirmNr());
        } catch (Exception e2) {
            Log.e(TAG, "getLoaderSqlText: ", e2);
            return string;
        }
    }
    public String getAvailableCabinListOnTheStorageSql(Context context, int i2, int i3, String str) {
        String str2;
        String string = context.getString(R.string.qry_get_available_cabin_list_on_the_storage);
        try {
            if (TextUtils.isEmpty(str)) {
                str2 = "";
            } else {
                str2 = " AND (" + context.getString(R.string.column_code) + " LIKE '%" + str + "%' )";
            }
            return ContextUtils.formatStringEnglish(string, str2, Integer.valueOf(i2), Integer.valueOf(i3), ErpCreator.getInstance().getmBaseErp().getUser().getFirmNr());
        } catch (Exception e2) {
            Log.e(TAG, "getLoaderSqlText: ", e2);
            return string;
        }
    }
    public int getClProfileId(int i2) {
        Cursor cursor = null;
        int i3 = 0;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT PROFILEID FROM CLCARD WHERE LOGICALREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    i3 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClProfileId: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
      return i3;
    }
    public void updateCustomerRiskTotals(ClRisk clRisk) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("RISKLIMIT", Double.valueOf(clRisk.getRiskLimit()));
            contentValues.put("RISKCLOSED", Double.valueOf(clRisk.getRiskClosed()));
            contentValues.put("RISKTOTAL", Double.valueOf(clRisk.getRiskTotal()));
            if (ErpCreator.getInstance().getmBaseErp().getErpType() == ErpType.TIGER) {
                contentValues.put("RISKCONTROLTYPE", Integer.valueOf(clRisk.getRiskControlType()));
                contentValues.put("ACCRISKLIMIT", Double.valueOf(clRisk.getAccRiskLimit()));
                contentValues.put("ACCRISKCLOSED", Double.valueOf(clRisk.getAccRiskClosed()));
                contentValues.put("ACCRISKTOTAL", Double.valueOf(clRisk.getAccRiskTotal()));
                contentValues.put("DESPRISKLIMIT", Double.valueOf(clRisk.getDespRiskLimit()));
                contentValues.put("DESPRISKCLOSED", Double.valueOf(clRisk.getDespRiskClosed()));
                contentValues.put("DESPRISKTOTAL", Double.valueOf(clRisk.getDespRiskTotal()));
                contentValues.put("DESPRISKLIMITSUG", Double.valueOf(clRisk.getDespRiskLimitSug()));
                contentValues.put("DESPRISKCLOSEDSUG", Double.valueOf(clRisk.getDespRiskClosedSug()));
                contentValues.put("DESPRISKTOTALSUG", Double.valueOf(clRisk.getDespRiskTotalSug()));
                contentValues.put("ORDRISKLIMIT", Double.valueOf(clRisk.getOrdRiskLimit()));
                contentValues.put("ORDRISKCLOSED", Double.valueOf(clRisk.getOrdRiskClosed()));
                contentValues.put("ORDRISKTOTAL", Double.valueOf(clRisk.getOrdRiskTotal()));
                contentValues.put("ORDRISKLIMITSUG", Double.valueOf(clRisk.getOrdRiskLimitSug()));
                contentValues.put("ORDRISKCLOSEDSUG", Double.valueOf(clRisk.getOrdRiskClosedSug()));
                contentValues.put("ORDRISKTOTALSUG", Double.valueOf(clRisk.getOrdRiskTotalSug()));
            }
            getWritableDatabase().update("CLRISK", contentValues, "CODE='" + clRisk.getCode() + "'", null);
        } catch (SQLException e2) {
            Log.e(TAG, "updateCustomerRiskTotals: ", e2);
        }
    }
    public void saveCustomerRiskTotals(ClRisk clRisk) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            try {
                if (ErpCreator.getInstance().getmBaseErp().getErpType() == ErpType.NETSIS) {
                    writableDatabase.execSQL("INSERT INTO CLRISK (CLREF,CODE,RISKLIMIT,RISKCLOSED,RISKTOTAL) VALUES (" + clRisk.getClRef() + ",'" + clRisk.getCode() + "'," + clRisk.getRiskLimit() + "," + clRisk.getRiskClosed() + "," + clRisk.getRiskTotal() + ")");
                } else {
                    writableDatabase.execSQL("INSERT INTO CLRISK (CLREF,CODE,RISKLIMIT,RISKCLOSED,RISKTOTAL,RISKCONTROLTYPE,ACCRISKLIMIT,ACCRISKCLOSED,ACCRISKTOTAL,DESPRISKLIMIT,DESPRISKCLOSED,DESPRISKTOTAL,DESPRISKLIMITSUG,DESPRISKCLOSEDSUG,DESPRISKTOTALSUG,ORDRISKLIMIT,ORDRISKCLOSED,ORDRISKTOTAL,ORDRISKLIMITSUG,ORDRISKCLOSEDSUG,ORDRISKTOTALSUG) VALUES (" + clRisk.getClRef() + ",'" + clRisk.getCode() + "'," + clRisk.getRiskLimit() + "," + clRisk.getRiskClosed() + "," + clRisk.getRiskTotal() + "," + clRisk.getRiskControlType() + "," + clRisk.getAccRiskLimit() + "," + clRisk.getAccRiskClosed() + "," + clRisk.getAccRiskTotal() + "," + clRisk.getDespRiskLimit() + "," + clRisk.getDespRiskClosed() + "," + clRisk.getDespRiskTotal() + "," + clRisk.getDespRiskLimitSug() + "," + clRisk.getDespRiskClosedSug() + "," + clRisk.getDespRiskTotalSug() + "," + clRisk.getOrdRiskLimit() + "," + clRisk.getOrdRiskClosed() + "," + clRisk.getOrdRiskTotal() + "," + clRisk.getOrdRiskLimitSug() + "," + clRisk.getOrdRiskClosedSug() + "," + clRisk.getOrdRiskTotalSug() + ")");
                }
                writableDatabase.setTransactionSuccessful();
            } catch (SQLException e2) {
                Log.e(TAG, "saveCustomerRiskTotals: ", e2);
            }
            writableDatabase.endTransaction();
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }
    public int getClEDespatchUser(int i2) {
        Cursor cursor = null;
        int i3 = 0;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT ACCEPTEDESP FROM CLCARD WHERE LOGICALREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    i3 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClEInvoiceUser: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i3;
    }
    public int getClEInvoiceUser(String str) {
        Cursor cursor = null;
        int i2 = 0;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT ACCEPTEINV FROM CLCARD WHERE CODE ='" + str + "'", null);
                if (cursor != null && cursor.moveToFirst()) {
                    i2 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClEInvoiceUser: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i2;
    }
    public int getClCardDueDate(int i2) {
        Cursor cursor = null;
        int i3 = 0;
        try {
            try {
                cursor = getReadableDatabase().rawQuery("SELECT DUEDATE FROM CLCARD WHERE LOGICALREF =" + i2, null);
                if (cursor != null && cursor.moveToFirst()) {
                    i3 = cursor.getInt(0);
                }
            } catch (Exception e2) {
                Log.i(TAG, "getClCardDueDate: ", e2);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i3;
    }
    public void updateEDocStatus(EDocStatus eDocStatus, int i2) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        readableDatabase.beginTransaction();
        try {
            try {
                readableDatabase.execSQL("UPDATE INVOICE SET EDOCSTATUS=" + eDocStatus.ordinal() + " WHERE LOGICALREF = '" + i2 + "'");
                readableDatabase.setTransactionSuccessful();
            } catch (SQLException e2) {
                Log.e(TAG, "updateTransferedCustomer: ", e2);
            }
        } finally {
            readableDatabase.endTransaction();
        }
    }
    protected static List<String> convertToStringArrayList(List<RecommendedProducts> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<RecommendedProducts> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getStockRef());
        }
        return arrayList;
    }
    public class SalesTableNames {
        private final String detailTable;
        private final String table;

        public SalesTableNames(String str, String str2) {
            this.table = str;
            this.detailTable = str2;
        }

        public String getTable() {
            return this.table;
        }

        public String getDetailTable() {
            return this.detailTable;
        }
    }
    protected SalesTableNames getTableName(SalesType salesType) {
        String str;
        String str2;
        if (SalesUtils.isSalesTypeOrder(salesType)) {
            str = "ORDERS";
            str2 = "ORDERDETAIL";
        } else {
            str = "INVOICE";
            str2 = "INVOICEDETAIL";
        }
        return new SalesTableNames(str, str2);
    }
}
