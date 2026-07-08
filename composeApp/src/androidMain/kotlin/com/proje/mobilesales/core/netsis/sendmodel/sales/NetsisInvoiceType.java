package com.proje.mobilesales.core.netsis.sendmodel.sales;



public enum NetsisInvoiceType {
    ft_Bos(0),
    ft_Kapali(1),
    ft_Acik(2),
    ft_Muhtelif(3),
    ft_Iade(4),
    ft_ZIade(5),
    ft_Ihracat(6),
    ft_YurtIci(7),
    ft_YurtDisi(8);

    int mStatusValue;

    NetsisInvoiceType(int i2) {
        this.mStatusValue = i2;
    }

    public int getStatusValue() {
        return this.mStatusValue;
    }
}
