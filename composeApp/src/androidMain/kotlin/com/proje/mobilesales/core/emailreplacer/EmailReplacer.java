package com.proje.mobilesales.core.emailreplacer;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.EmailTemplateType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditX;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCreditDetail;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeed;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.ChequedeedDetail;
import com.proje.mobilesales.features.customer.model.Customer;
import com.proje.mobilesales.features.customer.model.CustomerBeforeBalance;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.dbmodel.Bank;
import com.proje.mobilesales.features.dbmodel.BankAccount;
import com.proje.mobilesales.features.dbmodel.EmailTemplate;
import com.proje.mobilesales.features.dbmodel.Payment;
import com.proje.mobilesales.features.dbmodel.Project;
import com.proje.mobilesales.features.dbmodel.Variant;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;
import com.proje.mobilesales.features.product.model.ItemUnit;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.SalesFicheDiscountProps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import net.objecthunter.exp4j.ExpressionBuilder;
 
public abstract class EmailReplacer {
    ISqlManager sqlManager;
    BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
    String _html = "";
    String _header = "";
    String _footer = "";
    String _line = "";
    CalculatedFields _calculatedFields;
    Customer _customer;
    CustomerBeforeBalance _balances;
    ReplaceKeyWords rkw = new ReplaceKeyWords();
    EmailTemplateType _templateType = EmailTemplateType.Unknown;
    final String HEADER = "Header";
    final String MAIN = "Main";
    final String FOOTER = "Footer";

    public abstract EmailObject replaceHtml(EmailTemplateType emailTemplateType, CustomerBeforeBalance customerBeforeBalance);

    public void getTemplateHtml(final EmailTemplateType emailTemplateType) {
        final List table = baseErp.getLogoSqlHelper().getTable(EmailTemplate.class, "FORMTYPE=?", new String[]{String.valueOf(emailTemplateType.getmValue())});
        if (null == table || 0 >= table.size()) {
            return;
        }
        final String formContent = ((EmailTemplate) table.get(0)).getFormContent();
        _html = formContent;
        if (0 <= formContent.indexOf("<div id=\"header\">")) {
            final String str = _html;
            _header = str.substring(str.indexOf("<div id=\"header\">"), _html.indexOf("<div id=\"main\">"));
        }
        if (0 <= this._html.indexOf("<div id=\"main\">")) {
            if (0 <= this._html.indexOf("<div id=\"footer\">")) {
                final String str2 = _html;
                _line = str2.substring(str2.indexOf("<div id=\"main\">"), _html.indexOf("<div id=\"footer\">"));
            } else {
                final String str3 = _html;
                _line = str3.substring(str3.indexOf("<div id=\"main\">"), _html.lastIndexOf("</body>"));
            }
        }
        if (0 <= this._html.indexOf("<div id=\"footer\">")) {
            final String str4 = _html;
            _footer = str4.substring(str4.indexOf("<div id=\"footer\">"), _html.lastIndexOf("</body>"));
        }
        if ("".equals(((EmailTemplate) table.get(0)).getFormConfig())) {
            return;
        }
        final CalculatedFields calculatedFields = new Gson().fromJson(((EmailTemplate) table.get(0)).getFormConfig(), CalculatedFields.class);
        _calculatedFields = calculatedFields;
        final Iterator<CalculatedField> it = calculatedFields.getCalculatedFields().iterator();
        while (it.hasNext()) {
            final CalculatedField next = it.next();
            next.setFormula(next.getOrgFormula().replace(",", "."));
        }
    }

    private String getPlateFromLocalData(final Sales sales) {
        final List table = baseErp.getLogoSqlHelper().getTable(EDispatchAdditionalInfo.class, "SALESFICHEID=?", new String[]{String.valueOf(sales.getLogicalRef())});
        if (null != table && 0 < table.size()) {
            return ((EDispatchAdditionalInfo) table.get(0)).firstDriverPlate;
        }
        return "";
    }

    public void getCustomerInfo(final int i2, final EmailTemplateType emailTemplateType) {
        final List table = baseErp.getLogoSqlHelper().getTable(ClCard.class, "CODE=?", new String[]{baseErp.getLogoSqlHelper().getClCode(i2)});
        if (null == table || 0 >= table.size()) {
            return;
        }
        final Customer customer = new Customer();
        _customer = customer;
        customer.setCode(((ClCard) table.get(0)).getCode());
        final ErpType erpType = baseErp.getErpType();
        final ErpType erpType2 = ErpType.NETSIS;
        if (erpType == erpType2) {
            _customer.setTown(((ClCard) table.get(0)).getTownCode());
            _customer.setCity(((ClCard) table.get(0)).getCityCode());
        } else {
            _customer.setTown(((ClCard) table.get(0)).getTownDefinition());
            _customer.setCity(((ClCard) table.get(0)).getCityDefinition());
        }
        _customer.setTitle(((ClCard) table.get(0)).getDefinition());
        _customer.setSecondTitle(((ClCard) table.get(0)).getDefinition2());
        if (baseErp.getErpType() == erpType2) {
            _customer.setBalance(((ClCard) table.get(0)).getBakiye());
        } else {
            final Customer customer2 = _customer;
            final CustomerBeforeBalance customerBeforeBalance = _balances;
            customer2.setBalance(null != customerBeforeBalance ? customerBeforeBalance.getBalance() : ((ClCard) table.get(0)).getBakiye());
        }
        final Customer customer3 = _customer;
        final CustomerBeforeBalance customerBeforeBalance2 = _balances;
        customer3.setBeforeBalance(null != customerBeforeBalance2 ? StringUtils.formatDouble(customerBeforeBalance2.getBeforeBalance()) : null);
        _customer.setInfoNote(((ClCard) table.get(0)).getAddr1() + " " + ((ClCard) table.get(0)).getAddr2() + " " + ((ClCard) table.get(0)).getCityDefinition() + " " + ((ClCard) table.get(0)).getTownDefinition());
        baseErp.setCustomerInchargeEmailAddress((ClCard) table.get(0));
        switch (C25251.SwitchMapcomprojemobilesalescoreenumsEmailTemplateType[emailTemplateType.ordinal()]) {
            case 2:
                _customer.setEmail(((ClCard) table.get(0)).getOrdSendEmailAddr());
                break;
            case 3:
                _customer.setEmail(((ClCard) table.get(0)).getDspSendEmailAddr());
                break;
            case 4:
                _customer.setEmail(((ClCard) table.get(0)).getInvSendEmailAddr());
                break;
            case 5:
                _customer.setEmail(((ClCard) table.get(0)).getExtSendEmailAddr());
                break;
            case 6:
                _customer.setEmail(((ClCard) table.get(0)).getExtSendEmailAddr());
                break;
            case 7:
                _customer.setEmail(((ClCard) table.get(0)).getExtSendEmailAddr());
                break;
            case 8:
                _customer.setEmail(((ClCard) table.get(0)).getExtSendEmailAddr());
                break;
            case 9:
                _customer.setEmail(((ClCard) table.get(0)).getExtSendEmailAddr());
                break;
        }
    } 
    enum C25251 {
        ;
        static final int[] SwitchMapcomprojemobilesalescoreenumsEmailTemplateType;

