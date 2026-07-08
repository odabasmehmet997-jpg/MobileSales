package com.proje.mobilesales.core.interfaces;

import com.proje.mobilesales.features.sales.model.Sales;

public interface ConvertSales {
    void convertSales(Sales sales);

    Sales convertSalesFicheToSales();
}
