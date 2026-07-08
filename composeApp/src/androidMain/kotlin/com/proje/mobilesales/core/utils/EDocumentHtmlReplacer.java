package com.proje.mobilesales.core.utils;

import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.dbmodel.City;
import com.proje.mobilesales.features.model.EDocumentPdfContent;
import com.proje.mobilesales.features.model.EDocumentPdfDetail;
import com.proje.mobilesales.features.model.EDocumentPdfDetailNetsis;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;

public class EDocumentHtmlReplacer {
    private String htmlContainer;
    private final EDocumentPdfContent mPdfModel;
    private final String FIRM_NAME = "{FIRM_NAME}";
    private final String FIRM_ADDRESS = "{FIRM_ADDRESS}";
    private final String FIRM_COUNTY = "{FIRM_COUNTY}";
    private final String FIRM_CITY = "{FIRM_CITY}";
    private final String FIRM_PHONE = "{FIRM_PHONE}";
    private final String FIRM_FAX = "{FIRM_FAX}";
    private final String FIRM_WEBURL = "{FIRM_WEBURL}";
    private final String FIRM_EMAIL = "{FIRM_EMAIL}";
    private final String FIRM_TAXOFFICE = "{FIRM_TAXOFFICE}";
    private final String FIRM_TAXNUMBER = "{FIRM_TAXNUMBER}";
    private final String FIRM_POSTCODE = "{FIRM_POSTCODE}";
    private final String CUSTOMER_NAME = "{CUSTOMER_NAME}";
    private final String CUSTOMER_ADRESS = "{CUSTOMER_ADRESS}";
    private final String CUSTOMER_COUNTY = "{CUSTOMER_COUNTY}";
    private final String CUSTOMER_CITY = "{CUSTOMER_CITY}";
    private final String CUSTOMER_WEBURL = "{CUSTOMER_WEBURL}";
    private final String CUSTOMER_EMAIL = "{CUSTOMER_EMAIL}";
    private final String CUSTOMER_PHONE = "{CUSTOMER_PHONE}";
    private final String CUSTOMER_FAX = "{CUSTOMER_FAX}";
    private final String CUSTOMER_TAXOFFICE = "{CUSTOMER_TAXOFFICE}";
    private final String CUSTOMER_TAXNUMBER = "{CUSTOMER_TAXNUMBER}";
    private final String CUSTOMER_POSTCODE = "{CUSTOMER_POSTCODE}";
    private final String QRCODE = "{QRCODE}";
    private final String FICHENO = "{FICHENO}";
    private final String DOC_DATE = "{DOC_DATE}";
    private final String DOC_TIME = "{DOC_TIME}";
    private final String SHIP_DATE = "{SHIP_DATE}";
    private final String SHIP_TIME = "{SHIP_TIME}";
    private final String ETTN = "{ETTN}";
    private final String FICHE_LINES = "{FICHE_LINES}";
    private final String LINE_NO = "{LINE_NO}";
    private final String PRODUCT_NAME = "{PRODUCT_NAME}";
    private final String PRODUCT_CODE = "{PRODUCT_CODE}";
    private final String PRODUCT_AMOUNT = "{PRODUCT_AMOUNT}";
    private final String PRODUCT_LINE_PRICE = "{PRODUCT_LINE_PRICE}";
    private final String PRODUCT_TOTAL = "{PRODUCT_TOTAL}";
    private final String FICHE_TOTAL = "{FICHE_TOTAL}";
    private final String SHIPAGENT_TAXNUMBER = "{SHIPAGENT_TAXNUMBER}";
    private final String SHIPAGENT_TITLE = "{SHIPAGENT_TITLE}";
    private final String PRODUCT_UNIT = "{PRODUCT_UNIT}";
    private final String PRICE_CURRENCY = "{PRICE_CURRENCY}";
    private final String GROSS_TOTAL = "{GROSS_TOTAL}";
    private final String TOTALDISCOUNTS = "{TOTALDISCOUNTS}";
    private final String TOTALVAT = "{TOTALVAT}";
    private final String INVOICE_NO = "{INVOICENO}";
    private final String INVOICE_DATE = "{INVOICEDATE}";
    private final String VATAMNT = "{VATAMNT}";
    private final String VAT = "{VAT}";
    private final String DISTDISC = "{DISTDISC}";
    private final String LINEEXP = "{LINEEXP}";
    private final String VATEXCEPTREASON = "{VATEXCEPTREASON}";
    private final String TOTALBYTEXT = "{TOTALBYTEXT}";
    private final String BALANCE = "{BALANCE}";
    private final String SHIPADDRESSTITLE = "{SHIPADDRESSTITLE}";
    private final String SHIPADDRESSTAXNR = "{SHIPADDRESSTAXNR}";
    private final String VKN_OR_TCKN = "{VKN_OR_TCKN}";
    private final String VEHICLE_PLATE_NUMBER = "{VEHICLE_PLATE_NUMBER}";
    private final String TRAILER_PLATE_NUMBER = "{TRAILER_PLATE_NUMBER}";
    private final String DRIVER_NAME_SURNAME = "{DRIVER_NAME_SURNAME}";
    private final String DRIVER_IDENTITY_NUMBER = "{DRIVER_IDENTITY_NUMBER}";
    private final String GENEXP1 = "{GENEXP1}";

