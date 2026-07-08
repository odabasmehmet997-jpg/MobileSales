package com.proje.mobilesales.core.mail;

import com.proje.mobilesales.features.sales.model.Sales;

public interface FicheHtmlCreator {
    String getOrderHtml(Sales sales);
}
