package com.proje.mobilesales.core.edocument;

public class ShowEDocumentResponse extends EDocumentResponse {
    private String envolopeId;
    private String ettn;
    private String htmlContent;

    public String getHtmlContent() {
        return this.htmlContent;
    }
    public void setHtmlContent(String str) {
        this.htmlContent = str;
    }
    public String getEnvolopeId() {
        return this.envolopeId;
    }
    public void setEnvolopeId(String str) {
        this.envolopeId = str;
    }
    public String getEttn() {
        return this.ettn;
    }
    public void setEttn(String str) {
        this.ettn = str;
    }
}
