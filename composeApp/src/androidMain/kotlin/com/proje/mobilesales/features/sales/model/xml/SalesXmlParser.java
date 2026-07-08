package com.proje.mobilesales.features.sales.model.xml;

import android.util.Log;
import com.proje.mobilesales.core.enums.DataObjectType;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.core.Persister;

import static com.proje.mobilesales.core.enums.NetsisFicheTypeNo.Companion.WhenMappings.EnumSwitchMapping0;

public final class SalesXmlParser {
    public static final SalesXmlParser INSTANCE = new SalesXmlParser();
    public  class WhenMappings {
        public int[] EnumSwitchMapping0 = new int[0];

        {
            int[] iArr = new int[DataObjectType.values().length];
            try {
                iArr[DataObjectType.ADDINVOICE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DataObjectType.ADDDISPATCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DataObjectType.ADDORDER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            EnumSwitchMapping0 = iArr;
        }
    }
    private SalesXmlParser() {
    }
    public static SalesXml parse(DataObjectType dataObjectType, String str) {
        int i2;
        try {
            Persister persister = new Persister();
            if (dataObjectType == null) {
                i2 = -1;
            } else {
                i2 = EnumSwitchMapping0[dataObjectType.ordinal()];
            }
            if (i2 == 1) {
                return INSTANCE.convertToSalesXml((SalesInvoicesXml) persister.read((Class<? extends Object>) SalesInvoicesXml.class, str));
            } else if (i2 == 2) {
                return INSTANCE.convertToSalesXml((SalesDispatchesXml) persister.read((Class<? extends Object>) SalesDispatchesXml.class, str));
            } else if (i2 != 3) {
                return null;
            } else {
                return INSTANCE.convertToSalesXml((SalesOrdersXml) persister.read((Class<? extends Object>) SalesOrdersXml.class, str));
            }
        } catch (Exception e2) {
            Log.e("SalesXmlParser", "Error", e2);
            return null;
        }
    }
    public SalesXml convertToSalesXml(SalesInvoicesXml salesInvoicesXml) {
        if (salesInvoicesXml == null) {
            return null;
        }
        String guid = salesInvoicesXml.guid();
        List<SalesTransactionXml> salesTransactions = salesInvoicesXml.salesTransactions();
        Intrinsics.checkNotNull(salesTransactions);
        return new SalesXml(guid, salesTransactions);
    }
    public SalesXml convertToSalesXml(SalesDispatchesXml salesDispatchesXml) {
        if (salesDispatchesXml == null) {
            return null;
        }
        String guid = salesDispatchesXml.guid();
        List<SalesTransactionXml> salesTransactions = salesDispatchesXml.salesTransactions();
        Intrinsics.checkNotNull(salesTransactions);
        return new SalesXml(guid, salesTransactions);
    }
    public SalesXml convertToSalesXml(SalesOrdersXml salesOrdersXml) {
        if (salesOrdersXml == null) {
            return null;
        }
        String guid = salesOrdersXml.guid();
        Intrinsics.checkNotNull(guid);
        List<SalesTransactionXml> salesTransactions = salesOrdersXml.salesTransactions();
        Intrinsics.checkNotNull(salesTransactions);
        return new SalesXml(guid, salesTransactions);
    }
}
