package com.proje.mobilesales.core.interfaces;

import android.widget.EditText;
import com.proje.mobilesales.features.sales.model.Sales;

public interface ISalesDiscountFragment {
    Sales getClonedSales();

    EditText[] getEdtSalesDetailDiscountRatios();

    EditText[] getEdtSalesDetailDiscountTotals();
}
