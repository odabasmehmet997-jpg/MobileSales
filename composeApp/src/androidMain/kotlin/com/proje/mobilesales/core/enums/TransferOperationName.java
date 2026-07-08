package com.proje.mobilesales.core.enums;

import androidx.annotation.StringRes;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.CabinTrans;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCredit;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.Chequedeed;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import com.proje.mobilesales.features.dbmodel.Invoice;
import com.proje.mobilesales.features.dbmodel.Order;
import com.proje.mobilesales.features.dbmodel.WhTransfer;
import com.proje.mobilesales.features.penetration.model.database.UserPenetration;
import com.proje.mobilesales.features.todo.model.TodoInfoModel;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class TransferOperationName extends Enum<TransferOperationName> {
    private static final EnumEntries ENTRIES;
    private static final TransferOperationName[] VALUES;
    private final Class<?> databaseClass;
    private final int processType;

    private final int resId;
    public static final TransferOperationName ORDER = new TransferOperationName(DailyInfo.ORDER, 0, R.string.str_order, Order.class, 0);
    public static final TransferOperationName DEMAND = new TransferOperationName(DailyInfo.DEMAND, 1, R.string.str_demand, Order.class, -1);
    public static final TransferOperationName INVOICE = new TransferOperationName("INVOICE", 2, R.string.str_invoice, Invoice.class, 1);
    public static final TransferOperationName RETURN_INVOICE = new TransferOperationName("RETURN_INVOICE", 3, R.string.str_return_invoice, Invoice.class, 2);
    public static final TransferOperationName DISPATCH = new TransferOperationName("DISPATCH", 4, R.string.str_dispatch, Invoice.class, 3);
    public static final TransferOperationName RETURN_DISPATCH = new TransferOperationName("RETURN_DISPATCH", 5, R.string.str_return_dispatch, Invoice.class, 4);
    public static final TransferOperationName ONE_TO_ONE_CHANGE = new TransferOperationName("ONE_TO_ONE_CHANGE", 6, R.string.str_one_to_one_change, Invoice.class, 1);
    public static final TransferOperationName CHEQUE = new TransferOperationName("CHEQUE", 7, R.string.str_cheque_receipt, Chequedeed.class, 5);
    public static final TransferOperationName DEED = new TransferOperationName("DEED", 8, R.string.str_bill_receipt, Chequedeed.class, 6);
    public static final TransferOperationName CASH = new TransferOperationName(DailyInfo.CASH, 9, R.string.str_cash_receipt, CashCredit.class, 7);
    public static final TransferOperationName CREDIT_CARD = new TransferOperationName("CREDIT_CARD", 10, R.string.str_credit_cart_receipt, CashCredit.class, 8);
    public static final TransferOperationName VISIT = new TransferOperationName(DailyInfo.VISIT, 11, R.string.str_visit, VisitInfo.class, 9);
    public static final TransferOperationName PENETRATION = new TransferOperationName("PENETRATION", 12, R.string.str_customer_penetration, UserPenetration.class, 10);
    public static final TransferOperationName CASE_CASH = new TransferOperationName("CASE_CASH", 13, R.string.str_case_receipt, CaseCash.class, 11);
    public static final TransferOperationName CUSTOMER = new TransferOperationName("CUSTOMER", 14, R.string.str_customer_accounts, ClCard.class, 12);
    public static final TransferOperationName TODOWORPROC = new TransferOperationName("TODOWORPROC", 15, R.string.str_todo_info, TodoInfoModel.class, 13);
    public static final TransferOperationName RETAIL_INVOICE = new TransferOperationName("RETAIL_INVOICE", 16, R.string.str_sales_retail_invoice, Invoice.class, 14);
    public static final TransferOperationName RETAIL_RETURN_INVOICE = new TransferOperationName("RETAIL_RETURN_INVOICE", 17, R.string.str_sales_retail_return_invoice, Invoice.class, 15);
    public static final TransferOperationName CABINTRANS = new TransferOperationName("CABINTRANS", 18, R.string.str_cabin_operations, CabinTrans.class, -1);
    public static final TransferOperationName WHTRANSFER = new TransferOperationName(DailyInfo.WHTRANSFER, 19, R.string.str_sales_transfer, WhTransfer.class, 16);

    private static TransferOperationName[] values() {
        return new TransferOperationName[]{ORDER, DEMAND, INVOICE, RETURN_INVOICE, DISPATCH, RETURN_DISPATCH, ONE_TO_ONE_CHANGE, CHEQUE, DEED, CASH, CREDIT_CARD, VISIT, PENETRATION, CASE_CASH, CUSTOMER, TODOWORPROC, RETAIL_INVOICE, RETAIL_RETURN_INVOICE, CABINTRANS, WHTRANSFER};
    }

    public static EnumEntries<TransferOperationName> getEntries() {
        return ENTRIES;
    }

    public static TransferOperationName valueOf(String str) {
        return Enum.valueOf(TransferOperationName.class, str);
    }

    public static TransferOperationName[] values() {
        return VALUES.clone();
    }

    private TransferOperationName( String str, int i2, int i3, Class cls, int i4) {
        super(str, i2);
        this.resId = i3;
        this.databaseClass = cls;
        this.processType = i4;
    }

    public int getResId() {
        return this.resId;
    }

    public Class<?> getDatabaseClass() {
        return this.databaseClass;
    }

    public int getProcessType() {
        return this.processType;
    }

    static {
        TransferOperationName[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
