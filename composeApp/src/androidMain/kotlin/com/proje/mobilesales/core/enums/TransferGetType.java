package com.proje.mobilesales.core.enums;
 
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class TransferGetType extends Enum<TransferGetType> {
    private static final  EnumEntries ENTRIES;
    public int id;
    public int resId;
    public static final TransferGetType OTHER_INFO = new TransferGetType("OTHER_INFO", 0, R.string.str_other_info, -1);
    public static final TransferGetType USER_INFORMATION = new TransferGetType("USER_INFORMATION", 1, R.string.str_user, 0);
    public static final TransferGetType WAREHOUSE = new TransferGetType("WAREHOUSE", 2, R.string.str_warehouses, 1);
    public static final TransferGetType BRANCHES = new TransferGetType("BRANCHES", 3, R.string.str_branches, 2);
    public static final TransferGetType FACTORY = new TransferGetType("FACTORY", 4, R.string.str_factory, 3);
    public static final TransferGetType DIVISION = new TransferGetType("DIVISION", 5, R.string.str_department, 4);
    public static final TransferGetType TODO = new TransferGetType("TODO", 6, R.string.str_todo, 5);
    public static final TransferGetType MARKETING_MESSAGE = new TransferGetType("MARKETING_MESSAGE", 7, R.string.str_marketing_message, 6);
    public static final TransferGetType VISIT = new TransferGetType(DailyInfo.VISIT, 8, R.string.str_visit, 7);
    public static final TransferGetType PENETRATION = new TransferGetType("PENETRATION", 9, R.string.str_penetration, 8);
    public static final TransferGetType SPECODE = new TransferGetType("SPECODE", 10, R.string.str_specode, 9);
    public static final TransferGetType TRADING = new TransferGetType("TRADING", 11, R.string.str_trading_process, 10);
    public static final TransferGetType PAYMENT = new TransferGetType("PAYMENT", 12, R.string.str_payment, 11);
    public static final TransferGetType SHIP_TYPE = new TransferGetType("SHIP_TYPE", 13, R.string.str_ship_type, 12);
    public static final TransferGetType SHIP_AGENT = new TransferGetType("SHIP_AGENT", 14, R.string.str_ship_agent, 13);
    public static final TransferGetType ITEM = new TransferGetType("ITEM", 15, R.string.str_item, 14);
    public static final TransferGetType VARIANT = new TransferGetType("VARIANT", 16, R.string.str_variant, 15);
    public static final TransferGetType UNIT = new TransferGetType("UNIT", 17, R.string.str_units, 16);
    public static final TransferGetType BARCODE = new TransferGetType("BARCODE", 18, R.string.str_barcodes, 17);
    public static final TransferGetType FORM = new TransferGetType("FORM", 19, R.string.str_form, 18);
    public static final TransferGetType STOCK = new TransferGetType("STOCK", 20, R.string.str_stocks, 19);
    public static final TransferGetType PRICE = new TransferGetType("PRICE", 21, R.string.str_prices, 20);
    public static final TransferGetType DISCOUNT = new TransferGetType("DISCOUNT", 22, R.string.str_discounts, 21);
    public static final TransferGetType CUSTOMER = new TransferGetType("CUSTOMER", 23, R.string.str_customer_accounts, 22);
    public static final TransferGetType SHIP_ADDRESS = new TransferGetType("SHIP_ADDRESS", 24, R.string.str_ship_addresses, 23);
    public static final TransferGetType BANK = new TransferGetType("BANK", 25, R.string.str_banks, 24);
    public static final TransferGetType BANKACCOUNT = new TransferGetType("BANKACCOUNT", 26, R.string.str_bank_accounts, 25);
    public static final TransferGetType CASE = new TransferGetType(DailyInfo.CASE, 27, R.string.str_case, 26);
    public static final TransferGetType VEHICLE = new TransferGetType("VEHICLE", 28, R.string.str_vehicle_info, 27);
    public static final TransferGetType DESIGN_FILES = new TransferGetType("DESIGN_FILES", 29, R.string.str_design_files, 28);
    public static final TransferGetType CURRENCY = new TransferGetType("CURRENCY", 30, R.string.str_currency_informations, 29);
    public static final TransferGetType PROJECT = new TransferGetType("PROJECT", 31, R.string.str_project_code, 30);
    public static final TransferGetType EMAIL = new TransferGetType("EMAIL", 32, R.string.str_email_address, 31);
    public static final TransferGetType ERP_PARAMS = new TransferGetType("ERP_PARAMS", 33, R.string.str_erp_parameters, 32);
    public static final TransferGetType ITEM_IMAGE = new TransferGetType("ITEM_IMAGE", 34, R.string.str_material_picture, 33);
    public static final TransferGetType CUSTOMER_IMAGE = new TransferGetType("CUSTOMER_IMAGE", 35, R.string.str_customer_image, 34);
    public static final TransferGetType EMAIL_TEMPLATES = new TransferGetType("EMAIL_TEMPLATES", 36, R.string.str_email_templates, 35);
    public static    TransferGetType[] values() {
        return new TransferGetType[]{OTHER_INFO, USER_INFORMATION, WAREHOUSE, BRANCHES, FACTORY, DIVISION, TODO, MARKETING_MESSAGE, VISIT, PENETRATION, SPECODE, TRADING, PAYMENT, SHIP_TYPE, SHIP_AGENT, ITEM, VARIANT, UNIT, BARCODE, FORM, STOCK, PRICE, DISCOUNT, CUSTOMER, SHIP_ADDRESS, BANK, BANKACCOUNT, CASE, VEHICLE, DESIGN_FILES, CURRENCY, PROJECT, EMAIL, ERP_PARAMS, ITEM_IMAGE, CUSTOMER_IMAGE, EMAIL_TEMPLATES};
    }
    public static EnumEntries<TransferGetType> getEntries() {
        return ENTRIES;
    }
    public static TransferGetType valueOf(String str) {
        return  Enum.valueOf(TransferGetType.class, str);
    }
    private TransferGetType(String str, int i, int i2, int i3) {
        super(str,i);
        this.resId = i2;
        this.id = i3;
    }
    static {
        TransferGetType[] values = values();
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
