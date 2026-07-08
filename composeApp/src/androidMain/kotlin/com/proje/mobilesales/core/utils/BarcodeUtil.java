package com.proje.mobilesales.core.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.enums.ProductListShowType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.TrackType;
import com.proje.mobilesales.core.interfaces.AsyncReportResponse;
import com.proje.mobilesales.core.interfaces.IBarcodeSearchResult;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.tigerwcf.*;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.features.cabinoperation.interfaces.ICabinBarcodeSearchResult;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.product.model.Product;
import com.proje.mobilesales.features.product.model.ProductListParameter;
import com.proje.mobilesales.features.product.model.database.RecommendedProducts;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import org.jetbrains.annotations.UnknownNullability;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BarcodeUtil implements AsyncReportResponse {
    private BarcodeProduct barcodeProduct;
    private IBarcodeSearchResult barcodeSearchResult;
    private final BaseErp baseErp;
    private ICabinBarcodeSearchResult cabinBarcodeSearchResult;
    private ArrayList<BarcodeResult> mBarcodeFilter;
    private int mCustomerRef;
    private int mDefOrderId;
    private int mDivisionNr;
    private int mPaymentRef;
    private String mPaymentTradeGroup;
    private String mProductGroupCode;
    private ProductListParameter mProductListParameter;
    private ProductListShowType mProductListShowType;
    private SalesType mSalesType;
    private String mSpecode;
    private int mWareHouseNr;
    private ArrayList<RecommendedProducts> recommendedProductList;
    private ArrayList<SeriLotItem> seriLotItems;
    private final ISqlManager sqlManager;
    private final String mFilter = "";
    private final boolean isBarcodeSearch = true;
    private final ArrayList<Integer> seriItemRefList = new ArrayList<>();
    public void onPreExecute(ProcessType processType) {
    }
    public BarcodeUtil(IBarcodeSearchResult iBarcodeSearchResult, ISqlManager iSqlManager, BaseErp baseErp, int i2, SalesType salesType, int i3, String str, int i4, String str2, int i5, String str3) {
        this.barcodeSearchResult = iBarcodeSearchResult;
        this.sqlManager = iSqlManager;
        this.baseErp = baseErp;
        this.mCustomerRef = i2;
        this.mSalesType = salesType;
        this.mWareHouseNr = i3;
        this.mPaymentTradeGroup = str;
        this.mPaymentRef = i4;
        setmBarcodeFilter(new ArrayList<>());
        this.mProductListParameter = baseErp.getProductListParameter(salesType);
        this.mProductListShowType = ProductListShowType.PRODUCT;
        this.mProductGroupCode = str2;
        this.mDivisionNr = i5;
        this.mSpecode = str3;
        if (salesType == null || !SalesUtils.isSalesTypeWhTransfer(salesType)) {
            return;
        }
        this.mWareHouseNr = baseErp.getUser().getWarehouseNr();
    }
    public BarcodeUtil(ICabinBarcodeSearchResult iCabinBarcodeSearchResult, ISqlManager iSqlManager, BaseErp baseErp) {
        this.cabinBarcodeSearchResult = iCabinBarcodeSearchResult;
        this.sqlManager = iSqlManager;
        this.baseErp = baseErp;
    }
    public void getProductFromBarcode(BarcodeResult barcodeResult) {
        this.seriItemRefList.clear();
        getmBarcodeFilter().add(barcodeResult);
        this.barcodeProduct = this.sqlManager.getSingleBarcodeResult(ContextUtils.getmContext(), this.baseErp.getLogoSqlHelper().getBarcodeScannerSql(), R.string.column_item_ref, barcodeResult.getBarcode());
        if (this.baseErp.getUseStoredProcedureForBarcode()) {
            execWmsBarcodeSp(barcodeResult.getBarcode());
            return;
        }
        if (getBarcodeProduct().getItemRef() != 0) {
            getProductList();
        } else if (this.baseErp.getErpType() == ErpType.NETSIS) {
            this.barcodeSearchResult.getSearchResult(null);
            getmBarcodeFilter().clear();
        } else {
            checkSeriLotAfterBarcode();
        }
    }
    private void execWmsBarcodeSp(String str) {
        this.baseErp.execWmsBarcodeSp(str, new BarcodeSpResponseListener(this, str));
    }
    public BarcodeProduct getBarcodeProduct() {
        return this.barcodeProduct;
    }
    private class BarcodeSpResponseListener(WeakReference<BarcodeUtil> mBarcodeUtil,
                                             String barcode) implements ResponseListener<ArrayList<WmsBarcodeModel>> {
            private BarcodeSpResponseListener(BarcodeUtil mBarcodeUtil, String barcode) { this(new WeakReference<>(mBarcodeUtil).get(), barcode);}
            public void onResponse(ArrayList<ArrayList<WmsBarcodeModel>> arrayList) {
                if (this.mBarcodeUtil.get() == null || arrayList == null) {
                    return;
                }
                if (arrayList.size() == 0) {
                    this.mBarcodeUtil.get().barcodeSearchResult.getSearchResult(null);
                    this.mBarcodeUtil.get().getmBarcodeFilter().clear();
                    return;
                }
                BarcodeResult barcodeResult = this.mBarcodeUtil.get().getmBarcodeFilter().get(this.mBarcodeUtil.get().getmBarcodeFilter().size() - 1);
                barcodeResult.setBarcode(arrayList.get(0).getBarcode());
                barcodeResult.setAmount(arrayList.get(0).getAmount());
                barcodeResult.setUnitCode(arrayList.get(0).getUnitCode());
                barcodeResult.setSecondAmount(arrayList.get(0).getSecondAmount());
                barcodeResult.setSecondUnitCode(arrayList.get(0).getSecondUnitCode());
                this.mBarcodeUtil.get().barcodeProduct = this.mBarcodeUtil.get().sqlManager.getSingleBarcodeResult(ContextUtils.getmContext(), this.mBarcodeUtil.get().baseErp.getLogoSqlHelper().getBarcodeScannerSql(), R.string.column_item_ref, barcodeResult.getBarcode());
                if (this.mBarcodeUtil.get().getBarcodeProduct().getItemRef() != 0) {
                    this.mBarcodeUtil.get().getProductList();
                    this.mBarcodeUtil.get().getBarcodeProduct().setFoundByStoredProcedure(true);
                    this.mBarcodeUtil.get().getBarcodeProduct().setSearchBarcode(this.barcode);
                } else {
                    this.mBarcodeUtil.get().barcodeSearchResult.getSearchResult(null);
                    this.mBarcodeUtil.get().getmBarcodeFilter().clear();
                }
            }
            public void onError(String str) {
                if (this.mBarcodeUtil.get() != null) {
                    Log.d("AA", "onError: " + str);
                    this.mBarcodeUtil.get().barcodeSearchResult.getSearchResult(null);
                    this.mBarcodeUtil.get().getmBarcodeFilter().clear();
                }
            }
        public void onFailure(Throwable throwable) {}
        public void onResponse(Boolean aBoolean) {}
        public void onResponse(Sales sales) {}
        public void onResponse(TigerServiceResult tigerServiceResult) {}
        public void onResponse() {}
        public void onResponse(Integer integer) {}
        public boolean equals(Object obj) { return false; }
        public int hashCode() { return 0;}
        public String toString() { return ""; }
    }
    public void getProductList() {
        this.sqlManager.getProductList(getLoaderSqlText(50, 0), this.mProductListParameter.getUnitPriceDigit(), new ProductListResponseListener(this), this.mSpecode);
    }
    private String getLoaderSqlText(int i2, int i3) {
        String format;
        long nanoTime = System.nanoTime();
        String productSortListSql = this.baseErp.getLogoSqlHelper().getProductSortListSql(ContextUtils.getmContext(), this.mProductListParameter, this.mDefOrderId);
        ProductListShowType productListShowType = this.mProductListShowType;
        if (productListShowType == ProductListShowType.ALL) {
            format = String.format("%s UNION %s %s %s", getProductSql(productSortListSql), getServiceSql(productSortListSql), productSortListSql, ContextUtils.formatStringEnglish(ContextUtils.getmContext().getString(R.string.qry_limit), Integer.valueOf(i2), Integer.valueOf(i3)));
        } else {
            format = String.format("%s %s %s", productListShowType == ProductListShowType.PRODUCT ? getProductSql(productSortListSql) : getServiceSql(productSortListSql), productSortListSql, ContextUtils.formatStringEnglish(ContextUtils.getmContext().getString(R.string.qry_limit), Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
        return format.replaceAll("\\s*[\\r\\n]+\\s*", "").trim();
    }
    public ArrayList<BarcodeResult> getmBarcodeFilter() {
        return this.mBarcodeFilter;
    }
    public void setmBarcodeFilter(ArrayList<BarcodeResult> arrayList) {
        this.mBarcodeFilter = arrayList;
    }
    public ArrayList<SeriLotItem> getSeriLotItems() {
        return this.seriLotItems;
    }
    public void setSeriLotItems(ArrayList<SeriLotItem> arrayList) {
        this.seriLotItems = arrayList;
    }
    private class ProductListResponseListener(WeakReference<BarcodeUtil> mBarcodeUtil) implements ResponseListener<ArrayList<Product>> {
            private ProductListResponseListener(BarcodeUtil mBarcodeUtil) { this(new WeakReference<>(mBarcodeUtil));}
            public void onResponse(PrintSlipModel arrayList) {
                if (this.mBarcodeUtil.get() == null || arrayList == null) {
                    return;
                }
                Log.e("BarcodeUtil", arrayList.toString());
                if (arrayList.size() <= 0) {
                    this.mBarcodeUtil.get().barcodeSearchResult.getSearchResult(null);
                } else {
                    if (this.mBarcodeUtil.get().getSeriLotItems() != null) {
                        Iterator<SeriLotItem> it = this.mBarcodeUtil.get().getSeriLotItems().iterator();
                        while (it.hasNext()) {
                            SeriLotItem next = it.next();
                            if (arrayList.get(0).getLogicalRef() == next.itemRef) {
                                arrayList.get(0).getSalesSerialLots().add(next.serilot);
                                arrayList.get(0).setAmount(arrayList.get(0).getAmount() + next.serilot.reAmount);
                            }
                        }
                    }
                    this.mBarcodeUtil.get().barcodeSearchResult.getSearchResult(arrayList.get(0));
                }
                this.mBarcodeUtil.get().getmBarcodeFilter().clear();
            }
            public void onError(String str) {
                if (this.mBarcodeUtil.get() != null) {
                    Log.d("AA", "onError: " + str);
                    this.mBarcodeUtil.get().barcodeSearchResult.getSearchResult(null);
                    this.mBarcodeUtil.get().getmBarcodeFilter().clear();
                }
            }
        public void onFailure(Throwable throwable) {}
        public void onResponse(Boolean aBoolean) {}
        public void onResponse(Sales sales) {}
        public void onResponse(TigerServiceResult tigerServiceResult) {}
        public void onResponse(ArrayList<ArrayList<Product>> obj) {}
        public void onResponse() {}
        public void onResponse(Integer integer) {}
        public boolean equals(Object obj) { return false;}
        public int hashCode() { return 0; }
        public String toString() { return "";}
    }
    private String getProductSql(String str) {
        return this.baseErp.getLogoSqlHelper().getProductListSql(ContextUtils.getmContext(), this.mProductListParameter, this.mCustomerRef, this.mPaymentTradeGroup, this.mWareHouseNr, getmBarcodeFilter(), this.mFilter, this.mDefOrderId, str, this.isBarcodeSearch, this.seriItemRefList, this.mPaymentRef, getBarcodeProduct().getVariant(), this.mProductGroupCode, this.mDivisionNr, this.recommendedProductList);
    }
    private String getServiceSql(String str) {
        return this.baseErp.getLogoSqlHelper().getServiceListSql(ContextUtils.getmContext(), this.mProductListParameter, this.mCustomerRef, this.mPaymentTradeGroup, this.mWareHouseNr, getmBarcodeFilter(), this.mFilter, this.mDefOrderId, str, this.mPaymentRef, getBarcodeProduct().getVariant());
    }
    public void processFinish(REPORTXML reportxml, ProcessType processType) {
        List<REPORTLINE> list;
        setSeriLotItems(new ArrayList<>());
        this.seriItemRefList.clear();
        if (reportxml != null && (list = reportxml.reportLines) != null) {
            int size = list.size();
            if (size > 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    REPORTLINE reportline = reportxml.reportLines.get(i2);
                    Serilot serilot = new Serilot();
                    serilot.logicalRef = StringUtils.convertStringToInt(reportline.f1208X);
                    serilot.code = reportline.f1209Y;
                    serilot.reAmount = Float.parseFloat(reportline.f1210Z);
                    serilot.amount = Float.parseFloat(reportline.f1210Z);
                    serilot.unit = reportline.f1207W;
                    serilot.expDate = DateAndTimeUtils.convertYMDToDMY(reportline.f1203A.split(ExifInterface.GPS_DIRECTION_TRUE)[0]);
                    serilot.name = reportline.f1204B;
                    getSeriLotItems().add(new SeriLotItem(serilot, StringUtils.convertStringToInt(reportline.f1205K)));
                    this.seriItemRefList.add(Integer.valueOf(getSeriLotItems().get(i2).itemRef));
                }
                getProductList();
                return;
            }
            this.barcodeSearchResult.getSearchResult(null);
            getmBarcodeFilter().clear();
            return;
        }
        this.barcodeSearchResult.getSearchResult(null);
        getmBarcodeFilter().clear();
    }
    private SelectResult checkSeriLotListWCF(String str) {
        LogoTigerTableName logoTigerTableName = new LogoTigerTableName(ErpCreator.getInstance().getmBaseErp().getUser().getDbName(), USER.firmano, USER.periodNr);
        SelectResult selectResult = new SelectResult();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT  ST.LOGICALREF [X], ST.AMOUNT[Z] , SL.CODE [Y], USLINE.CODE[W] , ST.EXPDATE [A] , SL.NAME [B] , ST.ITEMREF [K] FROM " + logoTigerTableName.SLTRANS + " ST WITH(NOLOCK) LEFT OUTER JOIN " + logoTigerTableName.SERILOTN + " SL WITH(NOLOCK) ON  (ST.SLREF  =  SL.LOGICALREF) LEFT OUTER JOIN " + logoTigerTableName.STFICHE + " STFIC WITH(NOLOCK) ON  (ST.STFICHEREF  =  STFIC.LOGICALREF) LEFT OUTER JOIN " + logoTigerTableName.UNITSETL + " USLINE WITH(NOLOCK)  ON (ST.UOMREF  =  USLINE.LOGICALREF) WHERE (ST.CANCELLED = 0) AND (ST.LPRODSTAT = 0) ");
        if (this.mWareHouseNr == -1) {
            String replace = this.baseErp.getLogoSqlHelper().getParamValue(ParameterTypes.ptGeneralSettings_Warehouse).replace(";", ",");
            if (!replace.equals("")) {
                sb.append("AND (ST.INVENNO IN (" + replace + ")) ");
            }
        } else {
            sb.append("AND (ST.INVENNO = " + this.mWareHouseNr + ") ");
        }
        sb.append("AND (ST.EXIMFCTYPE IN ( 0 , 4 , 7 )) AND (ST.STATUS = 0) AND (ST.DATE_ <= CONVERT(dateTime, GETDATE(), 101)) AND (ST.IOCODE IN (1,2)) ");
        sb.append("AND (SL.CODE IN (" + str + ") ) ");
        sb.append("AND (ST.SLTYPE=" + TrackType.SERIAL.getType() + SqlLiteVariable._CLOSE_BRACKET);
        sb.append("AND (ST.REMAMOUNT > 0) AND (SL.STATE=1) ");
        selectResult.sql = sb.toString();
        selectResult.orderByText = "ORDER BY Y,X ";
        selectResult.type = ProcessType.GETCHECKSERILOT;
        return selectResult;
    }
    private void checkSeriLotAfterBarcode() {
        new ServicesClientForTiger(this).new ReportRetrieve(checkSeriLotListWCF(StringUtils.getBarcodeText(getmBarcodeFilter()))).execute();
    }
    public class SeriLotItem implements Serializable, Parcelable {
        private static final long serialVersionUID = 1;
        public final Parcelable.Creator<SeriLotItem> CREATOR = new Parcelable.Creator<SeriLotItem>() {
            void C25751() {
            }
            public SeriLotItem createFromParcel(Parcel parcel) {
                return BarcodeUtil.this.new SeriLotItem(parcel);
            }
            public SeriLotItem[] newArray(int i2) {
                return new SeriLotItem[i2];
            }
        };
        public int itemRef;
        public Serilot serilot;
        public int describeContents() {
            return 0;
        }
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeParcelable(this.serilot, i2);
            parcel.writeInt(this.itemRef);
        }
        public SeriLotItem(Serilot serilot, int i2) {
            this.serilot = serilot;
            this.itemRef = i2;
        }
        protected SeriLotItem(Parcel parcel) {
            this.serilot = parcel.readParcelable(Serilot.class.getClassLoader());
            this.itemRef = parcel.readInt();
        }
        class C25751 implements Parcelable.Creator<SeriLotItem> {
            C25751() {
            }

            
            public SeriLotItem createFromParcel(Parcel parcel) {
                return BarcodeUtil.this.new SeriLotItem(parcel);
            }

            
            public SeriLotItem[] newArray(int i2) {
                return new SeriLotItem[i2];
            }
        }
    }
    public void getCustomerFromCabinBarcode(BarcodeResult barcodeResult) {
        BarcodeCustomer singleCabinBarcodeCustomerResult = this.sqlManager.getSingleCabinBarcodeCustomerResult(ContextUtils.getmContext(), this.baseErp.getLogoSqlHelper().getCabinBarcodeScannerSql(), barcodeResult.getBarcode(), this.baseErp.getUser().getFirmNr());
        if (singleCabinBarcodeCustomerResult.getCabinRef() <= 0) {
            singleCabinBarcodeCustomerResult.setCode(barcodeResult.getBarcode());
        }
        this.cabinBarcodeSearchResult.getCabinSearchResult(singleCabinBarcodeCustomerResult);
    }
    public void getAvailableCabinFromCabinBarcode(BarcodeResult barcodeResult) {
        this.cabinBarcodeSearchResult.getCabinSearchResult(this.sqlManager.getSingleAvailableCabinFromBarcode(ContextUtils.getmContext(), this.baseErp.getLogoSqlHelper().getAvailableCabinOnTheStorageBarcodeScannerSql(), barcodeResult.getBarcode(), this.baseErp.getUser().getFirmNr()));
    }
}
