package com.proje.mobilesales.core.data;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.design.Netsis;
import com.proje.mobilesales.core.enums.DataObjectType;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.LineType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.TrackType;
import com.proje.mobilesales.core.enums.TransferOperationName;
import com.proje.mobilesales.core.netsis.NetsisSelectResult;
import com.proje.mobilesales.core.netsis.NetsisServiceResult;
import com.proje.mobilesales.core.netsis.sendmodel.chequedeed.NetsisChequeAndDeed;
import com.proje.mobilesales.core.netsis.sendmodel.chequedeed.NetsisChequeAndDeedMain;
import com.proje.mobilesales.core.netsis.sendmodel.chequedeed.NetsisChequeAndDeedSaveType;
import com.proje.mobilesales.core.netsis.sendmodel.chequedeed.NetsisChequeAndDeedType;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.netsis.sendmodel.recipt.MixedReceipt;
import com.proje.mobilesales.core.netsis.sendmodel.recipt.MixedReceiptsMain;
import com.proje.mobilesales.core.netsis.sendmodel.safedeposit.SafeDeposit;
import com.proje.mobilesales.core.netsis.sendmodel.sales.EWaybillInfo;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlipHeader;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlipLine;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlipSerialLine;
import com.proje.mobilesales.core.netsis.sendmodel.sales.NetsisInvoiceType;
import com.proje.mobilesales.core.netsis.sendmodel.sales.NetsisSlipType;
import com.proje.mobilesales.core.netsis.sendmodel.sales.NetsisWarehouseTransType;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.CabinTrans;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditX;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCreditDetail;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeed;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.ChequedeedDetail;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;
import com.proje.mobilesales.features.model.FicheDiscountProp;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.penetration.model.Penetration;
import com.proje.mobilesales.features.penetration.model.PenetrationLine;
import com.proje.mobilesales.features.product.model.database.ItemPrice;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.SalesFicheDiscountProps;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import com.proje.mobilesales.features.settings.model.enums.MatterArea;
import com.proje.mobilesales.features.todo.model.database.TodoInfoDb;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class NetsisSendDataCreator implements SendDataCreator<NetsisServiceResult, NetsisSelectResult>, SendBuilder<NetsisServiceResult, Object, Object> {
    private static final String TAG = "com.proje.mobilesales.core.data.NetsisSendDataCreator";
    private double convertRatioToTotal(double d2, double d3) {
        return (d2 * 100.0d) / d3;
    }
    public NetsisServiceResult buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, int i2, String str, int i3, String str2, String str3, Object obj, int i4) {
        return null;
    }
    public NetsisServiceResult buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, int i2, String str, String str2, String str3, Object obj, int i3) {
        return null;
    }
    public NetsisServiceResult getCustomer(CustomerNew customerNew) {
        return null;
    }
    public NetsisServiceResult getDemand(Sales sales, int i2) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        return buildServiceResult(baseErp.getLogoSqlHelper(), DataObjectType.ADDREQEST, i2, sales.getGDate(), sales.getAndFicheNo(), getSlip(baseErp, sales, NetsisSlipType.ftAlTalep, NetsisInvoiceType.ft_Acik), sales, sales.getLogicalRef());
    }
    public NetsisServiceResult getOrder(Sales sales) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        return buildServiceResult(baseErp.getLogoSqlHelper(), DataObjectType.ADDORDER, sales.getGDate(), sales.getClCode(), sales.getAndFicheNo(), getSlip(baseErp, sales, NetsisSlipType.ftSSip, NetsisInvoiceType.ft_Acik), sales, sales.getLogicalRef());
    }
    public NetsisServiceResult getInvoice(@UnknownNullability PrintSlipModel sales) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        return buildServiceResult(baseErp.getLogoSqlHelper(), DataObjectType.ADDINVOICE, sales.getGDate(), sales.getClCode(), sales.getAndFicheNo(), (Object) getSlip(baseErp, sales, NetsisSlipType.ftSFat, NetsisInvoiceType.ft_Acik), (Object) sales, sales.getLogicalRef());
    }
    public NetsisServiceResult getReturnInvoice(Sales sales) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        return buildServiceResult(baseErp.getLogoSqlHelper(), DataObjectType.ADDINVOICE, sales.getGDate(), sales.getClCode(), sales.getAndFicheNo(), getSlip(baseErp, sales, NetsisSlipType.ftAFat, NetsisInvoiceType.ft_Iade), sales, sales.getLogicalRef());
    }
    public NetsisServiceResult getDispatch(Sales sales) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        return buildServiceResult(baseErp.getLogoSqlHelper(), DataObjectType.ADDDISPATCH, sales.getGDate(), sales.getClCode(), sales.getAndFicheNo(), getSlip(baseErp, sales, NetsisSlipType.ftSIrs, NetsisInvoiceType.ft_Acik), sales, sales.getLogicalRef());
    }
    public NetsisServiceResult getReturnDispatch(Sales sales) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        return buildServiceResult(baseErp.getLogoSqlHelper(), DataObjectType.ADDDISPATCH, sales.getGDate(), sales.getClCode(), sales.getAndFicheNo(), getSlip(baseErp, sales, NetsisSlipType.ftAIrs, NetsisInvoiceType.ft_Iade), sales, sales.getLogicalRef());
    }
    public List<NetsisServiceResult> getOneToOne(Sales sales) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getReturnInvoice(sales));
        sales.setAndFicheNo(UUID.randomUUID().toString().toUpperCase());
        arrayList.add(getInvoice(sales));
        return arrayList;
    }
    private ItemSlip getSlip(BaseErp baseErp, Sales sales, NetsisSlipType netsisSlipType, NetsisInvoiceType netsisInvoiceType) {
        return ItemSlip.newBuilder().withSlipHeader(getSlipHeader(baseErp, sales, netsisSlipType, netsisInvoiceType)).withLines(getSlipLines(baseErp, sales, netsisSlipType, netsisInvoiceType)).withSlipType(netsisSlipType).withRiskControl(true).withLastNumberWrite(true).withOtoDiscountGet(true).withConnectControl(true).withTransactionSupport(true).withDiscountCalculate(true).withReceiptLineCount(0).withSerial(getSerialFromSales(sales)).withEWaybillInfo(getEWaybillInfo(sales)).withRecordNumberUpdate(true).withSerialCalculate(sales.getMSalesDetailList().stream().anyMatch(new Predicate() { // from class: com.proje.mobilesales.core.data.NetsisSendDataCreatorExternalSyntheticLambda0
            public boolean test(Object obj) {
                boolean lambdagetSlip0;
                lambdagetSlip0 = NetsisSendDataCreator.lambdagetSlip0((SalesDetail) obj);
                return lambdagetSlip0;
            }
        })).withOtoAverageDay(baseErp.isDueDateByLineEnabled()).build();
    }
    private String getSerialFromSales(Sales sales) {
        if (sales.getmSalesType() == SalesType.DISPATCH) {
            if (sales.getEDespatch().isSelect()) {
                return sales.getEDocSerial().getDefinition();
            }
            return null;
        }
        if (TextUtils.isEmpty(sales.getEDocSerial().getDefinition())) {
            return null;
        }
        return sales.getEDocSerial().getDefinition();
    }
    private static boolean lambdagetSlip0(SalesDetail salesDetail) {
        return salesDetail.getTrackType() != TrackType.NON_TRACK.getType() && salesDetail.getSalesSerialLots() != null && !salesDetail.getSalesSerialLots().isEmpty();
    }
    private EWaybillInfo getEWaybillInfo(Sales sales) {
        if (sales.getmSalesType() != SalesType.DISPATCH || !sales.getEDespatch().isSelect()) {
            return null;
        }
        EWaybillInfo eWaybillInfo = new EWaybillInfo();
        EDispatchAdditionalInfo eDispatchAdditionalInfo = sales.getEDispatchAdditionalInfo();
        eWaybillInfo.setPlate(eDispatchAdditionalInfo.firstDriverPlate);
        eWaybillInfo.setCarrierTaxNr(eDispatchAdditionalInfo.carrierTaxNr);
        eWaybillInfo.setCarrierName(eDispatchAdditionalInfo.carrierName);
        eWaybillInfo.setCarrierCounty(eDispatchAdditionalInfo.carrierCounty);
        eWaybillInfo.setCarrierCity(eDispatchAdditionalInfo.carrierCity);
        eWaybillInfo.setCarrierCountry(eDispatchAdditionalInfo.carrierCountry);
        eWaybillInfo.setCarrierPostCode(eDispatchAdditionalInfo.carrierPostCode);
        eWaybillInfo.setFirstDriverName(eDispatchAdditionalInfo.firstDriverName);
        eWaybillInfo.setFirstDriverLastName(eDispatchAdditionalInfo.firstDriverLastName);
        eWaybillInfo.setFirstDriverDescription(eDispatchAdditionalInfo.firstDriverDescription);
        eWaybillInfo.setFirstDriverIdentityNr(eDispatchAdditionalInfo.firstDriverIdentityNr);
        eWaybillInfo.setFirstTrailerPlate(eDispatchAdditionalInfo.firstTrailerPlate);
        eWaybillInfo.setShipmentDate(DateAndTimeUtils.convertDateSqlDateFull(DateAndTimeUtils.getNowDateTime()));
        eDispatchAdditionalInfo.shipmentDate = eWaybillInfo.getShipmentDate();
        return eWaybillInfo;
    }
    private ItemSlipHeader getSlipHeader(BaseErp baseErp, Sales sales, NetsisSlipType netsisSlipType, NetsisInvoiceType netsisInvoiceType) {
        String convertSqlDate;
        ItemSlipHeader itemSlipHeader = new ItemSlipHeader();
        itemSlipHeader.setSlipType(netsisSlipType);
        itemSlipHeader.seteInvoiceCustomer(baseErp.getLogoSqlHelper().getClEInvoiceUser(sales.getClCode()) == 1);
        if (sales.getmSalesType() == SalesType.DISPATCH && sales.getEDespatch().isSelect()) {
            itemSlipHeader.setSlipNo(null);
            itemSlipHeader.seteDespatch(true);
        } else {
            MatterSettings matterSettings = baseErp.getMatterSettings(ContextUtils.getmContext(), sales.getFicheType(sales.getmSalesType()));
            if (matterSettings.isUseMatterNo() && matterSettings.getMatterArea() == MatterArea.FICHE_NO) {
                itemSlipHeader.setSlipNo(sales.getFicheNo());
            }
        }
        itemSlipHeader.setNetsisInvoiceType(netsisInvoiceType);
        String convertDateSqlDateFull = DateAndTimeUtils.convertDateSqlDateFull(sales.getGDate());
        itemSlipHeader.setDate(convertDateSqlDateFull);
        itemSlipHeader.setBranchCode(((Preferences.NetsisUserSettings) baseErp.getUserSettings()).getBranchCode());
        itemSlipHeader.setCustomerCode(sales.getClCode());
        if (TextUtils.isEmpty(sales.getDeliveryDate().getDefinition())) {
            convertSqlDate = DateAndTimeUtils.getDateSqlYearAndHour();
        } else {
            convertSqlDate = DateAndTimeUtils.convertSqlDate(sales.getDeliveryDate().getDefinition());
        }
        itemSlipHeader.setOrderTest(convertSqlDate);
        itemSlipHeader.setExplanation(sales.getExplanation1().toString());
        itemSlipHeader.setAppendixExplanation1(sales.getExplanation2().toString());
        itemSlipHeader.setAppendixExplanation2(sales.getExplanation3().toString());
        itemSlipHeader.setAppendixExplanation3(sales.getExplanation4().toString());
        itemSlipHeader.setAppendixExplanation4(sales.getExplanation5().toString());
        itemSlipHeader.setAppendixExplanation5(sales.getExplanation6().toString());
        itemSlipHeader.setAppendixExplanation6(sales.getExplanation7().toString());
        itemSlipHeader.setAppendixExplanation7(sales.getExplanation8().toString());
        itemSlipHeader.setAppendixExplanation8(sales.getExplanation9().toString());
        itemSlipHeader.setAppendixExplanation9(sales.getExplanation10().toString());
        itemSlipHeader.setAppendixExplanation15(sales.getAndFicheNo());
        itemSlipHeader.setPlaCode(baseErp.getUser().getCode());
        itemSlipHeader.setProjectCode(sales.getProjectCode().getCode());
        itemSlipHeader.setPaymentCode(sales.getPayPlan().getCode());
        itemSlipHeader.setCustomerCode2(sales.getShipAccount().getCode());
        itemSlipHeader.setGeneralDiscountRatio(getLineDiscountRatio(sales.getDiscountRatio(0), sales.getDiscountTotal(0), sales.getGrossTotal()));
        itemSlipHeader.setGeneralDiscountRatio2(getLineDiscountRatio(sales.getDiscountRatio(1), sales.getDiscountTotal(1), sales.getGrossTotal()));
        itemSlipHeader.setGeneralDiscountRatio3(getLineDiscountRatio(sales.getDiscountRatio(2), sales.getDiscountTotal(2), sales.getGrossTotal()));
        itemSlipHeader.setGeneralDiscount(getDiscount(sales.getDiscountTotal(0)));
        itemSlipHeader.setGeneralDiscount2(getDiscount(sales.getDiscountTotal(1)));
        itemSlipHeader.setGeneralDiscount3(getDiscount(sales.getDiscountTotal(2)));
        itemSlipHeader.setDiscountCode(getLineDiscountCardCode(sales.getDiscountCard(0)));
        itemSlipHeader.setDiscountDate(convertDateSqlDateFull);
        itemSlipHeader.setPriceDate(convertDateSqlDateFull);
        itemSlipHeader.setActualDate(convertDateSqlDateFull);
        itemSlipHeader.setInclueVat(sales.getIncludeVat().isSelect());
        itemSlipHeader.setbForm("H");
        if (sales.getFirstSpeCode() != null && !TextUtils.isEmpty(sales.getFirstSpeCode().getCode())) {
            itemSlipHeader.setCode1(sales.getFirstSpeCode().getCode());
        }
        if (sales.getSecondSpeCode() != null && !TextUtils.isEmpty(sales.getSecondSpeCode().getCode())) {
            itemSlipHeader.setCode2(sales.getSecondSpeCode().getCode());
        }
        if (baseErp.getSendFicheToMainCustomer()) {
            String mainClCode = baseErp.getMainClCode(sales.getClCode());
            if (!TextUtils.isEmpty(mainClCode)) {
                itemSlipHeader.setCustomerCode2(itemSlipHeader.getCustomerCode());
                itemSlipHeader.setCustomerCode(mainClCode);
            }
        } else {
            itemSlipHeader.setCustomerCode2(sales.getShipAddress().getCode());
        }
        if (netsisSlipType == NetsisSlipType.ftLokalDepo) {
            itemSlipHeader.setaMBHARTUR(NetsisWarehouseTransType.htDepolar);
        }
        if (sales.getDueDate() != null && !TextUtils.isEmpty(sales.getFicheCreateDate())) {
            try {
                long convert = TimeUnit.DAYS.convert(Math.abs(DateAndTimeUtils.convertStringToDateddMMYYYY(sales.getDueDate().toString()).getTime() - DateAndTimeUtils.convertStringToDateddMMYYYY(sales.getFicheCreateDate()).getTime()), TimeUnit.MILLISECONDS);
                itemSlipHeader.setPaymentDate(DateAndTimeUtils.convertDateSqlDateWithoutTime(sales.getDueDate().toString()));
                itemSlipHeader.setPaymentDay((int) convert);
            } catch (Exception e2) {
                Log.d(TAG, "Due days calculate", e2);
            }
        }
        return itemSlipHeader;
    }
    private String getItemSlipDiscount(SalesFicheDiscountProps salesFicheDiscountProps, int i2) {
        if (salesFicheDiscountProps.getDiscountRatio(i2).getDefinitionDouble() != 0.0d) {
            return salesFicheDiscountProps.getDiscountRatio(i2).getDefinition();
        }
        if (salesFicheDiscountProps.getDiscountTotal(i2).getDefinitionDouble() != 0.0d) {
            return salesFicheDiscountProps.getDiscountTotal(i2).getDefinition();
        }
        if (TextUtils.isEmpty(salesFicheDiscountProps.getDiscountCard(i2).getCode())) {
            return "";
        }
        return salesFicheDiscountProps.getDiscountCard(i2).getCode();
    }
    private static boolean lambdagetSlip1(SalesDetail salesDetail) {
        return salesDetail.getTrackType() == TrackType.NON_TRACK.getType();
    }
    private List<ItemSlipLine> getSlipLines(BaseErp baseErp, Sales sales, NetsisSlipType netsisSlipType, NetsisInvoiceType netsisInvoiceType) {
        ArrayList arrayList = new ArrayList();
        Iterator<SalesDetail> it = sales.getMSalesDetailList().iterator();
        while (it.hasNext()) {
            arrayList.add(getSlipLine(baseErp, sales, it.next(), netsisSlipType, netsisInvoiceType));
        }
        return arrayList;
    }
    private ItemSlipLine getSlipLine(BaseErp baseErp, Sales sales, SalesDetail salesDetail, NetsisSlipType netsisSlipType, NetsisInvoiceType netsisInvoiceType) {
        String definition;
        List table;
        ItemSlipLine itemSlipLine = new ItemSlipLine();
        itemSlipLine.setStokKodu(salesDetail.getCode());
        itemSlipLine.setMALADI(salesDetail.getName());
        double calculateCurrPrice = salesDetail.getCalculateCurrPrice() / salesDetail.getConvFact2();
        if (netsisInvoiceType == NetsisInvoiceType.ft_Iade) {
            itemSlipLine.setSTraIAF(calculateCurrPrice);
        }
        itemSlipLine.setSTraBF(calculateCurrPrice);
        itemSlipLine.setSTraNF(calculateCurrPrice);
        itemSlipLine.setYedBf(calculateCurrPrice);
        itemSlipLine.setSTraKDV(salesDetail.getVat().getDefinitionDouble());
        if (salesDetail.getLineType() == LineType.COMPOSITE_COLI.value) {
            itemSlipLine.setKoliStok(true);
        }
        itemSlipLine.setSTraGCMIK(salesDetail.getAmount().getDefinitionDouble() * salesDetail.getConvFact2());
        itemSlipLine.setSTraMALFISK(salesDetail.getSurplusAmount().getDefinitionDouble());
        int unitLineNr = baseErp.getLogoSqlHelper().getUnitLineNr(salesDetail.getCode(), salesDetail.getUnit().getCode(), true);
        itemSlipLine.setFiatBirimi(unitLineNr);
        itemSlipLine.setOlcubr(unitLineNr);
        if (baseErp.getLogoSqlHelper().getLogoParamValue("OLCUBIRIMITABLODAN").equals("D") && salesDetail.getSecondAmount().getDefinitionDouble() != 0.0d && !TextUtils.isEmpty(salesDetail.getSecondUnit().getCode())) {
            List table2 = baseErp.getLogoSqlHelper().getTable(ItemUnits.class, "ITEMCODE=? AND CODE=?", new String[]{salesDetail.getCode(), salesDetail.getUnit().getCode()});
            if (table2 == null || table2.size() == 0) {
                return itemSlipLine;
            }
            double[] unitConvfact = baseErp.getLogoSqlHelper().getUnitConvfact(salesDetail.getSecondUnit().getCode(), salesDetail.getCode());
            double definitionDouble = (salesDetail.getSecondAmount().getDefinitionDouble() * unitConvfact[1]) / unitConvfact[0];
            if (((ItemUnits) table2.get(0)).linenr == 1) {
                itemSlipLine.setSTraGCMIK2(definitionDouble);
                itemSlipLine.setBolgeFark(itemSlipLine.getSTraGCMIK2());
            } else {
                if (!TextUtils.isEmpty(salesDetail.getSelectedPrice().getCode()) && (table = baseErp.getLogoSqlHelper().getTable(ItemPrice.class, "ITEMCODE=?", new String[]{salesDetail.getCode()})) != null && table.size() > 0) {
                    itemSlipLine.setFiatBirimi(StringUtils.convertStringToInt(((ItemPrice) table.get(0)).unitCode));
                    itemSlipLine.setStraFiyatBirimi(StringUtils.convertStringToInt(((ItemPrice) table.get(0)).unitCode));
                }
                itemSlipLine.setSTraGCMIK(definitionDouble);
                List table3 = baseErp.getLogoSqlHelper().getTable(ItemUnits.class, "ITEMCODE=? AND LINENR=?", new String[]{salesDetail.getCode(), Integer.toString(6 - (unitLineNr + 1))});
                double d2 = (table3 == null || table3.size() <= 0) ? 1.0d : ((ItemUnits) table3.get(0)).convfact1 / ((ItemUnits) table3.get(0)).convfact2;
                itemSlipLine.setOlcubr(unitLineNr);
                itemSlipLine.setCEVRIM(salesDetail.getAmount().getDefinitionDouble() / definitionDouble);
                if (itemSlipLine.getOlcubr() == 2) {
                    itemSlipLine.setBrCevrim1(itemSlipLine.getCEVRIM());
                    itemSlipLine.setBrCevrim2(d2);
                }
                if (itemSlipLine.getOlcubr() == 3) {
                    itemSlipLine.setBrCevrim2(itemSlipLine.getCEVRIM());
                    itemSlipLine.setBrCevrim1(d2);
                }
                double convFact2 = calculateCurrPrice * salesDetail.getConvFact2() * itemSlipLine.getCEVRIM();
                itemSlipLine.setSTraBF(convFact2);
                itemSlipLine.setSTraNF(convFact2);
                itemSlipLine.setYedBf(convFact2);
            }
        }
        itemSlipLine.setSira(salesDetail.getLineNr());
        if (!TextUtils.isEmpty(salesDetail.getExplanation().getDefinition())) {
            itemSlipLine.setSatirBaziAciks(new ArrayList());
            itemSlipLine.getSatirBaziAciks().add(salesDetail.getExplanation().getDefinition());
        }
        itemSlipLine.setProjeKodu(sales.getProjectCode().getLogicalRef() > 0 ? baseErp.getLogoSqlHelper().getProjectCode(sales.getProjectCode().getLogicalRef()) : sales.getProjectCode().getCode());
        itemSlipLine.setSTraCARIKOD(sales.getShipAddress().getCode());
        itemSlipLine.setSTraSatIsk(getLineDiscountRatio(salesDetail.getDiscountRatio(0), salesDetail.getDiscountTotal(0), salesDetail.getCalculateCurrPrice() * salesDetail.getAmount().getDefinitionDouble()));
        itemSlipLine.setSTraSatIsk2(getLineDiscountRatio(salesDetail.getDiscountRatio(1), salesDetail.getDiscountTotal(1), salesDetail.getCalculateCurrPrice() * salesDetail.getAmount().getDefinitionDouble()));
        itemSlipLine.setSTraSatIsk3(getLineDiscountRatio(salesDetail.getDiscountRatio(2), salesDetail.getDiscountTotal(2), salesDetail.getCalculateCurrPrice() * salesDetail.getAmount().getDefinitionDouble()));
        itemSlipLine.setSTraSatIsk4(getLineDiscountRatio(salesDetail.getDiscountRatio(3), salesDetail.getDiscountTotal(3), salesDetail.getCalculateCurrPrice() * salesDetail.getAmount().getDefinitionDouble()));
        itemSlipLine.setSTraSatIsk5(getLineDiscountRatio(salesDetail.getDiscountRatio(4), salesDetail.getDiscountTotal(4), salesDetail.getCalculateCurrPrice() * salesDetail.getAmount().getDefinitionDouble()));
        if (TextUtils.isEmpty(salesDetail.getDeliveryDate().getDefinition())) {
            definition = DateAndTimeUtils.getDateSqlYearAndHour();
        } else {
            definition = salesDetail.getDeliveryDate().getDefinition();
        }
        itemSlipLine.setSTraTestar(definition);
        if (netsisSlipType == NetsisSlipType.ftLokalDepo) {
            itemSlipLine.setDEPOKODU(sales.getTransferSourceWareHouse().getLogicalRef());
            itemSlipLine.setGirDepoKodu(sales.getWareHouse().getLogicalRef());
        } else if (netsisSlipType == NetsisSlipType.ftAFat || netsisSlipType == NetsisSlipType.ftAIrs) {
            itemSlipLine.setDEPOKODU(sales.getReturnWareHouse().getLogicalRef());
        } else if (salesDetail.getWareHouse() == null || salesDetail.getWareHouse().getLogicalRef() == -1) {
            itemSlipLine.setDEPOKODU(sales.getWareHouse().getLogicalRef());
        } else {
            itemSlipLine.setDEPOKODU(salesDetail.getWareHouse().getLogicalRef());
        }
        itemSlipLine.setPlasiyerKodu(baseErp.getUser().getCode());
        itemSlipLine.setSTraFTIRSIP(String.valueOf(netsisSlipType.ordinal()));
        itemSlipLine.setStraOnayTipi(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
        itemSlipLine.setSTraGC("C");
        itemSlipLine.setSTraHTUR("H");
        itemSlipLine.setMamulmu("H");
        itemSlipLine.setSTraSIPNUM(salesDetail.getSipNum());
        itemSlipLine.setSTraSIPKONT(salesDetail.getSipKont());
        if (salesDetail.getPrCurrType() != 0) {
            itemSlipLine.setSTraDOVTIP(salesDetail.getPrCurrType());
            itemSlipLine.setSTraDovizAdi(salesDetail.getCurCodeStr());
            itemSlipLine.setDovizAdi(salesDetail.getCurCodeStr());
            itemSlipLine.setDOVTIP(salesDetail.getPrCurrType());
            try {
                itemSlipLine.setSTraDOVFIAT(salesDetail.getUsePrice());
            } catch (Exception e2) {
                Log.e(TAG, "getSlipLine: ", e2);
            }
        }
        String referenceCode = baseErp.getLogoSqlHelper().getReferenceCode(salesDetail.getReferenceCode().getLogicalRef());
        if (referenceCode.equals(".")) {
            referenceCode = baseErp.getLogoSqlHelper().getLogoParamValue("MUHREF_KODU");
        }
        itemSlipLine.setReferansKodu(referenceCode.isEmpty() ? "." : referenceCode);
        itemSlipLine.setIskFlag(0);
        String convertDateSqlDateFull = DateAndTimeUtils.convertDateSqlDateFull(sales.getGDate());
        itemSlipLine.setStraKosulK(getLineDiscountCardCode(salesDetail.getDiscountCard(0)));
        itemSlipLine.setStraKosTar(convertDateSqlDateFull);
        itemSlipLine.setStraFiiliTar(convertDateSqlDateFull);
        itemSlipLine.setStraFiyatTar(convertDateSqlDateFull);
        itemSlipLine.setDYEDEK10(DateAndTimeUtils.nowDateSqlPrint());
        if (!TextUtils.isEmpty(salesDetail.getVariant().getCode())) {
            itemSlipLine.setYapKod(salesDetail.getVariant().getCode());
        }
        if (sales.getmSalesType() == SalesType.ORDER && baseErp.getLogoSqlHelper().getLogoParamValue("STOK_AYIR").equals(ExifInterface.LONGITUDE_EAST)) {
            itemSlipLine.setSTraSIPTURU(salesDetail.getReserve().isSelect() ? ExifInterface.LATITUDE_SOUTH : "B");
        }
        if (salesDetail.getTrackType() != TrackType.NON_TRACK.getType() && salesDetail.getSalesSerialLots() != null && !salesDetail.getSalesSerialLots().isEmpty()) {
            itemSlipLine.setSeriTakibi(ExifInterface.LONGITUDE_EAST);
            itemSlipLine.setKalemSeri(new ArrayList());
            Iterator<Serilot> it = salesDetail.getSalesSerialLots().iterator();
            while (it.hasNext()) {
                Serilot next = it.next();
                ItemSlipSerialLine itemSlipSerialLine = new ItemSlipSerialLine();
                itemSlipSerialLine.setSerial1(next.code);
                itemSlipSerialLine.setTransactionType(SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(sales.getmSalesType()) ? 1 : 2);
                itemSlipSerialLine.setAmount(next.amount);
                itemSlipLine.getKalemSeri().add(itemSlipSerialLine);
            }
        }
        if (baseErp.isDueDateByLineEnabled() && salesDetail.getDueDate() != null && !TextUtils.isEmpty(salesDetail.getDueDate().toString())) {
            itemSlipLine.setVadetar(DateAndTimeUtils.convertDateSqlDateWithoutTime(salesDetail.getDueDate().toString()));
        }
        itemSlipLine.setWeight(Double.valueOf(salesDetail.getNetWeight() * salesDetail.getAmount().getDefinitionDouble()));
        itemSlipLine.setVolume(Double.valueOf(salesDetail.getNetVolume() * salesDetail.getAmount().getDefinitionDouble()));
        updateSurplusAmountByUnit(unitLineNr, itemSlipLine);
        return itemSlipLine;
    }
    private void updateSurplusAmountByUnit(int i2, ItemSlipLine itemSlipLine) {
        if (itemSlipLine.getCEVRIM() != 0.0d) {
            itemSlipLine.setSTraMALFISK(itemSlipLine.getSTraMALFISK() / itemSlipLine.getCEVRIM());
            return;
        }
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ItemUnits.class, "ITEMCODE=? AND LINENR=?", new String[]{itemSlipLine.getStokKodu(), Integer.toString(i2)});
        if (table == null || table.size() == 0 || ((ItemUnits) table.get(0)).convfact1 == 0.0d) {
            return;
        }
        itemSlipLine.setSTraMALFISK((itemSlipLine.getSTraMALFISK() * ((ItemUnits) table.get(0)).convfact2) / ((ItemUnits) table.get(0)).convfact1);
    }
    private double getDiscount(FicheDiscountProp ficheDiscountProp) {
        if (ficheDiscountProp != null) {
            return ficheDiscountProp.getDefinitionDouble();
        }
        return 0.0d;
    }
    private double getLineDiscountRatio(FicheDiscountProp ficheDiscountProp, FicheDiscountProp ficheDiscountProp2, double d2) {
        if (ficheDiscountProp != null && ficheDiscountProp.getDefinitionDouble() > 0.0d) {
            return ficheDiscountProp.getDefinitionDouble();
        }
        if (ficheDiscountProp2 == null || ficheDiscountProp2.getDefinitionDouble() <= 0.0d) {
            return 0.0d;
        }
        return convertRatioToTotal(ficheDiscountProp2.getDefinitionDouble(), d2);
    }
    private static boolean lambdagetSlip2(SalesDetail salesDetail) {
        return salesDetail.getTrackType() == TrackType.NON_TRACK.getType();
    }
    private int getLineDiscountCard(FicheDiscountRefProp ficheDiscountRefProp) {
        if (ficheDiscountRefProp == null || TextUtils.isEmpty(ficheDiscountRefProp.getCode())) {
            return 0;
        }
        return ficheDiscountRefProp.getLogicalRef();
    }
    private String getLineDiscountCardCode(FicheDiscountRefProp ficheDiscountRefProp) {
        return ficheDiscountRefProp == null || TextUtils.isEmpty(ficheDiscountRefProp.getCode()) ? "" : ficheDiscountRefProp.getCode();
    }
    public NetsisServiceResult getCaseCash(CaseCash caseCash) {
        String str;
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        MatterSettings matterSettings = baseErp.getMatterSettings(ContextUtils.getmContext(), FicheType.CASE_CASH);
        String str2 = caseCash.andFicheNo;
        if (!Preferences.getCashPrintUseMatter(ContextUtils.getmContext()) && !caseCash.docNo.isEmpty()) {
            str2 = caseCash.docNo;
        }
        SafeDeposit safeDeposit = new SafeDeposit();
        safeDeposit.setMuhasebelesmisBelge(false);
        safeDeposit.setTransactSupport(true);
        safeDeposit.setKod(caseCash.clCode);
        safeDeposit.setKsMasKod(caseCash.caseCOde);
        if (matterSettings.isUseMatterNo()) {
            str2 = caseCash.ficheNo;
        }
        safeDeposit.setFisno(str2);
        safeDeposit.setTarih(DateAndTimeUtils.nowDateSqlPrint());
        safeDeposit.setiO("G");
        safeDeposit.setTip("C");
        safeDeposit.setCariMuh("C");
        safeDeposit.setOnayTipi(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
        safeDeposit.setGecerli(ExifInterface.LONGITUDE_EAST);
        safeDeposit.setAciklama(caseCash.desc);
        safeDeposit.setTutar(caseCash.total);
        if (caseCash.salesManCode.equals("")) {
            str = baseErp.getUser().getCode();
        } else if (caseCash.salesManCode.indexOf(" - ") != -1) {
            String str3 = caseCash.salesManCode;
            str = str3.substring(0, str3.indexOf(" - "));
        } else {
            str = caseCash.salesManCode;
        }
        safeDeposit.setPlasiyerKodu(str);
        safeDeposit.setProjeKodu(baseErp.getLogoSqlHelper().getProjectCode(StringUtils.convertStringToInt(caseCash.projectRef)));
        safeDeposit.setSubeKodu(((Preferences.NetsisUserSettings) baseErp.getUserSettings()).getBranchCode());
        safeDeposit.setOdemeTuru(0);
        safeDeposit.setKaynak(1);
        safeDeposit.setKrtSozMasInckeyNo(0);
        safeDeposit.setCariHareketAciklama(caseCash.desc);
        return buildServiceResult(baseErp.getLogoSqlHelper(), DataObjectType.ADDCASECASH, caseCash.getgDate(), caseCash.clCode, caseCash.andFicheNo, safeDeposit, caseCash, caseCash.logicalRef);
    }
    public NetsisServiceResult getCheque(ChequeDeed chequeDeed) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        return buildServiceResult(baseErp.getLogoSqlHelper(), DataObjectType.ADDCHEQUE, chequeDeed.getChequedeed().date, chequeDeed.getChequedeed().clCode, chequeDeed.getChequedeed().andFicheNo, getChequeDeed(baseErp, chequeDeed, NetsisChequeAndDeedType.csMCEK, NetsisChequeAndDeedSaveType.ektCekSenAlma), chequeDeed, chequeDeed.getChequedeed().logicalRef);
    }
    public NetsisServiceResult getDeed(ChequeDeed chequeDeed) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        return buildServiceResult(baseErp.getLogoSqlHelper(), DataObjectType.ADDDEED, chequeDeed.getChequedeed().date, chequeDeed.getChequedeed().clCode, chequeDeed.getChequedeed().andFicheNo, getChequeDeed(baseErp, chequeDeed, NetsisChequeAndDeedType.csMSEN, NetsisChequeAndDeedSaveType.ektCekSenAlma), chequeDeed, chequeDeed.getChequedeed().logicalRef);
    }
    private NetsisChequeAndDeedMain getChequeDeed(BaseErp baseErp, ChequeDeed chequeDeed, NetsisChequeAndDeedType netsisChequeAndDeedType, NetsisChequeAndDeedSaveType netsisChequeAndDeedSaveType) {
        MatterSettings matterSettings = baseErp.getMatterSettings(ContextUtils.getmContext(), netsisChequeAndDeedType == NetsisChequeAndDeedType.csMCEK ? FicheType.CHEQUE : FicheType.DEED);
        return NetsisChequeAndDeedMain.newBuilder().withOtoNumaraGetir(!matterSettings.isUseMatterNo()).withBordroNo(chequeDeed.getChequedeed().andFicheNo).withCekSenEvrakKaydetmeTip(netsisChequeAndDeedSaveType).withTip(netsisChequeAndDeedType).withEvrakAdedi(chequeDeed.getChequedeedDetail().size()).withEvraklar(getChequeDeedLines(baseErp, chequeDeed, chequeDeed.getChequedeedDetail(), !matterSettings.isUseMatterNo())).build();
    }
    private List<NetsisChequeAndDeed> getChequeDeedLines(BaseErp baseErp, ChequeDeed chequeDeed, List<ChequedeedDetail> list, boolean z) {
        ArrayList<NetsisChequeAndDeed> arrayList = new ArrayList<>();
        for (ChequedeedDetail chequedeedDetail : list) {
            arrayList.add(getChequeDeedLine(baseErp, chequeDeed, chequedeedDetail, z));
        }
        return arrayList;
    }
    private NetsisChequeAndDeed getChequeDeedLine(BaseErp baseErp, ChequeDeed chequeDeed, ChequedeedDetail chequedeedDetail, boolean z) {
        NetsisChequeAndDeed netsisChequeAndDeed = new NetsisChequeAndDeed();
        if (!z) {
            netsisChequeAndDeed.setsCNO(chequeDeed.getChequedeed().ficheNo);
            netsisChequeAndDeed.setsCALBNO(chequeDeed.getChequedeed().ficheNo);
        }
        netsisChequeAndDeed.setcEKSERI("H");
        netsisChequeAndDeed.setaSC(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
        netsisChequeAndDeed.setyERI("P");
        netsisChequeAndDeed.setvADETRH(DateAndTimeUtils.convertSqlDate(chequedeedDetail.date));
        netsisChequeAndDeed.setsCGIRTRH(DateAndTimeUtils.convertSqlDate(chequeDeed.getChequedeed().getgDate()));
        netsisChequeAndDeed.setsCODETRH(DateAndTimeUtils.convertSqlDate(chequedeedDetail.date));
        netsisChequeAndDeed.setTutar(chequedeedDetail.total);
        netsisChequeAndDeed.setSubeKodu(((Preferences.NetsisUserSettings) baseErp.getUserSettings()).getBranchCode());
        netsisChequeAndDeed.setcNUMARA(chequedeedDetail.accNo);
        netsisChequeAndDeed.setcEKSERI(chequedeedDetail.serialNo);
        netsisChequeAndDeed.setPlasiyerKodu(baseErp.getUser().getCode());
        netsisChequeAndDeed.setsCVERENK(chequeDeed.getChequedeed().clCode);
        String projectCode = baseErp.getLogoSqlHelper().getProjectCode(StringUtils.convertStringToInt(chequeDeed.getChequedeed().projectRef));
        if (TextUtils.isEmpty(projectCode)) {
            projectCode = null;
        }
        netsisChequeAndDeed.setProjeKodu(projectCode);
        netsisChequeAndDeed.setSubeKodu(((Preferences.NetsisUserSettings) baseErp.getUserSettings()).getBranchCode());
        return netsisChequeAndDeed;
    }
    public NetsisServiceResult getCreditCard(CashCreditX cashCreditX) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        return buildServiceResult(baseErp.getLogoSqlHelper(), DataObjectType.ADDCREDIT, cashCreditX.getCashCredit().getgDate(), cashCreditX.getCashCredit().clCode, cashCreditX.getCashCredit().andFicheNo, getMixedReceiptsMain(baseErp, ReceiptType.CREDIT, cashCreditX), cashCreditX, cashCreditX.getCashCredit().logicalRef);
    }
    public NetsisServiceResult getCash(CashCreditX cashCreditX) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        return buildServiceResult(baseErp.getLogoSqlHelper(), DataObjectType.ADDCASH, cashCreditX.getCashCredit().getgDate(), cashCreditX.getCashCredit().clCode, cashCreditX.getCashCredit().andFicheNo, getMixedReceiptsMain(baseErp, ReceiptType.CASH, cashCreditX), cashCreditX, cashCreditX.getCashCredit().logicalRef);
    }
    private SafeDeposit getSafeDeposit(BaseErp baseErp, CashCreditX cashCreditX, ReceiptType receiptType) {
        if (cashCreditX.getCashCreditDetails() == null || cashCreditX.getCashCreditDetails().size() <= 0) {
            return null;
        }
        CashCreditDetail cashCreditDetail = cashCreditX.getCashCreditDetails().get(0);
        SafeDeposit safeDeposit = new SafeDeposit();
        safeDeposit.setTransactSupport(true);
        safeDeposit.setKod(cashCreditX.getCashCredit().clCode);
        safeDeposit.setKsMasKod(baseErp.getLogoSqlHelper().getMainCaseCashCode());
        safeDeposit.setFisno(cashCreditX.getCashCredit().andFicheNo);
        safeDeposit.setTarih(DateAndTimeUtils.nowDateSqlPrint());
        safeDeposit.setiO("G");
        safeDeposit.setTip("C");
        safeDeposit.setCariMuh("C");
        if (receiptType == ReceiptType.CREDIT) {
            safeDeposit.setKrediKartNo(cashCreditDetail.crediCardNo);
            safeDeposit.setKaynak(1);
            safeDeposit.setTaksit(0);
        } else {
            safeDeposit.setKaynak(0);
        }
        safeDeposit.setCariHareketAciklama(cashCreditDetail.desc);
        safeDeposit.setAciklama(cashCreditX.getCashCredit().desc1);
        safeDeposit.setTutar(cashCreditX.getCashCredit().total);
        safeDeposit.setPlasiyerKodu(baseErp.getUser().getCode());
        safeDeposit.setProjeKodu(baseErp.getLogoSqlHelper().getProjectCode(StringUtils.convertStringToInt(cashCreditX.getCashCredit().projectRef)));
        safeDeposit.setSubeKodu(((Preferences.NetsisUserSettings) baseErp.getUserSettings()).getBranchCode());
        safeDeposit.setMuhasebelesmisBelge(false);
        return safeDeposit;
    }
    static  class C24781 {
        static final  int[] f1170x3afa6613 = new int[ReceiptType.values().length];
        static {
            try {
                f1170x3afa6613[ReceiptType.CASE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1170x3afa6613[ReceiptType.CREDIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1170x3afa6613[ReceiptType.CASH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
    private MixedReceiptsMain getMixedReceiptsMain(BaseErp baseErp, ReceiptType receiptType, CashCreditX cashCreditX) {
        MatterSettings matterSettings = new MatterSettings();
        int i2 = C24781.f1170x3afa6613[receiptType.ordinal()];
        if (i2 == 1) {
            matterSettings = baseErp.getMatterSettings(ContextUtils.getmContext(), FicheType.CASE_CASH);
        } else if (i2 == 2) {
            matterSettings = baseErp.getMatterSettings(ContextUtils.getmContext(), FicheType.CREDIT_CART);
        } else if (i2 == 3) {
            matterSettings = baseErp.getMatterSettings(ContextUtils.getmContext(), FicheType.CASH);
        } else {
            matterSettings.setUseMatterNo(false);
        }
        String str = cashCreditX.getCashCredit().andFicheNo;
        if (!Preferences.getCashPrintUseMatter(ContextUtils.getmContext()) && cashCreditX.getCashCreditDetails().size() == 1 && !cashCreditX.getCashCreditDetails().get(0).docNo.isEmpty()) {
            str = cashCreditX.getCashCreditDetails().get(0).docNo;
        }
        MixedReceiptsMain.Builder withKasaKod = MixedReceiptsMain.newBuilder().withTransactSupport(true).withKasaKod(baseErp.getLogoSqlHelper().getMainCaseCashCode());
        if (matterSettings.isUseMatterNo()) {
            str = cashCreditX.getCashCredit().ficheNo;
        }
        return withKasaKod.withBelgeNo(str).withCariKod(cashCreditX.getCashCredit().clCode).withTahsilatKalemAdedi(cashCreditX.getCashCreditDetails().size()).withTahsilats(getMixedReceiptLines(baseErp, receiptType, cashCreditX)).withIslemTarihi(DateAndTimeUtils.nowDateSqlPrint()).withTime(DateAndTimeUtils.getTimeOnly(cashCreditX.getCashCredit().getgDate())).withSpeCode(cashCreditX.getCashCredit().specode).withBankCode(cashCreditX.getCashCredit().bankCode).withBankDescription(cashCreditX.getCashCredit().bankDescription).build();
    }
    private List<MixedReceipt> getMixedReceiptLines(BaseErp baseErp, ReceiptType receiptType, CashCreditX cashCreditX) {
        ArrayList arrayList = new ArrayList();
        for (CashCreditDetail cashCreditDetail : cashCreditX.getCashCreditDetails()) {
            arrayList.add(getMixedReceiptLine(baseErp, receiptType, cashCreditX, cashCreditDetail));
        }
        return arrayList;
    }
    private MixedReceipt getMixedReceiptLine(BaseErp baseErp, ReceiptType receiptType, CashCreditX cashCreditX, CashCreditDetail cashCreditDetail) {
        MixedReceipt mixedReceipt = new MixedReceipt();
        if (receiptType == ReceiptType.CASE) {
            mixedReceipt.setContractCode("NAK\u0130T");
        } else {
            mixedReceipt.setContractCode(cashCreditX.getCashCredit().aggrCode);
            mixedReceipt.setCardNo(cashCreditDetail.crediCardNo);
            mixedReceipt.setInstalmentCount(cashCreditX.getCashCredit().getInstallmentCount());
        }
        mixedReceipt.setCurrencyTotal(0.0d);
        mixedReceipt.setExplanation(cashCreditDetail.desc);
        mixedReceipt.setPlasiyerCode(baseErp.getUser().getCode());
        mixedReceipt.setPrice(cashCreditDetail.total);
        mixedReceipt.setProjectCode(baseErp.getLogoSqlHelper().getProjectCode(StringUtils.convertStringToInt(cashCreditX.getCashCredit().projectRef)));
        mixedReceipt.setExplanation(cashCreditX.getCashCredit().desc1);
        mixedReceipt.setCaseCode(baseErp.getLogoSqlHelper().getMainCaseCashCode());
        mixedReceipt.setDocNo(cashCreditDetail.docNo);
        return mixedReceipt;
    }
    public NetsisSelectResult getVisit(VisitInfo visitInfo) {
        return ((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().insertVisit(visitInfo);
    }
    public NetsisSelectResult getTodo(TodoInfoDb todoInfoDb) {
        return ((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().insertTodoWorProc(todoInfoDb);
    }
    public NetsisSelectResult getPenetration(Penetration penetration) {
        return ((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().insertPenetration(penetration);
    }
    public List<NetsisSelectResult> getPenetrationDetailList(Penetration penetration) {
        ArrayList arrayList = new ArrayList();
        for (PenetrationLine penetrationLine : penetration.getPenetrations()) {
            if ((penetration.isExist() && penetrationLine.getExist().isSelect()) || (!penetration.isExist() && penetrationLine.getAmount().getDefinitionDouble() > 0.0d)) {
                arrayList.add(((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().insertPenetrationDetail(penetration, penetrationLine));
            }
        }
        return arrayList;
    }
    public NetsisSelectResult getCabinTrans(CabinTrans cabinTrans) {
        return ((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().insertWorCabinTrans(cabinTrans);
    }
    public NetsisSelectResult getCabin(int i2) {
        return ((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().updateWorCabin(i2);
    }
    public NetsisServiceResult getWhTransfer(@UnknownNullability PrintSlipModel sales) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        return buildServiceResult(baseErp.getLogoSqlHelper(), DataObjectType.ADDWHTRANSFER, sales.getGDate(), sales.getClCode(), sales.getAndFicheNo(), (Object) getSlip(baseErp, sales, NetsisSlipType.ftLokalDepo, NetsisInvoiceType.ft_Bos), (Object) sales, sales.getLogicalRef());
    }
    public NetsisServiceResult buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, String str, int i2, String str2, Object obj, Object obj2, int i3) {
        return (NetsisServiceResult) NetsisServiceResult.newBuilder().withClCode(iSqlHelper.getClCode(i2)).withClName(iSqlHelper.getClName(i2)).withMClRef(i2).withDate(str).withLogicalRef(i3).withDataType(dataObjectType).withGuid(str2).withData(obj2).withSendData(obj).build();
    }
    public NetsisServiceResult buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, String str, String str2, int i2, String str3, String str4, Object obj, int i3) {
        return (NetsisServiceResult) NetsisServiceResult.newBuilder().withClCode(str).withClName(iSqlHelper.getClName(str)).withMClRef(iSqlHelper.getClLogicalRef(str)).withDate(str2).withLogicalRef(i3).withDataType(dataObjectType).withGuid(str3).withData(obj).withSendData(null).build();
    }
    public NetsisServiceResult buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, String str, String str2, String str3, Object obj, Object obj2, int i2) {
        return (NetsisServiceResult) NetsisServiceResult.newBuilder().withClCode(str2).withClName(iSqlHelper.getClName(str2)).withMClRef(iSqlHelper.getClLogicalRef(str2)).withDate(str).withLogicalRef(i2).withDataType(dataObjectType).withGuid(str3).withData(obj2).withSendData(obj).build();
    } 
    public NetsisServiceResult buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, int i2, String str, String str2, Object obj, Object obj2, int i3) {
        NetsisServiceResult.NetsisServiceBuilder newBuilder = NetsisServiceResult.newBuilder();
        Context context = ContextUtils.getmContext();
        TransferOperationName transferOperationName = TransferOperationName.DEMAND;
        return (NetsisServiceResult) newBuilder.withClCode(ContextUtils.formatStringEnglish("%s %d", context.getString(transferOperationName.getResId()), Integer.valueOf(i2))).withClName(ContextUtils.formatStringEnglish("%s %d", ContextUtils.getmContext().getString(transferOperationName.getResId()), Integer.valueOf(i2))).withMClRef(i2).withDate(str).withLogicalRef(i3).withDataType(dataObjectType).withGuid(str2).withData(obj2).withSendData(obj).build();
    }
}
