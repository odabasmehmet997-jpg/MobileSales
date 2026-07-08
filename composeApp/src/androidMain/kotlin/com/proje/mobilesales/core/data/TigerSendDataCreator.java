package com.proje.mobilesales.core.data;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.util.TimingLogger;
import android.util.Xml;
import androidx.annotation.Nullable;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.design.Tiger;
import com.proje.mobilesales.core.enums.CurrselDetailType;
import com.proje.mobilesales.core.enums.DataObjectType;
import com.proje.mobilesales.core.enums.DiscountDetailLevel;
import com.proje.mobilesales.core.enums.DiscountType;
import com.proje.mobilesales.core.enums.ErpInvoiceType;
import com.proje.mobilesales.core.enums.ErpParamType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.IoCode;
import com.proje.mobilesales.core.enums.LineType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.TrackType;
import com.proje.mobilesales.core.enums.TransferOperationName;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.tigerwcf.TigerSelectResult;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import android.text.TextUtils;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.ClCardFicheType;
import com.proje.mobilesales.core.utils.CalculateUtils;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DOMParser;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.FicheTypeControlUtils;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.utils.UnitPriceFormatter;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.CabinTrans;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditX;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCreditDetail;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeed;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.ChequedeedDetail;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.dbmodel.Branch;
import com.proje.mobilesales.features.dbmodel.Discount;
import com.proje.mobilesales.features.dbmodel.Division;
import com.proje.mobilesales.features.dbmodel.Factory;
import com.proje.mobilesales.features.dbmodel.OrderAvailableAmount;
import com.proje.mobilesales.features.dbmodel.Payment;
import com.proje.mobilesales.features.dbmodel.Project;
import com.proje.mobilesales.features.dbmodel.Service;
import com.proje.mobilesales.features.dbmodel.ShipAddress;
import com.proje.mobilesales.features.dbmodel.ShipAgent;
import com.proje.mobilesales.features.dbmodel.ShipType;
import com.proje.mobilesales.features.dbmodel.Specodes;
import com.proje.mobilesales.features.dbmodel.Tradinggrp;
import com.proje.mobilesales.features.dbmodel.Variant;
import com.proje.mobilesales.features.dbmodel.WareHouse;
import com.proje.mobilesales.features.model.CompositeDetail;
import com.proje.mobilesales.features.model.EDocInfoModel;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheDateProp;
import com.proje.mobilesales.features.model.FicheDiscountProp;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.model.ModuleNr;
import com.proje.mobilesales.features.penetration.model.Penetration;
import com.proje.mobilesales.features.product.model.database.Item;
import com.proje.mobilesales.features.product.model.database.ItemPrice;
import com.proje.mobilesales.features.product.model.database.ItemStock;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import com.proje.mobilesales.features.settings.model.enums.MatterArea;
import com.proje.mobilesales.features.todo.model.database.TodoInfoDb;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jetbrains.annotations.UnknownNullability;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

