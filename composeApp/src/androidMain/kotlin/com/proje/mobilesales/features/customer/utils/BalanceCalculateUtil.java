package com.proje.mobilesales.features.customer.utils;

import com.proje.mobilesales.features.customer.repository.BaseCustomerRepository;
import com.proje.mobilesales.features.customer.view.BaseCustomerViewModel;

public final class BalanceCalculateUtil {
    public static final BalanceCalculateUtil INSTANCE = new BalanceCalculateUtil();
    private static final BaseCustomerRepository repository;
    private static final BaseCustomerViewModel viewModel;

    private BalanceCalculateUtil() {
    }

    static {
        BaseCustomerRepository baseCustomerRepository = new BaseCustomerRepository();
        repository = baseCustomerRepository;
        viewModel = new BaseCustomerViewModel(baseCustomerRepository);
    }

    private double getNotTransferredCreditTotal(int i2) {
        return viewModel.getSqlHelper().getLocalCreditTotal(i2);
    }

    private double getNotTransferredDebitTotal(int i2) {
        return viewModel.getSqlHelper().getLocalDebitTotal(i2);
    }

    public double getTotalCredit(int i2, double d2) {
        return getNotTransferredCreditTotal(i2) + d2;
    }

    public double getTotalDebit(int i2, double d2) {
        return getNotTransferredDebitTotal(i2) + d2;
    }

    public static double getTotalBalance(int i2, double d2) {
        BalanceCalculateUtil balanceCalculateUtil = INSTANCE;
        return d2 + (balanceCalculateUtil.getNotTransferredDebitTotal(i2) - balanceCalculateUtil.getNotTransferredCreditTotal(i2));
    }
}
