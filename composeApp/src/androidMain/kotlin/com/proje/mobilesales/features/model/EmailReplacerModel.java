package com.proje.mobilesales.features.model;

import com.proje.mobilesales.core.emailreplacer.EmailReplacer;

public class EmailReplacerModel {
    private int clRef;
    private String email;
    private EmailReplacer emailReplacer;
    private int ficheRef;
    public EmailReplacer getEmailReplacer() {
        return emailReplacer;
    }
    public void setEmailReplacer(final EmailReplacer emailReplacer) {
        this.emailReplacer = emailReplacer;
    }
    public int getClRef() {
        return clRef;
    }
    public void setClRef(final int i2) {
        clRef = i2;
    }
    public int getFicheRef() {
        return ficheRef;
    }
    public void setFicheRef(final int i2) {
        ficheRef = i2;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(final String str) {
        email = str;
    }
}