        static {
            final int[] iArr = new int[EmailTemplateType.values().length];
            SwitchMapcomprojemobilesalescoreenumsEmailTemplateType = iArr;
            try {
                iArr[EmailTemplateType.Unknown.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C25251.SwitchMapcomprojemobilesalescoreenumsEmailTemplateType[EmailTemplateType.Order.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C25251.SwitchMapcomprojemobilesalescoreenumsEmailTemplateType[EmailTemplateType.Dispatch.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                C25251.SwitchMapcomprojemobilesalescoreenumsEmailTemplateType[EmailTemplateType.Invoice.ordinal()] = 4;
            } catch (final NoSuchFieldError unused4) {
            }
            try {
                C25251.SwitchMapcomprojemobilesalescoreenumsEmailTemplateType[EmailTemplateType.CashCollection.ordinal()] = 5;
            } catch (final NoSuchFieldError unused5) {
            }
            try {
                C25251.SwitchMapcomprojemobilesalescoreenumsEmailTemplateType[EmailTemplateType.CreditCollection.ordinal()] = 6;
            } catch (final NoSuchFieldError unused6) {
            }
            try {
                C25251.SwitchMapcomprojemobilesalescoreenumsEmailTemplateType[EmailTemplateType.CheckCollection.ordinal()] = 7;
            } catch (final NoSuchFieldError unused7) {
            }
            try {
                C25251.SwitchMapcomprojemobilesalescoreenumsEmailTemplateType[EmailTemplateType.BillCollection.ordinal()] = 8;
            } catch (final NoSuchFieldError unused8) {
            }
            try {
                C25251.SwitchMapcomprojemobilesalescoreenumsEmailTemplateType[EmailTemplateType.CaseCollection.ordinal()] = 9;
            } catch (final NoSuchFieldError unused9) {
            }
        }
    }

    void replaceFicheHeader(final Sales sales) {
        CharSequence charSequence;
        final String str;
        CharSequence charSequence2;
        final String str2;
        CharSequence charSequence3;
        final String str3;
        CharSequence charSequence4;
        final String str4;
        CharSequence charSequence5;
        final String str5;
        CharSequence charSequence6;
        final String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        if (TextUtils.isEmpty(_customer.getBeforeBalance())) {
            if (SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoice(sales.getmSalesType())) {
                final Customer customer = _customer;
                customer.setBeforeBalance(StringUtils.formatDouble(Math.abs(customer.getBalance() + sales.getTotalNet())));
            } else if (SalesUtils.isSalesTypeInvoiceOrRetailInvoice(sales.getmSalesType())) {
                final Customer customer2 = _customer;
                customer2.setBeforeBalance(StringUtils.formatDouble(Math.abs(customer2.getBalance() - sales.getTotalNet())));
            } else {
                final Customer customer3 = _customer;
                customer3.setBeforeBalance(StringUtils.formatDouble(customer3.getBalance()));
            }
        }
        final String str15 = _header;
        Objects.requireNonNull(rkw);
        String str16 = "{city}";
        final String replace = str15.replace("{city}", StringUtils.checkNullValueString(_customer.getCity()));
        _header = replace;
        Objects.requireNonNull(rkw);
        String str17 = "{district}";
        final String replace2 = replace.replace("{district}", StringUtils.checkNullValueString(_customer.getTown()));
        _header = replace2;
        Objects.requireNonNull(rkw);
        final String replace3 = replace2.replace("{customer_code}", StringUtils.checkNullValueString(_customer.getCode()));
        _header = replace3;
        Objects.requireNonNull(rkw);
        final String replace4 = replace3.replace("{customer_title}", StringUtils.checkNullValueString(_customer.getTitle()));
        _header = replace4;
        Objects.requireNonNull(rkw);
        final String replace5 = replace4.replace("{warehouse}", null != sales.getWareHouse() ? StringUtils.checkNullValueString(sales.getWareHouse().getDefinition()) : "");
        _header = replace5;
        Objects.requireNonNull(rkw);
        final String replace6 = replace5.replace("{special_code}", null != sales.getSpeCode() ? StringUtils.checkNullValueString(sales.getSpeCode().getDefinition()) : "");
        _header = replace6;
        Objects.requireNonNull(rkw);
        final String replace7 = replace6.replace("{authorization_code}", null != sales.getWarrantyCode() ? StringUtils.checkNullValueString(sales.getWarrantyCode().getDefinition()) : "");
        _header = replace7;
        Objects.requireNonNull(rkw);
        final String replace8 = replace7.replace("{document_number}", null != sales.getDocumentNo() ? StringUtils.checkNullValueString(sales.getDocumentNo().getDefinition()) : "");
        _header = replace8;
        Objects.requireNonNull(rkw);
        final String replace9 = replace8.replace("{document_tracking_number}", null != sales.getDocumentTrackingNo() ? StringUtils.checkNullValueString(sales.getDocumentTrackingNo().getDefinition()) : "");
        _header = replace9;
        Objects.requireNonNull(rkw);
        final String replace10 = replace9.replace("{customer_order_number}", null != sales.getCustomerOrderNo() ? StringUtils.checkNullValueString(sales.getCustomerOrderNo().getDefinition()) : "");
        _header = replace10;
        Objects.requireNonNull(rkw);
        final String replace11 = replace10.replace("{shipment_account}", null != sales.getShipAccount() ? StringUtils.checkNullValueString(sales.getShipAccount().getDefinition()) : "");
        _header = replace11;
        Objects.requireNonNull(rkw);
        final String replace12 = replace11.replace("{shipment_address}", null != sales.getShipAddress() ? StringUtils.checkNullValueString(sales.getShipAddress().getDefinition()) : "");
        _header = replace12;
        Objects.requireNonNull(rkw);
        final String replace13 = replace12.replace("{invoice_address}", StringUtils.checkNullValueString(_customer.getInfoNote()));
        _header = replace13;
        Objects.requireNonNull(rkw);
        if (null != sales.getShipAgent()) {
            str = StringUtils.checkNullValueString(sales.getShipAgent().getDefinition());
            charSequence = "{invoice_address}";
        } else {
            charSequence = "{invoice_address}";
            str = "";
        }
        final String replace14 = replace13.replace("{carrier_company}", str);
        _header = replace14;
        Objects.requireNonNull(rkw);
        if (null != sales.getShipType()) {
            str2 = StringUtils.checkNullValueString(sales.getShipType().getDefinition());
            charSequence2 = "{carrier_company}";
        } else {
            charSequence2 = "{carrier_company}";
            str2 = "";
        }
        final String replace15 = replace14.replace("{delivery_type}", str2);
        _header = replace15;
        Objects.requireNonNull(rkw);
        if (null != sales.getTradeGroup()) {
            str3 = StringUtils.checkNullValueString(sales.getTradeGroup().getDefinition());
            charSequence3 = "{delivery_type}";
        } else {
            charSequence3 = "{delivery_type}";
            str3 = "";
        }
        final String replace16 = replace15.replace("{trading_group}", str3);
        _header = replace16;
        Objects.requireNonNull(rkw);
        if (null != sales.getProjectCode()) {
            str4 = StringUtils.checkNullValueString(sales.getProjectCode().getDefinition());
            charSequence4 = "{trading_group}";
        } else {
            charSequence4 = "{trading_group}";
            str4 = "";
        }
        final String replace17 = replace16.replace("{project_code}", str4);
        _header = replace17;
        Objects.requireNonNull(rkw);
        if (null != sales.getPayPlan()) {
            str5 = StringUtils.checkNullValueString(sales.getPayPlan().getDefinition());
            charSequence5 = "{project_code}";
        } else {
            charSequence5 = "{project_code}";
            str5 = "";
        }
        final String replace18 = replace17.replace("{payment_plan}", str5);
        _header = replace18;
        Objects.requireNonNull(rkw);
        if (null != sales.getPayPlan()) {
            str6 = StringUtils.checkNullValueString(sales.getPayPlan().getDefinition());
            charSequence6 = "{payment_plan}";
        } else {
            charSequence6 = "{payment_plan}";
            str6 = "";
        }
        final String replace19 = replace18.replace("{payment_method}", str6);
        _header = replace19;
        Objects.requireNonNull(rkw);
        CharSequence charSequence7 = "{payment_method}";
        final String replace20 = replace19.replace("{carrier_plate}", this.getPlateFromLocalData(sales));
        _header = replace20;
        Objects.requireNonNull(rkw);
        final String replace21 = replace20.replace("{order_date}", StringUtils.checkNullValueString(sales.getFicheCreateDate()));
        _header = replace21;
        Objects.requireNonNull(rkw);
        final String replace22 = replace21.replace("{order_number}", StringUtils.checkNullValueString(sales.getFicheNo()));
        _header = replace22;
        Objects.requireNonNull(rkw);
        final String replace23 = replace22.replace("{salesman_code}", StringUtils.checkNullValueString(baseErp.getUser().getCode()));
        _header = replace23;
        Objects.requireNonNull(rkw);
        final String replace24 = replace23.replace("{salesman_fullName}", StringUtils.checkNullValueString(baseErp.getUser().getName()));
        _header = replace24;
        Objects.requireNonNull(rkw);
        final String replace25 = replace24.replace("{current_account_title}", StringUtils.checkNullValueString(_customer.getTitle()));
        _header = replace25;
        Objects.requireNonNull(rkw);
        _header = replace25.replace("{current_account_title_2}", StringUtils.checkNullValueString(_customer.getSecondTitle()));
        final CalculatedFields calculatedFields = _calculatedFields;
        if (null != calculatedFields) {
            Iterator<CalculatedField> it = calculatedFields.getCalculatedFields().iterator();
            while (it.hasNext()) {
                final CalculatedField next = it.next();
                final Iterator<CalculatedField> it2 = it;
                if ("Header".equals(next.getSection())) {
                    final String formula = next.getFormula();
                    Objects.requireNonNull(rkw);
                    final String replace26 = formula.replace(str16, StringUtils.checkNullValueString(_customer.getCity()));
                    Objects.requireNonNull(rkw);
                    final String replace27 = replace26.replace(str17, StringUtils.checkNullValueString(_customer.getTown()));
                    Objects.requireNonNull(rkw);
                    final String replace28 = replace27.replace("{customer_code}", StringUtils.checkNullValueString(_customer.getCode()));
                    Objects.requireNonNull(rkw);
                    final String replace29 = replace28.replace("{customer_title}", StringUtils.checkNullValueString(_customer.getTitle()));
                    Objects.requireNonNull(rkw);
                    final String replace30 = replace29.replace("{warehouse}", null != sales.getWareHouse() ? StringUtils.checkNullValueString(sales.getWareHouse().getDefinition()) : "");
                    Objects.requireNonNull(rkw);
                    final String replace31 = replace30.replace("{special_code}", null != sales.getSpeCode() ? StringUtils.checkNullValueString(sales.getSpeCode().getDefinition()) : "");
                    Objects.requireNonNull(rkw);
                    final String replace32 = replace31.replace("{authorization_code}", null != sales.getWarrantyCode() ? StringUtils.checkNullValueString(sales.getWarrantyCode().getDefinition()) : "");
                    Objects.requireNonNull(rkw);
                    final String replace33 = replace32.replace("{document_number}", null != sales.getDocumentNo() ? StringUtils.checkNullValueString(sales.getDocumentNo().getDefinition()) : "");
                    Objects.requireNonNull(rkw);
                    final String replace34 = replace33.replace("{document_tracking_number}", null != sales.getDocumentTrackingNo() ? StringUtils.checkNullValueString(sales.getDocumentTrackingNo().getDefinition()) : "");
                    Objects.requireNonNull(rkw);
                    final String replace35 = replace34.replace("{customer_order_number}", null != sales.getCustomerOrderNo() ? StringUtils.checkNullValueString(sales.getCustomerOrderNo().getDefinition()) : "");
                    Objects.requireNonNull(rkw);
                    final String replace36 = replace35.replace("{shipment_account}", null != sales.getShipAccount() ? StringUtils.checkNullValueString(sales.getShipAccount().getDefinition()) : "");
                    Objects.requireNonNull(rkw);
                    final String replace37 = replace36.replace("{shipment_address}", null != sales.getShipAddress() ? StringUtils.checkNullValueString(sales.getShipAddress().getDefinition()) : "");
                    Objects.requireNonNull(rkw);
                    str7 = str16;
                    final CharSequence charSequence8 = charSequence;
                    final String replace38 = replace37.replace(charSequence8, StringUtils.checkNullValueString(_customer.getInfoNote()));
                    Objects.requireNonNull(rkw);
                    if (null != sales.getShipAgent()) {
                        str9 = StringUtils.checkNullValueString(sales.getShipAgent().getDefinition());
                        charSequence = charSequence8;
                    } else {
                        charSequence = charSequence8;
                        str9 = "";
                    }
                    final CharSequence charSequence9 = charSequence2;
                    final String replace39 = replace38.replace(charSequence9, str9);
                    Objects.requireNonNull(rkw);
                    if (null != sales.getShipType()) {
                        str10 = StringUtils.checkNullValueString(sales.getShipType().getDefinition());
                        charSequence2 = charSequence9;
                    } else {
                        charSequence2 = charSequence9;
                        str10 = "";
                    }
                    final CharSequence charSequence10 = charSequence3;
                    final String replace40 = replace39.replace(charSequence10, str10);
                    Objects.requireNonNull(rkw);
                    if (null != sales.getTradeGroup()) {
                        str11 = StringUtils.checkNullValueString(sales.getTradeGroup().getDefinition());
                        charSequence3 = charSequence10;
                    } else {
                        charSequence3 = charSequence10;
                        str11 = "";
                    }
                    final CharSequence charSequence11 = charSequence4;
                    final String replace41 = replace40.replace(charSequence11, str11);
                    Objects.requireNonNull(rkw);
                    if (null != sales.getProjectCode()) {
                        str12 = StringUtils.checkNullValueString(sales.getProjectCode().getDefinition());
                        charSequence4 = charSequence11;
                    } else {
                        charSequence4 = charSequence11;
                        str12 = "";
                    }
                    final CharSequence charSequence12 = charSequence5;
                    final String replace42 = replace41.replace(charSequence12, str12);
                    Objects.requireNonNull(rkw);
                    if (null != sales.getPayPlan()) {
                        str13 = StringUtils.checkNullValueString(sales.getPayPlan().getDefinition());
                        charSequence5 = charSequence12;
                    } else {
                        charSequence5 = charSequence12;
                        str13 = "";
                    }
                    final CharSequence charSequence13 = charSequence6;
                    final String replace43 = replace42.replace(charSequence13, str13);
                    Objects.requireNonNull(rkw);
                    if (null != sales.getPayPlan()) {
                        str14 = StringUtils.checkNullValueString(sales.getPayPlan().getDefinition());
                        charSequence6 = charSequence13;
                    } else {
                        charSequence6 = charSequence13;
                        str14 = "";
                    }
                    final CharSequence charSequence14 = charSequence7;
                    final String replace44 = replace43.replace(charSequence14, str14);
                    Objects.requireNonNull(rkw);
                    charSequence7 = charSequence14;
                    final String replace45 = replace44.replace("{carrier_plate}", this.getPlateFromLocalData(sales));
                    Objects.requireNonNull(rkw);
                    final String replace46 = replace45.replace("{order_date}", StringUtils.checkNullValueString(sales.getFicheCreateDate()));
                    Objects.requireNonNull(rkw);
                    final String replace47 = replace46.replace("{order_number}", StringUtils.checkNullValueString(sales.getFicheNo()));
                    Objects.requireNonNull(rkw);
                    final String replace48 = replace47.replace("{salesman_code}", StringUtils.checkNullValueString(baseErp.getUser().getCode()));
                    Objects.requireNonNull(rkw);
                    final String replace49 = replace48.replace("{salesman_fullName}", StringUtils.checkNullValueString(baseErp.getUser().getName()));
                    Objects.requireNonNull(rkw);
                    final String replace50 = replace49.replace("{current_account_title}", StringUtils.checkNullValueString(_customer.getTitle()));
                    Objects.requireNonNull(rkw);
                    final String calculateFormula = this.calculateFormula(replace50.replace("{current_account_title_2}", StringUtils.checkNullValueString(_customer.getSecondTitle())));
                    final String str18 = _header;
                    final StringBuilder sb = new StringBuilder();
                    str8 = str17;
                    sb.append("{");
                    sb.append(next.getName());
                    sb.append("}");
                    _header = str18.replace(sb.toString(), calculateFormula);
                } else {
                    str7 = str16;
                    str8 = str17;
                }
                it = it2;
                str16 = str7;
                str17 = str8;
            }
        }
    }

    void replaceFicheLine(final Sales sales) {
        String str;
        String str2;
        String str3;
        String str4;
        Iterator<SalesDetail> it;
        String str5;
        CharSequence charSequence;
        String str6;
        String empty;
        CharSequence charSequence2;
        String empty2;
        String str7;
        String str8;
        CharSequence charSequence3;
        CharSequence charSequence4;
        CharSequence charSequence5;
        CharSequence charSequence6;
        CharSequence charSequence7;
        String str9;
        sqlManager = baseErp.getSendCreator().getSqlManager();
        String str10 = _line;
        String str11 = "";
        _line = "";
        Iterator<SalesDetail> it2 = sales.getMSalesDetailList().iterator();
        while (it2.hasNext()) {
            final SalesDetail next = it2.next();
            Objects.requireNonNull(rkw);
            String str12 = "{product_code}";
            final String replace = str10.replace("{product_code}", StringUtils.checkNullValueString(next.getCode()));
            Objects.requireNonNull(rkw);
            String str13 = "{product_explanation}";
            final String replace2 = replace.replace("{product_explanation}", StringUtils.checkNullValueString(next.getName()));
            Objects.requireNonNull(rkw);
            String str14 = "{quantity}";
            final String replace3 = replace2.replace("{quantity}", null != next.getAmount() ? StringUtils.checkNullValueString(next.getAmount().getDefinition()) : str11);
            Objects.requireNonNull(rkw);
            final String replace4 = replace3.replace("{unit}", null != next.getUnit() ? StringUtils.checkNullValueString(next.getUnit().getCode()) : str11);
            Objects.requireNonNull(rkw);
            final String replace5 = replace4.replace("{price}", StringUtils.formatDouble(next.getUsePrice()));
            Objects.requireNonNull(rkw);
            final String replace6 = replace5.replace("{vat}", null != next.getVat() ? StringUtils.checkNullValueString(next.getVat().getDefinition()) : str11);
            Objects.requireNonNull(rkw);
            final String replace7 = replace6.replace("{amount}", StringUtils.formatDouble(next.getProductTotal()));
            Objects.requireNonNull(rkw);
            final String replace8 = replace7.replace("{payments}", null != next.getPayment() ? StringUtils.checkNullValueString(next.getPayment().getDefinition()) : str11);
            Objects.requireNonNull(rkw);
            final String replace9 = replace8.replace("{delivery_date}", null != next.getDeliveryDate() ? StringUtils.checkNullValueString(next.getDeliveryDate().getDefinition()) : str11);
            Objects.requireNonNull(rkw);
            if (null != next.getDeliveryCode()) {
                str2 = StringUtils.checkNullValueString(next.getDeliveryCode().getDefinition());
                str = str10;
            } else {
                str = str10;
                str2 = str11;
            }
            final String replace10 = replace9.replace("{delivery_code}", str2);
            Objects.requireNonNull(rkw);
            if (null != next.getSpeCode()) {
                str3 = StringUtils.checkNullValueString(next.getSpeCode().getDefinition());
                str4 = str11;
            } else {
                str3 = str11;
                str4 = str3;
            }
            final String replace11 = replace10.replace("{special_code}", str3);
            Objects.requireNonNull(rkw);
            if (null != next.getVariant()) {
                str5 = this.getVariantCode(next.getVariant().getLogicalRef());
                it = it2;
            } else {
                it = it2;
                str5 = str4;
            }
            final String replace12 = replace11.replace("{variant_code}", str5);
            Objects.requireNonNull(rkw);
            if (null != next.getVariant()) {
                str6 = StringUtils.checkNullValueString(next.getVariant().getDefinition());
                charSequence = "{variant_code}";
            } else {
                charSequence = "{variant_code}";
                str6 = str4;
            }
            final String replace13 = replace12.replace("{variant_explanation}", str6);
            Objects.requireNonNull(rkw);
            CharSequence charSequence8 = "{variant_explanation}";
            final String replace14 = replace13.replace("{line_discount1}", this.getDiscount(next.getSalesFicheDiscountProps(), 0));
            Objects.requireNonNull(rkw);
            final String replace15 = replace14.replace("{line_discount2}", this.getDiscount(next.getSalesFicheDiscountProps(), 1));
            Objects.requireNonNull(rkw);
            final String replace16 = replace15.replace("{line_discount3}", this.getDiscount(next.getSalesFicheDiscountProps(), 2));
            Objects.requireNonNull(rkw);
            final String replace17 = replace16.replace("{line_discount4}", this.getDiscount(next.getSalesFicheDiscountProps(), 3));
            Objects.requireNonNull(rkw);
            final String replace18 = replace17.replace("{line_discount5}", this.getDiscount(next.getSalesFicheDiscountProps(), 4));
            Objects.requireNonNull(rkw);
            final String replace19 = replace18.replace("{total_line_discount}", StringUtils.formatDouble(next.getProductDiscountTotal()));
            Objects.requireNonNull(rkw);
            final String replace20 = replace19.replace("{currency_type}", StringUtils.checkNullValueString(next.getCurCodeStr()));
            Objects.requireNonNull(rkw);
            CharSequence charSequence9 = "{special_code}";
            CharSequence charSequence10 = "{delivery_code}";
            final String replace21 = replace20.replace("{width}", null != this.sqlManager.getItemUnit(sales.getFicheNo(), next.getLineNumber(), sales.getmSalesType()).width ? sqlManager.getItemUnit(sales.getFicheNo(), next.getLineNumber(), sales.getmSalesType()).width : StringUtils.empty());
            Objects.requireNonNull(rkw);
            final String replace22 = replace21.replace("{lenght}", null != this.sqlManager.getItemUnit(sales.getFicheNo(), next.getLineNumber(), sales.getmSalesType()).length ? sqlManager.getItemUnit(sales.getFicheNo(), next.getLineNumber(), sales.getmSalesType()).length : StringUtils.empty());
            Objects.requireNonNull(rkw);
            final String replace23 = replace22.replace("{height}", null != this.sqlManager.getItemUnit(sales.getFicheNo(), next.getLineNumber(), sales.getmSalesType()).height ? sqlManager.getItemUnit(sales.getFicheNo(), next.getLineNumber(), sales.getmSalesType()).height : StringUtils.empty());
            final boolean equals = baseErp.getLocalCurrencyCode().equals(next.curCodeStr);
            if (equals) {
                empty = StringUtils.empty();
            } else {
                empty = StringUtils.formatDoubleNormal(next.getProductLineNet() / (0.0d == next.getPrRate() ? 1.0d : next.getPrRate()));
            }
            Objects.requireNonNull(rkw);
            if (equals || next.curCodeStr.isEmpty()) {
                charSequence2 = "{delivery_date}";
                empty2 = StringUtils.empty();
            } else {
                charSequence2 = "{delivery_date}";
                empty2 = String.format("%s / %s", Double.valueOf(next.getUsePrice()), next.curCodeStr);
            }
            final String replace24 = replace23.replace("{unit_price_in_foreign_currency}", empty2);
            Objects.requireNonNull(rkw);
            final String replace25 = replace24.replace("{amount_in_foreign_currency}", (equals || next.curCodeStr.isEmpty()) ? StringUtils.empty() : String.format("%s / %s", empty, next.curCodeStr));
            Objects.requireNonNull(rkw);
            final String replace26 = replace25.replace("{special_code_1}", baseErp.getCustomerInfo(sales.getClRef()).getSpecode());
            Objects.requireNonNull(rkw);
            final String replace27 = replace26.replace("{special_code_2}", baseErp.getCustomerInfo(sales.getClRef()).getSpecode2());
            Objects.requireNonNull(rkw);
            String replace28 = replace27.replace("{special_code_3}", baseErp.getCustomerInfo(sales.getClRef()).getSpecode3());
            final CalculatedFields calculatedFields = _calculatedFields;
            if (null != calculatedFields) {
                Iterator<CalculatedField> it3 = calculatedFields.getCalculatedFields().iterator();
                while (it3.hasNext()) {
                    final CalculatedField next2 = it3.next();
                    final Iterator<CalculatedField> it4 = it3;
                    final String str15 = replace28;
                    if ("Main".equals(next2.getSection())) {
                        final String formula = next2.getFormula();
                        Objects.requireNonNull(rkw);
                        final String replace29 = formula.replace(str12, StringUtils.checkNullValueString(next.getCode()));
                        Objects.requireNonNull(rkw);
                        final String replace30 = replace29.replace(str13, StringUtils.checkNullValueString(next.getName()));
                        Objects.requireNonNull(rkw);
                        final String replace31 = replace30.replace(str14, null != next.getAmount() ? StringUtils.checkNullValueString(next.getAmount().getDefinition()) : str4);
                        Objects.requireNonNull(rkw);
                        final String replace32 = replace31.replace("{unit}", null != next.getUnit() ? StringUtils.checkNullValueString(next.getUnit().getCode()) : str4);
                        Objects.requireNonNull(rkw);
                        final String replace33 = replace32.replace("{price}", String.valueOf(next.getUsePrice()));
                        Objects.requireNonNull(rkw);
                        final String replace34 = replace33.replace("{vat}", null != next.getVat() ? StringUtils.checkNullValueString(next.getVat().getDefinition()) : str4);
                        Objects.requireNonNull(rkw);
                        final String replace35 = replace34.replace("{amount}", String.valueOf(next.getProductTotal()));
                        Objects.requireNonNull(rkw);
                        final String replace36 = replace35.replace("{payments}", null != next.getPayment() ? StringUtils.checkNullValueString(next.getPayment().getDefinition()) : str4);
                        Objects.requireNonNull(rkw);
                        final CharSequence charSequence11 = charSequence2;
                        str9 = str12;
                        final String replace37 = replace36.replace(charSequence11, null != next.getDeliveryDate() ? StringUtils.checkNullValueString(next.getDeliveryDate().getDefinition()) : str4);
                        Objects.requireNonNull(rkw);
                        final CharSequence charSequence12 = charSequence10;
                        charSequence7 = charSequence11;
                        final String replace38 = replace37.replace(charSequence12, null != next.getDeliveryCode() ? StringUtils.checkNullValueString(next.getDeliveryCode().getDefinition()) : str4);
                        Objects.requireNonNull(rkw);
                        final CharSequence charSequence13 = charSequence9;
                        charSequence6 = charSequence12;
                        final String replace39 = replace38.replace(charSequence13, null != next.getSpeCode() ? StringUtils.checkNullValueString(next.getSpeCode().getDefinition()) : str4);
                        Objects.requireNonNull(rkw);
                        final CharSequence charSequence14 = charSequence;
                        charSequence5 = charSequence13;
                        final String replace40 = replace39.replace(charSequence14, null != next.getVariant() ? this.getVariantCode(next.getVariant().getLogicalRef()) : str4);
                        Objects.requireNonNull(rkw);
                        final CharSequence charSequence15 = charSequence8;
                        charSequence4 = charSequence14;
                        final String replace41 = replace40.replace(charSequence15, null != next.getVariant() ? StringUtils.checkNullValueString(next.getVariant().getDefinition()) : str4);
                        Objects.requireNonNull(rkw);
                        charSequence3 = charSequence15;
                        final String replace42 = replace41.replace("{line_discount1}", this.getDiscount(next.getSalesFicheDiscountProps(), 0));
                        Objects.requireNonNull(rkw);
                        final String replace43 = replace42.replace("{line_discount2}", this.getDiscount(next.getSalesFicheDiscountProps(), 1));
                        Objects.requireNonNull(rkw);
                        final String replace44 = replace43.replace("{line_discount3}", this.getDiscount(next.getSalesFicheDiscountProps(), 2));
                        Objects.requireNonNull(rkw);
                        final String replace45 = replace44.replace("{line_discount4}", this.getDiscount(next.getSalesFicheDiscountProps(), 3));
                        Objects.requireNonNull(rkw);
                        final String replace46 = replace45.replace("{line_discount5}", this.getDiscount(next.getSalesFicheDiscountProps(), 4));
                        Objects.requireNonNull(rkw);
                        final String replace47 = replace46.replace("{total_line_discount}", String.valueOf(next.getProductDiscountTotal()));
                        Objects.requireNonNull(rkw);
                        final String replace48 = replace47.replace("{currency_type}", StringUtils.checkNullValueString(next.getCurCodeStr()));
                        Objects.requireNonNull(rkw);
                        str7 = str13;
                        str8 = str14;
                        final String replace49 = replace48.replace("{width}", null != this.sqlManager.getItemUnit(sales.getFicheNo(), next.getLineNumber(), sales.getmSalesType()).width ? sqlManager.getItemUnit(sales.getFicheNo(), next.getLineNumber(), sales.getmSalesType()).width : StringUtils.empty());
                        Objects.requireNonNull(rkw);
                        final String replace50 = replace49.replace("{lenght}", null != this.sqlManager.getItemUnit(sales.getFicheNo(), next.getLineNumber(), sales.getmSalesType()).length ? sqlManager.getItemUnit(sales.getFicheNo(), next.getLineNumber(), sales.getmSalesType()).length : StringUtils.empty());
                        Objects.requireNonNull(rkw);
                        final String replace51 = replace50.replace("{height}", null != this.sqlManager.getItemUnit(sales.getFicheNo(), next.getLineNumber(), sales.getmSalesType()).height ? sqlManager.getItemUnit(sales.getFicheNo(), next.getLineNumber(), sales.getmSalesType()).height : StringUtils.empty());
                        Objects.requireNonNull(rkw);
                        final String replace52 = replace51.replace("{unit_price_in_foreign_currency}", (equals || next.curCodeStr.isEmpty()) ? StringUtils.empty() : String.format("%s / %s", Double.valueOf(next.getUsePrice()), next.curCodeStr));
                        Objects.requireNonNull(rkw);
                        final String replace53 = replace52.replace("{amount_in_foreign_currency}", (equals || next.curCodeStr.isEmpty()) ? StringUtils.empty() : String.format("%s / %s", empty, next.curCodeStr));
                        Objects.requireNonNull(rkw);
                        final String replace54 = replace53.replace("{special_code_1}", baseErp.getCustomerInfo(sales.getClRef()).getSpecode());
                        Objects.requireNonNull(rkw);
                        final String replace55 = replace54.replace("{special_code_2}", baseErp.getCustomerInfo(sales.getClRef()).getSpecode2());
                        Objects.requireNonNull(rkw);
                        replace28 = str15.replace("{" + next2.getName() + "}", this.calculateFormula(replace55.replace("{special_code_3}", baseErp.getCustomerInfo(sales.getClRef()).getSpecode3())));
                    } else {
                        str7 = str13;
                        str8 = str14;
                        charSequence3 = charSequence8;
                        charSequence4 = charSequence;
                        charSequence5 = charSequence9;
                        charSequence6 = charSequence10;
                        charSequence7 = charSequence2;
                        str9 = str12;
                        replace28 = str15;
                    }
                    str12 = str9;
                    it3 = it4;
                    str13 = str7;
                    str14 = str8;
                    charSequence2 = charSequence7;
                    charSequence10 = charSequence6;
                    charSequence9 = charSequence5;
                    charSequence = charSequence4;
                    charSequence8 = charSequence3;
                }
            }
            _line += replace28;
            str10 = str;
            str11 = str4;
            it2 = it;
        }
    }

    void replaceFicheFooter(final Sales sales) {
        String str;
        String str2;
        CharSequence charSequence;
        Iterator<CalculatedField> it;
        this.calculateWeightAndVolume(sales);
        final String str3 = _footer;
        Objects.requireNonNull(rkw);
        String str4 = "{general_discount1}";
        final String replace = str3.replace("{general_discount1}", this.getDiscount(sales.getSalesFicheDiscountProps(), 0));
        _footer = replace;
        Objects.requireNonNull(rkw);
        String str5 = "{general_discount2}";
        final String replace2 = replace.replace("{general_discount2}", this.getDiscount(sales.getSalesFicheDiscountProps(), 1));
        _footer = replace2;
        Objects.requireNonNull(rkw);
        final String replace3 = replace2.replace("{general_discount3}", this.getDiscount(sales.getSalesFicheDiscountProps(), 2));
        _footer = replace3;
        Objects.requireNonNull(rkw);
        final String replace4 = replace3.replace("{general_discount4}", this.getDiscount(sales.getSalesFicheDiscountProps(), 3));
        _footer = replace4;
        Objects.requireNonNull(rkw);
        final String replace5 = replace4.replace("{general_discount5}", this.getDiscount(sales.getSalesFicheDiscountProps(), 4));
        _footer = replace5;
        Objects.requireNonNull(rkw);
        final String replace6 = replace5.replace("{general_explanation}", StringUtils.checkNullValueString(sales.getExplanation1().getDefinition()));
        _footer = replace6;
        Objects.requireNonNull(rkw);
        final String replace7 = replace6.replace("{general_explanation1}", StringUtils.checkNullValueString(sales.getExplanation2().getDefinition()));
        _footer = replace7;
        Objects.requireNonNull(rkw);
        final String replace8 = replace7.replace("{general_explanation2}", StringUtils.checkNullValueString(sales.getExplanation3().getDefinition()));
        _footer = replace8;
        Objects.requireNonNull(rkw);
        final String replace9 = replace8.replace("{general_explanation3}", StringUtils.checkNullValueString(sales.getExplanation4().getDefinition()));
        _footer = replace9;
        Objects.requireNonNull(rkw);
        final String replace10 = replace9.replace("{general_explanation4}", StringUtils.checkNullValueString(sales.getExplanation5().getDefinition()));
        _footer = replace10;
        Objects.requireNonNull(rkw);
        final String replace11 = replace10.replace("{general_explanation5}", StringUtils.checkNullValueString(sales.getExplanation6().getDefinition()));
        _footer = replace11;
        Objects.requireNonNull(rkw);
        final String replace12 = replace11.replace("{general_explanation6}", StringUtils.checkNullValueString(sales.getExplanation7().getDefinition()));
        _footer = replace12;
        Objects.requireNonNull(rkw);
        final String replace13 = replace12.replace("{general_explanation7}", StringUtils.checkNullValueString(sales.getExplanation8().getDefinition()));
        _footer = replace13;
        Objects.requireNonNull(rkw);
        final String replace14 = replace13.replace("{general_explanation8}", StringUtils.checkNullValueString(sales.getExplanation9().getDefinition()));
        _footer = replace14;
        Objects.requireNonNull(rkw);
        final String replace15 = replace14.replace("{general_explanation9}", StringUtils.checkNullValueString(sales.getExplanation10().getDefinition()));
        _footer = replace15;
        Objects.requireNonNull(rkw);
        final String replace16 = replace15.replace("{total_net_volume}", StringUtils.formatDouble(sales.getTotalNetVolume()));
        _footer = replace16;
        Objects.requireNonNull(rkw);
        final String replace17 = replace16.replace("{total_gross_volume}", StringUtils.formatDouble(sales.getTotalGrossVolume()));
        _footer = replace17;
        Objects.requireNonNull(rkw);
        final String replace18 = replace17.replace("{total_net_weight}", StringUtils.formatDouble(sales.getTotalNetWeight()));
        _footer = replace18;
        Objects.requireNonNull(rkw);
        final String replace19 = replace18.replace("{total_gross_weight}", StringUtils.formatDouble(sales.getTotalGrossWeight()));
        _footer = replace19;
        Objects.requireNonNull(rkw);
        final String replace20 = replace19.replace("{total_gross}", StringUtils.formatDouble(sales.getGrossTotal()));
        _footer = replace20;
        Objects.requireNonNull(rkw);
        final String replace21 = replace20.replace("{total_discount}", StringUtils.formatDouble(sales.getDiscTotal()));
        _footer = replace21;
        Objects.requireNonNull(rkw);
        final String replace22 = replace21.replace("{total_vat}", StringUtils.formatDouble(sales.getTotalVat()));
        _footer = replace22;
        Objects.requireNonNull(rkw);
        final String replace23 = replace22.replace("{total_net}", StringUtils.formatDouble(sales.getTotalNet()));
        _footer = replace23;
        Objects.requireNonNull(rkw);
        CharSequence charSequence2 = "{general_explanation5}";
        final String replace24 = replace23.replace("{total_net_in_words}", StringUtils.convertTotal2Text(StringUtils.dFormat(sales.getTotalNet()), "TL", "KR", baseErp.getLocalCurrencyCode()));
        _footer = replace24;
        Objects.requireNonNull(rkw);
        final String replace25 = replace24.replace("{customer_balance}", StringUtils.formatDouble(_customer.getBalance()));
        _footer = replace25;
        Objects.requireNonNull(rkw);
        final String replace26 = replace25.replace("{customer_balance_before}", _customer.getBeforeBalance());
        _footer = replace26;
        Objects.requireNonNull(rkw);
        final String replace27 = replace26.replace("{client_balance}", StringUtils.formatDouble(_customer.getBalance()));
        _footer = replace27;
        Objects.requireNonNull(rkw);
        final String replace28 = replace27.replace("{salesman_code}", StringUtils.checkNullValueString(baseErp.getUser().getCode()));
        _footer = replace28;
        Objects.requireNonNull(rkw);
        final String replace29 = replace28.replace("{salesman_fullName}", StringUtils.checkNullValueString(baseErp.getUser().getName()));
        _footer = replace29;
        Objects.requireNonNull(rkw);
        final String replace30 = replace29.replace("{special_code_1}", baseErp.getCustomerInfo(sales.getClRef()).getSpecode());
        _footer = replace30;
        Objects.requireNonNull(rkw);
        final String replace31 = replace30.replace("{special_code_2}", baseErp.getCustomerInfo(sales.getClRef()).getSpecode2());
        _footer = replace31;
        Objects.requireNonNull(rkw);
        _footer = replace31.replace("{special_code_3}", baseErp.getCustomerInfo(sales.getClRef()).getSpecode3());
        final CalculatedFields calculatedFields = _calculatedFields;
        if (null != calculatedFields) {
            Iterator<CalculatedField> it2 = calculatedFields.getCalculatedFields().iterator();
            while (it2.hasNext()) {
                final CalculatedField next = it2.next();
                if ("Footer".equals(next.getSection())) {
                    final String formula = next.getFormula();
                    Objects.requireNonNull(rkw);
                    final String replace32 = formula.replace(str4, this.getDiscount(sales.getSalesFicheDiscountProps(), 0));
                    Objects.requireNonNull(rkw);
                    final String replace33 = replace32.replace(str5, this.getDiscount(sales.getSalesFicheDiscountProps(), 1));
                    Objects.requireNonNull(rkw);
                    final String replace34 = replace33.replace("{general_discount3}", this.getDiscount(sales.getSalesFicheDiscountProps(), 2));
                    Objects.requireNonNull(rkw);
                    final String replace35 = replace34.replace("{general_discount4}", this.getDiscount(sales.getSalesFicheDiscountProps(), 3));
                    Objects.requireNonNull(rkw);
                    final String replace36 = replace35.replace("{general_discount5}", this.getDiscount(sales.getSalesFicheDiscountProps(), 4));
                    Objects.requireNonNull(rkw);
                    final String replace37 = replace36.replace("{general_explanation}", StringUtils.checkNullValueString(sales.getExplanation1().getDefinition()));
                    Objects.requireNonNull(rkw);
                    final String replace38 = replace37.replace("{general_explanation1}", StringUtils.checkNullValueString(sales.getExplanation2().getDefinition()));
                    Objects.requireNonNull(rkw);
                    final String replace39 = replace38.replace("{general_explanation2}", StringUtils.checkNullValueString(sales.getExplanation3().getDefinition()));
                    Objects.requireNonNull(rkw);
                    final String replace40 = replace39.replace("{general_explanation3}", StringUtils.checkNullValueString(sales.getExplanation4().getDefinition()));
                    Objects.requireNonNull(rkw);
                    final String replace41 = replace40.replace("{general_explanation4}", StringUtils.checkNullValueString(sales.getExplanation5().getDefinition()));
                    Objects.requireNonNull(rkw);
                    charSequence = charSequence2;
                    final String replace42 = replace41.replace(charSequence, StringUtils.checkNullValueString(sales.getExplanation6().getDefinition()));
                    Objects.requireNonNull(rkw);
                    it = it2;
                    final String replace43 = replace42.replace("{general_explanation6}", StringUtils.checkNullValueString(sales.getExplanation7().getDefinition()));
                    Objects.requireNonNull(rkw);
                    final String replace44 = replace43.replace("{general_explanation7}", StringUtils.checkNullValueString(sales.getExplanation8().getDefinition()));
                    Objects.requireNonNull(rkw);
                    final String replace45 = replace44.replace("{general_explanation8}", StringUtils.checkNullValueString(sales.getExplanation9().getDefinition()));
                    Objects.requireNonNull(rkw);
                    final String replace46 = replace45.replace("{general_explanation9}", StringUtils.checkNullValueString(sales.getExplanation10().getDefinition()));
                    Objects.requireNonNull(rkw);
                    final String replace47 = replace46.replace("{total_net_volume}", String.valueOf(sales.getTotalNetVolume()));
                    Objects.requireNonNull(rkw);
                    final String replace48 = replace47.replace("{total_gross_volume}", String.valueOf(sales.getTotalGrossVolume()));
                    Objects.requireNonNull(rkw);
                    final String replace49 = replace48.replace("{total_net_weight}", String.valueOf(sales.getTotalNetWeight()));
                    Objects.requireNonNull(rkw);
                    final String replace50 = replace49.replace("{total_gross_weight}", String.valueOf(sales.getTotalGrossWeight()));
                    Objects.requireNonNull(rkw);
                    final String replace51 = replace50.replace("{total_gross}", String.valueOf(sales.getGrossTotal()));
                    Objects.requireNonNull(rkw);
                    final String replace52 = replace51.replace("{total_discount}", String.valueOf(sales.getDiscTotal()));
                    Objects.requireNonNull(rkw);
                    final String replace53 = replace52.replace("{total_vat}", String.valueOf(sales.getTotalVat()));
                    Objects.requireNonNull(rkw);
                    final String replace54 = replace53.replace("{total_net}", String.valueOf(sales.getTotalNet()));
                    Objects.requireNonNull(rkw);
                    str = str4;
                    str2 = str5;
                    final String replace55 = replace54.replace("{total_net_in_words}", StringUtils.convertTotal2Text(StringUtils.dFormat(sales.getTotalNet()), "TL", "KR", baseErp.getLocalCurrencyCode()));
                    Objects.requireNonNull(rkw);
                    final String replace56 = replace55.replace("{customer_balance}", String.valueOf(_customer.getBalance()));
                    Objects.requireNonNull(rkw);
                    final String replace57 = replace56.replace("{customer_balance_before}", _customer.getBeforeBalance());
                    Objects.requireNonNull(rkw);
                    final String replace58 = replace57.replace("{client_balance}", StringUtils.formatDouble(_customer.getBalance()));
                    Objects.requireNonNull(rkw);
                    final String replace59 = replace58.replace("{salesman_code}", StringUtils.checkNullValueString(baseErp.getUser().getCode()));
                    Objects.requireNonNull(rkw);
                    final String replace60 = replace59.replace("{salesman_fullName}", StringUtils.checkNullValueString(baseErp.getUser().getName()));
                    Objects.requireNonNull(rkw);
                    final String replace61 = replace60.replace("{special_code_1}", baseErp.getCustomerInfo(sales.getClRef()).getSpecode());
                    Objects.requireNonNull(rkw);
                    final String replace62 = replace61.replace("{special_code_2}", baseErp.getCustomerInfo(sales.getClRef()).getSpecode2());
                    Objects.requireNonNull(rkw);
                    final String calculateFormula = this.calculateFormula(replace62.replace("{special_code_3}", baseErp.getCustomerInfo(sales.getClRef()).getSpecode3()));
                    _footer = _footer.replace("{" + next.getName() + "}", calculateFormula);
                } else {
                    str = str4;
                    str2 = str5;
                    charSequence = charSequence2;
                    it = it2;
                }
                it2 = it;
                str4 = str;
                str5 = str2;
                charSequence2 = charSequence;
            }
        }
    }

    String getDiscount(final SalesFicheDiscountProps salesFicheDiscountProps, final int i2) {
        if (null != salesFicheDiscountProps.getDiscountRatio(i2) && 0.0d < salesFicheDiscountProps.getDiscountRatio(i2).getDefinitionDouble()) {
            if ("" == StringUtils.checkNullValueString(salesFicheDiscountProps.getDiscountRatio(i2).getDefinition())) {
                return "";
            }
            return StringUtils.checkNullValueString(salesFicheDiscountProps.getDiscountRatio(i2).getDefinition() + " (" + ContextUtils.getStringResource(R.string.str_ratio) + ")");
        }
        if (null == salesFicheDiscountProps.getDiscountTotal(i2) || 0.0d >= salesFicheDiscountProps.getDiscountTotal(i2).getDefinitionDouble()) {
            return (null == salesFicheDiscountProps.getDiscountCard(i2) || -1 == salesFicheDiscountProps.getDiscountCard(i2).getLogicalRef()) ? "" : StringUtils.checkNullValueString(salesFicheDiscountProps.getDiscountCard(i2).getCode());
        }
        if ("" == StringUtils.checkNullValueString(salesFicheDiscountProps.getDiscountTotal(i2).getDefinition())) {
            return "";
        }
        return StringUtils.checkNullValueString(salesFicheDiscountProps.getDiscountTotal(i2).getDefinition() + " (" + ContextUtils.getStringResource(R.string.str_quantity) + ")");
    }

    private boolean hasUnit(final SalesDetail salesDetail) {
        return (baseErp.getErpType() == ErpType.TIGER && 0 != salesDetail.getUnit().getLogicalRef()) || (baseErp.getErpType() == ErpType.NETSIS && !TextUtils.isEmpty(salesDetail.getUnit().getCode()));
    }

    private void calculateWeightAndVolume(final Sales sales) {
        ArrayList<ItemUnit> productUnits;
        final Iterator<SalesDetail> it = sales.getMSalesDetailList().iterator();
        while (it.hasNext()) {
            final SalesDetail next = it.next();
            if (null != next && this.hasUnit(next) && null != (productUnits = this.baseErp.getLogoSqlHelper().getProductUnits(next.getItemRef(), !next.isProduct())) && 0 < productUnits.size()) {
                final Iterator<ItemUnit> it2 = productUnits.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        final ItemUnit next2 = it2.next();
                        if (next2.logicalRef == next.getUnit().getLogicalRef()) {
                            next.setNetVolume(next2.netVolume);
                            next.setGrossVolume(next2.grossVolume);
                            next.setNetWeight(next2.netWeight);
                            next.setGrossWeight(next2.grossWeight);
                            break;
                        }
                    }
                }
            }
        }
        sales.calculateVolumeAndWeightInfo(baseErp.getSalesFicheUserRight(sales.getmSalesType()).isLineIntegration());
    }

