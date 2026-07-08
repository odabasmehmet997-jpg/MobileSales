package com.proje.mobilesales.core.masterpass;

public class AccountStatusResult {
    private AccountStatus accountStatus;
    private String message;
    public String getMessage() {
        return this.message;
    }
    public void setMessage(String str) {
        this.message = str;
    }
    public AccountStatus getAccountStatus() {
        return this.accountStatus;
    }
    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
