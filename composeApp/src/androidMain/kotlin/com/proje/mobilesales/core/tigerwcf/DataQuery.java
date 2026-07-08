package com.proje.mobilesales.core.tigerwcf;

import androidx.core.app.NotificationCompat;
import com.proje.mobilesales.BuildConfig;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.strategy.Name;



public class DataQuery {

    @Element(name = "dataType", required = false)
    private IntElement adataType;

    @Element(name = "dataReference", required = false)
    private IntElement bdataReference;

    @Element(name = "dataXML", required = false)
    private StringElementAppend cdataXML;

    @Element(name = "paramXML", required = false)
    private StringElementAppend dparamXML;

    @Element(name = "errorString", required = false)
    private StringElementAppend eerrorString;

    @Element(name = NotificationCompat.CATEGORY_STATUS, required = false)
    private IntElement fstatus;

    @Element(name = "LbsLoadPass", required = false)
    private StringElementAppend gLbsLoadPass;

    @Element(name = "FirmNr", required = false)
    private StringElementAppend hFirmNr;

    @Attribute(empty = "o0", name = Name.MARK, required = false)
    private String f1200id;

    @Element(name = "securityCode", required = false)
    private StringElementAppend ksecurityCode;

    @Attribute(empty = BuildConfig.NETSIS_DEMO_PASSWORD, name = "c:root", required = false)
    private String root;

    @Attribute(empty = "http://tempuri.org/", name = "xmlns", required = false)
    private String xmlns;

    public IntElement getAdataType() {
        return this.adataType;
    }

    public void setAdataType(IntElement intElement) {
        this.adataType = intElement;
    }

    public IntElement getBdataReference() {
        return this.bdataReference;
    }

    public void setBdataReference(IntElement intElement) {
        this.bdataReference = intElement;
    }

    public StringElementAppend getCdataXML() {
        return this.cdataXML;
    }

    public void setCdataXML(StringElementAppend stringElementAppend) {
        this.cdataXML = stringElementAppend;
    }

    public StringElementAppend getDparamXML() {
        return this.dparamXML;
    }

    public void setDparamXML(StringElementAppend stringElementAppend) {
        this.dparamXML = stringElementAppend;
    }

    public StringElementAppend getEerrorString() {
        return this.eerrorString;
    }

    public void setEerrorString(StringElementAppend stringElementAppend) {
        this.eerrorString = stringElementAppend;
    }

    public IntElement getFstatus() {
        return this.fstatus;
    }

    public void setFstatus(IntElement intElement) {
        this.fstatus = intElement;
    }

    public StringElementAppend getgLbsLoadPass() {
        return this.gLbsLoadPass;
    }

    public void setgLbsLoadPass(StringElementAppend stringElementAppend) {
        this.gLbsLoadPass = stringElementAppend;
    }

    public StringElementAppend gethFirmNr() {
        return this.hFirmNr;
    }

    public void sethFirmNr(StringElementAppend stringElementAppend) {
        this.hFirmNr = stringElementAppend;
    }

    public StringElementAppend getKsecurityCode() {
        return this.ksecurityCode;
    }

    public void setKsecurityCode(StringElementAppend stringElementAppend) {
        this.ksecurityCode = stringElementAppend;
    }
}