    private void replaceCashCreditLine(final CashCreditX cashCreditX) {
        String str;
        CharSequence charSequence;
        String str2;
        String str3 = _line;
        _line = "";
        if (TextUtils.isEmpty(_customer.getBeforeBalance())) {
            final Customer customer = _customer;
            customer.setBeforeBalance(StringUtils.formatDouble(Math.abs(customer.getBalance() + cashCreditX.getCashCredit().total)));
        }
        Iterator<CashCreditDetail> it = cashCreditX.getCashCreditDetails().iterator();
        while (it.hasNext()) {
            final CashCreditDetail next = it.next();
            Objects.requireNonNull(rkw);
            String str4 = "{customer_code}";
            final String replace = str3.replace("{customer_code}", StringUtils.checkNullValueString(_customer.getCode()));
            Objects.requireNonNull(rkw);
            final String replace2 = replace.replace("{customer_title}", StringUtils.checkNullValueString(_customer.getTitle()));
            Objects.requireNonNull(rkw);
            final String replace3 = replace2.replace("{document_number}", StringUtils.checkNullValueString(next.docNo));
            Objects.requireNonNull(rkw);
            final String replace4 = replace3.replace("{document_date}", cashCreditX.getCashCredit().getgDate());
            Objects.requireNonNull(rkw);
            final String replace5 = replace4.replace("{special_code}", StringUtils.checkNullValueString(cashCreditX.getCashCredit().specode));
            Objects.requireNonNull(rkw);
            final String replace6 = replace5.replace("{authorization_code}", StringUtils.checkNullValueString(cashCreditX.getCashCredit().cyphcode));
            Objects.requireNonNull(rkw);
            final String replace7 = replace6.replace("{trading_group}", StringUtils.checkNullValueString(cashCreditX.getCashCredit().tradinggrp));
            Objects.requireNonNull(rkw);
            final String replace8 = replace7.replace("{project_code}", this.getProjectDef(cashCreditX.getCashCredit().projectRef));
            Objects.requireNonNull(rkw);
            final String replace9 = replace8.replace("{repayment_schedule}", this.getPaymentDef(next.paymentRef));
            Objects.requireNonNull(rkw);
            final String replace10 = replace9.replace("{bank_name}", this.getBankDef(cashCreditX.getCashCredit().bankRef));
            Objects.requireNonNull(rkw);
            final String str5 = str3;
            final String replace11 = replace10.replace("{bank_account_number}", this.getBankAccDef(cashCreditX.getCashCredit().bankAccRef));
            Objects.requireNonNull(rkw);
            final Iterator<CashCreditDetail> it2 = it;
            String str6 = "{amount}";
            final String replace12 = replace11.replace("{amount}", StringUtils.formatDouble(next.total));
            Objects.requireNonNull(rkw);
            final String replace13 = replace12.replace("{customer_balance}", StringUtils.formatDouble(_customer.getBalance()));
            Objects.requireNonNull(rkw);
            CharSequence charSequence2 = "{customer_balance}";
            final String replace14 = replace13.replace("{customer_balance_before}", _customer.getBeforeBalance());
            Objects.requireNonNull(rkw);
            final String replace15 = replace14.replace("{explanation1}", StringUtils.checkNullValueString(cashCreditX.getCashCredit().desc1));
            Objects.requireNonNull(rkw);
            final String replace16 = replace15.replace("{explanation2}", StringUtils.checkNullValueString(cashCreditX.getCashCredit().desc2));
            Objects.requireNonNull(rkw);
            final String replace17 = replace16.replace("{explanation3}", StringUtils.checkNullValueString(cashCreditX.getCashCredit().desc3));
            Objects.requireNonNull(rkw);
            final String replace18 = replace17.replace("{explanation4}", StringUtils.checkNullValueString(cashCreditX.getCashCredit().desc4));
            Objects.requireNonNull(rkw);
            String replace19 = replace18.replace("{currency_type}", baseErp.getLocalCurrencyCode());
            final CalculatedFields calculatedFields = _calculatedFields;
            if (null != calculatedFields) {
                Iterator<CalculatedField> it3 = calculatedFields.getCalculatedFields().iterator();
                while (it3.hasNext()) {
                    final CalculatedField next2 = it3.next();
                    final Iterator<CalculatedField> it4 = it3;
                    final String str7 = replace19;
                    if ("Main".equals(next2.getSection())) {
                        final String formula = next2.getFormula();
                        Objects.requireNonNull(rkw);
                        final String replace20 = formula.replace(str4, StringUtils.checkNullValueString(_customer.getCode()));
                        Objects.requireNonNull(rkw);
                        final String replace21 = replace20.replace("{customer_title}", StringUtils.checkNullValueString(_customer.getTitle()));
                        Objects.requireNonNull(rkw);
                        final String replace22 = replace21.replace("{document_number}", StringUtils.checkNullValueString(next.docNo));
                        Objects.requireNonNull(rkw);
                        final String replace23 = replace22.replace("{document_date}", cashCreditX.getCashCredit().getgDate());
                        Objects.requireNonNull(rkw);
                        final String replace24 = replace23.replace("{special_code}", StringUtils.checkNullValueString(cashCreditX.getCashCredit().specode));
                        Objects.requireNonNull(rkw);
                        final String replace25 = replace24.replace("{authorization_code}", StringUtils.checkNullValueString(cashCreditX.getCashCredit().cyphcode));
                        Objects.requireNonNull(rkw);
                        final String replace26 = replace25.replace("{trading_group}", StringUtils.checkNullValueString(cashCreditX.getCashCredit().tradinggrp));
                        Objects.requireNonNull(rkw);
                        final String replace27 = replace26.replace("{project_code}", this.getProjectDef(cashCreditX.getCashCredit().projectRef));
                        Objects.requireNonNull(rkw);
                        final String replace28 = replace27.replace("{repayment_schedule}", this.getPaymentDef(next.paymentRef));
                        Objects.requireNonNull(rkw);
                        final String replace29 = replace28.replace("{bank_name}", this.getBankDef(cashCreditX.getCashCredit().bankRef));
                        Objects.requireNonNull(rkw);
                        str = str4;
                        final String replace30 = replace29.replace("{bank_account_number}", this.getBankAccDef(cashCreditX.getCashCredit().bankAccRef));
                        Objects.requireNonNull(rkw);
                        final String replace31 = replace30.replace(str6, String.valueOf(next.total));
                        Objects.requireNonNull(rkw);
                        charSequence = charSequence2;
                        final String replace32 = replace31.replace(charSequence, String.valueOf(_customer.getBalance()));
                        Objects.requireNonNull(rkw);
                        str2 = str6;
                        final String replace33 = replace32.replace("{customer_balance_before}", _customer.getBeforeBalance());
                        Objects.requireNonNull(rkw);
                        final String replace34 = replace33.replace("{explanation1}", StringUtils.checkNullValueString(cashCreditX.getCashCredit().desc1));
                        Objects.requireNonNull(rkw);
                        final String replace35 = replace34.replace("{explanation2}", StringUtils.checkNullValueString(cashCreditX.getCashCredit().desc2));
                        Objects.requireNonNull(rkw);
                        final String replace36 = replace35.replace("{explanation3}", StringUtils.checkNullValueString(cashCreditX.getCashCredit().desc3));
                        Objects.requireNonNull(rkw);
                        final String replace37 = replace36.replace("{explanation4}", StringUtils.checkNullValueString(cashCreditX.getCashCredit().desc4));
                        Objects.requireNonNull(rkw);
                        replace19 = str7.replace("{" + next2.getName() + "}", this.calculateFormula(replace37.replace("{currency_type}", baseErp.getLocalCurrencyCode())));
                    } else {
                        str = str4;
                        charSequence = charSequence2;
                        str2 = str6;
                        replace19 = str7;
                    }
                    str6 = str2;
                    it3 = it4;
                    charSequence2 = charSequence;
                    str4 = str;
                }
            }
            _line += replace19;
            str3 = str5;
            it = it2;
        }
    }

