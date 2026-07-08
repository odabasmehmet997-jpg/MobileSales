package com.proje.mobilesales.core.mail;

import android.text.TextUtils;
import android.util.Log;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.utils.CalculateUtils;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.dbmodel.ShipAddress;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import java.util.Iterator;
import java.util.List;

public final class FicheHtmlCreatorImpl implements FicheHtmlCreator {
    private static final Object LOCK = new Object();
    private static final String TAG = "com.proje.mobilesales.core.mail.FicheHtmlCreatorImpl";
    private static FicheHtmlCreatorImpl instance;

    private FicheHtmlCreatorImpl() {
    }

    public static FicheHtmlCreatorImpl getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                instance = new FicheHtmlCreatorImpl();
            }
        }
        return instance;
    }
    public String getOrderHtml(Sales sales) {
        String formatStringEnglish = ContextUtils.formatStringEnglish(R.string.mail_order, getOrderHeader(sales, ErpCreator.getInstance().getmBaseErp()), getOrderDetail(sales), getOrderTotal(sales));
        Log.d(TAG, "getOrderHtml: " + formatStringEnglish);
        return formatStringEnglish;
    }

    private String getOrderHeader(Sales sales, BaseErp baseErp) {
        return ContextUtils.formatStringEnglish(R.string.mail_order_header, sales.getGDate(), sales.getFicheNo(), sales.getClCode(), sales.getDocumentNo().toString(), sales.getSpeCode().toString(), sales.getWareHouse().toString(), baseErp.getUser().getCode() + " / " + baseErp.getUser().getUserName(), baseErp.getLogoSqlHelper().getClName(sales.getShipAccount().getLogicalRef()), baseErp.getLogoSqlHelper().getShipAddressCode(sales.getShipAddress().getLogicalRef(), sales.getClRef()), getShipAddress(baseErp, sales.getShipAddress().getLogicalRef()), sales.getExplanation1().toString(), sales.getExplanation2().toString(), sales.getExplanation3().toString(), sales.getExplanation4().toString());
    }

    private String getShipAddress(BaseErp baseErp, int i2) {
        List table = baseErp.getLogoSqlHelper().getTable(ShipAddress.class, "LOGICALREF=?", new String[]{StringUtils.convertIntToString(i2)});
        if (table != null && table.size() > 0) {
            return ((ShipAddress) table.get(0)).getFullAddress();
        }
        return "";
    }

    private String getOrderDetail(Sales sales) {
        return ContextUtils.formatStringEnglish(R.string.mail_order_detail_main, getOrderDetailHtml(sales) + getOrderGeneralDiscountHtml(sales));
    }

    private String getOrderDetailHtml(Sales sales) {
        Iterator<SalesDetail> it = sales.getMSalesDetailList().iterator();
        String str = "";
        while (it.hasNext()) {
            SalesDetail next = it.next();
            str = str + ContextUtils.formatStringEnglish(R.string.mail_order_detail_line, next.getCode(), next.getName(), next.getUnit().getCode(), next.getAmount().toString(), StringUtils.formatDouble(next.getCalculateCurrPrice()), StringUtils.formatDouble(next.getProductTotal()), next.getVat().toString(), StringUtils.formatDouble(0.0d), next.getExplanation());
            for (int i2 = 0; i2 < next.getDiscountLength(); i2++) {
                if (next.getDiscountTotal(i2).getDefinitionDouble() > 0.0d) {
                    str = str + ContextUtils.formatStringEnglish(R.string.mail_order_detail_discount_line, StringUtils.formatDouble(next.getDiscountTotal(i2).getDefinitionDouble()));
                } else if (next.getDiscountRatio(i2).getDefinitionDouble() > 0.0d) {
                    str = str + ContextUtils.formatStringEnglish(R.string.mail_order_detail_discount_line, StringUtils.formatDouble(CalculateUtils.calculateDiscountRatioToTotal(next.getProductLineNet() + next.getProductDiscountTotal(), next.getDiscountRatio(i2).getDefinitionDouble())));
                } else if (next.getDiscountCard(i2).getLogicalRef() > 0) {
                    str = str + ContextUtils.formatStringEnglish(R.string.mail_order_detail_discount_line, StringUtils.formatDouble(next.getDiscountCard(i2).getDefinitionDouble()));
                }
            }
        }
        return str;
    }

    private String getOrderGeneralDiscountHtml(Sales sales) {
        String str = "";
        for (int i2 = 0; i2 < sales.getDiscountLength(); i2++) {
            if (sales.getDiscountTotal(i2).getDefinitionDouble() > 0.0d) {
                str = str + ContextUtils.formatStringEnglish(R.string.mail_order_detail_discount_line, StringUtils.formatDouble(sales.getDiscountTotal(i2).getDefinitionDouble()));
            } else if (sales.getDiscountRatio(i2).getDefinitionDouble() > 0.0d) {
                str = str + ContextUtils.formatStringEnglish(R.string.mail_order_detail_discount_line, StringUtils.formatDouble(CalculateUtils.calculateDiscountRatioToTotal(sales.getProductTotal(), sales.getDiscountRatio(i2).getDefinitionDouble())));
            } else if (sales.getDiscountCard(i2).getLogicalRef() > 0) {
                str = str + ContextUtils.formatStringEnglish(R.string.mail_order_detail_discount_line, StringUtils.formatDouble(sales.getDiscountCard(i2).getDefinitionDouble()));
            }
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return ContextUtils.getStringResource(R.string.mail_table_add_row_line) + str;
    }

    private String getOrderTotal(Sales sales) {
        return ContextUtils.formatStringEnglish(R.string.mail_order_discount, StringUtils.formatDouble(sales.getDiscTotal()), StringUtils.formatDouble(sales.getTotal()), StringUtils.formatDouble(sales.getTotalVat()), StringUtils.formatDouble(sales.getTotalNet()));
    }
}