    public EDocumentHtmlReplacer(EDocumentPdfContent eDocumentPdfContent) {
        this.mPdfModel = eDocumentPdfContent;
    }

    public String prepareHtml(String str, String str2, SalesType salesType) {
        this.htmlContainer = ContextUtils.getTextAssets(str);
        ErpType erpType = ErpCreator.getInstance().getmBaseErp().getErpType();
        ErpType erpType2 = ErpType.NETSIS;
        if (erpType == erpType2) {
            fillNetsisFirmaInfo();
        } else {
            fillFirmaInfo(salesType);
        }
        if (ErpCreator.getInstance().getmBaseErp().getErpType() == erpType2) {
            fillNetsisDocumentInfo(str2, salesType);
        } else if (SalesUtils.isSalesTypeDispatchOrWhTransfer(salesType)) {
            fillTigerDispatchInfo(str2, salesType);
        } else if (SalesUtils.isSalesTypeOnlyInvoice(salesType)) {
            fillTigerInvoiceInfo(str2, salesType);
        }
        String replace = this.htmlContainer.replace("{PRICE_CURRENCY}", this.mPdfModel.getLocalCurrencyCode());
        this.htmlContainer = replace;
        return replace;
    }

    private String fillNetsisFirmaInfo() {
        String replace = this.htmlContainer.replace("{FIRM_NAME}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getFirmTitle()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getFirmTitle() : "");
        this.htmlContainer = replace;
        String replace2 = replace.replace("{FIRM_ADDRESS}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getFirmAddr1()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getFirmAddr1() : "");
        this.htmlContainer = replace2;
        String replace3 = replace2.replace("{FIRM_COUNTY}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getFirmTown()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getFirmTown() : "");
        this.htmlContainer = replace3;
        String replace4 = replace3.replace("{FIRM_CITY}", getCityNetsis(this.mPdfModel.getEDocumentPdfHeaderNetsis().getFirmCity()));
        this.htmlContainer = replace4;
        String replace5 = replace4.replace("{FIRM_PHONE}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getFirmtPhone1()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getFirmtPhone1() : "");
        this.htmlContainer = replace5;
        String replace6 = replace5.replace("{FIRM_FAX}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getFirmFax()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getFirmFax() : "");
        this.htmlContainer = replace6;
        String replace7 = replace6.replace("{FIRM_WEBURL}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getFirmWebAddr()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getFirmWebAddr() : "");
        this.htmlContainer = replace7;
        String replace8 = replace7.replace("{FIRM_EMAIL}", TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getFirmEmailAddr()) ? "" : this.mPdfModel.getEDocumentPdfHeaderNetsis().getFirmEmailAddr());
        this.htmlContainer = replace8;
        String replace9 = replace8.replace("{FIRM_TAXOFFICE}", this.mPdfModel.getEDocumentPdfHeaderNetsis().getFirmTaxOffice());
        this.htmlContainer = replace9;
        String replace10 = replace9.replace("{FIRM_TAXNUMBER}", this.mPdfModel.getEDocumentPdfHeaderNetsis().getFirmTaxNr());
        this.htmlContainer = replace10;
        return replace10;
    }

    private String fillFirmaInfo(SalesType salesType) {
        String replace = this.htmlContainer.replace("{FIRM_NAME}", !TextUtils.isEmpty(this.mPdfModel.getFirm().getOfficalTitle()) ? this.mPdfModel.getFirm().getOfficalTitle() : "");
        this.htmlContainer = replace;
        String replace2 = replace.replace("{FIRM_ADDRESS}", !TextUtils.isEmpty(this.mPdfModel.getFirm().getAddress()) ? this.mPdfModel.getFirm().getAddress() : "");
        this.htmlContainer = replace2;
        String replace3 = replace2.replace("{FIRM_COUNTY}", !TextUtils.isEmpty(this.mPdfModel.getFirm().getDistrict()) ? this.mPdfModel.getFirm().getDistrict() : "");
        this.htmlContainer = replace3;
        String replace4 = replace3.replace("{FIRM_CITY}", !TextUtils.isEmpty(this.mPdfModel.getFirm().getCity()) ? this.mPdfModel.getFirm().getCity() : "");
        this.htmlContainer = replace4;
        String replace5 = replace4.replace("{FIRM_PHONE}", !TextUtils.isEmpty(this.mPdfModel.getFirm().getPhone1()) ? this.mPdfModel.getFirm().getPhone1() : "");
        this.htmlContainer = replace5;
        String replace6 = replace5.replace("{FIRM_FAX}", !TextUtils.isEmpty(this.mPdfModel.getFirm().getFax()) ? this.mPdfModel.getFirm().getFax() : "");
        this.htmlContainer = replace6;
        String replace7 = replace6.replace("{FIRM_WEBURL}", !TextUtils.isEmpty(this.mPdfModel.getFirm().getProfileId()) ? this.mPdfModel.getFirm().getProfileId() : "");
        this.htmlContainer = replace7;
        String replace8 = replace7.replace("{FIRM_EMAIL}", !TextUtils.isEmpty(this.mPdfModel.getFirm().getFirmEmailAddress()) ? this.mPdfModel.getFirm().getFirmEmailAddress() : "");
        this.htmlContainer = replace8;
        String replace9 = replace8.replace("{FIRM_TAXOFFICE}", !TextUtils.isEmpty(this.mPdfModel.getFirm().getTaxOffice()) ? this.mPdfModel.getFirm().getTaxOffice() : "");
        this.htmlContainer = replace9;
        String replace10 = replace9.replace("{FIRM_TAXNUMBER}", !TextUtils.isEmpty(this.mPdfModel.getFirm().getTaxNr()) ? this.mPdfModel.getFirm().getTaxNr() : "");
        this.htmlContainer = replace10;
        String replace11 = replace10.replace("{FIRM_POSTCODE}", TextUtils.isEmpty(this.mPdfModel.getFirm().getZipCode()) ? "" : this.mPdfModel.getFirm().getZipCode());
        this.htmlContainer = replace11;
        String replace12 = replace11.replace("{CUSTOMER_ADRESS}", getCustomerAddress(salesType));
        this.htmlContainer = replace12;
        return replace12;
    }

    private String fillNetsisDocumentInfo(String str, SalesType salesType) {
        String replace = this.htmlContainer.replace("{CUSTOMER_NAME}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientDef()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientDef() : "");
        this.htmlContainer = replace;
        String replace2 = replace.replace("{CUSTOMER_COUNTY}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientTown()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientTown() : "");
        this.htmlContainer = replace2;
        String replace3 = replace2.replace("{CUSTOMER_CITY}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientCity()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientCity() : "");
        this.htmlContainer = replace3;
        String replace4 = replace3.replace("{CUSTOMER_WEBURL}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientWebAddr()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientWebAddr() : "");
        this.htmlContainer = replace4;
        String replace5 = replace4.replace("{CUSTOMER_EMAIL}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientEmailAddr()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientEmailAddr() : "");
        this.htmlContainer = replace5;
        String replace6 = replace5.replace("{CUSTOMER_PHONE}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientPhone1()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientPhone1() : "");
        this.htmlContainer = replace6;
        String replace7 = replace6.replace("{CUSTOMER_FAX}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientFax()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientFax() : "");
        this.htmlContainer = replace7;
        String replace8 = replace7.replace("{CUSTOMER_TAXOFFICE}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientTaxOffice()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientTaxOffice() : "");
        this.htmlContainer = replace8;
        String replace9 = replace8.replace("{CUSTOMER_TAXNUMBER}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientTaxNr()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientTaxNr() : "");
        this.htmlContainer = replace9;
        String replace10 = replace9.replace("{VKN_OR_TCKN}", "VKN");
        this.htmlContainer = replace10;
        String replace11 = replace10.replace("{CUSTOMER_ADRESS}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientAddr1()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getClientAddr1() : "");
        this.htmlContainer = replace11;
        String replace12 = replace11.replace("{FICHE_LINES}", prepareLines(str, salesType));
        this.htmlContainer = replace12;
        String replace13 = replace12.replace("{FICHE_TOTAL}", StringUtils.formatDouble(this.mPdfModel.getEDocumentPdfHeaderNetsis().getNetTotal()));
        this.htmlContainer = replace13;
        String replace14 = replace13.replace("{TOTALDISCOUNTS}", StringUtils.formatDouble(this.mPdfModel.getEDocumentPdfHeaderNetsis().getTotalDiscount()));
        this.htmlContainer = replace14;
        String replace15 = replace14.replace("{GROSS_TOTAL}", StringUtils.formatDouble(this.mPdfModel.getEDocumentPdfHeaderNetsis().getGrossTotal()));
        this.htmlContainer = replace15;
        String replace16 = replace15.replace("{TOTALVAT}", StringUtils.formatDouble(this.mPdfModel.getEDocumentPdfHeaderNetsis().getTotalVat()));
        this.htmlContainer = replace16;
        String replace17 = replace16.replace("{SHIPADDRESSTAXNR}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getShipAddressTaxNr()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getShipAddressTaxNr() : "");
        this.htmlContainer = replace17;
        String replace18 = replace17.replace("{SHIPADDRESSTITLE}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getShipAddressTitle()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getShipAddressTitle() : "");
        this.htmlContainer = replace18;
        String replace19 = replace18.replace("{FICHENO}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getFicheNo()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getFicheNo() : "");
        this.htmlContainer = replace19;
        String replace20 = replace19.replace("{DOC_DATE}", getFormattedDate(this.mPdfModel.getEDocumentPdfHeaderNetsis().getDocDate()));
        this.htmlContainer = replace20;
        String replace21 = replace20.replace("{TOTALBYTEXT}", StringUtils.convertTotal2Text(StringUtils.formatDouble(this.mPdfModel.getEDocumentPdfHeaderNetsis().getNetTotal()), "", "", ""));
        this.htmlContainer = replace21;
        String replace22 = replace21.replace("{BALANCE}", !TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getBakiye()) ? this.mPdfModel.getEDocumentPdfHeaderNetsis().getBakiye() : "");
        this.htmlContainer = replace22;
        String replace23 = replace22.replace("{ETTN}", this.mPdfModel.getEDocumentPdfHeaderNetsis().getGuid());
        this.htmlContainer = replace23;
        String replace24 = replace23.replace("{QRCODE}", TextUtils.isEmpty(this.mPdfModel.getEDocumentPdfHeaderNetsis().getGuid()) ? "" : generateQrCode(this.mPdfModel.getEDocumentPdfHeaderNetsis().getGuid()));
        this.htmlContainer = replace24;
        String replace25 = replace24.replace("{VEHICLE_PLATE_NUMBER}", this.mPdfModel.getEDocumentPdfHeaderNetsis().getPlateNum1());
        this.htmlContainer = replace25;
        String replace26 = replace25.replace("{TRAILER_PLATE_NUMBER}", this.mPdfModel.getEDocumentPdfHeaderNetsis().getShipDorsePlaka1());
        this.htmlContainer = replace26;
        String replace27 = replace26.replace("{DRIVER_NAME_SURNAME}", this.mPdfModel.getEDocumentPdfHeaderNetsis().getDriverName1() + " " + this.mPdfModel.getEDocumentPdfHeaderNetsis().getDriverSurname1());
        this.htmlContainer = replace27;
        String replace28 = replace27.replace("{DRIVER_IDENTITY_NUMBER}", this.mPdfModel.getEDocumentPdfHeaderNetsis().getDriverTckNo1());
        this.htmlContainer = replace28;
        String replace29 = replace28.replace("{GENEXP1}", this.mPdfModel.getEDocumentPdfHeaderNetsis().getGenExp1());
        this.htmlContainer = replace29;
        return replace29;
    }

    private String fillTigerDispatchInfo(String str, SalesType salesType) {
        String replace = this.htmlContainer.replace("{CUSTOMER_NAME}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getClientDef()) ? this.mPdfModel.getEDespatchPdfHeader().getClientDef() : "");
        this.htmlContainer = replace;
        String replace2 = replace.replace("{CUSTOMER_COUNTY}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getClientTown()) ? this.mPdfModel.getEDespatchPdfHeader().getClientTown() : "");
        this.htmlContainer = replace2;
        String replace3 = replace2.replace("{CUSTOMER_CITY}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getClientCity()) ? this.mPdfModel.getEDespatchPdfHeader().getClientCity() : "");
        this.htmlContainer = replace3;
        String replace4 = replace3.replace("{CUSTOMER_WEBURL}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getClientWebAddr()) ? this.mPdfModel.getEDespatchPdfHeader().getClientWebAddr() : "");
        this.htmlContainer = replace4;
        String replace5 = replace4.replace("{CUSTOMER_EMAIL}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getClientEmailAddr()) ? this.mPdfModel.getEDespatchPdfHeader().getClientEmailAddr() : "");
        this.htmlContainer = replace5;
        String replace6 = replace5.replace("{CUSTOMER_PHONE}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getClientPhone1()) ? this.mPdfModel.getEDespatchPdfHeader().getClientPhone1() : "");
        this.htmlContainer = replace6;
        String replace7 = replace6.replace("{CUSTOMER_FAX}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getClientFax()) ? this.mPdfModel.getEDespatchPdfHeader().getClientFax() : "");
        this.htmlContainer = replace7;
        String replace8 = replace7.replace("{CUSTOMER_TAXOFFICE}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getClientTaxOffice()) ? this.mPdfModel.getEDespatchPdfHeader().getClientTaxOffice() : "");
        this.htmlContainer = replace8;
        String replace9 = replace8.replace("{CUSTOMER_TAXNUMBER}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getClientTaxNr()) ? this.mPdfModel.getEDespatchPdfHeader().getClientTaxNr() : "");
        this.htmlContainer = replace9;
        String replace10 = replace9.replace("{QRCODE}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getGuid()) ? generateQrCode(this.mPdfModel.getEDespatchPdfHeader().getGuid()) : "");
        this.htmlContainer = replace10;
        String replace11 = replace10.replace("{FICHENO}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getFicheNo()) ? this.mPdfModel.getEDespatchPdfHeader().getFicheNo() : "");
        this.htmlContainer = replace11;
        String replace12 = replace11.replace("{DOC_DATE}", getFormattedDate(this.mPdfModel.getEDespatchPdfHeader().getDocDate()));
        this.htmlContainer = replace12;
        String replace13 = replace12.replace("{DOC_TIME}", DateAndTimeUtils.intToNowTimeLogo(this.mPdfModel.getEDespatchPdfHeader().getDocTime()));
        this.htmlContainer = replace13;
        String replace14 = replace13.replace("{SHIP_DATE}", getFormattedDate(this.mPdfModel.getEDespatchPdfHeader().getShipDate()));
        this.htmlContainer = replace14;
        String replace15 = replace14.replace("{SHIP_TIME}", DateAndTimeUtils.intToNowTimeLogo(this.mPdfModel.getEDespatchPdfHeader().getShipTime()));
        this.htmlContainer = replace15;
        String replace16 = replace15.replace("{ETTN}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getGuid()) ? this.mPdfModel.getEDespatchPdfHeader().getGuid() : "");
        this.htmlContainer = replace16;
        String replace17 = replace16.replace("{FICHE_LINES}", prepareLines(str, salesType));
        this.htmlContainer = replace17;
        String replace18 = replace17.replace("{FICHE_TOTAL}", StringUtils.formatDouble(this.mPdfModel.getEDespatchPdfHeader().getNetTotal()));
        this.htmlContainer = replace18;
        String replace19 = replace18.replace("{TOTALDISCOUNTS}", StringUtils.formatDouble(this.mPdfModel.getEDespatchPdfHeader().getTotalDiscount()));
        this.htmlContainer = replace19;
        String replace20 = replace19.replace("{GROSS_TOTAL}", StringUtils.formatDouble(this.mPdfModel.getEDespatchPdfHeader().getGrossTotal()));
        this.htmlContainer = replace20;
        String replace21 = replace20.replace("{TOTALVAT}", StringUtils.formatDouble(this.mPdfModel.getEDespatchPdfHeader().getTotalVat()));
        this.htmlContainer = replace21;
        String replace22 = replace21.replace("{SHIPAGENT_TAXNUMBER}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getShipAgentTaxNr()) ? this.mPdfModel.getEDespatchPdfHeader().getShipAgentTaxNr() : "");
        this.htmlContainer = replace22;
        String replace23 = replace22.replace("{SHIPAGENT_TITLE}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getShipAgentTitle()) ? this.mPdfModel.getEDespatchPdfHeader().getShipAgentTitle() : "");
        this.htmlContainer = replace23;
        String replace24 = replace23.replace("{VEHICLE_PLATE_NUMBER}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getPlateNum1()) ? this.mPdfModel.getEDespatchPdfHeader().getPlateNum1() : "");
        this.htmlContainer = replace24;
        String replace25 = replace24.replace("{TRAILER_PLATE_NUMBER}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getChassisNum1()) ? this.mPdfModel.getEDespatchPdfHeader().getChassisNum1() : "");
        this.htmlContainer = replace25;
        String sb = (!TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getDriverName1()) ? this.mPdfModel.getEDespatchPdfHeader().getDriverName1() : "") +
                " " +
                (!TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getDriverSurname1()) ? this.mPdfModel.getEDespatchPdfHeader().getDriverSurname1() : "");
        String replace26 = replace25.replace("{DRIVER_NAME_SURNAME}", sb);
        this.htmlContainer = replace26;
        String replace27 = replace26.replace("{DRIVER_IDENTITY_NUMBER}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getDriverTckNo1()) ? this.mPdfModel.getEDespatchPdfHeader().getDriverTckNo1() : "");
        this.htmlContainer = replace27;
        String replace28 = replace27.replace("{SHIPADDRESSTITLE}", !TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getShipAddressTitle()) ? this.mPdfModel.getEDespatchPdfHeader().getShipAddressTitle() : "");
        this.htmlContainer = replace28;
        String replace29 = replace28.replace("{GENEXP1}", TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getGenExp1()) ? "" : this.mPdfModel.getEDespatchPdfHeader().getGenExp1());
        this.htmlContainer = replace29;
        return replace29;
    }

    private String fillTigerInvoiceInfo(String str, SalesType salesType) {
        String replace = this.htmlContainer.replace("{CUSTOMER_NAME}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getClientDef()) ? this.mPdfModel.getEInvoicePdfHeader().getClientDef() : "");
        this.htmlContainer = replace;
        String replace2 = replace.replace("{CUSTOMER_COUNTY}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getClientTown()) ? this.mPdfModel.getEInvoicePdfHeader().getClientTown() : "");
        this.htmlContainer = replace2;
        String replace3 = replace2.replace("{CUSTOMER_CITY}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getClientCity()) ? this.mPdfModel.getEInvoicePdfHeader().getClientCity() : "");
        this.htmlContainer = replace3;
        String replace4 = replace3.replace("{CUSTOMER_WEBURL}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getClientWebAddr()) ? this.mPdfModel.getEInvoicePdfHeader().getClientWebAddr() : "");
        this.htmlContainer = replace4;
        String replace5 = replace4.replace("{CUSTOMER_EMAIL}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getClientEmailAddr()) ? this.mPdfModel.getEInvoicePdfHeader().getClientEmailAddr() : "");
        this.htmlContainer = replace5;
        String replace6 = replace5.replace("{CUSTOMER_PHONE}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getClientPhone1()) ? this.mPdfModel.getEInvoicePdfHeader().getClientPhone1() : "");
        this.htmlContainer = replace6;
        String replace7 = replace6.replace("{CUSTOMER_FAX}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getClientFax()) ? this.mPdfModel.getEInvoicePdfHeader().getClientFax() : "");
        this.htmlContainer = replace7;
        String replace8 = replace7.replace("{CUSTOMER_TAXOFFICE}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getClientTaxOffice()) ? this.mPdfModel.getEInvoicePdfHeader().getClientTaxOffice() : "");
        this.htmlContainer = replace8;
        String replace9 = replace8.replace("{VKN_OR_TCKN}", isPersonalCompany(this.mPdfModel.getEInvoicePdfHeader().getClientRef()) ? "TCKN" : "VKN");
        this.htmlContainer = replace9;
        String replace10 = replace9.replace("{CUSTOMER_TAXNUMBER}", getCustomerTaxNr());
        this.htmlContainer = replace10;
        String replace11 = replace10.replace("{CUSTOMER_POSTCODE}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getClientPostCode()) ? this.mPdfModel.getEInvoicePdfHeader().getClientPostCode() : "");
        this.htmlContainer = replace11;
        String replace12 = replace11.replace("{QRCODE}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getGuid()) ? generateQrCode(this.mPdfModel.getEInvoicePdfHeader().getGuid()) : "");
        this.htmlContainer = replace12;
        String replace13 = replace12.replace("{ETTN}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getGuid()) ? this.mPdfModel.getEInvoicePdfHeader().getGuid() : "");
        this.htmlContainer = replace13;
        String replace14 = replace13.replace("{FICHE_LINES}", prepareLines(str, salesType));
        this.htmlContainer = replace14;
        String replace15 = replace14.replace("{FICHE_TOTAL}", StringUtils.formatDouble(this.mPdfModel.getEInvoicePdfHeader().getNetTotal()));
        this.htmlContainer = replace15;
        String replace16 = replace15.replace("{TOTALDISCOUNTS}", StringUtils.formatDouble(this.mPdfModel.getEInvoicePdfHeader().getTotalDiscount()));
        this.htmlContainer = replace16;
        String replace17 = replace16.replace("{GROSS_TOTAL}", StringUtils.formatDouble(this.mPdfModel.getEInvoicePdfHeader().getGrossTotal()));
        this.htmlContainer = replace17;
        String replace18 = replace17.replace("{TOTALVAT}", StringUtils.formatDouble(this.mPdfModel.getEInvoicePdfHeader().getTotalVat()));
        this.htmlContainer = replace18;
        String replace19 = replace18.replace("{SHIPAGENT_TAXNUMBER}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getShipAgentTaxNr()) ? this.mPdfModel.getEInvoicePdfHeader().getShipAgentTaxNr() : "");
        this.htmlContainer = replace19;
        String replace20 = replace19.replace("{SHIPAGENT_TITLE}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getShipAgentTitle()) ? this.mPdfModel.getEInvoicePdfHeader().getShipAgentTitle() : "");
        this.htmlContainer = replace20;
        String replace21 = replace20.replace("{INVOICEDATE}", getFormattedDate(this.mPdfModel.getEInvoicePdfHeader().getInvoiceDate()));
        this.htmlContainer = replace21;
        String replace22 = replace21.replace("{INVOICENO}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getInvoiceNo()) ? this.mPdfModel.getEInvoicePdfHeader().getInvoiceNo() : "");
        this.htmlContainer = replace22;
        String replace23 = replace22.replace("{FICHENO}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getDespatchNo()) ? this.mPdfModel.getEInvoicePdfHeader().getDespatchNo() : "");
        this.htmlContainer = replace23;
        String replace24 = replace23.replace("{DOC_DATE}", getFormattedDate(this.mPdfModel.getEInvoicePdfHeader().getDocDate()));
        this.htmlContainer = replace24;
        String replace25 = replace24.replace("{TOTALBYTEXT}", StringUtils.convertTotal2Text(StringUtils.formatDouble(this.mPdfModel.getEInvoicePdfHeader().getNetTotal()), "", "", ""));
        this.htmlContainer = replace25;
        String replace26 = replace25.replace("{SHIPADDRESSTITLE}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getShipAddressTitle()) ? this.mPdfModel.getEInvoicePdfHeader().getShipAddressTitle() : "");
        this.htmlContainer = replace26;
        String replace27 = replace26.replace("{SHIPADDRESSTAXNR}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getShipAddressTaxNr()) ? this.mPdfModel.getEInvoicePdfHeader().getShipAddressTaxNr() : "");
        this.htmlContainer = replace27;
        String replace28 = replace27.replace("{BALANCE}", StringUtils.formatDouble(getCustomerBalance(this.mPdfModel.getEInvoicePdfHeader().getClientRef()).doubleValue()));
        this.htmlContainer = replace28;
        String replace29 = replace28.replace("{VEHICLE_PLATE_NUMBER}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getPlateNum1()) ? this.mPdfModel.getEInvoicePdfHeader().getPlateNum1() : "");
        this.htmlContainer = replace29;
        String replace30 = replace29.replace("{TRAILER_PLATE_NUMBER}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getChassisNum1()) ? this.mPdfModel.getEInvoicePdfHeader().getChassisNum1() : "");
        this.htmlContainer = replace30;
        String sb = (!TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getDriverName1()) ? this.mPdfModel.getEInvoicePdfHeader().getDriverName1() : "") +
                " " +
                (!TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getDriverSurname1()) ? this.mPdfModel.getEInvoicePdfHeader().getDriverSurname1() : "");
        String replace31 = replace30.replace("{DRIVER_NAME_SURNAME}", sb);
        this.htmlContainer = replace31;
        String replace32 = replace31.replace("{DRIVER_IDENTITY_NUMBER}", !TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getDriverTckNo1()) ? this.mPdfModel.getEInvoicePdfHeader().getDriverTckNo1() : "");
        this.htmlContainer = replace32;
        String replace33 = replace32.replace("{GENEXP1}", TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getGenExp1()) ? "" : this.mPdfModel.getEInvoicePdfHeader().getGenExp1());
        this.htmlContainer = replace33;
        return replace33;
    }

    private String getCustomerTaxNr() {
        if (!TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getClientIdentityNr())) {
            return this.mPdfModel.getEInvoicePdfHeader().getClientIdentityNr();
        }
        if (TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getClientTaxNr())) {
            return "";
        }
        return this.mPdfModel.getEInvoicePdfHeader().getClientTaxNr();
    }

    private boolean isPersonalCompany(int i2) {
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ClCard.class, "LOGICALREF=?", new String[]{String.valueOf(i2)});
        return ((table == null || table.size() <= 0) ? 0 : ((ClCard) table.get(0)).getPersonalCompany()) == 1;
    }

    private Double getCustomerBalance(int i2) {
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ClCard.class, "LOGICALREF=?", new String[]{String.valueOf(i2)});
        return Double.valueOf((table == null || table.size() <= 0) ? 0.0d : ((ClCard) table.get(0)).getBakiye());
    }

    private String getCityNetsis(String str) {
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(City.class, "CITYCODE=?", new String[]{str});
        if (table != null && table.size() > 0) {
            return ((City) table.get(0)).getCityName();
        }
        return "";
    }

    private String getFormattedDate(String str) {
        if (!TextUtils.isEmpty(str)) {
            return DateAndTimeUtils.convertDateY(str.split(ExifInterface.GPS_DIRECTION_TRUE)[0]);
        }
        return "";
    }

    private String getCustomerAddress(SalesType salesType) {
        StringBuilder sb = new StringBuilder();
        if (salesType == SalesType.DISPATCH) {
            if (!TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getClientAddr1())) {
                sb.append(this.mPdfModel.getEDespatchPdfHeader().getClientAddr1());
                sb.append(" ");
            }
            if (!TextUtils.isEmpty(this.mPdfModel.getEDespatchPdfHeader().getClientAddr2())) {
                sb.append(this.mPdfModel.getEDespatchPdfHeader().getClientAddr2());
            }
        } else if (salesType == SalesType.INVOICE) {
            if (!TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getClientAddr1())) {
                sb.append(this.mPdfModel.getEInvoicePdfHeader().getClientAddr1());
                sb.append(" ");
            }
            if (!TextUtils.isEmpty(this.mPdfModel.getEInvoicePdfHeader().getClientAddr2())) {
                sb.append(this.mPdfModel.getEInvoicePdfHeader().getClientAddr2());
            }
        }
        return sb.toString();
    }

    private String prepareLines(String str, SalesType salesType) {
        String str2;
        int i2;
        String str3;
        String str4;
        CharSequence charSequence;
        CharSequence charSequence2;
        CharSequence charSequence3;
        CharSequence charSequence4;
        CharSequence charSequence5;
        String str5;
        String str6;
        String convertIntToString;
        String localCurrencyCode;
        CharSequence charSequence6;
        String str7;
        CharSequence charSequence7;
        String str8;
        CharSequence charSequence8;
        String str9;
        String str10;
        String localCurrencyCode2;
        EDocumentHtmlReplacer eDocumentHtmlReplacer = this;
        String str11 = str;
        SalesType salesType2 = salesType;
        StringBuilder sb = new StringBuilder();
        String str12 = "{PRODUCT_CODE}";
        CharSequence charSequence9 = "{VATEXCEPTREASON}";
        String str13 = "{PRODUCT_AMOUNT}";
        CharSequence charSequence10 = "{LINEEXP}";
        String str14 = "{PRODUCT_NAME}";
        CharSequence charSequence11 = "{DISTDISC}";
        String str15 = "{LINE_NO}";
        CharSequence charSequence12 = "{VAT}";
        CharSequence charSequence13 = "{VATAMNT}";
        if (ErpCreator.getInstance().getmBaseErp().getErpType() == ErpType.NETSIS) {
            Iterator<EDocumentPdfDetailNetsis> it = eDocumentHtmlReplacer.mPdfModel.getmEDocumentPdfDetailNetsisList().iterator();
            int i3 = 1;
            while (it.hasNext()) {
                EDocumentPdfDetailNetsis next = it.next();
                Iterator<EDocumentPdfDetailNetsis> it2 = it;
                String str16 = str12;
                String replace = ContextUtils.getTextAssets(str).replace("{LINE_NO}", StringUtils.convertIntToString(i3)).replace("{PRODUCT_NAME}", !TextUtils.isEmpty(next.getItemName()) ? next.getItemName() : "").replace(str13, StringUtils.formatDoubleThreeDigits(next.getAmount()));
                SalesType salesType3 = SalesType.DISPATCH;
                if (salesType2 == salesType3 && str11.equals("edocument_line_template_for_3_inc")) {
                    str10 = str13;
                    localCurrencyCode2 = "";
                } else {
                    str10 = str13;
                    localCurrencyCode2 = eDocumentHtmlReplacer.mPdfModel.getLocalCurrencyCode();
                }
                sb.append(replace.replace("{PRICE_CURRENCY}", localCurrencyCode2).replace("{PRODUCT_UNIT}", !TextUtils.isEmpty(next.getUnitCode()) ? next.getUnitCode() : "").replace("{PRODUCT_LINE_PRICE}", (salesType2 == salesType3 && str11.equals("edocument_line_template_for_3_inc")) ? "" : StringUtils.formatDouble(next.getPrice())).replace("{PRODUCT_TOTAL}", (salesType2 == salesType3 && str11.equals("edocument_line_template_for_3_inc")) ? "" : StringUtils.formatDouble(next.getAmount() * next.getPrice())));
                i3++;
                it = it2;
                str12 = str16;
                str13 = str10;
            }
            str2 = str13;
            str3 = "{PRODUCT_NAME}";
            i2 = i3;
            str5 = str12;
            charSequence = charSequence9;
            charSequence2 = charSequence10;
            charSequence3 = charSequence11;
            charSequence5 = charSequence13;
            str4 = "{LINE_NO}";
            charSequence4 = charSequence12;
        } else {
            str2 = "{PRODUCT_AMOUNT}";
            String str17 = "{PRODUCT_CODE}";
            Iterator<EDocumentPdfDetail> it3 = eDocumentHtmlReplacer.mPdfModel.getEDocumentPdfDetailList().iterator();
            i2 = 1;
            while (it3.hasNext()) {
                EDocumentPdfDetail next2 = it3.next();
                String str18 = str17;
                Iterator<EDocumentPdfDetail> it4 = it3;
                String replace2 = ContextUtils.getTextAssets(str).replace(str15, StringUtils.convertIntToString(i2)).replace(str18, !TextUtils.isEmpty(next2.getItemCode()) ? next2.getItemCode() : "").replace(str14, !TextUtils.isEmpty(next2.getItemName()) ? next2.getItemName() : "");
                String str19 = str14;
                if (next2.getDivUnit() == 1) {
                    convertIntToString = StringUtils.formatDoubleThreeDigits(next2.getAmount());
                    str6 = str15;
                } else {
                    str6 = str15;
                    convertIntToString = StringUtils.convertIntToString((int) next2.getAmount());
                }
                String str20 = str2;
                String replace3 = replace2.replace(str20, convertIntToString);
                SalesType salesType4 = SalesType.DISPATCH;
                if (salesType2 == salesType4 && str11.equals("edocument_line_template_for_3_inc")) {
                    str2 = str20;
                    localCurrencyCode = "";
                } else {
                    str2 = str20;
                    localCurrencyCode = eDocumentHtmlReplacer.mPdfModel.getLocalCurrencyCode();
                }
                String replace4 = replace3.replace("{PRICE_CURRENCY}", localCurrencyCode).replace("{PRODUCT_UNIT}", !TextUtils.isEmpty(next2.getUnitCode()) ? next2.getUnitCode() : "").replace("{PRODUCT_LINE_PRICE}", (salesType2 == salesType4 && str11.equals("edocument_line_template_for_3_inc")) ? "" : StringUtils.formatDouble(next2.getPrice()));
                String formatDouble = (salesType2 == salesType4 && str11.equals("edocument_line_template_for_3_inc")) ? "" : StringUtils.formatDouble(next2.getTotal());
                CharSequence charSequence14 = charSequence13;
                String replace5 = replace4.replace("{PRODUCT_TOTAL}", formatDouble).replace(charSequence14, StringUtils.formatDouble(next2.getVatAmount()));
                if (TextUtils.isEmpty(StringUtils.formatDouble(next2.getVat()))) {
                    charSequence6 = charSequence12;
                    str7 = "";
                } else {
                    str7 = "%" + StringUtils.formatDouble(next2.getVat());
                    charSequence6 = charSequence12;
                }
                CharSequence charSequence15 = charSequence11;
                String replace6 = replace5.replace(charSequence6, str7).replace(charSequence15, StringUtils.formatDouble(next2.getDistDisc()));
                if (TextUtils.isEmpty(next2.getLineExp())) {
                    charSequence7 = charSequence10;
                    str8 = "";
                } else {
                    str8 = next2.getLineExp();
                    charSequence7 = charSequence10;
                }
                String replace7 = replace6.replace(charSequence7, str8);
                if (TextUtils.isEmpty(next2.getVatExceptReason())) {
                    charSequence8 = charSequence9;
                    str9 = "";
                } else {
                    str9 = next2.getVatExceptReason();
                    charSequence8 = charSequence9;
                }
                sb.append(replace7.replace(charSequence8, str9));
                i2++;
                eDocumentHtmlReplacer = this;
                charSequence11 = charSequence15;
                charSequence10 = charSequence7;
                charSequence9 = charSequence8;
                charSequence12 = charSequence6;
                charSequence13 = charSequence14;
                str15 = str6;
                str14 = str19;
                str11 = str;
                salesType2 = salesType;
                str17 = str18;
                it3 = it4;
            }
            str3 = str14;
            str4 = str15;
            charSequence = charSequence9;
            charSequence2 = charSequence10;
            charSequence3 = charSequence11;
            charSequence4 = charSequence12;
            charSequence5 = charSequence13;
            str5 = str17;
        }
        if (i2 < 10) {
            for (int i4 = 10; i2 <= i4; i4 = 10) {
                sb.append(ContextUtils.getTextAssets(str).replace(str4, "").replace(str5, "").replace(str3, "").replace(str2, "").replace("{PRICE_CURRENCY}", "").replace("{PRODUCT_UNIT}", "").replace("{PRODUCT_LINE_PRICE}", "").replace("{PRODUCT_TOTAL}", "").replace(charSequence5, "").replace(charSequence4, "").replace(charSequence3, "").replace(charSequence2, "").replace(charSequence, ""));
                i2++;
            }
        }
        return sb.toString();
    }

    private String generateQrCode(String str) {
        QRCodeWriter qRCodeWriter = new QRCodeWriter();
        EnumMap enumMap = new EnumMap(EncodeHintType.class);
        enumMap.put((EnumMap) EncodeHintType.CHARACTER_SET, (EncodeHintType) "UTF-8");
        enumMap.put((EnumMap) EncodeHintType.MARGIN, (EncodeHintType) 0);
        enumMap.put((EnumMap) EncodeHintType.ERROR_CORRECTION, (EncodeHintType) ErrorCorrectionLevel.H);
        enumMap.put((EnumMap) EncodeHintType.QR_VERSION, (EncodeHintType) 5);
        try {
            return BitmapUtils.convertBitmapToJpgBase64(new BarcodeEncoder().createBitmap(qRCodeWriter.encode(str, BarcodeFormat.QR_CODE, 100, 100, enumMap)));
        } catch (WriterException e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
