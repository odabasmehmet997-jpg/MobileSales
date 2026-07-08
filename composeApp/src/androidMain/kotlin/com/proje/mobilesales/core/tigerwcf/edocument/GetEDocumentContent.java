package com.proje.mobilesales.core.tigerwcf.edocument;

import androidx.core.app.NotificationCompat;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.core.tigerwcf.StringElementAppend;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.strategy.Name;

public class GetEDocumentContent {

    private StringElementAppend aDocTyp;
    private StringElementAppend bOutFormat;
    private StringElementAppend cGuid;
    private StringElementAppend dMsg;
    private StringElementAppend eLbsLoadPass;
    private StringElementAppend fSecurityCode;
    private String f1216id;
    private String root;
    private String xmlns;
    public String getXmlns() {
        return this.xmlns;
    }
    public void setXmlns(String str) {
        this.xmlns = str;
    }
    public String getId() {
        return this.f1216id;
    }
    public void setId(String str) {
        this.f1216id = str;
    }
    public String getRoot() {
        return this.root;
    }
    public void setRoot(String str) {
        this.root = str;
    }
    public StringElementAppend getLbsLoadPass() {
        return this.eLbsLoadPass;
    }
    public void setLbsLoadPass(StringElementAppend stringElementAppend) {
        this.eLbsLoadPass = stringElementAppend;
    }
    public StringElementAppend getSecurityCode() {
        return this.fSecurityCode;
    }
    public void setSecurityCode(StringElementAppend stringElementAppend) {
        this.fSecurityCode = stringElementAppend;
    }
    public StringElementAppend getGuid() {
        return this.cGuid;
    }
    public void setGuid(StringElementAppend stringElementAppend) {
        this.cGuid = stringElementAppend;
    }
    public StringElementAppend getOutFormat() {
        return this.bOutFormat;
    }
    public void setOutFormat(StringElementAppend stringElementAppend) {
        this.bOutFormat = stringElementAppend;
    }
    public StringElementAppend getDocTyp() {
        return this.aDocTyp;
    }
    public void setDocTyp(StringElementAppend stringElementAppend) {
        this.aDocTyp = stringElementAppend;
    }
    public StringElementAppend getMsg() {
        return this.dMsg;
    }
    public void setMsg(StringElementAppend stringElementAppend) {
        this.dMsg = stringElementAppend;
    }
}