    private void replaceChequeDeedHeader(final ChequeDeed chequeDeed) {
        if (TextUtils.isEmpty(_customer.getBeforeBalance())) {
            final Customer customer = _customer;
            customer.setBeforeBalance(StringUtils.formatDouble(Math.abs(customer.getBalance() + chequeDeed.getChequedeed().total)));
        }
        final String str = _header;
        Objects.requireNonNull(rkw);
        final String replace = str.replace("{customer_code}", StringUtils.checkNullValueString(_customer.getCode()));
        _header = replace;
        Objects.requireNonNull(rkw);
        final String replace2 = replace.replace("{customer_title}", StringUtils.checkNullValueString(_customer.getTitle()));
        _header = replace2;
        Objects.requireNonNull(rkw);
        final String replace3 = replace2.replace("{document_date}", chequeDeed.getChequedeed().date);
        _header = replace3;
        Objects.requireNonNull(rkw);
        final String replace4 = replace3.replace("{document_number}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().docNo));
        _header = replace4;
        Objects.requireNonNull(rkw);
        final String replace5 = replace4.replace("{special_code}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().specode));
        _header = replace5;
        Objects.requireNonNull(rkw);
        final String replace6 = replace5.replace("{authorization_code}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().cyphcode));
        _header = replace6;
        Objects.requireNonNull(rkw);
        final String replace7 = replace6.replace("{trading_group}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().tradinggrp));
        _header = replace7;
        Objects.requireNonNull(rkw);
        final String replace8 = replace7.replace("{project_code}", this.getProjectDef(chequeDeed.getChequedeed().projectRef));
        _header = replace8;
        Objects.requireNonNull(rkw);
        _header = replace8.replace("{check_number}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().ficheNo));
        final CalculatedFields calculatedFields = _calculatedFields;
        if (null != calculatedFields) {
            final Iterator<CalculatedField> it = calculatedFields.getCalculatedFields().iterator();
            while (it.hasNext()) {
                final CalculatedField next = it.next();
                if ("Header".equals(next.getSection())) {
                    final String formula = next.getFormula();
                    Objects.requireNonNull(rkw);
                    final String replace9 = formula.replace("{customer_code}", StringUtils.checkNullValueString(_customer.getCode()));
                    Objects.requireNonNull(rkw);
                    final String replace10 = replace9.replace("{customer_title}", StringUtils.checkNullValueString(_customer.getTitle()));
                    Objects.requireNonNull(rkw);
                    final String replace11 = replace10.replace("{document_date}", chequeDeed.getChequedeed().date);
                    Objects.requireNonNull(rkw);
                    final String replace12 = replace11.replace("{document_number}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().docNo));
                    Objects.requireNonNull(rkw);
                    final String replace13 = replace12.replace("{special_code}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().specode));
                    Objects.requireNonNull(rkw);
                    final String replace14 = replace13.replace("{authorization_code}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().cyphcode));
                    Objects.requireNonNull(rkw);
                    final String replace15 = replace14.replace("{trading_group}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().tradinggrp));
                    Objects.requireNonNull(rkw);
                    final String replace16 = replace15.replace("{project_code}", this.getProjectDef(chequeDeed.getChequedeed().projectRef));
                    Objects.requireNonNull(rkw);
                    final String calculateFormula = this.calculateFormula(replace16.replace("{check_number}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().ficheNo)));
                    _header = _header.replace("{" + next.getName() + "}", calculateFormula);
                }
            }
        }
    }

    private void replaceChequeDeedLine(final ChequeDeed chequeDeed) {
        String str;
        CharSequence charSequence;
        String str2;
        String str3 = _line;
        _line = "";
        Iterator<ChequedeedDetail> it = chequeDeed.getChequedeedDetail().iterator();
        while (it.hasNext()) {
            final ChequedeedDetail next = it.next();
            Objects.requireNonNull(rkw);
            String str4 = "{portfolio_number}";
            final String replace = str3.replace("{portfolio_number}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().number));
            Objects.requireNonNull(rkw);
            final String replace2 = replace.replace("{check_serial_number}", StringUtils.checkNullValueString(next.serialNo));
            Objects.requireNonNull(rkw);
            final String replace3 = replace2.replace("{bill_serial_number}", StringUtils.checkNullValueString(next.serialNo));
            Objects.requireNonNull(rkw);
            final String replace4 = replace3.replace("{bank_name}", StringUtils.checkNullValueString(next.bankName));
            Objects.requireNonNull(rkw);
            final String replace5 = replace4.replace("{bank_branch}", StringUtils.checkNullValueString(next.bankBranchName));
            Objects.requireNonNull(rkw);
            final String replace6 = replace5.replace("{bank_account_number}", StringUtils.checkNullValueString(next.accNo));
            Objects.requireNonNull(rkw);
            final String replace7 = replace6.replace("{check_amount}", StringUtils.formatDouble(next.total));
            Objects.requireNonNull(rkw);
            final String replace8 = replace7.replace("{check_maturity}", next.date);
            Objects.requireNonNull(rkw);
            final String replace9 = replace8.replace("{debitor}", StringUtils.checkNullValueString(next.debited));
            Objects.requireNonNull(rkw);
            final String replace10 = replace9.replace("{garantor}", StringUtils.checkNullValueString(next.inCharge));
            Objects.requireNonNull(rkw);
            final String str5 = str3;
            final String replace11 = replace10.replace("{payment_place}", StringUtils.checkNullValueString(next.payWhere));
            Objects.requireNonNull(rkw);
            CharSequence charSequence2 = "{payment_place}";
            final Iterator<ChequedeedDetail> it2 = it;
            String str6 = "{stamp}";
            final String replace12 = replace11.replace("{stamp}", StringUtils.formatDouble(next.pul));
            Objects.requireNonNull(rkw);
            final String replace13 = replace12.replace("{currency_type}", baseErp.getLocalCurrencyCode());
            Objects.requireNonNull(rkw);
            String replace14 = replace13.replace("{check_number}", StringUtils.checkNullValueString(next.ficheNo));
            final CalculatedFields calculatedFields = _calculatedFields;
            if (null != calculatedFields) {
                Iterator<CalculatedField> it3 = calculatedFields.getCalculatedFields().iterator();
                while (it3.hasNext()) {
                    final CalculatedField next2 = it3.next();
                    final Iterator<CalculatedField> it4 = it3;
                    final String str7 = replace14;
                    if ("Main".equals(next2.getSection())) {
                        final String formula = next2.getFormula();
                        Objects.requireNonNull(rkw);
                        final String replace15 = formula.replace(str4, StringUtils.checkNullValueString(chequeDeed.getChequedeed().number));
                        Objects.requireNonNull(rkw);
                        final String replace16 = replace15.replace("{check_serial_number}", StringUtils.checkNullValueString(next.serialNo));
                        Objects.requireNonNull(rkw);
                        final String replace17 = replace16.replace("{bill_serial_number}", StringUtils.checkNullValueString(next.serialNo));
                        Objects.requireNonNull(rkw);
                        final String replace18 = replace17.replace("{bank_name}", StringUtils.checkNullValueString(next.bankName));
                        Objects.requireNonNull(rkw);
                        final String replace19 = replace18.replace("{bank_branch}", StringUtils.checkNullValueString(next.bankBranchName));
                        Objects.requireNonNull(rkw);
                        final String replace20 = replace19.replace("{bank_account_number}", StringUtils.checkNullValueString(next.accNo));
                        Objects.requireNonNull(rkw);
                        str = str4;
                        final String replace21 = replace20.replace("{check_amount}", String.valueOf(next.total));
                        Objects.requireNonNull(rkw);
                        final String replace22 = replace21.replace("{check_maturity}", next.date);
                        Objects.requireNonNull(rkw);
                        final String replace23 = replace22.replace("{debitor}", StringUtils.checkNullValueString(next.debited));
                        Objects.requireNonNull(rkw);
                        final String replace24 = replace23.replace("{garantor}", StringUtils.checkNullValueString(next.inCharge));
                        Objects.requireNonNull(rkw);
                        charSequence = charSequence2;
                        final String replace25 = replace24.replace(charSequence, StringUtils.checkNullValueString(next.payWhere));
                        Objects.requireNonNull(rkw);
                        final String replace26 = replace25.replace(str6, String.valueOf(next.pul));
                        Objects.requireNonNull(rkw);
                        str2 = str6;
                        final String replace27 = replace26.replace("{currency_type}", baseErp.getLocalCurrencyCode());
                        Objects.requireNonNull(rkw);
                        replace14 = str7.replace("{" + next2.getName() + "}", this.calculateFormula(replace27.replace("{check_number}", StringUtils.checkNullValueString(next.ficheNo))));
                    } else {
                        str = str4;
                        charSequence = charSequence2;
                        str2 = str6;
                        replace14 = str7;
                    }
                    str6 = str2;
                    it3 = it4;
                    charSequence2 = charSequence;
                    str4 = str;
                }
            }
            _line += replace14;
            str3 = str5;
            it = it2;
        }
    }

    private void replaceChequeDeedFooter(final ChequeDeed chequeDeed) {
        final String str = _footer;
        Objects.requireNonNull(rkw);
        final String replace = str.replace("{customer_balance}", StringUtils.formatDouble(_customer.getBalance()));
        _footer = replace;
        Objects.requireNonNull(rkw);
        final String replace2 = replace.replace("{customer_balance_before}", _customer.getBeforeBalance());
        _footer = replace2;
        Objects.requireNonNull(rkw);
        final String replace3 = replace2.replace("{total}", StringUtils.formatDouble(chequeDeed.getChequedeed().total));
        _footer = replace3;
        Objects.requireNonNull(rkw);
        final String replace4 = replace3.replace("{explanation1}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().desc1));
        _footer = replace4;
        Objects.requireNonNull(rkw);
        final String replace5 = replace4.replace("{explanation2}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().desc2));
        _footer = replace5;
        Objects.requireNonNull(rkw);
        final String replace6 = replace5.replace("{explanation3}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().desc3));
        _footer = replace6;
        Objects.requireNonNull(rkw);
        _footer = replace6.replace("{explanation4}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().desc4));
        final CalculatedFields calculatedFields = _calculatedFields;
        if (null != calculatedFields) {
            final Iterator<CalculatedField> it = calculatedFields.getCalculatedFields().iterator();
            while (it.hasNext()) {
                final CalculatedField next = it.next();
                if ("Footer".equals(next.getSection())) {
                    final String formula = next.getFormula();
                    Objects.requireNonNull(rkw);
                    final String replace7 = formula.replace("{customer_balance}", String.valueOf(_customer.getBalance()));
                    Objects.requireNonNull(rkw);
                    final String replace8 = replace7.replace("{customer_balance_before}", _customer.getBeforeBalance());
                    Objects.requireNonNull(rkw);
                    final String replace9 = replace8.replace("{total}", String.valueOf(chequeDeed.getChequedeed().total));
                    Objects.requireNonNull(rkw);
                    final String replace10 = replace9.replace("{explanation1}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().desc1));
                    Objects.requireNonNull(rkw);
                    final String replace11 = replace10.replace("{explanation2}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().desc2));
                    Objects.requireNonNull(rkw);
                    final String replace12 = replace11.replace("{explanation3}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().desc3));
                    Objects.requireNonNull(rkw);
                    final String calculateFormula = this.calculateFormula(replace12.replace("{explanation4}", StringUtils.checkNullValueString(chequeDeed.getChequedeed().desc4)));
                    _footer = _footer.replace("{" + next.getName() + "}", calculateFormula);
                }
            }
        }
    }

    private void replaceCaseCashLine(final CaseCash caseCash) {
        String str;
        String str2;
        CaseCash caseCash2 = caseCash;
        if (TextUtils.isEmpty(_customer.getBeforeBalance())) {
            final Customer customer = _customer;
            customer.setBeforeBalance(StringUtils.formatDouble(Math.abs(customer.getBalance() + caseCash2.total)));
        }
        final String str3 = _line;
        Objects.requireNonNull(rkw);
        String str4 = "{customer_code}";
        final String replace = str3.replace("{customer_code}", StringUtils.checkNullValueString(_customer.getCode()));
        _line = replace;
        Objects.requireNonNull(rkw);
        String str5 = "{customer_title}";
        final String replace2 = replace.replace("{customer_title}", StringUtils.checkNullValueString(_customer.getTitle()));
        _line = replace2;
        Objects.requireNonNull(rkw);
        final String replace3 = replace2.replace("{document_date}", caseCash.getgDate());
        _line = replace3;
        Objects.requireNonNull(rkw);
        final String replace4 = replace3.replace("{document_number}", StringUtils.checkNullValueString(caseCash2.docNo));
        _line = replace4;
        Objects.requireNonNull(rkw);
        final String replace5 = replace4.replace("{special_code}", StringUtils.checkNullValueString(caseCash2.specode));
        _line = replace5;
        Objects.requireNonNull(rkw);
        final String replace6 = replace5.replace("{authorization_code}", StringUtils.checkNullValueString(caseCash2.cyphcode));
        _line = replace6;
        Objects.requireNonNull(rkw);
        final String replace7 = replace6.replace("{trading_group}", StringUtils.checkNullValueString(caseCash2.tradinggrp));
        _line = replace7;
        Objects.requireNonNull(rkw);
        final String replace8 = replace7.replace("{project_code}", this.getProjectDef(caseCash2.projectRef));
        _line = replace8;
        Objects.requireNonNull(rkw);
        final String replace9 = replace8.replace("{amount}", StringUtils.formatDouble(caseCash2.total));
        _line = replace9;
        Objects.requireNonNull(rkw);
        final String replace10 = replace9.replace("{customer_balance}", StringUtils.formatDouble(_customer.getBalance()));
        _line = replace10;
        Objects.requireNonNull(rkw);
        final String replace11 = replace10.replace("{customer_balance_before}", _customer.getBeforeBalance());
        _line = replace11;
        Objects.requireNonNull(rkw);
        final String replace12 = replace11.replace("{explanation}", StringUtils.checkNullValueString(caseCash2.desc));
        _line = replace12;
        Objects.requireNonNull(rkw);
        String str6 = "{currency_type}";
        _line = replace12.replace("{currency_type}", baseErp.getLocalCurrencyCode());
        final CalculatedFields calculatedFields = _calculatedFields;
        if (null != calculatedFields) {
            Iterator<CalculatedField> it = calculatedFields.getCalculatedFields().iterator();
            while (it.hasNext()) {
                final CalculatedField next = it.next();
                final Iterator<CalculatedField> it2 = it;
                final String str7 = str6;
                if ("Main".equals(next.getSection())) {
                    final String formula = next.getFormula();
                    Objects.requireNonNull(rkw);
                    final String replace13 = formula.replace(str4, StringUtils.checkNullValueString(_customer.getCode()));
                    Objects.requireNonNull(rkw);
                    final String replace14 = replace13.replace(str5, StringUtils.checkNullValueString(_customer.getTitle()));
                    Objects.requireNonNull(rkw);
                    final String replace15 = replace14.replace("{document_date}", caseCash.getgDate());
                    Objects.requireNonNull(rkw);
                    final String replace16 = replace15.replace("{document_number}", StringUtils.checkNullValueString(caseCash2.docNo));
                    Objects.requireNonNull(rkw);
                    final String replace17 = replace16.replace("{special_code}", StringUtils.checkNullValueString(caseCash2.specode));
                    Objects.requireNonNull(rkw);
                    final String replace18 = replace17.replace("{authorization_code}", StringUtils.checkNullValueString(caseCash2.cyphcode));
                    Objects.requireNonNull(rkw);
                    final String replace19 = replace18.replace("{trading_group}", StringUtils.checkNullValueString(caseCash2.tradinggrp));
                    Objects.requireNonNull(rkw);
                    final String replace20 = replace19.replace("{project_code}", this.getProjectDef(caseCash2.projectRef));
                    Objects.requireNonNull(rkw);
                    str = str4;
                    str2 = str5;
                    final String replace21 = replace20.replace("{amount}", String.valueOf(caseCash2.total));
                    Objects.requireNonNull(rkw);
                    final String replace22 = replace21.replace("{customer_balance}", StringUtils.formatDouble(_customer.getBalance()));
                    Objects.requireNonNull(rkw);
                    final String replace23 = replace22.replace("{customer_balance_before}", _customer.getBeforeBalance());
                    Objects.requireNonNull(rkw);
                    final String replace24 = replace23.replace("{explanation}", StringUtils.checkNullValueString(caseCash2.desc));
                    Objects.requireNonNull(rkw);
                    final String calculateFormula = this.calculateFormula(replace24.replace(str7, baseErp.getLocalCurrencyCode()));
                    _line = _line.replace("{" + next.getName() + "}", calculateFormula);
                } else {
                    str = str4;
                    str2 = str5;
                }
                caseCash2 = caseCash;
                str4 = str;
                it = it2;
                str6 = str7;
                str5 = str2;
            }
        }
    }

    private String getProjectDef(final String str) {
        final List table;
        if (null == str || "" == str || null == (table = this.baseErp.getLogoSqlHelper().getTable(Project.class, "LOGICALREF=?", new String[]{str})) || 0 >= table.size()) {
            return "";
        }
        return ((Project) table.get(0)).getProje();
    }

    private String getPaymentDef(final int i2) {
        final List table;
        if (-1 >= i2 || null == (table = this.baseErp.getLogoSqlHelper().getTable(Payment.class, "LOGICALREF=?", new String[]{String.valueOf(i2)})) || 0 >= table.size()) {
            return "";
        }
        return ((Payment) table.get(0)).getOdemePlan();
    }

    private String getBankDef(final int i2) {
        final List table;
        if (-1 >= i2 || null == (table = this.baseErp.getLogoSqlHelper().getTable(Bank.class, "LOGICALREF=?", new String[]{String.valueOf(i2)})) || 0 >= table.size()) {
            return "";
        }
        return ((Bank) table.get(0)).getDefinition();
    }

    private String getBankAccDef(final int i2) {
        final List table;
        if (-1 >= i2 || null == (table = this.baseErp.getLogoSqlHelper().getTable(BankAccount.class, "LOGICALREF=?", new String[]{String.valueOf(i2)})) || 0 >= table.size()) {
            return "";
        }
        return ((BankAccount) table.get(0)).getDefinition();
    }

    private String getVariantCode(final int i2) {
        final List table;
        if (-1 >= i2 || null == (table = this.baseErp.getLogoSqlHelper().getTable(Variant.class, "LOGICALREF=?", new String[]{String.valueOf(i2)})) || 0 >= table.size()) {
            return "";
        }
        return ((Variant) table.get(0)).getCode();
    }

    private String calculateFormula(final String str) {
        try {
            return StringUtils.formatDouble(new ExpressionBuilder(str).build().evaluate());
        } catch (final Exception unused) {
            return str;
        }
    }

    EmailObject GetEmailObject() {
        if (TextUtils.isEmpty(_html)) {
            return null;
        }
        final String str = _html;
        String sb = str.substring(str.indexOf("<!DOCTYPE html>"), _html.indexOf("<body>") + 6) +
                _header +
                _line +
                _footer +
                "</body></html>";
        _html = sb;
        final EmailObject emailObject = new EmailObject();
        emailObject.setHtml(_html);
        final ArrayList arrayList = new ArrayList();
        if (null != this._customer.getEmail()) {
            arrayList.addAll(StringUtils.arrayToList(_customer.getEmail().split(";")));
        }
        if (ErpCreator.getInstance().getmBaseErp().sendOtherMail()) {
            arrayList.addAll(StringUtils.arrayToList(ErpCreator.getInstance().getmBaseErp().getOtherMailAddress()));
        }
        if (0 < arrayList.size()) {
            emailObject.setTo((String[]) arrayList.toArray(new String[0]));
        }
        emailObject.setSubject(ContextUtils.getStringResource(_templateType.getmResId()));
        return emailObject;
    }

    public void init(final EmailTemplateType emailTemplateType, final int i2) {
        _templateType = emailTemplateType;
        this.getCustomerInfo(i2, emailTemplateType);
        this.getTemplateHtml(emailTemplateType);
    }

    public EmailObject relaceFicheHTML(final Sales sales, final CustomerBeforeBalance customerBeforeBalance) {
        if (sales.getEmailTemplateType() == EmailTemplateType.Unknown) {
            return null;
        }
        _balances = customerBeforeBalance;
        this.init(sales.getEmailTemplateType(), sales.getClRef());
        this.replaceFicheHeader(sales);
        this.replaceFicheLine(sales);
        this.replaceFicheFooter(sales);
        return this.GetEmailObject();
    }

    public EmailObject replaceCashCreditHTML(final CashCreditX cashCreditX, final EmailTemplateType emailTemplateType, final CustomerBeforeBalance customerBeforeBalance) {
        _balances = customerBeforeBalance;
        this.init(emailTemplateType, cashCreditX.getCashCredit().clRef);
        this.replaceCashCreditLine(cashCreditX);
        return this.GetEmailObject();
    }

    public EmailObject replaceChequeDeedHTML(final ChequeDeed chequeDeed, final EmailTemplateType emailTemplateType, final CustomerBeforeBalance customerBeforeBalance) {
        _balances = customerBeforeBalance;
        this.init(emailTemplateType, chequeDeed.getChequedeed().clRef);
        this.replaceChequeDeedHeader(chequeDeed);
        this.replaceChequeDeedLine(chequeDeed);
        this.replaceChequeDeedFooter(chequeDeed);
        return this.GetEmailObject();
    }

    public EmailObject replaceCaseCashHTML(final CaseCash caseCash, final EmailTemplateType emailTemplateType, final CustomerBeforeBalance customerBeforeBalance) {
        _balances = customerBeforeBalance;
        this.init(emailTemplateType, caseCash.clRef);
        this.replaceCaseCashLine(caseCash);
        return this.GetEmailObject();
    }
}
