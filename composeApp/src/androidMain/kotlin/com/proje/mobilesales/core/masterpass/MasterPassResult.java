package com.proje.mobilesales.core.masterpass;

import cardtek.masterpass.results.ValidateTransaction3DResult;
import cardtek.masterpass.results.ValidateTransactionResult;
import java.io.Serializable;

public class MasterPassResult implements Serializable {
    boolean is3DPay;
    ValidateTransaction3DResult validateTransaction3DResult;
    ValidateTransactionResult validateTransactionResult;

    public boolean is3DPay() {
        return this.is3DPay;
    }

    public void setIs3DPay(boolean z) {
        this.is3DPay = z;
    }

    public ValidateTransactionResult getValidateTransactionResult() {
        return this.validateTransactionResult;
    }

    public void setValidateTransactionResult(ValidateTransactionResult validateTransactionResult) {
        this.validateTransactionResult = validateTransactionResult;
    }

    public ValidateTransaction3DResult getValidateTransaction3DResult() {
        return this.validateTransaction3DResult;
    }

    public void setValidateTransaction3DResult(ValidateTransaction3DResult validateTransaction3DResult) {
        this.validateTransaction3DResult = validateTransaction3DResult;
    }
}
