package com.proje.mobilesales.core.data;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.DataObjectType;
import com.proje.mobilesales.core.enums.DiscountDetailLevel;
import com.proje.mobilesales.core.enums.DiscountType;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.LineType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.utils.CalculateUtils;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DOMParser;
import com.proje.mobilesales.core.utils.FicheTypeControlUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.utils.UnitPriceFormatter;
import com.proje.mobilesales.features.dbmodel.Variant;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheDiscountProp;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.product.model.database.Item;
import com.proje.mobilesales.features.product.model.database.ItemStock;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TigerXmlParserForUpdate {
    private final String _ORDER_SLIP = ContextUtils.getStringResource(R.string.tag_order_slip);
    private final String _INVOICE = ContextUtils.getStringResource(R.string.tag_invoice);
    private final String _DISPATCH = ContextUtils.getStringResource(R.string.tag_dispatch);
    private final String _TRANSACTIONS = ContextUtils.getStringResource(R.string.tag_transactions);
    private final String _TRANSACTION = ContextUtils.getStringResource(R.string.tag_transaction);
    private final String _TYPE = ContextUtils.getStringResource(R.string.tag_type);
    private final String _DISCOUNT_RATE = ContextUtils.getStringResource(R.string.tag_discount_rate);
    private final String _TOTAL = ContextUtils.getStringResource(R.string.tag_total);
    private final String _DETAIL_LEVEL = ContextUtils.getStringResource(R.string.tag_detail_level);
    private final String _CALC_TYPE = ContextUtils.getStringResource(R.string.tag_calc_type);
    private final String _CAMPAIGN_INFOS = ContextUtils.getStringResource(R.string.tag_campaign_infos);
    private final String _UNIT_CODE = ContextUtils.getStringResource(R.string.tag_unit_code);
    private final String _UNIT_CONV1 = ContextUtils.getStringResource(R.string.tag_unit_conv1);
    private final String _UNIT_CONV2 = ContextUtils.getStringResource(R.string.tag_unit_conv2);
    private final String _VAT_RATE = ContextUtils.getStringResource(R.string.tag_vat_rate);
    private final String _QUANTITY = ContextUtils.getStringResource(R.string.tag_quantity);
    private final String _MASTER_CODE = ContextUtils.getStringResource(R.string.tag_master_code);
    private final String _GUID = ContextUtils.getStringResource(R.string.tag_guid);
    private final String _VAT_INCLUDED = ContextUtils.getStringResource(R.string.tag_vat_included);
    private final String _CURR_PRICE = ContextUtils.getStringResource(R.string.tag_curr_pice);
    private final String _DISCEXP_CALC = ContextUtils.getStringResource(R.string.tag_discexp_calc);
    private final String _VARIANT_CODE = ContextUtils.getStringResource(R.string.tag_variant_code);
    public Sales parseXml(TigerServiceResult tigerServiceResult) {
        if (tigerServiceResult.getDataType() == DataObjectType.ADDORDER || tigerServiceResult.getDataType() == DataObjectType.ADDINVOICE || tigerServiceResult.getDataType() == DataObjectType.ADDDISPATCH) {
            return getXmlToSales((Sales) tigerServiceResult.getData(), tigerServiceResult.getDataXML());
        }
        return null;
    }
    private Sales getXmlToSales(Sales sales, String str) {
        try {
            parseXml(sales, str);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
        } catch (SAXException e3) {
            e3.printStackTrace();
        }
        return sales;
    }
    private void parseXml(Sales sales, String str) throws ParserConfigurationException, SAXException, IOException {
        Document parseXml = DOMParser.newInstance().parseXml(str);
        parseXml.getDocumentElement().normalize();
        parseHeader(parseXml, sales);
        parseDetail(parseXml, sales);
    }
    private void parseHeader(Document document, Sales sales) {
        String str;
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
                for (int i = 0; i < childNodes.getLength(); i++) {
                    Node item = childNodes.item(i);
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
                        } else if (item.getNodeName().equals("NUMBER")) {
                            sales.setFicheNo(item.getTextContent());
                        }
                    }
                }
            }
        }
        sales.setTotal(sales.getTotalNet() - sales.getTotalVat());
    }
    private void parseDetail(Document document, Sales sales) {
        int i;
        SalesDetail salesDetail;
        boolean z;
        int i2;
        int i3;
        NodeList nodeByTagName = DOMParser.newInstance().getNodeByTagName(document, this._TRANSACTIONS);
        ISqlHelper logoSqlHelper = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
        if (nodeByTagName.getLength() > 0) {
            int i4 = 0;
            NodeList childNodes = nodeByTagName.item(0).getChildNodes();
            SalesDetail salesDetail2 = null;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            while (i5 < childNodes.getLength()) {
                Node item = childNodes.item(i5);
                if (item.getNodeName().equals(this._TRANSACTION)) {
                    NodeList childNodes2 = item.getChildNodes();
                    int intValue = Integer.valueOf(DOMParser.newInstance().getChildNodeByNodeList(childNodes2, this._TYPE).getTextContent()).intValue();
                    if (intValue == 2) {
                        if (TextUtils.isEmpty(DOMParser.newInstance().getChildNodeByNodeList(childNodes2, this._DETAIL_LEVEL).getTextContent())) {
                            i3 = DiscountDetailLevel._LINE.getLevel();
                        } else {
                            i3 = StringUtils.convertStringToInt(DOMParser.newInstance().getChildNodeByNodeList(childNodes2, this._DETAIL_LEVEL).getTextContent());
                        }
                        int convertStringToInt = StringUtils.convertStringToInt(DOMParser.newInstance().getChildNodeByNodeList(childNodes2, this._CALC_TYPE).getTextContent());
                        String textContent = DOMParser.newInstance().getChildNodeByNodeList(childNodes2, this._GUID).getTextContent();
                        if (i3 == DiscountDetailLevel._GENERAL.getLevel()) {
                            if (!findDiscount(sales, textContent)) {
                                addGeneralDiscount(childNodes2, sales, i6, convertStringToInt);
                                sales.calculateSalesTotal();
                            } else {
                                int findDiscountIndexByGuid = sales.findDiscountIndexByGuid(textContent);
                                if (!(findDiscountIndexByGuid == -1 || findDiscountIndexByGuid == i6)) {
                                    sales.swapDiscountIndexes(findDiscountIndexByGuid, i6);
                                }
                                addRatioToGeneralCardDiscount(sales, textContent, childNodes2);
                            }
                            i6++;
                        } else if (i3 == DiscountDetailLevel._LINE.getLevel() && salesDetail2 != null) {
                            if (!findDiscount(salesDetail2, textContent)) {
                                addDetailDiscount(childNodes2, salesDetail2, i7, convertStringToInt);
                                salesDetail2.calculateFiche(sales.isNotUseGattribKdv());
                            } else {
                                int findDiscountIndexByGuid2 = salesDetail2.findDiscountIndexByGuid(textContent);
                                if (!(findDiscountIndexByGuid2 == -1 || findDiscountIndexByGuid2 == i7)) {
                                    sales.swapDiscountIndexes(findDiscountIndexByGuid2, i7);
                                }
                                addRatioToDetailCardDiscount(sales.getmSalesType(), salesDetail2, textContent, childNodes2);
                            }
                            i7++;
                        }
                    } else {
                        LineType lineType = LineType.PRODUCT;
                        if (intValue == lineType.value || intValue == LineType.COMPOSITE_COLI.value || intValue == LineType.PROMOTION.value || intValue == LineType.SERVICE.value) {
                            String textContent2 = DOMParser.newInstance().getChildNodeByNodeList(childNodes2, this._MASTER_CODE).getTextContent();
                            String textContent3 = DOMParser.newInstance().getChildNodeByNodeList(childNodes2, this._GUID).getTextContent();
                            if (intValue == lineType.value || intValue == LineType.COMPOSITE_COLI.value) {
                                i = 0;
                                salesDetail = findSalesDetail(sales, textContent2, intValue, textContent3);
                                if (salesDetail != null) {
                                    setProductInfoToDetail(salesDetail);
                                    setPrice(salesDetail, 2, setSalesDetail(childNodes2, salesDetail, true, logoSqlHelper));
                                    salesDetail.calculateFiche(sales.isNotUseGattribKdv());
                                    salesDetail2 = salesDetail;
                                    i7 = i;
                                    i5++;
                                    i4 = i;
                                } else {
                                    salesDetail2 = salesDetail;
                                    i5++;
                                    i4 = i;
                                }
                            } else {
                                if (intValue == LineType.PROMOTION.value) {
                                    SalesDetail findSalesDetail = findSalesDetail(sales, textContent2, intValue, textContent3);
                                    if (findSalesDetail == null) {
                                        findSalesDetail = new SalesDetail();
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    findSalesDetail.setCode(textContent2);
                                    findSalesDetail.setPromotion(new FicheBooleanProp(true));
                                    setProductInfoToDetail(findSalesDetail);
                                    findSalesDetail.setWareHouse(new FicheRefProp(sales.getWareHouse().getLogicalRef(), -1, sales.getWareHouse().getDefinition()));
                                    findSalesDetail.setSalesType(sales.getMSalesType());
                                    setPromotionStock(findSalesDetail);
                                    setPrice(findSalesDetail, 2, setSalesDetail(childNodes2, findSalesDetail, z, logoSqlHelper));
                                    findSalesDetail.calculateFiche(sales.isNotUseGattribKdv());
                                    if (!z) {
                                        ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
                                        if (sales.getMSalesDetailList().contains(salesDetail2)) {
                                            i2 = sales.getMSalesDetailList().indexOf(salesDetail2) + 1;
                                        } else {
                                            i2 = sales.getMSalesDetailList().size();
                                        }
                                        mSalesDetailList.add(i2, findSalesDetail);
                                    }
                                } else {
                                    LineType lineType2 = LineType.SERVICE;
                                    if (intValue == lineType2.value) {
                                        salesDetail = findSalesDetail(sales, textContent2, intValue, textContent3);
                                        i = 0;
                                        salesDetail.setProduct(false);
                                        salesDetail.setCardType(4);
                                        salesDetail.lineType = lineType2.value;
                                        setPrice(salesDetail, 4, setSalesDetailService(childNodes2, salesDetail, true, logoSqlHelper));
                                        salesDetail.calculateFiche(sales.isNotUseGattribKdv());
                                        salesDetail2 = salesDetail;
                                        i7 = i;
                                        i5++;
                                        i4 = i;
                                    }
                                }
                                i = 0;
                                i5++;
                                i4 = i;
                            }
                        }
                    }
                }
                i = i4;
                i5++;
                i4 = i;
            }
        }
    }
    private void setProductInfoToDetail(SalesDetail salesDetail) {
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Item.class, "CODE=?", new String[]{salesDetail.getCode()});
        if (table != null && table.size() != 0) {
            salesDetail.setProduct(true);
            salesDetail.setCardType(((Item) table.get(0)).cardType);
            salesDetail.lineType = 0;
            if (TextUtils.isEmpty(salesDetail.getName())) {
                salesDetail.setName(((Item) table.get(0)).name);
            }
            if (salesDetail.getItemRef() == 0) {
                salesDetail.setItemRef(((Item) table.get(0)).logicalRef);
            }
            if (salesDetail.getCardType() == 2) {
                salesDetail.lineType = 6;
            } else if (salesDetail.getPromotion().isSelect()) {
                salesDetail.lineType = 1;
            }
        }
    }
    private void addRatioToDetailCardDiscount(SalesType salesType, SalesDetail salesDetail, String str, NodeList nodeList) {
        int findDiscountIndexByGuid;
        if (!TextUtils.isEmpty(DOMParser.newInstance().getChildNodeByNodeList(nodeList, this._MASTER_CODE).getTextContent())) {
            String textContent = DOMParser.newInstance().getChildNodeByNodeList(nodeList, this._DISCOUNT_RATE).getTextContent();
            if (!TextUtils.isEmpty(textContent) && (findDiscountIndexByGuid = salesDetail.findDiscountIndexByGuid(str)) != -1) {
                FicheDiscountProp discountRatio = salesDetail.getSalesFicheDiscountProps().getDiscountRatio(findDiscountIndexByGuid);
                FicheStringProp.setDefinition(textContent);
                discountRatio.setBoundedToCard(true);
            }
        }
    }
    private void addRatioToGeneralCardDiscount(Sales sales, String str, NodeList nodeList) {
        int findDiscountIndexByGuid;
        if (!TextUtils.isEmpty(DOMParser.newInstance().getChildNodeByNodeList(nodeList, this._MASTER_CODE).getTextContent())) {
            String textContent = DOMParser.newInstance().getChildNodeByNodeList(nodeList, this._DISCOUNT_RATE).getTextContent();
            if (!TextUtils.isEmpty(textContent) && (findDiscountIndexByGuid = sales.findDiscountIndexByGuid(str)) != -1) {
                FicheDiscountProp discountRatio = sales.getSalesFicheDiscountProps().getDiscountRatio(findDiscountIndexByGuid);
                FicheStringProp.setDefinition(textContent);
                discountRatio.setBoundedToCard(true);
            }
        }
    }
    private void setPromotionStock(SalesDetail salesDetail) {
        List<ItemStock> table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ItemStock.class, "ITEMREF=? AND WAREHOUSENR=?", new String[]{Integer.toString(salesDetail.getItemRef()), Integer.toString(salesDetail.getWareHouse().getLogicalRef())});
        if (table != null && table.size() > 0) {
            for (ItemStock itemStock : table) {
                if (itemStock.wareHouseNr == salesDetail.getWareHouse().getLogicalRef()) {
                    salesDetail.setActualStock(itemStock.realStock);
                    return;
                }
            }
        }
    }
    private String setSalesDetail(NodeList nodeList, SalesDetail salesDetail, boolean z, ISqlHelper iSqlHelper) {
        List table;
        String str = "";
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            if (item.getNodeName().equals(this._QUANTITY)) {
                salesDetail.setAmount(new FicheStringProp(item.getTextContent()));
            } else if (item.getNodeName().equals(this._VAT_RATE)) {
                salesDetail.setVat(new FicheStringProp(item.getTextContent()));
            } else if (item.getNodeName().equals(this._VAT_INCLUDED)) {
                salesDetail.setIncludeVat(new FicheBooleanProp(item.getTextContent().equals(BuildConfig.NETSIS_DEMO_PASSWORD)));
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
                    str = item.getTextContent();
                } else if (item.getNodeName().equals(this._GUID) && !z) {
                    salesDetail.setGuid(item.getTextContent());
                } else if (item.getNodeName().equals(this._CAMPAIGN_INFOS)) {
                    setCampaignCodesAndLines(item, salesDetail);
                } else if (item.getNodeName().equals(this._DETAIL_LEVEL)) {
                    salesDetail.setGlobalLineType(StringUtils.convertStringToInt(item.getTextContent()));
                } else if (item.getNodeName().equals(this._VARIANT_CODE) && (table = iSqlHelper.getTable(Variant.class, "ITEMREF = ? AND CODE = ?", new String[]{StringUtils.convertIntToString(salesDetail.getItemRef()), item.getTextContent()})) != null && table.size() > 0) {
                    Variant variant = (Variant) table.get(0);
                    salesDetail.setVariant(new FicheDiscountRefProp(variant.getLogicalRef(), -1, variant.getName(), variant.getCode()));
                    salesDetail.setHasVariant(true);
                }
            }
        }
        return str;
    }
    private String setSalesDetailService(NodeList nodeList, SalesDetail salesDetail, boolean z, ISqlHelper iSqlHelper) {
        String str = "";
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            if (item.getNodeName().equals(this._QUANTITY)) {
                salesDetail.setAmount(new FicheStringProp(item.getTextContent()));
            } else if (item.getNodeName().equals(this._VAT_RATE)) {
                salesDetail.setVat(new FicheStringProp(item.getTextContent()));
            } else if (item.getNodeName().equals(this._VAT_INCLUDED)) {
                salesDetail.setIncludeVat(new FicheBooleanProp(item.getTextContent().equals(BuildConfig.NETSIS_DEMO_PASSWORD)));
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
                    str = item.getTextContent();
                } else if (item.getNodeName().equals(this._GUID) && !z) {
                    salesDetail.setGuid(item.getTextContent());
                } else if (item.getNodeName().equals(this._CAMPAIGN_INFOS)) {
                    setCampaignCodesAndLines(item, salesDetail);
                }
            }
        }
        return str;
    }
    private void addGeneralDiscount(NodeList nodeList, Sales sales, int i, int i2) {
        String str;
        String str2 = "";
        String str3 = str2;
        String str4 = str3;
        String str5 = str4;
        boolean z = false;
        String str6 = str5;
        for (int i3 = 0; i3 < nodeList.getLength(); i3++) {
            Node item = nodeList.item(i3);
            if (item.getNodeName().equals(this._MASTER_CODE)) {
                str2 = item.getTextContent();
            } else if (item.getNodeName().equals(this._DISCOUNT_RATE) && i2 == DiscountType.DISCRATIO.value) {
                str3 = item.getTextContent();
            } else if (item.getNodeName().equals(this._TOTAL) && i2 == DiscountType.DISCTOTAL.value) {
                str3 = item.getTextContent();
                z = true;
            } else if (item.getNodeName().equals(this._GUID)) {
                str4 = item.getTextContent();
            } else if (item.getNodeName().equals(this._CAMPAIGN_INFOS)) {
                str6 = getCampaignCode(item);
                str5 = getCampaignLineNo(item);
            }
        }
        boolean isEmpty = TextUtils.isEmpty(str2);
        String upperCase = TextUtils.isEmpty(str6) ? !TextUtils.isEmpty(str4) ? str4 : UUID.randomUUID().toString().toUpperCase() : str6;
        if (isEmpty && TextUtils.isEmpty(str3)) {
            String textContent = DOMParser.newInstance().getChildNodeByNodeList(nodeList, this._DISCEXP_CALC).getTextContent();
            if (!TextUtils.isEmpty(textContent) && StringUtils.convertStringToInt(textContent) == DiscountType.DISCTOTAL.value) {
                String textContent2 = DOMParser.newInstance().getChildNodeByNodeList(nodeList, this._TOTAL).getTextContent();
                str = textContent2;
                if (!TextUtils.isEmpty(textContent2)) {
                    z = true;
                }
                if (!isEmpty || z) {
                    setDiscount(sales, str2, str, isEmpty, z, i, str4, upperCase, str5);
                }
                return;
            }
        }
        str = str3;
        if (!isEmpty) {
            return;
        }
        setDiscount(sales, str2, str, isEmpty, z, i, str4, upperCase, str5);
    }
    private String getCampaignCode(Node node) {
        NodeList childNodes = node.getChildNodes();
        String str = "";
        if (childNodes.getLength() > 0) {
            for (int i = 0; i < childNodes.getLength(); i++) {
                NodeList childNodes2 = childNodes.item(i).getChildNodes();
                if (childNodes2.getLength() > 0) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= childNodes2.getLength()) {
                            break;
                        } else if (childNodes2.item(i2).getNodeName().equals("CAMPCODE1")) {
                            str = childNodes2.item(i2).getTextContent();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
        }
        return str;
    }
    private String getCampaignLineNo(Node node) {
        NodeList childNodes = node.getChildNodes();
        String str = "";
        for (int i = 0; i < childNodes.getLength(); i++) {
            NodeList childNodes2 = childNodes.item(i).getChildNodes();
            int i2 = 0;
            while (true) {
                if (i2 >= childNodes2.getLength()) {
                    break;
                } else if (childNodes2.item(i2).getNodeName().equals("CAMP_LN_NO")) {
                    str = childNodes2.item(i2).getTextContent();
                    break;
                } else {
                    i2++;
                }
            }
        }
        return str;
    }
    private void setCampaignCodesAndLines(Node node, SalesDetail salesDetail) {
        NodeList childNodes = node.getChildNodes();
        if (childNodes.getLength() > 0) {
            for (int i = 0; i < childNodes.getLength(); i++) {
                NodeList childNodes2 = childNodes.item(i).getChildNodes();
                if (childNodes2.getLength() > 0) {
                    for (int i2 = 0; i2 < childNodes2.getLength(); i2++) {
                        if (childNodes2.item(i2).getNodeName().equals("CAMPCODE1")) {
                            salesDetail.setCampaignCode(childNodes2.item(i2).getTextContent());
                        }
                        if (childNodes2.item(i2).getNodeName().equals("CAMPCODE2")) {
                            salesDetail.setCampaignCode2(childNodes2.item(i2).getTextContent());
                        }
                        if (childNodes2.item(i2).getNodeName().equals("CAMPCODE3")) {
                            salesDetail.setCampaignCode3(childNodes2.item(i2).getTextContent());
                        }
                        if (childNodes2.item(i2).getNodeName().equals("CAMPCODE4")) {
                            salesDetail.setCampaignCode4(childNodes2.item(i2).getTextContent());
                        }
                        if (childNodes2.item(i2).getNodeName().equals("CAMPCODE5")) {
                            salesDetail.setCampaignCode5(childNodes2.item(i2).getTextContent());
                        }
                        if (childNodes2.item(i2).getNodeName().equals("CAMP_LN_NO")) {
                            salesDetail.setCampaignLineNo(childNodes2.item(i2).getTextContent());
                        }
                    }
                }
            }
        }
    }
    private void addDetailDiscount(NodeList nodeList, SalesDetail salesDetail, int i, int i2) {
        String str;
        String str2 = "";
        String str3 = str2;
        String str4 = str3;
        String str5 = str4;
        boolean z = false;
        String str6 = str5;
        for (int i3 = 0; i3 < nodeList.getLength(); i3++) {
            Node item = nodeList.item(i3);
            if (item.getNodeName().equals(this._MASTER_CODE)) {
                str2 = item.getTextContent();
            } else if (item.getNodeName().equals(this._DISCOUNT_RATE)) {
                if (i2 == DiscountType.DISCRATIO.value) {
                    str3 = item.getTextContent();
                }
                salesDetail.setCampaignDiscountRatio(item.getTextContent());
            } else if (item.getNodeName().equals(this._TOTAL) && i2 == DiscountType.DISCTOTAL.value) {
                str3 = item.getTextContent();
                z = true;
            } else if (item.getNodeName().equals(this._GUID)) {
                str4 = item.getTextContent();
            } else if (item.getNodeName().equals(this._CAMPAIGN_INFOS)) {
                str6 = getCampaignCode(item);
                str5 = getCampaignLineNo(item);
            }
        }
        boolean isEmpty = TextUtils.isEmpty(str2);
        String upperCase = TextUtils.isEmpty(str6) ? !TextUtils.isEmpty(str4) ? str4 : UUID.randomUUID().toString().toUpperCase() : str6;
        if (isEmpty && TextUtils.isEmpty(str3)) {
            String textContent = DOMParser.newInstance().getChildNodeByNodeList(nodeList, this._DISCEXP_CALC).getTextContent();
            if (!TextUtils.isEmpty(textContent) && StringUtils.convertStringToInt(textContent) == DiscountType.DISCTOTAL.value) {
                String textContent2 = DOMParser.newInstance().getChildNodeByNodeList(nodeList, this._TOTAL).getTextContent();
                str = textContent2;
                if (!TextUtils.isEmpty(textContent2)) {
                    z = true;
                }
                if (!isEmpty || z) {
                    setDiscount(salesDetail, str2, str, isEmpty, z, i, str4, upperCase, str5);
                }
                return;
            }
        }
        str = str3;
        if (!isEmpty) {
        }
        setDiscount(salesDetail, str2, str, isEmpty, z, i, str4, upperCase, str5);
    }
    private void setDiscount(SalesDetail salesDetail, String str, String str2, boolean z, boolean z2, int i, String str3, String str4, String str5) {
        if (z) {
            if (!z2) {
                if (!salesDetail.getDiscountRatio(i).getDefinition().equals(str2)) {
                    salesDetail.getDiscountRatio(i);
                    FicheStringProp.setDefinition(str2);
                    salesDetail.getDiscountRatio(i).setGuid(str3);
                    salesDetail.getDiscountRatio(i).setCampaignCode(str4);
                    salesDetail.getDiscountRatio(i).setCampaignLineNo(str5);
                }
            } else if (!salesDetail.getDiscountTotal(i).getDefinition().equals(str2)) {
                salesDetail.getDiscountTotal(i);
                FicheStringProp.setDefinition(str2);
                salesDetail.getDiscountTotal(i).setGuid(str3);
                salesDetail.getDiscountTotal(i).setCampaignCode(str4);
                salesDetail.getDiscountTotal(i).setCampaignLineNo(str5);
            }
        } else if (!salesDetail.getDiscountCard(i).getCode().equals(str)) {
            salesDetail.getDiscountCard(i).setCode(str);
            salesDetail.getDiscountCard(i).setGuid(str3);
            salesDetail.getDiscountCard(i).setCampaignCode(str4);
            salesDetail.getDiscountCard(i).setCampaignLineNo(str5);
        }
    }
    private void setDiscount(Sales sales, String str, String str2, boolean z, boolean z2, int i, String str3, String str4, String str5) {
        if (z) {
            if (!z2) {
                if (!sales.getDiscountRatio(i).getDefinition().equals(str2)) {
                    sales.getDiscountRatio(i);
                    FicheStringProp.setDefinition(str2);
                    sales.getDiscountRatio(i).setGuid(str3);
                    sales.getDiscountRatio(i).setCampaignCode(str4);
                    sales.getDiscountRatio(i).setCampaignLineNo(str5);
                }
            } else if (!sales.getDiscountTotal(i).getDefinition().equals(str2)) {
                sales.getDiscountTotal(i);
                FicheStringProp.setDefinition(str2);
                sales.getDiscountTotal(i).setGuid(str3);
                sales.getDiscountTotal(i).setCampaignCode(str4);
                sales.getDiscountTotal(i).setCampaignLineNo(str5);
            }
        } else if (!sales.getDiscountCard(i).getCode().equals(str)) {
            sales.getDiscountCard(i).setCode(str);
            sales.getDiscountCard(i).setGuid(str3);
            sales.getDiscountCard(i).setCampaignCode(str4);
            sales.getDiscountCard(i).setCampaignLineNo(str5);
        }
    }
    private SalesDetail findSalesDetail(Sales sales, String str, int i, String str2) {
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
    private FicheDiscountProp getDiscount(Sales sales, String str) {
        return sales.getDiscountByGuid(str);
    }
    private boolean findDiscount(SalesDetail salesDetail, String str) {
        return salesDetail.findDiscountByGuid(str);
    }
    private FicheDiscountProp getDiscount(SalesDetail salesDetail, String str) {
        return salesDetail.getDiscountByGuid(str);
    }
    private int getDiscountIndexByGuid(SalesDetail salesDetail, String str) {
        return salesDetail.findDiscountIndexByGuid(str);
    }
    @SuppressLint("Range")
    private void setPrice(SalesDetail salesDetail, int r20, String str) {
        double d2;
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.qry_get_price_value, r20 == 4 ? "SERVICEUNITS" : "ITEMUNITS"), String.valueOf(salesDetail.getItemRef()), StringUtils.convertNullToEmpty(str), StringUtils.convertIntToString(r20));
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    boolean z = cursorQuery.getInt(cursorQuery.getColumnIndex("UNITCONVERT")) == 1;
                    double d3 = cursorQuery.getDouble(cursorQuery.getColumnIndex("PRICE"));
                    if (d3 == salesDetail.getUsePrice() || salesDetail.isCampaignLine()) {
                        salesDetail.getSelectedPrice().reset();
                        salesDetail.setEnteryPrice(0.0d);
                        salesDetail.setUsePrice(0.0d);
                        salesDetail.setPriceWithDigit("");
                        salesDetail.setPriceWithCurCode("");
                        salesDetail.getPrice().reset();
                        salesDetail.setCalculateCurrPrice(0.0d);
                        salesDetail.setPriceSet(true);
                        if (z) {
                            d2 = 0.0d;
                            salesDetail.setEnteryPrice(CalculateUtils.convertUnitPrice(d3, salesDetail.getConvFact1(), salesDetail.getConvFact2(), cursorQuery.getDouble(cursorQuery.getColumnIndex("CONVFACT1")), cursorQuery.getDouble(cursorQuery.getColumnIndex("CONVFACT2"))));
                        } else {
                            d2 = 0.0d;
                            salesDetail.setEnteryPrice(d3);
                        }
                        salesDetail.getSelectedPrice().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex(ContextUtils.getmContext().getString(R.string.column_id))));
                        salesDetail.curCodeStr = cursorQuery.getString(cursorQuery.getColumnIndex("CURCODE"));
                        salesDetail.setPriceWithDigit(UnitPriceFormatter.getInstance(ErpCreator.getInstance().getmBaseErp().getCentOfUnitPriceDigit()).getFormattedPrice(salesDetail.getEnteryPrice()));
                        salesDetail.setPriceWithCurCode(String.format("%s / %s", salesDetail.getPriceWithDigit(), salesDetail.getCurCodeStr()));
                        FicheStringProp.setDefinition(salesDetail.getPriceWithCurCode());
                        double d4 = cursorQuery.getDouble(cursorQuery.getColumnIndex("RATE"));
                        if (d4 == d2) {
                            d4 = 1.0d;
                        }
                        salesDetail.setPrRate(d4);
                        salesDetail.prCurrType = cursorQuery.getInt(cursorQuery.getColumnIndex(ContextUtils.getmContext().getString(R.string.column_curr_nr)));
                        salesDetail.getIncludeVat().setSelect(StringUtils.convertIntToBoolean(cursorQuery.getInt(cursorQuery.getColumnIndex("INCVAT"))));
                        Log.d(MobileSales.TAG, "setResultXml: " + salesDetail.getIncludeVat());
                    } else {
                        double dConvertStringToDouble = StringUtils.convertStringToDouble(salesDetail.getPrice().toString());
                        if (dConvertStringToDouble <= 0.0d) {
                            dConvertStringToDouble = salesDetail.getEnteryPrice();
                        }
                        salesDetail.setCalculateCurrPrice(dConvertStringToDouble);
                    }
                }
                if (cursorQuery == null || cursorQuery.isClosed()) {
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (true) {
                    return;
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (false) {
                cursorQuery.close();
            }
            throw th;
        }
    }
}