public final class TigerSendDataCreator implements SendDataCreator<TigerServiceResult, TigerSelectResult>, SendBuilder<TigerServiceResult, Object, Object> {
    private static final String TAG = "TigerSendDataCreator";
    private static final String _NS = "";
    private static final String _OUTPUT_ENCODING = "UTF-8";
    private static TigerSendDataCreator instance = new TigerSendDataCreator();
    private static final Object lock = new Object();
    private final String _ENCODING = ContextUtils.getStringResource(R.string.xml_encoding);
    private final String _ATTR_DBOP = ContextUtils.getStringResource(R.string.attr_drop);
    private final String _ATTR_DROP_INS = ContextUtils.getStringResource(R.string.value_drop_ins);
    private final String _ATTR_DROP_UPD = ContextUtils.getStringResource(R.string.value_drop_upt);
    private final String _SALES_ORDER = ContextUtils.getStringResource(R.string.tag_sales_order);
    private final String _ORDER_SLIP = ContextUtils.getStringResource(R.string.tag_order_slip);
    private final String _SALES_INVOICES = ContextUtils.getStringResource(R.string.tag_sales_invoices);
    private final String _INVOICE = ContextUtils.getStringResource(R.string.tag_invoice);
    private final String _SALES_DISPATCHES = ContextUtils.getStringResource(R.string.tag_sales_dispatches);
    private final String _DISPATCHES = ContextUtils.getStringResource(R.string.tag_dispatches);
    private final String _DISPATCH = ContextUtils.getStringResource(R.string.tag_dispatch);
    private final String _SD_TRANSACTIONS = ContextUtils.getStringResource(R.string.tag_sd_transactions);
    private final String _SD_TRANSACTION = ContextUtils.getStringResource(R.string.tag_sd_transaction);
    private final String _CQPN_ROLLS = ContextUtils.getStringResource(R.string.tag_cqpn_rolls);
    private final String _CHQPN_ROLL = ContextUtils.getStringResource(R.string.tag_chqpn_roll);
    private final String _ARP_VOUCHERS = ContextUtils.getStringResource(R.string.tag_arp_vouchers);
    private final String _ARP_VOUCHER = ContextUtils.getStringResource(R.string.tag_arp_voucher);
    private final String _DEMAND_FICHES = ContextUtils.getStringResource(R.string.tag_demand_fiches);
    private final String _DEMAND_VOUCHER = ContextUtils.getStringResource(R.string.tag_demand_voucher);
    private final String _AFFECT_COLLATRL = ContextUtils.getStringResource(R.string.tag_affect_collatrl);
    private final String _NUMBER = ContextUtils.getStringResource(R.string.tag_number);
    private final String _DATA_REFERENCE = ContextUtils.getStringResource(R.string.tag_data_reference);
    private final String _DATE = ContextUtils.getStringResource(R.string.tag_date);
    private final String _TIME = ContextUtils.getStringResource(R.string.tag_time);
    private final String _DOC_TRACK_NR = ContextUtils.getStringResource(R.string.tag_doc_track_nr);
    private final String _DOC_NR = ContextUtils.getStringResource(R.string.tag_doc_nr);
    private final String _AUXIL_CODE = ContextUtils.getStringResource(R.string.tag_auxil_code);
    private final String _AUTH_CODE = ContextUtils.getStringResource(R.string.tag_auth_code);
    private final String _ARP_CODE = ContextUtils.getStringResource(R.string.tag_arp_code);
    private final String _ARP_CODE_SHPM = ContextUtils.getStringResource(R.string.tag_arp_code_shpm);
    private final String _GL_CODE = ContextUtils.getStringResource(R.string.tag_gl_code);
    private final String _GL_CODE1 = ContextUtils.getStringResource(R.string.tag_gl_code1);
    private final String _SOURCE_COST_GRP = ContextUtils.getStringResource(R.string.tag_source_cost_grp);
    private final String _SOURCE_WH = ContextUtils.getStringResource(R.string.tag_source_wh);
    private final String _NOTES1 = ContextUtils.getStringResource(R.string.tag_notes1);
    private final String _NOTES2 = ContextUtils.getStringResource(R.string.tag_notes2);
    private final String _NOTES3 = ContextUtils.getStringResource(R.string.tag_notes3);
    private final String _NOTES4 = ContextUtils.getStringResource(R.string.tag_notes4);
    private final String _SHIPLOC_CODE = ContextUtils.getStringResource(R.string.tag_shiploc_code);
    private final String _PAYMENT_CODE = ContextUtils.getStringResource(R.string.tag_payment_code);
    private final String _DIVISION = ContextUtils.getStringResource(R.string.tag_division);
    private final String _SALESMAN_CODE = ContextUtils.getStringResource(R.string.tag_salesman_code);
    private final String _SHIPMENT_TYPE = ContextUtils.getStringResource(R.string.tag_shipment_type);
    private final String _SHIPPING_AGENT = ContextUtils.getStringResource(R.string.tag_shipping_agent);
    private final String _TRADING_GRP = ContextUtils.getStringResource(R.string.tag_trading_grp);
    private final String _FACTORY = ContextUtils.getStringResource(R.string.tag_factory);
    private final String _CUST_ORD_NO = ContextUtils.getStringResource(R.string.tag_cust_ord_no);
    private final String _PROJECT_CODE = ContextUtils.getStringResource(R.string.tag_project_code);
    private final String _CAMPAIGN_CODE = ContextUtils.getStringResource(R.string.tag_campaign_code);
    private final String _DEPARTMENT = ContextUtils.getStringResource(R.string.tag_department);
    private final String _AFFECT_RISK = ContextUtils.getStringResource(R.string.tag_affect_risk);
    private final String _WITH_PAYMENT = ContextUtils.getStringResource(R.string.tag_with_payment);
    private final String _RC_RATE = ContextUtils.getStringResource(R.string.tag_rc_rate);
    private final String _TC_RATE = ContextUtils.getStringResource(R.string.tag_tc_rate);
    private final String _ORDER_STATUS = ContextUtils.getStringResource(R.string.tag_order_status);
    private final String _GUID = ContextUtils.getStringResource(R.string.tag_guid);
    private final String _CURR_TRANSACTIN = ContextUtils.getStringResource(R.string.tag_curr_transactin);
    private final String _CURRSEL_TOTAL = ContextUtils.getStringResource(R.string.tag_currsel_total);
    private final String _CURRSEL_DETAILS = ContextUtils.getStringResource(R.string.tag_currsel_details);
    private final String _CURRSEL_TRANS = ContextUtils.getStringResource(R.string.tag_currsel_trans);
    private final String _CREATED_BY = ContextUtils.getStringResource(R.string.tag_created_by);
    private final String _DATE_CREATED = ContextUtils.getStringResource(R.string.tag_date_created);
    private final String _HOUR_CREATED = ContextUtils.getStringResource(R.string.tag_hour_created);
    private final String _MIN_CREATED = ContextUtils.getStringResource(R.string.tag_min_created);
    private final String _SEC_CREATED = ContextUtils.getStringResource(R.string.tag_sec_created);
    private final String _APPROVE_NR = ContextUtils.getStringResource(R.string.tag_approve_nr);
    private final String _TRANSACTIONS = ContextUtils.getStringResource(R.string.tag_transactions);
    private final String _TRANSACTION = ContextUtils.getStringResource(R.string.tag_transaction);
    private final String _TYPE = ContextUtils.getStringResource(R.string.tag_type);
    private final String _MASTER_CODE = ContextUtils.getStringResource(R.string.tag_master_code);
    private final String _VARIANT_CODE = ContextUtils.getStringResource(R.string.tag_variant_code);
    private final String _VARIANT_NAME = ContextUtils.getStringResource(R.string.tag_variant_name);
    private final String _CAN_CONFIG = ContextUtils.getStringResource(R.string.tag_can_config);
    private final String _VAT_RATE = ContextUtils.getStringResource(R.string.tag_vat_rate);
    private final String _QUANTITY = ContextUtils.getStringResource(R.string.tag_quantity);
    private final String _PRICE = ContextUtils.getStringResource(R.string.tag_price);
    private final String _UNIT_CODE = ContextUtils.getStringResource(R.string.tag_unit_code);
    private final String _UNIT_CONV1 = ContextUtils.getStringResource(R.string.tag_unit_conv1);
    private final String _CANT_CRE_DEDUCT = ContextUtils.getStringResource(R.string.tag_cant_cre_deduct);
    private final String _UNIT_CONV2 = ContextUtils.getStringResource(R.string.tag_unit_conv2);
    private final String _UNIT_CONV3 = ContextUtils.getStringResource(R.string.tag_unit_conv3);
    private final String _UNIT_CONV4 = ContextUtils.getStringResource(R.string.tag_unit_conv4);
    private final String _UNIT_CONV5 = ContextUtils.getStringResource(R.string.tag_unit_conv5);
    private final String _UNIT_CONV6 = ContextUtils.getStringResource(R.string.tag_unit_conv6);
    private final String _UNIT_CONV7 = ContextUtils.getStringResource(R.string.tag_unit_conv7);
    private final String _UNIT_CONV8 = ContextUtils.getStringResource(R.string.tag_unit_conv8);
    private final String _GROSS_U_INFO1 = ContextUtils.getStringResource(R.string.tag_gross_u_info1);
    private final String _GROSS_U_INFO2 = ContextUtils.getStringResource(R.string.tag_gross_u_info2);
    private final String _CURR_PRICE = ContextUtils.getStringResource(R.string.tag_curr_pice);
    private final String _RC_XRATE = ContextUtils.getStringResource(R.string.tag_rc_xrate);
    private final String _PR_RATE = ContextUtils.getStringResource(R.string.tag_pr_rate);
    private final String _EDT_CURR = ContextUtils.getStringResource(R.string.tag_edt_curr);
    private final String _EDT_PRICE = ContextUtils.getStringResource(R.string.tag_edt_price);
    private final String _PC_PRICE = ContextUtils.getStringResource(R.string.tag_pc_price);
    private final String _EXCLINE_PRICE = ContextUtils.getStringResource(R.string.tag_excline_price);
    private final String _DUE_DATE = ContextUtils.getStringResource(R.string.tag_due_date);
    private final String _ORDER_RESERVE = ContextUtils.getStringResource(R.string.tag_order_reserve);
    private final String _VAT_INCLUDED = ContextUtils.getStringResource(R.string.tag_vat_included);
    private final String _TRANS_DESCRIPTION = ContextUtils.getStringResource(R.string.tag_trans_description);
    private final String _DELVRY_CODE = ContextUtils.getStringResource(R.string.tag_delvry_code);
    private final String _COMPOSITE = ContextUtils.getStringResource(R.string.tag_composite);
    private final String _CURRSEL_TOTALS = ContextUtils.getStringResource(R.string.tag_currsel_totals);
    private final String _TC_XRATE = ContextUtils.getStringResource(R.string.tag_tc_xrate);
    private final String _CURR_INVOICE = ContextUtils.getStringResource(R.string.tag_curr_invoice);
    private final String _EINVOICE = ContextUtils.getStringResource(R.string.tag_einvoice);
    private final String _EINVOICETYP = ContextUtils.getStringResource(R.string.tag_einvoice_typ);
    private final String _STATUS = ContextUtils.getStringResource(R.string.tag_status);
    private final String _CURR_TRANSACTION = ContextUtils.getStringResource(R.string.tag_curr_transaction);
    private final String _DOC_DATE = ContextUtils.getStringResource(R.string.tag_doc_date);
    private final String _ORIG_NUMBER = ContextUtils.getStringResource(R.string.tag_orig_number);
    private final String _POST_FLAGS = ContextUtils.getStringResource(R.string.tag_post_flags);
    private final String _DISP_STATUS = ContextUtils.getStringResource(R.string.tag_disp_status);
    private final String _INVOICE_NUMBER = ContextUtils.getStringResource(R.string.tag_einvoice_number);
    private final String _SALESMANCODE = ContextUtils.getStringResource(R.string.tag_salesmancode);
    private final String _DOC_NUMBER = ContextUtils.getStringResource(R.string.tag_doc_number);
    private final String _EDURATION_TYPE = ContextUtils.getStringResource(R.string.tag_eduration_type);
    private final String _EARCHIVEDETR_INTPAYMENTDATE = ContextUtils.getStringResource(R.string.tag_earchivedetr_intpaymentdate);
    private final String _EARCHIVEDETR_INTPAYMENTTYPE = ContextUtils.getStringResource(R.string.tag_earchivedetr_intpaymenttype);
    private final String _EARCHIVEDETR_INTSALESADDR = ContextUtils.getStringResource(R.string.tag_earchivedetr_intsalesaddr);
    private final String _EARCHIVEDETR_SENDMOD = ContextUtils.getStringResource(R.string.tag_earchivedetr_sendmod);
    private final String _EINVOICE_EINVOICETYPSGK = ContextUtils.getStringResource(R.string.tag_einvoicetypsgk);
    private final String _EINVOICE_TAXPAYERCODE = ContextUtils.getStringResource(R.string.tag_einvoice_taxpayercode);
    private final String _EINVOICE_TAXPAYERNAME = ContextUtils.getStringResource(R.string.tag_einvoice_taxpayername);
    private final String _EINVOICE_DOCUMENTNOSGK = ContextUtils.getStringResource(R.string.tag_einvoice_documentnosgk);
    private final String _ESTARTDATE = ContextUtils.getStringResource(R.string.tag_einvoice_estartdate);
    private final String _EENDDATE = ContextUtils.getStringResource(R.string.tag_einvoice_eenddate);
    private final String _EINVOICE_STARTDATE = ContextUtils.getStringResource(R.string.tag_order_einvoice_estartdate);
    private final String _EINVOICE_ENDDATE = ContextUtils.getStringResource(R.string.tag_order_einvoice_eenddate);
    private final String _EARCHIVEDETR_ISPERCURR = ContextUtils.getStringResource(R.string.tag_earchivedetr_isperrcurr);
    private final String _EARCHIVEDETR_INSTEADOFDESP = ContextUtils.getStringResource(R.string.tag_earchivedetr_insteadofdesp);
    private final String _EINVOICE_PROFILEID = ContextUtils.getStringResource(R.string.tag_einvoice_profile_id);
    private final String _PROFILE_ID = ContextUtils.getStringResource(R.string.tag_profile_id);
    private final String _SALEMANCODE = ContextUtils.getStringResource(R.string.tag_salemancode);
    private final String _SOURCEINDEX = ContextUtils.getStringResource(R.string.tag_sourceindex);
    private final String _SOURCECOSTGRP = ContextUtils.getStringResource(R.string.tag_sourcecostgrp);
    private final String _DISPATCH_NUMBER = ContextUtils.getStringResource(R.string.tag_dispatch_number);
    private final String _VARIANTNAME = ContextUtils.getStringResource(R.string.tag_variantname);
    private final String _VARIANTCODE = ContextUtils.getStringResource(R.string.tag_variantcode);
    private final String _BILLED = ContextUtils.getStringResource(R.string.tag_billed);
    private final String _RET_COST_TYPE = ContextUtils.getStringResource(R.string.tag_ret_cost_type);
    private final String _DESCRIPTION = ContextUtils.getStringResource(R.string.tag_description);
    private final String _EXCHLINE_PRICE = ContextUtils.getStringResource(R.string.tag_exchline_price);
    private final String _ORDER_REFERENCE = "ORDER_REFERENCE";
    private final String _DIST_ORD_REFERENCE = "DIST_ORD_REFERENCE";
    private final String _SL_DETAILS = ContextUtils.getStringResource(R.string.tag_sl_details);
    private final String _SERIAL_LOT_TRN = ContextUtils.getStringResource(R.string.tag_seri_lot_trn);
    private final String _SL_TYPE = ContextUtils.getStringResource(R.string.tag_sl_type);
    private final String _SL_CODE = ContextUtils.getStringResource(R.string.tag_sl_code);
    private final String _MU_QUANTITY = ContextUtils.getStringResource(R.string.tag_mu_quantity);
    private final String _IOCODE = ContextUtils.getStringResource(R.string.tag_iocode);
    private final String _SOURCE_SLT_REFERENCE = ContextUtils.getStringResource(R.string.tag_source_slt_reference);
    private final String _DATE_EXPIRED = ContextUtils.getStringResource(R.string.tag_date_expired);
    private final String _GRP_BEG_CODE = ContextUtils.getStringResource(R.string.tag_grp_beg_code);
    private final String _GRP_END_CODE = ContextUtils.getStringResource(R.string.tag_grp_end_code);
    private final String _LOCATION_CODE = ContextUtils.getStringResource(R.string.tag_location_code);
    private final String _SOURCE_MT_REFERENCE = ContextUtils.getStringResource(R.string.tag_source_mt_reference);
    private final String _SOURCE_QUANTITY = ContextUtils.getStringResource(R.string.tag_source_quantity);
    private final String _DISCOUNT_RATE = ContextUtils.getStringResource(R.string.tag_discount_rate);
    private final String _TOTAL = ContextUtils.getStringResource(R.string.tag_total);
    private final String _DETAIL_LEVEL = ContextUtils.getStringResource(R.string.tag_detail_level);
    private final String _CALC_TYPE = ContextUtils.getStringResource(R.string.tag_calc_type);
    private final String _CAMPAIGN_INFOS = ContextUtils.getStringResource(R.string.tag_campaign_infos);
    private final String _CAMPAIGN_INFO = ContextUtils.getStringResource(R.string.tag_campaign_info);
    private final String _CAMPCODE1 = ContextUtils.getStringResource(R.string.tag_campcode1);
    private final String _CAMPCODE2 = ContextUtils.getStringResource(R.string.tag_campcode2);
    private final String _CAMPCODE3 = ContextUtils.getStringResource(R.string.tag_campcode3);
    private final String _CAMPCODE4 = ContextUtils.getStringResource(R.string.tag_campcode4);
    private final String _CAMPCODE5 = ContextUtils.getStringResource(R.string.tag_campcode5);
    private final String _CAMP_LN_NO = ContextUtils.getStringResource(R.string.tag_camp_ln_no);
    private final String _DEFNFLDS = ContextUtils.getStringResource(R.string.tag_defnflds);
    private final String _DISCEXP_CALC = ContextUtils.getStringResource(R.string.tag_discexp_calc);
    private final String _SD_CODE = ContextUtils.getStringResource(R.string.tag_sd_code);
    private final String _SD_CODE_CROSS = ContextUtils.getStringResource(R.string.tag_sd_code_cross);
    private final String _SD_NUMBER_CROSS = ContextUtils.getStringResource(R.string.tag_sd_number_cross);
    private final String _CROSS_DATA_REFERENCE = ContextUtils.getStringResource(R.string.tag_cross_data_reference);
    private final String _AMOUNT = ContextUtils.getStringResource(R.string.tag_amount);
    private final String _TC_AMOUNT = ContextUtils.getStringResource(R.string.tag_tc_amount);
    private final String _RC_AMOUNT = ContextUtils.getStringResource(R.string.tag_rc_amount);
    private final String _ATTACHMENT_ARP = ContextUtils.getStringResource(R.string.tag_attachment_arp);
    private final String _TRANNO = ContextUtils.getStringResource(R.string.tag_tranno);
    private final String _CREDIT = ContextUtils.getStringResource(R.string.tag_credit);
    private final String _HOUR = ContextUtils.getStringResource(R.string.tag_hour);
    private final String _MINUTE = ContextUtils.getStringResource(R.string.tag_minute);
    private final String _MASTER_TITLE = ContextUtils.getStringResource(R.string.tag_master_title);
    private final String _CURR_TRANS = ContextUtils.getStringResource(R.string.tag_curr_trans);
    private final String _TRCURR = ContextUtils.getStringResource(R.string.tag_trcurr);
    private final String _TRRATE = ContextUtils.getStringResource(R.string.tag_trrate);
    private final String _REPORTRATE = ContextUtils.getStringResource(R.string.tag_reportrate);
    private final String _MASTER_MODULE = ContextUtils.getStringResource(R.string.tag_master_module);
    private final String _DOCUMENT_COUNT = ContextUtils.getStringResource(R.string.tag_document_count);
    private final String _COUNTRY = ContextUtils.getStringResource(R.string.tag_country);
    private final String _COUNTRYCODE = ContextUtils.getStringResource(R.string.tag_country_code);
    private final String _CITY = ContextUtils.getStringResource(R.string.tag_city);
    private final String _CITYCODE = ContextUtils.getStringResource(R.string.tag_city_code);
    private final String _TOWN = ContextUtils.getStringResource(R.string.tag_town);
    private final String _TOWNCODE = ContextUtils.getStringResource(R.string.tag_town_code);
    private final String _OWING = ContextUtils.getStringResource(R.string.tag_owing);
    private final String _SERIAL_NR = ContextUtils.getStringResource(R.string.tag_serial_nr);
    private final String _BANK_TITLE = ContextUtils.getStringResource(R.string.tag_bank_title);
    private final String _DIVISION_NO = ContextUtils.getStringResource(R.string.tag_division_no);
    private final String _ACCOUNT_NO = ContextUtils.getStringResource(R.string.tag_account_no);
    private final String _GUARANTOR = ContextUtils.getStringResource(R.string.tag_guarantor);
    private final String _STAMP_FEE = ContextUtils.getStringResource(R.string.tag_stamp_fee);
    private final String _TC_TOTAL = ContextUtils.getStringResource(R.string.tag_tc_total);
    private final String _RC_TOTAL = ContextUtils.getStringResource(R.string.tag_rc_total);
    private final String _TAX_NR = ContextUtils.getStringResource(R.string.tag_tax_nr);
    private final String _TOTAL_CREDIT = ContextUtils.getStringResource(R.string.tag_total_credit);
    private final String _BANKACC_CODE = ContextUtils.getStringResource(R.string.tag_bankacc_code);
    private final String _PAYMENT_LIST = ContextUtils.getStringResource(R.string.tag_payment_list);
    private final String _PAYMENT = ContextUtils.getStringResource(R.string.tag_payment);
    private final String _MODULENR = ContextUtils.getStringResource(R.string.tag_module_nr);
    private final String _PAY_NO = ContextUtils.getStringResource(R.string.tag_pay_no);
    private final String _CREDIT_CARD_NO = ContextUtils.getStringResource(R.string.tag_credit_card_no);
    private final String _TRCODE = ContextUtils.getStringResource(R.string.tag_trcode);
    private final String _SOURCE_INDEX = ContextUtils.getStringResource(R.string.tag_source_index);
    private final String _REAL_SRC_INDEX = ContextUtils.getStringResource(R.string.tag_real_src_index);
    private final String _BRANCH = ContextUtils.getStringResource(R.string.tag_branch);
    private final String _LINE_TYPE = ContextUtils.getStringResource(R.string.tag_line_type);
    private final String _ITEM_CODE = ContextUtils.getStringResource(R.string.tag_item_code);
    private final String _FACTORY_NR = ContextUtils.getStringResource(R.string.tag_factory_nr);
    private final String _DO_CODE = ContextUtils.getStringResource(R.string.tag_do_code);
    private final String _USER_NO = ContextUtils.getStringResource(R.string.tag_user_no);
    private final String _PROCURE_DATE = ContextUtils.getStringResource(R.string.tag_procure_date);
    private final String _LINE_CNT = ContextUtils.getStringResource(R.string.tag_line_cnt);
    private final String _EMPLOYEE_CODE = ContextUtils.getStringResource(R.string.tag_employee_code);
    private final String _FICHE_DATE = ContextUtils.getStringResource(R.string.tag_fiche_date);
    private final String _LINE_NO = ContextUtils.getStringResource(R.string.tag_line_no);
    private final String _USER_NAME = ContextUtils.getStringResource(R.string.tag_user_name);
    private final String _MEET_TYPE = ContextUtils.getStringResource(R.string.tag_meet_type);
    private final String _ADD_TAX_RATE = ContextUtils.getStringResource(R.string.tag_add_tax_rate);
    private final String _ADD_TAX_AMOUNT = ContextUtils.getStringResource(R.string.tag_add_tax_amount);
    private final String _ADD_TAX_CONV_FACT = ContextUtils.getStringResource(R.string.tag_add_tax_conv_fact);
    private final String _ADD_TAX_DISC_AMNT = ContextUtils.getStringResource(R.string.tag_add_tax_disc_amount);
    private final String _ADD_TAX_AMNTISUPD = ContextUtils.getStringResource(R.string.tag_add_tax_amntisupd);
    private final String _ADD_TAX_CODE = ContextUtils.getStringResource(R.string.tag_add_tax_code);
    private final String _ADD_TAX_GLOBAL_CODE = ContextUtils.getStringResource(R.string.tag_add_tax_globalcode);
    private final String _ADD_TAX_EFFECT_KDV = ContextUtils.getStringResource(R.string.tag_add_tax_effect_kdv);
    private final String _SALESMANREF = ContextUtils.getStringResource(R.string.tag_salesman_ref);
    private final String _PRCLISTCODE = ContextUtils.getStringResource(R.string.tag_price_list_code);
    private final String _PRCLISTTYPE = ContextUtils.getStringResource(R.string.tag_price_list_type);
    private final String _BNLN_TC_XRATE = ContextUtils.getStringResource(R.string.tag_bnln_tc_xrate);
    private final String _BNLN_TC_AMOUNT = ContextUtils.getStringResource(R.string.tag_bnln_tc_amount);
    private final String _SHIP_DATE = ContextUtils.getStringResource(R.string.tag_ship_date);
    private final String _SHIP_TIME = ContextUtils.getStringResource(R.string.tag_ship_time);
    private final String _EDESPATCH = ContextUtils.getStringResource(R.string.tag_edespatch);
    private final String _EDESPATCH_PROFILEID = ContextUtils.getStringResource(R.string.tag_edespatch_profileId);
    private final String _MATERIAL_SLIPS = ContextUtils.getStringResource(R.string.tag_material_slips);
    private final String _SLIP = ContextUtils.getStringResource(R.string.tag_slip);
    private final String _DEST_WH = ContextUtils.getStringResource(R.string.tag_dest_wh);
    private final String _DEST_COST_GRP = ContextUtils.getStringResource(R.string.tag_dest_cost_grp);
    private final String _SOURCE_FACTORY_NR = ContextUtils.getStringResource(R.string.tag_src_factory_nr);
    private final String _SOURCE_DEPARTMENT_NR = ContextUtils.getStringResource(R.string.tag_src_dept_nr);
    private final String _SOURCE_DIVISION_NR = ContextUtils.getStringResource(R.string.tag_src_div_nr);
    private final String _DEST_DIVISION_NR = ContextUtils.getStringResource(R.string.tag_dest_div_nr);
    private final String _DEST_DEPARTMENT_NR = ContextUtils.getStringResource(R.string.tag_dest_dept_nr);
    private final String _DEST_FACTORY = ContextUtils.getStringResource(R.string.tag_dest_factory);
    private final String _FOOTNOTE1 = ContextUtils.getStringResource(R.string.tag_footnote1);
    private final String _FOOTNOTE2 = ContextUtils.getStringResource(R.string.tag_footnote2);
    private final String _FOOTNOTE3 = ContextUtils.getStringResource(R.string.tag_footnote3);
    private final String _FOOTNOTE4 = ContextUtils.getStringResource(R.string.tag_footnote4);
    private final String _DESTINDEX = ContextUtils.getStringResource(R.string.tag_destIndex);
    private final String _DESTCOSTGRP = ContextUtils.getStringResource(R.string.tag_destCostGrp);
    private final String _DOC_TIME = ContextUtils.getStringResource(R.string.tag_docTime);
    private final String _EINVOICE_DRIVERNAME1 = ContextUtils.getStringResource(R.string.tag_eInvoice_driverName1);
    private final String _EINVOICE_DRIVERSURNAME1 = ContextUtils.getStringResource(R.string.tag_eInvoice_driverSurname1);
    private final String _EINVOICE_DRIVERTCKNO1 = ContextUtils.getStringResource(R.string.tag_eInvoice_driverTckno1);
    private final String _EINVOICE_PLATENUM1 = ContextUtils.getStringResource(R.string.tag_eInvoice_platenum1);
    private final String _EINVOICE_CHASSISNUM1 = ContextUtils.getStringResource(R.string.tag_eInvoice_chassisnum1);
    private final String _EINVOICE_DRIVERNAME2 = ContextUtils.getStringResource(R.string.tag_eInvoice_driverName2);
    private final String _EINVOICE_DRIVERSURNAME2 = ContextUtils.getStringResource(R.string.tag_eInvoice_driverSurname2);
    private final String _EINVOICE_DRIVERTCKNO2 = ContextUtils.getStringResource(R.string.tag_eInvoice_driverTckno2);
    private final String _EINVOICE_PLATENUM2 = ContextUtils.getStringResource(R.string.tag_eInvoice_platenum2);
    private final String _EINVOICE_CHASSISNUM2 = ContextUtils.getStringResource(R.string.tag_eInvoice_chassisnum2);
    private final String _EINVOICE_DRIVERNAME3 = ContextUtils.getStringResource(R.string.tag_eInvoice_driverName3);
    private final String _EINVOICE_DRIVERSURNAME3 = ContextUtils.getStringResource(R.string.tag_eInvoice_driverSurname3);
    private final String _EINVOICE_DRIVERTCKNO3 = ContextUtils.getStringResource(R.string.tag_eInvoice_driverTckno3);
    private final String _EINVOICE_PLATENUM3 = ContextUtils.getStringResource(R.string.tag_eInvoice_platenum3);
    private final String _EINVOICE_CHASSISNUM3 = ContextUtils.getStringResource(R.string.tag_eInvoice_chassisnum3);
    private final String _DEST_LOCATION_CODE = ContextUtils.getStringResource(R.string.tag_dest_locationCode);
    private final String _EARCHIVEDETR_DRIVERNAME1 = ContextUtils.getStringResource(R.string.tag_eArchiveTr_driverName1);
    private final String _EARCHIVEDETR_DRIVERSURNAME1 = ContextUtils.getStringResource(R.string.tag_eArchiveTr_driverSurname1);
    private final String _EARCHIVEDETR_DRIVERTCKNO1 = ContextUtils.getStringResource(R.string.tag_eArchiveTr_driverTckno1);
    private final String _EARCHIVEDETR_PLATENUM1 = ContextUtils.getStringResource(R.string.tag_eArchiveTr_platenum1);
    private final String _EARCHIVEDETR_CHASSISNUM1 = ContextUtils.getStringResource(R.string.tag_eArchiveTr_chassisnum1);
    private final String _EARCHIVEDETR_DRIVERNAME2 = ContextUtils.getStringResource(R.string.tag_eArchiveTr_driverName2);
    private final String _EARCHIVEDETR_DRIVERSURNAME2 = ContextUtils.getStringResource(R.string.tag_eArchiveTr_driverSurname2);
    private final String _EARCHIVEDETR_DRIVERTCKNO2 = ContextUtils.getStringResource(R.string.tag_eArchiveTr_driverTckno2);
    private final String _EARCHIVEDETR_PLATENUM2 = ContextUtils.getStringResource(R.string.tag_eArchiveTr_platenum2);
    private final String _EARCHIVEDETR_CHASSISNUM2 = ContextUtils.getStringResource(R.string.tag_eArchiveTr_chassisnum2);
    private final String _EARCHIVEDETR_DRIVERNAME3 = ContextUtils.getStringResource(R.string.tag_eArchiveTr_driverName3);
    private final String _EARCHIVEDETR_DRIVERSURNAME3 = ContextUtils.getStringResource(R.string.tag_eArchiveTr_driverSurname3);
    private final String _EARCHIVEDETR_DRIVERTCKNO3 = ContextUtils.getStringResource(R.string.tag_eArchiveTr_driverTckno3);
    private final String _EARCHIVEDETR_PLATENUM3 = ContextUtils.getStringResource(R.string.tag_eArchiveTr_platenum3);
    private final String _EARCHIVEDETR_CHASSISNUM3 = ContextUtils.getStringResource(R.string.tag_eArchiveTr_chassisnum3);
    private final String _EINSTEAD_OF_DISPATCH = ContextUtils.getStringResource(R.string.tag_einstead_of_dispatch);
    private final String _CL_TR_CURR = ContextUtils.getStringResource(R.string.tag_cl_tr_curr);
    private final String _CL_TR_RATE = ContextUtils.getStringResource(R.string.tag_cl_tr_rate);
    private final String _CL_TR_NET = ContextUtils.getStringResource(R.string.tag_cl_tr_net);
    private final String _LINE_EXP = ContextUtils.getStringResource(R.string.tag_line_exp);
    private final String _UPDCURR_ORDER = ContextUtils.getStringResource(R.string.tag_upd_curr_order);
    private final String _UPDTRCURR_ORDER = ContextUtils.getStringResource(R.string.tag_upd_tr_curr_order);
    private final String _UPDCURR_DISPATCH = ContextUtils.getStringResource(R.string.tag_upd_curr_dispatch);
    private final String _UPDTRCURR_DISPATCH = ContextUtils.getStringResource(R.string.tag_upd_tr_curr_dispatch);
    private final String _CANDEDUCT = ContextUtils.getStringResource(R.string.tag_can_deduct);
    private final String _DEDUCTCODE = ContextUtils.getStringResource(R.string.tag_deduct_code);
    private final String _SALEDEDUCTPART1 = ContextUtils.getStringResource(R.string.tag_sales_deduct_part_1);
    private final String _SALEDEDUCTPART2 = ContextUtils.getStringResource(R.string.tag_sales_deduct_part_2);
    private final String _REM_QUANTITY = ContextUtils.getStringResource(R.string.tag_rem_quantity);
    private final String _SALEDEDUCTPART2 = ContextUtils.getStringResource(R.string.tag_sales_deduct_part_2);
    private final String _REM_QUANTITY = ContextUtils.getStringResource(R.string.tag_rem_quantity);
    private final String _LU_REM_QUANTITY = ContextUtils.getStringResource(R.string.tag_lu_rem_quantity);
    private final String _SITE_ID = ContextUtils.getStringResource(R.string.tag_site_id);
    public TigerServiceResult buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, int i2, String str, String str2, Object obj, Object obj2, int i3) {
        return null;
    }
    public TigerServiceResult buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, String str, int i2, String str2, Object obj, Object obj2, int i3) {
        return null;
    }
    public TigerServiceResult buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, String str, String str2, int i2, String str3, String str4, Object obj, int i3) {
        return null;
    }
    public TigerServiceResult buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, String str, String str2, String str3, Object obj, Object obj2, int i2) {
        return null;
    }

    private TigerSendDataCreator() {
    }

    public static TigerSendDataCreator getInstance() {
        synchronized (lock) {
            try {
                if (instance == null) {
                    instance = new TigerSendDataCreator();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return instance;
    }
    public TigerServiceResult getDemand(Sales sales, int i2) {
        IOException e2;
        String str;
        ArrayList<CompositeDetail> compositeDetails;
        TimingLogger timingLogger = new TimingLogger("CreateXml", "getDemandToXml");
        timingLogger.addSplit("Start Demand Xml Create ");
        String str2 = "";
        try {
            try {
                BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                XmlSerializer createXmlSerializer = createXmlSerializer(byteArrayOutputStream, _OUTPUT_ENCODING);
                createXmlSerializer.startDocument(this._ENCODING, null);
                startTag(createXmlSerializer, this._DEMAND_FICHES);
                startTag(createXmlSerializer, this._DEMAND_VOUCHER);
                if (sales.getTransferCount() > 0) {
                    try {
                        addAttribute(createXmlSerializer, this._ATTR_DBOP, this._ATTR_DROP_UPD);
                        addElement(createXmlSerializer, this._DATA_REFERENCE, sales.getFicheRef());
                    } catch (IOException e3) {
                        e2 = e3;
                        e2.printStackTrace();
                        return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDREQEST, i2, sales.getGDate(), sales.getAndFicheNo(), str2, sales, sales.getLogicalRef());
                    }
                } else {
                    addAttribute(createXmlSerializer, this._ATTR_DBOP, this._ATTR_DROP_INS);
                }
                addElement(createXmlSerializer, this._NUMBER, "~");
                addElement(createXmlSerializer, this._DATE, sales.getFicheCreateDate());
                addElement(createXmlSerializer, this._TIME, DateAndTimeUtils.getLogoDateTimeCode(sales.getGDate()));
                addElement(createXmlSerializer, this._DO_CODE, sales.getDocumentNo().toString());
                addElement(createXmlSerializer, this._AUTH_CODE, sales.getWarrantyCode().toString());
                addElement(createXmlSerializer, this._AUXIL_CODE, sales.getSpeCode().toString());
                addElement(createXmlSerializer, this._SOURCE_INDEX, sales.getWareHouse().getLogicalRef());
                addElement(createXmlSerializer, this._BRANCH, sales.getBranch().getLogicalRef());
                addElement(createXmlSerializer, this._DEPARTMENT, sales.getDivision().getLogicalRef());
                addElement(createXmlSerializer, this._FACTORY_NR, sales.getFactory().getLogicalRef());
                addElement(createXmlSerializer, this._PROJECT_CODE, baseErp.getLogoSqlHelper().getProjectCode(sales.getProjectCode().getLogicalRef()));
                addElement(createXmlSerializer, this._STATUS, baseErp.getDemandFicheStatus());
                addElement(createXmlSerializer, this._CREATED_BY, baseErp.getUser().getLogoNr());
                Calendar instance2 = Calendar.getInstance();
                addElement(createXmlSerializer, this._DATE_CREATED, DateAndTimeUtils.getDateToFormat(instance2, "dd/MM/yyyy"));
                addElement(createXmlSerializer, this._HOUR_CREATED, instance2.get(11));
                addElement(createXmlSerializer, this._MIN_CREATED, instance2.get(12));
                addElement(createXmlSerializer, this._SEC_CREATED, instance2.get(13));
                addElement(createXmlSerializer, this._USER_NO, baseErp.getUser().getLogoNr());
                startTag(createXmlSerializer, this._TRANSACTIONS);
                Iterator<SalesDetail> it = sales.getMSalesDetailList().iterator();
                char c2 = 0;
                int i3 = 0;
                while (it.hasNext()) {
                    SalesDetail next = it.next();
                    startTag(createXmlSerializer, this._TRANSACTION);
                    addElement(createXmlSerializer, this._LINE_TYPE, next.getLineType());
                    addElement(createXmlSerializer, this._ITEM_CODE, next.getCode());
                    if (next.getVariant().getLogicalRef() != -1) {
                        String[] variantCode = baseErp.getLogoSqlHelper().getVariantCode(next.getVariant().getLogicalRef());
                        addElement(createXmlSerializer, this._VARIANT_CODE, variantCode[c2]);
                        addElement(createXmlSerializer, this._VARIANT_NAME, variantCode[1]);
                        addElement(createXmlSerializer, this._CAN_CONFIG, true);
                    }
                    addElement(createXmlSerializer, this._AMOUNT, next.getAmount().getDefinitionDouble());
                    int logicalRef = baseErp.changeDemandLinesWarehouse() ? next.getWareHouse().getLogicalRef() : sales.getSourceWareHouse().getLogicalRef();
                    addElement(createXmlSerializer, this._SOURCE_INDEX, logicalRef);
                    addElement(createXmlSerializer, this._REAL_SRC_INDEX, sales.getWareHouse().getLogicalRef());
                    addElement(createXmlSerializer, this._UNIT_CODE, baseErp.getLogoSqlHelper().getUnitCode(next.getUnit().getLogicalRef(), next.isProduct()));
                    addElement(createXmlSerializer, this._UNIT_CONV1, next.getConvFact1());
                    addElement(createXmlSerializer, this._UNIT_CONV2, next.getConvFact2());
                    addElement(createXmlSerializer, this._DESCRIPTION, next.getExplanation().toString());
                    addElement(createXmlSerializer, this._PRICE, next.getCalculateCurrPrice());
                    addElement(createXmlSerializer, this._PROCURE_DATE, next.getDeliveryDate().toString());
                    i3++;
                    addElement(createXmlSerializer, this._LINE_NO, i3);
                    addElement(createXmlSerializer, this._USER_NAME, baseErp.getUser().getLogoUserName());
                    addElement(createXmlSerializer, this._MEET_TYPE, 2);
                    addElement(createXmlSerializer, this._FACTORY_NR, sales.getFactory().getLogicalRef());
                    addElement(createXmlSerializer, this._STATUS, baseErp.getDemandFicheStatus());
                    endTag(createXmlSerializer, this._TRANSACTION);
                    if (next.getLineType() == LineType.COMPOSITE_COLI.value && (compositeDetails = ISqlManager.getCompositeDetails(next.getItemRef())) != null && compositeDetails.size() > 0) {
                        Iterator<CompositeDetail> it2 = compositeDetails.iterator();
                        while (it2.hasNext()) {
                            CompositeDetail next2 = it2.next();
                            startTag(createXmlSerializer, this._TRANSACTION);
                            addElement(createXmlSerializer, this._LINE_TYPE, LineType.COMPOSITE_COLI_DETAIL.value);
                            double amount = next2.getAmount() * next.getAmount().getDefinitionDouble();
                            addElement(createXmlSerializer, this._AMOUNT, amount);
                            addElement(createXmlSerializer, this._ITEM_CODE, next2.getCode());
                            addElement(createXmlSerializer, this._UNIT_CODE, next2.getUnitCode());
                            str = str2;
                            try {
                                addElement(createXmlSerializer, this._UNIT_CONV1, next2.getConvFact1());
                                addElement(createXmlSerializer, this._UNIT_CONV2, next2.getConvFact2());
                                addVariantInfoForCompositColi(next2, createXmlSerializer);
                                addElement(createXmlSerializer, this._SOURCE_INDEX, sales.getWareHouse().getLogicalRef());
                                addElement(createXmlSerializer, this._REAL_SRC_INDEX, logicalRef);
                                addElement(createXmlSerializer, this._PRICE, (next.getAmount().getDefinitionDouble() * (next2.getPerc() * next.getPrice().getDefinitionDouble())) / (amount * 100.0d));
                                addElement(createXmlSerializer, this._STATUS, baseErp.getDemandFicheStatus());
                                endTag(createXmlSerializer, this._TRANSACTION);
                                it = it;
                                str2 = str;
                                byteArrayOutputStream = byteArrayOutputStream;
                            } catch (IOException e4) {
                                e2 = e4;
                                str2 = str;
                                e2.printStackTrace();
                                return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDREQEST, i2, sales.getGDate(), sales.getAndFicheNo(), str2, sales, sales.getLogicalRef());
                            }
                        }
                    }
                    it = it;
                    str2 = str2;
                    byteArrayOutputStream = byteArrayOutputStream;
                    c2 = 0;
                }
                str = str2;
                endTag(createXmlSerializer, this._TRANSACTIONS);
                addElement(createXmlSerializer, this._LINE_CNT, i3);
                endTag(createXmlSerializer, this._DEMAND_VOUCHER);
                endTag(createXmlSerializer, this._DEMAND_FICHES);
                createXmlSerializer.endDocument();
                timingLogger.addSplit("Start Finish Demand Xml Create ");
                str2 = byteArrayOutputStream.toString();
                Log.d(MobileSales.TAG, str2);
                createXmlSerializer.flush();
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
            } catch (IOException e5) {
                e2 = e5;
            }
            return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDREQEST, i2, sales.getGDate(), sales.getAndFicheNo(), str2, sales, sales.getLogicalRef());
        } finally {
            timingLogger.dumpToLog();
        }
    }

    private String getArpCode(Sales sales) {
        if (!TextUtils.isEmpty(sales.getClCode())) {
            return sales.getClCode();
        }
        sales.setClCode(ErpCreator.getInstance().getmBaseErp().getCustomerClCode(sales.getClRef()));
        return sales.getClCode();
    }

    public TigerServiceResult getOrder(Sales sales) {
        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.core.data.TigerSendDataCreator.getOrder(com.proje.mobilesales.features.sales.model.Sales):com.proje.mobilesales.core.tigerwcf.TigerServiceResult");
    }


    public TigerServiceResult getInvoice(@UnknownNullability PrintSlipModel sales) {
        return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDINVOICE, sales.getClRef(), sales.getGDate(), sales.getCampaignWithControl(), sales.getAndFicheNo(), getInvoiceXml(sales), (Object) sales, sales.getLogicalRef());
    }

    public TigerServiceResult getReturnInvoice(Sales sales) {
        return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDINVOICE, sales.getClRef(), sales.getGDate(), sales.getCampaignWithControl(), sales.getAndFicheNo(), getInvoiceXml(sales), sales, sales.getLogicalRef());
    }

    private String getInvoiceXml(Sales r49) {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.core.data.TigerSendDataCreator.getInvoiceXml(com.proje.mobilesales.features.sales.model.Sales):java.lang.String");
    }

    private double calculateSourceQuantityForSeriLot(int i2, SalesDetail salesDetail, double d2) {
        List table;
        if (salesDetail.getConvFact1() == 0.0d || (table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ItemUnits.class, "LOGICALREF=? AND ITEMREF=?", new String[]{String.valueOf(i2), String.valueOf(salesDetail.getItemRef())})) == null || table.size() <= 0 || ((ItemUnits) table.get(0)).convfact2 == 0.0d) {
            return 0.0d;
        }
        return (d2 * ((ItemUnits) table.get(0)).convfact1) / ((ItemUnits) table.get(0)).convfact2;
    }

    private void AddPriceTags(SalesDetail salesDetail, XmlSerializer xmlSerializer) {
        int i2;
        try {
            BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
            if (salesDetail.getSelectedPrice().getLogicalRef() == -1 && salesDetail.previousPriceRef <= 0) {
                return;
            }
            ISqlHelper logoSqlHelper = baseErp.getLogoSqlHelper();
            if (salesDetail.getSelectedPrice().getLogicalRef() > 0) {
                i2 = salesDetail.getSelectedPrice().getLogicalRef();
            } else {
                i2 = salesDetail.previousPriceRef;
            }
            List table = logoSqlHelper.getTable(ItemPrice.class, "LOGICALREF=?", new String[]{StringUtils.convertIntToString(i2)});
            if (table != null && table.size() > 0) {
                addElement(xmlSerializer, this._PRCLISTCODE, ((ItemPrice) table.get(0)).priceCode);
                addElement(xmlSerializer, this._PRCLISTTYPE, ((ItemPrice) table.get(0)).ptype);
            }
        } catch (Exception e2) {
            Log.e(TAG, "AddPriceTags error", e2);
        }
    }


    public TigerServiceResult getDispatch(Sales sales) {
        return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDDISPATCH, sales.getClRef(), sales.getGDate(), sales.getCampaignWithControl(), sales.getAndFicheNo(), getDispatchXml(sales), sales, sales.getLogicalRef());
    }


    public TigerServiceResult getReturnDispatch(Sales sales) {
        return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDDISPATCH, sales.getClRef(), sales.getGDate(), sales.getCampaignWithControl(), sales.getAndFicheNo(), getDispatchXml(sales), sales, sales.getLogicalRef());
    }

    private int getDetailWareHouse(SalesDetail salesDetail, Sales sales) {
        if (ErpCreator.getInstance().getmBaseErp().changeSalesLinesWarehouse()) {
            return salesDetail.getWareHouse().getLogicalRef();
        }
        if (SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(sales.getmSalesType())) {
            return sales.getReturnWareHouse().getLogicalRef();
        }
        return sales.getWareHouse().getLogicalRef();
    }
    private String getDispatchXml(Sales r45) {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.core.data.TigerSendDataCreator.getDispatchXml(com.proje.mobilesales.features.sales.model.Sales):java.lang.String");
    }

    private void getDispatchToXmlHeader(BaseErp baseErp, XmlSerializer xmlSerializer, Sales sales) throws IOException {
        ErpInvoiceType erpInvoiceType;
        int i2;
        String str;
        if (SalesUtils.isSalesTypeInvoiceOrDispatch(sales.getmSalesType())) {
            if (sales.getConsignee().isSelect()) {
                addElement(xmlSerializer, this._TYPE, 9);
            } else {
                addElement(xmlSerializer, this._TYPE, 8);
            }
        } else if (SalesUtils.isSalesTypeReturnInvoiceOrReturnDispatch(sales.getmSalesType())) {
            if (sales.getConsignee().isSelect()) {
                addElement(xmlSerializer, this._TYPE, 4);
            } else {
                addElement(xmlSerializer, this._TYPE, 3);
            }
        } else if (SalesUtils.isSalesTypeOnlyRetailInvoice(sales.getmSalesType())) {
            addElement(xmlSerializer, this._TYPE, 7);
        } else if (SalesUtils.isSalesTypeOnlyRetailReturnInvoice(sales.getmSalesType())) {
            addElement(xmlSerializer, this._TYPE, 2);
        }
        MatterSettings matterSettings = baseErp.getMatterSettings(ContextUtils.getmContext(), sales.getFicheType(sales.getmSalesType()));
        if (!matterSettings.isUseMatterNo()) {
            addElement(xmlSerializer, this._NUMBER, "~");
        } else if (matterSettings.getMatterArea() == MatterArea.FICHE_NO) {
            String str2 = this._NUMBER;
            if (sales.getFicheNo().equals("")) {
                str = "~";
            } else {
                str = sales.getFicheNo();
            }
            addElement(xmlSerializer, str2, str);
        }
        addElement(xmlSerializer, this._DATE, sales.getFicheCreateDate());
        addElement(xmlSerializer, this._TIME, DateAndTimeUtils.getLogoDateTimeCode(sales.getGDate()));
        addElement(xmlSerializer, this._DOC_TRACK_NR, sales.getDocumentTrackingNo().toString());
        addElement(xmlSerializer, this._DOC_NUMBER, sales.getDocumentNo().toString());
        addElement(xmlSerializer, this._AUTH_CODE, sales.getWarrantyCode().toString());
        addElement(xmlSerializer, this._AUXIL_CODE, sales.getSpeCode().toString());
        addElement(xmlSerializer, this._ARP_CODE, getArpCode(sales));
        addElement(xmlSerializer, this._GL_CODE, baseErp.getLogoSqlHelper().getMuhAccCode(sales.getClRef()));
        addElement(xmlSerializer, this._ARP_CODE_SHPM, baseErp.getLogoSqlHelper().getClCode(sales.getShipAccount().getLogicalRef()));
        int wareHouseCostGrp = baseErp.getLogoSqlHelper().getWareHouseCostGrp(sales.getWareHouse().getLogicalRef());
        int logicalRef = sales.getBranch().getLogicalRef();
        int logicalRef2 = sales.getFactory().getLogicalRef();
        if (SalesUtils.isSalesTypeDispatch(sales.getmSalesType())) {
            addElement(xmlSerializer, this._SOURCE_WH, sales.getWareHouse().getLogicalRef());
        } else if (SalesUtils.isSalesTypeReturnDispatch(sales.getmSalesType())) {
            addElement(xmlSerializer, this._SOURCE_WH, sales.getReturnWareHouse().getLogicalRef());
            ArrayList<Integer> wareHouseCostGrpFactoryDivision = baseErp.getLogoSqlHelper().getWareHouseCostGrpFactoryDivision(sales.getReturnWareHouse().getLogicalRef());
            int intValue = wareHouseCostGrpFactoryDivision.get(0).intValue();
            logicalRef2 = wareHouseCostGrpFactoryDivision.get(1).intValue();
            logicalRef = wareHouseCostGrpFactoryDivision.get(2).intValue();
            wareHouseCostGrp = intValue;
        }
        addElement(xmlSerializer, this._SOURCE_COST_GRP, wareHouseCostGrp);
        addElement(xmlSerializer, this._DIVISION, logicalRef);
        addElement(xmlSerializer, this._DEPARTMENT, sales.getDivision().getLogicalRef());
        addElement(xmlSerializer, this._FACTORY, logicalRef2);
        addElement(xmlSerializer, this._NOTES1, sales.getExplanation1().toString());
        addElement(xmlSerializer, this._NOTES2, sales.getExplanation2().toString());
        addElement(xmlSerializer, this._NOTES3, sales.getExplanation3().toString());
        addElement(xmlSerializer, this._NOTES4, sales.getExplanation4().toString());
        addElement(xmlSerializer, this._SHIPLOC_CODE, baseErp.getLogoSqlHelper().getShipAddressCode(sales.getShipAddress().getLogicalRef(), sales.getClRef()));
        addElement(xmlSerializer, this._PAYMENT_CODE, baseErp.getLogoSqlHelper().getPaymentCode(sales.getPayPlan().getLogicalRef()));
        addElement(xmlSerializer, this._SHIPMENT_TYPE, sales.getShipType().toString());
        addElement(xmlSerializer, this._SHIPPING_AGENT, sales.getShipAgent().toString());
        addElement(xmlSerializer, this._TRADING_GRP, sales.getTradeGroup().toString());
        addElement(xmlSerializer, this._PROJECT_CODE, baseErp.getLogoSqlHelper().getProjectCode(sales.getProjectCode().getLogicalRef()));
        addElement(xmlSerializer, this._ORIG_NUMBER, "~");
        addElement(xmlSerializer, this._DISP_STATUS, 1);
        if (baseErp.getSalesStatus(sales).getStatus() != 0) {
            addElement(xmlSerializer, this._STATUS, baseErp.getSalesStatus(sales).getStatus());
        }
        if (sales.getErpInvoiceType() == null || sales.getErpInvoiceType().getLogicalRef() < 0) {
            erpInvoiceType = baseErp.getErpTypeFromSales(sales);
        } else {
            erpInvoiceType = ErpInvoiceType.fromErpInvoiceType(sales.getErpInvoiceType().getLogicalRef());
        }
        if (erpInvoiceType == ErpInvoiceType.EInvoice) {
            addElement(xmlSerializer, this._EINVOICE, erpInvoiceType.getmValue());
            addElement(xmlSerializer, this._EINVOICETYP, baseErp.getLogoSqlHelper().getClEInvoiceTyp(sales.getClRef()));
            addElement(xmlSerializer, this._EINVOICE_PROFILEID, baseErp.getLogoSqlHelper().getClProfileId(sales.getClRef()));
        } else if (erpInvoiceType == ErpInvoiceType.EArchiveInvoice || erpInvoiceType == ErpInvoiceType.EArchiveInternetInvoice) {
            addElement(xmlSerializer, this._EINVOICE, erpInvoiceType.getmValue());
            addElement(xmlSerializer, this._EINVOICETYP, baseErp.getLogoSqlHelper().getClEInvoiceTyp(sales.getClRef()));
            addElement(xmlSerializer, this._EARCHIVEDETR_INTPAYMENTDATE, sales.getFicheCreateDate());
            addElement(xmlSerializer, this._EARCHIVEDETR_INTPAYMENTTYPE, baseErp.getLogoSqlHelper().getLogoParamValue(String.valueOf(ErpParamType.SALES_SINTPAYMENTTYP.getmValue())));
            String str3 = this._EARCHIVEDETR_SENDMOD;
            if (baseErp.getLogoSqlHelper().getClCardSendMod(sales.getClRef()) == 0) {
                i2 = 1;
            } else {
                i2 = baseErp.getLogoSqlHelper().getClCardSendMod(sales.getClRef());
            }
            addElement(xmlSerializer, str3, i2);
            addElement(xmlSerializer, this._EARCHIVEDETR_INTSALESADDR, baseErp.getUser().getIntSalesAddr());
            addElement(xmlSerializer, this._EARCHIVEDETR_ISPERCURR, baseErp.getLogoSqlHelper().getClCardIsPerCurr(sales.getClRef()));
            addElement(xmlSerializer, this._EARCHIVEDETR_INSTEADOFDESP, baseErp.getLogoSqlHelper().getClCardEArchiveInsteadOfDesp(sales.getClRef()));
        }
        addElement(xmlSerializer, this._SALESMANCODE, baseErp.getUser().getCode());
        addElement(xmlSerializer, this._AFFECT_RISK, baseErp.getAffectRisk());
        if (SalesUtils.isSalesTypeInvoiceOrReturnInvoiceOrRetailInvOrRetailReturnInv(sales.getmSalesType())) {
            addElement(xmlSerializer, this._AFFECT_COLLATRL, baseErp.getLogoSqlHelper().getLogoParamValue(String.valueOf(ErpParamType.FIN_COLLRISKSALINV.getmValue())));
        } else if (SalesUtils.isSalesTypeDispatchOrReturnDispatch(sales.getmSalesType())) {
            addElement(xmlSerializer, this._AFFECT_COLLATRL, baseErp.getLogoSqlHelper().getLogoParamValue(String.valueOf(ErpParamType.FIN_COLLRISKSALDSP.getmValue())));
        }
        int dispatchCurrselTotal = baseErp.getDispatchCurrselTotal();
        int salesCurrselDetail = getSalesCurrselDetail(baseErp.getDispatchCurrselDetail(), sales);
        int clCardCurrency = baseErp.getLogoSqlHelper().getClCardCurrency(sales.getClRef());
        int localCurrType = baseErp.getLocalCurrType();
        int reportCurrType = baseErp.getReportCurrType();
        double currRateWithoutDefaultValue = (localCurrType == reportCurrType || reportCurrType == 0) ? 1.0d : baseErp.getLogoSqlHelper().getCurrRateWithoutDefaultValue(reportCurrType);
        setTotalAndExchangeRateIfEDocumentAndHasCurrencyType(xmlSerializer, clCardCurrency, (clCardCurrency == localCurrType || clCardCurrency == 0) ? 1.0d : baseErp.getLogoSqlHelper().getCurrRateWithoutDefaultValue(clCardCurrency), dispatchCurrselTotal, salesCurrselDetail, sales.getFicheType(), sales.getBranch().getLogicalRef(), true);
        if (currRateWithoutDefaultValue != 0.0d) {
            addElement(xmlSerializer, this._RC_RATE, currRateWithoutDefaultValue);
        }
        addElement(xmlSerializer, this._CURR_TRANSACTION, clCardCurrency);
        addElement(xmlSerializer, this._GUID, sales.getAndFicheNo());
        addElement(xmlSerializer, this._CREATED_BY, baseErp.getUser().getLogoNr());
        Calendar instance2 = Calendar.getInstance();
        addElement(xmlSerializer, this._DATE_CREATED, DateAndTimeUtils.getDateToFormat(instance2, "dd/MM/yyyy"));
        addElement(xmlSerializer, this._HOUR_CREATED, instance2.get(11));
        addElement(xmlSerializer, this._MIN_CREATED, instance2.get(12));
        addElement(xmlSerializer, this._SEC_CREATED, instance2.get(13));
        addElement(xmlSerializer, this._SHIP_DATE, DateAndTimeUtils.getDateToFormat(instance2, "dd.MM.yyyy"));
        addElement(xmlSerializer, this._SHIP_TIME, DateAndTimeUtils.getLogoDateTimeCode(DateAndTimeUtils.getDateToFormat(instance2, "dd.MM.yyyy HH:mm:ss")));
        addElement(xmlSerializer, this._DOC_TIME, DateAndTimeUtils.getLogoDateTimeCode(DateAndTimeUtils.getDateToFormat(instance2, "dd.MM.yyyy HH:mm:ss")));
        addElement(xmlSerializer, this._UPDCURR_DISPATCH, baseErp.getLogoSqlHelper().getLogoParamValue(String.valueOf(ErpParamType.SALES_UPDPRCCURRFORTR.getmValue())));
        addElement(xmlSerializer, this._UPDTRCURR_DISPATCH, baseErp.getLogoSqlHelper().getLogoParamValue(String.valueOf(ErpParamType.SALES_UPDTRNCURRFORTR.getmValue())));
        if (baseErp.getLogoSqlHelper().getClCardEArchiveInsteadOfDesp(sales.getClRef()) != 1 || !SalesUtils.isSalesTypeInvoiceOrRetailInvoice(sales.getmSalesType())) {
            EDocInfoModel eDocInfo = baseErp.getEDocInfo(sales.getClRef(), sales.getBranch().getLogicalRef());
            if (eDocInfo.isEDespatchCustomer()) {
                addElement(xmlSerializer, this._EDESPATCH, !sales.getInsteadOfEDespatch().isSelect());
                addElement(xmlSerializer, this._EDESPATCH_PROFILEID, eDocInfo.getProfileIdEDespatch());
                addElement(xmlSerializer, this._DOC_DATE, DateAndTimeUtils.getDateToFormat(instance2, "dd.MM.yyyy"));
            }
            addElement(xmlSerializer, this._SITE_ID, baseErp.getSiteId());
        }
    }

    private void addVariantInfoForCompositColi(CompositeDetail compositeDetail, XmlSerializer xmlSerializer) throws IOException {
        if (compositeDetail.getVariantRef() != 0) {
            addElement(xmlSerializer, this._VARIANTCODE, compositeDetail.getVariantCode());
            addElement(xmlSerializer, this._CAN_CONFIG, "1");
        }
    }


    public List<TigerServiceResult> getOneToOne(Sales sales) {
        ArrayList arrayList = new ArrayList();
        sales.setMSalesType(SalesType.DISPATCH.getmValue());
        TigerServiceResult dispatch = getDispatch(sales);
        sales.setMSalesType(SalesType.RETURN_DISPATCH.getmValue());
        sales.setAndFicheNo(getCreateAndFicheNo(0));
        TigerServiceResult returnDispatch = getReturnDispatch(sales);
        arrayList.add(dispatch);
        arrayList.add(returnDispatch);
        sales.setMSalesType(SalesType.ONE_TO_ONE_CHANGE.getmValue());
        return arrayList;
    }

    private String getCreateAndFicheNo(int i2) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        if (baseErp.getErpType() == ErpType.TIGER || baseErp.getErpType() == ErpType.GO) {
            return UUID.randomUUID().toString().toUpperCase();
        }
        return generateRandomNumber(15);
    }

    private String generateRandomNumber(int i2) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder("0");
        for (int i3 = 0; i3 < i2 - 1; i3++) {
            sb.append("0123456789".charAt(secureRandom.nextInt(10)));
        }
        return sb.toString();
    }

    private void addDiscountOrder(XmlSerializer xmlSerializer, DiscountDetailLevel discountDetailLevel, FicheDiscountProp ficheDiscountProp, FicheDiscountProp ficheDiscountProp2, FicheDiscountRefProp ficheDiscountRefProp, String str, double d2, int i2, int i3) throws IOException {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        DiscountType discountType;
        DiscountType discountType2 = DiscountType.UNDEFINED;
        if (ficheDiscountProp.getDefinitionDouble() > 0.0d && !ficheDiscountProp.isBoundedToCard()) {
            discountType = DiscountType.DISCRATIO;
            str6 = this._DISCOUNT_RATE;
            str5 = ficheDiscountProp.getDefinition();
            str4 = ficheDiscountProp.getGuid();
            str3 = ficheDiscountProp.getCampaignCode();
            str2 = ficheDiscountProp.getCampaignLineNo();
        } else if (ficheDiscountProp2.getDefinitionDouble() > 0.0d) {
            discountType = DiscountType.DISCTOTAL;
            str6 = this._TOTAL;
            str5 = ficheDiscountProp2.getDefinition();
            str4 = ficheDiscountProp2.getGuid();
            str3 = ficheDiscountProp2.getCampaignCode();
            str2 = ficheDiscountProp2.getCampaignLineNo();
        } else if (!TextUtils.isEmpty(ficheDiscountRefProp.getCode())) {
            discountType = DiscountType.DISCCARDCODE;
            str6 = this._MASTER_CODE;
            str5 = ficheDiscountRefProp.getCode();
            str4 = ficheDiscountRefProp.getGuid();
            str3 = ficheDiscountRefProp.getCampaignCode();
            str2 = ficheDiscountRefProp.getCampaignLineNo();
        } else {
            str6 = "";
            discountType = discountType2;
            str5 = str6;
            str4 = str5;
            str3 = str4;
            str2 = str3;
        }
        if (!TextUtils.isEmpty(str3)) {
            Log.d(TAG, "addDiscountOrder: campaign line dont send ");
        }
        if (discountType != discountType2) {
            startTag(xmlSerializer, this._TRANSACTION);
            addElement(xmlSerializer, this._TYPE, LineType.DISCOUNT.value);
            addElement(xmlSerializer, str6, str5);
            addElement(xmlSerializer, this._QUANTITY, "0");
            addElement(xmlSerializer, this._DETAIL_LEVEL, discountDetailLevel.getLevel());
            addElement(xmlSerializer, this._SALESMAN_CODE, str);
            if (discountType == DiscountType.DISCRATIO) {
                addElement(xmlSerializer, this._TOTAL, d2);
            } else {
                addElement(xmlSerializer, this._CALC_TYPE, discountType.value);
            }
            addElement(xmlSerializer, this._GUID, str4);
            addElement(xmlSerializer, this._SOURCE_COST_GRP, i2);
            addElement(xmlSerializer, this._SOURCE_WH, i3);
            if (str3 != null && !str3.equalsIgnoreCase(str4)) {
                addDiscountCampaignInfos(xmlSerializer, str3, str2);
            }
            startTag(xmlSerializer, this._DEFNFLDS);
            endTag(xmlSerializer, this._DEFNFLDS);
            endTag(xmlSerializer, this._TRANSACTION);
        }
    }

    private void addDiscountInvoice(XmlSerializer xmlSerializer, DiscountDetailLevel discountDetailLevel, FicheDiscountProp ficheDiscountProp, FicheDiscountProp ficheDiscountProp2, FicheDiscountRefProp ficheDiscountRefProp, String str, double d2, int i2, int i3) throws IOException {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        DiscountType discountType;
        DiscountType discountType2 = DiscountType.UNDEFINED;
        if (ficheDiscountProp.getDefinitionDouble() > 0.0d && !ficheDiscountProp.isBoundedToCard()) {
            discountType = DiscountType.DISCRATIO;
            str6 = this._DISCOUNT_RATE;
            str5 = ficheDiscountProp.getDefinition();
            str4 = ficheDiscountProp.getGuid();
            str3 = ficheDiscountProp.getCampaignCode();
            str2 = ficheDiscountProp.getCampaignLineNo();
        } else if (ficheDiscountProp2.getDefinitionDouble() > 0.0d) {
            discountType = DiscountType.DISCTOTAL;
            str6 = this._TOTAL;
            str5 = ficheDiscountProp2.getDefinition();
            str4 = ficheDiscountProp2.getGuid();
            str3 = ficheDiscountProp2.getCampaignCode();
            str2 = ficheDiscountProp2.getCampaignLineNo();
        } else if (!TextUtils.isEmpty(ficheDiscountRefProp.getCode())) {
            discountType = DiscountType.DISCCARDCODE;
            str6 = this._MASTER_CODE;
            str5 = ficheDiscountRefProp.getCode();
            str4 = ficheDiscountRefProp.getGuid();
            str3 = ficheDiscountRefProp.getCampaignCode();
            str2 = ficheDiscountRefProp.getCampaignLineNo();
        } else {
            str6 = "";
            discountType = discountType2;
            str5 = str6;
            str4 = str5;
            str3 = str4;
            str2 = str3;
        }
        if (!TextUtils.isEmpty(str3)) {
            Log.d(TAG, "addDiscountInvoice: campaign line dont send");
        }
        if (discountType != discountType2) {
            startTag(xmlSerializer, this._TRANSACTION);
            addElement(xmlSerializer, this._TYPE, LineType.DISCOUNT.value);
            addElement(xmlSerializer, str6, str5);
            addElement(xmlSerializer, this._QUANTITY, "0");
            addElement(xmlSerializer, this._DETAIL_LEVEL, discountDetailLevel.getLevel());
            addElement(xmlSerializer, this._SALEMANCODE, str);
            addElement(xmlSerializer, this._GUID, str4);
            addElement(xmlSerializer, this._SOURCECOSTGRP, i2);
            addElement(xmlSerializer, this._SOURCEINDEX, i3);
            if (discountType == DiscountType.DISCRATIO) {
                addElement(xmlSerializer, this._TOTAL, d2);
            } else {
                addElement(xmlSerializer, this._DISCEXP_CALC, discountType.value);
            }
            addElement(xmlSerializer, this._DISPATCH_NUMBER, "~");
            addElement(xmlSerializer, this._BILLED, 1);
            if (str3 != null && !str3.equalsIgnoreCase(str4)) {
                addDiscountCampaignInfos(xmlSerializer, str3, str2);
            }
            endTag(xmlSerializer, this._TRANSACTION);
        }
    }

    private void addCampaignInfos(XmlSerializer xmlSerializer, SalesDetail salesDetail) throws IOException {
        startTag(xmlSerializer, this._CAMPAIGN_INFOS);
        startTag(xmlSerializer, this._CAMPAIGN_INFO);
        if (!TextUtils.isEmpty(salesDetail.getCampaignCode())) {
            addElement(xmlSerializer, this._CAMPCODE1, salesDetail.getCampaignCode());
        }
        if (!TextUtils.isEmpty(salesDetail.getCampaignCode2())) {
            addElement(xmlSerializer, this._CAMPCODE2, salesDetail.getCampaignCode2());
        }
        if (!TextUtils.isEmpty(salesDetail.getCampaignCode3())) {
            addElement(xmlSerializer, this._CAMPCODE3, salesDetail.getCampaignCode3());
        }
        if (!TextUtils.isEmpty(salesDetail.getCampaignCode4())) {
            addElement(xmlSerializer, this._CAMPCODE4, salesDetail.getCampaignCode4());
        }
        if (!TextUtils.isEmpty(salesDetail.getCampaignCode5())) {
            addElement(xmlSerializer, this._CAMPCODE5, salesDetail.getCampaignCode5());
        }
        if (!TextUtils.isEmpty(salesDetail.getCampaignLineNo())) {
            addElement(xmlSerializer, this._CAMP_LN_NO, salesDetail.getCampaignLineNo());
        }
        endTag(xmlSerializer, this._CAMPAIGN_INFO);
        endTag(xmlSerializer, this._CAMPAIGN_INFOS);
    }

    private void addDiscountCampaignInfos(XmlSerializer xmlSerializer, String str, String str2) throws IOException {
        startTag(xmlSerializer, this._CAMPAIGN_INFOS);
        startTag(xmlSerializer, this._CAMPAIGN_INFO);
        if (!TextUtils.isEmpty(str)) {
            addElement(xmlSerializer, this._CAMPCODE1, str);
        }
        if (!TextUtils.isEmpty(str2)) {
            addElement(xmlSerializer, this._CAMP_LN_NO, str2);
        }
        endTag(xmlSerializer, this._CAMPAIGN_INFO);
        endTag(xmlSerializer, this._CAMPAIGN_INFOS);
    }

    private void addFicheDimensionsInfo(XmlSerializer xmlSerializer, BaseErp baseErp, int i2, int i3) throws IOException {
        List table = baseErp.getLogoSqlHelper().getTable(ItemUnits.class, "ITEMREF=? AND LOGICALREF=?", new String[]{String.valueOf(i2), String.valueOf(i3)});
        if (table != null && table.size() > 0) {
            ItemUnits itemUnits = (ItemUnits) table.get(0);
            addElement(xmlSerializer, this._UNIT_CONV3, itemUnits.width);
            addElement(xmlSerializer, this._UNIT_CONV4, itemUnits.length);
            addElement(xmlSerializer, this._UNIT_CONV5, itemUnits.height);
            addElement(xmlSerializer, this._UNIT_CONV6, itemUnits.area);
            addElement(xmlSerializer, this._UNIT_CONV7, itemUnits.volume);
            addElement(xmlSerializer, this._UNIT_CONV8, itemUnits.weight);
            addElement(xmlSerializer, this._GROSS_U_INFO1, itemUnits.grossVolume);
            addElement(xmlSerializer, this._GROSS_U_INFO2, itemUnits.grossWeight);
        }
    }
    public TigerServiceResult getCaseCash(CaseCash r37) {
        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.core.data.TigerSendDataCreator.getCaseCash(com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash):com.proje.mobilesales.core.tigerwcf.TigerServiceResult");
    }
    public TigerServiceResult getCheque(ChequeDeed chequeDeed) {
        Throwable th;
        IOException e2;
        Throwable th2;
        BaseErp baseErp = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        XmlSerializer createXmlSerializer = null;
        String str = "";
        String projectCode = "";
        String convertStringDate = "";
        double currRateWithControl = 0;
        int i2 = 0;
        int i3 = 0;
        String str2 = "";
        TimingLogger timingLogger = null;
        int i4 = 0;
        double d2 = 0;
        Iterator<ChequedeedDetail> it;
        BaseErp baseErp2;
        double d3;
        ByteArrayOutputStream byteArrayOutputStream2;
        String str3;
        TimingLogger timingLogger2 = new TimingLogger("CreateXml", "getChequeToXml");
        timingLogger2.addSplit("Start Cheque Xml Create ");
        String str4 = "";
        try {
            try {
                baseErp = ErpCreator.getInstance().getmBaseErp();
                byteArrayOutputStream = new ByteArrayOutputStream();
                createXmlSerializer = createXmlSerializer(byteArrayOutputStream, _OUTPUT_ENCODING);
                createXmlSerializer.startDocument(this._ENCODING, null);
                startTag(createXmlSerializer, this._CQPN_ROLLS);
                startTag(createXmlSerializer, this._CHQPN_ROLL);
                if (chequeDeed.getChequedeed().isTransfer > 0) {
                    try {
                        addAttribute(createXmlSerializer, this._ATTR_DBOP, this._ATTR_DROP_UPD);
                        addElement(createXmlSerializer, this._DATA_REFERENCE, chequeDeed.getChequedeed().ficheref);
                    } catch (IOException e3) {
                        e = e3;
                        e2 = e;
                        e2.printStackTrace();
                        timingLogger2.dumpToLog();
                        return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDCHEQUE, chequeDeed.getChequedeed().clRef, chequeDeed.getChequedeed().getgDate(), 0, chequeDeed.getChequedeed().andFicheNo, str4, chequeDeed, chequeDeed.getChequedeed().logicalRef);
                    }
                } else {
                    addAttribute(createXmlSerializer, this._ATTR_DBOP, this._ATTR_DROP_INS);
                }
                addElement(createXmlSerializer, this._TYPE, 1);
                MatterSettings matterSettings = baseErp.getMatterSettings(ContextUtils.getmContext(), FicheType.CHEQUE);
                str = "~";
                if (!matterSettings.isUseMatterNo()) {
                    addElement(createXmlSerializer, this._NUMBER, str);
                } else if (matterSettings.getMatterArea() == MatterArea.FICHE_NO) {
                    String str5 = this._NUMBER;
                    if (TextUtils.isEmpty(chequeDeed.getChequedeed().ficheNo)) {
                        str3 = str;
                    } else {
                        str3 = chequeDeed.getChequedeed().ficheNo;
                    }
                    addElement(createXmlSerializer, str5, str3);
                }
                addElement(createXmlSerializer, this._MASTER_MODULE, ModuleNr.CL_CARD.getType());
                addElement(createXmlSerializer, this._MASTER_CODE, baseErp.getLogoSqlHelper().getClCode(chequeDeed.getChequedeed().clRef));
                addElement(createXmlSerializer, this._AUTH_CODE, chequeDeed.getChequedeed().cyphcode);
                addElement(createXmlSerializer, this._AUXIL_CODE, chequeDeed.getChequedeed().specode);
                addElement(createXmlSerializer, this._DIVISION, chequeDeed.getChequedeed().branchnr);
                addElement(createXmlSerializer, this._DEPARTMENT, chequeDeed.getChequedeed().divisnr);
                addElement(createXmlSerializer, this._TRADING_GRP, chequeDeed.getChequedeed().tradinggrp);
                projectCode = baseErp.getLogoSqlHelper().getProjectCode(StringUtils.convertStringToInt(chequeDeed.getChequedeed().projectRef));
                addElement(createXmlSerializer, this._PROJECT_CODE, projectCode);
                addElement(createXmlSerializer, this._DOC_NUMBER, chequeDeed.getChequedeed().docNo);
                addElement(createXmlSerializer, this._DOCUMENT_COUNT, chequeDeed.getChequedeedDetail().size());
                addElement(createXmlSerializer, this._TOTAL, chequeDeed.getChequedeed().total);
                addElement(createXmlSerializer, this._NOTES1, chequeDeed.getChequedeed().desc1);
                addElement(createXmlSerializer, this._NOTES2, chequeDeed.getChequedeed().desc2);
                addElement(createXmlSerializer, this._NOTES3, chequeDeed.getChequedeed().desc3);
                addElement(createXmlSerializer, this._NOTES4, chequeDeed.getChequedeed().desc4);
                addElement(createXmlSerializer, this._GUID, chequeDeed.getChequedeed().andFicheNo);
                boolean affectRisk = baseErp.getAffectRisk();
                addElement(createXmlSerializer, this._AFFECT_RISK, affectRisk ? 1 : 0);
                addElement(createXmlSerializer, this._AFFECT_COLLATRL, baseErp.getLogoSqlHelper().getLogoParamValue(String.valueOf(ErpParamType.FIN_COLLRISK.getmValue())));
                addElement(createXmlSerializer, this._SALESMAN_CODE, baseErp.getUser().getCode());
                convertStringDate = DateAndTimeUtils.convertStringDate(chequeDeed.getChequedeed().getgDate(), "dd.MM.yyyy HH:mm:ss", "dd.MM.yyyy");
                addElement(createXmlSerializer, this._DATE, convertStringDate);
                DateAndTimeUtils.getDateCalendar(chequeDeed.getChequedeed().getgDate(), "dd.MM.yyyy HH:mm:ss");
                addElement(createXmlSerializer, this._CREATED_BY, baseErp.getUser().getLogoNr());
                Calendar instance2 = Calendar.getInstance();
                addElement(createXmlSerializer, this._DATE_CREATED, DateAndTimeUtils.getDateToFormat(instance2, "dd/MM/yyyy"));
                addElement(createXmlSerializer, this._HOUR_CREATED, instance2.get(11));
                addElement(createXmlSerializer, this._MIN_CREATED, instance2.get(12));
                addElement(createXmlSerializer, this._SEC_CREATED, instance2.get(13));
                addElement(createXmlSerializer, this._CURRSEL_TOTALS, baseErp.getChequeCurrselTotal());
                addElement(createXmlSerializer, this._CURRSEL_DETAILS, baseErp.getChequeCurrselDetail());
                int clCardCurrency = baseErp.getLogoSqlHelper().getClCardCurrency(chequeDeed.getChequedeed().clRef);
                currRateWithControl = baseErp.getLogoSqlHelper().getCurrRateWithControl(baseErp.getReportCurrType(), baseErp.getLocalCurrType());
                ISqlHelper logoSqlHelper = baseErp.getLogoSqlHelper();
                i2 = affectRisk ? 1 : 0;
                double currRate = logoSqlHelper.getCurrRate(clCardCurrency);
                i3 = (currRate > 0.0d ? 1 : (currRate == 0.0d ? 0 : -1));
                if (i3 != 0) {
                    try {
                        try {
                            addElement(createXmlSerializer, this._TC_XRATE, currRate);
                            str2 = str4;
                            try {
                                timingLogger = timingLogger2;
                            } catch (IOException e4) {
                                e = e4;
                                e2 = e;
                                str4 = str2;
                                e2.printStackTrace();
                                timingLogger2.dumpToLog();
                                return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDCHEQUE, chequeDeed.getChequedeed().clRef, chequeDeed.getChequedeed().getgDate(), 0, chequeDeed.getChequedeed().andFicheNo, str4, chequeDeed, chequeDeed.getChequedeed().logicalRef);
                            }
                        } catch (Throwable th3) {
                            th2 = th3;
                            th = th2;
                            timingLogger2.dumpToLog();
                            throw th;
                        }
                    } catch (IOException e5) {
                        e = e5;
                        e2 = e;
                        e2.printStackTrace();
                        timingLogger2.dumpToLog();
                        return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDCHEQUE, chequeDeed.getChequedeed().clRef, chequeDeed.getChequedeed().getgDate(), 0, chequeDeed.getChequedeed().andFicheNo, str4, chequeDeed, chequeDeed.getChequedeed().logicalRef);
                    }
                    try {
                        addElement(createXmlSerializer, this._TC_TOTAL, chequeDeed.getChequedeed().total / currRate);
                    } catch (IOException e6) {
                        e2 = e6;
                        str4 = str2;
                        timingLogger2 = timingLogger;
                        e2.printStackTrace();
                        timingLogger2.dumpToLog();
                        return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDCHEQUE, chequeDeed.getChequedeed().clRef, chequeDeed.getChequedeed().getgDate(), 0, chequeDeed.getChequedeed().andFicheNo, str4, chequeDeed, chequeDeed.getChequedeed().logicalRef);
                    } catch (Throwable th4) {
                        th = th4;
                        timingLogger2 = timingLogger;
                        timingLogger2.dumpToLog();
                        throw th;
                    }
                } else {
                    timingLogger = timingLogger2;
                    str2 = str4;
                }
                i4 = (currRateWithControl > 0.0d ? 1 : (currRateWithControl == 0.0d ? 0 : -1));
                if (i4 != 0) {
                    addElement(createXmlSerializer, this._RC_XRATE, currRateWithControl);
                    d2 = currRate;
                    addElement(createXmlSerializer, this._RC_TOTAL, chequeDeed.getChequedeed().total / currRateWithControl);
                } else {
                    d2 = currRate;
                }
            } catch (Throwable th5) {
                th2 = th5;
            }
            try {
                addElement(createXmlSerializer, this._GL_CODE, baseErp.getLogoSqlHelper().getMuhAccCode(chequeDeed.getChequedeed().clRef));
                startTag(createXmlSerializer, this._TRANSACTIONS);
                Iterator<ChequedeedDetail> it2 = chequeDeed.getChequedeedDetail().iterator();
                while (it2.hasNext()) {
                    ChequedeedDetail next = it2.next();
                    startTag(createXmlSerializer, this._TRANSACTION);
                    addElement(createXmlSerializer, this._TYPE, 1);
                    addElement(createXmlSerializer, this._NUMBER, str);
                    addElement(createXmlSerializer, this._AUXIL_CODE, next.specode);
                    addElement(createXmlSerializer, this._AUTH_CODE, next.cyphcode);
                    addElement(createXmlSerializer, this._CITY, next.payWhere);
                    addElement(createXmlSerializer, this._OWING, next.debited);
                    addElement(createXmlSerializer, this._DIVISION, chequeDeed.getChequedeed().branchnr);
                    addElement(createXmlSerializer, this._DUE_DATE, next.date);
                    addElement(createXmlSerializer, this._DATE, convertStringDate);
                    addElement(createXmlSerializer, this._AMOUNT, next.total);
                    addElement(createXmlSerializer, this._SERIAL_NR, next.serialNo);
                    addElement(createXmlSerializer, this._PROJECT_CODE, projectCode);
                    addElement(createXmlSerializer, this._AFFECT_RISK, i2);
                    addElement(createXmlSerializer, this._BANK_TITLE, next.bankName);
                    addElement(createXmlSerializer, this._DIVISION_NO, next.bankBranchName);
                    addElement(createXmlSerializer, this._ACCOUNT_NO, next.accNo);
                    if (i3 != 0) {
                        i2 = i2;
                        d3 = d2;
                        addElement(createXmlSerializer, this._TC_XRATE, d3);
                        it = it2;
                        baseErp2 = baseErp;
                        addElement(createXmlSerializer, this._TC_AMOUNT, next.total / d3);
                        byteArrayOutputStream2 = byteArrayOutputStream;
                    } else {
                        i2 = i2;
                        d3 = d2;
                        it = it2;
                        baseErp2 = baseErp;
                        addElement(createXmlSerializer, this._TC_XRATE, 1);
                        byteArrayOutputStream2 = byteArrayOutputStream;
                        addElement(createXmlSerializer, this._TC_AMOUNT, next.total);
                    }
                    if (i4 != 0) {
                        addElement(createXmlSerializer, this._RC_XRATE, currRateWithControl);
                        addElement(createXmlSerializer, this._RC_AMOUNT, next.total / currRateWithControl);
                    }
                    addElement(createXmlSerializer, this._SALESMAN_CODE, baseErp2.getUser().getCode());
                    if (baseErp2.getChequeStatus().getStatus() != 0) {
                        addElement(createXmlSerializer, this._STATUS, baseErp2.getChequeStatus().getStatus());
                    }
                    baseErp = baseErp2;
                    ClCard customerInfo = baseErp.getCustomerInfo(chequeDeed.getChequedeed().clRef);
                    addElement(createXmlSerializer, this._TAX_NR, StringUtils.convertIntToBoolean(customerInfo.getPersonalCompany()) ? customerInfo.getTckNo() : customerInfo.getTaxNr());
                    endTag(createXmlSerializer, this._TRANSACTION);
                    byteArrayOutputStream = byteArrayOutputStream2;
                    it2 = it;
                    d2 = d3;
                    str = str;
                    convertStringDate = convertStringDate;
                }
                endTag(createXmlSerializer, this._TRANSACTIONS);
                if (baseErp.getChequeStatus().getStatus() != 0) {
                    addElement(createXmlSerializer, this._STATUS, baseErp.getChequeStatus().getStatus());
                }
                endTag(createXmlSerializer, this._CHQPN_ROLL);
                endTag(createXmlSerializer, this._CQPN_ROLLS);
                createXmlSerializer.endDocument();
                timingLogger2 = timingLogger;
                try {
                    timingLogger2.addSplit("Start Finish Cheque Xml Create ");
                    str4 = byteArrayOutputStream.toString();
                    Log.d(MobileSales.TAG, str4);
                    createXmlSerializer.flush();
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                } catch (IOException e7) {
                    e = e7;
                    e2 = e;
                    str4 = str2;
                    e2.printStackTrace();
                    timingLogger2.dumpToLog();
                    return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDCHEQUE, chequeDeed.getChequedeed().clRef, chequeDeed.getChequedeed().getgDate(), 0, chequeDeed.getChequedeed().andFicheNo, str4, chequeDeed, chequeDeed.getChequedeed().logicalRef);
                }
            } catch (IOException e8) {
                e = e8;
                timingLogger2 = timingLogger;
            } catch (Throwable th6) {
                th2 = th6;
                timingLogger2 = timingLogger;
                th = th2;
                timingLogger2.dumpToLog();
                try {
                    throw th;
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e9) {
            e = e9;
        }
        timingLogger2.dumpToLog();
        return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDCHEQUE, chequeDeed.getChequedeed().clRef, chequeDeed.getChequedeed().getgDate(), 0, chequeDeed.getChequedeed().andFicheNo, str4, chequeDeed, chequeDeed.getChequedeed().logicalRef);
    }


    public TigerServiceResult getDeed(ChequeDeed chequeDeed) {
        TimingLogger timingLogger = null;
        Throwable th;
        Throwable th2;
        String str = "";
        IOException e2;
        String str2;
        TimingLogger timingLogger2;
        double d2;
        Iterator<ChequedeedDetail> it;
        BaseErp baseErp;
        double d3;
        String str3;
        try {
            timingLogger = new TimingLogger("CreateXml", "getDeedToXml");
            timingLogger.addSplit("Start Deed Xml Create ");
            str = "";
        } catch (Throwable th3) {
            th2 = th3;
        }
        try {
            BaseErp baseErp2 = ErpCreator.getInstance().getmBaseErp();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            XmlSerializer createXmlSerializer = createXmlSerializer(byteArrayOutputStream, _OUTPUT_ENCODING);
            createXmlSerializer.startDocument(this._ENCODING, null);
            startTag(createXmlSerializer, this._CQPN_ROLLS);
            startTag(createXmlSerializer, this._CHQPN_ROLL);
            if (chequeDeed.getChequedeed().isTransfer > 0) {
                try {
                    addAttribute(createXmlSerializer, this._ATTR_DBOP, this._ATTR_DROP_UPD);
                    addElement(createXmlSerializer, this._DATA_REFERENCE, chequeDeed.getChequedeed().ficheref);
                } catch (IOException e3) {
                    e = e3;
                    e2 = e;
                    e2.printStackTrace();
                    timingLogger.dumpToLog();
                    return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDDEED, chequeDeed.getChequedeed().clRef, chequeDeed.getChequedeed().getgDate(), 0, chequeDeed.getChequedeed().andFicheNo, str, chequeDeed, chequeDeed.getChequedeed().logicalRef);
                }
            } else {
                addAttribute(createXmlSerializer, this._ATTR_DBOP, this._ATTR_DROP_INS);
            }
            addElement(createXmlSerializer, this._TYPE, 2);
            MatterSettings matterSettings = baseErp2.getMatterSettings(ContextUtils.getmContext(), FicheType.DEED);
            String str4 = "~";
            if (!matterSettings.isUseMatterNo()) {
                addElement(createXmlSerializer, this._NUMBER, str4);
            } else if (matterSettings.getMatterArea() == MatterArea.FICHE_NO) {
                String str5 = this._NUMBER;
                if (TextUtils.isEmpty(chequeDeed.getChequedeed().ficheNo)) {
                    str3 = str4;
                } else {
                    str3 = chequeDeed.getChequedeed().ficheNo;
                }
                addElement(createXmlSerializer, str5, str3);
            }
            addElement(createXmlSerializer, this._MASTER_MODULE, ModuleNr.CL_CARD.getType());
            addElement(createXmlSerializer, this._MASTER_CODE, baseErp2.getLogoSqlHelper().getClCode(chequeDeed.getChequedeed().clRef));
            addElement(createXmlSerializer, this._AUTH_CODE, chequeDeed.getChequedeed().cyphcode);
            addElement(createXmlSerializer, this._AUXIL_CODE, chequeDeed.getChequedeed().specode);
            addElement(createXmlSerializer, this._DIVISION, chequeDeed.getChequedeed().branchnr);
            addElement(createXmlSerializer, this._DEPARTMENT, chequeDeed.getChequedeed().divisnr);
            addElement(createXmlSerializer, this._TRADING_GRP, chequeDeed.getChequedeed().tradinggrp);
            String projectCode = baseErp2.getLogoSqlHelper().getProjectCode(StringUtils.convertStringToInt(chequeDeed.getChequedeed().projectRef));
            addElement(createXmlSerializer, this._PROJECT_CODE, projectCode);
            addElement(createXmlSerializer, this._DOC_NUMBER, chequeDeed.getChequedeed().docNo);
            if (chequeDeed.getChequedeedDetail() != null) {
                addElement(createXmlSerializer, this._DOCUMENT_COUNT, chequeDeed.getChequedeedDetail().size());
            }
            addElement(createXmlSerializer, this._TOTAL, chequeDeed.getChequedeed().total);
            addElement(createXmlSerializer, this._NOTES1, chequeDeed.getChequedeed().desc1);
            addElement(createXmlSerializer, this._NOTES2, chequeDeed.getChequedeed().desc2);
            addElement(createXmlSerializer, this._NOTES3, chequeDeed.getChequedeed().desc3);
            addElement(createXmlSerializer, this._NOTES4, chequeDeed.getChequedeed().desc4);
            addElement(createXmlSerializer, this._GUID, chequeDeed.getChequedeed().andFicheNo);
            boolean affectRisk = baseErp2.getAffectRisk();
            addElement(createXmlSerializer, this._AFFECT_RISK, affectRisk ? 1 : 0);
            addElement(createXmlSerializer, this._AFFECT_COLLATRL, baseErp2.getLogoSqlHelper().getLogoParamValue(String.valueOf(ErpParamType.FIN_COLLRISK.getmValue())));
            addElement(createXmlSerializer, this._SALESMAN_CODE, baseErp2.getUser().getCode());
            String convertStringDate = DateAndTimeUtils.convertStringDate(chequeDeed.getChequedeed().getgDate(), "dd.MM.yyyy HH:mm:ss", "dd.MM.yyyy");
            addElement(createXmlSerializer, this._DATE, convertStringDate);
            DateAndTimeUtils.getDateCalendar(chequeDeed.getChequedeed().getgDate(), "dd.MM.yyyy HH:mm:ss");
            addElement(createXmlSerializer, this._CREATED_BY, baseErp2.getUser().getLogoNr());
            Calendar instance2 = Calendar.getInstance();
            addElement(createXmlSerializer, this._DATE_CREATED, DateAndTimeUtils.getDateToFormat(instance2, "dd/MM/yyyy"));
            addElement(createXmlSerializer, this._HOUR_CREATED, instance2.get(11));
            addElement(createXmlSerializer, this._MIN_CREATED, instance2.get(12));
            addElement(createXmlSerializer, this._SEC_CREATED, instance2.get(13));
            addElement(createXmlSerializer, this._CURRSEL_TOTALS, baseErp2.getDeedCurrselTotal());
            addElement(createXmlSerializer, this._CURRSEL_DETAILS, baseErp2.getDeedCurrselDetail());
            int clCardCurrency = baseErp2.getLogoSqlHelper().getClCardCurrency(chequeDeed.getChequedeed().clRef);
            double currRateWithControl = baseErp2.getLogoSqlHelper().getCurrRateWithControl(baseErp2.getReportCurrType(), baseErp2.getLocalCurrType());
            ISqlHelper logoSqlHelper = baseErp2.getLogoSqlHelper();
            int i2 = affectRisk ? 1 : 0;
            double currRate = logoSqlHelper.getCurrRate(clCardCurrency);
            int i3 = (currRate > 0.0d ? 1 : (currRate == 0.0d ? 0 : -1));
            if (i3 != 0) {
                try {
                    try {
                        addElement(createXmlSerializer, this._TC_XRATE, currRate);
                        str2 = str;
                    } catch (IOException e4) {
                        e = e4;
                        e2 = e;
                        e2.printStackTrace();
                        timingLogger.dumpToLog();
                        return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDDEED, chequeDeed.getChequedeed().clRef, chequeDeed.getChequedeed().getgDate(), 0, chequeDeed.getChequedeed().andFicheNo, str, chequeDeed, chequeDeed.getChequedeed().logicalRef);
                    }
                    try {
                        timingLogger2 = timingLogger;
                    } catch (IOException e5) {
                        e = e5;
                        e2 = e;
                        str = str2;
                        e2.printStackTrace();
                        timingLogger.dumpToLog();
                        return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDDEED, chequeDeed.getChequedeed().clRef, chequeDeed.getChequedeed().getgDate(), 0, chequeDeed.getChequedeed().andFicheNo, str, chequeDeed, chequeDeed.getChequedeed().logicalRef);
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    th = th2;
                    timingLogger.dumpToLog();
                    throw th;
                }
                try {
                    addElement(createXmlSerializer, this._TC_TOTAL, chequeDeed.getChequedeed().total / currRate);
                } catch (IOException e6) {
                    e2 = e6;
                    str = str2;
                    timingLogger = timingLogger2;
                    e2.printStackTrace();
                    timingLogger.dumpToLog();
                    return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDDEED, chequeDeed.getChequedeed().clRef, chequeDeed.getChequedeed().getgDate(), 0, chequeDeed.getChequedeed().andFicheNo, str, chequeDeed, chequeDeed.getChequedeed().logicalRef);
                } catch (Throwable th5) {
                    th = th5;
                    timingLogger = timingLogger2;
                    timingLogger.dumpToLog();
                    throw th;
                }
            } else {
                timingLogger2 = timingLogger;
                str2 = str;
            }
            int i4 = (currRateWithControl > 0.0d ? 1 : (currRateWithControl == 0.0d ? 0 : -1));
            if (i4 != 0) {
                addElement(createXmlSerializer, this._RC_XRATE, currRateWithControl);
                d2 = currRate;
                addElement(createXmlSerializer, this._RC_TOTAL, chequeDeed.getChequedeed().total / currRateWithControl);
            } else {
                d2 = currRate;
            }
            try {
                addElement(createXmlSerializer, this._GL_CODE, baseErp2.getLogoSqlHelper().getMuhAccCode(chequeDeed.getChequedeed().clRef));
                startTag(createXmlSerializer, this._TRANSACTIONS);
                if (chequeDeed.getChequedeedDetail() != null) {
                    Iterator<ChequedeedDetail> it2 = chequeDeed.getChequedeedDetail().iterator();
                    while (it2.hasNext()) {
                        ChequedeedDetail next = it2.next();
                        startTag(createXmlSerializer, this._TRANSACTION);
                        addElement(createXmlSerializer, this._TYPE, 2);
                        addElement(createXmlSerializer, this._NUMBER, str4);
                        addElement(createXmlSerializer, this._AUXIL_CODE, next.specode);
                        addElement(createXmlSerializer, this._AUTH_CODE, next.cyphcode);
                        addElement(createXmlSerializer, this._CITY, next.payWhere);
                        addElement(createXmlSerializer, this._OWING, next.debited);
                        addElement(createXmlSerializer, this._DIVISION, chequeDeed.getChequedeed().branchnr);
                        addElement(createXmlSerializer, this._DUE_DATE, next.date);
                        addElement(createXmlSerializer, this._DATE, convertStringDate);
                        addElement(createXmlSerializer, this._AMOUNT, next.total);
                        addElement(createXmlSerializer, this._SERIAL_NR, next.serialNo);
                        addElement(createXmlSerializer, this._PROJECT_CODE, projectCode);
                        addElement(createXmlSerializer, this._AFFECT_RISK, i2);
                        addElement(createXmlSerializer, this._GUARANTOR, next.inCharge);
                        i2 = i2;
                        addElement(createXmlSerializer, this._STAMP_FEE, (double) next.pul);
                        if (i3 != 0) {
                            d3 = d2;
                            addElement(createXmlSerializer, this._TC_XRATE, d3);
                            it = it2;
                            baseErp = baseErp2;
                            addElement(createXmlSerializer, this._TC_AMOUNT, next.total / d3);
                        } else {
                            d3 = d2;
                            it = it2;
                            baseErp = baseErp2;
                        }
                        if (i4 != 0) {
                            addElement(createXmlSerializer, this._RC_XRATE, currRateWithControl);
                            addElement(createXmlSerializer, this._RC_AMOUNT, next.total / currRateWithControl);
                        }
                        addElement(createXmlSerializer, this._SALESMAN_CODE, baseErp.getUser().getCode());
                        if (baseErp.getDeedStatus().getStatus() != 0) {
                            addElement(createXmlSerializer, this._STATUS, baseErp.getDeedStatus().getStatus());
                        }
                        baseErp2 = baseErp;
                        ClCard customerInfo = baseErp2.getCustomerInfo(chequeDeed.getChequedeed().clRef);
                        addElement(createXmlSerializer, this._TAX_NR, StringUtils.convertIntToBoolean(customerInfo.getPersonalCompany()) ? customerInfo.getTckNo() : customerInfo.getTaxNr());
                        endTag(createXmlSerializer, this._TRANSACTION);
                        it2 = it;
                        d2 = d3;
                        str4 = str4;
                        convertStringDate = convertStringDate;
                    }
                }
                endTag(createXmlSerializer, this._TRANSACTIONS);
                if (baseErp2.getDeedStatus().getStatus() != 0) {
                    addElement(createXmlSerializer, this._STATUS, baseErp2.getDeedStatus().getStatus());
                }
                endTag(createXmlSerializer, this._CHQPN_ROLL);
                endTag(createXmlSerializer, this._CQPN_ROLLS);
                createXmlSerializer.endDocument();
                timingLogger = timingLogger2;
                try {
                    timingLogger.addSplit("Start Finish Deed Xml Create ");
                    str = byteArrayOutputStream.toString();
                    Log.d(MobileSales.TAG, str);
                    createXmlSerializer.flush();
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                } catch (IOException e7) {
                    e = e7;
                    e2 = e;
                    str = str2;
                    e2.printStackTrace();
                    timingLogger.dumpToLog();
                    return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDDEED, chequeDeed.getChequedeed().clRef, chequeDeed.getChequedeed().getgDate(), 0, chequeDeed.getChequedeed().andFicheNo, str, chequeDeed, chequeDeed.getChequedeed().logicalRef);
                }
            } catch (IOException e8) {
                e = e8;
                timingLogger = timingLogger2;
            } catch (Throwable th6) {
                th2 = th6;
                timingLogger = timingLogger2;
                th = th2;
                timingLogger.dumpToLog();
                throw th;
            }
        } catch (Throwable e9) {
            e = e9;
        }
        timingLogger.dumpToLog();
        return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDDEED, chequeDeed.getChequedeed().clRef, chequeDeed.getChequedeed().getgDate(), 0, chequeDeed.getChequedeed().andFicheNo, str, chequeDeed, chequeDeed.getChequedeed().logicalRef);
    }
    public TigerServiceResult getCreditCard(CashCreditX cashCreditX) {
        TimingLogger timingLogger;
        Throwable th;
        String str = "";
        String str2 = "";
        IOException e2;
        IOException e3;
        BaseErp baseErp = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        XmlSerializer createXmlSerializer = null;
        String convertStringDate = "";
        String projectCode = "";
        String clCode = "";
        String bankAccCode = "";
        boolean affectRisk = false;
        int clCardCurrency = 0;
        int reportCurrType = 0;
        int localCurrType = 0;
        String str3;
        boolean z;
        String str4;
        double d2;
        String obj;
        TimingLogger timingLogger2 = new TimingLogger("CreateXml", "getCreditCartToXml");
        timingLogger2.addSplit("Start Credit Card Xml Create ");
        try {
            try {
                baseErp = ErpCreator.getInstance().getmBaseErp();
                byteArrayOutputStream = new ByteArrayOutputStream();
                createXmlSerializer = createXmlSerializer(byteArrayOutputStream, _OUTPUT_ENCODING);
                createXmlSerializer.startDocument(this._ENCODING, null);
                startTag(createXmlSerializer, this._ARP_VOUCHERS);
                startTag(createXmlSerializer, this._ARP_VOUCHER);
                if (cashCreditX.getCashCredit().isTransfer > 0) {
                    try {
                        addAttribute(createXmlSerializer, this._ATTR_DBOP, this._ATTR_DROP_UPD);
                        addElement(createXmlSerializer, this._DATA_REFERENCE, cashCreditX.getCashCredit().ficheref);
                    } catch (Throwable th2) {
                        th = th2;
                        timingLogger = timingLogger2;
                        timingLogger.dumpToLog();
                        throw th;
                    }
                } else {
                    addAttribute(createXmlSerializer, this._ATTR_DBOP, this._ATTR_DROP_INS);
                }
                addElement(createXmlSerializer, this._TYPE, ClCardFicheType.CREDIT_CARD_RECEIPT.getType());
                MatterSettings matterSettings = baseErp.getMatterSettings(ContextUtils.getmContext(), FicheType.CREDIT_CART);
                String str5 = "~";
                if (!matterSettings.isUseMatterNo()) {
                    addElement(createXmlSerializer, this._NUMBER, str5);
                } else if (matterSettings.getMatterArea() == MatterArea.FICHE_NO) {
                    String str6 = this._NUMBER;
                    if (!TextUtils.isEmpty(cashCreditX.getCashCredit().ficheNo)) {
                        str5 = cashCreditX.getCashCredit().ficheNo;
                    }
                    addElement(createXmlSerializer, str6, str5);
                }
                convertStringDate = DateAndTimeUtils.convertStringDate(cashCreditX.getCashCredit().getgDate(), "dd.MM.yyyy HH:mm:ss", "dd.MM.yyyy");
                addElement(createXmlSerializer, this._DATE, convertStringDate);
                addElement(createXmlSerializer, this._TIME, DateAndTimeUtils.getLogoDateTimeCode(cashCreditX.getCashCredit().getgDate()));
                addElement(createXmlSerializer, this._AUTH_CODE, cashCreditX.getCashCredit().cyphcode);
                addElement(createXmlSerializer, this._AUXIL_CODE, cashCreditX.getCashCredit().specode);
                addElement(createXmlSerializer, this._NOTES1, cashCreditX.getCashCredit().desc1);
                addElement(createXmlSerializer, this._NOTES2, cashCreditX.getCashCredit().desc2);
                addElement(createXmlSerializer, this._NOTES3, cashCreditX.getCashCredit().desc3);
                addElement(createXmlSerializer, this._NOTES4, cashCreditX.getCashCredit().desc4);
                addElement(createXmlSerializer, this._TOTAL_CREDIT, cashCreditX.getCashCredit().total);
                if (cashCreditX.getCashCredit().branchnr != -1) {
                    addElement(createXmlSerializer, this._DIVISION, cashCreditX.getCashCredit().branchnr);
                }
                if (cashCreditX.getCashCredit().divisnr != -1) {
                    addElement(createXmlSerializer, this._DEPARTMENT, cashCreditX.getCashCredit().divisnr);
                }
                projectCode = baseErp.getLogoSqlHelper().getProjectCode(StringUtils.convertStringToInt(cashCreditX.getCashCredit().projectRef));
                addElement(createXmlSerializer, this._SALESMAN_CODE, baseErp.getUser().getCode());
                addElement(createXmlSerializer, this._PROJECT_CODE, projectCode);
                clCode = baseErp.getLogoSqlHelper().getClCode(cashCreditX.getCashCredit().clRef);
                addElement(createXmlSerializer, this._ARP_CODE, clCode);
                addElement(createXmlSerializer, this._GL_CODE, baseErp.getLogoSqlHelper().getMuhAccCode(cashCreditX.getCashCredit().clRef));
                addElement(createXmlSerializer, this._GUID, cashCreditX.getCashCredit().andFicheNo);
                bankAccCode = baseErp.getLogoSqlHelper().getBankAccCode(cashCreditX.getCashCredit().bankAccRef);
                addElement(createXmlSerializer, this._BANKACC_CODE, bankAccCode);
                affectRisk = baseErp.getAffectRisk();
                addElement(createXmlSerializer, this._AFFECT_RISK, affectRisk ? 1 : 0);
                addElement(createXmlSerializer, this._AFFECT_COLLATRL, baseErp.getLogoSqlHelper().getLogoParamValue(String.valueOf(ErpParamType.FIN_COLLRISKCLITR.getmValue())));
                addElement(createXmlSerializer, this._CREATED_BY, baseErp.getUser().getLogoNr());
                Calendar instance2 = Calendar.getInstance();
                addElement(createXmlSerializer, this._DATE_CREATED, DateAndTimeUtils.getDateToFormat(instance2, "dd/MM/yyyy"));
                addElement(createXmlSerializer, this._HOUR_CREATED, instance2.get(11));
                addElement(createXmlSerializer, this._MIN_CREATED, instance2.get(12));
                addElement(createXmlSerializer, this._SEC_CREATED, instance2.get(13));
                addElement(createXmlSerializer, this._CURRSEL_TOTALS, baseErp.getCreditCardCurrselTotal());
                addElement(createXmlSerializer, this._CURRSEL_DETAILS, baseErp.getCreditCardCurrselDetails());
                if (baseErp.getCreditCardStatus().getStatus() != 0) {
                    addElement(createXmlSerializer, this._STATUS, baseErp.getCreditCardStatus().getStatus());
                }
                clCardCurrency = baseErp.getLogoSqlHelper().getClCardCurrency(cashCreditX.getCashCredit().clRef);
                reportCurrType = baseErp.getReportCurrType();
                localCurrType = baseErp.getLocalCurrType();
                str2 = "";
            } catch (IOException e4) {
                e3 = e4;
                str2 = "";
                timingLogger = timingLogger2;
            }
        } catch (Throwable th3) {
            th = th3;
            timingLogger = timingLogger2;
        }
        try {
            double currRateWithControl = baseErp.getLogoSqlHelper().getCurrRateWithControl(reportCurrType, localCurrType);
            double currRate = baseErp.getLogoSqlHelper().getCurrRate(clCardCurrency);
            startTag(createXmlSerializer, this._TRANSACTIONS);
            if (cashCreditX.getCashCreditDetails() != null) {
                try {
                    for (Iterator<CashCreditDetail> it = cashCreditX.getCashCreditDetails().iterator(); it.hasNext(); it = it) {
                        CashCreditDetail next = it.next();
                        startTag(createXmlSerializer, this._TRANSACTION);
                        addElement(createXmlSerializer, this._ARP_CODE, clCode);
                        addElement(createXmlSerializer, this._GL_CODE1, baseErp.getLogoSqlHelper().getMuhAccCode(cashCreditX.getCashCredit().clRef));
                        addElement(createXmlSerializer, this._TRADING_GRP, cashCreditX.getCashCredit().tradinggrp);
                        addElement(createXmlSerializer, this._SALESMAN_CODE, baseErp.getUser().getCode());
                        addElement(createXmlSerializer, this._PROJECT_CODE, projectCode);
                        addElement(createXmlSerializer, this._DESCRIPTION, next.desc);
                        addElement(createXmlSerializer, this._CREDIT, next.total);
                        addElement(createXmlSerializer, this._PAYMENT_CODE, baseErp.getLogoSqlHelper().getPaymentCode(next.paymentRef));
                        String str7 = this._AFFECT_RISK;
                        int i2 = affectRisk ? 1 : 0;
                        int i3 = affectRisk ? 1 : 0;
                        addElement(createXmlSerializer, str7, i2);
                        addElement(createXmlSerializer, this._AFFECT_COLLATRL, baseErp.getLogoSqlHelper().getLogoParamValue(String.valueOf(ErpParamType.FIN_COLLRISKCLITR.getmValue())));
                        addElement(createXmlSerializer, this._AUXIL_CODE, next.specode);
                        addElement(createXmlSerializer, this._DOC_NUMBER, next.docNo);
                        if (clCardCurrency == localCurrType || clCardCurrency == 0) {
                            addElement(createXmlSerializer, this._TC_AMOUNT, next.total);
                            addElement(createXmlSerializer, this._BNLN_TC_AMOUNT, next.total / currRate);
                        } else {
                            addElement(createXmlSerializer, this._CURR_TRANS, clCardCurrency);
                            if (currRate != 0.0d) {
                                addElement(createXmlSerializer, this._TC_XRATE, currRate);
                                addElement(createXmlSerializer, this._TC_AMOUNT, next.total / currRate);
                                addElement(createXmlSerializer, this._BNLN_TC_XRATE, currRate);
                                addElement(createXmlSerializer, this._BNLN_TC_AMOUNT, next.total / currRate);
                            } else {
                                addElement(createXmlSerializer, this._TC_AMOUNT, next.total);
                                addElement(createXmlSerializer, this._BNLN_TC_AMOUNT, next.total / currRate);
                            }
                        }
                        int i4 = (currRateWithControl > 0.0d ? 1 : (currRateWithControl == 0.0d ? 0 : -1));
                        if (i4 != 0) {
                            str3 = projectCode;
                            d2 = currRateWithControl;
                            addElement(createXmlSerializer, this._RC_XRATE, d2);
                            z = affectRisk;
                            str4 = bankAccCode;
                            addElement(createXmlSerializer, this._RC_AMOUNT, next.total / d2);
                        } else {
                            str3 = projectCode;
                            str4 = bankAccCode;
                            int z2 = affectRisk ? 1 : 0;
                            Object[] objArr = new int[][]{new int[]{affectRisk ? 1 : 0}};
                            Object[] objArr2 = new int[][]{new int[]{affectRisk ? 1 : 0}};
                            z = z2;
                            d2 = currRateWithControl;
                        }
                        startTag(createXmlSerializer, this._PAYMENT_LIST);
                        startTag(createXmlSerializer, this._PAYMENT);
                        addElement(createXmlSerializer, this._DATE, convertStringDate);
                        addElement(createXmlSerializer, this._MODULENR, ModuleNr.CL_CARD.getType());
                        addElement(createXmlSerializer, this._TRCODE, ClCardFicheType.CREDIT_CARD_RECEIPT.getType());
                        addElement(createXmlSerializer, this._PAY_NO, 1);
                        if (clCardCurrency == localCurrType || clCardCurrency == 0) {
                            addElement(createXmlSerializer, this._TOTAL, next.total);
                        } else {
                            addElement(createXmlSerializer, this._TRCURR, clCardCurrency);
                            if (currRate != 0.0d) {
                                addElement(createXmlSerializer, this._TRRATE, currRate);
                                addElement(createXmlSerializer, this._TOTAL, next.total / currRate);
                            } else {
                                addElement(createXmlSerializer, this._TOTAL, next.total);
                            }
                        }
                        if (i4 != 0) {
                            addElement(createXmlSerializer, this._REPORTRATE, d2);
                        }
                        endTag(createXmlSerializer, this._PAYMENT);
                        endTag(createXmlSerializer, this._PAYMENT_LIST);
                        addElement(createXmlSerializer, this._CREDIT_CARD_NO, next.crediCardNo);
                        addElement(createXmlSerializer, this._BANKACC_CODE, str4);
                        addElement(createXmlSerializer, this._DESCRIPTION, next.desc);
                        addElement(createXmlSerializer, this._APPROVE_NR, next.approvalNo);
                        endTag(createXmlSerializer, this._TRANSACTION);
                        bankAccCode = str4;
                        clCode = clCode;
                        byteArrayOutputStream = byteArrayOutputStream;
                        projectCode = str3;
                        affectRisk = z;
                        currRateWithControl = d2;
                    }
                } catch (IOException e5) {
                    e2 = e5;
                    timingLogger = timingLogger2;
                    e2.printStackTrace();
                    timingLogger.dumpToLog();
                    str = str2;
                    ISqlHelper logoSqlHelper = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                    timingLogger = DataObjectType.ADDCREDIT;
                    return buildServiceResult(logoSqlHelper, (DataObjectType) timingLogger, cashCreditX.getCashCredit().clRef, cashCreditX.getCashCredit().getgDate(), 0, cashCreditX.getCashCredit().andFicheNo, str, cashCreditX, cashCreditX.getCashCredit().logicalRef);
                } catch (Throwable th4) {
                    th = th4;
                    timingLogger = timingLogger2;
                    timingLogger.dumpToLog();
                    throw th;
                }
            }
            endTag(createXmlSerializer, this._TRANSACTIONS);
            endTag(createXmlSerializer, this._ARP_VOUCHER);
            endTag(createXmlSerializer, this._ARP_VOUCHERS);
            createXmlSerializer.endDocument();
            timingLogger = timingLogger2;
            try {
                timingLogger.addSplit("Start Finish Credit Card Xml Create ");
                obj = byteArrayOutputStream.toString();
                try {
                    Log.d(MobileSales.TAG, obj);
                    createXmlSerializer.flush();
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    timingLogger.dumpToLog();
                    str = obj;
                } catch (IOException e7) {
                    e3 = e7;
                    str2 = obj;
                    e2 = e3;
                    e2.printStackTrace();
                    timingLogger.dumpToLog();
                    str = str2;
                    ISqlHelper logoSqlHelper = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                    timingLogger = DataObjectType.ADDCREDIT;
                    return buildServiceResult(logoSqlHelper, (DataObjectType) timingLogger, cashCreditX.getCashCredit().clRef, cashCreditX.getCashCredit().getgDate(), 0, cashCreditX.getCashCredit().andFicheNo, str, cashCreditX, cashCreditX.getCashCredit().logicalRef);
                }
            } catch (Throwable th5) {
                th = th5;
                th = th;
                timingLogger.dumpToLog();
                throw th;
            }
        } catch (IOException e8) {
            e3 = e8;
            timingLogger = timingLogger2;
        } catch (Throwable th6) {
            th = th6;
            timingLogger = timingLogger2;
            th = th;
            timingLogger.dumpToLog();
            try {
                throw th;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        ISqlHelper logoSqlHelper = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
        timingLogger = DataObjectType.ADDCREDIT;
        return buildServiceResult(logoSqlHelper, (DataObjectType) timingLogger, cashCreditX.getCashCredit().clRef, cashCreditX.getCashCredit().getgDate(), 0, cashCreditX.getCashCredit().andFicheNo, str, cashCreditX, cashCreditX.getCashCredit().logicalRef);
    }
    public TigerServiceResult getCash(CashCreditX r26) {
        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.core.data.TigerSendDataCreator.getCash(com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditX):com.proje.mobilesales.core.tigerwcf.TigerServiceResult");
    }
    public TigerServiceResult getCustomer(CustomerNew customerNew) {
        String str = "";
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            XmlSerializer createXmlSerializer = createXmlSerializer(byteArrayOutputStream, _OUTPUT_ENCODING);
            BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
            startTag(createXmlSerializer, "AR_APS");
            startTag(createXmlSerializer, "AR_AP");
            addAttribute(createXmlSerializer, this._ATTR_DBOP, this._ATTR_DROP_INS);
            addElement(createXmlSerializer, "ACCOUNT_TYPE", 3);
            addElement(createXmlSerializer, "RECORD_STATUS", customerNew.getActive().booleanValue() ? "0" : "1");
            addElement(createXmlSerializer, "CODE", customerNew.getCode().toString());
            addElement(createXmlSerializer, "TITLE", customerNew.getName().toString());
            addElement(createXmlSerializer, this._AUXIL_CODE, customerNew.getSpeCode().toString());
            addElement(createXmlSerializer, "AUXIL_CODE2", customerNew.getSpeCode2().toString());
            addElement(createXmlSerializer, "AUXIL_CODE3", customerNew.getSpeCode3().toString());
            addElement(createXmlSerializer, "AUXIL_CODE4", customerNew.getSpeCode4().toString());
            addElement(createXmlSerializer, "AUXIL_CODE5", customerNew.getSpeCode5().toString());
            addElement(createXmlSerializer, this._AUTH_CODE, customerNew.getWarrantyCode().toString());
            addElement(createXmlSerializer, "CONTACT", customerNew.getRelatedPerson().toString());
            addElement(createXmlSerializer, "TAX_OFFICE", customerNew.getTaxOffice().toString());
            addElement(createXmlSerializer, "TAX_ID", customerNew.getTaxNo().toString());
            addElement(createXmlSerializer, "E_MAIL", customerNew.getEmail().toString());
            addElement(createXmlSerializer, "E_MAIL2", customerNew.getEmail2().toString());
            addElement(createXmlSerializer, "TELEPHONE1", customerNew.getTel1().toString());
            addElement(createXmlSerializer, "TELEPHONE2", customerNew.getTel2().toString());
            addElement(createXmlSerializer, "FAX", customerNew.getFax().toString());
            addElement(createXmlSerializer, "ADDRESS1", customerNew.getAddress1().toString());
            addElement(createXmlSerializer, "ADDRESS2", customerNew.getAddress2().toString());
            addElement(createXmlSerializer, "POSTAL_CODE", customerNew.getZipCode().toString());
            addElement(createXmlSerializer, "PAYMENT_CODE", baseErp.getLogoSqlHelper().getPaymentCode(customerNew.getPayPlan().getLogicalRef()));
            addElement(createXmlSerializer, "NAME", customerNew.getCustomerName().toString());
            addElement(createXmlSerializer, "SURNAME", customerNew.getCustomerSurname().toString());
            addElement(createXmlSerializer, "PERSCOMPANY", customerNew.getPersonalCompany().isSelect() ? 1 : 0);
            addElement(createXmlSerializer, "TCKNO", customerNew.getTCNo().toString());
            addElement(createXmlSerializer, "PURCHBRWS", 1);
            addElement(createXmlSerializer, "SALESBRWS", 1);
            addElement(createXmlSerializer, "IMPBRWS", 1);
            addElement(createXmlSerializer, "EXPBRWS", 1);
            addElement(createXmlSerializer, "FINBRWS", 1);
            addElement(createXmlSerializer, this._COUNTRY, customerNew.getCountry().toString());
            addElement(createXmlSerializer, this._COUNTRYCODE, customerNew.getCountry().getCode());
            addElement(createXmlSerializer, this._CITY, customerNew.getCity().toString());
            addElement(createXmlSerializer, this._CITYCODE, customerNew.getCity().getCode());
            addElement(createXmlSerializer, this._TOWN, customerNew.getTown().toString());
            addElement(createXmlSerializer, this._TOWNCODE, customerNew.getTown().getCode());
            Calendar instance2 = Calendar.getInstance();
            addElement(createXmlSerializer, this._DATE_CREATED, DateAndTimeUtils.getDateToFormat(instance2, "dd/MM/yyyy"));
            addElement(createXmlSerializer, this._HOUR_CREATED, instance2.get(11));
            addElement(createXmlSerializer, this._MIN_CREATED, instance2.get(12));
            addElement(createXmlSerializer, this._SEC_CREATED, instance2.get(13));
            endTag(createXmlSerializer, "AR_AP");
            endTag(createXmlSerializer, "AR_APS");
            createXmlSerializer.startDocument(this._ENCODING, null);
            createXmlSerializer.endDocument();
            str = byteArrayOutputStream.toString();
            Log.d(MobileSales.TAG, str);
            createXmlSerializer.flush();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        TigerServiceResult build = (TigerServiceResult) TigerServiceResult.newBuilder().withSendData(str).withData(customerNew).withDataType(DataObjectType.ADDCLCARD).build();
        build.setNotDuplicateControl(true);
        return build;
    }


    public TigerSelectResult getVisit(VisitInfo visitInfo) {
        return ((Tiger) ErpCreator.getInstance().getmBaseErp()).getTigerQueryCreator().insertVisit(visitInfo);
    }


    public TigerSelectResult getTodo(TodoInfoDb todoInfoDb) {
        return ((Tiger) ErpCreator.getInstance().getmBaseErp()).getTigerQueryCreator().insertTodoWorProc(todoInfoDb);
    }


    public TigerSelectResult getPenetration(Penetration penetration) {
        return ((Tiger) ErpCreator.getInstance().getmBaseErp()).getTigerQueryCreator().insertPenetration(penetration);
    }


    public List<TigerSelectResult> getPenetrationDetailList(Penetration penetration) {
        return new ArrayList();
    }


    public TigerSelectResult getCabinTrans(CabinTrans cabinTrans) {
        return ((Tiger) ErpCreator.getInstance().getmBaseErp()).getTigerQueryCreator().insertWorCabinTrans(cabinTrans);
    }


    public TigerSelectResult getCabin(int i2) {
        return ((Tiger) ErpCreator.getInstance().getmBaseErp()).getTigerQueryCreator().updateWorCabin(i2);
    }


    public TigerServiceResult getWhTransfer(@UnknownNullability PrintSlipModel sales) {
        return buildServiceResult(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper(), DataObjectType.ADDWHTRANSFER, sales.getClRef(), sales.getGDate(), 0, sales.getAndFicheNo(), getWhTransferXml(sales), (Object) sales, sales.getLogicalRef());
    }

    private String getWhTransferXml(Sales sales) {
        IOException e2;
        String str;
        boolean z;
        ByteArrayOutputStream byteArrayOutputStream;
        int i2;
        String str2;
        Iterator<SalesDetail> it;
        Calendar calendar;
        String str3;
        Iterator<SalesDetail> it2;
        String str4;
        String str5;
        double d2;
        String str6 = "dd.MM.yyyy HH:mm:ss";
        String str7 = "dd.MM.yyyy";
        TimingLogger timingLogger = new TimingLogger("CreateXml", "getWhTransferXml");
        timingLogger.addSplit("Start MATERIAL_SLIPS Xml Create ");
        String str8 = "";
        try {
            try {
                BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                XmlSerializer createXmlSerializer = createXmlSerializer(byteArrayOutputStream2, _OUTPUT_ENCODING);
                createXmlSerializer.startDocument(this._ENCODING, null);
                startTag(createXmlSerializer, this._MATERIAL_SLIPS);
                startTag(createXmlSerializer, this._SLIP);
                addAttribute(createXmlSerializer, this._ATTR_DBOP, this._ATTR_DROP_INS);
                addElement(createXmlSerializer, this._TYPE, 25);
                MatterSettings matterSettings = baseErp.getMatterSettings(ContextUtils.getmContext(), sales.getFicheType(sales.getmSalesType()));
                String str9 = "~";
                if (matterSettings.isUseMatterNo()) {
                    try {
                        if (matterSettings.getMatterArea() == MatterArea.FICHE_NO) {
                            String str10 = this._NUMBER;
                            if (!sales.getFicheNo().equals(str8)) {
                                str9 = sales.getFicheNo();
                            }
                            addElement(createXmlSerializer, str10, str9);
                        }
                    } catch (IOException e3) {
                        e = e3;
                        e2 = e;
                        e2.printStackTrace();
                        timingLogger.dumpToLog();
                        return str8;
                    }
                } else {
                    addElement(createXmlSerializer, this._NUMBER, str9);
                }
                addElement(createXmlSerializer, this._DATE, sales.getFicheCreateDate());
                addElement(createXmlSerializer, this._TIME, DateAndTimeUtils.getLogoDateTimeCode(sales.getGDate()));
                addElement(createXmlSerializer, this._ARP_CODE, getArpCode(sales));
                addElement(createXmlSerializer, this._DOC_TRACK_NR, sales.getDocumentTrackingNo().toString());
                addElement(createXmlSerializer, this._DOC_NUMBER, sales.getDocumentNo().toString());
                addElement(createXmlSerializer, this._AUTH_CODE, sales.getWarrantyCode().toString());
                addElement(createXmlSerializer, this._AUXIL_CODE, sales.getSpeCode().toString());
                addElement(createXmlSerializer, this._SOURCE_WH, sales.getTransferSourceWareHouse().getLogicalRef());
                addElement(createXmlSerializer, this._SOURCE_COST_GRP, sales.getTransferSourceCostGrp().getLogicalRef());
                addElement(createXmlSerializer, this._SOURCE_DIVISION_NR, sales.getTransferSourceBranch().getLogicalRef());
                addElement(createXmlSerializer, this._SOURCE_DEPARTMENT_NR, 0);
                addElement(createXmlSerializer, this._SOURCE_FACTORY_NR, sales.getTransferSourceFactory().getLogicalRef());
                addElement(createXmlSerializer, this._DEST_WH, sales.getWareHouse().getLogicalRef());
                int wareHouseCostGrp = baseErp.getLogoSqlHelper().getWareHouseCostGrp(sales.getWareHouse().getLogicalRef());
                addElement(createXmlSerializer, this._DEST_COST_GRP, wareHouseCostGrp);
                addElement(createXmlSerializer, this._DEST_DIVISION_NR, sales.getBranch().getLogicalRef());
                addElement(createXmlSerializer, this._DEST_DEPARTMENT_NR, sales.getDivision().getLogicalRef());
                addElement(createXmlSerializer, this._DEST_FACTORY, sales.getFactory().getLogicalRef());
                addElement(createXmlSerializer, this._FOOTNOTE1, sales.getExplanation1().toString());
                addElement(createXmlSerializer, this._FOOTNOTE2, sales.getExplanation2().toString());
                addElement(createXmlSerializer, this._FOOTNOTE3, sales.getExplanation3().toString());
                addElement(createXmlSerializer, this._FOOTNOTE4, sales.getExplanation4().toString());
                addElement(createXmlSerializer, this._GUID, sales.getAndFicheNo());
                addElement(createXmlSerializer, this._CREATED_BY, baseErp.getUser().getLogoNr());
                Calendar instance2 = Calendar.getInstance();
                addElement(createXmlSerializer, this._DATE_CREATED, DateAndTimeUtils.getDateToFormat(instance2, "dd/MM/yyyy"));
                addElement(createXmlSerializer, this._HOUR_CREATED, instance2.get(11));
                addElement(createXmlSerializer, this._MIN_CREATED, instance2.get(12));
                addElement(createXmlSerializer, this._SEC_CREATED, instance2.get(13));
                addElement(createXmlSerializer, this._DOC_DATE, sales.getFicheCreateDate());
                addElement(createXmlSerializer, this._DOC_TIME, DateAndTimeUtils.getLogoDateTimeCode(sales.getGDate()));
                addElement(createXmlSerializer, this._CURRSEL_TOTALS, "1");
                startTag(createXmlSerializer, this._TRANSACTIONS);
                timingLogger.addSplit("Start Invoice Xml Detail Create ");
                Iterator<SalesDetail> it3 = sales.getMSalesDetailList().iterator();
                while (it3.hasNext()) {
                    SalesDetail next = it3.next();
                    startTag(createXmlSerializer, this._TRANSACTION);
                    addElement(createXmlSerializer, this._ITEM_CODE, next.getCode());
                    addElement(createXmlSerializer, this._LINE_TYPE, next.getLineType());
                    addElement(createXmlSerializer, this._SOURCECOSTGRP, sales.getTransferSourceCostGrp().getLogicalRef());
                    addElement(createXmlSerializer, this._SOURCEINDEX, sales.getTransferSourceWareHouse().getLogicalRef());
                    addElement(createXmlSerializer, this._DESTINDEX, sales.getWareHouse().getLogicalRef());
                    addElement(createXmlSerializer, this._DESTCOSTGRP, wareHouseCostGrp);
                    addElement(createXmlSerializer, this._AUXIL_CODE, next.getSpeCode().toString());
                    addElement(createXmlSerializer, this._DELVRY_CODE, next.getDeliveryCode().toString());
                    addElement(createXmlSerializer, this._DESCRIPTION, next.getExplanation().toString());
                    if (next.getVariant().getLogicalRef() != -1) {
                        String[] variantCode = baseErp.getLogoSqlHelper().getVariantCode(next.getVariant().getLogicalRef());
                        str = str8;
                        z = false;
                        try {
                            addElement(createXmlSerializer, this._VARIANTCODE, variantCode[0]);
                            addElement(createXmlSerializer, this._VARIANTNAME, variantCode[1]);
                            addElement(createXmlSerializer, this._CAN_CONFIG, true);
                        } catch (IOException e4) {
                            e2 = e4;
                            str8 = str;
                            e2.printStackTrace();
                            timingLogger.dumpToLog();
                            return str8;
                        }
                    } else {
                        str = str8;
                        z = false;
                    }
                    int i3 = wareHouseCostGrp;
                    addElement(createXmlSerializer, this._QUANTITY, next.getAmount().getDefinitionDouble());
                    String unitCode = baseErp.getLogoSqlHelper().getUnitCode(next.getUnit().getLogicalRef(), next.isProduct());
                    addElement(createXmlSerializer, this._UNIT_CODE, unitCode);
                    addElement(createXmlSerializer, this._UNIT_CONV1, next.getConvFact1());
                    addElement(createXmlSerializer, this._UNIT_CONV2, next.getConvFact2());
                    if (next.getTrackType() == TrackType.NON_TRACK.getType() && !next.isLocTracking()) {
                        str3 = str6;
                        str2 = str7;
                        byteArrayOutputStream = byteArrayOutputStream2;
                        it = it3;
                        i2 = i3;
                        calendar = instance2;
                        addFicheDimensionsInfo(createXmlSerializer, baseErp, next.getItemRef(), next.getUnit().getLogicalRef());
                        endTag(createXmlSerializer, this._TRANSACTION);
                        str6 = str3;
                        instance2 = calendar;
                        str8 = str;
                        it3 = it;
                        str7 = str2;
                        wareHouseCostGrp = i2;
                        byteArrayOutputStream2 = byteArrayOutputStream;
                    }
                    startTag(createXmlSerializer, this._SL_DETAILS);
                    Iterator<Serilot> it4 = next.getSalesSerialLots().iterator();
                    while (it4.hasNext()) {
                        Serilot next2 = it4.next();
                        startTag(createXmlSerializer, this._SERIAL_LOT_TRN);
                        addElement(createXmlSerializer, this._SOURCE_SLT_REFERENCE, next2.logicalRef);
                        addElement(createXmlSerializer, this._SOURCE_MT_REFERENCE, next2.stTransRef);
                        addElement(createXmlSerializer, this._IOCODE, IoCode.WARE_HOUSE_OUTPUT.getType());
                        if (next2.mainUnitRef != next.getUnit().getLogicalRef()) {
                            double d3 = 0.0d;
                            if (next.getConvFact2() == 0.0d) {
                                str5 = str6;
                                str4 = str7;
                            } else {
                                str5 = str6;
                                str4 = str7;
                                d3 = (next2.amount * next.getConvFact1()) / next.getConvFact2();
                            }
                            it2 = it3;
                            d2 = d3;
                        } else {
                            str5 = str6;
                            str4 = str7;
                            d2 = next2.amount;
                            it2 = it3;
                        }
                        addElement(createXmlSerializer, this._QUANTITY, d2);
                        double d4 = next2.amount;
                        addElement(createXmlSerializer, this._MU_QUANTITY, d4);
                        addElement(createXmlSerializer, this._SOURCE_QUANTITY, calculateSourceQuantityForSeriLot(next2.sourceUnitRef, next, next2.amount));
                        addElement(createXmlSerializer, this._SL_CODE, next2.code);
                        addElement(createXmlSerializer, this._SL_TYPE, next.getTrackType());
                        addElement(createXmlSerializer, this._UNIT_CODE, unitCode);
                        addElement(createXmlSerializer, this._UNIT_CONV1, next.getConvFact1() * d2);
                        addElement(createXmlSerializer, this._UNIT_CONV2, next.getConvFact2() * d2);
                        addElement(createXmlSerializer, this._DATE_EXPIRED, next2.expDate);
                        addElement(createXmlSerializer, this._SOURCE_WH, sales.getTransferSourceWareHouse().getLogicalRef());
                        addElement(createXmlSerializer, this._REM_QUANTITY, d4);
                        addElement(createXmlSerializer, this._LU_REM_QUANTITY, d2);
                        if (next.getTrackType() == TrackType.SERIAL_GROUP.getType()) {
                            addElement(createXmlSerializer, this._GRP_BEG_CODE, next2.grpBegCode);
                            addElement(createXmlSerializer, this._GRP_END_CODE, next2.grpEndCode);
                        }
                        if (next.isLocTracking() || !TextUtils.isEmpty(next2.locationCode)) {
                            addElement(createXmlSerializer, this._LOCATION_CODE, next2.locationCode);
                            addElement(createXmlSerializer, this._DEST_LOCATION_CODE, next2.locationCode);
                        }
                        endTag(createXmlSerializer, this._SERIAL_LOT_TRN);
                        str6 = str5;
                        instance2 = instance2;
                        it4 = it4;
                        it3 = it2;
                        str7 = str4;
                        i3 = i3;
                        byteArrayOutputStream2 = byteArrayOutputStream2;
                    }
                    str3 = str6;
                    str2 = str7;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    it = it3;
                    i2 = i3;
                    calendar = instance2;
                    endTag(createXmlSerializer, this._SL_DETAILS);
                    addFicheDimensionsInfo(createXmlSerializer, baseErp, next.getItemRef(), next.getUnit().getLogicalRef());
                    endTag(createXmlSerializer, this._TRANSACTION);
                    str6 = str3;
                    instance2 = calendar;
                    str8 = str;
                    it3 = it;
                    str7 = str2;
                    wareHouseCostGrp = i2;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                }
                str = str8;
                timingLogger.addSplit("Start Detail Invoice Xml Create End ");
                timingLogger.addSplit("Start Add Invoice Xml Discount ");
                endTag(createXmlSerializer, this._TRANSACTIONS);
                addElement(createXmlSerializer, this._SHIPPING_AGENT, sales.getShipAgent().toString());
                if (sales.getEDespatch().isSelect()) {
                    EDocInfoModel eDocInfo = baseErp.getEDocInfo(sales.getClRef(), sales.getBranch().getLogicalRef());
                    addElement(createXmlSerializer, this._EDESPATCH, 1);
                    addElement(createXmlSerializer, this._EDESPATCH_PROFILEID, eDocInfo.getProfileIdEDespatch());
                    addElement(createXmlSerializer, this._SHIP_DATE, DateAndTimeUtils.getDateToFormat(instance2, str7));
                    addElement(createXmlSerializer, this._SHIP_TIME, DateAndTimeUtils.getLogoDateTimeCode(DateAndTimeUtils.getDateToFormat(instance2, str6)));
                    addElement(createXmlSerializer, this._DOC_DATE, DateAndTimeUtils.getDateToFormat(instance2, str7));
                    addElement(createXmlSerializer, this._DOC_TIME, DateAndTimeUtils.getLogoDateTimeCode(DateAndTimeUtils.getDateToFormat(instance2, str6)));
                    if (sales.getEDispatchAdditionalInfo() != null) {
                        addElement(createXmlSerializer, this._EINVOICE_DRIVERNAME1, sales.getEDispatchAdditionalInfo().firstDriverName);
                        addElement(createXmlSerializer, this._EINVOICE_DRIVERSURNAME1, sales.getEDispatchAdditionalInfo().firstDriverLastName);
                        addElement(createXmlSerializer, this._EINVOICE_DRIVERTCKNO1, sales.getEDispatchAdditionalInfo().firstDriverIdentityNr);
                        addElement(createXmlSerializer, this._EINVOICE_PLATENUM1, sales.getEDispatchAdditionalInfo().firstDriverPlate);
                        addElement(createXmlSerializer, this._EINVOICE_CHASSISNUM1, sales.getEDispatchAdditionalInfo().firstTrailerPlate);
                        addElement(createXmlSerializer, this._EINVOICE_DRIVERNAME2, sales.getEDispatchAdditionalInfo().secondDriverName);
                        addElement(createXmlSerializer, this._EINVOICE_DRIVERSURNAME2, sales.getEDispatchAdditionalInfo().secondDriverLastName);
                        addElement(createXmlSerializer, this._EINVOICE_DRIVERTCKNO2, sales.getEDispatchAdditionalInfo().secondDriverIdentityNr);
                        addElement(createXmlSerializer, this._EINVOICE_PLATENUM2, sales.getEDispatchAdditionalInfo().secondDriverPlate);
                        addElement(createXmlSerializer, this._EINVOICE_CHASSISNUM2, sales.getEDispatchAdditionalInfo().secondTrailerPlate);
                        addElement(createXmlSerializer, this._EINVOICE_DRIVERNAME3, sales.getEDispatchAdditionalInfo().thirdDriverName);
                        addElement(createXmlSerializer, this._EINVOICE_DRIVERSURNAME3, sales.getEDispatchAdditionalInfo().thirdDriverLastName);
                        addElement(createXmlSerializer, this._EINVOICE_DRIVERTCKNO3, sales.getEDispatchAdditionalInfo().thirdDriverIdentityNr);
                        addElement(createXmlSerializer, this._EINVOICE_PLATENUM3, sales.getEDispatchAdditionalInfo().thirdDriverPlate);
                        addElement(createXmlSerializer, this._EINVOICE_CHASSISNUM3, sales.getEDispatchAdditionalInfo().thirdTrailerPlate);
                    }
                }
                endTag(createXmlSerializer, this._SLIP);
                endTag(createXmlSerializer, this._MATERIAL_SLIPS);
                createXmlSerializer.endDocument();
                timingLogger.addSplit("Start Finis WhTransfer Xml Create ");
                str8 = byteArrayOutputStream2.toString();
                Log.d(MobileSales.TAG, str8);
                createXmlSerializer.flush();
                byteArrayOutputStream2.flush();
                byteArrayOutputStream2.close();
            } catch (IOException e5) {
                e = e5;
            }
            timingLogger.dumpToLog();
            return str8;
        } catch (Throwable th) {
            timingLogger.dumpToLog();
            throw th;
        }
    }


    public TigerServiceResult buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, int i2, String str, int i3, String str2, String str3, Object obj, int i4) {
        return (TigerServiceResult) TigerServiceResult.newBuilder().withSendData(str3).withData(obj).withApplyCampaign(i3).withClCode(iSqlHelper.getClCode(i2)).withClName(iSqlHelper.getClName(i2)).withMClRef(i2).withDate(str).withLogicalRef(i4).withDataType(dataObjectType).withGuid(str2).build();
    }


    public TigerServiceResult buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, int i2, String str, String str2, String str3, Object obj, int i3) {
        TigerServiceResult.TigerServiceBuilder withApplyCampaign = TigerServiceResult.newBuilder().withSendData(str3).withData(obj).withApplyCampaign(0);
        Context context = ContextUtils.getmContext();
        TransferOperationName transferOperationName = TransferOperationName.DEMAND;
        return (TigerServiceResult) withApplyCampaign.withClCode(ContextUtils.formatStringEnglish("%s %d", context.getString(transferOperationName.getResId()), Integer.valueOf(i2))).withClName(ContextUtils.formatStringEnglish("%s %d", ContextUtils.getmContext().getString(transferOperationName.getResId()), Integer.valueOf(i2))).withMClRef(i2).withDate(str).withLogicalRef(i3).withDataType(dataObjectType).withGuid(str2).build();
    }

    private XmlSerializer createXmlSerializer(OutputStream outputStream, String str) throws IOException {
        XmlSerializer newSerializer = Xml.newSerializer();
        newSerializer.setOutput(outputStream, str);
        return newSerializer;
    }

    private XmlSerializer createXmlSerializer(Writer writer) throws IOException {
        XmlSerializer newSerializer = Xml.newSerializer();
        newSerializer.setOutput(writer);
        return newSerializer;
    }

    private void startTag(XmlSerializer xmlSerializer, String str) throws IOException {
        xmlSerializer.startTag("", str);
    }

    private void endTag(XmlSerializer xmlSerializer, String str) throws IOException {
        xmlSerializer.endTag("", str);
    }

    private void addAttribute(XmlSerializer xmlSerializer, String str, String str2) throws IOException {
        xmlSerializer.attribute("", str, str2);
    }

    private void addElementWithoutControl(XmlSerializer xmlSerializer, String str, int i2) throws IOException {
        addElementWithoutControl(xmlSerializer, str, String.valueOf(i2));
    }

    private void addElementWithoutControl(XmlSerializer xmlSerializer, String str, String str2) throws IOException {
        xmlSerializer.startTag("", str);
        xmlSerializer.text(str2);
        xmlSerializer.endTag("", str);
    }

    private void addElementWithoutControl(XmlSerializer xmlSerializer, String str) throws IOException {
        xmlSerializer.startTag("", str);
        xmlSerializer.text("");
        xmlSerializer.endTag("", str);
    }

    private void addElement(XmlSerializer xmlSerializer, String str, String str2) throws IOException {
        if (!TextUtils.isEmpty(str2)) {
            xmlSerializer.startTag("", str);
            xmlSerializer.text(str2);
            xmlSerializer.endTag("", str);
        }
    }

    private void checkAndAddDeduct(XmlSerializer xmlSerializer, int i2) throws IOException {
        Item item;
        int i3;
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Item.class, "LOGICALREF=?", new String[]{String.valueOf(i2)}, null, null, null);
        if (table.size() == 1 && (i3 = (item = (Item) table.get(0)).candeduct) == 1) {
            addElement(xmlSerializer, this._CANDEDUCT, i3);
            addElement(xmlSerializer, this._DEDUCTCODE, item.deductcode);
            addElement(xmlSerializer, this._SALEDEDUCTPART1, item.salesDeductPart1);
            addElement(xmlSerializer, this._SALEDEDUCTPART2, item.salesDeductPart2);
        }
    }

    private void addElement(XmlSerializer xmlSerializer, String str, int i2) throws IOException {
        addElement(xmlSerializer, str, StringUtils.convertIntToString(i2));
    }

    private void addElement(XmlSerializer xmlSerializer, String str, double d2) throws IOException {
        addElement(xmlSerializer, str, StringUtils.convertDoubleToString(Double.valueOf(d2)));
    }

    private void addElement(XmlSerializer xmlSerializer, String str, boolean z) throws IOException {
        addElement(xmlSerializer, str, z ? 1 : 0);
    }

    public Sales getXmlToSales(Sales sales, String str, List<OrderAvailableAmount> list) {
        try {
            parseXml(sales, str, list);
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (ParserConfigurationException e3) {
            e3.printStackTrace();
        } catch (SAXException e4) {
            e4.printStackTrace();
        }
        return sales;
    }

    public void parseXml(Sales sales, String str, List<OrderAvailableAmount> list) throws ParserConfigurationException, SAXException, IOException {
        String str2;
        Document parseXml = DOMParser.newInstance().parseXml(str);
        parseXml.getDocumentElement().normalize();
        try {
            new Sales();
            if (sales.getMSalesDetailList().size() == 0) {
                parseHeader(parseXml, sales);
                setSalesEmptyFields(sales);
            }
            Sales clone = sales.clone();
            clone.getMSalesDetailList().clear();
            parseHeader(parseXml, clone);
            setSalesEmptyFields(clone);
            parseDetail(parseXml, clone, list);
            if (list == null || list.size() <= 0) {
                sales.setSalesFicheDiscountProps(clone.getSalesFicheDiscountProps().clone());
                sales.setTotal(clone.getTotal());
                sales.setDiscTotal(clone.getDiscTotal());
                sales.setTotalNet(clone.getTotalNet());
                sales.setTotalVat(clone.getTotalVat());
            } else {
                double d2 = 0.0d;
                for (int i2 = 0; i2 < clone.getDiscountLength(); i2++) {
                    d2 += clone.getDiscountTotal(i2).getDefinitionDouble();
                    clone.getDiscountTotal(i2);
                    FicheStringProp.setDefinition("0");
                }
                for (int i3 = 0; i3 < clone.getMSalesDetailList().size(); i3++) {
                    SalesDetail salesDetail = clone.getMSalesDetailList().get(i3);
                    int i4 = 0;
                    while (true) {
                        if (i4 >= salesDetail.getDiscountLength()) {
                            break;
                        }
                        if (!salesDetail.getDiscountTotal(i4).getDefinition().isEmpty() && salesDetail.getDiscountRatio(i4).getDefinition().isEmpty() && salesDetail.getDiscountCard(i4).getDefinition().isEmpty()) {
                            salesDetail.getDiscountTotal(i4);
                            FicheStringProp.setDefinition(StringUtils.convertDoubleToStringDot(Double.valueOf((salesDetail.getAmount().getDefinitionDouble() / salesDetail.getOrderAmount()) * salesDetail.getDiscountTotal(i4).getDefinitionDouble())));
                        }
                        if (salesDetail.getDiscountTotal(i4).getDefinition().isEmpty() && salesDetail.getDiscountRatio(i4).getDefinition().isEmpty() && salesDetail.getDiscountCard(i4).getDefinition().isEmpty()) {
                            salesDetail.getDiscountTotal(i4);
                            FicheStringProp.setDefinition(StringUtils.convertDoubleToStringDot(Double.valueOf((salesDetail.getProductTotal() / clone.getGrossTotal()) * d2)));
                            salesDetail.getDiscountTotal(i4).setGuid("");
                            break;
                        }
                        i4++;
                    }
                }
            }
            if (clone.getMSalesDetailList().size() <= 0 || !(list == null || list.size() == 0)) {
                str2 = "";
            } else {
                str2 = clone.getMSalesDetailList().get(0).getDeliveryDate().getDefinition();
            }
            Iterator<SalesDetail> it = clone.getMSalesDetailList().iterator();
            while (it.hasNext()) {
                SalesDetail next = it.next();
                if (!str2.equals(next.getDeliveryDate().getDefinition())) {
                    str2 = "";
                }
                if (!next.getCode().isEmpty()) {
                    sales.getMSalesDetailList().add(next.clone());
                }
            }
            if (!str2.isEmpty()) {
                sales.setDeliveryDate(new FicheDateProp(str2));
            }
        } catch (CloneNotSupportedException e2) {
            e2.printStackTrace();
        }
    }

    private void setSalesEmptyFields(Sales sales) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        if (sales.getWareHouse() == null || (sales.getWareHouse() != null && sales.getWareHouse().getLogicalRef() == -1)) {
            setSalesWarehouse(sales, baseErp, "0", new FicheRefProp());
        }
        if (sales.getDivision() == null || (sales.getDivision() != null && sales.getDivision().getLogicalRef() == -1)) {
            setSalesDivision(sales, baseErp, "0", new FicheRefProp());
        }
        if (sales.getFactory() == null || (sales.getFactory() != null && sales.getFactory().getLogicalRef() == -1)) {
            setSalesFactory(sales, baseErp, "0", new FicheRefProp());
        }
        if (sales.getBranch() == null || (sales.getBranch() != null && sales.getBranch().getLogicalRef() == -1)) {
            setSalesBranch(sales, baseErp, "0", new FicheRefProp());
        }
    }

    private double getOrderTotalQuantity(Document document) {
        NodeList nodeByTagName = DOMParser.newInstance().getNodeByTagName(document, this._QUANTITY);
        double d2 = 0.0d;
        if (nodeByTagName.getLength() > 0) {
            for (int i2 = 0; i2 < nodeByTagName.getLength(); i2++) {
                NodeList childNodes = nodeByTagName.item(i2).getChildNodes();
                if (childNodes.getLength() > 0) {
                    for (int i3 = 0; i3 < childNodes.getLength(); i3++) {
                        d2 += StringUtils.convertStringToDouble(childNodes.item(i3).getTextContent());
                    }
                }
            }
        }
        return d2;
    }

    private void parseHeader(Document document, Sales sales) {
        String str;
        List table;
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        FicheType ficheType = sales.getFicheType();
        if (FicheTypeControlUtils.isFicheTypeOrder(ficheType)) {
            str = this._ORDER_SLIP;
        } else if (FicheTypeControlUtils.isFicheTypeInvoiceOrRetailInvoice(ficheType)) {
            str = this._INVOICE;
        } else if (FicheTypeControlUtils.isFicheTypeDispatchOrOneToOne(ficheType)) {
            str = this._DISPATCH;
        } else {
            str = "";
        }
        NodeList nodeByTagName = DOMParser.newInstance().getNodeByTagName(document, str);
        if (nodeByTagName.getLength() > 0) {
            NodeList childNodes = nodeByTagName.item(0).getChildNodes();
            if (childNodes.getLength() > 0) {
                for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
                    Node item = childNodes.item(i2);
                    if (!item.getNodeName().equals("#text")) {
                        if (item.getNodeName().equals("TOTAL_DISCOUNTS")) {
                            sales.setDiscTotal(Double.valueOf(item.getTextContent()).doubleValue());
                        } else if (item.getNodeName().equals("TOTAL_NET")) {
                            sales.setTotalNet(Double.valueOf(item.getTextContent()).doubleValue());
                        } else if (!item.getNodeName().equals("TOTAL_PROMOTIONS")) {
                            if (item.getNodeName().equals("TOTAL_GROSS")) {
                                sales.setGrossTotal(Double.valueOf(item.getTextContent()).doubleValue());
                            } else if (item.getNodeName().equals("TOTAL_VAT")) {
                                sales.setTotalVat(Double.valueOf(item.getTextContent()).doubleValue());
                            } else if (item.getNodeName().equals("TOTAL_EXPENSES")) {
                                sales.setTotalExpenses(Double.valueOf(item.getTextContent()).doubleValue());
                            } else if (item.getNodeName().equals(this._NUMBER)) {
                                sales.setFicheNo(item.getTextContent());
                            } else if (item.getNodeName().equals(this._PROJECT_CODE)) {
                                List table2 = baseErp.getLogoSqlHelper().getTable(Project.class, "CODE=?", new String[]{String.valueOf(item.getTextContent())});
                                if (table2 != null && table2.size() > 0) {
                                    sales.setProjectCode(new FicheDiscountRefProp(((Project) table2.get(0)).getLogicalRef(), -1, ((Project) table2.get(0)).getProje(), item.getTextContent()));
                                }
                            } else if (item.getNodeName().equals(this._DOC_NUMBER)) {
                                sales.setDocumentNo(new FicheStringProp(item.getTextContent()));
                            } else if (item.getNodeName().equals(this._AUXIL_CODE)) {
                                List table3 = baseErp.getLogoSqlHelper().getTable(Specodes.class, "SPECODE=?", new String[]{String.valueOf(item.getTextContent())});
                                if (table3 != null && table3.size() > 0) {
                                    sales.setSpeCode(new FicheRefProp(((Specodes) table3.get(0)).getLogicalRef(), -1, item.getTextContent()));
                                }
                            } else if (item.getNodeName().equals(this._AUTH_CODE)) {
                                sales.setWarrantyCode(new FicheRefProp(-1, -1, item.getTextContent()));
                            } else if (item.getNodeName().equals(this._SOURCE_WH)) {
                                setSalesWarehouse(sales, baseErp, item.getTextContent(), new FicheRefProp());
                            } else if (item.getNodeName().equals(this._PAYMENT_CODE)) {
                                List table4 = baseErp.getLogoSqlHelper().getTable(Payment.class, "CODE=?", new String[]{String.valueOf(item.getTextContent())});
                                if (table4 != null && table4.size() > 0) {
                                    sales.setPayPlan(new FicheDiscountRefProp(((Payment) table4.get(0)).getLogicalRef(), -1, ((Payment) table4.get(0)).getOdemePlan(), ((Payment) table4.get(0)).getCode()));
                                }
                            } else if (item.getNodeName().equals(this._DEPARTMENT)) {
                                setSalesDivision(sales, baseErp, item.getTextContent(), null);
                            } else if (item.getNodeName().equals(this._TRADING_GRP)) {
                                List table5 = baseErp.getLogoSqlHelper().getTable(Tradinggrp.class, "CODE=?", new String[]{String.valueOf(item.getTextContent())});
                                if (table5 != null && table5.size() > 0) {
                                    sales.setTradeGroup(new FicheRefProp(((Tradinggrp) table5.get(0)).getLogicalRef(), -1, ((Tradinggrp) table5.get(0)).getCode()));
                                    sales.setNotUseGattribKdv(CalculateUtils.isGattribNotUseKdv(((Tradinggrp) table5.get(0)).getGattrib()));
                                }
                            } else if (item.getNodeName().equals(this._FACTORY)) {
                                setSalesFactory(sales, baseErp, item.getTextContent(), null);
                            } else if (item.getNodeName().equals(this._DIVISION)) {
                                setSalesBranch(sales, baseErp, item.getTextContent(), null);
                            } else if (item.getNodeName().equals(this._DATA_REFERENCE)) {
                                sales.setOrderRef(StringUtils.convertStringToInt(item.getTextContent()));
                            } else if (item.getNodeName().equals(this._NOTES1)) {
                                sales.setExplanation1(new FicheStringProp(item.getTextContent()));
                            } else if (item.getNodeName().equals(this._NOTES2)) {
                                sales.setExplanation2(new FicheStringProp(item.getTextContent()));
                            } else if (item.getNodeName().equals(this._NOTES3)) {
                                sales.setExplanation3(new FicheStringProp(item.getTextContent()));
                            } else if (item.getNodeName().equals(this._NOTES4)) {
                                sales.setExplanation4(new FicheStringProp(item.getTextContent()));
                            } else if (item.getNodeName().equals(this._DOC_TRACK_NR)) {
                                sales.setDocumentTrackingNo(new FicheStringProp(item.getTextContent()));
                            } else if (item.getNodeName().equals(this._CUST_ORD_NO)) {
                                sales.setCustomerOrderNo(new FicheStringProp(item.getTextContent()));
                            } else if (item.getNodeName().equals(this._WITH_PAYMENT)) {
                                sales.setPaymentOrder(new FicheBooleanProp(item.getTextContent().equals("1")));
                            } else if (item.getNodeName().equals(this._ARP_CODE_SHPM)) {
                                List table6 = baseErp.getLogoSqlHelper().getTable(ClCard.class, "CODE=?", new String[]{String.valueOf(item.getTextContent())});
                                if (table6 != null && table6.size() > 0) {
                                    sales.setShipAccount(new FicheDiscountRefProp(((ClCard) table6.get(0)).getLogicalRef(), -1, ((ClCard) table6.get(0)).getDefinition(), ((ClCard) table6.get(0)).getCode()));
                                }
                            } else if (item.getNodeName().equals(this._SHIPLOC_CODE)) {
                                List table7 = baseErp.getLogoSqlHelper().getTable(ShipAddress.class, "CODE=?", new String[]{String.valueOf(item.getTextContent())});
                                if (table7 != null && table7.size() > 0) {
                                    sales.setShipAddress(new FicheDiscountRefProp(((ShipAddress) table7.get(0)).getLogicalRef(), -1, table7.get(0).toString(), ((ShipAddress) table7.get(0)).getCode()));
                                }
                            } else if (item.getNodeName().equals(this._SHIPPING_AGENT)) {
                                List table8 = baseErp.getLogoSqlHelper().getTable(ShipAgent.class, "CODE=?", new String[]{String.valueOf(item.getTextContent())});
                                if (table8 != null && table8.size() > 0) {
                                    sales.setShipAgent(new FicheRefProp(((ShipAgent) table8.get(0)).getLogicalRef(), -1, ((ShipAgent) table8.get(0)).getCode()));
                                }
                            } else if (item.getNodeName().equals(this._SHIPMENT_TYPE) && (table = baseErp.getLogoSqlHelper().getTable(ShipType.class, "CODE=?", new String[]{String.valueOf(item.getTextContent())})) != null && table.size() > 0) {
                                sales.setShipType(new FicheRefProp(((ShipType) table.get(0)).getLogicalRef(), -1, ((ShipType) table.get(0)).getCode()));
                            }
                        }
                    }
                }
            }
        }
        sales.setTotal(sales.getTotalNet() - sales.getTotalVat());
    }

    private void setSalesBranch(Sales sales, BaseErp baseErp, String str, FicheRefProp ficheRefProp) {
        if (sales.getBranch() == null) {
            ficheRefProp = new FicheRefProp();
        } else {
            List table = baseErp.getLogoSqlHelper().getTable(Branch.class, "NR=?", new String[]{str});
            if (table != null && table.size() > 0) {
                ficheRefProp = new FicheRefProp(((Branch) table.get(0)).getNr(), -1, ((Branch) table.get(0)).getIsYeri());
            }
        }
        sales.setBranch(ficheRefProp);
    }

    private void setSalesFactory(Sales sales, BaseErp baseErp, String str, FicheRefProp ficheRefProp) {
        if (sales.getFactory() == null) {
            ficheRefProp = new FicheRefProp();
        } else {
            List table = baseErp.getLogoSqlHelper().getTable(Factory.class, "NR=?", new String[]{str});
            if (table != null && table.size() > 0) {
                ficheRefProp = new FicheRefProp(((Factory) table.get(0)).getNr(), -1, ((Factory) table.get(0)).getFabrika());
            }
        }
        sales.setFactory(ficheRefProp);
    }

    private void setSalesDivision(Sales sales, BaseErp baseErp, String str, FicheRefProp ficheRefProp) {
        if (sales.getDivision() == null) {
            ficheRefProp = new FicheRefProp();
        } else {
            List table = baseErp.getLogoSqlHelper().getTable(Division.class, "NR=?", new String[]{str});
            if (table != null && table.size() > 0) {
                ficheRefProp = new FicheRefProp(((Division) table.get(0)).getNr(), -1, ((Division) table.get(0)).getBolum());
            }
        }
        sales.setDivision(ficheRefProp);
    }

    private void setSalesWarehouse(Sales sales, BaseErp baseErp, String str, FicheRefProp ficheRefProp) {
        if (sales.getWareHouse() == null) {
            ficheRefProp = new FicheRefProp();
        } else {
            List table = baseErp.getLogoSqlHelper().getTable(WareHouse.class, "NR=?", new String[]{str});
            if (table != null && table.size() > 0) {
                ficheRefProp = new FicheRefProp(((WareHouse) table.get(0)).getNr(), -1, ((WareHouse) table.get(0)).getAmbar());
            }
        }
        sales.setWareHouse(ficheRefProp);
    }

    private void setSalesDetailWarehouse(SalesDetail salesDetail, BaseErp baseErp, String str, FicheRefProp ficheRefProp) {
        try {
            if (salesDetail.getWareHouse() == null) {
                ficheRefProp = new FicheRefProp();
            } else {
                List table = baseErp.getLogoSqlHelper().getTable(WareHouse.class, "NR=?", new String[]{str});
                if (table != null && table.size() > 0) {
                    ficheRefProp = new FicheRefProp(((WareHouse) table.get(0)).getNr(), -1, ((WareHouse) table.get(0)).getAmbar());
                }
            }
            salesDetail.setWareHouse(ficheRefProp);
        } catch (Exception e2) {
            Log.e(TAG, "setSalesDetailWarehouse", e2);
        }
    }

    private void parseDetail(Document document, Sales sales, List<OrderAvailableAmount> list) {
        int i2;
        NodeList nodeList;
        int i3;
        int i4 = 0;
        int i5 = 0;
        boolean z;
        int i6;
        boolean z2;
        int i7;
        boolean z3;
        int i8;
        int i9;
        int i10 = 0;
        boolean z4 = list != null && list.size() > 0;
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        NodeList nodeByTagName = DOMParser.newInstance().getNodeByTagName(document, this._TRANSACTIONS);
        ISqlHelper logoSqlHelper = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
        if (nodeByTagName.getLength() > 0) {
            SalesDetail salesDetail = null;
            int i11 = 0;
            int i12 = 0;
            int i13 = 0;
            for (NodeList childNodes = nodeByTagName.item(0).getChildNodes(); i11 < childNodes.getLength(); childNodes = nodeList) {
                Node item = childNodes.item(i11);
                if (!item.getNodeName().equals("#text") && item.getNodeName().equals(this._TRANSACTION)) {
                    NodeList childNodes2 = item.getChildNodes();
                    int intValue = Integer.valueOf(DOMParser.newInstance().getChildNodeByNodeList(childNodes2, this._TYPE).getTextContent()).intValue();
                    if (intValue == 2) {
                        if (TextUtils.isEmpty(DOMParser.newInstance().getChildNodeByNodeList(childNodes2, this._DETAIL_LEVEL).getTextContent())) {
                            i9 = DiscountDetailLevel._LINE.getLevel();
                        } else {
                            i9 = StringUtils.convertStringToInt(DOMParser.newInstance().getChildNodeByNodeList(childNodes2, this._DETAIL_LEVEL).getTextContent());
                        }
                        int convertStringToInt = StringUtils.convertStringToInt(DOMParser.newInstance().getChildNodeByNodeList(childNodes2, this._CALC_TYPE).getTextContent());
                        String textContent = DOMParser.newInstance().getChildNodeByNodeList(childNodes2, this._GUID).getTextContent();
                        if (i9 == DiscountDetailLevel._GENERAL.getLevel()) {
                            if (!findDiscount(sales, textContent)) {
                                int i14 = DiscountType.DISCTOTAL.value;
                                if (convertStringToInt != i14 && z4) {
                                    convertStringToInt = i14;
                                }
                                addGeneralDiscount(childNodes2, sales, i12, convertStringToInt);
                            }
                            i12++;
                        } else {
                            if (i9 == DiscountDetailLevel._LINE.getLevel() && salesDetail != null) {
                                if (!findDiscount(salesDetail, textContent)) {
                                    addDetailDiscount(childNodes2, salesDetail, i13, convertStringToInt);
                                }
                                i13++;
                            }
                            nodeList = childNodes;
                            i2 = i11;
                            i5 = i12;
                            i4 = i13;
                            i3 = 0;
                        }
                        nodeList = childNodes;
                        i2 = i11;
                        i3 = 0;
                        i11 = i2 + 1;
                        i10 = i3;
                    } else {
                        LineType lineType = LineType.PRODUCT;
                        if (intValue == lineType.value || intValue == LineType.COMPOSITE_COLI.value || intValue == LineType.PROMOTION.value || intValue == LineType.SERVICE.value) {
                            String textContent2 = DOMParser.newInstance().getChildNodeByNodeList(childNodes2, this._MASTER_CODE).getTextContent();
                            nodeList = childNodes;
                            String textContent3 = DOMParser.newInstance().getChildNodeByNodeList(childNodes2, this._GUID).getTextContent();
                            i5 = i12;
                            String textContent4 = DOMParser.newInstance().getChildNodeByNodeList(childNodes2, this._DATA_REFERENCE).getTextContent();
                            i4 = i13;
                            String textContent5 = DOMParser.newInstance().getChildNodeByNodeList(childNodes2, this._DUE_DATE).getTextContent();
                            i2 = i11;
                            if (intValue == lineType.value || intValue == LineType.COMPOSITE_COLI.value) {
                                SalesDetail findSalesDetail = findSalesDetail(sales, textContent2, intValue, textContent3);
                                if (findSalesDetail == null) {
                                    findSalesDetail = new SalesDetail();
                                    if (textContent4 != null && !textContent4.isEmpty() && z4) {
                                        findSalesDetail.setOrderDetailReference(StringUtils.convertStringToInt(textContent4));
                                    }
                                    z = false;
                                } else {
                                    z = true;
                                }
                                findSalesDetail.setCardType(intValue);
                                findSalesDetail.getLineType();
                                findSalesDetail.setCode(textContent2);
                                findSalesDetail.setProduct(true);
                                if (textContent5 != null && !textContent5.isEmpty()) {
                                    findSalesDetail.setDeliveryDate(new FicheDateProp(textContent5));
                                }
                                List table = baseErp.getLogoSqlHelper().getTable(Item.class, "CODE=?", new String[]{textContent2});
                                if (table == null || table.size() <= 0) {
                                    i3 = 0;
                                } else {
                                    i3 = 0;
                                    findSalesDetail.setName(((Item) table.get(0)).name);
                                    findSalesDetail.setItemRef(((Item) table.get(0)).logicalRef);
                                    findSalesDetail.setTrackType(((Item) table.get(0)).trackType);
                                    List table2 = baseErp.getLogoSqlHelper().getTable(ItemStock.class, "ITEMREF=? AND WAREHOUSENR=?", new String[]{Integer.toString(findSalesDetail.getItemRef()), Integer.toString(sales.getWareHouse().getLogicalRef())});
                                    if (table2 != null && table2.size() > 0) {
                                        Iterator it = table2.iterator();
                                        while (true) {
                                            if (!it.hasNext()) {
                                                break;
                                            }
                                            ItemStock itemStock = (ItemStock) it.next();
                                            if (itemStock.wareHouseNr == sales.getWareHouse().getLogicalRef()) {
                                                findSalesDetail.setActualStock(itemStock.realStock);
                                                break;
                                            }
                                        }
                                    }
                                }
                                setSalesDetail(childNodes2, findSalesDetail, z, logoSqlHelper);
                                if (z4) {
                                    setOrderItemAvailableAmount(findSalesDetail, list);
                                    if (findSalesDetail.getOrderAvailableAmount() == 0.0d) {
                                        salesDetail = findSalesDetail;
                                        i13 = i3;
                                        i12 = i5;
                                    } else {
                                        findSalesDetail.getOrderAvailableAmount();
                                    }
                                }
                                if (findSalesDetail.getPrice().getDefinition().equalsIgnoreCase(StringUtils.convertDoubleToString(Double.valueOf(findSalesDetail.getUsePrice()))) && !findSalesDetail.getSelectedPrice().getCode().isEmpty()) {
                                    setPrice(findSalesDetail, 2, findSalesDetail.getSelectedPrice().getCode());
                                }
                                findSalesDetail.calculateFiche(sales.isNotUseGattribKdv());
                                if (!z) {
                                    ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
                                    if (sales.getMSalesDetailList().contains(findSalesDetail)) {
                                        i6 = sales.getMSalesDetailList().indexOf(findSalesDetail) + 1;
                                    } else {
                                        i6 = sales.getMSalesDetailList().size();
                                    }
                                    mSalesDetailList.add(i6, findSalesDetail);
                                }
                                salesDetail = findSalesDetail;
                                i13 = i3;
                                i12 = i5;
                            } else if (intValue == LineType.PROMOTION.value) {
                                SalesDetail findSalesDetail2 = findSalesDetail(sales, textContent2, intValue, textContent3);
                                if (findSalesDetail2 == null) {
                                    findSalesDetail2 = new SalesDetail();
                                    z3 = false;
                                } else {
                                    z3 = true;
                                }
                                findSalesDetail2.setCode(textContent2);
                                findSalesDetail2.setItemRef(logoSqlHelper.getItemLogicalRef(textContent2));
                                findSalesDetail2.setName(logoSqlHelper.getItemName(textContent2));
                                findSalesDetail2.setPromotion(new FicheBooleanProp(true));
                                findSalesDetail2.setProduct(true);
                                findSalesDetail2.setCardType(intValue);
                                findSalesDetail2.getLineType();
                                setSalesDetail(childNodes2, findSalesDetail2, z3, logoSqlHelper);
                                setPrice(findSalesDetail2, 2, findSalesDetail2.getPrice().getDefinition());
                                findSalesDetail2.calculateFiche(sales.isNotUseGattribKdv());
                                if (!z3) {
                                    ArrayList<SalesDetail> mSalesDetailList2 = sales.getMSalesDetailList();
                                    if (sales.getMSalesDetailList().contains(salesDetail)) {
                                        i8 = sales.getMSalesDetailList().indexOf(salesDetail) + 1;
                                    } else {
                                        i8 = sales.getMSalesDetailList().size();
                                    }
                                    mSalesDetailList2.add(i8, findSalesDetail2);
                                }
                                salesDetail.calculateFiche(sales.isNotUseGattribKdv());
                                i12 = i5;
                                i13 = i4;
                                i3 = 0;
                            } else {
                                if (intValue == LineType.SERVICE.value) {
                                    SalesDetail findSalesDetail3 = findSalesDetail(sales, textContent2, intValue, textContent3);
                                    if (findSalesDetail3 == null) {
                                        findSalesDetail3 = new SalesDetail();
                                        z2 = false;
                                    } else {
                                        z2 = true;
                                    }
                                    findSalesDetail3.setProduct(false);
                                    findSalesDetail3.setCardType(intValue);
                                    findSalesDetail3.getLineType();
                                    findSalesDetail3.setCode(textContent2);
                                    List table3 = baseErp.getLogoSqlHelper().getTable(Service.class, "CODE=?", new String[]{textContent2});
                                    if (table3 != null && table3.size() > 0) {
                                        findSalesDetail3.setName(((Service) table3.get(0)).getName());
                                        findSalesDetail3.setItemRef(((Service) table3.get(0)).getLogicalRef());
                                    }
                                    setSalesDetailService(childNodes2, findSalesDetail3, z2, logoSqlHelper);
                                    if (findSalesDetail3.getPrice().getDefinition().equalsIgnoreCase(StringUtils.convertDoubleToString(Double.valueOf(findSalesDetail3.getUsePrice()))) && !findSalesDetail3.getSelectedPrice().getCode().isEmpty()) {
                                        setPrice(findSalesDetail3, 4, findSalesDetail3.getPrice().getDefinition());
                                    }
                                    findSalesDetail3.calculateFiche(sales.isNotUseGattribKdv());
                                    if (!z2) {
                                        ArrayList<SalesDetail> mSalesDetailList3 = sales.getMSalesDetailList();
                                        if (sales.getMSalesDetailList().contains(findSalesDetail3)) {
                                            i7 = sales.getMSalesDetailList().indexOf(findSalesDetail3) + 1;
                                        } else {
                                            i7 = sales.getMSalesDetailList().size();
                                        }
                                        mSalesDetailList3.add(i7, findSalesDetail3);
                                    }
                                    salesDetail = findSalesDetail3;
                                    i12 = i5;
                                    i3 = 0;
                                    i13 = 0;
                                }
                                i3 = 0;
                            }
                            i11 = i2 + 1;
                            i10 = i3;
                        }
                        nodeList = childNodes;
                        i2 = i11;
                        i5 = i12;
                        i4 = i13;
                        i3 = 0;
                    }
                } else {
                    nodeList = childNodes;
                    i2 = i11;
                    i5 = i12;
                    i4 = i13;
                    i3 = i10;
                }
                i12 = i5;
                i13 = i4;
                i11 = i2 + 1;
                i10 = i3;
            }
        }
    }

    private void setSalesDetail(NodeList nodeList, SalesDetail salesDetail, boolean z, ISqlHelper iSqlHelper) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
            Node item = nodeList.item(i2);
            if (!item.getNodeName().equals("#text")) {
                if (item.getNodeName().equals(this._QUANTITY)) {
                    salesDetail.setAmount(new FicheStringProp(item.getTextContent()));
                } else if (item.getNodeName().equals(this._VAT_RATE)) {
                    salesDetail.setVat(new FicheStringProp(item.getTextContent()));
                } else if (item.getNodeName().equals(this._VAT_INCLUDED)) {
                    salesDetail.setIncludeVat(new FicheBooleanProp(item.getTextContent().equals("1")));
                } else if (item.getNodeName().equals(this._UNIT_CODE)) {
                    salesDetail.setUnit(new FicheDiscountRefProp());
                    salesDetail.getUnit().setCode(item.getTextContent());
                    salesDetail.getUnit().setLogicalRef(iSqlHelper.getUnitLogicalRef(salesDetail.getUnit().getCode(), salesDetail.getItemRef()));
                } else if (item.getNodeName().equals(this._UNIT_CONV1)) {
                    salesDetail.setConvFact1(Double.valueOf(item.getTextContent()).doubleValue());
                } else if (item.getNodeName().equals(this._UNIT_CONV2)) {
                    salesDetail.setConvFact2(Double.valueOf(item.getTextContent()).doubleValue());
                } else if (!item.getNodeName().equals(this._CURR_PRICE)) {
                    if (item.getNodeName().equals("PRCLISTCODE")) {
                        if (salesDetail.getPrice().getDefinition().equalsIgnoreCase(StringUtils.convertDoubleToString(Double.valueOf(salesDetail.getUsePrice())))) {
                            salesDetail.setSelectedPrice(new FicheDiscountRefProp(-1, -1, "", item.getTextContent()));
                        }
                    } else if (item.getNodeName().equals(this._GUID) && !z) {
                        salesDetail.setGuid(item.getTextContent());
                    } else if (item.getNodeName().equals(this._CAMPAIGN_INFOS)) {
                        salesDetail.setCampaignCode(getCampaignCode(item));
                    } else if (item.getNodeName().equals(this._PRICE)) {
                        salesDetail.setPrice(new FicheRefProp(-1, -1, StringUtils.convertDoubleToString(Double.valueOf(StringUtils.convertStringToDouble(item.getTextContent())))));
                    } else if (item.getNodeName().equals(this._PC_PRICE)) {
                        salesDetail.setUsePrice(StringUtils.convertStringToDouble(item.getTextContent()));
                    } else if (item.getNodeName().equals(this._DETAIL_LEVEL)) {
                        salesDetail.setGlobalLineType(StringUtils.convertStringToInt(item.getTextContent()));
                    } else if (item.getNodeName().equals(this._VARIANT_CODE)) {
                        List table = baseErp.getLogoSqlHelper().getTable(Variant.class, "ITEMREF=? AND CODE=?", new String[]{Integer.toString(salesDetail.getItemRef()), item.getTextContent()});
                        if (table != null && table.size() > 0) {
                            salesDetail.setHasVariant(true);
                            salesDetail.setVariant(new FicheDiscountRefProp(((Variant) table.get(0)).getLogicalRef(), -1, ((Variant) table.get(0)).getName(), ((Variant) table.get(0)).getCode()));
                        }
                    } else if (item.getNodeName().equals(this._PAYMENT_CODE)) {
                        List table2 = baseErp.getLogoSqlHelper().getTable(Payment.class, "CODE=?", new String[]{item.getTextContent()});
                        if (table2 != null && table2.size() > 0) {
                            salesDetail.setPayment(new FicheDiscountRefProp(((Payment) table2.get(0)).getLogicalRef(), -1, ((Payment) table2.get(0)).getOdemePlan(), ((Payment) table2.get(0)).getCode()));
                        }
                    } else if (item.getNodeName().equals(this._SOURCE_WH)) {
                        setSalesDetailWarehouse(salesDetail, baseErp, item.getTextContent(), null);
                    } else if (item.getNodeName().equals(this._SOURCE_INDEX)) {
                        setSalesDetailWarehouse(salesDetail, baseErp, item.getTextContent(), null);
                    }
                }
            }
        }
        if (salesDetail.getWareHouse() == null || (salesDetail.getWareHouse() != null && salesDetail.getWareHouse().getLogicalRef() == -1)) {
            setSalesDetailWarehouse(salesDetail, baseErp, "0", new FicheRefProp());
        }
        FicheStringProp.setDefinition(String.format("%s ( %d : %d )", salesDetail.getUnit().getCode(), Long.valueOf(Math.round(salesDetail.getConvFact1())), Long.valueOf(Math.round(salesDetail.getConvFact2()))));
    }

    private void setSalesDetailService(NodeList nodeList, SalesDetail salesDetail, boolean z, ISqlHelper iSqlHelper) {
        for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
            Node item = nodeList.item(i2);
            if (item.getNodeName().equals(this._QUANTITY)) {
                salesDetail.setAmount(new FicheStringProp(item.getTextContent()));
            } else if (item.getNodeName().equals(this._VAT_RATE)) {
                salesDetail.setVat(new FicheStringProp(item.getTextContent()));
            } else if (item.getNodeName().equals(this._VAT_INCLUDED)) {
                salesDetail.setIncludeVat(new FicheBooleanProp(item.getTextContent().equals("1")));
            } else if (item.getNodeName().equals(this._UNIT_CODE)) {
                salesDetail.setUnit(new FicheDiscountRefProp());
                salesDetail.getUnit().setCode(item.getTextContent());
                salesDetail.getUnit().setLogicalRef(iSqlHelper.getServiceUnitLogicalRef(salesDetail.getUnit().getCode(), salesDetail.getItemRef()));
            } else if (item.getNodeName().equals(this._UNIT_CONV1)) {
                salesDetail.setConvFact1(Double.valueOf(item.getTextContent()).doubleValue());
            } else if (item.getNodeName().equals(this._UNIT_CONV2)) {
                salesDetail.setConvFact2(Double.valueOf(item.getTextContent()).doubleValue());
            } else if (!item.getNodeName().equals(this._CURR_PRICE)) {
                if (item.getNodeName().equals("PRCLISTCODE")) {
                    if (salesDetail.getPrice().getDefinition().equalsIgnoreCase(StringUtils.convertDoubleToString(Double.valueOf(salesDetail.getUsePrice())))) {
                        salesDetail.setSelectedPrice(new FicheDiscountRefProp(-1, -1, "", item.getTextContent()));
                    }
                } else if (item.getNodeName().equals(this._GUID) && !z) {
                    salesDetail.setGuid(item.getTextContent());
                } else if (item.getNodeName().equals(this._CAMPAIGN_INFOS)) {
                    salesDetail.setCampaignCode(getCampaignCode(item));
                } else if (item.getNodeName().equals(this._PRICE)) {
                    salesDetail.setPrice(new FicheRefProp(-1, -1, StringUtils.convertDoubleToString(Double.valueOf(StringUtils.convertStringToDouble(item.getTextContent())))));
                } else if (item.getNodeName().equals(this._PC_PRICE)) {
                    salesDetail.setUsePrice(StringUtils.convertStringToDouble(item.getTextContent()));
                }
            }
        }
        FicheStringProp.setDefinition(String.format("%s ( %d : %d )", salesDetail.getUnit().getCode(), Long.valueOf(Math.round(salesDetail.getConvFact1())), Long.valueOf(Math.round(salesDetail.getConvFact2()))));
    }

    private void addGeneralDiscount(NodeList nodeList, Sales sales, int i2, int i3) {
        String str = "";
        String str2 = str;
        String str3 = str2;
        String str4 = str3;
        boolean z = false;
        for (int i4 = 0; i4 < nodeList.getLength(); i4++) {
            Node item = nodeList.item(i4);
            if (item.getNodeName().equals(this._MASTER_CODE)) {
                str = item.getTextContent();
            } else if (item.getNodeName().equals(this._DISCOUNT_RATE) && i3 == DiscountType.DISCRATIO.value) {
                str2 = item.getTextContent();
            } else if (item.getNodeName().equals(this._TOTAL) && i3 == DiscountType.DISCTOTAL.value) {
                str2 = item.getTextContent();
                z = true;
                str = "";
            } else if (item.getNodeName().equals(this._GUID)) {
                str3 = item.getTextContent();
            } else if (item.getNodeName().equals(this._CAMPAIGN_INFOS)) {
                str4 = getCampaignCode(item);
            }
        }
        setDiscount(sales, str, str2, TextUtils.isEmpty(str), z, i2, str3, str4);
    }

    private String getCampaignCode(Node node) {
        NodeList childNodes = node.getChildNodes();
        String str = "";
        if (childNodes.getLength() > 0) {
            for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
                NodeList childNodes2 = childNodes.item(i2).getChildNodes();
                if (childNodes2.getLength() > 0) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= childNodes2.getLength()) {
                            break;
                        } else if (childNodes2.item(i3).getNodeName().equals("CAMPCODE1")) {
                            str = childNodes2.item(i3).getTextContent();
                            break;
                        } else {
                            i3++;
                        }
                    }
                }
            }
        }
        return str;
    }

    private void addDetailDiscount(NodeList nodeList, SalesDetail salesDetail, int i2, int i3) {
        String str = "";
        String str2 = str;
        String str3 = str2;
        String str4 = str3;
        boolean z = false;
        for (int i4 = 0; i4 < nodeList.getLength(); i4++) {
            Node item = nodeList.item(i4);
            if (item.getNodeName().equals(this._MASTER_CODE)) {
                str = item.getTextContent();
            } else if (item.getNodeName().equals(this._DISCOUNT_RATE) && i3 == DiscountType.DISCRATIO.value) {
                str2 = item.getTextContent();
            } else if (item.getNodeName().equals(this._TOTAL) && i3 == DiscountType.DISCTOTAL.value) {
                str2 = item.getTextContent();
                z = true;
            } else if (item.getNodeName().equals(this._GUID)) {
                str3 = item.getTextContent();
            } else if (item.getNodeName().equals(this._CAMPAIGN_INFOS)) {
                str4 = getCampaignCode(item);
            }
        }
        setDiscount(salesDetail, str, str2, TextUtils.isEmpty(str), z, i2, str3, str4);
    }

    private void setDiscount(SalesDetail salesDetail, String str, String str2, boolean z, boolean z2, int i2, String str3, String str4) {
        if (z) {
            if (!z2) {
                if (!salesDetail.getDiscountRatio(i2).getDefinition().equals(str2)) {
                    salesDetail.getDiscountRatio(i2);
                    FicheStringProp.setDefinition(str2);
                    salesDetail.getDiscountRatio(i2).setGuid(str3);
                    salesDetail.getDiscountRatio(i2).setCampaignCode(str4);
                }
            } else if (!salesDetail.getDiscountTotal(i2).getDefinition().equals(str2)) {
                salesDetail.getDiscountTotal(i2);
                FicheStringProp.setDefinition(str2);
                salesDetail.getDiscountTotal(i2).setGuid(str3);
                salesDetail.getDiscountTotal(i2).setCampaignCode(str4);
            }
        } else if (!salesDetail.getDiscountCard(i2).getCode().equals(str)) {
            List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Discount.class, "CODE=? AND TYPE=?", new String[]{str, "0"});
            if (table != null && table.size() > 0) {
                salesDetail.getDiscountCard(i2).setLogicalRef(((Discount) table.get(0)).getLogicalRef());
                salesDetail.getDiscountCard(i2);
                FicheStringProp.setDefinition(((Discount) table.get(0)).getIndirim());
            }
            salesDetail.getDiscountCard(i2).setCode(str);
            salesDetail.getDiscountCard(i2).setGuid(str3);
            salesDetail.getDiscountCard(i2).setCampaignCode(str4);
        }
    }

    private void setDiscount(Sales sales, String str, String str2, boolean z, boolean z2, int i2, String str3, String str4) {
        if (z) {
            if (!z2) {
                if (!sales.getDiscountRatio(i2).getDefinition().equals(str2)) {
                    sales.getDiscountRatio(i2);
                    FicheStringProp.setDefinition(str2);
                    sales.getDiscountRatio(i2).setGuid(str3);
                    sales.getDiscountRatio(i2).setCampaignCode(str4);
                }
            } else if (!sales.getDiscountTotal(i2).getDefinition().equals(str2)) {
                sales.getDiscountTotal(i2);
                FicheStringProp.setDefinition(str2);
                sales.getDiscountTotal(i2).setGuid(str3);
                sales.getDiscountTotal(i2).setCampaignCode(str4);
            }
        } else if (!sales.getDiscountCard(i2).getCode().equals(str)) {
            List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Discount.class, "CODE=? AND TYPE=?", new String[]{str, "1"});
            if (table != null && table.size() > 0) {
                sales.getDiscountCard(i2).setLogicalRef(((Discount) table.get(0)).getLogicalRef());
                sales.getDiscountCard(i2);
                FicheStringProp.setDefinition(((Discount) table.get(0)).getIndirim());
            }
            sales.getDiscountCard(i2).setCode(str);
            sales.getDiscountCard(i2).setGuid(str3);
            sales.getDiscountCard(i2).setCampaignCode(str4);
        }
    }

    @Nullable
    private SalesDetail findSalesDetail(Sales sales, String str, int i2, String str2) {
        Iterator<SalesDetail> it = sales.getMSalesDetailList().iterator();
        while (it.hasNext()) {
            SalesDetail next = it.next();
            if (next.guid.equals(str2)) {
                return next;
            }
        }
        return null;
    }

    private boolean findDiscount(Sales sales, String str) {
        return sales.findDiscountByGuid(str);
    }

    private boolean findDiscount(SalesDetail salesDetail, String str) {
        return salesDetail.findDiscountByGuid(str);
    }

    private void setPrice(SalesDetail salesDetail, int i2, String str) {
        Cursor cursor = null;
        try {
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.qry_get_price_value, i2 == 4 ? "SERVICEUNITS" : "ITEMUNITS"), String.valueOf(salesDetail.getItemRef()), StringUtils.convertNullToEmpty(str), StringUtils.convertIntToString(i2));
                if (cursor != null && cursor.moveToFirst()) {
                    double d2 = cursor.getDouble(cursor.getColumnIndex("PRICE"));
                    if (salesDetail.getPrice().getDefinitionDouble() == d2) {
                        salesDetail.getSelectedPrice().reset();
                        salesDetail.setEnteryPrice(0.0d);
                        salesDetail.setUsePrice(0.0d);
                        salesDetail.setPriceWithDigit("");
                        salesDetail.setPriceWithCurCode("");
                        salesDetail.getPrice().reset();
                        salesDetail.setCalculateCurrPrice(0.0d);
                        salesDetail.setPriceSet(true);
                        if (cursor.getInt(cursor.getColumnIndex("UNITCONVERT")) == 1) {
                            salesDetail.setEnteryPrice(CalculateUtils.convertUnitPrice(d2, salesDetail.getConvFact1(), salesDetail.getConvFact2(), cursor.getDouble(cursor.getColumnIndex("CONVFACT1")), cursor.getDouble(cursor.getColumnIndex("CONVFACT2"))));
                        } else {
                            salesDetail.setEnteryPrice(d2);
                        }
                        salesDetail.getSelectedPrice().setLogicalRef(cursor.getInt(cursor.getColumnIndex(ContextUtils.getmContext().getString(R.string.column_id))));
                        salesDetail.curCodeStr = cursor.getString(cursor.getColumnIndex("CURCODE"));
                        salesDetail.setPriceWithDigit(UnitPriceFormatter.getInstance(ErpCreator.getInstance().getmBaseErp().getCentOfUnitPriceDigit()).getFormattedPrice(salesDetail.getEnteryPrice()));
                        salesDetail.setPriceWithCurCode(String.format("%s / %s", salesDetail.getPriceWithDigit(), salesDetail.getCurCodeStr()));
                        FicheStringProp.setDefinition(salesDetail.getPriceWithCurCode());
                        double d3 = cursor.getDouble(cursor.getColumnIndex("RATE"));
                        if (d3 == 0.0d) {
                            d3 = 1.0d;
                        }
                        salesDetail.setPrRate(d3);
                        salesDetail.prCurrType = cursor.getInt(cursor.getColumnIndex(ContextUtils.getmContext().getString(R.string.column_curr_nr)));
                        salesDetail.getIncludeVat().setSelect(StringUtils.convertIntToBoolean(cursor.getInt(cursor.getColumnIndex("INCVAT"))));
                        Log.d(MobileSales.TAG, "setResultXml: " + salesDetail.getIncludeVat());
                    } else if (!cursor.isClosed()) {
                        cursor.close();
                        return;
                    } else {
                        return;
                    }
                }
                if (cursor == null || cursor.isClosed()) {
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursor == null || cursor.isClosed()) {
                    return;
                }
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    private void setOrderItemAvailableAmount(SalesDetail salesDetail, List<OrderAvailableAmount> list) {
        for (OrderAvailableAmount orderAvailableAmount : list) {
            if (orderAvailableAmount.getOrderLineLogicalRef() == salesDetail.getOrderDetailReference()) {
                salesDetail.setOrderAvailableAmount(orderAvailableAmount.getAvailableAmount());
                salesDetail.setOrderAmount(orderAvailableAmount.getOrderAmount());
                salesDetail.setAmount(new FicheStringProp(StringUtils.convertDoubleToString(Double.valueOf(orderAvailableAmount.getAvailableAmount()))));
                salesDetail.setDistributionRef(orderAvailableAmount.getDitributionRef());
                salesDetail.setDistributionLineRef(orderAvailableAmount.getDitributionLineRef());
            }
        }
    }

    public TigerServiceResult getCanceledFiche(String str) {
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayOutputStream byteArrayOutputStream = null;
        DocumentBuilderFactory newInstance;
        String str2 = "";
        byteArrayInputStream = new ByteArrayInputStream(str.getBytes(StandardCharsets.ISO_8859_1));
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            newInstance = DocumentBuilderFactory.newInstance();
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
        try {
            newInstance.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
            newInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            newInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
            newInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            newInstance.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            newInstance.setXIncludeAware(false);
            newInstance.setExpandEntityReferences(false);
            Document parse = null;
            try {
                parse = newInstance.newDocumentBuilder().parse(byteArrayInputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            }
            ((Element) parse.getElementsByTagName("INVOICE").item(0)).setAttribute("DBOP", "UPD");
            insertCanceledElement(parse, this._INVOICE);
            insertCanceledElement(parse, this._DISPATCH);
            insertCanceledElement(parse, this._TRANSACTION);
            insertCanceledElement(parse, this._PAYMENT);
            TransformerFactory newInstance2 = TransformerFactory.newInstance();
            try {
                newInstance2.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
                newInstance2.setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", str2);
                newInstance2.setAttribute("http://javax.xml.XMLConstants/property/accessExternalStylesheet", str2);
                try {
                    newInstance2.newTransformer().transform(new DOMSource(parse), new StreamResult(byteArrayOutputStream));
                } catch (TransformerException e) {
                    throw new RuntimeException(e);
                }
                str2 = byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1);
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Log.d(TAG, "getCanceledFiche: " + str2);
                return (TigerServiceResult) TigerServiceResult.newBuilder().withDataType(DataObjectType.ADDINVOICE).withSendData(str2).build();
            } catch (TransformerConfigurationException e3) {
                throw new RuntimeException("Transformer Security Configuration Failed", e3);
            }
        } catch (ParserConfigurationException e4) {
            throw new RuntimeException("Parser Security Configuration Failed", e4);
        }
    }

    private void insertCanceledElement(Document document, String str) {
        NodeList elementsByTagName = document.getElementsByTagName(str);
        Element createElement = document.createElement("CANCELLED");
        createElement.setTextContent("1");
        if (elementsByTagName.getLength() > 0) {
            for (int i2 = 0; i2 < elementsByTagName.getLength(); i2++) {
                elementsByTagName.item(i2).appendChild(createElement);
            }
        }
    }

    public TigerServiceResult getReadServiceResult(int i2) {
        return (TigerServiceResult) TigerServiceResult.newBuilder().withDataType(DataObjectType.ADDINVOICE).withDataReference(i2).build();
    }

    public TigerServiceResult getReadServiceResult(int i2, DataObjectType dataObjectType) {
        return (TigerServiceResult) TigerServiceResult.newBuilder().withDataType(dataObjectType).withDataReference(i2).build();
    }

    private void addItemTaxLine(XmlSerializer xmlSerializer, BaseErp baseErp, int i2, String str, String str2, SalesType salesType) throws IOException {
        String str3;
        double d2;
        int i3;
        String str4;
        if (i2 != 0) {
            Cursor availableAddTax = baseErp.getLogoSqlHelper().getAvailableAddTax(i2, str, str2);
            if (availableAddTax == null || !availableAddTax.moveToFirst()) {
                str3 = "";
                d2 = 0.0d;
                i3 = 0;
                str4 = str3;
            } else {
                d2 = availableAddTax.getDouble(availableAddTax.getColumnIndex("RATE"));
                str3 = availableAddTax.getString(availableAddTax.getColumnIndex("TAXCODE"));
                str4 = availableAddTax.getString(availableAddTax.getColumnIndex("GLOBALCODE"));
                i3 = availableAddTax.getInt(availableAddTax.getColumnIndex("EFFECTKDV"));
            }
            addElement(xmlSerializer, this._ADD_TAX_RATE, d2 / 100.0d);
            if (salesType != SalesType.ORDER) {
                addElement(xmlSerializer, this._ADD_TAX_CODE, str3);
                addElement(xmlSerializer, this._ADD_TAX_GLOBAL_CODE, str4);
            }
            addElement(xmlSerializer, this._ADD_TAX_EFFECT_KDV, i3);
        }
    }

    private int getSalesCurrselDetail(int i2, Sales sales) {
        if (!(sales.getMSalesDetailList() == null || sales.getMSalesDetailList().size() == 0)) {
            int logicalRef = sales.getMSalesDetailList().get(0).getCurrType().getLogicalRef();
            Iterator<SalesDetail> it = sales.getMSalesDetailList().iterator();
            while (it.hasNext()) {
                if (logicalRef != it.next().getCurrType().getLogicalRef()) {
                    return CurrselDetailType.PRICING_CURRENCY.getType();
                }
            }
        }
        return i2;
    }

    private void setTotalAndExchangeRateIfEDocumentAndHasCurrencyType(XmlSerializer xmlSerializer, int i2, double d2, int i3, int i4, FicheType ficheType, int i5, boolean z) throws IOException {
        List table;
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        if ((!baseErp.eDocControlTypeFirm() || (!baseErp.firmUseEInvoice() && !baseErp.firmUseEDespatch() && !baseErp.firmUseEArchive())) && (!baseErp.eDocControlTypeBranch() || (table = baseErp.getLogoSqlHelper().getTable(Branch.class, "NR=?", new String[]{String.valueOf(i5)})) == null || table.size() <= 0 || ((Branch) table.get(0)).getUseEinvoice() != 1 || ((Branch) table.get(0)).getUseEdespatch() != 1 || ((Branch) table.get(0)).getUseEarchive() != 1)) {
            addElement(xmlSerializer, ficheType == FicheType.ORDER ? this._CURRSEL_TOTAL : this._CURRSEL_TOTALS, i3);
            addElement(xmlSerializer, this._CURRSEL_DETAILS, i4);
            if (d2 != 0.0d) {
                addElement(xmlSerializer, ((ficheType == FicheType.INVOICE || ficheType == FicheType.RETAILINVOICE) && !z) ? this._TC_XRATE : this._TC_RATE, d2);
            }
        } else if (i2 != 0) {
            addElement(xmlSerializer, ficheType == FicheType.ORDER ? this._CURRSEL_TOTAL : this._CURRSEL_TOTALS, 2);
            addElement(xmlSerializer, this._CURRSEL_DETAILS, 2);
            if (d2 <= 0.0d) {
                addElement(xmlSerializer, ((ficheType == FicheType.INVOICE || ficheType == FicheType.RETAILINVOICE) && !z) ? this._TC_XRATE : this._TC_RATE, 1);
            } else {
                addElement(xmlSerializer, ((ficheType == FicheType.INVOICE || ficheType == FicheType.RETAILINVOICE) && !z) ? this._TC_XRATE : this._TC_RATE, d2);
            }
        } else {
            addElement(xmlSerializer, ficheType == FicheType.ORDER ? this._CURRSEL_TOTAL : this._CURRSEL_TOTALS, i3);
            addElement(xmlSerializer, this._CURRSEL_DETAILS, i4);
            addElement(xmlSerializer, ((ficheType == FicheType.INVOICE || ficheType == FicheType.RETAILINVOICE) && !z) ? this._TC_XRATE : this._TC_RATE, d2);
        }
    }

    private boolean isCompKdvUse(SalesDetail salesDetail) {
        return ((Item) ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Item.class, "LOGICALREF=?", new String[]{String.valueOf(salesDetail.getItemRef())}).get(0)).compKdvUse == 1;
    }
}
